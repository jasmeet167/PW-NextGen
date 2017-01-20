package com.csc.fsg.life.biz.service;

import java.sql.Connection;
import java.util.List;
import java.util.Map;

import com.csc.fsg.life.biz.bo.BatchEventTrxReport;
import com.csc.fsg.life.biz.bo.XgreposModel;
import com.csc.fsg.life.biz.exception.BusinessException;
import com.csc.fsg.life.biz.meta.ServiceMetaData;
import com.csc.fsg.life.biz.meta.ServiceMetaData.ServiceEventType;

/* Modifications: ENH988, T0135 */

/**
 * This interface is implemented by the service used to access functionality
 * related to Batch Event processing.
 */
public interface BatchEventService
	extends Service
{
	public static final int BATCH_EVENT_NOT_STARTED = -1;
	public static final int BATCH_EVENT_INITIALIZED = 0;
	public static final int BATCH_EVENT_COMPLETED = 100;

	/**
	 * Submit a Batch Event to Service Manager for processing.
	 * 
	 * @param param
	 *        User session-specific run-time information.
	 * 
	 * @param smi
	 *        Container for the data to be submitted along with the triggered
	 *        Batch Event, as well as any other relevant information.
	 * 
	 * @return Key information used to uniquely identify the invoked Batch
	 *         Event.
	 * 
	 * @exception BusinessException
	 */
	@ServiceMetaData(serviceEventType = ServiceEventType.UPDATE)
	public BatchId invokeBatchEvent(ServiceParam param, ServiceManagerInput smi)
		throws BusinessException;

	/**
	 * This method will return a list of Batch Events initiated by the user
	 * identified by <em>userId</em>, and not yet marked as received. The return
	 * List includes only enough information to to identify each Batch Event:
	 * the first row of the first transaction.
	 * 
	 * @param param
	 *        User session-specific run-time information.
	 * 
	 * @param userId
	 *        Id of the user, whose Batch Events are to be returned.
	 * 
	 * @return List of rows with key information for each applicable Batch
	 *         Event.
	 * 
	 * @exception BusinessException
	 */
	@ServiceMetaData(serviceEventType = ServiceEventType.INQUIRY, isolationLevel = Connection.TRANSACTION_READ_UNCOMMITTED)
	public List<XgreposModel> getBatchEventsForUser(ServiceParam param, String userId)
		throws BusinessException;

	/**
	 * This method will return value of <code>true</code> if at least one
	 * transaction in the Batch Event indicated by <em>batchId</em> is still
	 * pending execution, and <code>false</code> otherwise. The return value of
	 * <code>false</code> may indicate that the event was either completed, or
	 * cancelled.
	 * 
	 * @param param
	 *        User session-specific run-time information.
	 * 
	 * @param batchId
	 *        Key information used to uniquely identify the invoked Batch Event.
	 * 
	 * @return A flag indicating whether the Batch Event identified by
	 *         <em>batchId</em> is still running.
	 * 
	 * @exception BusinessException
	 */
	@ServiceMetaData(serviceEventType = ServiceEventType.INQUIRY, isolationLevel = Connection.TRANSACTION_READ_UNCOMMITTED)
	public boolean isBatchEventActive(ServiceParam param, BatchId batchId)
		throws BusinessException;

	/**
	 * If the Batch Event indicated by batchId has been either completed or
	 * cancelled, this method will return value of <code>100</code>. Otherwise,
	 * it will calculate and return an estimate of the percentage complete of
	 * the total work required to process all transactions in. It will base the
	 * calculation on ratio of the number of processed transactions to the total
	 * count of transactions in the Batch Event.
	 * 
	 * @param param
	 *        User session-specific run-time information.
	 * 
	 * @param batchId
	 *        Key information used to uniquely identify the invoked Batch Event.
	 * 
	 * @exception BusinessException
	 */
	@ServiceMetaData(serviceEventType = ServiceEventType.INQUIRY, isolationLevel = Connection.TRANSACTION_READ_UNCOMMITTED)
	public int getBatchEventProgressReport(ServiceParam param, BatchId batchId)
		throws BusinessException;

	/**
	 * This method will identify all unprocessed XGREPOS rows corresponding to
	 * the Batch Event indicated by <em>batchId</em>, and mark them as
	 * cancelled, by setting the value of STATUS to <code>X</code>. This will be
	 * interpreted by the host system as instructions to ignore the
	 * corresponding transactions.
	 * 
	 * @param param
	 *        User session-specific run-time information.
	 * 
	 * @param batchId
	 *        Key information used to uniquely identify the invoked Batch Event.
	 * 
	 * @return The number of XGREPOS rows actually cancelled.
	 * 
	 * @exception BusinessException
	 */
	@ServiceMetaData(serviceEventType = ServiceEventType.UPDATE)
	public int cancelRemainingBatchEvent(ServiceParam param, BatchId batchId)
		throws BusinessException;

	/**
	 * This method will return all messages associated with all transactions in
	 * a Batch Event indicated by <code>batchId</code>. The method will first
	 * check whether the Batch Event processing is still active. If the method
	 * <em>public boolean
	 * isBatchEventActive(ServiceParam param, BatchId batchId)</em> returns
	 * true, an exception will be thrown. Otherwise the returned messages will
	 * be packaged into an instance of Map constructed as follows:
	 * <ol>
	 * <li>It will be keyed by SEQUENCE_NO; this key will be used to associate
	 * the corresponding Map entry with an actual transaction participating in
	 * the Batch Event</li>
	 * <li>Each element will wrap an object containing a flag
	 * isSuccessfullyCompleted (whose value will be set to true if the first
	 * byte of STATUS_CODE has value of "Y"), and a list of BusinessMessage,
	 * with each list element wrapping a message in the corresponding
	 * instance(s) of CXGERREC.</li>
	 * <li>It will be possible to report a successful completion, while
	 * returning warning messages.</li>
	 * </ol>
	 * 
	 * @param param
	 *        User session-specific run-time information.
	 * 
	 * @param batchId
	 *        Key information used to uniquely identify the invoked Batch Event.
	 * 
	 * @return The Batch Event messages.
	 * 
	 * @exception BusinessException
	 */
	@ServiceMetaData(serviceEventType = ServiceEventType.INQUIRY)
	public Map<Integer, BatchEventTrxReport> getBatchEventMessages(ServiceParam param, BatchId batchId)
		throws BusinessException;

	/**
	 * This method will first check whether the Batch Event identified by
	 * batchId is still active. If the method <em>public boolean
	 * isBatchEventActive(BatchId batchId)</em> returns <code>true</code>, an
	 * exception will be thrown. Otherwise, RESPONSE_STATUS will be changed to
	 * "R" in every XGREPOS row corresponding to the Batch Event, thus
	 * indicating that the response has been received and processed.
	 * 
	 * @param param
	 *        User session-specific run-time information.
	 * 
	 * @param batchId
	 *        Key information used to uniquely identify the invoked Batch Event.
	 * 
	 * @exception BusinessException
	 */
	@ServiceMetaData(serviceEventType = ServiceEventType.UPDATE)
	public void indicateBatchEventResponseReceived(ServiceParam param, BatchId batchId)
		throws BusinessException;

	/**
	 * Clear any temporary data accumulated in the course of execution of the
	 * Batch Event.
	 * 
	 * @param param
	 *        User session-specific run-time information.
	 * 
	 * @param batchId
	 *        Key information used to uniquely identify the invoked Batch Event.
	 * 
	 * @exception BusinessException
	 */
	@ServiceMetaData(serviceEventType = ServiceEventType.UPDATE)
	public void clearTransientArtifacts(ServiceParam param, BatchId batchId)
		throws BusinessException;
}
