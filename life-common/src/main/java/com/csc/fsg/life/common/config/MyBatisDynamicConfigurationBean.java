package com.csc.fsg.life.common.config;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.annotation.PostConstruct;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.ibatis.type.Alias;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.ClassPathScanningCandidateComponentProvider;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.core.type.filter.AnnotationTypeFilter;
import org.springframework.util.StringUtils;

/* Modifications: T0199 */

public class MyBatisDynamicConfigurationBean
{
	static private final Log log = LogFactory.getLog("com.csc.fsg");

	private String configLocationPattern = null;
	private String typeHandlersPackage = null;
	private List<String> typeAliasPackages = null;
	private List<String> mapperLocationPatterns = null;

	private Resource configLocation = null;
	private List<Class<?>> typeAliases = null;
	private List<Resource> mapperLocations = null;

	public void setConfigLocationPattern(String configLocationPattern)
	{
		this.configLocationPattern = configLocationPattern;
	}

	public void setTypeHandlersPackage(String typeHandlersPackage)
	{
		this.typeHandlersPackage = typeHandlersPackage;
	}

	public void setTypeAliasPackages(List<String> typeAliasPackages)
	{
		this.typeAliasPackages = typeAliasPackages;
	}

	public void setMapperLocationPatterns(List<String> mapperLocationPatterns)
	{
		this.mapperLocationPatterns = mapperLocationPatterns;
	}

	@PostConstruct
	private void discoverDynamicConfiguration()
	{
		if (StringUtils.hasText(configLocationPattern))
			configLocation = discoverConfiguration();

		if (typeAliasPackages != null && !typeAliasPackages.isEmpty())
			typeAliases = discoverTypeAliases();

		if (mapperLocationPatterns != null && !mapperLocationPatterns.isEmpty())
			mapperLocations = discoverMapperLocations();
	}

	private Resource discoverConfiguration()
	{
		ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
		Resource resource = resolver.getResource(configLocationPattern);
		return resource;
	}

	private List<Class<?>> discoverTypeAliases()
	{
		List<Class<?>> aliases = new ArrayList<>();

		ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
		ClassPathScanningCandidateComponentProvider scanner = new ClassPathScanningCandidateComponentProvider(false);
		scanner.addIncludeFilter(new AnnotationTypeFilter(Alias.class));

		try {
			for (String packageName : typeAliasPackages) {
				for (BeanDefinition bd : scanner.findCandidateComponents(packageName)) {
					String className = bd.getBeanClassName();
					aliases.add(classLoader.loadClass(className));
				}
			}
		}
		catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		log.debug("Discovered " + aliases.size() + " MyBatis annotated type aliases");
		return aliases;
	}

	private List<Resource> discoverMapperLocations()
	{
		List<Resource> locations = new ArrayList<>();

		try {
			ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
			for (String locationPattern : mapperLocationPatterns) {
				Resource[] resources = resolver.getResources(locationPattern);
				locations.addAll(Arrays.asList(resources));
			}
		}
		catch (IOException e) {
			e.printStackTrace();
		}

		log.debug("Discovered " + locations.size() + " MyBatis mapper locations");
		return locations;
	}

	public Resource getConfigLocation()
	{
		return configLocation;
	}

	public String getTypeHandlersPackage()
	{
		return typeHandlersPackage;
	}

	public Class<?>[] getTypeAliases()
	{
		if (typeAliases == null)
			return new Class<?>[0];
		else
			return typeAliases.toArray(new Class<?>[0]);
	}

	public Resource[] getMapperLocations()
	{
		if (mapperLocations == null)
			return new Resource[0];
		else
			return mapperLocations.toArray(new Resource[0]);
	}
}
