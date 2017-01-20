/*
 *  This software and/or documentation contains trade secrets and
 *  confidential information, which are proprietary to
 *  Computer Sciences Corporation.
 *  The use, reproduction, distribution, or disclosure of this
 *  software and/or documentation, in whole or in part, without the express
 *  written permission of Computer Sciences Corporation is prohibited.
 *  This software and/or documentation is also an unpublished work protected
 *  under the copyright laws of the United States of America and other countries.
 *  If this software and/or documentation becomes published, the following
 *  notice shall apply:
 *
 *  Copyright 2004 Computer Sciences Corporation. All Rights Reserved.
 */
package com.csc.fsg.life.rules;
import java.util.List;
import java.util.Map;
/**
 * <code>RulesManager</code> interface defines the public API for the
 * fsConnect Rules framework component.
 */
@SuppressWarnings("unchecked")
public interface IRulesManager {
	/**
	 * Constant for core values.
	 */
	public static final String CORE = "CORE";
	/**
	 * Constant for display values.
	 */
	public static final String DISPLAY = "Display";
	/**
	   Returns the core value for a specified attribute. Will return null, if
	   there is no core value satisfying the input keys. 
	   
	   @param objClass the business object class to be used for mapping
	   @param attributeName the simple (unqualified) name of the attribute
	   @param keys a <code>java.util.Map</code> containing the key values to
	   get the domains
	   @param displayValue The display value to get a core value for.
	   @return the core value for the specified attribute
	 */
	public Object getCoreValue(Class objClass, String attributeName, Map<String,String> keys,
			String displayValue);
	/**
	   Returns the allowed core values for a specified attribute.
  
	   @param objClass the business object class containing the specified attribute
	   @param attributeName the simple (unqualified) name of the attribute
	   @param keys  a <code>java.util.Map</code> containing the key values to
	   get the domains
	   @return a <code>java.util.List</code> of the allowed core values for
	   the attribute
	 */
	public List<String> getCoreValues(Class objClass, String attributeName, Map<String,String> keys);
	/**
	   Returns the display value for a specified attribute. Will return null, if
	   there is no display value satisfying the input keys. 
	   
	   @param objClass the business object class to be used for mapping
	   @param attributeName the simple (unqualified) name of the attribute
	   @param keys a <code>java.util.Map</code> containing the key values to
	   get the domains
	   @param coreValue The core value to get a display value for.
	   @return the core value for the specified attribute
	*/
	public Object getDisplayValue(Class objClass, String attributeName, Map<String,String> keys,
			String coreValue);
	/**
	  Returns the allowed core values for a specified attribute.
	  
	  @param objClass the business object class containing the specified attribute
	  @param attributeName the simple (unqualified) name of the attribute
	  @param keys a <code>java.util.Map</code> containing the key values to
	  get the domains
	  @return a <code>java.util.List</code> of the allowed core values for
	  the attribute
	 */
	public List getDisplayValues(Class objClass, String attributeName, Map<String,String> keys);
	/**
	  Validates all simple attributes of the input business object.
	  
	  @param obj the business object class whose simple attributes are to be
	  validated
	  @return a <code>com.csc.fs.Result</code> object containing result data
	  and messages. The <code>isValid</code> indicator is set to
	  <code>false</code> if any attribute fails validation.
	 */
	public Result validateAllFields(Object obj);
	/**
	  This method performs the basic validation for the input object.
	  
	  @param obj the business object to be validated
	  @return a <code>com.csc.fs.Result</code> object containing other result
	  data and messages
	*/
	public Result validateObject(Object obj);
	/**
	  Validates the specified attribute's value.
	  
	  @param obj the business object containing the attribute to be validated
	  @param attributeName the simple (unqualified) name of the attribute to be validated
	  @param keys Allowable Values access keys.
	  @return a <code>com.csc.fs.Result</code> object containing result data
	  and messages. The <code>isValid</code> indicator is set to
	  false if the attribute fails validation.
	 */
	public Result validateAttribute(Object obj, String attributeName, Map<String,String> keys);
	/**
	  Validates the specified value against the specified domain.
	  
	  @param objClass the business object class containing the specified attribute
	  @param attributeName the simple (unqualified) name of the attribute
	  @param value the value to be validated
	  @param keys a <code>java.util.Map</code> containing the key objects for
	  the domain
	  @return a <code>com.csc.fs.Result</code> object containing result data
	  and messages. The <code>isValid</code> indicator is set to
	  false if the value is not valid.
	*/
	public Result validateValue(Class objClass, String attributeName, Object value, Map<String,String> keys);
	
	/**
	   Gets the Default Values for a given attributeName.
	   @param objClass The object to get default value for.
	   @param attributeName The property on that object to get a default value for.
	   @param keys The keys used to access the allowable values.
	   @return The default value.
	*/
	public String getDefaultValue(Class objClass, String attributeName, Map<String,String> keys);
	
    /**
	   Returns the core and display values as name value pairs in a map.
	   @param objClass The class for the field to allowable values for.
	   @param attributeName The field on the class to get allowable values for.
	   @param keys AVM access keys.
	   @return the core and display values as name value pairs in a map.
     */
    public Map<String,String> getCoreAndDisplayValues(Class objClass, String attributeName, Map<String,String> keys);
    
    /**
	   Returns the core and display values as name value pairs in a map.
	   @param attributeAvmRef The AVM field to get a default value for.
	   @param keys The access keys for AVM.
	   @return the core and display values as name value pairs in a map.
     */
    public Map<String,String> getCoreAndDisplayValues(String attributeAvmRef, Map<String,String> keys);
    
	/**
	  Returns the AVM field's specified default value.   
	  @param attributeAvmRef The AVM field to get a default value for.
	  @param keys The access keys for AVM.
	  @return the AVM field's specified default value.   
	 */
    public String getDefaultValue(String attributeAvmRef, Map<String,String> keys);
}
