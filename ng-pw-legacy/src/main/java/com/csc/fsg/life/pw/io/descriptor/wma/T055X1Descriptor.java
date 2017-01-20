package com.csc.fsg.life.pw.io.descriptor.wma;

import com.csc.fsg.life.pw.io.*;

public class T055X1Descriptor
    extends TableDescriptor
{
    private static final String pagingSql = "SELECT COMPANY_CODE, FACTOR_TABLE_NO, FACTOR_RECORD_NO, FACTOR_POLICY_YEAR, NO_LIVING, V_SUPER_T, LN_V_SUPER_T, D_SUB_X, N_SUB_X, PRESENT_VALUE_I, M_SUB_X, M_PRIME_SUB_X FROM ";
    
    public void initialize()
    {
        setRowClass(T055X1Row.class);
        setTableName("T055X1");
        setTableId("55X");
        super.initialize();
    }
    
    public void initializeColumnDescriptors()
    {
        super.initializeColumnDescriptors();
        addColumnDescriptor(new ColumnDescriptor(this,"getCompanyCode","setCompanyCode","COMPANY_CODE,1,1,3,0,true|,0,CHAR,1,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getFactorTableNo","setFactorTableNo","FACTOR_TABLE_NO,1,2,16,0,true|,0,CHAR,1,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getFactorRecordNo","setFactorRecordNo","FACTOR_RECORD_NO,5,3,3,0,true|,0,INTEGER,1,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getFactorPolicyYear","setFactorPolicyYear","FACTOR_POLICY_YEAR,1,4,3,0,true|,0,CHAR,1,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getNoLiving","setNoLiving","NO_LIVING,3,5,17,8,false|,0,DOUBLE,2,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getVSuperT","setVSuperT","V_SUPER_T,3,6,10,8,false|,0,DOUBLE,1,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getLnVSuperT","setLnVSuperT","LN_V_SUPER_T,3,7,13,11,false|,0,DOUBLE,1,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getDSubX","setDSubX","D_SUB_X,3,8,17,8,false|,0,DOUBLE,1,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getNSubX","setNSubX","N_SUB_X,3,9,17,8,false|,0,DOUBLE,1,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getPresentValueI","setPresentValueI","PRESENT_VALUE_I,3,10,10,8,false|,0,DOUBLE,1,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getMSubX","setMSubX","M_SUB_X,3,11,17,8,false|,0,DOUBLE,1,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getMPrimeSubX","setMPrimeSubX","M_PRIME_SUB_X,3,12,17,8,false|,0,DOUBLE,1,null,null,null,null,null"));
    }
    
    public String getPagingSQL(String schemaName,boolean isSubsetMode,boolean isNext, boolean locator)
    {
        String pagingWhere = null;
        String order = null;
        if (isNext) {
            if (isSubsetMode)
                if (locator)
                    pagingWhere = ".T055X1 WHERE (COMPANY_CODE = :company_code) AND ((FACTOR_TABLE_NO > :factor_table_no) OR (FACTOR_TABLE_NO = :factor_table_no AND FACTOR_RECORD_NO > :factor_record_no) OR (FACTOR_TABLE_NO = :factor_table_no AND FACTOR_RECORD_NO = :factor_record_no AND FACTOR_POLICY_YEAR > :factor_policy_year) OR (FACTOR_TABLE_NO = :factor_table_no AND FACTOR_RECORD_NO = :factor_record_no AND FACTOR_POLICY_YEAR = :factor_policy_year)) ";
                else 
                    pagingWhere = ".T055X1 WHERE (COMPANY_CODE = :company_code) AND ((FACTOR_TABLE_NO > :factor_table_no) OR (FACTOR_TABLE_NO = :factor_table_no AND FACTOR_RECORD_NO > :factor_record_no) OR (FACTOR_TABLE_NO = :factor_table_no AND FACTOR_RECORD_NO = :factor_record_no AND FACTOR_POLICY_YEAR > :factor_policy_year)) ";
            else
                if (locator)
                    pagingWhere = ".T055X1 WHERE (COMPANY_CODE = :company_code) AND ((FACTOR_TABLE_NO > :factor_table_no) OR (FACTOR_TABLE_NO = :factor_table_no AND FACTOR_RECORD_NO > :factor_record_no) OR (FACTOR_TABLE_NO = :factor_table_no AND FACTOR_RECORD_NO = :factor_record_no AND FACTOR_POLICY_YEAR > :factor_policy_year) OR (FACTOR_TABLE_NO = :factor_table_no AND FACTOR_RECORD_NO = :factor_record_no AND FACTOR_POLICY_YEAR = :factor_policy_year)) ";
                else
                    pagingWhere = ".T055X1 WHERE (COMPANY_CODE = :company_code) AND ((FACTOR_TABLE_NO > :factor_table_no) OR (FACTOR_TABLE_NO = :factor_table_no AND FACTOR_RECORD_NO > :factor_record_no) OR (FACTOR_TABLE_NO = :factor_table_no AND FACTOR_RECORD_NO = :factor_record_no AND FACTOR_POLICY_YEAR > :factor_policy_year)) ";
            order = " ORDER BY COMPANY_CODE, FACTOR_TABLE_NO, FACTOR_RECORD_NO, FACTOR_POLICY_YEAR";
        }
        else {
            if (isSubsetMode)
                pagingWhere = ".T055X1 WHERE (COMPANY_CODE = :company_code) AND ((FACTOR_TABLE_NO < :factor_table_no) OR (FACTOR_TABLE_NO = :factor_table_no AND FACTOR_RECORD_NO < :factor_record_no) OR (FACTOR_TABLE_NO = :factor_table_no AND FACTOR_RECORD_NO = :factor_record_no AND FACTOR_POLICY_YEAR < :factor_policy_year)) ";
            else
                pagingWhere = ".T055X1 WHERE (COMPANY_CODE = :company_code) AND ((FACTOR_TABLE_NO < :factor_table_no) OR (FACTOR_TABLE_NO = :factor_table_no AND FACTOR_RECORD_NO < :factor_record_no) OR (FACTOR_TABLE_NO = :factor_table_no AND FACTOR_RECORD_NO = :factor_record_no AND FACTOR_POLICY_YEAR < :factor_policy_year)) ";
            order = " ORDER BY COMPANY_CODE DESC, FACTOR_TABLE_NO DESC, FACTOR_RECORD_NO DESC, FACTOR_POLICY_YEAR DESC";
        }
        return pagingSql + schemaName + pagingWhere + order;
    }
    
    public String prepareInsertStmt(String schemaName) {
        StringBuffer sb = new StringBuffer();
        sb.append("INSERT INTO ").append(schemaName);
        sb.append(".T055X1 ( ");
        sb.append("COMPANY_CODE, FACTOR_TABLE_NO, FACTOR_RECORD_NO, FACTOR_POLICY_YEAR, NO_LIVING, V_SUPER_T, LN_V_SUPER_T, D_SUB_X, N_SUB_X, PRESENT_VALUE_I, M_SUB_X, M_PRIME_SUB_X )");
        sb.append(" VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ? )");
        return sb.toString();
    }
    
    public String prepareUpdateStmt(String schemaName)
    {
        StringBuffer sb = new StringBuffer();
        sb.append("UPDATE ").append(schemaName);
        sb.append(".T055X1 ");
        sb.append(" SET COMPANY_CODE = ?, FACTOR_TABLE_NO = ?, FACTOR_RECORD_NO = ?, FACTOR_POLICY_YEAR = ?, NO_LIVING = ?, V_SUPER_T = ?, LN_V_SUPER_T = ?, D_SUB_X = ?, N_SUB_X = ?, PRESENT_VALUE_I = ?, M_SUB_X = ?, M_PRIME_SUB_X = ?");
        sb.append(" WHERE COMPANY_CODE = ? AND FACTOR_TABLE_NO = ? AND FACTOR_RECORD_NO = ? AND FACTOR_POLICY_YEAR = ?");
        return sb.toString();
    }
    
    public String prepareDeleteStmt(String schemaName)
    {
        StringBuffer sb = new StringBuffer();
        sb.append("DELETE FROM ").append(schemaName);
        sb.append(".T055X1 ");
        sb.append(" WHERE COMPANY_CODE = ? AND FACTOR_TABLE_NO = ? AND FACTOR_RECORD_NO = ? AND FACTOR_POLICY_YEAR = ?");
        return sb.toString();
    }
}
