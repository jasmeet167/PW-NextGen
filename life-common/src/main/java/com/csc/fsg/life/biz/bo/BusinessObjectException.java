package com.csc.fsg.life.biz.bo;

import com.csc.fsg.life.biz.exception.BusinessException;
import com.csc.fsg.life.biz.exception.BusinessMessage;

/**
   Base exception class for business objects.
**/
public class BusinessObjectException 
    extends BusinessException 
{
	/**
	   Creaets a business object exception from a string message.
	   @param msg The message.
	**/
    public BusinessObjectException(String msg)
    {
        super(msg);
    }

	/**
	   Creaets a business object exception from another exception.
	   @param e The exception.
	**/
    public BusinessObjectException(Exception e)
    {
        super(e);
    }

    /**
	   Create a BusinessObjectException from a structued BusinessMessage object.
	   @param message the structued BusinessMessage object.
	**/
    public BusinessObjectException(BusinessMessage message)
    {
    	super(message);
    }
}
