package com.csc.fsg.life.pw.web.io.descriptor.wma;

import com.csc.fsg.life.pw.web.io.*;

public class TAE2FDescriptor
    extends TableDescriptor
{
    private static final String pagingSql = "SELECT COMPANY_CODE, TABLE_SUBSET, EFFECTIVE_DATE, ISSUE_AGE, MAX_POLICY_YEAR, RIDER_BENEFIT_FCTR FROM ";
    
    public void initialize()
    {
        setRowClass(TAE2FRow.class);
        setTableName("TAE2F");
        setTableId("AE2");
        super.initialize();
    }
    
    public void initializeColumnDescriptors()
    {
        super.initializeColumnDescriptors();
        addColumnDescriptor(new ColumnDescriptor(this,"getCompanyCode","setCompanyCode","COMPANY_CODE,1,1,3,0,true|,0,CHAR,1,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getTableSubset","setTableSubset","TABLE_SUBSET,1,2,16,0,true|,0,CHAR,1,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getEffectiveDate","setEffectiveDate","EFFECTIVE_DATE,91,3,10,0,true|,0,DATE,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getIssueAge","setIssueAge","ISSUE_AGE,3,4,3,0,true|,0,INTEGER,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getMaxPolicyYear","setMaxPolicyYear","MAX_POLICY_YEAR,3,5,3,0,true|,0,INTEGER,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getRiderBenefitFctr","setRiderBenefitFctr","RIDER_BENEFIT_FCTR,3,6,5,3,false|,0,DOUBLE,0,null,null,null,null,null"));
    }
    
    public String getPagingSQL(String schemaName,boolean isSubsetMode,boolean isNext, boolean locator)
    {
        String pagingWhere = null;
        String order = null;
        if (isNext) {
            if (isSubsetMode)
                if (locator)
                    pagingWhere = ".TAE2F WHERE (COMPANY_CODE = :company_code) AND (TABLE_SUBSET = :table_subset) AND ((EFFECTIVE_DATE > :effective_date) OR (EFFECTIVE_DATE = :effective_date AND ISSUE_AGE > :issue_age) OR (EFFECTIVE_DATE = :effective_date AND ISSUE_AGE = :issue_age AND MAX_POLICY_YEAR > :max_policy_year) OR (EFFECTIVE_DATE = :effective_date AND ISSUE_AGE = :issue_age AND MAX_POLICY_YEAR = :max_policy_year)) ";
                else 
                    pagingWhere = ".TAE2F WHERE (COMPANY_CODE = :company_code) AND (TABLE_SUBSET = :table_subset) AND ((EFFECTIVE_DATE > :effective_date) OR (EFFECTIVE_DATE = :effective_date AND ISSUE_AGE > :issue_age) OR (EFFECTIVE_DATE = :effective_date AND ISSUE_AGE = :issue_age AND MAX_POLICY_YEAR > :max_policy_year)) ";
            else
                if (locator)
                    pagingWhere = ".TAE2F WHERE (COMPANY_CODE = :company_code) AND ((TABLE_SUBSET > :table_subset) OR (TABLE_SUBSET = :table_subset AND EFFECTIVE_DATE > :effective_date) OR (TABLE_SUBSET = :table_subset AND EFFECTIVE_DATE = :effective_date AND ISSUE_AGE > :issue_age) OR (TABLE_SUBSET = :table_subset AND EFFECTIVE_DATE = :effective_date AND ISSUE_AGE = :issue_age AND MAX_POLICY_YEAR > :max_policy_year) OR (TABLE_SUBSET = :table_subset AND EFFECTIVE_DATE = :effective_date AND ISSUE_AGE = :issue_age AND MAX_POLICY_YEAR = :max_policy_year)) ";
                else
                    pagingWhere = ".TAE2F WHERE (COMPANY_CODE = :company_code) AND ((TABLE_SUBSET > :table_subset) OR (TABLE_SUBSET = :table_subset AND EFFECTIVE_DATE > :effective_date) OR (TABLE_SUBSET = :table_subset AND EFFECTIVE_DATE = :effective_date AND ISSUE_AGE > :issue_age) OR (TABLE_SUBSET = :table_subset AND EFFECTIVE_DATE = :effective_date AND ISSUE_AGE = :issue_age AND MAX_POLICY_YEAR > :max_policy_year)) ";
            order = " ORDER BY COMPANY_CODE, TABLE_SUBSET, EFFECTIVE_DATE, ISSUE_AGE, MAX_POLICY_YEAR";
        }
        else {
            if (isSubsetMode)
                pagingWhere = ".TAE2F WHERE (COMPANY_CODE = :company_code) AND (TABLE_SUBSET = :table_subset) AND ((EFFECTIVE_DATE < :effective_date) OR (EFFECTIVE_DATE = :effective_date AND ISSUE_AGE < :issue_age) OR (EFFECTIVE_DATE = :effective_date AND ISSUE_AGE = :issue_age AND MAX_POLICY_YEAR < :max_policy_year)) ";
            else
                pagingWhere = ".TAE2F WHERE (COMPANY_CODE = :company_code) AND ((TABLE_SUBSET < :table_subset) OR (TABLE_SUBSET = :table_subset AND EFFECTIVE_DATE < :effective_date) OR (TABLE_SUBSET = :table_subset AND EFFECTIVE_DATE = :effective_date AND ISSUE_AGE < :issue_age) OR (TABLE_SUBSET = :table_subset AND EFFECTIVE_DATE = :effective_date AND ISSUE_AGE = :issue_age AND MAX_POLICY_YEAR < :max_policy_year)) ";
            order = " ORDER BY COMPANY_CODE DESC, TABLE_SUBSET DESC, EFFECTIVE_DATE DESC, ISSUE_AGE DESC, MAX_POLICY_YEAR DESC";
        }
        return pagingSql + schemaName + pagingWhere + order;
    }
    
    public String prepareInsertStmt(String schemaName) {
        StringBuffer sb = new StringBuffer();
        sb.append("INSERT INTO ").append(schemaName);
        sb.append(".TAE2F ( ");
        sb.append("COMPANY_CODE, TABLE_SUBSET, EFFECTIVE_DATE, ISSUE_AGE, MAX_POLICY_YEAR, RIDER_BENEFIT_FCTR )");
        sb.append(" VALUES (?, ?, ?, ?, ?, ? )");
        return sb.toString();
    }
    
    public String prepareUpdateStmt(String schemaName)
    {
        StringBuffer sb = new StringBuffer();
        sb.append("UPDATE ").append(schemaName);
        sb.append(".TAE2F ");
        sb.append(" SET COMPANY_CODE = ?, TABLE_SUBSET = ?, EFFECTIVE_DATE = ?, ISSUE_AGE = ?, MAX_POLICY_YEAR = ?, RIDER_BENEFIT_FCTR = ?");
        sb.append(" WHERE COMPANY_CODE = ? AND TABLE_SUBSET = ? AND EFFECTIVE_DATE = ? AND ISSUE_AGE = ? AND MAX_POLICY_YEAR = ?");
        return sb.toString();
    }
    
    public String prepareDeleteStmt(String schemaName)
    {
        StringBuffer sb = new StringBuffer();
        sb.append("DELETE FROM ").append(schemaName);
        sb.append(".TAE2F ");
        sb.append(" WHERE COMPANY_CODE = ? AND TABLE_SUBSET = ? AND EFFECTIVE_DATE = ? AND ISSUE_AGE = ? AND MAX_POLICY_YEAR = ?");
        return sb.toString();
    }
}
