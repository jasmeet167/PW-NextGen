package com.csc.fsg.life.pw.util;

import java.io.UnsupportedEncodingException;
import java.sql.*;

import org.apache.commons.logging.Log;

import com.csc.fsg.life.exceptions.WrapperException;
import com.csc.fsg.life.pw.util.Utils;
import com.csc.fsg.life.pw.environment.*;
import com.csc.fsg.life.pw.log.PWServerLogManager;
//import com.csc.fsg.life.pw.utils.sql.SQLBuilderT000X;

/* Modifications: T0103, T0116, CCCV-E768, T0129*/

public final class HighValueHandler {
	
	private static Log _log = PWServerLogManager.getLog(HighValueHandler.class
	        .getPackage().getName());
	
	private static final byte ASTERISK_BYTE = (byte) 0x2A;

	private static final char ASTERISK = '*';

	private HighValueHandler() {
		super();
	}

	public static String convertHVToAsterisk(String s, String envId) {

		if (s == null || s.length() == 0)
			return s;
		Environment environment = EnvironmentManager.getInstance().getEnvironment(envId);
		byte HIGHVALUE_BYTE_FROM_DB = environment.getHIGHVALUE_BYTE_RECEIVED();

		byte bytes[] =encode(s, environment);
			
		for (int i = 0; i < bytes.length; i++) {
			if ((byte) (bytes[i] & 0xff) == HIGHVALUE_BYTE_FROM_DB)
				bytes[i] = ASTERISK_BYTE;
		}

		return new String(bytes);
	}

	public static String convertAsteriskToHV(String s, String envId) {
		
		if (s == null || s.length() == 0)
			return s;

		Environment environment = EnvironmentManager.getInstance().getEnvironment(envId);
		
		byte[] bytes = s.getBytes();
		for (int i = 0; i < bytes.length; i++) {
			if ((bytes[i] & 0xff) == ASTERISK_BYTE)
				bytes[i] = environment.getHIGHVALUE_BYTE_TO_SEND();
		}
		
		String encoding = getEncoding(environment);
		
		String str = null;
		try {
			str = new String(bytes, encoding);
		} catch (UnsupportedEncodingException us) {
			us.printStackTrace();
			str = new String(bytes);
		}

		return str;
	}

	public static String getHighValues(int noOfChars, String environment) {
		if (noOfChars <= 0)
			return null;
		return convertAsteriskToHV(getAsterisks(noOfChars), environment);
	}

	public static String getAsterisks(int noOfChars) {
		if (noOfChars <= 0)
			return null;

		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < noOfChars; i++)
			sb.append(ASTERISK);

		return sb.toString().trim();
	}

	public static boolean isAllAsterisks(String s) {
		if (s == null || s.trim().length() == 0)
			return false;
		return s.trim().equals(getAsterisks(s.trim().length()));
	}

	// T0116 - rewrite to use TPWEV table, and to read first, write if necessary
	public static byte retrieveHighValue(Connection conn, String schema,String environmentId)
		throws Exception {
		
		PreparedStatement iStmt = null;
		Statement stmt = null;
		ResultSet rs = null;
		
		byte[] hvBytes = new byte[3];

		try {
			String highValueString = null;

			String selectSql = "SELECT COMPANY_CODE FROM " + schema
				+ ".TPWEV WHERE DDLNAME = 'HVTEST'";

			stmt = conn.createStatement();
			rs = stmt.executeQuery(selectSql);
			if ( rs.next() ) {
				highValueString = rs.getString(1);
			} else {
				Utils.closeResultSet(rs);
				// special HVTEST entry not found.  Try to insert it.
				_log.info("Highvalue test entry not found for " + environmentId + " (" + schema + ").  Trying to insert.");
				String timestamp = new Timestamp((new java.util.Date()).getTime()).toString();
				String insertSql = "INSERT INTO "
				        + schema
				        + ".TPWEV (COMPANY_CODE, DDLNAME, "
				        + "ENVIRONMENT, PRODUCT_PREFIX, SUBSET_NAME, TIME_STAMP)"
				        + " VALUES (?,'HVTEST','HVTEST','Z','HVTEST','" + timestamp + "')";

				iStmt = conn.prepareStatement(insertSql);
				//iStmt.setBytes(1, hvBytes);
				iStmt.setString(1, HighValueHandler.getHighValues(3, environmentId));
				iStmt.execute();

				// now try to read it back
				rs = stmt.executeQuery(selectSql);
				if ( rs.next() )
					highValueString = rs.getString(1);
			}

			if ( highValueString != null ) {
				hvBytes = encode(highValueString, EnvironmentManager.getInstance().getEnvironment(environmentId));
				_log.info("Highvalue char/byte from " + environmentId + " (" + schema + ") is "
				        + highValueString + "/" + Integer.toHexString(hvBytes[0]));
			} else {
				_log.error("Unable to retrieve high value char/byte from " + environmentId + " (" + schema + ")");
			}
		
		} catch (Exception ex) {
			throw WrapperException.wrap(ex,"Exception while retrieving high value for "+environmentId);
		} finally {
		
			Utils.closeResultSet(rs);
			Utils.closeStatement(stmt);
			Utils.closePreparedStatement(iStmt);
		}
		return hvBytes[0];
	}
	
	public static String getEncoding(Environment env){
		String encoding = env.getHighValueEncoding();
		if (encoding==null) 
			encoding = System.getProperty("file.encoding");
		return encoding;
	}
	
	public static byte[] encode(String str,Environment env){
		byte[] bytes = null;
		try{
			bytes = str.getBytes(getEncoding(env));
		}catch (UnsupportedEncodingException us) {
			us.printStackTrace();
			bytes = str.getBytes();
		}
		return bytes;
	}
	

}
