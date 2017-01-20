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
 * Copyright (c) 1994 - 2014 Computer Sciences Corporation,
 * all rights reserved.
 * 
 * This software may be modified only in accordance with the terms of the
 * applicable license agreement.  Any modifications may result in the 
 * voiding of applicable warranties and support services.
 */

/* Modifications: T0166 */
 
/**
 * Interface used for communication between the front-end
 * and the host system.
 */
public interface CommArea
	extends UserContextAware
{
	/** Constant for failure status. */
    public static final String COMM_FAILURE = "FAIL";
    
    /** Constant for success status. */
    public static final String COMM_SUCCESS = "SUCC";
    
    /** Constant for success status with returned messages. */
    public static final String COMM_SUCCESS_WITH_MESSAGES = "MSGS";
    
    /** Constant for errors status. */
    public static final String COMM_ERRORS = "ERRS";
    
    /**
     * Set the value of cicsTrx.
     * 
     * @param value the new value of cicsTrx.
     * @throws BusinessObjectException If there is a problem setting the value.
     */
    public void setCicsTrx(String value) throws BusinessObjectException;
 
    /**
     * Get the value of returnstatus.
     *    
     * @return the value of returnstatus.
     */
    @MetaData (size=4, scale=0, required=false)
    public String getReturnstatus();

    /**
     * Get the value of errorcode.
     * 
     * @return the value of errorcode.
     */
    @MetaData (size=4, scale=0, required=false)
    public String getErrorcode();
    
    /**
     * Get the value of responseTimestamp.
     *             
     * @return the value of responseTimestamp.
     */
    @MetaData (size=26, scale=0, required=false)
    public String getResponseTimestamp();

	/**
	 * Get the value of responseSequenceNo.
	 * 
	 * @return the value of responseSequenceNo.
	 */
	public Long getResponseSequenceNo();
}