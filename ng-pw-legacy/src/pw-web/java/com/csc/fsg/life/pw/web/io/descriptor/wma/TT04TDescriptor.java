package com.csc.fsg.life.pw.web.io.descriptor.wma;

import com.csc.fsg.life.pw.web.io.*;

public class TT04TDescriptor
    extends TableDescriptor
{
    private static final String pagingSql = "SELECT COMPANY_CODE, TABLE_SUBSET, EFFECTIVE_DATE, AGE_DEF_CODE, EARL_LAPSE_DELETN, GRACE_PERIOD, INT_DUE_GRACE_PERD, LOAN_INT_EXT_DAYS, MIN_ISSUE_AGE, MAX_ISSUE_AGE, COV_TERM_BASIS, COV_TERM_AGE, PREM_TERM_BASIS, PREM_TERM_AGE, INS_CLASS_CODE, RENEWAL_PERIOD, LEVEL_PREM_IND, DEATH_BEN_AMT_IND, PARTICIPATION_IND, CASH_VALUE_METHOD, AUTOPRLOANAVA_IND, EXTTERMINSAVA_IND, EXTTERMINSPEC_LIM, EXEMPT_INDICATOR, EXTTERMINSFLAT_LIM, EXTTERM_INS_RATING, EXTTERMINSFA_CALC, REDAIDUPAVAIL_IND, REDPADUPFAMT_CALC, AUTONONFORF_OPTION, REINST_MONTHS, REOUTSTDPREM_IND, REOUTSTDLOS_IND, REINSBACKPREM_RATE, REINSMINFUT_MONTHS, MIN_COVERAGE_UNITS, MAX_COVERAGE_UNITS, APLSEMIANNMP_AVAIL, APLQUARTERMP_AVAIL, APLMONTHLYMP_AVAIL, DVDMINCOV_UNIT_REQ, RPUDVDMINCUNIT_REQ, UNEAPREMDEATH_IND, DVD_EXTRACT_DAYS, DEATHPROC_INT_RATE, MAT_EXTRACT_DAYS, FUTUREPROCESS_DAYS, NFO_CALC_IND, SPCLCLASS_COMM_IND, RATINGFACTCOMM_IND, FLATEXTRA_COMM_IND, APL_APPL_RULE, APL_LIMIT_RULE, MIN_CONS_APL, MAX_CONS_MONTHS, APL_BILL_CHANGE, APL_NOTIFY_CODE, LOAN_CV_CALC_RULE, LOAN_CAP_RULE, VANISH_PREM_IND, VANISHPR_PROC_DAYS, ANNUAL_ELIGIBILITY, NFO_LOAN_USAGE, COMMISSION_EXTRACT, REDPDUPMINFACE_AMT, SUSPENSE_CASH_CODE, EXTTRMINSPURND_IND, VP_PUAR_AVAIL_IND, VP_SUFF_RULE, ANNSTMPREANN_DAYS, ANN_STMENT_TYPE, PLANRIDERTYPE_CODE, COST_BASIS_NOTICE, TERMREINPER1_DAYS, TAMRA_TESTING_IND, TAMRA_SUBSTD_IND, SEVENPAYREJREM_IND, R_B_CASH_VALUE_IND, LOANCAPREST_IND, CHANGE_NFO_CODE, M_CASH_VAL_AGE_ADJ, F_CASH_VAL_AGE_ADJ, CVG_LEVEL_ACCT_IND, TAMRA_1035_EXC_IND, POL_PREM_BAND_RULE, PAC_BILLING_IND, LIST_BILL_IND, IND_MAN_BILL_IND, OFF_ANNIV_BILLING, AUTO_REBILL, SUPPRS_BILLEXT_IND, IND_MAN_START_BILL, LIST_STARTBILL_IND, FRACTIONAL_RULE, TAMRA_DUE_DATE_USE, EFFDTE_RESTRCT_IND, FUTURE_DT_POL_IND, APL_INITIAT_RULE, APL_PAC_RULE, FUT_VAL_LOAN_QUOTE, VANISH_PRM_ADV_LIM, PARTIAL_VPAPL_OPT, DIV_OPT_AVAIL_VP, BILL_OPT_FREQ_VP, MPREM_BACKDTE_LIM, MPREM_QUOTE_EFFDT, MPREM_PTD_LIMIT, NET_ANN_PREM_IND, TAMRA_PREM_IND, TAMRA_PREM_DATE, ROP_ELIG_PREM_ACCUM_IND FROM ";
    
    public void initialize()
    {
        setRowClass(TT04TRow.class);
        setTableName("TT04T");
        setTableId("T04");
        super.initialize();
    }
    
    public void initializeColumnDescriptors()
    {
        super.initializeColumnDescriptors();
        addColumnDescriptor(new ColumnDescriptor(this,"getCompanyCode","setCompanyCode","COMPANY_CODE,1,1,3,0,true|,0,CHAR,1,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getTableSubset","setTableSubset","TABLE_SUBSET,1,2,16,0,true|,0,CHAR,1,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getEffectiveDate","setEffectiveDate","EFFECTIVE_DATE,91,3,10,0,true|,0,DATE,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getAgeDefCode","setAgeDefCode","AGE_DEF_CODE,1,4,1,0,false|,0,CHAR,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getEarlLapseDeletn","setEarlLapseDeletn","EARL_LAPSE_DELETN,3,5,2,0,false|,0,INTEGER,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getGracePeriod","setGracePeriod","GRACE_PERIOD,3,6,2,0,false|,0,INTEGER,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getIntDueGracePerd","setIntDueGracePerd","INT_DUE_GRACE_PERD,3,7,2,0,false|,0,INTEGER,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getLoanIntExtDays","setLoanIntExtDays","LOAN_INT_EXT_DAYS,3,8,2,0,false|,0,INTEGER,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getMinIssueAge","setMinIssueAge","MIN_ISSUE_AGE,3,9,3,0,false|,0,INTEGER,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getMaxIssueAge","setMaxIssueAge","MAX_ISSUE_AGE,3,10,3,0,false|,0,INTEGER,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getCovTermBasis","setCovTermBasis","COV_TERM_BASIS,1,11,1,0,false|,0,CHAR,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getCovTermAge","setCovTermAge","COV_TERM_AGE,3,12,3,0,false|,0,INTEGER,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getPremTermBasis","setPremTermBasis","PREM_TERM_BASIS,1,13,1,0,false|,0,CHAR,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getPremTermAge","setPremTermAge","PREM_TERM_AGE,3,14,3,0,false|,0,INTEGER,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getInsClassCode","setInsClassCode","INS_CLASS_CODE,1,15,1,0,false|,0,CHAR,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getRenewalPeriod","setRenewalPeriod","RENEWAL_PERIOD,3,16,2,0,false|,0,INTEGER,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getLevelPremInd","setLevelPremInd","LEVEL_PREM_IND,1,17,1,0,false|,0,CHAR,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getDeathBenAmtInd","setDeathBenAmtInd","DEATH_BEN_AMT_IND,1,18,1,0,false|,0,CHAR,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getParticipationInd","setParticipationInd","PARTICIPATION_IND,1,19,1,0,false|,0,CHAR,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getCashValueMethod","setCashValueMethod","CASH_VALUE_METHOD,1,20,1,0,false|,0,CHAR,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getAutoprloanavaInd","setAutoprloanavaInd","AUTOPRLOANAVA_IND,1,21,1,0,false|,0,CHAR,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getExtterminsavaInd","setExtterminsavaInd","EXTTERMINSAVA_IND,1,22,1,0,false|,0,CHAR,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getExtterminspecLim","setExtterminspecLim","EXTTERMINSPEC_LIM,1,23,2,0,false|,0,CHAR,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getExemptIndicator","setExemptIndicator","EXEMPT_INDICATOR,1,24,1,0,false|,0,CHAR,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getExtterminsflatLim","setExtterminsflatLim","EXTTERMINSFLAT_LIM,3,25,4,2,false|,0,DOUBLE,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getExttermInsRating","setExttermInsRating","EXTTERM_INS_RATING,3,26,5,3,false|,0,DOUBLE,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getExtterminsfaCalc","setExtterminsfaCalc","EXTTERMINSFA_CALC,1,27,1,0,false|,0,CHAR,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getRedaidupavailInd","setRedaidupavailInd","REDAIDUPAVAIL_IND,1,28,1,0,false|,0,CHAR,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getRedpadupfamtCalc","setRedpadupfamtCalc","REDPADUPFAMT_CALC,1,29,1,0,false|,0,CHAR,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getAutononforfOption","setAutononforfOption","AUTONONFORF_OPTION,1,30,1,0,false|,0,CHAR,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getReinstMonths","setReinstMonths","REINST_MONTHS,3,31,3,0,false|,0,INTEGER,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getReoutstdpremInd","setReoutstdpremInd","REOUTSTDPREM_IND,1,32,1,0,false|,0,CHAR,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getReoutstdlosInd","setReoutstdlosInd","REOUTSTDLOS_IND,1,33,1,0,false|,0,CHAR,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getReinsbackpremRate","setReinsbackpremRate","REINSBACKPREM_RATE,3,34,4,2,false|,0,DOUBLE,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getReinsminfutMonths","setReinsminfutMonths","REINSMINFUT_MONTHS,3,35,3,0,false|,0,INTEGER,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getMinCoverageUnits","setMinCoverageUnits","MIN_COVERAGE_UNITS,3,36,11,5,false|,0,DOUBLE,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getMaxCoverageUnits","setMaxCoverageUnits","MAX_COVERAGE_UNITS,3,37,11,5,false|,0,DOUBLE,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getAplsemiannmpAvail","setAplsemiannmpAvail","APLSEMIANNMP_AVAIL,1,38,1,0,false|,0,CHAR,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getAplquartermpAvail","setAplquartermpAvail","APLQUARTERMP_AVAIL,1,39,1,0,false|,0,CHAR,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getAplmonthlympAvail","setAplmonthlympAvail","APLMONTHLYMP_AVAIL,1,40,1,0,false|,0,CHAR,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getDvdmincovUnitReq","setDvdmincovUnitReq","DVDMINCOV_UNIT_REQ,3,41,11,5,false|,0,DOUBLE,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getRpudvdmincunitReq","setRpudvdmincunitReq","RPUDVDMINCUNIT_REQ,3,42,11,5,false|,0,DOUBLE,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getUneapremdeathInd","setUneapremdeathInd","UNEAPREMDEATH_IND,1,43,1,0,false|,0,CHAR,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getDvdExtractDays","setDvdExtractDays","DVD_EXTRACT_DAYS,3,44,3,0,false|,0,INTEGER,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getDeathprocIntRate","setDeathprocIntRate","DEATHPROC_INT_RATE,3,45,4,2,false|,0,DOUBLE,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getMatExtractDays","setMatExtractDays","MAT_EXTRACT_DAYS,3,46,3,0,false|,0,INTEGER,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getFutureprocessDays","setFutureprocessDays","FUTUREPROCESS_DAYS,3,47,3,0,false|,0,INTEGER,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getNfoCalcInd","setNfoCalcInd","NFO_CALC_IND,1,48,1,0,false|,0,CHAR,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getSpclclassCommInd","setSpclclassCommInd","SPCLCLASS_COMM_IND,1,49,1,0,false|,0,CHAR,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getRatingfactcommInd","setRatingfactcommInd","RATINGFACTCOMM_IND,1,50,1,0,false|,0,CHAR,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getFlatextraCommInd","setFlatextraCommInd","FLATEXTRA_COMM_IND,1,51,1,0,false|,0,CHAR,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getAplApplRule","setAplApplRule","APL_APPL_RULE,1,52,1,0,false|,0,CHAR,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getAplLimitRule","setAplLimitRule","APL_LIMIT_RULE,1,53,1,0,false|,0,CHAR,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getMinConsApl","setMinConsApl","MIN_CONS_APL,3,54,2,0,false|,0,INTEGER,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getMaxConsMonths","setMaxConsMonths","MAX_CONS_MONTHS,3,55,2,0,false|,0,INTEGER,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getAplBillChange","setAplBillChange","APL_BILL_CHANGE,1,56,1,0,false|,0,CHAR,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getAplNotifyCode","setAplNotifyCode","APL_NOTIFY_CODE,1,57,1,0,false|,0,CHAR,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getLoanCvCalcRule","setLoanCvCalcRule","LOAN_CV_CALC_RULE,1,58,1,0,false|,0,CHAR,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getLoanCapRule","setLoanCapRule","LOAN_CAP_RULE,1,59,1,0,false|,0,CHAR,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getVanishPremInd","setVanishPremInd","VANISH_PREM_IND,1,60,1,0,false|,0,CHAR,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getVanishprProcDays","setVanishprProcDays","VANISHPR_PROC_DAYS,3,61,2,0,false|,0,INTEGER,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getAnnualEligibility","setAnnualEligibility","ANNUAL_ELIGIBILITY,1,62,1,0,false|,0,CHAR,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getNfoLoanUsage","setNfoLoanUsage","NFO_LOAN_USAGE,1,63,1,0,false|,0,CHAR,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getCommissionExtract","setCommissionExtract","COMMISSION_EXTRACT,1,64,1,0,false|,0,CHAR,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getRedpdupminfaceAmt","setRedpdupminfaceAmt","REDPDUPMINFACE_AMT,3,65,11,2,false|,0,DOUBLE,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getSuspenseCashCode","setSuspenseCashCode","SUSPENSE_CASH_CODE,1,66,1,0,false|,0,CHAR,4,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getExttrminspurndInd","setExttrminspurndInd","EXTTRMINSPURND_IND,1,67,1,0,false|,0,CHAR,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getVpPuarAvailInd","setVpPuarAvailInd","VP_PUAR_AVAIL_IND,1,68,1,0,false|,0,CHAR,4,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getVpSuffRule","setVpSuffRule","VP_SUFF_RULE,1,69,1,0,false|,0,CHAR,4,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getAnnstmpreannDays","setAnnstmpreannDays","ANNSTMPREANN_DAYS,3,70,3,0,false|,0,INTEGER,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getAnnStmentType","setAnnStmentType","ANN_STMENT_TYPE,1,71,1,0,false|,0,CHAR,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getPlanridertypeCode","setPlanridertypeCode","PLANRIDERTYPE_CODE,1,72,2,0,false|,0,CHAR,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getCostBasisNotice","setCostBasisNotice","COST_BASIS_NOTICE,1,73,1,0,false|,0,CHAR,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getTermreinper1Days","setTermreinper1Days","TERMREINPER1_DAYS,3,74,3,0,false|,0,INTEGER,2,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getTamraTestingInd","setTamraTestingInd","TAMRA_TESTING_IND,1,75,1,0,false|,0,CHAR,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getTamraSubstdInd","setTamraSubstdInd","TAMRA_SUBSTD_IND,1,76,1,0,false|,0,CHAR,4,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getSevenpayrejremInd","setSevenpayrejremInd","SEVENPAYREJREM_IND,1,77,1,0,false|,0,CHAR,4,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getRBCashValueInd","setRBCashValueInd","R_B_CASH_VALUE_IND,1,78,1,0,false|,0,CHAR,4,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getLoancaprestInd","setLoancaprestInd","LOANCAPREST_IND,1,79,1,0,false|,0,CHAR,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getChangeNfoCode","setChangeNfoCode","CHANGE_NFO_CODE,1,80,1,0,false|,0,CHAR,4,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getMCashValAgeAdj","setMCashValAgeAdj","M_CASH_VAL_AGE_ADJ,3,81,3,0,false|,0,INTEGER,4,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getFCashValAgeAdj","setFCashValAgeAdj","F_CASH_VAL_AGE_ADJ,3,82,3,0,false|,0,INTEGER,4,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getCvgLevelAcctInd","setCvgLevelAcctInd","CVG_LEVEL_ACCT_IND,1,83,1,0,false|,0,CHAR,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getTamra1035ExcInd","setTamra1035ExcInd","TAMRA_1035_EXC_IND,1,84,1,0,false|,0,CHAR,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getPolPremBandRule","setPolPremBandRule","POL_PREM_BAND_RULE,1,85,1,0,false|,0,CHAR,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getPacBillingInd","setPacBillingInd","PAC_BILLING_IND,1,86,2,0,false|,0,CHAR,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getListBillInd","setListBillInd","LIST_BILL_IND,1,87,2,0,false|,0,CHAR,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getIndManBillInd","setIndManBillInd","IND_MAN_BILL_IND,1,88,2,0,false|,0,CHAR,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getOffAnnivBilling","setOffAnnivBilling","OFF_ANNIV_BILLING,1,89,1,0,false|,0,CHAR,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getAutoRebill","setAutoRebill","AUTO_REBILL,1,90,1,0,false|,0,CHAR,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getSupprsBillextInd","setSupprsBillextInd","SUPPRS_BILLEXT_IND,1,91,1,0,false|,0,CHAR,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getIndManStartBill","setIndManStartBill","IND_MAN_START_BILL,1,92,1,0,false|,0,CHAR,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getListStartbillInd","setListStartbillInd","LIST_STARTBILL_IND,1,93,1,0,false|,0,CHAR,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getFractionalRule","setFractionalRule","FRACTIONAL_RULE,1,94,1,0,false|,0,CHAR,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getTamraDueDateUse","setTamraDueDateUse","TAMRA_DUE_DATE_USE,1,95,1,0,false|,0,CHAR,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getEffdteRestrctInd","setEffdteRestrctInd","EFFDTE_RESTRCT_IND,1,96,1,0,false|,0,CHAR,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getFutureDtPolInd","setFutureDtPolInd","FUTURE_DT_POL_IND,1,97,1,0,false|,0,CHAR,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getAplInitiatRule","setAplInitiatRule","APL_INITIAT_RULE,1,98,1,0,false|,0,CHAR,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getAplPacRule","setAplPacRule","APL_PAC_RULE,1,99,1,0,false|,0,CHAR,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getFutValLoanQuote","setFutValLoanQuote","FUT_VAL_LOAN_QUOTE,1,100,1,0,false|,0,CHAR,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getVanishPrmAdvLim","setVanishPrmAdvLim","VANISH_PRM_ADV_LIM,3,101,2,0,false|,0,INTEGER,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getPartialVpaplOpt","setPartialVpaplOpt","PARTIAL_VPAPL_OPT,1,102,1,0,false|,0,CHAR,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getDivOptAvailVp","setDivOptAvailVp","DIV_OPT_AVAIL_VP,1,103,1,0,false|,0,CHAR,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getBillOptFreqVp","setBillOptFreqVp","BILL_OPT_FREQ_VP,1,104,1,0,false|,0,CHAR,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getMpremBackdteLim","setMpremBackdteLim","MPREM_BACKDTE_LIM,3,105,3,0,false|,0,INTEGER,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getMpremQuoteEffdt","setMpremQuoteEffdt","MPREM_QUOTE_EFFDT,1,106,1,0,false|,0,CHAR,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getMpremPtdLimit","setMpremPtdLimit","MPREM_PTD_LIMIT,3,107,3,0,false|,0,INTEGER,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getNetAnnPremInd","setNetAnnPremInd","NET_ANN_PREM_IND,1,108,1,0,false|,0,CHAR,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getTamraPremInd","setTamraPremInd","TAMRA_PREM_IND,1,109,1,0,false|,0,CHAR,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getTamraPremDate","setTamraPremDate","TAMRA_PREM_DATE,91,110,10,0,false|,0,DATE,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getRopEligPremAccumInd","setRopEligPremAccumInd","ROP_ELIG_PREM_ACCUM_IND,1,111,2,0,false|,0,CHAR,0,null,null,null,null,null"));
    }
    
    public String getPagingSQL(String schemaName,boolean isSubsetMode,boolean isNext, boolean locator)
    {
        String pagingWhere = null;
        String order = null;
        if (isNext) {
            if (isSubsetMode)
                if (locator)
                    pagingWhere = ".TT04T WHERE (COMPANY_CODE = :company_code) AND (TABLE_SUBSET = :table_subset) AND ((EFFECTIVE_DATE > :effective_date) OR (EFFECTIVE_DATE = :effective_date)) ";
                else 
                    pagingWhere = ".TT04T WHERE (COMPANY_CODE = :company_code) AND (TABLE_SUBSET = :table_subset) AND ((EFFECTIVE_DATE > :effective_date)) ";
            else
                if (locator)
                    pagingWhere = ".TT04T WHERE (COMPANY_CODE = :company_code) AND ((TABLE_SUBSET > :table_subset) OR (TABLE_SUBSET = :table_subset AND EFFECTIVE_DATE > :effective_date) OR (TABLE_SUBSET = :table_subset AND EFFECTIVE_DATE = :effective_date)) ";
                else
                    pagingWhere = ".TT04T WHERE (COMPANY_CODE = :company_code) AND ((TABLE_SUBSET > :table_subset) OR (TABLE_SUBSET = :table_subset AND EFFECTIVE_DATE > :effective_date)) ";
            order = " ORDER BY COMPANY_CODE, TABLE_SUBSET, EFFECTIVE_DATE";
        }
        else {
            if (isSubsetMode)
                pagingWhere = ".TT04T WHERE (COMPANY_CODE = :company_code) AND (TABLE_SUBSET = :table_subset) AND ((EFFECTIVE_DATE < :effective_date)) ";
            else
                pagingWhere = ".TT04T WHERE (COMPANY_CODE = :company_code) AND ((TABLE_SUBSET < :table_subset) OR (TABLE_SUBSET = :table_subset AND EFFECTIVE_DATE < :effective_date)) ";
            order = " ORDER BY COMPANY_CODE DESC, TABLE_SUBSET DESC, EFFECTIVE_DATE DESC";
        }
        return pagingSql + schemaName + pagingWhere + order;
    }
    
    public String prepareInsertStmt(String schemaName) {
        StringBuffer sb = new StringBuffer();
        sb.append("INSERT INTO ").append(schemaName);
        sb.append(".TT04T ( ");
        sb.append("COMPANY_CODE, TABLE_SUBSET, EFFECTIVE_DATE, AGE_DEF_CODE, EARL_LAPSE_DELETN, GRACE_PERIOD, INT_DUE_GRACE_PERD, LOAN_INT_EXT_DAYS, MIN_ISSUE_AGE, MAX_ISSUE_AGE, COV_TERM_BASIS, COV_TERM_AGE, PREM_TERM_BASIS, PREM_TERM_AGE, INS_CLASS_CODE, RENEWAL_PERIOD, LEVEL_PREM_IND, DEATH_BEN_AMT_IND, PARTICIPATION_IND, CASH_VALUE_METHOD, AUTOPRLOANAVA_IND, EXTTERMINSAVA_IND, EXTTERMINSPEC_LIM, EXEMPT_INDICATOR, EXTTERMINSFLAT_LIM, EXTTERM_INS_RATING, EXTTERMINSFA_CALC, REDAIDUPAVAIL_IND, REDPADUPFAMT_CALC, AUTONONFORF_OPTION, REINST_MONTHS, REOUTSTDPREM_IND, REOUTSTDLOS_IND, REINSBACKPREM_RATE, REINSMINFUT_MONTHS, MIN_COVERAGE_UNITS, MAX_COVERAGE_UNITS, APLSEMIANNMP_AVAIL, APLQUARTERMP_AVAIL, APLMONTHLYMP_AVAIL, DVDMINCOV_UNIT_REQ, RPUDVDMINCUNIT_REQ, UNEAPREMDEATH_IND, DVD_EXTRACT_DAYS, DEATHPROC_INT_RATE, MAT_EXTRACT_DAYS, FUTUREPROCESS_DAYS, NFO_CALC_IND, SPCLCLASS_COMM_IND, RATINGFACTCOMM_IND, FLATEXTRA_COMM_IND, APL_APPL_RULE, APL_LIMIT_RULE, MIN_CONS_APL, MAX_CONS_MONTHS, APL_BILL_CHANGE, APL_NOTIFY_CODE, LOAN_CV_CALC_RULE, LOAN_CAP_RULE, VANISH_PREM_IND, VANISHPR_PROC_DAYS, ANNUAL_ELIGIBILITY, NFO_LOAN_USAGE, COMMISSION_EXTRACT, REDPDUPMINFACE_AMT, SUSPENSE_CASH_CODE, EXTTRMINSPURND_IND, VP_PUAR_AVAIL_IND, VP_SUFF_RULE, ANNSTMPREANN_DAYS, ANN_STMENT_TYPE, PLANRIDERTYPE_CODE, COST_BASIS_NOTICE, TERMREINPER1_DAYS, TAMRA_TESTING_IND, TAMRA_SUBSTD_IND, SEVENPAYREJREM_IND, R_B_CASH_VALUE_IND, LOANCAPREST_IND, CHANGE_NFO_CODE, M_CASH_VAL_AGE_ADJ, F_CASH_VAL_AGE_ADJ, CVG_LEVEL_ACCT_IND, TAMRA_1035_EXC_IND, POL_PREM_BAND_RULE, PAC_BILLING_IND, LIST_BILL_IND, IND_MAN_BILL_IND, OFF_ANNIV_BILLING, AUTO_REBILL, SUPPRS_BILLEXT_IND, IND_MAN_START_BILL, LIST_STARTBILL_IND, FRACTIONAL_RULE, TAMRA_DUE_DATE_USE, EFFDTE_RESTRCT_IND, FUTURE_DT_POL_IND, APL_INITIAT_RULE, APL_PAC_RULE, FUT_VAL_LOAN_QUOTE, VANISH_PRM_ADV_LIM, PARTIAL_VPAPL_OPT, DIV_OPT_AVAIL_VP, BILL_OPT_FREQ_VP, MPREM_BACKDTE_LIM, MPREM_QUOTE_EFFDT, MPREM_PTD_LIMIT, NET_ANN_PREM_IND, TAMRA_PREM_IND, TAMRA_PREM_DATE, ROP_ELIG_PREM_ACCUM_IND )");
        sb.append(" VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ? )");
        return sb.toString();
    }
    
    public String prepareUpdateStmt(String schemaName)
    {
        StringBuffer sb = new StringBuffer();
        sb.append("UPDATE ").append(schemaName);
        sb.append(".TT04T ");
        sb.append(" SET COMPANY_CODE = ?, TABLE_SUBSET = ?, EFFECTIVE_DATE = ?, AGE_DEF_CODE = ?, EARL_LAPSE_DELETN = ?, GRACE_PERIOD = ?, INT_DUE_GRACE_PERD = ?, LOAN_INT_EXT_DAYS = ?, MIN_ISSUE_AGE = ?, MAX_ISSUE_AGE = ?, COV_TERM_BASIS = ?, COV_TERM_AGE = ?, PREM_TERM_BASIS = ?, PREM_TERM_AGE = ?, INS_CLASS_CODE = ?, RENEWAL_PERIOD = ?, LEVEL_PREM_IND = ?, DEATH_BEN_AMT_IND = ?, PARTICIPATION_IND = ?, CASH_VALUE_METHOD = ?, AUTOPRLOANAVA_IND = ?, EXTTERMINSAVA_IND = ?, EXTTERMINSPEC_LIM = ?, EXEMPT_INDICATOR = ?, EXTTERMINSFLAT_LIM = ?, EXTTERM_INS_RATING = ?, EXTTERMINSFA_CALC = ?, REDAIDUPAVAIL_IND = ?, REDPADUPFAMT_CALC = ?, AUTONONFORF_OPTION = ?, REINST_MONTHS = ?, REOUTSTDPREM_IND = ?, REOUTSTDLOS_IND = ?, REINSBACKPREM_RATE = ?, REINSMINFUT_MONTHS = ?, MIN_COVERAGE_UNITS = ?, MAX_COVERAGE_UNITS = ?, APLSEMIANNMP_AVAIL = ?, APLQUARTERMP_AVAIL = ?, APLMONTHLYMP_AVAIL = ?, DVDMINCOV_UNIT_REQ = ?, RPUDVDMINCUNIT_REQ = ?, UNEAPREMDEATH_IND = ?, DVD_EXTRACT_DAYS = ?, DEATHPROC_INT_RATE = ?, MAT_EXTRACT_DAYS = ?, FUTUREPROCESS_DAYS = ?, NFO_CALC_IND = ?, SPCLCLASS_COMM_IND = ?, RATINGFACTCOMM_IND = ?, FLATEXTRA_COMM_IND = ?, APL_APPL_RULE = ?, APL_LIMIT_RULE = ?, MIN_CONS_APL = ?, MAX_CONS_MONTHS = ?, APL_BILL_CHANGE = ?, APL_NOTIFY_CODE = ?, LOAN_CV_CALC_RULE = ?, LOAN_CAP_RULE = ?, VANISH_PREM_IND = ?, VANISHPR_PROC_DAYS = ?, ANNUAL_ELIGIBILITY = ?, NFO_LOAN_USAGE = ?, COMMISSION_EXTRACT = ?, REDPDUPMINFACE_AMT = ?, SUSPENSE_CASH_CODE = ?, EXTTRMINSPURND_IND = ?, VP_PUAR_AVAIL_IND = ?, VP_SUFF_RULE = ?, ANNSTMPREANN_DAYS = ?, ANN_STMENT_TYPE = ?, PLANRIDERTYPE_CODE = ?, COST_BASIS_NOTICE = ?, TERMREINPER1_DAYS = ?, TAMRA_TESTING_IND = ?, TAMRA_SUBSTD_IND = ?, SEVENPAYREJREM_IND = ?, R_B_CASH_VALUE_IND = ?, LOANCAPREST_IND = ?, CHANGE_NFO_CODE = ?, M_CASH_VAL_AGE_ADJ = ?, F_CASH_VAL_AGE_ADJ = ?, CVG_LEVEL_ACCT_IND = ?, TAMRA_1035_EXC_IND = ?, POL_PREM_BAND_RULE = ?, PAC_BILLING_IND = ?, LIST_BILL_IND = ?, IND_MAN_BILL_IND = ?, OFF_ANNIV_BILLING = ?, AUTO_REBILL = ?, SUPPRS_BILLEXT_IND = ?, IND_MAN_START_BILL = ?, LIST_STARTBILL_IND = ?, FRACTIONAL_RULE = ?, TAMRA_DUE_DATE_USE = ?, EFFDTE_RESTRCT_IND = ?, FUTURE_DT_POL_IND = ?, APL_INITIAT_RULE = ?, APL_PAC_RULE = ?, FUT_VAL_LOAN_QUOTE = ?, VANISH_PRM_ADV_LIM = ?, PARTIAL_VPAPL_OPT = ?, DIV_OPT_AVAIL_VP = ?, BILL_OPT_FREQ_VP = ?, MPREM_BACKDTE_LIM = ?, MPREM_QUOTE_EFFDT = ?, MPREM_PTD_LIMIT = ?, NET_ANN_PREM_IND = ?, TAMRA_PREM_IND = ?, TAMRA_PREM_DATE = ?, ROP_ELIG_PREM_ACCUM_IND = ?");
        sb.append(" WHERE COMPANY_CODE = ? AND TABLE_SUBSET = ? AND EFFECTIVE_DATE = ?");
        return sb.toString();
    }
    
    public String prepareDeleteStmt(String schemaName)
    {
        StringBuffer sb = new StringBuffer();
        sb.append("DELETE FROM ").append(schemaName);
        sb.append(".TT04T ");
        sb.append(" WHERE COMPANY_CODE = ? AND TABLE_SUBSET = ? AND EFFECTIVE_DATE = ?");
        return sb.toString();
    }
}
