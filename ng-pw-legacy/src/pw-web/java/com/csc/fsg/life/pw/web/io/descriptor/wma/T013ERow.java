package com.csc.fsg.life.pw.web.io.descriptor.wma;

import com.csc.fsg.life.pw.web.io.Row;

public class T013ERow
    extends Row
{
    public String companyCode;
    public String tableSubset;
    public String effectiveDate;
    public String maxIssueAge;
    public String maxPolDuration;
    public String minFaceAmt;
    
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
    
    public String getMaxPolDuration() {
        return maxPolDuration;
    }
    public void setMaxPolDuration(String newMaxPolDuration)
    {
        maxPolDuration = newMaxPolDuration;
    }
    
    public String getMinFaceAmt() {
        return minFaceAmt;
    }
    public void setMinFaceAmt(String newMinFaceAmt)
    {
        minFaceAmt = newMinFaceAmt;
    }
}
