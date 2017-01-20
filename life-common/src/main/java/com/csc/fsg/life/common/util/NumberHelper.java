package com.csc.fsg.life.common.util;

import java.text.DecimalFormat;
import java.text.NumberFormat;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/* Modifications: T0140, T0150, T0175, WMA-1879 */

/**
   Utility class for helper methods related to numbers.
**/
public class NumberHelper
{
    /** Class Logger from Apache Commons Logging. **/
	protected static final Log log = LogFactory.getLog("com.csc.fsg");
   
    private static final String S_PERCENT = "#,##0.###%";
	private static final String S_NUMBER = "#,##0.#####";
    private static final String S_NUMBER_STRING = "###0.#####";

    // Standard units are 6.5 - but maybe formatUnit() should allow for a
    // parameter that specifies the precision?
    private static final String S_UNIT = "#,##0.00000";
    
    public static String zeroFill(String str, int size)
    {
    	// Sanity check size
    	if (size < 0) return str;
    	
        if (str.length() < size) {
        	StringBuilder sb = new StringBuilder();
        	
            char ch = '0';
            int count = size - str.length();
            for(int i = 0; i < count; i++) {
            	sb.append(ch);
            }
            return sb.append(str).toString();
        }

        return str;
    }
    
    /**
	   Formats a double as a currency in a string.
	   @param d the number to format.
	   @return the number as a currency.
	**/
    public static String formatCurrency(double d)
    {
        return formatCurrency(new Double(d));
    }

    /**
	   Formats a number as a currency in a string.
	   @param numCurr the number to format.
	   @return the number as a currency.
	**/
    public static String formatCurrency(Number numCurr)
    {
        if (numCurr == null)
            return "";

        return NumberFormat.getCurrencyInstance().format(numCurr);
    }

	/**
	   Will convert a Double to a String based on a given
	   format.  For example, 25.0 will be converted to 25 but
	   25.25 will remain 25.25.  It will include commas if
       necessary.
	   @param d the number to format.
	   @return the formatted number,
	**/
	public static String formatNumber(Double d)
	{
		if (d == null)
			return "";
		
		return formatNumber(d.doubleValue());
	}
	
	/**
	   Will convert a double to a String based on a given
	   format.  For example, 25.0 will be converted to 25 but
	   25.25 will remain 25.25.  It will include commas if
       necessary.
	   @param d the number to format.
	   @return the formatted number,
	**/
	public static String formatNumber(double d)
	{	
		return new DecimalFormat(S_NUMBER).format(d);
	}
	
    /**
	   Formats a Double as a string with no commas.
	   @param d the number to format.
	   @return the formatted number,
     */
    public static String formatNumberString(Double d)
    {   
        if (d == null)
            return "";
        
        return formatNumberString(d.doubleValue());
    }
    
    /**
	   Formats a Double as a string with no commas.
	   @param d the number to format.
	   @return the formatted number,
     */
    public static String formatNumberString(double d)
    {   
        return new DecimalFormat(S_NUMBER_STRING).format(d);
    }
    
    public static String formatNumberString(Double d, int scale)
    {	
		if (d == null)
			return "";

		StringBuilder buf = new StringBuilder("###0.#");

		for (int i = 0, n = scale; i < n; i++)
			buf.append("#");

		String mask = buf.toString();
    	    	
		return new DecimalFormat(mask).format(d.doubleValue());
    }
    
	/**
	   Rounds a double to the specified precision.
	   After performing calculations the double value my be
	   Slightly off, this rounds off the extra.
	   An example of a problematic calculation is 0.555d * 100.0d
	   @param dval The value to round.
	   @param precision The number of significant decimal positions.
	**/
	static public double roundDouble(double dval, int precision)
	{
		// Remove precision
		double pVal = Math.pow(10, precision);
		dval = dval * pVal;
		
		// round to a long
		long val = Math.round(dval);
		
		// Go back to a double
		double roundedVal = (double)val;
		
		// add precision back in
		roundedVal = roundedVal / pVal;
		return roundedVal;
	}
	
    /**
	   Formats a number as a percent in a string.
	   @param n the number to format.
	   @return the number as a percent.
	**/
    public static String formatPercent(Double n)
    {
        if (n == null)
            return "";

        //if (n.doubleValue() > 1.0)
        // for percent less then 1.0 e.g .5% was turned into 50% 
            n = new Double(n.doubleValue() / 100.0);

        return new DecimalFormat(S_PERCENT).format(n);
    }

    /**
	   Formats a number as a unit in a string.
	   @param n the number to format.
	   @return the number as a unit.
	**/
	public static String formatUnit(Double n)
	{
        if (n == null)
            return "";

        return new DecimalFormat(S_UNIT).format(n);
	}

	/**
	   Attempts to parse a numeric value from a string.  The number may 
	   be formatted as a percent or a currency.
	   @param value The value to parse.
	   @return the value as a Double.
	**/
    public static Double parseDouble(String value) { return parseDouble(value, false); }

	/**
	   Attempts to parse a numeric value from a string.  The number may 
	   be formatted as a percent or a currency.
	   @param value The value to parse.
	   @param wholePercent  If true returns percent as a whole number, 
	   if false returns it as a decimal.  For example 90% will be returned as
	   90 if this parameter is true, and as 0.90 if this parameter is false.
	   @return the value as a Double.
	**/
    public static Double parseDouble(String value, boolean wholePercent)
    {
		if ( value==null || value.length() < 1 || value.startsWith(" ") )
			return null;

		if (value.equals("$"))
			return new Double(0.0);

		if (value.endsWith("%")) {
			Double d = new Double(value.substring(0,value.length()-1));
			if (wholePercent)
				return d;
			else
				return new Double(d.doubleValue()/100.0);
		}

		Number numCurr = null;
		try {
			numCurr = NumberFormat.getCurrencyInstance().parse(value);
		}
		catch (java.text.ParseException ex) {
			//NumberFomat is picky about having a "$" so let Double have a go at it
			try {
				numCurr = new Double(value);
			}
			catch (Exception ex2) {
				log.error("Error converting string to double: " + value);
				numCurr = new Double(0.0);
			}

		}
		return new Double(numCurr.doubleValue());
	}

	/**
	   Parse a numeric value in a string into a Long.
	   @param value the value.
	   @return the value as a Long.
	**/
    public static Long parseLong(String value)
    {
		if (value==null || value.length() < 1 || value.startsWith(" ") )
			return null;

		if (value.equals("$"))
			return Long.valueOf(0);

		if (value.endsWith("%")) {
			Long d = Long.valueOf(value.substring(0,value.length()-1));
			return Long.valueOf(d.longValue()/100);
		}

		Number numCurr = null;
		try {
			numCurr = NumberFormat.getCurrencyInstance().parse(value);
		}
		catch (java.text.ParseException ex) {
			//NumberFomat is picky about having a "$" so let Long have a go at it
			try {
				numCurr = Long.valueOf(value);
			}
			catch (Exception ex2) {
				log.error("Error converting string to long: " + value);
				numCurr = Long.valueOf(0);
			}

		}
		return Long.valueOf(numCurr.longValue());
	}

	
	/**
	   Parse a numeric value in a string into an Integer.
	   @param value the value.
	   @return the value as an Integer.
	**/
    public static Integer parseInteger(String value)
    {
		Long val = parseLong(value);
		if (val == null) return null;
		return Integer.valueOf(val.intValue());
	}

	
    /**
	   Turns a string into a boolean value.  If the string starts with
	   Y, y, T, or T then it is considered true.  All other values are 
	   considered false.
	   @param value The value.
	   @return the boolean value.
     **/
	public static boolean parseBoolean(String value)
	{
		if (value == null) value = "";
		String nv = value.toLowerCase();
		if ((nv.startsWith("y")) 
		||	(nv.startsWith("t")))
			return true;
		else
			return false;
	}
	
	/**
     * Safely convert an int value to a byte without overflowing.
     * @param v The int value to convert to a byte.
     * @return a byte value.  If the value is too big, Byte.MAX_VALUE is returned
     *   If the value is too small, Byte.MIN_VALUE is returned.
     */
    public static final byte safeIntToByte(int v)
    {
    	if (v > Byte.MAX_VALUE) {
			log.error("Data Value Truncated");
			return Byte.MAX_VALUE;
		}
    	else if (v < Byte.MIN_VALUE) {
			log.error("Data Value Truncated");
			return Byte.MIN_VALUE;
		}
    	else
    		return (byte)v;
    }
    
    /**
     * Safely convert a long value to an int without overflowing.
     * @param v The long value to convert to an int.
     * @return a byte value.  If the value is too big, Integer.MAX_VALUE is returned
     *   If the value is too small, Integer.MIN_VALUE is returned.
     */
    public static final int safeLongToInt(long v)
    {
    	if (v > Integer.MAX_VALUE) {
			log.error("Data Value Truncated");
			return Integer.MAX_VALUE;
		}
    	else if (v < Integer.MIN_VALUE) {
			log.error("Data Value Truncated");
			return Integer.MIN_VALUE;
		}
    	else
    		return (int)v;
    }
}
