package com.csc.fsg.life.pw.io.descriptor.wma;

import com.csc.fsg.life.pw.io.*;

public class TH13HDescriptor
    extends TableDescriptor
{
    private static final String pagingSql = "SELECT COMPANY_CODE, TABLE_SUBSET, ISSUE_STATE, EFFECTIVE_DATE, MAX_RIDER_ISS_AGE, FAV_SUBSET, CHARGE_FREQUENCY, DEDUCTION_FUND_TYP, AMT_PER_UNIT_SUBST, FLAT_AMOUNT_SUBSET, FLAT_PERCNT_SUBSET, PERCENT_CHRG_BASIS, FUND_STRGY_EXL_SUB FROM ";
    
    public void initialize()
    {
        setRowClass(TH13HRow.class);
        setTableName("TH13H");
        setTableId("H13");
        super.initialize();
    }
    
    public void initializeColumnDescriptors()
    {
        super.initializeColumnDescriptors();
        addColumnDescriptor(new ColumnDescriptor(this,"getCompanyCode","setCompanyCode","COMPANY_CODE,1,1,3,0,true|,0,CHAR,1,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getTableSubset","setTableSubset","TABLE_SUBSET,1,2,16,0,true|,0,CHAR,1,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getIssueState","setIssueState","ISSUE_STATE,1,3,3,0,true|,0,CHAR,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getEffectiveDate","setEffectiveDate","EFFECTIVE_DATE,91,4,10,0,true|,0,DATE,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getMaxRiderIssAge","setMaxRiderIssAge","MAX_RIDER_ISS_AGE,3,5,3,0,true|,0,INTEGER,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getFavSubset","setFavSubset","FAV_SUBSET,1,6,16,0,false|,0,CHAR,0,H21,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getChargeFrequency","setChargeFrequency","CHARGE_FREQUENCY,1,7,2,0,false|,0,CHAR,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getDeductionFundTyp","setDeductionFundTyp","DEDUCTION_FUND_TYP,1,8,1,0,false|,0,CHAR,4,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getAmtPerUnitSubst","setAmtPerUnitSubst","AMT_PER_UNIT_SUBST,1,9,16,0,false|,0,CHAR,0,H23,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getFlatAmountSubset","setFlatAmountSubset","FLAT_AMOUNT_SUBSET,1,10,16,0,false|,0,CHAR,0,H22,1,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getFlatPercntSubset","setFlatPercntSubset","FLAT_PERCNT_SUBSET,1,11,16,0,false|,0,CHAR,0,H22,2,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getPercentChrgBasis","setPercentChrgBasis","PERCENT_CHRG_BASIS,1,12,1,0,false|,0,CHAR,4,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getFundStrgyExlSub","setFundStrgyExlSub","FUND_STRGY_EXL_SUB,1,13,16,0,false|,0,CHAR,0,null,null,null,null,null"));
    }
    
    public String getPagingSQL(String schemaName,boolean isSubsetMode,boolean isNext, boolean locator)
    {
        String pagingWhere = null;
        String order = null;
        if (isNext) {
            if (isSubsetMode)
                if (locator)
                    pagingWhere = ".TH13H WHERE (COMPANY_CODE = :company_code) AND (TABLE_SUBSET = :table_subset) AND ((ISSUE_STATE > :issue_state) OR (ISSUE_STATE = :issue_state AND EFFECTIVE_DATE > :effective_date) OR (ISSUE_STATE = :issue_state AND EFFECTIVE_DATE = :effective_date AND MAX_RIDER_ISS_AGE > :max_rider_iss_age) OR (ISSUE_STATE = :issue_state AND EFFECTIVE_DATE = :effective_date AND MAX_RIDER_ISS_AGE = :max_rider_iss_age)) ";
                else 
                    pagingWhere = ".TH13H WHERE (COMPANY_CODE = :company_code) AND (TABLE_SUBSET = :table_subset) AND ((ISSUE_STATE > :issue_state) OR (ISSUE_STATE = :issue_state AND EFFECTIVE_DATE > :effective_date) OR (ISSUE_STATE = :issue_state AND EFFECTIVE_DATE = :effective_date AND MAX_RIDER_ISS_AGE > :max_rider_iss_age)) ";
            else
                if (locator)
                    pagingWhere = ".TH13H WHERE (COMPANY_CODE = :company_code) AND ((TABLE_SUBSET > :table_subset) OR (TABLE_SUBSET = :table_subset AND ISSUE_STATE > :issue_state) OR (TABLE_SUBSET = :table_subset AND ISSUE_STATE = :issue_state AND EFFECTIVE_DATE > :effective_date) OR (TABLE_SUBSET = :table_subset AND ISSUE_STATE = :issue_state AND EFFECTIVE_DATE = :effective_date AND MAX_RIDER_ISS_AGE > :max_rider_iss_age) OR (TABLE_SUBSET = :table_subset AND ISSUE_STATE = :issue_state AND EFFECTIVE_DATE = :effective_date AND MAX_RIDER_ISS_AGE = :max_rider_iss_age)) ";
                else
                    pagingWhere = ".TH13H WHERE (COMPANY_CODE = :company_code) AND ((TABLE_SUBSET > :table_subset) OR (TABLE_SUBSET = :table_subset AND ISSUE_STATE > :issue_state) OR (TABLE_SUBSET = :table_subset AND ISSUE_STATE = :issue_state AND EFFECTIVE_DATE > :effective_date) OR (TABLE_SUBSET = :table_subset AND ISSUE_STATE = :issue_state AND EFFECTIVE_DATE = :effective_date AND MAX_RIDER_ISS_AGE > :max_rider_iss_age)) ";
            order = " ORDER BY COMPANY_CODE, TABLE_SUBSET, ISSUE_STATE, EFFECTIVE_DATE, MAX_RIDER_ISS_AGE";
        }
        else {
            if (isSubsetMode)
                pagingWhere = ".TH13H WHERE (COMPANY_CODE = :company_code) AND (TABLE_SUBSET = :table_subset) AND ((ISSUE_STATE < :issue_state) OR (ISSUE_STATE = :issue_state AND EFFECTIVE_DATE < :effective_date) OR (ISSUE_STATE = :issue_state AND EFFECTIVE_DATE = :effective_date AND MAX_RIDER_ISS_AGE < :max_rider_iss_age)) ";
            else
                pagingWhere = ".TH13H WHERE (COMPANY_CODE = :company_code) AND ((TABLE_SUBSET < :table_subset) OR (TABLE_SUBSET = :table_subset AND ISSUE_STATE < :issue_state) OR (TABLE_SUBSET = :table_subset AND ISSUE_STATE = :issue_state AND EFFECTIVE_DATE < :effective_date) OR (TABLE_SUBSET = :table_subset AND ISSUE_STATE = :issue_state AND EFFECTIVE_DATE = :effective_date AND MAX_RIDER_ISS_AGE < :max_rider_iss_age)) ";
            order = " ORDER BY COMPANY_CODE DESC, TABLE_SUBSET DESC, ISSUE_STATE DESC, EFFECTIVE_DATE DESC, MAX_RIDER_ISS_AGE DESC";
        }
        return pagingSql + schemaName + pagingWhere + order;
    }
    
    public String prepareInsertStmt(String schemaName) {
        StringBuffer sb = new StringBuffer();
        sb.append("INSERT INTO ").append(schemaName);
        sb.append(".TH13H ( ");
        sb.append("COMPANY_CODE, TABLE_SUBSET, ISSUE_STATE, EFFECTIVE_DATE, MAX_RIDER_ISS_AGE, FAV_SUBSET, CHARGE_FREQUENCY, DEDUCTION_FUND_TYP, AMT_PER_UNIT_SUBST, FLAT_AMOUNT_SUBSET, FLAT_PERCNT_SUBSET, PERCENT_CHRG_BASIS, FUND_STRGY_EXL_SUB )");
        sb.append(" VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ? )");
        return sb.toString();
    }
    
    public String prepareUpdateStmt(String schemaName)
    {
        StringBuffer sb = new StringBuffer();
        sb.append("UPDATE ").append(schemaName);
        sb.append(".TH13H ");
        sb.append(" SET COMPANY_CODE = ?, TABLE_SUBSET = ?, ISSUE_STATE = ?, EFFECTIVE_DATE = ?, MAX_RIDER_ISS_AGE = ?, FAV_SUBSET = ?, CHARGE_FREQUENCY = ?, DEDUCTION_FUND_TYP = ?, AMT_PER_UNIT_SUBST = ?, FLAT_AMOUNT_SUBSET = ?, FLAT_PERCNT_SUBSET = ?, PERCENT_CHRG_BASIS = ?, FUND_STRGY_EXL_SUB = ?");
        sb.append(" WHERE COMPANY_CODE = ? AND TABLE_SUBSET = ? AND ISSUE_STATE = ? AND EFFECTIVE_DATE = ? AND MAX_RIDER_ISS_AGE = ?");
        return sb.toString();
    }
    
    public String prepareDeleteStmt(String schemaName)
    {
        StringBuffer sb = new StringBuffer();
        sb.append("DELETE FROM ").append(schemaName);
        sb.append(".TH13H ");
        sb.append(" WHERE COMPANY_CODE = ? AND TABLE_SUBSET = ? AND ISSUE_STATE = ? AND EFFECTIVE_DATE = ? AND MAX_RIDER_ISS_AGE = ?");
        return sb.toString();
    }
}
