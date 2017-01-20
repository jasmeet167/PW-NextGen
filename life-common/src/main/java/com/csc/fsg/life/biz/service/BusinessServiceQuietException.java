package com.csc.fsg.life.biz.service;

import java.io.PrintStream;
import java.io.PrintWriter;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.csc.fsg.life.biz.exception.BusinessMessage;

/* Modifications: T0160 */

/**
 * The purpose of this class is to provide a base
 * <code>BusinessServiceException</code> class, which can be extended to
 * indicate specialized information about the state of the application.
 * <p>
 * All of the <code>printStackTrace</code> methods in these exceptions will do
 * nothing, unless one or both of the following conditions are true:
 * <ul>
 * <li>Value of the flag <code>quietMode</code> is explicitly set to
 * <code>false</code></li>
 * <li>Logging level is set to DEBUG or TRACE</li>
 * </ul>
 */
public class BusinessServiceQuietException
	extends BusinessServiceException
{
	private static final long serialVersionUID = 3042737390104474488L;
	protected static final Log log = LogFactory.getLog("com.csc.fsg");

	private boolean isQuietMode = true;

	/**
	 * Create an empty exception.
	 */
	public BusinessServiceQuietException()
	{
		super();
	}

	/**
	 * Create the exception from a message.
	 * 
	 * @param msg
	 *        Detailed message.
	 */
	public BusinessServiceQuietException(String msg)
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
	public BusinessServiceQuietException(BusinessMessage msg)
	{
		super(msg);
	}

	/**
	 * Enable or disable the facility to print stack trace associated with this
	 * exception.
	 */
	protected void setQuietMode(boolean isQuietMode)
	{
		this.isQuietMode = isQuietMode;
	}

	/**
	 * If the object is in quiet mode, then this method will do nothing.
	 * Otherwise, it will invoke the overridden method in the super class.
	 * 
	 * @see Throwable#printStackTrace()
	 */
	@Override
	public void printStackTrace()
	{
		if (!isQuietMode || log.isDebugEnabled())
			super.printStackTrace();
	}

	/**
	 * If the object is in quiet mode, then this method will do nothing.
	 * Otherwise, it will invoke the overridden method in the super class.
	 * 
	 * @see Throwable#printStackTrace(PrintStream)
	 */
	@Override
	public void printStackTrace(PrintStream s)
	{
		if (!isQuietMode || log.isDebugEnabled())
			super.printStackTrace(s);
	}

	/**
	 * If the object is in quiet mode, then this method will do nothing.
	 * Otherwise, it will invoke the overridden method in the super class.
	 * 
	 * @see Throwable#printStackTrace(PrintWriter)
	 */
	@Override
	public void printStackTrace(PrintWriter s)
	{
		if (!isQuietMode || log.isDebugEnabled())
			super.printStackTrace(s);
	}
}
