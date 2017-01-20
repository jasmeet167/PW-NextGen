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
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
/**
 * This class provides the default <code>Result</code> implementation.
 * <p/>
 * This implementation is done as final class to improve performance.
 * If extensions of the <code>Result</code> functionality are required then
 * <code>ResultAbstract</code> should be subclassed or <code>Result</code>
 * should be implemented directly.
 */
@SuppressWarnings("unchecked")
public class ResultImpl 
	extends ResultBase 
{
    private static final long serialVersionUID = 01000;
    /**
     * Description of the Field
     */
    protected List<Object> data = null;
    /**
     * Constructor for the ResultImpl object.
     */
    public ResultImpl() {
    }
    /**
     * Constructor for the ResultImpl object.
     *
     * @param code          Message code
     * @param variableData  Variable data
     */
    public ResultImpl(int code, Object[] variableData) {
        super.addMessage(code, variableData);
    }
    /**
     * Constructor for the ResultImpl object.
     *
     * @param code  Message code
     */
    public ResultImpl(int code) {
        super.addMessage(code);
    }
    /**
     * Adds a feature to the Result attribute of the ResultImpl object
     *
     * @param result  The feature to be added to the Result attribute
     * @return        Description of the Returned Value
     */
    public Result addResult(Object result) {
        if (result != null) {
            if (data == null) {
                data = new ArrayList<Object>();
            }
            data.add(result);
        }
        return this;
    }
    /**
     * Adds a feature to the Results attribute of the ResultImpl object
     *
     * @param result  The feature to be added to the Results attribute
     * @return        Description of the Returned Value
     */
    public Result addResults(List result) {
        if (result != null) {
            if (data == null) {
                data = new ArrayList<Object>((List)result);
            }
            else {
                data.addAll(result);
            }
        }
        return this;
    }
    /**
     */
    public void clear() {
        super.clear();
        data = null;
    }
    /**
     * Gets the Data attribute of the ResultImpl object
     *
     * @return   The Data value
     */
    public List getData() {
        return data == null ? Collections.EMPTY_LIST : data;
    }
    /**
     * Gets the First attribute of the ResultImpl object
     *
     * @return   The First value
     */
    public Object getFirst() {
        return (data == null || data.isEmpty()) ? null : data.get(0);
    }
    /**
     * Gets the ReturnData attribute of the ResultImpl object
     *
     * @return   The ReturnData value
     */
    public List getReturnData() {
        return data == null ? Collections.EMPTY_LIST : data;
    }
    /**
     * @return   Description of the Returned Value
     */
    public boolean hasErrors() {
        return error;
    }
    /**
     * @param result  Description of Parameter
     * @return        Description of the Returned Value
     */
    public Result merge(Result result) {
        if (result != null) {
            if (result.getClass() == ResultImpl.class) {
                List local = result.getReturnData();
                if (local.size() > 0) {
                    this.addResults(local);
                }
            }
            super.merge(result);
        }
        return this;
    }
    /**
     * Sets the Errors attribute of the ResultImpl object
     *
     * @param flag  The new Errors value
     */
    public void setErrors(boolean flag) {
        error = flag;
    }
}
