package com.csc.fsg.life.biz.copyobject;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.csc.fsg.life.biz.bo.BusinessObjectException;
import com.csc.fsg.life.biz.bo.HistoryDetailItem;
import com.csc.fsg.life.biz.bo.XgErrorAreaErrorArray;
import com.csc.fsg.life.biz.service.ServiceValidationException;
import com.csc.fsg.life.context.UserContext;
import com.csc.fsg.life.convert.Converter;

/* $#(c)
 * This software contains trade secrets and confidential information,
 * which are proprietary to Computer Sciences Corporation. The use,
 * reproduction, distribution, or disclosure of this software, in whole
 * or in part, without the express written permission of Computer
 * Sciences Corporation is prohibited. This software is also an
 * unpublished work protected under the copyright laws of the United
 * States of America and other countries. If this software becomes
 * published, the following notice shall apply:
 * 
 * Copyright (c) 1994 - 2006 Computer Sciences Corporation,
 * all rights reserved.
 * 
 * This software may be modified only in accordance with the terms of the
 * applicable license agreement.  Any modifications may result in the 
 * voiding of applicable warranties and support services.
 */
 
/* Modifications: T0140 */

/**
 *  Copy Object class that represents copybook CXGERREC.
 */
public class XgErrorAreaErrorArrayCopyObject
    extends CopyObject 
    implements XgErrorAreaErrorArray 
{
	/** Statndard apache commons logger. **/
	protected static Log log = LogFactory.getLog("com.csc.fsg");
	
    
    /**
	   Default XgErrorAreaErrorArrayCopyObject constructor.  Creates this object and initializes all fields
	   and sub-objects with default values.  
	   @throws CopyObjectException If there is an initialization problem.
	**/
    public XgErrorAreaErrorArrayCopyObject() 
        throws CopyObjectException 
    {
        bytes = new byte[200];
        try {
            setErrorFieldDisplacement(Long.valueOf(0));
            setErrorCode("");
            setErrorLevel("");
            setErrorMessage("");
            setMiscDetail("");
        }
        catch (NumberFormatException nfE) {
            throw new CopyObjectException(nfE.getMessage());
    }
    }
    
    /**
	   Creates this object and initializes all fields
	   and sub-objects with default values.   In addition, initializes 
	   the user context.
	   @param userContext the user context.
	   @throws CopyObjectException If there is an initialization problem.
	**/
    public void init(UserContext userContext) 
        throws CopyObjectException
    {
        if (initialized)
            return;
        
        setUserContext(userContext);
        try {
        }
        catch (NumberFormatException nfE) {
            throw new CopyObjectException(nfE.getMessage());
        }

        initialized = true;
    }

    /**
	   Initialization method for this copy object.  Additional flag that can be used to 
	   force re-initialization.
	   @param userContext The user context.
	   @param isForced true to force re-initialization.
	   @throws BusinessObjectException If initialization fails.
	**/
    public void init(UserContext userContext, boolean isForced) 
    	throws BusinessObjectException
    {
    	if (isForced)
    		initialized = false;
    		
    	init(userContext);
    }

    /**
	   Create a XgErrorAreaErrorArrayCopyObject from the specified byte array.
	   @param userContext The user context.
	   @param copybookData The byte array to initialize to.
	   @throws CopyObjectException If initialization fails.
	**/
    public XgErrorAreaErrorArrayCopyObject(UserContext userContext, byte[] copybookData) 
        throws CopyObjectException
    {
        this();
    	init(userContext, copybookData);
    }
    
    /**
	   Initialization method for this copy object.  This initialzes the copy object 
	   off of the byte array.
	   @param userContext The user context.
	   @param copybookData The byte array to initialize to.
	   @throws CopyObjectException If initialization fails.
	**/
    public void init(UserContext userContext, byte[] copybookData) 
        throws CopyObjectException
    {
        if (copybookData.length > 200)
            throw new CopyObjectException("Invalid length for XgErrorAreaErrorArray copybook, length must be less than 200 but length is " + copybookData.length);
        
        // initialize byte array
        init(userContext);
        
        // copy the given copybook data into byte array
        System.arraycopy(copybookData, 0, bytes, 0, copybookData.length);
        
    }

	/**
	   Returns the copybook name.
	   @return the copybook name.
	**/
	public String getCopybookName()
	{
		return "CXGERREC";
	}

	/**
	   Returns a bye array that represents the copybook.  This byte array is
	   already converted to the appropriate format (ASCII, EBCIDIC, etc.) 
	   as defined by the converter.
	   @return a bye array that represents the copybook. 
	   @throws CopyObjectException If conversion fails.
	**/
    public byte[] toBytes()
        throws CopyObjectException
    {
        return bytes;
    }
    
    /**
	   Returns true if this object is populated with data other than
	   default values.
	   @return true if this object is populated.
	   @throws CopyObjectException if there is a data access problem.
	**/
    public boolean isXgErrorAreaErrorArrayPopulated()
        throws CopyObjectException
    {
        if (getErrorFieldDisplacement().longValue() != 0)
            return true;
        if (getErrorCode().trim().length() != 0)
            return true;
        if (getErrorLevel().trim().length() != 0)
            return true;
        if (getErrorMessage().trim().length() != 0)
            return true;
        if (getMiscDetail().trim().length() != 0)
            return true;
        return false;
    }
    
    /**
       Get the value of errorFieldDisplacement.
	   @return the value of errorFieldDisplacement.
	   @throws CopyObjectException If conversion fails.
	   @see #setErrorFieldDisplacement
     */
    public Long getErrorFieldDisplacement()
    {
        return Long.valueOf(convertFromHost(Converter.NUMERIC, 9, 0, getBytes(0, 9)));
    }
    	
    /**
       Set the value of errorFieldDisplacement.
	   @param value the new value of errorFieldDisplacement.
	   @throws CopyObjectException If conversion fails.
	   @see #getErrorFieldDisplacement
     */
    public void setErrorFieldDisplacement(Long value)
        throws CopyObjectException
    {
        if (value == null)
            value = Long.valueOf(0);
        else
            validateLong("errorFieldDisplacement", 9, 0, value);
        replaceBytes(0, 9, convertToHost(Converter.NUMERIC, 9, 0, value.toString()));
    }
    
    /**
       Get the value of errorCode.
	   @return the value of errorCode.
	   @throws CopyObjectException If conversion fails.
	   @see #setErrorCode
     */
    public String getErrorCode()
    {
        return convertFromHost(Converter.ALPHANUM, 4, 0, getBytes(9, 13)).trim();
    }
    	
    /**
       Set the value of errorCode.
	   @param value the new value of errorCode.
	   @throws CopyObjectException If conversion fails.
	   @see #getErrorCode
     */
    public void setErrorCode(String value)
        throws CopyObjectException
    {
        validateString("errorCode", 4, value);
        replaceBytes(9, 13, convertToHost(Converter.ALPHANUM, 4, 0, value));
    }
    
    /**
       Get the value of errorLevel.
	   @return the value of errorLevel.
	   @throws CopyObjectException If conversion fails.
	   @see #setErrorLevel
     */
    public String getErrorLevel()
    {
        return convertFromHost(Converter.ALPHANUM, 1, 0, getBytes(13, 14)).trim();
    }
    	
    /**
       Set the value of errorLevel.
	   @param value the new value of errorLevel.
	   @throws CopyObjectException If conversion fails.
	   @see #getErrorLevel
     */
    public void setErrorLevel(String value)
        throws CopyObjectException
    {
        validateString("errorLevel", 1, value);
        replaceBytes(13, 14, convertToHost(Converter.ALPHANUM, 1, 0, value));
    }
    
    /**
       Get the value of errorMessage.
	   @return the value of errorMessage.
	   @throws CopyObjectException If conversion fails.
	   @see #setErrorMessage
     */
    public String getErrorMessage()
    {
        return convertFromHost(Converter.ALPHANUM, 30, 0, getBytes(14, 44)).trim();
    }
    	
    /**
       Set the value of errorMessage.
	   @param value the new value of errorMessage.
	   @throws CopyObjectException If conversion fails.
	   @see #getErrorMessage
     */
    public void setErrorMessage(String value)
        throws CopyObjectException
    {
        validateString("errorMessage", 30, value);
        replaceBytes(14, 44, convertToHost(Converter.ALPHANUM, 30, 0, value));
    }
    
    /**
       Get the value of miscDetail.
	   @return the value of miscDetail.
	   @throws CopyObjectException If conversion fails.
	   @see #setMiscDetail
     */
    public String getMiscDetail()
    {
        return convertFromHost(Converter.ALPHANUM, 156, 0, getBytes(44, 200)).trim();
    }
    	
    /**
       Set the value of miscDetail.
	   @param value the new value of miscDetail.
	   @throws CopyObjectException If conversion fails.
	   @see #getMiscDetail
     */
    public void setMiscDetail(String value)
        throws CopyObjectException
    {
        validateString("miscDetail", 156, value);
        replaceBytes(44, 200, convertToHost(Converter.ALPHANUM, 156, 0, value));
    }


    /**
	   Validate this copy object.
	   @throws ServiceValidationException If there is invalid data.
	 **/
    public void validate()
		throws ServiceValidationException
    {
		ServiceValidationException e = new ServiceValidationException();


		// If there were any invalid allowable values throw exception.
		if (e.hasMessages())
			throw e;
    }

    /**
	   Returns a list of detail objects.  Each object in the list
	   represents one field in the copybook.
	   @param prefix A string to prefix field names with.
	   @return a list of detail objects.
	 */
	public List<HistoryDetailItem> toDetailList(String prefix)
	{
		return new ArrayList<HistoryDetailItem>();
	}

    /**
	 */
    public String toString()
    {
	    StringBuffer buf = new StringBuffer();
        buf.append("\nErrorFieldDisplacement = '");
        buf.append(getErrorFieldDisplacement());
        buf.append("'");
        buf.append("\nErrorCode = '");
        buf.append(getErrorCode());
        buf.append("'");
        buf.append("\nErrorLevel = '");
        buf.append(getErrorLevel());
        buf.append("'");
        buf.append("\nErrorMessage = '");
        buf.append(getErrorMessage());
        buf.append("'");
        buf.append("\nMiscDetail = '");
        buf.append(getMiscDetail());
        buf.append("'");
        return buf.toString();
	}
}
