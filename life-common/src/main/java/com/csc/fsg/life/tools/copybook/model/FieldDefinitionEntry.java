package com.csc.fsg.life.tools.copybook.model;

import java.io.Serializable;

/* Modifications: ENH1019, ENH1091 */

/**
 * The Field Definition Entry class is utilized in order to maintain information 
 * about a field defined in the Copybook.  This information defines all attributes of 
 * a field. The following information is maintained in the object:
 *      Copybook Name - name of the Copybook that the field is defined in
 *      Data Length - length in bytes that the data of the field occupies
 *      Data Scale - number of decimal places utilized by the field
 *      Data Type - type of data defined by the field (Numeric,AlphaNumeric,...)
 *      Field Id - index of the field definition in the copybook
 *      Field Name - name assign to the field in the copybook
 *      Field Type - type of the Field Definition (Element,Group,Occurs,...)
 *      Number of Occurs - number of repetitions of the field
 *      Offset - starting location of data defined by the field
 *      Parent Field Id - index of parent field of this field
 *      Redefines Field Id - index of field being redefine by this field
 *      Depending On Field Id - index of field indicating the number of repetitions
 */
public class FieldDefinitionEntry 
	implements Serializable
{
	private static final long serialVersionUID = 6897997437118873658L;
	private String copybookName = "";
	private int fieldLength = 0;
	private int dataLength = 0;
    private int objectLength = 0;
	private int dataScale = 0;
	private String dataType = "";
	private String stringValueOfTrue = null;
	private String stringValueOfFalse = null;
	private int fieldId = -1;
	private String fieldName = "";
    
	private String fieldType = "";
	/** Field type for elements. **/
    public final static String ElementFieldType = "E";
	/** Field type for groups. **/
    public final static String GroupFieldType = "G";
    
	private int nbrOfOccurs = 1;
	private int offset = 0;
	private int parentFieldId = -1;
	private int redefinesFieldId = -1;
	private int dependingOnFieldId = -1;
	
	private String redefineCreateTrigger = null;

    /** NODATE type of field date type */
    public final static String DateNo = "NOTADATE";
    /** DATE8 type of field date type */
    public final static String Date8 = "DATE8";
    /** DATE10 type of field date type */
    public final static String Date10 = "DATE10";
    
	/**
	 * Empty Constructor for Field Definition Entry Class.
	 *
	 */
	public FieldDefinitionEntry()
	{
	}

	/**
	 * Constructor for Field Definition Entry Class.
	 *
	 */
	public FieldDefinitionEntry(String aCopybookName, 
			                    int aDataLength, 
			                    int aDataScale, 
			                    String aDataType, 
			                    String aStringValueOfTrue, 
			                    String aStringValueOfFalse, 
			                    int aFieldId, 
			                    String aFieldName, 
			                    String aFieldType, 
			                    int aNbrOfOccurs, 
			                    int aOffset, 
			                    int aParentFieldId, 
			                    int aRedefinesFieldId, 
			                    String aRedefineCreateTrigger)
	{

		copybookName = aCopybookName;
		dataLength = aDataLength;
        objectLength = dataLength;
		dataScale = aDataScale;
		dataType = aDataType;
		stringValueOfTrue = aStringValueOfTrue;
		stringValueOfFalse = aStringValueOfFalse;
		fieldId = aFieldId;
		fieldName = aFieldName;
		fieldType = aFieldType;
		nbrOfOccurs = aNbrOfOccurs;
		offset = aOffset;
		parentFieldId = aParentFieldId;
		redefinesFieldId = aRedefinesFieldId;
		redefineCreateTrigger = aRedefineCreateTrigger;
	}

	/**
	   Hash based on field id - should be unique for each field in a copybook.
	   If a map needs to be created across copybooks this could be changed
	   to use copybookname + field id.
	**/
	public int hashCode()
	{
		return fieldId;
	}
	
	/**
	 * Method t create a String representation of the Field Definition Entry
	 *
	 * @return (String) - returns the a String value representing the Field Definition Entry
	 */
	public String toString()
	{
		StringBuffer sb = new StringBuffer();

		sb.append(copybookName + "\t");
		sb.append(fieldId + "\t");
		sb.append(fieldName + "\t");

		sb.append(fieldType + "\t");
		sb.append(fieldLength + "\t");
        sb.append(objectLength + "\t");
		sb.append(dataLength + "\t");
		sb.append(dataScale + "\t");
		sb.append(dataType + "\t");
		sb.append((stringValueOfTrue == null ? "null" : stringValueOfTrue) + "\t");
		sb.append((stringValueOfFalse == null ? "null" : stringValueOfFalse) + "\t");

		sb.append(nbrOfOccurs + "\t");
		sb.append(offset + "\t");
		sb.append(parentFieldId + "\t");
		sb.append(redefinesFieldId + "\t");
		sb.append(redefineCreateTrigger + "\t");

		// sb.append(dependingOnFieldId +"\t" );
		return sb.toString();
	}

	/**
	 * Access method for the name of the copybook of the Field Definition.
	 *
	 * @return (String) - returns the copybook name data of the copybookName property
	 */
	public String getCopybookName()
	{
		return copybookName;
	}

	/**
	 * Access method for the data length of the Field Definition.
	 *
	 * @return (int) - returns the data length of the Field Definition
	 */
	public int getDataLength()
	{
		return dataLength;
	}

	/**
	   Returns the FieldLength property.
	   @return the FieldLength property.
	**/
	public int getFieldLength()
	{
		return fieldLength;
	}
	
	/**
	   Returns the ObjectLength property.
	   @return the ObjectLength property.
	**/
    public int getObjectLength()
    {
        return objectLength;
    }
    
	/**
	 * Access method for the data scale of the Field Definition.
	 *
	 * @return (int) - returns the data scale of the Field Definition
	 */
	public int getDataScale()
	{
		return dataScale;
	}

	/**
	   Is this field signed.
	**/
	public boolean isSigned()
	{
		return dataType.startsWith("S");
	}
	
	/**
	 * Access method for the data type of the Field Definition.
	 *
	 * @return (int) - returns the data type of the Field Definition
	 */
	public String getDataType()
	{
		return dataType;
	}

	/**
	   Returns the StringValueOfTrue property.
	   @return the StringValueOfTrue property.
	**/
	public String getStringValueOfTrue()
	{
		return stringValueOfTrue;
	}

	/**
	   Returns the StringValueOfFalse property.
	   @return the StringValueOfFalse property.
	**/
	public String getStringValueOfFalse()
	{
		return stringValueOfFalse;
	}

	/**
	 * Access method for the field id of the Field Definition.
	 *
	 * @return (int) - returns the field id of the Field Definition
	 */
	public int getFieldId()
	{
		return fieldId;
	}

	/**
	 * Access method for the field name of the Field Definition.
	 *
	 * @return (int) - returns the field name of the Field Definition
	 */
	public String getFieldName()
	{
		return fieldName;
	}

	/**
	   Returns a unique field name for this field.  The name itself is not
	   necessarily unique, but coupled with the field it it is.
	**/
	public String getUniqueFieldName()
	{
		if (fieldType.equals("FG") || fieldType.equals("FE"))
			return fieldName + "_" + fieldId;
		else
			return fieldName;
	}
	
	/**
	 * Access method for the field type of the Field Definition.
	 *
	 * @return (int) - returns the field type of the Field Definition
	 */
	public String getFieldType()
	{
		return fieldType;
	}

	/**
	   Determines if this is a field/group is a Date
	**/
    public String getDateType()
    {
 	    String dateType = DateNo;
        if ((getFieldName().indexOf("-DATE") != -1
             || getFieldName().indexOf("DATE-") != -1
             || getFieldName().indexOf("-DTE") != -1
             || getFieldName().indexOf("DTE-") != -1
             || getFieldName().indexOf("BIRTHDATE") != -1
             || getFieldName().indexOf("-DT") != -1)
        && (getFieldName().indexOf("-TIME") == -1
        	 || getFieldName().indexOf("TIME-") == -1))
        	dateType = getDateTypeByLength();
        
        return dateType;
    }
    
    /**
     Determines date type based on length
     **/
    public String getDateTypeByLength()
    {
        if (getDataLength() == 8)
	        return Date8;
        else if (getDataLength() == 10)
        	return Date10;
        
        return DateNo; 
    }
    
	/**
	   Determins if this is a group or not based upon the field type.
	**/
	public boolean isGroup()
	{
		return (fieldType.equals("G") 
             || fieldType.equals("OG") 
             || fieldType.equals("FG")
             || fieldType.equals("FOG")
             || fieldType.equals("OE")
             || fieldType.equals("FOE"));
	}
	
	/**
	   Determins if this is an occurs group or not based upon the field type.
	**/
	public boolean isOccursGroup()
	{
		return (fieldType.equals("OG")
             || fieldType.equals("FOG")
             || fieldType.equals("OE")
             || fieldType.equals("FOE"));
	}
	
	/**
	 * Access method for the number of occurrences of the Field Definition.
	 *
	 * @return (int) - returns the number of occurrences of the Field Definition
	 */
	public int getNbrOfOccurs()
	{
		return nbrOfOccurs;
	}

	/**
	 * Access method for the offset value of the Field Definition.
	 *
	 * @return (int) - returns the offset value of the Field Definition
	 */
	public int getOffset()
	{
		return offset;
	}

	/**
	 * Access method for the parent field id of the Field Definition.
	 *
	 * @return (int) - returns the parent field id of the Field Definition
	 */
	public int getParentFieldId()
	{
		return parentFieldId;
	}

	/**
	 * Access method for the field id being redefined by the Field Definition.
	 *
	 * @return (int) - returns the field id being redefined by the Field Definition
	 */
	public int getRedefinesFieldId()
	{
		return redefinesFieldId;
	}

	/**
	 * Access method for the code which will trigger/drive field id being redefined by the Field Definition.
	 *
	 * @return (String) - returns the field id being redefined by the Field Definition
	 */
	public String getRedefineCreateTrigger()
	{
		return redefineCreateTrigger;
	}

	/**
	 * Access method for the field id dictating number of occurrences in the Field Definition.
	 *
	 * @return (int) - returns the field id being specifying the number of occurrences 
	 *                 of the Field in Field Definition
	 */
	public int getDependingOnFieldId()
	{
		return dependingOnFieldId;
	}

	/**
	 * Method to set the data value of copybookName property in the Field Definition.
	 *
	 * @param aCopybookName - (String) new data value of copybookName in Field Definition object
	 */
	public void setCopybookName(String aCopybookName)
	{
		copybookName = aCopybookName;
	}

	/**
	 * Method to set the data value of dataLength property in the Field Definition.
	 *
	 * @param aDataLength - (int) new data value of dataLength in Field Definition object
	 */
	public void setDataLength(int aDataLength)
	{
		dataLength = aDataLength;
	}

	/**
	   Sets the field length.
	*/
	public void setFieldLength(int aFieldLength)
	{
		fieldLength = aFieldLength;
	}
	
	/**
	   Sets the object length.
	**/
    public void setObjectLength(int aObjectLength)
    {
        objectLength = aObjectLength;
    }

	/**
	 * Method to set the data value of dataScale property in the Field Definition.
	 *
	 * @param aDataScale - (int) new data value of dataScale in Field Definition object
	 */
	public void setDataScale(int aDataScale)
	{
		dataScale = aDataScale;
	}

	/**
	 * Method to set the data value of dataType property in the Field Definition.
	 *
	 * @param aDataType - (String) new data value of dataType in Field Definition object
	 */
	public void setDataType(String aDataType)
	{
		dataType = aDataType;
	}

	/**
	   Sets the StringValueOfTrue property.
	   @param aStringValueOfTrue the StringValueOfTrue property.
	**/
	public void setStringValueOfTrue(String aStringValueOfTrue)
	{
		stringValueOfTrue = aStringValueOfTrue;
	}

	/**
	   Sets the StringValueOfFalse property.
	   @param aStringValueOfFalse the StringValueOfFalse property.
	**/
	public void setStringValueOfFalse(String aStringValueOfFalse)
	{
		stringValueOfFalse = aStringValueOfFalse;
	}

	/**
	 * Method to set the data value of fieldId property in the Field Definition.
	 *
	 * @param aFieldId - (int) new data value of fieldId in Field Definition object
	 */
	public void setFieldId(int aFieldId)
	{
		fieldId = aFieldId;
	}

	/**
	 * Method to set the data value of fieldName property in the Field Definition.
	 *
	 * @param aFieldName - (String) new data value of fieldName in Field Definition object
	 */
	public void setFieldName(String aFieldName)
	{
		fieldName = aFieldName;
	}

	/**
	 * Method to set the data value of fieldType property in the Field Definition.
	 *
	 * @param aFieldType - (String) new data value of fieldType in Field Definition object
	 */
	public void setFieldType(String aFieldType)
	{
		fieldType = aFieldType;
	}

	/**
	 * Method to set the data value of nbrOfOccurs property in the Field Definition.
	 *
	 * @param aNbrOfOccurs - (int) new data value of nbrOfOccurs in Field Definition object
	 */
	public void setNbrOfOccurs(int aNbrOfOccurs)
	{
		nbrOfOccurs = aNbrOfOccurs;
	}

	/**
	 * Method to set the data value for Offset property in the Field Definition.
	 *
	 * @param aOffset - (int) new data value of Offset in Field Definition object.
	 */
	public void setOffset(int aOffset)
	{
		offset = aOffset;
	}

	/**
	 * Method to set the data value of parentFieldId property in the Field Definition.
	 *
	 * @param aParentFieldId - (int) new data value of parentFieldId in Field Definition object
	 */
	public void setParentFieldId(int aParentFieldId)
	{
		parentFieldId = aParentFieldId;
	}

	/**
	 * Method to set the data value of redefinesFieldId property in the Field Definition.
	 *
	 * @param aRedefinesFieldId - (int) new data value of redefinesFieldId in Field Definition object
	 */
	public void setRedefinesFieldId(int aRedefinesFieldId)
	{
		redefinesFieldId = aRedefinesFieldId;
	}

	/**
	 * Method to set the data value of redefineCreateTrigger property in the Field Definition.
	 *
	 * @param aRedefineCreateTrigger - (String) new data value of redefineCreateTrigger in Field Definition object
	 */
	public void setRedefineCreateTrigger(String aRedefineCreateTrigger)
	{
		redefineCreateTrigger = aRedefineCreateTrigger;
	}

	/**
	 * Method to set the data value of dependingOnFieldId property in the Field Definition.
	 *
	 * @param aDependingOnFieldId - (int) new data value of dependingOnFieldId in Field Definition object
	 */
	public void setDependingOnFieldId(int aDependingOnFieldId)
	{
		dependingOnFieldId = aDependingOnFieldId;
	}

    public Object clone()
    {
        FieldDefinitionEntry fde = new FieldDefinitionEntry();
        
        fde.setCopybookName(copybookName);
        fde.setDataLength(dataLength);
        fde.setDataScale(dataScale);
        fde.setDataType(dataType);
        fde.setStringValueOfTrue(stringValueOfTrue);
        fde.setStringValueOfFalse(stringValueOfFalse);
        fde.setDependingOnFieldId(dependingOnFieldId);
        fde.setFieldId(fieldId);
        fde.setFieldLength(fieldLength);
        fde.setFieldName(fieldName);
        fde.setFieldType(fieldType);
        fde.setNbrOfOccurs(nbrOfOccurs);
        fde.setOffset(offset);
        fde.setParentFieldId(parentFieldId);
        fde.setRedefinesFieldId(redefinesFieldId);
        fde.setRedefineCreateTrigger(redefineCreateTrigger);
        
        return fde;
    }
}
