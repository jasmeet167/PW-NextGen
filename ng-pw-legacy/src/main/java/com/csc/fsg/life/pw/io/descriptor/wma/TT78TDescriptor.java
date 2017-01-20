package com.csc.fsg.life.pw.io.descriptor.wma;

import com.csc.fsg.life.pw.io.*;

public class TT78TDescriptor
    extends TableDescriptor
{
    private static final String pagingSql = "SELECT COMPANY_CODE, TABLE_SUBSET, EFFECTIVE_DATE, BILL_OPTION_CODE, FRACTIONAL_PERIOD, FRACTIONAL_FACTOR, MODAL_BASIS FROM ";
    
    public void initialize()
    {
        setRowClass(TT78TRow.class);
        setTableName("TT78T");
        setTableId("T78");
        super.initialize();
    }
    
    public void initializeColumnDescriptors()
    {
        super.initializeColumnDescriptors();
        addColumnDescriptor(new ColumnDescriptor(this,"getCompanyCode","setCompanyCode","COMPANY_CODE,1,1,3,0,true|,0,CHAR,1,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getTableSubset","setTableSubset","TABLE_SUBSET,1,2,16,0,true|,0,CHAR,1,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getEffectiveDate","setEffectiveDate","EFFECTIVE_DATE,91,3,10,0,true|,0,DATE,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getBillOptionCode","setBillOptionCode","BILL_OPTION_CODE,1,4,1,0,true|,0,CHAR,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getFractionalPeriod","setFractionalPeriod","FRACTIONAL_PERIOD,3,5,2,0,true|,0,INTEGER,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getFractionalFactor","setFractionalFactor","FRACTIONAL_FACTOR,3,6,8,5,false|,0,DOUBLE,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getModalBasis","setModalBasis","MODAL_BASIS,3,7,2,0,false|,0,INTEGER,0,null,null,null,null,null"));
    }
    
    public String getPagingSQL(String schemaName,boolean isSubsetMode,boolean isNext, boolean locator)
    {
        String pagingWhere = null;
        String order = null;
        if (isNext) {
            if (isSubsetMode)
                if (locator)
                    pagingWhere = ".TT78T WHERE (COMPANY_CODE = :company_code) AND (TABLE_SUBSET = :table_subset) AND ((EFFECTIVE_DATE > :effective_date) OR (EFFECTIVE_DATE = :effective_date AND BILL_OPTION_CODE > :bill_option_code) OR (EFFECTIVE_DATE = :effective_date AND BILL_OPTION_CODE = :bill_option_code AND FRACTIONAL_PERIOD > :fractional_period) OR (EFFECTIVE_DATE = :effective_date AND BILL_OPTION_CODE = :bill_option_code AND FRACTIONAL_PERIOD = :fractional_period)) ";
                else 
                    pagingWhere = ".TT78T WHERE (COMPANY_CODE = :company_code) AND (TABLE_SUBSET = :table_subset) AND ((EFFECTIVE_DATE > :effective_date) OR (EFFECTIVE_DATE = :effective_date AND BILL_OPTION_CODE > :bill_option_code) OR (EFFECTIVE_DATE = :effective_date AND BILL_OPTION_CODE = :bill_option_code AND FRACTIONAL_PERIOD > :fractional_period)) ";
            else
                if (locator)
                    pagingWhere = ".TT78T WHERE (COMPANY_CODE = :company_code) AND ((TABLE_SUBSET > :table_subset) OR (TABLE_SUBSET = :table_subset AND EFFECTIVE_DATE > :effective_date) OR (TABLE_SUBSET = :table_subset AND EFFECTIVE_DATE = :effective_date AND BILL_OPTION_CODE > :bill_option_code) OR (TABLE_SUBSET = :table_subset AND EFFECTIVE_DATE = :effective_date AND BILL_OPTION_CODE = :bill_option_code AND FRACTIONAL_PERIOD > :fractional_period) OR (TABLE_SUBSET = :table_subset AND EFFECTIVE_DATE = :effective_date AND BILL_OPTION_CODE = :bill_option_code AND FRACTIONAL_PERIOD = :fractional_period)) ";
                else
                    pagingWhere = ".TT78T WHERE (COMPANY_CODE = :company_code) AND ((TABLE_SUBSET > :table_subset) OR (TABLE_SUBSET = :table_subset AND EFFECTIVE_DATE > :effective_date) OR (TABLE_SUBSET = :table_subset AND EFFECTIVE_DATE = :effective_date AND BILL_OPTION_CODE > :bill_option_code) OR (TABLE_SUBSET = :table_subset AND EFFECTIVE_DATE = :effective_date AND BILL_OPTION_CODE = :bill_option_code AND FRACTIONAL_PERIOD > :fractional_period)) ";
            order = " ORDER BY COMPANY_CODE, TABLE_SUBSET, EFFECTIVE_DATE, BILL_OPTION_CODE, FRACTIONAL_PERIOD";
        }
        else {
            if (isSubsetMode)
                pagingWhere = ".TT78T WHERE (COMPANY_CODE = :company_code) AND (TABLE_SUBSET = :table_subset) AND ((EFFECTIVE_DATE < :effective_date) OR (EFFECTIVE_DATE = :effective_date AND BILL_OPTION_CODE < :bill_option_code) OR (EFFECTIVE_DATE = :effective_date AND BILL_OPTION_CODE = :bill_option_code AND FRACTIONAL_PERIOD < :fractional_period)) ";
            else
                pagingWhere = ".TT78T WHERE (COMPANY_CODE = :company_code) AND ((TABLE_SUBSET < :table_subset) OR (TABLE_SUBSET = :table_subset AND EFFECTIVE_DATE < :effective_date) OR (TABLE_SUBSET = :table_subset AND EFFECTIVE_DATE = :effective_date AND BILL_OPTION_CODE < :bill_option_code) OR (TABLE_SUBSET = :table_subset AND EFFECTIVE_DATE = :effective_date AND BILL_OPTION_CODE = :bill_option_code AND FRACTIONAL_PERIOD < :fractional_period)) ";
            order = " ORDER BY COMPANY_CODE DESC, TABLE_SUBSET DESC, EFFECTIVE_DATE DESC, BILL_OPTION_CODE DESC, FRACTIONAL_PERIOD DESC";
        }
        return pagingSql + schemaName + pagingWhere + order;
    }
    
    public String prepareInsertStmt(String schemaName) {
        StringBuffer sb = new StringBuffer();
        sb.append("INSERT INTO ").append(schemaName);
        sb.append(".TT78T ( ");
        sb.append("COMPANY_CODE, TABLE_SUBSET, EFFECTIVE_DATE, BILL_OPTION_CODE, FRACTIONAL_PERIOD, FRACTIONAL_FACTOR, MODAL_BASIS )");
        sb.append(" VALUES (?, ?, ?, ?, ?, ?, ? )");
        return sb.toString();
    }
    
    public String prepareUpdateStmt(String schemaName)
    {
        StringBuffer sb = new StringBuffer();
        sb.append("UPDATE ").append(schemaName);
        sb.append(".TT78T ");
        sb.append(" SET COMPANY_CODE = ?, TABLE_SUBSET = ?, EFFECTIVE_DATE = ?, BILL_OPTION_CODE = ?, FRACTIONAL_PERIOD = ?, FRACTIONAL_FACTOR = ?, MODAL_BASIS = ?");
        sb.append(" WHERE COMPANY_CODE = ? AND TABLE_SUBSET = ? AND EFFECTIVE_DATE = ? AND BILL_OPTION_CODE = ? AND FRACTIONAL_PERIOD = ?");
        return sb.toString();
    }
    
    public String prepareDeleteStmt(String schemaName)
    {
        StringBuffer sb = new StringBuffer();
        sb.append("DELETE FROM ").append(schemaName);
        sb.append(".TT78T ");
        sb.append(" WHERE COMPANY_CODE = ? AND TABLE_SUBSET = ? AND EFFECTIVE_DATE = ? AND BILL_OPTION_CODE = ? AND FRACTIONAL_PERIOD = ?");
        return sb.toString();
    }
}
