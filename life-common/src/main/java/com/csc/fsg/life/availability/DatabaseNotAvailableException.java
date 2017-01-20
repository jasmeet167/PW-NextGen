package com.csc.fsg.life.availability;

import com.csc.fsg.life.biz.exception.BusinessMessage;

/* Modifications: T0153 */

/**
 * An exception used to indicate that the database is not available for the
 * requested type of access.
 */
public class DatabaseNotAvailableException
	extends AvailabilityException
{
	private static final long serialVersionUID = 984019571258307453L;

	/**
	 * Create an instance of DatabaseNotAvailableException with no message.
	 */
	public DatabaseNotAvailableException()
	{
		super();
	}

	/**
	 * Create an instance of DatabaseNotAvailableException with a message.
	 * 
	 * @param msg
	 *        A message associated with this exception.
	 */
	public DatabaseNotAvailableException(String msg)
	{
		super(msg);
	}

	/**
	 * Create an instance of DatabaseNotAvailableException with a structured
	 * BusinessMessage object.
	 * 
	 * @param msg
	 *        A message associated with this exception.
	 */
	public DatabaseNotAvailableException(BusinessMessage msg)
	{
		super(msg);
	}
}
