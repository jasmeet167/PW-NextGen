package com.csc.fsg.life.biz.service;

import org.springframework.aop.TargetSource;
import org.springframework.aop.framework.autoproxy.BeanNameAutoProxyCreator;
 
/* Modifications: P0157 */

public class CustomBeanNameAutoproxyCreator 
	extends BeanNameAutoProxyCreator 
{
	private String jmxInterceptorClass;
	private String prefix;
	private CallableMBeanExporter exporter;
	
	@SuppressWarnings("unchecked")
	protected Object[] getAdvicesAndAdvisorsForBean(Class clazz, String beanName, TargetSource targetSource)
	{
		Object[] advices = super.getAdvicesAndAdvisorsForBean(clazz, beanName, targetSource);
		if(advices == PROXY_WITHOUT_ADDITIONAL_INTERCEPTORS) {
			Object interceptor;
			try {
				interceptor = Class.forName(getJmxInterceptorClass()).newInstance();
			}
			catch(ClassNotFoundException e) {
				logger.error("Cannot create interceptor - class not found:" + getJmxInterceptorClass());
				return advices;
			}
			catch(Exception e) {
				logger.error("Cannot create interceptor", e);
				return advices;
			}
			String oname = getPrefix() + ":" + "service=performanceMonitor,beanName=" + beanName;
			getExporter().registerBeanInstance(oname, interceptor);
			return new Object[] {interceptor};
		}
		else
			return advices;
	}

	public String getJmxInterceptorClass() {
		return jmxInterceptorClass;
	}

	public void setJmxInterceptorClass(String jmxInterceptorClass) {
		this.jmxInterceptorClass = jmxInterceptorClass;
	}

	public String getPrefix() {
		return prefix;
	}

	public void setPrefix(String prefix) {
		this.prefix = prefix;
	}

	public CallableMBeanExporter getExporter() {
		return exporter;
	}

	public void setExporter(CallableMBeanExporter exporter) {
		this.exporter = exporter;
	}

}
