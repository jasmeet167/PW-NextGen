package com.csc.fsg.life.tools.copybook.parser;

import java.util.Vector;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.csc.fsg.life.tools.copybook.model.FieldDefinitionEntry;

/* Modifications: T0150 */

/**
 * Field Definition Table class supports the Field Definition Table and all 
 * processing against this table.  The Field Definition Table maintains Field 
 * Definition Entries about a copybook. Processing against the Field Definition 
 * Table includes the creation of each of the specific types of Field Definition
 * Elements, updating of the Group Length once computed, updating the offset values
 * once determined, and the determination of a Field Id for a field from the Name 
 * of the field.  
 */
public class FieldDefinitionTable {

    private int fieldId = 0;
    private int parentFieldId = 0;
    private String copybookName = "";
    private Vector<FieldDefinitionEntry> fdTable = new Vector<FieldDefinitionEntry>();
    private FieldInformation fieldInformation;
    
    /** Apache Commons Logger. **/
    protected static final Log log = LogFactory.getLog("com.csc.fsg.life.tools.copybook");
    
    /**
     * Empty constructor for Field Definition Table Class.
     */
    protected FieldDefinitionTable() {
    }
    
    /**
     * Makes decision and processes appropriate field definition processing based
     * on copybook line information passed in.
     *
     * @param afieldId - (int) contains the field id of the new Field Definition Element.
     * @param aParentFieldId - (int) contains the Parent Field Id of the new Field Definition Element.
     * @param aFieldInformation - (fieldInformation) contains the formatted Field Information
     */
    protected void processField(int afieldId,
                                int aParentFieldId,
                                FieldInformation aFieldInformation) 
    	throws CopybookInterpreterException 
    {
        // Set up required data needed to create entries
        copybookName = aFieldInformation.getCopybookName();
        fieldId = afieldId;
        parentFieldId = aParentFieldId;
        fieldInformation = aFieldInformation;
        
        // Set up required information for processing decision
        boolean isElement = aFieldInformation.getHasPictureClause() 
        						|| aFieldInformation.getDataType().equals("POINTER");
        int nbrOfOccurs = aFieldInformation.getNbrOfOccurs();
                
        // New Element
        if ((isElement)
        &&  (nbrOfOccurs == 1)) {
            addElement();
        }
        // New Group
        else if ((!isElement)
        &&  (nbrOfOccurs == 1)) {
            addGroup();
        }
        // New Occurs Group
        else if ((!isElement)
        &&  (nbrOfOccurs > 1)) {
            addOccursGroup();
        }
        // New Occurs Element
        else if ((isElement)
        &&  (nbrOfOccurs > 1)) {
            addOccursElement();
        }
    }
    
    /**
     * Adds a new Element Type Field Definition Entry to the Field Definition Table.
     *
     */
    private void addElement()
    	throws CopybookInterpreterException 
    {
        FieldDefinitionEntry fdEntry = new FieldDefinitionEntry();
        fdEntry.setCopybookName(copybookName);
        fdEntry.setFieldLength(fieldInformation.getFieldLength());
        fdEntry.setDataLength(fieldInformation.getDataLength());
        fdEntry.setDataScale(fieldInformation.getDataScale());
        fdEntry.setDataType(fieldInformation.getDataType());
        fdEntry.setFieldId(fieldId);
        fdEntry.setFieldName(fieldInformation.getFieldName());
        fdEntry.setParentFieldId(parentFieldId);
        if (fieldInformation.getIsFillerLine()) {
            fdEntry.setFieldType("FE");
        }
        else {
            fdEntry.setFieldType("E");
        }
        if (fieldInformation.getRedefinedFieldName().length() > 0) {
            String redefinedFieldName = fieldInformation.getRedefinedFieldName();
            int redefinedFieldId = getFieldIdForFieldName(fieldId, parentFieldId, redefinedFieldName);
            fdEntry.setRedefinesFieldId(redefinedFieldId);
        }
        fdTable.add(fdEntry);
    }
    
    /**
     * Adds a new Group Type Field Definition Entry to the Field Definition Table.
     *
     */
    private void addGroup() 
    	throws CopybookInterpreterException 
    {
        FieldDefinitionEntry fdEntry = new FieldDefinitionEntry();
        fdEntry.setCopybookName(copybookName);
        fdEntry.setDataType(fieldInformation.getDataType());
        fdEntry.setFieldId(fieldId);
        fdEntry.setFieldName(fieldInformation.getFieldName());
        fdEntry.setParentFieldId(parentFieldId);
        if (fieldInformation.getIsFillerLine()) {
            fdEntry.setFieldType("FG");
        }
        else {
            fdEntry.setFieldType("G");
        }
        if (fieldInformation.getRedefinedFieldName().length() > 0) {
            String redefinedFieldName = fieldInformation.getRedefinedFieldName();
            int redefinedFieldId = getFieldIdForFieldName(fieldId, parentFieldId, redefinedFieldName);
            fdEntry.setRedefinesFieldId(redefinedFieldId);
        }
        fdTable.add(fdEntry);
    }
    
    /**
     * Adds a new Occurs Element Type Field Definition Entry to the Field Definition Table.
     *
     */
    private void addOccursElement() 
    	throws CopybookInterpreterException 
    {
        FieldDefinitionEntry fdEntry = new FieldDefinitionEntry();
        fdEntry.setCopybookName(copybookName);
        fdEntry.setDataLength(fieldInformation.getDataLength());
        fdEntry.setDataScale(fieldInformation.getDataScale());
        fdEntry.setDataType(fieldInformation.getDataType());
        fdEntry.setFieldId(fieldId);
        fdEntry.setFieldName(fieldInformation.getFieldName());
        fdEntry.setParentFieldId(parentFieldId);
        fdEntry.setNbrOfOccurs(fieldInformation.getNbrOfOccurs());
        if (fieldInformation.getIsFillerLine()) {
            fdEntry.setFieldType("FOE");
        }
        else {
            fdEntry.setFieldType("OE");
        }
        if (fieldInformation.getRedefinedFieldName().length() > 0) {
            String redefinedFieldName = fieldInformation.getRedefinedFieldName();
            int redefinedFieldId = getFieldIdForFieldName(fieldId, parentFieldId, redefinedFieldName);
            fdEntry.setRedefinesFieldId(redefinedFieldId);
        }
        if (fieldInformation.getOccursDependingOn().length() > 0) {
            String dependingOnFieldName = fieldInformation.getOccursDependingOn();
            int dependingOnFieldId = getFieldIdForFieldName(fieldId, -1, dependingOnFieldName);
            fdEntry.setDependingOnFieldId(dependingOnFieldId);
        }
        fdTable.add(fdEntry);
    }
    
    /**
     * Adds a new Occurs Group Type Field Definition Entry to the Field Definition Table.
     *
     */
    private void addOccursGroup() 
    	throws CopybookInterpreterException 
    {
        FieldDefinitionEntry fdEntry = new FieldDefinitionEntry();
        fdEntry.setCopybookName(copybookName);
        fdEntry.setDataType(fieldInformation.getDataType());
        fdEntry.setFieldId(fieldId);
        fdEntry.setFieldName(fieldInformation.getFieldName());
        fdEntry.setParentFieldId(parentFieldId);
        fdEntry.setNbrOfOccurs(fieldInformation.getNbrOfOccurs());
        if (fieldInformation.getIsFillerLine()) {
            fdEntry.setFieldType("FOG");
        }
        else {
            fdEntry.setFieldType("OG");
        }
        if (fieldInformation.getRedefinedFieldName().length() > 0) {
            String redefinedFieldName = fieldInformation.getRedefinedFieldName();
            int redefinedFieldId = getFieldIdForFieldName(fieldId, parentFieldId, redefinedFieldName);
            fdEntry.setRedefinesFieldId(redefinedFieldId);
        }
        if (fieldInformation.getOccursDependingOn().length() > 0) {
            String dependingOnFieldName = fieldInformation.getOccursDependingOn();
            int dependingOnFieldId = getFieldIdForFieldName(fieldId, -1, dependingOnFieldName);
            fdEntry.setDependingOnFieldId(dependingOnFieldId);
        }
        fdTable.add(fdEntry);
    }
    
    /**
     * Determines the Field Id for the Field Name input parameter.
     * If the Field Name doesn't exist in the fdTable then the Copybook Interpreter
     * Exception is generated.  If ParentId is equal to -1, then the match is 
     * performed on Field Name only.
     *
     * @param fieldId - (int) the Field Id of the redefining field
     * @param parentFieldId - (int) the parent Field Id of the redefining field
     * @param fieldName - (String) contains the Field Name of the entry in which
     *                    the Field Id is to be returned
     * @return (int) - returns the Field Id of the Field Name parameter
     */
    private int getFieldIdForFieldName(int fieldId,int parentFieldId, String fieldName) 
    	throws CopybookInterpreterException 
    {
        boolean ignoreLevel = false;
        FieldDefinitionEntry fdEntry;
        if (parentFieldId == -1
         || fieldId == parentFieldId) {
            ignoreLevel = true;
        }
        
        for (int i=fdTable.size() - 1; i > -1; i--) {
            // get the next Field Definition Element
            fdEntry = fdTable.elementAt(i);
            // if the field name is found and the parent ids are equal or
            // if the field name is found and the parameter parent field id is -1
            // pass back the Field Definition Element's field id
            if ((fdEntry.getFieldName().equals(fieldName))
            &&  (parentFieldId == fdEntry.getParentFieldId()
            ||   ignoreLevel)) {
                // if the Field Definition Element is redefined itself, pass
                // back the field it is redefining
                if (fdEntry.getRedefinesFieldId() > -1) {
                    return fdEntry.getRedefinesFieldId();
                }
                return i;
            }
        } 
        // generate exception for missing closing parenthesis
        String ciException = "Field name not defined in copybook: " 
                             + copybookName + " "  
                             + fieldName;
        log.info(ciException);
        throw new CopybookInterpreterException(ciException);
    }
    
    /**
     * Updates the length of the Field Definition Entries in the Field Definition 
     * Table whose Field Id is contained in the completed groups vector.
     *
     */
    protected void updateGroupLengths(Vector<CompletedGroup> completedGroups) 
    {
        int groupId;
        int groupLength;
        CompletedGroup completedGroup;
        FieldDefinitionEntry fdEntry;
        for (int i=0; i < completedGroups.size(); i++) {
            completedGroup = completedGroups.elementAt(i);
            groupId = completedGroup.getGroupId();
            groupLength = completedGroup.getGroupLength();
            fdEntry = fdTable.elementAt(groupId);
            fdEntry.setDataLength(groupLength);
            if (fdEntry.getNbrOfOccurs() > 0)
                fdEntry.setObjectLength(groupLength * fdEntry.getNbrOfOccurs());
            else
                fdEntry.setObjectLength(groupLength);
        }
    }
    
    /**
     * Populates the Offset Value for each of the Field Definition Table Entries.
     */
    protected void processOffsets() 
    {
        int offset = 0;
        int fieldId = 0;
        int redefinesFieldId = 0;
        int restoreOffset = 0;
        String fieldType = "";
        FieldDefinitionEntry fdEntry;
        FieldDefinitionEntry redefinedFDEntry;
        OffsetTable offsetTable = new OffsetTable();
        
        for (int i=0; i < fdTable.size(); i++) {
            // Set reference to Field Definition Table Entry
            fdEntry = fdTable.elementAt(i);
            
            // access applicable Field Definition data
            fieldId = fdEntry.getFieldId();
            parentFieldId = fdEntry.getParentFieldId();
            redefinesFieldId = fdEntry.getRedefinesFieldId();
            fieldType = fdEntry.getFieldType();
            
            // Obtain Reset Offset if an offset entry is completed
            offset = offsetTable.completeOffsets(parentFieldId, offset);
            
            // If this is a new parent level which is not redefining the previous 
            // parent level, obtain offset based on parent level from the Offset Table
            if (fieldId == parentFieldId
            &&  redefinesFieldId == -1) {
                offset = offsetTable.getParentOffset(offset);
            }
            
            // If this is a Redefining Field Definition, push the current offset value
            // and reset the offset to be the offset of the Redefined Field Definition
            if (redefinesFieldId != -1) {
                offsetTable.addOffset(fieldId, offset);
                redefinesFieldId = fdEntry.getRedefinesFieldId();
                redefinedFDEntry = fdTable.elementAt(redefinesFieldId);
                offset = redefinedFDEntry.getOffset();
            }
            // If this is a Group or Occurs Group Entry, push the current Offset Value
            else if ((fieldType.equals("G"))
            ||  (fieldType.equals("OG"))
            ||  (fieldType.equals("FG"))
            ||  (fieldType.equals("FOG"))) {
                restoreOffset = offset + (fdEntry.getDataLength() *
                                          fdEntry.getNbrOfOccurs());
                offsetTable.addOffset(fieldId, restoreOffset);
            }
            
            // populate the Offset Value in the Field Definition
            fdEntry.setOffset(offset);
            
            // adjust the offset for all none Elements that are not Redefines
            if ((redefinesFieldId == -1)
            &&  ((fieldType.equals("E"))
            ||   (fieldType.equals("FE"))
            ||   (fieldType.equals("OE"))
            ||   (fieldType.equals("FOE")))) {
                // offset is adjusted by Number of Occurs times Data Length
                offset = offset + (fdEntry.getDataLength() *
                fdEntry.getNbrOfOccurs());
            }
        }
    }
    
    /**
     * Access method for the fdTable property
     */
    protected Vector<FieldDefinitionEntry> getFDTable() {
        return fdTable;
    }
}
