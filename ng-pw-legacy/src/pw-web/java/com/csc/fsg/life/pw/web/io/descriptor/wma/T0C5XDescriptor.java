package com.csc.fsg.life.pw.web.io.descriptor.wma;

import com.csc.fsg.life.pw.web.io.*;

public class T0C5XDescriptor
    extends TableDescriptor
{
    private static final String pagingSql = "SELECT COMPANY_CODE, PRODUCT_PREFIX, TABLE_SUBSET, EFFECTIVE_DATE, ISSUE_STATE, MEMO_CODE, COST_AVG_TYPE, ELECT_PERIOD_DUR, MIN_ACCT_VALUE, TRANSF_FREQ, MIN_TRANSF_AMT, MAX_TRANSF_AMT, MIN_TRANSF_UNITS, MAX_TRANSF_UNITS, MIN_TRANSF_PCT, MAX_TRANSF_PCT, FEE_AMOUNT, FEE_PERCENT, ADVANCE_DAYS FROM ";
    
    public void initialize()
    {
        setRowClass(T0C5XRow.class);
        setTableName("T0C5X");
        setTableId("0C5");
        super.initialize();
    }
    
    public void initializeColumnDescriptors()
    {
        super.initializeColumnDescriptors();
        addColumnDescriptor(new ColumnDescriptor(this,"getCompanyCode","setCompanyCode","COMPANY_CODE,1,1,3,0,true|A,0,CHAR,1,null,null,null,null,null|U,0,CHAR,1,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getProductPrefix","setProductPrefix","PRODUCT_PREFIX,1,2,1,0,true|A,0,CHAR,1,null,null,null,null,null|U,0,CHAR,1,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getTableSubset","setTableSubset","TABLE_SUBSET,1,3,16,0,true|A,0,CHAR,1,null,null,null,null,null|U,0,CHAR,1,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getEffectiveDate","setEffectiveDate","EFFECTIVE_DATE,91,4,10,0,true|A,0,DATE,0,null,null,null,null,null|U,0,DATE,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getIssueState","setIssueState","ISSUE_STATE,1,5,3,0,true|A,0,CHAR,0,null,null,null,null,null|U,0,CHAR,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getMemoCode","setMemoCode","MEMO_CODE,1,6,2,0,true|A,0,CHAR,0,null,null,null,null,null|U,0,CHAR,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getCostAvgType","setCostAvgType","COST_AVG_TYPE,1,7,2,0,true|A,0,CHAR,0,null,null,null,null,null|U,0,CHAR,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getElectPeriodDur","setElectPeriodDur","ELECT_PERIOD_DUR,3,8,3,0,true|A,0,INTEGER,0,null,null,null,null,null|U,0,INTEGER,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getMinAcctValue","setMinAcctValue","MIN_ACCT_VALUE,3,9,11,2,false|A,0,DOUBLE,0,null,null,null,null,null|U,0,DOUBLE,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getTransfFreq","setTransfFreq","TRANSF_FREQ,3,10,2,0,false|A,0,INTEGER,2,null,null,null,null,null|U,0,INTEGER,2,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getMinTransfAmt","setMinTransfAmt","MIN_TRANSF_AMT,3,11,11,2,false|A,0,DOUBLE,0,null,null,null,null,null|U,0,DOUBLE,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getMaxTransfAmt","setMaxTransfAmt","MAX_TRANSF_AMT,3,12,11,2,false|A,0,DOUBLE,0,null,null,null,null,null|U,0,DOUBLE,2,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getMinTransfUnits","setMinTransfUnits","MIN_TRANSF_UNITS,3,13,11,4,false|A,0,DOUBLE,0,null,null,null,null,null|U,0,DOUBLE,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getMaxTransfUnits","setMaxTransfUnits","MAX_TRANSF_UNITS,3,14,11,4,false|A,0,DOUBLE,0,null,null,null,null,null|U,0,DOUBLE,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getMinTransfPct","setMinTransfPct","MIN_TRANSF_PCT,3,15,6,3,false|A,0,DOUBLE,0,null,null,null,null,null|U,0,DOUBLE,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getMaxTransfPct","setMaxTransfPct","MAX_TRANSF_PCT,3,16,6,3,false|A,0,DOUBLE,0,null,null,null,null,null|U,0,DOUBLE,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getFeeAmount","setFeeAmount","FEE_AMOUNT,3,17,9,2,false|A,0,DOUBLE,0,null,null,null,null,null|U,0,DOUBLE,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getFeePercent","setFeePercent","FEE_PERCENT,3,18,6,3,false|A,0,DOUBLE,2,null,null,null,null,null|U,0,DOUBLE,2,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getAdvanceDays","setAdvanceDays","ADVANCE_DAYS,3,19,3,0,false|A,0,INTEGER,2,null,null,null,null,null|U,0,INTEGER,2,null,null,null,null,null"));
    }
    
    public String getPagingSQL(String schemaName,boolean isSubsetMode,boolean isNext, boolean locator)
    {
        String pagingWhere = null;
        String order = null;
        if (isNext) {
            if (isSubsetMode)
                if (locator)
                    pagingWhere = ".T0C5X WHERE (COMPANY_CODE = :company_code) AND (PRODUCT_PREFIX = :product_prefix) AND (TABLE_SUBSET = :table_subset) AND ((EFFECTIVE_DATE > :effective_date) OR (EFFECTIVE_DATE = :effective_date AND ISSUE_STATE > :issue_state) OR (EFFECTIVE_DATE = :effective_date AND ISSUE_STATE = :issue_state AND MEMO_CODE > :memo_code) OR (EFFECTIVE_DATE = :effective_date AND ISSUE_STATE = :issue_state AND MEMO_CODE = :memo_code AND COST_AVG_TYPE > :cost_avg_type) OR (EFFECTIVE_DATE = :effective_date AND ISSUE_STATE = :issue_state AND MEMO_CODE = :memo_code AND COST_AVG_TYPE = :cost_avg_type AND ELECT_PERIOD_DUR > :elect_period_dur) OR (EFFECTIVE_DATE = :effective_date AND ISSUE_STATE = :issue_state AND MEMO_CODE = :memo_code AND COST_AVG_TYPE = :cost_avg_type AND ELECT_PERIOD_DUR = :elect_period_dur)) ";
                else 
                    pagingWhere = ".T0C5X WHERE (COMPANY_CODE = :company_code) AND (PRODUCT_PREFIX = :product_prefix) AND (TABLE_SUBSET = :table_subset) AND ((EFFECTIVE_DATE > :effective_date) OR (EFFECTIVE_DATE = :effective_date AND ISSUE_STATE > :issue_state) OR (EFFECTIVE_DATE = :effective_date AND ISSUE_STATE = :issue_state AND MEMO_CODE > :memo_code) OR (EFFECTIVE_DATE = :effective_date AND ISSUE_STATE = :issue_state AND MEMO_CODE = :memo_code AND COST_AVG_TYPE > :cost_avg_type) OR (EFFECTIVE_DATE = :effective_date AND ISSUE_STATE = :issue_state AND MEMO_CODE = :memo_code AND COST_AVG_TYPE = :cost_avg_type AND ELECT_PERIOD_DUR > :elect_period_dur)) ";
            else
                if (locator)
                    pagingWhere = ".T0C5X WHERE (COMPANY_CODE = :company_code) AND ((PRODUCT_PREFIX > :product_prefix) OR (PRODUCT_PREFIX = :product_prefix AND TABLE_SUBSET > :table_subset) OR (PRODUCT_PREFIX = :product_prefix AND TABLE_SUBSET = :table_subset AND EFFECTIVE_DATE > :effective_date) OR (PRODUCT_PREFIX = :product_prefix AND TABLE_SUBSET = :table_subset AND EFFECTIVE_DATE = :effective_date AND ISSUE_STATE > :issue_state) OR (PRODUCT_PREFIX = :product_prefix AND TABLE_SUBSET = :table_subset AND EFFECTIVE_DATE = :effective_date AND ISSUE_STATE = :issue_state AND MEMO_CODE > :memo_code) OR (PRODUCT_PREFIX = :product_prefix AND TABLE_SUBSET = :table_subset AND EFFECTIVE_DATE = :effective_date AND ISSUE_STATE = :issue_state AND MEMO_CODE = :memo_code AND COST_AVG_TYPE > :cost_avg_type) OR (PRODUCT_PREFIX = :product_prefix AND TABLE_SUBSET = :table_subset AND EFFECTIVE_DATE = :effective_date AND ISSUE_STATE = :issue_state AND MEMO_CODE = :memo_code AND COST_AVG_TYPE = :cost_avg_type AND ELECT_PERIOD_DUR > :elect_period_dur) OR (PRODUCT_PREFIX = :product_prefix AND TABLE_SUBSET = :table_subset AND EFFECTIVE_DATE = :effective_date AND ISSUE_STATE = :issue_state AND MEMO_CODE = :memo_code AND COST_AVG_TYPE = :cost_avg_type AND ELECT_PERIOD_DUR = :elect_period_dur)) ";
                else
                    pagingWhere = ".T0C5X WHERE (COMPANY_CODE = :company_code) AND ((PRODUCT_PREFIX > :product_prefix) OR (PRODUCT_PREFIX = :product_prefix AND TABLE_SUBSET > :table_subset) OR (PRODUCT_PREFIX = :product_prefix AND TABLE_SUBSET = :table_subset AND EFFECTIVE_DATE > :effective_date) OR (PRODUCT_PREFIX = :product_prefix AND TABLE_SUBSET = :table_subset AND EFFECTIVE_DATE = :effective_date AND ISSUE_STATE > :issue_state) OR (PRODUCT_PREFIX = :product_prefix AND TABLE_SUBSET = :table_subset AND EFFECTIVE_DATE = :effective_date AND ISSUE_STATE = :issue_state AND MEMO_CODE > :memo_code) OR (PRODUCT_PREFIX = :product_prefix AND TABLE_SUBSET = :table_subset AND EFFECTIVE_DATE = :effective_date AND ISSUE_STATE = :issue_state AND MEMO_CODE = :memo_code AND COST_AVG_TYPE > :cost_avg_type) OR (PRODUCT_PREFIX = :product_prefix AND TABLE_SUBSET = :table_subset AND EFFECTIVE_DATE = :effective_date AND ISSUE_STATE = :issue_state AND MEMO_CODE = :memo_code AND COST_AVG_TYPE = :cost_avg_type AND ELECT_PERIOD_DUR > :elect_period_dur)) ";
            order = " ORDER BY COMPANY_CODE, PRODUCT_PREFIX, TABLE_SUBSET, EFFECTIVE_DATE, ISSUE_STATE, MEMO_CODE, COST_AVG_TYPE, ELECT_PERIOD_DUR";
        }
        else {
            if (isSubsetMode)
                pagingWhere = ".T0C5X WHERE (COMPANY_CODE = :company_code) AND (PRODUCT_PREFIX = :product_prefix) AND (TABLE_SUBSET = :table_subset) AND ((EFFECTIVE_DATE < :effective_date) OR (EFFECTIVE_DATE = :effective_date AND ISSUE_STATE < :issue_state) OR (EFFECTIVE_DATE = :effective_date AND ISSUE_STATE = :issue_state AND MEMO_CODE < :memo_code) OR (EFFECTIVE_DATE = :effective_date AND ISSUE_STATE = :issue_state AND MEMO_CODE = :memo_code AND COST_AVG_TYPE < :cost_avg_type) OR (EFFECTIVE_DATE = :effective_date AND ISSUE_STATE = :issue_state AND MEMO_CODE = :memo_code AND COST_AVG_TYPE = :cost_avg_type AND ELECT_PERIOD_DUR < :elect_period_dur)) ";
            else
                pagingWhere = ".T0C5X WHERE (COMPANY_CODE = :company_code) AND ((PRODUCT_PREFIX < :product_prefix) OR (PRODUCT_PREFIX = :product_prefix AND TABLE_SUBSET < :table_subset) OR (PRODUCT_PREFIX = :product_prefix AND TABLE_SUBSET = :table_subset AND EFFECTIVE_DATE < :effective_date) OR (PRODUCT_PREFIX = :product_prefix AND TABLE_SUBSET = :table_subset AND EFFECTIVE_DATE = :effective_date AND ISSUE_STATE < :issue_state) OR (PRODUCT_PREFIX = :product_prefix AND TABLE_SUBSET = :table_subset AND EFFECTIVE_DATE = :effective_date AND ISSUE_STATE = :issue_state AND MEMO_CODE < :memo_code) OR (PRODUCT_PREFIX = :product_prefix AND TABLE_SUBSET = :table_subset AND EFFECTIVE_DATE = :effective_date AND ISSUE_STATE = :issue_state AND MEMO_CODE = :memo_code AND COST_AVG_TYPE < :cost_avg_type) OR (PRODUCT_PREFIX = :product_prefix AND TABLE_SUBSET = :table_subset AND EFFECTIVE_DATE = :effective_date AND ISSUE_STATE = :issue_state AND MEMO_CODE = :memo_code AND COST_AVG_TYPE = :cost_avg_type AND ELECT_PERIOD_DUR < :elect_period_dur)) ";
            order = " ORDER BY COMPANY_CODE DESC, PRODUCT_PREFIX DESC, TABLE_SUBSET DESC, EFFECTIVE_DATE DESC, ISSUE_STATE DESC, MEMO_CODE DESC, COST_AVG_TYPE DESC, ELECT_PERIOD_DUR DESC";
        }
        return pagingSql + schemaName + pagingWhere + order;
    }
    
    public String prepareInsertStmt(String schemaName) {
        StringBuffer sb = new StringBuffer();
        sb.append("INSERT INTO ").append(schemaName);
        sb.append(".T0C5X ( ");
        sb.append("COMPANY_CODE, PRODUCT_PREFIX, TABLE_SUBSET, EFFECTIVE_DATE, ISSUE_STATE, MEMO_CODE, COST_AVG_TYPE, ELECT_PERIOD_DUR, MIN_ACCT_VALUE, TRANSF_FREQ, MIN_TRANSF_AMT, MAX_TRANSF_AMT, MIN_TRANSF_UNITS, MAX_TRANSF_UNITS, MIN_TRANSF_PCT, MAX_TRANSF_PCT, FEE_AMOUNT, FEE_PERCENT, ADVANCE_DAYS )");
        sb.append(" VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ? )");
        return sb.toString();
    }
    
    public String prepareUpdateStmt(String schemaName)
    {
        StringBuffer sb = new StringBuffer();
        sb.append("UPDATE ").append(schemaName);
        sb.append(".T0C5X ");
        sb.append(" SET COMPANY_CODE = ?, PRODUCT_PREFIX = ?, TABLE_SUBSET = ?, EFFECTIVE_DATE = ?, ISSUE_STATE = ?, MEMO_CODE = ?, COST_AVG_TYPE = ?, ELECT_PERIOD_DUR = ?, MIN_ACCT_VALUE = ?, TRANSF_FREQ = ?, MIN_TRANSF_AMT = ?, MAX_TRANSF_AMT = ?, MIN_TRANSF_UNITS = ?, MAX_TRANSF_UNITS = ?, MIN_TRANSF_PCT = ?, MAX_TRANSF_PCT = ?, FEE_AMOUNT = ?, FEE_PERCENT = ?, ADVANCE_DAYS = ?");
        sb.append(" WHERE COMPANY_CODE = ? AND PRODUCT_PREFIX = ? AND TABLE_SUBSET = ? AND EFFECTIVE_DATE = ? AND ISSUE_STATE = ? AND MEMO_CODE = ? AND COST_AVG_TYPE = ? AND ELECT_PERIOD_DUR = ?");
        return sb.toString();
    }
    
    public String prepareDeleteStmt(String schemaName)
    {
        StringBuffer sb = new StringBuffer();
        sb.append("DELETE FROM ").append(schemaName);
        sb.append(".T0C5X ");
        sb.append(" WHERE COMPANY_CODE = ? AND PRODUCT_PREFIX = ? AND TABLE_SUBSET = ? AND EFFECTIVE_DATE = ? AND ISSUE_STATE = ? AND MEMO_CODE = ? AND COST_AVG_TYPE = ? AND ELECT_PERIOD_DUR = ?");
        return sb.toString();
    }
}
