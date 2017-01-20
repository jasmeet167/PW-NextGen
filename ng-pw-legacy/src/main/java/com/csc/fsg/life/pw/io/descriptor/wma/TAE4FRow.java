package com.csc.fsg.life.pw.io.descriptor.wma;

import com.csc.fsg.life.pw.io.Row;

public class TAE4FRow
    extends Row
{
    public String companyCode;
    public String tableSubset;
    public String statCompany;
    public String effectiveDate;
    public String policyDur;
    public String aseqInterestRate;
    
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
    
    public String getPolicyDur() {
        return policyDur;
    }
    public void setPolicyDur(String newPolicyDur)
    {
        policyDur = newPolicyDur;
    }
    
    public String getAseqInterestRate() {
        return aseqInterestRate;
    }
    public void setAseqInterestRate(String newAseqInterestRate)
    {
        aseqInterestRate = newAseqInterestRate;
    }
}
