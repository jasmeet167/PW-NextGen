package com.csc.fsg.life.pw.io.descriptor.wma;

import com.csc.fsg.life.pw.io.*;

public class T120XDescriptor
    extends TableDescriptor
{
    private static final String pagingSql = "SELECT COMPANY_CODE, PRODUCT_PREFIX, TABLE_SUBSET, EFFECTIVE_DATE, CHILD_MAX_ISS_AGE, CHILD_EXP_AGE, CHILD_EXP_NTC_DAYS, CHILD_EXP_DT_RULE FROM ";
    
    public void initialize()
    {
        setRowClass(T120XRow.class);
        setTableName("T120X");
        setTableId("120");
        super.initialize();
    }
    
    public void initializeColumnDescriptors()
    {
        super.initializeColumnDescriptors();
        addColumnDescriptor(new ColumnDescriptor(this,"getCompanyCode","setCompanyCode","COMPANY_CODE,1,1,3,0,true|T,0,CHAR,1,null,null,null,null,null|U,0,CHAR,1,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getProductPrefix","setProductPrefix","PRODUCT_PREFIX,1,2,1,0,true|T,0,CHAR,1,null,null,null,null,null|U,0,CHAR,1,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getTableSubset","setTableSubset","TABLE_SUBSET,1,3,16,0,true|T,0,CHAR,1,null,null,null,null,null|U,0,CHAR,1,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getEffectiveDate","setEffectiveDate","EFFECTIVE_DATE,91,4,10,0,true|T,0,DATE,0,null,null,null,null,null|U,0,DATE,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getChildMaxIssAge","setChildMaxIssAge","CHILD_MAX_ISS_AGE,5,5,3,0,false|T,0,INTEGER,0,null,null,null,null,null|U,0,INTEGER,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getChildExpAge","setChildExpAge","CHILD_EXP_AGE,5,6,3,0,false|T,0,INTEGER,0,null,null,null,null,null|U,0,INTEGER,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getChildExpNtcDays","setChildExpNtcDays","CHILD_EXP_NTC_DAYS,5,7,4,0,false|T,0,INTEGER,0,null,null,null,null,null|U,0,INTEGER,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getChildExpDtRule","setChildExpDtRule","CHILD_EXP_DT_RULE,1,8,1,0,false|T,0,CHAR,0,null,null,null,null,null|U,0,CHAR,0,null,null,null,null,null"));
    }
    
    public String getPagingSQL(String schemaName,boolean isSubsetMode,boolean isNext, boolean locator)
    {
        String pagingWhere = null;
        String order = null;
        if (isNext) {
            if (isSubsetMode)
                if (locator)
                    pagingWhere = ".T120X WHERE (COMPANY_CODE = :company_code) AND (PRODUCT_PREFIX = :product_prefix) AND (TABLE_SUBSET = :table_subset) AND ((EFFECTIVE_DATE > :effective_date) OR (EFFECTIVE_DATE = :effective_date)) ";
                else 
                    pagingWhere = ".T120X WHERE (COMPANY_CODE = :company_code) AND (PRODUCT_PREFIX = :product_prefix) AND (TABLE_SUBSET = :table_subset) AND ((EFFECTIVE_DATE > :effective_date)) ";
            else
                if (locator)
                    pagingWhere = ".T120X WHERE (COMPANY_CODE = :company_code) AND ((PRODUCT_PREFIX > :product_prefix) OR (PRODUCT_PREFIX = :product_prefix AND TABLE_SUBSET > :table_subset) OR (PRODUCT_PREFIX = :product_prefix AND TABLE_SUBSET = :table_subset AND EFFECTIVE_DATE > :effective_date) OR (PRODUCT_PREFIX = :product_prefix AND TABLE_SUBSET = :table_subset AND EFFECTIVE_DATE = :effective_date)) ";
                else
                    pagingWhere = ".T120X WHERE (COMPANY_CODE = :company_code) AND ((PRODUCT_PREFIX > :product_prefix) OR (PRODUCT_PREFIX = :product_prefix AND TABLE_SUBSET > :table_subset) OR (PRODUCT_PREFIX = :product_prefix AND TABLE_SUBSET = :table_subset AND EFFECTIVE_DATE > :effective_date)) ";
            order = " ORDER BY COMPANY_CODE, PRODUCT_PREFIX, TABLE_SUBSET, EFFECTIVE_DATE";
        }
        else {
            if (isSubsetMode)
                pagingWhere = ".T120X WHERE (COMPANY_CODE = :company_code) AND (PRODUCT_PREFIX = :product_prefix) AND (TABLE_SUBSET = :table_subset) AND ((EFFECTIVE_DATE < :effective_date)) ";
            else
                pagingWhere = ".T120X WHERE (COMPANY_CODE = :company_code) AND ((PRODUCT_PREFIX < :product_prefix) OR (PRODUCT_PREFIX = :product_prefix AND TABLE_SUBSET < :table_subset) OR (PRODUCT_PREFIX = :product_prefix AND TABLE_SUBSET = :table_subset AND EFFECTIVE_DATE < :effective_date)) ";
            order = " ORDER BY COMPANY_CODE DESC, PRODUCT_PREFIX DESC, TABLE_SUBSET DESC, EFFECTIVE_DATE DESC";
        }
        return pagingSql + schemaName + pagingWhere + order;
    }
    
    public String prepareInsertStmt(String schemaName) {
        StringBuffer sb = new StringBuffer();
        sb.append("INSERT INTO ").append(schemaName);
        sb.append(".T120X ( ");
        sb.append("COMPANY_CODE, PRODUCT_PREFIX, TABLE_SUBSET, EFFECTIVE_DATE, CHILD_MAX_ISS_AGE, CHILD_EXP_AGE, CHILD_EXP_NTC_DAYS, CHILD_EXP_DT_RULE )");
        sb.append(" VALUES (?, ?, ?, ?, ?, ?, ?, ? )");
        return sb.toString();
    }
    
    public String prepareUpdateStmt(String schemaName)
    {
        StringBuffer sb = new StringBuffer();
        sb.append("UPDATE ").append(schemaName);
        sb.append(".T120X ");
        sb.append(" SET COMPANY_CODE = ?, PRODUCT_PREFIX = ?, TABLE_SUBSET = ?, EFFECTIVE_DATE = ?, CHILD_MAX_ISS_AGE = ?, CHILD_EXP_AGE = ?, CHILD_EXP_NTC_DAYS = ?, CHILD_EXP_DT_RULE = ?");
        sb.append(" WHERE COMPANY_CODE = ? AND PRODUCT_PREFIX = ? AND TABLE_SUBSET = ? AND EFFECTIVE_DATE = ?");
        return sb.toString();
    }
    
    public String prepareDeleteStmt(String schemaName)
    {
        StringBuffer sb = new StringBuffer();
        sb.append("DELETE FROM ").append(schemaName);
        sb.append(".T120X ");
        sb.append(" WHERE COMPANY_CODE = ? AND PRODUCT_PREFIX = ? AND TABLE_SUBSET = ? AND EFFECTIVE_DATE = ?");
        return sb.toString();
    }
}
