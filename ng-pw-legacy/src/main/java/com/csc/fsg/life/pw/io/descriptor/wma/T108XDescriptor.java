package com.csc.fsg.life.pw.io.descriptor.wma;

import com.csc.fsg.life.pw.io.*;

public class T108XDescriptor
    extends TableDescriptor
{
    private static final String pagingSql = "SELECT COMPANY_CODE, PRODUCT_PREFIX, PRODUCT_SUFFIX, EFFECTIVE_DATE, TRANSACTION_CODE, TAX_YEAR, LINE_OF_BUSINESS, CONTINUATION_IND, REMITTANCE_MAX_AGE, ROLLOVER_CODE, MAX_AMOUNT, OVER_FIFTY_AMOUNT, MAX_VIOL_REJ_IND FROM ";
    
    public void initialize()
    {
        setRowClass(T108XRow.class);
        setTableName("T108X");
        setTableId("108");
        super.initialize();
    }
    
    public void initializeColumnDescriptors()
    {
        super.initializeColumnDescriptors();
        addColumnDescriptor(new ColumnDescriptor(this,"getCompanyCode","setCompanyCode","COMPANY_CODE,1,1,3,0,true|,0,CHAR,1,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getProductPrefix","setProductPrefix","PRODUCT_PREFIX,1,2,1,0,true|,0,CHAR,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getProductSuffix","setProductSuffix","PRODUCT_SUFFIX,1,3,1,0,true|,0,CHAR,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getEffectiveDate","setEffectiveDate","EFFECTIVE_DATE,91,4,10,0,true|,0,DATE,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getTransactionCode","setTransactionCode","TRANSACTION_CODE,1,5,4,0,true|,0,CHAR,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getTaxYear","setTaxYear","TAX_YEAR,3,6,4,0,true|,0,INTEGER,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getLineOfBusiness","setLineOfBusiness","LINE_OF_BUSINESS,1,7,3,0,true|,0,CHAR,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getContinuationInd","setContinuationInd","CONTINUATION_IND,1,8,1,0,true|,0,CHAR,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getRemittanceMaxAge","setRemittanceMaxAge","REMITTANCE_MAX_AGE,3,9,4,1,true|,0,DOUBLE,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getRolloverCode","setRolloverCode","ROLLOVER_CODE,1,10,1,0,true|,0,CHAR,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getMaxAmount","setMaxAmount","MAX_AMOUNT,3,11,11,2,false|,0,DOUBLE,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getOverFiftyAmount","setOverFiftyAmount","OVER_FIFTY_AMOUNT,3,12,11,2,false|,0,DOUBLE,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getMaxViolRejInd","setMaxViolRejInd","MAX_VIOL_REJ_IND,1,13,1,0,false|,0,CHAR,0,null,null,null,null,null"));
    }
    
    public String getPagingSQL(String schemaName,boolean isSubsetMode,boolean isNext, boolean locator)
    {
        String pagingWhere = null;
        String order = null;
        if (isNext) {
            if (isSubsetMode)
                if (locator)
                    pagingWhere = ".T108X WHERE (COMPANY_CODE = :company_code) AND (PRODUCT_PREFIX = :product_prefix) AND ((PRODUCT_SUFFIX > :product_suffix) OR (PRODUCT_SUFFIX = :product_suffix AND EFFECTIVE_DATE > :effective_date) OR (PRODUCT_SUFFIX = :product_suffix AND EFFECTIVE_DATE = :effective_date AND TRANSACTION_CODE > :transaction_code) OR (PRODUCT_SUFFIX = :product_suffix AND EFFECTIVE_DATE = :effective_date AND TRANSACTION_CODE = :transaction_code AND TAX_YEAR > :tax_year) OR (PRODUCT_SUFFIX = :product_suffix AND EFFECTIVE_DATE = :effective_date AND TRANSACTION_CODE = :transaction_code AND TAX_YEAR = :tax_year AND LINE_OF_BUSINESS > :line_of_business) OR (PRODUCT_SUFFIX = :product_suffix AND EFFECTIVE_DATE = :effective_date AND TRANSACTION_CODE = :transaction_code AND TAX_YEAR = :tax_year AND LINE_OF_BUSINESS = :line_of_business AND CONTINUATION_IND > :continuation_ind) OR (PRODUCT_SUFFIX = :product_suffix AND EFFECTIVE_DATE = :effective_date AND TRANSACTION_CODE = :transaction_code AND TAX_YEAR = :tax_year AND LINE_OF_BUSINESS = :line_of_business AND CONTINUATION_IND = :continuation_ind AND REMITTANCE_MAX_AGE > :remittance_max_age) OR (PRODUCT_SUFFIX = :product_suffix AND EFFECTIVE_DATE = :effective_date AND TRANSACTION_CODE = :transaction_code AND TAX_YEAR = :tax_year AND LINE_OF_BUSINESS = :line_of_business AND CONTINUATION_IND = :continuation_ind AND REMITTANCE_MAX_AGE = :remittance_max_age AND ROLLOVER_CODE > :rollover_code) OR (PRODUCT_SUFFIX = :product_suffix AND EFFECTIVE_DATE = :effective_date AND TRANSACTION_CODE = :transaction_code AND TAX_YEAR = :tax_year AND LINE_OF_BUSINESS = :line_of_business AND CONTINUATION_IND = :continuation_ind AND REMITTANCE_MAX_AGE = :remittance_max_age AND ROLLOVER_CODE = :rollover_code)) ";
                else 
                    pagingWhere = ".T108X WHERE (COMPANY_CODE = :company_code) AND (PRODUCT_PREFIX = :product_prefix) AND ((PRODUCT_SUFFIX > :product_suffix) OR (PRODUCT_SUFFIX = :product_suffix AND EFFECTIVE_DATE > :effective_date) OR (PRODUCT_SUFFIX = :product_suffix AND EFFECTIVE_DATE = :effective_date AND TRANSACTION_CODE > :transaction_code) OR (PRODUCT_SUFFIX = :product_suffix AND EFFECTIVE_DATE = :effective_date AND TRANSACTION_CODE = :transaction_code AND TAX_YEAR > :tax_year) OR (PRODUCT_SUFFIX = :product_suffix AND EFFECTIVE_DATE = :effective_date AND TRANSACTION_CODE = :transaction_code AND TAX_YEAR = :tax_year AND LINE_OF_BUSINESS > :line_of_business) OR (PRODUCT_SUFFIX = :product_suffix AND EFFECTIVE_DATE = :effective_date AND TRANSACTION_CODE = :transaction_code AND TAX_YEAR = :tax_year AND LINE_OF_BUSINESS = :line_of_business AND CONTINUATION_IND > :continuation_ind) OR (PRODUCT_SUFFIX = :product_suffix AND EFFECTIVE_DATE = :effective_date AND TRANSACTION_CODE = :transaction_code AND TAX_YEAR = :tax_year AND LINE_OF_BUSINESS = :line_of_business AND CONTINUATION_IND = :continuation_ind AND REMITTANCE_MAX_AGE > :remittance_max_age) OR (PRODUCT_SUFFIX = :product_suffix AND EFFECTIVE_DATE = :effective_date AND TRANSACTION_CODE = :transaction_code AND TAX_YEAR = :tax_year AND LINE_OF_BUSINESS = :line_of_business AND CONTINUATION_IND = :continuation_ind AND REMITTANCE_MAX_AGE = :remittance_max_age AND ROLLOVER_CODE > :rollover_code)) ";
            else
                if (locator)
                    pagingWhere = ".T108X WHERE (COMPANY_CODE = :company_code) AND ((PRODUCT_PREFIX > :product_prefix) OR (PRODUCT_PREFIX = :product_prefix AND PRODUCT_SUFFIX > :product_suffix) OR (PRODUCT_PREFIX = :product_prefix AND PRODUCT_SUFFIX = :product_suffix AND EFFECTIVE_DATE > :effective_date) OR (PRODUCT_PREFIX = :product_prefix AND PRODUCT_SUFFIX = :product_suffix AND EFFECTIVE_DATE = :effective_date AND TRANSACTION_CODE > :transaction_code) OR (PRODUCT_PREFIX = :product_prefix AND PRODUCT_SUFFIX = :product_suffix AND EFFECTIVE_DATE = :effective_date AND TRANSACTION_CODE = :transaction_code AND TAX_YEAR > :tax_year) OR (PRODUCT_PREFIX = :product_prefix AND PRODUCT_SUFFIX = :product_suffix AND EFFECTIVE_DATE = :effective_date AND TRANSACTION_CODE = :transaction_code AND TAX_YEAR = :tax_year AND LINE_OF_BUSINESS > :line_of_business) OR (PRODUCT_PREFIX = :product_prefix AND PRODUCT_SUFFIX = :product_suffix AND EFFECTIVE_DATE = :effective_date AND TRANSACTION_CODE = :transaction_code AND TAX_YEAR = :tax_year AND LINE_OF_BUSINESS = :line_of_business AND CONTINUATION_IND > :continuation_ind) OR (PRODUCT_PREFIX = :product_prefix AND PRODUCT_SUFFIX = :product_suffix AND EFFECTIVE_DATE = :effective_date AND TRANSACTION_CODE = :transaction_code AND TAX_YEAR = :tax_year AND LINE_OF_BUSINESS = :line_of_business AND CONTINUATION_IND = :continuation_ind AND REMITTANCE_MAX_AGE > :remittance_max_age) OR (PRODUCT_PREFIX = :product_prefix AND PRODUCT_SUFFIX = :product_suffix AND EFFECTIVE_DATE = :effective_date AND TRANSACTION_CODE = :transaction_code AND TAX_YEAR = :tax_year AND LINE_OF_BUSINESS = :line_of_business AND CONTINUATION_IND = :continuation_ind AND REMITTANCE_MAX_AGE = :remittance_max_age AND ROLLOVER_CODE > :rollover_code) OR (PRODUCT_PREFIX = :product_prefix AND PRODUCT_SUFFIX = :product_suffix AND EFFECTIVE_DATE = :effective_date AND TRANSACTION_CODE = :transaction_code AND TAX_YEAR = :tax_year AND LINE_OF_BUSINESS = :line_of_business AND CONTINUATION_IND = :continuation_ind AND REMITTANCE_MAX_AGE = :remittance_max_age AND ROLLOVER_CODE = :rollover_code)) ";
                else
                    pagingWhere = ".T108X WHERE (COMPANY_CODE = :company_code) AND ((PRODUCT_PREFIX > :product_prefix) OR (PRODUCT_PREFIX = :product_prefix AND PRODUCT_SUFFIX > :product_suffix) OR (PRODUCT_PREFIX = :product_prefix AND PRODUCT_SUFFIX = :product_suffix AND EFFECTIVE_DATE > :effective_date) OR (PRODUCT_PREFIX = :product_prefix AND PRODUCT_SUFFIX = :product_suffix AND EFFECTIVE_DATE = :effective_date AND TRANSACTION_CODE > :transaction_code) OR (PRODUCT_PREFIX = :product_prefix AND PRODUCT_SUFFIX = :product_suffix AND EFFECTIVE_DATE = :effective_date AND TRANSACTION_CODE = :transaction_code AND TAX_YEAR > :tax_year) OR (PRODUCT_PREFIX = :product_prefix AND PRODUCT_SUFFIX = :product_suffix AND EFFECTIVE_DATE = :effective_date AND TRANSACTION_CODE = :transaction_code AND TAX_YEAR = :tax_year AND LINE_OF_BUSINESS > :line_of_business) OR (PRODUCT_PREFIX = :product_prefix AND PRODUCT_SUFFIX = :product_suffix AND EFFECTIVE_DATE = :effective_date AND TRANSACTION_CODE = :transaction_code AND TAX_YEAR = :tax_year AND LINE_OF_BUSINESS = :line_of_business AND CONTINUATION_IND > :continuation_ind) OR (PRODUCT_PREFIX = :product_prefix AND PRODUCT_SUFFIX = :product_suffix AND EFFECTIVE_DATE = :effective_date AND TRANSACTION_CODE = :transaction_code AND TAX_YEAR = :tax_year AND LINE_OF_BUSINESS = :line_of_business AND CONTINUATION_IND = :continuation_ind AND REMITTANCE_MAX_AGE > :remittance_max_age) OR (PRODUCT_PREFIX = :product_prefix AND PRODUCT_SUFFIX = :product_suffix AND EFFECTIVE_DATE = :effective_date AND TRANSACTION_CODE = :transaction_code AND TAX_YEAR = :tax_year AND LINE_OF_BUSINESS = :line_of_business AND CONTINUATION_IND = :continuation_ind AND REMITTANCE_MAX_AGE = :remittance_max_age AND ROLLOVER_CODE > :rollover_code)) ";
            order = " ORDER BY COMPANY_CODE, PRODUCT_PREFIX, PRODUCT_SUFFIX, EFFECTIVE_DATE, TRANSACTION_CODE, TAX_YEAR, LINE_OF_BUSINESS, CONTINUATION_IND, REMITTANCE_MAX_AGE, ROLLOVER_CODE";
        }
        else {
            if (isSubsetMode)
                pagingWhere = ".T108X WHERE (COMPANY_CODE = :company_code) AND (PRODUCT_PREFIX = :product_prefix) AND ((PRODUCT_SUFFIX < :product_suffix) OR (PRODUCT_SUFFIX = :product_suffix AND EFFECTIVE_DATE < :effective_date) OR (PRODUCT_SUFFIX = :product_suffix AND EFFECTIVE_DATE = :effective_date AND TRANSACTION_CODE < :transaction_code) OR (PRODUCT_SUFFIX = :product_suffix AND EFFECTIVE_DATE = :effective_date AND TRANSACTION_CODE = :transaction_code AND TAX_YEAR < :tax_year) OR (PRODUCT_SUFFIX = :product_suffix AND EFFECTIVE_DATE = :effective_date AND TRANSACTION_CODE = :transaction_code AND TAX_YEAR = :tax_year AND LINE_OF_BUSINESS < :line_of_business) OR (PRODUCT_SUFFIX = :product_suffix AND EFFECTIVE_DATE = :effective_date AND TRANSACTION_CODE = :transaction_code AND TAX_YEAR = :tax_year AND LINE_OF_BUSINESS = :line_of_business AND CONTINUATION_IND < :continuation_ind) OR (PRODUCT_SUFFIX = :product_suffix AND EFFECTIVE_DATE = :effective_date AND TRANSACTION_CODE = :transaction_code AND TAX_YEAR = :tax_year AND LINE_OF_BUSINESS = :line_of_business AND CONTINUATION_IND = :continuation_ind AND REMITTANCE_MAX_AGE < :remittance_max_age) OR (PRODUCT_SUFFIX = :product_suffix AND EFFECTIVE_DATE = :effective_date AND TRANSACTION_CODE = :transaction_code AND TAX_YEAR = :tax_year AND LINE_OF_BUSINESS = :line_of_business AND CONTINUATION_IND = :continuation_ind AND REMITTANCE_MAX_AGE = :remittance_max_age AND ROLLOVER_CODE < :rollover_code)) ";
            else
                pagingWhere = ".T108X WHERE (COMPANY_CODE = :company_code) AND ((PRODUCT_PREFIX < :product_prefix) OR (PRODUCT_PREFIX = :product_prefix AND PRODUCT_SUFFIX < :product_suffix) OR (PRODUCT_PREFIX = :product_prefix AND PRODUCT_SUFFIX = :product_suffix AND EFFECTIVE_DATE < :effective_date) OR (PRODUCT_PREFIX = :product_prefix AND PRODUCT_SUFFIX = :product_suffix AND EFFECTIVE_DATE = :effective_date AND TRANSACTION_CODE < :transaction_code) OR (PRODUCT_PREFIX = :product_prefix AND PRODUCT_SUFFIX = :product_suffix AND EFFECTIVE_DATE = :effective_date AND TRANSACTION_CODE = :transaction_code AND TAX_YEAR < :tax_year) OR (PRODUCT_PREFIX = :product_prefix AND PRODUCT_SUFFIX = :product_suffix AND EFFECTIVE_DATE = :effective_date AND TRANSACTION_CODE = :transaction_code AND TAX_YEAR = :tax_year AND LINE_OF_BUSINESS < :line_of_business) OR (PRODUCT_PREFIX = :product_prefix AND PRODUCT_SUFFIX = :product_suffix AND EFFECTIVE_DATE = :effective_date AND TRANSACTION_CODE = :transaction_code AND TAX_YEAR = :tax_year AND LINE_OF_BUSINESS = :line_of_business AND CONTINUATION_IND < :continuation_ind) OR (PRODUCT_PREFIX = :product_prefix AND PRODUCT_SUFFIX = :product_suffix AND EFFECTIVE_DATE = :effective_date AND TRANSACTION_CODE = :transaction_code AND TAX_YEAR = :tax_year AND LINE_OF_BUSINESS = :line_of_business AND CONTINUATION_IND = :continuation_ind AND REMITTANCE_MAX_AGE < :remittance_max_age) OR (PRODUCT_PREFIX = :product_prefix AND PRODUCT_SUFFIX = :product_suffix AND EFFECTIVE_DATE = :effective_date AND TRANSACTION_CODE = :transaction_code AND TAX_YEAR = :tax_year AND LINE_OF_BUSINESS = :line_of_business AND CONTINUATION_IND = :continuation_ind AND REMITTANCE_MAX_AGE = :remittance_max_age AND ROLLOVER_CODE < :rollover_code)) ";
            order = " ORDER BY COMPANY_CODE DESC, PRODUCT_PREFIX DESC, PRODUCT_SUFFIX DESC, EFFECTIVE_DATE DESC, TRANSACTION_CODE DESC, TAX_YEAR DESC, LINE_OF_BUSINESS DESC, CONTINUATION_IND DESC, REMITTANCE_MAX_AGE DESC, ROLLOVER_CODE DESC";
        }
        return pagingSql + schemaName + pagingWhere + order;
    }
    
    public String prepareInsertStmt(String schemaName) {
        StringBuffer sb = new StringBuffer();
        sb.append("INSERT INTO ").append(schemaName);
        sb.append(".T108X ( ");
        sb.append("COMPANY_CODE, PRODUCT_PREFIX, PRODUCT_SUFFIX, EFFECTIVE_DATE, TRANSACTION_CODE, TAX_YEAR, LINE_OF_BUSINESS, CONTINUATION_IND, REMITTANCE_MAX_AGE, ROLLOVER_CODE, MAX_AMOUNT, OVER_FIFTY_AMOUNT, MAX_VIOL_REJ_IND )");
        sb.append(" VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ? )");
        return sb.toString();
    }
    
    public String prepareUpdateStmt(String schemaName)
    {
        StringBuffer sb = new StringBuffer();
        sb.append("UPDATE ").append(schemaName);
        sb.append(".T108X ");
        sb.append(" SET COMPANY_CODE = ?, PRODUCT_PREFIX = ?, PRODUCT_SUFFIX = ?, EFFECTIVE_DATE = ?, TRANSACTION_CODE = ?, TAX_YEAR = ?, LINE_OF_BUSINESS = ?, CONTINUATION_IND = ?, REMITTANCE_MAX_AGE = ?, ROLLOVER_CODE = ?, MAX_AMOUNT = ?, OVER_FIFTY_AMOUNT = ?, MAX_VIOL_REJ_IND = ?");
        sb.append(" WHERE COMPANY_CODE = ? AND PRODUCT_PREFIX = ? AND PRODUCT_SUFFIX = ? AND EFFECTIVE_DATE = ? AND TRANSACTION_CODE = ? AND TAX_YEAR = ? AND LINE_OF_BUSINESS = ? AND CONTINUATION_IND = ? AND REMITTANCE_MAX_AGE = ? AND ROLLOVER_CODE = ?");
        return sb.toString();
    }
    
    public String prepareDeleteStmt(String schemaName)
    {
        StringBuffer sb = new StringBuffer();
        sb.append("DELETE FROM ").append(schemaName);
        sb.append(".T108X ");
        sb.append(" WHERE COMPANY_CODE = ? AND PRODUCT_PREFIX = ? AND PRODUCT_SUFFIX = ? AND EFFECTIVE_DATE = ? AND TRANSACTION_CODE = ? AND TAX_YEAR = ? AND LINE_OF_BUSINESS = ? AND CONTINUATION_IND = ? AND REMITTANCE_MAX_AGE = ? AND ROLLOVER_CODE = ?");
        return sb.toString();
    }
}
