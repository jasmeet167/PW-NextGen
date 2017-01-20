/*
 * THIS PROGRAM IS THE PROPERTY OF CSC FINANCIAL SERVICES GROUP. IT MAY NOT BE
 * COPIED IN WHOLE OR IN PART WITHOUT THE EXPRESS WRITTEN CONSENT OF CSC
 * FINANCIAL SERVICES GROUP.
 */

package com.csc.fsg.life.pw.io;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;

import org.apache.commons.logging.Log;

import com.csc.fsg.life.pw.util.Constants;
import com.csc.fsg.life.pw.util.IOTokenizer;
import com.csc.fsg.life.pw.log.PWServerLogManager;

/* Modifications: T0103, T0091 */

/**
 * A helper class to process the v10-c2s-table.txt format input stream. This
 * class uses C2sTableParser to parse data rows of the stream. NOTE: It does not
 * currently support direct WIP Updates from the Client
 * 
 * @author Ramgopal Madarapu
 */

public class C2sTableProcessor {

	private static Log log = PWServerLogManager.getLog(C2sTableProcessor.class
	        .getName());

	private boolean debug = false;

	// TableDecriptor line in the stream
	private String attrMask = null;

	private int numberOfColumns = 0;

	private String ddlName = null;

	private String tableId = null;

	// WIP Descriptor line in the stream
	private String envSchema = null;

	private String companyCode = null;

	private String subsetName = null;

	private String projectName = null;

	private String userId = null;

	/**
	 * Constructor C2sTableProcessor
	 */

	public C2sTableProcessor() {

		debug = log.isDebugEnabled();
		if (debug) {
			log.debug("Creating C2sTableProcessor");
		}
	}

	/**
	 * Method process
	 * 
	 * @param c2stable
	 * @param wipRows
	 * @throws ColumnSizeException
	 * @throws IOException
	 * @throws InvalidColumnCountException
	 * @throws PWIOException
	 * @throws TableIdNotFoundException
	 */

	public void process(BufferedReader c2stable, IWIPRows wipRows)
	        throws PWIOException, IOException, ColumnSizeException, Exception {

		WIPRow wipRow = null;
		String line = null;
		int lineCount = 0;
		ArrayList<WIPRow> deleteOperRows = new ArrayList<WIPRow>();
		ArrayList<WIPRow> updateOperRows = new ArrayList<WIPRow>();
		ArrayList<WIPRow> insertOperRows = new ArrayList<WIPRow>();

		while ((line = c2stable.readLine()) != null) {
			if ((line.length() != 0) && !line.substring(0, 1).equals("#")) {
				if (lineCount == 0) {
					processTableDescriptorLine(line);
				} else if (lineCount == 1) {
					processWIPDescriptorLine(line);
				} else {
					wipRow = processDataRow(line);

					int attr_mask = wipRow.getWipActionAttrMask();
					boolean insert = (attr_mask & Constants.TABLE_ATTR_INSERT) != 0;
					boolean update = (attr_mask & Constants.TABLE_ATTR_UPDATE) != 0;
					boolean delete = (attr_mask & Constants.TABLE_ATTR_DELETE) != 0;
					if (delete)
						deleteOperRows.add(wipRow);
					else if (update)
						updateOperRows.add(wipRow);
					else
						insertOperRows.add(wipRow);
					
//					if (wipRow.getOperation().equalsIgnoreCase(
//					        Constants.DELETE_OPERATION)) {
//						deleteOperRows.add(wipRow);
//					} else if (wipRow.getOperation().equalsIgnoreCase(
//					        Constants.UPDATE_OPERATION)) {
//						boolean wipDelete = (wipRow.getWipActionAttrMask() & Constants.TABLE_ATTR_DELETE) != 0;
//						if (wipDelete)
//							deleteOperRows.add(wipRow);
//						else
//							updateOperRows.add(wipRow);
//
//					} else if (wipRow.getOperation().equalsIgnoreCase(
//					        Constants.INSERT_OPERATION)) {
//						insertOperRows.add(wipRow);
//					} else {
//						deleteOperRows.add(wipRow);
//					}

				}
				lineCount++;
			}
		}

		for (int i = 0; i < deleteOperRows.size(); i++)
			wipRows.add(deleteOperRows.get(i));

		for (int i = 0; i < updateOperRows.size(); i++)
			wipRows.add(updateOperRows.get(i));

		for (int i = 0; i < insertOperRows.size(); i++)
			wipRows.add(insertOperRows.get(i));

		if (debug) {
			log.debug("Completed processing the buffered reader...");
		}
	}

	private void processTableDescriptorLine(String line) throws PWIOException {

		try {
			// Should contain following fields in the same order
			// ATTR_MASK, NUMBER_OF_COLUMNS, DDLNAME, TABLE_ID
			IOTokenizer fLine = new IOTokenizer(line, "\t");

			attrMask = fLine.nextToken();
			numberOfColumns = (new Integer(fLine.nextToken())).intValue();
			ddlName = fLine.nextToken();
			tableId = fLine.nextToken();
			if (debug) {
				log.debug("Table Descriptor -" + " AttrMask: " + attrMask
				        + " Num Of Columns: " + numberOfColumns + " DdlName: "
				        + ddlName + " TableID: " + tableId);
			}
		} catch (Exception e) {
			throw new PWIOException(PWIOException.INVALID_TABLE_DESCRIPTOR_LINE);
		}
	}

	private void processWIPDescriptorLine(String line) throws PWIOException {

		try {
			// Should contain following fields in the same order
			// ENV_SCHEMA, COMPANY_CODE, TABLE_SUBSET, PROJECT_NAME, USER_ID,
			// PRODUCT_PREFIX
			IOTokenizer fLine = new IOTokenizer(line, "\t");

			envSchema = fLine.nextToken();
			companyCode = fLine.nextToken();
			subsetName = fLine.nextToken();
			projectName = fLine.nextToken();

			if (projectName.trim().length() == 0) {
				throw new Exception("Blank project name");
			}
			userId = fLine.nextToken();

			if (debug) {
				log.debug("WIP Descriptor - " + "EnvSchema: " + envSchema
				        + " Comp Code: " + companyCode + " SubsetName: "
				        + subsetName + " ProjectName: " + projectName
				        + " UserID: " + userId);
			}
			// + " ProductPrefix: " + productPrefix);
		} catch (Exception e) {
			throw new PWIOException(PWIOException.INVALID_WIP_DESCRIPTOR_LINE);
		}
	}

	private WIPRow processDataRow(String line) throws PWIOException,
	        ColumnSizeException, Exception {

		// either tableId/ddlName
		if (ddlName.equals(Constants.TABLE_ZERO_NAME)) {
			PlanWIPRow pWipRow = (PlanWIPRow) WIPRowFactory
			        .createWIPRowOfType(Constants.PLAN_WIP);
			C2sTableParser c2s = new C2sTableParser(numberOfColumns, line,
													Constants.PLAN_WIP,
													tableId, envSchema);
			// refactor plan key.
			pWipRow.setDataNew(c2s.getPlanRow());
			/*
			pWipRow.setEffDate(c2s.getEffDate());
			pWipRow.setIssueState(c2s.getIssueState());
			pWipRow.setLob(c2s.getLob());
			pWipRow.setPlanCode(c2s.getPlanCode());
			pWipRow.setProductSuffix(c2s.getProductSuffix());
			pWipRow.setTablePtrId(c2s.getTablePtrId());
			pWipRow.setTablePtrSubset(c2s.getTablePtrSubset());
			pWipRow.setTablePtrVar(c2s.getTablePtrVar());
			*/
			setDefaultValues(pWipRow, line, Constants.PLAN_WIP, c2s);
			return pWipRow;

		} else if (ddlName.equals(Constants.SUBSET_TABLE_NAME)) {
			IndexWIPRow iWipRow = (IndexWIPRow) WIPRowFactory
			        .createWIPRowOfType(Constants.INDEX_WIP);
			C2sTableParser c2s = new C2sTableParser(numberOfColumns, line,
			        Constants.INDEX_WIP, tableId, envSchema);

			iWipRow.setChildTableId(c2s.getSecondaryTableId());
			iWipRow.setChildTableSubset(c2s.getSecondaryTableSubset());
			iWipRow.setChildTableVar(c2s.getSecondaryTableVar());
			iWipRow.setParentTableId(c2s.getTablePtrId());
			iWipRow.setParentTableSubset(c2s.getTablePtrSubset());
			setDefaultValues(iWipRow, line, Constants.INDEX_WIP, c2s);
			return iWipRow;
		} else {
			CommonWIPRow cWipRow = (CommonWIPRow) WIPRowFactory
			        .createWIPRowOfType(Constants.COMMON_WIP);
			C2sTableParser c2s = new C2sTableParser(numberOfColumns, line,
			        Constants.COMMON_WIP, tableId, envSchema);

			setDefaultValues(cWipRow, line, Constants.COMMON_WIP, c2s);
			return cWipRow;
		}
	}

	private void setDefaultValues(WIPRow wipRow, String line, int wipType,
	        C2sTableParser c2s) throws Exception,
	        com.csc.fsg.life.pw.io.ColumnSizeException, Exception {

		wipRow.setAttrMask(attrMask);
		wipRow.setNumberOfColumns(numberOfColumns);

		wipRow.setEnvironment(envSchema);
		wipRow.setCompanyCode(companyCode);
		wipRow.setDdlName(ddlName);
		// Fiona - SPR 1985 - 11th April
		// wipRow.setSubsetName(subsetName);
		wipRow.setTableId(tableId);
		wipRow.setProjectName(projectName);
		wipRow.setChangeUserId(userId);

		// other lines: WIPACTION TARGET_OPERATION ROWINDEX DATACOLUMNS
		// CHNAGED_COLUMNS OLD_CONCAT_KEY
		// C2sTableParser c2s = new C2sTableParser(numberOfColumns, line ,
		// wipType );

		int wipActionMask = c2s.getwipActionAttrMask();
		String operation = c2s.getOperation();
		String newKeyNData = c2s.getNewData();

		wipRow.setWipActionAttrMask(wipActionMask);
		wipRow.setOperation(operation);
		wipRow.setRowIndex(c2s.getRowIndex());
		// wipRow.setNewData(newData);
		wipRow.setChangedColumns(c2s.getChangedColumns());
		wipRow.setProductPrefix(c2s.getProductPrefix());

		RowManager rowManager = new RowManager(envSchema);
		Row row = rowManager.getBlankRow(tableId);

		row.setColumns(newKeyNData, Constants.STAB);

		if (!row.getTableSubset().equals("BASECLASSNULLDATA")) {
			wipRow.setSubsetName(row.getTableSubset());
		}

		String newConcatKey = row.buildKey();
		wipRow.setNewConcatKey(newConcatKey);

		String newData = null;
		if (wipType == Constants.PLAN_WIP || wipType == Constants.INDEX_WIP)
			newData = ""; //newdata or old data is always "" for plan and index tables.
		else
			newData = row.buildData();

		wipRow.setNewData(newData);

		String oldConcatKey = null;
		String oldData = null;

		if (isInsert(wipActionMask)
		        && operation.equalsIgnoreCase(Constants.INSERT_OPERATION)) {
			oldConcatKey = newConcatKey;
			oldData = newData;
		} else {
			oldConcatKey = c2s.getOldConcatKey();
			oldData = c2s.getOldData();

			if (oldConcatKey == null) {
				throw new PWIOException("OLD CONCAT KEY MISSING");
			}
		}

		wipRow.setOldConcatKey(oldConcatKey);
		wipRow.setOldData(oldData);

		ArrayList<String> originalData = c2s.getOriginalData();

		if (hasOriginalData(wipActionMask)) {
			if ((originalData == null) || (originalData.size() == 0)) {
				throw new PWIOException(PWIOException.ORIGINAL_DATA_MISSING);
			}

			wipRow.setOriginalData(originalData);
		}

		if (debug) {
			log.debug(wipRow.toString());
		}
	}

	private boolean isInsert(int attr_mask) {

		if ((attr_mask & Constants.TABLE_ATTR_INSERT) != 0) {
			return true;
		} else {
			return false;
		}
	}

	private boolean hasOriginalData(int attr_mask) {

		if ((attr_mask & Constants.TABLE_ATTR_ORIGINFO) != 0) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * Method getEnvSchema
	 * 
	 * @return
	 */

	public String getEnvSchema() {
		return envSchema;
	}

	/**
	 * Method getDDLName
	 * 
	 * @return
	 */

	public String getDDLName() {
		return ddlName;
	}

	/**
	 * Method getNumberOfColumns
	 * 
	 * @return
	 */

	public int getNumberOfColumns() {
		return numberOfColumns;
	}
}
