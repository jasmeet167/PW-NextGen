package com.csc.fsg.life.biz.bo;

import java.sql.Timestamp;

import com.csc.fsg.life.biz.meta.MetaData;

/* Modifications: ENH863, ENH988, ENH1107 */

/**
 * Interface for access to a row object for the XGREPOS table.
 */
public interface XgreposModel
{
	/**
	 *  Sets a new value to the systemId property.  
	 *  @param value New value for systemId property.
	 */
	public void setSystemId(String value);
	
	/**
	 *  Returns the current value of the systemId property.
     *
	 *  @return The value of the systemId property.
	 */
    @MetaData (size=5)
	public String getSystemId();
	
	/**
	 *  Sets a new value to the eventId property.  
	 *  @param value New value for eventId property.
	 */
	public void setEventId(String value);
	
	/**
	 *  Returns the current value of the eventId property.
     *
	 *  @return The value of the eventId property.
	 */
    @MetaData (size=5)
	public String getEventId();
	
	/**
	 *  Sets a new value to the messageKey property.  
	 *  @param value New value for messageKey property.
	 */
	public void setMessageKey(String value);
	
	/**
	 *  Returns the current value of the messageKey property.
     *
	 *  @return The value of the messageKey property.
	 */
    @MetaData (size=20)
	public String getMessageKey();
	
	/**
	 *  Sets a new value to the operation property.  
	 *  @param value New value for operation property.
	 */
	public void setOperation(String value);
	
	/**
	 *  Returns the current value of the operation property.
     *
	 *  @return The value of the operation property.
	 */
    @MetaData (size=1)
	public String getOperation();
	
	/**
	 *  Sets a new value to the sequenceNo property.  
	 *  @param value New value for sequenceNo property.
	 */
	public void setSequenceNo(Integer value);
	
	/**
	 *  Returns the current value of the sequenceNo property.
     *
	 *  @return The value of the sequenceNo property.
	 */
    @MetaData (size=6)
	public Integer getSequenceNo();
	
	/**
	 *  Sets a new value to the subSeqNo property.  
	 *  @param value New value for subSeqNo property.
	 */
	public void setSubSeqNo(Integer value);
	
	/**
	 *  Returns the current value of the subSeqNo property.
     *
	 *  @return The value of the subSeqNo property.
	 */
    @MetaData (size=6)
	public Integer getSubSeqNo();
	
	/**
	 *  Sets a new value to the mapId property.  
	 *  @param value New value for mapId property.
	 */
	public void setMapId(Integer value);
	
	/**
	 *  Returns the current value of the mapId property.
     *
	 *  @return The value of the mapId property.
	 */
    @MetaData (size=11)
	public Integer getMapId();
	
	/**
	 *  Sets a new value to the copybookName property.  
	 *  @param value New value for copybookName property.
	 */
	public void setCopybookName(String value);
	
	/**
	 *  Returns the current value of the copybookName property.
     *
	 *  @return The value of the copybookName property.
	 */
    @MetaData (size=8)
	public String getCopybookName();
	
	/**
	 *  Sets a new value to the batchOrigin property.
	 *	@param value New value for batchOrigin property.
	 */
	public void setBatchOrigin(String value);

	/**
	 *  Returns the current value of the batchOrigin property.
     *
	 *	@return The value of the batchOrigin property.
	 */
	public String getBatchOrigin();

	/**
	 *  Sets a new value to the statusCode property.  
	 *  @param value New value for statusCode property.
	 */
	public void setStatusCode(String value);
	
	/**
	 *  Returns the current value of the statusCode property.
     *
	 *  @return The value of the statusCode property.
	 */
    @MetaData (size=2)
	public String getStatusCode();

    /**
	 *  Sets a new value to the userId property.  
	 *  @param value New value for userId property.
     */
    public void setUserId(String value);  
	
    /**
	 *  Returns the current value of the userId property.
     *
	 *  @return The value of the userId property.
     */
    @MetaData (size=12)
    public String getUserId();

    /**
	 *  Sets a new value to the trxCode property.  
	 *  @param value New value for trxCode property.
     */
    public void setTrxCode(String value);

    /**
	 *  Returns the current value of the trxCode property.
     *
	 *  @return The value of the trxCode property.
     */
    @MetaData (size=4)
    public String getTrxCode();
    
    /**
	 *  Sets a new value to the responseStatus property.  
	 *  @param value New value for responseStatus property.
     */
    public void setResponseStatus(String value);

    /**
	 *  Returns the current value of the responseStatus property.
     *
	 *  @return The value of the responseStatus property.
     */
    @MetaData (size=1)
    public String getResponseStatus();
    
    /**
	 *  Sets a new value to the timeStamp property.
	 *	@param value New value for timeStamp property.
	 */
	public void setTimeStamp(Timestamp value);

	/**
	 *  Returns the current value of the timeStamp property.
     *
	 *	@return The value of the timeStamp property.
	 */
	public Timestamp getTimeStamp();
	
	/**
	 * Set new value of the property {@code reseqSw}
	 * 
	 * @param value
	 *        New value of the property {@code reseqSw}.
	 * @see #getReseqSw()
	 */
	public void setReseqSw(String value);

	/**
	 * Returns the current value of the reseqSw property.
	 * 
	 * @return Current value of the property {@code reseqSw}
	 * @see #setReseqSw(String)
	 */
	@MetaData(size = 1)
	public String getReseqSw();

	/**
	 *  Sets a new value to the eventImage property.  
	 *  @param value New value for eventImage property.
	 */
	public void setEventImage(byte[] value);
	
	/**
	 *  Returns the current value of the eventImage property.
	 *  This property has a maxiumum size of 32000
	 *  @return The value of the eventImage property.
	 */
	public byte[] getEventImage();
}
