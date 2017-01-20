package com.csc.fsg.life.pw.io.descriptor.wma;

import com.csc.fsg.life.pw.io.*;

public class TH01HDescriptor
    extends TableDescriptor
{
    private static final String pagingSql = "SELECT COMPANY_CODE, TABLE_SUBSET, EFFECTIVE_DATE, CURR_VALUE_AMOUNT, CURR_VALUE_UNITS, HCC_RIDER_PLAN_CD, HCC_RIDER_TYPE, POLICY_PERSON_IND, HCC_DED_PRIORITY FROM ";
    
    public void initialize()
    {
        setRowClass(TH01HRow.class);
        setTableName("TH01H");
        setTableId("H01");
        super.initialize();
    }
    
    public void initializeColumnDescriptors()
    {
        super.initializeColumnDescriptors();
        addColumnDescriptor(new ColumnDescriptor(this,"getCompanyCode","setCompanyCode","COMPANY_CODE,1,1,3,0,true|,0,CHAR,1,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getTableSubset","setTableSubset","TABLE_SUBSET,1,2,16,0,true|,0,CHAR,1,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getEffectiveDate","setEffectiveDate","EFFECTIVE_DATE,91,3,10,0,true|,0,DATE,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getCurrValueAmount","setCurrValueAmount","CURR_VALUE_AMOUNT,3,4,11,2,true|,0,DOUBLE,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getCurrValueUnits","setCurrValueUnits","CURR_VALUE_UNITS,3,5,11,5,true|,0,DOUBLE,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getHccRiderPlanCd","setHccRiderPlanCd","HCC_RIDER_PLAN_CD,1,6,6,0,true|,0,CHAR,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getHccRiderType","setHccRiderType","HCC_RIDER_TYPE,1,7,2,0,true|,0,CHAR,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getPolicyPersonInd","setPolicyPersonInd","POLICY_PERSON_IND,1,8,1,0,false|,0,CHAR,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getHccDedPriority","setHccDedPriority","HCC_DED_PRIORITY,3,9,3,0,false|,0,INTEGER,0,null,null,null,null,null"));
    }
    
    public String getPagingSQL(String schemaName,boolean isSubsetMode,boolean isNext, boolean locator)
    {
        String pagingWhere = null;
        String order = null;
        if (isNext) {
            if (isSubsetMode)
                if (locator)
                    pagingWhere = ".TH01H WHERE (COMPANY_CODE = :company_code) AND (TABLE_SUBSET = :table_subset) AND ((EFFECTIVE_DATE > :effective_date) OR (EFFECTIVE_DATE = :effective_date AND CURR_VALUE_AMOUNT > :curr_value_amount) OR (EFFECTIVE_DATE = :effective_date AND CURR_VALUE_AMOUNT = :curr_value_amount AND CURR_VALUE_UNITS > :curr_value_units) OR (EFFECTIVE_DATE = :effective_date AND CURR_VALUE_AMOUNT = :curr_value_amount AND CURR_VALUE_UNITS = :curr_value_units AND HCC_RIDER_PLAN_CD > :hcc_rider_plan_cd) OR (EFFECTIVE_DATE = :effective_date AND CURR_VALUE_AMOUNT = :curr_value_amount AND CURR_VALUE_UNITS = :curr_value_units AND HCC_RIDER_PLAN_CD = :hcc_rider_plan_cd AND HCC_RIDER_TYPE > :hcc_rider_type) OR (EFFECTIVE_DATE = :effective_date AND CURR_VALUE_AMOUNT = :curr_value_amount AND CURR_VALUE_UNITS = :curr_value_units AND HCC_RIDER_PLAN_CD = :hcc_rider_plan_cd AND HCC_RIDER_TYPE = :hcc_rider_type)) ";
                else 
                    pagingWhere = ".TH01H WHERE (COMPANY_CODE = :company_code) AND (TABLE_SUBSET = :table_subset) AND ((EFFECTIVE_DATE > :effective_date) OR (EFFECTIVE_DATE = :effective_date AND CURR_VALUE_AMOUNT > :curr_value_amount) OR (EFFECTIVE_DATE = :effective_date AND CURR_VALUE_AMOUNT = :curr_value_amount AND CURR_VALUE_UNITS > :curr_value_units) OR (EFFECTIVE_DATE = :effective_date AND CURR_VALUE_AMOUNT = :curr_value_amount AND CURR_VALUE_UNITS = :curr_value_units AND HCC_RIDER_PLAN_CD > :hcc_rider_plan_cd) OR (EFFECTIVE_DATE = :effective_date AND CURR_VALUE_AMOUNT = :curr_value_amount AND CURR_VALUE_UNITS = :curr_value_units AND HCC_RIDER_PLAN_CD = :hcc_rider_plan_cd AND HCC_RIDER_TYPE > :hcc_rider_type)) ";
            else
                if (locator)
                    pagingWhere = ".TH01H WHERE (COMPANY_CODE = :company_code) AND ((TABLE_SUBSET > :table_subset) OR (TABLE_SUBSET = :table_subset AND EFFECTIVE_DATE > :effective_date) OR (TABLE_SUBSET = :table_subset AND EFFECTIVE_DATE = :effective_date AND CURR_VALUE_AMOUNT > :curr_value_amount) OR (TABLE_SUBSET = :table_subset AND EFFECTIVE_DATE = :effective_date AND CURR_VALUE_AMOUNT = :curr_value_amount AND CURR_VALUE_UNITS > :curr_value_units) OR (TABLE_SUBSET = :table_subset AND EFFECTIVE_DATE = :effective_date AND CURR_VALUE_AMOUNT = :curr_value_amount AND CURR_VALUE_UNITS = :curr_value_units AND HCC_RIDER_PLAN_CD > :hcc_rider_plan_cd) OR (TABLE_SUBSET = :table_subset AND EFFECTIVE_DATE = :effective_date AND CURR_VALUE_AMOUNT = :curr_value_amount AND CURR_VALUE_UNITS = :curr_value_units AND HCC_RIDER_PLAN_CD = :hcc_rider_plan_cd AND HCC_RIDER_TYPE > :hcc_rider_type) OR (TABLE_SUBSET = :table_subset AND EFFECTIVE_DATE = :effective_date AND CURR_VALUE_AMOUNT = :curr_value_amount AND CURR_VALUE_UNITS = :curr_value_units AND HCC_RIDER_PLAN_CD = :hcc_rider_plan_cd AND HCC_RIDER_TYPE = :hcc_rider_type)) ";
                else
                    pagingWhere = ".TH01H WHERE (COMPANY_CODE = :company_code) AND ((TABLE_SUBSET > :table_subset) OR (TABLE_SUBSET = :table_subset AND EFFECTIVE_DATE > :effective_date) OR (TABLE_SUBSET = :table_subset AND EFFECTIVE_DATE = :effective_date AND CURR_VALUE_AMOUNT > :curr_value_amount) OR (TABLE_SUBSET = :table_subset AND EFFECTIVE_DATE = :effective_date AND CURR_VALUE_AMOUNT = :curr_value_amount AND CURR_VALUE_UNITS > :curr_value_units) OR (TABLE_SUBSET = :table_subset AND EFFECTIVE_DATE = :effective_date AND CURR_VALUE_AMOUNT = :curr_value_amount AND CURR_VALUE_UNITS = :curr_value_units AND HCC_RIDER_PLAN_CD > :hcc_rider_plan_cd) OR (TABLE_SUBSET = :table_subset AND EFFECTIVE_DATE = :effective_date AND CURR_VALUE_AMOUNT = :curr_value_amount AND CURR_VALUE_UNITS = :curr_value_units AND HCC_RIDER_PLAN_CD = :hcc_rider_plan_cd AND HCC_RIDER_TYPE > :hcc_rider_type)) ";
            order = " ORDER BY COMPANY_CODE, TABLE_SUBSET, EFFECTIVE_DATE, CURR_VALUE_AMOUNT, CURR_VALUE_UNITS, HCC_RIDER_PLAN_CD, HCC_RIDER_TYPE";
        }
        else {
            if (isSubsetMode)
                pagingWhere = ".TH01H WHERE (COMPANY_CODE = :company_code) AND (TABLE_SUBSET = :table_subset) AND ((EFFECTIVE_DATE < :effective_date) OR (EFFECTIVE_DATE = :effective_date AND CURR_VALUE_AMOUNT < :curr_value_amount) OR (EFFECTIVE_DATE = :effective_date AND CURR_VALUE_AMOUNT = :curr_value_amount AND CURR_VALUE_UNITS < :curr_value_units) OR (EFFECTIVE_DATE = :effective_date AND CURR_VALUE_AMOUNT = :curr_value_amount AND CURR_VALUE_UNITS = :curr_value_units AND HCC_RIDER_PLAN_CD < :hcc_rider_plan_cd) OR (EFFECTIVE_DATE = :effective_date AND CURR_VALUE_AMOUNT = :curr_value_amount AND CURR_VALUE_UNITS = :curr_value_units AND HCC_RIDER_PLAN_CD = :hcc_rider_plan_cd AND HCC_RIDER_TYPE < :hcc_rider_type)) ";
            else
                pagingWhere = ".TH01H WHERE (COMPANY_CODE = :company_code) AND ((TABLE_SUBSET < :table_subset) OR (TABLE_SUBSET = :table_subset AND EFFECTIVE_DATE < :effective_date) OR (TABLE_SUBSET = :table_subset AND EFFECTIVE_DATE = :effective_date AND CURR_VALUE_AMOUNT < :curr_value_amount) OR (TABLE_SUBSET = :table_subset AND EFFECTIVE_DATE = :effective_date AND CURR_VALUE_AMOUNT = :curr_value_amount AND CURR_VALUE_UNITS < :curr_value_units) OR (TABLE_SUBSET = :table_subset AND EFFECTIVE_DATE = :effective_date AND CURR_VALUE_AMOUNT = :curr_value_amount AND CURR_VALUE_UNITS = :curr_value_units AND HCC_RIDER_PLAN_CD < :hcc_rider_plan_cd) OR (TABLE_SUBSET = :table_subset AND EFFECTIVE_DATE = :effective_date AND CURR_VALUE_AMOUNT = :curr_value_amount AND CURR_VALUE_UNITS = :curr_value_units AND HCC_RIDER_PLAN_CD = :hcc_rider_plan_cd AND HCC_RIDER_TYPE < :hcc_rider_type)) ";
            order = " ORDER BY COMPANY_CODE DESC, TABLE_SUBSET DESC, EFFECTIVE_DATE DESC, CURR_VALUE_AMOUNT DESC, CURR_VALUE_UNITS DESC, HCC_RIDER_PLAN_CD DESC, HCC_RIDER_TYPE DESC";
        }
        return pagingSql + schemaName + pagingWhere + order;
    }
    
    public String prepareInsertStmt(String schemaName) {
        StringBuffer sb = new StringBuffer();
        sb.append("INSERT INTO ").append(schemaName);
        sb.append(".TH01H ( ");
        sb.append("COMPANY_CODE, TABLE_SUBSET, EFFECTIVE_DATE, CURR_VALUE_AMOUNT, CURR_VALUE_UNITS, HCC_RIDER_PLAN_CD, HCC_RIDER_TYPE, POLICY_PERSON_IND, HCC_DED_PRIORITY )");
        sb.append(" VALUES (?, ?, ?, ?, ?, ?, ?, ?, ? )");
        return sb.toString();
    }
    
    public String prepareUpdateStmt(String schemaName)
    {
        StringBuffer sb = new StringBuffer();
        sb.append("UPDATE ").append(schemaName);
        sb.append(".TH01H ");
        sb.append(" SET COMPANY_CODE = ?, TABLE_SUBSET = ?, EFFECTIVE_DATE = ?, CURR_VALUE_AMOUNT = ?, CURR_VALUE_UNITS = ?, HCC_RIDER_PLAN_CD = ?, HCC_RIDER_TYPE = ?, POLICY_PERSON_IND = ?, HCC_DED_PRIORITY = ?");
        sb.append(" WHERE COMPANY_CODE = ? AND TABLE_SUBSET = ? AND EFFECTIVE_DATE = ? AND CURR_VALUE_AMOUNT = ? AND CURR_VALUE_UNITS = ? AND HCC_RIDER_PLAN_CD = ? AND HCC_RIDER_TYPE = ?");
        return sb.toString();
    }
    
    public String prepareDeleteStmt(String schemaName)
    {
        StringBuffer sb = new StringBuffer();
        sb.append("DELETE FROM ").append(schemaName);
        sb.append(".TH01H ");
        sb.append(" WHERE COMPANY_CODE = ? AND TABLE_SUBSET = ? AND EFFECTIVE_DATE = ? AND CURR_VALUE_AMOUNT = ? AND CURR_VALUE_UNITS = ? AND HCC_RIDER_PLAN_CD = ? AND HCC_RIDER_TYPE = ?");
        return sb.toString();
    }
}
