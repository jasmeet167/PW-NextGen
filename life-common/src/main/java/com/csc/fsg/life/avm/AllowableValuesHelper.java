package com.csc.fsg.life.avm;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.csc.fsg.life.biz.meta.MetaData;
import com.csc.fsg.life.biz.meta.MetaDataHelper;
import com.csc.fsg.life.common.util.NumberHelper;
import com.csc.fsg.life.common.util.ReflectionUtils;
import com.csc.fsg.life.context.UserContext;
import com.csc.fsg.life.rules.IRulesManager;
import com.csc.fsg.life.rules.Result;

/* Modifications: T0140 */

/**
 * This is a helper class that provides methods to get the allowable values from
 * AVM. 
 */
public class AllowableValuesHelper
{
    /**
     * Gets the core values for given attribute and Business Object.
     * 
     * @param context The user context.
     * @param objClass The class to get values for a field on.
     * @param attributeName The property in the class to get values for.
     * @return The list of core values found (as string objects).
     */
    @SuppressWarnings("unchecked")
	public static List<String> getCoreValues(UserContext context, Class objClass, String attributeName)
    {
    	boolean forceAvmBase = false;
		MetaData metaData = MetaDataHelper.getMetaData(objClass, attributeName);
		if (metaData != null)
			forceAvmBase = metaData.forceAvmBase();
		
        // constructs map for the key values
        Map<String,String> keys = createKeys(context, forceAvmBase);

        // get the RulesManagerImpl from the Current Context
        IRulesManager rulesManager = RulesManagerAvmImpl.getInstance();
        return rulesManager.getCoreValues(objClass, attributeName, keys);
    }

    /**
     * Gets the displayValues for the given attribute and Business Object.
     * 
     * @param context The user context.
     * @param objClass The class to get values for a field on.
     * @param attributeName The property in the class to get values for.
     * @return The list of display values found (as string objects).
     */
    @SuppressWarnings("unchecked")
	public static List getDisplayValues(UserContext context, Class objClass, String attributeName)
    {
    	boolean forceAvmBase = false;
		MetaData metaData = MetaDataHelper.getMetaData(objClass, attributeName);
		if (metaData != null)
			forceAvmBase = metaData.forceAvmBase();
		
        // constructs map for the key values
        Map<String,String> keys = createKeys(context, forceAvmBase);

        // get the RulesManagerImpl from the Current Context
        IRulesManager rulesManager = RulesManagerAvmImpl.getInstance();
        return rulesManager.getDisplayValues(objClass, attributeName, keys);
    }

    /**
     * Return a display value corresponding to the given core value.
     * 
     * @param context Current UserContext.
	 * @param objClass The object the attribute is on.
     * @param attributeName The attribute to use on 'objClass' to get an AVM field refernce to
	 * lookup allowable values on.
     * @param coreValue The core value to translate.
     * @return The display value.  If there is no specified translation then the
	 * core value is returned.
     */
	@SuppressWarnings("unchecked")
	public static String getDisplayValue(UserContext context,
										 Class       objClass,
										 String      attributeName,
										 String      coreValue)
	{
		// Get Rules Manager from the Current Context
		IRulesManager rulesManager = RulesManagerAvmImpl.getInstance();

		MetaData metaData = MetaDataHelper.getMetaData(objClass, attributeName);
		if (metaData == null) {
			return coreValue;
		}
	
        // Construct map of the key values
		Map<String,String> keys = createKeys(context, metaData.forceAvmBase());
		
		// Translate the given core value
		Map<String,String> coreAndDisplayDomain = rulesManager.getCoreAndDisplayValues(metaData.avref(), keys);

		//
		// If property is a number, zero-fill coreValue if necessary.
		// Necessary because allowable values work off of strings so
		// an field with a size of 2 and a value of 1 will be stored in
		// AVM as '01'.
		// 
		Class propertyType = ReflectionUtils.getAttributeType(objClass, attributeName);
		String cv = coreValue;
		// Compare both Integer Object and primitive Integer type
		// Maybe also add in Short and Long?
		if (propertyType.equals(Integer.class) ||
			propertyType.equals(Integer.TYPE) )
		{
			int size = metaData.size();
			if (size > 0)
				cv = NumberHelper.zeroFill(cv, size);
		}
		
		return getDisplayValue(coreAndDisplayDomain, cv);
	}

	/**
	 * Gets the display value for the specified core value.
	 * If there is no specified translation then the core value is returned.
	 * @param coreAndDisplayDomain The map of core to display values.
	 * @param coreValue The core value to get a display value for.
	 * @return The corresponding display value.
	 */
	private static String getDisplayValue(Map<String,String> coreAndDisplayDomain, String coreValue)
	{
		if (coreValue != null && coreValue.trim().length() == 0)
			coreValue = " ";
		
		String displayValue = null; 
		if (coreAndDisplayDomain != null)
			displayValue = (String) coreAndDisplayDomain.get(coreValue);

		// If no translation found, then return the core value.
		if (displayValue == null)
			return coreValue;
		
		return displayValue;
	}
	
    /**
     * Return a display value corresponding to the given core value.
     * 
     * @param context The user context.
     * @param attributeAvmRef The AVM field reference.
     * @param coreValue The core value to get a display value for.
     * @return a display value corresponding to the given core value.
     */
	public static String getDisplayValue(UserContext context, String attributeAvmRef, String coreValue)
	{
		return getDisplayValue(context, attributeAvmRef, false, coreValue);
	}
    
    /**
     * Return a display value corresponding to the given core value.
     * 
     * @param context The user context.
     * @param attributeAvmRef The AVM field reference.
     * @param coreValue The core value to get a display value for.
     * @return a display value corresponding to the given core value.
     */
	public static String getDisplayValue(UserContext context, String attributeAvmRef, boolean forceAvmBase, String coreValue)
	{
        // Construct map of the key values
		Map<String,String> keys = createKeys(context, forceAvmBase);
		
		// Get Rules Manager from the Current Context
		IRulesManager rulesManager = RulesManagerAvmImpl.getInstance();

		// Translate the given core value
		Map<String,String> coreAndDisplayDomain = rulesManager.getCoreAndDisplayValues(attributeAvmRef, keys);		
		return getDisplayValue(coreAndDisplayDomain, coreValue);
	}

	/**
     * Returns default values for an attribute name
     * 
     * @param sd
     * @param obj
     * @param attributeName
     * @return
     */
    /*public static String getDefaultValue(SessionData sd, Class objClass, String attributeName)
    {
        Map keys = createKeys(sd);

        // get the RulesManagerImpl from the Current Context
        IRulesManager rulesManager = RulesManagerAvmImpl.getInstance();
        String defaultValue = rulesManager.getDefaultValue(objClass, attributeName, keys);
        return defaultValue;
		}*/

    /**
     * Creates keys required for AVM access depends on
     * companyCode,productCode,pageId,trxCode.
     * 
     * @param userContext  The user context.
     * @return A map of the AVM key/value pairs.
     */
    public static Map<String,String> createKeys(UserContext userContext)
    {
        return createKeys(userContext, false);
    }

    /**
     * Creates keys required for AVM access depends on
     * companyCode,productCode,pageId,trxCode.
     * 
     * @param userContext  The user context.
     * @param forceAvmBase If true, keys are created suitable for lookup 
     *                     of Allowable Values associated with application=AVM, 
     *                     and environment=BASE. Otherwise Application/Environment
     *                     from userContext is used. 
     * @return A map of the AVM key/value pairs.
     */
    public static Map<String,String> createKeys(UserContext userContext, boolean forceAvmBase)
    {
        Map<String,String> keys = new HashMap<String,String>();
        
        if (!forceAvmBase)
        {
        	keys.put("avmApplicationKey", userContext.getAvmApplicationKey());
        	keys.put("avmEnvironmentKey", userContext.getAvmEnvironmentKey());
        }
        keys.put("companyCode", userContext.getCompanyCode());
        keys.put("companyCode", userContext.getCompanyCode());
        keys.put("productCode", userContext.getProductCode());
        keys.put("pageId", userContext.getPageId());
        keys.put("trxCode", userContext.getTrxCode());

        return keys;
    }

    /**
     * Returns default values for an attribute name utilizing Context.
     * 
     * @param rulesManager The rules manager.
     * @param userContext The current state.
	 * @param objClass The class the attribute is on.
     * @param attributeName The attribute.
     * @return The default value for the specified class/field.
     */
    @SuppressWarnings("unchecked")
	public static String getDefaultValueString(IRulesManager rulesManager,
											   UserContext   userContext,
											   Class         objClass,
											   String        attributeName)
    {
    	boolean forceAvmBase = false;
		MetaData metaData = MetaDataHelper.getMetaData(objClass, attributeName);
		if (metaData != null)
			forceAvmBase = metaData.forceAvmBase();
		
        Map<String,String> keys = createKeys(userContext, forceAvmBase);

        String defaultValue = rulesManager.getDefaultValue(objClass, attributeName, keys);
        if (defaultValue == null)
            return "";
        return defaultValue;
    }

    /**
     * Returns default string values for an attribute name utilizing Context.
     * 
     * @param rulesManager The rules manager.
     * @param userContext The current state.
	 * @param objClass The class the attribute is on.
     * @param attributeName The attribute.
     * @return The default value for the specified class/field.
     */
    @SuppressWarnings("unchecked")
	public static Long getDefaultValueLong(IRulesManager rulesManager,
										   UserContext   userContext,
										   Class         objClass,
										   String        attributeName)
    {
    	boolean forceAvmBase = false;
		MetaData metaData = MetaDataHelper.getMetaData(objClass, attributeName);
		if (metaData != null)
			forceAvmBase = metaData.forceAvmBase();
		
        Map<String,String> keys = createKeys(userContext, forceAvmBase);

        String defaultValue = rulesManager.getDefaultValue(objClass, attributeName, keys);
        if (defaultValue == null || defaultValue.trim().length() == 0)
            return Long.valueOf("0");
        return Long.valueOf(defaultValue);
    }

	/**
	 * Validates a field to a specific set of allowable values. 
	 * @param userContext The user context.
	 * @param objClass The class on which the attribute exists.
	 * @param attributeName The property to validate.
	 * @param value The value to verify if it is valid value.
	 * @return true if the valid is valid, false if not.
	 */
	@SuppressWarnings("unchecked")
	public static boolean validateValue(UserContext userContext, 
										Class       objClass,
										String      attributeName,
										Object      value)
	{
    	boolean forceAvmBase = false;
		MetaData metaData = MetaDataHelper.getMetaData(objClass, attributeName);
		if (metaData != null)
			forceAvmBase = metaData.forceAvmBase();
		
        // Construct map of the key values
		Map<String,String> keys = createKeys(userContext, forceAvmBase);
		
		// Get Rules Manager from the Current Context
		IRulesManager rulesManager = RulesManagerAvmImpl.getInstance();

		// Validate the value.
		Result result = rulesManager.validateValue(objClass, attributeName, value, keys);
		if (result.hasErrors())
			return false;
		else
			return true;
	}
}