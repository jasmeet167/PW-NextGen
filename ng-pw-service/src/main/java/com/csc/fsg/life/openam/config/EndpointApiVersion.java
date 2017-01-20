package com.csc.fsg.life.openam.config;

import java.io.Serializable;

public class EndpointApiVersion
	implements Serializable
{
	private static final long serialVersionUID = 8489866314490219260L;

	private String protocolVersion = null;
	private String resourceVersion = null;

	public String getProtocolVersion()
	{
		return protocolVersion;
	}

	public void setProtocolVersion(String protocolVersion)
	{
		this.protocolVersion = protocolVersion;
	}

	public String getResourceVersion()
	{
		return resourceVersion;
	}

	public void setResourceVersion(String resourceVersion)
	{
		this.resourceVersion = resourceVersion;
	}
}
