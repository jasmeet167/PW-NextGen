package com.csc.fsg.life.pw.web.io.descriptor.wma;

import com.csc.fsg.life.pw.web.io.Row;

public class TAE2FRow
    extends Row
{
    public String companyCode;
    public String tableSubset;
    public String effectiveDate;
    public String issueAge;
    public String maxPolicyYear;
    public String riderBenefitFctr;
    
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
    
    public String getRiderBenefitFctr() {
        return riderBenefitFctr;
    }
    public void setRiderBenefitFctr(String newRiderBenefitFctr)
    {
        riderBenefitFctr = newRiderBenefitFctr;
    }
}
