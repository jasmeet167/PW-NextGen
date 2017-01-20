package com.csc.fsg.life.pw.log;

import org.apache.commons.logging.Log;

/**
 * IPWLog: Extends commons-logging Log interface to allow getting and setting of
 * log level
 */
public interface IPWLog extends Log {
	String getLevel();

	void setLevel(String level);
}
