package com.csc.fsg.life.dbutils;

import com.csc.fsg.life.exceptions.BaseException;

/**
   An exception class used to report a connection exception.
**/
public class ConnMgrException extends BaseException {

	/**
	   Create an empty exception object.
	**/
	public ConnMgrException() {
		super();
	}

	/**
	   Create an connection manager exception from another exception.
	   @param e The exception to use.
	**/
	public ConnMgrException(Exception e) {
		super( "" + e );
	}


	/**
	   Create an connection manager exception from message.
	   @param s The message to use.
	**/
	public ConnMgrException(String s) {
		super( s );
	}


}
