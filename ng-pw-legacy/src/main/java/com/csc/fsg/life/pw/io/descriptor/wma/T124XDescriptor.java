package com.csc.fsg.life.pw.io.descriptor.wma;

import com.csc.fsg.life.pw.io.*;

public class T124XDescriptor
    extends TableDescriptor
{
    private static final String pagingSql = "SELECT COMPANY_CODE, PRODUCT_PREFIX, TABLE_SUBSET, STATUTORY_CODE, EFFECTIVE_DATE, LINE_OF_BUSINESS, FACE_INCRCVG_RULE, FACE_DECR_RULE, RIDER_TERM_RULE, PREMIUM_CLASS_RULE, PREMIUM_TIME_RULE, NON_STANDARD_RULE, NON_STD_TIME_RULE FROM ";
    
    public void initialize()
    {
        setRowClass(T124XRow.class);
        setTableName("T124X");
        setTableId("124");
        super.initialize();
    }
    
    public void initializeColumnDescriptors()
    {
        super.initializeColumnDescriptors();
        addColumnDescriptor(new ColumnDescriptor(this,"getCompanyCode","setCompanyCode","COMPANY_CODE,1,1,3,0,true|T,0,CHAR,1,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getProductPrefix","setProductPrefix","PRODUCT_PREFIX,1,2,1,0,true|T,0,CHAR,1,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getTableSubset","setTableSubset","TABLE_SUBSET,1,3,16,0,true|T,0,CHAR,1,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getStatutoryCode","setStatutoryCode","STATUTORY_CODE,1,4,3,0,true|T,0,CHAR,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getEffectiveDate","setEffectiveDate","EFFECTIVE_DATE,91,5,10,0,true|T,0,DATE,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getLineOfBusiness","setLineOfBusiness","LINE_OF_BUSINESS,1,6,3,0,true|T,0,CHAR,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getFaceIncrcvgRule","setFaceIncrcvgRule","FACE_INCRCVG_RULE,1,7,1,0,false|T,0,CHAR,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getFaceDecrRule","setFaceDecrRule","FACE_DECR_RULE,1,8,1,0,false|T,0,CHAR,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getRiderTermRule","setRiderTermRule","RIDER_TERM_RULE,1,9,1,0,false|T,0,CHAR,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getPremiumClassRule","setPremiumClassRule","PREMIUM_CLASS_RULE,1,10,1,0,false|T,0,CHAR,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getPremiumTimeRule","setPremiumTimeRule","PREMIUM_TIME_RULE,1,11,1,0,false|T,0,CHAR,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getNonStandardRule","setNonStandardRule","NON_STANDARD_RULE,1,12,1,0,false|T,0,CHAR,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getNonStdTimeRule","setNonStdTimeRule","NON_STD_TIME_RULE,1,13,1,0,false|T,0,CHAR,0,null,null,null,null,null"));
    }
    
    public String getPagingSQL(String schemaName,boolean isSubsetMode,boolean isNext, boolean locator)
    {
        String pagingWhere = null;
        String order = null;
        if (isNext) {
            if (isSubsetMode)
                if (locator)
                    pagingWhere = ".T124X WHERE (COMPANY_CODE = :company_code) AND (PRODUCT_PREFIX = :product_prefix) AND (TABLE_SUBSET = :table_subset) AND ((STATUTORY_CODE > :statutory_code) OR (STATUTORY_CODE = :statutory_code AND EFFECTIVE_DATE > :effective_date) OR (STATUTORY_CODE = :statutory_code AND EFFECTIVE_DATE = :effective_date AND LINE_OF_BUSINESS > :line_of_business) OR (STATUTORY_CODE = :statutory_code AND EFFECTIVE_DATE = :effective_date AND LINE_OF_BUSINESS = :line_of_business)) ";
                else 
                    pagingWhere = ".T124X WHERE (COMPANY_CODE = :company_code) AND (PRODUCT_PREFIX = :product_prefix) AND (TABLE_SUBSET = :table_subset) AND ((STATUTORY_CODE > :statutory_code) OR (STATUTORY_CODE = :statutory_code AND EFFECTIVE_DATE > :effective_date) OR (STATUTORY_CODE = :statutory_code AND EFFECTIVE_DATE = :effective_date AND LINE_OF_BUSINESS > :line_of_business)) ";
            else
                if (locator)
                    pagingWhere = ".T124X WHERE (COMPANY_CODE = :company_code) AND ((PRODUCT_PREFIX > :product_prefix) OR (PRODUCT_PREFIX = :product_prefix AND TABLE_SUBSET > :table_subset) OR (PRODUCT_PREFIX = :product_prefix AND TABLE_SUBSET = :table_subset AND STATUTORY_CODE > :statutory_code) OR (PRODUCT_PREFIX = :product_prefix AND TABLE_SUBSET = :table_subset AND STATUTORY_CODE = :statutory_code AND EFFECTIVE_DATE > :effective_date) OR (PRODUCT_PREFIX = :product_prefix AND TABLE_SUBSET = :table_subset AND STATUTORY_CODE = :statutory_code AND EFFECTIVE_DATE = :effective_date AND LINE_OF_BUSINESS > :line_of_business) OR (PRODUCT_PREFIX = :product_prefix AND TABLE_SUBSET = :table_subset AND STATUTORY_CODE = :statutory_code AND EFFECTIVE_DATE = :effective_date AND LINE_OF_BUSINESS = :line_of_business)) ";
                else
                    pagingWhere = ".T124X WHERE (COMPANY_CODE = :company_code) AND ((PRODUCT_PREFIX > :product_prefix) OR (PRODUCT_PREFIX = :product_prefix AND TABLE_SUBSET > :table_subset) OR (PRODUCT_PREFIX = :product_prefix AND TABLE_SUBSET = :table_subset AND STATUTORY_CODE > :statutory_code) OR (PRODUCT_PREFIX = :product_prefix AND TABLE_SUBSET = :table_subset AND STATUTORY_CODE = :statutory_code AND EFFECTIVE_DATE > :effective_date) OR (PRODUCT_PREFIX = :product_prefix AND TABLE_SUBSET = :table_subset AND STATUTORY_CODE = :statutory_code AND EFFECTIVE_DATE = :effective_date AND LINE_OF_BUSINESS > :line_of_business)) ";
            order = " ORDER BY COMPANY_CODE, PRODUCT_PREFIX, TABLE_SUBSET, STATUTORY_CODE, EFFECTIVE_DATE, LINE_OF_BUSINESS";
        }
        else {
            if (isSubsetMode)
                pagingWhere = ".T124X WHERE (COMPANY_CODE = :company_code) AND (PRODUCT_PREFIX = :product_prefix) AND (TABLE_SUBSET = :table_subset) AND ((STATUTORY_CODE < :statutory_code) OR (STATUTORY_CODE = :statutory_code AND EFFECTIVE_DATE < :effective_date) OR (STATUTORY_CODE = :statutory_code AND EFFECTIVE_DATE = :effective_date AND LINE_OF_BUSINESS < :line_of_business)) ";
            else
                pagingWhere = ".T124X WHERE (COMPANY_CODE = :company_code) AND ((PRODUCT_PREFIX < :product_prefix) OR (PRODUCT_PREFIX = :product_prefix AND TABLE_SUBSET < :table_subset) OR (PRODUCT_PREFIX = :product_prefix AND TABLE_SUBSET = :table_subset AND STATUTORY_CODE < :statutory_code) OR (PRODUCT_PREFIX = :product_prefix AND TABLE_SUBSET = :table_subset AND STATUTORY_CODE = :statutory_code AND EFFECTIVE_DATE < :effective_date) OR (PRODUCT_PREFIX = :product_prefix AND TABLE_SUBSET = :table_subset AND STATUTORY_CODE = :statutory_code AND EFFECTIVE_DATE = :effective_date AND LINE_OF_BUSINESS < :line_of_business)) ";
            order = " ORDER BY COMPANY_CODE DESC, PRODUCT_PREFIX DESC, TABLE_SUBSET DESC, STATUTORY_CODE DESC, EFFECTIVE_DATE DESC, LINE_OF_BUSINESS DESC";
        }
        return pagingSql + schemaName + pagingWhere + order;
    }
    
    public String prepareInsertStmt(String schemaName) {
        StringBuffer sb = new StringBuffer();
        sb.append("INSERT INTO ").append(schemaName);
        sb.append(".T124X ( ");
        sb.append("COMPANY_CODE, PRODUCT_PREFIX, TABLE_SUBSET, STATUTORY_CODE, EFFECTIVE_DATE, LINE_OF_BUSINESS, FACE_INCRCVG_RULE, FACE_DECR_RULE, RIDER_TERM_RULE, PREMIUM_CLASS_RULE, PREMIUM_TIME_RULE, NON_STANDARD_RULE, NON_STD_TIME_RULE )");
        sb.append(" VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ? )");
        return sb.toString();
    }
    
    public String prepareUpdateStmt(String schemaName)
    {
        StringBuffer sb = new StringBuffer();
        sb.append("UPDATE ").append(schemaName);
        sb.append(".T124X ");
        sb.append(" SET COMPANY_CODE = ?, PRODUCT_PREFIX = ?, TABLE_SUBSET = ?, STATUTORY_CODE = ?, EFFECTIVE_DATE = ?, LINE_OF_BUSINESS = ?, FACE_INCRCVG_RULE = ?, FACE_DECR_RULE = ?, RIDER_TERM_RULE = ?, PREMIUM_CLASS_RULE = ?, PREMIUM_TIME_RULE = ?, NON_STANDARD_RULE = ?, NON_STD_TIME_RULE = ?");
        sb.append(" WHERE COMPANY_CODE = ? AND PRODUCT_PREFIX = ? AND TABLE_SUBSET = ? AND STATUTORY_CODE = ? AND EFFECTIVE_DATE = ? AND LINE_OF_BUSINESS = ?");
        return sb.toString();
    }
    
    public String prepareDeleteStmt(String schemaName)
    {
        StringBuffer sb = new StringBuffer();
        sb.append("DELETE FROM ").append(schemaName);
        sb.append(".T124X ");
        sb.append(" WHERE COMPANY_CODE = ? AND PRODUCT_PREFIX = ? AND TABLE_SUBSET = ? AND STATUTORY_CODE = ? AND EFFECTIVE_DATE = ? AND LINE_OF_BUSINESS = ?");
        return sb.toString();
    }
}
