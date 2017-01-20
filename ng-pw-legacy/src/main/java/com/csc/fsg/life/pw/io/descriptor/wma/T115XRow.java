package com.csc.fsg.life.pw.io.descriptor.wma;

import com.csc.fsg.life.pw.io.Row;

public class T115XRow
    extends Row
{
    public String companyCode;
    public String entryNumber;
    public String effectiveDate;
    public String currentFavCode;
    public String duration;
    public String amount;
    public String newFavCode;
    
    public String getCompanyCode() {
        return companyCode;
    }
    public void setCompanyCode(String newCompanyCode)
    {
        companyCode = newCompanyCode;
    }
    
    public String getEntryNumber() {
        return entryNumber;
    }
    public void setEntryNumber(String newEntryNumber)
    {
        entryNumber = newEntryNumber;
    }
    
    public String getEffectiveDate() {
        return effectiveDate;
    }
    public void setEffectiveDate(String newEffectiveDate)
    {
        effectiveDate = newEffectiveDate;
    }
    
    public String getCurrentFavCode() {
        return currentFavCode;
    }
    public void setCurrentFavCode(String newCurrentFavCode)
    {
        currentFavCode = newCurrentFavCode;
    }
    
    public String getDuration() {
        return duration;
    }
    public void setDuration(String newDuration)
    {
        duration = newDuration;
    }
    
    public String getAmount() {
        return amount;
    }
    public void setAmount(String newAmount)
    {
        amount = newAmount;
    }
    
    public String getNewFavCode() {
        return newFavCode;
    }
    public void setNewFavCode(String newNewFavCode)
    {
        newFavCode = newNewFavCode;
    }
}
