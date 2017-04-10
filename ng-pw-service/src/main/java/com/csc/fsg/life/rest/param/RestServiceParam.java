package com.csc.fsg.life.rest.param;

import java.io.Serializable;

public class RestServiceParam
	implements Serializable
{
	private static final long serialVersionUID = 5217150979157700468L;

	private String authToken = null;
	private String sourceEnvId = null;
	private String targetEnvId = null;
	private String companyCode = null;
	private String tableId = null;

	public RestServiceParam(String authToken)
	{
		this.authToken = authToken;
	}

	public RestServiceParam(String authToken, String envId)
	{
		this.authToken = authToken;
		this.sourceEnvId = envId;
	}

	public RestServiceParam(String authToken, String[] envIds)
	{
		this.authToken = authToken;
		if (envIds != null) {
			if (envIds.length > 0)
				this.sourceEnvId = envIds[0];
			if (envIds.length > 1)
				this.targetEnvId = envIds[1];
		}
	}

	public RestServiceParam(String authToken, String envId, String companyCode)
	{
		this.authToken = authToken;
		this.sourceEnvId = envId;
		this.companyCode = companyCode;
	}

	public RestServiceParam(String authToken, String envId, String companyCode, String tableId)
	{
		this.authToken = authToken;
		this.sourceEnvId = envId;
		this.companyCode = companyCode;
		this.tableId = tableId;
	}

	public String getAuthToken()
	{
		return authToken;
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

	public String getTableId()
	{
		return tableId;
	}
}
