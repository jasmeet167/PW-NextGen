package com.csc.fsg.life.pw.web.io.descriptor.wma;

import com.csc.fsg.life.pw.web.io.*;

public class TH11HDescriptor
    extends TableDescriptor
{
    private static final String pagingSql = "SELECT COMPANY_CODE, TABLE_SUBSET, EFFECTIVE_DATE, MAX_POLICY_DUR, MAX_ISSUE_AGE, RIDER_ADD_COMM_IND, PER_DED_COMM_IND, MIN_ISSUE_UNITS, MAX_ISSUE_UNITS, MIN_ISSUE_AGE, COV_TERM_BASIS, COV_TERM_AGE, PER_CHRG_TERM_BAS, PER_CHRG_TERM_AGE, VALUE_CALC_IND, MINIMUM_VALUE, TERMINATION_IND, COMM_DT_OVER_IND, UPGRADE_RULE_IND, UPGRD_STRT_VAL_IND, UPGRADE_NT_DAYS, WIND_DAYS_FOR_UPGD, CALCULATION_BASIS FROM ";
    
    public void initialize()
    {
        setRowClass(TH11HRow.class);
        setTableName("TH11H");
        setTableId("H11");
        super.initialize();
    }
    
    public void initializeColumnDescriptors()
    {
        super.initializeColumnDescriptors();
        addColumnDescriptor(new ColumnDescriptor(this,"getCompanyCode","setCompanyCode","COMPANY_CODE,1,1,3,0,true|,0,CHAR,1,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getTableSubset","setTableSubset","TABLE_SUBSET,1,2,16,0,true|,0,CHAR,1,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getEffectiveDate","setEffectiveDate","EFFECTIVE_DATE,91,3,10,0,true|,0,DATE,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getMaxPolicyDur","setMaxPolicyDur","MAX_POLICY_DUR,3,4,3,0,true|,0,INTEGER,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getMaxIssueAge","setMaxIssueAge","MAX_ISSUE_AGE,3,5,3,0,true|,0,INTEGER,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getRiderAddCommInd","setRiderAddCommInd","RIDER_ADD_COMM_IND,1,6,1,0,false|,0,CHAR,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getPerDedCommInd","setPerDedCommInd","PER_DED_COMM_IND,1,7,1,0,false|,0,CHAR,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getMinIssueUnits","setMinIssueUnits","MIN_ISSUE_UNITS,3,8,11,5,false|,0,DOUBLE,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getMaxIssueUnits","setMaxIssueUnits","MAX_ISSUE_UNITS,3,9,11,5,false|,0,DOUBLE,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getMinIssueAge","setMinIssueAge","MIN_ISSUE_AGE,3,10,3,0,false|,0,INTEGER,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getCovTermBasis","setCovTermBasis","COV_TERM_BASIS,1,11,1,0,false|,0,CHAR,4,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getCovTermAge","setCovTermAge","COV_TERM_AGE,3,12,3,0,false|,0,INTEGER,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getPerChrgTermBas","setPerChrgTermBas","PER_CHRG_TERM_BAS,1,13,1,0,false|,0,CHAR,4,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getPerChrgTermAge","setPerChrgTermAge","PER_CHRG_TERM_AGE,3,14,3,0,false|,0,INTEGER,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getValueCalcInd","setValueCalcInd","VALUE_CALC_IND,1,15,1,0,false|,0,CHAR,4,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getMinimumValue","setMinimumValue","MINIMUM_VALUE,3,16,11,2,false|,0,DOUBLE,1,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getTerminationInd","setTerminationInd","TERMINATION_IND,1,17,1,0,false|,0,CHAR,4,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getCommDtOverInd","setCommDtOverInd","COMM_DT_OVER_IND,1,18,1,0,false|,0,CHAR,4,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getUpgradeRuleInd","setUpgradeRuleInd","UPGRADE_RULE_IND,1,19,1,0,false|,0,CHAR,4,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getUpgrdStrtValInd","setUpgrdStrtValInd","UPGRD_STRT_VAL_IND,1,20,2,0,false|,0,CHAR,4,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getUpgradeNtDays","setUpgradeNtDays","UPGRADE_NT_DAYS,3,21,3,0,false|,0,INTEGER,1,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getWindDaysForUpgd","setWindDaysForUpgd","WIND_DAYS_FOR_UPGD,3,22,3,0,false|,0,INTEGER,1,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getCalculationBasis","setCalculationBasis","CALCULATION_BASIS,1,23,1,0,false|,0,CHAR,0,null,null,null,null,null"));
    }
    
    public String getPagingSQL(String schemaName,boolean isSubsetMode,boolean isNext, boolean locator)
    {
        String pagingWhere = null;
        String order = null;
        if (isNext) {
            if (isSubsetMode)
                if (locator)
                    pagingWhere = ".TH11H WHERE (COMPANY_CODE = :company_code) AND (TABLE_SUBSET = :table_subset) AND ((EFFECTIVE_DATE > :effective_date) OR (EFFECTIVE_DATE = :effective_date AND MAX_POLICY_DUR > :max_policy_dur) OR (EFFECTIVE_DATE = :effective_date AND MAX_POLICY_DUR = :max_policy_dur AND MAX_ISSUE_AGE > :max_issue_age) OR (EFFECTIVE_DATE = :effective_date AND MAX_POLICY_DUR = :max_policy_dur AND MAX_ISSUE_AGE = :max_issue_age)) ";
                else 
                    pagingWhere = ".TH11H WHERE (COMPANY_CODE = :company_code) AND (TABLE_SUBSET = :table_subset) AND ((EFFECTIVE_DATE > :effective_date) OR (EFFECTIVE_DATE = :effective_date AND MAX_POLICY_DUR > :max_policy_dur) OR (EFFECTIVE_DATE = :effective_date AND MAX_POLICY_DUR = :max_policy_dur AND MAX_ISSUE_AGE > :max_issue_age)) ";
            else
                if (locator)
                    pagingWhere = ".TH11H WHERE (COMPANY_CODE = :company_code) AND ((TABLE_SUBSET > :table_subset) OR (TABLE_SUBSET = :table_subset AND EFFECTIVE_DATE > :effective_date) OR (TABLE_SUBSET = :table_subset AND EFFECTIVE_DATE = :effective_date AND MAX_POLICY_DUR > :max_policy_dur) OR (TABLE_SUBSET = :table_subset AND EFFECTIVE_DATE = :effective_date AND MAX_POLICY_DUR = :max_policy_dur AND MAX_ISSUE_AGE > :max_issue_age) OR (TABLE_SUBSET = :table_subset AND EFFECTIVE_DATE = :effective_date AND MAX_POLICY_DUR = :max_policy_dur AND MAX_ISSUE_AGE = :max_issue_age)) ";
                else
                    pagingWhere = ".TH11H WHERE (COMPANY_CODE = :company_code) AND ((TABLE_SUBSET > :table_subset) OR (TABLE_SUBSET = :table_subset AND EFFECTIVE_DATE > :effective_date) OR (TABLE_SUBSET = :table_subset AND EFFECTIVE_DATE = :effective_date AND MAX_POLICY_DUR > :max_policy_dur) OR (TABLE_SUBSET = :table_subset AND EFFECTIVE_DATE = :effective_date AND MAX_POLICY_DUR = :max_policy_dur AND MAX_ISSUE_AGE > :max_issue_age)) ";
            order = " ORDER BY COMPANY_CODE, TABLE_SUBSET, EFFECTIVE_DATE, MAX_POLICY_DUR, MAX_ISSUE_AGE";
        }
        else {
            if (isSubsetMode)
                pagingWhere = ".TH11H WHERE (COMPANY_CODE = :company_code) AND (TABLE_SUBSET = :table_subset) AND ((EFFECTIVE_DATE < :effective_date) OR (EFFECTIVE_DATE = :effective_date AND MAX_POLICY_DUR < :max_policy_dur) OR (EFFECTIVE_DATE = :effective_date AND MAX_POLICY_DUR = :max_policy_dur AND MAX_ISSUE_AGE < :max_issue_age)) ";
            else
                pagingWhere = ".TH11H WHERE (COMPANY_CODE = :company_code) AND ((TABLE_SUBSET < :table_subset) OR (TABLE_SUBSET = :table_subset AND EFFECTIVE_DATE < :effective_date) OR (TABLE_SUBSET = :table_subset AND EFFECTIVE_DATE = :effective_date AND MAX_POLICY_DUR < :max_policy_dur) OR (TABLE_SUBSET = :table_subset AND EFFECTIVE_DATE = :effective_date AND MAX_POLICY_DUR = :max_policy_dur AND MAX_ISSUE_AGE < :max_issue_age)) ";
            order = " ORDER BY COMPANY_CODE DESC, TABLE_SUBSET DESC, EFFECTIVE_DATE DESC, MAX_POLICY_DUR DESC, MAX_ISSUE_AGE DESC";
        }
        return pagingSql + schemaName + pagingWhere + order;
    }
    
    public String prepareInsertStmt(String schemaName) {
        StringBuffer sb = new StringBuffer();
        sb.append("INSERT INTO ").append(schemaName);
        sb.append(".TH11H ( ");
        sb.append("COMPANY_CODE, TABLE_SUBSET, EFFECTIVE_DATE, MAX_POLICY_DUR, MAX_ISSUE_AGE, RIDER_ADD_COMM_IND, PER_DED_COMM_IND, MIN_ISSUE_UNITS, MAX_ISSUE_UNITS, MIN_ISSUE_AGE, COV_TERM_BASIS, COV_TERM_AGE, PER_CHRG_TERM_BAS, PER_CHRG_TERM_AGE, VALUE_CALC_IND, MINIMUM_VALUE, TERMINATION_IND, COMM_DT_OVER_IND, UPGRADE_RULE_IND, UPGRD_STRT_VAL_IND, UPGRADE_NT_DAYS, WIND_DAYS_FOR_UPGD, CALCULATION_BASIS )");
        sb.append(" VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ? )");
        return sb.toString();
    }
    
    public String prepareUpdateStmt(String schemaName)
    {
        StringBuffer sb = new StringBuffer();
        sb.append("UPDATE ").append(schemaName);
        sb.append(".TH11H ");
        sb.append(" SET COMPANY_CODE = ?, TABLE_SUBSET = ?, EFFECTIVE_DATE = ?, MAX_POLICY_DUR = ?, MAX_ISSUE_AGE = ?, RIDER_ADD_COMM_IND = ?, PER_DED_COMM_IND = ?, MIN_ISSUE_UNITS = ?, MAX_ISSUE_UNITS = ?, MIN_ISSUE_AGE = ?, COV_TERM_BASIS = ?, COV_TERM_AGE = ?, PER_CHRG_TERM_BAS = ?, PER_CHRG_TERM_AGE = ?, VALUE_CALC_IND = ?, MINIMUM_VALUE = ?, TERMINATION_IND = ?, COMM_DT_OVER_IND = ?, UPGRADE_RULE_IND = ?, UPGRD_STRT_VAL_IND = ?, UPGRADE_NT_DAYS = ?, WIND_DAYS_FOR_UPGD = ?, CALCULATION_BASIS = ?");
        sb.append(" WHERE COMPANY_CODE = ? AND TABLE_SUBSET = ? AND EFFECTIVE_DATE = ? AND MAX_POLICY_DUR = ? AND MAX_ISSUE_AGE = ?");
        return sb.toString();
    }
    
    public String prepareDeleteStmt(String schemaName)
    {
        StringBuffer sb = new StringBuffer();
        sb.append("DELETE FROM ").append(schemaName);
        sb.append(".TH11H ");
        sb.append(" WHERE COMPANY_CODE = ? AND TABLE_SUBSET = ? AND EFFECTIVE_DATE = ? AND MAX_POLICY_DUR = ? AND MAX_ISSUE_AGE = ?");
        return sb.toString();
    }
}
