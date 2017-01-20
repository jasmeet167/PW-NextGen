package com.csc.fsg.life.pw.io.descriptor.wma;

import com.csc.fsg.life.pw.io.*;

public class T0C8XDescriptor
    extends TableDescriptor
{
    private static final String pagingSql = "SELECT COMPANY_CODE, PRODUCT_PREFIX, TABLE_SUBSET, FUND_NUMBER, TRX_CODE, LOB_CODE, EFFECTIVE_DATE, STATE_CODE, THRESHOLD_AMT, MEMO_CODE, INVST_TIM_PERD, INITIAL_HOLD_FUND, INVST_DAY, INVST_DD_1, INVST_DD_2, INVST_DD_3, GUAR_PARTIC_RATE, GUAR_INT_RATE, LEAP_YEAR_DAYS, LOCK_IN_METHOD, LOCK_IN_PCT, VALUATION_TYPE, WIN_DAYS_PRIOR_REN, WIN_DAYS_POST_REN, INVST_TERM_LEN, LOOK_BACK_IND, ASIAN_PERD_IND, ASIAN_PERD, REN_NOTIC_DAYS, REN_NOTIC_INC_DAYS, RENEWAL_IND, RENEWAL_HOLD_DAYS, RENEWAL_HOLD_FUND, CAP_PARTIC_RATE, DISCOUNT_MTHD, DTH_BEN_CALC_MTHD, REN_PAR_RT_SEL_IND, SURR_PAR_RT_TBL, NEW_BUS_PAR_RT_TBL, REN_PAR_RT_TBL, PAR_RT_UNI_FCT_IND, SPREAD_TBL, TRX_ALLWD_IND, INDX_PRR_NXT_IND, MATURITY_DURATION FROM ";
    
    public void initialize()
    {
        setRowClass(T0C8XRow.class);
        setTableName("T0C8X");
        setTableId("0C8");
        super.initialize();
    }
    
    public void initializeColumnDescriptors()
    {
        super.initializeColumnDescriptors();
        addColumnDescriptor(new ColumnDescriptor(this,"getCompanyCode","setCompanyCode","COMPANY_CODE,1,1,3,0,true|A,0,CHAR,1,null,null,null,null,null|U,0,CHAR,1,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getProductPrefix","setProductPrefix","PRODUCT_PREFIX,1,2,1,0,true|A,0,CHAR,1,null,null,null,null,null|U,0,CHAR,1,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getTableSubset","setTableSubset","TABLE_SUBSET,1,3,16,0,true|A,0,CHAR,1,null,null,null,null,null|U,0,CHAR,1,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getFundNumber","setFundNumber","FUND_NUMBER,3,4,8,0,true|A,0,INTEGER,0,null,null,null,null,null|U,0,INTEGER,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getTrxCode","setTrxCode","TRX_CODE,1,5,4,0,true|A,0,CHAR,0,null,null,null,null,null|U,0,CHAR,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getLobCode","setLobCode","LOB_CODE,1,6,3,0,true|A,0,CHAR,0,null,null,null,null,null|U,0,CHAR,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getEffectiveDate","setEffectiveDate","EFFECTIVE_DATE,91,7,10,0,true|A,0,DATE,0,null,null,null,null,null|U,0,DATE,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getStateCode","setStateCode","STATE_CODE,1,8,3,0,true|A,0,CHAR,0,null,null,null,null,null|U,0,CHAR,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getThresholdAmt","setThresholdAmt","THRESHOLD_AMT,3,9,11,2,true|A,0,DOUBLE,0,null,null,null,null,null|U,0,DOUBLE,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getMemoCode","setMemoCode","MEMO_CODE,1,10,2,0,true|A,0,CHAR,0,null,null,null,null,null|U,0,CHAR,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getInvstTimPerd","setInvstTimPerd","INVST_TIM_PERD,1,11,1,0,false|A,0,CHAR,0,null,null,null,null,null|U,0,CHAR,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getInitialHoldFund","setInitialHoldFund","INITIAL_HOLD_FUND,3,12,8,0,false|A,0,INTEGER,0,null,null,null,null,null|U,0,INTEGER,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getInvstDay","setInvstDay","INVST_DAY,1,13,3,0,false|A,0,CHAR,0,null,null,null,null,null|U,0,CHAR,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getInvstDd1","setInvstDd1","INVST_DD_1,1,14,2,0,false|A,0,CHAR,0,null,null,null,null,null|U,0,CHAR,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getInvstDd2","setInvstDd2","INVST_DD_2,1,15,2,0,false|A,0,CHAR,0,null,null,null,null,null|U,0,CHAR,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getInvstDd3","setInvstDd3","INVST_DD_3,1,16,2,0,false|A,0,CHAR,0,null,null,null,null,null|U,0,CHAR,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getGuarParticRate","setGuarParticRate","GUAR_PARTIC_RATE,3,17,5,2,false|A,0,DOUBLE,0,null,null,null,null,null|U,0,DOUBLE,2,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getGuarIntRate","setGuarIntRate","GUAR_INT_RATE,3,18,5,2,false|A,0,DOUBLE,0,null,null,null,null,null|U,0,DOUBLE,2,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getLeapYearDays","setLeapYearDays","LEAP_YEAR_DAYS,3,19,3,0,false|A,0,INTEGER,0,null,null,null,null,null|U,0,INTEGER,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getLockInMethod","setLockInMethod","LOCK_IN_METHOD,1,20,1,0,false|A,0,CHAR,0,null,null,null,null,null|U,0,CHAR,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getLockInPct","setLockInPct","LOCK_IN_PCT,3,21,5,2,false|A,0,DOUBLE,0,null,null,null,null,null|U,0,DOUBLE,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getValuationType","setValuationType","VALUATION_TYPE,1,22,1,0,false|A,0,CHAR,0,null,null,null,null,null|U,0,CHAR,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getWinDaysPriorRen","setWinDaysPriorRen","WIN_DAYS_PRIOR_REN,3,23,3,0,false|A,0,INTEGER,0,null,null,null,null,null|U,0,INTEGER,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getWinDaysPostRen","setWinDaysPostRen","WIN_DAYS_POST_REN,3,24,3,0,false|A,0,INTEGER,0,null,null,null,null,null|U,0,INTEGER,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getInvstTermLen","setInvstTermLen","INVST_TERM_LEN,3,25,4,0,false|A,0,INTEGER,0,null,null,null,null,null|U,0,INTEGER,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getLookBackInd","setLookBackInd","LOOK_BACK_IND,1,26,1,0,false|A,0,CHAR,0,null,null,null,null,null|U,0,CHAR,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getAsianPerdInd","setAsianPerdInd","ASIAN_PERD_IND,1,27,1,0,false|A,0,CHAR,0,null,null,null,null,null|U,0,CHAR,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getAsianPerd","setAsianPerd","ASIAN_PERD,3,28,3,0,false|A,0,INTEGER,0,null,null,null,null,null|U,0,INTEGER,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getRenNoticDays","setRenNoticDays","REN_NOTIC_DAYS,3,29,3,0,false|A,0,INTEGER,0,null,null,null,null,null|U,0,INTEGER,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getRenNoticIncDays","setRenNoticIncDays","REN_NOTIC_INC_DAYS,3,30,3,0,false|A,0,INTEGER,0,null,null,null,null,null|U,0,INTEGER,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getRenewalInd","setRenewalInd","RENEWAL_IND,1,31,1,0,false|A,0,CHAR,0,null,null,null,null,null|U,0,CHAR,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getRenewalHoldDays","setRenewalHoldDays","RENEWAL_HOLD_DAYS,3,32,3,0,false|A,0,INTEGER,0,null,null,null,null,null|U,0,INTEGER,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getRenewalHoldFund","setRenewalHoldFund","RENEWAL_HOLD_FUND,3,33,8,0,false|A,0,INTEGER,0,null,null,null,null,null|U,0,INTEGER,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getCapParticRate","setCapParticRate","CAP_PARTIC_RATE,3,34,5,2,false|A,0,DOUBLE,0,null,null,null,null,null|U,0,DOUBLE,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getDiscountMthd","setDiscountMthd","DISCOUNT_MTHD,1,35,1,0,false|A,0,CHAR,0,null,null,null,null,null|U,0,CHAR,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getDthBenCalcMthd","setDthBenCalcMthd","DTH_BEN_CALC_MTHD,1,36,1,0,false|A,0,CHAR,0,null,null,null,null,null|U,0,CHAR,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getRenParRtSelInd","setRenParRtSelInd","REN_PAR_RT_SEL_IND,1,37,1,0,false|A,0,CHAR,0,null,null,null,null,null|U,0,CHAR,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getSurrParRtTbl","setSurrParRtTbl","SURR_PAR_RT_TBL,1,38,16,0,false|A,0,CHAR,0,0C7,null,null,null,null|U,0,CHAR,0,0C7,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getNewBusParRtTbl","setNewBusParRtTbl","NEW_BUS_PAR_RT_TBL,1,39,16,0,false|A,0,CHAR,0,0C6,N,null,null,null|U,0,CHAR,0,0C6,N,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getRenParRtTbl","setRenParRtTbl","REN_PAR_RT_TBL,1,40,16,0,false|A,0,CHAR,0,0C6,R,null,null,null|U,0,CHAR,0,0C6,R,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getParRtUniFctInd","setParRtUniFctInd","PAR_RT_UNI_FCT_IND,1,41,1,0,false|A,0,CHAR,0,null,null,null,null,null|U,0,CHAR,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getSpreadTbl","setSpreadTbl","SPREAD_TBL,1,42,16,0,false|A,0,CHAR,0,0C3,null,null,null,null|U,0,CHAR,0,0C3,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getTrxAllwdInd","setTrxAllwdInd","TRX_ALLWD_IND,1,43,1,0,false|A,0,CHAR,0,null,null,null,null,null|U,0,CHAR,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getIndxPrrNxtInd","setIndxPrrNxtInd","INDX_PRR_NXT_IND,1,44,1,0,false|A,0,CHAR,0,null,null,null,null,null|U,0,CHAR,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getMaturityDuration","setMaturityDuration","MATURITY_DURATION,3,45,3,0,false|A,0,INTEGER,0,null,null,null,null,null|U,0,INTEGER,0,null,null,null,null,null"));
    }
    
    public String getPagingSQL(String schemaName,boolean isSubsetMode,boolean isNext, boolean locator)
    {
        String pagingWhere = null;
        String order = null;
        if (isNext) {
            if (isSubsetMode)
                if (locator)
                    pagingWhere = ".T0C8X WHERE (COMPANY_CODE = :company_code) AND (PRODUCT_PREFIX = :product_prefix) AND (TABLE_SUBSET = :table_subset) AND ((FUND_NUMBER > :fund_number) OR (FUND_NUMBER = :fund_number AND TRX_CODE > :trx_code) OR (FUND_NUMBER = :fund_number AND TRX_CODE = :trx_code AND LOB_CODE > :lob_code) OR (FUND_NUMBER = :fund_number AND TRX_CODE = :trx_code AND LOB_CODE = :lob_code AND EFFECTIVE_DATE > :effective_date) OR (FUND_NUMBER = :fund_number AND TRX_CODE = :trx_code AND LOB_CODE = :lob_code AND EFFECTIVE_DATE = :effective_date AND STATE_CODE > :state_code) OR (FUND_NUMBER = :fund_number AND TRX_CODE = :trx_code AND LOB_CODE = :lob_code AND EFFECTIVE_DATE = :effective_date AND STATE_CODE = :state_code AND THRESHOLD_AMT > :threshold_amt) OR (FUND_NUMBER = :fund_number AND TRX_CODE = :trx_code AND LOB_CODE = :lob_code AND EFFECTIVE_DATE = :effective_date AND STATE_CODE = :state_code AND THRESHOLD_AMT = :threshold_amt AND MEMO_CODE > :memo_code) OR (FUND_NUMBER = :fund_number AND TRX_CODE = :trx_code AND LOB_CODE = :lob_code AND EFFECTIVE_DATE = :effective_date AND STATE_CODE = :state_code AND THRESHOLD_AMT = :threshold_amt AND MEMO_CODE = :memo_code)) ";
                else 
                    pagingWhere = ".T0C8X WHERE (COMPANY_CODE = :company_code) AND (PRODUCT_PREFIX = :product_prefix) AND (TABLE_SUBSET = :table_subset) AND ((FUND_NUMBER > :fund_number) OR (FUND_NUMBER = :fund_number AND TRX_CODE > :trx_code) OR (FUND_NUMBER = :fund_number AND TRX_CODE = :trx_code AND LOB_CODE > :lob_code) OR (FUND_NUMBER = :fund_number AND TRX_CODE = :trx_code AND LOB_CODE = :lob_code AND EFFECTIVE_DATE > :effective_date) OR (FUND_NUMBER = :fund_number AND TRX_CODE = :trx_code AND LOB_CODE = :lob_code AND EFFECTIVE_DATE = :effective_date AND STATE_CODE > :state_code) OR (FUND_NUMBER = :fund_number AND TRX_CODE = :trx_code AND LOB_CODE = :lob_code AND EFFECTIVE_DATE = :effective_date AND STATE_CODE = :state_code AND THRESHOLD_AMT > :threshold_amt) OR (FUND_NUMBER = :fund_number AND TRX_CODE = :trx_code AND LOB_CODE = :lob_code AND EFFECTIVE_DATE = :effective_date AND STATE_CODE = :state_code AND THRESHOLD_AMT = :threshold_amt AND MEMO_CODE > :memo_code)) ";
            else
                if (locator)
                    pagingWhere = ".T0C8X WHERE (COMPANY_CODE = :company_code) AND ((PRODUCT_PREFIX > :product_prefix) OR (PRODUCT_PREFIX = :product_prefix AND TABLE_SUBSET > :table_subset) OR (PRODUCT_PREFIX = :product_prefix AND TABLE_SUBSET = :table_subset AND FUND_NUMBER > :fund_number) OR (PRODUCT_PREFIX = :product_prefix AND TABLE_SUBSET = :table_subset AND FUND_NUMBER = :fund_number AND TRX_CODE > :trx_code) OR (PRODUCT_PREFIX = :product_prefix AND TABLE_SUBSET = :table_subset AND FUND_NUMBER = :fund_number AND TRX_CODE = :trx_code AND LOB_CODE > :lob_code) OR (PRODUCT_PREFIX = :product_prefix AND TABLE_SUBSET = :table_subset AND FUND_NUMBER = :fund_number AND TRX_CODE = :trx_code AND LOB_CODE = :lob_code AND EFFECTIVE_DATE > :effective_date) OR (PRODUCT_PREFIX = :product_prefix AND TABLE_SUBSET = :table_subset AND FUND_NUMBER = :fund_number AND TRX_CODE = :trx_code AND LOB_CODE = :lob_code AND EFFECTIVE_DATE = :effective_date AND STATE_CODE > :state_code) OR (PRODUCT_PREFIX = :product_prefix AND TABLE_SUBSET = :table_subset AND FUND_NUMBER = :fund_number AND TRX_CODE = :trx_code AND LOB_CODE = :lob_code AND EFFECTIVE_DATE = :effective_date AND STATE_CODE = :state_code AND THRESHOLD_AMT > :threshold_amt) OR (PRODUCT_PREFIX = :product_prefix AND TABLE_SUBSET = :table_subset AND FUND_NUMBER = :fund_number AND TRX_CODE = :trx_code AND LOB_CODE = :lob_code AND EFFECTIVE_DATE = :effective_date AND STATE_CODE = :state_code AND THRESHOLD_AMT = :threshold_amt AND MEMO_CODE > :memo_code) OR (PRODUCT_PREFIX = :product_prefix AND TABLE_SUBSET = :table_subset AND FUND_NUMBER = :fund_number AND TRX_CODE = :trx_code AND LOB_CODE = :lob_code AND EFFECTIVE_DATE = :effective_date AND STATE_CODE = :state_code AND THRESHOLD_AMT = :threshold_amt AND MEMO_CODE = :memo_code)) ";
                else
                    pagingWhere = ".T0C8X WHERE (COMPANY_CODE = :company_code) AND ((PRODUCT_PREFIX > :product_prefix) OR (PRODUCT_PREFIX = :product_prefix AND TABLE_SUBSET > :table_subset) OR (PRODUCT_PREFIX = :product_prefix AND TABLE_SUBSET = :table_subset AND FUND_NUMBER > :fund_number) OR (PRODUCT_PREFIX = :product_prefix AND TABLE_SUBSET = :table_subset AND FUND_NUMBER = :fund_number AND TRX_CODE > :trx_code) OR (PRODUCT_PREFIX = :product_prefix AND TABLE_SUBSET = :table_subset AND FUND_NUMBER = :fund_number AND TRX_CODE = :trx_code AND LOB_CODE > :lob_code) OR (PRODUCT_PREFIX = :product_prefix AND TABLE_SUBSET = :table_subset AND FUND_NUMBER = :fund_number AND TRX_CODE = :trx_code AND LOB_CODE = :lob_code AND EFFECTIVE_DATE > :effective_date) OR (PRODUCT_PREFIX = :product_prefix AND TABLE_SUBSET = :table_subset AND FUND_NUMBER = :fund_number AND TRX_CODE = :trx_code AND LOB_CODE = :lob_code AND EFFECTIVE_DATE = :effective_date AND STATE_CODE > :state_code) OR (PRODUCT_PREFIX = :product_prefix AND TABLE_SUBSET = :table_subset AND FUND_NUMBER = :fund_number AND TRX_CODE = :trx_code AND LOB_CODE = :lob_code AND EFFECTIVE_DATE = :effective_date AND STATE_CODE = :state_code AND THRESHOLD_AMT > :threshold_amt) OR (PRODUCT_PREFIX = :product_prefix AND TABLE_SUBSET = :table_subset AND FUND_NUMBER = :fund_number AND TRX_CODE = :trx_code AND LOB_CODE = :lob_code AND EFFECTIVE_DATE = :effective_date AND STATE_CODE = :state_code AND THRESHOLD_AMT = :threshold_amt AND MEMO_CODE > :memo_code)) ";
            order = " ORDER BY COMPANY_CODE, PRODUCT_PREFIX, TABLE_SUBSET, FUND_NUMBER, TRX_CODE, LOB_CODE, EFFECTIVE_DATE, STATE_CODE, THRESHOLD_AMT, MEMO_CODE";
        }
        else {
            if (isSubsetMode)
                pagingWhere = ".T0C8X WHERE (COMPANY_CODE = :company_code) AND (PRODUCT_PREFIX = :product_prefix) AND (TABLE_SUBSET = :table_subset) AND ((FUND_NUMBER < :fund_number) OR (FUND_NUMBER = :fund_number AND TRX_CODE < :trx_code) OR (FUND_NUMBER = :fund_number AND TRX_CODE = :trx_code AND LOB_CODE < :lob_code) OR (FUND_NUMBER = :fund_number AND TRX_CODE = :trx_code AND LOB_CODE = :lob_code AND EFFECTIVE_DATE < :effective_date) OR (FUND_NUMBER = :fund_number AND TRX_CODE = :trx_code AND LOB_CODE = :lob_code AND EFFECTIVE_DATE = :effective_date AND STATE_CODE < :state_code) OR (FUND_NUMBER = :fund_number AND TRX_CODE = :trx_code AND LOB_CODE = :lob_code AND EFFECTIVE_DATE = :effective_date AND STATE_CODE = :state_code AND THRESHOLD_AMT < :threshold_amt) OR (FUND_NUMBER = :fund_number AND TRX_CODE = :trx_code AND LOB_CODE = :lob_code AND EFFECTIVE_DATE = :effective_date AND STATE_CODE = :state_code AND THRESHOLD_AMT = :threshold_amt AND MEMO_CODE < :memo_code)) ";
            else
                pagingWhere = ".T0C8X WHERE (COMPANY_CODE = :company_code) AND ((PRODUCT_PREFIX < :product_prefix) OR (PRODUCT_PREFIX = :product_prefix AND TABLE_SUBSET < :table_subset) OR (PRODUCT_PREFIX = :product_prefix AND TABLE_SUBSET = :table_subset AND FUND_NUMBER < :fund_number) OR (PRODUCT_PREFIX = :product_prefix AND TABLE_SUBSET = :table_subset AND FUND_NUMBER = :fund_number AND TRX_CODE < :trx_code) OR (PRODUCT_PREFIX = :product_prefix AND TABLE_SUBSET = :table_subset AND FUND_NUMBER = :fund_number AND TRX_CODE = :trx_code AND LOB_CODE < :lob_code) OR (PRODUCT_PREFIX = :product_prefix AND TABLE_SUBSET = :table_subset AND FUND_NUMBER = :fund_number AND TRX_CODE = :trx_code AND LOB_CODE = :lob_code AND EFFECTIVE_DATE < :effective_date) OR (PRODUCT_PREFIX = :product_prefix AND TABLE_SUBSET = :table_subset AND FUND_NUMBER = :fund_number AND TRX_CODE = :trx_code AND LOB_CODE = :lob_code AND EFFECTIVE_DATE = :effective_date AND STATE_CODE < :state_code) OR (PRODUCT_PREFIX = :product_prefix AND TABLE_SUBSET = :table_subset AND FUND_NUMBER = :fund_number AND TRX_CODE = :trx_code AND LOB_CODE = :lob_code AND EFFECTIVE_DATE = :effective_date AND STATE_CODE = :state_code AND THRESHOLD_AMT < :threshold_amt) OR (PRODUCT_PREFIX = :product_prefix AND TABLE_SUBSET = :table_subset AND FUND_NUMBER = :fund_number AND TRX_CODE = :trx_code AND LOB_CODE = :lob_code AND EFFECTIVE_DATE = :effective_date AND STATE_CODE = :state_code AND THRESHOLD_AMT = :threshold_amt AND MEMO_CODE < :memo_code)) ";
            order = " ORDER BY COMPANY_CODE DESC, PRODUCT_PREFIX DESC, TABLE_SUBSET DESC, FUND_NUMBER DESC, TRX_CODE DESC, LOB_CODE DESC, EFFECTIVE_DATE DESC, STATE_CODE DESC, THRESHOLD_AMT DESC, MEMO_CODE DESC";
        }
        return pagingSql + schemaName + pagingWhere + order;
    }
    
    public String prepareInsertStmt(String schemaName) {
        StringBuffer sb = new StringBuffer();
        sb.append("INSERT INTO ").append(schemaName);
        sb.append(".T0C8X ( ");
        sb.append("COMPANY_CODE, PRODUCT_PREFIX, TABLE_SUBSET, FUND_NUMBER, TRX_CODE, LOB_CODE, EFFECTIVE_DATE, STATE_CODE, THRESHOLD_AMT, MEMO_CODE, INVST_TIM_PERD, INITIAL_HOLD_FUND, INVST_DAY, INVST_DD_1, INVST_DD_2, INVST_DD_3, GUAR_PARTIC_RATE, GUAR_INT_RATE, LEAP_YEAR_DAYS, LOCK_IN_METHOD, LOCK_IN_PCT, VALUATION_TYPE, WIN_DAYS_PRIOR_REN, WIN_DAYS_POST_REN, INVST_TERM_LEN, LOOK_BACK_IND, ASIAN_PERD_IND, ASIAN_PERD, REN_NOTIC_DAYS, REN_NOTIC_INC_DAYS, RENEWAL_IND, RENEWAL_HOLD_DAYS, RENEWAL_HOLD_FUND, CAP_PARTIC_RATE, DISCOUNT_MTHD, DTH_BEN_CALC_MTHD, REN_PAR_RT_SEL_IND, SURR_PAR_RT_TBL, NEW_BUS_PAR_RT_TBL, REN_PAR_RT_TBL, PAR_RT_UNI_FCT_IND, SPREAD_TBL, TRX_ALLWD_IND, INDX_PRR_NXT_IND, MATURITY_DURATION )");
        sb.append(" VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ? )");
        return sb.toString();
    }
    
    public String prepareUpdateStmt(String schemaName)
    {
        StringBuffer sb = new StringBuffer();
        sb.append("UPDATE ").append(schemaName);
        sb.append(".T0C8X ");
        sb.append(" SET COMPANY_CODE = ?, PRODUCT_PREFIX = ?, TABLE_SUBSET = ?, FUND_NUMBER = ?, TRX_CODE = ?, LOB_CODE = ?, EFFECTIVE_DATE = ?, STATE_CODE = ?, THRESHOLD_AMT = ?, MEMO_CODE = ?, INVST_TIM_PERD = ?, INITIAL_HOLD_FUND = ?, INVST_DAY = ?, INVST_DD_1 = ?, INVST_DD_2 = ?, INVST_DD_3 = ?, GUAR_PARTIC_RATE = ?, GUAR_INT_RATE = ?, LEAP_YEAR_DAYS = ?, LOCK_IN_METHOD = ?, LOCK_IN_PCT = ?, VALUATION_TYPE = ?, WIN_DAYS_PRIOR_REN = ?, WIN_DAYS_POST_REN = ?, INVST_TERM_LEN = ?, LOOK_BACK_IND = ?, ASIAN_PERD_IND = ?, ASIAN_PERD = ?, REN_NOTIC_DAYS = ?, REN_NOTIC_INC_DAYS = ?, RENEWAL_IND = ?, RENEWAL_HOLD_DAYS = ?, RENEWAL_HOLD_FUND = ?, CAP_PARTIC_RATE = ?, DISCOUNT_MTHD = ?, DTH_BEN_CALC_MTHD = ?, REN_PAR_RT_SEL_IND = ?, SURR_PAR_RT_TBL = ?, NEW_BUS_PAR_RT_TBL = ?, REN_PAR_RT_TBL = ?, PAR_RT_UNI_FCT_IND = ?, SPREAD_TBL = ?, TRX_ALLWD_IND = ?, INDX_PRR_NXT_IND = ?, MATURITY_DURATION = ?");
        sb.append(" WHERE COMPANY_CODE = ? AND PRODUCT_PREFIX = ? AND TABLE_SUBSET = ? AND FUND_NUMBER = ? AND TRX_CODE = ? AND LOB_CODE = ? AND EFFECTIVE_DATE = ? AND STATE_CODE = ? AND THRESHOLD_AMT = ? AND MEMO_CODE = ?");
        return sb.toString();
    }
    
    public String prepareDeleteStmt(String schemaName)
    {
        StringBuffer sb = new StringBuffer();
        sb.append("DELETE FROM ").append(schemaName);
        sb.append(".T0C8X ");
        sb.append(" WHERE COMPANY_CODE = ? AND PRODUCT_PREFIX = ? AND TABLE_SUBSET = ? AND FUND_NUMBER = ? AND TRX_CODE = ? AND LOB_CODE = ? AND EFFECTIVE_DATE = ? AND STATE_CODE = ? AND THRESHOLD_AMT = ? AND MEMO_CODE = ?");
        return sb.toString();
    }
}
