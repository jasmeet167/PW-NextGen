/*
 * THIS PROGRAM IS THE PROPERTY OF CSC FINANCIAL SERVICES GROUP. IT MAY NOT BE
 * COPIED IN WHOLE OR IN PART WITHOUT THE EXPRESS WRITTEN CONSENT OF CSC
 * FINANCIAL SERVICES GROUP.
 */

package com.csc.fsg.life.pw.web.actions.tree;

import java.sql.*;
import java.util.*;

import org.apache.commons.logging.Log;

import com.csc.fsg.life.pw.common.transferobjects.PlanCriteriaTO;
import com.csc.fsg.life.pw.common.transferobjects.PlanRowTO;
import com.csc.fsg.life.pw.common.util.*;
import com.csc.fsg.life.pw.web.environment.*;
import com.csc.fsg.life.pw.web.io.InfoException;
import com.csc.fsg.life.pw.web.log.PWServerLogManager;
import com.csc.fsg.life.pw.web.utils.*;
import com.csc.fsg.life.pw.web.utils.sql.SQLBuilderMERGEDX;
import com.csc.fsg.life.pw.web.utils.sql.SQLBuilderPLANWIP;
import com.csc.fsg.life.pw.web.utils.sql.SQLBuilderT000X;
import com.csc.fsg.life.pw.web.utils.sql.SQLBuilderWIPX;

/* Modifications: T0103, T0091, ENH961, WMABASEIXI-2749, CCCV-E768, T0129 */
// ENH961 - improve exception handling

/**
 * Class PlanMergeAssistent
 * 
 * @author CSC-FSG,E.Hartford
 * @version PW 2.0 , 09-24-2002
 */

public class PlanMergeAssistent {

	private static Log _log = PWServerLogManager
	        .getLog(PlanMergeAssistent.class.getPackage().getName());

	// refactor plan key.
	private PlanCriteriaTO planCriteria;
	private List<PlanCriteriaTO> planCriteriaList = null;
	/*
	private HashMap _treeKey;

	private boolean _loadNP;

	private String _view;

	private ArrayList _treeKeys;
*/	
	private WIPProperties wipProps;

	//private boolean newWIPRows;

	private Map<String, Set<PlanRowTO>> treeCache = new TreeMap<String, Set<PlanRowTO>>();
	private boolean createTreeCache = false;

	/**
	 * Method clean
	 * 
	 * @param conn
	 * @throws Exception
	 */

	public void clean(Connection conn) throws Exception {
		Statement stmt = conn.createStatement();
		stmt.executeUpdate("DROP TABLE SESSION.MERGEDX");
		stmt.executeUpdate("DROP TABLE SESSION.WIPX");
		Utils.closeStatement(stmt);
	}

	public PlanMergeAssistent(Connection conn, PlanCriteriaTO planCriteria) throws Exception {
		this.planCriteria = planCriteria;
		initTables(conn);
	}

	public PlanMergeAssistent(Connection conn, List<PlanCriteriaTO> planCriteriaList) throws Exception {
		this(conn, planCriteriaList, false);
	}

	public PlanMergeAssistent(Connection conn, List<PlanCriteriaTO> planCriteriaList, boolean createTreeCache) throws Exception {
		this.planCriteriaList = planCriteriaList;
		this.createTreeCache = createTreeCache;
		initTables(conn);
	}

	// refactor plan key.
	/* @deprecated use PlanMergeAssistent(Connection, PlanCriteriaTO)
	public PlanMergeAssistent(Connection con, HashMap treeKey, boolean loadNP,
	        String view) throws Exception {

		_view = view;
		_loadNP = loadNP;
		_treeKey = treeKey;
		initTables(con);
	}

	public PlanMergeAssistent(Connection con, ArrayList treeKeys,
	        boolean loadNP, String view) throws Exception {

		_view = view;
		_loadNP = loadNP;
		_treeKeys = treeKeys;
		initTables(con);
	}
	*/

	// refactor plan sql
	private void initTables(Connection con) throws Exception {
		wipProps = WIPProperties.getInstance();
	//	newWIPRows = false;
		StringBuffer sql = new StringBuffer();
		PreparedStatement iStmt = null;
		PreparedStatement iWIPStmt = null;
		Statement createStmt = null;
		Statement queryStmt = null;
		ResultSet rs = null;
		
		try {
			// refactor plan key. Make sure planCriteria is set for SQLBuilders
			if ( (planCriteriaList != null) && !planCriteriaList.isEmpty()) {
				planCriteria = planCriteriaList.iterator().next();
			}
			
			createStmt = con.createStatement();
			createTables(createStmt);

			String iSql = new SQLBuilderMERGEDX(planCriteria.getEnvironment()).prepareInsertFromT000XStatement();
			String iWIPSql = new SQLBuilderWIPX(planCriteria.getEnvironment()).prepareInsertStatement();

			iStmt = con.prepareStatement(iSql);
			iWIPStmt = con.prepareStatement(iWIPSql);
			queryStmt = con.createStatement();

			// refactor plan key.
			if ( (planCriteriaList != null) && !planCriteriaList.isEmpty()) {
				Iterator<PlanCriteriaTO> iterator = planCriteriaList.iterator();

				while (iterator.hasNext()) {
					planCriteria = iterator.next();
					populateMergedX(sql, iStmt, queryStmt);
					populateWIPX(sql, iWIPStmt, queryStmt);
				}
			} else {
				populateMergedX(sql, iStmt, queryStmt);
				populateWIPX(sql, iWIPStmt, queryStmt);
			}
			
			
			sql = null;

			if (planCriteria.isViewChanges())
				merge(con);

			if ( createTreeCache ) {
				rs = queryStmt.executeQuery("SELECT * FROM SESSION.MERGEDX");
				while ( rs.next() ) {
					PlanRowTO row = new PlanRowTO(rs, planCriteria.getEnvironment());
					
					String key = row.getPlanKey("|");
					Set<PlanRowTO> rows = treeCache.get(key);
					if ( rows == null ) {
						treeCache.put(key, new TreeSet<PlanRowTO>());
						rows = treeCache.get(key);
					}
					rows.add(row);
				}
			}
		} catch (Exception e) {
		    clean(con);
		    throw e;
		} finally {
			Utils.closeResultSet(rs);
			Utils.closeStatement(createStmt);
			Utils.closeStatement(queryStmt);
			Utils.closePreparedStatement(iStmt);
			Utils.closePreparedStatement(iWIPStmt);			

		}
	}
	/*
	private void initTables(Connection con) throws Exception {

		wipProps = WIPProperties.getInstance();
		newWIPRows = false;
		StringBuffer sql = new StringBuffer();
		PreparedStatement iStmt = null;
		PreparedStatement iWIPStmt = null;
		Statement createStmt = null;
		Statement queryStmt = null;

		try {
			createStmt = con.createStatement();
			createTables(sql, createStmt);

			String iSql = buildMergedXInsertSQL();
			String iWIPSql = buildWIPXInsertSQL();

			iStmt = con.prepareStatement(iSql);
			iWIPStmt = con.prepareStatement(iWIPSql);
			queryStmt = con.createStatement();

			if ((_treeKeys != null) && !_treeKeys.isEmpty()) {
				Iterator iterator = _treeKeys.iterator();

				while (iterator.hasNext()) {
					_treeKey = (HashMap) iterator.next();
					populateMergedX(sql, iStmt, queryStmt);
					populateWIPX(sql, iWIPStmt, queryStmt);
				}

			} else {
				populateMergedX(sql, iStmt, queryStmt);
				populateWIPX(sql, iWIPStmt, queryStmt);
			}

			sql = null;

			if (_view.equalsIgnoreCase("with"))
				merge(con);

		}catch (Exception e) {
		    clean(con);
		    throw WrapperException.wrap(e);
        } finally {
			Utils.closeStatement(iStmt);
			Utils.closeStatement(iWIPStmt);
			Utils.closeStatement(queryStmt);

		}
	}
	*/

	/**
	 * @param sql
	 * @param iWIPStmt
	 * @param queryStmt
	 * @throws SQLException
	 */
	// refactor plan sql
	private void populateWIPX(StringBuffer sql, PreparedStatement iWIPStmt, Statement queryStmt) throws SQLException {
		
		Environment environment = EnvironmentManager.getInstance().getEnvironment(planCriteria.getEnvironment());
		sql.setLength(0);
		String select = new SQLBuilderPLANWIP(planCriteria).buildSelectForMergeStatement();
		
		ResultSet queryRs = null;
		// Limit batch size
		int i=0;
		try{
			queryRs = queryStmt.executeQuery(select);
			while (queryRs.next()) {
				SQLBuilderWIPX.setInsertValues(iWIPStmt, queryRs);
				iWIPStmt.addBatch();
				if (i!=0 && i%environment.getProps().getPacketSize()==0)
					iWIPStmt.executeBatch();
				i++;
			}
			iWIPStmt.executeBatch();
		}catch (BatchUpdateException be) {
			StringBuffer errorBuffer = new StringBuffer();
			errorBuffer.append(be.getErrorCode()+" "+be.getMessage()+"\n");
			SQLException SQLe = be.getNextException();
			while (SQLe != null) {
				errorBuffer.append(SQLe.getMessage()).append("\n");
				SQLe = SQLe.getNextException();
			}

			throw new SQLException(errorBuffer.toString());
		}finally{
			Utils.closeResultSet(queryRs);
		}
	}
	/*
	private void populateWIPX(
		StringBuffer sql,
		PreparedStatement iWIPStmt,
		Statement queryStmt)
		throws SQLException {
		ResultSet queryRs;
		sql.setLength(0);
		getWIPSelectClause(sql);
		getWIPWhereClause(sql);
		queryRs = queryStmt.executeQuery(sql.toString());
		while (queryRs.next()) {
			setValuesForWIPXInsert(iWIPStmt, queryRs);
			iWIPStmt.addBatch();
		}
		
		try{
		    iWIPStmt.executeBatch();
		}catch (BatchUpdateException be) {
			StringBuffer errorBuffer = new StringBuffer();
			errorBuffer.append(be.getErrorCode()+" "+be.getMessage()+"\n");
			SQLException SQLe = be.getNextException();
			while (SQLe != null) {
				errorBuffer.append(SQLe.getMessage()).append("\n");
				SQLe = SQLe.getNextException();
			}

			throw new SQLException(errorBuffer.toString());
		}
		
	}
	*/

	/**
	 * @param sql
	 * @param iStmt
	 * @param queryStmt
	 * @throws SQLException
	 */
	// refactor plan sql
	private void populateMergedX(StringBuffer sql, PreparedStatement iStmt,
	        Statement queryStmt) throws SQLException {
	
		Environment environment = EnvironmentManager.getInstance().getEnvironment(planCriteria.getEnvironment());
		sql.setLength(0);
		String select = new SQLBuilderT000X(planCriteria).buildSelectForMergeStatement();
		
		ResultSet queryRs = null;
			
		// Limit batch size
		int i = 0;
		try{
			queryRs = queryStmt.executeQuery(select);
			while (queryRs.next()) {
				SQLBuilderMERGEDX.setInsertFromT000XValues(iStmt, queryRs);
				iStmt.addBatch();
				if (i!=0 && i%environment.getProps().getPacketSize()==0)
				    iStmt.executeBatch();
				i++;
			}
		    iStmt.executeBatch();
		}catch (BatchUpdateException be) {
			StringBuffer errorBuffer = new StringBuffer();
			errorBuffer.append(be.getErrorCode()+" "+be.getMessage()+"\n");
			SQLException SQLe = be.getNextException();
			while (SQLe != null) {
				errorBuffer.append(SQLe.getMessage()).append("\n");
				SQLe = SQLe.getNextException();
			}

			throw new SQLException(errorBuffer.toString());
		}finally{
			Utils.closeResultSet(queryRs);
		}
	}
	/*
	private void populateMergedX(
		StringBuffer sql,
		PreparedStatement iStmt,
		Statement queryStmt)
		throws SQLException {
		ResultSet queryRs;
		sql.setLength(0);
		getLocalSelectClause(sql);
		getWhereClause(sql);
		queryRs = queryStmt.executeQuery(sql.toString());
		while (queryRs.next()) {
			setValuesForMergedXInsert(iStmt, queryRs);
			iStmt.addBatch();
		}
		
		try{
		    iStmt.executeBatch();
		}catch (BatchUpdateException be) {
			StringBuffer errorBuffer = new StringBuffer();
			errorBuffer.append(be.getErrorCode()+" "+be.getMessage()+"\n");
			SQLException SQLe = be.getNextException();
			while (SQLe != null) {
				errorBuffer.append(SQLe.getMessage()).append("\n");
				SQLe = SQLe.getNextException();
			}

			throw new SQLException(errorBuffer.toString());
		}
	}
		*/

	// refactor plan sql
	private void createTables(Statement statement)  throws SQLException{
		String sql;

		sql = new SQLBuilderMERGEDX(planCriteria.getEnvironment()).buildDeclareTableStatement();
		SqlPW.update(SqlPW.SQL_PLAN_MERGE1, statement, sql);

		sql = new SQLBuilderWIPX(planCriteria.getEnvironment()).buildDeclareTableStatement();
		SqlPW.update(SqlPW.SQL_PLAN_MERGE2, statement, sql);
	
	}
	/*
	private void createTables(StringBuffer sql, Statement stmt)
	        throws SQLException {

		try {
			sql.append(" DECLARE GLOBAL TEMPORARY TABLE SESSION.MERGEDX ");
			sql.append(" ( ");
			sql.append(" COMPANY_CODE             CHAR(3) NOT NULL , ");
			sql.append(" PRODUCT_PREFIX           CHAR(1) NOT NULL , ");
			sql.append(" PRODUCT_SUFFIX           CHAR(1) NOT NULL , ");
			sql.append(" PLAN_CODE                CHAR(6) NOT NULL , ");
			sql.append(" ISSUE_STATE              CHAR(3) NOT NULL , ");
			sql.append(" LINE_OF_BUSINESS         CHAR(3) NOT NULL , ");
			sql.append(" EFFECTIVE_DATE           DATE NOT NULL , ");
			sql.append(" PLAN_TYPE                CHAR(1) NOT NULL , ");
			sql.append(" TABLE_PTR_ID             CHAR(3) NOT NULL , ");
			sql.append(" TABLE_PTR_VAR            CHAR(1) NOT NULL , ");
			sql.append(" TABLE_PTR_SUBSET         CHAR(16) NOT NULL , ");
			sql.append(" OLD_COMPANY_CODE         CHAR(3) NOT NULL DEFAULT, ");
			sql.append(" OLD_PRODUCT_PREFIX       CHAR(1) NOT NULL DEFAULT, ");
			sql.append(" OLD_PRODUCT_SUFFIX       CHAR(1) NOT NULL DEFAULT, ");
			sql.append(" OLD_PLAN_CODE            CHAR(6) NOT NULL DEFAULT, ");
			sql.append(" OLD_ISSUE_STATE          CHAR(3) NOT NULL DEFAULT, ");
			sql.append(" OLD_LOB                  CHAR(3) NOT NULL DEFAULT, ");
			sql
			        .append(" OLD_EFFECTIVE_DATE       DATE NOT NULL WITH DEFAULT, ");
			sql.append(" OLD_PLAN_TYPE            CHAR(1) NOT NULL DEFAULT, ");
			sql.append(" OLD_PTR_ID               CHAR(3) NOT NULL DEFAULT, ");
			sql.append(" OLD_PTR_VAR              CHAR(1) NOT NULL DEFAULT, ");
			sql.append(" OLD_PTR_SUBSET           CHAR(16) NOT NULL DEFAULT, ");
			sql.append(" PROJECT_NAME             CHAR(16) NOT NULL DEFAULT, ");
			sql.append(" OPERATION                CHAR(6) NOT NULL DEFAULT, ");
			sql.append(" CHANGE_USER_ID           CHAR(32) NOT NULL DEFAULT, ");
			sql.append(" TIME_STAMP               TIMESTAMP NOT NULL DEFAULT ");
			sql.append(" ) ");
			sql.append(" ON COMMIT PRESERVE ROWS ");
			SqlPW.update(SqlPW.SQL_PLAN_MERGE1, stmt, sql.toString());

			sql.setLength(0);
			sql.append("DECLARE GLOBAL TEMPORARY TABLE SESSION.WIPX ");
			sql.append(" ( ");
			sql.append(" COMPANY_CODE             CHAR(3) NOT NULL , ");
			sql.append(" PRODUCT_PREFIX           CHAR(1) NOT NULL , ");
			sql.append(" PRODUCT_SUFFIX           CHAR(1) NOT NULL , ");
			sql.append(" PLAN_CODE                CHAR(6) NOT NULL , ");
			sql.append(" ISSUE_STATE              CHAR(3) NOT NULL , ");
			sql.append(" LINE_OF_BUSINESS         CHAR(3) NOT NULL , ");
			sql.append(" EFFECTIVE_DATE           DATE NOT NULL , ");
			sql.append(" PLAN_TYPE                CHAR(1) NOT NULL , ");
			sql.append(" TABLE_PTR_ID             CHAR(3) NOT NULL , ");
			sql.append(" TABLE_PTR_VAR            CHAR(1) NOT NULL , ");
			sql.append(" TABLE_PTR_SUBSET         CHAR(16) NOT NULL , ");
			sql.append(" OPERATION                CHAR(6) NOT NULL, ");
			sql.append(" OLD_COMPANY_CODE         CHAR(3) NOT NULL, ");
			sql.append(" OLD_PRODUCT_PREFIX       CHAR(1) NOT NULL, ");
			sql.append(" OLD_PRODUCT_SUFFIX       CHAR(1) NOT NULL, ");
			sql.append(" OLD_PLAN_CODE            CHAR(6) NOT NULL, ");
			sql.append(" OLD_ISSUE_STATE          CHAR(3) NOT NULL, ");
			sql.append(" OLD_LOB                  CHAR(3) NOT NULL, ");
			sql
			        .append(" OLD_EFFECTIVE_DATE       DATE NOT NULL WITH DEFAULT, ");
			sql.append(" OLD_PLAN_TYPE            CHAR(1) NOT NULL, ");
			sql.append(" OLD_PTR_ID               CHAR(3) NOT NULL, ");
			sql.append(" OLD_PTR_VAR              CHAR(1) NOT NULL, ");
			sql.append(" OLD_PTR_SUBSET           CHAR(16) NOT NULL, ");
			sql.append(" PROJECT_NAME             CHAR(16) NOT NULL,");
			sql.append(" CHANGE_USER_ID           CHAR(32)  NOT NULL,");
			sql.append(" TIME_STAMP               TIMESTAMP NOT NULL");
			sql.append(" ) ");
			sql.append(" ON COMMIT PRESERVE ROWS ");
			SqlPW.update(SqlPW.SQL_PLAN_MERGE2, stmt, sql.toString());
		} catch (SQLException se) {
			throw WrapperException.wrap(se, SQLException.class);
		}
		
	}
	*/

	// WMABASEIXI-2749 - try to improve performance by batching updates/inserts/deletes
	private void merge(Connection con) throws Exception {

		String oper = null;
		// refactor plan sql.
		String iSql = new SQLBuilderMERGEDX(planCriteria.getEnvironment()).prepareInsertStatement();
		String uSql = new SQLBuilderMERGEDX(planCriteria.getEnvironment()).prepareUpdateStatement();
		String dSql = new SQLBuilderMERGEDX(planCriteria.getEnvironment()).prepareDeleteStatement();
		/*
		String iSql = buildMergedXInsertSQLFromWIPX();
		String uSql = buildMergedXUpdateSQL();
		String dSql = buildMergedXDeleteSQL();
		*/
		
		PreparedStatement uStmt = null;
		PreparedStatement iStmt = null;
		PreparedStatement dStmt = null;
		Statement astmt = null;
		Statement mstmt = null;
		ResultSet ars = null;

		try {
			uStmt = con.prepareStatement(uSql);
			iStmt = con.prepareStatement(iSql);
			dStmt = con.prepareStatement(dSql);
			astmt = con.createStatement();
			mstmt = con.createStatement();

			int numUpdates = 0;
			int numInserts = 0;
			int numDeletes = 0;
			
			// Get rows from the WIP to merge
			ars = astmt.executeQuery(new SQLBuilderWIPX(planCriteria.getEnvironment()).buildSelectTableStatement());

			while (ars.next()) {
				oper = ars.getString("OPERATION").trim();

				if (oper.equalsIgnoreCase("UPDATE")) {
					if (matchSourceFromMERGEDX(mstmt, ars)) {
						SQLBuilderMERGEDX.setUpdateValues(uStmt, ars);
						uStmt.addBatch();
						numUpdates++;
					} else if (matchSourceFromT000X(mstmt, ars)) {
						SQLBuilderMERGEDX.setInsertFromWIPXValues(iStmt, ars);
						iStmt.addBatch();
						numInserts++;
					} else {
						_log.error("Bad Data in WIP. Row not found in T000X for WIP row");
						_log.debug(ars.getString(wipProps.getOldCompanyCode())+" "+ars.getString(wipProps.getOldPlanCode())+" "+ars.getString(wipProps.getProjectName()));
						throw new InfoException("Update Plan WIP row key does not match any row in T000X");
					}
				} else if (oper.equalsIgnoreCase("INSERT")) {
					SQLBuilderMERGEDX.setInsertFromWIPXValues(iStmt, ars);
					iStmt.addBatch();
					numInserts++;
				} else if (oper.equalsIgnoreCase("DELETE")) {
					SQLBuilderMERGEDX.setDeleteValues(dStmt, ars);
					dStmt.addBatch();
					numDeletes++;
				} else {
					throw new Exception("INVALID OPERATION (" + oper + ")");
				}
			}
			if ( numUpdates > 0 )
				uStmt.executeBatch();
			if ( numInserts > 0 )
				iStmt.executeBatch();
			if ( numDeletes > 0 )
				dStmt.executeBatch();
			
		//	Utils.closeResultSet(ars);
		//	Utils.closeStatement(astmt);

	
		} finally {
			Utils.closeResultSet(ars);
			Utils.closeStatement(astmt);
			Utils.closeStatement(uStmt);
			Utils.closeStatement(dStmt);
			Utils.closeStatement(iStmt);
			Utils.closeStatement(mstmt);
		}
	}

	// refactor plan sql
	/* @deprecated use SQLBuilderMERGEDX.buildSelectForMergeStatement()
	private void getLocalSelectClause(StringBuffer sql) {
		Environment environment = EnvironmentManager.getInstance().getEnvironment((String)_treeKey.get("schema"));
		sql.append(" SELECT COMPANY_CODE,PRODUCT_PREFIX,PRODUCT_SUFFIX,PLAN_CODE,");
		sql.append(" ISSUE_STATE,LINE_OF_BUSINESS,EFFECTIVE_DATE,PLAN_TYPE,TABLE_PTR_ID, ");
		sql.append(" TABLE_PTR_VAR,TABLE_PTR_SUBSET");
		sql.append(" FROM " + environment.getApplSchema() + ".T000X");
	}

	private void getWIPSelectClause(StringBuffer sql) {
		Environment environment = EnvironmentManager.getInstance().getEnvironment((String)_treeKey.get("schema"));
		sql
		        .append(" SELECT COMPANY_CODE,PRODUCT_PREFIX,PRODUCT_SUFFIX,PLAN_CODE,");
		sql
		        .append(" ISSUE_STATE,LINE_OF_BUSINESS,EFFECTIVE_DATE,PLAN_TYPE,TABLE_PTR_ID,");
		sql.append(" TABLE_PTR_VAR,TABLE_PTR_SUBSET,OPERATION,");
		sql.append(" OLD_COMPANY_CODE,");
		sql.append(" OLD_PRODUCT_PREFIX,");
		sql.append(" OLD_PRODUCT_SUFFIX,");
		sql.append(" OLD_PLAN_CODE,");
		sql.append(" OLD_ISSUE_STATE,");
		sql.append(" OLD_LOB,");
		sql.append(" OLD_EFFECTIVE_DATE,");
		sql.append(" OLD_PLAN_TYPE,");
		sql.append(" OLD_PTR_ID,");
		sql.append(" OLD_PTR_VAR,");
		sql.append(" OLD_PTR_SUBSET,");
		sql.append(" PROJECT_NAME,CHANGE_USER_ID,TIME_STAMP");
		sql.append(" FROM " + environment.getApplSchema() + "."
		        + wipProps.getTableName(Constants.PLAN_WIP));
	}
	*/

	// refactor plan sql
	/* @deprecated use SQLBuilderMERGEDX.buildSelectForMergeStatement()
	private void getWhereClause(StringBuffer sql) {

		if (_treeKey.containsKey("schema")) {
			sql.append(" WHERE ENVIRONMENT = ");
			sql.append(" '").append(_treeKey.get("schema")).append("' ");
		}
		TreeUtils.writeWhereKey(_treeKey, "company_code", sql);
		if (_treeKey.containsKey("product_prefix")) {
			sql.append(" AND ( ( ");
			String pp = (String) _treeKey.get("product_prefix");

			sql.append(" product_prefix = '").append(pp).append("' ");
			if ((_treeKey.get("product_suffix") != null)
			        && !((String) _treeKey.get("product_suffix"))
			                .startsWith("*")) {

				TreeUtils.writeWhereKey(_treeKey, "product_suffix", sql);
			}
			TreeUtils.writeWhereKey(_treeKey, "plan_code", sql);
			if ((_treeKey.get("issue_state") != null)) {
				TreeUtils.writeWhereKey(_treeKey, "issue_state", sql);
			}
			if ((_treeKey.get("line_of_business") != null)) {
				TreeUtils.writeWhereKey(_treeKey, "line_of_business", sql);
			}
			TreeUtils.writeWhereKey(_treeKey, "effective_date", sql);
			TreeUtils.writeWhereKey(_treeKey, "plan_type", sql);
			TreeUtils.writeWhereKey(_treeKey, "table_ptr_id", sql);
			sql.append(" ) ");
			if (_loadNP) {
				sql
				        .append(" OR ( PRODUCT_PREFIX='N' OR PRODUCT_PREFIX='H' ) )");
			} else {
				sql.append(")");
			}

		}
	}
	*/

	// refactor plan sql
	/* @deprecated use SQLBuilderWIPX.buildSelectForMergeStatement()
	private void getWIPWhereClause(StringBuffer sql) {

		if (!_treeKey.isEmpty()) {

			if (_treeKey.containsKey("schema")) {
				sql.append(" WHERE ENVIRONMENT = ");
				sql.append(" '").append(_treeKey.get("schema")).append("' ");
			}

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

			if (_treeKey.containsKey("company_code")) {
				Object obj = _treeKey.get("company_code");

				if (obj instanceof Vector) {
					Vector codes = (Vector) obj;

					sql.append(" AND (");
					sql.append(wipProps.getOldCompanyCode() + " IN (");
					sql.append(TreeUtils.vectorToString(codes)).append(") ");
					sql.append(" OR ");
					sql.append(wipProps.getCompanyCode() + " IN (");
					sql.append(TreeUtils.vectorToString(codes)).append(") )");
				} else {
					String cc = (String) obj;

					sql.append(" AND (");
					sql.append(wipProps.getOldCompanyCode() + " = '");
					sql.append(cc).append("' ");
					sql.append(" OR ");
					sql.append(wipProps.getCompanyCode() + " = '");
					sql.append(cc).append("' )");
				}
			}
			if (_treeKey.containsKey("plan_type")) {
				String pt = (String) _treeKey.get("plan_type");

				sql.append(" AND (");
				sql.append(wipProps.getOldPlanType() + " = '");
				sql.append(pt).append("' ");
				sql.append(" OR ");
				sql.append(wipProps.getPlanType() + " = '");
				sql.append(pt).append("' )");
			}
			if (_treeKey.containsKey("product_prefix")) {
				String pp = (String) _treeKey.get("product_prefix");

				sql.append(" AND ( ( (");
				sql.append(wipProps.getOldProductPrefix() + " = '");
				sql.append(pp).append("' ");
				sql.append(" OR ");
				sql.append(wipProps.getProductPrefix() + " = '");
				sql.append(pp).append("' )");

				if ((_treeKey.get("product_suffix") != null)
				        && !((String) _treeKey.get("product_suffix"))
				                .startsWith("*")) {
					String ps = (String) _treeKey.get("product_suffix");

					sql.append(" AND (");
					sql.append(wipProps.getOldProductSuffix() + " = '");
					sql.append(ps).append("' ");
					sql.append(" OR ");
					sql.append(wipProps.getProductSuffix() + " = '");
					sql.append(ps).append("' )");
				}
				if (_treeKey.containsKey("plan_code")) {
					String pc = (String) _treeKey.get("plan_code");

					sql.append(" AND (");
					sql.append(wipProps.getOldPlanCode() + " = '");
					sql.append(pc).append("' ");
					sql.append(" OR ");
					sql.append(wipProps.getPlanCode() + " = '");
					sql.append(pc).append("' )");
				}
				if (_treeKey.containsKey("effective_date")) {
					String ef = (String) _treeKey.get("effective_date");
					sql.append(" AND (");
					sql.append(wipProps.getOldEffectiveDate() + " = '");
					sql.append(ef).append("' ");
					sql.append(" OR ");
					sql.append(wipProps.getEffectiveDate() + " = '");
					sql.append(ef).append("' )");
				}
				if ((_treeKey.get("issue_state") != null)
				        && !((String) _treeKey.get("issue_state"))
				                .startsWith("*")) {
					String st = (String) _treeKey.get("issue_state");

					sql.append(" AND (");
					sql.append(wipProps.getOldIssueState() + " = '");
					sql.append(st).append("' ");
					sql.append(" OR ");
					sql.append(wipProps.getIssueState() + " = '");
					sql.append(st).append("' )");
				}
				if ((_treeKey.get("line_of_business") != null)
				        && !((String) _treeKey.get("line_of_business"))
				                .startsWith("*")) {
					String lob = (String) _treeKey.get("line_of_business");

					sql.append(" AND (");
					sql.append(wipProps.getOldLOB() + " = '");
					sql.append(lob).append("' ");
					sql.append(" OR ");
					sql.append(wipProps.getLOB() + " = '");
					sql.append(lob).append("' )");
				}

				if (_treeKey.get("locked_rows") != null) {
					sql.append(" AND promotion_lock");
					boolean lockedRows = ((Boolean) _treeKey.get("locked_rows"))
					        .booleanValue();

					if (lockedRows) {
						sql.append(" = 'L'");
					} else {
						sql.append(" != 'L'");
					}

				}
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
	*/

	// refactor plan sql
	/* @deprecated use SQLBuilderMERGEDX
	private void setValuesForMergedXInsert(PreparedStatement iStmt,
	        ResultSet ars) throws java.sql.SQLException {

		_log.trace("INSERT SOURCE into Session.MERGEDX FROM T000X..");

		SqlPW.setString(iStmt, 1, ars, wipProps.getCompanyCode());
		SqlPW.setString(iStmt, 2, ars, wipProps.getProductPrefix());
		SqlPW.setString(iStmt, 3, ars, wipProps.getProductSuffix());
		SqlPW.setString(iStmt, 4, ars, wipProps.getPlanCode());
		iStmt.setString(5, ars.getString(wipProps.getIssueState()));
		iStmt.setString(6, ars.getString(wipProps.getLOB()));
		// SqlPW.setString(iStmt, 7, ars, wipProps.getEffectiveDate());
		SqlPW.setDate(iStmt, 7, ars, wipProps.getEffectiveDate());
		SqlPW.setString(iStmt, 8, ars, wipProps.getPlanType());
		SqlPW.setString(iStmt, 9, ars, wipProps.getTablePtrId());
		SqlPW.setString(iStmt, 10, ars, wipProps.getTablePtrVar());
		SqlPW.setString(iStmt, 11, ars, wipProps.getTablePtrSubset());
	}

	private void setValuesForMergedXInsertFromWIPX(PreparedStatement iStmt,
	        ResultSet ars) throws java.sql.SQLException {

		_log.trace("INSERT SOURCE into Session.MERGEDX FROM T000X..");

		SqlPW.setString(iStmt, 1, ars, wipProps.getCompanyCode());
		SqlPW.setString(iStmt, 2, ars, wipProps.getProductPrefix());
		SqlPW.setString(iStmt, 3, ars, wipProps.getProductSuffix());
		SqlPW.setString(iStmt, 4, ars, wipProps.getPlanCode());
		iStmt.setString(5, ars.getString(wipProps.getIssueState()));
		iStmt.setString(6, ars.getString(wipProps.getLOB()));
		// SqlPW.setString(iStmt, 7, ars, wipProps.getEffectiveDate());
		SqlPW.setDate(iStmt, 7, ars, wipProps.getEffectiveDate());
		SqlPW.setString(iStmt, 8, ars, wipProps.getPlanType());
		SqlPW.setString(iStmt, 9, ars, wipProps.getTablePtrId());
		SqlPW.setString(iStmt, 10, ars, wipProps.getTablePtrVar());
		SqlPW.setString(iStmt, 11, ars, wipProps.getTablePtrSubset());
		SqlPW.setString(iStmt, 12, ars, wipProps.getOldCompanyCode());
		SqlPW.setString(iStmt, 13, ars, wipProps.getOldProductPrefix());
		SqlPW.setString(iStmt, 14, ars, wipProps.getOldProductSuffix());
		SqlPW.setString(iStmt, 15, ars, wipProps.getOldPlanCode());
		iStmt.setString(16, ars.getString(wipProps.getOldIssueState()));
		iStmt.setString(17, ars.getString(wipProps.getOldLOB()));
		// SqlPW.setString(iStmt, 18, ars, wipProps.getOldEffectiveDate());
		SqlPW.setDate(iStmt, 18, ars, wipProps.getOldEffectiveDate());
		SqlPW.setString(iStmt, 19, ars, wipProps.getOldPlanType());
		SqlPW.setString(iStmt, 20, ars, wipProps.getOldPtrId());
		SqlPW.setString(iStmt, 21, ars, wipProps.getOldPtrVar());
		SqlPW.setString(iStmt, 22, ars, wipProps.getOldPtrSubset());
		SqlPW.setString(iStmt, 23, ars, wipProps.getProjectName());
		SqlPW.setString(iStmt, 24, ars, wipProps.getOperation());
		SqlPW.setString(iStmt, 25, ars, wipProps.getChangeUserId());
		SqlPW.setTimestamp(iStmt, 26, ars, wipProps.getTimeStamp());
	}
	*/

	// refactor plan sql
	/* @deprecated use SQLBuilderWIPX
	private void setValuesForWIPXInsert(PreparedStatement iStmt, ResultSet ars)
	        throws java.sql.SQLException {

		_log.trace("INSERT WIP into Session.WIPX...");

		SqlPW.setString(iStmt, 1, ars, wipProps.getCompanyCode());
		SqlPW.setString(iStmt, 2, ars, wipProps.getProductPrefix());
		SqlPW.setString(iStmt, 3, ars, wipProps.getProductSuffix());
		SqlPW.setString(iStmt, 4, ars, wipProps.getPlanCode());
		iStmt.setString(5, ars.getString(wipProps.getIssueState()));
		iStmt.setString(6, ars.getString(wipProps.getLOB()));
		// SqlPW.setString(iStmt, 7, ars, wipProps.getEffectiveDate());
		SqlPW.setDate(iStmt, 7, ars, wipProps.getEffectiveDate());
		SqlPW.setString(iStmt, 8, ars, wipProps.getPlanType());
		SqlPW.setString(iStmt, 9, ars, wipProps.getTablePtrId());
		SqlPW.setString(iStmt, 10, ars, wipProps.getTablePtrVar());
		SqlPW.setString(iStmt, 11, ars, wipProps.getTablePtrSubset());
		SqlPW.setString(iStmt, 12, ars, wipProps.getOldCompanyCode());
		SqlPW.setString(iStmt, 13, ars, wipProps.getOldProductPrefix());
		SqlPW.setString(iStmt, 14, ars, wipProps.getOldProductSuffix());
		SqlPW.setString(iStmt, 15, ars, wipProps.getOldPlanCode());
		iStmt.setString(16, ars.getString(wipProps.getOldIssueState()));
		iStmt.setString(17, ars.getString(wipProps.getOldLOB()));
		// SqlPW.setString(iStmt, 18, ars, wipProps.getOldEffectiveDate());
		SqlPW.setDate(iStmt, 18, ars, wipProps.getOldEffectiveDate());
		SqlPW.setString(iStmt, 19, ars, wipProps.getOldPlanType());
		SqlPW.setString(iStmt, 20, ars, wipProps.getOldPtrId());
		SqlPW.setString(iStmt, 21, ars, wipProps.getOldPtrVar());
		SqlPW.setString(iStmt, 22, ars, wipProps.getOldPtrSubset());
		SqlPW.setString(iStmt, 23, ars, wipProps.getProjectName());
		SqlPW.setString(iStmt, 24, ars, wipProps.getOperation());
		SqlPW.setString(iStmt, 25, ars, wipProps.getChangeUserId());
		SqlPW.setTimestamp(iStmt, 26, ars, wipProps.getTimeStamp());
	}
	*/
	
	// refactor plan sql
	/* @deprecated use SQLBuilderMERGEDX
	private void setValuesForMergedXUpdate(
		PreparedStatement uStmt,
		ResultSet ars)
		throws java.sql.SQLException {

	private void setValuesForMergedXUpdate(PreparedStatement uStmt,
	        ResultSet ars) throws java.sql.SQLException {

		_log.trace("Update PLAN WIP set ...");
		SqlPW.setString(uStmt, 1, ars, wipProps.getCompanyCode());
		SqlPW.setString(uStmt, 2, ars, wipProps.getProductPrefix());
		SqlPW.setString(uStmt, 3, ars, wipProps.getProductSuffix());
		SqlPW.setString(uStmt, 4, ars, wipProps.getPlanCode());
		uStmt.setString(5, ars.getString(wipProps.getIssueState()));
		uStmt.setString(6, ars.getString(wipProps.getLOB()));
		// SqlPW.setString(uStmt, 7, ars, wipProps.getEffectiveDate());
		SqlPW.setDate(uStmt, 7, ars, wipProps.getEffectiveDate());
		SqlPW.setString(uStmt, 8, ars, wipProps.getPlanType());
		SqlPW.setString(uStmt, 9, ars, wipProps.getTablePtrId());
		SqlPW.setString(uStmt, 10, ars, wipProps.getTablePtrVar());
		SqlPW.setString(uStmt, 11, ars, wipProps.getTablePtrSubset());

		_log.trace("..WHERE...");
		SqlPW.setString(uStmt, 12, ars, wipProps.getOldCompanyCode());
		SqlPW.setString(uStmt, 13, ars, wipProps.getOldProductPrefix());
		SqlPW.setString(uStmt, 14, ars, wipProps.getOldProductSuffix());
		SqlPW.setString(uStmt, 15, ars, wipProps.getOldPlanCode());
		uStmt.setString(16, ars.getString(wipProps.getOldIssueState()));
		uStmt.setString(17, ars.getString(wipProps.getOldLOB()));
		// SqlPW.setString(uStmt, 18, ars, wipProps.getOldEffectiveDate());
		SqlPW.setDate(uStmt, 18, ars, wipProps.getOldEffectiveDate());
		SqlPW.setString(uStmt, 19, ars, wipProps.getOldPlanType());
		SqlPW.setString(uStmt, 20, ars, wipProps.getOldPtrId());
		SqlPW.setString(uStmt, 21, ars, wipProps.getOldPtrVar());
		SqlPW.setString(uStmt, 22, ars, wipProps.getOldPtrSubset());
		SqlPW.setString(uStmt, 23, ars, wipProps.getProjectName());
		SqlPW.setString(uStmt, 24, ars, wipProps.getOperation());
		SqlPW.setString(uStmt, 25, ars, wipProps.getChangeUserId());
		SqlPW.setTimestamp(uStmt, 26, ars, wipProps.getTimeStamp());
		SqlPW.setString(uStmt, 27, ars, wipProps.getOldCompanyCode());
		SqlPW.setString(uStmt, 28, ars, wipProps.getOldProductPrefix());
		SqlPW.setString(uStmt, 29, ars, wipProps.getOldProductSuffix());
		SqlPW.setString(uStmt, 30, ars, wipProps.getOldPlanCode());
		uStmt.setString(31, ars.getString(wipProps.getOldIssueState()));
		uStmt.setString(32, ars.getString(wipProps.getOldLOB()));
		// SqlPW.setString(uStmt, 33, ars, wipProps.getOldEffectiveDate());
		SqlPW.setDate(uStmt, 33, ars, wipProps.getOldEffectiveDate());
		SqlPW.setString(uStmt, 34, ars, wipProps.getOldPlanType());
		SqlPW.setString(uStmt, 35, ars, wipProps.getOldPtrId());
		SqlPW.setString(uStmt, 36, ars, wipProps.getOldPtrVar());
		SqlPW.setString(uStmt, 37, ars, wipProps.getOldPtrSubset());
	}

	private void setValuesForMergedXDelete(PreparedStatement dStmt,
	        ResultSet ars) throws java.sql.SQLException {

		dStmt.setString(1, ars.getString(wipProps.getOldCompanyCode()));
		dStmt.setString(2, ars.getString(wipProps.getOldProductPrefix()));
		dStmt.setString(3, ars.getString(wipProps.getOldProductSuffix()));
		dStmt.setString(4, ars.getString(wipProps.getOldPlanCode()));
		dStmt.setString(5, ars.getString(wipProps.getOldIssueState()));
		dStmt.setString(6, ars.getString(wipProps.getOldLOB()));
		// dStmt.setString(7, ars.getString(wipProps.getOldEffectiveDate()));
		SqlPW.setDate(dStmt, 7, ars, wipProps.getOldEffectiveDate());
		dStmt.setString(8, ars.getString(wipProps.getOldPlanType()));
		dStmt.setString(9, ars.getString(wipProps.getOldPtrId()));
		dStmt.setString(10, ars.getString(wipProps.getOldPtrVar()));
		dStmt.setString(11, ars.getString(wipProps.getOldPtrSubset()));
	}
	*/

	// refactor plan sql
	/* @deprecated use SQLBuilderWIPX
	private String buildWIPXInsertSQL() {

		StringBuffer sb = new StringBuffer(200);

		sb.append(" INSERT INTO ");
		sb.append(" SESSION.WIPX");
		sb.append(" ( ");
		sb.append(" COMPANY_CODE,");
		sb.append(" PRODUCT_PREFIX,");
		sb.append(" PRODUCT_SUFFIX,");
		sb.append(" PLAN_CODE,");
		sb.append(" ISSUE_STATE,");
		sb.append(" LINE_OF_BUSINESS,");
		sb.append(" EFFECTIVE_DATE,");
		sb.append(" PLAN_TYPE,");
		sb.append(" TABLE_PTR_ID,");
		sb.append(" TABLE_PTR_VAR,");
		sb.append(" TABLE_PTR_SUBSET,");
		sb.append(" OLD_COMPANY_CODE,");
		sb.append(" OLD_PRODUCT_PREFIX,");
		sb.append(" OLD_PRODUCT_SUFFIX,");
		sb.append(" OLD_PLAN_CODE,");
		sb.append(" OLD_ISSUE_STATE,");
		sb.append(" OLD_LOB,");
		sb.append(" OLD_EFFECTIVE_DATE,");
		sb.append(" OLD_PLAN_TYPE,");
		sb.append(" OLD_PTR_ID,");
		sb.append(" OLD_PTR_VAR,");
		sb.append(" OLD_PTR_SUBSET,");
		sb.append(" PROJECT_NAME,");
		sb.append(" OPERATION,");
		sb.append(" CHANGE_USER_ID,");
		sb.append(" TIME_STAMP");
		sb.append(" ) ");
		sb
		        .append("VALUES ( ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
		String sql = sb.toString();

		sb = null;
		return sql;
	}
	*/

	// refactor plan sql
	/* @deprecated use SQLBuilderMERGEDX
	private String buildMergedXInsertSQL() {
		StringBuffer sb = new StringBuffer(200);
		sb.append(" INSERT INTO ");
		sb.append(" SESSION.MERGEDX ");
		sb.append(" ( ");
		sb.append(" COMPANY_CODE,");
		sb.append(" PRODUCT_PREFIX,");
		sb.append(" PRODUCT_SUFFIX,");
		sb.append(" PLAN_CODE,");
		sb.append(" ISSUE_STATE,");
		sb.append(" LINE_OF_BUSINESS,");
		sb.append(" EFFECTIVE_DATE,");
		sb.append(" PLAN_TYPE,");
		sb.append(" TABLE_PTR_ID,");
		sb.append(" TABLE_PTR_VAR,");
		sb.append(" TABLE_PTR_SUBSET");
		sb.append(" ) ");
		sb.append("VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
		return sb.toString();
	}

	private String buildMergedXInsertSQLFromWIPX() {
		StringBuffer sb = new StringBuffer(200);
		sb.append(" INSERT INTO ");
		sb.append(" SESSION.MERGEDX ");
		sb.append(" ( ");
		sb.append(" COMPANY_CODE,");
		sb.append(" PRODUCT_PREFIX,");
		sb.append(" PRODUCT_SUFFIX,");
		sb.append(" PLAN_CODE,");
		sb.append(" ISSUE_STATE,");
		sb.append(" LINE_OF_BUSINESS,");
		sb.append(" EFFECTIVE_DATE,");
		sb.append(" PLAN_TYPE,");
		sb.append(" TABLE_PTR_ID,");
		sb.append(" TABLE_PTR_VAR,");
		sb.append(" TABLE_PTR_SUBSET,");

		sb.append(" OLD_COMPANY_CODE,");
		sb.append(" OLD_PRODUCT_PREFIX,");
		sb.append(" OLD_PRODUCT_SUFFIX, ");
		sb.append(" OLD_PLAN_CODE, ");
		sb.append(" OLD_ISSUE_STATE, ");
		sb.append(" OLD_LOB, ");
		sb.append(" OLD_EFFECTIVE_DATE, ");
		sb.append(" OLD_PLAN_TYPE, ");
		sb.append(" OLD_PTR_ID, ");
		sb.append(" OLD_PTR_VAR, ");
		sb.append(" OLD_PTR_SUBSET, ");
		sb.append(" PROJECT_NAME, ");
		sb.append(" OPERATION, ");
		sb.append(" CHANGE_USER_ID, ");
		sb.append(" TIME_STAMP");
		sb.append(" ) ");
		sb
		        .append("VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ? )");
		return sb.toString();
	}

	private String buildMergedXUpdateSQL() {

		StringBuffer sb = new StringBuffer(200);

		sb.append(" UPDATE SESSION.MERGEDX  ");
		sb.append(" SET ");
		sb.append(" COMPANY_CODE = ?,");
		sb.append(" PRODUCT_PREFIX = ?,");
		sb.append(" PRODUCT_SUFFIX = ?,");
		sb.append(" PLAN_CODE= ?,");
		sb.append(" ISSUE_STATE= ?,");
		sb.append(" LINE_OF_BUSINESS= ?,");
		sb.append(" EFFECTIVE_DATE= ?,");
		sb.append(" PLAN_TYPE= ?,");
		sb.append(" TABLE_PTR_ID= ?,");
		sb.append(" TABLE_PTR_VAR= ?,");
		sb.append(" TABLE_PTR_SUBSET= ?,");
		sb.append(" OLD_COMPANY_CODE = ?,");
		sb.append(" OLD_PRODUCT_PREFIX = ?,");
		sb.append(" OLD_PRODUCT_SUFFIX = ?,");
		sb.append(" OLD_PLAN_CODE= ?,");
		sb.append(" OLD_ISSUE_STATE= ?,");
		sb.append(" OLD_LOB= ?,");
		sb.append(" OLD_EFFECTIVE_DATE= ?,");
		sb.append(" OLD_PLAN_TYPE= ?,");
		sb.append(" OLD_PTR_ID= ?,");
		sb.append(" OLD_PTR_VAR= ?,");
		sb.append(" OLD_PTR_SUBSET= ?,");
		sb.append(" PROJECT_NAME= ?,");
		sb.append(" OPERATION= ?,");
		sb.append(" CHANGE_USER_ID= ?,");
		sb.append(" TIME_STAMP= ?");
		sb.append(" WHERE ");
		sb.append(" COMPANY_CODE = ?");
		sb.append(" AND PRODUCT_PREFIX = ?");
		sb.append(" AND PRODUCT_SUFFIX= ?");
		sb.append(" AND PLAN_CODE= ?");
		sb.append(" AND ISSUE_STATE= ?");
		sb.append(" AND LINE_OF_BUSINESS= ?");
		sb.append(" AND EFFECTIVE_DATE= ?");
		sb.append(" AND PLAN_TYPE= ?");
		sb.append(" AND TABLE_PTR_ID= ?");
		sb.append(" AND TABLE_PTR_VAR= ?");
		sb.append(" AND TABLE_PTR_SUBSET = ?");
		return sb.toString();
	}

	private String buildMergedXDeleteSQL() {

		StringBuffer sb = new StringBuffer(200);

		sb.append(" DELETE FROM SESSION.MERGEDX ");
		sb.append(" WHERE ");
		sb.append(" COMPANY_CODE = ?");
		sb.append(" AND PRODUCT_PREFIX = ?");
		sb.append(" AND PRODUCT_SUFFIX= ?");
		sb.append(" AND PLAN_CODE= ?");
		sb.append(" AND ISSUE_STATE= ?");
		sb.append(" AND LINE_OF_BUSINESS= ?");
		sb.append(" AND EFFECTIVE_DATE= ?");
		sb.append(" AND PLAN_TYPE= ?");
		sb.append(" AND TABLE_PTR_ID= ?");
		sb.append(" AND TABLE_PTR_VAR= ?");
		sb.append(" AND TABLE_PTR_SUBSET = ?");
		String sql = sb.toString();

		sb = null;
		return sql;
	}

	/**
	 * This method attempts to find a matching source row for the old values in
	 * the UPDATE row from the WIP. If successful, it returns true.
	 */

	// refactor plan sql
	private boolean matchSourceFromMERGEDX(Statement stmt, ResultSet ars) throws Exception {
		boolean foundMatch = false;
		ResultSet rs = null;
		try {
				PlanRowTO planRow = new PlanRowTO(ars, wipProps);
				planRow.setEnvironment(planCriteria.getEnvironment());
				String sql = new SQLBuilderMERGEDX(planRow.getEnvironment(), new PlanCriteriaTO(planRow)).buildCountAllStatement();
				rs = stmt.executeQuery(sql);
				if (rs.next()) {
					foundMatch = (rs.getInt(1) != 0);
				}
		
		} finally {
			Utils.closeResultSet(rs);		
		}
		return foundMatch;
	}

	/*
	private boolean matchSourceFromMergedX(Statement stmt, ResultSet ars,
	        StringBuffer sql) throws SQLException {

		boolean foundMatch = false;
		sql.setLength(0);
		sql.append("SELECT COUNT(*) FROM SESSION.MERGEDX ");
		sql.append("WHERE " + wipProps.getCompanyCode() + "='"
		        + ars.getString(wipProps.getOldCompanyCode()) + "' ");
		sql.append("AND " + wipProps.getProductPrefix() + "='"
		        + ars.getString(wipProps.getOldProductPrefix()) + "' ");
		sql.append("AND " + wipProps.getProductSuffix() + "='"
		        + ars.getString(wipProps.getOldProductSuffix()) + "' ");
		sql.append("AND " + wipProps.getPlanCode() + "='"
		        + ars.getString(wipProps.getOldPlanCode()) + "' ");

		sql.append("AND " + wipProps.getIssueState() + "='"
		        + ars.getString(wipProps.getOldIssueState()) + "' ");

		sql.append("AND " + wipProps.getLOB() + "='"
		        + ars.getString(wipProps.getOldLOB()) + "' ");

		sql.append("AND " + wipProps.getEffectiveDate() + "='"
		        + ars.getString(wipProps.getOldEffectiveDate()) + "' ");

		sql.append("AND " + wipProps.getPlanType() + "='"
		        + ars.getString(wipProps.getOldPlanType()) + "' ");
		sql.append("AND " + wipProps.getTablePtrId() + "='"
		        + ars.getString(wipProps.getOldPtrId()) + "' ");
		sql.append("AND " + wipProps.getTablePtrVar() + "='"
		        + ars.getString(wipProps.getOldPtrVar()) + "' ");
		sql.append("AND " + wipProps.getTablePtrSubset() + "='"
		        + ars.getString(wipProps.getOldPtrSubset()) + "' ");

		ResultSet rs = stmt.executeQuery(sql.toString());

		if (rs.next()) {
			foundMatch = (rs.getInt(1) != 0);
		}
		Utils.closeResultSet(rs);
		return foundMatch;
	}
	*/

	// refactor plan sql
	private boolean matchSourceFromT000X(Statement stmt, ResultSet ars) throws Exception {
		boolean foundMatch = false;
		ResultSet rs = null;
		try {
				PlanRowTO planRow = new PlanRowTO(ars, wipProps);
				planRow.setEnvironment(planCriteria.getEnvironment());
				String sql = new SQLBuilderT000X(new PlanCriteriaTO(planRow)).buildCountAllStatement();
				rs = stmt.executeQuery(sql);
				if (rs.next()) {
					foundMatch = (rs.getInt(1) != 0);
				}
		} finally {
			Utils.closeResultSet(rs);		
		}
		return foundMatch;
	}
	/*
	private boolean matchSourceFromT000X(Statement stmt, ResultSet ars,
	        StringBuffer sql) throws SQLException {

		boolean foundMatch = false;
		sql.setLength(0);
		Environment environment = EnvironmentManager.getInstance().getEnvironment((String)_treeKey.get("schema"));
		sql.append("SELECT COUNT(*) FROM ").append(environment.getApplSchema())
		        .append(".T000X ");
		sql.append(" WHERE " + wipProps.getCompanyCode() + "='"
		        + ars.getString(wipProps.getOldCompanyCode()) + "' ");
		sql.append("AND " + wipProps.getProductPrefix() + "='"
		        + ars.getString(wipProps.getOldProductPrefix()) + "' ");
		sql.append("AND " + wipProps.getProductSuffix() + "='"
		        + ars.getString(wipProps.getOldProductSuffix()) + "' ");
		sql.append("AND " + wipProps.getPlanCode() + "='"
		        + ars.getString(wipProps.getOldPlanCode()) + "' ");
		sql.append("AND " + wipProps.getIssueState() + "='"
		        + ars.getString(wipProps.getOldIssueState()) + "' ");
		sql.append("AND " + wipProps.getLOB() + "='"
		        + ars.getString(wipProps.getOldLOB()) + "' ");
		sql.append("AND " + wipProps.getEffectiveDate() + "='"
		        + ars.getString(wipProps.getOldEffectiveDate()) + "' ");

		sql.append("AND " + wipProps.getPlanType() + "='"
		        + ars.getString(wipProps.getOldPlanType()) + "' ");
		sql.append("AND " + wipProps.getTablePtrId() + "='"
		        + ars.getString(wipProps.getOldPtrId()) + "' ");
		sql.append("AND " + wipProps.getTablePtrVar() + "='"
		        + ars.getString(wipProps.getOldPtrVar()) + "' ");
		sql.append("AND " + wipProps.getTablePtrSubset() + "='"
		        + ars.getString(wipProps.getOldPtrSubset()) + "' ");

		ResultSet rs = stmt.executeQuery(sql.toString());

		if (rs.next()) {
			foundMatch = (rs.getInt(1) != 0);
		}
		Utils.closeResultSet(rs);

		return foundMatch;
	}
	*/

	public Set<PlanRowTO> getPlanRows(PlanCriteriaTO planCriteria) {
		return treeCache.get(planCriteria.getPlanKey("|"));
	}
	
	public Set<String> getPlans() {
		return treeCache.keySet();
	}
}
