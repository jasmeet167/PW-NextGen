
package com.csc.fsg.life.pw.web.environment;

import java.sql.*;
import java.util.*;

import org.apache.commons.logging.Log;

import com.csc.fsg.life.exceptions.WrapperException;
import com.csc.fsg.life.pw.common.transferobjects.PlanRowTO;
import com.csc.fsg.life.pw.common.util.*;
import com.csc.fsg.life.pw.web.config.ProductManager;
import com.csc.fsg.life.pw.web.io.IndexTableQueryFilter;
import com.csc.fsg.life.pw.web.log.PWServerLogManager;
import com.csc.fsg.life.pw.web.utils.sql.SQLBuilderT000X;
import com.csc.fsg.life.pw.web.utils.*;

/* Modifications: CCCV-E199, T0103, T0091, T0129 */

public class EnvironmentLoader {
	
	private static Log _log = PWServerLogManager.getLog(EnvironmentLoader.class.getPackage().getName());
	
	public static void reloadLocal(Connection brConn, Connection applConn, Environment environment,HashSet<String> companies) throws Exception {

		_log.debug("Reloading "+environment.getId());
		
		String applSchema = environment.getApplSchema();

		String environmentId = environment.getId();

		String deleteT000X = "DELETE FROM " + applSchema+ ".T000X WHERE ENVIRONMENT = '" + environmentId + "' ";
		String deleteT000XA = "DELETE FROM " + applSchema+ ".T000XA WHERE ENVIRONMENT = '" + environmentId + "' ";

		StringBuffer where = new StringBuffer();
		if (companies!=null && !companies.isEmpty()){
			where.append(" AND COMPANY_CODE IN (");
			Iterator<String> iter = companies.iterator();
			while (iter.hasNext()) {
				where.append(" '" + iter.next() + "' ");
				if (iter.hasNext())
					where.append(", ");
			}
			where.append(") ");
		}
		
		Statement stmt = null;

		try {
			stmt = applConn.createStatement();
			stmt.executeUpdate(deleteT000X+where);
			stmt.executeUpdate(deleteT000XA+where);
			_log.debug("Delete data successfully from T000X and T000XA ");
			synchronizeLocalTables(applConn,brConn,environment,companies);
			_log.debug("Data reloaded successfully into T000X and T000XA ");
	
		} finally {
			Utils.closeStatement(stmt);
		}
	}
	
	
	public static void unLockRows(Connection applConn,Environment environment)  throws Exception {
		
		String applSchema = environment.getApplSchema();
		String environmentId = environment.getId();
		
		String commonWIPsql = "UPDATE "+applSchema+".COMMONWIP SET PROMOTION_LOCK = '' " +
							"WHERE ENVIRONMENT = '"+environmentId+"' AND PROMOTION_LOCK = 'L'";
		
		String planWIPsql = "UPDATE "+applSchema+".PLANWIP SET PROMOTION_LOCK = '' " +
							"WHERE ENVIRONMENT = '"+environmentId+"' AND PROMOTION_LOCK = 'L'";
		
		String indexWIPsql = "UPDATE "+applSchema+".INDEXWIP SET PROMOTION_LOCK = '' " +
							"WHERE ENVIRONMENT = '"+environmentId+"' AND PROMOTION_LOCK = 'L'";
		
		Statement stmt = null;
		try {
	
			stmt = applConn.createStatement();
			SqlPW.update(SqlPW.SQL_SCRUB_PW, stmt, commonWIPsql);
			SqlPW.update(SqlPW.SQL_SCRUB_PW, stmt, planWIPsql);
			SqlPW.update(SqlPW.SQL_SCRUB_PW, stmt, indexWIPsql);
			_log.debug("Unlocked WIP rows successfully in "+environmentId);	
	
		} finally {
			Utils.closeStatement(stmt);
		}
	}
	

	public static void synchronizeLocalTables(Connection applConn, Connection brConn, 
			Environment environment,HashSet<String> companies) throws Exception {

		String applSchema = environment.getApplSchema();
		String brSchema = environment.getSchema();
		
		String environmentId = environment.getId();
		
		PreparedStatement selectT000XPS = null;
		PreparedStatement selectT000XAPS = null;
		
		PreparedStatement insertT000XPS = null;
		PreparedStatement insertT000XAPS = null;
		
		ResultSet planTableRS = null;
		ResultSet indexTableRS = null;
		try {

			StringBuffer where = getWhereString(companies,environment);

			String selectT000X = "SELECT * FROM "+brSchema+".T000X  "+where;
			
			String selectT000XA = "SELECT * FROM "+brSchema+".T000XA  "+where;
						
			// refactor plan key
			String insertT000X = new SQLBuilderT000X(environmentId,SQLBuilderT000X.APPL).prepareInsertForReloadStatement();
			/*
			String insertT000X = "INSERT INTO "
					+ applSchema+ ".T000X (\"ENVIRONMENT\",COMPANY_CODE,PRODUCT_PREFIX, PRODUCT_SUFFIX,"
					+ "PLAN_CODE,ISSUE_STATE,LINE_OF_BUSINESS,EFFECTIVE_DATE,PLAN_TYPE,TABLE_PTR_ID,"
					+ "TABLE_PTR_SUBSET,TABLE_PTR_VAR) VALUES ( ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ? )";
			*/
			
			
			String insertT000XA = "INSERT INTO "
				+ applSchema+".T000XA (\"ENVIRONMENT\", COMPANY_CODE, PRODUCT_PREFIX, PRIMARY_TABLE_ID, "
				+ "PRIMARY_PTR_SUBSET, SECONDARY_TABLE_ID, SECNDRY_PTR_SUBSET, SECNDRY_TABLE_VAR ) "
				+ "VALUES ( ? , ? , ? , ? , ? , ? , ? , ? )";
			

			selectT000XPS = brConn.prepareStatement(selectT000X);
			selectT000XAPS = brConn.prepareStatement(selectT000XA);
			insertT000XPS = applConn.prepareStatement(insertT000X);
			insertT000XAPS = applConn.prepareStatement(insertT000XA);
				
			planTableRS = SqlPW.query(SqlPW.SQL_LOCAL_SYNC, selectT000XPS);
			
			int i = 0;
			while (planTableRS.next()) {
				// refactor plan key.
				//PlanRowTO planRow = new PlanRowTO(planTableRS);
				//planRow.setEnvironment(environmentId);
				//SQLBuilderT000X.setInsertValues(insertT000XPS, planRow);
				
				insertT000XPS.setString(1, environmentId);
				insertT000XPS.setString(2, planTableRS.getString("COMPANY_CODE").trim());
				insertT000XPS.setString(3, HighValueHandler.convertHVToAsterisk(planTableRS
						.getString("PRODUCT_PREFIX"), environmentId).trim());
				insertT000XPS.setString(4, HighValueHandler.convertHVToAsterisk(planTableRS
						.getString("PRODUCT_SUFFIX"), environmentId).trim());
				insertT000XPS.setString(5, planTableRS.getString("PLAN_CODE").trim());
				insertT000XPS.setString(6, HighValueHandler.convertHVToAsterisk(planTableRS
						.getString("ISSUE_STATE"), environmentId).trim());
				insertT000XPS.setString(7, HighValueHandler.convertHVToAsterisk(planTableRS
						.getString("LINE_OF_BUSINESS"), environmentId).trim());
				insertT000XPS.setObject(8, planTableRS.getString("EFFECTIVE_DATE").trim());
				insertT000XPS.setString(9, planTableRS.getString("PLAN_TYPE").trim());
				insertT000XPS.setString(10, planTableRS.getString("TABLE_PTR_ID").trim());
				insertT000XPS.setString(11, planTableRS.getString("TABLE_PTR_VAR").trim());
				insertT000XPS.setString(12, planTableRS.getString("TABLE_PTR_SUBSET").trim());
				
				insertT000XPS.addBatch();

				// Limit batch size
				if (i!=0 && i%environment.getProps().getPacketSize()==0)
					SqlPW.batch(SqlPW.SQL_LOCAL_SYNC, insertT000XPS);
				i++;
			}
			
			SqlPW.batch(SqlPW.SQL_LOCAL_SYNC, insertT000XPS);
			
			indexTableRS = SqlPW.query(SqlPW.SQL_LOCAL_SYNC, selectT000XAPS);
					
			i = 0;
			while (indexTableRS.next()) {
				insertT000XAPS.setString(1, environmentId.trim());
				insertT000XAPS.setString(2,indexTableRS.getString("COMPANY_CODE").trim());
				insertT000XAPS.setString(3, indexTableRS.getString("PRODUCT_PREFIX").trim());
				insertT000XAPS.setString(4, indexTableRS.getString("PRIMARY_TABLE_ID").trim());
				insertT000XAPS.setString(5, indexTableRS.getString("PRIMARY_PTR_SUBSET").trim());
				insertT000XAPS.setString(6, indexTableRS.getString("SECONDARY_TABLE_ID").trim());
				insertT000XAPS.setString(7, indexTableRS.getString("SECNDRY_PTR_SUBSET").trim());
				insertT000XAPS.setString(8, indexTableRS.getString("SECNDRY_TABLE_VAR").trim());
				insertT000XAPS.addBatch();
				
				// Limit batch size
				if (i!=0 && i%environment.getProps().getPacketSize()==0)
					SqlPW.batch(SqlPW.SQL_LOCAL_SYNC, insertT000XAPS);
				i++;
			}
			
			SqlPW.batch(SqlPW.SQL_LOCAL_SYNC, insertT000XAPS);
			

	
		} finally {
			Utils.closeResultSet(planTableRS);
			Utils.closeResultSet(indexTableRS);
			Utils.closePreparedStatement(selectT000XPS);
			Utils.closePreparedStatement(selectT000XAPS);
			Utils.closePreparedStatement(insertT000XPS);
			Utils.closePreparedStatement(insertT000XAPS);
		}
	}

	private static StringBuffer getWhereString(HashSet<String> companies,Environment environment) {
		StringBuffer where = new StringBuffer(120);

		if (companies != null && !companies.isEmpty()) {
			where.append(" WHERE COMPANY_CODE IN (");
			Iterator<String> iter = companies.iterator();
			while (iter.hasNext()) {
				where.append(" '" + iter.next().toString().toUpperCase()+ "' ");
				if (iter.hasNext())
					where.append(", ");
			}
			where.append(") ");
		}

		InstallConfigBean installBean = ProductManager.getInstance().getProduct(environment.getProductSystem()).getInstallBean();
		ArrayList al = installBean.getInstalledProducts();
		if (al.size() > 0) {
			if (where.length() == 0)
				where.append(" WHERE ( ");
			else
				where.append(" AND ( ");

			for (int i = 0; i < al.size(); i++) {
				if (i == 0)
					where.append(" PRODUCT_PREFIX" + " = '"
							+ (String) al.get(i) + "' ");
				else
					where.append(" OR  PRODUCT_PREFIX" + " = '"
							+ (String) al.get(i) + "' ");
			}
			where.append(" ) ");
		}
		return where;
	}
	
	public static void synchronizeLocalTables(String envId, Connection mfConn,
			IndexTableQueryFilter filter) throws Exception {
	

		Connection connToLocal = null;
		PreparedStatement deleteT000XAPS = null;
		PreparedStatement selectT000XAPS = null;

		try {
			Environment environment = EnvironmentManager.getInstance().getEnvironment(envId);

			connToLocal = DBConnMgr.getInstance().getConnection(envId,
					DBConnMgr.APPL);

			String where = buildIndexWhereSQL(filter);

			StringBuffer deleteT000XA = new StringBuffer();
			deleteT000XA.append("DELETE FROM ").append(environment.getApplSchema())
					.append(".").append("T000XA").append(where);
			deleteT000XAPS = connToLocal.prepareStatement(deleteT000XA
					.toString());
			SqlPW.update(SqlPW.SQL_LOCAL_SYNC, deleteT000XAPS);

			StringBuffer selectT000XA = new StringBuffer();
			// CCCV-E199 - bug fix.  Use getSchema, not getApplSchema
			selectT000XA.append("SELECT * FROM ").append(environment.getSchema()).append(".")
					.append("T000XA").append(where);
			selectT000XAPS = mfConn.prepareStatement(selectT000XA.toString());

			insertIndexes(SqlPW.query(SqlPW.SQL_LOCAL_SYNC, selectT000XAPS),
					envId, connToLocal);
	

	
		} finally {
			Utils.closePreparedStatement(deleteT000XAPS);
			Utils.closePreparedStatement(selectT000XAPS);
			DBConnMgr.getInstance().releaseConnection(connToLocal);
		}

	}
	
	private static void insertIndexes(ResultSet indexTableRS, String envId,
			Connection _connToLocal)  {

		String company = null, pp = null, pti = null, pts = null, sti = null, sps = null, stv = null;
		int i = 0;
		int[] results = null;
		PreparedStatement insertT000XAPS = null;
		Environment environment = EnvironmentManager.getInstance().getEnvironment(envId);
		try {

			StringBuffer insertT000XA = new StringBuffer();
			insertT000XA
					.append("INSERT INTO ")
					.append(environment.getApplSchema())
					.append(
							".T000XA (\"ENVIRONMENT\", COMPANY_CODE, PRODUCT_PREFIX, PRIMARY_TABLE_ID, "
									+ "PRIMARY_PTR_SUBSET, SECONDARY_TABLE_ID, SECNDRY_PTR_SUBSET, SECNDRY_TABLE_VAR ) "
									+ "VALUES ( ? , ? , ? , ? , ? , ? , ? , ? )");
			insertT000XAPS = _connToLocal.prepareStatement(insertT000XA
					.toString());

			while (indexTableRS.next()) {
				i++;
				company = indexTableRS.getString("COMPANY_CODE");
				pp = indexTableRS.getString("PRODUCT_PREFIX");
				pti = indexTableRS.getString("PRIMARY_TABLE_ID");
				pts = indexTableRS.getString("PRIMARY_PTR_SUBSET");
				sti = indexTableRS.getString("SECONDARY_TABLE_ID");
				sps = indexTableRS.getString("SECNDRY_PTR_SUBSET");
				stv = indexTableRS.getString("SECNDRY_TABLE_VAR");
				insertT000XAPS.setString(1, envId.trim());
				insertT000XAPS.setString(2, company.trim());
				insertT000XAPS.setString(3, pp.trim());
				insertT000XAPS.setString(4, pti.trim());
				insertT000XAPS.setString(5, pts.trim());
				insertT000XAPS.setString(6, sti.trim());
				insertT000XAPS.setString(7, sps.trim());
				insertT000XAPS.setString(8, stv.trim());
				insertT000XAPS.addBatch();
			}
			SqlPW.batch(SqlPW.SQL_LOCAL_SYNC, insertT000XAPS);
			_log.info("  SyncTables: inserted " + i
					+ " index rows into local.T000XA for " + envId);
		} catch (BatchUpdateException bex) {
			results = bex.getUpdateCounts();
			// MFE Driver not returning getUpdateCounts
			if (results == null || results.length == 0) {
				throw WrapperException.wrap(bex, "Insert Failed. Batch Update did not return row information");
			}
			for (int j = 0; j < results.length; j++) {
				if (results[j] < 0
				        && results[j] != Constants.BATCH_UPDATE_SUCCESS) {
					_log.error("Row "
					        + Integer.valueOf(j + 1)
					        + " failed to insert (RC="
					        + results[j] +")"
					        + "\n");
				}
			}
		} catch (SQLException se) {
			throw WrapperException.wrap(se);
		} finally {
			Utils.closeResultSet(indexTableRS);
			Utils.closePreparedStatement(insertT000XAPS);
		}
	}

	private static String buildIndexWhereSQL(IndexTableQueryFilter filter)
			throws Exception {

		StringBuffer sql = new StringBuffer();

		if (filter.getCompanyCode() == null) {
			throw new Exception("filter companyCode cannot be null");
		}

		sql.append(" WHERE ");
		sql.append("COMPANY_CODE" + " = '" + filter.getCompanyCode() + "' ");

		ArrayList<String> ProductPrefixes = filter.getProductPrefixes();
		if (ProductPrefixes != null) {
			if (ProductPrefixes.size() == 0) {
				throw new Exception("Empty productPrefixes");
			}
			for (int j = 0; j < ProductPrefixes.size(); j++) {
				if (j == 0) {
					sql.append(" AND ( " + "PRODUCT_PREFIX" + " = '"
							+ ProductPrefixes.get(j) + "' ");
				} else {
					sql.append(" OR " + "PRODUCT_PREFIX" + " = '"
							+ ProductPrefixes.get(j) + "' ");
				}
			}
			sql.append(" ) ");
		}

		ArrayList<String> ParentTableIds = filter.getParentTableIds();
		if (ParentTableIds != null) {
			if (ParentTableIds.size() == 0) {
				throw new Exception("Empty ParentTableIds");
			}
			for (int j = 0; j < ParentTableIds.size(); j++) {
				if (j == 0) {
					sql.append(" AND ( " + "PRIMARY_TABLE_ID" + " = '"
							+ ParentTableIds.get(j) + "' ");
				} else {
					sql.append(" OR " + "PRIMARY_TABLE_ID" + " = '"
							+ ParentTableIds.get(j) + "' ");
				}
			}
			sql.append(" ) ");
		}

		ArrayList<String> ParentTableSubsets = filter.getParentTableSubsets();
		if (ParentTableSubsets != null) {
			if (ParentTableSubsets.size() == 0) {
				throw new Exception("Empty ParentTableSubsets");
			}
			for (int j = 0; j < ParentTableSubsets.size(); j++) {
				if (j == 0) {
					sql.append(" AND ( " + "PRIMARY_PTR_SUBSET" + " = '"
							+ ParentTableSubsets.get(j) + "' ");
				} else {
					sql.append(" OR " + "PRIMARY_PTR_SUBSET" + " = '"
							+ ParentTableSubsets.get(j) + "' ");
				}
			}
			sql.append(" ) ");
		}

		ArrayList<String> ChildTableIds = filter.getChildTableIds();
		if (ChildTableIds != null) {
			if (ChildTableIds.size() == 0) {
				throw new Exception("Empty ChildTableIds");
			}
			for (int j = 0; j < ChildTableIds.size(); j++) {
				if (j == 0) {
					sql.append(" AND ( " + "SECONDARY_TABLE_ID" + " = '"
							+ ChildTableIds.get(j) + "' ");
				} else {
					sql.append(" OR " + "SECONDARY_TABLE_ID" + " = '"
							+ ChildTableIds.get(j) + "' ");
				}
			}
			sql.append(" ) ");
		}

		ArrayList<String> ChildTableSubsets = filter.getChildTableSubsets();
		if (ChildTableSubsets != null) {
			if (ChildTableSubsets.size() == 0) {
				throw new Exception("Empty ChildTableSubsets");
			}
			for (int j = 0; j < ChildTableSubsets.size(); j++) {
				if (j == 0) {
					sql.append(" AND ( " + "SECNDRY_PTR_SUBSET" + " = '"
							+ ChildTableSubsets.get(j) + "' ");
				} else {
					sql.append(" OR " + "SECNDRY_PTR_SUBSET" + " = '"
							+ ChildTableSubsets.get(j) + "' ");
				}
			}
			sql.append(" ) ");
		}

		ArrayList<String> ChildTableVars = filter.getChildTableVars();
		if (ChildTableVars != null) {
			if (ChildTableVars.size() == 0) {
				throw new Exception("Empty ChildTableVars");
			}
			for (int j = 0; j < ChildTableVars.size(); j++) {
				if (j == 0) {
					sql.append(" AND ( " + "SECNDRY_TABLE_VAR" + " = '"
							+ ChildTableVars.get(j) + "' ");
				} else {
					sql.append(" OR " + "SECNDRY_TABLE_VAR" + " = '"
							+ ChildTableVars.get(j) + "' ");
				}
			}
			sql.append(" ) ");
		}

		return sql.toString();
	}

}
