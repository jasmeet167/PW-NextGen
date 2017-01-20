package com.csc.fsg.life.biz.service;

import org.springframework.jmx.export.MBeanExportException;
import org.springframework.jmx.export.MBeanExporter;

/* Modifications: P0157 */

public class CallableMBeanExporter extends MBeanExporter {
	
	public void registerBeanInstance(String oname, Object bean)
	{
		try
		{
			registerBeanNameOrInstance(bean, oname);
		}
		catch(MBeanExportException e)
		{
			logger.warn("Unable to register bean " + oname, e);
		}
	}
	
	@Override
	public void afterPropertiesSet() {
		
	}
	
}
