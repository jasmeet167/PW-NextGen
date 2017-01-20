package com.csc.fsg.life.pw.io.descriptor.wma;

import com.csc.fsg.life.pw.io.*;

public class T210BDescriptor
    extends TableDescriptor
{
    private static final String pagingSql = "SELECT COMPANY_CODE, TABLE_SUBSET, STATUTORY_CODE, LINE_OF_BUSINESS, EFFECTIVE_DATE, FUND_NUMBER, FUND_END_DATE FROM ";
    
    public void initialize()
    {
        setRowClass(T210BRow.class);
        setTableName("T210B");
        setTableId("210");
        super.initialize();
    }
    
    public void initializeColumnDescriptors()
    {
        super.initializeColumnDescriptors();
        addColumnDescriptor(new ColumnDescriptor(this,"getCompanyCode","setCompanyCode","COMPANY_CODE,1,1,3,0,true|,0,CHAR,1,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getTableSubset","setTableSubset","TABLE_SUBSET,1,2,16,0,true|,0,CHAR,1,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getStatutoryCode","setStatutoryCode","STATUTORY_CODE,1,3,3,0,true|,0,CHAR,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getLineOfBusiness","setLineOfBusiness","LINE_OF_BUSINESS,1,4,3,0,true|,0,CHAR,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getEffectiveDate","setEffectiveDate","EFFECTIVE_DATE,91,5,10,0,true|,0,DATE,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getFundNumber","setFundNumber","FUND_NUMBER,3,6,8,0,true|,0,INTEGER,4,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getFundEndDate","setFundEndDate","FUND_END_DATE,91,7,10,0,false|,0,DATE,0,null,null,null,null,null"));
    }
    
    public String getPagingSQL(String schemaName,boolean isSubsetMode,boolean isNext, boolean locator)
    {
        String pagingWhere = null;
        String order = null;
        if (isNext) {
            if (isSubsetMode)
                if (locator)
                    pagingWhere = ".T210B WHERE (COMPANY_CODE = :company_code) AND (TABLE_SUBSET = :table_subset) AND ((STATUTORY_CODE > :statutory_code) OR (STATUTORY_CODE = :statutory_code AND LINE_OF_BUSINESS > :line_of_business) OR (STATUTORY_CODE = :statutory_code AND LINE_OF_BUSINESS = :line_of_business AND EFFECTIVE_DATE > :effective_date) OR (STATUTORY_CODE = :statutory_code AND LINE_OF_BUSINESS = :line_of_business AND EFFECTIVE_DATE = :effective_date AND FUND_NUMBER > :fund_number) OR (STATUTORY_CODE = :statutory_code AND LINE_OF_BUSINESS = :line_of_business AND EFFECTIVE_DATE = :effective_date AND FUND_NUMBER = :fund_number)) ";
                else 
                    pagingWhere = ".T210B WHERE (COMPANY_CODE = :company_code) AND (TABLE_SUBSET = :table_subset) AND ((STATUTORY_CODE > :statutory_code) OR (STATUTORY_CODE = :statutory_code AND LINE_OF_BUSINESS > :line_of_business) OR (STATUTORY_CODE = :statutory_code AND LINE_OF_BUSINESS = :line_of_business AND EFFECTIVE_DATE > :effective_date) OR (STATUTORY_CODE = :statutory_code AND LINE_OF_BUSINESS = :line_of_business AND EFFECTIVE_DATE = :effective_date AND FUND_NUMBER > :fund_number)) ";
            else
                if (locator)
                    pagingWhere = ".T210B WHERE (COMPANY_CODE = :company_code) AND ((TABLE_SUBSET > :table_subset) OR (TABLE_SUBSET = :table_subset AND STATUTORY_CODE > :statutory_code) OR (TABLE_SUBSET = :table_subset AND STATUTORY_CODE = :statutory_code AND LINE_OF_BUSINESS > :line_of_business) OR (TABLE_SUBSET = :table_subset AND STATUTORY_CODE = :statutory_code AND LINE_OF_BUSINESS = :line_of_business AND EFFECTIVE_DATE > :effective_date) OR (TABLE_SUBSET = :table_subset AND STATUTORY_CODE = :statutory_code AND LINE_OF_BUSINESS = :line_of_business AND EFFECTIVE_DATE = :effective_date AND FUND_NUMBER > :fund_number) OR (TABLE_SUBSET = :table_subset AND STATUTORY_CODE = :statutory_code AND LINE_OF_BUSINESS = :line_of_business AND EFFECTIVE_DATE = :effective_date AND FUND_NUMBER = :fund_number)) ";
                else
                    pagingWhere = ".T210B WHERE (COMPANY_CODE = :company_code) AND ((TABLE_SUBSET > :table_subset) OR (TABLE_SUBSET = :table_subset AND STATUTORY_CODE > :statutory_code) OR (TABLE_SUBSET = :table_subset AND STATUTORY_CODE = :statutory_code AND LINE_OF_BUSINESS > :line_of_business) OR (TABLE_SUBSET = :table_subset AND STATUTORY_CODE = :statutory_code AND LINE_OF_BUSINESS = :line_of_business AND EFFECTIVE_DATE > :effective_date) OR (TABLE_SUBSET = :table_subset AND STATUTORY_CODE = :statutory_code AND LINE_OF_BUSINESS = :line_of_business AND EFFECTIVE_DATE = :effective_date AND FUND_NUMBER > :fund_number)) ";
            order = " ORDER BY COMPANY_CODE, TABLE_SUBSET, STATUTORY_CODE, LINE_OF_BUSINESS, EFFECTIVE_DATE, FUND_NUMBER";
        }
        else {
            if (isSubsetMode)
                pagingWhere = ".T210B WHERE (COMPANY_CODE = :company_code) AND (TABLE_SUBSET = :table_subset) AND ((STATUTORY_CODE < :statutory_code) OR (STATUTORY_CODE = :statutory_code AND LINE_OF_BUSINESS < :line_of_business) OR (STATUTORY_CODE = :statutory_code AND LINE_OF_BUSINESS = :line_of_business AND EFFECTIVE_DATE < :effective_date) OR (STATUTORY_CODE = :statutory_code AND LINE_OF_BUSINESS = :line_of_business AND EFFECTIVE_DATE = :effective_date AND FUND_NUMBER < :fund_number)) ";
            else
                pagingWhere = ".T210B WHERE (COMPANY_CODE = :company_code) AND ((TABLE_SUBSET < :table_subset) OR (TABLE_SUBSET = :table_subset AND STATUTORY_CODE < :statutory_code) OR (TABLE_SUBSET = :table_subset AND STATUTORY_CODE = :statutory_code AND LINE_OF_BUSINESS < :line_of_business) OR (TABLE_SUBSET = :table_subset AND STATUTORY_CODE = :statutory_code AND LINE_OF_BUSINESS = :line_of_business AND EFFECTIVE_DATE < :effective_date) OR (TABLE_SUBSET = :table_subset AND STATUTORY_CODE = :statutory_code AND LINE_OF_BUSINESS = :line_of_business AND EFFECTIVE_DATE = :effective_date AND FUND_NUMBER < :fund_number)) ";
            order = " ORDER BY COMPANY_CODE DESC, TABLE_SUBSET DESC, STATUTORY_CODE DESC, LINE_OF_BUSINESS DESC, EFFECTIVE_DATE DESC, FUND_NUMBER DESC";
        }
        return pagingSql + schemaName + pagingWhere + order;
    }
    
    public String prepareInsertStmt(String schemaName) {
        StringBuffer sb = new StringBuffer();
        sb.append("INSERT INTO ").append(schemaName);
        sb.append(".T210B ( ");
        sb.append("COMPANY_CODE, TABLE_SUBSET, STATUTORY_CODE, LINE_OF_BUSINESS, EFFECTIVE_DATE, FUND_NUMBER, FUND_END_DATE )");
        sb.append(" VALUES (?, ?, ?, ?, ?, ?, ? )");
        return sb.toString();
    }
    
    public String prepareUpdateStmt(String schemaName)
    {
        StringBuffer sb = new StringBuffer();
        sb.append("UPDATE ").append(schemaName);
        sb.append(".T210B ");
        sb.append(" SET COMPANY_CODE = ?, TABLE_SUBSET = ?, STATUTORY_CODE = ?, LINE_OF_BUSINESS = ?, EFFECTIVE_DATE = ?, FUND_NUMBER = ?, FUND_END_DATE = ?");
        sb.append(" WHERE COMPANY_CODE = ? AND TABLE_SUBSET = ? AND STATUTORY_CODE = ? AND LINE_OF_BUSINESS = ? AND EFFECTIVE_DATE = ? AND FUND_NUMBER = ?");
        return sb.toString();
    }
    
    public String prepareDeleteStmt(String schemaName)
    {
        StringBuffer sb = new StringBuffer();
        sb.append("DELETE FROM ").append(schemaName);
        sb.append(".T210B ");
        sb.append(" WHERE COMPANY_CODE = ? AND TABLE_SUBSET = ? AND STATUTORY_CODE = ? AND LINE_OF_BUSINESS = ? AND EFFECTIVE_DATE = ? AND FUND_NUMBER = ?");
        return sb.toString();
    }
}
