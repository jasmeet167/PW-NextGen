/*
 * THIS PROGRAM IS THE PROPERTY OF CSC FINANCIAL SERVICES GROUP. IT MAY NOT BE
 * COPIED IN WHOLE OR IN PART WITHOUT THE EXPRESS WRITTEN CONSENT OF CSC
 * FINANCIAL SERVICES GROUP.
 */

package com.csc.fsg.life.pw.util;

import java.sql.*;

import org.apache.commons.logging.Log;

import com.csc.fsg.life.pw.util.Constants;
import com.csc.fsg.life.pw.log.PWServerLogManager;

public abstract class SqlPW {
	// create logger with class name instead of package name
	private static Log _log = PWServerLogManager.getLog(SqlPW.class.getName());

	public static int SQL_OTHER = 0,

	SQL_GET_PLAN_NAME = 5, SQL_GET_PLAN_ROW = 6, SQL_GET_FILTER_DDL = 7,
	        SQL_GET_COUNT = 8, SQL_GET_TBL_IDS = 9, SQL_TBL_MGR_PUT = 10,
	        SQL_WIP_INSERT = 11, SQL_WIP_DELETE = 12, SQL_WIP_UPDATE = 13,
	        WIP_ROWS_UPDATE = 14, SQL_WIP_ROWS_FOR_CPY = 15,
	        SQL_ROWS_FOR_COMPANY = 16, SQL_SRC_PLAN_FILTER = 17,
	        SQL_WIP_PLAN_FILTER = 18,

	        SQL_TBL_SUBSET = 20, SQL_TBL_PAGE = 21,

	        SQL_WIP_ROWS = 29, SQL_WIP_SAVE = 30, SQL_SCRUB_PW = 31,
	        SQL_WIP_COMMON_DEL = 32, SQL_WIP_COMMON_INS = 33,
	        SQL_WIP_COMMON_UPD = 34, SQL_WIP_PLAN_DEL = 35,
	        SQL_WIP_PLAN_INS = 36, SQL_WIP_PLAN_UPD = 37,
	        SQL_WIP_INDEX_DEL = 38, SQL_WIP_INDEX_INS = 39,
	        SQL_WIP_INDEX_UPD = 40,

	        SQL_ERROR_INSERT = 50, SQL_ERROR_UPDATE = 51,
	        SQL_ERROR_DELETE = 52,

	        SQL_INDEX_MERGE1 = 55, SQL_INDEX_MERGE2 = 56,
	        SQL_INDEX_MERGE3 = 57, SQL_INDEX_MERGE4 = 58,

	        SQL_INDEX_SAVE = 60, SQL_PLAN_SAVE = 61, SQL_LOCAL_SYNC = 62,

	        WIP_AGENT_UPDATE = 70,
	        SQL_PROMOTE_LOCK = 71,
	        SQL_WIP_INS_ERRS = 72,

	        SQL_AUDIT_FILTER = 75,
	        SQL_WIP_FILTER = 76,

	        SQL_AV_QUERY = 80,

	        SQL_PLAN_MERGE1 = 81,
	        SQL_PLAN_MERGE2 = 82,
	        SQL_PLAN_MERGE3 = 83,
	        SQL_PLAN_MERGE4 = 84,
	        SQL_PLAN_MERGE5 = 85,
	        SQL_PLAN_MERGE6 = 86,
	        SQL_MERGE_INSERT = 87,
	        SQL_MERGE_UPDATE = 88,
	        SQL_MERGE_DELETE = 89,

	        // //////////////////////////////////////////////
	        SQL_DETAIL_IS_USEFUL = 90, // all SQL higher than this
	        // is not shown even in detail, because it doesnt vary
	        // //////////////////////////////////////////////

	        SQL_RCM_PREPARE_BATCH = 90, SQL_RCM_EXEC_BATCH = 91,
	        SQL_RCM_COMMIT = 92, SQL_RCM_SP_EXEC = 93, SQL_RCM_DELETE = 94,
	        SQL_IMPORT_PREP_BATCH = 95, SQL_IMPORT_EXEC_BATCH = 96,
	        SQL_IMPORT_COMMIT = 97, SQL_IMPORT_ORPH_BATCH = 98,

	        SQL_MAX_ID = 100; // LAST

	static String label[] = new String[SQL_MAX_ID];

	static {
		label[SQL_GET_PLAN_NAME] = "Get plan name";
		label[SQL_GET_PLAN_ROW] = "Get plan row";
		label[SQL_GET_FILTER_DDL] = "Get filter ddl";
		label[SQL_GET_COUNT] = "Get count";
		label[SQL_GET_TBL_IDS] = "Get tbl ids";
		label[SQL_TBL_MGR_PUT] = "Tbl mgr put";
		label[SQL_WIP_INSERT] = "Wip insert";
		label[SQL_WIP_DELETE] = "Wip delete";
		label[SQL_WIP_UPDATE] = "Wip update";
		label[WIP_ROWS_UPDATE] = "Rows update";
		label[SQL_WIP_ROWS_FOR_CPY] = "WIP Rows for company (Ins/Del)";
		label[SQL_ROWS_FOR_COMPANY] = "Source Rows for company";
		label[SQL_SRC_PLAN_FILTER] = "Source Plan Filter Spec";
		label[SQL_WIP_PLAN_FILTER] = "WIP Plan Filter Spec";
		label[SQL_TBL_SUBSET] = "Tbl View";
		label[SQL_TBL_PAGE] = "Tbl View Page";
		label[SQL_WIP_ROWS] = "Wip rows";
		label[SQL_WIP_SAVE] = "Wip save";
		label[SQL_SCRUB_PW] = "Scrub wip";
		label[SQL_WIP_COMMON_DEL] = "Wip common del";
		label[SQL_WIP_COMMON_INS] = "Wip common ins";
		label[SQL_WIP_COMMON_UPD] = "Wip common upd";
		label[SQL_WIP_PLAN_DEL] = "Wip plan del";
		label[SQL_WIP_PLAN_INS] = "Wip plan ins";
		label[SQL_WIP_PLAN_UPD] = "Wip plan upd";
		label[SQL_WIP_INDEX_DEL] = "Wip index del";
		label[SQL_WIP_INDEX_INS] = "Wip index ins";
		label[SQL_WIP_INDEX_UPD] = "Wip index upd";
		label[SQL_ERROR_INSERT] = "Error insert";
		label[SQL_ERROR_UPDATE] = "Error update";
		label[SQL_ERROR_DELETE] = "Error delete";
		label[SQL_INDEX_MERGE1] = "Index merge1";
		label[SQL_INDEX_MERGE2] = "Index merge2";
		label[SQL_INDEX_MERGE3] = "Index merge3";
		label[SQL_INDEX_MERGE4] = "Index merge4";
		label[SQL_INDEX_SAVE] = "Index save";
		label[SQL_PLAN_SAVE] = "Plan save";
		label[SQL_LOCAL_SYNC] = "Sync local X/XA";
		label[WIP_AGENT_UPDATE] = "Agent update";
		label[SQL_PROMOTE_LOCK] = "Promote lock";
		label[SQL_WIP_INS_ERRS] = "Wip ins errs";
		label[SQL_AUDIT_FILTER] = "Audit Filter Boxes";
		label[SQL_WIP_FILTER] = "WIP Filter Boxes";
		label[SQL_AV_QUERY] = "AV query";
		label[SQL_PLAN_MERGE1] = "Plan merge1";
		label[SQL_PLAN_MERGE2] = "Plan merge2";
		label[SQL_PLAN_MERGE3] = "Plan merge3";
		label[SQL_PLAN_MERGE4] = "Plan merge4";
		label[SQL_PLAN_MERGE5] = "Plan merge5";
		label[SQL_PLAN_MERGE6] = "Plan merge6";
		label[SQL_MERGE_INSERT] = "Merge insert";
		label[SQL_MERGE_UPDATE] = "Merge update";
		label[SQL_MERGE_DELETE] = "Merge delete";
		label[SQL_RCM_PREPARE_BATCH] = "Rcm prepare batch";
		label[SQL_RCM_EXEC_BATCH] = "Rcm exec batch";
		label[SQL_RCM_COMMIT] = "Rcm commit";
		label[SQL_RCM_SP_EXEC] = "Rcm sp exec";
		label[SQL_RCM_DELETE] = "Rcm delete";
		label[SQL_IMPORT_PREP_BATCH] = "Direct Import prep batch";
		label[SQL_IMPORT_EXEC_BATCH] = "Direct Import exec batch";
		label[SQL_IMPORT_COMMIT] = "Direct Import commit";
		label[SQL_IMPORT_ORPH_BATCH] = "Direct Import orphan batch";

	}

	protected static String getLabel(int id) {
		if (id >= 0 && id <= SQL_MAX_ID && label[id] != null)
			return label[id];
		else
			return "SqlPW.ID" + id;

	}

	public static final int LOG_VERY_SLOW_MS = 3000;

	public static final int LOG_SLOW_MS = 500;

	public static final int LOG_FAST_MS = 100;

	public static long logStart(String s) {
		long now = System.currentTimeMillis();
		if (_log.isDebugEnabled())
			_log.debug(s + " starting...");
		return now;
	}

	public static long logFinish(String s, long startTime) {
		long elapsed = System.currentTimeMillis() - startTime;
		if (_log.isDebugEnabled())
			_log.debug(s + " done.  Elapsed: " + elapsed / 1000.0 + "s");
		return elapsed;
	}

	protected static void logSqlEx(int id, SQLException se, String sql) {
		_log.error("**SQLEx** [" + getLabel(id) + "] " + se.toString() + "<"
		        + sql + ">");
	}

	protected static boolean showSummary(long elapsed) {
		boolean isVerySlow = elapsed > LOG_VERY_SLOW_MS;
		boolean isSlow = elapsed > LOG_SLOW_MS;

		return (_log.isErrorEnabled() && isVerySlow)
		        || (_log.isDebugEnabled() && isSlow) || _log.isTraceEnabled();
	}

	protected static boolean showDetail(long elapsed, int id) {
		return (id < SQL_DETAIL_IS_USEFUL)
		        && ((_log.isTraceEnabled() && (elapsed > LOG_FAST_MS)) || (_log
		                .isDebugEnabled() && (elapsed > LOG_SLOW_MS)));
	}

	protected static String getSqlFromStmt(Statement stmt) {
		String s = stmt.toString();
		int x1 = s.indexOf("SQL =");
		int x2 = s.indexOf("\r");
		if (x2 < x1)
			x2 = x1 + 80;
		if (x1 > 0 && x2 > 0)
			return (s.substring(x1, x2));
		else
			return "unknown";
	}

	protected static void tally(int id, long startTime, Statement stmt) {
		tally(id, startTime, getSqlFromStmt(stmt));
	}

	protected static void tally(int id, long startTime, String sql) {
		tally(id, startTime, sql, -1);
	}

	protected static void tally(int id, long startTime, Statement stmt,
	        int numrows) {
		tally(id, startTime, getSqlFromStmt(stmt), numrows);
	}

	protected static void tally(int id, long startTime, String sql, int numrows) {
		long elapsed = tally(id, startTime);

		if (showDetail(elapsed, id)) {
			String msg = "<" + sql + ">";
			if (elapsed > LOG_SLOW_MS)
				_log.debug(msg);
			else
				_log.trace(msg);
		}

		if (numrows == 0 || showSummary(elapsed)) {
			String msg = "**SQL** [" + getLabel(id) + "] took " + elapsed
			        / 1000.0 + "s";
			if (numrows >= 0)
				msg += " rows affected: " + numrows;
			if (elapsed > LOG_VERY_SLOW_MS)
				_log.error(msg);
			else if (elapsed > LOG_SLOW_MS)
				_log.debug(msg);
			else
				_log.trace(msg);
		}

	}

	protected static long tally(int id, long startTime) {
		long elapsed = System.currentTimeMillis() - startTime;
		SqlPWMonitor.inst().add(id, elapsed);
		return elapsed;
	}

	public static int update(int id, Statement stmt, String sql)
	        throws SQLException {
		long startTime = System.currentTimeMillis();
		try {
			int numRows = stmt.executeUpdate(sql);
			tally(id, startTime, sql, numRows);
			return numRows;
		} catch (SQLException se) {
			logSqlEx(id, se, sql);
			throw se;
		}
	}

	public static int update(int id, PreparedStatement stmt)
	        throws SQLException {
		long startTime = System.currentTimeMillis();
		try {
			int numRows = stmt.executeUpdate();
			tally(id, startTime, stmt, numRows);
			return numRows;
		} catch (SQLException se) {
			logSqlEx(id, se, "pstmt.executeUpdate() " + getSqlFromStmt(stmt));
			throw se;
		}
	}

	public static ResultSet query(int id, Statement stmt, String sql)
	        throws SQLException {
		long startTime = System.currentTimeMillis();
		try {
			ResultSet rs = stmt.executeQuery(sql);
			tally(id, startTime, sql);
			return rs;
		} catch (SQLException se) {
			logSqlEx(id, se, sql);
			throw se;
		}
	}

	public static ResultSet query(int id, PreparedStatement stmt)
	        throws SQLException {
		long startTime = System.currentTimeMillis();
		try {
			ResultSet rs = stmt.executeQuery();
			tally(id, startTime, stmt);
			return rs;
		} catch (SQLException se) {
			logSqlEx(id, se, "pstmt.executeQuery()" + getSqlFromStmt(stmt));
			throw se;
		}
	}

	public static int[] batch(int id, PreparedStatement pstmt)
	        throws BatchUpdateException, SQLException {
		long startTime = System.currentTimeMillis();
		try {
			int results[] = pstmt.executeBatch();
			tally(id, startTime, pstmt);
			dumpResults(results);
			return results;
		} catch (BatchUpdateException bex) {
			
			_log.debug("Batch exception....");
			_log.debug("Error Code...."+bex.getErrorCode());
			_log.debug("SQL State...."+bex.getSQLState());
			_log.debug("Error Message...."+bex.getMessage());
			
			int[] updateCounts = bex.getUpdateCounts();
			_log.debug("Update counts...."+updateCounts.length);
			
			SQLException nextException = bex.getNextException();

			for (int i = 0; i < updateCounts.length; i++) {
				if (updateCounts[i] >= 0) {
				}else if (updateCounts[i] == Statement.SUCCESS_NO_INFO || 
						updateCounts[i] == Statement.EXECUTE_FAILED){
					
					String errorMessage = "unknown";
					int errorCode = -1;
					
					if (nextException!=null){
						errorMessage = nextException.getMessage();
						errorCode = nextException.getErrorCode();
						nextException = nextException.getNextException();
					}else{
						_log.debug("Next Exception is null ");
					}
				
					_log.debug("Row Number  "+i+" Error Code is "+errorCode+" Error Message is "+errorMessage);
				}
			}
		
			logSqlEx(id, bex, "pstmt.executeBatch() " + getSqlFromStmt(pstmt));
			throw bex;
		} catch (SQLException se) {
			logSqlEx(id, se, "pstmt.executeBatch() " + getSqlFromStmt(pstmt));
			throw se;
		}
	}

	public static boolean exec(int id, PreparedStatement pstmt)
	        throws SQLException {
		long startTime = System.currentTimeMillis();
		try {
			boolean b = pstmt.execute();
			tally(id, startTime, pstmt);
			if (_log.isTraceEnabled())
				_log.trace("..exec result: " + b);
			return b;
		} catch (SQLException se) {
			logSqlEx(id, se, "pstmt.execute() " + getSqlFromStmt(pstmt));
			throw se;
		}
	}

	public static void setString(PreparedStatement pstmt, int paramNum,
	        ResultSet rs, String paramName) throws SQLException {
		String val = rs.getString(paramName);
		if (_log.isTraceEnabled())
			_log.trace("Set " + paramName + " = <" + val + ">");
		pstmt.setString(paramNum, val);
	}

	public static void setTimestamp(PreparedStatement pstmt, int paramNum,
	        ResultSet rs, String paramName) throws SQLException {
		Timestamp ts = rs.getTimestamp(paramName);
		if (_log.isTraceEnabled())
			_log.trace("Set tstamp " + paramName + " = <" + ts + ">");
		pstmt.setTimestamp(paramNum, ts);
	}

	// Added for use with JCC Type 4 Driver
	public static void setDate(PreparedStatement pstmt, int paramNum,
	        ResultSet rs, String paramName) throws SQLException {
		Date dt = rs.getDate(paramName);
		if (_log.isTraceEnabled())
			_log.trace("Set date " + paramName + " = <" + dt + ">");
		pstmt.setDate(paramNum, dt);
	}

	public static void lap(int id, long startTime)  {
		tally(id, startTime);
	}

	protected static void dumpResults(int[] results) {
		if (_log.isErrorEnabled()) {
			StringBuffer sb = new StringBuffer();
			for (int i = 0; i < results.length; i++) {
				// 0 or greater indicate that batch update not implemented
				// properly (MFE XDB Driver)
				if (results[i] < 0
				        && results[i] != Constants.BATCH_UPDATE_SUCCESS) {
					sb.append("**Error on results[" + i + "] = " + results[i]
					        + " ");
				}
			}
			if (sb.length() > 0)
				_log.error(sb.toString());
		}
	}

}
