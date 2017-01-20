package com.csc.fsg.life.pw.io.descriptor.wma;

import com.csc.fsg.life.pw.io.Row;

public class T054ERow
    extends Row
{
    public String companyCode;
    public String tableSubset;
    public String effectiveDate;
    public String maxIssueAge;
    public String maxPolDur;
    public String premClassCode;
    public String maxTotalUnits;
    public String chgPerUnitPol;
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
    
    public String getMaxIssueAge() {
        return maxIssueAge;
    }
    public void setMaxIssueAge(String newMaxIssueAge)
    {
        maxIssueAge = newMaxIssueAge;
    }
    
    public String getMaxPolDur() {
        return maxPolDur;
    }
    public void setMaxPolDur(String newMaxPolDur)
    {
        maxPolDur = newMaxPolDur;
    }
    
    public String getPremClassCode() {
        return premClassCode;
    }
    public void setPremClassCode(String newPremClassCode)
    {
        premClassCode = newPremClassCode;
    }
    
    public String getMaxTotalUnits() {
        return maxTotalUnits;
    }
    public void setMaxTotalUnits(String newMaxTotalUnits)
    {
        maxTotalUnits = newMaxTotalUnits;
    }
    
    public String getChgPerUnitPol() {
        return chgPerUnitPol;
    }
    public void setChgPerUnitPol(String newChgPerUnitPol)
    {
        chgPerUnitPol = newChgPerUnitPol;
    }
    
    public String getFlatDollarAmt() {
        return flatDollarAmt;
    }
    public void setFlatDollarAmt(String newFlatDollarAmt)
    {
        flatDollarAmt = newFlatDollarAmt;
    }
}
