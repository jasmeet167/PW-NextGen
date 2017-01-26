
package com.csc.fsg.life.pw.web.actions.rcm.beans;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Vector;

import org.apache.commons.logging.Log;


import com.csc.fsg.life.pw.common.User;
import com.csc.fsg.life.pw.common.util.*;
import com.csc.fsg.life.pw.web.avm.AVManager;
import com.csc.fsg.life.pw.web.environment.Environment;
import com.csc.fsg.life.pw.web.io.*;
import com.csc.fsg.life.pw.web.log.PWServerLogManager;
import com.csc.fsg.life.pw.web.utils.DBConnMgr;
import com.csc.fsg.life.pw.web.utils.sql.SQLBuilderPLANWIP;

/* Modifications: T0103, T0091, CCCV-D506, ENH1063-05 */

public class RCMObject {

	private static Log _log = PWServerLogManager.getLog(RCMObject.class
	        .getPackage().getName());

	private String rcmUser = null;
	private Environment sourceEnv = null;
	private Environment targetEnv = null;
	private WIPTableFilter wipFilter = null;
	protected User userObject = null;
	
	protected static int initialSeed = 10;

	public RCMObject(Environment srcEnv, Environment targetEnv, WIPTableFilter filter) {
		this.sourceEnv = srcEnv;
		this.targetEnv = targetEnv;
		this.wipFilter = filter;
	}

	public Environment getSourceEnvironment() {
		return sourceEnv;
	}
	
	public Environment getTargetEnvironment() {
		return targetEnv;
	}
	
	public Environment getPromotionEnvironment() {
		return (targetEnv != null) ? targetEnv : sourceEnv;
	}

	protected boolean isPromotion() {
		return (!(getPromotionEnvironment().getId().equalsIgnoreCase(getSourceEnvironment().getId())));
	}

	public WIPTableFilter getWIPFilter() {
		return wipFilter;
	}
	
	public void setCurrentUser(String user) {
		this.rcmUser = user;
	}

	public void setUser(User user) {
		this.userObject = user;
	}

	public User getUser() {
		return userObject;
	}

	public String getCurrentUser() {
		return rcmUser;
	}

	public String getOrderByTimeStampClause() {
		return " ORDER BY TIME_STAMP";
	}

	public static final String generatePackageID() {

		java.util.Date dt = new java.util.Date();
		// CCCV-D506 - change package ID format to one better for sorting 
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddhhmmss");
		String datePrefix = sdf.format(dt);

		if (initialSeed == 99) {
			initialSeed = 10;
		}
		initialSeed = initialSeed + 1;

		return datePrefix + initialSeed;
	}


	protected static void writeToUserLog(String userInfo) {
		if (_log.isDebugEnabled()) {
			_log.debug(userInfo);
		}
	}

	public static void writeToErrorLog(String error, Throwable exception) {
		_log.error(error, exception);
	}

	protected Vector<String> getDistinctProjects(WIPRows allRows) {

		if (allRows == null) {
			return null;
		}

		Vector<String> distinctProjects = null;
		String tempProject = null;

		distinctProjects = new Vector<String>();

		for (int i = 0; i < allRows.size(); i++) {
			tempProject = (allRows.get(i)).getProjectName();
			tempProject = tempProject.trim();
			if (!distinctProjects.contains(tempProject)) {
				distinctProjects.add(tempProject);
			}
		}
		return distinctProjects;
	}


	protected WIPRows getFilteredTableRows(WIPRows allRows, Vector tableIds) {

		WIPRows tableRows = null;
		CommonWIPRow tempRow = null;

		if (allRows == null) {
			return null;
		}

		tableRows = new WIPRows();

		for (int i = 0; i < allRows.size(); i++) {
			tempRow = (allRows.get(i)).transformToCommon();
			String tempTableId = (tempRow.getTableId()).trim();

			if (tableIds.contains(tempTableId)) {
				tableRows.add(tempRow);
			}
		}
		return tableRows;
	}


	public static Vector<String> getVector(String initialValue) {

		if (initialValue == null) {
			return null;
		}
		Vector<String> newVector = new Vector<String>();

		newVector.addElement(initialValue);
		return newVector;
	}

	private WIPTableFilter getPromotionFilter() {
		WIPTableFilter filter = new WIPTableFilter();
		filter.setEnvironment(getPromotionEnvironment().getId());
		filter.setLocked(true);
		return filter;
	}

	public int getLockedRowsCount() throws Exception {
		return RCMObject.countWipRows(getPromotionEnvironment(), getPromotionFilter());
	}
	
	public static int countWipRows(Environment env, WIPTableFilter filter) throws Exception {
		Connection connection = null;
		Statement stmt = null;
		ResultSet rs = null;
		int rowCount = 0;
		try {
			connection = DBConnMgr.getInstance().getConnection(env.getId(),DBConnMgr.APPL);
			stmt = connection.createStatement();
			
			String sql = "Select COUNT(*) from "+env.getApplSchema()
					+".COMMONWIP "+ CommonWIPRow.buildWhereSQL(filter);
			
			rs = stmt.executeQuery(sql);
			if (rs.next()) 
				rowCount = rs.getInt(1);			
			Utils.closeResultSet(rs);
			
			sql = "Select COUNT(*) from "+env.getApplSchema()
	        	+".INDEXWIP "+IndexWIPRow.buildWhereSQL(filter);
			
			rs = stmt.executeQuery(sql);			
			if (rs.next()) 
				rowCount = rowCount + rs.getInt(1);			
			Utils.closeResultSet(rs);
			
			sql = new SQLBuilderPLANWIP(filter).buildCountAllStatement();			
			rs = stmt.executeQuery(sql);			
			if (rs.next()) 
				rowCount = rowCount + rs.getInt(1);
		} finally {
			Utils.closeResultSet(rs);
			Utils.closeStatement(stmt);
			DBConnMgr.getInstance().releaseConnection(connection);
		}
		return rowCount;
	}

	public static void deleteRecordsFromLocalWIP(String envId, String project, boolean locked) {
		WIPTableFilter filter = new WIPTableFilter();
		filter.setEnvironment(envId);
		if ( project != null ) {
			Vector<String> projectNames = new Vector<String>();
			projectNames.add(project);
			filter.setProjectNames(projectNames);
		}
		filter.setLocked(locked);

		Connection localConn = null;
		try {
			localConn = DBConnMgr.getInstance().getConnection(envId, DBConnMgr.APPL);
			WIPRows.deleteRows(localConn,filter);
		} catch (Exception e) {
			_log.error(e);
		} finally {
			DBConnMgr.getInstance().releaseConnection(localConn);
		}
	}

	public String getTableTranslation(String ddlName) {
		
		String translatedName = null;
		try {
			translatedName = AVManager.getTableName(getSourceEnvironment().getId(), ddlName);
		} catch (Exception e) {
			// IGNORE IF TRANSLATION IS NOT FOUND SEND TABLE ID
		}

		if (translatedName == null)
			return ddlName;

		return translatedName;
	}

}
