package com.csc.fsg.life.pw.io.descriptor.wma;

import com.csc.fsg.life.pw.io.*;

public class TW08XDescriptor
    extends TableDescriptor
{
    private static final String pagingSql = "SELECT COMPANY_CODE, DEDUCTION_CODE, MARITAL_STATUS, PYMT_MODE, NUM_ALLOW, EFFECTIVE_DATE, MAND_WITH_IND, NON_PERIODIC_RATE, MIN_PYMT, MIN_WTHLD_PER_PYMT, MAX_WTHLD_PER_PYMT, STATE_SUPPRESS_CD, FED_WTHHLD_PERCENT, DED_FED_AMT_CODE, MAX_FIT_AMT, BKUP_WTHHLD_CODE, PCT_BKUP_WTHLD, PCT_TXBL_PYMT, ALLOW_RDCT_CODE, ALLOWANCE_VALUE, ADDL_ALLOW_VALUE, HNDCP_ALLOW_VALUE FROM ";
    
    public void initialize()
    {
        setRowClass(TW08XRow.class);
        setTableName("TW08X");
        setTableId("W08");
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
        addColumnDescriptor(new ColumnDescriptor(this,"getMandWithInd","setMandWithInd","MAND_WITH_IND,1,7,1,0,false|,0,CHAR,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getNonPeriodicRate","setNonPeriodicRate","NON_PERIODIC_RATE,3,8,5,2,false|,0,DOUBLE,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getMinPymt","setMinPymt","MIN_PYMT,3,9,11,2,false|,0,DOUBLE,4,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getMinWthldPerPymt","setMinWthldPerPymt","MIN_WTHLD_PER_PYMT,3,10,11,2,false|,0,DOUBLE,4,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getMaxWthldPerPymt","setMaxWthldPerPymt","MAX_WTHLD_PER_PYMT,3,11,11,2,false|,0,DOUBLE,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getStateSuppressCd","setStateSuppressCd","STATE_SUPPRESS_CD,1,12,1,0,false|,0,CHAR,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getFedWthhldPercent","setFedWthhldPercent","FED_WTHHLD_PERCENT,3,13,5,2,false|,0,DOUBLE,4,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getDedFedAmtCode","setDedFedAmtCode","DED_FED_AMT_CODE,1,14,1,0,false|,0,CHAR,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getMaxFitAmt","setMaxFitAmt","MAX_FIT_AMT,3,15,11,2,false|,0,DOUBLE,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getBkupWthhldCode","setBkupWthhldCode","BKUP_WTHHLD_CODE,1,16,1,0,false|,0,CHAR,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getPctBkupWthld","setPctBkupWthld","PCT_BKUP_WTHLD,3,17,5,2,false|,0,DOUBLE,4,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getPctTxblPymt","setPctTxblPymt","PCT_TXBL_PYMT,3,18,5,2,false|,0,DOUBLE,4,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getAllowRdctCode","setAllowRdctCode","ALLOW_RDCT_CODE,1,19,1,0,false|,0,CHAR,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getAllowanceValue","setAllowanceValue","ALLOWANCE_VALUE,3,20,11,2,false|,0,DOUBLE,4,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getAddlAllowValue","setAddlAllowValue","ADDL_ALLOW_VALUE,3,21,11,2,false|,0,DOUBLE,4,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getHndcpAllowValue","setHndcpAllowValue","HNDCP_ALLOW_VALUE,3,22,11,2,false|,0,DOUBLE,4,null,null,null,null,null"));
    }
    
    public String getPagingSQL(String schemaName,boolean isSubsetMode,boolean isNext, boolean locator)
    {
        String pagingWhere = null;
        String order = null;
        if (isNext) {
            if (isSubsetMode)
                if (locator)
                    pagingWhere = ".TW08X WHERE (COMPANY_CODE = :company_code) AND ((DEDUCTION_CODE > :deduction_code) OR (DEDUCTION_CODE = :deduction_code AND MARITAL_STATUS > :marital_status) OR (DEDUCTION_CODE = :deduction_code AND MARITAL_STATUS = :marital_status AND PYMT_MODE > :pymt_mode) OR (DEDUCTION_CODE = :deduction_code AND MARITAL_STATUS = :marital_status AND PYMT_MODE = :pymt_mode AND NUM_ALLOW > :num_allow) OR (DEDUCTION_CODE = :deduction_code AND MARITAL_STATUS = :marital_status AND PYMT_MODE = :pymt_mode AND NUM_ALLOW = :num_allow AND EFFECTIVE_DATE > :effective_date) OR (DEDUCTION_CODE = :deduction_code AND MARITAL_STATUS = :marital_status AND PYMT_MODE = :pymt_mode AND NUM_ALLOW = :num_allow AND EFFECTIVE_DATE = :effective_date)) ";
                else 
                    pagingWhere = ".TW08X WHERE (COMPANY_CODE = :company_code) AND ((DEDUCTION_CODE > :deduction_code) OR (DEDUCTION_CODE = :deduction_code AND MARITAL_STATUS > :marital_status) OR (DEDUCTION_CODE = :deduction_code AND MARITAL_STATUS = :marital_status AND PYMT_MODE > :pymt_mode) OR (DEDUCTION_CODE = :deduction_code AND MARITAL_STATUS = :marital_status AND PYMT_MODE = :pymt_mode AND NUM_ALLOW > :num_allow) OR (DEDUCTION_CODE = :deduction_code AND MARITAL_STATUS = :marital_status AND PYMT_MODE = :pymt_mode AND NUM_ALLOW = :num_allow AND EFFECTIVE_DATE > :effective_date)) ";
            else
                if (locator)
                    pagingWhere = ".TW08X WHERE (COMPANY_CODE = :company_code) AND ((DEDUCTION_CODE > :deduction_code) OR (DEDUCTION_CODE = :deduction_code AND MARITAL_STATUS > :marital_status) OR (DEDUCTION_CODE = :deduction_code AND MARITAL_STATUS = :marital_status AND PYMT_MODE > :pymt_mode) OR (DEDUCTION_CODE = :deduction_code AND MARITAL_STATUS = :marital_status AND PYMT_MODE = :pymt_mode AND NUM_ALLOW > :num_allow) OR (DEDUCTION_CODE = :deduction_code AND MARITAL_STATUS = :marital_status AND PYMT_MODE = :pymt_mode AND NUM_ALLOW = :num_allow AND EFFECTIVE_DATE > :effective_date) OR (DEDUCTION_CODE = :deduction_code AND MARITAL_STATUS = :marital_status AND PYMT_MODE = :pymt_mode AND NUM_ALLOW = :num_allow AND EFFECTIVE_DATE = :effective_date)) ";
                else
                    pagingWhere = ".TW08X WHERE (COMPANY_CODE = :company_code) AND ((DEDUCTION_CODE > :deduction_code) OR (DEDUCTION_CODE = :deduction_code AND MARITAL_STATUS > :marital_status) OR (DEDUCTION_CODE = :deduction_code AND MARITAL_STATUS = :marital_status AND PYMT_MODE > :pymt_mode) OR (DEDUCTION_CODE = :deduction_code AND MARITAL_STATUS = :marital_status AND PYMT_MODE = :pymt_mode AND NUM_ALLOW > :num_allow) OR (DEDUCTION_CODE = :deduction_code AND MARITAL_STATUS = :marital_status AND PYMT_MODE = :pymt_mode AND NUM_ALLOW = :num_allow AND EFFECTIVE_DATE > :effective_date)) ";
            order = " ORDER BY COMPANY_CODE, DEDUCTION_CODE, MARITAL_STATUS, PYMT_MODE, NUM_ALLOW, EFFECTIVE_DATE";
        }
        else {
            if (isSubsetMode)
                pagingWhere = ".TW08X WHERE (COMPANY_CODE = :company_code) AND ((DEDUCTION_CODE < :deduction_code) OR (DEDUCTION_CODE = :deduction_code AND MARITAL_STATUS < :marital_status) OR (DEDUCTION_CODE = :deduction_code AND MARITAL_STATUS = :marital_status AND PYMT_MODE < :pymt_mode) OR (DEDUCTION_CODE = :deduction_code AND MARITAL_STATUS = :marital_status AND PYMT_MODE = :pymt_mode AND NUM_ALLOW < :num_allow) OR (DEDUCTION_CODE = :deduction_code AND MARITAL_STATUS = :marital_status AND PYMT_MODE = :pymt_mode AND NUM_ALLOW = :num_allow AND EFFECTIVE_DATE < :effective_date)) ";
            else
                pagingWhere = ".TW08X WHERE (COMPANY_CODE = :company_code) AND ((DEDUCTION_CODE < :deduction_code) OR (DEDUCTION_CODE = :deduction_code AND MARITAL_STATUS < :marital_status) OR (DEDUCTION_CODE = :deduction_code AND MARITAL_STATUS = :marital_status AND PYMT_MODE < :pymt_mode) OR (DEDUCTION_CODE = :deduction_code AND MARITAL_STATUS = :marital_status AND PYMT_MODE = :pymt_mode AND NUM_ALLOW < :num_allow) OR (DEDUCTION_CODE = :deduction_code AND MARITAL_STATUS = :marital_status AND PYMT_MODE = :pymt_mode AND NUM_ALLOW = :num_allow AND EFFECTIVE_DATE < :effective_date)) ";
            order = " ORDER BY COMPANY_CODE DESC, DEDUCTION_CODE DESC, MARITAL_STATUS DESC, PYMT_MODE DESC, NUM_ALLOW DESC, EFFECTIVE_DATE DESC";
        }
        return pagingSql + schemaName + pagingWhere + order;
    }
    
    public String prepareInsertStmt(String schemaName) {
        StringBuffer sb = new StringBuffer();
        sb.append("INSERT INTO ").append(schemaName);
        sb.append(".TW08X ( ");
        sb.append("COMPANY_CODE, DEDUCTION_CODE, MARITAL_STATUS, PYMT_MODE, NUM_ALLOW, EFFECTIVE_DATE, MAND_WITH_IND, NON_PERIODIC_RATE, MIN_PYMT, MIN_WTHLD_PER_PYMT, MAX_WTHLD_PER_PYMT, STATE_SUPPRESS_CD, FED_WTHHLD_PERCENT, DED_FED_AMT_CODE, MAX_FIT_AMT, BKUP_WTHHLD_CODE, PCT_BKUP_WTHLD, PCT_TXBL_PYMT, ALLOW_RDCT_CODE, ALLOWANCE_VALUE, ADDL_ALLOW_VALUE, HNDCP_ALLOW_VALUE )");
        sb.append(" VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ? )");
        return sb.toString();
    }
    
    public String prepareUpdateStmt(String schemaName)
    {
        StringBuffer sb = new StringBuffer();
        sb.append("UPDATE ").append(schemaName);
        sb.append(".TW08X ");
        sb.append(" SET COMPANY_CODE = ?, DEDUCTION_CODE = ?, MARITAL_STATUS = ?, PYMT_MODE = ?, NUM_ALLOW = ?, EFFECTIVE_DATE = ?, MAND_WITH_IND = ?, NON_PERIODIC_RATE = ?, MIN_PYMT = ?, MIN_WTHLD_PER_PYMT = ?, MAX_WTHLD_PER_PYMT = ?, STATE_SUPPRESS_CD = ?, FED_WTHHLD_PERCENT = ?, DED_FED_AMT_CODE = ?, MAX_FIT_AMT = ?, BKUP_WTHHLD_CODE = ?, PCT_BKUP_WTHLD = ?, PCT_TXBL_PYMT = ?, ALLOW_RDCT_CODE = ?, ALLOWANCE_VALUE = ?, ADDL_ALLOW_VALUE = ?, HNDCP_ALLOW_VALUE = ?");
        sb.append(" WHERE COMPANY_CODE = ? AND DEDUCTION_CODE = ? AND MARITAL_STATUS = ? AND PYMT_MODE = ? AND NUM_ALLOW = ? AND EFFECTIVE_DATE = ?");
        return sb.toString();
    }
    
    public String prepareDeleteStmt(String schemaName)
    {
        StringBuffer sb = new StringBuffer();
        sb.append("DELETE FROM ").append(schemaName);
        sb.append(".TW08X ");
        sb.append(" WHERE COMPANY_CODE = ? AND DEDUCTION_CODE = ? AND MARITAL_STATUS = ? AND PYMT_MODE = ? AND NUM_ALLOW = ? AND EFFECTIVE_DATE = ?");
        return sb.toString();
    }
}
