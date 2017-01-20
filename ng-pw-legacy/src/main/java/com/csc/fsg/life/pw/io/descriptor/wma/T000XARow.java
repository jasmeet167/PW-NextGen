package com.csc.fsg.life.pw.io.descriptor.wma;

import com.csc.fsg.life.pw.io.Row;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({ "companyCode","productPrefixJson","primaryTableId","primaryPtrSubset","secondaryTableId","secndryPtrSubset","secndryTableVar" })
public class T000XARow
    extends Row
{
    public String companyCode;
    public String productPrefix;
    public String primaryTableId;
    public String primaryPtrSubset;
    public String secondaryTableId;
    public String secndryPtrSubset;
    public String secndryTableVar;
    
    public String getCompanyCode() {
        return companyCode;
    }
    public void setCompanyCode(String newCompanyCode)
    {
        companyCode = newCompanyCode;
    }
    
    public String getProductPrefix() {
        return productPrefix;
    }
    public void setProductPrefix(String newProductPrefix)
    {
        productPrefix = newProductPrefix;
    }
    
    public String getPrimaryTableId() {
        return primaryTableId;
    }
    public void setPrimaryTableId(String newPrimaryTableId)
    {
        primaryTableId = newPrimaryTableId;
    }
    
    public String getPrimaryPtrSubset() {
        return primaryPtrSubset;
    }
    public void setPrimaryPtrSubset(String newPrimaryPtrSubset)
    {
        primaryPtrSubset = newPrimaryPtrSubset;
    }
    
    public String getSecondaryTableId() {
        return secondaryTableId;
    }
    public void setSecondaryTableId(String newSecondaryTableId)
    {
        secondaryTableId = newSecondaryTableId;
    }
    
    public String getSecndryPtrSubset() {
        return secndryPtrSubset;
    }
    public void setSecndryPtrSubset(String newSecndryPtrSubset)
    {
        secndryPtrSubset = newSecndryPtrSubset;
    }
    
    public String getSecndryTableVar() {
        return secndryTableVar;
    }
    public void setSecndryTableVar(String newSecndryTableVar)
    {
        secndryTableVar = newSecndryTableVar;
    }

    @JsonIgnore
	public String getTableSubset() {
		return super.getTableSubset();
	}

    public String getProductPrefixJson() {
        return productPrefix;
    }
}
