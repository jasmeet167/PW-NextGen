package com.csc.fsg.life.avm.biz.dao.model;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.ArrayList;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import com.csc.fsg.life.avm.biz.dao.dataaccessor.AVRNGDAO;
import com.csc.fsg.life.dao.dataaccessor.DataAccessor;
import com.csc.fsg.life.dao.model.DAOModel;
import com.csc.fsg.life.dao.model.ModelColumn;

/* Modifications: T0175 */

/** 
    Table Model data transfer object AVRNGModel.
 **/ 
public class AVRNGModel 
	extends DAOModel 
	implements java.io.Serializable 
{ 
    /** 
        Property fieldKey for table AVRNG.
     **/ 
    private String fieldKey;
    /** 
        Property allrngKey for table AVRNG.
     **/ 
    private String allrngKey;
    /** 
        Property minValue for table AVRNG.
     **/ 
    private Double minValue;
    /** 
        Property maxValue for table AVRNG.
     **/ 
    private Double maxValue;
    /** 
        Property allrngProject for table AVRNG.
     **/ 
    private String allrngProject;
    /** 
        Property allrngStatus for table AVRNG.
     **/ 
    private String allrngStatus;
    /** 
        Property lstChangedBy for table AVRNG.
     **/ 
    private String lstChangedBy;
    /** 
        Property lstChangeDate for table AVRNG.
     **/ 
    private Date lstChangeDate;
    /** 
        Property timeStamp for table AVRNG.
     **/ 
    private Timestamp timeStamp;
    /** 
        Property cacheind for table AVRNG.
     **/ 
    private Long cacheind;
    private AVRNGModel dbCopy;

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
        Returns property allrngKey for this object.
        @return the property allrngKey for this object.
        @see #setAllrngKey
     **/ 
    public String getAllrngKey(){ 
        return this.allrngKey;
    } 
    /** 
        Sets the property allrngKey for this object.
        @param aString the new value for this property.
        @see #getAllrngKey
     **/ 
    public void setAllrngKey(String aString){ 
        allrngKey = aString;
    } 

    /** 
        Returns property minValue for this object.
        @return the property minValue for this object.
        @see #setMinValue
     **/ 
    public Double getMinValue(){ 
        return this.minValue;
    } 
    /** 
        Sets the property minValue for this object.
        @param aDouble the new value for this property.
        @see #getMinValue
     **/ 
    public void setMinValue(Double aDouble){ 
        minValue = aDouble;
    } 

    /** 
        Returns property maxValue for this object.
        @return the property maxValue for this object.
        @see #setMaxValue
     **/ 
    public Double getMaxValue(){ 
        return this.maxValue;
    } 
    /** 
        Sets the property maxValue for this object.
        @param aDouble the new value for this property.
        @see #getMaxValue
     **/ 
    public void setMaxValue(Double aDouble){ 
        maxValue = aDouble;
    } 

    /** 
        Returns property allrngProject for this object.
        @return the property allrngProject for this object.
        @see #setAllrngProject
     **/ 
    public String getAllrngProject(){ 
        return this.allrngProject;
    } 
    /** 
        Sets the property allrngProject for this object.
        @param aString the new value for this property.
        @see #getAllrngProject
     **/ 
    public void setAllrngProject(String aString){ 
        allrngProject = aString;
    } 

    /** 
        Returns property allrngStatus for this object.
        @return the property allrngStatus for this object.
        @see #setAllrngStatus
     **/ 
    public String getAllrngStatus(){ 
        return this.allrngStatus;
    } 
    /** 
        Sets the property allrngStatus for this object.
        @param aString the new value for this property.
        @see #getAllrngStatus
     **/ 
    public void setAllrngStatus(String aString){ 
        allrngStatus = aString;
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
    public AVRNGModel getDbCopy(){ 
        return this.dbCopy;
    } 
    /** 
        Sets the property dbCopy for this object.
        @param aAVRNGModel the new value for this property.
        @see #getDbCopy
     **/ 
    public void setDbCopy(AVRNGModel aAVRNGModel){ 
        dbCopy = aAVRNGModel;
    } 
    /** 
        Default constructor for AVRNGModel.
     **/ 
    public  AVRNGModel(){ 
        super();
        dbCopy = null;
    } 

    /** 
        Constructor for AVRNGModel with state.
        @param aint the state of this model object.
     **/ 
    public  AVRNGModel(int aint){ 
        super(aint);
        dbCopy = null;
    } 

    /** 
        Returns the model name.
        @return the model name.
     **/ 
    @Override
    public String getModelName(){ 
        return "com.csc.fsg.life.avm.biz.dao.model.AVRNGModel";
    } 

    /** 
        Returns the table name AVRNG.
        @return the table name.
     **/ 
    public String getTableName(){ 
        return "AVRNG";
    } 

    /** 
        Returns a new instance of this class.
        @return the new instance.
     **/ 
    @Override
    public DAOModel createNew(){ 
        return new AVRNGModel();
    } 

    /** 
        Returns a clone of this instance.
        @return the clone instance.
     **/ 
    public DAOModel createCopy(){ 
        AVRNGModel newModel = new AVRNGModel();
        newModel.setFieldKey(this.getFieldKey());
        newModel.setAllrngKey(this.getAllrngKey());
        newModel.setMinValue(this.getMinValue());
        newModel.setMaxValue(this.getMaxValue());
        newModel.setAllrngProject(this.getAllrngProject());
        newModel.setAllrngStatus(this.getAllrngStatus());
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
        if (this.getAllrngKey() != null) { 
             if (!this.getAllrngKey().equals(dbCopy.getAllrngKey()))  return true;
        }
         else {
             if (dbCopy.getAllrngKey() != null)  return true;
        }
        if (this.getMinValue() != null) { 
             if (!this.getMinValue().equals(dbCopy.getMinValue()))  return true;
        }
         else {
             if (dbCopy.getMinValue() != null)  return true;
        }
        if (this.getMaxValue() != null) { 
             if (!this.getMaxValue().equals(dbCopy.getMaxValue()))  return true;
        }
         else {
             if (dbCopy.getMaxValue() != null)  return true;
        }
        if (this.getAllrngProject() != null) { 
             if (!this.getAllrngProject().equals(dbCopy.getAllrngProject()))  return true;
        }
         else {
             if (dbCopy.getAllrngProject() != null)  return true;
        }
        if (this.getAllrngStatus() != null) { 
             if (!this.getAllrngStatus().equals(dbCopy.getAllrngStatus()))  return true;
        }
         else {
             if (dbCopy.getAllrngStatus() != null)  return true;
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
        AVRNGModel aAVRNGModel = (AVRNGModel)aObject;
        if (this.getFieldKey() != null) { 
            if (!this.getFieldKey().equals(aAVRNGModel.getFieldKey()))  return false;
        }
         else {
            if (aAVRNGModel.getFieldKey() != null)  return false;
        }
        if (this.getAllrngKey() != null) { 
            if (!this.getAllrngKey().equals(aAVRNGModel.getAllrngKey()))  return false;
        }
         else {
            if (aAVRNGModel.getAllrngKey() != null)  return false;
        }
        if (this.getMinValue() != null) { 
            if (!this.getMinValue().equals(aAVRNGModel.getMinValue()))  return false;
        }
         else {
            if (aAVRNGModel.getMinValue() != null)  return false;
        }
        if (this.getMaxValue() != null) { 
            if (!this.getMaxValue().equals(aAVRNGModel.getMaxValue()))  return false;
        }
         else {
            if (aAVRNGModel.getMaxValue() != null)  return false;
        }
        if (this.getAllrngProject() != null) { 
            if (!this.getAllrngProject().equals(aAVRNGModel.getAllrngProject()))  return false;
        }
         else {
            if (aAVRNGModel.getAllrngProject() != null)  return false;
        }
        if (this.getAllrngStatus() != null) { 
            if (!this.getAllrngStatus().equals(aAVRNGModel.getAllrngStatus()))  return false;
        }
         else {
            if (aAVRNGModel.getAllrngStatus() != null)  return false;
        }
        if (this.getLstChangedBy() != null) { 
            if (!this.getLstChangedBy().equals(aAVRNGModel.getLstChangedBy()))  return false;
        }
         else {
            if (aAVRNGModel.getLstChangedBy() != null)  return false;
        }
        if (this.getLstChangeDate() != null) { 
            if (!this.getLstChangeDate().equals(aAVRNGModel.getLstChangeDate()))  return false;
        }
         else {
            if (aAVRNGModel.getLstChangeDate() != null)  return false;
        }
        if (this.getTimeStamp() != null) { 
            if (!this.getTimeStamp().equals(aAVRNGModel.getTimeStamp()))  return false;
        }
         else {
            if (aAVRNGModel.getTimeStamp() != null)  return false;
        }
        if (this.getCacheind() != null) { 
            if (!this.getCacheind().equals(aAVRNGModel.getCacheind()))  return false;
        }
         else {
            if (aAVRNGModel.getCacheind() != null)  return false;
        }
        return true;
    } 

	@Override
	public int hashCode()
	{
		StringBuilder buf = new StringBuilder();
		buf.append(getFieldKey());
		buf.append(getAllrngKey());
		buf.append(getMinValue());
		buf.append(getMaxValue());
		buf.append(getAllrngProject());
		buf.append(getAllrngStatus());
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
        if (this.getAllrngKey() != null) { 
           if (!this.getAllrngKey().equals( dbCopy.getAllrngKey()))  return true;
        }
         else {
           if (dbCopy.getAllrngKey() != null)  return true;
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
        AVRNGModel aAVRNGModel = (AVRNGModel)aObject;
        if (this.getFieldKey() != null) { 
            if (!this.getFieldKey().equals(aAVRNGModel.getFieldKey()))  return true;
        }
         else {
             if (aAVRNGModel.getFieldKey() != null)  return true;
        }
        if (this.getAllrngKey() != null) { 
            if (!this.getAllrngKey().equals(aAVRNGModel.getAllrngKey()))  return true;
        }
         else {
             if (aAVRNGModel.getAllrngKey() != null)  return true;
        }
        return false;
    } 

    /** 
        Returns true if all required fields are non-null.
        @return the true all required fields are non-null, false if not.
     **/ 
    public boolean isRequiredFieldsNotNull(){ 
           if (this.getFieldKey() == null)  return false;
           if (this.getAllrngKey() == null)  return false;
           if (this.getAllrngProject() == null)  return false;
           if (this.getAllrngStatus() == null)  return false;
           if (this.getLstChangedBy() == null)  return false;
        return true;
    } 

    /** 
        Returns true if all primary key fields are non-null.
        @return the true all primary key fields are non-null, false if not.
     **/ 
    public boolean isPrimaryKeysNotNull(){ 
        if (this.getFieldKey() == null)  return false;
        if (this.getAllrngKey() == null)  return false;
        return true;
    } 

    @Override
    public String toString(){ 
        StringBuffer sb = new StringBuffer();
        sb.append ("FieldKey="+this.getFieldKey()+"  ");
        sb.append ("AllrngKey="+this.getAllrngKey()+"  ");
        sb.append ("MinValue="+this.getMinValue()+"  ");
        sb.append ("MaxValue="+this.getMaxValue()+"  ");
        sb.append ("AllrngProject="+this.getAllrngProject()+"  ");
        sb.append ("AllrngStatus="+this.getAllrngStatus()+"  ");
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
        	modelColumn = new ModelColumn("ALLRNG_KEY");
        	if (dbCopy != null) {
        		if (dbCopy.getAllrngKey() != null) {
        			modelColumn.setOldData(dbCopy.getAllrngKey().toString());
        		}
        	}
        	if (getAllrngKey() != null) {
        		modelColumn.setNewData(getAllrngKey().toString());
        	}
        	retval.add(modelColumn);
        	modelColumn = new ModelColumn("MIN_VALUE");
        	if (dbCopy != null) {
        		if (dbCopy.getMinValue() != null) {
        			modelColumn.setOldData(dbCopy.getMinValue().toString());
        		}
        	}
        	if (getMinValue() != null) {
        		modelColumn.setNewData(getMinValue().toString());
        	}
        	retval.add(modelColumn);
        	modelColumn = new ModelColumn("MAX_VALUE");
        	if (dbCopy != null) {
        		if (dbCopy.getMaxValue() != null) {
        			modelColumn.setOldData(dbCopy.getMaxValue().toString());
        		}
        	}
        	if (getMaxValue() != null) {
        		modelColumn.setNewData(getMaxValue().toString());
        	}
        	retval.add(modelColumn);
        	modelColumn = new ModelColumn("ALLRNG_PROJECT");
        	if (dbCopy != null) {
        		if (dbCopy.getAllrngProject() != null) {
        			modelColumn.setOldData(dbCopy.getAllrngProject().toString());
        		}
        	}
        	if (getAllrngProject() != null) {
        		modelColumn.setNewData(getAllrngProject().toString());
        	}
        	retval.add(modelColumn);
        	modelColumn = new ModelColumn("ALLRNG_STATUS");
        	if (dbCopy != null) {
        		if (dbCopy.getAllrngStatus() != null) {
        			modelColumn.setOldData(dbCopy.getAllrngStatus().toString());
        		}
        	}
        	if (getAllrngStatus() != null) {
        		modelColumn.setNewData(getAllrngStatus().toString());
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
        Element element = aDocument.createElement("AVRNGModel");
        element.setAttribute("fieldKey", toSafeString(this.getFieldKey()));
        element.setAttribute("allrngKey", toSafeString(this.getAllrngKey()));
        element.setAttribute("minValue", toSafeString(this.getMinValue()));
        element.setAttribute("maxValue", toSafeString(this.getMaxValue()));
        element.setAttribute("allrngProject", toSafeString(this.getAllrngProject()));
        element.setAttribute("allrngStatus", toSafeString(this.getAllrngStatus()));
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
        AVRNGModel aVRNGModel = new AVRNGModel();
        aVRNGModel.setFieldKey(aElement.getAttribute("fieldKey"));
        aVRNGModel.setAllrngKey(aElement.getAttribute("allrngKey"));
        aVRNGModel.setMinValue(toDouble(aElement.getAttribute("minValue")));
        aVRNGModel.setMaxValue(toDouble(aElement.getAttribute("maxValue")));
        aVRNGModel.setAllrngProject(aElement.getAttribute("allrngProject"));
        aVRNGModel.setAllrngStatus(aElement.getAttribute("allrngStatus"));
        aVRNGModel.setLstChangedBy(aElement.getAttribute("lstChangedBy"));
        aVRNGModel.setLstChangeDate(toDate(aElement.getAttribute("lstChangeDate")));
        aVRNGModel.setCacheind(toLong(aElement.getAttribute("cacheind")));
        return aVRNGModel;
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
        	dbCopy = (AVRNGModel)aDAOModel;
        }
        else {
        	dbCopy = null;
        }
    } 

    /** 
        Returns an instance of the DAO access class (AVRNGDAO) for this model.
        @return an instance of the DAO access class for this model.
     **/ 
    @Override
    public DataAccessor getDataAccessorObject(){ 
        return new AVRNGDAO();
    } 
}