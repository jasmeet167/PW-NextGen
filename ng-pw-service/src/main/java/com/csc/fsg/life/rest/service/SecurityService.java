package com.csc.fsg.life.rest.service;

import java.util.List;

import com.csc.fsg.life.rest.model.Credentials;
import com.csc.fsg.life.rest.model.LoginResponse;
import com.csc.fsg.life.rest.model.SelectItem;
import com.csc.fsg.life.rest.param.AuthorizationAction;
import com.csc.fsg.life.rest.param.RestServiceParam;

public interface SecurityService
{
	public LoginResponse authenticate(Credentials credentials);

	public void validateSession(String authToken);

	public void logout(String authToken);

	public String buildEnvironmentUrl(String envId);

	public String buildCompanyUrl(String envId, String companyCode);

	public String buildTableUrl(String envId, String companyCode, String tableDdlName);

	public void assertAuthorization(RestServiceParam param, AuthorizationAction action);

	public List<SelectItem> filterAuthorizedEnvironments(String authToken, List<SelectItem> allEnvironments);

	public List<SelectItem> filterAuthorizedCompanies(String authToken, String envId, List<SelectItem> allCompanies);

	public List<SelectItem> filterAuthorizedTables(String authToken, String envId, String companyCode, List<SelectItem> allTables);

	public boolean isAuthorized(String authToken, List<String> resources, AuthorizationAction action);
}
