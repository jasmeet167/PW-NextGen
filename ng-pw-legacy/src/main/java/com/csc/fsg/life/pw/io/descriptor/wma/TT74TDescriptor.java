package com.csc.fsg.life.pw.io.descriptor.wma;

import com.csc.fsg.life.pw.io.*;

public class TT74TDescriptor
    extends TableDescriptor
{
    private static final String pagingSql = "SELECT COMPANY_CODE, TABLE_SUBSET, EFFECTIVE_DATE, PREMIUM_CLASS_CODE, MX_CVG_UNIT, AGE_USAGE_CODE, CALCULATION_RULE, ML_ANNPRM_PUNIT_TE, FM_ANNPRM_PUNIT_TE, FEMALE_SETBACK, ML_GR_MX_ANNPPU_TE, FM_GR_MX_ANNPPU_TE, CU_UNISX_ANNPPU_TE, GR_UNISX_ANNPPU_TE, CU_ML_FM_ANNPPU_TE, GR_ML_FM_ANNPPU_TE FROM ";
    
    public void initialize()
    {
        setRowClass(TT74TRow.class);
        setTableName("TT74T");
        setTableId("T74");
        super.initialize();
    }
    
    public void initializeColumnDescriptors()
    {
        super.initializeColumnDescriptors();
        addColumnDescriptor(new ColumnDescriptor(this,"getCompanyCode","setCompanyCode","COMPANY_CODE,1,1,3,0,true|,0,CHAR,1,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getTableSubset","setTableSubset","TABLE_SUBSET,1,2,16,0,true|,0,CHAR,1,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getEffectiveDate","setEffectiveDate","EFFECTIVE_DATE,91,3,10,0,true|,0,DATE,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getPremiumClassCode","setPremiumClassCode","PREMIUM_CLASS_CODE,1,4,1,0,true|,0,CHAR,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getMxCvgUnit","setMxCvgUnit","MX_CVG_UNIT,3,5,11,5,true|,0,DOUBLE,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getAgeUsageCode","setAgeUsageCode","AGE_USAGE_CODE,1,6,1,0,false|,0,CHAR,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getCalculationRule","setCalculationRule","CALCULATION_RULE,1,7,1,0,false|,0,CHAR,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getMlAnnprmPunitTe","setMlAnnprmPunitTe","ML_ANNPRM_PUNIT_TE,1,8,16,0,false|,0,CHAR,0,T72,1,ML,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getFmAnnprmPunitTe","setFmAnnprmPunitTe","FM_ANNPRM_PUNIT_TE,1,9,16,0,false|,0,CHAR,0,T72,2,FM,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getFemaleSetback","setFemaleSetback","FEMALE_SETBACK,3,10,3,0,false|,0,INTEGER,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getMlGrMxAnnppuTe","setMlGrMxAnnppuTe","ML_GR_MX_ANNPPU_TE,1,11,16,0,false|,0,CHAR,0,T72,3,ML,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getFmGrMxAnnppuTe","setFmGrMxAnnppuTe","FM_GR_MX_ANNPPU_TE,1,12,16,0,false|,0,CHAR,0,T72,4,FM,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getCuUnisxAnnppuTe","setCuUnisxAnnppuTe","CU_UNISX_ANNPPU_TE,1,13,16,0,false|,0,CHAR,0,T72,5,U_SEX,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getGrUnisxAnnppuTe","setGrUnisxAnnppuTe","GR_UNISX_ANNPPU_TE,1,14,16,0,false|,0,CHAR,0,T72,6,U_SEX,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getCuMlFmAnnppuTe","setCuMlFmAnnppuTe","CU_ML_FM_ANNPPU_TE,1,15,16,0,false|,0,CHAR,0,T72,7,ML_FM,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getGrMlFmAnnppuTe","setGrMlFmAnnppuTe","GR_ML_FM_ANNPPU_TE,1,16,16,0,false|,0,CHAR,0,T72,8,ML_FM,null,null"));
    }
    
    public String getPagingSQL(String schemaName,boolean isSubsetMode,boolean isNext, boolean locator)
    {
        String pagingWhere = null;
        String order = null;
        if (isNext) {
            if (isSubsetMode)
                if (locator)
                    pagingWhere = ".TT74T WHERE (COMPANY_CODE = :company_code) AND (TABLE_SUBSET = :table_subset) AND ((EFFECTIVE_DATE > :effective_date) OR (EFFECTIVE_DATE = :effective_date AND PREMIUM_CLASS_CODE > :premium_class_code) OR (EFFECTIVE_DATE = :effective_date AND PREMIUM_CLASS_CODE = :premium_class_code AND MX_CVG_UNIT > :mx_cvg_unit) OR (EFFECTIVE_DATE = :effective_date AND PREMIUM_CLASS_CODE = :premium_class_code AND MX_CVG_UNIT = :mx_cvg_unit)) ";
                else 
                    pagingWhere = ".TT74T WHERE (COMPANY_CODE = :company_code) AND (TABLE_SUBSET = :table_subset) AND ((EFFECTIVE_DATE > :effective_date) OR (EFFECTIVE_DATE = :effective_date AND PREMIUM_CLASS_CODE > :premium_class_code) OR (EFFECTIVE_DATE = :effective_date AND PREMIUM_CLASS_CODE = :premium_class_code AND MX_CVG_UNIT > :mx_cvg_unit)) ";
            else
                if (locator)
                    pagingWhere = ".TT74T WHERE (COMPANY_CODE = :company_code) AND ((TABLE_SUBSET > :table_subset) OR (TABLE_SUBSET = :table_subset AND EFFECTIVE_DATE > :effective_date) OR (TABLE_SUBSET = :table_subset AND EFFECTIVE_DATE = :effective_date AND PREMIUM_CLASS_CODE > :premium_class_code) OR (TABLE_SUBSET = :table_subset AND EFFECTIVE_DATE = :effective_date AND PREMIUM_CLASS_CODE = :premium_class_code AND MX_CVG_UNIT > :mx_cvg_unit) OR (TABLE_SUBSET = :table_subset AND EFFECTIVE_DATE = :effective_date AND PREMIUM_CLASS_CODE = :premium_class_code AND MX_CVG_UNIT = :mx_cvg_unit)) ";
                else
                    pagingWhere = ".TT74T WHERE (COMPANY_CODE = :company_code) AND ((TABLE_SUBSET > :table_subset) OR (TABLE_SUBSET = :table_subset AND EFFECTIVE_DATE > :effective_date) OR (TABLE_SUBSET = :table_subset AND EFFECTIVE_DATE = :effective_date AND PREMIUM_CLASS_CODE > :premium_class_code) OR (TABLE_SUBSET = :table_subset AND EFFECTIVE_DATE = :effective_date AND PREMIUM_CLASS_CODE = :premium_class_code AND MX_CVG_UNIT > :mx_cvg_unit)) ";
            order = " ORDER BY COMPANY_CODE, TABLE_SUBSET, EFFECTIVE_DATE, PREMIUM_CLASS_CODE, MX_CVG_UNIT";
        }
        else {
            if (isSubsetMode)
                pagingWhere = ".TT74T WHERE (COMPANY_CODE = :company_code) AND (TABLE_SUBSET = :table_subset) AND ((EFFECTIVE_DATE < :effective_date) OR (EFFECTIVE_DATE = :effective_date AND PREMIUM_CLASS_CODE < :premium_class_code) OR (EFFECTIVE_DATE = :effective_date AND PREMIUM_CLASS_CODE = :premium_class_code AND MX_CVG_UNIT < :mx_cvg_unit)) ";
            else
                pagingWhere = ".TT74T WHERE (COMPANY_CODE = :company_code) AND ((TABLE_SUBSET < :table_subset) OR (TABLE_SUBSET = :table_subset AND EFFECTIVE_DATE < :effective_date) OR (TABLE_SUBSET = :table_subset AND EFFECTIVE_DATE = :effective_date AND PREMIUM_CLASS_CODE < :premium_class_code) OR (TABLE_SUBSET = :table_subset AND EFFECTIVE_DATE = :effective_date AND PREMIUM_CLASS_CODE = :premium_class_code AND MX_CVG_UNIT < :mx_cvg_unit)) ";
            order = " ORDER BY COMPANY_CODE DESC, TABLE_SUBSET DESC, EFFECTIVE_DATE DESC, PREMIUM_CLASS_CODE DESC, MX_CVG_UNIT DESC";
        }
        return pagingSql + schemaName + pagingWhere + order;
    }
    
    public String prepareInsertStmt(String schemaName) {
        StringBuffer sb = new StringBuffer();
        sb.append("INSERT INTO ").append(schemaName);
        sb.append(".TT74T ( ");
        sb.append("COMPANY_CODE, TABLE_SUBSET, EFFECTIVE_DATE, PREMIUM_CLASS_CODE, MX_CVG_UNIT, AGE_USAGE_CODE, CALCULATION_RULE, ML_ANNPRM_PUNIT_TE, FM_ANNPRM_PUNIT_TE, FEMALE_SETBACK, ML_GR_MX_ANNPPU_TE, FM_GR_MX_ANNPPU_TE, CU_UNISX_ANNPPU_TE, GR_UNISX_ANNPPU_TE, CU_ML_FM_ANNPPU_TE, GR_ML_FM_ANNPPU_TE )");
        sb.append(" VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ? )");
        return sb.toString();
    }
    
    public String prepareUpdateStmt(String schemaName)
    {
        StringBuffer sb = new StringBuffer();
        sb.append("UPDATE ").append(schemaName);
        sb.append(".TT74T ");
        sb.append(" SET COMPANY_CODE = ?, TABLE_SUBSET = ?, EFFECTIVE_DATE = ?, PREMIUM_CLASS_CODE = ?, MX_CVG_UNIT = ?, AGE_USAGE_CODE = ?, CALCULATION_RULE = ?, ML_ANNPRM_PUNIT_TE = ?, FM_ANNPRM_PUNIT_TE = ?, FEMALE_SETBACK = ?, ML_GR_MX_ANNPPU_TE = ?, FM_GR_MX_ANNPPU_TE = ?, CU_UNISX_ANNPPU_TE = ?, GR_UNISX_ANNPPU_TE = ?, CU_ML_FM_ANNPPU_TE = ?, GR_ML_FM_ANNPPU_TE = ?");
        sb.append(" WHERE COMPANY_CODE = ? AND TABLE_SUBSET = ? AND EFFECTIVE_DATE = ? AND PREMIUM_CLASS_CODE = ? AND MX_CVG_UNIT = ?");
        return sb.toString();
    }
    
    public String prepareDeleteStmt(String schemaName)
    {
        StringBuffer sb = new StringBuffer();
        sb.append("DELETE FROM ").append(schemaName);
        sb.append(".TT74T ");
        sb.append(" WHERE COMPANY_CODE = ? AND TABLE_SUBSET = ? AND EFFECTIVE_DATE = ? AND PREMIUM_CLASS_CODE = ? AND MX_CVG_UNIT = ?");
        return sb.toString();
    }
}
