package com.csc.fsg.life.pw.web.log;

import com.csc.fsg.life.pw.common.log.*;
import com.csc.fsg.life.pw.web.config.AppConfig;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.commons.logging.impl.Jdk14Logger;

import java.io.*;
import java.util.logging.*;
import java.util.*;

/* Modifications: T0091, T0113, T0111 */
// T0111 - remove dependency on JDK underlying implementation.  Use LogFactory.getLog

/**
 * Class PWServerLogManager: Maintains server loggers and logging levels
 */
public class PWServerLogManager {
	
	static PWLogFormatter _formatter;
	static {
		_formatter = new PWLogFormatter();
		PWCommonLogManager.setLog(getLog("com.csc.fsg.life.pw.common"));
	}
	
	/**
	 * getLog(): create and/or return the logger for name parameter
	 * 
	 * @param name -
	 *            name of the logger to get
	 * @return logger specific to name parameter
	 */
	public static synchronized Log getLog(String name) {
		Log log = LogFactory.getLog(name);
		if ( log instanceof Jdk14Logger ) {
			// make sure all handlers use our formatter
			Logger tmpLog = ((Jdk14Logger)log).getLogger();
			while (tmpLog != null) {
				Handler[] handlers = tmpLog.getHandlers();
				for (Handler handler : handlers) {
					handler.setFormatter(_formatter);
				}
				tmpLog = tmpLog.getParent();
			}
		}
		return log;
	}
}
