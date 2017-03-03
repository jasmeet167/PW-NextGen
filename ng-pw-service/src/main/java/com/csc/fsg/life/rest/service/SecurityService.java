package com.csc.fsg.life.rest.service;

import java.util.List;

import com.csc.fsg.life.rest.model.Credentials;
import com.csc.fsg.life.rest.model.SelectItem;
import com.csc.fsg.life.rest.model.AuthToken;
import com.csc.fsg.life.rest.param.AuthorizationAction;
import com.csc.fsg.life.rest.param.RestServiceParam;

public interface SecurityService
{
	public AuthToken authenticate(Credentials credentials);

	public void validateSession(String authToken);

	public void logout(String authToken);

	public void assertAuthorization(RestServiceParam param, AuthorizationAction action);

	public List<SelectItem> filterAuthorizedEnvironments(String authToken, List<SelectItem> allEnvironments);

	public List<SelectItem> filterAuthorizedCompanies(String authToken, String envId, List<SelectItem> allCompanies);

	public List<SelectItem> filterAuthorizedTables(String authToken, String envId, String companyCode, List<SelectItem> allTables);
}
