package com.csc.fsg.life.pw.io.descriptor.wma;

import com.csc.fsg.life.pw.io.Row;
import com.fasterxml.jackson.annotation.JsonIgnore;

public class T001XRow
    extends Row
{
    public String companyCode;
    public String companyName;
    public String companyNameAbbrv;
    public String citizenshpReqInd;
    public String ownerResReqInd;
    
    public String getCompanyCode() {
        return companyCode;
    }
    public void setCompanyCode(String newCompanyCode)
    {
        companyCode = newCompanyCode;
    }
    
    public String getCompanyName() {
        return companyName;
    }
    public void setCompanyName(String newCompanyName)
    {
        companyName = newCompanyName;
    }
    
    public String getCompanyNameAbbrv() {
        return companyNameAbbrv;
    }
    public void setCompanyNameAbbrv(String newCompanyNameAbbrv)
    {
        companyNameAbbrv = newCompanyNameAbbrv;
    }
    
    public String getCitizenshpReqInd() {
        return citizenshpReqInd;
    }
    public void setCitizenshpReqInd(String newCitizenshpReqInd)
    {
        citizenshpReqInd = newCitizenshpReqInd;
    }
    
    public String getOwnerResReqInd() {
        return ownerResReqInd;
    }
    public void setOwnerResReqInd(String newOwnerResReqInd)
    {
        ownerResReqInd = newOwnerResReqInd;
    }

    @JsonIgnore
	public String getTableSubset() {
		return super.getTableSubset();
	}
}
