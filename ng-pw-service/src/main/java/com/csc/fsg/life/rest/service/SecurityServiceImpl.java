package com.csc.fsg.life.rest.service;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

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

import com.csc.fsg.life.openam.config.SecurityManagementConfig;
import com.csc.fsg.life.openam.model.AuthorizationArgument;
import com.csc.fsg.life.openam.model.AuthorizationQuery;
import com.csc.fsg.life.openam.model.AuthorizationResponse;
import com.csc.fsg.life.openam.model.AuthorizationTreeQuery;
import com.csc.fsg.life.openam.model.AuthorizationTreeResponse;
import com.csc.fsg.life.openam.model.TokenValidationResponse;
import com.csc.fsg.life.rest.annotation.AuthorizationAction;
import com.csc.fsg.life.rest.exception.RestServiceException;
import com.csc.fsg.life.rest.exception.UnauthorizedException;
import com.csc.fsg.life.rest.exception.UnexpectedException;
import com.csc.fsg.life.rest.model.Credentials;
import com.csc.fsg.life.rest.model.ErrorModel;
import com.csc.fsg.life.rest.model.ErrorModelFactory;
import com.csc.fsg.life.rest.model.SessionToken;

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

	@Autowired
	protected SecurityManagementConfig secConfig = null;

	@Autowired
	protected ErrorModelFactory errorModelFactory = null;

	public SessionToken authenticate(Credentials credentials)
	{
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
			headers.set(HEADER_ACCEPT_API_VERSION, secConfig.getApiVersionHeaderForEndpoint(ENDPOINT_AUTHENTICATE));
			HttpEntity<String> entity = new HttpEntity<>(headers);
			RestTemplate restTemplate = new RestTemplate();

			String url = secConfig.getSecurityManagementUrl() + ACTION_AUTHENTICATE;
			ResponseEntity<SessionToken> response = restTemplate.exchange(url, HttpMethod.POST, entity, SessionToken.class);
			return response.getBody();
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

	public void validateSession(String sessionToken)
	{
		try {
			if (!StringUtils.hasText(sessionToken))
				throw new UnauthorizedException();

			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.APPLICATION_JSON);
			headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
			headers.set(HEADER_ACCEPT_API_VERSION, secConfig.getApiVersionHeaderForEndpoint(ENDPOINT_SESSIONS));
			HttpEntity<String> entity = new HttpEntity<>(headers);
			RestTemplate restTemplate = new RestTemplate();

			String customizedAction = ACTION_VALIDATE_TOKEN.replaceFirst("\\{0\\}", sessionToken);
			String url = secConfig.getSecurityManagementUrl() + customizedAction;

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

	public void logout(String sessionToken)
	{
		try {
			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.APPLICATION_JSON);
			headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
			headers.set(HEADER_ACCEPT_API_VERSION, secConfig.getApiVersionHeaderForEndpoint(ENDPOINT_SESSIONS));
			headers.set(secConfig.getSessionTokenCookieName(), sessionToken);
			HttpEntity<String> entity = new HttpEntity<>(headers);
			RestTemplate restTemplate = new RestTemplate();

			String url = secConfig.getSecurityManagementUrl() + ACTION_LOGOUT;
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

	public void assertAuthority(String sessionToken, AuthorizationArgument... arguments)
	{
		try {
			AuthorizationQuery query = new AuthorizationQuery();
			for (AuthorizationArgument argument : arguments)
				query.addResource(argument.getResource());

			query.setApplication(secConfig.getPolicySetName());
			query.getSubject().setSsoToken(sessionToken);

			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.APPLICATION_JSON);
			headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
			headers.set(HEADER_ACCEPT_API_VERSION, secConfig.getApiVersionHeaderForEndpoint(ENDPOINT_POLICIES));
			headers.set(secConfig.getSessionTokenCookieName(), sessionToken);
			HttpEntity<AuthorizationQuery> entity = new HttpEntity<>(query, headers);
			RestTemplate restTemplate = new RestTemplate();

			String url = secConfig.getSecurityManagementUrl() + secConfig.getRealm() + ACTION_EVAL_AUTHORITY;

			ResponseEntity<AuthorizationResponse[]> httpResponse = restTemplate.exchange(url, HttpMethod.POST, entity, AuthorizationResponse[].class);
			AuthorizationResponse[] responses = httpResponse.getBody();

			Map<String, AuthorizationResponse> responseMap = new HashMap<>();
			for (AuthorizationResponse response : responses)
				responseMap.put(response.getResource(), response);

			for (AuthorizationArgument argument : arguments) {
				AuthorizationResponse response = responseMap.get(argument.getResource());
				Map<String, Boolean> actionMap = response.getActions();
				if (!Boolean.TRUE.equals(actionMap.get(argument.getAction().toString())))
					throw new UnauthorizedException();
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

	public Set<String> filterAuthorizedEnvironments(String sessionToken, AuthorizationAction action, Set<String> allEnvironments)
	{
		try {
			AuthorizationTreeQuery query = new AuthorizationTreeQuery();
			query.setResource(ENVIRONMENT_URL_ROOT);
			query.setApplication(secConfig.getPolicySetName());
			query.getSubject().setSsoToken(sessionToken);

			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.APPLICATION_JSON);
			headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
			headers.set(HEADER_ACCEPT_API_VERSION, secConfig.getApiVersionHeaderForEndpoint(ENDPOINT_POLICIES));
			headers.set(secConfig.getSessionTokenCookieName(), sessionToken);
			HttpEntity<AuthorizationTreeQuery> entity = new HttpEntity<>(query, headers);
			RestTemplate restTemplate = new RestTemplate();

			String url = secConfig.getSecurityManagementUrl() + secConfig.getRealm() + ACTION_EVAL_AUTHORITY_TREE;

			ResponseEntity<AuthorizationTreeResponse[]> httpResponse = restTemplate.exchange(url, HttpMethod.POST, entity, AuthorizationTreeResponse[].class);
			AuthorizationTreeResponse[] responses = httpResponse.getBody();

			// Map of authorization decisions for all environments, for which
			// one or more authorization policies have been established;
			// the map is keyed by Environment ID, and the corresponding
			// value represents outcome of authorization evaluation:
			Map<String, Boolean> authEnvironments = new HashMap<>();

			for (AuthorizationTreeResponse response : responses) {
				String[] urlComponents = response.getResource().split(ENVIRONMENT_URL_ROOT);
				if (urlComponents.length == 0)
					continue;

				String responseEnvironment = urlComponents[1];
				Map<String, Boolean> authorizations = response.getActions();
				Boolean isAuthorized = authorizations.get(action.toString());
				if (isAuthorized != null)
					authEnvironments.put(responseEnvironment, isAuthorized);
			}

			Set<String> responseEnvironments = new HashSet<>();
			Boolean isWildcardAuthorizationGranted = authEnvironments.get("*");

			for (String environment : allEnvironments) {
				Boolean isAuthorized = authEnvironments.get(environment);

				// Access to this environment is explicitly denied
				if (Boolean.FALSE.equals(isAuthorized))
					continue;

				// Access to this environment is explicitly granted
				else if (Boolean.TRUE.equals(isAuthorized))
					responseEnvironments.add(environment);

				// Access to the wildcard environment "*" is granted
				else if (Boolean.TRUE.equals(isWildcardAuthorizationGranted))
					responseEnvironments.add(environment);

				// Otherwise access to the environment is denied
			}

			return responseEnvironments;
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
}
