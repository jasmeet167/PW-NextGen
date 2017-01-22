package com.csc.fsg.life.pw.web.io.descriptor.wma;

import com.csc.fsg.life.pw.web.io.*;

public class TW71XDescriptor
    extends TableDescriptor
{
    private static final String pagingSql = "SELECT COMPANY_CODE, PRODUCT_PREFIX, TABLE_SUBSET, STATUTORY_CODE, LOB_CODE, EFFECTIVE_DATE, ISSUE_AGE, MIN_DEFER_PD, MAX_DEFER_PD, NUM_OF_ADJUSTMENTS, MAX_ADJUST_YEARS, MAX_NUM_ACCEL_PYMTS FROM ";
    
    public void initialize()
    {
        setRowClass(TW71XRow.class);
        setTableName("TW71X");
        setTableId("W71");
        super.initialize();
    }
    
    public void initializeColumnDescriptors()
    {
        super.initializeColumnDescriptors();
        addColumnDescriptor(new ColumnDescriptor(this,"getCompanyCode","setCompanyCode","COMPANY_CODE,1,1,3,0,true|,0,CHAR,1,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getProductPrefix","setProductPrefix","PRODUCT_PREFIX,1,2,1,0,true|,0,CHAR,1,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getTableSubset","setTableSubset","TABLE_SUBSET,1,3,16,0,true|,0,CHAR,1,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getStatutoryCode","setStatutoryCode","STATUTORY_CODE,1,4,3,0,true|,0,CHAR,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getLobCode","setLobCode","LOB_CODE,1,5,3,0,true|,0,CHAR,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getEffectiveDate","setEffectiveDate","EFFECTIVE_DATE,91,6,10,0,true|,0,DATE,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getIssueAge","setIssueAge","ISSUE_AGE,5,7,3,0,true|,0,INTEGER,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getMinDeferPd","setMinDeferPd","MIN_DEFER_PD,5,8,3,0,false|,0,INTEGER,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getMaxDeferPd","setMaxDeferPd","MAX_DEFER_PD,5,9,3,0,false|,0,INTEGER,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getNumOfAdjustments","setNumOfAdjustments","NUM_OF_ADJUSTMENTS,5,10,3,0,false|,0,INTEGER,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getMaxAdjustYears","setMaxAdjustYears","MAX_ADJUST_YEARS,5,11,3,0,false|,0,INTEGER,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getMaxNumAccelPymts","setMaxNumAccelPymts","MAX_NUM_ACCEL_PYMTS,5,12,3,0,false|,0,INTEGER,0,null,null,null,null,null"));
    }
    
    public String getPagingSQL(String schemaName,boolean isSubsetMode,boolean isNext, boolean locator)
    {
        String pagingWhere = null;
        String order = null;
        if (isNext) {
            if (isSubsetMode)
                if (locator)
                    pagingWhere = ".TW71X WHERE (COMPANY_CODE = :company_code) AND (PRODUCT_PREFIX = :product_prefix) AND (TABLE_SUBSET = :table_subset) AND ((STATUTORY_CODE > :statutory_code) OR (STATUTORY_CODE = :statutory_code AND LOB_CODE > :lob_code) OR (STATUTORY_CODE = :statutory_code AND LOB_CODE = :lob_code AND EFFECTIVE_DATE > :effective_date) OR (STATUTORY_CODE = :statutory_code AND LOB_CODE = :lob_code AND EFFECTIVE_DATE = :effective_date AND ISSUE_AGE > :issue_age) OR (STATUTORY_CODE = :statutory_code AND LOB_CODE = :lob_code AND EFFECTIVE_DATE = :effective_date AND ISSUE_AGE = :issue_age)) ";
                else 
                    pagingWhere = ".TW71X WHERE (COMPANY_CODE = :company_code) AND (PRODUCT_PREFIX = :product_prefix) AND (TABLE_SUBSET = :table_subset) AND ((STATUTORY_CODE > :statutory_code) OR (STATUTORY_CODE = :statutory_code AND LOB_CODE > :lob_code) OR (STATUTORY_CODE = :statutory_code AND LOB_CODE = :lob_code AND EFFECTIVE_DATE > :effective_date) OR (STATUTORY_CODE = :statutory_code AND LOB_CODE = :lob_code AND EFFECTIVE_DATE = :effective_date AND ISSUE_AGE > :issue_age)) ";
            else
                if (locator)
                    pagingWhere = ".TW71X WHERE (COMPANY_CODE = :company_code) AND ((PRODUCT_PREFIX > :product_prefix) OR (PRODUCT_PREFIX = :product_prefix AND TABLE_SUBSET > :table_subset) OR (PRODUCT_PREFIX = :product_prefix AND TABLE_SUBSET = :table_subset AND STATUTORY_CODE > :statutory_code) OR (PRODUCT_PREFIX = :product_prefix AND TABLE_SUBSET = :table_subset AND STATUTORY_CODE = :statutory_code AND LOB_CODE > :lob_code) OR (PRODUCT_PREFIX = :product_prefix AND TABLE_SUBSET = :table_subset AND STATUTORY_CODE = :statutory_code AND LOB_CODE = :lob_code AND EFFECTIVE_DATE > :effective_date) OR (PRODUCT_PREFIX = :product_prefix AND TABLE_SUBSET = :table_subset AND STATUTORY_CODE = :statutory_code AND LOB_CODE = :lob_code AND EFFECTIVE_DATE = :effective_date AND ISSUE_AGE > :issue_age) OR (PRODUCT_PREFIX = :product_prefix AND TABLE_SUBSET = :table_subset AND STATUTORY_CODE = :statutory_code AND LOB_CODE = :lob_code AND EFFECTIVE_DATE = :effective_date AND ISSUE_AGE = :issue_age)) ";
                else
                    pagingWhere = ".TW71X WHERE (COMPANY_CODE = :company_code) AND ((PRODUCT_PREFIX > :product_prefix) OR (PRODUCT_PREFIX = :product_prefix AND TABLE_SUBSET > :table_subset) OR (PRODUCT_PREFIX = :product_prefix AND TABLE_SUBSET = :table_subset AND STATUTORY_CODE > :statutory_code) OR (PRODUCT_PREFIX = :product_prefix AND TABLE_SUBSET = :table_subset AND STATUTORY_CODE = :statutory_code AND LOB_CODE > :lob_code) OR (PRODUCT_PREFIX = :product_prefix AND TABLE_SUBSET = :table_subset AND STATUTORY_CODE = :statutory_code AND LOB_CODE = :lob_code AND EFFECTIVE_DATE > :effective_date) OR (PRODUCT_PREFIX = :product_prefix AND TABLE_SUBSET = :table_subset AND STATUTORY_CODE = :statutory_code AND LOB_CODE = :lob_code AND EFFECTIVE_DATE = :effective_date AND ISSUE_AGE > :issue_age)) ";
            order = " ORDER BY COMPANY_CODE, PRODUCT_PREFIX, TABLE_SUBSET, STATUTORY_CODE, LOB_CODE, EFFECTIVE_DATE, ISSUE_AGE";
        }
        else {
            if (isSubsetMode)
                pagingWhere = ".TW71X WHERE (COMPANY_CODE = :company_code) AND (PRODUCT_PREFIX = :product_prefix) AND (TABLE_SUBSET = :table_subset) AND ((STATUTORY_CODE < :statutory_code) OR (STATUTORY_CODE = :statutory_code AND LOB_CODE < :lob_code) OR (STATUTORY_CODE = :statutory_code AND LOB_CODE = :lob_code AND EFFECTIVE_DATE < :effective_date) OR (STATUTORY_CODE = :statutory_code AND LOB_CODE = :lob_code AND EFFECTIVE_DATE = :effective_date AND ISSUE_AGE < :issue_age)) ";
            else
                pagingWhere = ".TW71X WHERE (COMPANY_CODE = :company_code) AND ((PRODUCT_PREFIX < :product_prefix) OR (PRODUCT_PREFIX = :product_prefix AND TABLE_SUBSET < :table_subset) OR (PRODUCT_PREFIX = :product_prefix AND TABLE_SUBSET = :table_subset AND STATUTORY_CODE < :statutory_code) OR (PRODUCT_PREFIX = :product_prefix AND TABLE_SUBSET = :table_subset AND STATUTORY_CODE = :statutory_code AND LOB_CODE < :lob_code) OR (PRODUCT_PREFIX = :product_prefix AND TABLE_SUBSET = :table_subset AND STATUTORY_CODE = :statutory_code AND LOB_CODE = :lob_code AND EFFECTIVE_DATE < :effective_date) OR (PRODUCT_PREFIX = :product_prefix AND TABLE_SUBSET = :table_subset AND STATUTORY_CODE = :statutory_code AND LOB_CODE = :lob_code AND EFFECTIVE_DATE = :effective_date AND ISSUE_AGE < :issue_age)) ";
            order = " ORDER BY COMPANY_CODE DESC, PRODUCT_PREFIX DESC, TABLE_SUBSET DESC, STATUTORY_CODE DESC, LOB_CODE DESC, EFFECTIVE_DATE DESC, ISSUE_AGE DESC";
        }
        return pagingSql + schemaName + pagingWhere + order;
    }
    
    public String prepareInsertStmt(String schemaName) {
        StringBuffer sb = new StringBuffer();
        sb.append("INSERT INTO ").append(schemaName);
        sb.append(".TW71X ( ");
        sb.append("COMPANY_CODE, PRODUCT_PREFIX, TABLE_SUBSET, STATUTORY_CODE, LOB_CODE, EFFECTIVE_DATE, ISSUE_AGE, MIN_DEFER_PD, MAX_DEFER_PD, NUM_OF_ADJUSTMENTS, MAX_ADJUST_YEARS, MAX_NUM_ACCEL_PYMTS )");
        sb.append(" VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ? )");
        return sb.toString();
    }
    
    public String prepareUpdateStmt(String schemaName)
    {
        StringBuffer sb = new StringBuffer();
        sb.append("UPDATE ").append(schemaName);
        sb.append(".TW71X ");
        sb.append(" SET COMPANY_CODE = ?, PRODUCT_PREFIX = ?, TABLE_SUBSET = ?, STATUTORY_CODE = ?, LOB_CODE = ?, EFFECTIVE_DATE = ?, ISSUE_AGE = ?, MIN_DEFER_PD = ?, MAX_DEFER_PD = ?, NUM_OF_ADJUSTMENTS = ?, MAX_ADJUST_YEARS = ?, MAX_NUM_ACCEL_PYMTS = ?");
        sb.append(" WHERE COMPANY_CODE = ? AND PRODUCT_PREFIX = ? AND TABLE_SUBSET = ? AND STATUTORY_CODE = ? AND LOB_CODE = ? AND EFFECTIVE_DATE = ? AND ISSUE_AGE = ?");
        return sb.toString();
    }
    
    public String prepareDeleteStmt(String schemaName)
    {
        StringBuffer sb = new StringBuffer();
        sb.append("DELETE FROM ").append(schemaName);
        sb.append(".TW71X ");
        sb.append(" WHERE COMPANY_CODE = ? AND PRODUCT_PREFIX = ? AND TABLE_SUBSET = ? AND STATUTORY_CODE = ? AND LOB_CODE = ? AND EFFECTIVE_DATE = ? AND ISSUE_AGE = ?");
        return sb.toString();
    }
}
