package com.csc.fsg.life.dao.exceptions;

import com.csc.fsg.life.exceptions.BaseException;

/********************************************************
*  This class extends from BaseException and		
*  Base Exception is extending from java.lang.Exception 
*  Use this class as the base class for any exception related 
*  to the JBOs
*********************************************************/
public class BusinessObjectException 
    extends BaseException 
{
	/**
	   Creates an empty BusinessObjectException.
	 **/
    public BusinessObjectException() {
        super();
    }

    /**
	   Creates a BusinessObjectException from an exception.
	   @param  e Exceptino to get a message from.
     **/
    public BusinessObjectException(Exception e) {
        super( "" + e );
    }
    
    /**
	   Creates a BusinessObjectException from a message.
	   @param s Exception message.
	**/
    public BusinessObjectException(String s) {
        super( s );
    }
}
