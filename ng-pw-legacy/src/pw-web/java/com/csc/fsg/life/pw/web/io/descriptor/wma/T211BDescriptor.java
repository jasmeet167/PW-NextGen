package com.csc.fsg.life.pw.web.io.descriptor.wma;

import com.csc.fsg.life.pw.web.io.*;

public class T211BDescriptor
    extends TableDescriptor
{
    private static final String pagingSql = "SELECT COMPANY_CODE, TABLE_SUBSET, STATUTORY_CODE, STATE_CODE, APPROVAL_DATE FROM ";
    
    public void initialize()
    {
        setRowClass(T211BRow.class);
        setTableName("T211B");
        setTableId("211");
        super.initialize();
    }
    
    public void initializeColumnDescriptors()
    {
        super.initializeColumnDescriptors();
        addColumnDescriptor(new ColumnDescriptor(this,"getCompanyCode","setCompanyCode","COMPANY_CODE,1,1,3,0,true|,0,CHAR,1,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getTableSubset","setTableSubset","TABLE_SUBSET,1,2,16,0,true|,0,CHAR,1,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getStatutoryCode","setStatutoryCode","STATUTORY_CODE,1,3,3,0,true|,0,CHAR,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getStateCode","setStateCode","STATE_CODE,1,4,3,0,true|,0,CHAR,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getApprovalDate","setApprovalDate","APPROVAL_DATE,91,5,10,0,false|,0,DATE,0,null,null,null,null,null"));
    }
    
    public String getPagingSQL(String schemaName,boolean isSubsetMode,boolean isNext, boolean locator)
    {
        String pagingWhere = null;
        String order = null;
        if (isNext) {
            if (isSubsetMode)
                if (locator)
                    pagingWhere = ".T211B WHERE (COMPANY_CODE = :company_code) AND (TABLE_SUBSET = :table_subset) AND ((STATUTORY_CODE > :statutory_code) OR (STATUTORY_CODE = :statutory_code AND STATE_CODE > :state_code) OR (STATUTORY_CODE = :statutory_code AND STATE_CODE = :state_code)) ";
                else 
                    pagingWhere = ".T211B WHERE (COMPANY_CODE = :company_code) AND (TABLE_SUBSET = :table_subset) AND ((STATUTORY_CODE > :statutory_code) OR (STATUTORY_CODE = :statutory_code AND STATE_CODE > :state_code)) ";
            else
                if (locator)
                    pagingWhere = ".T211B WHERE (COMPANY_CODE = :company_code) AND ((TABLE_SUBSET > :table_subset) OR (TABLE_SUBSET = :table_subset AND STATUTORY_CODE > :statutory_code) OR (TABLE_SUBSET = :table_subset AND STATUTORY_CODE = :statutory_code AND STATE_CODE > :state_code) OR (TABLE_SUBSET = :table_subset AND STATUTORY_CODE = :statutory_code AND STATE_CODE = :state_code)) ";
                else
                    pagingWhere = ".T211B WHERE (COMPANY_CODE = :company_code) AND ((TABLE_SUBSET > :table_subset) OR (TABLE_SUBSET = :table_subset AND STATUTORY_CODE > :statutory_code) OR (TABLE_SUBSET = :table_subset AND STATUTORY_CODE = :statutory_code AND STATE_CODE > :state_code)) ";
            order = " ORDER BY COMPANY_CODE, TABLE_SUBSET, STATUTORY_CODE, STATE_CODE";
        }
        else {
            if (isSubsetMode)
                pagingWhere = ".T211B WHERE (COMPANY_CODE = :company_code) AND (TABLE_SUBSET = :table_subset) AND ((STATUTORY_CODE < :statutory_code) OR (STATUTORY_CODE = :statutory_code AND STATE_CODE < :state_code)) ";
            else
                pagingWhere = ".T211B WHERE (COMPANY_CODE = :company_code) AND ((TABLE_SUBSET < :table_subset) OR (TABLE_SUBSET = :table_subset AND STATUTORY_CODE < :statutory_code) OR (TABLE_SUBSET = :table_subset AND STATUTORY_CODE = :statutory_code AND STATE_CODE < :state_code)) ";
            order = " ORDER BY COMPANY_CODE DESC, TABLE_SUBSET DESC, STATUTORY_CODE DESC, STATE_CODE DESC";
        }
        return pagingSql + schemaName + pagingWhere + order;
    }
    
    public String prepareInsertStmt(String schemaName) {
        StringBuffer sb = new StringBuffer();
        sb.append("INSERT INTO ").append(schemaName);
        sb.append(".T211B ( ");
        sb.append("COMPANY_CODE, TABLE_SUBSET, STATUTORY_CODE, STATE_CODE, APPROVAL_DATE )");
        sb.append(" VALUES (?, ?, ?, ?, ? )");
        return sb.toString();
    }
    
    public String prepareUpdateStmt(String schemaName)
    {
        StringBuffer sb = new StringBuffer();
        sb.append("UPDATE ").append(schemaName);
        sb.append(".T211B ");
        sb.append(" SET COMPANY_CODE = ?, TABLE_SUBSET = ?, STATUTORY_CODE = ?, STATE_CODE = ?, APPROVAL_DATE = ?");
        sb.append(" WHERE COMPANY_CODE = ? AND TABLE_SUBSET = ? AND STATUTORY_CODE = ? AND STATE_CODE = ?");
        return sb.toString();
    }
    
    public String prepareDeleteStmt(String schemaName)
    {
        StringBuffer sb = new StringBuffer();
        sb.append("DELETE FROM ").append(schemaName);
        sb.append(".T211B ");
        sb.append(" WHERE COMPANY_CODE = ? AND TABLE_SUBSET = ? AND STATUTORY_CODE = ? AND STATE_CODE = ?");
        return sb.toString();
    }
}
