package com.csc.fsg.life.pw.io.descriptor.wma;

import com.csc.fsg.life.pw.io.Row;

public class TW58XRow
    extends Row
{
    public String companyCode;
    public String productPrefix;
    public String tableSubset;
    public String effectiveDate;
    public String annuitantAge;
    public String jointAge;
    public String purchaseRate;
    
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
    
    public String getAnnuitantAge() {
        return annuitantAge;
    }
    public void setAnnuitantAge(String newAnnuitantAge)
    {
        annuitantAge = newAnnuitantAge;
    }
    
    public String getJointAge() {
        return jointAge;
    }
    public void setJointAge(String newJointAge)
    {
        jointAge = newJointAge;
    }
    
    public String getPurchaseRate() {
        return purchaseRate;
    }
    public void setPurchaseRate(String newPurchaseRate)
    {
        purchaseRate = newPurchaseRate;
    }
}
