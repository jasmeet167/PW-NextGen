package com.csc.fsg.life.pw.io.descriptor.wma;

import com.csc.fsg.life.pw.io.Row;

public class T027ERow
    extends Row
{
    public String companyCode;
    public String tableSubset;
    public String fundValueBase;
    public String baseMnyIntRt;
    
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
    
    public String getFundValueBase() {
        return fundValueBase;
    }
    public void setFundValueBase(String newFundValueBase)
    {
        fundValueBase = newFundValueBase;
    }
    
    public String getBaseMnyIntRt() {
        return baseMnyIntRt;
    }
    public void setBaseMnyIntRt(String newBaseMnyIntRt)
    {
        baseMnyIntRt = newBaseMnyIntRt;
    }
}
