package com.csc.fsg.life.pw.io.descriptor.wma;

import com.csc.fsg.life.pw.io.*;

public class T012XDescriptor
    extends TableDescriptor
{
    private static final String pagingSql = "SELECT COMPANY_CODE, PRODUCT_PREFIX, TABLE_SUBSET, EFFECTIVE_DATE, SPECIAL_CLASS_CD, PREMIUM_CLASS_CD, MAX_COVG_UNITS, AMT_AT_RISK, AGE_USAGE_CODE, CALC_RULE, MALE_ULT_RATE_TBL, FMALE_ULT_RATE_TBL, MALE_SEL_RATE_TBL, MALE_SEL_AGE_ADJ, FMALE_SEL_AGE_ADJ, FMALE_SEL_RATE_TBL, MALE_ANN_PREMNO, FMALE_ANN_PREMNO, FMALE_SETBACK, RATING_FACTOR, MGUAR_MAXANN_PPU, FGUAR_MAXANN_PPU, CUNISEX_ANN_PPU, GUNISEX_ANN_PPU, CMALE_FEM_ANNPPU, GMALE_FEM_ANNPPU, MALE_ULT_GRN_RATE, FMALE_ULT_GRN_RATE, MALE_SEL_GRN_RATE, FMALE_SEL_GRN_RATE, SELECT_PERIOD, MALE_ULT_GRNAGEAD, FMALE_ULT_GRNAGEAD, ULT_RATE_FACTOR, SEL_RATE_FACTOR, AMT_RISK_BAND_CD, MALE_ULT_AGE_ADJ, FMALE_ULT_AGE_ADJ, MALE_SELGRNAGEADJ, FMALE_SELGRNAGEADJ FROM ";
    
    public void initialize()
    {
        setRowClass(T012XRow.class);
        setTableName("T012X");
        setTableId("012");
        super.initialize();
    }
    
    public void initializeColumnDescriptors()
    {
        super.initializeColumnDescriptors();
        addColumnDescriptor(new ColumnDescriptor(this,"getCompanyCode","setCompanyCode","COMPANY_CODE,1,1,3,0,true|T,0,CHAR,1,null,null,null,null,null|U,0,CHAR,1,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getProductPrefix","setProductPrefix","PRODUCT_PREFIX,1,2,1,0,true|T,0,CHAR,1,null,null,null,null,null|U,0,CHAR,1,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getTableSubset","setTableSubset","TABLE_SUBSET,1,3,16,0,true|T,0,CHAR,1,null,null,null,null,null|U,0,CHAR,1,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getEffectiveDate","setEffectiveDate","EFFECTIVE_DATE,91,4,10,0,true|T,0,DATE,0,null,null,null,null,null|U,0,DATE,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getSpecialClassCd","setSpecialClassCd","SPECIAL_CLASS_CD,1,5,2,0,true|T,0,CHAR,0,null,null,null,null,null|U,0,CHAR,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getPremiumClassCd","setPremiumClassCd","PREMIUM_CLASS_CD,1,6,1,0,true|T,0,CHAR,0,null,null,null,null,null|U,0,CHAR,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getMaxCovgUnits","setMaxCovgUnits","MAX_COVG_UNITS,3,7,11,5,true|T,0,DOUBLE,0,null,null,null,null,null|U,0,DOUBLE,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getAmtAtRisk","setAmtAtRisk","AMT_AT_RISK,3,8,11,2,true|T,0,DOUBLE,2,null,null,null,null,null|U,0,DOUBLE,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getAgeUsageCode","setAgeUsageCode","AGE_USAGE_CODE,1,9,1,0,false|T,0,CHAR,0,null,null,null,null,null|U,0,CHAR,2,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getCalcRule","setCalcRule","CALC_RULE,1,10,1,0,false|T,0,CHAR,0,null,null,null,null,null|U,0,CHAR,2,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getMaleUltRateTbl","setMaleUltRateTbl","MALE_ULT_RATE_TBL,1,11,16,0,false|T,0,CHAR,2,null,null,ML,null,null|U,0,CHAR,0,029,1,ML,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getFmaleUltRateTbl","setFmaleUltRateTbl","FMALE_ULT_RATE_TBL,1,12,16,0,false|T,0,CHAR,2,null,null,FM,null,null|U,0,CHAR,0,029,2,FM,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getMaleSelRateTbl","setMaleSelRateTbl","MALE_SEL_RATE_TBL,1,13,16,0,false|T,0,CHAR,2,null,null,ML,null,null|U,0,CHAR,0,030,1,ML,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getMaleSelAgeAdj","setMaleSelAgeAdj","MALE_SEL_AGE_ADJ,3,14,2,0,false|T,0,INTEGER,2,null,null,null,null,null|U,0,INTEGER,4,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getFmaleSelAgeAdj","setFmaleSelAgeAdj","FMALE_SEL_AGE_ADJ,3,15,2,0,false|T,0,INTEGER,2,null,null,null,null,null|U,0,INTEGER,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getFmaleSelRateTbl","setFmaleSelRateTbl","FMALE_SEL_RATE_TBL,1,16,16,0,false|T,0,CHAR,2,null,null,FM,null,null|U,0,CHAR,0,030,2,FM,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getMaleAnnPremno","setMaleAnnPremno","MALE_ANN_PREMNO,1,17,16,0,false|T,0,CHAR,0,T72,1,ML,null,null|U,0,CHAR,2,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getFmaleAnnPremno","setFmaleAnnPremno","FMALE_ANN_PREMNO,1,18,16,0,false|T,0,CHAR,0,T72,2,FM,null,null|U,0,CHAR,2,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getFmaleSetback","setFmaleSetback","FMALE_SETBACK,3,19,2,0,false|T,0,INTEGER,0,null,null,null,null,null|U,0,INTEGER,2,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getRatingFactor","setRatingFactor","RATING_FACTOR,3,20,7,5,false|T,0,DOUBLE,0,null,null,null,null,null|U,0,DOUBLE,2,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getMguarMaxannPpu","setMguarMaxannPpu","MGUAR_MAXANN_PPU,1,21,16,0,false|T,0,CHAR,0,T72,3,ML,null,null|U,0,CHAR,2,null,null,ML,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getFguarMaxannPpu","setFguarMaxannPpu","FGUAR_MAXANN_PPU,1,22,16,0,false|T,0,CHAR,0,T72,4,FM,null,null|U,0,CHAR,2,null,null,FM,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getCunisexAnnPpu","setCunisexAnnPpu","CUNISEX_ANN_PPU,1,23,16,0,false|T,0,CHAR,0,T72,5,U_SEX,null,null|U,0,CHAR,2,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getGunisexAnnPpu","setGunisexAnnPpu","GUNISEX_ANN_PPU,1,24,16,0,false|T,0,CHAR,0,T72,6,U_SEX,null,null|U,0,CHAR,2,null,null,U_SEX,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getCmaleFemAnnppu","setCmaleFemAnnppu","CMALE_FEM_ANNPPU,1,25,16,0,false|T,0,CHAR,0,T72,7,ML_FM,null,null|U,0,CHAR,2,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getGmaleFemAnnppu","setGmaleFemAnnppu","GMALE_FEM_ANNPPU,1,26,16,0,false|T,0,CHAR,0,T72,8,ML_FM,null,null|U,0,CHAR,2,null,null,ML_FM,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getMaleUltGrnRate","setMaleUltGrnRate","MALE_ULT_GRN_RATE,1,27,16,0,false|T,0,CHAR,2,null,null,null,null,null|U,0,CHAR,0,029,3,ML,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getFmaleUltGrnRate","setFmaleUltGrnRate","FMALE_ULT_GRN_RATE,1,28,16,0,false|T,0,CHAR,2,null,null,null,null,null|U,0,CHAR,0,029,4,FM,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getMaleSelGrnRate","setMaleSelGrnRate","MALE_SEL_GRN_RATE,1,29,16,0,false|T,0,CHAR,2,null,null,null,null,null|U,0,CHAR,0,030,3,ML,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getFmaleSelGrnRate","setFmaleSelGrnRate","FMALE_SEL_GRN_RATE,1,30,16,0,false|T,0,CHAR,2,null,null,null,null,null|U,0,CHAR,0,030,4,FM,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getSelectPeriod","setSelectPeriod","SELECT_PERIOD,3,31,3,0,false|T,0,INTEGER,2,null,null,null,null,null|U,0,INTEGER,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getMaleUltGrnagead","setMaleUltGrnagead","MALE_ULT_GRNAGEAD,3,32,2,0,false|T,0,INTEGER,2,null,null,null,null,null|U,0,INTEGER,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getFmaleUltGrnagead","setFmaleUltGrnagead","FMALE_ULT_GRNAGEAD,3,33,2,0,false|T,0,INTEGER,2,null,null,null,null,null|U,0,INTEGER,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getUltRateFactor","setUltRateFactor","ULT_RATE_FACTOR,3,34,6,5,false|T,0,DOUBLE,2,null,null,null,null,null|U,0,DOUBLE,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getSelRateFactor","setSelRateFactor","SEL_RATE_FACTOR,3,35,6,5,false|T,0,DOUBLE,2,null,null,null,null,null|U,0,DOUBLE,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getAmtRiskBandCd","setAmtRiskBandCd","AMT_RISK_BAND_CD,1,36,1,0,false|T,0,CHAR,2,null,null,null,null,null|U,0,CHAR,2,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getMaleUltAgeAdj","setMaleUltAgeAdj","MALE_ULT_AGE_ADJ,3,37,2,0,false|T,0,INTEGER,2,null,null,null,null,null|U,0,INTEGER,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getFmaleUltAgeAdj","setFmaleUltAgeAdj","FMALE_ULT_AGE_ADJ,3,38,2,0,false|T,0,INTEGER,2,null,null,null,null,null|U,0,INTEGER,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getMaleSelgrnageadj","setMaleSelgrnageadj","MALE_SELGRNAGEADJ,3,39,2,0,false|T,0,INTEGER,2,null,null,null,null,null|U,0,INTEGER,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getFmaleSelgrnageadj","setFmaleSelgrnageadj","FMALE_SELGRNAGEADJ,3,40,2,0,false|T,0,INTEGER,2,null,null,null,null,null|U,0,INTEGER,0,null,null,null,null,null"));
    }
    
    public String getPagingSQL(String schemaName,boolean isSubsetMode,boolean isNext, boolean locator)
    {
        String pagingWhere = null;
        String order = null;
        if (isNext) {
            if (isSubsetMode)
                if (locator)
                    pagingWhere = ".T012X WHERE (COMPANY_CODE = :company_code) AND (PRODUCT_PREFIX = :product_prefix) AND (TABLE_SUBSET = :table_subset) AND ((EFFECTIVE_DATE > :effective_date) OR (EFFECTIVE_DATE = :effective_date AND SPECIAL_CLASS_CD > :special_class_cd) OR (EFFECTIVE_DATE = :effective_date AND SPECIAL_CLASS_CD = :special_class_cd AND PREMIUM_CLASS_CD > :premium_class_cd) OR (EFFECTIVE_DATE = :effective_date AND SPECIAL_CLASS_CD = :special_class_cd AND PREMIUM_CLASS_CD = :premium_class_cd AND MAX_COVG_UNITS > :max_covg_units) OR (EFFECTIVE_DATE = :effective_date AND SPECIAL_CLASS_CD = :special_class_cd AND PREMIUM_CLASS_CD = :premium_class_cd AND MAX_COVG_UNITS = :max_covg_units AND AMT_AT_RISK > :amt_at_risk) OR (EFFECTIVE_DATE = :effective_date AND SPECIAL_CLASS_CD = :special_class_cd AND PREMIUM_CLASS_CD = :premium_class_cd AND MAX_COVG_UNITS = :max_covg_units AND AMT_AT_RISK = :amt_at_risk)) ";
                else 
                    pagingWhere = ".T012X WHERE (COMPANY_CODE = :company_code) AND (PRODUCT_PREFIX = :product_prefix) AND (TABLE_SUBSET = :table_subset) AND ((EFFECTIVE_DATE > :effective_date) OR (EFFECTIVE_DATE = :effective_date AND SPECIAL_CLASS_CD > :special_class_cd) OR (EFFECTIVE_DATE = :effective_date AND SPECIAL_CLASS_CD = :special_class_cd AND PREMIUM_CLASS_CD > :premium_class_cd) OR (EFFECTIVE_DATE = :effective_date AND SPECIAL_CLASS_CD = :special_class_cd AND PREMIUM_CLASS_CD = :premium_class_cd AND MAX_COVG_UNITS > :max_covg_units) OR (EFFECTIVE_DATE = :effective_date AND SPECIAL_CLASS_CD = :special_class_cd AND PREMIUM_CLASS_CD = :premium_class_cd AND MAX_COVG_UNITS = :max_covg_units AND AMT_AT_RISK > :amt_at_risk)) ";
            else
                if (locator)
                    pagingWhere = ".T012X WHERE (COMPANY_CODE = :company_code) AND ((PRODUCT_PREFIX > :product_prefix) OR (PRODUCT_PREFIX = :product_prefix AND TABLE_SUBSET > :table_subset) OR (PRODUCT_PREFIX = :product_prefix AND TABLE_SUBSET = :table_subset AND EFFECTIVE_DATE > :effective_date) OR (PRODUCT_PREFIX = :product_prefix AND TABLE_SUBSET = :table_subset AND EFFECTIVE_DATE = :effective_date AND SPECIAL_CLASS_CD > :special_class_cd) OR (PRODUCT_PREFIX = :product_prefix AND TABLE_SUBSET = :table_subset AND EFFECTIVE_DATE = :effective_date AND SPECIAL_CLASS_CD = :special_class_cd AND PREMIUM_CLASS_CD > :premium_class_cd) OR (PRODUCT_PREFIX = :product_prefix AND TABLE_SUBSET = :table_subset AND EFFECTIVE_DATE = :effective_date AND SPECIAL_CLASS_CD = :special_class_cd AND PREMIUM_CLASS_CD = :premium_class_cd AND MAX_COVG_UNITS > :max_covg_units) OR (PRODUCT_PREFIX = :product_prefix AND TABLE_SUBSET = :table_subset AND EFFECTIVE_DATE = :effective_date AND SPECIAL_CLASS_CD = :special_class_cd AND PREMIUM_CLASS_CD = :premium_class_cd AND MAX_COVG_UNITS = :max_covg_units AND AMT_AT_RISK > :amt_at_risk) OR (PRODUCT_PREFIX = :product_prefix AND TABLE_SUBSET = :table_subset AND EFFECTIVE_DATE = :effective_date AND SPECIAL_CLASS_CD = :special_class_cd AND PREMIUM_CLASS_CD = :premium_class_cd AND MAX_COVG_UNITS = :max_covg_units AND AMT_AT_RISK = :amt_at_risk)) ";
                else
                    pagingWhere = ".T012X WHERE (COMPANY_CODE = :company_code) AND ((PRODUCT_PREFIX > :product_prefix) OR (PRODUCT_PREFIX = :product_prefix AND TABLE_SUBSET > :table_subset) OR (PRODUCT_PREFIX = :product_prefix AND TABLE_SUBSET = :table_subset AND EFFECTIVE_DATE > :effective_date) OR (PRODUCT_PREFIX = :product_prefix AND TABLE_SUBSET = :table_subset AND EFFECTIVE_DATE = :effective_date AND SPECIAL_CLASS_CD > :special_class_cd) OR (PRODUCT_PREFIX = :product_prefix AND TABLE_SUBSET = :table_subset AND EFFECTIVE_DATE = :effective_date AND SPECIAL_CLASS_CD = :special_class_cd AND PREMIUM_CLASS_CD > :premium_class_cd) OR (PRODUCT_PREFIX = :product_prefix AND TABLE_SUBSET = :table_subset AND EFFECTIVE_DATE = :effective_date AND SPECIAL_CLASS_CD = :special_class_cd AND PREMIUM_CLASS_CD = :premium_class_cd AND MAX_COVG_UNITS > :max_covg_units) OR (PRODUCT_PREFIX = :product_prefix AND TABLE_SUBSET = :table_subset AND EFFECTIVE_DATE = :effective_date AND SPECIAL_CLASS_CD = :special_class_cd AND PREMIUM_CLASS_CD = :premium_class_cd AND MAX_COVG_UNITS = :max_covg_units AND AMT_AT_RISK > :amt_at_risk)) ";
            order = " ORDER BY COMPANY_CODE, PRODUCT_PREFIX, TABLE_SUBSET, EFFECTIVE_DATE, SPECIAL_CLASS_CD, PREMIUM_CLASS_CD, MAX_COVG_UNITS, AMT_AT_RISK";
        }
        else {
            if (isSubsetMode)
                pagingWhere = ".T012X WHERE (COMPANY_CODE = :company_code) AND (PRODUCT_PREFIX = :product_prefix) AND (TABLE_SUBSET = :table_subset) AND ((EFFECTIVE_DATE < :effective_date) OR (EFFECTIVE_DATE = :effective_date AND SPECIAL_CLASS_CD < :special_class_cd) OR (EFFECTIVE_DATE = :effective_date AND SPECIAL_CLASS_CD = :special_class_cd AND PREMIUM_CLASS_CD < :premium_class_cd) OR (EFFECTIVE_DATE = :effective_date AND SPECIAL_CLASS_CD = :special_class_cd AND PREMIUM_CLASS_CD = :premium_class_cd AND MAX_COVG_UNITS < :max_covg_units) OR (EFFECTIVE_DATE = :effective_date AND SPECIAL_CLASS_CD = :special_class_cd AND PREMIUM_CLASS_CD = :premium_class_cd AND MAX_COVG_UNITS = :max_covg_units AND AMT_AT_RISK < :amt_at_risk)) ";
            else
                pagingWhere = ".T012X WHERE (COMPANY_CODE = :company_code) AND ((PRODUCT_PREFIX < :product_prefix) OR (PRODUCT_PREFIX = :product_prefix AND TABLE_SUBSET < :table_subset) OR (PRODUCT_PREFIX = :product_prefix AND TABLE_SUBSET = :table_subset AND EFFECTIVE_DATE < :effective_date) OR (PRODUCT_PREFIX = :product_prefix AND TABLE_SUBSET = :table_subset AND EFFECTIVE_DATE = :effective_date AND SPECIAL_CLASS_CD < :special_class_cd) OR (PRODUCT_PREFIX = :product_prefix AND TABLE_SUBSET = :table_subset AND EFFECTIVE_DATE = :effective_date AND SPECIAL_CLASS_CD = :special_class_cd AND PREMIUM_CLASS_CD < :premium_class_cd) OR (PRODUCT_PREFIX = :product_prefix AND TABLE_SUBSET = :table_subset AND EFFECTIVE_DATE = :effective_date AND SPECIAL_CLASS_CD = :special_class_cd AND PREMIUM_CLASS_CD = :premium_class_cd AND MAX_COVG_UNITS < :max_covg_units) OR (PRODUCT_PREFIX = :product_prefix AND TABLE_SUBSET = :table_subset AND EFFECTIVE_DATE = :effective_date AND SPECIAL_CLASS_CD = :special_class_cd AND PREMIUM_CLASS_CD = :premium_class_cd AND MAX_COVG_UNITS = :max_covg_units AND AMT_AT_RISK < :amt_at_risk)) ";
            order = " ORDER BY COMPANY_CODE DESC, PRODUCT_PREFIX DESC, TABLE_SUBSET DESC, EFFECTIVE_DATE DESC, SPECIAL_CLASS_CD DESC, PREMIUM_CLASS_CD DESC, MAX_COVG_UNITS DESC, AMT_AT_RISK DESC";
        }
        return pagingSql + schemaName + pagingWhere + order;
    }
    
    public String prepareInsertStmt(String schemaName) {
        StringBuffer sb = new StringBuffer();
        sb.append("INSERT INTO ").append(schemaName);
        sb.append(".T012X ( ");
        sb.append("COMPANY_CODE, PRODUCT_PREFIX, TABLE_SUBSET, EFFECTIVE_DATE, SPECIAL_CLASS_CD, PREMIUM_CLASS_CD, MAX_COVG_UNITS, AMT_AT_RISK, AGE_USAGE_CODE, CALC_RULE, MALE_ULT_RATE_TBL, FMALE_ULT_RATE_TBL, MALE_SEL_RATE_TBL, MALE_SEL_AGE_ADJ, FMALE_SEL_AGE_ADJ, FMALE_SEL_RATE_TBL, MALE_ANN_PREMNO, FMALE_ANN_PREMNO, FMALE_SETBACK, RATING_FACTOR, MGUAR_MAXANN_PPU, FGUAR_MAXANN_PPU, CUNISEX_ANN_PPU, GUNISEX_ANN_PPU, CMALE_FEM_ANNPPU, GMALE_FEM_ANNPPU, MALE_ULT_GRN_RATE, FMALE_ULT_GRN_RATE, MALE_SEL_GRN_RATE, FMALE_SEL_GRN_RATE, SELECT_PERIOD, MALE_ULT_GRNAGEAD, FMALE_ULT_GRNAGEAD, ULT_RATE_FACTOR, SEL_RATE_FACTOR, AMT_RISK_BAND_CD, MALE_ULT_AGE_ADJ, FMALE_ULT_AGE_ADJ, MALE_SELGRNAGEADJ, FMALE_SELGRNAGEADJ )");
        sb.append(" VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ? )");
        return sb.toString();
    }
    
    public String prepareUpdateStmt(String schemaName)
    {
        StringBuffer sb = new StringBuffer();
        sb.append("UPDATE ").append(schemaName);
        sb.append(".T012X ");
        sb.append(" SET COMPANY_CODE = ?, PRODUCT_PREFIX = ?, TABLE_SUBSET = ?, EFFECTIVE_DATE = ?, SPECIAL_CLASS_CD = ?, PREMIUM_CLASS_CD = ?, MAX_COVG_UNITS = ?, AMT_AT_RISK = ?, AGE_USAGE_CODE = ?, CALC_RULE = ?, MALE_ULT_RATE_TBL = ?, FMALE_ULT_RATE_TBL = ?, MALE_SEL_RATE_TBL = ?, MALE_SEL_AGE_ADJ = ?, FMALE_SEL_AGE_ADJ = ?, FMALE_SEL_RATE_TBL = ?, MALE_ANN_PREMNO = ?, FMALE_ANN_PREMNO = ?, FMALE_SETBACK = ?, RATING_FACTOR = ?, MGUAR_MAXANN_PPU = ?, FGUAR_MAXANN_PPU = ?, CUNISEX_ANN_PPU = ?, GUNISEX_ANN_PPU = ?, CMALE_FEM_ANNPPU = ?, GMALE_FEM_ANNPPU = ?, MALE_ULT_GRN_RATE = ?, FMALE_ULT_GRN_RATE = ?, MALE_SEL_GRN_RATE = ?, FMALE_SEL_GRN_RATE = ?, SELECT_PERIOD = ?, MALE_ULT_GRNAGEAD = ?, FMALE_ULT_GRNAGEAD = ?, ULT_RATE_FACTOR = ?, SEL_RATE_FACTOR = ?, AMT_RISK_BAND_CD = ?, MALE_ULT_AGE_ADJ = ?, FMALE_ULT_AGE_ADJ = ?, MALE_SELGRNAGEADJ = ?, FMALE_SELGRNAGEADJ = ?");
        sb.append(" WHERE COMPANY_CODE = ? AND PRODUCT_PREFIX = ? AND TABLE_SUBSET = ? AND EFFECTIVE_DATE = ? AND SPECIAL_CLASS_CD = ? AND PREMIUM_CLASS_CD = ? AND MAX_COVG_UNITS = ? AND AMT_AT_RISK = ?");
        return sb.toString();
    }
    
    public String prepareDeleteStmt(String schemaName)
    {
        StringBuffer sb = new StringBuffer();
        sb.append("DELETE FROM ").append(schemaName);
        sb.append(".T012X ");
        sb.append(" WHERE COMPANY_CODE = ? AND PRODUCT_PREFIX = ? AND TABLE_SUBSET = ? AND EFFECTIVE_DATE = ? AND SPECIAL_CLASS_CD = ? AND PREMIUM_CLASS_CD = ? AND MAX_COVG_UNITS = ? AND AMT_AT_RISK = ?");
        return sb.toString();
    }
}
