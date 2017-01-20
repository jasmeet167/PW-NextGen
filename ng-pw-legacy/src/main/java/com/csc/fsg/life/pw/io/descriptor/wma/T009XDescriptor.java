package com.csc.fsg.life.pw.io.descriptor.wma;

import com.csc.fsg.life.pw.io.*;

public class T009XDescriptor
    extends TableDescriptor
{
    private static final String pagingSql = "SELECT COMPANY_CODE, FUND_NUMBER, TAX_STATUS, FUND_TYPE, FUND_STATUS, REGISTRATION_CODE, ACCOUNT_CODE, FUND_NAME, FUND_NAME_ABBRV, FUND_EFF_DT, FUND_TERM_DT, NEW_BUS_TERM_DT, FUND_FAMILY, FUND_STRATEGY, REBALANCING_IND, RFEE_SUBSET_PTR, HARD_CLOSE_EFF_DATE, HARD_CLOSE_ORIGINATOR_CODE, HARD_CLOSE_MEMO_CODE, HARD_CLOSE_SUCCESSOR_FUND, LIQUIDATION_EFF_DATE, LIQUIDATION_ORIGINATOR_CODE, LIQUIDATION_MEMO_CODE, LIQUIDATION_SUCCESSOR_FUND FROM ";
    
    public void initialize()
    {
        setRowClass(T009XRow.class);
        setTableName("T009X");
        setTableId("009");
        super.initialize();
    }
    
    public void initializeColumnDescriptors()
    {
        super.initializeColumnDescriptors();
        addColumnDescriptor(new ColumnDescriptor(this,"getCompanyCode","setCompanyCode","COMPANY_CODE,1,1,3,0,true|,0,CHAR,1,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getFundNumber","setFundNumber","FUND_NUMBER,3,2,8,0,true|,0,INTEGER,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getTaxStatus","setTaxStatus","TAX_STATUS,1,3,1,0,false|,0,CHAR,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getFundType","setFundType","FUND_TYPE,1,4,1,0,false|,0,CHAR,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getFundStatus","setFundStatus","FUND_STATUS,1,5,1,0,false|,0,CHAR,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getRegistrationCode","setRegistrationCode","REGISTRATION_CODE,1,6,1,0,false|,0,CHAR,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getAccountCode","setAccountCode","ACCOUNT_CODE,1,7,1,0,false|,0,CHAR,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getFundName","setFundName","FUND_NAME,1,8,80,0,false|,0,CHAR,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getFundNameAbbrv","setFundNameAbbrv","FUND_NAME_ABBRV,1,9,20,0,false|,0,CHAR,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getFundEffDt","setFundEffDt","FUND_EFF_DT,91,10,10,0,false|,0,DATE,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getFundTermDt","setFundTermDt","FUND_TERM_DT,91,11,10,0,false|,0,DATE,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getNewBusTermDt","setNewBusTermDt","NEW_BUS_TERM_DT,91,12,10,0,false|,0,DATE,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getFundFamily","setFundFamily","FUND_FAMILY,1,13,2,0,false|,0,CHAR,4,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getFundStrategy","setFundStrategy","FUND_STRATEGY,1,14,2,0,false|,0,CHAR,4,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getRebalancingInd","setRebalancingInd","REBALANCING_IND,1,15,1,0,false|,0,CHAR,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getRfeeSubsetPtr","setRfeeSubsetPtr","RFEE_SUBSET_PTR,1,16,16,0,false|,0,CHAR,0,125,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getHardCloseEffDate","setHardCloseEffDate","HARD_CLOSE_EFF_DATE,91,17,10,0,false|,0,DATE,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getHardCloseOriginatorCode","setHardCloseOriginatorCode","HARD_CLOSE_ORIGINATOR_CODE,1,18,8,0,false|,0,CHAR,4,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getHardCloseMemoCode","setHardCloseMemoCode","HARD_CLOSE_MEMO_CODE,1,19,2,0,false|,0,CHAR,4,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getHardCloseSuccessorFund","setHardCloseSuccessorFund","HARD_CLOSE_SUCCESSOR_FUND,3,20,8,0,false|,0,INTEGER,4,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getLiquidationEffDate","setLiquidationEffDate","LIQUIDATION_EFF_DATE,91,21,10,0,false|,0,DATE,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getLiquidationOriginatorCode","setLiquidationOriginatorCode","LIQUIDATION_ORIGINATOR_CODE,1,22,8,0,false|,0,CHAR,4,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getLiquidationMemoCode","setLiquidationMemoCode","LIQUIDATION_MEMO_CODE,1,23,2,0,false|,0,CHAR,4,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getLiquidationSuccessorFund","setLiquidationSuccessorFund","LIQUIDATION_SUCCESSOR_FUND,3,24,8,0,false|,0,INTEGER,4,null,null,null,null,null"));
    }
    
    public String getPagingSQL(String schemaName,boolean isSubsetMode,boolean isNext, boolean locator)
    {
        String pagingWhere = null;
        String order = null;
        if (isNext) {
            if (isSubsetMode)
                if (locator)
                    pagingWhere = ".T009X WHERE (COMPANY_CODE = :company_code) AND ((FUND_NUMBER > :fund_number) OR (FUND_NUMBER = :fund_number)) ";
                else 
                    pagingWhere = ".T009X WHERE (COMPANY_CODE = :company_code) AND ((FUND_NUMBER > :fund_number)) ";
            else
                if (locator)
                    pagingWhere = ".T009X WHERE (COMPANY_CODE = :company_code) AND ((FUND_NUMBER > :fund_number) OR (FUND_NUMBER = :fund_number)) ";
                else
                    pagingWhere = ".T009X WHERE (COMPANY_CODE = :company_code) AND ((FUND_NUMBER > :fund_number)) ";
            order = " ORDER BY COMPANY_CODE, FUND_NUMBER";
        }
        else {
            if (isSubsetMode)
                pagingWhere = ".T009X WHERE (COMPANY_CODE = :company_code) AND ((FUND_NUMBER < :fund_number)) ";
            else
                pagingWhere = ".T009X WHERE (COMPANY_CODE = :company_code) AND ((FUND_NUMBER < :fund_number)) ";
            order = " ORDER BY COMPANY_CODE DESC, FUND_NUMBER DESC";
        }
        return pagingSql + schemaName + pagingWhere + order;
    }
    
    public String prepareInsertStmt(String schemaName) {
        StringBuffer sb = new StringBuffer();
        sb.append("INSERT INTO ").append(schemaName);
        sb.append(".T009X ( ");
        sb.append("COMPANY_CODE, FUND_NUMBER, TAX_STATUS, FUND_TYPE, FUND_STATUS, REGISTRATION_CODE, ACCOUNT_CODE, FUND_NAME, FUND_NAME_ABBRV, FUND_EFF_DT, FUND_TERM_DT, NEW_BUS_TERM_DT, FUND_FAMILY, FUND_STRATEGY, REBALANCING_IND, RFEE_SUBSET_PTR, HARD_CLOSE_EFF_DATE, HARD_CLOSE_ORIGINATOR_CODE, HARD_CLOSE_MEMO_CODE, HARD_CLOSE_SUCCESSOR_FUND, LIQUIDATION_EFF_DATE, LIQUIDATION_ORIGINATOR_CODE, LIQUIDATION_MEMO_CODE, LIQUIDATION_SUCCESSOR_FUND )");
        sb.append(" VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ? )");
        return sb.toString();
    }
    
    public String prepareUpdateStmt(String schemaName)
    {
        StringBuffer sb = new StringBuffer();
        sb.append("UPDATE ").append(schemaName);
        sb.append(".T009X ");
        sb.append(" SET COMPANY_CODE = ?, FUND_NUMBER = ?, TAX_STATUS = ?, FUND_TYPE = ?, FUND_STATUS = ?, REGISTRATION_CODE = ?, ACCOUNT_CODE = ?, FUND_NAME = ?, FUND_NAME_ABBRV = ?, FUND_EFF_DT = ?, FUND_TERM_DT = ?, NEW_BUS_TERM_DT = ?, FUND_FAMILY = ?, FUND_STRATEGY = ?, REBALANCING_IND = ?, RFEE_SUBSET_PTR = ?, HARD_CLOSE_EFF_DATE = ?, HARD_CLOSE_ORIGINATOR_CODE = ?, HARD_CLOSE_MEMO_CODE = ?, HARD_CLOSE_SUCCESSOR_FUND = ?, LIQUIDATION_EFF_DATE = ?, LIQUIDATION_ORIGINATOR_CODE = ?, LIQUIDATION_MEMO_CODE = ?, LIQUIDATION_SUCCESSOR_FUND = ?");
        sb.append(" WHERE COMPANY_CODE = ? AND FUND_NUMBER = ?");
        return sb.toString();
    }
    
    public String prepareDeleteStmt(String schemaName)
    {
        StringBuffer sb = new StringBuffer();
        sb.append("DELETE FROM ").append(schemaName);
        sb.append(".T009X ");
        sb.append(" WHERE COMPANY_CODE = ? AND FUND_NUMBER = ?");
        return sb.toString();
    }
}
