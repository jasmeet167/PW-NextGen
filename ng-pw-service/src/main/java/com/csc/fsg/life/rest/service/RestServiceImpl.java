package com.csc.fsg.life.rest.service;

import java.text.MessageFormat;
import java.util.ResourceBundle;

import org.springframework.util.StringUtils;

import com.csc.fsg.life.common.util.ApplicationContextUtils;

abstract public class RestServiceImpl
	implements RestService
{
	private ResourceBundle messages = null;

	protected RestServiceImpl()
	{
	}

	protected RestServiceImpl(String bundlePath)
	{
		if (StringUtils.hasText(bundlePath))
			messages = ResourceBundle.getBundle(bundlePath);
	}

	protected String getMessage(String key)
	{
		if (messages == null)
			return null;
		else
			return getMessage(key, null);
	}

	protected String getMessage(String key, Object param)
	{
		if (messages == null)
			return null;

		String message = messages.getString(key);
		if (param != null)
			message = MessageFormat.format(message, param);

		return message;
	}

	protected Object getBean(String name)
	{
		Object bean = ApplicationContextUtils.getBean(name);
		return bean;
	}

	protected <T> T getBean(Class<T> requiredType)
	{
		T bean = ApplicationContextUtils.getBean(requiredType);
		return bean;
	}
}
