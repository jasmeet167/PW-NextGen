package com.csc.fsg.life.biz.exception;

import java.text.MessageFormat;
import java.util.ResourceBundle;


/**
 * This class represents a Business Message, whose text is retrieved from a
 * Resource Bundle. This class may be instantiated directly, or through
 * BundleMessageFactory, which may be used to create and supply the applicable
 * Resource Bundle, as well as to hold on to Locale associated with the current
 * user.
 * 
 * @see BundleMessageFactory
 */
public class BundleMessage
	extends BusinessMessage
{
	/**
	 * Default Constructor.
	 */
	public BundleMessage()
	{
	}

	/**
	 * Create a new instance of BundleMessage, using message text from the given
	 * resource bundle.
	 * 
	 * @param bundle
	 *            The resource bundle with message text.
	 * 
	 * @param messageKey
	 *            Key used to idenify the text to be retrieved from the resource
	 *            bundle.
	 */
	public BundleMessage(ResourceBundle bundle, String messageKey)
	{
		String messageText = buildMessageText(bundle, messageKey, null);
		setMessage(messageText);
	}

	/**
	 * Create a new instance of BundleMessage, using message text from the given
	 * resource bundle.
	 * 
	 * @param bundle
	 *            The resource bundle with message text.
	 * 
	 * @param messageKey
	 *            Key used to idenify the text to be retrieved from the resource
	 *            bundle.
	 * 
	 * @param param
	 *            The text inserted into message at the place indicating by a
	 *            parameter placeholder.
	 */
	public BundleMessage(ResourceBundle bundle, String messageKey, Object param)
	{
		String messageText = buildMessageText(bundle, messageKey, param);
		setMessage(messageText);
	}

	/**
	 * Extract the string corresponding to messageKey from the given Resource
	 * Bundle. If param is specified, then use it to replace the parameter
	 * placeholder indicated in the text of the message.
	 */
	private String buildMessageText(ResourceBundle bundle, String messageKey, Object param)
	{
		String messageText = bundle.getString(messageKey);
		if (param != null)
		{
			Object parameterArray[] = { param };
			messageText = MessageFormat.format(messageText, parameterArray);
		}

		return messageText;
	}
}
