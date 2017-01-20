package com.csc.fsg.life.pw.io.descriptor.wma;

import com.csc.fsg.life.pw.io.*;

public class TA33FDescriptor
    extends TableDescriptor
{
    private static final String pagingSql = "SELECT COMPANY_CODE, TABLE_SUBSET, MIN_WITHDRAWAL_AMT, MAX_WITHDRAWAL_AMT, MIN_PENALTY_AMT, MAX_PENALTY_AMT, ALLOW_SURR_YR_IND, NO_SURRS_ALLOWED, MIN_REM_BASE_IND, MIN_REMAINING_AMT, MIN_REMAINING_PCT FROM ";
    
    public void initialize()
    {
        setRowClass(TA33FRow.class);
        setTableName("TA33F");
        setTableId("A33");
        super.initialize();
    }
    
    public void initializeColumnDescriptors()
    {
        super.initializeColumnDescriptors();
        addColumnDescriptor(new ColumnDescriptor(this,"getCompanyCode","setCompanyCode","COMPANY_CODE,1,1,3,0,true|,0,CHAR,1,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getTableSubset","setTableSubset","TABLE_SUBSET,1,2,16,0,true|,0,CHAR,1,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getMinWithdrawalAmt","setMinWithdrawalAmt","MIN_WITHDRAWAL_AMT,3,3,11,2,false|,0,DOUBLE,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getMaxWithdrawalAmt","setMaxWithdrawalAmt","MAX_WITHDRAWAL_AMT,3,4,11,2,false|,0,DOUBLE,4,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getMinPenaltyAmt","setMinPenaltyAmt","MIN_PENALTY_AMT,3,5,11,2,false|,0,DOUBLE,2,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getMaxPenaltyAmt","setMaxPenaltyAmt","MAX_PENALTY_AMT,3,6,11,2,false|,0,DOUBLE,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getAllowSurrYrInd","setAllowSurrYrInd","ALLOW_SURR_YR_IND,1,7,1,0,false|,0,CHAR,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getNoSurrsAllowed","setNoSurrsAllowed","NO_SURRS_ALLOWED,3,8,2,0,false|,0,INTEGER,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getMinRemBaseInd","setMinRemBaseInd","MIN_REM_BASE_IND,1,9,1,0,false|,0,CHAR,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getMinRemainingAmt","setMinRemainingAmt","MIN_REMAINING_AMT,3,10,11,2,false|,0,DOUBLE,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getMinRemainingPct","setMinRemainingPct","MIN_REMAINING_PCT,3,11,6,3,false|,0,DOUBLE,0,null,null,null,null,null"));
    }
    
    public String getPagingSQL(String schemaName,boolean isSubsetMode,boolean isNext, boolean locator)
    {
        String pagingWhere = null;
        String order = null;
        if (isNext) {
            if (isSubsetMode)
                if (locator)
                    pagingWhere = ".TA33F WHERE (COMPANY_CODE = :company_code) AND (TABLE_SUBSET = :table_subset)";
                else 
                    pagingWhere = ".TA33F WHERE (COMPANY_CODE = :company_code) AND (TABLE_SUBSET = :table_subset)";
            else
                if (locator)
                    pagingWhere = ".TA33F WHERE (COMPANY_CODE = :company_code) AND ((TABLE_SUBSET > :table_subset) OR (TABLE_SUBSET = :table_subset)) ";
                else
                    pagingWhere = ".TA33F WHERE (COMPANY_CODE = :company_code) AND ((TABLE_SUBSET > :table_subset)) ";
            order = " ORDER BY COMPANY_CODE, TABLE_SUBSET";
        }
        else {
            if (isSubsetMode)
                pagingWhere = ".TA33F WHERE (COMPANY_CODE = :company_code) AND (TABLE_SUBSET = :table_subset)";
            else
                pagingWhere = ".TA33F WHERE (COMPANY_CODE = :company_code) AND ((TABLE_SUBSET < :table_subset)) ";
            order = " ORDER BY COMPANY_CODE DESC, TABLE_SUBSET DESC";
        }
        return pagingSql + schemaName + pagingWhere + order;
    }
    
    public String prepareInsertStmt(String schemaName) {
        StringBuffer sb = new StringBuffer();
        sb.append("INSERT INTO ").append(schemaName);
        sb.append(".TA33F ( ");
        sb.append("COMPANY_CODE, TABLE_SUBSET, MIN_WITHDRAWAL_AMT, MAX_WITHDRAWAL_AMT, MIN_PENALTY_AMT, MAX_PENALTY_AMT, ALLOW_SURR_YR_IND, NO_SURRS_ALLOWED, MIN_REM_BASE_IND, MIN_REMAINING_AMT, MIN_REMAINING_PCT )");
        sb.append(" VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ? )");
        return sb.toString();
    }
    
    public String prepareUpdateStmt(String schemaName)
    {
        StringBuffer sb = new StringBuffer();
        sb.append("UPDATE ").append(schemaName);
        sb.append(".TA33F ");
        sb.append(" SET COMPANY_CODE = ?, TABLE_SUBSET = ?, MIN_WITHDRAWAL_AMT = ?, MAX_WITHDRAWAL_AMT = ?, MIN_PENALTY_AMT = ?, MAX_PENALTY_AMT = ?, ALLOW_SURR_YR_IND = ?, NO_SURRS_ALLOWED = ?, MIN_REM_BASE_IND = ?, MIN_REMAINING_AMT = ?, MIN_REMAINING_PCT = ?");
        sb.append(" WHERE COMPANY_CODE = ? AND TABLE_SUBSET = ?");
        return sb.toString();
    }
    
    public String prepareDeleteStmt(String schemaName)
    {
        StringBuffer sb = new StringBuffer();
        sb.append("DELETE FROM ").append(schemaName);
        sb.append(".TA33F ");
        sb.append(" WHERE COMPANY_CODE = ? AND TABLE_SUBSET = ?");
        return sb.toString();
    }
}
