package com.csc.fsg.life.biz.service;

import java.io.Serializable;
import java.text.DecimalFormat;

import com.csc.fsg.life.context.UserContext;
import com.csc.fsg.life.dbutils.UniqueNumberGenerator;

/* Modifications: T0160, T0175, WMA-1638 */

/**
 * A parameter object used to hold general parameters to a BusinessService.
 */
public class ServiceParam
	implements Serializable
{
	/** The user context this service is executing under. */
    protected UserContext userContext;

	/** The real-time indicator R for real-time, D for data collect. */
    protected String realtimeInd = "D";

	/** Should warnings be overridden?  Y for yes, N for no. */
    protected String errorOverrideInd = "N";
    
    private boolean returnOverriddenErrors = false;
	private String dcRcRevisedInd;
	private String dcRcRevisedKey;
    private String trxCode = "";
	
	/** True to use MRO processing, false to turn it off */
	private boolean mroIndicator = false;

	/** The MRO region for this service instance */
	private String mroControlRegion;
	private String mroQueueName;
	private String mroControlUserData;

	// Remove restriction
	private boolean removeRestrict = false;
	
	// Remove suspension
	private boolean removeSuspend = false;
	
	/**
	 * service origin indicator 
	 * Currently defaulted to a space.
	 * 
	 * From UI it should be a 'J' and
	 * from webservices should be a 'W'
	 * as defined in the CXGCOMM1 copybook
	 * */
	
	private String invocationOrigin = "";
	public static final String INVOCATION_UI = "J";
	public static final String INVOCATION_WEB_SERVICES = "W";	
	
    /**
     * Builds a unique message key that will be used to retrieve
     * any records from the XGREPOS table.
     * 
     * @return  unique key
     */
    protected String buildMessageKey()
    {
        DecimalFormat formatter = new DecimalFormat("0000000000000000000");
        return formatter.format(UniqueNumberGenerator.getUniqueLongNumber());
    }

	/**
	 * Returns the user context.
	 * @return the user context.
	 * @see #setUserContext
	 */
    public UserContext getUserContext()
    {
        return userContext;
    }
    
	/**
	 * Sets the UserContext.
	 * @param userContext The new UserContext value.
	 * @see #getUserContext
	 */
    public void setUserContext(UserContext userContext)
    {
        this.userContext = userContext;
    }
    
	/**
	 * Returns the real-time indicator. R for real-time, D for data collect.
	 * @return the real-time indicator. R for real-time, D for data collect.
	 * @see #setRealtimeInd
	 */
    public String getRealtimeInd()
    {
        return realtimeInd;
    }
    
	/**
	 * Sets the RealtimeInd.
	 * @param realtimeInd The new RealtimeInd value.
	 * @see #getRealtimeInd
	 */
    public void setRealtimeInd(String realtimeInd)
    {
        this.realtimeInd = realtimeInd;
    }
    
	/**
	 * Override warnings and errors that can be overridden.
	 * Y - Yes, N - No
	 */
    public String getErrorOverrideInd()
    {
        return errorOverrideInd;
    }
    
	/**
	 * Override warnings and errors that can be overridden.
	 * Y - Yes, N - No
	 */
    public void setErrorOverrideInd(String errorOverrideInd)
    {
        this.errorOverrideInd = errorOverrideInd;
    }

	/**
	 * If warnings are overridden, should they be returned? 
	 * Y - Yes, N - No
	 */
	public boolean getReturnOverriddenErrors()
	{
		return returnOverriddenErrors;
	}

	/**
	 * If warnings are overridden, should they be returned? 
	 * Y - Yes, N - No
	 */
	public void setReturnOverriddenErrors(boolean val)
	{
		returnOverriddenErrors = val;
	}

	/**
	 * Is this service request an event revision?
	 * Null, empty string or space means no.
	 * D means a revision from data collect (DC).
	 * R means a revision from revolving error (RC).
	 */
    public String getDcRcRevisedInd()
    {
        return dcRcRevisedInd;
    }
    
	/**
	 * Is this service request an event revision?
	 * Null, empty string or space means no.
	 * D means a revision from data collect (DC).
	 * R means a revision from revolving error (RC).
	 */
    public void setDcRcRevisedInd(String dcRcRevisedInd)
    {
        this.dcRcRevisedInd = dcRcRevisedInd;
    }

	/**
	 * Is this an event revision.
	 */
	public boolean isRevision()
	{
		if ((dcRcRevisedInd == null) || (dcRcRevisedInd.trim().length() == 0))
			return false;
		return true;
	}

	/**
	 * The key used for service event revision, 
	 */
    public String getDcRcRevisedKey()
    {
        return dcRcRevisedKey;
    }
    
	/**
	 * The key used for service event revision, 
	 */
    public void setDcRcRevisedKey(String dcRcRevisedKey)
    {
        this.dcRcRevisedKey = dcRcRevisedKey;
    }

	/** 
	 * Gets the MroIndicator.
	 * @return True to use MRO processing, otherwise false
	 * @see #setMroIndicator
	 */
	public boolean getMroIndicator()
	{
		return mroIndicator;
	}

	/**
	 * Sets the MroIndicator.
	 * @param val The mew MroIndicator value.
	 * @see #getMroIndicator
	 */
	public void setMroIndicator(boolean val)
	{
		mroIndicator = val;
	}
	
	/** 
	 * Gets the MRO control region 
	 * @see #setMroControlRegion
	 */
	public String getMroControlRegion()
	{
		return mroControlRegion;
	}

	/**
	 * Sets the MroControlRegion.
	 * @param val The new MroControlRegion value.
	 * @see #getMroControlRegion
	 */
	public void setMroControlRegion(String val)
	{
		mroControlRegion = val;
	}

	/**
	 * Returns the MroQueueName.
	 * @return The MroQueueName value.
	 * @see #setMroQueueName
	 */
	public String getMroQueueName()
	{
		return mroQueueName;
	}

	/**
	 * Sets the MroQueueName.
	 * @param val The new MroQueueName value.
	 * @see #getMroQueueName
	 */
	public void setMroQueueName(String val)
	{
		mroQueueName = val;
	}

	/**
	 * Returns the MroControlUserData.
	 * @return The MroControlUserData value.
	 * @see #setMroControlUserData
	 */
	public String getMroControlUserData()
	{
		return mroControlUserData;

	}

	/**
	 * Sets the MroControlUserData.
	 * @param val The new MroControlUserData value.
	 * @see #getMroControlUserData
	 */
	public void setMroControlUserData(String val)
	{
		mroControlUserData = val;
	}

	/**
	 * Returns the RemoveRestrict indicator.
	 * @return The RemoveRestrict value.
	 * @see #setRemoveRestrict
	 */
	public boolean getRemoveRestrict()
	{
		return removeRestrict;
	}

	/**
	 * Sets the RemoveRestrict.
	 * @param val The new RemoveRestrict value.
	 * @see #getRemoveRestrict
	 */
	public void setRemoveRestrict(boolean val)
	{
		removeRestrict = val;
	}

	/**
	 * Returns the RemoveSuspend indicator.
	 * @return The RemoveSuspend value.
	 * @see #setRemoveSuspend
	 */
	public boolean getRemoveSuspend()
	{
		return removeSuspend;
	}
		
	/**
	 * Sets the RemoveSuspend.
	 * @param val The new RemoveSuspend value.
	 * @see #getRemoveSuspend
	 */
	public void setRemoveSuspend(boolean val)
	{
		removeSuspend = val;
	}

    /**
     * Returns the TrxCode.
     * @return the TrxCode value
     * @see #setTrxCode
     */
    public String getTrxCode()
    {
        return trxCode;
    }

    public String getTrxCode4()
    {
        if (trxCode.length() <= 4)
            return trxCode;
        
        String[] trxs = trxCode.split(",");
        if (trxs != null && trxs.length > 0) {
            if (trxs[0].length() > 4)
                return trxs[0].substring(0, 4);
            else
                return trxs[0];
        }
        
        return "";
    }
    
    /**
     * Sets the TrxCode
     * @param trxCode The new TrxCode value
     * @see #getTrxCode
     */
    public void setTrxCode(String trxCode)
    {
        this.trxCode = trxCode;
    }

    public String getInvocationOrigin() {
		return invocationOrigin;
	}

	public void setInvocationOrigin(String invocationOrigin) {
		this.invocationOrigin = invocationOrigin;
	}
}
