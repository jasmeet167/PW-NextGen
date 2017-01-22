package com.csc.fsg.life.pw.web.io.descriptor.wma;

import com.csc.fsg.life.pw.web.io.*;

public class TXW1XDescriptor
    extends TableDescriptor
{
    private static final String pagingSql = "SELECT COMPANY_CODE, PRODUCT_PREFIX, TABLE_SUBSET, LINE_OF_BUSINESS, EFFECTIVE_DATE, TRANSACTION_CODE, MIN_TOTAL_DISTRIB, MAX_TOTAL_DISTRIB, MEMO_CODE, TAX_TYPE_IND, TAX_WITHD_IND, FLAT_WITHD_TAX_AMT, WITHD_TAX_PCT, SSN_NOT_VER_AMT, SSN_NOT_VER_PCT, INT_WITHD_TAX_PCT, PRODUCE_TAX_RPTS FROM ";
    
    public void initialize()
    {
        setRowClass(TXW1XRow.class);
        setTableName("TXW1X");
        setTableId("XW1");
        super.initialize();
    }
    
    public void initializeColumnDescriptors()
    {
        super.initializeColumnDescriptors();
        addColumnDescriptor(new ColumnDescriptor(this,"getCompanyCode","setCompanyCode","COMPANY_CODE,1,1,3,0,true|A,0,CHAR,1,null,null,null,null,null|T,0,CHAR,1,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getProductPrefix","setProductPrefix","PRODUCT_PREFIX,1,2,1,0,true|A,0,CHAR,1,null,null,null,null,null|T,0,CHAR,1,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getTableSubset","setTableSubset","TABLE_SUBSET,1,3,16,0,true|A,0,CHAR,1,null,null,null,null,null|T,0,CHAR,1,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getLineOfBusiness","setLineOfBusiness","LINE_OF_BUSINESS,1,4,3,0,true|A,0,CHAR,0,null,null,null,null,null|T,0,CHAR,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getEffectiveDate","setEffectiveDate","EFFECTIVE_DATE,91,5,10,0,true|A,0,DATE,0,null,null,null,null,null|T,0,DATE,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getTransactionCode","setTransactionCode","TRANSACTION_CODE,1,6,4,0,true|A,0,CHAR,0,null,null,null,null,null|T,0,CHAR,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getMinTotalDistrib","setMinTotalDistrib","MIN_TOTAL_DISTRIB,3,7,7,0,true|A,0,INTEGER,0,null,null,null,null,null|T,0,INTEGER,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getMaxTotalDistrib","setMaxTotalDistrib","MAX_TOTAL_DISTRIB,3,8,7,0,true|A,0,INTEGER,0,null,null,null,null,null|T,0,INTEGER,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getMemoCode","setMemoCode","MEMO_CODE,1,9,2,0,true|A,0,CHAR,4,null,null,null,null,null|T,0,CHAR,4,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getTaxTypeInd","setTaxTypeInd","TAX_TYPE_IND,1,10,1,0,false|A,0,CHAR,2,null,null,null,null,null|T,0,CHAR,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getTaxWithdInd","setTaxWithdInd","TAX_WITHD_IND,1,11,1,0,false|A,0,CHAR,2,null,null,null,null,null|T,0,CHAR,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getFlatWithdTaxAmt","setFlatWithdTaxAmt","FLAT_WITHD_TAX_AMT,3,12,7,0,false|A,0,INTEGER,0,null,null,null,null,null|T,0,INTEGER,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getWithdTaxPct","setWithdTaxPct","WITHD_TAX_PCT,3,13,7,4,false|A,0,DOUBLE,0,null,null,null,null,null|T,0,DOUBLE,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getSsnNotVerAmt","setSsnNotVerAmt","SSN_NOT_VER_AMT,3,14,7,0,false|A,0,INTEGER,0,null,null,null,null,null|T,0,INTEGER,2,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getSsnNotVerPct","setSsnNotVerPct","SSN_NOT_VER_PCT,3,15,7,4,false|A,0,DOUBLE,0,null,null,null,null,null|T,0,DOUBLE,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getIntWithdTaxPct","setIntWithdTaxPct","INT_WITHD_TAX_PCT,3,16,7,4,false|A,0,DOUBLE,2,null,null,null,null,null|T,0,DOUBLE,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getProduceTaxRpts","setProduceTaxRpts","PRODUCE_TAX_RPTS,1,17,1,0,false|A,0,CHAR,4,null,null,null,null,null|T,0,CHAR,4,null,null,null,null,null"));
    }
    
    public String getPagingSQL(String schemaName,boolean isSubsetMode,boolean isNext, boolean locator)
    {
        String pagingWhere = null;
        String order = null;
        if (isNext) {
            if (isSubsetMode)
                if (locator)
                    pagingWhere = ".TXW1X WHERE (COMPANY_CODE = :company_code) AND (PRODUCT_PREFIX = :product_prefix) AND (TABLE_SUBSET = :table_subset) AND ((LINE_OF_BUSINESS > :line_of_business) OR (LINE_OF_BUSINESS = :line_of_business AND EFFECTIVE_DATE > :effective_date) OR (LINE_OF_BUSINESS = :line_of_business AND EFFECTIVE_DATE = :effective_date AND TRANSACTION_CODE > :transaction_code) OR (LINE_OF_BUSINESS = :line_of_business AND EFFECTIVE_DATE = :effective_date AND TRANSACTION_CODE = :transaction_code AND MIN_TOTAL_DISTRIB > :min_total_distrib) OR (LINE_OF_BUSINESS = :line_of_business AND EFFECTIVE_DATE = :effective_date AND TRANSACTION_CODE = :transaction_code AND MIN_TOTAL_DISTRIB = :min_total_distrib AND MAX_TOTAL_DISTRIB > :max_total_distrib) OR (LINE_OF_BUSINESS = :line_of_business AND EFFECTIVE_DATE = :effective_date AND TRANSACTION_CODE = :transaction_code AND MIN_TOTAL_DISTRIB = :min_total_distrib AND MAX_TOTAL_DISTRIB = :max_total_distrib AND MEMO_CODE > :memo_code) OR (LINE_OF_BUSINESS = :line_of_business AND EFFECTIVE_DATE = :effective_date AND TRANSACTION_CODE = :transaction_code AND MIN_TOTAL_DISTRIB = :min_total_distrib AND MAX_TOTAL_DISTRIB = :max_total_distrib AND MEMO_CODE = :memo_code)) ";
                else 
                    pagingWhere = ".TXW1X WHERE (COMPANY_CODE = :company_code) AND (PRODUCT_PREFIX = :product_prefix) AND (TABLE_SUBSET = :table_subset) AND ((LINE_OF_BUSINESS > :line_of_business) OR (LINE_OF_BUSINESS = :line_of_business AND EFFECTIVE_DATE > :effective_date) OR (LINE_OF_BUSINESS = :line_of_business AND EFFECTIVE_DATE = :effective_date AND TRANSACTION_CODE > :transaction_code) OR (LINE_OF_BUSINESS = :line_of_business AND EFFECTIVE_DATE = :effective_date AND TRANSACTION_CODE = :transaction_code AND MIN_TOTAL_DISTRIB > :min_total_distrib) OR (LINE_OF_BUSINESS = :line_of_business AND EFFECTIVE_DATE = :effective_date AND TRANSACTION_CODE = :transaction_code AND MIN_TOTAL_DISTRIB = :min_total_distrib AND MAX_TOTAL_DISTRIB > :max_total_distrib) OR (LINE_OF_BUSINESS = :line_of_business AND EFFECTIVE_DATE = :effective_date AND TRANSACTION_CODE = :transaction_code AND MIN_TOTAL_DISTRIB = :min_total_distrib AND MAX_TOTAL_DISTRIB = :max_total_distrib AND MEMO_CODE > :memo_code)) ";
            else
                if (locator)
                    pagingWhere = ".TXW1X WHERE (COMPANY_CODE = :company_code) AND ((PRODUCT_PREFIX > :product_prefix) OR (PRODUCT_PREFIX = :product_prefix AND TABLE_SUBSET > :table_subset) OR (PRODUCT_PREFIX = :product_prefix AND TABLE_SUBSET = :table_subset AND LINE_OF_BUSINESS > :line_of_business) OR (PRODUCT_PREFIX = :product_prefix AND TABLE_SUBSET = :table_subset AND LINE_OF_BUSINESS = :line_of_business AND EFFECTIVE_DATE > :effective_date) OR (PRODUCT_PREFIX = :product_prefix AND TABLE_SUBSET = :table_subset AND LINE_OF_BUSINESS = :line_of_business AND EFFECTIVE_DATE = :effective_date AND TRANSACTION_CODE > :transaction_code) OR (PRODUCT_PREFIX = :product_prefix AND TABLE_SUBSET = :table_subset AND LINE_OF_BUSINESS = :line_of_business AND EFFECTIVE_DATE = :effective_date AND TRANSACTION_CODE = :transaction_code AND MIN_TOTAL_DISTRIB > :min_total_distrib) OR (PRODUCT_PREFIX = :product_prefix AND TABLE_SUBSET = :table_subset AND LINE_OF_BUSINESS = :line_of_business AND EFFECTIVE_DATE = :effective_date AND TRANSACTION_CODE = :transaction_code AND MIN_TOTAL_DISTRIB = :min_total_distrib AND MAX_TOTAL_DISTRIB > :max_total_distrib) OR (PRODUCT_PREFIX = :product_prefix AND TABLE_SUBSET = :table_subset AND LINE_OF_BUSINESS = :line_of_business AND EFFECTIVE_DATE = :effective_date AND TRANSACTION_CODE = :transaction_code AND MIN_TOTAL_DISTRIB = :min_total_distrib AND MAX_TOTAL_DISTRIB = :max_total_distrib AND MEMO_CODE > :memo_code) OR (PRODUCT_PREFIX = :product_prefix AND TABLE_SUBSET = :table_subset AND LINE_OF_BUSINESS = :line_of_business AND EFFECTIVE_DATE = :effective_date AND TRANSACTION_CODE = :transaction_code AND MIN_TOTAL_DISTRIB = :min_total_distrib AND MAX_TOTAL_DISTRIB = :max_total_distrib AND MEMO_CODE = :memo_code)) ";
                else
                    pagingWhere = ".TXW1X WHERE (COMPANY_CODE = :company_code) AND ((PRODUCT_PREFIX > :product_prefix) OR (PRODUCT_PREFIX = :product_prefix AND TABLE_SUBSET > :table_subset) OR (PRODUCT_PREFIX = :product_prefix AND TABLE_SUBSET = :table_subset AND LINE_OF_BUSINESS > :line_of_business) OR (PRODUCT_PREFIX = :product_prefix AND TABLE_SUBSET = :table_subset AND LINE_OF_BUSINESS = :line_of_business AND EFFECTIVE_DATE > :effective_date) OR (PRODUCT_PREFIX = :product_prefix AND TABLE_SUBSET = :table_subset AND LINE_OF_BUSINESS = :line_of_business AND EFFECTIVE_DATE = :effective_date AND TRANSACTION_CODE > :transaction_code) OR (PRODUCT_PREFIX = :product_prefix AND TABLE_SUBSET = :table_subset AND LINE_OF_BUSINESS = :line_of_business AND EFFECTIVE_DATE = :effective_date AND TRANSACTION_CODE = :transaction_code AND MIN_TOTAL_DISTRIB > :min_total_distrib) OR (PRODUCT_PREFIX = :product_prefix AND TABLE_SUBSET = :table_subset AND LINE_OF_BUSINESS = :line_of_business AND EFFECTIVE_DATE = :effective_date AND TRANSACTION_CODE = :transaction_code AND MIN_TOTAL_DISTRIB = :min_total_distrib AND MAX_TOTAL_DISTRIB > :max_total_distrib) OR (PRODUCT_PREFIX = :product_prefix AND TABLE_SUBSET = :table_subset AND LINE_OF_BUSINESS = :line_of_business AND EFFECTIVE_DATE = :effective_date AND TRANSACTION_CODE = :transaction_code AND MIN_TOTAL_DISTRIB = :min_total_distrib AND MAX_TOTAL_DISTRIB = :max_total_distrib AND MEMO_CODE > :memo_code)) ";
            order = " ORDER BY COMPANY_CODE, PRODUCT_PREFIX, TABLE_SUBSET, LINE_OF_BUSINESS, EFFECTIVE_DATE, TRANSACTION_CODE, MIN_TOTAL_DISTRIB, MAX_TOTAL_DISTRIB, MEMO_CODE";
        }
        else {
            if (isSubsetMode)
                pagingWhere = ".TXW1X WHERE (COMPANY_CODE = :company_code) AND (PRODUCT_PREFIX = :product_prefix) AND (TABLE_SUBSET = :table_subset) AND ((LINE_OF_BUSINESS < :line_of_business) OR (LINE_OF_BUSINESS = :line_of_business AND EFFECTIVE_DATE < :effective_date) OR (LINE_OF_BUSINESS = :line_of_business AND EFFECTIVE_DATE = :effective_date AND TRANSACTION_CODE < :transaction_code) OR (LINE_OF_BUSINESS = :line_of_business AND EFFECTIVE_DATE = :effective_date AND TRANSACTION_CODE = :transaction_code AND MIN_TOTAL_DISTRIB < :min_total_distrib) OR (LINE_OF_BUSINESS = :line_of_business AND EFFECTIVE_DATE = :effective_date AND TRANSACTION_CODE = :transaction_code AND MIN_TOTAL_DISTRIB = :min_total_distrib AND MAX_TOTAL_DISTRIB < :max_total_distrib) OR (LINE_OF_BUSINESS = :line_of_business AND EFFECTIVE_DATE = :effective_date AND TRANSACTION_CODE = :transaction_code AND MIN_TOTAL_DISTRIB = :min_total_distrib AND MAX_TOTAL_DISTRIB = :max_total_distrib AND MEMO_CODE < :memo_code)) ";
            else
                pagingWhere = ".TXW1X WHERE (COMPANY_CODE = :company_code) AND ((PRODUCT_PREFIX < :product_prefix) OR (PRODUCT_PREFIX = :product_prefix AND TABLE_SUBSET < :table_subset) OR (PRODUCT_PREFIX = :product_prefix AND TABLE_SUBSET = :table_subset AND LINE_OF_BUSINESS < :line_of_business) OR (PRODUCT_PREFIX = :product_prefix AND TABLE_SUBSET = :table_subset AND LINE_OF_BUSINESS = :line_of_business AND EFFECTIVE_DATE < :effective_date) OR (PRODUCT_PREFIX = :product_prefix AND TABLE_SUBSET = :table_subset AND LINE_OF_BUSINESS = :line_of_business AND EFFECTIVE_DATE = :effective_date AND TRANSACTION_CODE < :transaction_code) OR (PRODUCT_PREFIX = :product_prefix AND TABLE_SUBSET = :table_subset AND LINE_OF_BUSINESS = :line_of_business AND EFFECTIVE_DATE = :effective_date AND TRANSACTION_CODE = :transaction_code AND MIN_TOTAL_DISTRIB < :min_total_distrib) OR (PRODUCT_PREFIX = :product_prefix AND TABLE_SUBSET = :table_subset AND LINE_OF_BUSINESS = :line_of_business AND EFFECTIVE_DATE = :effective_date AND TRANSACTION_CODE = :transaction_code AND MIN_TOTAL_DISTRIB = :min_total_distrib AND MAX_TOTAL_DISTRIB < :max_total_distrib) OR (PRODUCT_PREFIX = :product_prefix AND TABLE_SUBSET = :table_subset AND LINE_OF_BUSINESS = :line_of_business AND EFFECTIVE_DATE = :effective_date AND TRANSACTION_CODE = :transaction_code AND MIN_TOTAL_DISTRIB = :min_total_distrib AND MAX_TOTAL_DISTRIB = :max_total_distrib AND MEMO_CODE < :memo_code)) ";
            order = " ORDER BY COMPANY_CODE DESC, PRODUCT_PREFIX DESC, TABLE_SUBSET DESC, LINE_OF_BUSINESS DESC, EFFECTIVE_DATE DESC, TRANSACTION_CODE DESC, MIN_TOTAL_DISTRIB DESC, MAX_TOTAL_DISTRIB DESC, MEMO_CODE DESC";
        }
        return pagingSql + schemaName + pagingWhere + order;
    }
    
    public String prepareInsertStmt(String schemaName) {
        StringBuffer sb = new StringBuffer();
        sb.append("INSERT INTO ").append(schemaName);
        sb.append(".TXW1X ( ");
        sb.append("COMPANY_CODE, PRODUCT_PREFIX, TABLE_SUBSET, LINE_OF_BUSINESS, EFFECTIVE_DATE, TRANSACTION_CODE, MIN_TOTAL_DISTRIB, MAX_TOTAL_DISTRIB, MEMO_CODE, TAX_TYPE_IND, TAX_WITHD_IND, FLAT_WITHD_TAX_AMT, WITHD_TAX_PCT, SSN_NOT_VER_AMT, SSN_NOT_VER_PCT, INT_WITHD_TAX_PCT, PRODUCE_TAX_RPTS )");
        sb.append(" VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ? )");
        return sb.toString();
    }
    
    public String prepareUpdateStmt(String schemaName)
    {
        StringBuffer sb = new StringBuffer();
        sb.append("UPDATE ").append(schemaName);
        sb.append(".TXW1X ");
        sb.append(" SET COMPANY_CODE = ?, PRODUCT_PREFIX = ?, TABLE_SUBSET = ?, LINE_OF_BUSINESS = ?, EFFECTIVE_DATE = ?, TRANSACTION_CODE = ?, MIN_TOTAL_DISTRIB = ?, MAX_TOTAL_DISTRIB = ?, MEMO_CODE = ?, TAX_TYPE_IND = ?, TAX_WITHD_IND = ?, FLAT_WITHD_TAX_AMT = ?, WITHD_TAX_PCT = ?, SSN_NOT_VER_AMT = ?, SSN_NOT_VER_PCT = ?, INT_WITHD_TAX_PCT = ?, PRODUCE_TAX_RPTS = ?");
        sb.append(" WHERE COMPANY_CODE = ? AND PRODUCT_PREFIX = ? AND TABLE_SUBSET = ? AND LINE_OF_BUSINESS = ? AND EFFECTIVE_DATE = ? AND TRANSACTION_CODE = ? AND MIN_TOTAL_DISTRIB = ? AND MAX_TOTAL_DISTRIB = ? AND MEMO_CODE = ?");
        return sb.toString();
    }
    
    public String prepareDeleteStmt(String schemaName)
    {
        StringBuffer sb = new StringBuffer();
        sb.append("DELETE FROM ").append(schemaName);
        sb.append(".TXW1X ");
        sb.append(" WHERE COMPANY_CODE = ? AND PRODUCT_PREFIX = ? AND TABLE_SUBSET = ? AND LINE_OF_BUSINESS = ? AND EFFECTIVE_DATE = ? AND TRANSACTION_CODE = ? AND MIN_TOTAL_DISTRIB = ? AND MAX_TOTAL_DISTRIB = ? AND MEMO_CODE = ?");
        return sb.toString();
    }
}
