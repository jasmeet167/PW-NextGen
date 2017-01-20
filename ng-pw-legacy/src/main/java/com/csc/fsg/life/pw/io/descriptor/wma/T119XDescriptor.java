package com.csc.fsg.life.pw.io.descriptor.wma;

import com.csc.fsg.life.pw.io.*;

public class T119XDescriptor
    extends TableDescriptor
{
    private static final String pagingSql = "SELECT COMPANY_CODE, PRODUCT_PREFIX, TABLE_SUBSET, EFFECTIVE_DATE, FAV_CODE FROM ";
    
    public void initialize()
    {
        setRowClass(T119XRow.class);
        setTableName("T119X");
        setTableId("119");
        super.initialize();
    }
    
    public void initializeColumnDescriptors()
    {
        super.initializeColumnDescriptors();
        addColumnDescriptor(new ColumnDescriptor(this,"getCompanyCode","setCompanyCode","COMPANY_CODE,1,1,3,0,true|A,0,CHAR,1,null,null,null,null,null|U,0,CHAR,1,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getProductPrefix","setProductPrefix","PRODUCT_PREFIX,1,2,1,0,true|A,0,CHAR,1,null,null,null,null,null|U,0,CHAR,1,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getTableSubset","setTableSubset","TABLE_SUBSET,1,3,16,0,true|A,0,CHAR,1,null,null,null,null,null|U,0,CHAR,1,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getEffectiveDate","setEffectiveDate","EFFECTIVE_DATE,91,4,10,0,true|A,0,DATE,0,null,null,null,null,null|U,0,DATE,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getFavCode","setFavCode","FAV_CODE,1,5,6,0,true|A,0,CHAR,4,null,null,null,null,null|U,0,CHAR,4,null,null,null,null,null"));
    }
    
    public String getPagingSQL(String schemaName,boolean isSubsetMode,boolean isNext, boolean locator)
    {
        String pagingWhere = null;
        String order = null;
        if (isNext) {
            if (isSubsetMode)
                if (locator)
                    pagingWhere = ".T119X WHERE (COMPANY_CODE = :company_code) AND (PRODUCT_PREFIX = :product_prefix) AND (TABLE_SUBSET = :table_subset) AND ((EFFECTIVE_DATE > :effective_date) OR (EFFECTIVE_DATE = :effective_date AND FAV_CODE > :fav_code) OR (EFFECTIVE_DATE = :effective_date AND FAV_CODE = :fav_code)) ";
                else 
                    pagingWhere = ".T119X WHERE (COMPANY_CODE = :company_code) AND (PRODUCT_PREFIX = :product_prefix) AND (TABLE_SUBSET = :table_subset) AND ((EFFECTIVE_DATE > :effective_date) OR (EFFECTIVE_DATE = :effective_date AND FAV_CODE > :fav_code)) ";
            else
                if (locator)
                    pagingWhere = ".T119X WHERE (COMPANY_CODE = :company_code) AND ((PRODUCT_PREFIX > :product_prefix) OR (PRODUCT_PREFIX = :product_prefix AND TABLE_SUBSET > :table_subset) OR (PRODUCT_PREFIX = :product_prefix AND TABLE_SUBSET = :table_subset AND EFFECTIVE_DATE > :effective_date) OR (PRODUCT_PREFIX = :product_prefix AND TABLE_SUBSET = :table_subset AND EFFECTIVE_DATE = :effective_date AND FAV_CODE > :fav_code) OR (PRODUCT_PREFIX = :product_prefix AND TABLE_SUBSET = :table_subset AND EFFECTIVE_DATE = :effective_date AND FAV_CODE = :fav_code)) ";
                else
                    pagingWhere = ".T119X WHERE (COMPANY_CODE = :company_code) AND ((PRODUCT_PREFIX > :product_prefix) OR (PRODUCT_PREFIX = :product_prefix AND TABLE_SUBSET > :table_subset) OR (PRODUCT_PREFIX = :product_prefix AND TABLE_SUBSET = :table_subset AND EFFECTIVE_DATE > :effective_date) OR (PRODUCT_PREFIX = :product_prefix AND TABLE_SUBSET = :table_subset AND EFFECTIVE_DATE = :effective_date AND FAV_CODE > :fav_code)) ";
            order = " ORDER BY COMPANY_CODE, PRODUCT_PREFIX, TABLE_SUBSET, EFFECTIVE_DATE, FAV_CODE";
        }
        else {
            if (isSubsetMode)
                pagingWhere = ".T119X WHERE (COMPANY_CODE = :company_code) AND (PRODUCT_PREFIX = :product_prefix) AND (TABLE_SUBSET = :table_subset) AND ((EFFECTIVE_DATE < :effective_date) OR (EFFECTIVE_DATE = :effective_date AND FAV_CODE < :fav_code)) ";
            else
                pagingWhere = ".T119X WHERE (COMPANY_CODE = :company_code) AND ((PRODUCT_PREFIX < :product_prefix) OR (PRODUCT_PREFIX = :product_prefix AND TABLE_SUBSET < :table_subset) OR (PRODUCT_PREFIX = :product_prefix AND TABLE_SUBSET = :table_subset AND EFFECTIVE_DATE < :effective_date) OR (PRODUCT_PREFIX = :product_prefix AND TABLE_SUBSET = :table_subset AND EFFECTIVE_DATE = :effective_date AND FAV_CODE < :fav_code)) ";
            order = " ORDER BY COMPANY_CODE DESC, PRODUCT_PREFIX DESC, TABLE_SUBSET DESC, EFFECTIVE_DATE DESC, FAV_CODE DESC";
        }
        return pagingSql + schemaName + pagingWhere + order;
    }
    
    public String prepareInsertStmt(String schemaName) {
        StringBuffer sb = new StringBuffer();
        sb.append("INSERT INTO ").append(schemaName);
        sb.append(".T119X ( ");
        sb.append("COMPANY_CODE, PRODUCT_PREFIX, TABLE_SUBSET, EFFECTIVE_DATE, FAV_CODE )");
        sb.append(" VALUES (?, ?, ?, ?, ? )");
        return sb.toString();
    }
    
    public String prepareUpdateStmt(String schemaName)
    {
        StringBuffer sb = new StringBuffer();
        sb.append("UPDATE ").append(schemaName);
        sb.append(".T119X ");
        sb.append(" SET COMPANY_CODE = ?, PRODUCT_PREFIX = ?, TABLE_SUBSET = ?, EFFECTIVE_DATE = ?, FAV_CODE = ?");
        sb.append(" WHERE COMPANY_CODE = ? AND PRODUCT_PREFIX = ? AND TABLE_SUBSET = ? AND EFFECTIVE_DATE = ? AND FAV_CODE = ?");
        return sb.toString();
    }
    
    public String prepareDeleteStmt(String schemaName)
    {
        StringBuffer sb = new StringBuffer();
        sb.append("DELETE FROM ").append(schemaName);
        sb.append(".T119X ");
        sb.append(" WHERE COMPANY_CODE = ? AND PRODUCT_PREFIX = ? AND TABLE_SUBSET = ? AND EFFECTIVE_DATE = ? AND FAV_CODE = ?");
        return sb.toString();
    }
}
