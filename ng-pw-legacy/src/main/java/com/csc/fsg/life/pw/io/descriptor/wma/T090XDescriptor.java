package com.csc.fsg.life.pw.io.descriptor.wma;

import com.csc.fsg.life.pw.io.*;

public class T090XDescriptor
    extends TableDescriptor
{
    private static final String pagingSql = "SELECT COMPANY_CODE, ASSET_ACCT_CODE, MONEY_SOURCE_CODE, ASSET_ACCT_NAME, SOURCE_DESC FROM ";
    
    public void initialize()
    {
        setRowClass(T090XRow.class);
        setTableName("T090X");
        setTableId("090");
        super.initialize();
    }
    
    public void initializeColumnDescriptors()
    {
        super.initializeColumnDescriptors();
        addColumnDescriptor(new ColumnDescriptor(this,"getCompanyCode","setCompanyCode","COMPANY_CODE,1,1,3,0,true|,0,CHAR,1,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getAssetAcctCode","setAssetAcctCode","ASSET_ACCT_CODE,1,2,2,0,true|,0,CHAR,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getMoneySourceCode","setMoneySourceCode","MONEY_SOURCE_CODE,1,3,2,0,true|,0,CHAR,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getAssetAcctName","setAssetAcctName","ASSET_ACCT_NAME,1,4,25,0,false|,0,CHAR,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getSourceDesc","setSourceDesc","SOURCE_DESC,1,5,13,0,false|,0,CHAR,4,null,null,null,null,null"));
    }
    
    public String getPagingSQL(String schemaName,boolean isSubsetMode,boolean isNext, boolean locator)
    {
        String pagingWhere = null;
        String order = null;
        if (isNext) {
            if (isSubsetMode)
                if (locator)
                    pagingWhere = ".T090X WHERE (COMPANY_CODE = :company_code) AND ((ASSET_ACCT_CODE > :asset_acct_code) OR (ASSET_ACCT_CODE = :asset_acct_code AND MONEY_SOURCE_CODE > :money_source_code) OR (ASSET_ACCT_CODE = :asset_acct_code AND MONEY_SOURCE_CODE = :money_source_code)) ";
                else 
                    pagingWhere = ".T090X WHERE (COMPANY_CODE = :company_code) AND ((ASSET_ACCT_CODE > :asset_acct_code) OR (ASSET_ACCT_CODE = :asset_acct_code AND MONEY_SOURCE_CODE > :money_source_code)) ";
            else
                if (locator)
                    pagingWhere = ".T090X WHERE (COMPANY_CODE = :company_code) AND ((ASSET_ACCT_CODE > :asset_acct_code) OR (ASSET_ACCT_CODE = :asset_acct_code AND MONEY_SOURCE_CODE > :money_source_code) OR (ASSET_ACCT_CODE = :asset_acct_code AND MONEY_SOURCE_CODE = :money_source_code)) ";
                else
                    pagingWhere = ".T090X WHERE (COMPANY_CODE = :company_code) AND ((ASSET_ACCT_CODE > :asset_acct_code) OR (ASSET_ACCT_CODE = :asset_acct_code AND MONEY_SOURCE_CODE > :money_source_code)) ";
            order = " ORDER BY COMPANY_CODE, ASSET_ACCT_CODE, MONEY_SOURCE_CODE";
        }
        else {
            if (isSubsetMode)
                pagingWhere = ".T090X WHERE (COMPANY_CODE = :company_code) AND ((ASSET_ACCT_CODE < :asset_acct_code) OR (ASSET_ACCT_CODE = :asset_acct_code AND MONEY_SOURCE_CODE < :money_source_code)) ";
            else
                pagingWhere = ".T090X WHERE (COMPANY_CODE = :company_code) AND ((ASSET_ACCT_CODE < :asset_acct_code) OR (ASSET_ACCT_CODE = :asset_acct_code AND MONEY_SOURCE_CODE < :money_source_code)) ";
            order = " ORDER BY COMPANY_CODE DESC, ASSET_ACCT_CODE DESC, MONEY_SOURCE_CODE DESC";
        }
        return pagingSql + schemaName + pagingWhere + order;
    }
    
    public String prepareInsertStmt(String schemaName) {
        StringBuffer sb = new StringBuffer();
        sb.append("INSERT INTO ").append(schemaName);
        sb.append(".T090X ( ");
        sb.append("COMPANY_CODE, ASSET_ACCT_CODE, MONEY_SOURCE_CODE, ASSET_ACCT_NAME, SOURCE_DESC )");
        sb.append(" VALUES (?, ?, ?, ?, ? )");
        return sb.toString();
    }
    
    public String prepareUpdateStmt(String schemaName)
    {
        StringBuffer sb = new StringBuffer();
        sb.append("UPDATE ").append(schemaName);
        sb.append(".T090X ");
        sb.append(" SET COMPANY_CODE = ?, ASSET_ACCT_CODE = ?, MONEY_SOURCE_CODE = ?, ASSET_ACCT_NAME = ?, SOURCE_DESC = ?");
        sb.append(" WHERE COMPANY_CODE = ? AND ASSET_ACCT_CODE = ? AND MONEY_SOURCE_CODE = ?");
        return sb.toString();
    }
    
    public String prepareDeleteStmt(String schemaName)
    {
        StringBuffer sb = new StringBuffer();
        sb.append("DELETE FROM ").append(schemaName);
        sb.append(".T090X ");
        sb.append(" WHERE COMPANY_CODE = ? AND ASSET_ACCT_CODE = ? AND MONEY_SOURCE_CODE = ?");
        return sb.toString();
    }
}
