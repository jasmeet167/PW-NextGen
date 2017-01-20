
package com.csc.fsg.life.common.util;


import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/* Modifications: T0175 */

/**
   Utility class for helper methods related to converting dates
   to/from string and number formats.
 **/
public class DateHelper
{
	/**
	   A formatting string for <code>java.text.SimpleDateFormat</code> that
	   will allow parsing and formatting of ISO 8601 date strings
	   with all UTC fields specified.
	**/
    public static final String ISO8601_UTC_FORMAT =
		"yyyy-MM-dd'T'kk:mm:ss.SSS'Z'";

	/** A foratting string for <code>java.text.SimpleDateFormat</code> taht 
		is for ISO 8601 short data format.
	**/
    public static final String ISO_SHORT =
		"yyyy-MM-dd";
	
	/** Date format string for numerict dates. **/
    public static final String NUMERIC_FORMAT =
        "yyyyMMdd";
    
	/**
	   Formats a date object as a ISO 8601 date will all fields
	   specified.
	   @param d the date.
	   @return the date as an ISO date string.
	**/
	public static String toFormattedISOString(Date d)
	{
		DateFormat isoFormatter = new SimpleDateFormat(ISO8601_UTC_FORMAT);
		return isoFormatter.format(d);
	}

	/**
	   Foramts a date object as a ISO SHORT date.
	   @param d the date.
	   @return the date as an ISO short string.
	**/
	public static String toFormattedISOShortString(Date d)
	{
		DateFormat isoShortFormatter = new SimpleDateFormat(ISO_SHORT);
		return isoShortFormatter.format(d);
	}
    
    /**
	   Formats a Date object as a number string (e.g. 20060101)
	   @param d the date.
	   @return the date as an number string (yyyyMMdd).
     */ 
    public static String toFormattedNumericString(Date d) 
    {
        DateFormat numericFormatter = new SimpleDateFormat(NUMERIC_FORMAT);
        return numericFormatter.format(d);      
    }
    
    /**
	   Formats a Date object as a number string (e.g. 20060101).
	   In additional to valid dates, handles 12/31/9999 and turns
	   it into 99999999.
	   @param d the date.
	   @return the date as an number string (yyyyMMdd).
     */ 
    public static String toFormattedNumericString9s(Date d) 
    {
        DateFormat numericFormatter = new SimpleDateFormat(NUMERIC_FORMAT);
        String dateString = numericFormatter.format(d);
        if (dateString.equals("99991231"))
            return "99999999";
        return dateString;
    }
    
    /**
	   Formats a Date object as a number (e.g. 20060101.0).
	   @param d the date.
	   @return the date as a Double.
     */ 
    public static Double toNumber(Date d) 
    {
        DateFormat numericFormatter = new SimpleDateFormat(NUMERIC_FORMAT);
        return Double.valueOf(numericFormatter.format(d));      
    }

    /**
	   Formats a Date object as a number (e.g. 20060101.0)
	   @param d the date.
	   @return the date as an int.
     */ 
    public static int toInt(Date d) 
    {
        DateFormat numericFormatter = new SimpleDateFormat(NUMERIC_FORMAT);
        return Integer.parseInt(numericFormatter.format(d));      
    }
    
	/**
	   Foramts a calendar object as a ISO 8601 date will all fields
	   specified.
	   @param c the date.
	   @return the date as an ISO string.
	**/
	public static String toFormattedISOString(Calendar c)
	{
		return toFormattedISOString(c.getTime());
	}

	/**
	   Foramts a date object as a ISO SHORT date.
	   @param c the date.
	   @return the date as an ISO short string.
	**/
	public static String toFormattedISOShortString(Calendar c)
	{
		return toFormattedISOShortString(c.getTime());
	}
	
	/**
	   Turns a Date object into MM/DD/YYYY
	   @param d the date.
	**/
	public static String toFormattedString(Date d)
	{
		if (d == null)
			return "";

		Calendar cal = new GregorianCalendar();
		cal.setTime(d);

		int day = cal.get(java.util.Calendar.DATE);
		String dayStr = "" + day;
		if (day < 10)
			dayStr = "0" + day;

		int month = cal.get(java.util.Calendar.MONTH) + 1;
		String monthStr = "" + month;
		if (month < 10)
			monthStr = "0" + month;

		int year = cal.get(java.util.Calendar.YEAR);
		String yearStr = "" + year;

		return monthStr + "/" + dayStr + "/" + yearStr;
	}

	/**
	   Return month from a Date object.
	   @param d the date.
	**/
	public static String getMonth(Date d)
	{
		String monthStr = DateHelper.toFormattedString(d);

		return monthStr.substring(0,2);
	}

	/**
	   Return day from a Date object.
	   @param d the date.
	**/
	public static String getDay(Date d)
	{
		String dayStr = DateHelper.toFormattedString(d);

		return dayStr.substring(3,5);
	}

	/**
	   Return year from a Date object.
	   @param d the date.
	**/
	public static String getYear(Date d)
	{
		String yearStr = DateHelper.toFormattedString(d);

		return yearStr.substring(6,10);
	}

	/**
	   Parses a date using the specified format.  If the dateString is
	   all 9's then it turns it into 12/31/9999.
	   @param dateString the string to parse.
	   @param dateFormat an additional format to try first.  Pass null if no 
	   additional format to use.
	   @return the date object.
	 **/
    public static Date parseDate9s( String dateString, String dateFormat )
        throws ParseException
    {
        if (dateString != null) {
            if (dateString.equals("99999999"))
                return parseDate("99991231", dateFormat);
            if (dateString.equals("9999/99/99"))
                return parseDate("9999/12/31", dateFormat);
        }
        
        return parseDate(dateString, dateFormat);
    }

	/**
	   Tries really hard to parse the string into a date, using a
	   number of different common formats.  Will try the supplied
	   format first.
	   @param dateString the string to parse.
	   @param dateFormat an additional format to try first.  Pass null if no 
	   additional format to use.
	   @return the date object.
	   @throws ParseException if the string can't be converted to a date object.
	**/
	public static Date parseDate( String dateString, String dateFormat )
		throws ParseException
	{
		if (dateString == null)
			return null;

		Date       date = null;
		DateFormat format = null;

		if (dateFormat == null)
			return DateHelper.parseDate( dateString );

		try
		{
			format = new SimpleDateFormat( dateFormat );
			date   = format.parse(dateString);
		}
		catch (java.text.ParseException pex)
		{
			date = DateHelper.parseDate( dateString );
		}

		return date;
	}


	/**
	   Tries really hard to parse the string into a date, using a
	   number of different common formats.
	   @param dateString the string to parse.
	   @return the date object or null if it couldn't be converted.
	**/
	public static Date parseDate( String dateString )
	{
		if (dateString == null) return null;

		Date date = DateHelper.tryCustomDateFormats( dateString );
		if (date != null) return date;

		date = DateHelper.tryParseDateFormats( dateString );
		return date;
	}

	private static Date tryParseDateFormats( String dateString )
	{
		int[] styles = { DateFormat.SHORT,
						 DateFormat.MEDIUM,
						 DateFormat.LONG,
						 DateFormat.FULL };

		Date date    = null;

		for (int i = 0; i < styles.length; ++i)
		{
			DateFormat format = DateFormat.getDateInstance( styles[i] );
			try
			{
				date = format.parse( dateString );
				break;
			}
			catch (ParseException pex)
			{
			}
		}

		if (date != null) return date;

		for (int i = 0; i < styles.length; ++i)
		{
			DateFormat format = DateFormat.getTimeInstance( styles[i] );
			try
			{
				date = format.parse( dateString );
				break;
			}
			catch (ParseException pex)
			{
			}
		}

		if (date != null) return date;

		for (int i = 0; i < styles.length; ++i)
		{
			DateFormat format = DateFormat.getDateTimeInstance( styles[i],
																styles[i] );
			try
			{
				date = format.parse( dateString );
				break;
			}
			catch (ParseException pex)
			{
			}
		}

		return date;

	}

	private static Date tryCustomDateFormats( String dateString )
	{
		String[] formats = { ISO8601_UTC_FORMAT,
                             ISO_SHORT,
			                 "yyyyMMdd",
							 "MM/dd/yyyy hh:mm a",
							 "MM/dd/yyyy HH:mm",
							 "yyyyMMddhhmmss",
							 "hhmmss" };
		SimpleDateFormat format = new SimpleDateFormat();
		Date             date   = null;
		for (int i = 0; i < formats.length; ++i)
		{
			format.applyPattern( formats[i] );
			try
			{
				date = format.parse( dateString );
				break;
			}
			catch (ParseException pex)
			{
				; // Ignore exception and continue to try next date format.
			}
		}
		return date;
	}

	/**
	   Calculates a time duration in years (rounding up) with a
	   month adjustement to the start date (if nec).

	   <P>Sample Results:
	   <pre>
	   Start: Sat Feb 11 00:00:00 EST 1995
	   End: Mon Mar 11 17:02:35 EST 2002
	   Adj: 0
	   Result: 7

	   Start: Sat Mar 11 00:00:00 EST 1995
	   End: Mon Mar 11 17:02:35 EST 2002
	   Adj: 0
	   Result: 7

	   Start: Tue Apr 11 00:00:00 EDT 1995
	   End: Mon Mar 11 17:02:35 EST 2002
	   Adj: 0
	   Result: 7

	   Start: Fri Feb 10 00:00:00 EST 1995
	   End: Mon Mar 11 17:02:35 EST 2002
	   Adj: 1
	   Result: 8

	   Start: Fri Feb 10 00:00:00 EST 1995
	   End: Mon Mar 11 17:02:35 EST 2002
	   Adj: 2
	   Result: 8
	   </pre>
	**/
	public static int calculateDuration(Date start, Date end, int monthAdj)
	{
		// Setup the start and end times.
		Calendar startCal = new GregorianCalendar();
		Calendar endCal = new GregorianCalendar();
		startCal.setTime(start);
		endCal.setTime(end);

		// Adjust by specified months.
		startCal.add(Calendar.MONTH, monthAdj);

		// Diff start and end.
		int years = endCal.get(Calendar.YEAR) - startCal.get(Calendar.YEAR);
		if (years < 0) return 0;

		int month = endCal.get(Calendar.MONTH) - startCal.get(Calendar.MONTH);
		if (month < 0) return years;
		if (month == 0)
		{
			int day = endCal.get(Calendar.DATE) - startCal.get(Calendar.DATE);
			if (day > 0)
				years ++;
		}
		else
			years ++;

		return years;
	}
    
    /**
	   Returns a Date object with value matching the input date 
	   represented as a number (e.g. 20060101.0).
		@param d the value to turn to a date.
		@return the date object or null if it can't be converted to 
		a valid date.
     */
    public static Date parseDate(Double d) { return parseDate(d.intValue()); }

	/** 
		Returns a date object for the numeric input date.
		Input format should be of yyyyMMdd.
		@param value the value to turn to a date.
		@return the date object or null if it can't be converted to 
		a valid date.
	**/
    public static Date parseDate(int value) {       
        if (value == 99999999)
        	return null;
        
        Date date = null;

        try 
        {
            DateFormat numericFormatter = new SimpleDateFormat(NUMERIC_FORMAT);
            date = numericFormatter.parse(String.valueOf(value));
        }
        catch (ParseException e) 
        {
        	; // Ignore exception and fall through to return null date.
        }           
        
        return date;
    }
    
    /**
	 * Formats a Date object as a number string (e.g. 20060101). In additional
	 * to valid dates, handles 12/31/9999 and turns it into 99999999. Also
	 * handles 01/01/1901 and turns it into 00000000.
	 * 
	 * @param date
	 *            the date.
	 * @return the date as an number string (yyyyMMdd).
	 */
	public static String toFormattedNumericString0s9s(Date date) {
		DateFormat numericFormatter = new SimpleDateFormat(NUMERIC_FORMAT);
		String dateString = numericFormatter.format(date);
		if (dateString.equals("99991231"))
			return "99999999";
		else if (dateString.equals("19010101"))
			return "00000000";
		return dateString;
	}
	
	/**
	 * Parses a date using the specified format.If the dateString is all 9's
	 * then it turns it into 12/31/9999 or if the dateString is all 0's then it
	 * turns it into 01/01/1901.
	 * 
	 * @param dateString
	 *            the string to parse.
	 * @param dateFormat
	 *            an additional format to try first. Pass null if no additional
	 *            format to use.
	 * @return the date object.
	 **/
	public static Date parseDate0s9s(String dateString, String dateFormat)
			throws ParseException {
		if (dateString != null) {
			if (dateString.equals("00000000"))
				return DateHelper.parseDate("19010101", dateFormat);
			else if (dateString.equals("0000/00/00"))
				return DateHelper.parseDate("1901/01/01", dateFormat);
			else if (dateString.equals("99999999"))
                return DateHelper.parseDate("99991231", dateFormat);
			else if (dateString.equals("9999/99/99"))
                return DateHelper.parseDate("9999/12/31", dateFormat);
		}

		return DateHelper.parseDate(dateString, dateFormat);
	}

}
