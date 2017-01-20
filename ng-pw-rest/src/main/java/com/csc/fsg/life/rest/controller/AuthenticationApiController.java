package com.csc.fsg.life.rest.controller;

import javax.inject.Inject;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.csc.fsg.life.rest.api.AuthenticationApi;
import com.csc.fsg.life.rest.model.AuthenticationResponse;
import com.csc.fsg.life.rest.model.UserCredentials;
import com.csc.fsg.life.rest.service.SecurityService;

@Controller
public class AuthenticationApiController
	extends RestApiController
	implements AuthenticationApi
{
	@Inject
	private SecurityService securityService = null;

	@RequestMapping(value = "/authentication", produces = { "application/json" }, consumes = { "application/json" }, method = RequestMethod.POST)
	public ResponseEntity<AuthenticationResponse> authenticationPost(@RequestBody UserCredentials credentials)
	{
		AuthenticationResponse response = securityService.authenticate(credentials);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
}
