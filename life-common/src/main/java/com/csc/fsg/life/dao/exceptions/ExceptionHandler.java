package com.csc.fsg.life.dao.exceptions;

import java.sql.SQLException;
import java.util.ResourceBundle;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/* Modifications: ENHT0085 */

/**
 *  Singleton class used to convert database exceptions into
 *	user-friendly error messages.
 */
public class ExceptionHandler
{
	private static Log logger = LogFactory.getLog(ExceptionHandler.class);

	private static ExceptionHandler handler = null;

	private ExceptionHandler()
	{
		// TODO: load messages from a resource file
	}

	/**
	   Get the singleton ExceptionHandler.
	**/
	public static ExceptionHandler getInstance()
	{
		if (handler == null)
			handler = new ExceptionHandler();

		return handler;
	}

	/**
	 *  Takes an Exception and converts its message into a user 
	 *	readable form.
	 */
	public String getReadableMessage(Exception e)
	{
		// log the original exception
		e.printStackTrace();

		String msg = e.getMessage();

		if (e instanceof SQLException) {
			SQLException sqle = (SQLException)e;

			// check resource bundle for translated message
			String newMsg = null;
			try {
				ResourceBundle messages = ResourceBundle.getBundle("com.csc.fsg.life.dao.exceptions.ErrorMessages");
				newMsg = messages.getString(sqle.getSQLState());
			} 
            catch(Exception ex) {
				logger.error("ExceptionHandler: Key not found for " + sqle.getSQLState());
			}
			
			if (newMsg != null)
				return newMsg;
		}

		return msg;
	}
}

