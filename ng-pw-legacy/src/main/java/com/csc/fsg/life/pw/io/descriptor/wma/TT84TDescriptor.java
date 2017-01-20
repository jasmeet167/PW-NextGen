package com.csc.fsg.life.pw.io.descriptor.wma;

import com.csc.fsg.life.pw.io.*;

public class TT84TDescriptor
    extends TableDescriptor
{
    private static final String pagingSql = "SELECT COMPANY_CODE, TABLE_SUBSET, EFFECTIVE_DATE, STAT_COMP_CODE_01, STAT_COMP_CODE_02, STAT_COMP_CODE_03, STAT_COMP_CODE_04, STAT_COMP_CODE_05, STAT_COMP_CODE_06, STAT_COMP_CODE_07, STAT_COMP_CODE_08, STAT_COMP_CODE_09, STAT_COMP_CODE_10 FROM ";
    
    public void initialize()
    {
        setRowClass(TT84TRow.class);
        setTableName("TT84T");
        setTableId("T84");
        super.initialize();
    }
    
    public void initializeColumnDescriptors()
    {
        super.initializeColumnDescriptors();
        addColumnDescriptor(new ColumnDescriptor(this,"getCompanyCode","setCompanyCode","COMPANY_CODE,1,1,3,0,true|,0,CHAR,1,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getTableSubset","setTableSubset","TABLE_SUBSET,1,2,16,0,true|,0,CHAR,1,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getEffectiveDate","setEffectiveDate","EFFECTIVE_DATE,91,3,10,0,true|,0,DATE,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getStatCompCode01","setStatCompCode01","STAT_COMP_CODE_01,1,4,3,0,false|,0,CHAR,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getStatCompCode02","setStatCompCode02","STAT_COMP_CODE_02,1,5,3,0,false|,0,CHAR,4,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getStatCompCode03","setStatCompCode03","STAT_COMP_CODE_03,1,6,3,0,false|,0,CHAR,4,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getStatCompCode04","setStatCompCode04","STAT_COMP_CODE_04,1,7,3,0,false|,0,CHAR,4,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getStatCompCode05","setStatCompCode05","STAT_COMP_CODE_05,1,8,3,0,false|,0,CHAR,4,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getStatCompCode06","setStatCompCode06","STAT_COMP_CODE_06,1,9,3,0,false|,0,CHAR,4,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getStatCompCode07","setStatCompCode07","STAT_COMP_CODE_07,1,10,3,0,false|,0,CHAR,4,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getStatCompCode08","setStatCompCode08","STAT_COMP_CODE_08,1,11,3,0,false|,0,CHAR,4,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getStatCompCode09","setStatCompCode09","STAT_COMP_CODE_09,1,12,3,0,false|,0,CHAR,4,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getStatCompCode10","setStatCompCode10","STAT_COMP_CODE_10,1,13,3,0,false|,0,CHAR,4,null,null,null,null,null"));
    }
    
    public String getPagingSQL(String schemaName,boolean isSubsetMode,boolean isNext, boolean locator)
    {
        String pagingWhere = null;
        String order = null;
        if (isNext) {
            if (isSubsetMode)
                if (locator)
                    pagingWhere = ".TT84T WHERE (COMPANY_CODE = :company_code) AND (TABLE_SUBSET = :table_subset) AND ((EFFECTIVE_DATE > :effective_date) OR (EFFECTIVE_DATE = :effective_date)) ";
                else 
                    pagingWhere = ".TT84T WHERE (COMPANY_CODE = :company_code) AND (TABLE_SUBSET = :table_subset) AND ((EFFECTIVE_DATE > :effective_date)) ";
            else
                if (locator)
                    pagingWhere = ".TT84T WHERE (COMPANY_CODE = :company_code) AND ((TABLE_SUBSET > :table_subset) OR (TABLE_SUBSET = :table_subset AND EFFECTIVE_DATE > :effective_date) OR (TABLE_SUBSET = :table_subset AND EFFECTIVE_DATE = :effective_date)) ";
                else
                    pagingWhere = ".TT84T WHERE (COMPANY_CODE = :company_code) AND ((TABLE_SUBSET > :table_subset) OR (TABLE_SUBSET = :table_subset AND EFFECTIVE_DATE > :effective_date)) ";
            order = " ORDER BY COMPANY_CODE, TABLE_SUBSET, EFFECTIVE_DATE";
        }
        else {
            if (isSubsetMode)
                pagingWhere = ".TT84T WHERE (COMPANY_CODE = :company_code) AND (TABLE_SUBSET = :table_subset) AND ((EFFECTIVE_DATE < :effective_date)) ";
            else
                pagingWhere = ".TT84T WHERE (COMPANY_CODE = :company_code) AND ((TABLE_SUBSET < :table_subset) OR (TABLE_SUBSET = :table_subset AND EFFECTIVE_DATE < :effective_date)) ";
            order = " ORDER BY COMPANY_CODE DESC, TABLE_SUBSET DESC, EFFECTIVE_DATE DESC";
        }
        return pagingSql + schemaName + pagingWhere + order;
    }
    
    public String prepareInsertStmt(String schemaName) {
        StringBuffer sb = new StringBuffer();
        sb.append("INSERT INTO ").append(schemaName);
        sb.append(".TT84T ( ");
        sb.append("COMPANY_CODE, TABLE_SUBSET, EFFECTIVE_DATE, STAT_COMP_CODE_01, STAT_COMP_CODE_02, STAT_COMP_CODE_03, STAT_COMP_CODE_04, STAT_COMP_CODE_05, STAT_COMP_CODE_06, STAT_COMP_CODE_07, STAT_COMP_CODE_08, STAT_COMP_CODE_09, STAT_COMP_CODE_10 )");
        sb.append(" VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ? )");
        return sb.toString();
    }
    
    public String prepareUpdateStmt(String schemaName)
    {
        StringBuffer sb = new StringBuffer();
        sb.append("UPDATE ").append(schemaName);
        sb.append(".TT84T ");
        sb.append(" SET COMPANY_CODE = ?, TABLE_SUBSET = ?, EFFECTIVE_DATE = ?, STAT_COMP_CODE_01 = ?, STAT_COMP_CODE_02 = ?, STAT_COMP_CODE_03 = ?, STAT_COMP_CODE_04 = ?, STAT_COMP_CODE_05 = ?, STAT_COMP_CODE_06 = ?, STAT_COMP_CODE_07 = ?, STAT_COMP_CODE_08 = ?, STAT_COMP_CODE_09 = ?, STAT_COMP_CODE_10 = ?");
        sb.append(" WHERE COMPANY_CODE = ? AND TABLE_SUBSET = ? AND EFFECTIVE_DATE = ?");
        return sb.toString();
    }
    
    public String prepareDeleteStmt(String schemaName)
    {
        StringBuffer sb = new StringBuffer();
        sb.append("DELETE FROM ").append(schemaName);
        sb.append(".TT84T ");
        sb.append(" WHERE COMPANY_CODE = ? AND TABLE_SUBSET = ? AND EFFECTIVE_DATE = ?");
        return sb.toString();
    }
}
