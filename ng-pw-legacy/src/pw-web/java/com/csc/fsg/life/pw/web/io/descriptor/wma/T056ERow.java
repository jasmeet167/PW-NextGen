package com.csc.fsg.life.pw.web.io.descriptor.wma;

import com.csc.fsg.life.pw.web.io.Row;

public class T056ERow
    extends Row
{
    public String companyCode;
    public String tableSubset;
    public String effectiveDate;
    public String maximumDuration;
    public String backRepayInd;
    public String minPrsstPmtMms;
    public String loanIntRepayInd;
    public String surrTgtAdjType;
    
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
    
    public String getMaximumDuration() {
        return maximumDuration;
    }
    public void setMaximumDuration(String newMaximumDuration)
    {
        maximumDuration = newMaximumDuration;
    }
    
    public String getBackRepayInd() {
        return backRepayInd;
    }
    public void setBackRepayInd(String newBackRepayInd)
    {
        backRepayInd = newBackRepayInd;
    }
    
    public String getMinPrsstPmtMms() {
        return minPrsstPmtMms;
    }
    public void setMinPrsstPmtMms(String newMinPrsstPmtMms)
    {
        minPrsstPmtMms = newMinPrsstPmtMms;
    }
    
    public String getLoanIntRepayInd() {
        return loanIntRepayInd;
    }
    public void setLoanIntRepayInd(String newLoanIntRepayInd)
    {
        loanIntRepayInd = newLoanIntRepayInd;
    }
    
    public String getSurrTgtAdjType() {
        return surrTgtAdjType;
    }
    public void setSurrTgtAdjType(String newSurrTgtAdjType)
    {
        surrTgtAdjType = newSurrTgtAdjType;
    }
}
