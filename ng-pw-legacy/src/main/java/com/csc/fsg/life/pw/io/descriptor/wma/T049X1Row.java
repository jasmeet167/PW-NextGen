package com.csc.fsg.life.pw.io.descriptor.wma;

import com.csc.fsg.life.pw.io.Row;

public class T049X1Row
    extends Row
{
    public String companyCode;
    public String tableSubset;
    public String sequenceNumber;
    public String livingInfoSeq;
    public String issueAge;
    public String policyYear;
    public String noLiving;
    
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
    
    public String getLivingInfoSeq() {
        return livingInfoSeq;
    }
    public void setLivingInfoSeq(String newLivingInfoSeq)
    {
        livingInfoSeq = newLivingInfoSeq;
    }
    
    public String getIssueAge() {
        return issueAge;
    }
    public void setIssueAge(String newIssueAge)
    {
        issueAge = newIssueAge;
    }
    
    public String getPolicyYear() {
        return policyYear;
    }
    public void setPolicyYear(String newPolicyYear)
    {
        policyYear = newPolicyYear;
    }
    
    public String getNoLiving() {
        return noLiving;
    }
    public void setNoLiving(String newNoLiving)
    {
        noLiving = newNoLiving;
    }
}
