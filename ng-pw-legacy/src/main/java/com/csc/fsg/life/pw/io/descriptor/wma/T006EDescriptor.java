package com.csc.fsg.life.pw.io.descriptor.wma;

import com.csc.fsg.life.pw.io.*;

public class T006EDescriptor
    extends TableDescriptor
{
    private static final String pagingSql = "SELECT COMPANY_CODE, TABLE_SUBSET, RIDER_BENE_TYPE, CVG_TERM_BASIS, CVG_TERMINATION, PREM_TERM_BASIS, PREM_TERMINATION, MIN_CVG_UNITS, MAX_CVG_UNITS, M_SPOUSE_AGE_ADJ, F_SPOUSE_AGE_ADJ, SPCL_CLS_COMM_IND, RTG_FACT_COMM_IND, FLAT_X_COMM_IND, COI_METHOD, AVERAGING_YEARS, GUAR_INT_ADJ_IND FROM ";
    
    public void initialize()
    {
        setRowClass(T006ERow.class);
        setTableName("T006E");
        setTableId("006");
        super.initialize();
    }
    
    public void initializeColumnDescriptors()
    {
        super.initializeColumnDescriptors();
        addColumnDescriptor(new ColumnDescriptor(this,"getCompanyCode","setCompanyCode","COMPANY_CODE,1,1,3,0,true|,0,CHAR,1,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getTableSubset","setTableSubset","TABLE_SUBSET,1,2,16,0,true|,0,CHAR,1,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getRiderBeneType","setRiderBeneType","RIDER_BENE_TYPE,1,3,2,0,false|,0,CHAR,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getCvgTermBasis","setCvgTermBasis","CVG_TERM_BASIS,1,4,1,0,false|,0,CHAR,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getCvgTermination","setCvgTermination","CVG_TERMINATION,3,5,3,0,false|,0,INTEGER,4,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getPremTermBasis","setPremTermBasis","PREM_TERM_BASIS,1,6,1,0,false|,0,CHAR,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getPremTermination","setPremTermination","PREM_TERMINATION,3,7,3,0,false|,0,INTEGER,4,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getMinCvgUnits","setMinCvgUnits","MIN_CVG_UNITS,3,8,11,5,false|,0,DOUBLE,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getMaxCvgUnits","setMaxCvgUnits","MAX_CVG_UNITS,3,9,11,5,false|,0,DOUBLE,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getMSpouseAgeAdj","setMSpouseAgeAdj","M_SPOUSE_AGE_ADJ,3,10,3,0,false|,0,INTEGER,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getFSpouseAgeAdj","setFSpouseAgeAdj","F_SPOUSE_AGE_ADJ,3,11,3,0,false|,0,INTEGER,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getSpclClsCommInd","setSpclClsCommInd","SPCL_CLS_COMM_IND,1,12,1,0,false|,0,CHAR,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getRtgFactCommInd","setRtgFactCommInd","RTG_FACT_COMM_IND,1,13,1,0,false|,0,CHAR,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getFlatXCommInd","setFlatXCommInd","FLAT_X_COMM_IND,1,14,1,0,false|,0,CHAR,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getCoiMethod","setCoiMethod","COI_METHOD,1,15,1,0,false|,0,CHAR,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getAveragingYears","setAveragingYears","AVERAGING_YEARS,5,16,4,0,false|,0,INTEGER,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getGuarIntAdjInd","setGuarIntAdjInd","GUAR_INT_ADJ_IND,1,17,1,0,false|,0,CHAR,0,null,null,null,null,null"));
    }
    
    public String getPagingSQL(String schemaName,boolean isSubsetMode,boolean isNext, boolean locator)
    {
        String pagingWhere = null;
        String order = null;
        if (isNext) {
            if (isSubsetMode)
                if (locator)
                    pagingWhere = ".T006E WHERE (COMPANY_CODE = :company_code) AND (TABLE_SUBSET = :table_subset)";
                else 
                    pagingWhere = ".T006E WHERE (COMPANY_CODE = :company_code) AND (TABLE_SUBSET = :table_subset)";
            else
                if (locator)
                    pagingWhere = ".T006E WHERE (COMPANY_CODE = :company_code) AND ((TABLE_SUBSET > :table_subset) OR (TABLE_SUBSET = :table_subset)) ";
                else
                    pagingWhere = ".T006E WHERE (COMPANY_CODE = :company_code) AND ((TABLE_SUBSET > :table_subset)) ";
            order = " ORDER BY COMPANY_CODE, TABLE_SUBSET";
        }
        else {
            if (isSubsetMode)
                pagingWhere = ".T006E WHERE (COMPANY_CODE = :company_code) AND (TABLE_SUBSET = :table_subset)";
            else
                pagingWhere = ".T006E WHERE (COMPANY_CODE = :company_code) AND ((TABLE_SUBSET < :table_subset)) ";
            order = " ORDER BY COMPANY_CODE DESC, TABLE_SUBSET DESC";
        }
        return pagingSql + schemaName + pagingWhere + order;
    }
    
    public String prepareInsertStmt(String schemaName) {
        StringBuffer sb = new StringBuffer();
        sb.append("INSERT INTO ").append(schemaName);
        sb.append(".T006E ( ");
        sb.append("COMPANY_CODE, TABLE_SUBSET, RIDER_BENE_TYPE, CVG_TERM_BASIS, CVG_TERMINATION, PREM_TERM_BASIS, PREM_TERMINATION, MIN_CVG_UNITS, MAX_CVG_UNITS, M_SPOUSE_AGE_ADJ, F_SPOUSE_AGE_ADJ, SPCL_CLS_COMM_IND, RTG_FACT_COMM_IND, FLAT_X_COMM_IND, COI_METHOD, AVERAGING_YEARS, GUAR_INT_ADJ_IND )");
        sb.append(" VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ? )");
        return sb.toString();
    }
    
    public String prepareUpdateStmt(String schemaName)
    {
        StringBuffer sb = new StringBuffer();
        sb.append("UPDATE ").append(schemaName);
        sb.append(".T006E ");
        sb.append(" SET COMPANY_CODE = ?, TABLE_SUBSET = ?, RIDER_BENE_TYPE = ?, CVG_TERM_BASIS = ?, CVG_TERMINATION = ?, PREM_TERM_BASIS = ?, PREM_TERMINATION = ?, MIN_CVG_UNITS = ?, MAX_CVG_UNITS = ?, M_SPOUSE_AGE_ADJ = ?, F_SPOUSE_AGE_ADJ = ?, SPCL_CLS_COMM_IND = ?, RTG_FACT_COMM_IND = ?, FLAT_X_COMM_IND = ?, COI_METHOD = ?, AVERAGING_YEARS = ?, GUAR_INT_ADJ_IND = ?");
        sb.append(" WHERE COMPANY_CODE = ? AND TABLE_SUBSET = ?");
        return sb.toString();
    }
    
    public String prepareDeleteStmt(String schemaName)
    {
        StringBuffer sb = new StringBuffer();
        sb.append("DELETE FROM ").append(schemaName);
        sb.append(".T006E ");
        sb.append(" WHERE COMPANY_CODE = ? AND TABLE_SUBSET = ?");
        return sb.toString();
    }
}
