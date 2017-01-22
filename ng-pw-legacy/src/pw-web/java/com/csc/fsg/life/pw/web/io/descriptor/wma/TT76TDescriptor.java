package com.csc.fsg.life.pw.web.io.descriptor.wma;

import com.csc.fsg.life.pw.web.io.*;

public class TT76TDescriptor
    extends TableDescriptor
{
    private static final String pagingSql = "SELECT COMPANY_CODE, TABLE_SUBSET, EFFECTIVE_DATE, BILL_OPTION_CD, MAX_COV_UNITS, SELECTION_NUMBER, FEE_SIGN_IND, ANN_POL_FEE, SEMI_ANN_POL_FEE, QUARTERLY_POL_FEE, MONTHLY_POL_FEE FROM ";
    
    public void initialize()
    {
        setRowClass(TT76TRow.class);
        setTableName("TT76T");
        setTableId("T76");
        super.initialize();
    }
    
    public void initializeColumnDescriptors()
    {
        super.initializeColumnDescriptors();
        addColumnDescriptor(new ColumnDescriptor(this,"getCompanyCode","setCompanyCode","COMPANY_CODE,1,1,3,0,true|,0,CHAR,1,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getTableSubset","setTableSubset","TABLE_SUBSET,1,2,16,0,true|,0,CHAR,1,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getEffectiveDate","setEffectiveDate","EFFECTIVE_DATE,91,3,10,0,true|,0,DATE,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getBillOptionCd","setBillOptionCd","BILL_OPTION_CD,1,4,1,0,true|,0,CHAR,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getMaxCovUnits","setMaxCovUnits","MAX_COV_UNITS,3,5,11,5,true|,0,DOUBLE,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getSelectionNumber","setSelectionNumber","SELECTION_NUMBER,1,6,3,0,true|,0,CHAR,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getFeeSignInd","setFeeSignInd","FEE_SIGN_IND,1,7,1,0,false|,0,CHAR,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getAnnPolFee","setAnnPolFee","ANN_POL_FEE,3,8,5,2,false|,0,DOUBLE,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getSemiAnnPolFee","setSemiAnnPolFee","SEMI_ANN_POL_FEE,3,9,5,2,false|,0,DOUBLE,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getQuarterlyPolFee","setQuarterlyPolFee","QUARTERLY_POL_FEE,3,10,5,2,false|,0,DOUBLE,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getMonthlyPolFee","setMonthlyPolFee","MONTHLY_POL_FEE,3,11,5,2,false|,0,DOUBLE,0,null,null,null,null,null"));
    }
    
    public String getPagingSQL(String schemaName,boolean isSubsetMode,boolean isNext, boolean locator)
    {
        String pagingWhere = null;
        String order = null;
        if (isNext) {
            if (isSubsetMode)
                if (locator)
                    pagingWhere = ".TT76T WHERE (COMPANY_CODE = :company_code) AND (TABLE_SUBSET = :table_subset) AND ((EFFECTIVE_DATE > :effective_date) OR (EFFECTIVE_DATE = :effective_date AND BILL_OPTION_CD > :bill_option_cd) OR (EFFECTIVE_DATE = :effective_date AND BILL_OPTION_CD = :bill_option_cd AND MAX_COV_UNITS > :max_cov_units) OR (EFFECTIVE_DATE = :effective_date AND BILL_OPTION_CD = :bill_option_cd AND MAX_COV_UNITS = :max_cov_units AND SELECTION_NUMBER > :selection_number) OR (EFFECTIVE_DATE = :effective_date AND BILL_OPTION_CD = :bill_option_cd AND MAX_COV_UNITS = :max_cov_units AND SELECTION_NUMBER = :selection_number)) ";
                else 
                    pagingWhere = ".TT76T WHERE (COMPANY_CODE = :company_code) AND (TABLE_SUBSET = :table_subset) AND ((EFFECTIVE_DATE > :effective_date) OR (EFFECTIVE_DATE = :effective_date AND BILL_OPTION_CD > :bill_option_cd) OR (EFFECTIVE_DATE = :effective_date AND BILL_OPTION_CD = :bill_option_cd AND MAX_COV_UNITS > :max_cov_units) OR (EFFECTIVE_DATE = :effective_date AND BILL_OPTION_CD = :bill_option_cd AND MAX_COV_UNITS = :max_cov_units AND SELECTION_NUMBER > :selection_number)) ";
            else
                if (locator)
                    pagingWhere = ".TT76T WHERE (COMPANY_CODE = :company_code) AND ((TABLE_SUBSET > :table_subset) OR (TABLE_SUBSET = :table_subset AND EFFECTIVE_DATE > :effective_date) OR (TABLE_SUBSET = :table_subset AND EFFECTIVE_DATE = :effective_date AND BILL_OPTION_CD > :bill_option_cd) OR (TABLE_SUBSET = :table_subset AND EFFECTIVE_DATE = :effective_date AND BILL_OPTION_CD = :bill_option_cd AND MAX_COV_UNITS > :max_cov_units) OR (TABLE_SUBSET = :table_subset AND EFFECTIVE_DATE = :effective_date AND BILL_OPTION_CD = :bill_option_cd AND MAX_COV_UNITS = :max_cov_units AND SELECTION_NUMBER > :selection_number) OR (TABLE_SUBSET = :table_subset AND EFFECTIVE_DATE = :effective_date AND BILL_OPTION_CD = :bill_option_cd AND MAX_COV_UNITS = :max_cov_units AND SELECTION_NUMBER = :selection_number)) ";
                else
                    pagingWhere = ".TT76T WHERE (COMPANY_CODE = :company_code) AND ((TABLE_SUBSET > :table_subset) OR (TABLE_SUBSET = :table_subset AND EFFECTIVE_DATE > :effective_date) OR (TABLE_SUBSET = :table_subset AND EFFECTIVE_DATE = :effective_date AND BILL_OPTION_CD > :bill_option_cd) OR (TABLE_SUBSET = :table_subset AND EFFECTIVE_DATE = :effective_date AND BILL_OPTION_CD = :bill_option_cd AND MAX_COV_UNITS > :max_cov_units) OR (TABLE_SUBSET = :table_subset AND EFFECTIVE_DATE = :effective_date AND BILL_OPTION_CD = :bill_option_cd AND MAX_COV_UNITS = :max_cov_units AND SELECTION_NUMBER > :selection_number)) ";
            order = " ORDER BY COMPANY_CODE, TABLE_SUBSET, EFFECTIVE_DATE, BILL_OPTION_CD, MAX_COV_UNITS, SELECTION_NUMBER";
        }
        else {
            if (isSubsetMode)
                pagingWhere = ".TT76T WHERE (COMPANY_CODE = :company_code) AND (TABLE_SUBSET = :table_subset) AND ((EFFECTIVE_DATE < :effective_date) OR (EFFECTIVE_DATE = :effective_date AND BILL_OPTION_CD < :bill_option_cd) OR (EFFECTIVE_DATE = :effective_date AND BILL_OPTION_CD = :bill_option_cd AND MAX_COV_UNITS < :max_cov_units) OR (EFFECTIVE_DATE = :effective_date AND BILL_OPTION_CD = :bill_option_cd AND MAX_COV_UNITS = :max_cov_units AND SELECTION_NUMBER < :selection_number)) ";
            else
                pagingWhere = ".TT76T WHERE (COMPANY_CODE = :company_code) AND ((TABLE_SUBSET < :table_subset) OR (TABLE_SUBSET = :table_subset AND EFFECTIVE_DATE < :effective_date) OR (TABLE_SUBSET = :table_subset AND EFFECTIVE_DATE = :effective_date AND BILL_OPTION_CD < :bill_option_cd) OR (TABLE_SUBSET = :table_subset AND EFFECTIVE_DATE = :effective_date AND BILL_OPTION_CD = :bill_option_cd AND MAX_COV_UNITS < :max_cov_units) OR (TABLE_SUBSET = :table_subset AND EFFECTIVE_DATE = :effective_date AND BILL_OPTION_CD = :bill_option_cd AND MAX_COV_UNITS = :max_cov_units AND SELECTION_NUMBER < :selection_number)) ";
            order = " ORDER BY COMPANY_CODE DESC, TABLE_SUBSET DESC, EFFECTIVE_DATE DESC, BILL_OPTION_CD DESC, MAX_COV_UNITS DESC, SELECTION_NUMBER DESC";
        }
        return pagingSql + schemaName + pagingWhere + order;
    }
    
    public String prepareInsertStmt(String schemaName) {
        StringBuffer sb = new StringBuffer();
        sb.append("INSERT INTO ").append(schemaName);
        sb.append(".TT76T ( ");
        sb.append("COMPANY_CODE, TABLE_SUBSET, EFFECTIVE_DATE, BILL_OPTION_CD, MAX_COV_UNITS, SELECTION_NUMBER, FEE_SIGN_IND, ANN_POL_FEE, SEMI_ANN_POL_FEE, QUARTERLY_POL_FEE, MONTHLY_POL_FEE )");
        sb.append(" VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ? )");
        return sb.toString();
    }
    
    public String prepareUpdateStmt(String schemaName)
    {
        StringBuffer sb = new StringBuffer();
        sb.append("UPDATE ").append(schemaName);
        sb.append(".TT76T ");
        sb.append(" SET COMPANY_CODE = ?, TABLE_SUBSET = ?, EFFECTIVE_DATE = ?, BILL_OPTION_CD = ?, MAX_COV_UNITS = ?, SELECTION_NUMBER = ?, FEE_SIGN_IND = ?, ANN_POL_FEE = ?, SEMI_ANN_POL_FEE = ?, QUARTERLY_POL_FEE = ?, MONTHLY_POL_FEE = ?");
        sb.append(" WHERE COMPANY_CODE = ? AND TABLE_SUBSET = ? AND EFFECTIVE_DATE = ? AND BILL_OPTION_CD = ? AND MAX_COV_UNITS = ? AND SELECTION_NUMBER = ?");
        return sb.toString();
    }
    
    public String prepareDeleteStmt(String schemaName)
    {
        StringBuffer sb = new StringBuffer();
        sb.append("DELETE FROM ").append(schemaName);
        sb.append(".TT76T ");
        sb.append(" WHERE COMPANY_CODE = ? AND TABLE_SUBSET = ? AND EFFECTIVE_DATE = ? AND BILL_OPTION_CD = ? AND MAX_COV_UNITS = ? AND SELECTION_NUMBER = ?");
        return sb.toString();
    }
}
