package com.csc.fsg.life.biz.copyobject;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.csc.fsg.life.biz.bo.BusinessObjectException;
import com.csc.fsg.life.biz.bo.HistoryDetailItem;
import com.csc.fsg.life.biz.bo.XgCommAreaErrorcode;
import com.csc.fsg.life.biz.service.ServiceValidationException;
import com.csc.fsg.life.context.UserContext;
import com.csc.fsg.life.convert.ASCIIConverter;
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
 
/**
 *  Copy Object class that represents copybook CXGCOMM1.
 */
public class XgCommAreaErrorcodeCopyObject
    extends CopyObject 
    implements XgCommAreaErrorcode 
{
	/** Statndard apache commons logger. **/
	protected static Log log = LogFactory.getLog("com.csc.fsg");
	
    
    /**
	   Default XgCommAreaErrorcodeCopyObject constructor.  Creates this object and initializes all fields
	   and sub-objects with default values.  
	   @throws CopyObjectException If there is an initialization problem.
	**/
    public XgCommAreaErrorcodeCopyObject() 
        throws CopyObjectException 
    {
        bytes = new byte[4];
        setErrorcode("");
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
		setConverter(new ASCIIConverter());

        if (initialized)
        	return;
        	
        setUserContext(userContext);

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
	   Create a XgCommAreaErrorcodeCopyObject from the specified byte array.
	   @param userContext The user context.
	   @param copybookData The byte array to initialize to.
	   @throws CopyObjectException If initialization fails.
	**/
    public XgCommAreaErrorcodeCopyObject(UserContext userContext, byte[] copybookData) 
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
        if (copybookData.length > 4)
            throw new CopyObjectException("Invalid length for XgCommAreaErrorcode copybook, length must be less than 4 but length is " + copybookData.length);
               
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
		return "CXGCOMM1";
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
    public boolean isXgCommAreaErrorcodePopulated()
        throws CopyObjectException
    {
        if (getErrorcode().trim().length() != 0)
            return true;
        return false;
    }
    
    /**
       Get the value of errorcode.
	   @return the value of errorcode.
	   @throws CopyObjectException If conversion fails.
	   @see #setErrorcode
     */
    public String getErrorcode()
    {
        return convertFromHost(Converter.ALPHANUM, 4, 0, getBytes(0, 4)).trim();
    }
    	
    /**
       Set the value of errorcode.
	   @param value the new value of errorcode.
	   @throws CopyObjectException If conversion fails.
	   @see #getErrorcode
     */
    public void setErrorcode(String value)
        throws CopyObjectException
    {
        validateString("errorcode", 4, value);
        replaceBytes(0, 4, convertToHost(Converter.ALPHANUM, 4, 0, value));
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
	    List<HistoryDetailItem> detailList = new ArrayList<HistoryDetailItem>();
        return detailList;
	}

    /**
	 */
    public String toString()
    {
	    StringBuffer buf = new StringBuffer();
        buf.append("\nErrorcode = '");
        buf.append(getErrorcode());
        buf.append("'");
        return buf.toString();
	}
}
