package com.csc.fsg.life.avm.api;

import com.csc.fsg.life.avm.lib.exceptions.AVMException;

/* Modifications: T0140, T0143 */

/**
 * A class used to hold access keys to a field in AVM. The keys are:
 * <UL>
 * <LI>Application - The application to access.</LI>
 * <LI>Environment - The environment to access.</LI>
 * <LI>Page - The page in the application current in use.</LI>
 * <LI>Company - The processing company selected.</LI>
 * <LI>Product - The insurance product selected.</LI>
 * <LI>Trx - The transaction being processed.</LI>
 * <LI>Field - The AVM field name.</LI>
 * </UL>
 * Many of the keys support a wildcard.
 */
public class AccessKeys 
{
	private String applKey;
	private String envrnmntKey;
	private String pageKey;
	private String companyKey;
	private String product;
	private String trxCode;
	private String fieldName;
   
	private String applId;
	private String envrnmntId;
	private String pageId;
	
	/** The AVM Application key. */
	public static final String AvmApplKey = "AVAPPLAVM";
	/** The ALL application environment key. **/
	public static final String AllEnvKey = "AVENVBASE";
	/** The ALL page key. */
	public static final String AllPageKey = "AVPAGEALL";
	/** The ALL company key. */
	public static final String AllCompanyKey = "ALL";
	/** The all Product key. */
	public static final String AllProductKey = "ALL";
	/** The all transaction key. */
	public static final String AllTrxCodeKey = "ALL";
   
	private boolean isAppIdSet = false;
	private boolean isEnvrnmntIdSet = false;
	private boolean isPageIdSet = false;
	
	/** Flag indicating Environment ID was converted successfully to a key. */
	public static final int ENVID_ENABLED = 0x0001;
	/** Flag indicating Application ID was converted successfully to a key. */
	public static final int APPLID_ENABLED = 0x0002;
	/** Flag indicating Page ID was converted successfully to a key. */
	public static final int PAGEID_ENABLED = 0x0004;
	/** Flag indicating Environment ID and Application ID were converted successfully to a key. */
	public static final int ENVID_APPLID_ENABLED =
		ENVID_ENABLED | APPLID_ENABLED;
	/** Flag indicating Environment ID and Page ID were converted successfully to a key. */
	public static final int ENVID_PAGEID_ENABLED =
		ENVID_ENABLED | PAGEID_ENABLED;
	/** Flag indicating Application ID and Page ID were converted successfully to a key. */
	public static final int APPLID_PAGEID_ENABLED =
		APPLID_ENABLED | PAGEID_ENABLED;
	/** Flag indicating Environment ID, Application ID,  and Page ID were converted successfully to a key. */
	public static final int ENVID_APPLID_PAGEID_ENABLED =
		ENVID_APPLID_ENABLED | APPLID_ENABLED | PAGEID_ENABLED;
   

	/**
	   Create an access key object from key fields.  In order to call this method you need
	   to know the internal keys that AVM uses.  It is better to call the other
	   constructor and use the ID's instead.
	   @param aApplKey The application key.
	   @param aEnvrnmntKey The environment key.
	   @param aPageKey The page key.
	   @param aCompanyKey The company code.
	   @param aProduct The product code.
	   @param aTrxCode The trx code.
	   @param aFieldName The field name to access.
	**/
	public AccessKeys(String aApplKey,
					  String aEnvrnmntKey,
					  String aPageKey,
					  String aCompanyKey,
					  String aProduct,
					  String aTrxCode,
					  String aFieldName)
	{
		applKey = aApplKey;
        envrnmntKey = aEnvrnmntKey;
		
        if (aPageKey == null
        ||  aPageKey.trim().length() == 0)
            pageKey = AllPageKey;
        else
            pageKey = aPageKey;
        
        if (aCompanyKey == null
        ||  aCompanyKey.trim().length() == 0)
            companyKey = AllCompanyKey;
        else
            companyKey = aCompanyKey;

        if (aProduct == null
        ||  aProduct.trim().length() == 0)
            product = AllProductKey;
        else
            product = aProduct;

        if (aTrxCode == null
        ||  aTrxCode.trim().length() == 0)
            trxCode = AllTrxCodeKey;
        else
            trxCode = aTrxCode;

        fieldName = aFieldName;
	}
  
	/**
	   Create an access key object from keys and/or ID fields. 
	   @param aApplIdOrKey The application ID or key.
	   @param aEnvrnmntIdOrKey The environment ID or key.
	   @param aPageIdOrKey The page ID or key.
	   @param aCompanyKey The company code.
	   @param aProduct The product code.
	   @param aTrxCode The trx code.
	   @param aFieldName The field name to access.
	   @param idEnabler An integer mask indicating which fields passed in are ID's and which are keys.
	**/
	public AccessKeys(
	  String aApplIdOrKey,
	  String aEnvrnmntIdOrKey,
	  String aPageIdOrKey,
	  String aCompanyKey,
	  String aProduct,
	  String aTrxCode,
	  String aFieldName,
	  int idEnabler) 
	{
		if (isIdSet(idEnabler, APPLID_ENABLED)) {
			applId = aApplIdOrKey;
			isAppIdSet = true;
		}
		if (isIdSet(idEnabler, ENVID_ENABLED)) {
			if (aEnvrnmntIdOrKey == null || aEnvrnmntIdOrKey.trim().length() == 0) {
				envrnmntKey = AllEnvKey;
				isEnvrnmntIdSet = false;
			}
			else{	
				envrnmntId = aEnvrnmntIdOrKey;
				isEnvrnmntIdSet = true;
			}  
		}
		if (isIdSet(idEnabler, PAGEID_ENABLED)) {
			if (aPageIdOrKey == null || aPageIdOrKey.trim().length() == 0) {
				pageKey = AllPageKey;
				isPageIdSet = false; //redundant but better readablity
			} else {
				pageId = aPageIdOrKey;
				isPageIdSet = true;
			}
		} else {
			if (aPageIdOrKey == null || aPageIdOrKey.trim().length() == 0)
				pageKey = AllPageKey;
			else
				pageKey = aPageIdOrKey;
		}
		
		if (aCompanyKey == null || aCompanyKey.trim().length() == 0)
			companyKey = AllCompanyKey;
		else
			companyKey = aCompanyKey;
		
		if (aProduct == null || aProduct.trim().length() == 0)
			product = AllProductKey;
		else
			product = aProduct;
		
		if (aTrxCode == null || aTrxCode.trim().length() == 0)
			trxCode = AllTrxCodeKey;
		else
			trxCode = aTrxCode;
		
		fieldName = aFieldName;
	}
  
   
	/**
	   Transform the ID's in this object to keys.  The keys are stored on this object.
	   @return A integer bit flag indicating which ID's were transformed to keys.
	   @throws AVMException Thrown when the access keys object stores fields as ID's and
	   needs to convert them to a key.  To do this it needs to access the DB to get the 
	   key equivalent.  If there is an error converting the key then this exception is thrown.
	**/
	public int transformIdsToKey() throws AVMException 
	{
		AVMManager avmManager = AVMManager.getInstance(); 
		int idsEnabled = 0;
		if (isAppIdSet) {
			applKey =
				avmManager.getKeyEquivalent(this,applId, AccessKeys.APPLID_ENABLED);
			idsEnabled = idsEnabled | APPLID_ENABLED;
		}
		if (isEnvrnmntIdSet) {
			envrnmntKey =
				avmManager.getKeyEquivalent(this,
											envrnmntId,
											AccessKeys.ENVID_ENABLED);
			idsEnabled = idsEnabled | ENVID_ENABLED;
		}
		if (isPageIdSet) {
			pageKey =
				avmManager.getKeyEquivalent(this,pageId, AccessKeys.PAGEID_ENABLED);
			idsEnabled = idsEnabled | PAGEID_ENABLED;
		}
		return idsEnabled;
	}

	/**
	   Get the access key object as a key string.
	   @return this object as a key string.
	   @throws AVMException Thrown when the access keys object stores fields as ID's and
	   needs to convert them to a key.  To do this it needs to access the DB to get the 
	   key equivalent.  If there is an error converting the key then this exception is thrown.
	**/
	public String getAccessKeysString() 
		throws AVMException 
	{
		if (isAppIdSet || isEnvrnmntIdSet || isPageIdSet) {
			transformIdsToKey();
		}
		return applKey
			+ envrnmntKey
			+ pageKey
			+ companyKey
			+ product
			+ trxCode
			+ fieldName;
	}
   
	/**
	   Access method for the applKey property.
	   @return   the current value of the applKey property
	   @see #setApplKey
    */
	public String getApplKey() 
	{
		return applKey;
	}
   
	/**
	   Access method for the envrnmntKey property.
	   @return   the current value of the envrnmntKey property
	   @see #setEnvrnmntKey
    */
	public String getEnvrnmntKey() 
	{
		return envrnmntKey;
	}
   
	/**
	   Access method for the pageKey property.
	   @return   the current value of the pageKey property
	   @see #setPageKey
	*/
	public String getPageKey() 
	{
		return pageKey;
	}
	
	/**
	   Access method for the companyKey property.
	   @return   the current value of the companyKey property
	   @see #setCompanyKey
    */
	public String getCompanyKey() 
	{
		return companyKey;
	}
	
	/**
	   Access method for the product property.
	   @return   the current value of the product property
	   @see #setProduct
	*/
	public String getProduct() 
	{
		return product;
	}
	
	/**
	  Access method for the trxCode property.
	  @return   the current value of the trxCode property
	  @see #setTrxCode
	*/
	public String getTrxCode() 
	{
		return trxCode;
	}
	
	/**
	   Access method for the fieldName property.
	   @return   the current value of the fieldName property
	   @see #setFieldName
    */
	public String getFieldName() 
	{
		return fieldName;
	}
	
	/**
	   Sets the value of the applKey property.
	   @param aApplKey the new value of the applKey property
	   @see #getApplKey
    */
	public void setApplKey(String aApplKey) 
	{
		applKey = aApplKey.trim();
	}
   
	/**
	   Sets the value of the envrnmntKey property.
	   @param aEnvrnmntKey the new value of the envrnmntKey property
	   @see #getEnvrnmntKey
    */
	public void setEnvrnmntKey(String aEnvrnmntKey) 
	{
        envrnmntKey = aEnvrnmntKey.trim();
	}
   
	/**
	   Sets the value of the pageKey property.
	   @param aPageKey the new value of the pageKey property
	   @see #getPageKey
    */
	public void setPageKey(String aPageKey) 
	{
		if (aPageKey == null
			||  aPageKey.trim().length() == 0)
			pageKey = AllPageKey;
		else
			pageKey = aPageKey.trim();
	}
	
	/**
	   Sets the value of the companyKey property.
	   @param aCompanyKey the new value of the companyKey property
	   @see #getCompanyKey
    */
	public void setCompanyKey(String aCompanyKey) 
	{
		if (aCompanyKey == null
			||  aCompanyKey.trim().length() == 0)
			companyKey = AllCompanyKey;
		else
			companyKey = aCompanyKey.trim();
	}
	
	/**
	   Sets the value of the product property.
	   @param aProduct the new value of the product property
	   @see #getProduct
    */
	public void setProduct(String aProduct) 
	{
		if (aProduct == null
			||  aProduct.trim().length() == 0)
			product = AllProductKey;
		else
			product = aProduct.trim();
	}
   
	/**
	   Sets the value of the trxCode property.
	   @param aTrxCode the new value of the trxCode property
	   @see #getTrxCode
    */
	public void setTrxCode(String aTrxCode) 
	{
		if (aTrxCode == null
			||  aTrxCode.trim().length() == 0)
			trxCode = AllTrxCodeKey;
		else
			trxCode = aTrxCode.trim();
	}
   
	/**
	   Sets the value of the fieldName property.
	   @param aFieldName the new value of the fieldName property
	   @see #getFieldName
    */
	public void setFieldName(String aFieldName) 
	{
		fieldName = aFieldName.trim();
	}
	
	/**
	   Sets the value of the application ID property.
	   @param aApplId the new value of the application ID property
	   @see #getApplId
    */
	public void setApplId(String aApplId) {
		applId = aApplId.trim();
		isAppIdSet = true;
	}

	/**
	   Sets the value of the environment ID property.
	   @param aEnvrnmntId the new value of the environment ID property
	   @see #getEnvrnmntId
    */
	public void setEnvrnmntId(String aEnvrnmntId) {
		envrnmntId = aEnvrnmntId.trim();
		isEnvrnmntIdSet = true;
	}
	
	/**
	   Sets the value of the page ID property.
	   @param aPageId the new value of the page ID property
	   @see #getPageId
    */
	public void setPageId(String aPageId) {
		if (aPageId == null 
		 || aPageId.trim().length() == 0) {
			pageId = "";
			pageKey = AllPageKey;
			isPageIdSet = false;
		} else {
			pageId = aPageId.trim();
			pageKey = aPageId.trim();
			isPageIdSet = true;
		}
	}
	
	/** 
		Returns the application ID (if set). 
		@return the application ID (if set). 
		@see #setApplId
	**/
	public String getApplId() {
		return applId;
	}
	
	/** 
		Returns the environment ID (if set). 
		@return the environment ID (if set). 
		@see #setEnvrnmntId
	**/
	public String getEnvrnmntId() {
		return envrnmntId;
	}

	/** 
		Returns the page ID (if set). 
		@return the page ID (if set). 
		@see #setPageId
	**/
	public String getPageId() {
		return pageId;
	}
   
	/** 
		Returns true if the application ID is set, false if not.
		@return true if the application ID is set, false if not.
	**/
	public boolean isAppIdSet(){
   		return isAppIdSet;			
	}

	/** 
		Returns true if the environment ID is set, false if not.
		@return true if the environment ID is set, false if not.
	**/
	public boolean isEnvrnmntIdSet(){
   		return isEnvrnmntIdSet;
	}

	/** 
		Returns true if the page ID is set, false if not.
		@return true if the page ID is set, false if not.
	**/
	public boolean isPageIdSet(){
   		return isPageIdSet;
	}
	
	/**
	   Checks to see if idToCheck flag is in the idSet int bit flag variable.
	   Called internally and from AVMManager so it is protected.
	   @param idSet the ID bit flag of IDs.
	   @param idToCheck the ID to check for in the bit flag.
	   @return true if found, false if not.
	**/
	protected static boolean isIdSet(int idSet, int idToCheck) {
		return ((idSet & idToCheck) == idToCheck);
	}
   
}
