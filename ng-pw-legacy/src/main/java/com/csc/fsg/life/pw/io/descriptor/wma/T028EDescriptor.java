package com.csc.fsg.life.pw.io.descriptor.wma;

import com.csc.fsg.life.pw.io.*;

public class T028EDescriptor
    extends TableDescriptor
{
    private static final String pagingSql = "SELECT COMPANY_CODE, TABLE_SUBSET, PREMIUM_CLASS_CODE, EFFECTIVE_DATE, MX_CVG_UNIT, MX_AMT_AT_RSK, SELECT_PERIOD, MALE_ULT_RATE_TBL, FMALE_ULT_RATE_TBL, ML_ULT_AGE_ADJ, FEM_ULT_AGE_ADJ, ML_ULT_GUAR_RT_TBL, FEM_ULT_GR_RT_TBL, ML_ULT_GR_AGE_ADJT, FEM_ULTGR_AGE_ADJT, ML_SEL_RT_TBL, FEM_SEL_RT_TBL, ML_SEL_AGE_ADJ, FEM_SEL_AGE_ADJ, ML_SEL_GUAR_RT_TBL, FEM_SEL_GR_RT_TBL, ML_SEL_GR_AGE_ADJT, FEM_SELGR_AGE_ADJT FROM ";
    
    public void initialize()
    {
        setRowClass(T028ERow.class);
        setTableName("T028E");
        setTableId("028");
        super.initialize();
    }
    
    public void initializeColumnDescriptors()
    {
        super.initializeColumnDescriptors();
        addColumnDescriptor(new ColumnDescriptor(this,"getCompanyCode","setCompanyCode","COMPANY_CODE,1,1,3,0,true|,0,CHAR,1,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getTableSubset","setTableSubset","TABLE_SUBSET,1,2,16,0,true|,0,CHAR,1,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getPremiumClassCode","setPremiumClassCode","PREMIUM_CLASS_CODE,1,3,1,0,true|,0,CHAR,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getEffectiveDate","setEffectiveDate","EFFECTIVE_DATE,91,4,10,0,true|,0,DATE,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getMxCvgUnit","setMxCvgUnit","MX_CVG_UNIT,3,5,11,5,true|,0,DOUBLE,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getMxAmtAtRsk","setMxAmtAtRsk","MX_AMT_AT_RSK,3,6,11,2,true|,0,DOUBLE,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getSelectPeriod","setSelectPeriod","SELECT_PERIOD,3,7,3,0,false|,0,INTEGER,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getMaleUltRateTbl","setMaleUltRateTbl","MALE_ULT_RATE_TBL,1,8,16,0,false|,0,CHAR,0,029,1,ML_C,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getFmaleUltRateTbl","setFmaleUltRateTbl","FMALE_ULT_RATE_TBL,1,9,16,0,false|,0,CHAR,0,029,2,FM_C,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getMlUltAgeAdj","setMlUltAgeAdj","ML_ULT_AGE_ADJ,3,10,3,0,false|,0,INTEGER,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getFemUltAgeAdj","setFemUltAgeAdj","FEM_ULT_AGE_ADJ,3,11,3,0,false|,0,INTEGER,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getMlUltGuarRtTbl","setMlUltGuarRtTbl","ML_ULT_GUAR_RT_TBL,1,12,16,0,false|,0,CHAR,0,029,3,ML_G,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getFemUltGrRtTbl","setFemUltGrRtTbl","FEM_ULT_GR_RT_TBL,1,13,16,0,false|,0,CHAR,0,029,4,FM_G,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getMlUltGrAgeAdjt","setMlUltGrAgeAdjt","ML_ULT_GR_AGE_ADJT,3,14,3,0,false|,0,INTEGER,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getFemUltgrAgeAdjt","setFemUltgrAgeAdjt","FEM_ULTGR_AGE_ADJT,3,15,3,0,false|,0,INTEGER,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getMlSelRtTbl","setMlSelRtTbl","ML_SEL_RT_TBL,1,16,16,0,false|,0,CHAR,0,030,1,ML_C,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getFemSelRtTbl","setFemSelRtTbl","FEM_SEL_RT_TBL,1,17,16,0,false|,0,CHAR,0,030,2,FM_C,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getMlSelAgeAdj","setMlSelAgeAdj","ML_SEL_AGE_ADJ,3,18,3,0,false|,0,INTEGER,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getFemSelAgeAdj","setFemSelAgeAdj","FEM_SEL_AGE_ADJ,3,19,3,0,false|,0,INTEGER,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getMlSelGuarRtTbl","setMlSelGuarRtTbl","ML_SEL_GUAR_RT_TBL,1,20,16,0,false|,0,CHAR,0,030,3,ML_G,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getFemSelGrRtTbl","setFemSelGrRtTbl","FEM_SEL_GR_RT_TBL,1,21,16,0,false|,0,CHAR,0,030,4,FM_G,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getMlSelGrAgeAdjt","setMlSelGrAgeAdjt","ML_SEL_GR_AGE_ADJT,3,22,3,0,false|,0,INTEGER,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getFemSelgrAgeAdjt","setFemSelgrAgeAdjt","FEM_SELGR_AGE_ADJT,3,23,3,0,false|,0,INTEGER,0,null,null,null,null,null"));
    }
    
    public String getPagingSQL(String schemaName,boolean isSubsetMode,boolean isNext, boolean locator)
    {
        String pagingWhere = null;
        String order = null;
        if (isNext) {
            if (isSubsetMode)
                if (locator)
                    pagingWhere = ".T028E WHERE (COMPANY_CODE = :company_code) AND (TABLE_SUBSET = :table_subset) AND ((PREMIUM_CLASS_CODE > :premium_class_code) OR (PREMIUM_CLASS_CODE = :premium_class_code AND EFFECTIVE_DATE > :effective_date) OR (PREMIUM_CLASS_CODE = :premium_class_code AND EFFECTIVE_DATE = :effective_date AND MX_CVG_UNIT > :mx_cvg_unit) OR (PREMIUM_CLASS_CODE = :premium_class_code AND EFFECTIVE_DATE = :effective_date AND MX_CVG_UNIT = :mx_cvg_unit AND MX_AMT_AT_RSK > :mx_amt_at_rsk) OR (PREMIUM_CLASS_CODE = :premium_class_code AND EFFECTIVE_DATE = :effective_date AND MX_CVG_UNIT = :mx_cvg_unit AND MX_AMT_AT_RSK = :mx_amt_at_rsk)) ";
                else 
                    pagingWhere = ".T028E WHERE (COMPANY_CODE = :company_code) AND (TABLE_SUBSET = :table_subset) AND ((PREMIUM_CLASS_CODE > :premium_class_code) OR (PREMIUM_CLASS_CODE = :premium_class_code AND EFFECTIVE_DATE > :effective_date) OR (PREMIUM_CLASS_CODE = :premium_class_code AND EFFECTIVE_DATE = :effective_date AND MX_CVG_UNIT > :mx_cvg_unit) OR (PREMIUM_CLASS_CODE = :premium_class_code AND EFFECTIVE_DATE = :effective_date AND MX_CVG_UNIT = :mx_cvg_unit AND MX_AMT_AT_RSK > :mx_amt_at_rsk)) ";
            else
                if (locator)
                    pagingWhere = ".T028E WHERE (COMPANY_CODE = :company_code) AND ((TABLE_SUBSET > :table_subset) OR (TABLE_SUBSET = :table_subset AND PREMIUM_CLASS_CODE > :premium_class_code) OR (TABLE_SUBSET = :table_subset AND PREMIUM_CLASS_CODE = :premium_class_code AND EFFECTIVE_DATE > :effective_date) OR (TABLE_SUBSET = :table_subset AND PREMIUM_CLASS_CODE = :premium_class_code AND EFFECTIVE_DATE = :effective_date AND MX_CVG_UNIT > :mx_cvg_unit) OR (TABLE_SUBSET = :table_subset AND PREMIUM_CLASS_CODE = :premium_class_code AND EFFECTIVE_DATE = :effective_date AND MX_CVG_UNIT = :mx_cvg_unit AND MX_AMT_AT_RSK > :mx_amt_at_rsk) OR (TABLE_SUBSET = :table_subset AND PREMIUM_CLASS_CODE = :premium_class_code AND EFFECTIVE_DATE = :effective_date AND MX_CVG_UNIT = :mx_cvg_unit AND MX_AMT_AT_RSK = :mx_amt_at_rsk)) ";
                else
                    pagingWhere = ".T028E WHERE (COMPANY_CODE = :company_code) AND ((TABLE_SUBSET > :table_subset) OR (TABLE_SUBSET = :table_subset AND PREMIUM_CLASS_CODE > :premium_class_code) OR (TABLE_SUBSET = :table_subset AND PREMIUM_CLASS_CODE = :premium_class_code AND EFFECTIVE_DATE > :effective_date) OR (TABLE_SUBSET = :table_subset AND PREMIUM_CLASS_CODE = :premium_class_code AND EFFECTIVE_DATE = :effective_date AND MX_CVG_UNIT > :mx_cvg_unit) OR (TABLE_SUBSET = :table_subset AND PREMIUM_CLASS_CODE = :premium_class_code AND EFFECTIVE_DATE = :effective_date AND MX_CVG_UNIT = :mx_cvg_unit AND MX_AMT_AT_RSK > :mx_amt_at_rsk)) ";
            order = " ORDER BY COMPANY_CODE, TABLE_SUBSET, PREMIUM_CLASS_CODE, EFFECTIVE_DATE, MX_CVG_UNIT, MX_AMT_AT_RSK";
        }
        else {
            if (isSubsetMode)
                pagingWhere = ".T028E WHERE (COMPANY_CODE = :company_code) AND (TABLE_SUBSET = :table_subset) AND ((PREMIUM_CLASS_CODE < :premium_class_code) OR (PREMIUM_CLASS_CODE = :premium_class_code AND EFFECTIVE_DATE < :effective_date) OR (PREMIUM_CLASS_CODE = :premium_class_code AND EFFECTIVE_DATE = :effective_date AND MX_CVG_UNIT < :mx_cvg_unit) OR (PREMIUM_CLASS_CODE = :premium_class_code AND EFFECTIVE_DATE = :effective_date AND MX_CVG_UNIT = :mx_cvg_unit AND MX_AMT_AT_RSK < :mx_amt_at_rsk)) ";
            else
                pagingWhere = ".T028E WHERE (COMPANY_CODE = :company_code) AND ((TABLE_SUBSET < :table_subset) OR (TABLE_SUBSET = :table_subset AND PREMIUM_CLASS_CODE < :premium_class_code) OR (TABLE_SUBSET = :table_subset AND PREMIUM_CLASS_CODE = :premium_class_code AND EFFECTIVE_DATE < :effective_date) OR (TABLE_SUBSET = :table_subset AND PREMIUM_CLASS_CODE = :premium_class_code AND EFFECTIVE_DATE = :effective_date AND MX_CVG_UNIT < :mx_cvg_unit) OR (TABLE_SUBSET = :table_subset AND PREMIUM_CLASS_CODE = :premium_class_code AND EFFECTIVE_DATE = :effective_date AND MX_CVG_UNIT = :mx_cvg_unit AND MX_AMT_AT_RSK < :mx_amt_at_rsk)) ";
            order = " ORDER BY COMPANY_CODE DESC, TABLE_SUBSET DESC, PREMIUM_CLASS_CODE DESC, EFFECTIVE_DATE DESC, MX_CVG_UNIT DESC, MX_AMT_AT_RSK DESC";
        }
        return pagingSql + schemaName + pagingWhere + order;
    }
    
    public String prepareInsertStmt(String schemaName) {
        StringBuffer sb = new StringBuffer();
        sb.append("INSERT INTO ").append(schemaName);
        sb.append(".T028E ( ");
        sb.append("COMPANY_CODE, TABLE_SUBSET, PREMIUM_CLASS_CODE, EFFECTIVE_DATE, MX_CVG_UNIT, MX_AMT_AT_RSK, SELECT_PERIOD, MALE_ULT_RATE_TBL, FMALE_ULT_RATE_TBL, ML_ULT_AGE_ADJ, FEM_ULT_AGE_ADJ, ML_ULT_GUAR_RT_TBL, FEM_ULT_GR_RT_TBL, ML_ULT_GR_AGE_ADJT, FEM_ULTGR_AGE_ADJT, ML_SEL_RT_TBL, FEM_SEL_RT_TBL, ML_SEL_AGE_ADJ, FEM_SEL_AGE_ADJ, ML_SEL_GUAR_RT_TBL, FEM_SEL_GR_RT_TBL, ML_SEL_GR_AGE_ADJT, FEM_SELGR_AGE_ADJT )");
        sb.append(" VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ? )");
        return sb.toString();
    }
    
    public String prepareUpdateStmt(String schemaName)
    {
        StringBuffer sb = new StringBuffer();
        sb.append("UPDATE ").append(schemaName);
        sb.append(".T028E ");
        sb.append(" SET COMPANY_CODE = ?, TABLE_SUBSET = ?, PREMIUM_CLASS_CODE = ?, EFFECTIVE_DATE = ?, MX_CVG_UNIT = ?, MX_AMT_AT_RSK = ?, SELECT_PERIOD = ?, MALE_ULT_RATE_TBL = ?, FMALE_ULT_RATE_TBL = ?, ML_ULT_AGE_ADJ = ?, FEM_ULT_AGE_ADJ = ?, ML_ULT_GUAR_RT_TBL = ?, FEM_ULT_GR_RT_TBL = ?, ML_ULT_GR_AGE_ADJT = ?, FEM_ULTGR_AGE_ADJT = ?, ML_SEL_RT_TBL = ?, FEM_SEL_RT_TBL = ?, ML_SEL_AGE_ADJ = ?, FEM_SEL_AGE_ADJ = ?, ML_SEL_GUAR_RT_TBL = ?, FEM_SEL_GR_RT_TBL = ?, ML_SEL_GR_AGE_ADJT = ?, FEM_SELGR_AGE_ADJT = ?");
        sb.append(" WHERE COMPANY_CODE = ? AND TABLE_SUBSET = ? AND PREMIUM_CLASS_CODE = ? AND EFFECTIVE_DATE = ? AND MX_CVG_UNIT = ? AND MX_AMT_AT_RSK = ?");
        return sb.toString();
    }
    
    public String prepareDeleteStmt(String schemaName)
    {
        StringBuffer sb = new StringBuffer();
        sb.append("DELETE FROM ").append(schemaName);
        sb.append(".T028E ");
        sb.append(" WHERE COMPANY_CODE = ? AND TABLE_SUBSET = ? AND PREMIUM_CLASS_CODE = ? AND EFFECTIVE_DATE = ? AND MX_CVG_UNIT = ? AND MX_AMT_AT_RSK = ?");
        return sb.toString();
    }
}
