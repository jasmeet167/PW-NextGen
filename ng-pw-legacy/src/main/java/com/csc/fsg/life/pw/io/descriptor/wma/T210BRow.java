package com.csc.fsg.life.pw.io.descriptor.wma;

import com.csc.fsg.life.pw.io.Row;

public class T210BRow
    extends Row
{
    public String companyCode;
    public String tableSubset;
    public String statutoryCode;
    public String lineOfBusiness;
    public String effectiveDate;
    public String fundNumber;
    public String fundEndDate;
    
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
    
    public String getStatutoryCode() {
        return statutoryCode;
    }
    public void setStatutoryCode(String newStatutoryCode)
    {
        statutoryCode = newStatutoryCode;
    }
    
    public String getLineOfBusiness() {
        return lineOfBusiness;
    }
    public void setLineOfBusiness(String newLineOfBusiness)
    {
        lineOfBusiness = newLineOfBusiness;
    }
    
    public String getEffectiveDate() {
        return effectiveDate;
    }
    public void setEffectiveDate(String newEffectiveDate)
    {
        effectiveDate = newEffectiveDate;
    }
    
    public String getFundNumber() {
        return fundNumber;
    }
    public void setFundNumber(String newFundNumber)
    {
        fundNumber = newFundNumber;
    }
    
    public String getFundEndDate() {
        return fundEndDate;
    }
    public void setFundEndDate(String newFundEndDate)
    {
        fundEndDate = newFundEndDate;
    }
}
