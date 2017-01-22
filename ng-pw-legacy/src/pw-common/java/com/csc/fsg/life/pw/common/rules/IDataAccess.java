/*
 * THIS PROGRAM IS THE PROPERTY OF CSC FINANCIAL SERVICES GROUP. IT MAY NOT BE
 * COPIED IN WHOLE OR IN PART WITHOUT THE EXPRESS WRITTEN CONSENT OF CSC
 * FINANCIAL SERVICES GROUP.
 */

package com.csc.fsg.life.pw.common.rules;

/**
 * @author Peter Santoro
 */

public interface IDataAccess {

	/**
	 * Method getEditValue
	 * 
	 * @param row
	 * @param column
	 * @return
	 * @throws Exception
	 */

	public Object getEditValue(int row, String column) throws Exception;

	/**
	 * Method setEditValue
	 * 
	 * @param row
	 * @param column
	 * @param value
	 * @throws Exception
	 */

	public void setEditValue(int row, String column, String value)
	        throws Exception;

	/**
	 * Method getDefaultFund
	 * 
	 * @throws Exception
	 */

	public int getDefaultFund() throws Exception;

	/**
	 * Method getDefaultFund
	 * 
	 * @throws Exception
	 */

	public int getMaxFundNumber() throws Exception;
}
