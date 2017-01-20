package com.csc.fsg.life.pw.io.descriptor.wma;

import com.csc.fsg.life.pw.io.Row;

public class TT72TRow
    extends Row
{
    public String companyCode;
    public String tableSubset;
    public String effectiveDate;
    public String maxAge;
    public String maxDuration;
    public String annPremPerUnit;
    public String waiverOfPrem;
    
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
    
    public String getAnnPremPerUnit() {
        return annPremPerUnit;
    }
    public void setAnnPremPerUnit(String newAnnPremPerUnit)
    {
        annPremPerUnit = newAnnPremPerUnit;
    }
    
    public String getWaiverOfPrem() {
        return waiverOfPrem;
    }
    public void setWaiverOfPrem(String newWaiverOfPrem)
    {
        waiverOfPrem = newWaiverOfPrem;
    }
}
