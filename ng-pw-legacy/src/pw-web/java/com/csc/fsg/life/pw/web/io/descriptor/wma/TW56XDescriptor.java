package com.csc.fsg.life.pw.web.io.descriptor.wma;

import com.csc.fsg.life.pw.web.io.*;

public class TW56XDescriptor
    extends TableDescriptor
{
    private static final String pagingSql = "SELECT COMPANY_CODE, PRODUCT_PREFIX, TABLE_SUBSET, PURCH_SERIES_CODE, EFFECTIVE_DATE, SEX_CODE, BIRTH_YEAR, SETBACK_YEARS FROM ";
    
    public void initialize()
    {
        setRowClass(TW56XRow.class);
        setTableName("TW56X");
        setTableId("W56");
        super.initialize();
    }
    
    public void initializeColumnDescriptors()
    {
        super.initializeColumnDescriptors();
        addColumnDescriptor(new ColumnDescriptor(this,"getCompanyCode","setCompanyCode","COMPANY_CODE,1,1,3,0,true|A,0,CHAR,1,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getProductPrefix","setProductPrefix","PRODUCT_PREFIX,1,2,1,0,true|A,0,CHAR,1,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getTableSubset","setTableSubset","TABLE_SUBSET,1,3,16,0,true|A,0,CHAR,1,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getPurchSeriesCode","setPurchSeriesCode","PURCH_SERIES_CODE,1,4,5,0,true|A,0,CHAR,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getEffectiveDate","setEffectiveDate","EFFECTIVE_DATE,91,5,10,0,true|A,0,DATE,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getSexCode","setSexCode","SEX_CODE,1,6,1,0,true|A,0,CHAR,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getBirthYear","setBirthYear","BIRTH_YEAR,3,7,4,0,true|A,0,INTEGER,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getSetbackYears","setSetbackYears","SETBACK_YEARS,3,8,3,0,false|A,0,INTEGER,0,null,null,null,null,null"));
    }
    
    public String getPagingSQL(String schemaName,boolean isSubsetMode,boolean isNext, boolean locator)
    {
        String pagingWhere = null;
        String order = null;
        if (isNext) {
            if (isSubsetMode)
                if (locator)
                    pagingWhere = ".TW56X WHERE (COMPANY_CODE = :company_code) AND (PRODUCT_PREFIX = :product_prefix) AND (TABLE_SUBSET = :table_subset) AND ((PURCH_SERIES_CODE > :purch_series_code) OR (PURCH_SERIES_CODE = :purch_series_code AND EFFECTIVE_DATE > :effective_date) OR (PURCH_SERIES_CODE = :purch_series_code AND EFFECTIVE_DATE = :effective_date AND SEX_CODE > :sex_code) OR (PURCH_SERIES_CODE = :purch_series_code AND EFFECTIVE_DATE = :effective_date AND SEX_CODE = :sex_code AND BIRTH_YEAR > :birth_year) OR (PURCH_SERIES_CODE = :purch_series_code AND EFFECTIVE_DATE = :effective_date AND SEX_CODE = :sex_code AND BIRTH_YEAR = :birth_year)) ";
                else 
                    pagingWhere = ".TW56X WHERE (COMPANY_CODE = :company_code) AND (PRODUCT_PREFIX = :product_prefix) AND (TABLE_SUBSET = :table_subset) AND ((PURCH_SERIES_CODE > :purch_series_code) OR (PURCH_SERIES_CODE = :purch_series_code AND EFFECTIVE_DATE > :effective_date) OR (PURCH_SERIES_CODE = :purch_series_code AND EFFECTIVE_DATE = :effective_date AND SEX_CODE > :sex_code) OR (PURCH_SERIES_CODE = :purch_series_code AND EFFECTIVE_DATE = :effective_date AND SEX_CODE = :sex_code AND BIRTH_YEAR > :birth_year)) ";
            else
                if (locator)
                    pagingWhere = ".TW56X WHERE (COMPANY_CODE = :company_code) AND ((PRODUCT_PREFIX > :product_prefix) OR (PRODUCT_PREFIX = :product_prefix AND TABLE_SUBSET > :table_subset) OR (PRODUCT_PREFIX = :product_prefix AND TABLE_SUBSET = :table_subset AND PURCH_SERIES_CODE > :purch_series_code) OR (PRODUCT_PREFIX = :product_prefix AND TABLE_SUBSET = :table_subset AND PURCH_SERIES_CODE = :purch_series_code AND EFFECTIVE_DATE > :effective_date) OR (PRODUCT_PREFIX = :product_prefix AND TABLE_SUBSET = :table_subset AND PURCH_SERIES_CODE = :purch_series_code AND EFFECTIVE_DATE = :effective_date AND SEX_CODE > :sex_code) OR (PRODUCT_PREFIX = :product_prefix AND TABLE_SUBSET = :table_subset AND PURCH_SERIES_CODE = :purch_series_code AND EFFECTIVE_DATE = :effective_date AND SEX_CODE = :sex_code AND BIRTH_YEAR > :birth_year) OR (PRODUCT_PREFIX = :product_prefix AND TABLE_SUBSET = :table_subset AND PURCH_SERIES_CODE = :purch_series_code AND EFFECTIVE_DATE = :effective_date AND SEX_CODE = :sex_code AND BIRTH_YEAR = :birth_year)) ";
                else
                    pagingWhere = ".TW56X WHERE (COMPANY_CODE = :company_code) AND ((PRODUCT_PREFIX > :product_prefix) OR (PRODUCT_PREFIX = :product_prefix AND TABLE_SUBSET > :table_subset) OR (PRODUCT_PREFIX = :product_prefix AND TABLE_SUBSET = :table_subset AND PURCH_SERIES_CODE > :purch_series_code) OR (PRODUCT_PREFIX = :product_prefix AND TABLE_SUBSET = :table_subset AND PURCH_SERIES_CODE = :purch_series_code AND EFFECTIVE_DATE > :effective_date) OR (PRODUCT_PREFIX = :product_prefix AND TABLE_SUBSET = :table_subset AND PURCH_SERIES_CODE = :purch_series_code AND EFFECTIVE_DATE = :effective_date AND SEX_CODE > :sex_code) OR (PRODUCT_PREFIX = :product_prefix AND TABLE_SUBSET = :table_subset AND PURCH_SERIES_CODE = :purch_series_code AND EFFECTIVE_DATE = :effective_date AND SEX_CODE = :sex_code AND BIRTH_YEAR > :birth_year)) ";
            order = " ORDER BY COMPANY_CODE, PRODUCT_PREFIX, TABLE_SUBSET, PURCH_SERIES_CODE, EFFECTIVE_DATE, SEX_CODE, BIRTH_YEAR";
        }
        else {
            if (isSubsetMode)
                pagingWhere = ".TW56X WHERE (COMPANY_CODE = :company_code) AND (PRODUCT_PREFIX = :product_prefix) AND (TABLE_SUBSET = :table_subset) AND ((PURCH_SERIES_CODE < :purch_series_code) OR (PURCH_SERIES_CODE = :purch_series_code AND EFFECTIVE_DATE < :effective_date) OR (PURCH_SERIES_CODE = :purch_series_code AND EFFECTIVE_DATE = :effective_date AND SEX_CODE < :sex_code) OR (PURCH_SERIES_CODE = :purch_series_code AND EFFECTIVE_DATE = :effective_date AND SEX_CODE = :sex_code AND BIRTH_YEAR < :birth_year)) ";
            else
                pagingWhere = ".TW56X WHERE (COMPANY_CODE = :company_code) AND ((PRODUCT_PREFIX < :product_prefix) OR (PRODUCT_PREFIX = :product_prefix AND TABLE_SUBSET < :table_subset) OR (PRODUCT_PREFIX = :product_prefix AND TABLE_SUBSET = :table_subset AND PURCH_SERIES_CODE < :purch_series_code) OR (PRODUCT_PREFIX = :product_prefix AND TABLE_SUBSET = :table_subset AND PURCH_SERIES_CODE = :purch_series_code AND EFFECTIVE_DATE < :effective_date) OR (PRODUCT_PREFIX = :product_prefix AND TABLE_SUBSET = :table_subset AND PURCH_SERIES_CODE = :purch_series_code AND EFFECTIVE_DATE = :effective_date AND SEX_CODE < :sex_code) OR (PRODUCT_PREFIX = :product_prefix AND TABLE_SUBSET = :table_subset AND PURCH_SERIES_CODE = :purch_series_code AND EFFECTIVE_DATE = :effective_date AND SEX_CODE = :sex_code AND BIRTH_YEAR < :birth_year)) ";
            order = " ORDER BY COMPANY_CODE DESC, PRODUCT_PREFIX DESC, TABLE_SUBSET DESC, PURCH_SERIES_CODE DESC, EFFECTIVE_DATE DESC, SEX_CODE DESC, BIRTH_YEAR DESC";
        }
        return pagingSql + schemaName + pagingWhere + order;
    }
    
    public String prepareInsertStmt(String schemaName) {
        StringBuffer sb = new StringBuffer();
        sb.append("INSERT INTO ").append(schemaName);
        sb.append(".TW56X ( ");
        sb.append("COMPANY_CODE, PRODUCT_PREFIX, TABLE_SUBSET, PURCH_SERIES_CODE, EFFECTIVE_DATE, SEX_CODE, BIRTH_YEAR, SETBACK_YEARS )");
        sb.append(" VALUES (?, ?, ?, ?, ?, ?, ?, ? )");
        return sb.toString();
    }
    
    public String prepareUpdateStmt(String schemaName)
    {
        StringBuffer sb = new StringBuffer();
        sb.append("UPDATE ").append(schemaName);
        sb.append(".TW56X ");
        sb.append(" SET COMPANY_CODE = ?, PRODUCT_PREFIX = ?, TABLE_SUBSET = ?, PURCH_SERIES_CODE = ?, EFFECTIVE_DATE = ?, SEX_CODE = ?, BIRTH_YEAR = ?, SETBACK_YEARS = ?");
        sb.append(" WHERE COMPANY_CODE = ? AND PRODUCT_PREFIX = ? AND TABLE_SUBSET = ? AND PURCH_SERIES_CODE = ? AND EFFECTIVE_DATE = ? AND SEX_CODE = ? AND BIRTH_YEAR = ?");
        return sb.toString();
    }
    
    public String prepareDeleteStmt(String schemaName)
    {
        StringBuffer sb = new StringBuffer();
        sb.append("DELETE FROM ").append(schemaName);
        sb.append(".TW56X ");
        sb.append(" WHERE COMPANY_CODE = ? AND PRODUCT_PREFIX = ? AND TABLE_SUBSET = ? AND PURCH_SERIES_CODE = ? AND EFFECTIVE_DATE = ? AND SEX_CODE = ? AND BIRTH_YEAR = ?");
        return sb.toString();
    }
}
