package com.csc.fsg.life.pw.web.io.descriptor.wma;

import com.csc.fsg.life.pw.web.io.*;

public class TW68X1Descriptor
    extends TableDescriptor
{
    private static final String pagingSql = "SELECT COMPANY_CODE, TABLE_SUBSET, ISSUE_AGE, POLICY_YEAR, NUMBER_LIVING FROM ";
    
    public void initialize()
    {
        setRowClass(TW68X1Row.class);
        setTableName("TW68X1");
        setTableId("68X");
        super.initialize();
    }
    
    public void initializeColumnDescriptors()
    {
        super.initializeColumnDescriptors();
        addColumnDescriptor(new ColumnDescriptor(this,"getCompanyCode","setCompanyCode","COMPANY_CODE,1,1,3,0,true|,0,CHAR,1,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getTableSubset","setTableSubset","TABLE_SUBSET,1,2,16,0,true|,0,CHAR,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getIssueAge","setIssueAge","ISSUE_AGE,3,3,3,0,true|,0,INTEGER,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getPolicyYear","setPolicyYear","POLICY_YEAR,3,4,3,0,true|,0,INTEGER,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getNumberLiving","setNumberLiving","NUMBER_LIVING,3,5,17,8,false|,0,DOUBLE,0,null,null,null,null,null"));
    }
    
    public String getPagingSQL(String schemaName,boolean isSubsetMode,boolean isNext, boolean locator)
    {
        String pagingWhere = null;
        String order = null;
        if (isNext) {
            if (isSubsetMode)
                if (locator)
                    pagingWhere = ".TW68X1 WHERE (COMPANY_CODE = :company_code) AND (TABLE_SUBSET = :table_subset) AND ((ISSUE_AGE > :issue_age) OR (ISSUE_AGE = :issue_age AND POLICY_YEAR > :policy_year) OR (ISSUE_AGE = :issue_age AND POLICY_YEAR = :policy_year)) ";
                else 
                    pagingWhere = ".TW68X1 WHERE (COMPANY_CODE = :company_code) AND (TABLE_SUBSET = :table_subset) AND ((ISSUE_AGE > :issue_age) OR (ISSUE_AGE = :issue_age AND POLICY_YEAR > :policy_year)) ";
            else
                if (locator)
                    pagingWhere = ".TW68X1 WHERE (COMPANY_CODE = :company_code) AND ((TABLE_SUBSET > :table_subset) OR (TABLE_SUBSET = :table_subset AND ISSUE_AGE > :issue_age) OR (TABLE_SUBSET = :table_subset AND ISSUE_AGE = :issue_age AND POLICY_YEAR > :policy_year) OR (TABLE_SUBSET = :table_subset AND ISSUE_AGE = :issue_age AND POLICY_YEAR = :policy_year)) ";
                else
                    pagingWhere = ".TW68X1 WHERE (COMPANY_CODE = :company_code) AND ((TABLE_SUBSET > :table_subset) OR (TABLE_SUBSET = :table_subset AND ISSUE_AGE > :issue_age) OR (TABLE_SUBSET = :table_subset AND ISSUE_AGE = :issue_age AND POLICY_YEAR > :policy_year)) ";
            order = " ORDER BY COMPANY_CODE, TABLE_SUBSET, ISSUE_AGE, POLICY_YEAR";
        }
        else {
            if (isSubsetMode)
                pagingWhere = ".TW68X1 WHERE (COMPANY_CODE = :company_code) AND (TABLE_SUBSET = :table_subset) AND ((ISSUE_AGE < :issue_age) OR (ISSUE_AGE = :issue_age AND POLICY_YEAR < :policy_year)) ";
            else
                pagingWhere = ".TW68X1 WHERE (COMPANY_CODE = :company_code) AND ((TABLE_SUBSET < :table_subset) OR (TABLE_SUBSET = :table_subset AND ISSUE_AGE < :issue_age) OR (TABLE_SUBSET = :table_subset AND ISSUE_AGE = :issue_age AND POLICY_YEAR < :policy_year)) ";
            order = " ORDER BY COMPANY_CODE DESC, TABLE_SUBSET DESC, ISSUE_AGE DESC, POLICY_YEAR DESC";
        }
        return pagingSql + schemaName + pagingWhere + order;
    }
    
    public String prepareInsertStmt(String schemaName) {
        StringBuffer sb = new StringBuffer();
        sb.append("INSERT INTO ").append(schemaName);
        sb.append(".TW68X1 ( ");
        sb.append("COMPANY_CODE, TABLE_SUBSET, ISSUE_AGE, POLICY_YEAR, NUMBER_LIVING )");
        sb.append(" VALUES (?, ?, ?, ?, ? )");
        return sb.toString();
    }
    
    public String prepareUpdateStmt(String schemaName)
    {
        StringBuffer sb = new StringBuffer();
        sb.append("UPDATE ").append(schemaName);
        sb.append(".TW68X1 ");
        sb.append(" SET COMPANY_CODE = ?, TABLE_SUBSET = ?, ISSUE_AGE = ?, POLICY_YEAR = ?, NUMBER_LIVING = ?");
        sb.append(" WHERE COMPANY_CODE = ? AND TABLE_SUBSET = ? AND ISSUE_AGE = ? AND POLICY_YEAR = ?");
        return sb.toString();
    }
    
    public String prepareDeleteStmt(String schemaName)
    {
        StringBuffer sb = new StringBuffer();
        sb.append("DELETE FROM ").append(schemaName);
        sb.append(".TW68X1 ");
        sb.append(" WHERE COMPANY_CODE = ? AND TABLE_SUBSET = ? AND ISSUE_AGE = ? AND POLICY_YEAR = ?");
        return sb.toString();
    }
}
