package com.csc.fsg.life.pw.web.io.descriptor.wma;

import com.csc.fsg.life.pw.web.io.Row;

public class T061E1Row
    extends Row
{
    public String companyCode;
    public String tableSubset;
    public String seqNo;
    public String accumMaxDuration;
    public String accumMaxAmount;
    
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
    
    public String getSeqNo() {
        return seqNo;
    }
    public void setSeqNo(String newSeqNo)
    {
        seqNo = newSeqNo;
    }
    
    public String getAccumMaxDuration() {
        return accumMaxDuration;
    }
    public void setAccumMaxDuration(String newAccumMaxDuration)
    {
        accumMaxDuration = newAccumMaxDuration;
    }
    
    public String getAccumMaxAmount() {
        return accumMaxAmount;
    }
    public void setAccumMaxAmount(String newAccumMaxAmount)
    {
        accumMaxAmount = newAccumMaxAmount;
    }
}
