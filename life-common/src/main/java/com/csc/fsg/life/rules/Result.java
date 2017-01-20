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
import java.util.List;
/**
 * Result defines the public API for the returned results component.
 */
public interface Result {
    /**
     * Adds a feature to the message attribute of the ResultAbstract object.
     *
     * @param message  Message number
     * @return         Description of the Returned Value
     */
    public Result addMessage(int message);
    /**
     * Adds a feature to the message attribute of the ResultAbstract object.
     *
     * @param code   The feature to be added to the Message attribute
     * @param vdata  Variable data
     * @return       Itself
     */
    public Result addMessage(int code, Object[] vdata);
    /**
     * Adds a message to the Result.
     *
     * @param message  An instance of Message
     * @return         Itself
     */
    public Result addMessage(Message message);
    /**
     * Adds a feature to the message attribute of the ResultAbstract object.
     *
     * @param component  Represents the component owning the message
     * @param id         The feature to be added to the Message attribute
     * @param type       Message severity
     * @return           Itself
     */
    public Result addMessage(
            ResultComponent component,
            int id,
            ResultType type);
    /**
     * Adds a feature to the message attribute of the ResultAbstract object.
     *
     * @param component     The component owning the message
     * @param id            The feature to be added to the Message attribute
     * @param type          Message severity
     * @param variableData  The feature to be added to the Message attribute
     * @return              Itself
     */
    public Result addMessage(
            ResultComponent component,
            int id,
            ResultType type,
            Object[] variableData);
    /**
     * Adds a list of messages to the Result.
     *
     * @param messages  A list of messages
     * @return          Itself
     */
    public Result addMessages(List<Message> messages);
    /**
     * Adds a list of messages to the Result.
     *
     * @param messages  A list of messages
     * @return          Itself
     */
    public Result addMessages(Message[] messages);
    /**
     * Adds a Result object to the current object.
     *
     * @param result  The additional Result object
     * @return        Itself
     */
    public Result addResult(Object result);
    /**
     * Adds a list of Result objects to the current object.
     *
     * @param results  The feature to be added to the Results attribute
     * @return         Itself
     */
    public Result addResults(List results);
    /**
     * Removes all existing data from the Result object.
     */
    public void clear();
    /**
     * Disposes a Result object.
     * <p/>
     * If the implementation of the Result object supports pooling the object
     * will be returned.
     */
    public void dispose();
    /**
     * Returns the list of returned objects.
     *
     * @return   List of objects
     */
    public List getData();
    /**
     * Returns the first of the returned objects or null.
     *
     * @return   First of the returned objects
     */
    public Object getFirst();
    /**
     * Returns the list of messages.
     *
     * @return   List of messages contained in this Result
     */
    public Message[] getMessages();
    /**
     * Sets error messages on result.
     *
     * @param messages  The new Messages value
     */
    public void setMessages(Message[] messages);
    /**
     * Returns the list of returned objects.
     *
     * @return   List of objects
     */
    public List getReturnData();
    /**
     * Returns the error indicator.
     *
     * @return   Indicator if action was valid
     */
    public boolean hasErrors();
    /**
     * Returns the error indicator.
     *
     * @return   Indicator if action was valid
     */
    public boolean isErrors();
    /**
     * Sets the Errors attribute of the Result object
     *
     * @param flag  The new Errors value
     */
    public void setErrors(boolean flag);
    /**
     * Indicates whether or not the Result has a specific message
     *
     * @param message  Description of Parameter
     * @return         Indicator if message exists
     */
    public boolean hasMessage(int message);
    /**
     * Merges the content of a Result into the current object.
     *
     * @param result  Result object whose content should be merged
     * @return        Itself
     */
    public Result merge(Result result);
    /**
     * Imbedded factory for default implementation.
     */
    public static final class Factory {
        private Factory() {
        }
        /**
         * Creates a Result object with default implementations
         *
         * @return   A default object
         */
        public static final Result create() {
            return new ResultImpl();
        }
    }
}
