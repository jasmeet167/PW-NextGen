package com.csc.fsg.life.pw.io.descriptor.wma;

import com.csc.fsg.life.pw.io.*;

/* Modifications: ENH1112 */

public class T046EDescriptor
    extends TableDescriptor
{
    private static final String pagingSql = "SELECT COMPANY_CODE, TABLE_SUBSET, EFFECTIVE_DATE, MAX_POLICY_DUR, PREF_LN_CALC_METH, MIN_NUMBER_MONTHS, PREF_LOAN_CAP_METH, LN_REPY_METH_IND, PREF_LOAN_INT_TBL, PREF_COLL_INT_TBL, LOAN_TREAT_NLG, PCT_INIT_PREM_TBL, PCT_INIT_PREM_FND, PCT_INIT_PREM, MAX_PREF_LOAN_ALOW, PREF_ACT_REQ_DUR, PREF_PERCENT_OF_ACV, RECLASSIFY_TO_REG_IND, RECLASSIFY_TIMING FROM ";
    
    public void initialize()
    {
        setRowClass(T046ERow.class);
        setTableName("T046E");
        setTableId("046");
        super.initialize();
    }
    
    public void initializeColumnDescriptors()
    {
        super.initializeColumnDescriptors();
        addColumnDescriptor(new ColumnDescriptor(this,"getCompanyCode","setCompanyCode","COMPANY_CODE,1,1,3,0,true|,0,CHAR,1,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getTableSubset","setTableSubset","TABLE_SUBSET,1,2,16,0,true|,0,CHAR,1,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getEffectiveDate","setEffectiveDate","EFFECTIVE_DATE,91,3,10,0,true|,0,DATE,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getMaxPolicyDur","setMaxPolicyDur","MAX_POLICY_DUR,3,4,3,0,true|,0,INTEGER,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getPrefLnCalcMeth","setPrefLnCalcMeth","PREF_LN_CALC_METH,1,5,1,0,false|,0,CHAR,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getMinNumberMonths","setMinNumberMonths","MIN_NUMBER_MONTHS,3,6,3,0,false|,0,INTEGER,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getPrefLoanCapMeth","setPrefLoanCapMeth","PREF_LOAN_CAP_METH,1,7,1,0,false|,0,CHAR,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getLnRepyMethInd","setLnRepyMethInd","LN_REPY_METH_IND,1,8,1,0,false|,0,CHAR,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getPrefLoanIntTbl","setPrefLoanIntTbl","PREF_LOAN_INT_TBL,1,9,16,0,false|,0,CHAR,0,024,1,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getPrefCollIntTbl","setPrefCollIntTbl","PREF_COLL_INT_TBL,1,10,16,0,false|,0,CHAR,0,024,2,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getLoanTreatNlg","setLoanTreatNlg","LOAN_TREAT_NLG,1,11,1,0,false|,0,CHAR,2,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getPctInitPremTbl","setPctInitPremTbl","PCT_INIT_PREM_TBL,1,12,16,0,false|,0,CHAR,0,024,3,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getPctInitPremFnd","setPctInitPremFnd","PCT_INIT_PREM_FND,3,13,8,0,false|,0,INTEGER,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getPctInitPrem","setPctInitPrem","PCT_INIT_PREM,3,14,5,2,false|,0,DOUBLE,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getMaxPrefLoanAlow","setMaxPrefLoanAlow","MAX_PREF_LOAN_ALOW,3,15,3,0,false|,0,INTEGER,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getPrefActReqDur","setPrefActReqDur","PREF_ACT_REQ_DUR,3,16,3,0,false|,0,INTEGER,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getPrefPercentOfAcv","setPrefPercentOfAcv","PREF_PERCENT_OF_ACV,3,17,5,2,false|,0,DOUBLE,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getReclassifyToRegInd","setReclassifyToRegInd","RECLASSIFY_TO_REG_IND,1,18,1,0,false|,0,CHAR,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getReclassifyTiming","setReclassifyTiming","RECLASSIFY_TIMING,1,19,1,0,false|,0,CHAR,0,null,null,null,null,null"));
    }
    
    public String getPagingSQL(String schemaName,boolean isSubsetMode,boolean isNext, boolean locator)
    {
        String pagingWhere = null;
        String order = null;
        if (isNext) {
            if (isSubsetMode)
                if (locator)
                    pagingWhere = ".T046E WHERE (COMPANY_CODE = :company_code) AND (TABLE_SUBSET = :table_subset) AND ((EFFECTIVE_DATE > :effective_date) OR (EFFECTIVE_DATE = :effective_date AND MAX_POLICY_DUR > :max_policy_dur) OR (EFFECTIVE_DATE = :effective_date AND MAX_POLICY_DUR = :max_policy_dur)) ";
                else 
                    pagingWhere = ".T046E WHERE (COMPANY_CODE = :company_code) AND (TABLE_SUBSET = :table_subset) AND ((EFFECTIVE_DATE > :effective_date) OR (EFFECTIVE_DATE = :effective_date AND MAX_POLICY_DUR > :max_policy_dur)) ";
            else
                if (locator)
                    pagingWhere = ".T046E WHERE (COMPANY_CODE = :company_code) AND ((TABLE_SUBSET > :table_subset) OR (TABLE_SUBSET = :table_subset AND EFFECTIVE_DATE > :effective_date) OR (TABLE_SUBSET = :table_subset AND EFFECTIVE_DATE = :effective_date AND MAX_POLICY_DUR > :max_policy_dur) OR (TABLE_SUBSET = :table_subset AND EFFECTIVE_DATE = :effective_date AND MAX_POLICY_DUR = :max_policy_dur)) ";
                else
                    pagingWhere = ".T046E WHERE (COMPANY_CODE = :company_code) AND ((TABLE_SUBSET > :table_subset) OR (TABLE_SUBSET = :table_subset AND EFFECTIVE_DATE > :effective_date) OR (TABLE_SUBSET = :table_subset AND EFFECTIVE_DATE = :effective_date AND MAX_POLICY_DUR > :max_policy_dur)) ";
            order = " ORDER BY COMPANY_CODE, TABLE_SUBSET, EFFECTIVE_DATE, MAX_POLICY_DUR";
        }
        else {
            if (isSubsetMode)
                pagingWhere = ".T046E WHERE (COMPANY_CODE = :company_code) AND (TABLE_SUBSET = :table_subset) AND ((EFFECTIVE_DATE < :effective_date) OR (EFFECTIVE_DATE = :effective_date AND MAX_POLICY_DUR < :max_policy_dur)) ";
            else
                pagingWhere = ".T046E WHERE (COMPANY_CODE = :company_code) AND ((TABLE_SUBSET < :table_subset) OR (TABLE_SUBSET = :table_subset AND EFFECTIVE_DATE < :effective_date) OR (TABLE_SUBSET = :table_subset AND EFFECTIVE_DATE = :effective_date AND MAX_POLICY_DUR < :max_policy_dur)) ";
            order = " ORDER BY COMPANY_CODE DESC, TABLE_SUBSET DESC, EFFECTIVE_DATE DESC, MAX_POLICY_DUR DESC";
        }
        return pagingSql + schemaName + pagingWhere + order;
    }
    
    public String prepareInsertStmt(String schemaName) {
        StringBuffer sb = new StringBuffer();
        sb.append("INSERT INTO ").append(schemaName);
        sb.append(".T046E ( ");
        sb.append("COMPANY_CODE, TABLE_SUBSET, EFFECTIVE_DATE, MAX_POLICY_DUR, PREF_LN_CALC_METH, MIN_NUMBER_MONTHS, PREF_LOAN_CAP_METH, LN_REPY_METH_IND, PREF_LOAN_INT_TBL, PREF_COLL_INT_TBL, LOAN_TREAT_NLG, PCT_INIT_PREM_TBL, PCT_INIT_PREM_FND, PCT_INIT_PREM, MAX_PREF_LOAN_ALOW, PREF_ACT_REQ_DUR, PREF_PERCENT_OF_ACV, RECLASSIFY_TO_REG_IND, RECLASSIFY_TIMING )");
        sb.append(" VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ? )");
        return sb.toString();
    }
    
    public String prepareUpdateStmt(String schemaName)
    {
        StringBuffer sb = new StringBuffer();
        sb.append("UPDATE ").append(schemaName);
        sb.append(".T046E ");
        sb.append(" SET COMPANY_CODE = ?, TABLE_SUBSET = ?, EFFECTIVE_DATE = ?, MAX_POLICY_DUR = ?, PREF_LN_CALC_METH = ?, MIN_NUMBER_MONTHS = ?, PREF_LOAN_CAP_METH = ?, LN_REPY_METH_IND = ?, PREF_LOAN_INT_TBL = ?, PREF_COLL_INT_TBL = ?, LOAN_TREAT_NLG = ?, PCT_INIT_PREM_TBL = ?, PCT_INIT_PREM_FND = ?, PCT_INIT_PREM = ?, MAX_PREF_LOAN_ALOW = ?, PREF_ACT_REQ_DUR = ?, PREF_PERCENT_OF_ACV = ?, RECLASSIFY_TO_REG_IND = ?, RECLASSIFY_TIMING = ?");
        sb.append(" WHERE COMPANY_CODE = ? AND TABLE_SUBSET = ? AND EFFECTIVE_DATE = ? AND MAX_POLICY_DUR = ?");
        return sb.toString();
    }
    
    public String prepareDeleteStmt(String schemaName)
    {
        StringBuffer sb = new StringBuffer();
        sb.append("DELETE FROM ").append(schemaName);
        sb.append(".T046E ");
        sb.append(" WHERE COMPANY_CODE = ? AND TABLE_SUBSET = ? AND EFFECTIVE_DATE = ? AND MAX_POLICY_DUR = ?");
        return sb.toString();
    }
}
