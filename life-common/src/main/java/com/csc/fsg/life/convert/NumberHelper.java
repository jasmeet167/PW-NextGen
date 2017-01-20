package com.csc.fsg.life.convert;

/* Modifications: T0140 */

/**
   Utility class for helper methods related to numbers.
**/
public class NumberHelper
{
	private static ThreadLocal<Boolean> ascii = new ThreadLocal<Boolean>();

	/** character array of hex characters. **/
    public static char[] chartoHex = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B',
            'C', 'D', 'E', 'F' };

    /**
	   Returns the integer as a Hex character.
	   @return char This method takes in an integer value and returns a
	   character.
     */
    public static char convertToHex(int n)
    {
        return chartoHex[n];
    }

    /**
	   Performs a shift right of the input long number by the scale.  So 
	   for example if an input of 12345 and a scale of 2 is passed in then 
	   123.45 is returned.
	   @return double This method takes in a long and an integer value, In
              return, using the first parameter - long value / by the second
              parameter - integer value by using the Math.pow(). This method
              returns value of the first argument(in this case 10) raised to
              the power of the second argument(which is the integer parameter).
     */
    public static double insertDecimal(long input, int scale)
    {
        return (input / (Math.pow(10, scale)));
    }

    /**
	   Returns the string parameter as a binary number in a string.
	   @return This method takes in a parameter as byte and return it as
	   a String
     */
    public static String getBinary(byte b)
    {
        StringBuffer sb = new StringBuffer();
        int temp;
        if (b < 0)
            temp = b + 256;
        else
            temp = b;
        if ((temp & 128) == 0)
            sb.append("0");
        else
            sb.append("1");
        if ((temp & 64) == 0)
            sb.append("0");
        else
            sb.append("1");
        if ((temp & 32) == 0)
            sb.append("0");
        else
            sb.append("1");
        if ((temp & 16) == 0)
            sb.append("0");
        else
            sb.append("1");
        if ((temp & 8) == 0)
            sb.append("0");
        else
            sb.append("1");
        if ((temp & 4) == 0)
            sb.append("0");
        else
            sb.append("1");
        if ((temp & 2) == 0)
            sb.append("0");
        else
            sb.append("1");
        if ((temp & 1) == 0)
            sb.append("0");
        else
            sb.append("1");

        return new String(sb);
    }

    /**
	   Converts a byte to a HEX value and return that value in a string.
	   @return String This method takes in a byte parameter and return it as a
	   string format
     */
    public static String getHex(byte b)
    {
        StringBuffer sb = new StringBuffer();
        int temp;
        if (b < 0)
            temp = b + 256;
        else
            temp = b;

        // int highbyte = temp / 16;
        int highbyte = (temp & 0x00F0) >> 4;

        // int lowbyte = temp % 16;
        int lowbyte = temp & 0x000F;

        sb.append(convertToHex(highbyte));
        sb.append(convertToHex(lowbyte));
        return sb.toString();
    }

     /**
	  * This method converts a char value to an integer value.
	  * @param ch The character to convert.
	  * @return integer
     */
    public static int toInt(char ch)
    {
        switch (ch) {
            case '0':
                return 0;
            case '1':
                return 1;
            case '2':
                return 2;
            case '3':
                return 3;
            case '4':
                return 4;
            case '5':
                return 5;
            case '6':
                return 6;
            case '7':
                return 7;
            case '8':
                return 8;
            case '9':
                return 9;
            case 'A':
                return 10;
            case 'B':
                return 11;
            case 'C':
                return 12;
            case 'D':
                return 13;
            case 'E':
                return 14;
            case 'F':
                return 15;
            case 'a':
                return 10;
            case 'b':
                return 11;
            case 'c':
                return 12;
            case 'd':
                return 13;
            case 'e':
                return 14;
            case 'f':
                return 15;
        }
        return 0;
    }

    /**
     * Returns the ascii.
     * @return boolean
     */
    public static boolean isAscii() {
        if (ascii.get() == null)
            setAscii(true);
        boolean bAscii = ascii.get().booleanValue();
        return bAscii;
    }

    /**
     * Sets the ascii indicator.
     * @param bAscii The new ascii indicator value.
     */
    public static void setAscii(boolean bAscii) {
        ascii.set(Boolean.valueOf(bAscii));
    }
    
    /**
     * Safely convert an int value to a byte without overflowing.
     * @param v The int value to convert to a byte.
     * @return a byte value.  If the value is too big, Byte.MAX_VALUE is returned
     *   If the vlaue is too small, Byte.MIN_VALUE is returned.
     */
    public static final byte safeIntToByte(int v)
    {
    	if (v > Byte.MAX_VALUE)
    		return Byte.MAX_VALUE;
    	else if (v < Byte.MIN_VALUE)
    		return Byte.MIN_VALUE;
    	else
    		return (byte)v;
    }
    
    /**
     * Safely convert a long value to an int without overflowing.
     * @param v The long value to convert to an int.
     * @return an int value.  If the value is too big, Integer.MAX_VALUE is returned
     *   If the vlaue is too small, Integer.MIN_VALUE is returned.
     */
    public static final int safeLongToInt(long v)
    {
    	if (v > Integer.MAX_VALUE)
    		return Integer.MAX_VALUE;
    	else if (v < Integer.MIN_VALUE)
    		return Integer.MIN_VALUE;
    	else
    		return (int)v;
    }
    
    /**
     * Safely convert a char value to a byte.
     * @param v The char value to convert to a byte.
     * @return a byte value.  If the value is too big then Byte.MAX_VALUE is returned
     *   If the vlaue is negative, then the 0 byte is returned.
     */
    public static byte safeCharToByte(char v)
    {
    	if (v > Character.MAX_VALUE)
    		return Byte.MAX_VALUE;
    	else if (v < 0)
    		return 0;
    	else
    		return (byte)v;
    }
}
