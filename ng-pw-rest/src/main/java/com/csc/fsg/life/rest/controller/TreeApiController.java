package com.csc.fsg.life.rest.controller;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import org.joda.time.LocalDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.csc.fsg.life.rest.api.TreeApi;
import com.csc.fsg.life.rest.model.BusinessRuleTreeSearchInput;
import com.csc.fsg.life.rest.model.tree.TreeNode;
import com.csc.fsg.life.rest.param.RestServiceParam;
import com.csc.fsg.life.rest.service.BusinessRulesTreeSearchService;

@Controller
public class TreeApiController
	extends RestApiController
	implements TreeApi
{
	@Autowired
	BusinessRulesTreeSearchService searchService = null;

	@RequestMapping(value = "/tree/search", produces = { "application/json" }, method = RequestMethod.GET)
	public ResponseEntity<List<Object>> getBusinessRulesTree(@RequestHeader(value = "sessionToken", required = true) String sessionToken,
															 @RequestHeader(value = "viewChanges", required = true) Boolean viewChanges,
															 @RequestHeader(value = "envId", required = true) String envId,
															 @RequestHeader(value = "companyCode", required = false) String companyCode,
															 @RequestHeader(value = "productCode", required = false) String productCode,
															 @RequestHeader(value = "planCode", required = false) String planCode,
															 @RequestHeader(value = "issueState", required = false) String issueState,
															 @RequestHeader(value = "lob", required = false) String lob,
															 @RequestHeader(value = "effDate", required = false) LocalDate effDate,
															 @RequestHeader(value = "includeOrphans", required = false) Boolean includeOrphans)
	{
		RestServiceParam param = buildRestServiceParam(sessionToken, envId, companyCode);

		BusinessRuleTreeSearchInput input = new BusinessRuleTreeSearchInput();
		input.setAreChangesIncluded(viewChanges.booleanValue());
		input.setProductCode(productCode);
		input.setPlanCode(planCode);
		input.setIssueState(issueState);
		input.setLob(lob);
		if (effDate != null)
			input.setEffDate(Date.valueOf(effDate.toString()));

		input.setOrphansIncluded(Boolean.TRUE.equals(includeOrphans));

		List<TreeNode> branches = searchService.getBusinessRulesTree(param, input);
		// TODO: +++ Remove conversion to List<Object>, when list of specific type is returned
		List<Object> objectBranches = new ArrayList<>();
		for (TreeNode branch : branches)
			objectBranches.add(branch);

		return new ResponseEntity<>(objectBranches, HttpStatus.OK);
	}
}
