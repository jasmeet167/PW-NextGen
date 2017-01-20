package com.csc.fsg.life.biz.service;

import com.csc.fsg.life.biz.exception.BusinessException;
import com.csc.fsg.life.biz.exception.BusinessMessage;

/**
 * The exception class thrown when a service is called with invalid data.
 */
public class ServiceValidationException extends BusinessException
{  
	/**
	 * Create an empty exception class.
	 */
    public ServiceValidationException()
    {
        super();
    }
    
	/**
	 * Create this exception class with a generic message.
	 * 
	 * @param msg The message.
	 */
    public ServiceValidationException(String msg)
    {
        super(msg);
    }
    
	/**
	 * Create this exception class with a BusinesMessage object.
	 * 
	 * @param msg the structured BusinessMessage class.
	 */
    public ServiceValidationException(BusinessMessage msg)
    {
        super(msg);
    }
}
