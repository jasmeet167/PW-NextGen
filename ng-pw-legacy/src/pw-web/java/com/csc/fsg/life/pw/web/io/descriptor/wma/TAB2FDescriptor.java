package com.csc.fsg.life.pw.web.io.descriptor.wma;

import com.csc.fsg.life.pw.web.io.*;

public class TAB2FDescriptor
    extends TableDescriptor
{
    private static final String pagingSql = "SELECT COMPANY_CODE, TABLE_SUBSET, CASH_FLOW_DB_IND, CASH_FLOW_DUR_YRS, SURR_PENALTY_AMT, SURR_PENALTY_PCT, FREE_PART_YR_IND, NUM_FREE_PART_YR, FREE_PCT_YR_IND, MAX_FREE_PART_PCT, FREE_AMOUNT_IND, MAX_FREE_PART_AMT, EXCESS_AMT_PEN_IND, INT_PENALTY_IND, INT_PENALTY_MONTHS, BAIL_OUT_IND, BAIL_OUT_MONTHS, BAIL_OUT_RATE FROM ";
    
    public void initialize()
    {
        setRowClass(TAB2FRow.class);
        setTableName("TAB2F");
        setTableId("AB2");
        super.initialize();
    }
    
    public void initializeColumnDescriptors()
    {
        super.initializeColumnDescriptors();
        addColumnDescriptor(new ColumnDescriptor(this,"getCompanyCode","setCompanyCode","COMPANY_CODE,1,1,3,0,true|,0,CHAR,1,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getTableSubset","setTableSubset","TABLE_SUBSET,1,2,16,0,true|,0,CHAR,1,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getCashFlowDbInd","setCashFlowDbInd","CASH_FLOW_DB_IND,1,3,1,0,true|,0,CHAR,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getCashFlowDurYrs","setCashFlowDurYrs","CASH_FLOW_DUR_YRS,3,4,3,0,true|,0,INTEGER,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getSurrPenaltyAmt","setSurrPenaltyAmt","SURR_PENALTY_AMT,3,5,7,2,false|,0,DOUBLE,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getSurrPenaltyPct","setSurrPenaltyPct","SURR_PENALTY_PCT,3,6,6,3,false|,0,DOUBLE,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getFreePartYrInd","setFreePartYrInd","FREE_PART_YR_IND,1,7,1,0,false|,0,CHAR,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getNumFreePartYr","setNumFreePartYr","NUM_FREE_PART_YR,3,8,3,0,false|,0,INTEGER,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getFreePctYrInd","setFreePctYrInd","FREE_PCT_YR_IND,1,9,1,0,false|,0,CHAR,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getMaxFreePartPct","setMaxFreePartPct","MAX_FREE_PART_PCT,3,10,6,3,false|,0,DOUBLE,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getFreeAmountInd","setFreeAmountInd","FREE_AMOUNT_IND,1,11,1,0,false|,0,CHAR,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getMaxFreePartAmt","setMaxFreePartAmt","MAX_FREE_PART_AMT,3,12,10,3,false|,0,DOUBLE,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getExcessAmtPenInd","setExcessAmtPenInd","EXCESS_AMT_PEN_IND,1,13,1,0,false|,0,CHAR,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getIntPenaltyInd","setIntPenaltyInd","INT_PENALTY_IND,1,14,1,0,false|,0,CHAR,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getIntPenaltyMonths","setIntPenaltyMonths","INT_PENALTY_MONTHS,3,15,3,0,false|,0,INTEGER,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getBailOutInd","setBailOutInd","BAIL_OUT_IND,1,16,1,0,false|,0,CHAR,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getBailOutMonths","setBailOutMonths","BAIL_OUT_MONTHS,3,17,3,0,false|,0,INTEGER,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getBailOutRate","setBailOutRate","BAIL_OUT_RATE,3,18,6,3,false|,0,DOUBLE,0,null,null,null,null,null"));
    }
    
    public String getPagingSQL(String schemaName,boolean isSubsetMode,boolean isNext, boolean locator)
    {
        String pagingWhere = null;
        String order = null;
        if (isNext) {
            if (isSubsetMode)
                if (locator)
                    pagingWhere = ".TAB2F WHERE (COMPANY_CODE = :company_code) AND (TABLE_SUBSET = :table_subset) AND ((CASH_FLOW_DB_IND > :cash_flow_db_ind) OR (CASH_FLOW_DB_IND = :cash_flow_db_ind AND CASH_FLOW_DUR_YRS > :cash_flow_dur_yrs) OR (CASH_FLOW_DB_IND = :cash_flow_db_ind AND CASH_FLOW_DUR_YRS = :cash_flow_dur_yrs)) ";
                else 
                    pagingWhere = ".TAB2F WHERE (COMPANY_CODE = :company_code) AND (TABLE_SUBSET = :table_subset) AND ((CASH_FLOW_DB_IND > :cash_flow_db_ind) OR (CASH_FLOW_DB_IND = :cash_flow_db_ind AND CASH_FLOW_DUR_YRS > :cash_flow_dur_yrs)) ";
            else
                if (locator)
                    pagingWhere = ".TAB2F WHERE (COMPANY_CODE = :company_code) AND ((TABLE_SUBSET > :table_subset) OR (TABLE_SUBSET = :table_subset AND CASH_FLOW_DB_IND > :cash_flow_db_ind) OR (TABLE_SUBSET = :table_subset AND CASH_FLOW_DB_IND = :cash_flow_db_ind AND CASH_FLOW_DUR_YRS > :cash_flow_dur_yrs) OR (TABLE_SUBSET = :table_subset AND CASH_FLOW_DB_IND = :cash_flow_db_ind AND CASH_FLOW_DUR_YRS = :cash_flow_dur_yrs)) ";
                else
                    pagingWhere = ".TAB2F WHERE (COMPANY_CODE = :company_code) AND ((TABLE_SUBSET > :table_subset) OR (TABLE_SUBSET = :table_subset AND CASH_FLOW_DB_IND > :cash_flow_db_ind) OR (TABLE_SUBSET = :table_subset AND CASH_FLOW_DB_IND = :cash_flow_db_ind AND CASH_FLOW_DUR_YRS > :cash_flow_dur_yrs)) ";
            order = " ORDER BY COMPANY_CODE, TABLE_SUBSET, CASH_FLOW_DB_IND, CASH_FLOW_DUR_YRS";
        }
        else {
            if (isSubsetMode)
                pagingWhere = ".TAB2F WHERE (COMPANY_CODE = :company_code) AND (TABLE_SUBSET = :table_subset) AND ((CASH_FLOW_DB_IND < :cash_flow_db_ind) OR (CASH_FLOW_DB_IND = :cash_flow_db_ind AND CASH_FLOW_DUR_YRS < :cash_flow_dur_yrs)) ";
            else
                pagingWhere = ".TAB2F WHERE (COMPANY_CODE = :company_code) AND ((TABLE_SUBSET < :table_subset) OR (TABLE_SUBSET = :table_subset AND CASH_FLOW_DB_IND < :cash_flow_db_ind) OR (TABLE_SUBSET = :table_subset AND CASH_FLOW_DB_IND = :cash_flow_db_ind AND CASH_FLOW_DUR_YRS < :cash_flow_dur_yrs)) ";
            order = " ORDER BY COMPANY_CODE DESC, TABLE_SUBSET DESC, CASH_FLOW_DB_IND DESC, CASH_FLOW_DUR_YRS DESC";
        }
        return pagingSql + schemaName + pagingWhere + order;
    }
    
    public String prepareInsertStmt(String schemaName) {
        StringBuffer sb = new StringBuffer();
        sb.append("INSERT INTO ").append(schemaName);
        sb.append(".TAB2F ( ");
        sb.append("COMPANY_CODE, TABLE_SUBSET, CASH_FLOW_DB_IND, CASH_FLOW_DUR_YRS, SURR_PENALTY_AMT, SURR_PENALTY_PCT, FREE_PART_YR_IND, NUM_FREE_PART_YR, FREE_PCT_YR_IND, MAX_FREE_PART_PCT, FREE_AMOUNT_IND, MAX_FREE_PART_AMT, EXCESS_AMT_PEN_IND, INT_PENALTY_IND, INT_PENALTY_MONTHS, BAIL_OUT_IND, BAIL_OUT_MONTHS, BAIL_OUT_RATE )");
        sb.append(" VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ? )");
        return sb.toString();
    }
    
    public String prepareUpdateStmt(String schemaName)
    {
        StringBuffer sb = new StringBuffer();
        sb.append("UPDATE ").append(schemaName);
        sb.append(".TAB2F ");
        sb.append(" SET COMPANY_CODE = ?, TABLE_SUBSET = ?, CASH_FLOW_DB_IND = ?, CASH_FLOW_DUR_YRS = ?, SURR_PENALTY_AMT = ?, SURR_PENALTY_PCT = ?, FREE_PART_YR_IND = ?, NUM_FREE_PART_YR = ?, FREE_PCT_YR_IND = ?, MAX_FREE_PART_PCT = ?, FREE_AMOUNT_IND = ?, MAX_FREE_PART_AMT = ?, EXCESS_AMT_PEN_IND = ?, INT_PENALTY_IND = ?, INT_PENALTY_MONTHS = ?, BAIL_OUT_IND = ?, BAIL_OUT_MONTHS = ?, BAIL_OUT_RATE = ?");
        sb.append(" WHERE COMPANY_CODE = ? AND TABLE_SUBSET = ? AND CASH_FLOW_DB_IND = ? AND CASH_FLOW_DUR_YRS = ?");
        return sb.toString();
    }
    
    public String prepareDeleteStmt(String schemaName)
    {
        StringBuffer sb = new StringBuffer();
        sb.append("DELETE FROM ").append(schemaName);
        sb.append(".TAB2F ");
        sb.append(" WHERE COMPANY_CODE = ? AND TABLE_SUBSET = ? AND CASH_FLOW_DB_IND = ? AND CASH_FLOW_DUR_YRS = ?");
        return sb.toString();
    }
}
