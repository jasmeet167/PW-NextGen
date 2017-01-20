package com.csc.fsg.life.biz.copyobject;

import com.csc.fsg.life.biz.bo.BusinessObjectException;

/**
 *  This class is used by the copy objects when there is
 *  an error while processing.
 */
public class CopyObjectException extends BusinessObjectException 
{   
	/**
	   Create a new exception.
	   @param msg The message.
	**/
    public CopyObjectException(String msg)
    {
        super(msg);
    }
}
