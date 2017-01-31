package com.csc.fsg.life.openam.model;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_NULL)
public class AuthorizationTreeQuery
	implements Serializable
{
	static private final long serialVersionUID = 1145913768002789394L;

	private String resource = null;
	private String application = null;
	private AuthorizationSubject subject = new AuthorizationSubject();

	public String getResource()
	{
		return resource;
	}

	public void setResource(String resource)
	{
		this.resource = resource;
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
