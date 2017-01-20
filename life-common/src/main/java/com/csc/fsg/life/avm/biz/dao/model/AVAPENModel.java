package com.csc.fsg.life.avm.biz.dao.model;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.ArrayList;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import com.csc.fsg.life.avm.biz.dao.dataaccessor.AVAPENDAO;
import com.csc.fsg.life.dao.dataaccessor.DataAccessor;
import com.csc.fsg.life.dao.model.DAOModel;
import com.csc.fsg.life.dao.model.ModelColumn;

/* Modifications: T0175 */

/** 
    Table Model data transfer object AVAPENModel.
 **/ 
public class AVAPENModel 
	extends DAOModel 
		implements java.io.Serializable { 

    /** 
        Property applKey for table AVAPEN.
     **/ 
    private String applKey;
    /** 
        Property envrnmntKey for table AVAPEN.
     **/ 
    private String envrnmntKey;
    /** 
        Property description for table AVAPEN.
     **/ 
    private String description;
    /** 
        Property subsystem for table AVAPEN.
     **/ 
    private String subsystem;
    /** 
        Property schema for table AVAPEN.
     **/ 
    private String schema;
    /** 
        Property fromPrmtEnvrm for table AVAPEN.
     **/ 
    private String fromPrmtEnvrm;
    /** 
        Property fromPrmtDate for table AVAPEN.
     **/ 
    private Date fromPrmtDate;
    /** 
        Property fromPrmtTimestampfor table AVAPEN.
     **/ 
    private Timestamp fromPrmtTimestamp;
    /** 
        Property fromPrmtBy for table AVAPEN.
     **/ 
    private String fromPrmtBy;
    /** 
        Property fromPrmtOption for table AVAPEN.
     **/ 
    private String fromPrmtOption;
    /** 
        Property fromPrmtChgDate for table AVAPEN.
     **/ 
    private Date fromPrmtChgDate;
    /** 
        Property fromPrmtChgProj for table AVAPEN.
     **/ 
    private String fromPrmtChgProj;
    /** 
        Property fromExportFile for table AVAPEN.
     **/ 
    private String fromExportFile;
    /** 
        Property toPrmtEnvrmfor table AVAPEN.
     **/ 
    private String toPrmtEnvrm;
    /** 
        Property toPrmtDate for table AVAPEN.
     **/ 
    private Date toPrmtDate;
    /** 
        Property toPrmtTimestamp for table AVAPEN.
     **/ 
    private Timestamp toPrmtTimestamp;
    /** 
        Property toPrmtByfor table AVAPEN.
     **/ 
    private String toPrmtBy;
    /** 
        Property toPrmtOption for table AVAPEN.
     **/ 
    private String toPrmtOption;
    /** 
        Property toPrmtChgDate for table AVAPEN.
     **/ 
    private Date toPrmtChgDate;
    /** 
        Property toPrmtChgProj for table AVAPEN.
     **/ 
    private String toPrmtChgProj;
    /** 
        Property toExportFile for table AVAPEN.
     **/ 
    private String toExportFile;
    /** 
        Property lstChangedBy for table AVAPEN.
     **/ 
    private String lstChangedBy;
    /** 
        Property lstChangeDate for table AVAPEN.
     **/ 
    private Date lstChangeDate;
    /** 
        Property timeStamp for table AVAPEN.
     **/ 
    private Timestamp timeStamp;
    private AVAPENModel dbCopy;

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
        Returns property description for this object.
        @return the property description for this object.
	   @see #setDescription
	**/
    public String getDescription(){ 
        return this.description;
    } 
	/**
        Sets the property description for this object.
        @param aString the new value for this property.
	   @see #getDescription
	 **/
    public void setDescription(String aString){ 
        description = aString;
    } 

	/**
        Returns property subsystem for this object.
        @return the property subsystem for this object.
	   @see #setSubsystem
	**/
    public String getSubsystem(){ 
        return this.subsystem;
    } 
	/**
        Sets the property subsystem for this object.
        @param aString the new value for this property.
	   @see #getSubsystem
	 **/
    public void setSubsystem(String aString){ 
        subsystem = aString;
    } 

	/**
        Returns property schema for this object.
        @return the property schema for this object.
	   @see #setSchema
	**/
    public String getSchema(){ 
        return this.schema;
    } 
	/**
        Sets the property schema for this object.
        @param aString the new value for this property.
	   @see #getSchema
	 **/
    public void setSchema(String aString){ 
        schema = aString;
    } 

	/**
        Returns property fromPrmtEnvrm for this object.
        @return the property fromPrmtEnvrm for this object.
	   @see #setFromPrmtEnvrm
	**/
    public String getFromPrmtEnvrm(){ 
        return this.fromPrmtEnvrm;
    } 
	/**
        Sets the property fromPrmtEnvrm for this object.
        @param aString the new value for this property.
	   @see #getFromPrmtEnvrm
	 **/
    public void setFromPrmtEnvrm(String aString){ 
        fromPrmtEnvrm = aString;
    } 

	/**
        Returns property fromPrmtDate for this object.
        @return the property fromPrmtDate for this object.
	   @see #setFromPrmtDate
	**/
    public Date getFromPrmtDate(){ 
        return this.fromPrmtDate;
    } 
	/**
        Sets the property fromPrmtDate for this object.
        @param aDate the new value for this property.
	   @see #getFromPrmtDate
	 **/
    public void setFromPrmtDate(Date aDate){ 
        fromPrmtDate = aDate;
    } 

	/**
        Returns property fromPrmtTimestamp for this object.
        @return the property fromPrmtTimestamp for this object.
	   @see #setFromPrmtTimestamp
	**/
    public Timestamp getFromPrmtTimestamp(){ 
        return this.fromPrmtTimestamp;
    } 
	/**
        Sets the property fromPrmtTimestamp for this object.
        @param aTimestamp the new value for this property.
	   @see #getFromPrmtTimestamp
	 **/
    public void setFromPrmtTimestamp(Timestamp aTimestamp){ 
        fromPrmtTimestamp = aTimestamp;
    } 

	/**
        Returns property fromPrmtBy for this object.
        @return the property fromPrmtBy for this object.
	   @see #setFromPrmtBy
	**/
    public String getFromPrmtBy(){ 
        return this.fromPrmtBy;
    } 
	/**
        Sets the property fromPrmtBy for this object.
        @param aString the new value for this property.
	   @see #getFromPrmtBy
	 **/
    public void setFromPrmtBy(String aString){ 
        fromPrmtBy = aString;
    } 

	/**
        Returns property fromPrmtOption for this object.
        @return the property fromPrmtOption for this object.
	   @see #setFromPrmtOption
	**/
    public String getFromPrmtOption(){ 
        return this.fromPrmtOption;
    } 
	/**
        Sets the property fromPrmtOption for this object.
        @param aString the new value for this property.
	   @see #getFromPrmtOption
	 **/
    public void setFromPrmtOption(String aString){ 
        fromPrmtOption = aString;
    } 

	/**
        Returns property fromPrmtChgDate for this object.
        @return the property fromPrmtChgDate for this object.
	   @see #setFromPrmtChgDate
	**/
    public Date getFromPrmtChgDate(){ 
        return this.fromPrmtChgDate;
    } 
	/**
        Sets the property fromPrmtChgDate for this object.
        @param aDate the new value for this property.
	   @see #getFromPrmtChgDate
	 **/
    public void setFromPrmtChgDate(Date aDate){ 
        fromPrmtChgDate = aDate;
    } 

	/**
        Returns property fromPrmtChgProj for this object.
        @return the property fromPrmtChgProj for this object.
	   @see #setFromPrmtChgProj
	**/
    public String getFromPrmtChgProj(){ 
        return this.fromPrmtChgProj;
    } 
	/**
        Sets the property fromPrmtChgProj for this object.
        @param aString the new value for this property.
	   @see #getFromPrmtChgProj
	 **/
    public void setFromPrmtChgProj(String aString){ 
        fromPrmtChgProj = aString;
    } 

	/**
        Returns property fromExportFile for this object.
        @return the property fromExportFile for this object.
	   @see #setFromExportFile
	**/
    public String getFromExportFile(){ 
        return this.fromExportFile;
    } 
	/**
        Sets the property fromExportFile for this object.
        @param aString the new value for this property.
	   @see #getFromExportFile
	 **/
    public void setFromExportFile(String aString){ 
        fromExportFile = aString;
    } 

	/**
        Returns property toPrmtEnvrm for this object.
        @return the property toPrmtEnvrm for this object.
	   @see #setToPrmtEnvrm
	**/
    public String getToPrmtEnvrm(){ 
        return this.toPrmtEnvrm;
    } 
	/**
        Sets the property toPrmtEnvrm for this object.
        @param aString the new value for this property.
	   @see #getToPrmtEnvrm
	 **/
    public void setToPrmtEnvrm(String aString){ 
        toPrmtEnvrm = aString;
    } 

	/**
        Returns property toPrmtDate for this object.
        @return the property toPrmtDate for this object.
	   @see #setToPrmtDate
	**/
    public Date getToPrmtDate(){ 
        return this.toPrmtDate;
    } 
	/**
        Sets the property toPrmtDate for this object.
        @param aDate the new value for this property.
	   @see #getToPrmtDate
	 **/
    public void setToPrmtDate(Date aDate){ 
        toPrmtDate = aDate;
    } 

	/**
        Returns property toPrmtTimestamp for this object.
        @return the property toPrmtTimestamp for this object.
	   @see #setToPrmtTimestamp
	**/
    public Timestamp getToPrmtTimestamp(){ 
        return this.toPrmtTimestamp;
    } 
	/**
        Sets the property toPrmtTimestamp for this object.
        @param aTimestamp the new value for this property.
	   @see #getToPrmtTimestamp
	 **/
    public void setToPrmtTimestamp(Timestamp aTimestamp){ 
        toPrmtTimestamp = aTimestamp;
    } 

	/**
        Returns property toPrmtBy for this object.
        @return the property toPrmtBy for this object.
	   @see #setToPrmtBy
	**/
    public String getToPrmtBy(){ 
        return this.toPrmtBy;
    } 
	/**
        Sets the property toPrmtBy for this object.
        @param aString the new value for this property.
	   @see #getToPrmtBy
	 **/
    public void setToPrmtBy(String aString){ 
        toPrmtBy = aString;
    } 

	/**
        Returns property toPrmtOption for this object.
        @return the property toPrmtOption for this object.
	   @see #setToPrmtOption
	**/
    public String getToPrmtOption(){ 
        return this.toPrmtOption;
    } 
	/**
        Sets the property toPrmtOption for this object.
        @param aString the new value for this property.
	   @see #getToPrmtOption
	 **/
    public void setToPrmtOption(String aString){ 
        toPrmtOption = aString;
    } 

	/**
        Returns property toPrmtChgDate for this object.
        @return the property toPrmtChgDate for this object.
	   @see #setToPrmtChgDate
	**/
    public Date getToPrmtChgDate(){ 
        return this.toPrmtChgDate;
    } 
	/**
        Sets the property toPrmtChgDate for this object.
        @param aDate the new value for this property.
	   @see #getToPrmtChgDate
	 **/
    public void setToPrmtChgDate(Date aDate){ 
        toPrmtChgDate = aDate;
    } 

	/**
        Returns property toPrmtChgProj for this object.
        @return the property toPrmtChgProj for this object.
	   @see #setToPrmtChgProj
	**/
    public String getToPrmtChgProj(){ 
        return this.toPrmtChgProj;
    } 
	/**
        Sets the property toPrmtChgProj for this object.
        @param aString the new value for this property.
	   @see #getToPrmtChgProj
	 **/
    public void setToPrmtChgProj(String aString){ 
        toPrmtChgProj = aString;
    } 

	/**
        Returns property toExportFile for this object.
        @return the property toExportFile for this object.
	   @see #setToExportFile
	**/
    public String getToExportFile(){ 
        return this.toExportFile;
    } 
	/**
        Sets the property toExportFile for this object.
        @param aString the new value for this property.
	   @see #getToExportFile
	 **/
    public void setToExportFile(String aString){ 
        toExportFile = aString;
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
    public AVAPENModel getDbCopy(){ 
        return this.dbCopy;
    } 
	/**
        Sets the property dbCopy for this object.
        @param aAVAPENModel the new value for this property.
	   @see #getDbCopy
	 **/
    public void setDbCopy(AVAPENModel aAVAPENModel){ 
        dbCopy = aAVAPENModel;
    } 
    /** 
        Default constructor for AVAPENModel.
     **/ 
    public  AVAPENModel(){ 
        super();
        dbCopy = null;
    } 

    /** 
        Constructor for AVAPENModel with state.
        @param aint the state of this model object.
     **/ 
    public  AVAPENModel(int aint){ 
        super(aint);
        dbCopy = null;
    } 

	/**
        Returns the model name.
        @return the model name.
	**/
    @Override
    public String getModelName(){ 
        return "com.csc.fsg.life.avm.biz.dao.model.AVAPENModel";
    } 

	/**
        Returns the table name AVAPEN.
        @return the table name.
	**/
    public String getTableName(){ 
        return "AVAPEN";
    } 

    /** 
        Returns a new instance of this class.
        @return the new instance.
     **/ 
    @Override
    public DAOModel createNew(){ 
        return new AVAPENModel();
    } 

    /** 
        Returns a clone of this instance.
        @return the clone instance.
     **/ 
    public DAOModel createCopy(){ 
        AVAPENModel newModel = new AVAPENModel();
        newModel.setApplKey(this.getApplKey());
        newModel.setEnvrnmntKey(this.getEnvrnmntKey());
        newModel.setDescription(this.getDescription());
        newModel.setSubsystem(this.getSubsystem());
        newModel.setSchema(this.getSchema());
        newModel.setFromPrmtEnvrm(this.getFromPrmtEnvrm());
        newModel.setFromPrmtDate(this.getFromPrmtDate());
        newModel.setFromPrmtTimestamp(this.getFromPrmtTimestamp());
        newModel.setFromPrmtBy(this.getFromPrmtBy());
        newModel.setFromPrmtOption(this.getFromPrmtOption());
        newModel.setFromPrmtChgDate(this.getFromPrmtChgDate());
        newModel.setFromPrmtChgProj(this.getFromPrmtChgProj());
        newModel.setFromExportFile(this.getFromExportFile());
        newModel.setToPrmtEnvrm(this.getToPrmtEnvrm());
        newModel.setToPrmtDate(this.getToPrmtDate());
        newModel.setToPrmtTimestamp(this.getToPrmtTimestamp());
        newModel.setToPrmtBy(this.getToPrmtBy());
        newModel.setToPrmtOption(this.getToPrmtOption());
        newModel.setToPrmtChgDate(this.getToPrmtChgDate());
        newModel.setToPrmtChgProj(this.getToPrmtChgProj());
        newModel.setToExportFile(this.getToExportFile());
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
        if (this.getEnvrnmntKey() != null) { 
             if (!this.getEnvrnmntKey().equals(dbCopy.getEnvrnmntKey()))  return true;
        }
         else {
             if (dbCopy.getEnvrnmntKey() != null)  return true;
        }
        if (this.getDescription() != null) { 
             if (!this.getDescription().equals(dbCopy.getDescription()))  return true;
        }
         else {
             if (dbCopy.getDescription() != null)  return true;
        }
        if (this.getSubsystem() != null) { 
             if (!this.getSubsystem().equals(dbCopy.getSubsystem()))  return true;
        }
         else {
             if (dbCopy.getSubsystem() != null)  return true;
        }
        if (this.getSchema() != null) { 
             if (!this.getSchema().equals(dbCopy.getSchema()))  return true;
        }
         else {
             if (dbCopy.getSchema() != null)  return true;
        }
        if (this.getFromPrmtEnvrm() != null) { 
             if (!this.getFromPrmtEnvrm().equals(dbCopy.getFromPrmtEnvrm()))  return true;
        }
         else {
             if (dbCopy.getFromPrmtEnvrm() != null)  return true;
        }
        if (this.getFromPrmtDate() != null) { 
             if (!this.getFromPrmtDate().equals(dbCopy.getFromPrmtDate()))  return true;
        }
         else {
             if (dbCopy.getFromPrmtDate() != null)  return true;
        }
        if (this.getFromPrmtTimestamp() != null) { 
             if (!this.getFromPrmtTimestamp().equals(dbCopy.getFromPrmtTimestamp()))  return true;
        }
         else {
             if (dbCopy.getFromPrmtTimestamp() != null)  return true;
        }
        if (this.getFromPrmtBy() != null) { 
             if (!this.getFromPrmtBy().equals(dbCopy.getFromPrmtBy()))  return true;
        }
         else {
             if (dbCopy.getFromPrmtBy() != null)  return true;
        }
        if (this.getFromPrmtOption() != null) { 
             if (!this.getFromPrmtOption().equals(dbCopy.getFromPrmtOption()))  return true;
        }
         else {
             if (dbCopy.getFromPrmtOption() != null)  return true;
        }
        if (this.getFromPrmtChgDate() != null) { 
             if (!this.getFromPrmtChgDate().equals(dbCopy.getFromPrmtChgDate()))  return true;
        }
         else {
             if (dbCopy.getFromPrmtChgDate() != null)  return true;
        }
        if (this.getFromPrmtChgProj() != null) { 
             if (!this.getFromPrmtChgProj().equals(dbCopy.getFromPrmtChgProj()))  return true;
        }
         else {
             if (dbCopy.getFromPrmtChgProj() != null)  return true;
        }
        if (this.getFromExportFile() != null) { 
             if (!this.getFromExportFile().equals(dbCopy.getFromExportFile()))  return true;
        }
         else {
             if (dbCopy.getFromExportFile() != null)  return true;
        }
        if (this.getToPrmtEnvrm() != null) { 
             if (!this.getToPrmtEnvrm().equals(dbCopy.getToPrmtEnvrm()))  return true;
        }
         else {
             if (dbCopy.getToPrmtEnvrm() != null)  return true;
        }
        if (this.getToPrmtDate() != null) { 
             if (!this.getToPrmtDate().equals(dbCopy.getToPrmtDate()))  return true;
        }
         else {
             if (dbCopy.getToPrmtDate() != null)  return true;
        }
        if (this.getToPrmtTimestamp() != null) { 
             if (!this.getToPrmtTimestamp().equals(dbCopy.getToPrmtTimestamp()))  return true;
        }
         else {
             if (dbCopy.getToPrmtTimestamp() != null)  return true;
        }
        if (this.getToPrmtBy() != null) { 
             if (!this.getToPrmtBy().equals(dbCopy.getToPrmtBy()))  return true;
        }
         else {
             if (dbCopy.getToPrmtBy() != null)  return true;
        }
        if (this.getToPrmtOption() != null) { 
             if (!this.getToPrmtOption().equals(dbCopy.getToPrmtOption()))  return true;
        }
         else {
             if (dbCopy.getToPrmtOption() != null)  return true;
        }
        if (this.getToPrmtChgDate() != null) { 
             if (!this.getToPrmtChgDate().equals(dbCopy.getToPrmtChgDate()))  return true;
        }
         else {
             if (dbCopy.getToPrmtChgDate() != null)  return true;
        }
        if (this.getToPrmtChgProj() != null) { 
             if (!this.getToPrmtChgProj().equals(dbCopy.getToPrmtChgProj()))  return true;
        }
         else {
             if (dbCopy.getToPrmtChgProj() != null)  return true;
        }
        if (this.getToExportFile() != null) { 
             if (!this.getToExportFile().equals(dbCopy.getToExportFile()))  return true;
        }
         else {
             if (dbCopy.getToExportFile() != null)  return true;
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
        AVAPENModel aAVAPENModel = (AVAPENModel)aObject;
        if (this.getApplKey() != null) { 
            if (!this.getApplKey().equals(aAVAPENModel.getApplKey()))  return false;
        }
         else {
            if (aAVAPENModel.getApplKey() != null)  return false;
        }
        if (this.getEnvrnmntKey() != null) { 
            if (!this.getEnvrnmntKey().equals(aAVAPENModel.getEnvrnmntKey()))  return false;
        }
         else {
            if (aAVAPENModel.getEnvrnmntKey() != null)  return false;
        }
        if (this.getDescription() != null) { 
            if (!this.getDescription().equals(aAVAPENModel.getDescription()))  return false;
        }
         else {
            if (aAVAPENModel.getDescription() != null)  return false;
        }
        if (this.getSubsystem() != null) { 
            if (!this.getSubsystem().equals(aAVAPENModel.getSubsystem()))  return false;
        }
         else {
            if (aAVAPENModel.getSubsystem() != null)  return false;
        }
        if (this.getSchema() != null) { 
            if (!this.getSchema().equals(aAVAPENModel.getSchema()))  return false;
        }
         else {
            if (aAVAPENModel.getSchema() != null)  return false;
        }
        if (this.getFromPrmtEnvrm() != null) { 
            if (!this.getFromPrmtEnvrm().equals(aAVAPENModel.getFromPrmtEnvrm()))  return false;
        }
         else {
            if (aAVAPENModel.getFromPrmtEnvrm() != null)  return false;
        }
        if (this.getFromPrmtDate() != null) { 
            if (!this.getFromPrmtDate().equals(aAVAPENModel.getFromPrmtDate()))  return false;
        }
         else {
            if (aAVAPENModel.getFromPrmtDate() != null)  return false;
        }
        if (this.getFromPrmtTimestamp() != null) { 
            if (!this.getFromPrmtTimestamp().equals(aAVAPENModel.getFromPrmtTimestamp()))  return false;
        }
         else {
            if (aAVAPENModel.getFromPrmtTimestamp() != null)  return false;
        }
        if (this.getFromPrmtBy() != null) { 
            if (!this.getFromPrmtBy().equals(aAVAPENModel.getFromPrmtBy()))  return false;
        }
         else {
            if (aAVAPENModel.getFromPrmtBy() != null)  return false;
        }
        if (this.getFromPrmtOption() != null) { 
            if (!this.getFromPrmtOption().equals(aAVAPENModel.getFromPrmtOption()))  return false;
        }
         else {
            if (aAVAPENModel.getFromPrmtOption() != null)  return false;
        }
        if (this.getFromPrmtChgDate() != null) { 
            if (!this.getFromPrmtChgDate().equals(aAVAPENModel.getFromPrmtChgDate()))  return false;
        }
         else {
            if (aAVAPENModel.getFromPrmtChgDate() != null)  return false;
        }
        if (this.getFromPrmtChgProj() != null) { 
            if (!this.getFromPrmtChgProj().equals(aAVAPENModel.getFromPrmtChgProj()))  return false;
        }
         else {
            if (aAVAPENModel.getFromPrmtChgProj() != null)  return false;
        }
        if (this.getFromExportFile() != null) { 
            if (!this.getFromExportFile().equals(aAVAPENModel.getFromExportFile()))  return false;
        }
         else {
            if (aAVAPENModel.getFromExportFile() != null)  return false;
        }
        if (this.getToPrmtEnvrm() != null) { 
            if (!this.getToPrmtEnvrm().equals(aAVAPENModel.getToPrmtEnvrm()))  return false;
        }
         else {
            if (aAVAPENModel.getToPrmtEnvrm() != null)  return false;
        }
        if (this.getToPrmtDate() != null) { 
            if (!this.getToPrmtDate().equals(aAVAPENModel.getToPrmtDate()))  return false;
        }
         else {
            if (aAVAPENModel.getToPrmtDate() != null)  return false;
        }
        if (this.getToPrmtTimestamp() != null) { 
            if (!this.getToPrmtTimestamp().equals(aAVAPENModel.getToPrmtTimestamp()))  return false;
        }
         else {
            if (aAVAPENModel.getToPrmtTimestamp() != null)  return false;
        }
        if (this.getToPrmtBy() != null) { 
            if (!this.getToPrmtBy().equals(aAVAPENModel.getToPrmtBy()))  return false;
        }
         else {
            if (aAVAPENModel.getToPrmtBy() != null)  return false;
        }
        if (this.getToPrmtOption() != null) { 
            if (!this.getToPrmtOption().equals(aAVAPENModel.getToPrmtOption()))  return false;
        }
         else {
            if (aAVAPENModel.getToPrmtOption() != null)  return false;
        }
        if (this.getToPrmtChgDate() != null) { 
            if (!this.getToPrmtChgDate().equals(aAVAPENModel.getToPrmtChgDate()))  return false;
        }
         else {
            if (aAVAPENModel.getToPrmtChgDate() != null)  return false;
        }
        if (this.getToPrmtChgProj() != null) { 
            if (!this.getToPrmtChgProj().equals(aAVAPENModel.getToPrmtChgProj()))  return false;
        }
         else {
            if (aAVAPENModel.getToPrmtChgProj() != null)  return false;
        }
        if (this.getToExportFile() != null) { 
            if (!this.getToExportFile().equals(aAVAPENModel.getToExportFile()))  return false;
        }
         else {
            if (aAVAPENModel.getToExportFile() != null)  return false;
        }
        if (this.getLstChangedBy() != null) { 
            if (!this.getLstChangedBy().equals(aAVAPENModel.getLstChangedBy()))  return false;
        }
         else {
            if (aAVAPENModel.getLstChangedBy() != null)  return false;
        }
        if (this.getLstChangeDate() != null) { 
            if (!this.getLstChangeDate().equals(aAVAPENModel.getLstChangeDate()))  return false;
        }
         else {
            if (aAVAPENModel.getLstChangeDate() != null)  return false;
        }
        if (this.getTimeStamp() != null) { 
            if (!this.getTimeStamp().equals(aAVAPENModel.getTimeStamp()))  return false;
        }
         else {
            if (aAVAPENModel.getTimeStamp() != null)  return false;
        }
        return true;
    } 

	@Override
	public int hashCode()
	{
		StringBuilder buf = new StringBuilder();
		buf.append(getApplKey());
		buf.append(getEnvrnmntKey());
		buf.append(getDescription());
		buf.append(getSubsystem());
		buf.append(getSchema());
		buf.append(getFromPrmtEnvrm());
		buf.append(getFromPrmtDate());
		buf.append(getFromPrmtTimestamp());
		buf.append(getFromPrmtBy());
		buf.append(getFromPrmtOption());
		buf.append(getFromPrmtChgDate());
		buf.append(getFromPrmtChgProj());
		buf.append(getFromExportFile());
		buf.append(getToPrmtEnvrm());
		buf.append(getToPrmtDate());
		buf.append(getToPrmtTimestamp());
		buf.append(getToPrmtBy());
		buf.append(getToPrmtOption());
		buf.append(getToPrmtChgDate());
		buf.append(getToPrmtChgProj());
		buf.append(getToExportFile());
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
        AVAPENModel aAVAPENModel = (AVAPENModel)aObject;
        if (this.getApplKey() != null) { 
            if (!this.getApplKey().equals(aAVAPENModel.getApplKey()))  return true;
        }
         else {
             if (aAVAPENModel.getApplKey() != null)  return true;
        }
        if (this.getEnvrnmntKey() != null) { 
            if (!this.getEnvrnmntKey().equals(aAVAPENModel.getEnvrnmntKey()))  return true;
        }
         else {
             if (aAVAPENModel.getEnvrnmntKey() != null)  return true;
        }
        return false;
    } 

    /** 
        Returns true if all required fields are non-null.
        @return the true all required fields are non-null, false if not.
     **/ 
    public boolean isRequiredFieldsNotNull(){ 
           if (this.getApplKey() == null)  return false;
           if (this.getEnvrnmntKey() == null)  return false;
           if (this.getDescription() == null)  return false;
        return true;
    } 

    /** 
        Returns true if all primary key fields are non-null.
        @return the true all primary key fields are non-null, false if not.
     **/ 
    public boolean isPrimaryKeysNotNull(){ 
        if (this.getApplKey() == null)  return false;
        if (this.getEnvrnmntKey() == null)  return false;
        return true;
    } 

    @Override
    public String toString(){ 
        StringBuffer sb = new StringBuffer();
        sb.append ("ApplKey="+this.getApplKey()+"  ");
        sb.append ("EnvrnmntKey="+this.getEnvrnmntKey()+"  ");
        sb.append ("Description="+this.getDescription()+"  ");
        sb.append ("Subsystem="+this.getSubsystem()+"  ");
        sb.append ("Schema="+this.getSchema()+"  ");
        sb.append ("FromPrmtEnvrm="+this.getFromPrmtEnvrm()+"  ");
        sb.append ("FromPrmtDate="+this.getFromPrmtDate()+"  ");
        sb.append ("FromPrmtTimestamp="+this.getFromPrmtTimestamp()+"  ");
        sb.append ("FromPrmtBy="+this.getFromPrmtBy()+"  ");
        sb.append ("FromPrmtOption="+this.getFromPrmtOption()+"  ");
        sb.append ("FromPrmtChgDate="+this.getFromPrmtChgDate()+"  ");
        sb.append ("FromPrmtChgProj="+this.getFromPrmtChgProj()+"  ");
        sb.append ("FromExportFile="+this.getFromExportFile()+"  ");
        sb.append ("ToPrmtEnvrm="+this.getToPrmtEnvrm()+"  ");
        sb.append ("ToPrmtDate="+this.getToPrmtDate()+"  ");
        sb.append ("ToPrmtTimestamp="+this.getToPrmtTimestamp()+"  ");
        sb.append ("ToPrmtBy="+this.getToPrmtBy()+"  ");
        sb.append ("ToPrmtOption="+this.getToPrmtOption()+"  ");
        sb.append ("ToPrmtChgDate="+this.getToPrmtChgDate()+"  ");
        sb.append ("ToPrmtChgProj="+this.getToPrmtChgProj()+"  ");
        sb.append ("ToExportFile="+this.getToExportFile()+"  ");
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
        	modelColumn = new ModelColumn("DESCRIPTION");
        	if (dbCopy != null) {
        		if (dbCopy.getDescription() != null) {
        			modelColumn.setOldData(dbCopy.getDescription().toString());
        		}
        	}
        	if (getDescription() != null) {
        		modelColumn.setNewData(getDescription().toString());
        	}
        	retval.add(modelColumn);
        	modelColumn = new ModelColumn("SUBSYSTEM");
        	if (dbCopy != null) {
        		if (dbCopy.getSubsystem() != null) {
        			modelColumn.setOldData(dbCopy.getSubsystem().toString());
        		}
        	}
        	if (getSubsystem() != null) {
        		modelColumn.setNewData(getSubsystem().toString());
        	}
        	retval.add(modelColumn);
        	modelColumn = new ModelColumn("SCHEMA");
        	if (dbCopy != null) {
        		if (dbCopy.getSchema() != null) {
        			modelColumn.setOldData(dbCopy.getSchema().toString());
        		}
        	}
        	if (getSchema() != null) {
        		modelColumn.setNewData(getSchema().toString());
        	}
        	retval.add(modelColumn);
        	modelColumn = new ModelColumn("FROM_PRMT_ENVRM");
        	if (dbCopy != null) {
        		if (dbCopy.getFromPrmtEnvrm() != null) {
        			modelColumn.setOldData(dbCopy.getFromPrmtEnvrm().toString());
        		}
        	}
        	if (getFromPrmtEnvrm() != null) {
        		modelColumn.setNewData(getFromPrmtEnvrm().toString());
        	}
        	retval.add(modelColumn);
        	modelColumn = new ModelColumn("FROM_PRMT_DATE");
        	if (dbCopy != null) {
        		if (dbCopy.getFromPrmtDate() != null) {
        			modelColumn.setOldData(dbCopy.getFromPrmtDate().toString());
        		}
        	}
        	if (getFromPrmtDate() != null) {
        		modelColumn.setNewData(getFromPrmtDate().toString());
        	}
        	retval.add(modelColumn);
        	modelColumn = new ModelColumn("FROM_PRMT_TIMESTAMP");
        	if (dbCopy != null) {
        		if (dbCopy.getFromPrmtTimestamp() != null) {
        			modelColumn.setOldData(dbCopy.getFromPrmtTimestamp().toString());
        		}
        	}
        	if (getFromPrmtTimestamp() != null) {
        		modelColumn.setNewData(getFromPrmtTimestamp().toString());
        	}
        	retval.add(modelColumn);
        	modelColumn = new ModelColumn("FROM_PRMT_BY");
        	if (dbCopy != null) {
        		if (dbCopy.getFromPrmtBy() != null) {
        			modelColumn.setOldData(dbCopy.getFromPrmtBy().toString());
        		}
        	}
        	if (getFromPrmtBy() != null) {
        		modelColumn.setNewData(getFromPrmtBy().toString());
        	}
        	retval.add(modelColumn);
        	modelColumn = new ModelColumn("FROM_PRMT_OPTION");
        	if (dbCopy != null) {
        		if (dbCopy.getFromPrmtOption() != null) {
        			modelColumn.setOldData(dbCopy.getFromPrmtOption().toString());
        		}
        	}
        	if (getFromPrmtOption() != null) {
        		modelColumn.setNewData(getFromPrmtOption().toString());
        	}
        	retval.add(modelColumn);
        	modelColumn = new ModelColumn("FROM_PRMT_CHG_DATE");
        	if (dbCopy != null) {
        		if (dbCopy.getFromPrmtChgDate() != null) {
        			modelColumn.setOldData(dbCopy.getFromPrmtChgDate().toString());
        		}
        	}
        	if (getFromPrmtChgDate() != null) {
        		modelColumn.setNewData(getFromPrmtChgDate().toString());
        	}
        	retval.add(modelColumn);
        	modelColumn = new ModelColumn("FROM_PRMT_CHG_PROJ");
        	if (dbCopy != null) {
        		if (dbCopy.getFromPrmtChgProj() != null) {
        			modelColumn.setOldData(dbCopy.getFromPrmtChgProj().toString());
        		}
        	}
        	if (getFromPrmtChgProj() != null) {
        		modelColumn.setNewData(getFromPrmtChgProj().toString());
        	}
        	retval.add(modelColumn);
        	modelColumn = new ModelColumn("FROM_EXPORT_FILE");
        	if (dbCopy != null) {
        		if (dbCopy.getFromExportFile() != null) {
        			modelColumn.setOldData(dbCopy.getFromExportFile().toString());
        		}
        	}
        	if (getFromExportFile() != null) {
        		modelColumn.setNewData(getFromExportFile().toString());
        	}
        	retval.add(modelColumn);
        	modelColumn = new ModelColumn("TO_PRMT_ENVRM");
        	if (dbCopy != null) {
        		if (dbCopy.getToPrmtEnvrm() != null) {
        			modelColumn.setOldData(dbCopy.getToPrmtEnvrm().toString());
        		}
        	}
        	if (getToPrmtEnvrm() != null) {
        		modelColumn.setNewData(getToPrmtEnvrm().toString());
        	}
        	retval.add(modelColumn);
        	modelColumn = new ModelColumn("TO_PRMT_DATE");
        	if (dbCopy != null) {
        		if (dbCopy.getToPrmtDate() != null) {
        			modelColumn.setOldData(dbCopy.getToPrmtDate().toString());
        		}
        	}
        	if (getToPrmtDate() != null) {
        		modelColumn.setNewData(getToPrmtDate().toString());
        	}
        	retval.add(modelColumn);
        	modelColumn = new ModelColumn("TO_PRMT_TIMESTAMP");
        	if (dbCopy != null) {
        		if (dbCopy.getToPrmtTimestamp() != null) {
        			modelColumn.setOldData(dbCopy.getToPrmtTimestamp().toString());
        		}
        	}
        	if (getToPrmtTimestamp() != null) {
        		modelColumn.setNewData(getToPrmtTimestamp().toString());
        	}
        	retval.add(modelColumn);
        	modelColumn = new ModelColumn("TO_PRMT_BY");
        	if (dbCopy != null) {
        		if (dbCopy.getToPrmtBy() != null) {
        			modelColumn.setOldData(dbCopy.getToPrmtBy().toString());
        		}
        	}
        	if (getToPrmtBy() != null) {
        		modelColumn.setNewData(getToPrmtBy().toString());
        	}
        	retval.add(modelColumn);
        	modelColumn = new ModelColumn("TO_PRMT_OPTION");
        	if (dbCopy != null) {
        		if (dbCopy.getToPrmtOption() != null) {
        			modelColumn.setOldData(dbCopy.getToPrmtOption().toString());
        		}
        	}
        	if (getToPrmtOption() != null) {
        		modelColumn.setNewData(getToPrmtOption().toString());
        	}
        	retval.add(modelColumn);
        	modelColumn = new ModelColumn("TO_PRMT_CHG_DATE");
        	if (dbCopy != null) {
        		if (dbCopy.getToPrmtChgDate() != null) {
        			modelColumn.setOldData(dbCopy.getToPrmtChgDate().toString());
        		}
        	}
        	if (getToPrmtChgDate() != null) {
        		modelColumn.setNewData(getToPrmtChgDate().toString());
        	}
        	retval.add(modelColumn);
        	modelColumn = new ModelColumn("TO_PRMT_CHG_PROJ");
        	if (dbCopy != null) {
        		if (dbCopy.getToPrmtChgProj() != null) {
        			modelColumn.setOldData(dbCopy.getToPrmtChgProj().toString());
        		}
        	}
        	if (getToPrmtChgProj() != null) {
        		modelColumn.setNewData(getToPrmtChgProj().toString());
        	}
        	retval.add(modelColumn);
        	modelColumn = new ModelColumn("TO_EXPORT_FILE");
        	if (dbCopy != null) {
        		if (dbCopy.getToExportFile() != null) {
        			modelColumn.setOldData(dbCopy.getToExportFile().toString());
        		}
        	}
        	if (getToExportFile() != null) {
        		modelColumn.setNewData(getToExportFile().toString());
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
        Element element = aDocument.createElement("AVAPENModel");
        element.setAttribute("applKey", toSafeString(this.getApplKey()));
        element.setAttribute("envrnmntKey", toSafeString(this.getEnvrnmntKey()));
        element.setAttribute("description", toSafeString(this.getDescription()));
        element.setAttribute("subsystem", toSafeString(this.getSubsystem()));
        element.setAttribute("schema", toSafeString(this.getSchema()));
        element.setAttribute("fromPrmtEnvrm", toSafeString(this.getFromPrmtEnvrm()));
        element.setAttribute("fromPrmtDate", toSafeString(this.getFromPrmtDate()));
        element.setAttribute("fromPrmtTimestamp", toSafeString(this.getFromPrmtTimestamp()));
        element.setAttribute("fromPrmtBy", toSafeString(this.getFromPrmtBy()));
        element.setAttribute("fromPrmtOption", toSafeString(this.getFromPrmtOption()));
        element.setAttribute("fromPrmtChgDate", toSafeString(this.getFromPrmtChgDate()));
        element.setAttribute("fromPrmtChgProj", toSafeString(this.getFromPrmtChgProj()));
        element.setAttribute("fromExportFile", toSafeString(this.getFromExportFile()));
        element.setAttribute("toPrmtEnvrm", toSafeString(this.getToPrmtEnvrm()));
        element.setAttribute("toPrmtDate", toSafeString(this.getToPrmtDate()));
        element.setAttribute("toPrmtTimestamp", toSafeString(this.getToPrmtTimestamp()));
        element.setAttribute("toPrmtBy", toSafeString(this.getToPrmtBy()));
        element.setAttribute("toPrmtOption", toSafeString(this.getToPrmtOption()));
        element.setAttribute("toPrmtChgDate", toSafeString(this.getToPrmtChgDate()));
        element.setAttribute("toPrmtChgProj", toSafeString(this.getToPrmtChgProj()));
        element.setAttribute("toExportFile", toSafeString(this.getToExportFile()));
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
        AVAPENModel aVAPENModel = new AVAPENModel();
        aVAPENModel.setApplKey(aElement.getAttribute("applKey"));
        aVAPENModel.setEnvrnmntKey(aElement.getAttribute("envrnmntKey"));
        aVAPENModel.setDescription(aElement.getAttribute("description"));
        aVAPENModel.setSubsystem(aElement.getAttribute("subsystem"));
        aVAPENModel.setSchema(aElement.getAttribute("schema"));
        aVAPENModel.setFromPrmtEnvrm(aElement.getAttribute("fromPrmtEnvrm"));
        aVAPENModel.setFromPrmtDate(toDate(aElement.getAttribute("fromPrmtDate")));
        aVAPENModel.setFromPrmtBy(aElement.getAttribute("fromPrmtBy"));
        aVAPENModel.setFromPrmtOption(aElement.getAttribute("fromPrmtOption"));
        aVAPENModel.setFromPrmtChgDate(toDate(aElement.getAttribute("fromPrmtChgDate")));
        aVAPENModel.setFromPrmtChgProj(aElement.getAttribute("fromPrmtChgProj"));
        aVAPENModel.setFromExportFile(aElement.getAttribute("fromExportFile"));
        aVAPENModel.setToPrmtEnvrm(aElement.getAttribute("toPrmtEnvrm"));
        aVAPENModel.setToPrmtDate(toDate(aElement.getAttribute("toPrmtDate")));
        aVAPENModel.setToPrmtBy(aElement.getAttribute("toPrmtBy"));
        aVAPENModel.setToPrmtOption(aElement.getAttribute("toPrmtOption"));
        aVAPENModel.setToPrmtChgDate(toDate(aElement.getAttribute("toPrmtChgDate")));
        aVAPENModel.setToPrmtChgProj(aElement.getAttribute("toPrmtChgProj"));
        aVAPENModel.setToExportFile(aElement.getAttribute("toExportFile"));
        aVAPENModel.setLstChangedBy(aElement.getAttribute("lstChangedBy"));
        aVAPENModel.setLstChangeDate(toDate(aElement.getAttribute("lstChangeDate")));
        return aVAPENModel;
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
        	dbCopy = (AVAPENModel)aDAOModel;
        }
        else
        {
        	dbCopy = null;
        }
    } 

    /** 
        Returns an instance of the DAO access class (AVAPENDAO) for this model.
        @return an instance of the DAO access class for this model.
     **/ 
    @Override
    public DataAccessor getDataAccessorObject(){ 
        return new AVAPENDAO();
    } 
}
