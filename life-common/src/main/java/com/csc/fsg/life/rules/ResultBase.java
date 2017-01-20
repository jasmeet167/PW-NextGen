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
import java.util.Iterator;
import java.util.List;
/**
 * This class provides the abstract <code>Result</code> implementation.
 * <p/>
 * This implementation is done as final class to improve performance.
 * If extensions of the <code>Result</code> functionality are required then
 * <code>ResultAbstract</code> should be subclassed or <code>Result</code>
 * should be implemented directly.
 */
public class ResultBase implements Result {
    private static final long serialVersionUID = 01000;
    /**
	   Is the result an error or not.
     */
    protected boolean error = false;
    /**
	   List of messages on the result.
     */
    protected List<Message> msgs = null;
    /**
	   Identifier for the result.
     */
    protected String identifier = null;
    /**
     * Constructor for the ResultImpl object.
     */
    public ResultBase() {
    }
    /**
     * Constructor for the ResultImpl object.
     *
     * @param code          Message code
     * @param variableData  Variable data
     */
    public ResultBase(int code, Object[] variableData) {
        addMessage(code, variableData);
    }
    /**
     * Constructor for the ResultImpl object.
     *
     * @param code  Message code
     */
    public ResultBase(int code) {
        addMessage(code);
    }
    /**
     * Adds a feature to the Message attribute of the ResultImpl object
     *
     * @param message  The feature to be added to the Message attribute
     * @param vdata    The feature to be added to the Message attribute
     * @return         Description of the Returned Value
     */
    public Result addMessage(int message, Object[] vdata) {
        if (msgs == null) {
            msgs = new ArrayList<Message>();
        }
        Message r = (Message)Message.create(message);
        r = r.setVariableData(vdata);
        if (!error) {
            if (r.getSeverity() > Message.WARNING) {
                error = true;
            }
        }
        msgs.add(r);
        return this;
    }
    /**
     * Adds a feature to the Message attribute of the ResultImpl object
     *
     * @param code  The feature to be added to the Message attribute
     * @return      Description of the Returned Value
     */
    public Result addMessage(int code) {
        if (msgs == null) {
            msgs = new ArrayList<Message>();
        }
        Message r = Message.create(code);
        if (!error) {
            if (r.getSeverity() > Message.WARNING) {
                error = true;
            }
        }
        msgs.add(r);
        return this;
    }
    /**
     * @param component  The feature to be added to the Message attribute
     * @param message    The feature to be added to the Message attribute
     * @param type       The feature to be added to the Message attribute
     * @param vdata      The feature to be added to the Message attribute
     * @return           Description of the Returned Value
     * @deprecated
     */
    public Result addMessage(
            ResultComponent component,
            int message,
            ResultType type,
            Object[] vdata) {
        if (!error) {
            if ((type == ResultType.ERROR)
                    || (type == ResultType.SEVERE)) {
                error = true;
            }
        }
        if (msgs == null) {
            msgs = new ArrayList<Message>();
        }
        Message r =
                Message.create(
                component.getComponent(),
                message,
                type.getType());
        if (vdata != null) {
            r = r.setVariableData(vdata);
        }
        msgs.add(r);
        return this;
    }
    /**
     * @param component  The feature to be added to the Message attribute
     * @param message    The feature to be added to the Message attribute
     * @param type       The feature to be added to the Message attribute
     * @return           Description of the Returned Value
     * @deprecated
     */
    public Result addMessage(
            ResultComponent component,
            int message,
            ResultType type) {
        if (!error) {
            if (type == ResultType.ERROR) {
                error = true;
            }
        }
        if (msgs == null) {
            msgs = new ArrayList<Message>();
        }
        msgs.add(
                Message.create(
                component.getComponent(),
                message,
                type.getType()));
        return this;
    }
    /**
     * Adds a feature to the Message attribute of the ResultImpl object
     *
     * @param message  The feature to be added to the Message attribute
     * @return         Description of the Returned Value
     */
    public Result addMessage(Message message) {
        if (msgs == null) {
            msgs = new ArrayList<Message>();
        }
        if (message != null) {
            if (!error) {
                if (message.getSeverity() > Message.WARN) {
                    error = true;
                }
            }
            this.msgs.add(message);
        }
        return this;
    }
    /**
     * Adds a feature to the Messages attribute of the ResultImpl object
     *
     * @param messages  The feature to be added to the Messages attribute
     * @return          Description of the Returned Value
     */
    public Result addMessages(List<Message> messages) {
        if (msgs == null) {
            msgs = new ArrayList<Message>();
        }
        if (messages != null) {
            if (!error) {
                int size = messages.size();
                for (int i = 0; i < size; i++) {
                    if (messages.get(i).getSeverity()
                            > Message.WARN) {
                        error = true;
                        break;
                    }
                }
            }
            this.msgs.addAll(messages);
        }
        return this;
    }
    /**
     * Adds a feature to the Messages attribute of the ResultBase object
     *
     * @param messages  The feature to be added to the Messages attribute
     * @return          Description of the Returned Value
     */
    public Result addMessages(Message[] messages) {
        if (msgs == null) {
            msgs = new ArrayList<Message>();
        }
        if (messages != null) {
            int size = messages.length;
            if (!error) {
                for (int i = 0; i < size; i++) {
                    if ((messages[i]).getSeverity()
                            > Message.WARN) {
                        error = true;
                        break;
                    }
                }
            }
            for (int i = 0; i < size; i++) {
                msgs.add(messages[i]);
            }
        }
        return this;
    }
    /**
     * Adds a feature to the Result attribute of the ResultImpl object
     *
     * @param result  The feature to be added to the Result attribute
     * @return        Description of the Returned Value
     */
    public Result addResult(Object result) {
        throw new UnsupportedOperationException("addResult method not supported on implementation");
    }
    /**
     * Adds a feature to the Results attribute of the ResultImpl object
     *
     * @param result  The feature to be added to the Results attribute
     * @return        Description of the Returned Value
     */
    public Result addResults(List result) {
        throw new UnsupportedOperationException("addResults method not supported on implementation");
    }
    /**
     */
    public void clear() {
        error = false;
        msgs = null;
    }
    /**
     */
    public void dispose() {
    }
    /**
     * Gets the Data attribute of the ResultImpl object
     *
     * @return   The Data value
     */
    public List getData() {
        throw new UnsupportedOperationException("getData method not supported on implementation");
    }
    /**
     * Gets the First attribute of the ResultImpl object
     *
     * @return   The First value
     */
    public Object getFirst() {
        throw new UnsupportedOperationException("getFirst method not supported on implementation");
    }
    /*
     *  This class is required to implement the inherited abstract methods
     *  defined by the ValueObject interface.
     *  However, since the Result class does not have nor will it probably
     *  ever have a corresponding DataObject class this method should do nothing.
     */
    /**
     * Gets the Identifier attribute of the ResultImpl object
     *
     * @return   The Identifier value
     */
    public String getIdentifier() {
        return identifier;
    }
    /**
     * Gets the Messages attribute of the ResultImpl object
     *
     * @return   The Messages value, null if no messages
     */
    public Message[] getMessages() {
        Message[] retVal = null;
        if (msgs != null && msgs.size() > 0) {
            int size = msgs.size();
            retVal = new Message[size];
            for (int i = 0; i < size; i++) {
                retVal[i] = msgs.get(i);
            }
        }
        return retVal;
    }
    /**
     * Gets the ReturnData attribute of the ResultImpl object
     *
     * @return   The ReturnData value
     */
    public List getReturnData() {
        throw new UnsupportedOperationException("getReturnData method not supported on implementation");
    }
    /**
     * @return   Description of the Returned Value
     */
    public boolean hasErrors() {
        return error;
    }
    /**
     * @param message  Description of Parameter
     * @return         Description of the Returned Value
     */
    public boolean hasMessage(int message) {
        if (msgs != null && msgs.size() > 0) {
            Message msg;
            Iterator<Message> i = msgs.iterator();
            while (i.hasNext()) {
                msg = i.next();
                if (msg.getCode() == message) {
                    return true;
                }
            }
        }
        return false;
    }
    /**
     * @param result  Description of Parameter
     * @return        Description of the Returned Value
     */
    public Result merge(Result result) {
        if (result != null) {
            Message[] srcm = result.getMessages();
            if ((srcm != null) && (srcm.length > 0)) {
                if (msgs == null) {
                    msgs = new ArrayList<Message>();
                }
                for (int i = 0; i < srcm.length; i++) {
                    msgs.add(srcm[i]);
                }
            }
            this.error |= result.hasErrors();
        }
        return this;
    }
    /*
     *  This class is required to implement the inherited abstract methods
     *  defined by the ValueObject interface.
     *  However, since the Result class does not have nor will it probably
     *  ever have a corresponding DataObject class this method should do nothing.
     */
    /**
     * Sets the Identifier attribute of the ResultImpl object
     *
     * @param id  The new Identifier value
     */
    public void setIdentifier(String id) {
        identifier = id;
    }
    /**
     * Gets the Errors attribute of the ResultBase object
     *
     * @return   The Errors value
     */
    public boolean isErrors() {
        return error;
    }
    /**
     * Sets the Messages attribute of the ResultBase object
     *
     * @param messages  The new Messages value
     */
    public void setMessages(Message[] messages) {
        if (messages != null && messages.length > 0) {
            this.msgs = new ArrayList<Message>(messages.length);
            for (int i = 0; i < messages.length; i++) {
                msgs.add(messages[i]);
            }
        }
    }
    /**
     * Sets the Errors attribute of the ResultBase object
     *
     * @param flag  The new Errors value
     */
    public void setErrors(boolean flag) {
        error = flag;
    }
}
