
package com.csc.fsg.life.pw.io;

import java.sql.Connection;
import java.sql.ResultSet;

import java.sql.Statement;
import java.util.Iterator;
import java.util.Map;
import java.util.HashMap;
import java.util.List;

import com.csc.fsg.life.pw.util.*;
import com.csc.fsg.life.pw.environment.Environment;

/* Modifications: T0091, CCCV-E637, T0129 */

// TT wma SPR 6579 - class rewritten to provide subsetExists (boolean return)

public class TableRowCounter {

	/*private static Log _log = PWServerLogManager.getLog(TableRowCounter.class
	        .getPackage().getName());*/

	private Environment environment;

	public TableRowCounter(Environment environment) {
		this.environment = environment;
	}

	private int countSourceRowsForSubset(String tableId, String companyCode,
	        String productPrefix, String tableSubset) throws Exception {

		TableFilter sourceFilter = new TableFilter();
		sourceFilter.setTableSubset(tableSubset);
		if (productPrefix != null) {
			if (!(productPrefix.equals("N") || productPrefix.equals("C"))) 
				sourceFilter.setProductPrefix(productPrefix);
		}
		
		sourceFilter.setCompanyCode(companyCode);
		return environment.getTableDescMgr().getTableDescriptor(tableId).countTableRows(environment, sourceFilter);
	}

	public boolean subsetExists(String tableId, String companyCode,
	        String productPrefix, String tableSubset, List<String> projects) throws Exception {
		Connection applConn = null;
		int count = 0;
		try{
			applConn = DBConnMgr.getInstance().getConnection(environment.getId(),DBConnMgr.APPL);
			return subsetExists(applConn, tableId, companyCode, productPrefix, tableSubset, projects);
		}catch (Exception e) {
			throw e;
		}finally{
			DBConnMgr.getInstance().releaseConnection(applConn);
		}
	}

	public boolean subsetExists(Connection wipConn, String tableId, String companyCode,
	        String productPrefix, String tableSubset, List<String> projects) throws Exception {

		Map<String, Integer> wipCounts = getWIPcounts(companyCode, productPrefix, null, tableId, tableSubset, wipConn, projects);
		if ( wipCounts.containsKey("UPDATE") ) {
			if ( wipCounts.get("UPDATE") > 0 )
				return true;
		}

		if ( wipCounts.containsKey("INSERT") ) {
			if ( wipCounts.get("INSERT") > 0 )
				return true;
		}

		int count = 0;
		if ( wipCounts.containsKey("DELETE") )
			count -= wipCounts.get("DELETE").intValue();		
		count += countSourceRowsForSubset(tableId, companyCode, productPrefix, tableSubset);
		
		return (count > 0);
	}
	

	public int countRowsFromWIP(String companyCode,
	        String productPrefix, String tableDDLName, String tableId,
	        String tableSubset, Connection wipConn, List<String> projects)
	        throws  Exception {
		
		// CCCV-E637 - pass tableDDLName
		Map<String, Integer> wipCounts = getWIPcounts(companyCode, productPrefix, tableDDLName, tableId, tableSubset, wipConn, projects);
		int count = 0;
		if ( wipCounts.containsKey("INSERT") )
			count += wipCounts.get("INSERT").intValue();		
		if ( wipCounts.containsKey("DELETE") )
			count -= wipCounts.get("DELETE").intValue();		
		return count;
	}

	public Map<String, Integer> getWIPcounts(String companyCode,
	        String productPrefix, String tableDDLName, String tableId,
	        String tableSubset, Connection wipConn, List<String> projects)
	        throws  Exception {

		Statement stmt = null;
		ResultSet resultSet = null;
		StringBuffer sql = new StringBuffer(256);

		Map<String, Integer> results = new HashMap<String, Integer>();
		try 
		{
			sql.append("SELECT OPERATION,COUNT(*) FROM "+environment.getApplSchema()+".COMMONWIP");
			sql.append(" WHERE ENVIRONMENT = '"+environment.getId()+"' ");
			
			if (companyCode != null) 
				sql.append(" AND COMPANY_CODE = '"+ companyCode + "' ");
			
			if (productPrefix != null) 
				sql.append(" AND PRODUCT_PREFIX = '" + productPrefix + "' ");
			
			if (tableDDLName != null) 
				sql.append(" AND DDLNAME = '"+ tableDDLName+ "' ");
			
			if (tableId != null) 
				sql.append(" AND TABLE_ID = '"+ tableId + "' ");
			
			if (tableSubset != null) 
				sql.append(" AND SUBSET_NAME = '"+ tableSubset + "' ");
			
			if (projects!=null && !projects.isEmpty()){
				sql.append(" AND ( " );
				for (Iterator<String> iterator = projects.iterator(); iterator.hasNext();) {
					String proj =  iterator.next();
					sql.append(" PROJECT_NAME = '" ).append(proj.trim()).append("' ");
					if (iterator.hasNext())
						sql.append(" OR " );
				}
				sql.append(" )" );
			}
			
			sql.append(" GROUP BY OPERATION");
			
			stmt = wipConn.createStatement();
			
			resultSet = SqlPW.query(SqlPW.SQL_WIP_ROWS_FOR_CPY, stmt, sql.toString());
			
			while (resultSet.next()) 
				results.put(resultSet.getString(1), Integer.valueOf(resultSet.getInt(2)));
			
		} finally {
			Utils.closeResultSet(resultSet);
			Utils.closeStatement(stmt);
		}
		return results;
	}

	public Map<String, Integer> getWIPCountsByTable(String companyCode, Connection wipConn) throws  Exception {

		Statement stmt = null;
		ResultSet resultSet = null;
		StringBuffer sql = new StringBuffer(256);

		Map<String, Integer> results = new HashMap<String, Integer>();
		try 
		{
			sql.append("SELECT DDLNAME,OPERATION,COUNT(*) as numRows FROM "+environment.getApplSchema()+".COMMONWIP");
			sql.append(" WHERE ENVIRONMENT = '"+environment.getId()+"' ");
			sql.append(" AND COMPANY_CODE = '"+ companyCode + "' ");
			sql.append(" GROUP BY DDLNAME, OPERATION");
			
			stmt = wipConn.createStatement();
			
			resultSet = SqlPW.query(SqlPW.SQL_WIP_ROWS_FOR_CPY, stmt, sql.toString());
			
			while ( resultSet.next() ) {
				String ddlName = resultSet.getString("DDLNAME").trim();
				String operation = resultSet.getString("OPERATION").trim();
				int numRows = resultSet.getInt("numRows");
				Integer icount = results.get(ddlName);
				if ( icount == null ) {
					icount = Integer.valueOf(0);
					results.put(ddlName, icount);
				}
				int count = icount.intValue();
				if ( operation.equals("INSERT") )
					count += numRows;
				else if ( operation.equals("DELETE") )
					count -= numRows;
				results.put(ddlName, Integer.valueOf(count));
			}
			
		} finally {
			Utils.closeResultSet(resultSet);
			Utils.closeStatement(stmt);
		}
		return results;
	}
}
