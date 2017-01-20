package com.csc.fsg.life.pw.io.descriptor.wma;

import com.csc.fsg.life.pw.io.*;

public class TW70XDescriptor
    extends TableDescriptor
{
    private static final String pagingSql = "SELECT COMPANY_CODE, TABLE_SUBSET, TABLE_TYPE, STARTING_AGE, ENDING_AGE, INT_ASM_TBL, MORT_ASM_TBL FROM ";
    
    public void initialize()
    {
        setRowClass(TW70XRow.class);
        setTableName("TW70X");
        setTableId("W70");
        super.initialize();
    }
    
    public void initializeColumnDescriptors()
    {
        super.initializeColumnDescriptors();
        addColumnDescriptor(new ColumnDescriptor(this,"getCompanyCode","setCompanyCode","COMPANY_CODE,1,1,3,0,true|,0,CHAR,1,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getTableSubset","setTableSubset","TABLE_SUBSET,1,2,16,0,true|,0,CHAR,1,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getTableType","setTableType","TABLE_TYPE,1,3,3,0,true|,0,CHAR,1,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getStartingAge","setStartingAge","STARTING_AGE,5,4,3,0,true|,0,INTEGER,1,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getEndingAge","setEndingAge","ENDING_AGE,5,5,3,0,false|,0,INTEGER,1,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getIntAsmTbl","setIntAsmTbl","INT_ASM_TBL,1,6,16,0,false|,0,CHAR,1,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getMortAsmTbl","setMortAsmTbl","MORT_ASM_TBL,1,7,16,0,false|,0,CHAR,1,null,null,null,null,null"));
    }
    
    public String getPagingSQL(String schemaName,boolean isSubsetMode,boolean isNext, boolean locator)
    {
        String pagingWhere = null;
        String order = null;
        if (isNext) {
            if (isSubsetMode)
                if (locator)
                    pagingWhere = ".TW70X WHERE (COMPANY_CODE = :company_code) AND (TABLE_SUBSET = :table_subset) AND ((TABLE_TYPE > :table_type) OR (TABLE_TYPE = :table_type AND STARTING_AGE > :starting_age) OR (TABLE_TYPE = :table_type AND STARTING_AGE = :starting_age)) ";
                else 
                    pagingWhere = ".TW70X WHERE (COMPANY_CODE = :company_code) AND (TABLE_SUBSET = :table_subset) AND ((TABLE_TYPE > :table_type) OR (TABLE_TYPE = :table_type AND STARTING_AGE > :starting_age)) ";
            else
                if (locator)
                    pagingWhere = ".TW70X WHERE (COMPANY_CODE = :company_code) AND ((TABLE_SUBSET > :table_subset) OR (TABLE_SUBSET = :table_subset AND TABLE_TYPE > :table_type) OR (TABLE_SUBSET = :table_subset AND TABLE_TYPE = :table_type AND STARTING_AGE > :starting_age) OR (TABLE_SUBSET = :table_subset AND TABLE_TYPE = :table_type AND STARTING_AGE = :starting_age)) ";
                else
                    pagingWhere = ".TW70X WHERE (COMPANY_CODE = :company_code) AND ((TABLE_SUBSET > :table_subset) OR (TABLE_SUBSET = :table_subset AND TABLE_TYPE > :table_type) OR (TABLE_SUBSET = :table_subset AND TABLE_TYPE = :table_type AND STARTING_AGE > :starting_age)) ";
            order = " ORDER BY COMPANY_CODE, TABLE_SUBSET, TABLE_TYPE, STARTING_AGE";
        }
        else {
            if (isSubsetMode)
                pagingWhere = ".TW70X WHERE (COMPANY_CODE = :company_code) AND (TABLE_SUBSET = :table_subset) AND ((TABLE_TYPE < :table_type) OR (TABLE_TYPE = :table_type AND STARTING_AGE < :starting_age)) ";
            else
                pagingWhere = ".TW70X WHERE (COMPANY_CODE = :company_code) AND ((TABLE_SUBSET < :table_subset) OR (TABLE_SUBSET = :table_subset AND TABLE_TYPE < :table_type) OR (TABLE_SUBSET = :table_subset AND TABLE_TYPE = :table_type AND STARTING_AGE < :starting_age)) ";
            order = " ORDER BY COMPANY_CODE DESC, TABLE_SUBSET DESC, TABLE_TYPE DESC, STARTING_AGE DESC";
        }
        return pagingSql + schemaName + pagingWhere + order;
    }
    
    public String prepareInsertStmt(String schemaName) {
        StringBuffer sb = new StringBuffer();
        sb.append("INSERT INTO ").append(schemaName);
        sb.append(".TW70X ( ");
        sb.append("COMPANY_CODE, TABLE_SUBSET, TABLE_TYPE, STARTING_AGE, ENDING_AGE, INT_ASM_TBL, MORT_ASM_TBL )");
        sb.append(" VALUES (?, ?, ?, ?, ?, ?, ? )");
        return sb.toString();
    }
    
    public String prepareUpdateStmt(String schemaName)
    {
        StringBuffer sb = new StringBuffer();
        sb.append("UPDATE ").append(schemaName);
        sb.append(".TW70X ");
        sb.append(" SET COMPANY_CODE = ?, TABLE_SUBSET = ?, TABLE_TYPE = ?, STARTING_AGE = ?, ENDING_AGE = ?, INT_ASM_TBL = ?, MORT_ASM_TBL = ?");
        sb.append(" WHERE COMPANY_CODE = ? AND TABLE_SUBSET = ? AND TABLE_TYPE = ? AND STARTING_AGE = ?");
        return sb.toString();
    }
    
    public String prepareDeleteStmt(String schemaName)
    {
        StringBuffer sb = new StringBuffer();
        sb.append("DELETE FROM ").append(schemaName);
        sb.append(".TW70X ");
        sb.append(" WHERE COMPANY_CODE = ? AND TABLE_SUBSET = ? AND TABLE_TYPE = ? AND STARTING_AGE = ?");
        return sb.toString();
    }
}
