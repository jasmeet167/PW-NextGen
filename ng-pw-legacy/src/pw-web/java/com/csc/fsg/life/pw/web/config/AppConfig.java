package com.csc.fsg.life.pw.web.config;

import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.context.ApplicationContext;

import com.csc.fsg.life.avm.environments.AVMConfigBean;
import com.csc.fsg.life.common.util.ApplicationContextUtils;
import com.csc.fsg.life.pw.web.environment.EnvProperties;

/** Modifications: T0113 */

public class AppConfig {

	public static ApplicationContext getContext() {
		return ApplicationContextUtils.getApplicationContext();
	}
	
	public static AVMConfigBean getAvmBean() {
		return (AVMConfigBean) getContext().getBean("AVMConfigBean");
	}

	public static EnvProperties getEnvProps(String envId) {
		EnvProperties props = null;
		try{
			props = (EnvProperties)getContext().getBean(envId+"Props");
		}catch (NoSuchBeanDefinitionException e) {
			props = (EnvProperties)getContext().getBean("envProps");
		}
		return props;	
	}
}
