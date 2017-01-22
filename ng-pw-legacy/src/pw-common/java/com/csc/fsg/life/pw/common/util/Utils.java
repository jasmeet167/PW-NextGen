/*
 * THIS PROGRAM IS THE PROPERTY OF CSC FINANCIAL SERVICES GROUP. IT MAY NOT BE
 * COPIED IN WHOLE OR IN PART WITHOUT THE EXPRESS WRITTEN CONSENT OF CSC
 * FINANCIAL SERVICES GROUP.
 */

package com.csc.fsg.life.pw.common.util;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import java.util.StringTokenizer;

/* Modifications: ENH959, WMABASEIXI-4702, T0129, T0179 */

/**
 * Class Utils
 * 
 * @author CSC-FSG,E.Hartford
 * @version PW 2.0 , 09-24-2002
 */

public final class Utils {

	private Utils() {
	}

	public static synchronized void closeResultSet(ResultSet rs) {

		try {
			if (rs != null) {
				rs.close();
			}
		} catch (SQLException e) {
			//do nothing
		}
	}

	public static synchronized void closeCallableStatement(
			CallableStatement stmt) {

		try {
			if (stmt != null) {
				stmt.close();
			}
		} catch (SQLException e) {
			//do nothing
		}
	}

	public static synchronized void closeStatement(Statement stmt) {

		try {
			if (stmt != null) {
				stmt.close();
			}
		} catch (SQLException e) {
			//do nothing
		}
	}

	public static synchronized void closePreparedStatement(
			PreparedStatement pstmt) {

		try {
			if (pstmt != null) {
				pstmt.close();
			}
		} catch (SQLException e) {
			//do nothing
		}
	}

	public static String safeTrim(String string) {

		if (string == null) {
			return ("");
		} else if (string.equals(" ")) {
			return (string);
		} else if ((0 < string.length()) && (string.trim().length() == 0)) {
			return " ";
		} else {
			return (string.trim());
		}
	}

	public static String rightJustify(String string, int length) {

		StringBuffer sb = new StringBuffer(length);

		for (int i = 0; i < length; i++) {
			sb.append(" ");
		}

		if (string == null) {
			return (" ");
		} else if (string.equals(" ")) {
			return (string);
		} else if ((0 < string.length()) && (string.trim().length() == 0)) {
			return " ";
		} else {
			sb.append(string);
			return (sb.substring(sb.length() - length));
		}
	}

//	public static String[] getDataSourceStrNSchema(String str) {
//		Log _log = PWCommonLogManager.getLog();
//		_log.debug("Incoming String " + str);
//		String[] strs = str.split("\\.");
//		String envSchema = strs[strs.length - 1];
//
//		StringBuffer ds = new StringBuffer();
//		for (int i = 0; i < (strs.length - 1); i++) {
//			ds.append(strs[i]);
//			if (i != (strs.length - 2))
//				ds.append(".");
//		}
//		String[] toSend = new String[2];
//		toSend[0] = ds.toString().trim();
//		toSend[1] = envSchema;
//
//		_log.debug("DataSource String " + toSend[0]);
//		_log.debug("Schema " + envSchema);
//		return toSend;
//	}

	public static String changeTokenizer(String initialString,
			String fromDelim, String toDelim) {

		StringBuffer chgString = new StringBuffer();
		StringTokenizer tokenizer = new StringTokenizer(initialString,
				fromDelim);
		int noOfTokens = tokenizer.countTokens();
		int count = 0;
		while (tokenizer.hasMoreTokens()) {
			if (count == noOfTokens - 1) {
				chgString.append(tokenizer.nextToken().trim());
			} else {
				chgString.append(tokenizer.nextToken().trim() + toDelim);
			}
			count++;
		}
		return chgString.toString();
	}

	/**
	 * This method ensures that the returned value has decimal place according
	 * to the value specified in the second argument
	 * 
	 * Method ensureDecimal
	 * 
	 * @param rawValue
	 * @param place
	 * @return
	 * 
	 */
	public static double ensureDecimal(String rawValue, int place) {
		String returnValue = "";
		if (rawValue.indexOf(".") == -1) {
			returnValue = rawValue.substring(0, (place - 1)) + "."
					+ rawValue.substring(place - 1);
			// System.out.println(returnValue);
			return (Double.parseDouble(returnValue));
		} else
			return Double.parseDouble(rawValue);
	}

	/**
	 * This method pads zeroes equal to second argument to the String passed as
	 * first argument
	 * 
	 * Method padZeros
	 * 
	 * @param s
	 * @param reqLen
	 * @return
	 * 
	 */
	public static String padZeros(String s, int reqLen) {
		String pz = "";
		int padlen = 0;
		int lengthAfterDec = 0;
		StringBuffer sb = new StringBuffer();
		int indexofDec = s.lastIndexOf(".");
		if (indexofDec == -1) {
			// if decimal position is not there , add all zeroes to end of it
			if (reqLen > 0) {
				sb.append(s.trim() + ".");
				while (reqLen > 0) {
					sb.append("0");
					reqLen--;
				}
			}
			pz = sb.toString();
		} else {
			lengthAfterDec = s.substring(indexofDec).length() - 1;
			if (lengthAfterDec < reqLen) {
				padlen = reqLen - lengthAfterDec;

			}
			if (padlen > 0) {
				sb.append(s.trim());
				while (padlen > 0) {
					sb.append("0");
					padlen--;
				}
				pz = sb.toString();
			} else {
				pz = s;
			}
		}

		return pz;
	}

	/**
	 * This method converts a double into a string using specific precision.one
	 * can pass any value as precision To be precise enough one can pass value
	 * for e.g. as 15, so it will deal with values upto 15 decimal places
	 * 
	 * 
	 * Method doubleToString
	 * 
	 * @param val
	 * @param precision
	 * @return
	 * 
	 */
	public static String doubleToString(double val, int precision) {

		if (Double.isNaN(val) || Double.isInfinite(val))
			return Double.toString(val);

		String origStr = String.valueOf(val);

		// find out the exponent
		int expnPos = origStr.lastIndexOf('e');

		if ((expnPos > 0)
				&& ((Math.abs(val) > Long.MAX_VALUE) || (Math.abs(val) < Math
						.pow(10.0, -precision)))) {
			String expnStr = origStr.substring(expnPos + 1);

			double base = val
					/ Math.pow(10.0, Double.valueOf(expnStr).doubleValue());

			return baseToString(base, precision) + "e" + expnStr;
		} else {
			return baseToString(val, precision);
		}
	}

	/**
	 * This method converts a base double value (no exponential component) into
	 * a string using specific precision.
	 * 
	 * Method baseToString
	 * 
	 * @param val
	 * @param precision
	 * @return
	 * 
	 */
	public static String baseToString(double val, int precision) {
		double absval = Math.abs(val);

		String signStr = "";
		if (val < 0)
			signStr = "-";

		long intDigit = (long) Math.floor(absval);
		String intDigitStr = String.valueOf(intDigit);

		// if the number is very large, double can't provide enough precision
		precision -= intDigitStr.length();

		String precDigitStr = "";
		if (precision > 0) {
			long precDigit = Math.round((absval - intDigit)
					* Math.pow(10.0, precision));
			precDigitStr = String.valueOf(precDigit);

			// Find out number of 0's right after the precision point
			StringBuffer zeroFilling = new StringBuffer();
			for (int i = 0; i < precision - precDigitStr.length(); i++)
				zeroFilling.append("0");
			precDigitStr = zeroFilling.toString() + precDigitStr;

			// Get rid of trailing 0's, find the last 0
			int lastZero;
			for (lastZero = precDigitStr.length() - 1; lastZero >= 0; lastZero--)
				if (precDigitStr.charAt(lastZero) != '0')
					break;

			precDigitStr = precDigitStr.substring(0, lastZero + 1);
		}

		if (precDigitStr.equals(""))
			return signStr + intDigitStr;
		else
			return signStr + intDigitStr + "." + precDigitStr;
	}

	// ENH959 - added method
	public static String getStackTrace(Throwable t) {

		String s = null;
		ByteArrayOutputStream bos = new ByteArrayOutputStream(512);
		PrintWriter pw = new PrintWriter(bos);
		try {
			t.printStackTrace(pw);
			pw.flush();

			s = bos.toString();
			pw.close();
			bos.close();
		} catch (IOException e) {
		}

		return s;
	}

	// WMABASEIXI-4702 
	public static String getTableIdFromDDL(String ddlName) {
		String tableId = null;
		if ((ddlName != null) && (ddlName.length() > 0)) {
			int length = ddlName.length();

			tableId = ddlName.substring((length - 4), (length - 1));
		}
		return tableId;
	}

	/**
	 * This method returns a {@code String} instance constructed by searching
	 * {@code input} for all occurrences of {@code arg}, and replacing them with
	 * {@code replacement}. The returned string is equal to value returned by
	 * the expression {@code input.replaceAll(arg, replacement)}. Direct use of
	 * regular expression is avoided in order to prevent creation of code
	 * pattern identified by HP Fortify as Regular Expressions Denial of
	 * Service.
	 */
	public static String replaceAll(String input, String arg, String replacement)
	{
		if (input == null
		 || arg == null
		 || replacement == null)
			return input;

		final int len = arg.length();
		StringBuilder buf = new StringBuilder(input);

		int idx = buf.indexOf(arg);
		while (idx >= 0) {
			buf.replace(idx, idx + len, replacement);
			idx = buf.indexOf(arg, idx);
		}

		return buf.toString();
	}
}
