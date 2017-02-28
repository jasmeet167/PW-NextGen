package com.csc.fsg.life.rest.service;

import java.util.List;

import com.csc.fsg.life.rest.model.Credentials;
import com.csc.fsg.life.rest.model.SelectItem;
import com.csc.fsg.life.rest.model.SessionToken;
import com.csc.fsg.life.rest.param.AuthorizationAction;
import com.csc.fsg.life.rest.param.RestServiceParam;

public interface SecurityService
{
	public SessionToken authenticate(Credentials credentials);

	public void validateSession(String sessionToken);

	public void logout(String sessionToken);

	public void assertAuthorization(RestServiceParam param, AuthorizationAction action);

	public List<SelectItem> filterAuthorizedEnvironments(String sessionToken, List<SelectItem> allEnvironments);

	public List<SelectItem> filterAuthorizedCompanies(String sessionToken, String envId, List<SelectItem> allCompanies);

	public List<SelectItem> filterAuthorizedTables(String sessionToken, String envId, String companyCode, List<SelectItem> allTables);
}
