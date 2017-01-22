package com.csc.fsg.life.pw.web.io.descriptor.wma;

import com.csc.fsg.life.pw.web.io.Row;

public class T010XRow
    extends Row
{
    public String companyCode;
    public String productPrefix;
    public String tableSubset;
    public String statutoryCode;
    public String stateCode;
    public String lineOfBusiness;
    public String effectiveDate;
    public String fundNumber;
    public String terminationDate;
    
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
    
    public String getStatutoryCode() {
        return statutoryCode;
    }
    public void setStatutoryCode(String newStatutoryCode)
    {
        statutoryCode = newStatutoryCode;
    }
    
    public String getStateCode() {
        return stateCode;
    }
    public void setStateCode(String newStateCode)
    {
        stateCode = newStateCode;
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
    
    public String getTerminationDate() {
        return terminationDate;
    }
    public void setTerminationDate(String newTerminationDate)
    {
        terminationDate = newTerminationDate;
    }
}
