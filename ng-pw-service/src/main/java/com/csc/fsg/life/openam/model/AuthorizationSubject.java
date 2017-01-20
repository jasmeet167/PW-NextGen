package com.csc.fsg.life.openam.model;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_NULL)
public class AuthorizationSubject
	implements Serializable
{
	static private final long serialVersionUID = -5883028269465469057L;

	private String ssoToken;
	private String jwt;

	public String getSsoToken()
	{
		return ssoToken;
	}

	public void setSsoToken(String ssoToken)
	{
		this.ssoToken = ssoToken;
	}

	public String getJwt()
	{
		return jwt;
	}

	public void setJwt(String jwt)
	{
		this.jwt = jwt;
	}
}
