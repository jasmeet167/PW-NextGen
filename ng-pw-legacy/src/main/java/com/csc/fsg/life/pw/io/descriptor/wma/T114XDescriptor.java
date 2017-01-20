package com.csc.fsg.life.pw.io.descriptor.wma;

import com.csc.fsg.life.pw.io.*;

public class T114XDescriptor
    extends TableDescriptor
{
    private static final String pagingSql = "SELECT COMPANY_CODE, PRODUCT_PREFIX, PRODUCT_SUFFIX, PLAN_CODE, EFFECTIVE_DATE, ISSUE_STATE, LINE_OF_BUSINESS, MEMO_CODE, INIT_FAV_CODE, FAV_CODE_CHG_IND, TRX_PRIORITY_IND, INIT_FAV_PERIOD, FREQUENCY_TYPE, FAV_CODE_CHG_FREQ, NBR_OF_FREQ, AMOUNT_TYPE, FAVCH_ENTRY, NBR_OF_OCCURS, DURATION_TYPE FROM ";
    
    public void initialize()
    {
        setRowClass(T114XRow.class);
        setTableName("T114X");
        setTableId("114");
        super.initialize();
    }
    
    public void initializeColumnDescriptors()
    {
        super.initializeColumnDescriptors();
        addColumnDescriptor(new ColumnDescriptor(this,"getCompanyCode","setCompanyCode","COMPANY_CODE,1,1,3,0,true|,0,CHAR,1,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getProductPrefix","setProductPrefix","PRODUCT_PREFIX,1,2,1,0,true|,0,CHAR,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getProductSuffix","setProductSuffix","PRODUCT_SUFFIX,1,3,1,0,true|,0,CHAR,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getPlanCode","setPlanCode","PLAN_CODE,1,4,6,0,true|,0,CHAR,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getEffectiveDate","setEffectiveDate","EFFECTIVE_DATE,91,5,10,0,true|,0,DATE,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getIssueState","setIssueState","ISSUE_STATE,1,6,3,0,true|,0,CHAR,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getLineOfBusiness","setLineOfBusiness","LINE_OF_BUSINESS,1,7,3,0,true|,0,CHAR,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getMemoCode","setMemoCode","MEMO_CODE,1,8,2,0,true|,0,CHAR,4,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getInitFavCode","setInitFavCode","INIT_FAV_CODE,1,9,6,0,false|,0,CHAR,4,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getFavCodeChgInd","setFavCodeChgInd","FAV_CODE_CHG_IND,1,10,1,0,false|,0,CHAR,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getTrxPriorityInd","setTrxPriorityInd","TRX_PRIORITY_IND,1,11,1,0,false|,0,CHAR,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getInitFavPeriod","setInitFavPeriod","INIT_FAV_PERIOD,5,12,3,0,false|,0,INTEGER,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getFrequencyType","setFrequencyType","FREQUENCY_TYPE,1,13,1,0,false|,0,CHAR,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getFavCodeChgFreq","setFavCodeChgFreq","FAV_CODE_CHG_FREQ,1,14,1,0,false|,0,CHAR,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getNbrOfFreq","setNbrOfFreq","NBR_OF_FREQ,5,15,3,0,false|,0,INTEGER,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getAmountType","setAmountType","AMOUNT_TYPE,1,16,1,0,false|,0,CHAR,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getFavchEntry","setFavchEntry","FAVCH_ENTRY,1,17,16,0,false|,0,CHAR,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getNbrOfOccurs","setNbrOfOccurs","NBR_OF_OCCURS,5,18,4,0,false|,0,INTEGER,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getDurationType","setDurationType","DURATION_TYPE,1,19,1,0,false|,0,CHAR,0,null,null,null,null,null"));
    }
    
    public String getPagingSQL(String schemaName,boolean isSubsetMode,boolean isNext, boolean locator)
    {
        String pagingWhere = null;
        String order = null;
        if (isNext) {
            if (isSubsetMode)
                if (locator)
                    pagingWhere = ".T114X WHERE (COMPANY_CODE = :company_code) AND (PRODUCT_PREFIX = :product_prefix) AND ((PRODUCT_SUFFIX > :product_suffix) OR (PRODUCT_SUFFIX = :product_suffix AND PLAN_CODE > :plan_code) OR (PRODUCT_SUFFIX = :product_suffix AND PLAN_CODE = :plan_code AND EFFECTIVE_DATE > :effective_date) OR (PRODUCT_SUFFIX = :product_suffix AND PLAN_CODE = :plan_code AND EFFECTIVE_DATE = :effective_date AND ISSUE_STATE > :issue_state) OR (PRODUCT_SUFFIX = :product_suffix AND PLAN_CODE = :plan_code AND EFFECTIVE_DATE = :effective_date AND ISSUE_STATE = :issue_state AND LINE_OF_BUSINESS > :line_of_business) OR (PRODUCT_SUFFIX = :product_suffix AND PLAN_CODE = :plan_code AND EFFECTIVE_DATE = :effective_date AND ISSUE_STATE = :issue_state AND LINE_OF_BUSINESS = :line_of_business AND MEMO_CODE > :memo_code) OR (PRODUCT_SUFFIX = :product_suffix AND PLAN_CODE = :plan_code AND EFFECTIVE_DATE = :effective_date AND ISSUE_STATE = :issue_state AND LINE_OF_BUSINESS = :line_of_business AND MEMO_CODE = :memo_code)) ";
                else 
                    pagingWhere = ".T114X WHERE (COMPANY_CODE = :company_code) AND (PRODUCT_PREFIX = :product_prefix) AND ((PRODUCT_SUFFIX > :product_suffix) OR (PRODUCT_SUFFIX = :product_suffix AND PLAN_CODE > :plan_code) OR (PRODUCT_SUFFIX = :product_suffix AND PLAN_CODE = :plan_code AND EFFECTIVE_DATE > :effective_date) OR (PRODUCT_SUFFIX = :product_suffix AND PLAN_CODE = :plan_code AND EFFECTIVE_DATE = :effective_date AND ISSUE_STATE > :issue_state) OR (PRODUCT_SUFFIX = :product_suffix AND PLAN_CODE = :plan_code AND EFFECTIVE_DATE = :effective_date AND ISSUE_STATE = :issue_state AND LINE_OF_BUSINESS > :line_of_business) OR (PRODUCT_SUFFIX = :product_suffix AND PLAN_CODE = :plan_code AND EFFECTIVE_DATE = :effective_date AND ISSUE_STATE = :issue_state AND LINE_OF_BUSINESS = :line_of_business AND MEMO_CODE > :memo_code)) ";
            else
                if (locator)
                    pagingWhere = ".T114X WHERE (COMPANY_CODE = :company_code) AND ((PRODUCT_PREFIX > :product_prefix) OR (PRODUCT_PREFIX = :product_prefix AND PRODUCT_SUFFIX > :product_suffix) OR (PRODUCT_PREFIX = :product_prefix AND PRODUCT_SUFFIX = :product_suffix AND PLAN_CODE > :plan_code) OR (PRODUCT_PREFIX = :product_prefix AND PRODUCT_SUFFIX = :product_suffix AND PLAN_CODE = :plan_code AND EFFECTIVE_DATE > :effective_date) OR (PRODUCT_PREFIX = :product_prefix AND PRODUCT_SUFFIX = :product_suffix AND PLAN_CODE = :plan_code AND EFFECTIVE_DATE = :effective_date AND ISSUE_STATE > :issue_state) OR (PRODUCT_PREFIX = :product_prefix AND PRODUCT_SUFFIX = :product_suffix AND PLAN_CODE = :plan_code AND EFFECTIVE_DATE = :effective_date AND ISSUE_STATE = :issue_state AND LINE_OF_BUSINESS > :line_of_business) OR (PRODUCT_PREFIX = :product_prefix AND PRODUCT_SUFFIX = :product_suffix AND PLAN_CODE = :plan_code AND EFFECTIVE_DATE = :effective_date AND ISSUE_STATE = :issue_state AND LINE_OF_BUSINESS = :line_of_business AND MEMO_CODE > :memo_code) OR (PRODUCT_PREFIX = :product_prefix AND PRODUCT_SUFFIX = :product_suffix AND PLAN_CODE = :plan_code AND EFFECTIVE_DATE = :effective_date AND ISSUE_STATE = :issue_state AND LINE_OF_BUSINESS = :line_of_business AND MEMO_CODE = :memo_code)) ";
                else
                    pagingWhere = ".T114X WHERE (COMPANY_CODE = :company_code) AND ((PRODUCT_PREFIX > :product_prefix) OR (PRODUCT_PREFIX = :product_prefix AND PRODUCT_SUFFIX > :product_suffix) OR (PRODUCT_PREFIX = :product_prefix AND PRODUCT_SUFFIX = :product_suffix AND PLAN_CODE > :plan_code) OR (PRODUCT_PREFIX = :product_prefix AND PRODUCT_SUFFIX = :product_suffix AND PLAN_CODE = :plan_code AND EFFECTIVE_DATE > :effective_date) OR (PRODUCT_PREFIX = :product_prefix AND PRODUCT_SUFFIX = :product_suffix AND PLAN_CODE = :plan_code AND EFFECTIVE_DATE = :effective_date AND ISSUE_STATE > :issue_state) OR (PRODUCT_PREFIX = :product_prefix AND PRODUCT_SUFFIX = :product_suffix AND PLAN_CODE = :plan_code AND EFFECTIVE_DATE = :effective_date AND ISSUE_STATE = :issue_state AND LINE_OF_BUSINESS > :line_of_business) OR (PRODUCT_PREFIX = :product_prefix AND PRODUCT_SUFFIX = :product_suffix AND PLAN_CODE = :plan_code AND EFFECTIVE_DATE = :effective_date AND ISSUE_STATE = :issue_state AND LINE_OF_BUSINESS = :line_of_business AND MEMO_CODE > :memo_code)) ";
            order = " ORDER BY COMPANY_CODE, PRODUCT_PREFIX, PRODUCT_SUFFIX, PLAN_CODE, EFFECTIVE_DATE, ISSUE_STATE, LINE_OF_BUSINESS, MEMO_CODE";
        }
        else {
            if (isSubsetMode)
                pagingWhere = ".T114X WHERE (COMPANY_CODE = :company_code) AND (PRODUCT_PREFIX = :product_prefix) AND ((PRODUCT_SUFFIX < :product_suffix) OR (PRODUCT_SUFFIX = :product_suffix AND PLAN_CODE < :plan_code) OR (PRODUCT_SUFFIX = :product_suffix AND PLAN_CODE = :plan_code AND EFFECTIVE_DATE < :effective_date) OR (PRODUCT_SUFFIX = :product_suffix AND PLAN_CODE = :plan_code AND EFFECTIVE_DATE = :effective_date AND ISSUE_STATE < :issue_state) OR (PRODUCT_SUFFIX = :product_suffix AND PLAN_CODE = :plan_code AND EFFECTIVE_DATE = :effective_date AND ISSUE_STATE = :issue_state AND LINE_OF_BUSINESS < :line_of_business) OR (PRODUCT_SUFFIX = :product_suffix AND PLAN_CODE = :plan_code AND EFFECTIVE_DATE = :effective_date AND ISSUE_STATE = :issue_state AND LINE_OF_BUSINESS = :line_of_business AND MEMO_CODE < :memo_code)) ";
            else
                pagingWhere = ".T114X WHERE (COMPANY_CODE = :company_code) AND ((PRODUCT_PREFIX < :product_prefix) OR (PRODUCT_PREFIX = :product_prefix AND PRODUCT_SUFFIX < :product_suffix) OR (PRODUCT_PREFIX = :product_prefix AND PRODUCT_SUFFIX = :product_suffix AND PLAN_CODE < :plan_code) OR (PRODUCT_PREFIX = :product_prefix AND PRODUCT_SUFFIX = :product_suffix AND PLAN_CODE = :plan_code AND EFFECTIVE_DATE < :effective_date) OR (PRODUCT_PREFIX = :product_prefix AND PRODUCT_SUFFIX = :product_suffix AND PLAN_CODE = :plan_code AND EFFECTIVE_DATE = :effective_date AND ISSUE_STATE < :issue_state) OR (PRODUCT_PREFIX = :product_prefix AND PRODUCT_SUFFIX = :product_suffix AND PLAN_CODE = :plan_code AND EFFECTIVE_DATE = :effective_date AND ISSUE_STATE = :issue_state AND LINE_OF_BUSINESS < :line_of_business) OR (PRODUCT_PREFIX = :product_prefix AND PRODUCT_SUFFIX = :product_suffix AND PLAN_CODE = :plan_code AND EFFECTIVE_DATE = :effective_date AND ISSUE_STATE = :issue_state AND LINE_OF_BUSINESS = :line_of_business AND MEMO_CODE < :memo_code)) ";
            order = " ORDER BY COMPANY_CODE DESC, PRODUCT_PREFIX DESC, PRODUCT_SUFFIX DESC, PLAN_CODE DESC, EFFECTIVE_DATE DESC, ISSUE_STATE DESC, LINE_OF_BUSINESS DESC, MEMO_CODE DESC";
        }
        return pagingSql + schemaName + pagingWhere + order;
    }
    
    public String prepareInsertStmt(String schemaName) {
        StringBuffer sb = new StringBuffer();
        sb.append("INSERT INTO ").append(schemaName);
        sb.append(".T114X ( ");
        sb.append("COMPANY_CODE, PRODUCT_PREFIX, PRODUCT_SUFFIX, PLAN_CODE, EFFECTIVE_DATE, ISSUE_STATE, LINE_OF_BUSINESS, MEMO_CODE, INIT_FAV_CODE, FAV_CODE_CHG_IND, TRX_PRIORITY_IND, INIT_FAV_PERIOD, FREQUENCY_TYPE, FAV_CODE_CHG_FREQ, NBR_OF_FREQ, AMOUNT_TYPE, FAVCH_ENTRY, NBR_OF_OCCURS, DURATION_TYPE )");
        sb.append(" VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ? )");
        return sb.toString();
    }
    
    public String prepareUpdateStmt(String schemaName)
    {
        StringBuffer sb = new StringBuffer();
        sb.append("UPDATE ").append(schemaName);
        sb.append(".T114X ");
        sb.append(" SET COMPANY_CODE = ?, PRODUCT_PREFIX = ?, PRODUCT_SUFFIX = ?, PLAN_CODE = ?, EFFECTIVE_DATE = ?, ISSUE_STATE = ?, LINE_OF_BUSINESS = ?, MEMO_CODE = ?, INIT_FAV_CODE = ?, FAV_CODE_CHG_IND = ?, TRX_PRIORITY_IND = ?, INIT_FAV_PERIOD = ?, FREQUENCY_TYPE = ?, FAV_CODE_CHG_FREQ = ?, NBR_OF_FREQ = ?, AMOUNT_TYPE = ?, FAVCH_ENTRY = ?, NBR_OF_OCCURS = ?, DURATION_TYPE = ?");
        sb.append(" WHERE COMPANY_CODE = ? AND PRODUCT_PREFIX = ? AND PRODUCT_SUFFIX = ? AND PLAN_CODE = ? AND EFFECTIVE_DATE = ? AND ISSUE_STATE = ? AND LINE_OF_BUSINESS = ? AND MEMO_CODE = ?");
        return sb.toString();
    }
    
    public String prepareDeleteStmt(String schemaName)
    {
        StringBuffer sb = new StringBuffer();
        sb.append("DELETE FROM ").append(schemaName);
        sb.append(".T114X ");
        sb.append(" WHERE COMPANY_CODE = ? AND PRODUCT_PREFIX = ? AND PRODUCT_SUFFIX = ? AND PLAN_CODE = ? AND EFFECTIVE_DATE = ? AND ISSUE_STATE = ? AND LINE_OF_BUSINESS = ? AND MEMO_CODE = ?");
        return sb.toString();
    }
}
