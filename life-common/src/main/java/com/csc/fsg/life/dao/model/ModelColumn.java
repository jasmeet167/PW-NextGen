package com.csc.fsg.life.dao.model;

/**
   Represents a row's column data in a database table.
**/
public class ModelColumn {
    private String columnName;
    private String oldData;
    private String newData;
    
	/**
	   Create a new ModelColumn object. 
	**/
    public ModelColumn() {
        
    }
    
	/**
	   Create a new ModelColumn object. 
	**/
    public ModelColumn(String colname) {
        columnName = colname;
    }

    /**
	   Flip old and new data. 
	**/
    public void flip() {
        String temp = oldData;
        oldData = newData;
        newData = temp;
    }
    
    /**
	   Returns the value of the OldData propety.
	   @return the value of the OldData proerty.
	   @see #setOldData
	**/
    public String getOldData() {
        return oldData;
    }

	/**
	   Sets the OldData property.
	   @param str the new value for the OldData property.
	   @see #getOldData
	**/
	public void setOldData(String str) {
        oldData = str;
    }
    
    /**
	   Returns the value of the NewData propety.
	   @return the value of the NewData proerty.
	   @see #setNewData
	**/
    public String getNewData() {
        return newData;
    }

	/**
	   Sets the NewData property.
	   @param str the new value for the NewData property.
	   @see #getNewData
	**/
	public void setNewData(String str) {
        newData = str;
    }
    
    /**
	   Returns the value of the ColumnName propety.
	   @return the value of the ColumnName proerty.
	   @see #setColumnName
	**/
    public String getColumnName() {
        return columnName;
    }

	/**
	   Sets the ColumnName property.
	   @param str the new value for the ColumnName property.
	   @see #getColumnName
	**/
	public void setColumnName(String str) {
        columnName = str;
    }
}
