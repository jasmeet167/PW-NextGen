package com.csc.fsg.life.dbutils;

import java.security.SecureRandom;

/* Modifications: T0106, T0175, T0166 */

/**
 * Class used to generate a unique number.
 */
public class UniqueNumberGenerator {

	private static SecureRandom randomNumber = new SecureRandom(SecureRandom.getSeed(2048));

	/**
	 * Appends key to the unique number.
	 * 
	 * @param key a prefix for the unique number.
	 * @return The randomly generated unique number.
	 */
	public static String getUniqueNumber(String key) {

		return key + getUniqueNumber();
	}

	/**
	 * Appends key to the unique number.
	 * 
	 * @param key a prefix for the unique number.
	 * @param maxNumber maximum integer below which the random number to be
	 *            generated
	 * @return The randomly generated unique number.
	 */
	public static String getUniqueNumber(String key, int maxNumber) {

		return key + getUniqueNumber(maxNumber);
	}

	/**
	 * Generates a unique number.
	 * 
	 * @return the unique number.
	 */
	public static int getUniqueNumber() {

		int nextInt = Integer.MIN_VALUE;
		while (nextInt == Integer.MIN_VALUE)
			nextInt = randomNumber.nextInt();

		return Math.abs(nextInt);
	}

	/**
	 * Generates a unique number between 0 and maxNumber.
	 * 
	 * @param maxNumber maximum integer below which the random number to be
	 *            generated
	 * @return the unique number.
	 */
	public static int getUniqueNumber(int maxNumber) {

		int nextInt = Integer.MIN_VALUE;
		while (nextInt == Integer.MIN_VALUE)
			nextInt = randomNumber.nextInt(maxNumber);

		return Math.abs(nextInt);
	}

	/**
	 * Generates a unique long number.
	 * 
	 * @return the unique long number.
	 */
	public static long getUniqueLongNumber()
	{
		long nextLong = Long.MIN_VALUE;
		while (nextLong == Long.MIN_VALUE)
			nextLong = randomNumber.nextLong();

		return Math.abs(nextLong);
	}
}
