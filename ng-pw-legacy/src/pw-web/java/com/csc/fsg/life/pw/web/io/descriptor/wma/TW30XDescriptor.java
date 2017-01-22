package com.csc.fsg.life.pw.web.io.descriptor.wma;

import com.csc.fsg.life.pw.web.io.*;

public class TW30XDescriptor
    extends TableDescriptor
{
    private static final String pagingSql = "SELECT COMPANY_CODE, PRODUCT_PREFIX, TABLE_SUBSET, EFFECTIVE_DATE, PUR_SERIES_CODE, BENEFIT_DESCRIPTOR, DURATION, FLOOR_PCT, CAP_PCT, REVALUE_FLOOR, GUAR_PCT_INCREASE FROM ";
    
    public void initialize()
    {
        setRowClass(TW30XRow.class);
        setTableName("TW30X");
        setTableId("W30");
        super.initialize();
    }
    
    public void initializeColumnDescriptors()
    {
        super.initializeColumnDescriptors();
        addColumnDescriptor(new ColumnDescriptor(this,"getCompanyCode","setCompanyCode","COMPANY_CODE,1,1,3,0,true|A,0,CHAR,1,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getProductPrefix","setProductPrefix","PRODUCT_PREFIX,1,2,1,0,true|A,0,CHAR,1,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getTableSubset","setTableSubset","TABLE_SUBSET,1,3,16,0,true|A,0,CHAR,1,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getEffectiveDate","setEffectiveDate","EFFECTIVE_DATE,91,4,10,0,true|A,0,DATE,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getPurSeriesCode","setPurSeriesCode","PUR_SERIES_CODE,1,5,5,0,true|A,0,CHAR,4,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getBenefitDescriptor","setBenefitDescriptor","BENEFIT_DESCRIPTOR,1,6,8,0,true|A,0,CHAR,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getDuration","setDuration","DURATION,3,7,3,0,true|A,0,INTEGER,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getFloorPct","setFloorPct","FLOOR_PCT,3,8,5,3,false|A,0,DOUBLE,4,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getCapPct","setCapPct","CAP_PCT,3,9,5,3,false|A,0,DOUBLE,4,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getRevalueFloor","setRevalueFloor","REVALUE_FLOOR,3,10,2,0,false|A,0,INTEGER,4,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getGuarPctIncrease","setGuarPctIncrease","GUAR_PCT_INCREASE,3,11,5,3,false|A,0,DOUBLE,4,null,null,null,null,null"));
    }
    
    public String getPagingSQL(String schemaName,boolean isSubsetMode,boolean isNext, boolean locator)
    {
        String pagingWhere = null;
        String order = null;
        if (isNext) {
            if (isSubsetMode)
                if (locator)
                    pagingWhere = ".TW30X WHERE (COMPANY_CODE = :company_code) AND (PRODUCT_PREFIX = :product_prefix) AND (TABLE_SUBSET = :table_subset) AND ((EFFECTIVE_DATE > :effective_date) OR (EFFECTIVE_DATE = :effective_date AND PUR_SERIES_CODE > :pur_series_code) OR (EFFECTIVE_DATE = :effective_date AND PUR_SERIES_CODE = :pur_series_code AND BENEFIT_DESCRIPTOR > :benefit_descriptor) OR (EFFECTIVE_DATE = :effective_date AND PUR_SERIES_CODE = :pur_series_code AND BENEFIT_DESCRIPTOR = :benefit_descriptor AND DURATION > :duration) OR (EFFECTIVE_DATE = :effective_date AND PUR_SERIES_CODE = :pur_series_code AND BENEFIT_DESCRIPTOR = :benefit_descriptor AND DURATION = :duration)) ";
                else 
                    pagingWhere = ".TW30X WHERE (COMPANY_CODE = :company_code) AND (PRODUCT_PREFIX = :product_prefix) AND (TABLE_SUBSET = :table_subset) AND ((EFFECTIVE_DATE > :effective_date) OR (EFFECTIVE_DATE = :effective_date AND PUR_SERIES_CODE > :pur_series_code) OR (EFFECTIVE_DATE = :effective_date AND PUR_SERIES_CODE = :pur_series_code AND BENEFIT_DESCRIPTOR > :benefit_descriptor) OR (EFFECTIVE_DATE = :effective_date AND PUR_SERIES_CODE = :pur_series_code AND BENEFIT_DESCRIPTOR = :benefit_descriptor AND DURATION > :duration)) ";
            else
                if (locator)
                    pagingWhere = ".TW30X WHERE (COMPANY_CODE = :company_code) AND ((PRODUCT_PREFIX > :product_prefix) OR (PRODUCT_PREFIX = :product_prefix AND TABLE_SUBSET > :table_subset) OR (PRODUCT_PREFIX = :product_prefix AND TABLE_SUBSET = :table_subset AND EFFECTIVE_DATE > :effective_date) OR (PRODUCT_PREFIX = :product_prefix AND TABLE_SUBSET = :table_subset AND EFFECTIVE_DATE = :effective_date AND PUR_SERIES_CODE > :pur_series_code) OR (PRODUCT_PREFIX = :product_prefix AND TABLE_SUBSET = :table_subset AND EFFECTIVE_DATE = :effective_date AND PUR_SERIES_CODE = :pur_series_code AND BENEFIT_DESCRIPTOR > :benefit_descriptor) OR (PRODUCT_PREFIX = :product_prefix AND TABLE_SUBSET = :table_subset AND EFFECTIVE_DATE = :effective_date AND PUR_SERIES_CODE = :pur_series_code AND BENEFIT_DESCRIPTOR = :benefit_descriptor AND DURATION > :duration) OR (PRODUCT_PREFIX = :product_prefix AND TABLE_SUBSET = :table_subset AND EFFECTIVE_DATE = :effective_date AND PUR_SERIES_CODE = :pur_series_code AND BENEFIT_DESCRIPTOR = :benefit_descriptor AND DURATION = :duration)) ";
                else
                    pagingWhere = ".TW30X WHERE (COMPANY_CODE = :company_code) AND ((PRODUCT_PREFIX > :product_prefix) OR (PRODUCT_PREFIX = :product_prefix AND TABLE_SUBSET > :table_subset) OR (PRODUCT_PREFIX = :product_prefix AND TABLE_SUBSET = :table_subset AND EFFECTIVE_DATE > :effective_date) OR (PRODUCT_PREFIX = :product_prefix AND TABLE_SUBSET = :table_subset AND EFFECTIVE_DATE = :effective_date AND PUR_SERIES_CODE > :pur_series_code) OR (PRODUCT_PREFIX = :product_prefix AND TABLE_SUBSET = :table_subset AND EFFECTIVE_DATE = :effective_date AND PUR_SERIES_CODE = :pur_series_code AND BENEFIT_DESCRIPTOR > :benefit_descriptor) OR (PRODUCT_PREFIX = :product_prefix AND TABLE_SUBSET = :table_subset AND EFFECTIVE_DATE = :effective_date AND PUR_SERIES_CODE = :pur_series_code AND BENEFIT_DESCRIPTOR = :benefit_descriptor AND DURATION > :duration)) ";
            order = " ORDER BY COMPANY_CODE, PRODUCT_PREFIX, TABLE_SUBSET, EFFECTIVE_DATE, PUR_SERIES_CODE, BENEFIT_DESCRIPTOR, DURATION";
        }
        else {
            if (isSubsetMode)
                pagingWhere = ".TW30X WHERE (COMPANY_CODE = :company_code) AND (PRODUCT_PREFIX = :product_prefix) AND (TABLE_SUBSET = :table_subset) AND ((EFFECTIVE_DATE < :effective_date) OR (EFFECTIVE_DATE = :effective_date AND PUR_SERIES_CODE < :pur_series_code) OR (EFFECTIVE_DATE = :effective_date AND PUR_SERIES_CODE = :pur_series_code AND BENEFIT_DESCRIPTOR < :benefit_descriptor) OR (EFFECTIVE_DATE = :effective_date AND PUR_SERIES_CODE = :pur_series_code AND BENEFIT_DESCRIPTOR = :benefit_descriptor AND DURATION < :duration)) ";
            else
                pagingWhere = ".TW30X WHERE (COMPANY_CODE = :company_code) AND ((PRODUCT_PREFIX < :product_prefix) OR (PRODUCT_PREFIX = :product_prefix AND TABLE_SUBSET < :table_subset) OR (PRODUCT_PREFIX = :product_prefix AND TABLE_SUBSET = :table_subset AND EFFECTIVE_DATE < :effective_date) OR (PRODUCT_PREFIX = :product_prefix AND TABLE_SUBSET = :table_subset AND EFFECTIVE_DATE = :effective_date AND PUR_SERIES_CODE < :pur_series_code) OR (PRODUCT_PREFIX = :product_prefix AND TABLE_SUBSET = :table_subset AND EFFECTIVE_DATE = :effective_date AND PUR_SERIES_CODE = :pur_series_code AND BENEFIT_DESCRIPTOR < :benefit_descriptor) OR (PRODUCT_PREFIX = :product_prefix AND TABLE_SUBSET = :table_subset AND EFFECTIVE_DATE = :effective_date AND PUR_SERIES_CODE = :pur_series_code AND BENEFIT_DESCRIPTOR = :benefit_descriptor AND DURATION < :duration)) ";
            order = " ORDER BY COMPANY_CODE DESC, PRODUCT_PREFIX DESC, TABLE_SUBSET DESC, EFFECTIVE_DATE DESC, PUR_SERIES_CODE DESC, BENEFIT_DESCRIPTOR DESC, DURATION DESC";
        }
        return pagingSql + schemaName + pagingWhere + order;
    }
    
    public String prepareInsertStmt(String schemaName) {
        StringBuffer sb = new StringBuffer();
        sb.append("INSERT INTO ").append(schemaName);
        sb.append(".TW30X ( ");
        sb.append("COMPANY_CODE, PRODUCT_PREFIX, TABLE_SUBSET, EFFECTIVE_DATE, PUR_SERIES_CODE, BENEFIT_DESCRIPTOR, DURATION, FLOOR_PCT, CAP_PCT, REVALUE_FLOOR, GUAR_PCT_INCREASE )");
        sb.append(" VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ? )");
        return sb.toString();
    }
    
    public String prepareUpdateStmt(String schemaName)
    {
        StringBuffer sb = new StringBuffer();
        sb.append("UPDATE ").append(schemaName);
        sb.append(".TW30X ");
        sb.append(" SET COMPANY_CODE = ?, PRODUCT_PREFIX = ?, TABLE_SUBSET = ?, EFFECTIVE_DATE = ?, PUR_SERIES_CODE = ?, BENEFIT_DESCRIPTOR = ?, DURATION = ?, FLOOR_PCT = ?, CAP_PCT = ?, REVALUE_FLOOR = ?, GUAR_PCT_INCREASE = ?");
        sb.append(" WHERE COMPANY_CODE = ? AND PRODUCT_PREFIX = ? AND TABLE_SUBSET = ? AND EFFECTIVE_DATE = ? AND PUR_SERIES_CODE = ? AND BENEFIT_DESCRIPTOR = ? AND DURATION = ?");
        return sb.toString();
    }
    
    public String prepareDeleteStmt(String schemaName)
    {
        StringBuffer sb = new StringBuffer();
        sb.append("DELETE FROM ").append(schemaName);
        sb.append(".TW30X ");
        sb.append(" WHERE COMPANY_CODE = ? AND PRODUCT_PREFIX = ? AND TABLE_SUBSET = ? AND EFFECTIVE_DATE = ? AND PUR_SERIES_CODE = ? AND BENEFIT_DESCRIPTOR = ? AND DURATION = ?");
        return sb.toString();
    }
}
