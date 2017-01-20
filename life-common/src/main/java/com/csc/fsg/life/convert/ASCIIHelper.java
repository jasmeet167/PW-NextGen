package com.csc.fsg.life.convert;

/* Modifications: T0175 */

/**
   Helper class used to support ASCII converter.
**/
public class ASCIIHelper
{
    /** Pool of string buffer objects used for performance. **/
    protected static final StringBufferPool _sbPool = StringBufferPool.getInstance();
   
    public static String handleNumeric(int datatype, int length, int scale, byte[] inpbytes)
    {
    	// sanity check
    	if (scale < 0) return "0";

        for (int j = 0; j < inpbytes.length; j++) {
            int index = inpbytes[j];
            if (index == 43 
            ||  index == 45
            ||  index == 46
            || (index > 47
             && index < 58))
                continue;
            else
                return "0";
        }
        StringBuffer sb = new StringBuffer(new String(inpbytes));
        
        // insert the decimal point if one does not already exist
        int pointIndex = sb.indexOf(".");
        if (scale != 0 && pointIndex < 0) {
            pointIndex = sb.length() - scale;
            if (pointIndex > 0)
                sb.insert(pointIndex, '.');
        }
        
 		return sb.toString();
    }
    
    /**
     * Convert the given data into a byte array.  Any decimal points are removed
     * and zeroes are prepended to make data the appropriate length.
     */
    public static byte[] handleNumeric(int datatype, int length, int scale, String data)
    {
        byte[] bytes = new byte[length];
        int byteIndex = length-1;
        
        // append zeroes to byte array for decimals not supplied
        if (scale != 0) {
        	int existingDecimals = 0;
            int pointIndex = data.indexOf(".");
           	if (pointIndex > 0)
           		existingDecimals = data.length() - pointIndex - 1 ;
            while (existingDecimals < scale) {
                bytes[byteIndex--] = (byte)48;
                existingDecimals++;
           	}
        }
        
        // add characters to byte array, working backwards for efficiency
        if (byteIndex >= 0) {
        	for (int i = data.length() - 1; i >= 0; i--) {
        		char c = data.charAt(i);
        		//ignore decimal point
        		if (c != '.')
        			bytes[byteIndex--] = (byte)c;
        	}

        	// prepend zeroes
        	while (byteIndex >= 0) {
            	bytes[byteIndex--] = (byte)48;
        	}
        }
        
        return bytes;  
    }
    
    public static String handleSignedNumeric(int datatype, int length, int scale, byte[] inpbytes)
    {
        // sanity check
        if (scale < 0) return "0";
        
        StringBuffer sb = null;
        try {
            sb = _sbPool.getEntry();

            byte[] bytes = new byte[inpbytes.length-1];
            for (int i = 0; i < inpbytes.length - 1; i++) {
                // convert spaces to zeroes
                if (inpbytes[i] == 32)
                    bytes[i] = 48;
                else
                    bytes[i] = inpbytes[i];
            }
            sb.append(new String(bytes));
            
            String lasttwodigits = NumberHelper.getHex(inpbytes[length - 1]);
            char sign = lasttwodigits.charAt(0);
            char value = lasttwodigits.charAt(1);
            sb.append(value);
            if (sign == 'D' || sign == 'd')
                sb.insert(0, '-');

            if (scale != 0
                    && (sb.length() - scale) > 0)
                sb.insert(sb.length() - scale, '.');

            return sb.toString();

        } catch (Exception e) {
            return "0";
        } finally {
            if (sb != null)
                _sbPool.releaseEntry(sb);
        }
    }
    
    public static byte[] handleSignedNumeric(int datatype, int length, int scale, String data)
    {
        if ((data != null) 
        &&  (data.trim().length() > 0)) {
            byte[] bb = new byte[length];
            int signIndex = data.indexOf("-");
            boolean isNegative = (signIndex != -1);
            if (isNegative)
                data = data.substring(1);

            data = FormatHelper.padTrailingZeros(data, scale);
            data = FormatHelper.removeDecimal(data);
            data = FormatHelper.padZerosInFront(data, length);
            
            for (int i=0; i<data.length(); i++) {
                char c = data.charAt(i);
                bb[i] = (byte) c;
            }
            
            if (isNegative) {
                byte characterByte = bb[bb.length - 1];
                String lastCharString = Byte.toString(characterByte);
                char lastChar = lastCharString.charAt(0);
                switch (lastChar) {
                    case '0':
                        bb[bb.length - 1] = (byte) 'p';
                        break;
                    case '1':
                        bb[bb.length - 1] = (byte) 'q';
                        break;
                    case '2':
                        bb[bb.length - 1] = (byte) 'r';
                        break;
                    case '3':
                        bb[bb.length - 1] = (byte) 's';
                        break;
                    case '4':
                        bb[bb.length - 1] = (byte) 't';
                        break;
                    case '5':
                        bb[bb.length - 1] = (byte) 'u';
                        break;
                    case '6':
                        bb[bb.length - 1] = (byte) 'v';
                        break;
                    case '7':
                        bb[bb.length - 1] = (byte) 'w';
                        break;
                    case '8':
                        bb[bb.length - 1] = (byte) 'x';
                        break;
                    case '9':
                        bb[bb.length - 1] = (byte) 'y';
                        break;
                }
            }
            
            return bb;
        }
        else {
            byte[] bb = new byte[length];
            for (int i = 0; i < bb.length - 1; i++) {
                bb[i] = (byte) '0';
            }

            return bb;
        }
    }
}