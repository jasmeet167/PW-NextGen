package com.csc.fsg.life.common.util;

/**
   Excaption type to hold information related to a table, row and column.
 */
public final class DataException
	extends Exception {

	// Class variables
	private static final String LABEL = "Data Exception";

	// Instance variables
	private String _table;
	private String _colType;
	private String _colName;
	private String _row;

	/**
	   Creates a DataException object without a message.
	 */
	public DataException() {
		super();
	}

	/**
	 * This constructor allows an exception handler to retrieve the exception message
	 * via the <EM>getMessage</EM> method.<P>
	 * @see Throwable#getMessage
	 * @param msg
	 */
	public DataException(String msg) {
		super(msg);
	}

	/**
	   Sets the table name this DataException is for.
	   @param s
	 */
	public void setTableName(String s) {
		_table = s;
	}

	/**
	   Returns the table name the exception pertains to.
	   @return The table name the exception pertains to.
	 */
	public String getTableName() {
		return _table;
	}

	/**
	 * Sets a row identifier this exception pertains to.
	 * @param s
	 */
	public void setRow(String s) {
		_row = s;
	}

	/**
	 * Returns a row identifier this exception pertains to.
	 * @return The row.
	 */
	public String getRow() {
		return _row;
	}

	/**
	 * Sets a column identifier this exception pertains to.
	 * @param s
	 */
	public void setColName(String s) {
		_colName = s;
	}

	/**
	 * Returns a column identifier this exception pertains to.
	 * @return The column name.
	 */
	public String getColName() {
		return _colName;
	}

	/**
	 * Sets the column type this exception pertains to.
	 * @param s
	 */
	public void setColType(String s) {
		_colType = s;
	}

	/**
	 * Returns the column type this exception pertains to.
	 * @return The column type.
	 */
	public String getColType() {
		return _colType;
	}

	/**
	 * Returns an overview description of this exception.
	 * @return The title.
	 */
	public String getTitle() {

		StringBuffer sb = new StringBuffer(80);

		sb.append(LABEL);

		if (_table != null) {
			sb.append(" in table ");
			sb.append(_table);
		}

		return sb.toString();
	}

	/**
	 * Returns a detailed description of this exception.
	 * @return The details.
	 */
	public String getDetails() {

		String msg;
		StringBuffer sb = new StringBuffer(1024);

		sb.append(LABEL);

		if (_table != null) {
			sb.append(" in table ");
			sb.append(_table);
		}

		sb.append("\n\n");

		if ((msg = getMessage()) != null) {
			sb.append("Reason: ");
			sb.append(msg);
			sb.append('\n');
		}

		if (_colName != null) {
			sb.append("Column name: ");
			sb.append(_colName);
			sb.append('\n');
		}

		if (_colType != null) {
			sb.append("Column data type: ");
			sb.append(_colType);
			sb.append('\n');
		}

		if (_row != null) {
			sb.append("Row data: ");
			sb.append(_row);
			sb.append('\n');
		}

		return sb.toString();
	}
}
