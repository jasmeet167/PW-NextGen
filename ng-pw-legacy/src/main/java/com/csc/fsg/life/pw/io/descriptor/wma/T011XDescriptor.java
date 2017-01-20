package com.csc.fsg.life.pw.io.descriptor.wma;

import com.csc.fsg.life.pw.io.*;

public class T011XDescriptor
    extends TableDescriptor
{
    private static final String pagingSql = "SELECT COMPANY_CODE, PRODUCT_PREFIX, TABLE_SUBSET, STATUTORY_CODE, STATE_CODE, APPROVAL_DATE, TABLE_PTR_SUBSET, EFFECTIVE_DATE_IND, UNISEX_RATES_IND FROM ";
    
    public void initialize()
    {
        setRowClass(T011XRow.class);
        setTableName("T011X");
        setTableId("011");
        super.initialize();
    }
    
    public void initializeColumnDescriptors()
    {
        super.initializeColumnDescriptors();
        addColumnDescriptor(new ColumnDescriptor(this,"getCompanyCode","setCompanyCode","COMPANY_CODE,1,1,3,0,true|A,0,CHAR,1,null,null,null,null,null|H,0,CHAR,1,null,null,null,null,null|T,0,CHAR,1,null,null,null,null,null|U,0,CHAR,1,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getProductPrefix","setProductPrefix","PRODUCT_PREFIX,1,2,1,0,true|A,0,CHAR,1,null,null,null,null,null|H,0,CHAR,1,null,null,null,null,null|T,0,CHAR,1,null,null,null,null,null|U,0,CHAR,1,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getTableSubset","setTableSubset","TABLE_SUBSET,1,3,16,0,true|A,0,CHAR,1,null,null,null,null,null|H,0,CHAR,1,null,null,null,null,null|T,0,CHAR,1,null,null,null,null,null|U,0,CHAR,1,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getStatutoryCode","setStatutoryCode","STATUTORY_CODE,1,4,3,0,true|A,0,CHAR,0,null,null,null,null,null|H,0,CHAR,0,null,null,null,null,null|T,0,CHAR,0,null,null,null,null,null|U,0,CHAR,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getStateCode","setStateCode","STATE_CODE,1,5,3,0,true|A,0,CHAR,0,null,null,null,null,null|H,0,CHAR,0,null,null,null,null,null|T,0,CHAR,0,null,null,null,null,null|U,0,CHAR,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getApprovalDate","setApprovalDate","APPROVAL_DATE,91,6,10,0,false|A,0,DATE,0,null,null,null,null,null|H,0,DATE,0,null,null,null,null,null|T,0,DATE,0,null,null,null,null,null|U,0,DATE,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getTablePtrSubset","setTablePtrSubset","TABLE_PTR_SUBSET,1,7,16,0,false|A,0,CHAR,0,127,null,null,null,null|H,0,CHAR,2,null,null,null,null,null|T,0,CHAR,2,null,null,null,null,null|U,0,CHAR,0,127,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getEffectiveDateInd","setEffectiveDateInd","EFFECTIVE_DATE_IND,1,8,1,0,false|A,0,CHAR,0,null,null,null,null,null|H,0,CHAR,2,null,null,null,null,null|T,0,CHAR,2,null,null,null,null,null|U,0,CHAR,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getUnisexRatesInd","setUnisexRatesInd","UNISEX_RATES_IND,1,9,1,0,false|A,0,CHAR,0,null,null,null,null,null|H,0,CHAR,2,null,null,null,null,null|T,0,CHAR,2,null,null,null,null,null|U,0,CHAR,2,null,null,null,null,null"));
    }
    
    public String getPagingSQL(String schemaName,boolean isSubsetMode,boolean isNext, boolean locator)
    {
        String pagingWhere = null;
        String order = null;
        if (isNext) {
            if (isSubsetMode)
                if (locator)
                    pagingWhere = ".T011X WHERE (COMPANY_CODE = :company_code) AND (PRODUCT_PREFIX = :product_prefix) AND (TABLE_SUBSET = :table_subset) AND ((STATUTORY_CODE > :statutory_code) OR (STATUTORY_CODE = :statutory_code AND STATE_CODE > :state_code) OR (STATUTORY_CODE = :statutory_code AND STATE_CODE = :state_code)) ";
                else 
                    pagingWhere = ".T011X WHERE (COMPANY_CODE = :company_code) AND (PRODUCT_PREFIX = :product_prefix) AND (TABLE_SUBSET = :table_subset) AND ((STATUTORY_CODE > :statutory_code) OR (STATUTORY_CODE = :statutory_code AND STATE_CODE > :state_code)) ";
            else
                if (locator)
                    pagingWhere = ".T011X WHERE (COMPANY_CODE = :company_code) AND ((PRODUCT_PREFIX > :product_prefix) OR (PRODUCT_PREFIX = :product_prefix AND TABLE_SUBSET > :table_subset) OR (PRODUCT_PREFIX = :product_prefix AND TABLE_SUBSET = :table_subset AND STATUTORY_CODE > :statutory_code) OR (PRODUCT_PREFIX = :product_prefix AND TABLE_SUBSET = :table_subset AND STATUTORY_CODE = :statutory_code AND STATE_CODE > :state_code) OR (PRODUCT_PREFIX = :product_prefix AND TABLE_SUBSET = :table_subset AND STATUTORY_CODE = :statutory_code AND STATE_CODE = :state_code)) ";
                else
                    pagingWhere = ".T011X WHERE (COMPANY_CODE = :company_code) AND ((PRODUCT_PREFIX > :product_prefix) OR (PRODUCT_PREFIX = :product_prefix AND TABLE_SUBSET > :table_subset) OR (PRODUCT_PREFIX = :product_prefix AND TABLE_SUBSET = :table_subset AND STATUTORY_CODE > :statutory_code) OR (PRODUCT_PREFIX = :product_prefix AND TABLE_SUBSET = :table_subset AND STATUTORY_CODE = :statutory_code AND STATE_CODE > :state_code)) ";
            order = " ORDER BY COMPANY_CODE, PRODUCT_PREFIX, TABLE_SUBSET, STATUTORY_CODE, STATE_CODE";
        }
        else {
            if (isSubsetMode)
                pagingWhere = ".T011X WHERE (COMPANY_CODE = :company_code) AND (PRODUCT_PREFIX = :product_prefix) AND (TABLE_SUBSET = :table_subset) AND ((STATUTORY_CODE < :statutory_code) OR (STATUTORY_CODE = :statutory_code AND STATE_CODE < :state_code)) ";
            else
                pagingWhere = ".T011X WHERE (COMPANY_CODE = :company_code) AND ((PRODUCT_PREFIX < :product_prefix) OR (PRODUCT_PREFIX = :product_prefix AND TABLE_SUBSET < :table_subset) OR (PRODUCT_PREFIX = :product_prefix AND TABLE_SUBSET = :table_subset AND STATUTORY_CODE < :statutory_code) OR (PRODUCT_PREFIX = :product_prefix AND TABLE_SUBSET = :table_subset AND STATUTORY_CODE = :statutory_code AND STATE_CODE < :state_code)) ";
            order = " ORDER BY COMPANY_CODE DESC, PRODUCT_PREFIX DESC, TABLE_SUBSET DESC, STATUTORY_CODE DESC, STATE_CODE DESC";
        }
        return pagingSql + schemaName + pagingWhere + order;
    }
    
    public String prepareInsertStmt(String schemaName) {
        StringBuffer sb = new StringBuffer();
        sb.append("INSERT INTO ").append(schemaName);
        sb.append(".T011X ( ");
        sb.append("COMPANY_CODE, PRODUCT_PREFIX, TABLE_SUBSET, STATUTORY_CODE, STATE_CODE, APPROVAL_DATE, TABLE_PTR_SUBSET, EFFECTIVE_DATE_IND, UNISEX_RATES_IND )");
        sb.append(" VALUES (?, ?, ?, ?, ?, ?, ?, ?, ? )");
        return sb.toString();
    }
    
    public String prepareUpdateStmt(String schemaName)
    {
        StringBuffer sb = new StringBuffer();
        sb.append("UPDATE ").append(schemaName);
        sb.append(".T011X ");
        sb.append(" SET COMPANY_CODE = ?, PRODUCT_PREFIX = ?, TABLE_SUBSET = ?, STATUTORY_CODE = ?, STATE_CODE = ?, APPROVAL_DATE = ?, TABLE_PTR_SUBSET = ?, EFFECTIVE_DATE_IND = ?, UNISEX_RATES_IND = ?");
        sb.append(" WHERE COMPANY_CODE = ? AND PRODUCT_PREFIX = ? AND TABLE_SUBSET = ? AND STATUTORY_CODE = ? AND STATE_CODE = ?");
        return sb.toString();
    }
    
    public String prepareDeleteStmt(String schemaName)
    {
        StringBuffer sb = new StringBuffer();
        sb.append("DELETE FROM ").append(schemaName);
        sb.append(".T011X ");
        sb.append(" WHERE COMPANY_CODE = ? AND PRODUCT_PREFIX = ? AND TABLE_SUBSET = ? AND STATUTORY_CODE = ? AND STATE_CODE = ?");
        return sb.toString();
    }
}
