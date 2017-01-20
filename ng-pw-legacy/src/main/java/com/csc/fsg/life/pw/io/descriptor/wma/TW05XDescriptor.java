package com.csc.fsg.life.pw.io.descriptor.wma;

import com.csc.fsg.life.pw.io.*;

public class TW05XDescriptor
    extends TableDescriptor
{
    private static final String pagingSql = "SELECT COMPANY_CODE, PRODUCT_PREFIX, TABLE_SUBSET, STATUTORY_CODE, LOB_CODE, BENEFIT_DESCRIPTOR, EFFECTIVE_DATE, SEX_CODE, MEMO_CODE, FXD_PUR_SER_CD, VAR_PUR_SER_CD, FXD_PYMT_SER_CD, VAR_PYMT_SER_CD, ABL_PUR_SER_CD, ABL_PYMT_SER_CD, ANNUITY_ADJUSTMENT_RIDER FROM ";
    
    public void initialize()
    {
        setRowClass(TW05XRow.class);
        setTableName("TW05X");
        setTableId("W05");
        super.initialize();
    }
    
    public void initializeColumnDescriptors()
    {
        super.initializeColumnDescriptors();
        addColumnDescriptor(new ColumnDescriptor(this,"getCompanyCode","setCompanyCode","COMPANY_CODE,1,1,3,0,true|A,0,CHAR,1,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getProductPrefix","setProductPrefix","PRODUCT_PREFIX,1,2,1,0,true|A,0,CHAR,1,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getTableSubset","setTableSubset","TABLE_SUBSET,1,3,16,0,true|A,0,CHAR,1,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getStatutoryCode","setStatutoryCode","STATUTORY_CODE,1,4,3,0,true|A,0,CHAR,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getLobCode","setLobCode","LOB_CODE,1,5,3,0,true|A,0,CHAR,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getBenefitDescriptor","setBenefitDescriptor","BENEFIT_DESCRIPTOR,1,6,8,0,true|A,0,CHAR,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getEffectiveDate","setEffectiveDate","EFFECTIVE_DATE,91,7,10,0,true|A,0,DATE,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getSexCode","setSexCode","SEX_CODE,1,8,1,0,true|A,0,CHAR,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getMemoCode","setMemoCode","MEMO_CODE,1,9,2,0,true|A,0,CHAR,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getFxdPurSerCd","setFxdPurSerCd","FXD_PUR_SER_CD,1,10,5,0,false|A,0,CHAR,4,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getVarPurSerCd","setVarPurSerCd","VAR_PUR_SER_CD,1,11,5,0,false|A,0,CHAR,4,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getFxdPymtSerCd","setFxdPymtSerCd","FXD_PYMT_SER_CD,1,12,5,0,false|A,0,CHAR,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getVarPymtSerCd","setVarPymtSerCd","VAR_PYMT_SER_CD,1,13,5,0,false|A,0,CHAR,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getAblPurSerCd","setAblPurSerCd","ABL_PUR_SER_CD,1,14,5,0,false|A,0,CHAR,4,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getAblPymtSerCd","setAblPymtSerCd","ABL_PYMT_SER_CD,1,15,5,0,false|A,0,CHAR,4,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getAnnuityAdjustmentRider","setAnnuityAdjustmentRider","ANNUITY_ADJUSTMENT_RIDER,1,16,1,0,false|A,0,CHAR,0,null,null,null,null,null"));
    }
    
    public String getPagingSQL(String schemaName,boolean isSubsetMode,boolean isNext, boolean locator)
    {
        String pagingWhere = null;
        String order = null;
        if (isNext) {
            if (isSubsetMode)
                if (locator)
                    pagingWhere = ".TW05X WHERE (COMPANY_CODE = :company_code) AND (PRODUCT_PREFIX = :product_prefix) AND (TABLE_SUBSET = :table_subset) AND ((STATUTORY_CODE > :statutory_code) OR (STATUTORY_CODE = :statutory_code AND LOB_CODE > :lob_code) OR (STATUTORY_CODE = :statutory_code AND LOB_CODE = :lob_code AND BENEFIT_DESCRIPTOR > :benefit_descriptor) OR (STATUTORY_CODE = :statutory_code AND LOB_CODE = :lob_code AND BENEFIT_DESCRIPTOR = :benefit_descriptor AND EFFECTIVE_DATE > :effective_date) OR (STATUTORY_CODE = :statutory_code AND LOB_CODE = :lob_code AND BENEFIT_DESCRIPTOR = :benefit_descriptor AND EFFECTIVE_DATE = :effective_date AND SEX_CODE > :sex_code) OR (STATUTORY_CODE = :statutory_code AND LOB_CODE = :lob_code AND BENEFIT_DESCRIPTOR = :benefit_descriptor AND EFFECTIVE_DATE = :effective_date AND SEX_CODE = :sex_code AND MEMO_CODE > :memo_code) OR (STATUTORY_CODE = :statutory_code AND LOB_CODE = :lob_code AND BENEFIT_DESCRIPTOR = :benefit_descriptor AND EFFECTIVE_DATE = :effective_date AND SEX_CODE = :sex_code AND MEMO_CODE = :memo_code)) ";
                else 
                    pagingWhere = ".TW05X WHERE (COMPANY_CODE = :company_code) AND (PRODUCT_PREFIX = :product_prefix) AND (TABLE_SUBSET = :table_subset) AND ((STATUTORY_CODE > :statutory_code) OR (STATUTORY_CODE = :statutory_code AND LOB_CODE > :lob_code) OR (STATUTORY_CODE = :statutory_code AND LOB_CODE = :lob_code AND BENEFIT_DESCRIPTOR > :benefit_descriptor) OR (STATUTORY_CODE = :statutory_code AND LOB_CODE = :lob_code AND BENEFIT_DESCRIPTOR = :benefit_descriptor AND EFFECTIVE_DATE > :effective_date) OR (STATUTORY_CODE = :statutory_code AND LOB_CODE = :lob_code AND BENEFIT_DESCRIPTOR = :benefit_descriptor AND EFFECTIVE_DATE = :effective_date AND SEX_CODE > :sex_code) OR (STATUTORY_CODE = :statutory_code AND LOB_CODE = :lob_code AND BENEFIT_DESCRIPTOR = :benefit_descriptor AND EFFECTIVE_DATE = :effective_date AND SEX_CODE = :sex_code AND MEMO_CODE > :memo_code)) ";
            else
                if (locator)
                    pagingWhere = ".TW05X WHERE (COMPANY_CODE = :company_code) AND ((PRODUCT_PREFIX > :product_prefix) OR (PRODUCT_PREFIX = :product_prefix AND TABLE_SUBSET > :table_subset) OR (PRODUCT_PREFIX = :product_prefix AND TABLE_SUBSET = :table_subset AND STATUTORY_CODE > :statutory_code) OR (PRODUCT_PREFIX = :product_prefix AND TABLE_SUBSET = :table_subset AND STATUTORY_CODE = :statutory_code AND LOB_CODE > :lob_code) OR (PRODUCT_PREFIX = :product_prefix AND TABLE_SUBSET = :table_subset AND STATUTORY_CODE = :statutory_code AND LOB_CODE = :lob_code AND BENEFIT_DESCRIPTOR > :benefit_descriptor) OR (PRODUCT_PREFIX = :product_prefix AND TABLE_SUBSET = :table_subset AND STATUTORY_CODE = :statutory_code AND LOB_CODE = :lob_code AND BENEFIT_DESCRIPTOR = :benefit_descriptor AND EFFECTIVE_DATE > :effective_date) OR (PRODUCT_PREFIX = :product_prefix AND TABLE_SUBSET = :table_subset AND STATUTORY_CODE = :statutory_code AND LOB_CODE = :lob_code AND BENEFIT_DESCRIPTOR = :benefit_descriptor AND EFFECTIVE_DATE = :effective_date AND SEX_CODE > :sex_code) OR (PRODUCT_PREFIX = :product_prefix AND TABLE_SUBSET = :table_subset AND STATUTORY_CODE = :statutory_code AND LOB_CODE = :lob_code AND BENEFIT_DESCRIPTOR = :benefit_descriptor AND EFFECTIVE_DATE = :effective_date AND SEX_CODE = :sex_code AND MEMO_CODE > :memo_code) OR (PRODUCT_PREFIX = :product_prefix AND TABLE_SUBSET = :table_subset AND STATUTORY_CODE = :statutory_code AND LOB_CODE = :lob_code AND BENEFIT_DESCRIPTOR = :benefit_descriptor AND EFFECTIVE_DATE = :effective_date AND SEX_CODE = :sex_code AND MEMO_CODE = :memo_code)) ";
                else
                    pagingWhere = ".TW05X WHERE (COMPANY_CODE = :company_code) AND ((PRODUCT_PREFIX > :product_prefix) OR (PRODUCT_PREFIX = :product_prefix AND TABLE_SUBSET > :table_subset) OR (PRODUCT_PREFIX = :product_prefix AND TABLE_SUBSET = :table_subset AND STATUTORY_CODE > :statutory_code) OR (PRODUCT_PREFIX = :product_prefix AND TABLE_SUBSET = :table_subset AND STATUTORY_CODE = :statutory_code AND LOB_CODE > :lob_code) OR (PRODUCT_PREFIX = :product_prefix AND TABLE_SUBSET = :table_subset AND STATUTORY_CODE = :statutory_code AND LOB_CODE = :lob_code AND BENEFIT_DESCRIPTOR > :benefit_descriptor) OR (PRODUCT_PREFIX = :product_prefix AND TABLE_SUBSET = :table_subset AND STATUTORY_CODE = :statutory_code AND LOB_CODE = :lob_code AND BENEFIT_DESCRIPTOR = :benefit_descriptor AND EFFECTIVE_DATE > :effective_date) OR (PRODUCT_PREFIX = :product_prefix AND TABLE_SUBSET = :table_subset AND STATUTORY_CODE = :statutory_code AND LOB_CODE = :lob_code AND BENEFIT_DESCRIPTOR = :benefit_descriptor AND EFFECTIVE_DATE = :effective_date AND SEX_CODE > :sex_code) OR (PRODUCT_PREFIX = :product_prefix AND TABLE_SUBSET = :table_subset AND STATUTORY_CODE = :statutory_code AND LOB_CODE = :lob_code AND BENEFIT_DESCRIPTOR = :benefit_descriptor AND EFFECTIVE_DATE = :effective_date AND SEX_CODE = :sex_code AND MEMO_CODE > :memo_code)) ";
            order = " ORDER BY COMPANY_CODE, PRODUCT_PREFIX, TABLE_SUBSET, STATUTORY_CODE, LOB_CODE, BENEFIT_DESCRIPTOR, EFFECTIVE_DATE, SEX_CODE, MEMO_CODE";
        }
        else {
            if (isSubsetMode)
                pagingWhere = ".TW05X WHERE (COMPANY_CODE = :company_code) AND (PRODUCT_PREFIX = :product_prefix) AND (TABLE_SUBSET = :table_subset) AND ((STATUTORY_CODE < :statutory_code) OR (STATUTORY_CODE = :statutory_code AND LOB_CODE < :lob_code) OR (STATUTORY_CODE = :statutory_code AND LOB_CODE = :lob_code AND BENEFIT_DESCRIPTOR < :benefit_descriptor) OR (STATUTORY_CODE = :statutory_code AND LOB_CODE = :lob_code AND BENEFIT_DESCRIPTOR = :benefit_descriptor AND EFFECTIVE_DATE < :effective_date) OR (STATUTORY_CODE = :statutory_code AND LOB_CODE = :lob_code AND BENEFIT_DESCRIPTOR = :benefit_descriptor AND EFFECTIVE_DATE = :effective_date AND SEX_CODE < :sex_code) OR (STATUTORY_CODE = :statutory_code AND LOB_CODE = :lob_code AND BENEFIT_DESCRIPTOR = :benefit_descriptor AND EFFECTIVE_DATE = :effective_date AND SEX_CODE = :sex_code AND MEMO_CODE < :memo_code)) ";
            else
                pagingWhere = ".TW05X WHERE (COMPANY_CODE = :company_code) AND ((PRODUCT_PREFIX < :product_prefix) OR (PRODUCT_PREFIX = :product_prefix AND TABLE_SUBSET < :table_subset) OR (PRODUCT_PREFIX = :product_prefix AND TABLE_SUBSET = :table_subset AND STATUTORY_CODE < :statutory_code) OR (PRODUCT_PREFIX = :product_prefix AND TABLE_SUBSET = :table_subset AND STATUTORY_CODE = :statutory_code AND LOB_CODE < :lob_code) OR (PRODUCT_PREFIX = :product_prefix AND TABLE_SUBSET = :table_subset AND STATUTORY_CODE = :statutory_code AND LOB_CODE = :lob_code AND BENEFIT_DESCRIPTOR < :benefit_descriptor) OR (PRODUCT_PREFIX = :product_prefix AND TABLE_SUBSET = :table_subset AND STATUTORY_CODE = :statutory_code AND LOB_CODE = :lob_code AND BENEFIT_DESCRIPTOR = :benefit_descriptor AND EFFECTIVE_DATE < :effective_date) OR (PRODUCT_PREFIX = :product_prefix AND TABLE_SUBSET = :table_subset AND STATUTORY_CODE = :statutory_code AND LOB_CODE = :lob_code AND BENEFIT_DESCRIPTOR = :benefit_descriptor AND EFFECTIVE_DATE = :effective_date AND SEX_CODE < :sex_code) OR (PRODUCT_PREFIX = :product_prefix AND TABLE_SUBSET = :table_subset AND STATUTORY_CODE = :statutory_code AND LOB_CODE = :lob_code AND BENEFIT_DESCRIPTOR = :benefit_descriptor AND EFFECTIVE_DATE = :effective_date AND SEX_CODE = :sex_code AND MEMO_CODE < :memo_code)) ";
            order = " ORDER BY COMPANY_CODE DESC, PRODUCT_PREFIX DESC, TABLE_SUBSET DESC, STATUTORY_CODE DESC, LOB_CODE DESC, BENEFIT_DESCRIPTOR DESC, EFFECTIVE_DATE DESC, SEX_CODE DESC, MEMO_CODE DESC";
        }
        return pagingSql + schemaName + pagingWhere + order;
    }
    
    public String prepareInsertStmt(String schemaName) {
        StringBuffer sb = new StringBuffer();
        sb.append("INSERT INTO ").append(schemaName);
        sb.append(".TW05X ( ");
        sb.append("COMPANY_CODE, PRODUCT_PREFIX, TABLE_SUBSET, STATUTORY_CODE, LOB_CODE, BENEFIT_DESCRIPTOR, EFFECTIVE_DATE, SEX_CODE, MEMO_CODE, FXD_PUR_SER_CD, VAR_PUR_SER_CD, FXD_PYMT_SER_CD, VAR_PYMT_SER_CD, ABL_PUR_SER_CD, ABL_PYMT_SER_CD, ANNUITY_ADJUSTMENT_RIDER )");
        sb.append(" VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ? )");
        return sb.toString();
    }
    
    public String prepareUpdateStmt(String schemaName)
    {
        StringBuffer sb = new StringBuffer();
        sb.append("UPDATE ").append(schemaName);
        sb.append(".TW05X ");
        sb.append(" SET COMPANY_CODE = ?, PRODUCT_PREFIX = ?, TABLE_SUBSET = ?, STATUTORY_CODE = ?, LOB_CODE = ?, BENEFIT_DESCRIPTOR = ?, EFFECTIVE_DATE = ?, SEX_CODE = ?, MEMO_CODE = ?, FXD_PUR_SER_CD = ?, VAR_PUR_SER_CD = ?, FXD_PYMT_SER_CD = ?, VAR_PYMT_SER_CD = ?, ABL_PUR_SER_CD = ?, ABL_PYMT_SER_CD = ?, ANNUITY_ADJUSTMENT_RIDER = ?");
        sb.append(" WHERE COMPANY_CODE = ? AND PRODUCT_PREFIX = ? AND TABLE_SUBSET = ? AND STATUTORY_CODE = ? AND LOB_CODE = ? AND BENEFIT_DESCRIPTOR = ? AND EFFECTIVE_DATE = ? AND SEX_CODE = ? AND MEMO_CODE = ?");
        return sb.toString();
    }
    
    public String prepareDeleteStmt(String schemaName)
    {
        StringBuffer sb = new StringBuffer();
        sb.append("DELETE FROM ").append(schemaName);
        sb.append(".TW05X ");
        sb.append(" WHERE COMPANY_CODE = ? AND PRODUCT_PREFIX = ? AND TABLE_SUBSET = ? AND STATUTORY_CODE = ? AND LOB_CODE = ? AND BENEFIT_DESCRIPTOR = ? AND EFFECTIVE_DATE = ? AND SEX_CODE = ? AND MEMO_CODE = ?");
        return sb.toString();
    }
}
