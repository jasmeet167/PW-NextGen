package com.csc.fsg.life.rest.service;

import java.util.List;

import com.csc.fsg.life.rest.model.BusinessRule;
import com.csc.fsg.life.rest.model.TableModel;
import com.csc.fsg.life.rest.param.RestServiceParam;

public interface BusinessRuleService
{
	public List<BusinessRule> getBusinessRules(RestServiceParam param, String env, String company);

	public TableModel getBusinessRuleModel(RestServiceParam param, String rule, String env, String company);
}
