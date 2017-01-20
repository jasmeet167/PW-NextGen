package com.csc.fsg.life.rest.controller;

import java.util.List;

import javax.inject.Inject;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.csc.fsg.life.rest.api.BusinessruleApi;
import com.csc.fsg.life.rest.model.BusinessRule;
import com.csc.fsg.life.rest.model.TableModel;
import com.csc.fsg.life.rest.param.RestServiceParam;
import com.csc.fsg.life.rest.service.BusinessRuleService;

@Controller
public class BusinessruleApiController
	extends RestApiController
	implements BusinessruleApi
{
	@Inject
	private BusinessRuleService businessRuleService = null;

	@RequestMapping(value = "/businessrule", produces = { "application/json" }, method = RequestMethod.GET)
	public ResponseEntity<List<BusinessRule>> businessruleGet(@RequestHeader(value = "sessionToken", required = true) String sessionToken,
															  @RequestParam(value = "env", required = true) String env,
															  @RequestParam(value = "company", required = true) String company)
	{
		RestServiceParam param = buildRestServiceParam(sessionToken);
		List<BusinessRule> rules = businessRuleService.getBusinessRules(param, env, company);
		return new ResponseEntity<>(rules, HttpStatus.OK);
	}

	@RequestMapping(value = "/businessrule/{rule}", produces = { "application/json" }, method = RequestMethod.GET)
	public ResponseEntity<TableModel> businessruleRuleGet(@RequestHeader(value = "sessionToken", required = true) String sessionToken,
														  @PathVariable("rule") String rule,
														  @RequestParam(value = "env", required = true) String env,
														  @RequestParam(value = "company", required = true) String company)
	{
		RestServiceParam param = buildRestServiceParam(sessionToken);
		TableModel model = businessRuleService.getBusinessRuleModel(param, rule, env, company);
		return new ResponseEntity<>(model, HttpStatus.OK);
	}
}
