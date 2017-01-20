package com.csc.fsg.life.rest.controller;

import javax.inject.Inject;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.csc.fsg.life.rest.api.LogoutApi;
import com.csc.fsg.life.rest.service.SecurityService;

@Controller
public class LogoutApiController
	extends RestApiController
	implements LogoutApi
{
	@Inject
	private SecurityService securityService = null;

	@RequestMapping(value = "/logout", produces = { "application/json" }, method = RequestMethod.POST)
	public ResponseEntity<Void> logoutPost(@RequestHeader(value = "sessionToken", required = true) String sessionToken)
	{
		securityService.logout(sessionToken);
		return null;
	}
}
