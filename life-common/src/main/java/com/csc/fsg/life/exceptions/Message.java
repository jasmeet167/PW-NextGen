package com.csc.fsg.life.exceptions;

import java.net.URL;
import java.text.MessageFormat;
import java.util.Properties;

/* Modifications: T0175 */

public class Message
{
    // ~ Message Codes
    // ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

	/** Can't format message identifier. **/
    public static final String      CANNOT_FORMAT_MESSAGE  = "CANNOT_FORMAT_MESSAGE";
	/** Can't load properites file identifier. **/
    public static final String      CANNOT_LOAD_PROPERTIES = "CANNOT_LOAD_PROPERTIES";
	/** Can't fetch policy identifier. **/
    public static final String      UNABLE_TO_FETCH_POLICY = "UNABLE_TO_FETCH_POLICY";


    // ~ Static Variables
    // ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

    private static final Properties MESSAGES               = new Properties();

    private static String           _messageResourceName   = "messages.properties";

    // ~ Static Methods
    // ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

	/** 
		Sets the name of the properties file
		@param name the properites file name.
	**/
    public static void setMessageResourceName(String name)
    {
        _messageResourceName = name;
    }

    /**
     * This method created, loads and returns properties from the provided URL.
     * <p>
     * This method does not wrap any exception thrown in a WrapperException as
     * usual, so a call from {@link WrapperException}does not get back another
     * WrapperException and go in an infinite loop.
     */
    private static Properties loadProps(URL url) throws Exception
    {
        Properties props = new Properties();

        props.load(url.openStream());

        return props;
    }

    /**
     * This is a special method for the {@link WrapperException}. This method
     * does not catch the exception and wrap it as usual to avoid an infinite
     * loop.
     * 
     * @see #format(String, Object[])
     */
    public static String formatSpecial(String messageCode, Object[] msgParts) throws Exception
    {
		synchronized (MESSAGES) {
			if (MESSAGES.size() == 0) {
				URL url = Utils.class.getResource(_messageResourceName);
				MESSAGES.putAll(loadProps(url));
			}
		}

        String pattern = MESSAGES.getProperty(messageCode, messageCode);

        if (pattern.equals(messageCode))
            return messageCode;

        return MessageFormat.format(pattern, msgParts);
    }

    /**
     * This method is simply calls the {@link #formatSpecial(String, Object[])}method
     * and catches and wraps any exceptions thrown, in a
     * {@link WrapperException}.
     * 
     * @throws WrapperException
     *             When unable to format the message.
     */
    public static String format(String messageCode, Object[] msgParts)
    {
        try
        {
            return formatSpecial(messageCode, msgParts);
        }
        catch (Exception e)
        {
            throw WrapperException.wrap(e, CANNOT_FORMAT_MESSAGE, messageCode);
        }
    }

	/**
	   Cleas the properites object.
	**/
    public static void clear()
    {
        MESSAGES.clear();
    }

    // ~ Constructors
    // ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

    private Message()
    {
        // Static class
    }
}
