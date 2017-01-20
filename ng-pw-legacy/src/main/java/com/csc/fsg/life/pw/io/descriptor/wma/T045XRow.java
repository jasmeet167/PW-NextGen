package com.csc.fsg.life.pw.io.descriptor.wma;

import com.csc.fsg.life.pw.io.Row;

public class T045XRow
    extends Row
{
    public String companyCode;
    public String messageCode;
    public String statusRequest;
    public String destinationCode;
    public String actionReqMsg;
    
    public String getCompanyCode() {
        return companyCode;
    }
    public void setCompanyCode(String newCompanyCode)
    {
        companyCode = newCompanyCode;
    }
    
    public String getMessageCode() {
        return messageCode;
    }
    public void setMessageCode(String newMessageCode)
    {
        messageCode = newMessageCode;
    }
    
    public String getStatusRequest() {
        return statusRequest;
    }
    public void setStatusRequest(String newStatusRequest)
    {
        statusRequest = newStatusRequest;
    }
    
    public String getDestinationCode() {
        return destinationCode;
    }
    public void setDestinationCode(String newDestinationCode)
    {
        destinationCode = newDestinationCode;
    }
    
    public String getActionReqMsg() {
        return actionReqMsg;
    }
    public void setActionReqMsg(String newActionReqMsg)
    {
        actionReqMsg = newActionReqMsg;
    }
}
