package com.csc.fsg.life.biz.service;

import java.io.Serializable;

/* Modifications: ENH988 */

/**
 * This class is a wrapper around the key information used to uniquely identify
 * a Batch Event in XGREPOS. The information stored in this object is immutable.
 */
public class BatchId
	implements Serializable
{
	private static final long serialVersionUID = 984759283470123475L;

	/**
	 * The key value uniquely identifying a Batch Event.
	 */
	private String messageKey = null;

	/**
	 * Constructor.
	 */
	public BatchId()
	{
		messageKey = new ServiceParam().buildMessageKey();
	}

	/**
	 * Constructor.
	 */
	public BatchId(String messageKey)
	{
		this.messageKey = messageKey;
	}

	/**
	 * @return Current value of the attribute <em>messageKey<em>.
	 */
	public String getMessageKey()
	{
		return messageKey;
	}

	/**
	 * @return A string representation of this object.
	 */
	@Override
	public String toString()
	{
		return (messageKey == null) ? "" : messageKey;
	}
}
