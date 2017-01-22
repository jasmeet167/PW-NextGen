package com.csc.fsg.life.pw.web.io.descriptor.wma;

import com.csc.fsg.life.pw.web.io.*;

/* Modifications: ENH1112 */

public class T038XDescriptor
    extends TableDescriptor
{
    private static final String pagingSql = "SELECT COMPANY_CODE, PRODUCT_PREFIX, TABLE_SUBSET, EFFECTIVE_DATE, MEMO_CODE, SELECTION_NUMBER, MAX_MONTHLY_DUR, ADV_ARR_IND, COLL_INT_CRED_FREQ, PRIOR_ANNIV_DAYS, LOAN_INT_EXT_DAYS, INT_DUE_GRACE_PRD, RATE_CHNG_OVRRIDE, LOAN_INT_SPEC_TBL, COLL_INT_SPEC_TBL, GRCE_END_PROC_IND, STRT_DT_403B_RULE, PRE_59_HALF_NOTICE, LN_REPAY_METHOD, LN_REFND_DISB_IND, VP_EXIST_LOAN_RULE, LN_UNDER_PAY_AMT, LN_OVER_PAY_AMT, LOAN_INTEREST_IND, PREMIUM_LOAN_RULE, PUAR_APL_RULE, LN_INSTALLMENT_UNDER_PAY_AMT FROM ";
    
    public void initialize()
    {
        setRowClass(T038XRow.class);
        setTableName("T038X");
        setTableId("038");
        super.initialize();
    }
    
    public void initializeColumnDescriptors()
    {
        super.initializeColumnDescriptors();
        addColumnDescriptor(new ColumnDescriptor(this,"getCompanyCode","setCompanyCode","COMPANY_CODE,1,1,3,0,true|A,0,CHAR,1,null,null,null,null,null|T,0,CHAR,1,null,null,null,null,null|U,0,CHAR,1,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getProductPrefix","setProductPrefix","PRODUCT_PREFIX,1,2,1,0,true|A,0,CHAR,1,null,null,null,null,null|T,0,CHAR,1,null,null,null,null,null|U,0,CHAR,1,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getTableSubset","setTableSubset","TABLE_SUBSET,1,3,16,0,true|A,0,CHAR,1,null,null,null,null,null|T,0,CHAR,1,null,null,null,null,null|U,0,CHAR,1,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getEffectiveDate","setEffectiveDate","EFFECTIVE_DATE,91,4,10,0,true|A,0,DATE,0,null,null,null,null,null|T,0,DATE,0,null,null,null,null,null|U,0,DATE,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getMemoCode","setMemoCode","MEMO_CODE,1,5,2,0,true|A,0,CHAR,4,null,null,null,null,null|T,0,CHAR,2,null,null,null,null,null|U,0,CHAR,2,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getSelectionNumber","setSelectionNumber","SELECTION_NUMBER,1,6,3,0,true|A,0,CHAR,2,null,null,null,null,null|T,0,CHAR,0,null,null,null,null,null|U,0,CHAR,2,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getMaxMonthlyDur","setMaxMonthlyDur","MAX_MONTHLY_DUR,3,7,4,0,true|U,0,INTEGER,4,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getAdvArrInd","setAdvArrInd","ADV_ARR_IND,1,8,3,0,false|A,0,CHAR,0,null,null,null,null,null|T,0,CHAR,0,null,null,null,null,null|U,0,CHAR,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getCollIntCredFreq","setCollIntCredFreq","COLL_INT_CRED_FREQ,1,9,1,0,false|A,0,CHAR,0,null,null,null,null,null|T,0,CHAR,2,null,null,null,null,null|U,0,CHAR,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getPriorAnnivDays","setPriorAnnivDays","PRIOR_ANNIV_DAYS,3,10,2,0,false|A,0,INTEGER,0,null,null,null,null,null|T,0,INTEGER,0,null,null,null,null,null|U,0,INTEGER,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getLoanIntExtDays","setLoanIntExtDays","LOAN_INT_EXT_DAYS,3,11,3,0,false|A,0,INTEGER,0,null,null,null,null,null|T,0,INTEGER,2,null,null,null,null,null|U,0,INTEGER,2,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getIntDueGracePrd","setIntDueGracePrd","INT_DUE_GRACE_PRD,3,12,3,0,false|A,0,INTEGER,0,null,null,null,null,null|T,0,INTEGER,2,null,null,null,null,null|U,0,INTEGER,2,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getRateChngOvrride","setRateChngOvrride","RATE_CHNG_OVRRIDE,1,13,1,0,false|A,0,CHAR,0,null,null,null,null,null|T,0,CHAR,0,null,null,null,null,null|U,0,CHAR,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getLoanIntSpecTbl","setLoanIntSpecTbl","LOAN_INT_SPEC_TBL,1,14,16,0,false|A,0,CHAR,0,024,1,null,null,null|T,0,CHAR,0,T24,null,null,null,null|U,0,CHAR,0,024,1,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getCollIntSpecTbl","setCollIntSpecTbl","COLL_INT_SPEC_TBL,1,15,16,0,false|A,0,CHAR,0,024,2,null,null,null|T,0,CHAR,2,null,null,null,null,null|U,0,CHAR,0,024,2,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getGrceEndProcInd","setGrceEndProcInd","GRCE_END_PROC_IND,1,16,1,0,false|A,0,CHAR,0,null,null,null,null,null|T,0,CHAR,2,null,null,null,null,null|U,0,CHAR,2,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getStrtDt403bRule","setStrtDt403bRule","STRT_DT_403B_RULE,91,17,10,0,false|A,0,DATE,0,null,null,null,null,null|T,0,DATE,2,null,null,null,null,null|U,0,DATE,2,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getPre59HalfNotice","setPre59HalfNotice","PRE_59_HALF_NOTICE,3,18,3,0,false|A,0,INTEGER,0,null,null,null,null,null|T,0,INTEGER,2,null,null,null,null,null|U,0,INTEGER,2,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getLnRepayMethod","setLnRepayMethod","LN_REPAY_METHOD,1,19,4,0,false|A,0,CHAR,2,null,null,null,null,null|T,0,CHAR,0,null,null,null,null,null|U,0,CHAR,2,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getLnRefndDisbInd","setLnRefndDisbInd","LN_REFND_DISB_IND,1,20,1,0,false|A,0,CHAR,2,null,null,null,null,null|T,0,CHAR,0,null,null,null,null,null|U,0,CHAR,2,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getVpExistLoanRule","setVpExistLoanRule","VP_EXIST_LOAN_RULE,1,21,1,0,false|A,0,CHAR,2,null,null,null,null,null|T,0,CHAR,0,null,null,null,null,null|U,0,CHAR,2,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getLnUnderPayAmt","setLnUnderPayAmt","LN_UNDER_PAY_AMT,3,22,11,2,false|A,0,DOUBLE,0,null,null,null,null,null|T,0,DOUBLE,0,null,null,null,null,null|U,0,DOUBLE,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getLnOverPayAmt","setLnOverPayAmt","LN_OVER_PAY_AMT,3,23,11,2,false|A,0,DOUBLE,0,null,null,null,null,null|T,0,DOUBLE,0,null,null,null,null,null|U,0,DOUBLE,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getLoanInterestInd","setLoanInterestInd","LOAN_INTEREST_IND,1,24,1,0,false|A,0,CHAR,2,null,null,null,null,null|T,0,CHAR,0,null,null,null,null,null|U,0,CHAR,2,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getPremiumLoanRule","setPremiumLoanRule","PREMIUM_LOAN_RULE,1,25,1,0,false|A,0,CHAR,4,null,null,null,null,null|T,0,CHAR,4,null,null,null,null,null|U,0,CHAR,4,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getPuarAplRule","setPuarAplRule","PUAR_APL_RULE,1,26,1,0,false|A,0,CHAR,4,null,null,null,null,null|T,0,CHAR,4,null,null,null,null,null|U,0,CHAR,4,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getLnInstallmentUnderPayAmt","setLnInstallmentUnderPayAmt","LN_INSTALLMENT_UNDER_PAY_AMT,3,27,11,2,false"));
    }
    
    public String getPagingSQL(String schemaName,boolean isSubsetMode,boolean isNext, boolean locator)
    {
        String pagingWhere = null;
        String order = null;
        if (isNext) {
            if (isSubsetMode)
                if (locator)
                    pagingWhere = ".T038X WHERE (COMPANY_CODE = :company_code) AND (PRODUCT_PREFIX = :product_prefix) AND (TABLE_SUBSET = :table_subset) AND ((EFFECTIVE_DATE > :effective_date) OR (EFFECTIVE_DATE = :effective_date AND MEMO_CODE > :memo_code) OR (EFFECTIVE_DATE = :effective_date AND MEMO_CODE = :memo_code AND SELECTION_NUMBER > :selection_number) OR (EFFECTIVE_DATE = :effective_date AND MEMO_CODE = :memo_code AND SELECTION_NUMBER = :selection_number AND MAX_MONTHLY_DUR > :max_monthly_dur) OR (EFFECTIVE_DATE = :effective_date AND MEMO_CODE = :memo_code AND SELECTION_NUMBER = :selection_number AND MAX_MONTHLY_DUR = :max_monthly_dur)) ";
                else 
                    pagingWhere = ".T038X WHERE (COMPANY_CODE = :company_code) AND (PRODUCT_PREFIX = :product_prefix) AND (TABLE_SUBSET = :table_subset) AND ((EFFECTIVE_DATE > :effective_date) OR (EFFECTIVE_DATE = :effective_date AND MEMO_CODE > :memo_code) OR (EFFECTIVE_DATE = :effective_date AND MEMO_CODE = :memo_code AND SELECTION_NUMBER > :selection_number) OR (EFFECTIVE_DATE = :effective_date AND MEMO_CODE = :memo_code AND SELECTION_NUMBER = :selection_number AND MAX_MONTHLY_DUR > :max_monthly_dur)) ";
            else
                if (locator)
                    pagingWhere = ".T038X WHERE (COMPANY_CODE = :company_code) AND ((PRODUCT_PREFIX > :product_prefix) OR (PRODUCT_PREFIX = :product_prefix AND TABLE_SUBSET > :table_subset) OR (PRODUCT_PREFIX = :product_prefix AND TABLE_SUBSET = :table_subset AND EFFECTIVE_DATE > :effective_date) OR (PRODUCT_PREFIX = :product_prefix AND TABLE_SUBSET = :table_subset AND EFFECTIVE_DATE = :effective_date AND MEMO_CODE > :memo_code) OR (PRODUCT_PREFIX = :product_prefix AND TABLE_SUBSET = :table_subset AND EFFECTIVE_DATE = :effective_date AND MEMO_CODE = :memo_code AND SELECTION_NUMBER > :selection_number) OR (PRODUCT_PREFIX = :product_prefix AND TABLE_SUBSET = :table_subset AND EFFECTIVE_DATE = :effective_date AND MEMO_CODE = :memo_code AND SELECTION_NUMBER = :selection_number AND MAX_MONTHLY_DUR > :max_monthly_dur) OR (PRODUCT_PREFIX = :product_prefix AND TABLE_SUBSET = :table_subset AND EFFECTIVE_DATE = :effective_date AND MEMO_CODE = :memo_code AND SELECTION_NUMBER = :selection_number AND MAX_MONTHLY_DUR = :max_monthly_dur)) ";
                else
                    pagingWhere = ".T038X WHERE (COMPANY_CODE = :company_code) AND ((PRODUCT_PREFIX > :product_prefix) OR (PRODUCT_PREFIX = :product_prefix AND TABLE_SUBSET > :table_subset) OR (PRODUCT_PREFIX = :product_prefix AND TABLE_SUBSET = :table_subset AND EFFECTIVE_DATE > :effective_date) OR (PRODUCT_PREFIX = :product_prefix AND TABLE_SUBSET = :table_subset AND EFFECTIVE_DATE = :effective_date AND MEMO_CODE > :memo_code) OR (PRODUCT_PREFIX = :product_prefix AND TABLE_SUBSET = :table_subset AND EFFECTIVE_DATE = :effective_date AND MEMO_CODE = :memo_code AND SELECTION_NUMBER > :selection_number) OR (PRODUCT_PREFIX = :product_prefix AND TABLE_SUBSET = :table_subset AND EFFECTIVE_DATE = :effective_date AND MEMO_CODE = :memo_code AND SELECTION_NUMBER = :selection_number AND MAX_MONTHLY_DUR > :max_monthly_dur)) ";
            order = " ORDER BY COMPANY_CODE, PRODUCT_PREFIX, TABLE_SUBSET, EFFECTIVE_DATE, MEMO_CODE, SELECTION_NUMBER, MAX_MONTHLY_DUR";
        }
        else {
            if (isSubsetMode)
                pagingWhere = ".T038X WHERE (COMPANY_CODE = :company_code) AND (PRODUCT_PREFIX = :product_prefix) AND (TABLE_SUBSET = :table_subset) AND ((EFFECTIVE_DATE < :effective_date) OR (EFFECTIVE_DATE = :effective_date AND MEMO_CODE < :memo_code) OR (EFFECTIVE_DATE = :effective_date AND MEMO_CODE = :memo_code AND SELECTION_NUMBER < :selection_number) OR (EFFECTIVE_DATE = :effective_date AND MEMO_CODE = :memo_code AND SELECTION_NUMBER = :selection_number AND MAX_MONTHLY_DUR < :max_monthly_dur)) ";
            else
                pagingWhere = ".T038X WHERE (COMPANY_CODE = :company_code) AND ((PRODUCT_PREFIX < :product_prefix) OR (PRODUCT_PREFIX = :product_prefix AND TABLE_SUBSET < :table_subset) OR (PRODUCT_PREFIX = :product_prefix AND TABLE_SUBSET = :table_subset AND EFFECTIVE_DATE < :effective_date) OR (PRODUCT_PREFIX = :product_prefix AND TABLE_SUBSET = :table_subset AND EFFECTIVE_DATE = :effective_date AND MEMO_CODE < :memo_code) OR (PRODUCT_PREFIX = :product_prefix AND TABLE_SUBSET = :table_subset AND EFFECTIVE_DATE = :effective_date AND MEMO_CODE = :memo_code AND SELECTION_NUMBER < :selection_number) OR (PRODUCT_PREFIX = :product_prefix AND TABLE_SUBSET = :table_subset AND EFFECTIVE_DATE = :effective_date AND MEMO_CODE = :memo_code AND SELECTION_NUMBER = :selection_number AND MAX_MONTHLY_DUR < :max_monthly_dur)) ";
            order = " ORDER BY COMPANY_CODE DESC, PRODUCT_PREFIX DESC, TABLE_SUBSET DESC, EFFECTIVE_DATE DESC, MEMO_CODE DESC, SELECTION_NUMBER DESC, MAX_MONTHLY_DUR DESC";
        }
        return pagingSql + schemaName + pagingWhere + order;
    }
    
    public String prepareInsertStmt(String schemaName) {
        StringBuffer sb = new StringBuffer();
        sb.append("INSERT INTO ").append(schemaName);
        sb.append(".T038X ( ");
        sb.append("COMPANY_CODE, PRODUCT_PREFIX, TABLE_SUBSET, EFFECTIVE_DATE, MEMO_CODE, SELECTION_NUMBER, MAX_MONTHLY_DUR, ADV_ARR_IND, COLL_INT_CRED_FREQ, PRIOR_ANNIV_DAYS, LOAN_INT_EXT_DAYS, INT_DUE_GRACE_PRD, RATE_CHNG_OVRRIDE, LOAN_INT_SPEC_TBL, COLL_INT_SPEC_TBL, GRCE_END_PROC_IND, STRT_DT_403B_RULE, PRE_59_HALF_NOTICE, LN_REPAY_METHOD, LN_REFND_DISB_IND, VP_EXIST_LOAN_RULE, LN_UNDER_PAY_AMT, LN_OVER_PAY_AMT, LOAN_INTEREST_IND, PREMIUM_LOAN_RULE, PUAR_APL_RULE, LN_INSTALLMENT_UNDER_PAY_AMT )");
        sb.append(" VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ? )");
        return sb.toString();
    }
    
    public String prepareUpdateStmt(String schemaName)
    {
        StringBuffer sb = new StringBuffer();
        sb.append("UPDATE ").append(schemaName);
        sb.append(".T038X ");
        sb.append(" SET COMPANY_CODE = ?, PRODUCT_PREFIX = ?, TABLE_SUBSET = ?, EFFECTIVE_DATE = ?, MEMO_CODE = ?, SELECTION_NUMBER = ?, MAX_MONTHLY_DUR = ?, ADV_ARR_IND = ?, COLL_INT_CRED_FREQ = ?, PRIOR_ANNIV_DAYS = ?, LOAN_INT_EXT_DAYS = ?, INT_DUE_GRACE_PRD = ?, RATE_CHNG_OVRRIDE = ?, LOAN_INT_SPEC_TBL = ?, COLL_INT_SPEC_TBL = ?, GRCE_END_PROC_IND = ?, STRT_DT_403B_RULE = ?, PRE_59_HALF_NOTICE = ?, LN_REPAY_METHOD = ?, LN_REFND_DISB_IND = ?, VP_EXIST_LOAN_RULE = ?, LN_UNDER_PAY_AMT = ?, LN_OVER_PAY_AMT = ?, LOAN_INTEREST_IND = ?, PREMIUM_LOAN_RULE = ?, PUAR_APL_RULE = ?, LN_INSTALLMENT_UNDER_PAY_AMT = ?");
        sb.append(" WHERE COMPANY_CODE = ? AND PRODUCT_PREFIX = ? AND TABLE_SUBSET = ? AND EFFECTIVE_DATE = ? AND MEMO_CODE = ? AND SELECTION_NUMBER = ? AND MAX_MONTHLY_DUR = ?");
        return sb.toString();
    }
    
    public String prepareDeleteStmt(String schemaName)
    {
        StringBuffer sb = new StringBuffer();
        sb.append("DELETE FROM ").append(schemaName);
        sb.append(".T038X ");
        sb.append(" WHERE COMPANY_CODE = ? AND PRODUCT_PREFIX = ? AND TABLE_SUBSET = ? AND EFFECTIVE_DATE = ? AND MEMO_CODE = ? AND SELECTION_NUMBER = ? AND MAX_MONTHLY_DUR = ?");
        return sb.toString();
    }
}
