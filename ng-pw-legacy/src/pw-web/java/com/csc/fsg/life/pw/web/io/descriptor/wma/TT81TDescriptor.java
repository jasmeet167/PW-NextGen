package com.csc.fsg.life.pw.web.io.descriptor.wma;

import com.csc.fsg.life.pw.web.io.*;

public class TT81TDescriptor
    extends TableDescriptor
{
    private static final String pagingSql = "SELECT COMPANY_CODE, TABLE_SUBSET, MAX_POL_DURATION, BILNG_OPTION_CODE, BILNG_FREQUENCY, EFFECTIVE_DATE, REM_NOTICE_L_P_O_P, LATE_PAY_OFF_PER, AUTNON_FRFIT_ACT_P, RECALC_BILL_DAYS, START_BILLEXT_DAYS FROM ";
    
    public void initialize()
    {
        setRowClass(TT81TRow.class);
        setTableName("TT81T");
        setTableId("T81");
        super.initialize();
    }
    
    public void initializeColumnDescriptors()
    {
        super.initializeColumnDescriptors();
        addColumnDescriptor(new ColumnDescriptor(this,"getCompanyCode","setCompanyCode","COMPANY_CODE,1,1,3,0,true|,0,CHAR,1,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getTableSubset","setTableSubset","TABLE_SUBSET,1,2,16,0,true|,0,CHAR,1,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getMaxPolDuration","setMaxPolDuration","MAX_POL_DURATION,3,3,3,0,true|,0,INTEGER,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getBilngOptionCode","setBilngOptionCode","BILNG_OPTION_CODE,1,4,1,0,true|,0,CHAR,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getBilngFrequency","setBilngFrequency","BILNG_FREQUENCY,1,5,2,0,true|,0,CHAR,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getEffectiveDate","setEffectiveDate","EFFECTIVE_DATE,91,6,10,0,true|,0,DATE,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getRemNoticeLPOP","setRemNoticeLPOP","REM_NOTICE_L_P_O_P,3,7,3,0,false|,0,INTEGER,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getLatePayOffPer","setLatePayOffPer","LATE_PAY_OFF_PER,3,8,3,0,false|,0,INTEGER,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getAutnonFrfitActP","setAutnonFrfitActP","AUTNON_FRFIT_ACT_P,3,9,3,0,false|,0,INTEGER,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getRecalcBillDays","setRecalcBillDays","RECALC_BILL_DAYS,3,10,3,0,false|,0,INTEGER,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getStartBillextDays","setStartBillextDays","START_BILLEXT_DAYS,3,11,3,0,false|,0,INTEGER,0,null,null,null,null,null"));
    }
    
    public String getPagingSQL(String schemaName,boolean isSubsetMode,boolean isNext, boolean locator)
    {
        String pagingWhere = null;
        String order = null;
        if (isNext) {
            if (isSubsetMode)
                if (locator)
                    pagingWhere = ".TT81T WHERE (COMPANY_CODE = :company_code) AND (TABLE_SUBSET = :table_subset) AND ((MAX_POL_DURATION > :max_pol_duration) OR (MAX_POL_DURATION = :max_pol_duration AND BILNG_OPTION_CODE > :bilng_option_code) OR (MAX_POL_DURATION = :max_pol_duration AND BILNG_OPTION_CODE = :bilng_option_code AND BILNG_FREQUENCY > :bilng_frequency) OR (MAX_POL_DURATION = :max_pol_duration AND BILNG_OPTION_CODE = :bilng_option_code AND BILNG_FREQUENCY = :bilng_frequency AND EFFECTIVE_DATE > :effective_date) OR (MAX_POL_DURATION = :max_pol_duration AND BILNG_OPTION_CODE = :bilng_option_code AND BILNG_FREQUENCY = :bilng_frequency AND EFFECTIVE_DATE = :effective_date)) ";
                else 
                    pagingWhere = ".TT81T WHERE (COMPANY_CODE = :company_code) AND (TABLE_SUBSET = :table_subset) AND ((MAX_POL_DURATION > :max_pol_duration) OR (MAX_POL_DURATION = :max_pol_duration AND BILNG_OPTION_CODE > :bilng_option_code) OR (MAX_POL_DURATION = :max_pol_duration AND BILNG_OPTION_CODE = :bilng_option_code AND BILNG_FREQUENCY > :bilng_frequency) OR (MAX_POL_DURATION = :max_pol_duration AND BILNG_OPTION_CODE = :bilng_option_code AND BILNG_FREQUENCY = :bilng_frequency AND EFFECTIVE_DATE > :effective_date)) ";
            else
                if (locator)
                    pagingWhere = ".TT81T WHERE (COMPANY_CODE = :company_code) AND ((TABLE_SUBSET > :table_subset) OR (TABLE_SUBSET = :table_subset AND MAX_POL_DURATION > :max_pol_duration) OR (TABLE_SUBSET = :table_subset AND MAX_POL_DURATION = :max_pol_duration AND BILNG_OPTION_CODE > :bilng_option_code) OR (TABLE_SUBSET = :table_subset AND MAX_POL_DURATION = :max_pol_duration AND BILNG_OPTION_CODE = :bilng_option_code AND BILNG_FREQUENCY > :bilng_frequency) OR (TABLE_SUBSET = :table_subset AND MAX_POL_DURATION = :max_pol_duration AND BILNG_OPTION_CODE = :bilng_option_code AND BILNG_FREQUENCY = :bilng_frequency AND EFFECTIVE_DATE > :effective_date) OR (TABLE_SUBSET = :table_subset AND MAX_POL_DURATION = :max_pol_duration AND BILNG_OPTION_CODE = :bilng_option_code AND BILNG_FREQUENCY = :bilng_frequency AND EFFECTIVE_DATE = :effective_date)) ";
                else
                    pagingWhere = ".TT81T WHERE (COMPANY_CODE = :company_code) AND ((TABLE_SUBSET > :table_subset) OR (TABLE_SUBSET = :table_subset AND MAX_POL_DURATION > :max_pol_duration) OR (TABLE_SUBSET = :table_subset AND MAX_POL_DURATION = :max_pol_duration AND BILNG_OPTION_CODE > :bilng_option_code) OR (TABLE_SUBSET = :table_subset AND MAX_POL_DURATION = :max_pol_duration AND BILNG_OPTION_CODE = :bilng_option_code AND BILNG_FREQUENCY > :bilng_frequency) OR (TABLE_SUBSET = :table_subset AND MAX_POL_DURATION = :max_pol_duration AND BILNG_OPTION_CODE = :bilng_option_code AND BILNG_FREQUENCY = :bilng_frequency AND EFFECTIVE_DATE > :effective_date)) ";
            order = " ORDER BY COMPANY_CODE, TABLE_SUBSET, MAX_POL_DURATION, BILNG_OPTION_CODE, BILNG_FREQUENCY, EFFECTIVE_DATE";
        }
        else {
            if (isSubsetMode)
                pagingWhere = ".TT81T WHERE (COMPANY_CODE = :company_code) AND (TABLE_SUBSET = :table_subset) AND ((MAX_POL_DURATION < :max_pol_duration) OR (MAX_POL_DURATION = :max_pol_duration AND BILNG_OPTION_CODE < :bilng_option_code) OR (MAX_POL_DURATION = :max_pol_duration AND BILNG_OPTION_CODE = :bilng_option_code AND BILNG_FREQUENCY < :bilng_frequency) OR (MAX_POL_DURATION = :max_pol_duration AND BILNG_OPTION_CODE = :bilng_option_code AND BILNG_FREQUENCY = :bilng_frequency AND EFFECTIVE_DATE < :effective_date)) ";
            else
                pagingWhere = ".TT81T WHERE (COMPANY_CODE = :company_code) AND ((TABLE_SUBSET < :table_subset) OR (TABLE_SUBSET = :table_subset AND MAX_POL_DURATION < :max_pol_duration) OR (TABLE_SUBSET = :table_subset AND MAX_POL_DURATION = :max_pol_duration AND BILNG_OPTION_CODE < :bilng_option_code) OR (TABLE_SUBSET = :table_subset AND MAX_POL_DURATION = :max_pol_duration AND BILNG_OPTION_CODE = :bilng_option_code AND BILNG_FREQUENCY < :bilng_frequency) OR (TABLE_SUBSET = :table_subset AND MAX_POL_DURATION = :max_pol_duration AND BILNG_OPTION_CODE = :bilng_option_code AND BILNG_FREQUENCY = :bilng_frequency AND EFFECTIVE_DATE < :effective_date)) ";
            order = " ORDER BY COMPANY_CODE DESC, TABLE_SUBSET DESC, MAX_POL_DURATION DESC, BILNG_OPTION_CODE DESC, BILNG_FREQUENCY DESC, EFFECTIVE_DATE DESC";
        }
        return pagingSql + schemaName + pagingWhere + order;
    }
    
    public String prepareInsertStmt(String schemaName) {
        StringBuffer sb = new StringBuffer();
        sb.append("INSERT INTO ").append(schemaName);
        sb.append(".TT81T ( ");
        sb.append("COMPANY_CODE, TABLE_SUBSET, MAX_POL_DURATION, BILNG_OPTION_CODE, BILNG_FREQUENCY, EFFECTIVE_DATE, REM_NOTICE_L_P_O_P, LATE_PAY_OFF_PER, AUTNON_FRFIT_ACT_P, RECALC_BILL_DAYS, START_BILLEXT_DAYS )");
        sb.append(" VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ? )");
        return sb.toString();
    }
    
    public String prepareUpdateStmt(String schemaName)
    {
        StringBuffer sb = new StringBuffer();
        sb.append("UPDATE ").append(schemaName);
        sb.append(".TT81T ");
        sb.append(" SET COMPANY_CODE = ?, TABLE_SUBSET = ?, MAX_POL_DURATION = ?, BILNG_OPTION_CODE = ?, BILNG_FREQUENCY = ?, EFFECTIVE_DATE = ?, REM_NOTICE_L_P_O_P = ?, LATE_PAY_OFF_PER = ?, AUTNON_FRFIT_ACT_P = ?, RECALC_BILL_DAYS = ?, START_BILLEXT_DAYS = ?");
        sb.append(" WHERE COMPANY_CODE = ? AND TABLE_SUBSET = ? AND MAX_POL_DURATION = ? AND BILNG_OPTION_CODE = ? AND BILNG_FREQUENCY = ? AND EFFECTIVE_DATE = ?");
        return sb.toString();
    }
    
    public String prepareDeleteStmt(String schemaName)
    {
        StringBuffer sb = new StringBuffer();
        sb.append("DELETE FROM ").append(schemaName);
        sb.append(".TT81T ");
        sb.append(" WHERE COMPANY_CODE = ? AND TABLE_SUBSET = ? AND MAX_POL_DURATION = ? AND BILNG_OPTION_CODE = ? AND BILNG_FREQUENCY = ? AND EFFECTIVE_DATE = ?");
        return sb.toString();
    }
}
