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

/* Modifications: ENH1019 */

/**
 * This class is responsible for parsing the "config" Spring custom namespace and
 * building an USI definition.
 */
public class USIBeanDefinitionParser extends AbstractSingleBeanDefinitionParser {

	/** logger for the EnvironmentBeanDefinitionParser class. */
    protected static Log log = LogFactory.getLog("com.csc.fsg");
    
	public static final String DEFAULT_ID = "USIConfigBean"; 
	public static final String DEFAULT_SCHEMA = "USI";

    @Override
	protected Class<USIConfigBean> getBeanClass(Element element)
	{
		return USIConfigBean.class;
	}

	@Override
	protected void doParse(Element element, ParserContext parserContext, BeanDefinitionBuilder bean)
	{
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
			}
		}
		
		// optional attributes
		String schema = element.getAttribute("schema");
		if (StringUtils.hasText(schema))
			bean.addPropertyValue("schema", schema);
		else
			bean.addPropertyValue("schema", DEFAULT_SCHEMA);
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