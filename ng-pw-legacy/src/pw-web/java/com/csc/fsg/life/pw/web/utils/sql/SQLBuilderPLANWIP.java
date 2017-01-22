package com.csc.fsg.life.pw.web.utils.sql;

import java.lang.reflect.Method;
import java.util.Vector;
import java.util.List;

import com.csc.fsg.life.exceptions.WrapperException;
import com.csc.fsg.life.pw.common.transferobjects.PlanCriteriaTO;
import com.csc.fsg.life.pw.common.util.Constants;
import com.csc.fsg.life.pw.common.util.WIPProperties;
import com.csc.fsg.life.pw.web.io.ColumnDescriptor;
import com.csc.fsg.life.pw.web.io.WIPTableFilter;
import com.csc.fsg.life.pw.web.io.descriptor.wma.PLANWIPDescriptor;

/* Modifications: T0103, T0091, T0129 */
// T0103 - new class for refactor plan key

public class SQLBuilderPLANWIP extends SQLBuilder {

	private PlanCriteriaTO planCriteria;
	private WIPTableFilter wipTableFilter;
	
	private static WIPProperties wipProps = WIPProperties.getInstance();

	public SQLBuilderPLANWIP(String envId) {
		super(envId, APPL, new PLANWIPDescriptor());
	}

	public SQLBuilderPLANWIP(PlanCriteriaTO planCriteria) {
		this(planCriteria.getEnvironment());
		setPlanCriteria(planCriteria);
	}

	public SQLBuilderPLANWIP(WIPTableFilter wipTableFilter) {
		super(wipTableFilter.getEnvironment(), APPL, new PLANWIPDescriptor());
		setWIPTableFilter(wipTableFilter);
		setPlanCriteria(wipTableFilter.getData());
	}

	public String buildInsertStatement(String wipOp) {
		addInsertIntoClause();
		addInsertColumns();
		addInsertValues(getPlanCriteria(),wipOp);
		return getFinalStatement();
	}

	public String buildToggleLockStatement() {
		addUpdateClause();
		addSetForToggleLockClause();
		addWhereTableFilterClause();
		return getFinalStatement();
	}

	public String buildUnlockStatement() {
		addUpdateClause();
		addSetForUnlockClause();
		addWhereForUnlockClause();
		return getFinalStatement();
	}
	
	protected void addSetForToggleLockClause() {
		startUpdateColumns();
		String value = null;
		if (getWIPTableFilter().isLocked()) {
			value = " ";
		} else {
			value = "L";
		}
		addSetColumn(wipProps.getPromotionLock(Constants.PLAN_WIP), value);
	}

	protected void addSetForUnlockClause() {
		startUpdateColumns();
		addSetColumn(wipProps.getPromotionLock(Constants.PLAN_WIP), " ");
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
		setColumnNames();
		addWhereClause();
		return getFinalStatement();
	}
	protected void addUpdateColumns() {
		Vector<ColumnDescriptor> cds = getTableDescriptor().getColumnDescriptors();
		String[] columnNames = new String[cds.size()];
		for (int i = 0; i < cds.size(); i++) {
			ColumnDescriptor cd = cds.get(i);
			String columnName = cd.getDbColumnName();
			if (
					(!columnName.startsWith("OLD")) && 
					(!columnName.equals(wipProps.getTimeStamp())) &&
					(!columnName.equals(wipProps.getDBSequence()))
				) {
				columnNames[i] = columnName;			
			}
		}
		addUpdateColumns(columnNames);
	}

	public String prepareDeleteStatement() {
		addDeleteClause();
		setColumnNames();
		addWhereClause();
		return getFinalStatement();
	}

	protected void setColumnNames() {
		Vector<ColumnDescriptor> cds = getTableDescriptor().getColumnDescriptors();
		String[] columnNames = new String[cds.size()];
		for (int i = 0; i < cds.size(); i++) {
			ColumnDescriptor cd = cds.get(i);
			String columnName = cd.getDbColumnName();
			if (
					(!columnName.equals(wipProps.getTimeStamp())) &&
					(!columnName.equals(wipProps.getDBSequence())) &&
					(!columnName.equals(wipProps.getOperation())) &&
					(!columnName.equals(wipProps.getProjectName())) &&
					(!columnName.equals(wipProps.getChangeUserId())) &&
					(!columnName.equals(wipProps.getPromoteUserId())) &&
					(!columnName.equals(wipProps.getPackageId()))
				) {
				columnNames[i] = columnName;			
			}
		}
		setColumnNames(columnNames);
	}

	protected void addWhereColumnCondition(String name, String value) {
		String operator = "=";
		if (
				(name.equals(wipProps.getErrorIndicator())) &&
				(value.equals("?"))
			) {
			operator = "LIKE";
		}
		addWhereColumnCondition(name, operator, value);
	}

	public String buildDeleteStatement() {
		addDeleteClause();
		addWhereTableFilterClause();
		return getFinalStatement();
	}

	public String buildCountAllStatement() {
		setSelectCount(true);
		return buildSelectAllStatement();
	}

	public String buildSelectAllStatement() {
		addSelectAllClause();
		addFromClause();
		setIgnoreHighValues(false);
		addWhereTableFilterClause();
		return getFinalStatement();
	}

	public String buildSelectForFilterStatement() {
		addSelectForFilterClause();
		addFromClause();
		addWhereForFilterClause();
		return getFinalStatement();
	}
	
	public String buildSelectForChangesOnlyStatement() {
		addSelectForChangesOnlyClause();
		addFromClause();
		addWhereTableFilterClause();
		return getFinalStatement();
	}
	
	public String buildSelectForMergeStatement() {
		addSelectForMergeClause();
		addFromClause();
		setIgnoreHighValues(false);
		addWhereForMergeClause();
		return getFinalStatement();
	}

	public String buildSelectForAudit(String userId, String packageId) {
		String newConcatKey = wipProps.getCompanyCode() +
			" CONCAT " + wipProps.getProductPrefix() +
			" CONCAT " + wipProps.getProductSuffix() +
			" CONCAT " + wipProps.getPlanCode() +
			" CONCAT " + wipProps.getIssueState() +
			" CONCAT " + wipProps.getLOB() +
			" CONCAT CHAR(" + wipProps.getEffectiveDate() + ")" +
			" CONCAT " + wipProps.getPlanType() +
			" CONCAT " + wipProps.getTablePtrId() +
			" CONCAT " + wipProps.getTablePtrVar() +
			" CONCAT " + wipProps.getTablePtrSubset();
		
		String oldConcatKey = wipProps.getOldCompanyCode() +
			" CONCAT " + wipProps.getOldProductPrefix() +
			" CONCAT " + wipProps.getOldProductSuffix() +
			" CONCAT " + wipProps.getOldPlanCode() +
			" CONCAT " + wipProps.getOldIssueState() +
			" CONCAT " + wipProps.getOldLOB() +
			" CONCAT CHAR(" + wipProps.getOldEffectiveDate() + ")" +
			" CONCAT " + wipProps.getOldPlanType() +
			" CONCAT " + wipProps.getOldPtrId() +
			" CONCAT " + wipProps.getOldPtrVar() +
			" CONCAT " + wipProps.getOldPtrSubset();
		
		sql.append("( Select ENVIRONMENT ,'T000X' as DDLNAME, '000' AS TABLE_ID,  COMPANY_CODE, " +
				"PRODUCT_PREFIX, '' AS SUBSET_NAME, " +
				newConcatKey + " AS NEW_CONCAT_KEY," +
				" '' AS NEW_DATA,  OPERATION," +
				oldConcatKey + " AS OLD_CONCAT_KEY, " +
				" '' AS OLD_DATA,  PROJECT_NAME, CHANGE_USER_ID, " +
				"'"+userId.toUpperCase()+"' as PROMOTE_USER_ID, "
				+ "'" + packageId + "' as PACKAGE_ID");
		
		addFromClause();
		sql.append(" WHERE PROMOTION_LOCK = 'L' )");
		return getFinalStatement();
	}
	
	protected void addInsertColumns() {
		Vector<ColumnDescriptor> cds = getTableDescriptor().getColumnDescriptors();
		ColumnDescriptor cd;
		String[] columnNames = new String[cds.size()];
		String name;
		for (int i = 0; i < cds.size(); i++) {
			cd = cds.get(i);
			name = cd.getDbColumnName();
			if (!name.equals("TIME_STAMP")) {
				columnNames[i] = name;
			}
		}
		addInsertColumns(columnNames);
	}

	protected void addInsertValues(PlanCriteriaTO planCriteria, String wipOp) {
		String[] columnNames = getColumnNames();
		String name;
		String nameTmp;
		String value;
		String[] values = new String[columnNames.length];
		Class<? extends PlanCriteriaTO> planClass = planCriteria.getClass();
		Method[] methods = planClass.getMethods();
		Method method;
		String methodName;
		boolean methodExists;
		Object[] args = new Object[0];
		Class[] argTypes = new Class[0];
		try {
			for (int i = 0; i < columnNames.length; i++) {
				value = null;
				name = columnNames[i];
				if (name != null) {
					nameTmp = name.replaceFirst("LOB", PlanCriteriaTO.LINE_OF_BUSINESS_NAME);
					nameTmp = nameTmp.replaceFirst("OLD_PTR", "OLD_TABLE_PTR");
					if (nameTmp.equals(wipProps.getChangeUserId())) {
						nameTmp = PlanCriteriaTO.USER_NAME_KEY;
					}
					methodName = getGetterMethodName(nameTmp, true);
					methodExists = false;
					for (Method method2 : methods) {
						if (method2.getName().equals(methodName)) {
							methodExists = true;
							break;
						}
					}
					if (methodExists == true) {
					    method = planClass.getMethod(methodName, argTypes);
						value = (String) method.invoke(planCriteria, args);
					}
					if ((value != null) && (!value.equals(""))) {
						value = "'" + value + "'";
					}
					else {
						if (
								(name.equals(wipProps.getTablePtrId())) ||
								(name.equals(wipProps.getTablePtrVar())) ||
								(name.equals(wipProps.getTablePtrSubset())) ||
								(name.equals(wipProps.getOldPtrId())) ||
								(name.equals(wipProps.getOldPtrVar())) ||
								(name.equals(wipProps.getOldPtrSubset())) ||
								(name.equals(wipProps.getDBSequence())))
							value = "?";
						else if (name.equals(wipProps.getOperation()))
							value = "'" + wipOp + "'";
						else
							value = "''";
					}
					values[i] = value;
				}
			}
		}
		catch (Exception e) {
			throw WrapperException.wrap(e);
		}
		addInsertValues(values);
	}

	protected void addSelectForFilterClause() {
		PlanCriteriaTO planCriteria = getPlanCriteria();
		Vector<ColumnDescriptor> cds = getTableDescriptor().getColumnDescriptors();
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

	protected void addSelectForChangesOnlyClause() {
		setSelectDistinct(true);
		WIPProperties wipProps = WIPProperties.getInstance();
		String[] columnNames = {
				wipProps.getCompanyCode(),
				wipProps.getProjectName(),
				wipProps.getPackageId(),
				wipProps.getProductPrefix()
		};
		addSelectColumnsClause(columnNames);
	}

	protected void addWhereTableFilterClause() {
		addWherePlanRowKeysClause(getPlanCriteria());

		WIPTableFilter filter = getWIPTableFilter();
		if ( filter != null ) {
			Vector projectNames = filter.getProjectNames();
			if ((projectNames != null) && (projectNames.size() != 0)) {
				// String projectName = (String) projectNames.firstElement();
				if ( projectNames.size() == 1 ) {
					addWhereColumnCondition(wipProps.getProjectName(Constants.PLAN_WIP),
							(String) projectNames.firstElement());
				} else {
					StringBuffer projectsCSV = new StringBuffer();
					for (int i=0; i < projectNames.size(); i++) {
						projectsCSV.append("'").append(projectNames.get(i)).append("'");
						if ( i < (projectNames.size() - 1) )
							projectsCSV.append(",");
					}
					addWhereColumnCondition(wipProps.getProjectName(Constants.PLAN_WIP),
							projectsCSV.toString());
				}
			}
			Vector packageIds = filter.getPackageIds();
			if ((packageIds != null) && (packageIds.size() != 0)) {
				// String packageId = (String) packageIds.firstElement();
				if ( packageIds.size() == 1 ) {
					addWhereColumnCondition(wipProps.getPackageId(Constants.PLAN_WIP),
							(String) packageIds.firstElement());
				} else {
					StringBuffer packagesCSV = new StringBuffer();
					for (int i=0; i < packageIds.size(); i++) {
						packagesCSV.append("'").append((String) packageIds.get(i)).append("'");
						if ( i < (packageIds.size() - 1) )
							packagesCSV.append(",");
					}
					addWhereColumnCondition(wipProps.getPackageId(Constants.PLAN_WIP),
							packagesCSV.toString());
				}
			}
			Vector changeUserIds = filter.getChangeUserIds();
			if ((changeUserIds != null) && (changeUserIds.size() != 0)) {
				if ( changeUserIds.size() == 1 ) {
					addWhereColumnCondition(wipProps.getChangeUserId(Constants.PLAN_WIP),
							(String) changeUserIds.firstElement());
				} else {
					StringBuffer usersCSV = new StringBuffer();
					for (int i=0; i < changeUserIds.size(); i++) {
						usersCSV.append("'").append((String) changeUserIds.get(i)).append("'");
						if ( i < (changeUserIds.size() - 1) )
							usersCSV.append(",");
					}
					addWhereColumnCondition(wipProps.getChangeUserId(Constants.PLAN_WIP),
							usersCSV.toString());
				}
			}
			if (filter.isWithErrors()) {
				addWhereColumnCondition(wipProps.getErrorIndicator(Constants.PLAN_WIP), "Y");
			}
			if (filter.isLocked()) {
				addWhereColumnCondition(wipProps.getPromotionLock(Constants.PLAN_WIP), "L");
			}
		}
	}

	protected void addWhereForFilterClause() {
		addWherePlanRowKeysClause(getPlanCriteria());
		addWhereColumnCondition(wipProps.getOperation(), Constants.INSERT_OPERATION);
	}

	protected void addWhereForUnlockClause() {
		startWhereClause();
		addWhereColumnCondition(wipProps.getPromotionLock(Constants.PLAN_WIP), "L");		
	}
	
	protected void addSelectForMergeClause() {
		WIPProperties wipProps = WIPProperties.getInstance();
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
				wipProps.getOperation(),
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
				wipProps.getChangeUserId(),
				wipProps.getTimeStamp()
		};
		addSelectColumnsClause(columnNames);
	}
	
	protected void addWhereForMergeClause() {
		PlanCriteriaTO planCriteria = getPlanCriteria();

		startWhereClause();
		WIPProperties wipProps = WIPProperties.getInstance();
		String value;
		addWhereColumnCondition(wipProps.getEnvironment(), planCriteria.getEnvironment());
		
		List<String> projectNames = planCriteria.getProjects();
		if ( projectNames.size() > 0 ) {
			// TT wmA SPR 6362 - ignore projects "None" 
			if ( !projectNames.get(0).equals("None") ) {
				if ( projectNames.size() == 1 ) {
					addWhereColumnCondition(wipProps.getProjectName(Constants.PLAN_WIP),
							projectNames.get(0));
				} else {
					StringBuffer projectsCSV = new StringBuffer();
					for (int i=0; i < projectNames.size(); i++) {
						projectsCSV.append("'").append(projectNames.get(i)).append("'");
						if ( i < (projectNames.size() - 1) )
							projectsCSV.append(",");
					}
					addWhereColumnCondition(wipProps.getProjectName(Constants.PLAN_WIP),
							projectsCSV.toString());
				}
			}
		}
		/*
		value = planCriteria.getProject();
		if ((value != null) && (!value.equals("None"))) {
			addWhereColumnCondition(wipProps.getProjectName(), value);
		}
		*/
		
		value = planCriteria.getCompanyCode();
		if (!value.equals("")) {
			openParenthesis();
			addWhereColumnCondition(wipProps.getOldCompanyCode(), value);
			setAppendOR(true);
			addWhereColumnCondition(wipProps.getCompanyCode(), value);
			closeParenthesis();
		}
		value = planCriteria.getPlanType();
		if (!value.equals("")) {
			setAppendAND(true);
			openParenthesis();
			addWhereColumnCondition(wipProps.getOldPlanType(), value);
			setAppendOR(true);
			addWhereColumnCondition(wipProps.getPlanType(), value);
			closeParenthesis();
		}
		value = planCriteria.getProductPrefix();
		if (!value.equals("")) {
			setAppendAND(true);
			if (planCriteria.isLoadNP()) {
				setAppendAND(true);
				openParenthesis();
				setAppendOR(true);
				addWhereColumnCondition(wipProps.getProductPrefix(), "N");
				addWhereColumnCondition(wipProps.getProductPrefix(), "H");
				openParenthesis();
			}
			String[] values = {
					planCriteria.getProductPrefix(),
					planCriteria.getProductSuffix(),
					planCriteria.getPlanCode(),
					planCriteria.getIssueState(),
					planCriteria.getLineOfBusiness(),
					planCriteria.getEffectiveDate()
			};
			String[] columnNames = {
					wipProps.getOldProductPrefix(), 
					wipProps.getProductPrefix(),
					wipProps.getOldProductSuffix(), 
					wipProps.getProductSuffix(),
					wipProps.getOldPlanCode(), 
					wipProps.getPlanCode(),
					wipProps.getOldIssueState(), 
					wipProps.getIssueState(),
					wipProps.getOldLOB(), 
					wipProps.getLOB(),
					wipProps.getOldEffectiveDate(), 
					wipProps.getEffectiveDate()
			};
			for (int i = 0, j = 0; i < values.length; i++) {
				value = values[i];
				if ( (!value.startsWith("*") || !wipProps.getOldProductSuffix().equals(columnNames[j]) )
						&& (!value.equals(""))) {
					setAppendAND(true);
					openParenthesis();
					addWhereColumnCondition(columnNames[j++], value);
					setAppendOR(true);
					addWhereColumnCondition(columnNames[j++], value);
					closeParenthesis();
				}
				else {
					j+=2;
				}
			}
		}
		closeAllParenthesis();
	}

	protected PlanCriteriaTO getPlanCriteria() {
		return planCriteria;
	}

	protected void setPlanCriteria(PlanCriteriaTO planCriteria) {
		this.planCriteria = planCriteria;
	}

	protected WIPTableFilter getWIPTableFilter() {
		return wipTableFilter;
	}

	protected void setWIPTableFilter(WIPTableFilter wipTableFilter) {
		this.wipTableFilter = wipTableFilter;
	}

}
