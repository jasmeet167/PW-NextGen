package com.csc.fsg.life.pw.io.descriptor.wma;

import com.csc.fsg.life.pw.io.*;

public class TT67TDescriptor
    extends TableDescriptor
{
    private static final String pagingSql = "SELECT COMPANY_CODE, TABLE_SUBSET, EFFECTIVE_DATE, SEX_CODE, SPECIAL_CLASS_CODE, PREMIUM_CLASS_CODE, MAXIMUM_AGE, MAX_DURATION, SPEC_CLASS_MAX_DUR, NET_SNGL_PREM_FCTR, REPL_COST_NSP_FCTR FROM ";
    
    public void initialize()
    {
        setRowClass(TT67TRow.class);
        setTableName("TT67T");
        setTableId("T67");
        super.initialize();
    }
    
    public void initializeColumnDescriptors()
    {
        super.initializeColumnDescriptors();
        addColumnDescriptor(new ColumnDescriptor(this,"getCompanyCode","setCompanyCode","COMPANY_CODE,1,1,3,0,true|,0,CHAR,1,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getTableSubset","setTableSubset","TABLE_SUBSET,1,2,16,0,true|,0,CHAR,1,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getEffectiveDate","setEffectiveDate","EFFECTIVE_DATE,91,3,10,0,true|,0,DATE,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getSexCode","setSexCode","SEX_CODE,1,4,1,0,true|,0,CHAR,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getSpecialClassCode","setSpecialClassCode","SPECIAL_CLASS_CODE,1,5,2,0,true|,0,CHAR,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getPremiumClassCode","setPremiumClassCode","PREMIUM_CLASS_CODE,1,6,1,0,true|,0,CHAR,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getMaximumAge","setMaximumAge","MAXIMUM_AGE,3,7,3,0,true|,0,INTEGER,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getMaxDuration","setMaxDuration","MAX_DURATION,3,8,3,0,true|,0,INTEGER,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getSpecClassMaxDur","setSpecClassMaxDur","SPEC_CLASS_MAX_DUR,3,9,3,0,true|,0,INTEGER,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getNetSnglPremFctr","setNetSnglPremFctr","NET_SNGL_PREM_FCTR,3,10,11,7,false|,0,DOUBLE,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getReplCostNspFctr","setReplCostNspFctr","REPL_COST_NSP_FCTR,3,11,11,7,false|,0,DOUBLE,0,null,null,null,null,null"));
    }
    
    public String getPagingSQL(String schemaName,boolean isSubsetMode,boolean isNext, boolean locator)
    {
        String pagingWhere = null;
        String order = null;
        if (isNext) {
            if (isSubsetMode)
                if (locator)
                    pagingWhere = ".TT67T WHERE (COMPANY_CODE = :company_code) AND (TABLE_SUBSET = :table_subset) AND ((EFFECTIVE_DATE > :effective_date) OR (EFFECTIVE_DATE = :effective_date AND SEX_CODE > :sex_code) OR (EFFECTIVE_DATE = :effective_date AND SEX_CODE = :sex_code AND SPECIAL_CLASS_CODE > :special_class_code) OR (EFFECTIVE_DATE = :effective_date AND SEX_CODE = :sex_code AND SPECIAL_CLASS_CODE = :special_class_code AND PREMIUM_CLASS_CODE > :premium_class_code) OR (EFFECTIVE_DATE = :effective_date AND SEX_CODE = :sex_code AND SPECIAL_CLASS_CODE = :special_class_code AND PREMIUM_CLASS_CODE = :premium_class_code AND MAXIMUM_AGE > :maximum_age) OR (EFFECTIVE_DATE = :effective_date AND SEX_CODE = :sex_code AND SPECIAL_CLASS_CODE = :special_class_code AND PREMIUM_CLASS_CODE = :premium_class_code AND MAXIMUM_AGE = :maximum_age AND MAX_DURATION > :max_duration) OR (EFFECTIVE_DATE = :effective_date AND SEX_CODE = :sex_code AND SPECIAL_CLASS_CODE = :special_class_code AND PREMIUM_CLASS_CODE = :premium_class_code AND MAXIMUM_AGE = :maximum_age AND MAX_DURATION = :max_duration AND SPEC_CLASS_MAX_DUR > :spec_class_max_dur) OR (EFFECTIVE_DATE = :effective_date AND SEX_CODE = :sex_code AND SPECIAL_CLASS_CODE = :special_class_code AND PREMIUM_CLASS_CODE = :premium_class_code AND MAXIMUM_AGE = :maximum_age AND MAX_DURATION = :max_duration AND SPEC_CLASS_MAX_DUR = :spec_class_max_dur)) ";
                else 
                    pagingWhere = ".TT67T WHERE (COMPANY_CODE = :company_code) AND (TABLE_SUBSET = :table_subset) AND ((EFFECTIVE_DATE > :effective_date) OR (EFFECTIVE_DATE = :effective_date AND SEX_CODE > :sex_code) OR (EFFECTIVE_DATE = :effective_date AND SEX_CODE = :sex_code AND SPECIAL_CLASS_CODE > :special_class_code) OR (EFFECTIVE_DATE = :effective_date AND SEX_CODE = :sex_code AND SPECIAL_CLASS_CODE = :special_class_code AND PREMIUM_CLASS_CODE > :premium_class_code) OR (EFFECTIVE_DATE = :effective_date AND SEX_CODE = :sex_code AND SPECIAL_CLASS_CODE = :special_class_code AND PREMIUM_CLASS_CODE = :premium_class_code AND MAXIMUM_AGE > :maximum_age) OR (EFFECTIVE_DATE = :effective_date AND SEX_CODE = :sex_code AND SPECIAL_CLASS_CODE = :special_class_code AND PREMIUM_CLASS_CODE = :premium_class_code AND MAXIMUM_AGE = :maximum_age AND MAX_DURATION > :max_duration) OR (EFFECTIVE_DATE = :effective_date AND SEX_CODE = :sex_code AND SPECIAL_CLASS_CODE = :special_class_code AND PREMIUM_CLASS_CODE = :premium_class_code AND MAXIMUM_AGE = :maximum_age AND MAX_DURATION = :max_duration AND SPEC_CLASS_MAX_DUR > :spec_class_max_dur)) ";
            else
                if (locator)
                    pagingWhere = ".TT67T WHERE (COMPANY_CODE = :company_code) AND ((TABLE_SUBSET > :table_subset) OR (TABLE_SUBSET = :table_subset AND EFFECTIVE_DATE > :effective_date) OR (TABLE_SUBSET = :table_subset AND EFFECTIVE_DATE = :effective_date AND SEX_CODE > :sex_code) OR (TABLE_SUBSET = :table_subset AND EFFECTIVE_DATE = :effective_date AND SEX_CODE = :sex_code AND SPECIAL_CLASS_CODE > :special_class_code) OR (TABLE_SUBSET = :table_subset AND EFFECTIVE_DATE = :effective_date AND SEX_CODE = :sex_code AND SPECIAL_CLASS_CODE = :special_class_code AND PREMIUM_CLASS_CODE > :premium_class_code) OR (TABLE_SUBSET = :table_subset AND EFFECTIVE_DATE = :effective_date AND SEX_CODE = :sex_code AND SPECIAL_CLASS_CODE = :special_class_code AND PREMIUM_CLASS_CODE = :premium_class_code AND MAXIMUM_AGE > :maximum_age) OR (TABLE_SUBSET = :table_subset AND EFFECTIVE_DATE = :effective_date AND SEX_CODE = :sex_code AND SPECIAL_CLASS_CODE = :special_class_code AND PREMIUM_CLASS_CODE = :premium_class_code AND MAXIMUM_AGE = :maximum_age AND MAX_DURATION > :max_duration) OR (TABLE_SUBSET = :table_subset AND EFFECTIVE_DATE = :effective_date AND SEX_CODE = :sex_code AND SPECIAL_CLASS_CODE = :special_class_code AND PREMIUM_CLASS_CODE = :premium_class_code AND MAXIMUM_AGE = :maximum_age AND MAX_DURATION = :max_duration AND SPEC_CLASS_MAX_DUR > :spec_class_max_dur) OR (TABLE_SUBSET = :table_subset AND EFFECTIVE_DATE = :effective_date AND SEX_CODE = :sex_code AND SPECIAL_CLASS_CODE = :special_class_code AND PREMIUM_CLASS_CODE = :premium_class_code AND MAXIMUM_AGE = :maximum_age AND MAX_DURATION = :max_duration AND SPEC_CLASS_MAX_DUR = :spec_class_max_dur)) ";
                else
                    pagingWhere = ".TT67T WHERE (COMPANY_CODE = :company_code) AND ((TABLE_SUBSET > :table_subset) OR (TABLE_SUBSET = :table_subset AND EFFECTIVE_DATE > :effective_date) OR (TABLE_SUBSET = :table_subset AND EFFECTIVE_DATE = :effective_date AND SEX_CODE > :sex_code) OR (TABLE_SUBSET = :table_subset AND EFFECTIVE_DATE = :effective_date AND SEX_CODE = :sex_code AND SPECIAL_CLASS_CODE > :special_class_code) OR (TABLE_SUBSET = :table_subset AND EFFECTIVE_DATE = :effective_date AND SEX_CODE = :sex_code AND SPECIAL_CLASS_CODE = :special_class_code AND PREMIUM_CLASS_CODE > :premium_class_code) OR (TABLE_SUBSET = :table_subset AND EFFECTIVE_DATE = :effective_date AND SEX_CODE = :sex_code AND SPECIAL_CLASS_CODE = :special_class_code AND PREMIUM_CLASS_CODE = :premium_class_code AND MAXIMUM_AGE > :maximum_age) OR (TABLE_SUBSET = :table_subset AND EFFECTIVE_DATE = :effective_date AND SEX_CODE = :sex_code AND SPECIAL_CLASS_CODE = :special_class_code AND PREMIUM_CLASS_CODE = :premium_class_code AND MAXIMUM_AGE = :maximum_age AND MAX_DURATION > :max_duration) OR (TABLE_SUBSET = :table_subset AND EFFECTIVE_DATE = :effective_date AND SEX_CODE = :sex_code AND SPECIAL_CLASS_CODE = :special_class_code AND PREMIUM_CLASS_CODE = :premium_class_code AND MAXIMUM_AGE = :maximum_age AND MAX_DURATION = :max_duration AND SPEC_CLASS_MAX_DUR > :spec_class_max_dur)) ";
            order = " ORDER BY COMPANY_CODE, TABLE_SUBSET, EFFECTIVE_DATE, SEX_CODE, SPECIAL_CLASS_CODE, PREMIUM_CLASS_CODE, MAXIMUM_AGE, MAX_DURATION, SPEC_CLASS_MAX_DUR";
        }
        else {
            if (isSubsetMode)
                pagingWhere = ".TT67T WHERE (COMPANY_CODE = :company_code) AND (TABLE_SUBSET = :table_subset) AND ((EFFECTIVE_DATE < :effective_date) OR (EFFECTIVE_DATE = :effective_date AND SEX_CODE < :sex_code) OR (EFFECTIVE_DATE = :effective_date AND SEX_CODE = :sex_code AND SPECIAL_CLASS_CODE < :special_class_code) OR (EFFECTIVE_DATE = :effective_date AND SEX_CODE = :sex_code AND SPECIAL_CLASS_CODE = :special_class_code AND PREMIUM_CLASS_CODE < :premium_class_code) OR (EFFECTIVE_DATE = :effective_date AND SEX_CODE = :sex_code AND SPECIAL_CLASS_CODE = :special_class_code AND PREMIUM_CLASS_CODE = :premium_class_code AND MAXIMUM_AGE < :maximum_age) OR (EFFECTIVE_DATE = :effective_date AND SEX_CODE = :sex_code AND SPECIAL_CLASS_CODE = :special_class_code AND PREMIUM_CLASS_CODE = :premium_class_code AND MAXIMUM_AGE = :maximum_age AND MAX_DURATION < :max_duration) OR (EFFECTIVE_DATE = :effective_date AND SEX_CODE = :sex_code AND SPECIAL_CLASS_CODE = :special_class_code AND PREMIUM_CLASS_CODE = :premium_class_code AND MAXIMUM_AGE = :maximum_age AND MAX_DURATION = :max_duration AND SPEC_CLASS_MAX_DUR < :spec_class_max_dur)) ";
            else
                pagingWhere = ".TT67T WHERE (COMPANY_CODE = :company_code) AND ((TABLE_SUBSET < :table_subset) OR (TABLE_SUBSET = :table_subset AND EFFECTIVE_DATE < :effective_date) OR (TABLE_SUBSET = :table_subset AND EFFECTIVE_DATE = :effective_date AND SEX_CODE < :sex_code) OR (TABLE_SUBSET = :table_subset AND EFFECTIVE_DATE = :effective_date AND SEX_CODE = :sex_code AND SPECIAL_CLASS_CODE < :special_class_code) OR (TABLE_SUBSET = :table_subset AND EFFECTIVE_DATE = :effective_date AND SEX_CODE = :sex_code AND SPECIAL_CLASS_CODE = :special_class_code AND PREMIUM_CLASS_CODE < :premium_class_code) OR (TABLE_SUBSET = :table_subset AND EFFECTIVE_DATE = :effective_date AND SEX_CODE = :sex_code AND SPECIAL_CLASS_CODE = :special_class_code AND PREMIUM_CLASS_CODE = :premium_class_code AND MAXIMUM_AGE < :maximum_age) OR (TABLE_SUBSET = :table_subset AND EFFECTIVE_DATE = :effective_date AND SEX_CODE = :sex_code AND SPECIAL_CLASS_CODE = :special_class_code AND PREMIUM_CLASS_CODE = :premium_class_code AND MAXIMUM_AGE = :maximum_age AND MAX_DURATION < :max_duration) OR (TABLE_SUBSET = :table_subset AND EFFECTIVE_DATE = :effective_date AND SEX_CODE = :sex_code AND SPECIAL_CLASS_CODE = :special_class_code AND PREMIUM_CLASS_CODE = :premium_class_code AND MAXIMUM_AGE = :maximum_age AND MAX_DURATION = :max_duration AND SPEC_CLASS_MAX_DUR < :spec_class_max_dur)) ";
            order = " ORDER BY COMPANY_CODE DESC, TABLE_SUBSET DESC, EFFECTIVE_DATE DESC, SEX_CODE DESC, SPECIAL_CLASS_CODE DESC, PREMIUM_CLASS_CODE DESC, MAXIMUM_AGE DESC, MAX_DURATION DESC, SPEC_CLASS_MAX_DUR DESC";
        }
        return pagingSql + schemaName + pagingWhere + order;
    }
    
    public String prepareInsertStmt(String schemaName) {
        StringBuffer sb = new StringBuffer();
        sb.append("INSERT INTO ").append(schemaName);
        sb.append(".TT67T ( ");
        sb.append("COMPANY_CODE, TABLE_SUBSET, EFFECTIVE_DATE, SEX_CODE, SPECIAL_CLASS_CODE, PREMIUM_CLASS_CODE, MAXIMUM_AGE, MAX_DURATION, SPEC_CLASS_MAX_DUR, NET_SNGL_PREM_FCTR, REPL_COST_NSP_FCTR )");
        sb.append(" VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ? )");
        return sb.toString();
    }
    
    public String prepareUpdateStmt(String schemaName)
    {
        StringBuffer sb = new StringBuffer();
        sb.append("UPDATE ").append(schemaName);
        sb.append(".TT67T ");
        sb.append(" SET COMPANY_CODE = ?, TABLE_SUBSET = ?, EFFECTIVE_DATE = ?, SEX_CODE = ?, SPECIAL_CLASS_CODE = ?, PREMIUM_CLASS_CODE = ?, MAXIMUM_AGE = ?, MAX_DURATION = ?, SPEC_CLASS_MAX_DUR = ?, NET_SNGL_PREM_FCTR = ?, REPL_COST_NSP_FCTR = ?");
        sb.append(" WHERE COMPANY_CODE = ? AND TABLE_SUBSET = ? AND EFFECTIVE_DATE = ? AND SEX_CODE = ? AND SPECIAL_CLASS_CODE = ? AND PREMIUM_CLASS_CODE = ? AND MAXIMUM_AGE = ? AND MAX_DURATION = ? AND SPEC_CLASS_MAX_DUR = ?");
        return sb.toString();
    }
    
    public String prepareDeleteStmt(String schemaName)
    {
        StringBuffer sb = new StringBuffer();
        sb.append("DELETE FROM ").append(schemaName);
        sb.append(".TT67T ");
        sb.append(" WHERE COMPANY_CODE = ? AND TABLE_SUBSET = ? AND EFFECTIVE_DATE = ? AND SEX_CODE = ? AND SPECIAL_CLASS_CODE = ? AND PREMIUM_CLASS_CODE = ? AND MAXIMUM_AGE = ? AND MAX_DURATION = ? AND SPEC_CLASS_MAX_DUR = ?");
        return sb.toString();
    }
}
