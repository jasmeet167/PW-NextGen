package com.csc.fsg.life.pw.io.descriptor.wma;

import com.csc.fsg.life.pw.io.*;

public class T064EDescriptor
    extends TableDescriptor
{
    private static final String pagingSql = "SELECT COMPANY_CODE, TABLE_SUBSET, EFFECTIVE_DATE, MIN_ISSUE_AGE, MAX_ISSUE_AGE, MAX_NO_INCREASE, DUR_COLA_INCREASE, MAX_INCREASE_AMT, MTHD_OF_INCREASE, TYPE_OF_INCREASE, INCREASE_PCT, INCREASE_FLAT_AMT, DECREASE_TERM_CD, TYP_CHG_TERM_CD FROM ";
    
    public void initialize()
    {
        setRowClass(T064ERow.class);
        setTableName("T064E");
        setTableId("064");
        super.initialize();
    }
    
    public void initializeColumnDescriptors()
    {
        super.initializeColumnDescriptors();
        addColumnDescriptor(new ColumnDescriptor(this,"getCompanyCode","setCompanyCode","COMPANY_CODE,1,1,3,0,true|,0,CHAR,1,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getTableSubset","setTableSubset","TABLE_SUBSET,1,2,16,0,true|,0,CHAR,1,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getEffectiveDate","setEffectiveDate","EFFECTIVE_DATE,91,3,10,0,true|,0,DATE,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getMinIssueAge","setMinIssueAge","MIN_ISSUE_AGE,3,4,3,0,true|,0,INTEGER,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getMaxIssueAge","setMaxIssueAge","MAX_ISSUE_AGE,3,5,3,0,true|,0,INTEGER,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getMaxNoIncrease","setMaxNoIncrease","MAX_NO_INCREASE,3,6,3,0,false|,0,INTEGER,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getDurColaIncrease","setDurColaIncrease","DUR_COLA_INCREASE,3,7,1,0,false|,0,INTEGER,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getMaxIncreaseAmt","setMaxIncreaseAmt","MAX_INCREASE_AMT,3,8,9,2,false|,0,DOUBLE,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getMthdOfIncrease","setMthdOfIncrease","MTHD_OF_INCREASE,1,9,8,0,false|,0,CHAR,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getTypeOfIncrease","setTypeOfIncrease","TYPE_OF_INCREASE,1,10,1,0,false|,0,CHAR,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getIncreasePct","setIncreasePct","INCREASE_PCT,3,11,5,2,false|,0,DOUBLE,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getIncreaseFlatAmt","setIncreaseFlatAmt","INCREASE_FLAT_AMT,3,12,9,2,false|,0,DOUBLE,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getDecreaseTermCd","setDecreaseTermCd","DECREASE_TERM_CD,1,13,1,0,false|,0,CHAR,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getTypChgTermCd","setTypChgTermCd","TYP_CHG_TERM_CD,1,14,1,0,false|,0,CHAR,0,null,null,null,null,null"));
    }
    
    public String getPagingSQL(String schemaName,boolean isSubsetMode,boolean isNext, boolean locator)
    {
        String pagingWhere = null;
        String order = null;
        if (isNext) {
            if (isSubsetMode)
                if (locator)
                    pagingWhere = ".T064E WHERE (COMPANY_CODE = :company_code) AND (TABLE_SUBSET = :table_subset) AND ((EFFECTIVE_DATE > :effective_date) OR (EFFECTIVE_DATE = :effective_date AND MIN_ISSUE_AGE > :min_issue_age) OR (EFFECTIVE_DATE = :effective_date AND MIN_ISSUE_AGE = :min_issue_age AND MAX_ISSUE_AGE > :max_issue_age) OR (EFFECTIVE_DATE = :effective_date AND MIN_ISSUE_AGE = :min_issue_age AND MAX_ISSUE_AGE = :max_issue_age)) ";
                else 
                    pagingWhere = ".T064E WHERE (COMPANY_CODE = :company_code) AND (TABLE_SUBSET = :table_subset) AND ((EFFECTIVE_DATE > :effective_date) OR (EFFECTIVE_DATE = :effective_date AND MIN_ISSUE_AGE > :min_issue_age) OR (EFFECTIVE_DATE = :effective_date AND MIN_ISSUE_AGE = :min_issue_age AND MAX_ISSUE_AGE > :max_issue_age)) ";
            else
                if (locator)
                    pagingWhere = ".T064E WHERE (COMPANY_CODE = :company_code) AND ((TABLE_SUBSET > :table_subset) OR (TABLE_SUBSET = :table_subset AND EFFECTIVE_DATE > :effective_date) OR (TABLE_SUBSET = :table_subset AND EFFECTIVE_DATE = :effective_date AND MIN_ISSUE_AGE > :min_issue_age) OR (TABLE_SUBSET = :table_subset AND EFFECTIVE_DATE = :effective_date AND MIN_ISSUE_AGE = :min_issue_age AND MAX_ISSUE_AGE > :max_issue_age) OR (TABLE_SUBSET = :table_subset AND EFFECTIVE_DATE = :effective_date AND MIN_ISSUE_AGE = :min_issue_age AND MAX_ISSUE_AGE = :max_issue_age)) ";
                else
                    pagingWhere = ".T064E WHERE (COMPANY_CODE = :company_code) AND ((TABLE_SUBSET > :table_subset) OR (TABLE_SUBSET = :table_subset AND EFFECTIVE_DATE > :effective_date) OR (TABLE_SUBSET = :table_subset AND EFFECTIVE_DATE = :effective_date AND MIN_ISSUE_AGE > :min_issue_age) OR (TABLE_SUBSET = :table_subset AND EFFECTIVE_DATE = :effective_date AND MIN_ISSUE_AGE = :min_issue_age AND MAX_ISSUE_AGE > :max_issue_age)) ";
            order = " ORDER BY COMPANY_CODE, TABLE_SUBSET, EFFECTIVE_DATE, MIN_ISSUE_AGE, MAX_ISSUE_AGE";
        }
        else {
            if (isSubsetMode)
                pagingWhere = ".T064E WHERE (COMPANY_CODE = :company_code) AND (TABLE_SUBSET = :table_subset) AND ((EFFECTIVE_DATE < :effective_date) OR (EFFECTIVE_DATE = :effective_date AND MIN_ISSUE_AGE < :min_issue_age) OR (EFFECTIVE_DATE = :effective_date AND MIN_ISSUE_AGE = :min_issue_age AND MAX_ISSUE_AGE < :max_issue_age)) ";
            else
                pagingWhere = ".T064E WHERE (COMPANY_CODE = :company_code) AND ((TABLE_SUBSET < :table_subset) OR (TABLE_SUBSET = :table_subset AND EFFECTIVE_DATE < :effective_date) OR (TABLE_SUBSET = :table_subset AND EFFECTIVE_DATE = :effective_date AND MIN_ISSUE_AGE < :min_issue_age) OR (TABLE_SUBSET = :table_subset AND EFFECTIVE_DATE = :effective_date AND MIN_ISSUE_AGE = :min_issue_age AND MAX_ISSUE_AGE < :max_issue_age)) ";
            order = " ORDER BY COMPANY_CODE DESC, TABLE_SUBSET DESC, EFFECTIVE_DATE DESC, MIN_ISSUE_AGE DESC, MAX_ISSUE_AGE DESC";
        }
        return pagingSql + schemaName + pagingWhere + order;
    }
    
    public String prepareInsertStmt(String schemaName) {
        StringBuffer sb = new StringBuffer();
        sb.append("INSERT INTO ").append(schemaName);
        sb.append(".T064E ( ");
        sb.append("COMPANY_CODE, TABLE_SUBSET, EFFECTIVE_DATE, MIN_ISSUE_AGE, MAX_ISSUE_AGE, MAX_NO_INCREASE, DUR_COLA_INCREASE, MAX_INCREASE_AMT, MTHD_OF_INCREASE, TYPE_OF_INCREASE, INCREASE_PCT, INCREASE_FLAT_AMT, DECREASE_TERM_CD, TYP_CHG_TERM_CD )");
        sb.append(" VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ? )");
        return sb.toString();
    }
    
    public String prepareUpdateStmt(String schemaName)
    {
        StringBuffer sb = new StringBuffer();
        sb.append("UPDATE ").append(schemaName);
        sb.append(".T064E ");
        sb.append(" SET COMPANY_CODE = ?, TABLE_SUBSET = ?, EFFECTIVE_DATE = ?, MIN_ISSUE_AGE = ?, MAX_ISSUE_AGE = ?, MAX_NO_INCREASE = ?, DUR_COLA_INCREASE = ?, MAX_INCREASE_AMT = ?, MTHD_OF_INCREASE = ?, TYPE_OF_INCREASE = ?, INCREASE_PCT = ?, INCREASE_FLAT_AMT = ?, DECREASE_TERM_CD = ?, TYP_CHG_TERM_CD = ?");
        sb.append(" WHERE COMPANY_CODE = ? AND TABLE_SUBSET = ? AND EFFECTIVE_DATE = ? AND MIN_ISSUE_AGE = ? AND MAX_ISSUE_AGE = ?");
        return sb.toString();
    }
    
    public String prepareDeleteStmt(String schemaName)
    {
        StringBuffer sb = new StringBuffer();
        sb.append("DELETE FROM ").append(schemaName);
        sb.append(".T064E ");
        sb.append(" WHERE COMPANY_CODE = ? AND TABLE_SUBSET = ? AND EFFECTIVE_DATE = ? AND MIN_ISSUE_AGE = ? AND MAX_ISSUE_AGE = ?");
        return sb.toString();
    }
}
