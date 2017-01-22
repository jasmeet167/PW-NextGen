package com.csc.fsg.life.pw.web.io.descriptor.wma;

import com.csc.fsg.life.pw.web.io.Row;

public class TAB8FRow
    extends Row
{
    public String companyCode;
    public String tableSubset;
    public String effectiveDate;
    public String issueAge;
    public String sexCode;
    public String undrwrtngBasisCd;
    public String memoCode;
    public String pctgOfPrem;
    
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
    
    public String getEffectiveDate() {
        return effectiveDate;
    }
    public void setEffectiveDate(String newEffectiveDate)
    {
        effectiveDate = newEffectiveDate;
    }
    
    public String getIssueAge() {
        return issueAge;
    }
    public void setIssueAge(String newIssueAge)
    {
        issueAge = newIssueAge;
    }
    
    public String getSexCode() {
        return sexCode;
    }
    public void setSexCode(String newSexCode)
    {
        sexCode = newSexCode;
    }
    
    public String getUndrwrtngBasisCd() {
        return undrwrtngBasisCd;
    }
    public void setUndrwrtngBasisCd(String newUndrwrtngBasisCd)
    {
        undrwrtngBasisCd = newUndrwrtngBasisCd;
    }
    
    public String getMemoCode() {
        return memoCode;
    }
    public void setMemoCode(String newMemoCode)
    {
        memoCode = newMemoCode;
    }
    
    public String getPctgOfPrem() {
        return pctgOfPrem;
    }
    public void setPctgOfPrem(String newPctgOfPrem)
    {
        pctgOfPrem = newPctgOfPrem;
    }
}
