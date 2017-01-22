package com.csc.fsg.life.pw.web.io.descriptor.wma;

import com.csc.fsg.life.pw.web.io.*;

public class TTB5TDescriptor
    extends TableDescriptor
{
    private static final String pagingSql = "SELECT COMPANY_CODE, TABLE_SUBSET, EFFECTIVE_DATE, COMM_CLASS_CODE, COMM_CALC_METHOD, DIS_PAYMENT_IND, ANNUALIZATION_IND, CAN_CHRGBK_IND, SURR_CHRGBK_IND, LAPSE_CHRGBK_IND, MAX_RENEWAL_DUR, FLAT_EXT_REA_CD1, FLAT_EXT_REA_CD2, FLAT_EXT_REA_CD3, FLAT_EXT_REA_CD4, FLAT_EXT_REA_CD5, FLAT_EXT_REA_CD6, FLAT_EXT_REA_CD7, FLAT_EXT_REA_CD8, FLAT_EXT_REA_CD9, FLAT_EXT_REA_CD10, FLAT_EXT_INC_CD, SPCL_CL_CODE1, SPCL_CL_CODE2, SPCL_CL_CODE3, SPCL_CL_CODE4, SPCL_CL_CODE5, SPCL_CL_CODE6, SPCL_CL_CODE7, SPCL_CL_CODE8, SPCL_CL_CODE9, SPCL_CL_CODE10, SPCL_CL_INCLUS_CD, COMM_RATE_TBL_NUM, COMM_CRGBK_TBL_NUM, FACE_DEC_CRGBK_IND, OFF_MODBNDCRGBKIND FROM ";
    
    public void initialize()
    {
        setRowClass(TTB5TRow.class);
        setTableName("TTB5T");
        setTableId("TB5");
        super.initialize();
    }
    
    public void initializeColumnDescriptors()
    {
        super.initializeColumnDescriptors();
        addColumnDescriptor(new ColumnDescriptor(this,"getCompanyCode","setCompanyCode","COMPANY_CODE,1,1,3,0,true|,0,CHAR,1,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getTableSubset","setTableSubset","TABLE_SUBSET,1,2,16,0,true|,0,CHAR,1,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getEffectiveDate","setEffectiveDate","EFFECTIVE_DATE,91,3,10,0,true|,0,DATE,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getCommClassCode","setCommClassCode","COMM_CLASS_CODE,1,4,2,0,true|,0,CHAR,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getCommCalcMethod","setCommCalcMethod","COMM_CALC_METHOD,1,5,1,0,false|,0,CHAR,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getDisPaymentInd","setDisPaymentInd","DIS_PAYMENT_IND,1,6,1,0,false|,0,CHAR,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getAnnualizationInd","setAnnualizationInd","ANNUALIZATION_IND,1,7,1,0,false|,0,CHAR,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getCanChrgbkInd","setCanChrgbkInd","CAN_CHRGBK_IND,1,8,1,0,false|,0,CHAR,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getSurrChrgbkInd","setSurrChrgbkInd","SURR_CHRGBK_IND,1,9,1,0,false|,0,CHAR,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getLapseChrgbkInd","setLapseChrgbkInd","LAPSE_CHRGBK_IND,1,10,1,0,false|,0,CHAR,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getMaxRenewalDur","setMaxRenewalDur","MAX_RENEWAL_DUR,3,11,2,0,false|,0,INTEGER,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getFlatExtReaCd1","setFlatExtReaCd1","FLAT_EXT_REA_CD1,1,12,2,0,false|,0,CHAR,4,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getFlatExtReaCd2","setFlatExtReaCd2","FLAT_EXT_REA_CD2,1,13,2,0,false|,0,CHAR,4,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getFlatExtReaCd3","setFlatExtReaCd3","FLAT_EXT_REA_CD3,1,14,2,0,false|,0,CHAR,4,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getFlatExtReaCd4","setFlatExtReaCd4","FLAT_EXT_REA_CD4,1,15,2,0,false|,0,CHAR,4,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getFlatExtReaCd5","setFlatExtReaCd5","FLAT_EXT_REA_CD5,1,16,2,0,false|,0,CHAR,4,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getFlatExtReaCd6","setFlatExtReaCd6","FLAT_EXT_REA_CD6,1,17,2,0,false|,0,CHAR,4,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getFlatExtReaCd7","setFlatExtReaCd7","FLAT_EXT_REA_CD7,1,18,2,0,false|,0,CHAR,4,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getFlatExtReaCd8","setFlatExtReaCd8","FLAT_EXT_REA_CD8,1,19,2,0,false|,0,CHAR,4,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getFlatExtReaCd9","setFlatExtReaCd9","FLAT_EXT_REA_CD9,1,20,2,0,false|,0,CHAR,4,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getFlatExtReaCd10","setFlatExtReaCd10","FLAT_EXT_REA_CD10,1,21,2,0,false|,0,CHAR,4,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getFlatExtIncCd","setFlatExtIncCd","FLAT_EXT_INC_CD,1,22,1,0,false|,0,CHAR,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getSpclClCode1","setSpclClCode1","SPCL_CL_CODE1,1,23,2,0,false|,0,CHAR,4,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getSpclClCode2","setSpclClCode2","SPCL_CL_CODE2,1,24,2,0,false|,0,CHAR,4,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getSpclClCode3","setSpclClCode3","SPCL_CL_CODE3,1,25,2,0,false|,0,CHAR,4,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getSpclClCode4","setSpclClCode4","SPCL_CL_CODE4,1,26,2,0,false|,0,CHAR,4,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getSpclClCode5","setSpclClCode5","SPCL_CL_CODE5,1,27,2,0,false|,0,CHAR,4,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getSpclClCode6","setSpclClCode6","SPCL_CL_CODE6,1,28,2,0,false|,0,CHAR,4,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getSpclClCode7","setSpclClCode7","SPCL_CL_CODE7,1,29,2,0,false|,0,CHAR,4,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getSpclClCode8","setSpclClCode8","SPCL_CL_CODE8,1,30,2,0,false|,0,CHAR,4,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getSpclClCode9","setSpclClCode9","SPCL_CL_CODE9,1,31,2,0,false|,0,CHAR,4,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getSpclClCode10","setSpclClCode10","SPCL_CL_CODE10,1,32,2,0,false|,0,CHAR,4,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getSpclClInclusCd","setSpclClInclusCd","SPCL_CL_INCLUS_CD,1,33,1,0,false|,0,CHAR,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getCommRateTblNum","setCommRateTblNum","COMM_RATE_TBL_NUM,1,34,16,0,false|,0,CHAR,0,TB6,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getCommCrgbkTblNum","setCommCrgbkTblNum","COMM_CRGBK_TBL_NUM,1,35,16,0,false|,0,CHAR,0,TB7,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getFaceDecCrgbkInd","setFaceDecCrgbkInd","FACE_DEC_CRGBK_IND,1,36,1,0,false|,0,CHAR,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getOffModbndcrgbkind","setOffModbndcrgbkind","OFF_MODBNDCRGBKIND,1,37,1,0,false|,0,CHAR,0,null,null,null,null,null"));
    }
    
    public String getPagingSQL(String schemaName,boolean isSubsetMode,boolean isNext, boolean locator)
    {
        String pagingWhere = null;
        String order = null;
        if (isNext) {
            if (isSubsetMode)
                if (locator)
                    pagingWhere = ".TTB5T WHERE (COMPANY_CODE = :company_code) AND (TABLE_SUBSET = :table_subset) AND ((EFFECTIVE_DATE > :effective_date) OR (EFFECTIVE_DATE = :effective_date AND COMM_CLASS_CODE > :comm_class_code) OR (EFFECTIVE_DATE = :effective_date AND COMM_CLASS_CODE = :comm_class_code)) ";
                else 
                    pagingWhere = ".TTB5T WHERE (COMPANY_CODE = :company_code) AND (TABLE_SUBSET = :table_subset) AND ((EFFECTIVE_DATE > :effective_date) OR (EFFECTIVE_DATE = :effective_date AND COMM_CLASS_CODE > :comm_class_code)) ";
            else
                if (locator)
                    pagingWhere = ".TTB5T WHERE (COMPANY_CODE = :company_code) AND ((TABLE_SUBSET > :table_subset) OR (TABLE_SUBSET = :table_subset AND EFFECTIVE_DATE > :effective_date) OR (TABLE_SUBSET = :table_subset AND EFFECTIVE_DATE = :effective_date AND COMM_CLASS_CODE > :comm_class_code) OR (TABLE_SUBSET = :table_subset AND EFFECTIVE_DATE = :effective_date AND COMM_CLASS_CODE = :comm_class_code)) ";
                else
                    pagingWhere = ".TTB5T WHERE (COMPANY_CODE = :company_code) AND ((TABLE_SUBSET > :table_subset) OR (TABLE_SUBSET = :table_subset AND EFFECTIVE_DATE > :effective_date) OR (TABLE_SUBSET = :table_subset AND EFFECTIVE_DATE = :effective_date AND COMM_CLASS_CODE > :comm_class_code)) ";
            order = " ORDER BY COMPANY_CODE, TABLE_SUBSET, EFFECTIVE_DATE, COMM_CLASS_CODE";
        }
        else {
            if (isSubsetMode)
                pagingWhere = ".TTB5T WHERE (COMPANY_CODE = :company_code) AND (TABLE_SUBSET = :table_subset) AND ((EFFECTIVE_DATE < :effective_date) OR (EFFECTIVE_DATE = :effective_date AND COMM_CLASS_CODE < :comm_class_code)) ";
            else
                pagingWhere = ".TTB5T WHERE (COMPANY_CODE = :company_code) AND ((TABLE_SUBSET < :table_subset) OR (TABLE_SUBSET = :table_subset AND EFFECTIVE_DATE < :effective_date) OR (TABLE_SUBSET = :table_subset AND EFFECTIVE_DATE = :effective_date AND COMM_CLASS_CODE < :comm_class_code)) ";
            order = " ORDER BY COMPANY_CODE DESC, TABLE_SUBSET DESC, EFFECTIVE_DATE DESC, COMM_CLASS_CODE DESC";
        }
        return pagingSql + schemaName + pagingWhere + order;
    }
    
    public String prepareInsertStmt(String schemaName) {
        StringBuffer sb = new StringBuffer();
        sb.append("INSERT INTO ").append(schemaName);
        sb.append(".TTB5T ( ");
        sb.append("COMPANY_CODE, TABLE_SUBSET, EFFECTIVE_DATE, COMM_CLASS_CODE, COMM_CALC_METHOD, DIS_PAYMENT_IND, ANNUALIZATION_IND, CAN_CHRGBK_IND, SURR_CHRGBK_IND, LAPSE_CHRGBK_IND, MAX_RENEWAL_DUR, FLAT_EXT_REA_CD1, FLAT_EXT_REA_CD2, FLAT_EXT_REA_CD3, FLAT_EXT_REA_CD4, FLAT_EXT_REA_CD5, FLAT_EXT_REA_CD6, FLAT_EXT_REA_CD7, FLAT_EXT_REA_CD8, FLAT_EXT_REA_CD9, FLAT_EXT_REA_CD10, FLAT_EXT_INC_CD, SPCL_CL_CODE1, SPCL_CL_CODE2, SPCL_CL_CODE3, SPCL_CL_CODE4, SPCL_CL_CODE5, SPCL_CL_CODE6, SPCL_CL_CODE7, SPCL_CL_CODE8, SPCL_CL_CODE9, SPCL_CL_CODE10, SPCL_CL_INCLUS_CD, COMM_RATE_TBL_NUM, COMM_CRGBK_TBL_NUM, FACE_DEC_CRGBK_IND, OFF_MODBNDCRGBKIND )");
        sb.append(" VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ? )");
        return sb.toString();
    }
    
    public String prepareUpdateStmt(String schemaName)
    {
        StringBuffer sb = new StringBuffer();
        sb.append("UPDATE ").append(schemaName);
        sb.append(".TTB5T ");
        sb.append(" SET COMPANY_CODE = ?, TABLE_SUBSET = ?, EFFECTIVE_DATE = ?, COMM_CLASS_CODE = ?, COMM_CALC_METHOD = ?, DIS_PAYMENT_IND = ?, ANNUALIZATION_IND = ?, CAN_CHRGBK_IND = ?, SURR_CHRGBK_IND = ?, LAPSE_CHRGBK_IND = ?, MAX_RENEWAL_DUR = ?, FLAT_EXT_REA_CD1 = ?, FLAT_EXT_REA_CD2 = ?, FLAT_EXT_REA_CD3 = ?, FLAT_EXT_REA_CD4 = ?, FLAT_EXT_REA_CD5 = ?, FLAT_EXT_REA_CD6 = ?, FLAT_EXT_REA_CD7 = ?, FLAT_EXT_REA_CD8 = ?, FLAT_EXT_REA_CD9 = ?, FLAT_EXT_REA_CD10 = ?, FLAT_EXT_INC_CD = ?, SPCL_CL_CODE1 = ?, SPCL_CL_CODE2 = ?, SPCL_CL_CODE3 = ?, SPCL_CL_CODE4 = ?, SPCL_CL_CODE5 = ?, SPCL_CL_CODE6 = ?, SPCL_CL_CODE7 = ?, SPCL_CL_CODE8 = ?, SPCL_CL_CODE9 = ?, SPCL_CL_CODE10 = ?, SPCL_CL_INCLUS_CD = ?, COMM_RATE_TBL_NUM = ?, COMM_CRGBK_TBL_NUM = ?, FACE_DEC_CRGBK_IND = ?, OFF_MODBNDCRGBKIND = ?");
        sb.append(" WHERE COMPANY_CODE = ? AND TABLE_SUBSET = ? AND EFFECTIVE_DATE = ? AND COMM_CLASS_CODE = ?");
        return sb.toString();
    }
    
    public String prepareDeleteStmt(String schemaName)
    {
        StringBuffer sb = new StringBuffer();
        sb.append("DELETE FROM ").append(schemaName);
        sb.append(".TTB5T ");
        sb.append(" WHERE COMPANY_CODE = ? AND TABLE_SUBSET = ? AND EFFECTIVE_DATE = ? AND COMM_CLASS_CODE = ?");
        return sb.toString();
    }
}
