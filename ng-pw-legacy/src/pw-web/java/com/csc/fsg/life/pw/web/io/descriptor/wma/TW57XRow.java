package com.csc.fsg.life.pw.web.io.descriptor.wma;

import com.csc.fsg.life.pw.web.io.Row;

public class TW57XRow
    extends Row
{
    public String companyCode;
    public String productPrefix;
    public String tableSubset;
    public String purchSeriesCode;
    public String effectiveDate;
    public String benefitDescriptor;
    public String tableType;
    public String purchRateTbl;
    
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
    
    public String getBenefitDescriptor() {
        return benefitDescriptor;
    }
    public void setBenefitDescriptor(String newBenefitDescriptor)
    {
        benefitDescriptor = newBenefitDescriptor;
    }
    
    public String getTableType() {
        return tableType;
    }
    public void setTableType(String newTableType)
    {
        tableType = newTableType;
    }
    
    public String getPurchRateTbl() {
        return purchRateTbl;
    }
    public void setPurchRateTbl(String newPurchRateTbl)
    {
        purchRateTbl = newPurchRateTbl;
    }
}
