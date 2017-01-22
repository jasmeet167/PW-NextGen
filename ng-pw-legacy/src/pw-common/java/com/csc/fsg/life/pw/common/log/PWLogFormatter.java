package com.csc.fsg.life.pw.common.log;

import java.text.SimpleDateFormat;
import java.util.logging.Formatter;
import java.util.logging.LogRecord;

import com.csc.fsg.life.pw.common.util.Utils;

/* Modifications: ENH959 */

public class PWLogFormatter extends Formatter {
	private final static SimpleDateFormat _logDateFmt = new SimpleDateFormat(
	        "yyyy-MM-dd HH:mm:ss,SSS");

	private final static String lineSep = System.getProperty("line.separator");

	public String format(LogRecord record) {
		StringBuffer sb = new StringBuffer(256);

		java.util.Date timestamp = new java.util.Date(record.getMillis());
		sb.append("[");
		sb.append(_logDateFmt.format(timestamp));
		sb.append("] ");

		sb.append(formatMessage(record));
		sb.append(lineSep);

		Throwable t = record.getThrown();
		if (t != null) {
			// ENH959 - use Utils.getStackTrace
			sb.append(Utils.getStackTrace(t));
			sb.append(lineSep);
		}

		return sb.toString();
	}

}
