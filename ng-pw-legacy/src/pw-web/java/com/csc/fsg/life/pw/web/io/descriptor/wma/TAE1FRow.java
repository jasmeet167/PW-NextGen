package com.csc.fsg.life.pw.web.io.descriptor.wma;

import com.csc.fsg.life.pw.web.io.Row;

public class TAE1FRow
    extends Row
{
    public String companyCode;
    public String tableSubset;
    public String effectiveDate;
    public String issueYear;
    public String issueAge;
    public String maxProjectionYrs;
    
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
    
    public String getIssueYear() {
        return issueYear;
    }
    public void setIssueYear(String newIssueYear)
    {
        issueYear = newIssueYear;
    }
    
    public String getIssueAge() {
        return issueAge;
    }
    public void setIssueAge(String newIssueAge)
    {
        issueAge = newIssueAge;
    }
    
    public String getMaxProjectionYrs() {
        return maxProjectionYrs;
    }
    public void setMaxProjectionYrs(String newMaxProjectionYrs)
    {
        maxProjectionYrs = newMaxProjectionYrs;
    }
}
