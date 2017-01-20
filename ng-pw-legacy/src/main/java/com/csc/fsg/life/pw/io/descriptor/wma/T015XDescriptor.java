package com.csc.fsg.life.pw.io.descriptor.wma;

import com.csc.fsg.life.pw.io.*;

public class T015XDescriptor
    extends TableDescriptor
{
    private static final String pagingSql = "SELECT COMPANY_CODE, STATUTORY_CODE, PRODUCT_PREFIX, PRODUCT_SUFFIX, STATE_CODE, AREA_CODE, EFFECTIVE_DATE, MEMO_CODE, LOB_CODE, DURATION, TAX_RATE FROM ";
    
    public void initialize()
    {
        setRowClass(T015XRow.class);
        setTableName("T015X");
        setTableId("015");
        super.initialize();
    }
    
    public void initializeColumnDescriptors()
    {
        super.initializeColumnDescriptors();
        addColumnDescriptor(new ColumnDescriptor(this,"getCompanyCode","setCompanyCode","COMPANY_CODE,1,1,3,0,true|,0,CHAR,1,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getStatutoryCode","setStatutoryCode","STATUTORY_CODE,1,2,3,0,true|,0,CHAR,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getProductPrefix","setProductPrefix","PRODUCT_PREFIX,1,3,1,0,true|,0,CHAR,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getProductSuffix","setProductSuffix","PRODUCT_SUFFIX,1,4,1,0,true|,0,CHAR,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getStateCode","setStateCode","STATE_CODE,1,5,3,0,true|,0,CHAR,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getAreaCode","setAreaCode","AREA_CODE,1,6,6,0,true|,0,CHAR,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getEffectiveDate","setEffectiveDate","EFFECTIVE_DATE,91,7,10,0,true|,0,DATE,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getMemoCode","setMemoCode","MEMO_CODE,1,8,2,0,true|,0,CHAR,4,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getLobCode","setLobCode","LOB_CODE,1,9,3,0,true|,0,CHAR,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getDuration","setDuration","DURATION,3,10,3,0,true|,0,INTEGER,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getTaxRate","setTaxRate","TAX_RATE,3,11,5,3,false|,0,DOUBLE,0,null,null,null,null,null"));
    }
    
    public String getPagingSQL(String schemaName,boolean isSubsetMode,boolean isNext, boolean locator)
    {
        String pagingWhere = null;
        String order = null;
        if (isNext) {
            if (isSubsetMode)
                if (locator)
                    pagingWhere = ".T015X WHERE (COMPANY_CODE = :company_code) AND (PRODUCT_PREFIX = :product_prefix) AND ((STATUTORY_CODE > :statutory_code) OR (STATUTORY_CODE = :statutory_code AND PRODUCT_SUFFIX > :product_suffix) OR (STATUTORY_CODE = :statutory_code AND PRODUCT_SUFFIX = :product_suffix AND STATE_CODE > :state_code) OR (STATUTORY_CODE = :statutory_code AND PRODUCT_SUFFIX = :product_suffix AND STATE_CODE = :state_code AND AREA_CODE > :area_code) OR (STATUTORY_CODE = :statutory_code AND PRODUCT_SUFFIX = :product_suffix AND STATE_CODE = :state_code AND AREA_CODE = :area_code AND EFFECTIVE_DATE > :effective_date) OR (STATUTORY_CODE = :statutory_code AND PRODUCT_SUFFIX = :product_suffix AND STATE_CODE = :state_code AND AREA_CODE = :area_code AND EFFECTIVE_DATE = :effective_date AND MEMO_CODE > :memo_code) OR (STATUTORY_CODE = :statutory_code AND PRODUCT_SUFFIX = :product_suffix AND STATE_CODE = :state_code AND AREA_CODE = :area_code AND EFFECTIVE_DATE = :effective_date AND MEMO_CODE = :memo_code AND LOB_CODE > :lob_code) OR (STATUTORY_CODE = :statutory_code AND PRODUCT_SUFFIX = :product_suffix AND STATE_CODE = :state_code AND AREA_CODE = :area_code AND EFFECTIVE_DATE = :effective_date AND MEMO_CODE = :memo_code AND LOB_CODE = :lob_code AND DURATION > :duration) OR (STATUTORY_CODE = :statutory_code AND PRODUCT_SUFFIX = :product_suffix AND STATE_CODE = :state_code AND AREA_CODE = :area_code AND EFFECTIVE_DATE = :effective_date AND MEMO_CODE = :memo_code AND LOB_CODE = :lob_code AND DURATION = :duration)) ";
                else 
                    pagingWhere = ".T015X WHERE (COMPANY_CODE = :company_code) AND (PRODUCT_PREFIX = :product_prefix) AND ((STATUTORY_CODE > :statutory_code) OR (STATUTORY_CODE = :statutory_code AND PRODUCT_SUFFIX > :product_suffix) OR (STATUTORY_CODE = :statutory_code AND PRODUCT_SUFFIX = :product_suffix AND STATE_CODE > :state_code) OR (STATUTORY_CODE = :statutory_code AND PRODUCT_SUFFIX = :product_suffix AND STATE_CODE = :state_code AND AREA_CODE > :area_code) OR (STATUTORY_CODE = :statutory_code AND PRODUCT_SUFFIX = :product_suffix AND STATE_CODE = :state_code AND AREA_CODE = :area_code AND EFFECTIVE_DATE > :effective_date) OR (STATUTORY_CODE = :statutory_code AND PRODUCT_SUFFIX = :product_suffix AND STATE_CODE = :state_code AND AREA_CODE = :area_code AND EFFECTIVE_DATE = :effective_date AND MEMO_CODE > :memo_code) OR (STATUTORY_CODE = :statutory_code AND PRODUCT_SUFFIX = :product_suffix AND STATE_CODE = :state_code AND AREA_CODE = :area_code AND EFFECTIVE_DATE = :effective_date AND MEMO_CODE = :memo_code AND LOB_CODE > :lob_code) OR (STATUTORY_CODE = :statutory_code AND PRODUCT_SUFFIX = :product_suffix AND STATE_CODE = :state_code AND AREA_CODE = :area_code AND EFFECTIVE_DATE = :effective_date AND MEMO_CODE = :memo_code AND LOB_CODE = :lob_code AND DURATION > :duration)) ";
            else
                if (locator)
                    pagingWhere = ".T015X WHERE (COMPANY_CODE = :company_code) AND ((STATUTORY_CODE > :statutory_code) OR (STATUTORY_CODE = :statutory_code AND PRODUCT_PREFIX > :product_prefix) OR (STATUTORY_CODE = :statutory_code AND PRODUCT_PREFIX = :product_prefix AND PRODUCT_SUFFIX > :product_suffix) OR (STATUTORY_CODE = :statutory_code AND PRODUCT_PREFIX = :product_prefix AND PRODUCT_SUFFIX = :product_suffix AND STATE_CODE > :state_code) OR (STATUTORY_CODE = :statutory_code AND PRODUCT_PREFIX = :product_prefix AND PRODUCT_SUFFIX = :product_suffix AND STATE_CODE = :state_code AND AREA_CODE > :area_code) OR (STATUTORY_CODE = :statutory_code AND PRODUCT_PREFIX = :product_prefix AND PRODUCT_SUFFIX = :product_suffix AND STATE_CODE = :state_code AND AREA_CODE = :area_code AND EFFECTIVE_DATE > :effective_date) OR (STATUTORY_CODE = :statutory_code AND PRODUCT_PREFIX = :product_prefix AND PRODUCT_SUFFIX = :product_suffix AND STATE_CODE = :state_code AND AREA_CODE = :area_code AND EFFECTIVE_DATE = :effective_date AND MEMO_CODE > :memo_code) OR (STATUTORY_CODE = :statutory_code AND PRODUCT_PREFIX = :product_prefix AND PRODUCT_SUFFIX = :product_suffix AND STATE_CODE = :state_code AND AREA_CODE = :area_code AND EFFECTIVE_DATE = :effective_date AND MEMO_CODE = :memo_code AND LOB_CODE > :lob_code) OR (STATUTORY_CODE = :statutory_code AND PRODUCT_PREFIX = :product_prefix AND PRODUCT_SUFFIX = :product_suffix AND STATE_CODE = :state_code AND AREA_CODE = :area_code AND EFFECTIVE_DATE = :effective_date AND MEMO_CODE = :memo_code AND LOB_CODE = :lob_code AND DURATION > :duration) OR (STATUTORY_CODE = :statutory_code AND PRODUCT_PREFIX = :product_prefix AND PRODUCT_SUFFIX = :product_suffix AND STATE_CODE = :state_code AND AREA_CODE = :area_code AND EFFECTIVE_DATE = :effective_date AND MEMO_CODE = :memo_code AND LOB_CODE = :lob_code AND DURATION = :duration)) ";
                else
                    pagingWhere = ".T015X WHERE (COMPANY_CODE = :company_code) AND ((STATUTORY_CODE > :statutory_code) OR (STATUTORY_CODE = :statutory_code AND PRODUCT_PREFIX > :product_prefix) OR (STATUTORY_CODE = :statutory_code AND PRODUCT_PREFIX = :product_prefix AND PRODUCT_SUFFIX > :product_suffix) OR (STATUTORY_CODE = :statutory_code AND PRODUCT_PREFIX = :product_prefix AND PRODUCT_SUFFIX = :product_suffix AND STATE_CODE > :state_code) OR (STATUTORY_CODE = :statutory_code AND PRODUCT_PREFIX = :product_prefix AND PRODUCT_SUFFIX = :product_suffix AND STATE_CODE = :state_code AND AREA_CODE > :area_code) OR (STATUTORY_CODE = :statutory_code AND PRODUCT_PREFIX = :product_prefix AND PRODUCT_SUFFIX = :product_suffix AND STATE_CODE = :state_code AND AREA_CODE = :area_code AND EFFECTIVE_DATE > :effective_date) OR (STATUTORY_CODE = :statutory_code AND PRODUCT_PREFIX = :product_prefix AND PRODUCT_SUFFIX = :product_suffix AND STATE_CODE = :state_code AND AREA_CODE = :area_code AND EFFECTIVE_DATE = :effective_date AND MEMO_CODE > :memo_code) OR (STATUTORY_CODE = :statutory_code AND PRODUCT_PREFIX = :product_prefix AND PRODUCT_SUFFIX = :product_suffix AND STATE_CODE = :state_code AND AREA_CODE = :area_code AND EFFECTIVE_DATE = :effective_date AND MEMO_CODE = :memo_code AND LOB_CODE > :lob_code) OR (STATUTORY_CODE = :statutory_code AND PRODUCT_PREFIX = :product_prefix AND PRODUCT_SUFFIX = :product_suffix AND STATE_CODE = :state_code AND AREA_CODE = :area_code AND EFFECTIVE_DATE = :effective_date AND MEMO_CODE = :memo_code AND LOB_CODE = :lob_code AND DURATION > :duration)) ";
            order = " ORDER BY COMPANY_CODE, STATUTORY_CODE, PRODUCT_PREFIX, PRODUCT_SUFFIX, STATE_CODE, AREA_CODE, EFFECTIVE_DATE, MEMO_CODE, LOB_CODE, DURATION";
        }
        else {
            if (isSubsetMode)
                pagingWhere = ".T015X WHERE (COMPANY_CODE = :company_code) AND (PRODUCT_PREFIX = :product_prefix) AND ((STATUTORY_CODE < :statutory_code) OR (STATUTORY_CODE = :statutory_code AND PRODUCT_SUFFIX < :product_suffix) OR (STATUTORY_CODE = :statutory_code AND PRODUCT_SUFFIX = :product_suffix AND STATE_CODE < :state_code) OR (STATUTORY_CODE = :statutory_code AND PRODUCT_SUFFIX = :product_suffix AND STATE_CODE = :state_code AND AREA_CODE < :area_code) OR (STATUTORY_CODE = :statutory_code AND PRODUCT_SUFFIX = :product_suffix AND STATE_CODE = :state_code AND AREA_CODE = :area_code AND EFFECTIVE_DATE < :effective_date) OR (STATUTORY_CODE = :statutory_code AND PRODUCT_SUFFIX = :product_suffix AND STATE_CODE = :state_code AND AREA_CODE = :area_code AND EFFECTIVE_DATE = :effective_date AND MEMO_CODE < :memo_code) OR (STATUTORY_CODE = :statutory_code AND PRODUCT_SUFFIX = :product_suffix AND STATE_CODE = :state_code AND AREA_CODE = :area_code AND EFFECTIVE_DATE = :effective_date AND MEMO_CODE = :memo_code AND LOB_CODE < :lob_code) OR (STATUTORY_CODE = :statutory_code AND PRODUCT_SUFFIX = :product_suffix AND STATE_CODE = :state_code AND AREA_CODE = :area_code AND EFFECTIVE_DATE = :effective_date AND MEMO_CODE = :memo_code AND LOB_CODE = :lob_code AND DURATION < :duration)) ";
            else
                pagingWhere = ".T015X WHERE (COMPANY_CODE = :company_code) AND ((STATUTORY_CODE < :statutory_code) OR (STATUTORY_CODE = :statutory_code AND PRODUCT_PREFIX < :product_prefix) OR (STATUTORY_CODE = :statutory_code AND PRODUCT_PREFIX = :product_prefix AND PRODUCT_SUFFIX < :product_suffix) OR (STATUTORY_CODE = :statutory_code AND PRODUCT_PREFIX = :product_prefix AND PRODUCT_SUFFIX = :product_suffix AND STATE_CODE < :state_code) OR (STATUTORY_CODE = :statutory_code AND PRODUCT_PREFIX = :product_prefix AND PRODUCT_SUFFIX = :product_suffix AND STATE_CODE = :state_code AND AREA_CODE < :area_code) OR (STATUTORY_CODE = :statutory_code AND PRODUCT_PREFIX = :product_prefix AND PRODUCT_SUFFIX = :product_suffix AND STATE_CODE = :state_code AND AREA_CODE = :area_code AND EFFECTIVE_DATE < :effective_date) OR (STATUTORY_CODE = :statutory_code AND PRODUCT_PREFIX = :product_prefix AND PRODUCT_SUFFIX = :product_suffix AND STATE_CODE = :state_code AND AREA_CODE = :area_code AND EFFECTIVE_DATE = :effective_date AND MEMO_CODE < :memo_code) OR (STATUTORY_CODE = :statutory_code AND PRODUCT_PREFIX = :product_prefix AND PRODUCT_SUFFIX = :product_suffix AND STATE_CODE = :state_code AND AREA_CODE = :area_code AND EFFECTIVE_DATE = :effective_date AND MEMO_CODE = :memo_code AND LOB_CODE < :lob_code) OR (STATUTORY_CODE = :statutory_code AND PRODUCT_PREFIX = :product_prefix AND PRODUCT_SUFFIX = :product_suffix AND STATE_CODE = :state_code AND AREA_CODE = :area_code AND EFFECTIVE_DATE = :effective_date AND MEMO_CODE = :memo_code AND LOB_CODE = :lob_code AND DURATION < :duration)) ";
            order = " ORDER BY COMPANY_CODE DESC, STATUTORY_CODE DESC, PRODUCT_PREFIX DESC, PRODUCT_SUFFIX DESC, STATE_CODE DESC, AREA_CODE DESC, EFFECTIVE_DATE DESC, MEMO_CODE DESC, LOB_CODE DESC, DURATION DESC";
        }
        return pagingSql + schemaName + pagingWhere + order;
    }
    
    public String prepareInsertStmt(String schemaName) {
        StringBuffer sb = new StringBuffer();
        sb.append("INSERT INTO ").append(schemaName);
        sb.append(".T015X ( ");
        sb.append("COMPANY_CODE, STATUTORY_CODE, PRODUCT_PREFIX, PRODUCT_SUFFIX, STATE_CODE, AREA_CODE, EFFECTIVE_DATE, MEMO_CODE, LOB_CODE, DURATION, TAX_RATE )");
        sb.append(" VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ? )");
        return sb.toString();
    }
    
    public String prepareUpdateStmt(String schemaName)
    {
        StringBuffer sb = new StringBuffer();
        sb.append("UPDATE ").append(schemaName);
        sb.append(".T015X ");
        sb.append(" SET COMPANY_CODE = ?, STATUTORY_CODE = ?, PRODUCT_PREFIX = ?, PRODUCT_SUFFIX = ?, STATE_CODE = ?, AREA_CODE = ?, EFFECTIVE_DATE = ?, MEMO_CODE = ?, LOB_CODE = ?, DURATION = ?, TAX_RATE = ?");
        sb.append(" WHERE COMPANY_CODE = ? AND STATUTORY_CODE = ? AND PRODUCT_PREFIX = ? AND PRODUCT_SUFFIX = ? AND STATE_CODE = ? AND AREA_CODE = ? AND EFFECTIVE_DATE = ? AND MEMO_CODE = ? AND LOB_CODE = ? AND DURATION = ?");
        return sb.toString();
    }
    
    public String prepareDeleteStmt(String schemaName)
    {
        StringBuffer sb = new StringBuffer();
        sb.append("DELETE FROM ").append(schemaName);
        sb.append(".T015X ");
        sb.append(" WHERE COMPANY_CODE = ? AND STATUTORY_CODE = ? AND PRODUCT_PREFIX = ? AND PRODUCT_SUFFIX = ? AND STATE_CODE = ? AND AREA_CODE = ? AND EFFECTIVE_DATE = ? AND MEMO_CODE = ? AND LOB_CODE = ? AND DURATION = ?");
        return sb.toString();
    }
}
