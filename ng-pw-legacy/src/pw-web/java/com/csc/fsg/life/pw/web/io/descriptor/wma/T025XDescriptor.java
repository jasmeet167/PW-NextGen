package com.csc.fsg.life.pw.web.io.descriptor.wma;

import com.csc.fsg.life.pw.web.io.*;

public class T025XDescriptor
    extends TableDescriptor
{
    private static final String pagingSql = "SELECT COMPANY_CODE, PRODUCT_PREFIX, TABLE_SUBSET, ISSUE_STATE, RCPT_PERD_STRT_DT, INT_RT_EFF_DT, DURATION_MONTHS, SETTL_DATE_IND, INTEREST_RATE FROM ";
    
    public void initialize()
    {
        setRowClass(T025XRow.class);
        setTableName("T025X");
        setTableId("025");
        super.initialize();
    }
    
    public void initializeColumnDescriptors()
    {
        super.initializeColumnDescriptors();
        addColumnDescriptor(new ColumnDescriptor(this,"getCompanyCode","setCompanyCode","COMPANY_CODE,1,1,3,0,true|A,0,CHAR,1,null,null,null,null,null|T,0,CHAR,1,null,null,null,null,null|U,0,CHAR,1,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getProductPrefix","setProductPrefix","PRODUCT_PREFIX,1,2,1,0,true|A,0,CHAR,1,null,null,null,null,null|T,0,CHAR,1,null,null,null,null,null|U,0,CHAR,1,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getTableSubset","setTableSubset","TABLE_SUBSET,1,3,16,0,true|A,0,CHAR,1,null,null,null,null,null|T,0,CHAR,1,null,null,null,null,null|U,0,CHAR,1,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getIssueState","setIssueState","ISSUE_STATE,1,4,3,0,true|A,0,CHAR,0,null,null,null,null,null|T,0,CHAR,0,null,null,null,null,null|U,0,CHAR,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getRcptPerdStrtDt","setRcptPerdStrtDt","RCPT_PERD_STRT_DT,91,5,10,0,true|A,0,DATE,0,null,null,null,null,null|T,0,DATE,0,null,null,null,null,null|U,0,DATE,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getIntRtEffDt","setIntRtEffDt","INT_RT_EFF_DT,91,6,10,0,true|A,0,DATE,0,null,null,null,null,null|T,0,DATE,0,null,null,null,null,null|U,0,DATE,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getDurationMonths","setDurationMonths","DURATION_MONTHS,3,7,3,0,true|A,0,INTEGER,0,null,null,null,null,null|T,0,INTEGER,2,null,null,null,null,null|U,0,INTEGER,2,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getSettlDateInd","setSettlDateInd","SETTL_DATE_IND,1,8,1,0,false|A,0,CHAR,0,null,null,null,null,null|T,0,CHAR,4,null,null,null,null,null|U,0,CHAR,4,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getInterestRate","setInterestRate","INTEREST_RATE,3,9,5,3,false|A,0,DOUBLE,0,null,null,null,null,null|T,0,DOUBLE,0,null,null,null,null,null|U,0,DOUBLE,0,null,null,null,null,null"));
    }
    
    public String getPagingSQL(String schemaName,boolean isSubsetMode,boolean isNext, boolean locator)
    {
        String pagingWhere = null;
        String order = null;
        if (isNext) {
            if (isSubsetMode)
                if (locator)
                    pagingWhere = ".T025X WHERE (COMPANY_CODE = :company_code) AND (PRODUCT_PREFIX = :product_prefix) AND (TABLE_SUBSET = :table_subset) AND ((ISSUE_STATE > :issue_state) OR (ISSUE_STATE = :issue_state AND RCPT_PERD_STRT_DT > :rcpt_perd_strt_dt) OR (ISSUE_STATE = :issue_state AND RCPT_PERD_STRT_DT = :rcpt_perd_strt_dt AND INT_RT_EFF_DT > :int_rt_eff_dt) OR (ISSUE_STATE = :issue_state AND RCPT_PERD_STRT_DT = :rcpt_perd_strt_dt AND INT_RT_EFF_DT = :int_rt_eff_dt AND DURATION_MONTHS > :duration_months) OR (ISSUE_STATE = :issue_state AND RCPT_PERD_STRT_DT = :rcpt_perd_strt_dt AND INT_RT_EFF_DT = :int_rt_eff_dt AND DURATION_MONTHS = :duration_months)) ";
                else 
                    pagingWhere = ".T025X WHERE (COMPANY_CODE = :company_code) AND (PRODUCT_PREFIX = :product_prefix) AND (TABLE_SUBSET = :table_subset) AND ((ISSUE_STATE > :issue_state) OR (ISSUE_STATE = :issue_state AND RCPT_PERD_STRT_DT > :rcpt_perd_strt_dt) OR (ISSUE_STATE = :issue_state AND RCPT_PERD_STRT_DT = :rcpt_perd_strt_dt AND INT_RT_EFF_DT > :int_rt_eff_dt) OR (ISSUE_STATE = :issue_state AND RCPT_PERD_STRT_DT = :rcpt_perd_strt_dt AND INT_RT_EFF_DT = :int_rt_eff_dt AND DURATION_MONTHS > :duration_months)) ";
            else
                if (locator)
                    pagingWhere = ".T025X WHERE (COMPANY_CODE = :company_code) AND ((PRODUCT_PREFIX > :product_prefix) OR (PRODUCT_PREFIX = :product_prefix AND TABLE_SUBSET > :table_subset) OR (PRODUCT_PREFIX = :product_prefix AND TABLE_SUBSET = :table_subset AND ISSUE_STATE > :issue_state) OR (PRODUCT_PREFIX = :product_prefix AND TABLE_SUBSET = :table_subset AND ISSUE_STATE = :issue_state AND RCPT_PERD_STRT_DT > :rcpt_perd_strt_dt) OR (PRODUCT_PREFIX = :product_prefix AND TABLE_SUBSET = :table_subset AND ISSUE_STATE = :issue_state AND RCPT_PERD_STRT_DT = :rcpt_perd_strt_dt AND INT_RT_EFF_DT > :int_rt_eff_dt) OR (PRODUCT_PREFIX = :product_prefix AND TABLE_SUBSET = :table_subset AND ISSUE_STATE = :issue_state AND RCPT_PERD_STRT_DT = :rcpt_perd_strt_dt AND INT_RT_EFF_DT = :int_rt_eff_dt AND DURATION_MONTHS > :duration_months) OR (PRODUCT_PREFIX = :product_prefix AND TABLE_SUBSET = :table_subset AND ISSUE_STATE = :issue_state AND RCPT_PERD_STRT_DT = :rcpt_perd_strt_dt AND INT_RT_EFF_DT = :int_rt_eff_dt AND DURATION_MONTHS = :duration_months)) ";
                else
                    pagingWhere = ".T025X WHERE (COMPANY_CODE = :company_code) AND ((PRODUCT_PREFIX > :product_prefix) OR (PRODUCT_PREFIX = :product_prefix AND TABLE_SUBSET > :table_subset) OR (PRODUCT_PREFIX = :product_prefix AND TABLE_SUBSET = :table_subset AND ISSUE_STATE > :issue_state) OR (PRODUCT_PREFIX = :product_prefix AND TABLE_SUBSET = :table_subset AND ISSUE_STATE = :issue_state AND RCPT_PERD_STRT_DT > :rcpt_perd_strt_dt) OR (PRODUCT_PREFIX = :product_prefix AND TABLE_SUBSET = :table_subset AND ISSUE_STATE = :issue_state AND RCPT_PERD_STRT_DT = :rcpt_perd_strt_dt AND INT_RT_EFF_DT > :int_rt_eff_dt) OR (PRODUCT_PREFIX = :product_prefix AND TABLE_SUBSET = :table_subset AND ISSUE_STATE = :issue_state AND RCPT_PERD_STRT_DT = :rcpt_perd_strt_dt AND INT_RT_EFF_DT = :int_rt_eff_dt AND DURATION_MONTHS > :duration_months)) ";
            order = " ORDER BY COMPANY_CODE, PRODUCT_PREFIX, TABLE_SUBSET, ISSUE_STATE, RCPT_PERD_STRT_DT, INT_RT_EFF_DT, DURATION_MONTHS";
        }
        else {
            if (isSubsetMode)
                pagingWhere = ".T025X WHERE (COMPANY_CODE = :company_code) AND (PRODUCT_PREFIX = :product_prefix) AND (TABLE_SUBSET = :table_subset) AND ((ISSUE_STATE < :issue_state) OR (ISSUE_STATE = :issue_state AND RCPT_PERD_STRT_DT < :rcpt_perd_strt_dt) OR (ISSUE_STATE = :issue_state AND RCPT_PERD_STRT_DT = :rcpt_perd_strt_dt AND INT_RT_EFF_DT < :int_rt_eff_dt) OR (ISSUE_STATE = :issue_state AND RCPT_PERD_STRT_DT = :rcpt_perd_strt_dt AND INT_RT_EFF_DT = :int_rt_eff_dt AND DURATION_MONTHS < :duration_months)) ";
            else
                pagingWhere = ".T025X WHERE (COMPANY_CODE = :company_code) AND ((PRODUCT_PREFIX < :product_prefix) OR (PRODUCT_PREFIX = :product_prefix AND TABLE_SUBSET < :table_subset) OR (PRODUCT_PREFIX = :product_prefix AND TABLE_SUBSET = :table_subset AND ISSUE_STATE < :issue_state) OR (PRODUCT_PREFIX = :product_prefix AND TABLE_SUBSET = :table_subset AND ISSUE_STATE = :issue_state AND RCPT_PERD_STRT_DT < :rcpt_perd_strt_dt) OR (PRODUCT_PREFIX = :product_prefix AND TABLE_SUBSET = :table_subset AND ISSUE_STATE = :issue_state AND RCPT_PERD_STRT_DT = :rcpt_perd_strt_dt AND INT_RT_EFF_DT < :int_rt_eff_dt) OR (PRODUCT_PREFIX = :product_prefix AND TABLE_SUBSET = :table_subset AND ISSUE_STATE = :issue_state AND RCPT_PERD_STRT_DT = :rcpt_perd_strt_dt AND INT_RT_EFF_DT = :int_rt_eff_dt AND DURATION_MONTHS < :duration_months)) ";
            order = " ORDER BY COMPANY_CODE DESC, PRODUCT_PREFIX DESC, TABLE_SUBSET DESC, ISSUE_STATE DESC, RCPT_PERD_STRT_DT DESC, INT_RT_EFF_DT DESC, DURATION_MONTHS DESC";
        }
        return pagingSql + schemaName + pagingWhere + order;
    }
    
    public String prepareInsertStmt(String schemaName) {
        StringBuffer sb = new StringBuffer();
        sb.append("INSERT INTO ").append(schemaName);
        sb.append(".T025X ( ");
        sb.append("COMPANY_CODE, PRODUCT_PREFIX, TABLE_SUBSET, ISSUE_STATE, RCPT_PERD_STRT_DT, INT_RT_EFF_DT, DURATION_MONTHS, SETTL_DATE_IND, INTEREST_RATE )");
        sb.append(" VALUES (?, ?, ?, ?, ?, ?, ?, ?, ? )");
        return sb.toString();
    }
    
    public String prepareUpdateStmt(String schemaName)
    {
        StringBuffer sb = new StringBuffer();
        sb.append("UPDATE ").append(schemaName);
        sb.append(".T025X ");
        sb.append(" SET COMPANY_CODE = ?, PRODUCT_PREFIX = ?, TABLE_SUBSET = ?, ISSUE_STATE = ?, RCPT_PERD_STRT_DT = ?, INT_RT_EFF_DT = ?, DURATION_MONTHS = ?, SETTL_DATE_IND = ?, INTEREST_RATE = ?");
        sb.append(" WHERE COMPANY_CODE = ? AND PRODUCT_PREFIX = ? AND TABLE_SUBSET = ? AND ISSUE_STATE = ? AND RCPT_PERD_STRT_DT = ? AND INT_RT_EFF_DT = ? AND DURATION_MONTHS = ?");
        return sb.toString();
    }
    
    public String prepareDeleteStmt(String schemaName)
    {
        StringBuffer sb = new StringBuffer();
        sb.append("DELETE FROM ").append(schemaName);
        sb.append(".T025X ");
        sb.append(" WHERE COMPANY_CODE = ? AND PRODUCT_PREFIX = ? AND TABLE_SUBSET = ? AND ISSUE_STATE = ? AND RCPT_PERD_STRT_DT = ? AND INT_RT_EFF_DT = ? AND DURATION_MONTHS = ?");
        return sb.toString();
    }
}
