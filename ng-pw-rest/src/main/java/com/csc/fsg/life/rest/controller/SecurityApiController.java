package com.csc.fsg.life.rest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.csc.fsg.life.rest.api.SecurityApi;
import com.csc.fsg.life.rest.model.Credentials;
import com.csc.fsg.life.rest.model.LoginResponse;
import com.csc.fsg.life.rest.service.SecurityService;

@Controller
public class SecurityApiController
	extends RestApiController
	implements SecurityApi
{
	@Autowired
	private SecurityService securityService = null;

	@RequestMapping(value = "/security/authentication", produces = { "application/json" }, consumes = { "application/json" }, method = RequestMethod.POST)
	public ResponseEntity<LoginResponse> authenticate(@RequestBody Credentials credentials)
	{
		LoginResponse response = securityService.authenticate(credentials);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@RequestMapping(value = "/security/logout", produces = { "application/json" }, method = RequestMethod.POST)
	public ResponseEntity<Void> logout(@RequestHeader(value = "authToken", required = true) String authToken)
	{
		securityService.logout(authToken);
		return null;
	}
}
