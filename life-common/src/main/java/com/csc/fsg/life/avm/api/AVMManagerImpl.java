package com.csc.fsg.life.avm.api;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Timer;

import com.csc.fsg.life.avm.biz.dao.dataaccessor.APIFieldLookUpDAO;
import com.csc.fsg.life.avm.biz.dao.dataaccessor.AVAPENDAO;
import com.csc.fsg.life.avm.biz.dao.dataaccessor.AVAPPLONIDDAOExtn;
import com.csc.fsg.life.avm.biz.dao.dataaccessor.AVRNGFKASDAOExtn;
import com.csc.fsg.life.avm.biz.dao.dataaccessor.AVVALFKASSortedCoreDAOExtn;
import com.csc.fsg.life.avm.biz.dao.dataaccessor.AVVALFKASSortedDisplayDAOExtn;
import com.csc.fsg.life.avm.biz.dao.dataaccessor.FieldInfoDAO;
import com.csc.fsg.life.avm.biz.dao.dataaccessor.GetEnvIdAndKeyDAO;
import com.csc.fsg.life.avm.biz.dao.dataaccessor.GetPageIdInfoDAO;
import com.csc.fsg.life.avm.biz.dao.model.APIFieldLookUpModel;
import com.csc.fsg.life.avm.biz.dao.model.AVAPENModel;
import com.csc.fsg.life.avm.biz.dao.model.AVAPPLModel;
import com.csc.fsg.life.avm.biz.dao.model.AVRNGModel;
import com.csc.fsg.life.avm.biz.dao.model.AVVALModel;
import com.csc.fsg.life.avm.biz.dao.model.AllowableValuesField;
import com.csc.fsg.life.avm.biz.dao.model.FieldInfoModel;
import com.csc.fsg.life.avm.biz.dao.model.GetEnvIdAndKeyModel;
import com.csc.fsg.life.avm.biz.dao.model.GetPageIdInfoModel;
import com.csc.fsg.life.avm.environments.AVMConfigBean;
import com.csc.fsg.life.avm.lib.exceptions.AVMException;

/* Modifications: T0143, WMA-1424, T0175 */

/**
 * This is the main class used to access allowable values. It is a singleton. It
 * is configured with a configuration bean called AVMConfigBean. Typically this
 * is loaded via SPRING but may be loaded other ways. Use the getInstance()
 * method to get an instance of this class.
 */
public class AVMManagerImpl
	extends AVMManager 
{
    private AVMCacheHandler avmCacheHandler;
    
    private static Hashtable<String,String> appEnvHashTable = new Hashtable<String,String>();
	private static Hashtable<String,String> pageIDHashTable = new Hashtable<String,String>();
		
	private static String appKeyForEqvID = null;
	private	String accessCacheStatus = "OFF";
	
	private Hashtable<String,Object> policyAdminHash = new Hashtable<String,Object>();
	
	protected AVMManagerImpl(AVMConfigBean bean)
	{
		super(bean);

		try {
			initializeCacheHandler();
		}
		catch (Exception e) {
			log.error("Cache handler initialization failed with error: " + e.getMessage());
		}
	}
	
	/**
	 * Initialize the cache handler.
	 * @throws AVMException
	 * @throws SQLException
	 */
	protected void initializeCacheHandler() throws AVMException, SQLException {
		String applId 		= avmConfigBean.getApplicationId();
		String envrnmntId 	= avmConfigBean.getEnvironmentId();
		String applKey 		= avmConfigBean.getApplicationKey();
		String envrnmntKey 	= avmConfigBean.getEnvironmentKey();
		accessCacheStatus	= avmConfigBean.getAccessCacheStatus();

		if (applId != null 
		&& !applId.trim().equals("")) {
			appKeyForEqvID 	= loadAppKeyValue(applId,avmConfigBean);
			applKey 		= appKeyForEqvID;
			loadEnvHashTable(applKey, avmConfigBean);
			if (envrnmntId != null) {
				envrnmntKey = (String)appEnvHashTable.get(envrnmntId);
			}
		}

		if (avmConfigBean.isPageDataUtilized())
			loadPageHashTable(applKey, avmConfigBean);
		 
		boolean utilizeCache = false;
		
		String status = avmConfigBean.getStatus();
		if (status.equals("ON"))
		    utilizeCache = true;
		avmCacheHandler = new AVMCacheHandler(applKey, envrnmntKey, utilizeCache, avmConfigBean);
		
		if (status.equals("ON")) {
			// access time to delay (in milliseconds) for Cache process
			int delay = 0;
			String delayStr = avmConfigBean.getDelay();
			if (delayStr != null)
				delay = Integer.valueOf(delayStr).intValue();
            
			// access interval (in milliseconds) to trigger Cache process
			int period = 0;
			String periodStr = avmConfigBean.getPeriod();
			if (periodStr != null)
				period = Integer.valueOf(periodStr).intValue();
            
			// only schedule Cache processing if period is not 0
			if (period != 0) {
				log.info("AVM Caching on");
				Timer timer = new Timer();				 
				timer.schedule(avmCacheHandler, delay, period);
			}
		}
	}
    
	
	/**
	 * Returns the cache handler.
	 * 
	 * @return the cache handler.
	 */
    @Override
	public AVMCacheHandler getAVMCacheHandler() {
        return avmCacheHandler;
    }
    
    /**
	 * Get the specified field values from AVM.  If the field doesn't exist, returns null.
     * @param accessKeys The access keys to get the field.
	 * @param sortingStyle The sort to use for the allowable values returned.
     * @return the specified field values from AVM.  If the field doesn't exist, returns null.
	 * @throws AVMException When an unexpected problem accessing the field in the database occurs.
     */
    @Override
	public IFieldAllowableValues getFieldAllowableValues(AccessKeys accessKeys,
                                                         int        sortingStyle)
        throws AVMException
    {
        Connection conn = null;
        
        try {
            String schema = avmConfigBean.getSchema();
            accessKeys.transformIdsToKey();
            // structure application and Environment keys if not passed
            if (accessKeys.getApplKey() == null
            ||  accessKeys.getApplKey().trim().length() == 0)
                accessKeys.setApplKey(avmCacheHandler.getApplKey());
                
            if (accessKeys.getEnvrnmntKey() == null
            ||  accessKeys.getEnvrnmntKey().trim().length() == 0) {
                accessKeys.setEnvrnmntKey(avmCacheHandler.getEnvrnmntKey());
            }
            
            // builds parameter for cache to acknowledge active environment keys
            avmCacheHandler.addActiveEnvrnmntKey(accessKeys.getEnvrnmntKey());
            
            //Utilize upper case to void case sensitivity
			//log.info("AVM field: " + accessKeys.getFieldName());
            accessKeys.setFieldName(accessKeys.getFieldName().toUpperCase());
            
            // holder for field information matching access keys result
            APIFieldLookUpModel currentAPIFieldLookUpModel = null;
            
            //Obtain FieldKey from AccessKeyCache for AccessKeys
            
            String fieldKey = null;
            if(accessCacheStatus != null && accessCacheStatus.equalsIgnoreCase("ON"))
			fieldKey = avmCacheHandler.getFieldForAccessKeys(accessKeys);
			
            if (fieldKey == null) {
				// create AVMConnection if necessary
				if (conn == null)
					conn = avmConfigBean.getDataSource().getConnection();
				
                //If not in AccessKeyCache, fetch FieldKey and insert into AccessKeyCache
                currentAPIFieldLookUpModel = getAPIField(accessKeys, conn, schema);
                // return null if no field found in AVM
                if (currentAPIFieldLookUpModel == null)
                    return null;
                
                // populate holder for field key of resulting accessKeys fetch
                fieldKey = currentAPIFieldLookUpModel.getFieldKey();
                
                //Add AccessKey to AccessKeyCache
                avmCacheHandler.addAccessKey(accessKeys, fieldKey);
            }

            //Obtain fieldAllowableValues for fieldKey
            FieldAllowableValues fieldAllowableValues = avmCacheHandler.getFieldKeyFromFieldCache(fieldKey);
            if (fieldAllowableValues == null) {
				// create AVMConnection if necessary
				if (conn == null)
					conn = avmConfigBean.getDataSource().getConnection();

                //If FieldAllowableValues not found and AccessKeysCache exists, refetch and update
                if (currentAPIFieldLookUpModel == null) {
                    currentAPIFieldLookUpModel = getAPIField(accessKeys, conn, schema);
                    if (currentAPIFieldLookUpModel == null)
                        return null;
                    fieldKey = currentAPIFieldLookUpModel.getFieldKey();
                    //Update AccessKey in AccessKeyCache
                    avmCacheHandler.updateAccessKey(accessKeys, fieldKey);
                }
                if (currentAPIFieldLookUpModel.getFieldType().equals("L")) {
                    //Obtain Allowable Values for FieldKey
                    List<AVVALModel> aVVALModelList;
                    AVVALModel aVVALModel = new AVVALModel();
                    aVVALModel.setFieldKey(fieldKey);
                    aVVALModel.setAllvalStatus("A");
                    if (sortingStyle == SORT_CORE_VALUES_ASCENDING) {
                        AVVALFKASSortedCoreDAOExtn aVVALFKASSortedCoreDAOExtn = new AVVALFKASSortedCoreDAOExtn();
                        aVVALModelList = aVVALFKASSortedCoreDAOExtn.getList(conn, aVVALModel, schema);
                    }
                    else {
                        AVVALFKASSortedDisplayDAOExtn aVVALFKASSortedDisplayDAOExtn = new AVVALFKASSortedDisplayDAOExtn();
                        aVVALModelList = aVVALFKASSortedDisplayDAOExtn.getList(conn, aVVALModel, schema);
                    }
                    fieldAllowableValues = new FieldAllowableValues(accessKeys,
                                                                    currentAPIFieldLookUpModel,
                                                                    aVVALModelList);
                }
                else {
                    //Obtain Allowable Ranges for FieldKey
                    AVRNGFKASDAOExtn aVRNGFKASDAOExtn = new AVRNGFKASDAOExtn();
                    AVRNGModel aVRNGModel = new AVRNGModel();
                    aVRNGModel.setFieldKey(fieldKey);
                    aVRNGModel.setAllrngStatus("A");
                    List<AVRNGModel> aVRNGModelList = aVRNGFKASDAOExtn.getList(conn, aVRNGModel, schema);
                    fieldAllowableValues = new FieldAllowableValues(accessKeys,
                                                                    currentAPIFieldLookUpModel,
                                                                    aVRNGModelList);
                }
                avmCacheHandler.addFieldToFieldCache(fieldKey, fieldAllowableValues);
            }


            return fieldAllowableValues;
        }
        catch(SQLException e) {
			log.info("avm field: " + accessKeys.getFieldName() + " got exception: " + e.getMessage());
			if (conn != null) {
				try {
					conn.rollback();
				}
				catch (SQLException re) {
					log.error("AVMField, Error with rollback of connection: " + re.getMessage());
				}
			}
            throw new AVMException(e.getMessage());
        }
        finally {
            try {
                if (conn != null)
                    conn.close();
            }
            catch (SQLException cme) {
				log.error("AVMField, Error closing connection: " + cme.getMessage());
            }
        }
    }
    
    @Override
	public List<AllowableValuesField> getListOfPossibleReferences(String fieldName) throws AVMException {
		Connection conn = null;

		AccessKeys accessKeys = new AccessKeys(null,
                null,
                AccessKeys.AllPageKey,
                AccessKeys.AllCompanyKey,
                AccessKeys.AllProductKey,
                AccessKeys.AllTrxCodeKey,
                fieldName);
		
		try {
			String schema = avmConfigBean.getSchema();
			accessKeys.transformIdsToKey();
			
            // structure application and Environment keys if not passed
            if (accessKeys.getApplKey() == null
            ||  accessKeys.getApplKey().trim().length() == 0)
                accessKeys.setApplKey(avmCacheHandler.getApplKey());
                
            if (accessKeys.getEnvrnmntKey() == null
            ||  accessKeys.getEnvrnmntKey().trim().length() == 0) {
                accessKeys.setEnvrnmntKey(avmCacheHandler.getEnvrnmntKey());
            }
            
			// create AVMConnection if necessary
			if (conn == null)
				conn = avmConfigBean.getDataSource().getConnection();

			APIFieldLookUpDAO dao = new APIFieldLookUpDAO();
			List<AllowableValuesField> results = dao.getListOfPossibleReferences(conn, 
																				 accessKeys.getApplKey(), 
																				 accessKeys.getEnvrnmntKey(), 
																				 fieldName,
																				 schema);
			return results;
		} catch (SQLException e) {
			log.info("Error trying to list possible references", e);
			if (conn != null) {
				try {
					conn.rollback();
				} catch (SQLException re) {
					log.error("AVMField, Error with rollback of connection: "
							+ re.getMessage());
				}
			}
			throw new AVMException(e.getMessage());
		} finally {
			try {
				if (conn != null)
					conn.close();
			} catch (SQLException cme) {
				log.error("AVMField, Error closing connection: "
						+ cme.getMessage());
			}
		}
	}
    
    private static APIFieldLookUpModel getAPIField(AccessKeys    accessKeys,
                                                   Connection    conn,
                                                   String        schema)
        throws SQLException
    {
        // obtain fieldKey based on accessKeys
        APIFieldLookUpDAO apiFieldLookUpDAO = new APIFieldLookUpDAO();
        ArrayList<String> paramList = new ArrayList<String>();
        paramList.add(accessKeys.getApplKey());
        paramList.add(accessKeys.getEnvrnmntKey());
        paramList.add(accessKeys.getPageKey());
        paramList.add(accessKeys.getCompanyKey());
        paramList.add(accessKeys.getProduct());
        paramList.add(accessKeys.getTrxCode());
        paramList.add(accessKeys.getFieldName());
        List<APIFieldLookUpModel> apifieldLookUpModelList = apiFieldLookUpDAO.getList(conn, paramList, schema);
        int currentKeyCount = 0;
        int currentFieldMatch = -1;
        for (int i=0; i<apifieldLookUpModelList.size(); i++) {
            APIFieldLookUpModel apiFieldLookUpModel = (APIFieldLookUpModel)apifieldLookUpModelList.get(i);
            int matchKeyCount = 0;
            if (accessKeys.getApplKey().equals(apiFieldLookUpModel.getApplKey()))
                matchKeyCount++;
            if (accessKeys.getEnvrnmntKey().equals(apiFieldLookUpModel.getEnvrnmntKey()))
                matchKeyCount++;
            if (accessKeys.getPageKey().equals(apiFieldLookUpModel.getPageKey()))
                matchKeyCount++;
            if (accessKeys.getCompanyKey().equals(apiFieldLookUpModel.getCompanyKey()))
                matchKeyCount++;
            if (accessKeys.getProduct().equals(apiFieldLookUpModel.getProduct()))
                matchKeyCount++;
            if (accessKeys.getTrxCode().equals(apiFieldLookUpModel.getTrxCode()))
                matchKeyCount++;
            if (matchKeyCount > currentKeyCount) {
                currentKeyCount = matchKeyCount;
                currentFieldMatch = i;
            }
        }
        
        if (currentFieldMatch != -1) {        	
			return (APIFieldLookUpModel)apifieldLookUpModelList.get(currentFieldMatch);
        }
            
        
        return null;
    }
    
	private String loadAppKeyValue(String applicationId, AVMConfigBean avmConfigBean) 
		throws AVMException,SQLException 
	{
		AVAPPLONIDDAOExtn avAPPLONIDDAOExtn = new AVAPPLONIDDAOExtn();
		Connection conn = null;
		String applicationKey = null;
		try {
			String schema = avmConfigBean.getSchema();
			AVAPPLModel aVAPPLModel	= new AVAPPLModel();
			aVAPPLModel.setApplId(applicationId);
			if (conn == null)
				conn = avmConfigBean.getDataSource().getConnection();
			List<AVAPPLModel> aVAPPList = avAPPLONIDDAOExtn.getList(conn,aVAPPLModel,schema);
			if (aVAPPList.size() > 0) {
				aVAPPLModel = aVAPPList.get(0);
			}
			applicationKey = aVAPPLModel.getApplKey();
			return applicationKey;
		} 
		catch(SQLException e) {
			if (conn != null)
			throw new AVMException(e.getMessage());
		}
		finally {
				try {
                    if (conn != null)
                        conn.close();
				}
				catch (SQLException cme) {
					log.error("loadAppKeyValue, Error closing connection.");
				}
		}
		return null;
	}
	
	private static void loadEnvHashTable(String applicationKey, AVMConfigBean avmConfigBean) 
		throws AVMException
	{
		GetEnvIdAndKeyDAO getEnvIdAndKeyDAO = new GetEnvIdAndKeyDAO();
		Connection conn = null;

		try {
			String schema = avmConfigBean.getSchema();
			if (conn == null)
				conn = avmConfigBean.getDataSource().getConnection();
			ArrayList<String> aList	= new ArrayList<String>();
			aList.add(applicationKey);
			List<GetEnvIdAndKeyModel> envIDLst = getEnvIdAndKeyDAO.getList(conn,aList,schema);
			Iterator<GetEnvIdAndKeyModel> itx = envIDLst.iterator();
			String envId="", envKey	= "";
			GetEnvIdAndKeyModel getEnvIdAndKeyModel;
			while(itx.hasNext()){
				getEnvIdAndKeyModel = (GetEnvIdAndKeyModel)itx.next();
				if (getEnvIdAndKeyModel != null) {
					envId = getEnvIdAndKeyModel.getEnvrnmntId();
					envKey = getEnvIdAndKeyModel.getEnvrnmntKey();
					if(envId != null) {
						appEnvHashTable.put(envId,envKey);
					}	
				}
			}
		} 
		catch (SQLException e) {
			if (conn != null)
			throw new AVMException(e.getMessage());
		}
		finally {
			try {
				if (conn != null)
				conn.close();
			}
			catch (SQLException cme) {
				log.error("loadEnvHashTable, Error closing connection.");
			}
		}
	}
	
	private void loadPageHashTable(String applicationKey, AVMConfigBean avmConfigBean) throws AVMException,SQLException
	{
		GetPageIdInfoDAO getPageIdInfoDAO = new GetPageIdInfoDAO();	
		Connection conn = null;
		try {
			String schema 	= 	avmConfigBean.getSchema();
			if (conn == null)
			conn = avmConfigBean.getDataSource().getConnection();
			
			ArrayList<String> aList	= new ArrayList<String>();
			aList.add(applicationKey);
			List<GetPageIdInfoModel> pageIDList = getPageIdInfoDAO.getList(conn,aList,schema);
			Iterator<GetPageIdInfoModel> itx = pageIDList.iterator();
			String pageId = "", pageKey = "",applKey="", envrmntKey="";
			GetPageIdInfoModel getPageIdInfoModel;
			while(itx.hasNext()) {
				getPageIdInfoModel = (GetPageIdInfoModel)itx.next();
				if (getPageIdInfoModel != null) {
					pageId = getPageIdInfoModel.getPageId();
					pageKey	= getPageIdInfoModel.getPageKey();
					applKey = getPageIdInfoModel.getApplKey();
					envrmntKey = getPageIdInfoModel.getEnvrnmntKey();
					if (applKey != null && envrmntKey != null && pageId != null ) {
						pageIDHashTable.put(String.valueOf(applKey+"~"+pageId).toUpperCase(),
																				pageKey);
					}	
				}
			}
		} 
		catch (SQLException e) {
			if (conn != null)
			throw new AVMException(e.getMessage());
		}
		finally {
			try {
				if (conn != null)
					conn.close();
			} catch (SQLException cme) {
				log.error("loadPageHashTable, Error closing connection.");
			}
		}
	}

	/**
	 * getPageInfo: return a Hashtable of PageInfo objects for all pages
	 * associated with the given application and environment.  Includes a
	 * PageInfo object for the wildcard page (AVPAGEALL) keyed by the page
	 * id "ALL".
	 * 
	 * The end result is access to page descriptions for all pages, and field
	 * descriptions for all fields in all pages.
	 * 
	 * @param applId Application id used to get pages.
	 * @param envrnmntId Environment id used to get pages.
	 * @return Hashtable of PageInfo objects
	 * @throws AVMException
	 */
	@Override
	public Hashtable<String, PageInfo> getPageInfo(String applId, String envrnmntId) 
		throws AVMException
	{
		Hashtable<String, PageInfo> pageFieldHashTable = new Hashtable<String, PageInfo>();
		String envrnmntKey = (String)appEnvHashTable.get(envrnmntId);
		
		Connection conn = null;
		try {
			String schema = avmConfigBean.getSchema();
			conn = avmConfigBean.getDataSource().getConnection();
			FieldInfoDAO fieldInfoDAO = new FieldInfoDAO();	
			FieldInfoModel fieldInfoModel = null;
			
			ArrayList<String> fieldArgs	= new ArrayList<String>();
			fieldArgs.add(appKeyForEqvID);
			fieldArgs.add(envrnmntKey);
			List<FieldInfoModel> fieldList =  fieldInfoDAO.getList(conn,fieldArgs,schema);
			Iterator<FieldInfoModel> fit = fieldList.iterator();
			String pageKey, pageId, pageDesc, fieldName, fieldDesc;
			String curPageKey = "";
			PageInfo pageInfo = null;
			while (fit.hasNext()) {
				fieldInfoModel = fit.next();
				if (fieldInfoModel != null) {
					// each entry is a field in a page
					pageKey = fieldInfoModel.getPageKey();
					pageId = fieldInfoModel.getPageId();
					pageDesc = fieldInfoModel.getPageDesc();
					fieldName = fieldInfoModel.getFieldName();
					fieldDesc = fieldInfoModel.getFieldDesc();

					if (!curPageKey.equals(pageKey)) {
						// starting a new page: create a PageInfo object
						pageInfo = new PageInfo();
						pageInfo.setPageDesc(pageDesc);
						pageInfo.setFieldInfo(new Hashtable<String, String>());
						// add the the PageInfo to the return Hashtable
						pageFieldHashTable.put(pageId.toUpperCase(), pageInfo);
						curPageKey = pageKey;
					}
					
					// add the field to the PageInfo field Hashtable
					if (pageInfo != null) {
						if ((pageInfo.getFieldInfo().get(fieldName) == null) 
						|| !fieldInfoModel.getEnvrnmntKey().equals(AccessKeys.AllEnvKey)) 
							pageInfo.getFieldInfo().put(fieldName, fieldDesc);
					}
				}
			}
			
		} catch(SQLException e) {
			if (conn != null)
				throw new AVMException(e.getMessage());
		} finally {
			try {
				if (conn != null)
					conn.close();
			} catch (SQLException cme) {
				log.error("getPageInfo, Error closing connection.");
			}
		}
			
		return pageFieldHashTable;
	}

	/**
	 * Converts a ID to a key.
	 * 
	 * @param aKeys 	The access keys - used for conversion.
	 * @param idVal 	The ID.
	 * @param maskVal	The ID type to convert. One of the constants in AccessKeys.
	 * @return The key for the specified ID.
	 * @throws AVMException
	 */
	@Override
	protected String getKeyEquivalent(AccessKeys aKeys, String idVal, int maskVal) 
		throws AVMException
	{
		String returnKeyVal = null;
		if(AccessKeys.isIdSet(maskVal,AccessKeys.APPLID_ENABLED)) {
			returnKeyVal = appKeyForEqvID;	
		}
		else if(AccessKeys.isIdSet(maskVal,AccessKeys.ENVID_ENABLED)) {
			returnKeyVal = (String) appEnvHashTable.get(idVal);
			if(returnKeyVal == null) {
				if(aKeys.isAppIdSet()){
					loadEnvHashTable(getKeyEquivalent(aKeys,aKeys.getApplId(),AccessKeys.APPLID_ENABLED), avmConfigBean);
				}
				else {
					loadEnvHashTable(aKeys.getApplKey(), avmConfigBean);
				}
				returnKeyVal = (String) appEnvHashTable.get(idVal);
			}
		}
		else if(AccessKeys.isIdSet(maskVal,AccessKeys.PAGEID_ENABLED)) {
			String pageIdHashKey = "";
			if (aKeys.isAppIdSet()){
				pageIdHashKey = pageIdHashKey +getKeyEquivalent(aKeys,aKeys.getApplId(),AccessKeys.APPLID_ENABLED)+"~";
			}
			else {
				pageIdHashKey = pageIdHashKey + aKeys.getApplKey()+"~";
			}

			pageIdHashKey = pageIdHashKey + idVal;
			if(pageIdHashKey != null){
				pageIdHashKey = pageIdHashKey.toUpperCase();
			}
			returnKeyVal = (String) pageIDHashTable.get(pageIdHashKey);
		}
		if (returnKeyVal == null)
			throw ( new AVMException("envid/appid/pageid not found in mapping hashtable"));
		
		return returnKeyVal;
	}

	/**
	 * Get a list of environments for the specified application.
	 * 
	 * @param appId The application ID to get environments for.
	 * @return A list of environments for the specified application.
	 */
	@Override
	public ArrayList<AVMIdDescPair> getEnvironmentsForAppID(String appId){
		ArrayList<AVMIdDescPair> envList = null;
		Connection conn = null;
		try {
			conn = avmConfigBean.getDataSource().getConnection();
	        String schema 	= avmConfigBean.getSchema();	
	        AVAPENModel avAPENModel = new AVAPENModel();
	        avAPENModel.setApplKey(appKeyForEqvID);
	        envList = (new AVAPENDAO()).getEnvList(conn, avAPENModel,schema);
		} catch(Exception exp){
			log.error("Error getting environments for specified application: " + appId);
		} finally {
			try {
				if (conn != null)
					conn.close();
			}
			catch (SQLException cme) {
				log.error("getEnvironmentsForAppID connection close failed.");
			}
		}
		return envList;
	}
	
	/**
	 * Get a list of environments for the specified application key.
	 * 
	 * @param appKey The application Key to get environments for.
	 * @return A list of environments for the specified application.
	 */
	@Override
	public ArrayList<AVMIdDescPair> getEnvironmentsForAppKey(String appKey)
	{
		ArrayList<AVMIdDescPair> envList = null;
		Connection conn = null;
		try {
			conn = avmConfigBean.getDataSource().getConnection();
	        String schema 	= avmConfigBean.getSchema();	
	        AVAPENModel avAPENModel = new AVAPENModel();
	        avAPENModel.setApplKey(appKey);
	        envList = (new AVAPENDAO()).getEnvList(conn, avAPENModel,schema);
		} catch(Exception exp){
			log.error("Error getting environments for specified application key: " + appKey);
		} finally {
			try {
				if (conn != null)
					conn.close();
			}
			catch (SQLException cme) {
				log.error("getEnvironmentsForAppKey connection close failed.");
			}
		}
		return envList;
	}
	

	/**
	 * Get a list of companies for the specified application.
	 * 
	 * @param appId The application ID to get companies for.
	 * @return A list of companies for the specified application.
	 */
	@Override
	public ArrayList<AVMIdDescPair> getCompaniesForAppID(String appId)
	{
		ArrayList<AVMIdDescPair> envList = null;
		ArrayList<AVMIdDescPair> companiesList = new ArrayList<AVMIdDescPair>();
        String ALL_PAGE_KEY		= "ALL";
        String ALL_COMPANY_KEY 	= "ALL";
        String ALL_PRODUCT		= "ALL";
        String ALL_TRXCODE		= "ALL";
        String AVM_COMPANY_FIELD = "AVMCompany";
        Connection conn = null;
		try {
	        conn = 	avmConfigBean.getDataSource().getConnection();
	        String schema 	= avmConfigBean.getSchema();
	        AVAPENModel avAPENModel = new AVAPENModel();
	        avAPENModel.setApplKey(appKeyForEqvID);
	        envList = (new AVAPENDAO()).getEnvListAsKeys(conn, avAPENModel,schema);
	        Iterator<AVMIdDescPair> itr= envList.iterator();
	        AVMIdDescPair avmIdDescPr, avmIdDescPr1;
	        Hashtable<String, String> compIdNDesc = new Hashtable<String, String>();
	        while (itr.hasNext()) {
	        	avmIdDescPr = itr.next();
	        	AccessKeys aKeys = new AccessKeys(appKeyForEqvID,avmIdDescPr.getIdVal(),ALL_PAGE_KEY,ALL_COMPANY_KEY,
	        					  		ALL_PRODUCT,ALL_TRXCODE,AVM_COMPANY_FIELD);
	    		IFieldAllowableValues iav =	getFieldAllowableValues(aKeys,AVMManager.SORT_CORE_VALUES_ASCENDING);
	    		AllowableValues av = iav.getAllowableValues();
	    		List<String> coreValues = ((ListAllowableValues) av).getCoreValues();
	    		List<String> displayValues = ((ListAllowableValues) av).getDisplayValues();
	    		for (int i = 0; i < coreValues.size(); i++) {
	    			String coreValue = (String) coreValues.get(i);
	    			String displayValue = (String) displayValues.get(i);
	    			if(!compIdNDesc.containsKey(coreValue)){
	    				compIdNDesc.put(coreValue, displayValue);
	    				avmIdDescPr1 = new AVMIdDescPair(coreValue,displayValue);
	    				companiesList.add(avmIdDescPr1);
	    			}
	    		}
	        }
		} catch(Exception exp){
			log.error("Error getting companys for specified application: " + appId);
		} finally {
			try {
				if (conn != null)
					conn.close();
			}
			catch (SQLException cme) {
				log.error("getCompaniesForAppID connection close failed.");
			}
		}
		return companiesList;
	}

	/**
	   Get the policy admin hash.
	   @return the policy admin hash.
	   @see #setPolicyAdminHash
	**/
	@Override
	public Hashtable<String, Object> getPolicyAdminHash() {
		return policyAdminHash;
	}

	/**
	 * Get the application from The policy admin hash.
	 * 
	 * @see #setPolicyAdminForAppl
	 */
	@Override
	public Object getPolicyAdminForAppl(String applName){
		return policyAdminHash.get(applName);
	}

	/**
	 * Set the policy admin hash.
	 * 
	 * @param policyAdminHash
	 * @see #getPolicyAdminHash
	 */
	@Override
	public void setPolicyAdminHash(Hashtable<String, Object> policyAdminHash) {
		this.policyAdminHash = policyAdminHash;
	}

	/**
	 * @see #getPolicyAdminForAppl
	 */
	@Override
	public void setPolicyAdminForAppl(String applName,Object polcyAdmin){
		this.policyAdminHash.put(applName,polcyAdmin);
	}
}
