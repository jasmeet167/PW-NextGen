package com.csc.fsg.life.avm.biz.dao.model;

import java.util.ArrayList;

import com.csc.fsg.life.dao.model.DAOModel;
import com.csc.fsg.life.dao.model.ModelColumn;

/* Modifications: T0175 */

/** 
    Table Model data transfer object GetPageIdInfoModel.
 **/ 
public class GetPageIdInfoModel 
	extends DAOModel 
	implements java.io.Serializable 
{ 
    /** 
        Property applKey for table GetPageIdInfo.
     **/ 
    private String applKey;
    /** 
        Property envrnmntKey for table GetPageIdInfo.
     **/ 
    private String envrnmntKey;
    /** 
        Property pageKey for table GetPageIdInfo.
     **/ 
    private String pageKey;
    /** 
        Property pageId for table GetPageIdInfo.
     **/ 
    private String pageId;
    /** 
        Property pageDesc for table GetPageIdInfo.
     **/ 
    private String pageDesc;
    private GetPageIdInfoModel dbCopy;

    /** 
        Returns property applKey for this object.
        @return the property applKey for this object.
        @see #setApplKey
     **/ 
    public String getApplKey(){ 
        return this.applKey;
    } 
    /** 
        Sets the property applKey for this object.
        @param aString the new value for this property.
        @see #getApplKey
     **/ 
    public void setApplKey(String aString){ 
        applKey = aString;
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
        Returns property pageKey for this object.
        @return the property pageKey for this object.
        @see #setPageKey
     **/ 
    public String getPageKey(){ 
        return this.pageKey;
    } 
    /** 
        Sets the property pageKey for this object.
        @param aString the new value for this property.
        @see #getPageKey
     **/ 
    public void setPageKey(String aString){ 
        pageKey = aString;
    } 

    /** 
        Returns property pageId for this object.
        @return the property pageId for this object.
        @see #setPageId
     **/ 
    public String getPageId(){ 
        return this.pageId;
    } 
    /** 
        Sets the property pageId for this object.
        @param aString the new value for this property.
        @see #getPageId
     **/ 
    public void setPageId(String aString){ 
        pageId = aString;
    } 

    /** 
        Returns property pageDesc for this object.
        @return the property pageDesc for this object.
        @see #setPageDesc
     **/ 
    public String getPageDesc(){ 
        return this.pageDesc;
    } 
    /** 
        Sets the property pageDesc for this object.
        @param aString the new value for this property.
        @see #getPageDesc
     **/ 
    public void setPageDesc(String aString){ 
        pageDesc = aString;
    } 

    /** 
        Returns property dbCopy for this object.
        @return the property dbCopy for this object.
        @see #setDbCopy
     **/ 
    public GetPageIdInfoModel getDbCopy(){ 
        return this.dbCopy;
    } 
    /** 
        Sets the property dbCopy for this object.
        @param aGetPageIdInfoModel the new value for this property.
        @see #getDbCopy
     **/ 
    public void setDbCopy(GetPageIdInfoModel aGetPageIdInfoModel){ 
        dbCopy = aGetPageIdInfoModel;
    } 
    /** 
        Default constructor for GetPageIdInfoModel.
     **/ 
    public  GetPageIdInfoModel(){ 
        super();
        dbCopy = null;
    } 

    /** 
        Constructor for GetPageIdInfoModel with state.
        @param aint the state of this model object.
     **/ 
    public  GetPageIdInfoModel(int aint){ 
        super(aint);
        dbCopy = null;
    } 

    /** 
        Returns the model name.
        @return the model name.
     **/ 
    @Override
    public String getModelName(){ 
        return "com.csc.fsg.life.avm.biz.dao.model.GetPageIdInfoModel";
    } 

    /** 
        Returns the table name GetPageIdInfo.
        @return the table name.
     **/ 
    public String getTableName(){ 
        return "GetPageIdInfo";
    } 

    /** 
        Returns a new instance of this class.
        @return the new instance.
     **/ 
    @Override
    public DAOModel createNew(){ 
        return new GetPageIdInfoModel();
    } 

    /** 
        Returns a clone of this instance.
        @return the clone instance.
     **/ 
    public DAOModel createCopy(){ 
        GetPageIdInfoModel newModel = new GetPageIdInfoModel();
        newModel.setApplKey(this.getApplKey());
        newModel.setEnvrnmntKey(this.getEnvrnmntKey());
        newModel.setPageKey(this.getPageKey());
        newModel.setPageId(this.getPageId());
        newModel.setPageDesc(this.getPageDesc());
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
        if (this.getApplKey() != null) { 
             if (!this.getApplKey().equals(dbCopy.getApplKey()))  return true;
        }
         else {
             if (dbCopy.getApplKey() != null)  return true;
        }
        if (this.getEnvrnmntKey() != null) { 
             if (!this.getEnvrnmntKey().equals(dbCopy.getEnvrnmntKey()))  return true;
        }
         else {
             if (dbCopy.getEnvrnmntKey() != null)  return true;
        }
        if (this.getPageKey() != null) { 
             if (!this.getPageKey().equals(dbCopy.getPageKey()))  return true;
        }
         else {
             if (dbCopy.getPageKey() != null)  return true;
        }
        if (this.getPageId() != null) { 
             if (!this.getPageId().equals(dbCopy.getPageId()))  return true;
        }
         else {
             if (dbCopy.getPageId() != null)  return true;
        }
        if (this.getPageDesc() != null) { 
             if (!this.getPageDesc().equals(dbCopy.getPageDesc()))  return true;
        }
         else {
             if (dbCopy.getPageDesc() != null)  return true;
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
        GetPageIdInfoModel aGetPageIdInfoModel = (GetPageIdInfoModel)aObject;
        if (this.getApplKey() != null) { 
            if (!this.getApplKey().equals(aGetPageIdInfoModel.getApplKey()))  return false;
        }
         else {
            if (aGetPageIdInfoModel.getApplKey() != null)  return false;
        }
        if (this.getEnvrnmntKey() != null) { 
            if (!this.getEnvrnmntKey().equals(aGetPageIdInfoModel.getEnvrnmntKey()))  return false;
        }
         else {
            if (aGetPageIdInfoModel.getEnvrnmntKey() != null)  return false;
        }
        if (this.getPageKey() != null) { 
            if (!this.getPageKey().equals(aGetPageIdInfoModel.getPageKey()))  return false;
        }
         else {
            if (aGetPageIdInfoModel.getPageKey() != null)  return false;
        }
        if (this.getPageId() != null) { 
            if (!this.getPageId().equals(aGetPageIdInfoModel.getPageId()))  return false;
        }
         else {
            if (aGetPageIdInfoModel.getPageId() != null)  return false;
        }
        if (this.getPageDesc() != null) { 
            if (!this.getPageDesc().equals(aGetPageIdInfoModel.getPageDesc()))  return false;
        }
         else {
            if (aGetPageIdInfoModel.getPageDesc() != null)  return false;
        }
        return true;
    } 

	@Override
	public int hashCode()
	{
		StringBuilder buf = new StringBuilder();
		buf.append(getApplKey());
		buf.append(getEnvrnmntKey());
		buf.append(getPageKey());
		buf.append(getPageId());
		buf.append(getPageDesc());
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
           if (this.getApplKey() == null)  return false;
           if (this.getEnvrnmntKey() == null)  return false;
           if (this.getPageKey() == null)  return false;
           if (this.getPageId() == null)  return false;
           if (this.getPageDesc() == null)  return false;
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
        sb.append ("ApplKey="+this.getApplKey()+"  ");
        sb.append ("EnvrnmntKey="+this.getEnvrnmntKey()+"  ");
        sb.append ("PageKey="+this.getPageKey()+"  ");
        sb.append ("PageId="+this.getPageId()+"  ");
        sb.append ("PageDesc="+this.getPageDesc()+"  ");
        return new String(sb);
    } 

    /** 
        Returns a list of the fields in this object as ModelColumn objects.
        @return a list of the fields in this object as ModelColumn objects.
     **/ 
    public ArrayList<ModelColumn> getAsModelColumns(){ 
        	ArrayList<ModelColumn> retval = new ArrayList<ModelColumn>();
        	ModelColumn modelColumn = new ModelColumn("APPL_KEY");
        	if (dbCopy != null) {
        		if (dbCopy.getApplKey() != null) {
        			modelColumn.setOldData(dbCopy.getApplKey().toString());
        		}
        	}
        	if (getApplKey() != null) {
        		modelColumn.setNewData(getApplKey().toString());
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
        	modelColumn = new ModelColumn("PAGE_KEY");
        	if (dbCopy != null) {
        		if (dbCopy.getPageKey() != null) {
        			modelColumn.setOldData(dbCopy.getPageKey().toString());
        		}
        	}
        	if (getPageKey() != null) {
        		modelColumn.setNewData(getPageKey().toString());
        	}
        	retval.add(modelColumn);
        	modelColumn = new ModelColumn("PAGE_ID");
        	if (dbCopy != null) {
        		if (dbCopy.getPageId() != null) {
        			modelColumn.setOldData(dbCopy.getPageId().toString());
        		}
        	}
        	if (getPageId() != null) {
        		modelColumn.setNewData(getPageId().toString());
        	}
        	retval.add(modelColumn);
        	modelColumn = new ModelColumn("PAGE_DESC");
        	if (dbCopy != null) {
        		if (dbCopy.getPageDesc() != null) {
        			modelColumn.setOldData(dbCopy.getPageDesc().toString());
        		}
        	}
        	if (getPageDesc() != null) {
        		modelColumn.setNewData(getPageDesc().toString());
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
        	dbCopy = (GetPageIdInfoModel)aDAOModel;
        }
        else {
        	dbCopy = null;
        }
    } 
}