/*
 * THIS PROGRAM IS THE PROPERTY OF CSC FINANCIAL SERVICES GROUP. IT MAY NOT BE
 * COPIED IN WHOLE OR IN PART WITHOUT THE EXPRESS WRITTEN CONSENT OF CSC
 * FINANCIAL SERVICES GROUP.
 */

package com.csc.fsg.life.pw.common.rules;

/**
 * @author Peter Santoro
 */

import java.util.*;


/* Modifications: T0091 */

/**
 * Class StringRule
 * 
 * @author CSC-FSG,E.Hartford
 * @version PW 2.0 , 09-24-2002
 */

public abstract class StringRule extends BaseRule {

	/**
	 * Method getEditValue
	 * 
	 * @param da
	 * @param row
	 * @param column
	 * @return
	 * @throws Exception
	 */

	public static String getEditValue(IDataAccess da, int row, String column)
	        throws Exception {
		return (String) da.getEditValue(row, column);
	}

	protected String _value;

	/**
	 * Constructor StringRule
	 * 
	 * @param table
	 * @param column
	 */

	public StringRule(String table, String column) {
		super(table, column);
	}

	/**
	 * Method setValue
	 * 
	 * @param s
	 * @throws Exception
	 */

	public void setValue(String s) throws Exception {
		_value = s;
	}

	/**
	 * Method isEqual
	 * 
	 * @param value
	 * @return
	 */

	protected boolean isEqual(String value) {

		if (isBlank()) {
			return false;
		}

		if (_value.equalsIgnoreCase(value)) {
			return true;
		}

		return false;
	}

	/**
	 * Method isEqual
	 * 
	 * @param values
	 * @return
	 */

	protected boolean isEqual(ArrayList<String> values) {

		if (isBlank()) {
			return false;
		}

		for (int i = 0; i < values.size(); i++) {
			if (_value.equalsIgnoreCase(values.get(i))) {
				return true;
			}
		}

		return false;
	}

}
