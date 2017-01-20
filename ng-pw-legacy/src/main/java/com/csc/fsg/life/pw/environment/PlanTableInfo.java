package com.csc.fsg.life.pw.environment;

import java.sql.*;
import java.util.ArrayList;


import com.csc.fsg.life.pw.transferobjects.PlanCriteriaTO;
import com.csc.fsg.life.pw.util.Utils;
//import com.csc.fsg.life.pw.web.actions.tree.PlanMergeAssistent;
import com.csc.fsg.life.pw.util.*;
//import com.csc.fsg.life.pw.web.utils.sql.SQLBuilderMERGEDX;
//import com.csc.fsg.life.pw.web.utils.sql.SQLBuilderPLANWIP;
//import com.csc.fsg.life.pw.web.utils.sql.SQLBuilderT000X;

/* Modifications: T0103, T0091, WMABASEIXI-2749, CCCV-E-ISSUE_STATE */

public class PlanTableInfo {
	
	private Environment env = null;
	
	public PlanTableInfo(Environment environment){
		this.env = environment;
	}
	
	/*
	private void populateList(ArrayList list, String applSchema,String envId, Connection conn,String query){
		//System.out.println(query);
		ResultSet rs = null;
		Statement stmt = null;
		try {
			stmt = conn.createStatement();
			rs = SqlPW.query(SqlPW.SQL_SRC_PLAN_FILTER, stmt, query);
			while (rs.next())
				list.add(rs.getString(1).trim());
		} catch (Exception e) {
			throw WrapperException.wrap(e);
		} finally {
			Utils.closeStatement(stmt);
			Utils.closeResultSet(rs);
		}
	}
	*/

	/**
	 * Method convertToAL 
	 * @param rs
	 * @param al
	 *
	 * @throws Exception
	 */

	public static void convertToAL(ResultSet rs, ArrayList<String> al)
		throws Exception {

		while (rs.next()) {
			al.add(rs.getString(1).trim());
		}
		al.trimToSize();
	}

	// rework needed

	/**
	 * Method convertToPlanCodesAL 
	 * @param rs
	 * @param al
	 *
	 * @throws Exception
	 */

	public static void convertToPlanCodesAL(ResultSet rs, ArrayList<String> al)
		throws Exception {

		String value = null;

		while (rs.next()) {
			value = (rs.getString(1)).trim();
			if (!al.contains(value)) {
				al.add(value);
			}
		}
		al.trimToSize();
	}

	// WMABASEIXI-2749 - use PlanMergeAssistent to handle WIP merge
	public ArrayList<String> getNextPlanKey(PlanCriteriaTO planCriteria)
	throws Exception {
//		Connection conn = null;
		ArrayList<String> products = new ArrayList<String>();
//		ResultSet rs = null;
//		Statement stmt = null;
//		PlanMergeAssistent pma = null;
//
//		try {
//			conn = DBConnMgr.getInstance().getConnection(env.getId(),DBConnMgr.APPL);
//			stmt = conn.createStatement();
//			
//			pma = new PlanMergeAssistent(conn, planCriteria);
//			String sql = new SQLBuilderMERGEDX(planCriteria.getEnvironment(), planCriteria).buildSelectForFilterStatement();
//			rs = SqlPW.query(SqlPW.SQL_SRC_PLAN_FILTER, stmt, sql);
//			PlanTableInfo.convertToAL(rs, products);
//
//			/*
//			rs = SqlPW.query(SqlPW.SQL_SRC_PLAN_FILTER, stmt, 
//					new SQLBuilderT000X(planCriteria).buildSelectForFilterStatement());
//			PlanTableInfo.convertToAL(rs, products);
//			Utils.closeStatement(stmt);
//			Utils.closeResultSet(rs);
//
//			// query to plan wip
//			if (planCriteria.isViewChanges()) {
//				stmt = conn.createStatement();
//				rs = SqlPW.query(SqlPW.SQL_WIP_PLAN_FILTER, stmt, 
//						new SQLBuilderPLANWIP(planCriteria).buildSelectForFilterStatement());
//				PlanTableInfo.convertToPlanCodesAL(rs, products);
//			}
//			// wip end
//			*/
//		} finally {
//			if (pma != null)
//				pma.clean(conn);
//			Utils.closeStatement(stmt);
//			Utils.closeResultSet(rs);
//			DBConnMgr.getInstance().releaseConnection(conn);
//		}
		return products;
	}
	
	public ArrayList<String> getIssueStates(PlanCriteriaTO planCriteria)
	throws Exception {
//		Connection conn = null;
		ArrayList<String> products = new ArrayList<String>();
//		ResultSet rs = null;
//		Statement stmt = null;
//		PlanMergeAssistent pma = null;
//
//		try {
//			conn = DBConnMgr.getInstance().getConnection(env.getId(),DBConnMgr.APPL);
//			stmt = conn.createStatement();
//			
//			pma = new PlanMergeAssistent(conn, planCriteria);
//			String sql = new SQLBuilderMERGEDX(planCriteria.getEnvironment(), planCriteria).buildSelectIssueStateForIRU();
//			rs = SqlPW.query(SqlPW.SQL_SRC_PLAN_FILTER, stmt, sql);
//			PlanTableInfo.convertToAL(rs, products);
//		
//		} finally {
//			if (pma != null)
//				pma.clean(conn);
//			Utils.closeStatement(stmt);
//			Utils.closeResultSet(rs);
//			DBConnMgr.getInstance().releaseConnection(conn);
//		}
		return products;
	}
	
	// refactor plan key.
	/* @deprecated use getNextPlanKey(PlanCriteriaTO)
	public ArrayList getProducts(String companyCode,
			boolean includeWIP) throws Exception {

		ArrayList list = new ArrayList();
		Connection conn = null;
		String applSchema = env.getApplSchema();
		String envId = env.getId();

		try {

			conn = DBConnMgr.getInstance().getConnection(env.getId(),DBConnMgr.APPL);

			StringBuffer sb = new StringBuffer(
					"SELECT DISTINCT CONCAT(PRODUCT_PREFIX ,PRODUCT_SUFFIX) FROM "
							+ applSchema + ".T000X where " + "ENVIRONMENT ='"
							+ envId + "'" + " and company_code='" + companyCode
							+ "'");

			populateList(list,applSchema,envId,conn,sb.toString());
			
			if (includeWIP) {
				sb.setLength(0);
				sb.append("SELECT DISTINCT CONCAT(PRODUCT_PREFIX ,PRODUCT_SUFFIX) FROM "
								+ applSchema+ ".PLANWIP WHERE ENVIRONMENT='"
								+ envId	+ "'"+ " and company_code='"
								+ companyCode+ "' and operation='INSERT'");

				populateList(list,applSchema,envId,conn,sb.toString());
			}

		} catch (Exception e) {
			throw WrapperException.wrap(e);
		} finally {
			DBConnMgr.getInstance().releaseConnection(conn);
		}
		return list;
	}

	public ArrayList getPlanCodes(String companyCode,
			String product, boolean includeWIP) throws Exception {

		ArrayList list = new ArrayList();
		Connection conn = null;
		String applSchema = env.getApplSchema();
		String envId = env.getId();

		try {

			conn = DBConnMgr.getInstance().getConnection(env.getId(),DBConnMgr.APPL);
			String planQuery = "SELECT DISTINCT PLAN_CODE FROM " + applSchema+ ".T000X ";

			StringBuffer where = new StringBuffer(" WHERE ENVIRONMENT = '");
			where.append(envId).append("' AND COMPANY_CODE = '");
			where.append(companyCode + "' AND PRODUCT_PREFIX = '");
			where.append(product.substring(0, 1) + "' ");
			if (!product.substring(1, 2).equals("*"))
				where.append(" AND PRODUCT_SUFFIX='").append(
						product.substring(1, 2)).append("'");

			populateList(list,applSchema,envId,conn,planQuery + where.toString());
			
			if (includeWIP) {
				String wipQuery = "SELECT DISTINCT PLAN_CODE FROM "	+ applSchema + ".PLANWIP ";
				populateList(list,applSchema,envId,conn,wipQuery+ where.toString()+" AND OPERATION = 'INSERT'");
			}

		} catch (Exception e) {
			throw WrapperException.wrap(e);
		} finally {
			DBConnMgr.getInstance().releaseConnection(conn);
		}
		return list;
	}

	public ArrayList getIssueStates(String companyCode,
			String product, String plan_code, boolean includeWIP)
			throws Exception {

		ArrayList list = new ArrayList();
		Connection conn = null;
		String applSchema = env.getApplSchema();
		String envId = env.getId();

		try {

			conn = DBConnMgr.getInstance().getConnection(env.getId(),DBConnMgr.APPL);
			String planQuery = "SELECT DISTINCT ISSUE_STATE FROM " + applSchema+ ".T000X ";

			StringBuffer where = new StringBuffer(" WHERE ENVIRONMENT = '");
			where.append(envId).append("' AND COMPANY_CODE = '");
			where.append(companyCode + "' AND PRODUCT_PREFIX = '");
			where.append(product.substring(0, 1) + "' ");
			
			if (!product.substring(1, 2).equals("*"))
				where.append(" AND PRODUCT_SUFFIX='").append(product.substring(1, 2)).append("'");

			if (plan_code!=null)
				where.append(" AND PLAN_CODE = '").append(plan_code + "' ");

			populateList(list,applSchema,envId,conn,planQuery + where.toString());
			
			if (includeWIP) {
				String wipQuery = "SELECT DISTINCT ISSUE_STATE FROM "+ applSchema + ".PLANWIP ";
				populateList(list,applSchema,envId,conn,wipQuery+ where.toString()+" AND OPERATION = 'INSERT'");
			}

		} catch (Exception e) {
			throw WrapperException.wrap(e);
		} finally {
			DBConnMgr.getInstance().releaseConnection(conn);
		}
		return list;
	}

	public ArrayList getLOB( String companyCode,
			String product, String plan_code, String issue_state,
			boolean includeWIP) throws Exception {
		
		ArrayList list = new ArrayList();
		Connection conn = null;
		String applSchema = env.getApplSchema();
		String envId = env.getId();

		try {

			conn = DBConnMgr.getInstance().getConnection(env.getId(),DBConnMgr.APPL);
			String planQuery = "SELECT DISTINCT LINE_OF_BUSINESS FROM "	+ applSchema + ".T000X ";

			StringBuffer where = new StringBuffer(" WHERE ENVIRONMENT = '");
			where.append(envId).append("' AND COMPANY_CODE = '");
			where.append(companyCode + "' AND PRODUCT_PREFIX = '");
			where.append(product.substring(0, 1) + "' ");
			
			if (!product.substring(1, 2).equals("*"))
				where.append(" AND PRODUCT_SUFFIX='").append(product.substring(1, 2)).append("'");
			
			where.append(" AND PLAN_CODE = '");
			where.append(plan_code + "' ");
			
			if (!issue_state.startsWith("*")) {
				where.append(" AND ISSUE_STATE = '");
				where.append(issue_state + "' ");
			}
			
			populateList(list,applSchema,envId,conn,planQuery + where.toString());
			
			if (includeWIP) {
				String wipQuery = "SELECT DISTINCT LINE_OF_BUSINESS FROM "+ applSchema + ".PLANWIP ";
				populateList(list,applSchema,envId,conn,wipQuery+ where.toString()+" AND OPERATION = 'INSERT'");
			}

		} catch (Exception e) {
			throw WrapperException.wrap(e);
		} finally {
			DBConnMgr.getInstance().releaseConnection(conn);
		}
		return list;
	}

	public ArrayList getEffDates(String companyCode,
			String product, String plan_code, String issue_state, String lob,
			boolean includeWIP) throws Exception {

		ArrayList list = new ArrayList();
		Connection conn = null;
		String applSchema = env.getApplSchema();
		String envId = env.getId();

		try {

			conn = DBConnMgr.getInstance().getConnection(env.getId(),DBConnMgr.APPL);
			String planQuery = "SELECT DISTINCT EFFECTIVE_DATE FROM "+ applSchema + ".T000X ";

			StringBuffer where = new StringBuffer(" WHERE ENVIRONMENT = '");
			where.append(envId).append("' AND COMPANY_CODE = '");
			where.append(companyCode + "' AND PRODUCT_PREFIX = '");
			where.append(product.substring(0, 1) + "' ");
			
			if (!product.substring(1, 2).equals("*"))
				where.append(" AND PRODUCT_SUFFIX='").append(product.substring(1, 2)).append("'");
			
			where.append(" AND PLAN_CODE = '");
			where.append(plan_code + "' ");
			
			if (!issue_state.startsWith("*")) {
				where.append(" AND ISSUE_STATE = '");
				where.append(issue_state + "' ");
			}
			
			if (!lob.startsWith("*")) {
				where.append(" AND LINE_OF_BUSINESS='").append(lob)
						.append("' ");
			}

			populateList(list,applSchema,envId,conn,planQuery + where.toString());
			
			if (includeWIP) {
				String wipQuery = "SELECT DISTINCT EFFECTIVE_DATE FROM "+ applSchema + ".PLANWIP ";
				populateList(list,applSchema,envId,conn,wipQuery+ where.toString()+" AND OPERATION = 'INSERT'");
			}

		} catch (Exception e) {
			throw WrapperException.wrap(e);
		} finally {
			DBConnMgr.getInstance().releaseConnection(conn);
		}
		return list;
	}

	public ArrayList getPlanCodesForType(String companyCode, String product, String type) 
		throws Exception {

		ArrayList list = new ArrayList();
		Connection conn = null;
		String applSchema = env.getApplSchema();
		String envId = env.getId();

		try {

			conn = DBConnMgr.getInstance().getConnection(env.getId(),DBConnMgr.APPL);
			String planQuery = "SELECT DISTINCT PLAN_CODE FROM " + applSchema+ ".T000X ";

			StringBuffer where = new StringBuffer(" WHERE ENVIRONMENT = '");
			where.append(envId).append("' AND COMPANY_CODE = '");
			where.append(companyCode + "' AND PRODUCT_PREFIX = '");
			where.append(product.substring(0, 1) + "' ");
			
			if (!product.substring(1, 2).equals("*"))
				where.append(" AND PRODUCT_SUFFIX='").append(product.substring(1, 2)).append("'");
			
			where.append(" AND PLAN_TYPE ='").append(type).append("'");

			populateList(list,applSchema,envId,conn,planQuery + where.toString());

		} catch (Exception e) {
			throw WrapperException.wrap(e);
		} finally {
			DBConnMgr.getInstance().releaseConnection(conn);
		}
		return list;
	}
	*/
}