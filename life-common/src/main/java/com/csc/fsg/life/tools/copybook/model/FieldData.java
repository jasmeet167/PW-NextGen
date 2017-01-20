package com.csc.fsg.life.tools.copybook.model;

/* Modifications: T0175, WMA-1559, ENH1091 */

/**
 * Information regarding a field. Date from combined from copybook interpreter
 * and meta data file.
 */
public class FieldData
{
    /** field fullQualName */
    private String fullQualName;
    
    /** field name */
    private String name;
    
    /** field type */
    private String javaType;
    
    /** for boolean type, string constant to be used when field value == true */
    private String stringValueOfTrue;
    
    /** for boolean type, string constant to be used when field value == false */
    private String stringValueOfFalse;    
    
    /** COBOL type */
    private String cobolType;
    
    /** field cobol length */
    private int length;
    
    /** field offset */
    private int offset;

    /** field scale */
    private int scale;
    
    /** field length */
    private int fieldLength;
    
    /** field id */
    private int fieldId;
    
    /** number of occurs specified */
    private int numberOfOccurs;
    
    /** is redefined by another field/group */
    private boolean isRedefined = false;
    
    /** redefines field name */
    private String redefinesFieldName = "";
    
    /** number of redefines */
    private int nbrOfRedefinitions = 0;
    
    /** redefine Occurrence Number */
    private int redefineOccurrenceNbr = 0;
    
    private String redefineCreateTrigger = "";
    
    /** Constant for Field type of field. */
    public final static String FieldGroup = "FIELD";
    /** Constant for occurs type of field. */
    public final static String OccursGroup = "OCCURS";
    /** Constant for external group type of field. */
    public final static String ExternalGroup = "EXTERNAL";
    /** Constant for redefines type of field. */
    public final static String RedefinesGroup = "REDEFINES";
    
    /** Field group type Field/Occurs/External/Redefines */
    private String groupType;
   
    /** The datatype used for display or formatting (currency, percent, etc) */
    private String displayDatatype;
    
    /** is field required */
    private boolean isRequired = false;
    
    /** Allowable values reference. */
    private String avref = "";

    /** Field justification.  Left or right or none. */
    private String justification;

    /** Constant for field justification of NONE. */
    public final static String JUSTIFICATION_NONE = "none";
	/** Constant for field justification of RIGHT. */
    public final static String JUSTIFICATION_RIGHT = "right";
	/** Constant for field justification of LEFT. */
    public final static String JUSTIFICATION_LEFT = "left";
    
    /** default value */
    private String defaultValue;
    
    /** the ClassData object that this field belongs to */
    private ClassData parentClassData = null;
    
	/** If this represents an external class or an occurring class, the class data for this field. */
	private ClassData fieldClassData = null;


	/**
	 * Builds a field data object.
	 */
    public FieldData(String fullQualName,
                     String name, 
                     String javaType, 
                     String stringValueOfTrue, 
                     String stringValueOfFalse, 
                     String cobolType, 
                     int length, 
                     int offset, 
                     int scale, 
                     int fieldLength, 
                     int fieldId,
                     int numberOfOccurs,
                     boolean isRedefined,
                     String redefinesFieldName,
                     int nbrOfRedefinitions,
                     int redefineOccurrenceNbr,
                     String groupType,
                     String displayDatatype,
                     boolean isRequired,
                     String avref,
                     String justification,
                     String defaultValue,
                     ClassData parentClassData,
                     String redefineCreateTrigger)
    {
        this.fullQualName = fullQualName;
        this.name = name;
        this.javaType = javaType;
        this.stringValueOfTrue = stringValueOfTrue;
        this.stringValueOfFalse = stringValueOfFalse;
        this.cobolType = cobolType;
        this.length = length;
        this.offset = offset;
        this.scale = scale;
        this.fieldLength = fieldLength;
        this.fieldId = fieldId;
        this.numberOfOccurs = numberOfOccurs;
        this.isRedefined = isRedefined;
        this.redefinesFieldName = redefinesFieldName;
        this.nbrOfRedefinitions = nbrOfRedefinitions;
        this.redefineOccurrenceNbr = redefineOccurrenceNbr;
        this.groupType = groupType;
        this.displayDatatype = displayDatatype;
        this.isRequired = isRequired;
        this.avref = avref;
        this.justification = justification;
        setDefaultValue(defaultValue);
        this.parentClassData = parentClassData;
        this.redefineCreateTrigger = redefineCreateTrigger;
    }
    
	/**
	   Returns the FullQualName property.
	   @return the FullQualName property.
	**/
    public String getFullQualName()
    {
        return fullQualName;
    }
    
	/**
	   Returns the Name property.
	   @return the Name property.
	   @see #setName
	**/
    public String getName()
    {
		return name;
	}

	/**
	   Returns the BaseName for this field.
	   @return the BaseName for this field.
	**/
	public String getBaseName()
	{
		// If this isn't a external or occurring class, just return the name.
		if (fieldClassData == null)
			return name;
		
		if (fieldClassData.getSuperClass() != null)
			return fieldClassData.getSuperClass();
		else
			return fieldClassData.getName();
    }
    
	/**
	   Sets the Name property.
	   @param name the Name property.
	   @see #getName
	**/
    public void setName(String name)
    {
        this.name = name;
    }
    
	/**
	   Returns the Length property.
	   @return the Length property.
	**/
    public int getLength()
    {
        return length;
    }
    
	/**
	   Returns the Offset property.
	   @return the Offset property.
	**/
    public int getOffset()
    {
        return offset;
    }
    
	/**
	   Returns the EndOffset property.
	   @return the EndOffset property.
	**/
    public int getEndOffset()
    {
        return offset + length;
    }
    
	/**
	   Returns the JavaType property.
	   @return the JavaType property.
	**/
    public String getJavaType()
    {
    	if (javaType.equals(FieldDefinitionEntry.Date8)
    	||  javaType.equals(FieldDefinitionEntry.Date10))
    		return "Date";
    	
        return javaType;
    }
    
	/**
	   Returns the IsLong property.
	   @return the IsLong property.
	**/
    public boolean getIsLong()
    {
        if (javaType.equals("Long"))
            return true;
        return false;
    }
    
	/**
	   Returns the IsString property.
	   @return the IsString property.
	**/
    public boolean getIsString()
    {
        if (javaType.equals("String"))
            return true;
        return false;
    }
 
	/**
	   Returns the IsDouble property.
	   @return the IsDouble property.
	**/
    public boolean getIsDouble()
    {
        if (javaType.equals("Double"))
            return true;
        return false;
    }
    
	/**
	   Returns the IsBoolean property.
	   @return the IsBoolean property.
	**/
    public boolean getIsBoolean()
    {
        if (javaType.equals("boolean"))
            return true;
        return false;
    }

	/**
	   Returns the IsBinary property.
	   @return the IsBinary property.
	**/
	public boolean getIsBinary()
	{
		if (javaType.equals("byte[]"))
			return true;
	    return false;
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
	   Returns the CobolType property.
	   @return the CobolType property.
	**/
    public String getCobolType()
    {
        return cobolType;
    }
    
	/**
	   Returns the Scale property.
	   @return the Scale property.
	**/
    public int getScale()
    {
        return scale;
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
 		Returns the FieldId property.
 		@return the FieldId property.
	 **/
    public int getFieldId()
    {
    	return fieldId;
    }

	/**
	   Returns the PicSize property.
	   @return the PicSize property.
	**/
    public String getPicSize()
    {
    	if (scale > 0)
    		return (fieldLength - scale) + "." + scale;
    	
        if (getIsDate8())
            return "8";
        
        if (getIsDate10())
            return "10";
        
        // for groups, pass back cobol length which is alphanumeric length
        if ((groupType.equals(FieldGroup)
        ||   groupType.equals(OccursGroup)
        ||   groupType.equals(ExternalGroup)
        ||   groupType.equals(RedefinesGroup))
        &&  fieldLength == 0)
            return String.valueOf(length);
        return String.valueOf(fieldLength);
    }
    
	/**
	   Returns the NumberOfOccurs property.
	   @return the NumberOfOccurs property.
	**/
    public int getNumberOfOccurs()
    {
        return numberOfOccurs;
    }
    
	/**
	   Returns the IsRedefined property.
	   @return the IsRedefined property.
	**/
    public boolean getIsRedefined()
    {
        return isRedefined;
    }
    
	/**
	   Returns the RedefinesFieldName property.
	   @return the RedefinesFieldName property.
	**/
    public String getRedefinesFieldName()
    {
        return redefinesFieldName;
    }
    
	/**
	   Clears the RedefinesFieldName property.
	   @see #getRedefinesFieldName
	**/
    public void clearRedefinesFieldName()
    {
        redefinesFieldName = "";
    }
    
	/**
	   Returns the IsRequired property.
	   @return the IsRequired property.
	**/
    public boolean getIsRequired()
    {
        return isRequired;
    }
    
	/**
	   Returns the Avref property.
	   @return the Avref property.
	**/
    public String getAvref()
    {
        return avref;
    }
    
	/**
	   Returns the NbrOfRedefinitions property.
	   @return the NbrOfRedefinitions property.
	**/
    public int getNbrOfRedefinitions()
    {
        return nbrOfRedefinitions;
    }
    
	/**
	   Returns the RedefineOccurrenceNbr property.
	   @return the RedefineOccurrenceNbr property.
	**/
    public int getRedefineOccurrenceNbr()
    {
        return redefineOccurrenceNbr;
    }
    
	/**
	   Returns the IsRedefinesGroup property.
	   @return the IsRedefinesGroup property.
	**/
    public boolean getIsRedefinesGroup()
    {
        if (groupType.equals(RedefinesGroup))
            return true;
        
        return false;
    }
    
	/**
	   Returns the redefineCreateTrigger property.
	   @return the redefineCreateTrigger property.
	**/
    public String getRedefineCreateTrigger()
    {
        return redefineCreateTrigger.replaceAll("doublequotereplace", "\"");
    }
    
	/**
	   Returns boolean value indicating if the redefines is used
	   and supporting code needs to be generated.
	**/
    public boolean getGenerateRedefinesSupport() 
    {
    	boolean generateRedefinesSupport = false;
    	
    	if (parentClassData != null
    	 && parentClassData.getRedefineCreateTrigger() != null
    	 && parentClassData.getRedefineCreateTrigger().trim().length() > 0)
    		generateRedefinesSupport = true;
    	
    	if (redefineCreateTrigger != null
    	 && redefineCreateTrigger.trim().length() > 0)
    		generateRedefinesSupport = true;
    	
    	return generateRedefinesSupport;
    		
    }
    
	/**
	   Returns the IsNotRedefinesGroup property.
	   @return the IsNotRedefinesGroup property.
	**/
    public boolean getIsNotRedefinesGroup()
    {
        if (!groupType.equals(RedefinesGroup))
            return true;
        return false;
    }
    
	/**
	   Returns the IsFieldGroup property.
	   @return the IsFieldGroup property.
	**/
    public boolean getIsFieldGroup()
    {
        if (groupType.equals(FieldGroup))
            return true;
        return false;
    }
    
	/**
	   Returns the IsOccursGroup property.
	   @return the IsOccursGroup property.
	**/
    public boolean getIsOccursGroup()
    {
        if (groupType.equals(OccursGroup))
            return true;
        return false;
    }
    
	/**
	   Returns the IsNotOccursGroup property.
	   @return the IsNotOccursGroup property.
	**/
    public boolean getIsNotOccursGroup()
    {
        if (!groupType.equals(OccursGroup))
            return true;
        return false;
    }
    
	/**
	   Returns the IsExternalGroup property.
	   @return the IsExternalGroup property.
	**/
    public boolean getIsExternalGroup()
    {
        if (groupType.equals(ExternalGroup))
            return true;
        return false;
    }
    
	/**
	   Returns the IsNotExternalGroup property.
	   @return the IsNotExternalGroup property.
	**/
    public boolean getIsNotExternalGroup()
    {
        if (!groupType.equals(ExternalGroup))
            return true;
        return false;
    }

	/**
	   Returns the IsDate8 property.
	   @return the IsDate8 property.
	**/
    public boolean getIsDate8()
    {
        if (javaType.equals(FieldDefinitionEntry.Date8))
            return true;
        return false;
    }

	/**
	   Returns the IsDate10 property.
	   @return the IsDate10 property.
	**/
    public boolean getIsDate10()
    {
        if (javaType.equals(FieldDefinitionEntry.Date10))
            return true;
        return false;
    }

	/**
	   Returns the IsDate property.
	   @return the IsDate property.
	**/
    public boolean getIsDate()
    {
        return (getIsDate8() || getIsDate10());
    }
    
    /**
     * Used to determine if the given field has the same 
     * characteristics as this one.
     */
	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof FieldData))
			return false;
		
		FieldData fd = (FieldData)obj;
		if (!fd.getName().equalsIgnoreCase(getName()))
			return false;
		if (!fd.getJavaType().equalsIgnoreCase(getJavaType()))
			return false;
		if (fd.getLength() != getLength())
			return false;
		return true;
	}

	@Override
	public int hashCode()
	{
		StringBuilder buf = new StringBuilder();
		buf.append(getName());
		buf.append(getJavaType());
		buf.append(getLength());
		return buf.toString().hashCode();
	}

    /**
     * Used to determine if the given field has the same 
     * characteristics as this one.
     */
	public boolean equalsBase(Object obj) 
	{
		FieldData fd = (FieldData)obj;
		if (!fd.getBaseName().equalsIgnoreCase(getBaseName()))
			return false;
		if ( (getFieldClass() == null) && 
			 !fd.getJavaType().equalsIgnoreCase(getJavaType()) )
			return false;
		if (fd.getLength() != getLength())
			return false;
		return true;
	}

	/**
	   Returns the GroupType property.
	   @return the GroupType property.
	   @see #setGroupType
	**/
    public String getGroupType()
    {
        return groupType;
    }

	/**
	   Sets the GroupType property.
	   @param groupType the GroupType property.
	   @see #getGroupType
	**/
    public void setGroupType(String groupType)
    {
        this.groupType = groupType;
    }

	public void setDisplayDatatype(String displayDatatype)
	{
		this.displayDatatype = displayDatatype;
	}

	public String getDisplayDatatype()
	{
		return displayDatatype;
	}
	
	/**
	   Returns the ParentClassData property.
	   @return the ParentClassData property.
	   @see #setParentClassData
	**/
    public ClassData getParentClassData()
    {
        return parentClassData;
    }

	/**
	   Sets the ParentClassData property.
	   @param parentClassData the ParentClassData property.
	   @see #setParentClassData
	**/
    public void setParentClassData(ClassData parentClassData)
    {
        this.parentClassData = parentClassData;
    }

	/**
	   Returns the FieldClass property.
	   @return the FieldClass property.
	   @see #setFieldClass
	**/
	public ClassData getFieldClass()
	{
		return fieldClassData;
	}

	/**
	   Sets the FieldClass property.
	   @param fieldClass the FieldClass property.
	   @see #getFieldClass
	**/
	public void setFieldClass(ClassData fieldClass)
	{
		fieldClassData = fieldClass;
	}
    
	/**
	   Returns the CopybookOffset property.
	   @return the CopybookOffset property.
	**/
    public int getCopybookOffset()
    {
        if (parentClassData != null)
            return parentClassData.getOffsetStart() + offset;
        else
            return offset;
    }

	/**
	   Returns the Justification property.
	   @return the Justification property.
	   @see #setJustification
	**/
    public String getJustification()
    {
        return justification;
    }

	/**
	   Sets the Justification property.
	   @param justification the Justification property.
	   @see #getJustification
	**/
    public void setJustification(String justification)
    {
        this.justification = justification;
    }
    
	/**
	   Returns the rightJustified property.
	   @return the rightJustified property.
	**/
    public boolean isRightJustified()
    {
        if (justification != null && justification.equalsIgnoreCase(JUSTIFICATION_RIGHT))
            return true;
        return false;
    }
    
	/**
	   Returns the leftJustified property.
	   @return the leftJustified property.
	**/
    public boolean isLeftJustified()
    {
        if (justification != null && justification.equalsIgnoreCase(JUSTIFICATION_LEFT))
            return true;
        return false;
    }

	/**
	   Returns the DefaultValue property.
	   @return the DefaultValue property.
	   @see #setDefaultValue
	**/
    public String getDefaultValue()
    {
        return defaultValue;
    }

	/**
	   Sets the DefaultValue property.  If the default value
       has a length that is greater than the field's length,
       truncate it.
       
	   @param defaultValue the DefaultValue property.
	   @see #getDefaultValue
	**/
    public void setDefaultValue(String defaultValue)
    {
        int len = fieldLength;
        if (len == 0)
            len = length;
        
        if (defaultValue != null && defaultValue.length() > len)
            this.defaultValue = defaultValue.substring(0, len);
        else
            this.defaultValue = defaultValue;
    }
    
	/**
	   Returns the DefaultValue property.
	   @return the DefaultValue property.
	**/
    public boolean hasDefaultValue()
    {
        return defaultValue != null && defaultValue.trim().length() > 0;
    }

	@Override
	public String toString()
	{
		StringBuffer buf = new StringBuffer();
		buf.append("name: ");
		buf.append(name);
		return buf.toString();
	}
}