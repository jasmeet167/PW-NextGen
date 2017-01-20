package com.csc.fsg.life.common.config;

import javax.sql.DataSource;

/**
 * This class represents the db element is configuration file.
 */
public class EnvDbInfoBean {

	private String displayName;
	private String name;
	private String schema;
	private String tableType;
	private boolean defaultDb; 
	private DataSource jndiDataSource;
	private String dataBaseId;
	
	/** Constant for a Data type database. **/
	public static final String TYPE_DATA = "data";
	/** Constant for a Rules type database. **/
    public static final String TYPE_RULE = "rule";
	
	/**
	   Returns the DisplayName.
	   @return the DisplayName value.
	   @see #setDisplayName
	**/
	public String getDisplayName() {
		return displayName;
	}

	/**
	   Sets the DisplayName.
	   @param displayName the new DisplayName value.
	   @see #getDisplayName
	**/
	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}
	
	/**
	   Returns the Name.
	   @return the Name value.
	   @see #setName
	**/
	public String getName() {
		return name;
	}

	/**
	   Sets the Name.
	   @param name the new Name value.
	   @see #getName
	**/
	public void setName(String name) {
		this.name = name;
	}

	/**
	   Returns the Schema.
	   @return the Schema value.
	   @see #setSchema
	**/
	public String getSchema() {
		return schema;
	}

	/**
	   Sets the Schema.
	   @param schema the new Schema value.
	   @see #getSchema
	**/
	public void setSchema(String schema) {
		this.schema = schema;
	}

	/**
	   Returns the TableType.
	   @return the TableType value.
	   @see #setTableType
	**/
	public String getTableType() {
		return tableType;
	}

	/**
	   Sets the TableType.
	   @param tableType the new TableType value.
	   @see #getTableType
	**/
	public void setTableType(String tableType) {
		this.tableType = tableType;
	}

	/**
	   Returns true if this is a default DB.
	   @return true if this is a default DB.
	   @see #setDefaultDb
	**/
	public boolean isDefaultDb() {
		return defaultDb;
	}

	/**
	   Sets the Default flag for this EnvDbInfoBean.
	   @param def the new DefaultDb value.
	   @see #isDefaultDb
	**/
	public void setDefaultDb(boolean def) {
		this.defaultDb = def;
	}

	/**
	   Returns the JndiDataSource.
	   @return the JndiDataSource value.
	   @see #setJndiDataSource
	**/
	public DataSource getJndiDataSource() {
		return jndiDataSource;
	}

	/**
	   Sets the JndiDataSource.
	   @param jndiDataSource the new JndiDataSource value.
	   @see #getJndiDataSource
	**/
	public void setJndiDataSource(DataSource jndiDataSource) {
		this.jndiDataSource = jndiDataSource;
	}

	/**
	   Returns the DataBaseId.
	   @return the DataBaseId value.
	   @see #setDataBaseId
	**/
	public String getDataBaseId() {
		return dataBaseId;
	}

	/**
	   Sets the DataBaseId.
	   @param dataBaseId the new DataBaseId value.
	   @see #getDataBaseId
	**/
	public void setDataBaseId(String dataBaseId) {
		this.dataBaseId = dataBaseId;
	}
	
	public String toString()
	{
		StringBuffer buf = new StringBuffer();
		buf.append("displayName: ");
		buf.append(displayName);
		buf.append(", name: ");
		buf.append(name);
		buf.append(", schema: ");
		buf.append(schema);
		buf.append(", tableType: ");
		buf.append( tableType);
		buf.append(", defaultDB: ");
		buf.append(defaultDb);
		buf.append(", jndiDataSource: ");
		buf.append(jndiDataSource);
		buf.append(", databaseId: ");
		buf.append(dataBaseId);		
		
		return buf.toString();
	}
}
