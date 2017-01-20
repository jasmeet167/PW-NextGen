package com.csc.fsg.life.tools.copybook.parser;

import java.util.Vector;

/* Modifications: T0106 */

/**
 * Group Table class supports the Group Table and all processing against this 
 * table.  The Group Table maintains information about each of the active Groups
 * during the copybook interpretation process. This information is utilized to
 * compute and update each Groups Length in the Field Definition Table. The Group 
 * Table consists of a Vector of Group Entry Objects.  
 */
public class GroupTable 
{
    private Vector<GroupEntry> groupTable = new Vector<GroupEntry>();
    
    /**
     * Empty constructor for Group Table Class.
     */
    protected GroupTable() {
    }
    
    /**
     * Makes decision and performs appropriate group processing based on copybook
     * line information passed in.
     *
     * @param fieldId (int) Field Id of the current Field being processed
     * @param fieldInformation (FieldInformation) Parsed Field Information
     */
    protected void processGroup(int fieldId, FieldInformation fieldInformation) 
    {
        // If no picture clause exists, add a new Group Entry to Group Table
        if  (!fieldInformation.getHasPictureClause() && !fieldInformation.getDataType().equals("POINTER")) {
            addGroup(fieldId, fieldInformation);
        }
        // If a picture clause exists and Field Definition is redefining an
        // existing field, add it's Data Length to the length of the last Group
        else if ((fieldInformation.getRedefinedFieldName().length() == 0)
        &&  (groupTable.size() > 0)) {
            int length = fieldInformation.getDataLength();
            int nbrOfOccurs = fieldInformation.getNbrOfOccurs();
            addLength(length, nbrOfOccurs);
        }
    }
    
    /**
     * Creates a Group Entry in the Group Table based on the input parameters.
     *
     * @param fieldId (int) Field Id of the new group to be added.
     * @param fieldInformation (FieldInformation) Parsed Field Information
     */
    private void addGroup(int fieldId, FieldInformation fieldInformation) 
    {
        boolean redefines = false;
        String groupDataType = null;
        
        // if the Data Type of the parent group entry is a special usage type,
        // utilize that usage Data Type on the current Group
        groupDataType = getParentGroupDataType();
        
        // if the dataType of the field information is a special usage, store
        // it in the group entry else store null
        if ((groupDataType == null)
        &&  (fieldInformation.isSpecialUsage(fieldInformation.getDataType()))) {
            groupDataType = fieldInformation.getDataType();
        }
        
        // if length of redefined field name of field information is greater
        // than zero, set the redefines attribute to true
        if (fieldInformation.getRedefinedFieldName().length() > 0) {
            redefines = true;
        }
        
        // instantiate the new group entry
        GroupEntry groupEntry = new GroupEntry(fieldId,
                                               fieldInformation.getFieldLevel(),
                                               groupDataType,
                                               fieldInformation.getNbrOfOccurs(),
                                               redefines);
        // add group entry to group table
        groupTable.addElement(groupEntry);
    }
    
    /**
     * Adds a result of length times nbrOfOccurs of current field to the last
     * Group in the Group Table.
     *
     * @param length (int) Length of the current Field
     * @param nbrOfOccurs (int) Number of occurrences of the current Field
     */
    private void addLength(int length, int nbrOfOccurs) 
    {
        int lastGroup = groupTable.size() - 1;
        GroupEntry groupEntry = groupTable.elementAt(lastGroup);
        int oldLength = groupEntry.getGroupLength();
        int newLength = oldLength + (length * nbrOfOccurs);
        groupEntry.updateGroupLength(newLength);
    }
    
    /**
     * Returns a list of the groups and their associated lengths that will be
     * completed by the level parameter. Each time a Group is Completed the
     * length of the Group being deleted is added to the prior Group and then
     * the Completed Group is deleted from the Group Table.
     *
     * @param level (int) Level of the current Field of the copybook line
     *                that will be used to determine which group(s) are completed
     */
    protected Vector<CompletedGroup> completeGroups(int level) 
    {
        int prevGroupLength = 0;
        int prevGroupOccurs = 0;
        GroupEntry groupEntry;
        Vector<CompletedGroup> completedGroups = new Vector<CompletedGroup>();
        
        for (int i=groupTable.size() - 1; i > -1; i--) {
            // establish reference to last Group Table entry
            groupEntry = groupTable.elementAt(i);
            
            // adjust length of current Group by the previously deleted
            // Group's length and nbrOfOccurs
            if (prevGroupLength > 0) {
                addLength(prevGroupLength, prevGroupOccurs);
            }
            
            // determine if Group is completed by next level to be processed
            if (groupEntry.getGroupLevel() >= level) {
                // add completed group information to completed Group Vector
                CompletedGroup completedGroup = new CompletedGroup(groupEntry.getGroupFieldId(),
                groupEntry.getGroupLength());
                completedGroups.add(completedGroup);
                
                // retain completed Groups length and nbrOfOccurs in order to
                // adjust Parent's Group Length, unless completed group is redefine
                if (!groupEntry.getGroupRedefine()) {
                    prevGroupLength = groupEntry.getGroupLength();
                    prevGroupOccurs = groupEntry.getGroupOccurs();
                }
                else {
                    prevGroupLength = 0;
                    prevGroupOccurs = 0;
                }
                
                // remove the completed group from Group Table
                groupTable.removeElementAt(i);
            }
            else {
                i = -1;
            }
        }
        return completedGroups;
    }
    
    /**
     * Returns the last Group Table entry's Field Id.  If no Groups exist,
     * the parameter Field Id is returned.
     *
     * @param fieldId (int) Field Id of current Field being processed
     * @return (int) The Field Id of the last Group Entry in the Group Table
     */
    protected int getParentFieldId(int fieldId) 
    {
        if (groupTable.size() == 0) {
            return fieldId;
        }
        GroupEntry groupEntry = groupTable.lastElement();
        return groupEntry.getGroupFieldId();
    }
    
    /**
     * Retrieves the groupDataType property of the last Group Entry in the Group
     * Table.  If there are no group entries or if the groupDataType property is
     * null or an empty string, then null is returned.
     *
     * @return (String) The Data Type of the last Group Entry in the Group Table
     */
    protected String getParentGroupDataType() 
    {
        if (groupTable.size() == 0) {
            return null;
        }
        
        GroupEntry groupEntry = groupTable.lastElement();
        String lastGroupDataType = groupEntry.getGroupDataType();
        if ((lastGroupDataType == null )
        ||  (lastGroupDataType.equals(""))) {
            return null;
        }
        return lastGroupDataType;
    }
}
