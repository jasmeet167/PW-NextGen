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
 *	Contains a map of CopyObjects keyed by copybook name.  This
 *	provides an easy way to retrieve the appropriate CopyObject
 *	class for a given copybook.
 */
public class CopyObjectMap
{
	
	@SuppressWarnings("unchecked")
	private Map copyObjectMap = new HashMap();
	   
	/**
	   Returns the class for the specified copybook.
	   @param copybookName the copybook name to get a class for.
	   @return the copy object class name.
	**/
	public String getCopyObjectClass(String copybookName)
	{
		return (String)copyObjectMap.get(copybookName);
	}
    
	/**
	   Sets the CopyObjectMap.
	   @param copyObjectMap the new CopyObjectMap value.
	   @see #getCopyObjectMap
	**/
    @SuppressWarnings("unchecked")
	public void setCopyObjectMap(Map copyObjectMap)
    {
        this.copyObjectMap = copyObjectMap;
    }
    
	/**
	   Returns the CopyObjectMap.
	   @return the CopyObjectMap value.
	   @see #setCopyObjectMap
	**/
    @SuppressWarnings("unchecked")
	public Map getCopyObjectMap()
    {
        return copyObjectMap;
    }
}
