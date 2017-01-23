package com.csc.fsg.life.rest.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.csc.fsg.life.rest.api.SearchApi;
import com.csc.fsg.life.rest.model.CommonSelectItem;
import com.csc.fsg.life.rest.model.DateSelectItem;
import com.csc.fsg.life.rest.model.PlanSearchInput;

public class SearchApiController
	extends RestApiController
	implements SearchApi
{
	@RequestMapping(value = "/search/environment", produces = { "application/json" }, method = RequestMethod.GET)
	public ResponseEntity<List<CommonSelectItem>> searchEnvironments(@RequestHeader(value = "sessionToken", required = true) String sessionToken)
	{
		return null;
	}

	@RequestMapping(value = "/search/common", produces = { "application/json" }, consumes = { "application/json" }, method = RequestMethod.POST)
	public ResponseEntity<List<CommonSelectItem>> searchCommonValues(@RequestHeader(value = "sessionToken", required = true) String sessionToken, @RequestBody PlanSearchInput searchInput)
	{
		return null;
	}

	@RequestMapping(value = "/search/date", produces = { "application/json" }, consumes = { "application/json" }, method = RequestMethod.POST)
	public ResponseEntity<List<DateSelectItem>> searchDateValues(@RequestHeader(value = "sessionToken", required = true) String sessionToken,
																 @RequestBody PlanSearchInput searchInput)
	{
		return null;
	}
}
