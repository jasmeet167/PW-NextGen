package com.csc.fsg.life.pw.log;

import java.io.*;
import java.text.SimpleDateFormat;

/**
 * Class PWSimpleLog: commons-logging Log wrapper for simple stream output.
 * Defaults to System.err output but supports setting the output stream.
 */
public class PWSimpleLog implements IPWLog {
	// numeric trace levels. For easy level comparison.
	public static final int OFF = 6;

	public static final int TRACE = 5;

	public static final int DEBUG = 4;

	public static final int INFO = 3;

	public static final int WARN = 2;

	public static final int ERROR = 1;

	public static final int FATAL = 0;

	// the current level
	private int _level = INFO;

	// the output stream
	private PrintStream _out = System.err;

	// date format prepended to every message
	private final static SimpleDateFormat _logDateFmt = new SimpleDateFormat(
	        "yyyy-MM-dd HH:mm:ss,SSS");

	public PWSimpleLog() {
	}

	public PWSimpleLog(PrintStream s) {
		setStream(s);
	}

	/**
	 * setLevel - Set the logging level
	 * 
	 * @param level -
	 *            the desired level. Valid values are "OFF", "FATAL", "ERROR",
	 *            "WARN", "INFO", "DEBUG", "TRACE".
	 */
	public void setLevel(String level) {
		// translate from commons-logging strings to numeric level
		if (level.equalsIgnoreCase("OFF"))
			_level = PWSimpleLog.OFF;
		else if (level.equalsIgnoreCase("TRACE"))
			_level = PWSimpleLog.TRACE;
		else if (level.equalsIgnoreCase("DEBUG"))
			_level = PWSimpleLog.DEBUG;
		else if (level.equalsIgnoreCase("INFO"))
			_level = PWSimpleLog.INFO;
		else if (level.equalsIgnoreCase("WARN"))
			_level = PWSimpleLog.WARN;
		else if (level.equalsIgnoreCase("ERROR"))
			_level = PWSimpleLog.ERROR;
		else if (level.equalsIgnoreCase("FATAL"))
			_level = PWSimpleLog.FATAL;
	}

	/**
	 * getLevel - return current logging level
	 * 
	 * @return the current client logging level as a String. Possible values are
	 *         "OFF", "FATAL", "ERROR", "WARN", "INFO", "DEBUG", "TRACE".
	 */
	public String getLevel() {
		// translate from numeric value to commons-logging string
		if (_level == PWSimpleLog.TRACE)
			return "TRACE";
		if (_level == PWSimpleLog.DEBUG)
			return "DEBUG";
		if (_level == PWSimpleLog.INFO)
			return "INFO";
		if (_level == PWSimpleLog.WARN)
			return "WARN";
		if (_level == PWSimpleLog.ERROR)
			return "ERROR";
		if (_level == PWSimpleLog.FATAL)
			return "FATAL";
		return "OFF";
	}

	/**
	 * setStream - set the output stream
	 * 
	 * @param s -
	 *            the new output stream
	 */
	public void setStream(PrintStream s) {
		_out = s;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.apache.commons.logging.Log#isDebugEnabled()
	 */
	public boolean isDebugEnabled() {
		return _level >= DEBUG;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.apache.commons.logging.Log#isErrorEnabled()
	 */
	public boolean isErrorEnabled() {
		return _level >= ERROR;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.apache.commons.logging.Log#isFatalEnabled()
	 */
	public boolean isFatalEnabled() {
		return _level >= FATAL;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.apache.commons.logging.Log#isInfoEnabled()
	 */
	public boolean isInfoEnabled() {
		return _level >= INFO;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.apache.commons.logging.Log#isTraceEnabled()
	 */
	public boolean isTraceEnabled() {
		return _level >= TRACE;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.apache.commons.logging.Log#isWarnEnabled()
	 */
	public boolean isWarnEnabled() {
		return _level >= WARN;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.apache.commons.logging.Log#trace(java.lang.Object)
	 */
	public void trace(Object arg0) {
		if (isTraceEnabled())
			out(arg0.toString());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.apache.commons.logging.Log#trace(java.lang.Object,
	 *      java.lang.Throwable)
	 */
	public void trace(Object arg0, Throwable arg1) {
		if (isTraceEnabled()) {
			out(arg0.toString());
			arg1.printStackTrace(_out);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.apache.commons.logging.Log#debug(java.lang.Object)
	 */
	public void debug(Object arg0) {
		if (isDebugEnabled())
			out(arg0.toString());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.apache.commons.logging.Log#debug(java.lang.Object,
	 *      java.lang.Throwable)
	 */
	public void debug(Object arg0, Throwable arg1) {
		if (isDebugEnabled()) {
			out(arg0.toString());
			arg1.printStackTrace(_out);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.apache.commons.logging.Log#info(java.lang.Object)
	 */
	public void info(Object arg0) {
		if (isInfoEnabled())
			out(arg0.toString());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.apache.commons.logging.Log#info(java.lang.Object,
	 *      java.lang.Throwable)
	 */
	public void info(Object arg0, Throwable arg1) {
		if (isInfoEnabled()) {
			out(arg0.toString());
			arg1.printStackTrace(_out);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.apache.commons.logging.Log#warn(java.lang.Object)
	 */
	public void warn(Object arg0) {
		if (isWarnEnabled())
			out(arg0.toString());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.apache.commons.logging.Log#warn(java.lang.Object,
	 *      java.lang.Throwable)
	 */
	public void warn(Object arg0, Throwable arg1) {
		if (isWarnEnabled()) {
			out(arg0.toString());
			arg1.printStackTrace(_out);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.apache.commons.logging.Log#error(java.lang.Object)
	 */
	public void error(Object arg0) {
		if (isErrorEnabled())
			out(arg0.toString());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.apache.commons.logging.Log#error(java.lang.Object,
	 *      java.lang.Throwable)
	 */
	public void error(Object arg0, Throwable arg1) {
		if (isErrorEnabled()) {
			if (arg0 != null)
				out(arg0.toString());
			arg1.printStackTrace(_out);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.apache.commons.logging.Log#fatal(java.lang.Object)
	 */
	public void fatal(Object arg0) {
		if (isFatalEnabled())
			out(arg0.toString());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.apache.commons.logging.Log#fatal(java.lang.Object,
	 *      java.lang.Throwable)
	 */
	public void fatal(Object arg0, Throwable arg1) {
		if (isFatalEnabled()) {
			out(arg0.toString());
			arg1.printStackTrace(_out);
		}
	}

	/*
	 * Put the message out to the stream
	 */
	private void out(String msg) {
		// prepend the date
		java.util.Date now = new java.util.Date();
		StringBuffer sb = new StringBuffer(256);
		sb.append("[");
		sb.append(_logDateFmt.format(now));
		sb.append("] ");

		sb.append(msg);
		_out.println(sb.toString());
	}
}
