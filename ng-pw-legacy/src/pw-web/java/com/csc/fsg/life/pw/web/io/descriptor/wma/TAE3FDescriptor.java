package com.csc.fsg.life.pw.web.io.descriptor.wma;

import com.csc.fsg.life.pw.web.io.*;

public class TAE3FDescriptor
    extends TableDescriptor
{
    private static final String pagingSql = "SELECT COMPANY_CODE, TABLE_SUBSET, STAT_COMPANY, EFFECTIVE_DATE, RETIRE_AGE, MALE_ANN_FACTOR, FMALE_ANN_FACTOR FROM ";
    
    public void initialize()
    {
        setRowClass(TAE3FRow.class);
        setTableName("TAE3F");
        setTableId("AE3");
        super.initialize();
    }
    
    public void initializeColumnDescriptors()
    {
        super.initializeColumnDescriptors();
        addColumnDescriptor(new ColumnDescriptor(this,"getCompanyCode","setCompanyCode","COMPANY_CODE,1,1,3,0,true|,0,CHAR,1,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getTableSubset","setTableSubset","TABLE_SUBSET,1,2,16,0,true|,0,CHAR,1,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getStatCompany","setStatCompany","STAT_COMPANY,1,3,3,0,true|,0,CHAR,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getEffectiveDate","setEffectiveDate","EFFECTIVE_DATE,91,4,10,0,true|,0,DATE,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getRetireAge","setRetireAge","RETIRE_AGE,3,5,3,0,true|,0,INTEGER,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getMaleAnnFactor","setMaleAnnFactor","MALE_ANN_FACTOR,3,6,5,3,false|,0,DOUBLE,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getFmaleAnnFactor","setFmaleAnnFactor","FMALE_ANN_FACTOR,3,7,5,3,false|,0,DOUBLE,0,null,null,null,null,null"));
    }
    
    public String getPagingSQL(String schemaName,boolean isSubsetMode,boolean isNext, boolean locator)
    {
        String pagingWhere = null;
        String order = null;
        if (isNext) {
            if (isSubsetMode)
                if (locator)
                    pagingWhere = ".TAE3F WHERE (COMPANY_CODE = :company_code) AND (TABLE_SUBSET = :table_subset) AND ((STAT_COMPANY > :stat_company) OR (STAT_COMPANY = :stat_company AND EFFECTIVE_DATE > :effective_date) OR (STAT_COMPANY = :stat_company AND EFFECTIVE_DATE = :effective_date AND RETIRE_AGE > :retire_age) OR (STAT_COMPANY = :stat_company AND EFFECTIVE_DATE = :effective_date AND RETIRE_AGE = :retire_age)) ";
                else 
                    pagingWhere = ".TAE3F WHERE (COMPANY_CODE = :company_code) AND (TABLE_SUBSET = :table_subset) AND ((STAT_COMPANY > :stat_company) OR (STAT_COMPANY = :stat_company AND EFFECTIVE_DATE > :effective_date) OR (STAT_COMPANY = :stat_company AND EFFECTIVE_DATE = :effective_date AND RETIRE_AGE > :retire_age)) ";
            else
                if (locator)
                    pagingWhere = ".TAE3F WHERE (COMPANY_CODE = :company_code) AND ((TABLE_SUBSET > :table_subset) OR (TABLE_SUBSET = :table_subset AND STAT_COMPANY > :stat_company) OR (TABLE_SUBSET = :table_subset AND STAT_COMPANY = :stat_company AND EFFECTIVE_DATE > :effective_date) OR (TABLE_SUBSET = :table_subset AND STAT_COMPANY = :stat_company AND EFFECTIVE_DATE = :effective_date AND RETIRE_AGE > :retire_age) OR (TABLE_SUBSET = :table_subset AND STAT_COMPANY = :stat_company AND EFFECTIVE_DATE = :effective_date AND RETIRE_AGE = :retire_age)) ";
                else
                    pagingWhere = ".TAE3F WHERE (COMPANY_CODE = :company_code) AND ((TABLE_SUBSET > :table_subset) OR (TABLE_SUBSET = :table_subset AND STAT_COMPANY > :stat_company) OR (TABLE_SUBSET = :table_subset AND STAT_COMPANY = :stat_company AND EFFECTIVE_DATE > :effective_date) OR (TABLE_SUBSET = :table_subset AND STAT_COMPANY = :stat_company AND EFFECTIVE_DATE = :effective_date AND RETIRE_AGE > :retire_age)) ";
            order = " ORDER BY COMPANY_CODE, TABLE_SUBSET, STAT_COMPANY, EFFECTIVE_DATE, RETIRE_AGE";
        }
        else {
            if (isSubsetMode)
                pagingWhere = ".TAE3F WHERE (COMPANY_CODE = :company_code) AND (TABLE_SUBSET = :table_subset) AND ((STAT_COMPANY < :stat_company) OR (STAT_COMPANY = :stat_company AND EFFECTIVE_DATE < :effective_date) OR (STAT_COMPANY = :stat_company AND EFFECTIVE_DATE = :effective_date AND RETIRE_AGE < :retire_age)) ";
            else
                pagingWhere = ".TAE3F WHERE (COMPANY_CODE = :company_code) AND ((TABLE_SUBSET < :table_subset) OR (TABLE_SUBSET = :table_subset AND STAT_COMPANY < :stat_company) OR (TABLE_SUBSET = :table_subset AND STAT_COMPANY = :stat_company AND EFFECTIVE_DATE < :effective_date) OR (TABLE_SUBSET = :table_subset AND STAT_COMPANY = :stat_company AND EFFECTIVE_DATE = :effective_date AND RETIRE_AGE < :retire_age)) ";
            order = " ORDER BY COMPANY_CODE DESC, TABLE_SUBSET DESC, STAT_COMPANY DESC, EFFECTIVE_DATE DESC, RETIRE_AGE DESC";
        }
        return pagingSql + schemaName + pagingWhere + order;
    }
    
    public String prepareInsertStmt(String schemaName) {
        StringBuffer sb = new StringBuffer();
        sb.append("INSERT INTO ").append(schemaName);
        sb.append(".TAE3F ( ");
        sb.append("COMPANY_CODE, TABLE_SUBSET, STAT_COMPANY, EFFECTIVE_DATE, RETIRE_AGE, MALE_ANN_FACTOR, FMALE_ANN_FACTOR )");
        sb.append(" VALUES (?, ?, ?, ?, ?, ?, ? )");
        return sb.toString();
    }
    
    public String prepareUpdateStmt(String schemaName)
    {
        StringBuffer sb = new StringBuffer();
        sb.append("UPDATE ").append(schemaName);
        sb.append(".TAE3F ");
        sb.append(" SET COMPANY_CODE = ?, TABLE_SUBSET = ?, STAT_COMPANY = ?, EFFECTIVE_DATE = ?, RETIRE_AGE = ?, MALE_ANN_FACTOR = ?, FMALE_ANN_FACTOR = ?");
        sb.append(" WHERE COMPANY_CODE = ? AND TABLE_SUBSET = ? AND STAT_COMPANY = ? AND EFFECTIVE_DATE = ? AND RETIRE_AGE = ?");
        return sb.toString();
    }
    
    public String prepareDeleteStmt(String schemaName)
    {
        StringBuffer sb = new StringBuffer();
        sb.append("DELETE FROM ").append(schemaName);
        sb.append(".TAE3F ");
        sb.append(" WHERE COMPANY_CODE = ? AND TABLE_SUBSET = ? AND STAT_COMPANY = ? AND EFFECTIVE_DATE = ? AND RETIRE_AGE = ?");
        return sb.toString();
    }
}
