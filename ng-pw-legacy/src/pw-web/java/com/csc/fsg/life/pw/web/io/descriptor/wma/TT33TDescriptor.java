package com.csc.fsg.life.pw.web.io.descriptor.wma;

import com.csc.fsg.life.pw.web.io.*;

public class TT33TDescriptor
    extends TableDescriptor
{
    private static final String pagingSql = "SELECT COMPANY_CODE, TABLE_SUBSET, ISSUE_STATE, TRX_CODE, EFFECTIVE_DATE, MAX_DURATION, CVG_STATUS, MEMO_CODE, NBR_ALL_TRX_PERYR, MAX_NBR_TRX, MIN_SURR_WITHD_AMT, MIN_SURR_WITHD_PCT, WRAWAL_INGRACE_IND FROM ";
    
    public void initialize()
    {
        setRowClass(TT33TRow.class);
        setTableName("TT33T");
        setTableId("T33");
        super.initialize();
    }
    
    public void initializeColumnDescriptors()
    {
        super.initializeColumnDescriptors();
        addColumnDescriptor(new ColumnDescriptor(this,"getCompanyCode","setCompanyCode","COMPANY_CODE,1,1,3,0,true|,0,CHAR,1,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getTableSubset","setTableSubset","TABLE_SUBSET,1,2,16,0,true|,0,CHAR,1,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getIssueState","setIssueState","ISSUE_STATE,1,3,3,0,true|,0,CHAR,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getTrxCode","setTrxCode","TRX_CODE,1,4,4,0,true|,0,CHAR,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getEffectiveDate","setEffectiveDate","EFFECTIVE_DATE,91,5,10,0,true|,0,DATE,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getMaxDuration","setMaxDuration","MAX_DURATION,3,6,3,0,true|,0,INTEGER,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getCvgStatus","setCvgStatus","CVG_STATUS,1,7,1,0,true|,0,CHAR,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getMemoCode","setMemoCode","MEMO_CODE,1,8,2,0,true|,0,CHAR,4,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getNbrAllTrxPeryr","setNbrAllTrxPeryr","NBR_ALL_TRX_PERYR,3,9,2,0,false|,0,INTEGER,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getMaxNbrTrx","setMaxNbrTrx","MAX_NBR_TRX,3,10,3,0,false|,0,INTEGER,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getMinSurrWithdAmt","setMinSurrWithdAmt","MIN_SURR_WITHD_AMT,3,11,11,2,false|,0,DOUBLE,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getMinSurrWithdPct","setMinSurrWithdPct","MIN_SURR_WITHD_PCT,3,12,5,2,false|,0,DOUBLE,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getWrawalIngraceInd","setWrawalIngraceInd","WRAWAL_INGRACE_IND,1,13,1,0,false|,0,CHAR,0,null,null,null,null,null"));
    }
    
    public String getPagingSQL(String schemaName,boolean isSubsetMode,boolean isNext, boolean locator)
    {
        String pagingWhere = null;
        String order = null;
        if (isNext) {
            if (isSubsetMode)
                if (locator)
                    pagingWhere = ".TT33T WHERE (COMPANY_CODE = :company_code) AND (TABLE_SUBSET = :table_subset) AND ((ISSUE_STATE > :issue_state) OR (ISSUE_STATE = :issue_state AND TRX_CODE > :trx_code) OR (ISSUE_STATE = :issue_state AND TRX_CODE = :trx_code AND EFFECTIVE_DATE > :effective_date) OR (ISSUE_STATE = :issue_state AND TRX_CODE = :trx_code AND EFFECTIVE_DATE = :effective_date AND MAX_DURATION > :max_duration) OR (ISSUE_STATE = :issue_state AND TRX_CODE = :trx_code AND EFFECTIVE_DATE = :effective_date AND MAX_DURATION = :max_duration AND CVG_STATUS > :cvg_status) OR (ISSUE_STATE = :issue_state AND TRX_CODE = :trx_code AND EFFECTIVE_DATE = :effective_date AND MAX_DURATION = :max_duration AND CVG_STATUS = :cvg_status AND MEMO_CODE > :memo_code) OR (ISSUE_STATE = :issue_state AND TRX_CODE = :trx_code AND EFFECTIVE_DATE = :effective_date AND MAX_DURATION = :max_duration AND CVG_STATUS = :cvg_status AND MEMO_CODE = :memo_code)) ";
                else 
                    pagingWhere = ".TT33T WHERE (COMPANY_CODE = :company_code) AND (TABLE_SUBSET = :table_subset) AND ((ISSUE_STATE > :issue_state) OR (ISSUE_STATE = :issue_state AND TRX_CODE > :trx_code) OR (ISSUE_STATE = :issue_state AND TRX_CODE = :trx_code AND EFFECTIVE_DATE > :effective_date) OR (ISSUE_STATE = :issue_state AND TRX_CODE = :trx_code AND EFFECTIVE_DATE = :effective_date AND MAX_DURATION > :max_duration) OR (ISSUE_STATE = :issue_state AND TRX_CODE = :trx_code AND EFFECTIVE_DATE = :effective_date AND MAX_DURATION = :max_duration AND CVG_STATUS > :cvg_status) OR (ISSUE_STATE = :issue_state AND TRX_CODE = :trx_code AND EFFECTIVE_DATE = :effective_date AND MAX_DURATION = :max_duration AND CVG_STATUS = :cvg_status AND MEMO_CODE > :memo_code)) ";
            else
                if (locator)
                    pagingWhere = ".TT33T WHERE (COMPANY_CODE = :company_code) AND ((TABLE_SUBSET > :table_subset) OR (TABLE_SUBSET = :table_subset AND ISSUE_STATE > :issue_state) OR (TABLE_SUBSET = :table_subset AND ISSUE_STATE = :issue_state AND TRX_CODE > :trx_code) OR (TABLE_SUBSET = :table_subset AND ISSUE_STATE = :issue_state AND TRX_CODE = :trx_code AND EFFECTIVE_DATE > :effective_date) OR (TABLE_SUBSET = :table_subset AND ISSUE_STATE = :issue_state AND TRX_CODE = :trx_code AND EFFECTIVE_DATE = :effective_date AND MAX_DURATION > :max_duration) OR (TABLE_SUBSET = :table_subset AND ISSUE_STATE = :issue_state AND TRX_CODE = :trx_code AND EFFECTIVE_DATE = :effective_date AND MAX_DURATION = :max_duration AND CVG_STATUS > :cvg_status) OR (TABLE_SUBSET = :table_subset AND ISSUE_STATE = :issue_state AND TRX_CODE = :trx_code AND EFFECTIVE_DATE = :effective_date AND MAX_DURATION = :max_duration AND CVG_STATUS = :cvg_status AND MEMO_CODE > :memo_code) OR (TABLE_SUBSET = :table_subset AND ISSUE_STATE = :issue_state AND TRX_CODE = :trx_code AND EFFECTIVE_DATE = :effective_date AND MAX_DURATION = :max_duration AND CVG_STATUS = :cvg_status AND MEMO_CODE = :memo_code)) ";
                else
                    pagingWhere = ".TT33T WHERE (COMPANY_CODE = :company_code) AND ((TABLE_SUBSET > :table_subset) OR (TABLE_SUBSET = :table_subset AND ISSUE_STATE > :issue_state) OR (TABLE_SUBSET = :table_subset AND ISSUE_STATE = :issue_state AND TRX_CODE > :trx_code) OR (TABLE_SUBSET = :table_subset AND ISSUE_STATE = :issue_state AND TRX_CODE = :trx_code AND EFFECTIVE_DATE > :effective_date) OR (TABLE_SUBSET = :table_subset AND ISSUE_STATE = :issue_state AND TRX_CODE = :trx_code AND EFFECTIVE_DATE = :effective_date AND MAX_DURATION > :max_duration) OR (TABLE_SUBSET = :table_subset AND ISSUE_STATE = :issue_state AND TRX_CODE = :trx_code AND EFFECTIVE_DATE = :effective_date AND MAX_DURATION = :max_duration AND CVG_STATUS > :cvg_status) OR (TABLE_SUBSET = :table_subset AND ISSUE_STATE = :issue_state AND TRX_CODE = :trx_code AND EFFECTIVE_DATE = :effective_date AND MAX_DURATION = :max_duration AND CVG_STATUS = :cvg_status AND MEMO_CODE > :memo_code)) ";
            order = " ORDER BY COMPANY_CODE, TABLE_SUBSET, ISSUE_STATE, TRX_CODE, EFFECTIVE_DATE, MAX_DURATION, CVG_STATUS, MEMO_CODE";
        }
        else {
            if (isSubsetMode)
                pagingWhere = ".TT33T WHERE (COMPANY_CODE = :company_code) AND (TABLE_SUBSET = :table_subset) AND ((ISSUE_STATE < :issue_state) OR (ISSUE_STATE = :issue_state AND TRX_CODE < :trx_code) OR (ISSUE_STATE = :issue_state AND TRX_CODE = :trx_code AND EFFECTIVE_DATE < :effective_date) OR (ISSUE_STATE = :issue_state AND TRX_CODE = :trx_code AND EFFECTIVE_DATE = :effective_date AND MAX_DURATION < :max_duration) OR (ISSUE_STATE = :issue_state AND TRX_CODE = :trx_code AND EFFECTIVE_DATE = :effective_date AND MAX_DURATION = :max_duration AND CVG_STATUS < :cvg_status) OR (ISSUE_STATE = :issue_state AND TRX_CODE = :trx_code AND EFFECTIVE_DATE = :effective_date AND MAX_DURATION = :max_duration AND CVG_STATUS = :cvg_status AND MEMO_CODE < :memo_code)) ";
            else
                pagingWhere = ".TT33T WHERE (COMPANY_CODE = :company_code) AND ((TABLE_SUBSET < :table_subset) OR (TABLE_SUBSET = :table_subset AND ISSUE_STATE < :issue_state) OR (TABLE_SUBSET = :table_subset AND ISSUE_STATE = :issue_state AND TRX_CODE < :trx_code) OR (TABLE_SUBSET = :table_subset AND ISSUE_STATE = :issue_state AND TRX_CODE = :trx_code AND EFFECTIVE_DATE < :effective_date) OR (TABLE_SUBSET = :table_subset AND ISSUE_STATE = :issue_state AND TRX_CODE = :trx_code AND EFFECTIVE_DATE = :effective_date AND MAX_DURATION < :max_duration) OR (TABLE_SUBSET = :table_subset AND ISSUE_STATE = :issue_state AND TRX_CODE = :trx_code AND EFFECTIVE_DATE = :effective_date AND MAX_DURATION = :max_duration AND CVG_STATUS < :cvg_status) OR (TABLE_SUBSET = :table_subset AND ISSUE_STATE = :issue_state AND TRX_CODE = :trx_code AND EFFECTIVE_DATE = :effective_date AND MAX_DURATION = :max_duration AND CVG_STATUS = :cvg_status AND MEMO_CODE < :memo_code)) ";
            order = " ORDER BY COMPANY_CODE DESC, TABLE_SUBSET DESC, ISSUE_STATE DESC, TRX_CODE DESC, EFFECTIVE_DATE DESC, MAX_DURATION DESC, CVG_STATUS DESC, MEMO_CODE DESC";
        }
        return pagingSql + schemaName + pagingWhere + order;
    }
    
    public String prepareInsertStmt(String schemaName) {
        StringBuffer sb = new StringBuffer();
        sb.append("INSERT INTO ").append(schemaName);
        sb.append(".TT33T ( ");
        sb.append("COMPANY_CODE, TABLE_SUBSET, ISSUE_STATE, TRX_CODE, EFFECTIVE_DATE, MAX_DURATION, CVG_STATUS, MEMO_CODE, NBR_ALL_TRX_PERYR, MAX_NBR_TRX, MIN_SURR_WITHD_AMT, MIN_SURR_WITHD_PCT, WRAWAL_INGRACE_IND )");
        sb.append(" VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ? )");
        return sb.toString();
    }
    
    public String prepareUpdateStmt(String schemaName)
    {
        StringBuffer sb = new StringBuffer();
        sb.append("UPDATE ").append(schemaName);
        sb.append(".TT33T ");
        sb.append(" SET COMPANY_CODE = ?, TABLE_SUBSET = ?, ISSUE_STATE = ?, TRX_CODE = ?, EFFECTIVE_DATE = ?, MAX_DURATION = ?, CVG_STATUS = ?, MEMO_CODE = ?, NBR_ALL_TRX_PERYR = ?, MAX_NBR_TRX = ?, MIN_SURR_WITHD_AMT = ?, MIN_SURR_WITHD_PCT = ?, WRAWAL_INGRACE_IND = ?");
        sb.append(" WHERE COMPANY_CODE = ? AND TABLE_SUBSET = ? AND ISSUE_STATE = ? AND TRX_CODE = ? AND EFFECTIVE_DATE = ? AND MAX_DURATION = ? AND CVG_STATUS = ? AND MEMO_CODE = ?");
        return sb.toString();
    }
    
    public String prepareDeleteStmt(String schemaName)
    {
        StringBuffer sb = new StringBuffer();
        sb.append("DELETE FROM ").append(schemaName);
        sb.append(".TT33T ");
        sb.append(" WHERE COMPANY_CODE = ? AND TABLE_SUBSET = ? AND ISSUE_STATE = ? AND TRX_CODE = ? AND EFFECTIVE_DATE = ? AND MAX_DURATION = ? AND CVG_STATUS = ? AND MEMO_CODE = ?");
        return sb.toString();
    }
}
