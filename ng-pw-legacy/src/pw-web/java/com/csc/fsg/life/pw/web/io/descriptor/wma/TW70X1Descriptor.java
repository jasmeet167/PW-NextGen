package com.csc.fsg.life.pw.web.io.descriptor.wma;

import com.csc.fsg.life.pw.web.io.*;

public class TW70X1Descriptor
    extends TableDescriptor
{
    private static final String pagingSql = "SELECT COMPANY_CODE, TABLE_SUBSET, ISSUE_AGE, DURATION, D_SUB_X, LN_V_SUPER_T, ANNUITY_DUE FROM ";
    
    public void initialize()
    {
        setRowClass(TW70X1Row.class);
        setTableName("TW70X1");
        setTableId("70X");
        super.initialize();
    }
    
    public void initializeColumnDescriptors()
    {
        super.initializeColumnDescriptors();
        addColumnDescriptor(new ColumnDescriptor(this,"getCompanyCode","setCompanyCode","COMPANY_CODE,1,1,3,0,true|,0,CHAR,1,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getTableSubset","setTableSubset","TABLE_SUBSET,1,2,16,0,true|,0,CHAR,1,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getIssueAge","setIssueAge","ISSUE_AGE,3,3,3,0,true|,0,INTEGER,1,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getDuration","setDuration","DURATION,3,4,3,0,true|,0,INTEGER,1,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getDSubX","setDSubX","D_SUB_X,3,5,17,8,false|,0,DOUBLE,1,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getLnVSuperT","setLnVSuperT","LN_V_SUPER_T,3,6,13,11,false|,0,DOUBLE,1,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getAnnuityDue","setAnnuityDue","ANNUITY_DUE,3,7,9,6,false|,0,DOUBLE,1,null,null,null,null,null"));
    }
    
    public String getPagingSQL(String schemaName,boolean isSubsetMode,boolean isNext, boolean locator)
    {
        String pagingWhere = null;
        String order = null;
        if (isNext) {
            if (isSubsetMode)
                if (locator)
                    pagingWhere = ".TW70X1 WHERE (COMPANY_CODE = :company_code) AND (TABLE_SUBSET = :table_subset) AND ((ISSUE_AGE > :issue_age) OR (ISSUE_AGE = :issue_age AND DURATION > :duration) OR (ISSUE_AGE = :issue_age AND DURATION = :duration)) ";
                else 
                    pagingWhere = ".TW70X1 WHERE (COMPANY_CODE = :company_code) AND (TABLE_SUBSET = :table_subset) AND ((ISSUE_AGE > :issue_age) OR (ISSUE_AGE = :issue_age AND DURATION > :duration)) ";
            else
                if (locator)
                    pagingWhere = ".TW70X1 WHERE (COMPANY_CODE = :company_code) AND ((TABLE_SUBSET > :table_subset) OR (TABLE_SUBSET = :table_subset AND ISSUE_AGE > :issue_age) OR (TABLE_SUBSET = :table_subset AND ISSUE_AGE = :issue_age AND DURATION > :duration) OR (TABLE_SUBSET = :table_subset AND ISSUE_AGE = :issue_age AND DURATION = :duration)) ";
                else
                    pagingWhere = ".TW70X1 WHERE (COMPANY_CODE = :company_code) AND ((TABLE_SUBSET > :table_subset) OR (TABLE_SUBSET = :table_subset AND ISSUE_AGE > :issue_age) OR (TABLE_SUBSET = :table_subset AND ISSUE_AGE = :issue_age AND DURATION > :duration)) ";
            order = " ORDER BY COMPANY_CODE, TABLE_SUBSET, ISSUE_AGE, DURATION";
        }
        else {
            if (isSubsetMode)
                pagingWhere = ".TW70X1 WHERE (COMPANY_CODE = :company_code) AND (TABLE_SUBSET = :table_subset) AND ((ISSUE_AGE < :issue_age) OR (ISSUE_AGE = :issue_age AND DURATION < :duration)) ";
            else
                pagingWhere = ".TW70X1 WHERE (COMPANY_CODE = :company_code) AND ((TABLE_SUBSET < :table_subset) OR (TABLE_SUBSET = :table_subset AND ISSUE_AGE < :issue_age) OR (TABLE_SUBSET = :table_subset AND ISSUE_AGE = :issue_age AND DURATION < :duration)) ";
            order = " ORDER BY COMPANY_CODE DESC, TABLE_SUBSET DESC, ISSUE_AGE DESC, DURATION DESC";
        }
        return pagingSql + schemaName + pagingWhere + order;
    }
    
    public String prepareInsertStmt(String schemaName) {
        StringBuffer sb = new StringBuffer();
        sb.append("INSERT INTO ").append(schemaName);
        sb.append(".TW70X1 ( ");
        sb.append("COMPANY_CODE, TABLE_SUBSET, ISSUE_AGE, DURATION, D_SUB_X, LN_V_SUPER_T, ANNUITY_DUE )");
        sb.append(" VALUES (?, ?, ?, ?, ?, ?, ? )");
        return sb.toString();
    }
    
    public String prepareUpdateStmt(String schemaName)
    {
        StringBuffer sb = new StringBuffer();
        sb.append("UPDATE ").append(schemaName);
        sb.append(".TW70X1 ");
        sb.append(" SET COMPANY_CODE = ?, TABLE_SUBSET = ?, ISSUE_AGE = ?, DURATION = ?, D_SUB_X = ?, LN_V_SUPER_T = ?, ANNUITY_DUE = ?");
        sb.append(" WHERE COMPANY_CODE = ? AND TABLE_SUBSET = ? AND ISSUE_AGE = ? AND DURATION = ?");
        return sb.toString();
    }
    
    public String prepareDeleteStmt(String schemaName)
    {
        StringBuffer sb = new StringBuffer();
        sb.append("DELETE FROM ").append(schemaName);
        sb.append(".TW70X1 ");
        sb.append(" WHERE COMPANY_CODE = ? AND TABLE_SUBSET = ? AND ISSUE_AGE = ? AND DURATION = ?");
        return sb.toString();
    }
}
