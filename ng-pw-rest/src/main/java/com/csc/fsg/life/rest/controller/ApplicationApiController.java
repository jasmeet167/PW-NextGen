package com.csc.fsg.life.rest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.csc.fsg.life.rest.api.ApplicationApi;
import com.csc.fsg.life.rest.model.AboutApplication;
import com.csc.fsg.life.rest.param.RestServiceParam;
import com.csc.fsg.life.rest.service.ApplicationService;

@Controller
public class ApplicationApiController
	extends RestApiController
	implements ApplicationApi
{
	@Autowired
	ApplicationService applicationService = null;

	@RequestMapping(value = "/application/about", produces = { "application/json" }, method = RequestMethod.GET)
	public ResponseEntity<AboutApplication> getInfoAboutApplication(@RequestHeader(value = "sessionToken", required = true) String sessionToken)
	{
		RestServiceParam param = buildRestServiceParam(sessionToken);

		AboutApplication about = applicationService.getInfoAboutApplication(param);
		return new ResponseEntity<>(about, HttpStatus.OK);
	}
}
