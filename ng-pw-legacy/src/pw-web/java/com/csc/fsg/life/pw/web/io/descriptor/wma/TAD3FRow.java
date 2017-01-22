package com.csc.fsg.life.pw.web.io.descriptor.wma;

import com.csc.fsg.life.pw.web.io.Row;

public class TAD3FRow
    extends Row
{
    public String companyCode;
    public String tableSubset;
    public String effectiveDate;
    public String thresholdAmount;
    public String guaranteeDuration;
    public String declTblNumber;
    public String declGuarOption;
    public String declGuarTiming;
    public String declRecptSelect;
    public String noticeDays;
    public String windowDaysPrior;
    public String windowDaysAfter;
    
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
    
    public String getThresholdAmount() {
        return thresholdAmount;
    }
    public void setThresholdAmount(String newThresholdAmount)
    {
        thresholdAmount = newThresholdAmount;
    }
    
    public String getGuaranteeDuration() {
        return guaranteeDuration;
    }
    public void setGuaranteeDuration(String newGuaranteeDuration)
    {
        guaranteeDuration = newGuaranteeDuration;
    }
    
    public String getDeclTblNumber() {
        return declTblNumber;
    }
    public void setDeclTblNumber(String newDeclTblNumber)
    {
        declTblNumber = newDeclTblNumber;
    }
    
    public String getDeclGuarOption() {
        return declGuarOption;
    }
    public void setDeclGuarOption(String newDeclGuarOption)
    {
        declGuarOption = newDeclGuarOption;
    }
    
    public String getDeclGuarTiming() {
        return declGuarTiming;
    }
    public void setDeclGuarTiming(String newDeclGuarTiming)
    {
        declGuarTiming = newDeclGuarTiming;
    }
    
    public String getDeclRecptSelect() {
        return declRecptSelect;
    }
    public void setDeclRecptSelect(String newDeclRecptSelect)
    {
        declRecptSelect = newDeclRecptSelect;
    }
    
    public String getNoticeDays() {
        return noticeDays;
    }
    public void setNoticeDays(String newNoticeDays)
    {
        noticeDays = newNoticeDays;
    }
    
    public String getWindowDaysPrior() {
        return windowDaysPrior;
    }
    public void setWindowDaysPrior(String newWindowDaysPrior)
    {
        windowDaysPrior = newWindowDaysPrior;
    }
    
    public String getWindowDaysAfter() {
        return windowDaysAfter;
    }
    public void setWindowDaysAfter(String newWindowDaysAfter)
    {
        windowDaysAfter = newWindowDaysAfter;
    }
}
