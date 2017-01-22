package com.csc.fsg.life.pw.web.io.descriptor.wma;

import com.csc.fsg.life.pw.web.io.*;

public class T110XDescriptor
    extends TableDescriptor
{
    private static final String pagingSql = "SELECT COMPANY_CODE, EFFECTIVE_DATE, DURATION, TREASURY_RATE FROM ";
    
    public void initialize()
    {
        setRowClass(T110XRow.class);
        setTableName("T110X");
        setTableId("110");
        super.initialize();
    }
    
    public void initializeColumnDescriptors()
    {
        super.initializeColumnDescriptors();
        addColumnDescriptor(new ColumnDescriptor(this,"getCompanyCode","setCompanyCode","COMPANY_CODE,1,1,3,0,true|,0,CHAR,1,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getEffectiveDate","setEffectiveDate","EFFECTIVE_DATE,91,2,10,0,true|,0,DATE,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getDuration","setDuration","DURATION,3,3,3,0,true|,0,INTEGER,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getTreasuryRate","setTreasuryRate","TREASURY_RATE,3,4,4,2,false|,0,DOUBLE,0,null,null,null,null,null"));
    }
    
    public String getPagingSQL(String schemaName,boolean isSubsetMode,boolean isNext, boolean locator)
    {
        String pagingWhere = null;
        String order = null;
        if (isNext) {
            if (isSubsetMode)
                if (locator)
                    pagingWhere = ".T110X WHERE (COMPANY_CODE = :company_code) AND ((EFFECTIVE_DATE > :effective_date) OR (EFFECTIVE_DATE = :effective_date AND DURATION > :duration) OR (EFFECTIVE_DATE = :effective_date AND DURATION = :duration)) ";
                else 
                    pagingWhere = ".T110X WHERE (COMPANY_CODE = :company_code) AND ((EFFECTIVE_DATE > :effective_date) OR (EFFECTIVE_DATE = :effective_date AND DURATION > :duration)) ";
            else
                if (locator)
                    pagingWhere = ".T110X WHERE (COMPANY_CODE = :company_code) AND ((EFFECTIVE_DATE > :effective_date) OR (EFFECTIVE_DATE = :effective_date AND DURATION > :duration) OR (EFFECTIVE_DATE = :effective_date AND DURATION = :duration)) ";
                else
                    pagingWhere = ".T110X WHERE (COMPANY_CODE = :company_code) AND ((EFFECTIVE_DATE > :effective_date) OR (EFFECTIVE_DATE = :effective_date AND DURATION > :duration)) ";
            order = " ORDER BY COMPANY_CODE, EFFECTIVE_DATE, DURATION";
        }
        else {
            if (isSubsetMode)
                pagingWhere = ".T110X WHERE (COMPANY_CODE = :company_code) AND ((EFFECTIVE_DATE < :effective_date) OR (EFFECTIVE_DATE = :effective_date AND DURATION < :duration)) ";
            else
                pagingWhere = ".T110X WHERE (COMPANY_CODE = :company_code) AND ((EFFECTIVE_DATE < :effective_date) OR (EFFECTIVE_DATE = :effective_date AND DURATION < :duration)) ";
            order = " ORDER BY COMPANY_CODE DESC, EFFECTIVE_DATE DESC, DURATION DESC";
        }
        return pagingSql + schemaName + pagingWhere + order;
    }
    
    public String prepareInsertStmt(String schemaName) {
        StringBuffer sb = new StringBuffer();
        sb.append("INSERT INTO ").append(schemaName);
        sb.append(".T110X ( ");
        sb.append("COMPANY_CODE, EFFECTIVE_DATE, DURATION, TREASURY_RATE )");
        sb.append(" VALUES (?, ?, ?, ? )");
        return sb.toString();
    }
    
    public String prepareUpdateStmt(String schemaName)
    {
        StringBuffer sb = new StringBuffer();
        sb.append("UPDATE ").append(schemaName);
        sb.append(".T110X ");
        sb.append(" SET COMPANY_CODE = ?, EFFECTIVE_DATE = ?, DURATION = ?, TREASURY_RATE = ?");
        sb.append(" WHERE COMPANY_CODE = ? AND EFFECTIVE_DATE = ? AND DURATION = ?");
        return sb.toString();
    }
    
    public String prepareDeleteStmt(String schemaName)
    {
        StringBuffer sb = new StringBuffer();
        sb.append("DELETE FROM ").append(schemaName);
        sb.append(".T110X ");
        sb.append(" WHERE COMPANY_CODE = ? AND EFFECTIVE_DATE = ? AND DURATION = ?");
        return sb.toString();
    }
}
