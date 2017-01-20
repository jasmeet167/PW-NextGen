package com.csc.fsg.life.avm.biz.dao.model;

import java.util.ArrayList;

import com.csc.fsg.life.dao.model.DAOModel;
import com.csc.fsg.life.dao.model.ModelColumn;

/* Modifications: T0175 */

public class AVALFLDCacheCleanModel 
	extends DAOModel 
	implements java.io.Serializable 
{ 
    private String fieldKey;
    private AVALFLDCacheCleanModel dbCopy;

	/**
	   Returns the FieldKey.
	   @return the FieldKey for this object.
	   @see #setFieldKey
	**/
    public String getFieldKey(){ 
        return this.fieldKey;
    } 
	/**
	   Sets the FieldKey.
	   @param aString The new FieldKey value.
	   @see #getFieldKey
	 **/
    public void setFieldKey(String aString){ 
        fieldKey = aString;
    } 

	/**
	   Returns the DbCopy.
	   @return the DbCopy for this object.
	   @see #setDbCopy
	**/
    public AVALFLDCacheCleanModel getDbCopy(){ 
        return this.dbCopy;
    } 
	/**
	   Sets the DbCopy.
	   @param aAVALFLDCacheCleanModel The new DbCopy value.
	   @see #getDbCopy
	 **/
    public void setDbCopy(AVALFLDCacheCleanModel aAVALFLDCacheCleanModel){ 
        dbCopy = aAVALFLDCacheCleanModel;
    } 

	/**
	   Create a new instance of this object.
	**/
    public  AVALFLDCacheCleanModel(){ 
        super();
        dbCopy = null;
    } 

	/**
	   Create a new instance of this object with the specified state.
	   @param aint The state.
	**/
    public  AVALFLDCacheCleanModel(int aint){ 
        super(aint);
        dbCopy = null;
    } 

	/**
	   Returns the ModelName.
	   @return the ModelName for this object.
	**/
    @Override
    public String getModelName(){ 
        return "com.csc.fsg.life.avm.biz.dao.model.AVALFLDCacheCleanModel";
    } 

	/**
	   Returns the TableName.
	   @return the TableName for this object.
	**/
    public String getTableName(){ 
        return "AVALFLDCacheClean";
    } 

	/**
	   Create a new instance of this model object.
	**/
    @Override
    public DAOModel createNew(){ 
        return new AVALFLDCacheCleanModel();
    } 

	/**
	   Create a new instance of this model object that is 
	   a copy of this one.
	**/
    public DAOModel createCopy(){ 
        AVALFLDCacheCleanModel newModel = new AVALFLDCacheCleanModel();
        newModel.setFieldKey(this.getFieldKey());
        newModel.setModelState(this.getModelState());
        return newModel;
    } 

    @Override
    public boolean hasChanged(){ 
        if (dbCopy == null) return true;
        if (this.getFieldKey() != null) { 
             if (!this.getFieldKey().equals(dbCopy.getFieldKey()))  return true;
        }
         else {
             if (dbCopy.getFieldKey() != null)  return true;
        }
        return false;
    } 

    @Override
    public boolean equals(Object aObject){ 
        AVALFLDCacheCleanModel aAVALFLDCacheCleanModel = (AVALFLDCacheCleanModel)aObject;
        if (this.getFieldKey() != null) { 
            if (!this.getFieldKey().equals(aAVALFLDCacheCleanModel.getFieldKey()))  return false;
        }
         else {
            if (aAVALFLDCacheCleanModel.getFieldKey() != null)  return false;
        }
        return true;
    } 

	@Override
	public int hashCode()
	{
		StringBuilder buf = new StringBuilder();
		buf.append(getFieldKey());
		return buf.toString().hashCode();
	}

    @Override
    public boolean hasKeysChanged(){ 
        if (dbCopy == null)  return true;
        return false;
    } 

    @Override
    public boolean hasKeysChanged(Object aObject){
        return false;
    } 

    /** 
        Returns true if all required fields are non-null.
        @return the true all required fields are non-null, false if not.
     **/ 
    public boolean isRequiredFieldsNotNull(){ 
           if (this.getFieldKey() == null)  return false;
        return true;
    } 

    /** 
        Returns true if all primary key fields are non-null.
        @return the true all primary key fields are non-null, false if not.
     **/ 
    public boolean isPrimaryKeysNotNull(){ 
        return true;
    } 

    @Override
    public String toString(){ 
        StringBuffer sb = new StringBuffer();
        sb.append ("FieldKey="+this.getFieldKey()+"  ");
        return new String(sb);
    } 

    /** 
        Returns a list of the fields in this object as ModelColumn objects.
        @return a list of the fields in this object as ModelColumn objects.
     **/ 
    public ArrayList<ModelColumn> getAsModelColumns(){ 
        	ArrayList<ModelColumn> retval = new ArrayList<ModelColumn>();
        	ModelColumn modelColumn = new ModelColumn("FIELD_KEY");
        	if (dbCopy != null) {
        		if (dbCopy.getFieldKey() != null) {
        			modelColumn.setOldData(dbCopy.getFieldKey().toString());
        		}
        	}
        	if (getFieldKey() != null) {
        		modelColumn.setNewData(getFieldKey().toString());
        	}
        	retval.add(modelColumn);
        return retval;
    } 

	/**
	   Returns the value of the OldCopy property.
	   @return the value of the OldCopy property.
	   @see #setOldCopy
	**/
    @Override
    public DAOModel getOldCopy(){ 
        return dbCopy;
    } 

	/**
	   Sets the OldCopy property.
	   @param aDAOModel the new value for the OldCopy property.
	   @see #getOldCopy
	**/
	public void setOldCopy(DAOModel aDAOModel){ 
        if (aDAOModel != null) {
        	dbCopy = (AVALFLDCacheCleanModel)aDAOModel;
        }
        else {
        	dbCopy = null;
        }
    } 
}
