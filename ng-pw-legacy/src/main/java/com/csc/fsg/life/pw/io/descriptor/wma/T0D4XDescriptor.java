package com.csc.fsg.life.pw.io.descriptor.wma;

import com.csc.fsg.life.pw.io.*;

public class T0D4XDescriptor
    extends TableDescriptor
{
    private static final String pagingSql = "SELECT COMPANY_CODE, PRODUCT_PREFIX, TABLE_SUBSET, EFFECTIVE_DATE, STATE_CODE, FUND_NUMBER, TRX_CODE, MEMO_CODE, MVA_IND, CALC_TYPE, RATE_BASIS_ADJ, RATE_METHOD, RATE_TYPE, MKT_VAL_FACT_ADJ, INCLUDE_CDSC, INCLUDE_FEE, INCLUDE_TAX, FREE_CORRIDOR, GUAR_SURR_VAL, MVA_FLOOR_RATE, RATES_TBL FROM ";
    
    public void initialize()
    {
        setRowClass(T0D4XRow.class);
        setTableName("T0D4X");
        setTableId("0D4");
        super.initialize();
    }
    
    public void initializeColumnDescriptors()
    {
        super.initializeColumnDescriptors();
        addColumnDescriptor(new ColumnDescriptor(this,"getCompanyCode","setCompanyCode","COMPANY_CODE,1,1,3,0,true|A,0,CHAR,1,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getProductPrefix","setProductPrefix","PRODUCT_PREFIX,1,2,1,0,true|A,0,CHAR,1,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getTableSubset","setTableSubset","TABLE_SUBSET,1,3,16,0,true|A,0,CHAR,1,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getEffectiveDate","setEffectiveDate","EFFECTIVE_DATE,91,4,10,0,true|A,0,DATE,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getStateCode","setStateCode","STATE_CODE,1,5,3,0,true|A,0,CHAR,4,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getFundNumber","setFundNumber","FUND_NUMBER,3,6,8,0,true|A,0,INTEGER,4,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getTrxCode","setTrxCode","TRX_CODE,1,7,4,0,true|A,0,CHAR,4,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getMemoCode","setMemoCode","MEMO_CODE,1,8,2,0,true|A,0,CHAR,4,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getMvaInd","setMvaInd","MVA_IND,1,9,1,0,false|A,0,CHAR,4,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getCalcType","setCalcType","CALC_TYPE,1,10,1,0,false|A,0,CHAR,4,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getRateBasisAdj","setRateBasisAdj","RATE_BASIS_ADJ,3,11,5,4,false|A,0,DOUBLE,4,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getRateMethod","setRateMethod","RATE_METHOD,1,12,1,0,false|A,0,CHAR,4,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getRateType","setRateType","RATE_TYPE,1,13,1,0,false|A,0,CHAR,4,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getMktValFactAdj","setMktValFactAdj","MKT_VAL_FACT_ADJ,3,14,4,3,false|A,0,DOUBLE,4,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getIncludeCdsc","setIncludeCdsc","INCLUDE_CDSC,1,15,1,0,false|A,0,CHAR,4,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getIncludeFee","setIncludeFee","INCLUDE_FEE,1,16,1,0,false|A,0,CHAR,4,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getIncludeTax","setIncludeTax","INCLUDE_TAX,1,17,1,0,false|A,0,CHAR,4,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getFreeCorridor","setFreeCorridor","FREE_CORRIDOR,1,18,1,0,false|A,0,CHAR,4,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getGuarSurrVal","setGuarSurrVal","GUAR_SURR_VAL,1,19,1,0,false|A,0,CHAR,4,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getMvaFloorRate","setMvaFloorRate","MVA_FLOOR_RATE,3,20,5,3,false|A,0,DOUBLE,4,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getRatesTbl","setRatesTbl","RATES_TBL,1,21,16,0,false|A,0,CHAR,4,0D5,null,null,null,null"));
    }
    
    public String getPagingSQL(String schemaName,boolean isSubsetMode,boolean isNext, boolean locator)
    {
        String pagingWhere = null;
        String order = null;
        if (isNext) {
            if (isSubsetMode)
                if (locator)
                    pagingWhere = ".T0D4X WHERE (COMPANY_CODE = :company_code) AND (PRODUCT_PREFIX = :product_prefix) AND (TABLE_SUBSET = :table_subset) AND ((EFFECTIVE_DATE > :effective_date) OR (EFFECTIVE_DATE = :effective_date AND STATE_CODE > :state_code) OR (EFFECTIVE_DATE = :effective_date AND STATE_CODE = :state_code AND FUND_NUMBER > :fund_number) OR (EFFECTIVE_DATE = :effective_date AND STATE_CODE = :state_code AND FUND_NUMBER = :fund_number AND TRX_CODE > :trx_code) OR (EFFECTIVE_DATE = :effective_date AND STATE_CODE = :state_code AND FUND_NUMBER = :fund_number AND TRX_CODE = :trx_code AND MEMO_CODE > :memo_code) OR (EFFECTIVE_DATE = :effective_date AND STATE_CODE = :state_code AND FUND_NUMBER = :fund_number AND TRX_CODE = :trx_code AND MEMO_CODE = :memo_code)) ";
                else 
                    pagingWhere = ".T0D4X WHERE (COMPANY_CODE = :company_code) AND (PRODUCT_PREFIX = :product_prefix) AND (TABLE_SUBSET = :table_subset) AND ((EFFECTIVE_DATE > :effective_date) OR (EFFECTIVE_DATE = :effective_date AND STATE_CODE > :state_code) OR (EFFECTIVE_DATE = :effective_date AND STATE_CODE = :state_code AND FUND_NUMBER > :fund_number) OR (EFFECTIVE_DATE = :effective_date AND STATE_CODE = :state_code AND FUND_NUMBER = :fund_number AND TRX_CODE > :trx_code) OR (EFFECTIVE_DATE = :effective_date AND STATE_CODE = :state_code AND FUND_NUMBER = :fund_number AND TRX_CODE = :trx_code AND MEMO_CODE > :memo_code)) ";
            else
                if (locator)
                    pagingWhere = ".T0D4X WHERE (COMPANY_CODE = :company_code) AND ((PRODUCT_PREFIX > :product_prefix) OR (PRODUCT_PREFIX = :product_prefix AND TABLE_SUBSET > :table_subset) OR (PRODUCT_PREFIX = :product_prefix AND TABLE_SUBSET = :table_subset AND EFFECTIVE_DATE > :effective_date) OR (PRODUCT_PREFIX = :product_prefix AND TABLE_SUBSET = :table_subset AND EFFECTIVE_DATE = :effective_date AND STATE_CODE > :state_code) OR (PRODUCT_PREFIX = :product_prefix AND TABLE_SUBSET = :table_subset AND EFFECTIVE_DATE = :effective_date AND STATE_CODE = :state_code AND FUND_NUMBER > :fund_number) OR (PRODUCT_PREFIX = :product_prefix AND TABLE_SUBSET = :table_subset AND EFFECTIVE_DATE = :effective_date AND STATE_CODE = :state_code AND FUND_NUMBER = :fund_number AND TRX_CODE > :trx_code) OR (PRODUCT_PREFIX = :product_prefix AND TABLE_SUBSET = :table_subset AND EFFECTIVE_DATE = :effective_date AND STATE_CODE = :state_code AND FUND_NUMBER = :fund_number AND TRX_CODE = :trx_code AND MEMO_CODE > :memo_code) OR (PRODUCT_PREFIX = :product_prefix AND TABLE_SUBSET = :table_subset AND EFFECTIVE_DATE = :effective_date AND STATE_CODE = :state_code AND FUND_NUMBER = :fund_number AND TRX_CODE = :trx_code AND MEMO_CODE = :memo_code)) ";
                else
                    pagingWhere = ".T0D4X WHERE (COMPANY_CODE = :company_code) AND ((PRODUCT_PREFIX > :product_prefix) OR (PRODUCT_PREFIX = :product_prefix AND TABLE_SUBSET > :table_subset) OR (PRODUCT_PREFIX = :product_prefix AND TABLE_SUBSET = :table_subset AND EFFECTIVE_DATE > :effective_date) OR (PRODUCT_PREFIX = :product_prefix AND TABLE_SUBSET = :table_subset AND EFFECTIVE_DATE = :effective_date AND STATE_CODE > :state_code) OR (PRODUCT_PREFIX = :product_prefix AND TABLE_SUBSET = :table_subset AND EFFECTIVE_DATE = :effective_date AND STATE_CODE = :state_code AND FUND_NUMBER > :fund_number) OR (PRODUCT_PREFIX = :product_prefix AND TABLE_SUBSET = :table_subset AND EFFECTIVE_DATE = :effective_date AND STATE_CODE = :state_code AND FUND_NUMBER = :fund_number AND TRX_CODE > :trx_code) OR (PRODUCT_PREFIX = :product_prefix AND TABLE_SUBSET = :table_subset AND EFFECTIVE_DATE = :effective_date AND STATE_CODE = :state_code AND FUND_NUMBER = :fund_number AND TRX_CODE = :trx_code AND MEMO_CODE > :memo_code)) ";
            order = " ORDER BY COMPANY_CODE, PRODUCT_PREFIX, TABLE_SUBSET, EFFECTIVE_DATE, STATE_CODE, FUND_NUMBER, TRX_CODE, MEMO_CODE";
        }
        else {
            if (isSubsetMode)
                pagingWhere = ".T0D4X WHERE (COMPANY_CODE = :company_code) AND (PRODUCT_PREFIX = :product_prefix) AND (TABLE_SUBSET = :table_subset) AND ((EFFECTIVE_DATE < :effective_date) OR (EFFECTIVE_DATE = :effective_date AND STATE_CODE < :state_code) OR (EFFECTIVE_DATE = :effective_date AND STATE_CODE = :state_code AND FUND_NUMBER < :fund_number) OR (EFFECTIVE_DATE = :effective_date AND STATE_CODE = :state_code AND FUND_NUMBER = :fund_number AND TRX_CODE < :trx_code) OR (EFFECTIVE_DATE = :effective_date AND STATE_CODE = :state_code AND FUND_NUMBER = :fund_number AND TRX_CODE = :trx_code AND MEMO_CODE < :memo_code)) ";
            else
                pagingWhere = ".T0D4X WHERE (COMPANY_CODE = :company_code) AND ((PRODUCT_PREFIX < :product_prefix) OR (PRODUCT_PREFIX = :product_prefix AND TABLE_SUBSET < :table_subset) OR (PRODUCT_PREFIX = :product_prefix AND TABLE_SUBSET = :table_subset AND EFFECTIVE_DATE < :effective_date) OR (PRODUCT_PREFIX = :product_prefix AND TABLE_SUBSET = :table_subset AND EFFECTIVE_DATE = :effective_date AND STATE_CODE < :state_code) OR (PRODUCT_PREFIX = :product_prefix AND TABLE_SUBSET = :table_subset AND EFFECTIVE_DATE = :effective_date AND STATE_CODE = :state_code AND FUND_NUMBER < :fund_number) OR (PRODUCT_PREFIX = :product_prefix AND TABLE_SUBSET = :table_subset AND EFFECTIVE_DATE = :effective_date AND STATE_CODE = :state_code AND FUND_NUMBER = :fund_number AND TRX_CODE < :trx_code) OR (PRODUCT_PREFIX = :product_prefix AND TABLE_SUBSET = :table_subset AND EFFECTIVE_DATE = :effective_date AND STATE_CODE = :state_code AND FUND_NUMBER = :fund_number AND TRX_CODE = :trx_code AND MEMO_CODE < :memo_code)) ";
            order = " ORDER BY COMPANY_CODE DESC, PRODUCT_PREFIX DESC, TABLE_SUBSET DESC, EFFECTIVE_DATE DESC, STATE_CODE DESC, FUND_NUMBER DESC, TRX_CODE DESC, MEMO_CODE DESC";
        }
        return pagingSql + schemaName + pagingWhere + order;
    }
    
    public String prepareInsertStmt(String schemaName) {
        StringBuffer sb = new StringBuffer();
        sb.append("INSERT INTO ").append(schemaName);
        sb.append(".T0D4X ( ");
        sb.append("COMPANY_CODE, PRODUCT_PREFIX, TABLE_SUBSET, EFFECTIVE_DATE, STATE_CODE, FUND_NUMBER, TRX_CODE, MEMO_CODE, MVA_IND, CALC_TYPE, RATE_BASIS_ADJ, RATE_METHOD, RATE_TYPE, MKT_VAL_FACT_ADJ, INCLUDE_CDSC, INCLUDE_FEE, INCLUDE_TAX, FREE_CORRIDOR, GUAR_SURR_VAL, MVA_FLOOR_RATE, RATES_TBL )");
        sb.append(" VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ? )");
        return sb.toString();
    }
    
    public String prepareUpdateStmt(String schemaName)
    {
        StringBuffer sb = new StringBuffer();
        sb.append("UPDATE ").append(schemaName);
        sb.append(".T0D4X ");
        sb.append(" SET COMPANY_CODE = ?, PRODUCT_PREFIX = ?, TABLE_SUBSET = ?, EFFECTIVE_DATE = ?, STATE_CODE = ?, FUND_NUMBER = ?, TRX_CODE = ?, MEMO_CODE = ?, MVA_IND = ?, CALC_TYPE = ?, RATE_BASIS_ADJ = ?, RATE_METHOD = ?, RATE_TYPE = ?, MKT_VAL_FACT_ADJ = ?, INCLUDE_CDSC = ?, INCLUDE_FEE = ?, INCLUDE_TAX = ?, FREE_CORRIDOR = ?, GUAR_SURR_VAL = ?, MVA_FLOOR_RATE = ?, RATES_TBL = ?");
        sb.append(" WHERE COMPANY_CODE = ? AND PRODUCT_PREFIX = ? AND TABLE_SUBSET = ? AND EFFECTIVE_DATE = ? AND STATE_CODE = ? AND FUND_NUMBER = ? AND TRX_CODE = ? AND MEMO_CODE = ?");
        return sb.toString();
    }
    
    public String prepareDeleteStmt(String schemaName)
    {
        StringBuffer sb = new StringBuffer();
        sb.append("DELETE FROM ").append(schemaName);
        sb.append(".T0D4X ");
        sb.append(" WHERE COMPANY_CODE = ? AND PRODUCT_PREFIX = ? AND TABLE_SUBSET = ? AND EFFECTIVE_DATE = ? AND STATE_CODE = ? AND FUND_NUMBER = ? AND TRX_CODE = ? AND MEMO_CODE = ?");
        return sb.toString();
    }
}
