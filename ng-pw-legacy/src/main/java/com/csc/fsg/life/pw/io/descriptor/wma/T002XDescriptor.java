package com.csc.fsg.life.pw.io.descriptor.wma;

import com.csc.fsg.life.pw.io.*;

public class T002XDescriptor
    extends TableDescriptor
{
    private static final String pagingSql = "SELECT COMPANY_CODE, ERROR_CODE, SEVERITY_CODE, STATUS_INDICATOR, ERROR_MESSAGE, LONG_MESSAGE FROM ";
    
    public void initialize()
    {
        setRowClass(T002XRow.class);
        setTableName("T002X");
        setTableId("002");
        super.initialize();
    }
    
    public void initializeColumnDescriptors()
    {
        super.initializeColumnDescriptors();
        addColumnDescriptor(new ColumnDescriptor(this,"getCompanyCode","setCompanyCode","COMPANY_CODE,1,1,3,0,true|,0,CHAR,1,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getErrorCode","setErrorCode","ERROR_CODE,1,2,4,0,true|,0,CHAR,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getSeverityCode","setSeverityCode","SEVERITY_CODE,1,3,1,0,false|,0,CHAR,2,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getStatusIndicator","setStatusIndicator","STATUS_INDICATOR,1,4,1,0,false|,0,CHAR,2,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getErrorMessage","setErrorMessage","ERROR_MESSAGE,1,5,32,0,false|,0,CHAR,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getLongMessage","setLongMessage","LONG_MESSAGE,1,6,100,0,false|,0,CHAR,4,null,null,null,null,null"));
    }
    
    public String getPagingSQL(String schemaName,boolean isSubsetMode,boolean isNext, boolean locator)
    {
        String pagingWhere = null;
        String order = null;
        if (isNext) {
            if (isSubsetMode)
                if (locator)
                    pagingWhere = ".T002X WHERE (COMPANY_CODE = :company_code) AND ((ERROR_CODE > :error_code) OR (ERROR_CODE = :error_code)) ";
                else 
                    pagingWhere = ".T002X WHERE (COMPANY_CODE = :company_code) AND ((ERROR_CODE > :error_code)) ";
            else
                if (locator)
                    pagingWhere = ".T002X WHERE (COMPANY_CODE = :company_code) AND ((ERROR_CODE > :error_code) OR (ERROR_CODE = :error_code)) ";
                else
                    pagingWhere = ".T002X WHERE (COMPANY_CODE = :company_code) AND ((ERROR_CODE > :error_code)) ";
            order = " ORDER BY COMPANY_CODE, ERROR_CODE";
        }
        else {
            if (isSubsetMode)
                pagingWhere = ".T002X WHERE (COMPANY_CODE = :company_code) AND ((ERROR_CODE < :error_code)) ";
            else
                pagingWhere = ".T002X WHERE (COMPANY_CODE = :company_code) AND ((ERROR_CODE < :error_code)) ";
            order = " ORDER BY COMPANY_CODE DESC, ERROR_CODE DESC";
        }
        return pagingSql + schemaName + pagingWhere + order;
    }
    
    public String prepareInsertStmt(String schemaName) {
        StringBuffer sb = new StringBuffer();
        sb.append("INSERT INTO ").append(schemaName);
        sb.append(".T002X ( ");
        sb.append("COMPANY_CODE, ERROR_CODE, SEVERITY_CODE, STATUS_INDICATOR, ERROR_MESSAGE, LONG_MESSAGE )");
        sb.append(" VALUES (?, ?, ?, ?, ?, ? )");
        return sb.toString();
    }
    
    public String prepareUpdateStmt(String schemaName)
    {
        StringBuffer sb = new StringBuffer();
        sb.append("UPDATE ").append(schemaName);
        sb.append(".T002X ");
        sb.append(" SET COMPANY_CODE = ?, ERROR_CODE = ?, SEVERITY_CODE = ?, STATUS_INDICATOR = ?, ERROR_MESSAGE = ?, LONG_MESSAGE = ?");
        sb.append(" WHERE COMPANY_CODE = ? AND ERROR_CODE = ?");
        return sb.toString();
    }
    
    public String prepareDeleteStmt(String schemaName)
    {
        StringBuffer sb = new StringBuffer();
        sb.append("DELETE FROM ").append(schemaName);
        sb.append(".T002X ");
        sb.append(" WHERE COMPANY_CODE = ? AND ERROR_CODE = ?");
        return sb.toString();
    }
}
