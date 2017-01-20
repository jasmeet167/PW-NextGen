/*
 * THIS PROGRAM IS THE PROPERTY OF CSC FINANCIAL SERVICES GROUP. IT MAY NOT BE
 * COPIED IN WHOLE OR IN PART WITHOUT THE EXPRESS WRITTEN CONSENT OF CSC
 * FINANCIAL SERVICES GROUP.
 */

package com.csc.fsg.life.pw.io;

/* Modifications: ENH961 */
// ENH961 - added class

/**
 * Msg is informative to user
 */

public class InfoException extends Exception {

	/**
	 * Constructor InfoException
	 */

	public InfoException() {
		super();
	}

	/**
	 * Constructor InfoException
	 * 
	 * @param msg
	 */

	public InfoException(String msg) {
		super(msg);
	}
}
