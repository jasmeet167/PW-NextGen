package com.csc.fsg.life.pw.io.descriptor.wma;

import com.csc.fsg.life.pw.io.Row;
import com.fasterxml.jackson.annotation.JsonIgnore;

public class T002XRow
    extends Row
{
    public String companyCode;
    public String errorCode;
    public String severityCode;
    public String statusIndicator;
    public String errorMessage;
    public String longMessage;
    
    public String getCompanyCode() {
        return companyCode;
    }
    public void setCompanyCode(String newCompanyCode)
    {
        companyCode = newCompanyCode;
    }
    
    public String getErrorCode() {
        return errorCode;
    }
    public void setErrorCode(String newErrorCode)
    {
        errorCode = newErrorCode;
    }
    
    public String getSeverityCode() {
        return severityCode;
    }
    public void setSeverityCode(String newSeverityCode)
    {
        severityCode = newSeverityCode;
    }
    
    public String getStatusIndicator() {
        return statusIndicator;
    }
    public void setStatusIndicator(String newStatusIndicator)
    {
        statusIndicator = newStatusIndicator;
    }
    
    public String getErrorMessage() {
        return errorMessage;
    }
    public void setErrorMessage(String newErrorMessage)
    {
        errorMessage = newErrorMessage;
    }
    
    public String getLongMessage() {
        return longMessage;
    }
    public void setLongMessage(String newLongMessage)
    {
        longMessage = newLongMessage;
    }

    @JsonIgnore
	public String getTableSubset() {
		return super.getTableSubset();
	}
}
