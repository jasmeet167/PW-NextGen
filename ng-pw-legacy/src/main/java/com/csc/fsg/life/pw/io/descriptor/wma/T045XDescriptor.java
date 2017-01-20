package com.csc.fsg.life.pw.io.descriptor.wma;

import com.csc.fsg.life.pw.io.*;

public class T045XDescriptor
    extends TableDescriptor
{
    private static final String pagingSql = "SELECT COMPANY_CODE, MESSAGE_CODE, STATUS_REQUEST, DESTINATION_CODE, ACTION_REQ_MSG FROM ";
    
    public void initialize()
    {
        setRowClass(T045XRow.class);
        setTableName("T045X");
        setTableId("045");
        super.initialize();
    }
    
    public void initializeColumnDescriptors()
    {
        super.initializeColumnDescriptors();
        addColumnDescriptor(new ColumnDescriptor(this,"getCompanyCode","setCompanyCode","COMPANY_CODE,1,1,3,0,true|,0,CHAR,1,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getMessageCode","setMessageCode","MESSAGE_CODE,1,2,4,0,true|,0,CHAR,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getStatusRequest","setStatusRequest","STATUS_REQUEST,1,3,1,0,false|,0,CHAR,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getDestinationCode","setDestinationCode","DESTINATION_CODE,1,4,8,0,false|,0,CHAR,4,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getActionReqMsg","setActionReqMsg","ACTION_REQ_MSG,1,5,60,0,false|,0,CHAR,0,null,null,null,null,null"));
    }
    
    public String getPagingSQL(String schemaName,boolean isSubsetMode,boolean isNext, boolean locator)
    {
        String pagingWhere = null;
        String order = null;
        if (isNext) {
            if (isSubsetMode)
                if (locator)
                    pagingWhere = ".T045X WHERE (COMPANY_CODE = :company_code) AND ((MESSAGE_CODE > :message_code) OR (MESSAGE_CODE = :message_code)) ";
                else 
                    pagingWhere = ".T045X WHERE (COMPANY_CODE = :company_code) AND ((MESSAGE_CODE > :message_code)) ";
            else
                if (locator)
                    pagingWhere = ".T045X WHERE (COMPANY_CODE = :company_code) AND ((MESSAGE_CODE > :message_code) OR (MESSAGE_CODE = :message_code)) ";
                else
                    pagingWhere = ".T045X WHERE (COMPANY_CODE = :company_code) AND ((MESSAGE_CODE > :message_code)) ";
            order = " ORDER BY COMPANY_CODE, MESSAGE_CODE";
        }
        else {
            if (isSubsetMode)
                pagingWhere = ".T045X WHERE (COMPANY_CODE = :company_code) AND ((MESSAGE_CODE < :message_code)) ";
            else
                pagingWhere = ".T045X WHERE (COMPANY_CODE = :company_code) AND ((MESSAGE_CODE < :message_code)) ";
            order = " ORDER BY COMPANY_CODE DESC, MESSAGE_CODE DESC";
        }
        return pagingSql + schemaName + pagingWhere + order;
    }
    
    public String prepareInsertStmt(String schemaName) {
        StringBuffer sb = new StringBuffer();
        sb.append("INSERT INTO ").append(schemaName);
        sb.append(".T045X ( ");
        sb.append("COMPANY_CODE, MESSAGE_CODE, STATUS_REQUEST, DESTINATION_CODE, ACTION_REQ_MSG )");
        sb.append(" VALUES (?, ?, ?, ?, ? )");
        return sb.toString();
    }
    
    public String prepareUpdateStmt(String schemaName)
    {
        StringBuffer sb = new StringBuffer();
        sb.append("UPDATE ").append(schemaName);
        sb.append(".T045X ");
        sb.append(" SET COMPANY_CODE = ?, MESSAGE_CODE = ?, STATUS_REQUEST = ?, DESTINATION_CODE = ?, ACTION_REQ_MSG = ?");
        sb.append(" WHERE COMPANY_CODE = ? AND MESSAGE_CODE = ?");
        return sb.toString();
    }
    
    public String prepareDeleteStmt(String schemaName)
    {
        StringBuffer sb = new StringBuffer();
        sb.append("DELETE FROM ").append(schemaName);
        sb.append(".T045X ");
        sb.append(" WHERE COMPANY_CODE = ? AND MESSAGE_CODE = ?");
        return sb.toString();
    }
}
