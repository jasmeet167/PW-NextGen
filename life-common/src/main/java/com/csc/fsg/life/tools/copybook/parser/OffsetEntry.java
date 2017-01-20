package com.csc.fsg.life.tools.copybook.parser;


/**
 * Offset Entry class is utilized in order to retain information about restore
 * offset value. This class consists of a Offset Field Id which is the Field Id 
 * of the Field Defintion Entry being processed and an Offset Restore Value which
 * is the value the offset is to be restored to when the Offset Entry is completed.
 */
public class OffsetEntry {
    private int offsetFieldId = 0;
    private int offsetRestoreValue = 0;
    
    /**
     * Constructor for Offset Entry
     *
     * @param fieldId - (int) Field Id of the new Offset entry.
     * @param restoreValue - (int) Restore Offset value for Field Id.
     */
    protected OffsetEntry(int fieldId, int restoreValue) {
        offsetFieldId = fieldId;
        offsetRestoreValue = restoreValue;
    }
    
    /**
     * Access method for the offsetFieldId property.
     *
     * @return (int) - returns data value of offsetFieldId property
     */
    protected int getOffsetFieldId() {
        return offsetFieldId;
    }
    
    /**
     * Access method for the offsetRestoreValue property.
     *
     * @return (int) - returns data value of offsetRestoreValue property
     */
    protected int getOffsetRestoreValue() {
        return offsetRestoreValue;
    }
}
