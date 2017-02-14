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
import com.csc.fsg.life.rest.model.SummarySearchInput;
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
		RestServiceParam param = buildRestServiceParam(sessionToken, envId);
		PlanSearchInput searchInput = new PlanSearchInput();
		searchInput.setChangesIncluded(viewChanges);

		List<CommonSelectItem> companyList = searchService.getPlanCommonValues(param, searchInput);
		PropertyComparator.sort(companyList, new MutableSortDefinition("displayValue", true, true));
		return new ResponseEntity<>(companyList, HttpStatus.OK);
	}

	@RequestMapping(value = "/search/plan/product", produces = { "application/json" }, method = RequestMethod.GET)
	public ResponseEntity<List<CommonSelectItem>> getPlanProductCodes(@RequestHeader(value = "sessionToken", required = true) String sessionToken,
																	  @RequestHeader(value = "viewChanges", required = true) Boolean viewChanges,
																	  @RequestHeader(value = "envId", required = true) String envId,
																	  @RequestHeader(value = "companyCode", required = true) String companyCode)
	{
		RestServiceParam param = buildRestServiceParam(sessionToken, envId, companyCode);
		PlanSearchInput searchInput = new PlanSearchInput();
		searchInput.setChangesIncluded(viewChanges);

		List<CommonSelectItem> productList = searchService.getPlanCommonValues(param, searchInput);
		PropertyComparator.sort(productList, new MutableSortDefinition("displayValue", true, true));
		return new ResponseEntity<>(productList, HttpStatus.OK);
	}

	@RequestMapping(value = "/search/plan/plan", produces = { "application/json" }, method = RequestMethod.GET)
	public ResponseEntity<List<CommonSelectItem>> getPlanPlanCodes(@RequestHeader(value = "sessionToken", required = true) String sessionToken,
																   @RequestHeader(value = "viewChanges", required = true) Boolean viewChanges,
																   @RequestHeader(value = "envId", required = true) String envId,
																   @RequestHeader(value = "companyCode", required = true) String companyCode,
																   @RequestHeader(value = "productCode", required = true) String productCode)
	{
		RestServiceParam param = buildRestServiceParam(sessionToken, envId, companyCode);
		PlanSearchInput searchInput = new PlanSearchInput();
		searchInput.setChangesIncluded(viewChanges);
		searchInput.setProductCode(productCode);

		List<CommonSelectItem> planList = searchService.getPlanCommonValues(param, searchInput);
		PropertyComparator.sort(planList, new MutableSortDefinition("displayValue", true, true));
		return new ResponseEntity<>(planList, HttpStatus.OK);
	}

	@RequestMapping(value = "/search/plan/state", produces = { "application/json" }, method = RequestMethod.GET)
	public ResponseEntity<List<CommonSelectItem>> getPlanIssueStates(@RequestHeader(value = "sessionToken", required = true) String sessionToken,
																	 @RequestHeader(value = "viewChanges", required = true) Boolean viewChanges,
																	 @RequestHeader(value = "envId", required = true) String envId,
																	 @RequestHeader(value = "companyCode", required = true) String companyCode,
																	 @RequestHeader(value = "productCode", required = true) String productCode,
																	 @RequestHeader(value = "planCode", required = true) String planCode)
	{
		RestServiceParam param = buildRestServiceParam(sessionToken, envId, companyCode);
		PlanSearchInput searchInput = new PlanSearchInput();
		searchInput.setChangesIncluded(viewChanges);
		searchInput.setProductCode(productCode);
		searchInput.setPlanCode(planCode);

		List<CommonSelectItem> stateList = searchService.getPlanCommonValues(param, searchInput);
		PropertyComparator.sort(stateList, new MutableSortDefinition("displayValue", true, true));
		return new ResponseEntity<>(stateList, HttpStatus.OK);
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
		RestServiceParam param = buildRestServiceParam(sessionToken, envId, companyCode);
		PlanSearchInput searchInput = new PlanSearchInput();
		searchInput.setChangesIncluded(viewChanges);
		searchInput.setProductCode(productCode);
		searchInput.setPlanCode(planCode);
		searchInput.setIssueState(issueState);

		List<CommonSelectItem> lobList = searchService.getPlanCommonValues(param, searchInput);
		PropertyComparator.sort(lobList, new MutableSortDefinition("displayValue", true, true));
		return new ResponseEntity<>(lobList, HttpStatus.OK);
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
		RestServiceParam param = buildRestServiceParam(sessionToken, envId, companyCode);
		PlanSearchInput searchInput = new PlanSearchInput();
		searchInput.setChangesIncluded(viewChanges);
		searchInput.setProductCode(productCode);
		searchInput.setPlanCode(planCode);
		searchInput.setIssueState(issueState);
		searchInput.setLob(lob);

		List<DateSelectItem> effDateList = searchService.getPlanEffDates(param, searchInput);
		PropertyComparator.sort(effDateList, new MutableSortDefinition("displayValue", true, true));
		return new ResponseEntity<>(effDateList, HttpStatus.OK);
	}

	@RequestMapping(value = "/search/plan/project", produces = { "application/json" }, method = RequestMethod.GET)
	public ResponseEntity<List<String>> getPlanProjects(@RequestHeader(value = "sessionToken", required = true) String sessionToken,
														@RequestHeader(value = "envId", required = true) String envId)
	{
		RestServiceParam param = buildRestServiceParam(sessionToken, envId);

		List<String> projectList = searchService.getPlanProjects(param);
		Collections.sort(projectList);
		return new ResponseEntity<>(projectList, HttpStatus.OK);
	}

	@RequestMapping(value = "/search/plan/table", produces = { "application/json" }, method = RequestMethod.GET)
	public ResponseEntity<List<CommonSelectItem>> getPlanTables(@RequestHeader(value = "sessionToken", required = true) String sessionToken,
																@RequestHeader(value = "envId", required = true) String envId,
																@RequestHeader(value = "companyCode", required = true) String companyCode)	
	{
		RestServiceParam param = buildRestServiceParam(sessionToken, envId, companyCode);

		List<CommonSelectItem> tableList = searchService.getPlanTables(param);
		PropertyComparator.sort(tableList, new MutableSortDefinition("displayValue", true, true));
		return new ResponseEntity<>(tableList, HttpStatus.OK);
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
		RestServiceParam param = buildRestServiceParam(sessionToken, envId);

		ChangesFilterData filterData = searchService.getChangesFilterValues(param);
		Collections.sort(filterData.getProjects());
		Collections.sort(filterData.getUsers());
		PropertyComparator.sort(filterData.getBusinessRuleTables(), new MutableSortDefinition("displayValue", true, true));
		return new ResponseEntity<>(filterData, HttpStatus.OK);
	}

	@RequestMapping(value = "/search/apply/filter", produces = { "application/json" }, method = RequestMethod.GET)
	public ResponseEntity<ApplyFilterData> getApplyFilterValues(@RequestHeader(value = "sessionToken", required = true) String sessionToken,
																@RequestHeader(value = "envId", required = true) String envId)
	{
		RestServiceParam param = buildRestServiceParam(sessionToken, envId);

		ApplyFilterData filterData = searchService.getApplyFilterValues(param);
		PropertyComparator.sort(filterData.getPackages(), new MutableSortDefinition("displayValue", true, true));
		Collections.sort(filterData.getProjects());
		return new ResponseEntity<>(filterData, HttpStatus.OK);
	}

	@RequestMapping(value = "/search/audit/filter", produces = { "application/json" }, method = RequestMethod.GET)
	public ResponseEntity<AuditFilterData> getAuditFilterValues(@RequestHeader(value = "sessionToken", required = true) String sessionToken,
																@RequestHeader(value = "filterAspect", required = true) String filterAspect,
																@RequestHeader(value = "envId", required = true) String envId)
	{
		RestServiceParam param = buildRestServiceParam(sessionToken, envId);

		AuditFilterData filterData = searchService.getAuditFilterValues(param, filterAspect);
		PropertyComparator.sort(filterData.getPackages(), new MutableSortDefinition("displayValue", true, true));
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
		RestServiceParam param = buildRestServiceParam(sessionToken, new String[] { sourceEnvId, targetEnvId });

		PromoteFilterData filterData = searchService.getPromoteFilterValues(param);
		PropertyComparator.sort(filterData.getPackages(), new MutableSortDefinition("displayValue", true, true));
		Collections.sort(filterData.getProjects());
		return new ResponseEntity<>(filterData, HttpStatus.OK);
	}

	@RequestMapping(value = "/search/summary/company", produces = { "application/json" }, method = RequestMethod.GET)
	public ResponseEntity<List<CommonSelectItem>> getSummaryCompanyCodes(@RequestHeader(value = "sessionToken", required = true) String sessionToken,
																		 @RequestHeader(value = "filterAspect", required = true) String filterAspect,
																		 @RequestHeader(value = "envId", required = true) String envId)
	{
		RestServiceParam param = buildRestServiceParam(sessionToken, envId);
		SummarySearchInput searchInput = new SummarySearchInput();
		searchInput.setFilterAspect(filterAspect);

		List<CommonSelectItem> companyList = searchService.getSummaryCommonValues(param, searchInput);
		PropertyComparator.sort(companyList, new MutableSortDefinition("displayValue", true, true));
		return new ResponseEntity<>(companyList, HttpStatus.OK);
	}

	@RequestMapping(value = "/search/summary/product", produces = { "application/json" }, method = RequestMethod.GET)
	public ResponseEntity<List<CommonSelectItem>> getSummaryProductCodes(@RequestHeader(value = "sessionToken", required = true) String sessionToken,
																		 @RequestHeader(value = "filterAspect", required = true) String filterAspect,
																		 @RequestHeader(value = "envId", required = true) String envId,
																		 @RequestHeader(value = "companyCode", required = true) String companyCode)
	{
		RestServiceParam param = buildRestServiceParam(sessionToken, envId, companyCode);
		SummarySearchInput searchInput = new SummarySearchInput();
		searchInput.setFilterAspect(filterAspect);

		List<CommonSelectItem> productList = searchService.getSummaryCommonValues(param, searchInput);
		PropertyComparator.sort(productList, new MutableSortDefinition("displayValue", true, true));
		return new ResponseEntity<>(productList, HttpStatus.OK);
	}

	@RequestMapping(value = "/search/summary/plan", produces = { "application/json" }, method = RequestMethod.GET)
	public ResponseEntity<List<CommonSelectItem>> getSummaryPlanCodes(@RequestHeader(value = "sessionToken", required = true) String sessionToken,
																	  @RequestHeader(value = "filterAspect", required = true) String filterAspect,
																	  @RequestHeader(value = "envId", required = true) String envId,
																	  @RequestHeader(value = "companyCode", required = true) String companyCode,
																	  @RequestHeader(value = "productCode", required = true) String productCode)
	{
		RestServiceParam param = buildRestServiceParam(sessionToken, envId, companyCode);
		SummarySearchInput searchInput = new SummarySearchInput();
		searchInput.setFilterAspect(filterAspect);
		searchInput.setProductCode(productCode);

		List<CommonSelectItem> planList = searchService.getSummaryCommonValues(param, searchInput);
		PropertyComparator.sort(planList, new MutableSortDefinition("displayValue", true, true));
		return new ResponseEntity<>(planList, HttpStatus.OK);
	}

	@RequestMapping(value = "/search/summary/state", produces = { "application/json" }, method = RequestMethod.GET)
	public ResponseEntity<List<CommonSelectItem>> getSummaryIssueStates(@RequestHeader(value = "sessionToken", required = true) String sessionToken,
																		@RequestHeader(value = "filterAspect", required = true) String filterAspect,
																		@RequestHeader(value = "envId", required = true) String envId,
																		@RequestHeader(value = "companyCode", required = true) String companyCode,
																		@RequestHeader(value = "productCode", required = true) String productCode,
																		@RequestHeader(value = "planCode", required = true) String planCode)
	{
		RestServiceParam param = buildRestServiceParam(sessionToken, envId, companyCode);
		SummarySearchInput searchInput = new SummarySearchInput();
		searchInput.setFilterAspect(filterAspect);
		searchInput.setProductCode(productCode);
		searchInput.setPlanCode(planCode);

		List<CommonSelectItem> stateList = searchService.getSummaryCommonValues(param, searchInput);
		PropertyComparator.sort(stateList, new MutableSortDefinition("displayValue", true, true));
		return new ResponseEntity<>(stateList, HttpStatus.OK);
	}

	@RequestMapping(value = "/search/summary/lob", produces = { "application/json" }, method = RequestMethod.GET)
	public ResponseEntity<List<CommonSelectItem>> getSummaryLobs(@RequestHeader(value = "sessionToken", required = true) String sessionToken,
																 @RequestHeader(value = "filterAspect", required = true) String filterAspect,
																 @RequestHeader(value = "envId", required = true) String envId,
																 @RequestHeader(value = "companyCode", required = true) String companyCode,
																 @RequestHeader(value = "productCode", required = true) String productCode,
																 @RequestHeader(value = "planCode", required = true) String planCode,
																 @RequestHeader(value = "issueState", required = true) String issueState)
	{
		RestServiceParam param = buildRestServiceParam(sessionToken, envId, companyCode);
		SummarySearchInput searchInput = new SummarySearchInput();
		searchInput.setFilterAspect(filterAspect);
		searchInput.setProductCode(productCode);
		searchInput.setPlanCode(planCode);
		searchInput.setIssueState(issueState);

		List<CommonSelectItem> lobList = searchService.getSummaryCommonValues(param, searchInput);
		PropertyComparator.sort(lobList, new MutableSortDefinition("displayValue", true, true));
		return new ResponseEntity<>(lobList, HttpStatus.OK);
	}

	@RequestMapping(value = "/search/summary/effdate", produces = { "application/json" }, method = RequestMethod.GET)
	public ResponseEntity<List<DateSelectItem>> getSummaryEffDates(@RequestHeader(value = "sessionToken", required = true) String sessionToken,
																   @RequestHeader(value = "filterAspect", required = true) String filterAspect,
																   @RequestHeader(value = "envId", required = true) String envId,
																   @RequestHeader(value = "companyCode", required = true) String companyCode,
																   @RequestHeader(value = "productCode", required = true) String productCode,
																   @RequestHeader(value = "planCode", required = true) String planCode,
																   @RequestHeader(value = "issueState", required = true) String issueState,
																   @RequestHeader(value = "lob", required = true) String lob)
	{
		RestServiceParam param = buildRestServiceParam(sessionToken, envId, companyCode);
		SummarySearchInput searchInput = new SummarySearchInput();
		searchInput.setFilterAspect(filterAspect);
		searchInput.setProductCode(productCode);
		searchInput.setPlanCode(planCode);
		searchInput.setIssueState(issueState);
		searchInput.setLob(lob);

		List<DateSelectItem> effDateList = searchService.getSummaryEffDates(param, searchInput);
		PropertyComparator.sort(effDateList, new MutableSortDefinition("displayValue", true, true));
		return new ResponseEntity<>(effDateList, HttpStatus.OK);
	}
}
