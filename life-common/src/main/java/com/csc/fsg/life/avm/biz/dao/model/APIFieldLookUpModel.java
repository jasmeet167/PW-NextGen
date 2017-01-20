package com.csc.fsg.life.avm.biz.dao.model;

import java.util.ArrayList;

import com.csc.fsg.life.dao.model.DAOModel;
import com.csc.fsg.life.dao.model.ModelColumn;

/* Modifications: T0175 */

/** 
    Table Model data transfer object APIFieldLookUpModel.
 **/ 
public class APIFieldLookUpModel 
	extends DAOModel 
	implements java.io.Serializable 
{ 
    /** 
        Property applKey for table APIFieldLookUp.
     **/ 
    private String applKey;
    /** 
        Property envrnmntKey for table APIFieldLookUp.
     **/ 
    private String envrnmntKey;
    /** 
        Property pageKey for table APIFieldLookUp.
     **/ 
    private String pageKey;
    /** 
        Property companyKey for table APIFieldLookUp.
     **/ 
    private String companyKey;
    /** 
        Property product for table APIFieldLookUp.
     **/ 
    private String product;
    /** 
        Property trxCode for table APIFieldLookUp.
     **/ 
    private String trxCode;
    /** 
        Property fieldKey for table APIFieldLookUp.
     **/ 
    private String fieldKey;
    /** 
        Property fieldType for table APIFieldLookUp.
     **/ 
    private String fieldType;
    /** 
        Property listDefault for table APIFieldLookUp.
     **/ 
    private String listDefault;
    /** 
        Property rangeDefault for table APIFieldLookUp.
     **/ 
    private Double rangeDefault;
    
    private APIFieldLookUpModel dbCopy;

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
        Returns property companyKey for this object.
        @return the property companyKey for this object.
        @see #setCompanyKey
     **/ 
    public String getCompanyKey(){ 
        return this.companyKey;
    } 
    /** 
        Sets the property companyKey for this object.
        @param aString the new value for this property.
        @see #getCompanyKey
     **/ 
    public void setCompanyKey(String aString){ 
        companyKey = aString;
    } 

    /** 
        Returns property product for this object.
        @return the property product for this object.
        @see #setProduct
     **/ 
    public String getProduct(){ 
        return this.product;
    } 
    /** 
        Sets the property product for this object.
        @param aString the new value for this property.
        @see #getProduct
     **/ 
    public void setProduct(String aString){ 
        product = aString;
    } 

    /** 
        Returns property trxCode for this object.
        @return the property trxCode for this object.
        @see #setTrxCode
     **/ 
    public String getTrxCode(){ 
        return this.trxCode;
    } 
    /** 
        Sets the property trxCode for this object.
        @param aString the new value for this property.
        @see #getTrxCode
     **/ 
    public void setTrxCode(String aString){ 
        trxCode = aString;
    } 

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
        Returns property fieldType for this object.
        @return the property fieldType for this object.
        @see #setFieldType
     **/ 
    public String getFieldType(){ 
        return this.fieldType;
    } 
    /** 
        Sets the property fieldType for this object.
        @param aString the new value for this property.
        @see #getFieldType
     **/ 
    public void setFieldType(String aString){ 
        fieldType = aString;
    } 

    /** 
        Returns property listDefault for this object.
        @return the property listDefault for this object.
        @see #setListDefault
     **/ 
    public String getListDefault(){ 
        return this.listDefault;
    } 
    /** 
        Sets the property listDefault for this object.
        @param aString the new value for this property.
        @see #getListDefault
     **/ 
    public void setListDefault(String aString){ 
        listDefault = aString;
    } 

    /** 
        Returns property rangeDefault for this object.
        @return the property rangeDefault for this object.
        @see #setRangeDefault
     **/ 
    public Double getRangeDefault(){ 
        return this.rangeDefault;
    } 
    /** 
        Sets the property rangeDefault for this object.
        @param aDouble the new value for this property.
        @see #getRangeDefault
     **/ 
    public void setRangeDefault(Double aDouble){ 
        rangeDefault = aDouble;
    } 

    /** 
        Returns property dbCopy for this object.
        @return the property dbCopy for this object.
        @see #setDbCopy
     **/ 
    public APIFieldLookUpModel getDbCopy(){ 
        return this.dbCopy;
    } 
    /** 
        Sets the property dbCopy for this object.
        @param aAPIFieldLookUpModel the new value for this property.
        @see #getDbCopy
     **/ 
    public void setDbCopy(APIFieldLookUpModel aAPIFieldLookUpModel){ 
        dbCopy = aAPIFieldLookUpModel;
    } 
    /** 
        Default constructor for APIFieldLookUpModel.
     **/ 
    public  APIFieldLookUpModel(){ 
        super();
        dbCopy = null;
    } 

    /** 
        Constructor for APIFieldLookUpModel with state.
        @param aint the state of this model object.
     **/ 
    public  APIFieldLookUpModel(int aint){ 
        super(aint);
        dbCopy = null;
    } 

    /** 
        Returns the model name.
        @return the model name.
     **/ 
    @Override
    public String getModelName(){ 
        return "com.csc.fsg.life.avm.biz.dao.model.APIFieldLookUpModel";
    } 

    /** 
        Returns the table name APIFieldLookUp.
        @return the table name.
     **/ 
    public String getTableName(){ 
        return "APIFieldLookUp";
    } 

    /** 
        Returns a new instance of this class.
        @return the new instance.
     **/ 
    @Override
    public DAOModel createNew(){ 
        return new APIFieldLookUpModel();
    } 

    /** 
        Returns a clone of this instance.
        @return the clone instance.
     **/ 
    public DAOModel createCopy(){ 
        APIFieldLookUpModel newModel = new APIFieldLookUpModel();
        newModel.setApplKey(this.getApplKey());
        newModel.setEnvrnmntKey(this.getEnvrnmntKey());
        newModel.setPageKey(this.getPageKey());
        newModel.setCompanyKey(this.getCompanyKey());
        newModel.setProduct(this.getProduct());
        newModel.setTrxCode(this.getTrxCode());
        newModel.setFieldKey(this.getFieldKey());
        newModel.setFieldType(this.getFieldType());
        newModel.setListDefault(this.getListDefault());
        newModel.setRangeDefault(this.getRangeDefault());
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
        if (this.getCompanyKey() != null) { 
             if (!this.getCompanyKey().equals(dbCopy.getCompanyKey()))  return true;
        }
         else {
             if (dbCopy.getCompanyKey() != null)  return true;
        }
        if (this.getProduct() != null) { 
             if (!this.getProduct().equals(dbCopy.getProduct()))  return true;
        }
         else {
             if (dbCopy.getProduct() != null)  return true;
        }
        if (this.getTrxCode() != null) { 
             if (!this.getTrxCode().equals(dbCopy.getTrxCode()))  return true;
        }
         else {
             if (dbCopy.getTrxCode() != null)  return true;
        }
        if (this.getFieldKey() != null) { 
             if (!this.getFieldKey().equals(dbCopy.getFieldKey()))  return true;
        }
         else {
             if (dbCopy.getFieldKey() != null)  return true;
        }
        if (this.getFieldType() != null) { 
             if (!this.getFieldType().equals(dbCopy.getFieldType()))  return true;
        }
         else {
             if (dbCopy.getFieldType() != null)  return true;
        }
        if (this.getListDefault() != null) { 
             if (!this.getListDefault().equals(dbCopy.getListDefault()))  return true;
        }
         else {
             if (dbCopy.getListDefault() != null)  return true;
        }
        if (this.getRangeDefault() != null) { 
             if (!this.getRangeDefault().equals(dbCopy.getRangeDefault()))  return true;
        }
         else {
             if (dbCopy.getRangeDefault() != null)  return true;
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
        APIFieldLookUpModel aAPIFieldLookUpModel = (APIFieldLookUpModel)aObject;
        if (this.getApplKey() != null) { 
            if (!this.getApplKey().equals(aAPIFieldLookUpModel.getApplKey()))  return false;
        }
         else {
            if (aAPIFieldLookUpModel.getApplKey() != null)  return false;
        }
        if (this.getEnvrnmntKey() != null) { 
            if (!this.getEnvrnmntKey().equals(aAPIFieldLookUpModel.getEnvrnmntKey()))  return false;
        }
         else {
            if (aAPIFieldLookUpModel.getEnvrnmntKey() != null)  return false;
        }
        if (this.getPageKey() != null) { 
            if (!this.getPageKey().equals(aAPIFieldLookUpModel.getPageKey()))  return false;
        }
         else {
            if (aAPIFieldLookUpModel.getPageKey() != null)  return false;
        }
        if (this.getCompanyKey() != null) { 
            if (!this.getCompanyKey().equals(aAPIFieldLookUpModel.getCompanyKey()))  return false;
        }
         else {
            if (aAPIFieldLookUpModel.getCompanyKey() != null)  return false;
        }
        if (this.getProduct() != null) { 
            if (!this.getProduct().equals(aAPIFieldLookUpModel.getProduct()))  return false;
        }
         else {
            if (aAPIFieldLookUpModel.getProduct() != null)  return false;
        }
        if (this.getTrxCode() != null) { 
            if (!this.getTrxCode().equals(aAPIFieldLookUpModel.getTrxCode()))  return false;
        }
         else {
            if (aAPIFieldLookUpModel.getTrxCode() != null)  return false;
        }
        if (this.getFieldKey() != null) { 
            if (!this.getFieldKey().equals(aAPIFieldLookUpModel.getFieldKey()))  return false;
        }
         else {
            if (aAPIFieldLookUpModel.getFieldKey() != null)  return false;
        }
        if (this.getFieldType() != null) { 
            if (!this.getFieldType().equals(aAPIFieldLookUpModel.getFieldType()))  return false;
        }
         else {
            if (aAPIFieldLookUpModel.getFieldType() != null)  return false;
        }
        if (this.getListDefault() != null) { 
            if (!this.getListDefault().equals(aAPIFieldLookUpModel.getListDefault()))  return false;
        }
         else {
            if (aAPIFieldLookUpModel.getListDefault() != null)  return false;
        }
        if (this.getRangeDefault() != null) { 
            if (!this.getRangeDefault().equals(aAPIFieldLookUpModel.getRangeDefault()))  return false;
        }
         else {
            if (aAPIFieldLookUpModel.getRangeDefault() != null)  return false;
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
		buf.append(getCompanyKey());
		buf.append(getProduct());
		buf.append(getTrxCode());
		buf.append(getFieldKey());
		buf.append(getFieldType());
		buf.append(getListDefault());
		buf.append(getRangeDefault());
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
           if (this.getCompanyKey() == null)  return false;
           if (this.getProduct() == null)  return false;
           if (this.getTrxCode() == null)  return false;
           if (this.getFieldKey() == null)  return false;
           if (this.getFieldType() == null)  return false;
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
        sb.append ("CompanyKey="+this.getCompanyKey()+"  ");
        sb.append ("Product="+this.getProduct()+"  ");
        sb.append ("TrxCode="+this.getTrxCode()+"  ");
        sb.append ("FieldKey="+this.getFieldKey()+"  ");
        sb.append ("FieldType="+this.getFieldType()+"  ");
        sb.append ("ListDefault="+this.getListDefault()+"  ");
        sb.append ("RangeDefault="+this.getRangeDefault()+"  ");
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
        	modelColumn = new ModelColumn("COMPANY_KEY");
        	if (dbCopy != null) {
        		if (dbCopy.getCompanyKey() != null) {
        			modelColumn.setOldData(dbCopy.getCompanyKey().toString());
        		}
        	}
        	if (getCompanyKey() != null) {
        		modelColumn.setNewData(getCompanyKey().toString());
        	}
        	retval.add(modelColumn);
        	modelColumn = new ModelColumn("PRODUCT");
        	if (dbCopy != null) {
        		if (dbCopy.getProduct() != null) {
        			modelColumn.setOldData(dbCopy.getProduct().toString());
        		}
        	}
        	if (getProduct() != null) {
        		modelColumn.setNewData(getProduct().toString());
        	}
        	retval.add(modelColumn);
        	modelColumn = new ModelColumn("TRX_CODE");
        	if (dbCopy != null) {
        		if (dbCopy.getTrxCode() != null) {
        			modelColumn.setOldData(dbCopy.getTrxCode().toString());
        		}
        	}
        	if (getTrxCode() != null) {
        		modelColumn.setNewData(getTrxCode().toString());
        	}
        	retval.add(modelColumn);
        	modelColumn = new ModelColumn("FIELD_KEY");
        	if (dbCopy != null) {
        		if (dbCopy.getFieldKey() != null) {
        			modelColumn.setOldData(dbCopy.getFieldKey().toString());
        		}
        	}
        	if (getFieldKey() != null) {
        		modelColumn.setNewData(getFieldKey().toString());
        	}
        	retval.add(modelColumn);
        	modelColumn = new ModelColumn("FIELD_TYPE");
        	if (dbCopy != null) {
        		if (dbCopy.getFieldType() != null) {
        			modelColumn.setOldData(dbCopy.getFieldType().toString());
        		}
        	}
        	if (getFieldType() != null) {
        		modelColumn.setNewData(getFieldType().toString());
        	}
        	retval.add(modelColumn);
        	modelColumn = new ModelColumn("LIST_DEFAULT");
        	if (dbCopy != null) {
        		if (dbCopy.getListDefault() != null) {
        			modelColumn.setOldData(dbCopy.getListDefault().toString());
        		}
        	}
        	if (getListDefault() != null) {
        		modelColumn.setNewData(getListDefault().toString());
        	}
        	retval.add(modelColumn);
        	modelColumn = new ModelColumn("RANGE_DEFAULT");
        	if (dbCopy != null) {
        		if (dbCopy.getRangeDefault() != null) {
        			modelColumn.setOldData(dbCopy.getRangeDefault().toString());
        		}
        	}
        	if (getRangeDefault() != null) {
        		modelColumn.setNewData(getRangeDefault().toString());
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
        	dbCopy = (APIFieldLookUpModel)aDAOModel;
        }
        else {
        	dbCopy = null;
        }
    } 
}