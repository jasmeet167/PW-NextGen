package com.csc.fsg.life.pw.io.descriptor.wma;

import com.csc.fsg.life.pw.io.*;

public class T128XDescriptor
    extends TableDescriptor
{
    private static final String pagingSql = "SELECT COMPANY_CODE, PRODUCT_PREFIX, TABLE_SUBSET, EFFECTIVE_DATE, LOB_CODE, MEMO_CODE, MIN_INIT_PREM_AMOUNT, MIN_SUB_PREM_AMOUNT, MAX_PREM_AMOUNT, OWNER_ISSUE_AGE_MIN, CO_OWNER_ISSUE_AGE_MIN, ANNT_ISSUE_AGE_MIN, JNT_CO_ANNT_ISSUE_AGE_MIN, OWNER_ISSUE_AGE_MAX, CO_OWNER_ISSUE_AGE_MAX, ANNT_ISSUE_AGE_MAX, JNT_CO_ANNT_ISSUE_AGE_MAX, ANNT_COMMENCE_MAX_AGE, JNT_ANNT_COMMENCE_MAX_AGE, YEAR_INDICATOR, MAXIMUM_YEAR_PYMT, LIFETIME_MAX_AMOUNT, SUCC_ANNT_ISSUE_AGE_MIN, SUCC_ANNT_ISSUE_AGE_MAX, QLAC_MAX_DISB_AGE FROM ";
    
    public void initialize()
    {
        setRowClass(T128XRow.class);
        setTableName("T128X");
        setTableId("128");
        super.initialize();
    }
    
    public void initializeColumnDescriptors()
    {
        super.initializeColumnDescriptors();
        addColumnDescriptor(new ColumnDescriptor(this,"getCompanyCode","setCompanyCode","COMPANY_CODE,1,1,3,0,true|A,0,CHAR,1,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getProductPrefix","setProductPrefix","PRODUCT_PREFIX,1,2,1,0,true|A,0,CHAR,1,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getTableSubset","setTableSubset","TABLE_SUBSET,1,3,16,0,true|A,0,CHAR,1,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getEffectiveDate","setEffectiveDate","EFFECTIVE_DATE,91,4,10,0,true|A,0,DATE,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getLobCode","setLobCode","LOB_CODE,1,5,3,0,true|A,0,CHAR,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getMemoCode","setMemoCode","MEMO_CODE,1,6,2,0,true|A,0,CHAR,4,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getMinInitPremAmount","setMinInitPremAmount","MIN_INIT_PREM_AMOUNT,3,7,11,2,false|A,0,DOUBLE,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getMinSubPremAmount","setMinSubPremAmount","MIN_SUB_PREM_AMOUNT,3,8,11,2,false|A,0,DOUBLE,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getMaxPremAmount","setMaxPremAmount","MAX_PREM_AMOUNT,3,9,11,2,false|A,0,DOUBLE,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getOwnerIssueAgeMin","setOwnerIssueAgeMin","OWNER_ISSUE_AGE_MIN,5,10,3,0,false|A,0,INTEGER,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getCoOwnerIssueAgeMin","setCoOwnerIssueAgeMin","CO_OWNER_ISSUE_AGE_MIN,5,11,3,0,false|A,0,INTEGER,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getAnntIssueAgeMin","setAnntIssueAgeMin","ANNT_ISSUE_AGE_MIN,5,12,3,0,false|A,0,INTEGER,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getJntCoAnntIssueAgeMin","setJntCoAnntIssueAgeMin","JNT_CO_ANNT_ISSUE_AGE_MIN,5,13,3,0,false|A,0,INTEGER,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getOwnerIssueAgeMax","setOwnerIssueAgeMax","OWNER_ISSUE_AGE_MAX,5,14,3,0,false|A,0,INTEGER,4,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getCoOwnerIssueAgeMax","setCoOwnerIssueAgeMax","CO_OWNER_ISSUE_AGE_MAX,5,15,3,0,false|A,0,INTEGER,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getAnntIssueAgeMax","setAnntIssueAgeMax","ANNT_ISSUE_AGE_MAX,5,16,3,0,false|A,0,INTEGER,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getJntCoAnntIssueAgeMax","setJntCoAnntIssueAgeMax","JNT_CO_ANNT_ISSUE_AGE_MAX,5,17,3,0,false|A,0,INTEGER,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getAnntCommenceMaxAge","setAnntCommenceMaxAge","ANNT_COMMENCE_MAX_AGE,5,18,3,0,false|A,0,INTEGER,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getJntAnntCommenceMaxAge","setJntAnntCommenceMaxAge","JNT_ANNT_COMMENCE_MAX_AGE,5,19,3,0,false|A,0,INTEGER,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getYearIndicator","setYearIndicator","YEAR_INDICATOR,1,20,1,0,false|A,0,CHAR,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getMaximumYearPymt","setMaximumYearPymt","MAXIMUM_YEAR_PYMT,3,21,11,2,false|A,0,DOUBLE,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getLifetimeMaxAmount","setLifetimeMaxAmount","LIFETIME_MAX_AMOUNT,3,22,11,2,false|A,0,DOUBLE,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getSuccAnntIssueAgeMin","setSuccAnntIssueAgeMin","SUCC_ANNT_ISSUE_AGE_MIN,5,23,3,0,false|A,0,INTEGER,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getSuccAnntIssueAgeMax","setSuccAnntIssueAgeMax","SUCC_ANNT_ISSUE_AGE_MAX,5,24,3,0,false|A,0,INTEGER,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getQlacMaxDisbAge","setQlacMaxDisbAge","QLAC_MAX_DISB_AGE,5,25,3,0,false|A,0,INTEGER,0,null,null,null,null,null"));
    }
    
    public String getPagingSQL(String schemaName,boolean isSubsetMode,boolean isNext, boolean locator)
    {
        String pagingWhere = null;
        String order = null;
        if (isNext) {
            if (isSubsetMode)
                if (locator)
                    pagingWhere = ".T128X WHERE (COMPANY_CODE = :company_code) AND (PRODUCT_PREFIX = :product_prefix) AND (TABLE_SUBSET = :table_subset) AND ((EFFECTIVE_DATE > :effective_date) OR (EFFECTIVE_DATE = :effective_date AND LOB_CODE > :lob_code) OR (EFFECTIVE_DATE = :effective_date AND LOB_CODE = :lob_code AND MEMO_CODE > :memo_code) OR (EFFECTIVE_DATE = :effective_date AND LOB_CODE = :lob_code AND MEMO_CODE = :memo_code)) ";
                else 
                    pagingWhere = ".T128X WHERE (COMPANY_CODE = :company_code) AND (PRODUCT_PREFIX = :product_prefix) AND (TABLE_SUBSET = :table_subset) AND ((EFFECTIVE_DATE > :effective_date) OR (EFFECTIVE_DATE = :effective_date AND LOB_CODE > :lob_code) OR (EFFECTIVE_DATE = :effective_date AND LOB_CODE = :lob_code AND MEMO_CODE > :memo_code)) ";
            else
                if (locator)
                    pagingWhere = ".T128X WHERE (COMPANY_CODE = :company_code) AND ((PRODUCT_PREFIX > :product_prefix) OR (PRODUCT_PREFIX = :product_prefix AND TABLE_SUBSET > :table_subset) OR (PRODUCT_PREFIX = :product_prefix AND TABLE_SUBSET = :table_subset AND EFFECTIVE_DATE > :effective_date) OR (PRODUCT_PREFIX = :product_prefix AND TABLE_SUBSET = :table_subset AND EFFECTIVE_DATE = :effective_date AND LOB_CODE > :lob_code) OR (PRODUCT_PREFIX = :product_prefix AND TABLE_SUBSET = :table_subset AND EFFECTIVE_DATE = :effective_date AND LOB_CODE = :lob_code AND MEMO_CODE > :memo_code) OR (PRODUCT_PREFIX = :product_prefix AND TABLE_SUBSET = :table_subset AND EFFECTIVE_DATE = :effective_date AND LOB_CODE = :lob_code AND MEMO_CODE = :memo_code)) ";
                else
                    pagingWhere = ".T128X WHERE (COMPANY_CODE = :company_code) AND ((PRODUCT_PREFIX > :product_prefix) OR (PRODUCT_PREFIX = :product_prefix AND TABLE_SUBSET > :table_subset) OR (PRODUCT_PREFIX = :product_prefix AND TABLE_SUBSET = :table_subset AND EFFECTIVE_DATE > :effective_date) OR (PRODUCT_PREFIX = :product_prefix AND TABLE_SUBSET = :table_subset AND EFFECTIVE_DATE = :effective_date AND LOB_CODE > :lob_code) OR (PRODUCT_PREFIX = :product_prefix AND TABLE_SUBSET = :table_subset AND EFFECTIVE_DATE = :effective_date AND LOB_CODE = :lob_code AND MEMO_CODE > :memo_code)) ";
            order = " ORDER BY COMPANY_CODE, PRODUCT_PREFIX, TABLE_SUBSET, EFFECTIVE_DATE, LOB_CODE, MEMO_CODE";
        }
        else {
            if (isSubsetMode)
                pagingWhere = ".T128X WHERE (COMPANY_CODE = :company_code) AND (PRODUCT_PREFIX = :product_prefix) AND (TABLE_SUBSET = :table_subset) AND ((EFFECTIVE_DATE < :effective_date) OR (EFFECTIVE_DATE = :effective_date AND LOB_CODE < :lob_code) OR (EFFECTIVE_DATE = :effective_date AND LOB_CODE = :lob_code AND MEMO_CODE < :memo_code)) ";
            else
                pagingWhere = ".T128X WHERE (COMPANY_CODE = :company_code) AND ((PRODUCT_PREFIX < :product_prefix) OR (PRODUCT_PREFIX = :product_prefix AND TABLE_SUBSET < :table_subset) OR (PRODUCT_PREFIX = :product_prefix AND TABLE_SUBSET = :table_subset AND EFFECTIVE_DATE < :effective_date) OR (PRODUCT_PREFIX = :product_prefix AND TABLE_SUBSET = :table_subset AND EFFECTIVE_DATE = :effective_date AND LOB_CODE < :lob_code) OR (PRODUCT_PREFIX = :product_prefix AND TABLE_SUBSET = :table_subset AND EFFECTIVE_DATE = :effective_date AND LOB_CODE = :lob_code AND MEMO_CODE < :memo_code)) ";
            order = " ORDER BY COMPANY_CODE DESC, PRODUCT_PREFIX DESC, TABLE_SUBSET DESC, EFFECTIVE_DATE DESC, LOB_CODE DESC, MEMO_CODE DESC";
        }
        return pagingSql + schemaName + pagingWhere + order;
    }
    
    public String prepareInsertStmt(String schemaName) {
        StringBuffer sb = new StringBuffer();
        sb.append("INSERT INTO ").append(schemaName);
        sb.append(".T128X ( ");
        sb.append("COMPANY_CODE, PRODUCT_PREFIX, TABLE_SUBSET, EFFECTIVE_DATE, LOB_CODE, MEMO_CODE, MIN_INIT_PREM_AMOUNT, MIN_SUB_PREM_AMOUNT, MAX_PREM_AMOUNT, OWNER_ISSUE_AGE_MIN, CO_OWNER_ISSUE_AGE_MIN, ANNT_ISSUE_AGE_MIN, JNT_CO_ANNT_ISSUE_AGE_MIN, OWNER_ISSUE_AGE_MAX, CO_OWNER_ISSUE_AGE_MAX, ANNT_ISSUE_AGE_MAX, JNT_CO_ANNT_ISSUE_AGE_MAX, ANNT_COMMENCE_MAX_AGE, JNT_ANNT_COMMENCE_MAX_AGE, YEAR_INDICATOR, MAXIMUM_YEAR_PYMT, LIFETIME_MAX_AMOUNT, SUCC_ANNT_ISSUE_AGE_MIN, SUCC_ANNT_ISSUE_AGE_MAX, QLAC_MAX_DISB_AGE )");
        sb.append(" VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ? )");
        return sb.toString();
    }
    
    public String prepareUpdateStmt(String schemaName)
    {
        StringBuffer sb = new StringBuffer();
        sb.append("UPDATE ").append(schemaName);
        sb.append(".T128X ");
        sb.append(" SET COMPANY_CODE = ?, PRODUCT_PREFIX = ?, TABLE_SUBSET = ?, EFFECTIVE_DATE = ?, LOB_CODE = ?, MEMO_CODE = ?, MIN_INIT_PREM_AMOUNT = ?, MIN_SUB_PREM_AMOUNT = ?, MAX_PREM_AMOUNT = ?, OWNER_ISSUE_AGE_MIN = ?, CO_OWNER_ISSUE_AGE_MIN = ?, ANNT_ISSUE_AGE_MIN = ?, JNT_CO_ANNT_ISSUE_AGE_MIN = ?, OWNER_ISSUE_AGE_MAX = ?, CO_OWNER_ISSUE_AGE_MAX = ?, ANNT_ISSUE_AGE_MAX = ?, JNT_CO_ANNT_ISSUE_AGE_MAX = ?, ANNT_COMMENCE_MAX_AGE = ?, JNT_ANNT_COMMENCE_MAX_AGE = ?, YEAR_INDICATOR = ?, MAXIMUM_YEAR_PYMT = ?, LIFETIME_MAX_AMOUNT = ?, SUCC_ANNT_ISSUE_AGE_MIN = ?, SUCC_ANNT_ISSUE_AGE_MAX = ?, QLAC_MAX_DISB_AGE = ?");
        sb.append(" WHERE COMPANY_CODE = ? AND PRODUCT_PREFIX = ? AND TABLE_SUBSET = ? AND EFFECTIVE_DATE = ? AND LOB_CODE = ? AND MEMO_CODE = ?");
        return sb.toString();
    }
    
    public String prepareDeleteStmt(String schemaName)
    {
        StringBuffer sb = new StringBuffer();
        sb.append("DELETE FROM ").append(schemaName);
        sb.append(".T128X ");
        sb.append(" WHERE COMPANY_CODE = ? AND PRODUCT_PREFIX = ? AND TABLE_SUBSET = ? AND EFFECTIVE_DATE = ? AND LOB_CODE = ? AND MEMO_CODE = ?");
        return sb.toString();
    }
}
