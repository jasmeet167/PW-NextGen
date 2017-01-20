package com.csc.fsg.life.biz.service;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import com.csc.fsg.life.biz.bo.XgErrorArea;
import com.csc.fsg.life.biz.bo.XgErrorAreaErrorArray;
import com.csc.fsg.life.context.UserContext;

/* Modifications: T0175 */

/**
   Simple ErrorMessageManager that returns the same message it got.
**/
public class BasicErrorMessageManager
	implements ErrorMessageManager
{
	private static final long serialVersionUID = 2725272468244442113L;

	private Map<String, String> errorCodes = new HashMap<String, String>();

	/**
	   Called to initialize the error message for this request.
	**/
	public void initErrors(XgErrorArea errorArea, UserContext userContext)
	{
		Iterator<XgErrorAreaErrorArray> errorArrayItt = errorArea.getXgErrorAreaErrorArray().iterator();    
		while (errorArrayItt.hasNext()) {
			XgErrorAreaErrorArray error = (XgErrorAreaErrorArray)errorArrayItt.next();
			errorCodes.put(error.getErrorCode(), error.getErrorMessage());
		}
	}

	/**
	   Called to build a single error message.
	**/
	public String buildErrorMessage(String errorCode)
	{
		String msg = errorCodes.get(errorCode);
		if (msg == null)
			return "Error description not available.";
		else
			return msg;
	}

}
