package com.csc.fsg.life.pw.web.io.descriptor.wma;

import com.csc.fsg.life.pw.web.io.*;

public class TAC2FDescriptor
    extends TableDescriptor
{
    private static final String pagingSql = "SELECT COMPANY_CODE, TABLE_SUBSET, STATUTORY_COMPANY, LOB_CODE, PROVINCE_CODE, REGIST_PROD_TYPE, OLD_NEW_MONIES_IND, ATTAINED_AGE, EFFECTIVE_DATE, MINIMUM_FACTOR, MAXIMUM_FACTOR FROM ";
    
    public void initialize()
    {
        setRowClass(TAC2FRow.class);
        setTableName("TAC2F");
        setTableId("AC2");
        super.initialize();
    }
    
    public void initializeColumnDescriptors()
    {
        super.initializeColumnDescriptors();
        addColumnDescriptor(new ColumnDescriptor(this,"getCompanyCode","setCompanyCode","COMPANY_CODE,1,1,3,0,true|,0,CHAR,1,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getTableSubset","setTableSubset","TABLE_SUBSET,1,2,16,0,true|,0,CHAR,1,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getStatutoryCompany","setStatutoryCompany","STATUTORY_COMPANY,1,3,3,0,true|,0,CHAR,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getLobCode","setLobCode","LOB_CODE,1,4,3,0,true|,0,CHAR,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getProvinceCode","setProvinceCode","PROVINCE_CODE,1,5,3,0,true|,0,CHAR,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getRegistProdType","setRegistProdType","REGIST_PROD_TYPE,1,6,1,0,true|,0,CHAR,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getOldNewMoniesInd","setOldNewMoniesInd","OLD_NEW_MONIES_IND,1,7,1,0,true|,0,CHAR,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getAttainedAge","setAttainedAge","ATTAINED_AGE,1,8,3,0,true|,0,CHAR,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getEffectiveDate","setEffectiveDate","EFFECTIVE_DATE,91,9,10,0,true|,0,DATE,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getMinimumFactor","setMinimumFactor","MINIMUM_FACTOR,3,10,11,10,false|,0,DOUBLE,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getMaximumFactor","setMaximumFactor","MAXIMUM_FACTOR,3,11,11,10,false|,0,DOUBLE,0,null,null,null,null,null"));
    }
    
    public String getPagingSQL(String schemaName,boolean isSubsetMode,boolean isNext, boolean locator)
    {
        String pagingWhere = null;
        String order = null;
        if (isNext) {
            if (isSubsetMode)
                if (locator)
                    pagingWhere = ".TAC2F WHERE (COMPANY_CODE = :company_code) AND (TABLE_SUBSET = :table_subset) AND ((STATUTORY_COMPANY > :statutory_company) OR (STATUTORY_COMPANY = :statutory_company AND LOB_CODE > :lob_code) OR (STATUTORY_COMPANY = :statutory_company AND LOB_CODE = :lob_code AND PROVINCE_CODE > :province_code) OR (STATUTORY_COMPANY = :statutory_company AND LOB_CODE = :lob_code AND PROVINCE_CODE = :province_code AND REGIST_PROD_TYPE > :regist_prod_type) OR (STATUTORY_COMPANY = :statutory_company AND LOB_CODE = :lob_code AND PROVINCE_CODE = :province_code AND REGIST_PROD_TYPE = :regist_prod_type AND OLD_NEW_MONIES_IND > :old_new_monies_ind) OR (STATUTORY_COMPANY = :statutory_company AND LOB_CODE = :lob_code AND PROVINCE_CODE = :province_code AND REGIST_PROD_TYPE = :regist_prod_type AND OLD_NEW_MONIES_IND = :old_new_monies_ind AND ATTAINED_AGE > :attained_age) OR (STATUTORY_COMPANY = :statutory_company AND LOB_CODE = :lob_code AND PROVINCE_CODE = :province_code AND REGIST_PROD_TYPE = :regist_prod_type AND OLD_NEW_MONIES_IND = :old_new_monies_ind AND ATTAINED_AGE = :attained_age AND EFFECTIVE_DATE > :effective_date) OR (STATUTORY_COMPANY = :statutory_company AND LOB_CODE = :lob_code AND PROVINCE_CODE = :province_code AND REGIST_PROD_TYPE = :regist_prod_type AND OLD_NEW_MONIES_IND = :old_new_monies_ind AND ATTAINED_AGE = :attained_age AND EFFECTIVE_DATE = :effective_date)) ";
                else 
                    pagingWhere = ".TAC2F WHERE (COMPANY_CODE = :company_code) AND (TABLE_SUBSET = :table_subset) AND ((STATUTORY_COMPANY > :statutory_company) OR (STATUTORY_COMPANY = :statutory_company AND LOB_CODE > :lob_code) OR (STATUTORY_COMPANY = :statutory_company AND LOB_CODE = :lob_code AND PROVINCE_CODE > :province_code) OR (STATUTORY_COMPANY = :statutory_company AND LOB_CODE = :lob_code AND PROVINCE_CODE = :province_code AND REGIST_PROD_TYPE > :regist_prod_type) OR (STATUTORY_COMPANY = :statutory_company AND LOB_CODE = :lob_code AND PROVINCE_CODE = :province_code AND REGIST_PROD_TYPE = :regist_prod_type AND OLD_NEW_MONIES_IND > :old_new_monies_ind) OR (STATUTORY_COMPANY = :statutory_company AND LOB_CODE = :lob_code AND PROVINCE_CODE = :province_code AND REGIST_PROD_TYPE = :regist_prod_type AND OLD_NEW_MONIES_IND = :old_new_monies_ind AND ATTAINED_AGE > :attained_age) OR (STATUTORY_COMPANY = :statutory_company AND LOB_CODE = :lob_code AND PROVINCE_CODE = :province_code AND REGIST_PROD_TYPE = :regist_prod_type AND OLD_NEW_MONIES_IND = :old_new_monies_ind AND ATTAINED_AGE = :attained_age AND EFFECTIVE_DATE > :effective_date)) ";
            else
                if (locator)
                    pagingWhere = ".TAC2F WHERE (COMPANY_CODE = :company_code) AND ((TABLE_SUBSET > :table_subset) OR (TABLE_SUBSET = :table_subset AND STATUTORY_COMPANY > :statutory_company) OR (TABLE_SUBSET = :table_subset AND STATUTORY_COMPANY = :statutory_company AND LOB_CODE > :lob_code) OR (TABLE_SUBSET = :table_subset AND STATUTORY_COMPANY = :statutory_company AND LOB_CODE = :lob_code AND PROVINCE_CODE > :province_code) OR (TABLE_SUBSET = :table_subset AND STATUTORY_COMPANY = :statutory_company AND LOB_CODE = :lob_code AND PROVINCE_CODE = :province_code AND REGIST_PROD_TYPE > :regist_prod_type) OR (TABLE_SUBSET = :table_subset AND STATUTORY_COMPANY = :statutory_company AND LOB_CODE = :lob_code AND PROVINCE_CODE = :province_code AND REGIST_PROD_TYPE = :regist_prod_type AND OLD_NEW_MONIES_IND > :old_new_monies_ind) OR (TABLE_SUBSET = :table_subset AND STATUTORY_COMPANY = :statutory_company AND LOB_CODE = :lob_code AND PROVINCE_CODE = :province_code AND REGIST_PROD_TYPE = :regist_prod_type AND OLD_NEW_MONIES_IND = :old_new_monies_ind AND ATTAINED_AGE > :attained_age) OR (TABLE_SUBSET = :table_subset AND STATUTORY_COMPANY = :statutory_company AND LOB_CODE = :lob_code AND PROVINCE_CODE = :province_code AND REGIST_PROD_TYPE = :regist_prod_type AND OLD_NEW_MONIES_IND = :old_new_monies_ind AND ATTAINED_AGE = :attained_age AND EFFECTIVE_DATE > :effective_date) OR (TABLE_SUBSET = :table_subset AND STATUTORY_COMPANY = :statutory_company AND LOB_CODE = :lob_code AND PROVINCE_CODE = :province_code AND REGIST_PROD_TYPE = :regist_prod_type AND OLD_NEW_MONIES_IND = :old_new_monies_ind AND ATTAINED_AGE = :attained_age AND EFFECTIVE_DATE = :effective_date)) ";
                else
                    pagingWhere = ".TAC2F WHERE (COMPANY_CODE = :company_code) AND ((TABLE_SUBSET > :table_subset) OR (TABLE_SUBSET = :table_subset AND STATUTORY_COMPANY > :statutory_company) OR (TABLE_SUBSET = :table_subset AND STATUTORY_COMPANY = :statutory_company AND LOB_CODE > :lob_code) OR (TABLE_SUBSET = :table_subset AND STATUTORY_COMPANY = :statutory_company AND LOB_CODE = :lob_code AND PROVINCE_CODE > :province_code) OR (TABLE_SUBSET = :table_subset AND STATUTORY_COMPANY = :statutory_company AND LOB_CODE = :lob_code AND PROVINCE_CODE = :province_code AND REGIST_PROD_TYPE > :regist_prod_type) OR (TABLE_SUBSET = :table_subset AND STATUTORY_COMPANY = :statutory_company AND LOB_CODE = :lob_code AND PROVINCE_CODE = :province_code AND REGIST_PROD_TYPE = :regist_prod_type AND OLD_NEW_MONIES_IND > :old_new_monies_ind) OR (TABLE_SUBSET = :table_subset AND STATUTORY_COMPANY = :statutory_company AND LOB_CODE = :lob_code AND PROVINCE_CODE = :province_code AND REGIST_PROD_TYPE = :regist_prod_type AND OLD_NEW_MONIES_IND = :old_new_monies_ind AND ATTAINED_AGE > :attained_age) OR (TABLE_SUBSET = :table_subset AND STATUTORY_COMPANY = :statutory_company AND LOB_CODE = :lob_code AND PROVINCE_CODE = :province_code AND REGIST_PROD_TYPE = :regist_prod_type AND OLD_NEW_MONIES_IND = :old_new_monies_ind AND ATTAINED_AGE = :attained_age AND EFFECTIVE_DATE > :effective_date)) ";
            order = " ORDER BY COMPANY_CODE, TABLE_SUBSET, STATUTORY_COMPANY, LOB_CODE, PROVINCE_CODE, REGIST_PROD_TYPE, OLD_NEW_MONIES_IND, ATTAINED_AGE, EFFECTIVE_DATE";
        }
        else {
            if (isSubsetMode)
                pagingWhere = ".TAC2F WHERE (COMPANY_CODE = :company_code) AND (TABLE_SUBSET = :table_subset) AND ((STATUTORY_COMPANY < :statutory_company) OR (STATUTORY_COMPANY = :statutory_company AND LOB_CODE < :lob_code) OR (STATUTORY_COMPANY = :statutory_company AND LOB_CODE = :lob_code AND PROVINCE_CODE < :province_code) OR (STATUTORY_COMPANY = :statutory_company AND LOB_CODE = :lob_code AND PROVINCE_CODE = :province_code AND REGIST_PROD_TYPE < :regist_prod_type) OR (STATUTORY_COMPANY = :statutory_company AND LOB_CODE = :lob_code AND PROVINCE_CODE = :province_code AND REGIST_PROD_TYPE = :regist_prod_type AND OLD_NEW_MONIES_IND < :old_new_monies_ind) OR (STATUTORY_COMPANY = :statutory_company AND LOB_CODE = :lob_code AND PROVINCE_CODE = :province_code AND REGIST_PROD_TYPE = :regist_prod_type AND OLD_NEW_MONIES_IND = :old_new_monies_ind AND ATTAINED_AGE < :attained_age) OR (STATUTORY_COMPANY = :statutory_company AND LOB_CODE = :lob_code AND PROVINCE_CODE = :province_code AND REGIST_PROD_TYPE = :regist_prod_type AND OLD_NEW_MONIES_IND = :old_new_monies_ind AND ATTAINED_AGE = :attained_age AND EFFECTIVE_DATE < :effective_date)) ";
            else
                pagingWhere = ".TAC2F WHERE (COMPANY_CODE = :company_code) AND ((TABLE_SUBSET < :table_subset) OR (TABLE_SUBSET = :table_subset AND STATUTORY_COMPANY < :statutory_company) OR (TABLE_SUBSET = :table_subset AND STATUTORY_COMPANY = :statutory_company AND LOB_CODE < :lob_code) OR (TABLE_SUBSET = :table_subset AND STATUTORY_COMPANY = :statutory_company AND LOB_CODE = :lob_code AND PROVINCE_CODE < :province_code) OR (TABLE_SUBSET = :table_subset AND STATUTORY_COMPANY = :statutory_company AND LOB_CODE = :lob_code AND PROVINCE_CODE = :province_code AND REGIST_PROD_TYPE < :regist_prod_type) OR (TABLE_SUBSET = :table_subset AND STATUTORY_COMPANY = :statutory_company AND LOB_CODE = :lob_code AND PROVINCE_CODE = :province_code AND REGIST_PROD_TYPE = :regist_prod_type AND OLD_NEW_MONIES_IND < :old_new_monies_ind) OR (TABLE_SUBSET = :table_subset AND STATUTORY_COMPANY = :statutory_company AND LOB_CODE = :lob_code AND PROVINCE_CODE = :province_code AND REGIST_PROD_TYPE = :regist_prod_type AND OLD_NEW_MONIES_IND = :old_new_monies_ind AND ATTAINED_AGE < :attained_age) OR (TABLE_SUBSET = :table_subset AND STATUTORY_COMPANY = :statutory_company AND LOB_CODE = :lob_code AND PROVINCE_CODE = :province_code AND REGIST_PROD_TYPE = :regist_prod_type AND OLD_NEW_MONIES_IND = :old_new_monies_ind AND ATTAINED_AGE = :attained_age AND EFFECTIVE_DATE < :effective_date)) ";
            order = " ORDER BY COMPANY_CODE DESC, TABLE_SUBSET DESC, STATUTORY_COMPANY DESC, LOB_CODE DESC, PROVINCE_CODE DESC, REGIST_PROD_TYPE DESC, OLD_NEW_MONIES_IND DESC, ATTAINED_AGE DESC, EFFECTIVE_DATE DESC";
        }
        return pagingSql + schemaName + pagingWhere + order;
    }
    
    public String prepareInsertStmt(String schemaName) {
        StringBuffer sb = new StringBuffer();
        sb.append("INSERT INTO ").append(schemaName);
        sb.append(".TAC2F ( ");
        sb.append("COMPANY_CODE, TABLE_SUBSET, STATUTORY_COMPANY, LOB_CODE, PROVINCE_CODE, REGIST_PROD_TYPE, OLD_NEW_MONIES_IND, ATTAINED_AGE, EFFECTIVE_DATE, MINIMUM_FACTOR, MAXIMUM_FACTOR )");
        sb.append(" VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ? )");
        return sb.toString();
    }
    
    public String prepareUpdateStmt(String schemaName)
    {
        StringBuffer sb = new StringBuffer();
        sb.append("UPDATE ").append(schemaName);
        sb.append(".TAC2F ");
        sb.append(" SET COMPANY_CODE = ?, TABLE_SUBSET = ?, STATUTORY_COMPANY = ?, LOB_CODE = ?, PROVINCE_CODE = ?, REGIST_PROD_TYPE = ?, OLD_NEW_MONIES_IND = ?, ATTAINED_AGE = ?, EFFECTIVE_DATE = ?, MINIMUM_FACTOR = ?, MAXIMUM_FACTOR = ?");
        sb.append(" WHERE COMPANY_CODE = ? AND TABLE_SUBSET = ? AND STATUTORY_COMPANY = ? AND LOB_CODE = ? AND PROVINCE_CODE = ? AND REGIST_PROD_TYPE = ? AND OLD_NEW_MONIES_IND = ? AND ATTAINED_AGE = ? AND EFFECTIVE_DATE = ?");
        return sb.toString();
    }
    
    public String prepareDeleteStmt(String schemaName)
    {
        StringBuffer sb = new StringBuffer();
        sb.append("DELETE FROM ").append(schemaName);
        sb.append(".TAC2F ");
        sb.append(" WHERE COMPANY_CODE = ? AND TABLE_SUBSET = ? AND STATUTORY_COMPANY = ? AND LOB_CODE = ? AND PROVINCE_CODE = ? AND REGIST_PROD_TYPE = ? AND OLD_NEW_MONIES_IND = ? AND ATTAINED_AGE = ? AND EFFECTIVE_DATE = ?");
        return sb.toString();
    }
}
