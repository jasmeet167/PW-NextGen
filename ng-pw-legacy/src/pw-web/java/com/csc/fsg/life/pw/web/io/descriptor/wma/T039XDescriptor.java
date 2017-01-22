package com.csc.fsg.life.pw.web.io.descriptor.wma;

import com.csc.fsg.life.pw.web.io.*;

public class T039XDescriptor
    extends TableDescriptor
{
    private static final String pagingSql = "SELECT COMPANY_CODE, PRODUCT_PREFIX, TABLE_SUBSET, EFFECTIVE_DATE, MAXIMUM_DURATION, MEMO_CODE, MAXIMUM_LOANS, MINIMUM_LOAN_AMT, MIN_PRCNT_CASH_VAL, MIN_DEFINITION_CDE, REMAIN_CASH_VALUE, MIN_CEILING_PRCNT, MAX_CEILING_PRCNT, MAX_LOAN_AMOUNT, MAX_PRCT_CASH_VAL, MAX_DEF_CODE, REMAIN_MN_DEDUCT, REM_MN_DED_NXT_AN, REMAIN_DEF_CODE FROM ";
    
    public void initialize()
    {
        setRowClass(T039XRow.class);
        setTableName("T039X");
        setTableId("039");
        super.initialize();
    }
    
    public void initializeColumnDescriptors()
    {
        super.initializeColumnDescriptors();
        addColumnDescriptor(new ColumnDescriptor(this,"getCompanyCode","setCompanyCode","COMPANY_CODE,1,1,3,0,true|A,0,CHAR,1,null,null,null,null,null|T,0,CHAR,1,null,null,null,null,null|U,0,CHAR,1,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getProductPrefix","setProductPrefix","PRODUCT_PREFIX,1,2,1,0,true|A,0,CHAR,1,null,null,null,null,null|T,0,CHAR,1,null,null,null,null,null|U,0,CHAR,1,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getTableSubset","setTableSubset","TABLE_SUBSET,1,3,16,0,true|A,0,CHAR,1,null,null,null,null,null|T,0,CHAR,1,null,null,null,null,null|U,0,CHAR,1,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getEffectiveDate","setEffectiveDate","EFFECTIVE_DATE,91,4,10,0,true|A,0,DATE,0,null,null,null,null,null|T,0,DATE,0,null,null,null,null,null|U,0,DATE,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getMaximumDuration","setMaximumDuration","MAXIMUM_DURATION,3,5,2,0,true|A,0,INTEGER,0,null,null,null,null,null|T,0,INTEGER,0,null,null,null,null,null|U,0,INTEGER,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getMemoCode","setMemoCode","MEMO_CODE,1,6,2,0,true|A,0,CHAR,4,null,null,null,null,null|T,0,CHAR,2,null,null,null,null,null|U,0,CHAR,2,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getMaximumLoans","setMaximumLoans","MAXIMUM_LOANS,3,7,2,0,false|A,0,INTEGER,0,null,null,null,null,null|T,0,INTEGER,0,null,null,null,null,null|U,0,INTEGER,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getMinimumLoanAmt","setMinimumLoanAmt","MINIMUM_LOAN_AMT,3,8,11,2,false|A,0,DOUBLE,0,null,null,null,null,null|T,0,DOUBLE,0,null,null,null,null,null|U,0,DOUBLE,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getMinPrcntCashVal","setMinPrcntCashVal","MIN_PRCNT_CASH_VAL,3,9,5,2,false|A,0,DOUBLE,0,null,null,null,null,null|T,0,DOUBLE,0,null,null,null,null,null|U,0,DOUBLE,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getMinDefinitionCde","setMinDefinitionCde","MIN_DEFINITION_CDE,1,10,1,0,false|A,0,CHAR,0,null,null,null,null,null|T,0,CHAR,0,null,null,null,null,null|U,0,CHAR,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getRemainCashValue","setRemainCashValue","REMAIN_CASH_VALUE,3,11,11,2,false|A,0,DOUBLE,0,null,null,null,null,null|T,0,DOUBLE,0,null,null,null,null,null|U,0,DOUBLE,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getMinCeilingPrcnt","setMinCeilingPrcnt","MIN_CEILING_PRCNT,3,12,5,2,false|A,0,DOUBLE,0,null,null,null,null,null|T,0,DOUBLE,2,null,null,null,null,null|U,0,DOUBLE,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getMaxCeilingPrcnt","setMaxCeilingPrcnt","MAX_CEILING_PRCNT,3,13,5,2,false|A,0,DOUBLE,0,null,null,null,null,null|T,0,DOUBLE,2,null,null,null,null,null|U,0,DOUBLE,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getMaxLoanAmount","setMaxLoanAmount","MAX_LOAN_AMOUNT,3,14,11,2,false|A,0,DOUBLE,2,null,null,null,null,null|T,0,DOUBLE,0,null,null,null,null,null|U,0,DOUBLE,2,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getMaxPrctCashVal","setMaxPrctCashVal","MAX_PRCT_CASH_VAL,3,15,5,2,false|A,0,DOUBLE,2,null,null,null,null,null|T,0,DOUBLE,0,null,null,null,null,null|U,0,DOUBLE,2,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getMaxDefCode","setMaxDefCode","MAX_DEF_CODE,1,16,1,0,false|A,0,CHAR,2,null,null,null,null,null|T,0,CHAR,0,null,null,null,null,null|U,0,CHAR,2,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getRemainMnDeduct","setRemainMnDeduct","REMAIN_MN_DEDUCT,3,17,2,0,false|A,0,INTEGER,2,null,null,null,null,null|T,0,INTEGER,2,null,null,null,null,null|U,0,INTEGER,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getRemMnDedNxtAn","setRemMnDedNxtAn","REM_MN_DED_NXT_AN,1,18,1,0,false|A,0,CHAR,2,null,null,null,null,null|T,0,CHAR,2,null,null,null,null,null|U,0,CHAR,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getRemainDefCode","setRemainDefCode","REMAIN_DEF_CODE,1,19,1,0,false|A,0,CHAR,2,null,null,null,null,null|T,0,CHAR,2,null,null,null,null,null|U,0,CHAR,0,null,null,null,null,null"));
    }
    
    public String getPagingSQL(String schemaName,boolean isSubsetMode,boolean isNext, boolean locator)
    {
        String pagingWhere = null;
        String order = null;
        if (isNext) {
            if (isSubsetMode)
                if (locator)
                    pagingWhere = ".T039X WHERE (COMPANY_CODE = :company_code) AND (PRODUCT_PREFIX = :product_prefix) AND (TABLE_SUBSET = :table_subset) AND ((EFFECTIVE_DATE > :effective_date) OR (EFFECTIVE_DATE = :effective_date AND MAXIMUM_DURATION > :maximum_duration) OR (EFFECTIVE_DATE = :effective_date AND MAXIMUM_DURATION = :maximum_duration AND MEMO_CODE > :memo_code) OR (EFFECTIVE_DATE = :effective_date AND MAXIMUM_DURATION = :maximum_duration AND MEMO_CODE = :memo_code)) ";
                else 
                    pagingWhere = ".T039X WHERE (COMPANY_CODE = :company_code) AND (PRODUCT_PREFIX = :product_prefix) AND (TABLE_SUBSET = :table_subset) AND ((EFFECTIVE_DATE > :effective_date) OR (EFFECTIVE_DATE = :effective_date AND MAXIMUM_DURATION > :maximum_duration) OR (EFFECTIVE_DATE = :effective_date AND MAXIMUM_DURATION = :maximum_duration AND MEMO_CODE > :memo_code)) ";
            else
                if (locator)
                    pagingWhere = ".T039X WHERE (COMPANY_CODE = :company_code) AND ((PRODUCT_PREFIX > :product_prefix) OR (PRODUCT_PREFIX = :product_prefix AND TABLE_SUBSET > :table_subset) OR (PRODUCT_PREFIX = :product_prefix AND TABLE_SUBSET = :table_subset AND EFFECTIVE_DATE > :effective_date) OR (PRODUCT_PREFIX = :product_prefix AND TABLE_SUBSET = :table_subset AND EFFECTIVE_DATE = :effective_date AND MAXIMUM_DURATION > :maximum_duration) OR (PRODUCT_PREFIX = :product_prefix AND TABLE_SUBSET = :table_subset AND EFFECTIVE_DATE = :effective_date AND MAXIMUM_DURATION = :maximum_duration AND MEMO_CODE > :memo_code) OR (PRODUCT_PREFIX = :product_prefix AND TABLE_SUBSET = :table_subset AND EFFECTIVE_DATE = :effective_date AND MAXIMUM_DURATION = :maximum_duration AND MEMO_CODE = :memo_code)) ";
                else
                    pagingWhere = ".T039X WHERE (COMPANY_CODE = :company_code) AND ((PRODUCT_PREFIX > :product_prefix) OR (PRODUCT_PREFIX = :product_prefix AND TABLE_SUBSET > :table_subset) OR (PRODUCT_PREFIX = :product_prefix AND TABLE_SUBSET = :table_subset AND EFFECTIVE_DATE > :effective_date) OR (PRODUCT_PREFIX = :product_prefix AND TABLE_SUBSET = :table_subset AND EFFECTIVE_DATE = :effective_date AND MAXIMUM_DURATION > :maximum_duration) OR (PRODUCT_PREFIX = :product_prefix AND TABLE_SUBSET = :table_subset AND EFFECTIVE_DATE = :effective_date AND MAXIMUM_DURATION = :maximum_duration AND MEMO_CODE > :memo_code)) ";
            order = " ORDER BY COMPANY_CODE, PRODUCT_PREFIX, TABLE_SUBSET, EFFECTIVE_DATE, MAXIMUM_DURATION, MEMO_CODE";
        }
        else {
            if (isSubsetMode)
                pagingWhere = ".T039X WHERE (COMPANY_CODE = :company_code) AND (PRODUCT_PREFIX = :product_prefix) AND (TABLE_SUBSET = :table_subset) AND ((EFFECTIVE_DATE < :effective_date) OR (EFFECTIVE_DATE = :effective_date AND MAXIMUM_DURATION < :maximum_duration) OR (EFFECTIVE_DATE = :effective_date AND MAXIMUM_DURATION = :maximum_duration AND MEMO_CODE < :memo_code)) ";
            else
                pagingWhere = ".T039X WHERE (COMPANY_CODE = :company_code) AND ((PRODUCT_PREFIX < :product_prefix) OR (PRODUCT_PREFIX = :product_prefix AND TABLE_SUBSET < :table_subset) OR (PRODUCT_PREFIX = :product_prefix AND TABLE_SUBSET = :table_subset AND EFFECTIVE_DATE < :effective_date) OR (PRODUCT_PREFIX = :product_prefix AND TABLE_SUBSET = :table_subset AND EFFECTIVE_DATE = :effective_date AND MAXIMUM_DURATION < :maximum_duration) OR (PRODUCT_PREFIX = :product_prefix AND TABLE_SUBSET = :table_subset AND EFFECTIVE_DATE = :effective_date AND MAXIMUM_DURATION = :maximum_duration AND MEMO_CODE < :memo_code)) ";
            order = " ORDER BY COMPANY_CODE DESC, PRODUCT_PREFIX DESC, TABLE_SUBSET DESC, EFFECTIVE_DATE DESC, MAXIMUM_DURATION DESC, MEMO_CODE DESC";
        }
        return pagingSql + schemaName + pagingWhere + order;
    }
    
    public String prepareInsertStmt(String schemaName) {
        StringBuffer sb = new StringBuffer();
        sb.append("INSERT INTO ").append(schemaName);
        sb.append(".T039X ( ");
        sb.append("COMPANY_CODE, PRODUCT_PREFIX, TABLE_SUBSET, EFFECTIVE_DATE, MAXIMUM_DURATION, MEMO_CODE, MAXIMUM_LOANS, MINIMUM_LOAN_AMT, MIN_PRCNT_CASH_VAL, MIN_DEFINITION_CDE, REMAIN_CASH_VALUE, MIN_CEILING_PRCNT, MAX_CEILING_PRCNT, MAX_LOAN_AMOUNT, MAX_PRCT_CASH_VAL, MAX_DEF_CODE, REMAIN_MN_DEDUCT, REM_MN_DED_NXT_AN, REMAIN_DEF_CODE )");
        sb.append(" VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ? )");
        return sb.toString();
    }
    
    public String prepareUpdateStmt(String schemaName)
    {
        StringBuffer sb = new StringBuffer();
        sb.append("UPDATE ").append(schemaName);
        sb.append(".T039X ");
        sb.append(" SET COMPANY_CODE = ?, PRODUCT_PREFIX = ?, TABLE_SUBSET = ?, EFFECTIVE_DATE = ?, MAXIMUM_DURATION = ?, MEMO_CODE = ?, MAXIMUM_LOANS = ?, MINIMUM_LOAN_AMT = ?, MIN_PRCNT_CASH_VAL = ?, MIN_DEFINITION_CDE = ?, REMAIN_CASH_VALUE = ?, MIN_CEILING_PRCNT = ?, MAX_CEILING_PRCNT = ?, MAX_LOAN_AMOUNT = ?, MAX_PRCT_CASH_VAL = ?, MAX_DEF_CODE = ?, REMAIN_MN_DEDUCT = ?, REM_MN_DED_NXT_AN = ?, REMAIN_DEF_CODE = ?");
        sb.append(" WHERE COMPANY_CODE = ? AND PRODUCT_PREFIX = ? AND TABLE_SUBSET = ? AND EFFECTIVE_DATE = ? AND MAXIMUM_DURATION = ? AND MEMO_CODE = ?");
        return sb.toString();
    }
    
    public String prepareDeleteStmt(String schemaName)
    {
        StringBuffer sb = new StringBuffer();
        sb.append("DELETE FROM ").append(schemaName);
        sb.append(".T039X ");
        sb.append(" WHERE COMPANY_CODE = ? AND PRODUCT_PREFIX = ? AND TABLE_SUBSET = ? AND EFFECTIVE_DATE = ? AND MAXIMUM_DURATION = ? AND MEMO_CODE = ?");
        return sb.toString();
    }
}
