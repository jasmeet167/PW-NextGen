package com.csc.fsg.life.pw.io.descriptor.wma;

import com.csc.fsg.life.pw.io.*;

public class TA19FDescriptor
    extends TableDescriptor
{
    private static final String pagingSql = "SELECT COMPANY_CODE, TABLE_SUBSET, EFFECTIVE_DATE, TRANSACTION_CODE, SEX_CODE, MAXIMUM_ISSUE_AGE, MX_PLCY_DUR, CUM_PREM_TO_DT, DURATION_PREMIUM, ROLLOVER_IND, MEMO_CODE, CUM_PREM_IND, DUR_PREM_IND, LOAD_PCT_IND, LOAD_PERCENT, FLAT_DOLLAR_SFEE, PCT_DOLLAR_SFEE FROM ";
    
    public void initialize()
    {
        setRowClass(TA19FRow.class);
        setTableName("TA19F");
        setTableId("A19");
        super.initialize();
    }
    
    public void initializeColumnDescriptors()
    {
        super.initializeColumnDescriptors();
        addColumnDescriptor(new ColumnDescriptor(this,"getCompanyCode","setCompanyCode","COMPANY_CODE,1,1,3,0,true|,0,CHAR,1,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getTableSubset","setTableSubset","TABLE_SUBSET,1,2,16,0,true|,0,CHAR,1,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getEffectiveDate","setEffectiveDate","EFFECTIVE_DATE,91,3,10,0,true|,0,DATE,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getTransactionCode","setTransactionCode","TRANSACTION_CODE,1,4,4,0,true|,0,CHAR,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getSexCode","setSexCode","SEX_CODE,1,5,1,0,true|,0,CHAR,2,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getMaximumIssueAge","setMaximumIssueAge","MAXIMUM_ISSUE_AGE,3,6,3,0,true|,0,INTEGER,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getMxPlcyDur","setMxPlcyDur","MX_PLCY_DUR,3,7,2,0,true|,0,INTEGER,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getCumPremToDt","setCumPremToDt","CUM_PREM_TO_DT,3,8,11,2,true|,0,DOUBLE,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getDurationPremium","setDurationPremium","DURATION_PREMIUM,3,9,11,2,true|,0,DOUBLE,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getRolloverInd","setRolloverInd","ROLLOVER_IND,1,10,1,0,true|,0,CHAR,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getMemoCode","setMemoCode","MEMO_CODE,1,11,2,0,true|,0,CHAR,4,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getCumPremInd","setCumPremInd","CUM_PREM_IND,1,12,1,0,false|,0,CHAR,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getDurPremInd","setDurPremInd","DUR_PREM_IND,1,13,1,0,false|,0,CHAR,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getLoadPctInd","setLoadPctInd","LOAD_PCT_IND,1,14,1,0,false|,0,CHAR,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getLoadPercent","setLoadPercent","LOAD_PERCENT,3,15,7,5,false|,0,DOUBLE,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getFlatDollarSfee","setFlatDollarSfee","FLAT_DOLLAR_SFEE,3,16,7,2,false|,0,DOUBLE,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getPctDollarSfee","setPctDollarSfee","PCT_DOLLAR_SFEE,3,17,6,3,false|,0,DOUBLE,0,null,null,null,null,null"));
    }
    
    public String getPagingSQL(String schemaName,boolean isSubsetMode,boolean isNext, boolean locator)
    {
        String pagingWhere = null;
        String order = null;
        if (isNext) {
            if (isSubsetMode)
                if (locator)
                    pagingWhere = ".TA19F WHERE (COMPANY_CODE = :company_code) AND (TABLE_SUBSET = :table_subset) AND ((EFFECTIVE_DATE > :effective_date) OR (EFFECTIVE_DATE = :effective_date AND TRANSACTION_CODE > :transaction_code) OR (EFFECTIVE_DATE = :effective_date AND TRANSACTION_CODE = :transaction_code AND SEX_CODE > :sex_code) OR (EFFECTIVE_DATE = :effective_date AND TRANSACTION_CODE = :transaction_code AND SEX_CODE = :sex_code AND MAXIMUM_ISSUE_AGE > :maximum_issue_age) OR (EFFECTIVE_DATE = :effective_date AND TRANSACTION_CODE = :transaction_code AND SEX_CODE = :sex_code AND MAXIMUM_ISSUE_AGE = :maximum_issue_age AND MX_PLCY_DUR > :mx_plcy_dur) OR (EFFECTIVE_DATE = :effective_date AND TRANSACTION_CODE = :transaction_code AND SEX_CODE = :sex_code AND MAXIMUM_ISSUE_AGE = :maximum_issue_age AND MX_PLCY_DUR = :mx_plcy_dur AND CUM_PREM_TO_DT > :cum_prem_to_dt) OR (EFFECTIVE_DATE = :effective_date AND TRANSACTION_CODE = :transaction_code AND SEX_CODE = :sex_code AND MAXIMUM_ISSUE_AGE = :maximum_issue_age AND MX_PLCY_DUR = :mx_plcy_dur AND CUM_PREM_TO_DT = :cum_prem_to_dt AND DURATION_PREMIUM > :duration_premium) OR (EFFECTIVE_DATE = :effective_date AND TRANSACTION_CODE = :transaction_code AND SEX_CODE = :sex_code AND MAXIMUM_ISSUE_AGE = :maximum_issue_age AND MX_PLCY_DUR = :mx_plcy_dur AND CUM_PREM_TO_DT = :cum_prem_to_dt AND DURATION_PREMIUM = :duration_premium AND ROLLOVER_IND > :rollover_ind) OR (EFFECTIVE_DATE = :effective_date AND TRANSACTION_CODE = :transaction_code AND SEX_CODE = :sex_code AND MAXIMUM_ISSUE_AGE = :maximum_issue_age AND MX_PLCY_DUR = :mx_plcy_dur AND CUM_PREM_TO_DT = :cum_prem_to_dt AND DURATION_PREMIUM = :duration_premium AND ROLLOVER_IND = :rollover_ind AND MEMO_CODE > :memo_code) OR (EFFECTIVE_DATE = :effective_date AND TRANSACTION_CODE = :transaction_code AND SEX_CODE = :sex_code AND MAXIMUM_ISSUE_AGE = :maximum_issue_age AND MX_PLCY_DUR = :mx_plcy_dur AND CUM_PREM_TO_DT = :cum_prem_to_dt AND DURATION_PREMIUM = :duration_premium AND ROLLOVER_IND = :rollover_ind AND MEMO_CODE = :memo_code)) ";
                else 
                    pagingWhere = ".TA19F WHERE (COMPANY_CODE = :company_code) AND (TABLE_SUBSET = :table_subset) AND ((EFFECTIVE_DATE > :effective_date) OR (EFFECTIVE_DATE = :effective_date AND TRANSACTION_CODE > :transaction_code) OR (EFFECTIVE_DATE = :effective_date AND TRANSACTION_CODE = :transaction_code AND SEX_CODE > :sex_code) OR (EFFECTIVE_DATE = :effective_date AND TRANSACTION_CODE = :transaction_code AND SEX_CODE = :sex_code AND MAXIMUM_ISSUE_AGE > :maximum_issue_age) OR (EFFECTIVE_DATE = :effective_date AND TRANSACTION_CODE = :transaction_code AND SEX_CODE = :sex_code AND MAXIMUM_ISSUE_AGE = :maximum_issue_age AND MX_PLCY_DUR > :mx_plcy_dur) OR (EFFECTIVE_DATE = :effective_date AND TRANSACTION_CODE = :transaction_code AND SEX_CODE = :sex_code AND MAXIMUM_ISSUE_AGE = :maximum_issue_age AND MX_PLCY_DUR = :mx_plcy_dur AND CUM_PREM_TO_DT > :cum_prem_to_dt) OR (EFFECTIVE_DATE = :effective_date AND TRANSACTION_CODE = :transaction_code AND SEX_CODE = :sex_code AND MAXIMUM_ISSUE_AGE = :maximum_issue_age AND MX_PLCY_DUR = :mx_plcy_dur AND CUM_PREM_TO_DT = :cum_prem_to_dt AND DURATION_PREMIUM > :duration_premium) OR (EFFECTIVE_DATE = :effective_date AND TRANSACTION_CODE = :transaction_code AND SEX_CODE = :sex_code AND MAXIMUM_ISSUE_AGE = :maximum_issue_age AND MX_PLCY_DUR = :mx_plcy_dur AND CUM_PREM_TO_DT = :cum_prem_to_dt AND DURATION_PREMIUM = :duration_premium AND ROLLOVER_IND > :rollover_ind) OR (EFFECTIVE_DATE = :effective_date AND TRANSACTION_CODE = :transaction_code AND SEX_CODE = :sex_code AND MAXIMUM_ISSUE_AGE = :maximum_issue_age AND MX_PLCY_DUR = :mx_plcy_dur AND CUM_PREM_TO_DT = :cum_prem_to_dt AND DURATION_PREMIUM = :duration_premium AND ROLLOVER_IND = :rollover_ind AND MEMO_CODE > :memo_code)) ";
            else
                if (locator)
                    pagingWhere = ".TA19F WHERE (COMPANY_CODE = :company_code) AND ((TABLE_SUBSET > :table_subset) OR (TABLE_SUBSET = :table_subset AND EFFECTIVE_DATE > :effective_date) OR (TABLE_SUBSET = :table_subset AND EFFECTIVE_DATE = :effective_date AND TRANSACTION_CODE > :transaction_code) OR (TABLE_SUBSET = :table_subset AND EFFECTIVE_DATE = :effective_date AND TRANSACTION_CODE = :transaction_code AND SEX_CODE > :sex_code) OR (TABLE_SUBSET = :table_subset AND EFFECTIVE_DATE = :effective_date AND TRANSACTION_CODE = :transaction_code AND SEX_CODE = :sex_code AND MAXIMUM_ISSUE_AGE > :maximum_issue_age) OR (TABLE_SUBSET = :table_subset AND EFFECTIVE_DATE = :effective_date AND TRANSACTION_CODE = :transaction_code AND SEX_CODE = :sex_code AND MAXIMUM_ISSUE_AGE = :maximum_issue_age AND MX_PLCY_DUR > :mx_plcy_dur) OR (TABLE_SUBSET = :table_subset AND EFFECTIVE_DATE = :effective_date AND TRANSACTION_CODE = :transaction_code AND SEX_CODE = :sex_code AND MAXIMUM_ISSUE_AGE = :maximum_issue_age AND MX_PLCY_DUR = :mx_plcy_dur AND CUM_PREM_TO_DT > :cum_prem_to_dt) OR (TABLE_SUBSET = :table_subset AND EFFECTIVE_DATE = :effective_date AND TRANSACTION_CODE = :transaction_code AND SEX_CODE = :sex_code AND MAXIMUM_ISSUE_AGE = :maximum_issue_age AND MX_PLCY_DUR = :mx_plcy_dur AND CUM_PREM_TO_DT = :cum_prem_to_dt AND DURATION_PREMIUM > :duration_premium) OR (TABLE_SUBSET = :table_subset AND EFFECTIVE_DATE = :effective_date AND TRANSACTION_CODE = :transaction_code AND SEX_CODE = :sex_code AND MAXIMUM_ISSUE_AGE = :maximum_issue_age AND MX_PLCY_DUR = :mx_plcy_dur AND CUM_PREM_TO_DT = :cum_prem_to_dt AND DURATION_PREMIUM = :duration_premium AND ROLLOVER_IND > :rollover_ind) OR (TABLE_SUBSET = :table_subset AND EFFECTIVE_DATE = :effective_date AND TRANSACTION_CODE = :transaction_code AND SEX_CODE = :sex_code AND MAXIMUM_ISSUE_AGE = :maximum_issue_age AND MX_PLCY_DUR = :mx_plcy_dur AND CUM_PREM_TO_DT = :cum_prem_to_dt AND DURATION_PREMIUM = :duration_premium AND ROLLOVER_IND = :rollover_ind AND MEMO_CODE > :memo_code) OR (TABLE_SUBSET = :table_subset AND EFFECTIVE_DATE = :effective_date AND TRANSACTION_CODE = :transaction_code AND SEX_CODE = :sex_code AND MAXIMUM_ISSUE_AGE = :maximum_issue_age AND MX_PLCY_DUR = :mx_plcy_dur AND CUM_PREM_TO_DT = :cum_prem_to_dt AND DURATION_PREMIUM = :duration_premium AND ROLLOVER_IND = :rollover_ind AND MEMO_CODE = :memo_code)) ";
                else
                    pagingWhere = ".TA19F WHERE (COMPANY_CODE = :company_code) AND ((TABLE_SUBSET > :table_subset) OR (TABLE_SUBSET = :table_subset AND EFFECTIVE_DATE > :effective_date) OR (TABLE_SUBSET = :table_subset AND EFFECTIVE_DATE = :effective_date AND TRANSACTION_CODE > :transaction_code) OR (TABLE_SUBSET = :table_subset AND EFFECTIVE_DATE = :effective_date AND TRANSACTION_CODE = :transaction_code AND SEX_CODE > :sex_code) OR (TABLE_SUBSET = :table_subset AND EFFECTIVE_DATE = :effective_date AND TRANSACTION_CODE = :transaction_code AND SEX_CODE = :sex_code AND MAXIMUM_ISSUE_AGE > :maximum_issue_age) OR (TABLE_SUBSET = :table_subset AND EFFECTIVE_DATE = :effective_date AND TRANSACTION_CODE = :transaction_code AND SEX_CODE = :sex_code AND MAXIMUM_ISSUE_AGE = :maximum_issue_age AND MX_PLCY_DUR > :mx_plcy_dur) OR (TABLE_SUBSET = :table_subset AND EFFECTIVE_DATE = :effective_date AND TRANSACTION_CODE = :transaction_code AND SEX_CODE = :sex_code AND MAXIMUM_ISSUE_AGE = :maximum_issue_age AND MX_PLCY_DUR = :mx_plcy_dur AND CUM_PREM_TO_DT > :cum_prem_to_dt) OR (TABLE_SUBSET = :table_subset AND EFFECTIVE_DATE = :effective_date AND TRANSACTION_CODE = :transaction_code AND SEX_CODE = :sex_code AND MAXIMUM_ISSUE_AGE = :maximum_issue_age AND MX_PLCY_DUR = :mx_plcy_dur AND CUM_PREM_TO_DT = :cum_prem_to_dt AND DURATION_PREMIUM > :duration_premium) OR (TABLE_SUBSET = :table_subset AND EFFECTIVE_DATE = :effective_date AND TRANSACTION_CODE = :transaction_code AND SEX_CODE = :sex_code AND MAXIMUM_ISSUE_AGE = :maximum_issue_age AND MX_PLCY_DUR = :mx_plcy_dur AND CUM_PREM_TO_DT = :cum_prem_to_dt AND DURATION_PREMIUM = :duration_premium AND ROLLOVER_IND > :rollover_ind) OR (TABLE_SUBSET = :table_subset AND EFFECTIVE_DATE = :effective_date AND TRANSACTION_CODE = :transaction_code AND SEX_CODE = :sex_code AND MAXIMUM_ISSUE_AGE = :maximum_issue_age AND MX_PLCY_DUR = :mx_plcy_dur AND CUM_PREM_TO_DT = :cum_prem_to_dt AND DURATION_PREMIUM = :duration_premium AND ROLLOVER_IND = :rollover_ind AND MEMO_CODE > :memo_code)) ";
            order = " ORDER BY COMPANY_CODE, TABLE_SUBSET, EFFECTIVE_DATE, TRANSACTION_CODE, SEX_CODE, MAXIMUM_ISSUE_AGE, MX_PLCY_DUR, CUM_PREM_TO_DT, DURATION_PREMIUM, ROLLOVER_IND, MEMO_CODE";
        }
        else {
            if (isSubsetMode)
                pagingWhere = ".TA19F WHERE (COMPANY_CODE = :company_code) AND (TABLE_SUBSET = :table_subset) AND ((EFFECTIVE_DATE < :effective_date) OR (EFFECTIVE_DATE = :effective_date AND TRANSACTION_CODE < :transaction_code) OR (EFFECTIVE_DATE = :effective_date AND TRANSACTION_CODE = :transaction_code AND SEX_CODE < :sex_code) OR (EFFECTIVE_DATE = :effective_date AND TRANSACTION_CODE = :transaction_code AND SEX_CODE = :sex_code AND MAXIMUM_ISSUE_AGE < :maximum_issue_age) OR (EFFECTIVE_DATE = :effective_date AND TRANSACTION_CODE = :transaction_code AND SEX_CODE = :sex_code AND MAXIMUM_ISSUE_AGE = :maximum_issue_age AND MX_PLCY_DUR < :mx_plcy_dur) OR (EFFECTIVE_DATE = :effective_date AND TRANSACTION_CODE = :transaction_code AND SEX_CODE = :sex_code AND MAXIMUM_ISSUE_AGE = :maximum_issue_age AND MX_PLCY_DUR = :mx_plcy_dur AND CUM_PREM_TO_DT < :cum_prem_to_dt) OR (EFFECTIVE_DATE = :effective_date AND TRANSACTION_CODE = :transaction_code AND SEX_CODE = :sex_code AND MAXIMUM_ISSUE_AGE = :maximum_issue_age AND MX_PLCY_DUR = :mx_plcy_dur AND CUM_PREM_TO_DT = :cum_prem_to_dt AND DURATION_PREMIUM < :duration_premium) OR (EFFECTIVE_DATE = :effective_date AND TRANSACTION_CODE = :transaction_code AND SEX_CODE = :sex_code AND MAXIMUM_ISSUE_AGE = :maximum_issue_age AND MX_PLCY_DUR = :mx_plcy_dur AND CUM_PREM_TO_DT = :cum_prem_to_dt AND DURATION_PREMIUM = :duration_premium AND ROLLOVER_IND < :rollover_ind) OR (EFFECTIVE_DATE = :effective_date AND TRANSACTION_CODE = :transaction_code AND SEX_CODE = :sex_code AND MAXIMUM_ISSUE_AGE = :maximum_issue_age AND MX_PLCY_DUR = :mx_plcy_dur AND CUM_PREM_TO_DT = :cum_prem_to_dt AND DURATION_PREMIUM = :duration_premium AND ROLLOVER_IND = :rollover_ind AND MEMO_CODE < :memo_code)) ";
            else
                pagingWhere = ".TA19F WHERE (COMPANY_CODE = :company_code) AND ((TABLE_SUBSET < :table_subset) OR (TABLE_SUBSET = :table_subset AND EFFECTIVE_DATE < :effective_date) OR (TABLE_SUBSET = :table_subset AND EFFECTIVE_DATE = :effective_date AND TRANSACTION_CODE < :transaction_code) OR (TABLE_SUBSET = :table_subset AND EFFECTIVE_DATE = :effective_date AND TRANSACTION_CODE = :transaction_code AND SEX_CODE < :sex_code) OR (TABLE_SUBSET = :table_subset AND EFFECTIVE_DATE = :effective_date AND TRANSACTION_CODE = :transaction_code AND SEX_CODE = :sex_code AND MAXIMUM_ISSUE_AGE < :maximum_issue_age) OR (TABLE_SUBSET = :table_subset AND EFFECTIVE_DATE = :effective_date AND TRANSACTION_CODE = :transaction_code AND SEX_CODE = :sex_code AND MAXIMUM_ISSUE_AGE = :maximum_issue_age AND MX_PLCY_DUR < :mx_plcy_dur) OR (TABLE_SUBSET = :table_subset AND EFFECTIVE_DATE = :effective_date AND TRANSACTION_CODE = :transaction_code AND SEX_CODE = :sex_code AND MAXIMUM_ISSUE_AGE = :maximum_issue_age AND MX_PLCY_DUR = :mx_plcy_dur AND CUM_PREM_TO_DT < :cum_prem_to_dt) OR (TABLE_SUBSET = :table_subset AND EFFECTIVE_DATE = :effective_date AND TRANSACTION_CODE = :transaction_code AND SEX_CODE = :sex_code AND MAXIMUM_ISSUE_AGE = :maximum_issue_age AND MX_PLCY_DUR = :mx_plcy_dur AND CUM_PREM_TO_DT = :cum_prem_to_dt AND DURATION_PREMIUM < :duration_premium) OR (TABLE_SUBSET = :table_subset AND EFFECTIVE_DATE = :effective_date AND TRANSACTION_CODE = :transaction_code AND SEX_CODE = :sex_code AND MAXIMUM_ISSUE_AGE = :maximum_issue_age AND MX_PLCY_DUR = :mx_plcy_dur AND CUM_PREM_TO_DT = :cum_prem_to_dt AND DURATION_PREMIUM = :duration_premium AND ROLLOVER_IND < :rollover_ind) OR (TABLE_SUBSET = :table_subset AND EFFECTIVE_DATE = :effective_date AND TRANSACTION_CODE = :transaction_code AND SEX_CODE = :sex_code AND MAXIMUM_ISSUE_AGE = :maximum_issue_age AND MX_PLCY_DUR = :mx_plcy_dur AND CUM_PREM_TO_DT = :cum_prem_to_dt AND DURATION_PREMIUM = :duration_premium AND ROLLOVER_IND = :rollover_ind AND MEMO_CODE < :memo_code)) ";
            order = " ORDER BY COMPANY_CODE DESC, TABLE_SUBSET DESC, EFFECTIVE_DATE DESC, TRANSACTION_CODE DESC, SEX_CODE DESC, MAXIMUM_ISSUE_AGE DESC, MX_PLCY_DUR DESC, CUM_PREM_TO_DT DESC, DURATION_PREMIUM DESC, ROLLOVER_IND DESC, MEMO_CODE DESC";
        }
        return pagingSql + schemaName + pagingWhere + order;
    }
    
    public String prepareInsertStmt(String schemaName) {
        StringBuffer sb = new StringBuffer();
        sb.append("INSERT INTO ").append(schemaName);
        sb.append(".TA19F ( ");
        sb.append("COMPANY_CODE, TABLE_SUBSET, EFFECTIVE_DATE, TRANSACTION_CODE, SEX_CODE, MAXIMUM_ISSUE_AGE, MX_PLCY_DUR, CUM_PREM_TO_DT, DURATION_PREMIUM, ROLLOVER_IND, MEMO_CODE, CUM_PREM_IND, DUR_PREM_IND, LOAD_PCT_IND, LOAD_PERCENT, FLAT_DOLLAR_SFEE, PCT_DOLLAR_SFEE )");
        sb.append(" VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ? )");
        return sb.toString();
    }
    
    public String prepareUpdateStmt(String schemaName)
    {
        StringBuffer sb = new StringBuffer();
        sb.append("UPDATE ").append(schemaName);
        sb.append(".TA19F ");
        sb.append(" SET COMPANY_CODE = ?, TABLE_SUBSET = ?, EFFECTIVE_DATE = ?, TRANSACTION_CODE = ?, SEX_CODE = ?, MAXIMUM_ISSUE_AGE = ?, MX_PLCY_DUR = ?, CUM_PREM_TO_DT = ?, DURATION_PREMIUM = ?, ROLLOVER_IND = ?, MEMO_CODE = ?, CUM_PREM_IND = ?, DUR_PREM_IND = ?, LOAD_PCT_IND = ?, LOAD_PERCENT = ?, FLAT_DOLLAR_SFEE = ?, PCT_DOLLAR_SFEE = ?");
        sb.append(" WHERE COMPANY_CODE = ? AND TABLE_SUBSET = ? AND EFFECTIVE_DATE = ? AND TRANSACTION_CODE = ? AND SEX_CODE = ? AND MAXIMUM_ISSUE_AGE = ? AND MX_PLCY_DUR = ? AND CUM_PREM_TO_DT = ? AND DURATION_PREMIUM = ? AND ROLLOVER_IND = ? AND MEMO_CODE = ?");
        return sb.toString();
    }
    
    public String prepareDeleteStmt(String schemaName)
    {
        StringBuffer sb = new StringBuffer();
        sb.append("DELETE FROM ").append(schemaName);
        sb.append(".TA19F ");
        sb.append(" WHERE COMPANY_CODE = ? AND TABLE_SUBSET = ? AND EFFECTIVE_DATE = ? AND TRANSACTION_CODE = ? AND SEX_CODE = ? AND MAXIMUM_ISSUE_AGE = ? AND MX_PLCY_DUR = ? AND CUM_PREM_TO_DT = ? AND DURATION_PREMIUM = ? AND ROLLOVER_IND = ? AND MEMO_CODE = ?");
        return sb.toString();
    }
}
