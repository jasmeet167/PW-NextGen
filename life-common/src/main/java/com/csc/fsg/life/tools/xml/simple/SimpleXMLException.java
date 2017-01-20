package com.csc.fsg.life.tools.xml.simple;

import com.csc.fsg.life.biz.bo.BusinessObjectException;


/**
 * This class is used by Simple XML Encoder to indicate an error condition.
 */
public class SimpleXMLException
	extends BusinessObjectException
{
	/**
	 * Create a new exception.
	 * 
	 * @param msg
	 *            The message.
	 */
	public SimpleXMLException(String msg)
	{
		super(msg);
	}
}
