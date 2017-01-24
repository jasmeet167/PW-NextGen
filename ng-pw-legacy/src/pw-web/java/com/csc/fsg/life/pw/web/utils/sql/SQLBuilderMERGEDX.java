package com.csc.fsg.life.pw.web.utils.sql;

import java.lang.reflect.Method;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Vector;

import com.csc.fsg.life.exceptions.WrapperException;
import com.csc.fsg.life.pw.common.transferobjects.PlanCriteriaTO;
import com.csc.fsg.life.pw.common.util.WIPProperties;
import com.csc.fsg.life.pw.web.environment.EnvironmentManager;
import com.csc.fsg.life.pw.web.io.ColumnDescriptor;
import com.csc.fsg.life.pw.web.io.TableDescriptor;
import com.csc.fsg.life.pw.web.utils.SqlPW;

/* Modifications: T0103, WMABASEIXI-2749 ,CCCV-E-ISSUE_STATE */
// T0103 - new class for refactor plan key
// WMABASEIXI-2749 - add methods for BRS filter selection

public class SQLBuilderMERGEDX extends SQLBuilder {

	private PlanCriteriaTO planCriteria;
	
	private static WIPProperties wipProps = WIPProperties.getInstance();
	
	public SQLBuilderMERGEDX(String envId) {
		super(envId, "SESSION", "MERGEDX");
	}

	public SQLBuilderMERGEDX(String envId, PlanCriteriaTO planCriteria) {
		super(envId, "SESSION", "MERGEDX");
		this.planCriteria = planCriteria;
	}
	
	public String buildDeclareTableStatement() {
		addDeclareTableClause();
		addDeclareColumns();
		return getFinalStatement();
	}
	
	public String prepareInsertFromT000XStatement() {
		addInsertIntoClause();
		addInsertFromT000XColumns();
		addInsertValues();
		return getFinalStatement();
	}
	
	public String prepareInsertStatement() {
		addInsertIntoClause();
		addInsertColumns();
		addInsertValues();
		return getFinalStatement();
	}
	
	public String prepareUpdateStatement() {
		addUpdateClause();
		addUpdateColumns();
		addWhereClause();
		return getFinalStatement();
	}

	public String prepareDeleteStatement() {
		addDeleteClause();
		addWhereClause();
		return getFinalStatement();
	}

	public String buildSelectTableStatement(String userCondition) {
		addSelectAllClause();
		addFromClause();
		addUserConditionWhereClause(userCondition);
		addOrderByPlanKeysClause();
		return getFinalStatement();
	}
	
	public String buildSelectAllStatement() {
		addSelectAllClause();
		addFromClause();
		addWherePlanRowKeysClause();
		return getFinalStatement();
	}

	public String buildCountAllStatement() {
		setSelectCount(true);
		return buildSelectAllStatement();
	}

	public String buildSelectForWriterStatement() {
		setSelectDistinct(true);
		addSelectPlanKeysClause();
		addFromClause();
		setIgnoreHighValues(false);
		// TT wmA SPR 6140 - don't ignore product suffix == "*"
		setIgnoreHighValueProductSuffix(false);
		addWherePlanRowKeysClause();
		addGroupForWriterClause();
		addOrderForWriterClause();
		return getFinalStatement();		
	}
	
	public String buildSelectTablePtrsForWriterStatement() {
		addSelectTablePtrsClause();
		addFromClause();
		setIgnoreHighValues(false);
		addWherePlanRowKeysClause();
		addOrderTablePtrsForWriterClause();
		return getFinalStatement();
	}
	
	public String buildSelectIssueStateForIRU() {
		addSelectIssueStatesClause();
		addFromClause();
		setIgnoreHighValues(false);
		//addWherePlanRowKeysClause();
		//addOrderTablePtrsForWriterClause();
		return getFinalStatement();
	}
	
	public String buildSelectForPPMStatement() {
		addSelectAllClause();
		addFromClause();
		// TT wmA SPR 6384 - add plan row keys not just table pointers
		setIgnoreHighValues(false);
		addWherePlanRowKeysClause();
		return getFinalStatement();
	}

	public String buildSelectAllTablePtrsStatement() {
		addSelectTablePtrsClause();
		addFromClause();
		return getFinalStatement();
	}
	
	public String buildSelectWhereTablePtrsStatement() {
		addSelectPlanRowKeysClause();
		addFromClause();
		addWhereTablePtrsClause();
		return getFinalStatement();
	}

	public String buildSelectForRenamePlanStatement() {
		addSelectPlanRowKeysClause();
		addFromClause();
		addWhereTablePtrsClause();
		return getFinalStatement();
	}
	
	public String buildSelectForCompanyValidation() {
		setSelectDistinct(true);
		addSelectPlanKeysClause();
		addFromClause();
		return getFinalStatement();		
	}

	// WMABASEIXI-2749
	public String buildSelectForFilterStatement() {
		addSelectForFilterClause();
		addFromClause();
		return getFinalStatement();
	}

	protected String[] getPlanKeysColumnNames() {
		String[] columnNames = {
				wipProps.getCompanyCode(),
				wipProps.getProductPrefix(),
				wipProps.getProductSuffix(),
				wipProps.getPlanCode(),
				wipProps.getIssueState(),
				wipProps.getLOB(),
				wipProps.getEffectiveDate(),
				wipProps.getPlanType()
		};
		return columnNames;
	}
	protected String[] getPlanRowKeysColumnNames() {
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
				wipProps.getTablePtrSubset()
		};
		return columnNames;
	}
	protected String[] getTablePtrsColumnNames() {
		String[] columnNames = {
				wipProps.getTablePtrId(),
				wipProps.getTablePtrVar(),
				wipProps.getTablePtrSubset()
		};
		return columnNames;
	}
	protected String[] getIssueStateColumnName() {
		String[] columnNames = {
				wipProps.getIssueState()
		};
		return columnNames;
	}
	protected void addSelectPlanKeysClause() {
		addSelectColumnsClause(getPlanKeysColumnNames());
	}
	protected void addSelectPlanRowKeysClause() {
		addSelectColumnsClause(getPlanRowKeysColumnNames());
	}
	protected void addSelectTablePtrsClause() {
		addSelectColumnsClause(getTablePtrsColumnNames());
	}
	protected void addSelectIssueStatesClause() {
		addSelectColumnsClause(getIssueStateColumnName());
	}
	
	// WMABASEIXI-2749
	protected void addSelectForFilterClause() {
		PlanCriteriaTO planCriteria = getPlanCriteria();
		TableDescriptor td = EnvironmentManager.getInstance()
			.getEnvironment(planCriteria.getEnvironment()).getTableDescMgr().getTableDescriptor("000");
		Vector<ColumnDescriptor> cds = td.getColumnDescriptors();
		ColumnDescriptor cd;
		Class<PlanCriteriaTO> c = PlanCriteriaTO.class;
		Class[] argTypes = new Class[0];
		Object[] args = new Object[0];
		Method method;
		Object value;
		String columnName;

		setSelectDistinct(true);
		try {
			for (int i = 0; i < cds.size(); i++) {
				cd = cds.get(i);
				method = c.getMethod(cd.getGetterMethod().getName(), argTypes);
				value = method.invoke(planCriteria, args);
				columnName = cd.getDbColumnName();
				if (value.equals("")) {
					if (columnName.equals(PlanCriteriaTO.PRODUCT_PREFIX_NAME))
						addSelectColumnClause("CONCAT(" +
								PlanCriteriaTO.PRODUCT_PREFIX_NAME + "," +
								PlanCriteriaTO.PRODUCT_SUFFIX_NAME + ")"
							);
					else
						addSelectColumnClause(columnName);
					break;
				}
			}
		} 
		catch (Exception e) {
			throw WrapperException.wrap(e);
		}
	}
	
	protected void addDeclareColumns() {
		String[] columnDefintions = {
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
				wipProps.getOldCompanyCode() + " CHAR(3) NOT NULL DEFAULT",
				wipProps.getOldProductPrefix() + " CHAR(1) NOT NULL DEFAULT",
				wipProps.getOldProductSuffix() + " CHAR(1) NOT NULL DEFAULT",
				wipProps.getOldPlanCode() + " CHAR(6) NOT NULL DEFAULT",
				wipProps.getOldIssueState() + " CHAR(3) NOT NULL DEFAULT",
				wipProps.getOldLOB() + " CHAR(3) NOT NULL DEFAULT",
				wipProps.getOldEffectiveDate() + " DATE NOT NULL WITH DEFAULT",
				wipProps.getOldPlanType() + " CHAR(1) NOT NULL DEFAULT",
				wipProps.getOldPtrId() + " CHAR(3) NOT NULL DEFAULT",
				wipProps.getOldPtrVar() + " CHAR(1) NOT NULL DEFAULT",
				wipProps.getOldPtrSubset() + " CHAR(16) NOT NULL DEFAULT",
				wipProps.getProjectName() + " CHAR(16) NOT NULL DEFAULT",
				wipProps.getOperation() + " CHAR(6) NOT NULL DEFAULT",
				wipProps.getChangeUserId() + " CHAR(32) NOT NULL DEFAULT",
				wipProps.getTimeStamp() + " TIMESTAMP NOT NULL DEFAULT"
		};
		addDeclareColumns(columnDefintions);
	}
	
	protected void addInsertFromT000XColumns() {
		addInsertColumns(getPlanRowKeysColumnNames());
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
	
	protected void addUpdateColumns() {
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
		addUpdateColumns(columnNames);
	}

	protected void addWhereClause() {
		addWhereClause(getPlanRowKeysColumnNames());
	}
	protected void addWherePlanRowKeysClause() {
		startWhereClause();
		PlanCriteriaTO planCriteria = getPlanCriteria();
		addWhereColumnCondition(PlanCriteriaTO.COMPANY_CODE_NAME, planCriteria.getCompanyCode());
		addWhereColumnCondition(PlanCriteriaTO.PRODUCT_PREFIX_NAME, planCriteria.getProductPrefix());
		addWhereColumnCondition(PlanCriteriaTO.PRODUCT_SUFFIX_NAME, planCriteria.getProductSuffix());
		addWhereColumnCondition(PlanCriteriaTO.PLAN_CODE_NAME, planCriteria.getPlanCode());
		addWhereColumnCondition(PlanCriteriaTO.ISSUE_STATE_NAME, planCriteria.getIssueState());
		addWhereColumnCondition(PlanCriteriaTO.LINE_OF_BUSINESS_NAME, planCriteria.getLineOfBusiness());
		addWhereColumnCondition(PlanCriteriaTO.EFFECTIVE_DATE_NAME, planCriteria.getEffectiveDate());
		addWhereColumnCondition(PlanCriteriaTO.PLAN_TYPE_NAME, planCriteria.getPlanType());
		addWhereColumnCondition(PlanCriteriaTO.TABLE_PTR_ID_NAME, planCriteria.getTablePtrId());
		addWhereColumnCondition(PlanCriteriaTO.TABLE_PTR_VAR_NAME, planCriteria.getTablePtrVar());
		addWhereColumnCondition(PlanCriteriaTO.TABLE_PTR_SUBSET_NAME, planCriteria.getTablePtrSubset());
	}
	protected void addWhereTablePtrsClause() {
		startWhereClause();
		PlanCriteriaTO planCriteria = getPlanCriteria();
		addWhereColumnCondition(PlanCriteriaTO.TABLE_PTR_ID_NAME, planCriteria.getTablePtrId());
		addWhereColumnCondition(PlanCriteriaTO.TABLE_PTR_VAR_NAME, planCriteria.getTablePtrVar());
		addWhereColumnCondition(PlanCriteriaTO.TABLE_PTR_SUBSET_NAME, planCriteria.getTablePtrSubset());
	}
	
	protected void addWhereEnvNCompanyClause() {
		startWhereClause();
		PlanCriteriaTO planCriteria = getPlanCriteria();
		addWhereColumnCondition(PlanCriteriaTO.ENVIRONMENT_NAME, planCriteria.getEnvironment());
		addWhereColumnCondition(PlanCriteriaTO.COMPANY_CODE_NAME, planCriteria.getCompanyCode());
		
	}
	
	protected void addGroupForWriterClause() {
		String[] columnNames = {
				wipProps.getCompanyCode(),
				wipProps.getProductPrefix(),
				wipProps.getProductSuffix(),
				wipProps.getPlanCode(),
				wipProps.getPlanType(),
				wipProps.getIssueState(),
				wipProps.getLOB(),
				wipProps.getEffectiveDate()
		};
		addGroupByClause(columnNames);
	}

	protected void addOrderForWriterClause() {
		String[] columnNames = {
				wipProps.getCompanyCode(),
				wipProps.getProductPrefix(),
				wipProps.getProductSuffix(),
				wipProps.getPlanType(),
				wipProps.getPlanCode(),
				wipProps.getIssueState(),
				wipProps.getLOB(),
				wipProps.getEffectiveDate()
		};
		addOrderByClause(columnNames);
	}
	protected void addOrderTablePtrsForWriterClause() {
		addOrderByClause(getTablePtrsColumnNames());
	}
	
	protected void addUserConditionWhereClause(String userCondition) {
		if ( userCondition != null && userCondition.length() > 0 )
			sql.append(" WHERE ( ").append(userCondition).append(" ) ");
	}

	public static void setInsertFromT000XValues(PreparedStatement stmt, ResultSet rs) throws java.sql.SQLException {
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
	}

	public static void setInsertFromWIPXValues(PreparedStatement stmt, ResultSet rs) throws java.sql.SQLException {
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

	public static void setUpdateValues(PreparedStatement stmt, ResultSet rs) throws java.sql.SQLException {
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
		SqlPW.setString(stmt, 27, rs, wipProps.getOldCompanyCode());
		SqlPW.setString(stmt, 28, rs, wipProps.getOldProductPrefix());
		SqlPW.setString(stmt, 29, rs, wipProps.getOldProductSuffix());
		SqlPW.setString(stmt, 30, rs, wipProps.getOldPlanCode());
		stmt.setString(31, rs.getString(wipProps.getOldIssueState()));
		stmt.setString(32, rs.getString(wipProps.getOldLOB()));
		SqlPW.setDate(stmt, 33, rs, wipProps.getOldEffectiveDate());
		SqlPW.setString(stmt, 34, rs, wipProps.getOldPlanType());
		SqlPW.setString(stmt, 35, rs, wipProps.getOldPtrId());
		SqlPW.setString(stmt, 36, rs, wipProps.getOldPtrVar());
		SqlPW.setString(stmt, 37, rs, wipProps.getOldPtrSubset());
	}

	public static void setDeleteValues(PreparedStatement stmt, ResultSet rs) throws java.sql.SQLException {
		stmt.setString(1, rs.getString(wipProps.getOldCompanyCode()));
		stmt.setString(2, rs.getString(wipProps.getOldProductPrefix()));
		stmt.setString(3, rs.getString(wipProps.getOldProductSuffix()));
		stmt.setString(4, rs.getString(wipProps.getOldPlanCode()));
		stmt.setString(5, rs.getString(wipProps.getOldIssueState()));
		stmt.setString(6, rs.getString(wipProps.getOldLOB()));
		SqlPW.setDate(stmt, 7, rs, wipProps.getOldEffectiveDate());
		stmt.setString(8, rs.getString(wipProps.getOldPlanType()));
		stmt.setString(9, rs.getString(wipProps.getOldPtrId()));
		stmt.setString(10, rs.getString(wipProps.getOldPtrVar()));
		stmt.setString(11, rs.getString(wipProps.getOldPtrSubset()));
	}

	protected PlanCriteriaTO getPlanCriteria() {
		return planCriteria;
	}

	protected void setPlanCriteria(PlanCriteriaTO planCriteria) {
		this.planCriteria = planCriteria;
	}

}
