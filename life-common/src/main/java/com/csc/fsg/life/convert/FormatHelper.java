package com.csc.fsg.life.convert;

import java.util.Arrays;

import com.csc.fsg.life.common.util.NumberHelper;

/* Modifications: T0175 */

/**
 * Utility class used to format values when converting 
 * to strings or character arrays.
 */
public class FormatHelper
{
	/**
	 * Pad a string with zeros in the front.
	 * 
	 * @param input the string. 
	 * @param length The total desired length for the string.
	 * @return the padded string
	**/
    public static String padZerosInFront(String input, int length)
    {
    	// Sanity check length
    	if (length < 0) return input;
    	
        int inpLength = input.length();
        if (inpLength < length) {
            StringBuffer sb = new StringBuffer();
            int diff = length - inpLength;
            for (int i = 0; i < diff; i++) {
                sb.append('0');
            }

            sb.append(input);
            return new String(sb);
        }
        else if (inpLength > length) {
        	// This can happen for fields defined in the copybook with no digits
        	// prior to decimal point, such as PIC V9(6).
        	int extraLeadingZeroCount = inpLength - length;
        	input = input.substring(extraLeadingZeroCount);
        }
        
        return input;
    }

	/**
	 * Pad a string with zeros in the rear.  It is assumed that the string contains a 
	 * number and it searches for the decimal place.  
	 * 
	 * @param data the string. 
	 * @param scale The total number of digits to the right of the decimal.
	 * @return the padded string
	 */
    public static String padTrailingZeros(String data, int scale)
    {
    	// Sanity check scale to be positive
    	if (scale < 0) return data;
    	
        int decimalIndex = data.indexOf(".");
        if (decimalIndex > -1) {
            int decimalPlaces = (data.substring(decimalIndex + 1)).length();
            if (decimalPlaces > scale) {
                data = data.substring(0, NumberHelper.safeLongToInt(decimalIndex + scale + 1));
                return data;
            }
            scale = scale - decimalPlaces;
        }

        StringBuffer sb = new StringBuffer();
        sb.append(data);
        for (int i = 0; i < scale; i++) {
            sb.append('0');
        }

        return sb.toString();
    }

	/**
	 * Removes the decimal point from a string. 
	 * 
	 * @param data The input string.
	 * @return the cleaned string.
	 */
    public static String removeDecimal(String data)
    {
        int decimalIndex = data.indexOf(".");
        if (decimalIndex == -1)
            return data;

        StringBuffer sb = new StringBuffer();
        sb.append(data.substring(0, decimalIndex));
        sb.append(data.substring(decimalIndex + 1));

        return new String(sb);
    }

	/**
	 * Returns the decimal part of a number, with zero's padded to its scale.
	 * For example if the value of 123.4 is passed in with a scale of 2 then 40 is returned.
	 * 
	 * @return the decimal part of a number, with zero's padded to its scale.
	 */
    public static String removeDecimal(String value, int scale)
    {
        if (scale == 0) {
            // remove the decimal and the digits after the decimal
            if (value.indexOf(".") != -1)
                return value.substring(0, value.indexOf("."));
            return value;
        }

        String retValue = value.toString();
        String valString = padTrailingZeros(value.toString(), scale);
        if (valString.indexOf(".") != -1) {
            int decimalIndex = valString.indexOf(".");
            retValue = valString.substring(0, decimalIndex) + valString.substring(decimalIndex + 1);
            return retValue;
        }

        return valString;
    }

    /**
     * Pad a string to the specified length with spaces at the end.
	 */
    public static String padStringValue(String string, int length) {
        
        if (string == null)
            string = "";

        int max = length < string.length() ? length : string.length();
        StringBuffer sb = new StringBuffer(string.substring(0, max));        
        for (int i = string.length(); i < length; i++) {
            sb.append(" ");
        }
        
        return sb.toString();
    }

	/**
	 * Returns copy of the supplied <code>chArray</code>, with length equal to
	 * the provided <code>length</code>. The output array may be a truncated or
	 * padded copy of <code>chArray</code>, with new occurrences added at the
	 * end of the array as needed, and filled with space characters.
	 * <p>
	 * If a <code>null</code> value is provided, the method will treat it as en
	 * empty array.
	 * 
	 * @param chArray
	 *        The input array, contents of which are to be padded with spaces.
	 * @param length
	 *        Length of the output padded array
	 * @return char[] The return array is a copy of the input, padded with
	 *         spaces as needed
	 */
	public static char[] padCharArrayValue(char[] chArray, int length)
	{
		if (chArray == null)
			chArray = new char[0];

		char[] paddedArray = new char[length];

		int i = 0;
		int inputLen = chArray.length;
		while ((i < inputLen) && (i < length)) {
			paddedArray[i] = chArray[i];
			i++;
		}
		while (i < length)
			paddedArray[i++] = ' ';

		return paddedArray;
	}

	/**
	 * Returns a copy of the supplied <code>chArray</code>, with occurrences,
	 * which contain white space characters either in the beginning, or at the
	 * end of the input array, removed.
	 * <p>
	 * If a <code>null</code> value is provided, the method will return
	 * <code>null</code>.
	 * <p>
	 * This method is logically equivalent to the method <code>trim()</code> in
	 * class <code>java.lang.String</code>.
	 * 
	 * @param chArray
	 *        The input array, contents of which are to be trimmed of white
	 *        space
	 * @return char[] The return array is a copy of the input, with trimmed
	 *         white space
	 */
	public static char[] trimCharArray(char[] chArray)
	{
		if (chArray == null)
			return null;

		int len = chArray.length;
		int begOffset = 0;
		while ((begOffset < len) && (Character.isWhitespace(chArray[begOffset])))
			begOffset++;

		int endOffset = len;
		while ((begOffset < endOffset) && (Character.isWhitespace(chArray[endOffset - 1])))
			endOffset--;

		if ((begOffset > 0) || (begOffset < endOffset))
			return Arrays.copyOfRange(chArray, begOffset, endOffset);
		else
			return Arrays.copyOfRange(chArray, 0, len);
	}
    
	/**
	 * Pad a string to the specified length with zeros at the start.
	 */
    public static String padLongValue(String string, int length) {
        StringBuffer sb = new StringBuffer();
        
        for (int i = 0; i < length - string.length(); i++) {
            sb.append("0");
        }
        sb.append(string);
        
        return sb.toString();
    }

    /**
     * Pad a string to the specified length with zeros at the start. 
	 */
    public static String padDoubleValue(String string, int length) {
        StringBuffer sb = new StringBuffer();
        
        for (int i = 0; i < length - string.length(); i++) {
            sb.append("0");
        }
        sb.append(string);
        
        return sb.toString();
    }
}
