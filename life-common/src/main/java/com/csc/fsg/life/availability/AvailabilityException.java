package com.csc.fsg.life.availability;

import com.csc.fsg.life.biz.exception.BusinessMessage;
import com.csc.fsg.life.biz.service.BusinessServiceException;

/* Modifications: T0153 */

/**
 * An exception used to indicate that a system resource is not available.
 */
public class AvailabilityException
	extends BusinessServiceException
{
	private static final long serialVersionUID = 8712460258105705885L;

	/**
	 * Create an instance of AvailabilityException with no message.
	 */
	public AvailabilityException()
	{
		super();
	}

	/**
	 * Create an instance of AvailabilityException with a message.
	 * 
	 * @param msg
	 *        A message associated with this exception.
	 */
	public AvailabilityException(String msg)
	{
		super(msg);
	}

	/**
	 * Create an instance of AvailabilityException with a structured
	 * BusinessMessage object.
	 * 
	 * @param msg
	 *        A message associated with this exception.
	 */
	public AvailabilityException(BusinessMessage msg)
	{
		super(msg);
	}
}
