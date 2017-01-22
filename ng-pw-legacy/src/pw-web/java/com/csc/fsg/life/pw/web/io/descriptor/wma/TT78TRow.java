package com.csc.fsg.life.pw.web.io.descriptor.wma;

import com.csc.fsg.life.pw.web.io.Row;

public class TT78TRow
    extends Row
{
    public String companyCode;
    public String tableSubset;
    public String effectiveDate;
    public String billOptionCode;
    public String fractionalPeriod;
    public String fractionalFactor;
    public String modalBasis;
    
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
    
    public String getBillOptionCode() {
        return billOptionCode;
    }
    public void setBillOptionCode(String newBillOptionCode)
    {
        billOptionCode = newBillOptionCode;
    }
    
    public String getFractionalPeriod() {
        return fractionalPeriod;
    }
    public void setFractionalPeriod(String newFractionalPeriod)
    {
        fractionalPeriod = newFractionalPeriod;
    }
    
    public String getFractionalFactor() {
        return fractionalFactor;
    }
    public void setFractionalFactor(String newFractionalFactor)
    {
        fractionalFactor = newFractionalFactor;
    }
    
    public String getModalBasis() {
        return modalBasis;
    }
    public void setModalBasis(String newModalBasis)
    {
        modalBasis = newModalBasis;
    }
}
