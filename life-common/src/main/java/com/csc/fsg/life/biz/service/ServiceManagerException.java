package com.csc.fsg.life.biz.service;


import com.csc.fsg.life.biz.exception.BusinessException;


/**
 * General service manager exception class. Used when there is an error in the
 * service manager.
 */
public class ServiceManagerException
	extends BusinessException
{

	/**
	 * Create an exception from a message.
	 * 
	 * @param message The message.
	 */
    public ServiceManagerException(String message)
    {
        super(message);
    }
    
	/**
	 * Create an exception from a exception.
	 * 
	 * @param e The exception.
	 */
    public ServiceManagerException(Exception e)
    {
        super(e);
    }
}
