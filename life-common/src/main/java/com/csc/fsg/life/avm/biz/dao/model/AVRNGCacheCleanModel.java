package com.csc.fsg.life.avm.biz.dao.model;

import java.util.ArrayList;

import com.csc.fsg.life.dao.model.DAOModel;
import com.csc.fsg.life.dao.model.ModelColumn;

/* Modifications: T0175 */

/** 
    Table Model data transfer object AVRNGCacheCleanModel.
 **/ 
public class AVRNGCacheCleanModel 
	extends DAOModel 
	implements java.io.Serializable 
{ 
    /** 
        Property fieldKey for table AVRNGCacheClean.
     **/ 
    private String fieldKey;
    private AVRNGCacheCleanModel dbCopy;

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
    public AVRNGCacheCleanModel getDbCopy(){ 
        return this.dbCopy;
    } 
    /** 
        Sets the property dbCopy for this object.
        @param aAVRNGCacheCleanModel the new value for this property.
        @see #getDbCopy
     **/ 
    public void setDbCopy(AVRNGCacheCleanModel aAVRNGCacheCleanModel){ 
        dbCopy = aAVRNGCacheCleanModel;
    } 
    /** 
        Default constructor for AVRNGCacheCleanModel.
     **/ 
    public  AVRNGCacheCleanModel(){ 
        super();
        dbCopy = null;
    } 

    /** 
        Constructor for AVRNGCacheCleanModel with state.
        @param aint the state of this model object.
     **/ 
    public  AVRNGCacheCleanModel(int aint){ 
        super(aint);
        dbCopy = null;
    } 

    /** 
        Returns the model name.
        @return the model name.
     **/ 
    @Override
    public String getModelName(){ 
        return "com.csc.fsg.life.avm.biz.dao.model.AVRNGCacheCleanModel";
    } 

    /** 
        Returns the table name AVRNGCacheClean.
        @return the table name.
     **/ 
    public String getTableName(){ 
        return "AVRNGCacheClean";
    } 

    /** 
        Returns a new instance of this class.
        @return the new instance.
     **/ 
    @Override
    public DAOModel createNew(){ 
        return new AVRNGCacheCleanModel();
    } 

    /** 
        Returns a clone of this instance.
        @return the clone instance.
     **/ 
    public DAOModel createCopy(){ 
        AVRNGCacheCleanModel newModel = new AVRNGCacheCleanModel();
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
        AVRNGCacheCleanModel aAVRNGCacheCleanModel = (AVRNGCacheCleanModel)aObject;
        if (this.getFieldKey() != null) { 
            if (!this.getFieldKey().equals(aAVRNGCacheCleanModel.getFieldKey()))  return false;
        }
         else {
            if (aAVRNGCacheCleanModel.getFieldKey() != null)  return false;
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
        	dbCopy = (AVRNGCacheCleanModel)aDAOModel;
        }
        else {
        	dbCopy = null;
        }
    } 
}