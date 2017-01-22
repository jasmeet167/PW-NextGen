package com.csc.fsg.life.pw.web.io.descriptor.wma;

import com.csc.fsg.life.pw.web.io.*;

public class TW57XDescriptor
    extends TableDescriptor
{
    private static final String pagingSql = "SELECT COMPANY_CODE, PRODUCT_PREFIX, TABLE_SUBSET, PURCH_SERIES_CODE, EFFECTIVE_DATE, BENEFIT_DESCRIPTOR, TABLE_TYPE, PURCH_RATE_TBL FROM ";
    
    public void initialize()
    {
        setRowClass(TW57XRow.class);
        setTableName("TW57X");
        setTableId("W57");
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
        addColumnDescriptor(new ColumnDescriptor(this,"getBenefitDescriptor","setBenefitDescriptor","BENEFIT_DESCRIPTOR,1,6,8,0,true|A,0,CHAR,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getTableType","setTableType","TABLE_TYPE,1,7,1,0,true|A,0,CHAR,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getPurchRateTbl","setPurchRateTbl","PURCH_RATE_TBL,1,8,16,0,false|A,0,CHAR,0,W58,null,null,null,null"));
    }
    
    public String getPagingSQL(String schemaName,boolean isSubsetMode,boolean isNext, boolean locator)
    {
        String pagingWhere = null;
        String order = null;
        if (isNext) {
            if (isSubsetMode)
                if (locator)
                    pagingWhere = ".TW57X WHERE (COMPANY_CODE = :company_code) AND (PRODUCT_PREFIX = :product_prefix) AND (TABLE_SUBSET = :table_subset) AND ((PURCH_SERIES_CODE > :purch_series_code) OR (PURCH_SERIES_CODE = :purch_series_code AND EFFECTIVE_DATE > :effective_date) OR (PURCH_SERIES_CODE = :purch_series_code AND EFFECTIVE_DATE = :effective_date AND BENEFIT_DESCRIPTOR > :benefit_descriptor) OR (PURCH_SERIES_CODE = :purch_series_code AND EFFECTIVE_DATE = :effective_date AND BENEFIT_DESCRIPTOR = :benefit_descriptor AND TABLE_TYPE > :table_type) OR (PURCH_SERIES_CODE = :purch_series_code AND EFFECTIVE_DATE = :effective_date AND BENEFIT_DESCRIPTOR = :benefit_descriptor AND TABLE_TYPE = :table_type)) ";
                else 
                    pagingWhere = ".TW57X WHERE (COMPANY_CODE = :company_code) AND (PRODUCT_PREFIX = :product_prefix) AND (TABLE_SUBSET = :table_subset) AND ((PURCH_SERIES_CODE > :purch_series_code) OR (PURCH_SERIES_CODE = :purch_series_code AND EFFECTIVE_DATE > :effective_date) OR (PURCH_SERIES_CODE = :purch_series_code AND EFFECTIVE_DATE = :effective_date AND BENEFIT_DESCRIPTOR > :benefit_descriptor) OR (PURCH_SERIES_CODE = :purch_series_code AND EFFECTIVE_DATE = :effective_date AND BENEFIT_DESCRIPTOR = :benefit_descriptor AND TABLE_TYPE > :table_type)) ";
            else
                if (locator)
                    pagingWhere = ".TW57X WHERE (COMPANY_CODE = :company_code) AND ((PRODUCT_PREFIX > :product_prefix) OR (PRODUCT_PREFIX = :product_prefix AND TABLE_SUBSET > :table_subset) OR (PRODUCT_PREFIX = :product_prefix AND TABLE_SUBSET = :table_subset AND PURCH_SERIES_CODE > :purch_series_code) OR (PRODUCT_PREFIX = :product_prefix AND TABLE_SUBSET = :table_subset AND PURCH_SERIES_CODE = :purch_series_code AND EFFECTIVE_DATE > :effective_date) OR (PRODUCT_PREFIX = :product_prefix AND TABLE_SUBSET = :table_subset AND PURCH_SERIES_CODE = :purch_series_code AND EFFECTIVE_DATE = :effective_date AND BENEFIT_DESCRIPTOR > :benefit_descriptor) OR (PRODUCT_PREFIX = :product_prefix AND TABLE_SUBSET = :table_subset AND PURCH_SERIES_CODE = :purch_series_code AND EFFECTIVE_DATE = :effective_date AND BENEFIT_DESCRIPTOR = :benefit_descriptor AND TABLE_TYPE > :table_type) OR (PRODUCT_PREFIX = :product_prefix AND TABLE_SUBSET = :table_subset AND PURCH_SERIES_CODE = :purch_series_code AND EFFECTIVE_DATE = :effective_date AND BENEFIT_DESCRIPTOR = :benefit_descriptor AND TABLE_TYPE = :table_type)) ";
                else
                    pagingWhere = ".TW57X WHERE (COMPANY_CODE = :company_code) AND ((PRODUCT_PREFIX > :product_prefix) OR (PRODUCT_PREFIX = :product_prefix AND TABLE_SUBSET > :table_subset) OR (PRODUCT_PREFIX = :product_prefix AND TABLE_SUBSET = :table_subset AND PURCH_SERIES_CODE > :purch_series_code) OR (PRODUCT_PREFIX = :product_prefix AND TABLE_SUBSET = :table_subset AND PURCH_SERIES_CODE = :purch_series_code AND EFFECTIVE_DATE > :effective_date) OR (PRODUCT_PREFIX = :product_prefix AND TABLE_SUBSET = :table_subset AND PURCH_SERIES_CODE = :purch_series_code AND EFFECTIVE_DATE = :effective_date AND BENEFIT_DESCRIPTOR > :benefit_descriptor) OR (PRODUCT_PREFIX = :product_prefix AND TABLE_SUBSET = :table_subset AND PURCH_SERIES_CODE = :purch_series_code AND EFFECTIVE_DATE = :effective_date AND BENEFIT_DESCRIPTOR = :benefit_descriptor AND TABLE_TYPE > :table_type)) ";
            order = " ORDER BY COMPANY_CODE, PRODUCT_PREFIX, TABLE_SUBSET, PURCH_SERIES_CODE, EFFECTIVE_DATE, BENEFIT_DESCRIPTOR, TABLE_TYPE";
        }
        else {
            if (isSubsetMode)
                pagingWhere = ".TW57X WHERE (COMPANY_CODE = :company_code) AND (PRODUCT_PREFIX = :product_prefix) AND (TABLE_SUBSET = :table_subset) AND ((PURCH_SERIES_CODE < :purch_series_code) OR (PURCH_SERIES_CODE = :purch_series_code AND EFFECTIVE_DATE < :effective_date) OR (PURCH_SERIES_CODE = :purch_series_code AND EFFECTIVE_DATE = :effective_date AND BENEFIT_DESCRIPTOR < :benefit_descriptor) OR (PURCH_SERIES_CODE = :purch_series_code AND EFFECTIVE_DATE = :effective_date AND BENEFIT_DESCRIPTOR = :benefit_descriptor AND TABLE_TYPE < :table_type)) ";
            else
                pagingWhere = ".TW57X WHERE (COMPANY_CODE = :company_code) AND ((PRODUCT_PREFIX < :product_prefix) OR (PRODUCT_PREFIX = :product_prefix AND TABLE_SUBSET < :table_subset) OR (PRODUCT_PREFIX = :product_prefix AND TABLE_SUBSET = :table_subset AND PURCH_SERIES_CODE < :purch_series_code) OR (PRODUCT_PREFIX = :product_prefix AND TABLE_SUBSET = :table_subset AND PURCH_SERIES_CODE = :purch_series_code AND EFFECTIVE_DATE < :effective_date) OR (PRODUCT_PREFIX = :product_prefix AND TABLE_SUBSET = :table_subset AND PURCH_SERIES_CODE = :purch_series_code AND EFFECTIVE_DATE = :effective_date AND BENEFIT_DESCRIPTOR < :benefit_descriptor) OR (PRODUCT_PREFIX = :product_prefix AND TABLE_SUBSET = :table_subset AND PURCH_SERIES_CODE = :purch_series_code AND EFFECTIVE_DATE = :effective_date AND BENEFIT_DESCRIPTOR = :benefit_descriptor AND TABLE_TYPE < :table_type)) ";
            order = " ORDER BY COMPANY_CODE DESC, PRODUCT_PREFIX DESC, TABLE_SUBSET DESC, PURCH_SERIES_CODE DESC, EFFECTIVE_DATE DESC, BENEFIT_DESCRIPTOR DESC, TABLE_TYPE DESC";
        }
        return pagingSql + schemaName + pagingWhere + order;
    }
    
    public String prepareInsertStmt(String schemaName) {
        StringBuffer sb = new StringBuffer();
        sb.append("INSERT INTO ").append(schemaName);
        sb.append(".TW57X ( ");
        sb.append("COMPANY_CODE, PRODUCT_PREFIX, TABLE_SUBSET, PURCH_SERIES_CODE, EFFECTIVE_DATE, BENEFIT_DESCRIPTOR, TABLE_TYPE, PURCH_RATE_TBL )");
        sb.append(" VALUES (?, ?, ?, ?, ?, ?, ?, ? )");
        return sb.toString();
    }
    
    public String prepareUpdateStmt(String schemaName)
    {
        StringBuffer sb = new StringBuffer();
        sb.append("UPDATE ").append(schemaName);
        sb.append(".TW57X ");
        sb.append(" SET COMPANY_CODE = ?, PRODUCT_PREFIX = ?, TABLE_SUBSET = ?, PURCH_SERIES_CODE = ?, EFFECTIVE_DATE = ?, BENEFIT_DESCRIPTOR = ?, TABLE_TYPE = ?, PURCH_RATE_TBL = ?");
        sb.append(" WHERE COMPANY_CODE = ? AND PRODUCT_PREFIX = ? AND TABLE_SUBSET = ? AND PURCH_SERIES_CODE = ? AND EFFECTIVE_DATE = ? AND BENEFIT_DESCRIPTOR = ? AND TABLE_TYPE = ?");
        return sb.toString();
    }
    
    public String prepareDeleteStmt(String schemaName)
    {
        StringBuffer sb = new StringBuffer();
        sb.append("DELETE FROM ").append(schemaName);
        sb.append(".TW57X ");
        sb.append(" WHERE COMPANY_CODE = ? AND PRODUCT_PREFIX = ? AND TABLE_SUBSET = ? AND PURCH_SERIES_CODE = ? AND EFFECTIVE_DATE = ? AND BENEFIT_DESCRIPTOR = ? AND TABLE_TYPE = ?");
        return sb.toString();
    }
}
