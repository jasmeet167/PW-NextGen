package com.csc.fsg.life.pw.web.io.descriptor.wma;

import com.csc.fsg.life.pw.web.io.Row;

public class TW10XRow
    extends Row
{
    public String companyCode;
    public String transactionCode;
    public String purchSeriesCode;
    public String effectiveDate;
    public String pymtMode;
    public String modalFactor;
    
    public String getCompanyCode() {
        return companyCode;
    }
    public void setCompanyCode(String newCompanyCode)
    {
        companyCode = newCompanyCode;
    }
    
    public String getTransactionCode() {
        return transactionCode;
    }
    public void setTransactionCode(String newTransactionCode)
    {
        transactionCode = newTransactionCode;
    }
    
    public String getPurchSeriesCode() {
        return purchSeriesCode;
    }
    public void setPurchSeriesCode(String newPurchSeriesCode)
    {
        purchSeriesCode = newPurchSeriesCode;
    }
    
    public String getEffectiveDate() {
        return effectiveDate;
    }
    public void setEffectiveDate(String newEffectiveDate)
    {
        effectiveDate = newEffectiveDate;
    }
    
    public String getPymtMode() {
        return pymtMode;
    }
    public void setPymtMode(String newPymtMode)
    {
        pymtMode = newPymtMode;
    }
    
    public String getModalFactor() {
        return modalFactor;
    }
    public void setModalFactor(String newModalFactor)
    {
        modalFactor = newModalFactor;
    }
}
