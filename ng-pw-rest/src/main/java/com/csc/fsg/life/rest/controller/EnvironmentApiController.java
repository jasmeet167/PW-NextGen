package com.csc.fsg.life.rest.controller;

import java.util.List;

import javax.inject.Inject;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.csc.fsg.life.rest.api.EnvironmentApi;
import com.csc.fsg.life.rest.model.Company;
import com.csc.fsg.life.rest.model.Environment;
import com.csc.fsg.life.rest.param.RestServiceParam;
import com.csc.fsg.life.rest.service.EnvironmentService;

@Controller
public class EnvironmentApiController
	extends RestApiController
	implements EnvironmentApi
{
	@Inject
	private EnvironmentService environmentService = null;

	@RequestMapping(value = "/environment", produces = { "application/json" }, method = RequestMethod.GET)
	public ResponseEntity<List<Environment>> environmentGet(@RequestHeader(value = "sessionToken", required = true) String sessionToken)
	{
		RestServiceParam param = buildRestServiceParam(sessionToken);
		List<Environment> envList = environmentService.getEnvironments(param);
		return new ResponseEntity<>(envList, HttpStatus.OK);
	}

	@RequestMapping(value = "/environment/{env}/companies", produces = { "application/json" }, method = RequestMethod.GET)
	public ResponseEntity<List<Company>> environmentEnvCompaniesGet(@RequestHeader(value = "sessionToken", required = true) String sessionToken, @PathVariable("env") String env)
	{
		RestServiceParam param = buildRestServiceParam(sessionToken);
		List<Company> companyList = environmentService.getEnvironmentCompanies(param, env);
		return new ResponseEntity<>(companyList, HttpStatus.OK);
	}
}
