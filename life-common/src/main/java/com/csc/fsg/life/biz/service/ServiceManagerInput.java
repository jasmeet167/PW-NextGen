package com.csc.fsg.life.biz.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.csc.fsg.life.biz.bo.BatchInputItem;

/* Modifications: ENH988, ENH1107 */

/**
 * A parameter class used to hold input to a service manager request.
 */
public class ServiceManagerInput
{
    // service parameters
    private ServiceParam serviceParam = null;
    
    /**
     * If this instance of ServiceManagerInput is used to wrap input 
     * for a Batch Event, eventIdMap contains individual value of eventId 
     * for each input object, keyed by the actual input object.
     * <p>
     * Otherwise eventIdMap contains one entry with null key, 
     * and value of eventId applicable to all transactions in the event.
     */
    private Map<Object, String> eventIdMap = new HashMap<Object, String>();

    private String eventBatchOrigin = "";
    private String eventName = null;
    private String eventMode = null;
    private boolean isTrxOrderEnforced = false;

    // list of objects that are used for input
    private List<Object> inputObjects = new ArrayList<Object>();

	// Service class invoking this event.
	private BusinessService service;
    
	// Event Modes
	public static final String EVENT_MODE_INQUIRY = "I";
	public static final String EVENT_MODE_ADD = "A";
	public static final String EVENT_MODE_UPDATE = "U";
	public static final String EVENT_MODE_DELETE = "D";
	
	/**
	 * Create a new service manager input class.
	 * 
	 * @param serviceParam The service parameter object.
	 * @param eventId The event id for the service being executed.
	 * @param service The service being invoked.
	 */
	public ServiceManagerInput(ServiceParam serviceParam, String eventId, BusinessService service)
	{
	    this(serviceParam, eventId, service, "");
	}

	/**
	 * Create a new service manager input class.
	 * 
	 * @param serviceParam The service parameter object.
	 * @param eventId The event id for the service being executed.
	 * @param service The service being invoked.
	 * @param service The service being invoked.
	 */
	public ServiceManagerInput(ServiceParam serviceParam, String eventId, BusinessService service, String eventMode)
	{
		this.serviceParam = serviceParam;
		setEventId(eventId);
		this.service = service;
		this.eventMode = eventMode;
	}
 
	/**
	 * Returns the EventId.
	 * 
	 * @return The EventId value.
	 * @see #setEventId
	 */
    public String getEventId()
    {
        return eventIdMap.get(null);
    }

	/**
	 * Sets the EventId.
	 * 
	 * @param eventId The new EventId value.
	 * @see #getEventId
	 */
    public void setEventId(String eventId)
    {
    	eventIdMap.put(null, eventId);
    }

	/**
	 * Return eventId associated with the given instance 
	 * of <em>inputObject</em>.
	 * 
	 * @param inputObject
	 *        The instance of input object, whose 
	 *        corresponding eventId is to be returned. 
	 *        
	 * @return The eventId value.
	 * @see #setEventId(Object, String)
	 */
    public String getEventId(Object inputObject)
    {
   		return eventIdMap.get(inputObject);
    }

	/**
	 * Set value of eventId associated with the given 
	 * <em>inputObject</em>.
	 *
	 * @param inputObject
	 *        The instance of input object, whose
	 *        corresponding value of eventId is 
	 *        to be set.
	 *         
	 * @param eventId 
	 *        The new eventId value.
	 *        
	 * @see #getEventId(Object)
	 */
    public void setEventId(Object inputObject, String eventId)
    {
    	eventIdMap.put(inputObject, eventId);
    }

	/**
	 * @return Current value of the property <em>eventBatchOrigin</em>.
	 */
	public String getEventBatchOrigin()
	{
		return eventBatchOrigin;
	}

	/**
	 * @param eventBatchOrigin 
	 *        New value of the property <em>eventBatchOrigin</em>. 
	 */
	public void setEventBatchOrigin(String eventBatchOrigin)
	{
		if (eventBatchOrigin == null)
			this.eventBatchOrigin = "";
		else
			this.eventBatchOrigin = eventBatchOrigin;
	}

	/**
	 * @return A flag indicating whether the service manager input 
	 *         contained in this object is to be used for a Batch Event.
	 */
	public boolean isBatchEventInput()
	{
		return eventBatchOrigin.trim().length() > 0;
	}
	
	/**
	 * Returns the EventName. If the event name is not set on the input object,
	 * then it is taken from the service.
	 * 
	 * @return The event name.
	 * @see #setEventName
	 */
    public String getEventName()
    {
		// First return specific name if set.
		if (eventName != null)
			return eventName;

		// Else, if the service is available return that.
		if (service != null) 
			return service.getEventName();

		return "";
    }

	/**
	 * Sets the EventName.
	 * 
	 * @param eventName The new EventName value.
	 * @see #getEventName
	 */
    public void setEventName(String eventName)
    {
        this.eventName = eventName;
    }

	/**
	 * Returns the EventMode.
	 * 
	 * @return The EventMode value.
	 * @see #setEventMode
	 */
    public String getEventMode()
    {
    	return eventMode;
    }

	/**
	 * Sets the EventMode.
	 * 
	 * @param eventId The new EventMode value.
	 * @see #getEventMode
	 */
    public void setEventMode(String eventMode)
    {
    	this.eventMode = eventMode;
    }
    
	/**
	 * Return value of the property {@code trxOrderEnforced}
	 * 
	 * @return Current value of the property {@code trxOrderEnforced}
	 * @see #setTrxOrderEnforced(boolean)
	 */
	public boolean isTrxOrderEnforced()
	{
		return isTrxOrderEnforced;
	}

	/**
	 * Set value of the property {@code trxOrderEnforced}
	 * 
	 * @param isTrxOrderEnforced
	 *        New value of the property {@code trxOrderEnforced}
	 * @see #getTrxOrderEnforced()
	 */
	public void setTrxOrderEnforced(boolean isTrxOrderEnforced)
	{
		this.isTrxOrderEnforced = isTrxOrderEnforced;
	}

    /**
	 * Returns the business Service.
	 * 
	 * @return The business service.
	 * @see #setService
	 */
	public BusinessService getService() 
	{
		return service; 
	}

	/**
	 * Sets the business Service.
	 * 
	 * @param service The new Service value.
	 * @see #getService
	 */
	public void setService(BusinessService service) 
	{ 
		this.service = service; 
	}

	/**
	 * Returns the list of input objects.
	 * 
	 * @return the list of input objects.
	 * @see #setInputObjects
	 */
    public List<Object> getInputObjects()
    {
        return inputObjects;
    }

	/**
	 * Sets the InputObjects. This replaces any previously set input objects.
	 * 
	 * @param inputObjects The new InputObjects value as a list.
	 * @see #getInputObjects
	 */
    public void setInputObjects(List<Object> inputObjects)
    {
        this.inputObjects = inputObjects;
    }
   
	/**
	 * Adds an input object to the current list of input objects.
	 * 
	 * @param obj The object to add.
	 */
    public void addInputObject(Object obj)
    {
        inputObjects.add(obj);
    }

	/**
	 * Adds an input object to the current list of input objects.
	 * 
	 * @param item
	 * 		  An object, which contains both the object to add, and 
	 *        eventId attribute of the transaction, which is to be 
	 *        triggered on behalf of the given <em>item</em>.
	 */
    public void addInputObject(BatchInputItem item)
    {
    	Object inputObject = item.getCopyObject();
    	String trxEventId = item.getTrxEventId();
    	
    	eventIdMap.put(inputObject, trxEventId);
    	addInputObject(inputObject);
    }

	/**
	 * Returns the ServiceParam.
	 * 
	 * @return The ServiceParam value.
	 * @see #setServiceParam
	 */
    public ServiceParam getServiceParam()
    {
        return serviceParam;
    }

	/**
	 * Sets the ServiceParam.
	 * 
	 * @param serviceParam The new ServiceParam value.
	 * @see #getServiceParam
	 */
    public void setServiceParam(ServiceParam serviceParam)
    {
        this.serviceParam = serviceParam;
    }
}
