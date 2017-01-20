package com.csc.fsg.life.pw.io.descriptor.wma;

import com.csc.fsg.life.pw.io.*;

public class T052EDescriptor
    extends TableDescriptor
{
    private static final String pagingSql = "SELECT COMPANY_CODE, TABLE_SUBSET, EFFECTIVE_DATE, MAXIMUM_COV_UNITS, SP_CLASS_CODE, PR_CLASS_CODE, UNDER_BASIS, DEATH_BEN_OPT_TY, NON_STD_INC_IND, CHG_TARGET_IND, RATE_FACTOR, MALE_TRGT_AGE_ADJ, FM_TRGT_AGE_ADJ, MA_TRGT_RATE_TBL, FM_TRGT_RATE_TBL FROM ";
    
    public void initialize()
    {
        setRowClass(T052ERow.class);
        setTableName("T052E");
        setTableId("052");
        super.initialize();
    }
    
    public void initializeColumnDescriptors()
    {
        super.initializeColumnDescriptors();
        addColumnDescriptor(new ColumnDescriptor(this,"getCompanyCode","setCompanyCode","COMPANY_CODE,1,1,3,0,true|,0,CHAR,1,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getTableSubset","setTableSubset","TABLE_SUBSET,1,2,16,0,true|,0,CHAR,1,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getEffectiveDate","setEffectiveDate","EFFECTIVE_DATE,91,3,10,0,true|,0,DATE,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getMaximumCovUnits","setMaximumCovUnits","MAXIMUM_COV_UNITS,3,4,11,5,true|,0,DOUBLE,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getSpClassCode","setSpClassCode","SP_CLASS_CODE,1,5,2,0,true|,0,CHAR,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getPrClassCode","setPrClassCode","PR_CLASS_CODE,1,6,1,0,true|,0,CHAR,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getUnderBasis","setUnderBasis","UNDER_BASIS,1,7,1,0,true|,0,CHAR,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getDeathBenOptTy","setDeathBenOptTy","DEATH_BEN_OPT_TY,1,8,1,0,true|,0,CHAR,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getNonStdIncInd","setNonStdIncInd","NON_STD_INC_IND,1,9,1,0,false|,0,CHAR,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getChgTargetInd","setChgTargetInd","CHG_TARGET_IND,1,10,1,0,false|,0,CHAR,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getRateFactor","setRateFactor","RATE_FACTOR,3,11,6,5,false|,0,DOUBLE,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getMaleTrgtAgeAdj","setMaleTrgtAgeAdj","MALE_TRGT_AGE_ADJ,3,12,2,0,false|,0,INTEGER,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getFmTrgtAgeAdj","setFmTrgtAgeAdj","FM_TRGT_AGE_ADJ,3,13,2,0,false|,0,INTEGER,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getMaTrgtRateTbl","setMaTrgtRateTbl","MA_TRGT_RATE_TBL,1,14,16,0,false|,0,CHAR,0,053,1,ML,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getFmTrgtRateTbl","setFmTrgtRateTbl","FM_TRGT_RATE_TBL,1,15,16,0,false|,0,CHAR,0,053,2,FM,null,null"));
    }
    
    public String getPagingSQL(String schemaName,boolean isSubsetMode,boolean isNext, boolean locator)
    {
        String pagingWhere = null;
        String order = null;
        if (isNext) {
            if (isSubsetMode)
                if (locator)
                    pagingWhere = ".T052E WHERE (COMPANY_CODE = :company_code) AND (TABLE_SUBSET = :table_subset) AND ((EFFECTIVE_DATE > :effective_date) OR (EFFECTIVE_DATE = :effective_date AND MAXIMUM_COV_UNITS > :maximum_cov_units) OR (EFFECTIVE_DATE = :effective_date AND MAXIMUM_COV_UNITS = :maximum_cov_units AND SP_CLASS_CODE > :sp_class_code) OR (EFFECTIVE_DATE = :effective_date AND MAXIMUM_COV_UNITS = :maximum_cov_units AND SP_CLASS_CODE = :sp_class_code AND PR_CLASS_CODE > :pr_class_code) OR (EFFECTIVE_DATE = :effective_date AND MAXIMUM_COV_UNITS = :maximum_cov_units AND SP_CLASS_CODE = :sp_class_code AND PR_CLASS_CODE = :pr_class_code AND UNDER_BASIS > :under_basis) OR (EFFECTIVE_DATE = :effective_date AND MAXIMUM_COV_UNITS = :maximum_cov_units AND SP_CLASS_CODE = :sp_class_code AND PR_CLASS_CODE = :pr_class_code AND UNDER_BASIS = :under_basis AND DEATH_BEN_OPT_TY > :death_ben_opt_ty) OR (EFFECTIVE_DATE = :effective_date AND MAXIMUM_COV_UNITS = :maximum_cov_units AND SP_CLASS_CODE = :sp_class_code AND PR_CLASS_CODE = :pr_class_code AND UNDER_BASIS = :under_basis AND DEATH_BEN_OPT_TY = :death_ben_opt_ty)) ";
                else 
                    pagingWhere = ".T052E WHERE (COMPANY_CODE = :company_code) AND (TABLE_SUBSET = :table_subset) AND ((EFFECTIVE_DATE > :effective_date) OR (EFFECTIVE_DATE = :effective_date AND MAXIMUM_COV_UNITS > :maximum_cov_units) OR (EFFECTIVE_DATE = :effective_date AND MAXIMUM_COV_UNITS = :maximum_cov_units AND SP_CLASS_CODE > :sp_class_code) OR (EFFECTIVE_DATE = :effective_date AND MAXIMUM_COV_UNITS = :maximum_cov_units AND SP_CLASS_CODE = :sp_class_code AND PR_CLASS_CODE > :pr_class_code) OR (EFFECTIVE_DATE = :effective_date AND MAXIMUM_COV_UNITS = :maximum_cov_units AND SP_CLASS_CODE = :sp_class_code AND PR_CLASS_CODE = :pr_class_code AND UNDER_BASIS > :under_basis) OR (EFFECTIVE_DATE = :effective_date AND MAXIMUM_COV_UNITS = :maximum_cov_units AND SP_CLASS_CODE = :sp_class_code AND PR_CLASS_CODE = :pr_class_code AND UNDER_BASIS = :under_basis AND DEATH_BEN_OPT_TY > :death_ben_opt_ty)) ";
            else
                if (locator)
                    pagingWhere = ".T052E WHERE (COMPANY_CODE = :company_code) AND ((TABLE_SUBSET > :table_subset) OR (TABLE_SUBSET = :table_subset AND EFFECTIVE_DATE > :effective_date) OR (TABLE_SUBSET = :table_subset AND EFFECTIVE_DATE = :effective_date AND MAXIMUM_COV_UNITS > :maximum_cov_units) OR (TABLE_SUBSET = :table_subset AND EFFECTIVE_DATE = :effective_date AND MAXIMUM_COV_UNITS = :maximum_cov_units AND SP_CLASS_CODE > :sp_class_code) OR (TABLE_SUBSET = :table_subset AND EFFECTIVE_DATE = :effective_date AND MAXIMUM_COV_UNITS = :maximum_cov_units AND SP_CLASS_CODE = :sp_class_code AND PR_CLASS_CODE > :pr_class_code) OR (TABLE_SUBSET = :table_subset AND EFFECTIVE_DATE = :effective_date AND MAXIMUM_COV_UNITS = :maximum_cov_units AND SP_CLASS_CODE = :sp_class_code AND PR_CLASS_CODE = :pr_class_code AND UNDER_BASIS > :under_basis) OR (TABLE_SUBSET = :table_subset AND EFFECTIVE_DATE = :effective_date AND MAXIMUM_COV_UNITS = :maximum_cov_units AND SP_CLASS_CODE = :sp_class_code AND PR_CLASS_CODE = :pr_class_code AND UNDER_BASIS = :under_basis AND DEATH_BEN_OPT_TY > :death_ben_opt_ty) OR (TABLE_SUBSET = :table_subset AND EFFECTIVE_DATE = :effective_date AND MAXIMUM_COV_UNITS = :maximum_cov_units AND SP_CLASS_CODE = :sp_class_code AND PR_CLASS_CODE = :pr_class_code AND UNDER_BASIS = :under_basis AND DEATH_BEN_OPT_TY = :death_ben_opt_ty)) ";
                else
                    pagingWhere = ".T052E WHERE (COMPANY_CODE = :company_code) AND ((TABLE_SUBSET > :table_subset) OR (TABLE_SUBSET = :table_subset AND EFFECTIVE_DATE > :effective_date) OR (TABLE_SUBSET = :table_subset AND EFFECTIVE_DATE = :effective_date AND MAXIMUM_COV_UNITS > :maximum_cov_units) OR (TABLE_SUBSET = :table_subset AND EFFECTIVE_DATE = :effective_date AND MAXIMUM_COV_UNITS = :maximum_cov_units AND SP_CLASS_CODE > :sp_class_code) OR (TABLE_SUBSET = :table_subset AND EFFECTIVE_DATE = :effective_date AND MAXIMUM_COV_UNITS = :maximum_cov_units AND SP_CLASS_CODE = :sp_class_code AND PR_CLASS_CODE > :pr_class_code) OR (TABLE_SUBSET = :table_subset AND EFFECTIVE_DATE = :effective_date AND MAXIMUM_COV_UNITS = :maximum_cov_units AND SP_CLASS_CODE = :sp_class_code AND PR_CLASS_CODE = :pr_class_code AND UNDER_BASIS > :under_basis) OR (TABLE_SUBSET = :table_subset AND EFFECTIVE_DATE = :effective_date AND MAXIMUM_COV_UNITS = :maximum_cov_units AND SP_CLASS_CODE = :sp_class_code AND PR_CLASS_CODE = :pr_class_code AND UNDER_BASIS = :under_basis AND DEATH_BEN_OPT_TY > :death_ben_opt_ty)) ";
            order = " ORDER BY COMPANY_CODE, TABLE_SUBSET, EFFECTIVE_DATE, MAXIMUM_COV_UNITS, SP_CLASS_CODE, PR_CLASS_CODE, UNDER_BASIS, DEATH_BEN_OPT_TY";
        }
        else {
            if (isSubsetMode)
                pagingWhere = ".T052E WHERE (COMPANY_CODE = :company_code) AND (TABLE_SUBSET = :table_subset) AND ((EFFECTIVE_DATE < :effective_date) OR (EFFECTIVE_DATE = :effective_date AND MAXIMUM_COV_UNITS < :maximum_cov_units) OR (EFFECTIVE_DATE = :effective_date AND MAXIMUM_COV_UNITS = :maximum_cov_units AND SP_CLASS_CODE < :sp_class_code) OR (EFFECTIVE_DATE = :effective_date AND MAXIMUM_COV_UNITS = :maximum_cov_units AND SP_CLASS_CODE = :sp_class_code AND PR_CLASS_CODE < :pr_class_code) OR (EFFECTIVE_DATE = :effective_date AND MAXIMUM_COV_UNITS = :maximum_cov_units AND SP_CLASS_CODE = :sp_class_code AND PR_CLASS_CODE = :pr_class_code AND UNDER_BASIS < :under_basis) OR (EFFECTIVE_DATE = :effective_date AND MAXIMUM_COV_UNITS = :maximum_cov_units AND SP_CLASS_CODE = :sp_class_code AND PR_CLASS_CODE = :pr_class_code AND UNDER_BASIS = :under_basis AND DEATH_BEN_OPT_TY < :death_ben_opt_ty)) ";
            else
                pagingWhere = ".T052E WHERE (COMPANY_CODE = :company_code) AND ((TABLE_SUBSET < :table_subset) OR (TABLE_SUBSET = :table_subset AND EFFECTIVE_DATE < :effective_date) OR (TABLE_SUBSET = :table_subset AND EFFECTIVE_DATE = :effective_date AND MAXIMUM_COV_UNITS < :maximum_cov_units) OR (TABLE_SUBSET = :table_subset AND EFFECTIVE_DATE = :effective_date AND MAXIMUM_COV_UNITS = :maximum_cov_units AND SP_CLASS_CODE < :sp_class_code) OR (TABLE_SUBSET = :table_subset AND EFFECTIVE_DATE = :effective_date AND MAXIMUM_COV_UNITS = :maximum_cov_units AND SP_CLASS_CODE = :sp_class_code AND PR_CLASS_CODE < :pr_class_code) OR (TABLE_SUBSET = :table_subset AND EFFECTIVE_DATE = :effective_date AND MAXIMUM_COV_UNITS = :maximum_cov_units AND SP_CLASS_CODE = :sp_class_code AND PR_CLASS_CODE = :pr_class_code AND UNDER_BASIS < :under_basis) OR (TABLE_SUBSET = :table_subset AND EFFECTIVE_DATE = :effective_date AND MAXIMUM_COV_UNITS = :maximum_cov_units AND SP_CLASS_CODE = :sp_class_code AND PR_CLASS_CODE = :pr_class_code AND UNDER_BASIS = :under_basis AND DEATH_BEN_OPT_TY < :death_ben_opt_ty)) ";
            order = " ORDER BY COMPANY_CODE DESC, TABLE_SUBSET DESC, EFFECTIVE_DATE DESC, MAXIMUM_COV_UNITS DESC, SP_CLASS_CODE DESC, PR_CLASS_CODE DESC, UNDER_BASIS DESC, DEATH_BEN_OPT_TY DESC";
        }
        return pagingSql + schemaName + pagingWhere + order;
    }
    
    public String prepareInsertStmt(String schemaName) {
        StringBuffer sb = new StringBuffer();
        sb.append("INSERT INTO ").append(schemaName);
        sb.append(".T052E ( ");
        sb.append("COMPANY_CODE, TABLE_SUBSET, EFFECTIVE_DATE, MAXIMUM_COV_UNITS, SP_CLASS_CODE, PR_CLASS_CODE, UNDER_BASIS, DEATH_BEN_OPT_TY, NON_STD_INC_IND, CHG_TARGET_IND, RATE_FACTOR, MALE_TRGT_AGE_ADJ, FM_TRGT_AGE_ADJ, MA_TRGT_RATE_TBL, FM_TRGT_RATE_TBL )");
        sb.append(" VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ? )");
        return sb.toString();
    }
    
    public String prepareUpdateStmt(String schemaName)
    {
        StringBuffer sb = new StringBuffer();
        sb.append("UPDATE ").append(schemaName);
        sb.append(".T052E ");
        sb.append(" SET COMPANY_CODE = ?, TABLE_SUBSET = ?, EFFECTIVE_DATE = ?, MAXIMUM_COV_UNITS = ?, SP_CLASS_CODE = ?, PR_CLASS_CODE = ?, UNDER_BASIS = ?, DEATH_BEN_OPT_TY = ?, NON_STD_INC_IND = ?, CHG_TARGET_IND = ?, RATE_FACTOR = ?, MALE_TRGT_AGE_ADJ = ?, FM_TRGT_AGE_ADJ = ?, MA_TRGT_RATE_TBL = ?, FM_TRGT_RATE_TBL = ?");
        sb.append(" WHERE COMPANY_CODE = ? AND TABLE_SUBSET = ? AND EFFECTIVE_DATE = ? AND MAXIMUM_COV_UNITS = ? AND SP_CLASS_CODE = ? AND PR_CLASS_CODE = ? AND UNDER_BASIS = ? AND DEATH_BEN_OPT_TY = ?");
        return sb.toString();
    }
    
    public String prepareDeleteStmt(String schemaName)
    {
        StringBuffer sb = new StringBuffer();
        sb.append("DELETE FROM ").append(schemaName);
        sb.append(".T052E ");
        sb.append(" WHERE COMPANY_CODE = ? AND TABLE_SUBSET = ? AND EFFECTIVE_DATE = ? AND MAXIMUM_COV_UNITS = ? AND SP_CLASS_CODE = ? AND PR_CLASS_CODE = ? AND UNDER_BASIS = ? AND DEATH_BEN_OPT_TY = ?");
        return sb.toString();
    }
}
