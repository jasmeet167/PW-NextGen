package com.csc.fsg.life.pw.web.io.descriptor.wma;

import com.csc.fsg.life.pw.web.io.*;

public class T061E1Descriptor
    extends TableDescriptor
{
    private static final String pagingSql = "SELECT COMPANY_CODE, TABLE_SUBSET, SEQ_NO, ACCUM_MAX_DURATION, ACCUM_MAX_AMOUNT FROM ";
    
    public void initialize()
    {
        setRowClass(T061E1Row.class);
        setTableName("T061E1");
        setTableId("61E");
        super.initialize();
    }
    
    public void initializeColumnDescriptors()
    {
        super.initializeColumnDescriptors();
        addColumnDescriptor(new ColumnDescriptor(this,"getCompanyCode","setCompanyCode","COMPANY_CODE,1,1,3,0,true|,0,CHAR,1,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getTableSubset","setTableSubset","TABLE_SUBSET,1,2,16,0,true|,0,CHAR,1,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getSeqNo","setSeqNo","SEQ_NO,3,3,3,0,true|,0,INTEGER,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getAccumMaxDuration","setAccumMaxDuration","ACCUM_MAX_DURATION,3,4,4,0,false|,0,INTEGER,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getAccumMaxAmount","setAccumMaxAmount","ACCUM_MAX_AMOUNT,3,5,8,0,false|,0,INTEGER,0,null,null,null,null,null"));
    }
    
    public String getPagingSQL(String schemaName,boolean isSubsetMode,boolean isNext, boolean locator)
    {
        String pagingWhere = null;
        String order = null;
        if (isNext) {
            if (isSubsetMode)
                if (locator)
                    pagingWhere = ".T061E1 WHERE (COMPANY_CODE = :company_code) AND (TABLE_SUBSET = :table_subset) AND ((SEQ_NO > :seq_no) OR (SEQ_NO = :seq_no)) ";
                else 
                    pagingWhere = ".T061E1 WHERE (COMPANY_CODE = :company_code) AND (TABLE_SUBSET = :table_subset) AND ((SEQ_NO > :seq_no)) ";
            else
                if (locator)
                    pagingWhere = ".T061E1 WHERE (COMPANY_CODE = :company_code) AND ((TABLE_SUBSET > :table_subset) OR (TABLE_SUBSET = :table_subset AND SEQ_NO > :seq_no) OR (TABLE_SUBSET = :table_subset AND SEQ_NO = :seq_no)) ";
                else
                    pagingWhere = ".T061E1 WHERE (COMPANY_CODE = :company_code) AND ((TABLE_SUBSET > :table_subset) OR (TABLE_SUBSET = :table_subset AND SEQ_NO > :seq_no)) ";
            order = " ORDER BY COMPANY_CODE, TABLE_SUBSET, SEQ_NO";
        }
        else {
            if (isSubsetMode)
                pagingWhere = ".T061E1 WHERE (COMPANY_CODE = :company_code) AND (TABLE_SUBSET = :table_subset) AND ((SEQ_NO < :seq_no)) ";
            else
                pagingWhere = ".T061E1 WHERE (COMPANY_CODE = :company_code) AND ((TABLE_SUBSET < :table_subset) OR (TABLE_SUBSET = :table_subset AND SEQ_NO < :seq_no)) ";
            order = " ORDER BY COMPANY_CODE DESC, TABLE_SUBSET DESC, SEQ_NO DESC";
        }
        return pagingSql + schemaName + pagingWhere + order;
    }
    
    public String prepareInsertStmt(String schemaName) {
        StringBuffer sb = new StringBuffer();
        sb.append("INSERT INTO ").append(schemaName);
        sb.append(".T061E1 ( ");
        sb.append("COMPANY_CODE, TABLE_SUBSET, SEQ_NO, ACCUM_MAX_DURATION, ACCUM_MAX_AMOUNT )");
        sb.append(" VALUES (?, ?, ?, ?, ? )");
        return sb.toString();
    }
    
    public String prepareUpdateStmt(String schemaName)
    {
        StringBuffer sb = new StringBuffer();
        sb.append("UPDATE ").append(schemaName);
        sb.append(".T061E1 ");
        sb.append(" SET COMPANY_CODE = ?, TABLE_SUBSET = ?, SEQ_NO = ?, ACCUM_MAX_DURATION = ?, ACCUM_MAX_AMOUNT = ?");
        sb.append(" WHERE COMPANY_CODE = ? AND TABLE_SUBSET = ? AND SEQ_NO = ?");
        return sb.toString();
    }
    
    public String prepareDeleteStmt(String schemaName)
    {
        StringBuffer sb = new StringBuffer();
        sb.append("DELETE FROM ").append(schemaName);
        sb.append(".T061E1 ");
        sb.append(" WHERE COMPANY_CODE = ? AND TABLE_SUBSET = ? AND SEQ_NO = ?");
        return sb.toString();
    }
}
