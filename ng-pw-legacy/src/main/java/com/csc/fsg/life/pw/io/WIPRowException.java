/*
 * THIS PROGRAM IS THE PROPERTY OF CSC FINANCIAL SERVICES GROUP. IT MAY NOT BE
 * COPIED IN WHOLE OR IN PART WITHOUT THE EXPRESS WRITTEN CONSENT OF CSC
 * FINANCIAL SERVICES GROUP.
 */

package com.csc.fsg.life.pw.io;

/**
 * WIPAssistent class thorws this exception
 */

public class WIPRowException extends PWIOException {

	/**
	 * Constructor WIPRowException
	 */

	public WIPRowException() {
		super();
	}

	/**
	 * Constructor WIPRowException
	 * 
	 * @param msg
	 */

	public WIPRowException(String msg) {
		super(msg);
	}
}
