package com.csc.fsg.life.pw.io.descriptor.wma;

import com.csc.fsg.life.pw.io.*;

public class TED7ZDescriptor
    extends TableDescriptor
{
    private static final String pagingSql = "SELECT COMPANY_CODE, TABLE_SUBSET, REQT_MNEMONIC, REQUIREMENT_ID, REQUIREMENT_TYPE, CALL_DAYS, DAYS_VALID, NO_OF_FOLLOWUPS, FOLLOWUP_INTERVALS, AUTO_ORDER, ORDER_TIME, REST_CODE FROM ";
    
    public void initialize()
    {
        setRowClass(TED7ZRow.class);
        setTableName("TED7Z");
        setTableId("ED7");
        super.initialize();
    }
    
    public void initializeColumnDescriptors()
    {
        super.initializeColumnDescriptors();
        addColumnDescriptor(new ColumnDescriptor(this,"getCompanyCode","setCompanyCode","COMPANY_CODE,1,1,3,0,true|,0,CHAR,1,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getTableSubset","setTableSubset","TABLE_SUBSET,1,2,16,0,true|,0,CHAR,1,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getReqtMnemonic","setReqtMnemonic","REQT_MNEMONIC,1,3,7,0,true|,0,CHAR,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getRequirementId","setRequirementId","REQUIREMENT_ID,1,4,1,0,false|,0,CHAR,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getRequirementType","setRequirementType","REQUIREMENT_TYPE,1,5,2,0,false|,0,CHAR,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getCallDays","setCallDays","CALL_DAYS,3,6,3,0,false|,0,INTEGER,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getDaysValid","setDaysValid","DAYS_VALID,3,7,3,0,false|,0,INTEGER,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getNoOfFollowups","setNoOfFollowups","NO_OF_FOLLOWUPS,3,8,2,0,false|,0,INTEGER,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getFollowupIntervals","setFollowupIntervals","FOLLOWUP_INTERVALS,3,9,3,0,false|,0,INTEGER,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getAutoOrder","setAutoOrder","AUTO_ORDER,1,10,1,0,false|,0,CHAR,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getOrderTime","setOrderTime","ORDER_TIME,1,11,1,0,false|,0,CHAR,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getRestCode","setRestCode","REST_CODE,1,12,1,0,false|,0,CHAR,0,null,null,null,null,null"));
    }
    
    public String getPagingSQL(String schemaName,boolean isSubsetMode,boolean isNext, boolean locator)
    {
        String pagingWhere = null;
        String order = null;
        if (isNext) {
            if (isSubsetMode)
                if (locator)
                    pagingWhere = ".TED7Z WHERE (COMPANY_CODE = :company_code) AND (TABLE_SUBSET = :table_subset) AND ((REQT_MNEMONIC > :reqt_mnemonic) OR (REQT_MNEMONIC = :reqt_mnemonic)) ";
                else 
                    pagingWhere = ".TED7Z WHERE (COMPANY_CODE = :company_code) AND (TABLE_SUBSET = :table_subset) AND ((REQT_MNEMONIC > :reqt_mnemonic)) ";
            else
                if (locator)
                    pagingWhere = ".TED7Z WHERE (COMPANY_CODE = :company_code) AND ((TABLE_SUBSET > :table_subset) OR (TABLE_SUBSET = :table_subset AND REQT_MNEMONIC > :reqt_mnemonic) OR (TABLE_SUBSET = :table_subset AND REQT_MNEMONIC = :reqt_mnemonic)) ";
                else
                    pagingWhere = ".TED7Z WHERE (COMPANY_CODE = :company_code) AND ((TABLE_SUBSET > :table_subset) OR (TABLE_SUBSET = :table_subset AND REQT_MNEMONIC > :reqt_mnemonic)) ";
            order = " ORDER BY COMPANY_CODE, TABLE_SUBSET, REQT_MNEMONIC";
        }
        else {
            if (isSubsetMode)
                pagingWhere = ".TED7Z WHERE (COMPANY_CODE = :company_code) AND (TABLE_SUBSET = :table_subset) AND ((REQT_MNEMONIC < :reqt_mnemonic)) ";
            else
                pagingWhere = ".TED7Z WHERE (COMPANY_CODE = :company_code) AND ((TABLE_SUBSET < :table_subset) OR (TABLE_SUBSET = :table_subset AND REQT_MNEMONIC < :reqt_mnemonic)) ";
            order = " ORDER BY COMPANY_CODE DESC, TABLE_SUBSET DESC, REQT_MNEMONIC DESC";
        }
        return pagingSql + schemaName + pagingWhere + order;
    }
    
    public String prepareInsertStmt(String schemaName) {
        StringBuffer sb = new StringBuffer();
        sb.append("INSERT INTO ").append(schemaName);
        sb.append(".TED7Z ( ");
        sb.append("COMPANY_CODE, TABLE_SUBSET, REQT_MNEMONIC, REQUIREMENT_ID, REQUIREMENT_TYPE, CALL_DAYS, DAYS_VALID, NO_OF_FOLLOWUPS, FOLLOWUP_INTERVALS, AUTO_ORDER, ORDER_TIME, REST_CODE )");
        sb.append(" VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ? )");
        return sb.toString();
    }
    
    public String prepareUpdateStmt(String schemaName)
    {
        StringBuffer sb = new StringBuffer();
        sb.append("UPDATE ").append(schemaName);
        sb.append(".TED7Z ");
        sb.append(" SET COMPANY_CODE = ?, TABLE_SUBSET = ?, REQT_MNEMONIC = ?, REQUIREMENT_ID = ?, REQUIREMENT_TYPE = ?, CALL_DAYS = ?, DAYS_VALID = ?, NO_OF_FOLLOWUPS = ?, FOLLOWUP_INTERVALS = ?, AUTO_ORDER = ?, ORDER_TIME = ?, REST_CODE = ?");
        sb.append(" WHERE COMPANY_CODE = ? AND TABLE_SUBSET = ? AND REQT_MNEMONIC = ?");
        return sb.toString();
    }
    
    public String prepareDeleteStmt(String schemaName)
    {
        StringBuffer sb = new StringBuffer();
        sb.append("DELETE FROM ").append(schemaName);
        sb.append(".TED7Z ");
        sb.append(" WHERE COMPANY_CODE = ? AND TABLE_SUBSET = ? AND REQT_MNEMONIC = ?");
        return sb.toString();
    }
}
