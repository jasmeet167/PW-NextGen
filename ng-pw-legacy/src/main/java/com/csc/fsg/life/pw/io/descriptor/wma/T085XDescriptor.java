package com.csc.fsg.life.pw.io.descriptor.wma;

import com.csc.fsg.life.pw.io.*;

public class T085XDescriptor
    extends TableDescriptor
{
    private static final String pagingSql = "SELECT COMPANY_CODE, PRODUCT_PREFIX, TABLE_SUBSET, EFFECTIVE_DATE, MX_ISSUE_AGE, MX_ATAN_AGE, MX_COV_DUR, SEX_CODE, DTH_BNFT_UNIT_VAL, CON_AMT_PER_UNIT, CON_FAM_SPSE_CPU, CON_FAM_CHILD_CPU, PUAR_IND, PUAR_SCH_MAX_FACE, PUAR_UNSCH_MX_FACE FROM ";
    
    public void initialize()
    {
        setRowClass(T085XRow.class);
        setTableName("T085X");
        setTableId("085");
        super.initialize();
    }
    
    public void initializeColumnDescriptors()
    {
        super.initializeColumnDescriptors();
        addColumnDescriptor(new ColumnDescriptor(this,"getCompanyCode","setCompanyCode","COMPANY_CODE,1,1,3,0,true|T,0,CHAR,1,null,null,null,null,null|U,0,CHAR,1,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getProductPrefix","setProductPrefix","PRODUCT_PREFIX,1,2,1,0,true|T,0,CHAR,1,null,null,null,null,null|U,0,CHAR,1,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getTableSubset","setTableSubset","TABLE_SUBSET,1,3,16,0,true|T,0,CHAR,1,null,null,null,null,null|U,0,CHAR,1,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getEffectiveDate","setEffectiveDate","EFFECTIVE_DATE,91,4,10,0,true|T,0,DATE,0,null,null,null,null,null|U,0,DATE,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getMxIssueAge","setMxIssueAge","MX_ISSUE_AGE,3,5,3,0,true|T,0,INTEGER,0,null,null,null,null,null|U,0,INTEGER,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getMxAtanAge","setMxAtanAge","MX_ATAN_AGE,3,6,3,0,true|T,0,INTEGER,0,null,null,null,null,null|U,0,INTEGER,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getMxCovDur","setMxCovDur","MX_COV_DUR,3,7,4,0,true|T,0,INTEGER,0,null,null,null,null,null|U,0,INTEGER,2,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getSexCode","setSexCode","SEX_CODE,1,8,1,0,true|T,0,CHAR,0,null,null,null,null,null|U,0,CHAR,2,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getDthBnftUnitVal","setDthBnftUnitVal","DTH_BNFT_UNIT_VAL,3,9,11,2,false|T,0,DOUBLE,2,null,null,null,null,null|U,0,DOUBLE,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getConAmtPerUnit","setConAmtPerUnit","CON_AMT_PER_UNIT,3,10,7,2,false|T,0,DOUBLE,0,null,null,null,null,null|U,0,DOUBLE,2,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getConFamSpseCpu","setConFamSpseCpu","CON_FAM_SPSE_CPU,3,11,7,2,false|T,0,DOUBLE,0,null,null,null,null,null|U,0,DOUBLE,2,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getConFamChildCpu","setConFamChildCpu","CON_FAM_CHILD_CPU,3,12,7,2,false|T,0,DOUBLE,0,null,null,null,null,null|U,0,DOUBLE,2,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getPuarInd","setPuarInd","PUAR_IND,1,13,1,0,false|T,0,CHAR,0,null,null,null,null,null|U,0,CHAR,2,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getPuarSchMaxFace","setPuarSchMaxFace","PUAR_SCH_MAX_FACE,3,14,7,2,false|T,0,DOUBLE,0,null,null,null,null,null|U,0,DOUBLE,2,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getPuarUnschMxFace","setPuarUnschMxFace","PUAR_UNSCH_MX_FACE,3,15,7,2,false|T,0,DOUBLE,0,null,null,null,null,null|U,0,DOUBLE,2,null,null,null,null,null"));
    }
    
    public String getPagingSQL(String schemaName,boolean isSubsetMode,boolean isNext, boolean locator)
    {
        String pagingWhere = null;
        String order = null;
        if (isNext) {
            if (isSubsetMode)
                if (locator)
                    pagingWhere = ".T085X WHERE (COMPANY_CODE = :company_code) AND (PRODUCT_PREFIX = :product_prefix) AND (TABLE_SUBSET = :table_subset) AND ((EFFECTIVE_DATE > :effective_date) OR (EFFECTIVE_DATE = :effective_date AND MX_ISSUE_AGE > :mx_issue_age) OR (EFFECTIVE_DATE = :effective_date AND MX_ISSUE_AGE = :mx_issue_age AND MX_ATAN_AGE > :mx_atan_age) OR (EFFECTIVE_DATE = :effective_date AND MX_ISSUE_AGE = :mx_issue_age AND MX_ATAN_AGE = :mx_atan_age AND MX_COV_DUR > :mx_cov_dur) OR (EFFECTIVE_DATE = :effective_date AND MX_ISSUE_AGE = :mx_issue_age AND MX_ATAN_AGE = :mx_atan_age AND MX_COV_DUR = :mx_cov_dur AND SEX_CODE > :sex_code) OR (EFFECTIVE_DATE = :effective_date AND MX_ISSUE_AGE = :mx_issue_age AND MX_ATAN_AGE = :mx_atan_age AND MX_COV_DUR = :mx_cov_dur AND SEX_CODE = :sex_code)) ";
                else 
                    pagingWhere = ".T085X WHERE (COMPANY_CODE = :company_code) AND (PRODUCT_PREFIX = :product_prefix) AND (TABLE_SUBSET = :table_subset) AND ((EFFECTIVE_DATE > :effective_date) OR (EFFECTIVE_DATE = :effective_date AND MX_ISSUE_AGE > :mx_issue_age) OR (EFFECTIVE_DATE = :effective_date AND MX_ISSUE_AGE = :mx_issue_age AND MX_ATAN_AGE > :mx_atan_age) OR (EFFECTIVE_DATE = :effective_date AND MX_ISSUE_AGE = :mx_issue_age AND MX_ATAN_AGE = :mx_atan_age AND MX_COV_DUR > :mx_cov_dur) OR (EFFECTIVE_DATE = :effective_date AND MX_ISSUE_AGE = :mx_issue_age AND MX_ATAN_AGE = :mx_atan_age AND MX_COV_DUR = :mx_cov_dur AND SEX_CODE > :sex_code)) ";
            else
                if (locator)
                    pagingWhere = ".T085X WHERE (COMPANY_CODE = :company_code) AND ((PRODUCT_PREFIX > :product_prefix) OR (PRODUCT_PREFIX = :product_prefix AND TABLE_SUBSET > :table_subset) OR (PRODUCT_PREFIX = :product_prefix AND TABLE_SUBSET = :table_subset AND EFFECTIVE_DATE > :effective_date) OR (PRODUCT_PREFIX = :product_prefix AND TABLE_SUBSET = :table_subset AND EFFECTIVE_DATE = :effective_date AND MX_ISSUE_AGE > :mx_issue_age) OR (PRODUCT_PREFIX = :product_prefix AND TABLE_SUBSET = :table_subset AND EFFECTIVE_DATE = :effective_date AND MX_ISSUE_AGE = :mx_issue_age AND MX_ATAN_AGE > :mx_atan_age) OR (PRODUCT_PREFIX = :product_prefix AND TABLE_SUBSET = :table_subset AND EFFECTIVE_DATE = :effective_date AND MX_ISSUE_AGE = :mx_issue_age AND MX_ATAN_AGE = :mx_atan_age AND MX_COV_DUR > :mx_cov_dur) OR (PRODUCT_PREFIX = :product_prefix AND TABLE_SUBSET = :table_subset AND EFFECTIVE_DATE = :effective_date AND MX_ISSUE_AGE = :mx_issue_age AND MX_ATAN_AGE = :mx_atan_age AND MX_COV_DUR = :mx_cov_dur AND SEX_CODE > :sex_code) OR (PRODUCT_PREFIX = :product_prefix AND TABLE_SUBSET = :table_subset AND EFFECTIVE_DATE = :effective_date AND MX_ISSUE_AGE = :mx_issue_age AND MX_ATAN_AGE = :mx_atan_age AND MX_COV_DUR = :mx_cov_dur AND SEX_CODE = :sex_code)) ";
                else
                    pagingWhere = ".T085X WHERE (COMPANY_CODE = :company_code) AND ((PRODUCT_PREFIX > :product_prefix) OR (PRODUCT_PREFIX = :product_prefix AND TABLE_SUBSET > :table_subset) OR (PRODUCT_PREFIX = :product_prefix AND TABLE_SUBSET = :table_subset AND EFFECTIVE_DATE > :effective_date) OR (PRODUCT_PREFIX = :product_prefix AND TABLE_SUBSET = :table_subset AND EFFECTIVE_DATE = :effective_date AND MX_ISSUE_AGE > :mx_issue_age) OR (PRODUCT_PREFIX = :product_prefix AND TABLE_SUBSET = :table_subset AND EFFECTIVE_DATE = :effective_date AND MX_ISSUE_AGE = :mx_issue_age AND MX_ATAN_AGE > :mx_atan_age) OR (PRODUCT_PREFIX = :product_prefix AND TABLE_SUBSET = :table_subset AND EFFECTIVE_DATE = :effective_date AND MX_ISSUE_AGE = :mx_issue_age AND MX_ATAN_AGE = :mx_atan_age AND MX_COV_DUR > :mx_cov_dur) OR (PRODUCT_PREFIX = :product_prefix AND TABLE_SUBSET = :table_subset AND EFFECTIVE_DATE = :effective_date AND MX_ISSUE_AGE = :mx_issue_age AND MX_ATAN_AGE = :mx_atan_age AND MX_COV_DUR = :mx_cov_dur AND SEX_CODE > :sex_code)) ";
            order = " ORDER BY COMPANY_CODE, PRODUCT_PREFIX, TABLE_SUBSET, EFFECTIVE_DATE, MX_ISSUE_AGE, MX_ATAN_AGE, MX_COV_DUR, SEX_CODE";
        }
        else {
            if (isSubsetMode)
                pagingWhere = ".T085X WHERE (COMPANY_CODE = :company_code) AND (PRODUCT_PREFIX = :product_prefix) AND (TABLE_SUBSET = :table_subset) AND ((EFFECTIVE_DATE < :effective_date) OR (EFFECTIVE_DATE = :effective_date AND MX_ISSUE_AGE < :mx_issue_age) OR (EFFECTIVE_DATE = :effective_date AND MX_ISSUE_AGE = :mx_issue_age AND MX_ATAN_AGE < :mx_atan_age) OR (EFFECTIVE_DATE = :effective_date AND MX_ISSUE_AGE = :mx_issue_age AND MX_ATAN_AGE = :mx_atan_age AND MX_COV_DUR < :mx_cov_dur) OR (EFFECTIVE_DATE = :effective_date AND MX_ISSUE_AGE = :mx_issue_age AND MX_ATAN_AGE = :mx_atan_age AND MX_COV_DUR = :mx_cov_dur AND SEX_CODE < :sex_code)) ";
            else
                pagingWhere = ".T085X WHERE (COMPANY_CODE = :company_code) AND ((PRODUCT_PREFIX < :product_prefix) OR (PRODUCT_PREFIX = :product_prefix AND TABLE_SUBSET < :table_subset) OR (PRODUCT_PREFIX = :product_prefix AND TABLE_SUBSET = :table_subset AND EFFECTIVE_DATE < :effective_date) OR (PRODUCT_PREFIX = :product_prefix AND TABLE_SUBSET = :table_subset AND EFFECTIVE_DATE = :effective_date AND MX_ISSUE_AGE < :mx_issue_age) OR (PRODUCT_PREFIX = :product_prefix AND TABLE_SUBSET = :table_subset AND EFFECTIVE_DATE = :effective_date AND MX_ISSUE_AGE = :mx_issue_age AND MX_ATAN_AGE < :mx_atan_age) OR (PRODUCT_PREFIX = :product_prefix AND TABLE_SUBSET = :table_subset AND EFFECTIVE_DATE = :effective_date AND MX_ISSUE_AGE = :mx_issue_age AND MX_ATAN_AGE = :mx_atan_age AND MX_COV_DUR < :mx_cov_dur) OR (PRODUCT_PREFIX = :product_prefix AND TABLE_SUBSET = :table_subset AND EFFECTIVE_DATE = :effective_date AND MX_ISSUE_AGE = :mx_issue_age AND MX_ATAN_AGE = :mx_atan_age AND MX_COV_DUR = :mx_cov_dur AND SEX_CODE < :sex_code)) ";
            order = " ORDER BY COMPANY_CODE DESC, PRODUCT_PREFIX DESC, TABLE_SUBSET DESC, EFFECTIVE_DATE DESC, MX_ISSUE_AGE DESC, MX_ATAN_AGE DESC, MX_COV_DUR DESC, SEX_CODE DESC";
        }
        return pagingSql + schemaName + pagingWhere + order;
    }
    
    public String prepareInsertStmt(String schemaName) {
        StringBuffer sb = new StringBuffer();
        sb.append("INSERT INTO ").append(schemaName);
        sb.append(".T085X ( ");
        sb.append("COMPANY_CODE, PRODUCT_PREFIX, TABLE_SUBSET, EFFECTIVE_DATE, MX_ISSUE_AGE, MX_ATAN_AGE, MX_COV_DUR, SEX_CODE, DTH_BNFT_UNIT_VAL, CON_AMT_PER_UNIT, CON_FAM_SPSE_CPU, CON_FAM_CHILD_CPU, PUAR_IND, PUAR_SCH_MAX_FACE, PUAR_UNSCH_MX_FACE )");
        sb.append(" VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ? )");
        return sb.toString();
    }
    
    public String prepareUpdateStmt(String schemaName)
    {
        StringBuffer sb = new StringBuffer();
        sb.append("UPDATE ").append(schemaName);
        sb.append(".T085X ");
        sb.append(" SET COMPANY_CODE = ?, PRODUCT_PREFIX = ?, TABLE_SUBSET = ?, EFFECTIVE_DATE = ?, MX_ISSUE_AGE = ?, MX_ATAN_AGE = ?, MX_COV_DUR = ?, SEX_CODE = ?, DTH_BNFT_UNIT_VAL = ?, CON_AMT_PER_UNIT = ?, CON_FAM_SPSE_CPU = ?, CON_FAM_CHILD_CPU = ?, PUAR_IND = ?, PUAR_SCH_MAX_FACE = ?, PUAR_UNSCH_MX_FACE = ?");
        sb.append(" WHERE COMPANY_CODE = ? AND PRODUCT_PREFIX = ? AND TABLE_SUBSET = ? AND EFFECTIVE_DATE = ? AND MX_ISSUE_AGE = ? AND MX_ATAN_AGE = ? AND MX_COV_DUR = ? AND SEX_CODE = ?");
        return sb.toString();
    }
    
    public String prepareDeleteStmt(String schemaName)
    {
        StringBuffer sb = new StringBuffer();
        sb.append("DELETE FROM ").append(schemaName);
        sb.append(".T085X ");
        sb.append(" WHERE COMPANY_CODE = ? AND PRODUCT_PREFIX = ? AND TABLE_SUBSET = ? AND EFFECTIVE_DATE = ? AND MX_ISSUE_AGE = ? AND MX_ATAN_AGE = ? AND MX_COV_DUR = ? AND SEX_CODE = ?");
        return sb.toString();
    }
}
