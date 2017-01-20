package com.csc.fsg.life.pw.io.descriptor.wma;

import com.csc.fsg.life.pw.io.Row;

public class T005ERow
    extends Row
{
    public String companyCode;
    public String tableSubset;
    public String effectiveDate;
    public String tefraLitaInd;
    public String minAtanAge;
    public String mxAtanAge;
    public String corrPct;
    
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
    
    public String getTefraLitaInd() {
        return tefraLitaInd;
    }
    public void setTefraLitaInd(String newTefraLitaInd)
    {
        tefraLitaInd = newTefraLitaInd;
    }
    
    public String getMinAtanAge() {
        return minAtanAge;
    }
    public void setMinAtanAge(String newMinAtanAge)
    {
        minAtanAge = newMinAtanAge;
    }
    
    public String getMxAtanAge() {
        return mxAtanAge;
    }
    public void setMxAtanAge(String newMxAtanAge)
    {
        mxAtanAge = newMxAtanAge;
    }
    
    public String getCorrPct() {
        return corrPct;
    }
    public void setCorrPct(String newCorrPct)
    {
        corrPct = newCorrPct;
    }
}
