package com.csc.fsg.life.pw.web.io.descriptor.wma;

import com.csc.fsg.life.pw.web.io.Row;

public class T029ERow
    extends Row
{
    public String companyCode;
    public String tableSubset;
    public String effectiveDate;
    public String attainedAge;
    public String costOfRiskRate;
    
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
    
    public String getAttainedAge() {
        return attainedAge;
    }
    public void setAttainedAge(String newAttainedAge)
    {
        attainedAge = newAttainedAge;
    }
    
    public String getCostOfRiskRate() {
        return costOfRiskRate;
    }
    public void setCostOfRiskRate(String newCostOfRiskRate)
    {
        costOfRiskRate = newCostOfRiskRate;
    }
}
