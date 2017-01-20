package com.csc.fsg.life.biz.service;

import com.csc.fsg.life.biz.exception.BusinessMessage;

/* Modifications: T0160 */

/**
 * This exception is intended to be used to indicate the "No record found"
 * condition.
 * <p>
 * In order to optimize such notification process, this exception will produce
 * limited console output, due to customized logic inherited from the super
 * class.
 * 
 * @see BusinessServiceQuietException
 */
public class RecordNotFoundException
	extends BusinessServiceQuietException
{
	private static final long serialVersionUID = -1677926893066874632L;

	/**
	 * Create an exception with default message.
	 */
	public RecordNotFoundException()
	{
		super("No record found");
	}

	/**
	 * Create the exception from a message.
	 * 
	 * @param msg
	 *        Detailed message.
	 */
	public RecordNotFoundException(String msg)
	{
		super(msg);
	}

	/**
	 * Create the exception from an instance of <code>BusinessMessage</code>
	 * object.
	 * 
	 * @param msg
	 *        Detailed message.
	 */
	public RecordNotFoundException(BusinessMessage msg)
	{
		super(msg);
	}
}
