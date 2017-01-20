package com.csc.fsg.life.pw.io.descriptor.wma;

import com.csc.fsg.life.pw.io.*;

public class TAC1FDescriptor
    extends TableDescriptor
{
    private static final String pagingSql = "SELECT COMPANY_CODE, TABLE_SUBSET, STATUTORY_COMPANY, PROVINCE_CODE, AREA_CODE, LOB_CODE, MEMO_CODE, EFFECTIVE_DATE, PENSION_LMPSUM_IND, MAX_DISP_AMT, TAX_RATE FROM ";
    
    public void initialize()
    {
        setRowClass(TAC1FRow.class);
        setTableName("TAC1F");
        setTableId("AC1");
        super.initialize();
    }
    
    public void initializeColumnDescriptors()
    {
        super.initializeColumnDescriptors();
        addColumnDescriptor(new ColumnDescriptor(this,"getCompanyCode","setCompanyCode","COMPANY_CODE,1,1,3,0,true|,0,CHAR,1,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getTableSubset","setTableSubset","TABLE_SUBSET,1,2,16,0,true|,0,CHAR,1,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getStatutoryCompany","setStatutoryCompany","STATUTORY_COMPANY,1,3,3,0,true|,0,CHAR,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getProvinceCode","setProvinceCode","PROVINCE_CODE,1,4,3,0,true|,0,CHAR,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getAreaCode","setAreaCode","AREA_CODE,1,5,6,0,true|,0,CHAR,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getLobCode","setLobCode","LOB_CODE,1,6,3,0,true|,0,CHAR,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getMemoCode","setMemoCode","MEMO_CODE,1,7,2,0,true|,0,CHAR,4,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getEffectiveDate","setEffectiveDate","EFFECTIVE_DATE,91,8,10,0,true|,0,DATE,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getPensionLmpsumInd","setPensionLmpsumInd","PENSION_LMPSUM_IND,1,9,1,0,true|,0,CHAR,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getMaxDispAmt","setMaxDispAmt","MAX_DISP_AMT,3,10,11,2,true|,0,DOUBLE,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getTaxRate","setTaxRate","TAX_RATE,3,11,7,5,false|,0,DOUBLE,0,null,null,null,null,null"));
    }
    
    public String getPagingSQL(String schemaName,boolean isSubsetMode,boolean isNext, boolean locator)
    {
        String pagingWhere = null;
        String order = null;
        if (isNext) {
            if (isSubsetMode)
                if (locator)
                    pagingWhere = ".TAC1F WHERE (COMPANY_CODE = :company_code) AND (TABLE_SUBSET = :table_subset) AND ((STATUTORY_COMPANY > :statutory_company) OR (STATUTORY_COMPANY = :statutory_company AND PROVINCE_CODE > :province_code) OR (STATUTORY_COMPANY = :statutory_company AND PROVINCE_CODE = :province_code AND AREA_CODE > :area_code) OR (STATUTORY_COMPANY = :statutory_company AND PROVINCE_CODE = :province_code AND AREA_CODE = :area_code AND LOB_CODE > :lob_code) OR (STATUTORY_COMPANY = :statutory_company AND PROVINCE_CODE = :province_code AND AREA_CODE = :area_code AND LOB_CODE = :lob_code AND MEMO_CODE > :memo_code) OR (STATUTORY_COMPANY = :statutory_company AND PROVINCE_CODE = :province_code AND AREA_CODE = :area_code AND LOB_CODE = :lob_code AND MEMO_CODE = :memo_code AND EFFECTIVE_DATE > :effective_date) OR (STATUTORY_COMPANY = :statutory_company AND PROVINCE_CODE = :province_code AND AREA_CODE = :area_code AND LOB_CODE = :lob_code AND MEMO_CODE = :memo_code AND EFFECTIVE_DATE = :effective_date AND PENSION_LMPSUM_IND > :pension_lmpsum_ind) OR (STATUTORY_COMPANY = :statutory_company AND PROVINCE_CODE = :province_code AND AREA_CODE = :area_code AND LOB_CODE = :lob_code AND MEMO_CODE = :memo_code AND EFFECTIVE_DATE = :effective_date AND PENSION_LMPSUM_IND = :pension_lmpsum_ind AND MAX_DISP_AMT > :max_disp_amt) OR (STATUTORY_COMPANY = :statutory_company AND PROVINCE_CODE = :province_code AND AREA_CODE = :area_code AND LOB_CODE = :lob_code AND MEMO_CODE = :memo_code AND EFFECTIVE_DATE = :effective_date AND PENSION_LMPSUM_IND = :pension_lmpsum_ind AND MAX_DISP_AMT = :max_disp_amt)) ";
                else 
                    pagingWhere = ".TAC1F WHERE (COMPANY_CODE = :company_code) AND (TABLE_SUBSET = :table_subset) AND ((STATUTORY_COMPANY > :statutory_company) OR (STATUTORY_COMPANY = :statutory_company AND PROVINCE_CODE > :province_code) OR (STATUTORY_COMPANY = :statutory_company AND PROVINCE_CODE = :province_code AND AREA_CODE > :area_code) OR (STATUTORY_COMPANY = :statutory_company AND PROVINCE_CODE = :province_code AND AREA_CODE = :area_code AND LOB_CODE > :lob_code) OR (STATUTORY_COMPANY = :statutory_company AND PROVINCE_CODE = :province_code AND AREA_CODE = :area_code AND LOB_CODE = :lob_code AND MEMO_CODE > :memo_code) OR (STATUTORY_COMPANY = :statutory_company AND PROVINCE_CODE = :province_code AND AREA_CODE = :area_code AND LOB_CODE = :lob_code AND MEMO_CODE = :memo_code AND EFFECTIVE_DATE > :effective_date) OR (STATUTORY_COMPANY = :statutory_company AND PROVINCE_CODE = :province_code AND AREA_CODE = :area_code AND LOB_CODE = :lob_code AND MEMO_CODE = :memo_code AND EFFECTIVE_DATE = :effective_date AND PENSION_LMPSUM_IND > :pension_lmpsum_ind) OR (STATUTORY_COMPANY = :statutory_company AND PROVINCE_CODE = :province_code AND AREA_CODE = :area_code AND LOB_CODE = :lob_code AND MEMO_CODE = :memo_code AND EFFECTIVE_DATE = :effective_date AND PENSION_LMPSUM_IND = :pension_lmpsum_ind AND MAX_DISP_AMT > :max_disp_amt)) ";
            else
                if (locator)
                    pagingWhere = ".TAC1F WHERE (COMPANY_CODE = :company_code) AND ((TABLE_SUBSET > :table_subset) OR (TABLE_SUBSET = :table_subset AND STATUTORY_COMPANY > :statutory_company) OR (TABLE_SUBSET = :table_subset AND STATUTORY_COMPANY = :statutory_company AND PROVINCE_CODE > :province_code) OR (TABLE_SUBSET = :table_subset AND STATUTORY_COMPANY = :statutory_company AND PROVINCE_CODE = :province_code AND AREA_CODE > :area_code) OR (TABLE_SUBSET = :table_subset AND STATUTORY_COMPANY = :statutory_company AND PROVINCE_CODE = :province_code AND AREA_CODE = :area_code AND LOB_CODE > :lob_code) OR (TABLE_SUBSET = :table_subset AND STATUTORY_COMPANY = :statutory_company AND PROVINCE_CODE = :province_code AND AREA_CODE = :area_code AND LOB_CODE = :lob_code AND MEMO_CODE > :memo_code) OR (TABLE_SUBSET = :table_subset AND STATUTORY_COMPANY = :statutory_company AND PROVINCE_CODE = :province_code AND AREA_CODE = :area_code AND LOB_CODE = :lob_code AND MEMO_CODE = :memo_code AND EFFECTIVE_DATE > :effective_date) OR (TABLE_SUBSET = :table_subset AND STATUTORY_COMPANY = :statutory_company AND PROVINCE_CODE = :province_code AND AREA_CODE = :area_code AND LOB_CODE = :lob_code AND MEMO_CODE = :memo_code AND EFFECTIVE_DATE = :effective_date AND PENSION_LMPSUM_IND > :pension_lmpsum_ind) OR (TABLE_SUBSET = :table_subset AND STATUTORY_COMPANY = :statutory_company AND PROVINCE_CODE = :province_code AND AREA_CODE = :area_code AND LOB_CODE = :lob_code AND MEMO_CODE = :memo_code AND EFFECTIVE_DATE = :effective_date AND PENSION_LMPSUM_IND = :pension_lmpsum_ind AND MAX_DISP_AMT > :max_disp_amt) OR (TABLE_SUBSET = :table_subset AND STATUTORY_COMPANY = :statutory_company AND PROVINCE_CODE = :province_code AND AREA_CODE = :area_code AND LOB_CODE = :lob_code AND MEMO_CODE = :memo_code AND EFFECTIVE_DATE = :effective_date AND PENSION_LMPSUM_IND = :pension_lmpsum_ind AND MAX_DISP_AMT = :max_disp_amt)) ";
                else
                    pagingWhere = ".TAC1F WHERE (COMPANY_CODE = :company_code) AND ((TABLE_SUBSET > :table_subset) OR (TABLE_SUBSET = :table_subset AND STATUTORY_COMPANY > :statutory_company) OR (TABLE_SUBSET = :table_subset AND STATUTORY_COMPANY = :statutory_company AND PROVINCE_CODE > :province_code) OR (TABLE_SUBSET = :table_subset AND STATUTORY_COMPANY = :statutory_company AND PROVINCE_CODE = :province_code AND AREA_CODE > :area_code) OR (TABLE_SUBSET = :table_subset AND STATUTORY_COMPANY = :statutory_company AND PROVINCE_CODE = :province_code AND AREA_CODE = :area_code AND LOB_CODE > :lob_code) OR (TABLE_SUBSET = :table_subset AND STATUTORY_COMPANY = :statutory_company AND PROVINCE_CODE = :province_code AND AREA_CODE = :area_code AND LOB_CODE = :lob_code AND MEMO_CODE > :memo_code) OR (TABLE_SUBSET = :table_subset AND STATUTORY_COMPANY = :statutory_company AND PROVINCE_CODE = :province_code AND AREA_CODE = :area_code AND LOB_CODE = :lob_code AND MEMO_CODE = :memo_code AND EFFECTIVE_DATE > :effective_date) OR (TABLE_SUBSET = :table_subset AND STATUTORY_COMPANY = :statutory_company AND PROVINCE_CODE = :province_code AND AREA_CODE = :area_code AND LOB_CODE = :lob_code AND MEMO_CODE = :memo_code AND EFFECTIVE_DATE = :effective_date AND PENSION_LMPSUM_IND > :pension_lmpsum_ind) OR (TABLE_SUBSET = :table_subset AND STATUTORY_COMPANY = :statutory_company AND PROVINCE_CODE = :province_code AND AREA_CODE = :area_code AND LOB_CODE = :lob_code AND MEMO_CODE = :memo_code AND EFFECTIVE_DATE = :effective_date AND PENSION_LMPSUM_IND = :pension_lmpsum_ind AND MAX_DISP_AMT > :max_disp_amt)) ";
            order = " ORDER BY COMPANY_CODE, TABLE_SUBSET, STATUTORY_COMPANY, PROVINCE_CODE, AREA_CODE, LOB_CODE, MEMO_CODE, EFFECTIVE_DATE, PENSION_LMPSUM_IND, MAX_DISP_AMT";
        }
        else {
            if (isSubsetMode)
                pagingWhere = ".TAC1F WHERE (COMPANY_CODE = :company_code) AND (TABLE_SUBSET = :table_subset) AND ((STATUTORY_COMPANY < :statutory_company) OR (STATUTORY_COMPANY = :statutory_company AND PROVINCE_CODE < :province_code) OR (STATUTORY_COMPANY = :statutory_company AND PROVINCE_CODE = :province_code AND AREA_CODE < :area_code) OR (STATUTORY_COMPANY = :statutory_company AND PROVINCE_CODE = :province_code AND AREA_CODE = :area_code AND LOB_CODE < :lob_code) OR (STATUTORY_COMPANY = :statutory_company AND PROVINCE_CODE = :province_code AND AREA_CODE = :area_code AND LOB_CODE = :lob_code AND MEMO_CODE < :memo_code) OR (STATUTORY_COMPANY = :statutory_company AND PROVINCE_CODE = :province_code AND AREA_CODE = :area_code AND LOB_CODE = :lob_code AND MEMO_CODE = :memo_code AND EFFECTIVE_DATE < :effective_date) OR (STATUTORY_COMPANY = :statutory_company AND PROVINCE_CODE = :province_code AND AREA_CODE = :area_code AND LOB_CODE = :lob_code AND MEMO_CODE = :memo_code AND EFFECTIVE_DATE = :effective_date AND PENSION_LMPSUM_IND < :pension_lmpsum_ind) OR (STATUTORY_COMPANY = :statutory_company AND PROVINCE_CODE = :province_code AND AREA_CODE = :area_code AND LOB_CODE = :lob_code AND MEMO_CODE = :memo_code AND EFFECTIVE_DATE = :effective_date AND PENSION_LMPSUM_IND = :pension_lmpsum_ind AND MAX_DISP_AMT < :max_disp_amt)) ";
            else
                pagingWhere = ".TAC1F WHERE (COMPANY_CODE = :company_code) AND ((TABLE_SUBSET < :table_subset) OR (TABLE_SUBSET = :table_subset AND STATUTORY_COMPANY < :statutory_company) OR (TABLE_SUBSET = :table_subset AND STATUTORY_COMPANY = :statutory_company AND PROVINCE_CODE < :province_code) OR (TABLE_SUBSET = :table_subset AND STATUTORY_COMPANY = :statutory_company AND PROVINCE_CODE = :province_code AND AREA_CODE < :area_code) OR (TABLE_SUBSET = :table_subset AND STATUTORY_COMPANY = :statutory_company AND PROVINCE_CODE = :province_code AND AREA_CODE = :area_code AND LOB_CODE < :lob_code) OR (TABLE_SUBSET = :table_subset AND STATUTORY_COMPANY = :statutory_company AND PROVINCE_CODE = :province_code AND AREA_CODE = :area_code AND LOB_CODE = :lob_code AND MEMO_CODE < :memo_code) OR (TABLE_SUBSET = :table_subset AND STATUTORY_COMPANY = :statutory_company AND PROVINCE_CODE = :province_code AND AREA_CODE = :area_code AND LOB_CODE = :lob_code AND MEMO_CODE = :memo_code AND EFFECTIVE_DATE < :effective_date) OR (TABLE_SUBSET = :table_subset AND STATUTORY_COMPANY = :statutory_company AND PROVINCE_CODE = :province_code AND AREA_CODE = :area_code AND LOB_CODE = :lob_code AND MEMO_CODE = :memo_code AND EFFECTIVE_DATE = :effective_date AND PENSION_LMPSUM_IND < :pension_lmpsum_ind) OR (TABLE_SUBSET = :table_subset AND STATUTORY_COMPANY = :statutory_company AND PROVINCE_CODE = :province_code AND AREA_CODE = :area_code AND LOB_CODE = :lob_code AND MEMO_CODE = :memo_code AND EFFECTIVE_DATE = :effective_date AND PENSION_LMPSUM_IND = :pension_lmpsum_ind AND MAX_DISP_AMT < :max_disp_amt)) ";
            order = " ORDER BY COMPANY_CODE DESC, TABLE_SUBSET DESC, STATUTORY_COMPANY DESC, PROVINCE_CODE DESC, AREA_CODE DESC, LOB_CODE DESC, MEMO_CODE DESC, EFFECTIVE_DATE DESC, PENSION_LMPSUM_IND DESC, MAX_DISP_AMT DESC";
        }
        return pagingSql + schemaName + pagingWhere + order;
    }
    
    public String prepareInsertStmt(String schemaName) {
        StringBuffer sb = new StringBuffer();
        sb.append("INSERT INTO ").append(schemaName);
        sb.append(".TAC1F ( ");
        sb.append("COMPANY_CODE, TABLE_SUBSET, STATUTORY_COMPANY, PROVINCE_CODE, AREA_CODE, LOB_CODE, MEMO_CODE, EFFECTIVE_DATE, PENSION_LMPSUM_IND, MAX_DISP_AMT, TAX_RATE )");
        sb.append(" VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ? )");
        return sb.toString();
    }
    
    public String prepareUpdateStmt(String schemaName)
    {
        StringBuffer sb = new StringBuffer();
        sb.append("UPDATE ").append(schemaName);
        sb.append(".TAC1F ");
        sb.append(" SET COMPANY_CODE = ?, TABLE_SUBSET = ?, STATUTORY_COMPANY = ?, PROVINCE_CODE = ?, AREA_CODE = ?, LOB_CODE = ?, MEMO_CODE = ?, EFFECTIVE_DATE = ?, PENSION_LMPSUM_IND = ?, MAX_DISP_AMT = ?, TAX_RATE = ?");
        sb.append(" WHERE COMPANY_CODE = ? AND TABLE_SUBSET = ? AND STATUTORY_COMPANY = ? AND PROVINCE_CODE = ? AND AREA_CODE = ? AND LOB_CODE = ? AND MEMO_CODE = ? AND EFFECTIVE_DATE = ? AND PENSION_LMPSUM_IND = ? AND MAX_DISP_AMT = ?");
        return sb.toString();
    }
    
    public String prepareDeleteStmt(String schemaName)
    {
        StringBuffer sb = new StringBuffer();
        sb.append("DELETE FROM ").append(schemaName);
        sb.append(".TAC1F ");
        sb.append(" WHERE COMPANY_CODE = ? AND TABLE_SUBSET = ? AND STATUTORY_COMPANY = ? AND PROVINCE_CODE = ? AND AREA_CODE = ? AND LOB_CODE = ? AND MEMO_CODE = ? AND EFFECTIVE_DATE = ? AND PENSION_LMPSUM_IND = ? AND MAX_DISP_AMT = ?");
        return sb.toString();
    }
}
