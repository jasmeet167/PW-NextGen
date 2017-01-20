package com.csc.fsg.life.dao.bo;

import javax.sql.DataSource;

/********************************************************
*  Use this class to pass a command to the JBOs by calling 
*  the process method on the JBO
*********************************************************/
public class BusinessObjectCommand
{
	// the userId initiating the business object
	private String userId;
	// the action the business object should take
	private String action;
	// The datasource to attach to
	private String datasource;
	
	//jndiDataSource
	private DataSource jndiDataSource;
	
	//
	private String schema;

	/** Constant for INQUIRY action. **/
	public final static String ACTION_INQUIRY = "INQUIRY";
	/** Constant for UPDATE action. **/
	public final static String ACTION_UPDATE = "UPDATE";
	/** Constant for DELETE action. **/
	public final static String ACTION_DELETE = "DELETE";
	/** Constant for INSERT action. **/
	public final static String ACTION_INSERT = "INSERT";
	/** Constant for INIT action. **/
	public final static String ACTION_INIT = "INIT";
	/** Constant for CLONE action. **/
	public final static String ACTION_CLONE = "CLONE";
	/** Constant for ASSEMBLE action. **/
	public final static String ACTION_ASSEMBLE = "ASSEMBLE";
	
	private Object request = null;
	private Object response = null;
	private Exception exception = null;

	/**
	   Create a BusinessObjectCommand with the specified action.
	**/
	public BusinessObjectCommand ( String action )
	{
		this(null,action);
	}

	/**
	   Create a BusinessObjectCommand with the specified action and DB parameters.
	**/
	public BusinessObjectCommand ( DataSource dataSource,String userId, String schema, String action )
	{
		this.jndiDataSource = dataSource;
		this.userId = userId;
		this.schema = schema;
		this.action = action;
	}

	/**
	   Create a BusinessObjectCommand with the specified action and user.
	**/
	public BusinessObjectCommand ( String userId, String action )
	{
		this(userId, action, "DB2");
	}

	/**
	   Create a BusinessObjectCommand with the specified action and datasource paraemters.
	**/
	public BusinessObjectCommand ( String userId, String action, String datasource )
	{
        this.userId = userId;
		this.action = action;
		this.datasource = datasource;
	}

	/**
	   Sets the user id.
	**/
	public void setUserId ( String userId )
	{
		this.userId = userId;
	}

	/**
	   Sets the Action property.
	   @param action the new Action value.
	   @see #getAction
	**/
	public void setAction ( String action )
	{
		this.action = action;
	}

	/**
	   Sets the response.
	**/
	public void setResponse ( Object response )
	{		
		this.response = response;
	}

	/**
	   Sets the exception.
	**/
	public void setException ( Exception exception )
	{		
		this.exception = exception;
	}

	/**
	   Returns the UserId property.
	   @return the UserId property.
	**/
	public String getUserId()
	{
		return userId;
	}

	/**
	   Returns the Action property.
	   @return the Action property.
	   @see #setAction
	**/
	public String getAction()
	{
		return action;
	}
	
	/**
	   Returns the Request property.
	   @return the Request property.
	**/
	public Object getRequest()
	{
		return request;
	}

	/**
	   Returns the Response property.
	   @return the Response property.
	**/
	public Object getResponse()
	{
		return response;
	}
	
	/**
	   Returns the Exception property.
	   @return the Exception property.
	**/
	public Exception getException()
	{
		return exception;
	}

	/**
	   Returns the DataSource property.
	   @return the DataSource property.
	**/
	public String getDataSource()
	{
		return datasource;
	}

	/**
	   Returns the JndiDataSource property.
	   @return the JndiDataSource property.
	**/
	public DataSource getJndiDataSource() {
		return jndiDataSource;
	}

	/**
	   Returns the Schema property.
	   @return the Schema property.
	**/
	public String getSchema() {
		return schema;
	}
	
}

