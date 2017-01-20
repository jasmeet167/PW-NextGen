package com.csc.fsg.life.pw.io.descriptor.wma;

import com.csc.fsg.life.pw.io.*;

public class T016XDescriptor
    extends TableDescriptor
{
    private static final String pagingSql = "SELECT COMPANY_CODE, PRODUCT_PREFIX, PRODUCT_SUFFIX, PLAN_CODE, LOB_CODE, PAC_PAY_EXT_DT, PMT_CR_IND, EXTRACT_DATE FROM ";
    
    public void initialize()
    {
        setRowClass(T016XRow.class);
        setTableName("T016X");
        setTableId("016");
        super.initialize();
    }
    
    public void initializeColumnDescriptors()
    {
        super.initializeColumnDescriptors();
        addColumnDescriptor(new ColumnDescriptor(this,"getCompanyCode","setCompanyCode","COMPANY_CODE,1,1,3,0,true|,0,CHAR,1,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getProductPrefix","setProductPrefix","PRODUCT_PREFIX,1,2,1,0,true|,0,CHAR,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getProductSuffix","setProductSuffix","PRODUCT_SUFFIX,1,3,1,0,true|,0,CHAR,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getPlanCode","setPlanCode","PLAN_CODE,1,4,6,0,true|,0,CHAR,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getLobCode","setLobCode","LOB_CODE,1,5,3,0,true|,0,CHAR,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getPacPayExtDt","setPacPayExtDt","PAC_PAY_EXT_DT,91,6,10,0,true|,0,DATE,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getPmtCrInd","setPmtCrInd","PMT_CR_IND,1,7,1,0,false|,0,CHAR,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getExtractDate","setExtractDate","EXTRACT_DATE,91,8,10,0,false|,0,DATE,0,null,null,null,null,null"));
    }
    
    public String getPagingSQL(String schemaName,boolean isSubsetMode,boolean isNext, boolean locator)
    {
        String pagingWhere = null;
        String order = null;
        if (isNext) {
            if (isSubsetMode)
                if (locator)
                    pagingWhere = ".T016X WHERE (COMPANY_CODE = :company_code) AND (PRODUCT_PREFIX = :product_prefix) AND ((PRODUCT_SUFFIX > :product_suffix) OR (PRODUCT_SUFFIX = :product_suffix AND PLAN_CODE > :plan_code) OR (PRODUCT_SUFFIX = :product_suffix AND PLAN_CODE = :plan_code AND LOB_CODE > :lob_code) OR (PRODUCT_SUFFIX = :product_suffix AND PLAN_CODE = :plan_code AND LOB_CODE = :lob_code AND PAC_PAY_EXT_DT > :pac_pay_ext_dt) OR (PRODUCT_SUFFIX = :product_suffix AND PLAN_CODE = :plan_code AND LOB_CODE = :lob_code AND PAC_PAY_EXT_DT = :pac_pay_ext_dt)) ";
                else 
                    pagingWhere = ".T016X WHERE (COMPANY_CODE = :company_code) AND (PRODUCT_PREFIX = :product_prefix) AND ((PRODUCT_SUFFIX > :product_suffix) OR (PRODUCT_SUFFIX = :product_suffix AND PLAN_CODE > :plan_code) OR (PRODUCT_SUFFIX = :product_suffix AND PLAN_CODE = :plan_code AND LOB_CODE > :lob_code) OR (PRODUCT_SUFFIX = :product_suffix AND PLAN_CODE = :plan_code AND LOB_CODE = :lob_code AND PAC_PAY_EXT_DT > :pac_pay_ext_dt)) ";
            else
                if (locator)
                    pagingWhere = ".T016X WHERE (COMPANY_CODE = :company_code) AND ((PRODUCT_PREFIX > :product_prefix) OR (PRODUCT_PREFIX = :product_prefix AND PRODUCT_SUFFIX > :product_suffix) OR (PRODUCT_PREFIX = :product_prefix AND PRODUCT_SUFFIX = :product_suffix AND PLAN_CODE > :plan_code) OR (PRODUCT_PREFIX = :product_prefix AND PRODUCT_SUFFIX = :product_suffix AND PLAN_CODE = :plan_code AND LOB_CODE > :lob_code) OR (PRODUCT_PREFIX = :product_prefix AND PRODUCT_SUFFIX = :product_suffix AND PLAN_CODE = :plan_code AND LOB_CODE = :lob_code AND PAC_PAY_EXT_DT > :pac_pay_ext_dt) OR (PRODUCT_PREFIX = :product_prefix AND PRODUCT_SUFFIX = :product_suffix AND PLAN_CODE = :plan_code AND LOB_CODE = :lob_code AND PAC_PAY_EXT_DT = :pac_pay_ext_dt)) ";
                else
                    pagingWhere = ".T016X WHERE (COMPANY_CODE = :company_code) AND ((PRODUCT_PREFIX > :product_prefix) OR (PRODUCT_PREFIX = :product_prefix AND PRODUCT_SUFFIX > :product_suffix) OR (PRODUCT_PREFIX = :product_prefix AND PRODUCT_SUFFIX = :product_suffix AND PLAN_CODE > :plan_code) OR (PRODUCT_PREFIX = :product_prefix AND PRODUCT_SUFFIX = :product_suffix AND PLAN_CODE = :plan_code AND LOB_CODE > :lob_code) OR (PRODUCT_PREFIX = :product_prefix AND PRODUCT_SUFFIX = :product_suffix AND PLAN_CODE = :plan_code AND LOB_CODE = :lob_code AND PAC_PAY_EXT_DT > :pac_pay_ext_dt)) ";
            order = " ORDER BY COMPANY_CODE, PRODUCT_PREFIX, PRODUCT_SUFFIX, PLAN_CODE, LOB_CODE, PAC_PAY_EXT_DT";
        }
        else {
            if (isSubsetMode)
                pagingWhere = ".T016X WHERE (COMPANY_CODE = :company_code) AND (PRODUCT_PREFIX = :product_prefix) AND ((PRODUCT_SUFFIX < :product_suffix) OR (PRODUCT_SUFFIX = :product_suffix AND PLAN_CODE < :plan_code) OR (PRODUCT_SUFFIX = :product_suffix AND PLAN_CODE = :plan_code AND LOB_CODE < :lob_code) OR (PRODUCT_SUFFIX = :product_suffix AND PLAN_CODE = :plan_code AND LOB_CODE = :lob_code AND PAC_PAY_EXT_DT < :pac_pay_ext_dt)) ";
            else
                pagingWhere = ".T016X WHERE (COMPANY_CODE = :company_code) AND ((PRODUCT_PREFIX < :product_prefix) OR (PRODUCT_PREFIX = :product_prefix AND PRODUCT_SUFFIX < :product_suffix) OR (PRODUCT_PREFIX = :product_prefix AND PRODUCT_SUFFIX = :product_suffix AND PLAN_CODE < :plan_code) OR (PRODUCT_PREFIX = :product_prefix AND PRODUCT_SUFFIX = :product_suffix AND PLAN_CODE = :plan_code AND LOB_CODE < :lob_code) OR (PRODUCT_PREFIX = :product_prefix AND PRODUCT_SUFFIX = :product_suffix AND PLAN_CODE = :plan_code AND LOB_CODE = :lob_code AND PAC_PAY_EXT_DT < :pac_pay_ext_dt)) ";
            order = " ORDER BY COMPANY_CODE DESC, PRODUCT_PREFIX DESC, PRODUCT_SUFFIX DESC, PLAN_CODE DESC, LOB_CODE DESC, PAC_PAY_EXT_DT DESC";
        }
        return pagingSql + schemaName + pagingWhere + order;
    }
    
    public String prepareInsertStmt(String schemaName) {
        StringBuffer sb = new StringBuffer();
        sb.append("INSERT INTO ").append(schemaName);
        sb.append(".T016X ( ");
        sb.append("COMPANY_CODE, PRODUCT_PREFIX, PRODUCT_SUFFIX, PLAN_CODE, LOB_CODE, PAC_PAY_EXT_DT, PMT_CR_IND, EXTRACT_DATE )");
        sb.append(" VALUES (?, ?, ?, ?, ?, ?, ?, ? )");
        return sb.toString();
    }
    
    public String prepareUpdateStmt(String schemaName)
    {
        StringBuffer sb = new StringBuffer();
        sb.append("UPDATE ").append(schemaName);
        sb.append(".T016X ");
        sb.append(" SET COMPANY_CODE = ?, PRODUCT_PREFIX = ?, PRODUCT_SUFFIX = ?, PLAN_CODE = ?, LOB_CODE = ?, PAC_PAY_EXT_DT = ?, PMT_CR_IND = ?, EXTRACT_DATE = ?");
        sb.append(" WHERE COMPANY_CODE = ? AND PRODUCT_PREFIX = ? AND PRODUCT_SUFFIX = ? AND PLAN_CODE = ? AND LOB_CODE = ? AND PAC_PAY_EXT_DT = ?");
        return sb.toString();
    }
    
    public String prepareDeleteStmt(String schemaName)
    {
        StringBuffer sb = new StringBuffer();
        sb.append("DELETE FROM ").append(schemaName);
        sb.append(".T016X ");
        sb.append(" WHERE COMPANY_CODE = ? AND PRODUCT_PREFIX = ? AND PRODUCT_SUFFIX = ? AND PLAN_CODE = ? AND LOB_CODE = ? AND PAC_PAY_EXT_DT = ?");
        return sb.toString();
    }
}
