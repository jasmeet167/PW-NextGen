package com.csc.fsg.life.pw.io.descriptor.wma;

import com.csc.fsg.life.pw.io.*;

public class TTC7TDescriptor
    extends TableDescriptor
{
    private static final String pagingSql = "SELECT COMPANY_CODE, TABLE_SUBSET, EFFECTIVE_DATE, PRTY_USAGE_TRX_CD, COMBINE_ALL_PRTY, DIV_PREM_AMT_UN_PR, BASE_POL_CASHVALPR, ACCUM_DIV_PRTY, PUA_DIV_PRTY, PUAR_PUA_CASHVALPR, FLEX_PUA_CASHVALPR, FIFO_LIFO_PRTY_CD, LOAN_ALL_OVER_RIDE, BASE_PUA_CV_BY_DIV, PUAR_PUA_CV_BY_DIV, FLEX_PUA_CV_BY_DIV FROM ";
    
    public void initialize()
    {
        setRowClass(TTC7TRow.class);
        setTableName("TTC7T");
        setTableId("TC7");
        super.initialize();
    }
    
    public void initializeColumnDescriptors()
    {
        super.initializeColumnDescriptors();
        addColumnDescriptor(new ColumnDescriptor(this,"getCompanyCode","setCompanyCode","COMPANY_CODE,1,1,3,0,true|,0,CHAR,1,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getTableSubset","setTableSubset","TABLE_SUBSET,1,2,16,0,true|,0,CHAR,1,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getEffectiveDate","setEffectiveDate","EFFECTIVE_DATE,91,3,10,0,true|,0,DATE,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getPrtyUsageTrxCd","setPrtyUsageTrxCd","PRTY_USAGE_TRX_CD,1,4,4,0,true|,0,CHAR,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getCombineAllPrty","setCombineAllPrty","COMBINE_ALL_PRTY,1,5,1,0,false|,0,CHAR,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getDivPremAmtUnPr","setDivPremAmtUnPr","DIV_PREM_AMT_UN_PR,1,6,1,0,false|,0,CHAR,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getBasePolCashvalpr","setBasePolCashvalpr","BASE_POL_CASHVALPR,1,7,1,0,false|,0,CHAR,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getAccumDivPrty","setAccumDivPrty","ACCUM_DIV_PRTY,1,8,1,0,false|,0,CHAR,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getPuaDivPrty","setPuaDivPrty","PUA_DIV_PRTY,1,9,1,0,false|,0,CHAR,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getPuarPuaCashvalpr","setPuarPuaCashvalpr","PUAR_PUA_CASHVALPR,1,10,1,0,false|,0,CHAR,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getFlexPuaCashvalpr","setFlexPuaCashvalpr","FLEX_PUA_CASHVALPR,1,11,1,0,false|,0,CHAR,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getFifoLifoPrtyCd","setFifoLifoPrtyCd","FIFO_LIFO_PRTY_CD,1,12,1,0,false|,0,CHAR,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getLoanAllOverRide","setLoanAllOverRide","LOAN_ALL_OVER_RIDE,1,13,1,0,false|,0,CHAR,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getBasePuaCvByDiv","setBasePuaCvByDiv","BASE_PUA_CV_BY_DIV,1,14,1,0,false|,0,CHAR,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getPuarPuaCvByDiv","setPuarPuaCvByDiv","PUAR_PUA_CV_BY_DIV,1,15,1,0,false|,0,CHAR,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getFlexPuaCvByDiv","setFlexPuaCvByDiv","FLEX_PUA_CV_BY_DIV,1,16,1,0,false|,0,CHAR,0,null,null,null,null,null"));
    }
    
    public String getPagingSQL(String schemaName,boolean isSubsetMode,boolean isNext, boolean locator)
    {
        String pagingWhere = null;
        String order = null;
        if (isNext) {
            if (isSubsetMode)
                if (locator)
                    pagingWhere = ".TTC7T WHERE (COMPANY_CODE = :company_code) AND (TABLE_SUBSET = :table_subset) AND ((EFFECTIVE_DATE > :effective_date) OR (EFFECTIVE_DATE = :effective_date AND PRTY_USAGE_TRX_CD > :prty_usage_trx_cd) OR (EFFECTIVE_DATE = :effective_date AND PRTY_USAGE_TRX_CD = :prty_usage_trx_cd)) ";
                else 
                    pagingWhere = ".TTC7T WHERE (COMPANY_CODE = :company_code) AND (TABLE_SUBSET = :table_subset) AND ((EFFECTIVE_DATE > :effective_date) OR (EFFECTIVE_DATE = :effective_date AND PRTY_USAGE_TRX_CD > :prty_usage_trx_cd)) ";
            else
                if (locator)
                    pagingWhere = ".TTC7T WHERE (COMPANY_CODE = :company_code) AND ((TABLE_SUBSET > :table_subset) OR (TABLE_SUBSET = :table_subset AND EFFECTIVE_DATE > :effective_date) OR (TABLE_SUBSET = :table_subset AND EFFECTIVE_DATE = :effective_date AND PRTY_USAGE_TRX_CD > :prty_usage_trx_cd) OR (TABLE_SUBSET = :table_subset AND EFFECTIVE_DATE = :effective_date AND PRTY_USAGE_TRX_CD = :prty_usage_trx_cd)) ";
                else
                    pagingWhere = ".TTC7T WHERE (COMPANY_CODE = :company_code) AND ((TABLE_SUBSET > :table_subset) OR (TABLE_SUBSET = :table_subset AND EFFECTIVE_DATE > :effective_date) OR (TABLE_SUBSET = :table_subset AND EFFECTIVE_DATE = :effective_date AND PRTY_USAGE_TRX_CD > :prty_usage_trx_cd)) ";
            order = " ORDER BY COMPANY_CODE, TABLE_SUBSET, EFFECTIVE_DATE, PRTY_USAGE_TRX_CD";
        }
        else {
            if (isSubsetMode)
                pagingWhere = ".TTC7T WHERE (COMPANY_CODE = :company_code) AND (TABLE_SUBSET = :table_subset) AND ((EFFECTIVE_DATE < :effective_date) OR (EFFECTIVE_DATE = :effective_date AND PRTY_USAGE_TRX_CD < :prty_usage_trx_cd)) ";
            else
                pagingWhere = ".TTC7T WHERE (COMPANY_CODE = :company_code) AND ((TABLE_SUBSET < :table_subset) OR (TABLE_SUBSET = :table_subset AND EFFECTIVE_DATE < :effective_date) OR (TABLE_SUBSET = :table_subset AND EFFECTIVE_DATE = :effective_date AND PRTY_USAGE_TRX_CD < :prty_usage_trx_cd)) ";
            order = " ORDER BY COMPANY_CODE DESC, TABLE_SUBSET DESC, EFFECTIVE_DATE DESC, PRTY_USAGE_TRX_CD DESC";
        }
        return pagingSql + schemaName + pagingWhere + order;
    }
    
    public String prepareInsertStmt(String schemaName) {
        StringBuffer sb = new StringBuffer();
        sb.append("INSERT INTO ").append(schemaName);
        sb.append(".TTC7T ( ");
        sb.append("COMPANY_CODE, TABLE_SUBSET, EFFECTIVE_DATE, PRTY_USAGE_TRX_CD, COMBINE_ALL_PRTY, DIV_PREM_AMT_UN_PR, BASE_POL_CASHVALPR, ACCUM_DIV_PRTY, PUA_DIV_PRTY, PUAR_PUA_CASHVALPR, FLEX_PUA_CASHVALPR, FIFO_LIFO_PRTY_CD, LOAN_ALL_OVER_RIDE, BASE_PUA_CV_BY_DIV, PUAR_PUA_CV_BY_DIV, FLEX_PUA_CV_BY_DIV )");
        sb.append(" VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ? )");
        return sb.toString();
    }
    
    public String prepareUpdateStmt(String schemaName)
    {
        StringBuffer sb = new StringBuffer();
        sb.append("UPDATE ").append(schemaName);
        sb.append(".TTC7T ");
        sb.append(" SET COMPANY_CODE = ?, TABLE_SUBSET = ?, EFFECTIVE_DATE = ?, PRTY_USAGE_TRX_CD = ?, COMBINE_ALL_PRTY = ?, DIV_PREM_AMT_UN_PR = ?, BASE_POL_CASHVALPR = ?, ACCUM_DIV_PRTY = ?, PUA_DIV_PRTY = ?, PUAR_PUA_CASHVALPR = ?, FLEX_PUA_CASHVALPR = ?, FIFO_LIFO_PRTY_CD = ?, LOAN_ALL_OVER_RIDE = ?, BASE_PUA_CV_BY_DIV = ?, PUAR_PUA_CV_BY_DIV = ?, FLEX_PUA_CV_BY_DIV = ?");
        sb.append(" WHERE COMPANY_CODE = ? AND TABLE_SUBSET = ? AND EFFECTIVE_DATE = ? AND PRTY_USAGE_TRX_CD = ?");
        return sb.toString();
    }
    
    public String prepareDeleteStmt(String schemaName)
    {
        StringBuffer sb = new StringBuffer();
        sb.append("DELETE FROM ").append(schemaName);
        sb.append(".TTC7T ");
        sb.append(" WHERE COMPANY_CODE = ? AND TABLE_SUBSET = ? AND EFFECTIVE_DATE = ? AND PRTY_USAGE_TRX_CD = ?");
        return sb.toString();
    }
}
