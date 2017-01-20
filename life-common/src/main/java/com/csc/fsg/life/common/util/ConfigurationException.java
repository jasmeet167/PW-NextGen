package com.csc.fsg.life.common.util;

/**
 * This <EM>final</EM> exception class indicates that a configuration failure occurred.<P>
 * @see Throwable#getMessage
 * @see Throwable#printStackTrace
 * @see Object#getClass
 * @see Class#getName
 */
public final class ConfigurationException
	extends Exception {

	/**
	 * The default constructor.
	 */
	public ConfigurationException() {
		super();
	}

	/**
	 * This constructor allows an exception handler to retrieve the exception message
	 * via the <EM>getMessage</EM> method.<P>
	 * @see Throwable#getMessage
	 * @param msg The message.
	 */
	public ConfigurationException(String msg) {
		super(msg);
	}
}
