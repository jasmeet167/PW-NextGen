package com.csc.fsg.life.pw.io.descriptor.wma;

import com.csc.fsg.life.pw.io.*;

public class TA22FDescriptor
    extends TableDescriptor
{
    private static final String pagingSql = "SELECT COMPANY_CODE, TABLE_SUBSET, EFFECTIVE_DATE, TRANSACTION_CODE, PAYMENT_MODE, PAYMENT_METHOD, CUM_PREM_TO_DT, MAX_TRX_FEE_TO_DT, MEMO_CODE, TRX_FEE_AMT, TRX_FEE_PCT FROM ";
    
    public void initialize()
    {
        setRowClass(TA22FRow.class);
        setTableName("TA22F");
        setTableId("A22");
        super.initialize();
    }
    
    public void initializeColumnDescriptors()
    {
        super.initializeColumnDescriptors();
        addColumnDescriptor(new ColumnDescriptor(this,"getCompanyCode","setCompanyCode","COMPANY_CODE,1,1,3,0,true|,0,CHAR,1,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getTableSubset","setTableSubset","TABLE_SUBSET,1,2,16,0,true|,0,CHAR,1,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getEffectiveDate","setEffectiveDate","EFFECTIVE_DATE,91,3,10,0,true|,0,DATE,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getTransactionCode","setTransactionCode","TRANSACTION_CODE,1,4,4,0,true|,0,CHAR,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getPaymentMode","setPaymentMode","PAYMENT_MODE,3,5,2,0,true|,0,INTEGER,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getPaymentMethod","setPaymentMethod","PAYMENT_METHOD,1,6,1,0,true|,0,CHAR,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getCumPremToDt","setCumPremToDt","CUM_PREM_TO_DT,3,7,11,2,true|,0,DOUBLE,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getMaxTrxFeeToDt","setMaxTrxFeeToDt","MAX_TRX_FEE_TO_DT,3,8,11,2,true|,0,DOUBLE,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getMemoCode","setMemoCode","MEMO_CODE,1,9,2,0,true|,0,CHAR,4,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getTrxFeeAmt","setTrxFeeAmt","TRX_FEE_AMT,3,10,7,2,false|,0,DOUBLE,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getTrxFeePct","setTrxFeePct","TRX_FEE_PCT,3,11,6,3,false|,0,DOUBLE,0,null,null,null,null,null"));
    }
    
    public String getPagingSQL(String schemaName,boolean isSubsetMode,boolean isNext, boolean locator)
    {
        String pagingWhere = null;
        String order = null;
        if (isNext) {
            if (isSubsetMode)
                if (locator)
                    pagingWhere = ".TA22F WHERE (COMPANY_CODE = :company_code) AND (TABLE_SUBSET = :table_subset) AND ((EFFECTIVE_DATE > :effective_date) OR (EFFECTIVE_DATE = :effective_date AND TRANSACTION_CODE > :transaction_code) OR (EFFECTIVE_DATE = :effective_date AND TRANSACTION_CODE = :transaction_code AND PAYMENT_MODE > :payment_mode) OR (EFFECTIVE_DATE = :effective_date AND TRANSACTION_CODE = :transaction_code AND PAYMENT_MODE = :payment_mode AND PAYMENT_METHOD > :payment_method) OR (EFFECTIVE_DATE = :effective_date AND TRANSACTION_CODE = :transaction_code AND PAYMENT_MODE = :payment_mode AND PAYMENT_METHOD = :payment_method AND CUM_PREM_TO_DT > :cum_prem_to_dt) OR (EFFECTIVE_DATE = :effective_date AND TRANSACTION_CODE = :transaction_code AND PAYMENT_MODE = :payment_mode AND PAYMENT_METHOD = :payment_method AND CUM_PREM_TO_DT = :cum_prem_to_dt AND MAX_TRX_FEE_TO_DT > :max_trx_fee_to_dt) OR (EFFECTIVE_DATE = :effective_date AND TRANSACTION_CODE = :transaction_code AND PAYMENT_MODE = :payment_mode AND PAYMENT_METHOD = :payment_method AND CUM_PREM_TO_DT = :cum_prem_to_dt AND MAX_TRX_FEE_TO_DT = :max_trx_fee_to_dt AND MEMO_CODE > :memo_code) OR (EFFECTIVE_DATE = :effective_date AND TRANSACTION_CODE = :transaction_code AND PAYMENT_MODE = :payment_mode AND PAYMENT_METHOD = :payment_method AND CUM_PREM_TO_DT = :cum_prem_to_dt AND MAX_TRX_FEE_TO_DT = :max_trx_fee_to_dt AND MEMO_CODE = :memo_code)) ";
                else 
                    pagingWhere = ".TA22F WHERE (COMPANY_CODE = :company_code) AND (TABLE_SUBSET = :table_subset) AND ((EFFECTIVE_DATE > :effective_date) OR (EFFECTIVE_DATE = :effective_date AND TRANSACTION_CODE > :transaction_code) OR (EFFECTIVE_DATE = :effective_date AND TRANSACTION_CODE = :transaction_code AND PAYMENT_MODE > :payment_mode) OR (EFFECTIVE_DATE = :effective_date AND TRANSACTION_CODE = :transaction_code AND PAYMENT_MODE = :payment_mode AND PAYMENT_METHOD > :payment_method) OR (EFFECTIVE_DATE = :effective_date AND TRANSACTION_CODE = :transaction_code AND PAYMENT_MODE = :payment_mode AND PAYMENT_METHOD = :payment_method AND CUM_PREM_TO_DT > :cum_prem_to_dt) OR (EFFECTIVE_DATE = :effective_date AND TRANSACTION_CODE = :transaction_code AND PAYMENT_MODE = :payment_mode AND PAYMENT_METHOD = :payment_method AND CUM_PREM_TO_DT = :cum_prem_to_dt AND MAX_TRX_FEE_TO_DT > :max_trx_fee_to_dt) OR (EFFECTIVE_DATE = :effective_date AND TRANSACTION_CODE = :transaction_code AND PAYMENT_MODE = :payment_mode AND PAYMENT_METHOD = :payment_method AND CUM_PREM_TO_DT = :cum_prem_to_dt AND MAX_TRX_FEE_TO_DT = :max_trx_fee_to_dt AND MEMO_CODE > :memo_code)) ";
            else
                if (locator)
                    pagingWhere = ".TA22F WHERE (COMPANY_CODE = :company_code) AND ((TABLE_SUBSET > :table_subset) OR (TABLE_SUBSET = :table_subset AND EFFECTIVE_DATE > :effective_date) OR (TABLE_SUBSET = :table_subset AND EFFECTIVE_DATE = :effective_date AND TRANSACTION_CODE > :transaction_code) OR (TABLE_SUBSET = :table_subset AND EFFECTIVE_DATE = :effective_date AND TRANSACTION_CODE = :transaction_code AND PAYMENT_MODE > :payment_mode) OR (TABLE_SUBSET = :table_subset AND EFFECTIVE_DATE = :effective_date AND TRANSACTION_CODE = :transaction_code AND PAYMENT_MODE = :payment_mode AND PAYMENT_METHOD > :payment_method) OR (TABLE_SUBSET = :table_subset AND EFFECTIVE_DATE = :effective_date AND TRANSACTION_CODE = :transaction_code AND PAYMENT_MODE = :payment_mode AND PAYMENT_METHOD = :payment_method AND CUM_PREM_TO_DT > :cum_prem_to_dt) OR (TABLE_SUBSET = :table_subset AND EFFECTIVE_DATE = :effective_date AND TRANSACTION_CODE = :transaction_code AND PAYMENT_MODE = :payment_mode AND PAYMENT_METHOD = :payment_method AND CUM_PREM_TO_DT = :cum_prem_to_dt AND MAX_TRX_FEE_TO_DT > :max_trx_fee_to_dt) OR (TABLE_SUBSET = :table_subset AND EFFECTIVE_DATE = :effective_date AND TRANSACTION_CODE = :transaction_code AND PAYMENT_MODE = :payment_mode AND PAYMENT_METHOD = :payment_method AND CUM_PREM_TO_DT = :cum_prem_to_dt AND MAX_TRX_FEE_TO_DT = :max_trx_fee_to_dt AND MEMO_CODE > :memo_code) OR (TABLE_SUBSET = :table_subset AND EFFECTIVE_DATE = :effective_date AND TRANSACTION_CODE = :transaction_code AND PAYMENT_MODE = :payment_mode AND PAYMENT_METHOD = :payment_method AND CUM_PREM_TO_DT = :cum_prem_to_dt AND MAX_TRX_FEE_TO_DT = :max_trx_fee_to_dt AND MEMO_CODE = :memo_code)) ";
                else
                    pagingWhere = ".TA22F WHERE (COMPANY_CODE = :company_code) AND ((TABLE_SUBSET > :table_subset) OR (TABLE_SUBSET = :table_subset AND EFFECTIVE_DATE > :effective_date) OR (TABLE_SUBSET = :table_subset AND EFFECTIVE_DATE = :effective_date AND TRANSACTION_CODE > :transaction_code) OR (TABLE_SUBSET = :table_subset AND EFFECTIVE_DATE = :effective_date AND TRANSACTION_CODE = :transaction_code AND PAYMENT_MODE > :payment_mode) OR (TABLE_SUBSET = :table_subset AND EFFECTIVE_DATE = :effective_date AND TRANSACTION_CODE = :transaction_code AND PAYMENT_MODE = :payment_mode AND PAYMENT_METHOD > :payment_method) OR (TABLE_SUBSET = :table_subset AND EFFECTIVE_DATE = :effective_date AND TRANSACTION_CODE = :transaction_code AND PAYMENT_MODE = :payment_mode AND PAYMENT_METHOD = :payment_method AND CUM_PREM_TO_DT > :cum_prem_to_dt) OR (TABLE_SUBSET = :table_subset AND EFFECTIVE_DATE = :effective_date AND TRANSACTION_CODE = :transaction_code AND PAYMENT_MODE = :payment_mode AND PAYMENT_METHOD = :payment_method AND CUM_PREM_TO_DT = :cum_prem_to_dt AND MAX_TRX_FEE_TO_DT > :max_trx_fee_to_dt) OR (TABLE_SUBSET = :table_subset AND EFFECTIVE_DATE = :effective_date AND TRANSACTION_CODE = :transaction_code AND PAYMENT_MODE = :payment_mode AND PAYMENT_METHOD = :payment_method AND CUM_PREM_TO_DT = :cum_prem_to_dt AND MAX_TRX_FEE_TO_DT = :max_trx_fee_to_dt AND MEMO_CODE > :memo_code)) ";
            order = " ORDER BY COMPANY_CODE, TABLE_SUBSET, EFFECTIVE_DATE, TRANSACTION_CODE, PAYMENT_MODE, PAYMENT_METHOD, CUM_PREM_TO_DT, MAX_TRX_FEE_TO_DT, MEMO_CODE";
        }
        else {
            if (isSubsetMode)
                pagingWhere = ".TA22F WHERE (COMPANY_CODE = :company_code) AND (TABLE_SUBSET = :table_subset) AND ((EFFECTIVE_DATE < :effective_date) OR (EFFECTIVE_DATE = :effective_date AND TRANSACTION_CODE < :transaction_code) OR (EFFECTIVE_DATE = :effective_date AND TRANSACTION_CODE = :transaction_code AND PAYMENT_MODE < :payment_mode) OR (EFFECTIVE_DATE = :effective_date AND TRANSACTION_CODE = :transaction_code AND PAYMENT_MODE = :payment_mode AND PAYMENT_METHOD < :payment_method) OR (EFFECTIVE_DATE = :effective_date AND TRANSACTION_CODE = :transaction_code AND PAYMENT_MODE = :payment_mode AND PAYMENT_METHOD = :payment_method AND CUM_PREM_TO_DT < :cum_prem_to_dt) OR (EFFECTIVE_DATE = :effective_date AND TRANSACTION_CODE = :transaction_code AND PAYMENT_MODE = :payment_mode AND PAYMENT_METHOD = :payment_method AND CUM_PREM_TO_DT = :cum_prem_to_dt AND MAX_TRX_FEE_TO_DT < :max_trx_fee_to_dt) OR (EFFECTIVE_DATE = :effective_date AND TRANSACTION_CODE = :transaction_code AND PAYMENT_MODE = :payment_mode AND PAYMENT_METHOD = :payment_method AND CUM_PREM_TO_DT = :cum_prem_to_dt AND MAX_TRX_FEE_TO_DT = :max_trx_fee_to_dt AND MEMO_CODE < :memo_code)) ";
            else
                pagingWhere = ".TA22F WHERE (COMPANY_CODE = :company_code) AND ((TABLE_SUBSET < :table_subset) OR (TABLE_SUBSET = :table_subset AND EFFECTIVE_DATE < :effective_date) OR (TABLE_SUBSET = :table_subset AND EFFECTIVE_DATE = :effective_date AND TRANSACTION_CODE < :transaction_code) OR (TABLE_SUBSET = :table_subset AND EFFECTIVE_DATE = :effective_date AND TRANSACTION_CODE = :transaction_code AND PAYMENT_MODE < :payment_mode) OR (TABLE_SUBSET = :table_subset AND EFFECTIVE_DATE = :effective_date AND TRANSACTION_CODE = :transaction_code AND PAYMENT_MODE = :payment_mode AND PAYMENT_METHOD < :payment_method) OR (TABLE_SUBSET = :table_subset AND EFFECTIVE_DATE = :effective_date AND TRANSACTION_CODE = :transaction_code AND PAYMENT_MODE = :payment_mode AND PAYMENT_METHOD = :payment_method AND CUM_PREM_TO_DT < :cum_prem_to_dt) OR (TABLE_SUBSET = :table_subset AND EFFECTIVE_DATE = :effective_date AND TRANSACTION_CODE = :transaction_code AND PAYMENT_MODE = :payment_mode AND PAYMENT_METHOD = :payment_method AND CUM_PREM_TO_DT = :cum_prem_to_dt AND MAX_TRX_FEE_TO_DT < :max_trx_fee_to_dt) OR (TABLE_SUBSET = :table_subset AND EFFECTIVE_DATE = :effective_date AND TRANSACTION_CODE = :transaction_code AND PAYMENT_MODE = :payment_mode AND PAYMENT_METHOD = :payment_method AND CUM_PREM_TO_DT = :cum_prem_to_dt AND MAX_TRX_FEE_TO_DT = :max_trx_fee_to_dt AND MEMO_CODE < :memo_code)) ";
            order = " ORDER BY COMPANY_CODE DESC, TABLE_SUBSET DESC, EFFECTIVE_DATE DESC, TRANSACTION_CODE DESC, PAYMENT_MODE DESC, PAYMENT_METHOD DESC, CUM_PREM_TO_DT DESC, MAX_TRX_FEE_TO_DT DESC, MEMO_CODE DESC";
        }
        return pagingSql + schemaName + pagingWhere + order;
    }
    
    public String prepareInsertStmt(String schemaName) {
        StringBuffer sb = new StringBuffer();
        sb.append("INSERT INTO ").append(schemaName);
        sb.append(".TA22F ( ");
        sb.append("COMPANY_CODE, TABLE_SUBSET, EFFECTIVE_DATE, TRANSACTION_CODE, PAYMENT_MODE, PAYMENT_METHOD, CUM_PREM_TO_DT, MAX_TRX_FEE_TO_DT, MEMO_CODE, TRX_FEE_AMT, TRX_FEE_PCT )");
        sb.append(" VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ? )");
        return sb.toString();
    }
    
    public String prepareUpdateStmt(String schemaName)
    {
        StringBuffer sb = new StringBuffer();
        sb.append("UPDATE ").append(schemaName);
        sb.append(".TA22F ");
        sb.append(" SET COMPANY_CODE = ?, TABLE_SUBSET = ?, EFFECTIVE_DATE = ?, TRANSACTION_CODE = ?, PAYMENT_MODE = ?, PAYMENT_METHOD = ?, CUM_PREM_TO_DT = ?, MAX_TRX_FEE_TO_DT = ?, MEMO_CODE = ?, TRX_FEE_AMT = ?, TRX_FEE_PCT = ?");
        sb.append(" WHERE COMPANY_CODE = ? AND TABLE_SUBSET = ? AND EFFECTIVE_DATE = ? AND TRANSACTION_CODE = ? AND PAYMENT_MODE = ? AND PAYMENT_METHOD = ? AND CUM_PREM_TO_DT = ? AND MAX_TRX_FEE_TO_DT = ? AND MEMO_CODE = ?");
        return sb.toString();
    }
    
    public String prepareDeleteStmt(String schemaName)
    {
        StringBuffer sb = new StringBuffer();
        sb.append("DELETE FROM ").append(schemaName);
        sb.append(".TA22F ");
        sb.append(" WHERE COMPANY_CODE = ? AND TABLE_SUBSET = ? AND EFFECTIVE_DATE = ? AND TRANSACTION_CODE = ? AND PAYMENT_MODE = ? AND PAYMENT_METHOD = ? AND CUM_PREM_TO_DT = ? AND MAX_TRX_FEE_TO_DT = ? AND MEMO_CODE = ?");
        return sb.toString();
    }
}
