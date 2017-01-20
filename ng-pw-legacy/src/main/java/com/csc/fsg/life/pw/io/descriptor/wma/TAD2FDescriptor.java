package com.csc.fsg.life.pw.io.descriptor.wma;

import com.csc.fsg.life.pw.io.*;

public class TAD2FDescriptor
    extends TableDescriptor
{
    private static final String pagingSql = "SELECT COMPANY_CODE, TABLE_SUBSET, EFFECTIVE_DATE, MEMO_CODE, MIN_LOAN_AMOUNT, MAX_LOAN_AMOUNT, MAX_PERCENT, MAX_PERIOD, RETIRE_DT_CHK_IND, AUTO_GEN_REPAY_IND, ALOW_CSC_RPAY_MISS, ALOW_CUM_RPAY_MISS FROM ";
    
    public void initialize()
    {
        setRowClass(TAD2FRow.class);
        setTableName("TAD2F");
        setTableId("AD2");
        super.initialize();
    }
    
    public void initializeColumnDescriptors()
    {
        super.initializeColumnDescriptors();
        addColumnDescriptor(new ColumnDescriptor(this,"getCompanyCode","setCompanyCode","COMPANY_CODE,1,1,3,0,true|,0,CHAR,1,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getTableSubset","setTableSubset","TABLE_SUBSET,1,2,16,0,true|,0,CHAR,1,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getEffectiveDate","setEffectiveDate","EFFECTIVE_DATE,91,3,10,0,true|,0,DATE,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getMemoCode","setMemoCode","MEMO_CODE,1,4,2,0,true|,0,CHAR,4,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getMinLoanAmount","setMinLoanAmount","MIN_LOAN_AMOUNT,3,5,11,2,false|,0,DOUBLE,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getMaxLoanAmount","setMaxLoanAmount","MAX_LOAN_AMOUNT,3,6,11,2,false|,0,DOUBLE,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getMaxPercent","setMaxPercent","MAX_PERCENT,3,7,5,4,false|,0,DOUBLE,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getMaxPeriod","setMaxPeriod","MAX_PERIOD,3,8,3,0,false|,0,INTEGER,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getRetireDtChkInd","setRetireDtChkInd","RETIRE_DT_CHK_IND,1,9,1,0,false|,0,CHAR,2,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getAutoGenRepayInd","setAutoGenRepayInd","AUTO_GEN_REPAY_IND,1,10,1,0,false|,0,CHAR,2,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getAlowCscRpayMiss","setAlowCscRpayMiss","ALOW_CSC_RPAY_MISS,3,11,3,0,false|,0,INTEGER,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getAlowCumRpayMiss","setAlowCumRpayMiss","ALOW_CUM_RPAY_MISS,3,12,3,0,false|,0,INTEGER,0,null,null,null,null,null"));
    }
    
    public String getPagingSQL(String schemaName,boolean isSubsetMode,boolean isNext, boolean locator)
    {
        String pagingWhere = null;
        String order = null;
        if (isNext) {
            if (isSubsetMode)
                if (locator)
                    pagingWhere = ".TAD2F WHERE (COMPANY_CODE = :company_code) AND (TABLE_SUBSET = :table_subset) AND ((EFFECTIVE_DATE > :effective_date) OR (EFFECTIVE_DATE = :effective_date AND MEMO_CODE > :memo_code) OR (EFFECTIVE_DATE = :effective_date AND MEMO_CODE = :memo_code)) ";
                else 
                    pagingWhere = ".TAD2F WHERE (COMPANY_CODE = :company_code) AND (TABLE_SUBSET = :table_subset) AND ((EFFECTIVE_DATE > :effective_date) OR (EFFECTIVE_DATE = :effective_date AND MEMO_CODE > :memo_code)) ";
            else
                if (locator)
                    pagingWhere = ".TAD2F WHERE (COMPANY_CODE = :company_code) AND ((TABLE_SUBSET > :table_subset) OR (TABLE_SUBSET = :table_subset AND EFFECTIVE_DATE > :effective_date) OR (TABLE_SUBSET = :table_subset AND EFFECTIVE_DATE = :effective_date AND MEMO_CODE > :memo_code) OR (TABLE_SUBSET = :table_subset AND EFFECTIVE_DATE = :effective_date AND MEMO_CODE = :memo_code)) ";
                else
                    pagingWhere = ".TAD2F WHERE (COMPANY_CODE = :company_code) AND ((TABLE_SUBSET > :table_subset) OR (TABLE_SUBSET = :table_subset AND EFFECTIVE_DATE > :effective_date) OR (TABLE_SUBSET = :table_subset AND EFFECTIVE_DATE = :effective_date AND MEMO_CODE > :memo_code)) ";
            order = " ORDER BY COMPANY_CODE, TABLE_SUBSET, EFFECTIVE_DATE, MEMO_CODE";
        }
        else {
            if (isSubsetMode)
                pagingWhere = ".TAD2F WHERE (COMPANY_CODE = :company_code) AND (TABLE_SUBSET = :table_subset) AND ((EFFECTIVE_DATE < :effective_date) OR (EFFECTIVE_DATE = :effective_date AND MEMO_CODE < :memo_code)) ";
            else
                pagingWhere = ".TAD2F WHERE (COMPANY_CODE = :company_code) AND ((TABLE_SUBSET < :table_subset) OR (TABLE_SUBSET = :table_subset AND EFFECTIVE_DATE < :effective_date) OR (TABLE_SUBSET = :table_subset AND EFFECTIVE_DATE = :effective_date AND MEMO_CODE < :memo_code)) ";
            order = " ORDER BY COMPANY_CODE DESC, TABLE_SUBSET DESC, EFFECTIVE_DATE DESC, MEMO_CODE DESC";
        }
        return pagingSql + schemaName + pagingWhere + order;
    }
    
    public String prepareInsertStmt(String schemaName) {
        StringBuffer sb = new StringBuffer();
        sb.append("INSERT INTO ").append(schemaName);
        sb.append(".TAD2F ( ");
        sb.append("COMPANY_CODE, TABLE_SUBSET, EFFECTIVE_DATE, MEMO_CODE, MIN_LOAN_AMOUNT, MAX_LOAN_AMOUNT, MAX_PERCENT, MAX_PERIOD, RETIRE_DT_CHK_IND, AUTO_GEN_REPAY_IND, ALOW_CSC_RPAY_MISS, ALOW_CUM_RPAY_MISS )");
        sb.append(" VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ? )");
        return sb.toString();
    }
    
    public String prepareUpdateStmt(String schemaName)
    {
        StringBuffer sb = new StringBuffer();
        sb.append("UPDATE ").append(schemaName);
        sb.append(".TAD2F ");
        sb.append(" SET COMPANY_CODE = ?, TABLE_SUBSET = ?, EFFECTIVE_DATE = ?, MEMO_CODE = ?, MIN_LOAN_AMOUNT = ?, MAX_LOAN_AMOUNT = ?, MAX_PERCENT = ?, MAX_PERIOD = ?, RETIRE_DT_CHK_IND = ?, AUTO_GEN_REPAY_IND = ?, ALOW_CSC_RPAY_MISS = ?, ALOW_CUM_RPAY_MISS = ?");
        sb.append(" WHERE COMPANY_CODE = ? AND TABLE_SUBSET = ? AND EFFECTIVE_DATE = ? AND MEMO_CODE = ?");
        return sb.toString();
    }
    
    public String prepareDeleteStmt(String schemaName)
    {
        StringBuffer sb = new StringBuffer();
        sb.append("DELETE FROM ").append(schemaName);
        sb.append(".TAD2F ");
        sb.append(" WHERE COMPANY_CODE = ? AND TABLE_SUBSET = ? AND EFFECTIVE_DATE = ? AND MEMO_CODE = ?");
        return sb.toString();
    }
}
