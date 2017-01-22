/*
 * THIS PROGRAM IS THE PROPERTY OF CSC FINANCIAL SERVICES GROUP. IT MAY NOT BE
 * COPIED IN WHOLE OR IN PART WITHOUT THE EXPRESS WRITTEN CONSENT OF CSC
 * FINANCIAL SERVICES GROUP.
 */

package com.csc.fsg.life.pw.common.rules;

/**
 * @author Peter Santoro
 */

import com.csc.fsg.life.common.util.DataException;

/**
 * Class NaturalLogCalculatedField
 * 
 * @author CSC-FSG,E.Hartford
 * @version PW 2.0 , 09-24-2002
 */

public class NaturalLogCalculatedField extends BaseCalculatedField {

	/**
	 * Constructor NaturalLogCalculatedField
	 * 
	 * @param table
	 * @param column
	 * @param calculatedColumn
	 */

	public NaturalLogCalculatedField(String table, String column,
	        String calculatedColumn) {
		super(table, column, calculatedColumn);
	}

	/**
	 * Method update
	 * 
	 * @param row
	 * @param da
	 * @throws Exception
	 */

	public void update(int row, IDataAccess da) throws Exception {

		super.update(row, da);

		if (!isBlank()) {
			double d = toDouble(_value);

			Double nl = new Double(naturalLogOf(d));

			da.setEditValue(row, _calculatedColumn, nl.toString());
		}
	}

	private double naturalLogOf(double d) {

		double nl = Math.log((d / 100) + 1);

		return nl;
	}

	/**
	 * Method toDouble
	 * 
	 * @param s
	 * @return
	 * @throws DataException
	 */

	public static double toDouble(String s) throws DataException {

		double d;

		try {
			d = Double.parseDouble(s);
		} catch (NumberFormatException e) {
			throw new DataException("\"" + s + "\" is not a valid double");
		}

		return d;
	}

}
