package com.csc.fsg.life.pw.io.descriptor.wma;

import com.csc.fsg.life.pw.io.*;

public class T051XDescriptor
    extends TableDescriptor
{
    private static final String pagingSql = "SELECT COMPANY_CODE, TABLE_SUBSET, ERROR_IND, TABLE_TYPE, STRT_PLCY_YY, ENDING_POLICY_YEAR FROM ";
    
    public void initialize()
    {
        setRowClass(T051XRow.class);
        setTableName("T051X");
        setTableId("051");
        super.initialize();
    }
    
    public void initializeColumnDescriptors()
    {
        super.initializeColumnDescriptors();
        addColumnDescriptor(new ColumnDescriptor(this,"getCompanyCode","setCompanyCode","COMPANY_CODE,1,1,3,0,true|,0,CHAR,1,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getTableSubset","setTableSubset","TABLE_SUBSET,1,2,16,0,true|,0,CHAR,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getErrorInd","setErrorInd","ERROR_IND,1,3,1,0,false|,0,CHAR,2,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getTableType","setTableType","TABLE_TYPE,1,4,1,0,false|,0,CHAR,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getStrtPlcyYy","setStrtPlcyYy","STRT_PLCY_YY,3,5,3,0,false|,0,INTEGER,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getEndingPolicyYear","setEndingPolicyYear","ENDING_POLICY_YEAR,3,6,3,0,false|,0,INTEGER,0,null,null,null,null,null"));
    }
    
    public String getPagingSQL(String schemaName,boolean isSubsetMode,boolean isNext, boolean locator)
    {
        String pagingWhere = null;
        String order = null;
        if (isNext) {
            if (isSubsetMode)
                if (locator)
                    pagingWhere = ".T051X WHERE (COMPANY_CODE = :company_code) AND (TABLE_SUBSET = :table_subset)";
                else 
                    pagingWhere = ".T051X WHERE (COMPANY_CODE = :company_code) AND (TABLE_SUBSET = :table_subset)";
            else
                if (locator)
                    pagingWhere = ".T051X WHERE (COMPANY_CODE = :company_code) AND ((TABLE_SUBSET > :table_subset) OR (TABLE_SUBSET = :table_subset)) ";
                else
                    pagingWhere = ".T051X WHERE (COMPANY_CODE = :company_code) AND ((TABLE_SUBSET > :table_subset)) ";
            order = " ORDER BY COMPANY_CODE, TABLE_SUBSET";
        }
        else {
            if (isSubsetMode)
                pagingWhere = ".T051X WHERE (COMPANY_CODE = :company_code) AND (TABLE_SUBSET = :table_subset)";
            else
                pagingWhere = ".T051X WHERE (COMPANY_CODE = :company_code) AND ((TABLE_SUBSET < :table_subset)) ";
            order = " ORDER BY COMPANY_CODE DESC, TABLE_SUBSET DESC";
        }
        return pagingSql + schemaName + pagingWhere + order;
    }
    
    public String prepareInsertStmt(String schemaName) {
        StringBuffer sb = new StringBuffer();
        sb.append("INSERT INTO ").append(schemaName);
        sb.append(".T051X ( ");
        sb.append("COMPANY_CODE, TABLE_SUBSET, ERROR_IND, TABLE_TYPE, STRT_PLCY_YY, ENDING_POLICY_YEAR )");
        sb.append(" VALUES (?, ?, ?, ?, ?, ? )");
        return sb.toString();
    }
    
    public String prepareUpdateStmt(String schemaName)
    {
        StringBuffer sb = new StringBuffer();
        sb.append("UPDATE ").append(schemaName);
        sb.append(".T051X ");
        sb.append(" SET COMPANY_CODE = ?, TABLE_SUBSET = ?, ERROR_IND = ?, TABLE_TYPE = ?, STRT_PLCY_YY = ?, ENDING_POLICY_YEAR = ?");
        sb.append(" WHERE COMPANY_CODE = ? AND TABLE_SUBSET = ?");
        return sb.toString();
    }
    
    public String prepareDeleteStmt(String schemaName)
    {
        StringBuffer sb = new StringBuffer();
        sb.append("DELETE FROM ").append(schemaName);
        sb.append(".T051X ");
        sb.append(" WHERE COMPANY_CODE = ? AND TABLE_SUBSET = ?");
        return sb.toString();
    }
}
