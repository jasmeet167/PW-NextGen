package com.csc.fsg.life.pw.web.io.descriptor.wma;

import com.csc.fsg.life.pw.web.io.*;

public class TT15TDescriptor
    extends TableDescriptor
{
    private static final String pagingSql = "SELECT COMPANY_CODE, TABLE_SUBSET, STATUTORY_CODE, STATE_CODE, AREA_CODE, EFFECTIVE_DATE, MEMO_CODE, LOB_CODE, DURATION, TAX_RATE FROM ";
    
    public void initialize()
    {
        setRowClass(TT15TRow.class);
        setTableName("TT15T");
        setTableId("T15");
        super.initialize();
    }
    
    public void initializeColumnDescriptors()
    {
        super.initializeColumnDescriptors();
        addColumnDescriptor(new ColumnDescriptor(this,"getCompanyCode","setCompanyCode","COMPANY_CODE,1,1,3,0,true|,0,CHAR,1,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getTableSubset","setTableSubset","TABLE_SUBSET,1,2,16,0,true|,0,CHAR,1,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getStatutoryCode","setStatutoryCode","STATUTORY_CODE,1,3,3,0,true|,0,CHAR,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getStateCode","setStateCode","STATE_CODE,1,4,3,0,true|,0,CHAR,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getAreaCode","setAreaCode","AREA_CODE,1,5,6,0,true|,0,CHAR,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getEffectiveDate","setEffectiveDate","EFFECTIVE_DATE,91,6,10,0,true|,0,DATE,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getMemoCode","setMemoCode","MEMO_CODE,1,7,2,0,true|,0,CHAR,4,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getLobCode","setLobCode","LOB_CODE,1,8,3,0,true|,0,CHAR,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getDuration","setDuration","DURATION,3,9,3,0,true|,0,INTEGER,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getTaxRate","setTaxRate","TAX_RATE,3,10,5,3,false|,0,DOUBLE,0,null,null,null,null,null"));
    }
    
    public String getPagingSQL(String schemaName,boolean isSubsetMode,boolean isNext, boolean locator)
    {
        String pagingWhere = null;
        String order = null;
        if (isNext) {
            if (isSubsetMode)
                if (locator)
                    pagingWhere = ".TT15T WHERE (COMPANY_CODE = :company_code) AND (TABLE_SUBSET = :table_subset) AND ((STATUTORY_CODE > :statutory_code) OR (STATUTORY_CODE = :statutory_code AND STATE_CODE > :state_code) OR (STATUTORY_CODE = :statutory_code AND STATE_CODE = :state_code AND AREA_CODE > :area_code) OR (STATUTORY_CODE = :statutory_code AND STATE_CODE = :state_code AND AREA_CODE = :area_code AND EFFECTIVE_DATE > :effective_date) OR (STATUTORY_CODE = :statutory_code AND STATE_CODE = :state_code AND AREA_CODE = :area_code AND EFFECTIVE_DATE = :effective_date AND MEMO_CODE > :memo_code) OR (STATUTORY_CODE = :statutory_code AND STATE_CODE = :state_code AND AREA_CODE = :area_code AND EFFECTIVE_DATE = :effective_date AND MEMO_CODE = :memo_code AND LOB_CODE > :lob_code) OR (STATUTORY_CODE = :statutory_code AND STATE_CODE = :state_code AND AREA_CODE = :area_code AND EFFECTIVE_DATE = :effective_date AND MEMO_CODE = :memo_code AND LOB_CODE = :lob_code AND DURATION > :duration) OR (STATUTORY_CODE = :statutory_code AND STATE_CODE = :state_code AND AREA_CODE = :area_code AND EFFECTIVE_DATE = :effective_date AND MEMO_CODE = :memo_code AND LOB_CODE = :lob_code AND DURATION = :duration)) ";
                else 
                    pagingWhere = ".TT15T WHERE (COMPANY_CODE = :company_code) AND (TABLE_SUBSET = :table_subset) AND ((STATUTORY_CODE > :statutory_code) OR (STATUTORY_CODE = :statutory_code AND STATE_CODE > :state_code) OR (STATUTORY_CODE = :statutory_code AND STATE_CODE = :state_code AND AREA_CODE > :area_code) OR (STATUTORY_CODE = :statutory_code AND STATE_CODE = :state_code AND AREA_CODE = :area_code AND EFFECTIVE_DATE > :effective_date) OR (STATUTORY_CODE = :statutory_code AND STATE_CODE = :state_code AND AREA_CODE = :area_code AND EFFECTIVE_DATE = :effective_date AND MEMO_CODE > :memo_code) OR (STATUTORY_CODE = :statutory_code AND STATE_CODE = :state_code AND AREA_CODE = :area_code AND EFFECTIVE_DATE = :effective_date AND MEMO_CODE = :memo_code AND LOB_CODE > :lob_code) OR (STATUTORY_CODE = :statutory_code AND STATE_CODE = :state_code AND AREA_CODE = :area_code AND EFFECTIVE_DATE = :effective_date AND MEMO_CODE = :memo_code AND LOB_CODE = :lob_code AND DURATION > :duration)) ";
            else
                if (locator)
                    pagingWhere = ".TT15T WHERE (COMPANY_CODE = :company_code) AND ((TABLE_SUBSET > :table_subset) OR (TABLE_SUBSET = :table_subset AND STATUTORY_CODE > :statutory_code) OR (TABLE_SUBSET = :table_subset AND STATUTORY_CODE = :statutory_code AND STATE_CODE > :state_code) OR (TABLE_SUBSET = :table_subset AND STATUTORY_CODE = :statutory_code AND STATE_CODE = :state_code AND AREA_CODE > :area_code) OR (TABLE_SUBSET = :table_subset AND STATUTORY_CODE = :statutory_code AND STATE_CODE = :state_code AND AREA_CODE = :area_code AND EFFECTIVE_DATE > :effective_date) OR (TABLE_SUBSET = :table_subset AND STATUTORY_CODE = :statutory_code AND STATE_CODE = :state_code AND AREA_CODE = :area_code AND EFFECTIVE_DATE = :effective_date AND MEMO_CODE > :memo_code) OR (TABLE_SUBSET = :table_subset AND STATUTORY_CODE = :statutory_code AND STATE_CODE = :state_code AND AREA_CODE = :area_code AND EFFECTIVE_DATE = :effective_date AND MEMO_CODE = :memo_code AND LOB_CODE > :lob_code) OR (TABLE_SUBSET = :table_subset AND STATUTORY_CODE = :statutory_code AND STATE_CODE = :state_code AND AREA_CODE = :area_code AND EFFECTIVE_DATE = :effective_date AND MEMO_CODE = :memo_code AND LOB_CODE = :lob_code AND DURATION > :duration) OR (TABLE_SUBSET = :table_subset AND STATUTORY_CODE = :statutory_code AND STATE_CODE = :state_code AND AREA_CODE = :area_code AND EFFECTIVE_DATE = :effective_date AND MEMO_CODE = :memo_code AND LOB_CODE = :lob_code AND DURATION = :duration)) ";
                else
                    pagingWhere = ".TT15T WHERE (COMPANY_CODE = :company_code) AND ((TABLE_SUBSET > :table_subset) OR (TABLE_SUBSET = :table_subset AND STATUTORY_CODE > :statutory_code) OR (TABLE_SUBSET = :table_subset AND STATUTORY_CODE = :statutory_code AND STATE_CODE > :state_code) OR (TABLE_SUBSET = :table_subset AND STATUTORY_CODE = :statutory_code AND STATE_CODE = :state_code AND AREA_CODE > :area_code) OR (TABLE_SUBSET = :table_subset AND STATUTORY_CODE = :statutory_code AND STATE_CODE = :state_code AND AREA_CODE = :area_code AND EFFECTIVE_DATE > :effective_date) OR (TABLE_SUBSET = :table_subset AND STATUTORY_CODE = :statutory_code AND STATE_CODE = :state_code AND AREA_CODE = :area_code AND EFFECTIVE_DATE = :effective_date AND MEMO_CODE > :memo_code) OR (TABLE_SUBSET = :table_subset AND STATUTORY_CODE = :statutory_code AND STATE_CODE = :state_code AND AREA_CODE = :area_code AND EFFECTIVE_DATE = :effective_date AND MEMO_CODE = :memo_code AND LOB_CODE > :lob_code) OR (TABLE_SUBSET = :table_subset AND STATUTORY_CODE = :statutory_code AND STATE_CODE = :state_code AND AREA_CODE = :area_code AND EFFECTIVE_DATE = :effective_date AND MEMO_CODE = :memo_code AND LOB_CODE = :lob_code AND DURATION > :duration)) ";
            order = " ORDER BY COMPANY_CODE, TABLE_SUBSET, STATUTORY_CODE, STATE_CODE, AREA_CODE, EFFECTIVE_DATE, MEMO_CODE, LOB_CODE, DURATION";
        }
        else {
            if (isSubsetMode)
                pagingWhere = ".TT15T WHERE (COMPANY_CODE = :company_code) AND (TABLE_SUBSET = :table_subset) AND ((STATUTORY_CODE < :statutory_code) OR (STATUTORY_CODE = :statutory_code AND STATE_CODE < :state_code) OR (STATUTORY_CODE = :statutory_code AND STATE_CODE = :state_code AND AREA_CODE < :area_code) OR (STATUTORY_CODE = :statutory_code AND STATE_CODE = :state_code AND AREA_CODE = :area_code AND EFFECTIVE_DATE < :effective_date) OR (STATUTORY_CODE = :statutory_code AND STATE_CODE = :state_code AND AREA_CODE = :area_code AND EFFECTIVE_DATE = :effective_date AND MEMO_CODE < :memo_code) OR (STATUTORY_CODE = :statutory_code AND STATE_CODE = :state_code AND AREA_CODE = :area_code AND EFFECTIVE_DATE = :effective_date AND MEMO_CODE = :memo_code AND LOB_CODE < :lob_code) OR (STATUTORY_CODE = :statutory_code AND STATE_CODE = :state_code AND AREA_CODE = :area_code AND EFFECTIVE_DATE = :effective_date AND MEMO_CODE = :memo_code AND LOB_CODE = :lob_code AND DURATION < :duration)) ";
            else
                pagingWhere = ".TT15T WHERE (COMPANY_CODE = :company_code) AND ((TABLE_SUBSET < :table_subset) OR (TABLE_SUBSET = :table_subset AND STATUTORY_CODE < :statutory_code) OR (TABLE_SUBSET = :table_subset AND STATUTORY_CODE = :statutory_code AND STATE_CODE < :state_code) OR (TABLE_SUBSET = :table_subset AND STATUTORY_CODE = :statutory_code AND STATE_CODE = :state_code AND AREA_CODE < :area_code) OR (TABLE_SUBSET = :table_subset AND STATUTORY_CODE = :statutory_code AND STATE_CODE = :state_code AND AREA_CODE = :area_code AND EFFECTIVE_DATE < :effective_date) OR (TABLE_SUBSET = :table_subset AND STATUTORY_CODE = :statutory_code AND STATE_CODE = :state_code AND AREA_CODE = :area_code AND EFFECTIVE_DATE = :effective_date AND MEMO_CODE < :memo_code) OR (TABLE_SUBSET = :table_subset AND STATUTORY_CODE = :statutory_code AND STATE_CODE = :state_code AND AREA_CODE = :area_code AND EFFECTIVE_DATE = :effective_date AND MEMO_CODE = :memo_code AND LOB_CODE < :lob_code) OR (TABLE_SUBSET = :table_subset AND STATUTORY_CODE = :statutory_code AND STATE_CODE = :state_code AND AREA_CODE = :area_code AND EFFECTIVE_DATE = :effective_date AND MEMO_CODE = :memo_code AND LOB_CODE = :lob_code AND DURATION < :duration)) ";
            order = " ORDER BY COMPANY_CODE DESC, TABLE_SUBSET DESC, STATUTORY_CODE DESC, STATE_CODE DESC, AREA_CODE DESC, EFFECTIVE_DATE DESC, MEMO_CODE DESC, LOB_CODE DESC, DURATION DESC";
        }
        return pagingSql + schemaName + pagingWhere + order;
    }
    
    public String prepareInsertStmt(String schemaName) {
        StringBuffer sb = new StringBuffer();
        sb.append("INSERT INTO ").append(schemaName);
        sb.append(".TT15T ( ");
        sb.append("COMPANY_CODE, TABLE_SUBSET, STATUTORY_CODE, STATE_CODE, AREA_CODE, EFFECTIVE_DATE, MEMO_CODE, LOB_CODE, DURATION, TAX_RATE )");
        sb.append(" VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ? )");
        return sb.toString();
    }
    
    public String prepareUpdateStmt(String schemaName)
    {
        StringBuffer sb = new StringBuffer();
        sb.append("UPDATE ").append(schemaName);
        sb.append(".TT15T ");
        sb.append(" SET COMPANY_CODE = ?, TABLE_SUBSET = ?, STATUTORY_CODE = ?, STATE_CODE = ?, AREA_CODE = ?, EFFECTIVE_DATE = ?, MEMO_CODE = ?, LOB_CODE = ?, DURATION = ?, TAX_RATE = ?");
        sb.append(" WHERE COMPANY_CODE = ? AND TABLE_SUBSET = ? AND STATUTORY_CODE = ? AND STATE_CODE = ? AND AREA_CODE = ? AND EFFECTIVE_DATE = ? AND MEMO_CODE = ? AND LOB_CODE = ? AND DURATION = ?");
        return sb.toString();
    }
    
    public String prepareDeleteStmt(String schemaName)
    {
        StringBuffer sb = new StringBuffer();
        sb.append("DELETE FROM ").append(schemaName);
        sb.append(".TT15T ");
        sb.append(" WHERE COMPANY_CODE = ? AND TABLE_SUBSET = ? AND STATUTORY_CODE = ? AND STATE_CODE = ? AND AREA_CODE = ? AND EFFECTIVE_DATE = ? AND MEMO_CODE = ? AND LOB_CODE = ? AND DURATION = ?");
        return sb.toString();
    }
}
