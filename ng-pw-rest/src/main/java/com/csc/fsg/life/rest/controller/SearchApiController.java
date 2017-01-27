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
import com.csc.fsg.life.rest.model.ApplyFilterData;
import com.csc.fsg.life.rest.model.AuditFilterData;
import com.csc.fsg.life.rest.model.ChangesFilterData;
import com.csc.fsg.life.rest.model.CommonSelectItem;
import com.csc.fsg.life.rest.model.DateSelectItem;
import com.csc.fsg.life.rest.model.PlanSearchInput;
import com.csc.fsg.life.rest.model.PromoteFilterData;
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
	public ResponseEntity<List<CommonSelectItem>> getCommonEnvironments(@RequestHeader(value = "sessionToken", required = true) String sessionToken)
	{
		RestServiceParam param = buildRestServiceParam(sessionToken);
		List<CommonSelectItem> envList = searchService.getCommonEnvironments(param);
		PropertyComparator.sort(envList, new MutableSortDefinition("displayValue", true, true));
		return new ResponseEntity<>(envList, HttpStatus.OK);
	}

	@RequestMapping(value = "/search/plan/company", produces = { "application/json" }, method = RequestMethod.GET)
	public ResponseEntity<List<CommonSelectItem>> getPlanCompanyCodes(@RequestHeader(value = "sessionToken", required = true) String sessionToken,
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

	@RequestMapping(value = "/search/plan/product", produces = { "application/json" }, method = RequestMethod.GET)
	public ResponseEntity<List<CommonSelectItem>> getPlanProductCodes(@RequestHeader(value = "sessionToken", required = true) String sessionToken,
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

	@RequestMapping(value = "/search/plan/plan", produces = { "application/json" }, method = RequestMethod.GET)
	public ResponseEntity<List<CommonSelectItem>> getPlanPlanCodes(@RequestHeader(value = "sessionToken", required = true) String sessionToken,
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

	@RequestMapping(value = "/search/plan/state", produces = { "application/json" }, method = RequestMethod.GET)
	public ResponseEntity<List<CommonSelectItem>> getPlanIssueStates(@RequestHeader(value = "sessionToken", required = true) String sessionToken,
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

	@RequestMapping(value = "/search/plan/lob", produces = { "application/json" }, method = RequestMethod.GET)
	public ResponseEntity<List<CommonSelectItem>> getPlanLobs(@RequestHeader(value = "sessionToken", required = true) String sessionToken,
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

	@RequestMapping(value = "/search/plan/effdate", produces = { "application/json" }, method = RequestMethod.GET)
	public ResponseEntity<List<DateSelectItem>> getPlanEffDates(@RequestHeader(value = "sessionToken", required = true) String sessionToken,
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
		List<DateSelectItem> envList = searchService.getPlanEffDates(param, searchInput);
		PropertyComparator.sort(envList, new MutableSortDefinition("displayValue", true, true));
		return new ResponseEntity<>(envList, HttpStatus.OK);
	}

	@RequestMapping(value = "/search/plan/project", produces = { "application/json" }, method = RequestMethod.GET)
	public ResponseEntity<List<String>> getPlanProjects(@RequestHeader(value = "sessionToken", required = true) String sessionToken,
														@RequestHeader(value = "envId", required = true) String envId)
	{
		RestServiceParam param = buildRestServiceParam(sessionToken);
		List<String> envList = searchService.getPlanProjects(param, envId);
		Collections.sort(envList);
		return new ResponseEntity<>(envList, HttpStatus.OK);
	}

	@RequestMapping(value = "/search/etv/table", produces = { "application/json" }, method = RequestMethod.GET)
	public ResponseEntity<List<CommonSelectItem>> getPlanTables(@RequestHeader(value = "sessionToken", required = true) String sessionToken,
																@RequestHeader(value = "envId", required = true) String envId,
																@RequestHeader(value = "companyCode", required = true) String companyCode)	
	{
		RestServiceParam param = buildRestServiceParam(sessionToken);
		List<CommonSelectItem> envList = searchService.getPlanTables(param, envId, companyCode);
		PropertyComparator.sort(envList, new MutableSortDefinition("displayValue", true, true));
		return new ResponseEntity<>(envList, HttpStatus.OK);
	}

	@RequestMapping(value = "/search/changes/environment", produces = { "application/json" }, method = RequestMethod.GET)
	public ResponseEntity<List<CommonSelectItem>> getChangesEnvironments(@RequestHeader(value = "sessionToken", required = true) String sessionToken)
	{
		RestServiceParam param = buildRestServiceParam(sessionToken);
		List<CommonSelectItem> envList = searchService.getChangesEnvironments(param);
		PropertyComparator.sort(envList, new MutableSortDefinition("displayValue", true, true));
		return new ResponseEntity<>(envList, HttpStatus.OK);
	}

	@RequestMapping(value = "/search/changes/filter", produces = { "application/json" }, method = RequestMethod.GET)
	public ResponseEntity<ChangesFilterData> getChangesFilterValues(@RequestHeader(value = "sessionToken", required = true) String sessionToken,
																	@RequestHeader(value = "envId", required = true) String envId)
	{
		RestServiceParam param = buildRestServiceParam(sessionToken);
		ChangesFilterData filterData = searchService.getChangesFilterValues(param, envId);
		Collections.sort(filterData.getProjects());
		Collections.sort(filterData.getUsers());
		PropertyComparator.sort(filterData.getBusinessRuleTables(), new MutableSortDefinition("displayValue", true, true));
		return new ResponseEntity<>(filterData, HttpStatus.OK);
	}

	@RequestMapping(value = "/search/apply/filter", produces = { "application/json" }, method = RequestMethod.GET)
	public ResponseEntity<ApplyFilterData> getApplyFilterValues(@RequestHeader(value = "sessionToken", required = true) String sessionToken,
																@RequestHeader(value = "envId", required = true) String envId)
	{
		RestServiceParam param = buildRestServiceParam(sessionToken);
		ApplyFilterData filterData = searchService.getApplyFilterValues(param, envId);
		Collections.sort(filterData.getPackages());
		Collections.sort(filterData.getProjects());
		return new ResponseEntity<>(filterData, HttpStatus.OK);
	}

	@RequestMapping(value = "/search/audit/filter", produces = { "application/json" }, method = RequestMethod.GET)
	public ResponseEntity<AuditFilterData> getAuditFilterValues(@RequestHeader(value = "sessionToken", required = true) String sessionToken,
																@RequestHeader(value = "filterAspect", required = true) String filterAspect,
																@RequestHeader(value = "envId", required = true) String envId)
	{
		RestServiceParam param = buildRestServiceParam(sessionToken);
		AuditFilterData filterData = searchService.getAuditFilterValues(param, filterAspect, envId);
		Collections.sort(filterData.getPackages());
		Collections.sort(filterData.getProjects());
		PropertyComparator.sort(filterData.getBusinessRuleTables(), new MutableSortDefinition("displayValue", true, true));
		Collections.sort(filterData.getUsers());
		return new ResponseEntity<>(filterData, HttpStatus.OK);
	}

	@RequestMapping(value = "/search/promote/filter", produces = { "application/json" }, method = RequestMethod.GET)
	public ResponseEntity<PromoteFilterData> getPromoteFilterValues(@RequestHeader(value = "sessionToken", required = true) String sessionToken,
																	@RequestHeader(value = "sourceEnvId", required = true) String sourceEnvId,
																	@RequestHeader(value = "targetEnvId", required = true) String targetEnvId)
	{
		RestServiceParam param = buildRestServiceParam(sessionToken);
		PromoteFilterData filterData = searchService.getPromoteFilterValues(param, sourceEnvId, targetEnvId);
		Collections.sort(filterData.getPackages());
		Collections.sort(filterData.getProjects());
		return new ResponseEntity<>(filterData, HttpStatus.OK);
	}
}
