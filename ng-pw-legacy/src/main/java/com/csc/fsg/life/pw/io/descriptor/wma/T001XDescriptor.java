package com.csc.fsg.life.pw.io.descriptor.wma;

import com.csc.fsg.life.pw.io.*;

public class T001XDescriptor
    extends TableDescriptor
{
    private static final String pagingSql = "SELECT COMPANY_CODE, COMPANY_NAME, COMPANY_NAME_ABBRV, CITIZENSHP_REQ_IND, OWNER_RES_REQ_IND FROM ";
    
    public void initialize()
    {
        setRowClass(T001XRow.class);
        setTableName("T001X");
        setTableId("001");
        super.initialize();
    }
    
    public void initializeColumnDescriptors()
    {
        super.initializeColumnDescriptors();
        addColumnDescriptor(new ColumnDescriptor(this,"getCompanyCode","setCompanyCode","COMPANY_CODE,1,1,3,0,true|,0,CHAR,1,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getCompanyName","setCompanyName","COMPANY_NAME,1,2,50,0,false|,0,CHAR,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getCompanyNameAbbrv","setCompanyNameAbbrv","COMPANY_NAME_ABBRV,1,3,20,0,false|,0,CHAR,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getCitizenshpReqInd","setCitizenshpReqInd","CITIZENSHP_REQ_IND,1,4,1,0,false|,0,CHAR,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getOwnerResReqInd","setOwnerResReqInd","OWNER_RES_REQ_IND,1,5,1,0,false|,0,CHAR,0,null,null,null,null,null"));
    }
    
    public String getPagingSQL(String schemaName,boolean isSubsetMode,boolean isNext, boolean locator)
    {
        String pagingWhere = null;
        String order = null;
        if (isNext) {
            if (isSubsetMode)
                if (locator)
                    pagingWhere = ".T001X WHERE (COMPANY_CODE = :company_code)";
                else 
                    pagingWhere = ".T001X WHERE (COMPANY_CODE = :company_code)";
            else
                if (locator)
                    pagingWhere = ".T001X WHERE (COMPANY_CODE = :company_code)";
                else
                    pagingWhere = ".T001X WHERE (COMPANY_CODE = :company_code)";
            order = " ORDER BY COMPANY_CODE";
        }
        else {
            if (isSubsetMode)
                pagingWhere = ".T001X WHERE (COMPANY_CODE = :company_code)";
            else
                pagingWhere = ".T001X WHERE (COMPANY_CODE = :company_code)";
            order = " ORDER BY COMPANY_CODE DESC";
        }
        return pagingSql + schemaName + pagingWhere + order;
    }
    
    public String prepareInsertStmt(String schemaName) {
        StringBuffer sb = new StringBuffer();
        sb.append("INSERT INTO ").append(schemaName);
        sb.append(".T001X ( ");
        sb.append("COMPANY_CODE, COMPANY_NAME, COMPANY_NAME_ABBRV, CITIZENSHP_REQ_IND, OWNER_RES_REQ_IND )");
        sb.append(" VALUES (?, ?, ?, ?, ? )");
        return sb.toString();
    }
    
    public String prepareUpdateStmt(String schemaName)
    {
        StringBuffer sb = new StringBuffer();
        sb.append("UPDATE ").append(schemaName);
        sb.append(".T001X ");
        sb.append(" SET COMPANY_CODE = ?, COMPANY_NAME = ?, COMPANY_NAME_ABBRV = ?, CITIZENSHP_REQ_IND = ?, OWNER_RES_REQ_IND = ?");
        sb.append(" WHERE COMPANY_CODE = ?");
        return sb.toString();
    }
    
    public String prepareDeleteStmt(String schemaName)
    {
        StringBuffer sb = new StringBuffer();
        sb.append("DELETE FROM ").append(schemaName);
        sb.append(".T001X ");
        sb.append(" WHERE COMPANY_CODE = ?");
        return sb.toString();
    }
}
