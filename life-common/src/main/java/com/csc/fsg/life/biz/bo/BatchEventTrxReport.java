package com.csc.fsg.life.biz.bo;

import static com.csc.fsg.life.biz.bo.BatchEventTrxStatusType.CANCELLED;
import static com.csc.fsg.life.biz.bo.BatchEventTrxStatusType.FAILED;
import static com.csc.fsg.life.biz.bo.BatchEventTrxStatusType.INITIAL;
import static com.csc.fsg.life.biz.bo.BatchEventTrxStatusType.SKIPPED;
import static com.csc.fsg.life.biz.bo.BatchEventTrxStatusType.SUCCESSFUL;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.csc.fsg.life.biz.exception.BusinessMessage;

/* Modifications: ENH988 */

/**
 * Container for information used to indicate whether a specific transaction
 * participating in a Batch Event has been successful or not, as well as any
 * messages associated with that transaction.
 */
public class BatchEventTrxReport
	implements Serializable
{
	private static final long serialVersionUID = 948573012946382352L;

	private BatchEventTrxStatusType trxStatus = INITIAL;

	/**
	 * List of messages returned from Service Manager for a transaction
	 * participating in a Batch Event.
	 */
	private List<BusinessMessage> messages = new ArrayList<BusinessMessage>();

	/**
	 * Each message returned from ServiceManager may optionally be associated
	 * with offset of the field, which has caused an error, in the corresponding
	 * copybook. Such association is accomplished by mapping the message to the
	 * corresponding offset in <code>fieldDisplacementMap</code>.
	 */
	private Map<BusinessMessage, Long> fieldDisplacementMap = new HashMap<BusinessMessage, Long>();

	/**
	 * @return A flag indicating whether the transaction corresponding to this
	 *         object has been completed successfully.
	 */
	public boolean isSuccessful()
	{
		return trxStatus == SUCCESSFUL;
	}

	/**
	 * @return A flag indicating whether an error has occurred while processing
	 *         the transaction corresponding to this object.
	 */
	public boolean isFailed()
	{
		return trxStatus == FAILED;
	}

	/**
	 * @return A flag indicating whether the transaction corresponding to this
	 *         object has been cancelled.
	 */
	public boolean isCancelled()
	{
		return trxStatus == CANCELLED;
	}

	/**
	 * @return A flag indicating whether the transaction corresponding to this
	 *         object has been skipped by the host system.
	 */
	public boolean isSkipped()
	{
		return trxStatus == SKIPPED;
	}

	/**
	 * Indicate that the transaction corresponding to this object has been
	 * completed successfully.
	 */
	public void indicateSuccess()
	{
		trxStatus = SUCCESSFUL;
	}

	/**
	 * Indicate that the transaction corresponding to this object has failed.
	 */
	public void indicateFailure()
	{
		trxStatus = FAILED;
	}

	/**
	 * Indicate that the transaction corresponding to this object has been
	 * cancelled.
	 */
	public void indicateCancelled()
	{
		trxStatus = CANCELLED;
	}

	/**
	 * Indicate that the transaction corresponding to this object has been
	 * skipped by the host system.
	 */
	public void indicateSkipped()
	{
		trxStatus = SKIPPED;
	}

	/**
	 * @return A flag indicating whether there are any messages associated with
	 *         the transaction corresponding to this object.
	 */
	public boolean hasMessages()
	{
		return !messages.isEmpty();
	}

	/**
	 * Add a new message to the list of messages associated with the transaction
	 * corresponding to this object.
	 */
	public void addMessage(BusinessMessage message)
	{
		addMessage(message, null);
	}

	/**
	 * Add a new message to the list of messages associated with the transaction
	 * corresponding to this object.
	 */
	public void addMessage(BusinessMessage message, Long fieldDisplacement)
	{
		messages.add(message);
		if (fieldDisplacement != null)
			fieldDisplacementMap.put(message, fieldDisplacement);
	}

	/**
	 * Add a new message to the list of messages associated with the transaction
	 * corresponding to this object.
	 */
	public void addMessage(String message, Long fieldDisplacement)
	{
		BusinessMessage businessMessage = new BusinessMessage(message);
		messages.add(businessMessage);
		if (fieldDisplacement != null)
			fieldDisplacementMap.put(businessMessage, fieldDisplacement);
	}

	/**
	 * @return List of all messages associated with the transaction
	 *         corresponding to this object.
	 */
	public List<BusinessMessage> getMessages()
	{
		return messages;
	}

	/**
	 * Return the field displacement mapped to the given <code>message</code>.
	 * If no such mapping exists, then return <code>null</code>.
	 */
	public Long getFieldDisplacement(BusinessMessage message)
	{
		return fieldDisplacementMap.get(message);
	}
}
