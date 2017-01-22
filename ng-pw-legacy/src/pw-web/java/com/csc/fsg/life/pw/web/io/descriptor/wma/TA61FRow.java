package com.csc.fsg.life.pw.web.io.descriptor.wma;

import com.csc.fsg.life.pw.web.io.Row;

public class TA61FRow
    extends Row
{
    public String companyCode;
    public String tableSubset;
    public String fundNumber;
    public String effectiveDate;
    public String maxIssueAge;
    public String maxPolicyDur;
    public String bnftRsrvFct;
    
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
    
    public String getMaxIssueAge() {
        return maxIssueAge;
    }
    public void setMaxIssueAge(String newMaxIssueAge)
    {
        maxIssueAge = newMaxIssueAge;
    }
    
    public String getMaxPolicyDur() {
        return maxPolicyDur;
    }
    public void setMaxPolicyDur(String newMaxPolicyDur)
    {
        maxPolicyDur = newMaxPolicyDur;
    }
    
    public String getBnftRsrvFct() {
        return bnftRsrvFct;
    }
    public void setBnftRsrvFct(String newBnftRsrvFct)
    {
        bnftRsrvFct = newBnftRsrvFct;
    }
}
