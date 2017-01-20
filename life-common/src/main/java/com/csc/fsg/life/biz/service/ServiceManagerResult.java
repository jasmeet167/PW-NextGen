package com.csc.fsg.life.biz.service;

import java.util.ArrayList;
import java.util.List;

import com.csc.fsg.life.biz.bo.XgErrorArea;
import com.csc.fsg.life.biz.exception.BusinessMessage;

/* Modifications: WMA-636 */

/**
 *  ServiceManagerResult contains all the results of a <code>ServiceManager</code> 
 *  event.
 */
public class ServiceManagerResult
{
    /**  list of any returned objects */
	private List<Object> returnObjects = new ArrayList<Object>();

    /**  list of any returned messages */
	private List<BusinessMessage> returnMessages = new ArrayList<BusinessMessage>();
	/**  list CXGERREC records from the REPOS table, can be used for retrieving errors & warnings
	 *   directly from the copy book records 
	 */
	private List<XgErrorArea> statusRecords = new ArrayList<XgErrorArea>();

	/**
	 * Returns the List of return objects.
	 * 
	 * @return the List of return objects.
	 * @see #setReturnObjects
	 */
    public List getReturnObjects()
    {
        return returnObjects;
    }

	/**
	 * Sets the ReturnObjects.
	 * 
	 * @param returnObjects The new ReturnObjects value.
	 * @see #getReturnObjects
	 */
    public void setReturnObjects(List<Object> returnObjects)
    {
        this.returnObjects = returnObjects;
    }

    /**
	 * Adds a return object to the list.
	 * 
	 * @param obj The object to add.
	 */
    public void addReturnObject(Object obj)
    {
        returnObjects.add(obj);
    }
    
	/**
	 * Get the first return object contained in the ServiceManagerResult.
	 * 
	 * @return	first object in returnObjects list
	 * @throws BusinessServiceException
	 */
	public Object getFirstReturnObject() throws BusinessServiceException {
		
		// verify that we have a result
        if (returnObjects == null || returnObjects.size() == 0)
        {
        	BusinessServiceException e = new BusinessServiceException();
        	if (returnMessages != null) {
	        	for (BusinessMessage returnMessage : returnMessages)
	        		e.addMessage(returnMessage);
        	}
        	
        	e.addMessage("No record found");
        	throw e;
        }
        
        return returnObjects.get(0);
	}
	
	/**
	 * Returns the List of return messages.
	 * @return the List of return messages.
	 * @see #setReturnMessages
	 */
	public List<BusinessMessage> getReturnMessages()
	{
		return returnMessages;
	}

	/**
	 * Sets the returnMessages.
	 * @param returnMessages The new returnMessages value.
	 * @see #getReturnMessages
	 */
	public void setReturnMessages(List<BusinessMessage> returnMessages)
	{
		this.returnMessages = returnMessages;
	}

	/**
	 * Adds a return message to the list.
	 * @param obj The message to add.
	 */
	public void addReturnMessage(BusinessMessage obj)
	{
		returnMessages.add(obj);
	}
	/**
	 * @return the statusRecords
	 */
	public List<XgErrorArea> getStatusRecords() {
		return statusRecords;
	}

	/**
	 * @param statusRecords the statusRecords to set
	 */
	public void addStatusRecord(XgErrorArea statusRecord) {
		this.statusRecords.add(statusRecord);
	}
}
