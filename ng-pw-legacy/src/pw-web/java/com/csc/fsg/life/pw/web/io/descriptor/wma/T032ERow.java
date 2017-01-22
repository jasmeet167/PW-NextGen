package com.csc.fsg.life.pw.web.io.descriptor.wma;

import com.csc.fsg.life.pw.web.io.Row;

public class T032ERow
    extends Row
{
    public String companyCode;
    public String tableSubset;
    public String transactionCode;
    public String effectiveDate;
    public String numFreeWthYr;
    public String chgPerUnitInd;
    public String chgPerUnitTbl;
    public String pctCashValTbl;
    public String pctTgtTbl;
    public String remRiskLoadTbl;
    
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
    
    public String getTransactionCode() {
        return transactionCode;
    }
    public void setTransactionCode(String newTransactionCode)
    {
        transactionCode = newTransactionCode;
    }
    
    public String getEffectiveDate() {
        return effectiveDate;
    }
    public void setEffectiveDate(String newEffectiveDate)
    {
        effectiveDate = newEffectiveDate;
    }
    
    public String getNumFreeWthYr() {
        return numFreeWthYr;
    }
    public void setNumFreeWthYr(String newNumFreeWthYr)
    {
        numFreeWthYr = newNumFreeWthYr;
    }
    
    public String getChgPerUnitInd() {
        return chgPerUnitInd;
    }
    public void setChgPerUnitInd(String newChgPerUnitInd)
    {
        chgPerUnitInd = newChgPerUnitInd;
    }
    
    public String getChgPerUnitTbl() {
        return chgPerUnitTbl;
    }
    public void setChgPerUnitTbl(String newChgPerUnitTbl)
    {
        chgPerUnitTbl = newChgPerUnitTbl;
    }
    
    public String getPctCashValTbl() {
        return pctCashValTbl;
    }
    public void setPctCashValTbl(String newPctCashValTbl)
    {
        pctCashValTbl = newPctCashValTbl;
    }
    
    public String getPctTgtTbl() {
        return pctTgtTbl;
    }
    public void setPctTgtTbl(String newPctTgtTbl)
    {
        pctTgtTbl = newPctTgtTbl;
    }
    
    public String getRemRiskLoadTbl() {
        return remRiskLoadTbl;
    }
    public void setRemRiskLoadTbl(String newRemRiskLoadTbl)
    {
        remRiskLoadTbl = newRemRiskLoadTbl;
    }
}
