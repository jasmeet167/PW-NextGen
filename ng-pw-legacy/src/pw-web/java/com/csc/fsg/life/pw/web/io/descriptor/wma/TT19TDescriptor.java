package com.csc.fsg.life.pw.web.io.descriptor.wma;

import com.csc.fsg.life.pw.web.io.*;

public class TT19TDescriptor
    extends TableDescriptor
{
    private static final String pagingSql = "SELECT COMPANY_CODE, TABLE_SUBSET, EFFECTIVE_DATE, UNDERWRITING_BASIS, TRANSACTION_CODE, SEX_CODE, MAX_ISSUE_AGE, MAX_SEG_DUR, ROLLOVER_IND, MEMO_CODE, PMT_LD_PERCENT, SUBSTD_PMT_LD_PCT, ORDER_OF_PROC_CODE FROM ";
    
    public void initialize()
    {
        setRowClass(TT19TRow.class);
        setTableName("TT19T");
        setTableId("T19");
        super.initialize();
    }
    
    public void initializeColumnDescriptors()
    {
        super.initializeColumnDescriptors();
        addColumnDescriptor(new ColumnDescriptor(this,"getCompanyCode","setCompanyCode","COMPANY_CODE,1,1,3,0,true|,0,CHAR,1,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getTableSubset","setTableSubset","TABLE_SUBSET,1,2,16,0,true|,0,CHAR,1,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getEffectiveDate","setEffectiveDate","EFFECTIVE_DATE,91,3,10,0,true|,0,DATE,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getUnderwritingBasis","setUnderwritingBasis","UNDERWRITING_BASIS,1,4,1,0,true|,0,CHAR,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getTransactionCode","setTransactionCode","TRANSACTION_CODE,1,5,4,0,true|,0,CHAR,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getSexCode","setSexCode","SEX_CODE,1,6,1,0,true|,0,CHAR,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getMaxIssueAge","setMaxIssueAge","MAX_ISSUE_AGE,3,7,3,0,true|,0,INTEGER,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getMaxSegDur","setMaxSegDur","MAX_SEG_DUR,3,8,3,0,true|,0,INTEGER,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getRolloverInd","setRolloverInd","ROLLOVER_IND,1,9,1,0,true|,0,CHAR,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getMemoCode","setMemoCode","MEMO_CODE,1,10,2,0,true|,0,CHAR,4,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getPmtLdPercent","setPmtLdPercent","PMT_LD_PERCENT,3,11,7,4,false|,0,DOUBLE,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getSubstdPmtLdPct","setSubstdPmtLdPct","SUBSTD_PMT_LD_PCT,3,12,7,4,false|,0,DOUBLE,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getOrderOfProcCode","setOrderOfProcCode","ORDER_OF_PROC_CODE,1,13,1,0,false|,0,CHAR,0,null,null,null,null,null"));
    }
    
    public String getPagingSQL(String schemaName,boolean isSubsetMode,boolean isNext, boolean locator)
    {
        String pagingWhere = null;
        String order = null;
        if (isNext) {
            if (isSubsetMode)
                if (locator)
                    pagingWhere = ".TT19T WHERE (COMPANY_CODE = :company_code) AND (TABLE_SUBSET = :table_subset) AND ((EFFECTIVE_DATE > :effective_date) OR (EFFECTIVE_DATE = :effective_date AND UNDERWRITING_BASIS > :underwriting_basis) OR (EFFECTIVE_DATE = :effective_date AND UNDERWRITING_BASIS = :underwriting_basis AND TRANSACTION_CODE > :transaction_code) OR (EFFECTIVE_DATE = :effective_date AND UNDERWRITING_BASIS = :underwriting_basis AND TRANSACTION_CODE = :transaction_code AND SEX_CODE > :sex_code) OR (EFFECTIVE_DATE = :effective_date AND UNDERWRITING_BASIS = :underwriting_basis AND TRANSACTION_CODE = :transaction_code AND SEX_CODE = :sex_code AND MAX_ISSUE_AGE > :max_issue_age) OR (EFFECTIVE_DATE = :effective_date AND UNDERWRITING_BASIS = :underwriting_basis AND TRANSACTION_CODE = :transaction_code AND SEX_CODE = :sex_code AND MAX_ISSUE_AGE = :max_issue_age AND MAX_SEG_DUR > :max_seg_dur) OR (EFFECTIVE_DATE = :effective_date AND UNDERWRITING_BASIS = :underwriting_basis AND TRANSACTION_CODE = :transaction_code AND SEX_CODE = :sex_code AND MAX_ISSUE_AGE = :max_issue_age AND MAX_SEG_DUR = :max_seg_dur AND ROLLOVER_IND > :rollover_ind) OR (EFFECTIVE_DATE = :effective_date AND UNDERWRITING_BASIS = :underwriting_basis AND TRANSACTION_CODE = :transaction_code AND SEX_CODE = :sex_code AND MAX_ISSUE_AGE = :max_issue_age AND MAX_SEG_DUR = :max_seg_dur AND ROLLOVER_IND = :rollover_ind AND MEMO_CODE > :memo_code) OR (EFFECTIVE_DATE = :effective_date AND UNDERWRITING_BASIS = :underwriting_basis AND TRANSACTION_CODE = :transaction_code AND SEX_CODE = :sex_code AND MAX_ISSUE_AGE = :max_issue_age AND MAX_SEG_DUR = :max_seg_dur AND ROLLOVER_IND = :rollover_ind AND MEMO_CODE = :memo_code)) ";
                else 
                    pagingWhere = ".TT19T WHERE (COMPANY_CODE = :company_code) AND (TABLE_SUBSET = :table_subset) AND ((EFFECTIVE_DATE > :effective_date) OR (EFFECTIVE_DATE = :effective_date AND UNDERWRITING_BASIS > :underwriting_basis) OR (EFFECTIVE_DATE = :effective_date AND UNDERWRITING_BASIS = :underwriting_basis AND TRANSACTION_CODE > :transaction_code) OR (EFFECTIVE_DATE = :effective_date AND UNDERWRITING_BASIS = :underwriting_basis AND TRANSACTION_CODE = :transaction_code AND SEX_CODE > :sex_code) OR (EFFECTIVE_DATE = :effective_date AND UNDERWRITING_BASIS = :underwriting_basis AND TRANSACTION_CODE = :transaction_code AND SEX_CODE = :sex_code AND MAX_ISSUE_AGE > :max_issue_age) OR (EFFECTIVE_DATE = :effective_date AND UNDERWRITING_BASIS = :underwriting_basis AND TRANSACTION_CODE = :transaction_code AND SEX_CODE = :sex_code AND MAX_ISSUE_AGE = :max_issue_age AND MAX_SEG_DUR > :max_seg_dur) OR (EFFECTIVE_DATE = :effective_date AND UNDERWRITING_BASIS = :underwriting_basis AND TRANSACTION_CODE = :transaction_code AND SEX_CODE = :sex_code AND MAX_ISSUE_AGE = :max_issue_age AND MAX_SEG_DUR = :max_seg_dur AND ROLLOVER_IND > :rollover_ind) OR (EFFECTIVE_DATE = :effective_date AND UNDERWRITING_BASIS = :underwriting_basis AND TRANSACTION_CODE = :transaction_code AND SEX_CODE = :sex_code AND MAX_ISSUE_AGE = :max_issue_age AND MAX_SEG_DUR = :max_seg_dur AND ROLLOVER_IND = :rollover_ind AND MEMO_CODE > :memo_code)) ";
            else
                if (locator)
                    pagingWhere = ".TT19T WHERE (COMPANY_CODE = :company_code) AND ((TABLE_SUBSET > :table_subset) OR (TABLE_SUBSET = :table_subset AND EFFECTIVE_DATE > :effective_date) OR (TABLE_SUBSET = :table_subset AND EFFECTIVE_DATE = :effective_date AND UNDERWRITING_BASIS > :underwriting_basis) OR (TABLE_SUBSET = :table_subset AND EFFECTIVE_DATE = :effective_date AND UNDERWRITING_BASIS = :underwriting_basis AND TRANSACTION_CODE > :transaction_code) OR (TABLE_SUBSET = :table_subset AND EFFECTIVE_DATE = :effective_date AND UNDERWRITING_BASIS = :underwriting_basis AND TRANSACTION_CODE = :transaction_code AND SEX_CODE > :sex_code) OR (TABLE_SUBSET = :table_subset AND EFFECTIVE_DATE = :effective_date AND UNDERWRITING_BASIS = :underwriting_basis AND TRANSACTION_CODE = :transaction_code AND SEX_CODE = :sex_code AND MAX_ISSUE_AGE > :max_issue_age) OR (TABLE_SUBSET = :table_subset AND EFFECTIVE_DATE = :effective_date AND UNDERWRITING_BASIS = :underwriting_basis AND TRANSACTION_CODE = :transaction_code AND SEX_CODE = :sex_code AND MAX_ISSUE_AGE = :max_issue_age AND MAX_SEG_DUR > :max_seg_dur) OR (TABLE_SUBSET = :table_subset AND EFFECTIVE_DATE = :effective_date AND UNDERWRITING_BASIS = :underwriting_basis AND TRANSACTION_CODE = :transaction_code AND SEX_CODE = :sex_code AND MAX_ISSUE_AGE = :max_issue_age AND MAX_SEG_DUR = :max_seg_dur AND ROLLOVER_IND > :rollover_ind) OR (TABLE_SUBSET = :table_subset AND EFFECTIVE_DATE = :effective_date AND UNDERWRITING_BASIS = :underwriting_basis AND TRANSACTION_CODE = :transaction_code AND SEX_CODE = :sex_code AND MAX_ISSUE_AGE = :max_issue_age AND MAX_SEG_DUR = :max_seg_dur AND ROLLOVER_IND = :rollover_ind AND MEMO_CODE > :memo_code) OR (TABLE_SUBSET = :table_subset AND EFFECTIVE_DATE = :effective_date AND UNDERWRITING_BASIS = :underwriting_basis AND TRANSACTION_CODE = :transaction_code AND SEX_CODE = :sex_code AND MAX_ISSUE_AGE = :max_issue_age AND MAX_SEG_DUR = :max_seg_dur AND ROLLOVER_IND = :rollover_ind AND MEMO_CODE = :memo_code)) ";
                else
                    pagingWhere = ".TT19T WHERE (COMPANY_CODE = :company_code) AND ((TABLE_SUBSET > :table_subset) OR (TABLE_SUBSET = :table_subset AND EFFECTIVE_DATE > :effective_date) OR (TABLE_SUBSET = :table_subset AND EFFECTIVE_DATE = :effective_date AND UNDERWRITING_BASIS > :underwriting_basis) OR (TABLE_SUBSET = :table_subset AND EFFECTIVE_DATE = :effective_date AND UNDERWRITING_BASIS = :underwriting_basis AND TRANSACTION_CODE > :transaction_code) OR (TABLE_SUBSET = :table_subset AND EFFECTIVE_DATE = :effective_date AND UNDERWRITING_BASIS = :underwriting_basis AND TRANSACTION_CODE = :transaction_code AND SEX_CODE > :sex_code) OR (TABLE_SUBSET = :table_subset AND EFFECTIVE_DATE = :effective_date AND UNDERWRITING_BASIS = :underwriting_basis AND TRANSACTION_CODE = :transaction_code AND SEX_CODE = :sex_code AND MAX_ISSUE_AGE > :max_issue_age) OR (TABLE_SUBSET = :table_subset AND EFFECTIVE_DATE = :effective_date AND UNDERWRITING_BASIS = :underwriting_basis AND TRANSACTION_CODE = :transaction_code AND SEX_CODE = :sex_code AND MAX_ISSUE_AGE = :max_issue_age AND MAX_SEG_DUR > :max_seg_dur) OR (TABLE_SUBSET = :table_subset AND EFFECTIVE_DATE = :effective_date AND UNDERWRITING_BASIS = :underwriting_basis AND TRANSACTION_CODE = :transaction_code AND SEX_CODE = :sex_code AND MAX_ISSUE_AGE = :max_issue_age AND MAX_SEG_DUR = :max_seg_dur AND ROLLOVER_IND > :rollover_ind) OR (TABLE_SUBSET = :table_subset AND EFFECTIVE_DATE = :effective_date AND UNDERWRITING_BASIS = :underwriting_basis AND TRANSACTION_CODE = :transaction_code AND SEX_CODE = :sex_code AND MAX_ISSUE_AGE = :max_issue_age AND MAX_SEG_DUR = :max_seg_dur AND ROLLOVER_IND = :rollover_ind AND MEMO_CODE > :memo_code)) ";
            order = " ORDER BY COMPANY_CODE, TABLE_SUBSET, EFFECTIVE_DATE, UNDERWRITING_BASIS, TRANSACTION_CODE, SEX_CODE, MAX_ISSUE_AGE, MAX_SEG_DUR, ROLLOVER_IND, MEMO_CODE";
        }
        else {
            if (isSubsetMode)
                pagingWhere = ".TT19T WHERE (COMPANY_CODE = :company_code) AND (TABLE_SUBSET = :table_subset) AND ((EFFECTIVE_DATE < :effective_date) OR (EFFECTIVE_DATE = :effective_date AND UNDERWRITING_BASIS < :underwriting_basis) OR (EFFECTIVE_DATE = :effective_date AND UNDERWRITING_BASIS = :underwriting_basis AND TRANSACTION_CODE < :transaction_code) OR (EFFECTIVE_DATE = :effective_date AND UNDERWRITING_BASIS = :underwriting_basis AND TRANSACTION_CODE = :transaction_code AND SEX_CODE < :sex_code) OR (EFFECTIVE_DATE = :effective_date AND UNDERWRITING_BASIS = :underwriting_basis AND TRANSACTION_CODE = :transaction_code AND SEX_CODE = :sex_code AND MAX_ISSUE_AGE < :max_issue_age) OR (EFFECTIVE_DATE = :effective_date AND UNDERWRITING_BASIS = :underwriting_basis AND TRANSACTION_CODE = :transaction_code AND SEX_CODE = :sex_code AND MAX_ISSUE_AGE = :max_issue_age AND MAX_SEG_DUR < :max_seg_dur) OR (EFFECTIVE_DATE = :effective_date AND UNDERWRITING_BASIS = :underwriting_basis AND TRANSACTION_CODE = :transaction_code AND SEX_CODE = :sex_code AND MAX_ISSUE_AGE = :max_issue_age AND MAX_SEG_DUR = :max_seg_dur AND ROLLOVER_IND < :rollover_ind) OR (EFFECTIVE_DATE = :effective_date AND UNDERWRITING_BASIS = :underwriting_basis AND TRANSACTION_CODE = :transaction_code AND SEX_CODE = :sex_code AND MAX_ISSUE_AGE = :max_issue_age AND MAX_SEG_DUR = :max_seg_dur AND ROLLOVER_IND = :rollover_ind AND MEMO_CODE < :memo_code)) ";
            else
                pagingWhere = ".TT19T WHERE (COMPANY_CODE = :company_code) AND ((TABLE_SUBSET < :table_subset) OR (TABLE_SUBSET = :table_subset AND EFFECTIVE_DATE < :effective_date) OR (TABLE_SUBSET = :table_subset AND EFFECTIVE_DATE = :effective_date AND UNDERWRITING_BASIS < :underwriting_basis) OR (TABLE_SUBSET = :table_subset AND EFFECTIVE_DATE = :effective_date AND UNDERWRITING_BASIS = :underwriting_basis AND TRANSACTION_CODE < :transaction_code) OR (TABLE_SUBSET = :table_subset AND EFFECTIVE_DATE = :effective_date AND UNDERWRITING_BASIS = :underwriting_basis AND TRANSACTION_CODE = :transaction_code AND SEX_CODE < :sex_code) OR (TABLE_SUBSET = :table_subset AND EFFECTIVE_DATE = :effective_date AND UNDERWRITING_BASIS = :underwriting_basis AND TRANSACTION_CODE = :transaction_code AND SEX_CODE = :sex_code AND MAX_ISSUE_AGE < :max_issue_age) OR (TABLE_SUBSET = :table_subset AND EFFECTIVE_DATE = :effective_date AND UNDERWRITING_BASIS = :underwriting_basis AND TRANSACTION_CODE = :transaction_code AND SEX_CODE = :sex_code AND MAX_ISSUE_AGE = :max_issue_age AND MAX_SEG_DUR < :max_seg_dur) OR (TABLE_SUBSET = :table_subset AND EFFECTIVE_DATE = :effective_date AND UNDERWRITING_BASIS = :underwriting_basis AND TRANSACTION_CODE = :transaction_code AND SEX_CODE = :sex_code AND MAX_ISSUE_AGE = :max_issue_age AND MAX_SEG_DUR = :max_seg_dur AND ROLLOVER_IND < :rollover_ind) OR (TABLE_SUBSET = :table_subset AND EFFECTIVE_DATE = :effective_date AND UNDERWRITING_BASIS = :underwriting_basis AND TRANSACTION_CODE = :transaction_code AND SEX_CODE = :sex_code AND MAX_ISSUE_AGE = :max_issue_age AND MAX_SEG_DUR = :max_seg_dur AND ROLLOVER_IND = :rollover_ind AND MEMO_CODE < :memo_code)) ";
            order = " ORDER BY COMPANY_CODE DESC, TABLE_SUBSET DESC, EFFECTIVE_DATE DESC, UNDERWRITING_BASIS DESC, TRANSACTION_CODE DESC, SEX_CODE DESC, MAX_ISSUE_AGE DESC, MAX_SEG_DUR DESC, ROLLOVER_IND DESC, MEMO_CODE DESC";
        }
        return pagingSql + schemaName + pagingWhere + order;
    }
    
    public String prepareInsertStmt(String schemaName) {
        StringBuffer sb = new StringBuffer();
        sb.append("INSERT INTO ").append(schemaName);
        sb.append(".TT19T ( ");
        sb.append("COMPANY_CODE, TABLE_SUBSET, EFFECTIVE_DATE, UNDERWRITING_BASIS, TRANSACTION_CODE, SEX_CODE, MAX_ISSUE_AGE, MAX_SEG_DUR, ROLLOVER_IND, MEMO_CODE, PMT_LD_PERCENT, SUBSTD_PMT_LD_PCT, ORDER_OF_PROC_CODE )");
        sb.append(" VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ? )");
        return sb.toString();
    }
    
    public String prepareUpdateStmt(String schemaName)
    {
        StringBuffer sb = new StringBuffer();
        sb.append("UPDATE ").append(schemaName);
        sb.append(".TT19T ");
        sb.append(" SET COMPANY_CODE = ?, TABLE_SUBSET = ?, EFFECTIVE_DATE = ?, UNDERWRITING_BASIS = ?, TRANSACTION_CODE = ?, SEX_CODE = ?, MAX_ISSUE_AGE = ?, MAX_SEG_DUR = ?, ROLLOVER_IND = ?, MEMO_CODE = ?, PMT_LD_PERCENT = ?, SUBSTD_PMT_LD_PCT = ?, ORDER_OF_PROC_CODE = ?");
        sb.append(" WHERE COMPANY_CODE = ? AND TABLE_SUBSET = ? AND EFFECTIVE_DATE = ? AND UNDERWRITING_BASIS = ? AND TRANSACTION_CODE = ? AND SEX_CODE = ? AND MAX_ISSUE_AGE = ? AND MAX_SEG_DUR = ? AND ROLLOVER_IND = ? AND MEMO_CODE = ?");
        return sb.toString();
    }
    
    public String prepareDeleteStmt(String schemaName)
    {
        StringBuffer sb = new StringBuffer();
        sb.append("DELETE FROM ").append(schemaName);
        sb.append(".TT19T ");
        sb.append(" WHERE COMPANY_CODE = ? AND TABLE_SUBSET = ? AND EFFECTIVE_DATE = ? AND UNDERWRITING_BASIS = ? AND TRANSACTION_CODE = ? AND SEX_CODE = ? AND MAX_ISSUE_AGE = ? AND MAX_SEG_DUR = ? AND ROLLOVER_IND = ? AND MEMO_CODE = ?");
        return sb.toString();
    }
}
