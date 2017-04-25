/*
 * THIS PROGRAM IS THE PROPERTY OF CSC FINANCIAL SERVICES GROUP. IT MAY NOT BE
 * COPIED IN WHOLE OR IN PART WITHOUT THE EXPRESS WRITTEN CONSENT OF CSC
 * FINANCIAL SERVICES GROUP.
 */

package com.csc.fsg.life.pw.web.io;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Vector;

import com.csc.fsg.life.exceptions.WrapperException;
import com.csc.fsg.life.pw.common.transferobjects.PlanCriteriaTO;
import com.csc.fsg.life.pw.common.transferobjects.PlanRowTO;
import com.csc.fsg.life.pw.common.util.*;
import com.csc.fsg.life.pw.web.actions.tree.IndexMergeAssistent;
import com.csc.fsg.life.pw.web.actions.tree.PlanMergeAssistent;
import com.csc.fsg.life.pw.web.controller.IResponse;
import com.csc.fsg.life.pw.web.environment.Environment;
import com.csc.fsg.life.pw.web.environment.EnvironmentManager;
import com.csc.fsg.life.pw.web.utils.DBConnMgr;
import com.csc.fsg.life.pw.web.utils.sql.SQLBuilderMERGEDX;

/* Modifications: T0103, T0091, T0115 */

/**
 * Class PlanTableStream
 * 
 * @author CSC-FSG,E.Hartford
 * @version PW 2.0 , 09-24-2002
 */

public class PlanTableStream {

	private Environment environment = null;

	private boolean loadNP = false;

	private WIPProperties wipProps = null;

	private IResponse out;

	private PlanCriteriaTO planCriteria;
	
	public PlanTableStream(PlanCriteriaTO planCriteria) {
		Environment environment = EnvironmentManager.getInstance().getEnvironment(planCriteria.getEnvironment());
		this.environment = environment;
		this.planCriteria = planCriteria;
		wipProps = WIPProperties.getInstance();
		
	}
	
	public PlanTableStream(PlanCriteriaTO planCriteria, IResponse out) {
		this(planCriteria);
		this.out = out;
	}
	
	/**
	 * Constructor PlanTableStream
	 * 
	 * @param schemaName
	 */

	public PlanTableStream(Environment environment) {

		this.environment = environment;
		try {
		
			wipProps = WIPProperties.getInstance();
		} catch (Exception e) {
			throw WrapperException.wrap(e);
		}
	}

	/**
	 * Constructor PlanTableStream
	 * 
	 * @param schemaName
	 * @param out
	 */

	/* @deprecated use PlanTableStream(PlanCriteriaTO, IResponse)
	public PlanTableStream(IResponse out,Environment environment) {
		this.out = out;
		this.environment = environment;
		try {
			wipProps = WIPProperties.getInstance();
		} catch (Exception e) {
			throw WrapperException.wrap(e);
		}
	}
	*/
	
	public void getPlanTableData(int userAuthority) throws Exception {
		ArrayList<Object> mergedRows = mergePlanTable();
		toStream(mergedRows, Constants.TABLE_ZERO_ID, userAuthority,
				planCriteria.getCompanyCode(), planCriteria.getProductPrefix());
	}

	/**
	 * Method getPlanTableDataWithMerging
	 * 
	 * @param treeKey
	 * @param userAuthority
	 * @throws Exception
	 */

	// refacotr plan key.
	/* @deprecated use getPlanTableData(int)
	public void getPlanTableDataWithMerging(HashMap treeKey, int userAuthority)
	        throws Exception {

		ArrayList mergedRows = mergePlanTable(treeKey, "with");
		String companyCode = (String) treeKey.get("company_code");
		String productPrefix = (String) treeKey.get("product_prefix");

		toStream(mergedRows, Constants.TABLE_ZERO_ID, userAuthority,
		        companyCode, productPrefix);
	}
	public void getPlanTableDataWithoutMerging(HashMap treeKey,
	        int userAuthority) throws Exception {

		ArrayList rows = mergePlanTable(treeKey, "without");
		String company = (String) treeKey.get("company_code");
		String productPrefix = (String) treeKey.get("product_prefix");

		toStream(rows, Constants.TABLE_ZERO_ID, userAuthority, company,
		        productPrefix);
	}
	*/


	// refactor plan key.
	/* @deprecated use mergePlanTable()
	public ArrayList getPlanTableRowsWithMerging(String planKey)
	        throws Exception {
		return mergePlanTable(planKey, "with");
	}
	public ArrayList getPlanTableRowsWithMerging(HashMap treeKey)
	        throws Exception {
		return mergePlanTable(treeKey, "with");
	}
	public ArrayList getPlanTableRowsWithOutMerging(HashMap treeKey)
	        throws Exception {
		return mergePlanTable(treeKey, "without");
	}
	*/

	/**
	 * Method getIndexTableRowsWithMerging
	 * 
	 * @param treeKey
	 * @return
	 * @throws Exception
	 */

	public ArrayList<Object> getIndexTableRowsWithMerging(HashMap treeKey)
	        throws Exception {
		return mergeIndexTable(treeKey, "with");
	}

	/**
	 * Method getIndexTableRowsWithOutMerging
	 * 
	 * @param treeKey
	 * @return
	 * @throws Exception
	 */

	public ArrayList<Object> getIndexTableRowsWithOutMerging(HashMap treeKey)
	        throws Exception {
		return mergeIndexTable(treeKey, "without");
	}

	private void toStream(ArrayList<Object> rows, String tableId, int userAuthority,
	        String companyCode, String productPrefix) throws Exception {

		try {
			
			TableDescriptor td = environment.getTableDescMgr().getTableDescriptor(tableId);
			Vector<String> companies = new Vector<String>();

			companies.addElement(companyCode);
			SourceTableFilter filter = new SourceTableFilter();

			filter.setEnvSchema(environment.getId());
			filter.setTableName(td.getTableName());
			filter.setCompanies(companies);
			filter.setProductPrefix(productPrefix);

			UtilMethods.writeDescriptorsToStream(td, userAuthority, filter,
			        out, false, false, null, null, null, false);

			Object obj = null;
			Iterator<Object> iterator = rows.iterator();

			while (iterator.hasNext()) {
				obj = iterator.next();
				if (obj instanceof Row) {
					UtilMethods
					        .writeRowDataToStream(td, (Row) obj, out, environment.getId());
				} else {
					UtilMethods.writeWipDataToStream((WIPRow) obj, out);
				}
			}
		} catch (Exception e) {
			throw e;
		}
	}

	// refactor plan key.
	/* @deprecated use mergePlanTable()
	private ArrayList mergePlanTable(String planKey, String view)
	        throws Exception {

		HashMap treeKey = new HashMap(9);

		fillKey(environment.getId(), planKey, treeKey);
		return mergePlanTable(treeKey, view);
	}
	*/
	
	public ArrayList<Object> mergePlanTable() throws Exception {
		return mergePlanTable(null);
	}
	
	public ArrayList<Object> mergePlanTable(String userCondition) throws Exception {
		ArrayList<Object> mRows = null;
		Connection conn = null;
		ResultSet rs = null;
		Statement stmt = null;
		PlanMergeAssistent pma = null;

		try {
			conn = DBConnMgr.getInstance().getConnection(environment.getId(),DBConnMgr.APPL);
			pma = new PlanMergeAssistent(conn, planCriteria);
			stmt = conn.createStatement();
			String sql = new SQLBuilderMERGEDX(planCriteria.getEnvironment()).buildSelectTableStatement(userCondition);
			rs = stmt.executeQuery(sql.toString());
			mRows = new ArrayList<Object>(100);
			toPlanArrayList(rs, mRows);
	
		} finally {
			
			Utils.closeResultSet(rs);
			Utils.closeStatement(stmt);

			try{
			if (pma != null) 
				pma.clean(conn);
			}finally{
				DBConnMgr.getInstance().releaseConnection(conn);	
			}
		}
		return mRows;
	}

	// refactor plan key.
	/* @deprecated use mergePlanTable()
	public ArrayList mergePlanTable(HashMap treeKey, String view)
	        throws Exception {

		ArrayList mRows = null;
		Connection conn = null;
		ResultSet rs = null;
		Statement stmt = null;
		PlanMergeAssistent pma = null;
		        
		StringBuffer sql = new StringBuffer(512);

		try {
			conn = DBConnMgr.getInstance().getConnection(environment.getId(),DBConnMgr.APPL);
			pma = new PlanMergeAssistent(conn, treeKey, loadNP, view);
			stmt = conn.createStatement();
			buildSelectSQL(sql, Constants.TABLE_ZERO_ID);
			rs = stmt.executeQuery(sql.toString());
			mRows = new ArrayList(100);
			toPlanArrayList(rs, mRows);
		} catch (Exception e) {
			throw WrapperException.wrap(e);
		} finally {

			Utils.closeResultSet(rs);
			Utils.closeStatement(stmt);

			try {
				if (pma != null)
					pma.clean(conn);
			} catch (Exception e) {
				throw WrapperException.wrap(e);
			} finally {
				DBConnMgr.getInstance().releaseConnection(conn);
			}
		}
		return mRows;
	}
	*/

	private ArrayList<Object> mergeIndexTable(HashMap treeKey, String view)
	        throws Exception {

		ArrayList<Object> mRows = null;
		Connection conn = null;
		ResultSet rs = null;
		Statement stmt = null;
		IndexMergeAssistent ima = null;
		StringBuffer sql = new StringBuffer(512);

		try {
			conn = DBConnMgr.getInstance().getConnection(environment.getId(),DBConnMgr.APPL);
			ima = new IndexMergeAssistent(conn, treeKey,loadNP, view);
			String userCondition = (String) treeKey.get("userCondition");
			buildSelectSQL(sql, Constants.SUBSET_TABLE_ID, userCondition);
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql.toString());
			mRows = new ArrayList<Object>(100);
			toIndexArrayList(rs, mRows);
	
		} finally {

			Utils.closeResultSet(rs);
			Utils.closeStatement(stmt);

			try {
				if (ima != null)
					ima.clean(conn);
		
			} finally {
				DBConnMgr.getInstance().releaseConnection(conn);
			}
		}
		return mRows;
	}

	private void buildSelectSQL(StringBuffer sql, String tableId, String userCondition) {

		Vector<ColumnDescriptor> columns = environment.getTableDescMgr()
		        .getTableDescriptor(tableId).getColumnDescriptors();
		ColumnDescriptor cd = null;
		String tempTableName = null;

		if (tableId.equalsIgnoreCase(Constants.SUBSET_TABLE_ID)) {
			tempTableName = "MERGEDXA";
		} else {
			tempTableName = "MERGEDX";
		}

		sql.append("SELECT * FROM SESSION." + tempTableName);
		if ( userCondition != null && userCondition.length() > 0 )
			sql.append(" WHERE ( ").append(userCondition).append(" )");
		sql.append(" ORDER BY ");
		for (int i = 0; i < (columns.size() - 1); i++) {
			cd = columns.get(i);
			sql.append(cd.getDbColumnName() + ",");
		}
		sql.append(cd.getDbColumnName());
	}

	/**
	 * Method fillKey
	 * 
	 * @param envSchema
	 * @param planKey
	 * @param treeKey
	 */

	// refactor plan key.
	/* @deprecated use PlanCriteriaTO
	public static void fillKey(String envSchema, String planKey, HashMap treeKey) {

		IOTokenizer it = new IOTokenizer(planKey, "|");

		treeKey.put("schema", envSchema);
		String company_code = it.nextToken();
		String product_prefix = it.nextToken();
		String product_suffix = it.nextToken();
		String plan_code = it.nextToken();
		String issue_state = it.nextToken();
		String line_of_business = it.nextToken();
		String effective_date = it.nextToken();
		String plan_type = it.nextToken();

		treeKey.put("company_code", company_code);
		treeKey.put("product_prefix", product_prefix);
		treeKey.put("product_suffix", product_suffix);
		treeKey.put("plan_code", plan_code);
		treeKey.put("issue_state", issue_state);
		treeKey.put("line_of_business", line_of_business);
		treeKey.put("effective_date", effective_date);
		treeKey.put("plan_type", plan_type);
	}
	*/

	private void toPlanArrayList(ResultSet rs, ArrayList<Object> mRows)
	        throws Exception {

		if (rs != null) {
			PlanRow planRow = null;
			PlanWIPRow planWIPRow = null;
			StringBuffer sb = null;

			while (rs.next()) {
				sb = new StringBuffer();
				String project_name = rs.getString(wipProps.getProjectName())
				        .trim();

				if (!project_name.equals("")) {
					planWIPRow = (PlanWIPRow) WIPRowFactory
					        .createWIPRowOfType(Constants.PLAN_WIP);
					planWIPRow.setEnvironment(environment.getId());
					planWIPRow.setTableId(Constants.TABLE_ZERO_ID);
					planWIPRow.setDdlName(Constants.TABLE_ZERO_NAME);
					// refactor plan key.
					planWIPRow.setDataNew(new PlanRowTO(rs));
					/*
					planWIPRow.setCompanyCode(rs.getString(wipProps
					        .getCompanyCode()));
					planWIPRow.setProductPrefix(rs.getString(wipProps
					        .getProductPrefix()));
					planWIPRow.setProductSuffix(rs.getString(wipProps
					        .getProductSuffix()));
					planWIPRow
					        .setPlanCode(rs.getString(wipProps.getPlanCode()));
					planWIPRow.setIssueState(rs.getString(wipProps
					        .getIssueState()));
					planWIPRow.setLob(rs.getString(wipProps.getLOB()));
					planWIPRow.setEffDate(rs.getString(wipProps
					        .getEffectiveDate()));
					planWIPRow
					        .setPlanType(rs.getString(wipProps.getPlanType()));
					planWIPRow.setTablePtrId(rs.getString(wipProps
					        .getTablePtrId()));
					planWIPRow.setTablePtrVar(rs.getString(wipProps
					        .getTablePtrVar()));
					planWIPRow.setTablePtrSubset(rs.getString(wipProps
					        .getTablePtrSubset()));
					*/
					planWIPRow.setDataOld(new PlanRowTO(rs, wipProps));
					/*
					planWIPRow.setOldCompanyCode(rs.getString(wipProps
					        .getOldCompanyCode()));
					planWIPRow.setOldProductPrefix(rs.getString(wipProps
					        .getOldProductPrefix()));
					planWIPRow.setOldProductSuffix(rs.getString(wipProps
					        .getOldProductSuffix()));
					planWIPRow.setOldPlanCode(rs.getString(wipProps
					        .getOldPlanCode()));
					planWIPRow.setOldIssueState(rs.getString(wipProps
					        .getOldIssueState()));
					planWIPRow.setOldLob(rs.getString(wipProps.getOldLOB()));
					planWIPRow.setOldEffDate(rs.getString(wipProps
					        .getOldEffectiveDate()));
					planWIPRow.setOldPlanType(rs.getString(wipProps
					        .getOldPlanType()));
					planWIPRow
					        .setOldPtrId(rs.getString(wipProps.getOldPtrId()));
					planWIPRow.setOldPtrVar(rs.getString(wipProps
					        .getOldPtrVar()));
					planWIPRow.setOldPtrSubset(rs.getString(wipProps
					        .getOldPtrSubset()));
					*/
					planWIPRow.setProjectName(project_name);
					planWIPRow.setOperation(rs.getString(wipProps
					        .getOperation()));
					planWIPRow.setChangeUserId(rs.getString(wipProps
					        .getChangeUserId()));
					planWIPRow.setTimestamp(rs.getString(wipProps
					        .getTimeStamp()));

					mRows.add(planWIPRow);
				} else {
					planRow = (PlanRow) new RowManager(environment.getId())
					        .getBlankRow(Constants.TABLE_ZERO_ID);

					TableDescriptor _tableDescriptor = environment.getTableDescMgr().getTableDescriptor(
					                Constants.TABLE_ZERO_ID);
					Vector<ColumnDescriptor> columndescriptors = _tableDescriptor
					        .getColumnDescriptors();
					int size = columndescriptors.size();
					String columnValue = null;
					StringBuffer returnValue = null;

					for (int count = 0; count < size; count++) {
						ColumnDescriptor cd = columndescriptors
						        .get(count);

						returnValue = new StringBuffer();
						// refactor plan key.
						columnValue = rs.getString(cd.getDbColumnName());
						/*
						switch (count) {

							case 0:
								columnValue = rs.getString("COMPANY_CODE");
								break;
							case 1:
								columnValue = rs.getString("PRODUCT_PREFIX");
								break;
							case 2:
								columnValue = rs.getString("PRODUCT_SUFFIX");
								break;
							case 3:
								columnValue = rs.getString("PLAN_CODE");
								break;
							case 4:
								columnValue = rs.getString("ISSUE_STATE");
								break;
							case 5:
								columnValue = rs.getString("LINE_OF_BUSINESS");
								break;
							case 6:
								columnValue = rs.getString("EFFECTIVE_DATE");
								break;
							case 7:
								columnValue = rs.getString("PLAN_TYPE");
								break;
							case 8:
								columnValue = rs.getString("TABLE_PTR_ID");
								break;
							case 9:
								columnValue = rs.getString("TABLE_PTR_VAR");
								break;
							case 10:
								columnValue = rs.getString("TABLE_PTR_SUBSET");
								break;
						}
						*/
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

					planRow.setColumns(sb.toString());
					mRows.add(planRow);
				}
				
			}
		}
	}

	private void toIndexArrayList(ResultSet rs, ArrayList<Object> mRows)
	        throws Exception {

		if (rs != null) {
			Row indexRow = null;
			IndexWIPRow indexWIPRow = null;
			StringBuffer sb = null;

			while (rs.next()) {
				sb = new StringBuffer();
				String project_name = rs.getString(wipProps.getProjectName())
				        .trim();

				if (!project_name.equals("")) {
					indexWIPRow = (IndexWIPRow) WIPRowFactory
					        .createWIPRowOfType(Constants.INDEX_WIP);
					indexWIPRow.setEnvironment(environment.getId());
					indexWIPRow.setTableId(Constants.SUBSET_TABLE_ID);
					indexWIPRow.setDdlName(Constants.SUBSET_TABLE_NAME);
					indexWIPRow.setCompanyCode(rs.getString(wipProps
					        .getCompanyCode()));
					indexWIPRow.setProductPrefix(rs.getString(wipProps
					        .getProductPrefix()));
					indexWIPRow.setParentTableId(rs.getString(wipProps
					        .getPrimaryTableId()));
					indexWIPRow.setParentTableSubset(rs.getString(wipProps
					        .getPrimaryPtrSubset()));
					indexWIPRow.setChildTableId(rs.getString(wipProps
					        .getSecondaryTableId()));
					indexWIPRow.setChildTableSubset(rs.getString(wipProps
					        .getSecondaryPtrSubset()));
					indexWIPRow.setChildTableVar(rs.getString(wipProps
					        .getSecondaryTableVar()));
					indexWIPRow.setOldConcatKey(rs.getString(wipProps
					        .getOldConcatKey()));
					indexWIPRow.setProjectName(project_name);
					indexWIPRow.setOperation(rs.getString(wipProps
					        .getOperation()));
					indexWIPRow.setChangeUserId(rs.getString(wipProps
					        .getChangeUserId()));
					indexWIPRow.setTimestamp(rs.getString(wipProps
					        .getTimeStamp()));

					mRows.add(indexWIPRow);
				} else {
					indexRow = new RowManager(environment.getId())
					        .getBlankRow(Constants.SUBSET_TABLE_ID);
					TableDescriptor _tableDescriptor = environment.getTableDescMgr().getTableDescriptor(
					                Constants.SUBSET_TABLE_ID);
					Vector<ColumnDescriptor> columndescriptors = _tableDescriptor
					        .getColumnDescriptors();
					int size = columndescriptors.size();
					String columnValue = null;
					StringBuffer returnValue = null;

					for (int count = 0; count < size; count++) {
						ColumnDescriptor cd = columndescriptors
						        .get(count);

						returnValue = new StringBuffer();
						switch (count) {

							case 0:
								columnValue = rs.getString("COMPANY_CODE");
								break;
							case 1:
								columnValue = rs.getString("PRODUCT_PREFIX");
								break;
							case 2:
								columnValue = rs.getString("PRIMARY_TABLE_ID");
								break;
							case 3:
								columnValue = rs
								        .getString("PRIMARY_PTR_SUBSET");
								break;
							case 4:
								columnValue = rs
								        .getString("SECONDARY_TABLE_ID");
								break;
							case 5:
								columnValue = rs
								        .getString("SECNDRY_PTR_SUBSET");
								break;
							case 6:
								columnValue = rs.getString("SECNDRY_TABLE_VAR");
								break;
						}
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
					indexRow.setColumns(sb.toString());
					mRows.add(indexRow);
				}
				
			}
		}
	}

}
