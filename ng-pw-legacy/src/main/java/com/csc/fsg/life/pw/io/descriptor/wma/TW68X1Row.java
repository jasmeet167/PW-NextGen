package com.csc.fsg.life.pw.io.descriptor.wma;

import com.csc.fsg.life.pw.io.Row;

public class TW68X1Row
    extends Row
{
    public String companyCode;
    public String tableSubset;
    public String issueAge;
    public String policyYear;
    public String numberLiving;
    
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
    
    public String getIssueAge() {
        return issueAge;
    }
    public void setIssueAge(String newIssueAge)
    {
        issueAge = newIssueAge;
    }
    
    public String getPolicyYear() {
        return policyYear;
    }
    public void setPolicyYear(String newPolicyYear)
    {
        policyYear = newPolicyYear;
    }
    
    public String getNumberLiving() {
        return numberLiving;
    }
    public void setNumberLiving(String newNumberLiving)
    {
        numberLiving = newNumberLiving;
    }
}
