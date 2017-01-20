/**Copybook Interpreter Class
 * Copybook Interpreter class is the driver for the Copybook Interpretation process.
 * This class first instantiates the required Copybook File, Group Table, and Field 
 * Definition Table objects. Next a repetitive process of gathering the Field Information
 * for the next Field of the copybook and processing the information by performing 
 * Field Definition Table and Group Table processing is performed until all fields or
 * the copybook have been processed. Lastly, the clean up process is completed by 
 * updating the final lengths on any Groups existing in the Group Table followed by 
 * process to derive and update the offset values in the Field Definition Table.
 * The resulting Field Definition Table is then returned which includes all the 
 * Field Definition Entries for the copybook.
 */

package com.csc.fsg.life.tools.copybook.parser;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;

import com.csc.fsg.life.tools.copybook.model.FieldDefinitionEntry;


/**
   Copybook Interpreter class.   Parses copybook files into a java data structures.
**/
public class CopybookInterpreter 
{
    private int fieldId = 0;
    private FieldDefinitionTable fieldDefinitionTable = new FieldDefinitionTable();
    private GroupTable groupTable = new GroupTable();
    private List<File> sourcePathList = new ArrayList<File>();
    
    /**
     * Constructor for Copybook Interpreter Class.
     */
    public CopybookInterpreter() {
    }
    
    public CopybookInterpreter(List<File> sourcePathList) {
        this.sourcePathList = sourcePathList;
    }
    
    /**
     * Iterates through each line of the Copybook that is passed in.
     * Each line will be read and interpreted to produce an output vector
     * of CopybookInterpreterEntries.
     * 
     * @param copybook - (File) file that contains the copybook source
     * @return (FieldDefinitionEntry[]) - an array of Field Definition Entries defined 
     *                                    in the copybook
     */
    public FieldDefinitionEntry[] interpretCopybook(File copybook) 
    throws CopybookInterpreterException {
        
        CopybookFile copybookFile = new CopybookFile(copybook, sourcePathList);
        FieldInformation fieldInformation = new FieldInformation(copybookFile);
        
        // Line by Line build the Field Definition Data for the copybook
        processCopybookFile(copybookFile, fieldInformation);
        
        copybookFile.close();
        
        // Retrieve a list of all existing groups and their lengths.
        // The highest possible level of 1 is utilized to ensure all groups are closed.
        Vector<CompletedGroup> completedGroups = groupTable.completeGroups(1);
        
        // Update the field definition Table with completed group lengths
        fieldDefinitionTable.updateGroupLengths(completedGroups);
        
        // Compute and populate Offset information for Field Definition Table
        fieldDefinitionTable.processOffsets();
        
        // Return the Field Definition Entries in array format
        Vector<FieldDefinitionEntry> fdTable = fieldDefinitionTable.getFDTable();
        return fdTable.toArray(new FieldDefinitionEntry[0]);
    }

    
    /**
     * Performs the appropriate processing for each field definition of the copybook.
     * This process includes the following steps:
     *      1: Perform applicable Field Definition processing
     *      2: Perform applicable Group Table processing
     *
     * @param fieldInformation - (FieldInformation) contains parsed Field Information
     *                           defining a Field of the copybook
     */
    private void processFieldDefinition(FieldInformation fieldInformation) 
    throws CopybookInterpreterException {

        // access the parent field id for the current Field Definition
        int parentFieldId = groupTable.getParentFieldId(fieldId);
        
        // Perform any appropriate Field Definition Table updates
        fieldDefinitionTable.processField(fieldId,
                                          parentFieldId,
                                          fieldInformation);
        
        // Perform any appropriate Group Table updates
        groupTable.processGroup(fieldId, fieldInformation);
        
        // Increment the FieldId in order to get the next Field Id
        fieldId++;
    }
    
    private void processCopybookFile(CopybookFile copybookFile,
                                     FieldInformation fieldInformation)
		throws CopybookInterpreterException 
    {
        while (!copybookFile.isEndOfCopybook()) {
            // obtain the formatted Field Definition information
            fieldInformation.createFieldInformation(fieldDefinitionTable, groupTable);
            if (copybookFile.isCopyStatement()) {
                File copyStatementCopybook = 
					getCopybookFile(sourcePathList, 
									fieldInformation.getCopyStatementCopybookName() + ".CPY");

                CopybookFile copyStatementCopybookFile = new CopybookFile(copyStatementCopybook, sourcePathList);
                FieldInformation copyStatementFieldInformation = new FieldInformation(copyStatementCopybookFile);
                processCopybookFile(copyStatementCopybookFile, copyStatementFieldInformation);
                copybookFile.clearCopyStatement();
            }
            // check if Field Information exist
            else if (fieldInformation.getFieldName().length() > 0) {
                // process the current Field Definition information
                processFieldDefinition(fieldInformation);
            }
        }
	}

	/**
	   Gets the copybook file for the specified file from the list of possible directories.
	   @param sourcePathList a list of possible directories for the copybook.  
	   They are searched in order.  This parameter should be a list of File objects.
	   @param file The file name (including extension) of the copybook to get.
	   @return A File object for the copybook or null if not found.
	**/
	public static File getCopybookFile(List<File> sourcePathList, String file)
	{
		for (Iterator<File> itt = sourcePathList.iterator(); itt.hasNext();) {
			File nextPath = (File)itt.next();
			File f = new File(nextPath, file);
			
			// Found it if it exists.
			if (f.exists()){
				try {
					System.out.println("    Found file: " + file + " in path " + nextPath.getCanonicalPath());
				} catch(Exception e) {
					;  // Ignore we were just trying to log.
				}
				return f;
			}
		}

		System.out.println("     Unable to find copybook: " + file);
		return null;
	}

}
