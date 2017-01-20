/*
 * THIS PROGRAM IS THE PROPERTY OF CSC FINANCIAL SERVICES GROUP. IT MAY NOT BE
 * COPIED IN WHOLE OR IN PART WITHOUT THE EXPRESS WRITTEN CONSENT OF CSC
 * FINANCIAL SERVICES GROUP.
 */

package com.csc.fsg.life.pw.rules;

/**
 * Class BaseCalculatedField
 * 
 * @author CSC-FSG,E.Hartford
 * @version PW 2.0 , 09-24-2002
 */

public abstract class BaseCalculatedField {

	protected boolean _blank;

	protected String _table;

	protected String _column;

	protected String _value;

	protected String _calculatedColumn;

	/**
	 * Constructor BaseCalculatedField
	 * 
	 * @param table
	 * @param column
	 * @param calculatedColumn
	 */

	public BaseCalculatedField(String table, String column,
	        String calculatedColumn) {

		_table = table;
		_column = column;
		_calculatedColumn = calculatedColumn;
	}

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
	 * Method update
	 * 
	 * @param row
	 * @param da
	 * @throws Exception
	 */

	public void update(int row, IDataAccess da) throws Exception {

		Object obj = da.getEditValue(row, _column);

		if (obj == null) {
			_blank = true;
			return;
		}

		_value = obj.toString();

		if (!isBlank(_value)) {
			_blank = false;
		} else {
			_blank = true;
		}
	}

	/**
	 * Method setValue
	 * 
	 * @param s
	 */

	protected void setValue(String s) {
		_value = s;
	}

	/**
	 * Method isBlank
	 * 
	 * @return
	 */

	protected boolean isBlank() {
		return _blank;
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
}
