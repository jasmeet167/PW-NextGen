package com.csc.fsg.life.pw.web.io.descriptor.wma;

import com.csc.fsg.life.pw.web.io.*;

public class T024XDescriptor
    extends TableDescriptor
{
    private static final String pagingSql = "SELECT COMPANY_CODE, PRODUCT_PREFIX, TABLE_SUBSET, FUND_NUMBER, EFFECTIVE_DATE, MEMO_CODE, DCLR_INT_RT_TBL, GUAR_INT_RT_TBL, EXCS_INT_EXCL_TBL, INTEREST_IND, ANNIVERSARY_IND, INT_CALC_MTHD, LEAP_YEAR_DAYS, ROLLOVER_FREQUENCY, ROLLOVER_PERIOD, NEW_MONEY_IND, GUARANTEE_TYPE, GUARANTEE_DURATION, BONUS_INTEREST_TBL, ROLLOVER_PERCENT, INTEREST_METHOD, MF_THRESHOLD_TBL, SECURE_PERIOD, INCLUSION_PERIOD, EXCLUSION_PERIOD, BONUS_RATE, SETTL_INT_RATE_TBL FROM ";
    
    public void initialize()
    {
        setRowClass(T024XRow.class);
        setTableName("T024X");
        setTableId("024");
        super.initialize();
    }
    
    public void initializeColumnDescriptors()
    {
        super.initializeColumnDescriptors();
        addColumnDescriptor(new ColumnDescriptor(this,"getCompanyCode","setCompanyCode","COMPANY_CODE,1,1,3,0,true|A,0,CHAR,1,null,null,null,null,null|U,0,CHAR,1,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getProductPrefix","setProductPrefix","PRODUCT_PREFIX,1,2,1,0,true|A,0,CHAR,1,null,null,null,null,null|U,0,CHAR,1,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getTableSubset","setTableSubset","TABLE_SUBSET,1,3,16,0,true|A,0,CHAR,1,null,null,null,null,null|U,0,CHAR,1,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getFundNumber","setFundNumber","FUND_NUMBER,3,4,8,0,true|A,0,INTEGER,0,null,null,null,null,null|U,0,INTEGER,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getEffectiveDate","setEffectiveDate","EFFECTIVE_DATE,91,5,10,0,true|A,0,DATE,0,null,null,null,null,null|U,0,DATE,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getMemoCode","setMemoCode","MEMO_CODE,1,6,2,0,true|A,0,CHAR,4,null,null,null,null,null|U,0,CHAR,2,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getDclrIntRtTbl","setDclrIntRtTbl","DCLR_INT_RT_TBL,1,7,16,0,false|A,0,CHAR,0,025,null,null,null,null|U,0,CHAR,0,025,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getGuarIntRtTbl","setGuarIntRtTbl","GUAR_INT_RT_TBL,1,8,16,0,false|A,0,CHAR,0,026,null,null,null,null|U,0,CHAR,0,026,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getExcsIntExclTbl","setExcsIntExclTbl","EXCS_INT_EXCL_TBL,1,9,16,0,false|A,0,CHAR,2,null,null,null,null,null|U,0,CHAR,0,027,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getInterestInd","setInterestInd","INTEREST_IND,1,10,1,0,false|A,0,CHAR,0,null,null,null,null,null|U,0,CHAR,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getAnniversaryInd","setAnniversaryInd","ANNIVERSARY_IND,1,11,1,0,false|A,0,CHAR,0,null,null,null,null,null|U,0,CHAR,2,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getIntCalcMthd","setIntCalcMthd","INT_CALC_MTHD,1,12,5,0,false|A,0,CHAR,0,null,null,null,null,null|U,0,CHAR,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getLeapYearDays","setLeapYearDays","LEAP_YEAR_DAYS,3,13,3,0,false|A,0,INTEGER,0,null,null,null,null,null|U,0,INTEGER,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getRolloverFrequency","setRolloverFrequency","ROLLOVER_FREQUENCY,1,14,1,0,false|A,0,CHAR,0,null,null,null,null,null|U,0,CHAR,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getRolloverPeriod","setRolloverPeriod","ROLLOVER_PERIOD,3,15,3,0,false|A,0,INTEGER,0,null,null,null,null,null|U,0,INTEGER,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getNewMoneyInd","setNewMoneyInd","NEW_MONEY_IND,1,16,1,0,false|A,0,CHAR,2,null,null,null,null,null|U,0,CHAR,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getGuaranteeType","setGuaranteeType","GUARANTEE_TYPE,1,17,5,0,false|A,0,CHAR,4,null,null,null,null,null|U,0,CHAR,4,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getGuaranteeDuration","setGuaranteeDuration","GUARANTEE_DURATION,3,18,3,0,false|A,0,INTEGER,4,null,null,null,null,null|U,0,INTEGER,2,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getBonusInterestTbl","setBonusInterestTbl","BONUS_INTEREST_TBL,1,19,16,0,false|A,0,CHAR,2,null,null,null,null,null|U,0,CHAR,0,089,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getRolloverPercent","setRolloverPercent","ROLLOVER_PERCENT,3,20,6,3,false|A,0,DOUBLE,0,null,null,null,null,null|U,0,DOUBLE,2,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getInterestMethod","setInterestMethod","INTEREST_METHOD,1,21,1,0,false|A,0,CHAR,0,null,null,null,null,null|U,0,CHAR,2,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getMfThresholdTbl","setMfThresholdTbl","MF_THRESHOLD_TBL,1,22,16,0,false|A,0,CHAR,0,AD3,null,null,null,null|U,0,CHAR,2,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getSecurePeriod","setSecurePeriod","SECURE_PERIOD,3,23,3,0,false|A,0,INTEGER,0,null,null,null,null,null|U,0,INTEGER,2,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getInclusionPeriod","setInclusionPeriod","INCLUSION_PERIOD,3,24,3,0,false|A,0,INTEGER,0,null,null,null,null,null|U,0,INTEGER,2,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getExclusionPeriod","setExclusionPeriod","EXCLUSION_PERIOD,3,25,3,0,false|A,0,INTEGER,0,null,null,null,null,null|U,0,INTEGER,2,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getBonusRate","setBonusRate","BONUS_RATE,3,26,5,3,false|A,0,DOUBLE,0,null,null,null,null,null|U,0,DOUBLE,2,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getSettlIntRateTbl","setSettlIntRateTbl","SETTL_INT_RATE_TBL,1,27,16,0,false|A,0,CHAR,4,025,S,null,null,null|U,0,CHAR,2,null,null,null,null,null"));
    }
    
    public String getPagingSQL(String schemaName,boolean isSubsetMode,boolean isNext, boolean locator)
    {
        String pagingWhere = null;
        String order = null;
        if (isNext) {
            if (isSubsetMode)
                if (locator)
                    pagingWhere = ".T024X WHERE (COMPANY_CODE = :company_code) AND (PRODUCT_PREFIX = :product_prefix) AND (TABLE_SUBSET = :table_subset) AND ((FUND_NUMBER > :fund_number) OR (FUND_NUMBER = :fund_number AND EFFECTIVE_DATE > :effective_date) OR (FUND_NUMBER = :fund_number AND EFFECTIVE_DATE = :effective_date AND MEMO_CODE > :memo_code) OR (FUND_NUMBER = :fund_number AND EFFECTIVE_DATE = :effective_date AND MEMO_CODE = :memo_code)) ";
                else 
                    pagingWhere = ".T024X WHERE (COMPANY_CODE = :company_code) AND (PRODUCT_PREFIX = :product_prefix) AND (TABLE_SUBSET = :table_subset) AND ((FUND_NUMBER > :fund_number) OR (FUND_NUMBER = :fund_number AND EFFECTIVE_DATE > :effective_date) OR (FUND_NUMBER = :fund_number AND EFFECTIVE_DATE = :effective_date AND MEMO_CODE > :memo_code)) ";
            else
                if (locator)
                    pagingWhere = ".T024X WHERE (COMPANY_CODE = :company_code) AND ((PRODUCT_PREFIX > :product_prefix) OR (PRODUCT_PREFIX = :product_prefix AND TABLE_SUBSET > :table_subset) OR (PRODUCT_PREFIX = :product_prefix AND TABLE_SUBSET = :table_subset AND FUND_NUMBER > :fund_number) OR (PRODUCT_PREFIX = :product_prefix AND TABLE_SUBSET = :table_subset AND FUND_NUMBER = :fund_number AND EFFECTIVE_DATE > :effective_date) OR (PRODUCT_PREFIX = :product_prefix AND TABLE_SUBSET = :table_subset AND FUND_NUMBER = :fund_number AND EFFECTIVE_DATE = :effective_date AND MEMO_CODE > :memo_code) OR (PRODUCT_PREFIX = :product_prefix AND TABLE_SUBSET = :table_subset AND FUND_NUMBER = :fund_number AND EFFECTIVE_DATE = :effective_date AND MEMO_CODE = :memo_code)) ";
                else
                    pagingWhere = ".T024X WHERE (COMPANY_CODE = :company_code) AND ((PRODUCT_PREFIX > :product_prefix) OR (PRODUCT_PREFIX = :product_prefix AND TABLE_SUBSET > :table_subset) OR (PRODUCT_PREFIX = :product_prefix AND TABLE_SUBSET = :table_subset AND FUND_NUMBER > :fund_number) OR (PRODUCT_PREFIX = :product_prefix AND TABLE_SUBSET = :table_subset AND FUND_NUMBER = :fund_number AND EFFECTIVE_DATE > :effective_date) OR (PRODUCT_PREFIX = :product_prefix AND TABLE_SUBSET = :table_subset AND FUND_NUMBER = :fund_number AND EFFECTIVE_DATE = :effective_date AND MEMO_CODE > :memo_code)) ";
            order = " ORDER BY COMPANY_CODE, PRODUCT_PREFIX, TABLE_SUBSET, FUND_NUMBER, EFFECTIVE_DATE, MEMO_CODE";
        }
        else {
            if (isSubsetMode)
                pagingWhere = ".T024X WHERE (COMPANY_CODE = :company_code) AND (PRODUCT_PREFIX = :product_prefix) AND (TABLE_SUBSET = :table_subset) AND ((FUND_NUMBER < :fund_number) OR (FUND_NUMBER = :fund_number AND EFFECTIVE_DATE < :effective_date) OR (FUND_NUMBER = :fund_number AND EFFECTIVE_DATE = :effective_date AND MEMO_CODE < :memo_code)) ";
            else
                pagingWhere = ".T024X WHERE (COMPANY_CODE = :company_code) AND ((PRODUCT_PREFIX < :product_prefix) OR (PRODUCT_PREFIX = :product_prefix AND TABLE_SUBSET < :table_subset) OR (PRODUCT_PREFIX = :product_prefix AND TABLE_SUBSET = :table_subset AND FUND_NUMBER < :fund_number) OR (PRODUCT_PREFIX = :product_prefix AND TABLE_SUBSET = :table_subset AND FUND_NUMBER = :fund_number AND EFFECTIVE_DATE < :effective_date) OR (PRODUCT_PREFIX = :product_prefix AND TABLE_SUBSET = :table_subset AND FUND_NUMBER = :fund_number AND EFFECTIVE_DATE = :effective_date AND MEMO_CODE < :memo_code)) ";
            order = " ORDER BY COMPANY_CODE DESC, PRODUCT_PREFIX DESC, TABLE_SUBSET DESC, FUND_NUMBER DESC, EFFECTIVE_DATE DESC, MEMO_CODE DESC";
        }
        return pagingSql + schemaName + pagingWhere + order;
    }
    
    public String prepareInsertStmt(String schemaName) {
        StringBuffer sb = new StringBuffer();
        sb.append("INSERT INTO ").append(schemaName);
        sb.append(".T024X ( ");
        sb.append("COMPANY_CODE, PRODUCT_PREFIX, TABLE_SUBSET, FUND_NUMBER, EFFECTIVE_DATE, MEMO_CODE, DCLR_INT_RT_TBL, GUAR_INT_RT_TBL, EXCS_INT_EXCL_TBL, INTEREST_IND, ANNIVERSARY_IND, INT_CALC_MTHD, LEAP_YEAR_DAYS, ROLLOVER_FREQUENCY, ROLLOVER_PERIOD, NEW_MONEY_IND, GUARANTEE_TYPE, GUARANTEE_DURATION, BONUS_INTEREST_TBL, ROLLOVER_PERCENT, INTEREST_METHOD, MF_THRESHOLD_TBL, SECURE_PERIOD, INCLUSION_PERIOD, EXCLUSION_PERIOD, BONUS_RATE, SETTL_INT_RATE_TBL )");
        sb.append(" VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ? )");
        return sb.toString();
    }
    
    public String prepareUpdateStmt(String schemaName)
    {
        StringBuffer sb = new StringBuffer();
        sb.append("UPDATE ").append(schemaName);
        sb.append(".T024X ");
        sb.append(" SET COMPANY_CODE = ?, PRODUCT_PREFIX = ?, TABLE_SUBSET = ?, FUND_NUMBER = ?, EFFECTIVE_DATE = ?, MEMO_CODE = ?, DCLR_INT_RT_TBL = ?, GUAR_INT_RT_TBL = ?, EXCS_INT_EXCL_TBL = ?, INTEREST_IND = ?, ANNIVERSARY_IND = ?, INT_CALC_MTHD = ?, LEAP_YEAR_DAYS = ?, ROLLOVER_FREQUENCY = ?, ROLLOVER_PERIOD = ?, NEW_MONEY_IND = ?, GUARANTEE_TYPE = ?, GUARANTEE_DURATION = ?, BONUS_INTEREST_TBL = ?, ROLLOVER_PERCENT = ?, INTEREST_METHOD = ?, MF_THRESHOLD_TBL = ?, SECURE_PERIOD = ?, INCLUSION_PERIOD = ?, EXCLUSION_PERIOD = ?, BONUS_RATE = ?, SETTL_INT_RATE_TBL = ?");
        sb.append(" WHERE COMPANY_CODE = ? AND PRODUCT_PREFIX = ? AND TABLE_SUBSET = ? AND FUND_NUMBER = ? AND EFFECTIVE_DATE = ? AND MEMO_CODE = ?");
        return sb.toString();
    }
    
    public String prepareDeleteStmt(String schemaName)
    {
        StringBuffer sb = new StringBuffer();
        sb.append("DELETE FROM ").append(schemaName);
        sb.append(".T024X ");
        sb.append(" WHERE COMPANY_CODE = ? AND PRODUCT_PREFIX = ? AND TABLE_SUBSET = ? AND FUND_NUMBER = ? AND EFFECTIVE_DATE = ? AND MEMO_CODE = ?");
        return sb.toString();
    }
}
