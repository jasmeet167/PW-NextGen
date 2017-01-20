package com.csc.fsg.life.pw.io.descriptor.wma;

import com.csc.fsg.life.pw.io.*;

public class TW06XDescriptor
    extends TableDescriptor
{
    private static final String pagingSql = "SELECT COMPANY_CODE, MARITAL_STATUS, PYMT_MODE, EFFECTIVE_DATE, ALLOWANCE_VALUE, NON_PRDC_WTHLD_PCT, INT_ONLY_WTHLD_PCT, INT_ONLY_MIN_AMT, SOC_SEC_PCT FROM ";
    
    public void initialize()
    {
        setRowClass(TW06XRow.class);
        setTableName("TW06X");
        setTableId("W06");
        super.initialize();
    }
    
    public void initializeColumnDescriptors()
    {
        super.initializeColumnDescriptors();
        addColumnDescriptor(new ColumnDescriptor(this,"getCompanyCode","setCompanyCode","COMPANY_CODE,1,1,3,0,true|,0,CHAR,1,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getMaritalStatus","setMaritalStatus","MARITAL_STATUS,1,2,1,0,true|,0,CHAR,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getPymtMode","setPymtMode","PYMT_MODE,1,3,1,0,true|,0,CHAR,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getEffectiveDate","setEffectiveDate","EFFECTIVE_DATE,91,4,10,0,true|,0,DATE,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getAllowanceValue","setAllowanceValue","ALLOWANCE_VALUE,3,5,11,2,false|,0,DOUBLE,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getNonPrdcWthldPct","setNonPrdcWthldPct","NON_PRDC_WTHLD_PCT,3,6,5,2,false|,0,DOUBLE,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getIntOnlyWthldPct","setIntOnlyWthldPct","INT_ONLY_WTHLD_PCT,3,7,5,2,false|,0,DOUBLE,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getIntOnlyMinAmt","setIntOnlyMinAmt","INT_ONLY_MIN_AMT,3,8,11,2,false|,0,DOUBLE,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getSocSecPct","setSocSecPct","SOC_SEC_PCT,3,9,5,2,false|,0,DOUBLE,0,null,null,null,null,null"));
    }
    
    public String getPagingSQL(String schemaName,boolean isSubsetMode,boolean isNext, boolean locator)
    {
        String pagingWhere = null;
        String order = null;
        if (isNext) {
            if (isSubsetMode)
                if (locator)
                    pagingWhere = ".TW06X WHERE (COMPANY_CODE = :company_code) AND ((MARITAL_STATUS > :marital_status) OR (MARITAL_STATUS = :marital_status AND PYMT_MODE > :pymt_mode) OR (MARITAL_STATUS = :marital_status AND PYMT_MODE = :pymt_mode AND EFFECTIVE_DATE > :effective_date) OR (MARITAL_STATUS = :marital_status AND PYMT_MODE = :pymt_mode AND EFFECTIVE_DATE = :effective_date)) ";
                else 
                    pagingWhere = ".TW06X WHERE (COMPANY_CODE = :company_code) AND ((MARITAL_STATUS > :marital_status) OR (MARITAL_STATUS = :marital_status AND PYMT_MODE > :pymt_mode) OR (MARITAL_STATUS = :marital_status AND PYMT_MODE = :pymt_mode AND EFFECTIVE_DATE > :effective_date)) ";
            else
                if (locator)
                    pagingWhere = ".TW06X WHERE (COMPANY_CODE = :company_code) AND ((MARITAL_STATUS > :marital_status) OR (MARITAL_STATUS = :marital_status AND PYMT_MODE > :pymt_mode) OR (MARITAL_STATUS = :marital_status AND PYMT_MODE = :pymt_mode AND EFFECTIVE_DATE > :effective_date) OR (MARITAL_STATUS = :marital_status AND PYMT_MODE = :pymt_mode AND EFFECTIVE_DATE = :effective_date)) ";
                else
                    pagingWhere = ".TW06X WHERE (COMPANY_CODE = :company_code) AND ((MARITAL_STATUS > :marital_status) OR (MARITAL_STATUS = :marital_status AND PYMT_MODE > :pymt_mode) OR (MARITAL_STATUS = :marital_status AND PYMT_MODE = :pymt_mode AND EFFECTIVE_DATE > :effective_date)) ";
            order = " ORDER BY COMPANY_CODE, MARITAL_STATUS, PYMT_MODE, EFFECTIVE_DATE";
        }
        else {
            if (isSubsetMode)
                pagingWhere = ".TW06X WHERE (COMPANY_CODE = :company_code) AND ((MARITAL_STATUS < :marital_status) OR (MARITAL_STATUS = :marital_status AND PYMT_MODE < :pymt_mode) OR (MARITAL_STATUS = :marital_status AND PYMT_MODE = :pymt_mode AND EFFECTIVE_DATE < :effective_date)) ";
            else
                pagingWhere = ".TW06X WHERE (COMPANY_CODE = :company_code) AND ((MARITAL_STATUS < :marital_status) OR (MARITAL_STATUS = :marital_status AND PYMT_MODE < :pymt_mode) OR (MARITAL_STATUS = :marital_status AND PYMT_MODE = :pymt_mode AND EFFECTIVE_DATE < :effective_date)) ";
            order = " ORDER BY COMPANY_CODE DESC, MARITAL_STATUS DESC, PYMT_MODE DESC, EFFECTIVE_DATE DESC";
        }
        return pagingSql + schemaName + pagingWhere + order;
    }
    
    public String prepareInsertStmt(String schemaName) {
        StringBuffer sb = new StringBuffer();
        sb.append("INSERT INTO ").append(schemaName);
        sb.append(".TW06X ( ");
        sb.append("COMPANY_CODE, MARITAL_STATUS, PYMT_MODE, EFFECTIVE_DATE, ALLOWANCE_VALUE, NON_PRDC_WTHLD_PCT, INT_ONLY_WTHLD_PCT, INT_ONLY_MIN_AMT, SOC_SEC_PCT )");
        sb.append(" VALUES (?, ?, ?, ?, ?, ?, ?, ?, ? )");
        return sb.toString();
    }
    
    public String prepareUpdateStmt(String schemaName)
    {
        StringBuffer sb = new StringBuffer();
        sb.append("UPDATE ").append(schemaName);
        sb.append(".TW06X ");
        sb.append(" SET COMPANY_CODE = ?, MARITAL_STATUS = ?, PYMT_MODE = ?, EFFECTIVE_DATE = ?, ALLOWANCE_VALUE = ?, NON_PRDC_WTHLD_PCT = ?, INT_ONLY_WTHLD_PCT = ?, INT_ONLY_MIN_AMT = ?, SOC_SEC_PCT = ?");
        sb.append(" WHERE COMPANY_CODE = ? AND MARITAL_STATUS = ? AND PYMT_MODE = ? AND EFFECTIVE_DATE = ?");
        return sb.toString();
    }
    
    public String prepareDeleteStmt(String schemaName)
    {
        StringBuffer sb = new StringBuffer();
        sb.append("DELETE FROM ").append(schemaName);
        sb.append(".TW06X ");
        sb.append(" WHERE COMPANY_CODE = ? AND MARITAL_STATUS = ? AND PYMT_MODE = ? AND EFFECTIVE_DATE = ?");
        return sb.toString();
    }
}
