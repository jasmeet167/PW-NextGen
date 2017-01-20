package com.csc.fsg.life.pw.io.descriptor.wma;

import com.csc.fsg.life.pw.io.*;

public class T062EDescriptor
    extends TableDescriptor
{
    private static final String pagingSql = "SELECT COMPANY_CODE, TABLE_SUBSET, EFFECTIVE_DATE, MAX_BASE_CVG_UNITS, SEX, PREMIUM_CLASS_CODE, SPECIAL_CLASS_CODE, SEQ_NO, MAX_AMOUNT, INTEREST_RATE_SUBSET FROM ";
    
    public void initialize()
    {
        setRowClass(T062ERow.class);
        setTableName("T062E");
        setTableId("062");
        super.initialize();
    }
    
    public void initializeColumnDescriptors()
    {
        super.initializeColumnDescriptors();
        addColumnDescriptor(new ColumnDescriptor(this,"getCompanyCode","setCompanyCode","COMPANY_CODE,1,1,3,0,true|,0,CHAR,1,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getTableSubset","setTableSubset","TABLE_SUBSET,1,2,16,0,true|,0,CHAR,1,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getEffectiveDate","setEffectiveDate","EFFECTIVE_DATE,91,3,10,0,true|,0,DATE,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getMaxBaseCvgUnits","setMaxBaseCvgUnits","MAX_BASE_CVG_UNITS,3,4,11,5,true|,0,DOUBLE,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getSex","setSex","SEX,1,5,1,0,true|,0,CHAR,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getPremiumClassCode","setPremiumClassCode","PREMIUM_CLASS_CODE,1,6,1,0,true|,0,CHAR,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getSpecialClassCode","setSpecialClassCode","SPECIAL_CLASS_CODE,1,7,2,0,true|,0,CHAR,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getSeqNo","setSeqNo","SEQ_NO,3,8,3,0,true|,0,INTEGER,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getMaxAmount","setMaxAmount","MAX_AMOUNT,3,9,8,0,true|,0,INTEGER,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getInterestRateSubset","setInterestRateSubset","INTEREST_RATE_SUBSET,1,10,16,0,false|,0,CHAR,0,063,null,null,null,null"));
    }
    
    public String getPagingSQL(String schemaName,boolean isSubsetMode,boolean isNext, boolean locator)
    {
        String pagingWhere = null;
        String order = null;
        if (isNext) {
            if (isSubsetMode)
                if (locator)
                    pagingWhere = ".T062E WHERE (COMPANY_CODE = :company_code) AND (TABLE_SUBSET = :table_subset) AND ((EFFECTIVE_DATE > :effective_date) OR (EFFECTIVE_DATE = :effective_date AND MAX_BASE_CVG_UNITS > :max_base_cvg_units) OR (EFFECTIVE_DATE = :effective_date AND MAX_BASE_CVG_UNITS = :max_base_cvg_units AND SEX > :sex) OR (EFFECTIVE_DATE = :effective_date AND MAX_BASE_CVG_UNITS = :max_base_cvg_units AND SEX = :sex AND PREMIUM_CLASS_CODE > :premium_class_code) OR (EFFECTIVE_DATE = :effective_date AND MAX_BASE_CVG_UNITS = :max_base_cvg_units AND SEX = :sex AND PREMIUM_CLASS_CODE = :premium_class_code AND SPECIAL_CLASS_CODE > :special_class_code) OR (EFFECTIVE_DATE = :effective_date AND MAX_BASE_CVG_UNITS = :max_base_cvg_units AND SEX = :sex AND PREMIUM_CLASS_CODE = :premium_class_code AND SPECIAL_CLASS_CODE = :special_class_code AND SEQ_NO > :seq_no) OR (EFFECTIVE_DATE = :effective_date AND MAX_BASE_CVG_UNITS = :max_base_cvg_units AND SEX = :sex AND PREMIUM_CLASS_CODE = :premium_class_code AND SPECIAL_CLASS_CODE = :special_class_code AND SEQ_NO = :seq_no AND MAX_AMOUNT > :max_amount) OR (EFFECTIVE_DATE = :effective_date AND MAX_BASE_CVG_UNITS = :max_base_cvg_units AND SEX = :sex AND PREMIUM_CLASS_CODE = :premium_class_code AND SPECIAL_CLASS_CODE = :special_class_code AND SEQ_NO = :seq_no AND MAX_AMOUNT = :max_amount)) ";
                else 
                    pagingWhere = ".T062E WHERE (COMPANY_CODE = :company_code) AND (TABLE_SUBSET = :table_subset) AND ((EFFECTIVE_DATE > :effective_date) OR (EFFECTIVE_DATE = :effective_date AND MAX_BASE_CVG_UNITS > :max_base_cvg_units) OR (EFFECTIVE_DATE = :effective_date AND MAX_BASE_CVG_UNITS = :max_base_cvg_units AND SEX > :sex) OR (EFFECTIVE_DATE = :effective_date AND MAX_BASE_CVG_UNITS = :max_base_cvg_units AND SEX = :sex AND PREMIUM_CLASS_CODE > :premium_class_code) OR (EFFECTIVE_DATE = :effective_date AND MAX_BASE_CVG_UNITS = :max_base_cvg_units AND SEX = :sex AND PREMIUM_CLASS_CODE = :premium_class_code AND SPECIAL_CLASS_CODE > :special_class_code) OR (EFFECTIVE_DATE = :effective_date AND MAX_BASE_CVG_UNITS = :max_base_cvg_units AND SEX = :sex AND PREMIUM_CLASS_CODE = :premium_class_code AND SPECIAL_CLASS_CODE = :special_class_code AND SEQ_NO > :seq_no) OR (EFFECTIVE_DATE = :effective_date AND MAX_BASE_CVG_UNITS = :max_base_cvg_units AND SEX = :sex AND PREMIUM_CLASS_CODE = :premium_class_code AND SPECIAL_CLASS_CODE = :special_class_code AND SEQ_NO = :seq_no AND MAX_AMOUNT > :max_amount)) ";
            else
                if (locator)
                    pagingWhere = ".T062E WHERE (COMPANY_CODE = :company_code) AND ((TABLE_SUBSET > :table_subset) OR (TABLE_SUBSET = :table_subset AND EFFECTIVE_DATE > :effective_date) OR (TABLE_SUBSET = :table_subset AND EFFECTIVE_DATE = :effective_date AND MAX_BASE_CVG_UNITS > :max_base_cvg_units) OR (TABLE_SUBSET = :table_subset AND EFFECTIVE_DATE = :effective_date AND MAX_BASE_CVG_UNITS = :max_base_cvg_units AND SEX > :sex) OR (TABLE_SUBSET = :table_subset AND EFFECTIVE_DATE = :effective_date AND MAX_BASE_CVG_UNITS = :max_base_cvg_units AND SEX = :sex AND PREMIUM_CLASS_CODE > :premium_class_code) OR (TABLE_SUBSET = :table_subset AND EFFECTIVE_DATE = :effective_date AND MAX_BASE_CVG_UNITS = :max_base_cvg_units AND SEX = :sex AND PREMIUM_CLASS_CODE = :premium_class_code AND SPECIAL_CLASS_CODE > :special_class_code) OR (TABLE_SUBSET = :table_subset AND EFFECTIVE_DATE = :effective_date AND MAX_BASE_CVG_UNITS = :max_base_cvg_units AND SEX = :sex AND PREMIUM_CLASS_CODE = :premium_class_code AND SPECIAL_CLASS_CODE = :special_class_code AND SEQ_NO > :seq_no) OR (TABLE_SUBSET = :table_subset AND EFFECTIVE_DATE = :effective_date AND MAX_BASE_CVG_UNITS = :max_base_cvg_units AND SEX = :sex AND PREMIUM_CLASS_CODE = :premium_class_code AND SPECIAL_CLASS_CODE = :special_class_code AND SEQ_NO = :seq_no AND MAX_AMOUNT > :max_amount) OR (TABLE_SUBSET = :table_subset AND EFFECTIVE_DATE = :effective_date AND MAX_BASE_CVG_UNITS = :max_base_cvg_units AND SEX = :sex AND PREMIUM_CLASS_CODE = :premium_class_code AND SPECIAL_CLASS_CODE = :special_class_code AND SEQ_NO = :seq_no AND MAX_AMOUNT = :max_amount)) ";
                else
                    pagingWhere = ".T062E WHERE (COMPANY_CODE = :company_code) AND ((TABLE_SUBSET > :table_subset) OR (TABLE_SUBSET = :table_subset AND EFFECTIVE_DATE > :effective_date) OR (TABLE_SUBSET = :table_subset AND EFFECTIVE_DATE = :effective_date AND MAX_BASE_CVG_UNITS > :max_base_cvg_units) OR (TABLE_SUBSET = :table_subset AND EFFECTIVE_DATE = :effective_date AND MAX_BASE_CVG_UNITS = :max_base_cvg_units AND SEX > :sex) OR (TABLE_SUBSET = :table_subset AND EFFECTIVE_DATE = :effective_date AND MAX_BASE_CVG_UNITS = :max_base_cvg_units AND SEX = :sex AND PREMIUM_CLASS_CODE > :premium_class_code) OR (TABLE_SUBSET = :table_subset AND EFFECTIVE_DATE = :effective_date AND MAX_BASE_CVG_UNITS = :max_base_cvg_units AND SEX = :sex AND PREMIUM_CLASS_CODE = :premium_class_code AND SPECIAL_CLASS_CODE > :special_class_code) OR (TABLE_SUBSET = :table_subset AND EFFECTIVE_DATE = :effective_date AND MAX_BASE_CVG_UNITS = :max_base_cvg_units AND SEX = :sex AND PREMIUM_CLASS_CODE = :premium_class_code AND SPECIAL_CLASS_CODE = :special_class_code AND SEQ_NO > :seq_no) OR (TABLE_SUBSET = :table_subset AND EFFECTIVE_DATE = :effective_date AND MAX_BASE_CVG_UNITS = :max_base_cvg_units AND SEX = :sex AND PREMIUM_CLASS_CODE = :premium_class_code AND SPECIAL_CLASS_CODE = :special_class_code AND SEQ_NO = :seq_no AND MAX_AMOUNT > :max_amount)) ";
            order = " ORDER BY COMPANY_CODE, TABLE_SUBSET, EFFECTIVE_DATE, MAX_BASE_CVG_UNITS, SEX, PREMIUM_CLASS_CODE, SPECIAL_CLASS_CODE, SEQ_NO, MAX_AMOUNT";
        }
        else {
            if (isSubsetMode)
                pagingWhere = ".T062E WHERE (COMPANY_CODE = :company_code) AND (TABLE_SUBSET = :table_subset) AND ((EFFECTIVE_DATE < :effective_date) OR (EFFECTIVE_DATE = :effective_date AND MAX_BASE_CVG_UNITS < :max_base_cvg_units) OR (EFFECTIVE_DATE = :effective_date AND MAX_BASE_CVG_UNITS = :max_base_cvg_units AND SEX < :sex) OR (EFFECTIVE_DATE = :effective_date AND MAX_BASE_CVG_UNITS = :max_base_cvg_units AND SEX = :sex AND PREMIUM_CLASS_CODE < :premium_class_code) OR (EFFECTIVE_DATE = :effective_date AND MAX_BASE_CVG_UNITS = :max_base_cvg_units AND SEX = :sex AND PREMIUM_CLASS_CODE = :premium_class_code AND SPECIAL_CLASS_CODE < :special_class_code) OR (EFFECTIVE_DATE = :effective_date AND MAX_BASE_CVG_UNITS = :max_base_cvg_units AND SEX = :sex AND PREMIUM_CLASS_CODE = :premium_class_code AND SPECIAL_CLASS_CODE = :special_class_code AND SEQ_NO < :seq_no) OR (EFFECTIVE_DATE = :effective_date AND MAX_BASE_CVG_UNITS = :max_base_cvg_units AND SEX = :sex AND PREMIUM_CLASS_CODE = :premium_class_code AND SPECIAL_CLASS_CODE = :special_class_code AND SEQ_NO = :seq_no AND MAX_AMOUNT < :max_amount)) ";
            else
                pagingWhere = ".T062E WHERE (COMPANY_CODE = :company_code) AND ((TABLE_SUBSET < :table_subset) OR (TABLE_SUBSET = :table_subset AND EFFECTIVE_DATE < :effective_date) OR (TABLE_SUBSET = :table_subset AND EFFECTIVE_DATE = :effective_date AND MAX_BASE_CVG_UNITS < :max_base_cvg_units) OR (TABLE_SUBSET = :table_subset AND EFFECTIVE_DATE = :effective_date AND MAX_BASE_CVG_UNITS = :max_base_cvg_units AND SEX < :sex) OR (TABLE_SUBSET = :table_subset AND EFFECTIVE_DATE = :effective_date AND MAX_BASE_CVG_UNITS = :max_base_cvg_units AND SEX = :sex AND PREMIUM_CLASS_CODE < :premium_class_code) OR (TABLE_SUBSET = :table_subset AND EFFECTIVE_DATE = :effective_date AND MAX_BASE_CVG_UNITS = :max_base_cvg_units AND SEX = :sex AND PREMIUM_CLASS_CODE = :premium_class_code AND SPECIAL_CLASS_CODE < :special_class_code) OR (TABLE_SUBSET = :table_subset AND EFFECTIVE_DATE = :effective_date AND MAX_BASE_CVG_UNITS = :max_base_cvg_units AND SEX = :sex AND PREMIUM_CLASS_CODE = :premium_class_code AND SPECIAL_CLASS_CODE = :special_class_code AND SEQ_NO < :seq_no) OR (TABLE_SUBSET = :table_subset AND EFFECTIVE_DATE = :effective_date AND MAX_BASE_CVG_UNITS = :max_base_cvg_units AND SEX = :sex AND PREMIUM_CLASS_CODE = :premium_class_code AND SPECIAL_CLASS_CODE = :special_class_code AND SEQ_NO = :seq_no AND MAX_AMOUNT < :max_amount)) ";
            order = " ORDER BY COMPANY_CODE DESC, TABLE_SUBSET DESC, EFFECTIVE_DATE DESC, MAX_BASE_CVG_UNITS DESC, SEX DESC, PREMIUM_CLASS_CODE DESC, SPECIAL_CLASS_CODE DESC, SEQ_NO DESC, MAX_AMOUNT DESC";
        }
        return pagingSql + schemaName + pagingWhere + order;
    }
    
    public String prepareInsertStmt(String schemaName) {
        StringBuffer sb = new StringBuffer();
        sb.append("INSERT INTO ").append(schemaName);
        sb.append(".T062E ( ");
        sb.append("COMPANY_CODE, TABLE_SUBSET, EFFECTIVE_DATE, MAX_BASE_CVG_UNITS, SEX, PREMIUM_CLASS_CODE, SPECIAL_CLASS_CODE, SEQ_NO, MAX_AMOUNT, INTEREST_RATE_SUBSET )");
        sb.append(" VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ? )");
        return sb.toString();
    }
    
    public String prepareUpdateStmt(String schemaName)
    {
        StringBuffer sb = new StringBuffer();
        sb.append("UPDATE ").append(schemaName);
        sb.append(".T062E ");
        sb.append(" SET COMPANY_CODE = ?, TABLE_SUBSET = ?, EFFECTIVE_DATE = ?, MAX_BASE_CVG_UNITS = ?, SEX = ?, PREMIUM_CLASS_CODE = ?, SPECIAL_CLASS_CODE = ?, SEQ_NO = ?, MAX_AMOUNT = ?, INTEREST_RATE_SUBSET = ?");
        sb.append(" WHERE COMPANY_CODE = ? AND TABLE_SUBSET = ? AND EFFECTIVE_DATE = ? AND MAX_BASE_CVG_UNITS = ? AND SEX = ? AND PREMIUM_CLASS_CODE = ? AND SPECIAL_CLASS_CODE = ? AND SEQ_NO = ? AND MAX_AMOUNT = ?");
        return sb.toString();
    }
    
    public String prepareDeleteStmt(String schemaName)
    {
        StringBuffer sb = new StringBuffer();
        sb.append("DELETE FROM ").append(schemaName);
        sb.append(".T062E ");
        sb.append(" WHERE COMPANY_CODE = ? AND TABLE_SUBSET = ? AND EFFECTIVE_DATE = ? AND MAX_BASE_CVG_UNITS = ? AND SEX = ? AND PREMIUM_CLASS_CODE = ? AND SPECIAL_CLASS_CODE = ? AND SEQ_NO = ? AND MAX_AMOUNT = ?");
        return sb.toString();
    }
}
