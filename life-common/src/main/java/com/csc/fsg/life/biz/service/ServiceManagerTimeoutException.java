package com.csc.fsg.life.biz.service;

/* Modifications: ENH863 */
/**
 * Thrown by a <code>ServiceManagerConnection</code> implementation when
 * the communication with the host system has timed out.
 */
public class ServiceManagerTimeoutException extends ServiceManagerException
{

	/**
	 * Create an exception from a message.
	 * 
	 * @param message The message.
	 */
    public ServiceManagerTimeoutException(String message)
    {
        super(message);
    }
    
	/**
	 * Create an exception from a exception.
	 * 
	 * @param e  The exception.
	 */
    public ServiceManagerTimeoutException(Exception e)
    {
        super(e);
    }
}
