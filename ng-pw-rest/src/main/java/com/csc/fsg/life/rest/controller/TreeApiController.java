package com.csc.fsg.life.rest.controller;

import java.sql.Date;
import java.util.List;

import org.joda.time.LocalDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.csc.fsg.life.rest.api.TreeApi;
import com.csc.fsg.life.rest.model.BusinessRuleTreeSearchInput;
import com.csc.fsg.life.rest.model.TreeNode;
import com.csc.fsg.life.rest.param.RestServiceParam;
import com.csc.fsg.life.rest.service.TreeService;

@Controller
public class TreeApiController
	extends RestApiController
	implements TreeApi
{
	@Autowired
	private TreeService treeService = null;

	@CrossOrigin
	@RequestMapping(value = "/tree/search", produces = { "application/json" }, method = RequestMethod.GET)
	public ResponseEntity<List<TreeNode>> getBusinessRulesTree(@RequestHeader(value = "authToken", required = true) String authToken,
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
		RestServiceParam param = buildRestServiceParam(authToken, envId, companyCode);

		BusinessRuleTreeSearchInput input = new BusinessRuleTreeSearchInput();
		input.setAreChangesIncluded(viewChanges.booleanValue());
		input.setProductCode(productCode);
		input.setPlanCode(planCode);
		input.setIssueState(issueState);
		input.setLob(lob);
		if (effDate != null)
			input.setEffDate(Date.valueOf(effDate.toString()));

		input.setOrphansIncluded(Boolean.TRUE.equals(includeOrphans));

		List<TreeNode> branches = treeService.getBusinessRulesTree(param, input);
		return new ResponseEntity<>(branches, HttpStatus.OK);
	}
}
