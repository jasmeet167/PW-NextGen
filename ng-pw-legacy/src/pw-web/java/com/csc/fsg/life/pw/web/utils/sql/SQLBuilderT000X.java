package com.csc.fsg.life.pw.web.utils.sql;

import java.lang.reflect.Method;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.Vector;

import com.csc.fsg.life.exceptions.WrapperException;
import com.csc.fsg.life.pw.common.transferobjects.PlanCriteriaTO;
import com.csc.fsg.life.pw.common.transferobjects.PlanRowTO;
import com.csc.fsg.life.pw.common.util.Constants;
import com.csc.fsg.life.pw.common.util.InstallConfigBean;
import com.csc.fsg.life.pw.web.config.ProductManager;
import com.csc.fsg.life.pw.web.io.ColumnDescriptor;
import com.csc.fsg.life.pw.web.io.TableDescriptor;
import com.csc.fsg.life.pw.web.utils.HighValueHandler;

/* Modifications: T0103, T0091 */
// T0103 - new class for refactor plan key

public class SQLBuilderT000X extends SQLBuilder {

	private PlanCriteriaTO planCriteria;
	
	public SQLBuilderT000X(String envId, int schemaType) {
		super(envId, schemaType, Constants.TABLE_ZERO_ID);
	}

	public SQLBuilderT000X(PlanCriteriaTO planCriteria) {
		this(planCriteria.getEnvironment(), APPL);
		setPlanCriteria(planCriteria);
	}

	public String buildSelectForFilterStatement() {
		addSelectForFilterClause();
		addFromClause();
		addWherePlanRowKeysClause(getPlanCriteria());
		return getFinalStatement();
	}

	public String buildSelectForMergeStatement() {
		addSelectColumnDescriptorsClause();
		addFromClause();
		setIgnoreHighValues(false);
		addWhereForMergeClause();
		return getFinalStatement();
	}
	
	public String buildSelectAllStatement() {
		addSelectAllClause();
		addFromClause();
		setIgnoreHighValues(false);
		addWherePlanRowKeysClause(getPlanCriteria());
		return getFinalStatement();
	}

	public String buildCountAllStatement() {
		setSelectCount(true);
		return buildSelectAllStatement();
	}

	public String buildSelectColumnStatement(String columnName) {
		setSelectDistinct(true);
		addSelectColumnClause(columnName);
		addFromClause();
		addWherePlanRowKeysClause(getPlanCriteria());
		return getFinalStatement();
	}

	public String buildSelectForHighValueStatement() {
		PlanCriteriaTO planCriteria = new PlanCriteriaTO();
		planCriteria.setCompanyCode("ZZZ");
		planCriteria.setProductCode("ZZ");
		setPlanCriteria(planCriteria);
		
		addSelectColumnClause(PlanCriteriaTO.LINE_OF_BUSINESS_NAME);
		addFromClause();
		addWherePlanRowKeysClause(getPlanCriteria());
		return getFinalStatement();
	}

	public String buildSelectForSynchronizeStatement() {
		addSelectAllClause();
		addFromClause();
		addWhereForSynchronizeClause();
		return getFinalStatement();
	}

	public String buildSelectForAllowablePlansStatement() {
		setSelectDistinct(true);
		addSelectColumnClause("PLAN_CODE");
		addFromClause();
		addWherePlanRowKeysClause(getPlanCriteria());
		return getFinalStatement();
	}

	public String buildInsertForHighValueStatement() {
		PlanCriteriaTO planCriteria = new PlanCriteriaTO();
		planCriteria.setCompanyCode("ZZZ");
		planCriteria.setProductCode("ZZ");
		planCriteria.setPlanCode("ZZZZZZ");
		planCriteria.setIssueState("ZZZ");
		planCriteria.setEffectiveDate("1999-12-31");
		planCriteria.setLineOfBusiness("?");
		planCriteria.setPlanType("Z");
		planCriteria.setTablePtrId("ZZZ");
		planCriteria.setTablePtrVar("Z");
		planCriteria.setTablePtrSubset("ZZZ");
		setPlanCriteria(planCriteria);
		
		addInsertIntoClause();
		addInsertColumns();
		addInsertValues(getPlanCriteria());
		return getFinalStatement();
	}

	public String buildDeleteForHighValueStatement() {
		PlanCriteriaTO planCriteria = new PlanCriteriaTO();
		planCriteria.setCompanyCode("ZZZ");
		planCriteria.setProductCode("ZZ");
		setPlanCriteria(planCriteria);
		
		addDeleteClause();
		addWherePlanRowKeysClause(getPlanCriteria());
		return getFinalStatement();
	}

	public String buildSelectForExistingPlanCodesStatement() {
		setSelectDistinct(true);
		addSelectColumnClause("PLAN_CODE");
		addFromClause();
		addWherePlanRowKeysClause(getPlanCriteria());
		return getFinalStatement();
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

	protected void addWhereForMergeClause() {
		PlanCriteriaTO planCriteria = getPlanCriteria();

		startWhereClause();
		TableDescriptor td = getTableDescriptor();
		Vector<ColumnDescriptor> cds = new Vector<ColumnDescriptor>();
		ColumnDescriptor cd;

		String value;
		addWhereColumnCondition(PlanCriteriaTO.ENVIRONMENT_NAME, planCriteria.getEnvironment());
		addWhereColumnCondition(PlanCriteriaTO.COMPANY_CODE_NAME, planCriteria.getCompanyCode());
		value = planCriteria.getProductPrefix();
		if (!value.equals("")) {
			if (planCriteria.isLoadNP()) {
				openParenthesis();
				setAppendOR(true);
				addWhereColumnCondition(PlanCriteriaTO.PRODUCT_PREFIX_NAME, "N");
				addWhereColumnCondition(PlanCriteriaTO.PRODUCT_PREFIX_NAME, "H");
				openParenthesis();
			}
			setAppendAND(true);
			addWhereColumnCondition(PlanCriteriaTO.PRODUCT_PREFIX_NAME, planCriteria.getProductPrefix());

			String[] columnNames = {
					PlanCriteriaTO.PRODUCT_SUFFIX_NAME, 
					PlanCriteriaTO.PLAN_CODE_NAME,
					PlanCriteriaTO.ISSUE_STATE_NAME, 
					PlanCriteriaTO.LINE_OF_BUSINESS_NAME,
					PlanCriteriaTO.EFFECTIVE_DATE_NAME, 
					PlanCriteriaTO.PLAN_TYPE_NAME,
					PlanCriteriaTO.TABLE_PTR_ID_NAME
			};
			for (String columnName : columnNames) {
				cd = td.getColumnDescriptor(columnName);
				if (cd != null) 
					cds.add(cd);
			}

			Class<? extends PlanCriteriaTO> planClass = planCriteria.getClass();
			Method[] methods = planClass.getMethods();
			Method method;
			String methodName;
			boolean methodExists;
			Object[] args = new Object[0];
			Class[] argTypes = new Class[0];
			try {
				for (int i = 0; i < cds.size(); i++) {
					cd = cds.get(i);
					methodName = cd.getGetterMethod().getName();
					methodExists = false;
					for (Method method2 : methods) {
						if (method2.getName().equals(methodName)) {
							methodExists = true;
							break;
						}
					}
					if (methodExists == false)
						break;
				    method = planClass.getMethod(methodName, argTypes);
					value = (String) method.invoke(planCriteria, args);
					addWhereColumnCondition(cd.getDbColumnName(), value);
					}
			} 
			catch (Exception e) {
				throw WrapperException.wrap(e);
			}
		}
		closeAllParenthesis();
	}

	protected void addWhereForSynchronizeClause() {
		startWhereClause();
		addWhereColumnCondition(PlanCriteriaTO.COMPANY_CODE_NAME, getPlanCriteria().getCompanyCode());
		
		InstallConfigBean installBean = ProductManager.getInstance().getProduct(getEnvironment().getProductSystem()).getInstallBean();
		ArrayList<String> products = installBean.getInstalledProducts();
		if ((!isAppendWHERE()) && (products.size() > 1)) {
			openParenthesis();			
		}
		for (int i = 0; i < products.size(); i++) {
			addWhereColumnCondition(PlanCriteriaTO.PRODUCT_PREFIX_NAME,products.get(i));
			setAppendOR(true);
		}
		setAppendAND(true);
		closeAllParenthesis();
	}

	public String prepareInsertForReloadStatement() {
		addInsertIntoClause();
		addInsertForReloadColumns();
		addInsertValues();
		return getFinalStatement();
	}

	protected void addInsertForReloadColumns() {
		Vector<ColumnDescriptor> cds = getTableDescriptor().getColumnDescriptors();
		String[] columnNames = new String[cds.size()+1];
		ColumnDescriptor cd;
		columnNames[0] = PlanCriteriaTO.ENVIRONMENT_NAME;
		for (int i = 0; i < cds.size(); i++) {
			cd = cds.get(i);
			columnNames[i+1] = cd.getDbColumnName();
		}
		addInsertColumns(columnNames);
	}

	public static void setInsertValues(PreparedStatement stmt, PlanRowTO planRow) throws java.sql.SQLException {
		String environment = planRow.getEnvironment();
		stmt.setString(1, environment);
		stmt.setString(2, planRow.getCompanyCode());
		stmt.setString(3, HighValueHandler.convertHVToAsterisk(planRow.getProductPrefix(), environment));
		stmt.setString(4, HighValueHandler.convertHVToAsterisk(planRow.getProductSuffix(), environment));
		stmt.setString(5, planRow.getPlanCode());
		stmt.setString(6, HighValueHandler.convertHVToAsterisk(planRow.getIssueState(), environment));
		stmt.setString(7, HighValueHandler.convertHVToAsterisk(planRow.getLineOfBusiness(), environment));
		stmt.setString(8, planRow.getEffectiveDate());
		stmt.setString(9, planRow.getPlanType());
		stmt.setString(10, planRow.getTablePtrId());
		stmt.setString(11, planRow.getTablePtrVar());
		stmt.setString(12, planRow.getTablePtrSubset());
	}

	protected PlanCriteriaTO getPlanCriteria() {
		return planCriteria;
	}

	protected void setPlanCriteria(PlanCriteriaTO planCriteria) {
		this.planCriteria = planCriteria;
	}
	
}

