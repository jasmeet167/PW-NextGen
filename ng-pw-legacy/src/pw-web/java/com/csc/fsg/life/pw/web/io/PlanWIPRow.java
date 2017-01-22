/*
 * THIS PROGRAM IS THE PROPERTY OF CSC FINANCIAL SERVICES GROUP. IT MAY NOT BE
 * COPIED IN WHOLE OR IN PART WITHOUT THE EXPRESS WRITTEN CONSENT OF CSC
 * FINANCIAL SERVICES GROUP.
 */

package com.csc.fsg.life.pw.web.io;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.StringTokenizer;
import java.util.Vector;

import com.csc.fsg.life.exceptions.WrapperException;
import com.csc.fsg.life.pw.common.transferobjects.PlanRowTO;
import com.csc.fsg.life.pw.common.transferobjects.PlanTO;
import com.csc.fsg.life.pw.common.util.Constants;
import com.csc.fsg.life.pw.web.environment.*;
import com.csc.fsg.life.pw.web.utils.SqlPW;
import com.csc.fsg.life.pw.web.utils.sql.SQLBuilderPLANWIP;

/* Modifications: T0103, T0091, CCCV-D506, T0129, WMA-667, WMA-888, T0106, WMA-1550 */

/**
 * This class abstracts a single row from the Plan WIP table. Since it knows
 * what the table looks like, it can build SQL to query and update it. Creation
 * date: (10/10/2001 9:40:12 AM)
 * 
 * @author: Carl Ericksen
 */

public class PlanWIPRow extends WIPRow {

	private String planCode = "";

	private String issueState = "";

	private String lob = "";

	private String effDate;

	private String tablePtrId = "";

	private String tablePtrSubset = "";

	private String tablePtrVar = "";

	private String productSuffix = "";

	private String planType = "";

	// oldNew members are the result of parsing oldNewConcatKey if present
	private String oldNewSuffix = "";

	private String oldNewPlanCode = "";

	private String oldNewState = "";

	private String oldNewLOB = "";

	private String oldNewEffdt = "";

	private String oldNewPlanType = "";

	private String oldNewPtrId = "";

	private String oldNewPtrVar = "";

	private String oldNewPtrSubset = "";

	// discreet old key (in lieu of oldConcatKey)
	private String oldCompanyCode = "";

	private String oldProductPrefix = "";

	private String oldProductSuffix = "";

	private String oldPlanCode = "";

	private String oldIssueState = "";

	private String oldLob = "";

	private String oldEffDate;

	private String oldPlanType = "";

	private String oldPtrId = "";

	private String oldPtrVar = "";

	private String oldPtrSubset = "";

	/**
	 * Constructor for PlanWIPRow. DO NOT USE! Use WIPRowFactory instead.
	 */

	protected PlanWIPRow() {

		super();
		setDdlName(Constants.TABLE_ZERO_NAME);
		setTableId(Constants.TABLE_ZERO_ID);
	}

	/**
	 * This method builds a selects statement given the passed parameters.
	 * Parameters within WIPTableFilter which do not apply to this table will be
	 * ignored. Creation date: (10/11/2001 1:18:49 PM)
	 * 
	 * @return String
	 * @param filter
	 *            com.csc.fsg.life.pw.web.io.WIPTableFilter
	 * @param orderBy
	 *            String
	 */

	public static String buildSelectSQL(WIPTableFilter filter, String orderBy)
	         {

		StringBuffer sql = new StringBuffer();

		// refactor plan sql.
		sql.append(new SQLBuilderPLANWIP(filter).buildSelectAllStatement());
		/*
		Environment environment = EnvironmentManager.getInstance().getEnvironment(filter.getEnvironment());
	
		sql.append("SELECT * FROM "
		        + environment.getApplSchema() + "."
		        + wipProps.getTableName(Constants.PLAN_WIP));
		sql.append(buildWhereSQL(filter));
		*/
		if (orderBy != null) {
			// Same orderBy clause used as for COMMONWIP; 
			// reference to DDLNAME must bew removed, if found
			orderBy = UtilMethods.removeSortColumn(orderBy, "DDLNAME");
			sql.append(" " + orderBy);
		}
	
		return sql.toString();
	}

	// refactor plan sql.
	/* @deprecated use SQLBuilderPLANWIP
	public static String buildWhereSQL(WIPTableFilter filter)
	        throws WIPRowException {

		StringBuffer sql = new StringBuffer();
		WIPProperties wipProps = WIPProperties.getInstance();

		sql.append(" WHERE ");
		sql.append(wipProps.getEnvironment(Constants.PLAN_WIP) + " = '"
		        + filter.getEnvironment() + "' ");
		if (filter.getCompanyCode() != null) {
			sql.append(" AND "
			        + wipProps.getCompanyCode(Constants.PLAN_WIP) + " = '"
			        + filter.getCompanyCode() + "' ");
		}
		if (filter.getProductPrefix() != null) {
			sql.append(" AND "
			        + wipProps.getProductPrefix(Constants.PLAN_WIP)
			        + " = '" + filter.getProductPrefix() + "' ");
		}
		if (filter.getProductSuffix() != null) {
			sql.append(" AND " + wipProps.getProductSuffix() + " = '"
			        + filter.getProductSuffix() + "' ");
		}

		Vector changeUserIds = filter.getChangeUserIds();

		if (changeUserIds != null) {
			if (changeUserIds.size() == 0) {
				throw new WIPRowException("Empty request vector");
			}
			for (int j = 0; j < changeUserIds.size(); j++) {
				if (j == 0) {
					sql.append(" AND ( "
					        + wipProps.getChangeUserId(Constants.PLAN_WIP)
					        + " = '" + (String) changeUserIds.elementAt(j)
					        + "' ");
				} else {
					sql.append(" OR "
					        + wipProps.getChangeUserId(Constants.PLAN_WIP)
					        + " = '" + (String) changeUserIds.elementAt(j)
					        + "' ");
				}
			}
			sql.append(" ) ");
		}

		Vector packageIds = filter.getPackageIds();

		if (packageIds != null) {
			if (packageIds.size() == 0) {
				throw new WIPRowException("Empty request vector");
			}
			for (int k = 0; k < packageIds.size(); k++) {
				if (k == 0) {
					sql.append(" AND ( "
					        + wipProps.getPackageId(Constants.PLAN_WIP)
					        + " = '" + (String) packageIds.elementAt(k)
					        + "' ");
				} else {
					sql.append(" OR "
					        + wipProps.getPackageId(Constants.PLAN_WIP)
					        + " = '" + (String) packageIds.elementAt(k)
					        + "' ");
				}
			}
			sql.append(" ) ");
		}

		Vector projectNames = filter.getProjectNames();

		if (projectNames != null) {
			if (projectNames.size() == 0) {
				throw new WIPRowException("Empty request vector");
			}
			for (int l = 0; l < projectNames.size(); l++) {
				if (l == 0) {
					sql.append(" AND ( "
					        + wipProps.getProjectName(Constants.PLAN_WIP)
					        + " = '" + (String) projectNames.elementAt(l)
					        + "' ");
				} else {
					sql.append(" OR "
					        + wipProps.getProjectName(Constants.PLAN_WIP)
					        + " = '" + (String) projectNames.elementAt(l)
					        + "' ");
				}
			}
			sql.append(" ) ");
		}

		Vector dbSequence = filter.getDbSequence();

		if (dbSequence != null) {
			if (dbSequence.size() == 0) {
				throw new WIPRowException("Empty request vector");
			}
			for (int m = 0; m < dbSequence.size(); m++) {
				if (m == 0) {
					sql.append(" AND ( "
					        + wipProps.getDBSequence(Constants.AUDIT_LOG)
					        + " = '" + dbSequence.elementAt(m) + "' ");
				} else {
					sql.append(" OR "
					        + wipProps.getDBSequence(Constants.AUDIT_LOG)
					        + " = '" + dbSequence.elementAt(m) + "' ");
				}
			}
			sql.append(" ) ");
		}

		if (filter.isWithErrors()) {
			sql.append(" AND "
			        + wipProps.getErrorIndicator(Constants.PLAN_WIP)
			        + " ='Y'");
		}

		if (filter.isLocked()) {
			sql.append(" AND "
			        + wipProps.getPromotionLock(Constants.PLAN_WIP)
			        + " ='L'");
		}

		if (filter.getPlanCode() != null) {
			sql.append(" AND " + wipProps.getPlanCode() + " = '"
			        + filter.getPlanCode() + "' ");
		}

		if (filter.getIssueState() != null) {
			sql.append(" AND " + wipProps.getIssueState() + " = '"
			        + filter.getIssueState() + "' ");
		}

		if (filter.getLob() != null) {
			sql.append(" AND " + wipProps.getLOB() + " = '"
			        + filter.getLob() + "' ");
		}

		if (filter.getEffDate() != null) {
			sql.append(" AND " + wipProps.getEffectiveDate() + " = '"
			        + filter.getEffDate() + "' ");
		}

		if (filter.getPlanType() != null) {
			sql.append(" AND " + wipProps.getPlanType() + " = '"
			        + filter.getPlanType() + "' ");
		}

		if (filter.getTablePtrId() != null) {
			sql.append(" AND " + wipProps.getTablePtrId() + " = '"
			        + filter.getTablePtrId() + "' ");
		}

		if (filter.getTablePtrSubset() != null) {
			sql.append(" AND " + wipProps.getTablePtrSubset() + " = '"
			        + filter.getTablePtrSubset() + "' ");
		}

		if (filter.getTablePtrVar() != null) {
			sql.append(" AND " + wipProps.getTablePtrVar() + " = '"
			        + filter.getTablePtrVar() + "' ");
		}

		
		return sql.toString();
	}
	public static String buildDeleteSQL(WIPTableFilter filter)
	        throws WIPRowException {

		StringBuffer sql = new StringBuffer();
		WIPProperties wipProps = WIPProperties.getInstance();
		Environment environment = EnvironmentManager.getInstance().getEnvironment(filter.getEnvironment());
	
			sql.append("DELETE FROM " + environment.getApplSchema()
			        + "." + wipProps.getTableName(Constants.PLAN_WIP));
			sql.append(buildWhereSQL(filter));
		return sql.toString();
	}
	*/

	/**
	 * This method builds an update statement for promotionLock given the passed
	 * parameters Creation date: (10/11/2001 1:18:49 PM)
	 * 
	 * @return String
	 * @param filter
	 *            com.csc.fsg.life.pw.web.io.WIPTableFilter
	 * @throws WIPRowException
	 */

	public static String buildLockSQL(WIPTableFilter filter)
	         {

		// refactor plan sql.
		String sql = new SQLBuilderPLANWIP(filter).buildToggleLockStatement();
		return sql;
		
		/*
		StringBuffer sql = new StringBuffer();
		WIPProperties wipProps = WIPProperties.getInstance();
		Environment environment = EnvironmentManager.getInstance().getEnvironment(filter.getEnvironment());
	
		sql.append("UPDATE " + environment.getApplSchema() + "."
		        + wipProps.getTableName(Constants.PLAN_WIP));
		if (filter.isLocked() == true) {
			// unlock row
			sql.append(" SET "
			        + wipProps.getPromotionLock(Constants.PLAN_WIP)
			        + " = ' '");
		} else {
			// lock row
			sql.append(" SET "
			        + wipProps.getPromotionLock(Constants.PLAN_WIP)
			        + " = 'L'");
		}
		sql.append(buildWhereSQL(filter));
	
		return sql.toString();
		*/
	}

	/**
	 * This method will create a save SQL string using PreparedStatement syntax
	 * for the passed wipAction. Creation date: (10/12/2001 11:22:19 AM)
	 * 
	 * @return String
	 * @param wipAction
	 *            String[]
	 * @throws WIPRowException
	 */

	public static String buildSaveSQL(String envId,String wipAction) throws Exception {

		String sqlStr = "";
		try {
		if (wipAction.equals(Constants.INSERT_OPERATION)) {
				sqlStr = new SQLBuilderPLANWIP(envId).prepareInsertStatement();
		} else if (wipAction.equals(Constants.UPDATE_OPERATION)) {
				sqlStr = new SQLBuilderPLANWIP(envId).prepareUpdateStatement();
		} else if (wipAction.equals(Constants.DELETE_OPERATION)) {
				sqlStr = new SQLBuilderPLANWIP(envId).prepareDeleteStatement();
		}
		} catch (Exception e) {
			throw new WIPRowException(e.getMessage());
		} finally {

		}
		return sqlStr;
	}

	/* @deprecated use SQLBuilderPLANWIP.prepareInsertStatement()
	private static void buildSaveInsertSQL(String envId,StringBuffer sql,
	        WIPProperties wipProps) throws Exception {

		Environment environment = EnvironmentManager.getInstance().getEnvironment(envId);
		sql.append("INSERT INTO ");
		sql.append(environment.getApplSchema() + "."
		        + wipProps.getTableName(Constants.PLAN_WIP));
		sql.append(" ( " + wipProps.getEnvironment() + ",");
		sql.append(wipProps.getCompanyCode() + ",");
		sql.append(wipProps.getProductPrefix() + ",");
		sql.append(wipProps.getProductSuffix() + ",");
		sql.append(wipProps.getPlanCode() + ",");
		sql.append(wipProps.getIssueState() + ",");
		sql.append(wipProps.getLOB() + ",");
		sql.append(wipProps.getEffectiveDate() + ",");
		sql.append(wipProps.getPlanType() + ",");
		sql.append(wipProps.getTablePtrId() + ",");
		sql.append(wipProps.getTablePtrVar() + ",");
		sql.append(wipProps.getTablePtrSubset() + ",");
		sql.append(wipProps.getOperation() + ",");
		sql.append(wipProps.getOldCompanyCode() + ",");
		sql.append(wipProps.getOldProductPrefix() + ",");
		sql.append(wipProps.getOldProductSuffix() + ",");
		sql.append(wipProps.getOldPlanCode() + ",");
		sql.append(wipProps.getOldIssueState() + ",");
		sql.append(wipProps.getOldLOB() + ",");
		sql.append(wipProps.getOldEffectiveDate() + ",");
		sql.append(wipProps.getOldPlanType() + ",");
		sql.append(wipProps.getOldPtrId() + ",");
		sql.append(wipProps.getOldPtrVar() + ",");
		sql.append(wipProps.getOldPtrSubset() + ",");
		// end
		sql.append(wipProps.getProjectName() + ",");
		sql.append(wipProps.getChangeUserId() + ",");
		sql.append(wipProps.getPackageId() + ",");
		sql.append(wipProps.getErrorIndicator() + ",");
		sql.append(wipProps.getPromotionLock() + ",");
		sql.append(wipProps.getPromoteUserId() + ",");
		sql.append(wipProps.getDBSequence() + ")");
		sql
		        .append("VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ? )");
	}
	*/
	/* @deprecated use SQLBuilderPLANWIP.prepareUpdateStatement()
	private static void buildSaveUpdateSQL(String envId,StringBuffer sql,
	        WIPProperties wipProps) throws Exception {
		Environment environment = EnvironmentManager.getInstance().getEnvironment(envId);
		sql.append("UPDATE ");
		sql.append(environment.getApplSchema() + "."
		        + wipProps.getTableName(Constants.PLAN_WIP));
		sql.append(" SET ");
		sql.append(wipProps.getEnvironment() + " = ?, ");
		sql.append(wipProps.getCompanyCode() + " = ?, ");
		sql.append(wipProps.getProductPrefix() + " = ?, ");
		sql.append(wipProps.getProductSuffix() + " = ?, ");
		sql.append(wipProps.getPlanCode() + " = ?, ");
		sql.append(wipProps.getIssueState() + " = ?, ");
		sql.append(wipProps.getLOB() + " = ?, ");
		sql.append(wipProps.getEffectiveDate() + " = ?, ");
		sql.append(wipProps.getPlanType() + " = ?, ");
		sql.append(wipProps.getTablePtrId() + " = ?, ");
		sql.append(wipProps.getTablePtrVar() + " = ?, ");
		sql.append(wipProps.getTablePtrSubset() + " = ?, ");
		sql.append(wipProps.getOperation() + " = ?, ");
		sql.append(wipProps.getProjectName() + " = ?, ");
		sql.append(wipProps.getChangeUserId() + " = ?, ");
		sql.append(wipProps.getPackageId() + " = ?, ");
		sql.append(wipProps.getErrorIndicator() + " = ?, ");
		sql.append(wipProps.getPromotionLock() + " = ?, ");
		sql.append(wipProps.getPromoteUserId() + " = ? ");
		buildSaveWhereSQL(sql, wipProps);
	}
	*/
	/* @deprecated use SQLBuilderPLANWIP.prepareDeleteStatement()
	private static void buildSaveDeleteSQL(String envId,StringBuffer sql,
	        WIPProperties wipProps) throws Exception {
		Environment environment = EnvironmentManager.getInstance().getEnvironment(envId);
		sql.append("DELETE FROM ");
		sql.append(environment.getApplSchema() + "."
		        + wipProps.getTableName(Constants.PLAN_WIP));
		buildSaveWhereSQL(sql, wipProps);
	}

	private static void buildSaveWhereSQL(StringBuffer sql,
	        WIPProperties wipProps) throws Exception {

		sql.append(" WHERE ");
		sql.append(wipProps.getEnvironment() + " = ? ");
		sql.append("AND " + wipProps.getCompanyCode() + " = ? ");
		sql.append("AND " + wipProps.getProductPrefix() + " = ? ");
		sql.append("AND " + wipProps.getProductSuffix() + " = ? ");
		sql.append("AND " + wipProps.getPlanCode() + " = ? ");
		sql.append("AND " + wipProps.getIssueState() + " = ? ");
		sql.append("AND " + wipProps.getLOB() + " = ? ");
		sql.append("AND " + wipProps.getEffectiveDate() + " = ? ");
		sql.append("AND " + wipProps.getPlanType() + " = ? ");
		sql.append("AND " + wipProps.getTablePtrId() + " = ? ");
		sql.append("AND " + wipProps.getTablePtrVar() + " = ? ");
		sql.append("AND " + wipProps.getTablePtrSubset() + " = ? ");
		// start
		// sql.append("AND " + wipProps.getOldConcatKey() + " = ? ");
		sql.append("AND " + wipProps.getOldCompanyCode() + " = ? ");
		sql.append("AND " + wipProps.getOldProductPrefix() + " = ? ");
		sql.append("AND " + wipProps.getOldProductSuffix() + " = ? ");
		sql.append("AND " + wipProps.getOldPlanCode() + " = ? ");
		sql.append("AND " + wipProps.getOldIssueState() + " = ? ");
		sql.append("AND " + wipProps.getOldLOB() + " = ? ");
		sql.append("AND " + wipProps.getOldEffectiveDate() + " = ? ");
		sql.append("AND " + wipProps.getOldPlanType() + " = ? ");
		sql.append("AND " + wipProps.getOldPtrId() + " = ? ");
		sql.append("AND " + wipProps.getOldPtrVar() + " = ? ");
		sql.append("AND " + wipProps.getOldPtrSubset() + " = ? ");
		// end
		sql.append("AND " + wipProps.getErrorIndicator() + " LIKE ? ");
		sql.append("AND " + wipProps.getPromotionLock() + " = ? ");
	}
	*/
	/**
	 * This method saves the data it contains to the WIP table it represents
	 * Creation date: (10/10/2001 9:50:04 AM)
	 * 
	 * @param stmt
	 * @param action
	 * @param wipRows
	 * @throws WIPRowException
	 */

	public void save(PreparedStatement stmt, String action, IWIPRows wipRows)
	        throws WIPRowException {

		try {
			if (action.equals(Constants.INSERT_OPERATION)) {
				setValuesForInsert(stmt, wipRows);
			} else if (action.equals(Constants.UPDATE_OPERATION)) {
				setValuesForUpdate(stmt, wipRows.getRequestFilter());
			} else if (action.equals(Constants.DELETE_OPERATION)) {
				setValuesForDelete(stmt, wipRows.getRequestFilter());
			}

			int count = SqlPW.update(SqlPW.SQL_PLAN_SAVE, stmt);
			if (count == 0) {
				throw new WIPRowException(Constants.ROW_NOT_FOUND_MSG);
			}
		} catch (SQLException e) {
			throw WrapperException.wrap(e);
		}
	}

	/**
	 * This method transforms a PlanWIPRow to into a CommonWIPRow object.
	 * Creation date: (10/12/2001 1:32:03 PM)
	 * 
	 * @return com.csc.fsg.life.pw.web.io.CommonWIPRow
	 */

	public CommonWIPRow transformToCommon() {

		CommonWIPRow commonRow = (CommonWIPRow) WIPRowFactory
		        .createWIPRowOfType(Constants.COMMON_WIP);

		commonRow.setAttrMask(getAttrMask());
		commonRow.setChangeUserId(getChangeUserId());
		commonRow.setCompanyCode(getCompanyCode());
		commonRow.setDdlName(getDdlName());
		commonRow.setEnvironment(getEnvironment());
		commonRow.setErrorIndicator(getErrorIndicator());
		commonRow.setNewConcatKey(getNewConcatKey());
		commonRow.setNewData(getNewData());
		commonRow.setNumberOfColumns(getNumberOfColumns());
		commonRow.setOldConcatKey(getOldConcatKey());
		commonRow.setOldData(getOldConcatKey());// same as the oldData
		commonRow.setOldNewConcatKey(getOldNewConcatKey());
		commonRow.setOperation(getOperation());
		commonRow.setOriginalData(getOriginalData());
		commonRow.setPackageId(getPackageId());
		commonRow.setProductPrefix(getProductPrefix());
		commonRow.setProjectName(getProjectName());
		commonRow.setPromoteUserId(getPromoteUserId());
		commonRow.setPromotionLock(getPromotionLock());
		commonRow.setRowIndex(getRowIndex());
		commonRow.setSubsetName(getSubsetName());
		commonRow.setTableId(getTableId());
		commonRow.setTimestamp(getTimestamp());
		commonRow.setWipActionAttrMask(getWipActionAttrMask());
		commonRow.setDbSequence(getDbSequence());
		return commonRow;
	}

	/**
	 * This method returns a deep clone of the target WIPRow.
	 * 
	 * @return
	 * @throws CloneNotSupportedException
	 */

	public Object clone() throws CloneNotSupportedException {

		PlanWIPRow pRow = null;

		
		pRow = (PlanWIPRow) super.clone();
		if (getOriginalData() != null) {
			System.arraycopy(getOriginalData(), 0, pRow.getOriginalData(),
			        0, pRow.getOriginalData().size());
		}
		
		return pRow;
	}

	/**
	 * ********************* GETTERS **********************
	 * 
	 * @return
	 */

	// discreet old key
	public String getOldCompanyCode() {
		return oldCompanyCode;
	}

	/**
	 * Method getOldProductPrefix
	 * 
	 * @return
	 */

	public String getOldProductPrefix() {
		return oldProductPrefix;
	}

	/**
	 * Method getOldProductSuffix
	 * 
	 * @return
	 */

	public String getOldProductSuffix() {
		return oldProductSuffix;
	}

	/**
	 * Method getOldPlanCode
	 * 
	 * @return
	 */

	public String getOldPlanCode() {
		return oldPlanCode;
	}

	/**
	 * Method getOldEffDate
	 * 
	 * @return
	 */

	public String getOldEffDate() {
		return oldEffDate;
	}

	/**
	 * Method getOldIssueState
	 * 
	 * @return
	 */

	public String getOldIssueState() {
		return oldIssueState;
	}

	/**
	 * Method getOldLob
	 * 
	 * @return
	 */

	public String getOldLob() {
		return oldLob;
	}

	/**
	 * Method getOldPlanType
	 * 
	 * @return
	 */

	public String getOldPlanType() {
		return oldPlanType;
	}

	/**
	 * Method getOldPtrId
	 * 
	 * @return
	 */

	public String getOldPtrId() {
		return oldPtrId;
	}

	/**
	 * Method getOldPtrVar
	 * 
	 * @return
	 */

	public String getOldPtrVar() {
		return oldPtrVar;
	}

	/**
	 * Method getOldPtrSubset
	 * 
	 * @return
	 */

	public String getOldPtrSubset() {
		return oldPtrSubset;
	}

	/**
	 * Method getOldConcatKey
	 * 
	 * @return
	 */

	public String getOldConcatKey() {

		StringBuffer sb = new StringBuffer();
		StringBuffer returnValue = null;
		String columnValue = null;
		int originalColumnLength = 0;

		TableDescriptor td = EnvironmentManager.getInstance().getEnvironment(getEnvironment()).getTableDescMgr()
		        .getTableDescriptor(Constants.TABLE_ZERO_ID);
		Vector<ColumnDescriptor> columndescriptors = td.getColumnDescriptors();
		int size = columndescriptors.size();

		String planRowKey = getDataOld().getPlanRowKey("|");
		StringTokenizer tokenizer = new StringTokenizer(planRowKey, "|");
		for (int count = 0; count < size; count++) {
			ColumnDescriptor cd = columndescriptors
			        .get(count);

			originalColumnLength = cd.getColumnSize();
			// refactor plan key.
			columnValue = tokenizer.nextToken();
			/*
			switch (count) {

				case 0:
					columnValue = getOldCompanyCode();
					break;
				case 1:
					columnValue = getOldProductPrefix();
					break;
				case 2:
					columnValue = getOldProductSuffix();
					break;
				case 3:
					columnValue = getOldPlanCode();
					break;
				case 4:
					columnValue = getOldIssueState();
					break;
				case 5:
					columnValue = getOldLob();
					break;
				case 6:
					columnValue = getOldEffDate();
					break;
				case 7:
					columnValue = getOldPlanType();
					break;
				case 8:
					columnValue = getOldPtrId();
					break;
				case 9:
					columnValue = getOldPtrVar();
					break;
				case 10:
					columnValue = getOldPtrSubset();
					break;
			}
			*/
			returnValue = new StringBuffer();
			try {

				int apendType = UtilMethods.getAppendType(cd.getDataType());

				if (apendType == UtilMethods.APPEND_ZEROS) {
					UtilMethods.appendLeadingZeros(returnValue, columnValue,
					        originalColumnLength);
				} else {
					UtilMethods.appendTrailingSpaces(returnValue, columnValue,
					        originalColumnLength);
				}

			} catch (Exception e) {
				throw WrapperException.wrap(e);
			}

			sb.append(returnValue.toString());
			returnValue = null;
		}
		// String oldConcatKey = sb.toString();// spr 2378
		oldConcatKey = sb.toString(); // spr 2378
		return oldConcatKey;
	}

	/**
	 * Method getEffDate
	 * 
	 * @return
	 */

	public String getEffDate() {
		return effDate;
	}

	/**
	 * Method getIssueState
	 * 
	 * @return
	 */

	public String getIssueState() {
		return issueState;
	}

	/**
	 * Method getLob
	 * 
	 * @return
	 */

	public String getLob() {
		return lob;
	}

	/**
	 * This method returns a delimited Plankey using the supplied delimiter
	 * Creation date: (01/24/2002 01:36:28 PM)
	 * 
	 * @param delim
	 * @return String
	 */

	public String getConcatPlanKey(String delim) {
		// refactor plan key.
		return getDataNew().getPlanKey(delim);
		/*
		StringBuffer sb = new StringBuffer(50);

		sb.append(getCompanyCode().trim()).append(delim);
		sb.append(getProductPrefix().trim()).append(delim);
		sb.append(getProductSuffix().trim()).append(delim);
		sb.append(getPlanCode().trim()).append(delim);
		sb.append(getIssueState().trim()).append(delim);
		sb.append(getLob().trim()).append(delim);
		sb.append(getEffDate().trim()).append(delim);
		sb.append(getPlanType().trim());
		return sb.toString();
		*/
	}

	// WMA-667
	public String getOldConcatPlanKey(String delim) {
		return getDataOld().getPlanKey(delim);
	}
	
	/**
	 * This method returns a pipe delimited Plankey Creation date: (01/24/2002
	 * 01:36:28 PM)
	 * 
	 * @return String
	 */

	public String getConcatPlanKey() {
		return getConcatPlanKey(Constants.SPIPE);
	}

	/**
	 * This method builds the newConcatKey from the appropriate column(s) on the
	 * particular WIP table. Creation date: (10/12/2001 12:49:37 PM)
	 * 
	 * @return String
	 */

	public String getNewConcatKey() {
		return getNewData(); // key and data of T000X row are the same
	}

	/**
	 * This method builds the newData from the appropriate column(s) on the
	 * particular WIP table. Creation date: (10/10/2001 10:03:15 AM)
	 * 
	 * @return String
	 */

	public String getNewData() {

		StringBuffer sb = new StringBuffer();
		StringBuffer returnValue = null;
		String columnValue = null;
		int originalColumnLength = 0;

		TableDescriptor td = EnvironmentManager.getInstance().getEnvironment(getEnvironment()).getTableDescMgr()
		        .getTableDescriptor(Constants.TABLE_ZERO_ID);
		Vector<ColumnDescriptor> columndescriptors = td.getColumnDescriptors();
		int size = columndescriptors.size();

		String planRowKey = getDataNew().getPlanRowKey("|");
		StringTokenizer tokenizer = new StringTokenizer(planRowKey, "|");
		for (int count = 0; count < size; count++) {
			ColumnDescriptor cd = columndescriptors
			        .get(count);

			originalColumnLength = cd.getColumnSize();
			// refactor plan key.
			columnValue = tokenizer.nextToken();
			/*
			switch (count) {

				case 0:
					columnValue = getCompanyCode();
					break;
				case 1:
					columnValue = getProductPrefix();
					break;
				case 2:
					columnValue = getProductSuffix();
					break;
				case 3:
					columnValue = getPlanCode();
					break;
				case 4:
					columnValue = getIssueState();
					break;
				case 5:
					columnValue = getLob();
					break;
				case 6:
					columnValue = getEffDate();
					break;
				case 7:
					columnValue = getPlanType();
					break;
				case 8:
					columnValue = getTablePtrId();
					break;
				case 9:
					columnValue = getTablePtrVar();
					break;
				case 10:
					columnValue = getTablePtrSubset();
					break;
			}
			*/
			returnValue = new StringBuffer();
			try {

				int apendType = UtilMethods.getAppendType(cd.getDataType());

				if (apendType == UtilMethods.APPEND_ZEROS) {
					UtilMethods.appendLeadingZeros(returnValue, columnValue,
					        originalColumnLength);
				} else {
					UtilMethods.appendTrailingSpaces(returnValue, columnValue,
					        originalColumnLength);
				}

			} catch (Exception e) {
				throw WrapperException.wrap(e);
			}

			sb.append(returnValue.toString());
			returnValue = null;
		}
		newData = sb.toString();
	
		return newData;
	}

	public PlanRowTO getData() {
		return getDataNew();
	}
	public PlanRowTO getDataNew() {
		PlanRowTO planRow = new PlanRowTO();
		planRow.setEnvironment(getEnvironment());
		planRow.setCompanyCode(getCompanyCode());
		planRow.setProductPrefix(getProductPrefix());
		planRow.setProductSuffix(getProductSuffix());
		planRow.setPlanCode(getPlanCode());
		planRow.setIssueState(getIssueState());
		planRow.setLineOfBusiness(getLob());
		planRow.setEffectiveDate(getEffDate());
		planRow.setPlanType(getPlanType());
		planRow.setTablePtrId(getTablePtrId());
		planRow.setTablePtrVar(getTablePtrVar());
		planRow.setTablePtrSubset(getTablePtrSubset());
		return planRow;
	}
	public PlanRowTO getDataOld() {
		PlanRowTO planRow = new PlanRowTO();
		planRow.setEnvironment(getEnvironment());
		planRow.setCompanyCode(getOldCompanyCode());
		planRow.setProductPrefix(getOldProductPrefix());
		planRow.setProductSuffix(getOldProductSuffix());
		planRow.setPlanCode(getOldPlanCode());
		planRow.setIssueState(getOldIssueState());
		planRow.setLineOfBusiness(getOldLob());
		planRow.setEffectiveDate(getOldEffDate());
		planRow.setPlanType(getOldPlanType());
		planRow.setTablePtrId(getOldPtrId());
		planRow.setTablePtrVar(getOldPtrVar());
		planRow.setTablePtrSubset(getOldPtrSubset());
		return planRow;
	}

	/**
	 * Method getPlanCode
	 * 
	 * @return
	 */

	public String getPlanCode() {
		return planCode;
	}

	/**
	 * Method getPlanType
	 * 
	 * @return
	 */

	public String getPlanType() {
		return planType;
	}

	/**
	 * Method getProductSuffix
	 * 
	 * @return
	 */

	public String getProductSuffix() {
		return productSuffix;
	}

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
	 * Method getTablePtrVar
	 * 
	 * @return
	 */

	public String getTablePtrVar() {
		return tablePtrVar;
	}

	/**
	 * If the oldNewConcatKey is valued, this method will parse it and populate
	 * the respective oldNew variables on this. Otherwise, the oldNew variables
	 * will be set to the corresponding new values on this.
	 */

	private void getOldNewValues() {

		if (oldNewConcatKey == null 
		 || oldNewConcatKey.equals(""))			// oldNewConcatKey has not been valued
		{
			oldNewSuffix = getProductSuffix();
			oldNewPlanCode = getPlanCode();
			oldNewState = getIssueState();
			oldNewLOB = getLob();
			oldNewEffdt = getEffDate();
			oldNewPlanType = getPlanType();
			oldNewPtrId = getTablePtrId();
			oldNewPtrVar = getTablePtrVar();
			oldNewPtrSubset = getTablePtrSubset();
		} else // parse the oldNewConcatKey into distinct fields
		{
			oldNewSuffix = oldNewConcatKey.substring(4, 5);
			oldNewPlanCode = oldNewConcatKey.substring(5, 11);
			oldNewState = oldNewConcatKey.substring(11, 14);
			oldNewLOB = oldNewConcatKey.substring(14, 17);
			oldNewEffdt = oldNewConcatKey.substring(17, 27);
			oldNewPlanType = oldNewConcatKey.substring(27, 28);
			// TT SPR #119: fixed one-off index bug
			oldNewPtrId = oldNewConcatKey.substring(28, 31);
			oldNewPtrVar = oldNewConcatKey.substring(31, 32);
			oldNewPtrSubset = oldNewConcatKey.substring(32, 48);
		}
	}

	/**
	 * ********************* SETTERS **********************
	 * 
	 * @param oldCompanyCode
	 */

	// discreet old key
	public void setOldCompanyCode(String oldCompanyCode) {
		this.oldCompanyCode = oldCompanyCode;
	}

	/**
	 * Method setOldProductPrefix
	 * 
	 * @param oldProductPrefix
	 */

	public void setOldProductPrefix(String oldProductPrefix) {
		this.oldProductPrefix = oldProductPrefix;
	}

	/**
	 * Method setOldProductSuffix
	 * 
	 * @param oldProductSuffix
	 */

	public void setOldProductSuffix(String oldProductSuffix) {
		this.oldProductSuffix = oldProductSuffix;
	}

	/**
	 * Method setOldPlanCode
	 * 
	 * @param oldPlanCode
	 */

	public void setOldPlanCode(String oldPlanCode) {
		this.oldPlanCode = oldPlanCode;
	}

	/**
	 * Method setOldEffDate
	 * 
	 * @param oldEffDate
	 */

	public void setOldEffDate(String oldEffDate) {
		this.oldEffDate = oldEffDate;
	}

	/**
	 * Method setOldIssueState
	 * 
	 * @param oldIssueState
	 */

	public void setOldIssueState(String oldIssueState) {
		this.oldIssueState = oldIssueState;
	}

	/**
	 * Method setOldLob
	 * 
	 * @param oldLob
	 */

	public void setOldLob(String oldLob) {
		this.oldLob = oldLob;
	}

	/**
	 * Method setOldPlanType
	 * 
	 * @param oldPlanType
	 */

	public void setOldPlanType(String oldPlanType) {
		this.oldPlanType = oldPlanType;
	}

	/**
	 * Method setOldPtrId
	 * 
	 * @param oldPtrId
	 */

	public void setOldPtrId(String oldPtrId) {
		this.oldPtrId = oldPtrId;
	}

	/**
	 * Method setOldPtrVar
	 * 
	 * @param oldPtrVar
	 */

	public void setOldPtrVar(String oldPtrVar) {
		this.oldPtrVar = oldPtrVar;
	}

	/**
	 * Method setOldPtrSubset
	 * 
	 * @param oldPtrSubset
	 */

	public void setOldPtrSubset(String oldPtrSubset) {
		this.oldPtrSubset = oldPtrSubset;
	}

	/**
	 * Method setOldConcatKey
	 * 
	 * @param oldConcatKey
	 */

	public void setOldConcatKey(String oldConcatKey) {
		// refactor plan key.
		setDataOld(new PlanRowTO(oldConcatKey, ""));
		/*
		setOldCompanyCode(oldConcatKey.substring(0, 3));
		setOldProductPrefix(oldConcatKey.substring(3, 4));
		setOldProductSuffix(oldConcatKey.substring(4, 5));
		setOldPlanCode(oldConcatKey.substring(5, 11));
		setOldIssueState(oldConcatKey.substring(11, 14));
		setOldLob(oldConcatKey.substring(14, 17));
		setOldEffDate(oldConcatKey.substring(17, 27));
		setOldPlanType(oldConcatKey.substring(27, 28));
		setOldPtrId(oldConcatKey.substring(28, 31));
		setOldPtrVar(oldConcatKey.substring(31, 32));
		setOldPtrSubset(oldConcatKey.substring(32, 48));
		*/
	}

	/**
	 * Method setOldConcatKeyForDeletePlan
	 * 
	 * @param oldConcatKey
	 */

	public void setOldConcatKeyForDeletePlan(String oldConcatKey) {
		// refactor plan key.
		setDataOld(new PlanRowTO(new PlanTO(oldConcatKey, "")));
		/*
		setOldCompanyCode(oldConcatKey.substring(0, 3));
		setOldProductPrefix(oldConcatKey.substring(3, 4));
		setOldProductSuffix(oldConcatKey.substring(4, 5));
		setOldPlanCode(oldConcatKey.substring(5, 11));
		setOldIssueState(oldConcatKey.substring(11, 14));
		setOldLob(oldConcatKey.substring(14, 17));
		setOldEffDate(oldConcatKey.substring(17, 27));
		setOldPlanType(oldConcatKey.substring(27, 28));
		*/
	}

	// end

	/**
	 * Method setEffDate
	 * 
	 * @param effDate
	 */

	public void setEffDate(String effDate) {
		this.effDate = effDate;
	}

	/**
	 * Method setIssueState
	 * 
	 * @param issueState
	 */

	public void setIssueState(String issueState) {
		this.issueState = issueState;
	}

	/**
	 * Method setLob
	 * 
	 * @param lob
	 */

	public void setLob(String lob) {
		this.lob = lob;
	}

	/**
	 * This method sets the the appropriate column(s) on the particular WIP
	 * table from the passed tab-delimited newConcatKey. Creation date:
	 * (10/12/2001 12:49:37 PM)
	 * 
	 * @param newConcatKey
	 */

	public void setNewConcatKey(String newConcatKey) {
		// refactor plan key.
		setDataNew(new PlanRowTO(newConcatKey, ""));
		/*
		setCompanyCode(newConcatKey.substring(0, 3));
		setProductPrefix(newConcatKey.substring(3, 4));
		setProductSuffix(newConcatKey.substring(4, 5));
		setPlanCode(newConcatKey.substring(5, 11));
		setIssueState(newConcatKey.substring(11, 14));
		setLob(newConcatKey.substring(14, 17));
		setEffDate(newConcatKey.substring(17, 27));
		setPlanType(newConcatKey.substring(27, 28));
		setTablePtrId(newConcatKey.substring(28, 31));
		setTablePtrVar(newConcatKey.substring(31, 32));
		setTablePtrSubset(newConcatKey.substring(32, 48));
		*/
	}

	public void setData(PlanRowTO planRow) {
		setDataNew(planRow);
	}
	public void setDataNew(PlanRowTO planRow) {
		if (!planRow.getEnvironment().equals("")) {
			setEnvironment(planRow.getEnvironment());
		}
		setCompanyCode(planRow.getCompanyCode());
		setProductPrefix(planRow.getProductPrefix());
		setProductSuffix(planRow.getProductSuffix());
		setPlanCode(planRow.getPlanCode());
		setIssueState(planRow.getIssueState());
		setLob(planRow.getLineOfBusiness());
		setEffDate(planRow.getEffectiveDate());
		setPlanType(planRow.getPlanType());
		setTablePtrId(planRow.getTablePtrId());
		setTablePtrVar(planRow.getTablePtrVar());
		setTablePtrSubset(planRow.getTablePtrSubset());
	}
	
	public void setDataOld(PlanRowTO planRow) {
		setOldCompanyCode(planRow.getCompanyCode());
		setOldProductPrefix(planRow.getProductPrefix());
		setOldProductSuffix(planRow.getProductSuffix());
		setOldPlanCode(planRow.getPlanCode());
		setOldIssueState(planRow.getIssueState());
		setOldLob(planRow.getLineOfBusiness());
		setOldEffDate(planRow.getEffectiveDate());
		setOldPlanType(planRow.getPlanType());
		setOldPtrId(planRow.getTablePtrId());
		setOldPtrVar(planRow.getTablePtrVar());
		setOldPtrSubset(planRow.getTablePtrSubset());
	}

	/**
	 * This method sets the the appropriate column(s) on the particular WIP
	 * table from the passed tab-delimited newData. Creation date: (10/12/2001
	 * 12:54:40 PM)
	 * 
	 * @param newData
	 */

	public void setNewData(String newData) {
		this.newData = newData;
	}

	/**
	 * Method setPlanCode
	 * 
	 * @param planCode
	 */

	public void setPlanCode(String planCode) {
		this.planCode = planCode;
	}

	/**
	 * Method setPlanType
	 * 
	 * @param planType
	 */

	public void setPlanType(String planType) {
		this.planType = planType;
	}

	/**
	 * Method setProductSuffix
	 * 
	 * @param productSuffix
	 */

	public void setProductSuffix(String productSuffix) {
		this.productSuffix = productSuffix;
	}

	/**
	 * Method setTablePtrId
	 * 
	 * @param tablePtrId
	 */

	public void setTablePtrId(String tablePtrId) {
		this.tablePtrId = tablePtrId;
	}

	/**
	 * Method setTablePtrSubset
	 * 
	 * @param tablePtrSubset
	 */

	public void setTablePtrSubset(String tablePtrSubset) {
		this.tablePtrSubset = tablePtrSubset;
	}

	/**
	 * Method setTablePtrVar
	 * 
	 * @param tablePtrVar
	 */

	public void setTablePtrVar(String tablePtrVar) {
		this.tablePtrVar = tablePtrVar;
	}

	/**
	 * Method setValuesForInsert
	 * 
	 * @param stmt
	 * @throws SQLException
	 */

	protected void setValuesForInsert(PreparedStatement stmt, IWIPRows wiprows)
	        throws SQLException {

		stmt.setString(1, getEnvironment());
		stmt.setString(2, getCompanyCode());
		stmt.setString(3, getProductPrefix());
		stmt.setString(4, getProductSuffix());
		stmt.setString(5, getPlanCode());
		stmt.setString(6, getIssueState());
		stmt.setString(7, getLob());
		stmt.setObject(8, getEffDate());
		stmt.setString(9, getPlanType());
		stmt.setString(10, getTablePtrId());
		stmt.setString(11, getTablePtrVar());
		stmt.setString(12, getTablePtrSubset());
		stmt.setString(13, getOperation());
		stmt.setString(14, getOldCompanyCode());
		stmt.setString(15, getOldProductPrefix());
		stmt.setString(16, getOldProductSuffix());
		stmt.setString(17, getOldPlanCode());
		stmt.setString(18, getOldIssueState());
		stmt.setString(19, getOldLob());
		stmt.setObject(20, getOldEffDate());
		stmt.setString(21, getOldPlanType());
		stmt.setString(22, getOldPtrId());
		stmt.setString(23, getOldPtrVar());
		stmt.setString(24, getOldPtrSubset());
		stmt.setString(25, getProjectName());
		stmt.setString(26, getChangeUserId());
		//SPR 'PROMOTE FILTER FAIL'
		stmt.setString(27, getPromoteUserId());
		stmt.setString(28, getPackageId());
		stmt.setString(29, getErrorIndicator());
		stmt.setString(30, getPromotionLock());
		stmt.setString(31, ((WIPRows) wiprows)
		        .getDBSequenceNumber()
		        + "");
	}

	/**
	 * This method sets the values for the 'Update' PreparedStatement. Columns
	 * are SET based on the current values on this. The WHERE clause is valued
	 * based one of two inputs: 1) Old values on this (change to WIP key), if
	 * available 2) Current values on this (no change to WIP key)
	 * 
	 * @param stmt
	 * @param requestFilter
	 * @throws SQLException
	 */

	protected void setValuesForUpdate(PreparedStatement stmt,
	        WIPTableFilter requestFilter) throws SQLException {

		// SET starts...
		stmt.setString(1, getEnvironment());
		stmt.setString(2, getCompanyCode());
		stmt.setString(3, getProductPrefix());
		stmt.setString(4, getProductSuffix());
		stmt.setString(5, getPlanCode());
		stmt.setString(6, getIssueState());
		stmt.setString(7, getLob());
		stmt.setObject(8, getEffDate());
		stmt.setString(9, getPlanType());
		stmt.setString(10, getTablePtrId());
		stmt.setString(11, getTablePtrVar());
		stmt.setString(12, getTablePtrSubset());
		stmt.setString(13, getOperation());
		stmt.setString(14, getProjectName());
		stmt.setString(15, getChangeUserId());
		stmt.setString(16, getPromoteUserId());
		stmt.setString(17, getPackageId());
		stmt.setString(18, getErrorIndicator());
		stmt.setString(19, getPromotionLock());

		// WHERE starts...
		getOldNewValues();
		stmt.setString(20, getEnvironment()); // not updatable
		stmt.setString(21, getCompanyCode()); // not updatable
		stmt.setString(22, getProductPrefix()); // not updatable
		stmt.setString(23, oldNewSuffix);
		stmt.setString(24, oldNewPlanCode);
		stmt.setString(25, oldNewState);
		stmt.setString(26, oldNewLOB);
		stmt.setObject(27, oldNewEffdt);
		stmt.setString(28, oldNewPlanType);
		stmt.setString(29, oldNewPtrId);
		stmt.setString(30, oldNewPtrVar);
		stmt.setString(31, oldNewPtrSubset);
		stmt.setString(32, getOldCompanyCode());
		stmt.setString(33, getOldProductPrefix());
		stmt.setString(34, getOldProductSuffix());
		stmt.setString(35, getOldPlanCode());
		stmt.setString(36, getOldIssueState());
		stmt.setString(37, getOldLob());
		stmt.setObject(38, getOldEffDate());
		stmt.setString(39, getOldPlanType());
		stmt.setString(40, getOldPtrId());
		stmt.setString(41, getOldPtrVar());
		stmt.setString(42, getOldPtrSubset());
		if (requestFilter != null) {
			stmt.setString(43, getOldErrorInd());
		} else {
			stmt.setString(43, "%_%");
		}
		if (requestFilter != null) {
			stmt.setString(44, requestFilter.isLocked() ? "L" : " ");
		} else {
			stmt.setString(44, getPromotionLock());
		}
	}

	/**
	 * This method sets the values for the 'Delete' PreparedStatement.
	 * 
	 * @param stmt
	 * @param requestFilter
	 * @throws SQLException
	 */

	protected void setValuesForDelete(PreparedStatement stmt,
	        WIPTableFilter requestFilter) throws SQLException {

		stmt.setString(1, getEnvironment());
		stmt.setString(2, getCompanyCode());
		stmt.setString(3, getProductPrefix());
		stmt.setString(4, getProductSuffix());
		stmt.setString(5, getPlanCode());
		stmt.setString(6, getIssueState());
		stmt.setString(7, getLob());
		stmt.setObject(8, getEffDate());
		stmt.setString(9, getPlanType());
		stmt.setString(10, getTablePtrId());
		stmt.setString(11, getTablePtrVar());
		stmt.setString(12, getTablePtrSubset());
		stmt.setString(13, getOldCompanyCode());
		stmt.setString(14, getOldProductPrefix());
		stmt.setString(15, getOldProductSuffix());
		stmt.setString(16, getOldPlanCode());
		stmt.setString(17, getOldIssueState());
		stmt.setString(18, getOldLob());
		stmt.setObject(19, getOldEffDate());
		stmt.setString(20, getOldPlanType());
		stmt.setString(21, getOldPtrId());
		stmt.setString(22, getOldPtrVar());
		stmt.setString(23, getOldPtrSubset());
		if (requestFilter != null) {
			stmt.setString(24, requestFilter.isWithErrors() ? "Y" : " ");
		} else {
			stmt.setString(24, "%_%"); // SPR2345
		}
		if (requestFilter != null) {
			stmt.setString(25, requestFilter.isLocked() ? "L" : " ");
		} else {
			stmt.setString(25, getPromotionLock());
		}
	}

	/* @deprecated use SQLBuilderPLANWIP.buildUnlockStatement()
	public static String getWipUnlockSQL(String envId) {
		Environment environment = EnvironmentManager.getInstance().getEnvironment(envId);
		WIPProperties wipProps = WIPProperties.getInstance();
		StringBuffer sql = new StringBuffer(50);
		sql.append("UPDATE ");
		sql.append(environment.getApplSchema()+ "."
		        + wipProps.getTableName(Constants.PLAN_WIP));
		sql.append(" SET ");
		sql.append(wipProps.getPromotionLock() + " = ' ' ");
		sql.append(" WHERE ");
		sql.append(wipProps.getPromotionLock() + " = 'L' ");
		return sql.toString();
	}
	*/
}
