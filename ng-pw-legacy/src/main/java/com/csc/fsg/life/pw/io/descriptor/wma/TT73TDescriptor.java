package com.csc.fsg.life.pw.io.descriptor.wma;

import com.csc.fsg.life.pw.io.*;

public class TT73TDescriptor
    extends TableDescriptor
{
    private static final String pagingSql = "SELECT COMPANY_CODE, TABLE_SUBSET, EFFECTIVE_DATE, BILL_OPTION_CD, SELECTION_NO, TERM_REIN_PERIOD, POL_ASSEMBLY_RULE, COV_ASSEMBLY_RULE, GEN_ROUND_RULE, MODAL_ROUND_RULE, EXCEPTION_RULE, COV_SEQ_RULE, OTHER_SEQ_RULE, COLL_FEE_RULE, COLL_FEE_AMT, SEMI_ANNUAL_FACTOR, QUARTERLY_FACTOR, MONTHLY_FACTOR, ANNUAL_FACTOR FROM ";
    
    public void initialize()
    {
        setRowClass(TT73TRow.class);
        setTableName("TT73T");
        setTableId("T73");
        super.initialize();
    }
    
    public void initializeColumnDescriptors()
    {
        super.initializeColumnDescriptors();
        addColumnDescriptor(new ColumnDescriptor(this,"getCompanyCode","setCompanyCode","COMPANY_CODE,1,1,3,0,true|,0,CHAR,1,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getTableSubset","setTableSubset","TABLE_SUBSET,1,2,16,0,true|,0,CHAR,1,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getEffectiveDate","setEffectiveDate","EFFECTIVE_DATE,91,3,10,0,true|,0,DATE,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getBillOptionCd","setBillOptionCd","BILL_OPTION_CD,1,4,1,0,true|,0,CHAR,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getSelectionNo","setSelectionNo","SELECTION_NO,1,5,3,0,true|,0,CHAR,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getTermReinPeriod","setTermReinPeriod","TERM_REIN_PERIOD,1,6,1,0,true|,0,CHAR,2,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getPolAssemblyRule","setPolAssemblyRule","POL_ASSEMBLY_RULE,1,7,1,0,false|,0,CHAR,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getCovAssemblyRule","setCovAssemblyRule","COV_ASSEMBLY_RULE,1,8,1,0,false|,0,CHAR,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getGenRoundRule","setGenRoundRule","GEN_ROUND_RULE,1,9,1,0,false|,0,CHAR,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getModalRoundRule","setModalRoundRule","MODAL_ROUND_RULE,1,10,1,0,false|,0,CHAR,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getExceptionRule","setExceptionRule","EXCEPTION_RULE,1,11,1,0,false|,0,CHAR,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getCovSeqRule","setCovSeqRule","COV_SEQ_RULE,1,12,1,0,false|,0,CHAR,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getOtherSeqRule","setOtherSeqRule","OTHER_SEQ_RULE,1,13,1,0,false|,0,CHAR,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getCollFeeRule","setCollFeeRule","COLL_FEE_RULE,1,14,1,0,false|,0,CHAR,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getCollFeeAmt","setCollFeeAmt","COLL_FEE_AMT,3,15,5,2,false|,0,DOUBLE,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getSemiAnnualFactor","setSemiAnnualFactor","SEMI_ANNUAL_FACTOR,3,16,6,6,false|,0,DOUBLE,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getQuarterlyFactor","setQuarterlyFactor","QUARTERLY_FACTOR,3,17,6,6,false|,0,DOUBLE,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getMonthlyFactor","setMonthlyFactor","MONTHLY_FACTOR,3,18,6,6,false|,0,DOUBLE,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getAnnualFactor","setAnnualFactor","ANNUAL_FACTOR,3,19,6,6,false|,0,DOUBLE,0,null,null,null,null,null"));
    }
    
    public String getPagingSQL(String schemaName,boolean isSubsetMode,boolean isNext, boolean locator)
    {
        String pagingWhere = null;
        String order = null;
        if (isNext) {
            if (isSubsetMode)
                if (locator)
                    pagingWhere = ".TT73T WHERE (COMPANY_CODE = :company_code) AND (TABLE_SUBSET = :table_subset) AND ((EFFECTIVE_DATE > :effective_date) OR (EFFECTIVE_DATE = :effective_date AND BILL_OPTION_CD > :bill_option_cd) OR (EFFECTIVE_DATE = :effective_date AND BILL_OPTION_CD = :bill_option_cd AND SELECTION_NO > :selection_no) OR (EFFECTIVE_DATE = :effective_date AND BILL_OPTION_CD = :bill_option_cd AND SELECTION_NO = :selection_no AND TERM_REIN_PERIOD > :term_rein_period) OR (EFFECTIVE_DATE = :effective_date AND BILL_OPTION_CD = :bill_option_cd AND SELECTION_NO = :selection_no AND TERM_REIN_PERIOD = :term_rein_period)) ";
                else 
                    pagingWhere = ".TT73T WHERE (COMPANY_CODE = :company_code) AND (TABLE_SUBSET = :table_subset) AND ((EFFECTIVE_DATE > :effective_date) OR (EFFECTIVE_DATE = :effective_date AND BILL_OPTION_CD > :bill_option_cd) OR (EFFECTIVE_DATE = :effective_date AND BILL_OPTION_CD = :bill_option_cd AND SELECTION_NO > :selection_no) OR (EFFECTIVE_DATE = :effective_date AND BILL_OPTION_CD = :bill_option_cd AND SELECTION_NO = :selection_no AND TERM_REIN_PERIOD > :term_rein_period)) ";
            else
                if (locator)
                    pagingWhere = ".TT73T WHERE (COMPANY_CODE = :company_code) AND ((TABLE_SUBSET > :table_subset) OR (TABLE_SUBSET = :table_subset AND EFFECTIVE_DATE > :effective_date) OR (TABLE_SUBSET = :table_subset AND EFFECTIVE_DATE = :effective_date AND BILL_OPTION_CD > :bill_option_cd) OR (TABLE_SUBSET = :table_subset AND EFFECTIVE_DATE = :effective_date AND BILL_OPTION_CD = :bill_option_cd AND SELECTION_NO > :selection_no) OR (TABLE_SUBSET = :table_subset AND EFFECTIVE_DATE = :effective_date AND BILL_OPTION_CD = :bill_option_cd AND SELECTION_NO = :selection_no AND TERM_REIN_PERIOD > :term_rein_period) OR (TABLE_SUBSET = :table_subset AND EFFECTIVE_DATE = :effective_date AND BILL_OPTION_CD = :bill_option_cd AND SELECTION_NO = :selection_no AND TERM_REIN_PERIOD = :term_rein_period)) ";
                else
                    pagingWhere = ".TT73T WHERE (COMPANY_CODE = :company_code) AND ((TABLE_SUBSET > :table_subset) OR (TABLE_SUBSET = :table_subset AND EFFECTIVE_DATE > :effective_date) OR (TABLE_SUBSET = :table_subset AND EFFECTIVE_DATE = :effective_date AND BILL_OPTION_CD > :bill_option_cd) OR (TABLE_SUBSET = :table_subset AND EFFECTIVE_DATE = :effective_date AND BILL_OPTION_CD = :bill_option_cd AND SELECTION_NO > :selection_no) OR (TABLE_SUBSET = :table_subset AND EFFECTIVE_DATE = :effective_date AND BILL_OPTION_CD = :bill_option_cd AND SELECTION_NO = :selection_no AND TERM_REIN_PERIOD > :term_rein_period)) ";
            order = " ORDER BY COMPANY_CODE, TABLE_SUBSET, EFFECTIVE_DATE, BILL_OPTION_CD, SELECTION_NO, TERM_REIN_PERIOD";
        }
        else {
            if (isSubsetMode)
                pagingWhere = ".TT73T WHERE (COMPANY_CODE = :company_code) AND (TABLE_SUBSET = :table_subset) AND ((EFFECTIVE_DATE < :effective_date) OR (EFFECTIVE_DATE = :effective_date AND BILL_OPTION_CD < :bill_option_cd) OR (EFFECTIVE_DATE = :effective_date AND BILL_OPTION_CD = :bill_option_cd AND SELECTION_NO < :selection_no) OR (EFFECTIVE_DATE = :effective_date AND BILL_OPTION_CD = :bill_option_cd AND SELECTION_NO = :selection_no AND TERM_REIN_PERIOD < :term_rein_period)) ";
            else
                pagingWhere = ".TT73T WHERE (COMPANY_CODE = :company_code) AND ((TABLE_SUBSET < :table_subset) OR (TABLE_SUBSET = :table_subset AND EFFECTIVE_DATE < :effective_date) OR (TABLE_SUBSET = :table_subset AND EFFECTIVE_DATE = :effective_date AND BILL_OPTION_CD < :bill_option_cd) OR (TABLE_SUBSET = :table_subset AND EFFECTIVE_DATE = :effective_date AND BILL_OPTION_CD = :bill_option_cd AND SELECTION_NO < :selection_no) OR (TABLE_SUBSET = :table_subset AND EFFECTIVE_DATE = :effective_date AND BILL_OPTION_CD = :bill_option_cd AND SELECTION_NO = :selection_no AND TERM_REIN_PERIOD < :term_rein_period)) ";
            order = " ORDER BY COMPANY_CODE DESC, TABLE_SUBSET DESC, EFFECTIVE_DATE DESC, BILL_OPTION_CD DESC, SELECTION_NO DESC, TERM_REIN_PERIOD DESC";
        }
        return pagingSql + schemaName + pagingWhere + order;
    }
    
    public String prepareInsertStmt(String schemaName) {
        StringBuffer sb = new StringBuffer();
        sb.append("INSERT INTO ").append(schemaName);
        sb.append(".TT73T ( ");
        sb.append("COMPANY_CODE, TABLE_SUBSET, EFFECTIVE_DATE, BILL_OPTION_CD, SELECTION_NO, TERM_REIN_PERIOD, POL_ASSEMBLY_RULE, COV_ASSEMBLY_RULE, GEN_ROUND_RULE, MODAL_ROUND_RULE, EXCEPTION_RULE, COV_SEQ_RULE, OTHER_SEQ_RULE, COLL_FEE_RULE, COLL_FEE_AMT, SEMI_ANNUAL_FACTOR, QUARTERLY_FACTOR, MONTHLY_FACTOR, ANNUAL_FACTOR )");
        sb.append(" VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ? )");
        return sb.toString();
    }
    
    public String prepareUpdateStmt(String schemaName)
    {
        StringBuffer sb = new StringBuffer();
        sb.append("UPDATE ").append(schemaName);
        sb.append(".TT73T ");
        sb.append(" SET COMPANY_CODE = ?, TABLE_SUBSET = ?, EFFECTIVE_DATE = ?, BILL_OPTION_CD = ?, SELECTION_NO = ?, TERM_REIN_PERIOD = ?, POL_ASSEMBLY_RULE = ?, COV_ASSEMBLY_RULE = ?, GEN_ROUND_RULE = ?, MODAL_ROUND_RULE = ?, EXCEPTION_RULE = ?, COV_SEQ_RULE = ?, OTHER_SEQ_RULE = ?, COLL_FEE_RULE = ?, COLL_FEE_AMT = ?, SEMI_ANNUAL_FACTOR = ?, QUARTERLY_FACTOR = ?, MONTHLY_FACTOR = ?, ANNUAL_FACTOR = ?");
        sb.append(" WHERE COMPANY_CODE = ? AND TABLE_SUBSET = ? AND EFFECTIVE_DATE = ? AND BILL_OPTION_CD = ? AND SELECTION_NO = ? AND TERM_REIN_PERIOD = ?");
        return sb.toString();
    }
    
    public String prepareDeleteStmt(String schemaName)
    {
        StringBuffer sb = new StringBuffer();
        sb.append("DELETE FROM ").append(schemaName);
        sb.append(".TT73T ");
        sb.append(" WHERE COMPANY_CODE = ? AND TABLE_SUBSET = ? AND EFFECTIVE_DATE = ? AND BILL_OPTION_CD = ? AND SELECTION_NO = ? AND TERM_REIN_PERIOD = ?");
        return sb.toString();
    }
}
