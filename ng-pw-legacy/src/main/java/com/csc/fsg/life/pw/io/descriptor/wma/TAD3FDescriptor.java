package com.csc.fsg.life.pw.io.descriptor.wma;

import com.csc.fsg.life.pw.io.*;

public class TAD3FDescriptor
    extends TableDescriptor
{
    private static final String pagingSql = "SELECT COMPANY_CODE, TABLE_SUBSET, EFFECTIVE_DATE, THRESHOLD_AMOUNT, GUARANTEE_DURATION, DECL_TBL_NUMBER, DECL_GUAR_OPTION, DECL_GUAR_TIMING, DECL_RECPT_SELECT, NOTICE_DAYS, WINDOW_DAYS_PRIOR, WINDOW_DAYS_AFTER FROM ";
    
    public void initialize()
    {
        setRowClass(TAD3FRow.class);
        setTableName("TAD3F");
        setTableId("AD3");
        super.initialize();
    }
    
    public void initializeColumnDescriptors()
    {
        super.initializeColumnDescriptors();
        addColumnDescriptor(new ColumnDescriptor(this,"getCompanyCode","setCompanyCode","COMPANY_CODE,1,1,3,0,true|,0,CHAR,1,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getTableSubset","setTableSubset","TABLE_SUBSET,1,2,16,0,true|,0,CHAR,1,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getEffectiveDate","setEffectiveDate","EFFECTIVE_DATE,91,3,10,0,true|,0,DATE,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getThresholdAmount","setThresholdAmount","THRESHOLD_AMOUNT,3,4,9,0,true|,0,INTEGER,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getGuaranteeDuration","setGuaranteeDuration","GUARANTEE_DURATION,3,5,3,0,true|,0,INTEGER,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getDeclTblNumber","setDeclTblNumber","DECL_TBL_NUMBER,1,6,16,0,false|,0,CHAR,0,025,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getDeclGuarOption","setDeclGuarOption","DECL_GUAR_OPTION,1,7,5,0,false|,0,CHAR,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getDeclGuarTiming","setDeclGuarTiming","DECL_GUAR_TIMING,1,8,1,0,false|,0,CHAR,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getDeclRecptSelect","setDeclRecptSelect","DECL_RECPT_SELECT,1,9,1,0,false|,0,CHAR,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getNoticeDays","setNoticeDays","NOTICE_DAYS,3,10,3,0,false|,0,INTEGER,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getWindowDaysPrior","setWindowDaysPrior","WINDOW_DAYS_PRIOR,3,11,3,0,false|,0,INTEGER,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getWindowDaysAfter","setWindowDaysAfter","WINDOW_DAYS_AFTER,3,12,3,0,false|,0,INTEGER,0,null,null,null,null,null"));
    }
    
    public String getPagingSQL(String schemaName,boolean isSubsetMode,boolean isNext, boolean locator)
    {
        String pagingWhere = null;
        String order = null;
        if (isNext) {
            if (isSubsetMode)
                if (locator)
                    pagingWhere = ".TAD3F WHERE (COMPANY_CODE = :company_code) AND (TABLE_SUBSET = :table_subset) AND ((EFFECTIVE_DATE > :effective_date) OR (EFFECTIVE_DATE = :effective_date AND THRESHOLD_AMOUNT > :threshold_amount) OR (EFFECTIVE_DATE = :effective_date AND THRESHOLD_AMOUNT = :threshold_amount AND GUARANTEE_DURATION > :guarantee_duration) OR (EFFECTIVE_DATE = :effective_date AND THRESHOLD_AMOUNT = :threshold_amount AND GUARANTEE_DURATION = :guarantee_duration)) ";
                else 
                    pagingWhere = ".TAD3F WHERE (COMPANY_CODE = :company_code) AND (TABLE_SUBSET = :table_subset) AND ((EFFECTIVE_DATE > :effective_date) OR (EFFECTIVE_DATE = :effective_date AND THRESHOLD_AMOUNT > :threshold_amount) OR (EFFECTIVE_DATE = :effective_date AND THRESHOLD_AMOUNT = :threshold_amount AND GUARANTEE_DURATION > :guarantee_duration)) ";
            else
                if (locator)
                    pagingWhere = ".TAD3F WHERE (COMPANY_CODE = :company_code) AND ((TABLE_SUBSET > :table_subset) OR (TABLE_SUBSET = :table_subset AND EFFECTIVE_DATE > :effective_date) OR (TABLE_SUBSET = :table_subset AND EFFECTIVE_DATE = :effective_date AND THRESHOLD_AMOUNT > :threshold_amount) OR (TABLE_SUBSET = :table_subset AND EFFECTIVE_DATE = :effective_date AND THRESHOLD_AMOUNT = :threshold_amount AND GUARANTEE_DURATION > :guarantee_duration) OR (TABLE_SUBSET = :table_subset AND EFFECTIVE_DATE = :effective_date AND THRESHOLD_AMOUNT = :threshold_amount AND GUARANTEE_DURATION = :guarantee_duration)) ";
                else
                    pagingWhere = ".TAD3F WHERE (COMPANY_CODE = :company_code) AND ((TABLE_SUBSET > :table_subset) OR (TABLE_SUBSET = :table_subset AND EFFECTIVE_DATE > :effective_date) OR (TABLE_SUBSET = :table_subset AND EFFECTIVE_DATE = :effective_date AND THRESHOLD_AMOUNT > :threshold_amount) OR (TABLE_SUBSET = :table_subset AND EFFECTIVE_DATE = :effective_date AND THRESHOLD_AMOUNT = :threshold_amount AND GUARANTEE_DURATION > :guarantee_duration)) ";
            order = " ORDER BY COMPANY_CODE, TABLE_SUBSET, EFFECTIVE_DATE, THRESHOLD_AMOUNT, GUARANTEE_DURATION";
        }
        else {
            if (isSubsetMode)
                pagingWhere = ".TAD3F WHERE (COMPANY_CODE = :company_code) AND (TABLE_SUBSET = :table_subset) AND ((EFFECTIVE_DATE < :effective_date) OR (EFFECTIVE_DATE = :effective_date AND THRESHOLD_AMOUNT < :threshold_amount) OR (EFFECTIVE_DATE = :effective_date AND THRESHOLD_AMOUNT = :threshold_amount AND GUARANTEE_DURATION < :guarantee_duration)) ";
            else
                pagingWhere = ".TAD3F WHERE (COMPANY_CODE = :company_code) AND ((TABLE_SUBSET < :table_subset) OR (TABLE_SUBSET = :table_subset AND EFFECTIVE_DATE < :effective_date) OR (TABLE_SUBSET = :table_subset AND EFFECTIVE_DATE = :effective_date AND THRESHOLD_AMOUNT < :threshold_amount) OR (TABLE_SUBSET = :table_subset AND EFFECTIVE_DATE = :effective_date AND THRESHOLD_AMOUNT = :threshold_amount AND GUARANTEE_DURATION < :guarantee_duration)) ";
            order = " ORDER BY COMPANY_CODE DESC, TABLE_SUBSET DESC, EFFECTIVE_DATE DESC, THRESHOLD_AMOUNT DESC, GUARANTEE_DURATION DESC";
        }
        return pagingSql + schemaName + pagingWhere + order;
    }
    
    public String prepareInsertStmt(String schemaName) {
        StringBuffer sb = new StringBuffer();
        sb.append("INSERT INTO ").append(schemaName);
        sb.append(".TAD3F ( ");
        sb.append("COMPANY_CODE, TABLE_SUBSET, EFFECTIVE_DATE, THRESHOLD_AMOUNT, GUARANTEE_DURATION, DECL_TBL_NUMBER, DECL_GUAR_OPTION, DECL_GUAR_TIMING, DECL_RECPT_SELECT, NOTICE_DAYS, WINDOW_DAYS_PRIOR, WINDOW_DAYS_AFTER )");
        sb.append(" VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ? )");
        return sb.toString();
    }
    
    public String prepareUpdateStmt(String schemaName)
    {
        StringBuffer sb = new StringBuffer();
        sb.append("UPDATE ").append(schemaName);
        sb.append(".TAD3F ");
        sb.append(" SET COMPANY_CODE = ?, TABLE_SUBSET = ?, EFFECTIVE_DATE = ?, THRESHOLD_AMOUNT = ?, GUARANTEE_DURATION = ?, DECL_TBL_NUMBER = ?, DECL_GUAR_OPTION = ?, DECL_GUAR_TIMING = ?, DECL_RECPT_SELECT = ?, NOTICE_DAYS = ?, WINDOW_DAYS_PRIOR = ?, WINDOW_DAYS_AFTER = ?");
        sb.append(" WHERE COMPANY_CODE = ? AND TABLE_SUBSET = ? AND EFFECTIVE_DATE = ? AND THRESHOLD_AMOUNT = ? AND GUARANTEE_DURATION = ?");
        return sb.toString();
    }
    
    public String prepareDeleteStmt(String schemaName)
    {
        StringBuffer sb = new StringBuffer();
        sb.append("DELETE FROM ").append(schemaName);
        sb.append(".TAD3F ");
        sb.append(" WHERE COMPANY_CODE = ? AND TABLE_SUBSET = ? AND EFFECTIVE_DATE = ? AND THRESHOLD_AMOUNT = ? AND GUARANTEE_DURATION = ?");
        return sb.toString();
    }
}
