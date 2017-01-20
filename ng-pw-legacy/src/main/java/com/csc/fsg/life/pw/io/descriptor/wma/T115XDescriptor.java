package com.csc.fsg.life.pw.io.descriptor.wma;

import com.csc.fsg.life.pw.io.*;

public class T115XDescriptor
    extends TableDescriptor
{
    private static final String pagingSql = "SELECT COMPANY_CODE, ENTRY_NUMBER, EFFECTIVE_DATE, CURRENT_FAV_CODE, DURATION, AMOUNT, NEW_FAV_CODE FROM ";
    
    public void initialize()
    {
        setRowClass(T115XRow.class);
        setTableName("T115X");
        setTableId("115");
        super.initialize();
    }
    
    public void initializeColumnDescriptors()
    {
        super.initializeColumnDescriptors();
        addColumnDescriptor(new ColumnDescriptor(this,"getCompanyCode","setCompanyCode","COMPANY_CODE,1,1,3,0,true|,0,CHAR,1,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getEntryNumber","setEntryNumber","ENTRY_NUMBER,1,2,16,0,true|,0,CHAR,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getEffectiveDate","setEffectiveDate","EFFECTIVE_DATE,91,3,10,0,true|,0,DATE,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getCurrentFavCode","setCurrentFavCode","CURRENT_FAV_CODE,1,4,6,0,true|,0,CHAR,4,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getDuration","setDuration","DURATION,5,5,3,0,true|,0,INTEGER,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getAmount","setAmount","AMOUNT,3,6,11,2,true|,0,DOUBLE,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getNewFavCode","setNewFavCode","NEW_FAV_CODE,1,7,6,0,false|,0,CHAR,0,null,null,null,null,null"));
    }
    
    public String getPagingSQL(String schemaName,boolean isSubsetMode,boolean isNext, boolean locator)
    {
        String pagingWhere = null;
        String order = null;
        if (isNext) {
            if (isSubsetMode)
                if (locator)
                    pagingWhere = ".T115X WHERE (COMPANY_CODE = :company_code) AND ((ENTRY_NUMBER > :entry_number) OR (ENTRY_NUMBER = :entry_number AND EFFECTIVE_DATE > :effective_date) OR (ENTRY_NUMBER = :entry_number AND EFFECTIVE_DATE = :effective_date AND CURRENT_FAV_CODE > :current_fav_code) OR (ENTRY_NUMBER = :entry_number AND EFFECTIVE_DATE = :effective_date AND CURRENT_FAV_CODE = :current_fav_code AND DURATION > :duration) OR (ENTRY_NUMBER = :entry_number AND EFFECTIVE_DATE = :effective_date AND CURRENT_FAV_CODE = :current_fav_code AND DURATION = :duration AND AMOUNT > :amount) OR (ENTRY_NUMBER = :entry_number AND EFFECTIVE_DATE = :effective_date AND CURRENT_FAV_CODE = :current_fav_code AND DURATION = :duration AND AMOUNT = :amount)) ";
                else 
                    pagingWhere = ".T115X WHERE (COMPANY_CODE = :company_code) AND ((ENTRY_NUMBER > :entry_number) OR (ENTRY_NUMBER = :entry_number AND EFFECTIVE_DATE > :effective_date) OR (ENTRY_NUMBER = :entry_number AND EFFECTIVE_DATE = :effective_date AND CURRENT_FAV_CODE > :current_fav_code) OR (ENTRY_NUMBER = :entry_number AND EFFECTIVE_DATE = :effective_date AND CURRENT_FAV_CODE = :current_fav_code AND DURATION > :duration) OR (ENTRY_NUMBER = :entry_number AND EFFECTIVE_DATE = :effective_date AND CURRENT_FAV_CODE = :current_fav_code AND DURATION = :duration AND AMOUNT > :amount)) ";
            else
                if (locator)
                    pagingWhere = ".T115X WHERE (COMPANY_CODE = :company_code) AND ((ENTRY_NUMBER > :entry_number) OR (ENTRY_NUMBER = :entry_number AND EFFECTIVE_DATE > :effective_date) OR (ENTRY_NUMBER = :entry_number AND EFFECTIVE_DATE = :effective_date AND CURRENT_FAV_CODE > :current_fav_code) OR (ENTRY_NUMBER = :entry_number AND EFFECTIVE_DATE = :effective_date AND CURRENT_FAV_CODE = :current_fav_code AND DURATION > :duration) OR (ENTRY_NUMBER = :entry_number AND EFFECTIVE_DATE = :effective_date AND CURRENT_FAV_CODE = :current_fav_code AND DURATION = :duration AND AMOUNT > :amount) OR (ENTRY_NUMBER = :entry_number AND EFFECTIVE_DATE = :effective_date AND CURRENT_FAV_CODE = :current_fav_code AND DURATION = :duration AND AMOUNT = :amount)) ";
                else
                    pagingWhere = ".T115X WHERE (COMPANY_CODE = :company_code) AND ((ENTRY_NUMBER > :entry_number) OR (ENTRY_NUMBER = :entry_number AND EFFECTIVE_DATE > :effective_date) OR (ENTRY_NUMBER = :entry_number AND EFFECTIVE_DATE = :effective_date AND CURRENT_FAV_CODE > :current_fav_code) OR (ENTRY_NUMBER = :entry_number AND EFFECTIVE_DATE = :effective_date AND CURRENT_FAV_CODE = :current_fav_code AND DURATION > :duration) OR (ENTRY_NUMBER = :entry_number AND EFFECTIVE_DATE = :effective_date AND CURRENT_FAV_CODE = :current_fav_code AND DURATION = :duration AND AMOUNT > :amount)) ";
            order = " ORDER BY COMPANY_CODE, ENTRY_NUMBER, EFFECTIVE_DATE, CURRENT_FAV_CODE, DURATION, AMOUNT";
        }
        else {
            if (isSubsetMode)
                pagingWhere = ".T115X WHERE (COMPANY_CODE = :company_code) AND ((ENTRY_NUMBER < :entry_number) OR (ENTRY_NUMBER = :entry_number AND EFFECTIVE_DATE < :effective_date) OR (ENTRY_NUMBER = :entry_number AND EFFECTIVE_DATE = :effective_date AND CURRENT_FAV_CODE < :current_fav_code) OR (ENTRY_NUMBER = :entry_number AND EFFECTIVE_DATE = :effective_date AND CURRENT_FAV_CODE = :current_fav_code AND DURATION < :duration) OR (ENTRY_NUMBER = :entry_number AND EFFECTIVE_DATE = :effective_date AND CURRENT_FAV_CODE = :current_fav_code AND DURATION = :duration AND AMOUNT < :amount)) ";
            else
                pagingWhere = ".T115X WHERE (COMPANY_CODE = :company_code) AND ((ENTRY_NUMBER < :entry_number) OR (ENTRY_NUMBER = :entry_number AND EFFECTIVE_DATE < :effective_date) OR (ENTRY_NUMBER = :entry_number AND EFFECTIVE_DATE = :effective_date AND CURRENT_FAV_CODE < :current_fav_code) OR (ENTRY_NUMBER = :entry_number AND EFFECTIVE_DATE = :effective_date AND CURRENT_FAV_CODE = :current_fav_code AND DURATION < :duration) OR (ENTRY_NUMBER = :entry_number AND EFFECTIVE_DATE = :effective_date AND CURRENT_FAV_CODE = :current_fav_code AND DURATION = :duration AND AMOUNT < :amount)) ";
            order = " ORDER BY COMPANY_CODE DESC, ENTRY_NUMBER DESC, EFFECTIVE_DATE DESC, CURRENT_FAV_CODE DESC, DURATION DESC, AMOUNT DESC";
        }
        return pagingSql + schemaName + pagingWhere + order;
    }
    
    public String prepareInsertStmt(String schemaName) {
        StringBuffer sb = new StringBuffer();
        sb.append("INSERT INTO ").append(schemaName);
        sb.append(".T115X ( ");
        sb.append("COMPANY_CODE, ENTRY_NUMBER, EFFECTIVE_DATE, CURRENT_FAV_CODE, DURATION, AMOUNT, NEW_FAV_CODE )");
        sb.append(" VALUES (?, ?, ?, ?, ?, ?, ? )");
        return sb.toString();
    }
    
    public String prepareUpdateStmt(String schemaName)
    {
        StringBuffer sb = new StringBuffer();
        sb.append("UPDATE ").append(schemaName);
        sb.append(".T115X ");
        sb.append(" SET COMPANY_CODE = ?, ENTRY_NUMBER = ?, EFFECTIVE_DATE = ?, CURRENT_FAV_CODE = ?, DURATION = ?, AMOUNT = ?, NEW_FAV_CODE = ?");
        sb.append(" WHERE COMPANY_CODE = ? AND ENTRY_NUMBER = ? AND EFFECTIVE_DATE = ? AND CURRENT_FAV_CODE = ? AND DURATION = ? AND AMOUNT = ?");
        return sb.toString();
    }
    
    public String prepareDeleteStmt(String schemaName)
    {
        StringBuffer sb = new StringBuffer();
        sb.append("DELETE FROM ").append(schemaName);
        sb.append(".T115X ");
        sb.append(" WHERE COMPANY_CODE = ? AND ENTRY_NUMBER = ? AND EFFECTIVE_DATE = ? AND CURRENT_FAV_CODE = ? AND DURATION = ? AND AMOUNT = ?");
        return sb.toString();
    }
}
