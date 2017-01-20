package com.csc.fsg.life.common.util;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/* Modifications: T0166, ENH1220 */

public class ApplicationContextUtils implements ApplicationContextAware {

	protected static ApplicationContext applicationContext = null;

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.springframework.context.ApplicationContextAware#setApplicationContext(org.springframework.context.ApplicationContext)
	 */
	public void setApplicationContext(ApplicationContext applicationContext)
			throws BeansException {
		this.applicationContext = applicationContext;
	}

	public static ApplicationContext getApplicationContext()
	{
		return applicationContext;
	}

	public static boolean containsBean(String name)	{
		if (applicationContext == null)
			return false;
		
		return applicationContext.containsBean(name);
	}

	public static Object getBean(String name) {
		Object bean = applicationContext.getBean(name);
		return bean;
	}

	public static <T> T getBean(Class<T> requiredType) {
		T bean = applicationContext.getBean(requiredType);
		return bean;
	}
}
