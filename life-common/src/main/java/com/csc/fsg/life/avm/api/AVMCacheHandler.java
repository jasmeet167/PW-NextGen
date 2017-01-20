package com.csc.fsg.life.avm.api;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.TimerTask;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.csc.fsg.life.avm.biz.dao.dataaccessor.AVALFLDCacheCleanDAO;
import com.csc.fsg.life.avm.biz.dao.dataaccessor.AVRNGCacheCleanDAO;
import com.csc.fsg.life.avm.biz.dao.dataaccessor.AVVALCacheCleanDAO;
import com.csc.fsg.life.avm.biz.dao.model.AVALFLDCacheCleanModel;
import com.csc.fsg.life.avm.biz.dao.model.AVRNGCacheCleanModel;
import com.csc.fsg.life.avm.biz.dao.model.AVVALCacheCleanModel;
import com.csc.fsg.life.avm.environments.AVMConfigBean;
import com.csc.fsg.life.avm.lib.exceptions.AVMException;

/**
 * This class is used to manage the cache of allowable values in AVM.
 */
public class AVMCacheHandler
    extends TimerTask
{
    // Logger
    private static Log log = LogFactory.getLog(AVMCacheHandler.class);

    private String applKey;
    private String envrnmntKey;
    private ArrayList<String> activeEnvironmentList = new ArrayList<String>();
    private boolean isCacheActive = false;
    //private String data source;
	private Long cacheIndVal = null;
	private boolean firstTimeCache = false;
    private static AccessKeyCache accessKeyCache = new AccessKeyCache();
    private static FieldAllowableValuesCache fieldAllowableValuesCache = new FieldAllowableValuesCache();
    private AVMConfigBean  avmConfigBean = null;
   
	/**
	 * Creates a cache handler object.
	 * 
	 * @param aApplKey The application to cache allowable values for.
	 * @param aEnvrnmntKey The environment =to cache allowable values for.
	 * @param utilizeCache Is caching used?
	 * @param avmConfigBean The avm configuration bean.
	 */
    protected AVMCacheHandler(String  aApplKey,
                              String  aEnvrnmntKey,
                              boolean utilizeCache,
                              AVMConfigBean  avmConfigBean)
    {
        applKey = aApplKey;
        envrnmntKey = aEnvrnmntKey;
        activeEnvironmentList.add(envrnmntKey);
        isCacheActive = utilizeCache;
        cacheIndVal = CacheIndMgr.getCacheIndVal(avmConfigBean);
		firstTimeCache	= true;
		this.avmConfigBean = avmConfigBean;
	}

    /**
	 * Gets the application key this cache handler is managing.
	 * 
	 * @return the application key this cache handler is managing.
	 */
    public String getApplKey() 
    {
        return applKey;
    }
    
    /**
	 * Gets the environment key this cache handler is managing.
	 * 
	 * @return the environment key this cache handler is managing.
	 */
    public String getEnvrnmntKey() 
    {
        return envrnmntKey;
    }
   
	/**
	 * Add an environment to cache list.
	 * 
	 * @param aEnvrnmntKey The new environment.
	 */
    protected void addActiveEnvrnmntKey(String aEnvrnmntKey) 
    {
        if (activeEnvironmentList.contains(aEnvrnmntKey))
            return;
        activeEnvironmentList.add(aEnvrnmntKey);
        envrnmntKey = "";
        for (int i=0; i<activeEnvironmentList.size(); i++) {
            if (i > 0)
                envrnmntKey += ",";
            envrnmntKey += "'" + (String)activeEnvironmentList.get(i) +"'";
        }
    }
   
	/**
	 * Add a field key to the cache.
	 * 
	 * @param accessKeys The access keys for the field key
	 * @param fieldKey The field key to cache.
	 * @throws AVMException when there is a problem using the access keys as
	 *             keys.
	 */
    public void addAccessKey(AccessKeys accessKeys, String fieldKey) throws AVMException
    {
        if (isCacheActive)
            accessKeyCache.addAccessKey(accessKeys, fieldKey);
    }
   
	/**
	 * Update a field key in the access key cache.
	 * 
	 * @param accessKeys The access keys for the field key
	 * @param fieldKey The field key to cache.
	 * @throws AVMException when there is a problem using the access keys as
	 *             keys.
	 */
    public void updateAccessKey(AccessKeys accessKeys, String fieldKey) throws AVMException
    {
        if (isCacheActive)
            accessKeyCache.updateAccessKey(accessKeys, fieldKey);
    }
   
	/**
	 * Get the field key for the specified access keys in the cache.
	 * 
	 * @param accessKeys The access keys for the field key
	 * @return the field key for the specified access keys in the cache.
	 * @throws AVMException when there is a problem using the access keys as
	 *             keys.
	 */
    public String getFieldForAccessKeys(AccessKeys accessKeys) throws AVMException
    {
       if (isCacheActive)
            return accessKeyCache.getFieldForAccessKeys(accessKeys);
       
       return null;
    }
   
	/**
	 * Add a field to the cache.
	 * 
	 * @param fieldKey The field key to cache.
	 * @param fieldAllowableValues The field data object to cache.
	 */
    public void addFieldToFieldCache(String fieldKey, FieldAllowableValues fieldAllowableValues) 
    {
        if (isCacheActive)
            fieldAllowableValuesCache.addField(fieldKey, fieldAllowableValues);
    }
   
	/**
	 * Get a field by field key from the cache.
	 * 
	 * @param fieldKey The field key to get
	 * @return a field object from the cache.
	 */
    public FieldAllowableValues getFieldKeyFromFieldCache(String fieldKey) 
    {
        if (isCacheActive)
            return fieldAllowableValuesCache.getFieldKeyFromCache(fieldKey);
       
        return null;
    }
   
	/**
	 * Remove a field by field key from the cache.
	 * 
	 * @param fieldKey The field key to remove from the cache.
	 */
    public void removeFieldFromFieldCache(String fieldKey) 
    {
        if (isCacheActive)
            fieldAllowableValuesCache.removeField(fieldKey);
    }
    
	/**
	 * Start the cache management thread.
	 */
    public void run()
	{
    	// Get the current indicator.  
    	// If it is:
    	//   o greater than the last check then update
    	//   o or if it is equal to the last check but the first time indicator is on then update
    	//   o or if it is zero (will be zero when the indicator value wraps)
		Long currCacheIndVal = CacheIndMgr.getCacheIndVal(avmConfigBean);
		if (currCacheIndVal.longValue() > cacheIndVal.longValue() 
		|| (firstTimeCache &&  currCacheIndVal.longValue() == cacheIndVal.longValue())  
		||	currCacheIndVal.longValue() == 0)
			buildCache();
		
		// Always update ind .. so if it is less for some reason (like number wrapping back to 0)
		// we will update the next time.
		cacheIndVal = currCacheIndVal;
	}
    
	/**
	 * Clean the cache of any values that have changed.
	 */
	@SuppressWarnings("unchecked")
	private void buildCache()
    {
    	Connection conn = null;
  		try {
			// get connection manager
			String schema = avmConfigBean.getSchema();
            
			// create AVM Connection
			conn = avmConfigBean.getDataSource().getConnection();
			
			//get AVALFLD changes since last clean
			AVALFLDCacheCleanDAO aVALFLDCacheCleanDAO = new AVALFLDCacheCleanDAO();
			ArrayList paramList = new ArrayList();
			paramList.add(applKey);
			paramList.add(envrnmntKey);
			paramList.add(cacheIndVal);
			List<AVALFLDCacheCleanModel> aVALFLDCacheCleanModelList = aVALFLDCacheCleanDAO.getList(conn, paramList, schema);
			//if FieldKey exists in Field Cache, remove it
			for (int i=0; i<aVALFLDCacheCleanModelList.size(); i++) {
				AVALFLDCacheCleanModel aVALFLDCacheCleanModel = aVALFLDCacheCleanModelList.get(i);
				String fieldKey = aVALFLDCacheCleanModel.getFieldKey();
				if (fieldAllowableValuesCache.isFieldinCache(fieldKey)) {
					fieldAllowableValuesCache.removeField(fieldKey);
				}	
			}
            
			//get AVVAL changes since last clean
			AVVALCacheCleanDAO aVVALCacheCleanDAO = new AVVALCacheCleanDAO();
			List<AVVALCacheCleanModel> aVVALCacheCleanModelList = aVVALCacheCleanDAO.getList(conn, paramList, schema);
			//if FieldKey exists in Field Cache, remove it
			for (int i=0; i<aVVALCacheCleanModelList.size(); i++) {
				AVVALCacheCleanModel aVVALCacheCleanModel = aVVALCacheCleanModelList.get(i);
				String fieldKey = aVVALCacheCleanModel.getFieldKey();
				if (fieldAllowableValuesCache.isFieldinCache(fieldKey)) {
					fieldAllowableValuesCache.removeField(fieldKey);
				}	
			}
            
			//get AVRNG changes since last clean
			AVRNGCacheCleanDAO aVRNGCacheCleanDAO = new AVRNGCacheCleanDAO();
			List<AVRNGCacheCleanModel> aVRNGCacheCleanModelList = aVRNGCacheCleanDAO.getList(conn, paramList, schema);
			//if FieldKey exists in Field Cache, remove it
			for (int i=0; i<aVRNGCacheCleanModelList.size(); i++) {
				AVRNGCacheCleanModel aVRNGCacheCleanModel = aVRNGCacheCleanModelList.get(i);
				String fieldKey = aVRNGCacheCleanModel.getFieldKey();
				if (fieldAllowableValuesCache.isFieldinCache(fieldKey)) {
					fieldAllowableValuesCache.removeField(fieldKey);
				}	
			}
		}
		catch (Exception e) {
			log.error("Cache clean failed");
		}
		finally {
			try {
				firstTimeCache = false;
				if (conn != null)
					conn.close();
			}
			catch (SQLException cme) {
				log.error("Cache clean connection close failed.");
			}
		}
    }
}
