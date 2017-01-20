/*
 *  This software and/or documentation contains trade secrets and
 *  confidential information, which are proprietary to
 *  Computer Sciences Corporation.
 *  The use, reproduction, distribution, or disclosure of this
 *  software and/or documentation, in whole or in part, without the express
 *  written permission of Computer Sciences Corporation is prohibited.
 *  This software and/or documentation is also an unpublished work protected
 *  under the copyright laws of the United States of America and other countries.
 *  If this software and/or documentation becomes published, the following
 *  notice shall apply:
 *
 *  Copyright 2004 Computer Sciences Corporation. All Rights Reserved.
 */
package com.csc.fsg.life.rules;
import java.io.ObjectStreamException;
import java.io.Serializable;
/**
 * This class is a type safe enumeration of the severity levels allowed
 * in ResultMessage.
 */
public class ResultType implements Serializable {
    /**
     * Description of the Field
     */
    public static final ResultType INFORMATION =
            new ResultType("INFORMATION", 0);
    /**
     * Description of the Field
     */
    public static final ResultType WARNING = new ResultType("WARNING", 1);
    /**
     * Description of the Field
     */
    public static final ResultType ERROR = new ResultType("ERROR", 2);
    /**
     * Description of the Field
     */
    public static final ResultType SEVERE = new ResultType("SEVERE", 3);
    private final String typeName;
    private final int typeValue;
    private static int nextOrdinal = 0;
    private final int ordinal = nextOrdinal++;
    private static final ResultType[] RESULT_TYPE_VALUES =
            {INFORMATION, WARNING, ERROR, SEVERE};
    private ResultType(String name, int value) {
        this.typeName = name;
        this.typeValue = value;
    }
    /**
	   Overrides Object.equals() method.
	   @param o  Description of Parameter
	   @return   Description of the Returned Value
	*/
    public final boolean equals(Object o) {
        return super.equals(o);
    }
    /**
	   @return   Description of the Returned Value
     */
    public final int hashCode() {
        return super.hashCode();
    }
    /**
	   Gets the Type attribute of the ResultType object
	   @return   The Type value
	*/
    public final int getType() {
        return this.typeValue;
    }
    private Object readResolve() throws ObjectStreamException {
        return RESULT_TYPE_VALUES[ordinal];
    }
    /**
	   @return   Description of the Returned Value
     */
    public String toString() {
        return typeName;
    }
}
