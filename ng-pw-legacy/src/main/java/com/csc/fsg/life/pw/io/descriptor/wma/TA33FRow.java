package com.csc.fsg.life.pw.io.descriptor.wma;

import com.csc.fsg.life.pw.io.Row;

public class TA33FRow
    extends Row
{
    public String companyCode;
    public String tableSubset;
    public String minWithdrawalAmt;
    public String maxWithdrawalAmt;
    public String minPenaltyAmt;
    public String maxPenaltyAmt;
    public String allowSurrYrInd;
    public String noSurrsAllowed;
    public String minRemBaseInd;
    public String minRemainingAmt;
    public String minRemainingPct;
    
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
    
    public String getMinWithdrawalAmt() {
        return minWithdrawalAmt;
    }
    public void setMinWithdrawalAmt(String newMinWithdrawalAmt)
    {
        minWithdrawalAmt = newMinWithdrawalAmt;
    }
    
    public String getMaxWithdrawalAmt() {
        return maxWithdrawalAmt;
    }
    public void setMaxWithdrawalAmt(String newMaxWithdrawalAmt)
    {
        maxWithdrawalAmt = newMaxWithdrawalAmt;
    }
    
    public String getMinPenaltyAmt() {
        return minPenaltyAmt;
    }
    public void setMinPenaltyAmt(String newMinPenaltyAmt)
    {
        minPenaltyAmt = newMinPenaltyAmt;
    }
    
    public String getMaxPenaltyAmt() {
        return maxPenaltyAmt;
    }
    public void setMaxPenaltyAmt(String newMaxPenaltyAmt)
    {
        maxPenaltyAmt = newMaxPenaltyAmt;
    }
    
    public String getAllowSurrYrInd() {
        return allowSurrYrInd;
    }
    public void setAllowSurrYrInd(String newAllowSurrYrInd)
    {
        allowSurrYrInd = newAllowSurrYrInd;
    }
    
    public String getNoSurrsAllowed() {
        return noSurrsAllowed;
    }
    public void setNoSurrsAllowed(String newNoSurrsAllowed)
    {
        noSurrsAllowed = newNoSurrsAllowed;
    }
    
    public String getMinRemBaseInd() {
        return minRemBaseInd;
    }
    public void setMinRemBaseInd(String newMinRemBaseInd)
    {
        minRemBaseInd = newMinRemBaseInd;
    }
    
    public String getMinRemainingAmt() {
        return minRemainingAmt;
    }
    public void setMinRemainingAmt(String newMinRemainingAmt)
    {
        minRemainingAmt = newMinRemainingAmt;
    }
    
    public String getMinRemainingPct() {
        return minRemainingPct;
    }
    public void setMinRemainingPct(String newMinRemainingPct)
    {
        minRemainingPct = newMinRemainingPct;
    }
}
