package com.csc.fsg.life.rest.controller;

import java.sql.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.csc.fsg.life.rest.api.BusinessRulesTreeApi;
import com.csc.fsg.life.rest.model.BusinessRulesTreeSearchInput;
import com.csc.fsg.life.rest.model.TreeNode;
import com.csc.fsg.life.rest.model.TreeNodePlanKey;
import com.csc.fsg.life.rest.param.RestServiceParam;
import com.csc.fsg.life.rest.service.BusinessRulesTreeService;

@Controller
public class BusinessRulesTreeApiController
	extends RestApiController
	implements BusinessRulesTreeApi
{
	@Autowired
	private BusinessRulesTreeService businessRuleTreeService = null;

	@RequestMapping(value = "/business-rules-tree/core", produces = { "application/json" }, method = RequestMethod.GET)
	public ResponseEntity<List<TreeNode>> getBusinessRulesTreeCore(@RequestHeader(value = "authToken", required = true) String authToken,
																   @RequestHeader(value = "envId", required = true) String envId,
																   @RequestHeader(value = "companyCode", required = true) String companyCode,
																   @RequestHeader(value = "productCode", required = true) String productCode,
																   @RequestHeader(value = "includeOrphans", required = false) Boolean includeOrphans)	
	{
		RestServiceParam param = buildRestServiceParam(authToken, envId, companyCode);
		List<TreeNode> treeCore = businessRuleTreeService.getBusinessRulesTreeCore(param, productCode, Boolean.TRUE.equals(includeOrphans));
		return new ResponseEntity<>(treeCore, HttpStatus.OK);
	}

	@RequestMapping(value = "/business-rules-tree/common/tables", produces = { "application/json" }, method = RequestMethod.GET)
	public ResponseEntity<List<TreeNode>> getBusinessRulesTreeCommonTables(@RequestHeader(value = "authToken", required = true) String authToken,
																		   @RequestHeader(value = "envId", required = true) String envId,
																		   @RequestHeader(value = "companyCode", required = true) String companyCode,
																		   @RequestHeader(value = "viewChanges", required = false) Boolean viewChanges)
	{
		RestServiceParam param = buildRestServiceParam(authToken, envId, companyCode);
		boolean includeChanges = Boolean.TRUE.equals(viewChanges);
		List<TreeNode> commonTables = businessRuleTreeService.getBusinessRulesTreeCommonTables(param, includeChanges);
		return new ResponseEntity<>(commonTables, HttpStatus.OK);
	}

	@RequestMapping(value = "/business-rules-tree/plan/list", produces = { "application/json" }, method = RequestMethod.GET)
	public ResponseEntity<List<TreeNode>> getBusinessRulesTreePlanList(@RequestHeader(value = "authToken", required = true) String authToken,
																	   @RequestHeader(value = "lazyType", required = true) String lazyType,
																	   @RequestHeader(value = "envId", required = true) String envId,
																	   @RequestHeader(value = "companyCode", required = true) String companyCode,
																	   @RequestHeader(value = "productCode", required = true) String productCode,
																	   @RequestHeader(value = "planCode", required = false) String planCode,
																	   @RequestHeader(value = "issueState", required = false) String issueState,
																	   @RequestHeader(value = "lob", required = false) String lob,
																	   @RequestHeader(value = "effDate", required = false) String effDate,
																	   @RequestHeader(value = "viewChanges", required = false) Boolean viewChanges,
																	   @RequestHeader(value = "includeOrphans", required = false) Boolean includeOrphans)
	{
		RestServiceParam param = buildRestServiceParam(authToken, envId, companyCode);
		BusinessRulesTreeSearchInput searchInput = new BusinessRulesTreeSearchInput();

		searchInput.setLazyType(lazyType);
		searchInput.setProductCode(productCode);
		searchInput.setPlanCode(planCode);
		searchInput.setIssueState(issueState);
		searchInput.setLob(lob);
		if (effDate != null)
			searchInput.setEffDate(Date.valueOf(effDate));

		searchInput.setViewChanges(Boolean.TRUE.equals(viewChanges));
		searchInput.setIncludeOrphans(Boolean.TRUE.equals(includeOrphans));

		List<TreeNode> planList = businessRuleTreeService.getBusinessRulesTreePlanList(param, searchInput);
		return new ResponseEntity<>(planList, HttpStatus.OK);
	}

	@RequestMapping(value = "/business-rules-tree/plan/detail", produces = { "application/json" }, consumes = { "application/json" }, method = RequestMethod.POST)
	public ResponseEntity<List<TreeNode>> getBusinessRulesTreePlanDetails(@RequestHeader(value = "authToken", required = true) String authToken,
																		  @RequestHeader(value = "envId", required = true) String envId,
																		  @RequestHeader(value = "companyCode", required = true) String companyCode,
																		  @RequestBody TreeNodePlanKey planKey,
																		  @RequestHeader(value = "viewChanges", required = false) Boolean viewChanges)
	{
		RestServiceParam param = buildRestServiceParam(authToken, envId, companyCode);
		boolean includeChanges = Boolean.TRUE.equals(viewChanges);
		List<TreeNode> planDetails = businessRuleTreeService.getBusinessRulesTreePlanDetails(param, planKey, includeChanges);
		return new ResponseEntity<>(planDetails, HttpStatus.OK);
	}

	@RequestMapping(value = "/business-rules-tree/orphans", produces = { "application/json" }, method = RequestMethod.GET)
	public ResponseEntity<List<TreeNode>> getBusinessRulesTreeOrphanSubsets(@RequestHeader(value = "authToken", required = true) String authToken,
																			@RequestHeader(value = "envId", required = true) String envId,
																			@RequestHeader(value = "companyCode", required = true) String companyCode,
																			@RequestHeader(value = "productCode", required = true) String productCode,
																			@RequestHeader(value = "planCode", required = false) String planCode,
																			@RequestHeader(value = "issueState", required = false) String issueState,
																			@RequestHeader(value = "lob", required = false) String lob,
																			@RequestHeader(value = "effDate", required = false) String effDate,
																			@RequestHeader(value = "viewChanges", required = false) Boolean viewChanges)
	{
		RestServiceParam param = buildRestServiceParam(authToken, envId, companyCode);
		BusinessRulesTreeSearchInput searchInput = new BusinessRulesTreeSearchInput();

		searchInput.setProductCode(productCode);
		searchInput.setPlanCode(planCode);
		searchInput.setIssueState(issueState);
		searchInput.setLob(lob);
		searchInput.setViewChanges(Boolean.TRUE.equals(viewChanges));
		if (effDate != null)
			searchInput.setEffDate(Date.valueOf(effDate));

		List<TreeNode> orphans = businessRuleTreeService.getBusinessRulesTreeOrphanSubsets(param, searchInput);
		return new ResponseEntity<>(orphans, HttpStatus.OK);
	}
}
