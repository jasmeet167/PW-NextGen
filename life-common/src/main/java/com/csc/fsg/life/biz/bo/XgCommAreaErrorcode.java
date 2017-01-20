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
 * Copyright (c) 1994 - 2009 Computer Sciences Corporation,
 * all rights reserved.
 * 
 * This software may be modified only in accordance with the terms of the
 * applicable license agreement.  Any modifications may result in the 
 * voiding of applicable warranties and support services.
 */

/**
 * Interface for CommArea sub object ErrorCode.
 */
public interface XgCommAreaErrorcode
	extends UserContextAware
{
    /**
     *  Get the value of errorcode
     */
    @MetaData (size=4, scale=0, required=false)
    public String getErrorcode();
    	
    /**
     *  Set the value of errorcode
     */
    public void setErrorcode(String value) throws BusinessObjectException;
}
