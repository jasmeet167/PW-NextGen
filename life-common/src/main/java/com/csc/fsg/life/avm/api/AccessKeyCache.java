package com.csc.fsg.life.avm.api;

import java.util.HashMap;

import com.csc.fsg.life.avm.lib.exceptions.AVMException;


/**
   This class maintains a cache of access keys -> field keys.
**/
public class AccessKeyCache 
{
   private HashMap<String, String> accessKeyCache = new HashMap<String, String>();
   
   /**
	  Add an access key / field key tuple to the cache.
	  @param accessKeys The access key used to get the field key.
	  @param fieldKey The field key for the specified access key.
	  @throws AVMException Thrown when the access keys object stores fields as ID's and
	  needs to convert them to a key.  To do this it needs to access the DB to get the 
	  key equivalent.  If there is an error converting the key then this exception is thrown.
   */
   protected void addAccessKey(AccessKeys accessKeys, String fieldKey) throws AVMException
   {
        accessKeyCache.put(accessKeys.getAccessKeysString(), fieldKey);
   }
   
   /**
	  Updates an access key / field key tuple to the cache.
	  @param accessKeys The access key used to get the field key.
	  @param fieldKey The field key for the specified access key.
	  @throws AVMException Thrown when the access keys object stores fields as ID's and
	  needs to convert them to a key.  To do this it needs to access the DB to get the 
	  key equivalent.  If there is an error converting the key then this exception is thrown.
   */
   protected void updateAccessKey(AccessKeys accessKeys, String fieldKey) throws AVMException
   {
        accessKeyCache.remove(accessKeys.getAccessKeysString());
        addAccessKey(accessKeys, fieldKey);
   }
   
   /**
	  Retrieves a field key for an access key from the cache.
	  @param accessKeys The access key used to get the field key.
	  @return The field key or null if not found.
	  @throws AVMException Thrown when the access keys object stores fields as ID's and
	  needs to convert them to a key.  To do this it needs to access the DB to get the 
	  key equivalent.  If there is an error converting the key then this exception is thrown.
   */
   protected String getFieldForAccessKeys(AccessKeys accessKeys) throws AVMException
   {
       String accessKeysString = accessKeys.getAccessKeysString();
       if (accessKeyCache.containsKey(accessKeysString))
           return accessKeyCache.get(accessKeysString);
       
       return null;
   }
}

