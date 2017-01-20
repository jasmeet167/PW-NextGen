package com.csc.fsg.life.pw.io.descriptor.wma;

import com.csc.fsg.life.pw.io.Row;

public class TW65XRow
    extends Row
{
    public String companyCode;
    public String tableSubset;
    public String effectiveDate;
    public String assumedIntRate;
    
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
    
    public String getAssumedIntRate() {
        return assumedIntRate;
    }
    public void setAssumedIntRate(String newAssumedIntRate)
    {
        assumedIntRate = newAssumedIntRate;
    }
}
