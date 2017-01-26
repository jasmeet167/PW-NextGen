package com.csc.fsg.life.rest.controller;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.support.MutableSortDefinition;
import org.springframework.beans.support.PropertyComparator;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.csc.fsg.life.rest.api.SearchApi;
import com.csc.fsg.life.rest.model.ChangesOnlyFilterData;
import com.csc.fsg.life.rest.model.CommonSelectItem;
import com.csc.fsg.life.rest.model.DateSelectItem;
import com.csc.fsg.life.rest.model.PlanSearchInput;
import com.csc.fsg.life.rest.param.RestServiceParam;
import com.csc.fsg.life.rest.service.SearchService;

@Controller
public class SearchApiController
	extends RestApiController
	implements SearchApi
{
	@Autowired
	SearchService searchService = null;

	@RequestMapping(value = "/search/common/environment", produces = { "application/json" }, method = RequestMethod.GET)
	public ResponseEntity<List<CommonSelectItem>> getEnvironments(@RequestHeader(value = "sessionToken", required = true) String sessionToken)
	{
		RestServiceParam param = buildRestServiceParam(sessionToken);
		List<CommonSelectItem> envList = searchService.getEnvironments(param);
		PropertyComparator.sort(envList, new MutableSortDefinition("displayValue", true, true));
		return new ResponseEntity<>(envList, HttpStatus.OK);
	}

	@RequestMapping(value = "/search/rules/company", produces = { "application/json" }, method = RequestMethod.GET)
	public ResponseEntity<List<CommonSelectItem>> getCompanyCodes(@RequestHeader(value = "sessionToken", required = true) String sessionToken,
																  @RequestHeader(value = "viewChanges", required = true) Boolean viewChanges,
																  @RequestHeader(value = "envId", required = true) String envId)
	{
		PlanSearchInput searchInput = new PlanSearchInput();
		searchInput.setViewChangesEffective(viewChanges);
		searchInput.setEnvId(envId);

		RestServiceParam param = buildRestServiceParam(sessionToken);
		List<CommonSelectItem> envList = searchService.getPlanCommonValues(param, searchInput);
		PropertyComparator.sort(envList, new MutableSortDefinition("displayValue", true, true));
		return new ResponseEntity<>(envList, HttpStatus.OK);
	}

	@RequestMapping(value = "/search/rules/product", produces = { "application/json" }, method = RequestMethod.GET)
	public ResponseEntity<List<CommonSelectItem>> getProductCodes(@RequestHeader(value = "sessionToken", required = true) String sessionToken,
																  @RequestHeader(value = "viewChanges", required = true) Boolean viewChanges,
																  @RequestHeader(value = "envId", required = true) String envId,
																  @RequestHeader(value = "companyCode", required = true) String companyCode)
	{
		PlanSearchInput searchInput = new PlanSearchInput();
		searchInput.setViewChangesEffective(viewChanges);
		searchInput.setEnvId(envId);
		searchInput.setCompanyCode(companyCode);

		RestServiceParam param = buildRestServiceParam(sessionToken);
		List<CommonSelectItem> envList = searchService.getPlanCommonValues(param, searchInput);
		PropertyComparator.sort(envList, new MutableSortDefinition("displayValue", true, true));
		return new ResponseEntity<>(envList, HttpStatus.OK);
	}

	@RequestMapping(value = "/search/rules/plan", produces = { "application/json" }, method = RequestMethod.GET)
	public ResponseEntity<List<CommonSelectItem>> getPlanCodes(@RequestHeader(value = "sessionToken", required = true) String sessionToken,
															   @RequestHeader(value = "viewChanges", required = true) Boolean viewChanges,
															   @RequestHeader(value = "envId", required = true) String envId,
															   @RequestHeader(value = "companyCode", required = true) String companyCode,
															   @RequestHeader(value = "productCode", required = true) String productCode)
	{
		PlanSearchInput searchInput = new PlanSearchInput();
		searchInput.setViewChangesEffective(viewChanges);
		searchInput.setEnvId(envId);
		searchInput.setCompanyCode(companyCode);
		searchInput.setProductCode(productCode);

		RestServiceParam param = buildRestServiceParam(sessionToken);
		List<CommonSelectItem> envList = searchService.getPlanCommonValues(param, searchInput);
		PropertyComparator.sort(envList, new MutableSortDefinition("displayValue", true, true));
		return new ResponseEntity<>(envList, HttpStatus.OK);
	}

	@RequestMapping(value = "/search/rules/state", produces = { "application/json" }, method = RequestMethod.GET)
	public ResponseEntity<List<CommonSelectItem>> getIssueStates(@RequestHeader(value = "sessionToken", required = true) String sessionToken,
																 @RequestHeader(value = "viewChanges", required = true) Boolean viewChanges,
																 @RequestHeader(value = "envId", required = true) String envId,
																 @RequestHeader(value = "companyCode", required = true) String companyCode,
																 @RequestHeader(value = "productCode", required = true) String productCode,
																 @RequestHeader(value = "planCode", required = true) String planCode)
	{
		PlanSearchInput searchInput = new PlanSearchInput();
		searchInput.setViewChangesEffective(viewChanges);
		searchInput.setEnvId(envId);
		searchInput.setCompanyCode(companyCode);
		searchInput.setProductCode(productCode);
		searchInput.setPlanCode(planCode);

		RestServiceParam param = buildRestServiceParam(sessionToken);
		List<CommonSelectItem> envList = searchService.getPlanCommonValues(param, searchInput);
		PropertyComparator.sort(envList, new MutableSortDefinition("displayValue", true, true));
		return new ResponseEntity<>(envList, HttpStatus.OK);
	}

	@RequestMapping(value = "/search/rules/lob", produces = { "application/json" }, method = RequestMethod.GET)
	public ResponseEntity<List<CommonSelectItem>> getLobs(@RequestHeader(value = "sessionToken", required = true) String sessionToken,
														  @RequestHeader(value = "viewChanges", required = true) Boolean viewChanges,
														  @RequestHeader(value = "envId", required = true) String envId,
														  @RequestHeader(value = "companyCode", required = true) String companyCode,
														  @RequestHeader(value = "productCode", required = true) String productCode,
														  @RequestHeader(value = "planCode", required = true) String planCode,
														  @RequestHeader(value = "issueState", required = true) String issueState)
	{
		PlanSearchInput searchInput = new PlanSearchInput();
		searchInput.setViewChangesEffective(viewChanges);
		searchInput.setEnvId(envId);
		searchInput.setCompanyCode(companyCode);
		searchInput.setProductCode(productCode);
		searchInput.setPlanCode(planCode);
		searchInput.setIssueState(issueState);

		RestServiceParam param = buildRestServiceParam(sessionToken);
		List<CommonSelectItem> envList = searchService.getPlanCommonValues(param, searchInput);
		PropertyComparator.sort(envList, new MutableSortDefinition("displayValue", true, true));
		return new ResponseEntity<>(envList, HttpStatus.OK);
	}

	@RequestMapping(value = "/search/rules/effdate", produces = { "application/json" }, method = RequestMethod.GET)
	public ResponseEntity<List<DateSelectItem>> getRulesEffDates(@RequestHeader(value = "sessionToken", required = true) String sessionToken,
																 @RequestHeader(value = "viewChanges", required = true) Boolean viewChanges,
																 @RequestHeader(value = "envId", required = true) String envId,
																 @RequestHeader(value = "companyCode", required = true) String companyCode,
																 @RequestHeader(value = "productCode", required = true) String productCode,
																 @RequestHeader(value = "planCode", required = true) String planCode,
																 @RequestHeader(value = "issueState", required = true) String issueState,
																 @RequestHeader(value = "lob", required = true) String lob)
	{
		PlanSearchInput searchInput = new PlanSearchInput();
		searchInput.setViewChangesEffective(viewChanges);
		searchInput.setEnvId(envId);
		searchInput.setCompanyCode(companyCode);
		searchInput.setProductCode(productCode);
		searchInput.setPlanCode(planCode);
		searchInput.setIssueState(issueState);
		searchInput.setLob(lob);

		RestServiceParam param = buildRestServiceParam(sessionToken);
		List<DateSelectItem> envList = searchService.getPlanEffectiveDates(param, searchInput);
		PropertyComparator.sort(envList, new MutableSortDefinition("displayValue", true, true));
		return new ResponseEntity<>(envList, HttpStatus.OK);
	}

	@RequestMapping(value = "/search/rules/project", produces = { "application/json" }, method = RequestMethod.GET)
	public ResponseEntity<List<String>> getProjects(@RequestHeader(value = "sessionToken", required = true) String sessionToken,
													@RequestHeader(value = "envId", required = true) String envId)
	{
		RestServiceParam param = buildRestServiceParam(sessionToken);
		List<String> envList = searchService.getProjects(param, envId);
		Collections.sort(envList);
		return new ResponseEntity<>(envList, HttpStatus.OK);
	}

	@RequestMapping(value = "/search/etv/table", produces = { "application/json" }, method = RequestMethod.GET)
	public ResponseEntity<List<CommonSelectItem>> getTables(@RequestHeader(value = "sessionToken", required = true) String sessionToken,
															@RequestHeader(value = "envId", required = true) String envId,
															@RequestHeader(value = "companyCode", required = true) String companyCode)	
	{
		RestServiceParam param = buildRestServiceParam(sessionToken);
		List<CommonSelectItem> envList = searchService.getTables(param, envId, companyCode);
		PropertyComparator.sort(envList, new MutableSortDefinition("displayValue", true, true));
		return new ResponseEntity<>(envList, HttpStatus.OK);
	}

	@RequestMapping(value = "/search/changes/environment", produces = { "application/json" }, method = RequestMethod.GET)
	public ResponseEntity<List<CommonSelectItem>> getEnvironmentsWithChanges(@RequestHeader(value = "sessionToken", required = true) String sessionToken)
	{
		RestServiceParam param = buildRestServiceParam(sessionToken);
		List<CommonSelectItem> envList = searchService.getEnvironmentsWithChanges(param);
		PropertyComparator.sort(envList, new MutableSortDefinition("displayValue", true, true));
		return new ResponseEntity<>(envList, HttpStatus.OK);
	}

	@RequestMapping(value = "/search/changes/filter", produces = { "application/json" }, method = RequestMethod.GET)
	public ResponseEntity<ChangesOnlyFilterData> getChangesOnlyFilterValues(@RequestHeader(value = "sessionToken", required = true) String sessionToken,
																			@RequestHeader(value = "envId", required = true) String envId)
	{
		RestServiceParam param = buildRestServiceParam(sessionToken);
		ChangesOnlyFilterData filterData = searchService.getChangesOnlyFilterValues(param, envId);
		Collections.sort(filterData.getProjects());
		Collections.sort(filterData.getUsers());
		PropertyComparator.sort(filterData.getBusinessRuleTables(), new MutableSortDefinition("displayValue", true, true));
		return new ResponseEntity<>(filterData, HttpStatus.OK);
	}
}
