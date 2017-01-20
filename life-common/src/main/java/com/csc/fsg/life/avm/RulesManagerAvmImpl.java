package com.csc.fsg.life.avm;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.util.StringUtils;

import com.csc.fsg.life.avm.api.AVMManager;
import com.csc.fsg.life.avm.api.AccessKeys;
import com.csc.fsg.life.avm.api.AllowableValues;
import com.csc.fsg.life.avm.api.IFieldAllowableValues;
import com.csc.fsg.life.avm.api.ListAllowableValues;
import com.csc.fsg.life.avm.api.RangeAllowableValues;
import com.csc.fsg.life.avm.lib.exceptions.AVMException;
import com.csc.fsg.life.biz.exception.WrapperException;
import com.csc.fsg.life.biz.meta.MetaData;
import com.csc.fsg.life.biz.meta.MetaDataHelper;
import com.csc.fsg.life.rules.IRulesManager;
import com.csc.fsg.life.rules.Result;
import com.csc.fsg.life.rules.ResultComponent;
import com.csc.fsg.life.rules.ResultImpl;
import com.csc.fsg.life.rules.ResultType;

/* Modifications: ENHT0085, ENHT0117, T0140, T0150, WMA-1985 */

/**
 * AVM Implementation for Rules Facade. Provides methods to fetch Core and
 * Display Values from AVM database. Helper methods to validate attribute Value,
 * fields validation and Object Validation based on Allowable Values.
 */
@SuppressWarnings("unchecked")
public class RulesManagerAvmImpl 
	implements IRulesManager,Serializable 
{
    /**  Logger for the ServiceManager class. */
    protected static final Log log = LogFactory.getLog("com.csc.fsg");	

	/**
	 * Private constructor - singleton.
	 */
	private RulesManagerAvmImpl() {}

	static private RulesManagerAvmImpl s_instance = null;

	/**
	 * Static method to access the singleton.
	 * @return The rules manager singleton.
	 */
	static public RulesManagerAvmImpl getInstance()
	{
		if (s_instance == null)
			s_instance = new RulesManagerAvmImpl();
		return s_instance;
	}
	
	/**
	 * Currently not implemented.
	 * 
	 * Returns the core value for a specified attribute. Will return null, if
	 * there is no core value satisfying the input keys. 
	 *
	 * @param objClass
	 *            the business object class to be used for mapping
	 * @param attributeName
	 *            the simple (unqualified) name of the attribute
	 * @param keys
	 *            a <code>java.util.Map</code> containing the key values to
	 *            get the domains
	 * @param displayValue The display value to get the core value for.
	 * @return the core value for the specified attribute
	 */
	public Object getCoreValue(Class objClass, String attributeName, Map<String,String> keys, String displayValue) 
	{
		throw new UnsupportedOperationException("This method is not implemented");
	}

	/**
	 * Returns the allowed core values for a specified attribute.
	 * 
	 * @param objClass
	 *            the business object class containing the specified attribute
	 * @param attributeName
	 *            the simple (unqualified) name of the attribute
	 * @param keys
	 *            a <code>java.util.Map</code> containing the key values to
	 *            get the domains
	 * @return a <code>java.util.List</code> of the allowed core values for
	 *         the attribute
	 */
	public List<String> getCoreValues(Class objClass, String attributeName, Map<String,String> keys) 
	{
		List<String> coreValues = new ArrayList<String>();
		try {
            // get the AVM reference for the Object using Commons Attribute
            String attributeAvmRef = getAvmRefName(objClass, attributeName);
            if (attributeAvmRef == null)
                return coreValues;

			AccessKeys accessKeys = getAccessKeys(attributeAvmRef, keys);
            if (accessKeys == null)
                return coreValues;
			coreValues = getCoreValues(accessKeys);
		} catch (SQLException e) {
			// log message
			throw WrapperException.wrap(e);
		} catch (AVMException e) {
			// log message
			throw WrapperException.wrap(e);
		} catch (Exception e) {
			// log message
			throw WrapperException.wrap(e);
		}
		return coreValues;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.csc.fsg.life.rules.IRulesManager#getDisplayValue(java.lang.Object,
	 *      java.lang.String, java.util.Map, java.lang.String)
	 */
	public Object getDisplayValue(Class objClass, String attributeName, Map keys, String coreValue) {
		throw new UnsupportedOperationException("This method is not implemented");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.csc.fsg.life.rules.IRulesManager#getDisplayValues(java.lang.Object,
	 *      java.lang.String, java.util.Map)
	 */
	public List<String> getDisplayValues(Class objClass, String attributeName, Map keys) 
	{
		List<String> displayValues = new ArrayList<String>();
		try {
            // get the AVM reference for the Object using Commons Attribute
            String attributeAvmRef = getAvmRefName(objClass, attributeName);
            if (attributeAvmRef == null)
                return displayValues;

			AccessKeys accessKeys = getAccessKeys(attributeAvmRef, keys);
            if (accessKeys == null)
                return displayValues;
			displayValues = getDisplayValues(accessKeys);
		} catch (SQLException e) {
			// log message
			throw WrapperException.wrap(e);
		} catch (AVMException e) {
			// log message
			throw WrapperException.wrap(e);
		} catch (Exception e) {
			// log message
			throw WrapperException.wrap(e);
		}

		return displayValues;
	}

	/**
	 * Validates all simple attributes of the input business object.
	 * 
	 * @param obj
	 *            the business object class whose simple attributes are to be
	 *            validated
	 * @return a <code>com.csc.fs.Result</code> object containing result data
	 *         and messages. The <code>isValid</code> indicator is set to
	 *         <code>false</code> if any attribute fails validation.
	 * 
	 * @see com.csc.fsg.life.rules.IRulesManager#validateAllFields(java.lang.Object)
	 */
	public Result validateAllFields(Object obj) {
		return null;
	}

	/**
	 * This method performs the basic validation for the input object.
	 * 
	 * @param obj
	 *            the business object to be validated
	 * @return a <code>com.csc.fs.Result</code> object containing other result
	 *         data and messages
	 * 
	 * @see com.csc.fsg.life.rules.IRulesManager#validateObject(java.lang.Object)
	 */
	public Result validateObject(Object obj) {
		return null;
	}

	/*
	 * Validates the specified attribute's value.
	 * 
	 * @param obj
	 *            the business object containing the attribute to be validated
	 * @param attributeName
	 *            the simple (unqualified) name of the attribute to be validated
	 * @param keys Allowable Values access keys.
	 * @return a <code>com.csc.fs.Result</code> object containing result data
	 *         and messages. The <code>isValid</code> indicator is set to
	 *         false if the attribute fails validation.
	 * 
	 * @see com.csc.fsg.life.rules.IRulesManager#validateAttribute(java.lang.Object,
	 *      java.lang.String, java.util.Map)
	 */
	public Result validateAttribute(Object obj, String attributeName, Map keys) {
		return null;
	}

	/**
	 * Validates the specified value against the specified domain.
	 * 
	 * @param objClass
	 *            the business object class containing the specified attribute
	 * @param attributeName
	 *            the simple (unqualified) name of the attribute
	 * @param value
	 *            the value to be validated
	 * @param keys
	 *            a <code>java.util.Map</code> containing the key objects for
	 *            the domain
	 * @return a <code>com.csc.fs.Result</code> object containing result data
	 *         and messages. The <code>isValid</code> indicator is set to
	 *         false if the value is not valid.
	 */
	public Result validateValue(Class objClass, String attributeName, Object value, Map keys) 
	{
		Result result = new ResultImpl();

		// Bail on null values.
		if (value == null) {
			//log.error("Null value ... bailing.");
			return result;
		}

		// Bail on empty string value.
		if (value.toString().length() == 0) {
            //log.error("Empty String value ... bailing.");
			return result;
		}

		// Check to see if the value is zero.
		boolean valueIsZero = true;
		String valStr = value.toString();
		for(int i = 0; i < valStr.length(); i++) {
			if (valStr.charAt(i) != '0') {
				valueIsZero = false;
				break;
			}
		}

		// get the AVM reference for the Object using Commons Attributes
		// If no AVM reference, then any value is valid.
		String attributeAvmRef = getAvmRefName(objClass, attributeName);
		if (attributeAvmRef == null) {
            log.error("No AVM Ref for attribute " + 
					  objClass.getName() + "." + attributeName + 
					  " ... bailing.");
			return result;
		}

		// Setup access keys
		AccessKeys accessKeys = getAccessKeys(attributeAvmRef, keys);

		try {
			// Get core values for specified field.
			List coreValues = getCoreValues(accessKeys);
            
			// Validate value against core values.
			boolean goodValue = coreValues.contains(value);
			if (valueIsZero && coreValues.contains("0"))
				goodValue = true;
			if (!goodValue) {
				log.error("Bad Value '" + value + "' for attribute " + 
						  objClass.getName() + "." + attributeName + 
						  " ... bailing " + coreValues);
				result.addMessage(ResultComponent.RULES, 0, ResultType.ERROR);
			}
		}
		catch(SQLException sqle) {
            log.error("SQLException: " + sqle.getMessage() + 
					  " when validating Value '" + value + "' for attribute " + 
					  objClass.getName() + "." + attributeName + 
					  " ... bailing.");
			result.addMessage(ResultComponent.RULES, 0, ResultType.ERROR);
		}
		catch(AVMException avme) {
            log.error("AVMException: " + avme.getMessage() + 
					  " when validating Value '" + value + "' for attribute " + 
					  objClass.getName() + "." + attributeName + 
					  " ... bailing.");
			result.addMessage(ResultComponent.RULES, 0, ResultType.ERROR);
		}
		
		return result;
	}

	/**
	 * Method to Create Access Keys (key parameters)
	 * 
	 * @param obj
	 * @param attributeName
	 * @param map
	 */
	private AccessKeys getAccessKeys(String attributeAvmRef, Map map)
	{
		String pageId = (String) map.get("pageId");
		if (pageId == null)
			pageId = AccessKeys.AllPageKey;
		String companyCode = (String) map.get("companyCode");
		if (companyCode == null)
			companyCode = AccessKeys.AllCompanyKey;
		String productCode = (String) map.get("productCode");
		if (productCode == null)
			productCode = AccessKeys.AllProductKey;
		String trxCode = (String) map.get("trxCode");
		if (trxCode == null)
			trxCode = AccessKeys.AllTrxCodeKey;
		
		String avmApplicationKey = (String) map.get("avmApplicationKey");
		String avmApplicationId =  AVMManager.getInstance().getAvmConfigBean().getApplicationId();
		
		String avmEnvironmentKey = (String) map.get("avmEnvironmentKey");		
		String avmEnvironmentId = AVMManager.getInstance().getAvmConfigBean().getEnvironmentId();
		
		AccessKeys accessKeys = null;
		if (avmApplicationKey != null && avmEnvironmentKey != null)
			accessKeys = new AccessKeys(avmApplicationKey, avmEnvironmentKey, pageId, companyCode,
					productCode, trxCode, attributeAvmRef);
		else
			accessKeys = new AccessKeys(avmApplicationId, avmEnvironmentId, pageId, companyCode,
					productCode, trxCode, attributeAvmRef, AccessKeys.ENVID_APPLID_ENABLED);
		return accessKeys;
	}

	/**
	 * This method fetch the allowable values using AVM API
	 * 
	 * @param accessKeys
	 * @throws SQLException
	 * @throws AVMException
	 */
	private AllowableValues getAllowableValuesFromAVM(AccessKeys accessKeys) 
		throws SQLException, AVMException 
	{
		IFieldAllowableValues iFieldAllowableValues = 
			AVMManager.getInstance().getFieldAllowableValues(accessKeys, AVMManager.SORT_DISPLAY_VALUES_ASCENDING);
		AllowableValues allowableValues = null;
		if (iFieldAllowableValues != null)
			allowableValues = iFieldAllowableValues.getAllowableValues();
		
		return allowableValues;
	}

    private String getDefaultValueFromAVM(AccessKeys accessKeys)
    	throws SQLException, AVMException
    {
        String defaultValue = null;
        IFieldAllowableValues iFieldAllowableValues = 
        	AVMManager.getInstance().getFieldAllowableValues(accessKeys, AVMManager.SORT_DISPLAY_VALUES_ASCENDING);
        if (iFieldAllowableValues != null)
            defaultValue = iFieldAllowableValues.getFieldDefault();
        
        return defaultValue;
    }

	/**
	 * Get the list of core values using the given access keys.
	 * 
	 * @param accessKeys
	 * @throws SQLException
	 * @throws AVMException
	 */
	private List<String> getCoreValues(AccessKeys accessKeys) 
		throws SQLException, AVMException 
	{
		AllowableValues allowableValues = getAllowableValuesFromAVM(accessKeys);
		List<String> coreValues = new ArrayList<String>();
        
        if (allowableValues == null)
            return coreValues;
        
		if (allowableValues instanceof RangeAllowableValues)
			coreValues = ((RangeAllowableValues) allowableValues).getRangeValues();
		else 
			coreValues = ((ListAllowableValues) allowableValues).getCoreValues();
		
		return coreValues;
	}

	/**
     * Get the list of display values for the given access keys.
     * 
	 * @param accessKeys
	 * @throws SQLException
	 * @throws AVMException
	 */
	private List<String> getDisplayValues(AccessKeys accessKeys) 
		throws SQLException, AVMException 
	{
		AllowableValues allowableValues = getAllowableValuesFromAVM(accessKeys);
		List<String> displayValues;
 		if (allowableValues instanceof RangeAllowableValues)
			displayValues = ((RangeAllowableValues) allowableValues).getRangeValues();
		else
			displayValues = ((ListAllowableValues) allowableValues).getDisplayValues();
		
        return displayValues;
	}

	/**
	 * Returns Map of core and display value for given keys
	 * 
	 * @param accessKeys
	 * @throws SQLException
	 * @throws AVMException
	 */
	private Map<String,String> getCoreDisplayMap(AccessKeys accessKeys) 
		throws SQLException, AVMException 
	{
		AllowableValues allowableValues = getAllowableValuesFromAVM(accessKeys);		
		Map<String,String> coreAndDisplayValues = new LinkedHashMap<String,String>();
        if (allowableValues == null)
            return coreAndDisplayValues;

		List<String> displayValues;
		List<String> coreValues;
        if (allowableValues instanceof RangeAllowableValues) {
			// if it is a range core and display are same
			coreValues = ((RangeAllowableValues) allowableValues).getRangeValues();
			displayValues = coreValues;
		} else {
			coreValues = ((ListAllowableValues) allowableValues).getCoreValues();
			displayValues = ((ListAllowableValues) allowableValues).getDisplayValues();
		}
        
        // find the core value of space, if exists
        int spaceIndex = findIndexOfSpace(coreValues);
        if (spaceIndex > -1) {
        	String coreValue = getValue(coreValues.get(spaceIndex));
			String displayValue = getValue(displayValues.get(spaceIndex));
        	coreAndDisplayValues.put(coreValue, displayValue);
        }
        
		for (int i = 0; i < coreValues.size(); i++) {
			// don't add the space since it was already added
			if (i == spaceIndex)
				continue;
			
			String coreValue = getValue(coreValues.get(i));
			String displayValue = getValue(displayValues.get(i));
			coreAndDisplayValues.put(coreValue, displayValue);
		}
		return coreAndDisplayValues;
	}

	/**
	 * Find the index in the list where the core value is empty or spaces.
	 */
	private int findIndexOfSpace(List<String> coreValues) {
		int spaceIndex = -1;
		for (int i = 0; i < coreValues.size(); i++) {
        	String coreValue = coreValues.get(i);
        	if (!StringUtils.hasText(coreValue)) {
        		spaceIndex = i;
        		break;
        	}
        }
		return spaceIndex;
	}
	
	private String getValue(String value) {
		if (value.trim().length() == 0)
			return " ";
		return value;
	}

	/**
	 * Uses meta data to get the AvmRef (reference AVM field i.e
	 * domain) from the interface
	 * 
	 * @param obj
	 * @param attributeName
	 */
	private String getAvmRefName(Class objClass, String attributeName) 
	{
		MetaData metaData = MetaDataHelper.getMetaData(objClass, attributeName);
		if (metaData == null)
            return null;
        return metaData.avref();
	}	

	/**
	 * Returns the class/field's specified default value.   
	 * @param objClass The class the field is on.
	 * @param attributeName The field to get a default value for.
	 * @param keys The access keys for AVM.
	 * @return the class/field's specified default value.   If no AVM Reference or
	 *   no default then null is returned.
	 * 
	 * @see com.csc.fsg.life.rules.IRulesManager#getDefaultValue(java.lang.Object,
	 *      java.lang.String, java.util.Map)
	 */
	public String getDefaultValue(Class objClass, String attributeName, Map<String,String> keys) 
	{
		String defaultValue = null;
		try {
            // get the AVM reference for the Object using Annotation
            String attributeAvmRef = getAvmRefName(objClass, attributeName);
            if (attributeAvmRef == null)
                return null;

			AccessKeys accessKeys = getAccessKeys(attributeAvmRef, keys);
            if (accessKeys == null)
                return null;

			IFieldAllowableValues iFieldAllowableValues = 
				AVMManager.getInstance().getFieldAllowableValues(accessKeys, AVMManager.SORT_DISPLAY_VALUES_ASCENDING);
			if (iFieldAllowableValues != null)
				defaultValue = iFieldAllowableValues.getFieldDefault();
		} catch (AVMException e) {
			// log message
			throw WrapperException.wrap(e);
		}
		return defaultValue;
	}

    /**
     * Returns the core and display values as name value pairs in a map.
     * @param objClass The class for the field to allowable values for.
     * @param attributeName The field on the class to get allowable values for.
     * @param keys AVM access keys.
     * @return the core and display values as name value pairs in a map.
     */
    public Map<String,String> getCoreAndDisplayValues(Class objClass, String attributeName, Map<String,String> keys) 
    {
    	Map<String,String> coreAndDisplayMap = new LinkedHashMap<String,String>();
        try {
            // get the AVM reference for the Object using Commons Attribute
            String attributeAvmRef = getAvmRefName(objClass, attributeName);
            if (attributeAvmRef == null) {
				log.error("No AVM Ref for " + objClass.getName() + "." + attributeName + " ... bailing.");
                return coreAndDisplayMap;
			}

            AccessKeys accessKeys = getAccessKeys(attributeAvmRef, keys);
            if (accessKeys == null)
                return coreAndDisplayMap;
            coreAndDisplayMap = getCoreDisplayMap(accessKeys);
        } catch (SQLException e) {
            // log message
            throw WrapperException.wrap(e);
        } catch (AVMException e) {
            // log message
            throw WrapperException.wrap(e);
        } catch (Exception e) {
            // log message
            throw WrapperException.wrap(e);
        }
        return coreAndDisplayMap;
        
    }

    /**
     * Returns the core and display values as name value pairs in a map.
     * @param attributeAvmRef The AVM field to get a default value for.
     * @param keys The access keys for AVM.
     * @return the core and display values as name value pairs in a map.
     */
    public Map<String,String> getCoreAndDisplayValues(String attributeAvmRef, Map<String,String> keys) 
    {
        Map<String,String> coreAndDisplayMap = new LinkedHashMap<String,String>();
        try {
            if (!StringUtils.hasText(attributeAvmRef))
                return coreAndDisplayMap;

            AccessKeys accessKeys = getAccessKeys(attributeAvmRef, keys);
            if (accessKeys == null)
                return coreAndDisplayMap;
            coreAndDisplayMap = getCoreDisplayMap(accessKeys);
        } catch (SQLException e) {
            // log message
            throw WrapperException.wrap(e);
        } catch (AVMException e) {
            // log message
            throw WrapperException.wrap(e);
        } catch (Exception e) {
            // log message
            throw WrapperException.wrap(e);
        }
        return coreAndDisplayMap;   
    }

	/**
	 * Returns the AVM field's specified default value.   
	 * @param attributeAvmRef The AVM field to get a default value for.
	 * @param keys The access keys for AVM.
	 * @return the AVM field's specified default value.   
	 */
    public String getDefaultValue(String attributeAvmRef, Map<String,String> keys) 
    {
        String defaultValue = null;
        try {
            if (attributeAvmRef == null)
                return defaultValue;

            AccessKeys accessKeys = getAccessKeys(attributeAvmRef, keys);
            if (accessKeys == null)
                return defaultValue;
            defaultValue = getDefaultValueFromAVM(accessKeys);
        } catch (SQLException e) {
            // log message
            throw WrapperException.wrap(e);
        } catch (AVMException e) {
            // log message
            throw WrapperException.wrap(e);
        } catch (Exception e) {
            // log message
            throw WrapperException.wrap(e);
        }
        return defaultValue;   
    }
}
