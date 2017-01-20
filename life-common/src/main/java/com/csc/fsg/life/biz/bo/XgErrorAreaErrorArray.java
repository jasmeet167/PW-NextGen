package com.csc.fsg.life.biz.bo;

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
 * Copyright (c) 1994 - 2006 Computer Sciences Corporation,
 * all rights reserved.
 * 
 * This software may be modified only in accordance with the terms of the
 * applicable license agreement.  Any modifications may result in the 
 * voiding of applicable warranties and support services.
 */

/**
 * Interface for XgErrorAreaErrorArray object.
 */
public interface XgErrorAreaErrorArray
	extends UserContextAware
{
    
    /**
	 * Get the value of errorFieldDisplacement.
	 * 
	 * @return the value of errorFieldDisplacement.
	 * @see #setErrorFieldDisplacement
	 */
    @MetaData (size=9, scale=0, required=false)
	public Long getErrorFieldDisplacement();
    	
    /**
	 * Set the value of errorFieldDisplacement
	 * 
	 * @param value the new value of errorFieldDisplacement
	 * @throws BusinessObjectException If there is a problem setting the value.
	 * @see #getErrorFieldDisplacement
	 */
    public void setErrorFieldDisplacement(Long value) throws BusinessObjectException;
    
    /**
	 * Get the value of errorCode.
	 * 
	 * @return the value of errorCode.
	 * @see #setErrorCode
	 */
    @MetaData (size=4,scale=0, required=false)
    public String getErrorCode();
    	
    /**
	 * Set the value of errorCode
	 * 
	 * @param value the new value of errorCode
	 * @throws BusinessObjectException If there is a problem setting the value.
	 * @see #getErrorCode
	 */
    public void setErrorCode(String value) throws BusinessObjectException;
    
    /**
	 * Get the value of errorLevel.
	 * 
	 * @return the value of errorLevel.
	 * @see #setErrorLevel
	 */
    @MetaData (size=1, scale=0, required=false)
    public String getErrorLevel();
    	
    /**
	 * Set the value of errorLevel
	 * 
	 * @param value the new value of errorLevel
	 * @throws BusinessObjectException If there is a problem setting the value.
	 * @see #getErrorLevel
	 */
    public void setErrorLevel(String value) throws BusinessObjectException;
    
    /**
	 * Get the value of errorMessage.
	 * 
	 * @return the value of errorMessage.
	 * @see #setErrorMessage
	 */
    @MetaData (size=30, scale=0, required=false)
    public String getErrorMessage();
    	
    /**
	 * Set the value of errorMessage
	 * 
	 * @param value the new value of errorMessage
	 * @throws BusinessObjectException If there is a problem setting the value.
	 * @see #getErrorMessage
	 */
    public void setErrorMessage(String value) throws BusinessObjectException;
    
    /**
	 * Get the value of miscDetail.
	 * 
	 * @return the value of miscDetail.
	 * @see #setMiscDetail
	 */
    @MetaData (size=156, scale=0, required=false)
    public String getMiscDetail();
    	
    /**
	 * Set the value of miscDetail
	 * 
	 * @param value the new value of miscDetail
	 * @throws BusinessObjectException If there is a problem setting the value.
	 * @see #getMiscDetail
	 */
    public void setMiscDetail(String value) throws BusinessObjectException;
}
