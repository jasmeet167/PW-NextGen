package com.csc.fsg.life.tools.copybook.model;

/**
   POJO for global field settings in the copy object generator.
**/
public class GlobalFieldGenInfo
{
    private String justification;
    private String defaultValue;
    
	/**
	   Returns value of the Justification property.
	   @return the value of the Justification property.
	**/
    public String getJustification()
    {
        return justification;
    }

	/**
	   Sets value of the Justification property.
	   @param justification the value of the Justification property.
	**/
    public void setJustification(String justification)
    {
        this.justification = justification;
    }

	/**
	   Returns value of the Default Value property.
	   @return the value of the Default Value property.
	**/
    public String getDefaultValue()
    {
        return defaultValue;
    }

	/**
	   Sets value of the Default Value property.
	   @param defaultValue the value of the Default Value property.
	**/
    public void setDefaultValue(String defaultValue)
    {
        this.defaultValue = defaultValue;
    }
}
