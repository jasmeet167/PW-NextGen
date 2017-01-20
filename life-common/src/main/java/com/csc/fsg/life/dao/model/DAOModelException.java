package com.csc.fsg.life.dao.model;

import com.csc.fsg.life.exceptions.BaseException;

/********************************************************
*  This class extends from BaseException and		
*  Base Exception is extending from java.lang.Exception 
*  Use this class as the base class for any exception related 
*  to the DAOModels
*********************************************************/
public class DAOModelException 
	extends BaseException 
{
	/**
	   Creates an empty DAOModelException.
	 **/
	public DAOModelException() {
		super();
	}

	/**
	   Creates a DAOModelException from an exception.
	   @param  e Exceptino to get a message from.
	**/
	public DAOModelException(Exception e) {
		super( "" + e );
	}

	/**
	   Creates a DAOModelException from a message.
	   @param s Exception message.
	 **/
	public DAOModelException(String s) {
		super( s );
	}
}
