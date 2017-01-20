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

/* Modifications: T0166 */

/**
 * This is a bean definition builder class for the performance log configuration
 * bean, when defined in the configuration file using Spring's custom namespace
 * mechanism.
 */
public class PerformanceLogBeanDefinitionParser
	extends AbstractSingleBeanDefinitionParser
{
	protected static Log log = LogFactory.getLog("com.csc.fsg");

	public static final String DEFAULT_ID = "performanceLogConfigBean";
	public static final Integer DEFAULT_LEVEL = Integer.valueOf(0);
	public static final String DEFAULT_SCHEMA = "PERFORM";
	public static final Long DEFAULT_MINIMUM_ELAPSED_TIME = Long.valueOf(0L);

	/**
	 * Return a class to be used to create and initialize an instance of
	 * performance log configuration bean.
	 * 
	 * @return A class to be used for building the configuration bean.
	 */
	@Override
	protected Class<PerformanceLogConfigBean> getBeanClass(@SuppressWarnings("unused") Element element)
	{
		return PerformanceLogConfigBean.class;
	}

	@Override
	protected void doParse(Element element, ParserContext parserContext, BeanDefinitionBuilder bean)
	{
		Integer level = DEFAULT_LEVEL;
		String levelAttribute = element.getAttribute("level");
		if (StringUtils.hasText(levelAttribute))
			level = Integer.valueOf(levelAttribute);
		bean.addPropertyValue("level", level);

		// Is this a reference to another defined datasource?
		String ref = element.getAttribute("datasourceRef");
		if (StringUtils.hasText(ref))
			bean.addPropertyReference("dataSource", ref);
		else {
			// Since there is no datasource reference, look for jndi name
			String jndiName = element.getAttribute("jndiName");
			if (StringUtils.hasText(jndiName)) {
				BeanDefinitionBuilder datasource = BeanDefinitionBuilder.rootBeanDefinition(JndiObjectFactoryBean.class);
				datasource.addPropertyValue("jndiName", jndiName);
				bean.addPropertyValue("dataSource", datasource.getBeanDefinition());
			}
			else if (level.intValue() > 0) {
				parserContext.getReaderContext().error("No performance log datasource configured - must use either 'datasourceRef' or 'jndiName'", element);
				return;
			}
		}

		// Optional attributes
		String schema = element.getAttribute("performanceSchema");
		if (StringUtils.hasText(schema))
			bean.addPropertyValue("performanceSchema", schema);
		else
			bean.addPropertyValue("performanceSchema", DEFAULT_SCHEMA);

		String minimumElapsedTime = element.getAttribute("minimumElapsedTime");
		if (StringUtils.hasText(minimumElapsedTime))
			bean.addPropertyValue("minimumElapsedTime", Long.valueOf(minimumElapsedTime));
		else
			bean.addPropertyValue("minimumElapsedTime", DEFAULT_MINIMUM_ELAPSED_TIME);
	}

	@Override
	protected String resolveId(Element element, AbstractBeanDefinition definition, ParserContext parserContext)
		throws BeanDefinitionStoreException
	{
		String id = super.resolveId(element, definition, parserContext);
		if (!StringUtils.hasText(id))
			id = DEFAULT_ID;
		return id;
	}
}