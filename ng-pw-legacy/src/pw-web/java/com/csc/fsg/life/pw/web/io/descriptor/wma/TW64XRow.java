package com.csc.fsg.life.pw.web.io.descriptor.wma;

import com.csc.fsg.life.pw.web.io.Row;

public class TW64XRow
    extends Row
{
    public String companyCode;
    public String productPrefix;
    public String tableSubset;
    public String transactionCode;
    public String effectiveDate;
    public String modelCode;
    public String modelName;
    public String allocModelTbl;
    
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
    
    public String getTableSubset() {
        return tableSubset;
    }
    public void setTableSubset(String newTableSubset)
    {
        tableSubset = newTableSubset;
    }
    
    public String getTransactionCode() {
        return transactionCode;
    }
    public void setTransactionCode(String newTransactionCode)
    {
        transactionCode = newTransactionCode;
    }
    
    public String getEffectiveDate() {
        return effectiveDate;
    }
    public void setEffectiveDate(String newEffectiveDate)
    {
        effectiveDate = newEffectiveDate;
    }
    
    public String getModelCode() {
        return modelCode;
    }
    public void setModelCode(String newModelCode)
    {
        modelCode = newModelCode;
    }
    
    public String getModelName() {
        return modelName;
    }
    public void setModelName(String newModelName)
    {
        modelName = newModelName;
    }
    
    public String getAllocModelTbl() {
        return allocModelTbl;
    }
    public void setAllocModelTbl(String newAllocModelTbl)
    {
        allocModelTbl = newAllocModelTbl;
    }
}
