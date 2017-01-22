package com.csc.fsg.life.pw.web.io.descriptor.wma;

import com.csc.fsg.life.pw.web.io.Row;

public class TTC8TRow
    extends Row
{
    public String companyCode;
    public String tableSubset;
    public String effectiveDate;
    public String maxPolicyDuration;
    public String trxCode;
    public String ropCashValuePercent;
    
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
    
    public String getMaxPolicyDuration() {
        return maxPolicyDuration;
    }
    public void setMaxPolicyDuration(String newMaxPolicyDuration)
    {
        maxPolicyDuration = newMaxPolicyDuration;
    }
    
    public String getTrxCode() {
        return trxCode;
    }
    public void setTrxCode(String newTrxCode)
    {
        trxCode = newTrxCode;
    }
    
    public String getRopCashValuePercent() {
        return ropCashValuePercent;
    }
    public void setRopCashValuePercent(String newRopCashValuePercent)
    {
        ropCashValuePercent = newRopCashValuePercent;
    }
}
