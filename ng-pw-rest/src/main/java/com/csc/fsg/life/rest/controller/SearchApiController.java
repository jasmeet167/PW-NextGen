package com.csc.fsg.life.rest.controller;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.support.MutableSortDefinition;
import org.springframework.beans.support.PropertyComparator;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.csc.fsg.life.rest.api.SearchApi;
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

	@RequestMapping(value = "/search/environment", produces = { "application/json" }, method = RequestMethod.GET)
	public ResponseEntity<List<CommonSelectItem>> getEnvironments(@RequestHeader(value = "sessionToken", required = true) String sessionToken)
	{
		RestServiceParam param = buildRestServiceParam(sessionToken);
		List<CommonSelectItem> envList = searchService.getEnvironments(param);
		PropertyComparator.sort(envList, new MutableSortDefinition("displayValue", true, true));
		return new ResponseEntity<>(envList, HttpStatus.OK);
	}

	@RequestMapping(value = "/search/common", produces = { "application/json" }, consumes = { "application/json" }, method = RequestMethod.POST)
	public ResponseEntity<List<CommonSelectItem>> getPlanCommonValues(@RequestHeader(value = "sessionToken", required = true) String sessionToken,
																	  @RequestBody PlanSearchInput searchInput)
	{
		RestServiceParam param = buildRestServiceParam(sessionToken);
		List<CommonSelectItem> envList = searchService.getPlanCommonValues(param, searchInput);
		PropertyComparator.sort(envList, new MutableSortDefinition("coreValue", true, true));
		return new ResponseEntity<>(envList, HttpStatus.OK);
	}

	@RequestMapping(value = "/search/date", produces = { "application/json" }, consumes = { "application/json" }, method = RequestMethod.POST)
	public ResponseEntity<List<DateSelectItem>> getPlanDateValues(@RequestHeader(value = "sessionToken", required = true) String sessionToken,
																  @RequestBody PlanSearchInput searchInput)
	{
		RestServiceParam param = buildRestServiceParam(sessionToken);
		List<DateSelectItem> envList = searchService.getPlanDateValues(param, searchInput);
		PropertyComparator.sort(envList, new MutableSortDefinition("coreValue", true, true));
		return new ResponseEntity<>(envList, HttpStatus.OK);
	}

	@RequestMapping(value = "/search/project", produces = { "application/json" }, method = RequestMethod.GET)
	public ResponseEntity<List<String>> getProjects(@RequestHeader(value = "sessionToken", required = true) String sessionToken,
													@RequestHeader(value = "envId", required = true) String envId)
	{
		RestServiceParam param = buildRestServiceParam(sessionToken);
		List<String> envList = searchService.getProjects(param, envId);
		Collections.sort(envList);
		return new ResponseEntity<>(envList, HttpStatus.OK);
	}

	@RequestMapping(value = "/search/table", produces = { "application/json" }, method = RequestMethod.GET)
	public ResponseEntity<List<CommonSelectItem>> getTables(@RequestHeader(value = "sessionToken", required = true) String sessionToken,
															@RequestHeader(value = "envId", required = true) String envId,
															@RequestHeader(value = "companyCode", required = true) String companyCode)	
	{
		RestServiceParam param = buildRestServiceParam(sessionToken);
		List<CommonSelectItem> envList = searchService.getTables(param, envId, companyCode);
		PropertyComparator.sort(envList, new MutableSortDefinition("coreValue", true, true));
		return new ResponseEntity<>(envList, HttpStatus.OK);
	}
}
