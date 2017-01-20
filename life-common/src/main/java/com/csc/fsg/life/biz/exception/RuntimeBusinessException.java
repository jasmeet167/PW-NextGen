package com.csc.fsg.life.biz.exception;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.csc.fsg.life.biz.service.ServiceManagerResult;

public abstract class RuntimeBusinessException
	extends RuntimeException
{
	private static final long serialVersionUID = 8394587672007234761L;

	private ServiceManagerResult serviceManagerResult;

	/**
	 * List of error message objects.
	 */
	protected List<BusinessMessage> messages = new ArrayList<BusinessMessage>();

	/**
	 * Create an empty exception.
	 */
	public RuntimeBusinessException()
	{
	}

	/**
	 * Create a BusinessException from a structured BusinessMessage object.
	 * 
	 * @param message
	 *        the structured BusinessMessage object.
	 */
	public RuntimeBusinessException(BusinessMessage message)
	{
		addMessage(message);
	}

	/**
	 * Create a BusinessException from a string message.
	 * 
	 * @param msg
	 *        The message.
	 */
	public RuntimeBusinessException(String msg)
	{
		addMessage(new BusinessMessage(null, msg, null));
	}

	/**
	 * Create a BusinessException object from another exception.
	 * 
	 * @param e
	 *        The exception that triggered this exception's creation.
	 */
	public RuntimeBusinessException(Throwable e)
	{
		super(e);
		addMessage(new BusinessMessage(null, e.getMessage(), null));
	}

	/**
	 * Adds a message to the list of business messages.
	 * 
	 * @param message
	 *        The message.
	 */
	public final void addMessage(BusinessMessage message)
	{
		messages.add(message);
	}

	/**
	 * Adds a new <code>BusinessMessage</code> using the given message string.
	 * 
	 * @param message
	 *        message string
	 */
	public void addMessage(String message)
	{
		messages.add(new BusinessMessage(message));
	}

	/**
	 * Gets the list of BusinessMessage objects.
	 **/
	public List<BusinessMessage> getMessages()
	{
		return messages;
	}

	/**
	 * Adds a list of BussinessMessages.
	 * 
	 * @param messages
	 *        the list of messages.
	 */
	public void addMessages(List<BusinessMessage> messages)
	{
		Iterator<BusinessMessage> itt = messages.iterator();

		while (itt.hasNext()) {
			BusinessMessage msg = itt.next();
			addMessage(msg);
		}
	}

	/**
	 * Returns true if there are any BusinessMessages on this exception.
	 * 
	 * @return true if there are any BusinessMessages on this exception.
	 */
	public boolean hasMessages()
	{
		return !messages.isEmpty();
	}

	/**
	 * Gets a text version of all BusinessMessages.
	 * 
	 * @return a text version of all BusinessMessages.
	 */
	@Override
	public String getMessage()
	{
		StringBuffer buf = new StringBuffer();
		int i = 0;
		for (Iterator<BusinessMessage> itt = messages.iterator(); itt.hasNext();) {
			BusinessMessage message = itt.next();
			if (message != null && message.getMessage() != null) {
				if (i != 0)
					buf.append("; ");
				buf.append(message.getMessage());
			}
			i++;
		}

		return buf.toString();
	}

	/**
	 * @return the serviceManagerResult
	 */
	public ServiceManagerResult getServiceManagerResult()
	{
		return serviceManagerResult;
	}

	/**
	 * @param serviceManagerResult
	 *        the serviceManagerResult to set
	 */
	public void setServiceManagerResult(ServiceManagerResult serviceManagerResult)
	{
		this.serviceManagerResult = serviceManagerResult;
	}
}
