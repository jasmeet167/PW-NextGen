package com.csc.fsg.life.pw.io.descriptor.wma;

import com.csc.fsg.life.pw.io.Row;

public class T053ERow
    extends Row
{
    public String companyCode;
    public String tableSubset;
    public String effectiveDate;
    public String maxIssueAge;
    public String maxDuration;
    public String trgtRatePerUn;
    
    public String getCompanyCode() {
        return companyCode;
    }
    public void setCompanyCode(String newCompanyCode)
    {
        companyCode = newCompanyCode;
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
    
    public String getMaxIssueAge() {
        return maxIssueAge;
    }
    public void setMaxIssueAge(String newMaxIssueAge)
    {
        maxIssueAge = newMaxIssueAge;
    }
    
    public String getMaxDuration() {
        return maxDuration;
    }
    public void setMaxDuration(String newMaxDuration)
    {
        maxDuration = newMaxDuration;
    }
    
    public String getTrgtRatePerUn() {
        return trgtRatePerUn;
    }
    public void setTrgtRatePerUn(String newTrgtRatePerUn)
    {
        trgtRatePerUn = newTrgtRatePerUn;
    }
}
