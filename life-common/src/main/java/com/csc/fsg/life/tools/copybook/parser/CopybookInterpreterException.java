/**Copybook Interpreter Exception class
 * CopybookInterpreterException.java
 *
 * Created on December 2001
 */

package com.csc.fsg.life.tools.copybook.parser;

public class CopybookInterpreterException 
    extends java.lang.Exception {

    /**
     * Creates new CopybookInterpreterException without detail message.
     */
    public CopybookInterpreterException() {
    }

    /**
     * Constructs an CopybookInterpreterException with the specified detail message.
     * 
     * @param message - (String) the exception detail message.
     */
    public CopybookInterpreterException(String message) {
        super(message);
    }
    
}
