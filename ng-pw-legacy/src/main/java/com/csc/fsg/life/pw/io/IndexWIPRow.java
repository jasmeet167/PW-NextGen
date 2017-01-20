/*
 * THIS PROGRAM IS THE PROPERTY OF CSC FINANCIAL SERVICES GROUP. IT MAY NOT BE
 * COPIED IN WHOLE OR IN PART WITHOUT THE EXPRESS WRITTEN CONSENT OF CSC
 * FINANCIAL SERVICES GROUP.
 */

package com.csc.fsg.life.pw.io;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Vector;

import com.csc.fsg.life.exceptions.WrapperException;
import com.csc.fsg.life.pw.util.Constants;
import com.csc.fsg.life.pw.util.WIPProperties;
import com.csc.fsg.life.pw.environment.*;
import com.csc.fsg.life.pw.util.SqlPW;

/* Modifications: T0091, CCCV-D506, T0129, T0106, WMA-1550 */

/**
 * This class abstracts a single row from the Index WIP table. Since it knows
 * what the table looks like, it can build SQL to query and update it. Creation
 * date: (10/10/2001 9:40:12 AM)
 * 
 * @author: Carl Ericksen
 */

public class IndexWIPRow extends WIPRow {

	private String parentTableId = "";

	private String parentTableSubset = "";

	private String childTableId = "";

	private String childTableSubset = "";

	private String childTableVar = "";

	// oldNew members are the result of parsing oldNewConcatKey if present
	private String oldNewParentId = "";

	private String oldNewParentSubset = "";

	private String oldNewChildId = "";

	private String oldNewChildSubset = "";

	private String oldNewChildVar = "";


	/**
	 * Constructor for IndexWIPRow. DO NOT USE! Use WIPRowFactory instead.
	 */

	protected IndexWIPRow() {

		super();
		setDdlName(Constants.SUBSET_TABLE_NAME);
		setTableId(Constants.SUBSET_TABLE_ID);
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
	 * @throws WIPRowException
	 */

	public static String buildSelectSQL(WIPTableFilter filter, String orderBy)
	        throws WIPRowException {


		StringBuffer sql = new StringBuffer();
		WIPProperties wipProps = WIPProperties.getInstance();
		Environment environment = EnvironmentManager.getInstance().getEnvironment(filter.getEnvironment());
		sql.append("SELECT * FROM "
		        + environment.getApplSchema() + "."
		        + wipProps.getTableName(Constants.INDEX_WIP));
		sql.append(buildWhereSQL(filter));
		if (orderBy != null) {
			// Same orderBy clause used as for COMMONWIP; 
			// reference to DDLNAME must bew removed, if found
			orderBy = UtilMethods.removeSortColumn(orderBy, "DDLNAME");
			sql.append(" " + orderBy);
		}
		return sql.toString();
	}

	/**
	 * This method creates the where clause based on the passed filter and
	 * orderBy parms. Creation date: (10/25/2001 4:19:43 PM)
	 * 
	 * @param filter
	 *            com.csc.fsg.life.pw.web.io.WIPTableFilter
	 * @return
	 * @throws WIPRowException
	 */

	public static String buildWhereSQL(WIPTableFilter filter)
	        throws WIPRowException {

		//String sqlStr = null;
		StringBuffer sql = new StringBuffer();
		WIPProperties wipProps = WIPProperties.getInstance();

		
			sql.append(" WHERE ");
			sql.append(wipProps.getEnvironment(Constants.INDEX_WIP) + " = '"
			        + filter.getEnvironment() + "' ");
			if (filter.getCompanyCode() != null) {
				sql.append(" AND "
				        + wipProps.getCompanyCode(Constants.INDEX_WIP) + " = '"
				        + filter.getCompanyCode() + "' ");
			}
			if (filter.getProductPrefix() != null) {
				sql.append(" AND "
				        + wipProps.getProductPrefix(Constants.INDEX_WIP)
				        + " = '" + filter.getProductPrefix() + "' ");
			}
			Vector changeUserIds = filter.getChangeUserIds();

			if (changeUserIds != null) {
				if (changeUserIds.size() == 0) {
					throw new WIPRowException("Empty request vector");
				}
				for (int j = 0; j < changeUserIds.size(); j++) {
					if (j == 0) {
						sql.append(" AND ( "
						        + wipProps.getChangeUserId(Constants.INDEX_WIP)
						        + " = '" + (String) changeUserIds.elementAt(j)
						        + "' ");
					} else {
						sql.append(" OR "
						        + wipProps.getChangeUserId(Constants.INDEX_WIP)
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
						        + wipProps.getPackageId(Constants.INDEX_WIP)
						        + " = '" + (String) packageIds.elementAt(k)
						        + "' ");
					} else {
						sql.append(" OR "
						        + wipProps.getPackageId(Constants.INDEX_WIP)
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
						        + wipProps.getProjectName(Constants.INDEX_WIP)
						        + " = '" + (String) projectNames.elementAt(l)
						        + "' ");
					} else {
						sql.append(" OR "
						        + wipProps.getProjectName(Constants.INDEX_WIP)
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
						        + wipProps.getDBSequence(Constants.INDEX_WIP)
						        + " = '" + dbSequence.elementAt(m) + "' ");
					} else {
						sql.append(" OR "
						        + wipProps.getDBSequence(Constants.INDEX_WIP)
						        + " = '" + dbSequence.elementAt(m) + "' ");
					}
				}
				sql.append(" ) ");
			}

			if (filter.isWithErrors()) {
				sql.append(" AND "
				        + wipProps.getErrorIndicator(Constants.INDEX_WIP)
				        + " ='Y'");
			}

			if (filter.isLocked()) {
				sql.append(" AND "
				        + wipProps.getPromotionLock(Constants.INDEX_WIP)
				        + " ='L'");
			}

			if (filter.getParentTableId() != null) {
				sql.append(" AND " + wipProps.getPrimaryTableId() + " = '"
				        + filter.getParentTableId() + "' ");
			}
			if (filter.getParentTableSubset() != null) {
				sql.append(" AND " + wipProps.getPrimaryPtrSubset() + " = '"
				        + filter.getParentTableSubset() + "' ");
			}
			if (filter.getChildTableId() != null) {
				sql.append(" AND " + wipProps.getSecondaryTableId() + " = '"
				        + filter.getChildTableId() + "' ");
			}
			if (filter.getChildTableSubset() != null) {
				sql.append(" AND " + wipProps.getSecondaryPtrSubset() + " = '"
				        + filter.getChildTableSubset() + "' ");
			}
			if (filter.getChildTableVar() != null) {
				sql.append(" AND " + wipProps.getSecondaryTableVar() + " = '"
				        + filter.getChildTableVar() + "' ");
			}
			if (filter.getOldConcatKey() != null) {
				sql.append(" AND " + wipProps.getOldConcatKey() + " = '"
				        + filter.getOldConcatKey() + "' ");
			}

	
		return sql.toString();
	}

	/**
	 * This method builds an delete statement given the passed parameters
	 * Creation date: (11/28/2001 12:38:49 AM)
	 * 
	 * @return String
	 * @param filter
	 *            com.csc.fsg.life.pw.web.io.WIPTableFilter
	 * @throws WIPRowException
	 */

	public static String buildDeleteSQL(WIPTableFilter filter)
	        throws WIPRowException {
	
		StringBuffer sql = new StringBuffer();
		WIPProperties wipProps = WIPProperties.getInstance();
		Environment environment = EnvironmentManager.getInstance().getEnvironment(filter.getEnvironment());
		sql.append("DELETE FROM " + environment.getApplSchema()
		        + "." + wipProps.getTableName(Constants.INDEX_WIP));
		sql.append(buildWhereSQL(filter));
		return sql.toString();
	}

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
	        throws WIPRowException {

		//String sqlStr = null;
		StringBuffer sql = new StringBuffer();
		WIPProperties wipProps = WIPProperties.getInstance();
		Environment environment = EnvironmentManager.getInstance().getEnvironment(filter.getEnvironment());
		
		sql.append("UPDATE " + environment.getApplSchema()
		        + "." + wipProps.getTableName(Constants.INDEX_WIP));
		if (filter.isLocked() == true) {
			// unlock row
			sql.append(" SET "
			        + wipProps.getPromotionLock(Constants.INDEX_WIP)
			        + " = ' '");
		} else {
			// lock row
			sql.append(" SET "
			        + wipProps.getPromotionLock(Constants.INDEX_WIP)
			        + " = 'L'");
		}
		sql.append(buildWhereSQL(filter));
		
		return sql.toString();
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

		StringBuffer sql = new StringBuffer();
		WIPProperties wipProps = WIPProperties.getInstance();
		if (wipAction.equals(Constants.INSERT_OPERATION)) {
			buildSaveInsertSQL(envId,sql, wipProps);
		} else if (wipAction.equals(Constants.UPDATE_OPERATION)) {
			buildSaveUpdateSQL(envId,sql, wipProps);
		} else if (wipAction.equals(Constants.DELETE_OPERATION)) {
			buildSaveDeleteSQL(envId,sql, wipProps);
		}
	
		return sql.toString();
	}

	private static void buildSaveInsertSQL(String envId,StringBuffer sql,
	        WIPProperties wipProps) throws Exception {
		Environment environment = EnvironmentManager.getInstance().getEnvironment(envId);
		sql.append("INSERT INTO ");
		sql.append(environment.getApplSchema() + "."
		        + wipProps.getTableName(Constants.INDEX_WIP));
		sql.append("( " + wipProps.getEnvironment(Constants.INDEX_WIP) + ",");
		sql.append(wipProps.getCompanyCode() + ",");
		sql.append(wipProps.getProductPrefix() + ",");
		sql.append(wipProps.getPrimaryTableId() + ",");
		sql.append(wipProps.getPrimaryPtrSubset() + ",");
		sql.append(wipProps.getSecondaryTableId() + ",");
		sql.append(wipProps.getSecondaryPtrSubset() + ",");
		sql.append(wipProps.getSecondaryTableVar() + ",");
		sql.append(wipProps.getOperation() + ",");
		sql.append(wipProps.getOldConcatKey() + ",");
		sql.append(wipProps.getProjectName() + ",");
		sql.append(wipProps.getChangeUserId() + ",");
		sql.append(wipProps.getPromoteUserId() + ",");
		sql.append(wipProps.getPackageId() + ",");
		sql.append(wipProps.getErrorIndicator() + ",");
		sql.append(wipProps.getPromotionLock() + ",");
		sql.append(wipProps.getDBSequence() + ")");
		sql
		        .append("VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
	}

	private static void buildSaveUpdateSQL(String envId,StringBuffer sql,
	        WIPProperties wipProps) throws Exception {
		Environment environment = EnvironmentManager.getInstance().getEnvironment(envId);
		sql.append("UPDATE ");
		sql.append(environment.getApplSchema() + "."
		        + wipProps.getTableName(Constants.INDEX_WIP));
		sql.append(" SET ");
		sql.append(wipProps.getEnvironment() + " = ?, ");
		sql.append(wipProps.getCompanyCode() + " = ?, ");
		sql.append(wipProps.getProductPrefix() + " = ?, ");
		sql.append(wipProps.getPrimaryTableId() + " = ?, ");
		sql.append(wipProps.getPrimaryPtrSubset() + " = ?, ");
		sql.append(wipProps.getSecondaryTableId() + " = ?, ");
		sql.append(wipProps.getSecondaryPtrSubset() + " = ?, ");
		sql.append(wipProps.getSecondaryTableVar() + " = ?, ");
		sql.append(wipProps.getOperation() + " = ?, ");
		sql.append(wipProps.getProjectName() + " = ?, ");
		sql.append(wipProps.getChangeUserId() + " = ?, ");
		sql.append(wipProps.getPackageId() + " = ?, ");
		sql.append(wipProps.getErrorIndicator() + " = ?, ");
		sql.append(wipProps.getPromotionLock() + " = ?, ");
		sql.append(wipProps.getPromoteUserId() + " = ? ");
		buildSaveWhereSQL(sql, wipProps);
	}

	private static void buildSaveDeleteSQL(String envId,StringBuffer sql,
	        WIPProperties wipProps) throws Exception {
		Environment environment = EnvironmentManager.getInstance().getEnvironment(envId);
		sql.append("DELETE FROM ");
		sql.append(environment.getApplSchema() + "."
		        + wipProps.getTableName(Constants.INDEX_WIP));
		buildSaveWhereSQL(sql, wipProps);
	}

	private static void buildSaveWhereSQL(StringBuffer sql,
	        WIPProperties wipProps) throws Exception {

		sql.append(" WHERE ");
		sql.append(wipProps.getEnvironment() + " = ? ");
		sql.append("AND " + wipProps.getCompanyCode() + " = ? ");
		sql.append("AND " + wipProps.getProductPrefix() + " = ? ");
		sql.append("AND " + wipProps.getPrimaryTableId() + " = ? ");
		sql.append("AND " + wipProps.getPrimaryPtrSubset() + " = ? ");
		sql.append("AND " + wipProps.getSecondaryTableId() + " = ? ");
		sql.append("AND " + wipProps.getSecondaryPtrSubset() + " = ? ");
		sql.append("AND " + wipProps.getSecondaryTableVar() + " = ? ");
		sql.append("AND " + wipProps.getOldConcatKey() + " = ? ");
		sql.append("AND " + wipProps.getErrorIndicator() + " LIKE ? ");
		sql.append("AND " + wipProps.getPromotionLock() + " = ? ");
	}

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

			int count = SqlPW.update(SqlPW.SQL_INDEX_SAVE, stmt);
			if (count == 0) {
				throw new WIPRowException(Constants.ROW_NOT_FOUND_MSG);
			}
		} catch (SQLException e) {
			throw WrapperException.wrap(e);
		}
	}

	/**
	 * This method transforms an IndexWIPRow to into a CommonWIPRow object.
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
		commonRow.setOldData(getOldConcatKey()); // key is same as data
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

		IndexWIPRow iRow = null;
		iRow = (IndexWIPRow) super.clone();
		if (getOriginalData() != null) {
			System.arraycopy(getOriginalData(), 0, iRow.getOriginalData(),
			        0, iRow.getOriginalData().size());
		}
		return iRow;
	}

	/**
	 * ********************* GETTERS **********************
	 * 
	 * @return
	 */

	public String getChildTableId() {
		return childTableId;
	}

	/**
	 * Method getChildTableSubset
	 * 
	 * @return
	 */

	public String getChildTableSubset() {
		return childTableSubset;
	}

	/**
	 * Method getChildTableVar
	 * 
	 * @return
	 */

	public String getChildTableVar() {
		return childTableVar;
	}

	/**
	 * Method getOldConcatKey
	 * 
	 * @return
	 */

	public String getOldConcatKey() {
		return oldConcatKey;
	}

	/**
	 * This method builds the newConcatKey from the appropriate column(s) on the
	 * particular WIP table. Creation date: (10/12/2001 12:49:37 PM)
	 * 
	 * @return String
	 */

	public String getNewConcatKey() {
		return getNewData(); // key and data are the same for T000XA row
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
		        .getTableDescriptor(Constants.SUBSET_TABLE_ID);
		Vector<ColumnDescriptor> columndescriptors = td.getColumnDescriptors();
		int size = columndescriptors.size();

		for (int count = 0; count < size; count++) {
			ColumnDescriptor cd = columndescriptors
			        .get(count);

			originalColumnLength = cd.getColumnSize();
			switch (count) {

				case 0:
					columnValue = getCompanyCode();
					break;
				case 1:
					columnValue = getProductPrefix();
					break;
				case 2:
					columnValue = getParentTableId();
					break;
				case 3:
					columnValue = getParentTableSubset();
					break;
				case 4:
					columnValue = getChildTableId();
					break;
				case 5:
					columnValue = getChildTableSubset();
					break;
				case 6:
					columnValue = getChildTableVar();
					break;

			}

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

	/**
	 * Method getParentTableId
	 * 
	 * @return
	 */

	public String getParentTableId() {
		return parentTableId;
	}

	/**
	 * Method getParentTableSubset
	 * 
	 * @return
	 */

	public String getParentTableSubset() {
		return parentTableSubset;
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
				oldNewParentId = getParentTableId();
				oldNewParentSubset = getParentTableSubset();
				oldNewChildId = getChildTableId();
				oldNewChildSubset = getChildTableSubset();
				oldNewChildVar = getChildTableVar();
			} else // parse the oldNewConcatKey into distinct fields
			{

				oldNewParentId = oldNewConcatKey.substring(4, 7);
				oldNewParentSubset = oldNewConcatKey.substring(7, 23);
				oldNewChildId = oldNewConcatKey.substring(23, 26);
				oldNewChildSubset = oldNewConcatKey.substring(26, 42);
				oldNewChildVar = oldNewConcatKey.substring(42, 43);
			}
	
	}

	/**
	 * ********************* SETTERS **********************
	 * 
	 * @param newChildTableId
	 */

	public void setChildTableId(String newChildTableId) {
		childTableId = newChildTableId;
	}

	/**
	 * Method setChildTableSubset
	 * 
	 * @param newChildTableSubset
	 */

	public void setChildTableSubset(String newChildTableSubset) {
		childTableSubset = newChildTableSubset;
	}

	/**
	 * Method setChildTableVar
	 * 
	 * @param newChildTableVar
	 */

	public void setChildTableVar(String newChildTableVar) {
		childTableVar = newChildTableVar;
	}

	/**
	 * This method sets the the appropriate column(s) on the particular WIP
	 * table from the passed tab-delimited newConcatKey. Creation date:
	 * (10/12/2001 12:49:37 PM)
	 * 
	 * @param newNewConcatKey
	 *            String
	 */

	public void setNewConcatKey(String newNewConcatKey) {

		setCompanyCode(newNewConcatKey.substring(0, 3));
		setProductPrefix(newNewConcatKey.substring(3, 4));
		setParentTableId(newNewConcatKey.substring(4, 7));
		setParentTableSubset(newNewConcatKey.substring(7, 23));
		setChildTableId(newNewConcatKey.substring(23, 26));
		setChildTableSubset(newNewConcatKey.substring(26, 42));
		setChildTableVar(newNewConcatKey.substring(42, 43));
	
	}

	/**
	 * This method sets the the appropriate column(s) on the particular WIP
	 * table from the passed tab-delimited newData. Creation date: (10/12/2001
	 * 12:54:40 PM)
	 * 
	 * @param newNewData
	 *            String
	 */

	public void setNewData(String newNewData) {
		this.newData = newNewData;
	}

	/**
	 * Method setParentTableId
	 * 
	 * @param newParentTableId
	 */

	public void setParentTableId(String newParentTableId) {
		parentTableId = newParentTableId;
	}

	/**
	 * Method setParentTableSubset
	 * 
	 * @param newParentTableSubset
	 */

	public void setParentTableSubset(String newParentTableSubset) {
		parentTableSubset = newParentTableSubset;
	}

	/**
	 * Method setValuesForInsert
	 * 
	 * @param stmt
	 * @throws SQLException
	 */

	protected void setValuesForInsert(PreparedStatement stmt, IWIPRows wipRows)
	        throws SQLException {

		stmt.setString(1, getEnvironment());
		stmt.setString(2, getCompanyCode());
		stmt.setString(3, getProductPrefix());
		stmt.setString(4, getParentTableId());
		stmt.setString(5, getParentTableSubset());
		stmt.setString(6, getChildTableId());
		stmt.setString(7, getChildTableSubset());
		stmt.setString(8, getChildTableVar());
		stmt.setString(9, getOperation());
		stmt.setString(10, getOldConcatKey());
		stmt.setString(11, getProjectName());
		stmt.setString(12, getChangeUserId());
		stmt.setString(13, getPromoteUserId());
		stmt.setString(14, getPackageId());
		stmt.setString(15, getErrorIndicator());
		stmt.setString(16, getPromotionLock());
		stmt.setString(17, ((WIPRows) wipRows)
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
		stmt.setString(4, getParentTableId());
		stmt.setString(5, getParentTableSubset());
		stmt.setString(6, getChildTableId());
		stmt.setString(7, getChildTableSubset());
		stmt.setString(8, getChildTableVar());
		stmt.setString(9, getOperation());
		stmt.setString(10, getProjectName());
		stmt.setString(11, getChangeUserId());
		stmt.setString(12, getPackageId());
		stmt.setString(13, getErrorIndicator());
		stmt.setString(14, getPromotionLock());
		stmt.setString(15, getPromoteUserId());

		// WHERE starts...
		getOldNewValues();
		stmt.setString(16, getEnvironment()); // not updatable
		stmt.setString(17, getCompanyCode()); // not updatable
		stmt.setString(18, getProductPrefix()); // not updatable
		stmt.setString(19, oldNewParentId);
		stmt.setString(20, oldNewParentSubset);
		stmt.setString(21, oldNewChildId);
		stmt.setString(22, oldNewChildSubset);
		stmt.setString(23, oldNewChildVar);
		stmt.setString(24, getOldConcatKey()); // not updatable
		if (requestFilter != null) {
			stmt.setString(25, getOldErrorInd());
		} else {
			stmt.setString(25, "%_%");
		}
		if (requestFilter != null) {
			stmt.setString(26, requestFilter.isLocked() ? "L" : " ");
		} else {
			stmt.setString(26, getPromotionLock());
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
		stmt.setString(4, getParentTableId());
		stmt.setString(5, getParentTableSubset());
		stmt.setString(6, getChildTableId());
		stmt.setString(7, getChildTableSubset());
		stmt.setString(8, getChildTableVar());
		stmt.setString(9, getOldConcatKey());
		if (requestFilter != null) {
			stmt.setString(10, requestFilter.isWithErrors() ? "Y" : " ");
		} else {
			stmt.setString(10, "%_%"); // SPR2345
		}
		if (requestFilter != null) {
			stmt.setString(11, requestFilter.isLocked() ? "L" : " ");
		} else {
			stmt.setString(11, getPromotionLock());
		}
	}

	public static String getWipUnlockSQL(String envId) {
		Environment environment = EnvironmentManager.getInstance().getEnvironment(envId);
		WIPProperties wipProps = WIPProperties.getInstance();
		StringBuffer sql = new StringBuffer(50);
		sql.append("UPDATE ");
		sql.append(environment.getApplSchema() + "."
		        + wipProps.getTableName(Constants.INDEX_WIP));
		sql.append(" SET ");
		sql.append(wipProps.getPromotionLock() + " = ' ' ");
		sql.append(" WHERE ");
		sql.append(wipProps.getPromotionLock() + " = 'L' ");
		return sql.toString();
	}
}
