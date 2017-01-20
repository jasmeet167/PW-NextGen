package com.csc.fsg.life.pw.io.descriptor.wma;

import com.csc.fsg.life.pw.io.*;

public class T088XDescriptor
    extends TableDescriptor
{
    private static final String pagingSql = "SELECT COMPANY_CODE, PRODUCT_PREFIX, TABLE_SUBSET, EFFECTIVE_DATE, MAX_NBR_INSUREDS, PCR_DAYS, JOINT_EQ_AGE_CALC, AVG_AGE_LIMIT, MAX_IND_ISSUE_AGE, MIN_IND_ISSUE_AGE, CONTAGION_AGE_METH, PERCENT_COI_METHOD, PAYMENT_LOAD_METH, PAY_LOAD_TARG_METH, RISK_LOAD_METHOD, SURR_CHARGE_METH, SURR_TARGET_METH, CORRIDOR_METHOD, COMM_LD_TARG_METH, MIN_PAY_TARG_METH, INCREASE_LOAD_METH, ANNIV_LOAD_METHOD, MIN_COVERAGE_UNITS, GAAP_RSRV_AGE_METH, COST_OF_INSUR_METH, MATURE_AGE_CALC, FREE_LOOK_AGE_IND, PREM_DB_AGE_BASIS FROM ";
    
    public void initialize()
    {
        setRowClass(T088XRow.class);
        setTableName("T088X");
        setTableId("088");
        super.initialize();
    }
    
    public void initializeColumnDescriptors()
    {
        super.initializeColumnDescriptors();
        addColumnDescriptor(new ColumnDescriptor(this,"getCompanyCode","setCompanyCode","COMPANY_CODE,1,1,3,0,true|T,0,CHAR,1,null,null,null,null,null|U,0,CHAR,1,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getProductPrefix","setProductPrefix","PRODUCT_PREFIX,1,2,1,0,true|T,0,CHAR,1,null,null,null,null,null|U,0,CHAR,1,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getTableSubset","setTableSubset","TABLE_SUBSET,1,3,16,0,true|T,0,CHAR,1,null,null,null,null,null|U,0,CHAR,1,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getEffectiveDate","setEffectiveDate","EFFECTIVE_DATE,91,4,10,0,true|T,0,DATE,0,null,null,null,null,null|U,0,DATE,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getMaxNbrInsureds","setMaxNbrInsureds","MAX_NBR_INSUREDS,3,5,2,0,false|T,0,INTEGER,0,null,null,null,null,null|U,0,INTEGER,2,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getPcrDays","setPcrDays","PCR_DAYS,3,6,3,0,false|T,0,INTEGER,0,null,null,null,null,null|U,0,INTEGER,2,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getJointEqAgeCalc","setJointEqAgeCalc","JOINT_EQ_AGE_CALC,1,7,1,0,false|T,0,CHAR,2,null,null,null,null,null|U,0,CHAR,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getAvgAgeLimit","setAvgAgeLimit","AVG_AGE_LIMIT,3,8,3,0,false|T,0,INTEGER,2,null,null,null,null,null|U,0,INTEGER,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getMaxIndIssueAge","setMaxIndIssueAge","MAX_IND_ISSUE_AGE,3,9,3,0,false|T,0,INTEGER,0,null,null,null,null,null|U,0,INTEGER,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getMinIndIssueAge","setMinIndIssueAge","MIN_IND_ISSUE_AGE,3,10,3,0,false|T,0,INTEGER,0,null,null,null,null,null|U,0,INTEGER,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getContagionAgeMeth","setContagionAgeMeth","CONTAGION_AGE_METH,1,11,1,0,false|T,0,CHAR,2,null,null,null,null,null|U,0,CHAR,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getPercentCoiMethod","setPercentCoiMethod","PERCENT_COI_METHOD,1,12,1,0,false|T,0,CHAR,2,null,null,null,null,null|U,0,CHAR,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getPaymentLoadMeth","setPaymentLoadMeth","PAYMENT_LOAD_METH,1,13,1,0,false|T,0,CHAR,2,null,null,null,null,null|U,0,CHAR,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getPayLoadTargMeth","setPayLoadTargMeth","PAY_LOAD_TARG_METH,1,14,1,0,false|T,0,CHAR,2,null,null,null,null,null|U,0,CHAR,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getRiskLoadMethod","setRiskLoadMethod","RISK_LOAD_METHOD,1,15,1,0,false|T,0,CHAR,2,null,null,null,null,null|U,0,CHAR,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getSurrChargeMeth","setSurrChargeMeth","SURR_CHARGE_METH,1,16,1,0,false|T,0,CHAR,2,null,null,null,null,null|U,0,CHAR,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getSurrTargetMeth","setSurrTargetMeth","SURR_TARGET_METH,1,17,1,0,false|T,0,CHAR,2,null,null,null,null,null|U,0,CHAR,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getCorridorMethod","setCorridorMethod","CORRIDOR_METHOD,1,18,1,0,false|T,0,CHAR,2,null,null,null,null,null|U,0,CHAR,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getCommLdTargMeth","setCommLdTargMeth","COMM_LD_TARG_METH,1,19,1,0,false|T,0,CHAR,2,null,null,null,null,null|U,0,CHAR,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getMinPayTargMeth","setMinPayTargMeth","MIN_PAY_TARG_METH,1,20,1,0,false|T,0,CHAR,2,null,null,null,null,null|U,0,CHAR,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getIncreaseLoadMeth","setIncreaseLoadMeth","INCREASE_LOAD_METH,1,21,1,0,false|T,0,CHAR,2,null,null,null,null,null|U,0,CHAR,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getAnnivLoadMethod","setAnnivLoadMethod","ANNIV_LOAD_METHOD,1,22,1,0,false|T,0,CHAR,2,null,null,null,null,null|U,0,CHAR,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getMinCoverageUnits","setMinCoverageUnits","MIN_COVERAGE_UNITS,1,23,1,0,false|T,0,CHAR,2,null,null,null,null,null|U,0,CHAR,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getGaapRsrvAgeMeth","setGaapRsrvAgeMeth","GAAP_RSRV_AGE_METH,1,24,1,0,false|T,0,CHAR,2,null,null,null,null,null|U,0,CHAR,2,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getCostOfInsurMeth","setCostOfInsurMeth","COST_OF_INSUR_METH,1,25,1,0,false|T,0,CHAR,2,null,null,null,null,null|U,0,CHAR,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getMatureAgeCalc","setMatureAgeCalc","MATURE_AGE_CALC,1,26,1,0,false|T,0,CHAR,2,null,null,null,null,null|U,0,CHAR,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getFreeLookAgeInd","setFreeLookAgeInd","FREE_LOOK_AGE_IND,1,27,1,0,false|T,0,CHAR,2,null,null,null,null,null|U,0,CHAR,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getPremDbAgeBasis","setPremDbAgeBasis","PREM_DB_AGE_BASIS,1,28,1,0,false|T,0,CHAR,2,null,null,null,null,null|U,0,CHAR,0,null,null,null,null,null"));
    }
    
    public String getPagingSQL(String schemaName,boolean isSubsetMode,boolean isNext, boolean locator)
    {
        String pagingWhere = null;
        String order = null;
        if (isNext) {
            if (isSubsetMode)
                if (locator)
                    pagingWhere = ".T088X WHERE (COMPANY_CODE = :company_code) AND (PRODUCT_PREFIX = :product_prefix) AND (TABLE_SUBSET = :table_subset) AND ((EFFECTIVE_DATE > :effective_date) OR (EFFECTIVE_DATE = :effective_date)) ";
                else 
                    pagingWhere = ".T088X WHERE (COMPANY_CODE = :company_code) AND (PRODUCT_PREFIX = :product_prefix) AND (TABLE_SUBSET = :table_subset) AND ((EFFECTIVE_DATE > :effective_date)) ";
            else
                if (locator)
                    pagingWhere = ".T088X WHERE (COMPANY_CODE = :company_code) AND ((PRODUCT_PREFIX > :product_prefix) OR (PRODUCT_PREFIX = :product_prefix AND TABLE_SUBSET > :table_subset) OR (PRODUCT_PREFIX = :product_prefix AND TABLE_SUBSET = :table_subset AND EFFECTIVE_DATE > :effective_date) OR (PRODUCT_PREFIX = :product_prefix AND TABLE_SUBSET = :table_subset AND EFFECTIVE_DATE = :effective_date)) ";
                else
                    pagingWhere = ".T088X WHERE (COMPANY_CODE = :company_code) AND ((PRODUCT_PREFIX > :product_prefix) OR (PRODUCT_PREFIX = :product_prefix AND TABLE_SUBSET > :table_subset) OR (PRODUCT_PREFIX = :product_prefix AND TABLE_SUBSET = :table_subset AND EFFECTIVE_DATE > :effective_date)) ";
            order = " ORDER BY COMPANY_CODE, PRODUCT_PREFIX, TABLE_SUBSET, EFFECTIVE_DATE";
        }
        else {
            if (isSubsetMode)
                pagingWhere = ".T088X WHERE (COMPANY_CODE = :company_code) AND (PRODUCT_PREFIX = :product_prefix) AND (TABLE_SUBSET = :table_subset) AND ((EFFECTIVE_DATE < :effective_date)) ";
            else
                pagingWhere = ".T088X WHERE (COMPANY_CODE = :company_code) AND ((PRODUCT_PREFIX < :product_prefix) OR (PRODUCT_PREFIX = :product_prefix AND TABLE_SUBSET < :table_subset) OR (PRODUCT_PREFIX = :product_prefix AND TABLE_SUBSET = :table_subset AND EFFECTIVE_DATE < :effective_date)) ";
            order = " ORDER BY COMPANY_CODE DESC, PRODUCT_PREFIX DESC, TABLE_SUBSET DESC, EFFECTIVE_DATE DESC";
        }
        return pagingSql + schemaName + pagingWhere + order;
    }
    
    public String prepareInsertStmt(String schemaName) {
        StringBuffer sb = new StringBuffer();
        sb.append("INSERT INTO ").append(schemaName);
        sb.append(".T088X ( ");
        sb.append("COMPANY_CODE, PRODUCT_PREFIX, TABLE_SUBSET, EFFECTIVE_DATE, MAX_NBR_INSUREDS, PCR_DAYS, JOINT_EQ_AGE_CALC, AVG_AGE_LIMIT, MAX_IND_ISSUE_AGE, MIN_IND_ISSUE_AGE, CONTAGION_AGE_METH, PERCENT_COI_METHOD, PAYMENT_LOAD_METH, PAY_LOAD_TARG_METH, RISK_LOAD_METHOD, SURR_CHARGE_METH, SURR_TARGET_METH, CORRIDOR_METHOD, COMM_LD_TARG_METH, MIN_PAY_TARG_METH, INCREASE_LOAD_METH, ANNIV_LOAD_METHOD, MIN_COVERAGE_UNITS, GAAP_RSRV_AGE_METH, COST_OF_INSUR_METH, MATURE_AGE_CALC, FREE_LOOK_AGE_IND, PREM_DB_AGE_BASIS )");
        sb.append(" VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ? )");
        return sb.toString();
    }
    
    public String prepareUpdateStmt(String schemaName)
    {
        StringBuffer sb = new StringBuffer();
        sb.append("UPDATE ").append(schemaName);
        sb.append(".T088X ");
        sb.append(" SET COMPANY_CODE = ?, PRODUCT_PREFIX = ?, TABLE_SUBSET = ?, EFFECTIVE_DATE = ?, MAX_NBR_INSUREDS = ?, PCR_DAYS = ?, JOINT_EQ_AGE_CALC = ?, AVG_AGE_LIMIT = ?, MAX_IND_ISSUE_AGE = ?, MIN_IND_ISSUE_AGE = ?, CONTAGION_AGE_METH = ?, PERCENT_COI_METHOD = ?, PAYMENT_LOAD_METH = ?, PAY_LOAD_TARG_METH = ?, RISK_LOAD_METHOD = ?, SURR_CHARGE_METH = ?, SURR_TARGET_METH = ?, CORRIDOR_METHOD = ?, COMM_LD_TARG_METH = ?, MIN_PAY_TARG_METH = ?, INCREASE_LOAD_METH = ?, ANNIV_LOAD_METHOD = ?, MIN_COVERAGE_UNITS = ?, GAAP_RSRV_AGE_METH = ?, COST_OF_INSUR_METH = ?, MATURE_AGE_CALC = ?, FREE_LOOK_AGE_IND = ?, PREM_DB_AGE_BASIS = ?");
        sb.append(" WHERE COMPANY_CODE = ? AND PRODUCT_PREFIX = ? AND TABLE_SUBSET = ? AND EFFECTIVE_DATE = ?");
        return sb.toString();
    }
    
    public String prepareDeleteStmt(String schemaName)
    {
        StringBuffer sb = new StringBuffer();
        sb.append("DELETE FROM ").append(schemaName);
        sb.append(".T088X ");
        sb.append(" WHERE COMPANY_CODE = ? AND PRODUCT_PREFIX = ? AND TABLE_SUBSET = ? AND EFFECTIVE_DATE = ?");
        return sb.toString();
    }
}
