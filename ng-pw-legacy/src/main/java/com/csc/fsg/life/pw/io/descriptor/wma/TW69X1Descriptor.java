package com.csc.fsg.life.pw.io.descriptor.wma;

import com.csc.fsg.life.pw.io.*;

public class TW69X1Descriptor
    extends TableDescriptor
{
    private static final String pagingSql = "SELECT COMPANY_CODE, TABLE_SUBSET, INTEREST_RATE_SEQ, INT_RATE, LN_1_PLUS_I, SUMM_LN_1_PLUS_I, PRESENT_VALUE_I, V_SUPER_T FROM ";
    
    public void initialize()
    {
        setRowClass(TW69X1Row.class);
        setTableName("TW69X1");
        setTableId("69X");
        super.initialize();
    }
    
    public void initializeColumnDescriptors()
    {
        super.initializeColumnDescriptors();
        addColumnDescriptor(new ColumnDescriptor(this,"getCompanyCode","setCompanyCode","COMPANY_CODE,1,1,3,0,true|,0,CHAR,1,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getTableSubset","setTableSubset","TABLE_SUBSET,1,2,16,0,true|,0,CHAR,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getInterestRateSeq","setInterestRateSeq","INTEREST_RATE_SEQ,3,3,3,0,true|,0,INTEGER,1,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getIntRate","setIntRate","INT_RATE,3,4,17,15,false|,0,DOUBLE,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getLn1PlusI","setLn1PlusI","LN_1_PLUS_I,3,5,13,11,false|,0,DOUBLE,2,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getSummLn1PlusI","setSummLn1PlusI","SUMM_LN_1_PLUS_I,3,6,13,11,false|,0,DOUBLE,2,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getPresentValueI","setPresentValueI","PRESENT_VALUE_I,3,7,10,8,false|,0,DOUBLE,2,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getVSuperT","setVSuperT","V_SUPER_T,3,8,10,8,false|,0,DOUBLE,2,null,null,null,null,null"));
    }
    
    public String getPagingSQL(String schemaName,boolean isSubsetMode,boolean isNext, boolean locator)
    {
        String pagingWhere = null;
        String order = null;
        if (isNext) {
            if (isSubsetMode)
                if (locator)
                    pagingWhere = ".TW69X1 WHERE (COMPANY_CODE = :company_code) AND (TABLE_SUBSET = :table_subset) AND ((INTEREST_RATE_SEQ > :interest_rate_seq) OR (INTEREST_RATE_SEQ = :interest_rate_seq)) ";
                else 
                    pagingWhere = ".TW69X1 WHERE (COMPANY_CODE = :company_code) AND (TABLE_SUBSET = :table_subset) AND ((INTEREST_RATE_SEQ > :interest_rate_seq)) ";
            else
                if (locator)
                    pagingWhere = ".TW69X1 WHERE (COMPANY_CODE = :company_code) AND ((TABLE_SUBSET > :table_subset) OR (TABLE_SUBSET = :table_subset AND INTEREST_RATE_SEQ > :interest_rate_seq) OR (TABLE_SUBSET = :table_subset AND INTEREST_RATE_SEQ = :interest_rate_seq)) ";
                else
                    pagingWhere = ".TW69X1 WHERE (COMPANY_CODE = :company_code) AND ((TABLE_SUBSET > :table_subset) OR (TABLE_SUBSET = :table_subset AND INTEREST_RATE_SEQ > :interest_rate_seq)) ";
            order = " ORDER BY COMPANY_CODE, TABLE_SUBSET, INTEREST_RATE_SEQ";
        }
        else {
            if (isSubsetMode)
                pagingWhere = ".TW69X1 WHERE (COMPANY_CODE = :company_code) AND (TABLE_SUBSET = :table_subset) AND ((INTEREST_RATE_SEQ < :interest_rate_seq)) ";
            else
                pagingWhere = ".TW69X1 WHERE (COMPANY_CODE = :company_code) AND ((TABLE_SUBSET < :table_subset) OR (TABLE_SUBSET = :table_subset AND INTEREST_RATE_SEQ < :interest_rate_seq)) ";
            order = " ORDER BY COMPANY_CODE DESC, TABLE_SUBSET DESC, INTEREST_RATE_SEQ DESC";
        }
        return pagingSql + schemaName + pagingWhere + order;
    }
    
    public String prepareInsertStmt(String schemaName) {
        StringBuffer sb = new StringBuffer();
        sb.append("INSERT INTO ").append(schemaName);
        sb.append(".TW69X1 ( ");
        sb.append("COMPANY_CODE, TABLE_SUBSET, INTEREST_RATE_SEQ, INT_RATE, LN_1_PLUS_I, SUMM_LN_1_PLUS_I, PRESENT_VALUE_I, V_SUPER_T )");
        sb.append(" VALUES (?, ?, ?, ?, ?, ?, ?, ? )");
        return sb.toString();
    }
    
    public String prepareUpdateStmt(String schemaName)
    {
        StringBuffer sb = new StringBuffer();
        sb.append("UPDATE ").append(schemaName);
        sb.append(".TW69X1 ");
        sb.append(" SET COMPANY_CODE = ?, TABLE_SUBSET = ?, INTEREST_RATE_SEQ = ?, INT_RATE = ?, LN_1_PLUS_I = ?, SUMM_LN_1_PLUS_I = ?, PRESENT_VALUE_I = ?, V_SUPER_T = ?");
        sb.append(" WHERE COMPANY_CODE = ? AND TABLE_SUBSET = ? AND INTEREST_RATE_SEQ = ?");
        return sb.toString();
    }
    
    public String prepareDeleteStmt(String schemaName)
    {
        StringBuffer sb = new StringBuffer();
        sb.append("DELETE FROM ").append(schemaName);
        sb.append(".TW69X1 ");
        sb.append(" WHERE COMPANY_CODE = ? AND TABLE_SUBSET = ? AND INTEREST_RATE_SEQ = ?");
        return sb.toString();
    }
}
