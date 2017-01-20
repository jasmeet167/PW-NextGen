/*
 *  This software and/or documentation contains trade secrets and
 *  confidential information, which are proprietary to
 *  Computer Sciences Corporation.
 *  The use, reproduction, distribution, or disclosure of this
 *  software and/or documentation, in whole or in part, without the express
 *  written permission of Computer Sciences Corporation is prohibited.
 *  This software and/or documentation is also an unpublished work protected
 *  under the copyright laws of the United States of America and other countries.
 *  If this software and/or documentation becomes published, the following
 *  notice shall apply:
 *
 *  Copyright 2012 Computer Sciences Corporation. All Rights Reserved.
 */
package com.csc.fsg.life.rules;
import java.io.Serializable;
import java.text.MessageFormat;
import java.util.Arrays;
import java.util.Collections;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

import com.csc.fsg.life.common.util.NumberHelper;

/* Modifications: ENHT0085, T0175 */

/**
   This class defines the message objects that are created and returned
   in case of errors.
 */
public class Message implements Cloneable, Serializable {
    private static final long serialVersionUID = 03100L;
    /**
	   Constant for Informational message.
     */
    public static final int INFORMATION = 0;
    /**
	  Constant for Informational message.
	*/
    public static final int INFO = 0;
    /**
	   Constant for warning message.
	*/
    public static final int WARNING = 1;
    /**
	   Constant for warning message.
	*/
    public static final int WARN = 1;
    /**
	   Constant for Error message.
	*/
    public static final int ERROR = 2;
    /**
	   Constant for Severe message.
	*/
    public static final int SEVERE = 3;
    /**  Text for missing message property **/
    public static final String ERR_MESSAGE_MISSING = "Message not found ";
    /**
	   Invalid message key into resource bundle.
	*/
    public static final String INVALID_MESSAGE_KEY = "Invalid message key: ";
    /**
	   Length of message key.
	*/
    public static final int MESSAGE_KEY_LENGTH = 8;
    private static Map<Integer, Message> msgs = new HashMap<Integer, Message>(89);
    private static Map<Integer, String> msgt = new HashMap<Integer, String>(89);

    /**
	   Creates a message object from the error code.
	   @param code Message code.
	   @return Message object for the specified message code.
	*/
    public static final Message create(int code) {
        Integer key = Integer.valueOf(code);
        Message msg = get(key);
        if (msg == null) {
            int component = code / 100000;
            int message = (code - component * 100000) / 10;
            int severity = code % 10;
            msg = new Message(component, message, severity);
            msg.code = code;
            msg.key = key;
            put(key, msg);
        }
        return msg;
    }
    /**
	   Create a Message for a structured message code
	   @param component  Component
	   @param message    Message number
	   @param severity   Message severity
	   @return           Description of the Returned Value
	*/
    public static final Message create(
            int component,
            int message,
            int severity) {
        int code = NumberHelper.safeLongToInt((component * 100000) + (message * 10) + severity);
        Integer key = Integer.valueOf(code);
        Message msg = get(key);
        if (msg == null) {
            msg = new Message(component, message, severity);
            msg.code = code;
            msg.key = key;
            put(key, msg);
        }
        return msg;
    }
    /**
	   Create a Message for a structured message code
	   @param component  Component
	   @param message    Message number
	   @param severity   Message severity
	   @param text       Description of Parameter
	   @return           Description of the Returned Value
     */
    public static final Message create(
            int component,
            int message,
            int severity,
            String text) {
        Message msg = Message.create(component, message, severity);
        msgt.put(msg.getKey(), text.intern());
        return msg;
    }
    /**
	   Create a copy of a Message containing variable data.
	   @param baseMessage   Description of Parameter
	   @param variableData  Description of Parameter
	   @return              Description of the Returned Value
     */
    public static final Message create(
            Message baseMessage,
            Object[] variableData) {
        return baseMessage.setVariableData(variableData);
    }
    /**
	   Create a copy of a Message containing variable data.
	   @param baseMessage   Description of Parameter
	   @param variableData  Description of Parameter
	   @return              Description of the Returned Value
	*/
    public static final Message create(
            Message baseMessage,
            Object variableData) {
        return baseMessage.setVariableData(new Object[]{variableData});
    }
    /**
	   Retrieve a Message identified by the message code.
	   @param key  Description of Parameter
	   @return     Description of the Returned Value
	*/
    public static final Message get(Integer key) {
        Message msg = msgs.get(key);
        return msg;
    }
    /**
	   Return the display value for a Message identified by the
	   input message number.
	   @param key  Integer representing the message number
	   @return     the external display value defined for this message number
	*/
    public static final String getText(Integer key) {
        
        String keyString = key.toString();
        if (keyString.length() != MESSAGE_KEY_LENGTH) {
            StringBuffer sb = new StringBuffer();
            sb.append(INVALID_MESSAGE_KEY);
            sb.append(keyString);
            sb.append("  Must be ");
            sb.append(MESSAGE_KEY_LENGTH);
            sb.append(" characters");            
            return ERR_MESSAGE_MISSING;
        }
        String txt = null;
        if (txt == null) {
            txt = msgt.get(key);
            if (txt == null) {
                txt = ERR_MESSAGE_MISSING;
            }
        }
        return txt;
    }
    /**
	   Return the display value of a Message, prefixed by the Message number.
	   @param key  Integer representing the message number
	   @return     the external display value defined for this message number,
	   prefixed with the message number
	*/
    public static final String getTextAndMessageNumber(Integer key) {
        if (key == null) {            
            return ERR_MESSAGE_MISSING;
        }
        StringBuffer sb = new StringBuffer(key.toString());
        sb.append(" - ");
        sb.append(getText(key));
        return sb.toString();
    }
    /**
	   Retrieve a system Message value identified by the message code,
	   using the default system name.
	   @param key  Integer representing the message number
	   @return     the external system value defined for this message number
	*/
    public static final String getSystemText(Integer key) {
        if (key == null) {            
            return ERR_MESSAGE_MISSING;
        }
        String keyString = key.toString();
        if (keyString.length() != MESSAGE_KEY_LENGTH) {
            StringBuffer sb = new StringBuffer();
            sb.append(INVALID_MESSAGE_KEY);
            sb.append(keyString);
            sb.append("  Must be ");
            sb.append(MESSAGE_KEY_LENGTH);
            sb.append(" characters");            
            return ERR_MESSAGE_MISSING;
        }
        String txt = null;
        if (txt == null) {
            txt = ERR_MESSAGE_MISSING;
        }
        return txt;
    }
    /**
	   Stores a Message identified by the message code.
	   @param key  Description of Parameter
	   @param msg  Description of Parameter
	   @return     Description of the Returned Value
	*/
    private static final Message put(Integer key, Message msg) {
        Object obj = msgs.put(key, msg);
        if (obj != null) {
           // ErrorHandler.log("Duplicate message " + msg.getKey());
        }
        return msg;
    }
    private int code;
    private Integer key;
    private final int component;
    private final int message;
    private final int severity;
    private Object[] variableData;
    /**
	   Builds an empty Message object.
	*/
    public Message() {
        this.component = 0;
        this.message = 0;
        this.severity = 0;
        this.code = 0;
        this.key = Integer.valueOf(0);
    }
    /**
	   Message constructor for structured message code
	   @param component  Component
	   @param message    Message number
	   @param severity   Message severity
	*/
    protected Message(int component, int message, int severity) {
        this.component = component;
        this.message = message;
        this.severity = severity;
    }
    /**
	   Overrides Object.equals() method.
	   @param that  Another object to compare this one to.
	   @return true if the object is equal to this one.
	*/
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        else {
            if (this.getClass() == that.getClass()) {
                Message thatrm = (Message)that;
                if (this.code == thatrm.code) {
                    if (this.variableData == null) {
                        if (thatrm.variableData == null) {
                            return true;
                        }
                    }
                    else {
                        return this.variableData.equals(thatrm.variableData);
                    }
                }
            }
        }
        return false;
    }
    /**
	   Returns the formatted message text.
	   <p/>
	   If variable data is present it will be formatted into the message.
	   @return   Formatted message
	*/
    public String format() {
        String msg = getText(key);
        if (variableData != null) {
            MessageFormat mf = new MessageFormat(msg);
            msg = mf.format(variableData);
        }
        return msg;
    }
    /**
	   Returns the formatted message text.
	   <p/>
	   If variable data is present it will be formatted into the message.
	   @return   Formatted message
	*/
    public String formatMessageText() {
        return format();
    }
    /**
	   Returns the message code.
	   @return   The message code
	*/
    public int getCode() {
        return code;
    }
    /**
	   Returns the component.
	   @return   The component
	*/
    public int getComponent() {
        return component;
    }
    /**
	   Returns the message code.
	   @return   The message key
	*/
    public Integer getKey() {
        return key;
    }
    /**
	   Returns the message.
	   @return   The message number
     */
    public int getMessage() {
        return message;
    }
    /**
	   Returns the variable data associated with the message.
	   @return       List of data elements
	   @deprecated
     */
    public List<Object> getMessageVariableData() {
        return Arrays.asList(variableData);
    }
    /**
	   Returns the variable data associated with the message.
	   @return   List of data elements
     */
    @SuppressWarnings("unchecked")
	public List<Object> getData() {
        if (variableData != null) {
            return Arrays.asList(variableData);
        }
        else {
            return Collections.EMPTY_LIST;
        }
    }
    /**
	   Returns the severity of the message.
	   @return   Severity
     */
    public int getSeverity() {
        return severity;
    }
    /**
	   Returns the variable data associated with the message.
	   @return       Array of data elements
	   @deprecated
     */
    public Object[] getVariableData() {
        return variableData;
    }
    /**
	   @return   Description of the Returned Value
     */
    public int hashCode() {
        return code;
    }
    /**
	   Set the message number.
	   @param code   The new message number
	   @deprecated
	*/
    public void setMessageCode(int code) {
    }
    /**
	   Set the variable data for the message..
	   @param data   The new MessageVariableData value
	   @deprecated
     */
    public void setMessageVariableData(Object[] data) {
    }
    /**
	   Set the variable data for the message..
	   @param variableData  The new variable data
	   @return              Description of the Returned Value
	*/
    public Message setVariableData(Object[] variableData) {
        Message rm = null;
        try {
            rm = (Message)this.clone();
        } 
        catch (CloneNotSupportedException e) {
        }
        
        if (rm != null)
        	rm.variableData = variableData;

        return rm;
    }
    /**
	   @return   Description of the Returned Value
	*/
    public String toString() {
        return format();
    }
    
    private static final class MessageBundle 
    	extends ResourceBundle 
    {
        /**
		   Gets the Keys attribute of the MessageBundle object
		   @return   The Keys value
		*/
        public Enumeration getKeys() {
            return null;
        }
        /**
		   @param string                        Description of Parameter
		   @return                              Description of the Returned Value
		   @exception MissingResourceException  Description of Exception
		*/
        protected Object handleGetObject(String string)
                 throws MissingResourceException {
            return null;
        }
    }
}
