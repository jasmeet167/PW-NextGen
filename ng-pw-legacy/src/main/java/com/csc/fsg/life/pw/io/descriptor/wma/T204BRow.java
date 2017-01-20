package com.csc.fsg.life.pw.io.descriptor.wma;

import com.csc.fsg.life.pw.io.Row;

public class T204BRow
    extends Row
{
    public String companyCode;
    public String tableSubset;
    public String effectiveDate;
    public String intCreditFreq;
    public String intCreditBasis;
    public String futureDays;
    public String trdAutoAnnBill;
    public String autoSuspClear;
    
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
    
    public String getIntCreditFreq() {
        return intCreditFreq;
    }
    public void setIntCreditFreq(String newIntCreditFreq)
    {
        intCreditFreq = newIntCreditFreq;
    }
    
    public String getIntCreditBasis() {
        return intCreditBasis;
    }
    public void setIntCreditBasis(String newIntCreditBasis)
    {
        intCreditBasis = newIntCreditBasis;
    }
    
    public String getFutureDays() {
        return futureDays;
    }
    public void setFutureDays(String newFutureDays)
    {
        futureDays = newFutureDays;
    }
    
    public String getTrdAutoAnnBill() {
        return trdAutoAnnBill;
    }
    public void setTrdAutoAnnBill(String newTrdAutoAnnBill)
    {
        trdAutoAnnBill = newTrdAutoAnnBill;
    }
    
    public String getAutoSuspClear() {
        return autoSuspClear;
    }
    public void setAutoSuspClear(String newAutoSuspClear)
    {
        autoSuspClear = newAutoSuspClear;
    }
}
