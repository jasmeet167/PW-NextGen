package com.csc.fsg.life.pw.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/* Modifications: ENH915, ENH629, T0129 */
// ENH915, ENH629: added class

/**
 * PWDate offers easy date comparison leveraging
 * the base Date class, but handling all nines, 12-31-9999, null, and 0.
 */
public class PWDate extends Date 
{
	private boolean isMaxDate;
	private boolean isEmpty;
	
	/**
	 * Date given can be in any of the 3 Vantage formats and ISO format
	 * MM/dd/yyyy, MM-dd-yyyy, yyyyMMdd, yyyy-MM-dd
	 * date can be null, "0", or all nines
	 * @param date A date in a string.
	 */
	public PWDate(String date) 
	{
		isEmpty = (date==null || date.equals("0"));

		if (!isEmpty) {
			isMaxDate = date.equals("99999999") || date.equals("99/99/9999") || date.equals("99-99-9999")
				|| date.equals("99991231") || date.equals("9999-12-31") || date.equals("12-31-9999") || date.equals("12/31/9999");
			if (!isMaxDate) {
				try {
					this.setTime(parseDate(date).getTime());					
				}
				catch (ParseException pe) {
					System.err.println("VantageDate :" + pe.toString());
					isEmpty = true;
				}
			}
		}
		else
			isMaxDate = false;
	}

	/**
	   parses a data in a string and turns it intoa java.util.Date object.
	   @param value The data as a string. 
	   @return The Date object.
	   @throws ParseException thrown when the parameter contains an invalid date format.
	**/
	private static Date parseDate(String value)
		throws ParseException 
	{

		if (value.indexOf("/") != -1) {
			return new SimpleDateFormat("MM/dd/yyyy").parse(value);
		}

		int index = value.indexOf("-"); 
		if (index != -1) {
			if ( index == 2 )
				return new SimpleDateFormat("MM-dd-yyyy").parse(value);
			return new SimpleDateFormat("yyyy-MM-dd").parse(value);
		}

		else {
			return new SimpleDateFormat("yyyyMMdd").parse(value);
		}

	}

	/**
	   Returns true if the date passed in was an empty string.
	   @return true if the date passed in was an empty string.
	**/
	public boolean isEmpty()
	{
		return isEmpty;
	}
	
	/**
	   Returns true if the date passed in was all nines.
	   @return true if the date passed in was all nines.
	**/
	public boolean isMaxDate()
	{
		return isMaxDate;
	}

	public boolean isPopulated()
	{
		return (!isEmpty() && !isMaxDate());
	}

	/**
	   Returns true if this date is before the date passed in.
	**/
	public boolean before(Date d)
	{
		if (isEmpty)
			return true;
		if (isMaxDate)
			return false;
		else
			return super.before(d);
	}
	
	/**
	   Returns true if this date is before the date passed in.
	**/
	public boolean before(PWDate d)
	{
		if (isEmpty)
			return true;
		if (isMaxDate)
			return false;
		else if (d.isMaxDate())
			return true;
		else
			return super.before(d);
	}	
	
	/**
	   Returns true if this date is after the date passed in.
	**/
	public boolean after(Date d)
	{
		if (isEmpty)
			return false;
		if (isMaxDate)
			return true;
		else
			return super.after(d);
	}
	
	/**
	   Returns true if this date is after the date passed in.
	**/
	public boolean after(PWDate d)
	{
		if (isEmpty)
			return false;
		if (isMaxDate)
			return !d.isMaxDate();
		else if (d.isMaxDate())
			return false;
		else
			return super.after(d);
	}	
	
	/**
	   Compares this date to the one passed in.
	**/
	public int compareTo(Date d)
	{
		if (isEmpty)
			return Integer.MIN_VALUE;
		else if (isMaxDate())
			return Integer.MAX_VALUE;
		else
			return super.compareTo(d);
	}
	
	/**
	   Compares this date to the one passed in.
	**/
	public int compareTo(PWDate d)
	{
		if (isEmpty)
			return Integer.MIN_VALUE;
		if (isMaxDate() && d.isMaxDate()) 
			return 0;
		else if (isMaxDate())
			return Integer.MAX_VALUE;
		else if (d.isMaxDate())
			return Integer.MIN_VALUE;
		else 
			return super.compareTo(d);
	}	

	public boolean equals(Object obj) {
		return super.equals(obj);
	}
	
	public int hashCode() {
		return super.hashCode();
	}
}
