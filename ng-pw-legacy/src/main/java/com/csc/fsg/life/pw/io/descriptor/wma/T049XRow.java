package com.csc.fsg.life.pw.io.descriptor.wma;

import com.csc.fsg.life.pw.io.Row;

public class T049XRow
    extends Row
{
    public String companyCode;
    public String tableSubset;
    public String sequenceNumber;
    public String errorInd;
    public String tableType;
    public String selectPeriod;
    public String startAge;
    public String endAge;
    public String mortRateDefInd;
    
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
    
    public String getSequenceNumber() {
        return sequenceNumber;
    }
    public void setSequenceNumber(String newSequenceNumber)
    {
        sequenceNumber = newSequenceNumber;
    }
    
    public String getErrorInd() {
        return errorInd;
    }
    public void setErrorInd(String newErrorInd)
    {
        errorInd = newErrorInd;
    }
    
    public String getTableType() {
        return tableType;
    }
    public void setTableType(String newTableType)
    {
        tableType = newTableType;
    }
    
    public String getSelectPeriod() {
        return selectPeriod;
    }
    public void setSelectPeriod(String newSelectPeriod)
    {
        selectPeriod = newSelectPeriod;
    }
    
    public String getStartAge() {
        return startAge;
    }
    public void setStartAge(String newStartAge)
    {
        startAge = newStartAge;
    }
    
    public String getEndAge() {
        return endAge;
    }
    public void setEndAge(String newEndAge)
    {
        endAge = newEndAge;
    }
    
    public String getMortRateDefInd() {
        return mortRateDefInd;
    }
    public void setMortRateDefInd(String newMortRateDefInd)
    {
        mortRateDefInd = newMortRateDefInd;
    }
}
