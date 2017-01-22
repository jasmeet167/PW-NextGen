package com.csc.fsg.life.pw.web.io.descriptor.wma;

import com.csc.fsg.life.pw.web.io.Row;

public class TAD2FRow
    extends Row
{
    public String companyCode;
    public String tableSubset;
    public String effectiveDate;
    public String memoCode;
    public String minLoanAmount;
    public String maxLoanAmount;
    public String maxPercent;
    public String maxPeriod;
    public String retireDtChkInd;
    public String autoGenRepayInd;
    public String alowCscRpayMiss;
    public String alowCumRpayMiss;
    
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
    
    public String getMemoCode() {
        return memoCode;
    }
    public void setMemoCode(String newMemoCode)
    {
        memoCode = newMemoCode;
    }
    
    public String getMinLoanAmount() {
        return minLoanAmount;
    }
    public void setMinLoanAmount(String newMinLoanAmount)
    {
        minLoanAmount = newMinLoanAmount;
    }
    
    public String getMaxLoanAmount() {
        return maxLoanAmount;
    }
    public void setMaxLoanAmount(String newMaxLoanAmount)
    {
        maxLoanAmount = newMaxLoanAmount;
    }
    
    public String getMaxPercent() {
        return maxPercent;
    }
    public void setMaxPercent(String newMaxPercent)
    {
        maxPercent = newMaxPercent;
    }
    
    public String getMaxPeriod() {
        return maxPeriod;
    }
    public void setMaxPeriod(String newMaxPeriod)
    {
        maxPeriod = newMaxPeriod;
    }
    
    public String getRetireDtChkInd() {
        return retireDtChkInd;
    }
    public void setRetireDtChkInd(String newRetireDtChkInd)
    {
        retireDtChkInd = newRetireDtChkInd;
    }
    
    public String getAutoGenRepayInd() {
        return autoGenRepayInd;
    }
    public void setAutoGenRepayInd(String newAutoGenRepayInd)
    {
        autoGenRepayInd = newAutoGenRepayInd;
    }
    
    public String getAlowCscRpayMiss() {
        return alowCscRpayMiss;
    }
    public void setAlowCscRpayMiss(String newAlowCscRpayMiss)
    {
        alowCscRpayMiss = newAlowCscRpayMiss;
    }
    
    public String getAlowCumRpayMiss() {
        return alowCumRpayMiss;
    }
    public void setAlowCumRpayMiss(String newAlowCumRpayMiss)
    {
        alowCumRpayMiss = newAlowCumRpayMiss;
    }
}
