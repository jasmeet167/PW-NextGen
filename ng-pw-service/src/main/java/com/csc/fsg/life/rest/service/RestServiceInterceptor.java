package com.csc.fsg.life.rest.service;

import java.lang.reflect.Method;
import java.util.ResourceBundle;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.csc.fsg.life.rest.annotation.SecuredMethod;
import com.csc.fsg.life.rest.exception.ForbiddenException;
import com.csc.fsg.life.rest.param.AuthorizationAction;
import com.csc.fsg.life.rest.param.RestServiceParam;

public class RestServiceInterceptor
	implements MethodInterceptor
{
	private final Log log = LogFactory.getLog("com.csc.fsg");

	private ResourceBundle messages = null;

	@Autowired
	private SecurityService securityService = null;

	public RestServiceInterceptor()
	{
		log.info("Initializing RestServiceInterceptor...");
		messages = ResourceBundle.getBundle("com.csc.fsg.life.rest.service.RestServiceInterceptor");
	}

	/**
	 * This method examines annotation of type {@code SecuredMethod} at the
	 * invoked method level. If one is not found, then the security aspect is
	 * bypassed. Otherwise security processing is enabled. In such case,
	 * presence of an argument of type {@code RestServiceParam} is required in
	 * the invoked method.
	 * <p>
	 * For authentication purposes, only non-empty value of the property
	 * <em>authToken</em> is required in the provided instance of
	 * {@code RestServiceParam}. It will be used to verify that the user has
	 * been successfully authenticated, and the session remains active. In such
	 * case the annotations need not have any arguments.
	 * 
	 * @see MethodInterceptor#invoke(MethodInvocation)
	 * @see SecuredMethod
	 * @see RestServiceParam
	 */
	public Object invoke(MethodInvocation invocation)
		throws Throwable
	{
		Method method = invocation.getMethod();
		SecuredMethod annotation = method.getAnnotation(SecuredMethod.class);
		if (annotation != null)
			processSecurityAspect(invocation, annotation.action());

		Object result = invocation.proceed();
		return result;
	}

	private void processSecurityAspect(MethodInvocation invocation, AuthorizationAction action)
	{
		RestServiceParam param = getRestServiceParam(invocation);
		if (param == null) {
			log.error(messages.getString("missing_service_param"));
			throw new ForbiddenException();
		}

		securityService.validateSession(param.getAuthToken());
		securityService.assertAuthorization(param, action);
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
}
