package com.csc.fsg.life.rest.controller;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.support.MutableSortDefinition;
import org.springframework.beans.support.PropertyComparator;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.csc.fsg.life.rest.api.SearchApi;
import com.csc.fsg.life.rest.model.ApplyFilterData;
import com.csc.fsg.life.rest.model.AuditFilterData;
import com.csc.fsg.life.rest.model.ChangesFilterData;
import com.csc.fsg.life.rest.model.PlanSearchInput;
import com.csc.fsg.life.rest.model.PromoteFilterData;
import com.csc.fsg.life.rest.model.SelectItem;
import com.csc.fsg.life.rest.model.SummarySearchInput;
import com.csc.fsg.life.rest.param.RestServiceParam;
import com.csc.fsg.life.rest.service.SearchService;

@Controller
public class SearchApiController
	extends RestApiController
	implements SearchApi
{
	@Autowired
	private SearchService searchService = null;

	@CrossOrigin
	@RequestMapping(value = "/search/common/environment", produces = { "application/json" }, method = RequestMethod.GET)
	public ResponseEntity<List<SelectItem>> getCommonEnvironments(@RequestHeader(value = "authToken", required = true) String authToken)
	{
		RestServiceParam param = buildRestServiceParam(authToken);

		List<SelectItem> envList = searchService.getCommonEnvironments(param);
		PropertyComparator.sort(envList, new MutableSortDefinition("label", true, true));
		return new ResponseEntity<>(envList, HttpStatus.OK);
	}

	@CrossOrigin
	@RequestMapping(value = "/search/plan/company", produces = { "application/json" }, method = RequestMethod.GET)
	public ResponseEntity<List<SelectItem>> getPlanCompanyCodes(@RequestHeader(value = "authToken", required = true) String authToken,
																@RequestHeader(value = "viewChanges", required = true) Boolean viewChanges,
																@RequestHeader(value = "envId", required = true) String envId)
	{
		RestServiceParam param = buildRestServiceParam(authToken, envId);
		PlanSearchInput searchInput = new PlanSearchInput();
		searchInput.setChangesIncluded(viewChanges);

		List<SelectItem> companyList = searchService.getPlanCommonValues(param, searchInput);
		PropertyComparator.sort(companyList, new MutableSortDefinition("label", true, true));
		return new ResponseEntity<>(companyList, HttpStatus.OK);
	}

	@CrossOrigin
	@RequestMapping(value = "/search/plan/product", produces = { "application/json" }, method = RequestMethod.GET)
	public ResponseEntity<List<SelectItem>> getPlanProductCodes(@RequestHeader(value = "authToken", required = true) String authToken,
																@RequestHeader(value = "viewChanges", required = true) Boolean viewChanges,
																@RequestHeader(value = "envId", required = true) String envId,
																@RequestHeader(value = "companyCode", required = true) String companyCode)
	{
		RestServiceParam param = buildRestServiceParam(authToken, envId, companyCode);
		PlanSearchInput searchInput = new PlanSearchInput();
		searchInput.setChangesIncluded(viewChanges);

		List<SelectItem> productList = searchService.getPlanCommonValues(param, searchInput);
		PropertyComparator.sort(productList, new MutableSortDefinition("label", true, true));
		return new ResponseEntity<>(productList, HttpStatus.OK);
	}

	@CrossOrigin
	@RequestMapping(value = "/search/plan/plan", produces = { "application/json" }, method = RequestMethod.GET)
	public ResponseEntity<List<SelectItem>> getPlanPlanCodes(@RequestHeader(value = "authToken", required = true) String authToken,
															@RequestHeader(value = "viewChanges", required = true) Boolean viewChanges,
															@RequestHeader(value = "envId", required = true) String envId,
															@RequestHeader(value = "companyCode", required = true) String companyCode,
															@RequestHeader(value = "productCode", required = true) String productCode)
	{
		RestServiceParam param = buildRestServiceParam(authToken, envId, companyCode);
		PlanSearchInput searchInput = new PlanSearchInput();
		searchInput.setChangesIncluded(viewChanges);
		searchInput.setProductCode(productCode);

		List<SelectItem> planList = searchService.getPlanCommonValues(param, searchInput);
		PropertyComparator.sort(planList, new MutableSortDefinition("label", true, true));
		return new ResponseEntity<>(planList, HttpStatus.OK);
	}

	@CrossOrigin
	@RequestMapping(value = "/search/plan/state", produces = { "application/json" }, method = RequestMethod.GET)
	public ResponseEntity<List<SelectItem>> getPlanIssueStates(@RequestHeader(value = "authToken", required = true) String authToken,
															   @RequestHeader(value = "viewChanges", required = true) Boolean viewChanges,
															   @RequestHeader(value = "envId", required = true) String envId,
															   @RequestHeader(value = "companyCode", required = true) String companyCode,
															   @RequestHeader(value = "productCode", required = true) String productCode,
															   @RequestHeader(value = "planCode", required = true) String planCode)
	{
		RestServiceParam param = buildRestServiceParam(authToken, envId, companyCode);
		PlanSearchInput searchInput = new PlanSearchInput();
		searchInput.setChangesIncluded(viewChanges);
		searchInput.setProductCode(productCode);
		searchInput.setPlanCode(planCode);

		List<SelectItem> stateList = searchService.getPlanCommonValues(param, searchInput);
		PropertyComparator.sort(stateList, new MutableSortDefinition("label", true, true));
		return new ResponseEntity<>(stateList, HttpStatus.OK);
	}

	@CrossOrigin
	@RequestMapping(value = "/search/plan/lob", produces = { "application/json" }, method = RequestMethod.GET)
	public ResponseEntity<List<SelectItem>> getPlanLobs(@RequestHeader(value = "authToken", required = true) String authToken,
														@RequestHeader(value = "viewChanges", required = true) Boolean viewChanges,
														@RequestHeader(value = "envId", required = true) String envId,
														@RequestHeader(value = "companyCode", required = true) String companyCode,
														@RequestHeader(value = "productCode", required = true) String productCode,
														@RequestHeader(value = "planCode", required = true) String planCode,
														@RequestHeader(value = "issueState", required = true) String issueState)
	{
		RestServiceParam param = buildRestServiceParam(authToken, envId, companyCode);
		PlanSearchInput searchInput = new PlanSearchInput();
		searchInput.setChangesIncluded(viewChanges);
		searchInput.setProductCode(productCode);
		searchInput.setPlanCode(planCode);
		searchInput.setIssueState(issueState);

		List<SelectItem> lobList = searchService.getPlanCommonValues(param, searchInput);
		PropertyComparator.sort(lobList, new MutableSortDefinition("label", true, true));
		return new ResponseEntity<>(lobList, HttpStatus.OK);
	}

	@CrossOrigin
	@RequestMapping(value = "/search/plan/effdate", produces = { "application/json" }, method = RequestMethod.GET)
	public ResponseEntity<List<SelectItem>> getPlanEffDates(@RequestHeader(value = "authToken", required = true) String authToken,
															@RequestHeader(value = "viewChanges", required = true) Boolean viewChanges,
															@RequestHeader(value = "envId", required = true) String envId,
															@RequestHeader(value = "companyCode", required = true) String companyCode,
															@RequestHeader(value = "productCode", required = true) String productCode,
															@RequestHeader(value = "planCode", required = true) String planCode,
															@RequestHeader(value = "issueState", required = true) String issueState,
															@RequestHeader(value = "lob", required = true) String lob)
	{
		RestServiceParam param = buildRestServiceParam(authToken, envId, companyCode);
		PlanSearchInput searchInput = new PlanSearchInput();
		searchInput.setChangesIncluded(viewChanges);
		searchInput.setProductCode(productCode);
		searchInput.setPlanCode(planCode);
		searchInput.setIssueState(issueState);
		searchInput.setLob(lob);

		List<SelectItem> effDateList = searchService.getPlanEffDates(param, searchInput);
		PropertyComparator.sort(effDateList, new MutableSortDefinition("label", true, true));
		return new ResponseEntity<>(effDateList, HttpStatus.OK);
	}

	@CrossOrigin
	@RequestMapping(value = "/search/plan/project", produces = { "application/json" }, method = RequestMethod.GET)
	public ResponseEntity<List<SelectItem>> getPlanProjects(@RequestHeader(value = "authToken", required = true) String authToken,
															@RequestHeader(value = "envId", required = true) String envId)
	{
		RestServiceParam param = buildRestServiceParam(authToken, envId);

		List<SelectItem> projectList = searchService.getPlanProjects(param);
		PropertyComparator.sort(projectList, new MutableSortDefinition("label", true, true));
		return new ResponseEntity<>(projectList, HttpStatus.OK);
	}

	@CrossOrigin
	@RequestMapping(value = "/search/plan/table", produces = { "application/json" }, method = RequestMethod.GET)
	public ResponseEntity<List<SelectItem>> getPlanTables(@RequestHeader(value = "authToken", required = true) String authToken,
														  @RequestHeader(value = "envId", required = true) String envId,
														  @RequestHeader(value = "companyCode", required = true) String companyCode)	
	{
		RestServiceParam param = buildRestServiceParam(authToken, envId, companyCode);

		List<SelectItem> tableList = searchService.getPlanTables(param);
		PropertyComparator.sort(tableList, new MutableSortDefinition("label", true, true));
		return new ResponseEntity<>(tableList, HttpStatus.OK);
	}

	@CrossOrigin
	@RequestMapping(value = "/search/changes/environment", produces = { "application/json" }, method = RequestMethod.GET)
	public ResponseEntity<List<SelectItem>> getChangesEnvironments(@RequestHeader(value = "authToken", required = true) String authToken)
	{
		RestServiceParam param = buildRestServiceParam(authToken);

		List<SelectItem> envList = searchService.getChangesEnvironments(param);
		PropertyComparator.sort(envList, new MutableSortDefinition("label", true, true));
		return new ResponseEntity<>(envList, HttpStatus.OK);
	}

	@CrossOrigin
	@RequestMapping(value = "/search/changes/filter", produces = { "application/json" }, method = RequestMethod.GET)
	public ResponseEntity<ChangesFilterData> getChangesFilterValues(@RequestHeader(value = "authToken", required = true) String authToken,
																	@RequestHeader(value = "envId", required = true) String envId)
	{
		RestServiceParam param = buildRestServiceParam(authToken, envId);

		ChangesFilterData filterData = searchService.getChangesFilterValues(param);
		Collections.sort(filterData.getProjects());
		Collections.sort(filterData.getUsers());
		PropertyComparator.sort(filterData.getBusinessRuleTables(), new MutableSortDefinition("label", true, true));
		return new ResponseEntity<>(filterData, HttpStatus.OK);
	}

	@CrossOrigin
	@RequestMapping(value = "/search/apply/filter", produces = { "application/json" }, method = RequestMethod.GET)
	public ResponseEntity<ApplyFilterData> getApplyFilterValues(@RequestHeader(value = "authToken", required = true) String authToken,
																@RequestHeader(value = "envId", required = true) String envId)
	{
		RestServiceParam param = buildRestServiceParam(authToken, envId);

		ApplyFilterData filterData = searchService.getApplyFilterValues(param);
		PropertyComparator.sort(filterData.getPackages(), new MutableSortDefinition("label", true, true));
		Collections.sort(filterData.getProjects());
		return new ResponseEntity<>(filterData, HttpStatus.OK);
	}

	@CrossOrigin
	@RequestMapping(value = "/search/audit/filter", produces = { "application/json" }, method = RequestMethod.GET)
	public ResponseEntity<AuditFilterData> getAuditFilterValues(@RequestHeader(value = "authToken", required = true) String authToken,
																@RequestHeader(value = "filterAspect", required = true) String filterAspect,
																@RequestHeader(value = "envId", required = true) String envId)
	{
		RestServiceParam param = buildRestServiceParam(authToken, envId);

		AuditFilterData filterData = searchService.getAuditFilterValues(param, filterAspect);
		PropertyComparator.sort(filterData.getPackages(), new MutableSortDefinition("label", true, true));
		Collections.sort(filterData.getProjects());
		PropertyComparator.sort(filterData.getBusinessRuleTables(), new MutableSortDefinition("label", true, true));
		Collections.sort(filterData.getUsers());
		return new ResponseEntity<>(filterData, HttpStatus.OK);
	}

	@CrossOrigin
	@RequestMapping(value = "/search/promote/filter", produces = { "application/json" }, method = RequestMethod.GET)
	public ResponseEntity<PromoteFilterData> getPromoteFilterValues(@RequestHeader(value = "authToken", required = true) String authToken,
																	@RequestHeader(value = "sourceEnvId", required = true) String sourceEnvId,
																	@RequestHeader(value = "targetEnvId", required = true) String targetEnvId)
	{
		RestServiceParam param = buildRestServiceParam(authToken, new String[] { sourceEnvId, targetEnvId });

		PromoteFilterData filterData = searchService.getPromoteFilterValues(param);
		PropertyComparator.sort(filterData.getPackages(), new MutableSortDefinition("label", true, true));
		Collections.sort(filterData.getProjects());
		return new ResponseEntity<>(filterData, HttpStatus.OK);
	}

	@CrossOrigin
	@RequestMapping(value = "/search/summary/company", produces = { "application/json" }, method = RequestMethod.GET)
	public ResponseEntity<List<SelectItem>> getSummaryCompanyCodes(@RequestHeader(value = "authToken", required = true) String authToken,
																   @RequestHeader(value = "filterAspect", required = true) String filterAspect,
																   @RequestHeader(value = "envId", required = true) String envId)
	{
		RestServiceParam param = buildRestServiceParam(authToken, envId);
		SummarySearchInput searchInput = new SummarySearchInput();
		searchInput.setFilterAspect(filterAspect);

		List<SelectItem> companyList = searchService.getSummaryCommonValues(param, searchInput);
		PropertyComparator.sort(companyList, new MutableSortDefinition("label", true, true));
		return new ResponseEntity<>(companyList, HttpStatus.OK);
	}

	@CrossOrigin
	@RequestMapping(value = "/search/summary/product", produces = { "application/json" }, method = RequestMethod.GET)
	public ResponseEntity<List<SelectItem>> getSummaryProductCodes(@RequestHeader(value = "authToken", required = true) String authToken,
																   @RequestHeader(value = "filterAspect", required = true) String filterAspect,
																   @RequestHeader(value = "envId", required = true) String envId,
																   @RequestHeader(value = "companyCode", required = true) String companyCode)
	{
		RestServiceParam param = buildRestServiceParam(authToken, envId, companyCode);
		SummarySearchInput searchInput = new SummarySearchInput();
		searchInput.setFilterAspect(filterAspect);

		List<SelectItem> productList = searchService.getSummaryCommonValues(param, searchInput);
		PropertyComparator.sort(productList, new MutableSortDefinition("label", true, true));
		return new ResponseEntity<>(productList, HttpStatus.OK);
	}

	@CrossOrigin
	@RequestMapping(value = "/search/summary/plan", produces = { "application/json" }, method = RequestMethod.GET)
	public ResponseEntity<List<SelectItem>> getSummaryPlanCodes(@RequestHeader(value = "authToken", required = true) String authToken,
																@RequestHeader(value = "filterAspect", required = true) String filterAspect,
																@RequestHeader(value = "envId", required = true) String envId,
																@RequestHeader(value = "companyCode", required = true) String companyCode,
																@RequestHeader(value = "productCode", required = true) String productCode)
	{
		RestServiceParam param = buildRestServiceParam(authToken, envId, companyCode);
		SummarySearchInput searchInput = new SummarySearchInput();
		searchInput.setFilterAspect(filterAspect);
		searchInput.setProductCode(productCode);

		List<SelectItem> planList = searchService.getSummaryCommonValues(param, searchInput);
		PropertyComparator.sort(planList, new MutableSortDefinition("label", true, true));
		return new ResponseEntity<>(planList, HttpStatus.OK);
	}

	@CrossOrigin
	@RequestMapping(value = "/search/summary/state", produces = { "application/json" }, method = RequestMethod.GET)
	public ResponseEntity<List<SelectItem>> getSummaryIssueStates(@RequestHeader(value = "authToken", required = true) String authToken,
																  @RequestHeader(value = "filterAspect", required = true) String filterAspect,
																  @RequestHeader(value = "envId", required = true) String envId,
																  @RequestHeader(value = "companyCode", required = true) String companyCode,
																  @RequestHeader(value = "productCode", required = true) String productCode,
																  @RequestHeader(value = "planCode", required = true) String planCode)
	{
		RestServiceParam param = buildRestServiceParam(authToken, envId, companyCode);
		SummarySearchInput searchInput = new SummarySearchInput();
		searchInput.setFilterAspect(filterAspect);
		searchInput.setProductCode(productCode);
		searchInput.setPlanCode(planCode);

		List<SelectItem> stateList = searchService.getSummaryCommonValues(param, searchInput);
		PropertyComparator.sort(stateList, new MutableSortDefinition("label", true, true));
		return new ResponseEntity<>(stateList, HttpStatus.OK);
	}

	@CrossOrigin
	@RequestMapping(value = "/search/summary/lob", produces = { "application/json" }, method = RequestMethod.GET)
	public ResponseEntity<List<SelectItem>> getSummaryLobs(@RequestHeader(value = "authToken", required = true) String authToken,
														   @RequestHeader(value = "filterAspect", required = true) String filterAspect,
														   @RequestHeader(value = "envId", required = true) String envId,
														   @RequestHeader(value = "companyCode", required = true) String companyCode,
														   @RequestHeader(value = "productCode", required = true) String productCode,
														   @RequestHeader(value = "planCode", required = true) String planCode,
														   @RequestHeader(value = "issueState", required = true) String issueState)
	{
		RestServiceParam param = buildRestServiceParam(authToken, envId, companyCode);
		SummarySearchInput searchInput = new SummarySearchInput();
		searchInput.setFilterAspect(filterAspect);
		searchInput.setProductCode(productCode);
		searchInput.setPlanCode(planCode);
		searchInput.setIssueState(issueState);

		List<SelectItem> lobList = searchService.getSummaryCommonValues(param, searchInput);
		PropertyComparator.sort(lobList, new MutableSortDefinition("label", true, true));
		return new ResponseEntity<>(lobList, HttpStatus.OK);
	}

	@CrossOrigin
	@RequestMapping(value = "/search/summary/effdate", produces = { "application/json" }, method = RequestMethod.GET)
	public ResponseEntity<List<SelectItem>> getSummaryEffDates(@RequestHeader(value = "authToken", required = true) String authToken,
															   @RequestHeader(value = "filterAspect", required = true) String filterAspect,
															   @RequestHeader(value = "envId", required = true) String envId,
															   @RequestHeader(value = "companyCode", required = true) String companyCode,
															   @RequestHeader(value = "productCode", required = true) String productCode,
															   @RequestHeader(value = "planCode", required = true) String planCode,
															   @RequestHeader(value = "issueState", required = true) String issueState,
															   @RequestHeader(value = "lob", required = true) String lob)
	{
		RestServiceParam param = buildRestServiceParam(authToken, envId, companyCode);
		SummarySearchInput searchInput = new SummarySearchInput();
		searchInput.setFilterAspect(filterAspect);
		searchInput.setProductCode(productCode);
		searchInput.setPlanCode(planCode);
		searchInput.setIssueState(issueState);
		searchInput.setLob(lob);

		List<SelectItem> effDateList = searchService.getSummaryEffDates(param, searchInput);
		PropertyComparator.sort(effDateList, new MutableSortDefinition("label", true, true));
		return new ResponseEntity<>(effDateList, HttpStatus.OK);
	}
}
