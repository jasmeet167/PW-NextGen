
package com.csc.fsg.life.pw.web.actions.rcm.beans;

import java.sql.Connection;
import java.util.*;

import org.apache.commons.logging.Log;

import com.csc.fsg.life.exceptions.WrapperException;
import com.csc.fsg.life.pw.common.util.*;
import com.csc.fsg.life.pw.web.actions.rcm.AuditEnvironmentsAction;
import com.csc.fsg.life.pw.web.environment.Environment;
import com.csc.fsg.life.pw.web.io.*;
import com.csc.fsg.life.pw.web.log.PWServerLogManager;
import com.csc.fsg.life.pw.web.utils.DBConnMgr;
import com.csc.fsg.life.security.authentication.UserPrincipal;

import com.csc.fsg.life.pw.web.utils.SqlPW;
import java.sql.*;

/* Modifications: V-D785, ENH874, T0091, ENH961, WMABASEIXI-4584, WMABASEIXI-4717, T0129 */

// V-D785 Promotion environments from Audit
// ENH874 - Improve audit filter performance
// ENH961 - throw InfoException

public class PackageBean extends RCMObject {

	private static Log _log = PWServerLogManager.getLog(PackageBean.class
	        .getPackage().getName());

	private String source = null;
	private String process = null;
	private String changesOnly = null;
	private StringBuffer filterStream = new StringBuffer();
	


	public PackageBean(Environment srcEnv, Environment targetEnv,WIPTableFilter filter) {
		super(srcEnv, targetEnv, filter);
	}

	public void setSource(String source) {
		this.source = source;
	}

	public void setChangesOnly(String changesOnly) {
		this.changesOnly = changesOnly;
	}

	private boolean isChangesOnly() {
		return ((changesOnly != null) && (changesOnly.trim()).length() != 0);
	}

	public void setProcess(String process) {
		this.process = process;
	}

	public String getInitialValues() throws Exception {

		StringBuffer outputData = new StringBuffer();

		if (process.equals(Constants.GET_ENVIRONMENTS)) {
			/** V-D785 Promotion environments from Audit */
			if (source.equals(Constants.AUDIT))
				 outputData = getInitialEnvironmentsFromAudit();
			 else
				 outputData = getInitialEnvironments();
		} else if (process.equals(Constants.GET_FILTER_INFO)) {
			outputData = getInitialFilterValues();
		}
		return outputData.toString();
	}

	private StringBuffer getInitialEnvironments() throws Exception {
		WIPProperties wipProps = WIPProperties.getInstance();
		ArrayList<String> distinctItemsFromWIP = WIPRows.getDistinctItemsFromWIP(
		        getSourceEnvironment(), wipProps
		                .getEnvironment(Constants.COMMON_WIP), false);
		// WMABASEIXI-4717 - return empty StringBuffer if no envs, don't throw exception.
		StringBuffer envList = new StringBuffer();
		if ( (distinctItemsFromWIP != null) && !distinctItemsFromWIP.isEmpty() )
			envList = toStringBuffer(distinctItemsFromWIP);
		return envList;
	}

	private StringBuffer toStringBuffer(ArrayList<String> list) {

		StringBuffer buffer = new StringBuffer();

		if ((list != null) && (list.size() != 0)) {
			for (int i = 0; i < (list.size() - 1); i++) {
				buffer.append(list.get(i) + "\t");
			}
			buffer.append(list.get(list.size() - 1));
		}
		return buffer;
	}

	private StringBuffer getInitialFilterValues() throws Exception {

		Hashtable<String, ArrayList<String>> rows = new Hashtable<String, ArrayList<String>>();
		ArrayList<String> packages = null;
		ArrayList<String> projects = null;
		ArrayList<String> users = null;
		ArrayList<String> tables = null;
		WIPProperties wipProps = WIPProperties.getInstance();

		try {
			Environment sourceEnvironment = getSourceEnvironment();
			if (source.equals(Constants.WIP)) {
				if (!isChangesOnly()) {
					// APPLY
					String error = WIPRow
						.getPromotionAlreadyInProgressMessage(getPromotionEnvironment(), null, true);
					if ( error != null )
						throw new InfoException(error);

					packages = WIPRows.getDistinctItemsFromWIP(
					        sourceEnvironment, wipProps
					                .getPackageId(Constants.COMMON_WIP), false);
					rows.put("PACKAGES", packages);
				} else {
					// CHANGES ONLY
					tables = WIPRows.getDistinctItemsFromWIP(sourceEnvironment,
					        wipProps.getDDLName(Constants.COMMON_WIP), false);
					rows.put("TABLES", tables);
					users = WIPRows.getDistinctItemsFromWIP(sourceEnvironment,
					        wipProps.getChangeUserId(Constants.COMMON_WIP),
					        false);
					rows.put("CHANGE_USERS", users);
				}
				projects = WIPRows.getDistinctItemsFromWIP(sourceEnvironment,
				        wipProps.getProjectName(Constants.COMMON_WIP), false);
				rows.put("PROJECTS", projects);

			} else if (source.equals(Constants.AUDIT)) {
				String error = WIPRow
					.getPromotionAlreadyInProgressMessage(getPromotionEnvironment(), null, true);
				if ( error != null )
					throw new InfoException(error);

				packages = WIPRow.getDistinctItemsFromAudit(
						sourceEnvironment, wipProps
				                .getPackageId(Constants.AUDIT_LOG), true);
				rows.put("PACKAGES", packages);
				projects = WIPRow.getDistinctItemsFromAudit(
						sourceEnvironment, wipProps
				                .getProjectName(Constants.AUDIT_LOG), true);
				rows.put("PROJECTS", projects);
			} else if (source.equals(Constants.AUDIT_VIEW)) {
				long start = System.currentTimeMillis();
				// ENH874 - get all distinct values in one SQL
				getInitialAuditValues(rows, wipProps);
				/*
				packages = WIPRow.getDistinctItemsFromAudit(sourceEnvironment,
				        wipProps.getPackageId(Constants.AUDIT_LOG), false);
				rows.put("PACKAGES", packages);
				projects = WIPRow.getDistinctItemsFromAudit(sourceEnvironment,
				        wipProps.getProjectName(Constants.AUDIT_LOG), false);
				rows.put("PROJECTS", projects);
				tables = WIPRow.getDistinctItemsFromAudit(sourceEnvironment,
				        wipProps.getDDLName(Constants.AUDIT_LOG), false);
				rows.put("TABLES", tables);
				users = WIPRow.getDistinctItemsFromAudit(sourceEnvironment,
				        wipProps.getChangeUserId(Constants.AUDIT_LOG), false);
				rows.put("CHANGE_USERS", users);
				*/
				if (_log.isTraceEnabled())
					_log.trace("Elapsed Time for Audit Filter="
					        + (System.currentTimeMillis() - start) + "ms");
			} else {
				packages = WIPRows.getDistinctItemsFromWIP(sourceEnvironment,
				        wipProps.getPackageId(Constants.COMMON_WIP), true);
				rows.put("PACKAGES", packages);
				projects = WIPRows.getDistinctItemsFromWIP(sourceEnvironment,
				        wipProps.getProjectName(Constants.COMMON_WIP), true);
				rows.put("PROJECTS", projects);
				tables = WIPRows.getDistinctItemsFromWIP(sourceEnvironment,
				        wipProps.getDDLName(Constants.COMMON_WIP), true);
				rows.put("TABLES", tables);
				users = WIPRows.getDistinctItemsFromWIP(sourceEnvironment,
				        wipProps.getChangeUserId(Constants.COMMON_WIP), true);
				rows.put("CHANGE_USERS", users);
			}
			initializeFilterStream(rows);
		} catch (Exception ex) {
			writeToErrorLog("Exception in getInitialValues()", ex);
			throw WrapperException.wrap(ex);
			
		}
		return filterStream;
	}

	// ENH874 - get all distinct values in one SQL
	// Then collect individual values
	private void getInitialAuditValues(Hashtable<String, ArrayList<String>> rows, WIPProperties wipProps) throws Exception{
		Set<String> packages = new TreeSet<String>();
		Set<String> tables = new TreeSet<String>();
		Set<String> projects = new TreeSet<String>();
		Set<String> users = new TreeSet<String>();
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;

		String autEnvId = getSourceEnvironment().getId();
		if (getSourceEnvironment().isExtAuditEnv())
			autEnvId = getSourceEnvironment().getId().substring(AuditEnvironmentsAction.EXT_AUDIT_PREFIX.length());
		try {
			String sql = "SELECT DISTINCT "
				+ wipProps.getPackageId(Constants.AUDIT_LOG) + ","
				+ wipProps.getProjectName(Constants.AUDIT_LOG) + ","
				+ wipProps.getDDLName(Constants.AUDIT_LOG) + ","
				+ wipProps.getChangeUserId(Constants.COMMON_WIP)
				+ " FROM " + getSourceEnvironment().getApplSchema() + ".AUDIT"
				+ " WHERE ENVIRONMENT = '" + autEnvId + "'";
			
			conn = DBConnMgr.getInstance().getConnection(getSourceEnvironment().getId(),DBConnMgr.APPL);
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			while ( rs.next() ) {
				packages.add(rs.getString(1));
				projects.add(rs.getString(2));
				tables.add(rs.getString(3));
				// WMABASEIXI-4584 - ensure login name is upper case
				users.add(rs.getString(4).toUpperCase());
			}
			rows.put("PACKAGES", new ArrayList<String>(packages));
			rows.put("PROJECTS", new ArrayList<String>(projects));
			rows.put("TABLES", new ArrayList<String>(tables));
			rows.put("CHANGE_USERS", new ArrayList<String>(users));
		} catch (Exception e) {
			throw(e);
		} finally {
			Utils.closeResultSet(rs);
			Utils.closeStatement(stmt);
			DBConnMgr.getInstance().releaseConnection(conn);
		}

	}
	
//	public String getExternalAuditValues(String envId,String product) throws Exception {
//
//		Hashtable rows = new Hashtable();
//		ArrayList packages = null;
//		ArrayList projects = null;
//		ArrayList users = null;
//		ArrayList tables = null;
//		WIPProperties wipProps = WIPProperties.getInstance();
//
//		try {
//				packages = WIPRow.getDistinctItemsFromAudit(envId,wipProps.getPackageId(Constants.AUDIT_LOG),product);
//				rows.put("PACKAGES", packages);
//				projects = WIPRow.getDistinctItemsFromAudit(envId,wipProps.getProjectName(Constants.AUDIT_LOG),product);
//				rows.put("PROJECTS", projects);
//				tables = WIPRow.getDistinctItemsFromAudit(envId,wipProps.getDDLName(Constants.AUDIT_LOG),product);
//				rows.put("TABLES", tables);
//				users = WIPRow.getDistinctItemsFromAudit(envId, wipProps.getChangeUserId(Constants.AUDIT_LOG),product);
//				rows.put("CHANGE_USERS", users);
//		
//				initializeFilterStream(rows);
//		} catch (Exception ex) {
//			throw WrapperException.wrap(ex);
//		}
//		return filterStream.toString();
//	}
	
	

	private void initializeFilterStream(Hashtable<String, ArrayList<String>> rows) throws Exception {

		if (rows == null || rows.isEmpty())
			throw new InfoException("No data found for the selected Environment");

		writePackages(rows.get("PACKAGES"));
		writeProjects(rows.get("PROJECTS"));
		writeTables(rows.get("TABLES"));
		writeUsers(rows.get("CHANGE_USERS"));

	}

	// ENH874 - rewite for performance improvement.  One SQL.
	private void writePackages(Object pkgs) throws Exception{
		StringBuffer toSend = new StringBuffer();
		List packages = (List) pkgs;
		Map<String, String> packageNames = new HashMap<String, String>();
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;

		if (pkgs == null)
			return;
		
		String autEnvId = getSourceEnvironment().getId();
		if (getSourceEnvironment().isExtAuditEnv())
			autEnvId = getSourceEnvironment().getId().substring(AuditEnvironmentsAction.EXT_AUDIT_PREFIX.length());
		try {
			String sql = "SELECT PACKAGE_ID, PACKAGE_NAME"
				+ " FROM " + getSourceEnvironment().getApplSchema() + ".PACKAGE_NAME"
				+ " WHERE ENVIRONMENT = '" +autEnvId + "'";

			conn = DBConnMgr.getInstance().getConnection(getSourceEnvironment().getId(),DBConnMgr.APPL);
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			while ( rs.next() ) {
				String id = rs.getString(1).trim();
				String name = rs.getString(2).trim();
				if ( name.length() > 0 )
					packageNames.put(id, name);
			}
			for (int i=0; i < packages.size(); i++) {
				String id = (String) packages.get(i);
				String name = packageNames.get(id);
				if ( name != null )
					toSend.append("#pkg#" + id + " - " + name + "\t");
				else
					toSend.append("#pkg#" + id + "\t");
			}
			filterStream.append(toSend);
		} catch (Exception e) {
			throw(e);
		} finally {
			Utils.closeResultSet(rs);
			Utils.closeStatement(stmt);
			DBConnMgr.getInstance().releaseConnection(conn);
		}

	}

	/*
	private void writePackages(Object pkgs) throws Exception{

		if (pkgs == null) {
			return;
		}

		StringBuffer toSend = new StringBuffer();
		ArrayList packages = (ArrayList) pkgs;
		Connection conn = null;

		try {
			conn = DBConnMgr.getInstance().getConnection(getSourceEnvironment().getId(),DBConnMgr.APPL);
			for (int i = 0; i < packages.size(); i++) {
				String packageId = (String) packages.get(i);
				String packageName = packageId;
				packageName = PackageAssistent.getPackageName(conn,
						getSourceEnvironment(), packageId);
				// WMA TT SPR# 240 - Prefix package ID to package Name
				if (packageName != null && (!(packageName.trim()).equals(""))) {
					toSend.append("#pkg#" + packageId + " - " + packageName
					        + "\t");
				} else {
					toSend.append("#pkg#" + packageId + "\t");
				}
			}
		} catch (Exception e) {

			toSend.setLength(0);
			for (int i = 0; i < packages.size(); i++)
				toSend.append("#pkg#" + (String) packages.get(i) + "\t");

		} finally {
			DBConnMgr.getInstance().releaseConnection(conn);
			filterStream.append(toSend);
		}

	}
	*/

	private void writeProjects(Object prjs) {

		if (prjs == null) {
			return;
		}
		ArrayList projects = (ArrayList) prjs;

		for (int i = 0; i < projects.size(); i++) {
			filterStream.append("#prj#" + projects.get(i) + "\t");
		}
	}

	private void writeTables(Object tbls) {

		if (tbls == null)
			return;

		ArrayList tables = (ArrayList) tbls;

		for (int i = 0; i < tables.size(); i++) {
			String tableName = ((String) tables.get(i)).trim();
			String translatedName = getTableNameWithTableID(tableName);
			filterStream.append("#tab#" + translatedName + "\t");
		}

	}

	private String getTableNameWithTableID(String ddlName) {
		StringBuffer sb = new StringBuffer(ddlName);
		sb.append(" - " + (getTableTranslation(ddlName)).trim());
		return sb.toString();
	}

	private void writeUsers(Object usrs) {

		if (usrs == null) {
			return;
		}
		ArrayList users = (ArrayList) usrs;

		for (int i = 0; i < users.size(); i++) {
			filterStream.append("#usr#" + getUserTranslation(users.get(i))
			        + "\t");
		}
	}

	// private void initializeUsersTable() {
	//
	// SecurityAssistent userAgent = null;
	//
	// if (userList == null) {
	// try {
	// userAgent = new SecurityAssistent();
	// userList = (Hashtable) userAgent.getAllUserNames(false);
	// } catch (Exception ex) {
	// //IGNORE TRANSLATION EXCEPTION
	// writeToErrorLog("Error in getUsersTable()", ex);
	// } finally {
	// if (userAgent!=null)
	// closeAssistent(userAgent);
	// }
	// }
	// }

	private String getUserTranslation(Object user) {

		if (user == null)
			return null;

		if (user instanceof UserPrincipal) {
			UserPrincipal usr = (UserPrincipal) user;
			return usr.getName() + " - " + usr.getLastName() + ", "
			        + usr.getFirstName();
		}

		return user.toString();
	}
	
	/** V-D785 Promotion environments from Audit */
    private StringBuffer getInitialEnvironmentsFromAudit() throws Exception {
    	Connection connection = null;
		StringBuffer sql = new StringBuffer();
		Statement stmt = null;
		ResultSet rs = null;

		ArrayList<String> distinctItems = new ArrayList<String>();

		try {

			connection = DBConnMgr.getInstance().getConnection(
					getSourceEnvironment().getId(), DBConnMgr.APPL);

			sql
					.append("SELECT DISTINCT ENVIRONMENT FROM "
							+ getSourceEnvironment().getApplSchema()
							+ ". AUDIT WHERE OPERATION <> 'DIRIMP' ");
			stmt = connection.createStatement();

			rs = SqlPW.query(SqlPW.SQL_AUDIT_FILTER, stmt, sql.toString());
			while (rs.next())
				distinctItems.add(rs.getString(1));

		} finally {
			Utils.closeResultSet(rs);
			Utils.closeStatement(stmt);
			DBConnMgr.getInstance().releaseConnection(connection);
		}

		StringBuffer envList = toStringBuffer(distinctItems);
		return envList;
	}

 }
