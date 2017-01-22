package com.csc.fsg.life.pw.web.io.descriptor.wma;

import com.csc.fsg.life.pw.web.io.*;

public class T042EDescriptor
    extends TableDescriptor
{
    private static final String pagingSql = "SELECT COMPANY_CODE, TABLE_SUBSET, EFFECTIVE_DATE, POLICY_TYPE, SEX_CODE, MEMO_CODE, MAX_ISSUE_AGE, MAX_DURATION, MAX_COVERAGE_UNITS, MAX_CASH_VALUE, INHIBIT_CHARGE_IND, PCT_CASH_VALUE FROM ";
    
    public void initialize()
    {
        setRowClass(T042ERow.class);
        setTableName("T042E");
        setTableId("042");
        super.initialize();
    }
    
    public void initializeColumnDescriptors()
    {
        super.initializeColumnDescriptors();
        addColumnDescriptor(new ColumnDescriptor(this,"getCompanyCode","setCompanyCode","COMPANY_CODE,1,1,3,0,true|,0,CHAR,1,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getTableSubset","setTableSubset","TABLE_SUBSET,1,2,16,0,true|,0,CHAR,1,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getEffectiveDate","setEffectiveDate","EFFECTIVE_DATE,91,3,10,0,true|,0,DATE,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getPolicyType","setPolicyType","POLICY_TYPE,1,4,1,0,true|,0,CHAR,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getSexCode","setSexCode","SEX_CODE,1,5,1,0,true|,0,CHAR,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getMemoCode","setMemoCode","MEMO_CODE,1,6,2,0,true|,0,CHAR,4,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getMaxIssueAge","setMaxIssueAge","MAX_ISSUE_AGE,3,7,3,0,true|,0,INTEGER,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getMaxDuration","setMaxDuration","MAX_DURATION,3,8,4,0,true|,0,INTEGER,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getMaxCoverageUnits","setMaxCoverageUnits","MAX_COVERAGE_UNITS,3,9,11,5,true|,0,DOUBLE,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getMaxCashValue","setMaxCashValue","MAX_CASH_VALUE,3,10,11,2,true|,0,DOUBLE,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getInhibitChargeInd","setInhibitChargeInd","INHIBIT_CHARGE_IND,1,11,1,0,false|,0,CHAR,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getPctCashValue","setPctCashValue","PCT_CASH_VALUE,3,12,5,2,false|,0,DOUBLE,0,null,null,null,null,null"));
    }
    
    public String getPagingSQL(String schemaName,boolean isSubsetMode,boolean isNext, boolean locator)
    {
        String pagingWhere = null;
        String order = null;
        if (isNext) {
            if (isSubsetMode)
                if (locator)
                    pagingWhere = ".T042E WHERE (COMPANY_CODE = :company_code) AND (TABLE_SUBSET = :table_subset) AND ((EFFECTIVE_DATE > :effective_date) OR (EFFECTIVE_DATE = :effective_date AND POLICY_TYPE > :policy_type) OR (EFFECTIVE_DATE = :effective_date AND POLICY_TYPE = :policy_type AND SEX_CODE > :sex_code) OR (EFFECTIVE_DATE = :effective_date AND POLICY_TYPE = :policy_type AND SEX_CODE = :sex_code AND MEMO_CODE > :memo_code) OR (EFFECTIVE_DATE = :effective_date AND POLICY_TYPE = :policy_type AND SEX_CODE = :sex_code AND MEMO_CODE = :memo_code AND MAX_ISSUE_AGE > :max_issue_age) OR (EFFECTIVE_DATE = :effective_date AND POLICY_TYPE = :policy_type AND SEX_CODE = :sex_code AND MEMO_CODE = :memo_code AND MAX_ISSUE_AGE = :max_issue_age AND MAX_DURATION > :max_duration) OR (EFFECTIVE_DATE = :effective_date AND POLICY_TYPE = :policy_type AND SEX_CODE = :sex_code AND MEMO_CODE = :memo_code AND MAX_ISSUE_AGE = :max_issue_age AND MAX_DURATION = :max_duration AND MAX_COVERAGE_UNITS > :max_coverage_units) OR (EFFECTIVE_DATE = :effective_date AND POLICY_TYPE = :policy_type AND SEX_CODE = :sex_code AND MEMO_CODE = :memo_code AND MAX_ISSUE_AGE = :max_issue_age AND MAX_DURATION = :max_duration AND MAX_COVERAGE_UNITS = :max_coverage_units AND MAX_CASH_VALUE > :max_cash_value) OR (EFFECTIVE_DATE = :effective_date AND POLICY_TYPE = :policy_type AND SEX_CODE = :sex_code AND MEMO_CODE = :memo_code AND MAX_ISSUE_AGE = :max_issue_age AND MAX_DURATION = :max_duration AND MAX_COVERAGE_UNITS = :max_coverage_units AND MAX_CASH_VALUE = :max_cash_value)) ";
                else 
                    pagingWhere = ".T042E WHERE (COMPANY_CODE = :company_code) AND (TABLE_SUBSET = :table_subset) AND ((EFFECTIVE_DATE > :effective_date) OR (EFFECTIVE_DATE = :effective_date AND POLICY_TYPE > :policy_type) OR (EFFECTIVE_DATE = :effective_date AND POLICY_TYPE = :policy_type AND SEX_CODE > :sex_code) OR (EFFECTIVE_DATE = :effective_date AND POLICY_TYPE = :policy_type AND SEX_CODE = :sex_code AND MEMO_CODE > :memo_code) OR (EFFECTIVE_DATE = :effective_date AND POLICY_TYPE = :policy_type AND SEX_CODE = :sex_code AND MEMO_CODE = :memo_code AND MAX_ISSUE_AGE > :max_issue_age) OR (EFFECTIVE_DATE = :effective_date AND POLICY_TYPE = :policy_type AND SEX_CODE = :sex_code AND MEMO_CODE = :memo_code AND MAX_ISSUE_AGE = :max_issue_age AND MAX_DURATION > :max_duration) OR (EFFECTIVE_DATE = :effective_date AND POLICY_TYPE = :policy_type AND SEX_CODE = :sex_code AND MEMO_CODE = :memo_code AND MAX_ISSUE_AGE = :max_issue_age AND MAX_DURATION = :max_duration AND MAX_COVERAGE_UNITS > :max_coverage_units) OR (EFFECTIVE_DATE = :effective_date AND POLICY_TYPE = :policy_type AND SEX_CODE = :sex_code AND MEMO_CODE = :memo_code AND MAX_ISSUE_AGE = :max_issue_age AND MAX_DURATION = :max_duration AND MAX_COVERAGE_UNITS = :max_coverage_units AND MAX_CASH_VALUE > :max_cash_value)) ";
            else
                if (locator)
                    pagingWhere = ".T042E WHERE (COMPANY_CODE = :company_code) AND ((TABLE_SUBSET > :table_subset) OR (TABLE_SUBSET = :table_subset AND EFFECTIVE_DATE > :effective_date) OR (TABLE_SUBSET = :table_subset AND EFFECTIVE_DATE = :effective_date AND POLICY_TYPE > :policy_type) OR (TABLE_SUBSET = :table_subset AND EFFECTIVE_DATE = :effective_date AND POLICY_TYPE = :policy_type AND SEX_CODE > :sex_code) OR (TABLE_SUBSET = :table_subset AND EFFECTIVE_DATE = :effective_date AND POLICY_TYPE = :policy_type AND SEX_CODE = :sex_code AND MEMO_CODE > :memo_code) OR (TABLE_SUBSET = :table_subset AND EFFECTIVE_DATE = :effective_date AND POLICY_TYPE = :policy_type AND SEX_CODE = :sex_code AND MEMO_CODE = :memo_code AND MAX_ISSUE_AGE > :max_issue_age) OR (TABLE_SUBSET = :table_subset AND EFFECTIVE_DATE = :effective_date AND POLICY_TYPE = :policy_type AND SEX_CODE = :sex_code AND MEMO_CODE = :memo_code AND MAX_ISSUE_AGE = :max_issue_age AND MAX_DURATION > :max_duration) OR (TABLE_SUBSET = :table_subset AND EFFECTIVE_DATE = :effective_date AND POLICY_TYPE = :policy_type AND SEX_CODE = :sex_code AND MEMO_CODE = :memo_code AND MAX_ISSUE_AGE = :max_issue_age AND MAX_DURATION = :max_duration AND MAX_COVERAGE_UNITS > :max_coverage_units) OR (TABLE_SUBSET = :table_subset AND EFFECTIVE_DATE = :effective_date AND POLICY_TYPE = :policy_type AND SEX_CODE = :sex_code AND MEMO_CODE = :memo_code AND MAX_ISSUE_AGE = :max_issue_age AND MAX_DURATION = :max_duration AND MAX_COVERAGE_UNITS = :max_coverage_units AND MAX_CASH_VALUE > :max_cash_value) OR (TABLE_SUBSET = :table_subset AND EFFECTIVE_DATE = :effective_date AND POLICY_TYPE = :policy_type AND SEX_CODE = :sex_code AND MEMO_CODE = :memo_code AND MAX_ISSUE_AGE = :max_issue_age AND MAX_DURATION = :max_duration AND MAX_COVERAGE_UNITS = :max_coverage_units AND MAX_CASH_VALUE = :max_cash_value)) ";
                else
                    pagingWhere = ".T042E WHERE (COMPANY_CODE = :company_code) AND ((TABLE_SUBSET > :table_subset) OR (TABLE_SUBSET = :table_subset AND EFFECTIVE_DATE > :effective_date) OR (TABLE_SUBSET = :table_subset AND EFFECTIVE_DATE = :effective_date AND POLICY_TYPE > :policy_type) OR (TABLE_SUBSET = :table_subset AND EFFECTIVE_DATE = :effective_date AND POLICY_TYPE = :policy_type AND SEX_CODE > :sex_code) OR (TABLE_SUBSET = :table_subset AND EFFECTIVE_DATE = :effective_date AND POLICY_TYPE = :policy_type AND SEX_CODE = :sex_code AND MEMO_CODE > :memo_code) OR (TABLE_SUBSET = :table_subset AND EFFECTIVE_DATE = :effective_date AND POLICY_TYPE = :policy_type AND SEX_CODE = :sex_code AND MEMO_CODE = :memo_code AND MAX_ISSUE_AGE > :max_issue_age) OR (TABLE_SUBSET = :table_subset AND EFFECTIVE_DATE = :effective_date AND POLICY_TYPE = :policy_type AND SEX_CODE = :sex_code AND MEMO_CODE = :memo_code AND MAX_ISSUE_AGE = :max_issue_age AND MAX_DURATION > :max_duration) OR (TABLE_SUBSET = :table_subset AND EFFECTIVE_DATE = :effective_date AND POLICY_TYPE = :policy_type AND SEX_CODE = :sex_code AND MEMO_CODE = :memo_code AND MAX_ISSUE_AGE = :max_issue_age AND MAX_DURATION = :max_duration AND MAX_COVERAGE_UNITS > :max_coverage_units) OR (TABLE_SUBSET = :table_subset AND EFFECTIVE_DATE = :effective_date AND POLICY_TYPE = :policy_type AND SEX_CODE = :sex_code AND MEMO_CODE = :memo_code AND MAX_ISSUE_AGE = :max_issue_age AND MAX_DURATION = :max_duration AND MAX_COVERAGE_UNITS = :max_coverage_units AND MAX_CASH_VALUE > :max_cash_value)) ";
            order = " ORDER BY COMPANY_CODE, TABLE_SUBSET, EFFECTIVE_DATE, POLICY_TYPE, SEX_CODE, MEMO_CODE, MAX_ISSUE_AGE, MAX_DURATION, MAX_COVERAGE_UNITS, MAX_CASH_VALUE";
        }
        else {
            if (isSubsetMode)
                pagingWhere = ".T042E WHERE (COMPANY_CODE = :company_code) AND (TABLE_SUBSET = :table_subset) AND ((EFFECTIVE_DATE < :effective_date) OR (EFFECTIVE_DATE = :effective_date AND POLICY_TYPE < :policy_type) OR (EFFECTIVE_DATE = :effective_date AND POLICY_TYPE = :policy_type AND SEX_CODE < :sex_code) OR (EFFECTIVE_DATE = :effective_date AND POLICY_TYPE = :policy_type AND SEX_CODE = :sex_code AND MEMO_CODE < :memo_code) OR (EFFECTIVE_DATE = :effective_date AND POLICY_TYPE = :policy_type AND SEX_CODE = :sex_code AND MEMO_CODE = :memo_code AND MAX_ISSUE_AGE < :max_issue_age) OR (EFFECTIVE_DATE = :effective_date AND POLICY_TYPE = :policy_type AND SEX_CODE = :sex_code AND MEMO_CODE = :memo_code AND MAX_ISSUE_AGE = :max_issue_age AND MAX_DURATION < :max_duration) OR (EFFECTIVE_DATE = :effective_date AND POLICY_TYPE = :policy_type AND SEX_CODE = :sex_code AND MEMO_CODE = :memo_code AND MAX_ISSUE_AGE = :max_issue_age AND MAX_DURATION = :max_duration AND MAX_COVERAGE_UNITS < :max_coverage_units) OR (EFFECTIVE_DATE = :effective_date AND POLICY_TYPE = :policy_type AND SEX_CODE = :sex_code AND MEMO_CODE = :memo_code AND MAX_ISSUE_AGE = :max_issue_age AND MAX_DURATION = :max_duration AND MAX_COVERAGE_UNITS = :max_coverage_units AND MAX_CASH_VALUE < :max_cash_value)) ";
            else
                pagingWhere = ".T042E WHERE (COMPANY_CODE = :company_code) AND ((TABLE_SUBSET < :table_subset) OR (TABLE_SUBSET = :table_subset AND EFFECTIVE_DATE < :effective_date) OR (TABLE_SUBSET = :table_subset AND EFFECTIVE_DATE = :effective_date AND POLICY_TYPE < :policy_type) OR (TABLE_SUBSET = :table_subset AND EFFECTIVE_DATE = :effective_date AND POLICY_TYPE = :policy_type AND SEX_CODE < :sex_code) OR (TABLE_SUBSET = :table_subset AND EFFECTIVE_DATE = :effective_date AND POLICY_TYPE = :policy_type AND SEX_CODE = :sex_code AND MEMO_CODE < :memo_code) OR (TABLE_SUBSET = :table_subset AND EFFECTIVE_DATE = :effective_date AND POLICY_TYPE = :policy_type AND SEX_CODE = :sex_code AND MEMO_CODE = :memo_code AND MAX_ISSUE_AGE < :max_issue_age) OR (TABLE_SUBSET = :table_subset AND EFFECTIVE_DATE = :effective_date AND POLICY_TYPE = :policy_type AND SEX_CODE = :sex_code AND MEMO_CODE = :memo_code AND MAX_ISSUE_AGE = :max_issue_age AND MAX_DURATION < :max_duration) OR (TABLE_SUBSET = :table_subset AND EFFECTIVE_DATE = :effective_date AND POLICY_TYPE = :policy_type AND SEX_CODE = :sex_code AND MEMO_CODE = :memo_code AND MAX_ISSUE_AGE = :max_issue_age AND MAX_DURATION = :max_duration AND MAX_COVERAGE_UNITS < :max_coverage_units) OR (TABLE_SUBSET = :table_subset AND EFFECTIVE_DATE = :effective_date AND POLICY_TYPE = :policy_type AND SEX_CODE = :sex_code AND MEMO_CODE = :memo_code AND MAX_ISSUE_AGE = :max_issue_age AND MAX_DURATION = :max_duration AND MAX_COVERAGE_UNITS = :max_coverage_units AND MAX_CASH_VALUE < :max_cash_value)) ";
            order = " ORDER BY COMPANY_CODE DESC, TABLE_SUBSET DESC, EFFECTIVE_DATE DESC, POLICY_TYPE DESC, SEX_CODE DESC, MEMO_CODE DESC, MAX_ISSUE_AGE DESC, MAX_DURATION DESC, MAX_COVERAGE_UNITS DESC, MAX_CASH_VALUE DESC";
        }
        return pagingSql + schemaName + pagingWhere + order;
    }
    
    public String prepareInsertStmt(String schemaName) {
        StringBuffer sb = new StringBuffer();
        sb.append("INSERT INTO ").append(schemaName);
        sb.append(".T042E ( ");
        sb.append("COMPANY_CODE, TABLE_SUBSET, EFFECTIVE_DATE, POLICY_TYPE, SEX_CODE, MEMO_CODE, MAX_ISSUE_AGE, MAX_DURATION, MAX_COVERAGE_UNITS, MAX_CASH_VALUE, INHIBIT_CHARGE_IND, PCT_CASH_VALUE )");
        sb.append(" VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ? )");
        return sb.toString();
    }
    
    public String prepareUpdateStmt(String schemaName)
    {
        StringBuffer sb = new StringBuffer();
        sb.append("UPDATE ").append(schemaName);
        sb.append(".T042E ");
        sb.append(" SET COMPANY_CODE = ?, TABLE_SUBSET = ?, EFFECTIVE_DATE = ?, POLICY_TYPE = ?, SEX_CODE = ?, MEMO_CODE = ?, MAX_ISSUE_AGE = ?, MAX_DURATION = ?, MAX_COVERAGE_UNITS = ?, MAX_CASH_VALUE = ?, INHIBIT_CHARGE_IND = ?, PCT_CASH_VALUE = ?");
        sb.append(" WHERE COMPANY_CODE = ? AND TABLE_SUBSET = ? AND EFFECTIVE_DATE = ? AND POLICY_TYPE = ? AND SEX_CODE = ? AND MEMO_CODE = ? AND MAX_ISSUE_AGE = ? AND MAX_DURATION = ? AND MAX_COVERAGE_UNITS = ? AND MAX_CASH_VALUE = ?");
        return sb.toString();
    }
    
    public String prepareDeleteStmt(String schemaName)
    {
        StringBuffer sb = new StringBuffer();
        sb.append("DELETE FROM ").append(schemaName);
        sb.append(".T042E ");
        sb.append(" WHERE COMPANY_CODE = ? AND TABLE_SUBSET = ? AND EFFECTIVE_DATE = ? AND POLICY_TYPE = ? AND SEX_CODE = ? AND MEMO_CODE = ? AND MAX_ISSUE_AGE = ? AND MAX_DURATION = ? AND MAX_COVERAGE_UNITS = ? AND MAX_CASH_VALUE = ?");
        return sb.toString();
    }
}
