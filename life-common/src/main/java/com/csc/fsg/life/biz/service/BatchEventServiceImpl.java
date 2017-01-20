package com.csc.fsg.life.biz.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.dao.DataAccessException;

import com.csc.fsg.life.biz.bo.BatchEventStatusModel;
import com.csc.fsg.life.biz.bo.BatchEventTrxReport;
import com.csc.fsg.life.biz.bo.XgErrorArea;
import com.csc.fsg.life.biz.bo.XgErrorAreaErrorArray;
import com.csc.fsg.life.biz.bo.XgreposModel;
import com.csc.fsg.life.biz.bo.XgreposModelImpl;
import com.csc.fsg.life.biz.copyobject.CopyObjectException;
import com.csc.fsg.life.biz.copyobject.XgErrorAreaCopyObject;
import com.csc.fsg.life.biz.dao.DuplicateMessageKeyException;
import com.csc.fsg.life.biz.dao.XgreposDAO;
import com.csc.fsg.life.biz.dao.XgreposDAOImpl;
import com.csc.fsg.life.biz.exception.BusinessException;
import com.csc.fsg.life.biz.meta.ServiceMetaData;
import com.csc.fsg.life.context.UserContext;

/* Modifications: ENH988, T0140, T0175, WMA-1638 */

public class BatchEventServiceImpl
	extends BusinessService
	implements BatchEventService
{
	/** Standard Apache Commons Logger. */
	protected static final Log log = LogFactory.getLog("com.csc.fsg");

	private static final ResourceBundle messages = ResourceBundle.getBundle("com.csc.fsg.life.biz.service.BatchEventService");

	/**
	 * Constructor.
	 */
	public BatchEventServiceImpl()
	{
		super();
	}

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
	public BatchId invokeBatchEvent(ServiceParam param, ServiceManagerInput smi)
		throws BusinessException
	{
		int retryCount = 0;
		while (true) {
			BatchId batchId = null;

			try {
				batchId = generateBatchId();
				invokeBatchEvent(param, smi, batchId);
				return batchId;
			}
			catch (DuplicateMessageKeyException e) {
				if (++retryCount > ServiceManager.MAX_DUPLICATE_MESSAGE_KEY_RETRIES) {
					log.error(e.getCause());
					throw new ServiceManagerException("Error inserting record into XGREPOS due to duplicate key: "
													 + batchId.getMessageKey());
				}
				else {
					log.info("Duplicate message key value detected: "
						   + batchId.getMessageKey()
						   + "; new message key will be generated for this event");
				}
			}
		}
	}

	/**
	 * Create a new and unique instance of BatchId.
	 * 
	 * @return A new unique instance of BatchId.
	 */
	protected BatchId generateBatchId()
	{
		return new BatchId();
	}

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
	 * @param batchId
	 *        Key information used to uniquely identify the invoked Batch Event.
	 * 
	 * @exception BusinessException
	 */
	protected void invokeBatchEvent(ServiceParam param, ServiceManagerInput smi, BatchId batchId)
		throws BusinessException
	{
		prepareToInvoke(param, smi);

		// If Batch Origin has not been specified, then force the default value.
		if (!smi.isBatchEventInput())
			smi.setEventBatchOrigin("BATCH");

		UserContext userContext = param.getUserContext();
		ServiceManager serviceManager = userContext.getApplicationEnvironment().getServiceManager();

		try {
			serviceManager.invokeBatchEvent(batchId, smi);
		}
		catch (ServiceManagerTimeoutException e) {
			throw new BatchEventTimeoutException((Exception) e.getCause(), batchId);
		}
	}

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
	public List<XgreposModel> getBatchEventsForUser(ServiceParam param, String userId)
		throws BusinessException
	{
		List<XgreposModel> batchEventRows = null;
		try {
			XgreposDAO dao = new XgreposDAOImpl(param.getUserContext());
			batchEventRows = dao.selectBatchEventsForUser(userId);
		}
		catch (DataAccessException e) {
			e.printStackTrace();
			throw new BusinessServiceException(e.getMostSpecificCause().getMessage());
		}

		return batchEventRows;
	}

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
	public boolean isBatchEventActive(ServiceParam param, BatchId batchId)
		throws BusinessException
	{
		BatchEventStatusSummary summary = getBatchEventStatusSummary(param, batchId);
		return summary.getResponseCount() < summary.getLiveCount();
	}

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
	public int getBatchEventProgressReport(ServiceParam param, BatchId batchId)
		throws BusinessException
	{
		int importStatus = BATCH_EVENT_NOT_STARTED;
		BatchEventStatusSummary summary = getBatchEventStatusSummary(param, batchId);
		int totalCount = summary.getLiveCount() + summary.getCancelCount();
		if (totalCount != 0) {
			int completedOrCancelledCount = summary.getResponseCount() + summary.getCancelCount();
			importStatus = completedOrCancelledCount * 100 / totalCount;
		}

		return Math.min(importStatus, BATCH_EVENT_COMPLETED);
	}

	/**
	 * @param param
	 *        User session-specific run-time information.
	 * 
	 * @param batchId
	 *        Key information used to uniquely identify the invoked Batch Event.
	 * 
	 * @return Batch Event status summary information, including total count of
	 *         transactions, and count of transactions, which have matching
	 *         responses.
	 * 
	 * @exception BusinessServiceException
	 */
	private BatchEventStatusSummary getBatchEventStatusSummary(ServiceParam param, BatchId batchId)
		throws BusinessServiceException
	{
		List<BatchEventStatusModel> statusRows = null;
		try {
			XgreposDAO dao = new XgreposDAOImpl(param.getUserContext());
			statusRows = dao.selectBatchEventStatus(batchId);
		}
		catch (DataAccessException e) {
			e.printStackTrace();
			throw new BusinessServiceException(e.getMostSpecificCause().getMessage());
		}

		int liveCount = 0;
		int cancelCount = 0;
		int responseCount = 0;
		if (statusRows != null) {
			for (BatchEventStatusModel row : statusRows) {
				String operation = row.getOperation();
				if (operation.equals("A"))
					liveCount += row.getCount().intValue();
				else if (operation.equals("X"))
					cancelCount += row.getCount().intValue();
				else if (operation.equals("R"))
					responseCount += row.getCount().intValue();
			}
		}

		return new BatchEventStatusSummary(liveCount, cancelCount, responseCount);
	}

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
	@ServiceMetaData(transaction = true)
	public int cancelRemainingBatchEvent(ServiceParam param, BatchId batchId)
		throws BusinessException
	{
		int rowCount = 0;
		try {
			XgreposDAO dao = new XgreposDAOImpl(param.getUserContext());
			rowCount = dao.cancelRemainingBatchEvent(batchId);
		}
		catch (DataAccessException e) {
			e.printStackTrace();
			throw new BusinessServiceException(e.getMostSpecificCause().getMessage());
		}

		if (rowCount == 0)
			throw new BusinessServiceException(messages.getString("batch_completed_on_cancel"));

		return rowCount;
	}

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
	public Map<Integer, BatchEventTrxReport> getBatchEventMessages(ServiceParam param, BatchId batchId)
		throws BusinessException
	{
		if (isBatchEventActive(param, batchId))
			throw new BusinessServiceException(messages.getString("batch_event_active_on_get_messages"));

		List<XgreposModel> cancelledRows = retrieveCancelledRows(param, batchId);
		List<XgreposModel> statusRows = retrieveStatusRows(param, batchId);

		return buildMessageMap(param, cancelledRows, statusRows);
	}

	/**
	 * Retrieve a list of XGREPOS rows representing cancelled transactions in
	 * Batch Event.
	 * 
	 * @param param
	 *        User session-specific run-time information.
	 * 
	 * @param batchId
	 *        Key information used to uniquely identify the invoked Batch Event.
	 * 
	 * @return A list of XGREPOS rows representing cancelled transactions in a
	 *         Batch Event (only rows with SUB_SEQ_NO = 0).
	 * 
	 * @exception BusinessException
	 */
	private List<XgreposModel> retrieveCancelledRows(ServiceParam param, BatchId batchId)
		throws BusinessException
	{
		List<XgreposModel> cancelledRows = null;
		try {
			XgreposDAO dao = new XgreposDAOImpl(param.getUserContext());
			cancelledRows = dao.selectBatchEventCancelledItems(batchId);
		}
		catch (DataAccessException e) {
			e.printStackTrace();
			throw new BusinessServiceException(e.getMostSpecificCause().getMessage());
		}

		return cancelledRows;
	}

	/**
	 * Retrieve a list of XGREPOS rows representing response status information
	 * returned from the host.
	 * 
	 * @param param
	 *        User session-specific run-time information.
	 * 
	 * @param batchId
	 *        Key information used to uniquely identify the invoked Batch Event.
	 * 
	 * @return The XGREPOS rows representing response status information
	 *         returned from the host.
	 * 
	 * @exception BusinessException
	 */
	@SuppressWarnings("unchecked")
	private List<XgreposModel> retrieveStatusRows(ServiceParam param, BatchId batchId)
		throws BusinessException
	{
		List<XgreposModel> statusRows = null;
		XgreposModel model = new XgreposModelImpl();
		model.setMessageKey(batchId.getMessageKey());
		model.setOperation(ServiceManager.OPERATION_RESPONSE);
		model.setSubSeqNo(Integer.valueOf(0));

		try {
			XgreposDAO dao = new XgreposDAOImpl(param.getUserContext());
			statusRows = dao.selectListFromXGREPOS(model);
		}
		catch (DataAccessException e) {
			e.printStackTrace();
			throw new BusinessServiceException(e.getMostSpecificCause().getMessage());
		}

		return statusRows;
	}

	/**
	 * Extract messages related to a Batch Event, and returned as a list of
	 * XGREPOS rows.
	 * 
	 * @param param
	 *        User session-specific run-time information.
	 * 
	 * @param cancelledRows
	 *        A list of XGREPOS rows representing cancelled transactions in a
	 *        Batch Event (only rows with SUB_SEQ_NO = 0).
	 * 
	 * @param statusRows
	 *        A list of XGREPOS rows containing status status information about
	 *        processed transactions (OPERATION = "R" and SUB_SEQ_NO = 0).
	 * 
	 * @return A map keyed by sequence number of each transaction within Batch
	 *         Event, and mapping them to an object containing both success
	 *         indicator, and a list of actual messages, if any have been
	 *         returned from the host system.
	 */
	private Map<Integer, BatchEventTrxReport> buildMessageMap(ServiceParam param, List<XgreposModel> cancelledRows, List<XgreposModel> statusRows)
		throws CopyObjectException
	{
		Map<Integer, BatchEventTrxReport> messageMap = new HashMap<Integer, BatchEventTrxReport>();

		for (XgreposModel cancelledRow : cancelledRows) {
			BatchEventTrxReport trxReport = new BatchEventTrxReport();
			messageMap.put(cancelledRow.getSequenceNo(), trxReport);
			trxReport.indicateCancelled();
		}

		UserContext userContext = param.getUserContext();
		for (XgreposModel statusRow : statusRows) {
			String status = statusRow.getStatusCode() + "  ";
			char firstChar = status.toUpperCase().charAt(0);

			// Ignore rows corresponding to transactions, which have not been
			// processed.
			if (firstChar == ' ')
				continue;

			BatchEventTrxReport trxReport = new BatchEventTrxReport();
			messageMap.put(statusRow.getSequenceNo(), trxReport);
			switch (firstChar) {
				case 'S':
					trxReport.indicateSkipped();
					break;
				case 'Y':
					trxReport.indicateSuccess();
					break;
				default:
					trxReport.indicateFailure();
					break;
			}

			// Look for messages only if the second character of STATUS_CODE
			// indicate that they have been returned from the host sysem.
			char secondChar = status.toUpperCase().charAt(1);
			if (secondChar != 'E')
				continue;

			ErrorMessageManager errorMessageManager = userContext.getErrorMessageManager();
			if (errorMessageManager == null)
				errorMessageManager = new BasicErrorMessageManager();

			XgErrorArea errorArea = new XgErrorAreaCopyObject(userContext, statusRow.getEventImage());
			errorMessageManager.initErrors(errorArea, userContext);

			for (XgErrorAreaErrorArray errorItem : errorArea.getXgErrorAreaErrorArray()) {
				XgErrorAreaErrorArray error = (XgErrorAreaErrorArray) errorItem;
				String errorCode = error.getErrorCode();
				String longErrorMsg = errorMessageManager.buildErrorMessage(errorCode);
				String errorMsg = errorCode + " - " + longErrorMsg;
				trxReport.addMessage(errorMsg, error.getErrorFieldDisplacement());
			}
		}

		return messageMap;
	}

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
	@ServiceMetaData(transaction = true)
	public void indicateBatchEventResponseReceived(ServiceParam param, BatchId batchId)
		throws BusinessException
	{
		if (isBatchEventActive(param, batchId))
			throw new BusinessServiceException(messages.getString("batch_event_active_on_receive"));

		try {
			XgreposDAO dao = new XgreposDAOImpl(param.getUserContext());
			dao.indicateBatchEventResponseReceived(batchId);
		}
		catch (DataAccessException e) {
			e.printStackTrace();
			throw new BusinessServiceException(e.getMostSpecificCause().getMessage());
		}
	}

	/**
	 * This method will first check whether the Batch Event identified by
	 * batchId is still active. If the method <em>public boolean
	 * isBatchEventActive(BatchId batchId)</em> returns <code>true</code>, an
	 * exception will be thrown. Otherwise any temporary data accumulated in the
	 * course of execution of the Batch Event will be cleared.
	 * 
	 * @param param
	 *        User session-specific run-time information.
	 * 
	 * @param batchId
	 *        Key information used to uniquely identify the invoked Batch Event.
	 * 
	 * @exception BusinessException
	 */
	@ServiceMetaData(transaction = true)
	public void clearTransientArtifacts(ServiceParam param, BatchId batchId)
		throws BusinessException
	{
		if (isBatchEventActive(param, batchId))
			throw new BusinessServiceException(messages.getString("batch_event_active_on_clear"));

		try {
			XgreposDAO dao = new XgreposDAOImpl(param.getUserContext());
			dao.deleteBatchEventFromXGREPOS(batchId);
		}
		catch (DataAccessException e) {
			e.printStackTrace();
			throw new BusinessServiceException(e.getMostSpecificCause().getMessage());
		}
	}

	/**
	 * This is a convenience class is used as a container for data used to
	 * express status of a Batch Event.
	 */
	private static class BatchEventStatusSummary
	{
		private int liveCount = 0;
		private int cancelCount = 0;
		private int responseCount = 0;

		/**
		 * Constructor.
		 */
		private BatchEventStatusSummary(int liveCount, int cancelCount, int responseCount)
		{
			this.liveCount = liveCount;
			this.cancelCount = cancelCount;
			this.responseCount = responseCount;
		}

		/**
		 * @return Current value of the property <em>liveCount</em>.
		 */
		private int getLiveCount()
		{
			return liveCount;
		}

		/**
		 * @return Current value of the property <em>cancelCount</em>.
		 */
		private int getCancelCount()
		{
			return cancelCount;
		}

		/**
		 * @return Current value of the property <em>responseCount</em>.
		 */
		private int getResponseCount()
		{
			return responseCount;
		}
	}
}
