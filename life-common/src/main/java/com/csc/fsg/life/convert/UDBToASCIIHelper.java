package com.csc.fsg.life.convert;

import java.math.BigInteger;

/* Modifications: T0175 */

public class UDBToASCIIHelper
{
    protected static final StringBufferPool _sbPool = StringBufferPool.getInstance();

    public static String handleAlphaNumeric(int datatype, int length, int scale, byte[] inpbytes)
    {
        StringBuffer sb = null;
        try {
            sb = _sbPool.getEntry();
            for (int j=0; j<inpbytes.length; j++) {
                byte characterByte = inpbytes[j];
                sb.append(Byte.toString(characterByte));
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
    	if (length < 0) return "0";
    	
        StringBuffer sb = null;
        try {
            sb = _sbPool.getEntry();
            for (int j=0; j<inpbytes.length; j++) {
                byte characterByte = inpbytes[j];
                sb.append(Byte.toString(characterByte));
            }

            if (scale > 0
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

    public static String handleSignedNumeric(int datatype, int length, int scale, byte[] inpbytes)
    {
    	// sanity check 
    	if (scale < 0) return "0";
    	if (length < 0) return "0";
    	
        StringBuffer sb = null;
        try {
            sb = _sbPool.getEntry();
            for (int j=0; j<inpbytes.length - 1; j++) {
                byte characterByte = inpbytes[j];
                sb.append(Byte.toString(characterByte));
            }

            boolean negative = false;
            byte characterByte = inpbytes[inpbytes.length - 1];
            String lastCharString = Byte.toString(characterByte);
            char lastChar = lastCharString.charAt(0);
            switch (lastChar) {
                case 'p':
                    sb.append('0');
                    negative = true;
                    break;
                case 'q':
                    sb.append('1');
                    negative = true;
                    break;
                case 'r':
                    sb.append('2');
                    negative = true;
                    break;
                case 's':
                    sb.append('3');
                    negative = true;
                    break;
                case 't':
                    sb.append('4');
                    negative = true;
                    break;
                case 'u':
                    sb.append('5');
                    negative = true;
                    break;
                case 'v':
                    sb.append('6');
                    negative = true;
                    break;
                case 'w':
                    sb.append('7');
                    negative = true;
                    break;
                case 'x':
                    sb.append('8');
                    negative = true;
                    break;
                case 'y':
                    sb.append('9');
                    negative = true;
                    break;
                default:
                    sb.append(lastChar);
            }
            
            if (scale > 0
            &&  (sb.length() - scale) > 0)
                sb.insert(sb.length() - scale, '.');
            
            if (negative)
                sb.insert(0, '-');

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
    	if (length < 0) return "0";
    	
        StringBuffer sb = null;
        try {
            sb = _sbPool.getEntry();
            for (int j=0; j<inpbytes.length; j++) {
                sb.append(NumberHelper.getHex(inpbytes[j]));
            }

            BigInteger bi = new BigInteger(sb.toString(), 16);
            sb.setLength(0);
            if (length <= 4) {
                sb.append(bi.intValue());
            } else {
                sb.append(bi.longValue());
            }

            int sbLen = sb.length();
            if (scale > 0
            &&  (sbLen - scale) > 0)
                sb.insert(sbLen - scale, '.');

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
    	if (length < 0) return "0";
    	
        StringBuffer sb = null;
        try {
            sb = _sbPool.getEntry();

            for (int j=0; j<inpbytes.length; j++) {
                sb.append(NumberHelper.getHex(inpbytes[j]));
            }

            char lastChar = sb.charAt(sb.length() - 1);
            if (lastChar != 'F' 
            &&  lastChar != 'f') {
                StringBuffer zeroNumber = new StringBuffer();
                for (int i=0; i<sb.length() - 1; i++) {
                    zeroNumber.append('0');
                }

                if (scale > 0
                &&  (zeroNumber.length() - scale) > 0)
                    zeroNumber.insert(zeroNumber.length() - scale, '.');

                return zeroNumber.toString();
            } else {
                sb.deleteCharAt(sb.length() - 1);
                if (scale > 0
                &&  (sb.length() - scale) > 0)
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
    	if (length < 0) return "0";

    	StringBuffer sb = null;
        try {
            sb = _sbPool.getEntry();
            for (int j=0; j<inpbytes.length; j++) {
                sb.append(NumberHelper.getHex(inpbytes[j]));
            }

            char sign = sb.charAt(sb.length() - 1);
            if (sign != 'C' 
            &&  sign != 'D' 
            &&  sign != 'c' 
            &&  sign != 'd') {
                StringBuffer zeroNumber = new StringBuffer();

                for (int i=0; i<sb.length() - 1; i++) {
                    zeroNumber.append('0');
                }

                if (scale != 0
                &&  (zeroNumber.length() - scale) > 0)
                    zeroNumber.insert(zeroNumber.length() - scale, '.');

                return zeroNumber.toString();
            } else {
                sb.deleteCharAt(sb.length() - 1);

                if (sign == 'D' 
                ||  sign == 'd') {
                    sb.insert(0, '-');
                }

                if (scale != 0
                &&  (sb.length() - scale) >= 0)
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
        return s;
    }
}