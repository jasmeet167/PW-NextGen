package com.csc.fsg.life.common.util;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

/* Modifications: WMA-1531 */

/**
   Manages a stream of event dates based on a mode (MONTHLY, WEEKLY, etc.).
   Takes a start date and an end date and has method to progress through 
   each date on which an event occurs.
 */
public class EventStream
{
	private GregorianCalendar firstEventDate;
	private GregorianCalendar streamEndDate;
	private GregorianCalendar nextEventDate;
	private int mode;

	/**
	   Creates a new EventStream for the provided start and end date and 
	   for the specified mode.  See <code>DateCalculator</code> for valid 
	   modes.
	   @param firstEventDate
	   @param streamEndDate
	   @param mode
	 */
	public EventStream(Date firstEventDate, Date streamEndDate, int mode)
	{
		if (firstEventDate == null || streamEndDate == null)
			throw new IllegalArgumentException();

		this.firstEventDate = new GregorianCalendar();
		this.firstEventDate.setTime(firstEventDate);
		DateCalculator.clearTimeComponents(this.firstEventDate);

		this.streamEndDate = new GregorianCalendar();
		this.streamEndDate.setTime(streamEndDate);
		DateCalculator.clearTimeComponents(this.streamEndDate);

		resetStream();
		this.mode = mode;
	}

	/**
	 * Return date of the next event in this stream, and advance value of next
	 * event date by one mode.
	 * 
	 * @return Date
	 */
	public Date getNextEventDate()
	{
		if (!hasMoreEvents())
			return null;

		int addField = -1;
		int addAmount = -1;
		switch (mode)
		{
			case DateCalculator.DAILY:
					addField = Calendar.DAY_OF_MONTH;
					addAmount = 1;
					break;

			case DateCalculator.WEEKLY:
					addField = Calendar.DAY_OF_MONTH;
					addAmount = 7;
					break;

			case DateCalculator.BI_WEEKLY:
					addField = Calendar.DAY_OF_MONTH;
					addAmount = 14;
					break;

			case DateCalculator.BI_FORTNIGHTLY:
					addField = Calendar.DAY_OF_MONTH;
					addAmount = 28;
					break;

			case DateCalculator.SEMI_MONTHLY:
					addField = Calendar.DAY_OF_MONTH;
					addAmount = 14;
					break;

			case DateCalculator.MONTHLY:
					addField = Calendar.MONTH;
					addAmount = 1;
					break;

			case DateCalculator.QUARTERLY:
					addField = Calendar.MONTH;
					addAmount = 3;
					break;

			case DateCalculator.SEMI_ANNUAL:
					addField = Calendar.MONTH;
					addAmount = 6;
					break;

			case DateCalculator.ANNUAL:
					addField = Calendar.YEAR;
					addAmount = 1;
					break;

			case DateCalculator.BIENNIAL:
					addField = Calendar.YEAR;
					addAmount = 2;
					break;

			case DateCalculator.TRIENNIAL:
					addField = Calendar.YEAR;
					addAmount = 3;
					break;

			case DateCalculator.QUADRENNIAL:
					addField = Calendar.YEAR;
					addAmount = 4;
					break;

			case DateCalculator.QUINQUENNIAL:
					addField = Calendar.YEAR;
					addAmount = 5;
					break;

			case DateCalculator.LUMP_SUM:
					addField = Calendar.YEAR;
					addAmount = 9999;
					break;

			default:
				throw new IllegalArgumentException();
		}

		Date returnDate = (Date) nextEventDate.getTime().clone();
		int originalDay = firstEventDate.get(Calendar.DAY_OF_MONTH);
		
		nextEventDate.add(addField, addAmount);

		if (mode == DateCalculator.SEMI_MONTHLY)
			DateCalculator.adjustEndOfHalfMonth(nextEventDate);
		else if (addField == Calendar.MONTH)
			DateCalculator.restoreMonthiversaryDay(nextEventDate, originalDay);
		
		return returnDate;
	}

	/**
	 * Return a list of all event dates remaining in this stream.
	 * 
	 * @return List
	 */
	public List<Date> getRemainingEventDates()
	{
		List<Date> datesList = new ArrayList<Date>();
		while (hasMoreEvents())
			datesList.add(getNextEventDate());

		return datesList;
	}

	/**
	 * Return count of all events remaining in this stream.
	 * 
	 * @return int
	 */
	public int getRemainingEventCount()
	{
		return getRemainingEventDates().size();
	}

	/**
	 * Return true if there is at least one more paout left in this stream.
	 * 
	 * @return boolean
	 */
	public boolean hasMoreEvents()
	{
		return nextEventDate.before(streamEndDate);
	}

	/**
	 * Restore next event date to value of the original first event date.
	 */
	public void resetStream()
	{
		nextEventDate = (GregorianCalendar) firstEventDate.clone();
	}
}
