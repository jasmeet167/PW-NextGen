package com.csc.fsg.life.common.config;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.BeanDefinitionStoreException;
import org.springframework.beans.factory.support.AbstractBeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.xml.AbstractSingleBeanDefinitionParser;
import org.springframework.beans.factory.xml.ParserContext;
import org.springframework.jndi.JndiObjectFactoryBean;
import org.springframework.util.StringUtils;
import org.w3c.dom.Element;

import com.csc.fsg.life.avm.environments.AVMConfigBean;

/* Modifications: T0143 */

/**
 * This class is responsible for parsing the "config" Spring custom namespace and
 * building an AVM definition.
 */
public class AVMBeanDefinitionParser extends AbstractSingleBeanDefinitionParser {

	/** logger for the EnvironmentBeanDefinitionParser class. */
    protected static Log log = LogFactory.getLog("com.csc.fsg");
    
	// defaults
	public static final boolean DEFAULT_ENABLED = true;
	public static final Boolean DEFAULT_PAGE_DATA_UTILIZED = Boolean.FALSE;
	
	public static final String DEFAULT_ID = "AVMConfigBean"; 
	public static final String DEFAULT_SCHEMA = "AVM"; 
	public static final String DEFAULT_STATUS = "ON"; 
	public static final String DEFAULT_PERIOD = "30000"; 
	public static final String DEFAULT_DELAY = "30000"; 

    @Override
	protected Class<AVMConfigBean> getBeanClass(Element element)
	{
		return AVMConfigBean.class;
	}

	@Override
	protected void doParse(Element element, ParserContext parserContext, BeanDefinitionBuilder bean)
	{
		boolean isEnabled = DEFAULT_ENABLED;
		String isEnabledAttribute = element.getAttribute("enabled");
		if (StringUtils.hasText(isEnabledAttribute))
			isEnabled = Boolean.valueOf(isEnabledAttribute).booleanValue();
		bean.addPropertyValue("enabled", Boolean.valueOf(isEnabled));

		String applicationId = element.getAttribute("applicationId");
		if (StringUtils.hasText(applicationId)) {
			bean.addPropertyValue("applicationId", applicationId);
		}
		else if (isEnabled) {
			parserContext.getReaderContext().error("No Application Id configured - must specify the attribute 'applicationId'", element);
			return;
		}

		String environmentId = element.getAttribute("environmentId");
		if (StringUtils.hasText(environmentId)) {
			bean.addPropertyValue("environmentId", environmentId);
		}
		else if (isEnabled) {
			parserContext.getReaderContext().error("No Environment Id configured - must specify the attribute 'environmentId'", element);
			return;
		}

		String serverLink = element.getAttribute("serverLink");
		bean.addPropertyValue("avmServerlink", serverLink);
		
		// is this a reference to another defined datasource?
		String ref = element.getAttribute("datasourceRef");
		if (StringUtils.hasText(ref))
			bean.addPropertyReference("dataSource", ref);
		else {
			// since there is no datasource reference, look for jndi name
			String jndiName = element.getAttribute("jndiName");
			if (StringUtils.hasText(jndiName)) {
				BeanDefinitionBuilder datasource = 
					BeanDefinitionBuilder.rootBeanDefinition(JndiObjectFactoryBean.class);
				datasource.addPropertyValue("jndiName", jndiName);
				bean.addPropertyValue("dataSource", datasource.getBeanDefinition());
			} else if (isEnabled) {
				parserContext.getReaderContext().error("No AVM datasource configured - must use either 'datasourceRef' or 'jndiName'", element);
				return;
			}
		}
		
		// optional attributes
		String schema = element.getAttribute("schema");
		if (StringUtils.hasText(schema))
			bean.addPropertyValue("schema", schema);
		else
			bean.addPropertyValue("schema", DEFAULT_SCHEMA);
		
		String status = element.getAttribute("status");
		if (StringUtils.hasText(status))
			bean.addPropertyValue("status", status);
		else
			bean.addPropertyValue("status", DEFAULT_STATUS);
		
		String accessCacheStatus = element.getAttribute("accessCacheStatus");
		if (StringUtils.hasText(accessCacheStatus))
			bean.addPropertyValue("accessCacheStatus", status);
		else
			bean.addPropertyValue("accessCacheStatus", DEFAULT_STATUS);
		
		String delay = element.getAttribute("delay");
		if (StringUtils.hasText(delay))
			bean.addPropertyValue("delay", delay);
		else
			bean.addPropertyValue("delay", DEFAULT_DELAY);
		
		String period = element.getAttribute("period");
		if (StringUtils.hasText(period))
			bean.addPropertyValue("period", period);
		else
			bean.addPropertyValue("period", DEFAULT_PERIOD);

		String isPageDataUtilized = element.getAttribute("pageDataUtilized");
		if (StringUtils.hasText(isPageDataUtilized))
			bean.addPropertyValue("pageDataUtilized", Boolean.valueOf(isPageDataUtilized));
		else
			bean.addPropertyValue("pageDataUtilized", DEFAULT_PAGE_DATA_UTILIZED);
	}
	
	@Override
	protected String resolveId(Element element,
			AbstractBeanDefinition definition, ParserContext parserContext)
			throws BeanDefinitionStoreException {
		String id = super.resolveId(element, definition, parserContext);
		if (!StringUtils.hasText(id))
			id = DEFAULT_ID;
		return id;
	}
}