package com.csc.fsg.life.pw.web.io.descriptor.wma;

import com.csc.fsg.life.pw.web.io.Row;

public class T040XRow
    extends Row
{
    public String companyCode;
    public String productPrefix;
    public String tableSubset;
    public String fundNumber;
    public String effectiveDate;
    public String memoCode;
    public String minRemainBal;
    public String minPercentRemain;
    public String minRemainUnits;
    public String exclTxfrFromInd;
    
    public String getCompanyCode() {
        return companyCode;
    }
    public void setCompanyCode(String newCompanyCode)
    {
        companyCode = newCompanyCode;
    }
    
    public String getProductPrefix() {
        return productPrefix;
    }
    public void setProductPrefix(String newProductPrefix)
    {
        productPrefix = newProductPrefix;
    }
    
    public String getTableSubset() {
        return tableSubset;
    }
    public void setTableSubset(String newTableSubset)
    {
        tableSubset = newTableSubset;
    }
    
    public String getFundNumber() {
        return fundNumber;
    }
    public void setFundNumber(String newFundNumber)
    {
        fundNumber = newFundNumber;
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
    
    public String getMinRemainBal() {
        return minRemainBal;
    }
    public void setMinRemainBal(String newMinRemainBal)
    {
        minRemainBal = newMinRemainBal;
    }
    
    public String getMinPercentRemain() {
        return minPercentRemain;
    }
    public void setMinPercentRemain(String newMinPercentRemain)
    {
        minPercentRemain = newMinPercentRemain;
    }
    
    public String getMinRemainUnits() {
        return minRemainUnits;
    }
    public void setMinRemainUnits(String newMinRemainUnits)
    {
        minRemainUnits = newMinRemainUnits;
    }
    
    public String getExclTxfrFromInd() {
        return exclTxfrFromInd;
    }
    public void setExclTxfrFromInd(String newExclTxfrFromInd)
    {
        exclTxfrFromInd = newExclTxfrFromInd;
    }
}
