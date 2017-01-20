/*
 * THIS PROGRAM IS THE PROPERTY OF CSC FINANCIAL SERVICES GROUP. IT MAY NOT BE
 * COPIED IN WHOLE OR IN PART WITHOUT THE EXPRESS WRITTEN CONSENT OF CSC
 * FINANCIAL SERVICES GROUP.
 */

package com.csc.fsg.life.pw.io;

import java.util.*;

import com.csc.fsg.life.pw.transferobjects.PlanRowTO;
import com.csc.fsg.life.pw.util.*;
import com.csc.fsg.life.pw.environment.EnvironmentManager;


/* Modifications: T0103, T0091, T0129, WMA-2015 */

/**
 * Class C2sTableParser
 * 
 * @author CSC-FSG,E.Hartford
 * @version PW 2.0 , 09-24-2002
 */

public class C2sTableParser extends IOTokenizer {

	private String productPrefix;

	private int numberOfColumns = 0;

	private int wipActionAttrMask;

	private String operation = null;

	private String rowIndex = null;

	private String newData = null;

	private String changedColumns = null;

	private String oldConcatKey = null;

	private String oldData = null;

	private ArrayList<String> originalData = null;
	// refactor plan key.
	private PlanRowTO planRow = null;
	/*
	private String productSuffix = null;

	// new fields for PlanWIP (T000X rows) and IndexWIP (T000XA rows)
	private String planCode = null;

	private String effDate = null;

	private String issueState = null;

	private String lob = null;

	private String planType = null;
	*/
	
	private String tablePtrId = null;

	private String tablePtrSubset = null;

	private String secTableId = null;

	private String secTableVar = null;

	private String secTableSubset = null;

	/**
	 * Constructor C2sTableParser
	 * 
	 * @param columns
	 * @param input
	 * @param wipType
	 * @param tableId
	 * @param envSchema
	 * @throws PWIOException
	 */

	public C2sTableParser(int columns, String input, int wipType,
	        String tableId, String envSchema) throws Exception {

		super(input, "\t");
		numberOfColumns = columns;
		initialize(wipType, tableId, envSchema);
	}

	private void initialize(int wipType, String tableId, String envSchema) throws Exception{

		StringBuffer sb = new StringBuffer();

		
		String data = "";

		wipActionAttrMask = new Integer(nextToken()).intValue();
		operation = nextToken();
		rowIndex = nextToken();
		productPrefix = nextToken();

		if (wipType == Constants.PLAN_WIP) {
			processPlanWipRow(sb, tableId, envSchema);
		} else if (wipType == Constants.INDEX_WIP) {
			processIndexWipRow(sb, tableId, envSchema);
		} else if (wipType == Constants.COMMON_WIP) {
			processCommonWipRow(sb, tableId, envSchema);
		}
		// sb.setLength(sb.length() - delim.length());
		newData = sb.toString();

		changedColumns = nextToken();

		if (hasMoreTokens()) {
			String pipeDelimKey = nextToken();

			if (pipeDelimKey.indexOf("|") != -1) {
				oldConcatKey = getPaddedKey(pipeDelimKey, tableId,
				        envSchema);
			} else {
				oldConcatKey = pipeDelimKey;
			}
			// oldConcatKey = nextToken();
		}

		if (hasMoreTokens()) {
			String pipeDelimData = nextToken();
			if (pipeDelimData.indexOf("|") != -1 ) {
				oldData = getPaddedData(pipeDelimData, "|", tableId, envSchema);
			}else if (pipeDelimData.indexOf("^") != -1 ) {
				oldData = getPaddedData(pipeDelimData, "^", tableId, envSchema);
			}else {
				if (isPaddingOfPipeDelimDataNeeded(envSchema, tableId, pipeDelimData))
					oldData = getPaddedData(pipeDelimData, "|", tableId, envSchema);
				else
					oldData = pipeDelimData;
			}
		}

		originalData = new ArrayList<String>();

		while (hasMoreTokens()) {
			data = nextToken();
			originalData.add(data);
		}
		
	}

	/**
	 * WMA-2015: This method returns <code>true</code> to indicate that the data
	 * provided in <code>pipeDelimData</code> needs to be padded, if these
	 * conditions are true:
	 * <ol>
	 * <li>The corresponding table has only one data column</li>
	 * <li>The data type of this column is DECIMAL</li>
	 * <li>The column value has not been already padded, as evidenced by absence
	 *     of the numeric sign character</li>
	 * </ol>
	 * 
	 * At the time the solution has been developed for WMA-2015, the only table
	 * known to cause the problem is TW68X1, when importing business rule data
	 * with action of REPLACE or DELETE.
	 */
	private boolean isPaddingOfPipeDelimDataNeeded(String envId, String tableId, String pipeDelimData)
	{
		int dataColumnCount = 0;
		int columnDataType = -1;
		List<ColumnDescriptor> columnDescriptors = getColumnDescriptors(envId, tableId);
		for (ColumnDescriptor descriptor : columnDescriptors) {
			if (!descriptor.isKey()) {
				dataColumnCount++;
				columnDataType = descriptor.getDataType();
			}
		}

		// columnDataType value of 3 indicates DECIMAL type
		if (dataColumnCount > 1 || columnDataType != 3) {
			return false;
		}
		else {
			boolean hasNegSign = pipeDelimData.indexOf("-") >= 0;
			boolean hasPosSign = pipeDelimData.indexOf("+") >= 0;
			return !hasNegSign && !hasPosSign;
		}
	}

	private void processCommonWipRow(StringBuffer sb, String tableId,
	        String envId) throws Exception{

		Vector<ColumnDescriptor> cds = getColumnDescriptors(envId,tableId);
		StringBuffer returnValue = null;

	
			if (cds.size() != numberOfColumns) {
				throw new PWIOException("No of Columns are not matched ");
			}

			for (int count = 0; count < numberOfColumns; count++) {
				ColumnDescriptor cd = cds.get(count);
				String columnValue = nextToken();

				returnValue = new StringBuffer();

				int originalColumnLength = cd.getColumnSize();
				int apendType = UtilMethods.getAppendType(cd
				        .getDataType());

				// rpy start 08/12/2002 11:25:39 AM spr 2436
				if (cd.getDbColumnName().indexOf("FUND_NUMBER") != -1) {
					UtilMethods.appendLeadingZeros(returnValue, Utils
					        .safeTrim(columnValue), originalColumnLength);
					// rpy end 08/12/2002 11:25:39 AM spr 2436
				} else if (apendType == UtilMethods.APPEND_ZEROS) {
					UtilMethods.appendLeadingZeros(returnValue, columnValue,
					        originalColumnLength);
				} else {
					UtilMethods.appendTrailingSpaces(returnValue, columnValue,
					        originalColumnLength);
				}
				sb.append(returnValue.toString());
				returnValue = null;
			}

	
	}

	private void processPlanWipRow(StringBuffer sb, String tableId,
	        String envId) throws Exception{

		Vector<ColumnDescriptor> cds = getColumnDescriptors(envId,tableId);
		StringBuffer returnValue = null;
		String value = null;

	
			if (cds.size() != numberOfColumns) {
				throw new PWIOException("No of Columns are not matched ");
			}
			StringBuffer planRowKeyBuffer = new StringBuffer();
			for (int count = 0; count < numberOfColumns; count++) {
				ColumnDescriptor cd = cds.get(count);
				String columnValue = nextToken();

				returnValue = new StringBuffer();

				int originalColumnLength = cd.getColumnSize();
				int apendType = UtilMethods.getAppendType(cd
				        .getDataType());

				if (apendType == UtilMethods.APPEND_ZEROS) {
					UtilMethods.appendLeadingZeros(returnValue, columnValue,
					        originalColumnLength);
				} else {
					UtilMethods.appendTrailingSpaces(returnValue, columnValue,
					        originalColumnLength);
				}

				value = returnValue.toString();
				sb.append(value);
				returnValue = null;
				// refactor plan key.
				planRowKeyBuffer.append(value + "|");
				/*
				switch (count) {

					case 2:
						productSuffix = value;
						break;
					case 3:
						planCode = value;
						break;
					case 4:
						issueState = value;
						break;
					case 5:
						lob = value;
						break;
					case 6:
						effDate = value;
						break;
					case 7:
						planType = value;
						break;
					case 8:
						tablePtrId = value;
						break;
					case 9:
						tablePtrVar = value;
						break;
					case 10:
						tablePtrSubset = value;
						break;
				}
				*/
			}
			planRow = new PlanRowTO(planRowKeyBuffer.toString(), "|");
			planRow.setEnvironment(envId);
	
	}

	private void processIndexWipRow(StringBuffer sb, String tableId,
	        String envId) throws PWIOException,Exception{

		Vector<ColumnDescriptor> cds = getColumnDescriptors(envId,tableId);
		StringBuffer returnValue = null;
		String value = null;

		
			if (cds.size() != numberOfColumns) {
				throw new PWIOException("No of Columns are not matched ");
			}

			for (int count = 0; count < numberOfColumns; count++) {
				ColumnDescriptor cd = cds.get(count);
				String columnValue = nextToken();

				returnValue = new StringBuffer();

				int originalColumnLength = cd.getColumnSize();
				int apendType = UtilMethods.getAppendType(cd
				        .getDataType());

				if (apendType == UtilMethods.APPEND_ZEROS) {
					UtilMethods.appendLeadingZeros(returnValue, columnValue,
					        originalColumnLength);
				} else {
					UtilMethods.appendTrailingSpaces(returnValue, columnValue,
					        originalColumnLength);
				}

				value = returnValue.toString();
				sb.append(value);
				returnValue = null;
				switch (count) {

					case 2:
						tablePtrId = value;
						break;
					case 3:
						tablePtrSubset = value;
						break;
					case 4:
						secTableId = value;
						break;
					case 5:
						secTableSubset = value;
						break;
					case 6:
						secTableVar = value;
						break;
				}
			}
	
	}

	/**
	 * Method getwipActionAttrMask
	 * 
	 * @return
	 */

	public int getwipActionAttrMask() {
		return wipActionAttrMask;
	}

	/**
	 * Method getOperation
	 * 
	 * @return
	 */

	public String getOperation() {
		return operation;
	}

	/**
	 * Method getRowIndex
	 * 
	 * @return
	 */

	public String getRowIndex() {
		return rowIndex;
	}

	/**
	 * Method getNewData
	 * 
	 * @return
	 */

	public String getNewData() {
		return newData;
	}

	/**
	 * Method getChangedColumns
	 * 
	 * @return
	 */

	public String getChangedColumns() {
		return changedColumns;
	}

	/**
	 * Method getOldConcatKey
	 * 
	 * @return
	 */

	public String getOldConcatKey() {
		return oldConcatKey;
	}

	public String getOldData() {
		return oldData;
	}

	/**
	 * Method getOriginalData
	 * 
	 * @return
	 */

	public ArrayList<String> getOriginalData() {
		return originalData;
	}

	/**
	 * Method getProductPrefix
	 * 
	 * @return
	 */

	public String getProductPrefix() {
		return productPrefix;
	}

	// refactor plan key.
	/*
	public String getProductSuffix() {
		return productSuffix;
	}
	public String getPlanCode() {
		return planCode;
	}
	public String getEffDate() {
		return effDate;
	}
	public String getIssueState() {
		return issueState;
	}
	public String getLob() {
		return lob;
	}
	public String getPlanType() {
		return planType;
	}
	*/

	/**
	 * Method getTablePtrId
	 * 
	 * @return
	 */

	public String getTablePtrId() {
		return tablePtrId;
	}

	/**
	 * Method getTablePtrSubset
	 * 
	 * @return
	 */

	public String getTablePtrSubset() {
		return tablePtrSubset;
	}

	/**
	 * Method getSecondaryTableId
	 * 
	 * @return
	 */

	public String getSecondaryTableId() {
		return secTableId;
	}

	/**
	 * Method getSecondaryTableSubset
	 * 
	 * @return
	 */

	public String getSecondaryTableSubset() {
		return secTableSubset;
	}

	/**
	 * Method getSecondaryTableVar
	 * 
	 * @return
	 */

	public String getSecondaryTableVar() {
		return secTableVar;
	}

	public static Vector<ColumnDescriptor> getColumnDescriptors(String envId,String tableId) {

			
		TableDescriptor td = EnvironmentManager.getInstance().getEnvironment(envId).getTableDescMgr()
		        .getTableDescriptor(tableId);

		return td.getColumnDescriptors();
	}

	public static String getPaddedKey(String pipeDelimKey, String tableId,
	        String envId) throws Exception{

		IOTokenizer iot = new IOTokenizer(pipeDelimKey, "|");

		String s = null;
		StringBuffer sb = null;
		StringBuffer returnValue = null;
		String columnValue = null;

	
			sb = new StringBuffer();
			Vector<ColumnDescriptor> columndescriptors = getColumnDescriptors(envId,tableId);
			int size = columndescriptors.size();

			for (int count = 0; count < size; count++) {
				ColumnDescriptor cd = columndescriptors
				        .get(count);

				if (cd.isKey()) {
					columnValue = iot.nextToken();
					returnValue = new StringBuffer();
					int originalColumnLength = cd.getColumnSize();
					int apendType = UtilMethods.getAppendType(cd
					        .getDataType());

					if (apendType == UtilMethods.APPEND_ZEROS) {
						UtilMethods.appendLeadingZeros(returnValue,
						        columnValue, originalColumnLength);
					} else {
						UtilMethods.appendTrailingSpaces(returnValue,
						        columnValue, originalColumnLength);
					}

					sb.append(returnValue.toString());
					returnValue = null;
				}
				// else
				// break;
			}
			s = sb.toString();
		
		return s;
	}

	public static String getPaddedData(String pipeDelimData,String delim, String tableId,
	        String envId) throws Exception{

		IOTokenizer iot = new IOTokenizer(pipeDelimData, delim);
		String s = null;
		StringBuffer sb = null;
		StringBuffer returnValue = null;
		String columnValue = null;		
	
			sb = new StringBuffer();
			Vector<ColumnDescriptor> columndescriptors = getColumnDescriptors(envId,tableId);
			int size = columndescriptors.size();
			for (int count = 0; count < size; count++) {
				ColumnDescriptor cd = columndescriptors
				        .get(count);
				if (!cd.isKey()) {
					columnValue = iot.nextToken();			
					returnValue = new StringBuffer();
					int originalColumnLength = cd.getColumnSize();
					int apendType = UtilMethods.getAppendType(cd
					        .getDataType());
					if (apendType == UtilMethods.APPEND_ZEROS) {
						UtilMethods.appendLeadingZeros(returnValue,
						        columnValue, originalColumnLength);
					} else {
						UtilMethods.appendTrailingSpaces(returnValue,
						        columnValue, originalColumnLength);
					}
					sb.append(returnValue.toString());
					returnValue = null;
				}
			}
			s = sb.toString();
		
		return s;
	}

	public PlanRowTO getPlanRow() {
		return planRow;
	}

	public void setPlanRow(PlanRowTO planRow) {
		this.planRow = planRow;
	}
	
}
