package com.csc.fsg.life.pw.web.io.descriptor.wma;

import com.csc.fsg.life.pw.web.io.Row;

public class TW70XRow
    extends Row
{
    public String companyCode;
    public String tableSubset;
    public String tableType;
    public String startingAge;
    public String endingAge;
    public String intAsmTbl;
    public String mortAsmTbl;
    
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
    
    public String getStartingAge() {
        return startingAge;
    }
    public void setStartingAge(String newStartingAge)
    {
        startingAge = newStartingAge;
    }
    
    public String getEndingAge() {
        return endingAge;
    }
    public void setEndingAge(String newEndingAge)
    {
        endingAge = newEndingAge;
    }
    
    public String getIntAsmTbl() {
        return intAsmTbl;
    }
    public void setIntAsmTbl(String newIntAsmTbl)
    {
        intAsmTbl = newIntAsmTbl;
    }
    
    public String getMortAsmTbl() {
        return mortAsmTbl;
    }
    public void setMortAsmTbl(String newMortAsmTbl)
    {
        mortAsmTbl = newMortAsmTbl;
    }
}
