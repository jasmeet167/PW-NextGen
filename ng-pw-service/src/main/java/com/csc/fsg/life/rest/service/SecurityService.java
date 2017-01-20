package com.csc.fsg.life.rest.service;

import com.csc.fsg.life.openam.model.AuthorizationArgument;
import com.csc.fsg.life.rest.model.AuthenticationResponse;
import com.csc.fsg.life.rest.model.UserCredentials;

public interface SecurityService
{
	public AuthenticationResponse authenticate(UserCredentials credentials);

	public void validateSession(String sessionToken);

	public void logout(String sessionToken);

	public void assertAuthority(String sessionToken, AuthorizationArgument... arguments);
}
