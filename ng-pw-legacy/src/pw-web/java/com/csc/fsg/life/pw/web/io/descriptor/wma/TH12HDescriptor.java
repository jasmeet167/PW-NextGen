package com.csc.fsg.life.pw.web.io.descriptor.wma;

import com.csc.fsg.life.pw.web.io.*;

public class TH12HDescriptor
    extends TableDescriptor
{
    private static final String pagingSql = "SELECT COMPANY_CODE, TABLE_SUBSET, EFFECTIVE_DATE, MAX_POLICY_DUR, MAX_RIDER_DUR, BENE_PERIOD_BASIS, BENE_PERIOD_AGE, WAIT_PERIOD_BASIS, WAIT_PERIOD, BENE_CALC_TYPE, MORTALITY_DEF, MIN_BENE_AMOUNT, MAX_BENE_AMOUNT, MIN_BENE_PERCENT, MAX_BENE_PERCENT, START_VALUE_IND, VALUE_CALC_IND, GROWTH_PERCENT, MAX_GROWTH_PERCENT, MAX_INDICATOR, AGE_DEF_TIMING_IND, CALCULATION_AGE, FREE_PERCENT, STEP_UP_BASIS, STEP_UP_FREQUENCY, STEP_UP_MODE, MAX_NO_OF_STEP_UPS, WITHDRAWAL_METHOD FROM ";
    
    public void initialize()
    {
        setRowClass(TH12HRow.class);
        setTableName("TH12H");
        setTableId("H12");
        super.initialize();
    }
    
    public void initializeColumnDescriptors()
    {
        super.initializeColumnDescriptors();
        addColumnDescriptor(new ColumnDescriptor(this,"getCompanyCode","setCompanyCode","COMPANY_CODE,1,1,3,0,true|,0,CHAR,1,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getTableSubset","setTableSubset","TABLE_SUBSET,1,2,16,0,true|,0,CHAR,1,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getEffectiveDate","setEffectiveDate","EFFECTIVE_DATE,91,3,10,0,true|,0,DATE,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getMaxPolicyDur","setMaxPolicyDur","MAX_POLICY_DUR,3,4,3,0,true|,0,INTEGER,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getMaxRiderDur","setMaxRiderDur","MAX_RIDER_DUR,3,5,3,0,true|,0,INTEGER,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getBenePeriodBasis","setBenePeriodBasis","BENE_PERIOD_BASIS,1,6,1,0,false|,0,CHAR,4,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getBenePeriodAge","setBenePeriodAge","BENE_PERIOD_AGE,3,7,3,0,false|,0,INTEGER,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getWaitPeriodBasis","setWaitPeriodBasis","WAIT_PERIOD_BASIS,1,8,1,0,false|,0,CHAR,4,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getWaitPeriod","setWaitPeriod","WAIT_PERIOD,3,9,3,0,false|,0,INTEGER,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getBeneCalcType","setBeneCalcType","BENE_CALC_TYPE,1,10,2,0,false|,0,CHAR,4,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getMortalityDef","setMortalityDef","MORTALITY_DEF,1,11,2,0,false|,0,CHAR,4,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getMinBeneAmount","setMinBeneAmount","MIN_BENE_AMOUNT,3,12,11,2,false|,0,DOUBLE,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getMaxBeneAmount","setMaxBeneAmount","MAX_BENE_AMOUNT,3,13,11,2,false|,0,DOUBLE,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getMinBenePercent","setMinBenePercent","MIN_BENE_PERCENT,3,14,5,2,false|,0,DOUBLE,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getMaxBenePercent","setMaxBenePercent","MAX_BENE_PERCENT,3,15,5,2,false|,0,DOUBLE,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getStartValueInd","setStartValueInd","START_VALUE_IND,1,16,1,0,false|,0,CHAR,4,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getValueCalcInd","setValueCalcInd","VALUE_CALC_IND,1,17,1,0,false|,0,CHAR,4,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getGrowthPercent","setGrowthPercent","GROWTH_PERCENT,3,18,5,2,false|,0,DOUBLE,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getMaxGrowthPercent","setMaxGrowthPercent","MAX_GROWTH_PERCENT,3,19,5,2,false|,0,DOUBLE,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getMaxIndicator","setMaxIndicator","MAX_INDICATOR,3,20,1,0,false|,0,INTEGER,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getAgeDefTimingInd","setAgeDefTimingInd","AGE_DEF_TIMING_IND,1,21,1,0,false|,0,CHAR,4,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getCalculationAge","setCalculationAge","CALCULATION_AGE,3,22,3,0,false|,0,INTEGER,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getFreePercent","setFreePercent","FREE_PERCENT,3,23,5,2,false|,0,DOUBLE,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getStepUpBasis","setStepUpBasis","STEP_UP_BASIS,3,24,10,0,false|,0,BIGINT,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getStepUpFrequency","setStepUpFrequency","STEP_UP_FREQUENCY,3,25,3,0,false|,0,INTEGER,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getStepUpMode","setStepUpMode","STEP_UP_MODE,3,26,2,0,false|,0,INTEGER,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getMaxNoOfStepUps","setMaxNoOfStepUps","MAX_NO_OF_STEP_UPS,3,27,3,0,false|,0,INTEGER,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getWithdrawalMethod","setWithdrawalMethod","WITHDRAWAL_METHOD,3,28,10,0,false|,0,BIGINT,0,null,null,null,null,null"));
    }
    
    public String getPagingSQL(String schemaName,boolean isSubsetMode,boolean isNext, boolean locator)
    {
        String pagingWhere = null;
        String order = null;
        if (isNext) {
            if (isSubsetMode)
                if (locator)
                    pagingWhere = ".TH12H WHERE (COMPANY_CODE = :company_code) AND (TABLE_SUBSET = :table_subset) AND ((EFFECTIVE_DATE > :effective_date) OR (EFFECTIVE_DATE = :effective_date AND MAX_POLICY_DUR > :max_policy_dur) OR (EFFECTIVE_DATE = :effective_date AND MAX_POLICY_DUR = :max_policy_dur AND MAX_RIDER_DUR > :max_rider_dur) OR (EFFECTIVE_DATE = :effective_date AND MAX_POLICY_DUR = :max_policy_dur AND MAX_RIDER_DUR = :max_rider_dur)) ";
                else 
                    pagingWhere = ".TH12H WHERE (COMPANY_CODE = :company_code) AND (TABLE_SUBSET = :table_subset) AND ((EFFECTIVE_DATE > :effective_date) OR (EFFECTIVE_DATE = :effective_date AND MAX_POLICY_DUR > :max_policy_dur) OR (EFFECTIVE_DATE = :effective_date AND MAX_POLICY_DUR = :max_policy_dur AND MAX_RIDER_DUR > :max_rider_dur)) ";
            else
                if (locator)
                    pagingWhere = ".TH12H WHERE (COMPANY_CODE = :company_code) AND ((TABLE_SUBSET > :table_subset) OR (TABLE_SUBSET = :table_subset AND EFFECTIVE_DATE > :effective_date) OR (TABLE_SUBSET = :table_subset AND EFFECTIVE_DATE = :effective_date AND MAX_POLICY_DUR > :max_policy_dur) OR (TABLE_SUBSET = :table_subset AND EFFECTIVE_DATE = :effective_date AND MAX_POLICY_DUR = :max_policy_dur AND MAX_RIDER_DUR > :max_rider_dur) OR (TABLE_SUBSET = :table_subset AND EFFECTIVE_DATE = :effective_date AND MAX_POLICY_DUR = :max_policy_dur AND MAX_RIDER_DUR = :max_rider_dur)) ";
                else
                    pagingWhere = ".TH12H WHERE (COMPANY_CODE = :company_code) AND ((TABLE_SUBSET > :table_subset) OR (TABLE_SUBSET = :table_subset AND EFFECTIVE_DATE > :effective_date) OR (TABLE_SUBSET = :table_subset AND EFFECTIVE_DATE = :effective_date AND MAX_POLICY_DUR > :max_policy_dur) OR (TABLE_SUBSET = :table_subset AND EFFECTIVE_DATE = :effective_date AND MAX_POLICY_DUR = :max_policy_dur AND MAX_RIDER_DUR > :max_rider_dur)) ";
            order = " ORDER BY COMPANY_CODE, TABLE_SUBSET, EFFECTIVE_DATE, MAX_POLICY_DUR, MAX_RIDER_DUR";
        }
        else {
            if (isSubsetMode)
                pagingWhere = ".TH12H WHERE (COMPANY_CODE = :company_code) AND (TABLE_SUBSET = :table_subset) AND ((EFFECTIVE_DATE < :effective_date) OR (EFFECTIVE_DATE = :effective_date AND MAX_POLICY_DUR < :max_policy_dur) OR (EFFECTIVE_DATE = :effective_date AND MAX_POLICY_DUR = :max_policy_dur AND MAX_RIDER_DUR < :max_rider_dur)) ";
            else
                pagingWhere = ".TH12H WHERE (COMPANY_CODE = :company_code) AND ((TABLE_SUBSET < :table_subset) OR (TABLE_SUBSET = :table_subset AND EFFECTIVE_DATE < :effective_date) OR (TABLE_SUBSET = :table_subset AND EFFECTIVE_DATE = :effective_date AND MAX_POLICY_DUR < :max_policy_dur) OR (TABLE_SUBSET = :table_subset AND EFFECTIVE_DATE = :effective_date AND MAX_POLICY_DUR = :max_policy_dur AND MAX_RIDER_DUR < :max_rider_dur)) ";
            order = " ORDER BY COMPANY_CODE DESC, TABLE_SUBSET DESC, EFFECTIVE_DATE DESC, MAX_POLICY_DUR DESC, MAX_RIDER_DUR DESC";
        }
        return pagingSql + schemaName + pagingWhere + order;
    }
    
    public String prepareInsertStmt(String schemaName) {
        StringBuffer sb = new StringBuffer();
        sb.append("INSERT INTO ").append(schemaName);
        sb.append(".TH12H ( ");
        sb.append("COMPANY_CODE, TABLE_SUBSET, EFFECTIVE_DATE, MAX_POLICY_DUR, MAX_RIDER_DUR, BENE_PERIOD_BASIS, BENE_PERIOD_AGE, WAIT_PERIOD_BASIS, WAIT_PERIOD, BENE_CALC_TYPE, MORTALITY_DEF, MIN_BENE_AMOUNT, MAX_BENE_AMOUNT, MIN_BENE_PERCENT, MAX_BENE_PERCENT, START_VALUE_IND, VALUE_CALC_IND, GROWTH_PERCENT, MAX_GROWTH_PERCENT, MAX_INDICATOR, AGE_DEF_TIMING_IND, CALCULATION_AGE, FREE_PERCENT, STEP_UP_BASIS, STEP_UP_FREQUENCY, STEP_UP_MODE, MAX_NO_OF_STEP_UPS, WITHDRAWAL_METHOD )");
        sb.append(" VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ? )");
        return sb.toString();
    }
    
    public String prepareUpdateStmt(String schemaName)
    {
        StringBuffer sb = new StringBuffer();
        sb.append("UPDATE ").append(schemaName);
        sb.append(".TH12H ");
        sb.append(" SET COMPANY_CODE = ?, TABLE_SUBSET = ?, EFFECTIVE_DATE = ?, MAX_POLICY_DUR = ?, MAX_RIDER_DUR = ?, BENE_PERIOD_BASIS = ?, BENE_PERIOD_AGE = ?, WAIT_PERIOD_BASIS = ?, WAIT_PERIOD = ?, BENE_CALC_TYPE = ?, MORTALITY_DEF = ?, MIN_BENE_AMOUNT = ?, MAX_BENE_AMOUNT = ?, MIN_BENE_PERCENT = ?, MAX_BENE_PERCENT = ?, START_VALUE_IND = ?, VALUE_CALC_IND = ?, GROWTH_PERCENT = ?, MAX_GROWTH_PERCENT = ?, MAX_INDICATOR = ?, AGE_DEF_TIMING_IND = ?, CALCULATION_AGE = ?, FREE_PERCENT = ?, STEP_UP_BASIS = ?, STEP_UP_FREQUENCY = ?, STEP_UP_MODE = ?, MAX_NO_OF_STEP_UPS = ?, WITHDRAWAL_METHOD = ?");
        sb.append(" WHERE COMPANY_CODE = ? AND TABLE_SUBSET = ? AND EFFECTIVE_DATE = ? AND MAX_POLICY_DUR = ? AND MAX_RIDER_DUR = ?");
        return sb.toString();
    }
    
    public String prepareDeleteStmt(String schemaName)
    {
        StringBuffer sb = new StringBuffer();
        sb.append("DELETE FROM ").append(schemaName);
        sb.append(".TH12H ");
        sb.append(" WHERE COMPANY_CODE = ? AND TABLE_SUBSET = ? AND EFFECTIVE_DATE = ? AND MAX_POLICY_DUR = ? AND MAX_RIDER_DUR = ?");
        return sb.toString();
    }
}
