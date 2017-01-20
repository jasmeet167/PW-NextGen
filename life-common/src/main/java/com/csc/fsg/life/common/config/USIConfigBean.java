package com.csc.fsg.life.common.config;

import javax.sql.DataSource;

/* Modifications: ENH1019 */

/** 
	This class holds the configuration information for USI.
 */
public class USIConfigBean 
{
	private DataSource dataSource;
	private String schema;

	/**
	   Get the data source used to access the AVM database.
	   @return the data source used to access the AVM database.
	   @see #setDataSource
	**/
	public DataSource getDataSource() {
		return dataSource;
	}

	/**
	   Sets the data source used to access the AVM database.
	   @param dataSource the data source used to access the AVM database.
	   @see #getDataSource
	**/
	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	/**
	   Returns the scheme used in the AVM database.
	   @return the scheme used in the AVM database.
	   @see #setSchema
	**/
	public String getSchema() {
		return schema;
	}

	/**
	   Sets the scheme used in the AVM database.
	   @param schema the scheme used in the AVM database.
	   @see #getSchema
	**/
	public void setSchema(String schema) {
		this.schema = schema;
	}
}
