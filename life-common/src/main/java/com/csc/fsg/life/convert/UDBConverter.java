package com.csc.fsg.life.convert;

/* Modifications: T0175 */

public class UDBConverter implements Converter
{
    // should Strings be forced to upper case?
    private boolean upperCase = false;
    
    public byte[] convertToHost(int datatype, int length, int scale, String data)
    {
        switch (datatype) {
            case ALPHANUM:
                if (upperCase && data != null) 
                    data = data.toUpperCase();
                return UDBFromASCIIHelper.numericAndAlphaNumeric(datatype, length, scale, data);
            case NUMERIC:
                return UDBFromASCIIHelper.numericAndAlphaNumeric(datatype, length, scale, data);
            case SNUMERIC:
                return UDBFromASCIIHelper.handleSignedNumeric(datatype, length, scale, data);
            case BINARY:
                return UDBFromASCIIHelper.handleBinary(datatype, length, scale, data);
            case SBINARY:
                // if the data is positive
                if (data != null && data.charAt(0) != '-')
                    return UDBFromASCIIHelper.handleBinary(datatype, length, scale, data);
                else
                    return UDBFromASCIIHelper.handleSignedBinary(datatype, length, scale, data);
            case PACKED:
            case SPACKED:
                return UDBFromASCIIHelper.handleCompData(datatype, length, scale, data);
            default:
                return new byte[0];
        }
    }

	/**
	 * Character array based data types are not supported by this implementation
	 * of <code>com.csc.fsg.life.convert.Converter</code>. To indicate this, an
	 * instance of <code>UnsupportedOperationException</code> is thrown.
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
	@SuppressWarnings("unused")
	public byte[] convertToHost(int datatype, int length, char[] data)
	{
		throw new UnsupportedOperationException();
	}

    public String convertFromHost(int datatype, int length, int scale, byte[] data)
    {
        switch (datatype) {
            case ALPHANUM:
                return UDBToASCIIHelper.handleAlphaNumeric(datatype, length, scale, data);
            case NUMERIC:
                return UDBToASCIIHelper.handleNumeric(datatype, length, scale, data);
            case SNUMERIC:
                return UDBToASCIIHelper.handleSignedNumeric(datatype, length, scale, data);
            case BINARY:
                return UDBToASCIIHelper.handleBinary(datatype, length, scale, data);
            case SBINARY:
                return UDBToASCIIHelper.handleBinary(datatype, length, scale, data);
            case PACKED:
                return UDBToASCIIHelper.handlePacked(datatype, length, scale, data);
            case SPACKED:
                return UDBToASCIIHelper.handleSignedPacked(datatype, length, scale, data);
            default:
               return "";
        }
    }

    public boolean isUpperCase()
    {
        return upperCase;
    }

    public void setUpperCase(boolean upperCase)
    {
        this.upperCase = upperCase;
    }
}