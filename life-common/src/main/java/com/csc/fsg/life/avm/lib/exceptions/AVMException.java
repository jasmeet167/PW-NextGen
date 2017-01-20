package com.csc.fsg.life.avm.lib.exceptions;

/** Base class for most of the AVM exceptions.  Some AVM specific exceptions
  * are not derivied off of this class since they are subclasses of other base
  * AxExceptions which are defined by the framework.  If you are going to derive
  * an AVM Exception from "AxException", then please derive it off of this class
  * instead.
  *
  */
public class AVMException extends Exception {

	/** Constructor to build an AVMException with a specific message.
	  * @param  message The message to incorporate into this exception.
	  */
	public AVMException(String message) {
		super(message);
	}

	/**
	  Build an AVMException with no message.
	**/
	public AVMException() {
		super();
	}
}
