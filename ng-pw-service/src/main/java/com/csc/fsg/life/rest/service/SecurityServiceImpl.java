package com.csc.fsg.life.rest.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import com.csc.fsg.life.common.config.CommonApplicationConfigBean;
import com.csc.fsg.life.common.config.MyBatisApplicationEnvironmentBean;
import com.csc.fsg.life.openam.config.PolicyDecisionPointConfig;
import com.csc.fsg.life.openam.model.AuthorizationQuery;
import com.csc.fsg.life.openam.model.AuthorizationResponse;
import com.csc.fsg.life.openam.model.AuthorizationTreeQuery;
import com.csc.fsg.life.openam.model.AuthorizationTreeResponse;
import com.csc.fsg.life.openam.model.TokenValidationResponse;
import com.csc.fsg.life.rest.exception.ForbiddenException;
import com.csc.fsg.life.rest.exception.RestServiceException;
import com.csc.fsg.life.rest.exception.UnauthorizedException;
import com.csc.fsg.life.rest.exception.UnexpectedException;
import com.csc.fsg.life.rest.model.Credentials;
import com.csc.fsg.life.rest.model.ErrorModel;
import com.csc.fsg.life.rest.model.LoginResponse;
import com.csc.fsg.life.rest.model.SelectItem;
import com.csc.fsg.life.rest.param.AuthorizationAction;
import com.csc.fsg.life.rest.param.RestServiceParam;

// Custom service name is used in order to prevent matching against the pattern used for creation of AOP proxies.
@Service("SecurityServiceComponent")
public class SecurityServiceImpl
	extends RestServiceImpl
	implements SecurityService
{
	static private final String ENDPOINT_AUTHENTICATE = "/authenticate";
	static private final String ENDPOINT_SESSIONS = "/sessions";
	static private final String ENDPOINT_POLICIES = "/policies";

	static private final String ACTION_AUTHENTICATE = ENDPOINT_AUTHENTICATE;
	static private final String ACTION_LOGOUT = ENDPOINT_SESSIONS + "/?_action=logout";
	static private final String ACTION_VALIDATE_TOKEN = ENDPOINT_SESSIONS + "/{0}?_action=validate";
	static private final String ACTION_EVAL_AUTHORITY = ENDPOINT_POLICIES + "?_action=evaluate";
	static private final String ACTION_EVAL_AUTHORITY_TREE = ENDPOINT_POLICIES + "?_action=evaluateTree";

	static private final String HEADER_ACCEPT_API_VERSION = "Accept-API-Version";

	static private final String ENVIRONMENT_URL_ROOT = "auth://environment/";
	static private final String COMPANY_URL_ROOT = "auth://company/";
	static private final String TABLE_URL_ROOT = "auth://table/";

	@Autowired
	protected PolicyDecisionPointConfig pdpConfig = null;

	public SecurityServiceImpl()
	{
		super("com.csc.fsg.life.rest.service.SecurityService");
	}

	public LoginResponse authenticate(Credentials credentials)
	{
		if (!pdpConfig.isSecurityEnabled()) {
			LoginResponse authToken = new LoginResponse();
			authToken.setTokenId("Security-Disabled");
			return authToken;
		}

		try {
			if (credentials == null
			 || !StringUtils.hasText(credentials.getUserName())
			 || !StringUtils.hasText(credentials.getPassword()))
				throw new UnauthorizedException();

			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.APPLICATION_JSON);
			headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
			headers.set("X-OpenAM-Username", credentials.getUserName());
			headers.set("X-OpenAM-Password", credentials.getPassword());
			headers.set(HEADER_ACCEPT_API_VERSION, pdpConfig.getApiVersionHeaderForEndpoint(ENDPOINT_AUTHENTICATE));
			HttpEntity<String> entity = new HttpEntity<>(headers);
			RestTemplate restTemplate = new RestTemplate();

			String url = pdpConfig.getSecurityManagementUrl() + ACTION_AUTHENTICATE;
			ResponseEntity<LoginResponse> response = restTemplate.exchange(url, HttpMethod.POST, entity, LoginResponse.class);
			LoginResponse loginResponse = response.getBody();
			assertAccessToAtLeastOneEnvironment(loginResponse.getTokenId());
			return loginResponse;
		}
		catch (HttpClientErrorException e) {
			HttpStatus status = e.getStatusCode();
			if (status == HttpStatus.UNAUTHORIZED) {
				throw new UnauthorizedException();
			}
			else {
				e.printStackTrace();
				ErrorModel model = errorModelFactory.newErrorModel(UnexpectedException.HTTP_STATUS);
				throw new UnexpectedException(model);
			}
		}
		catch (RestServiceException e) {
			throw e;
		}
		catch (Exception e) {
			e.printStackTrace();
			ErrorModel model = errorModelFactory.newErrorModel(UnexpectedException.HTTP_STATUS);
			throw new UnexpectedException(model);
		}
	}

	private void assertAccessToAtLeastOneEnvironment(String authToken)
	{
		if (!pdpConfig.isSecurityEnabled())
			return;

		CommonApplicationConfigBean pwConfig = getBean(CommonApplicationConfigBean.class);
		Map<String, MyBatisApplicationEnvironmentBean> environmentMap = pwConfig.getEnvironments();

		List<String> resources = new ArrayList<>();
		for (MyBatisApplicationEnvironmentBean env : environmentMap.values())
			resources.add(ENVIRONMENT_URL_ROOT + env.getName());

		Map<String, AuthorizationResponse> authMap = evaluateAuthorization(authToken, resources);

		for (AuthorizationResponse authResponse : authMap.values()) {
			Map<String, Boolean> actionMap = authResponse.getActions();
			if (actionMap != null && Boolean.TRUE.equals(actionMap.get(AuthorizationAction.VIEW.toString())))
				return;
		}

		HttpStatus status = ForbiddenException.HTTP_STATUS;
		ErrorModel errorModel = errorModelFactory.newErrorModel(status, getMessage("no_authorized_envs"));
		throw new ForbiddenException(errorModel);
	}

	public void validateSession(String authToken)
	{
		if (!pdpConfig.isSecurityEnabled())
			return;

		try {
			if (!StringUtils.hasText(authToken))
				throw new UnauthorizedException();

			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.APPLICATION_JSON);
			headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
			headers.set(HEADER_ACCEPT_API_VERSION, pdpConfig.getApiVersionHeaderForEndpoint(ENDPOINT_SESSIONS));
			HttpEntity<String> entity = new HttpEntity<>(headers);
			RestTemplate restTemplate = new RestTemplate();

			String customizedAction = ACTION_VALIDATE_TOKEN.replaceFirst("\\{0\\}", authToken);
			String url = pdpConfig.getSecurityManagementUrl() + customizedAction;

			ResponseEntity<TokenValidationResponse> httpResponse = restTemplate.exchange(url, HttpMethod.POST, entity, TokenValidationResponse.class);
			TokenValidationResponse validationResponse = httpResponse.getBody();
			if (!validationResponse.isValid())
				throw new UnauthorizedException();
		}
		catch (RestServiceException e) {
			throw e;
		}
		catch (Exception e) {
			e.printStackTrace();
			ErrorModel model = errorModelFactory.newErrorModel(UnexpectedException.HTTP_STATUS);
			throw new UnexpectedException(model);
		}
	}

	public void logout(String authToken)
	{
		if (!pdpConfig.isSecurityEnabled())
			return;

		try {
			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.APPLICATION_JSON);
			headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
			headers.set(HEADER_ACCEPT_API_VERSION, pdpConfig.getApiVersionHeaderForEndpoint(ENDPOINT_SESSIONS));
			headers.set(pdpConfig.getAuthTokenCookieName(), authToken);
			HttpEntity<String> entity = new HttpEntity<>(headers);
			RestTemplate restTemplate = new RestTemplate();

			String url = pdpConfig.getSecurityManagementUrl() + ACTION_LOGOUT;
			restTemplate.exchange(url, HttpMethod.POST, entity, Void.class);
		}
		catch (HttpClientErrorException e) {
			HttpStatus status = e.getStatusCode();
			if (status == HttpStatus.UNAUTHORIZED) {
				throw new UnauthorizedException();
			}
			else {
				e.printStackTrace();
				ErrorModel model = errorModelFactory.newErrorModel(UnexpectedException.HTTP_STATUS);
				throw new UnexpectedException(model);
			}
		}
		catch (RestServiceException e) {
			throw e;
		}
		catch (Exception e) {
			e.printStackTrace();
			ErrorModel model = errorModelFactory.newErrorModel(UnexpectedException.HTTP_STATUS);
			throw new UnexpectedException(model);
		}
	}

	public String buildEnvironmentUrl(String envId)
	{
		return ENVIRONMENT_URL_ROOT + envId;
	}

	public String buildCompanyUrl(String envId, String companyCode)
	{
		return COMPANY_URL_ROOT + envId + '/' + companyCode;
	}

	public String buildTableUrl(String envId, String companyCode, String tableDdlName)
	{
		return TABLE_URL_ROOT + envId + '/' + companyCode + '/' + tableDdlName;
	}

	public void assertAuthorization(RestServiceParam param, AuthorizationAction action)
	{
		if (!pdpConfig.isSecurityEnabled())
			return;
		if (action == AuthorizationAction.NONE)
			return;

		List<String> resources = new ArrayList<>();

		boolean isEnv1Found = false;
		String envId1 = param.getSourceEnvId();
		if (StringUtils.hasText(envId1)) {
			isEnv1Found = true;
			resources.add(ENVIRONMENT_URL_ROOT + envId1);
		}

		boolean isEnv2Found = false;
		String envId2 = param.getTargetEnvId();
		if (StringUtils.hasText(envId2)) {
			isEnv2Found = true;
			resources.add(ENVIRONMENT_URL_ROOT + envId2);
		}

		if (resources.isEmpty())
			return;

		String companyCode = param.getCompanyCode();
		if (StringUtils.hasText(companyCode)) {
			if (isEnv1Found)
				resources.add(COMPANY_URL_ROOT + envId1 + '/' + companyCode);
			if (isEnv2Found)
				resources.add(COMPANY_URL_ROOT + envId2 + '/' + companyCode);

			String tableDdlName = param.getTableDdlName();
			if (StringUtils.hasText(tableDdlName)) {
				if (isEnv1Found)
					resources.add(TABLE_URL_ROOT + envId1 + '/' + companyCode + '/');
				if (isEnv2Found)
					resources.add(TABLE_URL_ROOT + envId2 + '/' + companyCode + '/');
			}
		}

		String authToken = param.getAuthToken();
		Map<String, AuthorizationResponse> authMap = evaluateAuthorization(authToken, resources);

		for (AuthorizationResponse auth : authMap.values()) {
			Map<String, Boolean> actions = auth.getActions();
			if (!Boolean.TRUE.equals(actions.get(action.toString())))
				throw new ForbiddenException();
		}
	}

	public List<SelectItem> filterAuthorizedEnvironments(String authToken, List<SelectItem> allEnvironments)
	{
		if (!pdpConfig.isSecurityEnabled())
			return new ArrayList<>(allEnvironments);

		List<String> resources = new ArrayList<>();
		for (SelectItem env : allEnvironments)
			resources.add(ENVIRONMENT_URL_ROOT + env.getValue());

		// Map of authorization decisions for all environments, for which
		// one or more authorization policies have been established;
		// the map is keyed by Environment ID, and the corresponding
		// value represents outcome of authorization evaluation:
		Map<String, AuthorizationResponse> authMap = evaluateAuthorization(authToken, resources);

		List<SelectItem> response = new ArrayList<>();
		for (SelectItem env : allEnvironments) {
			AuthorizationResponse auth = authMap.get(ENVIRONMENT_URL_ROOT + env.getValue());
			if (auth != null) {
				Map<String, Boolean> actionMap = auth.getActions();
				if (actionMap != null
				 && Boolean.TRUE.equals(actionMap.get(AuthorizationAction.VIEW.toString())))
					response.add(env);
			}
		}

		return response;
	}

	public List<SelectItem> filterAuthorizedCompanies(String authToken, String envId, List<SelectItem> allCompanies)
	{
		if (!pdpConfig.isSecurityEnabled())
			return new ArrayList<>(allCompanies);

		String urlPrefix = COMPANY_URL_ROOT + envId + '/';

		List<String> resources = new ArrayList<>();
		for (SelectItem company : allCompanies)
			resources.add(urlPrefix + company.getValue());

		// Map of authorization decisions for all companies, for which
		// one or more authorization policies have been established;
		// the map is keyed by Environment ID/Company Code, and
		// the corresponding value represents outcome of authorization
		// evaluation:
		Map<String, AuthorizationResponse> authMap = evaluateAuthorization(authToken, resources);

		List<SelectItem> response = new ArrayList<>();
		for (SelectItem company : allCompanies) {
			AuthorizationResponse auth = authMap.get(urlPrefix + company.getValue());
			if (auth != null) {
				Map<String, Boolean> actionMap = auth.getActions();
				if (actionMap != null
				 && Boolean.TRUE.equals(actionMap.get(AuthorizationAction.VIEW.toString())))
					response.add(company);
			}
		}

		return response;
	}

	public List<SelectItem> filterAuthorizedTables(String authToken, String envId, String companyCode, List<SelectItem> allTables)
	{
		if (!pdpConfig.isSecurityEnabled())
			return new ArrayList<>(allTables);

		String urlPrefix = TABLE_URL_ROOT + envId + '/' + companyCode + '/';

		List<String> resources = new ArrayList<>();
		for (SelectItem table : allTables)
			resources.add(urlPrefix + table.getValue());

		// Map of authorization decisions for all tables, for which
		// one or more authorization policies have been established;
		// the map is keyed by Environment ID/Company Code/Table DDL
		// Name, and the corresponding value represents outcome of
		// authorization evaluation:
		Map<String, AuthorizationResponse> authMap = evaluateAuthorization(authToken, resources);

		List<SelectItem> response = new ArrayList<>();
		for (SelectItem table : allTables) {
			AuthorizationResponse auth = authMap.get(urlPrefix + table.getValue());
			if (auth != null) {
				Map<String, Boolean> actionMap = auth.getActions();
				if (actionMap != null
				 && Boolean.TRUE.equals(actionMap.get(AuthorizationAction.VIEW.toString())))
					response.add(table);
			}
		}

		return response;
	}

	public boolean isAuthorized(String authToken, List<String> resources, AuthorizationAction action)
	{
		if (!pdpConfig.isSecurityEnabled())
			return true;
		if (action == AuthorizationAction.NONE)
			return true;

		Map<String, AuthorizationResponse> authMap = evaluateAuthorization(authToken, resources);
		for (AuthorizationResponse authResponse : authMap.values()) {
			Map<String, Boolean> actionMap = authResponse.getActions();
			if (actionMap != null && Boolean.TRUE.equals(actionMap.get(action.toString())))
				return true;
		}

		return false;
	}

	private Map<String, AuthorizationResponse> evaluateAuthorization(String authToken, List<String> resources)
	{
		AuthorizationQuery query = new AuthorizationQuery();
		for (String resource : resources)
			query.addResource(resource);

		query.setApplication(pdpConfig.getPolicySetName());
		query.getSubject().setSsoToken(authToken);

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		headers.set(HEADER_ACCEPT_API_VERSION, pdpConfig.getApiVersionHeaderForEndpoint(ENDPOINT_POLICIES));
		headers.set(pdpConfig.getAuthTokenCookieName(), authToken);
		HttpEntity<AuthorizationQuery> entity = new HttpEntity<>(query, headers);
		RestTemplate restTemplate = new RestTemplate();

		String url = pdpConfig.getSecurityManagementUrl() + pdpConfig.getRealm() + ACTION_EVAL_AUTHORITY;

		ResponseEntity<AuthorizationResponse[]> httpResponse = restTemplate.exchange(url, HttpMethod.POST, entity, AuthorizationResponse[].class);
		AuthorizationResponse[] responses = httpResponse.getBody();

		Map<String, AuthorizationResponse> responseMap = new HashMap<>();
		for (AuthorizationResponse response : responses)
			responseMap.put(response.getResource(), response);

		return responseMap;
	}

	@SuppressWarnings("unused")
	private AuthorizationTreeResponse[] evaluateAuthorizationTree(String authToken, String urlRoot)
	{
		AuthorizationTreeQuery query = new AuthorizationTreeQuery();
		query.setResource(urlRoot);
		query.setApplication(pdpConfig.getPolicySetName());
		query.getSubject().setSsoToken(authToken);

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		headers.set(HEADER_ACCEPT_API_VERSION, pdpConfig.getApiVersionHeaderForEndpoint(ENDPOINT_POLICIES));
		headers.set(pdpConfig.getAuthTokenCookieName(), authToken);
		HttpEntity<AuthorizationTreeQuery> entity = new HttpEntity<>(query, headers);
		RestTemplate restTemplate = new RestTemplate();

		String url = pdpConfig.getSecurityManagementUrl() + pdpConfig.getRealm() + ACTION_EVAL_AUTHORITY_TREE;

		ResponseEntity<AuthorizationTreeResponse[]> httpResponse = restTemplate.exchange(url, HttpMethod.POST, entity, AuthorizationTreeResponse[].class);
		AuthorizationTreeResponse[] responses = httpResponse.getBody();
		return responses;
	}
}
