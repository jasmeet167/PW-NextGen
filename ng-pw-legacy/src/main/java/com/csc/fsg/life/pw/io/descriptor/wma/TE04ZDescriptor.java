package com.csc.fsg.life.pw.io.descriptor.wma;

import com.csc.fsg.life.pw.io.*;

public class TE04ZDescriptor
    extends TableDescriptor
{
    private static final String pagingSql = "SELECT COMPANY_CODE, TABLE_SUBSET, STATE_CODE, EFFECTIVE_DATE, PLAN_DISCONTI_DT, LINE_OF_BUSINESS, WPD_INDICATOR, JNT_COVERAGE_IND, GPO_INDICATOR, COLA_INDICATOR, OPT_BENEFIT_LBL_01, OPT_BENEFIT_LBL_02, OPT_BENEFIT_IND_01, OPT_BENEFIT_IND_02, ADD_INS_INDICATOR, FAMILY_INDICATOR, CHILD_INDICATOR, OPT_RIDER_LBL_01, OPT_RIDER_LBL_02, OPT_RIDER_IND_01, OPT_RIDER_IND_02, EXCLUSION_RDR_01, EXCLUSION_RDR_02, EXCLUSION_RDR_03, EXCLUSION_RDR_04, EXCLUSION_RDR_05, EXCLUSION_RDR_06, EXCLUSION_RDR_07, EXCLUSION_RDR_08, EXCLUSION_RDR_09, EXCLUSION_RDR_10, EXCLUSION_RDR_11, EXCLUSION_RDR_12, EXCLUSION_RDR_13, EXCLUSION_RDR_14, EXCLUSION_RDR_15, EXCLUSION_RDR_16, EXCLUSION_RDR_17, EXCLUSION_RDR_18, EXCLUSION_RDR_19, EXCLUSION_RDR_20, PLAN_MIN_AMOUNT, PLAN_MAX_AMOUNT, JET_MIN_AMOUNT, JET_MAX_AMOUNT, PLAN_MIN_AGE, PLAN_MAXIMUM_AGE, JET_MINIMUM_AGE, JET_MAXIMUM_AGE, MAXIMUM_RATING, MAX_FLAT_EXTRA, SPOUSE_RDR_IND, PR_INS_RDR_IND, EOH_INCLUSION_IND, TIMEFRAME_INCLSON FROM ";
    
    public void initialize()
    {
        setRowClass(TE04ZRow.class);
        setTableName("TE04Z");
        setTableId("E04");
        super.initialize();
    }
    
    public void initializeColumnDescriptors()
    {
        super.initializeColumnDescriptors();
        addColumnDescriptor(new ColumnDescriptor(this,"getCompanyCode","setCompanyCode","COMPANY_CODE,1,1,3,0,true|,0,CHAR,1,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getTableSubset","setTableSubset","TABLE_SUBSET,1,2,16,0,true|,0,CHAR,1,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getStateCode","setStateCode","STATE_CODE,1,3,3,0,true|,0,CHAR,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getEffectiveDate","setEffectiveDate","EFFECTIVE_DATE,91,4,10,0,true|,0,DATE,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getPlanDiscontiDt","setPlanDiscontiDt","PLAN_DISCONTI_DT,91,5,10,0,false|,0,DATE,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getLineOfBusiness","setLineOfBusiness","LINE_OF_BUSINESS,1,6,3,0,false|,0,CHAR,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getWpdIndicator","setWpdIndicator","WPD_INDICATOR,1,7,1,0,false|,0,CHAR,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getJntCoverageInd","setJntCoverageInd","JNT_COVERAGE_IND,1,8,1,0,false|,0,CHAR,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getGpoIndicator","setGpoIndicator","GPO_INDICATOR,1,9,1,0,false|,0,CHAR,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getColaIndicator","setColaIndicator","COLA_INDICATOR,1,10,1,0,false|,0,CHAR,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getOptBenefitLbl01","setOptBenefitLbl01","OPT_BENEFIT_LBL_01,1,11,5,0,false|,0,CHAR,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getOptBenefitLbl02","setOptBenefitLbl02","OPT_BENEFIT_LBL_02,1,12,5,0,false|,0,CHAR,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getOptBenefitInd01","setOptBenefitInd01","OPT_BENEFIT_IND_01,1,13,1,0,false|,0,CHAR,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getOptBenefitInd02","setOptBenefitInd02","OPT_BENEFIT_IND_02,1,14,1,0,false|,0,CHAR,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getAddInsIndicator","setAddInsIndicator","ADD_INS_INDICATOR,1,15,1,0,false|,0,CHAR,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getFamilyIndicator","setFamilyIndicator","FAMILY_INDICATOR,1,16,1,0,false|,0,CHAR,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getChildIndicator","setChildIndicator","CHILD_INDICATOR,1,17,1,0,false|,0,CHAR,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getOptRiderLbl01","setOptRiderLbl01","OPT_RIDER_LBL_01,1,18,5,0,false|,0,CHAR,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getOptRiderLbl02","setOptRiderLbl02","OPT_RIDER_LBL_02,1,19,5,0,false|,0,CHAR,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getOptRiderInd01","setOptRiderInd01","OPT_RIDER_IND_01,1,20,1,0,false|,0,CHAR,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getOptRiderInd02","setOptRiderInd02","OPT_RIDER_IND_02,1,21,1,0,false|,0,CHAR,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getExclusionRdr01","setExclusionRdr01","EXCLUSION_RDR_01,1,22,4,0,false|,0,CHAR,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getExclusionRdr02","setExclusionRdr02","EXCLUSION_RDR_02,1,23,4,0,false|,0,CHAR,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getExclusionRdr03","setExclusionRdr03","EXCLUSION_RDR_03,1,24,4,0,false|,0,CHAR,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getExclusionRdr04","setExclusionRdr04","EXCLUSION_RDR_04,1,25,4,0,false|,0,CHAR,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getExclusionRdr05","setExclusionRdr05","EXCLUSION_RDR_05,1,26,4,0,false|,0,CHAR,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getExclusionRdr06","setExclusionRdr06","EXCLUSION_RDR_06,1,27,4,0,false|,0,CHAR,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getExclusionRdr07","setExclusionRdr07","EXCLUSION_RDR_07,1,28,4,0,false|,0,CHAR,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getExclusionRdr08","setExclusionRdr08","EXCLUSION_RDR_08,1,29,4,0,false|,0,CHAR,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getExclusionRdr09","setExclusionRdr09","EXCLUSION_RDR_09,1,30,4,0,false|,0,CHAR,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getExclusionRdr10","setExclusionRdr10","EXCLUSION_RDR_10,1,31,4,0,false|,0,CHAR,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getExclusionRdr11","setExclusionRdr11","EXCLUSION_RDR_11,1,32,4,0,false|,0,CHAR,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getExclusionRdr12","setExclusionRdr12","EXCLUSION_RDR_12,1,33,4,0,false|,0,CHAR,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getExclusionRdr13","setExclusionRdr13","EXCLUSION_RDR_13,1,34,4,0,false|,0,CHAR,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getExclusionRdr14","setExclusionRdr14","EXCLUSION_RDR_14,1,35,4,0,false|,0,CHAR,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getExclusionRdr15","setExclusionRdr15","EXCLUSION_RDR_15,1,36,4,0,false|,0,CHAR,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getExclusionRdr16","setExclusionRdr16","EXCLUSION_RDR_16,1,37,4,0,false|,0,CHAR,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getExclusionRdr17","setExclusionRdr17","EXCLUSION_RDR_17,1,38,4,0,false|,0,CHAR,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getExclusionRdr18","setExclusionRdr18","EXCLUSION_RDR_18,1,39,4,0,false|,0,CHAR,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getExclusionRdr19","setExclusionRdr19","EXCLUSION_RDR_19,1,40,4,0,false|,0,CHAR,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getExclusionRdr20","setExclusionRdr20","EXCLUSION_RDR_20,1,41,4,0,false|,0,CHAR,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getPlanMinAmount","setPlanMinAmount","PLAN_MIN_AMOUNT,3,42,9,0,false|,0,INTEGER,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getPlanMaxAmount","setPlanMaxAmount","PLAN_MAX_AMOUNT,3,43,9,0,false|,0,INTEGER,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getJetMinAmount","setJetMinAmount","JET_MIN_AMOUNT,3,44,9,0,false|,0,INTEGER,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getJetMaxAmount","setJetMaxAmount","JET_MAX_AMOUNT,3,45,9,0,false|,0,INTEGER,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getPlanMinAge","setPlanMinAge","PLAN_MIN_AGE,3,46,3,0,false|,0,INTEGER,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getPlanMaximumAge","setPlanMaximumAge","PLAN_MAXIMUM_AGE,3,47,3,0,false|,0,INTEGER,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getJetMinimumAge","setJetMinimumAge","JET_MINIMUM_AGE,3,48,3,0,false|,0,INTEGER,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getJetMaximumAge","setJetMaximumAge","JET_MAXIMUM_AGE,3,49,3,0,false|,0,INTEGER,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getMaximumRating","setMaximumRating","MAXIMUM_RATING,3,50,4,1,false|,0,DOUBLE,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getMaxFlatExtra","setMaxFlatExtra","MAX_FLAT_EXTRA,3,51,5,2,false|,0,DOUBLE,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getSpouseRdrInd","setSpouseRdrInd","SPOUSE_RDR_IND,1,52,1,0,false|,0,CHAR,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getPrInsRdrInd","setPrInsRdrInd","PR_INS_RDR_IND,1,53,1,0,false|,0,CHAR,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getEohInclusionInd","setEohInclusionInd","EOH_INCLUSION_IND,1,54,1,0,false|,0,CHAR,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getTimeframeInclson","setTimeframeInclson","TIMEFRAME_INCLSON,3,55,3,0,false|,0,INTEGER,0,null,null,null,null,null"));
    }
    
    public String getPagingSQL(String schemaName,boolean isSubsetMode,boolean isNext, boolean locator)
    {
        String pagingWhere = null;
        String order = null;
        if (isNext) {
            if (isSubsetMode)
                if (locator)
                    pagingWhere = ".TE04Z WHERE (COMPANY_CODE = :company_code) AND (TABLE_SUBSET = :table_subset) AND ((STATE_CODE > :state_code) OR (STATE_CODE = :state_code AND EFFECTIVE_DATE > :effective_date) OR (STATE_CODE = :state_code AND EFFECTIVE_DATE = :effective_date)) ";
                else 
                    pagingWhere = ".TE04Z WHERE (COMPANY_CODE = :company_code) AND (TABLE_SUBSET = :table_subset) AND ((STATE_CODE > :state_code) OR (STATE_CODE = :state_code AND EFFECTIVE_DATE > :effective_date)) ";
            else
                if (locator)
                    pagingWhere = ".TE04Z WHERE (COMPANY_CODE = :company_code) AND ((TABLE_SUBSET > :table_subset) OR (TABLE_SUBSET = :table_subset AND STATE_CODE > :state_code) OR (TABLE_SUBSET = :table_subset AND STATE_CODE = :state_code AND EFFECTIVE_DATE > :effective_date) OR (TABLE_SUBSET = :table_subset AND STATE_CODE = :state_code AND EFFECTIVE_DATE = :effective_date)) ";
                else
                    pagingWhere = ".TE04Z WHERE (COMPANY_CODE = :company_code) AND ((TABLE_SUBSET > :table_subset) OR (TABLE_SUBSET = :table_subset AND STATE_CODE > :state_code) OR (TABLE_SUBSET = :table_subset AND STATE_CODE = :state_code AND EFFECTIVE_DATE > :effective_date)) ";
            order = " ORDER BY COMPANY_CODE, TABLE_SUBSET, STATE_CODE, EFFECTIVE_DATE";
        }
        else {
            if (isSubsetMode)
                pagingWhere = ".TE04Z WHERE (COMPANY_CODE = :company_code) AND (TABLE_SUBSET = :table_subset) AND ((STATE_CODE < :state_code) OR (STATE_CODE = :state_code AND EFFECTIVE_DATE < :effective_date)) ";
            else
                pagingWhere = ".TE04Z WHERE (COMPANY_CODE = :company_code) AND ((TABLE_SUBSET < :table_subset) OR (TABLE_SUBSET = :table_subset AND STATE_CODE < :state_code) OR (TABLE_SUBSET = :table_subset AND STATE_CODE = :state_code AND EFFECTIVE_DATE < :effective_date)) ";
            order = " ORDER BY COMPANY_CODE DESC, TABLE_SUBSET DESC, STATE_CODE DESC, EFFECTIVE_DATE DESC";
        }
        return pagingSql + schemaName + pagingWhere + order;
    }
    
    public String prepareInsertStmt(String schemaName) {
        StringBuffer sb = new StringBuffer();
        sb.append("INSERT INTO ").append(schemaName);
        sb.append(".TE04Z ( ");
        sb.append("COMPANY_CODE, TABLE_SUBSET, STATE_CODE, EFFECTIVE_DATE, PLAN_DISCONTI_DT, LINE_OF_BUSINESS, WPD_INDICATOR, JNT_COVERAGE_IND, GPO_INDICATOR, COLA_INDICATOR, OPT_BENEFIT_LBL_01, OPT_BENEFIT_LBL_02, OPT_BENEFIT_IND_01, OPT_BENEFIT_IND_02, ADD_INS_INDICATOR, FAMILY_INDICATOR, CHILD_INDICATOR, OPT_RIDER_LBL_01, OPT_RIDER_LBL_02, OPT_RIDER_IND_01, OPT_RIDER_IND_02, EXCLUSION_RDR_01, EXCLUSION_RDR_02, EXCLUSION_RDR_03, EXCLUSION_RDR_04, EXCLUSION_RDR_05, EXCLUSION_RDR_06, EXCLUSION_RDR_07, EXCLUSION_RDR_08, EXCLUSION_RDR_09, EXCLUSION_RDR_10, EXCLUSION_RDR_11, EXCLUSION_RDR_12, EXCLUSION_RDR_13, EXCLUSION_RDR_14, EXCLUSION_RDR_15, EXCLUSION_RDR_16, EXCLUSION_RDR_17, EXCLUSION_RDR_18, EXCLUSION_RDR_19, EXCLUSION_RDR_20, PLAN_MIN_AMOUNT, PLAN_MAX_AMOUNT, JET_MIN_AMOUNT, JET_MAX_AMOUNT, PLAN_MIN_AGE, PLAN_MAXIMUM_AGE, JET_MINIMUM_AGE, JET_MAXIMUM_AGE, MAXIMUM_RATING, MAX_FLAT_EXTRA, SPOUSE_RDR_IND, PR_INS_RDR_IND, EOH_INCLUSION_IND, TIMEFRAME_INCLSON )");
        sb.append(" VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ? )");
        return sb.toString();
    }
    
    public String prepareUpdateStmt(String schemaName)
    {
        StringBuffer sb = new StringBuffer();
        sb.append("UPDATE ").append(schemaName);
        sb.append(".TE04Z ");
        sb.append(" SET COMPANY_CODE = ?, TABLE_SUBSET = ?, STATE_CODE = ?, EFFECTIVE_DATE = ?, PLAN_DISCONTI_DT = ?, LINE_OF_BUSINESS = ?, WPD_INDICATOR = ?, JNT_COVERAGE_IND = ?, GPO_INDICATOR = ?, COLA_INDICATOR = ?, OPT_BENEFIT_LBL_01 = ?, OPT_BENEFIT_LBL_02 = ?, OPT_BENEFIT_IND_01 = ?, OPT_BENEFIT_IND_02 = ?, ADD_INS_INDICATOR = ?, FAMILY_INDICATOR = ?, CHILD_INDICATOR = ?, OPT_RIDER_LBL_01 = ?, OPT_RIDER_LBL_02 = ?, OPT_RIDER_IND_01 = ?, OPT_RIDER_IND_02 = ?, EXCLUSION_RDR_01 = ?, EXCLUSION_RDR_02 = ?, EXCLUSION_RDR_03 = ?, EXCLUSION_RDR_04 = ?, EXCLUSION_RDR_05 = ?, EXCLUSION_RDR_06 = ?, EXCLUSION_RDR_07 = ?, EXCLUSION_RDR_08 = ?, EXCLUSION_RDR_09 = ?, EXCLUSION_RDR_10 = ?, EXCLUSION_RDR_11 = ?, EXCLUSION_RDR_12 = ?, EXCLUSION_RDR_13 = ?, EXCLUSION_RDR_14 = ?, EXCLUSION_RDR_15 = ?, EXCLUSION_RDR_16 = ?, EXCLUSION_RDR_17 = ?, EXCLUSION_RDR_18 = ?, EXCLUSION_RDR_19 = ?, EXCLUSION_RDR_20 = ?, PLAN_MIN_AMOUNT = ?, PLAN_MAX_AMOUNT = ?, JET_MIN_AMOUNT = ?, JET_MAX_AMOUNT = ?, PLAN_MIN_AGE = ?, PLAN_MAXIMUM_AGE = ?, JET_MINIMUM_AGE = ?, JET_MAXIMUM_AGE = ?, MAXIMUM_RATING = ?, MAX_FLAT_EXTRA = ?, SPOUSE_RDR_IND = ?, PR_INS_RDR_IND = ?, EOH_INCLUSION_IND = ?, TIMEFRAME_INCLSON = ?");
        sb.append(" WHERE COMPANY_CODE = ? AND TABLE_SUBSET = ? AND STATE_CODE = ? AND EFFECTIVE_DATE = ?");
        return sb.toString();
    }
    
    public String prepareDeleteStmt(String schemaName)
    {
        StringBuffer sb = new StringBuffer();
        sb.append("DELETE FROM ").append(schemaName);
        sb.append(".TE04Z ");
        sb.append(" WHERE COMPANY_CODE = ? AND TABLE_SUBSET = ? AND STATE_CODE = ? AND EFFECTIVE_DATE = ?");
        return sb.toString();
    }
}
