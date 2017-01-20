package com.csc.fsg.life.pw.io.descriptor.wma;

import com.csc.fsg.life.pw.io.*;

public class TTB4TDescriptor
    extends TableDescriptor
{
    private static final String pagingSql = "SELECT COMPANY_CODE, TABLE_SUBSET, EFFECTIVE_DATE, POLICY_STATUS, MAX_FACE_AMOUNT, SEX, TRX_CODE, PREMIUM_CLASS_CODE, RTD_SPCL_PRM_CLASS, DCL_DV_RT_TB_ST_IN, DCL_DV_RT_SEL_PRD, DCL_DV_RT_TBL, PD_PC_INC_TB_ST_IN, PD_PC_INC_SEL_PRD, PD_PC_INC_TBL, PUA_FL_CH_TB_ST_IN, PUA_FL_CH_SEL_PRD, PUA_FL_CH_TBL, BAS_FL_CH_TB_ST_IN, BAS_FL_CH_SEL_PRD, BAS_FL_CH_TBL FROM ";
    
    public void initialize()
    {
        setRowClass(TTB4TRow.class);
        setTableName("TTB4T");
        setTableId("TB4");
        super.initialize();
    }
    
    public void initializeColumnDescriptors()
    {
        super.initializeColumnDescriptors();
        addColumnDescriptor(new ColumnDescriptor(this,"getCompanyCode","setCompanyCode","COMPANY_CODE,1,1,3,0,true|,0,CHAR,1,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getTableSubset","setTableSubset","TABLE_SUBSET,1,2,16,0,true|,0,CHAR,1,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getEffectiveDate","setEffectiveDate","EFFECTIVE_DATE,91,3,10,0,true|,0,DATE,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getPolicyStatus","setPolicyStatus","POLICY_STATUS,1,4,1,0,true|,0,CHAR,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getMaxFaceAmount","setMaxFaceAmount","MAX_FACE_AMOUNT,3,5,8,0,true|,0,INTEGER,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getSex","setSex","SEX,1,6,1,0,true|,0,CHAR,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getTrxCode","setTrxCode","TRX_CODE,1,7,4,0,true|,0,CHAR,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getPremiumClassCode","setPremiumClassCode","PREMIUM_CLASS_CODE,1,8,1,0,true|,0,CHAR,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getRtdSpclPrmClass","setRtdSpclPrmClass","RTD_SPCL_PRM_CLASS,1,9,2,0,true|,0,CHAR,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getDclDvRtTbStIn","setDclDvRtTbStIn","DCL_DV_RT_TB_ST_IN,1,10,1,0,false|,0,CHAR,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getDclDvRtSelPrd","setDclDvRtSelPrd","DCL_DV_RT_SEL_PRD,3,11,3,0,false|,0,INTEGER,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getDclDvRtTbl","setDclDvRtTbl","DCL_DV_RT_TBL,1,12,16,0,false|,0,CHAR,0,TBC,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getPdPcIncTbStIn","setPdPcIncTbStIn","PD_PC_INC_TB_ST_IN,1,13,1,0,false|,0,CHAR,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getPdPcIncSelPrd","setPdPcIncSelPrd","PD_PC_INC_SEL_PRD,3,14,3,0,false|,0,INTEGER,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getPdPcIncTbl","setPdPcIncTbl","PD_PC_INC_TBL,1,15,16,0,false|,0,CHAR,0,TBD,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getPuaFlChTbStIn","setPuaFlChTbStIn","PUA_FL_CH_TB_ST_IN,1,16,1,0,false|,0,CHAR,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getPuaFlChSelPrd","setPuaFlChSelPrd","PUA_FL_CH_SEL_PRD,3,17,3,0,false|,0,INTEGER,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getPuaFlChTbl","setPuaFlChTbl","PUA_FL_CH_TBL,1,18,16,0,false|,0,CHAR,0,TBA,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getBasFlChTbStIn","setBasFlChTbStIn","BAS_FL_CH_TB_ST_IN,1,19,1,0,false|,0,CHAR,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getBasFlChSelPrd","setBasFlChSelPrd","BAS_FL_CH_SEL_PRD,3,20,3,0,false|,0,INTEGER,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getBasFlChTbl","setBasFlChTbl","BAS_FL_CH_TBL,1,21,16,0,false|,0,CHAR,0,TBB,null,null,null,null"));
    }
    
    public String getPagingSQL(String schemaName,boolean isSubsetMode,boolean isNext, boolean locator)
    {
        String pagingWhere = null;
        String order = null;
        if (isNext) {
            if (isSubsetMode)
                if (locator)
                    pagingWhere = ".TTB4T WHERE (COMPANY_CODE = :company_code) AND (TABLE_SUBSET = :table_subset) AND ((EFFECTIVE_DATE > :effective_date) OR (EFFECTIVE_DATE = :effective_date AND POLICY_STATUS > :policy_status) OR (EFFECTIVE_DATE = :effective_date AND POLICY_STATUS = :policy_status AND MAX_FACE_AMOUNT > :max_face_amount) OR (EFFECTIVE_DATE = :effective_date AND POLICY_STATUS = :policy_status AND MAX_FACE_AMOUNT = :max_face_amount AND SEX > :sex) OR (EFFECTIVE_DATE = :effective_date AND POLICY_STATUS = :policy_status AND MAX_FACE_AMOUNT = :max_face_amount AND SEX = :sex AND TRX_CODE > :trx_code) OR (EFFECTIVE_DATE = :effective_date AND POLICY_STATUS = :policy_status AND MAX_FACE_AMOUNT = :max_face_amount AND SEX = :sex AND TRX_CODE = :trx_code AND PREMIUM_CLASS_CODE > :premium_class_code) OR (EFFECTIVE_DATE = :effective_date AND POLICY_STATUS = :policy_status AND MAX_FACE_AMOUNT = :max_face_amount AND SEX = :sex AND TRX_CODE = :trx_code AND PREMIUM_CLASS_CODE = :premium_class_code AND RTD_SPCL_PRM_CLASS > :rtd_spcl_prm_class) OR (EFFECTIVE_DATE = :effective_date AND POLICY_STATUS = :policy_status AND MAX_FACE_AMOUNT = :max_face_amount AND SEX = :sex AND TRX_CODE = :trx_code AND PREMIUM_CLASS_CODE = :premium_class_code AND RTD_SPCL_PRM_CLASS = :rtd_spcl_prm_class)) ";
                else 
                    pagingWhere = ".TTB4T WHERE (COMPANY_CODE = :company_code) AND (TABLE_SUBSET = :table_subset) AND ((EFFECTIVE_DATE > :effective_date) OR (EFFECTIVE_DATE = :effective_date AND POLICY_STATUS > :policy_status) OR (EFFECTIVE_DATE = :effective_date AND POLICY_STATUS = :policy_status AND MAX_FACE_AMOUNT > :max_face_amount) OR (EFFECTIVE_DATE = :effective_date AND POLICY_STATUS = :policy_status AND MAX_FACE_AMOUNT = :max_face_amount AND SEX > :sex) OR (EFFECTIVE_DATE = :effective_date AND POLICY_STATUS = :policy_status AND MAX_FACE_AMOUNT = :max_face_amount AND SEX = :sex AND TRX_CODE > :trx_code) OR (EFFECTIVE_DATE = :effective_date AND POLICY_STATUS = :policy_status AND MAX_FACE_AMOUNT = :max_face_amount AND SEX = :sex AND TRX_CODE = :trx_code AND PREMIUM_CLASS_CODE > :premium_class_code) OR (EFFECTIVE_DATE = :effective_date AND POLICY_STATUS = :policy_status AND MAX_FACE_AMOUNT = :max_face_amount AND SEX = :sex AND TRX_CODE = :trx_code AND PREMIUM_CLASS_CODE = :premium_class_code AND RTD_SPCL_PRM_CLASS > :rtd_spcl_prm_class)) ";
            else
                if (locator)
                    pagingWhere = ".TTB4T WHERE (COMPANY_CODE = :company_code) AND ((TABLE_SUBSET > :table_subset) OR (TABLE_SUBSET = :table_subset AND EFFECTIVE_DATE > :effective_date) OR (TABLE_SUBSET = :table_subset AND EFFECTIVE_DATE = :effective_date AND POLICY_STATUS > :policy_status) OR (TABLE_SUBSET = :table_subset AND EFFECTIVE_DATE = :effective_date AND POLICY_STATUS = :policy_status AND MAX_FACE_AMOUNT > :max_face_amount) OR (TABLE_SUBSET = :table_subset AND EFFECTIVE_DATE = :effective_date AND POLICY_STATUS = :policy_status AND MAX_FACE_AMOUNT = :max_face_amount AND SEX > :sex) OR (TABLE_SUBSET = :table_subset AND EFFECTIVE_DATE = :effective_date AND POLICY_STATUS = :policy_status AND MAX_FACE_AMOUNT = :max_face_amount AND SEX = :sex AND TRX_CODE > :trx_code) OR (TABLE_SUBSET = :table_subset AND EFFECTIVE_DATE = :effective_date AND POLICY_STATUS = :policy_status AND MAX_FACE_AMOUNT = :max_face_amount AND SEX = :sex AND TRX_CODE = :trx_code AND PREMIUM_CLASS_CODE > :premium_class_code) OR (TABLE_SUBSET = :table_subset AND EFFECTIVE_DATE = :effective_date AND POLICY_STATUS = :policy_status AND MAX_FACE_AMOUNT = :max_face_amount AND SEX = :sex AND TRX_CODE = :trx_code AND PREMIUM_CLASS_CODE = :premium_class_code AND RTD_SPCL_PRM_CLASS > :rtd_spcl_prm_class) OR (TABLE_SUBSET = :table_subset AND EFFECTIVE_DATE = :effective_date AND POLICY_STATUS = :policy_status AND MAX_FACE_AMOUNT = :max_face_amount AND SEX = :sex AND TRX_CODE = :trx_code AND PREMIUM_CLASS_CODE = :premium_class_code AND RTD_SPCL_PRM_CLASS = :rtd_spcl_prm_class)) ";
                else
                    pagingWhere = ".TTB4T WHERE (COMPANY_CODE = :company_code) AND ((TABLE_SUBSET > :table_subset) OR (TABLE_SUBSET = :table_subset AND EFFECTIVE_DATE > :effective_date) OR (TABLE_SUBSET = :table_subset AND EFFECTIVE_DATE = :effective_date AND POLICY_STATUS > :policy_status) OR (TABLE_SUBSET = :table_subset AND EFFECTIVE_DATE = :effective_date AND POLICY_STATUS = :policy_status AND MAX_FACE_AMOUNT > :max_face_amount) OR (TABLE_SUBSET = :table_subset AND EFFECTIVE_DATE = :effective_date AND POLICY_STATUS = :policy_status AND MAX_FACE_AMOUNT = :max_face_amount AND SEX > :sex) OR (TABLE_SUBSET = :table_subset AND EFFECTIVE_DATE = :effective_date AND POLICY_STATUS = :policy_status AND MAX_FACE_AMOUNT = :max_face_amount AND SEX = :sex AND TRX_CODE > :trx_code) OR (TABLE_SUBSET = :table_subset AND EFFECTIVE_DATE = :effective_date AND POLICY_STATUS = :policy_status AND MAX_FACE_AMOUNT = :max_face_amount AND SEX = :sex AND TRX_CODE = :trx_code AND PREMIUM_CLASS_CODE > :premium_class_code) OR (TABLE_SUBSET = :table_subset AND EFFECTIVE_DATE = :effective_date AND POLICY_STATUS = :policy_status AND MAX_FACE_AMOUNT = :max_face_amount AND SEX = :sex AND TRX_CODE = :trx_code AND PREMIUM_CLASS_CODE = :premium_class_code AND RTD_SPCL_PRM_CLASS > :rtd_spcl_prm_class)) ";
            order = " ORDER BY COMPANY_CODE, TABLE_SUBSET, EFFECTIVE_DATE, POLICY_STATUS, MAX_FACE_AMOUNT, SEX, TRX_CODE, PREMIUM_CLASS_CODE, RTD_SPCL_PRM_CLASS";
        }
        else {
            if (isSubsetMode)
                pagingWhere = ".TTB4T WHERE (COMPANY_CODE = :company_code) AND (TABLE_SUBSET = :table_subset) AND ((EFFECTIVE_DATE < :effective_date) OR (EFFECTIVE_DATE = :effective_date AND POLICY_STATUS < :policy_status) OR (EFFECTIVE_DATE = :effective_date AND POLICY_STATUS = :policy_status AND MAX_FACE_AMOUNT < :max_face_amount) OR (EFFECTIVE_DATE = :effective_date AND POLICY_STATUS = :policy_status AND MAX_FACE_AMOUNT = :max_face_amount AND SEX < :sex) OR (EFFECTIVE_DATE = :effective_date AND POLICY_STATUS = :policy_status AND MAX_FACE_AMOUNT = :max_face_amount AND SEX = :sex AND TRX_CODE < :trx_code) OR (EFFECTIVE_DATE = :effective_date AND POLICY_STATUS = :policy_status AND MAX_FACE_AMOUNT = :max_face_amount AND SEX = :sex AND TRX_CODE = :trx_code AND PREMIUM_CLASS_CODE < :premium_class_code) OR (EFFECTIVE_DATE = :effective_date AND POLICY_STATUS = :policy_status AND MAX_FACE_AMOUNT = :max_face_amount AND SEX = :sex AND TRX_CODE = :trx_code AND PREMIUM_CLASS_CODE = :premium_class_code AND RTD_SPCL_PRM_CLASS < :rtd_spcl_prm_class)) ";
            else
                pagingWhere = ".TTB4T WHERE (COMPANY_CODE = :company_code) AND ((TABLE_SUBSET < :table_subset) OR (TABLE_SUBSET = :table_subset AND EFFECTIVE_DATE < :effective_date) OR (TABLE_SUBSET = :table_subset AND EFFECTIVE_DATE = :effective_date AND POLICY_STATUS < :policy_status) OR (TABLE_SUBSET = :table_subset AND EFFECTIVE_DATE = :effective_date AND POLICY_STATUS = :policy_status AND MAX_FACE_AMOUNT < :max_face_amount) OR (TABLE_SUBSET = :table_subset AND EFFECTIVE_DATE = :effective_date AND POLICY_STATUS = :policy_status AND MAX_FACE_AMOUNT = :max_face_amount AND SEX < :sex) OR (TABLE_SUBSET = :table_subset AND EFFECTIVE_DATE = :effective_date AND POLICY_STATUS = :policy_status AND MAX_FACE_AMOUNT = :max_face_amount AND SEX = :sex AND TRX_CODE < :trx_code) OR (TABLE_SUBSET = :table_subset AND EFFECTIVE_DATE = :effective_date AND POLICY_STATUS = :policy_status AND MAX_FACE_AMOUNT = :max_face_amount AND SEX = :sex AND TRX_CODE = :trx_code AND PREMIUM_CLASS_CODE < :premium_class_code) OR (TABLE_SUBSET = :table_subset AND EFFECTIVE_DATE = :effective_date AND POLICY_STATUS = :policy_status AND MAX_FACE_AMOUNT = :max_face_amount AND SEX = :sex AND TRX_CODE = :trx_code AND PREMIUM_CLASS_CODE = :premium_class_code AND RTD_SPCL_PRM_CLASS < :rtd_spcl_prm_class)) ";
            order = " ORDER BY COMPANY_CODE DESC, TABLE_SUBSET DESC, EFFECTIVE_DATE DESC, POLICY_STATUS DESC, MAX_FACE_AMOUNT DESC, SEX DESC, TRX_CODE DESC, PREMIUM_CLASS_CODE DESC, RTD_SPCL_PRM_CLASS DESC";
        }
        return pagingSql + schemaName + pagingWhere + order;
    }
    
    public String prepareInsertStmt(String schemaName) {
        StringBuffer sb = new StringBuffer();
        sb.append("INSERT INTO ").append(schemaName);
        sb.append(".TTB4T ( ");
        sb.append("COMPANY_CODE, TABLE_SUBSET, EFFECTIVE_DATE, POLICY_STATUS, MAX_FACE_AMOUNT, SEX, TRX_CODE, PREMIUM_CLASS_CODE, RTD_SPCL_PRM_CLASS, DCL_DV_RT_TB_ST_IN, DCL_DV_RT_SEL_PRD, DCL_DV_RT_TBL, PD_PC_INC_TB_ST_IN, PD_PC_INC_SEL_PRD, PD_PC_INC_TBL, PUA_FL_CH_TB_ST_IN, PUA_FL_CH_SEL_PRD, PUA_FL_CH_TBL, BAS_FL_CH_TB_ST_IN, BAS_FL_CH_SEL_PRD, BAS_FL_CH_TBL )");
        sb.append(" VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ? )");
        return sb.toString();
    }
    
    public String prepareUpdateStmt(String schemaName)
    {
        StringBuffer sb = new StringBuffer();
        sb.append("UPDATE ").append(schemaName);
        sb.append(".TTB4T ");
        sb.append(" SET COMPANY_CODE = ?, TABLE_SUBSET = ?, EFFECTIVE_DATE = ?, POLICY_STATUS = ?, MAX_FACE_AMOUNT = ?, SEX = ?, TRX_CODE = ?, PREMIUM_CLASS_CODE = ?, RTD_SPCL_PRM_CLASS = ?, DCL_DV_RT_TB_ST_IN = ?, DCL_DV_RT_SEL_PRD = ?, DCL_DV_RT_TBL = ?, PD_PC_INC_TB_ST_IN = ?, PD_PC_INC_SEL_PRD = ?, PD_PC_INC_TBL = ?, PUA_FL_CH_TB_ST_IN = ?, PUA_FL_CH_SEL_PRD = ?, PUA_FL_CH_TBL = ?, BAS_FL_CH_TB_ST_IN = ?, BAS_FL_CH_SEL_PRD = ?, BAS_FL_CH_TBL = ?");
        sb.append(" WHERE COMPANY_CODE = ? AND TABLE_SUBSET = ? AND EFFECTIVE_DATE = ? AND POLICY_STATUS = ? AND MAX_FACE_AMOUNT = ? AND SEX = ? AND TRX_CODE = ? AND PREMIUM_CLASS_CODE = ? AND RTD_SPCL_PRM_CLASS = ?");
        return sb.toString();
    }
    
    public String prepareDeleteStmt(String schemaName)
    {
        StringBuffer sb = new StringBuffer();
        sb.append("DELETE FROM ").append(schemaName);
        sb.append(".TTB4T ");
        sb.append(" WHERE COMPANY_CODE = ? AND TABLE_SUBSET = ? AND EFFECTIVE_DATE = ? AND POLICY_STATUS = ? AND MAX_FACE_AMOUNT = ? AND SEX = ? AND TRX_CODE = ? AND PREMIUM_CLASS_CODE = ? AND RTD_SPCL_PRM_CLASS = ?");
        return sb.toString();
    }
}
