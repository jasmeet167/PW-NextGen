package com.csc.fsg.life.tools.copybook.model;

/**
   Data on a redefined field.
**/
public class RedefineData
{
    /** Field Id. */
    private int fieldId;
    
    /** Redefined Field Id. */
    private int redefinedFieldId;
    
	/** Redefine Occurrence Number. */
    private int redefineOccurenceNbr;
    
	/**
	   Create a new RedefineData object.
	**/
    public RedefineData(int fieldId, 
                        int redefinedFieldId, 
                        int redefineOccurenceNbr)
    {
        this.fieldId = fieldId;
        this.redefinedFieldId = redefinedFieldId;
        this.redefineOccurenceNbr = redefineOccurenceNbr;
    }
    
	/**
	   Returns the FieldId property.
	   @return the FieldId property.
	**/
    public int getFieldId()
    {
        return fieldId;
    }
    
	/**
	   Returns the RedefinedFieldId property.
	   @return the RedefinedFieldId property.
	**/
    public int getRedefinedFieldId()
    {
        return redefinedFieldId;
    }
    
	/**
	   Returns the RedefineOccurenceNbr property.
	   @return the RedefineOccurenceNbr property.
	**/
    public int getRedefineOccurenceNbr()
    {
        return redefineOccurenceNbr;
    }
}
