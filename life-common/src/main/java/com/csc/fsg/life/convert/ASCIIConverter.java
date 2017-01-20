package com.csc.fsg.life.convert;

import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.Charset;

/* Modifications: T0175 */

/**
 * Implementation of the <code>Converter</code> interface.  Deals solely
 * with ASCII data.
 */
public class ASCIIConverter implements Converter
{
    // should Strings be forced to upper case?
    private boolean upperCase = true;
    
    /**
     * Since this converter only deals with ASCII data, simply pad the given
     * data and return a byte array.
     * 
     * @param datatype  type of data
     * @param length    length of the data
     * @param scale     scale/precision
     * @param data      data to be converted
     * @return The data in ASCII.
     */
    public byte[] convertToHost(int datatype, int length, int scale, String data)
    {
        switch(datatype) {
            
            case ALPHANUM: {
                 if (upperCase && data != null) 
                    data = data.toUpperCase();
                 return FormatHelper.padStringValue(data, length).getBytes();
            }
            case MIXEDCASE: {
                return FormatHelper.padStringValue(data, length).getBytes();
            }
            case NUMERIC: {
                return ASCIIHelper.handleNumeric(datatype, length, scale, data);
            }
            case SNUMERIC: {
                return ASCIIHelper.handleSignedNumeric(datatype, length, scale, data);
            }
            case BINARY: {
                return ASCIIToEBCDICHelper.handleBinary(datatype, length, scale, data);
            }
            case SBINARY: {
                // if the data is positive
                if (data != null && data.charAt(0) != '-')
                    return ASCIIToEBCDICHelper.handleBinary(datatype, length, scale, data);
                else
                    return ASCIIToEBCDICHelper.handleSignedBinary(datatype, length, scale, data);
            }
            case PACKED:
            case SPACKED: {
                return ASCIIToEBCDICHelper.handleCompData(datatype, length, scale, data);
            }
            default:
                 return FormatHelper.padLongValue(data, length).getBytes();
        }
    }

	/**
	 * Convert the data from ASCII to a byte array in preparation for submittal
	 * to an ASCII-based host system.
	 * <p>
	 * This method is used to support text fields implemented as character
	 * arrays, rather then strings, due to recommendation by security
	 * guidelines. Because of this, the method will only accept data types of
	 * ALPHANUM and MIXEDCASE; any other data type will result in
	 * <code>IllegalArgumentException</code> being thrown.
	 * 
	 * @param datatype
	 *        Data type of the <code>data</code>.
	 * @param length
	 *        The target length of the field.
	 * @param data
	 *        The data to be converted.
	 * @return byte[] The data converted.
	 * @see Converter#convertToHost(int, int, char[])
	 */
	public byte[] convertToHost(int datatype, int length, char[] data)
	{
		switch (datatype) {
			case ALPHANUM: {
				// Upshift each character
				if (upperCase && data != null)
					for (int i = 0, n = data.length; i < n; i++)
						data[i] = Character.toUpperCase(data[i]);
			}
				//$FALL-THROUGH$
			case MIXEDCASE: {
				// Convert character array to a byte array, using default
				// character set. The translation to bytes is equivalent to
				// that, which occurs in the in the method getBytes() of
				// java.lang.String
				char[] paddedData = FormatHelper.padCharArrayValue(data, length);
				CharBuffer cBuf = CharBuffer.wrap(paddedData);
				ByteBuffer bBuf = Charset.defaultCharset().encode(cBuf);
				byte[] bArray = bBuf.array();

				return bArray;
			}
			default: {
				throw new IllegalArgumentException("Unsupported data type");
			}
		}
	}

    /**
     * Since this converter only deals with ASCII data, simply create a String
     * using the given byte array.
     * 
     * @param datatype  type of data
     * @param length    length of the data
     * @param scale     scale/precision
     * @param data      data to be converted
     * @return The data in ASCII.
     */
    public String convertFromHost(int datatype, int length, int scale, byte[] data)
    {
    	switch(datatype) {
         	case NUMERIC:
         		return ASCIIHelper.handleNumeric(datatype, length, scale, data);
            case PACKED:
                return EBCDICToASCIIHelper.handlePacked(datatype, length, scale, data);
            case SPACKED:
                return EBCDICToASCIIHelper.handleSignedPacked(datatype, length, scale, data);
            case SNUMERIC:
                return ASCIIHelper.handleSignedNumeric(datatype, length, scale, data);
            case BINARY:
                return EBCDICToASCIIHelper.handleBinary(datatype, length, scale, data);
            case SBINARY:
                return EBCDICToASCIIHelper.handleBinary(datatype, length, scale, data);
         	default:
         		return new String(data);
    	}
	}
    
	/**
	 * Should strings be forced to upper case?
	 * 
	 * @return true if strings are upper cased.
	 * @see #setUpperCase
	 */
    public boolean isUpperCase()
    {
        return upperCase;
    }

	/**
	 * Sets the UpperCase indicator.
	 * 
	 * @param upperCase the new UpperCase value.
	 * @see #isUpperCase
	 */
    public void setUpperCase(boolean upperCase)
    {
        this.upperCase = upperCase;
    }
}
