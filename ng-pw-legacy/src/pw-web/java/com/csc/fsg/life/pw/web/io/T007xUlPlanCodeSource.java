package com.csc.fsg.life.pw.web.io;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;

import com.csc.fsg.life.exceptions.WrapperException;
import com.csc.fsg.life.pw.common.transferobjects.PlanTO;
import com.csc.fsg.life.pw.common.util.Utils;
import com.csc.fsg.life.pw.web.environment.Environment;
import com.csc.fsg.life.pw.web.environment.EnvironmentManager;
import com.csc.fsg.life.pw.web.log.PWServerLogManager;
import com.csc.fsg.life.pw.web.utils.HighValueHandler;

/* Modifications: ENH1053 */

public class T007xUlPlanCodeSource
	extends TCDynamicTarget
{
	private static Log log = PWServerLogManager.getLog(T007xUlPlanCodeSource.class.getPackage().getName());

	@Override
	public boolean isUsingTable(String tableName)
	{
		if (tableName == null)
			return false;

		return tableName.equals("T000X")
			|| tableName.equals("T006E");
	}

	@Override
	public List<String[]> getDynamicAvList(String envId, String company, PlanTO plan, Connection conn)
	{
		List<String[]> avList = fetchFromCache(envId, company, plan, true);
		if (avList != null)
			return avList;

		avList = getAvValues(envId, plan, conn);
		if (avList == null) {
			return new ArrayList<String[]>();
		}
		else {
			putInCache(envId, company, plan, avList);
			return avList;
		}
	}

	private List<String[]> getAvValues(String envId, PlanTO plan, Connection connection)
	{
		List<String[]> dynamicValues = new ArrayList<String[]>();

		Environment environment = EnvironmentManager.getInstance().getEnvironment(envId);
		String schemaName = environment.getSchema();

		String highValues_3 = HighValueHandler.getHighValues(3, envId);
		String issueState = plan.getIssueState();
		String issueStateClause = null;
		boolean isIssueStateClauseUsed = !HighValueHandler.isAllAsterisks(issueState);

		if (isIssueStateClauseUsed)
			issueStateClause = "  AND T000X.ISSUE_STATE IN (?, ?) ";
		else
			issueStateClause = "";

		String lob = plan.getLineOfBusiness();
		String lobClause = null;
		boolean isLobClauseUsed = !HighValueHandler.isAllAsterisks(lob);

		if (isLobClauseUsed)
			lobClause = "  AND T000X.LINE_OF_BUSINESS IN (?, ?) ";
		else
			lobClause = "";

		String query = new StringBuilder()
			.append("SELECT DISTINCT T006E.RIDER_BENE_TYPE,")
			.append("                T000X.PLAN_CODE")

			.append(" FROM  " + schemaName + ".T000X T000X,") 
			.append("       " + schemaName + ".T006E T006E ")

			.append("WHERE  T000X.COMPANY_CODE = T006E.COMPANY_CODE") 
			.append("  AND  T000X.TABLE_PTR_SUBSET = T006E.TABLE_SUBSET")
			.append("  AND  T000X.PLAN_TYPE = 'R'")
			.append("  AND  T000X.PRODUCT_PREFIX = 'U'") 
			.append("  AND  T000X.TABLE_PTR_ID = '006'")
			.append("  AND  T000X.COMPANY_CODE = ?")
			.append(issueStateClause)
			.append(lobClause)
			.toString();

		log.debug(query);

		PreparedStatement pStatement = null;
		ResultSet rs = null;

		try {
			int i = 0;
			pStatement = connection.prepareStatement(query);
			pStatement.setString(++i, plan.getCompanyCode());

			if (isIssueStateClauseUsed) {
				pStatement.setString(++i, plan.getIssueState());
				pStatement.setString(++i, highValues_3);
			}
			if (isLobClauseUsed) {
				pStatement.setString(++i, plan.getLineOfBusiness());
				pStatement.setString(++i, highValues_3);
			}

			rs = pStatement.executeQuery();
			String currentRiderBeneType = null;

			while (rs.next()) {
				String riderBeneType = rs.getString("RIDER_BENE_TYPE");
				String planCode = rs.getString("PLAN_CODE");

				if (!riderBeneType.equals(currentRiderBeneType)) {
					currentRiderBeneType = riderBeneType;
					String[] blankValue = new String[3];
					blankValue[0] = riderBeneType.trim();
					blankValue[1] = "";
					blankValue[2] = "";
					dynamicValues.add(blankValue);
				}

				String[] dynamicValue = new String[3];
				if (riderBeneType != null)
					dynamicValue[0] = riderBeneType.trim();
				if (planCode != null)
					dynamicValue[1] = planCode.trim();
				// The last element of dynamicValue is null, because no display
				// value is applicable

				dynamicValues.add(dynamicValue);
			}
		}
		catch (SQLException e) {
			throw WrapperException.wrap(e);
		}
		finally {
			Utils.closeResultSet(rs);
			Utils.closeStatement(pStatement);
		}

		return dynamicValues;
	}
}
