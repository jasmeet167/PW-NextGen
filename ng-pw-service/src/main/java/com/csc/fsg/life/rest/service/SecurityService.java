package com.csc.fsg.life.rest.service;

import java.util.List;

import com.csc.fsg.life.openam.model.AuthorizationArgument;
import com.csc.fsg.life.rest.annotation.AuthorizationAction;
import com.csc.fsg.life.rest.model.CommonSelectItem;
import com.csc.fsg.life.rest.model.Credentials;
import com.csc.fsg.life.rest.model.SessionToken;

public interface SecurityService
{
	public SessionToken authenticate(Credentials credentials);

	public void validateSession(String sessionToken);

	public void logout(String sessionToken);

	public void assertAuthority(String sessionToken, AuthorizationArgument... arguments);

	public List<CommonSelectItem> filterAuthorizedEnvironments(String sessionToken, AuthorizationAction action, List<CommonSelectItem> allEnvironments);

	public List<CommonSelectItem> filterAuthorizedCompanies(String sessionToken, AuthorizationAction action, String envId, List<CommonSelectItem> allCompanies);
}
