package com.csc.fsg.life.pw.web.io.descriptor.wma;

import com.csc.fsg.life.pw.web.io.*;

public class T065EDescriptor
    extends TableDescriptor
{
    private static final String pagingSql = "SELECT COMPANY_CODE, TABLE_SUBSET, EFFECTIVE_DATE, ENDOWMENT_PERCENT, ENDOWMENT_AMOUNT, PRINT_METHOD, EXCS_INT_EXCL_TBL, COI_IND_01, FIX_FND_INT_01, VAR_FND_PRJ_01, COI_IND_02, FIX_FND_INT_02, VAR_FND_PRJ_02, COI_IND_03, FIX_FND_INT_03, VAR_FND_PRJ_03, COI_IND_04, FIX_FND_INT_04, VAR_FND_PRJ_04, COI_IND_05, FIX_FND_INT_05, VAR_FND_PRJ_05, COI_IND_06, FIX_FND_INT_06, VAR_FND_PRJ_06, COI_IND_07, FIX_FND_INT_07, VAR_FND_PRJ_07, COI_IND_08, FIX_FND_INT_08, VAR_FND_PRJ_08, COI_IND_09, FIX_FND_INT_09, VAR_FND_PRJ_09, PLAN_NAME, CVG_DSCR FROM ";
    
    public void initialize()
    {
        setRowClass(T065ERow.class);
        setTableName("T065E");
        setTableId("065");
        super.initialize();
    }
    
    public void initializeColumnDescriptors()
    {
        super.initializeColumnDescriptors();
        addColumnDescriptor(new ColumnDescriptor(this,"getCompanyCode","setCompanyCode","COMPANY_CODE,1,1,3,0,true|,0,CHAR,1,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getTableSubset","setTableSubset","TABLE_SUBSET,1,2,16,0,true|,0,CHAR,1,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getEffectiveDate","setEffectiveDate","EFFECTIVE_DATE,91,3,10,0,true|,0,DATE,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getEndowmentPercent","setEndowmentPercent","ENDOWMENT_PERCENT,3,4,5,2,false|,0,DOUBLE,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getEndowmentAmount","setEndowmentAmount","ENDOWMENT_AMOUNT,3,5,9,0,false|,0,INTEGER,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getPrintMethod","setPrintMethod","PRINT_METHOD,1,6,2,0,false|,0,CHAR,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getExcsIntExclTbl","setExcsIntExclTbl","EXCS_INT_EXCL_TBL,1,7,16,0,false|,0,CHAR,0,027,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getCoiInd01","setCoiInd01","COI_IND_01,1,8,1,0,false|,0,CHAR,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getFixFndInt01","setFixFndInt01","FIX_FND_INT_01,1,9,16,0,false|,0,CHAR,0,026,1,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getVarFndPrj01","setVarFndPrj01","VAR_FND_PRJ_01,1,10,16,0,false|,0,CHAR,0,026,A,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getCoiInd02","setCoiInd02","COI_IND_02,1,11,1,0,false|,0,CHAR,4,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getFixFndInt02","setFixFndInt02","FIX_FND_INT_02,1,12,16,0,false|,0,CHAR,0,026,2,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getVarFndPrj02","setVarFndPrj02","VAR_FND_PRJ_02,1,13,16,0,false|,0,CHAR,0,026,B,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getCoiInd03","setCoiInd03","COI_IND_03,1,14,1,0,false|,0,CHAR,4,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getFixFndInt03","setFixFndInt03","FIX_FND_INT_03,1,15,16,0,false|,0,CHAR,0,026,3,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getVarFndPrj03","setVarFndPrj03","VAR_FND_PRJ_03,1,16,16,0,false|,0,CHAR,0,026,C,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getCoiInd04","setCoiInd04","COI_IND_04,1,17,1,0,false|,0,CHAR,2,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getFixFndInt04","setFixFndInt04","FIX_FND_INT_04,1,18,16,0,false|,0,CHAR,2,026,4,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getVarFndPrj04","setVarFndPrj04","VAR_FND_PRJ_04,1,19,16,0,false|,0,CHAR,2,026,D,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getCoiInd05","setCoiInd05","COI_IND_05,1,20,1,0,false|,0,CHAR,2,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getFixFndInt05","setFixFndInt05","FIX_FND_INT_05,1,21,16,0,false|,0,CHAR,2,026,5,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getVarFndPrj05","setVarFndPrj05","VAR_FND_PRJ_05,1,22,16,0,false|,0,CHAR,2,026,E,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getCoiInd06","setCoiInd06","COI_IND_06,1,23,1,0,false|,0,CHAR,2,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getFixFndInt06","setFixFndInt06","FIX_FND_INT_06,1,24,16,0,false|,0,CHAR,2,026,6,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getVarFndPrj06","setVarFndPrj06","VAR_FND_PRJ_06,1,25,16,0,false|,0,CHAR,2,026,F,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getCoiInd07","setCoiInd07","COI_IND_07,1,26,1,0,false|,0,CHAR,2,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getFixFndInt07","setFixFndInt07","FIX_FND_INT_07,1,27,16,0,false|,0,CHAR,2,026,7,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getVarFndPrj07","setVarFndPrj07","VAR_FND_PRJ_07,1,28,16,0,false|,0,CHAR,2,026,G,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getCoiInd08","setCoiInd08","COI_IND_08,1,29,1,0,false|,0,CHAR,2,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getFixFndInt08","setFixFndInt08","FIX_FND_INT_08,1,30,16,0,false|,0,CHAR,2,026,8,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getVarFndPrj08","setVarFndPrj08","VAR_FND_PRJ_08,1,31,16,0,false|,0,CHAR,2,026,H,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getCoiInd09","setCoiInd09","COI_IND_09,1,32,1,0,false|,0,CHAR,2,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getFixFndInt09","setFixFndInt09","FIX_FND_INT_09,1,33,16,0,false|,0,CHAR,2,026,9,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getVarFndPrj09","setVarFndPrj09","VAR_FND_PRJ_09,1,34,16,0,false|,0,CHAR,2,026,I,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getPlanName","setPlanName","PLAN_NAME,1,35,40,0,false|,0,CHAR,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getCvgDscr","setCvgDscr","CVG_DSCR,1,36,40,0,false|,0,CHAR,4,null,null,null,null,null"));
    }
    
    public String getPagingSQL(String schemaName,boolean isSubsetMode,boolean isNext, boolean locator)
    {
        String pagingWhere = null;
        String order = null;
        if (isNext) {
            if (isSubsetMode)
                if (locator)
                    pagingWhere = ".T065E WHERE (COMPANY_CODE = :company_code) AND (TABLE_SUBSET = :table_subset) AND ((EFFECTIVE_DATE > :effective_date) OR (EFFECTIVE_DATE = :effective_date)) ";
                else 
                    pagingWhere = ".T065E WHERE (COMPANY_CODE = :company_code) AND (TABLE_SUBSET = :table_subset) AND ((EFFECTIVE_DATE > :effective_date)) ";
            else
                if (locator)
                    pagingWhere = ".T065E WHERE (COMPANY_CODE = :company_code) AND ((TABLE_SUBSET > :table_subset) OR (TABLE_SUBSET = :table_subset AND EFFECTIVE_DATE > :effective_date) OR (TABLE_SUBSET = :table_subset AND EFFECTIVE_DATE = :effective_date)) ";
                else
                    pagingWhere = ".T065E WHERE (COMPANY_CODE = :company_code) AND ((TABLE_SUBSET > :table_subset) OR (TABLE_SUBSET = :table_subset AND EFFECTIVE_DATE > :effective_date)) ";
            order = " ORDER BY COMPANY_CODE, TABLE_SUBSET, EFFECTIVE_DATE";
        }
        else {
            if (isSubsetMode)
                pagingWhere = ".T065E WHERE (COMPANY_CODE = :company_code) AND (TABLE_SUBSET = :table_subset) AND ((EFFECTIVE_DATE < :effective_date)) ";
            else
                pagingWhere = ".T065E WHERE (COMPANY_CODE = :company_code) AND ((TABLE_SUBSET < :table_subset) OR (TABLE_SUBSET = :table_subset AND EFFECTIVE_DATE < :effective_date)) ";
            order = " ORDER BY COMPANY_CODE DESC, TABLE_SUBSET DESC, EFFECTIVE_DATE DESC";
        }
        return pagingSql + schemaName + pagingWhere + order;
    }
    
    public String prepareInsertStmt(String schemaName) {
        StringBuffer sb = new StringBuffer();
        sb.append("INSERT INTO ").append(schemaName);
        sb.append(".T065E ( ");
        sb.append("COMPANY_CODE, TABLE_SUBSET, EFFECTIVE_DATE, ENDOWMENT_PERCENT, ENDOWMENT_AMOUNT, PRINT_METHOD, EXCS_INT_EXCL_TBL, COI_IND_01, FIX_FND_INT_01, VAR_FND_PRJ_01, COI_IND_02, FIX_FND_INT_02, VAR_FND_PRJ_02, COI_IND_03, FIX_FND_INT_03, VAR_FND_PRJ_03, COI_IND_04, FIX_FND_INT_04, VAR_FND_PRJ_04, COI_IND_05, FIX_FND_INT_05, VAR_FND_PRJ_05, COI_IND_06, FIX_FND_INT_06, VAR_FND_PRJ_06, COI_IND_07, FIX_FND_INT_07, VAR_FND_PRJ_07, COI_IND_08, FIX_FND_INT_08, VAR_FND_PRJ_08, COI_IND_09, FIX_FND_INT_09, VAR_FND_PRJ_09, PLAN_NAME, CVG_DSCR )");
        sb.append(" VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ? )");
        return sb.toString();
    }
    
    public String prepareUpdateStmt(String schemaName)
    {
        StringBuffer sb = new StringBuffer();
        sb.append("UPDATE ").append(schemaName);
        sb.append(".T065E ");
        sb.append(" SET COMPANY_CODE = ?, TABLE_SUBSET = ?, EFFECTIVE_DATE = ?, ENDOWMENT_PERCENT = ?, ENDOWMENT_AMOUNT = ?, PRINT_METHOD = ?, EXCS_INT_EXCL_TBL = ?, COI_IND_01 = ?, FIX_FND_INT_01 = ?, VAR_FND_PRJ_01 = ?, COI_IND_02 = ?, FIX_FND_INT_02 = ?, VAR_FND_PRJ_02 = ?, COI_IND_03 = ?, FIX_FND_INT_03 = ?, VAR_FND_PRJ_03 = ?, COI_IND_04 = ?, FIX_FND_INT_04 = ?, VAR_FND_PRJ_04 = ?, COI_IND_05 = ?, FIX_FND_INT_05 = ?, VAR_FND_PRJ_05 = ?, COI_IND_06 = ?, FIX_FND_INT_06 = ?, VAR_FND_PRJ_06 = ?, COI_IND_07 = ?, FIX_FND_INT_07 = ?, VAR_FND_PRJ_07 = ?, COI_IND_08 = ?, FIX_FND_INT_08 = ?, VAR_FND_PRJ_08 = ?, COI_IND_09 = ?, FIX_FND_INT_09 = ?, VAR_FND_PRJ_09 = ?, PLAN_NAME = ?, CVG_DSCR = ?");
        sb.append(" WHERE COMPANY_CODE = ? AND TABLE_SUBSET = ? AND EFFECTIVE_DATE = ?");
        return sb.toString();
    }
    
    public String prepareDeleteStmt(String schemaName)
    {
        StringBuffer sb = new StringBuffer();
        sb.append("DELETE FROM ").append(schemaName);
        sb.append(".T065E ");
        sb.append(" WHERE COMPANY_CODE = ? AND TABLE_SUBSET = ? AND EFFECTIVE_DATE = ?");
        return sb.toString();
    }
}
