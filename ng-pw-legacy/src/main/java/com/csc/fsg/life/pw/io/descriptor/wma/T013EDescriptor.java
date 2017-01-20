package com.csc.fsg.life.pw.io.descriptor.wma;

import com.csc.fsg.life.pw.io.*;

public class T013EDescriptor
    extends TableDescriptor
{
    private static final String pagingSql = "SELECT COMPANY_CODE, TABLE_SUBSET, EFFECTIVE_DATE, MAX_ISSUE_AGE, MAX_POL_DURATION, MIN_FACE_AMT FROM ";
    
    public void initialize()
    {
        setRowClass(T013ERow.class);
        setTableName("T013E");
        setTableId("013");
        super.initialize();
    }
    
    public void initializeColumnDescriptors()
    {
        super.initializeColumnDescriptors();
        addColumnDescriptor(new ColumnDescriptor(this,"getCompanyCode","setCompanyCode","COMPANY_CODE,1,1,3,0,true|,0,CHAR,1,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getTableSubset","setTableSubset","TABLE_SUBSET,1,2,16,0,true|,0,CHAR,1,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getEffectiveDate","setEffectiveDate","EFFECTIVE_DATE,91,3,10,0,true|,0,DATE,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getMaxIssueAge","setMaxIssueAge","MAX_ISSUE_AGE,3,4,3,0,true|,0,INTEGER,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getMaxPolDuration","setMaxPolDuration","MAX_POL_DURATION,3,5,3,0,true|,0,INTEGER,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getMinFaceAmt","setMinFaceAmt","MIN_FACE_AMT,3,6,11,5,false|,0,DOUBLE,0,null,null,null,null,null"));
    }
    
    public String getPagingSQL(String schemaName,boolean isSubsetMode,boolean isNext, boolean locator)
    {
        String pagingWhere = null;
        String order = null;
        if (isNext) {
            if (isSubsetMode)
                if (locator)
                    pagingWhere = ".T013E WHERE (COMPANY_CODE = :company_code) AND (TABLE_SUBSET = :table_subset) AND ((EFFECTIVE_DATE > :effective_date) OR (EFFECTIVE_DATE = :effective_date AND MAX_ISSUE_AGE > :max_issue_age) OR (EFFECTIVE_DATE = :effective_date AND MAX_ISSUE_AGE = :max_issue_age AND MAX_POL_DURATION > :max_pol_duration) OR (EFFECTIVE_DATE = :effective_date AND MAX_ISSUE_AGE = :max_issue_age AND MAX_POL_DURATION = :max_pol_duration)) ";
                else 
                    pagingWhere = ".T013E WHERE (COMPANY_CODE = :company_code) AND (TABLE_SUBSET = :table_subset) AND ((EFFECTIVE_DATE > :effective_date) OR (EFFECTIVE_DATE = :effective_date AND MAX_ISSUE_AGE > :max_issue_age) OR (EFFECTIVE_DATE = :effective_date AND MAX_ISSUE_AGE = :max_issue_age AND MAX_POL_DURATION > :max_pol_duration)) ";
            else
                if (locator)
                    pagingWhere = ".T013E WHERE (COMPANY_CODE = :company_code) AND ((TABLE_SUBSET > :table_subset) OR (TABLE_SUBSET = :table_subset AND EFFECTIVE_DATE > :effective_date) OR (TABLE_SUBSET = :table_subset AND EFFECTIVE_DATE = :effective_date AND MAX_ISSUE_AGE > :max_issue_age) OR (TABLE_SUBSET = :table_subset AND EFFECTIVE_DATE = :effective_date AND MAX_ISSUE_AGE = :max_issue_age AND MAX_POL_DURATION > :max_pol_duration) OR (TABLE_SUBSET = :table_subset AND EFFECTIVE_DATE = :effective_date AND MAX_ISSUE_AGE = :max_issue_age AND MAX_POL_DURATION = :max_pol_duration)) ";
                else
                    pagingWhere = ".T013E WHERE (COMPANY_CODE = :company_code) AND ((TABLE_SUBSET > :table_subset) OR (TABLE_SUBSET = :table_subset AND EFFECTIVE_DATE > :effective_date) OR (TABLE_SUBSET = :table_subset AND EFFECTIVE_DATE = :effective_date AND MAX_ISSUE_AGE > :max_issue_age) OR (TABLE_SUBSET = :table_subset AND EFFECTIVE_DATE = :effective_date AND MAX_ISSUE_AGE = :max_issue_age AND MAX_POL_DURATION > :max_pol_duration)) ";
            order = " ORDER BY COMPANY_CODE, TABLE_SUBSET, EFFECTIVE_DATE, MAX_ISSUE_AGE, MAX_POL_DURATION";
        }
        else {
            if (isSubsetMode)
                pagingWhere = ".T013E WHERE (COMPANY_CODE = :company_code) AND (TABLE_SUBSET = :table_subset) AND ((EFFECTIVE_DATE < :effective_date) OR (EFFECTIVE_DATE = :effective_date AND MAX_ISSUE_AGE < :max_issue_age) OR (EFFECTIVE_DATE = :effective_date AND MAX_ISSUE_AGE = :max_issue_age AND MAX_POL_DURATION < :max_pol_duration)) ";
            else
                pagingWhere = ".T013E WHERE (COMPANY_CODE = :company_code) AND ((TABLE_SUBSET < :table_subset) OR (TABLE_SUBSET = :table_subset AND EFFECTIVE_DATE < :effective_date) OR (TABLE_SUBSET = :table_subset AND EFFECTIVE_DATE = :effective_date AND MAX_ISSUE_AGE < :max_issue_age) OR (TABLE_SUBSET = :table_subset AND EFFECTIVE_DATE = :effective_date AND MAX_ISSUE_AGE = :max_issue_age AND MAX_POL_DURATION < :max_pol_duration)) ";
            order = " ORDER BY COMPANY_CODE DESC, TABLE_SUBSET DESC, EFFECTIVE_DATE DESC, MAX_ISSUE_AGE DESC, MAX_POL_DURATION DESC";
        }
        return pagingSql + schemaName + pagingWhere + order;
    }
    
    public String prepareInsertStmt(String schemaName) {
        StringBuffer sb = new StringBuffer();
        sb.append("INSERT INTO ").append(schemaName);
        sb.append(".T013E ( ");
        sb.append("COMPANY_CODE, TABLE_SUBSET, EFFECTIVE_DATE, MAX_ISSUE_AGE, MAX_POL_DURATION, MIN_FACE_AMT )");
        sb.append(" VALUES (?, ?, ?, ?, ?, ? )");
        return sb.toString();
    }
    
    public String prepareUpdateStmt(String schemaName)
    {
        StringBuffer sb = new StringBuffer();
        sb.append("UPDATE ").append(schemaName);
        sb.append(".T013E ");
        sb.append(" SET COMPANY_CODE = ?, TABLE_SUBSET = ?, EFFECTIVE_DATE = ?, MAX_ISSUE_AGE = ?, MAX_POL_DURATION = ?, MIN_FACE_AMT = ?");
        sb.append(" WHERE COMPANY_CODE = ? AND TABLE_SUBSET = ? AND EFFECTIVE_DATE = ? AND MAX_ISSUE_AGE = ? AND MAX_POL_DURATION = ?");
        return sb.toString();
    }
    
    public String prepareDeleteStmt(String schemaName)
    {
        StringBuffer sb = new StringBuffer();
        sb.append("DELETE FROM ").append(schemaName);
        sb.append(".T013E ");
        sb.append(" WHERE COMPANY_CODE = ? AND TABLE_SUBSET = ? AND EFFECTIVE_DATE = ? AND MAX_ISSUE_AGE = ? AND MAX_POL_DURATION = ?");
        return sb.toString();
    }
}
