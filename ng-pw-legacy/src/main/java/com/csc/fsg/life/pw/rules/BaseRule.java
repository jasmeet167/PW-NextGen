/*
 * THIS PROGRAM IS THE PROPERTY OF CSC FINANCIAL SERVICES GROUP. IT MAY NOT BE
 * COPIED IN WHOLE OR IN PART WITHOUT THE EXPRESS WRITTEN CONSENT OF CSC
 * FINANCIAL SERVICES GROUP.
 */

package com.csc.fsg.life.pw.rules;

/**
 * @author Peter Santoro
 */

import java.util.*;

/* Modifications: T0091 */

/**
 * Class BaseRule
 * 
 * @author CSC-FSG,E.Hartford
 * @version PW 2.0 , 09-24-2002
 */

public abstract class BaseRule {

	protected static ArrayList<String> _values = new ArrayList<String>(8);

	protected static StringBuffer _error = new StringBuffer(80);

	/**
	 * Method isBlank
	 * 
	 * @param obj
	 * @return
	 */

	protected static boolean isBlank(Object obj) {

		if (obj == null) {
			return true;
		}

		String s = obj.toString();

		if (s.trim().length() == 0) {
			return true;
		}

		return false;
	}

	protected boolean _blank;

	protected String _table;

	protected String _column;

	/**
	 * Constructor BaseRule
	 * 
	 * @param table
	 * @param column
	 */

	public BaseRule(String table, String column) {

		_table = table;
		_column = column;
	}

	/**
	 * Method setValue
	 * 
	 * @param s
	 * @throws Exception
	 */

	public abstract void setValue(String s) throws Exception;

	/**
	 * Method getTableName
	 * 
	 * @return
	 */

	public String getTableName() {
		return _table;
	}

	/**
	 * Method getColumnName
	 * 
	 * @return
	 */

	public String getColumnName() {
		return _column;
	}

	/**
	 * Method isBlank
	 * 
	 * @return
	 */

	public boolean isBlank() {
		return _blank;
	}

	/**
	 * Method getErrorMessage
	 * 
	 * @return
	 */

	public String getErrorMessage() {
		return _error.toString();
	}

	/**
	 * Method toString
	 * 
	 * @return
	 */

	public String toString() {

		StringBuffer sb = new StringBuffer(80);

		sb.append("rule ");
		sb.append(_table);
		sb.append(':');
		sb.append(_column);

		return sb.toString();
	}

	/**
	 * Method check
	 * 
	 * @param row
	 * @param da
	 * @return
	 * @throws Exception
	 */

	public boolean check(int row, IDataAccess da) throws Exception {

		_error.setLength(0);
		_values.clear();
		Object obj = da.getEditValue(row, _column);

		if (obj == null) {
			_blank = true;
			return true;
		}

		String s = obj.toString();

		if (!isBlank(s)) {
			setValue(s);
			_blank = false;
		} else {
			setValue(s);
			_blank = true;
		}

		return true;
	}

	/**
	 * Method isBlank
	 * 
	 * @param s
	 * @return
	 */

	protected boolean isBlank(String s) {

		if ((s == null) || (s.trim().length() == 0)) {
			return true;
		}

		return false;
	}

	/**
	 * Method isEqual
	 * 
	 * @param obj
	 * @param value
	 * @return
	 */

	protected boolean isEqual(Object obj, String value) {

		if (obj == null) {
			return false;
		}

		String s = obj.toString();

		if (isBlank(s)) {
			return false;
		}

		if (s.equalsIgnoreCase(value)) {
			return true;
		}

		return false;
	}

	/**
	 * Method isEqual
	 * 
	 * @param obj
	 * @param values
	 * @return
	 */

	protected boolean isEqual(Object obj, ArrayList<String> values) {

		if (obj == null) {
			return false;
		}

		String s = obj.toString();

		if (isBlank(s)) {
			return false;
		}

		for (int i = 0; i < values.size(); i++) {
			if (s.equalsIgnoreCase(values.get(i))) {
				return true;
			}
		}

		return false;
	}

	/**
	 * Method eval
	 * 
	 * @param exp
	 * @return
	 */

	protected boolean eval(boolean exp) {

		if (isBlank()) {
			return false;
		}

		return exp;
	}
}
