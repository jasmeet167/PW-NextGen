package com.csc.fsg.life.pw.web.utils.sql;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.csc.fsg.life.pw.common.util.WIPProperties;
import com.csc.fsg.life.pw.web.utils.SqlPW;

/* Modifications: T0103 */
// T0103 - new class for refactor plan key

public class SQLBuilderWIPX extends SQLBuilder {

	private static WIPProperties wipProps = WIPProperties.getInstance();
	
	public SQLBuilderWIPX(String envId) {
		super(envId, "SESSION", "WIPX");
	}

	public String buildDeclareTableStatement() {
		addDeclareTableClause();
		addDeclareColumns();
		return getFinalStatement();
	}
	
	public String prepareInsertStatement() {
		addInsertIntoClause();
		addInsertColumns();
		addInsertValues();
		return getFinalStatement();
	}
	
	protected void addDeclareColumns() {
		String[] columnDefs = {
				wipProps.getCompanyCode() + " CHAR(3) NOT NULL",
				wipProps.getProductPrefix() + " CHAR(1) NOT NULL",
				wipProps.getProductSuffix() + " CHAR(1) NOT NULL",
				wipProps.getPlanCode() + " CHAR(6) NOT NULL",
				wipProps.getIssueState() + " CHAR(3) NOT NULL",
				wipProps.getLOB() + " CHAR(3) NOT NULL",
				wipProps.getEffectiveDate() + " DATE NOT NULL",
				wipProps.getPlanType() + " CHAR(1) NOT NULL",
				wipProps.getTablePtrId() + " CHAR(3) NOT NULL",
				wipProps.getTablePtrVar() + " CHAR(1) NOT NULL",
				wipProps.getTablePtrSubset() + " CHAR(16) NOT NULL",
				wipProps.getOperation() + " CHAR(6) NOT NULL",
				wipProps.getOldCompanyCode() + " CHAR(3) NOT NULL",
				wipProps.getOldProductPrefix() + " CHAR(1) NOT NULL",
				wipProps.getOldProductSuffix() + " CHAR(1) NOT NULL",
				wipProps.getOldPlanCode() + " CHAR(6) NOT NULL",
				wipProps.getOldIssueState() + " CHAR(3) NOT NULL",
				wipProps.getOldLOB() + " CHAR(3) NOT NULL",
				wipProps.getOldEffectiveDate() + " DATE NOT NULL WITH DEFAULT",
				wipProps.getOldPlanType() + " CHAR(1) NOT NULL",
				wipProps.getOldPtrId() + " CHAR(3) NOT NULL",
				wipProps.getOldPtrVar() + " CHAR(1) NOT NULL",
				wipProps.getOldPtrSubset() + " CHAR(16) NOT NULL",
				wipProps.getProjectName() + " CHAR(16) NOT NULL",
				wipProps.getChangeUserId() + " CHAR(32) NOT NULL",
				wipProps.getTimeStamp() + " TIMESTAMP NOT NULL"
		};
		addDeclareColumns(columnDefs);
	}

	protected void addInsertColumns() {
		String[] columnNames = {
				wipProps.getCompanyCode(),
				wipProps.getProductPrefix(),
				wipProps.getProductSuffix(),
				wipProps.getPlanCode(),
				wipProps.getIssueState(),
				wipProps.getLOB(),
				wipProps.getEffectiveDate(),
				wipProps.getPlanType(),
				wipProps.getTablePtrId(),
				wipProps.getTablePtrVar(),
				wipProps.getTablePtrSubset(),
				wipProps.getOldCompanyCode(),
				wipProps.getOldProductPrefix(),
				wipProps.getOldProductSuffix(),
				wipProps.getOldPlanCode(),
				wipProps.getOldIssueState(),
				wipProps.getOldLOB(),
				wipProps.getOldEffectiveDate(),
				wipProps.getOldPlanType(),
				wipProps.getOldPtrId(),
				wipProps.getOldPtrVar(),
				wipProps.getOldPtrSubset(),
				wipProps.getProjectName(),
				wipProps.getOperation(),
				wipProps.getChangeUserId(),
				wipProps.getTimeStamp()
		};
		addInsertColumns(columnNames);
	}

	public static void setInsertValues(PreparedStatement stmt, ResultSet rs) throws java.sql.SQLException {
		SqlPW.setString(stmt, 1, rs, wipProps.getCompanyCode());
		SqlPW.setString(stmt, 2, rs, wipProps.getProductPrefix());
		SqlPW.setString(stmt, 3, rs, wipProps.getProductSuffix());
		SqlPW.setString(stmt, 4, rs, wipProps.getPlanCode());
		stmt.setString(5, rs.getString(wipProps.getIssueState()));
		stmt.setString(6, rs.getString(wipProps.getLOB()));
		SqlPW.setDate(stmt, 7, rs, wipProps.getEffectiveDate());
		SqlPW.setString(stmt, 8, rs, wipProps.getPlanType());
		SqlPW.setString(stmt, 9, rs, wipProps.getTablePtrId());
		SqlPW.setString(stmt, 10, rs, wipProps.getTablePtrVar());
		SqlPW.setString(stmt, 11, rs, wipProps.getTablePtrSubset());
		SqlPW.setString(stmt, 12, rs, wipProps.getOldCompanyCode());
		SqlPW.setString(stmt, 13, rs, wipProps.getOldProductPrefix());
		SqlPW.setString(stmt, 14, rs, wipProps.getOldProductSuffix());
		SqlPW.setString(stmt, 15, rs, wipProps.getOldPlanCode());
		stmt.setString(16, rs.getString(wipProps.getOldIssueState()));
		stmt.setString(17, rs.getString(wipProps.getOldLOB()));
		SqlPW.setDate(stmt, 18, rs, wipProps.getOldEffectiveDate());
		SqlPW.setString(stmt, 19, rs, wipProps.getOldPlanType());
		SqlPW.setString(stmt, 20, rs, wipProps.getOldPtrId());
		SqlPW.setString(stmt, 21, rs, wipProps.getOldPtrVar());
		SqlPW.setString(stmt, 22, rs, wipProps.getOldPtrSubset());
		SqlPW.setString(stmt, 23, rs, wipProps.getProjectName());
		SqlPW.setString(stmt, 24, rs, wipProps.getOperation());
		SqlPW.setString(stmt, 25, rs, wipProps.getChangeUserId());
		SqlPW.setTimestamp(stmt, 26, rs, wipProps.getTimeStamp());
	}

}
