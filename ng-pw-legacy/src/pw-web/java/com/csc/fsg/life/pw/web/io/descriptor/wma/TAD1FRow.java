package com.csc.fsg.life.pw.web.io.descriptor.wma;

import com.csc.fsg.life.pw.web.io.Row;

public class TAD1FRow
    extends Row
{
    public String companyCode;
    public String tableSubset;
    public String effectiveDate;
    public String annuityType;
    public String transactionCode;
    public String distribCode;
    public String taxTypeInd;
    public String annuityTypeDesc;
    
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
    
    public String getAnnuityType() {
        return annuityType;
    }
    public void setAnnuityType(String newAnnuityType)
    {
        annuityType = newAnnuityType;
    }
    
    public String getTransactionCode() {
        return transactionCode;
    }
    public void setTransactionCode(String newTransactionCode)
    {
        transactionCode = newTransactionCode;
    }
    
    public String getDistribCode() {
        return distribCode;
    }
    public void setDistribCode(String newDistribCode)
    {
        distribCode = newDistribCode;
    }
    
    public String getTaxTypeInd() {
        return taxTypeInd;
    }
    public void setTaxTypeInd(String newTaxTypeInd)
    {
        taxTypeInd = newTaxTypeInd;
    }
    
    public String getAnnuityTypeDesc() {
        return annuityTypeDesc;
    }
    public void setAnnuityTypeDesc(String newAnnuityTypeDesc)
    {
        annuityTypeDesc = newAnnuityTypeDesc;
    }
}
