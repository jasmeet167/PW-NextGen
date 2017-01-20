package com.csc.fsg.life.tools.copybook.model;

/* Modifications: ENH1091 */

/**
   Data directly from meta data file for a copybook field.
**/
public class CopybookFieldGenInfo
{
    private boolean bypassField = false;
    private boolean generateGroupLevel = false;
    private boolean genGrpLvlBypassField = false;
    private boolean externalGroup = false;
	private String  interfaces = "";
    private boolean treatAsDate = false;
    private boolean treatAsBoolean = false;
    private boolean treatAsBinary = false;
    private String stringValueOfTrue = null;
    private String stringValueOfFalse = null;
    private boolean flatten = false;
    private String aliasName;
    private String redefineCreateTrigger;

    /**
	   Creates a new CopybookFieldGenInfo object.
	**/
    public CopybookFieldGenInfo(String bypassField, 
                                String generateGroupLevel,
                                String genGrpLvlBypassField,
                                String externalGroup, 
								String interfaces,
                                String treatAsDate,  
                                String treatAsBoolean,
                                String treatAsBinary,
                                String stringValueOfTrue,
                                String stringValueOfFalse,
                                String flatten,
                                String aliasName,
                                String redefineCreateTrigger)
    {
        if (bypassField != null
        &&  bypassField.equalsIgnoreCase("true"))
            this.bypassField = true;
        if (generateGroupLevel != null
        &&  generateGroupLevel.equalsIgnoreCase("true"))
           this.generateGroupLevel = true;
        if (genGrpLvlBypassField != null
        &&  genGrpLvlBypassField.equalsIgnoreCase("true"))
            this.genGrpLvlBypassField = true;
        if (externalGroup != null
        &&  externalGroup.equalsIgnoreCase("true"))
            this.externalGroup = true;
		if (interfaces != null)
			this.interfaces = interfaces;
        if (treatAsDate != null
        &&  treatAsDate.equalsIgnoreCase("true"))
            this.treatAsDate = true;
        if (treatAsBoolean != null
        &&  treatAsBoolean.equalsIgnoreCase("true"))
            this.treatAsBoolean = true;
        if (treatAsBinary != null
        &&  treatAsBinary.equalsIgnoreCase("true"))
            this.treatAsBinary = true;
        if (stringValueOfTrue != null && stringValueOfTrue.length() > 0)
        	this.stringValueOfTrue = stringValueOfTrue;
        if (stringValueOfFalse != null && stringValueOfFalse.length() > 0)
        	this.stringValueOfFalse = stringValueOfFalse;
        if (flatten != null
        &&  flatten.equalsIgnoreCase("true"))
            this.flatten = true;
        this.aliasName = aliasName;
        this.redefineCreateTrigger = redefineCreateTrigger;
    }
    
	/**
	   Returns the bypassField property.
	   @return the bypassField property.
	**/
    public boolean isBypassField()
    {
        return bypassField;
    }
    
	/**
	   Returns value of the GenerateGroupLevel flag.
	   @return the value of the GenerateGroupLevel flag.
	**/
    public boolean isGenerateGroupLevel()
    {
        return generateGroupLevel;
    }
    
	/**
	   Returns value of the GenGrpLvlBypassField flag.
	   @return the value of the GenGrpLvlBypassField flag.
	**/
    public boolean isGenGrpLvlBypassField()
    {
        return genGrpLvlBypassField;
    }
    
	/**
	   Returns value of the ExternalGroup flag.
	   @return the value of the ExternalGroup flag.
	**/
    public boolean isExternalGroup()
    {
        return externalGroup;
    }
    
	/**
	   Returns the Interfaces property.
	   @return the Interfaces property.
	**/
	public String getInterfaces()
	{
		return interfaces;
	}

	/**
	   Returns value of the TreatAsDate flag.
	   @return the value of the TreatAsDate flag.
	**/
    public boolean isTreatAsDate()
    {
        return treatAsDate;
    }
    
	/**
	   Returns value of the TreatAsBoolean flag.
	   @return the value of the TreatAsBoolean flag.
	**/
    public boolean isTreatAsBoolean()
    {
        return treatAsBoolean;
    }

    
	/**
	   Returns value of the TreatAsBoolean flag.
	   @return the value of the TreatAsBoolean flag.
	**/
    public boolean isTreatAsBinary()
    {
        return treatAsBinary;
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
	   Returns the AliasName property.
	   @return the AliasName property.
	**/
    public String getAliasName()
    {
        return aliasName;
    }
    
	/**
	   Returns the redefineCreateTrigger property.
	   @return the redefineCreateTrigger property.
	**/
    public String getRedefineCreateTrigger()
    {
        return redefineCreateTrigger;
    }

	/**
	   Returns value of the Flatten flag.
	   @return the value of the Flatten flag.
	**/
    public boolean isFlatten()
    {
        return flatten;
    }

	/**
	   Sets the flatten flag value.
	**/
    public void setFlatten(boolean flatten)
    {
        this.flatten = flatten;
    }

	public String toString()
	{
		StringBuffer buf = new StringBuffer();
		buf.append("\nbypassField: ");
		buf.append(bypassField);
		buf.append("\ngenerateGroupLevel: ");
		buf.append(generateGroupLevel);
		buf.append("\ngenGrpLvlBypassField: ");
		buf.append(genGrpLvlBypassField);
		buf.append("\nexternalGroup: ");
		buf.append(externalGroup);
		buf.append("\ninterfaces: ");
		buf.append(interfaces);
		buf.append("\ntreatAsDate: ");
		buf.append(treatAsDate);
		buf.append("\ntreatAsBoolean: ");
		buf.append(treatAsBoolean);
		buf.append("\ntreatAsBinary: ");
		buf.append(treatAsBinary);
		buf.append("\nstringValueOfTrue: ");
		buf.append(stringValueOfTrue);
		buf.append("\nstringValueOfFalse: ");
		buf.append(stringValueOfFalse);
		buf.append("\nflatten: ");
		buf.append(flatten);
		buf.append("\naliasName: ");
		buf.append(aliasName);
		buf.append("\nredefineCreateTrigger: ");
		buf.append(redefineCreateTrigger);
		return buf.toString();
	}
}
