package com.csc.fsg.life.tools.copybook.parser;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/* Modifications: T0150 */

/**
 * Copybook File class is utilized to perform IO processing against the Copybook
 * File. As each line of the copybook is read it is determined if the line is a
 * valid line of code which needs to analyzed by the copybook interpretation 
 * process.  Lines of code that are determined to be Empty Lines, Comment Lines,
 * Skip Lines, and Eject Lines are excluded from being analyzed.  This class also 
 * strips line numbers and unused line information by only acknowledging code 
 * between the 6th and 72nd characters of a line of the copybook.
 */
public class CopybookFile {

    private String copybookName = "";
    private boolean endOfCopybook = false;
    private boolean copyStatement = false;
    private FileReader copybookReader;
    private BufferedReader copybookLineReader;
    private String holdCopybookLine = null;
    private String rejectReason = null;
    
    // Logger
    protected static final Log log = LogFactory.getLog("com.csc.fsg.life.tools.copybook");
    
    /**
     * Constructor for the CopybookFile Class.
     *
     * @param copybookFile - (File) a text file that contains the copybook source
     */
    protected CopybookFile(File copybookFile, List<File> sourcePathList)
    	throws CopybookInterpreterException 
    {
        // Instantiate a File Reader and Buffer Reader for the copybook
        try {
            copybookReader = new FileReader(copybookFile);
            copybookLineReader = new CopybookBufferedReader(copybookReader, sourcePathList);
        
            // Load the Copybook Name
            copybookName = copybookFile.getName();
            int extensionLoc = copybookName.indexOf('.');
            if (extensionLoc != -1) {
                copybookName = copybookName.substring(0,extensionLoc).toUpperCase();
            }
        }
        catch (FileNotFoundException fnfE) {
            String fnfException = "Copybook File Not Found: " 
                                  + copybookFile.getPath() 
                                  + copybookFile.getName();
            log.info(fnfException);
            log.info(fnfE.getMessage());
            throw new CopybookInterpreterException(fnfException);
        }
    }
    
    /**
     * Returns the actual code source of a next line read of File Buffer which
     * is not a comment line or an empty line. If the eof file is reached, null
     * is returned.
     *
     * @return - (String) The next line of source code from the copybook which is
     *           not a comment line or an empty line
     */
    protected String readNextValidCopybookLine()
    	throws CopybookInterpreterException 
    {
        String nextCopybookLine = "";
        String sourceCopybookLine = "";
        boolean haveValidSourceLine = false;
               
        try {
          while (!haveValidSourceLine) {
            // read the next line from the buffer
            nextCopybookLine = copybookLineReader.readLine();
            if (nextCopybookLine == null) {
                endOfCopybook = true;
                return null;
            }
			
           
            // bypass line of copybook if the line contains only a line number,
            // or if the line is a comment line,
            // or if the line contains an eject clause,
            // or if the line contains a skip clause
            if ((nextCopybookLine.length() > 6)
            &&  (!determineIfCommentLine(nextCopybookLine))
            &&  (!determineIfEjectStmt(nextCopybookLine))
            &&  (!determineIfSkipStmt(nextCopybookLine))) {
                // obtain actual source code of the line
                sourceCopybookLine = obtainActualCode(nextCopybookLine);

                // determine if line contains any source code, if there isn't
                // any source code don't interpret line
                if (sourceCopybookLine.length() != 0) {
             	    if (copyExists(sourceCopybookLine))
                        copyStatement = true;
                    else if (level66Exists(sourceCopybookLine) 
                            || occursDependingOnExists(sourceCopybookLine))
            	 	throw new CopybookInterpreterException("Copybook " + copybookName + " rejected because " + rejectReason +  " is not supported");
              	
                    haveValidSourceLine = true;
                }
            }
          }
          return sourceCopybookLine;
        }
        catch (IOException ioE) {
        	String msg = ioE.getMessage();
            String ioException = "IO Exception while reading File: " 
                                  + copybookName;
        	if (msg != null && msg.length() != 0) {
        		ioException = msg + "\n" + ioException; 
        	}
            log.info(ioException);
            log.info(ioE.getMessage());
            throw new CopybookInterpreterException(ioException);
        }
    }
    
    /**
     * Determines if the copybook line contains a COPY statement to include another copybook
     *
     * @param copybookSourceLine - (String) A line of Source code read from the copybook buffer
     * @return - (boolean) Returns a boolean value stating whether line has a COPY statement
     */
    private boolean copyExists (String copybookSourceLine) 
    {
        if (copybookSourceLine.indexOf("COPY ") > -1 ) {
        	rejectReason = "COPY statement OR Copybook within a Copybook parsing";
            return true;
	     }
	     
         return false;
    }
    
        /**
     * Determines if the copybook line contains a level 66 renames clause to include another copybook
     *
     * @param copybookSourceLine - (String) A line of Source code read from the copybook buffer
     * @return - (boolean) Returns a boolean value stating whether line has a level 66
     */
    
    private boolean level66Exists (String copybookSourceLine) {

        int lineLength = copybookSourceLine.length();
        
        // indexOf will fail some times, for ex.. occurs 366 times will have
        // a 66 occurrence and so the method returns true
		if (copybookSourceLine.toUpperCase().substring(0,lineLength).startsWith("66 ") ) { 	    	
        	String srcLine = copybookSourceLine.toUpperCase().substring(0,lineLength);
        	rejectReason = "Level 66 or renames clause : "+srcLine;
            return true;
        }
        
        return false;
    }
    
    
     /* Determines if the copybook line contains a OCCURS DEPENDING ON clause
     *
     * @param copybookSourceLine - (String) A line of Source code read from the copybook buffer
     * @return - (boolean) Returns a boolean value stating whether line has a DEPENDING ON clause
     */
    
    private boolean occursDependingOnExists (String copybookSourceLine) 
    {
        int lineLength = copybookSourceLine.length();
               
 	    if (copybookSourceLine.toUpperCase().substring(0,lineLength).indexOf(" DEPENDING ON ") > -1 ) {
        	rejectReason = "OCCURS DEPENDING ON";
            return true;
        }
        
        return false;
    }
    
   
    /**
     * Returns the actual code source of a full line of text from a copybook converted
     * to Upper Case. The actual code consists of the text from position 6 through the
     * end of the line or position 70, which ever comes first.
     *
     * @param copybookLine - (String) Full Line of Text from a copybook
     * @return - (String) A line of code of positions 6 thru the earlier of
     *           the end of the copybook line or position 70
     */
    private String obtainActualCode(String copybookLine) 
    {
        int lineLength = copybookLine.length();
        if (lineLength > 72) {
            lineLength = 72;
        }
        return copybookLine.toUpperCase().substring(6, lineLength).trim();
    }
    
    /**
     * Determines if the copybook line is a comment line.
     *
     * @param copybookSourceLine - (String) A line of Source code read from the copybook buffer
     * @return - (boolean) Returns a boolean value stating whether line is a comment line
     */
    private boolean determineIfCommentLine(String copybookSourceLine) 
    {
        if (copybookSourceLine.charAt(6) == '*' || copybookSourceLine.charAt(0) == '*') {
            return true;
        }
        return false;
    }
    
    /**
     * Determines if the any source code contains the eject statement
     *
     * @param copybookSourceLine - (String) A line of Source code read from the copybook buffer
     * @return - (boolean) Returns a boolean value stating whether or not the line contains
     *           an eject statement
     */
    private boolean determineIfEjectStmt(String copybookSourceLine) 
    {
        if (copybookSourceLine.indexOf(" EJECT ") > -1) {
            return true;
        }
        if (copybookSourceLine.indexOf(" EJECT.") > -1) {
            return true;
        }
        return false;
    }
    
    /**
     * Determines if the any source code contains the skip statement
     *
     * @param copybookSourceLine - (String) A line of Source code read from the copybook buffer
     * @return - (boolean) Returns a boolean value stating whether or not the line contains
     *           a skip statement
     */
    private boolean determineIfSkipStmt(String copybookSourceLine) 
    {
        if (copybookSourceLine.indexOf(" SKIP ") > -1) {
            return true;
        }
        if (copybookSourceLine.indexOf(" SKIP.") > -1) {
            return true;
        }
        return false;
    }
    
    /**
     * Access method for the copybookName property which represents the name of the
     * copybook being interpreted.
     *
     * @return - (String) Name of the Copybook property
     */
    protected String getCopybookName() {
        return copybookName;
    }
    
    /**
     * Access method for the endOfCopybook property which represents whether or not
     * all fields of the copybook have been processed.
     *
     * @return - (boolean) End of the Copybook marker property
     */
    protected boolean isEndOfCopybook() {
        return endOfCopybook;
    }
    
    /**
     * Access method for the copyStatement property which represents when
     * a copy statement has been reached in the copybook.
     *
     * @return - (boolean) Copy statement marker property
     */
    protected boolean isCopyStatement() {
        return copyStatement;
    }
    
    protected void clearCopyStatement() {
        copyStatement = false;
    }
    
    /**
     * Access method for the holdCopybookLine property which retains a line of the
     * copybook that still needs to be processed.
     *
     * @return - (String) Copybook line that has been stored
     */
    protected String getHoldCopybookLine() {
        return holdCopybookLine;
    }
    
    /**
     * Sets value of holdCopybookLine property.
     *
     * @param aHoldCopybookLine Copybook line that needs to be retained
     */
    protected void setHoldCopybookLine(String aHoldCopybookLine) {
        holdCopybookLine = aHoldCopybookLine;
    }
    
    protected void close() 
    	throws CopybookInterpreterException 
    {
    	try {
            copybookReader.close();
            copybookLineReader.close();
       	}
       	catch(Exception e) {
       		e.printStackTrace();
       		throw new CopybookInterpreterException(e.getMessage());
       	}
    }
}
