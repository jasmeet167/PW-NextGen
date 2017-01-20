package com.csc.fsg.life.pw.io.descriptor.wma;

import com.csc.fsg.life.pw.io.Row;

public class TTBDTRow
    extends Row
{
    public String companyCode;
    public String tableSubset;
    public String effectiveDate;
    public String maxAge;
    public String maxDuration;
    public String pdupPctIncrease;
    
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
    
    public String getMaxAge() {
        return maxAge;
    }
    public void setMaxAge(String newMaxAge)
    {
        maxAge = newMaxAge;
    }
    
    public String getMaxDuration() {
        return maxDuration;
    }
    public void setMaxDuration(String newMaxDuration)
    {
        maxDuration = newMaxDuration;
    }
    
    public String getPdupPctIncrease() {
        return pdupPctIncrease;
    }
    public void setPdupPctIncrease(String newPdupPctIncrease)
    {
        pdupPctIncrease = newPdupPctIncrease;
    }
}
