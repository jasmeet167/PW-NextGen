package com.csc.fsg.life.avm.api;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.csc.fsg.life.avm.biz.dao.model.AllowableValuesField;
import com.csc.fsg.life.avm.environments.AVMConfigBean;
import com.csc.fsg.life.avm.lib.exceptions.AVMException;
import com.csc.fsg.life.configuration.ConfigFileNames;

/* Modifications: T0140, T0150, T0143, T0175 */

abstract public class AVMManager
{
	protected static final Log log = LogFactory.getLog("com.csc.fsg");

	/** Sort allowable values by display values in ascending order. */
	public static final int SORT_DISPLAY_VALUES_ASCENDING = 1;
	/** Sort allowable values by core values in ascending order. */
	public static final int SORT_CORE_VALUES_ASCENDING = 2;
	/** Constant for a space in AVM. */
	public static final String AVMSpaceValue = "_____SpaceValue";

	private static AVMManager avmManager;

	protected static AVMConfigBean avmConfigBean;

	/**
	 * Get an instance of AVM. Call this when either:
	 * <UL>
	 * <LI>You know the AVMManager singleton has already been created.</LI>
	 * <LI>The AVMManager is configured by the default Spring configuration
	 * file.</LI>
	 * </UL>
	 * 
	 * @return The AVMManager singleton instance.
	 */
	public static AVMManager getInstance()
	{
		if (avmManager == null) {
			AVMConfigBean config = getAvmConfiguration();

			if (config != null && config.isEnabled())
				avmManager = new AVMManagerImpl(config);
			else
				avmManager = new AVMManagerStub(config);
		}

		return avmManager;
	}

	/**
	 * Get an instance of AVM. If the singleton has not been created the config
	 * bean is loaded via the Spring application context passed in.
	 * 
	 * @param appContext
	 *        The application context to load the config bean from.
	 * @return The AVMManager singleton instance.
	 */
	public static AVMManager getInstance(ApplicationContext appContext)
	{
		if (avmManager == null) {
			AVMConfigBean config = getAvmConfiguration(appContext);

			if (config != null && config.isEnabled())
				avmManager = new AVMManagerImpl(config);
			else
				avmManager = new AVMManagerStub(config);
		}

		return avmManager;
	}

	/**
	 * Get an instance of AVM. If the singleton has not been created the config
	 * bean passed in is used.
	 * 
	 * @param bean
	 *        The configuration bean used to configure the AVMManager.
	 * @return The AVMManager singleton instance.
	 */
	public static AVMManager getInstance(AVMConfigBean bean)
	{
		if (avmManager == null) {
			if (bean != null && bean.isEnabled())
				avmManager = new AVMManagerImpl(bean);
			else
				avmManager = new AVMManagerStub(bean);
		}

		return avmManager;
	}

	/**
	 * Load Spring framework's application context from configuration, and
	 * retrieve from it the instance of <code>AVMConfigBean</code>.
	 * 
	 * @return Instance of AVM configuration bean.
	 */
	private static AVMConfigBean getAvmConfiguration()
	{
		try {
			ClassPathXmlApplicationContext appContext = null;

			if (ConfigFileNames.getUserConfigFile() != null 
							&& !ConfigFileNames.getUserConfigFile().equals(""))
				appContext = new ClassPathXmlApplicationContext(new String[] { ConfigFileNames.getUserConfigFile() });
			else
				appContext = new ClassPathXmlApplicationContext(new String[] { "AVMConfigContext.xml" });

			return (AVMConfigBean) appContext.getBean("AVMConfigBean");
		}
		catch (Exception e) {
			log.error("Creation failed with error: " + e.getMessage());
		}

		return null;
	}

	/**
	 * Retrieve from the supplied Spring framework's application context the
	 * instance of <code>AVMConfigBean</code>.
	 * 
	 * @param appContext
	 *        Spring application context.
	 * @return Instance of AVM configuration bean.
	 */
	private static AVMConfigBean getAvmConfiguration(ApplicationContext appContext)
	{
		return (AVMConfigBean) appContext.getBean("AVMConfigBean");
	}

	/**
	 * Constructor; used to return a new instance of this object, and provide
	 * basic initialization.
	 */
	protected AVMManager(AVMConfigBean bean)
	{
		avmConfigBean = bean;
	}

	/**
	 * Returns the cache handler.
	 * 
	 * @return the cache handler.
	 */
	abstract public AVMCacheHandler getAVMCacheHandler();

	/**
	 * Get the specified field values from AVM. If the field doesn't exist,
	 * returns null.
	 * 
	 * @param accessKeys
	 *        The access keys to get the field.
	 * @param sortingStyle
	 *        The sort to use for the allowable values returned.
	 * @return the specified field values from AVM. If the field doesn't exist,
	 *         returns null.
	 * @throws AVMException
	 *         When an unexpected problem accessing the field in the database
	 *         occurs.
	 */
	abstract public IFieldAllowableValues getFieldAllowableValues(AccessKeys accessKeys, int sortingStyle)
		throws AVMException;

	abstract public List<AllowableValuesField> getListOfPossibleReferences(String fieldName)
		throws AVMException;

	/**
	 * getPageInfo: return a Hashtable of PageInfo objects for all pages
	 * associated with the given application and environment. Includes a
	 * PageInfo object for the wildcard page (AVPAGEALL) keyed by the page id
	 * "ALL".
	 * 
	 * The end result is access to page descriptions for all pages, and field
	 * descriptions for all fields in all pages.
	 * 
	 * @param applId
	 *        Application id used to get pages.
	 * @param envrnmntId
	 *        Environment id used to get pages.
	 * @return Hashtable of PageInfo objects
	 * @throws AVMException
	 */
	abstract public Hashtable<String, PageInfo> getPageInfo(String applId, String envrnmntId)
		throws AVMException;

	/**
	 * Gets the AVMConfigBean that configured this AVMManager singleton
	 * instance.
	 * 
	 * @return the AVMConfigBean that configured this AVMManager singleton
	 *         instance.
	 * @see #setAvmConfigBean
	 */
	public AVMConfigBean getAvmConfigBean()
	{
		return avmConfigBean;
	}

	/**
	 * Set the AVMConfigBean. Probably should not be called. Remove this?
	 * 
	 * @param avmConfigBean
	 *        The configuration bean.
	 * @see #getAvmConfigBean
	 */
	public void setAvmConfigBean(AVMConfigBean avmConfigBean)
	{
		AVMManager.avmConfigBean = avmConfigBean;
	}

	/**
	 * Converts a ID to a key.
	 * 
	 * @param aKeys
	 *        The access keys - used for conversion.
	 * @param idVal
	 *        The ID.
	 * @param maskVal
	 *        The ID type to convert. One of the constants in AccessKeys.
	 * @return The key for the specified ID.
	 * @throws AVMException
	 */
	abstract protected String getKeyEquivalent(AccessKeys aKeys, String idVal, int maskVal)
		throws AVMException;

	/**
	 * Get a list of environments for the specified application.
	 * 
	 * @param appId
	 *        The application ID to get environments for.
	 * @return A list of environments for the specified application.
	 */
	abstract public ArrayList<AVMIdDescPair> getEnvironmentsForAppID(String appId);

	/**
	 * Get a list of environments for the specified application key.
	 * 
	 * @param appKey
	 *        The application Key to get environments for.
	 * @return A list of environments for the specified application.
	 */
	abstract public ArrayList<AVMIdDescPair> getEnvironmentsForAppKey(String appKey);

	/**
	 * Get a list of companies for the specified application.
	 * 
	 * @param appId
	 *        The application ID to get companies for.
	 * @return A list of companies for the specified application.
	 */
	abstract public ArrayList<AVMIdDescPair> getCompaniesForAppID(String appId);

	/**
	 * Get the policy admin hash.
	 * 
	 * @return the policy admin hash.
	 * @see #setPolicyAdminHash
	 **/
	abstract public Hashtable<String, Object> getPolicyAdminHash();

	/**
	 * Get the application from The policy admin hash.
	 * 
	 * @see #setPolicyAdminForAppl
	 */
	abstract public Object getPolicyAdminForAppl(String applName);

	/**
	 * Set the policy admin hash.
	 * 
	 * @param policyAdminHash
	 * @see #getPolicyAdminHash
	 */
	abstract public void setPolicyAdminHash(Hashtable<String, Object> policyAdminHash);

	/**
	 * @see #getPolicyAdminForAppl
	 */
	abstract public void setPolicyAdminForAppl(String applName, Object polcyAdmin);
}
