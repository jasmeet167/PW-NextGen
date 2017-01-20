package com.csc.fsg.life.biz.service;

import java.io.Serializable;

import com.csc.fsg.life.biz.bo.XgErrorArea;
import com.csc.fsg.life.context.UserContext;

/* Modifications: T0175 */

/**
   Helper interface used to let projects build error messages 
   in different ways.
**/
public interface ErrorMessageManager
	extends Serializable
{
	/**
	   Called to initialize the error message for this request.
	**/
	public void initErrors(XgErrorArea errorArea, UserContext userContext);

	/**
	   Called to build a single error message.
	**/
	public String buildErrorMessage(String errorCode);
}
