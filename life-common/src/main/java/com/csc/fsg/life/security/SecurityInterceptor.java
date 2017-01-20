package com.csc.fsg.life.security;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

import javax.inject.Inject;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.csc.fsg.life.biz.service.ServiceParam;
import com.csc.fsg.life.context.UserContext;
import com.csc.fsg.life.security.authorization.permissions.PermissionsList;
import com.csc.fsg.life.security.authorization.resourceidentity.ServiceMethodAclIdentity;

/* Modifications: ENHT0085, T0135, ENH1220 */

/**
 * Spring AOP interceptor class used to intercept public method calls in service
 * classes, and weave in the security advice.
 */
public class SecurityInterceptor
	implements MethodInterceptor
{
	protected static final Log log = LogFactory.getLog("com.csc.fsg");

	@Inject
	SecurityManager securityManager = null;

	public SecurityInterceptor()
	{
		log.info("Initializing SecurityInterceptor...");
	}

	/**
	 * Spring AOP interceptor class used to intercept public method calls in
	 * service classes, and weave in the authorization check logic.
	 * <p>
	 * If user is not authorized, {@code SecurityException} is thrown.
	 */
	public Object invoke(MethodInvocation methodInvocation)
		throws Throwable
	{
		Method method = methodInvocation.getMethod();
		if (Modifier.isPublic(method.getModifiers())) {
			ServiceParam param = null;

			Object[] arguments = methodInvocation.getArguments();
			if (arguments != null) {
				for (Object argument : arguments) {
					if (argument instanceof ServiceParam) {
						param = (ServiceParam) argument;
						break;
					}
				}
			}
			if (param != null)
				checkAuthorization(param, method);
		}

		return methodInvocation.proceed();
	}

	private void checkAuthorization(ServiceParam param, Method method)
		throws SecurityException
	{
		Class<?> clz = method.getDeclaringClass();
		String className = clz.getCanonicalName();

		String methodDescription = method.toString();
		String[] methodDescriptionElements = methodDescription.split(className + "\\.|\\s+throws");
		String methodSignature = methodDescriptionElements[1];

		UserContext userContext = param.getUserContext();
		ServiceMethodAclIdentity aclIdentity = ServiceMethodAclIdentity.getInstance(userContext, className, methodSignature);
		boolean isAuthorized = securityManager.hasAuthority(userContext, aclIdentity, PermissionsList.GENERAL);
		logAuthorizationResult(userContext, className, methodSignature, isAuthorized);

		if (!isAuthorized)
			throw new SecurityException("User not authorized to perform this transaction");
	}

	private void logAuthorizationResult(UserContext userContext, String className, String methodSignature, boolean isAuthorized)
	{
		if (log.isDebugEnabled()) {
			String userId = userContext.getUserId();
			userId = (userId == null) ? "" : userId.trim();
			log.debug("Service authorization results: "
					 + userId + ":"
					 + className + ":"
					 + methodSignature
					 + ": isAccessGranted="
					 + isAuthorized);
		}
	}
}
