package com.csc.fsg.life.pw.io.descriptor.wma;

import com.csc.fsg.life.pw.io.*;

/* Modifications: ENH1205 */

public class T004EDescriptor
    extends TableDescriptor
{
    private static final String pagingSql = "SELECT COMPANY_CODE, TABLE_SUBSET, EFFECTIVE_DATE, CORRIDOR_DEF_CODE, CORRIDOR_AMOUNT, CORRIDOR_PCT_FACE, TEFRA_INDICATOR, PYMT_LOAD_TARG_IND, RISK_RATE_DIVISOR, AGE_DEF_CODE, AGE_DEF_TIMING_CDE, RISK_PURCHASE_IND, TYPE_CHANGE_IND, EARLIEST_LAPSE_DEL, LAPSE_GRACE_PERIOD, LAPSE_PEND_PAY_MON, FINL_LPSE_NOTIC_DY, REINSTATE_MONTHS, INT_DUE_GRACE_PERD, PAR_NONPAR_CODE, GRACE_PERD_BILL_IN, ALLOCATN_BASIS_IND, ATTND_AGE_CORR_TBL, WITHDRAW_FACE_METH, GUID_LNE_PRM_DEF, NO_LPS_GUAR_MON, INC_NO_LPS_GR_MN, MINPAY_TRG_ACM_DEF, MINPAY_TRG_ACM_SUS, DECREASE_FACE_METH, TYP_CHNG_INCR_METH, TYP_CHNG_DECR_METH, LOAN_INT_EXTRC_DYS, CANADIANIZE_INDIC, ANN_STATE_TRX_CODE, PAST_VALUE_MONTHS, TRANSFER_YR_INDIC, NUM_FREE_TRANS_YR, NUM_ALOW_TRANS_YR, BYPAS_VAR_PROC_IND, CAN_MIN_DB_PCT, CAN_MIN_MB_PCT, TAMRA_TEST_IND, TAMRA_REJECT_IND, FUTURE_PROCESS_DY, PDF_PREM_XCESS_IND, SPCL_CLASS_COM_IND, RATNG_FCTR_COM_IND, FLAT_EXTRA_COM_IND, COV_TERM_BASIS, COV_TERM_AGE, MINIMUM_ISSUE_AGE, MAXIMUM_ISSUE_AGE, SEC_GUIDELINE_IND, PROJECTION_IND, COST_BASIS_IND, EXCHANGE_IND, BILLING_CHANGE_IND, MODIFIED_LAPSE_IND, SHADOW_ACCT_IND, REINSTATEMENT_TYPE, PREM_DB_OPT_ELIGIBILITY_IND, INDEX_INDICATOR, MEC_REIN_GRACE_PERIOD FROM ";
    
    public void initialize()
    {
        setRowClass(T004ERow.class);
        setTableName("T004E");
        setTableId("004");
        super.initialize();
    }
    
    public void initializeColumnDescriptors()
    {
        super.initializeColumnDescriptors();
        addColumnDescriptor(new ColumnDescriptor(this,"getCompanyCode","setCompanyCode","COMPANY_CODE,1,1,3,0,true|,0,CHAR,1,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getTableSubset","setTableSubset","TABLE_SUBSET,1,2,16,0,true|,0,CHAR,1,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getEffectiveDate","setEffectiveDate","EFFECTIVE_DATE,91,3,10,0,true|,0,DATE,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getCorridorDefCode","setCorridorDefCode","CORRIDOR_DEF_CODE,1,4,1,0,false|,0,CHAR,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getCorridorAmount","setCorridorAmount","CORRIDOR_AMOUNT,3,5,8,0,false|,0,INTEGER,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getCorridorPctFace","setCorridorPctFace","CORRIDOR_PCT_FACE,3,6,6,3,false|,0,DOUBLE,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getTefraIndicator","setTefraIndicator","TEFRA_INDICATOR,1,7,1,0,false|,0,CHAR,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getPymtLoadTargInd","setPymtLoadTargInd","PYMT_LOAD_TARG_IND,1,8,1,0,false|,0,CHAR,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getRiskRateDivisor","setRiskRateDivisor","RISK_RATE_DIVISOR,3,9,6,3,false|,0,DOUBLE,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getAgeDefCode","setAgeDefCode","AGE_DEF_CODE,1,10,1,0,false|,0,CHAR,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getAgeDefTimingCde","setAgeDefTimingCde","AGE_DEF_TIMING_CDE,1,11,1,0,false|,0,CHAR,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getRiskPurchaseInd","setRiskPurchaseInd","RISK_PURCHASE_IND,1,12,1,0,false|,0,CHAR,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getTypeChangeInd","setTypeChangeInd","TYPE_CHANGE_IND,1,13,1,0,false|,0,CHAR,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getEarliestLapseDel","setEarliestLapseDel","EARLIEST_LAPSE_DEL,3,14,2,0,false|,0,INTEGER,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getLapseGracePeriod","setLapseGracePeriod","LAPSE_GRACE_PERIOD,3,15,3,0,false|,0,INTEGER,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getLapsePendPayMon","setLapsePendPayMon","LAPSE_PEND_PAY_MON,3,16,2,0,false|,0,INTEGER,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getFinlLpseNoticDy","setFinlLpseNoticDy","FINL_LPSE_NOTIC_DY,3,17,3,0,false|,0,INTEGER,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getReinstateMonths","setReinstateMonths","REINSTATE_MONTHS,3,18,3,0,false|,0,INTEGER,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getIntDueGracePerd","setIntDueGracePerd","INT_DUE_GRACE_PERD,3,19,3,0,false|,0,INTEGER,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getParNonparCode","setParNonparCode","PAR_NONPAR_CODE,1,20,1,0,false|,0,CHAR,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getGracePerdBillIn","setGracePerdBillIn","GRACE_PERD_BILL_IN,1,21,1,0,false|,0,CHAR,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getAllocatnBasisInd","setAllocatnBasisInd","ALLOCATN_BASIS_IND,1,22,1,0,false|,0,CHAR,4,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getAttndAgeCorrTbl","setAttndAgeCorrTbl","ATTND_AGE_CORR_TBL,1,23,16,0,false|,0,CHAR,0,005,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getWithdrawFaceMeth","setWithdrawFaceMeth","WITHDRAW_FACE_METH,1,24,4,0,false|,0,CHAR,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getGuidLnePrmDef","setGuidLnePrmDef","GUID_LNE_PRM_DEF,1,25,1,0,false|,0,CHAR,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getNoLpsGuarMon","setNoLpsGuarMon","NO_LPS_GUAR_MON,3,26,3,0,false|,0,INTEGER,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getIncNoLpsGrMn","setIncNoLpsGrMn","INC_NO_LPS_GR_MN,3,27,3,0,false|,0,INTEGER,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getMinpayTrgAcmDef","setMinpayTrgAcmDef","MINPAY_TRG_ACM_DEF,1,28,1,0,false|,0,CHAR,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getMinpayTrgAcmSus","setMinpayTrgAcmSus","MINPAY_TRG_ACM_SUS,1,29,1,0,false|,0,CHAR,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getDecreaseFaceMeth","setDecreaseFaceMeth","DECREASE_FACE_METH,1,30,4,0,false|,0,CHAR,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getTypChngIncrMeth","setTypChngIncrMeth","TYP_CHNG_INCR_METH,1,31,4,0,false|,0,CHAR,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getTypChngDecrMeth","setTypChngDecrMeth","TYP_CHNG_DECR_METH,1,32,4,0,false|,0,CHAR,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getLoanIntExtrcDys","setLoanIntExtrcDys","LOAN_INT_EXTRC_DYS,3,33,3,0,false|,0,INTEGER,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getCanadianizeIndic","setCanadianizeIndic","CANADIANIZE_INDIC,1,34,1,0,false|,0,CHAR,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getAnnStateTrxCode","setAnnStateTrxCode","ANN_STATE_TRX_CODE,1,35,1,0,false|,0,CHAR,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getPastValueMonths","setPastValueMonths","PAST_VALUE_MONTHS,3,36,3,0,false|,0,INTEGER,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getTransferYrIndic","setTransferYrIndic","TRANSFER_YR_INDIC,1,37,1,0,false|,0,CHAR,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getNumFreeTransYr","setNumFreeTransYr","NUM_FREE_TRANS_YR,3,38,3,0,false|,0,INTEGER,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getNumAlowTransYr","setNumAlowTransYr","NUM_ALOW_TRANS_YR,3,39,3,0,false|,0,INTEGER,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getBypasVarProcInd","setBypasVarProcInd","BYPAS_VAR_PROC_IND,1,40,1,0,false|,0,CHAR,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getCanMinDbPct","setCanMinDbPct","CAN_MIN_DB_PCT,3,41,3,1,false|,0,DOUBLE,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getCanMinMbPct","setCanMinMbPct","CAN_MIN_MB_PCT,3,42,3,1,false|,0,DOUBLE,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getTamraTestInd","setTamraTestInd","TAMRA_TEST_IND,1,43,1,0,false|,0,CHAR,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getTamraRejectInd","setTamraRejectInd","TAMRA_REJECT_IND,1,44,1,0,false|,0,CHAR,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getFutureProcessDy","setFutureProcessDy","FUTURE_PROCESS_DY,3,45,3,0,false|,0,INTEGER,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getPdfPremXcessInd","setPdfPremXcessInd","PDF_PREM_XCESS_IND,1,46,1,0,false|,0,CHAR,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getSpclClassComInd","setSpclClassComInd","SPCL_CLASS_COM_IND,1,47,1,0,false|,0,CHAR,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getRatngFctrComInd","setRatngFctrComInd","RATNG_FCTR_COM_IND,1,48,1,0,false|,0,CHAR,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getFlatExtraComInd","setFlatExtraComInd","FLAT_EXTRA_COM_IND,1,49,1,0,false|,0,CHAR,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getCovTermBasis","setCovTermBasis","COV_TERM_BASIS,1,50,1,0,false|,0,CHAR,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getCovTermAge","setCovTermAge","COV_TERM_AGE,3,51,3,0,false|,0,INTEGER,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getMinimumIssueAge","setMinimumIssueAge","MINIMUM_ISSUE_AGE,3,52,3,0,false|,0,INTEGER,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getMaximumIssueAge","setMaximumIssueAge","MAXIMUM_ISSUE_AGE,3,53,3,0,false|,0,INTEGER,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getSecGuidelineInd","setSecGuidelineInd","SEC_GUIDELINE_IND,1,54,1,0,false|,0,CHAR,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getProjectionInd","setProjectionInd","PROJECTION_IND,1,55,1,0,false|,0,CHAR,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getCostBasisInd","setCostBasisInd","COST_BASIS_IND,1,56,1,0,false|,0,CHAR,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getExchangeInd","setExchangeInd","EXCHANGE_IND,1,57,1,0,false|,0,CHAR,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getBillingChangeInd","setBillingChangeInd","BILLING_CHANGE_IND,1,58,1,0,false|,0,CHAR,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getModifiedLapseInd","setModifiedLapseInd","MODIFIED_LAPSE_IND,1,59,1,0,false|,0,CHAR,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getShadowAcctInd","setShadowAcctInd","SHADOW_ACCT_IND,1,60,1,0,false|,0,CHAR,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getReinstatementType","setReinstatementType","REINSTATEMENT_TYPE,1,61,1,0,false|,0,CHAR,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getPremDbOptEligibilityInd","setPremDbOptEligibilityInd","PREM_DB_OPT_ELIGIBILITY_IND,1,62,1,0,false"));
        addColumnDescriptor(new ColumnDescriptor(this,"getIndexIndicator","setIndexIndicator","INDEX_INDICATOR,1,63,1,0,false|,0,CHAR,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getMecReinGracePeriod","setMecReinGracePeriod","MEC_REIN_GRACE_PERIOD,3,64,3,0,false|,0,INTEGER,0,null,null,null,null,null"));
    }
    
    public String getPagingSQL(String schemaName,boolean isSubsetMode,boolean isNext, boolean locator)
    {
        String pagingWhere = null;
        String order = null;
        if (isNext) {
            if (isSubsetMode)
                if (locator)
                    pagingWhere = ".T004E WHERE (COMPANY_CODE = :company_code) AND (TABLE_SUBSET = :table_subset) AND ((EFFECTIVE_DATE > :effective_date) OR (EFFECTIVE_DATE = :effective_date)) ";
                else 
                    pagingWhere = ".T004E WHERE (COMPANY_CODE = :company_code) AND (TABLE_SUBSET = :table_subset) AND ((EFFECTIVE_DATE > :effective_date)) ";
            else
                if (locator)
                    pagingWhere = ".T004E WHERE (COMPANY_CODE = :company_code) AND ((TABLE_SUBSET > :table_subset) OR (TABLE_SUBSET = :table_subset AND EFFECTIVE_DATE > :effective_date) OR (TABLE_SUBSET = :table_subset AND EFFECTIVE_DATE = :effective_date)) ";
                else
                    pagingWhere = ".T004E WHERE (COMPANY_CODE = :company_code) AND ((TABLE_SUBSET > :table_subset) OR (TABLE_SUBSET = :table_subset AND EFFECTIVE_DATE > :effective_date)) ";
            order = " ORDER BY COMPANY_CODE, TABLE_SUBSET, EFFECTIVE_DATE";
        }
        else {
            if (isSubsetMode)
                pagingWhere = ".T004E WHERE (COMPANY_CODE = :company_code) AND (TABLE_SUBSET = :table_subset) AND ((EFFECTIVE_DATE < :effective_date)) ";
            else
                pagingWhere = ".T004E WHERE (COMPANY_CODE = :company_code) AND ((TABLE_SUBSET < :table_subset) OR (TABLE_SUBSET = :table_subset AND EFFECTIVE_DATE < :effective_date)) ";
            order = " ORDER BY COMPANY_CODE DESC, TABLE_SUBSET DESC, EFFECTIVE_DATE DESC";
        }
        return pagingSql + schemaName + pagingWhere + order;
    }
    
    public String prepareInsertStmt(String schemaName) {
        StringBuffer sb = new StringBuffer();
        sb.append("INSERT INTO ").append(schemaName);
        sb.append(".T004E ( ");
        sb.append("COMPANY_CODE, TABLE_SUBSET, EFFECTIVE_DATE, CORRIDOR_DEF_CODE, CORRIDOR_AMOUNT, CORRIDOR_PCT_FACE, TEFRA_INDICATOR, PYMT_LOAD_TARG_IND, RISK_RATE_DIVISOR, AGE_DEF_CODE, AGE_DEF_TIMING_CDE, RISK_PURCHASE_IND, TYPE_CHANGE_IND, EARLIEST_LAPSE_DEL, LAPSE_GRACE_PERIOD, LAPSE_PEND_PAY_MON, FINL_LPSE_NOTIC_DY, REINSTATE_MONTHS, INT_DUE_GRACE_PERD, PAR_NONPAR_CODE, GRACE_PERD_BILL_IN, ALLOCATN_BASIS_IND, ATTND_AGE_CORR_TBL, WITHDRAW_FACE_METH, GUID_LNE_PRM_DEF, NO_LPS_GUAR_MON, INC_NO_LPS_GR_MN, MINPAY_TRG_ACM_DEF, MINPAY_TRG_ACM_SUS, DECREASE_FACE_METH, TYP_CHNG_INCR_METH, TYP_CHNG_DECR_METH, LOAN_INT_EXTRC_DYS, CANADIANIZE_INDIC, ANN_STATE_TRX_CODE, PAST_VALUE_MONTHS, TRANSFER_YR_INDIC, NUM_FREE_TRANS_YR, NUM_ALOW_TRANS_YR, BYPAS_VAR_PROC_IND, CAN_MIN_DB_PCT, CAN_MIN_MB_PCT, TAMRA_TEST_IND, TAMRA_REJECT_IND, FUTURE_PROCESS_DY, PDF_PREM_XCESS_IND, SPCL_CLASS_COM_IND, RATNG_FCTR_COM_IND, FLAT_EXTRA_COM_IND, COV_TERM_BASIS, COV_TERM_AGE, MINIMUM_ISSUE_AGE, MAXIMUM_ISSUE_AGE, SEC_GUIDELINE_IND, PROJECTION_IND, COST_BASIS_IND, EXCHANGE_IND, BILLING_CHANGE_IND, MODIFIED_LAPSE_IND, SHADOW_ACCT_IND, REINSTATEMENT_TYPE, PREM_DB_OPT_ELIGIBILITY_IND, INDEX_INDICATOR, MEC_REIN_GRACE_PERIOD )");
        sb.append(" VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ? )");
        return sb.toString();
    }
    
    public String prepareUpdateStmt(String schemaName)
    {
        StringBuffer sb = new StringBuffer();
        sb.append("UPDATE ").append(schemaName);
        sb.append(".T004E ");
        sb.append(" SET COMPANY_CODE = ?, TABLE_SUBSET = ?, EFFECTIVE_DATE = ?, CORRIDOR_DEF_CODE = ?, CORRIDOR_AMOUNT = ?, CORRIDOR_PCT_FACE = ?, TEFRA_INDICATOR = ?, PYMT_LOAD_TARG_IND = ?, RISK_RATE_DIVISOR = ?, AGE_DEF_CODE = ?, AGE_DEF_TIMING_CDE = ?, RISK_PURCHASE_IND = ?, TYPE_CHANGE_IND = ?, EARLIEST_LAPSE_DEL = ?, LAPSE_GRACE_PERIOD = ?, LAPSE_PEND_PAY_MON = ?, FINL_LPSE_NOTIC_DY = ?, REINSTATE_MONTHS = ?, INT_DUE_GRACE_PERD = ?, PAR_NONPAR_CODE = ?, GRACE_PERD_BILL_IN = ?, ALLOCATN_BASIS_IND = ?, ATTND_AGE_CORR_TBL = ?, WITHDRAW_FACE_METH = ?, GUID_LNE_PRM_DEF = ?, NO_LPS_GUAR_MON = ?, INC_NO_LPS_GR_MN = ?, MINPAY_TRG_ACM_DEF = ?, MINPAY_TRG_ACM_SUS = ?, DECREASE_FACE_METH = ?, TYP_CHNG_INCR_METH = ?, TYP_CHNG_DECR_METH = ?, LOAN_INT_EXTRC_DYS = ?, CANADIANIZE_INDIC = ?, ANN_STATE_TRX_CODE = ?, PAST_VALUE_MONTHS = ?, TRANSFER_YR_INDIC = ?, NUM_FREE_TRANS_YR = ?, NUM_ALOW_TRANS_YR = ?, BYPAS_VAR_PROC_IND = ?, CAN_MIN_DB_PCT = ?, CAN_MIN_MB_PCT = ?, TAMRA_TEST_IND = ?, TAMRA_REJECT_IND = ?, FUTURE_PROCESS_DY = ?, PDF_PREM_XCESS_IND = ?, SPCL_CLASS_COM_IND = ?, RATNG_FCTR_COM_IND = ?, FLAT_EXTRA_COM_IND = ?, COV_TERM_BASIS = ?, COV_TERM_AGE = ?, MINIMUM_ISSUE_AGE = ?, MAXIMUM_ISSUE_AGE = ?, SEC_GUIDELINE_IND = ?, PROJECTION_IND = ?, COST_BASIS_IND = ?, EXCHANGE_IND = ?, BILLING_CHANGE_IND = ?, MODIFIED_LAPSE_IND = ?, SHADOW_ACCT_IND = ?, REINSTATEMENT_TYPE = ?, PREM_DB_OPT_ELIGIBILITY_IND = ?, INDEX_INDICATOR = ?, MEC_REIN_GRACE_PERIOD = ?");
        sb.append(" WHERE COMPANY_CODE = ? AND TABLE_SUBSET = ? AND EFFECTIVE_DATE = ?");
        return sb.toString();
    }
    
    public String prepareDeleteStmt(String schemaName)
    {
        StringBuffer sb = new StringBuffer();
        sb.append("DELETE FROM ").append(schemaName);
        sb.append(".T004E ");
        sb.append(" WHERE COMPANY_CODE = ? AND TABLE_SUBSET = ? AND EFFECTIVE_DATE = ?");
        return sb.toString();
    }
}
