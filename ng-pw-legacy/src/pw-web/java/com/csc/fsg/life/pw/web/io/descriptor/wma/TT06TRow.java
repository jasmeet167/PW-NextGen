package com.csc.fsg.life.pw.web.io.descriptor.wma;

import com.csc.fsg.life.pw.web.io.Row;

public class TT06TRow
    extends Row
{
    public String companyCode;
    public String tableSubset;
    public String riderBeneType;
    public String mCashValAgeAdj;
    public String fCashValAgeAdj;
    public String termDividendDur;
    
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
    
    public String getRiderBeneType() {
        return riderBeneType;
    }
    public void setRiderBeneType(String newRiderBeneType)
    {
        riderBeneType = newRiderBeneType;
    }
    
    public String getMCashValAgeAdj() {
        return mCashValAgeAdj;
    }
    public void setMCashValAgeAdj(String newMCashValAgeAdj)
    {
        mCashValAgeAdj = newMCashValAgeAdj;
    }
    
    public String getFCashValAgeAdj() {
        return fCashValAgeAdj;
    }
    public void setFCashValAgeAdj(String newFCashValAgeAdj)
    {
        fCashValAgeAdj = newFCashValAgeAdj;
    }
    
    public String getTermDividendDur() {
        return termDividendDur;
    }
    public void setTermDividendDur(String newTermDividendDur)
    {
        termDividendDur = newTermDividendDur;
    }
}
