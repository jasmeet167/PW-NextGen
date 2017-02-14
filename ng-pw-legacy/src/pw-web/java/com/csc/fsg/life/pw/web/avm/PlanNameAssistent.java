package com.csc.fsg.life.pw.web.avm;

import java.sql.Connection;
import java.sql.ResultSet;

import java.sql.Statement;


import com.csc.fsg.life.pw.common.transferobjects.PlanTO;
import com.csc.fsg.life.pw.common.util.Utils;
import com.csc.fsg.life.pw.web.utils.DBConnMgr;
import com.csc.fsg.life.pw.web.utils.sql.SQLBuilderPLAN_NAME;

/* Modifications: T0103 , CCCV-E768, T0129 */

public class PlanNameAssistent {

	public static String getPlanName(PlanTO plan) throws Exception{
		Connection conn = null;
		String planName = null;
		
		try {
			conn = DBConnMgr.getInstance()
			        .getConnection(plan.getEnvironment(),DBConnMgr.APPL);
			planName = getPlanName(plan, conn);
		} catch (Exception e) {
			throw e;
		} finally {
			DBConnMgr.getInstance().releaseConnection(conn);
		}
		return planName;
	}

	public static synchronized String getPlanName(PlanTO plan, Connection conn) throws Exception {
		String planName = null;
		Statement statement = null;
		ResultSet resultSet = null;

		try {
			statement = conn.createStatement();
			
			// refactor plan related sql.
			String sql = new SQLBuilderPLAN_NAME(plan).buildSelectPlanNameStatement();
			resultSet = statement.executeQuery(sql);
			if (resultSet.next())
				planName = Utils.safeTrim(resultSet.getString(1));

		} finally {
			Utils.closeResultSet(resultSet);
			Utils.closeStatement(statement);
		}

		return (planName);
	}
	
	public static boolean setPlanName(PlanTO plan, String action) throws Exception {
		Connection conn = null;
		boolean isUpdated = false;
		try {
			conn = DBConnMgr.getInstance().getConnection(plan.getEnvironment(), DBConnMgr.APPL);
			isUpdated =  setPlanName(plan, action, conn);
		}finally 
		{
			DBConnMgr.getInstance().releaseConnection(conn);
		}
		return isUpdated;		
	}
	
	public static boolean setPlanName(PlanTO plan, String action, Connection conn) throws Exception {
		boolean isUpdated = false;

		String planName = plan.getPlanName();
		if ( !action.equals("DELETE") &&
				((planName == null) || (planName.trim().length() == 0)) ) {
			return (false);
		}

		Statement statement = null;
		String sql;
		try {

			statement = conn.createStatement();
			conn.setAutoCommit(false);
			if (action.equals("UPDATE")) {
				sql = new SQLBuilderPLAN_NAME(plan).buildUpdatePlanNameStatement();
			} else if (action.equals("DELETE")) {
				sql = new SQLBuilderPLAN_NAME(plan).buildDeleteStatement();
			} else if (action.equals("INSERT")) {
				sql = new SQLBuilderPLAN_NAME(plan).buildInsertStatement();
			} else {
				sql = "";
			}
			int count = statement.executeUpdate(sql);
			if ((count < 1) && action.equals("UPDATE") ) {
				sql = new SQLBuilderPLAN_NAME(plan).buildInsertStatement();
				statement.executeUpdate(sql);
			}
			conn.commit();
			isUpdated = true;
	
		} finally {
				Utils.closeStatement(statement);
		}
		return isUpdated;
	}
	
	// refactor plan sql
	/* @deprecated use SQLBuilderPLAN_NAME
	public static String getPlanName(PlanObject key) throws Exception{
		Connection conn = null;
		String planName = null;
		
		try {
			conn = DBConnMgr.getInstance()
					.getConnection(DBConnMgr.PWSYS_SOURCE);
			planName = getPlanName(key, conn);
		} catch (Exception e) {
			throw e;
		} finally {
			if (conn != null)
				DBConnMgr.getInstance().releaseConnection(conn);
		}
		return planName;
	}
	public static synchronized String getPlanName(PlanObject key, Connection conn) {
		String planName = null;
		String product_code = key.getProductCode();
		String plan_code = key.getPlanCode();
		String issueState_code = key.getIssueState();
		String lob = key.getLOB();
		String effective_date = key.getEffectiveDate();
		String plan_type = key.getPlanType();

		Statement statement = null;
		ResultSet resultSet = null;
		StringBuffer sb = new StringBuffer();

		try {

			statement = conn.createStatement();

			Environment environment = EnvironmentManager.getInstance().getEnvironment(key.getEnvironment());
			sb.append("SELECT PLAN_NAME FROM ").append(environment.getApplSchema())
			        .append(".PLAN_NAME ");
			sb.append("WHERE ENVIRONMENT = '").append(
			        key.getEnvironment().toUpperCase()).append("' ");
			sb.append("AND COMPANY_CODE = '").append(
			        key.getCompany().toUpperCase()).append("' ");

			if (product_code != null) {

				sb.append("AND PRODUCT_PREFIX = '").append(
				        product_code.toUpperCase().substring(0, 1))
				        .append("' ");
				sb.append("AND PRODUCT_SUFFIX = '").append(
				        product_code.toUpperCase().substring(1, 2))
				        .append("' ");

			}

			if (plan_code != null) {
				sb.append("AND PLAN_CODE = '").append(plan_code.toUpperCase())
				        .append("' ");
			}

			if (issueState_code != null) {
				sb.append("AND ISSUE_STATE = '").append(issueState_code)
				        .append("' ");
			}

			if (lob != null) {
				sb.append("AND LINE_OF_BUSINESS = '").append(lob.toUpperCase())
				        .append("' ");
			}

			if (effective_date != null) {
				sb.append("AND EFFECTIVE_DATE = '").append(effective_date)
				        .append("' ");
			}

			if (plan_type != null) {
				sb.append("AND PLAN_TYPE = '").append(plan_type.toUpperCase())
				        .append("' ");
			}

			resultSet = statement.executeQuery(sb.toString());

			if (resultSet.next())
				planName = Utils.safeTrim(resultSet.getString(1));

		} catch (SQLException e) {
			throw WrapperException.wrap(e);
		} finally {

			Utils.closeResultSet(resultSet);
			Utils.closeStatement(statement);
		}

		return (planName);

	}

	public static boolean setPlanName(String planName, PlanObject key,
	        String action, Connection conn) {
		boolean bSuccess = false;

		if (!action.equals("DELETE")
		        && ((planName == null) || (planName.trim().length() == 0))) {
			return (false);
		}

		String environment_code = Utils.safeTrim(key.getEnvironment());
		String company_code = Utils.safeTrim(key.getCompany());
		String product_code = Utils.safeTrim(key.getProductCode());

		if (product_code.length() == 0) {
			product_code = " ";
		}

		String plan_code = Utils.safeTrim(key.getPlanCode()).toUpperCase();
		String issueState_code = Utils.safeTrim(key.getIssueState());
		String lob = Utils.safeTrim(key.getLOB()).toUpperCase();
		String effective_date = Utils.safeTrim(key.getEffectiveDate());
		String plan_type = Utils.safeTrim(key.getPlanType());

		// if (issueState_code.startsWith("*"))
		// issueState_code = toHVString(issueState_code);
		//
		// if (lob.startsWith("*"))
		// lob = toHVString(lob);

		Statement statement = null;
		StringBuffer sb = new StringBuffer();

		try {

			statement = conn.createStatement();
			conn.setAutoCommit(false);
			Environment environment = EnvironmentManager.getInstance().getEnvironment(key.getEnvironment());
			if (action.equals("INSERT")) {
				insertPlan(planName, environment_code, company_code,
				        product_code, plan_code, issueState_code, lob,
				        effective_date, plan_type, sb);
				statement.executeUpdate(sb.toString());
			} else if (action.equals("UPDATE") || action.equals("DELETE")) {
				if (action.equals("UPDATE")) {
					sb.append("UPDATE ").append(environment.getApplSchema()).append(
					        ".PLAN_NAME ");
					sb.append("SET PLAN_NAME = '").append(planName)
					        .append("' ");
				} else {
					sb.append("DELETE FROM ").append(environment.getApplSchema())
					        .append(".PLAN_NAME ");
				}

				sb.append("WHERE ENVIRONMENT = '").append(environment_code)
				        .append("' ");
				sb.append("AND COMPANY_CODE = '").append(company_code).append(
				        "' ");
				sb.append("AND PRODUCT_PREFIX = '").append(
				        product_code.substring(0, 1)).append("' ");
				sb.append("AND PRODUCT_SUFFIX = '").append(
				        product_code.substring(1, 2)).append("' ");
				sb.append("AND PLAN_CODE = '").append(plan_code).append("' ");
				sb.append("AND ISSUE_STATE = '").append(issueState_code)
				        .append("' ");
				sb.append("AND LINE_OF_BUSINESS = '").append(lob).append("' ");
				sb.append("AND EFFECTIVE_DATE = '").append(effective_date)
				        .append("' ");
				sb.append("AND PLAN_TYPE = '").append(plan_type).append("' ");
				int updateCount = statement.executeUpdate(sb.toString());
				if ((updateCount < 1) && !action.equals("DELETE")) {
					sb.setLength(0);
					insertPlan(planName, environment_code, company_code,
					        product_code, plan_code, issueState_code, lob,
					        effective_date, plan_type, sb);
					statement.executeUpdate(sb.toString());

				}
			}
			conn.commit();
			bSuccess = true;
		} catch (Exception e) {
			throw WrapperException.wrap(e);
		} finally {
			Utils.closeStatement(statement);
		}

		return bSuccess;
	}

	private static void insertPlan(String planName, String environment_code,
	        String company_code, String product_code, String plan_code,
	        String issueState_code, String lob, String effective_date,
	        String plan_type, StringBuffer sb) {
		
		Environment environment = EnvironmentManager.getInstance().getEnvironment(environment_code);
		sb.append("INSERT INTO ").append(environment.getApplSchema()).append(
		        ".PLAN_NAME ");
		sb
		        .append("(ENVIRONMENT, COMPANY_CODE, PRODUCT_PREFIX,PRODUCT_SUFFIX, PLAN_CODE, ISSUE_STATE, LINE_OF_BUSINESS, ");
		sb.append("EFFECTIVE_DATE, PLAN_TYPE, PLAN_NAME) ");
		sb.append("VALUES ('").append(environment_code).append("', ");
		sb.append("'").append(company_code).append("', ");
		sb.append("'").append(product_code.substring(0, 1)).append("', ");
		sb.append("'").append(product_code.substring(1, 2)).append("', ");
		sb.append("'").append(plan_code).append("', ");
		sb.append("'").append(issueState_code).append("', ");
		sb.append("'").append(lob).append("', ");
		sb.append("'").append(effective_date).append("', ");
		sb.append("'").append(plan_type).append("', ");
		sb.append("'").append(sqlQuoter(planName)).append("') ");

	}

	public static String getPlanName(PlanObject key) throws Exception {
		Connection conn = null;
		String planName = null;

		try {
			conn = DBConnMgr.getInstance()
			        .getConnection(key.getEnvironment(),DBConnMgr.APPL);
			planName = getPlanName(key, conn);
		} catch (Exception e) {
			throw e;
		} finally {
			if (conn != null)
				DBConnMgr.getInstance().releaseConnection(conn);
		}
		return planName;
	}

	public static boolean setPlanName(String planName, PlanObject key,
	        String action) throws Exception {

		Connection conn = null;
		boolean isRenamed = false;
		try {
			conn = DBConnMgr.getInstance()
			        .getConnection(key.getEnvironment(),DBConnMgr.APPL);
			isRenamed = setPlanName(planName, key, action, conn);
		} finally {
			if (conn != null)
				DBConnMgr.getInstance().releaseConnection(conn);
		}
		return isRenamed;
	}

	private static String sqlQuoter(String str) {

		String s = null;
		StringBuffer sb = new StringBuffer();
		sb.append(str);
		for (int i = 0; i < sb.length(); i++) {
			if (sb.charAt(i) == '\'') {
				sb.insert(i, "\'");
				i++;
			}
		}
		s = sb.toString();
		return (s);
	}

	// private static String toHVString(String lCode) {
	//
	// StringBuffer sb = new StringBuffer(lCode.length());
	// for (int i = 0; i < lCode.length(); i++) {
	// sb.append(Constants.HIGHVALUES_FROM_MAINFRAME);
	// }
	// return sb.toString();
	// }
	 */
}
