package com.csc.fsg.life.pw.web.io.descriptor.wma;

import com.csc.fsg.life.pw.web.io.*;

public class T033EDescriptor
    extends TableDescriptor
{
    private static final String pagingSql = "SELECT COMPANY_CODE, TABLE_SUBSET, TRANSACTION_CODE, EFFECTIVE_DATE, MAX_DURATION, MIN_SURR_AMOUNT, MIN_SURR_PERCENT, MAX_SURR_AMOUNT, MAX_SURR_PERCENT, MIN_SL_PEN_AMT, MX_SL_PEN_AMT, MIN_ADM_PEN_AMT, MX_ADM_PEN_AMT, MAX_NUM_TRX_PER_YR, MAX_NUM_TRX_TOTAL, RESIDUAL_INDICATOR, RSDL_COI_MMS, RSDL_CSH_VAL FROM ";
    
    public void initialize()
    {
        setRowClass(T033ERow.class);
        setTableName("T033E");
        setTableId("033");
        super.initialize();
    }
    
    public void initializeColumnDescriptors()
    {
        super.initializeColumnDescriptors();
        addColumnDescriptor(new ColumnDescriptor(this,"getCompanyCode","setCompanyCode","COMPANY_CODE,1,1,3,0,true|,0,CHAR,1,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getTableSubset","setTableSubset","TABLE_SUBSET,1,2,16,0,true|,0,CHAR,1,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getTransactionCode","setTransactionCode","TRANSACTION_CODE,1,3,4,0,true|,0,CHAR,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getEffectiveDate","setEffectiveDate","EFFECTIVE_DATE,91,4,10,0,true|,0,DATE,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getMaxDuration","setMaxDuration","MAX_DURATION,3,5,3,0,true|,0,INTEGER,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getMinSurrAmount","setMinSurrAmount","MIN_SURR_AMOUNT,3,6,11,2,false|,0,DOUBLE,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getMinSurrPercent","setMinSurrPercent","MIN_SURR_PERCENT,3,7,5,2,false|,0,DOUBLE,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getMaxSurrAmount","setMaxSurrAmount","MAX_SURR_AMOUNT,3,8,11,2,false|,0,DOUBLE,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getMaxSurrPercent","setMaxSurrPercent","MAX_SURR_PERCENT,3,9,5,2,false|,0,DOUBLE,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getMinSlPenAmt","setMinSlPenAmt","MIN_SL_PEN_AMT,3,10,11,2,false|,0,DOUBLE,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getMxSlPenAmt","setMxSlPenAmt","MX_SL_PEN_AMT,3,11,11,2,false|,0,DOUBLE,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getMinAdmPenAmt","setMinAdmPenAmt","MIN_ADM_PEN_AMT,3,12,11,2,false|,0,DOUBLE,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getMxAdmPenAmt","setMxAdmPenAmt","MX_ADM_PEN_AMT,3,13,11,2,false|,0,DOUBLE,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getMaxNumTrxPerYr","setMaxNumTrxPerYr","MAX_NUM_TRX_PER_YR,3,14,2,0,false|,0,INTEGER,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getMaxNumTrxTotal","setMaxNumTrxTotal","MAX_NUM_TRX_TOTAL,3,15,3,0,false|,0,INTEGER,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getResidualIndicator","setResidualIndicator","RESIDUAL_INDICATOR,1,16,1,0,false|,0,CHAR,4,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getRsdlCoiMms","setRsdlCoiMms","RSDL_COI_MMS,3,17,3,0,false|,0,INTEGER,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getRsdlCshVal","setRsdlCshVal","RSDL_CSH_VAL,3,18,11,2,false|,0,DOUBLE,0,null,null,null,null,null"));
    }
    
    public String getPagingSQL(String schemaName,boolean isSubsetMode,boolean isNext, boolean locator)
    {
        String pagingWhere = null;
        String order = null;
        if (isNext) {
            if (isSubsetMode)
                if (locator)
                    pagingWhere = ".T033E WHERE (COMPANY_CODE = :company_code) AND (TABLE_SUBSET = :table_subset) AND ((TRANSACTION_CODE > :transaction_code) OR (TRANSACTION_CODE = :transaction_code AND EFFECTIVE_DATE > :effective_date) OR (TRANSACTION_CODE = :transaction_code AND EFFECTIVE_DATE = :effective_date AND MAX_DURATION > :max_duration) OR (TRANSACTION_CODE = :transaction_code AND EFFECTIVE_DATE = :effective_date AND MAX_DURATION = :max_duration)) ";
                else 
                    pagingWhere = ".T033E WHERE (COMPANY_CODE = :company_code) AND (TABLE_SUBSET = :table_subset) AND ((TRANSACTION_CODE > :transaction_code) OR (TRANSACTION_CODE = :transaction_code AND EFFECTIVE_DATE > :effective_date) OR (TRANSACTION_CODE = :transaction_code AND EFFECTIVE_DATE = :effective_date AND MAX_DURATION > :max_duration)) ";
            else
                if (locator)
                    pagingWhere = ".T033E WHERE (COMPANY_CODE = :company_code) AND ((TABLE_SUBSET > :table_subset) OR (TABLE_SUBSET = :table_subset AND TRANSACTION_CODE > :transaction_code) OR (TABLE_SUBSET = :table_subset AND TRANSACTION_CODE = :transaction_code AND EFFECTIVE_DATE > :effective_date) OR (TABLE_SUBSET = :table_subset AND TRANSACTION_CODE = :transaction_code AND EFFECTIVE_DATE = :effective_date AND MAX_DURATION > :max_duration) OR (TABLE_SUBSET = :table_subset AND TRANSACTION_CODE = :transaction_code AND EFFECTIVE_DATE = :effective_date AND MAX_DURATION = :max_duration)) ";
                else
                    pagingWhere = ".T033E WHERE (COMPANY_CODE = :company_code) AND ((TABLE_SUBSET > :table_subset) OR (TABLE_SUBSET = :table_subset AND TRANSACTION_CODE > :transaction_code) OR (TABLE_SUBSET = :table_subset AND TRANSACTION_CODE = :transaction_code AND EFFECTIVE_DATE > :effective_date) OR (TABLE_SUBSET = :table_subset AND TRANSACTION_CODE = :transaction_code AND EFFECTIVE_DATE = :effective_date AND MAX_DURATION > :max_duration)) ";
            order = " ORDER BY COMPANY_CODE, TABLE_SUBSET, TRANSACTION_CODE, EFFECTIVE_DATE, MAX_DURATION";
        }
        else {
            if (isSubsetMode)
                pagingWhere = ".T033E WHERE (COMPANY_CODE = :company_code) AND (TABLE_SUBSET = :table_subset) AND ((TRANSACTION_CODE < :transaction_code) OR (TRANSACTION_CODE = :transaction_code AND EFFECTIVE_DATE < :effective_date) OR (TRANSACTION_CODE = :transaction_code AND EFFECTIVE_DATE = :effective_date AND MAX_DURATION < :max_duration)) ";
            else
                pagingWhere = ".T033E WHERE (COMPANY_CODE = :company_code) AND ((TABLE_SUBSET < :table_subset) OR (TABLE_SUBSET = :table_subset AND TRANSACTION_CODE < :transaction_code) OR (TABLE_SUBSET = :table_subset AND TRANSACTION_CODE = :transaction_code AND EFFECTIVE_DATE < :effective_date) OR (TABLE_SUBSET = :table_subset AND TRANSACTION_CODE = :transaction_code AND EFFECTIVE_DATE = :effective_date AND MAX_DURATION < :max_duration)) ";
            order = " ORDER BY COMPANY_CODE DESC, TABLE_SUBSET DESC, TRANSACTION_CODE DESC, EFFECTIVE_DATE DESC, MAX_DURATION DESC";
        }
        return pagingSql + schemaName + pagingWhere + order;
    }
    
    public String prepareInsertStmt(String schemaName) {
        StringBuffer sb = new StringBuffer();
        sb.append("INSERT INTO ").append(schemaName);
        sb.append(".T033E ( ");
        sb.append("COMPANY_CODE, TABLE_SUBSET, TRANSACTION_CODE, EFFECTIVE_DATE, MAX_DURATION, MIN_SURR_AMOUNT, MIN_SURR_PERCENT, MAX_SURR_AMOUNT, MAX_SURR_PERCENT, MIN_SL_PEN_AMT, MX_SL_PEN_AMT, MIN_ADM_PEN_AMT, MX_ADM_PEN_AMT, MAX_NUM_TRX_PER_YR, MAX_NUM_TRX_TOTAL, RESIDUAL_INDICATOR, RSDL_COI_MMS, RSDL_CSH_VAL )");
        sb.append(" VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ? )");
        return sb.toString();
    }
    
    public String prepareUpdateStmt(String schemaName)
    {
        StringBuffer sb = new StringBuffer();
        sb.append("UPDATE ").append(schemaName);
        sb.append(".T033E ");
        sb.append(" SET COMPANY_CODE = ?, TABLE_SUBSET = ?, TRANSACTION_CODE = ?, EFFECTIVE_DATE = ?, MAX_DURATION = ?, MIN_SURR_AMOUNT = ?, MIN_SURR_PERCENT = ?, MAX_SURR_AMOUNT = ?, MAX_SURR_PERCENT = ?, MIN_SL_PEN_AMT = ?, MX_SL_PEN_AMT = ?, MIN_ADM_PEN_AMT = ?, MX_ADM_PEN_AMT = ?, MAX_NUM_TRX_PER_YR = ?, MAX_NUM_TRX_TOTAL = ?, RESIDUAL_INDICATOR = ?, RSDL_COI_MMS = ?, RSDL_CSH_VAL = ?");
        sb.append(" WHERE COMPANY_CODE = ? AND TABLE_SUBSET = ? AND TRANSACTION_CODE = ? AND EFFECTIVE_DATE = ? AND MAX_DURATION = ?");
        return sb.toString();
    }
    
    public String prepareDeleteStmt(String schemaName)
    {
        StringBuffer sb = new StringBuffer();
        sb.append("DELETE FROM ").append(schemaName);
        sb.append(".T033E ");
        sb.append(" WHERE COMPANY_CODE = ? AND TABLE_SUBSET = ? AND TRANSACTION_CODE = ? AND EFFECTIVE_DATE = ? AND MAX_DURATION = ?");
        return sb.toString();
    }
}
