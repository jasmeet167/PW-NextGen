package com.csc.fsg.life.tools.copybook.model;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/* Modifications: ENH1091 */

/**
   Holds information about a class to be generated from a copybook.
**/
public class ClassData
{
    /* Class name */
    private String name;
    
    /* Class length */
    private int length;
    
    /* Class offsetStart */
    private int offsetStart;
    
    /* type of class types */
    public final static String BaseClass = "BASE";
    public final static String OccursClass = "OCCURS";
    public final static String ExternalClass = "EXTERNAL";
    /* field class type */
    private String classType;
    
    /* Class hasRedefines */
    private boolean hasRedefines = false;
    
    /* Package this class belongs to */
    private String pkg;
    
    /* Parent ClassData */
    private ClassData parentClassData;

	/** Children class data */
	private List<ClassData> children = new ArrayList<ClassData>();
    
    /* collection of fields */
    private List<FieldData> fields = new ArrayList<FieldData>();
    
	private String superClass;

    /* collection of redefining field information */
    private List<FieldData> redefiningFields = new ArrayList<FieldData>();

	/* collection of additional interfaces for this class */
	private String customInterfaces = null;

	/* data triggering redefine logic */
	private String redefineCreateTrigger = "";


    public ClassData(String pkg,
    				 String name)
	{
		this.pkg = pkg;
		this.name = name;
		this.classType = BaseClass;
	}
    
    public ClassData(String pkg,
    				 String name, 
                     int length, 
                     int offsetStart, 
                     String redefineCreateTrigger)
    {
    	this.pkg = pkg;
		this.name = name;
        this.length = length;
        this.offsetStart = offsetStart;
        this.redefineCreateTrigger = redefineCreateTrigger;
        this.classType = BaseClass;
    }
    
    public ClassData(String pkg,
    				 String name, 
                     int length, 
                     int offsetStart, 
                     String redefineCreateTrigger,
                     String classType,
                     ClassData parentClassData)
    {
    	this.pkg = pkg;
        this.name = name;
        this.length = length;
        this.offsetStart = offsetStart;
        this.redefineCreateTrigger = redefineCreateTrigger;
        this.classType = classType;
        this.parentClassData = parentClassData;
		
		parentClassData.addChild(this);
    }

	/**
	   Returns the Children property.
	   @return the Children property.
	**/
	public List<ClassData> getChildren()
	{
		return children;
	}

	/**
	   Add a child class to this class object.
	   @param cd The child.
	**/
	public void addChild(ClassData cd)
	{
		children.add(cd);
	}

	/**
	   Returns the SuperClass property.
	   @return the SuperClass property.
	   @see #setSuperClass
	**/
	public String getSuperClass()
	{
		return superClass;
	}

	/**
	   Sets the SuperClass property.
	   @param s the SuperClass property.
	   @see #getSuperClass
	**/
	public void setSuperClass(String s)
	{
		superClass = s;
	}

    /**
	   Append a field to this class.
	   @param field The field to add.
	**/
    public void appendField(FieldData field)
    {
        fields.add(field);
    }

	/**
	   Returns the NameLeaf property.  Just this class name, not including any context.
	   @return the NameLeaf property.
	**/
	public String getNameLeaf()
	{
		return name;
	}
    
	/**
	   Returns the Name property.  This is the fully qualified name of this class.
	   @return the Name property.
	**/
    public String getName()
    {
		if (parentClassData != null)
			return parentClassData.getName() + name;
		else
			return name;
    }
    
	/**
	   Returns the StringLength property.
	   @return the StringLength property.
	**/
    public String getStringLength()
    {
        return String.valueOf(length);
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
	   Returns the OffsetStart property.
	   @return the OffsetStart property.
	**/
    public int getOffsetStart()
    {
        return offsetStart;
    }
    
	/**
	   Returns the IsBaseClass property.
	   @return the IsBaseClass property.
	**/
    public String getIsBaseClass()
    {
        if (classType.equals(BaseClass))
            return "true";
        return "false";
    }
    
	/**
	   Returns the IsOccursClass property.
	   @return the IsOccursClass property.
	**/
    public String getIsOccursClass()
    {
        if (classType.equals(OccursClass))
            return "true";
        return "false";
    }
    
	/**
	   Returns the IsExternalClass property.
	   @return the IsExternalClass property.
	**/
    public String getIsExternalClass()
    {
        if (classType.equals(ExternalClass))
            return "true";
        return "false";
    }
    
	/**
	   Returns the HasRedefines property.
	   @return the HasRedefines property.
	**/
    public String getHasRedefines()
    {
        if (hasRedefines)
            return "true";
        return "false";
    }

    /**
	   Set this class flag to indicate it contains a redefine.
	**/
    public void setHasRedefines()
    {
        hasRedefines = true;
    }
    
	/**
	   Returns the Fields property.
	   @return the Fields property.
	   @see #setFields
	**/
    public List<FieldData> getFields()
    {
        return fields;
    }
    
	/**
	   Sets the Fields property.
	   @param fields the Fields property.
	   @see #getFields
	**/
    public void setFields(List<FieldData> fields)
    {
    	this.fields = fields;
    }

    /**
	   Append a redefined field.
	   @param field The field.
	**/
    public void appendRedefiningField(FieldData field)
    {
        redefiningFields.add(field);
    }
    
    public List<FieldData> getRedefiningFields()
    {/*
        //remove all fields from redefiningFields in which there is 
        //only 1 redefinition of the redefined field
        for (int i=0; i<fields.size(); i++) {
            FieldData field = (FieldData) fields.get(i);
            if (field.getNbrOfRedefinitions() == 1) {
                //remove entrys from each redefiningField that redefines this field
                int i2=redefiningFields.size();
                while (i2>0) {
                    i2--;
                    FieldData redefiningField = (FieldData)redefiningFields.get(i2);
                    if (redefiningField.getRedefinesFieldName().equals(field.getName()))
                        redefiningFields.remove(i2);
                }
                //remove redefinesFieldName from each Field that redefine this field
                for (int i3=0; i3<fields.size(); i3++) {
                    FieldData redefiningField = (FieldData)fields.get(i3);
                    if (redefiningField.getRedefinesFieldName().equals(field.getName()))
                        redefiningField.clearRedefinesFieldName();
                }
            }
        } */
        return redefiningFields;
    }

	/**
	   Returns the Pkg property.
	   @return the Pkg property.
	   @see #setPkg
	**/
	public String getPkg() {
		return pkg;
	}

	/**
	   Sets the Pkg property.
	   @param pkg the Pkg property.
	   @see #getPkg
	**/
	public void setPkg(String pkg) {
		this.pkg = pkg;
	}

	/**
	   Returns the ClassType property.
	   @return the ClassType property.
	   @see #setClassType
	**/
	public String getClassType() {
		return classType;
	}

	/**
	   Sets the ClassType property.
	   @param classType the ClassType property.
	   @see #getClassType
	**/
	public void setClassType(String classType) {
		this.classType = classType;
	}
    
	/**
	   Returns the ParentClassStructure property.
	   @return the ParentClassStructure property.
	**/
    public String getParentClassStructure(String parentClassDataString) {
        if (parentClassData == null)
            return parentClassDataString;
        
        parentClassDataString = parentClassData.getName() + "." + parentClassDataString;
        return parentClassData.getParentClassStructure(parentClassDataString);
    }

	/**
	   Returns the BaseName property.
	   @return the BaseName property.
	**/
	public String getBaseName(String baseEventName)
	{ 
		return getBaseName(baseEventName, false);
	}

	/**
	   Returns the BaseName property.
	   @return the BaseName property.
	**/
	public String getBaseName(String baseEventName, boolean baseAlreadyAdded)
	{
		if (isRoot())
		{
			if (baseAlreadyAdded)
				return baseEventName;
			else
				return "Base" + baseEventName;
		}
		else
		{
			return getParent().getBaseName(baseEventName, true) + "Base" + getNameLeaf();
		}
	}

	/**
	   Returns the Parent property.
	   @return the Parent property.
	**/
	public ClassData getParent()
	{
		return parentClassData;
	}

	/**
	   Returns the root flag.  True if this is the root class for the copybook object.
	   @return the root property.
	**/
	public boolean isRoot()
	{
		return (parentClassData == null);
	}

	/** Collection of additional interfaces for this class */
	public String getCustomInterfaces()
	{
		return customInterfaces;
	}
	
	public String getRedefineCreateTrigger()
	{
		return redefineCreateTrigger;
	}    
    /**
     * Returns a list of interfaces this class implements.
     */
    public List<String> getCustomInterfacesList()
    {
        List<String> interfaces = new ArrayList<String>();
        if (customInterfaces != null) {   
            StringTokenizer st = new StringTokenizer(customInterfaces, ",");
            while (st.hasMoreTokens()) {
                String token = st.nextToken();
                interfaces.add(token.trim());
            }
        }
        return interfaces;
    }

	public void setCustomInterfaces(String val)
	{
		if ((val == null) ||
			(val.trim().length() == 0))
			customInterfaces = null;
		else
			customInterfaces = val;
	}

	public String toString()
	{
		StringBuffer buf = new StringBuffer();
		buf.append("\nname: ");
		buf.append(getName());
		buf.append("\nnameLeaf: ");
		buf.append(getNameLeaf());

		buf.append("\nParent Class: " );
		if (parentClassData != null)
			buf.append(parentClassData.getName());

		buf.append("\nChildren: " );
		for(ClassData cd : children) {
			buf.append("   ");
			buf.append(cd.getName());
		}

		buf.append("\nclassType: ");
		buf.append(classType);
		buf.append("\ncustomInterfaces: ");
		buf.append(customInterfaces);

		return buf.toString();
	}

}
