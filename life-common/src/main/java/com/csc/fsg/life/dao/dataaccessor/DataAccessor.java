package com.csc.fsg.life.dao.dataaccessor;

import java.sql.Connection;
import java.sql.SQLException;

import com.csc.fsg.life.dao.model.DAOModel;
import com.csc.fsg.life.dao.model.DAOModelCollection;
import com.csc.fsg.life.dbutils.ConnMgrException;


/********************************************************
*  This class would serve as the base class for all generated 
*  DataAccessors	
*********************************************************/
public abstract class DataAccessor
{
	// Added for batch operations for java 1.3
	/**
	 * Constant for checking success status in batch insert operations.
	 */
	public static final int SUCCESS_NO_INFO = -2;
	/**
	 * Constant for checking failure status in batch insert operations.
	 */
	public static final int EXECUTE_FAILED = -3;

	/** The environment key. **/
	protected String envKey;

	/** 
		Create a DataAccessor.
	**/
	public DataAccessor()
	{
	}

	/** 
		Create a DataAccessor.
	**/
	public DataAccessor(String envKey) {
		this.envKey = envKey;
	}

	/**
	   Gets rows from the database. 
	   Not implemented, override if specific access class if needed.
	**/
	@SuppressWarnings("unchecked")
	public DAOModelCollection<? extends DAOModel> getList ( DAOModel model ) 
		throws SQLException, ConnMgrException, Exception
	{
		return new DAOModelCollection("No Collection");
	}
	
	
	/**
	   Gets rows from the database. 
	   Not implemented, override if specific access class if needed.
	**/
	@SuppressWarnings("unchecked")
	public DAOModelCollection<? extends DAOModel> getList () 
		throws SQLException, ConnMgrException, Exception
	{
		return new DAOModelCollection("No Collection");
	}
	
	/**
	   Insert row into the database. 
	   Not implemented, override if specific access class if needed.
	**/
	public int insert( Connection con, DAOModel model ) 
		throws SQLException, ConnMgrException, Exception
	{
		return -1;
	}

	/**
	   Insert row into the database. 
	   Not implemented, override if specific access class if needed.
	**/
	public int insert( DAOModel model ) 
		throws SQLException, ConnMgrException, Exception
	{
		return -1;
	}
		
	/**
	   Update row in the database. 
	   Not implemented, override if specific access class if needed.
	**/
	public int update( Connection con, DAOModel model ) 
		throws  SQLException, ConnMgrException, Exception
	{
		return -1;
	}

	/**
	   Update row in the database. 
	   Not implemented, override if specific access class if needed.
	**/
	public int update( DAOModel model ) 
		throws  SQLException, ConnMgrException, Exception
	{
		return -1;
	}
			
	/**
	   Delete row in the database. 
	   Not implemented, override if specific access class if needed.
	**/
	public int delete( Connection con, DAOModel model ) 
		throws SQLException, ConnMgrException, Exception
	{
		return -1;
	}
	
	/**
	   Delete row in the database. 
	   Not implemented, override if specific access class if needed.
	**/
	public int delete( DAOModel model ) 
		throws SQLException, ConnMgrException, Exception
	{
		return -1;
	}	
}

