package com.csc.fsg.life.pw.web.io.descriptor.wma;

import com.csc.fsg.life.pw.web.io.Row;

public class T089ERow
    extends Row
{
    public String companyCode;
    public String tableSubset;
    public String issueState;
    public String bonusRateEffDt;
    public String maxPolMnths;
    public String bonusRate;
    
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
    
    public String getBonusRateEffDt() {
        return bonusRateEffDt;
    }
    public void setBonusRateEffDt(String newBonusRateEffDt)
    {
        bonusRateEffDt = newBonusRateEffDt;
    }
    
    public String getMaxPolMnths() {
        return maxPolMnths;
    }
    public void setMaxPolMnths(String newMaxPolMnths)
    {
        maxPolMnths = newMaxPolMnths;
    }
    
    public String getBonusRate() {
        return bonusRate;
    }
    public void setBonusRate(String newBonusRate)
    {
        bonusRate = newBonusRate;
    }
}
