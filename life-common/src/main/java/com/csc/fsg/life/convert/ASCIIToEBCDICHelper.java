package com.csc.fsg.life.convert;

/* Modifications: T0140, T0175 */

public class ASCIIToEBCDICHelper
{
    public static byte[] numericAndAlphaNumeric(int datatype, int length, int scale, String data)
    {
        byte[] bb = new byte[length];
        if (datatype == Converter.ALPHANUM) {
            for (int i = 0; i < bb.length; i++) {
                bb[i] = (byte) 64;
            }
        }
        else {
            for (int i = 0; i < bb.length; i++) {
                bb[i] = (byte) 240;
            }
        }
        if (length > 0) {
            if (data != null) {
                if (datatype == Converter.NUMERIC) {
                    data = FormatHelper.padTrailingZeros(data, scale);
                    data = FormatHelper.removeDecimal(data);
                    data = FormatHelper.padZerosInFront(data, length);
                }

                int dlength = data.length();
                if (dlength >= length) {
                    for (int i = 0; i < length; i++) {
                        char c = data.charAt(i);

                        if (c == ' ')
                            bb[i] = (byte) 64;
                        else {
                            int ci = 0;
                            for (; ci < 255; ci++) {
                                if (EBCDICHelper.ebcdicset[ci] == c) {
                                    break;
                                }
                            }
                            bb[i] = (byte) ci;
                        }
                    }
                }
                else {
                    if (datatype == Converter.NUMERIC) {
                        data = FormatHelper.padZerosInFront(data, length);
                        dlength = data.length();
                    }

                    int i = 0;
                    for (; i < dlength; i++) {
                        char c = data.charAt(i);
                        if (c == ' ')
                            bb[i] = (byte) 64;
                        else {
                            int ci = 0;
                            for (; ci < 255; ci++) {
                                if (EBCDICHelper.ebcdicset[ci] == c) {
                                    break;
                                }
                            }
                            bb[i] = (byte) ci;
                        }
                    }
                }
            }
        }
        
        return bb;
    }

    public static byte[] handleSignedNumeric(int datatype, int length, int scale, String data)
    {
        if ((data != null) && (data.trim().length() > 0)) {
            byte[] bb = new byte[length];
            int signIndex = data.indexOf("-");
            boolean isNegative = (signIndex != -1);
            for (int i = 0; i < bb.length; i++) {
                if (isNegative && i == (bb.length - 1))
                    bb[i] = (byte) 208;

                else if (!isNegative && i == (bb.length - 1))
                    bb[i] = (byte) 192;
                else
                    bb[i] = (byte) 240;
            }

            if (isNegative)
                data = data.substring(1);

            data = FormatHelper.padTrailingZeros(data, scale);
            data = FormatHelper.removeDecimal(data);
            data = FormatHelper.padZerosInFront(data, length);
            for (int i = 0; i < length; i++) {
                if (i < length - 1) {
                    int val = hexToDecimal("F" + data.charAt(i));
                    bb[i] = (byte) val;
                }
                else {
                    int val = -1;
                    if (isNegative)
                        val = hexToDecimal("D" + data.charAt(i));
                    else
                        val = hexToDecimal("C" + data.charAt(i));

                    bb[i] = (byte) val;
                }
            }
            return bb;
        }
        else {
            byte[] bb = new byte[length];

            for (int i = 0; i < bb.length - 1; i++) {
                bb[i] = (byte) 240;
            }

            bb[bb.length - 1] = (byte) 192;
            return bb;
        }
    }

    public static byte[] handleBinary(int datatype, int length, int scale, String data)
    {
        byte[] bb = new byte[length];
        for (int i = 0; i < bb.length; i++) {
            bb[i] = (byte) 0;
        }

        if ((data != null) && (data.trim().length() > 0)) {
            try {
                Long number = Long.valueOf(data);
                String hexVal = Long.toString(number.longValue(), 16);
                if (hexVal.length() < bb.length * 2) {
                    hexVal = FormatHelper.padZerosInFront(hexVal, NumberHelper.safeLongToInt(bb.length * 2));
                }

                for (int i = 0; i < bb.length; i++) {
                    char c1 = hexVal.charAt(i * 2);
                    char c2 = hexVal.charAt(i * 2 + 1);
                    int val = NumberHelper.toInt(c1) * 16 + NumberHelper.toInt(c2);
                    bb[i] = (byte)val;
                }
            }
            catch (Exception e) {
            }
        }
        return bb;
    }

    public static byte[] handleSignedBinary(int datatype, int length, int scale, String data)
    {
        byte[] bb = new byte[length];
        for (int i = 0; i < bb.length; i++) {
            bb[i] = (byte) 0;
        }

        boolean isNegative = false;
        String valueData = null;
        if ((data != null)
                && (data.trim().length() > 0)) {
            if (data.charAt(0) == '-') {
                valueData = data.substring(1, data.length());
                isNegative = true;
            }
            else
                valueData = data;

            int dlength = valueData.length();
            int allowedLength = buildAllowedLength(length);;

            if (dlength > allowedLength) {
                valueData = valueData.substring(0, allowedLength);
            }
            else {
                if (dlength < allowedLength) {
                    valueData = FormatHelper.padZerosInFront(valueData, allowedLength);
                }
                else {
                    if (length % 2 == 0) {
                    }
                    else
                        valueData = valueData.substring(0, allowedLength);
                }
            }

            try {
                Long number = Long.valueOf(valueData);
                String hexVal = Long.toString(number.longValue(), 16);
                hexVal = isNegative ? hexVal + "D" : hexVal + "C";
                if (hexVal.length() < bb.length * 2) {
                    hexVal = FormatHelper.padZerosInFront(hexVal, NumberHelper.safeLongToInt(bb.length * 2));
                }

                for (int i = 0; i < bb.length; i++) {
                    char c1 = hexVal.charAt(i * 2);
                    char c2 = hexVal.charAt(i * 2 + 1);
                    int val = NumberHelper.toInt(c1) * 16 + NumberHelper.toInt(c2);
                    bb[i] = (byte) val;
                }
            }
            catch (Exception e) {
            }
        }
        return bb;
    }

    private static final int buildAllowedLength(int length)
    {
    	return NumberHelper.safeLongToInt(length * 2 - 1);
    }
    
    public static byte[] handleCompData(int datatype, int length, int scale, String data)
    {
    	// Sanity check data
    	if (scale < 0) return null;
    	
        // 'data' variable will have no decimal at this point
        // 'data' variable will have the proper number of trailing '0' added
        // (to have a valid scale)
        // boolean valid = validateData(datatype,length,scale,data);
        byte[] bb = new byte[length];
        for (int i = 0; i < bb.length; i++) {
            bb[i] = (byte) 0;
        }

        bb[bb.length - 1] = (byte) 12;
        boolean isNegative = false;
        String valueData;
        if ((data != null) && (data.trim().length() > 0)) {
            if (data.charAt(0) == '-' && datatype == Converter.SPACKED) {
                data = data.substring(1, data.length());
                isNegative = true;
            }

            // remove the decimal if the scale if the scale is 0
            valueData = FormatHelper.removeDecimal(data, scale);
            int dlength = valueData.length();
            int allowedLength = buildAllowedLength(length);
            if (dlength > allowedLength) {
                // THIS CONDITION SHOULD NEVER HAPPEN
                // SHOULD WE THROW EXCEPTION ???
                // check the scale length ... needed for delarations like SV9(5)
                if (scale == allowedLength) {
                    valueData = valueData.substring(dlength - scale);
                }
                else
                    valueData = valueData.substring(0, allowedLength);
            }
            else {
                if (dlength < allowedLength) {
                    valueData = FormatHelper.padZerosInFront(valueData, allowedLength);
                }
                else {
                    if (length % 2 == 0) {
                    }
                    else
                        valueData = valueData.substring(0, allowedLength);
                }
            }

            if (datatype == Converter.SPACKED)
                valueData = isNegative ? valueData + "D" : valueData + "C";
            else
                valueData = valueData + "F";

            if (length > 0)
            	for (int i = length - 1; i >= 0; i--) {
            		char c1 = valueData.charAt(i * 2);
            		char c2 = valueData.charAt(i * 2 + 1);
            		int val = NumberHelper.toInt(c1) * 16 + NumberHelper.toInt(c2);
            		bb[i] = (byte) val;
            	}

        }
        return bb;
    }

	public static byte convertAsciiByteToEbcdic(byte asciiByte)
	{
		if (asciiByte == ' ')
			return (byte) 0x40;

		int ci = 0;
		for (; ci < 255; ci++)
			if (EBCDICHelper.ebcdicset[ci] == asciiByte)
				break;

		return (byte) ci;
	}

    public static String toHex(int n)
    {
        String h = "";
        int r = 0;
        int nn = n;
        do {
            r = nn % 16;
            nn = nn / 16;
            switch (r) {
                case 10:
                    h = "A" + h;
                    break;
                case 11:
                    h = "B" + h;
                    break;
                case 12:
                    h = "C" + h;
                    break;
                case 13:
                    h = "D" + h;
                    break;
                case 14:
                    h = "E" + h;
                    break;
                case 15:
                    h = "F" + h;
                    break;
                default:
                    h = r + h;
                    break;
            }
        } while (nn > 0);
        return h;
    }

    public static int hexToDecimal(String val)
    {
        String trimVal = val.trim();
        return Integer.parseInt(trimVal, 16);
    }
}