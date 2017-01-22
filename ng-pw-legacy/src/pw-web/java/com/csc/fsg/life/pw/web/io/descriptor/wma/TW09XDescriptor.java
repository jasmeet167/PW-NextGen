package com.csc.fsg.life.pw.web.io.descriptor.wma;

import com.csc.fsg.life.pw.web.io.*;

public class TW09XDescriptor
    extends TableDescriptor
{
    private static final String pagingSql = "SELECT COMPANY_CODE, DEDUCTION_CODE, MARITAL_STATUS, PYMT_MODE, NUM_ALLOW, EFFECTIVE_DATE, PYMT_AMT_ST_RANGE, END_RANGE, WTHHLD_AMT, WTHHLD_RATE FROM ";
    
    public void initialize()
    {
        setRowClass(TW09XRow.class);
        setTableName("TW09X");
        setTableId("W09");
        super.initialize();
    }
    
    public void initializeColumnDescriptors()
    {
        super.initializeColumnDescriptors();
        addColumnDescriptor(new ColumnDescriptor(this,"getCompanyCode","setCompanyCode","COMPANY_CODE,1,1,3,0,true|,0,CHAR,1,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getDeductionCode","setDeductionCode","DEDUCTION_CODE,1,2,2,0,true|,0,CHAR,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getMaritalStatus","setMaritalStatus","MARITAL_STATUS,1,3,1,0,true|,0,CHAR,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getPymtMode","setPymtMode","PYMT_MODE,1,4,1,0,true|,0,CHAR,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getNumAllow","setNumAllow","NUM_ALLOW,3,5,2,0,true|,0,INTEGER,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getEffectiveDate","setEffectiveDate","EFFECTIVE_DATE,91,6,10,0,true|,0,DATE,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getPymtAmtStRange","setPymtAmtStRange","PYMT_AMT_ST_RANGE,3,7,11,2,true|,0,DOUBLE,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getEndRange","setEndRange","END_RANGE,3,8,11,2,true|,0,DOUBLE,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getWthhldAmt","setWthhldAmt","WTHHLD_AMT,3,9,11,2,false|,0,DOUBLE,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getWthhldRate","setWthhldRate","WTHHLD_RATE,3,10,4,2,false|,0,DOUBLE,0,null,null,null,null,null"));
    }
    
    public String getPagingSQL(String schemaName,boolean isSubsetMode,boolean isNext, boolean locator)
    {
        String pagingWhere = null;
        String order = null;
        if (isNext) {
            if (isSubsetMode)
                if (locator)
                    pagingWhere = ".TW09X WHERE (COMPANY_CODE = :company_code) AND ((DEDUCTION_CODE > :deduction_code) OR (DEDUCTION_CODE = :deduction_code AND MARITAL_STATUS > :marital_status) OR (DEDUCTION_CODE = :deduction_code AND MARITAL_STATUS = :marital_status AND PYMT_MODE > :pymt_mode) OR (DEDUCTION_CODE = :deduction_code AND MARITAL_STATUS = :marital_status AND PYMT_MODE = :pymt_mode AND NUM_ALLOW > :num_allow) OR (DEDUCTION_CODE = :deduction_code AND MARITAL_STATUS = :marital_status AND PYMT_MODE = :pymt_mode AND NUM_ALLOW = :num_allow AND EFFECTIVE_DATE > :effective_date) OR (DEDUCTION_CODE = :deduction_code AND MARITAL_STATUS = :marital_status AND PYMT_MODE = :pymt_mode AND NUM_ALLOW = :num_allow AND EFFECTIVE_DATE = :effective_date AND PYMT_AMT_ST_RANGE > :pymt_amt_st_range) OR (DEDUCTION_CODE = :deduction_code AND MARITAL_STATUS = :marital_status AND PYMT_MODE = :pymt_mode AND NUM_ALLOW = :num_allow AND EFFECTIVE_DATE = :effective_date AND PYMT_AMT_ST_RANGE = :pymt_amt_st_range AND END_RANGE > :end_range) OR (DEDUCTION_CODE = :deduction_code AND MARITAL_STATUS = :marital_status AND PYMT_MODE = :pymt_mode AND NUM_ALLOW = :num_allow AND EFFECTIVE_DATE = :effective_date AND PYMT_AMT_ST_RANGE = :pymt_amt_st_range AND END_RANGE = :end_range)) ";
                else 
                    pagingWhere = ".TW09X WHERE (COMPANY_CODE = :company_code) AND ((DEDUCTION_CODE > :deduction_code) OR (DEDUCTION_CODE = :deduction_code AND MARITAL_STATUS > :marital_status) OR (DEDUCTION_CODE = :deduction_code AND MARITAL_STATUS = :marital_status AND PYMT_MODE > :pymt_mode) OR (DEDUCTION_CODE = :deduction_code AND MARITAL_STATUS = :marital_status AND PYMT_MODE = :pymt_mode AND NUM_ALLOW > :num_allow) OR (DEDUCTION_CODE = :deduction_code AND MARITAL_STATUS = :marital_status AND PYMT_MODE = :pymt_mode AND NUM_ALLOW = :num_allow AND EFFECTIVE_DATE > :effective_date) OR (DEDUCTION_CODE = :deduction_code AND MARITAL_STATUS = :marital_status AND PYMT_MODE = :pymt_mode AND NUM_ALLOW = :num_allow AND EFFECTIVE_DATE = :effective_date AND PYMT_AMT_ST_RANGE > :pymt_amt_st_range) OR (DEDUCTION_CODE = :deduction_code AND MARITAL_STATUS = :marital_status AND PYMT_MODE = :pymt_mode AND NUM_ALLOW = :num_allow AND EFFECTIVE_DATE = :effective_date AND PYMT_AMT_ST_RANGE = :pymt_amt_st_range AND END_RANGE > :end_range)) ";
            else
                if (locator)
                    pagingWhere = ".TW09X WHERE (COMPANY_CODE = :company_code) AND ((DEDUCTION_CODE > :deduction_code) OR (DEDUCTION_CODE = :deduction_code AND MARITAL_STATUS > :marital_status) OR (DEDUCTION_CODE = :deduction_code AND MARITAL_STATUS = :marital_status AND PYMT_MODE > :pymt_mode) OR (DEDUCTION_CODE = :deduction_code AND MARITAL_STATUS = :marital_status AND PYMT_MODE = :pymt_mode AND NUM_ALLOW > :num_allow) OR (DEDUCTION_CODE = :deduction_code AND MARITAL_STATUS = :marital_status AND PYMT_MODE = :pymt_mode AND NUM_ALLOW = :num_allow AND EFFECTIVE_DATE > :effective_date) OR (DEDUCTION_CODE = :deduction_code AND MARITAL_STATUS = :marital_status AND PYMT_MODE = :pymt_mode AND NUM_ALLOW = :num_allow AND EFFECTIVE_DATE = :effective_date AND PYMT_AMT_ST_RANGE > :pymt_amt_st_range) OR (DEDUCTION_CODE = :deduction_code AND MARITAL_STATUS = :marital_status AND PYMT_MODE = :pymt_mode AND NUM_ALLOW = :num_allow AND EFFECTIVE_DATE = :effective_date AND PYMT_AMT_ST_RANGE = :pymt_amt_st_range AND END_RANGE > :end_range) OR (DEDUCTION_CODE = :deduction_code AND MARITAL_STATUS = :marital_status AND PYMT_MODE = :pymt_mode AND NUM_ALLOW = :num_allow AND EFFECTIVE_DATE = :effective_date AND PYMT_AMT_ST_RANGE = :pymt_amt_st_range AND END_RANGE = :end_range)) ";
                else
                    pagingWhere = ".TW09X WHERE (COMPANY_CODE = :company_code) AND ((DEDUCTION_CODE > :deduction_code) OR (DEDUCTION_CODE = :deduction_code AND MARITAL_STATUS > :marital_status) OR (DEDUCTION_CODE = :deduction_code AND MARITAL_STATUS = :marital_status AND PYMT_MODE > :pymt_mode) OR (DEDUCTION_CODE = :deduction_code AND MARITAL_STATUS = :marital_status AND PYMT_MODE = :pymt_mode AND NUM_ALLOW > :num_allow) OR (DEDUCTION_CODE = :deduction_code AND MARITAL_STATUS = :marital_status AND PYMT_MODE = :pymt_mode AND NUM_ALLOW = :num_allow AND EFFECTIVE_DATE > :effective_date) OR (DEDUCTION_CODE = :deduction_code AND MARITAL_STATUS = :marital_status AND PYMT_MODE = :pymt_mode AND NUM_ALLOW = :num_allow AND EFFECTIVE_DATE = :effective_date AND PYMT_AMT_ST_RANGE > :pymt_amt_st_range) OR (DEDUCTION_CODE = :deduction_code AND MARITAL_STATUS = :marital_status AND PYMT_MODE = :pymt_mode AND NUM_ALLOW = :num_allow AND EFFECTIVE_DATE = :effective_date AND PYMT_AMT_ST_RANGE = :pymt_amt_st_range AND END_RANGE > :end_range)) ";
            order = " ORDER BY COMPANY_CODE, DEDUCTION_CODE, MARITAL_STATUS, PYMT_MODE, NUM_ALLOW, EFFECTIVE_DATE, PYMT_AMT_ST_RANGE, END_RANGE";
        }
        else {
            if (isSubsetMode)
                pagingWhere = ".TW09X WHERE (COMPANY_CODE = :company_code) AND ((DEDUCTION_CODE < :deduction_code) OR (DEDUCTION_CODE = :deduction_code AND MARITAL_STATUS < :marital_status) OR (DEDUCTION_CODE = :deduction_code AND MARITAL_STATUS = :marital_status AND PYMT_MODE < :pymt_mode) OR (DEDUCTION_CODE = :deduction_code AND MARITAL_STATUS = :marital_status AND PYMT_MODE = :pymt_mode AND NUM_ALLOW < :num_allow) OR (DEDUCTION_CODE = :deduction_code AND MARITAL_STATUS = :marital_status AND PYMT_MODE = :pymt_mode AND NUM_ALLOW = :num_allow AND EFFECTIVE_DATE < :effective_date) OR (DEDUCTION_CODE = :deduction_code AND MARITAL_STATUS = :marital_status AND PYMT_MODE = :pymt_mode AND NUM_ALLOW = :num_allow AND EFFECTIVE_DATE = :effective_date AND PYMT_AMT_ST_RANGE < :pymt_amt_st_range) OR (DEDUCTION_CODE = :deduction_code AND MARITAL_STATUS = :marital_status AND PYMT_MODE = :pymt_mode AND NUM_ALLOW = :num_allow AND EFFECTIVE_DATE = :effective_date AND PYMT_AMT_ST_RANGE = :pymt_amt_st_range AND END_RANGE < :end_range)) ";
            else
                pagingWhere = ".TW09X WHERE (COMPANY_CODE = :company_code) AND ((DEDUCTION_CODE < :deduction_code) OR (DEDUCTION_CODE = :deduction_code AND MARITAL_STATUS < :marital_status) OR (DEDUCTION_CODE = :deduction_code AND MARITAL_STATUS = :marital_status AND PYMT_MODE < :pymt_mode) OR (DEDUCTION_CODE = :deduction_code AND MARITAL_STATUS = :marital_status AND PYMT_MODE = :pymt_mode AND NUM_ALLOW < :num_allow) OR (DEDUCTION_CODE = :deduction_code AND MARITAL_STATUS = :marital_status AND PYMT_MODE = :pymt_mode AND NUM_ALLOW = :num_allow AND EFFECTIVE_DATE < :effective_date) OR (DEDUCTION_CODE = :deduction_code AND MARITAL_STATUS = :marital_status AND PYMT_MODE = :pymt_mode AND NUM_ALLOW = :num_allow AND EFFECTIVE_DATE = :effective_date AND PYMT_AMT_ST_RANGE < :pymt_amt_st_range) OR (DEDUCTION_CODE = :deduction_code AND MARITAL_STATUS = :marital_status AND PYMT_MODE = :pymt_mode AND NUM_ALLOW = :num_allow AND EFFECTIVE_DATE = :effective_date AND PYMT_AMT_ST_RANGE = :pymt_amt_st_range AND END_RANGE < :end_range)) ";
            order = " ORDER BY COMPANY_CODE DESC, DEDUCTION_CODE DESC, MARITAL_STATUS DESC, PYMT_MODE DESC, NUM_ALLOW DESC, EFFECTIVE_DATE DESC, PYMT_AMT_ST_RANGE DESC, END_RANGE DESC";
        }
        return pagingSql + schemaName + pagingWhere + order;
    }
    
    public String prepareInsertStmt(String schemaName) {
        StringBuffer sb = new StringBuffer();
        sb.append("INSERT INTO ").append(schemaName);
        sb.append(".TW09X ( ");
        sb.append("COMPANY_CODE, DEDUCTION_CODE, MARITAL_STATUS, PYMT_MODE, NUM_ALLOW, EFFECTIVE_DATE, PYMT_AMT_ST_RANGE, END_RANGE, WTHHLD_AMT, WTHHLD_RATE )");
        sb.append(" VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ? )");
        return sb.toString();
    }
    
    public String prepareUpdateStmt(String schemaName)
    {
        StringBuffer sb = new StringBuffer();
        sb.append("UPDATE ").append(schemaName);
        sb.append(".TW09X ");
        sb.append(" SET COMPANY_CODE = ?, DEDUCTION_CODE = ?, MARITAL_STATUS = ?, PYMT_MODE = ?, NUM_ALLOW = ?, EFFECTIVE_DATE = ?, PYMT_AMT_ST_RANGE = ?, END_RANGE = ?, WTHHLD_AMT = ?, WTHHLD_RATE = ?");
        sb.append(" WHERE COMPANY_CODE = ? AND DEDUCTION_CODE = ? AND MARITAL_STATUS = ? AND PYMT_MODE = ? AND NUM_ALLOW = ? AND EFFECTIVE_DATE = ? AND PYMT_AMT_ST_RANGE = ? AND END_RANGE = ?");
        return sb.toString();
    }
    
    public String prepareDeleteStmt(String schemaName)
    {
        StringBuffer sb = new StringBuffer();
        sb.append("DELETE FROM ").append(schemaName);
        sb.append(".TW09X ");
        sb.append(" WHERE COMPANY_CODE = ? AND DEDUCTION_CODE = ? AND MARITAL_STATUS = ? AND PYMT_MODE = ? AND NUM_ALLOW = ? AND EFFECTIVE_DATE = ? AND PYMT_AMT_ST_RANGE = ? AND END_RANGE = ?");
        return sb.toString();
    }
}
