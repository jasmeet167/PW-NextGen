package com.csc.fsg.life.pw.web.io.descriptor.wma;

import com.csc.fsg.life.pw.web.io.*;

public class TEC5ZDescriptor
    extends TableDescriptor
{
    private static final String pagingSql = "SELECT COMPANY_CODE, TABLE_SUBSET, STATE_CODE, RESIDENT_STATE_CDE, AGE, FACE_AMOUNT_OF_INS, LINE_OF_BUSINESS, REPLACEMENT_IND, RULESET, REQRMTS_DATA_01, REQRMTS_DATA_02, REQRMTS_DATA_03, REQRMTS_DATA_04, REQRMTS_DATA_05, REQRMTS_DATA_06, REQRMTS_DATA_07, REQRMTS_DATA_08, REQRMTS_DATA_09, REQRMTS_DATA_10, REQRMTS_DATA_11, REQRMTS_DATA_12, REQRMTS_DATA_13, REQRMTS_DATA_14, REQRMTS_DATA_15, REQRMTS_DATA_16, REQRMTS_DATA_17, REQRMTS_DATA_18, REQRMTS_DATA_19, REQRMTS_DATA_20, REQRMTS_DATA_21, REQRMTS_DATA_22, REQRMTS_DATA_23, REQRMTS_DATA_24, REQRMTS_DATA_25, REQRMTS_DATA_26, REQRMTS_DATA_27, REQRMTS_DATA_28, REQRMTS_DATA_29, REQRMTS_DATA_30, REQRMTS_DATA_31, REQRMTS_DATA_32, REQRMTS_DATA_33, REQRMTS_DATA_34, REQRMTS_DATA_35, REQRMTS_DATA_36, REQRMTS_DATA_37, REQRMTS_DATA_38, REQRMTS_DATA_39, REQRMTS_DATA_40 FROM ";
    
    public void initialize()
    {
        setRowClass(TEC5ZRow.class);
        setTableName("TEC5Z");
        setTableId("EC5");
        super.initialize();
    }
    
    public void initializeColumnDescriptors()
    {
        super.initializeColumnDescriptors();
        addColumnDescriptor(new ColumnDescriptor(this,"getCompanyCode","setCompanyCode","COMPANY_CODE,1,1,3,0,true|,0,CHAR,1,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getTableSubset","setTableSubset","TABLE_SUBSET,1,2,16,0,true|,0,CHAR,1,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getStateCode","setStateCode","STATE_CODE,1,3,3,0,true|,0,CHAR,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getResidentStateCde","setResidentStateCde","RESIDENT_STATE_CDE,1,4,3,0,true|,0,CHAR,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getAge","setAge","AGE,3,5,3,0,true|,0,INTEGER,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getFaceAmountOfIns","setFaceAmountOfIns","FACE_AMOUNT_OF_INS,3,6,11,5,true|,0,DOUBLE,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getLineOfBusiness","setLineOfBusiness","LINE_OF_BUSINESS,1,7,3,0,true|,0,CHAR,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getReplacementInd","setReplacementInd","REPLACEMENT_IND,1,8,2,0,true|,0,CHAR,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getRuleset","setRuleset","RULESET,1,9,8,0,false|,0,CHAR,4,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getReqrmtsData01","setReqrmtsData01","REQRMTS_DATA_01,1,10,7,0,false|,0,CHAR,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getReqrmtsData02","setReqrmtsData02","REQRMTS_DATA_02,1,11,7,0,false|,0,CHAR,4,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getReqrmtsData03","setReqrmtsData03","REQRMTS_DATA_03,1,12,7,0,false|,0,CHAR,4,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getReqrmtsData04","setReqrmtsData04","REQRMTS_DATA_04,1,13,7,0,false|,0,CHAR,4,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getReqrmtsData05","setReqrmtsData05","REQRMTS_DATA_05,1,14,7,0,false|,0,CHAR,4,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getReqrmtsData06","setReqrmtsData06","REQRMTS_DATA_06,1,15,7,0,false|,0,CHAR,4,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getReqrmtsData07","setReqrmtsData07","REQRMTS_DATA_07,1,16,7,0,false|,0,CHAR,4,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getReqrmtsData08","setReqrmtsData08","REQRMTS_DATA_08,1,17,7,0,false|,0,CHAR,4,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getReqrmtsData09","setReqrmtsData09","REQRMTS_DATA_09,1,18,7,0,false|,0,CHAR,4,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getReqrmtsData10","setReqrmtsData10","REQRMTS_DATA_10,1,19,7,0,false|,0,CHAR,4,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getReqrmtsData11","setReqrmtsData11","REQRMTS_DATA_11,1,20,7,0,false|,0,CHAR,4,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getReqrmtsData12","setReqrmtsData12","REQRMTS_DATA_12,1,21,7,0,false|,0,CHAR,4,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getReqrmtsData13","setReqrmtsData13","REQRMTS_DATA_13,1,22,7,0,false|,0,CHAR,4,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getReqrmtsData14","setReqrmtsData14","REQRMTS_DATA_14,1,23,7,0,false|,0,CHAR,4,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getReqrmtsData15","setReqrmtsData15","REQRMTS_DATA_15,1,24,7,0,false|,0,CHAR,4,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getReqrmtsData16","setReqrmtsData16","REQRMTS_DATA_16,1,25,7,0,false|,0,CHAR,4,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getReqrmtsData17","setReqrmtsData17","REQRMTS_DATA_17,1,26,7,0,false|,0,CHAR,4,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getReqrmtsData18","setReqrmtsData18","REQRMTS_DATA_18,1,27,7,0,false|,0,CHAR,4,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getReqrmtsData19","setReqrmtsData19","REQRMTS_DATA_19,1,28,7,0,false|,0,CHAR,4,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getReqrmtsData20","setReqrmtsData20","REQRMTS_DATA_20,1,29,7,0,false|,0,CHAR,4,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getReqrmtsData21","setReqrmtsData21","REQRMTS_DATA_21,1,30,7,0,false|,0,CHAR,4,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getReqrmtsData22","setReqrmtsData22","REQRMTS_DATA_22,1,31,7,0,false|,0,CHAR,4,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getReqrmtsData23","setReqrmtsData23","REQRMTS_DATA_23,1,32,7,0,false|,0,CHAR,4,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getReqrmtsData24","setReqrmtsData24","REQRMTS_DATA_24,1,33,7,0,false|,0,CHAR,4,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getReqrmtsData25","setReqrmtsData25","REQRMTS_DATA_25,1,34,7,0,false|,0,CHAR,4,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getReqrmtsData26","setReqrmtsData26","REQRMTS_DATA_26,1,35,7,0,false|,0,CHAR,4,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getReqrmtsData27","setReqrmtsData27","REQRMTS_DATA_27,1,36,7,0,false|,0,CHAR,4,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getReqrmtsData28","setReqrmtsData28","REQRMTS_DATA_28,1,37,7,0,false|,0,CHAR,4,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getReqrmtsData29","setReqrmtsData29","REQRMTS_DATA_29,1,38,7,0,false|,0,CHAR,4,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getReqrmtsData30","setReqrmtsData30","REQRMTS_DATA_30,1,39,7,0,false|,0,CHAR,4,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getReqrmtsData31","setReqrmtsData31","REQRMTS_DATA_31,1,40,7,0,false|,0,CHAR,4,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getReqrmtsData32","setReqrmtsData32","REQRMTS_DATA_32,1,41,7,0,false|,0,CHAR,4,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getReqrmtsData33","setReqrmtsData33","REQRMTS_DATA_33,1,42,7,0,false|,0,CHAR,4,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getReqrmtsData34","setReqrmtsData34","REQRMTS_DATA_34,1,43,7,0,false|,0,CHAR,4,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getReqrmtsData35","setReqrmtsData35","REQRMTS_DATA_35,1,44,7,0,false|,0,CHAR,4,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getReqrmtsData36","setReqrmtsData36","REQRMTS_DATA_36,1,45,7,0,false|,0,CHAR,4,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getReqrmtsData37","setReqrmtsData37","REQRMTS_DATA_37,1,46,7,0,false|,0,CHAR,4,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getReqrmtsData38","setReqrmtsData38","REQRMTS_DATA_38,1,47,7,0,false|,0,CHAR,4,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getReqrmtsData39","setReqrmtsData39","REQRMTS_DATA_39,1,48,7,0,false|,0,CHAR,4,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getReqrmtsData40","setReqrmtsData40","REQRMTS_DATA_40,1,49,7,0,false|,0,CHAR,4,null,null,null,null,null"));
    }
    
    public String getPagingSQL(String schemaName,boolean isSubsetMode,boolean isNext, boolean locator)
    {
        String pagingWhere = null;
        String order = null;
        if (isNext) {
            if (isSubsetMode)
                if (locator)
                    pagingWhere = ".TEC5Z WHERE (COMPANY_CODE = :company_code) AND (TABLE_SUBSET = :table_subset) AND ((STATE_CODE > :state_code) OR (STATE_CODE = :state_code AND RESIDENT_STATE_CDE > :resident_state_cde) OR (STATE_CODE = :state_code AND RESIDENT_STATE_CDE = :resident_state_cde AND AGE > :age) OR (STATE_CODE = :state_code AND RESIDENT_STATE_CDE = :resident_state_cde AND AGE = :age AND FACE_AMOUNT_OF_INS > :face_amount_of_ins) OR (STATE_CODE = :state_code AND RESIDENT_STATE_CDE = :resident_state_cde AND AGE = :age AND FACE_AMOUNT_OF_INS = :face_amount_of_ins AND LINE_OF_BUSINESS > :line_of_business) OR (STATE_CODE = :state_code AND RESIDENT_STATE_CDE = :resident_state_cde AND AGE = :age AND FACE_AMOUNT_OF_INS = :face_amount_of_ins AND LINE_OF_BUSINESS = :line_of_business AND REPLACEMENT_IND > :replacement_ind) OR (STATE_CODE = :state_code AND RESIDENT_STATE_CDE = :resident_state_cde AND AGE = :age AND FACE_AMOUNT_OF_INS = :face_amount_of_ins AND LINE_OF_BUSINESS = :line_of_business AND REPLACEMENT_IND = :replacement_ind)) ";
                else 
                    pagingWhere = ".TEC5Z WHERE (COMPANY_CODE = :company_code) AND (TABLE_SUBSET = :table_subset) AND ((STATE_CODE > :state_code) OR (STATE_CODE = :state_code AND RESIDENT_STATE_CDE > :resident_state_cde) OR (STATE_CODE = :state_code AND RESIDENT_STATE_CDE = :resident_state_cde AND AGE > :age) OR (STATE_CODE = :state_code AND RESIDENT_STATE_CDE = :resident_state_cde AND AGE = :age AND FACE_AMOUNT_OF_INS > :face_amount_of_ins) OR (STATE_CODE = :state_code AND RESIDENT_STATE_CDE = :resident_state_cde AND AGE = :age AND FACE_AMOUNT_OF_INS = :face_amount_of_ins AND LINE_OF_BUSINESS > :line_of_business) OR (STATE_CODE = :state_code AND RESIDENT_STATE_CDE = :resident_state_cde AND AGE = :age AND FACE_AMOUNT_OF_INS = :face_amount_of_ins AND LINE_OF_BUSINESS = :line_of_business AND REPLACEMENT_IND > :replacement_ind)) ";
            else
                if (locator)
                    pagingWhere = ".TEC5Z WHERE (COMPANY_CODE = :company_code) AND ((TABLE_SUBSET > :table_subset) OR (TABLE_SUBSET = :table_subset AND STATE_CODE > :state_code) OR (TABLE_SUBSET = :table_subset AND STATE_CODE = :state_code AND RESIDENT_STATE_CDE > :resident_state_cde) OR (TABLE_SUBSET = :table_subset AND STATE_CODE = :state_code AND RESIDENT_STATE_CDE = :resident_state_cde AND AGE > :age) OR (TABLE_SUBSET = :table_subset AND STATE_CODE = :state_code AND RESIDENT_STATE_CDE = :resident_state_cde AND AGE = :age AND FACE_AMOUNT_OF_INS > :face_amount_of_ins) OR (TABLE_SUBSET = :table_subset AND STATE_CODE = :state_code AND RESIDENT_STATE_CDE = :resident_state_cde AND AGE = :age AND FACE_AMOUNT_OF_INS = :face_amount_of_ins AND LINE_OF_BUSINESS > :line_of_business) OR (TABLE_SUBSET = :table_subset AND STATE_CODE = :state_code AND RESIDENT_STATE_CDE = :resident_state_cde AND AGE = :age AND FACE_AMOUNT_OF_INS = :face_amount_of_ins AND LINE_OF_BUSINESS = :line_of_business AND REPLACEMENT_IND > :replacement_ind) OR (TABLE_SUBSET = :table_subset AND STATE_CODE = :state_code AND RESIDENT_STATE_CDE = :resident_state_cde AND AGE = :age AND FACE_AMOUNT_OF_INS = :face_amount_of_ins AND LINE_OF_BUSINESS = :line_of_business AND REPLACEMENT_IND = :replacement_ind)) ";
                else
                    pagingWhere = ".TEC5Z WHERE (COMPANY_CODE = :company_code) AND ((TABLE_SUBSET > :table_subset) OR (TABLE_SUBSET = :table_subset AND STATE_CODE > :state_code) OR (TABLE_SUBSET = :table_subset AND STATE_CODE = :state_code AND RESIDENT_STATE_CDE > :resident_state_cde) OR (TABLE_SUBSET = :table_subset AND STATE_CODE = :state_code AND RESIDENT_STATE_CDE = :resident_state_cde AND AGE > :age) OR (TABLE_SUBSET = :table_subset AND STATE_CODE = :state_code AND RESIDENT_STATE_CDE = :resident_state_cde AND AGE = :age AND FACE_AMOUNT_OF_INS > :face_amount_of_ins) OR (TABLE_SUBSET = :table_subset AND STATE_CODE = :state_code AND RESIDENT_STATE_CDE = :resident_state_cde AND AGE = :age AND FACE_AMOUNT_OF_INS = :face_amount_of_ins AND LINE_OF_BUSINESS > :line_of_business) OR (TABLE_SUBSET = :table_subset AND STATE_CODE = :state_code AND RESIDENT_STATE_CDE = :resident_state_cde AND AGE = :age AND FACE_AMOUNT_OF_INS = :face_amount_of_ins AND LINE_OF_BUSINESS = :line_of_business AND REPLACEMENT_IND > :replacement_ind)) ";
            order = " ORDER BY COMPANY_CODE, TABLE_SUBSET, STATE_CODE, RESIDENT_STATE_CDE, AGE, FACE_AMOUNT_OF_INS, LINE_OF_BUSINESS, REPLACEMENT_IND";
        }
        else {
            if (isSubsetMode)
                pagingWhere = ".TEC5Z WHERE (COMPANY_CODE = :company_code) AND (TABLE_SUBSET = :table_subset) AND ((STATE_CODE < :state_code) OR (STATE_CODE = :state_code AND RESIDENT_STATE_CDE < :resident_state_cde) OR (STATE_CODE = :state_code AND RESIDENT_STATE_CDE = :resident_state_cde AND AGE < :age) OR (STATE_CODE = :state_code AND RESIDENT_STATE_CDE = :resident_state_cde AND AGE = :age AND FACE_AMOUNT_OF_INS < :face_amount_of_ins) OR (STATE_CODE = :state_code AND RESIDENT_STATE_CDE = :resident_state_cde AND AGE = :age AND FACE_AMOUNT_OF_INS = :face_amount_of_ins AND LINE_OF_BUSINESS < :line_of_business) OR (STATE_CODE = :state_code AND RESIDENT_STATE_CDE = :resident_state_cde AND AGE = :age AND FACE_AMOUNT_OF_INS = :face_amount_of_ins AND LINE_OF_BUSINESS = :line_of_business AND REPLACEMENT_IND < :replacement_ind)) ";
            else
                pagingWhere = ".TEC5Z WHERE (COMPANY_CODE = :company_code) AND ((TABLE_SUBSET < :table_subset) OR (TABLE_SUBSET = :table_subset AND STATE_CODE < :state_code) OR (TABLE_SUBSET = :table_subset AND STATE_CODE = :state_code AND RESIDENT_STATE_CDE < :resident_state_cde) OR (TABLE_SUBSET = :table_subset AND STATE_CODE = :state_code AND RESIDENT_STATE_CDE = :resident_state_cde AND AGE < :age) OR (TABLE_SUBSET = :table_subset AND STATE_CODE = :state_code AND RESIDENT_STATE_CDE = :resident_state_cde AND AGE = :age AND FACE_AMOUNT_OF_INS < :face_amount_of_ins) OR (TABLE_SUBSET = :table_subset AND STATE_CODE = :state_code AND RESIDENT_STATE_CDE = :resident_state_cde AND AGE = :age AND FACE_AMOUNT_OF_INS = :face_amount_of_ins AND LINE_OF_BUSINESS < :line_of_business) OR (TABLE_SUBSET = :table_subset AND STATE_CODE = :state_code AND RESIDENT_STATE_CDE = :resident_state_cde AND AGE = :age AND FACE_AMOUNT_OF_INS = :face_amount_of_ins AND LINE_OF_BUSINESS = :line_of_business AND REPLACEMENT_IND < :replacement_ind)) ";
            order = " ORDER BY COMPANY_CODE DESC, TABLE_SUBSET DESC, STATE_CODE DESC, RESIDENT_STATE_CDE DESC, AGE DESC, FACE_AMOUNT_OF_INS DESC, LINE_OF_BUSINESS DESC, REPLACEMENT_IND DESC";
        }
        return pagingSql + schemaName + pagingWhere + order;
    }
    
    public String prepareInsertStmt(String schemaName) {
        StringBuffer sb = new StringBuffer();
        sb.append("INSERT INTO ").append(schemaName);
        sb.append(".TEC5Z ( ");
        sb.append("COMPANY_CODE, TABLE_SUBSET, STATE_CODE, RESIDENT_STATE_CDE, AGE, FACE_AMOUNT_OF_INS, LINE_OF_BUSINESS, REPLACEMENT_IND, RULESET, REQRMTS_DATA_01, REQRMTS_DATA_02, REQRMTS_DATA_03, REQRMTS_DATA_04, REQRMTS_DATA_05, REQRMTS_DATA_06, REQRMTS_DATA_07, REQRMTS_DATA_08, REQRMTS_DATA_09, REQRMTS_DATA_10, REQRMTS_DATA_11, REQRMTS_DATA_12, REQRMTS_DATA_13, REQRMTS_DATA_14, REQRMTS_DATA_15, REQRMTS_DATA_16, REQRMTS_DATA_17, REQRMTS_DATA_18, REQRMTS_DATA_19, REQRMTS_DATA_20, REQRMTS_DATA_21, REQRMTS_DATA_22, REQRMTS_DATA_23, REQRMTS_DATA_24, REQRMTS_DATA_25, REQRMTS_DATA_26, REQRMTS_DATA_27, REQRMTS_DATA_28, REQRMTS_DATA_29, REQRMTS_DATA_30, REQRMTS_DATA_31, REQRMTS_DATA_32, REQRMTS_DATA_33, REQRMTS_DATA_34, REQRMTS_DATA_35, REQRMTS_DATA_36, REQRMTS_DATA_37, REQRMTS_DATA_38, REQRMTS_DATA_39, REQRMTS_DATA_40 )");
        sb.append(" VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ? )");
        return sb.toString();
    }
    
    public String prepareUpdateStmt(String schemaName)
    {
        StringBuffer sb = new StringBuffer();
        sb.append("UPDATE ").append(schemaName);
        sb.append(".TEC5Z ");
        sb.append(" SET COMPANY_CODE = ?, TABLE_SUBSET = ?, STATE_CODE = ?, RESIDENT_STATE_CDE = ?, AGE = ?, FACE_AMOUNT_OF_INS = ?, LINE_OF_BUSINESS = ?, REPLACEMENT_IND = ?, RULESET = ?, REQRMTS_DATA_01 = ?, REQRMTS_DATA_02 = ?, REQRMTS_DATA_03 = ?, REQRMTS_DATA_04 = ?, REQRMTS_DATA_05 = ?, REQRMTS_DATA_06 = ?, REQRMTS_DATA_07 = ?, REQRMTS_DATA_08 = ?, REQRMTS_DATA_09 = ?, REQRMTS_DATA_10 = ?, REQRMTS_DATA_11 = ?, REQRMTS_DATA_12 = ?, REQRMTS_DATA_13 = ?, REQRMTS_DATA_14 = ?, REQRMTS_DATA_15 = ?, REQRMTS_DATA_16 = ?, REQRMTS_DATA_17 = ?, REQRMTS_DATA_18 = ?, REQRMTS_DATA_19 = ?, REQRMTS_DATA_20 = ?, REQRMTS_DATA_21 = ?, REQRMTS_DATA_22 = ?, REQRMTS_DATA_23 = ?, REQRMTS_DATA_24 = ?, REQRMTS_DATA_25 = ?, REQRMTS_DATA_26 = ?, REQRMTS_DATA_27 = ?, REQRMTS_DATA_28 = ?, REQRMTS_DATA_29 = ?, REQRMTS_DATA_30 = ?, REQRMTS_DATA_31 = ?, REQRMTS_DATA_32 = ?, REQRMTS_DATA_33 = ?, REQRMTS_DATA_34 = ?, REQRMTS_DATA_35 = ?, REQRMTS_DATA_36 = ?, REQRMTS_DATA_37 = ?, REQRMTS_DATA_38 = ?, REQRMTS_DATA_39 = ?, REQRMTS_DATA_40 = ?");
        sb.append(" WHERE COMPANY_CODE = ? AND TABLE_SUBSET = ? AND STATE_CODE = ? AND RESIDENT_STATE_CDE = ? AND AGE = ? AND FACE_AMOUNT_OF_INS = ? AND LINE_OF_BUSINESS = ? AND REPLACEMENT_IND = ?");
        return sb.toString();
    }
    
    public String prepareDeleteStmt(String schemaName)
    {
        StringBuffer sb = new StringBuffer();
        sb.append("DELETE FROM ").append(schemaName);
        sb.append(".TEC5Z ");
        sb.append(" WHERE COMPANY_CODE = ? AND TABLE_SUBSET = ? AND STATE_CODE = ? AND RESIDENT_STATE_CDE = ? AND AGE = ? AND FACE_AMOUNT_OF_INS = ? AND LINE_OF_BUSINESS = ? AND REPLACEMENT_IND = ?");
        return sb.toString();
    }
}
