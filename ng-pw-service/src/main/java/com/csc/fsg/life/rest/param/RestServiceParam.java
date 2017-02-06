package com.csc.fsg.life.rest.param;

import java.io.Serializable;

public class RestServiceParam
	implements Serializable
{
	private static final long serialVersionUID = 5217150979157700468L;

	private String sessionToken = null;
	private String sourceEnvId = null;
	private String targetEnvId = null;
	private String companyCode = null;
	private String tableDdlName = null;

	public RestServiceParam(String sessionToken)
	{
		this.sessionToken = sessionToken;
	}

	public RestServiceParam(String sessionToken, String envId)
	{
		this.sessionToken = sessionToken;
		this.sourceEnvId = envId;
	}

	public RestServiceParam(String sessionToken, String[] envIds)
	{
		this.sessionToken = sessionToken;
		if (envIds != null) {
			if (envIds.length > 0)
				this.sourceEnvId = envIds[0];
			if (envIds.length > 1)
				this.targetEnvId = envIds[1];
		}
	}

	public RestServiceParam(String sessionToken, String envId, String companyCode)
	{
		this.sessionToken = sessionToken;
		this.sourceEnvId = envId;
		this.companyCode = companyCode;
	}

	public RestServiceParam(String sessionToken, String envId, String companyCode, String tableDddlName)
	{
		this.sessionToken = sessionToken;
		this.sourceEnvId = envId;
		this.companyCode = companyCode;
		this.tableDdlName = tableDddlName;
	}

	public String getSessionToken()
	{
		return sessionToken;
	}

	public String getEnvId()
	{
		return sourceEnvId;
	}

	public String getSourceEnvId()
	{
		return sourceEnvId;
	}

	public String getTargetEnvId()
	{
		return targetEnvId;
	}

	public String getCompanyCode()
	{
		return companyCode;
	}

	public String getTableDdlName()
	{
		return tableDdlName;
	}
}
