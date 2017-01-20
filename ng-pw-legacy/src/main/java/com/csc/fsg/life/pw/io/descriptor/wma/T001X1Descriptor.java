package com.csc.fsg.life.pw.io.descriptor.wma;

import com.csc.fsg.life.pw.io.*;

public class T001X1Descriptor
    extends TableDescriptor
{
    private static final String pagingSql = "SELECT COMPANY_CODE, REPORT_NUMBER, DISTRIBUTION_CODE, COMPANY_NAME_CODE, REPORT_SEQ_NUMBER, REPORT_NAME_LINE1, REPORT_NAME_LINE2, OUTPUT_DESIRED_IND, NUMBER_OF_COPIES, SORT_FLD_01, PAGE_BRK_01, CNTRL_BRK_01, SORT_FLD_02, PAGE_BRK_02, CNTRL_BRK_02, SORT_FLD_03, PAGE_BRK_03, CNTRL_BRK_03, SORT_FLD_04, PAGE_BRK_04, CNTRL_BRK_04, SORT_FLD_05, PAGE_BRK_05, CNTRL_BRK_05, SORT_FLD_06, PAGE_BRK_06, CNTRL_BRK_06, SORT_FLD_07, PAGE_BRK_07, CNTRL_BRK_07, SORT_FLD_08, PAGE_BRK_08, CNTRL_BRK_08, SORT_FLD_09, PAGE_BRK_09, CNTRL_BRK_09, PROD_CODE_01, PROD_CODE_02, PROD_CODE_03, PROD_CODE_04, PROD_CODE_05, PROD_CODE_06, PROD_CODE_07, PROD_CODE_08, PROD_CODE_09, PROD_CODE_10, PROD_CODE_11, PROD_CODE_12, PROD_CODE_13, PROD_CODE_14, PROD_CODE_15, PROD_CODE_16, PROD_CODE_17, PROD_CODE_18, PROD_CODE_19, PROD_CODE_20 FROM ";
    
    public void initialize()
    {
        setRowClass(T001X1Row.class);
        setTableName("T001X1");
        setTableId("01X");
        super.initialize();
    }
    
    public void initializeColumnDescriptors()
    {
        super.initializeColumnDescriptors();
        addColumnDescriptor(new ColumnDescriptor(this,"getCompanyCode","setCompanyCode","COMPANY_CODE,1,1,3,0,true|,0,CHAR,1,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getReportNumber","setReportNumber","REPORT_NUMBER,1,2,3,0,true|,0,CHAR,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getDistributionCode","setDistributionCode","DISTRIBUTION_CODE,1,3,20,0,true|,0,CHAR,4,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getCompanyNameCode","setCompanyNameCode","COMPANY_NAME_CODE,1,4,1,0,false|,0,CHAR,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getReportSeqNumber","setReportSeqNumber","REPORT_SEQ_NUMBER,1,5,3,0,false|,0,CHAR,4,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getReportNameLine1","setReportNameLine1","REPORT_NAME_LINE1,1,6,50,0,false|,0,CHAR,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getReportNameLine2","setReportNameLine2","REPORT_NAME_LINE2,1,7,50,0,false|,0,CHAR,4,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getOutputDesiredInd","setOutputDesiredInd","OUTPUT_DESIRED_IND,1,8,1,0,false|,0,CHAR,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getNumberOfCopies","setNumberOfCopies","NUMBER_OF_COPIES,3,9,1,0,false|,0,INTEGER,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getSortFld01","setSortFld01","SORT_FLD_01,1,10,4,0,false|,0,CHAR,4,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getPageBrk01","setPageBrk01","PAGE_BRK_01,1,11,1,0,false|,0,CHAR,4,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getCntrlBrk01","setCntrlBrk01","CNTRL_BRK_01,1,12,1,0,false|,0,CHAR,4,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getSortFld02","setSortFld02","SORT_FLD_02,1,13,4,0,false|,0,CHAR,4,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getPageBrk02","setPageBrk02","PAGE_BRK_02,1,14,1,0,false|,0,CHAR,4,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getCntrlBrk02","setCntrlBrk02","CNTRL_BRK_02,1,15,1,0,false|,0,CHAR,4,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getSortFld03","setSortFld03","SORT_FLD_03,1,16,4,0,false|,0,CHAR,4,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getPageBrk03","setPageBrk03","PAGE_BRK_03,1,17,1,0,false|,0,CHAR,4,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getCntrlBrk03","setCntrlBrk03","CNTRL_BRK_03,1,18,1,0,false|,0,CHAR,4,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getSortFld04","setSortFld04","SORT_FLD_04,1,19,4,0,false|,0,CHAR,4,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getPageBrk04","setPageBrk04","PAGE_BRK_04,1,20,1,0,false|,0,CHAR,4,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getCntrlBrk04","setCntrlBrk04","CNTRL_BRK_04,1,21,1,0,false|,0,CHAR,4,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getSortFld05","setSortFld05","SORT_FLD_05,1,22,4,0,false|,0,CHAR,4,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getPageBrk05","setPageBrk05","PAGE_BRK_05,1,23,1,0,false|,0,CHAR,4,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getCntrlBrk05","setCntrlBrk05","CNTRL_BRK_05,1,24,1,0,false|,0,CHAR,4,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getSortFld06","setSortFld06","SORT_FLD_06,1,25,4,0,false|,0,CHAR,4,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getPageBrk06","setPageBrk06","PAGE_BRK_06,1,26,1,0,false|,0,CHAR,4,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getCntrlBrk06","setCntrlBrk06","CNTRL_BRK_06,1,27,1,0,false|,0,CHAR,4,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getSortFld07","setSortFld07","SORT_FLD_07,1,28,4,0,false|,0,CHAR,4,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getPageBrk07","setPageBrk07","PAGE_BRK_07,1,29,1,0,false|,0,CHAR,4,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getCntrlBrk07","setCntrlBrk07","CNTRL_BRK_07,1,30,1,0,false|,0,CHAR,4,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getSortFld08","setSortFld08","SORT_FLD_08,1,31,4,0,false|,0,CHAR,4,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getPageBrk08","setPageBrk08","PAGE_BRK_08,1,32,1,0,false|,0,CHAR,4,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getCntrlBrk08","setCntrlBrk08","CNTRL_BRK_08,1,33,1,0,false|,0,CHAR,4,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getSortFld09","setSortFld09","SORT_FLD_09,1,34,4,0,false|,0,CHAR,4,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getPageBrk09","setPageBrk09","PAGE_BRK_09,1,35,1,0,false|,0,CHAR,4,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getCntrlBrk09","setCntrlBrk09","CNTRL_BRK_09,1,36,1,0,false|,0,CHAR,4,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getProdCode01","setProdCode01","PROD_CODE_01,1,37,2,0,false|,0,CHAR,4,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getProdCode02","setProdCode02","PROD_CODE_02,1,38,2,0,false|,0,CHAR,4,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getProdCode03","setProdCode03","PROD_CODE_03,1,39,2,0,false|,0,CHAR,4,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getProdCode04","setProdCode04","PROD_CODE_04,1,40,2,0,false|,0,CHAR,4,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getProdCode05","setProdCode05","PROD_CODE_05,1,41,2,0,false|,0,CHAR,4,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getProdCode06","setProdCode06","PROD_CODE_06,1,42,2,0,false|,0,CHAR,4,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getProdCode07","setProdCode07","PROD_CODE_07,1,43,2,0,false|,0,CHAR,4,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getProdCode08","setProdCode08","PROD_CODE_08,1,44,2,0,false|,0,CHAR,4,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getProdCode09","setProdCode09","PROD_CODE_09,1,45,2,0,false|,0,CHAR,4,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getProdCode10","setProdCode10","PROD_CODE_10,1,46,2,0,false|,0,CHAR,4,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getProdCode11","setProdCode11","PROD_CODE_11,1,47,2,0,false|,0,CHAR,4,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getProdCode12","setProdCode12","PROD_CODE_12,1,48,2,0,false|,0,CHAR,4,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getProdCode13","setProdCode13","PROD_CODE_13,1,49,2,0,false|,0,CHAR,4,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getProdCode14","setProdCode14","PROD_CODE_14,1,50,2,0,false|,0,CHAR,4,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getProdCode15","setProdCode15","PROD_CODE_15,1,51,2,0,false|,0,CHAR,4,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getProdCode16","setProdCode16","PROD_CODE_16,1,52,2,0,false|,0,CHAR,4,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getProdCode17","setProdCode17","PROD_CODE_17,1,53,2,0,false|,0,CHAR,4,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getProdCode18","setProdCode18","PROD_CODE_18,1,54,2,0,false|,0,CHAR,4,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getProdCode19","setProdCode19","PROD_CODE_19,1,55,2,0,false|,0,CHAR,4,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getProdCode20","setProdCode20","PROD_CODE_20,1,56,2,0,false|,0,CHAR,4,null,null,null,null,null"));
    }
    
    public String getPagingSQL(String schemaName,boolean isSubsetMode,boolean isNext, boolean locator)
    {
        String pagingWhere = null;
        String order = null;
        if (isNext) {
            if (isSubsetMode)
                if (locator)
                    pagingWhere = ".T001X1 WHERE (COMPANY_CODE = :company_code) AND ((REPORT_NUMBER > :report_number) OR (REPORT_NUMBER = :report_number AND DISTRIBUTION_CODE > :distribution_code) OR (REPORT_NUMBER = :report_number AND DISTRIBUTION_CODE = :distribution_code)) ";
                else 
                    pagingWhere = ".T001X1 WHERE (COMPANY_CODE = :company_code) AND ((REPORT_NUMBER > :report_number) OR (REPORT_NUMBER = :report_number AND DISTRIBUTION_CODE > :distribution_code)) ";
            else
                if (locator)
                    pagingWhere = ".T001X1 WHERE (COMPANY_CODE = :company_code) AND ((REPORT_NUMBER > :report_number) OR (REPORT_NUMBER = :report_number AND DISTRIBUTION_CODE > :distribution_code) OR (REPORT_NUMBER = :report_number AND DISTRIBUTION_CODE = :distribution_code)) ";
                else
                    pagingWhere = ".T001X1 WHERE (COMPANY_CODE = :company_code) AND ((REPORT_NUMBER > :report_number) OR (REPORT_NUMBER = :report_number AND DISTRIBUTION_CODE > :distribution_code)) ";
            order = " ORDER BY COMPANY_CODE, REPORT_NUMBER, DISTRIBUTION_CODE";
        }
        else {
            if (isSubsetMode)
                pagingWhere = ".T001X1 WHERE (COMPANY_CODE = :company_code) AND ((REPORT_NUMBER < :report_number) OR (REPORT_NUMBER = :report_number AND DISTRIBUTION_CODE < :distribution_code)) ";
            else
                pagingWhere = ".T001X1 WHERE (COMPANY_CODE = :company_code) AND ((REPORT_NUMBER < :report_number) OR (REPORT_NUMBER = :report_number AND DISTRIBUTION_CODE < :distribution_code)) ";
            order = " ORDER BY COMPANY_CODE DESC, REPORT_NUMBER DESC, DISTRIBUTION_CODE DESC";
        }
        return pagingSql + schemaName + pagingWhere + order;
    }
    
    public String prepareInsertStmt(String schemaName) {
        StringBuffer sb = new StringBuffer();
        sb.append("INSERT INTO ").append(schemaName);
        sb.append(".T001X1 ( ");
        sb.append("COMPANY_CODE, REPORT_NUMBER, DISTRIBUTION_CODE, COMPANY_NAME_CODE, REPORT_SEQ_NUMBER, REPORT_NAME_LINE1, REPORT_NAME_LINE2, OUTPUT_DESIRED_IND, NUMBER_OF_COPIES, SORT_FLD_01, PAGE_BRK_01, CNTRL_BRK_01, SORT_FLD_02, PAGE_BRK_02, CNTRL_BRK_02, SORT_FLD_03, PAGE_BRK_03, CNTRL_BRK_03, SORT_FLD_04, PAGE_BRK_04, CNTRL_BRK_04, SORT_FLD_05, PAGE_BRK_05, CNTRL_BRK_05, SORT_FLD_06, PAGE_BRK_06, CNTRL_BRK_06, SORT_FLD_07, PAGE_BRK_07, CNTRL_BRK_07, SORT_FLD_08, PAGE_BRK_08, CNTRL_BRK_08, SORT_FLD_09, PAGE_BRK_09, CNTRL_BRK_09, PROD_CODE_01, PROD_CODE_02, PROD_CODE_03, PROD_CODE_04, PROD_CODE_05, PROD_CODE_06, PROD_CODE_07, PROD_CODE_08, PROD_CODE_09, PROD_CODE_10, PROD_CODE_11, PROD_CODE_12, PROD_CODE_13, PROD_CODE_14, PROD_CODE_15, PROD_CODE_16, PROD_CODE_17, PROD_CODE_18, PROD_CODE_19, PROD_CODE_20 )");
        sb.append(" VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ? )");
        return sb.toString();
    }
    
    public String prepareUpdateStmt(String schemaName)
    {
        StringBuffer sb = new StringBuffer();
        sb.append("UPDATE ").append(schemaName);
        sb.append(".T001X1 ");
        sb.append(" SET COMPANY_CODE = ?, REPORT_NUMBER = ?, DISTRIBUTION_CODE = ?, COMPANY_NAME_CODE = ?, REPORT_SEQ_NUMBER = ?, REPORT_NAME_LINE1 = ?, REPORT_NAME_LINE2 = ?, OUTPUT_DESIRED_IND = ?, NUMBER_OF_COPIES = ?, SORT_FLD_01 = ?, PAGE_BRK_01 = ?, CNTRL_BRK_01 = ?, SORT_FLD_02 = ?, PAGE_BRK_02 = ?, CNTRL_BRK_02 = ?, SORT_FLD_03 = ?, PAGE_BRK_03 = ?, CNTRL_BRK_03 = ?, SORT_FLD_04 = ?, PAGE_BRK_04 = ?, CNTRL_BRK_04 = ?, SORT_FLD_05 = ?, PAGE_BRK_05 = ?, CNTRL_BRK_05 = ?, SORT_FLD_06 = ?, PAGE_BRK_06 = ?, CNTRL_BRK_06 = ?, SORT_FLD_07 = ?, PAGE_BRK_07 = ?, CNTRL_BRK_07 = ?, SORT_FLD_08 = ?, PAGE_BRK_08 = ?, CNTRL_BRK_08 = ?, SORT_FLD_09 = ?, PAGE_BRK_09 = ?, CNTRL_BRK_09 = ?, PROD_CODE_01 = ?, PROD_CODE_02 = ?, PROD_CODE_03 = ?, PROD_CODE_04 = ?, PROD_CODE_05 = ?, PROD_CODE_06 = ?, PROD_CODE_07 = ?, PROD_CODE_08 = ?, PROD_CODE_09 = ?, PROD_CODE_10 = ?, PROD_CODE_11 = ?, PROD_CODE_12 = ?, PROD_CODE_13 = ?, PROD_CODE_14 = ?, PROD_CODE_15 = ?, PROD_CODE_16 = ?, PROD_CODE_17 = ?, PROD_CODE_18 = ?, PROD_CODE_19 = ?, PROD_CODE_20 = ?");
        sb.append(" WHERE COMPANY_CODE = ? AND REPORT_NUMBER = ? AND DISTRIBUTION_CODE = ?");
        return sb.toString();
    }
    
    public String prepareDeleteStmt(String schemaName)
    {
        StringBuffer sb = new StringBuffer();
        sb.append("DELETE FROM ").append(schemaName);
        sb.append(".T001X1 ");
        sb.append(" WHERE COMPANY_CODE = ? AND REPORT_NUMBER = ? AND DISTRIBUTION_CODE = ?");
        return sb.toString();
    }
}
