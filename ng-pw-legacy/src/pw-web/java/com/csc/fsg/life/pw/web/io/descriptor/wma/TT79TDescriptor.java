package com.csc.fsg.life.pw.web.io.descriptor.wma;

import com.csc.fsg.life.pw.web.io.*;

public class TT79TDescriptor
    extends TableDescriptor
{
    private static final String pagingSql = "SELECT COMPANY_CODE, TABLE_SUBSET, EFFECTIVE_DATE, LOB, MEMO_CODE, SOURCE_CODE, STATE, BILL_OPTION_CODE, BILLING_FREQUENCY, TOLERANCE_RULE, UNDER_AMOUNT, UNDER_PERCENT, OVER_AMOUNT, OVER_PERCENT, OVERAGE_PROC_RULE, OMB_PAYMT_TOL_AMT, OMB_REFUND_TOL_AMT FROM ";
    
    public void initialize()
    {
        setRowClass(TT79TRow.class);
        setTableName("TT79T");
        setTableId("T79");
        super.initialize();
    }
    
    public void initializeColumnDescriptors()
    {
        super.initializeColumnDescriptors();
        addColumnDescriptor(new ColumnDescriptor(this,"getCompanyCode","setCompanyCode","COMPANY_CODE,1,1,3,0,true|,0,CHAR,1,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getTableSubset","setTableSubset","TABLE_SUBSET,1,2,16,0,true|,0,CHAR,1,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getEffectiveDate","setEffectiveDate","EFFECTIVE_DATE,91,3,10,0,true|,0,DATE,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getLob","setLob","LOB,1,4,3,0,true|,0,CHAR,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getMemoCode","setMemoCode","MEMO_CODE,1,5,2,0,true|,0,CHAR,4,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getSourceCode","setSourceCode","SOURCE_CODE,1,6,2,0,true|,0,CHAR,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getState","setState","STATE,1,7,3,0,true|,0,CHAR,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getBillOptionCode","setBillOptionCode","BILL_OPTION_CODE,1,8,1,0,true|,0,CHAR,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getBillingFrequency","setBillingFrequency","BILLING_FREQUENCY,1,9,2,0,true|,0,CHAR,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getToleranceRule","setToleranceRule","TOLERANCE_RULE,1,10,1,0,false|,0,CHAR,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getUnderAmount","setUnderAmount","UNDER_AMOUNT,3,11,6,2,false|,0,DOUBLE,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getUnderPercent","setUnderPercent","UNDER_PERCENT,3,12,5,2,false|,0,DOUBLE,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getOverAmount","setOverAmount","OVER_AMOUNT,3,13,6,2,false|,0,DOUBLE,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getOverPercent","setOverPercent","OVER_PERCENT,3,14,5,2,false|,0,DOUBLE,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getOverageProcRule","setOverageProcRule","OVERAGE_PROC_RULE,1,15,1,0,false|,0,CHAR,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getOmbPaymtTolAmt","setOmbPaymtTolAmt","OMB_PAYMT_TOL_AMT,3,16,6,2,false|,0,DOUBLE,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getOmbRefundTolAmt","setOmbRefundTolAmt","OMB_REFUND_TOL_AMT,3,17,6,2,false|,0,DOUBLE,0,null,null,null,null,null"));
    }
    
    public String getPagingSQL(String schemaName,boolean isSubsetMode,boolean isNext, boolean locator)
    {
        String pagingWhere = null;
        String order = null;
        if (isNext) {
            if (isSubsetMode)
                if (locator)
                    pagingWhere = ".TT79T WHERE (COMPANY_CODE = :company_code) AND (TABLE_SUBSET = :table_subset) AND ((EFFECTIVE_DATE > :effective_date) OR (EFFECTIVE_DATE = :effective_date AND LOB > :lob) OR (EFFECTIVE_DATE = :effective_date AND LOB = :lob AND MEMO_CODE > :memo_code) OR (EFFECTIVE_DATE = :effective_date AND LOB = :lob AND MEMO_CODE = :memo_code AND SOURCE_CODE > :source_code) OR (EFFECTIVE_DATE = :effective_date AND LOB = :lob AND MEMO_CODE = :memo_code AND SOURCE_CODE = :source_code AND STATE > :state) OR (EFFECTIVE_DATE = :effective_date AND LOB = :lob AND MEMO_CODE = :memo_code AND SOURCE_CODE = :source_code AND STATE = :state AND BILL_OPTION_CODE > :bill_option_code) OR (EFFECTIVE_DATE = :effective_date AND LOB = :lob AND MEMO_CODE = :memo_code AND SOURCE_CODE = :source_code AND STATE = :state AND BILL_OPTION_CODE = :bill_option_code AND BILLING_FREQUENCY > :billing_frequency) OR (EFFECTIVE_DATE = :effective_date AND LOB = :lob AND MEMO_CODE = :memo_code AND SOURCE_CODE = :source_code AND STATE = :state AND BILL_OPTION_CODE = :bill_option_code AND BILLING_FREQUENCY = :billing_frequency)) ";
                else 
                    pagingWhere = ".TT79T WHERE (COMPANY_CODE = :company_code) AND (TABLE_SUBSET = :table_subset) AND ((EFFECTIVE_DATE > :effective_date) OR (EFFECTIVE_DATE = :effective_date AND LOB > :lob) OR (EFFECTIVE_DATE = :effective_date AND LOB = :lob AND MEMO_CODE > :memo_code) OR (EFFECTIVE_DATE = :effective_date AND LOB = :lob AND MEMO_CODE = :memo_code AND SOURCE_CODE > :source_code) OR (EFFECTIVE_DATE = :effective_date AND LOB = :lob AND MEMO_CODE = :memo_code AND SOURCE_CODE = :source_code AND STATE > :state) OR (EFFECTIVE_DATE = :effective_date AND LOB = :lob AND MEMO_CODE = :memo_code AND SOURCE_CODE = :source_code AND STATE = :state AND BILL_OPTION_CODE > :bill_option_code) OR (EFFECTIVE_DATE = :effective_date AND LOB = :lob AND MEMO_CODE = :memo_code AND SOURCE_CODE = :source_code AND STATE = :state AND BILL_OPTION_CODE = :bill_option_code AND BILLING_FREQUENCY > :billing_frequency)) ";
            else
                if (locator)
                    pagingWhere = ".TT79T WHERE (COMPANY_CODE = :company_code) AND ((TABLE_SUBSET > :table_subset) OR (TABLE_SUBSET = :table_subset AND EFFECTIVE_DATE > :effective_date) OR (TABLE_SUBSET = :table_subset AND EFFECTIVE_DATE = :effective_date AND LOB > :lob) OR (TABLE_SUBSET = :table_subset AND EFFECTIVE_DATE = :effective_date AND LOB = :lob AND MEMO_CODE > :memo_code) OR (TABLE_SUBSET = :table_subset AND EFFECTIVE_DATE = :effective_date AND LOB = :lob AND MEMO_CODE = :memo_code AND SOURCE_CODE > :source_code) OR (TABLE_SUBSET = :table_subset AND EFFECTIVE_DATE = :effective_date AND LOB = :lob AND MEMO_CODE = :memo_code AND SOURCE_CODE = :source_code AND STATE > :state) OR (TABLE_SUBSET = :table_subset AND EFFECTIVE_DATE = :effective_date AND LOB = :lob AND MEMO_CODE = :memo_code AND SOURCE_CODE = :source_code AND STATE = :state AND BILL_OPTION_CODE > :bill_option_code) OR (TABLE_SUBSET = :table_subset AND EFFECTIVE_DATE = :effective_date AND LOB = :lob AND MEMO_CODE = :memo_code AND SOURCE_CODE = :source_code AND STATE = :state AND BILL_OPTION_CODE = :bill_option_code AND BILLING_FREQUENCY > :billing_frequency) OR (TABLE_SUBSET = :table_subset AND EFFECTIVE_DATE = :effective_date AND LOB = :lob AND MEMO_CODE = :memo_code AND SOURCE_CODE = :source_code AND STATE = :state AND BILL_OPTION_CODE = :bill_option_code AND BILLING_FREQUENCY = :billing_frequency)) ";
                else
                    pagingWhere = ".TT79T WHERE (COMPANY_CODE = :company_code) AND ((TABLE_SUBSET > :table_subset) OR (TABLE_SUBSET = :table_subset AND EFFECTIVE_DATE > :effective_date) OR (TABLE_SUBSET = :table_subset AND EFFECTIVE_DATE = :effective_date AND LOB > :lob) OR (TABLE_SUBSET = :table_subset AND EFFECTIVE_DATE = :effective_date AND LOB = :lob AND MEMO_CODE > :memo_code) OR (TABLE_SUBSET = :table_subset AND EFFECTIVE_DATE = :effective_date AND LOB = :lob AND MEMO_CODE = :memo_code AND SOURCE_CODE > :source_code) OR (TABLE_SUBSET = :table_subset AND EFFECTIVE_DATE = :effective_date AND LOB = :lob AND MEMO_CODE = :memo_code AND SOURCE_CODE = :source_code AND STATE > :state) OR (TABLE_SUBSET = :table_subset AND EFFECTIVE_DATE = :effective_date AND LOB = :lob AND MEMO_CODE = :memo_code AND SOURCE_CODE = :source_code AND STATE = :state AND BILL_OPTION_CODE > :bill_option_code) OR (TABLE_SUBSET = :table_subset AND EFFECTIVE_DATE = :effective_date AND LOB = :lob AND MEMO_CODE = :memo_code AND SOURCE_CODE = :source_code AND STATE = :state AND BILL_OPTION_CODE = :bill_option_code AND BILLING_FREQUENCY > :billing_frequency)) ";
            order = " ORDER BY COMPANY_CODE, TABLE_SUBSET, EFFECTIVE_DATE, LOB, MEMO_CODE, SOURCE_CODE, STATE, BILL_OPTION_CODE, BILLING_FREQUENCY";
        }
        else {
            if (isSubsetMode)
                pagingWhere = ".TT79T WHERE (COMPANY_CODE = :company_code) AND (TABLE_SUBSET = :table_subset) AND ((EFFECTIVE_DATE < :effective_date) OR (EFFECTIVE_DATE = :effective_date AND LOB < :lob) OR (EFFECTIVE_DATE = :effective_date AND LOB = :lob AND MEMO_CODE < :memo_code) OR (EFFECTIVE_DATE = :effective_date AND LOB = :lob AND MEMO_CODE = :memo_code AND SOURCE_CODE < :source_code) OR (EFFECTIVE_DATE = :effective_date AND LOB = :lob AND MEMO_CODE = :memo_code AND SOURCE_CODE = :source_code AND STATE < :state) OR (EFFECTIVE_DATE = :effective_date AND LOB = :lob AND MEMO_CODE = :memo_code AND SOURCE_CODE = :source_code AND STATE = :state AND BILL_OPTION_CODE < :bill_option_code) OR (EFFECTIVE_DATE = :effective_date AND LOB = :lob AND MEMO_CODE = :memo_code AND SOURCE_CODE = :source_code AND STATE = :state AND BILL_OPTION_CODE = :bill_option_code AND BILLING_FREQUENCY < :billing_frequency)) ";
            else
                pagingWhere = ".TT79T WHERE (COMPANY_CODE = :company_code) AND ((TABLE_SUBSET < :table_subset) OR (TABLE_SUBSET = :table_subset AND EFFECTIVE_DATE < :effective_date) OR (TABLE_SUBSET = :table_subset AND EFFECTIVE_DATE = :effective_date AND LOB < :lob) OR (TABLE_SUBSET = :table_subset AND EFFECTIVE_DATE = :effective_date AND LOB = :lob AND MEMO_CODE < :memo_code) OR (TABLE_SUBSET = :table_subset AND EFFECTIVE_DATE = :effective_date AND LOB = :lob AND MEMO_CODE = :memo_code AND SOURCE_CODE < :source_code) OR (TABLE_SUBSET = :table_subset AND EFFECTIVE_DATE = :effective_date AND LOB = :lob AND MEMO_CODE = :memo_code AND SOURCE_CODE = :source_code AND STATE < :state) OR (TABLE_SUBSET = :table_subset AND EFFECTIVE_DATE = :effective_date AND LOB = :lob AND MEMO_CODE = :memo_code AND SOURCE_CODE = :source_code AND STATE = :state AND BILL_OPTION_CODE < :bill_option_code) OR (TABLE_SUBSET = :table_subset AND EFFECTIVE_DATE = :effective_date AND LOB = :lob AND MEMO_CODE = :memo_code AND SOURCE_CODE = :source_code AND STATE = :state AND BILL_OPTION_CODE = :bill_option_code AND BILLING_FREQUENCY < :billing_frequency)) ";
            order = " ORDER BY COMPANY_CODE DESC, TABLE_SUBSET DESC, EFFECTIVE_DATE DESC, LOB DESC, MEMO_CODE DESC, SOURCE_CODE DESC, STATE DESC, BILL_OPTION_CODE DESC, BILLING_FREQUENCY DESC";
        }
        return pagingSql + schemaName + pagingWhere + order;
    }
    
    public String prepareInsertStmt(String schemaName) {
        StringBuffer sb = new StringBuffer();
        sb.append("INSERT INTO ").append(schemaName);
        sb.append(".TT79T ( ");
        sb.append("COMPANY_CODE, TABLE_SUBSET, EFFECTIVE_DATE, LOB, MEMO_CODE, SOURCE_CODE, STATE, BILL_OPTION_CODE, BILLING_FREQUENCY, TOLERANCE_RULE, UNDER_AMOUNT, UNDER_PERCENT, OVER_AMOUNT, OVER_PERCENT, OVERAGE_PROC_RULE, OMB_PAYMT_TOL_AMT, OMB_REFUND_TOL_AMT )");
        sb.append(" VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ? )");
        return sb.toString();
    }
    
    public String prepareUpdateStmt(String schemaName)
    {
        StringBuffer sb = new StringBuffer();
        sb.append("UPDATE ").append(schemaName);
        sb.append(".TT79T ");
        sb.append(" SET COMPANY_CODE = ?, TABLE_SUBSET = ?, EFFECTIVE_DATE = ?, LOB = ?, MEMO_CODE = ?, SOURCE_CODE = ?, STATE = ?, BILL_OPTION_CODE = ?, BILLING_FREQUENCY = ?, TOLERANCE_RULE = ?, UNDER_AMOUNT = ?, UNDER_PERCENT = ?, OVER_AMOUNT = ?, OVER_PERCENT = ?, OVERAGE_PROC_RULE = ?, OMB_PAYMT_TOL_AMT = ?, OMB_REFUND_TOL_AMT = ?");
        sb.append(" WHERE COMPANY_CODE = ? AND TABLE_SUBSET = ? AND EFFECTIVE_DATE = ? AND LOB = ? AND MEMO_CODE = ? AND SOURCE_CODE = ? AND STATE = ? AND BILL_OPTION_CODE = ? AND BILLING_FREQUENCY = ?");
        return sb.toString();
    }
    
    public String prepareDeleteStmt(String schemaName)
    {
        StringBuffer sb = new StringBuffer();
        sb.append("DELETE FROM ").append(schemaName);
        sb.append(".TT79T ");
        sb.append(" WHERE COMPANY_CODE = ? AND TABLE_SUBSET = ? AND EFFECTIVE_DATE = ? AND LOB = ? AND MEMO_CODE = ? AND SOURCE_CODE = ? AND STATE = ? AND BILL_OPTION_CODE = ? AND BILLING_FREQUENCY = ?");
        return sb.toString();
    }
}
