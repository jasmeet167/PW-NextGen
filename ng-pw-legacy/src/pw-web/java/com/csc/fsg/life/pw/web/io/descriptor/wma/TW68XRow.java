package com.csc.fsg.life.pw.web.io.descriptor.wma;

import com.csc.fsg.life.pw.web.io.Row;

public class TW68XRow
    extends Row
{
    public String companyCode;
    public String tableSubset;
    public String tableType;
    public String startAge;
    public String endAge;
    public String selectPeriod;
    
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
    
    public String getTableType() {
        return tableType;
    }
    public void setTableType(String newTableType)
    {
        tableType = newTableType;
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
    
    public String getSelectPeriod() {
        return selectPeriod;
    }
    public void setSelectPeriod(String newSelectPeriod)
    {
        selectPeriod = newSelectPeriod;
    }
}
