package com.csc.fsg.life.pw.io.descriptor.wma;

import com.csc.fsg.life.pw.io.Row;

public class TTB6TRow
    extends Row
{
    public String companyCode;
    public String tableSubset;
    public String effectiveDate;
    public String agentContCode;
    public String premiumBand;
    public String maxDuration;
    public String commissionRate;
    public String commissionAmt;
    public String commissRateUnit;
    public String renCommissRate;
    public String serCommissRate;
    
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
    
    public String getAgentContCode() {
        return agentContCode;
    }
    public void setAgentContCode(String newAgentContCode)
    {
        agentContCode = newAgentContCode;
    }
    
    public String getPremiumBand() {
        return premiumBand;
    }
    public void setPremiumBand(String newPremiumBand)
    {
        premiumBand = newPremiumBand;
    }
    
    public String getMaxDuration() {
        return maxDuration;
    }
    public void setMaxDuration(String newMaxDuration)
    {
        maxDuration = newMaxDuration;
    }
    
    public String getCommissionRate() {
        return commissionRate;
    }
    public void setCommissionRate(String newCommissionRate)
    {
        commissionRate = newCommissionRate;
    }
    
    public String getCommissionAmt() {
        return commissionAmt;
    }
    public void setCommissionAmt(String newCommissionAmt)
    {
        commissionAmt = newCommissionAmt;
    }
    
    public String getCommissRateUnit() {
        return commissRateUnit;
    }
    public void setCommissRateUnit(String newCommissRateUnit)
    {
        commissRateUnit = newCommissRateUnit;
    }
    
    public String getRenCommissRate() {
        return renCommissRate;
    }
    public void setRenCommissRate(String newRenCommissRate)
    {
        renCommissRate = newRenCommissRate;
    }
    
    public String getSerCommissRate() {
        return serCommissRate;
    }
    public void setSerCommissRate(String newSerCommissRate)
    {
        serCommissRate = newSerCommissRate;
    }
}
