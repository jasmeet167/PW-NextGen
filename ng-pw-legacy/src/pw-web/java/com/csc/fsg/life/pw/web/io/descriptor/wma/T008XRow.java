package com.csc.fsg.life.pw.web.io.descriptor.wma;

import com.csc.fsg.life.pw.web.io.Row;

public class T008XRow
    extends Row
{
    public String companyCode;
    public String lobCode;
    public String lobName;
    public String lobNmAbbrv;
    public String qualInd;
    public String groupIndicator;
    public String pensionIndicator;
    public String annuityType;
    public String deferredImmedInd;
    
    public String getCompanyCode() {
        return companyCode;
    }
    public void setCompanyCode(String newCompanyCode)
    {
        companyCode = newCompanyCode;
    }
    
    public String getLobCode() {
        return lobCode;
    }
    public void setLobCode(String newLobCode)
    {
        lobCode = newLobCode;
    }
    
    public String getLobName() {
        return lobName;
    }
    public void setLobName(String newLobName)
    {
        lobName = newLobName;
    }
    
    public String getLobNmAbbrv() {
        return lobNmAbbrv;
    }
    public void setLobNmAbbrv(String newLobNmAbbrv)
    {
        lobNmAbbrv = newLobNmAbbrv;
    }
    
    public String getQualInd() {
        return qualInd;
    }
    public void setQualInd(String newQualInd)
    {
        qualInd = newQualInd;
    }
    
    public String getGroupIndicator() {
        return groupIndicator;
    }
    public void setGroupIndicator(String newGroupIndicator)
    {
        groupIndicator = newGroupIndicator;
    }
    
    public String getPensionIndicator() {
        return pensionIndicator;
    }
    public void setPensionIndicator(String newPensionIndicator)
    {
        pensionIndicator = newPensionIndicator;
    }
    
    public String getAnnuityType() {
        return annuityType;
    }
    public void setAnnuityType(String newAnnuityType)
    {
        annuityType = newAnnuityType;
    }
    
    public String getDeferredImmedInd() {
        return deferredImmedInd;
    }
    public void setDeferredImmedInd(String newDeferredImmedInd)
    {
        deferredImmedInd = newDeferredImmedInd;
    }
}
