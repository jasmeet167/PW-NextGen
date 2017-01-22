package com.csc.fsg.life.pw.web.io.descriptor.wma;

import com.csc.fsg.life.pw.web.io.*;

public class TW24XDescriptor
    extends TableDescriptor
{
    private static final String pagingSql = "SELECT COMPANY_CODE, PRODUCT_PREFIX, TABLE_SUBSET, FUND_NUMBER, EFFECTIVE_DATE, AIR, MEMO_CODE, GUAR_PAYT_INT_TBL, GUAR_TYPE, GUAR_DUR, EXCESS_INT_TBL, RATE_EFF_ON, SETTLEMENT_INT_TBL, COMMUTED_VAL_TBL, DACM_TBL, LEAP_YEAR_DAYS FROM ";
    
    public void initialize()
    {
        setRowClass(TW24XRow.class);
        setTableName("TW24X");
        setTableId("W24");
        super.initialize();
    }
    
    public void initializeColumnDescriptors()
    {
        super.initializeColumnDescriptors();
        addColumnDescriptor(new ColumnDescriptor(this,"getCompanyCode","setCompanyCode","COMPANY_CODE,1,1,3,0,true|A,0,CHAR,1,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getProductPrefix","setProductPrefix","PRODUCT_PREFIX,1,2,1,0,true|A,0,CHAR,1,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getTableSubset","setTableSubset","TABLE_SUBSET,1,3,16,0,true|A,0,CHAR,1,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getFundNumber","setFundNumber","FUND_NUMBER,3,4,8,0,true|A,0,INTEGER,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getEffectiveDate","setEffectiveDate","EFFECTIVE_DATE,91,5,10,0,true|A,0,DATE,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getAir","setAir","AIR,3,6,5,3,true|A,0,DOUBLE,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getMemoCode","setMemoCode","MEMO_CODE,1,7,2,0,true|A,0,CHAR,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getGuarPaytIntTbl","setGuarPaytIntTbl","GUAR_PAYT_INT_TBL,1,8,16,0,false|A,0,CHAR,0,025,G,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getGuarType","setGuarType","GUAR_TYPE,1,9,5,0,false|A,0,CHAR,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getGuarDur","setGuarDur","GUAR_DUR,3,10,3,0,false|A,0,INTEGER,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getExcessIntTbl","setExcessIntTbl","EXCESS_INT_TBL,1,11,16,0,false|A,0,CHAR,0,025,E,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getRateEffOn","setRateEffOn","RATE_EFF_ON,1,12,1,0,false|A,0,CHAR,4,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getSettlementIntTbl","setSettlementIntTbl","SETTLEMENT_INT_TBL,1,13,16,0,false|A,0,CHAR,0,025,S,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getCommutedValTbl","setCommutedValTbl","COMMUTED_VAL_TBL,1,14,16,0,false|A,0,CHAR,0,025,C,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getDacmTbl","setDacmTbl","DACM_TBL,1,15,16,0,false|A,0,CHAR,0,025,D,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getLeapYearDays","setLeapYearDays","LEAP_YEAR_DAYS,3,16,3,0,false|A,0,INTEGER,0,null,null,null,null,null"));
    }
    
    public String getPagingSQL(String schemaName,boolean isSubsetMode,boolean isNext, boolean locator)
    {
        String pagingWhere = null;
        String order = null;
        if (isNext) {
            if (isSubsetMode)
                if (locator)
                    pagingWhere = ".TW24X WHERE (COMPANY_CODE = :company_code) AND (PRODUCT_PREFIX = :product_prefix) AND (TABLE_SUBSET = :table_subset) AND ((FUND_NUMBER > :fund_number) OR (FUND_NUMBER = :fund_number AND EFFECTIVE_DATE > :effective_date) OR (FUND_NUMBER = :fund_number AND EFFECTIVE_DATE = :effective_date AND AIR > :air) OR (FUND_NUMBER = :fund_number AND EFFECTIVE_DATE = :effective_date AND AIR = :air AND MEMO_CODE > :memo_code) OR (FUND_NUMBER = :fund_number AND EFFECTIVE_DATE = :effective_date AND AIR = :air AND MEMO_CODE = :memo_code)) ";
                else 
                    pagingWhere = ".TW24X WHERE (COMPANY_CODE = :company_code) AND (PRODUCT_PREFIX = :product_prefix) AND (TABLE_SUBSET = :table_subset) AND ((FUND_NUMBER > :fund_number) OR (FUND_NUMBER = :fund_number AND EFFECTIVE_DATE > :effective_date) OR (FUND_NUMBER = :fund_number AND EFFECTIVE_DATE = :effective_date AND AIR > :air) OR (FUND_NUMBER = :fund_number AND EFFECTIVE_DATE = :effective_date AND AIR = :air AND MEMO_CODE > :memo_code)) ";
            else
                if (locator)
                    pagingWhere = ".TW24X WHERE (COMPANY_CODE = :company_code) AND ((PRODUCT_PREFIX > :product_prefix) OR (PRODUCT_PREFIX = :product_prefix AND TABLE_SUBSET > :table_subset) OR (PRODUCT_PREFIX = :product_prefix AND TABLE_SUBSET = :table_subset AND FUND_NUMBER > :fund_number) OR (PRODUCT_PREFIX = :product_prefix AND TABLE_SUBSET = :table_subset AND FUND_NUMBER = :fund_number AND EFFECTIVE_DATE > :effective_date) OR (PRODUCT_PREFIX = :product_prefix AND TABLE_SUBSET = :table_subset AND FUND_NUMBER = :fund_number AND EFFECTIVE_DATE = :effective_date AND AIR > :air) OR (PRODUCT_PREFIX = :product_prefix AND TABLE_SUBSET = :table_subset AND FUND_NUMBER = :fund_number AND EFFECTIVE_DATE = :effective_date AND AIR = :air AND MEMO_CODE > :memo_code) OR (PRODUCT_PREFIX = :product_prefix AND TABLE_SUBSET = :table_subset AND FUND_NUMBER = :fund_number AND EFFECTIVE_DATE = :effective_date AND AIR = :air AND MEMO_CODE = :memo_code)) ";
                else
                    pagingWhere = ".TW24X WHERE (COMPANY_CODE = :company_code) AND ((PRODUCT_PREFIX > :product_prefix) OR (PRODUCT_PREFIX = :product_prefix AND TABLE_SUBSET > :table_subset) OR (PRODUCT_PREFIX = :product_prefix AND TABLE_SUBSET = :table_subset AND FUND_NUMBER > :fund_number) OR (PRODUCT_PREFIX = :product_prefix AND TABLE_SUBSET = :table_subset AND FUND_NUMBER = :fund_number AND EFFECTIVE_DATE > :effective_date) OR (PRODUCT_PREFIX = :product_prefix AND TABLE_SUBSET = :table_subset AND FUND_NUMBER = :fund_number AND EFFECTIVE_DATE = :effective_date AND AIR > :air) OR (PRODUCT_PREFIX = :product_prefix AND TABLE_SUBSET = :table_subset AND FUND_NUMBER = :fund_number AND EFFECTIVE_DATE = :effective_date AND AIR = :air AND MEMO_CODE > :memo_code)) ";
            order = " ORDER BY COMPANY_CODE, PRODUCT_PREFIX, TABLE_SUBSET, FUND_NUMBER, EFFECTIVE_DATE, AIR, MEMO_CODE";
        }
        else {
            if (isSubsetMode)
                pagingWhere = ".TW24X WHERE (COMPANY_CODE = :company_code) AND (PRODUCT_PREFIX = :product_prefix) AND (TABLE_SUBSET = :table_subset) AND ((FUND_NUMBER < :fund_number) OR (FUND_NUMBER = :fund_number AND EFFECTIVE_DATE < :effective_date) OR (FUND_NUMBER = :fund_number AND EFFECTIVE_DATE = :effective_date AND AIR < :air) OR (FUND_NUMBER = :fund_number AND EFFECTIVE_DATE = :effective_date AND AIR = :air AND MEMO_CODE < :memo_code)) ";
            else
                pagingWhere = ".TW24X WHERE (COMPANY_CODE = :company_code) AND ((PRODUCT_PREFIX < :product_prefix) OR (PRODUCT_PREFIX = :product_prefix AND TABLE_SUBSET < :table_subset) OR (PRODUCT_PREFIX = :product_prefix AND TABLE_SUBSET = :table_subset AND FUND_NUMBER < :fund_number) OR (PRODUCT_PREFIX = :product_prefix AND TABLE_SUBSET = :table_subset AND FUND_NUMBER = :fund_number AND EFFECTIVE_DATE < :effective_date) OR (PRODUCT_PREFIX = :product_prefix AND TABLE_SUBSET = :table_subset AND FUND_NUMBER = :fund_number AND EFFECTIVE_DATE = :effective_date AND AIR < :air) OR (PRODUCT_PREFIX = :product_prefix AND TABLE_SUBSET = :table_subset AND FUND_NUMBER = :fund_number AND EFFECTIVE_DATE = :effective_date AND AIR = :air AND MEMO_CODE < :memo_code)) ";
            order = " ORDER BY COMPANY_CODE DESC, PRODUCT_PREFIX DESC, TABLE_SUBSET DESC, FUND_NUMBER DESC, EFFECTIVE_DATE DESC, AIR DESC, MEMO_CODE DESC";
        }
        return pagingSql + schemaName + pagingWhere + order;
    }
    
    public String prepareInsertStmt(String schemaName) {
        StringBuffer sb = new StringBuffer();
        sb.append("INSERT INTO ").append(schemaName);
        sb.append(".TW24X ( ");
        sb.append("COMPANY_CODE, PRODUCT_PREFIX, TABLE_SUBSET, FUND_NUMBER, EFFECTIVE_DATE, AIR, MEMO_CODE, GUAR_PAYT_INT_TBL, GUAR_TYPE, GUAR_DUR, EXCESS_INT_TBL, RATE_EFF_ON, SETTLEMENT_INT_TBL, COMMUTED_VAL_TBL, DACM_TBL, LEAP_YEAR_DAYS )");
        sb.append(" VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ? )");
        return sb.toString();
    }
    
    public String prepareUpdateStmt(String schemaName)
    {
        StringBuffer sb = new StringBuffer();
        sb.append("UPDATE ").append(schemaName);
        sb.append(".TW24X ");
        sb.append(" SET COMPANY_CODE = ?, PRODUCT_PREFIX = ?, TABLE_SUBSET = ?, FUND_NUMBER = ?, EFFECTIVE_DATE = ?, AIR = ?, MEMO_CODE = ?, GUAR_PAYT_INT_TBL = ?, GUAR_TYPE = ?, GUAR_DUR = ?, EXCESS_INT_TBL = ?, RATE_EFF_ON = ?, SETTLEMENT_INT_TBL = ?, COMMUTED_VAL_TBL = ?, DACM_TBL = ?, LEAP_YEAR_DAYS = ?");
        sb.append(" WHERE COMPANY_CODE = ? AND PRODUCT_PREFIX = ? AND TABLE_SUBSET = ? AND FUND_NUMBER = ? AND EFFECTIVE_DATE = ? AND AIR = ? AND MEMO_CODE = ?");
        return sb.toString();
    }
    
    public String prepareDeleteStmt(String schemaName)
    {
        StringBuffer sb = new StringBuffer();
        sb.append("DELETE FROM ").append(schemaName);
        sb.append(".TW24X ");
        sb.append(" WHERE COMPANY_CODE = ? AND PRODUCT_PREFIX = ? AND TABLE_SUBSET = ? AND FUND_NUMBER = ? AND EFFECTIVE_DATE = ? AND AIR = ? AND MEMO_CODE = ?");
        return sb.toString();
    }
}
