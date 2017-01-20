package com.csc.fsg.life.pw.io.descriptor.wma;

import com.csc.fsg.life.pw.io.*;

public class TT06TDescriptor
    extends TableDescriptor
{
    private static final String pagingSql = "SELECT COMPANY_CODE, TABLE_SUBSET, RIDER_BENE_TYPE, M_CASH_VAL_AGE_ADJ, F_CASH_VAL_AGE_ADJ, TERM_DIVIDEND_DUR FROM ";
    
    public void initialize()
    {
        setRowClass(TT06TRow.class);
        setTableName("TT06T");
        setTableId("T06");
        super.initialize();
    }
    
    public void initializeColumnDescriptors()
    {
        super.initializeColumnDescriptors();
        addColumnDescriptor(new ColumnDescriptor(this,"getCompanyCode","setCompanyCode","COMPANY_CODE,1,1,3,0,true|,0,CHAR,1,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getTableSubset","setTableSubset","TABLE_SUBSET,1,2,16,0,true|,0,CHAR,1,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getRiderBeneType","setRiderBeneType","RIDER_BENE_TYPE,1,3,2,0,false|,0,CHAR,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getMCashValAgeAdj","setMCashValAgeAdj","M_CASH_VAL_AGE_ADJ,3,4,3,0,false|,0,INTEGER,2,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getFCashValAgeAdj","setFCashValAgeAdj","F_CASH_VAL_AGE_ADJ,3,5,3,0,false|,0,INTEGER,2,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getTermDividendDur","setTermDividendDur","TERM_DIVIDEND_DUR,3,6,3,0,false|,0,INTEGER,0,null,null,null,null,null"));
    }
    
    public String getPagingSQL(String schemaName,boolean isSubsetMode,boolean isNext, boolean locator)
    {
        String pagingWhere = null;
        String order = null;
        if (isNext) {
            if (isSubsetMode)
                if (locator)
                    pagingWhere = ".TT06T WHERE (COMPANY_CODE = :company_code) AND (TABLE_SUBSET = :table_subset)";
                else 
                    pagingWhere = ".TT06T WHERE (COMPANY_CODE = :company_code) AND (TABLE_SUBSET = :table_subset)";
            else
                if (locator)
                    pagingWhere = ".TT06T WHERE (COMPANY_CODE = :company_code) AND ((TABLE_SUBSET > :table_subset) OR (TABLE_SUBSET = :table_subset)) ";
                else
                    pagingWhere = ".TT06T WHERE (COMPANY_CODE = :company_code) AND ((TABLE_SUBSET > :table_subset)) ";
            order = " ORDER BY COMPANY_CODE, TABLE_SUBSET";
        }
        else {
            if (isSubsetMode)
                pagingWhere = ".TT06T WHERE (COMPANY_CODE = :company_code) AND (TABLE_SUBSET = :table_subset)";
            else
                pagingWhere = ".TT06T WHERE (COMPANY_CODE = :company_code) AND ((TABLE_SUBSET < :table_subset)) ";
            order = " ORDER BY COMPANY_CODE DESC, TABLE_SUBSET DESC";
        }
        return pagingSql + schemaName + pagingWhere + order;
    }
    
    public String prepareInsertStmt(String schemaName) {
        StringBuffer sb = new StringBuffer();
        sb.append("INSERT INTO ").append(schemaName);
        sb.append(".TT06T ( ");
        sb.append("COMPANY_CODE, TABLE_SUBSET, RIDER_BENE_TYPE, M_CASH_VAL_AGE_ADJ, F_CASH_VAL_AGE_ADJ, TERM_DIVIDEND_DUR )");
        sb.append(" VALUES (?, ?, ?, ?, ?, ? )");
        return sb.toString();
    }
    
    public String prepareUpdateStmt(String schemaName)
    {
        StringBuffer sb = new StringBuffer();
        sb.append("UPDATE ").append(schemaName);
        sb.append(".TT06T ");
        sb.append(" SET COMPANY_CODE = ?, TABLE_SUBSET = ?, RIDER_BENE_TYPE = ?, M_CASH_VAL_AGE_ADJ = ?, F_CASH_VAL_AGE_ADJ = ?, TERM_DIVIDEND_DUR = ?");
        sb.append(" WHERE COMPANY_CODE = ? AND TABLE_SUBSET = ?");
        return sb.toString();
    }
    
    public String prepareDeleteStmt(String schemaName)
    {
        StringBuffer sb = new StringBuffer();
        sb.append("DELETE FROM ").append(schemaName);
        sb.append(".TT06T ");
        sb.append(" WHERE COMPANY_CODE = ? AND TABLE_SUBSET = ?");
        return sb.toString();
    }
}
