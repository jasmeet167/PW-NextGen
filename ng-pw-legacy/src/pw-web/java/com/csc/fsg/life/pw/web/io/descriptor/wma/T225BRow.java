package com.csc.fsg.life.pw.web.io.descriptor.wma;

import com.csc.fsg.life.pw.web.io.Row;

public class T225BRow
    extends Row
{
    public String companyCode;
    public String tableSubset;
    public String issueState;
    public String rcptPerdStrtDt;
    public String intRtEffDt;
    public String interestRate;
    
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
    
    public String getIssueState() {
        return issueState;
    }
    public void setIssueState(String newIssueState)
    {
        issueState = newIssueState;
    }
    
    public String getRcptPerdStrtDt() {
        return rcptPerdStrtDt;
    }
    public void setRcptPerdStrtDt(String newRcptPerdStrtDt)
    {
        rcptPerdStrtDt = newRcptPerdStrtDt;
    }
    
    public String getIntRtEffDt() {
        return intRtEffDt;
    }
    public void setIntRtEffDt(String newIntRtEffDt)
    {
        intRtEffDt = newIntRtEffDt;
    }
    
    public String getInterestRate() {
        return interestRate;
    }
    public void setInterestRate(String newInterestRate)
    {
        interestRate = newInterestRate;
    }
}
