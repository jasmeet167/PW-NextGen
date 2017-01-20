package com.csc.fsg.life.openam.model;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_NULL)
public class TokenValidationResponse
	implements Serializable
{
	private static final long serialVersionUID = -1553819250348588388L;

	private boolean valid = false;
	private String uid = null;
	private String realm = null;

	public boolean isValid()
	{
		return valid;
	}

	public void setValid(boolean valid)
	{
		this.valid = valid;
	}

	public String getUid()
	{
		return uid;
	}

	public void setUid(String uid)
	{
		this.uid = uid;
	}

	public String getRealm()
	{
		return realm;
	}

	public void setRealm(String realm)
	{
		this.realm = realm;
	}
}
