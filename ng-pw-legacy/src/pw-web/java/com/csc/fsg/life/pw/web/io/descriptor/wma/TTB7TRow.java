package com.csc.fsg.life.pw.web.io.descriptor.wma;

import com.csc.fsg.life.pw.web.io.Row;

public class TTB7TRow
    extends Row
{
    public String companyCode;
    public String tableSubset;
    public String effectiveDate;
    public String maxPremPayMths;
    public String chrgbackPercent;
    
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
    
    public String getMaxPremPayMths() {
        return maxPremPayMths;
    }
    public void setMaxPremPayMths(String newMaxPremPayMths)
    {
        maxPremPayMths = newMaxPremPayMths;
    }
    
    public String getChrgbackPercent() {
        return chrgbackPercent;
    }
    public void setChrgbackPercent(String newChrgbackPercent)
    {
        chrgbackPercent = newChrgbackPercent;
    }
}
