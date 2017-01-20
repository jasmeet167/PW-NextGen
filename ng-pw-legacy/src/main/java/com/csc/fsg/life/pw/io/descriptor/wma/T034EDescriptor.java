package com.csc.fsg.life.pw.io.descriptor.wma;

import com.csc.fsg.life.pw.io.*;

public class T034EDescriptor
    extends TableDescriptor
{
    private static final String pagingSql = "SELECT COMPANY_CODE, TABLE_SUBSET, EFFECTIVE_DATE, POLICY_TYPE, SEX_CODE, SPECIAL_CLASS, UNDERWRITE_BASIS, PREMIUM_CLASS, MEMO_CODE, MAX_ISSUE_AGE, MAX_DURATION, MAX_COVG_UNITS, MAX_CUM_GROSS_PAY, INH_CHARGE_IND, CHG_PER_UNIT_COVG FROM ";
    
    public void initialize()
    {
        setRowClass(T034ERow.class);
        setTableName("T034E");
        setTableId("034");
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
        addColumnDescriptor(new ColumnDescriptor(this,"getSpecialClass","setSpecialClass","SPECIAL_CLASS,1,6,2,0,true|,0,CHAR,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getUnderwriteBasis","setUnderwriteBasis","UNDERWRITE_BASIS,1,7,1,0,true|,0,CHAR,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getPremiumClass","setPremiumClass","PREMIUM_CLASS,1,8,1,0,true|,0,CHAR,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getMemoCode","setMemoCode","MEMO_CODE,1,9,2,0,true|,0,CHAR,4,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getMaxIssueAge","setMaxIssueAge","MAX_ISSUE_AGE,3,10,3,0,true|,0,INTEGER,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getMaxDuration","setMaxDuration","MAX_DURATION,3,11,4,0,true|,0,INTEGER,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getMaxCovgUnits","setMaxCovgUnits","MAX_COVG_UNITS,3,12,11,5,true|,0,DOUBLE,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getMaxCumGrossPay","setMaxCumGrossPay","MAX_CUM_GROSS_PAY,3,13,11,2,true|,0,DOUBLE,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getInhChargeInd","setInhChargeInd","INH_CHARGE_IND,1,14,1,0,false|,0,CHAR,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getChgPerUnitCovg","setChgPerUnitCovg","CHG_PER_UNIT_COVG,3,15,8,5,false|,0,DOUBLE,0,null,null,null,null,null"));
    }
    
    public String getPagingSQL(String schemaName,boolean isSubsetMode,boolean isNext, boolean locator)
    {
        String pagingWhere = null;
        String order = null;
        if (isNext) {
            if (isSubsetMode)
                if (locator)
                    pagingWhere = ".T034E WHERE (COMPANY_CODE = :company_code) AND (TABLE_SUBSET = :table_subset) AND ((EFFECTIVE_DATE > :effective_date) OR (EFFECTIVE_DATE = :effective_date AND POLICY_TYPE > :policy_type) OR (EFFECTIVE_DATE = :effective_date AND POLICY_TYPE = :policy_type AND SEX_CODE > :sex_code) OR (EFFECTIVE_DATE = :effective_date AND POLICY_TYPE = :policy_type AND SEX_CODE = :sex_code AND SPECIAL_CLASS > :special_class) OR (EFFECTIVE_DATE = :effective_date AND POLICY_TYPE = :policy_type AND SEX_CODE = :sex_code AND SPECIAL_CLASS = :special_class AND UNDERWRITE_BASIS > :underwrite_basis) OR (EFFECTIVE_DATE = :effective_date AND POLICY_TYPE = :policy_type AND SEX_CODE = :sex_code AND SPECIAL_CLASS = :special_class AND UNDERWRITE_BASIS = :underwrite_basis AND PREMIUM_CLASS > :premium_class) OR (EFFECTIVE_DATE = :effective_date AND POLICY_TYPE = :policy_type AND SEX_CODE = :sex_code AND SPECIAL_CLASS = :special_class AND UNDERWRITE_BASIS = :underwrite_basis AND PREMIUM_CLASS = :premium_class AND MEMO_CODE > :memo_code) OR (EFFECTIVE_DATE = :effective_date AND POLICY_TYPE = :policy_type AND SEX_CODE = :sex_code AND SPECIAL_CLASS = :special_class AND UNDERWRITE_BASIS = :underwrite_basis AND PREMIUM_CLASS = :premium_class AND MEMO_CODE = :memo_code AND MAX_ISSUE_AGE > :max_issue_age) OR (EFFECTIVE_DATE = :effective_date AND POLICY_TYPE = :policy_type AND SEX_CODE = :sex_code AND SPECIAL_CLASS = :special_class AND UNDERWRITE_BASIS = :underwrite_basis AND PREMIUM_CLASS = :premium_class AND MEMO_CODE = :memo_code AND MAX_ISSUE_AGE = :max_issue_age AND MAX_DURATION > :max_duration) OR (EFFECTIVE_DATE = :effective_date AND POLICY_TYPE = :policy_type AND SEX_CODE = :sex_code AND SPECIAL_CLASS = :special_class AND UNDERWRITE_BASIS = :underwrite_basis AND PREMIUM_CLASS = :premium_class AND MEMO_CODE = :memo_code AND MAX_ISSUE_AGE = :max_issue_age AND MAX_DURATION = :max_duration AND MAX_COVG_UNITS > :max_covg_units) OR (EFFECTIVE_DATE = :effective_date AND POLICY_TYPE = :policy_type AND SEX_CODE = :sex_code AND SPECIAL_CLASS = :special_class AND UNDERWRITE_BASIS = :underwrite_basis AND PREMIUM_CLASS = :premium_class AND MEMO_CODE = :memo_code AND MAX_ISSUE_AGE = :max_issue_age AND MAX_DURATION = :max_duration AND MAX_COVG_UNITS = :max_covg_units AND MAX_CUM_GROSS_PAY > :max_cum_gross_pay) OR (EFFECTIVE_DATE = :effective_date AND POLICY_TYPE = :policy_type AND SEX_CODE = :sex_code AND SPECIAL_CLASS = :special_class AND UNDERWRITE_BASIS = :underwrite_basis AND PREMIUM_CLASS = :premium_class AND MEMO_CODE = :memo_code AND MAX_ISSUE_AGE = :max_issue_age AND MAX_DURATION = :max_duration AND MAX_COVG_UNITS = :max_covg_units AND MAX_CUM_GROSS_PAY = :max_cum_gross_pay)) ";
                else 
                    pagingWhere = ".T034E WHERE (COMPANY_CODE = :company_code) AND (TABLE_SUBSET = :table_subset) AND ((EFFECTIVE_DATE > :effective_date) OR (EFFECTIVE_DATE = :effective_date AND POLICY_TYPE > :policy_type) OR (EFFECTIVE_DATE = :effective_date AND POLICY_TYPE = :policy_type AND SEX_CODE > :sex_code) OR (EFFECTIVE_DATE = :effective_date AND POLICY_TYPE = :policy_type AND SEX_CODE = :sex_code AND SPECIAL_CLASS > :special_class) OR (EFFECTIVE_DATE = :effective_date AND POLICY_TYPE = :policy_type AND SEX_CODE = :sex_code AND SPECIAL_CLASS = :special_class AND UNDERWRITE_BASIS > :underwrite_basis) OR (EFFECTIVE_DATE = :effective_date AND POLICY_TYPE = :policy_type AND SEX_CODE = :sex_code AND SPECIAL_CLASS = :special_class AND UNDERWRITE_BASIS = :underwrite_basis AND PREMIUM_CLASS > :premium_class) OR (EFFECTIVE_DATE = :effective_date AND POLICY_TYPE = :policy_type AND SEX_CODE = :sex_code AND SPECIAL_CLASS = :special_class AND UNDERWRITE_BASIS = :underwrite_basis AND PREMIUM_CLASS = :premium_class AND MEMO_CODE > :memo_code) OR (EFFECTIVE_DATE = :effective_date AND POLICY_TYPE = :policy_type AND SEX_CODE = :sex_code AND SPECIAL_CLASS = :special_class AND UNDERWRITE_BASIS = :underwrite_basis AND PREMIUM_CLASS = :premium_class AND MEMO_CODE = :memo_code AND MAX_ISSUE_AGE > :max_issue_age) OR (EFFECTIVE_DATE = :effective_date AND POLICY_TYPE = :policy_type AND SEX_CODE = :sex_code AND SPECIAL_CLASS = :special_class AND UNDERWRITE_BASIS = :underwrite_basis AND PREMIUM_CLASS = :premium_class AND MEMO_CODE = :memo_code AND MAX_ISSUE_AGE = :max_issue_age AND MAX_DURATION > :max_duration) OR (EFFECTIVE_DATE = :effective_date AND POLICY_TYPE = :policy_type AND SEX_CODE = :sex_code AND SPECIAL_CLASS = :special_class AND UNDERWRITE_BASIS = :underwrite_basis AND PREMIUM_CLASS = :premium_class AND MEMO_CODE = :memo_code AND MAX_ISSUE_AGE = :max_issue_age AND MAX_DURATION = :max_duration AND MAX_COVG_UNITS > :max_covg_units) OR (EFFECTIVE_DATE = :effective_date AND POLICY_TYPE = :policy_type AND SEX_CODE = :sex_code AND SPECIAL_CLASS = :special_class AND UNDERWRITE_BASIS = :underwrite_basis AND PREMIUM_CLASS = :premium_class AND MEMO_CODE = :memo_code AND MAX_ISSUE_AGE = :max_issue_age AND MAX_DURATION = :max_duration AND MAX_COVG_UNITS = :max_covg_units AND MAX_CUM_GROSS_PAY > :max_cum_gross_pay)) ";
            else
                if (locator)
                    pagingWhere = ".T034E WHERE (COMPANY_CODE = :company_code) AND ((TABLE_SUBSET > :table_subset) OR (TABLE_SUBSET = :table_subset AND EFFECTIVE_DATE > :effective_date) OR (TABLE_SUBSET = :table_subset AND EFFECTIVE_DATE = :effective_date AND POLICY_TYPE > :policy_type) OR (TABLE_SUBSET = :table_subset AND EFFECTIVE_DATE = :effective_date AND POLICY_TYPE = :policy_type AND SEX_CODE > :sex_code) OR (TABLE_SUBSET = :table_subset AND EFFECTIVE_DATE = :effective_date AND POLICY_TYPE = :policy_type AND SEX_CODE = :sex_code AND SPECIAL_CLASS > :special_class) OR (TABLE_SUBSET = :table_subset AND EFFECTIVE_DATE = :effective_date AND POLICY_TYPE = :policy_type AND SEX_CODE = :sex_code AND SPECIAL_CLASS = :special_class AND UNDERWRITE_BASIS > :underwrite_basis) OR (TABLE_SUBSET = :table_subset AND EFFECTIVE_DATE = :effective_date AND POLICY_TYPE = :policy_type AND SEX_CODE = :sex_code AND SPECIAL_CLASS = :special_class AND UNDERWRITE_BASIS = :underwrite_basis AND PREMIUM_CLASS > :premium_class) OR (TABLE_SUBSET = :table_subset AND EFFECTIVE_DATE = :effective_date AND POLICY_TYPE = :policy_type AND SEX_CODE = :sex_code AND SPECIAL_CLASS = :special_class AND UNDERWRITE_BASIS = :underwrite_basis AND PREMIUM_CLASS = :premium_class AND MEMO_CODE > :memo_code) OR (TABLE_SUBSET = :table_subset AND EFFECTIVE_DATE = :effective_date AND POLICY_TYPE = :policy_type AND SEX_CODE = :sex_code AND SPECIAL_CLASS = :special_class AND UNDERWRITE_BASIS = :underwrite_basis AND PREMIUM_CLASS = :premium_class AND MEMO_CODE = :memo_code AND MAX_ISSUE_AGE > :max_issue_age) OR (TABLE_SUBSET = :table_subset AND EFFECTIVE_DATE = :effective_date AND POLICY_TYPE = :policy_type AND SEX_CODE = :sex_code AND SPECIAL_CLASS = :special_class AND UNDERWRITE_BASIS = :underwrite_basis AND PREMIUM_CLASS = :premium_class AND MEMO_CODE = :memo_code AND MAX_ISSUE_AGE = :max_issue_age AND MAX_DURATION > :max_duration) OR (TABLE_SUBSET = :table_subset AND EFFECTIVE_DATE = :effective_date AND POLICY_TYPE = :policy_type AND SEX_CODE = :sex_code AND SPECIAL_CLASS = :special_class AND UNDERWRITE_BASIS = :underwrite_basis AND PREMIUM_CLASS = :premium_class AND MEMO_CODE = :memo_code AND MAX_ISSUE_AGE = :max_issue_age AND MAX_DURATION = :max_duration AND MAX_COVG_UNITS > :max_covg_units) OR (TABLE_SUBSET = :table_subset AND EFFECTIVE_DATE = :effective_date AND POLICY_TYPE = :policy_type AND SEX_CODE = :sex_code AND SPECIAL_CLASS = :special_class AND UNDERWRITE_BASIS = :underwrite_basis AND PREMIUM_CLASS = :premium_class AND MEMO_CODE = :memo_code AND MAX_ISSUE_AGE = :max_issue_age AND MAX_DURATION = :max_duration AND MAX_COVG_UNITS = :max_covg_units AND MAX_CUM_GROSS_PAY > :max_cum_gross_pay) OR (TABLE_SUBSET = :table_subset AND EFFECTIVE_DATE = :effective_date AND POLICY_TYPE = :policy_type AND SEX_CODE = :sex_code AND SPECIAL_CLASS = :special_class AND UNDERWRITE_BASIS = :underwrite_basis AND PREMIUM_CLASS = :premium_class AND MEMO_CODE = :memo_code AND MAX_ISSUE_AGE = :max_issue_age AND MAX_DURATION = :max_duration AND MAX_COVG_UNITS = :max_covg_units AND MAX_CUM_GROSS_PAY = :max_cum_gross_pay)) ";
                else
                    pagingWhere = ".T034E WHERE (COMPANY_CODE = :company_code) AND ((TABLE_SUBSET > :table_subset) OR (TABLE_SUBSET = :table_subset AND EFFECTIVE_DATE > :effective_date) OR (TABLE_SUBSET = :table_subset AND EFFECTIVE_DATE = :effective_date AND POLICY_TYPE > :policy_type) OR (TABLE_SUBSET = :table_subset AND EFFECTIVE_DATE = :effective_date AND POLICY_TYPE = :policy_type AND SEX_CODE > :sex_code) OR (TABLE_SUBSET = :table_subset AND EFFECTIVE_DATE = :effective_date AND POLICY_TYPE = :policy_type AND SEX_CODE = :sex_code AND SPECIAL_CLASS > :special_class) OR (TABLE_SUBSET = :table_subset AND EFFECTIVE_DATE = :effective_date AND POLICY_TYPE = :policy_type AND SEX_CODE = :sex_code AND SPECIAL_CLASS = :special_class AND UNDERWRITE_BASIS > :underwrite_basis) OR (TABLE_SUBSET = :table_subset AND EFFECTIVE_DATE = :effective_date AND POLICY_TYPE = :policy_type AND SEX_CODE = :sex_code AND SPECIAL_CLASS = :special_class AND UNDERWRITE_BASIS = :underwrite_basis AND PREMIUM_CLASS > :premium_class) OR (TABLE_SUBSET = :table_subset AND EFFECTIVE_DATE = :effective_date AND POLICY_TYPE = :policy_type AND SEX_CODE = :sex_code AND SPECIAL_CLASS = :special_class AND UNDERWRITE_BASIS = :underwrite_basis AND PREMIUM_CLASS = :premium_class AND MEMO_CODE > :memo_code) OR (TABLE_SUBSET = :table_subset AND EFFECTIVE_DATE = :effective_date AND POLICY_TYPE = :policy_type AND SEX_CODE = :sex_code AND SPECIAL_CLASS = :special_class AND UNDERWRITE_BASIS = :underwrite_basis AND PREMIUM_CLASS = :premium_class AND MEMO_CODE = :memo_code AND MAX_ISSUE_AGE > :max_issue_age) OR (TABLE_SUBSET = :table_subset AND EFFECTIVE_DATE = :effective_date AND POLICY_TYPE = :policy_type AND SEX_CODE = :sex_code AND SPECIAL_CLASS = :special_class AND UNDERWRITE_BASIS = :underwrite_basis AND PREMIUM_CLASS = :premium_class AND MEMO_CODE = :memo_code AND MAX_ISSUE_AGE = :max_issue_age AND MAX_DURATION > :max_duration) OR (TABLE_SUBSET = :table_subset AND EFFECTIVE_DATE = :effective_date AND POLICY_TYPE = :policy_type AND SEX_CODE = :sex_code AND SPECIAL_CLASS = :special_class AND UNDERWRITE_BASIS = :underwrite_basis AND PREMIUM_CLASS = :premium_class AND MEMO_CODE = :memo_code AND MAX_ISSUE_AGE = :max_issue_age AND MAX_DURATION = :max_duration AND MAX_COVG_UNITS > :max_covg_units) OR (TABLE_SUBSET = :table_subset AND EFFECTIVE_DATE = :effective_date AND POLICY_TYPE = :policy_type AND SEX_CODE = :sex_code AND SPECIAL_CLASS = :special_class AND UNDERWRITE_BASIS = :underwrite_basis AND PREMIUM_CLASS = :premium_class AND MEMO_CODE = :memo_code AND MAX_ISSUE_AGE = :max_issue_age AND MAX_DURATION = :max_duration AND MAX_COVG_UNITS = :max_covg_units AND MAX_CUM_GROSS_PAY > :max_cum_gross_pay)) ";
            order = " ORDER BY COMPANY_CODE, TABLE_SUBSET, EFFECTIVE_DATE, POLICY_TYPE, SEX_CODE, SPECIAL_CLASS, UNDERWRITE_BASIS, PREMIUM_CLASS, MEMO_CODE, MAX_ISSUE_AGE, MAX_DURATION, MAX_COVG_UNITS, MAX_CUM_GROSS_PAY";
        }
        else {
            if (isSubsetMode)
                pagingWhere = ".T034E WHERE (COMPANY_CODE = :company_code) AND (TABLE_SUBSET = :table_subset) AND ((EFFECTIVE_DATE < :effective_date) OR (EFFECTIVE_DATE = :effective_date AND POLICY_TYPE < :policy_type) OR (EFFECTIVE_DATE = :effective_date AND POLICY_TYPE = :policy_type AND SEX_CODE < :sex_code) OR (EFFECTIVE_DATE = :effective_date AND POLICY_TYPE = :policy_type AND SEX_CODE = :sex_code AND SPECIAL_CLASS < :special_class) OR (EFFECTIVE_DATE = :effective_date AND POLICY_TYPE = :policy_type AND SEX_CODE = :sex_code AND SPECIAL_CLASS = :special_class AND UNDERWRITE_BASIS < :underwrite_basis) OR (EFFECTIVE_DATE = :effective_date AND POLICY_TYPE = :policy_type AND SEX_CODE = :sex_code AND SPECIAL_CLASS = :special_class AND UNDERWRITE_BASIS = :underwrite_basis AND PREMIUM_CLASS < :premium_class) OR (EFFECTIVE_DATE = :effective_date AND POLICY_TYPE = :policy_type AND SEX_CODE = :sex_code AND SPECIAL_CLASS = :special_class AND UNDERWRITE_BASIS = :underwrite_basis AND PREMIUM_CLASS = :premium_class AND MEMO_CODE < :memo_code) OR (EFFECTIVE_DATE = :effective_date AND POLICY_TYPE = :policy_type AND SEX_CODE = :sex_code AND SPECIAL_CLASS = :special_class AND UNDERWRITE_BASIS = :underwrite_basis AND PREMIUM_CLASS = :premium_class AND MEMO_CODE = :memo_code AND MAX_ISSUE_AGE < :max_issue_age) OR (EFFECTIVE_DATE = :effective_date AND POLICY_TYPE = :policy_type AND SEX_CODE = :sex_code AND SPECIAL_CLASS = :special_class AND UNDERWRITE_BASIS = :underwrite_basis AND PREMIUM_CLASS = :premium_class AND MEMO_CODE = :memo_code AND MAX_ISSUE_AGE = :max_issue_age AND MAX_DURATION < :max_duration) OR (EFFECTIVE_DATE = :effective_date AND POLICY_TYPE = :policy_type AND SEX_CODE = :sex_code AND SPECIAL_CLASS = :special_class AND UNDERWRITE_BASIS = :underwrite_basis AND PREMIUM_CLASS = :premium_class AND MEMO_CODE = :memo_code AND MAX_ISSUE_AGE = :max_issue_age AND MAX_DURATION = :max_duration AND MAX_COVG_UNITS < :max_covg_units) OR (EFFECTIVE_DATE = :effective_date AND POLICY_TYPE = :policy_type AND SEX_CODE = :sex_code AND SPECIAL_CLASS = :special_class AND UNDERWRITE_BASIS = :underwrite_basis AND PREMIUM_CLASS = :premium_class AND MEMO_CODE = :memo_code AND MAX_ISSUE_AGE = :max_issue_age AND MAX_DURATION = :max_duration AND MAX_COVG_UNITS = :max_covg_units AND MAX_CUM_GROSS_PAY < :max_cum_gross_pay)) ";
            else
                pagingWhere = ".T034E WHERE (COMPANY_CODE = :company_code) AND ((TABLE_SUBSET < :table_subset) OR (TABLE_SUBSET = :table_subset AND EFFECTIVE_DATE < :effective_date) OR (TABLE_SUBSET = :table_subset AND EFFECTIVE_DATE = :effective_date AND POLICY_TYPE < :policy_type) OR (TABLE_SUBSET = :table_subset AND EFFECTIVE_DATE = :effective_date AND POLICY_TYPE = :policy_type AND SEX_CODE < :sex_code) OR (TABLE_SUBSET = :table_subset AND EFFECTIVE_DATE = :effective_date AND POLICY_TYPE = :policy_type AND SEX_CODE = :sex_code AND SPECIAL_CLASS < :special_class) OR (TABLE_SUBSET = :table_subset AND EFFECTIVE_DATE = :effective_date AND POLICY_TYPE = :policy_type AND SEX_CODE = :sex_code AND SPECIAL_CLASS = :special_class AND UNDERWRITE_BASIS < :underwrite_basis) OR (TABLE_SUBSET = :table_subset AND EFFECTIVE_DATE = :effective_date AND POLICY_TYPE = :policy_type AND SEX_CODE = :sex_code AND SPECIAL_CLASS = :special_class AND UNDERWRITE_BASIS = :underwrite_basis AND PREMIUM_CLASS < :premium_class) OR (TABLE_SUBSET = :table_subset AND EFFECTIVE_DATE = :effective_date AND POLICY_TYPE = :policy_type AND SEX_CODE = :sex_code AND SPECIAL_CLASS = :special_class AND UNDERWRITE_BASIS = :underwrite_basis AND PREMIUM_CLASS = :premium_class AND MEMO_CODE < :memo_code) OR (TABLE_SUBSET = :table_subset AND EFFECTIVE_DATE = :effective_date AND POLICY_TYPE = :policy_type AND SEX_CODE = :sex_code AND SPECIAL_CLASS = :special_class AND UNDERWRITE_BASIS = :underwrite_basis AND PREMIUM_CLASS = :premium_class AND MEMO_CODE = :memo_code AND MAX_ISSUE_AGE < :max_issue_age) OR (TABLE_SUBSET = :table_subset AND EFFECTIVE_DATE = :effective_date AND POLICY_TYPE = :policy_type AND SEX_CODE = :sex_code AND SPECIAL_CLASS = :special_class AND UNDERWRITE_BASIS = :underwrite_basis AND PREMIUM_CLASS = :premium_class AND MEMO_CODE = :memo_code AND MAX_ISSUE_AGE = :max_issue_age AND MAX_DURATION < :max_duration) OR (TABLE_SUBSET = :table_subset AND EFFECTIVE_DATE = :effective_date AND POLICY_TYPE = :policy_type AND SEX_CODE = :sex_code AND SPECIAL_CLASS = :special_class AND UNDERWRITE_BASIS = :underwrite_basis AND PREMIUM_CLASS = :premium_class AND MEMO_CODE = :memo_code AND MAX_ISSUE_AGE = :max_issue_age AND MAX_DURATION = :max_duration AND MAX_COVG_UNITS < :max_covg_units) OR (TABLE_SUBSET = :table_subset AND EFFECTIVE_DATE = :effective_date AND POLICY_TYPE = :policy_type AND SEX_CODE = :sex_code AND SPECIAL_CLASS = :special_class AND UNDERWRITE_BASIS = :underwrite_basis AND PREMIUM_CLASS = :premium_class AND MEMO_CODE = :memo_code AND MAX_ISSUE_AGE = :max_issue_age AND MAX_DURATION = :max_duration AND MAX_COVG_UNITS = :max_covg_units AND MAX_CUM_GROSS_PAY < :max_cum_gross_pay)) ";
            order = " ORDER BY COMPANY_CODE DESC, TABLE_SUBSET DESC, EFFECTIVE_DATE DESC, POLICY_TYPE DESC, SEX_CODE DESC, SPECIAL_CLASS DESC, UNDERWRITE_BASIS DESC, PREMIUM_CLASS DESC, MEMO_CODE DESC, MAX_ISSUE_AGE DESC, MAX_DURATION DESC, MAX_COVG_UNITS DESC, MAX_CUM_GROSS_PAY DESC";
        }
        return pagingSql + schemaName + pagingWhere + order;
    }
    
    public String prepareInsertStmt(String schemaName) {
        StringBuffer sb = new StringBuffer();
        sb.append("INSERT INTO ").append(schemaName);
        sb.append(".T034E ( ");
        sb.append("COMPANY_CODE, TABLE_SUBSET, EFFECTIVE_DATE, POLICY_TYPE, SEX_CODE, SPECIAL_CLASS, UNDERWRITE_BASIS, PREMIUM_CLASS, MEMO_CODE, MAX_ISSUE_AGE, MAX_DURATION, MAX_COVG_UNITS, MAX_CUM_GROSS_PAY, INH_CHARGE_IND, CHG_PER_UNIT_COVG )");
        sb.append(" VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ? )");
        return sb.toString();
    }
    
    public String prepareUpdateStmt(String schemaName)
    {
        StringBuffer sb = new StringBuffer();
        sb.append("UPDATE ").append(schemaName);
        sb.append(".T034E ");
        sb.append(" SET COMPANY_CODE = ?, TABLE_SUBSET = ?, EFFECTIVE_DATE = ?, POLICY_TYPE = ?, SEX_CODE = ?, SPECIAL_CLASS = ?, UNDERWRITE_BASIS = ?, PREMIUM_CLASS = ?, MEMO_CODE = ?, MAX_ISSUE_AGE = ?, MAX_DURATION = ?, MAX_COVG_UNITS = ?, MAX_CUM_GROSS_PAY = ?, INH_CHARGE_IND = ?, CHG_PER_UNIT_COVG = ?");
        sb.append(" WHERE COMPANY_CODE = ? AND TABLE_SUBSET = ? AND EFFECTIVE_DATE = ? AND POLICY_TYPE = ? AND SEX_CODE = ? AND SPECIAL_CLASS = ? AND UNDERWRITE_BASIS = ? AND PREMIUM_CLASS = ? AND MEMO_CODE = ? AND MAX_ISSUE_AGE = ? AND MAX_DURATION = ? AND MAX_COVG_UNITS = ? AND MAX_CUM_GROSS_PAY = ?");
        return sb.toString();
    }
    
    public String prepareDeleteStmt(String schemaName)
    {
        StringBuffer sb = new StringBuffer();
        sb.append("DELETE FROM ").append(schemaName);
        sb.append(".T034E ");
        sb.append(" WHERE COMPANY_CODE = ? AND TABLE_SUBSET = ? AND EFFECTIVE_DATE = ? AND POLICY_TYPE = ? AND SEX_CODE = ? AND SPECIAL_CLASS = ? AND UNDERWRITE_BASIS = ? AND PREMIUM_CLASS = ? AND MEMO_CODE = ? AND MAX_ISSUE_AGE = ? AND MAX_DURATION = ? AND MAX_COVG_UNITS = ? AND MAX_CUM_GROSS_PAY = ?");
        return sb.toString();
    }
}
