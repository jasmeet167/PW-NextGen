package com.csc.fsg.life.pw.io.descriptor.wma;

import com.csc.fsg.life.pw.io.Row;

public class T110XRow
    extends Row
{
    public String companyCode;
    public String effectiveDate;
    public String duration;
    public String treasuryRate;
    
    public String getCompanyCode() {
        return companyCode;
    }
    public void setCompanyCode(String newCompanyCode)
    {
        companyCode = newCompanyCode;
    }
    
    public String getEffectiveDate() {
        return effectiveDate;
    }
    public void setEffectiveDate(String newEffectiveDate)
    {
        effectiveDate = newEffectiveDate;
    }
    
    public String getDuration() {
        return duration;
    }
    public void setDuration(String newDuration)
    {
        duration = newDuration;
    }
    
    public String getTreasuryRate() {
        return treasuryRate;
    }
    public void setTreasuryRate(String newTreasuryRate)
    {
        treasuryRate = newTreasuryRate;
    }
}
