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
 * The ResultComponent class is a type save ENUM convenience class used to
 *  represent the allowable component types for fsConnectEnterprise.
 */
public class ResultComponent implements Serializable {
    /**
     * Description of the Field
     */
    public static final ResultComponent BASE = new ResultComponent("BASE", 100);
    /**
     * Description of the Field
     */
    public static final ResultComponent CONFIG = new ResultComponent("CONFIG", 150);
    /**
     * Description of the Field
     */
    public static final ResultComponent OBJMGMT = new ResultComponent("OBJMGMT", 200);
    /**
     * Description of the Field
     */
    public static final ResultComponent RULES = new ResultComponent("RULES", 250);
    /**
     * Description of the Field
     */
    public static final ResultComponent SVCLOC = new ResultComponent("SVCLOC", 300);
    /**
     * Description of the Field
     */
    public static final ResultComponent SERVICE = new ResultComponent("SERVICE", 350);
    /**
     * Description of the Field
     */
    public static final ResultComponent SYSACC = new ResultComponent("SYSACC", 400);
    /**
     * Description of the Field
     */
    public static final ResultComponent RADAPT = new ResultComponent("RADAPT", 450);
    /**
     * Description of the Field
     */
    public static final ResultComponent SESSION = new ResultComponent("SESSION", 500);
    /**
     * Description of the Field
     */
    public static final ResultComponent DEVTOOL = new ResultComponent("DEVTOOL", 550);
    /**
     * Description of the Field
     */
    public static final ResultComponent IMPL = new ResultComponent("IMPL", 600);
    /**
     * Description of the Field
     */
    public static final ResultComponent SUPPORT = new ResultComponent("SUPPORT", 650);
    private final String componentName;
    private final int componentID;
    private final ResultComponent[] COMPONENT_VALUES = {BASE, CONFIG, OBJMGMT, RULES, SVCLOC, SERVICE, SYSACC, RADAPT, SESSION, DEVTOOL, IMPL, SUPPORT};
    private static int nextOrdinal = 0;
    private final int ordinal = nextOrdinal++;
    /**
     *Constructor for the ResultComponent object
     *
     * @param name         Description of Parameter
     * @param componentID  Description of Parameter
     */
    public ResultComponent(String name, int componentID) {
        this.componentName = name;
        this.componentID = componentID;
    }
    /**
     * Gets the componentID attribute of the ResultComponent object
     *
     * @return   The componentID value
     */
    public final int getComponent() {
        return this.componentID;
    }
    /**
     * Compares objects for equality
     *
     * @param o  The object to be compared to "this"
     * @return   boolean indicating true if object parameter matches "this" or
     *                         indicating false if object parameter does not match "this"
     */
    public final boolean equals(Object o) {
        return super.equals(o);
    }
    /**
     * Returns the hashCode
     *
     * @return   The hashCode
     */
    public final int hashCode() {
        return super.hashCode();
    }
    /**
     * This method is necessary for serialization.
     * It is REQUIRED that each subclass of ResultComponent
     * implement this method individually.
     *
     * @return                           object
     * @exception ObjectStreamException  Description of Exception
     */
    private Object readResolve() throws ObjectStreamException {
        return COMPONENT_VALUES[ordinal];
    }
    /**
     * ResultComponent toString override
     *
     * @return   Description of the Returned Value
     */
    public String toString() {
        return this.componentName;
    }
}
