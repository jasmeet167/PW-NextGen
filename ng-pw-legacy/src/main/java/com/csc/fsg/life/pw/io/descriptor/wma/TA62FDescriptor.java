package com.csc.fsg.life.pw.io.descriptor.wma;

import com.csc.fsg.life.pw.io.*;

public class TA62FDescriptor
    extends TableDescriptor
{
    private static final String pagingSql = "SELECT COMPANY_CODE, TABLE_SUBSET, FUND_NUMBER, EFFECTIVE_DATE, ISSUE_AGE, MAX_POLICY_YEAR, GAAP_EXPENSE_FACT FROM ";
    
    public void initialize()
    {
        setRowClass(TA62FRow.class);
        setTableName("TA62F");
        setTableId("A62");
        super.initialize();
    }
    
    public void initializeColumnDescriptors()
    {
        super.initializeColumnDescriptors();
        addColumnDescriptor(new ColumnDescriptor(this,"getCompanyCode","setCompanyCode","COMPANY_CODE,1,1,3,0,true|,0,CHAR,1,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getTableSubset","setTableSubset","TABLE_SUBSET,1,2,16,0,true|,0,CHAR,1,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getFundNumber","setFundNumber","FUND_NUMBER,3,3,8,0,true|,0,INTEGER,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getEffectiveDate","setEffectiveDate","EFFECTIVE_DATE,91,4,10,0,true|,0,DATE,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getIssueAge","setIssueAge","ISSUE_AGE,3,5,3,0,true|,0,INTEGER,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getMaxPolicyYear","setMaxPolicyYear","MAX_POLICY_YEAR,3,6,3,0,true|,0,INTEGER,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getGaapExpenseFact","setGaapExpenseFact","GAAP_EXPENSE_FACT,3,7,7,5,false|,0,DOUBLE,0,null,null,null,null,null"));
    }
    
    public String getPagingSQL(String schemaName,boolean isSubsetMode,boolean isNext, boolean locator)
    {
        String pagingWhere = null;
        String order = null;
        if (isNext) {
            if (isSubsetMode)
                if (locator)
                    pagingWhere = ".TA62F WHERE (COMPANY_CODE = :company_code) AND (TABLE_SUBSET = :table_subset) AND ((FUND_NUMBER > :fund_number) OR (FUND_NUMBER = :fund_number AND EFFECTIVE_DATE > :effective_date) OR (FUND_NUMBER = :fund_number AND EFFECTIVE_DATE = :effective_date AND ISSUE_AGE > :issue_age) OR (FUND_NUMBER = :fund_number AND EFFECTIVE_DATE = :effective_date AND ISSUE_AGE = :issue_age AND MAX_POLICY_YEAR > :max_policy_year) OR (FUND_NUMBER = :fund_number AND EFFECTIVE_DATE = :effective_date AND ISSUE_AGE = :issue_age AND MAX_POLICY_YEAR = :max_policy_year)) ";
                else 
                    pagingWhere = ".TA62F WHERE (COMPANY_CODE = :company_code) AND (TABLE_SUBSET = :table_subset) AND ((FUND_NUMBER > :fund_number) OR (FUND_NUMBER = :fund_number AND EFFECTIVE_DATE > :effective_date) OR (FUND_NUMBER = :fund_number AND EFFECTIVE_DATE = :effective_date AND ISSUE_AGE > :issue_age) OR (FUND_NUMBER = :fund_number AND EFFECTIVE_DATE = :effective_date AND ISSUE_AGE = :issue_age AND MAX_POLICY_YEAR > :max_policy_year)) ";
            else
                if (locator)
                    pagingWhere = ".TA62F WHERE (COMPANY_CODE = :company_code) AND ((TABLE_SUBSET > :table_subset) OR (TABLE_SUBSET = :table_subset AND FUND_NUMBER > :fund_number) OR (TABLE_SUBSET = :table_subset AND FUND_NUMBER = :fund_number AND EFFECTIVE_DATE > :effective_date) OR (TABLE_SUBSET = :table_subset AND FUND_NUMBER = :fund_number AND EFFECTIVE_DATE = :effective_date AND ISSUE_AGE > :issue_age) OR (TABLE_SUBSET = :table_subset AND FUND_NUMBER = :fund_number AND EFFECTIVE_DATE = :effective_date AND ISSUE_AGE = :issue_age AND MAX_POLICY_YEAR > :max_policy_year) OR (TABLE_SUBSET = :table_subset AND FUND_NUMBER = :fund_number AND EFFECTIVE_DATE = :effective_date AND ISSUE_AGE = :issue_age AND MAX_POLICY_YEAR = :max_policy_year)) ";
                else
                    pagingWhere = ".TA62F WHERE (COMPANY_CODE = :company_code) AND ((TABLE_SUBSET > :table_subset) OR (TABLE_SUBSET = :table_subset AND FUND_NUMBER > :fund_number) OR (TABLE_SUBSET = :table_subset AND FUND_NUMBER = :fund_number AND EFFECTIVE_DATE > :effective_date) OR (TABLE_SUBSET = :table_subset AND FUND_NUMBER = :fund_number AND EFFECTIVE_DATE = :effective_date AND ISSUE_AGE > :issue_age) OR (TABLE_SUBSET = :table_subset AND FUND_NUMBER = :fund_number AND EFFECTIVE_DATE = :effective_date AND ISSUE_AGE = :issue_age AND MAX_POLICY_YEAR > :max_policy_year)) ";
            order = " ORDER BY COMPANY_CODE, TABLE_SUBSET, FUND_NUMBER, EFFECTIVE_DATE, ISSUE_AGE, MAX_POLICY_YEAR";
        }
        else {
            if (isSubsetMode)
                pagingWhere = ".TA62F WHERE (COMPANY_CODE = :company_code) AND (TABLE_SUBSET = :table_subset) AND ((FUND_NUMBER < :fund_number) OR (FUND_NUMBER = :fund_number AND EFFECTIVE_DATE < :effective_date) OR (FUND_NUMBER = :fund_number AND EFFECTIVE_DATE = :effective_date AND ISSUE_AGE < :issue_age) OR (FUND_NUMBER = :fund_number AND EFFECTIVE_DATE = :effective_date AND ISSUE_AGE = :issue_age AND MAX_POLICY_YEAR < :max_policy_year)) ";
            else
                pagingWhere = ".TA62F WHERE (COMPANY_CODE = :company_code) AND ((TABLE_SUBSET < :table_subset) OR (TABLE_SUBSET = :table_subset AND FUND_NUMBER < :fund_number) OR (TABLE_SUBSET = :table_subset AND FUND_NUMBER = :fund_number AND EFFECTIVE_DATE < :effective_date) OR (TABLE_SUBSET = :table_subset AND FUND_NUMBER = :fund_number AND EFFECTIVE_DATE = :effective_date AND ISSUE_AGE < :issue_age) OR (TABLE_SUBSET = :table_subset AND FUND_NUMBER = :fund_number AND EFFECTIVE_DATE = :effective_date AND ISSUE_AGE = :issue_age AND MAX_POLICY_YEAR < :max_policy_year)) ";
            order = " ORDER BY COMPANY_CODE DESC, TABLE_SUBSET DESC, FUND_NUMBER DESC, EFFECTIVE_DATE DESC, ISSUE_AGE DESC, MAX_POLICY_YEAR DESC";
        }
        return pagingSql + schemaName + pagingWhere + order;
    }
    
    public String prepareInsertStmt(String schemaName) {
        StringBuffer sb = new StringBuffer();
        sb.append("INSERT INTO ").append(schemaName);
        sb.append(".TA62F ( ");
        sb.append("COMPANY_CODE, TABLE_SUBSET, FUND_NUMBER, EFFECTIVE_DATE, ISSUE_AGE, MAX_POLICY_YEAR, GAAP_EXPENSE_FACT )");
        sb.append(" VALUES (?, ?, ?, ?, ?, ?, ? )");
        return sb.toString();
    }
    
    public String prepareUpdateStmt(String schemaName)
    {
        StringBuffer sb = new StringBuffer();
        sb.append("UPDATE ").append(schemaName);
        sb.append(".TA62F ");
        sb.append(" SET COMPANY_CODE = ?, TABLE_SUBSET = ?, FUND_NUMBER = ?, EFFECTIVE_DATE = ?, ISSUE_AGE = ?, MAX_POLICY_YEAR = ?, GAAP_EXPENSE_FACT = ?");
        sb.append(" WHERE COMPANY_CODE = ? AND TABLE_SUBSET = ? AND FUND_NUMBER = ? AND EFFECTIVE_DATE = ? AND ISSUE_AGE = ? AND MAX_POLICY_YEAR = ?");
        return sb.toString();
    }
    
    public String prepareDeleteStmt(String schemaName)
    {
        StringBuffer sb = new StringBuffer();
        sb.append("DELETE FROM ").append(schemaName);
        sb.append(".TA62F ");
        sb.append(" WHERE COMPANY_CODE = ? AND TABLE_SUBSET = ? AND FUND_NUMBER = ? AND EFFECTIVE_DATE = ? AND ISSUE_AGE = ? AND MAX_POLICY_YEAR = ?");
        return sb.toString();
    }
}
