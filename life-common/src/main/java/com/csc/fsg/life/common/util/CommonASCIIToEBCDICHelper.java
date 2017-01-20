package com.csc.fsg.life.common.util;

import com.csc.fsg.life.convert.NumberHelper;

/* Modifications: T0140 */

public class CommonASCIIToEBCDICHelper 
{
	public final  static byte[] convertData ( String datatype, int length, int scale, String data )
	{
		if ( datatype.equals ( "ALPHANUM" ) || datatype.equals ( "NUMERIC" )) {
			byte[] bb = new byte[length];			

			if ( datatype.equals ( "ALPHANUM" ) ) {
				for ( int i = 0; i < bb.length; i++ ) {
					bb[i] = (byte) 64;
				}
			}
			else {
				for ( int i = 0; i < bb.length; i++ ) {
					bb[i] = (byte) 240;
				}				
			}						
			if ( length > 0 ) {
				if ( data != null ) {
					if ( datatype.equals ( "NUMERIC" ) ) {
						data = padTrailingZeros( data, scale );
						data = removeDecimal( data );
						data = padZerosInFront( data,length );
					}
					
					int dlength = data.length();
					if ( dlength >= length ) {
						for ( int i = 0; i < length; i++ ) {
							char c = data.charAt(i);
							if ( c == ' ' )
								bb[i] = (byte)64;	
							else {
								int ci = 0;
								for ( ; ci < 255; ci++ ) {
									if ( CommonEBCDICHelper.ebcdicset[ci] == c )
										break;
								}
								
								bb[i] = (byte)ci;
							}
						}
					}
					else {
						if ( datatype.equals ( "NUMERIC" ) ) {
							data = padZerosInFront(data, length);
							dlength = data.length();
						}
						
						int i = 0;
						for ( ; i < dlength; i++ ) {
							char c = data.charAt(i);
							if ( c == ' ' )
								bb[i] = (byte)64;
							else {							
								int ci = 0;
								for ( ; ci < 255; ci++ ) {
									if ( CommonEBCDICHelper.ebcdicset[ci] == c )
										break;
								}
								
								bb[i] = (byte)ci;
							}
						}		
					}
				}							
			}

			return bb;
		}
		
		if ( datatype.equals ( "SNUMERIC" ) ) {
			if ( ( data != null ) && ( data.trim().length() > 0 ) ) {
				byte[] bb = new byte[length];

				int signIndex = data.indexOf('-');
				boolean isNegative = ( signIndex != -1);
				for ( int i = 0; i < bb.length; i++ ) {
					if( isNegative && i == (bb.length-1))
						bb[i] = (byte) 208;
					else if( !isNegative && i == (bb.length-1))
						bb[i] = (byte) 192;
					else bb[i] = (byte) 240;
				}
				
//				if( isNegative )	//we always want to remove the sign (+ or -)
				data = data.substring(1);
				data = padTrailingZeros( data, scale );
				data = removeDecimal( data );
				data = padZerosInFront( data,length );
				for ( int i =0; i < length; i++ ) {
					if ( i < length - 1 ) {
						int val = hexToDecimal("F"+data.charAt(i));
						bb[i] = (byte) val;
					}
					else {
						int val = -1;
						if ( isNegative )
							val = hexToDecimal("D"+data.charAt(i));
						else
							val = hexToDecimal("C"+data.charAt(i));

						bb[i] = (byte) val;
					}
				}
				return bb;
			}
			else {
				byte[] bb = new byte[length];
				for ( int i = 0; i < bb.length - 1; i++ ) {							
					bb[i] = (byte) 240;
				}
				
				bb[bb.length - 1] = (byte)192;
				return bb;
			}			
		}

		if ( datatype.equals ( "BINARY" ) ) {
			byte[] bb = new byte[length];	
			for ( int i = 0; i < bb.length; i++ ) {
				bb[i] = (byte) 0;
			}
			
			if ( ( data != null ) && ( data.trim().length() > 0 ) ) {
				try {
					Long number = new Long ( data );
					String hexVal = Long.toString( number.longValue(), 16);
					if ( hexVal.length() < bb.length*2 )
						hexVal = padZerosInFront ( hexVal, NumberHelper.safeLongToInt(bb.length*2) );
					
					for ( int i = 0; i < bb.length; i++ ) {
						char c1 = hexVal.charAt ( i*2);
						char c2 = hexVal.charAt ( i*2+1);
						int val = NumberHelper.toInt(c1)*16+NumberHelper.toInt(c2);
						bb[i] = (byte) val;
					}
				}
				catch ( Exception e ) {
					
				}
			}
			return bb;
		}
		
		if ( datatype.equals ( "SPACKED" ) || datatype.equals ( "PACKED" ) ) {
			byte[] bb = new byte[length];			
			for ( int i = 0; i < bb.length; i++ ) {
				bb[i] = (byte) 0;
			}
			
			bb[bb.length-1] = (byte)12;
			boolean isNegative = false;
			String valueData;
			if ( ( data != null ) && ( data.trim().length() > 0 ) ) {
				if ( data.charAt(0) == '-' ) {
					if ( datatype.equals ( "SPACKED" ) ) {
						isNegative = true;			
						valueData = data.substring(1, data.length());
					}
					else
						valueData = data.substring(1, data.length());
				}
				else
					valueData = data;
				
				int dlength = valueData.length();
				if ( dlength < length*2 -1 ) {
					valueData = padZerosInFront(valueData, NumberHelper.safeLongToInt(length*2 -1));
					dlength = valueData.length();
				}
				
				for ( int i =0; i < length; i++ ) {
					if ( i < length - 1 ) {
						char c1 = valueData.charAt ( i*2);
						char c2 = valueData.charAt ( i*2+1);
						int val = NumberHelper.toInt(c1)*16+NumberHelper.toInt(c2);
						bb[i] = (byte) val;
					}
					else {
						char c1 = valueData.charAt ( i*2);
						char c2 = 'C';
						if ( isNegative )
							c2 = 'D';
						
						int val = NumberHelper.toInt(c1)*16+NumberHelper.toInt(c2);
						bb[i] = (byte) val;
					}
				}
			}
			
			return bb;
		}
		
		return new byte[0];
	}


	public static String padZerosInFront ( String input, int length )
	{
		// Sanity check length
		if (length < 1) 
			return input;
		
		int inpLength = input.length();
		if ( inpLength < length ) {
			StringBuffer sb = new StringBuffer();
			int diff = length - inpLength;
			for ( int i = 0; i < diff; i++ ) {
				sb.append ( '0' );
			}
			
			sb.append ( input );
			return new String ( sb );
		}
		
		return input;
	}

	public static String padTrailingZeros ( String data, int scale )
	{
		int decimalIndex = data.indexOf('.');
		if( decimalIndex > -1 && decimalIndex < Integer.MAX_VALUE) {
			scale = scale - (data.substring(decimalIndex+1)).length();
		}

		StringBuffer sb = new StringBuffer();
		sb.append ( data );
		for ( int i = 0; i < scale; i++ ) {
			sb.append ( '0' );
		}
		
		return new String ( sb );
	}

	public static String removeDecimal ( String data )
	{
		int decimalIndex = data.indexOf('.');
		if( decimalIndex == -1 )
			return data;

		StringBuffer sb = new StringBuffer();
		sb.append ( data.substring(0,decimalIndex) );
		sb.append ( data.substring(decimalIndex+1) );		
		return new String ( sb );
	}

    public static int hexToDecimal(String val)
    {
    	Integer number = Integer.valueOf(Integer.parseInt(val,16));
    	return number.intValue();
    }   

}
