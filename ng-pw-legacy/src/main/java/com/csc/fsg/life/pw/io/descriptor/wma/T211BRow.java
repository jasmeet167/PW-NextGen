package com.csc.fsg.life.pw.io.descriptor.wma;

import com.csc.fsg.life.pw.io.Row;

public class T211BRow
    extends Row
{
    public String companyCode;
    public String tableSubset;
    public String statutoryCode;
    public String stateCode;
    public String approvalDate;
    
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
    
    public String getStatutoryCode() {
        return statutoryCode;
    }
    public void setStatutoryCode(String newStatutoryCode)
    {
        statutoryCode = newStatutoryCode;
    }
    
    public String getStateCode() {
        return stateCode;
    }
    public void setStateCode(String newStateCode)
    {
        stateCode = newStateCode;
    }
    
    public String getApprovalDate() {
        return approvalDate;
    }
    public void setApprovalDate(String newApprovalDate)
    {
        approvalDate = newApprovalDate;
    }
}
