package com.csc.fsg.life.pw.web.io.descriptor.wma;

import com.csc.fsg.life.pw.web.io.*;

public class T010XDescriptor
    extends TableDescriptor
{
    private static final String pagingSql = "SELECT COMPANY_CODE, PRODUCT_PREFIX, TABLE_SUBSET, STATUTORY_CODE, STATE_CODE, LINE_OF_BUSINESS, EFFECTIVE_DATE, FUND_NUMBER, TERMINATION_DATE FROM ";
    
    public void initialize()
    {
        setRowClass(T010XRow.class);
        setTableName("T010X");
        setTableId("010");
        super.initialize();
    }
    
    public void initializeColumnDescriptors()
    {
        super.initializeColumnDescriptors();
        addColumnDescriptor(new ColumnDescriptor(this,"getCompanyCode","setCompanyCode","COMPANY_CODE,1,1,3,0,true|A,0,CHAR,1,null,null,null,null,null|U,0,CHAR,1,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getProductPrefix","setProductPrefix","PRODUCT_PREFIX,1,2,1,0,true|A,0,CHAR,1,null,null,null,null,null|U,0,CHAR,1,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getTableSubset","setTableSubset","TABLE_SUBSET,1,3,16,0,true|A,0,CHAR,1,null,null,null,null,null|U,0,CHAR,1,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getStatutoryCode","setStatutoryCode","STATUTORY_CODE,1,4,3,0,true|A,0,CHAR,0,null,null,null,null,null|U,0,CHAR,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getStateCode","setStateCode","STATE_CODE,1,5,3,0,true|A,0,CHAR,0,null,null,null,null,null|U,0,CHAR,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getLineOfBusiness","setLineOfBusiness","LINE_OF_BUSINESS,1,6,3,0,true|A,0,CHAR,0,null,null,null,null,null|U,0,CHAR,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getEffectiveDate","setEffectiveDate","EFFECTIVE_DATE,91,7,10,0,true|A,0,DATE,0,null,null,null,null,null|U,0,DATE,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getFundNumber","setFundNumber","FUND_NUMBER,3,8,8,0,true|A,0,INTEGER,0,null,null,null,null,null|U,0,INTEGER,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getTerminationDate","setTerminationDate","TERMINATION_DATE,91,9,10,0,false|A,0,DATE,0,null,null,null,null,null|U,0,DATE,0,null,null,null,null,null"));
    }
    
    public String getPagingSQL(String schemaName,boolean isSubsetMode,boolean isNext, boolean locator)
    {
        String pagingWhere = null;
        String order = null;
        if (isNext) {
            if (isSubsetMode)
                if (locator)
                    pagingWhere = ".T010X WHERE (COMPANY_CODE = :company_code) AND (PRODUCT_PREFIX = :product_prefix) AND (TABLE_SUBSET = :table_subset) AND ((STATUTORY_CODE > :statutory_code) OR (STATUTORY_CODE = :statutory_code AND STATE_CODE > :state_code) OR (STATUTORY_CODE = :statutory_code AND STATE_CODE = :state_code AND LINE_OF_BUSINESS > :line_of_business) OR (STATUTORY_CODE = :statutory_code AND STATE_CODE = :state_code AND LINE_OF_BUSINESS = :line_of_business AND EFFECTIVE_DATE > :effective_date) OR (STATUTORY_CODE = :statutory_code AND STATE_CODE = :state_code AND LINE_OF_BUSINESS = :line_of_business AND EFFECTIVE_DATE = :effective_date AND FUND_NUMBER > :fund_number) OR (STATUTORY_CODE = :statutory_code AND STATE_CODE = :state_code AND LINE_OF_BUSINESS = :line_of_business AND EFFECTIVE_DATE = :effective_date AND FUND_NUMBER = :fund_number)) ";
                else 
                    pagingWhere = ".T010X WHERE (COMPANY_CODE = :company_code) AND (PRODUCT_PREFIX = :product_prefix) AND (TABLE_SUBSET = :table_subset) AND ((STATUTORY_CODE > :statutory_code) OR (STATUTORY_CODE = :statutory_code AND STATE_CODE > :state_code) OR (STATUTORY_CODE = :statutory_code AND STATE_CODE = :state_code AND LINE_OF_BUSINESS > :line_of_business) OR (STATUTORY_CODE = :statutory_code AND STATE_CODE = :state_code AND LINE_OF_BUSINESS = :line_of_business AND EFFECTIVE_DATE > :effective_date) OR (STATUTORY_CODE = :statutory_code AND STATE_CODE = :state_code AND LINE_OF_BUSINESS = :line_of_business AND EFFECTIVE_DATE = :effective_date AND FUND_NUMBER > :fund_number)) ";
            else
                if (locator)
                    pagingWhere = ".T010X WHERE (COMPANY_CODE = :company_code) AND ((PRODUCT_PREFIX > :product_prefix) OR (PRODUCT_PREFIX = :product_prefix AND TABLE_SUBSET > :table_subset) OR (PRODUCT_PREFIX = :product_prefix AND TABLE_SUBSET = :table_subset AND STATUTORY_CODE > :statutory_code) OR (PRODUCT_PREFIX = :product_prefix AND TABLE_SUBSET = :table_subset AND STATUTORY_CODE = :statutory_code AND STATE_CODE > :state_code) OR (PRODUCT_PREFIX = :product_prefix AND TABLE_SUBSET = :table_subset AND STATUTORY_CODE = :statutory_code AND STATE_CODE = :state_code AND LINE_OF_BUSINESS > :line_of_business) OR (PRODUCT_PREFIX = :product_prefix AND TABLE_SUBSET = :table_subset AND STATUTORY_CODE = :statutory_code AND STATE_CODE = :state_code AND LINE_OF_BUSINESS = :line_of_business AND EFFECTIVE_DATE > :effective_date) OR (PRODUCT_PREFIX = :product_prefix AND TABLE_SUBSET = :table_subset AND STATUTORY_CODE = :statutory_code AND STATE_CODE = :state_code AND LINE_OF_BUSINESS = :line_of_business AND EFFECTIVE_DATE = :effective_date AND FUND_NUMBER > :fund_number) OR (PRODUCT_PREFIX = :product_prefix AND TABLE_SUBSET = :table_subset AND STATUTORY_CODE = :statutory_code AND STATE_CODE = :state_code AND LINE_OF_BUSINESS = :line_of_business AND EFFECTIVE_DATE = :effective_date AND FUND_NUMBER = :fund_number)) ";
                else
                    pagingWhere = ".T010X WHERE (COMPANY_CODE = :company_code) AND ((PRODUCT_PREFIX > :product_prefix) OR (PRODUCT_PREFIX = :product_prefix AND TABLE_SUBSET > :table_subset) OR (PRODUCT_PREFIX = :product_prefix AND TABLE_SUBSET = :table_subset AND STATUTORY_CODE > :statutory_code) OR (PRODUCT_PREFIX = :product_prefix AND TABLE_SUBSET = :table_subset AND STATUTORY_CODE = :statutory_code AND STATE_CODE > :state_code) OR (PRODUCT_PREFIX = :product_prefix AND TABLE_SUBSET = :table_subset AND STATUTORY_CODE = :statutory_code AND STATE_CODE = :state_code AND LINE_OF_BUSINESS > :line_of_business) OR (PRODUCT_PREFIX = :product_prefix AND TABLE_SUBSET = :table_subset AND STATUTORY_CODE = :statutory_code AND STATE_CODE = :state_code AND LINE_OF_BUSINESS = :line_of_business AND EFFECTIVE_DATE > :effective_date) OR (PRODUCT_PREFIX = :product_prefix AND TABLE_SUBSET = :table_subset AND STATUTORY_CODE = :statutory_code AND STATE_CODE = :state_code AND LINE_OF_BUSINESS = :line_of_business AND EFFECTIVE_DATE = :effective_date AND FUND_NUMBER > :fund_number)) ";
            order = " ORDER BY COMPANY_CODE, PRODUCT_PREFIX, TABLE_SUBSET, STATUTORY_CODE, STATE_CODE, LINE_OF_BUSINESS, EFFECTIVE_DATE, FUND_NUMBER";
        }
        else {
            if (isSubsetMode)
                pagingWhere = ".T010X WHERE (COMPANY_CODE = :company_code) AND (PRODUCT_PREFIX = :product_prefix) AND (TABLE_SUBSET = :table_subset) AND ((STATUTORY_CODE < :statutory_code) OR (STATUTORY_CODE = :statutory_code AND STATE_CODE < :state_code) OR (STATUTORY_CODE = :statutory_code AND STATE_CODE = :state_code AND LINE_OF_BUSINESS < :line_of_business) OR (STATUTORY_CODE = :statutory_code AND STATE_CODE = :state_code AND LINE_OF_BUSINESS = :line_of_business AND EFFECTIVE_DATE < :effective_date) OR (STATUTORY_CODE = :statutory_code AND STATE_CODE = :state_code AND LINE_OF_BUSINESS = :line_of_business AND EFFECTIVE_DATE = :effective_date AND FUND_NUMBER < :fund_number)) ";
            else
                pagingWhere = ".T010X WHERE (COMPANY_CODE = :company_code) AND ((PRODUCT_PREFIX < :product_prefix) OR (PRODUCT_PREFIX = :product_prefix AND TABLE_SUBSET < :table_subset) OR (PRODUCT_PREFIX = :product_prefix AND TABLE_SUBSET = :table_subset AND STATUTORY_CODE < :statutory_code) OR (PRODUCT_PREFIX = :product_prefix AND TABLE_SUBSET = :table_subset AND STATUTORY_CODE = :statutory_code AND STATE_CODE < :state_code) OR (PRODUCT_PREFIX = :product_prefix AND TABLE_SUBSET = :table_subset AND STATUTORY_CODE = :statutory_code AND STATE_CODE = :state_code AND LINE_OF_BUSINESS < :line_of_business) OR (PRODUCT_PREFIX = :product_prefix AND TABLE_SUBSET = :table_subset AND STATUTORY_CODE = :statutory_code AND STATE_CODE = :state_code AND LINE_OF_BUSINESS = :line_of_business AND EFFECTIVE_DATE < :effective_date) OR (PRODUCT_PREFIX = :product_prefix AND TABLE_SUBSET = :table_subset AND STATUTORY_CODE = :statutory_code AND STATE_CODE = :state_code AND LINE_OF_BUSINESS = :line_of_business AND EFFECTIVE_DATE = :effective_date AND FUND_NUMBER < :fund_number)) ";
            order = " ORDER BY COMPANY_CODE DESC, PRODUCT_PREFIX DESC, TABLE_SUBSET DESC, STATUTORY_CODE DESC, STATE_CODE DESC, LINE_OF_BUSINESS DESC, EFFECTIVE_DATE DESC, FUND_NUMBER DESC";
        }
        return pagingSql + schemaName + pagingWhere + order;
    }
    
    public String prepareInsertStmt(String schemaName) {
        StringBuffer sb = new StringBuffer();
        sb.append("INSERT INTO ").append(schemaName);
        sb.append(".T010X ( ");
        sb.append("COMPANY_CODE, PRODUCT_PREFIX, TABLE_SUBSET, STATUTORY_CODE, STATE_CODE, LINE_OF_BUSINESS, EFFECTIVE_DATE, FUND_NUMBER, TERMINATION_DATE )");
        sb.append(" VALUES (?, ?, ?, ?, ?, ?, ?, ?, ? )");
        return sb.toString();
    }
    
    public String prepareUpdateStmt(String schemaName)
    {
        StringBuffer sb = new StringBuffer();
        sb.append("UPDATE ").append(schemaName);
        sb.append(".T010X ");
        sb.append(" SET COMPANY_CODE = ?, PRODUCT_PREFIX = ?, TABLE_SUBSET = ?, STATUTORY_CODE = ?, STATE_CODE = ?, LINE_OF_BUSINESS = ?, EFFECTIVE_DATE = ?, FUND_NUMBER = ?, TERMINATION_DATE = ?");
        sb.append(" WHERE COMPANY_CODE = ? AND PRODUCT_PREFIX = ? AND TABLE_SUBSET = ? AND STATUTORY_CODE = ? AND STATE_CODE = ? AND LINE_OF_BUSINESS = ? AND EFFECTIVE_DATE = ? AND FUND_NUMBER = ?");
        return sb.toString();
    }
    
    public String prepareDeleteStmt(String schemaName)
    {
        StringBuffer sb = new StringBuffer();
        sb.append("DELETE FROM ").append(schemaName);
        sb.append(".T010X ");
        sb.append(" WHERE COMPANY_CODE = ? AND PRODUCT_PREFIX = ? AND TABLE_SUBSET = ? AND STATUTORY_CODE = ? AND STATE_CODE = ? AND LINE_OF_BUSINESS = ? AND EFFECTIVE_DATE = ? AND FUND_NUMBER = ?");
        return sb.toString();
    }
}
