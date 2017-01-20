package com.csc.fsg.life.common.util;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;


/**
   Simple password encoder class.
**/
@SuppressWarnings("restriction")
public class PasswordEncoder 
{
    // logger 
    private static Log log = LogFactory.getLog(PasswordEncoder.class);

	/**
	   Encodes a byte array and returns the encoded value in a string.
	**/
	public static String encode(byte[] in) {
		BASE64Encoder encoder = new BASE64Encoder();
		return encoder.encode(in);
	}

	/**
	   Decodes the string.
	**/
	public static String decode(String strIn) {
		BASE64Decoder decoder = new BASE64Decoder();
		byte[] bytesOut = null;
		try {
			bytesOut = decoder.decodeBuffer(strIn);
		} catch (Exception ex) {
			log.error("Error decoding password: " + ex.getMessage());
		}
		return new String(bytesOut);
	}
}
