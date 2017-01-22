package com.csc.fsg.life.pw.web.io.descriptor.wma;

import com.csc.fsg.life.pw.web.io.*;

public class T007XDescriptor
    extends TableDescriptor
{
    private static final String pagingSql = "SELECT COMPANY_CODE, PRODUCT_PREFIX, TABLE_SUBSET, RIDER_BENE_TYPE, RIDER_PLAN_CODE, EFFECTIVE_DATE, TERMINATION_DATE, NO_LAPSE_GUAR_MONTHS, INCR_NO_LAPSE_GUAR_MONTHS FROM ";
    
    public void initialize()
    {
        setRowClass(T007XRow.class);
        setTableName("T007X");
        setTableId("007");
        super.initialize();
    }
    
    public void initializeColumnDescriptors()
    {
        super.initializeColumnDescriptors();
        addColumnDescriptor(new ColumnDescriptor(this,"getCompanyCode","setCompanyCode","COMPANY_CODE,1,1,3,0,true|T,0,CHAR,1,null,null,null,null,null|U,0,CHAR,1,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getProductPrefix","setProductPrefix","PRODUCT_PREFIX,1,2,1,0,true|T,0,CHAR,1,null,null,null,null,null|U,0,CHAR,1,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getTableSubset","setTableSubset","TABLE_SUBSET,1,3,16,0,true|T,0,CHAR,1,null,null,null,null,null|U,0,CHAR,1,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getRiderBeneType","setRiderBeneType","RIDER_BENE_TYPE,1,4,2,0,true|T,0,CHAR,0,null,null,null,null,null|U,0,CHAR,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getRiderPlanCode","setRiderPlanCode","RIDER_PLAN_CODE,1,5,6,0,true|T,0,CHAR,0,null,null,null,null,RIDER_BENE_TYPE|U,0,CHAR,0,null,null,null,null,RIDER_BENE_TYPE"));
        addColumnDescriptor(new ColumnDescriptor(this,"getEffectiveDate","setEffectiveDate","EFFECTIVE_DATE,91,6,10,0,true|T,0,DATE,0,null,null,null,null,null|U,0,DATE,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getTerminationDate","setTerminationDate","TERMINATION_DATE,91,7,10,0,false|T,0,DATE,0,null,null,null,null,null|U,0,DATE,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getNoLapseGuarMonths","setNoLapseGuarMonths","NO_LAPSE_GUAR_MONTHS,3,8,3,0,false|T,0,INTEGER,2,null,null,null,null,null|U,0,INTEGER,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getIncrNoLapseGuarMonths","setIncrNoLapseGuarMonths","INCR_NO_LAPSE_GUAR_MONTHS,3,9,3,0,false|T,0,INTEGER,2,null,null,null,null,null|U,0,INTEGER,0,null,null,null,null,null"));
    }
    
    public String getPagingSQL(String schemaName,boolean isSubsetMode,boolean isNext, boolean locator)
    {
        String pagingWhere = null;
        String order = null;
        if (isNext) {
            if (isSubsetMode)
                if (locator)
                    pagingWhere = ".T007X WHERE (COMPANY_CODE = :company_code) AND (PRODUCT_PREFIX = :product_prefix) AND (TABLE_SUBSET = :table_subset) AND ((RIDER_BENE_TYPE > :rider_bene_type) OR (RIDER_BENE_TYPE = :rider_bene_type AND RIDER_PLAN_CODE > :rider_plan_code) OR (RIDER_BENE_TYPE = :rider_bene_type AND RIDER_PLAN_CODE = :rider_plan_code AND EFFECTIVE_DATE > :effective_date) OR (RIDER_BENE_TYPE = :rider_bene_type AND RIDER_PLAN_CODE = :rider_plan_code AND EFFECTIVE_DATE = :effective_date)) ";
                else 
                    pagingWhere = ".T007X WHERE (COMPANY_CODE = :company_code) AND (PRODUCT_PREFIX = :product_prefix) AND (TABLE_SUBSET = :table_subset) AND ((RIDER_BENE_TYPE > :rider_bene_type) OR (RIDER_BENE_TYPE = :rider_bene_type AND RIDER_PLAN_CODE > :rider_plan_code) OR (RIDER_BENE_TYPE = :rider_bene_type AND RIDER_PLAN_CODE = :rider_plan_code AND EFFECTIVE_DATE > :effective_date)) ";
            else
                if (locator)
                    pagingWhere = ".T007X WHERE (COMPANY_CODE = :company_code) AND ((PRODUCT_PREFIX > :product_prefix) OR (PRODUCT_PREFIX = :product_prefix AND TABLE_SUBSET > :table_subset) OR (PRODUCT_PREFIX = :product_prefix AND TABLE_SUBSET = :table_subset AND RIDER_BENE_TYPE > :rider_bene_type) OR (PRODUCT_PREFIX = :product_prefix AND TABLE_SUBSET = :table_subset AND RIDER_BENE_TYPE = :rider_bene_type AND RIDER_PLAN_CODE > :rider_plan_code) OR (PRODUCT_PREFIX = :product_prefix AND TABLE_SUBSET = :table_subset AND RIDER_BENE_TYPE = :rider_bene_type AND RIDER_PLAN_CODE = :rider_plan_code AND EFFECTIVE_DATE > :effective_date) OR (PRODUCT_PREFIX = :product_prefix AND TABLE_SUBSET = :table_subset AND RIDER_BENE_TYPE = :rider_bene_type AND RIDER_PLAN_CODE = :rider_plan_code AND EFFECTIVE_DATE = :effective_date)) ";
                else
                    pagingWhere = ".T007X WHERE (COMPANY_CODE = :company_code) AND ((PRODUCT_PREFIX > :product_prefix) OR (PRODUCT_PREFIX = :product_prefix AND TABLE_SUBSET > :table_subset) OR (PRODUCT_PREFIX = :product_prefix AND TABLE_SUBSET = :table_subset AND RIDER_BENE_TYPE > :rider_bene_type) OR (PRODUCT_PREFIX = :product_prefix AND TABLE_SUBSET = :table_subset AND RIDER_BENE_TYPE = :rider_bene_type AND RIDER_PLAN_CODE > :rider_plan_code) OR (PRODUCT_PREFIX = :product_prefix AND TABLE_SUBSET = :table_subset AND RIDER_BENE_TYPE = :rider_bene_type AND RIDER_PLAN_CODE = :rider_plan_code AND EFFECTIVE_DATE > :effective_date)) ";
            order = " ORDER BY COMPANY_CODE, PRODUCT_PREFIX, TABLE_SUBSET, RIDER_BENE_TYPE, RIDER_PLAN_CODE, EFFECTIVE_DATE";
        }
        else {
            if (isSubsetMode)
                pagingWhere = ".T007X WHERE (COMPANY_CODE = :company_code) AND (PRODUCT_PREFIX = :product_prefix) AND (TABLE_SUBSET = :table_subset) AND ((RIDER_BENE_TYPE < :rider_bene_type) OR (RIDER_BENE_TYPE = :rider_bene_type AND RIDER_PLAN_CODE < :rider_plan_code) OR (RIDER_BENE_TYPE = :rider_bene_type AND RIDER_PLAN_CODE = :rider_plan_code AND EFFECTIVE_DATE < :effective_date)) ";
            else
                pagingWhere = ".T007X WHERE (COMPANY_CODE = :company_code) AND ((PRODUCT_PREFIX < :product_prefix) OR (PRODUCT_PREFIX = :product_prefix AND TABLE_SUBSET < :table_subset) OR (PRODUCT_PREFIX = :product_prefix AND TABLE_SUBSET = :table_subset AND RIDER_BENE_TYPE < :rider_bene_type) OR (PRODUCT_PREFIX = :product_prefix AND TABLE_SUBSET = :table_subset AND RIDER_BENE_TYPE = :rider_bene_type AND RIDER_PLAN_CODE < :rider_plan_code) OR (PRODUCT_PREFIX = :product_prefix AND TABLE_SUBSET = :table_subset AND RIDER_BENE_TYPE = :rider_bene_type AND RIDER_PLAN_CODE = :rider_plan_code AND EFFECTIVE_DATE < :effective_date)) ";
            order = " ORDER BY COMPANY_CODE DESC, PRODUCT_PREFIX DESC, TABLE_SUBSET DESC, RIDER_BENE_TYPE DESC, RIDER_PLAN_CODE DESC, EFFECTIVE_DATE DESC";
        }
        return pagingSql + schemaName + pagingWhere + order;
    }
    
    public String prepareInsertStmt(String schemaName) {
        StringBuffer sb = new StringBuffer();
        sb.append("INSERT INTO ").append(schemaName);
        sb.append(".T007X ( ");
        sb.append("COMPANY_CODE, PRODUCT_PREFIX, TABLE_SUBSET, RIDER_BENE_TYPE, RIDER_PLAN_CODE, EFFECTIVE_DATE, TERMINATION_DATE, NO_LAPSE_GUAR_MONTHS, INCR_NO_LAPSE_GUAR_MONTHS )");
        sb.append(" VALUES (?, ?, ?, ?, ?, ?, ?, ?, ? )");
        return sb.toString();
    }
    
    public String prepareUpdateStmt(String schemaName)
    {
        StringBuffer sb = new StringBuffer();
        sb.append("UPDATE ").append(schemaName);
        sb.append(".T007X ");
        sb.append(" SET COMPANY_CODE = ?, PRODUCT_PREFIX = ?, TABLE_SUBSET = ?, RIDER_BENE_TYPE = ?, RIDER_PLAN_CODE = ?, EFFECTIVE_DATE = ?, TERMINATION_DATE = ?, NO_LAPSE_GUAR_MONTHS = ?, INCR_NO_LAPSE_GUAR_MONTHS = ?");
        sb.append(" WHERE COMPANY_CODE = ? AND PRODUCT_PREFIX = ? AND TABLE_SUBSET = ? AND RIDER_BENE_TYPE = ? AND RIDER_PLAN_CODE = ? AND EFFECTIVE_DATE = ?");
        return sb.toString();
    }
    
    public String prepareDeleteStmt(String schemaName)
    {
        StringBuffer sb = new StringBuffer();
        sb.append("DELETE FROM ").append(schemaName);
        sb.append(".T007X ");
        sb.append(" WHERE COMPANY_CODE = ? AND PRODUCT_PREFIX = ? AND TABLE_SUBSET = ? AND RIDER_BENE_TYPE = ? AND RIDER_PLAN_CODE = ? AND EFFECTIVE_DATE = ?");
        return sb.toString();
    }
}
