package com.csc.fsg.life.pw.io.descriptor.wma;

import com.csc.fsg.life.pw.io.Row;

public class TTBBTRow
    extends Row
{
    public String companyCode;
    public String tableSubset;
    public String effectiveDate;
    public String maxAge;
    public String maxDuration;
    public String basFlatchgPrPay;
    public String basFlatchgNonprm;
    
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
    
    public String getMaxAge() {
        return maxAge;
    }
    public void setMaxAge(String newMaxAge)
    {
        maxAge = newMaxAge;
    }
    
    public String getMaxDuration() {
        return maxDuration;
    }
    public void setMaxDuration(String newMaxDuration)
    {
        maxDuration = newMaxDuration;
    }
    
    public String getBasFlatchgPrPay() {
        return basFlatchgPrPay;
    }
    public void setBasFlatchgPrPay(String newBasFlatchgPrPay)
    {
        basFlatchgPrPay = newBasFlatchgPrPay;
    }
    
    public String getBasFlatchgNonprm() {
        return basFlatchgNonprm;
    }
    public void setBasFlatchgNonprm(String newBasFlatchgNonprm)
    {
        basFlatchgNonprm = newBasFlatchgNonprm;
    }
}
