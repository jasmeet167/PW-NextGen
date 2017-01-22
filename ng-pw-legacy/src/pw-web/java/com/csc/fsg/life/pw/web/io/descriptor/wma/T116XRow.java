package com.csc.fsg.life.pw.web.io.descriptor.wma;

import com.csc.fsg.life.pw.web.io.Row;

public class T116XRow
    extends Row
{
    public String companyCode;
    public String productCode;
    public String effectiveDate;
    public String trxCode;
    public String trxSource;
    public String endOfDay;
    
    public String getCompanyCode() {
        return companyCode;
    }
    public void setCompanyCode(String newCompanyCode)
    {
        companyCode = newCompanyCode;
    }
    
    public String getProductCode() {
        return productCode;
    }
    public void setProductCode(String newProductCode)
    {
        productCode = newProductCode;
    }
    
    public String getEffectiveDate() {
        return effectiveDate;
    }
    public void setEffectiveDate(String newEffectiveDate)
    {
        effectiveDate = newEffectiveDate;
    }
    
    public String getTrxCode() {
        return trxCode;
    }
    public void setTrxCode(String newTrxCode)
    {
        trxCode = newTrxCode;
    }
    
    public String getTrxSource() {
        return trxSource;
    }
    public void setTrxSource(String newTrxSource)
    {
        trxSource = newTrxSource;
    }
    
    public String getEndOfDay() {
        return endOfDay;
    }
    public void setEndOfDay(String newEndOfDay)
    {
        endOfDay = newEndOfDay;
    }
}
