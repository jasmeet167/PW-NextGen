package com.csc.fsg.life.pw.web.io.descriptor.wma;

import com.csc.fsg.life.pw.web.io.*;

public class TA61FDescriptor
    extends TableDescriptor
{
    private static final String pagingSql = "SELECT COMPANY_CODE, TABLE_SUBSET, FUND_NUMBER, EFFECTIVE_DATE, MAX_ISSUE_AGE, MAX_POLICY_DUR, BNFT_RSRV_FCT FROM ";
    
    public void initialize()
    {
        setRowClass(TA61FRow.class);
        setTableName("TA61F");
        setTableId("A61");
        super.initialize();
    }
    
    public void initializeColumnDescriptors()
    {
        super.initializeColumnDescriptors();
        addColumnDescriptor(new ColumnDescriptor(this,"getCompanyCode","setCompanyCode","COMPANY_CODE,1,1,3,0,true|,0,CHAR,1,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getTableSubset","setTableSubset","TABLE_SUBSET,1,2,16,0,true|,0,CHAR,1,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getFundNumber","setFundNumber","FUND_NUMBER,3,3,8,0,true|,0,INTEGER,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getEffectiveDate","setEffectiveDate","EFFECTIVE_DATE,91,4,10,0,true|,0,DATE,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getMaxIssueAge","setMaxIssueAge","MAX_ISSUE_AGE,3,5,3,0,true|,0,INTEGER,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getMaxPolicyDur","setMaxPolicyDur","MAX_POLICY_DUR,3,6,3,0,true|,0,INTEGER,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getBnftRsrvFct","setBnftRsrvFct","BNFT_RSRV_FCT,3,7,7,5,false|,0,DOUBLE,0,null,null,null,null,null"));
    }
    
    public String getPagingSQL(String schemaName,boolean isSubsetMode,boolean isNext, boolean locator)
    {
        String pagingWhere = null;
        String order = null;
        if (isNext) {
            if (isSubsetMode)
                if (locator)
                    pagingWhere = ".TA61F WHERE (COMPANY_CODE = :company_code) AND (TABLE_SUBSET = :table_subset) AND ((FUND_NUMBER > :fund_number) OR (FUND_NUMBER = :fund_number AND EFFECTIVE_DATE > :effective_date) OR (FUND_NUMBER = :fund_number AND EFFECTIVE_DATE = :effective_date AND MAX_ISSUE_AGE > :max_issue_age) OR (FUND_NUMBER = :fund_number AND EFFECTIVE_DATE = :effective_date AND MAX_ISSUE_AGE = :max_issue_age AND MAX_POLICY_DUR > :max_policy_dur) OR (FUND_NUMBER = :fund_number AND EFFECTIVE_DATE = :effective_date AND MAX_ISSUE_AGE = :max_issue_age AND MAX_POLICY_DUR = :max_policy_dur)) ";
                else 
                    pagingWhere = ".TA61F WHERE (COMPANY_CODE = :company_code) AND (TABLE_SUBSET = :table_subset) AND ((FUND_NUMBER > :fund_number) OR (FUND_NUMBER = :fund_number AND EFFECTIVE_DATE > :effective_date) OR (FUND_NUMBER = :fund_number AND EFFECTIVE_DATE = :effective_date AND MAX_ISSUE_AGE > :max_issue_age) OR (FUND_NUMBER = :fund_number AND EFFECTIVE_DATE = :effective_date AND MAX_ISSUE_AGE = :max_issue_age AND MAX_POLICY_DUR > :max_policy_dur)) ";
            else
                if (locator)
                    pagingWhere = ".TA61F WHERE (COMPANY_CODE = :company_code) AND ((TABLE_SUBSET > :table_subset) OR (TABLE_SUBSET = :table_subset AND FUND_NUMBER > :fund_number) OR (TABLE_SUBSET = :table_subset AND FUND_NUMBER = :fund_number AND EFFECTIVE_DATE > :effective_date) OR (TABLE_SUBSET = :table_subset AND FUND_NUMBER = :fund_number AND EFFECTIVE_DATE = :effective_date AND MAX_ISSUE_AGE > :max_issue_age) OR (TABLE_SUBSET = :table_subset AND FUND_NUMBER = :fund_number AND EFFECTIVE_DATE = :effective_date AND MAX_ISSUE_AGE = :max_issue_age AND MAX_POLICY_DUR > :max_policy_dur) OR (TABLE_SUBSET = :table_subset AND FUND_NUMBER = :fund_number AND EFFECTIVE_DATE = :effective_date AND MAX_ISSUE_AGE = :max_issue_age AND MAX_POLICY_DUR = :max_policy_dur)) ";
                else
                    pagingWhere = ".TA61F WHERE (COMPANY_CODE = :company_code) AND ((TABLE_SUBSET > :table_subset) OR (TABLE_SUBSET = :table_subset AND FUND_NUMBER > :fund_number) OR (TABLE_SUBSET = :table_subset AND FUND_NUMBER = :fund_number AND EFFECTIVE_DATE > :effective_date) OR (TABLE_SUBSET = :table_subset AND FUND_NUMBER = :fund_number AND EFFECTIVE_DATE = :effective_date AND MAX_ISSUE_AGE > :max_issue_age) OR (TABLE_SUBSET = :table_subset AND FUND_NUMBER = :fund_number AND EFFECTIVE_DATE = :effective_date AND MAX_ISSUE_AGE = :max_issue_age AND MAX_POLICY_DUR > :max_policy_dur)) ";
            order = " ORDER BY COMPANY_CODE, TABLE_SUBSET, FUND_NUMBER, EFFECTIVE_DATE, MAX_ISSUE_AGE, MAX_POLICY_DUR";
        }
        else {
            if (isSubsetMode)
                pagingWhere = ".TA61F WHERE (COMPANY_CODE = :company_code) AND (TABLE_SUBSET = :table_subset) AND ((FUND_NUMBER < :fund_number) OR (FUND_NUMBER = :fund_number AND EFFECTIVE_DATE < :effective_date) OR (FUND_NUMBER = :fund_number AND EFFECTIVE_DATE = :effective_date AND MAX_ISSUE_AGE < :max_issue_age) OR (FUND_NUMBER = :fund_number AND EFFECTIVE_DATE = :effective_date AND MAX_ISSUE_AGE = :max_issue_age AND MAX_POLICY_DUR < :max_policy_dur)) ";
            else
                pagingWhere = ".TA61F WHERE (COMPANY_CODE = :company_code) AND ((TABLE_SUBSET < :table_subset) OR (TABLE_SUBSET = :table_subset AND FUND_NUMBER < :fund_number) OR (TABLE_SUBSET = :table_subset AND FUND_NUMBER = :fund_number AND EFFECTIVE_DATE < :effective_date) OR (TABLE_SUBSET = :table_subset AND FUND_NUMBER = :fund_number AND EFFECTIVE_DATE = :effective_date AND MAX_ISSUE_AGE < :max_issue_age) OR (TABLE_SUBSET = :table_subset AND FUND_NUMBER = :fund_number AND EFFECTIVE_DATE = :effective_date AND MAX_ISSUE_AGE = :max_issue_age AND MAX_POLICY_DUR < :max_policy_dur)) ";
            order = " ORDER BY COMPANY_CODE DESC, TABLE_SUBSET DESC, FUND_NUMBER DESC, EFFECTIVE_DATE DESC, MAX_ISSUE_AGE DESC, MAX_POLICY_DUR DESC";
        }
        return pagingSql + schemaName + pagingWhere + order;
    }
    
    public String prepareInsertStmt(String schemaName) {
        StringBuffer sb = new StringBuffer();
        sb.append("INSERT INTO ").append(schemaName);
        sb.append(".TA61F ( ");
        sb.append("COMPANY_CODE, TABLE_SUBSET, FUND_NUMBER, EFFECTIVE_DATE, MAX_ISSUE_AGE, MAX_POLICY_DUR, BNFT_RSRV_FCT )");
        sb.append(" VALUES (?, ?, ?, ?, ?, ?, ? )");
        return sb.toString();
    }
    
    public String prepareUpdateStmt(String schemaName)
    {
        StringBuffer sb = new StringBuffer();
        sb.append("UPDATE ").append(schemaName);
        sb.append(".TA61F ");
        sb.append(" SET COMPANY_CODE = ?, TABLE_SUBSET = ?, FUND_NUMBER = ?, EFFECTIVE_DATE = ?, MAX_ISSUE_AGE = ?, MAX_POLICY_DUR = ?, BNFT_RSRV_FCT = ?");
        sb.append(" WHERE COMPANY_CODE = ? AND TABLE_SUBSET = ? AND FUND_NUMBER = ? AND EFFECTIVE_DATE = ? AND MAX_ISSUE_AGE = ? AND MAX_POLICY_DUR = ?");
        return sb.toString();
    }
    
    public String prepareDeleteStmt(String schemaName)
    {
        StringBuffer sb = new StringBuffer();
        sb.append("DELETE FROM ").append(schemaName);
        sb.append(".TA61F ");
        sb.append(" WHERE COMPANY_CODE = ? AND TABLE_SUBSET = ? AND FUND_NUMBER = ? AND EFFECTIVE_DATE = ? AND MAX_ISSUE_AGE = ? AND MAX_POLICY_DUR = ?");
        return sb.toString();
    }
}
