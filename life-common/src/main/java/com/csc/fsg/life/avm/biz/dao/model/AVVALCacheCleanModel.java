package com.csc.fsg.life.avm.biz.dao.model;

import java.util.ArrayList;

import com.csc.fsg.life.dao.model.DAOModel;
import com.csc.fsg.life.dao.model.ModelColumn;

/* Modifications: T0175 */

/** 
    Table Model data transfer object AVVALCacheCleanModel.
 **/ 
public class AVVALCacheCleanModel 
	extends DAOModel 
	implements java.io.Serializable 
{ 
    /** 
        Property fieldKeyfor table AVVALCacheClean.
     **/ 
    private String fieldKey;
    private AVVALCacheCleanModel dbCopy;

    /** 
        Returns property fieldKey for this object.
        @return the property fieldKey for this object.
        @see #setFieldKey
     **/ 
    public String getFieldKey(){ 
        return this.fieldKey;
    } 
    /** 
        Sets the property fieldKey for this object.
        @param aString the new value for this property.
        @see #getFieldKey
     **/ 
    public void setFieldKey(String aString){ 
        fieldKey = aString;
    } 

    /** 
        Returns property dbCopy for this object.
        @return the property dbCopy for this object.
        @see #setDbCopy
     **/ 
    public AVVALCacheCleanModel getDbCopy(){ 
        return this.dbCopy;
    } 
    /** 
        Sets the property dbCopy for this object.
        @param aAVVALCacheCleanModel the new value for this property.
        @see #getDbCopy
     **/ 
    public void setDbCopy(AVVALCacheCleanModel aAVVALCacheCleanModel){ 
        dbCopy = aAVVALCacheCleanModel;
    } 
    /** 
        Default constructor for AVVALCacheCleanModel.
     **/ 
    public  AVVALCacheCleanModel(){ 
        super();
        dbCopy = null;
    } 

    /** 
        Constructor for AVVALCacheCleanModel with state.
        @param aint the state of this model object.
     **/ 
    public  AVVALCacheCleanModel(int aint){ 
        super(aint);
        dbCopy = null;
    } 

    /** 
        Returns the model name.
        @return the model name.
     **/ 
    @Override
    public String getModelName(){ 
        return "com.csc.fsg.life.avm.biz.dao.model.AVVALCacheCleanModel";
    } 

    /** 
        Returns the table name AVVALCacheClean.
        @return the table name.
     **/ 
    public String getTableName(){ 
        return "AVVALCacheClean";
    } 

    /** 
        Returns a new instance of this class.
        @return the new instance.
     **/ 
    @Override
    public DAOModel createNew(){ 
        return new AVVALCacheCleanModel();
    } 

    /** 
        Returns a clone of this instance.
        @return the clone instance.
     **/ 
    public DAOModel createCopy(){ 
        AVVALCacheCleanModel newModel = new AVVALCacheCleanModel();
        newModel.setFieldKey(this.getFieldKey());
        newModel.setModelState(this.getModelState());
        return newModel;
    } 

    /** 
        Returns true if any field in this object has changed since being read from the DB.
        @return the true if changed, false if not.
     **/ 
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

    /** 
        Returns true if any field in this object has changed since being read from the DB.
        @param aObject The object to compare against.
        @return the true if changed, false if not.
     **/ 
    @Override
    public boolean equals(Object aObject){ 
        AVVALCacheCleanModel aAVVALCacheCleanModel = (AVVALCacheCleanModel)aObject;
        if (this.getFieldKey() != null) { 
            if (!this.getFieldKey().equals(aAVVALCacheCleanModel.getFieldKey()))  return false;
        }
         else {
            if (aAVVALCacheCleanModel.getFieldKey() != null)  return false;
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

    /** 
        Returns true if any key field in this object has changed since being read from the DB.
        @return the true if changed, false if not.
     **/ 
    @Override
    public boolean hasKeysChanged(){ 
        if (dbCopy == null)  return true;
        return false;
    } 

    /** 
        Returns true if any key field in this object is different than the key fields in the specified object.
        @param aObject The object to compare with.
        @return the true if changed, false if not.
     **/ 
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
        Returns the original version of this model object as retrieved from the DB.
        @return the original version of this model object as retrieved from the DB.
        @see #setOldCopy
     **/ 
    @Override
    public DAOModel getOldCopy(){ 
        return dbCopy;
    } 

    /** 
        Sets the original version of this model object.
        @param aDAOModel the new value for the oldCopy property.
        @see #getOldCopy
     **/ 
    public void setOldCopy(DAOModel aDAOModel){ 
        if (aDAOModel != null) {
        	dbCopy = (AVVALCacheCleanModel)aDAOModel;
        }
        else {
        	dbCopy = null;
        }
    } 
}