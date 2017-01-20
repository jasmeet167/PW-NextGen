package com.csc.fsg.life.pw.io.descriptor.wma;

import com.csc.fsg.life.pw.io.*;

public class TA59FDescriptor
    extends TableDescriptor
{
    private static final String pagingSql = "SELECT COMPANY_CODE, TABLE_SUBSET, STATUTORY_COMPANY, EFFECTIVE_DATE, ISSUE_YEAR, LINE_OF_BUSINESS, RETIREMENT_AGE, STAT_VALUE_IND, GAAP_VALUE_IND, TAX_VALUE_IND, NEW_YORK_ST_IND, GAAP_RESER_MTHD, MAX_PROJ_YRS_TBL, INTEREST_RATE_TBL, GAAP_BENEFIT_TBL, GAAP_EXPENSE_TBL, SURRENDER_SPEC_TBL, RIDER_BENEFIT_TBL FROM ";
    
    public void initialize()
    {
        setRowClass(TA59FRow.class);
        setTableName("TA59F");
        setTableId("A59");
        super.initialize();
    }
    
    public void initializeColumnDescriptors()
    {
        super.initializeColumnDescriptors();
        addColumnDescriptor(new ColumnDescriptor(this,"getCompanyCode","setCompanyCode","COMPANY_CODE,1,1,3,0,true|,0,CHAR,1,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getTableSubset","setTableSubset","TABLE_SUBSET,1,2,16,0,true|,0,CHAR,1,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getStatutoryCompany","setStatutoryCompany","STATUTORY_COMPANY,1,3,3,0,true|,0,CHAR,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getEffectiveDate","setEffectiveDate","EFFECTIVE_DATE,91,4,10,0,true|,0,DATE,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getIssueYear","setIssueYear","ISSUE_YEAR,3,5,4,0,true|,0,INTEGER,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getLineOfBusiness","setLineOfBusiness","LINE_OF_BUSINESS,1,6,3,0,true|,0,CHAR,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getRetirementAge","setRetirementAge","RETIREMENT_AGE,3,7,3,0,false|,0,INTEGER,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getStatValueInd","setStatValueInd","STAT_VALUE_IND,1,8,1,0,false|,0,CHAR,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getGaapValueInd","setGaapValueInd","GAAP_VALUE_IND,1,9,1,0,false|,0,CHAR,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getTaxValueInd","setTaxValueInd","TAX_VALUE_IND,1,10,1,0,false|,0,CHAR,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getNewYorkStInd","setNewYorkStInd","NEW_YORK_ST_IND,1,11,1,0,false|,0,CHAR,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getGaapReserMthd","setGaapReserMthd","GAAP_RESER_MTHD,1,12,1,0,false|,0,CHAR,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getMaxProjYrsTbl","setMaxProjYrsTbl","MAX_PROJ_YRS_TBL,1,13,16,0,false|,0,CHAR,0,AE1,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getInterestRateTbl","setInterestRateTbl","INTEREST_RATE_TBL,1,14,16,0,false|,0,CHAR,0,060,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getGaapBenefitTbl","setGaapBenefitTbl","GAAP_BENEFIT_TBL,1,15,16,0,false|,0,CHAR,0,A61,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getGaapExpenseTbl","setGaapExpenseTbl","GAAP_EXPENSE_TBL,1,16,16,0,false|,0,CHAR,0,A62,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getSurrenderSpecTbl","setSurrenderSpecTbl","SURRENDER_SPEC_TBL,1,17,16,0,false|,0,CHAR,0,AB1,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getRiderBenefitTbl","setRiderBenefitTbl","RIDER_BENEFIT_TBL,1,18,16,0,false|,0,CHAR,0,AE2,null,null,null,null"));
    }
    
    public String getPagingSQL(String schemaName,boolean isSubsetMode,boolean isNext, boolean locator)
    {
        String pagingWhere = null;
        String order = null;
        if (isNext) {
            if (isSubsetMode)
                if (locator)
                    pagingWhere = ".TA59F WHERE (COMPANY_CODE = :company_code) AND (TABLE_SUBSET = :table_subset) AND ((STATUTORY_COMPANY > :statutory_company) OR (STATUTORY_COMPANY = :statutory_company AND EFFECTIVE_DATE > :effective_date) OR (STATUTORY_COMPANY = :statutory_company AND EFFECTIVE_DATE = :effective_date AND ISSUE_YEAR > :issue_year) OR (STATUTORY_COMPANY = :statutory_company AND EFFECTIVE_DATE = :effective_date AND ISSUE_YEAR = :issue_year AND LINE_OF_BUSINESS > :line_of_business) OR (STATUTORY_COMPANY = :statutory_company AND EFFECTIVE_DATE = :effective_date AND ISSUE_YEAR = :issue_year AND LINE_OF_BUSINESS = :line_of_business)) ";
                else 
                    pagingWhere = ".TA59F WHERE (COMPANY_CODE = :company_code) AND (TABLE_SUBSET = :table_subset) AND ((STATUTORY_COMPANY > :statutory_company) OR (STATUTORY_COMPANY = :statutory_company AND EFFECTIVE_DATE > :effective_date) OR (STATUTORY_COMPANY = :statutory_company AND EFFECTIVE_DATE = :effective_date AND ISSUE_YEAR > :issue_year) OR (STATUTORY_COMPANY = :statutory_company AND EFFECTIVE_DATE = :effective_date AND ISSUE_YEAR = :issue_year AND LINE_OF_BUSINESS > :line_of_business)) ";
            else
                if (locator)
                    pagingWhere = ".TA59F WHERE (COMPANY_CODE = :company_code) AND ((TABLE_SUBSET > :table_subset) OR (TABLE_SUBSET = :table_subset AND STATUTORY_COMPANY > :statutory_company) OR (TABLE_SUBSET = :table_subset AND STATUTORY_COMPANY = :statutory_company AND EFFECTIVE_DATE > :effective_date) OR (TABLE_SUBSET = :table_subset AND STATUTORY_COMPANY = :statutory_company AND EFFECTIVE_DATE = :effective_date AND ISSUE_YEAR > :issue_year) OR (TABLE_SUBSET = :table_subset AND STATUTORY_COMPANY = :statutory_company AND EFFECTIVE_DATE = :effective_date AND ISSUE_YEAR = :issue_year AND LINE_OF_BUSINESS > :line_of_business) OR (TABLE_SUBSET = :table_subset AND STATUTORY_COMPANY = :statutory_company AND EFFECTIVE_DATE = :effective_date AND ISSUE_YEAR = :issue_year AND LINE_OF_BUSINESS = :line_of_business)) ";
                else
                    pagingWhere = ".TA59F WHERE (COMPANY_CODE = :company_code) AND ((TABLE_SUBSET > :table_subset) OR (TABLE_SUBSET = :table_subset AND STATUTORY_COMPANY > :statutory_company) OR (TABLE_SUBSET = :table_subset AND STATUTORY_COMPANY = :statutory_company AND EFFECTIVE_DATE > :effective_date) OR (TABLE_SUBSET = :table_subset AND STATUTORY_COMPANY = :statutory_company AND EFFECTIVE_DATE = :effective_date AND ISSUE_YEAR > :issue_year) OR (TABLE_SUBSET = :table_subset AND STATUTORY_COMPANY = :statutory_company AND EFFECTIVE_DATE = :effective_date AND ISSUE_YEAR = :issue_year AND LINE_OF_BUSINESS > :line_of_business)) ";
            order = " ORDER BY COMPANY_CODE, TABLE_SUBSET, STATUTORY_COMPANY, EFFECTIVE_DATE, ISSUE_YEAR, LINE_OF_BUSINESS";
        }
        else {
            if (isSubsetMode)
                pagingWhere = ".TA59F WHERE (COMPANY_CODE = :company_code) AND (TABLE_SUBSET = :table_subset) AND ((STATUTORY_COMPANY < :statutory_company) OR (STATUTORY_COMPANY = :statutory_company AND EFFECTIVE_DATE < :effective_date) OR (STATUTORY_COMPANY = :statutory_company AND EFFECTIVE_DATE = :effective_date AND ISSUE_YEAR < :issue_year) OR (STATUTORY_COMPANY = :statutory_company AND EFFECTIVE_DATE = :effective_date AND ISSUE_YEAR = :issue_year AND LINE_OF_BUSINESS < :line_of_business)) ";
            else
                pagingWhere = ".TA59F WHERE (COMPANY_CODE = :company_code) AND ((TABLE_SUBSET < :table_subset) OR (TABLE_SUBSET = :table_subset AND STATUTORY_COMPANY < :statutory_company) OR (TABLE_SUBSET = :table_subset AND STATUTORY_COMPANY = :statutory_company AND EFFECTIVE_DATE < :effective_date) OR (TABLE_SUBSET = :table_subset AND STATUTORY_COMPANY = :statutory_company AND EFFECTIVE_DATE = :effective_date AND ISSUE_YEAR < :issue_year) OR (TABLE_SUBSET = :table_subset AND STATUTORY_COMPANY = :statutory_company AND EFFECTIVE_DATE = :effective_date AND ISSUE_YEAR = :issue_year AND LINE_OF_BUSINESS < :line_of_business)) ";
            order = " ORDER BY COMPANY_CODE DESC, TABLE_SUBSET DESC, STATUTORY_COMPANY DESC, EFFECTIVE_DATE DESC, ISSUE_YEAR DESC, LINE_OF_BUSINESS DESC";
        }
        return pagingSql + schemaName + pagingWhere + order;
    }
    
    public String prepareInsertStmt(String schemaName) {
        StringBuffer sb = new StringBuffer();
        sb.append("INSERT INTO ").append(schemaName);
        sb.append(".TA59F ( ");
        sb.append("COMPANY_CODE, TABLE_SUBSET, STATUTORY_COMPANY, EFFECTIVE_DATE, ISSUE_YEAR, LINE_OF_BUSINESS, RETIREMENT_AGE, STAT_VALUE_IND, GAAP_VALUE_IND, TAX_VALUE_IND, NEW_YORK_ST_IND, GAAP_RESER_MTHD, MAX_PROJ_YRS_TBL, INTEREST_RATE_TBL, GAAP_BENEFIT_TBL, GAAP_EXPENSE_TBL, SURRENDER_SPEC_TBL, RIDER_BENEFIT_TBL )");
        sb.append(" VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ? )");
        return sb.toString();
    }
    
    public String prepareUpdateStmt(String schemaName)
    {
        StringBuffer sb = new StringBuffer();
        sb.append("UPDATE ").append(schemaName);
        sb.append(".TA59F ");
        sb.append(" SET COMPANY_CODE = ?, TABLE_SUBSET = ?, STATUTORY_COMPANY = ?, EFFECTIVE_DATE = ?, ISSUE_YEAR = ?, LINE_OF_BUSINESS = ?, RETIREMENT_AGE = ?, STAT_VALUE_IND = ?, GAAP_VALUE_IND = ?, TAX_VALUE_IND = ?, NEW_YORK_ST_IND = ?, GAAP_RESER_MTHD = ?, MAX_PROJ_YRS_TBL = ?, INTEREST_RATE_TBL = ?, GAAP_BENEFIT_TBL = ?, GAAP_EXPENSE_TBL = ?, SURRENDER_SPEC_TBL = ?, RIDER_BENEFIT_TBL = ?");
        sb.append(" WHERE COMPANY_CODE = ? AND TABLE_SUBSET = ? AND STATUTORY_COMPANY = ? AND EFFECTIVE_DATE = ? AND ISSUE_YEAR = ? AND LINE_OF_BUSINESS = ?");
        return sb.toString();
    }
    
    public String prepareDeleteStmt(String schemaName)
    {
        StringBuffer sb = new StringBuffer();
        sb.append("DELETE FROM ").append(schemaName);
        sb.append(".TA59F ");
        sb.append(" WHERE COMPANY_CODE = ? AND TABLE_SUBSET = ? AND STATUTORY_COMPANY = ? AND EFFECTIVE_DATE = ? AND ISSUE_YEAR = ? AND LINE_OF_BUSINESS = ?");
        return sb.toString();
    }
}
