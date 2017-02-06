package com.csc.fsg.life.rest.controller;

import com.csc.fsg.life.rest.param.RestServiceParam;

abstract public class RestApiController
{
	protected RestServiceParam buildRestServiceParam(String sessionToken)
	{
		return new RestServiceParam(sessionToken);
	}

	protected RestServiceParam buildRestServiceParam(String sessionToken, String envId)
	{
		return new RestServiceParam(sessionToken, envId);
	}

	protected RestServiceParam buildRestServiceParam(String sessionToken, String[] envIds)
	{
		return new RestServiceParam(sessionToken, envIds);
	}

	protected RestServiceParam buildRestServiceParam(String sessionToken, String envId, String companyCode)
	{
		return new RestServiceParam(sessionToken, envId, companyCode);
	}

	protected RestServiceParam buildRestServiceParam(String sessionToken, String envId, String companyCode, String tableDddlName)
	{
		return new RestServiceParam(sessionToken, envId, companyCode, tableDddlName);
	}
}
