package com.csc.fsg.life.pw.io.descriptor.wma;

import com.csc.fsg.life.pw.io.Row;

public class TT24TRow
    extends Row
{
    public String companyCode;
    public String tableSubset;
    public String effectiveDate;
    public String declIntRateTbl;
    public String interestInd;
    public String interestCalcMthd;
    public String leapYearDays;
    public String interestCalcOpt;
    
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
    
    public String getDeclIntRateTbl() {
        return declIntRateTbl;
    }
    public void setDeclIntRateTbl(String newDeclIntRateTbl)
    {
        declIntRateTbl = newDeclIntRateTbl;
    }
    
    public String getInterestInd() {
        return interestInd;
    }
    public void setInterestInd(String newInterestInd)
    {
        interestInd = newInterestInd;
    }
    
    public String getInterestCalcMthd() {
        return interestCalcMthd;
    }
    public void setInterestCalcMthd(String newInterestCalcMthd)
    {
        interestCalcMthd = newInterestCalcMthd;
    }
    
    public String getLeapYearDays() {
        return leapYearDays;
    }
    public void setLeapYearDays(String newLeapYearDays)
    {
        leapYearDays = newLeapYearDays;
    }
    
    public String getInterestCalcOpt() {
        return interestCalcOpt;
    }
    public void setInterestCalcOpt(String newInterestCalcOpt)
    {
        interestCalcOpt = newInterestCalcOpt;
    }
}
