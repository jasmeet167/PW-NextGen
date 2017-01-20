package com.csc.fsg.life.avm.biz.dao.model;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.ArrayList;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import com.csc.fsg.life.avm.biz.dao.dataaccessor.AVENVDAO;
import com.csc.fsg.life.dao.dataaccessor.DataAccessor;
import com.csc.fsg.life.dao.model.DAOModel;
import com.csc.fsg.life.dao.model.ModelColumn;

/* Modifications: T0175 */

/** 
    Table Model data transfer object AVENVModel.
 **/ 
public class AVENVModel 
	extends DAOModel 
	implements java.io.Serializable 
{ 
    /** 
        Property envrnmntKey for table AVENV.
     **/ 
    private String envrnmntKey;
    /** 
        Property envrnmntId for table AVENV.
     **/ 
    private String envrnmntId;
    /** 
        Property envrnmntDesc for table AVENV.
     **/ 
    private String envrnmntDesc;
    /** 
        Property lstChangedBy for table AVENV.
     **/ 
    private String lstChangedBy;
    /** 
        Property lstChangeDate for table AVENV.
     **/ 
    private Date lstChangeDate;
    /** 
        Property timeStamp for table AVENV.
     **/ 
    private Timestamp timeStamp;
    private AVENVModel dbCopy;

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
        Returns property envrnmntDesc for this object.
        @return the property envrnmntDesc for this object.
        @see #setEnvrnmntDesc
     **/ 
    public String getEnvrnmntDesc(){ 
        return this.envrnmntDesc;
    } 
    /** 
        Sets the property envrnmntDesc for this object.
        @param aString the new value for this property.
        @see #getEnvrnmntDesc
     **/ 
    public void setEnvrnmntDesc(String aString){ 
        envrnmntDesc = aString;
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
    public AVENVModel getDbCopy(){ 
        return this.dbCopy;
    } 
    /** 
        Sets the property dbCopy for this object.
        @param aAVENVModel the new value for this property.
        @see #getDbCopy
     **/ 
    public void setDbCopy(AVENVModel aAVENVModel){ 
        dbCopy = aAVENVModel;
    } 
    /** 
        Default constructor for AVENVModel.
     **/ 
    public  AVENVModel(){ 
        super();
        dbCopy = null;
    } 

    /** 
        Constructor for AVENVModel with state.
        @param aint the state of this model object.
     **/ 
    public  AVENVModel(int aint){ 
        super(aint);
        dbCopy = null;
    } 

    /** 
        Returns the model name.
        @return the model name.
     **/ 
    @Override
    public String getModelName(){ 
        return "com.csc.fsg.life.avm.biz.dao.model.AVENVModel";
    } 

    /** 
        Returns the table name AVENV.
        @return the table name.
     **/ 
    public String getTableName(){ 
        return "AVENV";
    } 

    /** 
        Returns a new instance of this class.
        @return the new instance.
     **/ 
    @Override
    public DAOModel createNew(){ 
        return new AVENVModel();
    } 

    /** 
        Returns a clone of this instance.
        @return the clone instance.
     **/ 
    public DAOModel createCopy(){ 
        AVENVModel newModel = new AVENVModel();
        newModel.setEnvrnmntKey(this.getEnvrnmntKey());
        newModel.setEnvrnmntId(this.getEnvrnmntId());
        newModel.setEnvrnmntDesc(this.getEnvrnmntDesc());
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
        if (this.getEnvrnmntKey() != null) { 
             if (!this.getEnvrnmntKey().equals(dbCopy.getEnvrnmntKey()))  return true;
        }
         else {
             if (dbCopy.getEnvrnmntKey() != null)  return true;
        }
        if (this.getEnvrnmntId() != null) { 
             if (!this.getEnvrnmntId().equals(dbCopy.getEnvrnmntId()))  return true;
        }
         else {
             if (dbCopy.getEnvrnmntId() != null)  return true;
        }
        if (this.getEnvrnmntDesc() != null) { 
             if (!this.getEnvrnmntDesc().equals(dbCopy.getEnvrnmntDesc()))  return true;
        }
         else {
             if (dbCopy.getEnvrnmntDesc() != null)  return true;
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
        AVENVModel aAVENVModel = (AVENVModel)aObject;
        if (this.getEnvrnmntKey() != null) { 
            if (!this.getEnvrnmntKey().equals(aAVENVModel.getEnvrnmntKey()))  return false;
        }
         else {
            if (aAVENVModel.getEnvrnmntKey() != null)  return false;
        }
        if (this.getEnvrnmntId() != null) { 
            if (!this.getEnvrnmntId().equals(aAVENVModel.getEnvrnmntId()))  return false;
        }
         else {
            if (aAVENVModel.getEnvrnmntId() != null)  return false;
        }
        if (this.getEnvrnmntDesc() != null) { 
            if (!this.getEnvrnmntDesc().equals(aAVENVModel.getEnvrnmntDesc()))  return false;
        }
         else {
            if (aAVENVModel.getEnvrnmntDesc() != null)  return false;
        }
        if (this.getLstChangedBy() != null) { 
            if (!this.getLstChangedBy().equals(aAVENVModel.getLstChangedBy()))  return false;
        }
         else {
            if (aAVENVModel.getLstChangedBy() != null)  return false;
        }
        if (this.getLstChangeDate() != null) { 
            if (!this.getLstChangeDate().equals(aAVENVModel.getLstChangeDate()))  return false;
        }
         else {
            if (aAVENVModel.getLstChangeDate() != null)  return false;
        }
        if (this.getTimeStamp() != null) { 
            if (!this.getTimeStamp().equals(aAVENVModel.getTimeStamp()))  return false;
        }
         else {
            if (aAVENVModel.getTimeStamp() != null)  return false;
        }
        return true;
    } 

	@Override
	public int hashCode()
	{
		StringBuilder buf = new StringBuilder();
		buf.append(getEnvrnmntKey());
		buf.append(getEnvrnmntId());
		buf.append(getEnvrnmntDesc());
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
        if (this.getEnvrnmntKey() != null) { 
           if (!this.getEnvrnmntKey().equals( dbCopy.getEnvrnmntKey()))  return true;
        }
         else {
           if (dbCopy.getEnvrnmntKey() != null)  return true;
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
        AVENVModel aAVENVModel = (AVENVModel)aObject;
        if (this.getEnvrnmntKey() != null) { 
            if (!this.getEnvrnmntKey().equals(aAVENVModel.getEnvrnmntKey()))  return true;
        }
         else {
             if (aAVENVModel.getEnvrnmntKey() != null)  return true;
        }
        return false;
    } 

    /** 
        Returns true if all required fields are non-null.
        @return the true all required fields are non-null, false if not.
     **/ 
    public boolean isRequiredFieldsNotNull(){ 
           if (this.getEnvrnmntKey() == null)  return false;
           if (this.getEnvrnmntId() == null)  return false;
           if (this.getEnvrnmntDesc() == null)  return false;
           if (this.getLstChangedBy() == null)  return false;
        return true;
    } 

    /** 
        Returns true if all primary key fields are non-null.
        @return the true all primary key fields are non-null, false if not.
     **/ 
    public boolean isPrimaryKeysNotNull(){ 
        if (this.getEnvrnmntKey() == null)  return false;
        return true;
    } 

    @Override
    public String toString(){ 
        StringBuffer sb = new StringBuffer();
        sb.append ("EnvrnmntKey="+this.getEnvrnmntKey()+"  ");
        sb.append ("EnvrnmntId="+this.getEnvrnmntId()+"  ");
        sb.append ("EnvrnmntDesc="+this.getEnvrnmntDesc()+"  ");
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
        	ModelColumn modelColumn = new ModelColumn("ENVRNMNT_KEY");
        	if (dbCopy != null) {
        		if (dbCopy.getEnvrnmntKey() != null) {
        			modelColumn.setOldData(dbCopy.getEnvrnmntKey().toString());
        		}
        	}
        	if (getEnvrnmntKey() != null) {
        		modelColumn.setNewData(getEnvrnmntKey().toString());
        	}
        	retval.add(modelColumn);
        	modelColumn = new ModelColumn("ENVRNMNT_ID");
        	if (dbCopy != null) {
        		if (dbCopy.getEnvrnmntId() != null) {
        			modelColumn.setOldData(dbCopy.getEnvrnmntId().toString());
        		}
        	}
        	if (getEnvrnmntId() != null) {
        		modelColumn.setNewData(getEnvrnmntId().toString());
        	}
        	retval.add(modelColumn);
        	modelColumn = new ModelColumn("ENVRNMNT_DESC");
        	if (dbCopy != null) {
        		if (dbCopy.getEnvrnmntDesc() != null) {
        			modelColumn.setOldData(dbCopy.getEnvrnmntDesc().toString());
        		}
        	}
        	if (getEnvrnmntDesc() != null) {
        		modelColumn.setNewData(getEnvrnmntDesc().toString());
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
        Element element = aDocument.createElement("AVENVModel");
        element.setAttribute("envrnmntKey", toSafeString(this.getEnvrnmntKey()));
        element.setAttribute("envrnmntId", toSafeString(this.getEnvrnmntId()));
        element.setAttribute("envrnmntDesc", toSafeString(this.getEnvrnmntDesc()));
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
        AVENVModel aVENVModel = new AVENVModel();
        aVENVModel.setEnvrnmntKey(aElement.getAttribute("envrnmntKey"));
        aVENVModel.setEnvrnmntId(aElement.getAttribute("envrnmntId"));
        aVENVModel.setEnvrnmntDesc(aElement.getAttribute("envrnmntDesc"));
        aVENVModel.setLstChangedBy(aElement.getAttribute("lstChangedBy"));
        aVENVModel.setLstChangeDate(toDate(aElement.getAttribute("lstChangeDate")));
        return aVENVModel;
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
        	dbCopy = (AVENVModel)aDAOModel;
        }
        else
        {
        	dbCopy = null;
        }
    } 

    /** 
        Returns an instance of the DAO access class (AVENVDAO) for this model.
        @return an instance of the DAO access class for this model.
     **/ 
    @Override
    public DataAccessor getDataAccessorObject(){ 
        return new AVENVDAO();
    } 
}