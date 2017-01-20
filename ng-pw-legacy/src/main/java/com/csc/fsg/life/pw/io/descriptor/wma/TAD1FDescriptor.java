package com.csc.fsg.life.pw.io.descriptor.wma;

import com.csc.fsg.life.pw.io.*;

public class TAD1FDescriptor
    extends TableDescriptor
{
    private static final String pagingSql = "SELECT COMPANY_CODE, TABLE_SUBSET, EFFECTIVE_DATE, ANNUITY_TYPE, TRANSACTION_CODE, DISTRIB_CODE, TAX_TYPE_IND, ANNUITY_TYPE_DESC FROM ";
    
    public void initialize()
    {
        setRowClass(TAD1FRow.class);
        setTableName("TAD1F");
        setTableId("AD1");
        super.initialize();
    }
    
    public void initializeColumnDescriptors()
    {
        super.initializeColumnDescriptors();
        addColumnDescriptor(new ColumnDescriptor(this,"getCompanyCode","setCompanyCode","COMPANY_CODE,1,1,3,0,true|,0,CHAR,1,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getTableSubset","setTableSubset","TABLE_SUBSET,1,2,16,0,true|,0,CHAR,1,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getEffectiveDate","setEffectiveDate","EFFECTIVE_DATE,91,3,10,0,true|,0,DATE,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getAnnuityType","setAnnuityType","ANNUITY_TYPE,1,4,1,0,true|,0,CHAR,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getTransactionCode","setTransactionCode","TRANSACTION_CODE,1,5,4,0,true|,0,CHAR,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getDistribCode","setDistribCode","DISTRIB_CODE,1,6,1,0,true|,0,CHAR,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getTaxTypeInd","setTaxTypeInd","TAX_TYPE_IND,1,7,1,0,false|,0,CHAR,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getAnnuityTypeDesc","setAnnuityTypeDesc","ANNUITY_TYPE_DESC,1,8,20,0,false|,0,CHAR,4,null,null,null,null,null"));
    }
    
    public String getPagingSQL(String schemaName,boolean isSubsetMode,boolean isNext, boolean locator)
    {
        String pagingWhere = null;
        String order = null;
        if (isNext) {
            if (isSubsetMode)
                if (locator)
                    pagingWhere = ".TAD1F WHERE (COMPANY_CODE = :company_code) AND (TABLE_SUBSET = :table_subset) AND ((EFFECTIVE_DATE > :effective_date) OR (EFFECTIVE_DATE = :effective_date AND ANNUITY_TYPE > :annuity_type) OR (EFFECTIVE_DATE = :effective_date AND ANNUITY_TYPE = :annuity_type AND TRANSACTION_CODE > :transaction_code) OR (EFFECTIVE_DATE = :effective_date AND ANNUITY_TYPE = :annuity_type AND TRANSACTION_CODE = :transaction_code AND DISTRIB_CODE > :distrib_code) OR (EFFECTIVE_DATE = :effective_date AND ANNUITY_TYPE = :annuity_type AND TRANSACTION_CODE = :transaction_code AND DISTRIB_CODE = :distrib_code)) ";
                else 
                    pagingWhere = ".TAD1F WHERE (COMPANY_CODE = :company_code) AND (TABLE_SUBSET = :table_subset) AND ((EFFECTIVE_DATE > :effective_date) OR (EFFECTIVE_DATE = :effective_date AND ANNUITY_TYPE > :annuity_type) OR (EFFECTIVE_DATE = :effective_date AND ANNUITY_TYPE = :annuity_type AND TRANSACTION_CODE > :transaction_code) OR (EFFECTIVE_DATE = :effective_date AND ANNUITY_TYPE = :annuity_type AND TRANSACTION_CODE = :transaction_code AND DISTRIB_CODE > :distrib_code)) ";
            else
                if (locator)
                    pagingWhere = ".TAD1F WHERE (COMPANY_CODE = :company_code) AND ((TABLE_SUBSET > :table_subset) OR (TABLE_SUBSET = :table_subset AND EFFECTIVE_DATE > :effective_date) OR (TABLE_SUBSET = :table_subset AND EFFECTIVE_DATE = :effective_date AND ANNUITY_TYPE > :annuity_type) OR (TABLE_SUBSET = :table_subset AND EFFECTIVE_DATE = :effective_date AND ANNUITY_TYPE = :annuity_type AND TRANSACTION_CODE > :transaction_code) OR (TABLE_SUBSET = :table_subset AND EFFECTIVE_DATE = :effective_date AND ANNUITY_TYPE = :annuity_type AND TRANSACTION_CODE = :transaction_code AND DISTRIB_CODE > :distrib_code) OR (TABLE_SUBSET = :table_subset AND EFFECTIVE_DATE = :effective_date AND ANNUITY_TYPE = :annuity_type AND TRANSACTION_CODE = :transaction_code AND DISTRIB_CODE = :distrib_code)) ";
                else
                    pagingWhere = ".TAD1F WHERE (COMPANY_CODE = :company_code) AND ((TABLE_SUBSET > :table_subset) OR (TABLE_SUBSET = :table_subset AND EFFECTIVE_DATE > :effective_date) OR (TABLE_SUBSET = :table_subset AND EFFECTIVE_DATE = :effective_date AND ANNUITY_TYPE > :annuity_type) OR (TABLE_SUBSET = :table_subset AND EFFECTIVE_DATE = :effective_date AND ANNUITY_TYPE = :annuity_type AND TRANSACTION_CODE > :transaction_code) OR (TABLE_SUBSET = :table_subset AND EFFECTIVE_DATE = :effective_date AND ANNUITY_TYPE = :annuity_type AND TRANSACTION_CODE = :transaction_code AND DISTRIB_CODE > :distrib_code)) ";
            order = " ORDER BY COMPANY_CODE, TABLE_SUBSET, EFFECTIVE_DATE, ANNUITY_TYPE, TRANSACTION_CODE, DISTRIB_CODE";
        }
        else {
            if (isSubsetMode)
                pagingWhere = ".TAD1F WHERE (COMPANY_CODE = :company_code) AND (TABLE_SUBSET = :table_subset) AND ((EFFECTIVE_DATE < :effective_date) OR (EFFECTIVE_DATE = :effective_date AND ANNUITY_TYPE < :annuity_type) OR (EFFECTIVE_DATE = :effective_date AND ANNUITY_TYPE = :annuity_type AND TRANSACTION_CODE < :transaction_code) OR (EFFECTIVE_DATE = :effective_date AND ANNUITY_TYPE = :annuity_type AND TRANSACTION_CODE = :transaction_code AND DISTRIB_CODE < :distrib_code)) ";
            else
                pagingWhere = ".TAD1F WHERE (COMPANY_CODE = :company_code) AND ((TABLE_SUBSET < :table_subset) OR (TABLE_SUBSET = :table_subset AND EFFECTIVE_DATE < :effective_date) OR (TABLE_SUBSET = :table_subset AND EFFECTIVE_DATE = :effective_date AND ANNUITY_TYPE < :annuity_type) OR (TABLE_SUBSET = :table_subset AND EFFECTIVE_DATE = :effective_date AND ANNUITY_TYPE = :annuity_type AND TRANSACTION_CODE < :transaction_code) OR (TABLE_SUBSET = :table_subset AND EFFECTIVE_DATE = :effective_date AND ANNUITY_TYPE = :annuity_type AND TRANSACTION_CODE = :transaction_code AND DISTRIB_CODE < :distrib_code)) ";
            order = " ORDER BY COMPANY_CODE DESC, TABLE_SUBSET DESC, EFFECTIVE_DATE DESC, ANNUITY_TYPE DESC, TRANSACTION_CODE DESC, DISTRIB_CODE DESC";
        }
        return pagingSql + schemaName + pagingWhere + order;
    }
    
    public String prepareInsertStmt(String schemaName) {
        StringBuffer sb = new StringBuffer();
        sb.append("INSERT INTO ").append(schemaName);
        sb.append(".TAD1F ( ");
        sb.append("COMPANY_CODE, TABLE_SUBSET, EFFECTIVE_DATE, ANNUITY_TYPE, TRANSACTION_CODE, DISTRIB_CODE, TAX_TYPE_IND, ANNUITY_TYPE_DESC )");
        sb.append(" VALUES (?, ?, ?, ?, ?, ?, ?, ? )");
        return sb.toString();
    }
    
    public String prepareUpdateStmt(String schemaName)
    {
        StringBuffer sb = new StringBuffer();
        sb.append("UPDATE ").append(schemaName);
        sb.append(".TAD1F ");
        sb.append(" SET COMPANY_CODE = ?, TABLE_SUBSET = ?, EFFECTIVE_DATE = ?, ANNUITY_TYPE = ?, TRANSACTION_CODE = ?, DISTRIB_CODE = ?, TAX_TYPE_IND = ?, ANNUITY_TYPE_DESC = ?");
        sb.append(" WHERE COMPANY_CODE = ? AND TABLE_SUBSET = ? AND EFFECTIVE_DATE = ? AND ANNUITY_TYPE = ? AND TRANSACTION_CODE = ? AND DISTRIB_CODE = ?");
        return sb.toString();
    }
    
    public String prepareDeleteStmt(String schemaName)
    {
        StringBuffer sb = new StringBuffer();
        sb.append("DELETE FROM ").append(schemaName);
        sb.append(".TAD1F ");
        sb.append(" WHERE COMPANY_CODE = ? AND TABLE_SUBSET = ? AND EFFECTIVE_DATE = ? AND ANNUITY_TYPE = ? AND TRANSACTION_CODE = ? AND DISTRIB_CODE = ?");
        return sb.toString();
    }
}
