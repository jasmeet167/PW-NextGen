package com.csc.fsg.life.common.util;

import java.util.MissingResourceException;
import java.util.ResourceBundle;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/* Modifications: T0150 */

/**
 * A utility class to access build properties generated and placed on the
 * classpath by the build process. Values of these properties are then made
 * available through a set of specialized methods.
 */
public class BuildProperties
{
	private static final Log log = LogFactory.getLog("com.csc.fsg");

	private static final String BUNDLE_BASE_NAME = "com.csc.fsg.life.build.build";
	private static final String APP_DESIGNATOR_KEY = "app.designator";
	private static final String APP_VERSION_KEY = "app.version";
	private static final String BUILD_TIMESTAMP_KEY = "build.timestamp";

	/** The singleton instance of this class */
	private static BuildProperties buildProperties = null;

	/** Resource bundle containing build properties */
	private ResourceBundle buildPropertiesBundle = null;

	/**
	 * Private constructor is used to load the bundle containing build
	 * properties.
	 */
	private BuildProperties()
	{
		try {
			buildPropertiesBundle = ResourceBundle.getBundle(BUNDLE_BASE_NAME);
			log.info("Load of build properties bundle has been succesful");
		}
		catch (MissingResourceException e) {
			log.error("Load of build properties bundle failed: " + e.getMessage());
			buildProperties = null;
		}
	}

	/**
	 * Factory method; when invoked the first time it will create an instance of
	 * this class, and then will always return a reference to this instance.
	 */
	public static BuildProperties getInstance()
	{
		if (buildProperties == null)
			buildProperties = new BuildProperties();

		return buildProperties;
	}

	/**
	 * Return standard build identifier information in format suitable for
	 * display. The returned identifier contains the following components:
	 * <ul>
	 * <li>Application Designator</li>
	 * <li>Application Version</li>
	 * <li>Build Timestamp</li>
	 * </ul>
	 * 
	 * @return Standard build identifier in format suitable for display.
	 */
	public String getBuildIdentifier()
	{
		StringBuilder buf = new StringBuilder();

		String appDesignator = getAppDesignator();
		if (appDesignator != null)
			buf.append(appDesignator);

		buf.append('-');

		String appVersion = getAppVersion();
		if (appVersion != null)
			buf.append(appVersion);

		buf.append(' ');

		String buildTimestamp = getBuildTimestamp();
		if (buildTimestamp != null)
			buf.append(buildTimestamp);

		return buf.toString();
	}

	/**
	 * Return value of the property <code>app.designator</code> loaded from the
	 * build properties bundle.
	 * 
	 * @return Value of the property <code>app.designator</code> loaded from the
	 *         build properties bundle. If bundle was not loaded for any reason,
	 *         or if the property is not found, then return <code>null</code>.
	 */
	public String getAppDesignator()
	{
		return getProperty(APP_DESIGNATOR_KEY);
	}

	/**
	 * Return value of the property <code>app.version</code> loaded from the
	 * build properties bundle.
	 * 
	 * @return Value of the property <code>app.version</code> loaded from
	 *         the build properties bundle. If bundle was not loaded for any
	 *         reason, or if the property is not found, then return
	 *         <code>null</code>.
	 */
	public String getAppVersion()
	{
		return getProperty(APP_VERSION_KEY);
	}

	/**
	 * Return value of the property <code>build.timestamp</code> loaded from the
	 * build properties bundle.
	 * 
	 * @return Value of the property <code>build.timestamp</code> loaded from
	 *         the build properties bundle. If bundle was not loaded for any
	 *         reason, or if the property is not found, then return
	 *         <code>null</code>.
	 */
	public String getBuildTimestamp()
	{
		return getProperty(BUILD_TIMESTAMP_KEY);
	}

	/**
	 * Return value of the property identified by the parameter
	 * <code>propertyName</code>, which has been loaded from the build
	 * properties bundle.
	 * 
	 * @return Value of the property identified by the parameter
	 *         <code>propertyName</code>, which has been loaded from the build
	 *         properties bundle. If bundle was not loaded for any reason, or if
	 *         the property is not found, then return <code>null</code>.
	 */
	public String getProperty(String propertyName)
	{
		if (buildPropertiesBundle == null)
			return null;

		try {
			return buildPropertiesBundle.getString(propertyName);
		}
		catch (NullPointerException e) {
			return null;
		}
		catch (MissingResourceException e) {
			return null;
		}
	}

	/**
	 * @return String represenation of all loaded build properties. Used only
	 *         for troubleshooting.
	 */
	@Override
	public String toString()
	{
		if (buildPropertiesBundle == null || buildPropertiesBundle.keySet().isEmpty())
			return "No properties loaded";

		StringBuilder buf = new StringBuilder();
		for (String key : buildPropertiesBundle.keySet()) {
			String value = buildPropertiesBundle.getString(key);
			buf.append(key);
			buf.append('=');
			buf.append(value);
			buf.append("  ");
		}

		return buf.toString();
	}
}
