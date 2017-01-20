package com.csc.fsg.life.pw.io.descriptor.wma;

import com.csc.fsg.life.pw.io.*;

public class T032EDescriptor
    extends TableDescriptor
{
    private static final String pagingSql = "SELECT COMPANY_CODE, TABLE_SUBSET, TRANSACTION_CODE, EFFECTIVE_DATE, NUM_FREE_WTH_YR, CHG_PER_UNIT_IND, CHG_PER_UNIT_TBL, PCT_CASH_VAL_TBL, PCT_TGT_TBL, REM_RISK_LOAD_TBL FROM ";
    
    public void initialize()
    {
        setRowClass(T032ERow.class);
        setTableName("T032E");
        setTableId("032");
        super.initialize();
    }
    
    public void initializeColumnDescriptors()
    {
        super.initializeColumnDescriptors();
        addColumnDescriptor(new ColumnDescriptor(this,"getCompanyCode","setCompanyCode","COMPANY_CODE,1,1,3,0,true|,0,CHAR,1,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getTableSubset","setTableSubset","TABLE_SUBSET,1,2,16,0,true|,0,CHAR,1,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getTransactionCode","setTransactionCode","TRANSACTION_CODE,1,3,4,0,true|,0,CHAR,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getEffectiveDate","setEffectiveDate","EFFECTIVE_DATE,91,4,10,0,true|,0,DATE,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getNumFreeWthYr","setNumFreeWthYr","NUM_FREE_WTH_YR,3,5,2,0,false|,0,INTEGER,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getChgPerUnitInd","setChgPerUnitInd","CHG_PER_UNIT_IND,1,6,1,0,false|,0,CHAR,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getChgPerUnitTbl","setChgPerUnitTbl","CHG_PER_UNIT_TBL,1,7,16,0,false|,0,CHAR,0,034,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getPctCashValTbl","setPctCashValTbl","PCT_CASH_VAL_TBL,1,8,16,0,false|,0,CHAR,0,042,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getPctTgtTbl","setPctTgtTbl","PCT_TGT_TBL,1,9,16,0,false|,0,CHAR,0,043,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getRemRiskLoadTbl","setRemRiskLoadTbl","REM_RISK_LOAD_TBL,1,10,16,0,false|,0,CHAR,0,null,null,null,null,null"));
    }
    
    public String getPagingSQL(String schemaName,boolean isSubsetMode,boolean isNext, boolean locator)
    {
        String pagingWhere = null;
        String order = null;
        if (isNext) {
            if (isSubsetMode)
                if (locator)
                    pagingWhere = ".T032E WHERE (COMPANY_CODE = :company_code) AND (TABLE_SUBSET = :table_subset) AND ((TRANSACTION_CODE > :transaction_code) OR (TRANSACTION_CODE = :transaction_code AND EFFECTIVE_DATE > :effective_date) OR (TRANSACTION_CODE = :transaction_code AND EFFECTIVE_DATE = :effective_date)) ";
                else 
                    pagingWhere = ".T032E WHERE (COMPANY_CODE = :company_code) AND (TABLE_SUBSET = :table_subset) AND ((TRANSACTION_CODE > :transaction_code) OR (TRANSACTION_CODE = :transaction_code AND EFFECTIVE_DATE > :effective_date)) ";
            else
                if (locator)
                    pagingWhere = ".T032E WHERE (COMPANY_CODE = :company_code) AND ((TABLE_SUBSET > :table_subset) OR (TABLE_SUBSET = :table_subset AND TRANSACTION_CODE > :transaction_code) OR (TABLE_SUBSET = :table_subset AND TRANSACTION_CODE = :transaction_code AND EFFECTIVE_DATE > :effective_date) OR (TABLE_SUBSET = :table_subset AND TRANSACTION_CODE = :transaction_code AND EFFECTIVE_DATE = :effective_date)) ";
                else
                    pagingWhere = ".T032E WHERE (COMPANY_CODE = :company_code) AND ((TABLE_SUBSET > :table_subset) OR (TABLE_SUBSET = :table_subset AND TRANSACTION_CODE > :transaction_code) OR (TABLE_SUBSET = :table_subset AND TRANSACTION_CODE = :transaction_code AND EFFECTIVE_DATE > :effective_date)) ";
            order = " ORDER BY COMPANY_CODE, TABLE_SUBSET, TRANSACTION_CODE, EFFECTIVE_DATE";
        }
        else {
            if (isSubsetMode)
                pagingWhere = ".T032E WHERE (COMPANY_CODE = :company_code) AND (TABLE_SUBSET = :table_subset) AND ((TRANSACTION_CODE < :transaction_code) OR (TRANSACTION_CODE = :transaction_code AND EFFECTIVE_DATE < :effective_date)) ";
            else
                pagingWhere = ".T032E WHERE (COMPANY_CODE = :company_code) AND ((TABLE_SUBSET < :table_subset) OR (TABLE_SUBSET = :table_subset AND TRANSACTION_CODE < :transaction_code) OR (TABLE_SUBSET = :table_subset AND TRANSACTION_CODE = :transaction_code AND EFFECTIVE_DATE < :effective_date)) ";
            order = " ORDER BY COMPANY_CODE DESC, TABLE_SUBSET DESC, TRANSACTION_CODE DESC, EFFECTIVE_DATE DESC";
        }
        return pagingSql + schemaName + pagingWhere + order;
    }
    
    public String prepareInsertStmt(String schemaName) {
        StringBuffer sb = new StringBuffer();
        sb.append("INSERT INTO ").append(schemaName);
        sb.append(".T032E ( ");
        sb.append("COMPANY_CODE, TABLE_SUBSET, TRANSACTION_CODE, EFFECTIVE_DATE, NUM_FREE_WTH_YR, CHG_PER_UNIT_IND, CHG_PER_UNIT_TBL, PCT_CASH_VAL_TBL, PCT_TGT_TBL, REM_RISK_LOAD_TBL )");
        sb.append(" VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ? )");
        return sb.toString();
    }
    
    public String prepareUpdateStmt(String schemaName)
    {
        StringBuffer sb = new StringBuffer();
        sb.append("UPDATE ").append(schemaName);
        sb.append(".T032E ");
        sb.append(" SET COMPANY_CODE = ?, TABLE_SUBSET = ?, TRANSACTION_CODE = ?, EFFECTIVE_DATE = ?, NUM_FREE_WTH_YR = ?, CHG_PER_UNIT_IND = ?, CHG_PER_UNIT_TBL = ?, PCT_CASH_VAL_TBL = ?, PCT_TGT_TBL = ?, REM_RISK_LOAD_TBL = ?");
        sb.append(" WHERE COMPANY_CODE = ? AND TABLE_SUBSET = ? AND TRANSACTION_CODE = ? AND EFFECTIVE_DATE = ?");
        return sb.toString();
    }
    
    public String prepareDeleteStmt(String schemaName)
    {
        StringBuffer sb = new StringBuffer();
        sb.append("DELETE FROM ").append(schemaName);
        sb.append(".T032E ");
        sb.append(" WHERE COMPANY_CODE = ? AND TABLE_SUBSET = ? AND TRANSACTION_CODE = ? AND EFFECTIVE_DATE = ?");
        return sb.toString();
    }
}
