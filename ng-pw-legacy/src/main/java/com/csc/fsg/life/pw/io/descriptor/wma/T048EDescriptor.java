package com.csc.fsg.life.pw.io.descriptor.wma;

import com.csc.fsg.life.pw.io.*;

public class T048EDescriptor
    extends TableDescriptor
{
    private static final String pagingSql = "SELECT COMPANY_CODE, TABLE_SUBSET, EFFECTIVE_DATE, COV_PLAN_CODE, CALC_TYPE_IND, SPECIAL_CLASS_CODE, RATING_FACTOR, LEV_VAR_IND, RDR_BENE_SPEC_CLS, RDR_BENE_COST_RISK, RDR_BENE_ADM_LOAD, RDR_BENE_SALE_LOAD FROM ";
    
    public void initialize()
    {
        setRowClass(T048ERow.class);
        setTableName("T048E");
        setTableId("048");
        super.initialize();
    }
    
    public void initializeColumnDescriptors()
    {
        super.initializeColumnDescriptors();
        addColumnDescriptor(new ColumnDescriptor(this,"getCompanyCode","setCompanyCode","COMPANY_CODE,1,1,3,0,true|,0,CHAR,1,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getTableSubset","setTableSubset","TABLE_SUBSET,1,2,16,0,true|,0,CHAR,1,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getEffectiveDate","setEffectiveDate","EFFECTIVE_DATE,91,3,10,0,true|,0,DATE,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getCovPlanCode","setCovPlanCode","COV_PLAN_CODE,1,4,6,0,true|,0,CHAR,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getCalcTypeInd","setCalcTypeInd","CALC_TYPE_IND,1,5,1,0,true|,0,CHAR,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getSpecialClassCode","setSpecialClassCode","SPECIAL_CLASS_CODE,1,6,2,0,true|,0,CHAR,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getRatingFactor","setRatingFactor","RATING_FACTOR,3,7,6,5,true|,0,DOUBLE,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getLevVarInd","setLevVarInd","LEV_VAR_IND,1,8,1,0,true|,0,CHAR,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getRdrBeneSpecCls","setRdrBeneSpecCls","RDR_BENE_SPEC_CLS,1,9,16,0,false|,0,CHAR,0,012,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getRdrBeneCostRisk","setRdrBeneCostRisk","RDR_BENE_COST_RISK,1,10,16,0,false|,0,CHAR,0,028,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getRdrBeneAdmLoad","setRdrBeneAdmLoad","RDR_BENE_ADM_LOAD,1,11,16,0,false|,0,CHAR,0,031,1,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getRdrBeneSaleLoad","setRdrBeneSaleLoad","RDR_BENE_SALE_LOAD,1,12,16,0,false|,0,CHAR,0,031,2,null,null,null"));
    }
    
    public String getPagingSQL(String schemaName,boolean isSubsetMode,boolean isNext, boolean locator)
    {
        String pagingWhere = null;
        String order = null;
        if (isNext) {
            if (isSubsetMode)
                if (locator)
                    pagingWhere = ".T048E WHERE (COMPANY_CODE = :company_code) AND (TABLE_SUBSET = :table_subset) AND ((EFFECTIVE_DATE > :effective_date) OR (EFFECTIVE_DATE = :effective_date AND COV_PLAN_CODE > :cov_plan_code) OR (EFFECTIVE_DATE = :effective_date AND COV_PLAN_CODE = :cov_plan_code AND CALC_TYPE_IND > :calc_type_ind) OR (EFFECTIVE_DATE = :effective_date AND COV_PLAN_CODE = :cov_plan_code AND CALC_TYPE_IND = :calc_type_ind AND SPECIAL_CLASS_CODE > :special_class_code) OR (EFFECTIVE_DATE = :effective_date AND COV_PLAN_CODE = :cov_plan_code AND CALC_TYPE_IND = :calc_type_ind AND SPECIAL_CLASS_CODE = :special_class_code AND RATING_FACTOR > :rating_factor) OR (EFFECTIVE_DATE = :effective_date AND COV_PLAN_CODE = :cov_plan_code AND CALC_TYPE_IND = :calc_type_ind AND SPECIAL_CLASS_CODE = :special_class_code AND RATING_FACTOR = :rating_factor AND LEV_VAR_IND > :lev_var_ind) OR (EFFECTIVE_DATE = :effective_date AND COV_PLAN_CODE = :cov_plan_code AND CALC_TYPE_IND = :calc_type_ind AND SPECIAL_CLASS_CODE = :special_class_code AND RATING_FACTOR = :rating_factor AND LEV_VAR_IND = :lev_var_ind)) ";
                else 
                    pagingWhere = ".T048E WHERE (COMPANY_CODE = :company_code) AND (TABLE_SUBSET = :table_subset) AND ((EFFECTIVE_DATE > :effective_date) OR (EFFECTIVE_DATE = :effective_date AND COV_PLAN_CODE > :cov_plan_code) OR (EFFECTIVE_DATE = :effective_date AND COV_PLAN_CODE = :cov_plan_code AND CALC_TYPE_IND > :calc_type_ind) OR (EFFECTIVE_DATE = :effective_date AND COV_PLAN_CODE = :cov_plan_code AND CALC_TYPE_IND = :calc_type_ind AND SPECIAL_CLASS_CODE > :special_class_code) OR (EFFECTIVE_DATE = :effective_date AND COV_PLAN_CODE = :cov_plan_code AND CALC_TYPE_IND = :calc_type_ind AND SPECIAL_CLASS_CODE = :special_class_code AND RATING_FACTOR > :rating_factor) OR (EFFECTIVE_DATE = :effective_date AND COV_PLAN_CODE = :cov_plan_code AND CALC_TYPE_IND = :calc_type_ind AND SPECIAL_CLASS_CODE = :special_class_code AND RATING_FACTOR = :rating_factor AND LEV_VAR_IND > :lev_var_ind)) ";
            else
                if (locator)
                    pagingWhere = ".T048E WHERE (COMPANY_CODE = :company_code) AND ((TABLE_SUBSET > :table_subset) OR (TABLE_SUBSET = :table_subset AND EFFECTIVE_DATE > :effective_date) OR (TABLE_SUBSET = :table_subset AND EFFECTIVE_DATE = :effective_date AND COV_PLAN_CODE > :cov_plan_code) OR (TABLE_SUBSET = :table_subset AND EFFECTIVE_DATE = :effective_date AND COV_PLAN_CODE = :cov_plan_code AND CALC_TYPE_IND > :calc_type_ind) OR (TABLE_SUBSET = :table_subset AND EFFECTIVE_DATE = :effective_date AND COV_PLAN_CODE = :cov_plan_code AND CALC_TYPE_IND = :calc_type_ind AND SPECIAL_CLASS_CODE > :special_class_code) OR (TABLE_SUBSET = :table_subset AND EFFECTIVE_DATE = :effective_date AND COV_PLAN_CODE = :cov_plan_code AND CALC_TYPE_IND = :calc_type_ind AND SPECIAL_CLASS_CODE = :special_class_code AND RATING_FACTOR > :rating_factor) OR (TABLE_SUBSET = :table_subset AND EFFECTIVE_DATE = :effective_date AND COV_PLAN_CODE = :cov_plan_code AND CALC_TYPE_IND = :calc_type_ind AND SPECIAL_CLASS_CODE = :special_class_code AND RATING_FACTOR = :rating_factor AND LEV_VAR_IND > :lev_var_ind) OR (TABLE_SUBSET = :table_subset AND EFFECTIVE_DATE = :effective_date AND COV_PLAN_CODE = :cov_plan_code AND CALC_TYPE_IND = :calc_type_ind AND SPECIAL_CLASS_CODE = :special_class_code AND RATING_FACTOR = :rating_factor AND LEV_VAR_IND = :lev_var_ind)) ";
                else
                    pagingWhere = ".T048E WHERE (COMPANY_CODE = :company_code) AND ((TABLE_SUBSET > :table_subset) OR (TABLE_SUBSET = :table_subset AND EFFECTIVE_DATE > :effective_date) OR (TABLE_SUBSET = :table_subset AND EFFECTIVE_DATE = :effective_date AND COV_PLAN_CODE > :cov_plan_code) OR (TABLE_SUBSET = :table_subset AND EFFECTIVE_DATE = :effective_date AND COV_PLAN_CODE = :cov_plan_code AND CALC_TYPE_IND > :calc_type_ind) OR (TABLE_SUBSET = :table_subset AND EFFECTIVE_DATE = :effective_date AND COV_PLAN_CODE = :cov_plan_code AND CALC_TYPE_IND = :calc_type_ind AND SPECIAL_CLASS_CODE > :special_class_code) OR (TABLE_SUBSET = :table_subset AND EFFECTIVE_DATE = :effective_date AND COV_PLAN_CODE = :cov_plan_code AND CALC_TYPE_IND = :calc_type_ind AND SPECIAL_CLASS_CODE = :special_class_code AND RATING_FACTOR > :rating_factor) OR (TABLE_SUBSET = :table_subset AND EFFECTIVE_DATE = :effective_date AND COV_PLAN_CODE = :cov_plan_code AND CALC_TYPE_IND = :calc_type_ind AND SPECIAL_CLASS_CODE = :special_class_code AND RATING_FACTOR = :rating_factor AND LEV_VAR_IND > :lev_var_ind)) ";
            order = " ORDER BY COMPANY_CODE, TABLE_SUBSET, EFFECTIVE_DATE, COV_PLAN_CODE, CALC_TYPE_IND, SPECIAL_CLASS_CODE, RATING_FACTOR, LEV_VAR_IND";
        }
        else {
            if (isSubsetMode)
                pagingWhere = ".T048E WHERE (COMPANY_CODE = :company_code) AND (TABLE_SUBSET = :table_subset) AND ((EFFECTIVE_DATE < :effective_date) OR (EFFECTIVE_DATE = :effective_date AND COV_PLAN_CODE < :cov_plan_code) OR (EFFECTIVE_DATE = :effective_date AND COV_PLAN_CODE = :cov_plan_code AND CALC_TYPE_IND < :calc_type_ind) OR (EFFECTIVE_DATE = :effective_date AND COV_PLAN_CODE = :cov_plan_code AND CALC_TYPE_IND = :calc_type_ind AND SPECIAL_CLASS_CODE < :special_class_code) OR (EFFECTIVE_DATE = :effective_date AND COV_PLAN_CODE = :cov_plan_code AND CALC_TYPE_IND = :calc_type_ind AND SPECIAL_CLASS_CODE = :special_class_code AND RATING_FACTOR < :rating_factor) OR (EFFECTIVE_DATE = :effective_date AND COV_PLAN_CODE = :cov_plan_code AND CALC_TYPE_IND = :calc_type_ind AND SPECIAL_CLASS_CODE = :special_class_code AND RATING_FACTOR = :rating_factor AND LEV_VAR_IND < :lev_var_ind)) ";
            else
                pagingWhere = ".T048E WHERE (COMPANY_CODE = :company_code) AND ((TABLE_SUBSET < :table_subset) OR (TABLE_SUBSET = :table_subset AND EFFECTIVE_DATE < :effective_date) OR (TABLE_SUBSET = :table_subset AND EFFECTIVE_DATE = :effective_date AND COV_PLAN_CODE < :cov_plan_code) OR (TABLE_SUBSET = :table_subset AND EFFECTIVE_DATE = :effective_date AND COV_PLAN_CODE = :cov_plan_code AND CALC_TYPE_IND < :calc_type_ind) OR (TABLE_SUBSET = :table_subset AND EFFECTIVE_DATE = :effective_date AND COV_PLAN_CODE = :cov_plan_code AND CALC_TYPE_IND = :calc_type_ind AND SPECIAL_CLASS_CODE < :special_class_code) OR (TABLE_SUBSET = :table_subset AND EFFECTIVE_DATE = :effective_date AND COV_PLAN_CODE = :cov_plan_code AND CALC_TYPE_IND = :calc_type_ind AND SPECIAL_CLASS_CODE = :special_class_code AND RATING_FACTOR < :rating_factor) OR (TABLE_SUBSET = :table_subset AND EFFECTIVE_DATE = :effective_date AND COV_PLAN_CODE = :cov_plan_code AND CALC_TYPE_IND = :calc_type_ind AND SPECIAL_CLASS_CODE = :special_class_code AND RATING_FACTOR = :rating_factor AND LEV_VAR_IND < :lev_var_ind)) ";
            order = " ORDER BY COMPANY_CODE DESC, TABLE_SUBSET DESC, EFFECTIVE_DATE DESC, COV_PLAN_CODE DESC, CALC_TYPE_IND DESC, SPECIAL_CLASS_CODE DESC, RATING_FACTOR DESC, LEV_VAR_IND DESC";
        }
        return pagingSql + schemaName + pagingWhere + order;
    }
    
    public String prepareInsertStmt(String schemaName) {
        StringBuffer sb = new StringBuffer();
        sb.append("INSERT INTO ").append(schemaName);
        sb.append(".T048E ( ");
        sb.append("COMPANY_CODE, TABLE_SUBSET, EFFECTIVE_DATE, COV_PLAN_CODE, CALC_TYPE_IND, SPECIAL_CLASS_CODE, RATING_FACTOR, LEV_VAR_IND, RDR_BENE_SPEC_CLS, RDR_BENE_COST_RISK, RDR_BENE_ADM_LOAD, RDR_BENE_SALE_LOAD )");
        sb.append(" VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ? )");
        return sb.toString();
    }
    
    public String prepareUpdateStmt(String schemaName)
    {
        StringBuffer sb = new StringBuffer();
        sb.append("UPDATE ").append(schemaName);
        sb.append(".T048E ");
        sb.append(" SET COMPANY_CODE = ?, TABLE_SUBSET = ?, EFFECTIVE_DATE = ?, COV_PLAN_CODE = ?, CALC_TYPE_IND = ?, SPECIAL_CLASS_CODE = ?, RATING_FACTOR = ?, LEV_VAR_IND = ?, RDR_BENE_SPEC_CLS = ?, RDR_BENE_COST_RISK = ?, RDR_BENE_ADM_LOAD = ?, RDR_BENE_SALE_LOAD = ?");
        sb.append(" WHERE COMPANY_CODE = ? AND TABLE_SUBSET = ? AND EFFECTIVE_DATE = ? AND COV_PLAN_CODE = ? AND CALC_TYPE_IND = ? AND SPECIAL_CLASS_CODE = ? AND RATING_FACTOR = ? AND LEV_VAR_IND = ?");
        return sb.toString();
    }
    
    public String prepareDeleteStmt(String schemaName)
    {
        StringBuffer sb = new StringBuffer();
        sb.append("DELETE FROM ").append(schemaName);
        sb.append(".T048E ");
        sb.append(" WHERE COMPANY_CODE = ? AND TABLE_SUBSET = ? AND EFFECTIVE_DATE = ? AND COV_PLAN_CODE = ? AND CALC_TYPE_IND = ? AND SPECIAL_CLASS_CODE = ? AND RATING_FACTOR = ? AND LEV_VAR_IND = ?");
        return sb.toString();
    }
}
