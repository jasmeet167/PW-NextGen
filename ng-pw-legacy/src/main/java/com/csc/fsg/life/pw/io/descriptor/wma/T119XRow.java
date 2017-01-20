package com.csc.fsg.life.pw.io.descriptor.wma;

import com.csc.fsg.life.pw.io.Row;

public class T119XRow
    extends Row
{
    public String companyCode;
    public String productPrefix;
    public String tableSubset;
    public String effectiveDate;
    public String favCode;
    
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
    
    public String getEffectiveDate() {
        return effectiveDate;
    }
    public void setEffectiveDate(String newEffectiveDate)
    {
        effectiveDate = newEffectiveDate;
    }
    
    public String getFavCode() {
        return favCode;
    }
    public void setFavCode(String newFavCode)
    {
        favCode = newFavCode;
    }
}
