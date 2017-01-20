package com.csc.fsg.life.pw.io.descriptor.wma;

import com.csc.fsg.life.pw.io.Row;

public class TW56XRow
    extends Row
{
    public String companyCode;
    public String productPrefix;
    public String tableSubset;
    public String purchSeriesCode;
    public String effectiveDate;
    public String sexCode;
    public String birthYear;
    public String setbackYears;
    
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
    
    public String getSexCode() {
        return sexCode;
    }
    public void setSexCode(String newSexCode)
    {
        sexCode = newSexCode;
    }
    
    public String getBirthYear() {
        return birthYear;
    }
    public void setBirthYear(String newBirthYear)
    {
        birthYear = newBirthYear;
    }
    
    public String getSetbackYears() {
        return setbackYears;
    }
    public void setSetbackYears(String newSetbackYears)
    {
        setbackYears = newSetbackYears;
    }
}
