package com.csc.fsg.life.biz.exception;

import java.io.Serializable;

import com.csc.fsg.life.common.util.ToStringBuilder;


/**
 * A business message to relay warning or excepiton information 
 * from a business service.
 */
public class BusinessMessage
	implements Serializable
{
	/** Identifier of the transaction, which triggered this message. **/
	protected String messageTrxCode = null;
	
	/** Flag indicating whether display of messageTrxCode is optional **/
	protected boolean isTrxCodeOptional = true;
	
    /** Error code associated with the message. **/
    protected String code = null;

    /** Descriptive text for error. **/
    protected String message = null;

    /** Name of the field the message is associated to. **/
    protected String field = null;

	/**
	 * Creates an empty BusinessMessage object.
	 */
    public BusinessMessage()
    {
    }
    
	/**
	 * Creates a BusinessMessage object from a string message 
	 * with no code, and no messageTrxCode.
	 * @param message the message.
	 */
    public BusinessMessage(String message)
    {
    	this.message = message;
    }

	/**
	 * Creates a BusinessMessage object from code and message. 
	 * @param code the error/warning code.
	 * @param message the message.
	 * @param field the field taht caused the error.
	 */
    public BusinessMessage(String code, String message, String field)
    {
    	this.code = code;
    	this.message = message;
    	this.field = field;
    }

	/**
	 * Creates a BusinessMessage object from code, message, and messageTrxCode.
	 * @param messageTrxCode trx code of the transaction, which returned this message. 
	 * @param isTrxCodeOptional flag indicating whether display of messageTrxCode is optional.
	 * @param code the error/warning code.
	 * @param message the message.
	 * @param field the field taht caused the error.
	 */
    public BusinessMessage(String messageTrxCode, boolean isTrxCodeOptional, 
    							String code, String message, String field)
    {
    	this.messageTrxCode = messageTrxCode;
    	this.isTrxCodeOptional = isTrxCodeOptional;
    	this.code = code;
    	this.message = message;
    	this.field = field;
    }

    /**
	 * Return value of messageTrxCode.
	 * @return value of messageTrxCode.
	 * @see #setMessageTrxCode
	 */
    public String getMessageTrxCode()
    {
        return messageTrxCode;
    }

	/**
	 * Set value of messageTrxCode.
	 * @param messageTrxCode the new value of messageTrxCode.
	 * @see #getMessageTrxCode
	 */
    public void setMessageTrxCode(String messageTrxCode)
    {
        this.messageTrxCode = messageTrxCode;
    }

    /**
	 * Return value of isTrxCodeOptional.
	 * @return value of isTrxCodeOptional.
	 * @see #setTrxCodeOptional
	 */
    public boolean isTrxCodeOptional()
    {
        return isTrxCodeOptional;
    }

	/**
	 * Set value of isTrxCodeOptional.
	 * @param isTrxCodeOptional the new value of isTrxCodeOptional.
	 * @see #isTrxCodeOptional
	 */
    public void setTrxCodeOptional(boolean isTrxCodeOptional)
    {
        this.isTrxCodeOptional = isTrxCodeOptional;
    }
    
    /**
	 * Returns the Code.
	 * @return the Code value.
	 * @see #setCode
	 */
    public String getCode()
    {
        return code;
    }

	/**
	 * Sets the Code.
	 * @param code the new Code value.
	 * @see #getCode
	 */
    public void setCode(String code)
    {
        this.code = code;
    }

	/**
	 * Returns the Message.
	 * @return the Message value.
	 * @see #setMessage
	 */
    public String getMessage()
    {
        return message;
    }

	/**
	 * Sets the Message.
	 * @param message the new Message value.
	 * @see #getMessage
	 */
    public void setMessage(String message)
    {
        this.message = message;
    }

    /**
	 * Used to determine whether or not messageTrxCode is present.
	 * @return true if there is messageTrxCode.
     */
    public boolean hasMessageTrxCode()
    {
        return (messageTrxCode != null && messageTrxCode.trim().length() > 0);
    }
    
    /**
	 * Used to determine whether or not the message is present.
	 * @return true if there is a message.
     */
    public boolean hasMessage()
    {
        return (message != null && message.trim().length() > 0);
    }
    
	/**
	 * Returns the Field.
	 * @return the Field value.
	 * @see #setField
	 */
    public String getField()
    {
        return field;
    }

	/**
	 * Sets the Field.
	 * @param field the new Field value.
	 * @see #getField
	 */
    public void setField(String field)
    {
        this.field = field;
    }
    
    @Override
    public String toString() {
    	ToStringBuilder builder = new ToStringBuilder(this);
        builder.append("Code", code);
        builder.append("Message", message);
    	return builder.toString();
    }
}