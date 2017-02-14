package com.csc.fsg.life.pw.web.utils.sql;

import com.csc.fsg.life.pw.common.transferobjects.PlanTO;
import com.csc.fsg.life.pw.web.io.descriptor.wma.PLAN_NAMEDescriptor;

/* Modifications: T0103,V-E932 */
// T0103 - new class for refactor plan key

public class SQLBuilderPLAN_NAME extends SQLBuilder {
	
	private PlanTO plan;
	
	public SQLBuilderPLAN_NAME(PlanTO plan) {
		super(plan.getEnvironment(), APPL, new PLAN_NAMEDescriptor());
		setPlan(plan);
	}

	public String buildSelectPlanNameStatement() {
		addSelectColumnClause("PLAN_NAME");
		addFromClause();
		// V-E932 - don't ignore high values when selecting plan name
		setIgnoreHighValues(false);
		addWherePlanKeysClause(plan);
		return getFinalStatement();
	}

	public String buildInsertStatement() {
		addInsertIntoClause();
		addInsertColumns();
		addInsertValues(plan);
		return getFinalStatement();
	}
	
	public String buildUpdatePlanNameStatement() {
		addUpdateClause();
		addSetPlanNameClause();
		PlanTO plan = new PlanTO(this.plan);
		plan.setPlanName(null);
		addWherePlanKeysClause(plan);
		return getFinalStatement();
	}

	public String buildDeleteStatement() {
		addDeleteClause();
		addWherePlanKeysClause(plan);
		return getFinalStatement();
	}

	protected void addSetPlanNameClause() {
		startUpdateColumns();
		addSetColumn(PlanTO.PLAN_NAME_NAME, plan.getPlanName());
	}
	
	protected PlanTO getPlan() {
		return plan;
	}

	protected void setPlan(PlanTO plan) {
		this.plan = plan;
	}
}
