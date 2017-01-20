package com.csc.fsg.life.pw.io.descriptor.wma;

import com.csc.fsg.life.pw.io.Row;

public class TW69XRow
    extends Row
{
    public String companyCode;
    public String tableSubset;
    public String tableType;
    public String strtPlcyYy;
    public String endingPolicyYear;
    
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
    
    public String getStrtPlcyYy() {
        return strtPlcyYy;
    }
    public void setStrtPlcyYy(String newStrtPlcyYy)
    {
        strtPlcyYy = newStrtPlcyYy;
    }
    
    public String getEndingPolicyYear() {
        return endingPolicyYear;
    }
    public void setEndingPolicyYear(String newEndingPolicyYear)
    {
        endingPolicyYear = newEndingPolicyYear;
    }
}
