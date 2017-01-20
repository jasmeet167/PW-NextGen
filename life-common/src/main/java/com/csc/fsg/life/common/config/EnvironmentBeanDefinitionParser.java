package com.csc.fsg.life.common.config;

import org.apache.commons.dbcp2.BasicDataSource;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.support.AbstractBeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.ManagedMap;
import org.springframework.beans.factory.xml.AbstractSingleBeanDefinitionParser;
import org.springframework.jndi.JndiObjectFactoryBean;
import org.springframework.util.StringUtils;
import org.springframework.util.xml.DomUtils;
import org.w3c.dom.Element;

import com.csc.fsg.life.biz.service.ServiceManager;
import com.csc.fsg.life.biz.service.TCPIPConnection;

/* Modifications: P0158, T0150, T0160, T0199, T0196 */

/**
 * This class is responsible for parsing the "config" Spring custom namespace and
 * building an environment definition.
 */
public class EnvironmentBeanDefinitionParser extends AbstractSingleBeanDefinitionParser {

	/** logger for the EnvironmentBeanDefinitionParser class. */
    protected static final Log log = LogFactory.getLog("com.csc.fsg");
    
    @Override
	protected Class<MyBatisApplicationEnvironmentBean> getBeanClass(Element element)
	{
		return MyBatisApplicationEnvironmentBean.class;
	}
	
    @Override
	protected void doParse(Element element, BeanDefinitionBuilder bean)
	{
		String id = element.getAttribute("id");
		
		// if no "name" is specified, use the id
		String name = element.getAttribute("name");
		if (StringUtils.hasText(name))
			bean.addPropertyValue("name", name);
		else
			bean.addPropertyValue("name", id);
		
		// if no "databaseId" is specified, use the id
		String databaseId = element.getAttribute("databaseId");
		if (!StringUtils.hasText(databaseId)) {
			if (id.length() > 8) {
				log.warn("Trimming databaseId for environment: " + id);
				databaseId = id.substring(0, 8);
			} else
				databaseId = id;
		}
		bean.addPropertyValue("dataBaseId", databaseId);
		
		// if no "displayName" is specified, use the id
		String displayName = element.getAttribute("displayName");
		if (StringUtils.hasText(displayName))
			bean.addPropertyValue("displayName", displayName);
		else
			bean.addPropertyValue("displayName", id);
		
		Boolean isDebugAllowed = Boolean.FALSE;
		String debugAllowed = element.getAttribute("debugAllowed");
		if (StringUtils.hasText(debugAllowed)) {
			isDebugAllowed = Boolean.valueOf(debugAllowed);
			bean.addPropertyValue("debugAllowed", isDebugAllowed);
		}

		String defaultLogSwitch = element.getAttribute("defaultLogSwitch");
		if (StringUtils.hasText(defaultLogSwitch)) {
			//if (!isDebugAllowed)
			//	log.warn("Debugging not allowed in this environment...ignoring defaultLogSwitch attribute.");
			//else
				bean.addPropertyValue("defaultLogSwitch", defaultLogSwitch);
		}
		
		BeanDefinitionBuilder data = BeanDefinitionBuilder.rootBeanDefinition(EnvDbInfoBean.class);
		data.addPropertyValue("tableType", "data");
		data.addPropertyValue("schema", element.getAttribute("dataSchema"));
		data.addPropertyValue("defaultDb", Boolean.valueOf(true));
		
		String dataFile = element.getAttribute("dataFile");
		if (StringUtils.hasText(dataFile)) {
			data.addPropertyValue("name", dataFile);
			data.addPropertyValue("displayName", dataFile);
		}
		
		BeanDefinitionBuilder rules = BeanDefinitionBuilder.rootBeanDefinition(EnvDbInfoBean.class);
		rules.addPropertyValue("tableType", "rule");
		rules.addPropertyValue("defaultDb", Boolean.valueOf(true));
		rules.addPropertyValue("schema", element.getAttribute("rulesSchema"));
		
		String rulesFile = element.getAttribute("rulesFile");
		if (StringUtils.hasText(rulesFile)) {
			rules.addPropertyValue("name", rulesFile);
			rules.addPropertyValue("displayName", rulesFile);
		}
		
		ManagedMap<String,AbstractBeanDefinition> map = new ManagedMap<String,AbstractBeanDefinition>();
		map.put("data", data.getBeanDefinition());
		map.put("rules", rules.getBeanDefinition());
		bean.addPropertyValue("envDbInfo", map);		
		
		// check for optional "archivedEnv" reference
		String archivedEnv = element.getAttribute("archivedEnv");
		if (StringUtils.hasText(archivedEnv))
			bean.addPropertyValue("archivedEnv", Boolean.valueOf(archivedEnv));
		
		// check for optional "archivedDbEnv" reference
		String archivedDbEnv = element.getAttribute("archivedDbEnv");
		if (StringUtils.hasText(archivedDbEnv))
			bean.addPropertyReference("archivedDbEnv", archivedDbEnv);
		
		// check for optional "converter" reference
		String converter = element.getAttribute("converter");
		if (StringUtils.hasText(converter))
			bean.addPropertyReference("converter", converter);	
		
		parseDatasourceElement(DomUtils.getChildElementByTagName(element, "datasource"), element, bean);
		parseConnectionElement(DomUtils.getChildElementByTagName(element, "connection"), element, bean);
		
		String hvEncoding = element.getAttribute("highValueEncoding");
		if (StringUtils.hasText(hvEncoding)) 
			bean.addPropertyValue("highValueEncoding", hvEncoding);
	}

	private void parseDatasourceElement(Element element, Element parentElement, BeanDefinitionBuilder parent)
	{			
		// is this a reference to another defined datasource?
		String ref = element.getAttribute("ref");
		if (StringUtils.hasText(ref)) {
			parent.addPropertyReference("jndiDataSource", ref);
			return;
		}
		
		BeanDefinitionBuilder datasource = null;
		
		// is this a reference to a JNDI datasource?
		String jndiName = element.getAttribute("jndiName");
		if (StringUtils.hasText(jndiName)) {
			datasource = BeanDefinitionBuilder.rootBeanDefinition(JndiObjectFactoryBean.class);
			datasource.addPropertyValue("jndiName", jndiName);
		} else {
			datasource = BeanDefinitionBuilder.rootBeanDefinition(BasicDataSource.class);
			datasource.addPropertyValue("driverClassName", element.getAttribute("driverClassName"));
			datasource.addPropertyValue("url", element.getAttribute("url"));
			datasource.addPropertyValue("username", element.getAttribute("username"));
			datasource.addPropertyValue("password", element.getAttribute("password"));
			datasource.addPropertyValue("validationQuery", element.getAttribute("validationQuery"));
		}
		
		parent.addPropertyValue("jndiDataSource", datasource.getBeanDefinition());
	}
	
	private void parseConnectionElement(Element element, Element parentElement, BeanDefinitionBuilder parent)
	{		
		// build the service manager instance for this environment
		String serviceManagerClass = parentElement.getAttribute("serviceManager");
		BeanDefinitionBuilder serviceManager = null;
		if (StringUtils.hasText(serviceManagerClass))
			serviceManager = BeanDefinitionBuilder.rootBeanDefinition(serviceManagerClass);
		else
			serviceManager = BeanDefinitionBuilder.rootBeanDefinition(ServiceManager.class);
		serviceManager.addPropertyValue("systemId", element.getAttribute("systemId"));
		serviceManager.addPropertyReference("copyObjectMap", "copyObjectMap");
		
		// check for optional attribute "deleteRecords"
		String deleteRecords = element.getAttribute("deleteRecords");
		if (StringUtils.hasText(deleteRecords))
			serviceManager.addPropertyValue("deleteRecords", deleteRecords);
		
		// build the connection information
		BeanDefinitionBuilder connection = BeanDefinitionBuilder.rootBeanDefinition(TCPIPConnection.class);
		connection.addPropertyValue("ipAddress", element.getAttribute("ipAddress"));
		connection.addPropertyValue("port", element.getAttribute("port"));
		connection.addPropertyValue("transactionId", element.getAttribute("transactionId"));
		String timeout = element.getAttribute("timeout");
		if (StringUtils.hasText(timeout))
			connection.addPropertyValue("timeout", timeout);
		serviceManager.addPropertyValue("connection", connection.getBeanDefinition());
		
		parent.addPropertyValue("serviceManager", serviceManager.getBeanDefinition());
	}
}
