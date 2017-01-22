package com.csc.fsg.life.pw.web.io.descriptor.wma;

import com.csc.fsg.life.pw.web.io.*;

public class TTC1TDescriptor
    extends TableDescriptor
{
    private static final String pagingSql = "SELECT COMPANY_CODE, TABLE_SUBSET, EFFECTIVE_DATE, LOB_CODE, COST_BASIS_POL_VAL, PUA_IMPACT, ACCUM_DIV_IMPACT, ACCUM_DIVINT_IMP, ACCUM_DIVINT_WTH, PREMIUM_RED_IMP, CASH_DVD_IMPACT, ONEYR_TERMDIV_IMP, DIVAPPL_TO_LOANS, DIVAPPLTOLOANS_INT, DIVAPPLTO_PAYMENTS, DIVAPPLTO_ANNUITY, TERMINAL_DIVS, PUA_WITHDRAWALS, DVD_LOANS, ACCUM_DVD_WTH, PAY_MEC_REV_DAYS, NPAY_MEC_REV_DAYS, REIN_MAT_CHG_DAYS, FLEX_RID_DVD_IMP, REINSTAT_MATL_CHG, ETI_EXPIR_MEC_REV, REDUCE_PRM_WTHRAWL, VPWTH_ACCUM_DIVDS, VPWTH_PAIDUP_ADDS, WTH_FLEX_PUA_DIVDS, WTH_FLEX_ACC_DIVDS, END_YR_MEC_VIOLATN FROM ";
    
    public void initialize()
    {
        setRowClass(TTC1TRow.class);
        setTableName("TTC1T");
        setTableId("TC1");
        super.initialize();
    }
    
    public void initializeColumnDescriptors()
    {
        super.initializeColumnDescriptors();
        addColumnDescriptor(new ColumnDescriptor(this,"getCompanyCode","setCompanyCode","COMPANY_CODE,1,1,3,0,true|,0,CHAR,1,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getTableSubset","setTableSubset","TABLE_SUBSET,1,2,16,0,true|,0,CHAR,1,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getEffectiveDate","setEffectiveDate","EFFECTIVE_DATE,91,3,10,0,true|,0,DATE,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getLobCode","setLobCode","LOB_CODE,1,4,3,0,true|,0,CHAR,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getCostBasisPolVal","setCostBasisPolVal","COST_BASIS_POL_VAL,1,5,1,0,true|,0,CHAR,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getPuaImpact","setPuaImpact","PUA_IMPACT,1,6,1,0,false|,0,CHAR,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getAccumDivImpact","setAccumDivImpact","ACCUM_DIV_IMPACT,1,7,1,0,false|,0,CHAR,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getAccumDivintImp","setAccumDivintImp","ACCUM_DIVINT_IMP,1,8,1,0,false|,0,CHAR,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getAccumDivintWth","setAccumDivintWth","ACCUM_DIVINT_WTH,1,9,1,0,false|,0,CHAR,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getPremiumRedImp","setPremiumRedImp","PREMIUM_RED_IMP,1,10,1,0,false|,0,CHAR,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getCashDvdImpact","setCashDvdImpact","CASH_DVD_IMPACT,1,11,1,0,false|,0,CHAR,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getOneyrTermdivImp","setOneyrTermdivImp","ONEYR_TERMDIV_IMP,1,12,1,0,false|,0,CHAR,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getDivapplToLoans","setDivapplToLoans","DIVAPPL_TO_LOANS,1,13,1,0,false|,0,CHAR,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getDivappltoloansInt","setDivappltoloansInt","DIVAPPLTOLOANS_INT,1,14,1,0,false|,0,CHAR,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getDivappltoPayments","setDivappltoPayments","DIVAPPLTO_PAYMENTS,1,15,1,0,false|,0,CHAR,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getDivappltoAnnuity","setDivappltoAnnuity","DIVAPPLTO_ANNUITY,1,16,1,0,false|,0,CHAR,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getTerminalDivs","setTerminalDivs","TERMINAL_DIVS,1,17,1,0,false|,0,CHAR,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getPuaWithdrawals","setPuaWithdrawals","PUA_WITHDRAWALS,1,18,1,0,false|,0,CHAR,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getDvdLoans","setDvdLoans","DVD_LOANS,1,19,1,0,false|,0,CHAR,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getAccumDvdWth","setAccumDvdWth","ACCUM_DVD_WTH,1,20,1,0,false|,0,CHAR,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getPayMecRevDays","setPayMecRevDays","PAY_MEC_REV_DAYS,3,21,3,0,false|,0,INTEGER,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getNpayMecRevDays","setNpayMecRevDays","NPAY_MEC_REV_DAYS,3,22,3,0,false|,0,INTEGER,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getReinMatChgDays","setReinMatChgDays","REIN_MAT_CHG_DAYS,3,23,3,0,false|,0,INTEGER,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getFlexRidDvdImp","setFlexRidDvdImp","FLEX_RID_DVD_IMP,1,24,1,0,false|,0,CHAR,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getReinstatMatlChg","setReinstatMatlChg","REINSTAT_MATL_CHG,1,25,1,0,false|,0,CHAR,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getEtiExpirMecRev","setEtiExpirMecRev","ETI_EXPIR_MEC_REV,3,26,3,0,false|,0,INTEGER,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getReducePrmWthrawl","setReducePrmWthrawl","REDUCE_PRM_WTHRAWL,1,27,1,0,false|,0,CHAR,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getVpwthAccumDivds","setVpwthAccumDivds","VPWTH_ACCUM_DIVDS,1,28,1,0,false|,0,CHAR,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getVpwthPaidupAdds","setVpwthPaidupAdds","VPWTH_PAIDUP_ADDS,1,29,1,0,false|,0,CHAR,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getWthFlexPuaDivds","setWthFlexPuaDivds","WTH_FLEX_PUA_DIVDS,1,30,1,0,false|,0,CHAR,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getWthFlexAccDivds","setWthFlexAccDivds","WTH_FLEX_ACC_DIVDS,1,31,1,0,false|,0,CHAR,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getEndYrMecViolatn","setEndYrMecViolatn","END_YR_MEC_VIOLATN,1,32,1,0,false|,0,CHAR,0,null,null,null,null,null"));
    }
    
    public String getPagingSQL(String schemaName,boolean isSubsetMode,boolean isNext, boolean locator)
    {
        String pagingWhere = null;
        String order = null;
        if (isNext) {
            if (isSubsetMode)
                if (locator)
                    pagingWhere = ".TTC1T WHERE (COMPANY_CODE = :company_code) AND (TABLE_SUBSET = :table_subset) AND ((EFFECTIVE_DATE > :effective_date) OR (EFFECTIVE_DATE = :effective_date AND LOB_CODE > :lob_code) OR (EFFECTIVE_DATE = :effective_date AND LOB_CODE = :lob_code AND COST_BASIS_POL_VAL > :cost_basis_pol_val) OR (EFFECTIVE_DATE = :effective_date AND LOB_CODE = :lob_code AND COST_BASIS_POL_VAL = :cost_basis_pol_val)) ";
                else 
                    pagingWhere = ".TTC1T WHERE (COMPANY_CODE = :company_code) AND (TABLE_SUBSET = :table_subset) AND ((EFFECTIVE_DATE > :effective_date) OR (EFFECTIVE_DATE = :effective_date AND LOB_CODE > :lob_code) OR (EFFECTIVE_DATE = :effective_date AND LOB_CODE = :lob_code AND COST_BASIS_POL_VAL > :cost_basis_pol_val)) ";
            else
                if (locator)
                    pagingWhere = ".TTC1T WHERE (COMPANY_CODE = :company_code) AND ((TABLE_SUBSET > :table_subset) OR (TABLE_SUBSET = :table_subset AND EFFECTIVE_DATE > :effective_date) OR (TABLE_SUBSET = :table_subset AND EFFECTIVE_DATE = :effective_date AND LOB_CODE > :lob_code) OR (TABLE_SUBSET = :table_subset AND EFFECTIVE_DATE = :effective_date AND LOB_CODE = :lob_code AND COST_BASIS_POL_VAL > :cost_basis_pol_val) OR (TABLE_SUBSET = :table_subset AND EFFECTIVE_DATE = :effective_date AND LOB_CODE = :lob_code AND COST_BASIS_POL_VAL = :cost_basis_pol_val)) ";
                else
                    pagingWhere = ".TTC1T WHERE (COMPANY_CODE = :company_code) AND ((TABLE_SUBSET > :table_subset) OR (TABLE_SUBSET = :table_subset AND EFFECTIVE_DATE > :effective_date) OR (TABLE_SUBSET = :table_subset AND EFFECTIVE_DATE = :effective_date AND LOB_CODE > :lob_code) OR (TABLE_SUBSET = :table_subset AND EFFECTIVE_DATE = :effective_date AND LOB_CODE = :lob_code AND COST_BASIS_POL_VAL > :cost_basis_pol_val)) ";
            order = " ORDER BY COMPANY_CODE, TABLE_SUBSET, EFFECTIVE_DATE, LOB_CODE, COST_BASIS_POL_VAL";
        }
        else {
            if (isSubsetMode)
                pagingWhere = ".TTC1T WHERE (COMPANY_CODE = :company_code) AND (TABLE_SUBSET = :table_subset) AND ((EFFECTIVE_DATE < :effective_date) OR (EFFECTIVE_DATE = :effective_date AND LOB_CODE < :lob_code) OR (EFFECTIVE_DATE = :effective_date AND LOB_CODE = :lob_code AND COST_BASIS_POL_VAL < :cost_basis_pol_val)) ";
            else
                pagingWhere = ".TTC1T WHERE (COMPANY_CODE = :company_code) AND ((TABLE_SUBSET < :table_subset) OR (TABLE_SUBSET = :table_subset AND EFFECTIVE_DATE < :effective_date) OR (TABLE_SUBSET = :table_subset AND EFFECTIVE_DATE = :effective_date AND LOB_CODE < :lob_code) OR (TABLE_SUBSET = :table_subset AND EFFECTIVE_DATE = :effective_date AND LOB_CODE = :lob_code AND COST_BASIS_POL_VAL < :cost_basis_pol_val)) ";
            order = " ORDER BY COMPANY_CODE DESC, TABLE_SUBSET DESC, EFFECTIVE_DATE DESC, LOB_CODE DESC, COST_BASIS_POL_VAL DESC";
        }
        return pagingSql + schemaName + pagingWhere + order;
    }
    
    public String prepareInsertStmt(String schemaName) {
        StringBuffer sb = new StringBuffer();
        sb.append("INSERT INTO ").append(schemaName);
        sb.append(".TTC1T ( ");
        sb.append("COMPANY_CODE, TABLE_SUBSET, EFFECTIVE_DATE, LOB_CODE, COST_BASIS_POL_VAL, PUA_IMPACT, ACCUM_DIV_IMPACT, ACCUM_DIVINT_IMP, ACCUM_DIVINT_WTH, PREMIUM_RED_IMP, CASH_DVD_IMPACT, ONEYR_TERMDIV_IMP, DIVAPPL_TO_LOANS, DIVAPPLTOLOANS_INT, DIVAPPLTO_PAYMENTS, DIVAPPLTO_ANNUITY, TERMINAL_DIVS, PUA_WITHDRAWALS, DVD_LOANS, ACCUM_DVD_WTH, PAY_MEC_REV_DAYS, NPAY_MEC_REV_DAYS, REIN_MAT_CHG_DAYS, FLEX_RID_DVD_IMP, REINSTAT_MATL_CHG, ETI_EXPIR_MEC_REV, REDUCE_PRM_WTHRAWL, VPWTH_ACCUM_DIVDS, VPWTH_PAIDUP_ADDS, WTH_FLEX_PUA_DIVDS, WTH_FLEX_ACC_DIVDS, END_YR_MEC_VIOLATN )");
        sb.append(" VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ? )");
        return sb.toString();
    }
    
    public String prepareUpdateStmt(String schemaName)
    {
        StringBuffer sb = new StringBuffer();
        sb.append("UPDATE ").append(schemaName);
        sb.append(".TTC1T ");
        sb.append(" SET COMPANY_CODE = ?, TABLE_SUBSET = ?, EFFECTIVE_DATE = ?, LOB_CODE = ?, COST_BASIS_POL_VAL = ?, PUA_IMPACT = ?, ACCUM_DIV_IMPACT = ?, ACCUM_DIVINT_IMP = ?, ACCUM_DIVINT_WTH = ?, PREMIUM_RED_IMP = ?, CASH_DVD_IMPACT = ?, ONEYR_TERMDIV_IMP = ?, DIVAPPL_TO_LOANS = ?, DIVAPPLTOLOANS_INT = ?, DIVAPPLTO_PAYMENTS = ?, DIVAPPLTO_ANNUITY = ?, TERMINAL_DIVS = ?, PUA_WITHDRAWALS = ?, DVD_LOANS = ?, ACCUM_DVD_WTH = ?, PAY_MEC_REV_DAYS = ?, NPAY_MEC_REV_DAYS = ?, REIN_MAT_CHG_DAYS = ?, FLEX_RID_DVD_IMP = ?, REINSTAT_MATL_CHG = ?, ETI_EXPIR_MEC_REV = ?, REDUCE_PRM_WTHRAWL = ?, VPWTH_ACCUM_DIVDS = ?, VPWTH_PAIDUP_ADDS = ?, WTH_FLEX_PUA_DIVDS = ?, WTH_FLEX_ACC_DIVDS = ?, END_YR_MEC_VIOLATN = ?");
        sb.append(" WHERE COMPANY_CODE = ? AND TABLE_SUBSET = ? AND EFFECTIVE_DATE = ? AND LOB_CODE = ? AND COST_BASIS_POL_VAL = ?");
        return sb.toString();
    }
    
    public String prepareDeleteStmt(String schemaName)
    {
        StringBuffer sb = new StringBuffer();
        sb.append("DELETE FROM ").append(schemaName);
        sb.append(".TTC1T ");
        sb.append(" WHERE COMPANY_CODE = ? AND TABLE_SUBSET = ? AND EFFECTIVE_DATE = ? AND LOB_CODE = ? AND COST_BASIS_POL_VAL = ?");
        return sb.toString();
    }
}
