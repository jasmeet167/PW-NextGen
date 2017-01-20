package com.csc.fsg.life.biz.bo;

import com.csc.fsg.life.biz.exception.BusinessMessage;
import com.csc.fsg.life.biz.exception.RuntimeBusinessException;

/**
 * Base runtime exception class for business objects.
 */
public class RuntimeBusinessObjectException
	extends RuntimeBusinessException
{
	/**
	 * Creats a runtime business object exception from a string message.
	 * 
	 * @param msg
	 *        The message.
	 */
	public RuntimeBusinessObjectException(String msg)
	{
		super(msg);
	}

	/**
	 * Creaets a business object exception from another exception.
	 * 
	 * @param e
	 *        The exception.
	 */
	public RuntimeBusinessObjectException(Exception e)
	{
		super(e);
	}

	/**
	 * Create a BusinessObjectException from a structued BusinessMessage object.
	 * 
	 * @param message
	 *        the structued BusinessMessage object.
	 */
	public RuntimeBusinessObjectException(BusinessMessage message)
	{
		super(message);
	}
}
