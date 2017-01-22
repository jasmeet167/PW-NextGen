package com.csc.fsg.life.pw.web.io.descriptor.wma;

import com.csc.fsg.life.pw.web.io.*;

public class T107XDescriptor
    extends TableDescriptor
{
    private static final String pagingSql = "SELECT COMPANY_CODE, PRODUCT_PREFIX, PRODUCT_SUFFIX, PLAN_CODE_DIGIT_1, PLAN_CODE_DIGIT_2, PLAN_CODE_DIGIT_3, PLAN_CODE_DIGIT_4, PLAN_CODE_DIGIT_5, PLAN_CODE_DIGIT_6, EFFECTIVE_DATE, PREM_CALC_OPTION, MIN_ISSUE_AGE, MAX_ISSUE_AGE, PLMNT_P_EXP_CAN_DD, MIN_NBR_UNITS, MAX_NBR_UNITS FROM ";
    
    public void initialize()
    {
        setRowClass(T107XRow.class);
        setTableName("T107X");
        setTableId("107");
        super.initialize();
    }
    
    public void initializeColumnDescriptors()
    {
        super.initializeColumnDescriptors();
        addColumnDescriptor(new ColumnDescriptor(this,"getCompanyCode","setCompanyCode","COMPANY_CODE,1,1,3,0,true|,0,CHAR,1,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getProductPrefix","setProductPrefix","PRODUCT_PREFIX,1,2,1,0,true|,0,CHAR,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getProductSuffix","setProductSuffix","PRODUCT_SUFFIX,1,3,1,0,true|,0,CHAR,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getPlanCodeDigit1","setPlanCodeDigit1","PLAN_CODE_DIGIT_1,1,4,1,0,true|,0,CHAR,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getPlanCodeDigit2","setPlanCodeDigit2","PLAN_CODE_DIGIT_2,1,5,1,0,true|,0,CHAR,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getPlanCodeDigit3","setPlanCodeDigit3","PLAN_CODE_DIGIT_3,1,6,1,0,true|,0,CHAR,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getPlanCodeDigit4","setPlanCodeDigit4","PLAN_CODE_DIGIT_4,1,7,1,0,true|,0,CHAR,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getPlanCodeDigit5","setPlanCodeDigit5","PLAN_CODE_DIGIT_5,1,8,1,0,true|,0,CHAR,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getPlanCodeDigit6","setPlanCodeDigit6","PLAN_CODE_DIGIT_6,1,9,1,0,true|,0,CHAR,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getEffectiveDate","setEffectiveDate","EFFECTIVE_DATE,91,10,10,0,true|,0,DATE,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getPremCalcOption","setPremCalcOption","PREM_CALC_OPTION,1,11,1,0,false|,0,CHAR,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getMinIssueAge","setMinIssueAge","MIN_ISSUE_AGE,3,12,3,0,false|,0,INTEGER,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getMaxIssueAge","setMaxIssueAge","MAX_ISSUE_AGE,3,13,3,0,false|,0,INTEGER,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getPlmntPExpCanDd","setPlmntPExpCanDd","PLMNT_P_EXP_CAN_DD,3,14,3,0,false|,0,INTEGER,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getMinNbrUnits","setMinNbrUnits","MIN_NBR_UNITS,3,15,11,5,false|,0,DOUBLE,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getMaxNbrUnits","setMaxNbrUnits","MAX_NBR_UNITS,3,16,11,5,false|,0,DOUBLE,0,null,null,null,null,null"));
    }
    
    public String getPagingSQL(String schemaName,boolean isSubsetMode,boolean isNext, boolean locator)
    {
        String pagingWhere = null;
        String order = null;
        if (isNext) {
            if (isSubsetMode)
                if (locator)
                    pagingWhere = ".T107X WHERE (COMPANY_CODE = :company_code) AND (PRODUCT_PREFIX = :product_prefix) AND ((PRODUCT_SUFFIX > :product_suffix) OR (PRODUCT_SUFFIX = :product_suffix AND PLAN_CODE_DIGIT_1 > :plan_code_digit_1) OR (PRODUCT_SUFFIX = :product_suffix AND PLAN_CODE_DIGIT_1 = :plan_code_digit_1 AND PLAN_CODE_DIGIT_2 > :plan_code_digit_2) OR (PRODUCT_SUFFIX = :product_suffix AND PLAN_CODE_DIGIT_1 = :plan_code_digit_1 AND PLAN_CODE_DIGIT_2 = :plan_code_digit_2 AND PLAN_CODE_DIGIT_3 > :plan_code_digit_3) OR (PRODUCT_SUFFIX = :product_suffix AND PLAN_CODE_DIGIT_1 = :plan_code_digit_1 AND PLAN_CODE_DIGIT_2 = :plan_code_digit_2 AND PLAN_CODE_DIGIT_3 = :plan_code_digit_3 AND PLAN_CODE_DIGIT_4 > :plan_code_digit_4) OR (PRODUCT_SUFFIX = :product_suffix AND PLAN_CODE_DIGIT_1 = :plan_code_digit_1 AND PLAN_CODE_DIGIT_2 = :plan_code_digit_2 AND PLAN_CODE_DIGIT_3 = :plan_code_digit_3 AND PLAN_CODE_DIGIT_4 = :plan_code_digit_4 AND PLAN_CODE_DIGIT_5 > :plan_code_digit_5) OR (PRODUCT_SUFFIX = :product_suffix AND PLAN_CODE_DIGIT_1 = :plan_code_digit_1 AND PLAN_CODE_DIGIT_2 = :plan_code_digit_2 AND PLAN_CODE_DIGIT_3 = :plan_code_digit_3 AND PLAN_CODE_DIGIT_4 = :plan_code_digit_4 AND PLAN_CODE_DIGIT_5 = :plan_code_digit_5 AND PLAN_CODE_DIGIT_6 > :plan_code_digit_6) OR (PRODUCT_SUFFIX = :product_suffix AND PLAN_CODE_DIGIT_1 = :plan_code_digit_1 AND PLAN_CODE_DIGIT_2 = :plan_code_digit_2 AND PLAN_CODE_DIGIT_3 = :plan_code_digit_3 AND PLAN_CODE_DIGIT_4 = :plan_code_digit_4 AND PLAN_CODE_DIGIT_5 = :plan_code_digit_5 AND PLAN_CODE_DIGIT_6 = :plan_code_digit_6 AND EFFECTIVE_DATE > :effective_date) OR (PRODUCT_SUFFIX = :product_suffix AND PLAN_CODE_DIGIT_1 = :plan_code_digit_1 AND PLAN_CODE_DIGIT_2 = :plan_code_digit_2 AND PLAN_CODE_DIGIT_3 = :plan_code_digit_3 AND PLAN_CODE_DIGIT_4 = :plan_code_digit_4 AND PLAN_CODE_DIGIT_5 = :plan_code_digit_5 AND PLAN_CODE_DIGIT_6 = :plan_code_digit_6 AND EFFECTIVE_DATE = :effective_date)) ";
                else 
                    pagingWhere = ".T107X WHERE (COMPANY_CODE = :company_code) AND (PRODUCT_PREFIX = :product_prefix) AND ((PRODUCT_SUFFIX > :product_suffix) OR (PRODUCT_SUFFIX = :product_suffix AND PLAN_CODE_DIGIT_1 > :plan_code_digit_1) OR (PRODUCT_SUFFIX = :product_suffix AND PLAN_CODE_DIGIT_1 = :plan_code_digit_1 AND PLAN_CODE_DIGIT_2 > :plan_code_digit_2) OR (PRODUCT_SUFFIX = :product_suffix AND PLAN_CODE_DIGIT_1 = :plan_code_digit_1 AND PLAN_CODE_DIGIT_2 = :plan_code_digit_2 AND PLAN_CODE_DIGIT_3 > :plan_code_digit_3) OR (PRODUCT_SUFFIX = :product_suffix AND PLAN_CODE_DIGIT_1 = :plan_code_digit_1 AND PLAN_CODE_DIGIT_2 = :plan_code_digit_2 AND PLAN_CODE_DIGIT_3 = :plan_code_digit_3 AND PLAN_CODE_DIGIT_4 > :plan_code_digit_4) OR (PRODUCT_SUFFIX = :product_suffix AND PLAN_CODE_DIGIT_1 = :plan_code_digit_1 AND PLAN_CODE_DIGIT_2 = :plan_code_digit_2 AND PLAN_CODE_DIGIT_3 = :plan_code_digit_3 AND PLAN_CODE_DIGIT_4 = :plan_code_digit_4 AND PLAN_CODE_DIGIT_5 > :plan_code_digit_5) OR (PRODUCT_SUFFIX = :product_suffix AND PLAN_CODE_DIGIT_1 = :plan_code_digit_1 AND PLAN_CODE_DIGIT_2 = :plan_code_digit_2 AND PLAN_CODE_DIGIT_3 = :plan_code_digit_3 AND PLAN_CODE_DIGIT_4 = :plan_code_digit_4 AND PLAN_CODE_DIGIT_5 = :plan_code_digit_5 AND PLAN_CODE_DIGIT_6 > :plan_code_digit_6) OR (PRODUCT_SUFFIX = :product_suffix AND PLAN_CODE_DIGIT_1 = :plan_code_digit_1 AND PLAN_CODE_DIGIT_2 = :plan_code_digit_2 AND PLAN_CODE_DIGIT_3 = :plan_code_digit_3 AND PLAN_CODE_DIGIT_4 = :plan_code_digit_4 AND PLAN_CODE_DIGIT_5 = :plan_code_digit_5 AND PLAN_CODE_DIGIT_6 = :plan_code_digit_6 AND EFFECTIVE_DATE > :effective_date)) ";
            else
                if (locator)
                    pagingWhere = ".T107X WHERE (COMPANY_CODE = :company_code) AND ((PRODUCT_PREFIX > :product_prefix) OR (PRODUCT_PREFIX = :product_prefix AND PRODUCT_SUFFIX > :product_suffix) OR (PRODUCT_PREFIX = :product_prefix AND PRODUCT_SUFFIX = :product_suffix AND PLAN_CODE_DIGIT_1 > :plan_code_digit_1) OR (PRODUCT_PREFIX = :product_prefix AND PRODUCT_SUFFIX = :product_suffix AND PLAN_CODE_DIGIT_1 = :plan_code_digit_1 AND PLAN_CODE_DIGIT_2 > :plan_code_digit_2) OR (PRODUCT_PREFIX = :product_prefix AND PRODUCT_SUFFIX = :product_suffix AND PLAN_CODE_DIGIT_1 = :plan_code_digit_1 AND PLAN_CODE_DIGIT_2 = :plan_code_digit_2 AND PLAN_CODE_DIGIT_3 > :plan_code_digit_3) OR (PRODUCT_PREFIX = :product_prefix AND PRODUCT_SUFFIX = :product_suffix AND PLAN_CODE_DIGIT_1 = :plan_code_digit_1 AND PLAN_CODE_DIGIT_2 = :plan_code_digit_2 AND PLAN_CODE_DIGIT_3 = :plan_code_digit_3 AND PLAN_CODE_DIGIT_4 > :plan_code_digit_4) OR (PRODUCT_PREFIX = :product_prefix AND PRODUCT_SUFFIX = :product_suffix AND PLAN_CODE_DIGIT_1 = :plan_code_digit_1 AND PLAN_CODE_DIGIT_2 = :plan_code_digit_2 AND PLAN_CODE_DIGIT_3 = :plan_code_digit_3 AND PLAN_CODE_DIGIT_4 = :plan_code_digit_4 AND PLAN_CODE_DIGIT_5 > :plan_code_digit_5) OR (PRODUCT_PREFIX = :product_prefix AND PRODUCT_SUFFIX = :product_suffix AND PLAN_CODE_DIGIT_1 = :plan_code_digit_1 AND PLAN_CODE_DIGIT_2 = :plan_code_digit_2 AND PLAN_CODE_DIGIT_3 = :plan_code_digit_3 AND PLAN_CODE_DIGIT_4 = :plan_code_digit_4 AND PLAN_CODE_DIGIT_5 = :plan_code_digit_5 AND PLAN_CODE_DIGIT_6 > :plan_code_digit_6) OR (PRODUCT_PREFIX = :product_prefix AND PRODUCT_SUFFIX = :product_suffix AND PLAN_CODE_DIGIT_1 = :plan_code_digit_1 AND PLAN_CODE_DIGIT_2 = :plan_code_digit_2 AND PLAN_CODE_DIGIT_3 = :plan_code_digit_3 AND PLAN_CODE_DIGIT_4 = :plan_code_digit_4 AND PLAN_CODE_DIGIT_5 = :plan_code_digit_5 AND PLAN_CODE_DIGIT_6 = :plan_code_digit_6 AND EFFECTIVE_DATE > :effective_date) OR (PRODUCT_PREFIX = :product_prefix AND PRODUCT_SUFFIX = :product_suffix AND PLAN_CODE_DIGIT_1 = :plan_code_digit_1 AND PLAN_CODE_DIGIT_2 = :plan_code_digit_2 AND PLAN_CODE_DIGIT_3 = :plan_code_digit_3 AND PLAN_CODE_DIGIT_4 = :plan_code_digit_4 AND PLAN_CODE_DIGIT_5 = :plan_code_digit_5 AND PLAN_CODE_DIGIT_6 = :plan_code_digit_6 AND EFFECTIVE_DATE = :effective_date)) ";
                else
                    pagingWhere = ".T107X WHERE (COMPANY_CODE = :company_code) AND ((PRODUCT_PREFIX > :product_prefix) OR (PRODUCT_PREFIX = :product_prefix AND PRODUCT_SUFFIX > :product_suffix) OR (PRODUCT_PREFIX = :product_prefix AND PRODUCT_SUFFIX = :product_suffix AND PLAN_CODE_DIGIT_1 > :plan_code_digit_1) OR (PRODUCT_PREFIX = :product_prefix AND PRODUCT_SUFFIX = :product_suffix AND PLAN_CODE_DIGIT_1 = :plan_code_digit_1 AND PLAN_CODE_DIGIT_2 > :plan_code_digit_2) OR (PRODUCT_PREFIX = :product_prefix AND PRODUCT_SUFFIX = :product_suffix AND PLAN_CODE_DIGIT_1 = :plan_code_digit_1 AND PLAN_CODE_DIGIT_2 = :plan_code_digit_2 AND PLAN_CODE_DIGIT_3 > :plan_code_digit_3) OR (PRODUCT_PREFIX = :product_prefix AND PRODUCT_SUFFIX = :product_suffix AND PLAN_CODE_DIGIT_1 = :plan_code_digit_1 AND PLAN_CODE_DIGIT_2 = :plan_code_digit_2 AND PLAN_CODE_DIGIT_3 = :plan_code_digit_3 AND PLAN_CODE_DIGIT_4 > :plan_code_digit_4) OR (PRODUCT_PREFIX = :product_prefix AND PRODUCT_SUFFIX = :product_suffix AND PLAN_CODE_DIGIT_1 = :plan_code_digit_1 AND PLAN_CODE_DIGIT_2 = :plan_code_digit_2 AND PLAN_CODE_DIGIT_3 = :plan_code_digit_3 AND PLAN_CODE_DIGIT_4 = :plan_code_digit_4 AND PLAN_CODE_DIGIT_5 > :plan_code_digit_5) OR (PRODUCT_PREFIX = :product_prefix AND PRODUCT_SUFFIX = :product_suffix AND PLAN_CODE_DIGIT_1 = :plan_code_digit_1 AND PLAN_CODE_DIGIT_2 = :plan_code_digit_2 AND PLAN_CODE_DIGIT_3 = :plan_code_digit_3 AND PLAN_CODE_DIGIT_4 = :plan_code_digit_4 AND PLAN_CODE_DIGIT_5 = :plan_code_digit_5 AND PLAN_CODE_DIGIT_6 > :plan_code_digit_6) OR (PRODUCT_PREFIX = :product_prefix AND PRODUCT_SUFFIX = :product_suffix AND PLAN_CODE_DIGIT_1 = :plan_code_digit_1 AND PLAN_CODE_DIGIT_2 = :plan_code_digit_2 AND PLAN_CODE_DIGIT_3 = :plan_code_digit_3 AND PLAN_CODE_DIGIT_4 = :plan_code_digit_4 AND PLAN_CODE_DIGIT_5 = :plan_code_digit_5 AND PLAN_CODE_DIGIT_6 = :plan_code_digit_6 AND EFFECTIVE_DATE > :effective_date)) ";
            order = " ORDER BY COMPANY_CODE, PRODUCT_PREFIX, PRODUCT_SUFFIX, PLAN_CODE_DIGIT_1, PLAN_CODE_DIGIT_2, PLAN_CODE_DIGIT_3, PLAN_CODE_DIGIT_4, PLAN_CODE_DIGIT_5, PLAN_CODE_DIGIT_6, EFFECTIVE_DATE";
        }
        else {
            if (isSubsetMode)
                pagingWhere = ".T107X WHERE (COMPANY_CODE = :company_code) AND (PRODUCT_PREFIX = :product_prefix) AND ((PRODUCT_SUFFIX < :product_suffix) OR (PRODUCT_SUFFIX = :product_suffix AND PLAN_CODE_DIGIT_1 < :plan_code_digit_1) OR (PRODUCT_SUFFIX = :product_suffix AND PLAN_CODE_DIGIT_1 = :plan_code_digit_1 AND PLAN_CODE_DIGIT_2 < :plan_code_digit_2) OR (PRODUCT_SUFFIX = :product_suffix AND PLAN_CODE_DIGIT_1 = :plan_code_digit_1 AND PLAN_CODE_DIGIT_2 = :plan_code_digit_2 AND PLAN_CODE_DIGIT_3 < :plan_code_digit_3) OR (PRODUCT_SUFFIX = :product_suffix AND PLAN_CODE_DIGIT_1 = :plan_code_digit_1 AND PLAN_CODE_DIGIT_2 = :plan_code_digit_2 AND PLAN_CODE_DIGIT_3 = :plan_code_digit_3 AND PLAN_CODE_DIGIT_4 < :plan_code_digit_4) OR (PRODUCT_SUFFIX = :product_suffix AND PLAN_CODE_DIGIT_1 = :plan_code_digit_1 AND PLAN_CODE_DIGIT_2 = :plan_code_digit_2 AND PLAN_CODE_DIGIT_3 = :plan_code_digit_3 AND PLAN_CODE_DIGIT_4 = :plan_code_digit_4 AND PLAN_CODE_DIGIT_5 < :plan_code_digit_5) OR (PRODUCT_SUFFIX = :product_suffix AND PLAN_CODE_DIGIT_1 = :plan_code_digit_1 AND PLAN_CODE_DIGIT_2 = :plan_code_digit_2 AND PLAN_CODE_DIGIT_3 = :plan_code_digit_3 AND PLAN_CODE_DIGIT_4 = :plan_code_digit_4 AND PLAN_CODE_DIGIT_5 = :plan_code_digit_5 AND PLAN_CODE_DIGIT_6 < :plan_code_digit_6) OR (PRODUCT_SUFFIX = :product_suffix AND PLAN_CODE_DIGIT_1 = :plan_code_digit_1 AND PLAN_CODE_DIGIT_2 = :plan_code_digit_2 AND PLAN_CODE_DIGIT_3 = :plan_code_digit_3 AND PLAN_CODE_DIGIT_4 = :plan_code_digit_4 AND PLAN_CODE_DIGIT_5 = :plan_code_digit_5 AND PLAN_CODE_DIGIT_6 = :plan_code_digit_6 AND EFFECTIVE_DATE < :effective_date)) ";
            else
                pagingWhere = ".T107X WHERE (COMPANY_CODE = :company_code) AND ((PRODUCT_PREFIX < :product_prefix) OR (PRODUCT_PREFIX = :product_prefix AND PRODUCT_SUFFIX < :product_suffix) OR (PRODUCT_PREFIX = :product_prefix AND PRODUCT_SUFFIX = :product_suffix AND PLAN_CODE_DIGIT_1 < :plan_code_digit_1) OR (PRODUCT_PREFIX = :product_prefix AND PRODUCT_SUFFIX = :product_suffix AND PLAN_CODE_DIGIT_1 = :plan_code_digit_1 AND PLAN_CODE_DIGIT_2 < :plan_code_digit_2) OR (PRODUCT_PREFIX = :product_prefix AND PRODUCT_SUFFIX = :product_suffix AND PLAN_CODE_DIGIT_1 = :plan_code_digit_1 AND PLAN_CODE_DIGIT_2 = :plan_code_digit_2 AND PLAN_CODE_DIGIT_3 < :plan_code_digit_3) OR (PRODUCT_PREFIX = :product_prefix AND PRODUCT_SUFFIX = :product_suffix AND PLAN_CODE_DIGIT_1 = :plan_code_digit_1 AND PLAN_CODE_DIGIT_2 = :plan_code_digit_2 AND PLAN_CODE_DIGIT_3 = :plan_code_digit_3 AND PLAN_CODE_DIGIT_4 < :plan_code_digit_4) OR (PRODUCT_PREFIX = :product_prefix AND PRODUCT_SUFFIX = :product_suffix AND PLAN_CODE_DIGIT_1 = :plan_code_digit_1 AND PLAN_CODE_DIGIT_2 = :plan_code_digit_2 AND PLAN_CODE_DIGIT_3 = :plan_code_digit_3 AND PLAN_CODE_DIGIT_4 = :plan_code_digit_4 AND PLAN_CODE_DIGIT_5 < :plan_code_digit_5) OR (PRODUCT_PREFIX = :product_prefix AND PRODUCT_SUFFIX = :product_suffix AND PLAN_CODE_DIGIT_1 = :plan_code_digit_1 AND PLAN_CODE_DIGIT_2 = :plan_code_digit_2 AND PLAN_CODE_DIGIT_3 = :plan_code_digit_3 AND PLAN_CODE_DIGIT_4 = :plan_code_digit_4 AND PLAN_CODE_DIGIT_5 = :plan_code_digit_5 AND PLAN_CODE_DIGIT_6 < :plan_code_digit_6) OR (PRODUCT_PREFIX = :product_prefix AND PRODUCT_SUFFIX = :product_suffix AND PLAN_CODE_DIGIT_1 = :plan_code_digit_1 AND PLAN_CODE_DIGIT_2 = :plan_code_digit_2 AND PLAN_CODE_DIGIT_3 = :plan_code_digit_3 AND PLAN_CODE_DIGIT_4 = :plan_code_digit_4 AND PLAN_CODE_DIGIT_5 = :plan_code_digit_5 AND PLAN_CODE_DIGIT_6 = :plan_code_digit_6 AND EFFECTIVE_DATE < :effective_date)) ";
            order = " ORDER BY COMPANY_CODE DESC, PRODUCT_PREFIX DESC, PRODUCT_SUFFIX DESC, PLAN_CODE_DIGIT_1 DESC, PLAN_CODE_DIGIT_2 DESC, PLAN_CODE_DIGIT_3 DESC, PLAN_CODE_DIGIT_4 DESC, PLAN_CODE_DIGIT_5 DESC, PLAN_CODE_DIGIT_6 DESC, EFFECTIVE_DATE DESC";
        }
        return pagingSql + schemaName + pagingWhere + order;
    }
    
    public String prepareInsertStmt(String schemaName) {
        StringBuffer sb = new StringBuffer();
        sb.append("INSERT INTO ").append(schemaName);
        sb.append(".T107X ( ");
        sb.append("COMPANY_CODE, PRODUCT_PREFIX, PRODUCT_SUFFIX, PLAN_CODE_DIGIT_1, PLAN_CODE_DIGIT_2, PLAN_CODE_DIGIT_3, PLAN_CODE_DIGIT_4, PLAN_CODE_DIGIT_5, PLAN_CODE_DIGIT_6, EFFECTIVE_DATE, PREM_CALC_OPTION, MIN_ISSUE_AGE, MAX_ISSUE_AGE, PLMNT_P_EXP_CAN_DD, MIN_NBR_UNITS, MAX_NBR_UNITS )");
        sb.append(" VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ? )");
        return sb.toString();
    }
    
    public String prepareUpdateStmt(String schemaName)
    {
        StringBuffer sb = new StringBuffer();
        sb.append("UPDATE ").append(schemaName);
        sb.append(".T107X ");
        sb.append(" SET COMPANY_CODE = ?, PRODUCT_PREFIX = ?, PRODUCT_SUFFIX = ?, PLAN_CODE_DIGIT_1 = ?, PLAN_CODE_DIGIT_2 = ?, PLAN_CODE_DIGIT_3 = ?, PLAN_CODE_DIGIT_4 = ?, PLAN_CODE_DIGIT_5 = ?, PLAN_CODE_DIGIT_6 = ?, EFFECTIVE_DATE = ?, PREM_CALC_OPTION = ?, MIN_ISSUE_AGE = ?, MAX_ISSUE_AGE = ?, PLMNT_P_EXP_CAN_DD = ?, MIN_NBR_UNITS = ?, MAX_NBR_UNITS = ?");
        sb.append(" WHERE COMPANY_CODE = ? AND PRODUCT_PREFIX = ? AND PRODUCT_SUFFIX = ? AND PLAN_CODE_DIGIT_1 = ? AND PLAN_CODE_DIGIT_2 = ? AND PLAN_CODE_DIGIT_3 = ? AND PLAN_CODE_DIGIT_4 = ? AND PLAN_CODE_DIGIT_5 = ? AND PLAN_CODE_DIGIT_6 = ? AND EFFECTIVE_DATE = ?");
        return sb.toString();
    }
    
    public String prepareDeleteStmt(String schemaName)
    {
        StringBuffer sb = new StringBuffer();
        sb.append("DELETE FROM ").append(schemaName);
        sb.append(".T107X ");
        sb.append(" WHERE COMPANY_CODE = ? AND PRODUCT_PREFIX = ? AND PRODUCT_SUFFIX = ? AND PLAN_CODE_DIGIT_1 = ? AND PLAN_CODE_DIGIT_2 = ? AND PLAN_CODE_DIGIT_3 = ? AND PLAN_CODE_DIGIT_4 = ? AND PLAN_CODE_DIGIT_5 = ? AND PLAN_CODE_DIGIT_6 = ? AND EFFECTIVE_DATE = ?");
        return sb.toString();
    }
}
