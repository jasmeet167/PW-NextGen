package com.csc.fsg.life.pw.io.descriptor.wma;

import com.csc.fsg.life.pw.io.Row;

public class TTD1TRow
    extends Row
{
    public String companyCode;
    public String tableSubset;
    public String effectiveDate;
    public String eoiAvlMinAge;
    public String eoiAvlMaxAge;
    public String newInsrdMinAge;
    public String newInsrdMaxAge;
    public String costDuePercent;
    public String gfatherStatChng;
    
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
    
    public String getEoiAvlMinAge() {
        return eoiAvlMinAge;
    }
    public void setEoiAvlMinAge(String newEoiAvlMinAge)
    {
        eoiAvlMinAge = newEoiAvlMinAge;
    }
    
    public String getEoiAvlMaxAge() {
        return eoiAvlMaxAge;
    }
    public void setEoiAvlMaxAge(String newEoiAvlMaxAge)
    {
        eoiAvlMaxAge = newEoiAvlMaxAge;
    }
    
    public String getNewInsrdMinAge() {
        return newInsrdMinAge;
    }
    public void setNewInsrdMinAge(String newNewInsrdMinAge)
    {
        newInsrdMinAge = newNewInsrdMinAge;
    }
    
    public String getNewInsrdMaxAge() {
        return newInsrdMaxAge;
    }
    public void setNewInsrdMaxAge(String newNewInsrdMaxAge)
    {
        newInsrdMaxAge = newNewInsrdMaxAge;
    }
    
    public String getCostDuePercent() {
        return costDuePercent;
    }
    public void setCostDuePercent(String newCostDuePercent)
    {
        costDuePercent = newCostDuePercent;
    }
    
    public String getGfatherStatChng() {
        return gfatherStatChng;
    }
    public void setGfatherStatChng(String newGfatherStatChng)
    {
        gfatherStatChng = newGfatherStatChng;
    }
}
