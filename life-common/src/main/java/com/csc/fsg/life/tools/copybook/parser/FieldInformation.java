package com.csc.fsg.life.tools.copybook.parser;

import java.util.Vector;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/* Modifications: T0150 */

/**
 * Field Information class is utilized to analyze source code defining a field of
 * a copybook in order to determine all the COBOL attributes of that field.  This 
 * information can then be utilized to create the Field Definition Entries. 
 */
public class FieldInformation {

    private CopybookFile copybookFile;
    private String copybookName = "";
    private int fieldLevel;
    private String fieldName;
    private boolean isFillerLine;
    private boolean hasPictureClause;
    private String dataType;

	// Length when packed (if applicable).
    private int dataLength;

	// Length of field in characters.
	private int fieldLength;

    private int dataScale;
    private int nbrOfOccurs;
    private String redefinedFieldName;
    private String occursDependingOn;
    private String copyStatementCopybookName = "";

    // Logger
    protected static final Log log = LogFactory.getLog("com.csc.fsg.life.tools.copybook");
    
    /**
     * Constructor for Field Information class.
     *
     * @param aCopybookFile - (CopybookFile) Copybook File object which retains
     *                        copybook file information and access methods
     */
    protected FieldInformation(CopybookFile aCopybookFile) {
        // establish  reference File Reader and Buffer Reader for the copybook
        copybookFile = aCopybookFile;
        
        // capture the Copybook Name
        copybookName = copybookFile.getCopybookName();
    }
    
    /**
     * Performs the required processing to obtain the source code that defines
     * the next Field Definition of the Copybook and then formats the Field
     * Information which represents this Field Definition.
     *
     * @param fieldDefinitionTable - (FieldDefinitionTable) Field Definition Table for
     *                               the current copybook
     * @param groupTable - (GroupTable) Group information for current fields of copybook
     */
    protected void createFieldInformation(FieldDefinitionTable fieldDefinitionTable, 
                                          GroupTable groupTable)
    throws CopybookInterpreterException {
        // initialize data prior to interpreting copybook line
        reset();
        
        // obtain the next Field Definition to be processed
        String fieldDefinition = getValidLevelFieldDefinition();
        if (copybookFile.isCopyStatement()) {
            copyStatementCopybookName = retrieveCopyStatementCopybook(fieldDefinition);
            return;
        }
        
        // if another Field Definition exists, create the Field Definition Entry
        if (fieldDefinition != null) {
            // Complete any groups that are completed by current field and
            // update the completed group lengths
            Vector<CompletedGroup> completedGroups = groupTable.completeGroups(fieldLevel);
            fieldDefinitionTable.updateGroupLengths(completedGroups);
        
            fieldName = determineFieldName(fieldDefinition);
            occursDependingOn = determineOccursDependingOn(fieldDefinition);
            nbrOfOccurs = determineNbrOfOccurs(fieldDefinition);
            redefinedFieldName = determineRedefinedFieldName(fieldDefinition);
            
            // if a picture clause exists, interpret picture clause information
            hasPictureClause = determineIfPictureClause(fieldDefinition);
            if (hasPictureClause)
                processPictureCharacterString(fieldDefinition);

			// Save dataLength as field length before modifying for
			// packed or binary.
			fieldLength = dataLength;
			
            dataType = determinePacking(fieldDefinition, groupTable);
        }
    }
    
    /**
     * Resets the data of the Field Information for the current Field Definition
     * to default values.
     */
    private void reset() {
        fieldLevel = 0;
        fieldName = "";
        isFillerLine = false;
        hasPictureClause = false;
        dataType = "ALPHANUM";
        dataLength = 0;
        dataScale = 0;
        nbrOfOccurs = 1;
        redefinedFieldName = "";
        occursDependingOn = "";
    }
    
    /**
     * Perform processing to return the source code for next Field Definition with a
     * valid level. Only valid levels between 1 through 49 and 77 will be returned
     * for processing. Field Definition Entries are not required for the other levels.
     *
     * @return - (String) Returns the source for the next Field Definition of the
     *           copybook to be processed
     */
    private String getValidLevelFieldDefinition()
    throws CopybookInterpreterException {
        int nextLevel = -1;
        boolean validLevel = false;
        String fieldDefinitionSource = null;
        while (!validLevel) {
            fieldDefinitionSource = retrieveNextFieldDefinition();
            if (copybookFile.isCopyStatement()) {
                validLevel = true;
            }
            else if (fieldDefinitionSource != null) {
                nextLevel = determineLevel(fieldDefinitionSource);
                if (((nextLevel > 0)
                &&   (nextLevel < 50))
                ||   (nextLevel == 77)) {
                    fieldLevel = nextLevel;
                    validLevel = true;
                }
            }
            else if (copybookFile.isEndOfCopybook()) {
                validLevel = true;
            }
        }
        // return next Field Defintion to be processed
        return fieldDefinitionSource;
    }
    
    /**
     * Performs processing to obtain the next complete Field Definition source.
     * If no more copybook lines exist, null is returned.
     *
     * @return - (String) Returns the complete source code that defines the next
     *           Field Definition of the copybook
     */
    private String retrieveNextFieldDefinition()
    throws CopybookInterpreterException {
        int lineLevel = -1;
        boolean completedFieldInformation = false;
        String fieldInformationSource = "";
        String nextValidCopybookLine = "";
        
        // end of Copybbook reached on last read
        if (copybookFile.isEndOfCopybook()) {
            return null;
        }
        if (copybookFile.getHoldCopybookLine() == null) {
            // read the next copybook line to be processed
            fieldInformationSource = copybookFile.readNextValidCopybookLine();
            // is the end of the copybok reached
            if (copybookFile.isEndOfCopybook()) {
                return null;
            }
        }
        else {
            // load the last copybook line read the hasn't been processed yet
            fieldInformationSource = copybookFile.getHoldCopybookLine();
        }
        
        while (!completedFieldInformation) {
            // verify if last character is a period
            if (fieldInformationSource.endsWith(".")) {
                // Remove the ending period and return complete source code
                // defining the next Field Definition
                int sourceLength = fieldInformationSource.length();
                fieldInformationSource = fieldInformationSource.substring(0, sourceLength - 1);
                copybookFile.setHoldCopybookLine(null);
                completedFieldInformation = true;
            }
            else {
                // read the next copybook line
                nextValidCopybookLine = copybookFile.readNextValidCopybookLine();
                // is the end of the copybook reached
                if (nextValidCopybookLine == null) {
                    completedFieldInformation = true;
                }
                else {
                    // determine if a level exists on the next line
                    lineLevel = determineLevel(nextValidCopybookLine);
                    if (lineLevel == -1) {
                        // no level exists, concatenate the copybook source code
                        fieldInformationSource = fieldInformationSource.concat(" ");
                        fieldInformationSource = fieldInformationSource.concat(nextValidCopybookLine);
                    }
                    else {
                        // a level exists, store line so it can be processed later
                        copybookFile.setHoldCopybookLine(nextValidCopybookLine);
                        completedFieldInformation = true;
                    }
                }
            }
        }
        return fieldInformationSource;
    }
    
    private String retrieveCopyStatementCopybook(String copybookSourceLine) {
        int copyStatementIndex = copybookSourceLine.indexOf("COPY ");
        String stmt = copybookSourceLine.substring(copyStatementIndex + 5).trim();
        if (stmt.indexOf(" ") > 0)
        	stmt = stmt.substring(0, stmt.indexOf(" "));
        return stmt;
    }
    
    /**
     * Determines if a Level Number exists in the Field Definition.  If a Level
     * Number does exist, it is returned, else -1 is returned.
     *
     * @param fieldDefinitionSource - (String) Source code defining field of copybook
     * @return - (int) If a level exists the level is returned else -1 is returned
     */
    private int determineLevel(String fieldDefinitionSource) {
        // locate the index of the first space
        int levelLength = fieldDefinitionSource.indexOf(' ');
        // if no space is found, then assume there is no level
        if (levelLength == -1) {
            //levelLength = fieldDefinitionSource.length();
        	return -1;
        }
        // create a string of the possible level
        String fieldDefinitionLevel = fieldDefinitionSource.substring(0 , levelLength);
        //verify if it is an integer
        if (isInteger(fieldDefinitionLevel)) {
            // convert the string to an integer
            int level = Integer.parseInt(fieldDefinitionLevel);
            // if it is a valid level entry, return the level value
            if (((level > 0 )
            &&   (level < 50))
            ||  (level == 66)
            ||  (level == 77)
            ||  (level == 78)
            ||  (level == 88))
                return level;
        }
        return -1;
    }
    
    /**
     * Determines the Field Name of the Field Definition.  If a Field Name exists,
     * it is returned, else the Field Name is assigned to be FILLER.
     *
     * @param fieldDefinitionSource - (String) Source code defining field of copybook
     * @return - (String) Returns the field name of the Field Definition
     */
    private String determineFieldName(String fieldDefinitionSource) {
        String fillerString = "FILLER";
        // create a String representation of the level
        String levelString = Integer.toString(fieldLevel);
        // find index of the fieldLevel
        int indexLoc = fieldDefinitionSource.indexOf(levelString);
        // if the index plus the length of the level is equal to the Field
        // Definition length, then the field is FILLER
        indexLoc = indexLoc + levelString.length();
        if (indexLoc == fieldDefinitionSource.length()) {
            isFillerLine = true;
            return fillerString;
        }
        // create a string representing of possible field name
        String fieldNameSource = fieldDefinitionSource.substring(indexLoc).trim();
        // locate the end of the possible field name
        indexLoc = fieldNameSource.indexOf(' ');
        if (indexLoc > -1) {
            fieldNameSource = fieldNameSource.substring(0, indexLoc);
        }
        // if possible field name is reserve data description syntax, the
        // Field Definition defines FILLER
        if ((fieldNameSource.equals(fillerString))
        ||  (fieldNameSource.equals("REDEFINES"))
        ||  (fieldNameSource.equals("EXTERNAL"))
        ||  (fieldNameSource.equals("GLOBAL"))
        ||  (fieldNameSource.equals("PICTURE"))
        ||  (fieldNameSource.equals("PIC"))
        ||  (fieldNameSource.equals("OCCURS"))
        ||  (fieldNameSource.equals("SIGN"))
        ||  (fieldNameSource.equals("SYNCHRONIZED"))
        ||  (fieldNameSource.equals("SYNC"))
        ||  (fieldNameSource.equals("JUSTIFIED"))
        ||  (fieldNameSource.equals("JUST"))
        ||  (fieldNameSource.equals("BLANK"))
        ||  (fieldNameSource.equals("VALUE"))
        ||  (fieldNameSource.equals("IS"))
        ||  (isSpecialUsage(fieldNameSource))) {
            isFillerLine = true;
            return fillerString;
        }
        // return the field name of the Field Definition
        return fieldNameSource;
    }
    
    /**
     * Determines if the Field Definition contains an Occurs Depending clause.
     * If so the name of the field that dictates the number of occurs is returned.
     *
     * @param fieldDefinitionSource - (String) Source code defining field of copybook
     * @return - (String) Field Name of the Occurs Depending On clause
     */
    private String determineOccursDependingOn(String fieldDefinitionSource) 
    throws CopybookInterpreterException {
        
        // locate the start of Occurs clause
        String occursString = " OCCURS ";
        int indexLoc = fieldDefinitionSource.indexOf(occursString);
        // if no " OCCURS " Clause, return empty string
        if (indexLoc == -1) {
            return "";
        }
        
        // create number of times source code 
        String occursTimesSource = fieldDefinitionSource.substring(indexLoc + occursString.length());
        String dependingString = " DEPENDING ";
        indexLoc = occursTimesSource.indexOf(dependingString);
        // if no " DEPENDING " Clause, return empty string since no Depending On
        if (indexLoc == -1) {
            return "";
        }
        occursTimesSource = occursTimesSource.substring(0,indexLoc);
        
        // locate " TO " source if coded in the number of times source code
        String toString = " TO ";
        indexLoc = occursTimesSource.indexOf(toString);
        // if " TO " Clause exist, remove it to locate maximum number of occurs
        if (indexLoc > -1) {
            // if " TO " source exists, remove the " TO " clause
            occursTimesSource = occursTimesSource.substring(indexLoc + toString.length());
        }
        
        // trim the source code to key in on times integer in code
        occursTimesSource = occursTimesSource.trim();
        
        // locate the end of the maximum number of occurs
        indexLoc = occursTimesSource.indexOf(' ');
        occursTimesSource = occursTimesSource.substring(0, indexLoc);
        // verify that number of occurs is an integer
        if (isInteger(occursTimesSource)) {
            // convert the string to an integer
            nbrOfOccurs = Integer.parseInt(occursTimesSource);
        }
        else {
            // generate exception for missing maximum number of occurs
            String ciException = "Maximum number of Occurences missing: " 
                                  + copybookName + " "  
                                  + fieldName;
            log.info(ciException);
            throw new CopybookInterpreterException(ciException);
        }
                       
        // locate the end of Depending clause
        indexLoc = fieldDefinitionSource.indexOf(dependingString) + dependingString.length();
        
        // create a string representing the rest of the copybook line
        String occursDependingSource = fieldDefinitionSource.substring(indexLoc).trim();
        // verify if the ON is coded as part of the Depending clause and if so remove
        if ((occursDependingSource.length() > 2)
        &&  (occursDependingSource.substring(0,3).equals("ON "))) {
            // if ON utilized, remove "ON " source
            occursDependingSource = occursDependingSource.substring(3);
        }
        // locate the end of the Occurs Depending On Field Name
        indexLoc = occursDependingSource.indexOf(' ');
        if (indexLoc == -1) {
            return occursDependingSource;
        }
        return occursDependingSource.substring(0, indexLoc);
    }
    
    /**
     * Determines if the Field Definition contains an Occurs clause.
     * If so the number of occurences of the field definition isreturned.
     *
     * @param fieldDefinitionSource - (String) Source code defining field of copybook
     * @return - (int) Number of times the Field Occurs
     */
    private int determineNbrOfOccurs(String fieldDefinitionSource) 
    throws CopybookInterpreterException {
        
        // if an occurs depending on clause exists, there cannot be an occurs clause,
        // so return the number of occurs computed in Depending On logic
        if (occursDependingOn.length() > 0) {
            return nbrOfOccurs;
        }
        // locate the start of Occurs clause
        String occursString = " OCCURS ";
        int indexLoc = fieldDefinitionSource.indexOf(occursString);
        // if no Occurs Clause, return 1 representing no occurs clause
        if (indexLoc == -1) {
            return 1;
        }
        // create a string representing the source that contains the number of occurs
        String occursSource = fieldDefinitionSource.substring(indexLoc + occursString.length()).trim();
        // locate the end of the number of occurs
        indexLoc = occursSource.indexOf(' ');
        if (indexLoc > -1) {
            occursSource = occursSource.substring(0, indexLoc);
        }
        // verify that number of occurs is an integer
        if (isInteger(occursSource)) {
            // convert the string to an integer
            return Integer.parseInt(occursSource);
        }
        // generate exception for missing number of occurs
        String ciException = "Number of Occurences missing: " 
                             + copybookName + " "  
                             + fieldName;
        log.info(ciException);
        throw new CopybookInterpreterException(ciException);
    }
    
    /**
     * Determines if the Field Definition contains a Redefines clause and if so,
     * returns the name of the field that is redefined is returned.
     *
     * @param fieldDefinitionSource - (String) Source code defining field of copybook
     * @return - (String) Field Name of field being redefined
     */
    private String determineRedefinedFieldName(String fieldDefinitionSource) {
        String redefinesString = " REDEFINES ";
        // locate the start of Redefines clause
        int indexLoc = fieldDefinitionSource.indexOf(redefinesString);
        // if no Redefines clause, return empty string
        if (indexLoc == -1) {
            return "";
        }
        // create a string representing the rest of the copybook line
        String redefinesSource = fieldDefinitionSource.substring(indexLoc + redefinesString.length()).trim();
        // locate the end of the Field Name being redefined
        indexLoc = redefinesSource.indexOf(' ');
        // if no space is found, remainder of the line is redefined field name
        if (indexLoc == -1) {
            return redefinesSource;
        }
        // return the reefined field name
        return redefinesSource.substring(0, indexLoc);
    }
    
    /**
     * Determines if the Field Definition contains a Picture clause.
     *
     * @param fieldDefinitionSource - (String) Source code defining field of copybook
     * @return - (boolean) Returns a boolean value of true if the copybook line
     *           contains a Picture clause
     */
    private boolean determineIfPictureClause(String fieldDefinitionSource) {
        if (fieldDefinitionSource.indexOf(" PIC ") != -1) {
            return true;
        }
        else if (fieldDefinitionSource.indexOf(" PICTURE ") != -1) {
            return true;
        }
        return false;
    }
    
    /**
     * Performs analysis on the source code for a Field Definition in order to parse out
     * the character-string of the Picture Clause.
     *
     * @param fieldDefinitionSource - (String) Source code defining field of copybook
     */
    private void processPictureCharacterString(String fieldDefinitionSource) 
    throws CopybookInterpreterException {

        String characterString = "";
        // locate the start of Picture clause
        String pictureString = " PIC ";
        int indexLoc = fieldDefinitionSource.indexOf(pictureString);
        // if no Pic string locate the Picture string
        if (indexLoc == -1) {
            pictureString = " PICTURE ";
            indexLoc = fieldDefinitionSource.indexOf(pictureString);
            // create a string representing the rest of the copybook line
            characterString = fieldDefinitionSource.substring(indexLoc + pictureString.length()).trim();
        }
        else {
            // create a string representing the rest of the copybook line
            characterString = fieldDefinitionSource.substring(indexLoc + pictureString.length()).trim();
        }
        // verify if the IS string is coded as part of the picture clause and if so remove
        if ((characterString.length() > 2)
        &&  (characterString.substring(0,3).equals("IS "))) {
            // if IS utilized, remove "IS " from the remaining source
            characterString = characterString.substring(3);
        }
        // locate the end of the picture character-string
        indexLoc = characterString.indexOf(' ');
		String alphaNumString = characterString;
		if (indexLoc != -1) {
			alphaNumString = characterString.substring(0, indexLoc);
        }
        // if the character-string contains an "A" or a "X" then the character-
        // string defines an alphanumeric field
        if ((alphaNumString.indexOf('X') > -1)
        ||  (alphaNumString.indexOf('A') > -1)) {
            parseAlphanumericPicture(alphaNumString);
        }
        else {
            parseNumericPicture(characterString);
        }
    }
    
    /**
     * Performs analysis on a Picture clause whose character-string represents a
     * alphanumeric dataType in order to populate the dataType, dataLength, and
     * dataScale properties. The dataType for an alphanumeric Field Definition will
     * be equal to ALPHANUMERIC and the dataScale will be 0.
     *
     * @param characterString - (String) CharacterString representing Picture clause for
     *                          the current Field Definition
     */
    private void parseAlphanumericPicture(String characterString) 
    throws CopybookInterpreterException {
        
        dataType = "ALPHANUM";
        dataScale = 0;
        dataLength = 0;
        int endParenLoc = -1;
        String replicationString = "";
        
        // process each symbol that exists in the character-string
        for (int i=0; i<characterString.length(); i++) {
            // determine if replication is utilized
            if (characterString.charAt(i) == '(') {
                // replication is utilized, locate index of the ending parenthesis
                endParenLoc = characterString.indexOf(')',i);
                if (endParenLoc > -1) {
                    // verify that the replication value is an integer
                    replicationString = characterString.substring(i + 1, endParenLoc);
                    if (isInteger(replicationString)) {
                        // convert the string to an integer and add result less 1 to dataLength
                        // subtracting 1 do to count of original symbol
                        dataLength = dataLength + Integer.parseInt(replicationString) - 1;
                        i = endParenLoc;
                    }
                    // reset pointer for characterString
                    else { 
                        // generate exception for non numeric replication string
                        String ciException = "Invalid replication number in picture: " 
                                             + copybookName + " "  
                                             + fieldName;
                        log.info(ciException);
                        throw new CopybookInterpreterException(ciException);
                    }
                }
                else { 
                    // generate exception for missing closing parenthesis
                    String ciException = "Missing closing parenthesis: " 
                                         + copybookName + " "  
                                         + fieldName;
                    log.info(ciException);
                    throw new CopybookInterpreterException(ciException);
                }
            }
            else {
                // add 1 to dataLength to account for occupied character position
                dataLength++;
            }
        }
    }
    
    /**
     * Performs analysis on a Picture clause whose character-string represents a
     * numeric dataType in order to populate the dataType, dataLength, and dataScale
     * properties.
     *
     * @param characterString - (String) CharacterString representing Picture clause for
     *                          the current Field Definition
     */
    private void parseNumericPicture(String characterString) 
    throws CopybookInterpreterException {
        
        dataType = "NUMERIC";
        dataScale = 0;
        dataLength = 0;
        int endParenLoc = -1;
        String replicationString = "";
        boolean accumScale = false;
        char character;
        boolean separateSign = false;
        
		if (characterString.indexOf("SEPARATE") != -1) {
			separateSign = true;
			if (characterString.indexOf("TRAILING") != -1) {
				dataType = "SNUMTRAL";
			} else {
				dataType = "SNUMLEAD";
			}
		}
		if (characterString.indexOf(" ") != -1) {
			characterString = characterString.substring(0, characterString.indexOf(" "));
		}
        
        // process each symbol that exists in the character-string
        for (int i=0; i<characterString.length(); i++) {
            character = characterString.charAt(i);
            if (character == 'S' && !separateSign) {
                dataType = "SNUMERIC";
            }
            else if ((character == 'P')
            ||  (character == '.')
            ||  (character == 'V')) {
                accumScale = true;
            }
            else if (character == ')') {
            	continue;
            }
            else if (character == '(') {
            	//parenthesis specify length, so reset accumulated dataLength to 0
            	if (!accumScale) {
	            	dataLength = 0;
            	} else {
            		dataLength--;
            		dataScale--;
	            }
                // locate index of the ending parenthesis
                endParenLoc = characterString.indexOf(')',i);
                if (endParenLoc > -1) {
                    // verify that the replication value is an integer
                    replicationString = characterString.substring(i + 1, endParenLoc);
                    if (isInteger(replicationString)) {
                        // convert the string to an integer 
                        int replicationNbr = Integer.parseInt(replicationString);
                        dataLength = dataLength + replicationNbr;
                        i = endParenLoc;
                        if (accumScale) {
                            dataScale = dataScale + replicationNbr;
                        }
                    }
                    else { 
                        // generate exception for non numeric replication string
                        String ciException = "Invalid replication number in picture: " 
                                             + copybookName + " "  
                                             + fieldName;
                        log.info(ciException);
                        throw new CopybookInterpreterException(ciException);
                    }
                }
                else { 
                    // generate exception for missing closing parenthesis
                    String ciException = "Missing closing parenthesis: " 
                                         + copybookName + " "  
                                         + fieldName;
                    log.info(ciException);
                    throw new CopybookInterpreterException(ciException);
                }
            }
            else {
                // add 1 to dataLength to account for occupied character position
                dataLength++;
                // if scale start has been reached, add 1 to scale
                if (accumScale) {
                    dataScale++;
                }
            }
        }
        
        // if the sign is separate, add 1 to dataLength
        if (separateSign)
        	dataLength++;
    }
  
    /**
     * Determines if a Usage clause is utilized to format storage of the data for
     * a Field Definition.  If a field special usage is utilized it will override
     * the data type of the picture clause.
     *
     * @param fieldDefinitionSource - (String) Source code defining field of copybook
     * @param groupTable - (GroupTable) Group information for current fields of copybook
     * @return - (String) Returns the special usage if one is utilized
     */
    private String determinePacking(String fieldDefinitionSource, GroupTable groupTable) {
        // create signed component to concatenate to packing information
        String signedString = "";
        if (dataType.equals("SNUMERIC")) {
            signedString = "S";
        }
        
        // access the data type from the group record for Field Definition
        String groupDataType = groupTable.getParentGroupDataType();
        // if the parent group data type is not null then utilize the special usage
        // as the data type of this group entry
        if (groupDataType != null) {
            if (groupDataType.equals("BINARY")) {
                adjustLengthBinary();
            }
            else if (groupDataType.equals("PACKED")) {
                adjustLengthPacked();
            }
            return signedString.concat(groupDataType);
        }
  
        // create packing source to analyze for restricted usages
        String packingSource = fieldDefinitionSource.concat(" ");
        
        // check for all special usage types,  if found pass back
        if ((packingSource.indexOf(" COMP ") > -1)
        ||  (packingSource.indexOf(" COMPUTATIONAL ") > -1)
        ||  (packingSource.indexOf(" BINARY ") > -1)
        ||  (packingSource.indexOf(" COMPUTATIONAL-4 ") > -1)
        ||  (packingSource.indexOf(" COMP-4 ") > -1)) {
            adjustLengthBinary();
            return signedString.concat("BINARY");
        }
        if ((packingSource.indexOf(" COMPUTATIONAL-3 ") > -1)
        ||  (packingSource.indexOf(" COMP-3 ") > -1)
        ||  (packingSource.indexOf(" PACKED-DECIMAL ") > -1)) {
            adjustLengthPacked();
            return signedString.concat("PACKED");
        }
        if ((packingSource.indexOf(" COMPUTATIONAL-1 ") > -1)
        ||  (packingSource.indexOf(" COMP-1 ") > -1)) {
            return signedString.concat("COMP-1");
        }
        if ((packingSource.indexOf(" COMPUTATIONAL-2 ") > -1)
        ||  (packingSource.indexOf(" COMP-2 ") > -1)) {
            return signedString.concat("COMP-2");
        }
        if ((packingSource.indexOf(" COMPUTATIONAL-5 ") > -1)
        ||  (packingSource.indexOf(" COMP-5 ") > -1)
        ||  (packingSource.indexOf(" COMPUTATIONAL-X ") > -1)
        ||  (packingSource.indexOf(" COMP-X ") > -1)) {
            return signedString.concat("COMP-X");
        }
        if (packingSource.indexOf(" INDEX ") > -1) {
            return signedString.concat("INDEX");
        }
        if ((packingSource.indexOf(" DISPLAY ") > -1)
        ||  (packingSource.indexOf(" DISPLAY-1 ") > -1)) {
            return dataType;
        }
        if (packingSource.indexOf(" POINTER ") > -1) {
        	adjustLengthPointer();
            return signedString.concat("POINTER");
        }
        if (packingSource.indexOf(" PROCEDURE-POINTER ") > -1) {
            return signedString.concat("PROCPTR");
        }
        return dataType;
    }
    
    /**
     * Adjusts the dataLength property for the special Usage storage format of BINARY.
     */
    private void adjustLengthBinary() {

        if (dataLength > 0) {
            if (dataLength < 5) {
                dataLength = 2;
            }
            else if (dataLength < 10) {
                dataLength = 4;
            }
            else {
                dataLength = 8;
            }
        }
    }
    
    /**
     * Adjusts the dataLength property for the special Usage storage format is PACKED.
     */
    private void adjustLengthPacked() {
        if (dataLength > 0) {
            dataLength = dataLength / 2;
            dataLength++;
        }
    }
    
    /**
     * Adjusts the fieldLength and dataLength to 4 for the Usage storage format POINTER.
     */
    private void adjustLengthPointer() {
    	fieldLength = 4;
    	dataLength = 4;
    }
    
    /**
     * Determines if a special usage is utilized for the data type.  If a special usage
     * of data type is utilized the boolean true is returned, else false is returned.
     *
     * @param dataTypeSource - (String) Data Type of the Field Definition
     * @return - (boolean) Returns true if the data type parameter is that of a
     *           special usage
     */
    protected boolean isSpecialUsage(String dataTypeSource) {
        if ((dataTypeSource.equals("BINARY"))
        ||  (dataTypeSource.equals("SBINARY"))
        ||  (dataTypeSource.equals("PACKED"))
        ||  (dataTypeSource.equals("SPACKED"))
        ||  (dataTypeSource.equals("COMP-1"))
        ||  (dataTypeSource.equals("SCOMP-1"))
        ||  (dataTypeSource.equals("COMP-2"))
        ||  (dataTypeSource.equals("SCOMP-2"))
        ||  (dataTypeSource.equals("COMP-X"))
        ||  (dataTypeSource.equals("SCOMP-X"))
        ||  (dataTypeSource.equals("INDEX"))
        ||  (dataTypeSource.equals("SINDEX"))
        ||  (dataTypeSource.equals("POINTER"))
        ||  (dataTypeSource.equals("SPTR"))
        ||  (dataTypeSource.equals("PROCPTR"))
        ||  (dataTypeSource.equals("SPROCPTR"))) {
            return true;
        }
        return false;
    }
    
    /**
     * Check if the string is an integer. Return true if true, else return false.
     *
     * @param stringValue - (String) String to be chacked if it is an Integer
     */
    private boolean isInteger(String stringValue) {
        try {
            Integer.parseInt(stringValue);
            return true;
        }
        catch (NumberFormatException e) {
            return false;
        }
    }
    
    /**
     * Access method for the copybookName property which represents the name of
     * copybook being interpreted.
     *
     * @return - (String) Value of copybookName property
     */
    protected String getCopybookName() {
        return copybookName;
    }
    
    /**
     * Access method for the fieldLevel property which represents the level at
     * which the Field Information is defined at.
     *
     * @return - (int) Value of fieldLevel property
     */
    protected int getFieldLevel() {
        return fieldLevel;
    }
    
    /**
     * Access method for the fieldName property which represents the Field Name
     * for the current Field Information.
     *
     * @return - (String) Value of fieldName property
     */
    protected String getFieldName() {
        return fieldName;
    }
    
    /**
     * Access method for the dataType property which represents the format of the
     * data for the current Field Information.
     *
     * @return - (String) Value of dataType property
     */
    protected String getDataType() {
        return dataType;
    }
    
    /**
     * Access method for the dataLength property which represents the length of the
     * data for the current Field Information.
     *
     * @return - (int) Value of length property
     */
    protected int getDataLength() {
        return dataLength;
    }

    protected int getFieldLength() {
        return fieldLength;
    }
    
    /**
     * Access method for the dataScale property which represents the number of decimal
     * places for the current Field Information.
     *
     * @return - (int) Value of scale property
     */
    protected int getDataScale() {
        return dataScale;
    }
    
    /**
     * /**
     * Access method for the nbrOfOccurs property which represents the number of
     * times the current Field Information.
     *
     * @return - (int) Value of nbrOfOccurs property
     */
    protected int getNbrOfOccurs() {
        return nbrOfOccurs;
    }
    
    /**
     * Access method for the hasPictureClause property which represents whether or
     * not the current Field Information has a Picture Clause.
     *
     * @return - (boolean) Value of hasPictureClause property
     */
    protected boolean getHasPictureClause() {
        return hasPictureClause;
    }
    
    /**
     * Access method for the redefinedFieldName property which represents the Field
     * Name that the current Field Information redefines.
     *
     * @return - (String) Value of redefinedFieldName property
     */
    protected String getRedefinedFieldName() {
        return redefinedFieldName;
    }
    
    /**
     * Access method for the occursDependingOn property which represents the field
     * name that dictates the number of Occurences of the current Field Information.
     *
     * @return - (String) Value of occursDependingOn property
     */
    protected String getOccursDependingOn() {
        return occursDependingOn;
    }
    
    /**
     * Access method for the isFillerLine property which represents whether the
     * the current Field Information defines Filler.
     *
     * @return - (boolean) Value of isFillerLine property
     */
    protected boolean getIsFillerLine() {
        return isFillerLine;
    }
    
    protected String getCopyStatementCopybookName() {
        return copyStatementCopybookName;
    }
}
