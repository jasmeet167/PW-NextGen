package com.csc.fsg.life.pw.io.descriptor.wma;

import com.csc.fsg.life.pw.io.*;

public class T109XDescriptor
    extends TableDescriptor
{
    private static final String pagingSql = "SELECT COMPANY_CODE, STATUTORY_CODE, PRODUCT_PREFIX, PRODUCT_SUFFIX, TRANSACTION_CODE, REVERSAL_IND, REASON_CODE, REASON_DESCRIPTION FROM ";
    
    public void initialize()
    {
        setRowClass(T109XRow.class);
        setTableName("T109X");
        setTableId("109");
        super.initialize();
    }
    
    public void initializeColumnDescriptors()
    {
        super.initializeColumnDescriptors();
        addColumnDescriptor(new ColumnDescriptor(this,"getCompanyCode","setCompanyCode","COMPANY_CODE,1,1,3,0,true|,0,CHAR,1,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getStatutoryCode","setStatutoryCode","STATUTORY_CODE,1,2,3,0,true|,0,CHAR,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getProductPrefix","setProductPrefix","PRODUCT_PREFIX,1,3,1,0,true|,0,CHAR,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getProductSuffix","setProductSuffix","PRODUCT_SUFFIX,1,4,1,0,true|,0,CHAR,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getTransactionCode","setTransactionCode","TRANSACTION_CODE,1,5,4,0,true|,0,CHAR,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getReversalInd","setReversalInd","REVERSAL_IND,1,6,1,0,true|,0,CHAR,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getReasonCode","setReasonCode","REASON_CODE,1,7,4,0,true|,0,CHAR,4,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getReasonDescription","setReasonDescription","REASON_DESCRIPTION,1,8,30,0,false|,0,CHAR,4,null,null,null,null,null"));
    }
    
    public String getPagingSQL(String schemaName,boolean isSubsetMode,boolean isNext, boolean locator)
    {
        String pagingWhere = null;
        String order = null;
        if (isNext) {
            if (isSubsetMode)
                if (locator)
                    pagingWhere = ".T109X WHERE (COMPANY_CODE = :company_code) AND (PRODUCT_PREFIX = :product_prefix) AND ((STATUTORY_CODE > :statutory_code) OR (STATUTORY_CODE = :statutory_code AND PRODUCT_SUFFIX > :product_suffix) OR (STATUTORY_CODE = :statutory_code AND PRODUCT_SUFFIX = :product_suffix AND TRANSACTION_CODE > :transaction_code) OR (STATUTORY_CODE = :statutory_code AND PRODUCT_SUFFIX = :product_suffix AND TRANSACTION_CODE = :transaction_code AND REVERSAL_IND > :reversal_ind) OR (STATUTORY_CODE = :statutory_code AND PRODUCT_SUFFIX = :product_suffix AND TRANSACTION_CODE = :transaction_code AND REVERSAL_IND = :reversal_ind AND REASON_CODE > :reason_code) OR (STATUTORY_CODE = :statutory_code AND PRODUCT_SUFFIX = :product_suffix AND TRANSACTION_CODE = :transaction_code AND REVERSAL_IND = :reversal_ind AND REASON_CODE = :reason_code)) ";
                else 
                    pagingWhere = ".T109X WHERE (COMPANY_CODE = :company_code) AND (PRODUCT_PREFIX = :product_prefix) AND ((STATUTORY_CODE > :statutory_code) OR (STATUTORY_CODE = :statutory_code AND PRODUCT_SUFFIX > :product_suffix) OR (STATUTORY_CODE = :statutory_code AND PRODUCT_SUFFIX = :product_suffix AND TRANSACTION_CODE > :transaction_code) OR (STATUTORY_CODE = :statutory_code AND PRODUCT_SUFFIX = :product_suffix AND TRANSACTION_CODE = :transaction_code AND REVERSAL_IND > :reversal_ind) OR (STATUTORY_CODE = :statutory_code AND PRODUCT_SUFFIX = :product_suffix AND TRANSACTION_CODE = :transaction_code AND REVERSAL_IND = :reversal_ind AND REASON_CODE > :reason_code)) ";
            else
                if (locator)
                    pagingWhere = ".T109X WHERE (COMPANY_CODE = :company_code) AND ((STATUTORY_CODE > :statutory_code) OR (STATUTORY_CODE = :statutory_code AND PRODUCT_PREFIX > :product_prefix) OR (STATUTORY_CODE = :statutory_code AND PRODUCT_PREFIX = :product_prefix AND PRODUCT_SUFFIX > :product_suffix) OR (STATUTORY_CODE = :statutory_code AND PRODUCT_PREFIX = :product_prefix AND PRODUCT_SUFFIX = :product_suffix AND TRANSACTION_CODE > :transaction_code) OR (STATUTORY_CODE = :statutory_code AND PRODUCT_PREFIX = :product_prefix AND PRODUCT_SUFFIX = :product_suffix AND TRANSACTION_CODE = :transaction_code AND REVERSAL_IND > :reversal_ind) OR (STATUTORY_CODE = :statutory_code AND PRODUCT_PREFIX = :product_prefix AND PRODUCT_SUFFIX = :product_suffix AND TRANSACTION_CODE = :transaction_code AND REVERSAL_IND = :reversal_ind AND REASON_CODE > :reason_code) OR (STATUTORY_CODE = :statutory_code AND PRODUCT_PREFIX = :product_prefix AND PRODUCT_SUFFIX = :product_suffix AND TRANSACTION_CODE = :transaction_code AND REVERSAL_IND = :reversal_ind AND REASON_CODE = :reason_code)) ";
                else
                    pagingWhere = ".T109X WHERE (COMPANY_CODE = :company_code) AND ((STATUTORY_CODE > :statutory_code) OR (STATUTORY_CODE = :statutory_code AND PRODUCT_PREFIX > :product_prefix) OR (STATUTORY_CODE = :statutory_code AND PRODUCT_PREFIX = :product_prefix AND PRODUCT_SUFFIX > :product_suffix) OR (STATUTORY_CODE = :statutory_code AND PRODUCT_PREFIX = :product_prefix AND PRODUCT_SUFFIX = :product_suffix AND TRANSACTION_CODE > :transaction_code) OR (STATUTORY_CODE = :statutory_code AND PRODUCT_PREFIX = :product_prefix AND PRODUCT_SUFFIX = :product_suffix AND TRANSACTION_CODE = :transaction_code AND REVERSAL_IND > :reversal_ind) OR (STATUTORY_CODE = :statutory_code AND PRODUCT_PREFIX = :product_prefix AND PRODUCT_SUFFIX = :product_suffix AND TRANSACTION_CODE = :transaction_code AND REVERSAL_IND = :reversal_ind AND REASON_CODE > :reason_code)) ";
            order = " ORDER BY COMPANY_CODE, STATUTORY_CODE, PRODUCT_PREFIX, PRODUCT_SUFFIX, TRANSACTION_CODE, REVERSAL_IND, REASON_CODE";
        }
        else {
            if (isSubsetMode)
                pagingWhere = ".T109X WHERE (COMPANY_CODE = :company_code) AND (PRODUCT_PREFIX = :product_prefix) AND ((STATUTORY_CODE < :statutory_code) OR (STATUTORY_CODE = :statutory_code AND PRODUCT_SUFFIX < :product_suffix) OR (STATUTORY_CODE = :statutory_code AND PRODUCT_SUFFIX = :product_suffix AND TRANSACTION_CODE < :transaction_code) OR (STATUTORY_CODE = :statutory_code AND PRODUCT_SUFFIX = :product_suffix AND TRANSACTION_CODE = :transaction_code AND REVERSAL_IND < :reversal_ind) OR (STATUTORY_CODE = :statutory_code AND PRODUCT_SUFFIX = :product_suffix AND TRANSACTION_CODE = :transaction_code AND REVERSAL_IND = :reversal_ind AND REASON_CODE < :reason_code)) ";
            else
                pagingWhere = ".T109X WHERE (COMPANY_CODE = :company_code) AND ((STATUTORY_CODE < :statutory_code) OR (STATUTORY_CODE = :statutory_code AND PRODUCT_PREFIX < :product_prefix) OR (STATUTORY_CODE = :statutory_code AND PRODUCT_PREFIX = :product_prefix AND PRODUCT_SUFFIX < :product_suffix) OR (STATUTORY_CODE = :statutory_code AND PRODUCT_PREFIX = :product_prefix AND PRODUCT_SUFFIX = :product_suffix AND TRANSACTION_CODE < :transaction_code) OR (STATUTORY_CODE = :statutory_code AND PRODUCT_PREFIX = :product_prefix AND PRODUCT_SUFFIX = :product_suffix AND TRANSACTION_CODE = :transaction_code AND REVERSAL_IND < :reversal_ind) OR (STATUTORY_CODE = :statutory_code AND PRODUCT_PREFIX = :product_prefix AND PRODUCT_SUFFIX = :product_suffix AND TRANSACTION_CODE = :transaction_code AND REVERSAL_IND = :reversal_ind AND REASON_CODE < :reason_code)) ";
            order = " ORDER BY COMPANY_CODE DESC, STATUTORY_CODE DESC, PRODUCT_PREFIX DESC, PRODUCT_SUFFIX DESC, TRANSACTION_CODE DESC, REVERSAL_IND DESC, REASON_CODE DESC";
        }
        return pagingSql + schemaName + pagingWhere + order;
    }
    
    public String prepareInsertStmt(String schemaName) {
        StringBuffer sb = new StringBuffer();
        sb.append("INSERT INTO ").append(schemaName);
        sb.append(".T109X ( ");
        sb.append("COMPANY_CODE, STATUTORY_CODE, PRODUCT_PREFIX, PRODUCT_SUFFIX, TRANSACTION_CODE, REVERSAL_IND, REASON_CODE, REASON_DESCRIPTION )");
        sb.append(" VALUES (?, ?, ?, ?, ?, ?, ?, ? )");
        return sb.toString();
    }
    
    public String prepareUpdateStmt(String schemaName)
    {
        StringBuffer sb = new StringBuffer();
        sb.append("UPDATE ").append(schemaName);
        sb.append(".T109X ");
        sb.append(" SET COMPANY_CODE = ?, STATUTORY_CODE = ?, PRODUCT_PREFIX = ?, PRODUCT_SUFFIX = ?, TRANSACTION_CODE = ?, REVERSAL_IND = ?, REASON_CODE = ?, REASON_DESCRIPTION = ?");
        sb.append(" WHERE COMPANY_CODE = ? AND STATUTORY_CODE = ? AND PRODUCT_PREFIX = ? AND PRODUCT_SUFFIX = ? AND TRANSACTION_CODE = ? AND REVERSAL_IND = ? AND REASON_CODE = ?");
        return sb.toString();
    }
    
    public String prepareDeleteStmt(String schemaName)
    {
        StringBuffer sb = new StringBuffer();
        sb.append("DELETE FROM ").append(schemaName);
        sb.append(".T109X ");
        sb.append(" WHERE COMPANY_CODE = ? AND STATUTORY_CODE = ? AND PRODUCT_PREFIX = ? AND PRODUCT_SUFFIX = ? AND TRANSACTION_CODE = ? AND REVERSAL_IND = ? AND REASON_CODE = ?");
        return sb.toString();
    }
}
