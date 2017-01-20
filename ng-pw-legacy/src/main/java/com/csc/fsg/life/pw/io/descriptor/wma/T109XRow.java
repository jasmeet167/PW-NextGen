package com.csc.fsg.life.pw.io.descriptor.wma;

import com.csc.fsg.life.pw.io.Row;

public class T109XRow
    extends Row
{
    public String companyCode;
    public String statutoryCode;
    public String productPrefix;
    public String productSuffix;
    public String transactionCode;
    public String reversalInd;
    public String reasonCode;
    public String reasonDescription;
    
    public String getCompanyCode() {
        return companyCode;
    }
    public void setCompanyCode(String newCompanyCode)
    {
        companyCode = newCompanyCode;
    }
    
    public String getStatutoryCode() {
        return statutoryCode;
    }
    public void setStatutoryCode(String newStatutoryCode)
    {
        statutoryCode = newStatutoryCode;
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
    
    public String getTransactionCode() {
        return transactionCode;
    }
    public void setTransactionCode(String newTransactionCode)
    {
        transactionCode = newTransactionCode;
    }
    
    public String getReversalInd() {
        return reversalInd;
    }
    public void setReversalInd(String newReversalInd)
    {
        reversalInd = newReversalInd;
    }
    
    public String getReasonCode() {
        return reasonCode;
    }
    public void setReasonCode(String newReasonCode)
    {
        reasonCode = newReasonCode;
    }
    
    public String getReasonDescription() {
        return reasonDescription;
    }
    public void setReasonDescription(String newReasonDescription)
    {
        reasonDescription = newReasonDescription;
    }
}
