package com.csc.fsg.life.pw.web.io.descriptor.wma;

import com.csc.fsg.life.pw.web.io.*;

public class T055XDescriptor
    extends TableDescriptor
{
    private static final String pagingSql = "SELECT COMPANY_CODE, FACTOR_TABLE_NO, FACTOR_RECORD_NO, FACTOR_POLICY_YEAR, BA_COMPANY_CODE, INT_ASSUMP_TBL_ID, MORT_ASSUMP_TBL_ID, TABLE_TYPE, STARTING_AGE, ENDING_AGE, ISSUE_AGE, MAX_NO_ENTRIES, NO_ENTRIES, CALC_ASSUMP_IND FROM ";
    
    public void initialize()
    {
        setRowClass(T055XRow.class);
        setTableName("T055X");
        setTableId("055");
        super.initialize();
    }
    
    public void initializeColumnDescriptors()
    {
        super.initializeColumnDescriptors();
        addColumnDescriptor(new ColumnDescriptor(this,"getCompanyCode","setCompanyCode","COMPANY_CODE,1,1,3,0,true|,0,CHAR,1,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getFactorTableNo","setFactorTableNo","FACTOR_TABLE_NO,1,2,16,0,true|,0,CHAR,1,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getFactorRecordNo","setFactorRecordNo","FACTOR_RECORD_NO,5,3,3,0,true|,0,INTEGER,1,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getFactorPolicyYear","setFactorPolicyYear","FACTOR_POLICY_YEAR,1,4,3,0,false|,0,CHAR,1,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getBaCompanyCode","setBaCompanyCode","BA_COMPANY_CODE,1,5,3,0,false|,0,CHAR,1,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getIntAssumpTblId","setIntAssumpTblId","INT_ASSUMP_TBL_ID,1,6,16,0,false|,0,CHAR,1,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getMortAssumpTblId","setMortAssumpTblId","MORT_ASSUMP_TBL_ID,1,7,16,0,false|,0,CHAR,1,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getTableType","setTableType","TABLE_TYPE,1,8,3,0,false|,0,CHAR,1,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getStartingAge","setStartingAge","STARTING_AGE,3,9,3,0,false|,0,INTEGER,1,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getEndingAge","setEndingAge","ENDING_AGE,3,10,3,0,false|,0,INTEGER,1,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getIssueAge","setIssueAge","ISSUE_AGE,3,11,3,0,false|,0,INTEGER,1,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getMaxNoEntries","setMaxNoEntries","MAX_NO_ENTRIES,3,12,3,0,false|,0,INTEGER,1,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getNoEntries","setNoEntries","NO_ENTRIES,3,13,3,0,false|,0,INTEGER,1,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getCalcAssumpInd","setCalcAssumpInd","CALC_ASSUMP_IND,1,14,1,0,false|,0,CHAR,1,null,null,null,null,null"));
    }
    
    public String getPagingSQL(String schemaName,boolean isSubsetMode,boolean isNext, boolean locator)
    {
        String pagingWhere = null;
        String order = null;
        if (isNext) {
            if (isSubsetMode)
                if (locator)
                    pagingWhere = ".T055X WHERE (COMPANY_CODE = :company_code) AND ((FACTOR_TABLE_NO > :factor_table_no) OR (FACTOR_TABLE_NO = :factor_table_no AND FACTOR_RECORD_NO > :factor_record_no) OR (FACTOR_TABLE_NO = :factor_table_no AND FACTOR_RECORD_NO = :factor_record_no)) ";
                else 
                    pagingWhere = ".T055X WHERE (COMPANY_CODE = :company_code) AND ((FACTOR_TABLE_NO > :factor_table_no) OR (FACTOR_TABLE_NO = :factor_table_no AND FACTOR_RECORD_NO > :factor_record_no)) ";
            else
                if (locator)
                    pagingWhere = ".T055X WHERE (COMPANY_CODE = :company_code) AND ((FACTOR_TABLE_NO > :factor_table_no) OR (FACTOR_TABLE_NO = :factor_table_no AND FACTOR_RECORD_NO > :factor_record_no) OR (FACTOR_TABLE_NO = :factor_table_no AND FACTOR_RECORD_NO = :factor_record_no)) ";
                else
                    pagingWhere = ".T055X WHERE (COMPANY_CODE = :company_code) AND ((FACTOR_TABLE_NO > :factor_table_no) OR (FACTOR_TABLE_NO = :factor_table_no AND FACTOR_RECORD_NO > :factor_record_no)) ";
            order = " ORDER BY COMPANY_CODE, FACTOR_TABLE_NO, FACTOR_RECORD_NO";
        }
        else {
            if (isSubsetMode)
                pagingWhere = ".T055X WHERE (COMPANY_CODE = :company_code) AND ((FACTOR_TABLE_NO < :factor_table_no) OR (FACTOR_TABLE_NO = :factor_table_no AND FACTOR_RECORD_NO < :factor_record_no)) ";
            else
                pagingWhere = ".T055X WHERE (COMPANY_CODE = :company_code) AND ((FACTOR_TABLE_NO < :factor_table_no) OR (FACTOR_TABLE_NO = :factor_table_no AND FACTOR_RECORD_NO < :factor_record_no)) ";
            order = " ORDER BY COMPANY_CODE DESC, FACTOR_TABLE_NO DESC, FACTOR_RECORD_NO DESC";
        }
        return pagingSql + schemaName + pagingWhere + order;
    }
    
    public String prepareInsertStmt(String schemaName) {
        StringBuffer sb = new StringBuffer();
        sb.append("INSERT INTO ").append(schemaName);
        sb.append(".T055X ( ");
        sb.append("COMPANY_CODE, FACTOR_TABLE_NO, FACTOR_RECORD_NO, FACTOR_POLICY_YEAR, BA_COMPANY_CODE, INT_ASSUMP_TBL_ID, MORT_ASSUMP_TBL_ID, TABLE_TYPE, STARTING_AGE, ENDING_AGE, ISSUE_AGE, MAX_NO_ENTRIES, NO_ENTRIES, CALC_ASSUMP_IND )");
        sb.append(" VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ? )");
        return sb.toString();
    }
    
    public String prepareUpdateStmt(String schemaName)
    {
        StringBuffer sb = new StringBuffer();
        sb.append("UPDATE ").append(schemaName);
        sb.append(".T055X ");
        sb.append(" SET COMPANY_CODE = ?, FACTOR_TABLE_NO = ?, FACTOR_RECORD_NO = ?, FACTOR_POLICY_YEAR = ?, BA_COMPANY_CODE = ?, INT_ASSUMP_TBL_ID = ?, MORT_ASSUMP_TBL_ID = ?, TABLE_TYPE = ?, STARTING_AGE = ?, ENDING_AGE = ?, ISSUE_AGE = ?, MAX_NO_ENTRIES = ?, NO_ENTRIES = ?, CALC_ASSUMP_IND = ?");
        sb.append(" WHERE COMPANY_CODE = ? AND FACTOR_TABLE_NO = ? AND FACTOR_RECORD_NO = ?");
        return sb.toString();
    }
    
    public String prepareDeleteStmt(String schemaName)
    {
        StringBuffer sb = new StringBuffer();
        sb.append("DELETE FROM ").append(schemaName);
        sb.append(".T055X ");
        sb.append(" WHERE COMPANY_CODE = ? AND FACTOR_TABLE_NO = ? AND FACTOR_RECORD_NO = ?");
        return sb.toString();
    }
}
