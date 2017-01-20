package com.csc.fsg.life.biz.service;

import java.util.Iterator;
import java.util.List;

import com.csc.fsg.life.biz.copyobject.CopyObject;
import com.csc.fsg.life.biz.exception.BusinessException;
import com.csc.fsg.life.context.UserContext;

/* Modifications: ENH988, CCCV-E622, T0175 */

/**
 * This class is the superclass for all the business services
 * provided by the application.  It allows for easy injection
 * of service parameters such as event id, as well as, a single
 * place for common processing that should occur on all service
 * invocations.
 */
public abstract class BusinessService
{   
    /** Unique id of the business event. */
    protected String eventId = null;
    private String eventName = null;

	/**
	 * Perform allowable value validation for this service.
	 * Y for Yes, N for No, anything else will defer to the default on the 
	 * User Context.
	 */
	private String validateValues = null;

    /**  Service validator plugin. */
    private ServiceValidator validator = null;

    /**
     * Invokes the business service by performing any common processing,
     * such as:
     * 
     * <ul>
     *  <li>Pre-Validation
     *  <li>Allowable Values Validation if applicable
     *  <li>Service Validation
     *  <li>Post-Validation
     * </ul>
     * 
     * @param param Service parameter
     * @param smi   List of input objects required for this service invocation
     * @throws      BusinessException
     * @throws      ServiceManagerException
     */
    public ServiceManagerResult invoke(ServiceParam param, ServiceManagerInput smi)
        throws BusinessException, ServiceManagerException
    {
        prepareToInvoke(param, smi);
        
        UserContext userContext = param.getUserContext();
        ServiceManager serviceManager = userContext.getApplicationEnvironment().getServiceManager();
        
        return serviceManager.invoke(smi);
    }

    /**
     * Perform all common processing tasks prior to new event 
     * being actually invoked in Service Manager.
     *   
     * @param param Service parameter
     * @param smi   List of input objects required for this service invocation
     * @throws      BusinessException
     */
    protected void prepareToInvoke(ServiceParam param, ServiceManagerInput smi)
    	throws BusinessException
    {
        UserContext userContext = param.getUserContext();
        
        // perform pre validation processing
        preValidationProcessing(param, smi);
    
		// Validate business objects
		if (doValueValidation(userContext))
		{
			List<Object> objects = smi.getInputObjects();
			for(Iterator<Object> itt = objects.iterator(); itt.hasNext();)
			{
				Object o = itt.next();
				if (o instanceof CopyObject)
				{
					CopyObject co = (CopyObject)o;
					co.validate();
				}
			}
		}
    
        // perform any service specific validations
        if (validator != null)
            validator.validate(param);
        
        // perform post validation processing
        postValidationProcessing(param, smi);
    }
    
    /**
     * Hook method intended to be overridden to perform any
     * pre-validation common processing.
     */
    protected void preValidationProcessing(ServiceParam param, ServiceManagerInput smi)
        throws BusinessException
    {
        // do nothing
    }
    
    /**
     * Hook method intended to be overridden to perform any
     * post-validation common processing.
     */
    protected void postValidationProcessing(ServiceParam param, ServiceManagerInput smi) 
        throws BusinessException
    {
        // do nothing
    }
    
	/**
	   Returns the EventId.
	   @return The new EventId value.
	   @see #setEventId
	 **/
    public String getEventId()
    {
        return eventId;
    }

	/**
	   Sets the EventId.
	   @param eventId The new EventId value.
	   @see #getEventId
	 **/
    public void setEventId(String eventId)
    {
        this.eventId = eventId;
    }
    
	/**
	 * Returns the event name.  This should be a short business
	 * description of this service.  If not set, the class name
	 * is returned.
	 */
	public String getEventName()
	{
		if (eventName != null)
			return eventName;

		return getClass().getName();
	}

	/**
	 * Sets the EventName.
	 * @param name The new EventName value.
	 * @see #getEventName
	 */
	public void setEventName(String name)
	{
		eventName = name;
	}
    
    /**
     * Returns the inquiry event id, which is simply the event
     * id suffixed with an "I".
     */
    public String getInquiryEventId()
    {
        return eventId + "I";
    }

    /**
     * Utility method to pad a string with fill characters at the end.
     * @param s The string
     * @param size The size to pad the string to.
     * @param padCharacter The pad character.
     * @return the padded string.
	 */
    public String pad(String s, int size, char padCharacter)
    {
        int length = 0;
        if (s != null) {
            length = s.length();
        }
        StringBuffer sb = new StringBuffer(length);
        if (length < size) {
            for (int i = 0; i < (size - length); i++) {
                sb.append(padCharacter);
            }
        }
        sb.append(s);
        return sb.toString();
    }

	/**
	 * Returns the Validator or null if there isn't one set.
	 * @return The Validator value.
	 * @see #setValidator
	 */
    public ServiceValidator getValidator()
    {
        return validator;
    }

	/**
	 * Sets the Validator.
	 * @param validator The new Validator value.
	 * @see #getValidator
	 */
    public void setValidator(ServiceValidator validator)
    {
        this.validator = validator;
    }

	/**
	 * Return true if value validation should be performed false if not. If a
	 * flag is set on this object, it uses that, if not than it defers the
	 * decision to the user context object.
	 * 
	 * @param userContext the user context.
	 * @return true if value validation should be performed false if not.
	 */
	public boolean doValueValidation(UserContext userContext)
	{
		if (validateValues != null)
		{
			if (validateValues.equalsIgnoreCase("Y"))
				return true;
			if (validateValues.equalsIgnoreCase("N"))
				return false;
		}
		
		// Not set to Y or N for this service so use default on 
		// the user context.
		return userContext.getValidateValues();
	}
	
	/**
	 * Perform allowable value validation for this service. Y for Yes, N for No,
	 * anything else will defer to the default on the User Context.
	 * 
	 * @return the valiadate values flag.
	 */
    public String getValidateValues()
    {
        return validateValues;
    }

	/**
	 * Perform allowable value validation for this service. Y for Yes, N for No,
	 * anything else will defer to the default on the User Context.
	 * 
	 * @param validateValues
	 *            Y for Yes, N for No, anything else will defer to the default
	 *            on the User Context.
	 */
    public void setValidateValues(String validateValues)
    {
        this.validateValues = validateValues;
    }
    
    // override to return right serviceParam for your service 
    public ServiceParam getServiceParam()
    {
    	return  new ServiceParam();
    }
    
	/**
	 * Get the first return object contained in the ServiceManagerResult.
	 * 
	 * @param result ServiceManagerResult
	 * @return	first object in returnObjects list
	 * @throws BusinessServiceException
	 */
	public Object getReturnObject(ServiceManagerResult result) 
		throws BusinessServiceException {
        return result.getFirstReturnObject();
	}
}
