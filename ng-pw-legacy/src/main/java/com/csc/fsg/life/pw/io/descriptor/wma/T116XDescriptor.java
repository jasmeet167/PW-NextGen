package com.csc.fsg.life.pw.io.descriptor.wma;

import com.csc.fsg.life.pw.io.*;

public class T116XDescriptor
    extends TableDescriptor
{
    private static final String pagingSql = "SELECT COMPANY_CODE, PRODUCT_CODE, EFFECTIVE_DATE, TRX_CODE, TRX_SOURCE, END_OF_DAY FROM ";
    
    public void initialize()
    {
        setRowClass(T116XRow.class);
        setTableName("T116X");
        setTableId("116");
        super.initialize();
    }
    
    public void initializeColumnDescriptors()
    {
        super.initializeColumnDescriptors();
        addColumnDescriptor(new ColumnDescriptor(this,"getCompanyCode","setCompanyCode","COMPANY_CODE,1,1,3,0,true|,0,CHAR,1,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getProductCode","setProductCode","PRODUCT_CODE,1,2,2,0,true|,0,CHAR,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getEffectiveDate","setEffectiveDate","EFFECTIVE_DATE,91,3,10,0,true|,0,DATE,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getTrxCode","setTrxCode","TRX_CODE,1,4,4,0,true|,0,CHAR,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getTrxSource","setTrxSource","TRX_SOURCE,1,5,30,0,true|,0,CHAR,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getEndOfDay","setEndOfDay","END_OF_DAY,3,6,6,0,false|,0,INTEGER,0,null,null,null,null,null"));
    }
    
    public String getPagingSQL(String schemaName,boolean isSubsetMode,boolean isNext, boolean locator)
    {
        String pagingWhere = null;
        String order = null;
        if (isNext) {
            if (isSubsetMode)
                if (locator)
                    pagingWhere = ".T116X WHERE (COMPANY_CODE = :company_code) AND ((PRODUCT_CODE > :product_code) OR (PRODUCT_CODE = :product_code AND EFFECTIVE_DATE > :effective_date) OR (PRODUCT_CODE = :product_code AND EFFECTIVE_DATE = :effective_date AND TRX_CODE > :trx_code) OR (PRODUCT_CODE = :product_code AND EFFECTIVE_DATE = :effective_date AND TRX_CODE = :trx_code AND TRX_SOURCE > :trx_source) OR (PRODUCT_CODE = :product_code AND EFFECTIVE_DATE = :effective_date AND TRX_CODE = :trx_code AND TRX_SOURCE = :trx_source)) ";
                else 
                    pagingWhere = ".T116X WHERE (COMPANY_CODE = :company_code) AND ((PRODUCT_CODE > :product_code) OR (PRODUCT_CODE = :product_code AND EFFECTIVE_DATE > :effective_date) OR (PRODUCT_CODE = :product_code AND EFFECTIVE_DATE = :effective_date AND TRX_CODE > :trx_code) OR (PRODUCT_CODE = :product_code AND EFFECTIVE_DATE = :effective_date AND TRX_CODE = :trx_code AND TRX_SOURCE > :trx_source)) ";
            else
                if (locator)
                    pagingWhere = ".T116X WHERE (COMPANY_CODE = :company_code) AND ((PRODUCT_CODE > :product_code) OR (PRODUCT_CODE = :product_code AND EFFECTIVE_DATE > :effective_date) OR (PRODUCT_CODE = :product_code AND EFFECTIVE_DATE = :effective_date AND TRX_CODE > :trx_code) OR (PRODUCT_CODE = :product_code AND EFFECTIVE_DATE = :effective_date AND TRX_CODE = :trx_code AND TRX_SOURCE > :trx_source) OR (PRODUCT_CODE = :product_code AND EFFECTIVE_DATE = :effective_date AND TRX_CODE = :trx_code AND TRX_SOURCE = :trx_source)) ";
                else
                    pagingWhere = ".T116X WHERE (COMPANY_CODE = :company_code) AND ((PRODUCT_CODE > :product_code) OR (PRODUCT_CODE = :product_code AND EFFECTIVE_DATE > :effective_date) OR (PRODUCT_CODE = :product_code AND EFFECTIVE_DATE = :effective_date AND TRX_CODE > :trx_code) OR (PRODUCT_CODE = :product_code AND EFFECTIVE_DATE = :effective_date AND TRX_CODE = :trx_code AND TRX_SOURCE > :trx_source)) ";
            order = " ORDER BY COMPANY_CODE, PRODUCT_CODE, EFFECTIVE_DATE, TRX_CODE, TRX_SOURCE";
        }
        else {
            if (isSubsetMode)
                pagingWhere = ".T116X WHERE (COMPANY_CODE = :company_code) AND ((PRODUCT_CODE < :product_code) OR (PRODUCT_CODE = :product_code AND EFFECTIVE_DATE < :effective_date) OR (PRODUCT_CODE = :product_code AND EFFECTIVE_DATE = :effective_date AND TRX_CODE < :trx_code) OR (PRODUCT_CODE = :product_code AND EFFECTIVE_DATE = :effective_date AND TRX_CODE = :trx_code AND TRX_SOURCE < :trx_source)) ";
            else
                pagingWhere = ".T116X WHERE (COMPANY_CODE = :company_code) AND ((PRODUCT_CODE < :product_code) OR (PRODUCT_CODE = :product_code AND EFFECTIVE_DATE < :effective_date) OR (PRODUCT_CODE = :product_code AND EFFECTIVE_DATE = :effective_date AND TRX_CODE < :trx_code) OR (PRODUCT_CODE = :product_code AND EFFECTIVE_DATE = :effective_date AND TRX_CODE = :trx_code AND TRX_SOURCE < :trx_source)) ";
            order = " ORDER BY COMPANY_CODE DESC, PRODUCT_CODE DESC, EFFECTIVE_DATE DESC, TRX_CODE DESC, TRX_SOURCE DESC";
        }
        return pagingSql + schemaName + pagingWhere + order;
    }
    
    public String prepareInsertStmt(String schemaName) {
        StringBuffer sb = new StringBuffer();
        sb.append("INSERT INTO ").append(schemaName);
        sb.append(".T116X ( ");
        sb.append("COMPANY_CODE, PRODUCT_CODE, EFFECTIVE_DATE, TRX_CODE, TRX_SOURCE, END_OF_DAY )");
        sb.append(" VALUES (?, ?, ?, ?, ?, ? )");
        return sb.toString();
    }
    
    public String prepareUpdateStmt(String schemaName)
    {
        StringBuffer sb = new StringBuffer();
        sb.append("UPDATE ").append(schemaName);
        sb.append(".T116X ");
        sb.append(" SET COMPANY_CODE = ?, PRODUCT_CODE = ?, EFFECTIVE_DATE = ?, TRX_CODE = ?, TRX_SOURCE = ?, END_OF_DAY = ?");
        sb.append(" WHERE COMPANY_CODE = ? AND PRODUCT_CODE = ? AND EFFECTIVE_DATE = ? AND TRX_CODE = ? AND TRX_SOURCE = ?");
        return sb.toString();
    }
    
    public String prepareDeleteStmt(String schemaName)
    {
        StringBuffer sb = new StringBuffer();
        sb.append("DELETE FROM ").append(schemaName);
        sb.append(".T116X ");
        sb.append(" WHERE COMPANY_CODE = ? AND PRODUCT_CODE = ? AND EFFECTIVE_DATE = ? AND TRX_CODE = ? AND TRX_SOURCE = ?");
        return sb.toString();
    }
}
