package com.csc.fsg.life.pw.web.io.descriptor.wma;

import com.csc.fsg.life.pw.web.io.*;

public class T105XDescriptor
    extends TableDescriptor
{
    private static final String pagingSql = "SELECT COMPANY_CODE, YEAR, FUND_NUMBER, STATE_CODE, MONTH_01, DAY_01, MONTH_02, DAY_02, MONTH_03, DAY_03, MONTH_04, DAY_04, MONTH_05, DAY_05, MONTH_06, DAY_06, MONTH_07, DAY_07, MONTH_08, DAY_08, MONTH_09, DAY_09, MONTH_10, DAY_10, MONTH_11, DAY_11, MONTH_12, DAY_12, MONTH_13, DAY_13, MONTH_14, DAY_14, MONTH_15, DAY_15, MONTH_16, DAY_16, MONTH_17, DAY_17, MONTH_18, DAY_18, MONTH_19, DAY_19, MONTH_20, DAY_20, MONTH_21, DAY_21, MONTH_22, DAY_22, MONTH_23, DAY_23, MONTH_24, DAY_24, MONTH_25, DAY_25, MONTH_26, DAY_26, MONTH_27, DAY_27, MONTH_28, DAY_28, MONTH_29, DAY_29, MONTH_30, DAY_30 FROM ";
    
    public void initialize()
    {
        setRowClass(T105XRow.class);
        setTableName("T105X");
        setTableId("105");
        super.initialize();
    }
    
    public void initializeColumnDescriptors()
    {
        super.initializeColumnDescriptors();
        addColumnDescriptor(new ColumnDescriptor(this,"getCompanyCode","setCompanyCode","COMPANY_CODE,1,1,3,0,true|,0,CHAR,1,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getYear","setYear","YEAR,5,2,4,0,true|,0,INTEGER,4,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getFundNumber","setFundNumber","FUND_NUMBER,3,3,8,0,true|,0,INTEGER,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getStateCode","setStateCode","STATE_CODE,1,4,3,0,true|,0,CHAR,4,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getMonth01","setMonth01","MONTH_01,1,5,2,0,false|,0,CHAR,4,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getDay01","setDay01","DAY_01,1,6,2,0,false|,0,CHAR,4,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getMonth02","setMonth02","MONTH_02,1,7,2,0,false|,0,CHAR,4,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getDay02","setDay02","DAY_02,1,8,2,0,false|,0,CHAR,4,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getMonth03","setMonth03","MONTH_03,1,9,2,0,false|,0,CHAR,4,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getDay03","setDay03","DAY_03,1,10,2,0,false|,0,CHAR,4,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getMonth04","setMonth04","MONTH_04,1,11,2,0,false|,0,CHAR,4,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getDay04","setDay04","DAY_04,1,12,2,0,false|,0,CHAR,4,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getMonth05","setMonth05","MONTH_05,1,13,2,0,false|,0,CHAR,4,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getDay05","setDay05","DAY_05,1,14,2,0,false|,0,CHAR,4,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getMonth06","setMonth06","MONTH_06,1,15,2,0,false|,0,CHAR,4,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getDay06","setDay06","DAY_06,1,16,2,0,false|,0,CHAR,4,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getMonth07","setMonth07","MONTH_07,1,17,2,0,false|,0,CHAR,4,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getDay07","setDay07","DAY_07,1,18,2,0,false|,0,CHAR,4,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getMonth08","setMonth08","MONTH_08,1,19,2,0,false|,0,CHAR,4,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getDay08","setDay08","DAY_08,1,20,2,0,false|,0,CHAR,4,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getMonth09","setMonth09","MONTH_09,1,21,2,0,false|,0,CHAR,4,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getDay09","setDay09","DAY_09,1,22,2,0,false|,0,CHAR,4,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getMonth10","setMonth10","MONTH_10,1,23,2,0,false|,0,CHAR,4,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getDay10","setDay10","DAY_10,1,24,2,0,false|,0,CHAR,4,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getMonth11","setMonth11","MONTH_11,1,25,2,0,false|,0,CHAR,4,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getDay11","setDay11","DAY_11,1,26,2,0,false|,0,CHAR,4,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getMonth12","setMonth12","MONTH_12,1,27,2,0,false|,0,CHAR,4,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getDay12","setDay12","DAY_12,1,28,2,0,false|,0,CHAR,4,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getMonth13","setMonth13","MONTH_13,1,29,2,0,false|,0,CHAR,4,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getDay13","setDay13","DAY_13,1,30,2,0,false|,0,CHAR,4,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getMonth14","setMonth14","MONTH_14,1,31,2,0,false|,0,CHAR,4,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getDay14","setDay14","DAY_14,1,32,2,0,false|,0,CHAR,4,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getMonth15","setMonth15","MONTH_15,1,33,2,0,false|,0,CHAR,4,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getDay15","setDay15","DAY_15,1,34,2,0,false|,0,CHAR,4,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getMonth16","setMonth16","MONTH_16,1,35,2,0,false|,0,CHAR,4,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getDay16","setDay16","DAY_16,1,36,2,0,false|,0,CHAR,4,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getMonth17","setMonth17","MONTH_17,1,37,2,0,false|,0,CHAR,4,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getDay17","setDay17","DAY_17,1,38,2,0,false|,0,CHAR,4,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getMonth18","setMonth18","MONTH_18,1,39,2,0,false|,0,CHAR,4,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getDay18","setDay18","DAY_18,1,40,2,0,false|,0,CHAR,4,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getMonth19","setMonth19","MONTH_19,1,41,2,0,false|,0,CHAR,4,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getDay19","setDay19","DAY_19,1,42,2,0,false|,0,CHAR,4,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getMonth20","setMonth20","MONTH_20,1,43,2,0,false|,0,CHAR,4,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getDay20","setDay20","DAY_20,1,44,2,0,false|,0,CHAR,4,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getMonth21","setMonth21","MONTH_21,1,45,2,0,false|,0,CHAR,4,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getDay21","setDay21","DAY_21,1,46,2,0,false|,0,CHAR,4,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getMonth22","setMonth22","MONTH_22,1,47,2,0,false|,0,CHAR,4,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getDay22","setDay22","DAY_22,1,48,2,0,false|,0,CHAR,4,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getMonth23","setMonth23","MONTH_23,1,49,2,0,false|,0,CHAR,4,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getDay23","setDay23","DAY_23,1,50,2,0,false|,0,CHAR,4,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getMonth24","setMonth24","MONTH_24,1,51,2,0,false|,0,CHAR,4,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getDay24","setDay24","DAY_24,1,52,2,0,false|,0,CHAR,4,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getMonth25","setMonth25","MONTH_25,1,53,2,0,false|,0,CHAR,4,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getDay25","setDay25","DAY_25,1,54,2,0,false|,0,CHAR,4,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getMonth26","setMonth26","MONTH_26,1,55,2,0,false|,0,CHAR,4,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getDay26","setDay26","DAY_26,1,56,2,0,false|,0,CHAR,4,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getMonth27","setMonth27","MONTH_27,1,57,2,0,false|,0,CHAR,4,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getDay27","setDay27","DAY_27,1,58,2,0,false|,0,CHAR,4,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getMonth28","setMonth28","MONTH_28,1,59,2,0,false|,0,CHAR,4,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getDay28","setDay28","DAY_28,1,60,2,0,false|,0,CHAR,4,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getMonth29","setMonth29","MONTH_29,1,61,2,0,false|,0,CHAR,4,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getDay29","setDay29","DAY_29,1,62,2,0,false|,0,CHAR,4,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getMonth30","setMonth30","MONTH_30,1,63,2,0,false|,0,CHAR,4,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getDay30","setDay30","DAY_30,1,64,2,0,false|,0,CHAR,4,null,null,null,null,null"));
    }
    
    public String getPagingSQL(String schemaName,boolean isSubsetMode,boolean isNext, boolean locator)
    {
        String pagingWhere = null;
        String order = null;
        if (isNext) {
            if (isSubsetMode)
                if (locator)
                    pagingWhere = ".T105X WHERE (COMPANY_CODE = :company_code) AND ((YEAR > :year) OR (YEAR = :year AND FUND_NUMBER > :fund_number) OR (YEAR = :year AND FUND_NUMBER = :fund_number AND STATE_CODE > :state_code) OR (YEAR = :year AND FUND_NUMBER = :fund_number AND STATE_CODE = :state_code)) ";
                else 
                    pagingWhere = ".T105X WHERE (COMPANY_CODE = :company_code) AND ((YEAR > :year) OR (YEAR = :year AND FUND_NUMBER > :fund_number) OR (YEAR = :year AND FUND_NUMBER = :fund_number AND STATE_CODE > :state_code)) ";
            else
                if (locator)
                    pagingWhere = ".T105X WHERE (COMPANY_CODE = :company_code) AND ((YEAR > :year) OR (YEAR = :year AND FUND_NUMBER > :fund_number) OR (YEAR = :year AND FUND_NUMBER = :fund_number AND STATE_CODE > :state_code) OR (YEAR = :year AND FUND_NUMBER = :fund_number AND STATE_CODE = :state_code)) ";
                else
                    pagingWhere = ".T105X WHERE (COMPANY_CODE = :company_code) AND ((YEAR > :year) OR (YEAR = :year AND FUND_NUMBER > :fund_number) OR (YEAR = :year AND FUND_NUMBER = :fund_number AND STATE_CODE > :state_code)) ";
            order = " ORDER BY COMPANY_CODE, YEAR, FUND_NUMBER, STATE_CODE";
        }
        else {
            if (isSubsetMode)
                pagingWhere = ".T105X WHERE (COMPANY_CODE = :company_code) AND ((YEAR < :year) OR (YEAR = :year AND FUND_NUMBER < :fund_number) OR (YEAR = :year AND FUND_NUMBER = :fund_number AND STATE_CODE < :state_code)) ";
            else
                pagingWhere = ".T105X WHERE (COMPANY_CODE = :company_code) AND ((YEAR < :year) OR (YEAR = :year AND FUND_NUMBER < :fund_number) OR (YEAR = :year AND FUND_NUMBER = :fund_number AND STATE_CODE < :state_code)) ";
            order = " ORDER BY COMPANY_CODE DESC, YEAR DESC, FUND_NUMBER DESC, STATE_CODE DESC";
        }
        return pagingSql + schemaName + pagingWhere + order;
    }
    
    public String prepareInsertStmt(String schemaName) {
        StringBuffer sb = new StringBuffer();
        sb.append("INSERT INTO ").append(schemaName);
        sb.append(".T105X ( ");
        sb.append("COMPANY_CODE, YEAR, FUND_NUMBER, STATE_CODE, MONTH_01, DAY_01, MONTH_02, DAY_02, MONTH_03, DAY_03, MONTH_04, DAY_04, MONTH_05, DAY_05, MONTH_06, DAY_06, MONTH_07, DAY_07, MONTH_08, DAY_08, MONTH_09, DAY_09, MONTH_10, DAY_10, MONTH_11, DAY_11, MONTH_12, DAY_12, MONTH_13, DAY_13, MONTH_14, DAY_14, MONTH_15, DAY_15, MONTH_16, DAY_16, MONTH_17, DAY_17, MONTH_18, DAY_18, MONTH_19, DAY_19, MONTH_20, DAY_20, MONTH_21, DAY_21, MONTH_22, DAY_22, MONTH_23, DAY_23, MONTH_24, DAY_24, MONTH_25, DAY_25, MONTH_26, DAY_26, MONTH_27, DAY_27, MONTH_28, DAY_28, MONTH_29, DAY_29, MONTH_30, DAY_30 )");
        sb.append(" VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ? )");
        return sb.toString();
    }
    
    public String prepareUpdateStmt(String schemaName)
    {
        StringBuffer sb = new StringBuffer();
        sb.append("UPDATE ").append(schemaName);
        sb.append(".T105X ");
        sb.append(" SET COMPANY_CODE = ?, YEAR = ?, FUND_NUMBER = ?, STATE_CODE = ?, MONTH_01 = ?, DAY_01 = ?, MONTH_02 = ?, DAY_02 = ?, MONTH_03 = ?, DAY_03 = ?, MONTH_04 = ?, DAY_04 = ?, MONTH_05 = ?, DAY_05 = ?, MONTH_06 = ?, DAY_06 = ?, MONTH_07 = ?, DAY_07 = ?, MONTH_08 = ?, DAY_08 = ?, MONTH_09 = ?, DAY_09 = ?, MONTH_10 = ?, DAY_10 = ?, MONTH_11 = ?, DAY_11 = ?, MONTH_12 = ?, DAY_12 = ?, MONTH_13 = ?, DAY_13 = ?, MONTH_14 = ?, DAY_14 = ?, MONTH_15 = ?, DAY_15 = ?, MONTH_16 = ?, DAY_16 = ?, MONTH_17 = ?, DAY_17 = ?, MONTH_18 = ?, DAY_18 = ?, MONTH_19 = ?, DAY_19 = ?, MONTH_20 = ?, DAY_20 = ?, MONTH_21 = ?, DAY_21 = ?, MONTH_22 = ?, DAY_22 = ?, MONTH_23 = ?, DAY_23 = ?, MONTH_24 = ?, DAY_24 = ?, MONTH_25 = ?, DAY_25 = ?, MONTH_26 = ?, DAY_26 = ?, MONTH_27 = ?, DAY_27 = ?, MONTH_28 = ?, DAY_28 = ?, MONTH_29 = ?, DAY_29 = ?, MONTH_30 = ?, DAY_30 = ?");
        sb.append(" WHERE COMPANY_CODE = ? AND YEAR = ? AND FUND_NUMBER = ? AND STATE_CODE = ?");
        return sb.toString();
    }
    
    public String prepareDeleteStmt(String schemaName)
    {
        StringBuffer sb = new StringBuffer();
        sb.append("DELETE FROM ").append(schemaName);
        sb.append(".T105X ");
        sb.append(" WHERE COMPANY_CODE = ? AND YEAR = ? AND FUND_NUMBER = ? AND STATE_CODE = ?");
        return sb.toString();
    }
}
