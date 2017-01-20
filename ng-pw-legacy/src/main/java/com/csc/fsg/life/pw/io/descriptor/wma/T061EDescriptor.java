package com.csc.fsg.life.pw.io.descriptor.wma;

import com.csc.fsg.life.pw.io.*;

public class T061EDescriptor
    extends TableDescriptor
{
    private static final String pagingSql = "SELECT COMPANY_CODE, TABLE_SUBSET, EFFECTIVE_DATE, MAX_ISSUE_AGE, MAX_NUMBER_OF_ACCUMS, WITHDRAWAL_ORDER, WITHDRAWAL_LIMIT_IND, NO_LAPSE_GRACE_PERIOD, NO_LAPSE_GUARANTEE_PERIOD, REACTIVATION_PERIOD, CASH_VALUE_ALLOC_IND, TARGET_PREMIUM_CALC_IND, NEXT_DURATION_MONTHS, JOINT_SEX_IND_LOAD, JOINT_AGE_IND_LOAD, JOINT_SEX_IND_INT, JOINT_RATING_IND_INT, REGULAR_PYMT_ADJUST_IND, DISABILITY_PYMT_ADJUST_IND, REINSTATE_PYMT_ADJUST_IND, LOAN_ADJUST_IND, COLL_INT_CREDIT_ADJUST_IND, RISK_RATE_DIVISOR, INTEREST_RATE_SUBSET, ATTAINED_AGE_SUBSET, ACCUMULATOR_SUBSET, PREM_FLOW_SPEC_SUBSET FROM ";
    
    public void initialize()
    {
        setRowClass(T061ERow.class);
        setTableName("T061E");
        setTableId("061");
        super.initialize();
    }
    
    public void initializeColumnDescriptors()
    {
        super.initializeColumnDescriptors();
        addColumnDescriptor(new ColumnDescriptor(this,"getCompanyCode","setCompanyCode","COMPANY_CODE,1,1,3,0,true|,0,CHAR,1,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getTableSubset","setTableSubset","TABLE_SUBSET,1,2,16,0,true|,0,CHAR,1,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getEffectiveDate","setEffectiveDate","EFFECTIVE_DATE,91,3,10,0,true|,0,DATE,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getMaxIssueAge","setMaxIssueAge","MAX_ISSUE_AGE,3,4,3,0,true|,0,INTEGER,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getMaxNumberOfAccums","setMaxNumberOfAccums","MAX_NUMBER_OF_ACCUMS,3,5,3,0,false|,0,INTEGER,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getWithdrawalOrder","setWithdrawalOrder","WITHDRAWAL_ORDER,1,6,1,0,false|,0,CHAR,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getWithdrawalLimitInd","setWithdrawalLimitInd","WITHDRAWAL_LIMIT_IND,1,7,1,0,false|,0,CHAR,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getNoLapseGracePeriod","setNoLapseGracePeriod","NO_LAPSE_GRACE_PERIOD,3,8,4,0,false|,0,INTEGER,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getNoLapseGuaranteePeriod","setNoLapseGuaranteePeriod","NO_LAPSE_GUARANTEE_PERIOD,3,9,4,0,false|,0,INTEGER,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getReactivationPeriod","setReactivationPeriod","REACTIVATION_PERIOD,3,10,4,0,false|,0,INTEGER,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getCashValueAllocInd","setCashValueAllocInd","CASH_VALUE_ALLOC_IND,1,11,1,0,false|,0,CHAR,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getTargetPremiumCalcInd","setTargetPremiumCalcInd","TARGET_PREMIUM_CALC_IND,1,12,1,0,false|,0,CHAR,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getNextDurationMonths","setNextDurationMonths","NEXT_DURATION_MONTHS,3,13,2,0,false|,0,INTEGER,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getJointSexIndLoad","setJointSexIndLoad","JOINT_SEX_IND_LOAD,1,14,1,0,false|,0,CHAR,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getJointAgeIndLoad","setJointAgeIndLoad","JOINT_AGE_IND_LOAD,1,15,1,0,false|,0,CHAR,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getJointSexIndInt","setJointSexIndInt","JOINT_SEX_IND_INT,1,16,1,0,false|,0,CHAR,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getJointRatingIndInt","setJointRatingIndInt","JOINT_RATING_IND_INT,1,17,1,0,false|,0,CHAR,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getRegularPymtAdjustInd","setRegularPymtAdjustInd","REGULAR_PYMT_ADJUST_IND,1,18,1,0,false|,0,CHAR,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getDisabilityPymtAdjustInd","setDisabilityPymtAdjustInd","DISABILITY_PYMT_ADJUST_IND,1,19,1,0,false|,0,CHAR,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getReinstatePymtAdjustInd","setReinstatePymtAdjustInd","REINSTATE_PYMT_ADJUST_IND,1,20,1,0,false|,0,CHAR,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getLoanAdjustInd","setLoanAdjustInd","LOAN_ADJUST_IND,1,21,1,0,false|,0,CHAR,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getCollIntCreditAdjustInd","setCollIntCreditAdjustInd","COLL_INT_CREDIT_ADJUST_IND,1,22,1,0,false|,0,CHAR,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getRiskRateDivisor","setRiskRateDivisor","RISK_RATE_DIVISOR,3,23,6,3,false|,0,DOUBLE,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getInterestRateSubset","setInterestRateSubset","INTEREST_RATE_SUBSET,1,24,16,0,false|,0,CHAR,0,062,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getAttainedAgeSubset","setAttainedAgeSubset","ATTAINED_AGE_SUBSET,1,25,16,0,false|,0,CHAR,0,005,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getAccumulatorSubset","setAccumulatorSubset","ACCUMULATOR_SUBSET,1,26,16,0,false|,0,CHAR,0,61E,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getPremFlowSpecSubset","setPremFlowSpecSubset","PREM_FLOW_SPEC_SUBSET,1,27,16,0,false|,0,CHAR,0,083,null,null,null,null"));
    }
    
    public String getPagingSQL(String schemaName,boolean isSubsetMode,boolean isNext, boolean locator)
    {
        String pagingWhere = null;
        String order = null;
        if (isNext) {
            if (isSubsetMode)
                if (locator)
                    pagingWhere = ".T061E WHERE (COMPANY_CODE = :company_code) AND (TABLE_SUBSET = :table_subset) AND ((EFFECTIVE_DATE > :effective_date) OR (EFFECTIVE_DATE = :effective_date AND MAX_ISSUE_AGE > :max_issue_age) OR (EFFECTIVE_DATE = :effective_date AND MAX_ISSUE_AGE = :max_issue_age)) ";
                else 
                    pagingWhere = ".T061E WHERE (COMPANY_CODE = :company_code) AND (TABLE_SUBSET = :table_subset) AND ((EFFECTIVE_DATE > :effective_date) OR (EFFECTIVE_DATE = :effective_date AND MAX_ISSUE_AGE > :max_issue_age)) ";
            else
                if (locator)
                    pagingWhere = ".T061E WHERE (COMPANY_CODE = :company_code) AND ((TABLE_SUBSET > :table_subset) OR (TABLE_SUBSET = :table_subset AND EFFECTIVE_DATE > :effective_date) OR (TABLE_SUBSET = :table_subset AND EFFECTIVE_DATE = :effective_date AND MAX_ISSUE_AGE > :max_issue_age) OR (TABLE_SUBSET = :table_subset AND EFFECTIVE_DATE = :effective_date AND MAX_ISSUE_AGE = :max_issue_age)) ";
                else
                    pagingWhere = ".T061E WHERE (COMPANY_CODE = :company_code) AND ((TABLE_SUBSET > :table_subset) OR (TABLE_SUBSET = :table_subset AND EFFECTIVE_DATE > :effective_date) OR (TABLE_SUBSET = :table_subset AND EFFECTIVE_DATE = :effective_date AND MAX_ISSUE_AGE > :max_issue_age)) ";
            order = " ORDER BY COMPANY_CODE, TABLE_SUBSET, EFFECTIVE_DATE, MAX_ISSUE_AGE";
        }
        else {
            if (isSubsetMode)
                pagingWhere = ".T061E WHERE (COMPANY_CODE = :company_code) AND (TABLE_SUBSET = :table_subset) AND ((EFFECTIVE_DATE < :effective_date) OR (EFFECTIVE_DATE = :effective_date AND MAX_ISSUE_AGE < :max_issue_age)) ";
            else
                pagingWhere = ".T061E WHERE (COMPANY_CODE = :company_code) AND ((TABLE_SUBSET < :table_subset) OR (TABLE_SUBSET = :table_subset AND EFFECTIVE_DATE < :effective_date) OR (TABLE_SUBSET = :table_subset AND EFFECTIVE_DATE = :effective_date AND MAX_ISSUE_AGE < :max_issue_age)) ";
            order = " ORDER BY COMPANY_CODE DESC, TABLE_SUBSET DESC, EFFECTIVE_DATE DESC, MAX_ISSUE_AGE DESC";
        }
        return pagingSql + schemaName + pagingWhere + order;
    }
    
    public String prepareInsertStmt(String schemaName) {
        StringBuffer sb = new StringBuffer();
        sb.append("INSERT INTO ").append(schemaName);
        sb.append(".T061E ( ");
        sb.append("COMPANY_CODE, TABLE_SUBSET, EFFECTIVE_DATE, MAX_ISSUE_AGE, MAX_NUMBER_OF_ACCUMS, WITHDRAWAL_ORDER, WITHDRAWAL_LIMIT_IND, NO_LAPSE_GRACE_PERIOD, NO_LAPSE_GUARANTEE_PERIOD, REACTIVATION_PERIOD, CASH_VALUE_ALLOC_IND, TARGET_PREMIUM_CALC_IND, NEXT_DURATION_MONTHS, JOINT_SEX_IND_LOAD, JOINT_AGE_IND_LOAD, JOINT_SEX_IND_INT, JOINT_RATING_IND_INT, REGULAR_PYMT_ADJUST_IND, DISABILITY_PYMT_ADJUST_IND, REINSTATE_PYMT_ADJUST_IND, LOAN_ADJUST_IND, COLL_INT_CREDIT_ADJUST_IND, RISK_RATE_DIVISOR, INTEREST_RATE_SUBSET, ATTAINED_AGE_SUBSET, ACCUMULATOR_SUBSET, PREM_FLOW_SPEC_SUBSET )");
        sb.append(" VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ? )");
        return sb.toString();
    }
    
    public String prepareUpdateStmt(String schemaName)
    {
        StringBuffer sb = new StringBuffer();
        sb.append("UPDATE ").append(schemaName);
        sb.append(".T061E ");
        sb.append(" SET COMPANY_CODE = ?, TABLE_SUBSET = ?, EFFECTIVE_DATE = ?, MAX_ISSUE_AGE = ?, MAX_NUMBER_OF_ACCUMS = ?, WITHDRAWAL_ORDER = ?, WITHDRAWAL_LIMIT_IND = ?, NO_LAPSE_GRACE_PERIOD = ?, NO_LAPSE_GUARANTEE_PERIOD = ?, REACTIVATION_PERIOD = ?, CASH_VALUE_ALLOC_IND = ?, TARGET_PREMIUM_CALC_IND = ?, NEXT_DURATION_MONTHS = ?, JOINT_SEX_IND_LOAD = ?, JOINT_AGE_IND_LOAD = ?, JOINT_SEX_IND_INT = ?, JOINT_RATING_IND_INT = ?, REGULAR_PYMT_ADJUST_IND = ?, DISABILITY_PYMT_ADJUST_IND = ?, REINSTATE_PYMT_ADJUST_IND = ?, LOAN_ADJUST_IND = ?, COLL_INT_CREDIT_ADJUST_IND = ?, RISK_RATE_DIVISOR = ?, INTEREST_RATE_SUBSET = ?, ATTAINED_AGE_SUBSET = ?, ACCUMULATOR_SUBSET = ?, PREM_FLOW_SPEC_SUBSET = ?");
        sb.append(" WHERE COMPANY_CODE = ? AND TABLE_SUBSET = ? AND EFFECTIVE_DATE = ? AND MAX_ISSUE_AGE = ?");
        return sb.toString();
    }
    
    public String prepareDeleteStmt(String schemaName)
    {
        StringBuffer sb = new StringBuffer();
        sb.append("DELETE FROM ").append(schemaName);
        sb.append(".T061E ");
        sb.append(" WHERE COMPANY_CODE = ? AND TABLE_SUBSET = ? AND EFFECTIVE_DATE = ? AND MAX_ISSUE_AGE = ?");
        return sb.toString();
    }
}
