package com.csc.fsg.life.pw.common.log;

import org.apache.commons.logging.Log;

/* Modifications: T0129 */

/**
 */
public class PWCommonLogManager {
	private static Log _log = null;

	/**
	 * setLog(): set the common logger
	 * 
	 * @param log:
	 *            reference to the common Log
	 */
	public static synchronized void setLog(Log log) {
		_log = log;
	}

	/**
	 * getLog(): return the common logger
	 * 
	 * @return the singleton common Log
	 */
	public static synchronized Log getLog() {
		if (_log == null) {
			PWSimpleLog simpleLog = new PWSimpleLog();
			simpleLog.setLevel("ERROR");
			_log = simpleLog;
			_log.error("Common log initialized to console.");
		}
		return _log;
	}

}
