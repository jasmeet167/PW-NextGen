package com.csc.fsg.life.pw.io.descriptor.wma;

import com.csc.fsg.life.pw.io.*;

public class TAE4FDescriptor
    extends TableDescriptor
{
    private static final String pagingSql = "SELECT COMPANY_CODE, TABLE_SUBSET, STAT_COMPANY, EFFECTIVE_DATE, POLICY_DUR, ASEQ_INTEREST_RATE FROM ";
    
    public void initialize()
    {
        setRowClass(TAE4FRow.class);
        setTableName("TAE4F");
        setTableId("AE4");
        super.initialize();
    }
    
    public void initializeColumnDescriptors()
    {
        super.initializeColumnDescriptors();
        addColumnDescriptor(new ColumnDescriptor(this,"getCompanyCode","setCompanyCode","COMPANY_CODE,1,1,3,0,true|,0,CHAR,1,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getTableSubset","setTableSubset","TABLE_SUBSET,1,2,16,0,true|,0,CHAR,1,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getStatCompany","setStatCompany","STAT_COMPANY,1,3,3,0,true|,0,CHAR,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getEffectiveDate","setEffectiveDate","EFFECTIVE_DATE,91,4,10,0,true|,0,DATE,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getPolicyDur","setPolicyDur","POLICY_DUR,3,5,3,0,true|,0,INTEGER,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getAseqInterestRate","setAseqInterestRate","ASEQ_INTEREST_RATE,3,6,5,3,false|,0,DOUBLE,0,null,null,null,null,null"));
    }
    
    public String getPagingSQL(String schemaName,boolean isSubsetMode,boolean isNext, boolean locator)
    {
        String pagingWhere = null;
        String order = null;
        if (isNext) {
            if (isSubsetMode)
                if (locator)
                    pagingWhere = ".TAE4F WHERE (COMPANY_CODE = :company_code) AND (TABLE_SUBSET = :table_subset) AND ((STAT_COMPANY > :stat_company) OR (STAT_COMPANY = :stat_company AND EFFECTIVE_DATE > :effective_date) OR (STAT_COMPANY = :stat_company AND EFFECTIVE_DATE = :effective_date AND POLICY_DUR > :policy_dur) OR (STAT_COMPANY = :stat_company AND EFFECTIVE_DATE = :effective_date AND POLICY_DUR = :policy_dur)) ";
                else 
                    pagingWhere = ".TAE4F WHERE (COMPANY_CODE = :company_code) AND (TABLE_SUBSET = :table_subset) AND ((STAT_COMPANY > :stat_company) OR (STAT_COMPANY = :stat_company AND EFFECTIVE_DATE > :effective_date) OR (STAT_COMPANY = :stat_company AND EFFECTIVE_DATE = :effective_date AND POLICY_DUR > :policy_dur)) ";
            else
                if (locator)
                    pagingWhere = ".TAE4F WHERE (COMPANY_CODE = :company_code) AND ((TABLE_SUBSET > :table_subset) OR (TABLE_SUBSET = :table_subset AND STAT_COMPANY > :stat_company) OR (TABLE_SUBSET = :table_subset AND STAT_COMPANY = :stat_company AND EFFECTIVE_DATE > :effective_date) OR (TABLE_SUBSET = :table_subset AND STAT_COMPANY = :stat_company AND EFFECTIVE_DATE = :effective_date AND POLICY_DUR > :policy_dur) OR (TABLE_SUBSET = :table_subset AND STAT_COMPANY = :stat_company AND EFFECTIVE_DATE = :effective_date AND POLICY_DUR = :policy_dur)) ";
                else
                    pagingWhere = ".TAE4F WHERE (COMPANY_CODE = :company_code) AND ((TABLE_SUBSET > :table_subset) OR (TABLE_SUBSET = :table_subset AND STAT_COMPANY > :stat_company) OR (TABLE_SUBSET = :table_subset AND STAT_COMPANY = :stat_company AND EFFECTIVE_DATE > :effective_date) OR (TABLE_SUBSET = :table_subset AND STAT_COMPANY = :stat_company AND EFFECTIVE_DATE = :effective_date AND POLICY_DUR > :policy_dur)) ";
            order = " ORDER BY COMPANY_CODE, TABLE_SUBSET, STAT_COMPANY, EFFECTIVE_DATE, POLICY_DUR";
        }
        else {
            if (isSubsetMode)
                pagingWhere = ".TAE4F WHERE (COMPANY_CODE = :company_code) AND (TABLE_SUBSET = :table_subset) AND ((STAT_COMPANY < :stat_company) OR (STAT_COMPANY = :stat_company AND EFFECTIVE_DATE < :effective_date) OR (STAT_COMPANY = :stat_company AND EFFECTIVE_DATE = :effective_date AND POLICY_DUR < :policy_dur)) ";
            else
                pagingWhere = ".TAE4F WHERE (COMPANY_CODE = :company_code) AND ((TABLE_SUBSET < :table_subset) OR (TABLE_SUBSET = :table_subset AND STAT_COMPANY < :stat_company) OR (TABLE_SUBSET = :table_subset AND STAT_COMPANY = :stat_company AND EFFECTIVE_DATE < :effective_date) OR (TABLE_SUBSET = :table_subset AND STAT_COMPANY = :stat_company AND EFFECTIVE_DATE = :effective_date AND POLICY_DUR < :policy_dur)) ";
            order = " ORDER BY COMPANY_CODE DESC, TABLE_SUBSET DESC, STAT_COMPANY DESC, EFFECTIVE_DATE DESC, POLICY_DUR DESC";
        }
        return pagingSql + schemaName + pagingWhere + order;
    }
    
    public String prepareInsertStmt(String schemaName) {
        StringBuffer sb = new StringBuffer();
        sb.append("INSERT INTO ").append(schemaName);
        sb.append(".TAE4F ( ");
        sb.append("COMPANY_CODE, TABLE_SUBSET, STAT_COMPANY, EFFECTIVE_DATE, POLICY_DUR, ASEQ_INTEREST_RATE )");
        sb.append(" VALUES (?, ?, ?, ?, ?, ? )");
        return sb.toString();
    }
    
    public String prepareUpdateStmt(String schemaName)
    {
        StringBuffer sb = new StringBuffer();
        sb.append("UPDATE ").append(schemaName);
        sb.append(".TAE4F ");
        sb.append(" SET COMPANY_CODE = ?, TABLE_SUBSET = ?, STAT_COMPANY = ?, EFFECTIVE_DATE = ?, POLICY_DUR = ?, ASEQ_INTEREST_RATE = ?");
        sb.append(" WHERE COMPANY_CODE = ? AND TABLE_SUBSET = ? AND STAT_COMPANY = ? AND EFFECTIVE_DATE = ? AND POLICY_DUR = ?");
        return sb.toString();
    }
    
    public String prepareDeleteStmt(String schemaName)
    {
        StringBuffer sb = new StringBuffer();
        sb.append("DELETE FROM ").append(schemaName);
        sb.append(".TAE4F ");
        sb.append(" WHERE COMPANY_CODE = ? AND TABLE_SUBSET = ? AND STAT_COMPANY = ? AND EFFECTIVE_DATE = ? AND POLICY_DUR = ?");
        return sb.toString();
    }
}
