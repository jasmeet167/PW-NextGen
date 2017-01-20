package com.csc.fsg.life.rest.service;

import java.lang.reflect.Method;
import java.text.MessageFormat;
import java.util.HashSet;
import java.util.ResourceBundle;
import java.util.Set;

import javax.inject.Inject;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.csc.fsg.life.openam.model.AuthorizationArgument;
import com.csc.fsg.life.rest.annotation.AuthorizationAction;
import com.csc.fsg.life.rest.annotation.SecuredMethod;
import com.csc.fsg.life.rest.annotation.SecuredMethods;
import com.csc.fsg.life.rest.param.RestServiceParam;

public class RestServiceInterceptor
	implements MethodInterceptor
{
	private final Log log = LogFactory.getLog("com.csc.fsg");

	private ResourceBundle messages = null;

	@Inject
	private SecurityService securityService = null;

	public RestServiceInterceptor()
	{
		log.info("Initializing RestServiceInterceptor...");
		messages = ResourceBundle.getBundle("com.csc.fsg.life.rest.service.RestServiceInterceptor");
	}

	/**
	 * This method examines annotations of type {@code SecuredMethod} at the
	 * invoked method level. If none are found, then the security aspect is
	 * bypassed. If on the other hand one or more are present, security
	 * processing will be enabled. In such case, presence of an argument of type
	 * {@code RestServiceParam} is required in the invoked method.
	 * <p>
	 * For authentication purposes, only non-empty value of the property
	 * <em>sessionToken</em> is required in the provided instance of
	 * {@code RestServiceParam}. It will be used to verify that the user has
	 * been successfully authenticated, and the session remains active. In such
	 * case the annotations need not have any arguments, and the property
	 * <em>authArguments</em> in {@code RestServiceParam} may be empty.
	 * <p>
	 * To trigger evaluation of authorization policies, information about
	 * resources, access to which is to be requested, and the corresponding
	 * actions, must be provided. This can be done either through properties of
	 * one or more annotations {@code SecuredMethod}, or through contents of he
	 * property <em>authArguments</em> in {@code RestServiceParam}, or through
	 * combination of both.
	 * <p>
	 * If multiple resources and/or access methods are used, access to each will
	 * be evaluated separately first, and then conjunction of all results will
	 * be evaluated and returned. Consequently, access will be denied unless the
	 * user is authorized to access each resources in the list, using the
	 * correposnding provided action.
	 */
	public Object invoke(MethodInvocation invocation)
		throws Throwable
	{
		Set<AuthorizationArgument> authArguments = new HashSet<>();
		Method method = invocation.getMethod();

		SecuredMethod annotation = method.getAnnotation(SecuredMethod.class);
		if (annotation != null)
			processAuthAnnotation(method, authArguments, annotation);

		SecuredMethods repeatingAnnotations = method.getAnnotation(SecuredMethods.class);
		if (repeatingAnnotations != null)
			for (SecuredMethod repeatingAnnotation : repeatingAnnotations.value())
				processAuthAnnotation(method, authArguments, repeatingAnnotation);

		if (annotation != null || repeatingAnnotations != null)
			completeSecurityAspectProcessing(invocation, authArguments);

		Object result = invocation.proceed();
		return result;
	}

	private void processAuthAnnotation(Method method, Set<AuthorizationArgument> authArguments, SecuredMethod annotation)
	{
		String resource = annotation.resource();
		AuthorizationAction action = annotation.action();
		AuthorizationArgument argument = new AuthorizationArgument(resource, action);

		processAuthArgument(method, authArguments, argument);
	}

	private void processAuthArgument(Method method, Set<AuthorizationArgument> authArguments, AuthorizationArgument authArgument)
	{
		if (authArgument.isEmpty())
			return;

		if (authArgument.isConsistent()) {
			authArguments.add(authArgument);
		}
		else {
			String message = messages.getString("inconsistent_information");
			message = MessageFormat.format(message, method.getDeclaringClass(), method.getName(), authArgument.toString());
			log.warn(message);
		}
	}

	private void completeSecurityAspectProcessing(MethodInvocation invocation, Set<AuthorizationArgument> authArguments)
	{
		RestServiceParam param = getRestServiceParam(invocation);
		String sessionToken = null;

		if (param != null) {
			sessionToken = param.getSessionToken();

			Method method = invocation.getMethod();
			for (AuthorizationArgument authArgument : param.getAuthorizationArguments())
				processAuthArgument(method, authArguments, authArgument);
		}

		checkAuthorization(sessionToken, authArguments);
	}

	private RestServiceParam getRestServiceParam(MethodInvocation invocation)
	{
		RestServiceParam param = null;
		Object[] arguments = invocation.getArguments();

		if (arguments != null) {
			for (Object argument : arguments) {
				if (argument instanceof RestServiceParam) {
					param = (RestServiceParam) argument;
					break;
				}
			}
		}

		return param;
	}

	private void checkAuthorization(String sessionToken, Set<AuthorizationArgument> authArguments)
	{
		securityService.validateSession(sessionToken);
		if (!authArguments.isEmpty())
			securityService.assertAuthority(sessionToken, authArguments.toArray(new AuthorizationArgument[0]));
	}
}