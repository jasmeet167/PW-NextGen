package com.csc.fsg.life.common.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * VantageDate offers easy date comparison leveraging
 * the base Date class, but handling all nines, null, and 0.
 * Written 03Dec04 for Product Wizard.
 */
public class VantageDate extends Date 
{
	private boolean isAllNines;
	private boolean isEmpty;
	
	/**
	 * Date given can be in any of the 3 Vantage formats
	 * MM/dd/yyyy, MM-dd-yyyy, yyyyMMdd
	 * date can be null, "0", or all nines
	 * @param date A date in a string.
	 */
	public VantageDate(String date) 
	{
		isEmpty = (date==null || date.equals("0"));

		if (!isEmpty) {
			isAllNines = date.equals("99999999") || date.equals("99/99/9999") || date.equals("99-99-9999");
			if (!isAllNines) {
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
			isAllNines = false;
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

		else if (value.indexOf("-") != -1) {
			return new SimpleDateFormat("MM-dd-yyyy").parse(value);
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
	public boolean isAllNines()
	{
		return isAllNines;
	}

	/**
	   Returns true if this date is before the date passed in.
	**/
	public boolean before(Date d)
	{
		if (isEmpty)
			return true;
		if (isAllNines)
			return false;
		else
			return super.before(d);
	}
	
	/**
	   Returns true if this date is before the date passed in.
	**/
	public boolean before(VantageDate d)
	{
		if (isEmpty)
			return true;
		if (isAllNines)
			return false;
		else if (d.isAllNines())
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
		if (isAllNines)
			return true;
		else
			return super.after(d);
	}
	
	/**
	   Returns true if this date is after the date passed in.
	**/
	public boolean after(VantageDate d)
	{
		if (isEmpty)
			return false;
		if (isAllNines)
			return !d.isAllNines();
		else if (d.isAllNines())
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
		else if (isAllNines())
			return Integer.MAX_VALUE;
		else
			return super.compareTo(d);
	}
	
	/**
	   Compares this date to the one passed in.
	**/
	public int compareTo(VantageDate d)
	{
		if (isEmpty)
			return Integer.MIN_VALUE;
		if (isAllNines() && d.isAllNines()) 
			return 0;
		else if (isAllNines())
			return Integer.MAX_VALUE;
		else if (d.isAllNines())
			return Integer.MIN_VALUE;
		else 
			return super.compareTo(d);
	}	

}
