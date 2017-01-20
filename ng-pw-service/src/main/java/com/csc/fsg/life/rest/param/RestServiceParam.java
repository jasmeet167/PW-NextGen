package com.csc.fsg.life.rest.param;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.csc.fsg.life.openam.model.AuthorizationArgument;

public class RestServiceParam
	implements Serializable
{
	private static final long serialVersionUID = 5217150979157700468L;

	private String sessionToken = null;
	private List<AuthorizationArgument> authArguments = null;

	public RestServiceParam(String sessionToken)
	{
		this(sessionToken, null);
	}

	public RestServiceParam(String sessionToken, List<AuthorizationArgument> authArguments)
	{
		this.sessionToken = sessionToken;

		if (authArguments == null)
			this.authArguments = new ArrayList<>();
		else
			this.authArguments = authArguments;
	}

	public void addAuthorizationArgument(AuthorizationArgument authArgument)
	{
		authArguments.add(authArgument);
	}

	public String getSessionToken()
	{
		return sessionToken;
	}

	public List<AuthorizationArgument> getAuthorizationArguments()
	{
		return Collections.unmodifiableList(authArguments);
	}
}
