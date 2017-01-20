/*
 * THIS PROGRAM IS THE PROPERTY OF CSC FINANCIAL SERVICES GROUP. IT MAY NOT BE
 * COPIED IN WHOLE OR IN PART WITHOUT THE EXPRESS WRITTEN CONSENT OF CSC
 * FINANCIAL SERVICES GROUP.
 */

package com.csc.fsg.life.pw.io;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Vector;

import com.csc.fsg.life.common.util.CommonASCIIToEBCDICHelper;
import com.csc.fsg.life.exceptions.WrapperException;
import com.csc.fsg.life.pw.util.Constants;
import com.csc.fsg.life.pw.util.WIPProperties;
import com.csc.fsg.life.pw.rules.BaseRule;
import com.csc.fsg.life.pw.rules.IDataAccess;
import com.csc.fsg.life.pw.rules.RulesMgr;
import com.csc.fsg.life.pw.rules.StringRule;
import com.csc.fsg.life.pw.config.ProductManager;
import com.csc.fsg.life.pw.config.ProductObject;
import com.csc.fsg.life.pw.environment.*;
import com.csc.fsg.life.pw.util.*;

/* Modifications: T0091, CCCV-D506, T0129, T0106, ENH1063-05 */

/**
 * This class abstracts a single row from the Common WIP table. Since it knows
 * what the table looks like, it can build SQL to query and update it. Creation
 * date: (10/10/2001 9:40:12 AM)
 * 
 * @author: Carl Ericksen
 */

public class CommonWIPRow extends WIPRow {

	private String ebcdicKey = null;

	/**
	 * Constructor for CommonWIPRow. DO NOT USE! Use WIPRowFactory instead.
	 */

	protected CommonWIPRow() {
		super();
	}

	/**
	 * This method builds a selects statement given the passed parameters.
	 * Creation date: (10/11/2001 1:18:49 PM)
	 * 
	 * @return String
	 * @param filter
	 *            com.csc.fsg.life.pw.web.io.WIPTableFilter
	 * @param orderBy
	 *            String
	 * @throws WIPRowException
	 */

	public static String buildSelectSQL(WIPTableFilter filter, String orderBy)
	        throws Exception {

		//String sqlStr = null;
		StringBuffer sql = new StringBuffer();
		WIPProperties wipProps = WIPProperties.getInstance();
		boolean isPagingMode = filter.hasPagingModeSet();
		Environment environment = EnvironmentManager.getInstance().getEnvironment(filter.getEnvironment());
	
		sql.append("SELECT * FROM "
		        + environment.getApplSchema() + "."
		        + wipProps.getTableName(Constants.COMMON_WIP));
		sql.append(buildWhereSQL(filter));
		if (isPagingMode)
			orderBy = getPagingQuery(filter);
		else if (orderBy == null)
			orderBy = " ORDER BY EBCDIC_KEY ASC ";

		sql.append(" " + orderBy);
	
		return  sql.toString();
	}

	private static String getPagingQuery(WIPTableFilter filter)
	        throws Exception {
		StringBuffer pageBuffer = new StringBuffer();
		boolean isNext = filter.isPagingNext();
		String ebcKey = filter.getEbcdicKey();
		String locatorColumns = filter.getLocator();
		WIPProperties wipProps = WIPProperties.getInstance();
		if (locatorColumns != null && ebcKey == null) {
			Row row = new RowManager(filter.getEnvironment())
			        .getBlankRow((filter.getTableIds().elementAt(0)));
			row.setColumns(locatorColumns);
			ebcKey = row.getEBCDICKey(true);
		}
		pageBuffer.append(" AND ( ").append(wipProps.getEBCDICKey());
		if (isNext)
			if (locatorColumns != null)
				pageBuffer.append(" >= ");
			else
				pageBuffer.append(" > ");
		else
			pageBuffer.append(" < ");

		if ( EnvironmentManager.getInstance().getEnvironment(filter.getEnvironment()).isNexEnv())
			pageBuffer.append("'" + ebcKey + "' ) ");
		else
			pageBuffer.append("x'" + ebcKey + "' ) ");
		

		pageBuffer.append(" ORDER BY EBCDIC_KEY ");

		if (isNext)
			pageBuffer.append(" ASC ");
		else
			pageBuffer.append(" DESC ");

		return pageBuffer.toString();

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
	        {

		WIPProperties wipProps = WIPProperties.getInstance();

		StringBuffer sql = new StringBuffer();
		sql.append(" WHERE ");
		sql.append(wipProps.getEnvironment() + " = '" + filter.getEnvironment()
		        + "' ");

		if (filter.getCompanyCode() != null)
			sql.append(" AND " + wipProps.getCompanyCode() + " = '"
			        + filter.getCompanyCode() + "' ");

		if (filter.getProductPrefix() != null)
			sql.append(" AND " + wipProps.getProductPrefix() + " = '"
			        + filter.getProductPrefix() + "' ");

		if (filter.getSubsetName() != null)
			sql.append(" AND " + wipProps.getSubsetName() + " = '"
			        + filter.getSubsetName() + "' ");

		if (filter.isWithErrors()) {
			sql.append(" AND " + wipProps.getErrorIndicator() + " ='Y'");
		}

		if (filter.isLocked()) {
			sql.append(" AND " + wipProps.getPromotionLock() + " = 'L'");
		} else {
			sql.append(" AND " + wipProps.getPromotionLock() + " <> 'C'");
		}

		Vector tableIds = filter.getTableIds();

		if (tableIds != null && !tableIds.isEmpty()) {

			String tableId = null;
			int count = 0;
			boolean tableIdSql = false; // STP TT SPR#600

			for (int i = 0; i < tableIds.size(); i++) {
				tableId = (String) tableIds.elementAt(i);
				if (!tableId.equals(Constants.TABLE_ZERO_ID)
				        && !tableId.equals(Constants.SUBSET_TABLE_ID)) {

					if (count == 0) {
						tableIdSql = true;
						sql.append(" AND ( " + wipProps.getTableId() + " = '"
						        + tableId + "' ");
					} else {
						sql.append(" OR " + wipProps.getTableId() + " = '"
						        + tableId + "' ");
					}
					count++;
				}
			}
			if (tableIdSql == true) {
				sql.append(" ) ");
			}
		}

		Vector ddlNames = filter.getDdlNames();
		boolean ddlNamesSql = false; // STP TT SPR#600

		if (ddlNames != null && !ddlNames.isEmpty()) {

			String ddlName = null;
			int count = 0;

			for (int i = 0; i < ddlNames.size(); i++) {
				ddlName = (String) ddlNames.elementAt(i);

				if (!ddlName.equals(Constants.TABLE_ZERO_NAME)
				        && !ddlName.equals(Constants.SUBSET_TABLE_NAME)) {
					if (count == 0) {
						ddlNamesSql = true;
						sql.append(" AND ( " + wipProps.getDDLName() + " = '"
						        + ddlName + "' ");
					} else {
						sql.append(" OR " + wipProps.getDDLName() + " = '"
						        + ddlName + "' ");
					}
					count++;
				}
			}
			if (ddlNamesSql == true) {
				sql.append(" ) ");
			}
		}

		Vector changeUserIds = filter.getChangeUserIds();

		if (changeUserIds != null && !changeUserIds.isEmpty()) {

			for (int j = 0; j < changeUserIds.size(); j++) {
				if (j == 0) {
					sql.append(" AND ( " + wipProps.getChangeUserId() + " = '"
					        + (String) changeUserIds.elementAt(j) + "' ");
				} else {
					sql.append(" OR " + wipProps.getChangeUserId() + " = '"
					        + (String) changeUserIds.elementAt(j) + "' ");
				}
			}
			sql.append(" ) ");
		}

		Vector packageIds = filter.getPackageIds();

		if (packageIds != null && !packageIds.isEmpty()) {

			for (int k = 0; k < packageIds.size(); k++) {
				if (k == 0) {
					sql.append(" AND ( " + wipProps.getPackageId() + " = '"
					        + (String) packageIds.elementAt(k) + "' ");
				} else {
					sql.append(" OR " + wipProps.getPackageId() + " = '"
					        + (String) packageIds.elementAt(k) + "' ");
				}
			}
			sql.append(" ) ");
		}

		Vector projectNames = filter.getProjectNames();

		if (projectNames != null && !projectNames.isEmpty()) {

			for (int l = 0; l < projectNames.size(); l++) {
				if (l == 0) {
					sql.append(" AND ( " + wipProps.getProjectName() + " = '"
					        + (String) projectNames.elementAt(l) + "' ");
				} else {
					sql.append(" OR " + wipProps.getProjectName() + " = '"
					        + (String) projectNames.elementAt(l) + "' ");
				}
			}
			sql.append(" ) ");
		}

		Vector dbSequence = filter.getDbSequence();

		if (dbSequence != null && !dbSequence.isEmpty()) {

			for (int m = 0; m < dbSequence.size(); m++) {
				if (m == 0) {
					sql.append(" AND ( "
					        + wipProps.getDBSequence(Constants.COMMON_WIP)
					        + " = '" + dbSequence.elementAt(m) + "' ");
				} else {
					sql.append(" OR "
					        + wipProps.getDBSequence(Constants.COMMON_WIP)
					        + " = '" + dbSequence.elementAt(m) + "' ");
				}
			}
			sql.append(" ) ");
		}

		// TT wma SPR 6579 - consider old concat key
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
	         {

		StringBuffer sql =  new StringBuffer();
		WIPProperties wipProps = WIPProperties.getInstance();
		Environment environment = EnvironmentManager.getInstance().getEnvironment(filter.getEnvironment());
		sql.append("DELETE FROM "
		        + environment.getApplSchema() + "."
		        + wipProps.getTableName(Constants.COMMON_WIP));
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
	         {

		String sqlStr = null;
		StringBuffer sql = new StringBuffer();
		WIPProperties wipProps = WIPProperties.getInstance();
		Environment environment = EnvironmentManager.getInstance().getEnvironment(filter.getEnvironment());
	
		sql.append("UPDATE " + environment.getApplSchema()
		        + "." + wipProps.getTableName(Constants.COMMON_WIP));
		if (filter.isLocked()) {
			// unlock row
			sql.append(" SET " + wipProps.getPromotionLock() + " = ' '");
		} else {
			// lock row
			sql.append(" SET " + wipProps.getPromotionLock() + " = 'L'");
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
	 *            String
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
		        + wipProps.getTableName(Constants.COMMON_WIP));
		sql.append(" ( " + wipProps.getEnvironment() + ",");
		sql.append(wipProps.getDDLName() + ",");
		sql.append(wipProps.getTableId() + ",");
		sql.append(wipProps.getCompanyCode() + ",");
		sql.append(wipProps.getProductPrefix() + ",");
		// sql.append(wipProps.getProductSuffix() + ",");
		sql.append(wipProps.getSubsetName() + ",");
		sql.append(wipProps.getNewConcatKey() + ",");
		sql.append(wipProps.getNewData() + ",");
		sql.append(wipProps.getOperation() + ",");
		sql.append(wipProps.getOldConcatKey() + ",");
		sql.append(wipProps.getOldWIPData() + ",");
		sql.append(wipProps.getProjectName() + ",");
		sql.append(wipProps.getChangeUserId() + ",");
		sql.append(wipProps.getPromoteUserId() + ",");
		sql.append(wipProps.getPackageId() + ",");
		sql.append(wipProps.getErrorIndicator() + ",");
		sql.append(wipProps.getPromotionLock() + ",");
		sql.append(wipProps.getDBSequence() + ",");
		sql.append(wipProps.getEBCDICKey() + ")");
		sql
		        .append("VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
	}

	private static void buildSaveUpdateSQL(String envId,StringBuffer sql,
	        WIPProperties wipProps) throws Exception {

		Environment environment = EnvironmentManager.getInstance().getEnvironment(envId);
		sql.append("UPDATE ");
		sql.append(environment.getApplSchema() + "."
		        + wipProps.getTableName(Constants.COMMON_WIP));
		sql.append(" SET ");
		sql.append(wipProps.getEnvironment() + " = ?, ");
		sql.append(wipProps.getDDLName() + " = ?, ");
		sql.append(wipProps.getTableId() + " = ?, ");
		sql.append(wipProps.getCompanyCode() + " = ?, ");
		sql.append(wipProps.getProductPrefix() + " = ?, ");
//		sql.append(wipProps.getProductSuffix() + " = ?, ");
		sql.append(wipProps.getSubsetName() + " = ?, ");
		sql.append(wipProps.getNewConcatKey() + " = ?, ");
		sql.append(wipProps.getNewData() + " = ?, ");
		sql.append(wipProps.getOperation() + " = ?, ");
		sql.append(wipProps.getProjectName() + " = ?, ");
		sql.append(wipProps.getChangeUserId() + " = ?, ");
		sql.append(wipProps.getPromoteUserId() + " = ?, ");
		sql.append(wipProps.getPackageId() + " = ?, ");
		sql.append(wipProps.getErrorIndicator() + " = ?, ");
		sql.append(wipProps.getPromotionLock() + " = ? ");
		buildSaveWhereSQL(sql, wipProps);
	}

	private static void buildSaveDeleteSQL(String envId,StringBuffer sql,
	        WIPProperties wipProps) throws Exception {
		Environment environment = EnvironmentManager.getInstance().getEnvironment(envId);
		sql.append("DELETE FROM ");
		sql.append(environment.getApplSchema() + "."
		        + wipProps.getTableName(Constants.COMMON_WIP));
		buildSaveWhereSQL(sql, wipProps);
	}

	private static void buildSaveWhereSQL(StringBuffer sql,
	        WIPProperties wipProps) throws Exception {

		sql.append(" WHERE ");
		sql.append(wipProps.getEnvironment() + " = ? ");
		sql.append("AND " + wipProps.getDDLName() + " = ? ");
		sql.append("AND " + wipProps.getCompanyCode() + " = ? ");
		sql.append("AND " + wipProps.getProductPrefix() + " = ? ");
		sql.append("AND " + wipProps.getSubsetName() + " = ? ");
		sql.append("AND " + wipProps.getNewConcatKey() + " = ? ");
		sql.append("AND " + wipProps.getOldConcatKey() + " = ? ");
		sql.append("AND " + wipProps.getErrorIndicator() + " LIKE ? ");
		sql.append("AND " + wipProps.getPromotionLock() + " = ? ");
	}

	/**
	 * getWipUnlockSQL() builds SQL to unlock all rows in WIP at server startup
	 */
	public static String getWipUnlockSQL(String envId) {
		Environment environment = EnvironmentManager.getInstance().getEnvironment(envId);
		WIPProperties wipProps = WIPProperties.getInstance();
		StringBuffer sql = new StringBuffer(50);
		sql.append("UPDATE ");
		sql.append(environment.getApplSchema() + "."
		        + wipProps.getTableName(Constants.COMMON_WIP));
		sql.append(" SET ");
		sql.append(wipProps.getPromotionLock() + " = ' ' ");
		sql.append(" WHERE ");
		sql.append(wipProps.getPromotionLock() + " = 'L' ");
		return sql.toString();
	}

	/**
	 * getWipRollbackSQL builds SQL to rollback previous multipage write
	 * operations for a failed process (IE. Clone A Plan). WARNING: If you pass
	 * a sequence less than 0, all cache rows will be deleted, regardless of
	 * sequence number. This could unintentionally delete another rows from
	 * another processing thread!
	 * 
	 * @param sequence
	 * @return sql
	 */
	public static String getWipRollbackSQL(String envId,long sequence) {
		Environment environment = EnvironmentManager.getInstance().getEnvironment(envId);
		WIPProperties wipProps = WIPProperties.getInstance();
		StringBuffer sql = new StringBuffer(50);
		sql.append("DELETE FROM ");
		sql.append(environment.getApplSchema() + "."
		        + wipProps.getTableName(Constants.COMMON_WIP));
		sql.append(" WHERE ");
		sql.append(wipProps.getPromotionLock() + " = \'C\' ");
		if (sequence > 0)
			sql.append("AND " + wipProps.getPromoteUserId() + " = \'"
			        + sequence + "\' ");
		return sql.toString();
	}

	/**
	 * getWipCommitSQL builds SQL to commit a successful multipage write
	 * operation for a large process (IE. Clone A Plan). WARNING: If you pass a
	 * sequence less than 0, all cache rows will be updated, regardless of
	 * sequence number. This could unitentionally commit another rows from
	 * another processing thread!
	 * 
	 * @param sequence
	 * @return sql
	 */
	public static String getWipCommitSQL(String envId,long sequence) {
		Environment environment = EnvironmentManager.getInstance().getEnvironment(envId);
		WIPProperties wipProps = WIPProperties.getInstance();
		StringBuffer sql = new StringBuffer(50);
		sql.append("UPDATE ");
		sql.append(environment.getApplSchema() + "."
		        + wipProps.getTableName(Constants.COMMON_WIP));
		sql.append(" SET ");
		sql.append(wipProps.getPromotionLock() + " = \'\' ");
		sql.append(", " + wipProps.getPromoteUserId() + " = \'\'");
		sql.append(" WHERE ");
		sql.append(wipProps.getPromotionLock() + " = \'C\' ");
		sql.append("AND " + wipProps.getPromoteUserId() + " = \'" + sequence
		        + "\' ");
		return sql.toString();
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
			int sqlTag = SqlPW.SQL_OTHER;
			if (action.equals(Constants.INSERT_OPERATION)) {
				setValuesForInsert(stmt, wipRows);
				sqlTag = SqlPW.SQL_WIP_INSERT;
			} else if (action.equals(Constants.UPDATE_OPERATION)) {
				setValuesForUpdate(stmt, wipRows.getRequestFilter());
				sqlTag = SqlPW.SQL_WIP_UPDATE;
			} else if (action.equals(Constants.DELETE_OPERATION)) {
				setValuesForDelete(stmt, wipRows.getRequestFilter());
				sqlTag = SqlPW.SQL_WIP_DELETE;
			}

			int count = SqlPW.update(sqlTag, stmt);

			if (count == 0) {
				throw new WIPRowException(Constants.ROW_NOT_FOUND_MSG);
			}
		} catch (Exception e) {
			String message = "";

			if (e.getMessage().indexOf("-803") != -1) {
				message = Constants.DUPLICATE_ROW_MSG;
			} else {
				message = e.getMessage();
			}
			/** SPR 4658*/
			throw WrapperException.wrap(e, message);
		}
	}

	/**
	 * This method is an implementation of a required abstract methold. It
	 * transforms a particular WIPRow to into a CommonWIPRow object; In this
	 * case, itself. Creation date: (10/12/2001 1:32:03 PM)
	 * 
	 * @return com.csc.fsg.life.pw.web.io.CommonWIPRow
	 */

	public CommonWIPRow transformToCommon() {
		return this;
	}

	/**
	 * This method returns a deep clone of the target WIPRow.
	 * 
	 * @return
	 * @throws CloneNotSupportedException
	 */

	public Object clone() throws CloneNotSupportedException {

		CommonWIPRow cRow = null;

		
			cRow = (CommonWIPRow) super.clone();
			if (getOriginalData() != null) {
				System.arraycopy(getOriginalData(), 0, cRow.getOriginalData(),
				        0, cRow.getOriginalData().size());
			}
		
		return cRow;
	}

	/**
	 * Method getNewConcatKey
	 * 
	 * @return
	 */

	public String getNewConcatKey() {
		return newConcatKey;
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
	 * Method getOldConcatKey
	 * 
	 * @return
	 */

	public String getOldConcatKey() {
		return oldConcatKey;
	}

	/**
	 * Method setNetConcatKey
	 * 
	 * @param newNewConcatKey
	 */

	public void setNewConcatKey(String newNewConcatKey) {
		newConcatKey = newNewConcatKey;
	}

	private String createEBCDICKey() throws Exception {

		String oldConcatKey = HighValueHandler.convertAsteriskToHV(
		        getOldConcatKey(), getEnvironment());

		if ( EnvironmentManager.getInstance().getEnvironment(getEnvironment()).isNexEnv())
			return oldConcatKey;

		Vector<ColumnDescriptor> keyColDescs = EnvironmentManager.getInstance().getEnvironment(getEnvironment()).getTableDescMgr()
		        .getTableDescriptor(getTableId()).getKeyColumnDescriptors();

		int start = 0;
		int end = 0;

		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < keyColDescs.size(); i++) {

			ColumnDescriptor cd = keyColDescs.elementAt(i);
			String dataType = cd.getStringDataType();
			int size = cd.getColumnSize();

			if (dataType.endsWith("NUMERIC"))
				size++;

			end = start + size;
			String data = oldConcatKey.substring(start, end);
			byte[] ebcdicDataArray = CommonASCIIToEBCDICHelper.convertData(
			        dataType, size, 0, data);
			/**V-E231*/ 
			sb.append(new String(ebcdicDataArray,HighValueHandler.getEncoding(EnvironmentManager.getInstance().getEnvironment(getEnvironment()))));
			start = end;
		}

		return sb.toString();
	}

	/**
	 * Method setNewData
	 * 
	 * @param newNewData
	 */

	public void setNewData(String newNewData) {
		newData = newNewData;
	}

	/**
	 * Method setValuesForInsert
	 * 
	 * @param stmt
	 * @throws SQLException
	 */

	protected void setValuesForInsert(PreparedStatement stmt, IWIPRows wiprows)
	        throws Exception {

		stmt.setString(1, getEnvironment());
		stmt.setString(2, getDdlName());
		stmt.setString(3, getTableId());
		stmt.setString(4, getCompanyCode());
		stmt.setString(5, getProductPrefix());
		// stmt.setString(6, getProductSuffix());
		stmt.setString(6, getSubsetName());
		stmt.setString(7, getNewConcatKey());
		stmt.setString(8, getNewData());
		stmt.setString(9, getOperation());
		stmt.setString(10, getOldConcatKey());
		stmt.setString(11, getOldData());
		stmt.setString(12, getProjectName());
		stmt.setString(13, getChangeUserId());
		stmt.setString(14, getPromoteUserId());
		stmt.setString(15, getPackageId());
		stmt.setString(16, getErrorIndicator());
		stmt.setString(17, getPromotionLock());
		stmt.setString(18, ((WIPRows) wiprows)
		        .getDBSequenceNumber()+"");
		/**V-E231 */ 
		stmt.setBytes(19, HighValueHandler.encode(createEBCDICKey(), EnvironmentManager.getInstance().getEnvironment(getEnvironment())));
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
		stmt.setString(2, getDdlName());
		stmt.setString(3, getTableId());
		stmt.setString(4, getCompanyCode());
		stmt.setString(5, getProductPrefix());

		stmt.setString(6, getSubsetName());
		stmt.setString(7, getNewConcatKey());
		stmt.setString(8, getNewData());
		stmt.setString(9, getOperation());
		stmt.setString(10, getProjectName());
		stmt.setString(11, getChangeUserId());
		stmt.setString(12, getPromoteUserId());
		stmt.setString(13, getPackageId());
		stmt.setString(14, getErrorIndicator());
		stmt.setString(15, getPromotionLock());

		// WHERE starts...
		stmt.setString(16, getEnvironment());
		stmt.setString(17, getDdlName());
		stmt.setString(18, getCompanyCode());
		stmt.setString(19, getProductPrefix());
		stmt.setString(20, getSubsetName());
		if (getOldNewConcatKey() != null 
		&& !getOldNewConcatKey().equals("")) {
			stmt.setString(21, getOldNewConcatKey());
		} else {
			stmt.setString(21, getNewConcatKey());
		}
		stmt.setString(22, getOldConcatKey());
		if (requestFilter != null) {
			stmt.setString(23, getOldErrorInd());
		} else {
			stmt.setString(23, "%_%");
		}
		if (requestFilter != null) {
			stmt.setString(24, requestFilter.isLocked() ? "L" : " ");
		} else {
			stmt.setString(24, getPromotionLock());
		}
	}

	/**
	 * This method sets the values for the 'Delete' PreparedStatement. 1) Old
	 * values on this (change to WIP key), if available 2) Current values on
	 * this (no change to WIP key)
	 * 
	 * @param stmt
	 * @param requestFilter
	 * @throws SQLException
	 */

	protected void setValuesForDelete(PreparedStatement stmt,
	        WIPTableFilter requestFilter) throws SQLException {

		stmt.setString(1, getEnvironment());
		stmt.setString(2, getDdlName());
		stmt.setString(3, getCompanyCode());
		stmt.setString(4, getProductPrefix());
		stmt.setString(5, getSubsetName());
		stmt.setString(6, getNewConcatKey());
		stmt.setString(7, getOldConcatKey());
		if (requestFilter != null) {
			stmt.setString(8, requestFilter.isWithErrors() ? "Y" : " ");
		} else {
			stmt.setString(8, "%_%"); // SPR2345
		}
		if (requestFilter != null) {
			stmt.setString(9, requestFilter.isLocked() ? "L" : " ");
		} else {
			stmt.setString(9, getPromotionLock());
		}
	}

	public String getEbcdicKey(boolean dbVersion) throws Exception {
		
		if ( EnvironmentManager.getInstance().getEnvironment(getEnvironment()).isNexEnv())
			return HighValueHandler.convertAsteriskToHV(getOldConcatKey(),getEnvironment());
		
		if (dbVersion)
			return ebcdicKey;
		else
			return createEBCDICKey();
	}

	public void setEbcdicKey(String string) {
		ebcdicKey = string;
	}

	public boolean isDeleted() {
		return getOperation().equals(Constants.DELETE_OPERATION);
	}

	public boolean isUpdated() {
		return getOperation().equals(Constants.UPDATE_OPERATION);
	}

	public boolean isInserted() {
		return getOperation().equals(Constants.INSERT_OPERATION);
	}

	public boolean hasCrossEditErrors(StringBuffer errors) throws Exception {
		boolean hasErrors = false;
		
		if ( getOperation().equals(Constants.DELETE_OPERATION) )
			return false;
		
		try {
			Environment env = EnvironmentManager.getInstance().getEnvironment(getEnvironment());
			TableDescriptor td = env.getTableDescMgr().getTableDescriptor(getTableId());
			RulesMgr rulesMgr = new RulesMgr(td.getTableName());
			IDataAccess da = new DataAccessObj(env, this);
			
			Vector<ColumnDescriptor> cds = td.getColumnDescriptors();
			for (ColumnDescriptor cd : cds) {
				boolean hidden = false;
				if ( td.hasProductPrefix() ) {
					String productPrefix = StringRule.getEditValue(da, 0, "PRODUCT_PREFIX");
					int attrmask = cd.getAttrMask(productPrefix);
					if ( (attrmask & Constants.TABLE_ATTR_HIDDEN) != 0 )
						hidden = true;
				}
				
				if ( !hidden ) {
					try {
						BaseRule rule = rulesMgr.getRules(cd.getDbColumnName());
						if ( rule != null ) {
							if ( !rule.check(0, da) ) {
								hasErrors = true;
								if ( errors.length() > 0 )
									errors.append("\n");
								errors.append(rule.getErrorMessage());
							}
						}
					} catch ( Throwable t ) {
						// no rule for this column 
					}
				}
			}
		} catch ( Throwable t ) {
			_log.error("", t);
		}
		
		return hasErrors;
	}
	
	static class DataAccessObj implements IDataAccess {
		
		private Environment environment = null;
		private CommonWIPRow wipRow = null;
		private Row row = null;
		private TableDescriptor td = null;
		
		public DataAccessObj(Environment environment, CommonWIPRow wipRow) {
			this.environment = environment;
			this.wipRow = wipRow;
			td = environment.getTableDescMgr().getTableDescriptor(wipRow.getTableId());
		}
		
		
		public Object getEditValue(int rowNum, String column) throws Exception {
			if ( row == null ) {
				row = (new RowManager(environment.getId())).getBlankRow(wipRow.getTableId());
				row.setColumns(wipRow.getKeyAndDataConcatenated(true));
			}
			ColumnDescriptor cd = td.getColumnDescriptor(column);
			Object value = cd.getValue(row);
			if ( value instanceof String ) {
				String sValue = ((String) value).trim();
				if ( UtilMethods.isNumeric(cd.getDataType()) ) {
					if ( sValue.indexOf("+") != -1 ) {
						sValue = sValue.substring(1);
					}

					if ( sValue.indexOf(".") == -1 ) {
						sValue = new Integer(sValue).toString();
					} else {
						sValue = new Double(sValue).toString();
					}
				}
				return sValue;
			}
			return value;
		}
		
		public void setEditValue(int row, String column, String value) throws Exception{
		}
		
		public int getDefaultFund() throws Exception{
			ProductObject prodObj = ProductManager.getInstance().getProduct(environment.getProductSystem());
			return Integer.parseInt(prodObj.getDefaultFund());	
		}
		
		public int getMaxFundNumber() throws Exception{
			ProductObject prodObj = ProductManager.getInstance().getProduct(environment.getProductSystem());
			return Integer.parseInt(prodObj.getMaxFund());	
		}
	}
}
