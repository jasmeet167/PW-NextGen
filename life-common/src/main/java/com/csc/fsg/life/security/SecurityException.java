package com.csc.fsg.life.security;

import java.security.GeneralSecurityException;

import com.csc.fsg.life.biz.exception.BusinessException;
import com.csc.fsg.life.biz.exception.BusinessMessage;

/**
   General Security Exception. 
**/
public class SecurityException extends BusinessException
{  
	/**
	   Create a SecurityException with no message. 
	**/
    public SecurityException()
    {
        super();
    }
    
	/**
	 * Create a SecurityException with a message.
	 * @param msg - excpetion message 
	**/
    public SecurityException(String msg)
    {
        super(msg);
    }
    
	/** 
		Create a SecurityException with a structured BusinessMessage object.
		@param msg The message object.
	**/
    public SecurityException(BusinessMessage msg)
    {
        super(msg);
    }

	/**
	   Creates a new SecurityException from a JAAS exception.
	   @param gse The security exception that cuase the creation of this exception.
	**/
    public SecurityException(GeneralSecurityException gse)
	{
		super(gse);
	}
}
