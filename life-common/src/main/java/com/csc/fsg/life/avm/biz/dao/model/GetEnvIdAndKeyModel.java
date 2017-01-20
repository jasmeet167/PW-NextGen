package com.csc.fsg.life.avm.biz.dao.model;

import java.util.ArrayList;

import com.csc.fsg.life.dao.model.DAOModel;
import com.csc.fsg.life.dao.model.ModelColumn;

/* Modifications: T0175 */

    /** 
        Table Model data transfer object GetEnvIdAndKeyModel.
     **/ 
public class GetEnvIdAndKeyModel 
	extends DAOModel 
	implements java.io.Serializable 
{ 
    /** 
        Property envrnmntId for table GetEnvIdAndKey.
     **/ 
    private String envrnmntId;
    /** 
        Property envrnmntKey for table GetEnvIdAndKey.
     **/ 
    private String envrnmntKey;
    private GetEnvIdAndKeyModel dbCopy;

    /** 
        Returns property envrnmntId for this object.
        @return the property envrnmntId for this object.
        @see #setEnvrnmntId
     **/ 
    public String getEnvrnmntId(){ 
        return this.envrnmntId;
    } 
    /** 
        Sets the property envrnmntId for this object.
        @param aString the new value for this property.
        @see #getEnvrnmntId
     **/ 
    public void setEnvrnmntId(String aString){ 
        envrnmntId = aString;
    } 

    /** 
        Returns property envrnmntKey for this object.
        @return the property envrnmntKey for this object.
        @see #setEnvrnmntKey
     **/ 
    public String getEnvrnmntKey(){ 
        return this.envrnmntKey;
    } 
    /** 
        Sets the property envrnmntKey for this object.
        @param aString the new value for this property.
        @see #getEnvrnmntKey
     **/ 
    public void setEnvrnmntKey(String aString){ 
        envrnmntKey = aString;
    } 

    /** 
        Returns property dbCopy for this object.
        @return the property dbCopy for this object.
        @see #setDbCopy
     **/ 
    public GetEnvIdAndKeyModel getDbCopy(){ 
        return this.dbCopy;
    } 
    /** 
        Sets the property dbCopy for this object.
        @param aGetEnvIdAndKeyModel the new value for this property.
        @see #getDbCopy
     **/ 
    public void setDbCopy(GetEnvIdAndKeyModel aGetEnvIdAndKeyModel){ 
        dbCopy = aGetEnvIdAndKeyModel;
    } 
    /** 
        Default constructor for GetEnvIdAndKeyModel.
     **/ 
    public  GetEnvIdAndKeyModel(){ 
        super();
        dbCopy = null;
    } 

    /** 
        Constructor for GetEnvIdAndKeyModel with state.
        @param aint the state of this model object.
     **/ 
    public  GetEnvIdAndKeyModel(int aint){ 
        super(aint);
        dbCopy = null;
    } 

    /** 
        Returns the model name.
        @return the model name.
     **/ 
    @Override
    public String getModelName(){ 
        return "com.csc.fsg.life.avm.biz.dao.model.GetEnvIdAndKeyModel";
    } 

    /** 
        Returns the table name GetEnvIdAndKey.
        @return the table name.
     **/ 
    public String getTableName(){ 
        return "GetEnvIdAndKey";
    } 

    /** 
        Returns a new instance of this class.
        @return the new instance.
     **/ 
    @Override
    public DAOModel createNew(){ 
        return new GetEnvIdAndKeyModel();
    } 

    /** 
        Returns a clone of this instance.
        @return the clone instance.
     **/ 
    public DAOModel createCopy(){ 
        GetEnvIdAndKeyModel newModel = new GetEnvIdAndKeyModel();
        newModel.setEnvrnmntId(this.getEnvrnmntId());
        newModel.setEnvrnmntKey(this.getEnvrnmntKey());
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
        if (this.getEnvrnmntId() != null) { 
             if (!this.getEnvrnmntId().equals(dbCopy.getEnvrnmntId()))  return true;
        }
         else {
             if (dbCopy.getEnvrnmntId() != null)  return true;
        }
        if (this.getEnvrnmntKey() != null) { 
             if (!this.getEnvrnmntKey().equals(dbCopy.getEnvrnmntKey()))  return true;
        }
         else {
             if (dbCopy.getEnvrnmntKey() != null)  return true;
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
        GetEnvIdAndKeyModel aGetEnvIdAndKeyModel = (GetEnvIdAndKeyModel)aObject;
        if (this.getEnvrnmntId() != null) { 
            if (!this.getEnvrnmntId().equals(aGetEnvIdAndKeyModel.getEnvrnmntId()))  return false;
        }
         else {
            if (aGetEnvIdAndKeyModel.getEnvrnmntId() != null)  return false;
        }
        if (this.getEnvrnmntKey() != null) { 
            if (!this.getEnvrnmntKey().equals(aGetEnvIdAndKeyModel.getEnvrnmntKey()))  return false;
        }
         else {
            if (aGetEnvIdAndKeyModel.getEnvrnmntKey() != null)  return false;
        }
        return true;
    } 

	@Override
	public int hashCode()
	{
		StringBuilder buf = new StringBuilder();
		buf.append(getEnvrnmntId());
		buf.append(getEnvrnmntKey());
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
           if (this.getEnvrnmntId() == null)  return false;
           if (this.getEnvrnmntKey() == null)  return false;
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
        sb.append ("EnvrnmntId="+this.getEnvrnmntId()+"  ");
        sb.append ("EnvrnmntKey="+this.getEnvrnmntKey()+"  ");
        return new String(sb);
    } 

    /** 
        Returns a list of the fields in this object as ModelColumn objects.
        @return a list of the fields in this object as ModelColumn objects.
     **/ 
    public ArrayList<ModelColumn> getAsModelColumns(){ 
        	ArrayList<ModelColumn> retval = new ArrayList<ModelColumn>();
        	ModelColumn modelColumn = new ModelColumn("ENVRNMNT_ID");
        	if (dbCopy != null) {
        		if (dbCopy.getEnvrnmntId() != null) {
        			modelColumn.setOldData(dbCopy.getEnvrnmntId().toString());
        		}
        	}
        	if (getEnvrnmntId() != null) {
        		modelColumn.setNewData(getEnvrnmntId().toString());
        	}
        	retval.add(modelColumn);
        	modelColumn = new ModelColumn("ENVRNMNT_KEY");
        	if (dbCopy != null) {
        		if (dbCopy.getEnvrnmntKey() != null) {
        			modelColumn.setOldData(dbCopy.getEnvrnmntKey().toString());
        		}
        	}
        	if (getEnvrnmntKey() != null) {
        		modelColumn.setNewData(getEnvrnmntKey().toString());
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
        	dbCopy = (GetEnvIdAndKeyModel)aDAOModel;
        }
        else {
        	dbCopy = null;
        }
    } 
}