package com.csc.fsg.life.pw.io.descriptor.wma;

import com.csc.fsg.life.pw.io.*;

public class TW64XDescriptor
    extends TableDescriptor
{
    private static final String pagingSql = "SELECT COMPANY_CODE, PRODUCT_PREFIX, TABLE_SUBSET, TRANSACTION_CODE, EFFECTIVE_DATE, MODEL_CODE, MODEL_NAME, ALLOC_MODEL_TBL FROM ";
    
    public void initialize()
    {
        setRowClass(TW64XRow.class);
        setTableName("TW64X");
        setTableId("W64");
        super.initialize();
    }
    
    public void initializeColumnDescriptors()
    {
        super.initializeColumnDescriptors();
        addColumnDescriptor(new ColumnDescriptor(this,"getCompanyCode","setCompanyCode","COMPANY_CODE,1,1,3,0,true|A,0,CHAR,1,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getProductPrefix","setProductPrefix","PRODUCT_PREFIX,1,2,1,0,true|A,0,CHAR,1,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getTableSubset","setTableSubset","TABLE_SUBSET,1,3,16,0,true|A,0,CHAR,1,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getTransactionCode","setTransactionCode","TRANSACTION_CODE,1,4,4,0,true|A,0,CHAR,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getEffectiveDate","setEffectiveDate","EFFECTIVE_DATE,91,5,10,0,true|A,0,DATE,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getModelCode","setModelCode","MODEL_CODE,1,6,6,0,true|A,0,CHAR,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getModelName","setModelName","MODEL_NAME,1,7,20,0,false|A,0,CHAR,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getAllocModelTbl","setAllocModelTbl","ALLOC_MODEL_TBL,1,8,16,0,false|A,0,CHAR,0,W66,null,null,null,null"));
    }
    
    public String getPagingSQL(String schemaName,boolean isSubsetMode,boolean isNext, boolean locator)
    {
        String pagingWhere = null;
        String order = null;
        if (isNext) {
            if (isSubsetMode)
                if (locator)
                    pagingWhere = ".TW64X WHERE (COMPANY_CODE = :company_code) AND (PRODUCT_PREFIX = :product_prefix) AND (TABLE_SUBSET = :table_subset) AND ((TRANSACTION_CODE > :transaction_code) OR (TRANSACTION_CODE = :transaction_code AND EFFECTIVE_DATE > :effective_date) OR (TRANSACTION_CODE = :transaction_code AND EFFECTIVE_DATE = :effective_date AND MODEL_CODE > :model_code) OR (TRANSACTION_CODE = :transaction_code AND EFFECTIVE_DATE = :effective_date AND MODEL_CODE = :model_code)) ";
                else 
                    pagingWhere = ".TW64X WHERE (COMPANY_CODE = :company_code) AND (PRODUCT_PREFIX = :product_prefix) AND (TABLE_SUBSET = :table_subset) AND ((TRANSACTION_CODE > :transaction_code) OR (TRANSACTION_CODE = :transaction_code AND EFFECTIVE_DATE > :effective_date) OR (TRANSACTION_CODE = :transaction_code AND EFFECTIVE_DATE = :effective_date AND MODEL_CODE > :model_code)) ";
            else
                if (locator)
                    pagingWhere = ".TW64X WHERE (COMPANY_CODE = :company_code) AND ((PRODUCT_PREFIX > :product_prefix) OR (PRODUCT_PREFIX = :product_prefix AND TABLE_SUBSET > :table_subset) OR (PRODUCT_PREFIX = :product_prefix AND TABLE_SUBSET = :table_subset AND TRANSACTION_CODE > :transaction_code) OR (PRODUCT_PREFIX = :product_prefix AND TABLE_SUBSET = :table_subset AND TRANSACTION_CODE = :transaction_code AND EFFECTIVE_DATE > :effective_date) OR (PRODUCT_PREFIX = :product_prefix AND TABLE_SUBSET = :table_subset AND TRANSACTION_CODE = :transaction_code AND EFFECTIVE_DATE = :effective_date AND MODEL_CODE > :model_code) OR (PRODUCT_PREFIX = :product_prefix AND TABLE_SUBSET = :table_subset AND TRANSACTION_CODE = :transaction_code AND EFFECTIVE_DATE = :effective_date AND MODEL_CODE = :model_code)) ";
                else
                    pagingWhere = ".TW64X WHERE (COMPANY_CODE = :company_code) AND ((PRODUCT_PREFIX > :product_prefix) OR (PRODUCT_PREFIX = :product_prefix AND TABLE_SUBSET > :table_subset) OR (PRODUCT_PREFIX = :product_prefix AND TABLE_SUBSET = :table_subset AND TRANSACTION_CODE > :transaction_code) OR (PRODUCT_PREFIX = :product_prefix AND TABLE_SUBSET = :table_subset AND TRANSACTION_CODE = :transaction_code AND EFFECTIVE_DATE > :effective_date) OR (PRODUCT_PREFIX = :product_prefix AND TABLE_SUBSET = :table_subset AND TRANSACTION_CODE = :transaction_code AND EFFECTIVE_DATE = :effective_date AND MODEL_CODE > :model_code)) ";
            order = " ORDER BY COMPANY_CODE, PRODUCT_PREFIX, TABLE_SUBSET, TRANSACTION_CODE, EFFECTIVE_DATE, MODEL_CODE";
        }
        else {
            if (isSubsetMode)
                pagingWhere = ".TW64X WHERE (COMPANY_CODE = :company_code) AND (PRODUCT_PREFIX = :product_prefix) AND (TABLE_SUBSET = :table_subset) AND ((TRANSACTION_CODE < :transaction_code) OR (TRANSACTION_CODE = :transaction_code AND EFFECTIVE_DATE < :effective_date) OR (TRANSACTION_CODE = :transaction_code AND EFFECTIVE_DATE = :effective_date AND MODEL_CODE < :model_code)) ";
            else
                pagingWhere = ".TW64X WHERE (COMPANY_CODE = :company_code) AND ((PRODUCT_PREFIX < :product_prefix) OR (PRODUCT_PREFIX = :product_prefix AND TABLE_SUBSET < :table_subset) OR (PRODUCT_PREFIX = :product_prefix AND TABLE_SUBSET = :table_subset AND TRANSACTION_CODE < :transaction_code) OR (PRODUCT_PREFIX = :product_prefix AND TABLE_SUBSET = :table_subset AND TRANSACTION_CODE = :transaction_code AND EFFECTIVE_DATE < :effective_date) OR (PRODUCT_PREFIX = :product_prefix AND TABLE_SUBSET = :table_subset AND TRANSACTION_CODE = :transaction_code AND EFFECTIVE_DATE = :effective_date AND MODEL_CODE < :model_code)) ";
            order = " ORDER BY COMPANY_CODE DESC, PRODUCT_PREFIX DESC, TABLE_SUBSET DESC, TRANSACTION_CODE DESC, EFFECTIVE_DATE DESC, MODEL_CODE DESC";
        }
        return pagingSql + schemaName + pagingWhere + order;
    }
    
    public String prepareInsertStmt(String schemaName) {
        StringBuffer sb = new StringBuffer();
        sb.append("INSERT INTO ").append(schemaName);
        sb.append(".TW64X ( ");
        sb.append("COMPANY_CODE, PRODUCT_PREFIX, TABLE_SUBSET, TRANSACTION_CODE, EFFECTIVE_DATE, MODEL_CODE, MODEL_NAME, ALLOC_MODEL_TBL )");
        sb.append(" VALUES (?, ?, ?, ?, ?, ?, ?, ? )");
        return sb.toString();
    }
    
    public String prepareUpdateStmt(String schemaName)
    {
        StringBuffer sb = new StringBuffer();
        sb.append("UPDATE ").append(schemaName);
        sb.append(".TW64X ");
        sb.append(" SET COMPANY_CODE = ?, PRODUCT_PREFIX = ?, TABLE_SUBSET = ?, TRANSACTION_CODE = ?, EFFECTIVE_DATE = ?, MODEL_CODE = ?, MODEL_NAME = ?, ALLOC_MODEL_TBL = ?");
        sb.append(" WHERE COMPANY_CODE = ? AND PRODUCT_PREFIX = ? AND TABLE_SUBSET = ? AND TRANSACTION_CODE = ? AND EFFECTIVE_DATE = ? AND MODEL_CODE = ?");
        return sb.toString();
    }
    
    public String prepareDeleteStmt(String schemaName)
    {
        StringBuffer sb = new StringBuffer();
        sb.append("DELETE FROM ").append(schemaName);
        sb.append(".TW64X ");
        sb.append(" WHERE COMPANY_CODE = ? AND PRODUCT_PREFIX = ? AND TABLE_SUBSET = ? AND TRANSACTION_CODE = ? AND EFFECTIVE_DATE = ? AND MODEL_CODE = ?");
        return sb.toString();
    }
}
