package com.csc.fsg.life.pw.web.io.descriptor.wma;

import com.csc.fsg.life.pw.web.io.*;

public class TTBBTDescriptor
    extends TableDescriptor
{
    private static final String pagingSql = "SELECT COMPANY_CODE, TABLE_SUBSET, EFFECTIVE_DATE, MAX_AGE, MAX_DURATION, BAS_FLATCHG_PR_PAY, BAS_FLATCHG_NONPRM FROM ";
    
    public void initialize()
    {
        setRowClass(TTBBTRow.class);
        setTableName("TTBBT");
        setTableId("TBB");
        super.initialize();
    }
    
    public void initializeColumnDescriptors()
    {
        super.initializeColumnDescriptors();
        addColumnDescriptor(new ColumnDescriptor(this,"getCompanyCode","setCompanyCode","COMPANY_CODE,1,1,3,0,true|,0,CHAR,1,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getTableSubset","setTableSubset","TABLE_SUBSET,1,2,16,0,true|,0,CHAR,1,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getEffectiveDate","setEffectiveDate","EFFECTIVE_DATE,91,3,10,0,true|,0,DATE,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getMaxAge","setMaxAge","MAX_AGE,3,4,3,0,true|,0,INTEGER,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getMaxDuration","setMaxDuration","MAX_DURATION,3,5,3,0,true|,0,INTEGER,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getBasFlatchgPrPay","setBasFlatchgPrPay","BAS_FLATCHG_PR_PAY,3,6,11,2,false|,0,DOUBLE,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getBasFlatchgNonprm","setBasFlatchgNonprm","BAS_FLATCHG_NONPRM,3,7,11,2,false|,0,DOUBLE,0,null,null,null,null,null"));
    }
    
    public String getPagingSQL(String schemaName,boolean isSubsetMode,boolean isNext, boolean locator)
    {
        String pagingWhere = null;
        String order = null;
        if (isNext) {
            if (isSubsetMode)
                if (locator)
                    pagingWhere = ".TTBBT WHERE (COMPANY_CODE = :company_code) AND (TABLE_SUBSET = :table_subset) AND ((EFFECTIVE_DATE > :effective_date) OR (EFFECTIVE_DATE = :effective_date AND MAX_AGE > :max_age) OR (EFFECTIVE_DATE = :effective_date AND MAX_AGE = :max_age AND MAX_DURATION > :max_duration) OR (EFFECTIVE_DATE = :effective_date AND MAX_AGE = :max_age AND MAX_DURATION = :max_duration)) ";
                else 
                    pagingWhere = ".TTBBT WHERE (COMPANY_CODE = :company_code) AND (TABLE_SUBSET = :table_subset) AND ((EFFECTIVE_DATE > :effective_date) OR (EFFECTIVE_DATE = :effective_date AND MAX_AGE > :max_age) OR (EFFECTIVE_DATE = :effective_date AND MAX_AGE = :max_age AND MAX_DURATION > :max_duration)) ";
            else
                if (locator)
                    pagingWhere = ".TTBBT WHERE (COMPANY_CODE = :company_code) AND ((TABLE_SUBSET > :table_subset) OR (TABLE_SUBSET = :table_subset AND EFFECTIVE_DATE > :effective_date) OR (TABLE_SUBSET = :table_subset AND EFFECTIVE_DATE = :effective_date AND MAX_AGE > :max_age) OR (TABLE_SUBSET = :table_subset AND EFFECTIVE_DATE = :effective_date AND MAX_AGE = :max_age AND MAX_DURATION > :max_duration) OR (TABLE_SUBSET = :table_subset AND EFFECTIVE_DATE = :effective_date AND MAX_AGE = :max_age AND MAX_DURATION = :max_duration)) ";
                else
                    pagingWhere = ".TTBBT WHERE (COMPANY_CODE = :company_code) AND ((TABLE_SUBSET > :table_subset) OR (TABLE_SUBSET = :table_subset AND EFFECTIVE_DATE > :effective_date) OR (TABLE_SUBSET = :table_subset AND EFFECTIVE_DATE = :effective_date AND MAX_AGE > :max_age) OR (TABLE_SUBSET = :table_subset AND EFFECTIVE_DATE = :effective_date AND MAX_AGE = :max_age AND MAX_DURATION > :max_duration)) ";
            order = " ORDER BY COMPANY_CODE, TABLE_SUBSET, EFFECTIVE_DATE, MAX_AGE, MAX_DURATION";
        }
        else {
            if (isSubsetMode)
                pagingWhere = ".TTBBT WHERE (COMPANY_CODE = :company_code) AND (TABLE_SUBSET = :table_subset) AND ((EFFECTIVE_DATE < :effective_date) OR (EFFECTIVE_DATE = :effective_date AND MAX_AGE < :max_age) OR (EFFECTIVE_DATE = :effective_date AND MAX_AGE = :max_age AND MAX_DURATION < :max_duration)) ";
            else
                pagingWhere = ".TTBBT WHERE (COMPANY_CODE = :company_code) AND ((TABLE_SUBSET < :table_subset) OR (TABLE_SUBSET = :table_subset AND EFFECTIVE_DATE < :effective_date) OR (TABLE_SUBSET = :table_subset AND EFFECTIVE_DATE = :effective_date AND MAX_AGE < :max_age) OR (TABLE_SUBSET = :table_subset AND EFFECTIVE_DATE = :effective_date AND MAX_AGE = :max_age AND MAX_DURATION < :max_duration)) ";
            order = " ORDER BY COMPANY_CODE DESC, TABLE_SUBSET DESC, EFFECTIVE_DATE DESC, MAX_AGE DESC, MAX_DURATION DESC";
        }
        return pagingSql + schemaName + pagingWhere + order;
    }
    
    public String prepareInsertStmt(String schemaName) {
        StringBuffer sb = new StringBuffer();
        sb.append("INSERT INTO ").append(schemaName);
        sb.append(".TTBBT ( ");
        sb.append("COMPANY_CODE, TABLE_SUBSET, EFFECTIVE_DATE, MAX_AGE, MAX_DURATION, BAS_FLATCHG_PR_PAY, BAS_FLATCHG_NONPRM )");
        sb.append(" VALUES (?, ?, ?, ?, ?, ?, ? )");
        return sb.toString();
    }
    
    public String prepareUpdateStmt(String schemaName)
    {
        StringBuffer sb = new StringBuffer();
        sb.append("UPDATE ").append(schemaName);
        sb.append(".TTBBT ");
        sb.append(" SET COMPANY_CODE = ?, TABLE_SUBSET = ?, EFFECTIVE_DATE = ?, MAX_AGE = ?, MAX_DURATION = ?, BAS_FLATCHG_PR_PAY = ?, BAS_FLATCHG_NONPRM = ?");
        sb.append(" WHERE COMPANY_CODE = ? AND TABLE_SUBSET = ? AND EFFECTIVE_DATE = ? AND MAX_AGE = ? AND MAX_DURATION = ?");
        return sb.toString();
    }
    
    public String prepareDeleteStmt(String schemaName)
    {
        StringBuffer sb = new StringBuffer();
        sb.append("DELETE FROM ").append(schemaName);
        sb.append(".TTBBT ");
        sb.append(" WHERE COMPANY_CODE = ? AND TABLE_SUBSET = ? AND EFFECTIVE_DATE = ? AND MAX_AGE = ? AND MAX_DURATION = ?");
        return sb.toString();
    }
}
