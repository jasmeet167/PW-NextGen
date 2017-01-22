package com.csc.fsg.life.pw.web.io.descriptor.wma;

import com.csc.fsg.life.pw.web.io.Row;

public class TA62FRow
    extends Row
{
    public String companyCode;
    public String tableSubset;
    public String fundNumber;
    public String effectiveDate;
    public String issueAge;
    public String maxPolicyYear;
    public String gaapExpenseFact;
    
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
    
    public String getFundNumber() {
        return fundNumber;
    }
    public void setFundNumber(String newFundNumber)
    {
        fundNumber = newFundNumber;
    }
    
    public String getEffectiveDate() {
        return effectiveDate;
    }
    public void setEffectiveDate(String newEffectiveDate)
    {
        effectiveDate = newEffectiveDate;
    }
    
    public String getIssueAge() {
        return issueAge;
    }
    public void setIssueAge(String newIssueAge)
    {
        issueAge = newIssueAge;
    }
    
    public String getMaxPolicyYear() {
        return maxPolicyYear;
    }
    public void setMaxPolicyYear(String newMaxPolicyYear)
    {
        maxPolicyYear = newMaxPolicyYear;
    }
    
    public String getGaapExpenseFact() {
        return gaapExpenseFact;
    }
    public void setGaapExpenseFact(String newGaapExpenseFact)
    {
        gaapExpenseFact = newGaapExpenseFact;
    }
}
