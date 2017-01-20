
package com.csc.fsg.life.exceptions;

/********************************************************
*  This class extendings from java.lang.Exceptions and	
*  use by all other exception classes as the base	
*********************************************************/
public class BaseException extends Exception
{

	/** Constant for low priority error. **/
	public static int SEVERITY_LOW = 0;
	/** Constant for medium priority error. **/
	public static int SEVERITY_MEDIUM = 1;
	/** Constant for high priority error. **/
	public static int SEVERITY_HIGH = 2;
			
	private String sErrorString=null;

	/**
	   Create an empty BaseException.
	**/
	public BaseException() {

		super();
		sErrorString = "Unknown";
	}

	/**
	   Create a BaseException from an exception.
	**/
	public BaseException( Exception e ) {

		super( "" + e );
		sErrorString = "" + e;
	}

	/**
	   Create a BaseException from a message.
	**/
	public BaseException( String s ) {

		super( s );
		sErrorString = s;
	}

	/**
	   Sets an error string message.
	**/
	protected void setErrorString(String sIn)
	{
		sErrorString = sIn;
	}

	/** 
		Returns the error string message.
	**/
	public String getErrorString()
	{
		return sErrorString;
	}
}
