package com.csc.fsg.life.common.util;

import java.io.Serializable;
import java.lang.reflect.Method;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.csc.fsg.life.biz.exception.BusinessException;

/* Modifications: ENHT0085, T0140 */

/**
 * This class will intercept all method calls on all services and log service
 * access and timing metrics.
 */
public class TimerManagerInterceptor 
	implements MethodInterceptor,Serializable
{	
    /** Logger for the Service entry/exit. **/
    protected static Log log = LogFactory.getLog("com.csc.fsg");

	/** Constant for service time. **/
	private static final String SERVICE = "ServiceTotal";

	/**
	   Called when a method on a BusinessService is invoked. Throws a SecurityException
	   if the user does not have right to execute the service call.
	**/
    @SuppressWarnings("unchecked")
	public Object invoke(MethodInvocation methodInvocation)
		throws Throwable
    {
		// get the details of this method invocation
		Method method = methodInvocation.getMethod();
		Class clz = method.getDeclaringClass();

		// Ignore non-CSC classes.
		if (!clz.getName().startsWith("com.csc"))
			return methodInvocation.proceed();

		// Get the service ID, increment, and store the updated value.
		Long id = (Long)TimerManager.getProperty("SERVICE_ID_COUNTER");
		if (id == null)
			id = Long.valueOf(1);
		else
			id = Long.valueOf(id.longValue() + 1);
		TimerManager.setProperty("SERVICE_ID_COUNTER", id);
		
		String idSuffix = "." + id;
		TimerManager.logAttribute("Service" + idSuffix, clz.getName() + "." + method.getName());
		TimerManager.startTimer(SERVICE + idSuffix);
		try {
			return methodInvocation.proceed();
		}
		catch(Throwable t) {
			String msg = "";
			if (t instanceof BusinessException)
				msg = ((BusinessException)t).getMessage();
			else
				msg = t.toString(); 
			if ((msg != null) && (msg.indexOf("\n") != -1))
				msg = msg.substring(0, msg.indexOf("\n"));
			TimerManager.logAttribute("ServiceException", msg);
			throw t;
		}
		finally {
			TimerManager.endTimer(SERVICE + idSuffix);
		}
    }	
}
