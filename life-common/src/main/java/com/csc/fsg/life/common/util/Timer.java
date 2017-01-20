package com.csc.fsg.life.common.util;

/**
   Class that manages time for performance measurement purposes.
 */
public class Timer {

	private long t;

	/**
	   Builds a Timer object.
	 */
	public Timer() {
		reset();
	}

	/**
	   Resets the time to now.
	 */
	public void reset() {
		t = System.currentTimeMillis();
	}

	/**
	   Displays the current time taken to System.out.
	*/
	public void display() {

		long timeTaken = System.currentTimeMillis() - t;

		System.out.println("Time taken in msec :" + timeTaken);
		reset();
	}

	/**
	   Record the current time taken and return.
	 * @return The amount elapsed time in milliseconds since the last call to reset().
	 */
	public long timeTaken() {

		long timeTaken = System.currentTimeMillis() - t;

		reset();
		return timeTaken;
	}

}
