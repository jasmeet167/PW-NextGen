package com.csc.fsg.life.dao.exceptions;

import java.util.ListResourceBundle;

/**
   SQL Error message converter.  Holds SQL error code and cooresponding 
   english descriptoin.
**/
public class ErrorMessages 
    extends ListResourceBundle
{
	/**
	   Get the error messages.
	**/
	public Object[][] getContents()
	{
		return contents;
	}

	/**
	 *	Key - SQLState
	 *	Value - Readable Error Message
	 */
	private final Object[][] contents = 
	{
		{"23001", "Attempt to Delete where this record is a parent for a record in another Table. (SQLSTATE=23001)"},
		{"23502", "Attempt to Insert or Update a record in which data was not supplied for a field which requires data. (SQLSTATE=23502)"},
		{"23505", "Record already exists under current Key Parameters. (SQLSTATE=23505)"}
	};
}
