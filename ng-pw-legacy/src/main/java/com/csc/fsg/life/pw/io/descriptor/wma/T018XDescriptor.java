package com.csc.fsg.life.pw.io.descriptor.wma;

import com.csc.fsg.life.pw.io.*;

public class T018XDescriptor
    extends TableDescriptor
{
    private static final String pagingSql = "SELECT COMPANY_CODE, PRODUCT_PREFIX, PRODUCT_SUFFIX, LOB_CODE, BILLING_FREQUENCY, EXTRACT_DATE, BILLING_DATE, MESSAGE_CODE FROM ";
    
    public void initialize()
    {
        setRowClass(T018XRow.class);
        setTableName("T018X");
        setTableId("018");
        super.initialize();
    }
    
    public void initializeColumnDescriptors()
    {
        super.initializeColumnDescriptors();
        addColumnDescriptor(new ColumnDescriptor(this,"getCompanyCode","setCompanyCode","COMPANY_CODE,1,1,3,0,true|,0,CHAR,1,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getProductPrefix","setProductPrefix","PRODUCT_PREFIX,1,2,1,0,true|,0,CHAR,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getProductSuffix","setProductSuffix","PRODUCT_SUFFIX,1,3,1,0,true|,0,CHAR,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getLobCode","setLobCode","LOB_CODE,1,4,3,0,true|,0,CHAR,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getBillingFrequency","setBillingFrequency","BILLING_FREQUENCY,1,5,2,0,true|,0,CHAR,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getExtractDate","setExtractDate","EXTRACT_DATE,91,6,10,0,true|,0,DATE,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getBillingDate","setBillingDate","BILLING_DATE,91,7,10,0,false|,0,DATE,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getMessageCode","setMessageCode","MESSAGE_CODE,3,8,2,0,false|,0,INTEGER,0,null,null,null,null,null"));
    }
    
    public String getPagingSQL(String schemaName,boolean isSubsetMode,boolean isNext, boolean locator)
    {
        String pagingWhere = null;
        String order = null;
        if (isNext) {
            if (isSubsetMode)
                if (locator)
                    pagingWhere = ".T018X WHERE (COMPANY_CODE = :company_code) AND (PRODUCT_PREFIX = :product_prefix) AND ((PRODUCT_SUFFIX > :product_suffix) OR (PRODUCT_SUFFIX = :product_suffix AND LOB_CODE > :lob_code) OR (PRODUCT_SUFFIX = :product_suffix AND LOB_CODE = :lob_code AND BILLING_FREQUENCY > :billing_frequency) OR (PRODUCT_SUFFIX = :product_suffix AND LOB_CODE = :lob_code AND BILLING_FREQUENCY = :billing_frequency AND EXTRACT_DATE > :extract_date) OR (PRODUCT_SUFFIX = :product_suffix AND LOB_CODE = :lob_code AND BILLING_FREQUENCY = :billing_frequency AND EXTRACT_DATE = :extract_date)) ";
                else 
                    pagingWhere = ".T018X WHERE (COMPANY_CODE = :company_code) AND (PRODUCT_PREFIX = :product_prefix) AND ((PRODUCT_SUFFIX > :product_suffix) OR (PRODUCT_SUFFIX = :product_suffix AND LOB_CODE > :lob_code) OR (PRODUCT_SUFFIX = :product_suffix AND LOB_CODE = :lob_code AND BILLING_FREQUENCY > :billing_frequency) OR (PRODUCT_SUFFIX = :product_suffix AND LOB_CODE = :lob_code AND BILLING_FREQUENCY = :billing_frequency AND EXTRACT_DATE > :extract_date)) ";
            else
                if (locator)
                    pagingWhere = ".T018X WHERE (COMPANY_CODE = :company_code) AND ((PRODUCT_PREFIX > :product_prefix) OR (PRODUCT_PREFIX = :product_prefix AND PRODUCT_SUFFIX > :product_suffix) OR (PRODUCT_PREFIX = :product_prefix AND PRODUCT_SUFFIX = :product_suffix AND LOB_CODE > :lob_code) OR (PRODUCT_PREFIX = :product_prefix AND PRODUCT_SUFFIX = :product_suffix AND LOB_CODE = :lob_code AND BILLING_FREQUENCY > :billing_frequency) OR (PRODUCT_PREFIX = :product_prefix AND PRODUCT_SUFFIX = :product_suffix AND LOB_CODE = :lob_code AND BILLING_FREQUENCY = :billing_frequency AND EXTRACT_DATE > :extract_date) OR (PRODUCT_PREFIX = :product_prefix AND PRODUCT_SUFFIX = :product_suffix AND LOB_CODE = :lob_code AND BILLING_FREQUENCY = :billing_frequency AND EXTRACT_DATE = :extract_date)) ";
                else
                    pagingWhere = ".T018X WHERE (COMPANY_CODE = :company_code) AND ((PRODUCT_PREFIX > :product_prefix) OR (PRODUCT_PREFIX = :product_prefix AND PRODUCT_SUFFIX > :product_suffix) OR (PRODUCT_PREFIX = :product_prefix AND PRODUCT_SUFFIX = :product_suffix AND LOB_CODE > :lob_code) OR (PRODUCT_PREFIX = :product_prefix AND PRODUCT_SUFFIX = :product_suffix AND LOB_CODE = :lob_code AND BILLING_FREQUENCY > :billing_frequency) OR (PRODUCT_PREFIX = :product_prefix AND PRODUCT_SUFFIX = :product_suffix AND LOB_CODE = :lob_code AND BILLING_FREQUENCY = :billing_frequency AND EXTRACT_DATE > :extract_date)) ";
            order = " ORDER BY COMPANY_CODE, PRODUCT_PREFIX, PRODUCT_SUFFIX, LOB_CODE, BILLING_FREQUENCY, EXTRACT_DATE";
        }
        else {
            if (isSubsetMode)
                pagingWhere = ".T018X WHERE (COMPANY_CODE = :company_code) AND (PRODUCT_PREFIX = :product_prefix) AND ((PRODUCT_SUFFIX < :product_suffix) OR (PRODUCT_SUFFIX = :product_suffix AND LOB_CODE < :lob_code) OR (PRODUCT_SUFFIX = :product_suffix AND LOB_CODE = :lob_code AND BILLING_FREQUENCY < :billing_frequency) OR (PRODUCT_SUFFIX = :product_suffix AND LOB_CODE = :lob_code AND BILLING_FREQUENCY = :billing_frequency AND EXTRACT_DATE < :extract_date)) ";
            else
                pagingWhere = ".T018X WHERE (COMPANY_CODE = :company_code) AND ((PRODUCT_PREFIX < :product_prefix) OR (PRODUCT_PREFIX = :product_prefix AND PRODUCT_SUFFIX < :product_suffix) OR (PRODUCT_PREFIX = :product_prefix AND PRODUCT_SUFFIX = :product_suffix AND LOB_CODE < :lob_code) OR (PRODUCT_PREFIX = :product_prefix AND PRODUCT_SUFFIX = :product_suffix AND LOB_CODE = :lob_code AND BILLING_FREQUENCY < :billing_frequency) OR (PRODUCT_PREFIX = :product_prefix AND PRODUCT_SUFFIX = :product_suffix AND LOB_CODE = :lob_code AND BILLING_FREQUENCY = :billing_frequency AND EXTRACT_DATE < :extract_date)) ";
            order = " ORDER BY COMPANY_CODE DESC, PRODUCT_PREFIX DESC, PRODUCT_SUFFIX DESC, LOB_CODE DESC, BILLING_FREQUENCY DESC, EXTRACT_DATE DESC";
        }
        return pagingSql + schemaName + pagingWhere + order;
    }
    
    public String prepareInsertStmt(String schemaName) {
        StringBuffer sb = new StringBuffer();
        sb.append("INSERT INTO ").append(schemaName);
        sb.append(".T018X ( ");
        sb.append("COMPANY_CODE, PRODUCT_PREFIX, PRODUCT_SUFFIX, LOB_CODE, BILLING_FREQUENCY, EXTRACT_DATE, BILLING_DATE, MESSAGE_CODE )");
        sb.append(" VALUES (?, ?, ?, ?, ?, ?, ?, ? )");
        return sb.toString();
    }
    
    public String prepareUpdateStmt(String schemaName)
    {
        StringBuffer sb = new StringBuffer();
        sb.append("UPDATE ").append(schemaName);
        sb.append(".T018X ");
        sb.append(" SET COMPANY_CODE = ?, PRODUCT_PREFIX = ?, PRODUCT_SUFFIX = ?, LOB_CODE = ?, BILLING_FREQUENCY = ?, EXTRACT_DATE = ?, BILLING_DATE = ?, MESSAGE_CODE = ?");
        sb.append(" WHERE COMPANY_CODE = ? AND PRODUCT_PREFIX = ? AND PRODUCT_SUFFIX = ? AND LOB_CODE = ? AND BILLING_FREQUENCY = ? AND EXTRACT_DATE = ?");
        return sb.toString();
    }
    
    public String prepareDeleteStmt(String schemaName)
    {
        StringBuffer sb = new StringBuffer();
        sb.append("DELETE FROM ").append(schemaName);
        sb.append(".T018X ");
        sb.append(" WHERE COMPANY_CODE = ? AND PRODUCT_PREFIX = ? AND PRODUCT_SUFFIX = ? AND LOB_CODE = ? AND BILLING_FREQUENCY = ? AND EXTRACT_DATE = ?");
        return sb.toString();
    }
}
