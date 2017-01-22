package com.csc.fsg.life.pw.web.io.descriptor.wma;

import com.csc.fsg.life.pw.web.io.*;

public class TA04FDescriptor
    extends TableDescriptor
{
    private static final String pagingSql = "SELECT COMPANY_CODE, TABLE_SUBSET, EFFECTIVE_DATE, LINE_OF_BUSINESS, MEMO_CODE, PLAN_DESCRIPTION, AGE_DEF_CDE, AGE_DEF_TIMING_CDE, BLENDED_RATE_IND, OUT_FND_TRNS_YR_IN, NUM_ALLOW_TRANS_YR, NUM_OUT_FREE_TR_YR, CANADIAN_MIN_DB_PC, CANADIAN_MIN_MB_PC, BYPASS_VAR_PROC_IN, FUTURE_VALUE_DAYS, ANNUITY_LEAD_DAYS, AUTO_ANNUITY_IND, ANN_STATE_TYPE, TAMRA_403B_PROCESS, FUTURE_PROCESS_DAY, MRD_INDICATOR, MRD_NOTICE_DAYS, MRD_INTERVAL_DAYS, CAN_TKC_MIN_DIST_D, COMPARE_IND, COMPARE_PERIOD, COMPARE_DURATION, MAX_CONTINUE_DUR, CONTINGENT_ANN_IND, EIA_INDICATOR, PREMIUM_BONUS_IND, OA_DRIVEN_IND, DATE_RECALC_IND, REMIT_AGE_CALC_IND FROM ";
    
    public void initialize()
    {
        setRowClass(TA04FRow.class);
        setTableName("TA04F");
        setTableId("A04");
        super.initialize();
    }
    
    public void initializeColumnDescriptors()
    {
        super.initializeColumnDescriptors();
        addColumnDescriptor(new ColumnDescriptor(this,"getCompanyCode","setCompanyCode","COMPANY_CODE,1,1,3,0,true|,0,CHAR,1,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getTableSubset","setTableSubset","TABLE_SUBSET,1,2,16,0,true|,0,CHAR,1,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getEffectiveDate","setEffectiveDate","EFFECTIVE_DATE,91,3,10,0,true|,0,DATE,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getLineOfBusiness","setLineOfBusiness","LINE_OF_BUSINESS,1,4,3,0,true|,0,CHAR,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getMemoCode","setMemoCode","MEMO_CODE,1,5,2,0,true|,0,CHAR,4,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getPlanDescription","setPlanDescription","PLAN_DESCRIPTION,1,6,20,0,false|,0,CHAR,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getAgeDefCde","setAgeDefCde","AGE_DEF_CDE,1,7,1,0,false|,0,CHAR,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getAgeDefTimingCde","setAgeDefTimingCde","AGE_DEF_TIMING_CDE,1,8,1,0,false|,0,CHAR,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getBlendedRateInd","setBlendedRateInd","BLENDED_RATE_IND,1,9,1,0,false|,0,CHAR,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getOutFndTrnsYrIn","setOutFndTrnsYrIn","OUT_FND_TRNS_YR_IN,1,10,1,0,false|,0,CHAR,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getNumAllowTransYr","setNumAllowTransYr","NUM_ALLOW_TRANS_YR,3,11,3,0,false|,0,INTEGER,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getNumOutFreeTrYr","setNumOutFreeTrYr","NUM_OUT_FREE_TR_YR,3,12,3,0,false|,0,INTEGER,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getCanadianMinDbPc","setCanadianMinDbPc","CANADIAN_MIN_DB_PC,3,13,3,1,false|,0,DOUBLE,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getCanadianMinMbPc","setCanadianMinMbPc","CANADIAN_MIN_MB_PC,3,14,3,1,false|,0,DOUBLE,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getBypassVarProcIn","setBypassVarProcIn","BYPASS_VAR_PROC_IN,1,15,1,0,false|,0,CHAR,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getFutureValueDays","setFutureValueDays","FUTURE_VALUE_DAYS,3,16,3,0,false|,0,INTEGER,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getAnnuityLeadDays","setAnnuityLeadDays","ANNUITY_LEAD_DAYS,3,17,3,0,false|,0,INTEGER,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getAutoAnnuityInd","setAutoAnnuityInd","AUTO_ANNUITY_IND,1,18,1,0,false|,0,CHAR,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getAnnStateType","setAnnStateType","ANN_STATE_TYPE,1,19,1,0,false|,0,CHAR,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getTamra403bProcess","setTamra403bProcess","TAMRA_403B_PROCESS,1,20,1,0,false|,0,CHAR,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getFutureProcessDay","setFutureProcessDay","FUTURE_PROCESS_DAY,3,21,3,0,false|,0,INTEGER,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getMrdIndicator","setMrdIndicator","MRD_INDICATOR,1,22,1,0,false|,0,CHAR,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getMrdNoticeDays","setMrdNoticeDays","MRD_NOTICE_DAYS,3,23,3,0,false|,0,INTEGER,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getMrdIntervalDays","setMrdIntervalDays","MRD_INTERVAL_DAYS,3,24,3,0,false|,0,INTEGER,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getCanTkcMinDistD","setCanTkcMinDistD","CAN_TKC_MIN_DIST_D,3,25,3,0,false|,0,INTEGER,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getCompareInd","setCompareInd","COMPARE_IND,1,26,1,0,false|,0,CHAR,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getComparePeriod","setComparePeriod","COMPARE_PERIOD,3,27,3,0,false|,0,INTEGER,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getCompareDuration","setCompareDuration","COMPARE_DURATION,3,28,3,0,false|,0,INTEGER,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getMaxContinueDur","setMaxContinueDur","MAX_CONTINUE_DUR,3,29,3,0,false|,0,INTEGER,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getContingentAnnInd","setContingentAnnInd","CONTINGENT_ANN_IND,1,30,1,0,false|,0,CHAR,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getEiaIndicator","setEiaIndicator","EIA_INDICATOR,1,31,1,0,false|,0,CHAR,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getPremiumBonusInd","setPremiumBonusInd","PREMIUM_BONUS_IND,1,32,1,0,false|,0,CHAR,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getOaDrivenInd","setOaDrivenInd","OA_DRIVEN_IND,1,33,1,0,false|,0,CHAR,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getDateRecalcInd","setDateRecalcInd","DATE_RECALC_IND,1,34,1,0,false|,0,CHAR,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getRemitAgeCalcInd","setRemitAgeCalcInd","REMIT_AGE_CALC_IND,1,35,2,0,false|,0,CHAR,0,null,null,null,null,null"));
    }
    
    public String getPagingSQL(String schemaName,boolean isSubsetMode,boolean isNext, boolean locator)
    {
        String pagingWhere = null;
        String order = null;
        if (isNext) {
            if (isSubsetMode)
                if (locator)
                    pagingWhere = ".TA04F WHERE (COMPANY_CODE = :company_code) AND (TABLE_SUBSET = :table_subset) AND ((EFFECTIVE_DATE > :effective_date) OR (EFFECTIVE_DATE = :effective_date AND LINE_OF_BUSINESS > :line_of_business) OR (EFFECTIVE_DATE = :effective_date AND LINE_OF_BUSINESS = :line_of_business AND MEMO_CODE > :memo_code) OR (EFFECTIVE_DATE = :effective_date AND LINE_OF_BUSINESS = :line_of_business AND MEMO_CODE = :memo_code)) ";
                else 
                    pagingWhere = ".TA04F WHERE (COMPANY_CODE = :company_code) AND (TABLE_SUBSET = :table_subset) AND ((EFFECTIVE_DATE > :effective_date) OR (EFFECTIVE_DATE = :effective_date AND LINE_OF_BUSINESS > :line_of_business) OR (EFFECTIVE_DATE = :effective_date AND LINE_OF_BUSINESS = :line_of_business AND MEMO_CODE > :memo_code)) ";
            else
                if (locator)
                    pagingWhere = ".TA04F WHERE (COMPANY_CODE = :company_code) AND ((TABLE_SUBSET > :table_subset) OR (TABLE_SUBSET = :table_subset AND EFFECTIVE_DATE > :effective_date) OR (TABLE_SUBSET = :table_subset AND EFFECTIVE_DATE = :effective_date AND LINE_OF_BUSINESS > :line_of_business) OR (TABLE_SUBSET = :table_subset AND EFFECTIVE_DATE = :effective_date AND LINE_OF_BUSINESS = :line_of_business AND MEMO_CODE > :memo_code) OR (TABLE_SUBSET = :table_subset AND EFFECTIVE_DATE = :effective_date AND LINE_OF_BUSINESS = :line_of_business AND MEMO_CODE = :memo_code)) ";
                else
                    pagingWhere = ".TA04F WHERE (COMPANY_CODE = :company_code) AND ((TABLE_SUBSET > :table_subset) OR (TABLE_SUBSET = :table_subset AND EFFECTIVE_DATE > :effective_date) OR (TABLE_SUBSET = :table_subset AND EFFECTIVE_DATE = :effective_date AND LINE_OF_BUSINESS > :line_of_business) OR (TABLE_SUBSET = :table_subset AND EFFECTIVE_DATE = :effective_date AND LINE_OF_BUSINESS = :line_of_business AND MEMO_CODE > :memo_code)) ";
            order = " ORDER BY COMPANY_CODE, TABLE_SUBSET, EFFECTIVE_DATE, LINE_OF_BUSINESS, MEMO_CODE";
        }
        else {
            if (isSubsetMode)
                pagingWhere = ".TA04F WHERE (COMPANY_CODE = :company_code) AND (TABLE_SUBSET = :table_subset) AND ((EFFECTIVE_DATE < :effective_date) OR (EFFECTIVE_DATE = :effective_date AND LINE_OF_BUSINESS < :line_of_business) OR (EFFECTIVE_DATE = :effective_date AND LINE_OF_BUSINESS = :line_of_business AND MEMO_CODE < :memo_code)) ";
            else
                pagingWhere = ".TA04F WHERE (COMPANY_CODE = :company_code) AND ((TABLE_SUBSET < :table_subset) OR (TABLE_SUBSET = :table_subset AND EFFECTIVE_DATE < :effective_date) OR (TABLE_SUBSET = :table_subset AND EFFECTIVE_DATE = :effective_date AND LINE_OF_BUSINESS < :line_of_business) OR (TABLE_SUBSET = :table_subset AND EFFECTIVE_DATE = :effective_date AND LINE_OF_BUSINESS = :line_of_business AND MEMO_CODE < :memo_code)) ";
            order = " ORDER BY COMPANY_CODE DESC, TABLE_SUBSET DESC, EFFECTIVE_DATE DESC, LINE_OF_BUSINESS DESC, MEMO_CODE DESC";
        }
        return pagingSql + schemaName + pagingWhere + order;
    }
    
    public String prepareInsertStmt(String schemaName) {
        StringBuffer sb = new StringBuffer();
        sb.append("INSERT INTO ").append(schemaName);
        sb.append(".TA04F ( ");
        sb.append("COMPANY_CODE, TABLE_SUBSET, EFFECTIVE_DATE, LINE_OF_BUSINESS, MEMO_CODE, PLAN_DESCRIPTION, AGE_DEF_CDE, AGE_DEF_TIMING_CDE, BLENDED_RATE_IND, OUT_FND_TRNS_YR_IN, NUM_ALLOW_TRANS_YR, NUM_OUT_FREE_TR_YR, CANADIAN_MIN_DB_PC, CANADIAN_MIN_MB_PC, BYPASS_VAR_PROC_IN, FUTURE_VALUE_DAYS, ANNUITY_LEAD_DAYS, AUTO_ANNUITY_IND, ANN_STATE_TYPE, TAMRA_403B_PROCESS, FUTURE_PROCESS_DAY, MRD_INDICATOR, MRD_NOTICE_DAYS, MRD_INTERVAL_DAYS, CAN_TKC_MIN_DIST_D, COMPARE_IND, COMPARE_PERIOD, COMPARE_DURATION, MAX_CONTINUE_DUR, CONTINGENT_ANN_IND, EIA_INDICATOR, PREMIUM_BONUS_IND, OA_DRIVEN_IND, DATE_RECALC_IND, REMIT_AGE_CALC_IND )");
        sb.append(" VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ? )");
        return sb.toString();
    }
    
    public String prepareUpdateStmt(String schemaName)
    {
        StringBuffer sb = new StringBuffer();
        sb.append("UPDATE ").append(schemaName);
        sb.append(".TA04F ");
        sb.append(" SET COMPANY_CODE = ?, TABLE_SUBSET = ?, EFFECTIVE_DATE = ?, LINE_OF_BUSINESS = ?, MEMO_CODE = ?, PLAN_DESCRIPTION = ?, AGE_DEF_CDE = ?, AGE_DEF_TIMING_CDE = ?, BLENDED_RATE_IND = ?, OUT_FND_TRNS_YR_IN = ?, NUM_ALLOW_TRANS_YR = ?, NUM_OUT_FREE_TR_YR = ?, CANADIAN_MIN_DB_PC = ?, CANADIAN_MIN_MB_PC = ?, BYPASS_VAR_PROC_IN = ?, FUTURE_VALUE_DAYS = ?, ANNUITY_LEAD_DAYS = ?, AUTO_ANNUITY_IND = ?, ANN_STATE_TYPE = ?, TAMRA_403B_PROCESS = ?, FUTURE_PROCESS_DAY = ?, MRD_INDICATOR = ?, MRD_NOTICE_DAYS = ?, MRD_INTERVAL_DAYS = ?, CAN_TKC_MIN_DIST_D = ?, COMPARE_IND = ?, COMPARE_PERIOD = ?, COMPARE_DURATION = ?, MAX_CONTINUE_DUR = ?, CONTINGENT_ANN_IND = ?, EIA_INDICATOR = ?, PREMIUM_BONUS_IND = ?, OA_DRIVEN_IND = ?, DATE_RECALC_IND = ?, REMIT_AGE_CALC_IND = ?");
        sb.append(" WHERE COMPANY_CODE = ? AND TABLE_SUBSET = ? AND EFFECTIVE_DATE = ? AND LINE_OF_BUSINESS = ? AND MEMO_CODE = ?");
        return sb.toString();
    }
    
    public String prepareDeleteStmt(String schemaName)
    {
        StringBuffer sb = new StringBuffer();
        sb.append("DELETE FROM ").append(schemaName);
        sb.append(".TA04F ");
        sb.append(" WHERE COMPANY_CODE = ? AND TABLE_SUBSET = ? AND EFFECTIVE_DATE = ? AND LINE_OF_BUSINESS = ? AND MEMO_CODE = ?");
        return sb.toString();
    }
}
