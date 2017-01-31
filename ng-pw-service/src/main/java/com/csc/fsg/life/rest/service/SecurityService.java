package com.csc.fsg.life.rest.service;

import java.util.Set;

import com.csc.fsg.life.openam.model.AuthorizationArgument;
import com.csc.fsg.life.rest.annotation.AuthorizationAction;
import com.csc.fsg.life.rest.model.Credentials;
import com.csc.fsg.life.rest.model.SessionToken;

public interface SecurityService
{
	public SessionToken authenticate(Credentials credentials);

	public void validateSession(String sessionToken);

	public void logout(String sessionToken);

	public void assertAuthority(String sessionToken, AuthorizationArgument... arguments);

	public Set<String> filterAuthorizedEnvironments(String sessionToken, AuthorizationAction action, Set<String> allEnvironments);
}
