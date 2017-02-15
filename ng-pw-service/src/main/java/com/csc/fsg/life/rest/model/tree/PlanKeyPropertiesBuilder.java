package com.csc.fsg.life.rest.model.tree;

import java.sql.Date;

import org.joda.time.LocalDate;

import com.csc.fsg.life.rest.model.TreeNodePlanKey;

public class PlanKeyPropertiesBuilder
{
	static public void buildValues(TreeNodePlanKey planKey, String[] streamComponents)
	{
		int i = 0;
		if (i < streamComponents.length)
			planKey.setCompanyCode(streamComponents[i++]);
		if (i < streamComponents.length)
			planKey.setProductPrefix(streamComponents[i++]);
		if (i < streamComponents.length)
			planKey.setProductSuffix(streamComponents[i++]);
		if (i < streamComponents.length)
			planKey.setPlanCode(streamComponents[i++]);
		if (i < streamComponents.length)
			planKey.setIssueState(streamComponents[i++]);
		if (i < streamComponents.length)
			planKey.setLob(streamComponents[i++]);
		if (i < streamComponents.length) {
			Date date = Date.valueOf(streamComponents[i++]);
			planKey.setEffDate(new LocalDate(date.getTime()));
		}
		if (i < streamComponents.length)
			planKey.setPlanType(streamComponents[i++]);
		if (i < streamComponents.length)
			planKey.setTablePtrId(streamComponents[i++]);
		if (i < streamComponents.length)
			planKey.setTablePtrVar(streamComponents[i++]);
		if (i < streamComponents.length)
			planKey.setTablePtrSubset(streamComponents[i++]);
	}
}
