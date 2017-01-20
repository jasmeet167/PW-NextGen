package com.csc.fsg.life.pw.io.descriptor.wma;

import com.csc.fsg.life.pw.io.*;

public class T058XDescriptor
    extends TableDescriptor
{
    private static final String pagingSql = "SELECT COMPANY_CODE, PRODUCT_PREFIX, TABLE_SUBSET, EFFECTIVE_DATE, PLAN_CODE, SEX, PAYOR_MAX_AGE, INSURED_MAX_AGE, COVER_DURATION, MEMO_CODE, REGULAR_REMIT_IND, DUMP_IN_REMIT_IND, ALLOC_CHG_IND, LOANS_IND, PARTIAL_SURR_IND, FULL_SURR_IND, TRANSFER_IND, AP_CALC_TYPE_IND, AP_PW_CVG_IND, APP_WAIVER_PCT, APP_WAIVER_UNIT, TYPE_CHANGE_IND, WITHDRAWAL_IND, SCHED_ACTIVITY_IND FROM ";
    
    public void initialize()
    {
        setRowClass(T058XRow.class);
        setTableName("T058X");
        setTableId("058");
        super.initialize();
    }
    
    public void initializeColumnDescriptors()
    {
        super.initializeColumnDescriptors();
        addColumnDescriptor(new ColumnDescriptor(this,"getCompanyCode","setCompanyCode","COMPANY_CODE,1,1,3,0,true|A,0,CHAR,1,null,null,null,null,null|T,0,CHAR,1,null,null,null,null,null|U,0,CHAR,1,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getProductPrefix","setProductPrefix","PRODUCT_PREFIX,1,2,1,0,true|A,0,CHAR,1,null,null,null,null,null|T,0,CHAR,1,null,null,null,null,null|U,0,CHAR,1,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getTableSubset","setTableSubset","TABLE_SUBSET,1,3,16,0,true|A,0,CHAR,1,null,null,null,null,null|T,0,CHAR,1,null,null,null,null,null|U,0,CHAR,1,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getEffectiveDate","setEffectiveDate","EFFECTIVE_DATE,91,4,10,0,true|A,0,DATE,0,null,null,null,null,null|T,0,DATE,0,null,null,null,null,null|U,0,DATE,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getPlanCode","setPlanCode","PLAN_CODE,1,5,6,0,true|A,0,CHAR,2,null,null,null,null,null|T,0,CHAR,0,null,null,null,null,null|U,0,CHAR,2,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getSex","setSex","SEX,1,6,1,0,true|A,0,CHAR,2,null,null,null,null,null|T,0,CHAR,0,null,null,null,null,null|U,0,CHAR,2,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getPayorMaxAge","setPayorMaxAge","PAYOR_MAX_AGE,3,7,3,0,true|A,0,INTEGER,2,null,null,null,null,null|T,0,INTEGER,0,null,null,null,null,null|U,0,INTEGER,2,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getInsuredMaxAge","setInsuredMaxAge","INSURED_MAX_AGE,3,8,3,0,true|A,0,INTEGER,2,null,null,null,null,null|T,0,INTEGER,0,null,null,null,null,null|U,0,INTEGER,2,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getCoverDuration","setCoverDuration","COVER_DURATION,3,9,2,0,true|A,0,INTEGER,2,null,null,null,null,null|T,0,INTEGER,0,null,null,null,null,null|U,0,INTEGER,2,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getMemoCode","setMemoCode","MEMO_CODE,1,10,2,0,true|A,0,CHAR,4,null,null,null,null,null|T,0,CHAR,2,null,null,null,null,null|U,0,CHAR,2,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getRegularRemitInd","setRegularRemitInd","REGULAR_REMIT_IND,1,11,1,0,false|A,0,CHAR,0,null,null,null,null,null|T,0,CHAR,2,null,null,null,null,null|U,0,CHAR,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getDumpInRemitInd","setDumpInRemitInd","DUMP_IN_REMIT_IND,1,12,1,0,false|A,0,CHAR,0,null,null,null,null,null|T,0,CHAR,2,null,null,null,null,null|U,0,CHAR,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getAllocChgInd","setAllocChgInd","ALLOC_CHG_IND,1,13,1,0,false|A,0,CHAR,0,null,null,null,null,null|T,0,CHAR,2,null,null,null,null,null|U,0,CHAR,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getLoansInd","setLoansInd","LOANS_IND,1,14,1,0,false|A,0,CHAR,0,null,null,null,null,null|T,0,CHAR,2,null,null,null,null,null|U,0,CHAR,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getPartialSurrInd","setPartialSurrInd","PARTIAL_SURR_IND,1,15,1,0,false|A,0,CHAR,0,null,null,null,null,null|T,0,CHAR,2,null,null,null,null,null|U,0,CHAR,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getFullSurrInd","setFullSurrInd","FULL_SURR_IND,1,16,1,0,false|A,0,CHAR,0,null,null,null,null,null|T,0,CHAR,2,null,null,null,null,null|U,0,CHAR,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getTransferInd","setTransferInd","TRANSFER_IND,1,17,1,0,false|A,0,CHAR,0,null,null,null,null,null|T,0,CHAR,2,null,null,null,null,null|U,0,CHAR,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getApCalcTypeInd","setApCalcTypeInd","AP_CALC_TYPE_IND,1,18,1,0,false|A,0,CHAR,2,null,null,null,null,null|T,0,CHAR,0,null,null,null,null,null|U,0,CHAR,2,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getApPwCvgInd","setApPwCvgInd","AP_PW_CVG_IND,1,19,1,0,false|A,0,CHAR,2,null,null,null,null,null|T,0,CHAR,0,null,null,null,null,null|U,0,CHAR,2,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getAppWaiverPct","setAppWaiverPct","APP_WAIVER_PCT,3,20,4,2,false|A,0,DOUBLE,2,null,null,null,null,null|T,0,DOUBLE,0,null,null,null,null,null|U,0,DOUBLE,2,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getAppWaiverUnit","setAppWaiverUnit","APP_WAIVER_UNIT,3,21,4,2,false|A,0,DOUBLE,2,null,null,null,null,null|T,0,DOUBLE,0,null,null,null,null,null|U,0,DOUBLE,2,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getTypeChangeInd","setTypeChangeInd","TYPE_CHANGE_IND,1,22,1,0,false|A,0,CHAR,2,null,null,null,null,null|T,0,CHAR,2,null,null,null,null,null|U,0,CHAR,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getWithdrawalInd","setWithdrawalInd","WITHDRAWAL_IND,1,23,1,0,false|A,0,CHAR,2,null,null,null,null,null|T,0,CHAR,2,null,null,null,null,null|U,0,CHAR,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getSchedActivityInd","setSchedActivityInd","SCHED_ACTIVITY_IND,1,24,1,0,false|A,0,CHAR,2,null,null,null,null,null|T,0,CHAR,2,null,null,null,null,null|U,0,CHAR,0,null,null,null,null,null"));
    }
    
    public String getPagingSQL(String schemaName,boolean isSubsetMode,boolean isNext, boolean locator)
    {
        String pagingWhere = null;
        String order = null;
        if (isNext) {
            if (isSubsetMode)
                if (locator)
                    pagingWhere = ".T058X WHERE (COMPANY_CODE = :company_code) AND (PRODUCT_PREFIX = :product_prefix) AND (TABLE_SUBSET = :table_subset) AND ((EFFECTIVE_DATE > :effective_date) OR (EFFECTIVE_DATE = :effective_date AND PLAN_CODE > :plan_code) OR (EFFECTIVE_DATE = :effective_date AND PLAN_CODE = :plan_code AND SEX > :sex) OR (EFFECTIVE_DATE = :effective_date AND PLAN_CODE = :plan_code AND SEX = :sex AND PAYOR_MAX_AGE > :payor_max_age) OR (EFFECTIVE_DATE = :effective_date AND PLAN_CODE = :plan_code AND SEX = :sex AND PAYOR_MAX_AGE = :payor_max_age AND INSURED_MAX_AGE > :insured_max_age) OR (EFFECTIVE_DATE = :effective_date AND PLAN_CODE = :plan_code AND SEX = :sex AND PAYOR_MAX_AGE = :payor_max_age AND INSURED_MAX_AGE = :insured_max_age AND COVER_DURATION > :cover_duration) OR (EFFECTIVE_DATE = :effective_date AND PLAN_CODE = :plan_code AND SEX = :sex AND PAYOR_MAX_AGE = :payor_max_age AND INSURED_MAX_AGE = :insured_max_age AND COVER_DURATION = :cover_duration AND MEMO_CODE > :memo_code) OR (EFFECTIVE_DATE = :effective_date AND PLAN_CODE = :plan_code AND SEX = :sex AND PAYOR_MAX_AGE = :payor_max_age AND INSURED_MAX_AGE = :insured_max_age AND COVER_DURATION = :cover_duration AND MEMO_CODE = :memo_code)) ";
                else 
                    pagingWhere = ".T058X WHERE (COMPANY_CODE = :company_code) AND (PRODUCT_PREFIX = :product_prefix) AND (TABLE_SUBSET = :table_subset) AND ((EFFECTIVE_DATE > :effective_date) OR (EFFECTIVE_DATE = :effective_date AND PLAN_CODE > :plan_code) OR (EFFECTIVE_DATE = :effective_date AND PLAN_CODE = :plan_code AND SEX > :sex) OR (EFFECTIVE_DATE = :effective_date AND PLAN_CODE = :plan_code AND SEX = :sex AND PAYOR_MAX_AGE > :payor_max_age) OR (EFFECTIVE_DATE = :effective_date AND PLAN_CODE = :plan_code AND SEX = :sex AND PAYOR_MAX_AGE = :payor_max_age AND INSURED_MAX_AGE > :insured_max_age) OR (EFFECTIVE_DATE = :effective_date AND PLAN_CODE = :plan_code AND SEX = :sex AND PAYOR_MAX_AGE = :payor_max_age AND INSURED_MAX_AGE = :insured_max_age AND COVER_DURATION > :cover_duration) OR (EFFECTIVE_DATE = :effective_date AND PLAN_CODE = :plan_code AND SEX = :sex AND PAYOR_MAX_AGE = :payor_max_age AND INSURED_MAX_AGE = :insured_max_age AND COVER_DURATION = :cover_duration AND MEMO_CODE > :memo_code)) ";
            else
                if (locator)
                    pagingWhere = ".T058X WHERE (COMPANY_CODE = :company_code) AND ((PRODUCT_PREFIX > :product_prefix) OR (PRODUCT_PREFIX = :product_prefix AND TABLE_SUBSET > :table_subset) OR (PRODUCT_PREFIX = :product_prefix AND TABLE_SUBSET = :table_subset AND EFFECTIVE_DATE > :effective_date) OR (PRODUCT_PREFIX = :product_prefix AND TABLE_SUBSET = :table_subset AND EFFECTIVE_DATE = :effective_date AND PLAN_CODE > :plan_code) OR (PRODUCT_PREFIX = :product_prefix AND TABLE_SUBSET = :table_subset AND EFFECTIVE_DATE = :effective_date AND PLAN_CODE = :plan_code AND SEX > :sex) OR (PRODUCT_PREFIX = :product_prefix AND TABLE_SUBSET = :table_subset AND EFFECTIVE_DATE = :effective_date AND PLAN_CODE = :plan_code AND SEX = :sex AND PAYOR_MAX_AGE > :payor_max_age) OR (PRODUCT_PREFIX = :product_prefix AND TABLE_SUBSET = :table_subset AND EFFECTIVE_DATE = :effective_date AND PLAN_CODE = :plan_code AND SEX = :sex AND PAYOR_MAX_AGE = :payor_max_age AND INSURED_MAX_AGE > :insured_max_age) OR (PRODUCT_PREFIX = :product_prefix AND TABLE_SUBSET = :table_subset AND EFFECTIVE_DATE = :effective_date AND PLAN_CODE = :plan_code AND SEX = :sex AND PAYOR_MAX_AGE = :payor_max_age AND INSURED_MAX_AGE = :insured_max_age AND COVER_DURATION > :cover_duration) OR (PRODUCT_PREFIX = :product_prefix AND TABLE_SUBSET = :table_subset AND EFFECTIVE_DATE = :effective_date AND PLAN_CODE = :plan_code AND SEX = :sex AND PAYOR_MAX_AGE = :payor_max_age AND INSURED_MAX_AGE = :insured_max_age AND COVER_DURATION = :cover_duration AND MEMO_CODE > :memo_code) OR (PRODUCT_PREFIX = :product_prefix AND TABLE_SUBSET = :table_subset AND EFFECTIVE_DATE = :effective_date AND PLAN_CODE = :plan_code AND SEX = :sex AND PAYOR_MAX_AGE = :payor_max_age AND INSURED_MAX_AGE = :insured_max_age AND COVER_DURATION = :cover_duration AND MEMO_CODE = :memo_code)) ";
                else
                    pagingWhere = ".T058X WHERE (COMPANY_CODE = :company_code) AND ((PRODUCT_PREFIX > :product_prefix) OR (PRODUCT_PREFIX = :product_prefix AND TABLE_SUBSET > :table_subset) OR (PRODUCT_PREFIX = :product_prefix AND TABLE_SUBSET = :table_subset AND EFFECTIVE_DATE > :effective_date) OR (PRODUCT_PREFIX = :product_prefix AND TABLE_SUBSET = :table_subset AND EFFECTIVE_DATE = :effective_date AND PLAN_CODE > :plan_code) OR (PRODUCT_PREFIX = :product_prefix AND TABLE_SUBSET = :table_subset AND EFFECTIVE_DATE = :effective_date AND PLAN_CODE = :plan_code AND SEX > :sex) OR (PRODUCT_PREFIX = :product_prefix AND TABLE_SUBSET = :table_subset AND EFFECTIVE_DATE = :effective_date AND PLAN_CODE = :plan_code AND SEX = :sex AND PAYOR_MAX_AGE > :payor_max_age) OR (PRODUCT_PREFIX = :product_prefix AND TABLE_SUBSET = :table_subset AND EFFECTIVE_DATE = :effective_date AND PLAN_CODE = :plan_code AND SEX = :sex AND PAYOR_MAX_AGE = :payor_max_age AND INSURED_MAX_AGE > :insured_max_age) OR (PRODUCT_PREFIX = :product_prefix AND TABLE_SUBSET = :table_subset AND EFFECTIVE_DATE = :effective_date AND PLAN_CODE = :plan_code AND SEX = :sex AND PAYOR_MAX_AGE = :payor_max_age AND INSURED_MAX_AGE = :insured_max_age AND COVER_DURATION > :cover_duration) OR (PRODUCT_PREFIX = :product_prefix AND TABLE_SUBSET = :table_subset AND EFFECTIVE_DATE = :effective_date AND PLAN_CODE = :plan_code AND SEX = :sex AND PAYOR_MAX_AGE = :payor_max_age AND INSURED_MAX_AGE = :insured_max_age AND COVER_DURATION = :cover_duration AND MEMO_CODE > :memo_code)) ";
            order = " ORDER BY COMPANY_CODE, PRODUCT_PREFIX, TABLE_SUBSET, EFFECTIVE_DATE, PLAN_CODE, SEX, PAYOR_MAX_AGE, INSURED_MAX_AGE, COVER_DURATION, MEMO_CODE";
        }
        else {
            if (isSubsetMode)
                pagingWhere = ".T058X WHERE (COMPANY_CODE = :company_code) AND (PRODUCT_PREFIX = :product_prefix) AND (TABLE_SUBSET = :table_subset) AND ((EFFECTIVE_DATE < :effective_date) OR (EFFECTIVE_DATE = :effective_date AND PLAN_CODE < :plan_code) OR (EFFECTIVE_DATE = :effective_date AND PLAN_CODE = :plan_code AND SEX < :sex) OR (EFFECTIVE_DATE = :effective_date AND PLAN_CODE = :plan_code AND SEX = :sex AND PAYOR_MAX_AGE < :payor_max_age) OR (EFFECTIVE_DATE = :effective_date AND PLAN_CODE = :plan_code AND SEX = :sex AND PAYOR_MAX_AGE = :payor_max_age AND INSURED_MAX_AGE < :insured_max_age) OR (EFFECTIVE_DATE = :effective_date AND PLAN_CODE = :plan_code AND SEX = :sex AND PAYOR_MAX_AGE = :payor_max_age AND INSURED_MAX_AGE = :insured_max_age AND COVER_DURATION < :cover_duration) OR (EFFECTIVE_DATE = :effective_date AND PLAN_CODE = :plan_code AND SEX = :sex AND PAYOR_MAX_AGE = :payor_max_age AND INSURED_MAX_AGE = :insured_max_age AND COVER_DURATION = :cover_duration AND MEMO_CODE < :memo_code)) ";
            else
                pagingWhere = ".T058X WHERE (COMPANY_CODE = :company_code) AND ((PRODUCT_PREFIX < :product_prefix) OR (PRODUCT_PREFIX = :product_prefix AND TABLE_SUBSET < :table_subset) OR (PRODUCT_PREFIX = :product_prefix AND TABLE_SUBSET = :table_subset AND EFFECTIVE_DATE < :effective_date) OR (PRODUCT_PREFIX = :product_prefix AND TABLE_SUBSET = :table_subset AND EFFECTIVE_DATE = :effective_date AND PLAN_CODE < :plan_code) OR (PRODUCT_PREFIX = :product_prefix AND TABLE_SUBSET = :table_subset AND EFFECTIVE_DATE = :effective_date AND PLAN_CODE = :plan_code AND SEX < :sex) OR (PRODUCT_PREFIX = :product_prefix AND TABLE_SUBSET = :table_subset AND EFFECTIVE_DATE = :effective_date AND PLAN_CODE = :plan_code AND SEX = :sex AND PAYOR_MAX_AGE < :payor_max_age) OR (PRODUCT_PREFIX = :product_prefix AND TABLE_SUBSET = :table_subset AND EFFECTIVE_DATE = :effective_date AND PLAN_CODE = :plan_code AND SEX = :sex AND PAYOR_MAX_AGE = :payor_max_age AND INSURED_MAX_AGE < :insured_max_age) OR (PRODUCT_PREFIX = :product_prefix AND TABLE_SUBSET = :table_subset AND EFFECTIVE_DATE = :effective_date AND PLAN_CODE = :plan_code AND SEX = :sex AND PAYOR_MAX_AGE = :payor_max_age AND INSURED_MAX_AGE = :insured_max_age AND COVER_DURATION < :cover_duration) OR (PRODUCT_PREFIX = :product_prefix AND TABLE_SUBSET = :table_subset AND EFFECTIVE_DATE = :effective_date AND PLAN_CODE = :plan_code AND SEX = :sex AND PAYOR_MAX_AGE = :payor_max_age AND INSURED_MAX_AGE = :insured_max_age AND COVER_DURATION = :cover_duration AND MEMO_CODE < :memo_code)) ";
            order = " ORDER BY COMPANY_CODE DESC, PRODUCT_PREFIX DESC, TABLE_SUBSET DESC, EFFECTIVE_DATE DESC, PLAN_CODE DESC, SEX DESC, PAYOR_MAX_AGE DESC, INSURED_MAX_AGE DESC, COVER_DURATION DESC, MEMO_CODE DESC";
        }
        return pagingSql + schemaName + pagingWhere + order;
    }
    
    public String prepareInsertStmt(String schemaName) {
        StringBuffer sb = new StringBuffer();
        sb.append("INSERT INTO ").append(schemaName);
        sb.append(".T058X ( ");
        sb.append("COMPANY_CODE, PRODUCT_PREFIX, TABLE_SUBSET, EFFECTIVE_DATE, PLAN_CODE, SEX, PAYOR_MAX_AGE, INSURED_MAX_AGE, COVER_DURATION, MEMO_CODE, REGULAR_REMIT_IND, DUMP_IN_REMIT_IND, ALLOC_CHG_IND, LOANS_IND, PARTIAL_SURR_IND, FULL_SURR_IND, TRANSFER_IND, AP_CALC_TYPE_IND, AP_PW_CVG_IND, APP_WAIVER_PCT, APP_WAIVER_UNIT, TYPE_CHANGE_IND, WITHDRAWAL_IND, SCHED_ACTIVITY_IND )");
        sb.append(" VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ? )");
        return sb.toString();
    }
    
    public String prepareUpdateStmt(String schemaName)
    {
        StringBuffer sb = new StringBuffer();
        sb.append("UPDATE ").append(schemaName);
        sb.append(".T058X ");
        sb.append(" SET COMPANY_CODE = ?, PRODUCT_PREFIX = ?, TABLE_SUBSET = ?, EFFECTIVE_DATE = ?, PLAN_CODE = ?, SEX = ?, PAYOR_MAX_AGE = ?, INSURED_MAX_AGE = ?, COVER_DURATION = ?, MEMO_CODE = ?, REGULAR_REMIT_IND = ?, DUMP_IN_REMIT_IND = ?, ALLOC_CHG_IND = ?, LOANS_IND = ?, PARTIAL_SURR_IND = ?, FULL_SURR_IND = ?, TRANSFER_IND = ?, AP_CALC_TYPE_IND = ?, AP_PW_CVG_IND = ?, APP_WAIVER_PCT = ?, APP_WAIVER_UNIT = ?, TYPE_CHANGE_IND = ?, WITHDRAWAL_IND = ?, SCHED_ACTIVITY_IND = ?");
        sb.append(" WHERE COMPANY_CODE = ? AND PRODUCT_PREFIX = ? AND TABLE_SUBSET = ? AND EFFECTIVE_DATE = ? AND PLAN_CODE = ? AND SEX = ? AND PAYOR_MAX_AGE = ? AND INSURED_MAX_AGE = ? AND COVER_DURATION = ? AND MEMO_CODE = ?");
        return sb.toString();
    }
    
    public String prepareDeleteStmt(String schemaName)
    {
        StringBuffer sb = new StringBuffer();
        sb.append("DELETE FROM ").append(schemaName);
        sb.append(".T058X ");
        sb.append(" WHERE COMPANY_CODE = ? AND PRODUCT_PREFIX = ? AND TABLE_SUBSET = ? AND EFFECTIVE_DATE = ? AND PLAN_CODE = ? AND SEX = ? AND PAYOR_MAX_AGE = ? AND INSURED_MAX_AGE = ? AND COVER_DURATION = ? AND MEMO_CODE = ?");
        return sb.toString();
    }
}
