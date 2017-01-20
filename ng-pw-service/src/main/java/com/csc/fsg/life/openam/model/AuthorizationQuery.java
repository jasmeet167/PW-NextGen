package com.csc.fsg.life.openam.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_NULL)
public class AuthorizationQuery
	implements Serializable
{
	static private final long serialVersionUID = -4090278391690952019L;

	private List<String> resources = new ArrayList<>();
	private String application = null;
	private AuthorizationSubject subject = new AuthorizationSubject();

	public List<String> getResources()
	{
		return resources;
	}

	public void addResource(String resource)
	{
		resources.add(resource);
	}

	public String getApplication()
	{
		return application;
	}

	public void setApplication(String application)
	{
		this.application = application;
	}

	public AuthorizationSubject getSubject()
	{
		return subject;
	}
}
