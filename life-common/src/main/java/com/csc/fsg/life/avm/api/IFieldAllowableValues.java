package com.csc.fsg.life.avm.api;



/**
   An interface for a fields allowable values.
**/
public interface IFieldAllowableValues 
{
	/**
	   Returns the type of field - List or Range.
	   @return the type of field - List or Range.
	*/
	public String getFieldType();
	
	/**
	   Returns the allowable values object for this field.
	   @return the allowable values object for this field.
    */
	public AllowableValues getAllowableValues();
	
	/**
	   Returns the default value for this field.
	   @return the default value for this field.
    */
	public String getFieldDefault();
}
