package com.csc.fsg.life.biz.service;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

/* Modifications: ENHT0085, P0157 */

/**
   Data class used to hold a single history record of the 
   user calling a service.
**/
public class ServiceHistoryItem implements Serializable
{
	// Key Data
	private String eventId = "";
	private String eventName = "";
    private String realtimeInd = "R";
    private String errorOverrideInd = "N";
	private String messageKey = "";
    private String trxCode = "";
  
	// Input/Output Data
	private String status;
	private int numberOfInputObjects = 0;
	private int numberOfOutputObjects = 0;

	// List of errors on this service, if any.
	private List<String> errors = new ArrayList<String>();
    
	private Calendar eventTime = new GregorianCalendar();


	/**
	   Returns the EventId for this service history item.
	   @return The EventId value.
	   @see #setEventId
	 **/
	public String getEventId() { return eventId; }

	/**
	   Sets the EventId for this service history item.
	   @param eventId The new EventId value.
	   @see #getEventId
	 **/
	public void setEventId(String eventId) { this.eventId = eventId; }

	/**
	   Returns the EventName for this service history item.
	   @return The EventName value.
	   @see #setEventName
	 **/
	public String getEventName() { return eventName; }

	/**
	   Sets the EventName for this service history item.
	   @param eventName The new EventName value.
	   @see #getEventName
	 **/
	public void setEventName(String eventName) { this.eventName = eventName; }

	/**
	   Returns the RealtimeInd for this service history item.
	   @return The RealtimeInd value.
	   @see #setRealtimeInd
	 **/
	public String getRealtimeInd() { return realtimeInd; }

	/**
	   Sets the RealtimeInd for this service history item.
	   @param realtimeInd The new RealtimeInd value.
	   @see #getRealtimeInd
	 **/
	public void setRealtimeInd(String realtimeInd) { this.realtimeInd = realtimeInd; }
    
	/**
	   Returns the ErrorOverrideInd for this service history item.
	   @return The ErrorOverrideInd value.
	   @see #setErrorOverrideInd
	 **/
    public String getErrorOverrideInd() { return errorOverrideInd; }

	/**
	   Sets the ErrorOverrideInd for this service history item.
	   @param errorOverrideInd The new ErrorOverrideInd value.
	   @see #getErrorOverrideInd
	 **/
	public void setErrorOverrideInd(String errorOverrideInd) { this.errorOverrideInd = errorOverrideInd; }

 	/**
	   Returns the MessageKey for this service history item.
	   @return The MessageKey value.
	   @see #setMessageKey
	 **/
	public String getMessageKey() { return messageKey;}

 	/**
	   Sets the MessageKey for this service history item.
	   @param messageKey The new MessageKey value.
	   @see #getMessageKey
	 **/
    public void setMessageKey(String messageKey) { this.messageKey = messageKey; }
    
	/**
	   Returns the Status for this service history item.
	   @return The Status value.
	   @see #setStatus
	 **/
	public String getStatus() { return status;}

	/**
	   Sets the Status for this service history item.
	   @param status The new Status value.
	   @see #getStatus
	 **/
    public void setStatus(String status) { this.status = status; }

	/**
	   Returns the NumberOfInputObjects for this service history item.
	   @return The NumberOfInputObjects value.
	   @see #getNumberOfInputObjects
	 **/
	public int getNumberOfInputObjects() { return numberOfInputObjects;}

	/**
	   Sets the NumberOfInputObjects for this service history item.
	   @param numberOfInputObjects The new NumberOfInputObjects value.
	   @see #getNumberOfInputObjects
	 **/
    public void setNumberOfInputObjects(int numberOfInputObjects) 
	{ this.numberOfInputObjects = numberOfInputObjects; }

	/**
	   Returns the NumberOfOutputObjects for this service history item.
	   @return The NumberOfOutputObjects value.
	   @see #setNumberOfOutputObjects
	 **/
	public int getNumberOfOutputObjects() { return numberOfOutputObjects;}

	/**
	   Sets the NumberOfOutputObjects for this service history item.
	   @param numberOfOutputObjects The new NumberOfOutputObjects value.
	   @see #getNumberOfOutputObjects
	 **/
    public void setNumberOfOutputObjects(int numberOfOutputObjects) 
	{ this.numberOfOutputObjects = numberOfOutputObjects; }
   

	/**
	   Gets the event time calendar object.
	   @return the event time calendar object.
	**/
	public Calendar getEventTimeObj() { return eventTime; }

	/**
	   Gets the event time as a string.
	**/
	public String getEventTime() 
	{
		if (eventTime == null) return "";
		DateFormat df = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		return df.format(eventTime.getTime());
	}

	/**
	   Add an error code and message to this service history item.
	   @param code The error code
	   @param msg The error message.
	**/
	public void addError(String code, String msg)
	{
		if ((code != null) && (code.length() > 0))
			errors.add(code  + " - " + msg);
		else
			errors.add(" - " + msg);
	}

	/**
	   Return all the errors as a string.
	   @return the errors as a string.
	**/
	public String getErrors()
	{
		StringBuffer buf = new StringBuffer();
		for(String s : errors) {
			buf.append(s);
			buf.append(" ");
		}
		return buf.toString();
	}

	public String toString()
	{
		StringBuffer buf = new StringBuffer();
		buf.append("eventId: ");
		buf.append(eventId);
		buf.append("eventName: ");
		buf.append(eventName);
        buf.append("trxCode: ");
        buf.append(trxCode);
		buf.append("realtimeInd: ");
		buf.append(realtimeInd);
		buf.append("\nerrorOverrideInd: ");
		buf.append(errorOverrideInd);
		buf.append("\nmessageKey: ");
		buf.append(messageKey);
		buf.append("\nstatus: ");
		buf.append(status);
		buf.append("\nnumberOfInputObjects: ");
		buf.append(numberOfInputObjects);
		buf.append("\nnumberOfOutputObjects: ");
		buf.append(numberOfOutputObjects);
        buf.append("\ncommDuration: ");        
		buf.append("\neventDuration: ");		
		buf.append("\neventTime: ");
		buf.append(eventTime);
		return buf.toString();
	}

    /**
     * Returns the TrxCode.
     */
    public String getTrxCode()
    {
        return trxCode;
    }

    /**
     * Sets the TrxCode.
     */
    public void setTrxCode(String trxCode)
    {
        this.trxCode = trxCode;
    }
}