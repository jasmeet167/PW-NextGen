package com.csc.fsg.life.pw.io.descriptor.wma;

import com.csc.fsg.life.pw.io.*;

public class T097EDescriptor
    extends TableDescriptor
{
    private static final String pagingSql = "SELECT COMPANY_CODE, PRODUCT_PREFIX, TABLE_SUBSET, EFFECTIVE_DATE, DURATION, CALC_TYPE_IND, LINE_OF_BUSINESS, FUND_NUMBER, TOLERANCE_AMOUNT, CURR_GUAR_RT_IND, SPECIAL_CLASS, ADMIN_PAYMENT, SALES_PAYMENT, ADMIN_PAY_TARGET, SALES_PAY_TARGET, TRANSACTION_FEE, INTEREST_SPEC, FIT_GUAR_INT_RATE, COST_OF_RISK, ADMIN_RISK_LOAD, SALES_RISK_LOAD, PCT_GUAR_COI, CONTAGION_FACTOR, ADMIN_SURR_SPEC, SALES_SURR_SPEC, ADMIN_INCREASE, SALES_INCREASE, ADMIN_ANNIVERSARY, SALES_ANNIVERSARY FROM ";
    
    public void initialize()
    {
        setRowClass(T097ERow.class);
        setTableName("T097E");
        setTableId("097");
        super.initialize();
    }
    
    public void initializeColumnDescriptors()
    {
        super.initializeColumnDescriptors();
        addColumnDescriptor(new ColumnDescriptor(this,"getCompanyCode","setCompanyCode","COMPANY_CODE,1,1,3,0,true|,0,CHAR,1,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getProductPrefix","setProductPrefix","PRODUCT_PREFIX,1,2,1,0,true|,0,CHAR,1,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getTableSubset","setTableSubset","TABLE_SUBSET,1,3,16,0,true|,0,CHAR,1,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getEffectiveDate","setEffectiveDate","EFFECTIVE_DATE,91,4,10,0,true|,0,DATE,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getDuration","setDuration","DURATION,3,5,3,0,true|,0,INTEGER,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getCalcTypeInd","setCalcTypeInd","CALC_TYPE_IND,1,6,1,0,true|,0,CHAR,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getLineOfBusiness","setLineOfBusiness","LINE_OF_BUSINESS,1,7,3,0,true|,0,CHAR,2,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getFundNumber","setFundNumber","FUND_NUMBER,3,8,8,0,false|,0,INTEGER,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getToleranceAmount","setToleranceAmount","TOLERANCE_AMOUNT,3,9,5,2,false|,0,DOUBLE,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getCurrGuarRtInd","setCurrGuarRtInd","CURR_GUAR_RT_IND,1,10,1,0,false|,0,CHAR,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getSpecialClass","setSpecialClass","SPECIAL_CLASS,1,11,16,0,false|,0,CHAR,0,012,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getAdminPayment","setAdminPayment","ADMIN_PAYMENT,1,12,16,0,false|,0,CHAR,0,019,1,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getSalesPayment","setSalesPayment","SALES_PAYMENT,1,13,16,0,false|,0,CHAR,0,019,2,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getAdminPayTarget","setAdminPayTarget","ADMIN_PAY_TARGET,1,14,16,0,false|,0,CHAR,0,020,1,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getSalesPayTarget","setSalesPayTarget","SALES_PAY_TARGET,1,15,16,0,false|,0,CHAR,0,020,2,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getTransactionFee","setTransactionFee","TRANSACTION_FEE,1,16,16,0,false|,0,CHAR,0,022,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getInterestSpec","setInterestSpec","INTEREST_SPEC,1,17,16,0,false|,0,CHAR,0,024,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getFitGuarIntRate","setFitGuarIntRate","FIT_GUAR_INT_RATE,1,18,16,0,false|,0,CHAR,0,026,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getCostOfRisk","setCostOfRisk","COST_OF_RISK,1,19,16,0,false|,0,CHAR,0,028,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getAdminRiskLoad","setAdminRiskLoad","ADMIN_RISK_LOAD,1,20,16,0,false|,0,CHAR,0,031,1,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getSalesRiskLoad","setSalesRiskLoad","SALES_RISK_LOAD,1,21,16,0,false|,0,CHAR,0,031,2,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getPctGuarCoi","setPctGuarCoi","PCT_GUAR_COI,1,22,16,0,false|,0,CHAR,0,031,3,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getContagionFactor","setContagionFactor","CONTAGION_FACTOR,1,23,16,0,false|,0,CHAR,0,031,4,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getAdminSurrSpec","setAdminSurrSpec","ADMIN_SURR_SPEC,1,24,16,0,false|,0,CHAR,0,032,1,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getSalesSurrSpec","setSalesSurrSpec","SALES_SURR_SPEC,1,25,16,0,false|,0,CHAR,0,032,2,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getAdminIncrease","setAdminIncrease","ADMIN_INCREASE,1,26,16,0,false|,0,CHAR,0,037,1,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getSalesIncrease","setSalesIncrease","SALES_INCREASE,1,27,16,0,false|,0,CHAR,0,037,2,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getAdminAnniversary","setAdminAnniversary","ADMIN_ANNIVERSARY,1,28,16,0,false|,0,CHAR,0,054,1,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getSalesAnniversary","setSalesAnniversary","SALES_ANNIVERSARY,1,29,16,0,false|,0,CHAR,0,054,2,null,null,null"));
    }
    
    public String getPagingSQL(String schemaName,boolean isSubsetMode,boolean isNext, boolean locator)
    {
        String pagingWhere = null;
        String order = null;
        if (isNext) {
            if (isSubsetMode)
                if (locator)
                    pagingWhere = ".T097E WHERE (COMPANY_CODE = :company_code) AND (PRODUCT_PREFIX = :product_prefix) AND (TABLE_SUBSET = :table_subset) AND ((EFFECTIVE_DATE > :effective_date) OR (EFFECTIVE_DATE = :effective_date AND DURATION > :duration) OR (EFFECTIVE_DATE = :effective_date AND DURATION = :duration AND CALC_TYPE_IND > :calc_type_ind) OR (EFFECTIVE_DATE = :effective_date AND DURATION = :duration AND CALC_TYPE_IND = :calc_type_ind AND LINE_OF_BUSINESS > :line_of_business) OR (EFFECTIVE_DATE = :effective_date AND DURATION = :duration AND CALC_TYPE_IND = :calc_type_ind AND LINE_OF_BUSINESS = :line_of_business)) ";
                else 
                    pagingWhere = ".T097E WHERE (COMPANY_CODE = :company_code) AND (PRODUCT_PREFIX = :product_prefix) AND (TABLE_SUBSET = :table_subset) AND ((EFFECTIVE_DATE > :effective_date) OR (EFFECTIVE_DATE = :effective_date AND DURATION > :duration) OR (EFFECTIVE_DATE = :effective_date AND DURATION = :duration AND CALC_TYPE_IND > :calc_type_ind) OR (EFFECTIVE_DATE = :effective_date AND DURATION = :duration AND CALC_TYPE_IND = :calc_type_ind AND LINE_OF_BUSINESS > :line_of_business)) ";
            else
                if (locator)
                    pagingWhere = ".T097E WHERE (COMPANY_CODE = :company_code) AND ((PRODUCT_PREFIX > :product_prefix) OR (PRODUCT_PREFIX = :product_prefix AND TABLE_SUBSET > :table_subset) OR (PRODUCT_PREFIX = :product_prefix AND TABLE_SUBSET = :table_subset AND EFFECTIVE_DATE > :effective_date) OR (PRODUCT_PREFIX = :product_prefix AND TABLE_SUBSET = :table_subset AND EFFECTIVE_DATE = :effective_date AND DURATION > :duration) OR (PRODUCT_PREFIX = :product_prefix AND TABLE_SUBSET = :table_subset AND EFFECTIVE_DATE = :effective_date AND DURATION = :duration AND CALC_TYPE_IND > :calc_type_ind) OR (PRODUCT_PREFIX = :product_prefix AND TABLE_SUBSET = :table_subset AND EFFECTIVE_DATE = :effective_date AND DURATION = :duration AND CALC_TYPE_IND = :calc_type_ind AND LINE_OF_BUSINESS > :line_of_business) OR (PRODUCT_PREFIX = :product_prefix AND TABLE_SUBSET = :table_subset AND EFFECTIVE_DATE = :effective_date AND DURATION = :duration AND CALC_TYPE_IND = :calc_type_ind AND LINE_OF_BUSINESS = :line_of_business)) ";
                else
                    pagingWhere = ".T097E WHERE (COMPANY_CODE = :company_code) AND ((PRODUCT_PREFIX > :product_prefix) OR (PRODUCT_PREFIX = :product_prefix AND TABLE_SUBSET > :table_subset) OR (PRODUCT_PREFIX = :product_prefix AND TABLE_SUBSET = :table_subset AND EFFECTIVE_DATE > :effective_date) OR (PRODUCT_PREFIX = :product_prefix AND TABLE_SUBSET = :table_subset AND EFFECTIVE_DATE = :effective_date AND DURATION > :duration) OR (PRODUCT_PREFIX = :product_prefix AND TABLE_SUBSET = :table_subset AND EFFECTIVE_DATE = :effective_date AND DURATION = :duration AND CALC_TYPE_IND > :calc_type_ind) OR (PRODUCT_PREFIX = :product_prefix AND TABLE_SUBSET = :table_subset AND EFFECTIVE_DATE = :effective_date AND DURATION = :duration AND CALC_TYPE_IND = :calc_type_ind AND LINE_OF_BUSINESS > :line_of_business)) ";
            order = " ORDER BY COMPANY_CODE, PRODUCT_PREFIX, TABLE_SUBSET, EFFECTIVE_DATE, DURATION, CALC_TYPE_IND, LINE_OF_BUSINESS";
        }
        else {
            if (isSubsetMode)
                pagingWhere = ".T097E WHERE (COMPANY_CODE = :company_code) AND (PRODUCT_PREFIX = :product_prefix) AND (TABLE_SUBSET = :table_subset) AND ((EFFECTIVE_DATE < :effective_date) OR (EFFECTIVE_DATE = :effective_date AND DURATION < :duration) OR (EFFECTIVE_DATE = :effective_date AND DURATION = :duration AND CALC_TYPE_IND < :calc_type_ind) OR (EFFECTIVE_DATE = :effective_date AND DURATION = :duration AND CALC_TYPE_IND = :calc_type_ind AND LINE_OF_BUSINESS < :line_of_business)) ";
            else
                pagingWhere = ".T097E WHERE (COMPANY_CODE = :company_code) AND ((PRODUCT_PREFIX < :product_prefix) OR (PRODUCT_PREFIX = :product_prefix AND TABLE_SUBSET < :table_subset) OR (PRODUCT_PREFIX = :product_prefix AND TABLE_SUBSET = :table_subset AND EFFECTIVE_DATE < :effective_date) OR (PRODUCT_PREFIX = :product_prefix AND TABLE_SUBSET = :table_subset AND EFFECTIVE_DATE = :effective_date AND DURATION < :duration) OR (PRODUCT_PREFIX = :product_prefix AND TABLE_SUBSET = :table_subset AND EFFECTIVE_DATE = :effective_date AND DURATION = :duration AND CALC_TYPE_IND < :calc_type_ind) OR (PRODUCT_PREFIX = :product_prefix AND TABLE_SUBSET = :table_subset AND EFFECTIVE_DATE = :effective_date AND DURATION = :duration AND CALC_TYPE_IND = :calc_type_ind AND LINE_OF_BUSINESS < :line_of_business)) ";
            order = " ORDER BY COMPANY_CODE DESC, PRODUCT_PREFIX DESC, TABLE_SUBSET DESC, EFFECTIVE_DATE DESC, DURATION DESC, CALC_TYPE_IND DESC, LINE_OF_BUSINESS DESC";
        }
        return pagingSql + schemaName + pagingWhere + order;
    }
    
    public String prepareInsertStmt(String schemaName) {
        StringBuffer sb = new StringBuffer();
        sb.append("INSERT INTO ").append(schemaName);
        sb.append(".T097E ( ");
        sb.append("COMPANY_CODE, PRODUCT_PREFIX, TABLE_SUBSET, EFFECTIVE_DATE, DURATION, CALC_TYPE_IND, LINE_OF_BUSINESS, FUND_NUMBER, TOLERANCE_AMOUNT, CURR_GUAR_RT_IND, SPECIAL_CLASS, ADMIN_PAYMENT, SALES_PAYMENT, ADMIN_PAY_TARGET, SALES_PAY_TARGET, TRANSACTION_FEE, INTEREST_SPEC, FIT_GUAR_INT_RATE, COST_OF_RISK, ADMIN_RISK_LOAD, SALES_RISK_LOAD, PCT_GUAR_COI, CONTAGION_FACTOR, ADMIN_SURR_SPEC, SALES_SURR_SPEC, ADMIN_INCREASE, SALES_INCREASE, ADMIN_ANNIVERSARY, SALES_ANNIVERSARY )");
        sb.append(" VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ? )");
        return sb.toString();
    }
    
    public String prepareUpdateStmt(String schemaName)
    {
        StringBuffer sb = new StringBuffer();
        sb.append("UPDATE ").append(schemaName);
        sb.append(".T097E ");
        sb.append(" SET COMPANY_CODE = ?, PRODUCT_PREFIX = ?, TABLE_SUBSET = ?, EFFECTIVE_DATE = ?, DURATION = ?, CALC_TYPE_IND = ?, LINE_OF_BUSINESS = ?, FUND_NUMBER = ?, TOLERANCE_AMOUNT = ?, CURR_GUAR_RT_IND = ?, SPECIAL_CLASS = ?, ADMIN_PAYMENT = ?, SALES_PAYMENT = ?, ADMIN_PAY_TARGET = ?, SALES_PAY_TARGET = ?, TRANSACTION_FEE = ?, INTEREST_SPEC = ?, FIT_GUAR_INT_RATE = ?, COST_OF_RISK = ?, ADMIN_RISK_LOAD = ?, SALES_RISK_LOAD = ?, PCT_GUAR_COI = ?, CONTAGION_FACTOR = ?, ADMIN_SURR_SPEC = ?, SALES_SURR_SPEC = ?, ADMIN_INCREASE = ?, SALES_INCREASE = ?, ADMIN_ANNIVERSARY = ?, SALES_ANNIVERSARY = ?");
        sb.append(" WHERE COMPANY_CODE = ? AND PRODUCT_PREFIX = ? AND TABLE_SUBSET = ? AND EFFECTIVE_DATE = ? AND DURATION = ? AND CALC_TYPE_IND = ? AND LINE_OF_BUSINESS = ?");
        return sb.toString();
    }
    
    public String prepareDeleteStmt(String schemaName)
    {
        StringBuffer sb = new StringBuffer();
        sb.append("DELETE FROM ").append(schemaName);
        sb.append(".T097E ");
        sb.append(" WHERE COMPANY_CODE = ? AND PRODUCT_PREFIX = ? AND TABLE_SUBSET = ? AND EFFECTIVE_DATE = ? AND DURATION = ? AND CALC_TYPE_IND = ? AND LINE_OF_BUSINESS = ?");
        return sb.toString();
    }
}
