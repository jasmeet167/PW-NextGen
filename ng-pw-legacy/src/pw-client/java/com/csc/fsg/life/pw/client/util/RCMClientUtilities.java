/*
 * THIS PROGRAM IS THE PROPERTY OF CSC FINANCIAL SERVICES GROUP. IT MAY NOT BE
 * COPIED IN WHOLE OR IN PART WITHOUT THE EXPRESS WRITTEN CONSENT OF CSC
 * FINANCIAL SERVICES GROUP.
 */

package com.csc.fsg.life.pw.client.util;

/**
 * Class RCMClientUtilities
 * 
 * @author CSC-FSG,E.Hartford
 * @version PW 2.0 , 09-24-2002
 */

public class RCMClientUtilities {

	public static final String PKG_DELIM = "#pkg#";

	public static final String PRJ_DELIM = "#prj#";

	public static final String TAB_DELIM = "#tab#";

	public static final String USR_DELIM = "#usr#";

	public static final int DELIM_LENGTH = 5;

	/**
	 * Constructor RCMClientUtilities
	 */

	RCMClientUtilities() {
	}

	/**
	 * Method getDelimitedString
	 * 
	 * @param list
	 * @param delim
	 * @return
	 */

	public static String getDelimitedString(String[] list, String delim) {

		StringBuffer result = new StringBuffer();

		if (list != null) {
			for (int i = 0; i < list.length - 1; i++) {
				result.append(list[i]).append(delim);
			}
			if (list.length > 0) {
				result.append(list[list.length - 1]);
			}
		}
		return result.toString();
	}
}
