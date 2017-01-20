package com.csc.fsg.life.pw.io.descriptor.wma;

import com.csc.fsg.life.pw.io.*;

public class TT82TDescriptor
    extends TableDescriptor
{
    private static final String pagingSql = "SELECT COMPANY_CODE, TABLE_SUBSET, STATE_CODE, EFFECTIVE_DATE, COST_DISC_STMTIND, NON_FRFIT_VALSCH_I, PREM_SCHED_IND, DEATH_BEN_SCH_IND, FORM_NUMBER, FORMS_DESC_1, FORMS_DESC_2, FORMS_DESC_3, FORMS_DESC_4, FORMS_DESC_5, FORMS_DESC_6, FORMS_DESC_7, FORMS_DESC_8 FROM ";
    
    public void initialize()
    {
        setRowClass(TT82TRow.class);
        setTableName("TT82T");
        setTableId("T82");
        super.initialize();
    }
    
    public void initializeColumnDescriptors()
    {
        super.initializeColumnDescriptors();
        addColumnDescriptor(new ColumnDescriptor(this,"getCompanyCode","setCompanyCode","COMPANY_CODE,1,1,3,0,true|,0,CHAR,1,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getTableSubset","setTableSubset","TABLE_SUBSET,1,2,16,0,true|,0,CHAR,1,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getStateCode","setStateCode","STATE_CODE,1,3,3,0,true|,0,CHAR,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getEffectiveDate","setEffectiveDate","EFFECTIVE_DATE,91,4,10,0,true|,0,DATE,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getCostDiscStmtind","setCostDiscStmtind","COST_DISC_STMTIND,1,5,1,0,false|,0,CHAR,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getNonFrfitValschI","setNonFrfitValschI","NON_FRFIT_VALSCH_I,1,6,1,0,false|,0,CHAR,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getPremSchedInd","setPremSchedInd","PREM_SCHED_IND,1,7,1,0,false|,0,CHAR,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getDeathBenSchInd","setDeathBenSchInd","DEATH_BEN_SCH_IND,1,8,1,0,false|,0,CHAR,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getFormNumber","setFormNumber","FORM_NUMBER,1,9,35,0,false|,0,CHAR,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getFormsDesc1","setFormsDesc1","FORMS_DESC_1,1,10,40,0,false|,0,CHAR,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getFormsDesc2","setFormsDesc2","FORMS_DESC_2,1,11,40,0,false|,0,CHAR,4,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getFormsDesc3","setFormsDesc3","FORMS_DESC_3,1,12,40,0,false|,0,CHAR,4,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getFormsDesc4","setFormsDesc4","FORMS_DESC_4,1,13,40,0,false|,0,CHAR,4,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getFormsDesc5","setFormsDesc5","FORMS_DESC_5,1,14,40,0,false|,0,CHAR,4,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getFormsDesc6","setFormsDesc6","FORMS_DESC_6,1,15,40,0,false|,0,CHAR,4,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getFormsDesc7","setFormsDesc7","FORMS_DESC_7,1,16,40,0,false|,0,CHAR,4,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getFormsDesc8","setFormsDesc8","FORMS_DESC_8,1,17,40,0,false|,0,CHAR,4,null,null,null,null,null"));
    }
    
    public String getPagingSQL(String schemaName,boolean isSubsetMode,boolean isNext, boolean locator)
    {
        String pagingWhere = null;
        String order = null;
        if (isNext) {
            if (isSubsetMode)
                if (locator)
                    pagingWhere = ".TT82T WHERE (COMPANY_CODE = :company_code) AND (TABLE_SUBSET = :table_subset) AND ((STATE_CODE > :state_code) OR (STATE_CODE = :state_code AND EFFECTIVE_DATE > :effective_date) OR (STATE_CODE = :state_code AND EFFECTIVE_DATE = :effective_date)) ";
                else 
                    pagingWhere = ".TT82T WHERE (COMPANY_CODE = :company_code) AND (TABLE_SUBSET = :table_subset) AND ((STATE_CODE > :state_code) OR (STATE_CODE = :state_code AND EFFECTIVE_DATE > :effective_date)) ";
            else
                if (locator)
                    pagingWhere = ".TT82T WHERE (COMPANY_CODE = :company_code) AND ((TABLE_SUBSET > :table_subset) OR (TABLE_SUBSET = :table_subset AND STATE_CODE > :state_code) OR (TABLE_SUBSET = :table_subset AND STATE_CODE = :state_code AND EFFECTIVE_DATE > :effective_date) OR (TABLE_SUBSET = :table_subset AND STATE_CODE = :state_code AND EFFECTIVE_DATE = :effective_date)) ";
                else
                    pagingWhere = ".TT82T WHERE (COMPANY_CODE = :company_code) AND ((TABLE_SUBSET > :table_subset) OR (TABLE_SUBSET = :table_subset AND STATE_CODE > :state_code) OR (TABLE_SUBSET = :table_subset AND STATE_CODE = :state_code AND EFFECTIVE_DATE > :effective_date)) ";
            order = " ORDER BY COMPANY_CODE, TABLE_SUBSET, STATE_CODE, EFFECTIVE_DATE";
        }
        else {
            if (isSubsetMode)
                pagingWhere = ".TT82T WHERE (COMPANY_CODE = :company_code) AND (TABLE_SUBSET = :table_subset) AND ((STATE_CODE < :state_code) OR (STATE_CODE = :state_code AND EFFECTIVE_DATE < :effective_date)) ";
            else
                pagingWhere = ".TT82T WHERE (COMPANY_CODE = :company_code) AND ((TABLE_SUBSET < :table_subset) OR (TABLE_SUBSET = :table_subset AND STATE_CODE < :state_code) OR (TABLE_SUBSET = :table_subset AND STATE_CODE = :state_code AND EFFECTIVE_DATE < :effective_date)) ";
            order = " ORDER BY COMPANY_CODE DESC, TABLE_SUBSET DESC, STATE_CODE DESC, EFFECTIVE_DATE DESC";
        }
        return pagingSql + schemaName + pagingWhere + order;
    }
    
    public String prepareInsertStmt(String schemaName) {
        StringBuffer sb = new StringBuffer();
        sb.append("INSERT INTO ").append(schemaName);
        sb.append(".TT82T ( ");
        sb.append("COMPANY_CODE, TABLE_SUBSET, STATE_CODE, EFFECTIVE_DATE, COST_DISC_STMTIND, NON_FRFIT_VALSCH_I, PREM_SCHED_IND, DEATH_BEN_SCH_IND, FORM_NUMBER, FORMS_DESC_1, FORMS_DESC_2, FORMS_DESC_3, FORMS_DESC_4, FORMS_DESC_5, FORMS_DESC_6, FORMS_DESC_7, FORMS_DESC_8 )");
        sb.append(" VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ? )");
        return sb.toString();
    }
    
    public String prepareUpdateStmt(String schemaName)
    {
        StringBuffer sb = new StringBuffer();
        sb.append("UPDATE ").append(schemaName);
        sb.append(".TT82T ");
        sb.append(" SET COMPANY_CODE = ?, TABLE_SUBSET = ?, STATE_CODE = ?, EFFECTIVE_DATE = ?, COST_DISC_STMTIND = ?, NON_FRFIT_VALSCH_I = ?, PREM_SCHED_IND = ?, DEATH_BEN_SCH_IND = ?, FORM_NUMBER = ?, FORMS_DESC_1 = ?, FORMS_DESC_2 = ?, FORMS_DESC_3 = ?, FORMS_DESC_4 = ?, FORMS_DESC_5 = ?, FORMS_DESC_6 = ?, FORMS_DESC_7 = ?, FORMS_DESC_8 = ?");
        sb.append(" WHERE COMPANY_CODE = ? AND TABLE_SUBSET = ? AND STATE_CODE = ? AND EFFECTIVE_DATE = ?");
        return sb.toString();
    }
    
    public String prepareDeleteStmt(String schemaName)
    {
        StringBuffer sb = new StringBuffer();
        sb.append("DELETE FROM ").append(schemaName);
        sb.append(".TT82T ");
        sb.append(" WHERE COMPANY_CODE = ? AND TABLE_SUBSET = ? AND STATE_CODE = ? AND EFFECTIVE_DATE = ?");
        return sb.toString();
    }
}
