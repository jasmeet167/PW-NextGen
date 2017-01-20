package com.csc.fsg.life.pw.io.descriptor.wma;

import com.csc.fsg.life.pw.io.Row;

public class TH01HRow
    extends Row
{
    public String companyCode;
    public String tableSubset;
    public String effectiveDate;
    public String currValueAmount;
    public String currValueUnits;
    public String hccRiderPlanCd;
    public String hccRiderType;
    public String policyPersonInd;
    public String hccDedPriority;
    
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
    
    public String getCurrValueAmount() {
        return currValueAmount;
    }
    public void setCurrValueAmount(String newCurrValueAmount)
    {
        currValueAmount = newCurrValueAmount;
    }
    
    public String getCurrValueUnits() {
        return currValueUnits;
    }
    public void setCurrValueUnits(String newCurrValueUnits)
    {
        currValueUnits = newCurrValueUnits;
    }
    
    public String getHccRiderPlanCd() {
        return hccRiderPlanCd;
    }
    public void setHccRiderPlanCd(String newHccRiderPlanCd)
    {
        hccRiderPlanCd = newHccRiderPlanCd;
    }
    
    public String getHccRiderType() {
        return hccRiderType;
    }
    public void setHccRiderType(String newHccRiderType)
    {
        hccRiderType = newHccRiderType;
    }
    
    public String getPolicyPersonInd() {
        return policyPersonInd;
    }
    public void setPolicyPersonInd(String newPolicyPersonInd)
    {
        policyPersonInd = newPolicyPersonInd;
    }
    
    public String getHccDedPriority() {
        return hccDedPriority;
    }
    public void setHccDedPriority(String newHccDedPriority)
    {
        hccDedPriority = newHccDedPriority;
    }
}
