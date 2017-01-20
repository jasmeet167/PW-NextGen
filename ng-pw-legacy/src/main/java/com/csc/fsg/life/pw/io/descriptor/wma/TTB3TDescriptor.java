package com.csc.fsg.life.pw.io.descriptor.wma;

import com.csc.fsg.life.pw.io.*;

public class TTB3TDescriptor
    extends TableDescriptor
{
    private static final String pagingSql = "SELECT COMPANY_CODE, TABLE_SUBSET, EFFECTIVE_DATE, PD_UP_ADD_DIV_OPT, DIV_PD_UP_ADD_IND, DIV_PD_UP_ADD_TBL, DIV_PD_UP_ADD_PCT, ACC_DIV_OPT, DECL_INT_RATE_TBL, PRM_PY_RED_DIV_OPT, CASH_DIV_OPT, COMB_DIV_OPT, APPLY_LOAN_OPT, APPLY_LOAN_INT, ACC_PAY_PLAN_OPT, APPLY_ANNUITY_OPT, ONE_YR_TRM_DIV_OPT, YR_TRM_DIV_AP_MTH, YR_TERM_INS_TBL, DECL_DIV_TBL, TERM_DIV_TBL, TERM_DIV_DUR, SEP_LN_DIV_PTR_TBL, LN_ADJUSTMENT_IND, LOAN_DIV_ADJ_PCT, PD_UP_ADD_TBL, PD_UP_ADD_PLAN_CD, YR_TRM_PLAN_CODE, PDUP_AD_TRM_DV_TBL, PDUP_AD_TRM_DV_DUR, CSH_DVD_OPT_PMT_DY, DIVID_ADJ_YEARS, ACM_DIVDS_LNABLE, PDUP_ADDNS_LNABLE, RPU_ACCUM_DIVD_USE, RPU_PD_UP_ADDN_USE, RPU_OYT_DIVD_USAGE, ETI_ACCUM_DIVD_USE, ETI_PD_UP_ADDN_USE, ETI_OYT_DIVD_USAGE, APL_ACCUM_DIVD_USE, APL_PUA_USAGE, APL_FLEX_USAGE_CD, APL_PUAR_USAGE_CD, LN_CAP_ACM_DVD_USE, LN_CAP_PUA_DVD_USE, LN_CAP_DVD_UNP_USE, LN_CAP_FLX_USE_CD, LN_CAP_PUAR_USE_CD, RPU_PR_ANL_DVD_USE, RPU_UNPRM_USE_CD, ETI_PR_ANL_DVD_USE, ETI_UNPRM_USE_CD, REDC_PRM_DVD_LNS_I, LN_DIV_DL_INT_RTE, MIN_DEC_AN_DVD_PER, FLX_TRM_DVD_OPTION, FLX_TRM_DVD_AP_MTH, DIVD_DEATH_BEN_IND, SEGMENTED_PUA_IND, POST_MORTEM_DIVDND FROM ";
    
    public void initialize()
    {
        setRowClass(TTB3TRow.class);
        setTableName("TTB3T");
        setTableId("TB3");
        super.initialize();
    }
    
    public void initializeColumnDescriptors()
    {
        super.initializeColumnDescriptors();
        addColumnDescriptor(new ColumnDescriptor(this,"getCompanyCode","setCompanyCode","COMPANY_CODE,1,1,3,0,true|,0,CHAR,1,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getTableSubset","setTableSubset","TABLE_SUBSET,1,2,16,0,true|,0,CHAR,1,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getEffectiveDate","setEffectiveDate","EFFECTIVE_DATE,91,3,10,0,true|,0,DATE,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getPdUpAddDivOpt","setPdUpAddDivOpt","PD_UP_ADD_DIV_OPT,1,4,1,0,false|,0,CHAR,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getDivPdUpAddInd","setDivPdUpAddInd","DIV_PD_UP_ADD_IND,1,5,1,0,false|,0,CHAR,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getDivPdUpAddTbl","setDivPdUpAddTbl","DIV_PD_UP_ADD_TBL,1,6,16,0,false|,0,CHAR,0,TB4,1,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getDivPdUpAddPct","setDivPdUpAddPct","DIV_PD_UP_ADD_PCT,3,7,5,2,false|,0,DOUBLE,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getAccDivOpt","setAccDivOpt","ACC_DIV_OPT,1,8,1,0,false|,0,CHAR,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getDeclIntRateTbl","setDeclIntRateTbl","DECL_INT_RATE_TBL,1,9,16,0,false|,0,CHAR,0,025,1,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getPrmPyRedDivOpt","setPrmPyRedDivOpt","PRM_PY_RED_DIV_OPT,1,10,1,0,false|,0,CHAR,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getCashDivOpt","setCashDivOpt","CASH_DIV_OPT,1,11,1,0,false|,0,CHAR,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getCombDivOpt","setCombDivOpt","COMB_DIV_OPT,1,12,1,0,false|,0,CHAR,2,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getApplyLoanOpt","setApplyLoanOpt","APPLY_LOAN_OPT,1,13,1,0,false|,0,CHAR,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getApplyLoanInt","setApplyLoanInt","APPLY_LOAN_INT,1,14,1,0,false|,0,CHAR,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getAccPayPlanOpt","setAccPayPlanOpt","ACC_PAY_PLAN_OPT,1,15,1,0,false|,0,CHAR,2,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getApplyAnnuityOpt","setApplyAnnuityOpt","APPLY_ANNUITY_OPT,1,16,1,0,false|,0,CHAR,2,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getOneYrTrmDivOpt","setOneYrTrmDivOpt","ONE_YR_TRM_DIV_OPT,1,17,1,0,false|,0,CHAR,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getYrTrmDivApMth","setYrTrmDivApMth","YR_TRM_DIV_AP_MTH,1,18,1,0,false|,0,CHAR,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getYrTermInsTbl","setYrTermInsTbl","YR_TERM_INS_TBL,1,19,16,0,false|,0,CHAR,0,T67,1,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getDeclDivTbl","setDeclDivTbl","DECL_DIV_TBL,1,20,16,0,false|,0,CHAR,0,TB4,2,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getTermDivTbl","setTermDivTbl","TERM_DIV_TBL,1,21,16,0,false|,0,CHAR,0,TB4,3,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getTermDivDur","setTermDivDur","TERM_DIV_DUR,3,22,3,0,false|,0,INTEGER,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getSepLnDivPtrTbl","setSepLnDivPtrTbl","SEP_LN_DIV_PTR_TBL,1,23,16,0,false|,0,CHAR,0,TB4,4,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getLnAdjustmentInd","setLnAdjustmentInd","LN_ADJUSTMENT_IND,1,24,1,0,false|,0,CHAR,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getLoanDivAdjPct","setLoanDivAdjPct","LOAN_DIV_ADJ_PCT,3,25,5,2,false|,0,DOUBLE,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getPdUpAddTbl","setPdUpAddTbl","PD_UP_ADD_TBL,1,26,16,0,false|,0,CHAR,0,T67,2,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getPdUpAddPlanCd","setPdUpAddPlanCd","PD_UP_ADD_PLAN_CD,1,27,6,0,false|,0,CHAR,4,null,4,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getYrTrmPlanCode","setYrTrmPlanCode","YR_TRM_PLAN_CODE,1,28,6,0,false|,0,CHAR,4,null,4,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getPdupAdTrmDvTbl","setPdupAdTrmDvTbl","PDUP_AD_TRM_DV_TBL,1,29,16,0,false|,0,CHAR,0,TB4,5,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getPdupAdTrmDvDur","setPdupAdTrmDvDur","PDUP_AD_TRM_DV_DUR,3,30,3,0,false|,0,INTEGER,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getCshDvdOptPmtDy","setCshDvdOptPmtDy","CSH_DVD_OPT_PMT_DY,3,31,3,0,false|,0,INTEGER,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getDividAdjYears","setDividAdjYears","DIVID_ADJ_YEARS,3,32,2,0,false|,0,INTEGER,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getAcmDivdsLnable","setAcmDivdsLnable","ACM_DIVDS_LNABLE,1,33,1,0,false|,0,CHAR,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getPdupAddnsLnable","setPdupAddnsLnable","PDUP_ADDNS_LNABLE,1,34,1,0,false|,0,CHAR,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getRpuAccumDivdUse","setRpuAccumDivdUse","RPU_ACCUM_DIVD_USE,1,35,1,0,false|,0,CHAR,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getRpuPdUpAddnUse","setRpuPdUpAddnUse","RPU_PD_UP_ADDN_USE,1,36,1,0,false|,0,CHAR,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getRpuOytDivdUsage","setRpuOytDivdUsage","RPU_OYT_DIVD_USAGE,1,37,1,0,false|,0,CHAR,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getEtiAccumDivdUse","setEtiAccumDivdUse","ETI_ACCUM_DIVD_USE,1,38,1,0,false|,0,CHAR,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getEtiPdUpAddnUse","setEtiPdUpAddnUse","ETI_PD_UP_ADDN_USE,1,39,1,0,false|,0,CHAR,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getEtiOytDivdUsage","setEtiOytDivdUsage","ETI_OYT_DIVD_USAGE,1,40,1,0,false|,0,CHAR,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getAplAccumDivdUse","setAplAccumDivdUse","APL_ACCUM_DIVD_USE,1,41,1,0,false|,0,CHAR,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getAplPuaUsage","setAplPuaUsage","APL_PUA_USAGE,1,42,1,0,false|,0,CHAR,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getAplFlexUsageCd","setAplFlexUsageCd","APL_FLEX_USAGE_CD,1,43,1,0,false|,0,CHAR,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getAplPuarUsageCd","setAplPuarUsageCd","APL_PUAR_USAGE_CD,1,44,1,0,false|,0,CHAR,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getLnCapAcmDvdUse","setLnCapAcmDvdUse","LN_CAP_ACM_DVD_USE,1,45,1,0,false|,0,CHAR,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getLnCapPuaDvdUse","setLnCapPuaDvdUse","LN_CAP_PUA_DVD_USE,1,46,1,0,false|,0,CHAR,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getLnCapDvdUnpUse","setLnCapDvdUnpUse","LN_CAP_DVD_UNP_USE,1,47,1,0,false|,0,CHAR,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getLnCapFlxUseCd","setLnCapFlxUseCd","LN_CAP_FLX_USE_CD,1,48,1,0,false|,0,CHAR,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getLnCapPuarUseCd","setLnCapPuarUseCd","LN_CAP_PUAR_USE_CD,1,49,1,0,false|,0,CHAR,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getRpuPrAnlDvdUse","setRpuPrAnlDvdUse","RPU_PR_ANL_DVD_USE,1,50,1,0,false|,0,CHAR,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getRpuUnprmUseCd","setRpuUnprmUseCd","RPU_UNPRM_USE_CD,1,51,1,0,false|,0,CHAR,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getEtiPrAnlDvdUse","setEtiPrAnlDvdUse","ETI_PR_ANL_DVD_USE,1,52,1,0,false|,0,CHAR,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getEtiUnprmUseCd","setEtiUnprmUseCd","ETI_UNPRM_USE_CD,1,53,1,0,false|,0,CHAR,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getRedcPrmDvdLnsI","setRedcPrmDvdLnsI","REDC_PRM_DVD_LNS_I,1,54,1,0,false|,0,CHAR,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getLnDivDlIntRte","setLnDivDlIntRte","LN_DIV_DL_INT_RTE,1,55,16,0,false|,0,CHAR,0,025,2,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getMinDecAnDvdPer","setMinDecAnDvdPer","MIN_DEC_AN_DVD_PER,3,56,11,2,false|,0,DOUBLE,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getFlxTrmDvdOption","setFlxTrmDvdOption","FLX_TRM_DVD_OPTION,1,57,1,0,false|,0,CHAR,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getFlxTrmDvdApMth","setFlxTrmDvdApMth","FLX_TRM_DVD_AP_MTH,1,58,1,0,false|,0,CHAR,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getDivdDeathBenInd","setDivdDeathBenInd","DIVD_DEATH_BEN_IND,1,59,1,0,false|,0,CHAR,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getSegmentedPuaInd","setSegmentedPuaInd","SEGMENTED_PUA_IND,1,60,1,0,false|,0,CHAR,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getPostMortemDivdnd","setPostMortemDivdnd","POST_MORTEM_DIVDND,1,61,1,0,false|,0,CHAR,0,null,null,null,null,null"));
    }
    
    public String getPagingSQL(String schemaName,boolean isSubsetMode,boolean isNext, boolean locator)
    {
        String pagingWhere = null;
        String order = null;
        if (isNext) {
            if (isSubsetMode)
                if (locator)
                    pagingWhere = ".TTB3T WHERE (COMPANY_CODE = :company_code) AND (TABLE_SUBSET = :table_subset) AND ((EFFECTIVE_DATE > :effective_date) OR (EFFECTIVE_DATE = :effective_date)) ";
                else 
                    pagingWhere = ".TTB3T WHERE (COMPANY_CODE = :company_code) AND (TABLE_SUBSET = :table_subset) AND ((EFFECTIVE_DATE > :effective_date)) ";
            else
                if (locator)
                    pagingWhere = ".TTB3T WHERE (COMPANY_CODE = :company_code) AND ((TABLE_SUBSET > :table_subset) OR (TABLE_SUBSET = :table_subset AND EFFECTIVE_DATE > :effective_date) OR (TABLE_SUBSET = :table_subset AND EFFECTIVE_DATE = :effective_date)) ";
                else
                    pagingWhere = ".TTB3T WHERE (COMPANY_CODE = :company_code) AND ((TABLE_SUBSET > :table_subset) OR (TABLE_SUBSET = :table_subset AND EFFECTIVE_DATE > :effective_date)) ";
            order = " ORDER BY COMPANY_CODE, TABLE_SUBSET, EFFECTIVE_DATE";
        }
        else {
            if (isSubsetMode)
                pagingWhere = ".TTB3T WHERE (COMPANY_CODE = :company_code) AND (TABLE_SUBSET = :table_subset) AND ((EFFECTIVE_DATE < :effective_date)) ";
            else
                pagingWhere = ".TTB3T WHERE (COMPANY_CODE = :company_code) AND ((TABLE_SUBSET < :table_subset) OR (TABLE_SUBSET = :table_subset AND EFFECTIVE_DATE < :effective_date)) ";
            order = " ORDER BY COMPANY_CODE DESC, TABLE_SUBSET DESC, EFFECTIVE_DATE DESC";
        }
        return pagingSql + schemaName + pagingWhere + order;
    }
    
    public String prepareInsertStmt(String schemaName) {
        StringBuffer sb = new StringBuffer();
        sb.append("INSERT INTO ").append(schemaName);
        sb.append(".TTB3T ( ");
        sb.append("COMPANY_CODE, TABLE_SUBSET, EFFECTIVE_DATE, PD_UP_ADD_DIV_OPT, DIV_PD_UP_ADD_IND, DIV_PD_UP_ADD_TBL, DIV_PD_UP_ADD_PCT, ACC_DIV_OPT, DECL_INT_RATE_TBL, PRM_PY_RED_DIV_OPT, CASH_DIV_OPT, COMB_DIV_OPT, APPLY_LOAN_OPT, APPLY_LOAN_INT, ACC_PAY_PLAN_OPT, APPLY_ANNUITY_OPT, ONE_YR_TRM_DIV_OPT, YR_TRM_DIV_AP_MTH, YR_TERM_INS_TBL, DECL_DIV_TBL, TERM_DIV_TBL, TERM_DIV_DUR, SEP_LN_DIV_PTR_TBL, LN_ADJUSTMENT_IND, LOAN_DIV_ADJ_PCT, PD_UP_ADD_TBL, PD_UP_ADD_PLAN_CD, YR_TRM_PLAN_CODE, PDUP_AD_TRM_DV_TBL, PDUP_AD_TRM_DV_DUR, CSH_DVD_OPT_PMT_DY, DIVID_ADJ_YEARS, ACM_DIVDS_LNABLE, PDUP_ADDNS_LNABLE, RPU_ACCUM_DIVD_USE, RPU_PD_UP_ADDN_USE, RPU_OYT_DIVD_USAGE, ETI_ACCUM_DIVD_USE, ETI_PD_UP_ADDN_USE, ETI_OYT_DIVD_USAGE, APL_ACCUM_DIVD_USE, APL_PUA_USAGE, APL_FLEX_USAGE_CD, APL_PUAR_USAGE_CD, LN_CAP_ACM_DVD_USE, LN_CAP_PUA_DVD_USE, LN_CAP_DVD_UNP_USE, LN_CAP_FLX_USE_CD, LN_CAP_PUAR_USE_CD, RPU_PR_ANL_DVD_USE, RPU_UNPRM_USE_CD, ETI_PR_ANL_DVD_USE, ETI_UNPRM_USE_CD, REDC_PRM_DVD_LNS_I, LN_DIV_DL_INT_RTE, MIN_DEC_AN_DVD_PER, FLX_TRM_DVD_OPTION, FLX_TRM_DVD_AP_MTH, DIVD_DEATH_BEN_IND, SEGMENTED_PUA_IND, POST_MORTEM_DIVDND )");
        sb.append(" VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ? )");
        return sb.toString();
    }
    
    public String prepareUpdateStmt(String schemaName)
    {
        StringBuffer sb = new StringBuffer();
        sb.append("UPDATE ").append(schemaName);
        sb.append(".TTB3T ");
        sb.append(" SET COMPANY_CODE = ?, TABLE_SUBSET = ?, EFFECTIVE_DATE = ?, PD_UP_ADD_DIV_OPT = ?, DIV_PD_UP_ADD_IND = ?, DIV_PD_UP_ADD_TBL = ?, DIV_PD_UP_ADD_PCT = ?, ACC_DIV_OPT = ?, DECL_INT_RATE_TBL = ?, PRM_PY_RED_DIV_OPT = ?, CASH_DIV_OPT = ?, COMB_DIV_OPT = ?, APPLY_LOAN_OPT = ?, APPLY_LOAN_INT = ?, ACC_PAY_PLAN_OPT = ?, APPLY_ANNUITY_OPT = ?, ONE_YR_TRM_DIV_OPT = ?, YR_TRM_DIV_AP_MTH = ?, YR_TERM_INS_TBL = ?, DECL_DIV_TBL = ?, TERM_DIV_TBL = ?, TERM_DIV_DUR = ?, SEP_LN_DIV_PTR_TBL = ?, LN_ADJUSTMENT_IND = ?, LOAN_DIV_ADJ_PCT = ?, PD_UP_ADD_TBL = ?, PD_UP_ADD_PLAN_CD = ?, YR_TRM_PLAN_CODE = ?, PDUP_AD_TRM_DV_TBL = ?, PDUP_AD_TRM_DV_DUR = ?, CSH_DVD_OPT_PMT_DY = ?, DIVID_ADJ_YEARS = ?, ACM_DIVDS_LNABLE = ?, PDUP_ADDNS_LNABLE = ?, RPU_ACCUM_DIVD_USE = ?, RPU_PD_UP_ADDN_USE = ?, RPU_OYT_DIVD_USAGE = ?, ETI_ACCUM_DIVD_USE = ?, ETI_PD_UP_ADDN_USE = ?, ETI_OYT_DIVD_USAGE = ?, APL_ACCUM_DIVD_USE = ?, APL_PUA_USAGE = ?, APL_FLEX_USAGE_CD = ?, APL_PUAR_USAGE_CD = ?, LN_CAP_ACM_DVD_USE = ?, LN_CAP_PUA_DVD_USE = ?, LN_CAP_DVD_UNP_USE = ?, LN_CAP_FLX_USE_CD = ?, LN_CAP_PUAR_USE_CD = ?, RPU_PR_ANL_DVD_USE = ?, RPU_UNPRM_USE_CD = ?, ETI_PR_ANL_DVD_USE = ?, ETI_UNPRM_USE_CD = ?, REDC_PRM_DVD_LNS_I = ?, LN_DIV_DL_INT_RTE = ?, MIN_DEC_AN_DVD_PER = ?, FLX_TRM_DVD_OPTION = ?, FLX_TRM_DVD_AP_MTH = ?, DIVD_DEATH_BEN_IND = ?, SEGMENTED_PUA_IND = ?, POST_MORTEM_DIVDND = ?");
        sb.append(" WHERE COMPANY_CODE = ? AND TABLE_SUBSET = ? AND EFFECTIVE_DATE = ?");
        return sb.toString();
    }
    
    public String prepareDeleteStmt(String schemaName)
    {
        StringBuffer sb = new StringBuffer();
        sb.append("DELETE FROM ").append(schemaName);
        sb.append(".TTB3T ");
        sb.append(" WHERE COMPANY_CODE = ? AND TABLE_SUBSET = ? AND EFFECTIVE_DATE = ?");
        return sb.toString();
    }
}
