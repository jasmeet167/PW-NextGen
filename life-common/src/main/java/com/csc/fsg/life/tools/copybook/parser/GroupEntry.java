package com.csc.fsg.life.tools.copybook.parser;


/**
 * Group Entry class is utilized in order to maintain information about Group Field
 * Field Definition Elements. This information is utilize to compute the length of 
 * each of the Groups.  This class consists of a Group Field Id which is the Field Id 
 * of the Group in the Field Defintion Entry, Group Level which defines in what level 
 * the Group is defined in the Copybook, Group Data Type which is the Special Usage
 * definition of the data of that Group, Group Occurs which is the number of repetitions
 * of the Group, Group Redefine which indicates whether or not the Group Redefines an
 * existing element of the Copybook, and Group Length which is adjusted as elements of
 * Group are processed in order to compute the overall length of the Group.
 */
public class GroupEntry {
    private int groupFieldId = 0;
    private int groupLevel = 0;
    private String groupDataType = null;
    private int groupOccurs = 0;
    private boolean groupRedefine = false;
    private int groupLength = 0;
    
    /**
     * Constructor for Group Entry.
     *
     * @param fieldId (int) Field Id of the new Group Entry
     * @param level (int) Level of the current Group Entry
     * @param dataType (String) Data Type of the current Group Entry
     * @param occurs (int) Number of Occurrences of the current Group Entry
     * @param redefine (boolean) Boolean value representing whether Group Entry
     *                    is a redefines
     */
    protected GroupEntry(int fieldId,
                         int level,
                         String dataType,
                         int occurs,
                         boolean redefine) {
        groupFieldId = fieldId;
        groupLevel = level;
        groupDataType = dataType;
        groupOccurs = occurs;
        groupRedefine = redefine;
    }
    
    /**
     * Access method for the groupFieldId property.
     *
     * @return (int) returns data value of groupFieldId property
     */
    protected int getGroupFieldId() {
        return groupFieldId;
    }
    
    /**
     * Access method for the groupDataType property.
     *
     * @return (String) returns data value of groupDataType property
     */
    protected String getGroupDataType() {
        return groupDataType;
    }
    
    /**
     * Access method for the groupLevel property.
     *
     * @return (int) returns data value of groupLevel property
     */
    protected int getGroupLevel() {
        return groupLevel;
    }
    
    /**
     * Access method for the groupOccurs property.
     *
     * @return (int) returns data value of groupOccurs property
     */
    protected int getGroupOccurs() {
        return groupOccurs;
    }
    
    /**
     * Access method for the groupRedefine property.
     *
     * @return (boolean) returns data value of groupRedifine property
     */
    protected boolean getGroupRedefine() {
        return groupRedefine;
    }
    
    /**
     * Access method for the groupLength property.
     *
     * @return (int) returns data value of groupLength property
     */
    protected int getGroupLength() {
        return groupLength;
    }
    
    /**
     * Updates the value of the groupLength property.
     *
     * @param aGroupLength (int) New Length value for the Group entry
     */
    protected void updateGroupLength(int aGroupLength) {
        groupLength = aGroupLength;
    }
}
