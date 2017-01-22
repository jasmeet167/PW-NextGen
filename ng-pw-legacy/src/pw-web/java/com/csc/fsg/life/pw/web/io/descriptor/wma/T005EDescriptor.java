package com.csc.fsg.life.pw.web.io.descriptor.wma;

import com.csc.fsg.life.pw.web.io.*;

public class T005EDescriptor
    extends TableDescriptor
{
    private static final String pagingSql = "SELECT COMPANY_CODE, TABLE_SUBSET, EFFECTIVE_DATE, TEFRA_LITA_IND, MIN_ATAN_AGE, MX_ATAN_AGE, CORR_PCT FROM ";
    
    public void initialize()
    {
        setRowClass(T005ERow.class);
        setTableName("T005E");
        setTableId("005");
        super.initialize();
    }
    
    public void initializeColumnDescriptors()
    {
        super.initializeColumnDescriptors();
        addColumnDescriptor(new ColumnDescriptor(this,"getCompanyCode","setCompanyCode","COMPANY_CODE,1,1,3,0,true|,0,CHAR,1,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getTableSubset","setTableSubset","TABLE_SUBSET,1,2,16,0,true|,0,CHAR,1,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getEffectiveDate","setEffectiveDate","EFFECTIVE_DATE,91,3,10,0,true|,0,DATE,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getTefraLitaInd","setTefraLitaInd","TEFRA_LITA_IND,1,4,1,0,true|,0,CHAR,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getMinAtanAge","setMinAtanAge","MIN_ATAN_AGE,3,5,3,0,true|,0,INTEGER,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getMxAtanAge","setMxAtanAge","MX_ATAN_AGE,3,6,3,0,true|,0,INTEGER,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getCorrPct","setCorrPct","CORR_PCT,3,7,7,3,false|,0,DOUBLE,0,null,null,null,null,null"));
    }
    
    public String getPagingSQL(String schemaName,boolean isSubsetMode,boolean isNext, boolean locator)
    {
        String pagingWhere = null;
        String order = null;
        if (isNext) {
            if (isSubsetMode)
                if (locator)
                    pagingWhere = ".T005E WHERE (COMPANY_CODE = :company_code) AND (TABLE_SUBSET = :table_subset) AND ((EFFECTIVE_DATE > :effective_date) OR (EFFECTIVE_DATE = :effective_date AND TEFRA_LITA_IND > :tefra_lita_ind) OR (EFFECTIVE_DATE = :effective_date AND TEFRA_LITA_IND = :tefra_lita_ind AND MIN_ATAN_AGE > :min_atan_age) OR (EFFECTIVE_DATE = :effective_date AND TEFRA_LITA_IND = :tefra_lita_ind AND MIN_ATAN_AGE = :min_atan_age AND MX_ATAN_AGE > :mx_atan_age) OR (EFFECTIVE_DATE = :effective_date AND TEFRA_LITA_IND = :tefra_lita_ind AND MIN_ATAN_AGE = :min_atan_age AND MX_ATAN_AGE = :mx_atan_age)) ";
                else 
                    pagingWhere = ".T005E WHERE (COMPANY_CODE = :company_code) AND (TABLE_SUBSET = :table_subset) AND ((EFFECTIVE_DATE > :effective_date) OR (EFFECTIVE_DATE = :effective_date AND TEFRA_LITA_IND > :tefra_lita_ind) OR (EFFECTIVE_DATE = :effective_date AND TEFRA_LITA_IND = :tefra_lita_ind AND MIN_ATAN_AGE > :min_atan_age) OR (EFFECTIVE_DATE = :effective_date AND TEFRA_LITA_IND = :tefra_lita_ind AND MIN_ATAN_AGE = :min_atan_age AND MX_ATAN_AGE > :mx_atan_age)) ";
            else
                if (locator)
                    pagingWhere = ".T005E WHERE (COMPANY_CODE = :company_code) AND ((TABLE_SUBSET > :table_subset) OR (TABLE_SUBSET = :table_subset AND EFFECTIVE_DATE > :effective_date) OR (TABLE_SUBSET = :table_subset AND EFFECTIVE_DATE = :effective_date AND TEFRA_LITA_IND > :tefra_lita_ind) OR (TABLE_SUBSET = :table_subset AND EFFECTIVE_DATE = :effective_date AND TEFRA_LITA_IND = :tefra_lita_ind AND MIN_ATAN_AGE > :min_atan_age) OR (TABLE_SUBSET = :table_subset AND EFFECTIVE_DATE = :effective_date AND TEFRA_LITA_IND = :tefra_lita_ind AND MIN_ATAN_AGE = :min_atan_age AND MX_ATAN_AGE > :mx_atan_age) OR (TABLE_SUBSET = :table_subset AND EFFECTIVE_DATE = :effective_date AND TEFRA_LITA_IND = :tefra_lita_ind AND MIN_ATAN_AGE = :min_atan_age AND MX_ATAN_AGE = :mx_atan_age)) ";
                else
                    pagingWhere = ".T005E WHERE (COMPANY_CODE = :company_code) AND ((TABLE_SUBSET > :table_subset) OR (TABLE_SUBSET = :table_subset AND EFFECTIVE_DATE > :effective_date) OR (TABLE_SUBSET = :table_subset AND EFFECTIVE_DATE = :effective_date AND TEFRA_LITA_IND > :tefra_lita_ind) OR (TABLE_SUBSET = :table_subset AND EFFECTIVE_DATE = :effective_date AND TEFRA_LITA_IND = :tefra_lita_ind AND MIN_ATAN_AGE > :min_atan_age) OR (TABLE_SUBSET = :table_subset AND EFFECTIVE_DATE = :effective_date AND TEFRA_LITA_IND = :tefra_lita_ind AND MIN_ATAN_AGE = :min_atan_age AND MX_ATAN_AGE > :mx_atan_age)) ";
            order = " ORDER BY COMPANY_CODE, TABLE_SUBSET, EFFECTIVE_DATE, TEFRA_LITA_IND, MIN_ATAN_AGE, MX_ATAN_AGE";
        }
        else {
            if (isSubsetMode)
                pagingWhere = ".T005E WHERE (COMPANY_CODE = :company_code) AND (TABLE_SUBSET = :table_subset) AND ((EFFECTIVE_DATE < :effective_date) OR (EFFECTIVE_DATE = :effective_date AND TEFRA_LITA_IND < :tefra_lita_ind) OR (EFFECTIVE_DATE = :effective_date AND TEFRA_LITA_IND = :tefra_lita_ind AND MIN_ATAN_AGE < :min_atan_age) OR (EFFECTIVE_DATE = :effective_date AND TEFRA_LITA_IND = :tefra_lita_ind AND MIN_ATAN_AGE = :min_atan_age AND MX_ATAN_AGE < :mx_atan_age)) ";
            else
                pagingWhere = ".T005E WHERE (COMPANY_CODE = :company_code) AND ((TABLE_SUBSET < :table_subset) OR (TABLE_SUBSET = :table_subset AND EFFECTIVE_DATE < :effective_date) OR (TABLE_SUBSET = :table_subset AND EFFECTIVE_DATE = :effective_date AND TEFRA_LITA_IND < :tefra_lita_ind) OR (TABLE_SUBSET = :table_subset AND EFFECTIVE_DATE = :effective_date AND TEFRA_LITA_IND = :tefra_lita_ind AND MIN_ATAN_AGE < :min_atan_age) OR (TABLE_SUBSET = :table_subset AND EFFECTIVE_DATE = :effective_date AND TEFRA_LITA_IND = :tefra_lita_ind AND MIN_ATAN_AGE = :min_atan_age AND MX_ATAN_AGE < :mx_atan_age)) ";
            order = " ORDER BY COMPANY_CODE DESC, TABLE_SUBSET DESC, EFFECTIVE_DATE DESC, TEFRA_LITA_IND DESC, MIN_ATAN_AGE DESC, MX_ATAN_AGE DESC";
        }
        return pagingSql + schemaName + pagingWhere + order;
    }
    
    public String prepareInsertStmt(String schemaName) {
        StringBuffer sb = new StringBuffer();
        sb.append("INSERT INTO ").append(schemaName);
        sb.append(".T005E ( ");
        sb.append("COMPANY_CODE, TABLE_SUBSET, EFFECTIVE_DATE, TEFRA_LITA_IND, MIN_ATAN_AGE, MX_ATAN_AGE, CORR_PCT )");
        sb.append(" VALUES (?, ?, ?, ?, ?, ?, ? )");
        return sb.toString();
    }
    
    public String prepareUpdateStmt(String schemaName)
    {
        StringBuffer sb = new StringBuffer();
        sb.append("UPDATE ").append(schemaName);
        sb.append(".T005E ");
        sb.append(" SET COMPANY_CODE = ?, TABLE_SUBSET = ?, EFFECTIVE_DATE = ?, TEFRA_LITA_IND = ?, MIN_ATAN_AGE = ?, MX_ATAN_AGE = ?, CORR_PCT = ?");
        sb.append(" WHERE COMPANY_CODE = ? AND TABLE_SUBSET = ? AND EFFECTIVE_DATE = ? AND TEFRA_LITA_IND = ? AND MIN_ATAN_AGE = ? AND MX_ATAN_AGE = ?");
        return sb.toString();
    }
    
    public String prepareDeleteStmt(String schemaName)
    {
        StringBuffer sb = new StringBuffer();
        sb.append("DELETE FROM ").append(schemaName);
        sb.append(".T005E ");
        sb.append(" WHERE COMPANY_CODE = ? AND TABLE_SUBSET = ? AND EFFECTIVE_DATE = ? AND TEFRA_LITA_IND = ? AND MIN_ATAN_AGE = ? AND MX_ATAN_AGE = ?");
        return sb.toString();
    }
}
