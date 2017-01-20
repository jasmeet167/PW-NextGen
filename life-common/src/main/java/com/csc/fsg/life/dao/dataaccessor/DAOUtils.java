package com.csc.fsg.life.dao.dataaccessor;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.csc.fsg.life.dao.model.DAOModel;
import com.csc.fsg.life.dao.model.DAOModelException;

/* Modifications: T0140 */

/**
   Utility class that holds helper methods useful 
   when accessing a database via SQL.
**/
public class DAOUtils
{
	public static int getIntFromBoolean ( boolean flag, PreparedStatement ps ) throws SQLException
	{
		if (flag)
			return 1;
		else
			return ps.getUpdateCount();
	}

	/**
	   If parameter is null returns an empty string otherwise
	   returns the trimmed string.
	   @param str the string to process.
	   @return the string trimmed.
	**/
	public static String getString(String str)
	{
		if (str == null)
			return null;
		else
			return str.trim();
	}

	/**
	   If parameter is null returns an empty string otherwise
	   returns the trimmed string.
	**/
	public static String safeString(String str)
	{
		if (str == null)
			return "";
		else
			return str.trim();
	}
	
	/**
	   Returns a Double object. 
	**/
	public static Double getDouble(double d)
	{
		return Double.valueOf(d);
	}
	
	/**
	   Returns a Long object. 
	**/
	public static Long getLong(long l)
	{
		return Long.valueOf(l);
	}
	

	/**
	   Returns a Integer object if i is poisitve, else returns null.
	**/
	public static Integer getInteger(int i)
	{
		if (i < 0)
			return null;
		else
			return Integer.valueOf(i);
	}	

	/**
	   Right justifies a string to the specified size.
	**/
	public static String getJustifiedString(String str, int size)
    {
		if (str == null)
			return "";
        else {
            StringBuffer justifyString = new StringBuffer();
            int adjustmentLength = size - str.trim().length();
            for (int i=0; i<adjustmentLength; i++)
                justifyString.append(' ');
			return justifyString.toString() + str.trim();
		}
	}

	/**
	   Get the default integer value.
	**/
	public static int getDefaultInteger()
	{
		return -1;
	}
	
	/**
	   Get the default double value.
	**/
	public static double getDefaultDouble()
	{
		return (double)-1;
	}
	
	/**
	   Get the default long value.
	**/
	public static long getDefaultLong()
	{
		return (long)-1L;
	}	
	
    /**
	   Convenience method for {@link #handleOneModel(Connection, DataAccessor, DAOModel)}.
	*/
	public static int handleOneModel ( Connection con, DAOModel model ) throws DAOModelException
	{
		return handleOneModel ( con, model.getDataAccessorObject(), model );
	}	

	/**
	   Handles a model by performing either an insert, update, or delete based
	   on the state of the model.
	**/
	public static int handleOneModel(Connection con, DataAccessor dao, DAOModel model) throws DAOModelException
	{
		int result = 0;
		if (model.getModelState() == DAOModel.CREATED_IN_MEMORY)
			result = insert( con, dao, model );
		else {
			if (model.getModelState() == DAOModel.READ_FROM_DB_DELETED)
				result = delete(con, dao, model);
			else {
				if (model.getModelState() == DAOModel.READ_FROM_DB_UPDATED)
					result = update( con, dao, model );
				else {
					if (model.getModelState() == DAOModel.READ_FROM_DB) {
						if (model.hasChanged()) {
							if (model.hasKeysChanged()) {
								DAOModel oldmodel = model.getOldCopy(); 
								if (oldmodel != null)
									result = delete(con, dao, oldmodel);
								else
									throw new DAOModelException("Model " + model + " has state READ_FROM_DB but no dbCopy object");

								result = insert(con, dao, model);
							}
							else
								result = update(con, dao, model);
						}
					}
				}
			}
		}

		return result;
	}


	/**
	   Insert a row into DB table.
	**/
	public static int insert(Connection con, DataAccessor dao, DAOModel model) throws DAOModelException
	{
		int result = 0;
		try {
			result = dao.insert( con, model);

			if (result < 0) {
				con.rollback();
				throw new DAOModelException("Insert failed for model " + model.getModelName());
			}

			return result;
		}
		catch (Exception e) {
			throw new DAOModelException ( e );
		}

	}

	/**
	   Delete a row from DB table.
	**/
	public static int delete(Connection con, DataAccessor dao, DAOModel model) throws DAOModelException
	{
		int result = 0;
		try {
			result = dao.delete(con, model);
			if (result < 0) {
				con.rollback();
				throw new DAOModelException ( "Delete failed for model " + model.getModelName() );
			}

			return result;
		}
		catch (Exception e) {
			throw new DAOModelException ( e );
		}

	}

	/**
	   Update a row in a DB Table.
	**/
	public static int update ( Connection con, DataAccessor dao, DAOModel model ) throws DAOModelException
	{
		int result = 0;
		try {
			result = dao.update( con, model);
			if (result < 0) {
				try {
					result = dao.delete(con, model);
					if (result < 0) {
						con.rollback();
						throw new DAOModelException("Delete failed for model " + model.getModelName());
					}

					result = dao.insert(con, model);
					if ( result < 0 ) {
						con.rollback();
						throw new DAOModelException("Insert failed for model " + model.getModelName());
					}
				}
				catch (Exception e) {
					throw new DAOModelException(e);
				}
			}

			return result;
		}
		catch (Exception e) {
			throw new DAOModelException(e);
		}
	}

	/**
	   Builds a SQLException for invalid data.
	   @return a SQLException for invalid data.
	**/
	static public SQLException buildInvalidDataException() { 
		return new SQLException("Invalid Character", "22000"); 
	}

	/**
	   Validate that a string doesn't contain in-secure SQL characters.
	   @param s The string to validate.
	   @throws SQLException If the string contains invalid characters.
	**/
	static public void validString(String s)
		throws SQLException
	{
		if ((s.indexOf(";") > -1)  ||
			(s.indexOf("\'") > -1) ||
			(s.indexOf("-") > -1)  ||
			(s.indexOf("&") > -1)  ||
			(s.indexOf("\"") > -1)  ||
			(s.indexOf("\\") > -1))
			throw buildInvalidDataException();;
	}

}
