package com.csc.fsg.life.avm;

import java.io.Serializable;
import java.util.Comparator;

/* Modifications: T0175 */

/**
 * This class is used to compare two AVM values for sorting purposes.
 */
public class AVMCoreValueComparator 
	implements Comparator<Object>, Serializable 
{
	private static final long serialVersionUID = 2734851666159289914L;

	/**
	 * Compare two AVM values.
	 * 
	 * @param o1 First value to compare.
	 * @param o2 Second value to compare
	 * @return a negative integer, zero, or a positive integer as the first
	 *         argument is less than, equal to, or greater than the second
	 */
	public int compare(Object o1, Object o2) 
	{
		// handle null values.
		if ((o1 == null) && (o2 == null))
			return 0;
		if (o1 == null)
			return -1;
		if (o2 == null)
			return 1;

		if (o1 instanceof String && o2 instanceof String) {
			String s1 = o1.toString();
			String s2 = o2.toString();
			return s1.toUpperCase().compareTo(s2.toUpperCase());
		}
		else {
			//Check for Long values
			if(o1.equals(o2))
				return 0;
			else
				return -1;
		}
	}
}
