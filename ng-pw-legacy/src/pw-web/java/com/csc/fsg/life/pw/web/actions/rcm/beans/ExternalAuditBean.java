
package com.csc.fsg.life.pw.web.actions.rcm.beans;

import javax.sql.DataSource;

import org.springframework.beans.factory.NoSuchBeanDefinitionException;

import com.csc.fsg.life.pw.web.config.AppConfig;

/* Modifications: T0129 */


public class ExternalAuditBean {

	private String envPrefix = null; 
	private String schema = null;
	private DataSource dataSource = null;
	private int viewLimit = 50000;
	
    
	private ExternalAuditBean() {
	}
	
	public static ExternalAuditBean getInstance() {
		ExternalAuditBean bean;
		try {
			bean = (ExternalAuditBean) AppConfig.getContext().getBean("ExtAuditBean");
		} catch (NoSuchBeanDefinitionException ex) {
			bean = null;
		}
		return bean;
	}

	public DataSource getDataSource() {
		return dataSource;
	}
	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}
	public String getEnvPrefix() {
		return envPrefix;
	}
	public void setEnvPrefix(String envPrefix) {
		this.envPrefix = envPrefix;
	}
	public String getSchema() {
		return schema;
	}
	public void setSchema(String schema) {
		this.schema = schema;
	}
	public int getViewLimit() {
		return viewLimit;
	}
	public void setViewLimit(int viewLimit) {
		this.viewLimit = viewLimit;
	}
}
