package com.csc.fsg.life.tools.copybook.parser;

import java.util.Vector;

/**
 * Offset Table class supports the Offset Table and all processing against this 
 * table.  The Offset Table maintains the restore value for the current offset
 * pointer during the process of computing the offset values for the Field Definiton
 * Entries. The Offset Table consists of a Vector of Offset Entry Objects.  
 */
public class OffsetTable {
    private Vector<OffsetEntry> offsetTable = new Vector<OffsetEntry>();
    
    /**
     * Empty constructor for Offset Table Class.
     */
    protected OffsetTable() {
    }
    
    /**
     * Creates an Offset Entry in the Offset Table.
     *
     * @param fieldId (int) Field Id of the new Offset Entry
     * @param restoreValue (int) Restore Value for the Offset Entry
     */
    protected void addOffset(int fieldId, int restoreValue) {
        OffsetEntry offsetEntry = new OffsetEntry(fieldId, restoreValue);
        offsetTable.addElement(offsetEntry);
    }
    
    /**
     * Retrieves the first Offset Tables entry which is the parent offset 
     * and clears off all remaining Offset Restore Values.
     *
     * @param offset (int) Current offset value
     * @return (int) returns the Restore Offset Value of the first entry in the 
     *                 offset table
     */
    protected int getParentOffset(int offset) {
        // if offset doesn't contain any offset entries, return 0
        if (offsetTable.size() == 0) {
            return offset;
        }
        OffsetEntry offsetEntry = offsetTable.elementAt(0);
        offsetTable.clear();
        return offsetEntry.getOffsetRestoreValue();
    }
    
    /**
     * Returns the offset restore value that is determined by locating the
     * earliest entry in the offset table in which the passed Parent Id is
     * less than that of the offsets Field Id. If no reset value is to be
     * restored, the offset parameter is returned.
     *
     * @param parentFieldId (int) Parent Field Id of the current Field
     *                        Definition Entry being processed
     * @param offset (int) current Offset value
     * @return (int) returns the value the offset is to be restored to based 
     *                 on completed Offset Entries
     */
    protected int completeOffsets(int parentFieldId, int offset) {
        for (int i=offsetTable.size() - 1; i > -1; i--) {
            // establish reference to last Offset Table entry
            OffsetEntry offsetEntry = offsetTable.elementAt(i);
            // determine if Offset is completed and ready to be restored
            if (parentFieldId < offsetEntry.getOffsetFieldId()) {
                // capture the Offset Restore Value to return
                offset = offsetEntry.getOffsetRestoreValue();
                // remove the completed group from Group Table
                offsetTable.removeElementAt(i);
            }
            else {
                i = -1;
            }
        }
        return offset;
    }
}
