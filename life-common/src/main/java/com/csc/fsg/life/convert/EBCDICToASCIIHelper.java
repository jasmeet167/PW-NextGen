package com.csc.fsg.life.convert;

import java.math.BigInteger;

/* Modifications: T0175 */

/**
   Helper class used to convert EBCIDIC values to ASCII.
**/
public class EBCDICToASCIIHelper
{
	/** Pool of string buffer objects used for performance. **/
    protected static final StringBufferPool _sbPool = StringBufferPool.getInstance();
   
    public static String handleAlphaNumeric(int datatype, int length, int scale, byte[] inpbytes)
    {
        StringBuffer sb = null;
        try {
            sb = _sbPool.getEntry();
            for (int j = 0; j < inpbytes.length; j++) {
                int index = inpbytes[j];
                if (index < 0)
                    index += 256;
                if (index >= 256)
                    sb.append(" ");
                else
                    sb.append(EBCDICHelper.ebcdicset[index]);
            }
            return sb.toString();
        } catch (Exception e) {
            return "";// throw new NumericHandlerException(e);
        } finally {
            if (sb != null)
                _sbPool.releaseEntry(sb);
        }
    }

    public static String handleNumeric(int datatype, int length, int scale, byte[] inpbytes)
    {
    	// sanity check
    	if (scale < 0) return "0";
    	
        StringBuffer sb = null;
        try {
            sb = _sbPool.getEntry();
            for (int j = 0; j < inpbytes.length; j++) {
                int index = inpbytes[j];
                if (index == 64)
                    sb.append("00");
                else {
                    if (index < 0)
                        index += 256;
                    sb.append(EBCDICHelper.ebcdicset[index]);
                }
            }

            if (scale != 0
                    && (sb.length() - scale) >= 0)
                sb.insert(sb.length() - scale, '.');

            return sb.toString();

        } catch (Exception e) {
            // throw new NumericHandlerException(e);
            return "0";
        } finally {
            if (sb != null)
                _sbPool.releaseEntry(sb);
        }
    }

    public static String handleSignedNumeric(int datatype, int length, int scale, byte[] inpbytes)
    {
    	// sanity check
    	if (scale < 0) return "0";
    	
        StringBuffer sb = null;
        try {
            sb = _sbPool.getEntry();

            for (int j = 0; j < inpbytes.length - 1; j++) {
                int index = inpbytes[j];
                if (index == 64)
                    sb.append("00");
                else {
                    if (index < 0)
                        index += 256;
                    sb.append(EBCDICHelper.ebcdicset[index]);
                }
            }

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
            // throw new NumericHandlerException(e);
            return "0";
        } finally {
            if (sb != null)
                _sbPool.releaseEntry(sb);
        }
    }

    public static String handleBinary(int datatype, int length, int scale, byte[] inpbytes)
    {
    	// sanity check
    	if (scale < 0) return "0";
    	
        StringBuffer sb = null;
        try {
            sb = _sbPool.getEntry();
            for (int j = 0; j < inpbytes.length; j++) {
                sb.append(NumberHelper.getHex(inpbytes[j]));
            }

            BigInteger bi = new BigInteger(sb.toString(), 16);
            sb.setLength(0);
            if (length <= 4) {
                sb.append(bi.intValue());
            } else {
                sb.append(bi.longValue());
            }

            if (scale != 0
                    && (sb.length() - scale) > 0)
                sb.insert(sb.length() - scale, '.');

            return sb.toString();
        } catch (Exception e) {
            // throw new NumericHandlerException(e);
            return "0";
        } finally {
            if (sb != null)
                _sbPool.releaseEntry(sb);
        }
    }

    public static String handlePacked(int datatype, int length, int scale, byte[] inpbytes)
    {
    	// sanity check
    	if (scale < 0) return "0";
    	
        StringBuffer sb = null;
        try {
            sb = _sbPool.getEntry();

            for (int j = 0; j < inpbytes.length; j++) {
                sb.append(NumberHelper.getHex(inpbytes[j]));
            }

            char lastChar = sb.charAt(sb.length() - 1);
            if (lastChar != 'F' && lastChar != 'f') {
                StringBuffer zeroNumber = new StringBuffer();
                for (int i = 0; i < sb.length() - 1; i++) {
                    zeroNumber.append('0');
                }

                if (scale != 0
                        && (zeroNumber.length() - scale) > 0)
                    zeroNumber.insert(zeroNumber.length() - scale, '.');

                return zeroNumber.toString();
            } else {
                sb.deleteCharAt(sb.length() - 1);
                if (scale != 0
                        && (sb.length() - scale) > 0)
                    sb.insert(sb.length() - scale, '.');

                return sb.toString();
            }

        } catch (Exception e) {
            // throw new NumericHandlerException(e);
            return "0";
        } finally {
            if (sb != null)
                _sbPool.releaseEntry(sb);
        }
    }

    public static String handleSignedPacked(int datatype, int length, int scale, byte[] inpbytes)
    {
    	// sanity check
    	if (scale < 0) return "0";
    	
        StringBuffer sb = null;
        try {
            sb = _sbPool.getEntry();
            for (int j = 0; j < inpbytes.length; j++) {
                sb.append(NumberHelper.getHex(inpbytes[j]));
            }

            char sign = sb.charAt(sb.length() - 1);
            if (sign != 'C' && sign != 'D' && sign != 'c' && sign != 'd') {
                StringBuffer zeroNumber = new StringBuffer();

                for (int i = 0; i < sb.length() - 1; i++) {
                    zeroNumber.append('0');
                }

                if (scale != 0
                        && (zeroNumber.length() - scale) > 0)
                    zeroNumber.insert(zeroNumber.length() - scale, '.');

                return zeroNumber.toString();
            } else {
                sb.deleteCharAt(sb.length() - 1);

                if (sign == 'D' || sign == 'd') {
                    sb.insert(0, '-');
                }

                if (scale != 0
                        && (sb.length() - scale) >= 0)
                    sb.insert(sb.length() - scale, '.');

                return sb.toString();
            }
        } catch (Exception e) {
            //throw new NumericHandlerException(e);
            return "0";
        } finally {
            if (sb != null)
                _sbPool.releaseEntry(sb);
        }
    }

    public static String suppressNonPrintableChars(String s)
    {
        if (s == null)
            return "";

        s = s.trim();
        byte[] inpbytes = s.getBytes();
        int start = 0;
        int len = inpbytes.length;
        StringBuffer sb = null;

        try {
            sb = _sbPool.getEntry();

            for (int j = start; j < start + len; j++) {
                int index = inpbytes[j];
                if (index < 0)
                    index += 256;

                if (index >= 256)
                    sb.append(" ");
                else
                    sb.append(EBCDICHelper.ebcdicset[index]);
            }

            return sb.toString();

        } catch (Exception e) {
            return "";// throw new NumericHandlerException(e);
        } finally {
            if (sb != null)
                _sbPool.releaseEntry(sb);
        }
    }
}
