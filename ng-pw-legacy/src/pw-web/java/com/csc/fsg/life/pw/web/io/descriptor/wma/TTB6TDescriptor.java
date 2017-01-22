package com.csc.fsg.life.pw.web.io.descriptor.wma;

import com.csc.fsg.life.pw.web.io.*;

public class TTB6TDescriptor
    extends TableDescriptor
{
    private static final String pagingSql = "SELECT COMPANY_CODE, TABLE_SUBSET, EFFECTIVE_DATE, AGENT_CONT_CODE, PREMIUM_BAND, MAX_DURATION, COMMISSION_RATE, COMMISSION_AMT, COMMISS_RATE_UNIT, REN_COMMISS_RATE, SER_COMMISS_RATE FROM ";
    
    public void initialize()
    {
        setRowClass(TTB6TRow.class);
        setTableName("TTB6T");
        setTableId("TB6");
        super.initialize();
    }
    
    public void initializeColumnDescriptors()
    {
        super.initializeColumnDescriptors();
        addColumnDescriptor(new ColumnDescriptor(this,"getCompanyCode","setCompanyCode","COMPANY_CODE,1,1,3,0,true|,0,CHAR,1,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getTableSubset","setTableSubset","TABLE_SUBSET,1,2,16,0,true|,0,CHAR,1,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getEffectiveDate","setEffectiveDate","EFFECTIVE_DATE,91,3,10,0,true|,0,DATE,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getAgentContCode","setAgentContCode","AGENT_CONT_CODE,1,4,5,0,true|,0,CHAR,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getPremiumBand","setPremiumBand","PREMIUM_BAND,3,5,9,0,true|,0,INTEGER,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getMaxDuration","setMaxDuration","MAX_DURATION,3,6,2,0,true|,0,INTEGER,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getCommissionRate","setCommissionRate","COMMISSION_RATE,3,7,5,2,false|,0,DOUBLE,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getCommissionAmt","setCommissionAmt","COMMISSION_AMT,3,8,7,2,false|,0,DOUBLE,2,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getCommissRateUnit","setCommissRateUnit","COMMISS_RATE_UNIT,3,9,5,2,false|,0,DOUBLE,2,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getRenCommissRate","setRenCommissRate","REN_COMMISS_RATE,3,10,5,2,false|,0,DOUBLE,2,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getSerCommissRate","setSerCommissRate","SER_COMMISS_RATE,3,11,5,2,false|,0,DOUBLE,2,null,null,null,null,null"));
    }
    
    public String getPagingSQL(String schemaName,boolean isSubsetMode,boolean isNext, boolean locator)
    {
        String pagingWhere = null;
        String order = null;
        if (isNext) {
            if (isSubsetMode)
                if (locator)
                    pagingWhere = ".TTB6T WHERE (COMPANY_CODE = :company_code) AND (TABLE_SUBSET = :table_subset) AND ((EFFECTIVE_DATE > :effective_date) OR (EFFECTIVE_DATE = :effective_date AND AGENT_CONT_CODE > :agent_cont_code) OR (EFFECTIVE_DATE = :effective_date AND AGENT_CONT_CODE = :agent_cont_code AND PREMIUM_BAND > :premium_band) OR (EFFECTIVE_DATE = :effective_date AND AGENT_CONT_CODE = :agent_cont_code AND PREMIUM_BAND = :premium_band AND MAX_DURATION > :max_duration) OR (EFFECTIVE_DATE = :effective_date AND AGENT_CONT_CODE = :agent_cont_code AND PREMIUM_BAND = :premium_band AND MAX_DURATION = :max_duration)) ";
                else 
                    pagingWhere = ".TTB6T WHERE (COMPANY_CODE = :company_code) AND (TABLE_SUBSET = :table_subset) AND ((EFFECTIVE_DATE > :effective_date) OR (EFFECTIVE_DATE = :effective_date AND AGENT_CONT_CODE > :agent_cont_code) OR (EFFECTIVE_DATE = :effective_date AND AGENT_CONT_CODE = :agent_cont_code AND PREMIUM_BAND > :premium_band) OR (EFFECTIVE_DATE = :effective_date AND AGENT_CONT_CODE = :agent_cont_code AND PREMIUM_BAND = :premium_band AND MAX_DURATION > :max_duration)) ";
            else
                if (locator)
                    pagingWhere = ".TTB6T WHERE (COMPANY_CODE = :company_code) AND ((TABLE_SUBSET > :table_subset) OR (TABLE_SUBSET = :table_subset AND EFFECTIVE_DATE > :effective_date) OR (TABLE_SUBSET = :table_subset AND EFFECTIVE_DATE = :effective_date AND AGENT_CONT_CODE > :agent_cont_code) OR (TABLE_SUBSET = :table_subset AND EFFECTIVE_DATE = :effective_date AND AGENT_CONT_CODE = :agent_cont_code AND PREMIUM_BAND > :premium_band) OR (TABLE_SUBSET = :table_subset AND EFFECTIVE_DATE = :effective_date AND AGENT_CONT_CODE = :agent_cont_code AND PREMIUM_BAND = :premium_band AND MAX_DURATION > :max_duration) OR (TABLE_SUBSET = :table_subset AND EFFECTIVE_DATE = :effective_date AND AGENT_CONT_CODE = :agent_cont_code AND PREMIUM_BAND = :premium_band AND MAX_DURATION = :max_duration)) ";
                else
                    pagingWhere = ".TTB6T WHERE (COMPANY_CODE = :company_code) AND ((TABLE_SUBSET > :table_subset) OR (TABLE_SUBSET = :table_subset AND EFFECTIVE_DATE > :effective_date) OR (TABLE_SUBSET = :table_subset AND EFFECTIVE_DATE = :effective_date AND AGENT_CONT_CODE > :agent_cont_code) OR (TABLE_SUBSET = :table_subset AND EFFECTIVE_DATE = :effective_date AND AGENT_CONT_CODE = :agent_cont_code AND PREMIUM_BAND > :premium_band) OR (TABLE_SUBSET = :table_subset AND EFFECTIVE_DATE = :effective_date AND AGENT_CONT_CODE = :agent_cont_code AND PREMIUM_BAND = :premium_band AND MAX_DURATION > :max_duration)) ";
            order = " ORDER BY COMPANY_CODE, TABLE_SUBSET, EFFECTIVE_DATE, AGENT_CONT_CODE, PREMIUM_BAND, MAX_DURATION";
        }
        else {
            if (isSubsetMode)
                pagingWhere = ".TTB6T WHERE (COMPANY_CODE = :company_code) AND (TABLE_SUBSET = :table_subset) AND ((EFFECTIVE_DATE < :effective_date) OR (EFFECTIVE_DATE = :effective_date AND AGENT_CONT_CODE < :agent_cont_code) OR (EFFECTIVE_DATE = :effective_date AND AGENT_CONT_CODE = :agent_cont_code AND PREMIUM_BAND < :premium_band) OR (EFFECTIVE_DATE = :effective_date AND AGENT_CONT_CODE = :agent_cont_code AND PREMIUM_BAND = :premium_band AND MAX_DURATION < :max_duration)) ";
            else
                pagingWhere = ".TTB6T WHERE (COMPANY_CODE = :company_code) AND ((TABLE_SUBSET < :table_subset) OR (TABLE_SUBSET = :table_subset AND EFFECTIVE_DATE < :effective_date) OR (TABLE_SUBSET = :table_subset AND EFFECTIVE_DATE = :effective_date AND AGENT_CONT_CODE < :agent_cont_code) OR (TABLE_SUBSET = :table_subset AND EFFECTIVE_DATE = :effective_date AND AGENT_CONT_CODE = :agent_cont_code AND PREMIUM_BAND < :premium_band) OR (TABLE_SUBSET = :table_subset AND EFFECTIVE_DATE = :effective_date AND AGENT_CONT_CODE = :agent_cont_code AND PREMIUM_BAND = :premium_band AND MAX_DURATION < :max_duration)) ";
            order = " ORDER BY COMPANY_CODE DESC, TABLE_SUBSET DESC, EFFECTIVE_DATE DESC, AGENT_CONT_CODE DESC, PREMIUM_BAND DESC, MAX_DURATION DESC";
        }
        return pagingSql + schemaName + pagingWhere + order;
    }
    
    public String prepareInsertStmt(String schemaName) {
        StringBuffer sb = new StringBuffer();
        sb.append("INSERT INTO ").append(schemaName);
        sb.append(".TTB6T ( ");
        sb.append("COMPANY_CODE, TABLE_SUBSET, EFFECTIVE_DATE, AGENT_CONT_CODE, PREMIUM_BAND, MAX_DURATION, COMMISSION_RATE, COMMISSION_AMT, COMMISS_RATE_UNIT, REN_COMMISS_RATE, SER_COMMISS_RATE )");
        sb.append(" VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ? )");
        return sb.toString();
    }
    
    public String prepareUpdateStmt(String schemaName)
    {
        StringBuffer sb = new StringBuffer();
        sb.append("UPDATE ").append(schemaName);
        sb.append(".TTB6T ");
        sb.append(" SET COMPANY_CODE = ?, TABLE_SUBSET = ?, EFFECTIVE_DATE = ?, AGENT_CONT_CODE = ?, PREMIUM_BAND = ?, MAX_DURATION = ?, COMMISSION_RATE = ?, COMMISSION_AMT = ?, COMMISS_RATE_UNIT = ?, REN_COMMISS_RATE = ?, SER_COMMISS_RATE = ?");
        sb.append(" WHERE COMPANY_CODE = ? AND TABLE_SUBSET = ? AND EFFECTIVE_DATE = ? AND AGENT_CONT_CODE = ? AND PREMIUM_BAND = ? AND MAX_DURATION = ?");
        return sb.toString();
    }
    
    public String prepareDeleteStmt(String schemaName)
    {
        StringBuffer sb = new StringBuffer();
        sb.append("DELETE FROM ").append(schemaName);
        sb.append(".TTB6T ");
        sb.append(" WHERE COMPANY_CODE = ? AND TABLE_SUBSET = ? AND EFFECTIVE_DATE = ? AND AGENT_CONT_CODE = ? AND PREMIUM_BAND = ? AND MAX_DURATION = ?");
        return sb.toString();
    }
}
