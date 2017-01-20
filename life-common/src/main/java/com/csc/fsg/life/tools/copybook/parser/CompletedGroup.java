package com.csc.fsg.life.tools.copybook.parser;


/**
 * Completed Group class is utilized in order to pass information about Groups.
 * This class retains a Group Id which is the index into the Field Definition
 * Table and a length which is the total length of the Group.
 */
public class CompletedGroup {
    private int groupId = 0;
    private int groupLength = 0;
    
    /**
     * Constructor for Completed Group.
     *
     * @param fieldId - (int) Field Id of the completed Group Entry
     * @param length - (int) Length of the Group that has been completed
     */
    protected CompletedGroup(int fieldId, int length) {
        groupId = fieldId;
        groupLength = length;
    }
    
    /**
     * Access method for the groupId property.
     */
    protected int getGroupId() {
        return groupId;
    }
    
    /**
     * Access method for the groupLength property.
     */
    protected int getGroupLength() {
        return groupLength;
    }
}
