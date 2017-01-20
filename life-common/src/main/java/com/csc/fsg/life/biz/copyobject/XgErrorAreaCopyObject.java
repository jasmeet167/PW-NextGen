package com.csc.fsg.life.biz.copyobject;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.csc.fsg.life.biz.bo.BusinessObjectException;
import com.csc.fsg.life.biz.bo.HistoryDetailItem;
import com.csc.fsg.life.biz.bo.XgErrorArea;
import com.csc.fsg.life.biz.bo.XgErrorAreaErrorArray;
import com.csc.fsg.life.biz.service.ServiceValidationException;
import com.csc.fsg.life.common.util.FixedSizeArrayList;
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
public class XgErrorAreaCopyObject
    extends CopyObject 
    implements XgErrorArea 
{
	/** Standard apache commons logger. */
	protected static Log log = LogFactory.getLog("com.csc.fsg");
	  
    /** List to support XgErrorAreaErrorArray occurs. */
    private List<XgErrorAreaErrorArray> xgErrorAreaErrorArray;
    
    /**
	 * Default XgErrorAreaCopyObject constructor. Creates this object and
	 * initializes all fields and sub-objects with default values.
	 * 
	 * @throws CopyObjectException	If there is an initialization problem.
	 */
    public XgErrorAreaCopyObject() 
        throws CopyObjectException 
    {
        bytes = new byte[21000];
        try {
            setTrxStatus("");
            setTrxIdentification("");
            setNumberOfErrorsReturned(Long.valueOf(0));
            xgErrorAreaErrorArray = new FixedSizeArrayList<XgErrorAreaErrorArray>(100);
            XgErrorAreaErrorArrayCopyObject xgErrorAreaErrorArrayCopyObject = new XgErrorAreaErrorArrayCopyObject();
            for (int i=0; i<100; i++) {
                int start = 9 + (i * 200);
                int end = start + 200;
                replaceBytes(start, end, xgErrorAreaErrorArrayCopyObject.toBytes());
            }
            replaceBytes(20009, 21000, convertToHost(Converter.ALPHANUM, 991, 0, ""));
        }
        catch (NumberFormatException nfE) {
            throw new CopyObjectException(nfE.getMessage());
    }
    }
    
    /**
	 * Creates this object and initializes all fields and sub-objects with
	 * default values. In addition, initializes the user context.
	 * 
	 * @param userContext	the user context.
	 * @throws CopyObjectException	If there is an initialization problem.
	 */
    public void init(UserContext userContext) 
        throws CopyObjectException
    {
        if (initialized) 
            return;
        
        setUserContext(userContext);
        try {
            //bypass occursgroup xgErrorAreaErrorArray
        }
        catch (NumberFormatException nfE) {
            throw new CopyObjectException(nfE.getMessage());
        }

        initialized = true;
    }

    /**
	 * Initialization method for this copy object. Additional flag that can be
	 * used to force re-initialization.
	 * 
	 * @param userContext 	The user context.
	 * @param isForced 		true to force re-initialization.
	 * @throws BusinessObjectException	If initialization fails.
	 */
    public void init(UserContext userContext, boolean isForced) 
    	throws BusinessObjectException
    {
    	if (isForced)
    		initialized = false;
    		
    	init(userContext);
    }

    /**
	 * Create a XgErrorAreaCopyObject from the specified byte array.
	 * 
	 * @param userContext  The user context.
	 * @param copybookData The byte array to initialize to.
	 * @throws CopyObjectException If initialization fails.
	 */
    public XgErrorAreaCopyObject(UserContext userContext, byte[] copybookData) 
        throws CopyObjectException
    {
        this();
    	init(userContext, copybookData);
    }
    
    /**
	 * Initialization method for this copy object. This initializes the copy
	 * object off of the byte array.
	 * 
	 * @param userContext	The user context.
	 * @param copybookData	The byte array to initialize to.
	 * @throws CopyObjectException	If initialization fails.
	 */
    public void init(UserContext userContext, byte[] copybookData) 
        throws CopyObjectException
    {
        if (copybookData.length > 21000)
            throw new CopyObjectException("Invalid length for XgErrorArea copybook, length must be less than 21000 but length is " + copybookData.length);
        
        // initialize byte array
        init(userContext);
        
        // copy the given copybook data into byte array
        System.arraycopy(copybookData, 0, bytes, 0, copybookData.length);

        xgErrorAreaErrorArray = new FixedSizeArrayList<XgErrorAreaErrorArray>(100);
        for (int i=0; i<100; i++) {
            int start = 9 + (i * 200);
            int end = start + 200;
            XgErrorAreaErrorArrayCopyObject xgErrorAreaErrorArrayCopyObject = new XgErrorAreaErrorArrayCopyObject(userContext, getBytes(start, end));
            if (xgErrorAreaErrorArrayCopyObject.isXgErrorAreaErrorArrayPopulated())
                xgErrorAreaErrorArray.add(xgErrorAreaErrorArrayCopyObject);
            else
                break;
        }
    }

	/**
	 * Returns the copybook name.
	 * 
	 * @return the copybook name.
	 */
	public String getCopybookName()
	{
		return "CXGERREC";
	}

	/**
	 * Returns a bye array that represents the copybook. This byte array is
	 * already converted to the appropriate format (ASCII, EBCIDIC, etc.) as
	 * defined by the converter.
	 * 
	 * @return a bye array that represents the copybook.
	 * @throws CopyObjectException	If conversion fails.
	 */
    public byte[] toBytes()
        throws CopyObjectException
    {
        for (int i=0; i<xgErrorAreaErrorArray.size(); i++) {
            int start = 9 + (i * 200);
            int end = start + 200;
            replaceBytes(start, end, ((XgErrorAreaErrorArrayCopyObject)xgErrorAreaErrorArray.get(i)).toBytes());
        }
        for (int i=xgErrorAreaErrorArray.size(); i<100; i++) {
            int start = 9 + (i * 200);
            int end = start + 200;
            XgErrorAreaErrorArrayCopyObject xgErrorAreaErrorArrayCopyObject = new XgErrorAreaErrorArrayCopyObject();
            replaceBytes(start, end, xgErrorAreaErrorArrayCopyObject.toBytes());
        }
        return bytes;
    }
    
    /**
	 * Get the value of trxStatus.
	 * 
	 * @return the value of trxStatus.
	 * @throws CopyObjectException If conversion fails.
	 * @see #setTrxStatus
	 */
    public String getTrxStatus()
    {
        return convertFromHost(Converter.ALPHANUM, 2, 0, getBytes(0, 2)).trim();
    }
    	
    /**
	 * Set the value of trxStatus.
	 * 
	 * @param value the new value of trxStatus.
	 * @throws CopyObjectException If conversion fails.
	 * @see #getTrxStatus
	 */
    public void setTrxStatus(String value)
        throws CopyObjectException
    {
        validateString("trxStatus", 2, value);
        replaceBytes(0, 2, convertToHost(Converter.ALPHANUM, 2, 0, value));
    }
    
    /**
	 * Get the value of trxIdentification.
	 * 
	 * @return the value of trxIdentification.
	 * @throws CopyObjectException	If conversion fails.
	 * @see #setTrxIdentification
	 */
    public String getTrxIdentification()
    {
        return convertFromHost(Converter.ALPHANUM, 4, 0, getBytes(2, 6)).trim();
    }
    	
    /**
	 * Set the value of trxIdentification.
	 * 
	 * @param value	the new value of trxIdentification.
	 * @throws CopyObjectException	If conversion fails.
	 * @see #getTrxIdentification
	 */
    public void setTrxIdentification(String value)
        throws CopyObjectException
    {
        validateString("trxIdentification", 4, value);
        replaceBytes(2, 6, convertToHost(Converter.ALPHANUM, 4, 0, value));
    }
    
    /**
	 * Get the value of numberOfErrorsReturned.
	 * 
	 * @return the value of numberOfErrorsReturned.
	 * @throws CopyObjectException	If conversion fails.
	 * @see #setNumberOfErrorsReturned
	 */
    public Long getNumberOfErrorsReturned()
    {
        return Long.valueOf(convertFromHost(Converter.NUMERIC, 3, 0, getBytes(6, 9)));
    }
    	
    /**
	 * Set the value of numberOfErrorsReturned.
	 * 
	 * @param value the new value of numberOfErrorsReturned.
	 * @throws CopyObjectException If conversion fails.
	 * @see #getNumberOfErrorsReturned
	 */
    public void setNumberOfErrorsReturned(Long value)
        throws CopyObjectException
    {
        if (value == null)
            value = Long.valueOf(0);
        else
            validateLong("numberOfErrorsReturned", 3, 0, value);
        replaceBytes(6, 9, convertToHost(Converter.NUMERIC, 3, 0, value.toString()));
    }
    
    /**
	 * Get List supporting xgErrorAreaErrorArray occurs.
	 * 
	 * @return the list supporting xgErrorAreaErrorArray occurs.
	 */
    public List<XgErrorAreaErrorArray> getXgErrorAreaErrorArray() {
        return xgErrorAreaErrorArray;
    }
    
    /**
	 * Create an new instance of XgErrorAreaErrorArrayCopyObject.
	 * 
	 * @return the new instance.
	 * @throws CopyObjectException If conversion fails.
	 */
    public XgErrorAreaErrorArray createXgErrorAreaErrorArray() 
        throws CopyObjectException
    {
        XgErrorAreaErrorArrayCopyObject obj = new XgErrorAreaErrorArrayCopyObject();
        obj.init(getUserContext());
        return obj;
    }

    /**
     *  Remove an instance of XgErrorAreaErrorArrayCopyObject from List supporting xgErrorAreaErrorArray occurs.
     */
    public boolean removeXgErrorAreaErrorArray(XgErrorAreaErrorArray value) {
        return xgErrorAreaErrorArray.remove(value);
    }

    /**
	 * Validate this copy object.
	 * 
	 * @throws ServiceValidationException If there is invalid data.
	 */
    public void validate()
		throws ServiceValidationException
    {
		ServiceValidationException e = new ServiceValidationException();

        validate(getXgErrorAreaErrorArray(), e);

		// If there were any invalid allowable values throw exception.
		if (e.hasMessages())
			throw e;
    }

    /**
	 * Returns a list of detail objects. Each object in the list represents one
	 * field in the copybook.
	 * 
	 * @param prefix	A string to prefix field names with.
	 * @return a list of detail objects.
	 */
	public List<HistoryDetailItem> toDetailList(String prefix)
	{
		return new ArrayList<HistoryDetailItem>();
	}

    public String toString()
    {
	    StringBuffer buf = new StringBuffer();
        buf.append("\nTrxStatus = '");
        buf.append(getTrxStatus());
        buf.append("'");
        buf.append("\nTrxIdentification = '");
        buf.append(getTrxIdentification());
        buf.append("'");
        buf.append("\nNumberOfErrorsReturned = '");
        buf.append(getNumberOfErrorsReturned());
        buf.append("'");
        buf.append("\nXgErrorAreaErrorArray = '");
        buf.append(getXgErrorAreaErrorArray());
        buf.append("'");
        return buf.toString();
	}
}
