package com.csc.fsg.life.pw.web.io.descriptor.wma;

import com.csc.fsg.life.pw.web.io.*;

public class TAB3FDescriptor
    extends TableDescriptor
{
    private static final String pagingSql = "SELECT COMPANY_CODE, TABLE_SUBSET, FUND_NUMBER, EFFECTIVE_DATE, MAXIMUM_ISSUE_AGE, MAX_POL_DURATION, PAYMENT_MODE, PAYMENT_METHOD, CUM_CONTRIBUTIONS, MEMO_CODE, ADMIN_SLS_FEE_TMNG, CHARGE_INDICATOR, ADMIN_SLS_PCT_CHG, ADMIN_SLS_FLAT_CHG FROM ";
    
    public void initialize()
    {
        setRowClass(TAB3FRow.class);
        setTableName("TAB3F");
        setTableId("AB3");
        super.initialize();
    }
    
    public void initializeColumnDescriptors()
    {
        super.initializeColumnDescriptors();
        addColumnDescriptor(new ColumnDescriptor(this,"getCompanyCode","setCompanyCode","COMPANY_CODE,1,1,3,0,true|,0,CHAR,1,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getTableSubset","setTableSubset","TABLE_SUBSET,1,2,16,0,true|,0,CHAR,1,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getFundNumber","setFundNumber","FUND_NUMBER,3,3,8,0,true|,0,INTEGER,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getEffectiveDate","setEffectiveDate","EFFECTIVE_DATE,91,4,10,0,true|,0,DATE,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getMaximumIssueAge","setMaximumIssueAge","MAXIMUM_ISSUE_AGE,3,5,3,0,true|,0,INTEGER,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getMaxPolDuration","setMaxPolDuration","MAX_POL_DURATION,3,6,3,0,true|,0,INTEGER,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getPaymentMode","setPaymentMode","PAYMENT_MODE,1,7,2,0,true|,0,CHAR,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getPaymentMethod","setPaymentMethod","PAYMENT_METHOD,1,8,1,0,true|,0,CHAR,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getCumContributions","setCumContributions","CUM_CONTRIBUTIONS,3,9,12,2,true|,0,DOUBLE,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getMemoCode","setMemoCode","MEMO_CODE,1,10,2,0,true|,0,CHAR,4,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getAdminSlsFeeTmng","setAdminSlsFeeTmng","ADMIN_SLS_FEE_TMNG,1,11,1,0,false|,0,CHAR,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getChargeIndicator","setChargeIndicator","CHARGE_INDICATOR,1,12,1,0,false|,0,CHAR,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getAdminSlsPctChg","setAdminSlsPctChg","ADMIN_SLS_PCT_CHG,3,13,6,3,false|,0,DOUBLE,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getAdminSlsFlatChg","setAdminSlsFlatChg","ADMIN_SLS_FLAT_CHG,3,14,9,2,false|,0,DOUBLE,0,null,null,null,null,null"));
    }
    
    public String getPagingSQL(String schemaName,boolean isSubsetMode,boolean isNext, boolean locator)
    {
        String pagingWhere = null;
        String order = null;
        if (isNext) {
            if (isSubsetMode)
                if (locator)
                    pagingWhere = ".TAB3F WHERE (COMPANY_CODE = :company_code) AND (TABLE_SUBSET = :table_subset) AND ((FUND_NUMBER > :fund_number) OR (FUND_NUMBER = :fund_number AND EFFECTIVE_DATE > :effective_date) OR (FUND_NUMBER = :fund_number AND EFFECTIVE_DATE = :effective_date AND MAXIMUM_ISSUE_AGE > :maximum_issue_age) OR (FUND_NUMBER = :fund_number AND EFFECTIVE_DATE = :effective_date AND MAXIMUM_ISSUE_AGE = :maximum_issue_age AND MAX_POL_DURATION > :max_pol_duration) OR (FUND_NUMBER = :fund_number AND EFFECTIVE_DATE = :effective_date AND MAXIMUM_ISSUE_AGE = :maximum_issue_age AND MAX_POL_DURATION = :max_pol_duration AND PAYMENT_MODE > :payment_mode) OR (FUND_NUMBER = :fund_number AND EFFECTIVE_DATE = :effective_date AND MAXIMUM_ISSUE_AGE = :maximum_issue_age AND MAX_POL_DURATION = :max_pol_duration AND PAYMENT_MODE = :payment_mode AND PAYMENT_METHOD > :payment_method) OR (FUND_NUMBER = :fund_number AND EFFECTIVE_DATE = :effective_date AND MAXIMUM_ISSUE_AGE = :maximum_issue_age AND MAX_POL_DURATION = :max_pol_duration AND PAYMENT_MODE = :payment_mode AND PAYMENT_METHOD = :payment_method AND CUM_CONTRIBUTIONS > :cum_contributions) OR (FUND_NUMBER = :fund_number AND EFFECTIVE_DATE = :effective_date AND MAXIMUM_ISSUE_AGE = :maximum_issue_age AND MAX_POL_DURATION = :max_pol_duration AND PAYMENT_MODE = :payment_mode AND PAYMENT_METHOD = :payment_method AND CUM_CONTRIBUTIONS = :cum_contributions AND MEMO_CODE > :memo_code) OR (FUND_NUMBER = :fund_number AND EFFECTIVE_DATE = :effective_date AND MAXIMUM_ISSUE_AGE = :maximum_issue_age AND MAX_POL_DURATION = :max_pol_duration AND PAYMENT_MODE = :payment_mode AND PAYMENT_METHOD = :payment_method AND CUM_CONTRIBUTIONS = :cum_contributions AND MEMO_CODE = :memo_code)) ";
                else 
                    pagingWhere = ".TAB3F WHERE (COMPANY_CODE = :company_code) AND (TABLE_SUBSET = :table_subset) AND ((FUND_NUMBER > :fund_number) OR (FUND_NUMBER = :fund_number AND EFFECTIVE_DATE > :effective_date) OR (FUND_NUMBER = :fund_number AND EFFECTIVE_DATE = :effective_date AND MAXIMUM_ISSUE_AGE > :maximum_issue_age) OR (FUND_NUMBER = :fund_number AND EFFECTIVE_DATE = :effective_date AND MAXIMUM_ISSUE_AGE = :maximum_issue_age AND MAX_POL_DURATION > :max_pol_duration) OR (FUND_NUMBER = :fund_number AND EFFECTIVE_DATE = :effective_date AND MAXIMUM_ISSUE_AGE = :maximum_issue_age AND MAX_POL_DURATION = :max_pol_duration AND PAYMENT_MODE > :payment_mode) OR (FUND_NUMBER = :fund_number AND EFFECTIVE_DATE = :effective_date AND MAXIMUM_ISSUE_AGE = :maximum_issue_age AND MAX_POL_DURATION = :max_pol_duration AND PAYMENT_MODE = :payment_mode AND PAYMENT_METHOD > :payment_method) OR (FUND_NUMBER = :fund_number AND EFFECTIVE_DATE = :effective_date AND MAXIMUM_ISSUE_AGE = :maximum_issue_age AND MAX_POL_DURATION = :max_pol_duration AND PAYMENT_MODE = :payment_mode AND PAYMENT_METHOD = :payment_method AND CUM_CONTRIBUTIONS > :cum_contributions) OR (FUND_NUMBER = :fund_number AND EFFECTIVE_DATE = :effective_date AND MAXIMUM_ISSUE_AGE = :maximum_issue_age AND MAX_POL_DURATION = :max_pol_duration AND PAYMENT_MODE = :payment_mode AND PAYMENT_METHOD = :payment_method AND CUM_CONTRIBUTIONS = :cum_contributions AND MEMO_CODE > :memo_code)) ";
            else
                if (locator)
                    pagingWhere = ".TAB3F WHERE (COMPANY_CODE = :company_code) AND ((TABLE_SUBSET > :table_subset) OR (TABLE_SUBSET = :table_subset AND FUND_NUMBER > :fund_number) OR (TABLE_SUBSET = :table_subset AND FUND_NUMBER = :fund_number AND EFFECTIVE_DATE > :effective_date) OR (TABLE_SUBSET = :table_subset AND FUND_NUMBER = :fund_number AND EFFECTIVE_DATE = :effective_date AND MAXIMUM_ISSUE_AGE > :maximum_issue_age) OR (TABLE_SUBSET = :table_subset AND FUND_NUMBER = :fund_number AND EFFECTIVE_DATE = :effective_date AND MAXIMUM_ISSUE_AGE = :maximum_issue_age AND MAX_POL_DURATION > :max_pol_duration) OR (TABLE_SUBSET = :table_subset AND FUND_NUMBER = :fund_number AND EFFECTIVE_DATE = :effective_date AND MAXIMUM_ISSUE_AGE = :maximum_issue_age AND MAX_POL_DURATION = :max_pol_duration AND PAYMENT_MODE > :payment_mode) OR (TABLE_SUBSET = :table_subset AND FUND_NUMBER = :fund_number AND EFFECTIVE_DATE = :effective_date AND MAXIMUM_ISSUE_AGE = :maximum_issue_age AND MAX_POL_DURATION = :max_pol_duration AND PAYMENT_MODE = :payment_mode AND PAYMENT_METHOD > :payment_method) OR (TABLE_SUBSET = :table_subset AND FUND_NUMBER = :fund_number AND EFFECTIVE_DATE = :effective_date AND MAXIMUM_ISSUE_AGE = :maximum_issue_age AND MAX_POL_DURATION = :max_pol_duration AND PAYMENT_MODE = :payment_mode AND PAYMENT_METHOD = :payment_method AND CUM_CONTRIBUTIONS > :cum_contributions) OR (TABLE_SUBSET = :table_subset AND FUND_NUMBER = :fund_number AND EFFECTIVE_DATE = :effective_date AND MAXIMUM_ISSUE_AGE = :maximum_issue_age AND MAX_POL_DURATION = :max_pol_duration AND PAYMENT_MODE = :payment_mode AND PAYMENT_METHOD = :payment_method AND CUM_CONTRIBUTIONS = :cum_contributions AND MEMO_CODE > :memo_code) OR (TABLE_SUBSET = :table_subset AND FUND_NUMBER = :fund_number AND EFFECTIVE_DATE = :effective_date AND MAXIMUM_ISSUE_AGE = :maximum_issue_age AND MAX_POL_DURATION = :max_pol_duration AND PAYMENT_MODE = :payment_mode AND PAYMENT_METHOD = :payment_method AND CUM_CONTRIBUTIONS = :cum_contributions AND MEMO_CODE = :memo_code)) ";
                else
                    pagingWhere = ".TAB3F WHERE (COMPANY_CODE = :company_code) AND ((TABLE_SUBSET > :table_subset) OR (TABLE_SUBSET = :table_subset AND FUND_NUMBER > :fund_number) OR (TABLE_SUBSET = :table_subset AND FUND_NUMBER = :fund_number AND EFFECTIVE_DATE > :effective_date) OR (TABLE_SUBSET = :table_subset AND FUND_NUMBER = :fund_number AND EFFECTIVE_DATE = :effective_date AND MAXIMUM_ISSUE_AGE > :maximum_issue_age) OR (TABLE_SUBSET = :table_subset AND FUND_NUMBER = :fund_number AND EFFECTIVE_DATE = :effective_date AND MAXIMUM_ISSUE_AGE = :maximum_issue_age AND MAX_POL_DURATION > :max_pol_duration) OR (TABLE_SUBSET = :table_subset AND FUND_NUMBER = :fund_number AND EFFECTIVE_DATE = :effective_date AND MAXIMUM_ISSUE_AGE = :maximum_issue_age AND MAX_POL_DURATION = :max_pol_duration AND PAYMENT_MODE > :payment_mode) OR (TABLE_SUBSET = :table_subset AND FUND_NUMBER = :fund_number AND EFFECTIVE_DATE = :effective_date AND MAXIMUM_ISSUE_AGE = :maximum_issue_age AND MAX_POL_DURATION = :max_pol_duration AND PAYMENT_MODE = :payment_mode AND PAYMENT_METHOD > :payment_method) OR (TABLE_SUBSET = :table_subset AND FUND_NUMBER = :fund_number AND EFFECTIVE_DATE = :effective_date AND MAXIMUM_ISSUE_AGE = :maximum_issue_age AND MAX_POL_DURATION = :max_pol_duration AND PAYMENT_MODE = :payment_mode AND PAYMENT_METHOD = :payment_method AND CUM_CONTRIBUTIONS > :cum_contributions) OR (TABLE_SUBSET = :table_subset AND FUND_NUMBER = :fund_number AND EFFECTIVE_DATE = :effective_date AND MAXIMUM_ISSUE_AGE = :maximum_issue_age AND MAX_POL_DURATION = :max_pol_duration AND PAYMENT_MODE = :payment_mode AND PAYMENT_METHOD = :payment_method AND CUM_CONTRIBUTIONS = :cum_contributions AND MEMO_CODE > :memo_code)) ";
            order = " ORDER BY COMPANY_CODE, TABLE_SUBSET, FUND_NUMBER, EFFECTIVE_DATE, MAXIMUM_ISSUE_AGE, MAX_POL_DURATION, PAYMENT_MODE, PAYMENT_METHOD, CUM_CONTRIBUTIONS, MEMO_CODE";
        }
        else {
            if (isSubsetMode)
                pagingWhere = ".TAB3F WHERE (COMPANY_CODE = :company_code) AND (TABLE_SUBSET = :table_subset) AND ((FUND_NUMBER < :fund_number) OR (FUND_NUMBER = :fund_number AND EFFECTIVE_DATE < :effective_date) OR (FUND_NUMBER = :fund_number AND EFFECTIVE_DATE = :effective_date AND MAXIMUM_ISSUE_AGE < :maximum_issue_age) OR (FUND_NUMBER = :fund_number AND EFFECTIVE_DATE = :effective_date AND MAXIMUM_ISSUE_AGE = :maximum_issue_age AND MAX_POL_DURATION < :max_pol_duration) OR (FUND_NUMBER = :fund_number AND EFFECTIVE_DATE = :effective_date AND MAXIMUM_ISSUE_AGE = :maximum_issue_age AND MAX_POL_DURATION = :max_pol_duration AND PAYMENT_MODE < :payment_mode) OR (FUND_NUMBER = :fund_number AND EFFECTIVE_DATE = :effective_date AND MAXIMUM_ISSUE_AGE = :maximum_issue_age AND MAX_POL_DURATION = :max_pol_duration AND PAYMENT_MODE = :payment_mode AND PAYMENT_METHOD < :payment_method) OR (FUND_NUMBER = :fund_number AND EFFECTIVE_DATE = :effective_date AND MAXIMUM_ISSUE_AGE = :maximum_issue_age AND MAX_POL_DURATION = :max_pol_duration AND PAYMENT_MODE = :payment_mode AND PAYMENT_METHOD = :payment_method AND CUM_CONTRIBUTIONS < :cum_contributions) OR (FUND_NUMBER = :fund_number AND EFFECTIVE_DATE = :effective_date AND MAXIMUM_ISSUE_AGE = :maximum_issue_age AND MAX_POL_DURATION = :max_pol_duration AND PAYMENT_MODE = :payment_mode AND PAYMENT_METHOD = :payment_method AND CUM_CONTRIBUTIONS = :cum_contributions AND MEMO_CODE < :memo_code)) ";
            else
                pagingWhere = ".TAB3F WHERE (COMPANY_CODE = :company_code) AND ((TABLE_SUBSET < :table_subset) OR (TABLE_SUBSET = :table_subset AND FUND_NUMBER < :fund_number) OR (TABLE_SUBSET = :table_subset AND FUND_NUMBER = :fund_number AND EFFECTIVE_DATE < :effective_date) OR (TABLE_SUBSET = :table_subset AND FUND_NUMBER = :fund_number AND EFFECTIVE_DATE = :effective_date AND MAXIMUM_ISSUE_AGE < :maximum_issue_age) OR (TABLE_SUBSET = :table_subset AND FUND_NUMBER = :fund_number AND EFFECTIVE_DATE = :effective_date AND MAXIMUM_ISSUE_AGE = :maximum_issue_age AND MAX_POL_DURATION < :max_pol_duration) OR (TABLE_SUBSET = :table_subset AND FUND_NUMBER = :fund_number AND EFFECTIVE_DATE = :effective_date AND MAXIMUM_ISSUE_AGE = :maximum_issue_age AND MAX_POL_DURATION = :max_pol_duration AND PAYMENT_MODE < :payment_mode) OR (TABLE_SUBSET = :table_subset AND FUND_NUMBER = :fund_number AND EFFECTIVE_DATE = :effective_date AND MAXIMUM_ISSUE_AGE = :maximum_issue_age AND MAX_POL_DURATION = :max_pol_duration AND PAYMENT_MODE = :payment_mode AND PAYMENT_METHOD < :payment_method) OR (TABLE_SUBSET = :table_subset AND FUND_NUMBER = :fund_number AND EFFECTIVE_DATE = :effective_date AND MAXIMUM_ISSUE_AGE = :maximum_issue_age AND MAX_POL_DURATION = :max_pol_duration AND PAYMENT_MODE = :payment_mode AND PAYMENT_METHOD = :payment_method AND CUM_CONTRIBUTIONS < :cum_contributions) OR (TABLE_SUBSET = :table_subset AND FUND_NUMBER = :fund_number AND EFFECTIVE_DATE = :effective_date AND MAXIMUM_ISSUE_AGE = :maximum_issue_age AND MAX_POL_DURATION = :max_pol_duration AND PAYMENT_MODE = :payment_mode AND PAYMENT_METHOD = :payment_method AND CUM_CONTRIBUTIONS = :cum_contributions AND MEMO_CODE < :memo_code)) ";
            order = " ORDER BY COMPANY_CODE DESC, TABLE_SUBSET DESC, FUND_NUMBER DESC, EFFECTIVE_DATE DESC, MAXIMUM_ISSUE_AGE DESC, MAX_POL_DURATION DESC, PAYMENT_MODE DESC, PAYMENT_METHOD DESC, CUM_CONTRIBUTIONS DESC, MEMO_CODE DESC";
        }
        return pagingSql + schemaName + pagingWhere + order;
    }
    
    public String prepareInsertStmt(String schemaName) {
        StringBuffer sb = new StringBuffer();
        sb.append("INSERT INTO ").append(schemaName);
        sb.append(".TAB3F ( ");
        sb.append("COMPANY_CODE, TABLE_SUBSET, FUND_NUMBER, EFFECTIVE_DATE, MAXIMUM_ISSUE_AGE, MAX_POL_DURATION, PAYMENT_MODE, PAYMENT_METHOD, CUM_CONTRIBUTIONS, MEMO_CODE, ADMIN_SLS_FEE_TMNG, CHARGE_INDICATOR, ADMIN_SLS_PCT_CHG, ADMIN_SLS_FLAT_CHG )");
        sb.append(" VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ? )");
        return sb.toString();
    }
    
    public String prepareUpdateStmt(String schemaName)
    {
        StringBuffer sb = new StringBuffer();
        sb.append("UPDATE ").append(schemaName);
        sb.append(".TAB3F ");
        sb.append(" SET COMPANY_CODE = ?, TABLE_SUBSET = ?, FUND_NUMBER = ?, EFFECTIVE_DATE = ?, MAXIMUM_ISSUE_AGE = ?, MAX_POL_DURATION = ?, PAYMENT_MODE = ?, PAYMENT_METHOD = ?, CUM_CONTRIBUTIONS = ?, MEMO_CODE = ?, ADMIN_SLS_FEE_TMNG = ?, CHARGE_INDICATOR = ?, ADMIN_SLS_PCT_CHG = ?, ADMIN_SLS_FLAT_CHG = ?");
        sb.append(" WHERE COMPANY_CODE = ? AND TABLE_SUBSET = ? AND FUND_NUMBER = ? AND EFFECTIVE_DATE = ? AND MAXIMUM_ISSUE_AGE = ? AND MAX_POL_DURATION = ? AND PAYMENT_MODE = ? AND PAYMENT_METHOD = ? AND CUM_CONTRIBUTIONS = ? AND MEMO_CODE = ?");
        return sb.toString();
    }
    
    public String prepareDeleteStmt(String schemaName)
    {
        StringBuffer sb = new StringBuffer();
        sb.append("DELETE FROM ").append(schemaName);
        sb.append(".TAB3F ");
        sb.append(" WHERE COMPANY_CODE = ? AND TABLE_SUBSET = ? AND FUND_NUMBER = ? AND EFFECTIVE_DATE = ? AND MAXIMUM_ISSUE_AGE = ? AND MAX_POL_DURATION = ? AND PAYMENT_MODE = ? AND PAYMENT_METHOD = ? AND CUM_CONTRIBUTIONS = ? AND MEMO_CODE = ?");
        return sb.toString();
    }
}
