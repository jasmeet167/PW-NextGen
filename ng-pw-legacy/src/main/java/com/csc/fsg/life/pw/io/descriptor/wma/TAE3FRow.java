package com.csc.fsg.life.pw.io.descriptor.wma;

import com.csc.fsg.life.pw.io.Row;

public class TAE3FRow
    extends Row
{
    public String companyCode;
    public String tableSubset;
    public String statCompany;
    public String effectiveDate;
    public String retireAge;
    public String maleAnnFactor;
    public String fmaleAnnFactor;
    
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
    
    public String getStatCompany() {
        return statCompany;
    }
    public void setStatCompany(String newStatCompany)
    {
        statCompany = newStatCompany;
    }
    
    public String getEffectiveDate() {
        return effectiveDate;
    }
    public void setEffectiveDate(String newEffectiveDate)
    {
        effectiveDate = newEffectiveDate;
    }
    
    public String getRetireAge() {
        return retireAge;
    }
    public void setRetireAge(String newRetireAge)
    {
        retireAge = newRetireAge;
    }
    
    public String getMaleAnnFactor() {
        return maleAnnFactor;
    }
    public void setMaleAnnFactor(String newMaleAnnFactor)
    {
        maleAnnFactor = newMaleAnnFactor;
    }
    
    public String getFmaleAnnFactor() {
        return fmaleAnnFactor;
    }
    public void setFmaleAnnFactor(String newFmaleAnnFactor)
    {
        fmaleAnnFactor = newFmaleAnnFactor;
    }
}
