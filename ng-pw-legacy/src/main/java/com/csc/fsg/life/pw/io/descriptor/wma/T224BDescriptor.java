package com.csc.fsg.life.pw.io.descriptor.wma;

import com.csc.fsg.life.pw.io.*;

public class T224BDescriptor
    extends TableDescriptor
{
    private static final String pagingSql = "SELECT COMPANY_CODE, TABLE_SUBSET, FUND_NUMBER, EFFECTIVE_DATE, ACCESS_AMOUNT, DCLR_INT_RT_TBL, INTEREST_IND, INT_CALC_MTHD, ROLLOVER_FREQUENCY, ROLLOVER_PERIOD, NEW_MONEY_IND FROM ";
    
    public void initialize()
    {
        setRowClass(T224BRow.class);
        setTableName("T224B");
        setTableId("224");
        super.initialize();
    }
    
    public void initializeColumnDescriptors()
    {
        super.initializeColumnDescriptors();
        addColumnDescriptor(new ColumnDescriptor(this,"getCompanyCode","setCompanyCode","COMPANY_CODE,1,1,3,0,true|,0,CHAR,1,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getTableSubset","setTableSubset","TABLE_SUBSET,1,2,16,0,true|,0,CHAR,1,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getFundNumber","setFundNumber","FUND_NUMBER,3,3,8,0,true|,0,INTEGER,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getEffectiveDate","setEffectiveDate","EFFECTIVE_DATE,91,4,10,0,true|,0,DATE,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getAccessAmount","setAccessAmount","ACCESS_AMOUNT,3,5,11,2,true|,0,DOUBLE,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getDclrIntRtTbl","setDclrIntRtTbl","DCLR_INT_RT_TBL,1,6,16,0,false|,0,CHAR,0,225,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getInterestInd","setInterestInd","INTEREST_IND,1,7,1,0,false|,0,CHAR,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getIntCalcMthd","setIntCalcMthd","INT_CALC_MTHD,1,8,5,0,false|,0,CHAR,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getRolloverFrequency","setRolloverFrequency","ROLLOVER_FREQUENCY,1,9,1,0,false|,0,CHAR,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getRolloverPeriod","setRolloverPeriod","ROLLOVER_PERIOD,3,10,3,0,false|,0,INTEGER,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getNewMoneyInd","setNewMoneyInd","NEW_MONEY_IND,1,11,1,0,false|,0,CHAR,0,null,null,null,null,null"));
    }
    
    public String getPagingSQL(String schemaName,boolean isSubsetMode,boolean isNext, boolean locator)
    {
        String pagingWhere = null;
        String order = null;
        if (isNext) {
            if (isSubsetMode)
                if (locator)
                    pagingWhere = ".T224B WHERE (COMPANY_CODE = :company_code) AND (TABLE_SUBSET = :table_subset) AND ((FUND_NUMBER > :fund_number) OR (FUND_NUMBER = :fund_number AND EFFECTIVE_DATE > :effective_date) OR (FUND_NUMBER = :fund_number AND EFFECTIVE_DATE = :effective_date AND ACCESS_AMOUNT > :access_amount) OR (FUND_NUMBER = :fund_number AND EFFECTIVE_DATE = :effective_date AND ACCESS_AMOUNT = :access_amount)) ";
                else 
                    pagingWhere = ".T224B WHERE (COMPANY_CODE = :company_code) AND (TABLE_SUBSET = :table_subset) AND ((FUND_NUMBER > :fund_number) OR (FUND_NUMBER = :fund_number AND EFFECTIVE_DATE > :effective_date) OR (FUND_NUMBER = :fund_number AND EFFECTIVE_DATE = :effective_date AND ACCESS_AMOUNT > :access_amount)) ";
            else
                if (locator)
                    pagingWhere = ".T224B WHERE (COMPANY_CODE = :company_code) AND ((TABLE_SUBSET > :table_subset) OR (TABLE_SUBSET = :table_subset AND FUND_NUMBER > :fund_number) OR (TABLE_SUBSET = :table_subset AND FUND_NUMBER = :fund_number AND EFFECTIVE_DATE > :effective_date) OR (TABLE_SUBSET = :table_subset AND FUND_NUMBER = :fund_number AND EFFECTIVE_DATE = :effective_date AND ACCESS_AMOUNT > :access_amount) OR (TABLE_SUBSET = :table_subset AND FUND_NUMBER = :fund_number AND EFFECTIVE_DATE = :effective_date AND ACCESS_AMOUNT = :access_amount)) ";
                else
                    pagingWhere = ".T224B WHERE (COMPANY_CODE = :company_code) AND ((TABLE_SUBSET > :table_subset) OR (TABLE_SUBSET = :table_subset AND FUND_NUMBER > :fund_number) OR (TABLE_SUBSET = :table_subset AND FUND_NUMBER = :fund_number AND EFFECTIVE_DATE > :effective_date) OR (TABLE_SUBSET = :table_subset AND FUND_NUMBER = :fund_number AND EFFECTIVE_DATE = :effective_date AND ACCESS_AMOUNT > :access_amount)) ";
            order = " ORDER BY COMPANY_CODE, TABLE_SUBSET, FUND_NUMBER, EFFECTIVE_DATE, ACCESS_AMOUNT";
        }
        else {
            if (isSubsetMode)
                pagingWhere = ".T224B WHERE (COMPANY_CODE = :company_code) AND (TABLE_SUBSET = :table_subset) AND ((FUND_NUMBER < :fund_number) OR (FUND_NUMBER = :fund_number AND EFFECTIVE_DATE < :effective_date) OR (FUND_NUMBER = :fund_number AND EFFECTIVE_DATE = :effective_date AND ACCESS_AMOUNT < :access_amount)) ";
            else
                pagingWhere = ".T224B WHERE (COMPANY_CODE = :company_code) AND ((TABLE_SUBSET < :table_subset) OR (TABLE_SUBSET = :table_subset AND FUND_NUMBER < :fund_number) OR (TABLE_SUBSET = :table_subset AND FUND_NUMBER = :fund_number AND EFFECTIVE_DATE < :effective_date) OR (TABLE_SUBSET = :table_subset AND FUND_NUMBER = :fund_number AND EFFECTIVE_DATE = :effective_date AND ACCESS_AMOUNT < :access_amount)) ";
            order = " ORDER BY COMPANY_CODE DESC, TABLE_SUBSET DESC, FUND_NUMBER DESC, EFFECTIVE_DATE DESC, ACCESS_AMOUNT DESC";
        }
        return pagingSql + schemaName + pagingWhere + order;
    }
    
    public String prepareInsertStmt(String schemaName) {
        StringBuffer sb = new StringBuffer();
        sb.append("INSERT INTO ").append(schemaName);
        sb.append(".T224B ( ");
        sb.append("COMPANY_CODE, TABLE_SUBSET, FUND_NUMBER, EFFECTIVE_DATE, ACCESS_AMOUNT, DCLR_INT_RT_TBL, INTEREST_IND, INT_CALC_MTHD, ROLLOVER_FREQUENCY, ROLLOVER_PERIOD, NEW_MONEY_IND )");
        sb.append(" VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ? )");
        return sb.toString();
    }
    
    public String prepareUpdateStmt(String schemaName)
    {
        StringBuffer sb = new StringBuffer();
        sb.append("UPDATE ").append(schemaName);
        sb.append(".T224B ");
        sb.append(" SET COMPANY_CODE = ?, TABLE_SUBSET = ?, FUND_NUMBER = ?, EFFECTIVE_DATE = ?, ACCESS_AMOUNT = ?, DCLR_INT_RT_TBL = ?, INTEREST_IND = ?, INT_CALC_MTHD = ?, ROLLOVER_FREQUENCY = ?, ROLLOVER_PERIOD = ?, NEW_MONEY_IND = ?");
        sb.append(" WHERE COMPANY_CODE = ? AND TABLE_SUBSET = ? AND FUND_NUMBER = ? AND EFFECTIVE_DATE = ? AND ACCESS_AMOUNT = ?");
        return sb.toString();
    }
    
    public String prepareDeleteStmt(String schemaName)
    {
        StringBuffer sb = new StringBuffer();
        sb.append("DELETE FROM ").append(schemaName);
        sb.append(".T224B ");
        sb.append(" WHERE COMPANY_CODE = ? AND TABLE_SUBSET = ? AND FUND_NUMBER = ? AND EFFECTIVE_DATE = ? AND ACCESS_AMOUNT = ?");
        return sb.toString();
    }
}
