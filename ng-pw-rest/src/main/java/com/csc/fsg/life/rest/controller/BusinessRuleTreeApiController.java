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

import com.csc.fsg.life.rest.api.BusinessRuleTreeApi;
import com.csc.fsg.life.rest.model.BusinessRuleTreeSearchInput;
import com.csc.fsg.life.rest.model.TreeNode;
import com.csc.fsg.life.rest.model.TreeNodePlanKey;
import com.csc.fsg.life.rest.param.RestServiceParam;
import com.csc.fsg.life.rest.service.BusinessRuleTreeService;

@Controller
public class BusinessRuleTreeApiController
	extends RestApiController
	implements BusinessRuleTreeApi
{
	@Autowired
	private BusinessRuleTreeService businessRuleTreeService = null;

	@RequestMapping(value = "/business-rule-tree/core", produces = { "application/json" }, method = RequestMethod.GET)
	public ResponseEntity<List<TreeNode>> getBusinessRuleTreeCore(@RequestHeader(value = "authToken", required = true) String authToken,
																  @RequestHeader(value = "envId", required = true) String envId,
																  @RequestHeader(value = "companyCode", required = true) String companyCode,
																  @RequestHeader(value = "productCode", required = true) String productCode,
																  @RequestHeader(value = "includeOrphans", required = false) Boolean includeOrphans)	
	{
		RestServiceParam param = buildRestServiceParam(authToken, envId, companyCode);
		List<TreeNode> treeCore = businessRuleTreeService.getBusinessRuleTreeCore(param, productCode, Boolean.TRUE.equals(includeOrphans));
		return new ResponseEntity<>(treeCore, HttpStatus.OK);
	}

	@RequestMapping(value = "/business-rule-tree/common/tables", produces = { "application/json" }, method = RequestMethod.GET)
	public ResponseEntity<List<TreeNode>> getBusinessRuleTreeCommonTables(@RequestHeader(value = "authToken", required = true) String authToken,
																		  @RequestHeader(value = "envId", required = true) String envId,
																		  @RequestHeader(value = "companyCode", required = true) String companyCode,
																		  @RequestHeader(value = "viewChanges", required = false) Boolean viewChanges)
	{
		RestServiceParam param = buildRestServiceParam(authToken, envId, companyCode);
		boolean includeChanges = Boolean.TRUE.equals(viewChanges);
		List<TreeNode> commonTables = businessRuleTreeService.getBusinessRuleTreeCommonTables(param, includeChanges);
		return new ResponseEntity<>(commonTables, HttpStatus.OK);
	}

	@RequestMapping(value = "/business-rule-tree/plan/list", produces = { "application/json" }, method = RequestMethod.GET)
	public ResponseEntity<List<TreeNode>> getBusinessRuleTreePlanList(@RequestHeader(value = "authToken", required = true) String authToken,
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
		BusinessRuleTreeSearchInput searchInput = new BusinessRuleTreeSearchInput();

		searchInput.setLazyType(lazyType);
		searchInput.setProductCode(productCode);
		searchInput.setPlanCode(planCode);
		searchInput.setIssueState(issueState);
		searchInput.setLob(lob);
		if (effDate != null)
			searchInput.setEffDate(Date.valueOf(effDate));

		searchInput.setViewChanges(Boolean.TRUE.equals(viewChanges));
		searchInput.setIncludeOrphans(Boolean.TRUE.equals(includeOrphans));

		List<TreeNode> planList = businessRuleTreeService.getBusinessRuleTreePlanList(param, searchInput);
		return new ResponseEntity<>(planList, HttpStatus.OK);
	}

	@RequestMapping(value = "/business-rule-tree/plan/detail", produces = { "application/json" }, consumes = { "application/json" }, method = RequestMethod.POST)
	public ResponseEntity<List<TreeNode>> getBusinessRuleTreePlanDetails(@RequestHeader(value = "authToken", required = true) String authToken,
																		 @RequestHeader(value = "envId", required = true) String envId,
																		 @RequestHeader(value = "companyCode", required = true) String companyCode,
																		 @RequestBody TreeNodePlanKey planKey,
																		 @RequestHeader(value = "viewChanges", required = false) Boolean viewChanges)
	{
		RestServiceParam param = buildRestServiceParam(authToken, envId, companyCode);
		boolean includeChanges = Boolean.TRUE.equals(viewChanges);
		List<TreeNode> planDetails = businessRuleTreeService.getBusinessRuleTreePlanDetails(param, planKey, includeChanges);
		return new ResponseEntity<>(planDetails, HttpStatus.OK);
	}

	@RequestMapping(value = "/business-rule-tree/orphans", produces = { "application/json" }, method = RequestMethod.GET)
	public ResponseEntity<List<TreeNode>> getBusinessRuleTreeOrphanSubsets(@RequestHeader(value = "authToken", required = true) String authToken,
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
		BusinessRuleTreeSearchInput searchInput = new BusinessRuleTreeSearchInput();

		searchInput.setProductCode(productCode);
		searchInput.setPlanCode(planCode);
		searchInput.setIssueState(issueState);
		searchInput.setLob(lob);
		searchInput.setViewChanges(Boolean.TRUE.equals(viewChanges));
		if (effDate != null)
			searchInput.setEffDate(Date.valueOf(effDate));

		List<TreeNode> orphans = businessRuleTreeService.getBusinessRuleTreeOrphanSubsets(param, searchInput);
		return new ResponseEntity<>(orphans, HttpStatus.OK);
	}
}
