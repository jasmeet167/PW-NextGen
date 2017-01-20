package com.csc.fsg.life.pw.io.descriptor.wma;

import com.csc.fsg.life.pw.io.*;

public class T035EDescriptor
    extends TableDescriptor
{
    private static final String pagingSql = "SELECT COMPANY_CODE, TABLE_SUBSET, EFFECTIVE_DATE, STAT_CODE, STATE_CODE, MAX_DUR, MIN_BASE_ACCEL_AMT, MAX_BASE_ACCEL_AMT, MIN_BASE_ACCEL_PCT, MAX_BASE_ACCEL_PCT, ABR_METHOD, AI_IND, REDUCE_ADB_IND, TERM_RB_IND, LIEN_TBL, LOAN_RDCT_LIEN_IND FROM ";
    
    public void initialize()
    {
        setRowClass(T035ERow.class);
        setTableName("T035E");
        setTableId("035");
        super.initialize();
    }
    
    public void initializeColumnDescriptors()
    {
        super.initializeColumnDescriptors();
        addColumnDescriptor(new ColumnDescriptor(this,"getCompanyCode","setCompanyCode","COMPANY_CODE,1,1,3,0,true|,0,CHAR,1,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getTableSubset","setTableSubset","TABLE_SUBSET,1,2,16,0,true|,0,CHAR,1,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getEffectiveDate","setEffectiveDate","EFFECTIVE_DATE,91,3,10,0,true|,0,DATE,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getStatCode","setStatCode","STAT_CODE,1,4,3,0,true|,0,CHAR,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getStateCode","setStateCode","STATE_CODE,1,5,3,0,true|,0,CHAR,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getMaxDur","setMaxDur","MAX_DUR,3,6,3,0,true|,0,INTEGER,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getMinBaseAccelAmt","setMinBaseAccelAmt","MIN_BASE_ACCEL_AMT,3,7,11,2,false|,0,DOUBLE,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getMaxBaseAccelAmt","setMaxBaseAccelAmt","MAX_BASE_ACCEL_AMT,3,8,11,2,false|,0,DOUBLE,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getMinBaseAccelPct","setMinBaseAccelPct","MIN_BASE_ACCEL_PCT,3,9,5,2,false|,0,DOUBLE,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getMaxBaseAccelPct","setMaxBaseAccelPct","MAX_BASE_ACCEL_PCT,3,10,5,2,false|,0,DOUBLE,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getAbrMethod","setAbrMethod","ABR_METHOD,1,11,1,0,false|,0,CHAR,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getAiInd","setAiInd","AI_IND,1,12,1,0,false|,0,CHAR,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getReduceAdbInd","setReduceAdbInd","REDUCE_ADB_IND,1,13,1,0,false|,0,CHAR,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getTermRbInd","setTermRbInd","TERM_RB_IND,1,14,1,0,false|,0,CHAR,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getLienTbl","setLienTbl","LIEN_TBL,1,15,16,0,false|,0,CHAR,0,025,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getLoanRdctLienInd","setLoanRdctLienInd","LOAN_RDCT_LIEN_IND,1,16,1,0,false|,0,CHAR,0,null,null,null,null,null"));
    }
    
    public String getPagingSQL(String schemaName,boolean isSubsetMode,boolean isNext, boolean locator)
    {
        String pagingWhere = null;
        String order = null;
        if (isNext) {
            if (isSubsetMode)
                if (locator)
                    pagingWhere = ".T035E WHERE (COMPANY_CODE = :company_code) AND (TABLE_SUBSET = :table_subset) AND ((EFFECTIVE_DATE > :effective_date) OR (EFFECTIVE_DATE = :effective_date AND STAT_CODE > :stat_code) OR (EFFECTIVE_DATE = :effective_date AND STAT_CODE = :stat_code AND STATE_CODE > :state_code) OR (EFFECTIVE_DATE = :effective_date AND STAT_CODE = :stat_code AND STATE_CODE = :state_code AND MAX_DUR > :max_dur) OR (EFFECTIVE_DATE = :effective_date AND STAT_CODE = :stat_code AND STATE_CODE = :state_code AND MAX_DUR = :max_dur)) ";
                else 
                    pagingWhere = ".T035E WHERE (COMPANY_CODE = :company_code) AND (TABLE_SUBSET = :table_subset) AND ((EFFECTIVE_DATE > :effective_date) OR (EFFECTIVE_DATE = :effective_date AND STAT_CODE > :stat_code) OR (EFFECTIVE_DATE = :effective_date AND STAT_CODE = :stat_code AND STATE_CODE > :state_code) OR (EFFECTIVE_DATE = :effective_date AND STAT_CODE = :stat_code AND STATE_CODE = :state_code AND MAX_DUR > :max_dur)) ";
            else
                if (locator)
                    pagingWhere = ".T035E WHERE (COMPANY_CODE = :company_code) AND ((TABLE_SUBSET > :table_subset) OR (TABLE_SUBSET = :table_subset AND EFFECTIVE_DATE > :effective_date) OR (TABLE_SUBSET = :table_subset AND EFFECTIVE_DATE = :effective_date AND STAT_CODE > :stat_code) OR (TABLE_SUBSET = :table_subset AND EFFECTIVE_DATE = :effective_date AND STAT_CODE = :stat_code AND STATE_CODE > :state_code) OR (TABLE_SUBSET = :table_subset AND EFFECTIVE_DATE = :effective_date AND STAT_CODE = :stat_code AND STATE_CODE = :state_code AND MAX_DUR > :max_dur) OR (TABLE_SUBSET = :table_subset AND EFFECTIVE_DATE = :effective_date AND STAT_CODE = :stat_code AND STATE_CODE = :state_code AND MAX_DUR = :max_dur)) ";
                else
                    pagingWhere = ".T035E WHERE (COMPANY_CODE = :company_code) AND ((TABLE_SUBSET > :table_subset) OR (TABLE_SUBSET = :table_subset AND EFFECTIVE_DATE > :effective_date) OR (TABLE_SUBSET = :table_subset AND EFFECTIVE_DATE = :effective_date AND STAT_CODE > :stat_code) OR (TABLE_SUBSET = :table_subset AND EFFECTIVE_DATE = :effective_date AND STAT_CODE = :stat_code AND STATE_CODE > :state_code) OR (TABLE_SUBSET = :table_subset AND EFFECTIVE_DATE = :effective_date AND STAT_CODE = :stat_code AND STATE_CODE = :state_code AND MAX_DUR > :max_dur)) ";
            order = " ORDER BY COMPANY_CODE, TABLE_SUBSET, EFFECTIVE_DATE, STAT_CODE, STATE_CODE, MAX_DUR";
        }
        else {
            if (isSubsetMode)
                pagingWhere = ".T035E WHERE (COMPANY_CODE = :company_code) AND (TABLE_SUBSET = :table_subset) AND ((EFFECTIVE_DATE < :effective_date) OR (EFFECTIVE_DATE = :effective_date AND STAT_CODE < :stat_code) OR (EFFECTIVE_DATE = :effective_date AND STAT_CODE = :stat_code AND STATE_CODE < :state_code) OR (EFFECTIVE_DATE = :effective_date AND STAT_CODE = :stat_code AND STATE_CODE = :state_code AND MAX_DUR < :max_dur)) ";
            else
                pagingWhere = ".T035E WHERE (COMPANY_CODE = :company_code) AND ((TABLE_SUBSET < :table_subset) OR (TABLE_SUBSET = :table_subset AND EFFECTIVE_DATE < :effective_date) OR (TABLE_SUBSET = :table_subset AND EFFECTIVE_DATE = :effective_date AND STAT_CODE < :stat_code) OR (TABLE_SUBSET = :table_subset AND EFFECTIVE_DATE = :effective_date AND STAT_CODE = :stat_code AND STATE_CODE < :state_code) OR (TABLE_SUBSET = :table_subset AND EFFECTIVE_DATE = :effective_date AND STAT_CODE = :stat_code AND STATE_CODE = :state_code AND MAX_DUR < :max_dur)) ";
            order = " ORDER BY COMPANY_CODE DESC, TABLE_SUBSET DESC, EFFECTIVE_DATE DESC, STAT_CODE DESC, STATE_CODE DESC, MAX_DUR DESC";
        }
        return pagingSql + schemaName + pagingWhere + order;
    }
    
    public String prepareInsertStmt(String schemaName) {
        StringBuffer sb = new StringBuffer();
        sb.append("INSERT INTO ").append(schemaName);
        sb.append(".T035E ( ");
        sb.append("COMPANY_CODE, TABLE_SUBSET, EFFECTIVE_DATE, STAT_CODE, STATE_CODE, MAX_DUR, MIN_BASE_ACCEL_AMT, MAX_BASE_ACCEL_AMT, MIN_BASE_ACCEL_PCT, MAX_BASE_ACCEL_PCT, ABR_METHOD, AI_IND, REDUCE_ADB_IND, TERM_RB_IND, LIEN_TBL, LOAN_RDCT_LIEN_IND )");
        sb.append(" VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ? )");
        return sb.toString();
    }
    
    public String prepareUpdateStmt(String schemaName)
    {
        StringBuffer sb = new StringBuffer();
        sb.append("UPDATE ").append(schemaName);
        sb.append(".T035E ");
        sb.append(" SET COMPANY_CODE = ?, TABLE_SUBSET = ?, EFFECTIVE_DATE = ?, STAT_CODE = ?, STATE_CODE = ?, MAX_DUR = ?, MIN_BASE_ACCEL_AMT = ?, MAX_BASE_ACCEL_AMT = ?, MIN_BASE_ACCEL_PCT = ?, MAX_BASE_ACCEL_PCT = ?, ABR_METHOD = ?, AI_IND = ?, REDUCE_ADB_IND = ?, TERM_RB_IND = ?, LIEN_TBL = ?, LOAN_RDCT_LIEN_IND = ?");
        sb.append(" WHERE COMPANY_CODE = ? AND TABLE_SUBSET = ? AND EFFECTIVE_DATE = ? AND STAT_CODE = ? AND STATE_CODE = ? AND MAX_DUR = ?");
        return sb.toString();
    }
    
    public String prepareDeleteStmt(String schemaName)
    {
        StringBuffer sb = new StringBuffer();
        sb.append("DELETE FROM ").append(schemaName);
        sb.append(".T035E ");
        sb.append(" WHERE COMPANY_CODE = ? AND TABLE_SUBSET = ? AND EFFECTIVE_DATE = ? AND STAT_CODE = ? AND STATE_CODE = ? AND MAX_DUR = ?");
        return sb.toString();
    }
}
