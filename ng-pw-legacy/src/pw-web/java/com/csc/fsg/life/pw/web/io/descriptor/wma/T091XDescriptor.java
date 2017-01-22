package com.csc.fsg.life.pw.web.io.descriptor.wma;

import com.csc.fsg.life.pw.web.io.*;

/* Modifications: ENH1046 */

public class T091XDescriptor
    extends TableDescriptor
{
    private static final String pagingSql = "SELECT COMPANY_CODE, PRODUCT_PREFIX, TABLE_SUBSET, EFFECTIVE_DATE, TRANSACTION_CODE, HCC_RIDER_PLAN_CD, FUND_TYPE, FUND_NUMBER, FUND_PRIORITY, OVRD_PERMITTED FROM ";
    
    public void initialize()
    {
        setRowClass(T091XRow.class);
        setTableName("T091X");
        setTableId("091");
        super.initialize();
    }
    
    public void initializeColumnDescriptors()
    {
        super.initializeColumnDescriptors();
        addColumnDescriptor(new ColumnDescriptor(this,"getCompanyCode","setCompanyCode","COMPANY_CODE,1,1,3,0,true|A,0,CHAR,1,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getProductPrefix","setProductPrefix","PRODUCT_PREFIX,1,2,1,0,true|A,0,CHAR,1,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getTableSubset","setTableSubset","TABLE_SUBSET,1,3,16,0,true|A,0,CHAR,1,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getEffectiveDate","setEffectiveDate","EFFECTIVE_DATE,91,4,10,0,true|A,0,DATE,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getTransactionCode","setTransactionCode","TRANSACTION_CODE,1,5,4,0,true|A,0,CHAR,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getHccRiderPlanCd","setHccRiderPlanCd","HCC_RIDER_PLAN_CD,1,6,6,0,true|A,0,CHAR,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getFundType","setFundType","FUND_TYPE,1,7,1,0,true|A,0,CHAR,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getFundNumber","setFundNumber","FUND_NUMBER,3,8,8,0,true|A,0,INTEGER,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getFundPriority","setFundPriority","FUND_PRIORITY,3,9,3,0,false|A,0,INTEGER,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getOvrdPermitted","setOvrdPermitted","OVRD_PERMITTED,1,10,1,0,false|A,0,CHAR,0,null,null,null,null,null"));
    }
    
    public String getPagingSQL(String schemaName,boolean isSubsetMode,boolean isNext, boolean locator)
    {
        String pagingWhere = null;
        String order = null;
        if (isNext) {
            if (isSubsetMode)
                if (locator)
                    pagingWhere = ".T091X WHERE (COMPANY_CODE = :company_code) AND (PRODUCT_PREFIX = :product_prefix) AND (TABLE_SUBSET = :table_subset) AND ((EFFECTIVE_DATE > :effective_date) OR (EFFECTIVE_DATE = :effective_date AND TRANSACTION_CODE > :transaction_code) OR (EFFECTIVE_DATE = :effective_date AND TRANSACTION_CODE = :transaction_code AND HCC_RIDER_PLAN_CD > :hcc_rider_plan_cd) OR (EFFECTIVE_DATE = :effective_date AND TRANSACTION_CODE = :transaction_code AND HCC_RIDER_PLAN_CD = :hcc_rider_plan_cd AND FUND_TYPE > :fund_type) OR (EFFECTIVE_DATE = :effective_date AND TRANSACTION_CODE = :transaction_code AND HCC_RIDER_PLAN_CD = :hcc_rider_plan_cd AND FUND_TYPE = :fund_type AND FUND_NUMBER > :fund_number) OR (EFFECTIVE_DATE = :effective_date AND TRANSACTION_CODE = :transaction_code AND HCC_RIDER_PLAN_CD = :hcc_rider_plan_cd AND FUND_TYPE = :fund_type AND FUND_NUMBER = :fund_number)) ";
                else 
                    pagingWhere = ".T091X WHERE (COMPANY_CODE = :company_code) AND (PRODUCT_PREFIX = :product_prefix) AND (TABLE_SUBSET = :table_subset) AND ((EFFECTIVE_DATE > :effective_date) OR (EFFECTIVE_DATE = :effective_date AND TRANSACTION_CODE > :transaction_code) OR (EFFECTIVE_DATE = :effective_date AND TRANSACTION_CODE = :transaction_code AND HCC_RIDER_PLAN_CD > :hcc_rider_plan_cd) OR (EFFECTIVE_DATE = :effective_date AND TRANSACTION_CODE = :transaction_code AND HCC_RIDER_PLAN_CD = :hcc_rider_plan_cd AND FUND_TYPE > :fund_type) OR (EFFECTIVE_DATE = :effective_date AND TRANSACTION_CODE = :transaction_code AND HCC_RIDER_PLAN_CD = :hcc_rider_plan_cd AND FUND_TYPE = :fund_type AND FUND_NUMBER > :fund_number)) ";
            else
                if (locator)
                    pagingWhere = ".T091X WHERE (COMPANY_CODE = :company_code) AND ((PRODUCT_PREFIX > :product_prefix) OR (PRODUCT_PREFIX = :product_prefix AND TABLE_SUBSET > :table_subset) OR (PRODUCT_PREFIX = :product_prefix AND TABLE_SUBSET = :table_subset AND EFFECTIVE_DATE > :effective_date) OR (PRODUCT_PREFIX = :product_prefix AND TABLE_SUBSET = :table_subset AND EFFECTIVE_DATE = :effective_date AND TRANSACTION_CODE > :transaction_code) OR (PRODUCT_PREFIX = :product_prefix AND TABLE_SUBSET = :table_subset AND EFFECTIVE_DATE = :effective_date AND TRANSACTION_CODE = :transaction_code AND HCC_RIDER_PLAN_CD > :hcc_rider_plan_cd) OR (PRODUCT_PREFIX = :product_prefix AND TABLE_SUBSET = :table_subset AND EFFECTIVE_DATE = :effective_date AND TRANSACTION_CODE = :transaction_code AND HCC_RIDER_PLAN_CD = :hcc_rider_plan_cd AND FUND_TYPE > :fund_type) OR (PRODUCT_PREFIX = :product_prefix AND TABLE_SUBSET = :table_subset AND EFFECTIVE_DATE = :effective_date AND TRANSACTION_CODE = :transaction_code AND HCC_RIDER_PLAN_CD = :hcc_rider_plan_cd AND FUND_TYPE = :fund_type AND FUND_NUMBER > :fund_number) OR (PRODUCT_PREFIX = :product_prefix AND TABLE_SUBSET = :table_subset AND EFFECTIVE_DATE = :effective_date AND TRANSACTION_CODE = :transaction_code AND HCC_RIDER_PLAN_CD = :hcc_rider_plan_cd AND FUND_TYPE = :fund_type AND FUND_NUMBER = :fund_number)) ";
                else
                    pagingWhere = ".T091X WHERE (COMPANY_CODE = :company_code) AND ((PRODUCT_PREFIX > :product_prefix) OR (PRODUCT_PREFIX = :product_prefix AND TABLE_SUBSET > :table_subset) OR (PRODUCT_PREFIX = :product_prefix AND TABLE_SUBSET = :table_subset AND EFFECTIVE_DATE > :effective_date) OR (PRODUCT_PREFIX = :product_prefix AND TABLE_SUBSET = :table_subset AND EFFECTIVE_DATE = :effective_date AND TRANSACTION_CODE > :transaction_code) OR (PRODUCT_PREFIX = :product_prefix AND TABLE_SUBSET = :table_subset AND EFFECTIVE_DATE = :effective_date AND TRANSACTION_CODE = :transaction_code AND HCC_RIDER_PLAN_CD > :hcc_rider_plan_cd) OR (PRODUCT_PREFIX = :product_prefix AND TABLE_SUBSET = :table_subset AND EFFECTIVE_DATE = :effective_date AND TRANSACTION_CODE = :transaction_code AND HCC_RIDER_PLAN_CD = :hcc_rider_plan_cd AND FUND_TYPE > :fund_type) OR (PRODUCT_PREFIX = :product_prefix AND TABLE_SUBSET = :table_subset AND EFFECTIVE_DATE = :effective_date AND TRANSACTION_CODE = :transaction_code AND HCC_RIDER_PLAN_CD = :hcc_rider_plan_cd AND FUND_TYPE = :fund_type AND FUND_NUMBER > :fund_number)) ";
            order = " ORDER BY COMPANY_CODE, PRODUCT_PREFIX, TABLE_SUBSET, EFFECTIVE_DATE, TRANSACTION_CODE, HCC_RIDER_PLAN_CD, FUND_TYPE, FUND_NUMBER";
        }
        else {
            if (isSubsetMode)
                pagingWhere = ".T091X WHERE (COMPANY_CODE = :company_code) AND (PRODUCT_PREFIX = :product_prefix) AND (TABLE_SUBSET = :table_subset) AND ((EFFECTIVE_DATE < :effective_date) OR (EFFECTIVE_DATE = :effective_date AND TRANSACTION_CODE < :transaction_code) OR (EFFECTIVE_DATE = :effective_date AND TRANSACTION_CODE = :transaction_code AND HCC_RIDER_PLAN_CD < :hcc_rider_plan_cd) OR (EFFECTIVE_DATE = :effective_date AND TRANSACTION_CODE = :transaction_code AND HCC_RIDER_PLAN_CD = :hcc_rider_plan_cd AND FUND_TYPE < :fund_type) OR (EFFECTIVE_DATE = :effective_date AND TRANSACTION_CODE = :transaction_code AND HCC_RIDER_PLAN_CD = :hcc_rider_plan_cd AND FUND_TYPE = :fund_type AND FUND_NUMBER < :fund_number)) ";
            else
                pagingWhere = ".T091X WHERE (COMPANY_CODE = :company_code) AND ((PRODUCT_PREFIX < :product_prefix) OR (PRODUCT_PREFIX = :product_prefix AND TABLE_SUBSET < :table_subset) OR (PRODUCT_PREFIX = :product_prefix AND TABLE_SUBSET = :table_subset AND EFFECTIVE_DATE < :effective_date) OR (PRODUCT_PREFIX = :product_prefix AND TABLE_SUBSET = :table_subset AND EFFECTIVE_DATE = :effective_date AND TRANSACTION_CODE < :transaction_code) OR (PRODUCT_PREFIX = :product_prefix AND TABLE_SUBSET = :table_subset AND EFFECTIVE_DATE = :effective_date AND TRANSACTION_CODE = :transaction_code AND HCC_RIDER_PLAN_CD < :hcc_rider_plan_cd) OR (PRODUCT_PREFIX = :product_prefix AND TABLE_SUBSET = :table_subset AND EFFECTIVE_DATE = :effective_date AND TRANSACTION_CODE = :transaction_code AND HCC_RIDER_PLAN_CD = :hcc_rider_plan_cd AND FUND_TYPE < :fund_type) OR (PRODUCT_PREFIX = :product_prefix AND TABLE_SUBSET = :table_subset AND EFFECTIVE_DATE = :effective_date AND TRANSACTION_CODE = :transaction_code AND HCC_RIDER_PLAN_CD = :hcc_rider_plan_cd AND FUND_TYPE = :fund_type AND FUND_NUMBER < :fund_number)) ";
            order = " ORDER BY COMPANY_CODE DESC, PRODUCT_PREFIX DESC, TABLE_SUBSET DESC, EFFECTIVE_DATE DESC, TRANSACTION_CODE DESC, HCC_RIDER_PLAN_CD DESC, FUND_TYPE DESC, FUND_NUMBER DESC";
        }
        return pagingSql + schemaName + pagingWhere + order;
    }
    
    public String prepareInsertStmt(String schemaName) {
        StringBuffer sb = new StringBuffer();
        sb.append("INSERT INTO ").append(schemaName);
        sb.append(".T091X ( ");
        sb.append("COMPANY_CODE, PRODUCT_PREFIX, TABLE_SUBSET, EFFECTIVE_DATE, TRANSACTION_CODE, HCC_RIDER_PLAN_CD, FUND_TYPE, FUND_NUMBER, FUND_PRIORITY, OVRD_PERMITTED )");
        sb.append(" VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ? )");
        return sb.toString();
    }
    
    public String prepareUpdateStmt(String schemaName)
    {
        StringBuffer sb = new StringBuffer();
        sb.append("UPDATE ").append(schemaName);
        sb.append(".T091X ");
        sb.append(" SET COMPANY_CODE = ?, PRODUCT_PREFIX = ?, TABLE_SUBSET = ?, EFFECTIVE_DATE = ?, TRANSACTION_CODE = ?, HCC_RIDER_PLAN_CD = ?, FUND_TYPE = ?, FUND_NUMBER = ?, FUND_PRIORITY = ?, OVRD_PERMITTED = ?");
        sb.append(" WHERE COMPANY_CODE = ? AND PRODUCT_PREFIX = ? AND TABLE_SUBSET = ? AND EFFECTIVE_DATE = ? AND TRANSACTION_CODE = ? AND HCC_RIDER_PLAN_CD = ? AND FUND_TYPE = ? AND FUND_NUMBER = ?");
        return sb.toString();
    }
    
    public String prepareDeleteStmt(String schemaName)
    {
        StringBuffer sb = new StringBuffer();
        sb.append("DELETE FROM ").append(schemaName);
        sb.append(".T091X ");
        sb.append(" WHERE COMPANY_CODE = ? AND PRODUCT_PREFIX = ? AND TABLE_SUBSET = ? AND EFFECTIVE_DATE = ? AND TRANSACTION_CODE = ? AND HCC_RIDER_PLAN_CD = ? AND FUND_TYPE = ? AND FUND_NUMBER = ?");
        return sb.toString();
    }
}
