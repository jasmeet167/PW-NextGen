package com.csc.fsg.life.pw.io.descriptor.wma;

import com.csc.fsg.life.pw.io.Row;

public class T222BRow
    extends Row
{
    public String companyCode;
    public String tableSubset;
    public String effectiveDate;
    public String transactionCode;
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
