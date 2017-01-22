package com.csc.fsg.life.pw.web.io.descriptor.wma;

import com.csc.fsg.life.pw.web.io.Row;

public class T090XRow
    extends Row
{
    public String companyCode;
    public String assetAcctCode;
    public String moneySourceCode;
    public String assetAcctName;
    public String sourceDesc;
    
    public String getCompanyCode() {
        return companyCode;
    }
    public void setCompanyCode(String newCompanyCode)
    {
        companyCode = newCompanyCode;
    }
    
    public String getAssetAcctCode() {
        return assetAcctCode;
    }
    public void setAssetAcctCode(String newAssetAcctCode)
    {
        assetAcctCode = newAssetAcctCode;
    }
    
    public String getMoneySourceCode() {
        return moneySourceCode;
    }
    public void setMoneySourceCode(String newMoneySourceCode)
    {
        moneySourceCode = newMoneySourceCode;
    }
    
    public String getAssetAcctName() {
        return assetAcctName;
    }
    public void setAssetAcctName(String newAssetAcctName)
    {
        assetAcctName = newAssetAcctName;
    }
    
    public String getSourceDesc() {
        return sourceDesc;
    }
    public void setSourceDesc(String newSourceDesc)
    {
        sourceDesc = newSourceDesc;
    }
}
