package com.csc.fsg.life.pw.web.io.descriptor.wma;

import com.csc.fsg.life.pw.web.io.*;

public class T113XDescriptor
    extends TableDescriptor
{
    private static final String pagingSql = "SELECT COMPANY_CODE, FAV_CODE, FAV_EFFECTIVE_DATE, FAV_FUND_NUMBER, FAV_TERM_DATE, FAV_NB_TERM_DATE, FAV_FACTOR, FAV_CALC_IND, FAV_USE_INDICATOR, FAV_DESCRIPTION, ASSUM_INT_RATE_TBL FROM ";
    
    public void initialize()
    {
        setRowClass(T113XRow.class);
        setTableName("T113X");
        setTableId("113");
        super.initialize();
    }
    
    public void initializeColumnDescriptors()
    {
        super.initializeColumnDescriptors();
        addColumnDescriptor(new ColumnDescriptor(this,"getCompanyCode","setCompanyCode","COMPANY_CODE,1,1,3,0,true|,0,CHAR,1,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getFavCode","setFavCode","FAV_CODE,1,2,6,0,true|,0,CHAR,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getFavEffectiveDate","setFavEffectiveDate","FAV_EFFECTIVE_DATE,91,3,10,0,true|,0,DATE,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getFavFundNumber","setFavFundNumber","FAV_FUND_NUMBER,3,4,8,0,true|,0,INTEGER,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getFavTermDate","setFavTermDate","FAV_TERM_DATE,91,5,10,0,false|,0,DATE,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getFavNbTermDate","setFavNbTermDate","FAV_NB_TERM_DATE,91,6,10,0,false|,0,DATE,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getFavFactor","setFavFactor","FAV_FACTOR,3,7,11,8,false|,0,DOUBLE,4,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getFavCalcInd","setFavCalcInd","FAV_CALC_IND,1,8,1,0,false|,0,CHAR,4,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getFavUseIndicator","setFavUseIndicator","FAV_USE_INDICATOR,1,9,1,0,false|,0,CHAR,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getFavDescription","setFavDescription","FAV_DESCRIPTION,1,10,50,0,false|,0,CHAR,4,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getAssumIntRateTbl","setAssumIntRateTbl","ASSUM_INT_RATE_TBL,1,11,16,0,false|,0,CHAR,0,null,null,null,null,null"));
    }
    
    public String getPagingSQL(String schemaName,boolean isSubsetMode,boolean isNext, boolean locator)
    {
        String pagingWhere = null;
        String order = null;
        if (isNext) {
            if (isSubsetMode)
                if (locator)
                    pagingWhere = ".T113X WHERE (COMPANY_CODE = :company_code) AND ((FAV_CODE > :fav_code) OR (FAV_CODE = :fav_code AND FAV_EFFECTIVE_DATE > :fav_effective_date) OR (FAV_CODE = :fav_code AND FAV_EFFECTIVE_DATE = :fav_effective_date AND FAV_FUND_NUMBER > :fav_fund_number) OR (FAV_CODE = :fav_code AND FAV_EFFECTIVE_DATE = :fav_effective_date AND FAV_FUND_NUMBER = :fav_fund_number)) ";
                else 
                    pagingWhere = ".T113X WHERE (COMPANY_CODE = :company_code) AND ((FAV_CODE > :fav_code) OR (FAV_CODE = :fav_code AND FAV_EFFECTIVE_DATE > :fav_effective_date) OR (FAV_CODE = :fav_code AND FAV_EFFECTIVE_DATE = :fav_effective_date AND FAV_FUND_NUMBER > :fav_fund_number)) ";
            else
                if (locator)
                    pagingWhere = ".T113X WHERE (COMPANY_CODE = :company_code) AND ((FAV_CODE > :fav_code) OR (FAV_CODE = :fav_code AND FAV_EFFECTIVE_DATE > :fav_effective_date) OR (FAV_CODE = :fav_code AND FAV_EFFECTIVE_DATE = :fav_effective_date AND FAV_FUND_NUMBER > :fav_fund_number) OR (FAV_CODE = :fav_code AND FAV_EFFECTIVE_DATE = :fav_effective_date AND FAV_FUND_NUMBER = :fav_fund_number)) ";
                else
                    pagingWhere = ".T113X WHERE (COMPANY_CODE = :company_code) AND ((FAV_CODE > :fav_code) OR (FAV_CODE = :fav_code AND FAV_EFFECTIVE_DATE > :fav_effective_date) OR (FAV_CODE = :fav_code AND FAV_EFFECTIVE_DATE = :fav_effective_date AND FAV_FUND_NUMBER > :fav_fund_number)) ";
            order = " ORDER BY COMPANY_CODE, FAV_CODE, FAV_EFFECTIVE_DATE, FAV_FUND_NUMBER";
        }
        else {
            if (isSubsetMode)
                pagingWhere = ".T113X WHERE (COMPANY_CODE = :company_code) AND ((FAV_CODE < :fav_code) OR (FAV_CODE = :fav_code AND FAV_EFFECTIVE_DATE < :fav_effective_date) OR (FAV_CODE = :fav_code AND FAV_EFFECTIVE_DATE = :fav_effective_date AND FAV_FUND_NUMBER < :fav_fund_number)) ";
            else
                pagingWhere = ".T113X WHERE (COMPANY_CODE = :company_code) AND ((FAV_CODE < :fav_code) OR (FAV_CODE = :fav_code AND FAV_EFFECTIVE_DATE < :fav_effective_date) OR (FAV_CODE = :fav_code AND FAV_EFFECTIVE_DATE = :fav_effective_date AND FAV_FUND_NUMBER < :fav_fund_number)) ";
            order = " ORDER BY COMPANY_CODE DESC, FAV_CODE DESC, FAV_EFFECTIVE_DATE DESC, FAV_FUND_NUMBER DESC";
        }
        return pagingSql + schemaName + pagingWhere + order;
    }
    
    public String prepareInsertStmt(String schemaName) {
        StringBuffer sb = new StringBuffer();
        sb.append("INSERT INTO ").append(schemaName);
        sb.append(".T113X ( ");
        sb.append("COMPANY_CODE, FAV_CODE, FAV_EFFECTIVE_DATE, FAV_FUND_NUMBER, FAV_TERM_DATE, FAV_NB_TERM_DATE, FAV_FACTOR, FAV_CALC_IND, FAV_USE_INDICATOR, FAV_DESCRIPTION, ASSUM_INT_RATE_TBL )");
        sb.append(" VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ? )");
        return sb.toString();
    }
    
    public String prepareUpdateStmt(String schemaName)
    {
        StringBuffer sb = new StringBuffer();
        sb.append("UPDATE ").append(schemaName);
        sb.append(".T113X ");
        sb.append(" SET COMPANY_CODE = ?, FAV_CODE = ?, FAV_EFFECTIVE_DATE = ?, FAV_FUND_NUMBER = ?, FAV_TERM_DATE = ?, FAV_NB_TERM_DATE = ?, FAV_FACTOR = ?, FAV_CALC_IND = ?, FAV_USE_INDICATOR = ?, FAV_DESCRIPTION = ?, ASSUM_INT_RATE_TBL = ?");
        sb.append(" WHERE COMPANY_CODE = ? AND FAV_CODE = ? AND FAV_EFFECTIVE_DATE = ? AND FAV_FUND_NUMBER = ?");
        return sb.toString();
    }
    
    public String prepareDeleteStmt(String schemaName)
    {
        StringBuffer sb = new StringBuffer();
        sb.append("DELETE FROM ").append(schemaName);
        sb.append(".T113X ");
        sb.append(" WHERE COMPANY_CODE = ? AND FAV_CODE = ? AND FAV_EFFECTIVE_DATE = ? AND FAV_FUND_NUMBER = ?");
        return sb.toString();
    }
}
