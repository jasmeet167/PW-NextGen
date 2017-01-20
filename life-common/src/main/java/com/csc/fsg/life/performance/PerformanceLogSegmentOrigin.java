package com.csc.fsg.life.performance;

import java.io.Serializable;
import java.lang.reflect.Method;

import org.aopalliance.intercept.MethodInvocation;

/* Modifications: T0166 */

/**
 * This is a convenience class serving as container for information defining the
 * point of origin of a performance log segment.
 */
public class PerformanceLogSegmentOrigin
	implements Serializable
{
	private static final long serialVersionUID = 1624372712352657819L;

	private String enclosingClassName = null;
	private String declaringClassName = null;
	private String methodName = null;

	public PerformanceLogSegmentOrigin(MethodInvocation methodInvocation)
	{
		this.enclosingClassName = methodInvocation.getThis().getClass().getName();

		Method method = methodInvocation.getMethod();
		this.declaringClassName = method.getDeclaringClass().getName();
		this.methodName = method.getName();
	}

	public PerformanceLogSegmentOrigin(String enclosingClassName, String declaringClassName, String methodName)
	{
		this.enclosingClassName = enclosingClassName;
		this.declaringClassName = declaringClassName;
		this.methodName = methodName;
	}

	public String getEnclosingClassName()
	{
		return enclosingClassName;
	}

	public String getDeclaringClassName()
	{
		return declaringClassName;
	}

	public String getMethodName()
	{
		return methodName;
	}
}
