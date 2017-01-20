package com.csc.fsg.life.avm.biz.dao.model;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.ArrayList;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import com.csc.fsg.life.avm.biz.dao.dataaccessor.AVVALDAO;
import com.csc.fsg.life.dao.dataaccessor.DataAccessor;
import com.csc.fsg.life.dao.model.DAOModel;
import com.csc.fsg.life.dao.model.ModelColumn;

/* Modifications: T0175 */

/** 
    Table Model data transfer object AVVALModel.
 **/ 
public class AVVALModel 
	extends DAOModel 
	implements java.io.Serializable 
{ 
    /** 
        Property fieldKey for table AVVAL.
     **/ 
    private String fieldKey;
    /** 
        Property allvalKey for table AVVAL.
     **/ 
    private String allvalKey;
    /** 
        Property coreValue for table AVVAL.
     **/ 
    private String coreValue;
    /** 
        Property displayValue for table AVVAL.
     **/ 
    private String displayValue;
    /** 
        Property allvalProject for table AVVAL.
     **/ 
    private String allvalProject;
    /** 
        Property allvalStatus for table AVVAL.
     **/ 
    private String allvalStatus;
    /** 
        Property lstChangedBy for table AVVAL.
     **/ 
    private String lstChangedBy;
    /** 
        Property lstChangeDate for table AVVAL.
     **/ 
    private Date lstChangeDate;
    /** 
        Property timeStamp for table AVVAL.
     **/ 
    private Timestamp timeStamp;
    /** 
        Property cacheind for table AVVAL.
     **/ 
    private Long cacheind;
    private AVVALModel dbCopy;

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
        Returns property allvalKey for this object.
        @return the property allvalKey for this object.
        @see #setAllvalKey
     **/ 
    public String getAllvalKey(){ 
        return this.allvalKey;
    } 
    /** 
        Sets the property allvalKey for this object.
        @param aString the new value for this property.
        @see #getAllvalKey
     **/ 
    public void setAllvalKey(String aString){ 
        allvalKey = aString;
    } 

    /** 
        Returns property coreValue for this object.
        @return the property coreValue for this object.
        @see #setCoreValue
     **/ 
    public String getCoreValue(){ 
        return this.coreValue;
    } 
    /** 
        Sets the property coreValue for this object.
        @param aString the new value for this property.
        @see #getCoreValue
     **/ 
    public void setCoreValue(String aString){ 
        coreValue = aString;
    } 

    /** 
        Returns property displayValue for this object.
        @return the property displayValue for this object.
        @see #setDisplayValue
     **/ 
    public String getDisplayValue(){ 
        return this.displayValue;
    } 
    /** 
        Sets the property displayValue for this object.
        @param aString the new value for this property.
        @see #getDisplayValue
     **/ 
    public void setDisplayValue(String aString){ 
        displayValue = aString;
    } 

    /** 
        Returns property allvalProject for this object.
        @return the property allvalProject for this object.
        @see #setAllvalProject
     **/ 
    public String getAllvalProject(){ 
        return this.allvalProject;
    } 
    /** 
        Sets the property allvalProject for this object.
        @param aString the new value for this property.
        @see #getAllvalProject
     **/ 
    public void setAllvalProject(String aString){ 
        allvalProject = aString;
    } 

    /** 
        Returns property allvalStatus for this object.
        @return the property allvalStatus for this object.
        @see #setAllvalStatus
     **/ 
    public String getAllvalStatus(){ 
        return this.allvalStatus;
    } 
    /** 
        Sets the property allvalStatus for this object.
        @param aString the new value for this property.
        @see #getAllvalStatus
     **/ 
    public void setAllvalStatus(String aString){ 
        allvalStatus = aString;
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
        Returns property dbCopy for this object.
        @return the property dbCopy for this object.
        @see #setDbCopy
     **/ 
    public AVVALModel getDbCopy(){ 
        return this.dbCopy;
    } 
    /** 
        Sets the property dbCopy for this object.
        @param aAVVALModel the new value for this property.
        @see #getDbCopy
     **/ 
    public void setDbCopy(AVVALModel aAVVALModel){ 
        dbCopy = aAVVALModel;
    } 
    /** 
        Default constructor for AVVALModel.
     **/ 
    public  AVVALModel(){ 
        super();
        dbCopy = null;
    } 

    /** 
        Constructor for AVVALModel with state.
        @param aint the state of this model object.
     **/ 
    public  AVVALModel(int aint){ 
        super(aint);
        dbCopy = null;
    } 

    /** 
        Returns the model name.
        @return the model name.
     **/ 
    @Override
    public String getModelName(){ 
        return "com.csc.fsg.life.avm.biz.dao.model.AVVALModel";
    } 

    /** 
        Returns the table name AVVAL.
        @return the table name.
     **/ 
    public String getTableName(){ 
        return "AVVAL";
    } 

    /** 
        Returns a new instance of this class.
        @return the new instance.
     **/ 
    @Override
    public DAOModel createNew(){ 
        return new AVVALModel();
    } 

    /** 
        Returns a clone of this instance.
        @return the clone instance.
     **/ 
    public DAOModel createCopy(){ 
        AVVALModel newModel = new AVVALModel();
        newModel.setFieldKey(this.getFieldKey());
        newModel.setAllvalKey(this.getAllvalKey());
        newModel.setCoreValue(this.getCoreValue());
        newModel.setDisplayValue(this.getDisplayValue());
        newModel.setAllvalProject(this.getAllvalProject());
        newModel.setAllvalStatus(this.getAllvalStatus());
        newModel.setLstChangedBy(this.getLstChangedBy());
        newModel.setLstChangeDate(this.getLstChangeDate());
        newModel.setTimeStamp(this.getTimeStamp());
        newModel.setCacheind(this.getCacheind());
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
        if (this.getAllvalKey() != null) { 
             if (!this.getAllvalKey().equals(dbCopy.getAllvalKey()))  return true;
        }
         else {
             if (dbCopy.getAllvalKey() != null)  return true;
        }
        if (this.getCoreValue() != null) { 
             if (!this.getCoreValue().equals(dbCopy.getCoreValue()))  return true;
        }
         else {
             if (dbCopy.getCoreValue() != null)  return true;
        }
        if (this.getDisplayValue() != null) { 
             if (!this.getDisplayValue().equals(dbCopy.getDisplayValue()))  return true;
        }
         else {
             if (dbCopy.getDisplayValue() != null)  return true;
        }
        if (this.getAllvalProject() != null) { 
             if (!this.getAllvalProject().equals(dbCopy.getAllvalProject()))  return true;
        }
         else {
             if (dbCopy.getAllvalProject() != null)  return true;
        }
        if (this.getAllvalStatus() != null) { 
             if (!this.getAllvalStatus().equals(dbCopy.getAllvalStatus()))  return true;
        }
         else {
             if (dbCopy.getAllvalStatus() != null)  return true;
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
        if (this.getCacheind() != null) { 
             if (!this.getCacheind().equals(dbCopy.getCacheind()))  return true;
        }
         else {
             if (dbCopy.getCacheind() != null)  return true;
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
        AVVALModel aAVVALModel = (AVVALModel)aObject;
        if (this.getFieldKey() != null) { 
            if (!this.getFieldKey().equals(aAVVALModel.getFieldKey()))  return false;
        }
         else {
            if (aAVVALModel.getFieldKey() != null)  return false;
        }
        if (this.getAllvalKey() != null) { 
            if (!this.getAllvalKey().equals(aAVVALModel.getAllvalKey()))  return false;
        }
         else {
            if (aAVVALModel.getAllvalKey() != null)  return false;
        }
        if (this.getCoreValue() != null) { 
            if (!this.getCoreValue().equals(aAVVALModel.getCoreValue()))  return false;
        }
         else {
            if (aAVVALModel.getCoreValue() != null)  return false;
        }
        if (this.getDisplayValue() != null) { 
            if (!this.getDisplayValue().equals(aAVVALModel.getDisplayValue()))  return false;
        }
         else {
            if (aAVVALModel.getDisplayValue() != null)  return false;
        }
        if (this.getAllvalProject() != null) { 
            if (!this.getAllvalProject().equals(aAVVALModel.getAllvalProject()))  return false;
        }
         else {
            if (aAVVALModel.getAllvalProject() != null)  return false;
        }
        if (this.getAllvalStatus() != null) { 
            if (!this.getAllvalStatus().equals(aAVVALModel.getAllvalStatus()))  return false;
        }
         else {
            if (aAVVALModel.getAllvalStatus() != null)  return false;
        }
        if (this.getLstChangedBy() != null) { 
            if (!this.getLstChangedBy().equals(aAVVALModel.getLstChangedBy()))  return false;
        }
         else {
            if (aAVVALModel.getLstChangedBy() != null)  return false;
        }
        if (this.getLstChangeDate() != null) { 
            if (!this.getLstChangeDate().equals(aAVVALModel.getLstChangeDate()))  return false;
        }
         else {
            if (aAVVALModel.getLstChangeDate() != null)  return false;
        }
        if (this.getTimeStamp() != null) { 
            if (!this.getTimeStamp().equals(aAVVALModel.getTimeStamp()))  return false;
        }
         else {
            if (aAVVALModel.getTimeStamp() != null)  return false;
        }
        if (this.getCacheind() != null) { 
            if (!this.getCacheind().equals(aAVVALModel.getCacheind()))  return false;
        }
         else {
            if (aAVVALModel.getCacheind() != null)  return false;
        }
        return true;
    } 

	@Override
	public int hashCode()
	{
		StringBuilder buf = new StringBuilder();
		buf.append(getFieldKey());
		buf.append(getAllvalKey());
		buf.append(getCoreValue());
		buf.append(getDisplayValue());
		buf.append(getAllvalProject());
		buf.append(getAllvalStatus());
		buf.append(getLstChangedBy());
		buf.append(getLstChangeDate());
		buf.append(getTimeStamp());
		buf.append(getCacheind());
		return buf.toString().hashCode();
	}

    /** 
        Returns true if any key field in this object has changed since being read from the DB.
        @return the true if changed, false if not.
     **/ 
    @Override
    public boolean hasKeysChanged(){ 
        if (dbCopy == null)  return true;
        if (this.getFieldKey() != null) { 
           if (!this.getFieldKey().equals( dbCopy.getFieldKey()))  return true;
        }
         else {
           if (dbCopy.getFieldKey() != null)  return true;
        }
        if (this.getAllvalKey() != null) { 
           if (!this.getAllvalKey().equals( dbCopy.getAllvalKey()))  return true;
        }
         else {
           if (dbCopy.getAllvalKey() != null)  return true;
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
        AVVALModel aAVVALModel = (AVVALModel)aObject;
        if (this.getFieldKey() != null) { 
            if (!this.getFieldKey().equals(aAVVALModel.getFieldKey()))  return true;
        }
         else {
             if (aAVVALModel.getFieldKey() != null)  return true;
        }
        if (this.getAllvalKey() != null) { 
            if (!this.getAllvalKey().equals(aAVVALModel.getAllvalKey()))  return true;
        }
         else {
             if (aAVVALModel.getAllvalKey() != null)  return true;
        }
        return false;
    } 

    /** 
        Returns true if all required fields are non-null.
        @return the true all required fields are non-null, false if not.
     **/ 
    public boolean isRequiredFieldsNotNull(){ 
           if (this.getFieldKey() == null)  return false;
           if (this.getAllvalKey() == null)  return false;
           if (this.getCoreValue() == null)  return false;
           if (this.getDisplayValue() == null)  return false;
           if (this.getAllvalProject() == null)  return false;
           if (this.getAllvalStatus() == null)  return false;
           if (this.getLstChangedBy() == null)  return false;
        return true;
    } 

    /** 
        Returns true if all primary key fields are non-null.
        @return the true all primary key fields are non-null, false if not.
     **/ 
    public boolean isPrimaryKeysNotNull(){ 
        if (this.getFieldKey() == null)  return false;
        if (this.getAllvalKey() == null)  return false;
        return true;
    } 

    @Override
    public String toString(){ 
        StringBuffer sb = new StringBuffer();
        sb.append ("FieldKey="+this.getFieldKey()+"  ");
        sb.append ("AllvalKey="+this.getAllvalKey()+"  ");
        sb.append ("CoreValue="+this.getCoreValue()+"  ");
        sb.append ("DisplayValue="+this.getDisplayValue()+"  ");
        sb.append ("AllvalProject="+this.getAllvalProject()+"  ");
        sb.append ("AllvalStatus="+this.getAllvalStatus()+"  ");
        sb.append ("LstChangedBy="+this.getLstChangedBy()+"  ");
        sb.append ("LstChangeDate="+this.getLstChangeDate()+"  ");
        sb.append ("TimeStamp="+this.getTimeStamp()+"  ");
        sb.append ("Cacheind="+this.getCacheind()+"  ");
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
        	modelColumn = new ModelColumn("ALLVAL_KEY");
        	if (dbCopy != null) {
        		if (dbCopy.getAllvalKey() != null) {
        			modelColumn.setOldData(dbCopy.getAllvalKey().toString());
        		}
        	}
        	if (getAllvalKey() != null) {
        		modelColumn.setNewData(getAllvalKey().toString());
        	}
        	retval.add(modelColumn);
        	modelColumn = new ModelColumn("CORE_VALUE");
        	if (dbCopy != null) {
        		if (dbCopy.getCoreValue() != null) {
        			modelColumn.setOldData(dbCopy.getCoreValue().toString());
        		}
        	}
        	if (getCoreValue() != null) {
        		modelColumn.setNewData(getCoreValue().toString());
        	}
        	retval.add(modelColumn);
        	modelColumn = new ModelColumn("DISPLAY_VALUE");
        	if (dbCopy != null) {
        		if (dbCopy.getDisplayValue() != null) {
        			modelColumn.setOldData(dbCopy.getDisplayValue().toString());
        		}
        	}
        	if (getDisplayValue() != null) {
        		modelColumn.setNewData(getDisplayValue().toString());
        	}
        	retval.add(modelColumn);
        	modelColumn = new ModelColumn("ALLVAL_PROJECT");
        	if (dbCopy != null) {
        		if (dbCopy.getAllvalProject() != null) {
        			modelColumn.setOldData(dbCopy.getAllvalProject().toString());
        		}
        	}
        	if (getAllvalProject() != null) {
        		modelColumn.setNewData(getAllvalProject().toString());
        	}
        	retval.add(modelColumn);
        	modelColumn = new ModelColumn("ALLVAL_STATUS");
        	if (dbCopy != null) {
        		if (dbCopy.getAllvalStatus() != null) {
        			modelColumn.setOldData(dbCopy.getAllvalStatus().toString());
        		}
        	}
        	if (getAllvalStatus() != null) {
        		modelColumn.setNewData(getAllvalStatus().toString());
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
        return retval;
    } 

    /** 
        Returns a DOM Element for this object.
        @param aDocument The document used to create elements.
        @return a DOM Element for this object.
     **/ 
    @Override
    public Element toXML(Document aDocument){ 
        Element element = aDocument.createElement("AVVALModel");
        element.setAttribute("fieldKey", toSafeString(this.getFieldKey()));
        element.setAttribute("allvalKey", toSafeString(this.getAllvalKey()));
        element.setAttribute("coreValue", toSafeString(this.getCoreValue()));
        element.setAttribute("displayValue", toSafeString(this.getDisplayValue()));
        element.setAttribute("allvalProject", toSafeString(this.getAllvalProject()));
        element.setAttribute("allvalStatus", toSafeString(this.getAllvalStatus()));
        element.setAttribute("lstChangedBy", toSafeString(this.getLstChangedBy()));
        element.setAttribute("lstChangeDate", toSafeString(this.getLstChangeDate()));
        element.setAttribute("timeStamp", toSafeString(this.getTimeStamp()));
        element.setAttribute("cacheind", toSafeString(this.getCacheind()));
        return element;
    } 

    /** 
        Returns an instance of this model created from data in a  DOM Element.
        @param aElement The DOM Element to parse into this model.
        @return an instance of this model created from data in a  DOM Element.
     **/ 
    @Override
    public DAOModel fromXML(Element aElement){ 
        AVVALModel aVVALModel = new AVVALModel();
        aVVALModel.setFieldKey(aElement.getAttribute("fieldKey"));
        aVVALModel.setAllvalKey(aElement.getAttribute("allvalKey"));
        aVVALModel.setCoreValue(aElement.getAttribute("coreValue"));
        aVVALModel.setDisplayValue(aElement.getAttribute("displayValue"));
        aVVALModel.setAllvalProject(aElement.getAttribute("allvalProject"));
        aVVALModel.setAllvalStatus(aElement.getAttribute("allvalStatus"));
        aVVALModel.setLstChangedBy(aElement.getAttribute("lstChangedBy"));
        aVVALModel.setLstChangeDate(toDate(aElement.getAttribute("lstChangeDate")));
        aVVALModel.setCacheind(toLong(aElement.getAttribute("cacheind")));
        return aVVALModel;
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
        	dbCopy = (AVVALModel)aDAOModel;
        }
        else {
        	dbCopy = null;
        }
    } 

    /** 
        Returns an instance of the DAO access class (AVVALDAO) for this model.
        @return an instance of the DAO access class for this model.
     **/ 
    @Override
    public DataAccessor getDataAccessorObject(){ 
        return new AVVALDAO();
    } 
}