package com.csc.fsg.life.pw.io.descriptor.wma;

import com.csc.fsg.life.pw.io.*;

public class T057XDescriptor
    extends TableDescriptor
{
    private static final String pagingSql = "SELECT COMPANY_CODE, FUND_NUMBER, VOTE_FACTOR, PROSPECTUS_IND, DEFAULT_VOTE_01, DEFAULT_VOTE_02, DEFAULT_VOTE_03, DEFAULT_VOTE_04, DEFAULT_VOTE_05, DEFAULT_VOTE_06, DEFAULT_VOTE_07, DEFAULT_VOTE_08, DEFAULT_VOTE_09, DEFAULT_VOTE_10 FROM ";
    
    public void initialize()
    {
        setRowClass(T057XRow.class);
        setTableName("T057X");
        setTableId("057");
        super.initialize();
    }
    
    public void initializeColumnDescriptors()
    {
        super.initializeColumnDescriptors();
        addColumnDescriptor(new ColumnDescriptor(this,"getCompanyCode","setCompanyCode","COMPANY_CODE,1,1,3,0,true|,0,CHAR,1,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getFundNumber","setFundNumber","FUND_NUMBER,3,2,8,0,true|,0,INTEGER,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getVoteFactor","setVoteFactor","VOTE_FACTOR,3,3,5,4,false|,0,DOUBLE,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getProspectusInd","setProspectusInd","PROSPECTUS_IND,1,4,1,0,false|,0,CHAR,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getDefaultVote01","setDefaultVote01","DEFAULT_VOTE_01,1,5,1,0,false|,0,CHAR,4,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getDefaultVote02","setDefaultVote02","DEFAULT_VOTE_02,1,6,1,0,false|,0,CHAR,4,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getDefaultVote03","setDefaultVote03","DEFAULT_VOTE_03,1,7,1,0,false|,0,CHAR,4,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getDefaultVote04","setDefaultVote04","DEFAULT_VOTE_04,1,8,1,0,false|,0,CHAR,4,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getDefaultVote05","setDefaultVote05","DEFAULT_VOTE_05,1,9,1,0,false|,0,CHAR,4,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getDefaultVote06","setDefaultVote06","DEFAULT_VOTE_06,1,10,1,0,false|,0,CHAR,4,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getDefaultVote07","setDefaultVote07","DEFAULT_VOTE_07,1,11,1,0,false|,0,CHAR,4,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getDefaultVote08","setDefaultVote08","DEFAULT_VOTE_08,1,12,1,0,false|,0,CHAR,4,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getDefaultVote09","setDefaultVote09","DEFAULT_VOTE_09,1,13,1,0,false|,0,CHAR,4,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getDefaultVote10","setDefaultVote10","DEFAULT_VOTE_10,1,14,1,0,false|,0,CHAR,4,null,null,null,null,null"));
    }
    
    public String getPagingSQL(String schemaName,boolean isSubsetMode,boolean isNext, boolean locator)
    {
        String pagingWhere = null;
        String order = null;
        if (isNext) {
            if (isSubsetMode)
                if (locator)
                    pagingWhere = ".T057X WHERE (COMPANY_CODE = :company_code) AND ((FUND_NUMBER > :fund_number) OR (FUND_NUMBER = :fund_number)) ";
                else 
                    pagingWhere = ".T057X WHERE (COMPANY_CODE = :company_code) AND ((FUND_NUMBER > :fund_number)) ";
            else
                if (locator)
                    pagingWhere = ".T057X WHERE (COMPANY_CODE = :company_code) AND ((FUND_NUMBER > :fund_number) OR (FUND_NUMBER = :fund_number)) ";
                else
                    pagingWhere = ".T057X WHERE (COMPANY_CODE = :company_code) AND ((FUND_NUMBER > :fund_number)) ";
            order = " ORDER BY COMPANY_CODE, FUND_NUMBER";
        }
        else {
            if (isSubsetMode)
                pagingWhere = ".T057X WHERE (COMPANY_CODE = :company_code) AND ((FUND_NUMBER < :fund_number)) ";
            else
                pagingWhere = ".T057X WHERE (COMPANY_CODE = :company_code) AND ((FUND_NUMBER < :fund_number)) ";
            order = " ORDER BY COMPANY_CODE DESC, FUND_NUMBER DESC";
        }
        return pagingSql + schemaName + pagingWhere + order;
    }
    
    public String prepareInsertStmt(String schemaName) {
        StringBuffer sb = new StringBuffer();
        sb.append("INSERT INTO ").append(schemaName);
        sb.append(".T057X ( ");
        sb.append("COMPANY_CODE, FUND_NUMBER, VOTE_FACTOR, PROSPECTUS_IND, DEFAULT_VOTE_01, DEFAULT_VOTE_02, DEFAULT_VOTE_03, DEFAULT_VOTE_04, DEFAULT_VOTE_05, DEFAULT_VOTE_06, DEFAULT_VOTE_07, DEFAULT_VOTE_08, DEFAULT_VOTE_09, DEFAULT_VOTE_10 )");
        sb.append(" VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ? )");
        return sb.toString();
    }
    
    public String prepareUpdateStmt(String schemaName)
    {
        StringBuffer sb = new StringBuffer();
        sb.append("UPDATE ").append(schemaName);
        sb.append(".T057X ");
        sb.append(" SET COMPANY_CODE = ?, FUND_NUMBER = ?, VOTE_FACTOR = ?, PROSPECTUS_IND = ?, DEFAULT_VOTE_01 = ?, DEFAULT_VOTE_02 = ?, DEFAULT_VOTE_03 = ?, DEFAULT_VOTE_04 = ?, DEFAULT_VOTE_05 = ?, DEFAULT_VOTE_06 = ?, DEFAULT_VOTE_07 = ?, DEFAULT_VOTE_08 = ?, DEFAULT_VOTE_09 = ?, DEFAULT_VOTE_10 = ?");
        sb.append(" WHERE COMPANY_CODE = ? AND FUND_NUMBER = ?");
        return sb.toString();
    }
    
    public String prepareDeleteStmt(String schemaName)
    {
        StringBuffer sb = new StringBuffer();
        sb.append("DELETE FROM ").append(schemaName);
        sb.append(".T057X ");
        sb.append(" WHERE COMPANY_CODE = ? AND FUND_NUMBER = ?");
        return sb.toString();
    }
}
