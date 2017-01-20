package com.csc.fsg.life.pw.io.descriptor.wma;

import com.csc.fsg.life.pw.io.*;

public class T008XDescriptor
    extends TableDescriptor
{
    private static final String pagingSql = "SELECT COMPANY_CODE, LOB_CODE, LOB_NAME, LOB_NM_ABBRV, QUAL_IND, GROUP_INDICATOR, PENSION_INDICATOR, ANNUITY_TYPE, DEFERRED_IMMED_IND FROM ";
    
    public void initialize()
    {
        setRowClass(T008XRow.class);
        setTableName("T008X");
        setTableId("008");
        super.initialize();
    }
    
    public void initializeColumnDescriptors()
    {
        super.initializeColumnDescriptors();
        addColumnDescriptor(new ColumnDescriptor(this,"getCompanyCode","setCompanyCode","COMPANY_CODE,1,1,3,0,true|,0,CHAR,1,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getLobCode","setLobCode","LOB_CODE,1,2,3,0,true|,0,CHAR,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getLobName","setLobName","LOB_NAME,1,3,25,0,false|,0,CHAR,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getLobNmAbbrv","setLobNmAbbrv","LOB_NM_ABBRV,1,4,10,0,false|,0,CHAR,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getQualInd","setQualInd","QUAL_IND,1,5,1,0,false|,0,CHAR,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getGroupIndicator","setGroupIndicator","GROUP_INDICATOR,1,6,1,0,false|,0,CHAR,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getPensionIndicator","setPensionIndicator","PENSION_INDICATOR,1,7,1,0,false|,0,CHAR,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getAnnuityType","setAnnuityType","ANNUITY_TYPE,1,8,1,0,false|,0,CHAR,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getDeferredImmedInd","setDeferredImmedInd","DEFERRED_IMMED_IND,1,9,1,0,false|,0,CHAR,0,null,null,null,null,null"));
    }
    
    public String getPagingSQL(String schemaName,boolean isSubsetMode,boolean isNext, boolean locator)
    {
        String pagingWhere = null;
        String order = null;
        if (isNext) {
            if (isSubsetMode)
                if (locator)
                    pagingWhere = ".T008X WHERE (COMPANY_CODE = :company_code) AND ((LOB_CODE > :lob_code) OR (LOB_CODE = :lob_code)) ";
                else 
                    pagingWhere = ".T008X WHERE (COMPANY_CODE = :company_code) AND ((LOB_CODE > :lob_code)) ";
            else
                if (locator)
                    pagingWhere = ".T008X WHERE (COMPANY_CODE = :company_code) AND ((LOB_CODE > :lob_code) OR (LOB_CODE = :lob_code)) ";
                else
                    pagingWhere = ".T008X WHERE (COMPANY_CODE = :company_code) AND ((LOB_CODE > :lob_code)) ";
            order = " ORDER BY COMPANY_CODE, LOB_CODE";
        }
        else {
            if (isSubsetMode)
                pagingWhere = ".T008X WHERE (COMPANY_CODE = :company_code) AND ((LOB_CODE < :lob_code)) ";
            else
                pagingWhere = ".T008X WHERE (COMPANY_CODE = :company_code) AND ((LOB_CODE < :lob_code)) ";
            order = " ORDER BY COMPANY_CODE DESC, LOB_CODE DESC";
        }
        return pagingSql + schemaName + pagingWhere + order;
    }
    
    public String prepareInsertStmt(String schemaName) {
        StringBuffer sb = new StringBuffer();
        sb.append("INSERT INTO ").append(schemaName);
        sb.append(".T008X ( ");
        sb.append("COMPANY_CODE, LOB_CODE, LOB_NAME, LOB_NM_ABBRV, QUAL_IND, GROUP_INDICATOR, PENSION_INDICATOR, ANNUITY_TYPE, DEFERRED_IMMED_IND )");
        sb.append(" VALUES (?, ?, ?, ?, ?, ?, ?, ?, ? )");
        return sb.toString();
    }
    
    public String prepareUpdateStmt(String schemaName)
    {
        StringBuffer sb = new StringBuffer();
        sb.append("UPDATE ").append(schemaName);
        sb.append(".T008X ");
        sb.append(" SET COMPANY_CODE = ?, LOB_CODE = ?, LOB_NAME = ?, LOB_NM_ABBRV = ?, QUAL_IND = ?, GROUP_INDICATOR = ?, PENSION_INDICATOR = ?, ANNUITY_TYPE = ?, DEFERRED_IMMED_IND = ?");
        sb.append(" WHERE COMPANY_CODE = ? AND LOB_CODE = ?");
        return sb.toString();
    }
    
    public String prepareDeleteStmt(String schemaName)
    {
        StringBuffer sb = new StringBuffer();
        sb.append("DELETE FROM ").append(schemaName);
        sb.append(".T008X ");
        sb.append(" WHERE COMPANY_CODE = ? AND LOB_CODE = ?");
        return sb.toString();
    }
}
