/*
 * THIS PROGRAM IS THE PROPERTY OF CSC FINANCIAL SERVICES GROUP. IT MAY NOT BE
 * COPIED IN WHOLE OR IN PART WITHOUT THE EXPRESS WRITTEN CONSENT OF CSC
 * FINANCIAL SERVICES GROUP.
 */

package com.csc.fsg.life.pw.common.util;

import java.util.*;

/* Modifications: T0103, T0091, T0129, ENH996 */
// T0103 - moved to pwCommon

/**
 * Class IOTokenizer
 * 
 * @author CSC-FSG,E.Hartford
 */

public class IOTokenizer {

	private String input;

	private String delim;

	private Vector<String> tokens;

	private int current;

	/**
	 * IOTokenizer constructor comment.
	 * 
	 * @param input
	 * @param delim
	 */

	public IOTokenizer(String input, String delim) {

		super();
		this.input = input;
		this.delim = delim;

		tokens = new Vector<String>(10, 10);
		current = 0;

		parse();
	}

	/**
	 * Insert the method's description here. Creation date: (04/09/2001 4:23:16
	 * PM)
	 * 
	 * @return
	 */

	public int countTokens() {
		return tokens.size();
	}

	/**
	 * Insert the method's description here. Creation date: (04/09/2001 4:23:16
	 * PM)
	 * 
	 * @return
	 */

	public boolean hasMoreTokens() {

		if (current < tokens.size()) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * Insert the method's description here. Creation date: (04/09/2001 4:23:16
	 * PM)
	 * 
	 * @return
	 */

	public String nextToken() {

		String retVal = null;

		if (current < tokens.size()) {
			retVal = tokens.elementAt(current);
			current++;
		}

		return retVal;
	}

	/**
	 * Insert the method's description here. Creation date: (04/09/2001 4:23:16
	 * PM)
	 */

	private void parse() {

		String temp = input;

		while (temp.length() > 0) {
			int index = temp.indexOf(delim);

			String token = null;

			if (index < 0) {
				token = temp;
			} else {
				token = temp.substring(0, index);
				temp = temp.substring(index + delim.length());
			}

			tokens.addElement(token);

			if (index < 0) {
				break;
			}
		}

		if ( !input.isEmpty() ) {
			if (input.lastIndexOf(delim) == input.length() - delim.length()) {
				tokens.addElement("");
			}
		}
	}
	
	public String[] getValues() {
		return tokens.toArray(new String[0]);
	}
}
