package com.csc.fsg.life.tools.copybook.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

/* Modifications: T0140, ENH1091 */

/**
   Model built and used when generating copy objects.
**/
public class CopyBookMapModel
{
    private FieldDefinitionEntry[] fields;
	private Map<Integer,List<FieldDefinitionEntry>> childFieldMap;
	private Map<Integer,List<RedefineData>> redefinedFieldInfoMap;
	private Map<Integer,RedefineData> redefiningFieldInfoMap;
    private Map<Integer,List<FieldDefinitionEntry>> fieldGenInfosMap;
 
	/** Parent id for root copybook fields. **/
	public static final int ROOT = -1;
	
	/** 
		Create a new instance from a set of fields. 
	**/
	public CopyBookMapModel(FieldDefinitionEntry[] fields)
	{   
		this(fields, new HashMap<Integer,List<FieldDefinitionEntry>>());
	}

	/**
	   Builds a map of field-id -> list of children
       and a map of field-id -> list of fieldDefinitionEntry
	**/
	public CopyBookMapModel(FieldDefinitionEntry[] fields,
                            Map<Integer,List<FieldDefinitionEntry>> fieldGenInfosMap)
	{   
        this.fields = fields;
        this.fieldGenInfosMap = fieldGenInfosMap;
        
		// Children Field map is a map of a field-id -> list of children.
		childFieldMap = new HashMap<Integer,List<FieldDefinitionEntry>>(fields.length * 2);
		for(int i=0; i<fields.length; i++) {
			FieldDefinitionEntry field = fields[i];
            
			int parentId = field.getParentFieldId();
			
			// Add to it's parent in the field map.
			if (field.getFieldId() == parentId)
				addField(ROOT, field);
			else
				addField(parentId, field);
		}

		// Check for re-define groups in the children
		// and group them as lists.
		Set<Map.Entry<Integer,List<FieldDefinitionEntry>>> fieldSet = childFieldMap.entrySet();
		Map<Integer,List<FieldDefinitionEntry>> fixedFieldMap = new HashMap<Integer,List<FieldDefinitionEntry>>(fields.length * 2);
		for(Iterator<Map.Entry<Integer,List<FieldDefinitionEntry>>> itt = fieldSet.iterator(); itt.hasNext();) {
			Map.Entry<Integer,List<FieldDefinitionEntry>> nextEntry = (Map.Entry<Integer,List<FieldDefinitionEntry>>)itt.next();
			List<FieldDefinitionEntry> children = nextEntry.getValue();

			// Group redefine fields into a new list.
			children = groupRedefines(children);

			// Substitute that list in the map.
			fixedFieldMap.put(nextEntry.getKey(), children);
		}
		childFieldMap = fixedFieldMap;
        buildRedefineInfo();
	}

	/**
     * returns all direct children for a Group
	 **/
	public List<FieldDefinitionEntry> getChildFields(int key)
	{
		return childFieldMap.get(Integer.valueOf(key));
	}
	
	/**
     * returns redefined information for a field, structure of redefining information
     * key is Field Id of Field being redefined, Data is list of RedefineData for
     * fields redefining original field
	 **/
	public List<RedefineData> getRedefinedFieldInfo(int key)
	{
		return redefinedFieldInfoMap.get(Integer.valueOf(key));
	}
	
	/**
     * removes the redefining information for a field key from the Redefined Field Info map
	 **/
	public List<RedefineData> removeRedefinedFieldFromRedFieldInfoMap(int key)
	{
		return redefinedFieldInfoMap.remove(Integer.valueOf(key));
	}
    
    /**
     * removes the child field information for a field key from the child Field Info map
     **/
    public List<FieldDefinitionEntry> removeFieldFromChildFieldMap(int key)
    {
        return childFieldMap.remove(Integer.valueOf(key));
    }
    
	/**
     * returns redefining information, information about fields which redefine other fields
     * Key is field id of redefining field, Data is RedefineData
	 **/
	public RedefineData getRedefiningFieldInfo(int key)
	{
		return redefiningFieldInfoMap.get(Integer.valueOf(key));
	}
	
    /**
       Builds a fully qualified field name off of a copybook information.
       @param field The FieldDefinitionEntry to get a fully qualified name for.
	   @return The fully qualified name or the empty string if this is the root field.
    **/
     public String getFullQualName(FieldDefinitionEntry field)
    {
        if (field.getParentFieldId() == ROOT)
            return "";
        
        String fullName = "";
        if (field.getFieldId() != field.getParentFieldId()) {
            FieldDefinitionEntry parentfield = (FieldDefinitionEntry)fields[field.getParentFieldId()];
            if (parentfield != null)
                fullName = getFullQualName(parentfield) + ".";
        }
        
        return fullName + field.getFieldName().toUpperCase();
     }
        
	/**
	   adds a single field to the field map.
	   @param parentId The parent id to add it to.
	   @param field The field to add.
	**/
	private void addField(int parentId,
						  FieldDefinitionEntry field)
	{
		Integer key = Integer.valueOf(parentId);
		List<FieldDefinitionEntry> children = childFieldMap.get(key);
		if (children == null) {
			children = new ArrayList<FieldDefinitionEntry>();
			childFieldMap.put(key, children);
		}
		children.add(field);
	}

	/**
	   This turns a list of fields, into a list of fields
	   but grouped as re-defines.
	**/
	@SuppressWarnings("unchecked")
	private List<FieldDefinitionEntry> groupRedefines(List<FieldDefinitionEntry> fields)
	{
		// 1. The first pass builds a map of
		//    field-id -> List of fields that redefine it.
		Map<Integer,List<FieldDefinitionEntry>> redefineMap = new HashMap<Integer,List<FieldDefinitionEntry>>();
		for(Iterator<FieldDefinitionEntry> itt = fields.iterator(); itt.hasNext();) {
			// Get the next field.
			FieldDefinitionEntry nextField = (FieldDefinitionEntry)itt.next();

            // If this field isn't redefined, continue.
            int redefinesId = nextField.getRedefinesFieldId();
            if (redefinesId < 1)
                continue;

            // If this field or group is to be bypassed don't build redefined info
            String nextFieldFullQualName = getFullQualName(nextField);
            CopybookFieldGenInfo nextFieldCbGenInfo = (CopybookFieldGenInfo)fieldGenInfosMap.get(nextFieldFullQualName);
            if (nextFieldCbGenInfo != null
            && (nextFieldCbGenInfo.isBypassField()
            ||  nextFieldCbGenInfo.isGenGrpLvlBypassField()))
                continue;
                    
            // Populate nextField with applicable values from CopybookFieldGenInfo
            if (nextFieldCbGenInfo != null) {
            	nextField.setStringValueOfTrue(nextFieldCbGenInfo.getStringValueOfTrue());
            	nextField.setStringValueOfFalse(nextFieldCbGenInfo.getStringValueOfFalse());
            	nextField.setRedefineCreateTrigger(nextFieldCbGenInfo.getRedefineCreateTrigger());
            }

            // Else it is a redefine so build a list for it
			// if necessary and add the field to that list.
			Integer key = Integer.valueOf(redefinesId);
			List<FieldDefinitionEntry> rList = redefineMap.get(key);
			if (rList == null) {
				rList = new ArrayList<FieldDefinitionEntry>();
				redefineMap.put(key, rList);
			}
			rList.add(nextField);
		}

		// 2. The second pass builds the rebuilt list.
		//    adding the original fields to their re-define lists
		List rebuiltList = new ArrayList();
		for(Iterator<FieldDefinitionEntry> itt = fields.iterator(); itt.hasNext();) {
			// Get the next field.
			FieldDefinitionEntry nextField = (FieldDefinitionEntry)itt.next();

			// If this field isn't redefined ...
			int redefinesId = nextField.getRedefinesFieldId();
			if (redefinesId < 1) {
	            String nextFieldFullQualName = getFullQualName(nextField);
	            CopybookFieldGenInfo nextFieldCbGenInfo = (CopybookFieldGenInfo)fieldGenInfosMap.get(nextFieldFullQualName);

	            // Populate nextField with applicable values from CopybookFieldGenInfo
	            if (nextFieldCbGenInfo != null) {
	            	nextField.setStringValueOfTrue(nextFieldCbGenInfo.getStringValueOfTrue());
	            	nextField.setStringValueOfFalse(nextFieldCbGenInfo.getStringValueOfFalse());
	            	nextField.setRedefineCreateTrigger(nextFieldCbGenInfo.getRedefineCreateTrigger());
	            }

	            // Check to see if it has other fields that
				// re-define it.
				Integer key = Integer.valueOf(nextField.getFieldId());
				List rList = redefineMap.get(key);

				// If there isn't a re-define list for this field
				// then add it to the re-built list as a single field.
				if (rList == null) {
					rebuiltList.add(nextField);
				}
				// Otherwise, there is a re-define list for this field
				// so add it to that list and add the list to the re-built
				// list.
				else {
					rList.add(0, nextField);
					rebuiltList.add(rList);
				}
			}
		}

		return rebuiltList;
	}

	/**
	   This build redefine information for each redefined field
	**/
	private void buildRedefineInfo()
	{
		redefinedFieldInfoMap = new HashMap<Integer,List<RedefineData>>();
        redefiningFieldInfoMap = new HashMap<Integer,RedefineData>();
		for (int i=0; i<fields.length; i++) {
			// Get the next field.
			FieldDefinitionEntry nextField = fields[i];

			// If this field isn't a redefines, continue.
			int redefinesFieldId = nextField.getRedefinesFieldId();
			if (redefinesFieldId < 1)
				continue;
            
			// If this redefined field or group is a Date don't build redefined info
            FieldDefinitionEntry redefinedField = (FieldDefinitionEntry)fields[nextField.getRedefinesFieldId()];
	        String redefinedFullQualName = getFullQualName(redefinedField);
	        CopybookFieldGenInfo redefinedCbGenInfo = (CopybookFieldGenInfo)fieldGenInfosMap.get(redefinedFullQualName);
            if (!(redefinedField.getDateType()).equals(FieldDefinitionEntry.DateNo)
            || (redefinedCbGenInfo != null &&
                redefinedCbGenInfo.isTreatAsDate()))
                continue;
            
            // If this field or group is to be bypassed don't build redefined info
            if (redefinedCbGenInfo != null
            && (redefinedCbGenInfo.isBypassField()
            ||  redefinedCbGenInfo.isGenGrpLvlBypassField()))
                continue;
                    
			// Else it is a redefine so build a list for it
			// and add all the redefined fields children to Map with 
            // redefine occurrence of 1.
			Integer key = Integer.valueOf(redefinesFieldId);
			List<RedefineData> rList = redefinedFieldInfoMap.get(key);
			if (rList == null) {
				rList = new ArrayList<RedefineData>();
				redefinedFieldInfoMap.put(key, rList);
                visitRedefineGroup(rList,
                                   redefinesFieldId,
                                   redefinesFieldId, 1);
			}
            
            // Add entries for each child of the redefining Field
            RedefineData rd = rList.get(rList.size() - 1);
            int redefineOccurenceNbr = rd.getRedefineOccurenceNbr() + 1;
			visitRedefineGroup(rList,
                               redefinesFieldId, 
                               nextField.getFieldId(), 
                               redefineOccurenceNbr);
		}
    }
    
    /** 
     * Visits all children based on a Field Id and adds a RedefinedData entry 
     * to the list with the supplied redefine occurrence number
     **/
    private void visitRedefineGroup(List<RedefineData> rList, 
                                    int redefinedFieldId,
                                    int fieldId, 
                                    int redefineOccurenceNumber) 
    {
        FieldDefinitionEntry field = (FieldDefinitionEntry)fields[fieldId];

        String fieldFullQualName = getFullQualName(field);
        CopybookFieldGenInfo fieldCbGenInfo = (CopybookFieldGenInfo)fieldGenInfosMap.get(fieldFullQualName);

        // If the field is bypassed don't build redefine info
        if (fieldCbGenInfo != null && fieldCbGenInfo.isBypassField())
        	return;
    
        if (field.isGroup()) {
            // If this group is to be bypassed don't build redefined info
            if (fieldCbGenInfo != null && fieldCbGenInfo.isGenGrpLvlBypassField())
                return;
            
            List<FieldDefinitionEntry> childrenFields = getChildFields(fieldId);
            if (childrenFields == null)
            	return;

           // Process children
           visitRedefineChildren(rList,           	
					redefinedFieldId,
					fieldId, 
					redefineOccurenceNumber,
					childrenFields);
         }
        else {
        	RedefineData rd = new RedefineData(field.getFieldId(),
                                               redefinedFieldId,
                                               redefineOccurenceNumber);
            rList.add(rd);
            redefiningFieldInfoMap.put(Integer.valueOf(rd.getFieldId()), rd);
        }
    }
 
    /**
     *  Process children to build redefine info
	 **/
    @SuppressWarnings("unchecked")
	private void visitRedefineChildren(List<RedefineData> rList, 
            						int redefinedFieldId,
            						int fieldId, 
            						int redefineOccurenceNumber,
            						List<FieldDefinitionEntry> childrenFields)
    {
    	for (Iterator<?> itt = childrenFields.iterator(); itt.hasNext();) {
        	Object elem = itt.next();
            if (!(elem instanceof FieldDefinitionEntry)) {
            	visitRedefineChildren(rList,           	
            						redefinedFieldId,
            						fieldId, 
            						redefineOccurenceNumber,
            						(List<FieldDefinitionEntry>)elem);
            } else {
          		FieldDefinitionEntry redefinedChildField = (FieldDefinitionEntry)elem;
        		if (redefinedChildField.isGroup())
        			visitRedefineGroup(rList,
                                   redefinedFieldId,
                                   redefinedChildField.getFieldId(), 
                                   redefineOccurenceNumber);
        		else {
        			RedefineData rd = new RedefineData(redefinedChildField.getFieldId(),
                                                   redefinedFieldId,
                                                   redefineOccurenceNumber);
        			rList.add(rd);
        			redefiningFieldInfoMap.put(Integer.valueOf(rd.getFieldId()), rd);
        		}
            }
        }
    }
    
    /**
     *  return the field definition for a fieldId
	 **/
	public FieldDefinitionEntry getFieldDefinitionEntry(int fieldId)
	{
		return fields[fieldId];
	}
	
    /**
     *  return the last field definition in the copybook
	 **/
	public int getLastParentFieldDefinitionEntry(int fieldId)
	{
        FieldDefinitionEntry fdEntry = (FieldDefinitionEntry)fields[fieldId];
        if (fdEntry.getParentFieldId() != fdEntry.getFieldId()) {
            fieldId = getLastParentFieldDefinitionEntry(fdEntry.getParentFieldId());
        }
		return fieldId;
	}
	
	/**
     *  return the length of overall copybook
	 **/
	public int getCopybookLength()
	{
        FieldDefinitionEntry fdEntry = (FieldDefinitionEntry)fields[fields.length - 1];
        int fieldId = getLastParentFieldDefinitionEntry(fdEntry.getFieldId());
        fdEntry = (FieldDefinitionEntry)fields[fieldId];
        if (fdEntry.getObjectLength() == 0)
            return fdEntry.getOffset() + fdEntry.getDataLength();
        return fdEntry.getOffset() + fdEntry.getObjectLength();
	}
}
