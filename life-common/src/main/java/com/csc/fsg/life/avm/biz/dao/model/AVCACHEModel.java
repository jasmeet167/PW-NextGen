package com.csc.fsg.life.avm.biz.dao.model;

import java.sql.Timestamp;
import java.util.ArrayList;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import com.csc.fsg.life.avm.biz.dao.dataaccessor.AVCACHEDAO;
import com.csc.fsg.life.dao.dataaccessor.DataAccessor;
import com.csc.fsg.life.dao.model.DAOModel;
import com.csc.fsg.life.dao.model.ModelColumn;

/* Modifications: T0175 */

/** 
    Table Model data transfer object AVCACHEModel.
 **/ 
public class AVCACHEModel 
	extends DAOModel 
	implements java.io.Serializable 
{ 
    /** 
        Property applKey for table AVCACHE.
     **/ 
    private String applKey;
    /** 
        Property cacheind for table AVCACHE.
     **/ 
    private Long cacheind;
    /** 
        Property timeStamp for table AVCACHE.
     **/ 
    private Timestamp timeStamp;
    private AVCACHEModel dbCopy;

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
        Returns property cacheind for this object.
        @return the property cacheind for this object.
	   @see #setCacheind
	**/
    public Long getCacheind(){ 
        return this.cacheind;
    } 
	/**
        Sets the property cacheind for this object.
        @param aLong the new value for this property.
	   @see #getCacheind
	 **/
    public void setCacheind(Long aLong){ 
        cacheind = aLong;
    } 

	/**
        Returns property timeStamp for this object.
        @return the property timeStamp for this object.
	   @see #setTimeStamp
	**/
    public Timestamp getTimeStamp(){ 
        return this.timeStamp;
    } 
	/**
        Sets the property timeStamp for this object.
        @param aTimestamp the new value for this property.
	   @see #getTimeStamp
	 **/
    public void setTimeStamp(Timestamp aTimestamp){ 
        timeStamp = aTimestamp;
    } 

	/**
        Returns property dbCopy for this object.
        @return the property dbCopy for this object.
	   @see #setDbCopy
	**/
    public AVCACHEModel getDbCopy(){ 
        return this.dbCopy;
    } 
	/**
        Sets the property dbCopy for this object.
        @param aAVCACHEModel the new value for this property.
	   @see #getDbCopy
	 **/
    public void setDbCopy(AVCACHEModel aAVCACHEModel){ 
        dbCopy = aAVCACHEModel;
    } 
    /** 
        Default constructor for AVCACHEModel.
     **/ 
    public  AVCACHEModel(){ 
        super();
        dbCopy = null;
    } 

    /** 
        Constructor for AVCACHEModel with state.
        @param aint the state of this model object.
     **/ 
    public  AVCACHEModel(int aint){ 
        super(aint);
        dbCopy = null;
    } 

	/**
        Returns the model name.
        @return the model name.
	**/
    @Override
    public String getModelName(){ 
        return "com.csc.fsg.life.avm.biz.dao.model.AVCACHEModel";
    } 

	/**
        Returns the table name AVCACHE.
        @return the table name.
	**/
    public String getTableName(){ 
        return "AVCACHE";
    } 

    /** 
        Returns a new instance of this class.
        @return the new instance.
     **/ 
    @Override
    public DAOModel createNew(){ 
        return new AVCACHEModel();
    } 

    /** 
        Returns a clone of this instance.
        @return the clone instance.
     **/ 
    public DAOModel createCopy(){ 
        AVCACHEModel newModel = new AVCACHEModel();
        newModel.setApplKey(this.getApplKey());
        newModel.setCacheind(this.getCacheind());
        newModel.setTimeStamp(this.getTimeStamp());
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
        if (this.getCacheind() != null) { 
             if (!this.getCacheind().equals(dbCopy.getCacheind()))  return true;
        }
         else {
             if (dbCopy.getCacheind() != null)  return true;
        }
        if (this.getTimeStamp() != null) { 
             if (!this.getTimeStamp().equals(dbCopy.getTimeStamp()))  return true;
        }
         else {
             if (dbCopy.getTimeStamp() != null)  return true;
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
        AVCACHEModel aAVCACHEModel = (AVCACHEModel)aObject;
        if (this.getApplKey() != null) { 
            if (!this.getApplKey().equals(aAVCACHEModel.getApplKey()))  return false;
        }
         else {
            if (aAVCACHEModel.getApplKey() != null)  return false;
        }
        if (this.getCacheind() != null) { 
            if (!this.getCacheind().equals(aAVCACHEModel.getCacheind()))  return false;
        }
         else {
            if (aAVCACHEModel.getCacheind() != null)  return false;
        }
        if (this.getTimeStamp() != null) { 
            if (!this.getTimeStamp().equals(aAVCACHEModel.getTimeStamp()))  return false;
        }
         else {
            if (aAVCACHEModel.getTimeStamp() != null)  return false;
        }
        return true;
    } 

	@Override
	public int hashCode()
	{
		StringBuilder buf = new StringBuilder();
		buf.append(getApplKey());
		buf.append(getCacheind());
		buf.append(getTimeStamp());
		return buf.toString().hashCode();
	}

    /** 
        Returns true if any key field in this object has changed since being read from the DB.
        @return the true if changed, false if not.
     **/ 
    @Override
    public boolean hasKeysChanged(){ 
        if (dbCopy == null)  return true;
        if (this.getApplKey() != null) { 
           if (!this.getApplKey().equals( dbCopy.getApplKey()))  return true;
        }
         else {
           if (dbCopy.getApplKey() != null)  return true;
        }
        return false;
    } 

    /** 
        Returns true if any key field in this object is different than the key fields in the specified object.
        @param aObject The object to compare with.
        @return the true if changed, false if not.
     **/ 
    @Override
    public boolean hasKeysChanged(Object aObject){ 
        AVCACHEModel aAVCACHEModel = (AVCACHEModel)aObject;
        if (this.getApplKey() != null) { 
            if (!this.getApplKey().equals(aAVCACHEModel.getApplKey()))  return true;
        }
         else {
             if (aAVCACHEModel.getApplKey() != null)  return true;
        }
        return false;
    } 

    /** 
        Returns true if all required fields are non-null.
        @return the true all required fields are non-null, false if not.
     **/ 
    public boolean isRequiredFieldsNotNull(){ 
           if (this.getApplKey() == null)  return false;
           if (this.getCacheind() == null)  return false;
        return true;
    } 

    /** 
        Returns true if all primary key fields are non-null.
        @return the true all primary key fields are non-null, false if not.
     **/ 
    public boolean isPrimaryKeysNotNull(){ 
        if (this.getApplKey() == null)  return false;
        return true;
    } 

    @Override
    public String toString(){ 
        StringBuffer sb = new StringBuffer();
        sb.append ("ApplKey="+this.getApplKey()+"  ");
        sb.append ("Cacheind="+this.getCacheind()+"  ");
        sb.append ("TimeStamp="+this.getTimeStamp()+"  ");
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
        	modelColumn = new ModelColumn("CACHEIND");
        	if (dbCopy != null) {
        		if (dbCopy.getCacheind() != null) {
        			modelColumn.setOldData(dbCopy.getCacheind().toString());
        		}
        	}
        	if (getCacheind() != null) {
        		modelColumn.setNewData(getCacheind().toString());
        	}
        	retval.add(modelColumn);
        	modelColumn = new ModelColumn("TIME_STAMP");
        	if (dbCopy != null) {
        		if (dbCopy.getTimeStamp() != null) {
        			modelColumn.setOldData(dbCopy.getTimeStamp().toString());
        		}
        	}
        	if (getTimeStamp() != null) {
        		modelColumn.setNewData(getTimeStamp().toString());
        	}
        	retval.add(modelColumn);
        return retval;
    } 

    /** 
        Returns a DOM Element for this object.
        @param aDocument The document used to create elements.
        @return a DOM Element for this object.
     **/ 
    @Override
    public Element toXML(Document aDocument){ 
        Element element = aDocument.createElement("AVCACHEModel");
        element.setAttribute("applKey", toSafeString(this.getApplKey()));
        element.setAttribute("cacheind", toSafeString(this.getCacheind()));
        element.setAttribute("timeStamp", toSafeString(this.getTimeStamp()));
        return element;
    } 

    /** 
        Returns an instance of this model created from data in a  DOM Element.
        @param aElement The DOM Element to parse into this model.
        @return an instance of this model created from data in a  DOM Element.
     **/ 
    @Override
    public DAOModel fromXML(Element aElement){ 
        AVCACHEModel aVCACHEModel = new AVCACHEModel();
        aVCACHEModel.setApplKey(aElement.getAttribute("applKey"));
        aVCACHEModel.setCacheind(toLong(aElement.getAttribute("cacheind")));
        return aVCACHEModel;
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
        	dbCopy = (AVCACHEModel)aDAOModel;
        }
        else
        {
        	dbCopy = null;
        }
    } 

    /** 
        Returns an instance of the DAO access class (AVCACHEDAO) for this model.
        @return an instance of the DAO access class for this model.
     **/ 
    @Override
    public DataAccessor getDataAccessorObject(){ 
        return new AVCACHEDAO();
    } 
}
