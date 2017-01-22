package com.csc.fsg.life.pw.web.io.descriptor.wma;

import com.csc.fsg.life.pw.web.io.Row;

public class TW06XRow
    extends Row
{
    public String companyCode;
    public String maritalStatus;
    public String pymtMode;
    public String effectiveDate;
    public String allowanceValue;
    public String nonPrdcWthldPct;
    public String intOnlyWthldPct;
    public String intOnlyMinAmt;
    public String socSecPct;
    
    public String getCompanyCode() {
        return companyCode;
    }
    public void setCompanyCode(String newCompanyCode)
    {
        companyCode = newCompanyCode;
    }
    
    public String getMaritalStatus() {
        return maritalStatus;
    }
    public void setMaritalStatus(String newMaritalStatus)
    {
        maritalStatus = newMaritalStatus;
    }
    
    public String getPymtMode() {
        return pymtMode;
    }
    public void setPymtMode(String newPymtMode)
    {
        pymtMode = newPymtMode;
    }
    
    public String getEffectiveDate() {
        return effectiveDate;
    }
    public void setEffectiveDate(String newEffectiveDate)
    {
        effectiveDate = newEffectiveDate;
    }
    
    public String getAllowanceValue() {
        return allowanceValue;
    }
    public void setAllowanceValue(String newAllowanceValue)
    {
        allowanceValue = newAllowanceValue;
    }
    
    public String getNonPrdcWthldPct() {
        return nonPrdcWthldPct;
    }
    public void setNonPrdcWthldPct(String newNonPrdcWthldPct)
    {
        nonPrdcWthldPct = newNonPrdcWthldPct;
    }
    
    public String getIntOnlyWthldPct() {
        return intOnlyWthldPct;
    }
    public void setIntOnlyWthldPct(String newIntOnlyWthldPct)
    {
        intOnlyWthldPct = newIntOnlyWthldPct;
    }
    
    public String getIntOnlyMinAmt() {
        return intOnlyMinAmt;
    }
    public void setIntOnlyMinAmt(String newIntOnlyMinAmt)
    {
        intOnlyMinAmt = newIntOnlyMinAmt;
    }
    
    public String getSocSecPct() {
        return socSecPct;
    }
    public void setSocSecPct(String newSocSecPct)
    {
        socSecPct = newSocSecPct;
    }
}
