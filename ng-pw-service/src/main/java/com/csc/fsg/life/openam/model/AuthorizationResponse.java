package com.csc.fsg.life.openam.model;

import java.io.Serializable;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_NULL)
public class AuthorizationResponse
	implements Serializable
{
	static private final long serialVersionUID = -3882554437276891589L;

	private String resource = null;
	private String ttl = null;
	private Map<String, Boolean> actions = null;

	public String getResource()
	{
		return resource;
	}

	public void setResource(String resource)
	{
		this.resource = resource;
	}

	public String getTtl()
	{
		return ttl;
	}

	public void setTtl(String ttl)
	{
		this.ttl = ttl;
	}

	public Map<String, Boolean> getActions()
	{
		return actions;
	}

	public void setActions(Map<String, Boolean> actions)
	{
		this.actions = actions;
	}
}
