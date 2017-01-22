package com.csc.fsg.life.pw.web.io.descriptor.wma;

import com.csc.fsg.life.pw.web.io.Row;

public class T037ERow
    extends Row
{
    public String companyCode;
    public String tableSubset;
    public String effectiveDate;
    public String maxIncrIssueAge;
    public String maxTotPolUnits;
    public String maxIncreaseUnits;
    public String chgPerUnitIncr;
    public String flatDollarAmt;
    
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
    
    public String getMaxIncrIssueAge() {
        return maxIncrIssueAge;
    }
    public void setMaxIncrIssueAge(String newMaxIncrIssueAge)
    {
        maxIncrIssueAge = newMaxIncrIssueAge;
    }
    
    public String getMaxTotPolUnits() {
        return maxTotPolUnits;
    }
    public void setMaxTotPolUnits(String newMaxTotPolUnits)
    {
        maxTotPolUnits = newMaxTotPolUnits;
    }
    
    public String getMaxIncreaseUnits() {
        return maxIncreaseUnits;
    }
    public void setMaxIncreaseUnits(String newMaxIncreaseUnits)
    {
        maxIncreaseUnits = newMaxIncreaseUnits;
    }
    
    public String getChgPerUnitIncr() {
        return chgPerUnitIncr;
    }
    public void setChgPerUnitIncr(String newChgPerUnitIncr)
    {
        chgPerUnitIncr = newChgPerUnitIncr;
    }
    
    public String getFlatDollarAmt() {
        return flatDollarAmt;
    }
    public void setFlatDollarAmt(String newFlatDollarAmt)
    {
        flatDollarAmt = newFlatDollarAmt;
    }
}
