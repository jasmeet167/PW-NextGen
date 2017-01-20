package com.csc.fsg.life.pw.io.descriptor.wma;

import com.csc.fsg.life.pw.io.Row;

public class T0C7XRow
    extends Row
{
    public String companyCode;
    public String productPrefix;
    public String tableSubset;
    public String effectiveDate;
    public String indexPerdDur;
    public String surrPartiRate;
    public String surrPartiRtCap;
    
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
    
    public String getEffectiveDate() {
        return effectiveDate;
    }
    public void setEffectiveDate(String newEffectiveDate)
    {
        effectiveDate = newEffectiveDate;
    }
    
    public String getIndexPerdDur() {
        return indexPerdDur;
    }
    public void setIndexPerdDur(String newIndexPerdDur)
    {
        indexPerdDur = newIndexPerdDur;
    }
    
    public String getSurrPartiRate() {
        return surrPartiRate;
    }
    public void setSurrPartiRate(String newSurrPartiRate)
    {
        surrPartiRate = newSurrPartiRate;
    }
    
    public String getSurrPartiRtCap() {
        return surrPartiRtCap;
    }
    public void setSurrPartiRtCap(String newSurrPartiRtCap)
    {
        surrPartiRtCap = newSurrPartiRtCap;
    }
}
