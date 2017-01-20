package com.csc.fsg.life.common.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.StringTokenizer;

/* Modifications: T0140, T0175 */

/**
   Extends the Java Properties object to support the primitive
   types (boolean, int, float, double, etc).
**/
public class ExtProperties
	extends Properties {

	/**
	   Creates a new empty ExtProperties object.
	**/
	public ExtProperties() {
		super();
	}

	/**
	   Creates a new ExtProperties object with specified properties.
	   @param pDefault properties to prime this object with.
	**/
	public ExtProperties(Properties pDefault) {
		super(pDefault);
	}

	/**
	   Creates a new ExtProperties object with properties from 
	   the specified file.
	   @param filename The file to load properties from.  Assumes
	   the file is a classpath resource.
	   @throws IOException If there is an I/O exception accessing the file.
	   @throws IllegalArgumentException If the file is not found.
	**/
	public ExtProperties(String filename)
		throws IOException, IllegalArgumentException {

		super();
		
		filename = propsForEnvironment(filename); 

		InputStream is = null;
		try {
			is = ExtProperties.class.getResourceAsStream("/"
				+ filename);

			if (is == null) {
				throw new IllegalArgumentException(filename + " not found");
			}

			load(is);
		}
		finally {
			if (is != null)
				is.close();
		}
	}

	/**
	 * Sets the int value as a property for the specified key.
	 * @param sKey The key for the property to set.
	 * @param iValue The value for the property to set.
	 * @see #getIntProperty
	 */
	public void setIntProperty(String sKey, int iValue) {
		setProperty(sKey, Integer.toString(iValue));
	}

	/**
	 * Sets the long value as a property for the specified key.
	 * @param sKey The key for the property to set.
	 * @param lValue The value for the property to set.
	 * @see #getLongProperty
	 */
	public void setLongProperty(String sKey, long lValue) {
		setProperty(sKey, Long.toString(lValue));
	}

	/**
	 * Sets the float value as a property for the specified key.
	 * @param sKey The key for the property to set.
	 * @param fValue The value for the property to set.
	 * @see #getFloatProperty
	 */
	public void setFloatProperty(String sKey, float fValue) {
		setProperty(sKey, Float.toString(fValue));
	}

	/**
	 * Sets the double value as a property for the specified key.
	 * @param sKey The key for the property to set.
	 * @param dValue The value for the property to set.
	 * @see #getDoubleProperty
	 */
	public void setDoubleProperty(String sKey, double dValue) {
		setProperty(sKey, Double.toString(dValue));
	}

	/**
	 * Sets the boolean value as a property for the specified key.
	 * @param sKey The key for the property to set.
	 * @param bValue The value for the property to set.
	 * @see #getBooleanProperty
	 */
	public void setBooleanProperty(String sKey, boolean bValue) {
		setProperty(sKey, Boolean.valueOf(bValue).toString());
	}

	/**
	 * Returns the specified property as an int.
	 * @param sKey The key for the property to retrieve.
	 * @param iDefault The default value to return if the property is not found.
	 * @return The property value.
	 * @see #setIntProperty
	 */
	public int getIntProperty(String sKey, int iDefault) {

		String s = getProperty(sKey, Integer.toString(iDefault));

		return Integer.parseInt(s);
	}

	/**
	 * Returns the specified property as an long.
	 * @param sKey The key for the property to retrieve.
	 * @param lDefault The default value to return if the property is not found.
	 * @return The property value.
	 * @see #setLongProperty
	 */
	public long getLongProperty(String sKey, long lDefault) {

		String s = getProperty(sKey, Long.toString(lDefault));

		return Long.parseLong(s);
	}

	/**
	 * Returns the specified property as an float.
	 * @param sKey The key for the property to retrieve.
	 * @param fDefault The default value to return if the property is not found.
	 * @return The property value.
	 * @see #setFloatProperty
	 */
	public float getFloatProperty(String sKey, float fDefault) {

		String s = getProperty(sKey, Float.toString(fDefault));

		return Float.parseFloat(s);
	}

	/**
	 * Returns the specified property as an double.
	 * @param sKey The key for the property to retrieve.
	 * @param dDefault The default value to return if the property is not found.
	 * @return The property value.
	 * @see #setDoubleProperty
	 */
	public double getDoubleProperty(String sKey, double dDefault) {

		String s = getProperty(sKey, Double.toString(dDefault));

		return Double.parseDouble(s);
	}

	/**
	 * Returns the specified property as an boolean.
	 * @param sKey The key for the property to retrieve.
	 * @param bDefault The default value to return if the property is not found.
	 * @return The property value.
	 * @see #setBooleanProperty
	 */
	public boolean getBooleanProperty(String sKey, boolean bDefault) {

		boolean b;
		String s = getProperty(sKey, Boolean.valueOf(bDefault).toString());
		if (s.equalsIgnoreCase("true") || s.equalsIgnoreCase("yes")
			|| s.equals("1") || s.equalsIgnoreCase("on")) {
			b = true;
		} else {
			b = false;
		}

		return b;
	}

	private static ExtProperties pwProps;


	/**
	 * Returns the singleton for the product wizard properties object.
	 * @return The PW propertiers object.
	 */
	public static ExtProperties getPWProperties() {
		if(pwProps != null)
			return pwProps;
			
		try {
			pwProps = new ExtProperties("pw.properties");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return pwProps;
	}
	
	
	/**
	 * Use the Prudential AppServerEnv to select the correct pw_XXX.properties file.
	 * e.g. pw_DEV.properties, pw_PROD.properties.
	 * 
	 * This logic inserted into the HEAD stream:
	 *    1) "pw.properties" too widespread in PW code
	 *    2) didn't want to branch
	 * 
	 * Note that logic will continue to work fine for non-Pru version of PW.
	 * Better fix: create subclasses PWProperties and PruPWProperties 
	 * 
	 * @param filename
	 * @return the filename passed, modified when apprpriate
	 */
	private static boolean envShown = false;

	/**
	   Utility method to get the specific property file name 
	   based on the system property.
	   @param filename the base properties filename.
	   @return the filename passed, modified when apprpriate
	 */
	protected String propsForEnvironment(String filename)
	{
		if (filename.equals("pw.properties")) {
			String appServerEnv = System.getProperty("com.pru.AppServerEnv");
			if (!envShown && appServerEnv!=null) {
				System.out.println("com.pru.AppServerEnv: [" + appServerEnv + "]");
				envShown = true;
			}
			if (appServerEnv != null) {
				// First token is suffix to determine config file name
				StringTokenizer st = new StringTokenizer(appServerEnv,":");
				if (st.hasMoreTokens()) { 
					String envSuffix = st.nextToken(); 
					filename = "pw_" + envSuffix + ".properties";
				}
			}
		}
		return filename;
	}	
}
