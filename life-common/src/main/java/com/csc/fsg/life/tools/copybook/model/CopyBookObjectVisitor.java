package com.csc.fsg.life.tools.copybook.model;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import com.csc.fsg.life.tools.FieldMetaData;

/* Modifications: T0140, T0150, WMA-1559, ENH1019, ENH1091 */

/**
 * Visits a copybook to build model information combining data from three sources:
 * 
 * <UL>
 * <LI>Copybook interpreter data.</LI>
 * <LI>Copy object meta data.</LI>
 * <LI>Meta Data transfer file.</LI>
 * </UL>
 */
public class CopyBookObjectVisitor
    extends CopyBookVisitor
{
    private String eventName;
    private String copyObjectPackage;
    private Map<String, CopybookFieldGenInfo> fieldGenInfosMap;
    private Map<String, FieldMetaData> metadataEventInfosMap;
    private Map<String, GlobalFieldGenInfo> globalFieldGenInfosMap;
    
    /** Parent id for root copybook fields. */
    public static final int ROOT = -1;

    
    /**
     * Visits all fields in a copybook to build model for copy object.
	 */
    public CopyBookObjectVisitor(CopyBookMapModel fieldMap, 
                                 String copybookName,
                                 String eventName,
                                 String copyObjectPackage,
                                 Map<String, CopybookFieldGenInfo> fieldGenInfosMap,
                                 Map<String, FieldMetaData> metadataEventInfosMap,
                                 Map<String, GlobalFieldGenInfo> globalFieldGenInfosMap)
    {
        super(fieldMap);
        this.eventName = eventName;
        this.copyObjectPackage = copyObjectPackage;
        this.fieldGenInfosMap = fieldGenInfosMap;
        this.metadataEventInfosMap = metadataEventInfosMap;
        this.globalFieldGenInfosMap = globalFieldGenInfosMap;
    }

    /**
     * Visit a group to build a complex type.
     */
    protected void visit(FieldDefinitionEntry parent, Object userObject)
    {
        VisitorData data = (VisitorData)userObject;
        
        // put an entry in the object map for the ROOT copybook fields
        if (parent == null) {
            String className = eventName;
            int classLength = fieldMap.getCopybookLength();
            int offsetStart = 0;
            Integer objectKey = Integer.valueOf(ROOT);
            data.objectMap.put(objectKey, new ClassData(copyObjectPackage, 
            		                                    className, 
            		                                    classLength, 
            		                                    offsetStart,
            		                                    ""));
        } else {
            String fullQualName = fieldMap.getFullQualName(parent);
            CopybookFieldGenInfo cbGenInfo = fieldGenInfosMap.get(fullQualName);
            if (cbGenInfo != null
            && (cbGenInfo.isBypassField()
            ||  cbGenInfo.isGenGrpLvlBypassField()))
                return;
        }
        
        super.visit(parent, userObject);
    }

    /**
     * Visit a group to create a reference to the type before building its
     * children's complex types.
     */
    @SuppressWarnings("unchecked")
	protected void visitGroup(FieldDefinitionEntry group, Object userObject)
    {
        VisitorData data = (VisitorData)userObject;
        
        String fullQualName = fieldMap.getFullQualName(group);
        CopybookFieldGenInfo cbGenInfo = fieldGenInfosMap.get(fullQualName);
        
        ClassData parent = getFieldClassData(data, group);
                
        // create a class for any group with an occurs > 1
        if (group.isGroup() 
        &&  group.getNbrOfOccurs() > 1) {
            if (cbGenInfo != null && cbGenInfo.isFlatten()) {
            	// flatten the occurs group to a simple group of fields
                List children = new ArrayList();
                
                // if the group is an "Occurs Element", it means that the occurs and the
                // field definition are on the same line, so there are no children
                if (group.getFieldType().equals("OE"))
                	children.add(group);
                else
                	children = fieldMap.getChildFields(group.getFieldId());
                
                // add a field for each element in occurs group
                int offset = 0;
                for (int i = 1; i <= group.getNbrOfOccurs(); i++) {
                    Iterator itt = children.iterator();
                    while (itt.hasNext()) {
                    	Object obj = itt.next();
                    	if (obj instanceof List) {
                    		List<FieldDefinitionEntry> redefineList = (List<FieldDefinitionEntry>)obj;
                    		Iterator<FieldDefinitionEntry> redefineItt = redefineList.iterator();
                    		while (redefineItt.hasNext()) {
                    			FieldDefinitionEntry fde = (FieldDefinitionEntry)redefineItt.next();
                    			
    	                        FieldDefinitionEntry newFde = (FieldDefinitionEntry)fde.clone();
    	                        if (offset == 0)
    	                            offset = fde.getOffset();
    	                        
    	                        newFde.setFieldName(fde.getFieldName() + i);
    	                        newFde.setOffset(offset);
    	                        offset = offset + fde.getDataLength();
    	                        
    	                        // check for any metadata for the field that is being cloned
    	                        String fieldFullQualName = fieldMap.getFullQualName(fde);
    	                        CopybookFieldGenInfo fieldGenInfo = 
    	                            fieldGenInfosMap.get(fieldFullQualName);
    	                        
    	                        // if there is metadata, add an entry for the newly cloned field
    	                        if (fieldGenInfo != null) {
    	                            String newFieldFullQualName = fieldMap.getFullQualName(newFde);
    	                            fieldGenInfosMap.put(newFieldFullQualName, fieldGenInfo);
    	                        }
    	                        
    	                        addField(parent, newFde);
                    		}
                    	} else {
	                        FieldDefinitionEntry fde = (FieldDefinitionEntry)obj;
	
	                        FieldDefinitionEntry newFde = (FieldDefinitionEntry)fde.clone();
	                        if (offset == 0)
	                            offset = fde.getOffset();
	                        
	                        newFde.setFieldName(fde.getFieldName() + i);
	                        newFde.setOffset(offset);
	                        offset = offset + fde.getDataLength();
	                        
	                        // check for any metadata for the field that is being cloned
	                        String fieldFullQualName = fieldMap.getFullQualName(fde);
	                        CopybookFieldGenInfo fieldGenInfo = 
	                            fieldGenInfosMap.get(fieldFullQualName);
	                        
	                        // if there is metadata, add an entry for the newly cloned field
	                        if (fieldGenInfo != null) {
	                            String newFieldFullQualName = fieldMap.getFullQualName(newFde);
	                            fieldGenInfosMap.put(newFieldFullQualName, fieldGenInfo);
	                        }
	                        
	                        addField(parent, newFde);
                    	}
                    }
                }
                
                // remove the occurs group element
                fieldMap.removeFieldFromChildFieldMap(group.getFieldId());
            } else {
            
                // Occurs/List processing
                // add occurs group field to class
				FieldData fd = addField(parent, group, FieldData.OccursGroup);
                
                String className = getClassName(group);
                int classLength = group.getDataLength();
                int offsetStart = group.getOffset();
                Integer objectKey = Integer.valueOf(group.getFieldId());
				ClassData fieldCD = 
					new ClassData(copyObjectPackage, 
							      className, 
							      classLength, 
							      offsetStart,
							      group.getRedefineCreateTrigger(),
							      ClassData.OccursClass, 
							      parent);

				if (cbGenInfo != null && cbGenInfo.getInterfaces() != null)
					fieldCD.setCustomInterfaces(cbGenInfo.getInterfaces());
				
				data.objectMap.put(objectKey, fieldCD);

				// Set the class data for the occurring class on this field.
				if (fd != null)
					fd.setFieldClass(fieldCD);
                
                if (group.getFieldType().equals("OE")) {
                    ClassData cd = (ClassData)data.objectMap.get(objectKey);
                    // add occurs field entry to occurs class
                    addField(cd, group);
                }
            }
        }
        // create a class for any group with an external group indicator
        else if (group.isGroup() 
				 &&  cbGenInfo != null
				 &&  cbGenInfo.isExternalGroup()) 
		{
			// add external group field to class
			FieldData fd = addField(parent, group, FieldData.ExternalGroup);
				
			String className = getClassName(group);
			int classLength = group.getDataLength();
			int offsetStart = group.getOffset();
			Integer objectKey = Integer.valueOf(group.getFieldId());
			//build class to support external group
			ClassData cd = 
				new ClassData(copyObjectPackage, 
							  className, 
							  classLength, 
							  offsetStart,
						      group.getRedefineCreateTrigger(), 
							  ClassData.ExternalClass, 
							  parent);

			if (cbGenInfo.getInterfaces() != null)
				cd.setCustomInterfaces(cbGenInfo.getInterfaces());

			data.objectMap.put(objectKey, cd);
			
			// Set the class data for the external class on this field.
			if (fd != null)
				fd.setFieldClass(cd);
        }
        else if (group.isGroup()) {
            List rlist = fieldMap.getRedefinedFieldInfo(group.getFieldId());
            if (rlist != null
             && group.getDataType().equals("ALPHANUM")) {
                // add external group field to class
                addField(parent, group, FieldData.RedefinesGroup);
            }
        }            
        
        // if this is a date group or a treat as date group, don't visit group
        // just create a date field
        String dateType = group.getDateType();
        if (dateType.equals(FieldDefinitionEntry.DateNo)
        && (cbGenInfo == null ||
        	(cbGenInfo != null &&
            !cbGenInfo.isTreatAsDate()))) {
                if (cbGenInfo != null
                &&  cbGenInfo.isGenerateGroupLevel()) {
                    addField(parent, group);
                }
        	    super.visitGroup(group, userObject);
            }
        else
        	addField(parent, group);
    }
    
    /**
     * Visit a field.
     */
    protected void visitField(FieldDefinitionEntry field, Object userObject)
    {
        VisitorData data = (VisitorData)userObject;
        Integer objectKey = Integer.valueOf(field.getParentFieldId());
        
        // if the field redefines a date, then don't create a field or group
       	if (field.getRedefinesFieldId() != -1) {
       		FieldDefinitionEntry redefinedField = fieldMap.getFieldDefinitionEntry(field.getRedefinesFieldId());
            String redefinedFullQualName = fieldMap.getFullQualName(redefinedField);
            CopybookFieldGenInfo redefinedCbGenInfo = fieldGenInfosMap.get(redefinedFullQualName);
       		if (!(redefinedField.getDateType()).equals(FieldDefinitionEntry.DateNo)
       		|| (redefinedCbGenInfo != null &&
                redefinedCbGenInfo.isTreatAsDate()))
       			return;
       	}
       	
        ClassData cd = getFieldClassData(data, field);
        if (cd != null) {
            addField(cd, field);
        } 
        else  {
            // if the field has an occurs > 1, we need to create a class
            if (field.getNbrOfOccurs() > 1) {
                ClassData parent = getFieldClassData(data, field);
                String className = getClassName(field);
                int classLength = field.getDataLength();
                int offsetStart = field.getOffset();
                ClassData classData = 
					new ClassData(copyObjectPackage, 
							      className, 
							      classLength, 
							      offsetStart,
							      field.getRedefineCreateTrigger(), 
							      ClassData.OccursClass, 
							      parent);

                FieldData fd = addField(classData, field);

                data.objectMap.put(Integer.valueOf(field.getFieldId()), classData);

				// Set the class data for the occurring class on this field.
				if (fd != null)
					fd.setFieldClass(classData);
            }
            else {
                // this field's parent is NOT a collection, so add its field
                // to the ROOT class
                objectKey = Integer.valueOf(ROOT);
                cd = (ClassData)data.objectMap.get(objectKey);
                addField(cd, field);
            }
        }
    }

    /**
     * Add a new field to the specified class.
	 */
    public FieldData addField(ClassData cd, FieldDefinitionEntry field)
    {
        return addField(cd, field, FieldData.FieldGroup);
    }
      
    /**
     * Add a new field to the specified class.
	 */
    public FieldData addField(ClassData cd, FieldDefinitionEntry field, String groupType)
    {
        String fieldName;
        String fullQualName = fieldMap.getFullQualName(field);
        CopybookFieldGenInfo cbGenInfo = fieldGenInfosMap.get(fullQualName);
        if (cbGenInfo != null && cbGenInfo.isBypassField())  {
            if (field.getRedefinesFieldId() != -1)
                return null;
            else
                fieldName = "filler";
        }
        else if (cbGenInfo != null && cbGenInfo.isGenGrpLvlBypassField()) {
            return null;
		}
        else if (cbGenInfo != null
				 &&  cbGenInfo.getAliasName() != null
				 &&  cbGenInfo.getAliasName().length() > 0) {
            fieldName = cbGenInfo.getAliasName();
		}
        else {
            fieldName = getFieldName(field);
		}
        
        // convert COBOL type to java type
        String javaDataType = getJavaDataType(field, cbGenInfo);
        
        boolean isRedefined = false;
        String redefinesFieldName = "";
    	int nbrOfRedefinitions = 0;
    	int redefineOccurrenceNbr = 0;
       	// populate redefining Information
       	List<RedefineData> rlist = fieldMap.removeRedefinedFieldFromRedFieldInfoMap(field.getFieldId());
       	if (rlist != null) {
           	cd.setHasRedefines();
           	isRedefined = true;
           	RedefineData rd = rlist.get(rlist.size() - 1);;
           	FieldDefinitionEntry redefinedField = fieldMap.getFieldDefinitionEntry(rd.getRedefinedFieldId());
       		redefinesFieldName = getFieldName(redefinedField);
       		nbrOfRedefinitions = rd.getRedefineOccurenceNbr();
       		redefineOccurrenceNbr = 1;
           	
            // add the redefines structure to the class
            for(Iterator<RedefineData> rlistIter = rlist.iterator(); rlistIter.hasNext();) {
            	RedefineData redefiningField = rlistIter.next();
            	appendRedefiningField(cd, redefinesFieldName, redefiningField);
            }
        }
        else {
           	RedefineData rd = fieldMap.getRedefiningFieldInfo(field.getFieldId());
           	if (rd != null) {
               	FieldDefinitionEntry redefinedField = fieldMap.getFieldDefinitionEntry(rd.getRedefinedFieldId());
           	    redefinesFieldName = getFieldName(redefinedField);
               	redefineOccurrenceNbr = rd.getRedefineOccurenceNbr();
           	}
        }
        
       	// append eventName to any fields representing occurs or external groups
       	if (groupType.equalsIgnoreCase(FieldData.OccursGroup) 
         || groupType.equalsIgnoreCase((FieldData.ExternalGroup))) {
       		fieldName = lowercaseFirstChar(cd.getName()) + capitalizeFirstChar(fieldName);
        }
       	
        // process global metadata for field (if any)
        String justification = null;
        String defaultValue = null;
        GlobalFieldGenInfo globalFieldGenInfo = getGlobalFieldGenInfo(fullQualName);
        if (globalFieldGenInfo != null) {
            justification = globalFieldGenInfo.getJustification();
            defaultValue = globalFieldGenInfo.getDefaultValue();
        }
        
       	// process metadata for field
        String displayDatatype = null;
       	boolean isRequired = false;
       	String avref = "";
       	FieldMetaData mdEventFieldInfo = getMDFieldGenInfo(cd, fieldName);
       	if (mdEventFieldInfo != null) {
       		displayDatatype = mdEventFieldInfo.getDatatype();
       		isRequired = mdEventFieldInfo.isRequired();
       		avref = mdEventFieldInfo.getAvref();
       	}
       		
        FieldData fd = createFieldDataInstance(fullQualName,
                                               fieldName, 
                                               javaDataType,
                                               field.getStringValueOfTrue(),
                                               field.getStringValueOfFalse(),                                     
                                               field.getDataType(),
                                               field.getDataLength(), 
                                               field.getOffset() - cd.getOffsetStart(), 
                                               field.getDataScale(),
                                               field.getFieldLength(),
                                               field.getFieldId(),
                                               field.getNbrOfOccurs(),
                                               isRedefined, 
                                               redefinesFieldName,
                                               nbrOfRedefinitions,
                                               redefineOccurrenceNbr,
                                               groupType,
                                               displayDatatype,
                                               isRequired,
                                               avref,
                                               justification,
                                               defaultValue,
                                               cd,
                                               field.getRedefineCreateTrigger());
        cd.appendField(fd);

		return fd;
    }
    
    /**
     * Adds a field entry to the redefining field list of the class data
     */
    private void appendRedefiningField(ClassData cd, 
                                       String redefinesFieldName,
                                       RedefineData rd) 
    {
        FieldDefinitionEntry field = fieldMap.getFieldDefinitionEntry(rd.getFieldId());
        String fieldName;
        String fullQualName = fieldMap.getFullQualName(field);
        CopybookFieldGenInfo cbGenInfo = fieldGenInfosMap.get(fullQualName);
        if (cbGenInfo != null
        &&  cbGenInfo.getAliasName() != null
        &&  cbGenInfo.getAliasName().length() > 0)
            fieldName = cbGenInfo.getAliasName();
        else 
            fieldName = getFieldName(field);
        
        // convert COBOL type to java type
        String javaDataType = getJavaDataType(field, cbGenInfo);
        
        // process global metadata for field (if any)
        String justification = null;
        String defaultValue = null;
        GlobalFieldGenInfo globalFieldGenInfo = getGlobalFieldGenInfo(fullQualName);
        if (globalFieldGenInfo != null) {
            justification = globalFieldGenInfo.getJustification();
            defaultValue = globalFieldGenInfo.getDefaultValue();
        }
        
       	// process metadata for field
        String displayDatatype = null;
       	boolean isRequired = false;
       	String avref = "";
       	FieldMetaData mdEventFieldInfo = getMDFieldGenInfo(cd, fieldName);
       	if (mdEventFieldInfo != null) {
       		displayDatatype = mdEventFieldInfo.getDatatype();
       		isRequired = mdEventFieldInfo.isRequired();
       		avref = mdEventFieldInfo.getAvref();
       	}
       	       		
        FieldData fd = createFieldDataInstance(fullQualName,
                                               fieldName, 
                                               javaDataType,
                                               field.getStringValueOfTrue(),
                                               field.getStringValueOfFalse(),
                                               field.getDataType(),
                                               field.getDataLength(), 
                                               field.getOffset() - cd.getOffsetStart(), 
                                               field.getDataScale(),
                                               field.getFieldLength(),
                                               field.getFieldId(),
                                               field.getNbrOfOccurs(),
                                               false, 
                                               redefinesFieldName, 
                                               0,
                                               rd.getRedefineOccurenceNbr(),
                                               FieldData.FieldGroup,
                                               displayDatatype,
                                               isRequired,
                                               avref,
                                               defaultValue,
                                               justification,
                                               cd,
                                               field.getRedefineCreateTrigger());
        cd.appendRedefiningField(fd);
    }
    
	/**
	 * This method is intended to be overridden, if a specialized subclass of
	 * {@code FieldData} is required
	 */
	protected FieldData createFieldDataInstance(String fullQualName,
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
		return new FieldData(fullQualName,
							 name,
							 javaType,
							 stringValueOfTrue,
							 stringValueOfFalse,
							 cobolType,
							 length,
							 offset,
							 scale,
							 fieldLength,
							 fieldId,
							 numberOfOccurs,
							 isRedefined,
							 redefinesFieldName,
							 nbrOfRedefinitions,
							 redefineOccurrenceNbr,
							 groupType,
							 displayDatatype,
							 isRequired,
							 avref,
							 justification,
							 defaultValue,
							 parentClassData,
							 redefineCreateTrigger);
	}

    /**
     *  Convert COBOL data type into Java type.
     */
    public String getJavaDataType(FieldDefinitionEntry field, 
    							  CopybookFieldGenInfo cbGenInfo)
    {
        // capture date type info
        String dateType = FieldDefinitionEntry.DateNo;
        if (cbGenInfo != null
        &&  cbGenInfo.isTreatAsDate())
        	dateType = field.getDateTypeByLength();
        else
        	dateType = field.getDateType();
        
        if (!dateType.equals(FieldDefinitionEntry.DateNo))
        	return dateType;
        
        // is this a treat as boolean field
        if (cbGenInfo != null
        &&  cbGenInfo.isTreatAsBoolean())
        	return "boolean";
        
        // is this should be treated as binary
        if (cbGenInfo != null 
         &&  cbGenInfo.isTreatAsBinary())
        	return "byte[]";
        
        String dataType = null;
        if (field.getDataType().equalsIgnoreCase("ALPHANUM") 
        		|| field.getDataType().equalsIgnoreCase("POINTER"))
            dataType = "String";
        else if (field.getDataType().equalsIgnoreCase("NUMERIC")
        	 ||  field.getDataType().equalsIgnoreCase("SNUMERIC")
        	 ||  field.getDataType().equalsIgnoreCase("SNUMTRAL")
             ||  field.getDataType().equalsIgnoreCase("SPACKED")
        	 ||  field.getDataType().equalsIgnoreCase("PACKED")
             ||  field.getDataType().equalsIgnoreCase("BINARY")
             ||  field.getDataType().equalsIgnoreCase("SBINARY")) {
            if (field.getDataScale() > 0)
                dataType = "Double";
            else            
                dataType = "Long";
        } 
        else
            dataType = field.getDataType();
        
        return dataType;
    }
    
    /**
     * Data carried on stack while visiting a copybook.
     */
    static public class VisitorData
    {
        /** parentFieldId -> ClassData */
        public Map<Integer,ClassData> objectMap = new TreeMap<Integer,ClassData>();
    }
    
    /**
     * Builds a class name off of a copybook field.  On null, uses the 
     * product and event name.
     * @param field The FieldDefinitionEntry to get a class name for.
     * @return the class name.
	 */
    public String getClassName(FieldDefinitionEntry field)
    {
        if (field == null)
            return eventName;

        String fullQualName = fieldMap.getFullQualName(field);
        
        if (fieldGenInfosMap.containsKey(fullQualName)) {
            CopybookFieldGenInfo cbGenInfo = fieldGenInfosMap.get(fullQualName);
            if (cbGenInfo != null
            &&  cbGenInfo.getAliasName() != null
            &&  cbGenInfo.getAliasName().length() > 0) {
                String aliasName = cbGenInfo.getAliasName();
                return capitalizeFirstChar(aliasName);
            }
        }
        
        return capitalizeFirstChar(getFieldName(field));
    }
    
    /**
     * Builds a field name off of a copybook field.
     * @param field The FieldDefinitionEntry to get a name for.
     * @return the field name.
     */
    public String getFieldName(FieldDefinitionEntry field)
    {
        String s = field.getFieldName().toLowerCase();
        s = s.replace('-', ' ');
        StringBuffer sb = new StringBuffer();
        
        // make sure first character is a valid starting identifier
        char c = s.charAt(0);
        if (!Character.isJavaIdentifierStart(c))
            sb.append("_");
        sb.append(c);
        
        boolean forceNextToUpper = false;
        for (int i=1; i<s.length(); i++) {
            c = s.charAt(i);
            if (Character.isJavaIdentifierPart(c)) {
                sb.append( forceNextToUpper ? Character.toUpperCase(c) : c);
                forceNextToUpper = false;
            } else {
                forceNextToUpper = true;
            }
        }
        
        // ensure that second character is lowercase to keep the JSF gods happy
        char secondChar = sb.charAt(1);
        if (Character.isUpperCase(secondChar))
        	sb.setCharAt(1, Character.toLowerCase(secondChar));
        
        return sb.toString();
    }
    
    /**
     * Helper method to make the first character of the given string
     * upper case.
     */
    protected String capitalizeFirstChar(String s)
    {
        StringBuffer sb = new StringBuffer(s);
        if (sb.length() > 0) {
            sb.setCharAt(0, Character.toUpperCase(sb.charAt(0)) );
        }
        return sb.toString();
    }

    /**
     * Helper method to make the first character of the given string
     * lower case.
     */
    protected String lowercaseFirstChar(String s)
    {
        StringBuffer sb = new StringBuffer(s);
        if (sb.length() > 0) {
            sb.setCharAt(0, Character.toLowerCase(sb.charAt(0)) );
        }
        return sb.toString();
    }
    
    private ClassData getFieldClassData(VisitorData data, FieldDefinitionEntry field) {
        Integer classKey = Integer.valueOf(field.getFieldId());
        ClassData cd = (ClassData)data.objectMap.get(classKey);
        if (cd == null) {
            if (field.getParentFieldId() != field.getFieldId()) {
                FieldDefinitionEntry parentField = fieldMap.getFieldDefinitionEntry(field.getParentFieldId());
                return getFieldClassData(data, parentField);
            }
            else {
                classKey = Integer.valueOf(ROOT);
                cd = (ClassData)data.objectMap.get(classKey);
                return cd;
            }
        }
        return cd;
    }
    
    private FieldMetaData getMDFieldGenInfo(ClassData cd, String fieldName)
	{
    	String parentStructure = cd.getParentClassStructure(cd.getName());
        String eventFieldQualName = cd.getPkg() + "." + parentStructure + "." + fieldName;
        FieldMetaData mdEventFieldInfo = (FieldMetaData)metadataEventInfosMap.get(eventFieldQualName.toLowerCase());
        if (mdEventFieldInfo == null) {
           	eventFieldQualName = copyObjectPackage + eventFieldQualName;
           	mdEventFieldInfo = (FieldMetaData)metadataEventInfosMap.get(eventFieldQualName.toLowerCase());
           	if (mdEventFieldInfo == null) {
           	    return null;
           	}
        }
        return mdEventFieldInfo;
    }
    
    private GlobalFieldGenInfo getGlobalFieldGenInfo(String fullQualName)
    {
        String fieldLeaf = null;
        if (fullQualName.lastIndexOf(".") > 0)
            fieldLeaf = fullQualName.substring(fullQualName.lastIndexOf(".")+1);
        else
            fieldLeaf = fullQualName;
        GlobalFieldGenInfo result = (GlobalFieldGenInfo)globalFieldGenInfosMap.get(fieldLeaf);
        if (result != null) {
            if (result.getJustification() != null && result.getJustification().trim().length() > 0)
                System.out.println("    Justification for " + fullQualName + " set to " + result.getJustification());
            if (result.getDefaultValue() != null && result.getDefaultValue().trim().length() > 0)
                System.out.println("    Default value for " + fullQualName + " set to " + result.getDefaultValue());
        }
        
        return result;
    }
}
