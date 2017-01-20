package com.csc.fsg.life.pw.io.descriptor.wma;

import com.csc.fsg.life.pw.io.*;

public class T054EDescriptor
    extends TableDescriptor
{
    private static final String pagingSql = "SELECT COMPANY_CODE, TABLE_SUBSET, EFFECTIVE_DATE, MAX_ISSUE_AGE, MAX_POL_DUR, PREM_CLASS_CODE, MAX_TOTAL_UNITS, CHG_PER_UNIT_POL, FLAT_DOLLAR_AMT FROM ";
    
    public void initialize()
    {
        setRowClass(T054ERow.class);
        setTableName("T054E");
        setTableId("054");
        super.initialize();
    }
    
    public void initializeColumnDescriptors()
    {
        super.initializeColumnDescriptors();
        addColumnDescriptor(new ColumnDescriptor(this,"getCompanyCode","setCompanyCode","COMPANY_CODE,1,1,3,0,true|,0,CHAR,1,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getTableSubset","setTableSubset","TABLE_SUBSET,1,2,16,0,true|,0,CHAR,1,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getEffectiveDate","setEffectiveDate","EFFECTIVE_DATE,91,3,10,0,true|,0,DATE,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getMaxIssueAge","setMaxIssueAge","MAX_ISSUE_AGE,3,4,3,0,true|,0,INTEGER,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getMaxPolDur","setMaxPolDur","MAX_POL_DUR,3,5,3,0,true|,0,INTEGER,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getPremClassCode","setPremClassCode","PREM_CLASS_CODE,1,6,1,0,true|,0,CHAR,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getMaxTotalUnits","setMaxTotalUnits","MAX_TOTAL_UNITS,3,7,11,5,true|,0,DOUBLE,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getChgPerUnitPol","setChgPerUnitPol","CHG_PER_UNIT_POL,3,8,8,5,false|,0,DOUBLE,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getFlatDollarAmt","setFlatDollarAmt","FLAT_DOLLAR_AMT,3,9,7,2,false|,0,DOUBLE,0,null,null,null,null,null"));
    }
    
    public String getPagingSQL(String schemaName,boolean isSubsetMode,boolean isNext, boolean locator)
    {
        String pagingWhere = null;
        String order = null;
        if (isNext) {
            if (isSubsetMode)
                if (locator)
                    pagingWhere = ".T054E WHERE (COMPANY_CODE = :company_code) AND (TABLE_SUBSET = :table_subset) AND ((EFFECTIVE_DATE > :effective_date) OR (EFFECTIVE_DATE = :effective_date AND MAX_ISSUE_AGE > :max_issue_age) OR (EFFECTIVE_DATE = :effective_date AND MAX_ISSUE_AGE = :max_issue_age AND MAX_POL_DUR > :max_pol_dur) OR (EFFECTIVE_DATE = :effective_date AND MAX_ISSUE_AGE = :max_issue_age AND MAX_POL_DUR = :max_pol_dur AND PREM_CLASS_CODE > :prem_class_code) OR (EFFECTIVE_DATE = :effective_date AND MAX_ISSUE_AGE = :max_issue_age AND MAX_POL_DUR = :max_pol_dur AND PREM_CLASS_CODE = :prem_class_code AND MAX_TOTAL_UNITS > :max_total_units) OR (EFFECTIVE_DATE = :effective_date AND MAX_ISSUE_AGE = :max_issue_age AND MAX_POL_DUR = :max_pol_dur AND PREM_CLASS_CODE = :prem_class_code AND MAX_TOTAL_UNITS = :max_total_units)) ";
                else 
                    pagingWhere = ".T054E WHERE (COMPANY_CODE = :company_code) AND (TABLE_SUBSET = :table_subset) AND ((EFFECTIVE_DATE > :effective_date) OR (EFFECTIVE_DATE = :effective_date AND MAX_ISSUE_AGE > :max_issue_age) OR (EFFECTIVE_DATE = :effective_date AND MAX_ISSUE_AGE = :max_issue_age AND MAX_POL_DUR > :max_pol_dur) OR (EFFECTIVE_DATE = :effective_date AND MAX_ISSUE_AGE = :max_issue_age AND MAX_POL_DUR = :max_pol_dur AND PREM_CLASS_CODE > :prem_class_code) OR (EFFECTIVE_DATE = :effective_date AND MAX_ISSUE_AGE = :max_issue_age AND MAX_POL_DUR = :max_pol_dur AND PREM_CLASS_CODE = :prem_class_code AND MAX_TOTAL_UNITS > :max_total_units)) ";
            else
                if (locator)
                    pagingWhere = ".T054E WHERE (COMPANY_CODE = :company_code) AND ((TABLE_SUBSET > :table_subset) OR (TABLE_SUBSET = :table_subset AND EFFECTIVE_DATE > :effective_date) OR (TABLE_SUBSET = :table_subset AND EFFECTIVE_DATE = :effective_date AND MAX_ISSUE_AGE > :max_issue_age) OR (TABLE_SUBSET = :table_subset AND EFFECTIVE_DATE = :effective_date AND MAX_ISSUE_AGE = :max_issue_age AND MAX_POL_DUR > :max_pol_dur) OR (TABLE_SUBSET = :table_subset AND EFFECTIVE_DATE = :effective_date AND MAX_ISSUE_AGE = :max_issue_age AND MAX_POL_DUR = :max_pol_dur AND PREM_CLASS_CODE > :prem_class_code) OR (TABLE_SUBSET = :table_subset AND EFFECTIVE_DATE = :effective_date AND MAX_ISSUE_AGE = :max_issue_age AND MAX_POL_DUR = :max_pol_dur AND PREM_CLASS_CODE = :prem_class_code AND MAX_TOTAL_UNITS > :max_total_units) OR (TABLE_SUBSET = :table_subset AND EFFECTIVE_DATE = :effective_date AND MAX_ISSUE_AGE = :max_issue_age AND MAX_POL_DUR = :max_pol_dur AND PREM_CLASS_CODE = :prem_class_code AND MAX_TOTAL_UNITS = :max_total_units)) ";
                else
                    pagingWhere = ".T054E WHERE (COMPANY_CODE = :company_code) AND ((TABLE_SUBSET > :table_subset) OR (TABLE_SUBSET = :table_subset AND EFFECTIVE_DATE > :effective_date) OR (TABLE_SUBSET = :table_subset AND EFFECTIVE_DATE = :effective_date AND MAX_ISSUE_AGE > :max_issue_age) OR (TABLE_SUBSET = :table_subset AND EFFECTIVE_DATE = :effective_date AND MAX_ISSUE_AGE = :max_issue_age AND MAX_POL_DUR > :max_pol_dur) OR (TABLE_SUBSET = :table_subset AND EFFECTIVE_DATE = :effective_date AND MAX_ISSUE_AGE = :max_issue_age AND MAX_POL_DUR = :max_pol_dur AND PREM_CLASS_CODE > :prem_class_code) OR (TABLE_SUBSET = :table_subset AND EFFECTIVE_DATE = :effective_date AND MAX_ISSUE_AGE = :max_issue_age AND MAX_POL_DUR = :max_pol_dur AND PREM_CLASS_CODE = :prem_class_code AND MAX_TOTAL_UNITS > :max_total_units)) ";
            order = " ORDER BY COMPANY_CODE, TABLE_SUBSET, EFFECTIVE_DATE, MAX_ISSUE_AGE, MAX_POL_DUR, PREM_CLASS_CODE, MAX_TOTAL_UNITS";
        }
        else {
            if (isSubsetMode)
                pagingWhere = ".T054E WHERE (COMPANY_CODE = :company_code) AND (TABLE_SUBSET = :table_subset) AND ((EFFECTIVE_DATE < :effective_date) OR (EFFECTIVE_DATE = :effective_date AND MAX_ISSUE_AGE < :max_issue_age) OR (EFFECTIVE_DATE = :effective_date AND MAX_ISSUE_AGE = :max_issue_age AND MAX_POL_DUR < :max_pol_dur) OR (EFFECTIVE_DATE = :effective_date AND MAX_ISSUE_AGE = :max_issue_age AND MAX_POL_DUR = :max_pol_dur AND PREM_CLASS_CODE < :prem_class_code) OR (EFFECTIVE_DATE = :effective_date AND MAX_ISSUE_AGE = :max_issue_age AND MAX_POL_DUR = :max_pol_dur AND PREM_CLASS_CODE = :prem_class_code AND MAX_TOTAL_UNITS < :max_total_units)) ";
            else
                pagingWhere = ".T054E WHERE (COMPANY_CODE = :company_code) AND ((TABLE_SUBSET < :table_subset) OR (TABLE_SUBSET = :table_subset AND EFFECTIVE_DATE < :effective_date) OR (TABLE_SUBSET = :table_subset AND EFFECTIVE_DATE = :effective_date AND MAX_ISSUE_AGE < :max_issue_age) OR (TABLE_SUBSET = :table_subset AND EFFECTIVE_DATE = :effective_date AND MAX_ISSUE_AGE = :max_issue_age AND MAX_POL_DUR < :max_pol_dur) OR (TABLE_SUBSET = :table_subset AND EFFECTIVE_DATE = :effective_date AND MAX_ISSUE_AGE = :max_issue_age AND MAX_POL_DUR = :max_pol_dur AND PREM_CLASS_CODE < :prem_class_code) OR (TABLE_SUBSET = :table_subset AND EFFECTIVE_DATE = :effective_date AND MAX_ISSUE_AGE = :max_issue_age AND MAX_POL_DUR = :max_pol_dur AND PREM_CLASS_CODE = :prem_class_code AND MAX_TOTAL_UNITS < :max_total_units)) ";
            order = " ORDER BY COMPANY_CODE DESC, TABLE_SUBSET DESC, EFFECTIVE_DATE DESC, MAX_ISSUE_AGE DESC, MAX_POL_DUR DESC, PREM_CLASS_CODE DESC, MAX_TOTAL_UNITS DESC";
        }
        return pagingSql + schemaName + pagingWhere + order;
    }
    
    public String prepareInsertStmt(String schemaName) {
        StringBuffer sb = new StringBuffer();
        sb.append("INSERT INTO ").append(schemaName);
        sb.append(".T054E ( ");
        sb.append("COMPANY_CODE, TABLE_SUBSET, EFFECTIVE_DATE, MAX_ISSUE_AGE, MAX_POL_DUR, PREM_CLASS_CODE, MAX_TOTAL_UNITS, CHG_PER_UNIT_POL, FLAT_DOLLAR_AMT )");
        sb.append(" VALUES (?, ?, ?, ?, ?, ?, ?, ?, ? )");
        return sb.toString();
    }
    
    public String prepareUpdateStmt(String schemaName)
    {
        StringBuffer sb = new StringBuffer();
        sb.append("UPDATE ").append(schemaName);
        sb.append(".T054E ");
        sb.append(" SET COMPANY_CODE = ?, TABLE_SUBSET = ?, EFFECTIVE_DATE = ?, MAX_ISSUE_AGE = ?, MAX_POL_DUR = ?, PREM_CLASS_CODE = ?, MAX_TOTAL_UNITS = ?, CHG_PER_UNIT_POL = ?, FLAT_DOLLAR_AMT = ?");
        sb.append(" WHERE COMPANY_CODE = ? AND TABLE_SUBSET = ? AND EFFECTIVE_DATE = ? AND MAX_ISSUE_AGE = ? AND MAX_POL_DUR = ? AND PREM_CLASS_CODE = ? AND MAX_TOTAL_UNITS = ?");
        return sb.toString();
    }
    
    public String prepareDeleteStmt(String schemaName)
    {
        StringBuffer sb = new StringBuffer();
        sb.append("DELETE FROM ").append(schemaName);
        sb.append(".T054E ");
        sb.append(" WHERE COMPANY_CODE = ? AND TABLE_SUBSET = ? AND EFFECTIVE_DATE = ? AND MAX_ISSUE_AGE = ? AND MAX_POL_DUR = ? AND PREM_CLASS_CODE = ? AND MAX_TOTAL_UNITS = ?");
        return sb.toString();
    }
}
