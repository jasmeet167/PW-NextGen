package com.csc.fsg.life.pw.io.descriptor.wma;

import com.csc.fsg.life.pw.io.Row;

public class T007XRow
    extends Row
{
    public String companyCode;
    public String productPrefix;
    public String tableSubset;
    public String riderBeneType;
    public String riderPlanCode;
    public String effectiveDate;
    public String terminationDate;
    public String noLapseGuarMonths;
    public String incrNoLapseGuarMonths;
    
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
    
    public String getRiderBeneType() {
        return riderBeneType;
    }
    public void setRiderBeneType(String newRiderBeneType)
    {
        riderBeneType = newRiderBeneType;
    }
    
    public String getRiderPlanCode() {
        return riderPlanCode;
    }
    public void setRiderPlanCode(String newRiderPlanCode)
    {
        riderPlanCode = newRiderPlanCode;
    }
    
    public String getEffectiveDate() {
        return effectiveDate;
    }
    public void setEffectiveDate(String newEffectiveDate)
    {
        effectiveDate = newEffectiveDate;
    }
    
    public String getTerminationDate() {
        return terminationDate;
    }
    public void setTerminationDate(String newTerminationDate)
    {
        terminationDate = newTerminationDate;
    }
    
    public String getNoLapseGuarMonths() {
        return noLapseGuarMonths;
    }
    public void setNoLapseGuarMonths(String newNoLapseGuarMonths)
    {
        noLapseGuarMonths = newNoLapseGuarMonths;
    }
    
    public String getIncrNoLapseGuarMonths() {
        return incrNoLapseGuarMonths;
    }
    public void setIncrNoLapseGuarMonths(String newIncrNoLapseGuarMonths)
    {
        incrNoLapseGuarMonths = newIncrNoLapseGuarMonths;
    }
}
