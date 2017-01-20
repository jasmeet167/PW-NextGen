package com.csc.fsg.life.pw.io.descriptor.wma;

import com.csc.fsg.life.pw.io.*;

public class TW04XDescriptor
    extends TableDescriptor
{
    private static final String pagingSql = "SELECT COMPANY_CODE, PRODUCT_PREFIX, TABLE_SUBSET, LOB_CODE, EFFECTIVE_DATE, MEMO_CODE, PLAN_DESC, MIN_MODAL_BEN, FUND_TRAN_IND, NUMBER_TRNFR, NUMBER_FREE_TRNFR, RESTRICT_AIR, ANN_STMT_TYPE, MRD_IND, MRD_NOTE_DAYS, GUAR_FLOOR_IND, ABL_IND, DURATION_RATES_INDICATOR FROM ";
    
    public void initialize()
    {
        setRowClass(TW04XRow.class);
        setTableName("TW04X");
        setTableId("W04");
        super.initialize();
    }
    
    public void initializeColumnDescriptors()
    {
        super.initializeColumnDescriptors();
        addColumnDescriptor(new ColumnDescriptor(this,"getCompanyCode","setCompanyCode","COMPANY_CODE,1,1,3,0,true|A,0,CHAR,1,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getProductPrefix","setProductPrefix","PRODUCT_PREFIX,1,2,1,0,true|A,0,CHAR,1,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getTableSubset","setTableSubset","TABLE_SUBSET,1,3,16,0,true|A,0,CHAR,1,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getLobCode","setLobCode","LOB_CODE,1,4,3,0,true|A,0,CHAR,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getEffectiveDate","setEffectiveDate","EFFECTIVE_DATE,91,5,10,0,true|A,0,DATE,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getMemoCode","setMemoCode","MEMO_CODE,1,6,2,0,true|A,0,CHAR,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getPlanDesc","setPlanDesc","PLAN_DESC,1,7,20,0,false|A,0,CHAR,4,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getMinModalBen","setMinModalBen","MIN_MODAL_BEN,3,8,11,2,false|A,0,DOUBLE,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getFundTranInd","setFundTranInd","FUND_TRAN_IND,1,9,1,0,false|A,0,CHAR,4,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getNumberTrnfr","setNumberTrnfr","NUMBER_TRNFR,3,10,3,0,false|A,0,INTEGER,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getNumberFreeTrnfr","setNumberFreeTrnfr","NUMBER_FREE_TRNFR,3,11,3,0,false|A,0,INTEGER,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getRestrictAir","setRestrictAir","RESTRICT_AIR,1,12,1,0,false|A,0,CHAR,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getAnnStmtType","setAnnStmtType","ANN_STMT_TYPE,1,13,1,0,false|A,0,CHAR,4,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getMrdInd","setMrdInd","MRD_IND,1,14,1,0,false|A,0,CHAR,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getMrdNoteDays","setMrdNoteDays","MRD_NOTE_DAYS,3,15,3,0,false|A,0,INTEGER,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getGuarFloorInd","setGuarFloorInd","GUAR_FLOOR_IND,1,16,1,0,false|A,0,CHAR,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getAblInd","setAblInd","ABL_IND,1,17,1,0,false|A,0,CHAR,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getDurationRatesIndicator","setDurationRatesIndicator","DURATION_RATES_INDICATOR,1,18,1,0,false|A,0,CHAR,0,null,null,null,null,null"));
    }
    
    public String getPagingSQL(String schemaName,boolean isSubsetMode,boolean isNext, boolean locator)
    {
        String pagingWhere = null;
        String order = null;
        if (isNext) {
            if (isSubsetMode)
                if (locator)
                    pagingWhere = ".TW04X WHERE (COMPANY_CODE = :company_code) AND (PRODUCT_PREFIX = :product_prefix) AND (TABLE_SUBSET = :table_subset) AND ((LOB_CODE > :lob_code) OR (LOB_CODE = :lob_code AND EFFECTIVE_DATE > :effective_date) OR (LOB_CODE = :lob_code AND EFFECTIVE_DATE = :effective_date AND MEMO_CODE > :memo_code) OR (LOB_CODE = :lob_code AND EFFECTIVE_DATE = :effective_date AND MEMO_CODE = :memo_code)) ";
                else 
                    pagingWhere = ".TW04X WHERE (COMPANY_CODE = :company_code) AND (PRODUCT_PREFIX = :product_prefix) AND (TABLE_SUBSET = :table_subset) AND ((LOB_CODE > :lob_code) OR (LOB_CODE = :lob_code AND EFFECTIVE_DATE > :effective_date) OR (LOB_CODE = :lob_code AND EFFECTIVE_DATE = :effective_date AND MEMO_CODE > :memo_code)) ";
            else
                if (locator)
                    pagingWhere = ".TW04X WHERE (COMPANY_CODE = :company_code) AND ((PRODUCT_PREFIX > :product_prefix) OR (PRODUCT_PREFIX = :product_prefix AND TABLE_SUBSET > :table_subset) OR (PRODUCT_PREFIX = :product_prefix AND TABLE_SUBSET = :table_subset AND LOB_CODE > :lob_code) OR (PRODUCT_PREFIX = :product_prefix AND TABLE_SUBSET = :table_subset AND LOB_CODE = :lob_code AND EFFECTIVE_DATE > :effective_date) OR (PRODUCT_PREFIX = :product_prefix AND TABLE_SUBSET = :table_subset AND LOB_CODE = :lob_code AND EFFECTIVE_DATE = :effective_date AND MEMO_CODE > :memo_code) OR (PRODUCT_PREFIX = :product_prefix AND TABLE_SUBSET = :table_subset AND LOB_CODE = :lob_code AND EFFECTIVE_DATE = :effective_date AND MEMO_CODE = :memo_code)) ";
                else
                    pagingWhere = ".TW04X WHERE (COMPANY_CODE = :company_code) AND ((PRODUCT_PREFIX > :product_prefix) OR (PRODUCT_PREFIX = :product_prefix AND TABLE_SUBSET > :table_subset) OR (PRODUCT_PREFIX = :product_prefix AND TABLE_SUBSET = :table_subset AND LOB_CODE > :lob_code) OR (PRODUCT_PREFIX = :product_prefix AND TABLE_SUBSET = :table_subset AND LOB_CODE = :lob_code AND EFFECTIVE_DATE > :effective_date) OR (PRODUCT_PREFIX = :product_prefix AND TABLE_SUBSET = :table_subset AND LOB_CODE = :lob_code AND EFFECTIVE_DATE = :effective_date AND MEMO_CODE > :memo_code)) ";
            order = " ORDER BY COMPANY_CODE, PRODUCT_PREFIX, TABLE_SUBSET, LOB_CODE, EFFECTIVE_DATE, MEMO_CODE";
        }
        else {
            if (isSubsetMode)
                pagingWhere = ".TW04X WHERE (COMPANY_CODE = :company_code) AND (PRODUCT_PREFIX = :product_prefix) AND (TABLE_SUBSET = :table_subset) AND ((LOB_CODE < :lob_code) OR (LOB_CODE = :lob_code AND EFFECTIVE_DATE < :effective_date) OR (LOB_CODE = :lob_code AND EFFECTIVE_DATE = :effective_date AND MEMO_CODE < :memo_code)) ";
            else
                pagingWhere = ".TW04X WHERE (COMPANY_CODE = :company_code) AND ((PRODUCT_PREFIX < :product_prefix) OR (PRODUCT_PREFIX = :product_prefix AND TABLE_SUBSET < :table_subset) OR (PRODUCT_PREFIX = :product_prefix AND TABLE_SUBSET = :table_subset AND LOB_CODE < :lob_code) OR (PRODUCT_PREFIX = :product_prefix AND TABLE_SUBSET = :table_subset AND LOB_CODE = :lob_code AND EFFECTIVE_DATE < :effective_date) OR (PRODUCT_PREFIX = :product_prefix AND TABLE_SUBSET = :table_subset AND LOB_CODE = :lob_code AND EFFECTIVE_DATE = :effective_date AND MEMO_CODE < :memo_code)) ";
            order = " ORDER BY COMPANY_CODE DESC, PRODUCT_PREFIX DESC, TABLE_SUBSET DESC, LOB_CODE DESC, EFFECTIVE_DATE DESC, MEMO_CODE DESC";
        }
        return pagingSql + schemaName + pagingWhere + order;
    }
    
    public String prepareInsertStmt(String schemaName) {
        StringBuffer sb = new StringBuffer();
        sb.append("INSERT INTO ").append(schemaName);
        sb.append(".TW04X ( ");
        sb.append("COMPANY_CODE, PRODUCT_PREFIX, TABLE_SUBSET, LOB_CODE, EFFECTIVE_DATE, MEMO_CODE, PLAN_DESC, MIN_MODAL_BEN, FUND_TRAN_IND, NUMBER_TRNFR, NUMBER_FREE_TRNFR, RESTRICT_AIR, ANN_STMT_TYPE, MRD_IND, MRD_NOTE_DAYS, GUAR_FLOOR_IND, ABL_IND, DURATION_RATES_INDICATOR )");
        sb.append(" VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ? )");
        return sb.toString();
    }
    
    public String prepareUpdateStmt(String schemaName)
    {
        StringBuffer sb = new StringBuffer();
        sb.append("UPDATE ").append(schemaName);
        sb.append(".TW04X ");
        sb.append(" SET COMPANY_CODE = ?, PRODUCT_PREFIX = ?, TABLE_SUBSET = ?, LOB_CODE = ?, EFFECTIVE_DATE = ?, MEMO_CODE = ?, PLAN_DESC = ?, MIN_MODAL_BEN = ?, FUND_TRAN_IND = ?, NUMBER_TRNFR = ?, NUMBER_FREE_TRNFR = ?, RESTRICT_AIR = ?, ANN_STMT_TYPE = ?, MRD_IND = ?, MRD_NOTE_DAYS = ?, GUAR_FLOOR_IND = ?, ABL_IND = ?, DURATION_RATES_INDICATOR = ?");
        sb.append(" WHERE COMPANY_CODE = ? AND PRODUCT_PREFIX = ? AND TABLE_SUBSET = ? AND LOB_CODE = ? AND EFFECTIVE_DATE = ? AND MEMO_CODE = ?");
        return sb.toString();
    }
    
    public String prepareDeleteStmt(String schemaName)
    {
        StringBuffer sb = new StringBuffer();
        sb.append("DELETE FROM ").append(schemaName);
        sb.append(".TW04X ");
        sb.append(" WHERE COMPANY_CODE = ? AND PRODUCT_PREFIX = ? AND TABLE_SUBSET = ? AND LOB_CODE = ? AND EFFECTIVE_DATE = ? AND MEMO_CODE = ?");
        return sb.toString();
    }
}
