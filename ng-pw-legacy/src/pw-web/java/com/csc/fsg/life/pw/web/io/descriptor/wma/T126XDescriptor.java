package com.csc.fsg.life.pw.web.io.descriptor.wma;

import com.csc.fsg.life.pw.web.io.*;

public class T126XDescriptor
    extends TableDescriptor
{
    private static final String pagingSql = "SELECT COMPANY_CODE, TABLE_SUBSET, EFFECTIVE_DATE, STATUTORY_CODE, TRX_CODE, BACKDATE_CODE, MEMO_CODE, SOURCE_CODE, RFEE_APPLIES_IND FROM ";
    
    public void initialize()
    {
        setRowClass(T126XRow.class);
        setTableName("T126X");
        setTableId("126");
        super.initialize();
    }
    
    public void initializeColumnDescriptors()
    {
        super.initializeColumnDescriptors();
        addColumnDescriptor(new ColumnDescriptor(this,"getCompanyCode","setCompanyCode","COMPANY_CODE,1,1,3,0,true|,0,CHAR,1,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getTableSubset","setTableSubset","TABLE_SUBSET,1,2,16,0,true|,0,CHAR,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getEffectiveDate","setEffectiveDate","EFFECTIVE_DATE,91,3,10,0,true|,0,DATE,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getStatutoryCode","setStatutoryCode","STATUTORY_CODE,1,4,3,0,true|,0,CHAR,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getTrxCode","setTrxCode","TRX_CODE,1,5,4,0,true|,0,CHAR,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getBackdateCode","setBackdateCode","BACKDATE_CODE,1,6,4,0,true|,0,CHAR,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getMemoCode","setMemoCode","MEMO_CODE,1,7,2,0,true|,0,CHAR,4,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getSourceCode","setSourceCode","SOURCE_CODE,1,8,2,0,true|,0,CHAR,4,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getRfeeAppliesInd","setRfeeAppliesInd","RFEE_APPLIES_IND,1,9,1,0,false|,0,CHAR,4,null,null,null,null,null"));
    }
    
    public String getPagingSQL(String schemaName,boolean isSubsetMode,boolean isNext, boolean locator)
    {
        String pagingWhere = null;
        String order = null;
        if (isNext) {
            if (isSubsetMode)
                if (locator)
                    pagingWhere = ".T126X WHERE (COMPANY_CODE = :company_code) AND (TABLE_SUBSET = :table_subset) AND ((EFFECTIVE_DATE > :effective_date) OR (EFFECTIVE_DATE = :effective_date AND STATUTORY_CODE > :statutory_code) OR (EFFECTIVE_DATE = :effective_date AND STATUTORY_CODE = :statutory_code AND TRX_CODE > :trx_code) OR (EFFECTIVE_DATE = :effective_date AND STATUTORY_CODE = :statutory_code AND TRX_CODE = :trx_code AND BACKDATE_CODE > :backdate_code) OR (EFFECTIVE_DATE = :effective_date AND STATUTORY_CODE = :statutory_code AND TRX_CODE = :trx_code AND BACKDATE_CODE = :backdate_code AND MEMO_CODE > :memo_code) OR (EFFECTIVE_DATE = :effective_date AND STATUTORY_CODE = :statutory_code AND TRX_CODE = :trx_code AND BACKDATE_CODE = :backdate_code AND MEMO_CODE = :memo_code AND SOURCE_CODE > :source_code) OR (EFFECTIVE_DATE = :effective_date AND STATUTORY_CODE = :statutory_code AND TRX_CODE = :trx_code AND BACKDATE_CODE = :backdate_code AND MEMO_CODE = :memo_code AND SOURCE_CODE = :source_code)) ";
                else 
                    pagingWhere = ".T126X WHERE (COMPANY_CODE = :company_code) AND (TABLE_SUBSET = :table_subset) AND ((EFFECTIVE_DATE > :effective_date) OR (EFFECTIVE_DATE = :effective_date AND STATUTORY_CODE > :statutory_code) OR (EFFECTIVE_DATE = :effective_date AND STATUTORY_CODE = :statutory_code AND TRX_CODE > :trx_code) OR (EFFECTIVE_DATE = :effective_date AND STATUTORY_CODE = :statutory_code AND TRX_CODE = :trx_code AND BACKDATE_CODE > :backdate_code) OR (EFFECTIVE_DATE = :effective_date AND STATUTORY_CODE = :statutory_code AND TRX_CODE = :trx_code AND BACKDATE_CODE = :backdate_code AND MEMO_CODE > :memo_code) OR (EFFECTIVE_DATE = :effective_date AND STATUTORY_CODE = :statutory_code AND TRX_CODE = :trx_code AND BACKDATE_CODE = :backdate_code AND MEMO_CODE = :memo_code AND SOURCE_CODE > :source_code)) ";
            else
                if (locator)
                    pagingWhere = ".T126X WHERE (COMPANY_CODE = :company_code) AND ((TABLE_SUBSET > :table_subset) OR (TABLE_SUBSET = :table_subset AND EFFECTIVE_DATE > :effective_date) OR (TABLE_SUBSET = :table_subset AND EFFECTIVE_DATE = :effective_date AND STATUTORY_CODE > :statutory_code) OR (TABLE_SUBSET = :table_subset AND EFFECTIVE_DATE = :effective_date AND STATUTORY_CODE = :statutory_code AND TRX_CODE > :trx_code) OR (TABLE_SUBSET = :table_subset AND EFFECTIVE_DATE = :effective_date AND STATUTORY_CODE = :statutory_code AND TRX_CODE = :trx_code AND BACKDATE_CODE > :backdate_code) OR (TABLE_SUBSET = :table_subset AND EFFECTIVE_DATE = :effective_date AND STATUTORY_CODE = :statutory_code AND TRX_CODE = :trx_code AND BACKDATE_CODE = :backdate_code AND MEMO_CODE > :memo_code) OR (TABLE_SUBSET = :table_subset AND EFFECTIVE_DATE = :effective_date AND STATUTORY_CODE = :statutory_code AND TRX_CODE = :trx_code AND BACKDATE_CODE = :backdate_code AND MEMO_CODE = :memo_code AND SOURCE_CODE > :source_code) OR (TABLE_SUBSET = :table_subset AND EFFECTIVE_DATE = :effective_date AND STATUTORY_CODE = :statutory_code AND TRX_CODE = :trx_code AND BACKDATE_CODE = :backdate_code AND MEMO_CODE = :memo_code AND SOURCE_CODE = :source_code)) ";
                else
                    pagingWhere = ".T126X WHERE (COMPANY_CODE = :company_code) AND ((TABLE_SUBSET > :table_subset) OR (TABLE_SUBSET = :table_subset AND EFFECTIVE_DATE > :effective_date) OR (TABLE_SUBSET = :table_subset AND EFFECTIVE_DATE = :effective_date AND STATUTORY_CODE > :statutory_code) OR (TABLE_SUBSET = :table_subset AND EFFECTIVE_DATE = :effective_date AND STATUTORY_CODE = :statutory_code AND TRX_CODE > :trx_code) OR (TABLE_SUBSET = :table_subset AND EFFECTIVE_DATE = :effective_date AND STATUTORY_CODE = :statutory_code AND TRX_CODE = :trx_code AND BACKDATE_CODE > :backdate_code) OR (TABLE_SUBSET = :table_subset AND EFFECTIVE_DATE = :effective_date AND STATUTORY_CODE = :statutory_code AND TRX_CODE = :trx_code AND BACKDATE_CODE = :backdate_code AND MEMO_CODE > :memo_code) OR (TABLE_SUBSET = :table_subset AND EFFECTIVE_DATE = :effective_date AND STATUTORY_CODE = :statutory_code AND TRX_CODE = :trx_code AND BACKDATE_CODE = :backdate_code AND MEMO_CODE = :memo_code AND SOURCE_CODE > :source_code)) ";
            order = " ORDER BY COMPANY_CODE, TABLE_SUBSET, EFFECTIVE_DATE, STATUTORY_CODE, TRX_CODE, BACKDATE_CODE, MEMO_CODE, SOURCE_CODE";
        }
        else {
            if (isSubsetMode)
                pagingWhere = ".T126X WHERE (COMPANY_CODE = :company_code) AND (TABLE_SUBSET = :table_subset) AND ((EFFECTIVE_DATE < :effective_date) OR (EFFECTIVE_DATE = :effective_date AND STATUTORY_CODE < :statutory_code) OR (EFFECTIVE_DATE = :effective_date AND STATUTORY_CODE = :statutory_code AND TRX_CODE < :trx_code) OR (EFFECTIVE_DATE = :effective_date AND STATUTORY_CODE = :statutory_code AND TRX_CODE = :trx_code AND BACKDATE_CODE < :backdate_code) OR (EFFECTIVE_DATE = :effective_date AND STATUTORY_CODE = :statutory_code AND TRX_CODE = :trx_code AND BACKDATE_CODE = :backdate_code AND MEMO_CODE < :memo_code) OR (EFFECTIVE_DATE = :effective_date AND STATUTORY_CODE = :statutory_code AND TRX_CODE = :trx_code AND BACKDATE_CODE = :backdate_code AND MEMO_CODE = :memo_code AND SOURCE_CODE < :source_code)) ";
            else
                pagingWhere = ".T126X WHERE (COMPANY_CODE = :company_code) AND ((TABLE_SUBSET < :table_subset) OR (TABLE_SUBSET = :table_subset AND EFFECTIVE_DATE < :effective_date) OR (TABLE_SUBSET = :table_subset AND EFFECTIVE_DATE = :effective_date AND STATUTORY_CODE < :statutory_code) OR (TABLE_SUBSET = :table_subset AND EFFECTIVE_DATE = :effective_date AND STATUTORY_CODE = :statutory_code AND TRX_CODE < :trx_code) OR (TABLE_SUBSET = :table_subset AND EFFECTIVE_DATE = :effective_date AND STATUTORY_CODE = :statutory_code AND TRX_CODE = :trx_code AND BACKDATE_CODE < :backdate_code) OR (TABLE_SUBSET = :table_subset AND EFFECTIVE_DATE = :effective_date AND STATUTORY_CODE = :statutory_code AND TRX_CODE = :trx_code AND BACKDATE_CODE = :backdate_code AND MEMO_CODE < :memo_code) OR (TABLE_SUBSET = :table_subset AND EFFECTIVE_DATE = :effective_date AND STATUTORY_CODE = :statutory_code AND TRX_CODE = :trx_code AND BACKDATE_CODE = :backdate_code AND MEMO_CODE = :memo_code AND SOURCE_CODE < :source_code)) ";
            order = " ORDER BY COMPANY_CODE DESC, TABLE_SUBSET DESC, EFFECTIVE_DATE DESC, STATUTORY_CODE DESC, TRX_CODE DESC, BACKDATE_CODE DESC, MEMO_CODE DESC, SOURCE_CODE DESC";
        }
        return pagingSql + schemaName + pagingWhere + order;
    }
    
    public String prepareInsertStmt(String schemaName) {
        StringBuffer sb = new StringBuffer();
        sb.append("INSERT INTO ").append(schemaName);
        sb.append(".T126X ( ");
        sb.append("COMPANY_CODE, TABLE_SUBSET, EFFECTIVE_DATE, STATUTORY_CODE, TRX_CODE, BACKDATE_CODE, MEMO_CODE, SOURCE_CODE, RFEE_APPLIES_IND )");
        sb.append(" VALUES (?, ?, ?, ?, ?, ?, ?, ?, ? )");
        return sb.toString();
    }
    
    public String prepareUpdateStmt(String schemaName)
    {
        StringBuffer sb = new StringBuffer();
        sb.append("UPDATE ").append(schemaName);
        sb.append(".T126X ");
        sb.append(" SET COMPANY_CODE = ?, TABLE_SUBSET = ?, EFFECTIVE_DATE = ?, STATUTORY_CODE = ?, TRX_CODE = ?, BACKDATE_CODE = ?, MEMO_CODE = ?, SOURCE_CODE = ?, RFEE_APPLIES_IND = ?");
        sb.append(" WHERE COMPANY_CODE = ? AND TABLE_SUBSET = ? AND EFFECTIVE_DATE = ? AND STATUTORY_CODE = ? AND TRX_CODE = ? AND BACKDATE_CODE = ? AND MEMO_CODE = ? AND SOURCE_CODE = ?");
        return sb.toString();
    }
    
    public String prepareDeleteStmt(String schemaName)
    {
        StringBuffer sb = new StringBuffer();
        sb.append("DELETE FROM ").append(schemaName);
        sb.append(".T126X ");
        sb.append(" WHERE COMPANY_CODE = ? AND TABLE_SUBSET = ? AND EFFECTIVE_DATE = ? AND STATUTORY_CODE = ? AND TRX_CODE = ? AND BACKDATE_CODE = ? AND MEMO_CODE = ? AND SOURCE_CODE = ?");
        return sb.toString();
    }
}
