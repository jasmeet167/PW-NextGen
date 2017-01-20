package com.csc.fsg.life.common.util;

/**
   An exception used when there are resource leaks at
   application shut down time.
 */
public final class ResourceLeakException
	extends Exception 
{

	/**
	   Builds an empty ResourceLeakException.
	 */
	public ResourceLeakException() {
		super();
	}

	/**
	   Builds a ResourceLeakException with a message.
	   @param msg the message.
	*/
	public ResourceLeakException(String msg) {
		super(msg);
	}
}
