/*
 * THIS PROGRAM IS THE PROPERTY OF CSC FINANCIAL SERVICES GROUP. IT MAY NOT BE
 * COPIED IN WHOLE OR IN PART WITHOUT THE EXPRESS WRITTEN CONSENT OF CSC
 * FINANCIAL SERVICES GROUP.
 */

package com.csc.fsg.life.pw.io;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import java.util.Vector;

import org.apache.commons.logging.Log;

//import com.csc.fsg.life.pw.actions.rcm.AuditEnvironmentsAction;
//import com.csc.fsg.life.pw.web.actions.rcm.beans.*;
import com.csc.fsg.life.pw.environment.Environment;
import com.csc.fsg.life.pw.environment.EnvironmentManager;
import com.csc.fsg.life.pw.log.PWServerLogManager;
import com.csc.fsg.life.pw.util.Constants;
import com.csc.fsg.life.pw.util.DBConnMgr;
import com.csc.fsg.life.pw.util.SqlPW;
//import com.csc.fsg.life.pw.util.sql.SQLBuilderPLANWIP;
import com.csc.fsg.life.pw.util.Utils;
import com.csc.fsg.life.pw.util.WIPProperties;
import com.csc.fsg.life.pw.util.sql.SQLBuilderPLANWIP;

/* Modifications: T0103, T0091, T0094, T0113, CCCV-E768, T0129, ENH1063-05 */

/**
 * This class abstracts a single WIPRow. It is a superclass for particular kinds
 * of WIPRow classes which each represent a specific table. Creation date:
 * (10/09/2001 11:41:58 AM)
 * 
 * @author: Carl Ericksen
 */

public abstract class WIPRow implements Cloneable {

	private String attrMask = "";

	private String changedColumns = "";

	private int wipActionAttrMask;

	private String rowIndex = "";

	private int numberOfColumns;

	private String environment = "";

	private String companyCode = "";

	private String productPrefix = "";

	private String operation = "";

	protected String oldConcatKey = "";

	protected String oldData = "";

	private String projectName = "";

	private String changeUserId = "";

	private String promoteUserId = "";

	private long dbSequence;

	private String timestamp = "";

	private String packageId = "";

	private String errorIndicator = "";

	private String oldErrorIndicator = "";

	private ArrayList<String> originalData;

	private String promotionLock = "";

	private String ddlName = "";

	private String subsetName = "";

	private String tableId = "";

	protected String newConcatKey = "";

	protected String oldNewConcatKey = "";

	protected String newData = "";

	private boolean delNInsFlag;
	
	protected static Log _log = PWServerLogManager.getLog(WIPRow.class
			.getPackage().getName());

	/**
	 * This method asks each WIPRow subclass for the save SQL statement as a
	 * String in the PreparedStatement format, given the passed wipAction (IE:
	 * Insert). The caller has the responsibility to create the actual
	 * PreparedStatements on the connection using the returned Strings. Creation
	 * date: (10/12/2001 10:50:13 AM)
	 * 
	 * @param wipAction
	 * @return String
	 * @throws WIPRowException
	 */

	public static String[] getSaveSQL(String envId,String wipAction) throws Exception {

		String[] sqls = new String[3];

//		sqls[0] = CommonWIPRow.buildSaveSQL(envId,wipAction);
//		sqls[1] = PlanWIPRow.buildSaveSQL(envId,wipAction);
//		sqls[2] = IndexWIPRow.buildSaveSQL(envId,wipAction);
		return sqls;
	}

	/**
	 * This method acts as the controller for the create of SQL against the WIP
	 * tables and the Audit Log table. Either tableId or ddlName may be
	 * specified in the passed filter. This method inspects the passed filter
	 * for specific tableId/ddlName values in order to create the necessary SQL
	 * because not all tables have tableId/ddlName on them. IE. tableId and
	 * ddlName are implied on the PlanWIP and IndexWIP tables. Creation date:
	 * (10/11/2001 1:18:49 PM)
	 * 
	 * @return java.util.Hashmap
	 * @param filter
	 *            com.csc.fsg.life.pw.web.io.WIPTableFilter
	 * @param orderBy
	 *            String
	 * @throws WIPRowException
	 */

	public static HashMap<Integer, String> getSelectSQL(WIPTableFilter filter, String orderBy)
	        throws Exception {

		HashMap<Integer, String> sqlStrings = new HashMap<Integer, String>(4);
		boolean foundPlanTableId = false;
		boolean foundIndexTableId = false;
		boolean haveTableIds = false;
		boolean haveDDLNames = false;
		String sql = null;

//		if (filter instanceof AuditTableFilter) {
//			AuditTableFilter auditFilter = (AuditTableFilter) filter;
//			sql = AuditRow.buildSelectSQL(auditFilter, orderBy);
//			sqlStrings.put(Integer.valueOf(Constants.AUDIT_LOG), sql);
//		} else {
			Vector tableIds = filter.getTableIds();
			Vector ddlNames = filter.getDdlNames();

			if ((tableIds != null) && !tableIds.isEmpty()) {
				haveTableIds = true;
			}
			if ((ddlNames != null) && !ddlNames.isEmpty()) {
				haveDDLNames = true;
			}

			if (haveTableIds && haveDDLNames) {
				throw new WIPRowException(
				        "Cannot specify both filter items: tableIds and ddlNames");
			}

			if (haveTableIds || haveDDLNames) {
				if ((haveTableIds && tableIds.contains(Constants.TABLE_ZERO_ID))
				        || (haveDDLNames && ddlNames
				                .contains(Constants.TABLE_ZERO_NAME))) {
					foundPlanTableId = true;
					sql = PlanWIPRow.buildSelectSQL(filter, orderBy);
					sqlStrings.put(Integer.valueOf(Constants.PLAN_WIP), sql);
				}
				if ((haveTableIds && tableIds
				        .contains(Constants.SUBSET_TABLE_ID))
				        || (haveDDLNames && ddlNames
				                .contains(Constants.SUBSET_TABLE_NAME))) {
					foundIndexTableId = true;
					sql = IndexWIPRow.buildSelectSQL(filter, orderBy);
					sqlStrings.put(Integer.valueOf(Constants.INDEX_WIP), sql);
				}
				if ((haveTableIds && (tableIds.size() == 1))
				        || (haveDDLNames && (ddlNames.size() == 1))) {
					if (!foundPlanTableId && !foundIndexTableId) {
						sql = CommonWIPRow.buildSelectSQL(filter, orderBy);
						sqlStrings.put(Integer.valueOf(Constants.COMMON_WIP), sql);
					}
				}
				if ((haveTableIds && (tableIds.size() == 2))
				        || (haveDDLNames && (ddlNames.size() == 2))) {
					if (!foundPlanTableId || !foundIndexTableId) {
						sql = CommonWIPRow.buildSelectSQL(filter, orderBy);
						sqlStrings.put(Integer.valueOf(Constants.COMMON_WIP), sql);
					}
				}
				if ((haveTableIds && (tableIds.size() > 2))
				        || (haveDDLNames && (ddlNames.size() > 2))) {
					sql = CommonWIPRow.buildSelectSQL(filter, orderBy);
					sqlStrings.put(Integer.valueOf(Constants.COMMON_WIP), sql);
				}
			} else {
				sql = CommonWIPRow.buildSelectSQL(filter, orderBy);
				sqlStrings.put(Integer.valueOf(Constants.COMMON_WIP), sql);
				sql = PlanWIPRow.buildSelectSQL(filter, orderBy);
				sqlStrings.put(Integer.valueOf(Constants.PLAN_WIP), sql);
				sql = IndexWIPRow.buildSelectSQL(filter, orderBy);
				sqlStrings.put(Integer.valueOf(Constants.INDEX_WIP), sql);
			}
//		}
		return sqlStrings;
	}

	/**
	 * This method queries the Audit Log for a given schema looking for distinct
	 * values of the passed itemName. The valid itemNames are defined in
	 * Constants.java History: Ported from AuditViewAssistent for Service Pack 1
	 * Creation date: (10/09/2001 12:55:22 PM)
	 * 
	 * @param forPromotion
	 * @return java.util.ArrayList
	 * @param envSchema
	 *            String
	 * @param itemName
	 *            String
	 * @throws WIPRowException
	 */

	public static ArrayList<String> getDistinctItemsFromAudit(Environment environment,
	        String itemName, boolean forPromotion) throws Exception {

		Connection connection = null;
		StringBuffer sql = new StringBuffer();
		WIPProperties wipProps = WIPProperties.getInstance();
		Statement stmt = null;
		ResultSet rs = null;
		ArrayList<String> distinctItems = new ArrayList<String>();
  
		try {
			
			 String envId = environment.getId();
			//if (environment.isRemoteAudit()){
			//	connection = DBConnMgr.getInstance().getConnection(envId,DBConnMgr.BUSINESS_RULES);
			// }else{
			  	connection =  DBConnMgr.getInstance().getConnection(envId,DBConnMgr.APPL);
			// }
			 		 
			if (itemName.equals(wipProps.getProjectName(Constants.AUDIT_LOG))
			        || itemName.equals(wipProps
			                .getChangeUserId(Constants.AUDIT_LOG))
			        || itemName.equals(wipProps
			                .getPackageId(Constants.AUDIT_LOG))
			        || itemName.equals(wipProps
			                .getPromoteUserId(Constants.AUDIT_LOG))
			        || itemName
			                .equals(wipProps.getDDLName(Constants.AUDIT_LOG))
			        || itemName
			                .equals(wipProps.getTableId(Constants.AUDIT_LOG))) {
				String auditSchema = environment.getApplSchema();
			//	if (environment.isRemoteAudit())
			//		auditSchema = environment.getSchema();
			
//				if (environment.isExtAuditEnv())
//					envId = environment.getId().substring(AuditEnvironmentsAction.EXT_AUDIT_PREFIX.length());
				
				sql.append("SELECT DISTINCT " + itemName);
				sql.append(" FROM " + auditSchema + ". AUDIT ");
				sql.append(" WHERE ENVIRONMENT = '" + envId + "'");
				
				if (forPromotion) {
					// WMA TT SPR# 240 - Moved WHERE clause outside IF block
					sql.append(" AND " + wipProps.getOperation()
					        + " NOT LIKE '%C%' " + " AND "
					        + wipProps.getOperation() + " <> 'DIRIMP'"
					        + " AND " + wipProps.getOperation()
					        + " <> 'DELPLN'");
				}
			
				stmt = connection.createStatement();
				rs = SqlPW.query(SqlPW.SQL_AUDIT_FILTER, stmt, sql.toString());

				while (rs.next()) {
					distinctItems.add(rs.getString(1));
				}
			} else {
				throw new WIPRowException("Invalid item name");
			}
	
		} finally {

			Utils.closeResultSet(rs);
			Utils.closeStatement(stmt);
			DBConnMgr.getInstance().releaseConnection(connection);
		}

		return distinctItems;
	}
	
	//For external Audit entries
//	public static ArrayList getDistinctItemsFromAudit(String envId, String itemName,String product) throws Exception {
//
//		Connection connection = null;
//		StringBuffer sql = new StringBuffer();
//		String sqlStr = null;
//		WIPProperties wipProps = WIPProperties.getInstance();
//		Statement stmt = null;
//		ResultSet rs = null;
//		ArrayList distinctItems = new ArrayList();
//  
//		try {
//			 ExternalAuditBean extBean = ProductManager.getInstance().getProduct(product).getExtAuditBean();
//		   	connection = extBean.getDataSource().getConnection();
//		   	String auditSchema = extBean.getSchema();
//			 		 
//			if (itemName.equals(wipProps.getProjectName(Constants.AUDIT_LOG))
//			        || itemName.equals(wipProps.getChangeUserId(Constants.AUDIT_LOG))
//			        || itemName.equals(wipProps.getPackageId(Constants.AUDIT_LOG))
//			        || itemName.equals(wipProps.getPromoteUserId(Constants.AUDIT_LOG))
//			        || itemName.equals(wipProps.getDDLName(Constants.AUDIT_LOG))
//			        || itemName.equals(wipProps.getTableId(Constants.AUDIT_LOG))) 
//			{
//				
//				sql.append("SELECT DISTINCT " + itemName);
//				sql.append(" FROM " + auditSchema + "."
//				        + wipProps.getTableName(Constants.AUDIT_LOG));
//				sql.append(" WHERE ENVIRONMENT = '" + envId + "'");
//			
//				sqlStr = sql.toString();
//				stmt = connection.createStatement();
//				rs = SqlPW.query(SqlPW.SQL_AUDIT_FILTER, stmt, sqlStr);
//
//				while (rs.next()) {
//					distinctItems.add(rs.getString(1));
//				}
//			} else {
//				throw new WIPRowException("Invalid item name");
//			}
//		} catch (Exception e) {
//			throw WrapperException.wrap(e);
//		} finally {
//
//			Utils.closeResultSet(rs);
//			Utils.closeStatement(stmt);
//			DBConnMgr.getInstance().releaseConnection(connection);
//		}
//
//		return distinctItems;
//	}

	/**
	 * This method queries the WIP for a given schema looking for distinct
	 * values of the passed itemName. The valid itemNames are defined in
	 * Constants.java If only WIP records with errors are to be searched, set
	 * withErrors to true History: Ported from ViewAssistent for Service Pack 1
	 * Creation date: (10/09/2001 12:55:22 PM)
	 * 
	 * @return java.util.ArrayList
	 * @param envSchema
	 *            String
	 * @param itemName
	 *            String
	 * @param withErrors
	 *            boolean
	 * @throws WIPRowException
	 */

	// 9/9/8 - remove UNION SQL
	public static ArrayList<String> getDistinctItemsFromWIP(Environment environment,
	        String itemName, boolean withErrors) throws Exception {

		Connection connection = null;
		WIPProperties wipProps = WIPProperties.getInstance();
		Statement stmt = null;
		ResultSet rs = null;
		ArrayList<String> distinctItems = new ArrayList<String>();
		Set<String> distinctSet = new HashSet<String>();
		_log.debug(environment.getId()+" "+itemName+" "+withErrors);
		try {
		
			StringBuffer sql = new StringBuffer();
			connection = DBConnMgr.getInstance().getConnection(environment.getId(),DBConnMgr.APPL);
			stmt = connection.createStatement();
			
			if (itemName.equals(wipProps.getEnvironment())) {
				
				buildSelectDistinctSQL(sql, itemName, Constants.COMMON_WIP,environment);
				_log.debug(sql.toString());
				rs = SqlPW.query(SqlPW.SQL_WIP_FILTER, stmt, sql.toString());
				while (rs.next())
					distinctSet.add(rs.getString(1));
				Utils.closeResultSet(rs);
				
				sql.setLength(0);
				buildSelectDistinctSQL(sql, itemName, Constants.PLAN_WIP,environment);
				_log.debug(sql.toString());
				rs = SqlPW.query(SqlPW.SQL_WIP_FILTER, stmt, sql.toString());
				while (rs.next())
					distinctSet.add(rs.getString(1));
				Utils.closeResultSet(rs);

				sql.setLength(0);
				buildSelectDistinctSQL(sql, itemName, Constants.INDEX_WIP,environment);
				_log.debug(sql.toString());
				rs = SqlPW.query(SqlPW.SQL_WIP_FILTER, stmt, sql.toString());
				while (rs.next())
					distinctSet.add(rs.getString(1));
			} else if (itemName.equals(wipProps.getProjectName())
			        || itemName.equals(wipProps.getChangeUserId())
			        || itemName.equals(wipProps.getPackageId())
			        || itemName.equals(wipProps.getPromoteUserId())) {
			
				buildSelectDistinctSQL(sql, itemName, Constants.COMMON_WIP,environment);
				buildWhereSQLForDistinct(sql, environment.getId(), withErrors);
				_log.debug(sql.toString());
				rs = SqlPW.query(SqlPW.SQL_WIP_FILTER, stmt, sql.toString());
				while (rs.next())
					distinctSet.add(rs.getString(1));
				Utils.closeResultSet(rs);

				sql.setLength(0);
				buildSelectDistinctSQL(sql, itemName, Constants.PLAN_WIP,environment);
				buildWhereSQLForDistinct(sql, environment.getId(), withErrors);
				_log.debug(sql.toString());
				rs = SqlPW.query(SqlPW.SQL_WIP_FILTER, stmt, sql.toString());
				while (rs.next())
					distinctSet.add(rs.getString(1));
				Utils.closeResultSet(rs);

				sql.setLength(0);
				buildSelectDistinctSQL(sql, itemName, Constants.INDEX_WIP,environment);
				buildWhereSQLForDistinct(sql, environment.getId(), withErrors);
				rs = SqlPW.query(SqlPW.SQL_WIP_FILTER, stmt, sql.toString());
				_log.debug(sql.toString());
				while (rs.next())
					distinctSet.add(rs.getString(1));
			} else if (itemName.equals(wipProps.getTableId())
			        || itemName.equals(wipProps.getDDLName())) {

				// WMA TT SPR# 533 - T000X and T000XA should be at the top of
				// the list.
				sql.append("SELECT " + wipProps.getEnvironment());
				sql.append(" FROM " + environment.getApplSchema()
				        + "." + wipProps.getTableName(Constants.PLAN_WIP));
				buildWhereSQLForDistinct(sql, environment.getId(), withErrors);
				_log.debug(sql.toString());
				rs = stmt.executeQuery(sql.toString());
				if (rs.next()) {
					if (itemName.equals(wipProps.getDDLName())) {
						distinctSet.add(Constants.TABLE_ZERO_NAME);
					} else {
						distinctSet.add(Constants.TABLE_ZERO_ID);
					}
				}
				Utils.closeResultSet(rs);

				sql.setLength(0);
				sql.append("SELECT " + wipProps.getEnvironment());
				sql.append(" FROM " + environment.getApplSchema()
				        + "." + wipProps.getTableName(Constants.INDEX_WIP));
				buildWhereSQLForDistinct(sql, environment.getId(), withErrors);
				_log.debug(sql.toString());
				rs = SqlPW.query(SqlPW.SQL_WIP_FILTER, stmt, sql.toString());
				if (rs.next()) {
					if (itemName.equals(wipProps.getDDLName())) {
						distinctItems.add(Constants.SUBSET_TABLE_NAME);
					} else {
						distinctItems.add(Constants.SUBSET_TABLE_ID);
					}
				}
				Utils.closeResultSet(rs);

				sql.setLength(0);
				buildSelectDistinctSQL(sql, itemName, Constants.COMMON_WIP,environment);
				buildWhereSQLForDistinct(sql, environment.getId(), withErrors);
				_log.debug(sql.toString());
				rs = SqlPW.query(SqlPW.SQL_WIP_FILTER, stmt, sql.toString());
				while (rs.next())
					distinctSet.add(rs.getString(1));
			} else {
				throw new WIPRowException("Item name invalid");
			}
			
			distinctItems.addAll(distinctSet);
	
		} finally {
			Utils.closeResultSet(rs);
			Utils.closeStatement(stmt);
			DBConnMgr.getInstance().releaseConnection(connection);
		}
		return distinctItems;
	}

	private static void buildSelectDistinctSQL(StringBuffer sql,
	        String itemName, int typeOfWip,Environment environment) throws Exception {

		WIPProperties wipProps = WIPProperties.getInstance();

		sql.append("SELECT DISTINCT ");
		sql.append(itemName);
		sql.append(" FROM " +environment.getApplSchema() + "."
		        + wipProps.getTableName(typeOfWip));
	}

	private static void buildWhereSQLForDistinct(StringBuffer sql, String env,
	        boolean withErrors) throws Exception {

		WIPProperties wipProps = WIPProperties.getInstance();

		sql.append(" WHERE " + wipProps.getEnvironment() + " = '" + env + "'");
		if (withErrors) {
			sql.append(" AND " + wipProps.getErrorIndicator() + " = 'Y'");
		}
		sql.append(" AND " + wipProps.getPromotionLock() + " <> 'C'");
	}

	/**
	 * This method acts as the controller for the creation of delete SQL against
	 * the WIP tables. Creation date: (12/28/2001 12:38:49 PM)
	 * 
	 * @return java.util.HashMap
	 * @param filter
	 *            com.csc.fsg.life.pw.web.io.WIPTableFilter
	 * @throws WIPRowException
	 */

	public static HashMap<Integer, String> getDeleteSQL(WIPTableFilter filter)
	        throws WIPRowException {

		HashMap<Integer, String> sqlStrings = new HashMap<Integer, String>(3);
		String sql = null;

		// T0103 - use SQLBuilder
		sql = new SQLBuilderPLANWIP(filter).buildDeleteStatement();
		//sql = PlanWIPRow.buildDeleteSQL(filter);
		sqlStrings.put(Integer.valueOf(Constants.PLAN_WIP), sql);
		sql = IndexWIPRow.buildDeleteSQL(filter);
		sqlStrings.put(Integer.valueOf(Constants.INDEX_WIP), sql);
		sql = CommonWIPRow.buildDeleteSQL(filter);
		sqlStrings.put(Integer.valueOf(Constants.COMMON_WIP), sql);
		return sqlStrings;
	}

	/**
	 * This method acts as the controller for the creation of specific update
	 * SQL against the WIP tables. Creation date: (10/11/2001 1:18:49 PM)
	 * 
	 * @return java.util.HashMap
	 * @param filter
	 *            com.csc.fsg.life.pw.web.io.WIPTableFilter
	 * @throws WIPRowException
	 */

	public static HashMap<Integer, String> getLockSQL(WIPTableFilter filter)
	        throws WIPRowException {

		HashMap<Integer, String> sqlStrings = new HashMap<Integer, String>(3);
		String sql = null;

		sql = PlanWIPRow.buildLockSQL(filter);
		sqlStrings.put(Integer.valueOf(Constants.PLAN_WIP), sql);
		sql = IndexWIPRow.buildLockSQL(filter);
		sqlStrings.put(Integer.valueOf(Constants.INDEX_WIP), sql);
		sql = CommonWIPRow.buildLockSQL(filter);
		sqlStrings.put(Integer.valueOf(Constants.COMMON_WIP), sql);
		return sqlStrings;
	}

	/**
	 * The method queries the WIP for a given environment for locked rows and
	 * returns a user friendly message if there are locked rows.
	 * 
	 * @param projects
	 * @return boolean
	 * @param envSchema
	 *            String
	 * @throws WIPRowException
	 */

	public static String getPromotionAlreadyInProgressMessage(Environment env,
	        Vector projects, boolean bHTML) throws Exception {

		// query all three WIP tables together looking for locked rows for the
		// passed schema
		// use WIPProperties for all column names and DB2 table names for FROM
		// CLAUSE
		Connection connection = null;
		StringBuffer sql = new StringBuffer();
		WIPProperties wipProps = WIPProperties.getInstance();
		Statement stmt = null;
		ResultSet rs = null;
		String errorMessage = null;
		int wipSource = Constants.COMMON_WIP;

		try {
	
			connection = DBConnMgr.getInstance().getConnection(env.getId(),DBConnMgr.APPL);
			stmt = connection.createStatement();
			
			while (wipSource < 4) {
				sql.append("SELECT PROJECT_NAME, CHANGE_USER_ID FROM "
				        + env.getApplSchema() + "."
				        + wipProps.getTableName(wipSource));

				sql.append(" WHERE ");
				sql.append(wipProps.getEnvironment() + " = '" + env.getId()
				        + "' AND " + wipProps.getPromotionLock() + " = 'L'");

				if (projects != null) {
					if (projects.size() == 0) {
						throw new WIPRowException("Empty request vector");
					}
					for (int i = 0; i < projects.size(); i++) {
						if (i == 0) {
							sql.append(" AND ( " + wipProps.getProjectName()
							        + " = '" + (String) projects.elementAt(i)
							        + "' ");
						} else {
							sql.append(" OR " + wipProps.getProjectName()
							        + " = '" + (String) projects.elementAt(i)
							        + "' ");
						}
					}
					sql.append(" )");
				}

				String sqlStr = sql.toString();

				sql.setLength(0);
			
				rs = stmt.executeQuery(sqlStr);
				if (rs.next()) {
					String project = rs.getString("PROJECT_NAME");
					String user = rs.getString("CHANGE_USER_ID");
					StringBuffer sb = new StringBuffer();
					if ( bHTML )
						sb.append("<HTML>");
					sb.append("An Apply/Promote is already in progress for project '");
					sb.append(project.trim());
					sb.append("', created by user '");
					sb.append(user.trim());
					sb.append("'.");
					if ( bHTML )
						sb.append("<br>");
					else
						sb.append("\n");
					sb.append("Your operation is not allowed at this time.");
					errorMessage = sb.toString();
					break;
				}
				wipSource++;
				Utils.closeResultSet(rs);
			}
		
		} finally {
			Utils.closeResultSet(rs);
			Utils.closeStatement(stmt);
			DBConnMgr.getInstance().releaseConnection(connection);
		}
		return errorMessage;
	}

	/**
	 * This method builds the stream in the vxx-s2c-table.txt format
	 * 
	 * @return a string representation of the receiver
	 */

	public String toString() {

		StringBuffer sb = new StringBuffer();
		//String stream = null;
		String operation = getOperation();

		if (operation.equalsIgnoreCase(Constants.INSERT_OPERATION)) {
			sb.append(Constants.TABLE_ATTR_HAS_AUDIT_INFO
			        | Constants.TABLE_ATTR_TARGET_INSERT);
		} else if (operation.equalsIgnoreCase(Constants.UPDATE_OPERATION)) {
			sb.append(Constants.TABLE_ATTR_HAS_AUDIT_INFO
			        | Constants.TABLE_ATTR_TARGET_UPDATE);
		} else {
			sb.append(Constants.TABLE_ATTR_HAS_AUDIT_INFO
			        | Constants.TABLE_ATTR_TARGET_DELETE);
		}
		sb.append(Constants.STAB);

		/*
		 * sb.append(getNewData()); sb.append(Constants.STAB);
		 */

		sb.append(UtilMethods.getDataWithDelims(this)); // Tab is added at the
		// end while returning
		// from the method
		sb.append(getOldConcatKey());
		sb.append(Constants.STAB);
		sb.append(getOldData());
		sb.append(Constants.STAB);
		sb.append(getChangeUserId());
		sb.append(Constants.STAB);
		sb.append(getTimestamp());
		sb.append(Constants.STAB);
		sb.append(getProjectName());
		return sb.toString();
	}

	/**
	 * This abstract method must be implemented in all subclasses to save the
	 * data it contains to the appropriate table. Creation date: (10/10/2001
	 * 9:50:04 AM)
	 * 
	 * @param stmt
	 * @param action
	 * @param wipRows
	 * @throws WIPRowException
	 */

	public abstract void save(PreparedStatement stmt, String action,
	        IWIPRows wipRows) throws WIPRowException;

	/**
	 * This abstract method must be implemented in all subclasses to allow the
	 * particular WIPRow to transform itself into a CommonWIPRow. Creation date:
	 * (10/10/2001 9:56:01 AM)
	 * 
	 * @return com.csc.fsg.life.pw.web.io.CommonWIPRow
	 */

	public abstract CommonWIPRow transformToCommon();

	/**
	 * ********************* GETTERS **********************
	 * 
	 * @return
	 */

	public String getAttrMask() {
		return attrMask;
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
	 * Method getChangeUserId
	 * 
	 * @return
	 */

	public String getChangeUserId() {
		return changeUserId;
	}

	/**
	 * Method getCompanyCode
	 * 
	 * @return
	 */

	public String getCompanyCode() {
		return companyCode;
	}

	/**
	 * Method getDbSequence
	 * 
	 * @return
	 */

	public long getDbSequence() {
		return dbSequence;
	}

	/**
	 * Method getDdlName
	 * 
	 * @return
	 */

	public String getDdlName() {
		return ddlName;
	}

	/**
	 * Method getEnvironment
	 * 
	 * @return
	 */

	public String getEnvironment() {
		return environment;
	}

	/**
	 * Method getErrorIndicator
	 * 
	 * @return
	 */

	public String getErrorIndicator() {
		return errorIndicator;
	}

	/**
	 * Method getOldErrorInd
	 * 
	 * @return
	 */

	public String getOldErrorInd() {
		return oldErrorIndicator;
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
	 * Method getNumberOfColumns
	 * 
	 * @return
	 */

	public int getNumberOfColumns() {
		return numberOfColumns;
	}

	/**
	 * Method getOldConcatKey
	 * 
	 * @return
	 */

	public abstract String getOldConcatKey(); // spr 2378

	/**
	 * Method getOldNewConcatKey
	 * 
	 * @return
	 */

	public String getOldNewConcatKey() {
		return oldNewConcatKey;
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
	 * Method getOriginalData
	 * 
	 * @return
	 */

	public ArrayList<String> getOriginalData() {
		return originalData;
	}

	/**
	 * Method getPackageId
	 * 
	 * @return
	 */

	public String getPackageId() {
		return packageId;
	}

	/**
	 * Method getProductPrefix
	 * 
	 * @return
	 */

	public String getProductPrefix() {
		return productPrefix;
	}

	/**
	 * Method getProjectName
	 * 
	 * @return
	 */

	public String getProjectName() {
		return projectName;
	}

	/**
	 * Method getPromoteUserId
	 * 
	 * @return
	 */

	public String getPromoteUserId() {
		return promoteUserId;
	}

	/**
	 * Method getPromotionLock
	 * 
	 * @return
	 */

	public String getPromotionLock() {
		return promotionLock;
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
	 * Method getDisplayRowIndex
	 * 
	 * @return
	 */

	public String getDisplayRowIndex() {
		// convert to 1-based index
		if (rowIndex==null || rowIndex.trim().length()==0)
			return " ";
		int index = Integer.parseInt(rowIndex);
		index++;
		return Integer.toString(index);
	}

	/**
	 * Method getSubsetName
	 * 
	 * @return
	 */

	public String getSubsetName() {
		return subsetName;
	}

	/**
	 * Method getTableId
	 * 
	 * @return
	 */

	public String getTableId() {
		return tableId;
	}

	/**
	 * Method getTimestamp
	 * 
	 * @return
	 */

	public String getTimestamp() {
		return timestamp;
	}

	/**
	 * Method getWipActionAttrMask
	 * 
	 * @return
	 */

	public int getWipActionAttrMask() {
		return wipActionAttrMask;
	}

	/**
	 * ********************* SETTERS **********************
	 * 
	 * @param newAttrMask
	 */

	public void setAttrMask(String newAttrMask) {
		attrMask = newAttrMask;
	}

	/**
	 * Method setChangedColumns
	 * 
	 * @param newChangedColumns
	 */

	public void setChangedColumns(String newChangedColumns) {
		changedColumns = newChangedColumns;
	}

	/**
	 * Method setChangeUserId
	 * 
	 * @param newChangeUserId
	 */

	public void setChangeUserId(String newChangeUserId) {
		changeUserId = newChangeUserId;
	}

	/**
	 * Method setCompanyCode
	 * 
	 * @param newCompanyCode
	 */

	public void setCompanyCode(String newCompanyCode) {
		companyCode = newCompanyCode;
	}

	/**
	 * Method setDbSequence
	 * 
	 * @param newDbSequence
	 */

	public void setDbSequence(long newDbSequence) {
		dbSequence = newDbSequence;
	}

	/**
	 * Method setDdlName
	 * 
	 * @param newDdlName
	 */

	public void setDdlName(String newDdlName) {
		ddlName = newDdlName;
	}

	/**
	 * Method setEnvironment
	 * 
	 * @param newEnvironment
	 */

	public void setEnvironment(String newEnvironment) {
		environment = newEnvironment;
	}

	/**
	 * Method setErrorIndicator
	 * 
	 * @param errorIndicator
	 */

	public void setErrorIndicator(String errorIndicator) {
		this.errorIndicator = errorIndicator;
	}

	/**
	 * Method setOldErrorInd
	 * 
	 * @param oldErrorIndicator
	 */

	public void setOldErrorInd(String oldErrorIndicator) {
		this.oldErrorIndicator = oldErrorIndicator;
	}

	/**
	 * Method setNewConcatKey
	 * 
	 * @param nNewConcatKey
	 */

	public void setNewConcatKey(String nNewConcatKey) {
		newConcatKey = nNewConcatKey;
	}

	/**
	 * Method setNewData
	 * 
	 * @param nNewData
	 */

	public void setNewData(String nNewData) {
		newData = nNewData;
	}

	/**
	 * Method setNumberOfColumns
	 * 
	 * @param newNumberOfColumns
	 */

	public void setNumberOfColumns(int newNumberOfColumns) {
		numberOfColumns = newNumberOfColumns;
	}

	/**
	 * Method setOldConcatKey
	 * 
	 * @param newOldConcatKey
	 */

	public void setOldConcatKey(String newOldConcatKey) {
		oldConcatKey = newOldConcatKey;
	}

	/**
	 * This method allows a consumer to update key row data on a WIPRow while
	 * specifying the old key data (oldNewConcatKey)
	 * 
	 * @param oldNewConcatKey
	 */

	public void setOldNewConcatKey(String oldNewConcatKey) {
		this.oldNewConcatKey = oldNewConcatKey;
	}

	/**
	 * Method setOperation
	 * 
	 * @param newOperation
	 */

	public void setOperation(String newOperation) {
		operation = newOperation;
	}

	/**
	 * Method setOriginalData
	 * 
	 * @param newOriginalData
	 */

	public void setOriginalData(ArrayList<String> newOriginalData) {
		originalData = newOriginalData;
	}

	/**
	 * Method setPackageId
	 * 
	 * @param newPackageId
	 */

	public void setPackageId(String newPackageId) {
		packageId = newPackageId;
	}

	/**
	 * Method setProductPrefix
	 * 
	 * @param newProductPrefix
	 */

	public void setProductPrefix(String newProductPrefix) {
		productPrefix = newProductPrefix;
	}

	/**
	 * Method setProjectName
	 * 
	 * @param newProjectName
	 */

	public void setProjectName(String newProjectName) {
		projectName = newProjectName;
	}

	/**
	 * Method setPromoteUserId
	 * 
	 * @param newPromoteUserId
	 */

	public void setPromoteUserId(String newPromoteUserId) {
		promoteUserId = newPromoteUserId;
	}

	/**
	 * Method setPromotionLock
	 * 
	 * @param newPromotionLock
	 */

	public void setPromotionLock(String newPromotionLock) {
		promotionLock = newPromotionLock;
	}

	/**
	 * Method setRowIndex
	 * 
	 * @param newRowIndex
	 */

	public void setRowIndex(String newRowIndex) {
		rowIndex = newRowIndex;
	}

	/**
	 * Method setSubsetName
	 * 
	 * @param newSubsetName
	 */

	public void setSubsetName(String newSubsetName) {
		subsetName = newSubsetName;
	}

	/**
	 * Method setTableId
	 * 
	 * @param newTableId
	 */

	public void setTableId(String newTableId) {
		tableId = newTableId;
	}

	/**
	 * Method setTimestamp
	 * 
	 * @param newTimestamp
	 */

	public void setTimestamp(String newTimestamp) {
		timestamp = newTimestamp;
	}

	/**
	 * Method setWipActionAttrMask
	 * 
	 * @param newWipActionAttrMask
	 */

	public void setWipActionAttrMask(int newWipActionAttrMask) {
		wipActionAttrMask = newWipActionAttrMask;
	}

	/*
	 * private String getDataWithDelims(WIPRow wipRow) { int startValue = 0; int
	 * endValue = 0; int columnLength = 0; String columnValue = null;
	 * TableDescriptor _tableDescriptor =
	 * TableDescriptorManager.getInstance().getTableDescriptor(wipRow.getTableId());
	 * Vector columndescriptors = _tableDescriptor.getColumnDescriptors(); int
	 * columnCount = _tableDescriptor.getNumberOfColumns() ; StringBuffer toSend =
	 * new StringBuffer(); columnValue = wipRow.getNewData(); for ( int count =
	 * 0; count < columnCount; count++ ) { ColumnDescriptor cd =
	 * (ColumnDescriptor)columndescriptors.get(count); columnLength =
	 * cd.getColumnSize(wipRow.getEnvironment()); endValue = startValue +
	 * columnLength ; toSend.append(
	 * columnValue.substring(startValue,endValue));
	 * toSend.append(Constants.STAB); startValue = endValue; } return
	 * toSend.toString(); }
	 */

	public boolean isDelNInsFlag() {
		return delNInsFlag;
	}

	public void setDelNInsFlag(boolean b) {
		delNInsFlag = b;
	}

	public String getEnvCompTabIdNSubsetStr() {
		StringBuffer sb = new StringBuffer();
		sb.append(getEnvironment().trim());
		sb.append(getCompanyCode().trim());
		sb.append(getTableId());
		if (getSubsetName() != null)
			sb.append(getSubsetName().trim());
		return sb.toString();
	}

	public String getOldData() {
		return oldData;
	}

	public void setOldData(String string) {
		oldData = string;
	}

	public String getKeyAndDataConcatenated(boolean isNewKeyAndData){

		Environment env = EnvironmentManager.getInstance().getEnvironment(getEnvironment());
		TableDescriptor td = env.getTableDescMgr().getTableDescriptor(getTableId());
		String key = null;
		String data = null;
		
		if(isNewKeyAndData){
			key = getNewConcatKey();
			data = getNewData();
		}else{
			key = getOldConcatKey();
			data = getOldData();
		}
		
		if(td.isKeyFieldsInOrder())
			return key + data;
		else
			return td.parseToMatchOrder(key, data);
		
	}
}
