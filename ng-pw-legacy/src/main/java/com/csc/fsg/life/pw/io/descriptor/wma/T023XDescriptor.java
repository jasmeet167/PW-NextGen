package com.csc.fsg.life.pw.io.descriptor.wma;

import com.csc.fsg.life.pw.io.*;

public class T023XDescriptor
    extends TableDescriptor
{
    private static final String pagingSql = "SELECT COMPANY_CODE, PRODUCT_PREFIX, TABLE_SUBSET, FUND_NUMBER, EFFECTIVE_DATE, MEMO_CODE, CST_RSK_CHRG_MTHD, WITHDRAWAL_METHOD, TRANSFER_METHOD, LN_ACCM_MTHD, LN_REPY_MTHD, ANNL_CHRG_MTHD, VALUE_ADJ_MTHD FROM ";
    
    public void initialize()
    {
        setRowClass(T023XRow.class);
        setTableName("T023X");
        setTableId("023");
        super.initialize();
    }
    
    public void initializeColumnDescriptors()
    {
        super.initializeColumnDescriptors();
        addColumnDescriptor(new ColumnDescriptor(this,"getCompanyCode","setCompanyCode","COMPANY_CODE,1,1,3,0,true|A,0,CHAR,1,null,null,null,null,null|U,0,CHAR,1,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getProductPrefix","setProductPrefix","PRODUCT_PREFIX,1,2,1,0,true|A,0,CHAR,1,null,null,null,null,null|U,0,CHAR,1,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getTableSubset","setTableSubset","TABLE_SUBSET,1,3,16,0,true|A,0,CHAR,1,null,null,null,null,null|U,0,CHAR,1,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getFundNumber","setFundNumber","FUND_NUMBER,3,4,8,0,true|A,0,INTEGER,0,null,null,null,null,null|U,0,INTEGER,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getEffectiveDate","setEffectiveDate","EFFECTIVE_DATE,91,5,10,0,true|A,0,DATE,0,null,null,null,null,null|U,0,DATE,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getMemoCode","setMemoCode","MEMO_CODE,1,6,2,0,true|A,0,CHAR,4,null,null,null,null,null|U,0,CHAR,2,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getCstRskChrgMthd","setCstRskChrgMthd","CST_RSK_CHRG_MTHD,1,7,4,0,false|A,0,CHAR,2,null,null,null,null,null|U,0,CHAR,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getWithdrawalMethod","setWithdrawalMethod","WITHDRAWAL_METHOD,1,8,4,0,false|A,0,CHAR,0,null,null,null,null,null|U,0,CHAR,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getTransferMethod","setTransferMethod","TRANSFER_METHOD,1,9,4,0,false|A,0,CHAR,0,null,null,null,null,null|U,0,CHAR,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getLnAccmMthd","setLnAccmMthd","LN_ACCM_MTHD,1,10,4,0,false|A,0,CHAR,0,null,null,null,null,null|U,0,CHAR,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getLnRepyMthd","setLnRepyMthd","LN_REPY_MTHD,1,11,4,0,false|A,0,CHAR,0,null,null,null,null,null|U,0,CHAR,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getAnnlChrgMthd","setAnnlChrgMthd","ANNL_CHRG_MTHD,1,12,4,0,false|A,0,CHAR,0,null,null,null,null,null|U,0,CHAR,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getValueAdjMthd","setValueAdjMthd","VALUE_ADJ_MTHD,1,13,4,0,false|A,0,CHAR,0,null,null,null,null,null|U,0,CHAR,0,null,null,null,null,null"));
    }
    
    public String getPagingSQL(String schemaName,boolean isSubsetMode,boolean isNext, boolean locator)
    {
        String pagingWhere = null;
        String order = null;
        if (isNext) {
            if (isSubsetMode)
                if (locator)
                    pagingWhere = ".T023X WHERE (COMPANY_CODE = :company_code) AND (PRODUCT_PREFIX = :product_prefix) AND (TABLE_SUBSET = :table_subset) AND ((FUND_NUMBER > :fund_number) OR (FUND_NUMBER = :fund_number AND EFFECTIVE_DATE > :effective_date) OR (FUND_NUMBER = :fund_number AND EFFECTIVE_DATE = :effective_date AND MEMO_CODE > :memo_code) OR (FUND_NUMBER = :fund_number AND EFFECTIVE_DATE = :effective_date AND MEMO_CODE = :memo_code)) ";
                else 
                    pagingWhere = ".T023X WHERE (COMPANY_CODE = :company_code) AND (PRODUCT_PREFIX = :product_prefix) AND (TABLE_SUBSET = :table_subset) AND ((FUND_NUMBER > :fund_number) OR (FUND_NUMBER = :fund_number AND EFFECTIVE_DATE > :effective_date) OR (FUND_NUMBER = :fund_number AND EFFECTIVE_DATE = :effective_date AND MEMO_CODE > :memo_code)) ";
            else
                if (locator)
                    pagingWhere = ".T023X WHERE (COMPANY_CODE = :company_code) AND ((PRODUCT_PREFIX > :product_prefix) OR (PRODUCT_PREFIX = :product_prefix AND TABLE_SUBSET > :table_subset) OR (PRODUCT_PREFIX = :product_prefix AND TABLE_SUBSET = :table_subset AND FUND_NUMBER > :fund_number) OR (PRODUCT_PREFIX = :product_prefix AND TABLE_SUBSET = :table_subset AND FUND_NUMBER = :fund_number AND EFFECTIVE_DATE > :effective_date) OR (PRODUCT_PREFIX = :product_prefix AND TABLE_SUBSET = :table_subset AND FUND_NUMBER = :fund_number AND EFFECTIVE_DATE = :effective_date AND MEMO_CODE > :memo_code) OR (PRODUCT_PREFIX = :product_prefix AND TABLE_SUBSET = :table_subset AND FUND_NUMBER = :fund_number AND EFFECTIVE_DATE = :effective_date AND MEMO_CODE = :memo_code)) ";
                else
                    pagingWhere = ".T023X WHERE (COMPANY_CODE = :company_code) AND ((PRODUCT_PREFIX > :product_prefix) OR (PRODUCT_PREFIX = :product_prefix AND TABLE_SUBSET > :table_subset) OR (PRODUCT_PREFIX = :product_prefix AND TABLE_SUBSET = :table_subset AND FUND_NUMBER > :fund_number) OR (PRODUCT_PREFIX = :product_prefix AND TABLE_SUBSET = :table_subset AND FUND_NUMBER = :fund_number AND EFFECTIVE_DATE > :effective_date) OR (PRODUCT_PREFIX = :product_prefix AND TABLE_SUBSET = :table_subset AND FUND_NUMBER = :fund_number AND EFFECTIVE_DATE = :effective_date AND MEMO_CODE > :memo_code)) ";
            order = " ORDER BY COMPANY_CODE, PRODUCT_PREFIX, TABLE_SUBSET, FUND_NUMBER, EFFECTIVE_DATE, MEMO_CODE";
        }
        else {
            if (isSubsetMode)
                pagingWhere = ".T023X WHERE (COMPANY_CODE = :company_code) AND (PRODUCT_PREFIX = :product_prefix) AND (TABLE_SUBSET = :table_subset) AND ((FUND_NUMBER < :fund_number) OR (FUND_NUMBER = :fund_number AND EFFECTIVE_DATE < :effective_date) OR (FUND_NUMBER = :fund_number AND EFFECTIVE_DATE = :effective_date AND MEMO_CODE < :memo_code)) ";
            else
                pagingWhere = ".T023X WHERE (COMPANY_CODE = :company_code) AND ((PRODUCT_PREFIX < :product_prefix) OR (PRODUCT_PREFIX = :product_prefix AND TABLE_SUBSET < :table_subset) OR (PRODUCT_PREFIX = :product_prefix AND TABLE_SUBSET = :table_subset AND FUND_NUMBER < :fund_number) OR (PRODUCT_PREFIX = :product_prefix AND TABLE_SUBSET = :table_subset AND FUND_NUMBER = :fund_number AND EFFECTIVE_DATE < :effective_date) OR (PRODUCT_PREFIX = :product_prefix AND TABLE_SUBSET = :table_subset AND FUND_NUMBER = :fund_number AND EFFECTIVE_DATE = :effective_date AND MEMO_CODE < :memo_code)) ";
            order = " ORDER BY COMPANY_CODE DESC, PRODUCT_PREFIX DESC, TABLE_SUBSET DESC, FUND_NUMBER DESC, EFFECTIVE_DATE DESC, MEMO_CODE DESC";
        }
        return pagingSql + schemaName + pagingWhere + order;
    }
    
    public String prepareInsertStmt(String schemaName) {
        StringBuffer sb = new StringBuffer();
        sb.append("INSERT INTO ").append(schemaName);
        sb.append(".T023X ( ");
        sb.append("COMPANY_CODE, PRODUCT_PREFIX, TABLE_SUBSET, FUND_NUMBER, EFFECTIVE_DATE, MEMO_CODE, CST_RSK_CHRG_MTHD, WITHDRAWAL_METHOD, TRANSFER_METHOD, LN_ACCM_MTHD, LN_REPY_MTHD, ANNL_CHRG_MTHD, VALUE_ADJ_MTHD )");
        sb.append(" VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ? )");
        return sb.toString();
    }
    
    public String prepareUpdateStmt(String schemaName)
    {
        StringBuffer sb = new StringBuffer();
        sb.append("UPDATE ").append(schemaName);
        sb.append(".T023X ");
        sb.append(" SET COMPANY_CODE = ?, PRODUCT_PREFIX = ?, TABLE_SUBSET = ?, FUND_NUMBER = ?, EFFECTIVE_DATE = ?, MEMO_CODE = ?, CST_RSK_CHRG_MTHD = ?, WITHDRAWAL_METHOD = ?, TRANSFER_METHOD = ?, LN_ACCM_MTHD = ?, LN_REPY_MTHD = ?, ANNL_CHRG_MTHD = ?, VALUE_ADJ_MTHD = ?");
        sb.append(" WHERE COMPANY_CODE = ? AND PRODUCT_PREFIX = ? AND TABLE_SUBSET = ? AND FUND_NUMBER = ? AND EFFECTIVE_DATE = ? AND MEMO_CODE = ?");
        return sb.toString();
    }
    
    public String prepareDeleteStmt(String schemaName)
    {
        StringBuffer sb = new StringBuffer();
        sb.append("DELETE FROM ").append(schemaName);
        sb.append(".T023X ");
        sb.append(" WHERE COMPANY_CODE = ? AND PRODUCT_PREFIX = ? AND TABLE_SUBSET = ? AND FUND_NUMBER = ? AND EFFECTIVE_DATE = ? AND MEMO_CODE = ?");
        return sb.toString();
    }
}
