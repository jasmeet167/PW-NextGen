package com.csc.fsg.life.common.util;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.GregorianCalendar;

/* Modifications: T0140, WMA-1531 */

/**
   Utility class for helper methods related to calculating 
   values with dates.
 **/
public class DateCalculator
{
	private final static Collection<Integer> _30DayMonths = new ArrayList<Integer>();
	private final static Collection<Integer> _31DayMonths = new ArrayList<Integer>();

	private final static int MILLISECONDS_PER_DAY = 1000 * 60 * 60 * 24;

	/** Constant for DAILY Mode - number of days. **/
	public final static int DAILY = 0;
	/** Constant for WEEKLY Mode - number of weeks. **/
	public final static int WEEKLY = 1;
	/** Constant for BI-WEEKLY Mode - number of weeks / 2. **/
	public final static int BI_WEEKLY = 2;
	/** Constant for mode - number of 28 day periods. **/
	public final static int BI_FORTNIGHTLY = 3;
	/** Constant for Mode - number of months * 2 with rounding. **/
	public final static int SEMI_MONTHLY = 4;
	/** Constant for Mode - number of months. **/
	public final static int MONTHLY = 5;
	/** Constant for Mode - number of quarters. **/
	public final static int QUARTERLY = 6;
	/** Constant for Mode - number of years / 2. **/
	public final static int SEMI_ANNUAL = 7;
	/** Constant for Mode - number of years. **/
	public final static int ANNUAL = 8;
	/** Constant for Mode - number of years / 2. **/
	public final static int BIENNIAL = 9;
	/** Constant for Mode - number of years / 3. **/
	public final static int TRIENNIAL = 10;
	/** Constant for Mode - number of years / 4. **/
	public final static int QUADRENNIAL = 11;
	/** Constant for Mode - number of years / 5. **/
	public final static int QUINQUENNIAL = 12;
	/** Constant for Mode - lumps sum - always returns 1 increment. **/
	public final static int LUMP_SUM = 13;

	static
	{
		_30DayMonths.add(Integer.valueOf(Calendar.APRIL));
		_30DayMonths.add(Integer.valueOf(Calendar.JUNE));
		_30DayMonths.add(Integer.valueOf(Calendar.SEPTEMBER));
		_30DayMonths.add(Integer.valueOf(Calendar.NOVEMBER));

		_31DayMonths.add(Integer.valueOf(Calendar.JANUARY));
		_31DayMonths.add(Integer.valueOf(Calendar.MARCH));
		_31DayMonths.add(Integer.valueOf(Calendar.MAY));
		_31DayMonths.add(Integer.valueOf(Calendar.JULY));
		_31DayMonths.add(Integer.valueOf(Calendar.AUGUST));
		_31DayMonths.add(Integer.valueOf(Calendar.OCTOBER));
		_31DayMonths.add(Integer.valueOf(Calendar.DECEMBER));
	}

	/**
	 * Return an instance of GregorianCalendar, indicating the upper or lower
	 * boundary of the time period specified by date1 and date2. The input dates
	 * need not be in any specific order.
	 * 
	 * @param date1
	 * @param date2
	 * @param isBeginning
	 * @return GregorianCalendar
	 */
	private static GregorianCalendar getDurationBoundary(Date date1, Date date2, boolean isBeginning)
	{
		GregorianCalendar c1 = new GregorianCalendar();
		c1.setTime(date1);
		clearTimeComponents(c1);

		GregorianCalendar c2 = new GregorianCalendar();
		c2.setTime(date2);
		clearTimeComponents(c2);

		if (isBeginning)
			return selectMin(c1, c2);
		else
			return selectMax(c1, c2);
	}

	/**
	   Clear values to do another calculation.
	   @param gc Calendar object to clear
	 */
	public static void clearTimeComponents(GregorianCalendar gc)
	{
		gc.clear(Calendar.HOUR_OF_DAY);
		gc.clear(Calendar.MINUTE);
		gc.clear(Calendar.SECOND);
		gc.clear(Calendar.MILLISECOND);
	}
	
	/**
	 * Of the two instance of GregorianCalendar, return the one, which
	 * corresponds to an earlier date.
	 * 
	 * @param gc1
	 * @param gc2
	 * @return GregorianCalendar
	 */
	private static GregorianCalendar selectMin(GregorianCalendar gc1, GregorianCalendar gc2)
	{
		if (gc1.before(gc2))
			return gc1;
		else
			return gc2;
	}

	/**
	 * Of the two instance of GregorianCalendar, return the one, which
	 * corresponds to a later date.
	 * 
	 * @param gc1
	 * @param gc2
	 * @return GregorianCalendar
	 */
	private static GregorianCalendar selectMax(GregorianCalendar gc1, GregorianCalendar gc2)
	{
		if (gc1.after(gc2))
			return gc1;
		else
			return gc2;
	}

	/**
	 * Return number of days elapsed between two dates. The input dates need not
	 * be in any specific order.
	 * 
	 * @param date1
	 * @param date2
	 * @return int
	 */
	public static int getNumberOfDays(Date date1, Date date2)
	{
		if (date1 == null || date2 == null)
			throw new IllegalArgumentException();

		GregorianCalendar c1 = getDurationBoundary(date1, date2, true);
		GregorianCalendar c2 = getDurationBoundary(date1, date2, false);

		long timeInterval = c2.getTimeInMillis() - c1.getTimeInMillis();

		int nbrOfDays = NumberHelper.safeLongToInt(timeInterval / MILLISECONDS_PER_DAY);
		return nbrOfDays;
	}

	/**
	 * Return number of years elapsed between two dates. The input dates need
	 * not be in any specific order.
	 * 
	 * @param date1
	 * @param date2
	 * @return int
	 */
	public static int getNumberOfYears(Date date1, Date date2)
	{
		if (date1 == null || date2 == null)
			throw new IllegalArgumentException();

		GregorianCalendar c1 = getDurationBoundary(date1, date2, true);
		GregorianCalendar c2 = getDurationBoundary(date1, date2, false);

		int nbrOfYears = 0;
		while (!c1.after(c2))
		{
			c1.add(Calendar.YEAR, 1);
			if (!c1.after(c2))
				nbrOfYears++;
		}

		return nbrOfYears;
	}

	/**
	 * Return number of half-year periods elapsed between two dates. The input
	 * dates need not be in any specific order.
	 * 
	 * @param date1
	 * @param date2
	 * @return int
	 */
	private static int getSemiAnnualModeCount(Date date1, Date date2)
	{
		if (date1 == null || date2 == null)
			throw new IllegalArgumentException();

		GregorianCalendar c1 = getDurationBoundary(date1, date2, true);
		GregorianCalendar c2 = getDurationBoundary(date1, date2, false);

		int nbrOfYears = getNumberOfYears(date1, date2);
		c1.add(Calendar.YEAR, nbrOfYears);

		int remainingMonths = getNumberOfMonths(c1.getTime(), c2.getTime());
		if (remainingMonths < 6)
			return nbrOfYears * 2;
		else
			return nbrOfYears * 2 + 1;
	}

	/**
	 * Return number of months elapsed between two dates. The input dates need
	 * not be in any specific order.
	 * 
	 * @param date1
	 * @param date2
	 * @return int
	 */
	public static int getNumberOfMonths(Date date1, Date date2)
	{
		if (date1 == null || date2 == null)
			throw new IllegalArgumentException();

		GregorianCalendar c1 = getDurationBoundary(date1, date2, true);
		GregorianCalendar c2 = getDurationBoundary(date1, date2, false);

		int nbrOfYears = getNumberOfYears(date1, date2);
		c1.add(Calendar.YEAR, nbrOfYears);

		int nbrOfMonths = nbrOfYears * 12;
		int originalDayOfMonth = c1.get(Calendar.DAY_OF_MONTH);
		while (!c1.after(c2))
		{
			c1.add(Calendar.MONTH, 1);
			adjustEndOfMonth(c1, originalDayOfMonth);
			if (!c1.after(c2))
				nbrOfMonths++;
		}

		return nbrOfMonths;
	}

	/**
	   Adjusts the calendar object to be the correct last day of the month for 
	   the month is uses.
	   @param c The calendar to adjust.
	   @param originalDayOfMonth The original day of month calculated.
	 */
	public static void adjustEndOfMonth(GregorianCalendar c, int originalDayOfMonth)
	{
		if (originalDayOfMonth >= 29 && c.get(Calendar.MONTH) == Calendar.FEBRUARY)
		{
			if (c.isLeapYear(c.get(Calendar.YEAR)))
				c.set(Calendar.DAY_OF_MONTH, 29);
			else
				c.set(Calendar.DAY_OF_MONTH, 28);
			
			return;
		}

		if (originalDayOfMonth >= 30 && _30DayMonths.contains(Integer.valueOf(c.get(Calendar.MONTH))))
		{
			c.set(Calendar.DAY_OF_MONTH, 30);
			return;
		}

		if (originalDayOfMonth == 31 && _31DayMonths.contains(Integer.valueOf(c.get(Calendar.MONTH))))
		{
			c.set(Calendar.DAY_OF_MONTH, 31);
			return;
		}
	}

	public static void restoreMonthiversaryDay(GregorianCalendar c, int originalDayOfMonth)
	{
		int calYear = c.get(Calendar.YEAR);
		int calMonth = c.get(Calendar.MONTH);
		Integer month = Integer.valueOf(calMonth);

		if (originalDayOfMonth == 29) {
			if (calMonth != Calendar.FEBRUARY || c.isLeapYear(calYear))
				c.set(Calendar.DAY_OF_MONTH, 29);
		}
		else if (originalDayOfMonth == 30) {
			if (_30DayMonths.contains(month) || _31DayMonths.contains(month))
				c.set(Calendar.DAY_OF_MONTH, 30);
		}
		else if (originalDayOfMonth == 31 && _31DayMonths.contains(month)) {
			c.set(Calendar.DAY_OF_MONTH, 31);
		}
	}

	/**
	 * Return number of half-month periods elapsed between two dates. The input
	 * dates need not be in any specific order.
	 * 
	 * @param date1
	 * @param date2
	 * @return int
	 */
	private static int getSemiMonthlyModeCount(Date date1, Date date2)
	{
		if (date1 == null || date2 == null)
			throw new IllegalArgumentException();

		GregorianCalendar c1 = getDurationBoundary(date1, date2, true);
		GregorianCalendar c2 = getDurationBoundary(date1, date2, false);

		int nbrOfMonths = getNumberOfMonths(date1, date2);
		c1.add(Calendar.MONTH, nbrOfMonths);

		int remainingDays = getNumberOfDays(c1.getTime(), c2.getTime());
		if (remainingDays < 15)
			return nbrOfMonths * 2;
		else
			return nbrOfMonths * 2 + 1;
	}
	
	/**
	   Adjust the day of the month to either 1 or 15.  If it is >1 and <=15 it  
	   is made 15, otherwise it is made 1.
	   @param c The calendar object to adjust the day on.
	 */
	public static void adjustEndOfHalfMonth(GregorianCalendar c)
	{
		int day = c.get(Calendar.DAY_OF_MONTH);
		if (day == 1 || day == 15)
			return;
		
		if (day < 15)
			c.set(Calendar.DAY_OF_MONTH, 15);
		else
		{
			c.add(Calendar.MONTH, 1);
			c.set(Calendar.DAY_OF_MONTH, 1);
		}
	}
	
	/**
	 * Return number of weeks elapsed between two days. The input dates need not
	 * be in any specific order.
	 * 
	 * @param date1
	 * @param date2
	 * @return int
	 */
	public static int getNumberOfWeeks(Date date1, Date date2)
	{
		if (date1 == null || date2 == null)
			throw new IllegalArgumentException();

		int nbrOfDays = getNumberOfDays(date1, date2);
		int nbrOfWeeks = nbrOfDays / 7;
		return nbrOfWeeks;
	}

	/**
	 * Return number of modes of type mode elapsed between two dates. The input
	 * dates need not be in any specific order.
	 * 
	 * @param date1
	 * @param date2
	 * @param mode
	 * @return int
	 */
	public static int getNumberOfModes(Date date1, Date date2, int mode)
	{
		if (date1 == null || date2 == null)
			throw new IllegalArgumentException();

		switch (mode)
		{
			case DAILY:
				return getNumberOfDays(date1, date2);

			case WEEKLY:
				return getNumberOfWeeks(date1, date2);

			case BI_WEEKLY:
				return getNumberOfWeeks(date1, date2) / 2;

			case BI_FORTNIGHTLY:
				return getNumberOfDays(date1, date2) / 28;

			case SEMI_MONTHLY:
				return getSemiMonthlyModeCount(date1, date2);

			case MONTHLY:
				return getNumberOfMonths(date1, date2);

			case QUARTERLY:
				return getNumberOfMonths(date1, date2) / 3;

			case SEMI_ANNUAL:
				return getSemiAnnualModeCount(date1, date2);

			case ANNUAL:
				return getNumberOfYears(date1, date2);

			case BIENNIAL:
				return getNumberOfYears(date1, date2) / 2;

			case TRIENNIAL:
				return getNumberOfYears(date1, date2) / 3;

			case QUADRENNIAL:
				return getNumberOfYears(date1, date2) / 4;

			case QUINQUENNIAL:
				return getNumberOfYears(date1, date2) / 5;

			case LUMP_SUM:
				return 1;

			default:
				throw new IllegalArgumentException();
		}
	}	
}
