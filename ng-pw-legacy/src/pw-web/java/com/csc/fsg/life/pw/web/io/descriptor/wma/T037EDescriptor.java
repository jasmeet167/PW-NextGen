package com.csc.fsg.life.pw.web.io.descriptor.wma;

import com.csc.fsg.life.pw.web.io.*;

public class T037EDescriptor
    extends TableDescriptor
{
    private static final String pagingSql = "SELECT COMPANY_CODE, TABLE_SUBSET, EFFECTIVE_DATE, MAX_INCR_ISSUE_AGE, MAX_TOT_POL_UNITS, MAX_INCREASE_UNITS, CHG_PER_UNIT_INCR, FLAT_DOLLAR_AMT FROM ";
    
    public void initialize()
    {
        setRowClass(T037ERow.class);
        setTableName("T037E");
        setTableId("037");
        super.initialize();
    }
    
    public void initializeColumnDescriptors()
    {
        super.initializeColumnDescriptors();
        addColumnDescriptor(new ColumnDescriptor(this,"getCompanyCode","setCompanyCode","COMPANY_CODE,1,1,3,0,true|,0,CHAR,1,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getTableSubset","setTableSubset","TABLE_SUBSET,1,2,16,0,true|,0,CHAR,1,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getEffectiveDate","setEffectiveDate","EFFECTIVE_DATE,91,3,10,0,true|,0,DATE,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getMaxIncrIssueAge","setMaxIncrIssueAge","MAX_INCR_ISSUE_AGE,3,4,3,0,true|,0,INTEGER,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getMaxTotPolUnits","setMaxTotPolUnits","MAX_TOT_POL_UNITS,3,5,11,5,true|,0,DOUBLE,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getMaxIncreaseUnits","setMaxIncreaseUnits","MAX_INCREASE_UNITS,3,6,11,5,true|,0,DOUBLE,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getChgPerUnitIncr","setChgPerUnitIncr","CHG_PER_UNIT_INCR,3,7,8,5,false|,0,DOUBLE,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getFlatDollarAmt","setFlatDollarAmt","FLAT_DOLLAR_AMT,3,8,7,2,false|,0,DOUBLE,0,null,null,null,null,null"));
    }
    
    public String getPagingSQL(String schemaName,boolean isSubsetMode,boolean isNext, boolean locator)
    {
        String pagingWhere = null;
        String order = null;
        if (isNext) {
            if (isSubsetMode)
                if (locator)
                    pagingWhere = ".T037E WHERE (COMPANY_CODE = :company_code) AND (TABLE_SUBSET = :table_subset) AND ((EFFECTIVE_DATE > :effective_date) OR (EFFECTIVE_DATE = :effective_date AND MAX_INCR_ISSUE_AGE > :max_incr_issue_age) OR (EFFECTIVE_DATE = :effective_date AND MAX_INCR_ISSUE_AGE = :max_incr_issue_age AND MAX_TOT_POL_UNITS > :max_tot_pol_units) OR (EFFECTIVE_DATE = :effective_date AND MAX_INCR_ISSUE_AGE = :max_incr_issue_age AND MAX_TOT_POL_UNITS = :max_tot_pol_units AND MAX_INCREASE_UNITS > :max_increase_units) OR (EFFECTIVE_DATE = :effective_date AND MAX_INCR_ISSUE_AGE = :max_incr_issue_age AND MAX_TOT_POL_UNITS = :max_tot_pol_units AND MAX_INCREASE_UNITS = :max_increase_units)) ";
                else 
                    pagingWhere = ".T037E WHERE (COMPANY_CODE = :company_code) AND (TABLE_SUBSET = :table_subset) AND ((EFFECTIVE_DATE > :effective_date) OR (EFFECTIVE_DATE = :effective_date AND MAX_INCR_ISSUE_AGE > :max_incr_issue_age) OR (EFFECTIVE_DATE = :effective_date AND MAX_INCR_ISSUE_AGE = :max_incr_issue_age AND MAX_TOT_POL_UNITS > :max_tot_pol_units) OR (EFFECTIVE_DATE = :effective_date AND MAX_INCR_ISSUE_AGE = :max_incr_issue_age AND MAX_TOT_POL_UNITS = :max_tot_pol_units AND MAX_INCREASE_UNITS > :max_increase_units)) ";
            else
                if (locator)
                    pagingWhere = ".T037E WHERE (COMPANY_CODE = :company_code) AND ((TABLE_SUBSET > :table_subset) OR (TABLE_SUBSET = :table_subset AND EFFECTIVE_DATE > :effective_date) OR (TABLE_SUBSET = :table_subset AND EFFECTIVE_DATE = :effective_date AND MAX_INCR_ISSUE_AGE > :max_incr_issue_age) OR (TABLE_SUBSET = :table_subset AND EFFECTIVE_DATE = :effective_date AND MAX_INCR_ISSUE_AGE = :max_incr_issue_age AND MAX_TOT_POL_UNITS > :max_tot_pol_units) OR (TABLE_SUBSET = :table_subset AND EFFECTIVE_DATE = :effective_date AND MAX_INCR_ISSUE_AGE = :max_incr_issue_age AND MAX_TOT_POL_UNITS = :max_tot_pol_units AND MAX_INCREASE_UNITS > :max_increase_units) OR (TABLE_SUBSET = :table_subset AND EFFECTIVE_DATE = :effective_date AND MAX_INCR_ISSUE_AGE = :max_incr_issue_age AND MAX_TOT_POL_UNITS = :max_tot_pol_units AND MAX_INCREASE_UNITS = :max_increase_units)) ";
                else
                    pagingWhere = ".T037E WHERE (COMPANY_CODE = :company_code) AND ((TABLE_SUBSET > :table_subset) OR (TABLE_SUBSET = :table_subset AND EFFECTIVE_DATE > :effective_date) OR (TABLE_SUBSET = :table_subset AND EFFECTIVE_DATE = :effective_date AND MAX_INCR_ISSUE_AGE > :max_incr_issue_age) OR (TABLE_SUBSET = :table_subset AND EFFECTIVE_DATE = :effective_date AND MAX_INCR_ISSUE_AGE = :max_incr_issue_age AND MAX_TOT_POL_UNITS > :max_tot_pol_units) OR (TABLE_SUBSET = :table_subset AND EFFECTIVE_DATE = :effective_date AND MAX_INCR_ISSUE_AGE = :max_incr_issue_age AND MAX_TOT_POL_UNITS = :max_tot_pol_units AND MAX_INCREASE_UNITS > :max_increase_units)) ";
            order = " ORDER BY COMPANY_CODE, TABLE_SUBSET, EFFECTIVE_DATE, MAX_INCR_ISSUE_AGE, MAX_TOT_POL_UNITS, MAX_INCREASE_UNITS";
        }
        else {
            if (isSubsetMode)
                pagingWhere = ".T037E WHERE (COMPANY_CODE = :company_code) AND (TABLE_SUBSET = :table_subset) AND ((EFFECTIVE_DATE < :effective_date) OR (EFFECTIVE_DATE = :effective_date AND MAX_INCR_ISSUE_AGE < :max_incr_issue_age) OR (EFFECTIVE_DATE = :effective_date AND MAX_INCR_ISSUE_AGE = :max_incr_issue_age AND MAX_TOT_POL_UNITS < :max_tot_pol_units) OR (EFFECTIVE_DATE = :effective_date AND MAX_INCR_ISSUE_AGE = :max_incr_issue_age AND MAX_TOT_POL_UNITS = :max_tot_pol_units AND MAX_INCREASE_UNITS < :max_increase_units)) ";
            else
                pagingWhere = ".T037E WHERE (COMPANY_CODE = :company_code) AND ((TABLE_SUBSET < :table_subset) OR (TABLE_SUBSET = :table_subset AND EFFECTIVE_DATE < :effective_date) OR (TABLE_SUBSET = :table_subset AND EFFECTIVE_DATE = :effective_date AND MAX_INCR_ISSUE_AGE < :max_incr_issue_age) OR (TABLE_SUBSET = :table_subset AND EFFECTIVE_DATE = :effective_date AND MAX_INCR_ISSUE_AGE = :max_incr_issue_age AND MAX_TOT_POL_UNITS < :max_tot_pol_units) OR (TABLE_SUBSET = :table_subset AND EFFECTIVE_DATE = :effective_date AND MAX_INCR_ISSUE_AGE = :max_incr_issue_age AND MAX_TOT_POL_UNITS = :max_tot_pol_units AND MAX_INCREASE_UNITS < :max_increase_units)) ";
            order = " ORDER BY COMPANY_CODE DESC, TABLE_SUBSET DESC, EFFECTIVE_DATE DESC, MAX_INCR_ISSUE_AGE DESC, MAX_TOT_POL_UNITS DESC, MAX_INCREASE_UNITS DESC";
        }
        return pagingSql + schemaName + pagingWhere + order;
    }
    
    public String prepareInsertStmt(String schemaName) {
        StringBuffer sb = new StringBuffer();
        sb.append("INSERT INTO ").append(schemaName);
        sb.append(".T037E ( ");
        sb.append("COMPANY_CODE, TABLE_SUBSET, EFFECTIVE_DATE, MAX_INCR_ISSUE_AGE, MAX_TOT_POL_UNITS, MAX_INCREASE_UNITS, CHG_PER_UNIT_INCR, FLAT_DOLLAR_AMT )");
        sb.append(" VALUES (?, ?, ?, ?, ?, ?, ?, ? )");
        return sb.toString();
    }
    
    public String prepareUpdateStmt(String schemaName)
    {
        StringBuffer sb = new StringBuffer();
        sb.append("UPDATE ").append(schemaName);
        sb.append(".T037E ");
        sb.append(" SET COMPANY_CODE = ?, TABLE_SUBSET = ?, EFFECTIVE_DATE = ?, MAX_INCR_ISSUE_AGE = ?, MAX_TOT_POL_UNITS = ?, MAX_INCREASE_UNITS = ?, CHG_PER_UNIT_INCR = ?, FLAT_DOLLAR_AMT = ?");
        sb.append(" WHERE COMPANY_CODE = ? AND TABLE_SUBSET = ? AND EFFECTIVE_DATE = ? AND MAX_INCR_ISSUE_AGE = ? AND MAX_TOT_POL_UNITS = ? AND MAX_INCREASE_UNITS = ?");
        return sb.toString();
    }
    
    public String prepareDeleteStmt(String schemaName)
    {
        StringBuffer sb = new StringBuffer();
        sb.append("DELETE FROM ").append(schemaName);
        sb.append(".T037E ");
        sb.append(" WHERE COMPANY_CODE = ? AND TABLE_SUBSET = ? AND EFFECTIVE_DATE = ? AND MAX_INCR_ISSUE_AGE = ? AND MAX_TOT_POL_UNITS = ? AND MAX_INCREASE_UNITS = ?");
        return sb.toString();
    }
}
