package com.csc.fsg.life.rest.controller;

import java.util.List;

import com.csc.fsg.life.openam.model.AuthorizationArgument;
import com.csc.fsg.life.rest.param.RestServiceParam;

abstract public class RestApiController
{
	protected RestServiceParam buildRestServiceParam(String sessionToken)
	{
		RestServiceParam param = new RestServiceParam(sessionToken);
		return param;
	}

	protected RestServiceParam buildRestServiceParam(String sessionToken, List<AuthorizationArgument> authArguments)
	{
		RestServiceParam param = new RestServiceParam(sessionToken, authArguments);
		return param;
	}
}
