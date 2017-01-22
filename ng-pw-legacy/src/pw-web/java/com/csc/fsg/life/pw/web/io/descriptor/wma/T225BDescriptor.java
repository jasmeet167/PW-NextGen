package com.csc.fsg.life.pw.web.io.descriptor.wma;

import com.csc.fsg.life.pw.web.io.*;

public class T225BDescriptor
    extends TableDescriptor
{
    private static final String pagingSql = "SELECT COMPANY_CODE, TABLE_SUBSET, ISSUE_STATE, RCPT_PERD_STRT_DT, INT_RT_EFF_DT, INTEREST_RATE FROM ";
    
    public void initialize()
    {
        setRowClass(T225BRow.class);
        setTableName("T225B");
        setTableId("225");
        super.initialize();
    }
    
    public void initializeColumnDescriptors()
    {
        super.initializeColumnDescriptors();
        addColumnDescriptor(new ColumnDescriptor(this,"getCompanyCode","setCompanyCode","COMPANY_CODE,1,1,3,0,true|,0,CHAR,1,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getTableSubset","setTableSubset","TABLE_SUBSET,1,2,16,0,true|,0,CHAR,1,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getIssueState","setIssueState","ISSUE_STATE,1,3,3,0,true|,0,CHAR,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getRcptPerdStrtDt","setRcptPerdStrtDt","RCPT_PERD_STRT_DT,91,4,10,0,true|,0,DATE,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getIntRtEffDt","setIntRtEffDt","INT_RT_EFF_DT,91,5,10,0,true|,0,DATE,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getInterestRate","setInterestRate","INTEREST_RATE,3,6,5,3,false|,0,DOUBLE,0,null,null,null,null,null"));
    }
    
    public String getPagingSQL(String schemaName,boolean isSubsetMode,boolean isNext, boolean locator)
    {
        String pagingWhere = null;
        String order = null;
        if (isNext) {
            if (isSubsetMode)
                if (locator)
                    pagingWhere = ".T225B WHERE (COMPANY_CODE = :company_code) AND (TABLE_SUBSET = :table_subset) AND ((ISSUE_STATE > :issue_state) OR (ISSUE_STATE = :issue_state AND RCPT_PERD_STRT_DT > :rcpt_perd_strt_dt) OR (ISSUE_STATE = :issue_state AND RCPT_PERD_STRT_DT = :rcpt_perd_strt_dt AND INT_RT_EFF_DT > :int_rt_eff_dt) OR (ISSUE_STATE = :issue_state AND RCPT_PERD_STRT_DT = :rcpt_perd_strt_dt AND INT_RT_EFF_DT = :int_rt_eff_dt)) ";
                else 
                    pagingWhere = ".T225B WHERE (COMPANY_CODE = :company_code) AND (TABLE_SUBSET = :table_subset) AND ((ISSUE_STATE > :issue_state) OR (ISSUE_STATE = :issue_state AND RCPT_PERD_STRT_DT > :rcpt_perd_strt_dt) OR (ISSUE_STATE = :issue_state AND RCPT_PERD_STRT_DT = :rcpt_perd_strt_dt AND INT_RT_EFF_DT > :int_rt_eff_dt)) ";
            else
                if (locator)
                    pagingWhere = ".T225B WHERE (COMPANY_CODE = :company_code) AND ((TABLE_SUBSET > :table_subset) OR (TABLE_SUBSET = :table_subset AND ISSUE_STATE > :issue_state) OR (TABLE_SUBSET = :table_subset AND ISSUE_STATE = :issue_state AND RCPT_PERD_STRT_DT > :rcpt_perd_strt_dt) OR (TABLE_SUBSET = :table_subset AND ISSUE_STATE = :issue_state AND RCPT_PERD_STRT_DT = :rcpt_perd_strt_dt AND INT_RT_EFF_DT > :int_rt_eff_dt) OR (TABLE_SUBSET = :table_subset AND ISSUE_STATE = :issue_state AND RCPT_PERD_STRT_DT = :rcpt_perd_strt_dt AND INT_RT_EFF_DT = :int_rt_eff_dt)) ";
                else
                    pagingWhere = ".T225B WHERE (COMPANY_CODE = :company_code) AND ((TABLE_SUBSET > :table_subset) OR (TABLE_SUBSET = :table_subset AND ISSUE_STATE > :issue_state) OR (TABLE_SUBSET = :table_subset AND ISSUE_STATE = :issue_state AND RCPT_PERD_STRT_DT > :rcpt_perd_strt_dt) OR (TABLE_SUBSET = :table_subset AND ISSUE_STATE = :issue_state AND RCPT_PERD_STRT_DT = :rcpt_perd_strt_dt AND INT_RT_EFF_DT > :int_rt_eff_dt)) ";
            order = " ORDER BY COMPANY_CODE, TABLE_SUBSET, ISSUE_STATE, RCPT_PERD_STRT_DT, INT_RT_EFF_DT";
        }
        else {
            if (isSubsetMode)
                pagingWhere = ".T225B WHERE (COMPANY_CODE = :company_code) AND (TABLE_SUBSET = :table_subset) AND ((ISSUE_STATE < :issue_state) OR (ISSUE_STATE = :issue_state AND RCPT_PERD_STRT_DT < :rcpt_perd_strt_dt) OR (ISSUE_STATE = :issue_state AND RCPT_PERD_STRT_DT = :rcpt_perd_strt_dt AND INT_RT_EFF_DT < :int_rt_eff_dt)) ";
            else
                pagingWhere = ".T225B WHERE (COMPANY_CODE = :company_code) AND ((TABLE_SUBSET < :table_subset) OR (TABLE_SUBSET = :table_subset AND ISSUE_STATE < :issue_state) OR (TABLE_SUBSET = :table_subset AND ISSUE_STATE = :issue_state AND RCPT_PERD_STRT_DT < :rcpt_perd_strt_dt) OR (TABLE_SUBSET = :table_subset AND ISSUE_STATE = :issue_state AND RCPT_PERD_STRT_DT = :rcpt_perd_strt_dt AND INT_RT_EFF_DT < :int_rt_eff_dt)) ";
            order = " ORDER BY COMPANY_CODE DESC, TABLE_SUBSET DESC, ISSUE_STATE DESC, RCPT_PERD_STRT_DT DESC, INT_RT_EFF_DT DESC";
        }
        return pagingSql + schemaName + pagingWhere + order;
    }
    
    public String prepareInsertStmt(String schemaName) {
        StringBuffer sb = new StringBuffer();
        sb.append("INSERT INTO ").append(schemaName);
        sb.append(".T225B ( ");
        sb.append("COMPANY_CODE, TABLE_SUBSET, ISSUE_STATE, RCPT_PERD_STRT_DT, INT_RT_EFF_DT, INTEREST_RATE )");
        sb.append(" VALUES (?, ?, ?, ?, ?, ? )");
        return sb.toString();
    }
    
    public String prepareUpdateStmt(String schemaName)
    {
        StringBuffer sb = new StringBuffer();
        sb.append("UPDATE ").append(schemaName);
        sb.append(".T225B ");
        sb.append(" SET COMPANY_CODE = ?, TABLE_SUBSET = ?, ISSUE_STATE = ?, RCPT_PERD_STRT_DT = ?, INT_RT_EFF_DT = ?, INTEREST_RATE = ?");
        sb.append(" WHERE COMPANY_CODE = ? AND TABLE_SUBSET = ? AND ISSUE_STATE = ? AND RCPT_PERD_STRT_DT = ? AND INT_RT_EFF_DT = ?");
        return sb.toString();
    }
    
    public String prepareDeleteStmt(String schemaName)
    {
        StringBuffer sb = new StringBuffer();
        sb.append("DELETE FROM ").append(schemaName);
        sb.append(".T225B ");
        sb.append(" WHERE COMPANY_CODE = ? AND TABLE_SUBSET = ? AND ISSUE_STATE = ? AND RCPT_PERD_STRT_DT = ? AND INT_RT_EFF_DT = ?");
        return sb.toString();
    }
}
