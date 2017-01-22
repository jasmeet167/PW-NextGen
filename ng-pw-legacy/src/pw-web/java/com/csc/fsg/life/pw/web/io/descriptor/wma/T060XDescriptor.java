package com.csc.fsg.life.pw.web.io.descriptor.wma;

import com.csc.fsg.life.pw.web.io.*;

public class T060XDescriptor
    extends TableDescriptor
{
    private static final String pagingSql = "SELECT COMPANY_CODE, PRODUCT_PREFIX, TABLE_SUBSET, FUND_NUMBER, FAV_CODE, EFFECTIVE_DATE, ISSUE_STATE, ISSUE_YEAR, MAX_POLICY_YEAR, GUAR_INTEREST_RATE, STAT_INTEREST_RATE, GAAP_INTEREST_RATE, TAX_INTEREST_RATE FROM ";
    
    public void initialize()
    {
        setRowClass(T060XRow.class);
        setTableName("T060X");
        setTableId("060");
        super.initialize();
    }
    
    public void initializeColumnDescriptors()
    {
        super.initializeColumnDescriptors();
        addColumnDescriptor(new ColumnDescriptor(this,"getCompanyCode","setCompanyCode","COMPANY_CODE,1,1,3,0,true|A,0,CHAR,1,null,null,null,null,null|U,0,CHAR,1,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getProductPrefix","setProductPrefix","PRODUCT_PREFIX,1,2,1,0,true|A,0,CHAR,1,null,null,null,null,null|U,0,CHAR,1,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getTableSubset","setTableSubset","TABLE_SUBSET,1,3,16,0,true|A,0,CHAR,1,null,null,null,null,null|U,0,CHAR,1,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getFundNumber","setFundNumber","FUND_NUMBER,3,4,8,0,true|A,0,INTEGER,0,null,null,null,null,null|U,0,INTEGER,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getFavCode","setFavCode","FAV_CODE,1,5,6,0,true|A,0,CHAR,4,null,null,null,null,null|U,0,CHAR,4,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getEffectiveDate","setEffectiveDate","EFFECTIVE_DATE,91,6,10,0,true|A,0,DATE,0,null,null,null,null,null|U,0,DATE,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getIssueState","setIssueState","ISSUE_STATE,1,7,3,0,true|A,0,CHAR,2,null,null,null,null,null|U,0,CHAR,4,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getIssueYear","setIssueYear","ISSUE_YEAR,3,8,4,0,true|A,0,INTEGER,0,null,null,null,null,null|U,0,INTEGER,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getMaxPolicyYear","setMaxPolicyYear","MAX_POLICY_YEAR,3,9,3,0,true|A,0,INTEGER,0,null,null,null,null,null|U,0,INTEGER,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getGuarInterestRate","setGuarInterestRate","GUAR_INTEREST_RATE,3,10,5,3,false|A,0,DOUBLE,0,null,null,null,null,null|U,0,DOUBLE,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getStatInterestRate","setStatInterestRate","STAT_INTEREST_RATE,3,11,5,3,false|A,0,DOUBLE,0,null,null,null,null,null|U,0,DOUBLE,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getGaapInterestRate","setGaapInterestRate","GAAP_INTEREST_RATE,3,12,5,3,false|A,0,DOUBLE,0,null,null,null,null,null|U,0,DOUBLE,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getTaxInterestRate","setTaxInterestRate","TAX_INTEREST_RATE,3,13,5,3,false|A,0,DOUBLE,0,null,null,null,null,null|U,0,DOUBLE,0,null,null,null,null,null"));
    }
    
    public String getPagingSQL(String schemaName,boolean isSubsetMode,boolean isNext, boolean locator)
    {
        String pagingWhere = null;
        String order = null;
        if (isNext) {
            if (isSubsetMode)
                if (locator)
                    pagingWhere = ".T060X WHERE (COMPANY_CODE = :company_code) AND (PRODUCT_PREFIX = :product_prefix) AND (TABLE_SUBSET = :table_subset) AND ((FUND_NUMBER > :fund_number) OR (FUND_NUMBER = :fund_number AND FAV_CODE > :fav_code) OR (FUND_NUMBER = :fund_number AND FAV_CODE = :fav_code AND EFFECTIVE_DATE > :effective_date) OR (FUND_NUMBER = :fund_number AND FAV_CODE = :fav_code AND EFFECTIVE_DATE = :effective_date AND ISSUE_STATE > :issue_state) OR (FUND_NUMBER = :fund_number AND FAV_CODE = :fav_code AND EFFECTIVE_DATE = :effective_date AND ISSUE_STATE = :issue_state AND ISSUE_YEAR > :issue_year) OR (FUND_NUMBER = :fund_number AND FAV_CODE = :fav_code AND EFFECTIVE_DATE = :effective_date AND ISSUE_STATE = :issue_state AND ISSUE_YEAR = :issue_year AND MAX_POLICY_YEAR > :max_policy_year) OR (FUND_NUMBER = :fund_number AND FAV_CODE = :fav_code AND EFFECTIVE_DATE = :effective_date AND ISSUE_STATE = :issue_state AND ISSUE_YEAR = :issue_year AND MAX_POLICY_YEAR = :max_policy_year)) ";
                else 
                    pagingWhere = ".T060X WHERE (COMPANY_CODE = :company_code) AND (PRODUCT_PREFIX = :product_prefix) AND (TABLE_SUBSET = :table_subset) AND ((FUND_NUMBER > :fund_number) OR (FUND_NUMBER = :fund_number AND FAV_CODE > :fav_code) OR (FUND_NUMBER = :fund_number AND FAV_CODE = :fav_code AND EFFECTIVE_DATE > :effective_date) OR (FUND_NUMBER = :fund_number AND FAV_CODE = :fav_code AND EFFECTIVE_DATE = :effective_date AND ISSUE_STATE > :issue_state) OR (FUND_NUMBER = :fund_number AND FAV_CODE = :fav_code AND EFFECTIVE_DATE = :effective_date AND ISSUE_STATE = :issue_state AND ISSUE_YEAR > :issue_year) OR (FUND_NUMBER = :fund_number AND FAV_CODE = :fav_code AND EFFECTIVE_DATE = :effective_date AND ISSUE_STATE = :issue_state AND ISSUE_YEAR = :issue_year AND MAX_POLICY_YEAR > :max_policy_year)) ";
            else
                if (locator)
                    pagingWhere = ".T060X WHERE (COMPANY_CODE = :company_code) AND ((PRODUCT_PREFIX > :product_prefix) OR (PRODUCT_PREFIX = :product_prefix AND TABLE_SUBSET > :table_subset) OR (PRODUCT_PREFIX = :product_prefix AND TABLE_SUBSET = :table_subset AND FUND_NUMBER > :fund_number) OR (PRODUCT_PREFIX = :product_prefix AND TABLE_SUBSET = :table_subset AND FUND_NUMBER = :fund_number AND FAV_CODE > :fav_code) OR (PRODUCT_PREFIX = :product_prefix AND TABLE_SUBSET = :table_subset AND FUND_NUMBER = :fund_number AND FAV_CODE = :fav_code AND EFFECTIVE_DATE > :effective_date) OR (PRODUCT_PREFIX = :product_prefix AND TABLE_SUBSET = :table_subset AND FUND_NUMBER = :fund_number AND FAV_CODE = :fav_code AND EFFECTIVE_DATE = :effective_date AND ISSUE_STATE > :issue_state) OR (PRODUCT_PREFIX = :product_prefix AND TABLE_SUBSET = :table_subset AND FUND_NUMBER = :fund_number AND FAV_CODE = :fav_code AND EFFECTIVE_DATE = :effective_date AND ISSUE_STATE = :issue_state AND ISSUE_YEAR > :issue_year) OR (PRODUCT_PREFIX = :product_prefix AND TABLE_SUBSET = :table_subset AND FUND_NUMBER = :fund_number AND FAV_CODE = :fav_code AND EFFECTIVE_DATE = :effective_date AND ISSUE_STATE = :issue_state AND ISSUE_YEAR = :issue_year AND MAX_POLICY_YEAR > :max_policy_year) OR (PRODUCT_PREFIX = :product_prefix AND TABLE_SUBSET = :table_subset AND FUND_NUMBER = :fund_number AND FAV_CODE = :fav_code AND EFFECTIVE_DATE = :effective_date AND ISSUE_STATE = :issue_state AND ISSUE_YEAR = :issue_year AND MAX_POLICY_YEAR = :max_policy_year)) ";
                else
                    pagingWhere = ".T060X WHERE (COMPANY_CODE = :company_code) AND ((PRODUCT_PREFIX > :product_prefix) OR (PRODUCT_PREFIX = :product_prefix AND TABLE_SUBSET > :table_subset) OR (PRODUCT_PREFIX = :product_prefix AND TABLE_SUBSET = :table_subset AND FUND_NUMBER > :fund_number) OR (PRODUCT_PREFIX = :product_prefix AND TABLE_SUBSET = :table_subset AND FUND_NUMBER = :fund_number AND FAV_CODE > :fav_code) OR (PRODUCT_PREFIX = :product_prefix AND TABLE_SUBSET = :table_subset AND FUND_NUMBER = :fund_number AND FAV_CODE = :fav_code AND EFFECTIVE_DATE > :effective_date) OR (PRODUCT_PREFIX = :product_prefix AND TABLE_SUBSET = :table_subset AND FUND_NUMBER = :fund_number AND FAV_CODE = :fav_code AND EFFECTIVE_DATE = :effective_date AND ISSUE_STATE > :issue_state) OR (PRODUCT_PREFIX = :product_prefix AND TABLE_SUBSET = :table_subset AND FUND_NUMBER = :fund_number AND FAV_CODE = :fav_code AND EFFECTIVE_DATE = :effective_date AND ISSUE_STATE = :issue_state AND ISSUE_YEAR > :issue_year) OR (PRODUCT_PREFIX = :product_prefix AND TABLE_SUBSET = :table_subset AND FUND_NUMBER = :fund_number AND FAV_CODE = :fav_code AND EFFECTIVE_DATE = :effective_date AND ISSUE_STATE = :issue_state AND ISSUE_YEAR = :issue_year AND MAX_POLICY_YEAR > :max_policy_year)) ";
            order = " ORDER BY COMPANY_CODE, PRODUCT_PREFIX, TABLE_SUBSET, FUND_NUMBER, FAV_CODE, EFFECTIVE_DATE, ISSUE_STATE, ISSUE_YEAR, MAX_POLICY_YEAR";
        }
        else {
            if (isSubsetMode)
                pagingWhere = ".T060X WHERE (COMPANY_CODE = :company_code) AND (PRODUCT_PREFIX = :product_prefix) AND (TABLE_SUBSET = :table_subset) AND ((FUND_NUMBER < :fund_number) OR (FUND_NUMBER = :fund_number AND FAV_CODE < :fav_code) OR (FUND_NUMBER = :fund_number AND FAV_CODE = :fav_code AND EFFECTIVE_DATE < :effective_date) OR (FUND_NUMBER = :fund_number AND FAV_CODE = :fav_code AND EFFECTIVE_DATE = :effective_date AND ISSUE_STATE < :issue_state) OR (FUND_NUMBER = :fund_number AND FAV_CODE = :fav_code AND EFFECTIVE_DATE = :effective_date AND ISSUE_STATE = :issue_state AND ISSUE_YEAR < :issue_year) OR (FUND_NUMBER = :fund_number AND FAV_CODE = :fav_code AND EFFECTIVE_DATE = :effective_date AND ISSUE_STATE = :issue_state AND ISSUE_YEAR = :issue_year AND MAX_POLICY_YEAR < :max_policy_year)) ";
            else
                pagingWhere = ".T060X WHERE (COMPANY_CODE = :company_code) AND ((PRODUCT_PREFIX < :product_prefix) OR (PRODUCT_PREFIX = :product_prefix AND TABLE_SUBSET < :table_subset) OR (PRODUCT_PREFIX = :product_prefix AND TABLE_SUBSET = :table_subset AND FUND_NUMBER < :fund_number) OR (PRODUCT_PREFIX = :product_prefix AND TABLE_SUBSET = :table_subset AND FUND_NUMBER = :fund_number AND FAV_CODE < :fav_code) OR (PRODUCT_PREFIX = :product_prefix AND TABLE_SUBSET = :table_subset AND FUND_NUMBER = :fund_number AND FAV_CODE = :fav_code AND EFFECTIVE_DATE < :effective_date) OR (PRODUCT_PREFIX = :product_prefix AND TABLE_SUBSET = :table_subset AND FUND_NUMBER = :fund_number AND FAV_CODE = :fav_code AND EFFECTIVE_DATE = :effective_date AND ISSUE_STATE < :issue_state) OR (PRODUCT_PREFIX = :product_prefix AND TABLE_SUBSET = :table_subset AND FUND_NUMBER = :fund_number AND FAV_CODE = :fav_code AND EFFECTIVE_DATE = :effective_date AND ISSUE_STATE = :issue_state AND ISSUE_YEAR < :issue_year) OR (PRODUCT_PREFIX = :product_prefix AND TABLE_SUBSET = :table_subset AND FUND_NUMBER = :fund_number AND FAV_CODE = :fav_code AND EFFECTIVE_DATE = :effective_date AND ISSUE_STATE = :issue_state AND ISSUE_YEAR = :issue_year AND MAX_POLICY_YEAR < :max_policy_year)) ";
            order = " ORDER BY COMPANY_CODE DESC, PRODUCT_PREFIX DESC, TABLE_SUBSET DESC, FUND_NUMBER DESC, FAV_CODE DESC, EFFECTIVE_DATE DESC, ISSUE_STATE DESC, ISSUE_YEAR DESC, MAX_POLICY_YEAR DESC";
        }
        return pagingSql + schemaName + pagingWhere + order;
    }
    
    public String prepareInsertStmt(String schemaName) {
        StringBuffer sb = new StringBuffer();
        sb.append("INSERT INTO ").append(schemaName);
        sb.append(".T060X ( ");
        sb.append("COMPANY_CODE, PRODUCT_PREFIX, TABLE_SUBSET, FUND_NUMBER, FAV_CODE, EFFECTIVE_DATE, ISSUE_STATE, ISSUE_YEAR, MAX_POLICY_YEAR, GUAR_INTEREST_RATE, STAT_INTEREST_RATE, GAAP_INTEREST_RATE, TAX_INTEREST_RATE )");
        sb.append(" VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ? )");
        return sb.toString();
    }
    
    public String prepareUpdateStmt(String schemaName)
    {
        StringBuffer sb = new StringBuffer();
        sb.append("UPDATE ").append(schemaName);
        sb.append(".T060X ");
        sb.append(" SET COMPANY_CODE = ?, PRODUCT_PREFIX = ?, TABLE_SUBSET = ?, FUND_NUMBER = ?, FAV_CODE = ?, EFFECTIVE_DATE = ?, ISSUE_STATE = ?, ISSUE_YEAR = ?, MAX_POLICY_YEAR = ?, GUAR_INTEREST_RATE = ?, STAT_INTEREST_RATE = ?, GAAP_INTEREST_RATE = ?, TAX_INTEREST_RATE = ?");
        sb.append(" WHERE COMPANY_CODE = ? AND PRODUCT_PREFIX = ? AND TABLE_SUBSET = ? AND FUND_NUMBER = ? AND FAV_CODE = ? AND EFFECTIVE_DATE = ? AND ISSUE_STATE = ? AND ISSUE_YEAR = ? AND MAX_POLICY_YEAR = ?");
        return sb.toString();
    }
    
    public String prepareDeleteStmt(String schemaName)
    {
        StringBuffer sb = new StringBuffer();
        sb.append("DELETE FROM ").append(schemaName);
        sb.append(".T060X ");
        sb.append(" WHERE COMPANY_CODE = ? AND PRODUCT_PREFIX = ? AND TABLE_SUBSET = ? AND FUND_NUMBER = ? AND FAV_CODE = ? AND EFFECTIVE_DATE = ? AND ISSUE_STATE = ? AND ISSUE_YEAR = ? AND MAX_POLICY_YEAR = ?");
        return sb.toString();
    }
}
