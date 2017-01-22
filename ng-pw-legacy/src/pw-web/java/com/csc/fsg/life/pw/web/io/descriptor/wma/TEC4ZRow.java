package com.csc.fsg.life.pw.web.io.descriptor.wma;

import com.csc.fsg.life.pw.web.io.Row;

public class TEC4ZRow
    extends Row
{
    public String companyCode;
    public String tableSubset;
    public String age;
    public String height;
    public String minWeight;
    public String maxWeight;
    public String debitPoints;
    public String ruleset;
    public String metricEnglishInd;
    
    public String getCompanyCode() {
        return companyCode;
    }
    public void setCompanyCode(String newCompanyCode)
    {
        companyCode = newCompanyCode;
    }
    
    public String getTableSubset() {
        return tableSubset;
    }
    public void setTableSubset(String newTableSubset)
    {
        tableSubset = newTableSubset;
    }
    
    public String getAge() {
        return age;
    }
    public void setAge(String newAge)
    {
        age = newAge;
    }
    
    public String getHeight() {
        return height;
    }
    public void setHeight(String newHeight)
    {
        height = newHeight;
    }
    
    public String getMinWeight() {
        return minWeight;
    }
    public void setMinWeight(String newMinWeight)
    {
        minWeight = newMinWeight;
    }
    
    public String getMaxWeight() {
        return maxWeight;
    }
    public void setMaxWeight(String newMaxWeight)
    {
        maxWeight = newMaxWeight;
    }
    
    public String getDebitPoints() {
        return debitPoints;
    }
    public void setDebitPoints(String newDebitPoints)
    {
        debitPoints = newDebitPoints;
    }
    
    public String getRuleset() {
        return ruleset;
    }
    public void setRuleset(String newRuleset)
    {
        ruleset = newRuleset;
    }
    
    public String getMetricEnglishInd() {
        return metricEnglishInd;
    }
    public void setMetricEnglishInd(String newMetricEnglishInd)
    {
        metricEnglishInd = newMetricEnglishInd;
    }
}
