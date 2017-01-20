package com.csc.fsg.life.performance;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

import javax.inject.Inject;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.csc.fsg.life.biz.exception.BusinessException;
import com.csc.fsg.life.biz.service.Service;
import com.csc.fsg.life.biz.service.ServiceParam;
import com.csc.fsg.life.common.config.PerformanceLogEffectiveConfig;
import com.csc.fsg.life.common.util.ApplicationContextUtils;
import com.csc.fsg.life.context.UserContext;

/* Modifications: T0166, ENH1220 */

/**
 * Spring AOP interceptor class used to intercept public method calls in service
 * classes, and inject the performance logging logic.
 */
public class ServicePerformanceLogInterceptor
	implements MethodInterceptor
{
	protected static final Log log = LogFactory.getLog("com.csc.fsg");

	public ServicePerformanceLogInterceptor()
	{
		log.info("Initializing ServicePerformanceLogInterceptor...");
	}

	@Inject
	private PerformanceLogWriterService logInterceptorService = null;

	public Object invoke(MethodInvocation methodInvocation)
		throws Throwable
	{
		Method method = methodInvocation.getMethod();
		boolean isPublicMethod = Modifier.isPublic(method.getModifiers());

		UserContext userContext = null;
		PerformanceLogActivityStore activityStore = null;
		PerformanceLogActivity activity = null;
		PerformanceLogSegment segment = null;

		Class<?> clz = method.getDeclaringClass();
		boolean isPerformanceLogAware = isPublicMethod && Service.class.isAssignableFrom(clz);

		if (isPerformanceLogAware && isPerformanceLoggingEnabled()) {
			userContext = getUserContext(methodInvocation);
			activityStore = (PerformanceLogActivityStore) ApplicationContextUtils.getBean("performanceLogActivityStore");
			activity = getPerformanceLogActivity(userContext, activityStore);

			if (activity != null) {
				PerformanceLogSegmentOrigin origin = new PerformanceLogSegmentOrigin(methodInvocation);
				segment = activity.createServiceSegment(origin);
				segment.activate();
			}
		}

		try {
			return methodInvocation.proceed();
		}
		finally {
			if (segment != null) {
				segment.passivate();

				if (activity.isFirstSegment(segment)) {
					logPerformanceData(userContext, activity);
					activityStore.reset();
				}
			}
		}
	}

	private boolean isPerformanceLoggingEnabled()
	{
		PerformanceLogEffectiveConfig logConfigBean 
			= (PerformanceLogEffectiveConfig) ApplicationContextUtils.getBean("performanceLogEffectiveConfig");

		return logConfigBean != null && logConfigBean.isLoggingEnabledAtServiceLevel();
	}

	private UserContext getUserContext(MethodInvocation methodInvocation)
	{
		UserContext userContext = null;
		Object[] parametersType = methodInvocation.getArguments();

		if (parametersType != null) {
			for (int i = 0; i < parametersType.length; i++) {
				Object argument = parametersType[i];
				if (argument instanceof ServiceParam) {
					ServiceParam param = (ServiceParam) argument;
					userContext = param.getUserContext();
					break;
				}
			}
		}

		return userContext;
	}

	private PerformanceLogActivity getPerformanceLogActivity(UserContext userContext, PerformanceLogActivityStore activityStore)
	{
		String userId = null;
		String environment = null;

		if (userContext != null) {
			userId = userContext.getUserId();
			environment = userContext.getApplicationEnvironmentName();
		}

		if (userId == null || environment == null)
			return null;

		PerformanceLogActivity activity = null;
		if (activityStore.isEmpty()) {
			activity = new PerformanceLogActivity(userId, environment);
			activityStore.setActivity(activity);
		}
		else {
			activity = activityStore.getActivity();
		}

		return activity;
	}

	private void logPerformanceData(UserContext userContext, PerformanceLogActivity activity)
	{
		ServiceParam param = new ServiceParam();
		param.setUserContext(userContext);

		try {
			logInterceptorService.writePerformanceLogData(param, activity);
			activity.bookmarkLogLocation();
		}
		catch (BusinessException e) {
			log.error("Unable to log performance data: " + e.getMessage());
		}
	}
}
