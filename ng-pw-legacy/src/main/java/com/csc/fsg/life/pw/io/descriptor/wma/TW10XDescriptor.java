package com.csc.fsg.life.pw.io.descriptor.wma;

import com.csc.fsg.life.pw.io.*;

public class TW10XDescriptor
    extends TableDescriptor
{
    private static final String pagingSql = "SELECT COMPANY_CODE, TRANSACTION_CODE, PURCH_SERIES_CODE, EFFECTIVE_DATE, PYMT_MODE, MODAL_FACTOR FROM ";
    
    public void initialize()
    {
        setRowClass(TW10XRow.class);
        setTableName("TW10X");
        setTableId("W10");
        super.initialize();
    }
    
    public void initializeColumnDescriptors()
    {
        super.initializeColumnDescriptors();
        addColumnDescriptor(new ColumnDescriptor(this,"getCompanyCode","setCompanyCode","COMPANY_CODE,1,1,3,0,true|,0,CHAR,1,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getTransactionCode","setTransactionCode","TRANSACTION_CODE,1,2,4,0,true|,0,CHAR,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getPurchSeriesCode","setPurchSeriesCode","PURCH_SERIES_CODE,1,3,5,0,true|,0,CHAR,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getEffectiveDate","setEffectiveDate","EFFECTIVE_DATE,91,4,10,0,true|,0,DATE,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getPymtMode","setPymtMode","PYMT_MODE,1,5,1,0,true|,0,CHAR,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getModalFactor","setModalFactor","MODAL_FACTOR,3,6,4,2,false|,0,DOUBLE,0,null,null,null,null,null"));
    }
    
    public String getPagingSQL(String schemaName,boolean isSubsetMode,boolean isNext, boolean locator)
    {
        String pagingWhere = null;
        String order = null;
        if (isNext) {
            if (isSubsetMode)
                if (locator)
                    pagingWhere = ".TW10X WHERE (COMPANY_CODE = :company_code) AND ((TRANSACTION_CODE > :transaction_code) OR (TRANSACTION_CODE = :transaction_code AND PURCH_SERIES_CODE > :purch_series_code) OR (TRANSACTION_CODE = :transaction_code AND PURCH_SERIES_CODE = :purch_series_code AND EFFECTIVE_DATE > :effective_date) OR (TRANSACTION_CODE = :transaction_code AND PURCH_SERIES_CODE = :purch_series_code AND EFFECTIVE_DATE = :effective_date AND PYMT_MODE > :pymt_mode) OR (TRANSACTION_CODE = :transaction_code AND PURCH_SERIES_CODE = :purch_series_code AND EFFECTIVE_DATE = :effective_date AND PYMT_MODE = :pymt_mode)) ";
                else 
                    pagingWhere = ".TW10X WHERE (COMPANY_CODE = :company_code) AND ((TRANSACTION_CODE > :transaction_code) OR (TRANSACTION_CODE = :transaction_code AND PURCH_SERIES_CODE > :purch_series_code) OR (TRANSACTION_CODE = :transaction_code AND PURCH_SERIES_CODE = :purch_series_code AND EFFECTIVE_DATE > :effective_date) OR (TRANSACTION_CODE = :transaction_code AND PURCH_SERIES_CODE = :purch_series_code AND EFFECTIVE_DATE = :effective_date AND PYMT_MODE > :pymt_mode)) ";
            else
                if (locator)
                    pagingWhere = ".TW10X WHERE (COMPANY_CODE = :company_code) AND ((TRANSACTION_CODE > :transaction_code) OR (TRANSACTION_CODE = :transaction_code AND PURCH_SERIES_CODE > :purch_series_code) OR (TRANSACTION_CODE = :transaction_code AND PURCH_SERIES_CODE = :purch_series_code AND EFFECTIVE_DATE > :effective_date) OR (TRANSACTION_CODE = :transaction_code AND PURCH_SERIES_CODE = :purch_series_code AND EFFECTIVE_DATE = :effective_date AND PYMT_MODE > :pymt_mode) OR (TRANSACTION_CODE = :transaction_code AND PURCH_SERIES_CODE = :purch_series_code AND EFFECTIVE_DATE = :effective_date AND PYMT_MODE = :pymt_mode)) ";
                else
                    pagingWhere = ".TW10X WHERE (COMPANY_CODE = :company_code) AND ((TRANSACTION_CODE > :transaction_code) OR (TRANSACTION_CODE = :transaction_code AND PURCH_SERIES_CODE > :purch_series_code) OR (TRANSACTION_CODE = :transaction_code AND PURCH_SERIES_CODE = :purch_series_code AND EFFECTIVE_DATE > :effective_date) OR (TRANSACTION_CODE = :transaction_code AND PURCH_SERIES_CODE = :purch_series_code AND EFFECTIVE_DATE = :effective_date AND PYMT_MODE > :pymt_mode)) ";
            order = " ORDER BY COMPANY_CODE, TRANSACTION_CODE, PURCH_SERIES_CODE, EFFECTIVE_DATE, PYMT_MODE";
        }
        else {
            if (isSubsetMode)
                pagingWhere = ".TW10X WHERE (COMPANY_CODE = :company_code) AND ((TRANSACTION_CODE < :transaction_code) OR (TRANSACTION_CODE = :transaction_code AND PURCH_SERIES_CODE < :purch_series_code) OR (TRANSACTION_CODE = :transaction_code AND PURCH_SERIES_CODE = :purch_series_code AND EFFECTIVE_DATE < :effective_date) OR (TRANSACTION_CODE = :transaction_code AND PURCH_SERIES_CODE = :purch_series_code AND EFFECTIVE_DATE = :effective_date AND PYMT_MODE < :pymt_mode)) ";
            else
                pagingWhere = ".TW10X WHERE (COMPANY_CODE = :company_code) AND ((TRANSACTION_CODE < :transaction_code) OR (TRANSACTION_CODE = :transaction_code AND PURCH_SERIES_CODE < :purch_series_code) OR (TRANSACTION_CODE = :transaction_code AND PURCH_SERIES_CODE = :purch_series_code AND EFFECTIVE_DATE < :effective_date) OR (TRANSACTION_CODE = :transaction_code AND PURCH_SERIES_CODE = :purch_series_code AND EFFECTIVE_DATE = :effective_date AND PYMT_MODE < :pymt_mode)) ";
            order = " ORDER BY COMPANY_CODE DESC, TRANSACTION_CODE DESC, PURCH_SERIES_CODE DESC, EFFECTIVE_DATE DESC, PYMT_MODE DESC";
        }
        return pagingSql + schemaName + pagingWhere + order;
    }
    
    public String prepareInsertStmt(String schemaName) {
        StringBuffer sb = new StringBuffer();
        sb.append("INSERT INTO ").append(schemaName);
        sb.append(".TW10X ( ");
        sb.append("COMPANY_CODE, TRANSACTION_CODE, PURCH_SERIES_CODE, EFFECTIVE_DATE, PYMT_MODE, MODAL_FACTOR )");
        sb.append(" VALUES (?, ?, ?, ?, ?, ? )");
        return sb.toString();
    }
    
    public String prepareUpdateStmt(String schemaName)
    {
        StringBuffer sb = new StringBuffer();
        sb.append("UPDATE ").append(schemaName);
        sb.append(".TW10X ");
        sb.append(" SET COMPANY_CODE = ?, TRANSACTION_CODE = ?, PURCH_SERIES_CODE = ?, EFFECTIVE_DATE = ?, PYMT_MODE = ?, MODAL_FACTOR = ?");
        sb.append(" WHERE COMPANY_CODE = ? AND TRANSACTION_CODE = ? AND PURCH_SERIES_CODE = ? AND EFFECTIVE_DATE = ? AND PYMT_MODE = ?");
        return sb.toString();
    }
    
    public String prepareDeleteStmt(String schemaName)
    {
        StringBuffer sb = new StringBuffer();
        sb.append("DELETE FROM ").append(schemaName);
        sb.append(".TW10X ");
        sb.append(" WHERE COMPANY_CODE = ? AND TRANSACTION_CODE = ? AND PURCH_SERIES_CODE = ? AND EFFECTIVE_DATE = ? AND PYMT_MODE = ?");
        return sb.toString();
    }
}
