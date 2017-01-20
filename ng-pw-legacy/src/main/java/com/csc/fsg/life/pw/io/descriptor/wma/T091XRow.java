package com.csc.fsg.life.pw.io.descriptor.wma;

import com.csc.fsg.life.pw.io.Row;

/* Modifications: ENH1046 */

public class T091XRow
    extends Row
{
    public String companyCode;
    public String productPrefix;
    public String tableSubset;
    public String effectiveDate;
    public String transactionCode;
    public String hccRiderPlanCd;
    public String fundType;
    public String fundNumber;
    public String fundPriority;
    public String ovrdPermitted;
    
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
    
    public String getTransactionCode() {
        return transactionCode;
    }
    public void setTransactionCode(String newTransactionCode)
    {
        transactionCode = newTransactionCode;
    }
    
    public String getHccRiderPlanCd() {
        return hccRiderPlanCd;
    }
    public void setHccRiderPlanCd(String newHccRiderPlanCd)
    {
        hccRiderPlanCd = newHccRiderPlanCd;
    }
    
    public String getFundType() {
        return fundType;
    }
    public void setFundType(String newFundType)
    {
        fundType = newFundType;
    }
    
    public String getFundNumber() {
        return fundNumber;
    }
    public void setFundNumber(String newFundNumber)
    {
        fundNumber = newFundNumber;
    }
    
    public String getFundPriority() {
        return fundPriority;
    }
    public void setFundPriority(String newFundPriority)
    {
        fundPriority = newFundPriority;
    }
    
    public String getOvrdPermitted() {
        return ovrdPermitted;
    }
    public void setOvrdPermitted(String newOvrdPermitted)
    {
        ovrdPermitted = newOvrdPermitted;
    }
}
