package com.csc.fsg.life.pw.io.descriptor.wma;

import com.csc.fsg.life.pw.io.*;

public class T0C6XDescriptor
    extends TableDescriptor
{
    private static final String pagingSql = "SELECT COMPANY_CODE, PRODUCT_PREFIX, TABLE_SUBSET, STATE_CODE, PAR_RCPT_PRD_ST_DT, PAR_RATE_EFF_DT, PARTICIP_RATE FROM ";
    
    public void initialize()
    {
        setRowClass(T0C6XRow.class);
        setTableName("T0C6X");
        setTableId("0C6");
        super.initialize();
    }
    
    public void initializeColumnDescriptors()
    {
        super.initializeColumnDescriptors();
        addColumnDescriptor(new ColumnDescriptor(this,"getCompanyCode","setCompanyCode","COMPANY_CODE,1,1,3,0,true|A,0,CHAR,1,null,null,null,null,null|U,0,CHAR,1,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getProductPrefix","setProductPrefix","PRODUCT_PREFIX,1,2,1,0,true|A,0,CHAR,1,null,null,null,null,null|U,0,CHAR,1,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getTableSubset","setTableSubset","TABLE_SUBSET,1,3,16,0,true|A,0,CHAR,1,null,null,null,null,null|U,0,CHAR,1,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getStateCode","setStateCode","STATE_CODE,1,4,3,0,true|A,0,CHAR,0,null,null,null,null,null|U,0,CHAR,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getParRcptPrdStDt","setParRcptPrdStDt","PAR_RCPT_PRD_ST_DT,91,5,10,0,true|A,0,DATE,0,null,null,null,null,null|U,0,DATE,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getParRateEffDt","setParRateEffDt","PAR_RATE_EFF_DT,91,6,10,0,true|A,0,DATE,0,null,null,null,null,null|U,0,DATE,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getParticipRate","setParticipRate","PARTICIP_RATE,3,7,5,2,false|A,0,DOUBLE,0,null,null,null,null,null|U,0,DOUBLE,0,null,null,null,null,null"));
    }
    
    public String getPagingSQL(String schemaName,boolean isSubsetMode,boolean isNext, boolean locator)
    {
        String pagingWhere = null;
        String order = null;
        if (isNext) {
            if (isSubsetMode)
                if (locator)
                    pagingWhere = ".T0C6X WHERE (COMPANY_CODE = :company_code) AND (PRODUCT_PREFIX = :product_prefix) AND (TABLE_SUBSET = :table_subset) AND ((STATE_CODE > :state_code) OR (STATE_CODE = :state_code AND PAR_RCPT_PRD_ST_DT > :par_rcpt_prd_st_dt) OR (STATE_CODE = :state_code AND PAR_RCPT_PRD_ST_DT = :par_rcpt_prd_st_dt AND PAR_RATE_EFF_DT > :par_rate_eff_dt) OR (STATE_CODE = :state_code AND PAR_RCPT_PRD_ST_DT = :par_rcpt_prd_st_dt AND PAR_RATE_EFF_DT = :par_rate_eff_dt)) ";
                else 
                    pagingWhere = ".T0C6X WHERE (COMPANY_CODE = :company_code) AND (PRODUCT_PREFIX = :product_prefix) AND (TABLE_SUBSET = :table_subset) AND ((STATE_CODE > :state_code) OR (STATE_CODE = :state_code AND PAR_RCPT_PRD_ST_DT > :par_rcpt_prd_st_dt) OR (STATE_CODE = :state_code AND PAR_RCPT_PRD_ST_DT = :par_rcpt_prd_st_dt AND PAR_RATE_EFF_DT > :par_rate_eff_dt)) ";
            else
                if (locator)
                    pagingWhere = ".T0C6X WHERE (COMPANY_CODE = :company_code) AND ((PRODUCT_PREFIX > :product_prefix) OR (PRODUCT_PREFIX = :product_prefix AND TABLE_SUBSET > :table_subset) OR (PRODUCT_PREFIX = :product_prefix AND TABLE_SUBSET = :table_subset AND STATE_CODE > :state_code) OR (PRODUCT_PREFIX = :product_prefix AND TABLE_SUBSET = :table_subset AND STATE_CODE = :state_code AND PAR_RCPT_PRD_ST_DT > :par_rcpt_prd_st_dt) OR (PRODUCT_PREFIX = :product_prefix AND TABLE_SUBSET = :table_subset AND STATE_CODE = :state_code AND PAR_RCPT_PRD_ST_DT = :par_rcpt_prd_st_dt AND PAR_RATE_EFF_DT > :par_rate_eff_dt) OR (PRODUCT_PREFIX = :product_prefix AND TABLE_SUBSET = :table_subset AND STATE_CODE = :state_code AND PAR_RCPT_PRD_ST_DT = :par_rcpt_prd_st_dt AND PAR_RATE_EFF_DT = :par_rate_eff_dt)) ";
                else
                    pagingWhere = ".T0C6X WHERE (COMPANY_CODE = :company_code) AND ((PRODUCT_PREFIX > :product_prefix) OR (PRODUCT_PREFIX = :product_prefix AND TABLE_SUBSET > :table_subset) OR (PRODUCT_PREFIX = :product_prefix AND TABLE_SUBSET = :table_subset AND STATE_CODE > :state_code) OR (PRODUCT_PREFIX = :product_prefix AND TABLE_SUBSET = :table_subset AND STATE_CODE = :state_code AND PAR_RCPT_PRD_ST_DT > :par_rcpt_prd_st_dt) OR (PRODUCT_PREFIX = :product_prefix AND TABLE_SUBSET = :table_subset AND STATE_CODE = :state_code AND PAR_RCPT_PRD_ST_DT = :par_rcpt_prd_st_dt AND PAR_RATE_EFF_DT > :par_rate_eff_dt)) ";
            order = " ORDER BY COMPANY_CODE, PRODUCT_PREFIX, TABLE_SUBSET, STATE_CODE, PAR_RCPT_PRD_ST_DT, PAR_RATE_EFF_DT";
        }
        else {
            if (isSubsetMode)
                pagingWhere = ".T0C6X WHERE (COMPANY_CODE = :company_code) AND (PRODUCT_PREFIX = :product_prefix) AND (TABLE_SUBSET = :table_subset) AND ((STATE_CODE < :state_code) OR (STATE_CODE = :state_code AND PAR_RCPT_PRD_ST_DT < :par_rcpt_prd_st_dt) OR (STATE_CODE = :state_code AND PAR_RCPT_PRD_ST_DT = :par_rcpt_prd_st_dt AND PAR_RATE_EFF_DT < :par_rate_eff_dt)) ";
            else
                pagingWhere = ".T0C6X WHERE (COMPANY_CODE = :company_code) AND ((PRODUCT_PREFIX < :product_prefix) OR (PRODUCT_PREFIX = :product_prefix AND TABLE_SUBSET < :table_subset) OR (PRODUCT_PREFIX = :product_prefix AND TABLE_SUBSET = :table_subset AND STATE_CODE < :state_code) OR (PRODUCT_PREFIX = :product_prefix AND TABLE_SUBSET = :table_subset AND STATE_CODE = :state_code AND PAR_RCPT_PRD_ST_DT < :par_rcpt_prd_st_dt) OR (PRODUCT_PREFIX = :product_prefix AND TABLE_SUBSET = :table_subset AND STATE_CODE = :state_code AND PAR_RCPT_PRD_ST_DT = :par_rcpt_prd_st_dt AND PAR_RATE_EFF_DT < :par_rate_eff_dt)) ";
            order = " ORDER BY COMPANY_CODE DESC, PRODUCT_PREFIX DESC, TABLE_SUBSET DESC, STATE_CODE DESC, PAR_RCPT_PRD_ST_DT DESC, PAR_RATE_EFF_DT DESC";
        }
        return pagingSql + schemaName + pagingWhere + order;
    }
    
    public String prepareInsertStmt(String schemaName) {
        StringBuffer sb = new StringBuffer();
        sb.append("INSERT INTO ").append(schemaName);
        sb.append(".T0C6X ( ");
        sb.append("COMPANY_CODE, PRODUCT_PREFIX, TABLE_SUBSET, STATE_CODE, PAR_RCPT_PRD_ST_DT, PAR_RATE_EFF_DT, PARTICIP_RATE )");
        sb.append(" VALUES (?, ?, ?, ?, ?, ?, ? )");
        return sb.toString();
    }
    
    public String prepareUpdateStmt(String schemaName)
    {
        StringBuffer sb = new StringBuffer();
        sb.append("UPDATE ").append(schemaName);
        sb.append(".T0C6X ");
        sb.append(" SET COMPANY_CODE = ?, PRODUCT_PREFIX = ?, TABLE_SUBSET = ?, STATE_CODE = ?, PAR_RCPT_PRD_ST_DT = ?, PAR_RATE_EFF_DT = ?, PARTICIP_RATE = ?");
        sb.append(" WHERE COMPANY_CODE = ? AND PRODUCT_PREFIX = ? AND TABLE_SUBSET = ? AND STATE_CODE = ? AND PAR_RCPT_PRD_ST_DT = ? AND PAR_RATE_EFF_DT = ?");
        return sb.toString();
    }
    
    public String prepareDeleteStmt(String schemaName)
    {
        StringBuffer sb = new StringBuffer();
        sb.append("DELETE FROM ").append(schemaName);
        sb.append(".T0C6X ");
        sb.append(" WHERE COMPANY_CODE = ? AND PRODUCT_PREFIX = ? AND TABLE_SUBSET = ? AND STATE_CODE = ? AND PAR_RCPT_PRD_ST_DT = ? AND PAR_RATE_EFF_DT = ?");
        return sb.toString();
    }
}
