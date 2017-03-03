package com.csc.fsg.life.rest.controller;

import com.csc.fsg.life.rest.param.RestServiceParam;

abstract public class RestApiController
{
	protected RestServiceParam buildRestServiceParam(String authToken)
	{
		return new RestServiceParam(authToken);
	}

	protected RestServiceParam buildRestServiceParam(String authToken, String envId)
	{
		return new RestServiceParam(authToken, envId);
	}

	protected RestServiceParam buildRestServiceParam(String authToken, String[] envIds)
	{
		return new RestServiceParam(authToken, envIds);
	}

	protected RestServiceParam buildRestServiceParam(String authToken, String envId, String companyCode)
	{
		return new RestServiceParam(authToken, envId, companyCode);
	}

	protected RestServiceParam buildRestServiceParam(String authToken, String envId, String companyCode, String tableDddlName)
	{
		return new RestServiceParam(authToken, envId, companyCode, tableDddlName);
	}
}
