package com.csc.fsg.life.biz.bo;

import java.util.List;

import com.csc.fsg.life.biz.meta.MetaData;
import com.csc.fsg.life.context.UserContextAware;

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
 * Copyright (c) 1994 - 2009 Computer Sciences Corporation,
 * all rights reserved.
 * 
 * This software may be modified only in accordance with the terms of the
 * applicable license agreement.  Any modifications may result in the 
 * voiding of applicable warranties and support services.
 */

/**
 * Interface for XgErrorArea object.
 */ 
public interface XgErrorArea
	extends UserContextAware
{
    /**
     * Get the value of trxStatus.
     *
     * @return the value of trxStatus.
	 * @see #setTrxStatus
     */
    @MetaData (size=2, scale=0, required=false)
    public String getTrxStatus();
    	
    /**
     * Set the value of trxStatus
	 * @param value the new value of trxStatus
	 * @throws BusinessObjectException If there is a problem setting the value.
	 * @see #getTrxStatus
     */
    public void setTrxStatus(String value) throws BusinessObjectException;
    
    /**
     * Get the value of trxIdentification.
     *
     * @return the value of trxIdentification.
	 * @see #setTrxIdentification
     */
    @MetaData (size=4, scale=0, required=false)
    public String getTrxIdentification();
    	
    /**
     * Set the value of trxIdentification
	 * @param value the new value of trxIdentification
	 * @throws BusinessObjectException If there is a problem setting the value.
	 * @see #getTrxIdentification
     */
    public void setTrxIdentification(String value) throws BusinessObjectException;
    
    /**
     * Get the value of numberOfErrorsReturned.
     * 
     * @return the value of numberOfErrorsReturned.
	 * @see #setNumberOfErrorsReturned
     */
    @MetaData (size=3, scale=0, required=false)
    public Long getNumberOfErrorsReturned();
    	
    /**
     * Set the value of numberOfErrorsReturned
	 * @param value the new value of numberOfErrorsReturned
	 * @throws BusinessObjectException If there is a problem setting the value.
	 * @see #getNumberOfErrorsReturned
     */
    public void setNumberOfErrorsReturned(Long value) throws BusinessObjectException;
    
    /**
     * Get the value of XgErrorAreaErrorArray.
	 * @return the value of XgErrorAreaErrorArray
     */
    public List<XgErrorAreaErrorArray> getXgErrorAreaErrorArray();
    
    /**
     * Create an new instance of XgErrorAreaErrorArrayCopyObject.
	 * @return the new instance.
	 * @throws BusinessObjectException if unable to create an initialize the new object.
     */
    public XgErrorAreaErrorArray createXgErrorAreaErrorArray() throws BusinessObjectException;
    
    /**
	 * Remove an object from the XgErrorAreaErrorArray list.
	 * @throws BusinessObjectException if unable to remove the object from the collection.
     */
    public boolean removeXgErrorAreaErrorArray(XgErrorAreaErrorArray value) throws BusinessObjectException;
}
