/*
 * THIS PROGRAM IS THE PROPERTY OF CSC FINANCIAL SERVICES GROUP. IT MAY NOT BE
 * COPIED IN WHOLE OR IN PART WITHOUT THE EXPRESS WRITTEN CONSENT OF CSC
 * FINANCIAL SERVICES GROUP.
 */

package com.csc.fsg.life.pw.web.actions.tree;

import java.sql.*;
import java.util.*;

import com.csc.fsg.life.exceptions.WrapperException;
import com.csc.fsg.life.pw.common.transferobjects.PlanCriteriaTO;
import com.csc.fsg.life.pw.common.util.*;

import org.apache.commons.logging.Log;

import com.csc.fsg.life.pw.web.environment.*;
import com.csc.fsg.life.pw.web.io.descriptor.wma.T000XARow;
import com.csc.fsg.life.pw.web.log.PWServerLogManager;
import com.csc.fsg.life.pw.web.utils.SqlPW;

/* Modifications: T0103, ENH961 , CCCV-E768, T0129*/
// T0103 - IOTokenizer moved to common
// ENH961 - improve exception handling

/**
 * Class IndexMergeAssistent
 * 
 * @author CSC-FSG,E.Hartford
 * @version PW 2.0 , 09-24-2002
 */

public class IndexMergeAssistent {

	private static Log _log = PWServerLogManager
	        .getLog(IndexMergeAssistent.class.getPackage().getName());

	private HashMap _treeKey;

	private boolean _loadNP;

	private String _view;

	private WIPProperties wipProps = null;

	private Map<String, List<T000XARow>> treeCache = new HashMap<String, List<T000XARow>>();
	private boolean createTreeCache = false;
	
	/**
	 * Method clean
	 * 
	 * @param conn
	 * @throws Exception
	 */

	public void clean(Connection conn) throws Exception {

		Statement stmt = conn.createStatement();
		stmt.executeUpdate("DROP TABLE SESSION.MERGEDXA");
		stmt.executeUpdate("DROP TABLE SESSION.WIPXA");
		Utils.closeStatement(stmt);
	}


	/**
	 * Constructor IndexMergeAssistent
	 * 
	 * @param con
	 * @param treeKey
	 * @param loadNP
	 * @param view
	 * @throws Exception
	 */

	public IndexMergeAssistent(Connection con, HashMap treeKey, boolean loadNP,
	        String view) throws Exception {
		this(con, treeKey, loadNP, view, false);
	}

	public IndexMergeAssistent(Connection con, HashMap treeKey, boolean loadNP,
	        String view, boolean createTreeCache) throws Exception {
		_view = view;
		_loadNP = loadNP;
		_treeKey = treeKey;
		this.createTreeCache = createTreeCache;
		initTables(con);
	}

	private void initTables(Connection con) throws Exception {

		wipProps = WIPProperties.getInstance();
		StringBuffer sql = new StringBuffer(512);

		Statement stmt = null;
		ResultSet rs = null;

		try {

			stmt = con.createStatement();

			// ResultSet rs = null;
			sql.append(" DECLARE GLOBAL TEMPORARY TABLE SESSION.MERGEDXA ");
			sql.append(" ( ");
			sql.append(" COMPANY_CODE           CHAR(3)  NOT NULL , ");
			sql.append(" PRODUCT_PREFIX         CHAR(1)  NOT NULL , ");
			sql.append(" PRIMARY_TABLE_ID       CHAR(3)  NOT NULL , ");
			sql.append(" PRIMARY_PTR_SUBSET     CHAR(16) NOT NULL , ");
			sql.append(" SECONDARY_TABLE_ID     CHAR(3)  NOT NULL , ");
			sql.append(" SECNDRY_PTR_SUBSET     CHAR(16) NOT NULL , ");
			sql.append(" SECNDRY_TABLE_VAR      CHAR(1)  NOT NULL,  ");
			sql
			        .append(" OLD_CONCAT_KEY         VARCHAR(200) NOT NULL DEFAULT, ");
			sql.append(" PROJECT_NAME           CHAR(16) NOT NULL  DEFAULT , ");
			sql.append(" OPERATION              CHAR(6)  NOT NULL  DEFAULT , ");
			sql.append(" CHANGE_USER_ID         CHAR(32)  NOT NULL  DEFAULT , ");
			sql.append(" TIME_STAMP             TIMESTAMP NOT NULL  DEFAULT ");
			sql.append(" ) ");
			sql.append(" ON COMMIT PRESERVE ROWS ");
			// stmt.executeUpdate(sql.toString());
			SqlPW.update(SqlPW.SQL_INDEX_MERGE1, stmt, sql.toString());

			sql.setLength(0);
			sql.append("INSERT INTO SESSION.MERGEDXA ");
			sql.append(" ( COMPANY_CODE, PRODUCT_PREFIX,");
			sql
			        .append(" PRIMARY_TABLE_ID,PRIMARY_PTR_SUBSET,SECONDARY_TABLE_ID, ");
			sql.append(" SECNDRY_PTR_SUBSET,SECNDRY_TABLE_VAR");
			sql.append(" ) ");
			getLocalSelectClause(sql);
			getWhereClause(sql);
			// getOrderByClause(sql);
			// stmt.executeUpdate(sql.toString());
			SqlPW.update(SqlPW.SQL_INDEX_MERGE2, stmt, sql.toString());

			sql.setLength(0);
			sql.append("DECLARE GLOBAL TEMPORARY TABLE SESSION.WIPXA ");
			sql.append(" ( ");
			sql.append(" COMPANY_CODE           CHAR(3)  NOT NULL , ");
			sql.append(" PRODUCT_PREFIX         CHAR(1)  NOT NULL , ");
			sql.append(" PRIMARY_TABLE_ID       CHAR(3)  NOT NULL , ");
			sql.append(" PRIMARY_PTR_SUBSET     CHAR(16) NOT NULL , ");
			sql.append(" SECONDARY_TABLE_ID     CHAR(3)  NOT NULL , ");
			sql.append(" SECNDRY_PTR_SUBSET     CHAR(16) NOT NULL , ");
			sql.append(" SECNDRY_TABLE_VAR      CHAR(1)  NOT NULL , ");
			sql.append(" OPERATION              CHAR(6)  NOT NULL, ");
			sql.append(" OLD_CONCAT_KEY         VARCHAR(200) NOT NULL, ");
			sql.append(" PROJECT_NAME           CHAR(16) NOT NULL,  ");
			sql.append(" CHANGE_USER_ID         CHAR(32)  NOT NULL, ");
			sql.append(" TIME_STAMP             TIMESTAMP NOT NULL  ");
			sql.append(" ) ");
			sql.append(" ON COMMIT PRESERVE ROWS ");

			// stmt.executeUpdate(sql.toString());
			SqlPW.update(SqlPW.SQL_INDEX_MERGE3, stmt, sql.toString());

			sql.setLength(0);
			sql.append("INSERT INTO SESSION.WIPXA ");
			getWIPSelectClause(sql);
			getWipWhereClause(sql);

			// stmt.executeUpdate(sql.toString());
			SqlPW.update(SqlPW.SQL_INDEX_MERGE4, stmt, sql.toString());

			/*
			 * DEBUG
			 */
			if (_log.isTraceEnabled()) {
				rs = stmt.executeQuery("SELECT * FROM SESSION.MERGEDXA");
				_log.trace("\n\nINITIAL MERGEDXA");
				TreeUtils.dump(rs);
				Utils.closeResultSet(rs);
				rs = stmt.executeQuery("SELECT * FROM SESSION.WIPXA");
				_log.trace("INITIAL WIPXA");
				TreeUtils.dump(rs);
				_log.trace("\n\n");
				Utils.closeResultSet(rs);
			}

			sql = null;
			if (_view.equalsIgnoreCase("with")) {
				merge(con);
			}

			if ( createTreeCache ) {
				rs = stmt.executeQuery("SELECT * FROM SESSION.MERGEDXA");
				while ( rs.next() ) {
					T000XARow row = new T000XARow();
					row.setCompanyCode(rs.getString("COMPANY_CODE"));
					row.setProductPrefix(rs.getString("PRODUCT_PREFIX"));
					row.setPrimaryTableId(rs.getString("PRIMARY_TABLE_ID"));
					row.setPrimaryPtrSubset(rs.getString("PRIMARY_PTR_SUBSET"));
					row.setSecondaryTableId(rs.getString("SECONDARY_TABLE_ID"));
					row.setSecndryPtrSubset(rs.getString("SECNDRY_PTR_SUBSET"));
					row.setSecndryTableVar(rs.getString("SECNDRY_TABLE_VAR"));
					
					String key = row.getCompanyCode().trim() + row.getProductPrefix()
						+ row.getPrimaryTableId() + row.getPrimaryPtrSubset().trim();
					List<T000XARow> rows = treeCache.get(key);
					if ( rows == null ) {
						treeCache.put(key, new ArrayList<T000XARow>());
						rows = treeCache.get(key);
					}
					rows.add(row);
				}
			}
		}catch (Exception e) {
		    clean(con);
		    throw WrapperException.wrap(e);
        }  finally {
			Utils.closeResultSet(rs);
			Utils.closeStatement(stmt);
		}

	}

	private void merge(Connection con) throws Exception {

		Statement astmt = null;
		ResultSet ars = null;

		PreparedStatement uStmt = null;
		PreparedStatement iStmt = null;
		PreparedStatement dStmt = null;
	
		try{
			 astmt = con.createStatement();
			 ars = astmt.executeQuery("select * from SESSION.WIPXA ");	
	
			 String iSql = buildInsertSQL();
			 String uSql = buildUpdateSQL();
			 String dSql = buildDeleteSQL();
					
			 uStmt = con.prepareStatement(uSql);
			 iStmt = con.prepareStatement(iSql);
			 dStmt = con.prepareStatement(dSql);
			
			String d_company_code = null, k_company_code = null;
			String d_product_prefix = null, k_product_prefix = null;
			String d_primary_table_id = null, k_primary_table_id = null;
			String d_primary_ptr_subset = null, k_primary_ptr_subset = null;
			String d_secondary_table_id = null, k_secondary_table_id = null;
			String d_secndry_ptr_subset = null, k_secndry_ptr_subset = null;
			String d_secndry_table_var = null, k_secndry_table_var = null;
			String d_project_name = null;
			String d_operation = null;
			String d_change_user_id = null;
			// Fix for IBMDB2 v7.2 Fixpack 5
			// String d_time_stamp = null;
			Timestamp d_time_stamp = null;
	
			String old_concat_key = null;
			IOTokenizer t = null;
			String oper = null;
			while (ars.next()) {
				oper = ars.getString("OPERATION").trim();
				old_concat_key = ars.getString("OLD_CONCAT_KEY");
	
				d_company_code = ars.getString("COMPANY_CODE");
				d_product_prefix = ars.getString("PRODUCT_PREFIX");
				d_primary_table_id = ars.getString("PRIMARY_TABLE_ID");
				d_primary_ptr_subset = ars.getString("PRIMARY_PTR_SUBSET");
				d_secondary_table_id = ars.getString("SECONDARY_TABLE_ID");
				d_secndry_ptr_subset = ars.getString("SECNDRY_PTR_SUBSET");
				d_secndry_table_var = ars.getString("SECNDRY_TABLE_VAR");
				d_project_name = ars.getString("PROJECT_NAME");
				d_operation = ars.getString("OPERATION");
				d_change_user_id = ars.getString("CHANGE_USER_ID");
				// Fix for IBMDB2 v7.2 Fixpack 5
				// d_time_stamp = ars.getString("TIME_STAMP");
				d_time_stamp = ars.getTimestamp("TIME_STAMP");
	
				if (oper.equalsIgnoreCase("UPDATE")) {
					StringBuffer sbf = new StringBuffer();
	
					if (old_concat_key.indexOf("|") != -1) {
						throw new Exception(
						        "Old ConcatKey with pipe delimiter is found in IndexWIP");
					}
	
					TreeUtils.unpadColumns(old_concat_key, sbf, false);
					t = new IOTokenizer(sbf.toString(), "|");
	
					// t = new CscTokenizer(old_concat_key, "|");
					k_company_code = Utils.safeTrim(t.nextToken());
					k_product_prefix = Utils.safeTrim(t.nextToken());
					k_primary_table_id = Utils.safeTrim(t.nextToken());
					k_primary_ptr_subset = Utils.safeTrim(t.nextToken());
					k_secondary_table_id = Utils.safeTrim(t.nextToken());
					k_secndry_ptr_subset = Utils.safeTrim(t.nextToken());
					k_secndry_table_var = Utils.safeTrim(t.nextToken());
					// k_product_prefix = t.nextToken();
	
					uStmt.setString(1, d_company_code);
					uStmt.setString(2, d_product_prefix);
					uStmt.setString(3, d_primary_table_id);
					uStmt.setString(4, d_primary_ptr_subset);
					uStmt.setString(5, d_secondary_table_id);
					uStmt.setString(6, d_secndry_ptr_subset);
					uStmt.setString(7, d_secndry_table_var);
					uStmt.setString(8, old_concat_key);
					uStmt.setString(9, d_project_name);
					uStmt.setString(10, d_operation);
					uStmt.setString(11, d_change_user_id);
					// Fix for IBMDB2 v7.2 Fixpack 5
					// uStmt.setString(12, d_time_stamp);
					uStmt.setTimestamp(12, d_time_stamp);
					uStmt.setString(13, k_company_code);
					uStmt.setString(14, k_product_prefix);
					uStmt.setString(15, k_primary_table_id);
					uStmt.setString(16, k_primary_ptr_subset);
					uStmt.setString(17, k_secondary_table_id);
					uStmt.setString(18, k_secndry_ptr_subset);
					uStmt.setString(19, k_secndry_table_var);
	
					uStmt.execute();
				} else if (oper.equalsIgnoreCase("INSERT")) {
	
					iStmt.setString(1, d_company_code);
					iStmt.setString(2, d_product_prefix);
					iStmt.setString(3, d_primary_table_id);
					iStmt.setString(4, d_primary_ptr_subset);
					iStmt.setString(5, d_secondary_table_id);
					iStmt.setString(6, d_secndry_ptr_subset);
					iStmt.setString(7, d_secndry_table_var);
					iStmt.setString(8, old_concat_key);
					iStmt.setString(9, d_project_name);
					iStmt.setString(10, d_operation);
					iStmt.setString(11, d_change_user_id);
					// Fix for IBMDB2 v7.2 Fixpack 5
					// iStmt.setString(12, d_time_stamp);
					iStmt.setTimestamp(12, d_time_stamp);
	
					iStmt.execute();
				} else if (oper.equalsIgnoreCase("DELETE")) {
	
					StringBuffer sbf = new StringBuffer();
	
					old_concat_key = ars.getString("OLD_CONCAT_KEY");
	
					if (old_concat_key.indexOf("|") != -1) {
						throw new Exception(
						        "Old ConcatKey with pipe delimiter is found in IndexWIP");
					}
	
					TreeUtils.unpadColumns(old_concat_key, sbf, false);
					t = new IOTokenizer(sbf.toString(), "|");
	
					/*
					 * old_concat_key = ars.getString("OLD_CONCAT_KEY"); t = new
					 * CscTokenizer(old_concat_key, "|");
					 */
	
					k_company_code = Utils.safeTrim(t.nextToken());
					k_product_prefix = Utils.safeTrim(t.nextToken());
					k_primary_table_id = Utils.safeTrim(t.nextToken());
					k_primary_ptr_subset = Utils.safeTrim(t.nextToken());
					k_secondary_table_id = Utils.safeTrim(t.nextToken());
					k_secndry_ptr_subset = Utils.safeTrim(t.nextToken());
					k_secndry_table_var = Utils.safeTrim(t.nextToken());
					dStmt.setString(1, k_company_code);
					dStmt.setString(2, k_product_prefix);
					dStmt.setString(3, k_primary_table_id);
					dStmt.setString(4, k_primary_ptr_subset);
					dStmt.setString(5, k_secondary_table_id);
					dStmt.setString(6, k_secndry_ptr_subset);
					dStmt.setString(7, k_secndry_table_var);
					dStmt.execute();
				} else {
					throw new Exception("INVALID OPERATION (" + oper + ")");
				}
			}
		}finally{
			Utils.closeResultSet(ars);
			Utils.closeStatement(astmt);
			Utils.closePreparedStatement(uStmt);
			Utils.closePreparedStatement(dStmt);
			Utils.closePreparedStatement(iStmt);
			
			
		}

	}

	private String buildInsertSQL() {

		StringBuffer sb = new StringBuffer(256);

		sb.append(" INSERT INTO ");
		sb.append(" SESSION.MERGEDXA ");
		sb.append(" ( COMPANY_CODE, PRODUCT_PREFIX,");
		sb.append(" PRIMARY_TABLE_ID,PRIMARY_PTR_SUBSET,SECONDARY_TABLE_ID, ");
		sb.append(" SECNDRY_PTR_SUBSET,SECNDRY_TABLE_VAR");
		sb
		        .append(",OLD_CONCAT_KEY, PROJECT_NAME,OPERATION,CHANGE_USER_ID,TIME_STAMP");
		sb.append(" ) ");
		sb.append("VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
		return sb.toString();
	}

	private String buildUpdateSQL() {

		StringBuffer sb = new StringBuffer(256);

		sb.append(" UPDATE SESSION.MERGEDXA  ");
		sb.append(" SET ");
		sb.append(" COMPANY_CODE = ?,  ");
		sb.append(" PRODUCT_PREFIX = ?,  ");
		sb.append(" PRIMARY_TABLE_ID = ?,  ");
		sb.append(" PRIMARY_PTR_SUBSET= ?,  ");
		sb.append(" SECONDARY_TABLE_ID= ?,  ");
		sb.append(" SECNDRY_PTR_SUBSET= ?,  ");
		sb.append(" SECNDRY_TABLE_VAR= ?,  ");
		sb.append(" OLD_CONCAT_KEY= ?,  ");
		sb.append(" PROJECT_NAME= ?,  ");
		sb.append(" OPERATION= ?,  ");
		sb.append(" CHANGE_USER_ID= ?,  ");
		sb.append(" TIME_STAMP= ?  ");
		sb.append(" WHERE ");
		sb.append(" COMPANY_CODE = ?  ");
		sb.append(" AND PRODUCT_PREFIX = ?  ");
		sb.append(" AND PRIMARY_TABLE_ID = ?  ");
		sb.append(" AND PRIMARY_PTR_SUBSET= ?  ");
		sb.append(" AND SECONDARY_TABLE_ID= ?  ");
		sb.append(" AND SECNDRY_PTR_SUBSET= ?  ");
		sb.append(" AND SECNDRY_TABLE_VAR= ?  ");
		return sb.toString();
	}

	private String buildDeleteSQL() {

		StringBuffer sb = new StringBuffer(256);

		sb.append(" DELETE FROM SESSION.MERGEDXA ");
		sb.append(" WHERE ");
		sb.append(" COMPANY_CODE = ?  ");
		sb.append(" AND PRODUCT_PREFIX = ?  ");
		sb.append(" AND PRIMARY_TABLE_ID = ?  ");
		sb.append(" AND PRIMARY_PTR_SUBSET= ?  ");
		sb.append(" AND SECONDARY_TABLE_ID= ?  ");
		sb.append(" AND SECNDRY_PTR_SUBSET= ?  ");
		sb.append(" AND SECNDRY_TABLE_VAR= ?  ");
		return sb.toString();
	}

	private void getLocalSelectClause(StringBuffer sql) {

		Environment environment = EnvironmentManager.getInstance().getEnvironment((String)_treeKey.get("schema"));
		sql.append("SELECT COMPANY_CODE,PRODUCT_PREFIX,");
		sql.append(" PRIMARY_TABLE_ID, PRIMARY_PTR_SUBSET, SECONDARY_TABLE_ID, ");
		sql.append(" SECNDRY_PTR_SUBSET, SECNDRY_TABLE_VAR   ");
		sql.append(" FROM " + environment.getApplSchema() + ".T000XA");
	}

	private void getWIPSelectClause(StringBuffer sql) {
		Environment environment = EnvironmentManager.getInstance().getEnvironment((String)_treeKey.get("schema"));
		sql.append("SELECT COMPANY_CODE,PRODUCT_PREFIX,");
		sql.append(" PRIMARY_TABLE_ID, PRIMARY_PTR_SUBSET, SECONDARY_TABLE_ID, ");
		sql.append(" SECNDRY_PTR_SUBSET, SECNDRY_TABLE_VAR,   ");
		sql.append(" OPERATION,OLD_CONCAT_KEY");
		sql.append(",PROJECT_NAME,CHANGE_USER_ID,TIME_STAMP ");
		sql.append(" FROM " + environment.getApplSchema() + "."
		        + wipProps.getTableName(Constants.INDEX_WIP));
	}

	private void getWhereClause(StringBuffer sql) {

		if (!_treeKey.isEmpty()) {

			if (_treeKey.containsKey("schema")) {
				sql.append(" WHERE ENVIRONMENT = ");
				sql.append(" '").append(_treeKey.get("schema")).append("' ");
			}
			TreeUtils.writeWhereKey(_treeKey, "company_code", sql);

			if (_treeKey.containsKey("product_prefix")) {
				sql.append(" AND ( ( ");
				String pp = (String) _treeKey.get("product_prefix");

				sql.append(" product_prefix = '").append(pp).append("' ");

				sql.append(" ) ");

				if (_loadNP) {
					sql
					        .append(" OR ( PRODUCT_PREFIX='N' OR PRODUCT_PREFIX='H' ) )");
				} else {
					sql.append(")");
				}

			}
		}
	}

	private void getWipWhereClause(StringBuffer sql) {

		if (!_treeKey.isEmpty()) {

			if (_treeKey.containsKey("schema")) {
				sql.append(" WHERE ENVIRONMENT = ");
				sql.append(" '").append(_treeKey.get("schema")).append("' ");
			}

			/* Data Integrity Changes */

			if (_treeKey.containsKey("projects")) {
				Vector v = (Vector) _treeKey.get("projects");

				if (!v.firstElement().equals("None")) {
					sql.append(" AND PROJECT_NAME IN ( ");
					int len = v.size();

					for (int i = 0; i < len; i++) {
						if (i == (len - 1)) {
							sql.append("'" + v.get(i) + "'  )");
						} else {
							sql.append("'" + v.get(i) + "' , ");
						}
					}
				}
			}

			// T0103 - refactor plan key
			if (_treeKey.containsKey(PlanCriteriaTO.PROJECTS_KEY)) {
				String projectsStr = (String) _treeKey.get(PlanCriteriaTO.PROJECTS_KEY);
				String[] tokens = projectsStr.split("\n");
				sql.append(" AND PROJECT_NAME IN ( ");
				for (int i = 0; i < tokens.length; i++) {
					sql.append("'" + tokens[i] + "'");
					if (i == (tokens.length - 1)) {
						sql.append("  )");
					} else {
						sql.append(", ");
					}
				}
			}
			
			/* end */
			TreeUtils.writeWhereKey(_treeKey, "company_code", sql);

			if (_treeKey.containsKey("product_prefix")) {
				sql.append(" AND ( ( ");
				String pp = (String) _treeKey.get("product_prefix");

				sql.append(" product_prefix = '").append(pp).append("' ");

				sql.append(" ) ");

				if (_loadNP) {
					sql
					        .append(" OR ( PRODUCT_PREFIX='N' OR PRODUCT_PREFIX='H' ) )");
				} else {
					sql.append(")");
				}

				if (_treeKey.get("locked_rows") != null) {
					sql.append(" AND (promotion_lock");
					boolean lockedRows = ((Boolean) _treeKey.get("locked_rows"))
					        .booleanValue();

					if (lockedRows) {
						sql.append(" = 'L')");
					} else {
						sql.append(" != 'L')");
					}

				}
			}
		}
	}

	public List<T000XARow> getXAChildren(String companyCode, String productPrefix, String tableId, String subset) {
		String key = companyCode.trim() + productPrefix + tableId + subset.trim();
		return treeCache.get(key);
	}
}
