package com.csc.fsg.life.pw.io.descriptor.wma;

import com.csc.fsg.life.pw.io.Row;

public class T016XRow
    extends Row
{
    public String companyCode;
    public String productPrefix;
    public String productSuffix;
    public String planCode;
    public String lobCode;
    public String pacPayExtDt;
    public String pmtCrInd;
    public String extractDate;
    
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
    
    public String getProductSuffix() {
        return productSuffix;
    }
    public void setProductSuffix(String newProductSuffix)
    {
        productSuffix = newProductSuffix;
    }
    
    public String getPlanCode() {
        return planCode;
    }
    public void setPlanCode(String newPlanCode)
    {
        planCode = newPlanCode;
    }
    
    public String getLobCode() {
        return lobCode;
    }
    public void setLobCode(String newLobCode)
    {
        lobCode = newLobCode;
    }
    
    public String getPacPayExtDt() {
        return pacPayExtDt;
    }
    public void setPacPayExtDt(String newPacPayExtDt)
    {
        pacPayExtDt = newPacPayExtDt;
    }
    
    public String getPmtCrInd() {
        return pmtCrInd;
    }
    public void setPmtCrInd(String newPmtCrInd)
    {
        pmtCrInd = newPmtCrInd;
    }
    
    public String getExtractDate() {
        return extractDate;
    }
    public void setExtractDate(String newExtractDate)
    {
        extractDate = newExtractDate;
    }
}
