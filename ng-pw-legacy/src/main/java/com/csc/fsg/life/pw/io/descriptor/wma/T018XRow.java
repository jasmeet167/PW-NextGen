package com.csc.fsg.life.pw.io.descriptor.wma;

import com.csc.fsg.life.pw.io.Row;

public class T018XRow
    extends Row
{
    public String companyCode;
    public String productPrefix;
    public String productSuffix;
    public String lobCode;
    public String billingFrequency;
    public String extractDate;
    public String billingDate;
    public String messageCode;
    
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
    
    public String getProductSuffix() {
        return productSuffix;
    }
    public void setProductSuffix(String newProductSuffix)
    {
        productSuffix = newProductSuffix;
    }
    
    public String getLobCode() {
        return lobCode;
    }
    public void setLobCode(String newLobCode)
    {
        lobCode = newLobCode;
    }
    
    public String getBillingFrequency() {
        return billingFrequency;
    }
    public void setBillingFrequency(String newBillingFrequency)
    {
        billingFrequency = newBillingFrequency;
    }
    
    public String getExtractDate() {
        return extractDate;
    }
    public void setExtractDate(String newExtractDate)
    {
        extractDate = newExtractDate;
    }
    
    public String getBillingDate() {
        return billingDate;
    }
    public void setBillingDate(String newBillingDate)
    {
        billingDate = newBillingDate;
    }
    
    public String getMessageCode() {
        return messageCode;
    }
    public void setMessageCode(String newMessageCode)
    {
        messageCode = newMessageCode;
    }
}
