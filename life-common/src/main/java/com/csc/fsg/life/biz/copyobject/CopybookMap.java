package com.csc.fsg.life.biz.copyobject;

import java.util.HashMap;
import java.util.Map;

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
 *	Contains a map of Copybooks keyed by copyObject name.  This
 *	provides an easy way to retrieve the appropriate Copybook Id
 *	for a given copyObject.
 */
public class CopybookMap
{
	
	@SuppressWarnings("unchecked")
	private Map copybookMap = new HashMap();
	   
	/**
	   Returns the copybook id for the specified copyObject.
	   @param copyObjectName the copyObject to get a copybook for.
	   @return the copybook id for the copyObject.
	**/
	public String getCopybookId(String copyObjectName)
	{
		return (String)copybookMap.get(copyObjectName);
	}
    
	/**
	   Sets the CopybookMap.
	   @param copybookMap the new CopybookMap value.
	   @see #getCopybookMap
	**/
    @SuppressWarnings("unchecked")
	public void setCopybookMap(Map copybookMap)
    {
        this.copybookMap = copybookMap;
    }
    
	/**
	   Returns the CopybookMap.
	   @return the CopybookMap value.
	   @see #setCopybookMap
	**/
    @SuppressWarnings("unchecked")
	public Map getCopybookMap()
    {
        return copybookMap;
    }
}
