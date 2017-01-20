package com.csc.fsg.life.pw.io.descriptor.wma;

import com.csc.fsg.life.pw.io.Row;

public class T120XRow
    extends Row
{
    public String companyCode;
    public String productPrefix;
    public String tableSubset;
    public String effectiveDate;
    public String childMaxIssAge;
    public String childExpAge;
    public String childExpNtcDays;
    public String childExpDtRule;
    
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
    
    public String getChildMaxIssAge() {
        return childMaxIssAge;
    }
    public void setChildMaxIssAge(String newChildMaxIssAge)
    {
        childMaxIssAge = newChildMaxIssAge;
    }
    
    public String getChildExpAge() {
        return childExpAge;
    }
    public void setChildExpAge(String newChildExpAge)
    {
        childExpAge = newChildExpAge;
    }
    
    public String getChildExpNtcDays() {
        return childExpNtcDays;
    }
    public void setChildExpNtcDays(String newChildExpNtcDays)
    {
        childExpNtcDays = newChildExpNtcDays;
    }
    
    public String getChildExpDtRule() {
        return childExpDtRule;
    }
    public void setChildExpDtRule(String newChildExpDtRule)
    {
        childExpDtRule = newChildExpDtRule;
    }
}
