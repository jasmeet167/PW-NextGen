package com.csc.fsg.life.pw.io.descriptor.wma;

import com.csc.fsg.life.pw.io.*;

public class T026XDescriptor
    extends TableDescriptor
{
    private static final String pagingSql = "SELECT COMPANY_CODE, PRODUCT_PREFIX, TABLE_SUBSET, ISSUE_STATE, EFFECTIVE_DATE, MAXIMUM_DURATION, MX_CAL_YY, GUAR_INT_RT FROM ";
    
    public void initialize()
    {
        setRowClass(T026XRow.class);
        setTableName("T026X");
        setTableId("026");
        super.initialize();
    }
    
    public void initializeColumnDescriptors()
    {
        super.initializeColumnDescriptors();
        addColumnDescriptor(new ColumnDescriptor(this,"getCompanyCode","setCompanyCode","COMPANY_CODE,1,1,3,0,true|A,0,CHAR,1,null,null,null,null,null|U,0,CHAR,1,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getProductPrefix","setProductPrefix","PRODUCT_PREFIX,1,2,1,0,true|A,0,CHAR,1,null,null,null,null,null|U,0,CHAR,1,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getTableSubset","setTableSubset","TABLE_SUBSET,1,3,16,0,true|A,0,CHAR,1,null,null,null,null,null|U,0,CHAR,1,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getIssueState","setIssueState","ISSUE_STATE,1,4,3,0,true|A,0,CHAR,0,null,null,null,null,null|U,0,CHAR,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getEffectiveDate","setEffectiveDate","EFFECTIVE_DATE,91,5,10,0,true|A,0,DATE,0,null,null,null,null,null|U,0,DATE,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getMaximumDuration","setMaximumDuration","MAXIMUM_DURATION,3,6,3,0,true|A,0,INTEGER,0,null,null,null,null,null|U,0,INTEGER,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getMxCalYy","setMxCalYy","MX_CAL_YY,3,7,5,0,true|A,0,INTEGER,2,null,null,null,null,null|U,0,INTEGER,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getGuarIntRt","setGuarIntRt","GUAR_INT_RT,3,8,5,3,false|A,0,DOUBLE,0,null,null,null,null,null|U,0,DOUBLE,0,null,null,null,null,null"));
    }
    
    public String getPagingSQL(String schemaName,boolean isSubsetMode,boolean isNext, boolean locator)
    {
        String pagingWhere = null;
        String order = null;
        if (isNext) {
            if (isSubsetMode)
                if (locator)
                    pagingWhere = ".T026X WHERE (COMPANY_CODE = :company_code) AND (PRODUCT_PREFIX = :product_prefix) AND (TABLE_SUBSET = :table_subset) AND ((ISSUE_STATE > :issue_state) OR (ISSUE_STATE = :issue_state AND EFFECTIVE_DATE > :effective_date) OR (ISSUE_STATE = :issue_state AND EFFECTIVE_DATE = :effective_date AND MAXIMUM_DURATION > :maximum_duration) OR (ISSUE_STATE = :issue_state AND EFFECTIVE_DATE = :effective_date AND MAXIMUM_DURATION = :maximum_duration AND MX_CAL_YY > :mx_cal_yy) OR (ISSUE_STATE = :issue_state AND EFFECTIVE_DATE = :effective_date AND MAXIMUM_DURATION = :maximum_duration AND MX_CAL_YY = :mx_cal_yy)) ";
                else 
                    pagingWhere = ".T026X WHERE (COMPANY_CODE = :company_code) AND (PRODUCT_PREFIX = :product_prefix) AND (TABLE_SUBSET = :table_subset) AND ((ISSUE_STATE > :issue_state) OR (ISSUE_STATE = :issue_state AND EFFECTIVE_DATE > :effective_date) OR (ISSUE_STATE = :issue_state AND EFFECTIVE_DATE = :effective_date AND MAXIMUM_DURATION > :maximum_duration) OR (ISSUE_STATE = :issue_state AND EFFECTIVE_DATE = :effective_date AND MAXIMUM_DURATION = :maximum_duration AND MX_CAL_YY > :mx_cal_yy)) ";
            else
                if (locator)
                    pagingWhere = ".T026X WHERE (COMPANY_CODE = :company_code) AND ((PRODUCT_PREFIX > :product_prefix) OR (PRODUCT_PREFIX = :product_prefix AND TABLE_SUBSET > :table_subset) OR (PRODUCT_PREFIX = :product_prefix AND TABLE_SUBSET = :table_subset AND ISSUE_STATE > :issue_state) OR (PRODUCT_PREFIX = :product_prefix AND TABLE_SUBSET = :table_subset AND ISSUE_STATE = :issue_state AND EFFECTIVE_DATE > :effective_date) OR (PRODUCT_PREFIX = :product_prefix AND TABLE_SUBSET = :table_subset AND ISSUE_STATE = :issue_state AND EFFECTIVE_DATE = :effective_date AND MAXIMUM_DURATION > :maximum_duration) OR (PRODUCT_PREFIX = :product_prefix AND TABLE_SUBSET = :table_subset AND ISSUE_STATE = :issue_state AND EFFECTIVE_DATE = :effective_date AND MAXIMUM_DURATION = :maximum_duration AND MX_CAL_YY > :mx_cal_yy) OR (PRODUCT_PREFIX = :product_prefix AND TABLE_SUBSET = :table_subset AND ISSUE_STATE = :issue_state AND EFFECTIVE_DATE = :effective_date AND MAXIMUM_DURATION = :maximum_duration AND MX_CAL_YY = :mx_cal_yy)) ";
                else
                    pagingWhere = ".T026X WHERE (COMPANY_CODE = :company_code) AND ((PRODUCT_PREFIX > :product_prefix) OR (PRODUCT_PREFIX = :product_prefix AND TABLE_SUBSET > :table_subset) OR (PRODUCT_PREFIX = :product_prefix AND TABLE_SUBSET = :table_subset AND ISSUE_STATE > :issue_state) OR (PRODUCT_PREFIX = :product_prefix AND TABLE_SUBSET = :table_subset AND ISSUE_STATE = :issue_state AND EFFECTIVE_DATE > :effective_date) OR (PRODUCT_PREFIX = :product_prefix AND TABLE_SUBSET = :table_subset AND ISSUE_STATE = :issue_state AND EFFECTIVE_DATE = :effective_date AND MAXIMUM_DURATION > :maximum_duration) OR (PRODUCT_PREFIX = :product_prefix AND TABLE_SUBSET = :table_subset AND ISSUE_STATE = :issue_state AND EFFECTIVE_DATE = :effective_date AND MAXIMUM_DURATION = :maximum_duration AND MX_CAL_YY > :mx_cal_yy)) ";
            order = " ORDER BY COMPANY_CODE, PRODUCT_PREFIX, TABLE_SUBSET, ISSUE_STATE, EFFECTIVE_DATE, MAXIMUM_DURATION, MX_CAL_YY";
        }
        else {
            if (isSubsetMode)
                pagingWhere = ".T026X WHERE (COMPANY_CODE = :company_code) AND (PRODUCT_PREFIX = :product_prefix) AND (TABLE_SUBSET = :table_subset) AND ((ISSUE_STATE < :issue_state) OR (ISSUE_STATE = :issue_state AND EFFECTIVE_DATE < :effective_date) OR (ISSUE_STATE = :issue_state AND EFFECTIVE_DATE = :effective_date AND MAXIMUM_DURATION < :maximum_duration) OR (ISSUE_STATE = :issue_state AND EFFECTIVE_DATE = :effective_date AND MAXIMUM_DURATION = :maximum_duration AND MX_CAL_YY < :mx_cal_yy)) ";
            else
                pagingWhere = ".T026X WHERE (COMPANY_CODE = :company_code) AND ((PRODUCT_PREFIX < :product_prefix) OR (PRODUCT_PREFIX = :product_prefix AND TABLE_SUBSET < :table_subset) OR (PRODUCT_PREFIX = :product_prefix AND TABLE_SUBSET = :table_subset AND ISSUE_STATE < :issue_state) OR (PRODUCT_PREFIX = :product_prefix AND TABLE_SUBSET = :table_subset AND ISSUE_STATE = :issue_state AND EFFECTIVE_DATE < :effective_date) OR (PRODUCT_PREFIX = :product_prefix AND TABLE_SUBSET = :table_subset AND ISSUE_STATE = :issue_state AND EFFECTIVE_DATE = :effective_date AND MAXIMUM_DURATION < :maximum_duration) OR (PRODUCT_PREFIX = :product_prefix AND TABLE_SUBSET = :table_subset AND ISSUE_STATE = :issue_state AND EFFECTIVE_DATE = :effective_date AND MAXIMUM_DURATION = :maximum_duration AND MX_CAL_YY < :mx_cal_yy)) ";
            order = " ORDER BY COMPANY_CODE DESC, PRODUCT_PREFIX DESC, TABLE_SUBSET DESC, ISSUE_STATE DESC, EFFECTIVE_DATE DESC, MAXIMUM_DURATION DESC, MX_CAL_YY DESC";
        }
        return pagingSql + schemaName + pagingWhere + order;
    }
    
    public String prepareInsertStmt(String schemaName) {
        StringBuffer sb = new StringBuffer();
        sb.append("INSERT INTO ").append(schemaName);
        sb.append(".T026X ( ");
        sb.append("COMPANY_CODE, PRODUCT_PREFIX, TABLE_SUBSET, ISSUE_STATE, EFFECTIVE_DATE, MAXIMUM_DURATION, MX_CAL_YY, GUAR_INT_RT )");
        sb.append(" VALUES (?, ?, ?, ?, ?, ?, ?, ? )");
        return sb.toString();
    }
    
    public String prepareUpdateStmt(String schemaName)
    {
        StringBuffer sb = new StringBuffer();
        sb.append("UPDATE ").append(schemaName);
        sb.append(".T026X ");
        sb.append(" SET COMPANY_CODE = ?, PRODUCT_PREFIX = ?, TABLE_SUBSET = ?, ISSUE_STATE = ?, EFFECTIVE_DATE = ?, MAXIMUM_DURATION = ?, MX_CAL_YY = ?, GUAR_INT_RT = ?");
        sb.append(" WHERE COMPANY_CODE = ? AND PRODUCT_PREFIX = ? AND TABLE_SUBSET = ? AND ISSUE_STATE = ? AND EFFECTIVE_DATE = ? AND MAXIMUM_DURATION = ? AND MX_CAL_YY = ?");
        return sb.toString();
    }
    
    public String prepareDeleteStmt(String schemaName)
    {
        StringBuffer sb = new StringBuffer();
        sb.append("DELETE FROM ").append(schemaName);
        sb.append(".T026X ");
        sb.append(" WHERE COMPANY_CODE = ? AND PRODUCT_PREFIX = ? AND TABLE_SUBSET = ? AND ISSUE_STATE = ? AND EFFECTIVE_DATE = ? AND MAXIMUM_DURATION = ? AND MX_CAL_YY = ?");
        return sb.toString();
    }
}
