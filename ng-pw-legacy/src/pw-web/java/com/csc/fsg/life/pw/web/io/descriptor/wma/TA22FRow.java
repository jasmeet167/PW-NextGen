package com.csc.fsg.life.pw.web.io.descriptor.wma;

import com.csc.fsg.life.pw.web.io.Row;

public class TA22FRow
    extends Row
{
    public String companyCode;
    public String tableSubset;
    public String effectiveDate;
    public String transactionCode;
    public String paymentMode;
    public String paymentMethod;
    public String cumPremToDt;
    public String maxTrxFeeToDt;
    public String memoCode;
    public String trxFeeAmt;
    public String trxFeePct;
    
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
    
    public String getTransactionCode() {
        return transactionCode;
    }
    public void setTransactionCode(String newTransactionCode)
    {
        transactionCode = newTransactionCode;
    }
    
    public String getPaymentMode() {
        return paymentMode;
    }
    public void setPaymentMode(String newPaymentMode)
    {
        paymentMode = newPaymentMode;
    }
    
    public String getPaymentMethod() {
        return paymentMethod;
    }
    public void setPaymentMethod(String newPaymentMethod)
    {
        paymentMethod = newPaymentMethod;
    }
    
    public String getCumPremToDt() {
        return cumPremToDt;
    }
    public void setCumPremToDt(String newCumPremToDt)
    {
        cumPremToDt = newCumPremToDt;
    }
    
    public String getMaxTrxFeeToDt() {
        return maxTrxFeeToDt;
    }
    public void setMaxTrxFeeToDt(String newMaxTrxFeeToDt)
    {
        maxTrxFeeToDt = newMaxTrxFeeToDt;
    }
    
    public String getMemoCode() {
        return memoCode;
    }
    public void setMemoCode(String newMemoCode)
    {
        memoCode = newMemoCode;
    }
    
    public String getTrxFeeAmt() {
        return trxFeeAmt;
    }
    public void setTrxFeeAmt(String newTrxFeeAmt)
    {
        trxFeeAmt = newTrxFeeAmt;
    }
    
    public String getTrxFeePct() {
        return trxFeePct;
    }
    public void setTrxFeePct(String newTrxFeePct)
    {
        trxFeePct = newTrxFeePct;
    }
}
