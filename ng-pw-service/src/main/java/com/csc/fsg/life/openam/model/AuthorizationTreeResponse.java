package com.csc.fsg.life.openam.model;

import java.io.Serializable;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_NULL)
public class AuthorizationTreeResponse
	implements Serializable
{
	static private final long serialVersionUID = -4730245743668020339L;

	private String resource = null;
	private Map<String, Boolean> actions = null;
	private String ttl = null;

	public String getResource()
	{
		return resource;
	}

	public void setResource(String resource)
	{
		this.resource = resource;
	}

	public Map<String, Boolean> getActions()
	{
		return actions;
	}

	public void setActions(Map<String, Boolean> actions)
	{
		this.actions = actions;
	}

	public String getTtl()
	{
		return ttl;
	}

	public void setTtl(String ttl)
	{
		this.ttl = ttl;
	}
}
