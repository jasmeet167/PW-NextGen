package com.csc.fsg.life.avm.biz.dao.model;

import java.util.ArrayList;

import com.csc.fsg.life.dao.model.DAOModel;
import com.csc.fsg.life.dao.model.ModelColumn;

/* Modifications: T0175 */

/**
 * FieldInfoModel: Model to hold information on a field.  Combination of info
 * from AVPAGE, AVALFLD and AVFLD.  
 */
public class FieldInfoModel 
	extends DAOModel 
	implements java.io.Serializable 
{ 
    private String applKey;
    private String envrnmntKey;
    private String pageKey;
    private String pageId;
    private String pageDesc;
    private String companyKey;
    private String product;
    private String trxCode;
    private String fieldKey;
    private String fieldName;
    private String fieldDesc;
    private String fieldType;
    private String listDefault;
    private String rangeDefault;
    private FieldInfoModel dbCopy;

	/**
	   Returns the ApplKey.
	   @return the ApplKey for this object.
	   @see #setApplKey
	**/
    public String getApplKey(){ 
        return this.applKey;
    } 
	/**
	   Sets the ApplKey.
	   @param aString The new ApplKey value.
	   @see #getApplKey
	 **/
    public void setApplKey(String aString){ 
        applKey = aString;
    } 

	/**
	   Returns the EnvrnmntKey.
	   @return the EnvrnmntKey for this object.
	   @see #setEnvrnmntKey
	**/
    public String getEnvrnmntKey(){ 
        return this.envrnmntKey;
    } 
	/**
	   Sets the EnvrnmntKey.
	   @param aString The new EnvrnmntKey value.
	   @see #getEnvrnmntKey
	 **/
    public void setEnvrnmntKey(String aString){ 
        envrnmntKey = aString;
    } 

	/**
	   Returns the PageKey.
	   @return the PageKey for this object.
	   @see #setPageKey
	**/
    public String getPageKey(){ 
        return this.pageKey;
    } 
	/**
	   Sets the PageKey.
	   @param aString The new PageKey value.
	   @see #getPageKey
	 **/
    public void setPageKey(String aString){ 
        pageKey = aString;
    } 

	/**
	   Returns the PageId.
	   @return the PageId for this object.
	   @see #setPageId
	**/
    public String getPageId(){ 
        return this.pageId;
    } 
	/**
	   Sets the PageId.
	   @param aString The new PageId value.
	   @see #getPageId
	 **/
    public void setPageId(String aString){ 
        pageId = aString;
    } 

	/**
	   Returns the PageDesc.
	   @return the PageDesc for this object.
	   @see #setPageDesc
	**/
    public String getPageDesc(){ 
        return this.pageDesc;
    } 
	/**
	   Sets the PageDesc.
	   @param aString The new PageDesc value.
	   @see #getPageDesc
	 **/
    public void setPageDesc(String aString){ 
        pageDesc = aString;
    } 

	/**
	   Returns the CompanyKey.
	   @return the CompanyKey for this object.
	   @see #setCompanyKey
	**/
    public String getCompanyKey(){ 
        return this.companyKey;
    } 
	/**
	   Sets the CompanyKey.
	   @param aString The new CompanyKey value.
	   @see #getCompanyKey
	 **/
    public void setCompanyKey(String aString){ 
        companyKey = aString;
    } 

	/**
	   Returns the Product.
	   @return the Product for this object.
	   @see #setProduct
	**/
    public String getProduct(){ 
        return this.product;
    } 
	/**
	   Sets the Product.
	   @param aString The new Product value.
	   @see #getProduct
	 **/
    public void setProduct(String aString){ 
        product = aString;
    } 

	/**
	   Returns the TrxCode.
	   @return the TrxCode for this object.
	   @see #setTrxCode
	**/
    public String getTrxCode(){ 
        return this.trxCode;
    } 
	/**
	   Sets the TrxCode.
	   @param aString The new TrxCode value.
	   @see #getTrxCode
	 **/
    public void setTrxCode(String aString){ 
        trxCode = aString;
    } 

	/**
	   Returns the FieldKey.
	   @return the FieldKey for this object.
	   @see #setFieldKey
	**/
    public String getFieldKey(){ 
        return this.fieldKey;
    } 
	/**
	   Sets the FieldKey.
	   @param aString The new FieldKey value.
	   @see #getFieldKey
	 **/
    public void setFieldKey(String aString){ 
        fieldKey = aString;
    } 

	/**
	   Returns the FieldName.
	   @return the FieldName for this object.
	   @see #setFieldName
	**/
    public String getFieldName(){ 
        return this.fieldName;
    } 
	/**
	   Sets the FieldName.
	   @param aString The new FieldName value.
	   @see #getFieldName
	 **/
    public void setFieldName(String aString){ 
        fieldName = aString;
    } 

	/**
	   Returns the FieldDesc.
	   @return the FieldDesc for this object.
	   @see #setFieldDesc
	**/
    public String getFieldDesc(){ 
        return this.fieldDesc;
    } 
	/**
	   Sets the FieldDesc.
	   @param aString The new FieldDesc value.
	   @see #getFieldDesc
	 **/
    public void setFieldDesc(String aString){ 
        fieldDesc = aString;
    } 

	/**
	   Returns the FieldType.
	   @return the FieldType for this object.
	   @see #setFieldType
	**/
    public String getFieldType(){ 
        return this.fieldType;
    } 
	/**
	   Sets the FieldType.
	   @param aString The new FieldType value.
	   @see #getFieldType
	 **/
    public void setFieldType(String aString){ 
        fieldType = aString;
    } 

	/**
	   Returns the ListDefault.
	   @return the ListDefault for this object.
	   @see #setListDefault
	**/
    public String getListDefault(){ 
        return this.listDefault;
    } 
	/**
	   Sets the ListDefault.
	   @param aString The new ListDefault value.
	   @see #getListDefault
	 **/
    public void setListDefault(String aString){ 
        listDefault = aString;
    } 

	/**
	   Returns the RangeDefault.
	   @return the RangeDefault for this object.
	   @see #setRangeDefault
	**/
    public String getRangeDefault(){ 
        return this.rangeDefault;
    } 
	/**
	   Sets the RangeDefault.
	   @param aString The new RangeDefault value.
	   @see #getRangeDefault
	 **/
    public void setRangeDefault(String aString){ 
        rangeDefault = aString;
    } 

	/**
	   Returns the DbCopy.
	   @return the DbCopy for this object.
	   @see #setDbCopy
	**/
    public FieldInfoModel getDbCopy(){ 
        return this.dbCopy;
    } 
	/**
	   Sets the DbCopy.
	   @param aFieldInfoModel The new DbCopy value.
	   @see #getDbCopy
	 **/
    public void setDbCopy(FieldInfoModel aFieldInfoModel){ 
        dbCopy = aFieldInfoModel;
    } 

	/**
	   Creates a new FieldInfoModel object.
	**/
    public  FieldInfoModel(){ 
        super();
        dbCopy = null;
    } 

	/**
	   Creates a new FieldInfoModel object with the specified state.
	   @param aint The state.
	**/
    public  FieldInfoModel(int aint){ 
        super(aint);
        dbCopy = null;
    } 

	/**
	   Returns the ModelName.
	   @return the ModelName for this object.
	**/
    @Override
    public String getModelName(){ 
        return "com.csc.fsg.life.avm.biz.dao.model.FieldInfoModel";
    } 

	/**
	   Returns the TableName.
	   @return the TableName for this object.
	**/
    public String getTableName(){ 
        return "FieldInfo";
    } 

	/**
	   Creates a new instance of this object.
	   @return the new instance.
	**/
    @Override
    public DAOModel createNew(){ 
        return new FieldInfoModel();
    } 

	/**
	   Creates a new instance of this model that is a copy.
	   @return the new instance of this model that is a copy.
	**/
    public DAOModel createCopy(){ 
        FieldInfoModel newModel = new FieldInfoModel();
        newModel.setApplKey(this.getApplKey());
        newModel.setEnvrnmntKey(this.getEnvrnmntKey());
        newModel.setPageKey(this.getPageKey());
        newModel.setPageId(this.getPageId());
        newModel.setPageDesc(this.getPageDesc());
        newModel.setCompanyKey(this.getCompanyKey());
        newModel.setProduct(this.getProduct());
        newModel.setTrxCode(this.getTrxCode());
        newModel.setFieldKey(this.getFieldKey());
        newModel.setFieldName(this.getFieldName());
        newModel.setFieldDesc(this.getFieldDesc());
        newModel.setFieldType(this.getFieldType());
        newModel.setListDefault(this.getListDefault());
        newModel.setRangeDefault(this.getRangeDefault());
        newModel.setModelState(this.getModelState());
        return newModel;
    } 

	/**
	   Returns true if this object has different value that
	   the one originally created or retrieved from the DB.
	   @return true if this object is changed.
	**/
    @Override
    public boolean hasChanged(){ 
        if (dbCopy == null) return true;
        if (this.getApplKey() != null) { 
             if (!this.getApplKey().equals(dbCopy.getApplKey()))  return true;
        } else {
             if (dbCopy.getApplKey() != null)  return true;
        }
        if (this.getEnvrnmntKey() != null) { 
             if (!this.getEnvrnmntKey().equals(dbCopy.getEnvrnmntKey()))  return true;
        } else {
             if (dbCopy.getEnvrnmntKey() != null)  return true;
        }
        if (this.getPageKey() != null) { 
            if (!this.getPageKey().equals(dbCopy.getPageKey()))  return true;
        } else {
            if (dbCopy.getPageKey() != null)  return true;
        }
        if (this.getPageId() != null) { 
            if (!this.getPageId().equals(dbCopy.getPageId()))  return true;
        } else {
            if (dbCopy.getPageId() != null)  return true;
        }
        if (this.getPageDesc() != null) { 
            if (!this.getPageDesc().equals(dbCopy.getPageDesc()))  return true;
        } else {
            if (dbCopy.getPageDesc() != null)  return true;
        }
        if (this.getCompanyKey() != null) { 
            if (!this.getCompanyKey().equals(dbCopy.getCompanyKey()))  return true;
        } else {
        	if (dbCopy.getCompanyKey() != null)  return true;
        }
        if (this.getProduct() != null) { 
            if (!this.getProduct().equals(dbCopy.getProduct()))  return true;
        } else {
            if (dbCopy.getProduct() != null)  return true;
        }
        if (this.getTrxCode() != null) { 
            if (!this.getTrxCode().equals(dbCopy.getTrxCode()))  return true;
        } else {
            if (dbCopy.getTrxCode() != null)  return true;
        }
        if (this.getFieldKey() != null) { 
             if (!this.getFieldKey().equals(dbCopy.getFieldKey()))  return true;
        } else {
             if (dbCopy.getFieldKey() != null)  return true;
        }
		if (this.getFieldName() != null) { 
			if (!this.getFieldName().equals(dbCopy.getFieldName()))  return true;
		} else {
			if (dbCopy.getFieldName() != null)  return true;
		}
		if (this.getFieldDesc() != null) { 
			if (!this.getFieldDesc().equals(dbCopy.getFieldDesc()))  return true;
		} else {
			if (dbCopy.getFieldDesc() != null)  return true;
		}
		if (this.getFieldType() != null) { 
			if (!this.getFieldType().equals(dbCopy.getFieldType()))  return true;
		} else {
			if (dbCopy.getFieldType() != null)  return true;
		}
		if (this.getListDefault() != null) { 
			if (!this.getListDefault().equals(dbCopy.getListDefault()))  return true;
		} else {
			if (dbCopy.getListDefault() != null)  return true;
		}
		if (this.getRangeDefault() != null) { 
			if (!this.getRangeDefault().equals(dbCopy.getRangeDefault()))  return true;
		} else {
			if (dbCopy.getRangeDefault() != null)  return true;
		}
        return false;
    } 

    @Override
    public boolean equals(Object aObject){ 
        FieldInfoModel aFieldInfoModel = (FieldInfoModel)aObject;
        if (this.getApplKey() != null) { 
            if (!this.getApplKey().equals(aFieldInfoModel.getApplKey()))  return false;
        } else {
            if (aFieldInfoModel.getApplKey() != null)  return false;
        }
        if (this.getEnvrnmntKey() != null) { 
            if (!this.getEnvrnmntKey().equals(aFieldInfoModel.getEnvrnmntKey()))  return false;
        } else {
            if (aFieldInfoModel.getEnvrnmntKey() != null)  return false;
        }
		if (this.getPageKey() != null) { 
		    if (!this.getPageKey().equals(aFieldInfoModel.getPageKey()))  return false;
		} else {
		    if (aFieldInfoModel.getPageKey() != null)  return false;
		}
		if (this.getPageId() != null) { 
		    if (!this.getPageId().equals(aFieldInfoModel.getPageId()))  return false;
		} else {
		    if (aFieldInfoModel.getPageId() != null)  return false;
		}
		if (this.getPageDesc() != null) { 
		    if (!this.getPageDesc().equals(aFieldInfoModel.getPageDesc()))  return false;
		} else {
		    if (aFieldInfoModel.getPageDesc() != null)  return false;
		}
		if (this.getCompanyKey() != null) { 
		    if (!this.getCompanyKey().equals(aFieldInfoModel.getCompanyKey()))  return false;
		} else {
		    if (aFieldInfoModel.getCompanyKey() != null)  return false;
		}
		if (this.getProduct() != null) { 
		    if (!this.getProduct().equals(aFieldInfoModel.getProduct()))  return false;
		} else {
		    if (aFieldInfoModel.getProduct() != null)  return false;
		}
		if (this.getTrxCode() != null) { 
		    if (!this.getTrxCode().equals(aFieldInfoModel.getTrxCode()))  return false;
		} else {
		    if (aFieldInfoModel.getTrxCode() != null)  return false;
		}
        if (this.getFieldKey() != null) { 
            if (!this.getFieldKey().equals(aFieldInfoModel.getFieldKey()))  return false;
        } else {
            if (aFieldInfoModel.getFieldKey() != null)  return false;
        }
        if (this.getFieldName() != null) { 
            if (!this.getFieldName().equals(aFieldInfoModel.getFieldName()))  return false;
        } else {
            if (aFieldInfoModel.getFieldName() != null)  return false;
        }
        if (this.getFieldDesc() != null) { 
            if (!this.getFieldDesc().equals(aFieldInfoModel.getFieldDesc()))  return false;
        } else {
            if (aFieldInfoModel.getFieldDesc() != null)  return false;
        }
        if (this.getFieldType() != null) { 
            if (!this.getFieldType().equals(aFieldInfoModel.getFieldType()))  return false;
        } else {
            if (aFieldInfoModel.getFieldType() != null)  return false;
        }
        if (this.getListDefault() != null) { 
            if (!this.getListDefault().equals(aFieldInfoModel.getListDefault()))  return false;
        } else {
            if (aFieldInfoModel.getListDefault() != null)  return false;
        }
        if (this.getRangeDefault() != null) { 
            if (!this.getRangeDefault().equals(aFieldInfoModel.getRangeDefault()))  return false;
        } else {
            if (aFieldInfoModel.getRangeDefault() != null)  return false;
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
		buf.append(getCompanyKey());
		buf.append(getProduct());
		buf.append(getTrxCode());
		buf.append(getFieldKey());
		buf.append(getFieldName());
		buf.append(getFieldDesc());
		buf.append(getFieldType());
		buf.append(getListDefault());
		buf.append(getRangeDefault());
		return buf.toString().hashCode();
	}

    @Override
    public boolean hasKeysChanged(){ 
        if (dbCopy == null)  return true;
        return false;
    } 

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
           if (this.getCompanyKey() == null)  return false;
           if (this.getProduct() == null)  return false;
           if (this.getTrxCode() == null)  return false;
           if (this.getFieldKey() == null)  return false;
           if (this.getFieldName() == null)  return false;
           if (this.getFieldDesc() == null)  return false;
           if (this.getFieldType() == null)  return false;
           if (this.getListDefault() == null)  return false;
           if (this.getRangeDefault() == null)  return false;
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
        sb.append ("CompanyKey="+this.getCompanyKey()+"  ");
        sb.append ("Product="+this.getProduct()+"  ");
        sb.append ("TrxCode="+this.getTrxCode()+"  ");
        sb.append ("FieldKey="+this.getFieldKey()+"  ");
        sb.append ("FieldName="+this.getFieldName()+"  ");
        sb.append ("FieldDesc="+this.getFieldDesc()+"  ");
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
    	modelColumn = new ModelColumn("FIELD_NAME");
    	if (dbCopy != null) {
    		if (dbCopy.getFieldName() != null) {
    			modelColumn.setOldData(dbCopy.getFieldName().toString());
    		}
    	}
    	if (getFieldName() != null) {
    		modelColumn.setNewData(getFieldName().toString());
    	}
    	retval.add(modelColumn);
    	modelColumn = new ModelColumn("FIELD_DESC");
    	if (dbCopy != null) {
    		if (dbCopy.getFieldDesc() != null) {
    			modelColumn.setOldData(dbCopy.getFieldDesc().toString());
    		}
    	}
    	if (getFieldDesc() != null) {
    		modelColumn.setNewData(getFieldDesc().toString());
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
	   Returns the value of the OldCopy property.
	   @return the value of the OldCopy property.
	   @see #setOldCopy
	**/
    @Override
    public DAOModel getOldCopy(){ 
        return dbCopy;
    } 

	/**
	   Sets the OldCopy property.
	   @param aDAOModel the new value for the OldCopy property.
	   @see #getOldCopy
	**/
	public void setOldCopy(DAOModel aDAOModel){ 
        if (aDAOModel != null) {
        	dbCopy = (FieldInfoModel)aDAOModel;
        }
        else {
        	dbCopy = null;
        }
    } 
}
