package com.csc.fsg.life.biz.bo;

import java.io.Serializable;
import java.sql.Timestamp;

/* Modifications: ENH863, ENH988, ENH1107 */

/**
 * Implementation class for access to a row object for the XGREPOS table.
 */
public class XgreposModelImpl implements XgreposModel, Serializable
{
	private String systemId;
	private String eventId;
	private String messageKey;
	private String operation;
	private Integer sequenceNo;
	private Integer subSeqNo;
	private Integer mapId;
	private String copybookName;
	private String batchOrigin;
	private String statusCode;
    private String userId;
    private String trxCode;
    private String responseStatus;
    private Timestamp timeStamp;
	private String reseqSw;
	private byte[] eventImage;

	public XgreposModelImpl()
	{
	}

	/**
	 *  Sets a new value to the systemId property.
	 *	@param value New value for systemId property.
	 */
	public void setSystemId(String value)
	{
		this.systemId = value;
	}

	/**
	 *  Returns the current value of the systemId property.
     *
	 *	@return The value of the systemId property.
	 */
	public String getSystemId()
	{
		return this.systemId;
	}	
	
	/**
	 *  Sets a new value to the eventId property.
	 *	@param value New value for eventId property.
	 */
	public void setEventId(String value)
	{
		this.eventId = value;
	}

	/**
	 *  Returns the current value of the eventId property.
     *
	 *	@return The value of the eventId property.
	 */
	public String getEventId()
	{
		return this.eventId;
	}	
	
	/**
	 *  Sets a new value to the messageKey property.
	 *	@param value New value for messageKey property.
	 */
	public void setMessageKey(String value)
	{
		this.messageKey = value;
	}

	/**
	 *  Returns the current value of the messageKey property.
     *
	 *	@return The value of the messageKey property.
	 */
	public String getMessageKey()
	{
		return this.messageKey;
	}	
	
	/**
	 *  Sets a new value to the operation property.
	 *	@param value New value for operation property.
	 */
	public void setOperation(String value)
	{
		this.operation = value;
	}

	/**
	 *  Returns the current value of the operation property.
     *
	 *	@return The value of the operation property.
	 */
	public String getOperation()
	{
		return this.operation;
	}	
	
	/**
	 *  Sets a new value to the sequenceNo property.
	 *	@param value New value for sequenceNo property.
	 */
	public void setSequenceNo(Integer value)
	{
		this.sequenceNo = value;
	}

	/**
	 *  Returns the current value of the sequenceNo property.
     *
	 *	@return The value of the sequenceNo property.
	 */
	public Integer getSequenceNo()
	{
		return this.sequenceNo;
	}	
	
	/**
	 *  Sets a new value to the subSeqNo property.
	 *	@param value New value for subSeqNo property.
	 */
	public void setSubSeqNo(Integer value)
	{
		this.subSeqNo = value;
	}

	/**
	 *  Returns the current value of the subSeqNo property.
     *
	 *	@return The value of the subSeqNo property.
	 */
	public Integer getSubSeqNo()
	{
		return this.subSeqNo;
	}	
	
	/**
	 *  Sets a new value to the mapId property.
	 *	@param value New value for mapId property.
	 */
	public void setMapId(Integer value)
	{
		this.mapId = value;
	}

	/**
	 *  Returns the current value of the mapId property.
     *
	 *	@return The value of the mapId property.
	 */
	public Integer getMapId()
	{
		return this.mapId;
	}	
	
	/**
	 *  Sets a new value to the copybookName property.
	 *	@param value New value for copybookName property.
	 */
	public void setCopybookName(String value)
	{
		this.copybookName = value;
	}

	/**
	 *  Returns the current value of the copybookName property.
     *
	 *	@return The value of the copybookName property.
	 */
	public String getCopybookName()
	{
		return this.copybookName;
	}	
	
	/**
	 *  Sets a new value to the batchOrigin property.
	 *	@param value New value for batchOrigin property.
	 */
	public void setBatchOrigin(String value)
	{
		this.batchOrigin = value;
	}

	/**
	 *  Returns the current value of the batchOrigin property.
     *
	 *	@return The value of the batchOrigin property.
	 */
	public String getBatchOrigin()
	{
		return this.batchOrigin;
	}
	
	/**
	 *  Sets a new value to the statusCode property.
	 *	@param value New value for statusCode property.
	 */
	public void setStatusCode(String value)
	{
		this.statusCode = value;
	}

	/**
	 *  Returns the current value of the statusCode property.
     *
	 *	@return The value of the statusCode property.
	 */
	public String getStatusCode()
	{
		return this.statusCode;
	}	
	
    /**
	 *  Sets a new value to the userId property.
	 *	@param value New value for userId property.
     */
    public void setUserId(String value)
    {
        this.userId = value;
    }

    /**
	 *  Returns the current value of the userId property.
     *
	 *	@return The value of the userId property.
     */
    public String getUserId()
    {
        return userId;
    }

    /**
	 *  Sets a new value to the trxCode property.
	 *	@param value New value for trxCode property.
     */
    public void setTrxCode(String value)
    {
        this.trxCode = value;
    }

    /**
	 *  Returns the current value of the trxCode property.
     *
	 *	@return The value of the trxCode property.
     */
    public String getTrxCode()
    {
        return trxCode;
    }
    
    /**
	 *  Sets a new value to the responseStatus property.
	 *	@param value New value for responseStatus property.
     */
    public void setResponseStatus(String value)
    {
        this.responseStatus = value;
    }

    /**
	 *  Returns the current value of the responseStatus property.
     *
	 *	@return The value of the responseStatus property.
     */
    public String getResponseStatus()
    {
        return responseStatus;
    }
    
	/**
	 *  Sets a new value to the timeStamp property.
	 *	@param value New value for timeStamp property.
	 */
	public void setTimeStamp(Timestamp value)
	{
		this.timeStamp = value;
	}

	/**
	 *  Returns the current value of the timeStamp property.
     *
	 *	@return The value of the timeStamp property.
	 */
	public Timestamp getTimeStamp()
	{
		return this.timeStamp;
	}
	
	/**
	 * Set new value of the property {@code reseqSw}
	 * 
	 * @param value
	 *        New value of the property {@code reseqSw}.
	 * @see #getReseqSw()
	 */
	public void setReseqSw(String value)
	{
		this.reseqSw = value;
	}

	/**
	 * Returns the current value of the reseqSw property.
	 * 
	 * @return Current value of the property {@code reseqSw}
	 * @see #setReseqSw(String)
	 */
	public String getReseqSw()
	{
		return this.reseqSw;
	}

	/**
	 *  Sets a new value to the eventImage property.
	 *	@param value New value for eventImage property.
	 */
	public void setEventImage(byte[] value)
	{
		this.eventImage = value;
	}

	/**
	 *  Returns the current value of the eventImage property.
     *
	 *	@return The value of the eventImage property.
	 */
	public byte[] getEventImage()
	{
		return this.eventImage;
	}
	

	/**
	   Dumps model class to a string.
    **/
    @Override
	public String toString()
    {
        StringBuffer buf = new StringBuffer();
		buf.append("\nsystemId: ");
        buf.append(systemId);
		buf.append("\neventId: ");
        buf.append(eventId);
		buf.append("\nmessageKey: ");
        buf.append(messageKey);
		buf.append("\noperation: ");
        buf.append(operation);
		buf.append("\nsequenceNo: ");
        buf.append(sequenceNo);
		buf.append("\nsubSeqNo: ");
        buf.append(subSeqNo);
		buf.append("\nmapId: ");
        buf.append(mapId);
		buf.append("\ncopybookName: ");
        buf.append(copybookName);
		buf.append("\nbatchOrigin: ");
        buf.append(batchOrigin);
		buf.append("\nstatusCode: ");
        buf.append(statusCode);
		buf.append("\nuserId: ");
        buf.append(userId);
		buf.append("\ntrxCode: ");
        buf.append(trxCode);
		buf.append("\nresponseStatus: ");
        buf.append(responseStatus);
		buf.append("\ntimeStamp: ");
        buf.append(timeStamp);
		buf.append("\nreseqSw: ");
		buf.append(reseqSw);
        return buf.toString();
    }
}
