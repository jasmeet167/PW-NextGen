package com.csc.fsg.life.avm.api;

import java.util.HashMap;

/**
   A cache of field key -> FieldAllowableValues object.
**/
public class FieldAllowableValuesCache 
{
	private HashMap<String, FieldAllowableValues> allowableValuesCache = new HashMap<String, FieldAllowableValues>();
	
	/**
	   Add a field to the cache.
	   @param fieldKey The field key.
	   @param fieldAllowableValues The allowable values object for the field to cache.
    */
	protected void addField(String fieldKey, FieldAllowableValues fieldAllowableValues) 
	{
        allowableValuesCache.put(fieldKey, fieldAllowableValues);
	}
	
	/**
	   Gets a field from the cache.
	   @param fieldKey The field key.
	   @return The allowable values object for the specified field key.
    */
	protected FieldAllowableValues getFieldKeyFromCache(String fieldKey) 
	{
		return allowableValuesCache.get(fieldKey);
	}
	
	/**
	   Remove a field from the cache.
	   @param fieldKey The field key to remove.
	*/
	protected void removeField(String fieldKey) 
	{
        allowableValuesCache.remove(fieldKey);
	}
	
	/**
	   Returns true if the field is in the cache and false if not.
	   @param fieldKey The field key to check.
	   @return true if the field is in the cache and false if not.
	**/
	protected boolean isFieldinCache(String fieldKey) 
	{
        return allowableValuesCache.containsKey(fieldKey);
	}
}
