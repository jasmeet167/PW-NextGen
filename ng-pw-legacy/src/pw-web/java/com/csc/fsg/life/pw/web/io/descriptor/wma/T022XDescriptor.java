package com.csc.fsg.life.pw.web.io.descriptor.wma;

import com.csc.fsg.life.pw.web.io.*;

public class T022XDescriptor
    extends TableDescriptor
{
    private static final String pagingSql = "SELECT COMPANY_CODE, PRODUCT_PREFIX, TABLE_SUBSET, EFFECTIVE_DATE, TRANSACTION_CODE, SEX_CODE, MX_CVG_UNIT, CUM_PREM_TO_DT, CUM_TRX_FEE_TO_DT, MEMO_CODE, TRX_FEE_AMT, TRX_FEE_PCT, COMM_TRX_FEE_IND FROM ";
    
    public void initialize()
    {
        setRowClass(T022XRow.class);
        setTableName("T022X");
        setTableId("022");
        super.initialize();
    }
    
    public void initializeColumnDescriptors()
    {
        super.initializeColumnDescriptors();
        addColumnDescriptor(new ColumnDescriptor(this,"getCompanyCode","setCompanyCode","COMPANY_CODE,1,1,3,0,true|T,0,CHAR,1,null,null,null,null,null|U,0,CHAR,1,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getProductPrefix","setProductPrefix","PRODUCT_PREFIX,1,2,1,0,true|T,0,CHAR,1,null,null,null,null,null|U,0,CHAR,1,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getTableSubset","setTableSubset","TABLE_SUBSET,1,3,16,0,true|T,0,CHAR,1,null,null,null,null,null|U,0,CHAR,1,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getEffectiveDate","setEffectiveDate","EFFECTIVE_DATE,91,4,10,0,true|T,0,DATE,0,null,null,null,null,null|U,0,DATE,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getTransactionCode","setTransactionCode","TRANSACTION_CODE,1,5,4,0,true|T,0,CHAR,0,null,null,null,null,null|U,0,CHAR,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getSexCode","setSexCode","SEX_CODE,1,6,1,0,true|T,0,CHAR,0,null,null,null,null,null|U,0,CHAR,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getMxCvgUnit","setMxCvgUnit","MX_CVG_UNIT,3,7,11,5,true|T,0,DOUBLE,0,null,null,null,null,null|U,0,DOUBLE,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getCumPremToDt","setCumPremToDt","CUM_PREM_TO_DT,3,8,11,2,true|T,0,DOUBLE,0,null,null,null,null,null|U,0,DOUBLE,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getCumTrxFeeToDt","setCumTrxFeeToDt","CUM_TRX_FEE_TO_DT,3,9,11,2,true|T,0,DOUBLE,0,null,null,null,null,null|U,0,DOUBLE,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getMemoCode","setMemoCode","MEMO_CODE,1,10,2,0,true|T,0,CHAR,4,null,null,null,null,null|U,0,CHAR,4,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getTrxFeeAmt","setTrxFeeAmt","TRX_FEE_AMT,3,11,7,2,false|T,0,DOUBLE,0,null,null,null,null,null|U,0,DOUBLE,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getTrxFeePct","setTrxFeePct","TRX_FEE_PCT,3,12,7,5,false|T,0,DOUBLE,0,null,null,null,null,null|U,0,DOUBLE,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getCommTrxFeeInd","setCommTrxFeeInd","COMM_TRX_FEE_IND,1,13,1,0,false|T,0,CHAR,0,null,null,null,null,null|U,0,CHAR,2,null,null,null,null,null"));
    }
    
    public String getPagingSQL(String schemaName,boolean isSubsetMode,boolean isNext, boolean locator)
    {
        String pagingWhere = null;
        String order = null;
        if (isNext) {
            if (isSubsetMode)
                if (locator)
                    pagingWhere = ".T022X WHERE (COMPANY_CODE = :company_code) AND (PRODUCT_PREFIX = :product_prefix) AND (TABLE_SUBSET = :table_subset) AND ((EFFECTIVE_DATE > :effective_date) OR (EFFECTIVE_DATE = :effective_date AND TRANSACTION_CODE > :transaction_code) OR (EFFECTIVE_DATE = :effective_date AND TRANSACTION_CODE = :transaction_code AND SEX_CODE > :sex_code) OR (EFFECTIVE_DATE = :effective_date AND TRANSACTION_CODE = :transaction_code AND SEX_CODE = :sex_code AND MX_CVG_UNIT > :mx_cvg_unit) OR (EFFECTIVE_DATE = :effective_date AND TRANSACTION_CODE = :transaction_code AND SEX_CODE = :sex_code AND MX_CVG_UNIT = :mx_cvg_unit AND CUM_PREM_TO_DT > :cum_prem_to_dt) OR (EFFECTIVE_DATE = :effective_date AND TRANSACTION_CODE = :transaction_code AND SEX_CODE = :sex_code AND MX_CVG_UNIT = :mx_cvg_unit AND CUM_PREM_TO_DT = :cum_prem_to_dt AND CUM_TRX_FEE_TO_DT > :cum_trx_fee_to_dt) OR (EFFECTIVE_DATE = :effective_date AND TRANSACTION_CODE = :transaction_code AND SEX_CODE = :sex_code AND MX_CVG_UNIT = :mx_cvg_unit AND CUM_PREM_TO_DT = :cum_prem_to_dt AND CUM_TRX_FEE_TO_DT = :cum_trx_fee_to_dt AND MEMO_CODE > :memo_code) OR (EFFECTIVE_DATE = :effective_date AND TRANSACTION_CODE = :transaction_code AND SEX_CODE = :sex_code AND MX_CVG_UNIT = :mx_cvg_unit AND CUM_PREM_TO_DT = :cum_prem_to_dt AND CUM_TRX_FEE_TO_DT = :cum_trx_fee_to_dt AND MEMO_CODE = :memo_code)) ";
                else 
                    pagingWhere = ".T022X WHERE (COMPANY_CODE = :company_code) AND (PRODUCT_PREFIX = :product_prefix) AND (TABLE_SUBSET = :table_subset) AND ((EFFECTIVE_DATE > :effective_date) OR (EFFECTIVE_DATE = :effective_date AND TRANSACTION_CODE > :transaction_code) OR (EFFECTIVE_DATE = :effective_date AND TRANSACTION_CODE = :transaction_code AND SEX_CODE > :sex_code) OR (EFFECTIVE_DATE = :effective_date AND TRANSACTION_CODE = :transaction_code AND SEX_CODE = :sex_code AND MX_CVG_UNIT > :mx_cvg_unit) OR (EFFECTIVE_DATE = :effective_date AND TRANSACTION_CODE = :transaction_code AND SEX_CODE = :sex_code AND MX_CVG_UNIT = :mx_cvg_unit AND CUM_PREM_TO_DT > :cum_prem_to_dt) OR (EFFECTIVE_DATE = :effective_date AND TRANSACTION_CODE = :transaction_code AND SEX_CODE = :sex_code AND MX_CVG_UNIT = :mx_cvg_unit AND CUM_PREM_TO_DT = :cum_prem_to_dt AND CUM_TRX_FEE_TO_DT > :cum_trx_fee_to_dt) OR (EFFECTIVE_DATE = :effective_date AND TRANSACTION_CODE = :transaction_code AND SEX_CODE = :sex_code AND MX_CVG_UNIT = :mx_cvg_unit AND CUM_PREM_TO_DT = :cum_prem_to_dt AND CUM_TRX_FEE_TO_DT = :cum_trx_fee_to_dt AND MEMO_CODE > :memo_code)) ";
            else
                if (locator)
                    pagingWhere = ".T022X WHERE (COMPANY_CODE = :company_code) AND ((PRODUCT_PREFIX > :product_prefix) OR (PRODUCT_PREFIX = :product_prefix AND TABLE_SUBSET > :table_subset) OR (PRODUCT_PREFIX = :product_prefix AND TABLE_SUBSET = :table_subset AND EFFECTIVE_DATE > :effective_date) OR (PRODUCT_PREFIX = :product_prefix AND TABLE_SUBSET = :table_subset AND EFFECTIVE_DATE = :effective_date AND TRANSACTION_CODE > :transaction_code) OR (PRODUCT_PREFIX = :product_prefix AND TABLE_SUBSET = :table_subset AND EFFECTIVE_DATE = :effective_date AND TRANSACTION_CODE = :transaction_code AND SEX_CODE > :sex_code) OR (PRODUCT_PREFIX = :product_prefix AND TABLE_SUBSET = :table_subset AND EFFECTIVE_DATE = :effective_date AND TRANSACTION_CODE = :transaction_code AND SEX_CODE = :sex_code AND MX_CVG_UNIT > :mx_cvg_unit) OR (PRODUCT_PREFIX = :product_prefix AND TABLE_SUBSET = :table_subset AND EFFECTIVE_DATE = :effective_date AND TRANSACTION_CODE = :transaction_code AND SEX_CODE = :sex_code AND MX_CVG_UNIT = :mx_cvg_unit AND CUM_PREM_TO_DT > :cum_prem_to_dt) OR (PRODUCT_PREFIX = :product_prefix AND TABLE_SUBSET = :table_subset AND EFFECTIVE_DATE = :effective_date AND TRANSACTION_CODE = :transaction_code AND SEX_CODE = :sex_code AND MX_CVG_UNIT = :mx_cvg_unit AND CUM_PREM_TO_DT = :cum_prem_to_dt AND CUM_TRX_FEE_TO_DT > :cum_trx_fee_to_dt) OR (PRODUCT_PREFIX = :product_prefix AND TABLE_SUBSET = :table_subset AND EFFECTIVE_DATE = :effective_date AND TRANSACTION_CODE = :transaction_code AND SEX_CODE = :sex_code AND MX_CVG_UNIT = :mx_cvg_unit AND CUM_PREM_TO_DT = :cum_prem_to_dt AND CUM_TRX_FEE_TO_DT = :cum_trx_fee_to_dt AND MEMO_CODE > :memo_code) OR (PRODUCT_PREFIX = :product_prefix AND TABLE_SUBSET = :table_subset AND EFFECTIVE_DATE = :effective_date AND TRANSACTION_CODE = :transaction_code AND SEX_CODE = :sex_code AND MX_CVG_UNIT = :mx_cvg_unit AND CUM_PREM_TO_DT = :cum_prem_to_dt AND CUM_TRX_FEE_TO_DT = :cum_trx_fee_to_dt AND MEMO_CODE = :memo_code)) ";
                else
                    pagingWhere = ".T022X WHERE (COMPANY_CODE = :company_code) AND ((PRODUCT_PREFIX > :product_prefix) OR (PRODUCT_PREFIX = :product_prefix AND TABLE_SUBSET > :table_subset) OR (PRODUCT_PREFIX = :product_prefix AND TABLE_SUBSET = :table_subset AND EFFECTIVE_DATE > :effective_date) OR (PRODUCT_PREFIX = :product_prefix AND TABLE_SUBSET = :table_subset AND EFFECTIVE_DATE = :effective_date AND TRANSACTION_CODE > :transaction_code) OR (PRODUCT_PREFIX = :product_prefix AND TABLE_SUBSET = :table_subset AND EFFECTIVE_DATE = :effective_date AND TRANSACTION_CODE = :transaction_code AND SEX_CODE > :sex_code) OR (PRODUCT_PREFIX = :product_prefix AND TABLE_SUBSET = :table_subset AND EFFECTIVE_DATE = :effective_date AND TRANSACTION_CODE = :transaction_code AND SEX_CODE = :sex_code AND MX_CVG_UNIT > :mx_cvg_unit) OR (PRODUCT_PREFIX = :product_prefix AND TABLE_SUBSET = :table_subset AND EFFECTIVE_DATE = :effective_date AND TRANSACTION_CODE = :transaction_code AND SEX_CODE = :sex_code AND MX_CVG_UNIT = :mx_cvg_unit AND CUM_PREM_TO_DT > :cum_prem_to_dt) OR (PRODUCT_PREFIX = :product_prefix AND TABLE_SUBSET = :table_subset AND EFFECTIVE_DATE = :effective_date AND TRANSACTION_CODE = :transaction_code AND SEX_CODE = :sex_code AND MX_CVG_UNIT = :mx_cvg_unit AND CUM_PREM_TO_DT = :cum_prem_to_dt AND CUM_TRX_FEE_TO_DT > :cum_trx_fee_to_dt) OR (PRODUCT_PREFIX = :product_prefix AND TABLE_SUBSET = :table_subset AND EFFECTIVE_DATE = :effective_date AND TRANSACTION_CODE = :transaction_code AND SEX_CODE = :sex_code AND MX_CVG_UNIT = :mx_cvg_unit AND CUM_PREM_TO_DT = :cum_prem_to_dt AND CUM_TRX_FEE_TO_DT = :cum_trx_fee_to_dt AND MEMO_CODE > :memo_code)) ";
            order = " ORDER BY COMPANY_CODE, PRODUCT_PREFIX, TABLE_SUBSET, EFFECTIVE_DATE, TRANSACTION_CODE, SEX_CODE, MX_CVG_UNIT, CUM_PREM_TO_DT, CUM_TRX_FEE_TO_DT, MEMO_CODE";
        }
        else {
            if (isSubsetMode)
                pagingWhere = ".T022X WHERE (COMPANY_CODE = :company_code) AND (PRODUCT_PREFIX = :product_prefix) AND (TABLE_SUBSET = :table_subset) AND ((EFFECTIVE_DATE < :effective_date) OR (EFFECTIVE_DATE = :effective_date AND TRANSACTION_CODE < :transaction_code) OR (EFFECTIVE_DATE = :effective_date AND TRANSACTION_CODE = :transaction_code AND SEX_CODE < :sex_code) OR (EFFECTIVE_DATE = :effective_date AND TRANSACTION_CODE = :transaction_code AND SEX_CODE = :sex_code AND MX_CVG_UNIT < :mx_cvg_unit) OR (EFFECTIVE_DATE = :effective_date AND TRANSACTION_CODE = :transaction_code AND SEX_CODE = :sex_code AND MX_CVG_UNIT = :mx_cvg_unit AND CUM_PREM_TO_DT < :cum_prem_to_dt) OR (EFFECTIVE_DATE = :effective_date AND TRANSACTION_CODE = :transaction_code AND SEX_CODE = :sex_code AND MX_CVG_UNIT = :mx_cvg_unit AND CUM_PREM_TO_DT = :cum_prem_to_dt AND CUM_TRX_FEE_TO_DT < :cum_trx_fee_to_dt) OR (EFFECTIVE_DATE = :effective_date AND TRANSACTION_CODE = :transaction_code AND SEX_CODE = :sex_code AND MX_CVG_UNIT = :mx_cvg_unit AND CUM_PREM_TO_DT = :cum_prem_to_dt AND CUM_TRX_FEE_TO_DT = :cum_trx_fee_to_dt AND MEMO_CODE < :memo_code)) ";
            else
                pagingWhere = ".T022X WHERE (COMPANY_CODE = :company_code) AND ((PRODUCT_PREFIX < :product_prefix) OR (PRODUCT_PREFIX = :product_prefix AND TABLE_SUBSET < :table_subset) OR (PRODUCT_PREFIX = :product_prefix AND TABLE_SUBSET = :table_subset AND EFFECTIVE_DATE < :effective_date) OR (PRODUCT_PREFIX = :product_prefix AND TABLE_SUBSET = :table_subset AND EFFECTIVE_DATE = :effective_date AND TRANSACTION_CODE < :transaction_code) OR (PRODUCT_PREFIX = :product_prefix AND TABLE_SUBSET = :table_subset AND EFFECTIVE_DATE = :effective_date AND TRANSACTION_CODE = :transaction_code AND SEX_CODE < :sex_code) OR (PRODUCT_PREFIX = :product_prefix AND TABLE_SUBSET = :table_subset AND EFFECTIVE_DATE = :effective_date AND TRANSACTION_CODE = :transaction_code AND SEX_CODE = :sex_code AND MX_CVG_UNIT < :mx_cvg_unit) OR (PRODUCT_PREFIX = :product_prefix AND TABLE_SUBSET = :table_subset AND EFFECTIVE_DATE = :effective_date AND TRANSACTION_CODE = :transaction_code AND SEX_CODE = :sex_code AND MX_CVG_UNIT = :mx_cvg_unit AND CUM_PREM_TO_DT < :cum_prem_to_dt) OR (PRODUCT_PREFIX = :product_prefix AND TABLE_SUBSET = :table_subset AND EFFECTIVE_DATE = :effective_date AND TRANSACTION_CODE = :transaction_code AND SEX_CODE = :sex_code AND MX_CVG_UNIT = :mx_cvg_unit AND CUM_PREM_TO_DT = :cum_prem_to_dt AND CUM_TRX_FEE_TO_DT < :cum_trx_fee_to_dt) OR (PRODUCT_PREFIX = :product_prefix AND TABLE_SUBSET = :table_subset AND EFFECTIVE_DATE = :effective_date AND TRANSACTION_CODE = :transaction_code AND SEX_CODE = :sex_code AND MX_CVG_UNIT = :mx_cvg_unit AND CUM_PREM_TO_DT = :cum_prem_to_dt AND CUM_TRX_FEE_TO_DT = :cum_trx_fee_to_dt AND MEMO_CODE < :memo_code)) ";
            order = " ORDER BY COMPANY_CODE DESC, PRODUCT_PREFIX DESC, TABLE_SUBSET DESC, EFFECTIVE_DATE DESC, TRANSACTION_CODE DESC, SEX_CODE DESC, MX_CVG_UNIT DESC, CUM_PREM_TO_DT DESC, CUM_TRX_FEE_TO_DT DESC, MEMO_CODE DESC";
        }
        return pagingSql + schemaName + pagingWhere + order;
    }
    
    public String prepareInsertStmt(String schemaName) {
        StringBuffer sb = new StringBuffer();
        sb.append("INSERT INTO ").append(schemaName);
        sb.append(".T022X ( ");
        sb.append("COMPANY_CODE, PRODUCT_PREFIX, TABLE_SUBSET, EFFECTIVE_DATE, TRANSACTION_CODE, SEX_CODE, MX_CVG_UNIT, CUM_PREM_TO_DT, CUM_TRX_FEE_TO_DT, MEMO_CODE, TRX_FEE_AMT, TRX_FEE_PCT, COMM_TRX_FEE_IND )");
        sb.append(" VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ? )");
        return sb.toString();
    }
    
    public String prepareUpdateStmt(String schemaName)
    {
        StringBuffer sb = new StringBuffer();
        sb.append("UPDATE ").append(schemaName);
        sb.append(".T022X ");
        sb.append(" SET COMPANY_CODE = ?, PRODUCT_PREFIX = ?, TABLE_SUBSET = ?, EFFECTIVE_DATE = ?, TRANSACTION_CODE = ?, SEX_CODE = ?, MX_CVG_UNIT = ?, CUM_PREM_TO_DT = ?, CUM_TRX_FEE_TO_DT = ?, MEMO_CODE = ?, TRX_FEE_AMT = ?, TRX_FEE_PCT = ?, COMM_TRX_FEE_IND = ?");
        sb.append(" WHERE COMPANY_CODE = ? AND PRODUCT_PREFIX = ? AND TABLE_SUBSET = ? AND EFFECTIVE_DATE = ? AND TRANSACTION_CODE = ? AND SEX_CODE = ? AND MX_CVG_UNIT = ? AND CUM_PREM_TO_DT = ? AND CUM_TRX_FEE_TO_DT = ? AND MEMO_CODE = ?");
        return sb.toString();
    }
    
    public String prepareDeleteStmt(String schemaName)
    {
        StringBuffer sb = new StringBuffer();
        sb.append("DELETE FROM ").append(schemaName);
        sb.append(".T022X ");
        sb.append(" WHERE COMPANY_CODE = ? AND PRODUCT_PREFIX = ? AND TABLE_SUBSET = ? AND EFFECTIVE_DATE = ? AND TRANSACTION_CODE = ? AND SEX_CODE = ? AND MX_CVG_UNIT = ? AND CUM_PREM_TO_DT = ? AND CUM_TRX_FEE_TO_DT = ? AND MEMO_CODE = ?");
        return sb.toString();
    }
}
