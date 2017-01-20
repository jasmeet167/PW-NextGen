package com.csc.fsg.life.pw.io.descriptor.wma;

import com.csc.fsg.life.pw.io.*;

public class T127XDescriptor
    extends TableDescriptor
{
    private static final String pagingSql = "SELECT COMPANY_CODE, PRODUCT_PREFIX, TABLE_SUBSET, REPLACEMENT_IND, EFFECTIVE_DATE, ISSUE_AGE, ANNUITY_TYPE, NO_ELAPSED_DAYS, MEMO_CODE, FREE_LOOK_SUR_IND FROM ";
    
    public void initialize()
    {
        setRowClass(T127XRow.class);
        setTableName("T127X");
        setTableId("127");
        super.initialize();
    }
    
    public void initializeColumnDescriptors()
    {
        super.initializeColumnDescriptors();
        addColumnDescriptor(new ColumnDescriptor(this,"getCompanyCode","setCompanyCode","COMPANY_CODE,1,1,3,0,true|A,0,CHAR,1,null,null,null,null,null|H,0,CHAR,2,null,null,null,null,null|T,0,CHAR,2,null,null,null,null,null|U,0,CHAR,1,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getProductPrefix","setProductPrefix","PRODUCT_PREFIX,1,2,1,0,true|A,0,CHAR,1,null,null,null,null,null|H,0,CHAR,2,null,null,null,null,null|T,0,CHAR,2,null,null,null,null,null|U,0,CHAR,1,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getTableSubset","setTableSubset","TABLE_SUBSET,1,3,16,0,true|A,0,CHAR,1,null,null,null,null,null|H,0,CHAR,2,null,null,null,null,null|T,0,CHAR,2,null,null,null,null,null|U,0,CHAR,1,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getReplacementInd","setReplacementInd","REPLACEMENT_IND,1,4,1,0,true|A,0,CHAR,0,null,null,null,null,null|H,0,CHAR,2,null,null,null,null,null|T,0,CHAR,2,null,null,null,null,null|U,0,CHAR,2,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getEffectiveDate","setEffectiveDate","EFFECTIVE_DATE,91,5,10,0,true|A,0,DATE,0,null,null,null,null,null|H,0,DATE,2,null,null,null,null,null|T,0,DATE,2,null,null,null,null,null|U,0,DATE,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getIssueAge","setIssueAge","ISSUE_AGE,3,6,3,0,true|A,0,INTEGER,0,null,null,null,null,null|H,0,INTEGER,2,null,null,null,null,null|T,0,INTEGER,2,null,null,null,null,null|U,0,INTEGER,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getAnnuityType","setAnnuityType","ANNUITY_TYPE,1,7,1,0,true|A,0,CHAR,0,null,null,null,null,null|H,0,CHAR,2,null,null,null,null,null|T,0,CHAR,2,null,null,null,null,null|U,0,CHAR,2,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getNoElapsedDays","setNoElapsedDays","NO_ELAPSED_DAYS,3,8,3,0,true|A,0,INTEGER,0,null,null,null,null,null|H,0,INTEGER,2,null,null,null,null,null|T,0,INTEGER,2,null,null,null,null,null|U,0,INTEGER,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getMemoCode","setMemoCode","MEMO_CODE,1,9,2,0,true|A,0,CHAR,4,null,null,null,null,null|H,0,CHAR,2,null,null,null,null,null|T,0,CHAR,2,null,null,null,null,null|U,0,CHAR,4,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getFreeLookSurInd","setFreeLookSurInd","FREE_LOOK_SUR_IND,1,10,1,0,false|A,0,CHAR,0,null,null,null,null,null|H,0,CHAR,2,null,null,null,null,null|T,0,CHAR,2,null,null,null,null,null|U,0,CHAR,0,null,null,null,null,null"));
    }
    
    public String getPagingSQL(String schemaName,boolean isSubsetMode,boolean isNext, boolean locator)
    {
        String pagingWhere = null;
        String order = null;
        if (isNext) {
            if (isSubsetMode)
                if (locator)
                    pagingWhere = ".T127X WHERE (COMPANY_CODE = :company_code) AND (PRODUCT_PREFIX = :product_prefix) AND (TABLE_SUBSET = :table_subset) AND ((REPLACEMENT_IND > :replacement_ind) OR (REPLACEMENT_IND = :replacement_ind AND EFFECTIVE_DATE > :effective_date) OR (REPLACEMENT_IND = :replacement_ind AND EFFECTIVE_DATE = :effective_date AND ISSUE_AGE > :issue_age) OR (REPLACEMENT_IND = :replacement_ind AND EFFECTIVE_DATE = :effective_date AND ISSUE_AGE = :issue_age AND ANNUITY_TYPE > :annuity_type) OR (REPLACEMENT_IND = :replacement_ind AND EFFECTIVE_DATE = :effective_date AND ISSUE_AGE = :issue_age AND ANNUITY_TYPE = :annuity_type AND NO_ELAPSED_DAYS > :no_elapsed_days) OR (REPLACEMENT_IND = :replacement_ind AND EFFECTIVE_DATE = :effective_date AND ISSUE_AGE = :issue_age AND ANNUITY_TYPE = :annuity_type AND NO_ELAPSED_DAYS = :no_elapsed_days AND MEMO_CODE > :memo_code) OR (REPLACEMENT_IND = :replacement_ind AND EFFECTIVE_DATE = :effective_date AND ISSUE_AGE = :issue_age AND ANNUITY_TYPE = :annuity_type AND NO_ELAPSED_DAYS = :no_elapsed_days AND MEMO_CODE = :memo_code)) ";
                else 
                    pagingWhere = ".T127X WHERE (COMPANY_CODE = :company_code) AND (PRODUCT_PREFIX = :product_prefix) AND (TABLE_SUBSET = :table_subset) AND ((REPLACEMENT_IND > :replacement_ind) OR (REPLACEMENT_IND = :replacement_ind AND EFFECTIVE_DATE > :effective_date) OR (REPLACEMENT_IND = :replacement_ind AND EFFECTIVE_DATE = :effective_date AND ISSUE_AGE > :issue_age) OR (REPLACEMENT_IND = :replacement_ind AND EFFECTIVE_DATE = :effective_date AND ISSUE_AGE = :issue_age AND ANNUITY_TYPE > :annuity_type) OR (REPLACEMENT_IND = :replacement_ind AND EFFECTIVE_DATE = :effective_date AND ISSUE_AGE = :issue_age AND ANNUITY_TYPE = :annuity_type AND NO_ELAPSED_DAYS > :no_elapsed_days) OR (REPLACEMENT_IND = :replacement_ind AND EFFECTIVE_DATE = :effective_date AND ISSUE_AGE = :issue_age AND ANNUITY_TYPE = :annuity_type AND NO_ELAPSED_DAYS = :no_elapsed_days AND MEMO_CODE > :memo_code)) ";
            else
                if (locator)
                    pagingWhere = ".T127X WHERE (COMPANY_CODE = :company_code) AND ((PRODUCT_PREFIX > :product_prefix) OR (PRODUCT_PREFIX = :product_prefix AND TABLE_SUBSET > :table_subset) OR (PRODUCT_PREFIX = :product_prefix AND TABLE_SUBSET = :table_subset AND REPLACEMENT_IND > :replacement_ind) OR (PRODUCT_PREFIX = :product_prefix AND TABLE_SUBSET = :table_subset AND REPLACEMENT_IND = :replacement_ind AND EFFECTIVE_DATE > :effective_date) OR (PRODUCT_PREFIX = :product_prefix AND TABLE_SUBSET = :table_subset AND REPLACEMENT_IND = :replacement_ind AND EFFECTIVE_DATE = :effective_date AND ISSUE_AGE > :issue_age) OR (PRODUCT_PREFIX = :product_prefix AND TABLE_SUBSET = :table_subset AND REPLACEMENT_IND = :replacement_ind AND EFFECTIVE_DATE = :effective_date AND ISSUE_AGE = :issue_age AND ANNUITY_TYPE > :annuity_type) OR (PRODUCT_PREFIX = :product_prefix AND TABLE_SUBSET = :table_subset AND REPLACEMENT_IND = :replacement_ind AND EFFECTIVE_DATE = :effective_date AND ISSUE_AGE = :issue_age AND ANNUITY_TYPE = :annuity_type AND NO_ELAPSED_DAYS > :no_elapsed_days) OR (PRODUCT_PREFIX = :product_prefix AND TABLE_SUBSET = :table_subset AND REPLACEMENT_IND = :replacement_ind AND EFFECTIVE_DATE = :effective_date AND ISSUE_AGE = :issue_age AND ANNUITY_TYPE = :annuity_type AND NO_ELAPSED_DAYS = :no_elapsed_days AND MEMO_CODE > :memo_code) OR (PRODUCT_PREFIX = :product_prefix AND TABLE_SUBSET = :table_subset AND REPLACEMENT_IND = :replacement_ind AND EFFECTIVE_DATE = :effective_date AND ISSUE_AGE = :issue_age AND ANNUITY_TYPE = :annuity_type AND NO_ELAPSED_DAYS = :no_elapsed_days AND MEMO_CODE = :memo_code)) ";
                else
                    pagingWhere = ".T127X WHERE (COMPANY_CODE = :company_code) AND ((PRODUCT_PREFIX > :product_prefix) OR (PRODUCT_PREFIX = :product_prefix AND TABLE_SUBSET > :table_subset) OR (PRODUCT_PREFIX = :product_prefix AND TABLE_SUBSET = :table_subset AND REPLACEMENT_IND > :replacement_ind) OR (PRODUCT_PREFIX = :product_prefix AND TABLE_SUBSET = :table_subset AND REPLACEMENT_IND = :replacement_ind AND EFFECTIVE_DATE > :effective_date) OR (PRODUCT_PREFIX = :product_prefix AND TABLE_SUBSET = :table_subset AND REPLACEMENT_IND = :replacement_ind AND EFFECTIVE_DATE = :effective_date AND ISSUE_AGE > :issue_age) OR (PRODUCT_PREFIX = :product_prefix AND TABLE_SUBSET = :table_subset AND REPLACEMENT_IND = :replacement_ind AND EFFECTIVE_DATE = :effective_date AND ISSUE_AGE = :issue_age AND ANNUITY_TYPE > :annuity_type) OR (PRODUCT_PREFIX = :product_prefix AND TABLE_SUBSET = :table_subset AND REPLACEMENT_IND = :replacement_ind AND EFFECTIVE_DATE = :effective_date AND ISSUE_AGE = :issue_age AND ANNUITY_TYPE = :annuity_type AND NO_ELAPSED_DAYS > :no_elapsed_days) OR (PRODUCT_PREFIX = :product_prefix AND TABLE_SUBSET = :table_subset AND REPLACEMENT_IND = :replacement_ind AND EFFECTIVE_DATE = :effective_date AND ISSUE_AGE = :issue_age AND ANNUITY_TYPE = :annuity_type AND NO_ELAPSED_DAYS = :no_elapsed_days AND MEMO_CODE > :memo_code)) ";
            order = " ORDER BY COMPANY_CODE, PRODUCT_PREFIX, TABLE_SUBSET, REPLACEMENT_IND, EFFECTIVE_DATE, ISSUE_AGE, ANNUITY_TYPE, NO_ELAPSED_DAYS, MEMO_CODE";
        }
        else {
            if (isSubsetMode)
                pagingWhere = ".T127X WHERE (COMPANY_CODE = :company_code) AND (PRODUCT_PREFIX = :product_prefix) AND (TABLE_SUBSET = :table_subset) AND ((REPLACEMENT_IND < :replacement_ind) OR (REPLACEMENT_IND = :replacement_ind AND EFFECTIVE_DATE < :effective_date) OR (REPLACEMENT_IND = :replacement_ind AND EFFECTIVE_DATE = :effective_date AND ISSUE_AGE < :issue_age) OR (REPLACEMENT_IND = :replacement_ind AND EFFECTIVE_DATE = :effective_date AND ISSUE_AGE = :issue_age AND ANNUITY_TYPE < :annuity_type) OR (REPLACEMENT_IND = :replacement_ind AND EFFECTIVE_DATE = :effective_date AND ISSUE_AGE = :issue_age AND ANNUITY_TYPE = :annuity_type AND NO_ELAPSED_DAYS < :no_elapsed_days) OR (REPLACEMENT_IND = :replacement_ind AND EFFECTIVE_DATE = :effective_date AND ISSUE_AGE = :issue_age AND ANNUITY_TYPE = :annuity_type AND NO_ELAPSED_DAYS = :no_elapsed_days AND MEMO_CODE < :memo_code)) ";
            else
                pagingWhere = ".T127X WHERE (COMPANY_CODE = :company_code) AND ((PRODUCT_PREFIX < :product_prefix) OR (PRODUCT_PREFIX = :product_prefix AND TABLE_SUBSET < :table_subset) OR (PRODUCT_PREFIX = :product_prefix AND TABLE_SUBSET = :table_subset AND REPLACEMENT_IND < :replacement_ind) OR (PRODUCT_PREFIX = :product_prefix AND TABLE_SUBSET = :table_subset AND REPLACEMENT_IND = :replacement_ind AND EFFECTIVE_DATE < :effective_date) OR (PRODUCT_PREFIX = :product_prefix AND TABLE_SUBSET = :table_subset AND REPLACEMENT_IND = :replacement_ind AND EFFECTIVE_DATE = :effective_date AND ISSUE_AGE < :issue_age) OR (PRODUCT_PREFIX = :product_prefix AND TABLE_SUBSET = :table_subset AND REPLACEMENT_IND = :replacement_ind AND EFFECTIVE_DATE = :effective_date AND ISSUE_AGE = :issue_age AND ANNUITY_TYPE < :annuity_type) OR (PRODUCT_PREFIX = :product_prefix AND TABLE_SUBSET = :table_subset AND REPLACEMENT_IND = :replacement_ind AND EFFECTIVE_DATE = :effective_date AND ISSUE_AGE = :issue_age AND ANNUITY_TYPE = :annuity_type AND NO_ELAPSED_DAYS < :no_elapsed_days) OR (PRODUCT_PREFIX = :product_prefix AND TABLE_SUBSET = :table_subset AND REPLACEMENT_IND = :replacement_ind AND EFFECTIVE_DATE = :effective_date AND ISSUE_AGE = :issue_age AND ANNUITY_TYPE = :annuity_type AND NO_ELAPSED_DAYS = :no_elapsed_days AND MEMO_CODE < :memo_code)) ";
            order = " ORDER BY COMPANY_CODE DESC, PRODUCT_PREFIX DESC, TABLE_SUBSET DESC, REPLACEMENT_IND DESC, EFFECTIVE_DATE DESC, ISSUE_AGE DESC, ANNUITY_TYPE DESC, NO_ELAPSED_DAYS DESC, MEMO_CODE DESC";
        }
        return pagingSql + schemaName + pagingWhere + order;
    }
    
    public String prepareInsertStmt(String schemaName) {
        StringBuffer sb = new StringBuffer();
        sb.append("INSERT INTO ").append(schemaName);
        sb.append(".T127X ( ");
        sb.append("COMPANY_CODE, PRODUCT_PREFIX, TABLE_SUBSET, REPLACEMENT_IND, EFFECTIVE_DATE, ISSUE_AGE, ANNUITY_TYPE, NO_ELAPSED_DAYS, MEMO_CODE, FREE_LOOK_SUR_IND )");
        sb.append(" VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ? )");
        return sb.toString();
    }
    
    public String prepareUpdateStmt(String schemaName)
    {
        StringBuffer sb = new StringBuffer();
        sb.append("UPDATE ").append(schemaName);
        sb.append(".T127X ");
        sb.append(" SET COMPANY_CODE = ?, PRODUCT_PREFIX = ?, TABLE_SUBSET = ?, REPLACEMENT_IND = ?, EFFECTIVE_DATE = ?, ISSUE_AGE = ?, ANNUITY_TYPE = ?, NO_ELAPSED_DAYS = ?, MEMO_CODE = ?, FREE_LOOK_SUR_IND = ?");
        sb.append(" WHERE COMPANY_CODE = ? AND PRODUCT_PREFIX = ? AND TABLE_SUBSET = ? AND REPLACEMENT_IND = ? AND EFFECTIVE_DATE = ? AND ISSUE_AGE = ? AND ANNUITY_TYPE = ? AND NO_ELAPSED_DAYS = ? AND MEMO_CODE = ?");
        return sb.toString();
    }
    
    public String prepareDeleteStmt(String schemaName)
    {
        StringBuffer sb = new StringBuffer();
        sb.append("DELETE FROM ").append(schemaName);
        sb.append(".T127X ");
        sb.append(" WHERE COMPANY_CODE = ? AND PRODUCT_PREFIX = ? AND TABLE_SUBSET = ? AND REPLACEMENT_IND = ? AND EFFECTIVE_DATE = ? AND ISSUE_AGE = ? AND ANNUITY_TYPE = ? AND NO_ELAPSED_DAYS = ? AND MEMO_CODE = ?");
        return sb.toString();
    }
}
