package com.csc.fsg.life.dao.dataaccessor;

import com.csc.fsg.life.exceptions.BaseException;

/**
   This class extends from BaseException and
   Base Exception is extending from java.lang.Exception
   Use this class as the base class for any exception related
   to the DataAccessors
**/
public class DataAccessorException 
	extends BaseException 
{
	/**
	   Creates a new DataAccessorException excpeiton class with no message.
	**/
	public DataAccessorException() {
		super();
	}

	/**
	   Creates a new DataAccessorException excpeiton class with from 
	   the specified exception.
	   @param  e Exceptino to get a message from.
	**/
	public DataAccessorException(Exception e) {
		super( "" + e );
	}

	/**
	   Creates a new DataAccessorException excpeiton class with 
	   the specified message.
	   @param s Exception message.
	**/
	public DataAccessorException(String s) {
		super( s );
	}
}
