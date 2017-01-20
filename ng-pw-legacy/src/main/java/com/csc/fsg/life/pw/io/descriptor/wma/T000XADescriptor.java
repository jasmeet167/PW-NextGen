package com.csc.fsg.life.pw.io.descriptor.wma;

import com.csc.fsg.life.pw.io.*;

public class T000XADescriptor
    extends TableDescriptor
{
    private static final String pagingSql = "SELECT COMPANY_CODE, PRODUCT_PREFIX, PRIMARY_TABLE_ID, PRIMARY_PTR_SUBSET, SECONDARY_TABLE_ID, SECNDRY_PTR_SUBSET, SECNDRY_TABLE_VAR FROM ";
    
    public void initialize()
    {
        setRowClass(T000XARow.class);
        setTableName("T000XA");
        setTableId("00X");
        super.initialize();
    }
    
    public void initializeColumnDescriptors()
    {
        super.initializeColumnDescriptors();
        addColumnDescriptor(new ColumnDescriptor(this,"getCompanyCode","setCompanyCode","COMPANY_CODE,1,1,3,0,true|A,0,CHAR,1,null,null,null,null,null|T,0,CHAR,1,null,null,null,null,null|U,0,CHAR,1,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getProductPrefix","setProductPrefix","PRODUCT_PREFIX,1,2,1,0,true|A,0,CHAR,1,null,null,null,null,null|T,0,CHAR,1,null,null,null,null,null|U,0,CHAR,1,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getPrimaryTableId","setPrimaryTableId","PRIMARY_TABLE_ID,1,3,3,0,true|A,0,CHAR,1,null,null,null,null,null|T,0,CHAR,1,null,null,null,null,null|U,0,CHAR,1,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getPrimaryPtrSubset","setPrimaryPtrSubset","PRIMARY_PTR_SUBSET,1,4,16,0,true|A,0,CHAR,1,null,null,null,null,null|T,0,CHAR,1,null,null,null,null,null|U,0,CHAR,1,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getSecondaryTableId","setSecondaryTableId","SECONDARY_TABLE_ID,1,5,3,0,true|A,0,CHAR,1,null,null,null,null,null|T,0,CHAR,1,null,null,null,null,null|U,0,CHAR,1,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getSecndryPtrSubset","setSecndryPtrSubset","SECNDRY_PTR_SUBSET,1,6,16,0,true|A,0,CHAR,1,null,null,null,null,null|T,0,CHAR,1,null,null,null,null,null|U,0,CHAR,1,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getSecndryTableVar","setSecndryTableVar","SECNDRY_TABLE_VAR,1,7,1,0,true|A,0,CHAR,1,null,null,null,null,null|T,0,CHAR,1,null,null,null,null,null|U,0,CHAR,1,null,null,null,null,null"));
    }
    
    public String getPagingSQL(String schemaName,boolean isSubsetMode,boolean isNext, boolean locator)
    {
        String pagingWhere = null;
        String order = null;
        if (isNext) {
            if (isSubsetMode)
                if (locator)
                    pagingWhere = ".T000XA WHERE (COMPANY_CODE = :company_code) AND (PRODUCT_PREFIX = :product_prefix) AND ((PRIMARY_TABLE_ID > :primary_table_id) OR (PRIMARY_TABLE_ID = :primary_table_id AND PRIMARY_PTR_SUBSET > :primary_ptr_subset) OR (PRIMARY_TABLE_ID = :primary_table_id AND PRIMARY_PTR_SUBSET = :primary_ptr_subset AND SECONDARY_TABLE_ID > :secondary_table_id) OR (PRIMARY_TABLE_ID = :primary_table_id AND PRIMARY_PTR_SUBSET = :primary_ptr_subset AND SECONDARY_TABLE_ID = :secondary_table_id AND SECNDRY_PTR_SUBSET > :secndry_ptr_subset) OR (PRIMARY_TABLE_ID = :primary_table_id AND PRIMARY_PTR_SUBSET = :primary_ptr_subset AND SECONDARY_TABLE_ID = :secondary_table_id AND SECNDRY_PTR_SUBSET = :secndry_ptr_subset AND SECNDRY_TABLE_VAR > :secndry_table_var) OR (PRIMARY_TABLE_ID = :primary_table_id AND PRIMARY_PTR_SUBSET = :primary_ptr_subset AND SECONDARY_TABLE_ID = :secondary_table_id AND SECNDRY_PTR_SUBSET = :secndry_ptr_subset AND SECNDRY_TABLE_VAR = :secndry_table_var)) ";
                else 
                    pagingWhere = ".T000XA WHERE (COMPANY_CODE = :company_code) AND (PRODUCT_PREFIX = :product_prefix) AND ((PRIMARY_TABLE_ID > :primary_table_id) OR (PRIMARY_TABLE_ID = :primary_table_id AND PRIMARY_PTR_SUBSET > :primary_ptr_subset) OR (PRIMARY_TABLE_ID = :primary_table_id AND PRIMARY_PTR_SUBSET = :primary_ptr_subset AND SECONDARY_TABLE_ID > :secondary_table_id) OR (PRIMARY_TABLE_ID = :primary_table_id AND PRIMARY_PTR_SUBSET = :primary_ptr_subset AND SECONDARY_TABLE_ID = :secondary_table_id AND SECNDRY_PTR_SUBSET > :secndry_ptr_subset) OR (PRIMARY_TABLE_ID = :primary_table_id AND PRIMARY_PTR_SUBSET = :primary_ptr_subset AND SECONDARY_TABLE_ID = :secondary_table_id AND SECNDRY_PTR_SUBSET = :secndry_ptr_subset AND SECNDRY_TABLE_VAR > :secndry_table_var)) ";
            else
                if (locator)
                    pagingWhere = ".T000XA WHERE (COMPANY_CODE = :company_code) AND ((PRODUCT_PREFIX > :product_prefix) OR (PRODUCT_PREFIX = :product_prefix AND PRIMARY_TABLE_ID > :primary_table_id) OR (PRODUCT_PREFIX = :product_prefix AND PRIMARY_TABLE_ID = :primary_table_id AND PRIMARY_PTR_SUBSET > :primary_ptr_subset) OR (PRODUCT_PREFIX = :product_prefix AND PRIMARY_TABLE_ID = :primary_table_id AND PRIMARY_PTR_SUBSET = :primary_ptr_subset AND SECONDARY_TABLE_ID > :secondary_table_id) OR (PRODUCT_PREFIX = :product_prefix AND PRIMARY_TABLE_ID = :primary_table_id AND PRIMARY_PTR_SUBSET = :primary_ptr_subset AND SECONDARY_TABLE_ID = :secondary_table_id AND SECNDRY_PTR_SUBSET > :secndry_ptr_subset) OR (PRODUCT_PREFIX = :product_prefix AND PRIMARY_TABLE_ID = :primary_table_id AND PRIMARY_PTR_SUBSET = :primary_ptr_subset AND SECONDARY_TABLE_ID = :secondary_table_id AND SECNDRY_PTR_SUBSET = :secndry_ptr_subset AND SECNDRY_TABLE_VAR > :secndry_table_var) OR (PRODUCT_PREFIX = :product_prefix AND PRIMARY_TABLE_ID = :primary_table_id AND PRIMARY_PTR_SUBSET = :primary_ptr_subset AND SECONDARY_TABLE_ID = :secondary_table_id AND SECNDRY_PTR_SUBSET = :secndry_ptr_subset AND SECNDRY_TABLE_VAR = :secndry_table_var)) ";
                else
                    pagingWhere = ".T000XA WHERE (COMPANY_CODE = :company_code) AND ((PRODUCT_PREFIX > :product_prefix) OR (PRODUCT_PREFIX = :product_prefix AND PRIMARY_TABLE_ID > :primary_table_id) OR (PRODUCT_PREFIX = :product_prefix AND PRIMARY_TABLE_ID = :primary_table_id AND PRIMARY_PTR_SUBSET > :primary_ptr_subset) OR (PRODUCT_PREFIX = :product_prefix AND PRIMARY_TABLE_ID = :primary_table_id AND PRIMARY_PTR_SUBSET = :primary_ptr_subset AND SECONDARY_TABLE_ID > :secondary_table_id) OR (PRODUCT_PREFIX = :product_prefix AND PRIMARY_TABLE_ID = :primary_table_id AND PRIMARY_PTR_SUBSET = :primary_ptr_subset AND SECONDARY_TABLE_ID = :secondary_table_id AND SECNDRY_PTR_SUBSET > :secndry_ptr_subset) OR (PRODUCT_PREFIX = :product_prefix AND PRIMARY_TABLE_ID = :primary_table_id AND PRIMARY_PTR_SUBSET = :primary_ptr_subset AND SECONDARY_TABLE_ID = :secondary_table_id AND SECNDRY_PTR_SUBSET = :secndry_ptr_subset AND SECNDRY_TABLE_VAR > :secndry_table_var)) ";
            order = " ORDER BY COMPANY_CODE, PRODUCT_PREFIX, PRIMARY_TABLE_ID, PRIMARY_PTR_SUBSET, SECONDARY_TABLE_ID, SECNDRY_PTR_SUBSET, SECNDRY_TABLE_VAR";
        }
        else {
            if (isSubsetMode)
                pagingWhere = ".T000XA WHERE (COMPANY_CODE = :company_code) AND (PRODUCT_PREFIX = :product_prefix) AND ((PRIMARY_TABLE_ID < :primary_table_id) OR (PRIMARY_TABLE_ID = :primary_table_id AND PRIMARY_PTR_SUBSET < :primary_ptr_subset) OR (PRIMARY_TABLE_ID = :primary_table_id AND PRIMARY_PTR_SUBSET = :primary_ptr_subset AND SECONDARY_TABLE_ID < :secondary_table_id) OR (PRIMARY_TABLE_ID = :primary_table_id AND PRIMARY_PTR_SUBSET = :primary_ptr_subset AND SECONDARY_TABLE_ID = :secondary_table_id AND SECNDRY_PTR_SUBSET < :secndry_ptr_subset) OR (PRIMARY_TABLE_ID = :primary_table_id AND PRIMARY_PTR_SUBSET = :primary_ptr_subset AND SECONDARY_TABLE_ID = :secondary_table_id AND SECNDRY_PTR_SUBSET = :secndry_ptr_subset AND SECNDRY_TABLE_VAR < :secndry_table_var)) ";
            else
                pagingWhere = ".T000XA WHERE (COMPANY_CODE = :company_code) AND ((PRODUCT_PREFIX < :product_prefix) OR (PRODUCT_PREFIX = :product_prefix AND PRIMARY_TABLE_ID < :primary_table_id) OR (PRODUCT_PREFIX = :product_prefix AND PRIMARY_TABLE_ID = :primary_table_id AND PRIMARY_PTR_SUBSET < :primary_ptr_subset) OR (PRODUCT_PREFIX = :product_prefix AND PRIMARY_TABLE_ID = :primary_table_id AND PRIMARY_PTR_SUBSET = :primary_ptr_subset AND SECONDARY_TABLE_ID < :secondary_table_id) OR (PRODUCT_PREFIX = :product_prefix AND PRIMARY_TABLE_ID = :primary_table_id AND PRIMARY_PTR_SUBSET = :primary_ptr_subset AND SECONDARY_TABLE_ID = :secondary_table_id AND SECNDRY_PTR_SUBSET < :secndry_ptr_subset) OR (PRODUCT_PREFIX = :product_prefix AND PRIMARY_TABLE_ID = :primary_table_id AND PRIMARY_PTR_SUBSET = :primary_ptr_subset AND SECONDARY_TABLE_ID = :secondary_table_id AND SECNDRY_PTR_SUBSET = :secndry_ptr_subset AND SECNDRY_TABLE_VAR < :secndry_table_var)) ";
            order = " ORDER BY COMPANY_CODE DESC, PRODUCT_PREFIX DESC, PRIMARY_TABLE_ID DESC, PRIMARY_PTR_SUBSET DESC, SECONDARY_TABLE_ID DESC, SECNDRY_PTR_SUBSET DESC, SECNDRY_TABLE_VAR DESC";
        }
        return pagingSql + schemaName + pagingWhere + order;
    }
    
    public String prepareInsertStmt(String schemaName) {
        StringBuffer sb = new StringBuffer();
        sb.append("INSERT INTO ").append(schemaName);
        sb.append(".T000XA ( ");
        sb.append("COMPANY_CODE, PRODUCT_PREFIX, PRIMARY_TABLE_ID, PRIMARY_PTR_SUBSET, SECONDARY_TABLE_ID, SECNDRY_PTR_SUBSET, SECNDRY_TABLE_VAR )");
        sb.append(" VALUES (?, ?, ?, ?, ?, ?, ? )");
        return sb.toString();
    }
    
    public String prepareUpdateStmt(String schemaName)
    {
        StringBuffer sb = new StringBuffer();
        sb.append("UPDATE ").append(schemaName);
        sb.append(".T000XA ");
        sb.append(" SET COMPANY_CODE = ?, PRODUCT_PREFIX = ?, PRIMARY_TABLE_ID = ?, PRIMARY_PTR_SUBSET = ?, SECONDARY_TABLE_ID = ?, SECNDRY_PTR_SUBSET = ?, SECNDRY_TABLE_VAR = ?");
        sb.append(" WHERE COMPANY_CODE = ? AND PRODUCT_PREFIX = ? AND PRIMARY_TABLE_ID = ? AND PRIMARY_PTR_SUBSET = ? AND SECONDARY_TABLE_ID = ? AND SECNDRY_PTR_SUBSET = ? AND SECNDRY_TABLE_VAR = ?");
        return sb.toString();
    }
    
    public String prepareDeleteStmt(String schemaName)
    {
        StringBuffer sb = new StringBuffer();
        sb.append("DELETE FROM ").append(schemaName);
        sb.append(".T000XA ");
        sb.append(" WHERE COMPANY_CODE = ? AND PRODUCT_PREFIX = ? AND PRIMARY_TABLE_ID = ? AND PRIMARY_PTR_SUBSET = ? AND SECONDARY_TABLE_ID = ? AND SECNDRY_PTR_SUBSET = ? AND SECNDRY_TABLE_VAR = ?");
        return sb.toString();
    }
}
