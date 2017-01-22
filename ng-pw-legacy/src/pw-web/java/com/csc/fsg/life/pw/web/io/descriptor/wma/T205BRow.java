package com.csc.fsg.life.pw.web.io.descriptor.wma;

import com.csc.fsg.life.pw.web.io.Row;

public class T205BRow
    extends Row
{
    public String companyCode;
    public String tableSubset;
    public String fundNumber;
    public String effectiveDate;
    public String withdrawPostMeth;
    public String minDepAmount;
    public String maxDepAmount;
    public String minWithdrawal;
    public String maxWithdrawal;
    public String ind403b;
    
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
    
    public String getWithdrawPostMeth() {
        return withdrawPostMeth;
    }
    public void setWithdrawPostMeth(String newWithdrawPostMeth)
    {
        withdrawPostMeth = newWithdrawPostMeth;
    }
    
    public String getMinDepAmount() {
        return minDepAmount;
    }
    public void setMinDepAmount(String newMinDepAmount)
    {
        minDepAmount = newMinDepAmount;
    }
    
    public String getMaxDepAmount() {
        return maxDepAmount;
    }
    public void setMaxDepAmount(String newMaxDepAmount)
    {
        maxDepAmount = newMaxDepAmount;
    }
    
    public String getMinWithdrawal() {
        return minWithdrawal;
    }
    public void setMinWithdrawal(String newMinWithdrawal)
    {
        minWithdrawal = newMinWithdrawal;
    }
    
    public String getMaxWithdrawal() {
        return maxWithdrawal;
    }
    public void setMaxWithdrawal(String newMaxWithdrawal)
    {
        maxWithdrawal = newMaxWithdrawal;
    }
    
    public String getInd403b() {
        return ind403b;
    }
    public void setInd403b(String newInd403b)
    {
        ind403b = newInd403b;
    }
}
