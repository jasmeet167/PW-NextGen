package com.csc.fsg.life.pw.io.descriptor.wma;

import com.csc.fsg.life.pw.io.Row;

public class T025XRow
    extends Row
{
    public String companyCode;
    public String productPrefix;
    public String tableSubset;
    public String issueState;
    public String rcptPerdStrtDt;
    public String intRtEffDt;
    public String durationMonths;
    public String settlDateInd;
    public String interestRate;
    
    public String getCompanyCode() {
        return companyCode;
    }
    public void setCompanyCode(String newCompanyCode)
    {
        companyCode = newCompanyCode;
    }
    
    public String getProductPrefix() {
        return productPrefix;
    }
    public void setProductPrefix(String newProductPrefix)
    {
        productPrefix = newProductPrefix;
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
    
    public String getDurationMonths() {
        return durationMonths;
    }
    public void setDurationMonths(String newDurationMonths)
    {
        durationMonths = newDurationMonths;
    }
    
    public String getSettlDateInd() {
        return settlDateInd;
    }
    public void setSettlDateInd(String newSettlDateInd)
    {
        settlDateInd = newSettlDateInd;
    }
    
    public String getInterestRate() {
        return interestRate;
    }
    public void setInterestRate(String newInterestRate)
    {
        interestRate = newInterestRate;
    }
}
