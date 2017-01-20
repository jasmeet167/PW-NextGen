package com.csc.fsg.life.biz.service;

import com.csc.fsg.life.biz.exception.BusinessException;
import com.csc.fsg.life.biz.exception.BusinessMessage;

/**
 * General business service exception class. Used when there is an error in the
 * service.
 */
public class BusinessServiceException extends BusinessException
{   
	/**
	 * Create an empty exception object.
	 */
    public BusinessServiceException()
    {
        super();
    }

    /**
	 * Create the exception from a message.
	 * 
	 * @param msg The message.
	 */
    public BusinessServiceException(String msg)
    {
        super(msg);
    }
    
	/**
	 * Create the exception from a structured BusinesssMessage object.
	 * 
	 * @param msg The message object.
	 * @see BusinessMessage
	 */
    public BusinessServiceException(BusinessMessage msg)
    {
        super(msg);
    }
}
