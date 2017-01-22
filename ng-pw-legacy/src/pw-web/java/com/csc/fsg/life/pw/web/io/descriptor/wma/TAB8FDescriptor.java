package com.csc.fsg.life.pw.web.io.descriptor.wma;

import com.csc.fsg.life.pw.web.io.*;

public class TAB8FDescriptor
    extends TableDescriptor
{
    private static final String pagingSql = "SELECT COMPANY_CODE, TABLE_SUBSET, EFFECTIVE_DATE, ISSUE_AGE, SEX_CODE, UNDRWRTNG_BASIS_CD, MEMO_CODE, PCTG_OF_PREM FROM ";
    
    public void initialize()
    {
        setRowClass(TAB8FRow.class);
        setTableName("TAB8F");
        setTableId("AB8");
        super.initialize();
    }
    
    public void initializeColumnDescriptors()
    {
        super.initializeColumnDescriptors();
        addColumnDescriptor(new ColumnDescriptor(this,"getCompanyCode","setCompanyCode","COMPANY_CODE,1,1,3,0,true|,0,CHAR,1,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getTableSubset","setTableSubset","TABLE_SUBSET,1,2,16,0,true|,0,CHAR,1,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getEffectiveDate","setEffectiveDate","EFFECTIVE_DATE,91,3,10,0,true|,0,DATE,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getIssueAge","setIssueAge","ISSUE_AGE,3,4,3,0,true|,0,INTEGER,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getSexCode","setSexCode","SEX_CODE,1,5,1,0,true|,0,CHAR,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getUndrwrtngBasisCd","setUndrwrtngBasisCd","UNDRWRTNG_BASIS_CD,1,6,1,0,true|,0,CHAR,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getMemoCode","setMemoCode","MEMO_CODE,1,7,2,0,true|,0,CHAR,4,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getPctgOfPrem","setPctgOfPrem","PCTG_OF_PREM,3,8,6,3,false|,0,DOUBLE,0,null,null,null,null,null"));
    }
    
    public String getPagingSQL(String schemaName,boolean isSubsetMode,boolean isNext, boolean locator)
    {
        String pagingWhere = null;
        String order = null;
        if (isNext) {
            if (isSubsetMode)
                if (locator)
                    pagingWhere = ".TAB8F WHERE (COMPANY_CODE = :company_code) AND (TABLE_SUBSET = :table_subset) AND ((EFFECTIVE_DATE > :effective_date) OR (EFFECTIVE_DATE = :effective_date AND ISSUE_AGE > :issue_age) OR (EFFECTIVE_DATE = :effective_date AND ISSUE_AGE = :issue_age AND SEX_CODE > :sex_code) OR (EFFECTIVE_DATE = :effective_date AND ISSUE_AGE = :issue_age AND SEX_CODE = :sex_code AND UNDRWRTNG_BASIS_CD > :undrwrtng_basis_cd) OR (EFFECTIVE_DATE = :effective_date AND ISSUE_AGE = :issue_age AND SEX_CODE = :sex_code AND UNDRWRTNG_BASIS_CD = :undrwrtng_basis_cd AND MEMO_CODE > :memo_code) OR (EFFECTIVE_DATE = :effective_date AND ISSUE_AGE = :issue_age AND SEX_CODE = :sex_code AND UNDRWRTNG_BASIS_CD = :undrwrtng_basis_cd AND MEMO_CODE = :memo_code)) ";
                else 
                    pagingWhere = ".TAB8F WHERE (COMPANY_CODE = :company_code) AND (TABLE_SUBSET = :table_subset) AND ((EFFECTIVE_DATE > :effective_date) OR (EFFECTIVE_DATE = :effective_date AND ISSUE_AGE > :issue_age) OR (EFFECTIVE_DATE = :effective_date AND ISSUE_AGE = :issue_age AND SEX_CODE > :sex_code) OR (EFFECTIVE_DATE = :effective_date AND ISSUE_AGE = :issue_age AND SEX_CODE = :sex_code AND UNDRWRTNG_BASIS_CD > :undrwrtng_basis_cd) OR (EFFECTIVE_DATE = :effective_date AND ISSUE_AGE = :issue_age AND SEX_CODE = :sex_code AND UNDRWRTNG_BASIS_CD = :undrwrtng_basis_cd AND MEMO_CODE > :memo_code)) ";
            else
                if (locator)
                    pagingWhere = ".TAB8F WHERE (COMPANY_CODE = :company_code) AND ((TABLE_SUBSET > :table_subset) OR (TABLE_SUBSET = :table_subset AND EFFECTIVE_DATE > :effective_date) OR (TABLE_SUBSET = :table_subset AND EFFECTIVE_DATE = :effective_date AND ISSUE_AGE > :issue_age) OR (TABLE_SUBSET = :table_subset AND EFFECTIVE_DATE = :effective_date AND ISSUE_AGE = :issue_age AND SEX_CODE > :sex_code) OR (TABLE_SUBSET = :table_subset AND EFFECTIVE_DATE = :effective_date AND ISSUE_AGE = :issue_age AND SEX_CODE = :sex_code AND UNDRWRTNG_BASIS_CD > :undrwrtng_basis_cd) OR (TABLE_SUBSET = :table_subset AND EFFECTIVE_DATE = :effective_date AND ISSUE_AGE = :issue_age AND SEX_CODE = :sex_code AND UNDRWRTNG_BASIS_CD = :undrwrtng_basis_cd AND MEMO_CODE > :memo_code) OR (TABLE_SUBSET = :table_subset AND EFFECTIVE_DATE = :effective_date AND ISSUE_AGE = :issue_age AND SEX_CODE = :sex_code AND UNDRWRTNG_BASIS_CD = :undrwrtng_basis_cd AND MEMO_CODE = :memo_code)) ";
                else
                    pagingWhere = ".TAB8F WHERE (COMPANY_CODE = :company_code) AND ((TABLE_SUBSET > :table_subset) OR (TABLE_SUBSET = :table_subset AND EFFECTIVE_DATE > :effective_date) OR (TABLE_SUBSET = :table_subset AND EFFECTIVE_DATE = :effective_date AND ISSUE_AGE > :issue_age) OR (TABLE_SUBSET = :table_subset AND EFFECTIVE_DATE = :effective_date AND ISSUE_AGE = :issue_age AND SEX_CODE > :sex_code) OR (TABLE_SUBSET = :table_subset AND EFFECTIVE_DATE = :effective_date AND ISSUE_AGE = :issue_age AND SEX_CODE = :sex_code AND UNDRWRTNG_BASIS_CD > :undrwrtng_basis_cd) OR (TABLE_SUBSET = :table_subset AND EFFECTIVE_DATE = :effective_date AND ISSUE_AGE = :issue_age AND SEX_CODE = :sex_code AND UNDRWRTNG_BASIS_CD = :undrwrtng_basis_cd AND MEMO_CODE > :memo_code)) ";
            order = " ORDER BY COMPANY_CODE, TABLE_SUBSET, EFFECTIVE_DATE, ISSUE_AGE, SEX_CODE, UNDRWRTNG_BASIS_CD, MEMO_CODE";
        }
        else {
            if (isSubsetMode)
                pagingWhere = ".TAB8F WHERE (COMPANY_CODE = :company_code) AND (TABLE_SUBSET = :table_subset) AND ((EFFECTIVE_DATE < :effective_date) OR (EFFECTIVE_DATE = :effective_date AND ISSUE_AGE < :issue_age) OR (EFFECTIVE_DATE = :effective_date AND ISSUE_AGE = :issue_age AND SEX_CODE < :sex_code) OR (EFFECTIVE_DATE = :effective_date AND ISSUE_AGE = :issue_age AND SEX_CODE = :sex_code AND UNDRWRTNG_BASIS_CD < :undrwrtng_basis_cd) OR (EFFECTIVE_DATE = :effective_date AND ISSUE_AGE = :issue_age AND SEX_CODE = :sex_code AND UNDRWRTNG_BASIS_CD = :undrwrtng_basis_cd AND MEMO_CODE < :memo_code)) ";
            else
                pagingWhere = ".TAB8F WHERE (COMPANY_CODE = :company_code) AND ((TABLE_SUBSET < :table_subset) OR (TABLE_SUBSET = :table_subset AND EFFECTIVE_DATE < :effective_date) OR (TABLE_SUBSET = :table_subset AND EFFECTIVE_DATE = :effective_date AND ISSUE_AGE < :issue_age) OR (TABLE_SUBSET = :table_subset AND EFFECTIVE_DATE = :effective_date AND ISSUE_AGE = :issue_age AND SEX_CODE < :sex_code) OR (TABLE_SUBSET = :table_subset AND EFFECTIVE_DATE = :effective_date AND ISSUE_AGE = :issue_age AND SEX_CODE = :sex_code AND UNDRWRTNG_BASIS_CD < :undrwrtng_basis_cd) OR (TABLE_SUBSET = :table_subset AND EFFECTIVE_DATE = :effective_date AND ISSUE_AGE = :issue_age AND SEX_CODE = :sex_code AND UNDRWRTNG_BASIS_CD = :undrwrtng_basis_cd AND MEMO_CODE < :memo_code)) ";
            order = " ORDER BY COMPANY_CODE DESC, TABLE_SUBSET DESC, EFFECTIVE_DATE DESC, ISSUE_AGE DESC, SEX_CODE DESC, UNDRWRTNG_BASIS_CD DESC, MEMO_CODE DESC";
        }
        return pagingSql + schemaName + pagingWhere + order;
    }
    
    public String prepareInsertStmt(String schemaName) {
        StringBuffer sb = new StringBuffer();
        sb.append("INSERT INTO ").append(schemaName);
        sb.append(".TAB8F ( ");
        sb.append("COMPANY_CODE, TABLE_SUBSET, EFFECTIVE_DATE, ISSUE_AGE, SEX_CODE, UNDRWRTNG_BASIS_CD, MEMO_CODE, PCTG_OF_PREM )");
        sb.append(" VALUES (?, ?, ?, ?, ?, ?, ?, ? )");
        return sb.toString();
    }
    
    public String prepareUpdateStmt(String schemaName)
    {
        StringBuffer sb = new StringBuffer();
        sb.append("UPDATE ").append(schemaName);
        sb.append(".TAB8F ");
        sb.append(" SET COMPANY_CODE = ?, TABLE_SUBSET = ?, EFFECTIVE_DATE = ?, ISSUE_AGE = ?, SEX_CODE = ?, UNDRWRTNG_BASIS_CD = ?, MEMO_CODE = ?, PCTG_OF_PREM = ?");
        sb.append(" WHERE COMPANY_CODE = ? AND TABLE_SUBSET = ? AND EFFECTIVE_DATE = ? AND ISSUE_AGE = ? AND SEX_CODE = ? AND UNDRWRTNG_BASIS_CD = ? AND MEMO_CODE = ?");
        return sb.toString();
    }
    
    public String prepareDeleteStmt(String schemaName)
    {
        StringBuffer sb = new StringBuffer();
        sb.append("DELETE FROM ").append(schemaName);
        sb.append(".TAB8F ");
        sb.append(" WHERE COMPANY_CODE = ? AND TABLE_SUBSET = ? AND EFFECTIVE_DATE = ? AND ISSUE_AGE = ? AND SEX_CODE = ? AND UNDRWRTNG_BASIS_CD = ? AND MEMO_CODE = ?");
        return sb.toString();
    }
}
