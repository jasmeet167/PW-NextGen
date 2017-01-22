package com.csc.fsg.life.pw.web.io.descriptor.wma;

import com.csc.fsg.life.pw.web.io.*;

public class TTB1TDescriptor
    extends TableDescriptor
{
    private static final String pagingSql = "SELECT COMPANY_CODE, TABLE_SUBSET, EFFECTIVE_DATE, AGE_DEF_CODE, MIN_ISSUE_AGE, MAX_ISSUE_AGE, COV_TERM_BASIS, COV_TERM_AGE, PREM_TERM_BASIS, PREM_TERM_AGE, INS_CLASS_CODE, RENEWAL_PERIOD, LEVEL_PREM_IND, DEATH_BEN_AMT_IND, PARTICIPATION_IND, CASH_VALUE_METHOD, EXT_TRM_IN_AVL_IND, EXT_TRM_IN_SPC_LIM, EXT_TRM_IN_FLT_LIM, EXT_TRM_IN_RATING, EXT_TRM_IN_FCE_CLC, RED_PD_UP_AVL_IND, RD_PDUP_FC_AMT_CLC, AUTO_NONFORF_OPT, MIN_COVERAGE_UNITS, MAX_COVERAGE_UNITS, AWB_MAX_AGE, AWB_ADDL_PRM_IND, UNERND_PRM_DTH_IND, PW_APW_PPW_IND, DVD_MIN_COV_UNITS, SPCL_CLS_COMM_IND, RATNG_FCTR_COM_IND, FLAT_EXTRA_COM_IND, PAYR_DISAB_TRM_BAS, PAYR_DISAB_AGE_DUR, INSD_DISAB_TRM_AGE, COST_BASIS_ACCUM, DIS_TERM_CODE, GFT_EST_TAX_QT_IND, RPU_FLX_PUAR_USAGE, ETI_FLX_PUAR_USAGE, TAMRA_CALC_CODE, PLAN_RDR_TYPE_CODE, ABR_METHD, ABR_RD_LN_MTHD_IND, ABR_RED_CODE, ABR_MN_BSE_POL_AMT, ABR_MIN_BASE_AMT, ABR_MIN_BASE_PCT, ABR_MAX_BASE_AMT, ABR_MAX_BASE_PCT, FLEX_RIDER_IND, PUAR_IND, OTHR_TRM_RDR_IND, PUAR_NSP_RULE, NSP_DAYS, MIN_SCD_AN_PUR_PRM, MAX_SCD_AN_PUR_PRM, MIN_UNSCD_PUAR_PRM, MAX_UNSCD_PUAR_PRM, MAX_PUAR_PRM_YR, PW_CVG_AGE_RULE, PW_CHANGE_RULE, PAR_FLX_SEP_BL_IND, UNIT_PUR_RND_RULE, FLX_RDR_METHD_CD, MAX_FLX_RDR_TO_AGE, FLX_RDR_CON_PR_YRS, FLX_RDR_CON_AGE, FLX_RDR_PRM_PP, FLX_MIN_AMT_INSUFF, FLX_PUR_RID_IS_AGE, FLX_RDR_NY_ATT_AGE, FLX_RDR_MAX_SUB_PP, DEATH_UNPD_PRM_IND, TAMRA_PUAR_ANNV_CD, PUAR_1035_BILL_IND, FLEX_MINRISK_AMT_I, FLEX_PUAR_REINST_I, UPD_SUPPLEM_BILL, PW_PUAR_INDICATOR, ROP_ELIG_PREM_ACCUM_IND, ROP_CHARGE_METHOD FROM ";
    
    public void initialize()
    {
        setRowClass(TTB1TRow.class);
        setTableName("TTB1T");
        setTableId("TB1");
        super.initialize();
    }
    
    public void initializeColumnDescriptors()
    {
        super.initializeColumnDescriptors();
        addColumnDescriptor(new ColumnDescriptor(this,"getCompanyCode","setCompanyCode","COMPANY_CODE,1,1,3,0,true|,0,CHAR,1,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getTableSubset","setTableSubset","TABLE_SUBSET,1,2,16,0,true|,0,CHAR,1,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getEffectiveDate","setEffectiveDate","EFFECTIVE_DATE,91,3,10,0,true|,0,DATE,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getAgeDefCode","setAgeDefCode","AGE_DEF_CODE,1,4,1,0,false|,0,CHAR,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getMinIssueAge","setMinIssueAge","MIN_ISSUE_AGE,3,5,3,0,false|,0,INTEGER,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getMaxIssueAge","setMaxIssueAge","MAX_ISSUE_AGE,3,6,3,0,false|,0,INTEGER,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getCovTermBasis","setCovTermBasis","COV_TERM_BASIS,1,7,1,0,false|,0,CHAR,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getCovTermAge","setCovTermAge","COV_TERM_AGE,3,8,3,0,false|,0,INTEGER,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getPremTermBasis","setPremTermBasis","PREM_TERM_BASIS,1,9,1,0,false|,0,CHAR,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getPremTermAge","setPremTermAge","PREM_TERM_AGE,3,10,3,0,false|,0,INTEGER,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getInsClassCode","setInsClassCode","INS_CLASS_CODE,1,11,1,0,false|,0,CHAR,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getRenewalPeriod","setRenewalPeriod","RENEWAL_PERIOD,3,12,2,0,false|,0,INTEGER,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getLevelPremInd","setLevelPremInd","LEVEL_PREM_IND,1,13,1,0,false|,0,CHAR,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getDeathBenAmtInd","setDeathBenAmtInd","DEATH_BEN_AMT_IND,1,14,1,0,false|,0,CHAR,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getParticipationInd","setParticipationInd","PARTICIPATION_IND,1,15,1,0,false|,0,CHAR,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getCashValueMethod","setCashValueMethod","CASH_VALUE_METHOD,1,16,1,0,false|,0,CHAR,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getExtTrmInAvlInd","setExtTrmInAvlInd","EXT_TRM_IN_AVL_IND,1,17,1,0,false|,0,CHAR,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getExtTrmInSpcLim","setExtTrmInSpcLim","EXT_TRM_IN_SPC_LIM,1,18,2,0,false|,0,CHAR,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getExtTrmInFltLim","setExtTrmInFltLim","EXT_TRM_IN_FLT_LIM,1,19,4,0,false|,0,CHAR,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getExtTrmInRating","setExtTrmInRating","EXT_TRM_IN_RATING,1,20,5,0,false|,0,CHAR,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getExtTrmInFceClc","setExtTrmInFceClc","EXT_TRM_IN_FCE_CLC,1,21,1,0,false|,0,CHAR,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getRedPdUpAvlInd","setRedPdUpAvlInd","RED_PD_UP_AVL_IND,1,22,1,0,false|,0,CHAR,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getRdPdupFcAmtClc","setRdPdupFcAmtClc","RD_PDUP_FC_AMT_CLC,1,23,1,0,false|,0,CHAR,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getAutoNonforfOpt","setAutoNonforfOpt","AUTO_NONFORF_OPT,1,24,1,0,false|,0,CHAR,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getMinCoverageUnits","setMinCoverageUnits","MIN_COVERAGE_UNITS,3,25,11,5,false|,0,DOUBLE,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getMaxCoverageUnits","setMaxCoverageUnits","MAX_COVERAGE_UNITS,3,26,11,5,false|,0,DOUBLE,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getAwbMaxAge","setAwbMaxAge","AWB_MAX_AGE,3,27,3,0,false|,0,INTEGER,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getAwbAddlPrmInd","setAwbAddlPrmInd","AWB_ADDL_PRM_IND,1,28,1,0,false|,0,CHAR,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getUnerndPrmDthInd","setUnerndPrmDthInd","UNERND_PRM_DTH_IND,1,29,1,0,false|,0,CHAR,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getPwApwPpwInd","setPwApwPpwInd","PW_APW_PPW_IND,1,30,1,0,false|,0,CHAR,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getDvdMinCovUnits","setDvdMinCovUnits","DVD_MIN_COV_UNITS,3,31,11,5,false|,0,DOUBLE,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getSpclClsCommInd","setSpclClsCommInd","SPCL_CLS_COMM_IND,1,32,1,0,false|,0,CHAR,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getRatngFctrComInd","setRatngFctrComInd","RATNG_FCTR_COM_IND,1,33,1,0,false|,0,CHAR,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getFlatExtraComInd","setFlatExtraComInd","FLAT_EXTRA_COM_IND,1,34,1,0,false|,0,CHAR,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getPayrDisabTrmBas","setPayrDisabTrmBas","PAYR_DISAB_TRM_BAS,1,35,1,0,false|,0,CHAR,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getPayrDisabAgeDur","setPayrDisabAgeDur","PAYR_DISAB_AGE_DUR,3,36,3,0,false|,0,INTEGER,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getInsdDisabTrmAge","setInsdDisabTrmAge","INSD_DISAB_TRM_AGE,3,37,3,0,false|,0,INTEGER,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getCostBasisAccum","setCostBasisAccum","COST_BASIS_ACCUM,1,38,1,0,false|,0,CHAR,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getDisTermCode","setDisTermCode","DIS_TERM_CODE,1,39,1,0,false|,0,CHAR,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getGftEstTaxQtInd","setGftEstTaxQtInd","GFT_EST_TAX_QT_IND,1,40,1,0,false|,0,CHAR,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getRpuFlxPuarUsage","setRpuFlxPuarUsage","RPU_FLX_PUAR_USAGE,1,41,1,0,false|,0,CHAR,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getEtiFlxPuarUsage","setEtiFlxPuarUsage","ETI_FLX_PUAR_USAGE,1,42,1,0,false|,0,CHAR,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getTamraCalcCode","setTamraCalcCode","TAMRA_CALC_CODE,1,43,1,0,false|,0,CHAR,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getPlanRdrTypeCode","setPlanRdrTypeCode","PLAN_RDR_TYPE_CODE,1,44,2,0,false|,0,CHAR,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getAbrMethd","setAbrMethd","ABR_METHD,1,45,1,0,false|,0,CHAR,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getAbrRdLnMthdInd","setAbrRdLnMthdInd","ABR_RD_LN_MTHD_IND,1,46,1,0,false|,0,CHAR,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getAbrRedCode","setAbrRedCode","ABR_RED_CODE,1,47,1,0,false|,0,CHAR,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getAbrMnBsePolAmt","setAbrMnBsePolAmt","ABR_MN_BSE_POL_AMT,3,48,11,2,false|,0,DOUBLE,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getAbrMinBaseAmt","setAbrMinBaseAmt","ABR_MIN_BASE_AMT,3,49,11,2,false|,0,DOUBLE,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getAbrMinBasePct","setAbrMinBasePct","ABR_MIN_BASE_PCT,3,50,5,2,false|,0,DOUBLE,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getAbrMaxBaseAmt","setAbrMaxBaseAmt","ABR_MAX_BASE_AMT,3,51,11,2,false|,0,DOUBLE,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getAbrMaxBasePct","setAbrMaxBasePct","ABR_MAX_BASE_PCT,3,52,5,2,false|,0,DOUBLE,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getFlexRiderInd","setFlexRiderInd","FLEX_RIDER_IND,1,53,1,0,false|,0,CHAR,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getPuarInd","setPuarInd","PUAR_IND,1,54,1,0,false|,0,CHAR,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getOthrTrmRdrInd","setOthrTrmRdrInd","OTHR_TRM_RDR_IND,1,55,1,0,false|,0,CHAR,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getPuarNspRule","setPuarNspRule","PUAR_NSP_RULE,1,56,8,0,false|,0,CHAR,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getNspDays","setNspDays","NSP_DAYS,3,57,3,0,false|,0,INTEGER,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getMinScdAnPurPrm","setMinScdAnPurPrm","MIN_SCD_AN_PUR_PRM,3,58,11,2,false|,0,DOUBLE,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getMaxScdAnPurPrm","setMaxScdAnPurPrm","MAX_SCD_AN_PUR_PRM,3,59,11,2,false|,0,DOUBLE,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getMinUnscdPuarPrm","setMinUnscdPuarPrm","MIN_UNSCD_PUAR_PRM,3,60,11,2,false|,0,DOUBLE,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getMaxUnscdPuarPrm","setMaxUnscdPuarPrm","MAX_UNSCD_PUAR_PRM,3,61,11,2,false|,0,DOUBLE,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getMaxPuarPrmYr","setMaxPuarPrmYr","MAX_PUAR_PRM_YR,3,62,2,0,false|,0,INTEGER,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getPwCvgAgeRule","setPwCvgAgeRule","PW_CVG_AGE_RULE,3,63,1,0,false|,0,INTEGER,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getPwChangeRule","setPwChangeRule","PW_CHANGE_RULE,3,64,1,0,false|,0,INTEGER,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getParFlxSepBlInd","setParFlxSepBlInd","PAR_FLX_SEP_BL_IND,1,65,1,0,false|,0,CHAR,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getUnitPurRndRule","setUnitPurRndRule","UNIT_PUR_RND_RULE,1,66,1,0,false|,0,CHAR,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getFlxRdrMethdCd","setFlxRdrMethdCd","FLX_RDR_METHD_CD,1,67,1,0,false|,0,CHAR,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getMaxFlxRdrToAge","setMaxFlxRdrToAge","MAX_FLX_RDR_TO_AGE,3,68,3,0,false|,0,INTEGER,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getFlxRdrConPrYrs","setFlxRdrConPrYrs","FLX_RDR_CON_PR_YRS,3,69,2,0,false|,0,INTEGER,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getFlxRdrConAge","setFlxRdrConAge","FLX_RDR_CON_AGE,3,70,3,0,false|,0,INTEGER,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getFlxRdrPrmPp","setFlxRdrPrmPp","FLX_RDR_PRM_PP,3,71,2,0,false|,0,INTEGER,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getFlxMinAmtInsuff","setFlxMinAmtInsuff","FLX_MIN_AMT_INSUFF,3,72,11,2,false|,0,DOUBLE,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getFlxPurRidIsAge","setFlxPurRidIsAge","FLX_PUR_RID_IS_AGE,1,73,1,0,false|,0,CHAR,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getFlxRdrNyAttAge","setFlxRdrNyAttAge","FLX_RDR_NY_ATT_AGE,3,74,3,0,false|,0,INTEGER,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getFlxRdrMaxSubPp","setFlxRdrMaxSubPp","FLX_RDR_MAX_SUB_PP,3,75,3,0,false|,0,INTEGER,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getDeathUnpdPrmInd","setDeathUnpdPrmInd","DEATH_UNPD_PRM_IND,1,76,1,0,false|,0,CHAR,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getTamraPuarAnnvCd","setTamraPuarAnnvCd","TAMRA_PUAR_ANNV_CD,1,77,1,0,false|,0,CHAR,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getPuar1035BillInd","setPuar1035BillInd","PUAR_1035_BILL_IND,1,78,1,0,false|,0,CHAR,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getFlexMinriskAmtI","setFlexMinriskAmtI","FLEX_MINRISK_AMT_I,3,79,11,2,false|,0,DOUBLE,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getFlexPuarReinstI","setFlexPuarReinstI","FLEX_PUAR_REINST_I,1,80,1,0,false|,0,CHAR,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getUpdSupplemBill","setUpdSupplemBill","UPD_SUPPLEM_BILL,1,81,1,0,false|,0,CHAR,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getPwPuarIndicator","setPwPuarIndicator","PW_PUAR_INDICATOR,1,82,1,0,false|,0,CHAR,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getRopEligPremAccumInd","setRopEligPremAccumInd","ROP_ELIG_PREM_ACCUM_IND,1,83,2,0,false|,0,CHAR,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getRopChargeMethod","setRopChargeMethod","ROP_CHARGE_METHOD,1,84,1,0,false|,0,CHAR,0,null,null,null,null,null"));
    }
    
    public String getPagingSQL(String schemaName,boolean isSubsetMode,boolean isNext, boolean locator)
    {
        String pagingWhere = null;
        String order = null;
        if (isNext) {
            if (isSubsetMode)
                if (locator)
                    pagingWhere = ".TTB1T WHERE (COMPANY_CODE = :company_code) AND (TABLE_SUBSET = :table_subset) AND ((EFFECTIVE_DATE > :effective_date) OR (EFFECTIVE_DATE = :effective_date)) ";
                else 
                    pagingWhere = ".TTB1T WHERE (COMPANY_CODE = :company_code) AND (TABLE_SUBSET = :table_subset) AND ((EFFECTIVE_DATE > :effective_date)) ";
            else
                if (locator)
                    pagingWhere = ".TTB1T WHERE (COMPANY_CODE = :company_code) AND ((TABLE_SUBSET > :table_subset) OR (TABLE_SUBSET = :table_subset AND EFFECTIVE_DATE > :effective_date) OR (TABLE_SUBSET = :table_subset AND EFFECTIVE_DATE = :effective_date)) ";
                else
                    pagingWhere = ".TTB1T WHERE (COMPANY_CODE = :company_code) AND ((TABLE_SUBSET > :table_subset) OR (TABLE_SUBSET = :table_subset AND EFFECTIVE_DATE > :effective_date)) ";
            order = " ORDER BY COMPANY_CODE, TABLE_SUBSET, EFFECTIVE_DATE";
        }
        else {
            if (isSubsetMode)
                pagingWhere = ".TTB1T WHERE (COMPANY_CODE = :company_code) AND (TABLE_SUBSET = :table_subset) AND ((EFFECTIVE_DATE < :effective_date)) ";
            else
                pagingWhere = ".TTB1T WHERE (COMPANY_CODE = :company_code) AND ((TABLE_SUBSET < :table_subset) OR (TABLE_SUBSET = :table_subset AND EFFECTIVE_DATE < :effective_date)) ";
            order = " ORDER BY COMPANY_CODE DESC, TABLE_SUBSET DESC, EFFECTIVE_DATE DESC";
        }
        return pagingSql + schemaName + pagingWhere + order;
    }
    
    public String prepareInsertStmt(String schemaName) {
        StringBuffer sb = new StringBuffer();
        sb.append("INSERT INTO ").append(schemaName);
        sb.append(".TTB1T ( ");
        sb.append("COMPANY_CODE, TABLE_SUBSET, EFFECTIVE_DATE, AGE_DEF_CODE, MIN_ISSUE_AGE, MAX_ISSUE_AGE, COV_TERM_BASIS, COV_TERM_AGE, PREM_TERM_BASIS, PREM_TERM_AGE, INS_CLASS_CODE, RENEWAL_PERIOD, LEVEL_PREM_IND, DEATH_BEN_AMT_IND, PARTICIPATION_IND, CASH_VALUE_METHOD, EXT_TRM_IN_AVL_IND, EXT_TRM_IN_SPC_LIM, EXT_TRM_IN_FLT_LIM, EXT_TRM_IN_RATING, EXT_TRM_IN_FCE_CLC, RED_PD_UP_AVL_IND, RD_PDUP_FC_AMT_CLC, AUTO_NONFORF_OPT, MIN_COVERAGE_UNITS, MAX_COVERAGE_UNITS, AWB_MAX_AGE, AWB_ADDL_PRM_IND, UNERND_PRM_DTH_IND, PW_APW_PPW_IND, DVD_MIN_COV_UNITS, SPCL_CLS_COMM_IND, RATNG_FCTR_COM_IND, FLAT_EXTRA_COM_IND, PAYR_DISAB_TRM_BAS, PAYR_DISAB_AGE_DUR, INSD_DISAB_TRM_AGE, COST_BASIS_ACCUM, DIS_TERM_CODE, GFT_EST_TAX_QT_IND, RPU_FLX_PUAR_USAGE, ETI_FLX_PUAR_USAGE, TAMRA_CALC_CODE, PLAN_RDR_TYPE_CODE, ABR_METHD, ABR_RD_LN_MTHD_IND, ABR_RED_CODE, ABR_MN_BSE_POL_AMT, ABR_MIN_BASE_AMT, ABR_MIN_BASE_PCT, ABR_MAX_BASE_AMT, ABR_MAX_BASE_PCT, FLEX_RIDER_IND, PUAR_IND, OTHR_TRM_RDR_IND, PUAR_NSP_RULE, NSP_DAYS, MIN_SCD_AN_PUR_PRM, MAX_SCD_AN_PUR_PRM, MIN_UNSCD_PUAR_PRM, MAX_UNSCD_PUAR_PRM, MAX_PUAR_PRM_YR, PW_CVG_AGE_RULE, PW_CHANGE_RULE, PAR_FLX_SEP_BL_IND, UNIT_PUR_RND_RULE, FLX_RDR_METHD_CD, MAX_FLX_RDR_TO_AGE, FLX_RDR_CON_PR_YRS, FLX_RDR_CON_AGE, FLX_RDR_PRM_PP, FLX_MIN_AMT_INSUFF, FLX_PUR_RID_IS_AGE, FLX_RDR_NY_ATT_AGE, FLX_RDR_MAX_SUB_PP, DEATH_UNPD_PRM_IND, TAMRA_PUAR_ANNV_CD, PUAR_1035_BILL_IND, FLEX_MINRISK_AMT_I, FLEX_PUAR_REINST_I, UPD_SUPPLEM_BILL, PW_PUAR_INDICATOR, ROP_ELIG_PREM_ACCUM_IND, ROP_CHARGE_METHOD )");
        sb.append(" VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ? )");
        return sb.toString();
    }
    
    public String prepareUpdateStmt(String schemaName)
    {
        StringBuffer sb = new StringBuffer();
        sb.append("UPDATE ").append(schemaName);
        sb.append(".TTB1T ");
        sb.append(" SET COMPANY_CODE = ?, TABLE_SUBSET = ?, EFFECTIVE_DATE = ?, AGE_DEF_CODE = ?, MIN_ISSUE_AGE = ?, MAX_ISSUE_AGE = ?, COV_TERM_BASIS = ?, COV_TERM_AGE = ?, PREM_TERM_BASIS = ?, PREM_TERM_AGE = ?, INS_CLASS_CODE = ?, RENEWAL_PERIOD = ?, LEVEL_PREM_IND = ?, DEATH_BEN_AMT_IND = ?, PARTICIPATION_IND = ?, CASH_VALUE_METHOD = ?, EXT_TRM_IN_AVL_IND = ?, EXT_TRM_IN_SPC_LIM = ?, EXT_TRM_IN_FLT_LIM = ?, EXT_TRM_IN_RATING = ?, EXT_TRM_IN_FCE_CLC = ?, RED_PD_UP_AVL_IND = ?, RD_PDUP_FC_AMT_CLC = ?, AUTO_NONFORF_OPT = ?, MIN_COVERAGE_UNITS = ?, MAX_COVERAGE_UNITS = ?, AWB_MAX_AGE = ?, AWB_ADDL_PRM_IND = ?, UNERND_PRM_DTH_IND = ?, PW_APW_PPW_IND = ?, DVD_MIN_COV_UNITS = ?, SPCL_CLS_COMM_IND = ?, RATNG_FCTR_COM_IND = ?, FLAT_EXTRA_COM_IND = ?, PAYR_DISAB_TRM_BAS = ?, PAYR_DISAB_AGE_DUR = ?, INSD_DISAB_TRM_AGE = ?, COST_BASIS_ACCUM = ?, DIS_TERM_CODE = ?, GFT_EST_TAX_QT_IND = ?, RPU_FLX_PUAR_USAGE = ?, ETI_FLX_PUAR_USAGE = ?, TAMRA_CALC_CODE = ?, PLAN_RDR_TYPE_CODE = ?, ABR_METHD = ?, ABR_RD_LN_MTHD_IND = ?, ABR_RED_CODE = ?, ABR_MN_BSE_POL_AMT = ?, ABR_MIN_BASE_AMT = ?, ABR_MIN_BASE_PCT = ?, ABR_MAX_BASE_AMT = ?, ABR_MAX_BASE_PCT = ?, FLEX_RIDER_IND = ?, PUAR_IND = ?, OTHR_TRM_RDR_IND = ?, PUAR_NSP_RULE = ?, NSP_DAYS = ?, MIN_SCD_AN_PUR_PRM = ?, MAX_SCD_AN_PUR_PRM = ?, MIN_UNSCD_PUAR_PRM = ?, MAX_UNSCD_PUAR_PRM = ?, MAX_PUAR_PRM_YR = ?, PW_CVG_AGE_RULE = ?, PW_CHANGE_RULE = ?, PAR_FLX_SEP_BL_IND = ?, UNIT_PUR_RND_RULE = ?, FLX_RDR_METHD_CD = ?, MAX_FLX_RDR_TO_AGE = ?, FLX_RDR_CON_PR_YRS = ?, FLX_RDR_CON_AGE = ?, FLX_RDR_PRM_PP = ?, FLX_MIN_AMT_INSUFF = ?, FLX_PUR_RID_IS_AGE = ?, FLX_RDR_NY_ATT_AGE = ?, FLX_RDR_MAX_SUB_PP = ?, DEATH_UNPD_PRM_IND = ?, TAMRA_PUAR_ANNV_CD = ?, PUAR_1035_BILL_IND = ?, FLEX_MINRISK_AMT_I = ?, FLEX_PUAR_REINST_I = ?, UPD_SUPPLEM_BILL = ?, PW_PUAR_INDICATOR = ?, ROP_ELIG_PREM_ACCUM_IND = ?, ROP_CHARGE_METHOD = ?");
        sb.append(" WHERE COMPANY_CODE = ? AND TABLE_SUBSET = ? AND EFFECTIVE_DATE = ?");
        return sb.toString();
    }
    
    public String prepareDeleteStmt(String schemaName)
    {
        StringBuffer sb = new StringBuffer();
        sb.append("DELETE FROM ").append(schemaName);
        sb.append(".TTB1T ");
        sb.append(" WHERE COMPANY_CODE = ? AND TABLE_SUBSET = ? AND EFFECTIVE_DATE = ?");
        return sb.toString();
    }
}
