package com.csc.fsg.life.pw.web.io.descriptor.wma;

import com.csc.fsg.life.pw.web.io.*;

public class TEC4ZDescriptor
    extends TableDescriptor
{
    private static final String pagingSql = "SELECT COMPANY_CODE, TABLE_SUBSET, AGE, HEIGHT, MIN_WEIGHT, MAX_WEIGHT, DEBIT_POINTS, RULESET, METRIC_ENGLISH_IND FROM ";
    
    public void initialize()
    {
        setRowClass(TEC4ZRow.class);
        setTableName("TEC4Z");
        setTableId("EC4");
        super.initialize();
    }
    
    public void initializeColumnDescriptors()
    {
        super.initializeColumnDescriptors();
        addColumnDescriptor(new ColumnDescriptor(this,"getCompanyCode","setCompanyCode","COMPANY_CODE,1,1,3,0,true|,0,CHAR,1,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getTableSubset","setTableSubset","TABLE_SUBSET,1,2,16,0,true|,0,CHAR,1,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getAge","setAge","AGE,3,3,3,0,true|,0,INTEGER,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getHeight","setHeight","HEIGHT,3,4,3,0,true|,0,INTEGER,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getMinWeight","setMinWeight","MIN_WEIGHT,3,5,3,0,true|,0,INTEGER,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getMaxWeight","setMaxWeight","MAX_WEIGHT,3,6,3,0,true|,0,INTEGER,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getDebitPoints","setDebitPoints","DEBIT_POINTS,3,7,4,0,false|,0,INTEGER,0,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getRuleset","setRuleset","RULESET,1,8,8,0,false|,0,CHAR,4,null,null,null,null,null"));
        addColumnDescriptor(new ColumnDescriptor(this,"getMetricEnglishInd","setMetricEnglishInd","METRIC_ENGLISH_IND,1,9,1,0,false|,0,CHAR,0,null,null,null,null,null"));
    }
    
    public String getPagingSQL(String schemaName,boolean isSubsetMode,boolean isNext, boolean locator)
    {
        String pagingWhere = null;
        String order = null;
        if (isNext) {
            if (isSubsetMode)
                if (locator)
                    pagingWhere = ".TEC4Z WHERE (COMPANY_CODE = :company_code) AND (TABLE_SUBSET = :table_subset) AND ((AGE > :age) OR (AGE = :age AND HEIGHT > :height) OR (AGE = :age AND HEIGHT = :height AND MIN_WEIGHT > :min_weight) OR (AGE = :age AND HEIGHT = :height AND MIN_WEIGHT = :min_weight AND MAX_WEIGHT > :max_weight) OR (AGE = :age AND HEIGHT = :height AND MIN_WEIGHT = :min_weight AND MAX_WEIGHT = :max_weight)) ";
                else 
                    pagingWhere = ".TEC4Z WHERE (COMPANY_CODE = :company_code) AND (TABLE_SUBSET = :table_subset) AND ((AGE > :age) OR (AGE = :age AND HEIGHT > :height) OR (AGE = :age AND HEIGHT = :height AND MIN_WEIGHT > :min_weight) OR (AGE = :age AND HEIGHT = :height AND MIN_WEIGHT = :min_weight AND MAX_WEIGHT > :max_weight)) ";
            else
                if (locator)
                    pagingWhere = ".TEC4Z WHERE (COMPANY_CODE = :company_code) AND ((TABLE_SUBSET > :table_subset) OR (TABLE_SUBSET = :table_subset AND AGE > :age) OR (TABLE_SUBSET = :table_subset AND AGE = :age AND HEIGHT > :height) OR (TABLE_SUBSET = :table_subset AND AGE = :age AND HEIGHT = :height AND MIN_WEIGHT > :min_weight) OR (TABLE_SUBSET = :table_subset AND AGE = :age AND HEIGHT = :height AND MIN_WEIGHT = :min_weight AND MAX_WEIGHT > :max_weight) OR (TABLE_SUBSET = :table_subset AND AGE = :age AND HEIGHT = :height AND MIN_WEIGHT = :min_weight AND MAX_WEIGHT = :max_weight)) ";
                else
                    pagingWhere = ".TEC4Z WHERE (COMPANY_CODE = :company_code) AND ((TABLE_SUBSET > :table_subset) OR (TABLE_SUBSET = :table_subset AND AGE > :age) OR (TABLE_SUBSET = :table_subset AND AGE = :age AND HEIGHT > :height) OR (TABLE_SUBSET = :table_subset AND AGE = :age AND HEIGHT = :height AND MIN_WEIGHT > :min_weight) OR (TABLE_SUBSET = :table_subset AND AGE = :age AND HEIGHT = :height AND MIN_WEIGHT = :min_weight AND MAX_WEIGHT > :max_weight)) ";
            order = " ORDER BY COMPANY_CODE, TABLE_SUBSET, AGE, HEIGHT, MIN_WEIGHT, MAX_WEIGHT";
        }
        else {
            if (isSubsetMode)
                pagingWhere = ".TEC4Z WHERE (COMPANY_CODE = :company_code) AND (TABLE_SUBSET = :table_subset) AND ((AGE < :age) OR (AGE = :age AND HEIGHT < :height) OR (AGE = :age AND HEIGHT = :height AND MIN_WEIGHT < :min_weight) OR (AGE = :age AND HEIGHT = :height AND MIN_WEIGHT = :min_weight AND MAX_WEIGHT < :max_weight)) ";
            else
                pagingWhere = ".TEC4Z WHERE (COMPANY_CODE = :company_code) AND ((TABLE_SUBSET < :table_subset) OR (TABLE_SUBSET = :table_subset AND AGE < :age) OR (TABLE_SUBSET = :table_subset AND AGE = :age AND HEIGHT < :height) OR (TABLE_SUBSET = :table_subset AND AGE = :age AND HEIGHT = :height AND MIN_WEIGHT < :min_weight) OR (TABLE_SUBSET = :table_subset AND AGE = :age AND HEIGHT = :height AND MIN_WEIGHT = :min_weight AND MAX_WEIGHT < :max_weight)) ";
            order = " ORDER BY COMPANY_CODE DESC, TABLE_SUBSET DESC, AGE DESC, HEIGHT DESC, MIN_WEIGHT DESC, MAX_WEIGHT DESC";
        }
        return pagingSql + schemaName + pagingWhere + order;
    }
    
    public String prepareInsertStmt(String schemaName) {
        StringBuffer sb = new StringBuffer();
        sb.append("INSERT INTO ").append(schemaName);
        sb.append(".TEC4Z ( ");
        sb.append("COMPANY_CODE, TABLE_SUBSET, AGE, HEIGHT, MIN_WEIGHT, MAX_WEIGHT, DEBIT_POINTS, RULESET, METRIC_ENGLISH_IND )");
        sb.append(" VALUES (?, ?, ?, ?, ?, ?, ?, ?, ? )");
        return sb.toString();
    }
    
    public String prepareUpdateStmt(String schemaName)
    {
        StringBuffer sb = new StringBuffer();
        sb.append("UPDATE ").append(schemaName);
        sb.append(".TEC4Z ");
        sb.append(" SET COMPANY_CODE = ?, TABLE_SUBSET = ?, AGE = ?, HEIGHT = ?, MIN_WEIGHT = ?, MAX_WEIGHT = ?, DEBIT_POINTS = ?, RULESET = ?, METRIC_ENGLISH_IND = ?");
        sb.append(" WHERE COMPANY_CODE = ? AND TABLE_SUBSET = ? AND AGE = ? AND HEIGHT = ? AND MIN_WEIGHT = ? AND MAX_WEIGHT = ?");
        return sb.toString();
    }
    
    public String prepareDeleteStmt(String schemaName)
    {
        StringBuffer sb = new StringBuffer();
        sb.append("DELETE FROM ").append(schemaName);
        sb.append(".TEC4Z ");
        sb.append(" WHERE COMPANY_CODE = ? AND TABLE_SUBSET = ? AND AGE = ? AND HEIGHT = ? AND MIN_WEIGHT = ? AND MAX_WEIGHT = ?");
        return sb.toString();
    }
}
