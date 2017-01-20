package com.csc.fsg.life.pw.io.descriptor.wma;

import com.csc.fsg.life.pw.io.Row;

public class TW30XRow
    extends Row
{
    public String companyCode;
    public String productPrefix;
    public String tableSubset;
    public String effectiveDate;
    public String purSeriesCode;
    public String benefitDescriptor;
    public String duration;
    public String floorPct;
    public String capPct;
    public String revalueFloor;
    public String guarPctIncrease;
    
    public String getCompanyCode() {
        return companyCode;
    }
    public void setCompanyCode(String newCompanyCode)
    {
        companyCode = newCompanyCode;
    }
    
    public String getProductPrefix() {
        return productPrefix;
    }
    public void setProductPrefix(String newProductPrefix)
    {
        productPrefix = newProductPrefix;
    }
    
    public String getTableSubset() {
        return tableSubset;
    }
    public void setTableSubset(String newTableSubset)
    {
        tableSubset = newTableSubset;
    }
    
    public String getEffectiveDate() {
        return effectiveDate;
    }
    public void setEffectiveDate(String newEffectiveDate)
    {
        effectiveDate = newEffectiveDate;
    }
    
    public String getPurSeriesCode() {
        return purSeriesCode;
    }
    public void setPurSeriesCode(String newPurSeriesCode)
    {
        purSeriesCode = newPurSeriesCode;
    }
    
    public String getBenefitDescriptor() {
        return benefitDescriptor;
    }
    public void setBenefitDescriptor(String newBenefitDescriptor)
    {
        benefitDescriptor = newBenefitDescriptor;
    }
    
    public String getDuration() {
        return duration;
    }
    public void setDuration(String newDuration)
    {
        duration = newDuration;
    }
    
    public String getFloorPct() {
        return floorPct;
    }
    public void setFloorPct(String newFloorPct)
    {
        floorPct = newFloorPct;
    }
    
    public String getCapPct() {
        return capPct;
    }
    public void setCapPct(String newCapPct)
    {
        capPct = newCapPct;
    }
    
    public String getRevalueFloor() {
        return revalueFloor;
    }
    public void setRevalueFloor(String newRevalueFloor)
    {
        revalueFloor = newRevalueFloor;
    }
    
    public String getGuarPctIncrease() {
        return guarPctIncrease;
    }
    public void setGuarPctIncrease(String newGuarPctIncrease)
    {
        guarPctIncrease = newGuarPctIncrease;
    }
}
