package com.csc.fsg.life.pw.io.descriptor.wma;

import com.csc.fsg.life.pw.io.*;

public class T071XDescriptor
    extends TableDescriptor
{
    private static final String pagingSql = "SELECT COMPANY_CODE, PRODUCT_PREFIX, TABLE_SUBSET, EFFECTIVE_DATE, SEX_CODE, PREMIUM_CLASS_CD, ISSUE_AGE, POLICY_YEAR, TABLE_TYPE, SELECT_PERIOD, START_AGE, END_AGE, LAST_ULT_AGE, TABLE_COMP_IND, RECORD_TYPE, L_Q_IND, NO_LIVING, PROB_DYING FROM ";
    
    public void initialize()
    {
        setRowClass(T071XRow.class);
        setTableName("T071X");
        setTableId("071");
        super.initialize();
    }
    
    public void initializeColumnDescriptors()
    {
        super.initializeColumnDescriptors();
        addColumnDescriptor(new ColumnDescriptor(this,"getCompanyCode","setCompanyCode","COMPANY_CODE,1,1,3,0,true|T,0,CHAR,1,null,null,null,null,null|U,0,CHAR,1,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getProductPrefix","setProductPrefix","PRODUCT_PREFIX,1,2,1,0,true|T,0,CHAR,1,null,null,null,null,null|U,0,CHAR,1,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getTableSubset","setTableSubset","TABLE_SUBSET,1,3,16,0,true|T,0,CHAR,1,null,null,null,null,null|U,0,CHAR,1,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getEffectiveDate","setEffectiveDate","EFFECTIVE_DATE,91,4,10,0,true|T,0,DATE,0,null,null,null,null,null|U,0,DATE,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getSexCode","setSexCode","SEX_CODE,1,5,1,0,true|T,0,CHAR,0,null,null,null,null,null|U,0,CHAR,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getPremiumClassCd","setPremiumClassCd","PREMIUM_CLASS_CD,1,6,1,0,true|T,0,CHAR,0,null,null,null,null,null|U,0,CHAR,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getIssueAge","setIssueAge","ISSUE_AGE,3,7,3,0,true|T,0,INTEGER,0,null,null,null,null,null|U,0,INTEGER,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getPolicyYear","setPolicyYear","POLICY_YEAR,3,8,3,0,true|T,0,INTEGER,0,null,null,null,null,null|U,0,INTEGER,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getTableType","setTableType","TABLE_TYPE,1,9,3,0,false|T,0,CHAR,0,null,null,null,null,null|U,0,CHAR,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getSelectPeriod","setSelectPeriod","SELECT_PERIOD,3,10,3,0,false|T,0,INTEGER,0,null,null,null,null,null|U,0,INTEGER,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getStartAge","setStartAge","START_AGE,3,11,3,0,false|T,0,INTEGER,0,null,null,null,null,null|U,0,INTEGER,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getEndAge","setEndAge","END_AGE,3,12,3,0,false|T,0,INTEGER,0,null,null,null,null,null|U,0,INTEGER,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getLastUltAge","setLastUltAge","LAST_ULT_AGE,3,13,3,0,false|T,0,INTEGER,0,null,null,null,null,null|U,0,INTEGER,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getTableCompInd","setTableCompInd","TABLE_COMP_IND,1,14,1,0,false|T,0,CHAR,4,null,null,null,null,null|U,0,CHAR,4,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getRecordType","setRecordType","RECORD_TYPE,1,15,1,0,false|T,0,CHAR,0,null,null,null,null,null|U,0,CHAR,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getLQInd","setLQInd","L_Q_IND,1,16,1,0,false|T,0,CHAR,0,null,null,null,null,null|U,0,CHAR,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getNoLiving","setNoLiving","NO_LIVING,3,17,16,8,false|T,0,DOUBLE,0,null,null,null,null,null|U,0,DOUBLE,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getProbDying","setProbDying","PROB_DYING,3,18,9,8,false|T,0,DOUBLE,0,null,null,null,null,null|U,0,DOUBLE,0,null,null,null,null,null"));
    }
    
    public String getPagingSQL(String schemaName,boolean isSubsetMode,boolean isNext, boolean locator)
    {
        String pagingWhere = null;
        String order = null;
        if (isNext) {
            if (isSubsetMode)
                if (locator)
                    pagingWhere = ".T071X WHERE (COMPANY_CODE = :company_code) AND (PRODUCT_PREFIX = :product_prefix) AND (TABLE_SUBSET = :table_subset) AND ((EFFECTIVE_DATE > :effective_date) OR (EFFECTIVE_DATE = :effective_date AND SEX_CODE > :sex_code) OR (EFFECTIVE_DATE = :effective_date AND SEX_CODE = :sex_code AND PREMIUM_CLASS_CD > :premium_class_cd) OR (EFFECTIVE_DATE = :effective_date AND SEX_CODE = :sex_code AND PREMIUM_CLASS_CD = :premium_class_cd AND ISSUE_AGE > :issue_age) OR (EFFECTIVE_DATE = :effective_date AND SEX_CODE = :sex_code AND PREMIUM_CLASS_CD = :premium_class_cd AND ISSUE_AGE = :issue_age AND POLICY_YEAR > :policy_year) OR (EFFECTIVE_DATE = :effective_date AND SEX_CODE = :sex_code AND PREMIUM_CLASS_CD = :premium_class_cd AND ISSUE_AGE = :issue_age AND POLICY_YEAR = :policy_year)) ";
                else 
                    pagingWhere = ".T071X WHERE (COMPANY_CODE = :company_code) AND (PRODUCT_PREFIX = :product_prefix) AND (TABLE_SUBSET = :table_subset) AND ((EFFECTIVE_DATE > :effective_date) OR (EFFECTIVE_DATE = :effective_date AND SEX_CODE > :sex_code) OR (EFFECTIVE_DATE = :effective_date AND SEX_CODE = :sex_code AND PREMIUM_CLASS_CD > :premium_class_cd) OR (EFFECTIVE_DATE = :effective_date AND SEX_CODE = :sex_code AND PREMIUM_CLASS_CD = :premium_class_cd AND ISSUE_AGE > :issue_age) OR (EFFECTIVE_DATE = :effective_date AND SEX_CODE = :sex_code AND PREMIUM_CLASS_CD = :premium_class_cd AND ISSUE_AGE = :issue_age AND POLICY_YEAR > :policy_year)) ";
            else
                if (locator)
                    pagingWhere = ".T071X WHERE (COMPANY_CODE = :company_code) AND ((PRODUCT_PREFIX > :product_prefix) OR (PRODUCT_PREFIX = :product_prefix AND TABLE_SUBSET > :table_subset) OR (PRODUCT_PREFIX = :product_prefix AND TABLE_SUBSET = :table_subset AND EFFECTIVE_DATE > :effective_date) OR (PRODUCT_PREFIX = :product_prefix AND TABLE_SUBSET = :table_subset AND EFFECTIVE_DATE = :effective_date AND SEX_CODE > :sex_code) OR (PRODUCT_PREFIX = :product_prefix AND TABLE_SUBSET = :table_subset AND EFFECTIVE_DATE = :effective_date AND SEX_CODE = :sex_code AND PREMIUM_CLASS_CD > :premium_class_cd) OR (PRODUCT_PREFIX = :product_prefix AND TABLE_SUBSET = :table_subset AND EFFECTIVE_DATE = :effective_date AND SEX_CODE = :sex_code AND PREMIUM_CLASS_CD = :premium_class_cd AND ISSUE_AGE > :issue_age) OR (PRODUCT_PREFIX = :product_prefix AND TABLE_SUBSET = :table_subset AND EFFECTIVE_DATE = :effective_date AND SEX_CODE = :sex_code AND PREMIUM_CLASS_CD = :premium_class_cd AND ISSUE_AGE = :issue_age AND POLICY_YEAR > :policy_year) OR (PRODUCT_PREFIX = :product_prefix AND TABLE_SUBSET = :table_subset AND EFFECTIVE_DATE = :effective_date AND SEX_CODE = :sex_code AND PREMIUM_CLASS_CD = :premium_class_cd AND ISSUE_AGE = :issue_age AND POLICY_YEAR = :policy_year)) ";
                else
                    pagingWhere = ".T071X WHERE (COMPANY_CODE = :company_code) AND ((PRODUCT_PREFIX > :product_prefix) OR (PRODUCT_PREFIX = :product_prefix AND TABLE_SUBSET > :table_subset) OR (PRODUCT_PREFIX = :product_prefix AND TABLE_SUBSET = :table_subset AND EFFECTIVE_DATE > :effective_date) OR (PRODUCT_PREFIX = :product_prefix AND TABLE_SUBSET = :table_subset AND EFFECTIVE_DATE = :effective_date AND SEX_CODE > :sex_code) OR (PRODUCT_PREFIX = :product_prefix AND TABLE_SUBSET = :table_subset AND EFFECTIVE_DATE = :effective_date AND SEX_CODE = :sex_code AND PREMIUM_CLASS_CD > :premium_class_cd) OR (PRODUCT_PREFIX = :product_prefix AND TABLE_SUBSET = :table_subset AND EFFECTIVE_DATE = :effective_date AND SEX_CODE = :sex_code AND PREMIUM_CLASS_CD = :premium_class_cd AND ISSUE_AGE > :issue_age) OR (PRODUCT_PREFIX = :product_prefix AND TABLE_SUBSET = :table_subset AND EFFECTIVE_DATE = :effective_date AND SEX_CODE = :sex_code AND PREMIUM_CLASS_CD = :premium_class_cd AND ISSUE_AGE = :issue_age AND POLICY_YEAR > :policy_year)) ";
            order = " ORDER BY COMPANY_CODE, PRODUCT_PREFIX, TABLE_SUBSET, EFFECTIVE_DATE, SEX_CODE, PREMIUM_CLASS_CD, ISSUE_AGE, POLICY_YEAR";
        }
        else {
            if (isSubsetMode)
                pagingWhere = ".T071X WHERE (COMPANY_CODE = :company_code) AND (PRODUCT_PREFIX = :product_prefix) AND (TABLE_SUBSET = :table_subset) AND ((EFFECTIVE_DATE < :effective_date) OR (EFFECTIVE_DATE = :effective_date AND SEX_CODE < :sex_code) OR (EFFECTIVE_DATE = :effective_date AND SEX_CODE = :sex_code AND PREMIUM_CLASS_CD < :premium_class_cd) OR (EFFECTIVE_DATE = :effective_date AND SEX_CODE = :sex_code AND PREMIUM_CLASS_CD = :premium_class_cd AND ISSUE_AGE < :issue_age) OR (EFFECTIVE_DATE = :effective_date AND SEX_CODE = :sex_code AND PREMIUM_CLASS_CD = :premium_class_cd AND ISSUE_AGE = :issue_age AND POLICY_YEAR < :policy_year)) ";
            else
                pagingWhere = ".T071X WHERE (COMPANY_CODE = :company_code) AND ((PRODUCT_PREFIX < :product_prefix) OR (PRODUCT_PREFIX = :product_prefix AND TABLE_SUBSET < :table_subset) OR (PRODUCT_PREFIX = :product_prefix AND TABLE_SUBSET = :table_subset AND EFFECTIVE_DATE < :effective_date) OR (PRODUCT_PREFIX = :product_prefix AND TABLE_SUBSET = :table_subset AND EFFECTIVE_DATE = :effective_date AND SEX_CODE < :sex_code) OR (PRODUCT_PREFIX = :product_prefix AND TABLE_SUBSET = :table_subset AND EFFECTIVE_DATE = :effective_date AND SEX_CODE = :sex_code AND PREMIUM_CLASS_CD < :premium_class_cd) OR (PRODUCT_PREFIX = :product_prefix AND TABLE_SUBSET = :table_subset AND EFFECTIVE_DATE = :effective_date AND SEX_CODE = :sex_code AND PREMIUM_CLASS_CD = :premium_class_cd AND ISSUE_AGE < :issue_age) OR (PRODUCT_PREFIX = :product_prefix AND TABLE_SUBSET = :table_subset AND EFFECTIVE_DATE = :effective_date AND SEX_CODE = :sex_code AND PREMIUM_CLASS_CD = :premium_class_cd AND ISSUE_AGE = :issue_age AND POLICY_YEAR < :policy_year)) ";
            order = " ORDER BY COMPANY_CODE DESC, PRODUCT_PREFIX DESC, TABLE_SUBSET DESC, EFFECTIVE_DATE DESC, SEX_CODE DESC, PREMIUM_CLASS_CD DESC, ISSUE_AGE DESC, POLICY_YEAR DESC";
        }
        return pagingSql + schemaName + pagingWhere + order;
    }
    
    public String prepareInsertStmt(String schemaName) {
        StringBuffer sb = new StringBuffer();
        sb.append("INSERT INTO ").append(schemaName);
        sb.append(".T071X ( ");
        sb.append("COMPANY_CODE, PRODUCT_PREFIX, TABLE_SUBSET, EFFECTIVE_DATE, SEX_CODE, PREMIUM_CLASS_CD, ISSUE_AGE, POLICY_YEAR, TABLE_TYPE, SELECT_PERIOD, START_AGE, END_AGE, LAST_ULT_AGE, TABLE_COMP_IND, RECORD_TYPE, L_Q_IND, NO_LIVING, PROB_DYING )");
        sb.append(" VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ? )");
        return sb.toString();
    }
    
    public String prepareUpdateStmt(String schemaName)
    {
        StringBuffer sb = new StringBuffer();
        sb.append("UPDATE ").append(schemaName);
        sb.append(".T071X ");
        sb.append(" SET COMPANY_CODE = ?, PRODUCT_PREFIX = ?, TABLE_SUBSET = ?, EFFECTIVE_DATE = ?, SEX_CODE = ?, PREMIUM_CLASS_CD = ?, ISSUE_AGE = ?, POLICY_YEAR = ?, TABLE_TYPE = ?, SELECT_PERIOD = ?, START_AGE = ?, END_AGE = ?, LAST_ULT_AGE = ?, TABLE_COMP_IND = ?, RECORD_TYPE = ?, L_Q_IND = ?, NO_LIVING = ?, PROB_DYING = ?");
        sb.append(" WHERE COMPANY_CODE = ? AND PRODUCT_PREFIX = ? AND TABLE_SUBSET = ? AND EFFECTIVE_DATE = ? AND SEX_CODE = ? AND PREMIUM_CLASS_CD = ? AND ISSUE_AGE = ? AND POLICY_YEAR = ?");
        return sb.toString();
    }
    
    public String prepareDeleteStmt(String schemaName)
    {
        StringBuffer sb = new StringBuffer();
        sb.append("DELETE FROM ").append(schemaName);
        sb.append(".T071X ");
        sb.append(" WHERE COMPANY_CODE = ? AND PRODUCT_PREFIX = ? AND TABLE_SUBSET = ? AND EFFECTIVE_DATE = ? AND SEX_CODE = ? AND PREMIUM_CLASS_CD = ? AND ISSUE_AGE = ? AND POLICY_YEAR = ?");
        return sb.toString();
    }
}
