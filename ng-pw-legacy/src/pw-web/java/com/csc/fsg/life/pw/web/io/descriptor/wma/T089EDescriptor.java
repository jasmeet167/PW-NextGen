package com.csc.fsg.life.pw.web.io.descriptor.wma;

import com.csc.fsg.life.pw.web.io.*;

public class T089EDescriptor
    extends TableDescriptor
{
    private static final String pagingSql = "SELECT COMPANY_CODE, TABLE_SUBSET, ISSUE_STATE, BONUS_RATE_EFF_DT, MAX_POL_MNTHS, BONUS_RATE FROM ";
    
    public void initialize()
    {
        setRowClass(T089ERow.class);
        setTableName("T089E");
        setTableId("089");
        super.initialize();
    }
    
    public void initializeColumnDescriptors()
    {
        super.initializeColumnDescriptors();
        addColumnDescriptor(new ColumnDescriptor(this,"getCompanyCode","setCompanyCode","COMPANY_CODE,1,1,3,0,true|,0,CHAR,1,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getTableSubset","setTableSubset","TABLE_SUBSET,1,2,16,0,true|,0,CHAR,1,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getIssueState","setIssueState","ISSUE_STATE,1,3,3,0,true|,0,CHAR,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getBonusRateEffDt","setBonusRateEffDt","BONUS_RATE_EFF_DT,91,4,10,0,true|,0,DATE,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getMaxPolMnths","setMaxPolMnths","MAX_POL_MNTHS,3,5,4,0,true|,0,INTEGER,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getBonusRate","setBonusRate","BONUS_RATE,3,6,5,3,false|,0,DOUBLE,0,null,null,null,null,null"));
    }
    
    public String getPagingSQL(String schemaName,boolean isSubsetMode,boolean isNext, boolean locator)
    {
        String pagingWhere = null;
        String order = null;
        if (isNext) {
            if (isSubsetMode)
                if (locator)
                    pagingWhere = ".T089E WHERE (COMPANY_CODE = :company_code) AND (TABLE_SUBSET = :table_subset) AND ((ISSUE_STATE > :issue_state) OR (ISSUE_STATE = :issue_state AND BONUS_RATE_EFF_DT > :bonus_rate_eff_dt) OR (ISSUE_STATE = :issue_state AND BONUS_RATE_EFF_DT = :bonus_rate_eff_dt AND MAX_POL_MNTHS > :max_pol_mnths) OR (ISSUE_STATE = :issue_state AND BONUS_RATE_EFF_DT = :bonus_rate_eff_dt AND MAX_POL_MNTHS = :max_pol_mnths)) ";
                else 
                    pagingWhere = ".T089E WHERE (COMPANY_CODE = :company_code) AND (TABLE_SUBSET = :table_subset) AND ((ISSUE_STATE > :issue_state) OR (ISSUE_STATE = :issue_state AND BONUS_RATE_EFF_DT > :bonus_rate_eff_dt) OR (ISSUE_STATE = :issue_state AND BONUS_RATE_EFF_DT = :bonus_rate_eff_dt AND MAX_POL_MNTHS > :max_pol_mnths)) ";
            else
                if (locator)
                    pagingWhere = ".T089E WHERE (COMPANY_CODE = :company_code) AND ((TABLE_SUBSET > :table_subset) OR (TABLE_SUBSET = :table_subset AND ISSUE_STATE > :issue_state) OR (TABLE_SUBSET = :table_subset AND ISSUE_STATE = :issue_state AND BONUS_RATE_EFF_DT > :bonus_rate_eff_dt) OR (TABLE_SUBSET = :table_subset AND ISSUE_STATE = :issue_state AND BONUS_RATE_EFF_DT = :bonus_rate_eff_dt AND MAX_POL_MNTHS > :max_pol_mnths) OR (TABLE_SUBSET = :table_subset AND ISSUE_STATE = :issue_state AND BONUS_RATE_EFF_DT = :bonus_rate_eff_dt AND MAX_POL_MNTHS = :max_pol_mnths)) ";
                else
                    pagingWhere = ".T089E WHERE (COMPANY_CODE = :company_code) AND ((TABLE_SUBSET > :table_subset) OR (TABLE_SUBSET = :table_subset AND ISSUE_STATE > :issue_state) OR (TABLE_SUBSET = :table_subset AND ISSUE_STATE = :issue_state AND BONUS_RATE_EFF_DT > :bonus_rate_eff_dt) OR (TABLE_SUBSET = :table_subset AND ISSUE_STATE = :issue_state AND BONUS_RATE_EFF_DT = :bonus_rate_eff_dt AND MAX_POL_MNTHS > :max_pol_mnths)) ";
            order = " ORDER BY COMPANY_CODE, TABLE_SUBSET, ISSUE_STATE, BONUS_RATE_EFF_DT, MAX_POL_MNTHS";
        }
        else {
            if (isSubsetMode)
                pagingWhere = ".T089E WHERE (COMPANY_CODE = :company_code) AND (TABLE_SUBSET = :table_subset) AND ((ISSUE_STATE < :issue_state) OR (ISSUE_STATE = :issue_state AND BONUS_RATE_EFF_DT < :bonus_rate_eff_dt) OR (ISSUE_STATE = :issue_state AND BONUS_RATE_EFF_DT = :bonus_rate_eff_dt AND MAX_POL_MNTHS < :max_pol_mnths)) ";
            else
                pagingWhere = ".T089E WHERE (COMPANY_CODE = :company_code) AND ((TABLE_SUBSET < :table_subset) OR (TABLE_SUBSET = :table_subset AND ISSUE_STATE < :issue_state) OR (TABLE_SUBSET = :table_subset AND ISSUE_STATE = :issue_state AND BONUS_RATE_EFF_DT < :bonus_rate_eff_dt) OR (TABLE_SUBSET = :table_subset AND ISSUE_STATE = :issue_state AND BONUS_RATE_EFF_DT = :bonus_rate_eff_dt AND MAX_POL_MNTHS < :max_pol_mnths)) ";
            order = " ORDER BY COMPANY_CODE DESC, TABLE_SUBSET DESC, ISSUE_STATE DESC, BONUS_RATE_EFF_DT DESC, MAX_POL_MNTHS DESC";
        }
        return pagingSql + schemaName + pagingWhere + order;
    }
    
    public String prepareInsertStmt(String schemaName) {
        StringBuffer sb = new StringBuffer();
        sb.append("INSERT INTO ").append(schemaName);
        sb.append(".T089E ( ");
        sb.append("COMPANY_CODE, TABLE_SUBSET, ISSUE_STATE, BONUS_RATE_EFF_DT, MAX_POL_MNTHS, BONUS_RATE )");
        sb.append(" VALUES (?, ?, ?, ?, ?, ? )");
        return sb.toString();
    }
    
    public String prepareUpdateStmt(String schemaName)
    {
        StringBuffer sb = new StringBuffer();
        sb.append("UPDATE ").append(schemaName);
        sb.append(".T089E ");
        sb.append(" SET COMPANY_CODE = ?, TABLE_SUBSET = ?, ISSUE_STATE = ?, BONUS_RATE_EFF_DT = ?, MAX_POL_MNTHS = ?, BONUS_RATE = ?");
        sb.append(" WHERE COMPANY_CODE = ? AND TABLE_SUBSET = ? AND ISSUE_STATE = ? AND BONUS_RATE_EFF_DT = ? AND MAX_POL_MNTHS = ?");
        return sb.toString();
    }
    
    public String prepareDeleteStmt(String schemaName)
    {
        StringBuffer sb = new StringBuffer();
        sb.append("DELETE FROM ").append(schemaName);
        sb.append(".T089E ");
        sb.append(" WHERE COMPANY_CODE = ? AND TABLE_SUBSET = ? AND ISSUE_STATE = ? AND BONUS_RATE_EFF_DT = ? AND MAX_POL_MNTHS = ?");
        return sb.toString();
    }
}
