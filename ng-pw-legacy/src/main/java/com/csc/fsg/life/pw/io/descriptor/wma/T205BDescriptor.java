package com.csc.fsg.life.pw.io.descriptor.wma;

import com.csc.fsg.life.pw.io.*;

public class T205BDescriptor
    extends TableDescriptor
{
    private static final String pagingSql = "SELECT COMPANY_CODE, TABLE_SUBSET, FUND_NUMBER, EFFECTIVE_DATE, WITHDRAW_POST_METH, MIN_DEP_AMOUNT, MAX_DEP_AMOUNT, MIN_WITHDRAWAL, MAX_WITHDRAWAL, IND_403B FROM ";
    
    public void initialize()
    {
        setRowClass(T205BRow.class);
        setTableName("T205B");
        setTableId("205");
        super.initialize();
    }
    
    public void initializeColumnDescriptors()
    {
        super.initializeColumnDescriptors();
        addColumnDescriptor(new ColumnDescriptor(this,"getCompanyCode","setCompanyCode","COMPANY_CODE,1,1,3,0,true|,0,CHAR,1,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getTableSubset","setTableSubset","TABLE_SUBSET,1,2,16,0,true|,0,CHAR,1,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getFundNumber","setFundNumber","FUND_NUMBER,3,3,8,0,true|,0,INTEGER,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getEffectiveDate","setEffectiveDate","EFFECTIVE_DATE,91,4,10,0,true|,0,DATE,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getWithdrawPostMeth","setWithdrawPostMeth","WITHDRAW_POST_METH,1,5,4,0,false|,0,CHAR,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getMinDepAmount","setMinDepAmount","MIN_DEP_AMOUNT,3,6,7,2,false|,0,DOUBLE,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getMaxDepAmount","setMaxDepAmount","MAX_DEP_AMOUNT,3,7,11,2,false|,0,DOUBLE,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getMinWithdrawal","setMinWithdrawal","MIN_WITHDRAWAL,3,8,7,2,false|,0,DOUBLE,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getMaxWithdrawal","setMaxWithdrawal","MAX_WITHDRAWAL,3,9,11,2,false|,0,DOUBLE,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getInd403b","setInd403b","IND_403B,1,10,1,0,false|,0,CHAR,0,null,null,null,null,null"));
    }
    
    public String getPagingSQL(String schemaName,boolean isSubsetMode,boolean isNext, boolean locator)
    {
        String pagingWhere = null;
        String order = null;
        if (isNext) {
            if (isSubsetMode)
                if (locator)
                    pagingWhere = ".T205B WHERE (COMPANY_CODE = :company_code) AND (TABLE_SUBSET = :table_subset) AND ((FUND_NUMBER > :fund_number) OR (FUND_NUMBER = :fund_number AND EFFECTIVE_DATE > :effective_date) OR (FUND_NUMBER = :fund_number AND EFFECTIVE_DATE = :effective_date)) ";
                else 
                    pagingWhere = ".T205B WHERE (COMPANY_CODE = :company_code) AND (TABLE_SUBSET = :table_subset) AND ((FUND_NUMBER > :fund_number) OR (FUND_NUMBER = :fund_number AND EFFECTIVE_DATE > :effective_date)) ";
            else
                if (locator)
                    pagingWhere = ".T205B WHERE (COMPANY_CODE = :company_code) AND ((TABLE_SUBSET > :table_subset) OR (TABLE_SUBSET = :table_subset AND FUND_NUMBER > :fund_number) OR (TABLE_SUBSET = :table_subset AND FUND_NUMBER = :fund_number AND EFFECTIVE_DATE > :effective_date) OR (TABLE_SUBSET = :table_subset AND FUND_NUMBER = :fund_number AND EFFECTIVE_DATE = :effective_date)) ";
                else
                    pagingWhere = ".T205B WHERE (COMPANY_CODE = :company_code) AND ((TABLE_SUBSET > :table_subset) OR (TABLE_SUBSET = :table_subset AND FUND_NUMBER > :fund_number) OR (TABLE_SUBSET = :table_subset AND FUND_NUMBER = :fund_number AND EFFECTIVE_DATE > :effective_date)) ";
            order = " ORDER BY COMPANY_CODE, TABLE_SUBSET, FUND_NUMBER, EFFECTIVE_DATE";
        }
        else {
            if (isSubsetMode)
                pagingWhere = ".T205B WHERE (COMPANY_CODE = :company_code) AND (TABLE_SUBSET = :table_subset) AND ((FUND_NUMBER < :fund_number) OR (FUND_NUMBER = :fund_number AND EFFECTIVE_DATE < :effective_date)) ";
            else
                pagingWhere = ".T205B WHERE (COMPANY_CODE = :company_code) AND ((TABLE_SUBSET < :table_subset) OR (TABLE_SUBSET = :table_subset AND FUND_NUMBER < :fund_number) OR (TABLE_SUBSET = :table_subset AND FUND_NUMBER = :fund_number AND EFFECTIVE_DATE < :effective_date)) ";
            order = " ORDER BY COMPANY_CODE DESC, TABLE_SUBSET DESC, FUND_NUMBER DESC, EFFECTIVE_DATE DESC";
        }
        return pagingSql + schemaName + pagingWhere + order;
    }
    
    public String prepareInsertStmt(String schemaName) {
        StringBuffer sb = new StringBuffer();
        sb.append("INSERT INTO ").append(schemaName);
        sb.append(".T205B ( ");
        sb.append("COMPANY_CODE, TABLE_SUBSET, FUND_NUMBER, EFFECTIVE_DATE, WITHDRAW_POST_METH, MIN_DEP_AMOUNT, MAX_DEP_AMOUNT, MIN_WITHDRAWAL, MAX_WITHDRAWAL, IND_403B )");
        sb.append(" VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ? )");
        return sb.toString();
    }
    
    public String prepareUpdateStmt(String schemaName)
    {
        StringBuffer sb = new StringBuffer();
        sb.append("UPDATE ").append(schemaName);
        sb.append(".T205B ");
        sb.append(" SET COMPANY_CODE = ?, TABLE_SUBSET = ?, FUND_NUMBER = ?, EFFECTIVE_DATE = ?, WITHDRAW_POST_METH = ?, MIN_DEP_AMOUNT = ?, MAX_DEP_AMOUNT = ?, MIN_WITHDRAWAL = ?, MAX_WITHDRAWAL = ?, IND_403B = ?");
        sb.append(" WHERE COMPANY_CODE = ? AND TABLE_SUBSET = ? AND FUND_NUMBER = ? AND EFFECTIVE_DATE = ?");
        return sb.toString();
    }
    
    public String prepareDeleteStmt(String schemaName)
    {
        StringBuffer sb = new StringBuffer();
        sb.append("DELETE FROM ").append(schemaName);
        sb.append(".T205B ");
        sb.append(" WHERE COMPANY_CODE = ? AND TABLE_SUBSET = ? AND FUND_NUMBER = ? AND EFFECTIVE_DATE = ?");
        return sb.toString();
    }
}
