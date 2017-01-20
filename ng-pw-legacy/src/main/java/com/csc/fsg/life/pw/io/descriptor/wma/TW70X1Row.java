package com.csc.fsg.life.pw.io.descriptor.wma;

import com.csc.fsg.life.pw.io.Row;

public class TW70X1Row
    extends Row
{
    public String companyCode;
    public String tableSubset;
    public String issueAge;
    public String duration;
    public String dSubX;
    public String lnVSuperT;
    public String annuityDue;
    
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
    
    public String getIssueAge() {
        return issueAge;
    }
    public void setIssueAge(String newIssueAge)
    {
        issueAge = newIssueAge;
    }
    
    public String getDuration() {
        return duration;
    }
    public void setDuration(String newDuration)
    {
        duration = newDuration;
    }
    
    public String getDSubX() {
        return dSubX;
    }
    public void setDSubX(String newDSubX)
    {
        dSubX = newDSubX;
    }
    
    public String getLnVSuperT() {
        return lnVSuperT;
    }
    public void setLnVSuperT(String newLnVSuperT)
    {
        lnVSuperT = newLnVSuperT;
    }
    
    public String getAnnuityDue() {
        return annuityDue;
    }
    public void setAnnuityDue(String newAnnuityDue)
    {
        annuityDue = newAnnuityDue;
    }
}
