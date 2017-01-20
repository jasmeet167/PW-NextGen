/*
 * THIS PROGRAM IS THE PROPERTY OF CSC FINANCIAL SERVICES GROUP. IT MAY NOT BE
 * COPIED IN WHOLE OR IN PART WITHOUT THE EXPRESS WRITTEN CONSENT OF CSC
 * FINANCIAL SERVICES GROUP.
 */

package com.csc.fsg.life.pw.io;

import java.util.ArrayList;

/**
 * Class WizUtils
 * 
 * @author CSC-FSG,E.Hartford
 * @version PW 2.0 , 09-24-2002
 */

public final class WizUtils {

	private WizUtils() {
	}

	public static String[] asStringArray(ArrayList al) {

		String stringArray[] = new String[al.size()];

		for (int i = 0; i < stringArray.length; i++) {
			stringArray[i] = (String) al.get(i);
		}

		return stringArray;
	}

	public static String pwTrim(String s) {

		if (s == null) {
			return " ";
		}

		if (isStringAll(s, ' ')) {
			return " ";
		}

		return s.trim();
	}

	public static boolean isStringAll(String s, char c) {

		if (s == null) {
			return (false);
		}

		int len = s.length();

		for (int i = 0; i < len; i++) {
			if (s.charAt(i) != c) {
				return (false);
			}
		}

		return (true);
	}
}
