package com.csc.fsg.life.pw.io.descriptor.wma;

import com.csc.fsg.life.pw.io.*;

public class T125XDescriptor
    extends TableDescriptor
{
    private static final String pagingSql = "SELECT COMPANY_CODE, TABLE_SUBSET, EFFECTIVE_DATE, STATUTORY_CODE, FUND_FAMILY, FUND_STRATEGY, FUND_NUMBER, PLAN_CODE, ISSUE_STATE, RFEE_APPLY_IND, RFEE_HOLD_DAYS, RFEE_THRESHOLD_AMOUNT, RFEE_CALC_TYPE, RFEE_PERCENT, RFEE_TRX_SUBSET_PTR FROM ";
    
    public void initialize()
    {
        setRowClass(T125XRow.class);
        setTableName("T125X");
        setTableId("125");
        super.initialize();
    }
    
    public void initializeColumnDescriptors()
    {
        super.initializeColumnDescriptors();
        addColumnDescriptor(new ColumnDescriptor(this,"getCompanyCode","setCompanyCode","COMPANY_CODE,1,1,3,0,true|,0,CHAR,1,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getTableSubset","setTableSubset","TABLE_SUBSET,1,2,16,0,true|,0,CHAR,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getEffectiveDate","setEffectiveDate","EFFECTIVE_DATE,91,3,10,0,true|,0,DATE,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getStatutoryCode","setStatutoryCode","STATUTORY_CODE,1,4,3,0,true|,0,CHAR,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getFundFamily","setFundFamily","FUND_FAMILY,1,5,2,0,true|,0,CHAR,4,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getFundStrategy","setFundStrategy","FUND_STRATEGY,1,6,2,0,true|,0,CHAR,4,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getFundNumber","setFundNumber","FUND_NUMBER,3,7,8,0,true|,0,INTEGER,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getPlanCode","setPlanCode","PLAN_CODE,1,8,6,0,true|,0,CHAR,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getIssueState","setIssueState","ISSUE_STATE,1,9,3,0,true|,0,CHAR,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getRfeeApplyInd","setRfeeApplyInd","RFEE_APPLY_IND,1,10,1,0,false|,0,CHAR,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getRfeeHoldDays","setRfeeHoldDays","RFEE_HOLD_DAYS,3,11,3,0,false|,0,INTEGER,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getRfeeThresholdAmount","setRfeeThresholdAmount","RFEE_THRESHOLD_AMOUNT,3,12,11,2,false|,0,DOUBLE,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getRfeeCalcType","setRfeeCalcType","RFEE_CALC_TYPE,1,13,1,0,false|,0,CHAR,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getRfeePercent","setRfeePercent","RFEE_PERCENT,3,14,5,3,false|,0,DOUBLE,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getRfeeTrxSubsetPtr","setRfeeTrxSubsetPtr","RFEE_TRX_SUBSET_PTR,1,15,16,0,false|,0,CHAR,0,null,null,null,null,null"));
    }
    
    public String getPagingSQL(String schemaName,boolean isSubsetMode,boolean isNext, boolean locator)
    {
        String pagingWhere = null;
        String order = null;
        if (isNext) {
            if (isSubsetMode)
                if (locator)
                    pagingWhere = ".T125X WHERE (COMPANY_CODE = :company_code) AND (TABLE_SUBSET = :table_subset) AND ((EFFECTIVE_DATE > :effective_date) OR (EFFECTIVE_DATE = :effective_date AND STATUTORY_CODE > :statutory_code) OR (EFFECTIVE_DATE = :effective_date AND STATUTORY_CODE = :statutory_code AND FUND_FAMILY > :fund_family) OR (EFFECTIVE_DATE = :effective_date AND STATUTORY_CODE = :statutory_code AND FUND_FAMILY = :fund_family AND FUND_STRATEGY > :fund_strategy) OR (EFFECTIVE_DATE = :effective_date AND STATUTORY_CODE = :statutory_code AND FUND_FAMILY = :fund_family AND FUND_STRATEGY = :fund_strategy AND FUND_NUMBER > :fund_number) OR (EFFECTIVE_DATE = :effective_date AND STATUTORY_CODE = :statutory_code AND FUND_FAMILY = :fund_family AND FUND_STRATEGY = :fund_strategy AND FUND_NUMBER = :fund_number AND PLAN_CODE > :plan_code) OR (EFFECTIVE_DATE = :effective_date AND STATUTORY_CODE = :statutory_code AND FUND_FAMILY = :fund_family AND FUND_STRATEGY = :fund_strategy AND FUND_NUMBER = :fund_number AND PLAN_CODE = :plan_code AND ISSUE_STATE > :issue_state) OR (EFFECTIVE_DATE = :effective_date AND STATUTORY_CODE = :statutory_code AND FUND_FAMILY = :fund_family AND FUND_STRATEGY = :fund_strategy AND FUND_NUMBER = :fund_number AND PLAN_CODE = :plan_code AND ISSUE_STATE = :issue_state)) ";
                else 
                    pagingWhere = ".T125X WHERE (COMPANY_CODE = :company_code) AND (TABLE_SUBSET = :table_subset) AND ((EFFECTIVE_DATE > :effective_date) OR (EFFECTIVE_DATE = :effective_date AND STATUTORY_CODE > :statutory_code) OR (EFFECTIVE_DATE = :effective_date AND STATUTORY_CODE = :statutory_code AND FUND_FAMILY > :fund_family) OR (EFFECTIVE_DATE = :effective_date AND STATUTORY_CODE = :statutory_code AND FUND_FAMILY = :fund_family AND FUND_STRATEGY > :fund_strategy) OR (EFFECTIVE_DATE = :effective_date AND STATUTORY_CODE = :statutory_code AND FUND_FAMILY = :fund_family AND FUND_STRATEGY = :fund_strategy AND FUND_NUMBER > :fund_number) OR (EFFECTIVE_DATE = :effective_date AND STATUTORY_CODE = :statutory_code AND FUND_FAMILY = :fund_family AND FUND_STRATEGY = :fund_strategy AND FUND_NUMBER = :fund_number AND PLAN_CODE > :plan_code) OR (EFFECTIVE_DATE = :effective_date AND STATUTORY_CODE = :statutory_code AND FUND_FAMILY = :fund_family AND FUND_STRATEGY = :fund_strategy AND FUND_NUMBER = :fund_number AND PLAN_CODE = :plan_code AND ISSUE_STATE > :issue_state)) ";
            else
                if (locator)
                    pagingWhere = ".T125X WHERE (COMPANY_CODE = :company_code) AND ((TABLE_SUBSET > :table_subset) OR (TABLE_SUBSET = :table_subset AND EFFECTIVE_DATE > :effective_date) OR (TABLE_SUBSET = :table_subset AND EFFECTIVE_DATE = :effective_date AND STATUTORY_CODE > :statutory_code) OR (TABLE_SUBSET = :table_subset AND EFFECTIVE_DATE = :effective_date AND STATUTORY_CODE = :statutory_code AND FUND_FAMILY > :fund_family) OR (TABLE_SUBSET = :table_subset AND EFFECTIVE_DATE = :effective_date AND STATUTORY_CODE = :statutory_code AND FUND_FAMILY = :fund_family AND FUND_STRATEGY > :fund_strategy) OR (TABLE_SUBSET = :table_subset AND EFFECTIVE_DATE = :effective_date AND STATUTORY_CODE = :statutory_code AND FUND_FAMILY = :fund_family AND FUND_STRATEGY = :fund_strategy AND FUND_NUMBER > :fund_number) OR (TABLE_SUBSET = :table_subset AND EFFECTIVE_DATE = :effective_date AND STATUTORY_CODE = :statutory_code AND FUND_FAMILY = :fund_family AND FUND_STRATEGY = :fund_strategy AND FUND_NUMBER = :fund_number AND PLAN_CODE > :plan_code) OR (TABLE_SUBSET = :table_subset AND EFFECTIVE_DATE = :effective_date AND STATUTORY_CODE = :statutory_code AND FUND_FAMILY = :fund_family AND FUND_STRATEGY = :fund_strategy AND FUND_NUMBER = :fund_number AND PLAN_CODE = :plan_code AND ISSUE_STATE > :issue_state) OR (TABLE_SUBSET = :table_subset AND EFFECTIVE_DATE = :effective_date AND STATUTORY_CODE = :statutory_code AND FUND_FAMILY = :fund_family AND FUND_STRATEGY = :fund_strategy AND FUND_NUMBER = :fund_number AND PLAN_CODE = :plan_code AND ISSUE_STATE = :issue_state)) ";
                else
                    pagingWhere = ".T125X WHERE (COMPANY_CODE = :company_code) AND ((TABLE_SUBSET > :table_subset) OR (TABLE_SUBSET = :table_subset AND EFFECTIVE_DATE > :effective_date) OR (TABLE_SUBSET = :table_subset AND EFFECTIVE_DATE = :effective_date AND STATUTORY_CODE > :statutory_code) OR (TABLE_SUBSET = :table_subset AND EFFECTIVE_DATE = :effective_date AND STATUTORY_CODE = :statutory_code AND FUND_FAMILY > :fund_family) OR (TABLE_SUBSET = :table_subset AND EFFECTIVE_DATE = :effective_date AND STATUTORY_CODE = :statutory_code AND FUND_FAMILY = :fund_family AND FUND_STRATEGY > :fund_strategy) OR (TABLE_SUBSET = :table_subset AND EFFECTIVE_DATE = :effective_date AND STATUTORY_CODE = :statutory_code AND FUND_FAMILY = :fund_family AND FUND_STRATEGY = :fund_strategy AND FUND_NUMBER > :fund_number) OR (TABLE_SUBSET = :table_subset AND EFFECTIVE_DATE = :effective_date AND STATUTORY_CODE = :statutory_code AND FUND_FAMILY = :fund_family AND FUND_STRATEGY = :fund_strategy AND FUND_NUMBER = :fund_number AND PLAN_CODE > :plan_code) OR (TABLE_SUBSET = :table_subset AND EFFECTIVE_DATE = :effective_date AND STATUTORY_CODE = :statutory_code AND FUND_FAMILY = :fund_family AND FUND_STRATEGY = :fund_strategy AND FUND_NUMBER = :fund_number AND PLAN_CODE = :plan_code AND ISSUE_STATE > :issue_state)) ";
            order = " ORDER BY COMPANY_CODE, TABLE_SUBSET, EFFECTIVE_DATE, STATUTORY_CODE, FUND_FAMILY, FUND_STRATEGY, FUND_NUMBER, PLAN_CODE, ISSUE_STATE";
        }
        else {
            if (isSubsetMode)
                pagingWhere = ".T125X WHERE (COMPANY_CODE = :company_code) AND (TABLE_SUBSET = :table_subset) AND ((EFFECTIVE_DATE < :effective_date) OR (EFFECTIVE_DATE = :effective_date AND STATUTORY_CODE < :statutory_code) OR (EFFECTIVE_DATE = :effective_date AND STATUTORY_CODE = :statutory_code AND FUND_FAMILY < :fund_family) OR (EFFECTIVE_DATE = :effective_date AND STATUTORY_CODE = :statutory_code AND FUND_FAMILY = :fund_family AND FUND_STRATEGY < :fund_strategy) OR (EFFECTIVE_DATE = :effective_date AND STATUTORY_CODE = :statutory_code AND FUND_FAMILY = :fund_family AND FUND_STRATEGY = :fund_strategy AND FUND_NUMBER < :fund_number) OR (EFFECTIVE_DATE = :effective_date AND STATUTORY_CODE = :statutory_code AND FUND_FAMILY = :fund_family AND FUND_STRATEGY = :fund_strategy AND FUND_NUMBER = :fund_number AND PLAN_CODE < :plan_code) OR (EFFECTIVE_DATE = :effective_date AND STATUTORY_CODE = :statutory_code AND FUND_FAMILY = :fund_family AND FUND_STRATEGY = :fund_strategy AND FUND_NUMBER = :fund_number AND PLAN_CODE = :plan_code AND ISSUE_STATE < :issue_state)) ";
            else
                pagingWhere = ".T125X WHERE (COMPANY_CODE = :company_code) AND ((TABLE_SUBSET < :table_subset) OR (TABLE_SUBSET = :table_subset AND EFFECTIVE_DATE < :effective_date) OR (TABLE_SUBSET = :table_subset AND EFFECTIVE_DATE = :effective_date AND STATUTORY_CODE < :statutory_code) OR (TABLE_SUBSET = :table_subset AND EFFECTIVE_DATE = :effective_date AND STATUTORY_CODE = :statutory_code AND FUND_FAMILY < :fund_family) OR (TABLE_SUBSET = :table_subset AND EFFECTIVE_DATE = :effective_date AND STATUTORY_CODE = :statutory_code AND FUND_FAMILY = :fund_family AND FUND_STRATEGY < :fund_strategy) OR (TABLE_SUBSET = :table_subset AND EFFECTIVE_DATE = :effective_date AND STATUTORY_CODE = :statutory_code AND FUND_FAMILY = :fund_family AND FUND_STRATEGY = :fund_strategy AND FUND_NUMBER < :fund_number) OR (TABLE_SUBSET = :table_subset AND EFFECTIVE_DATE = :effective_date AND STATUTORY_CODE = :statutory_code AND FUND_FAMILY = :fund_family AND FUND_STRATEGY = :fund_strategy AND FUND_NUMBER = :fund_number AND PLAN_CODE < :plan_code) OR (TABLE_SUBSET = :table_subset AND EFFECTIVE_DATE = :effective_date AND STATUTORY_CODE = :statutory_code AND FUND_FAMILY = :fund_family AND FUND_STRATEGY = :fund_strategy AND FUND_NUMBER = :fund_number AND PLAN_CODE = :plan_code AND ISSUE_STATE < :issue_state)) ";
            order = " ORDER BY COMPANY_CODE DESC, TABLE_SUBSET DESC, EFFECTIVE_DATE DESC, STATUTORY_CODE DESC, FUND_FAMILY DESC, FUND_STRATEGY DESC, FUND_NUMBER DESC, PLAN_CODE DESC, ISSUE_STATE DESC";
        }
        return pagingSql + schemaName + pagingWhere + order;
    }
    
    public String prepareInsertStmt(String schemaName) {
        StringBuffer sb = new StringBuffer();
        sb.append("INSERT INTO ").append(schemaName);
        sb.append(".T125X ( ");
        sb.append("COMPANY_CODE, TABLE_SUBSET, EFFECTIVE_DATE, STATUTORY_CODE, FUND_FAMILY, FUND_STRATEGY, FUND_NUMBER, PLAN_CODE, ISSUE_STATE, RFEE_APPLY_IND, RFEE_HOLD_DAYS, RFEE_THRESHOLD_AMOUNT, RFEE_CALC_TYPE, RFEE_PERCENT, RFEE_TRX_SUBSET_PTR )");
        sb.append(" VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ? )");
        return sb.toString();
    }
    
    public String prepareUpdateStmt(String schemaName)
    {
        StringBuffer sb = new StringBuffer();
        sb.append("UPDATE ").append(schemaName);
        sb.append(".T125X ");
        sb.append(" SET COMPANY_CODE = ?, TABLE_SUBSET = ?, EFFECTIVE_DATE = ?, STATUTORY_CODE = ?, FUND_FAMILY = ?, FUND_STRATEGY = ?, FUND_NUMBER = ?, PLAN_CODE = ?, ISSUE_STATE = ?, RFEE_APPLY_IND = ?, RFEE_HOLD_DAYS = ?, RFEE_THRESHOLD_AMOUNT = ?, RFEE_CALC_TYPE = ?, RFEE_PERCENT = ?, RFEE_TRX_SUBSET_PTR = ?");
        sb.append(" WHERE COMPANY_CODE = ? AND TABLE_SUBSET = ? AND EFFECTIVE_DATE = ? AND STATUTORY_CODE = ? AND FUND_FAMILY = ? AND FUND_STRATEGY = ? AND FUND_NUMBER = ? AND PLAN_CODE = ? AND ISSUE_STATE = ?");
        return sb.toString();
    }
    
    public String prepareDeleteStmt(String schemaName)
    {
        StringBuffer sb = new StringBuffer();
        sb.append("DELETE FROM ").append(schemaName);
        sb.append(".T125X ");
        sb.append(" WHERE COMPANY_CODE = ? AND TABLE_SUBSET = ? AND EFFECTIVE_DATE = ? AND STATUTORY_CODE = ? AND FUND_FAMILY = ? AND FUND_STRATEGY = ? AND FUND_NUMBER = ? AND PLAN_CODE = ? AND ISSUE_STATE = ?");
        return sb.toString();
    }
}
