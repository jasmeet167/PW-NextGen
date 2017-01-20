package com.csc.fsg.life.pw.io.descriptor.wma;

import com.csc.fsg.life.pw.io.*;

public class T049XDescriptor
    extends TableDescriptor
{
    private static final String pagingSql = "SELECT COMPANY_CODE, TABLE_SUBSET, SEQUENCE_NUMBER, ERROR_IND, TABLE_TYPE, SELECT_PERIOD, START_AGE, END_AGE, MORT_RATE_DEF_IND FROM ";
    
    public void initialize()
    {
        setRowClass(T049XRow.class);
        setTableName("T049X");
        setTableId("049");
        super.initialize();
    }
    
    public void initializeColumnDescriptors()
    {
        super.initializeColumnDescriptors();
        addColumnDescriptor(new ColumnDescriptor(this,"getCompanyCode","setCompanyCode","COMPANY_CODE,1,1,3,0,true|,0,CHAR,1,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getTableSubset","setTableSubset","TABLE_SUBSET,1,2,16,0,true|,0,CHAR,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getSequenceNumber","setSequenceNumber","SEQUENCE_NUMBER,3,3,3,0,true|,0,INTEGER,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getErrorInd","setErrorInd","ERROR_IND,1,4,1,0,false|,0,CHAR,4,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getTableType","setTableType","TABLE_TYPE,1,5,3,0,false|,0,CHAR,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getSelectPeriod","setSelectPeriod","SELECT_PERIOD,3,6,3,0,false|,0,INTEGER,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getStartAge","setStartAge","START_AGE,3,7,3,0,false|,0,INTEGER,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getEndAge","setEndAge","END_AGE,3,8,3,0,false|,0,INTEGER,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getMortRateDefInd","setMortRateDefInd","MORT_RATE_DEF_IND,1,9,1,0,false|,0,CHAR,0,null,null,null,null,null"));
    }
    
    public String getPagingSQL(String schemaName,boolean isSubsetMode,boolean isNext, boolean locator)
    {
        String pagingWhere = null;
        String order = null;
        if (isNext) {
            if (isSubsetMode)
                if (locator)
                    pagingWhere = ".T049X WHERE (COMPANY_CODE = :company_code) AND (TABLE_SUBSET = :table_subset) AND ((SEQUENCE_NUMBER > :sequence_number) OR (SEQUENCE_NUMBER = :sequence_number)) ";
                else 
                    pagingWhere = ".T049X WHERE (COMPANY_CODE = :company_code) AND (TABLE_SUBSET = :table_subset) AND ((SEQUENCE_NUMBER > :sequence_number)) ";
            else
                if (locator)
                    pagingWhere = ".T049X WHERE (COMPANY_CODE = :company_code) AND ((TABLE_SUBSET > :table_subset) OR (TABLE_SUBSET = :table_subset AND SEQUENCE_NUMBER > :sequence_number) OR (TABLE_SUBSET = :table_subset AND SEQUENCE_NUMBER = :sequence_number)) ";
                else
                    pagingWhere = ".T049X WHERE (COMPANY_CODE = :company_code) AND ((TABLE_SUBSET > :table_subset) OR (TABLE_SUBSET = :table_subset AND SEQUENCE_NUMBER > :sequence_number)) ";
            order = " ORDER BY COMPANY_CODE, TABLE_SUBSET, SEQUENCE_NUMBER";
        }
        else {
            if (isSubsetMode)
                pagingWhere = ".T049X WHERE (COMPANY_CODE = :company_code) AND (TABLE_SUBSET = :table_subset) AND ((SEQUENCE_NUMBER < :sequence_number)) ";
            else
                pagingWhere = ".T049X WHERE (COMPANY_CODE = :company_code) AND ((TABLE_SUBSET < :table_subset) OR (TABLE_SUBSET = :table_subset AND SEQUENCE_NUMBER < :sequence_number)) ";
            order = " ORDER BY COMPANY_CODE DESC, TABLE_SUBSET DESC, SEQUENCE_NUMBER DESC";
        }
        return pagingSql + schemaName + pagingWhere + order;
    }
    
    public String prepareInsertStmt(String schemaName) {
        StringBuffer sb = new StringBuffer();
        sb.append("INSERT INTO ").append(schemaName);
        sb.append(".T049X ( ");
        sb.append("COMPANY_CODE, TABLE_SUBSET, SEQUENCE_NUMBER, ERROR_IND, TABLE_TYPE, SELECT_PERIOD, START_AGE, END_AGE, MORT_RATE_DEF_IND )");
        sb.append(" VALUES (?, ?, ?, ?, ?, ?, ?, ?, ? )");
        return sb.toString();
    }
    
    public String prepareUpdateStmt(String schemaName)
    {
        StringBuffer sb = new StringBuffer();
        sb.append("UPDATE ").append(schemaName);
        sb.append(".T049X ");
        sb.append(" SET COMPANY_CODE = ?, TABLE_SUBSET = ?, SEQUENCE_NUMBER = ?, ERROR_IND = ?, TABLE_TYPE = ?, SELECT_PERIOD = ?, START_AGE = ?, END_AGE = ?, MORT_RATE_DEF_IND = ?");
        sb.append(" WHERE COMPANY_CODE = ? AND TABLE_SUBSET = ? AND SEQUENCE_NUMBER = ?");
        return sb.toString();
    }
    
    public String prepareDeleteStmt(String schemaName)
    {
        StringBuffer sb = new StringBuffer();
        sb.append("DELETE FROM ").append(schemaName);
        sb.append(".T049X ");
        sb.append(" WHERE COMPANY_CODE = ? AND TABLE_SUBSET = ? AND SEQUENCE_NUMBER = ?");
        return sb.toString();
    }
}
