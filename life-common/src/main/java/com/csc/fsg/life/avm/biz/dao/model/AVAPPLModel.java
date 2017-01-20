package com.csc.fsg.life.avm.biz.dao.model;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.ArrayList;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import com.csc.fsg.life.avm.biz.dao.dataaccessor.AVAPPLDAO;
import com.csc.fsg.life.dao.dataaccessor.DataAccessor;
import com.csc.fsg.life.dao.model.DAOModel;
import com.csc.fsg.life.dao.model.ModelColumn;

/* Modifications: T0175 */

/** 
    Table Model data transfer object AVAPPLModel.
 **/ 
public class AVAPPLModel 
	extends DAOModel 
	implements java.io.Serializable 
{ 

    /** 
        Property applKeyfor table AVAPPL.
     **/ 
    private String applKey;
    /** 
        Property applIdfor table AVAPPL.
     **/ 
    private String applId;
    /** 
        Property applDescfor table AVAPPL.
     **/ 
    private String applDesc;
    /** 
        Property lstChangedByfor table AVAPPL.
     **/ 
    private String lstChangedBy;
    /** 
        Property lstChangeDatefor table AVAPPL.
     **/ 
    private Date lstChangeDate;
    /** 
        Property timeStampfor table AVAPPL.
     **/ 
    private Timestamp timeStamp;
    private AVAPPLModel dbCopy;

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
        Returns property applId for this object.
        @return the property applId for this object.
	   @see #setApplId
	**/
    public String getApplId(){ 
        return this.applId;
    } 
	/**
        Sets the property applId for this object.
        @param aString the new value for this property.
	   @see #getApplId
	 **/
    public void setApplId(String aString){ 
        applId = aString;
    } 

	/**
        Returns property applDesc for this object.
        @return the property applDesc for this object.
	   @see #setApplDesc
	**/
    public String getApplDesc(){ 
        return this.applDesc;
    } 
	/**
        Sets the property applDesc for this object.
        @param aString the new value for this property.
	   @see #getApplDesc
	 **/
    public void setApplDesc(String aString){ 
        applDesc = aString;
    } 

	/**
        Returns property lstChangedBy for this object.
        @return the property lstChangedBy for this object.
	   @see #setLstChangedBy
	**/
    public String getLstChangedBy(){ 
        return this.lstChangedBy;
    } 
	/**
        Sets the property lstChangedBy for this object.
        @param aString the new value for this property.
	   @see #getLstChangedBy
	 **/
    public void setLstChangedBy(String aString){ 
        lstChangedBy = aString;
    } 

	/**
        Returns property lstChangeDate for this object.
        @return the property lstChangeDate for this object.
	   @see #setLstChangeDate
	**/
    public Date getLstChangeDate(){ 
        return this.lstChangeDate;
    } 
	/**
        Sets the property lstChangeDate for this object.
        @param aDate the new value for this property.
	   @see #getLstChangeDate
	 **/
    public void setLstChangeDate(Date aDate){ 
        lstChangeDate = aDate;
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
    public AVAPPLModel getDbCopy(){ 
        return this.dbCopy;
    } 
	/**
        Sets the property dbCopy for this object.
        @param aAVAPPLModel the new value for this property.
	   @see #getDbCopy
	 **/
    public void setDbCopy(AVAPPLModel aAVAPPLModel){ 
        dbCopy = aAVAPPLModel;
    } 
    /** 
        Default constructor for AVAPPLModel.
     **/ 
    public  AVAPPLModel(){ 
        super();
        dbCopy = null;
    } 

    /** 
        Constructor for AVAPPLModel with state.
        @param aint the state of this model object.
     **/ 
    public  AVAPPLModel(int aint){ 
        super(aint);
        dbCopy = null;
    } 

	/**
        Returns the model name.
        @return the model name.
	**/
    @Override
    public String getModelName(){ 
        return "com.csc.fsg.life.avm.biz.dao.model.AVAPPLModel";
    } 

	/**
        Returns the table name AVAPPL.
        @return the table name.
	**/
    public String getTableName(){ 
        return "AVAPPL";
    } 

    /** 
        Returns a new instance of this class.
        @return the new instance.
     **/ 
    @Override
    public DAOModel createNew(){ 
        return new AVAPPLModel();
    } 

    /** 
        Returns a clone of this instance.
        @return the clone instance.
     **/ 
    public DAOModel createCopy(){ 
        AVAPPLModel newModel = new AVAPPLModel();
        newModel.setApplKey(this.getApplKey());
        newModel.setApplId(this.getApplId());
        newModel.setApplDesc(this.getApplDesc());
        newModel.setLstChangedBy(this.getLstChangedBy());
        newModel.setLstChangeDate(this.getLstChangeDate());
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
        if (this.getApplId() != null) { 
             if (!this.getApplId().equals(dbCopy.getApplId()))  return true;
        }
         else {
             if (dbCopy.getApplId() != null)  return true;
        }
        if (this.getApplDesc() != null) { 
             if (!this.getApplDesc().equals(dbCopy.getApplDesc()))  return true;
        }
         else {
             if (dbCopy.getApplDesc() != null)  return true;
        }
        if (this.getLstChangedBy() != null) { 
             if (!this.getLstChangedBy().equals(dbCopy.getLstChangedBy()))  return true;
        }
         else {
             if (dbCopy.getLstChangedBy() != null)  return true;
        }
        if (this.getLstChangeDate() != null) { 
             if (!this.getLstChangeDate().equals(dbCopy.getLstChangeDate()))  return true;
        }
         else {
             if (dbCopy.getLstChangeDate() != null)  return true;
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
        AVAPPLModel aAVAPPLModel = (AVAPPLModel)aObject;
        if (this.getApplKey() != null) { 
            if (!this.getApplKey().equals(aAVAPPLModel.getApplKey()))  return false;
        }
         else {
            if (aAVAPPLModel.getApplKey() != null)  return false;
        }
        if (this.getApplId() != null) { 
            if (!this.getApplId().equals(aAVAPPLModel.getApplId()))  return false;
        }
         else {
            if (aAVAPPLModel.getApplId() != null)  return false;
        }
        if (this.getApplDesc() != null) { 
            if (!this.getApplDesc().equals(aAVAPPLModel.getApplDesc()))  return false;
        }
         else {
            if (aAVAPPLModel.getApplDesc() != null)  return false;
        }
        if (this.getLstChangedBy() != null) { 
            if (!this.getLstChangedBy().equals(aAVAPPLModel.getLstChangedBy()))  return false;
        }
         else {
            if (aAVAPPLModel.getLstChangedBy() != null)  return false;
        }
        if (this.getLstChangeDate() != null) { 
            if (!this.getLstChangeDate().equals(aAVAPPLModel.getLstChangeDate()))  return false;
        }
         else {
            if (aAVAPPLModel.getLstChangeDate() != null)  return false;
        }
        if (this.getTimeStamp() != null) { 
            if (!this.getTimeStamp().equals(aAVAPPLModel.getTimeStamp()))  return false;
        }
         else {
            if (aAVAPPLModel.getTimeStamp() != null)  return false;
        }
        return true;
    } 

	@Override
	public int hashCode()
	{
		StringBuilder buf = new StringBuilder();
		buf.append(getApplKey());
		buf.append(getApplId());
		buf.append(getApplDesc());
		buf.append(getLstChangedBy());
		buf.append(getLstChangeDate());
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
        AVAPPLModel aAVAPPLModel = (AVAPPLModel)aObject;
        if (this.getApplKey() != null) { 
            if (!this.getApplKey().equals(aAVAPPLModel.getApplKey()))  return true;
        }
         else {
             if (aAVAPPLModel.getApplKey() != null)  return true;
        }
        return false;
    } 

    /** 
        Returns true if all required fields are non-null.
        @return the true all required fields are non-null, false if not.
     **/ 
    public boolean isRequiredFieldsNotNull(){ 
           if (this.getApplKey() == null)  return false;
           if (this.getApplId() == null)  return false;
           if (this.getApplDesc() == null)  return false;
           if (this.getLstChangedBy() == null)  return false;
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
        sb.append ("ApplId="+this.getApplId()+"  ");
        sb.append ("ApplDesc="+this.getApplDesc()+"  ");
        sb.append ("LstChangedBy="+this.getLstChangedBy()+"  ");
        sb.append ("LstChangeDate="+this.getLstChangeDate()+"  ");
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
        	modelColumn = new ModelColumn("APPL_ID");
        	if (dbCopy != null) {
        		if (dbCopy.getApplId() != null) {
        			modelColumn.setOldData(dbCopy.getApplId().toString());
        		}
        	}
        	if (getApplId() != null) {
        		modelColumn.setNewData(getApplId().toString());
        	}
        	retval.add(modelColumn);
        	modelColumn = new ModelColumn("APPL_DESC");
        	if (dbCopy != null) {
        		if (dbCopy.getApplDesc() != null) {
        			modelColumn.setOldData(dbCopy.getApplDesc().toString());
        		}
        	}
        	if (getApplDesc() != null) {
        		modelColumn.setNewData(getApplDesc().toString());
        	}
        	retval.add(modelColumn);
        	modelColumn = new ModelColumn("LST_CHANGED_BY");
        	if (dbCopy != null) {
        		if (dbCopy.getLstChangedBy() != null) {
        			modelColumn.setOldData(dbCopy.getLstChangedBy().toString());
        		}
        	}
        	if (getLstChangedBy() != null) {
        		modelColumn.setNewData(getLstChangedBy().toString());
        	}
        	retval.add(modelColumn);
        	modelColumn = new ModelColumn("LST_CHANGE_DATE");
        	if (dbCopy != null) {
        		if (dbCopy.getLstChangeDate() != null) {
        			modelColumn.setOldData(dbCopy.getLstChangeDate().toString());
        		}
        	}
        	if (getLstChangeDate() != null) {
        		modelColumn.setNewData(getLstChangeDate().toString());
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
        Element element = aDocument.createElement("AVAPPLModel");
        element.setAttribute("applKey", toSafeString(this.getApplKey()));
        element.setAttribute("applId", toSafeString(this.getApplId()));
        element.setAttribute("applDesc", toSafeString(this.getApplDesc()));
        element.setAttribute("lstChangedBy", toSafeString(this.getLstChangedBy()));
        element.setAttribute("lstChangeDate", toSafeString(this.getLstChangeDate()));
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
        AVAPPLModel aVAPPLModel = new AVAPPLModel();
        aVAPPLModel.setApplKey(aElement.getAttribute("applKey"));
        aVAPPLModel.setApplId(aElement.getAttribute("applId"));
        aVAPPLModel.setApplDesc(aElement.getAttribute("applDesc"));
        aVAPPLModel.setLstChangedBy(aElement.getAttribute("lstChangedBy"));
        aVAPPLModel.setLstChangeDate(toDate(aElement.getAttribute("lstChangeDate")));
        return aVAPPLModel;
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
        	dbCopy = (AVAPPLModel)aDAOModel;
        }
        else
        {
        	dbCopy = null;
        }
    } 

    /** 
        Returns an instance of the DAO access class (AVAPPLDAO) for this model.
        @return an instance of the DAO access class for this model.
     **/ 
    @Override
    public DataAccessor getDataAccessorObject(){ 
        return new AVAPPLDAO();
    } 
}
