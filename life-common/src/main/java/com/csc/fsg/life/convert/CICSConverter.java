package com.csc.fsg.life.convert;

import java.io.Serializable;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.Charset;

/* Modifications: ENHT0085, T0175 */

/**
 * Implementation of the <code>Converter</code> interface.  Converts data from ASCII to
 * EBCDIC before sending to the host system, and vice versa.
 */
public class CICSConverter implements Converter,Serializable
{
    // should Strings be forced to upper case?
    private boolean upperCase = true;
    
    /**
     * Convert the data from ASCII to EBCDIC in preparation for submittal to CICS host
     * system.
     * 
     * @param datatype  type of data
     * @param length    length of the data
     * @param scale     scale/precision
     * @param data      data to be converted
     * @return The data in EBCDIC.
     */
    public byte[] convertToHost(int datatype, int length, int scale, String data)
    {
        switch(datatype) {
        
            case ALPHANUM: {
                if (upperCase && data != null) 
                    data = data.toUpperCase();
                return ASCIIToEBCDICHelper.numericAndAlphaNumeric(datatype, length, scale, data);
            }
	        case MIXEDCASE: {
	        	return ASCIIToEBCDICHelper.numericAndAlphaNumeric(datatype, length, scale, data);
	        }
            case NUMERIC: {
                return ASCIIToEBCDICHelper.numericAndAlphaNumeric(datatype, length, scale, data);
            }
            case SNUMERIC: {
                return ASCIIToEBCDICHelper.handleSignedNumeric(datatype, length, scale, data);
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
                return new byte[0];
        }
    }

	/**
	 * Convert the data from ASCII to EBCDIC in preparation for submittal to
	 * CICS host system.
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
	 *        The data to be converted to EBCDIC.
	 * @return byte[] The data converted to EBCDIC.
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
				
				// Convert each character to EBCDIC
				for (int i = 0, n = bArray.length; i < n; i++)
					bArray[i] = ASCIIToEBCDICHelper.convertAsciiByteToEbcdic(bArray[i]);

				return bArray;
			}
			default: {
				throw new IllegalArgumentException("Unsupported data type");
			}
		}
	}

    /**
     * Convert the data returned from the CICS host system from EBCDIC to ASCII.
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
        	case ALPHANUM:
        	case MIXEDCASE:
                return EBCDICToASCIIHelper.handleAlphaNumeric(datatype, length, scale, data);
            case NUMERIC:
                return EBCDICToASCIIHelper.handleNumeric(datatype, length, scale, data);
            case SNUMERIC:
                return EBCDICToASCIIHelper.handleSignedNumeric(datatype, length, scale, data);
            case BINARY:
                return EBCDICToASCIIHelper.handleBinary(datatype, length, scale, data);
            case SBINARY:
                return EBCDICToASCIIHelper.handleBinary(datatype, length, scale, data);
            case PACKED:
                return EBCDICToASCIIHelper.handlePacked(datatype, length, scale, data);
            case SPACKED:
                return EBCDICToASCIIHelper.handleSignedPacked(datatype, length, scale, data);
            default:
                return "";
        }
    }

    /**
     * Returns true if the data should be made upper case
     * when converted.
     */
    public boolean isUpperCase()
    {
        return upperCase;
    }
    
    /**
     * Sets whether or not the data should be forced to upper case
     * when converted.
     */
    public void setUpperCase(boolean upperCase)
    {
        this.upperCase = upperCase;
    }
}