package com.csc.fsg.life.pw.io.descriptor.wma;

import com.csc.fsg.life.pw.io.*;

public class TW15XDescriptor
    extends TableDescriptor
{
    private static final String pagingSql = "SELECT COMPANY_CODE, PRODUCT_PREFIX, TABLE_SUBSET, BENEFIT_DESCRIPTOR, EFFECTIVE_DATE, SEQUENCE_NUMBER, ANN_STAT, ANN_PCT, JNT_ANN_STAT, JNT_ANN_PCT, START_CODE, MONTHS_PAY, FUNDING_CODE, BEN_SEG_TYPE_CODE, EXCESS_INT_CODE FROM ";
    
    public void initialize()
    {
        setRowClass(TW15XRow.class);
        setTableName("TW15X");
        setTableId("W15");
        super.initialize();
    }
    
    public void initializeColumnDescriptors()
    {
        super.initializeColumnDescriptors();
        addColumnDescriptor(new ColumnDescriptor(this,"getCompanyCode","setCompanyCode","COMPANY_CODE,1,1,3,0,true|A,0,CHAR,1,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getProductPrefix","setProductPrefix","PRODUCT_PREFIX,1,2,1,0,true|A,0,CHAR,1,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getTableSubset","setTableSubset","TABLE_SUBSET,1,3,16,0,true|A,0,CHAR,1,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getBenefitDescriptor","setBenefitDescriptor","BENEFIT_DESCRIPTOR,1,4,8,0,true|A,0,CHAR,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getEffectiveDate","setEffectiveDate","EFFECTIVE_DATE,91,5,10,0,true|A,0,DATE,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getSequenceNumber","setSequenceNumber","SEQUENCE_NUMBER,3,6,2,0,true|A,0,INTEGER,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getAnnStat","setAnnStat","ANN_STAT,1,7,1,0,false|A,0,CHAR,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getAnnPct","setAnnPct","ANN_PCT,3,8,5,4,false|A,0,DOUBLE,4,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getJntAnnStat","setJntAnnStat","JNT_ANN_STAT,1,9,1,0,false|A,0,CHAR,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getJntAnnPct","setJntAnnPct","JNT_ANN_PCT,3,10,5,4,false|A,0,DOUBLE,4,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getStartCode","setStartCode","START_CODE,1,11,1,0,false|A,0,CHAR,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getMonthsPay","setMonthsPay","MONTHS_PAY,3,12,3,0,false|A,0,INTEGER,4,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getFundingCode","setFundingCode","FUNDING_CODE,1,13,1,0,false|A,0,CHAR,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getBenSegTypeCode","setBenSegTypeCode","BEN_SEG_TYPE_CODE,1,14,1,0,false|A,0,CHAR,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getExcessIntCode","setExcessIntCode","EXCESS_INT_CODE,1,15,1,0,false|A,0,CHAR,0,null,null,null,null,null"));
    }
    
    public String getPagingSQL(String schemaName,boolean isSubsetMode,boolean isNext, boolean locator)
    {
        String pagingWhere = null;
        String order = null;
        if (isNext) {
            if (isSubsetMode)
                if (locator)
                    pagingWhere = ".TW15X WHERE (COMPANY_CODE = :company_code) AND (PRODUCT_PREFIX = :product_prefix) AND (TABLE_SUBSET = :table_subset) AND ((BENEFIT_DESCRIPTOR > :benefit_descriptor) OR (BENEFIT_DESCRIPTOR = :benefit_descriptor AND EFFECTIVE_DATE > :effective_date) OR (BENEFIT_DESCRIPTOR = :benefit_descriptor AND EFFECTIVE_DATE = :effective_date AND SEQUENCE_NUMBER > :sequence_number) OR (BENEFIT_DESCRIPTOR = :benefit_descriptor AND EFFECTIVE_DATE = :effective_date AND SEQUENCE_NUMBER = :sequence_number)) ";
                else 
                    pagingWhere = ".TW15X WHERE (COMPANY_CODE = :company_code) AND (PRODUCT_PREFIX = :product_prefix) AND (TABLE_SUBSET = :table_subset) AND ((BENEFIT_DESCRIPTOR > :benefit_descriptor) OR (BENEFIT_DESCRIPTOR = :benefit_descriptor AND EFFECTIVE_DATE > :effective_date) OR (BENEFIT_DESCRIPTOR = :benefit_descriptor AND EFFECTIVE_DATE = :effective_date AND SEQUENCE_NUMBER > :sequence_number)) ";
            else
                if (locator)
                    pagingWhere = ".TW15X WHERE (COMPANY_CODE = :company_code) AND ((PRODUCT_PREFIX > :product_prefix) OR (PRODUCT_PREFIX = :product_prefix AND TABLE_SUBSET > :table_subset) OR (PRODUCT_PREFIX = :product_prefix AND TABLE_SUBSET = :table_subset AND BENEFIT_DESCRIPTOR > :benefit_descriptor) OR (PRODUCT_PREFIX = :product_prefix AND TABLE_SUBSET = :table_subset AND BENEFIT_DESCRIPTOR = :benefit_descriptor AND EFFECTIVE_DATE > :effective_date) OR (PRODUCT_PREFIX = :product_prefix AND TABLE_SUBSET = :table_subset AND BENEFIT_DESCRIPTOR = :benefit_descriptor AND EFFECTIVE_DATE = :effective_date AND SEQUENCE_NUMBER > :sequence_number) OR (PRODUCT_PREFIX = :product_prefix AND TABLE_SUBSET = :table_subset AND BENEFIT_DESCRIPTOR = :benefit_descriptor AND EFFECTIVE_DATE = :effective_date AND SEQUENCE_NUMBER = :sequence_number)) ";
                else
                    pagingWhere = ".TW15X WHERE (COMPANY_CODE = :company_code) AND ((PRODUCT_PREFIX > :product_prefix) OR (PRODUCT_PREFIX = :product_prefix AND TABLE_SUBSET > :table_subset) OR (PRODUCT_PREFIX = :product_prefix AND TABLE_SUBSET = :table_subset AND BENEFIT_DESCRIPTOR > :benefit_descriptor) OR (PRODUCT_PREFIX = :product_prefix AND TABLE_SUBSET = :table_subset AND BENEFIT_DESCRIPTOR = :benefit_descriptor AND EFFECTIVE_DATE > :effective_date) OR (PRODUCT_PREFIX = :product_prefix AND TABLE_SUBSET = :table_subset AND BENEFIT_DESCRIPTOR = :benefit_descriptor AND EFFECTIVE_DATE = :effective_date AND SEQUENCE_NUMBER > :sequence_number)) ";
            order = " ORDER BY COMPANY_CODE, PRODUCT_PREFIX, TABLE_SUBSET, BENEFIT_DESCRIPTOR, EFFECTIVE_DATE, SEQUENCE_NUMBER";
        }
        else {
            if (isSubsetMode)
                pagingWhere = ".TW15X WHERE (COMPANY_CODE = :company_code) AND (PRODUCT_PREFIX = :product_prefix) AND (TABLE_SUBSET = :table_subset) AND ((BENEFIT_DESCRIPTOR < :benefit_descriptor) OR (BENEFIT_DESCRIPTOR = :benefit_descriptor AND EFFECTIVE_DATE < :effective_date) OR (BENEFIT_DESCRIPTOR = :benefit_descriptor AND EFFECTIVE_DATE = :effective_date AND SEQUENCE_NUMBER < :sequence_number)) ";
            else
                pagingWhere = ".TW15X WHERE (COMPANY_CODE = :company_code) AND ((PRODUCT_PREFIX < :product_prefix) OR (PRODUCT_PREFIX = :product_prefix AND TABLE_SUBSET < :table_subset) OR (PRODUCT_PREFIX = :product_prefix AND TABLE_SUBSET = :table_subset AND BENEFIT_DESCRIPTOR < :benefit_descriptor) OR (PRODUCT_PREFIX = :product_prefix AND TABLE_SUBSET = :table_subset AND BENEFIT_DESCRIPTOR = :benefit_descriptor AND EFFECTIVE_DATE < :effective_date) OR (PRODUCT_PREFIX = :product_prefix AND TABLE_SUBSET = :table_subset AND BENEFIT_DESCRIPTOR = :benefit_descriptor AND EFFECTIVE_DATE = :effective_date AND SEQUENCE_NUMBER < :sequence_number)) ";
            order = " ORDER BY COMPANY_CODE DESC, PRODUCT_PREFIX DESC, TABLE_SUBSET DESC, BENEFIT_DESCRIPTOR DESC, EFFECTIVE_DATE DESC, SEQUENCE_NUMBER DESC";
        }
        return pagingSql + schemaName + pagingWhere + order;
    }
    
    public String prepareInsertStmt(String schemaName) {
        StringBuffer sb = new StringBuffer();
        sb.append("INSERT INTO ").append(schemaName);
        sb.append(".TW15X ( ");
        sb.append("COMPANY_CODE, PRODUCT_PREFIX, TABLE_SUBSET, BENEFIT_DESCRIPTOR, EFFECTIVE_DATE, SEQUENCE_NUMBER, ANN_STAT, ANN_PCT, JNT_ANN_STAT, JNT_ANN_PCT, START_CODE, MONTHS_PAY, FUNDING_CODE, BEN_SEG_TYPE_CODE, EXCESS_INT_CODE )");
        sb.append(" VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ? )");
        return sb.toString();
    }
    
    public String prepareUpdateStmt(String schemaName)
    {
        StringBuffer sb = new StringBuffer();
        sb.append("UPDATE ").append(schemaName);
        sb.append(".TW15X ");
        sb.append(" SET COMPANY_CODE = ?, PRODUCT_PREFIX = ?, TABLE_SUBSET = ?, BENEFIT_DESCRIPTOR = ?, EFFECTIVE_DATE = ?, SEQUENCE_NUMBER = ?, ANN_STAT = ?, ANN_PCT = ?, JNT_ANN_STAT = ?, JNT_ANN_PCT = ?, START_CODE = ?, MONTHS_PAY = ?, FUNDING_CODE = ?, BEN_SEG_TYPE_CODE = ?, EXCESS_INT_CODE = ?");
        sb.append(" WHERE COMPANY_CODE = ? AND PRODUCT_PREFIX = ? AND TABLE_SUBSET = ? AND BENEFIT_DESCRIPTOR = ? AND EFFECTIVE_DATE = ? AND SEQUENCE_NUMBER = ?");
        return sb.toString();
    }
    
    public String prepareDeleteStmt(String schemaName)
    {
        StringBuffer sb = new StringBuffer();
        sb.append("DELETE FROM ").append(schemaName);
        sb.append(".TW15X ");
        sb.append(" WHERE COMPANY_CODE = ? AND PRODUCT_PREFIX = ? AND TABLE_SUBSET = ? AND BENEFIT_DESCRIPTOR = ? AND EFFECTIVE_DATE = ? AND SEQUENCE_NUMBER = ?");
        return sb.toString();
    }
}
