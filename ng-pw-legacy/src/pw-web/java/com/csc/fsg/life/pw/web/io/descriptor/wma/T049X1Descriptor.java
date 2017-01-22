package com.csc.fsg.life.pw.web.io.descriptor.wma;

import com.csc.fsg.life.pw.web.io.*;

public class T049X1Descriptor
    extends TableDescriptor
{
    private static final String pagingSql = "SELECT COMPANY_CODE, TABLE_SUBSET, SEQUENCE_NUMBER, LIVING_INFO_SEQ, ISSUE_AGE, POLICY_YEAR, NO_LIVING FROM ";
    
    public void initialize()
    {
        setRowClass(T049X1Row.class);
        setTableName("T049X1");
        setTableId("49X");
        super.initialize();
    }
    
    public void initializeColumnDescriptors()
    {
        super.initializeColumnDescriptors();
        addColumnDescriptor(new ColumnDescriptor(this,"getCompanyCode","setCompanyCode","COMPANY_CODE,1,1,3,0,true|,0,CHAR,1,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getTableSubset","setTableSubset","TABLE_SUBSET,1,2,16,0,true|,0,CHAR,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getSequenceNumber","setSequenceNumber","SEQUENCE_NUMBER,3,3,3,0,true|,0,INTEGER,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getLivingInfoSeq","setLivingInfoSeq","LIVING_INFO_SEQ,3,4,3,0,true|,0,INTEGER,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getIssueAge","setIssueAge","ISSUE_AGE,3,5,3,0,false|,0,INTEGER,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getPolicyYear","setPolicyYear","POLICY_YEAR,3,6,3,0,false|,0,INTEGER,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getNoLiving","setNoLiving","NO_LIVING,3,7,16,8,false|,0,DOUBLE,0,null,null,null,null,null"));
    }
    
    public String getPagingSQL(String schemaName,boolean isSubsetMode,boolean isNext, boolean locator)
    {
        String pagingWhere = null;
        String order = null;
        if (isNext) {
            if (isSubsetMode)
                if (locator)
                    pagingWhere = ".T049X1 WHERE (COMPANY_CODE = :company_code) AND (TABLE_SUBSET = :table_subset) AND ((SEQUENCE_NUMBER > :sequence_number) OR (SEQUENCE_NUMBER = :sequence_number AND LIVING_INFO_SEQ > :living_info_seq) OR (SEQUENCE_NUMBER = :sequence_number AND LIVING_INFO_SEQ = :living_info_seq)) ";
                else 
                    pagingWhere = ".T049X1 WHERE (COMPANY_CODE = :company_code) AND (TABLE_SUBSET = :table_subset) AND ((SEQUENCE_NUMBER > :sequence_number) OR (SEQUENCE_NUMBER = :sequence_number AND LIVING_INFO_SEQ > :living_info_seq)) ";
            else
                if (locator)
                    pagingWhere = ".T049X1 WHERE (COMPANY_CODE = :company_code) AND ((TABLE_SUBSET > :table_subset) OR (TABLE_SUBSET = :table_subset AND SEQUENCE_NUMBER > :sequence_number) OR (TABLE_SUBSET = :table_subset AND SEQUENCE_NUMBER = :sequence_number AND LIVING_INFO_SEQ > :living_info_seq) OR (TABLE_SUBSET = :table_subset AND SEQUENCE_NUMBER = :sequence_number AND LIVING_INFO_SEQ = :living_info_seq)) ";
                else
                    pagingWhere = ".T049X1 WHERE (COMPANY_CODE = :company_code) AND ((TABLE_SUBSET > :table_subset) OR (TABLE_SUBSET = :table_subset AND SEQUENCE_NUMBER > :sequence_number) OR (TABLE_SUBSET = :table_subset AND SEQUENCE_NUMBER = :sequence_number AND LIVING_INFO_SEQ > :living_info_seq)) ";
            order = " ORDER BY COMPANY_CODE, TABLE_SUBSET, SEQUENCE_NUMBER, LIVING_INFO_SEQ";
        }
        else {
            if (isSubsetMode)
                pagingWhere = ".T049X1 WHERE (COMPANY_CODE = :company_code) AND (TABLE_SUBSET = :table_subset) AND ((SEQUENCE_NUMBER < :sequence_number) OR (SEQUENCE_NUMBER = :sequence_number AND LIVING_INFO_SEQ < :living_info_seq)) ";
            else
                pagingWhere = ".T049X1 WHERE (COMPANY_CODE = :company_code) AND ((TABLE_SUBSET < :table_subset) OR (TABLE_SUBSET = :table_subset AND SEQUENCE_NUMBER < :sequence_number) OR (TABLE_SUBSET = :table_subset AND SEQUENCE_NUMBER = :sequence_number AND LIVING_INFO_SEQ < :living_info_seq)) ";
            order = " ORDER BY COMPANY_CODE DESC, TABLE_SUBSET DESC, SEQUENCE_NUMBER DESC, LIVING_INFO_SEQ DESC";
        }
        return pagingSql + schemaName + pagingWhere + order;
    }
    
    public String prepareInsertStmt(String schemaName) {
        StringBuffer sb = new StringBuffer();
        sb.append("INSERT INTO ").append(schemaName);
        sb.append(".T049X1 ( ");
        sb.append("COMPANY_CODE, TABLE_SUBSET, SEQUENCE_NUMBER, LIVING_INFO_SEQ, ISSUE_AGE, POLICY_YEAR, NO_LIVING )");
        sb.append(" VALUES (?, ?, ?, ?, ?, ?, ? )");
        return sb.toString();
    }
    
    public String prepareUpdateStmt(String schemaName)
    {
        StringBuffer sb = new StringBuffer();
        sb.append("UPDATE ").append(schemaName);
        sb.append(".T049X1 ");
        sb.append(" SET COMPANY_CODE = ?, TABLE_SUBSET = ?, SEQUENCE_NUMBER = ?, LIVING_INFO_SEQ = ?, ISSUE_AGE = ?, POLICY_YEAR = ?, NO_LIVING = ?");
        sb.append(" WHERE COMPANY_CODE = ? AND TABLE_SUBSET = ? AND SEQUENCE_NUMBER = ? AND LIVING_INFO_SEQ = ?");
        return sb.toString();
    }
    
    public String prepareDeleteStmt(String schemaName)
    {
        StringBuffer sb = new StringBuffer();
        sb.append("DELETE FROM ").append(schemaName);
        sb.append(".T049X1 ");
        sb.append(" WHERE COMPANY_CODE = ? AND TABLE_SUBSET = ? AND SEQUENCE_NUMBER = ? AND LIVING_INFO_SEQ = ?");
        return sb.toString();
    }
}
