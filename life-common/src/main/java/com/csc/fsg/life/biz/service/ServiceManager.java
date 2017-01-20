package com.csc.fsg.life.biz.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.ibatis.session.ExecutorType;
import org.springframework.jdbc.UncategorizedSQLException;
import org.springframework.util.StopWatch;
import org.springframework.util.StringUtils;

import com.csc.fsg.life.biz.bo.BusinessObjectException;
import com.csc.fsg.life.biz.bo.CommArea;
import com.csc.fsg.life.biz.bo.XgCommArea;
import com.csc.fsg.life.biz.bo.XgErrorArea;
import com.csc.fsg.life.biz.bo.XgErrorAreaErrorArray;
import com.csc.fsg.life.biz.bo.XgreposModel;
import com.csc.fsg.life.biz.bo.XgreposModelImpl;
import com.csc.fsg.life.biz.copyobject.CopyObject;
import com.csc.fsg.life.biz.copyobject.CopyObjectException;
import com.csc.fsg.life.biz.copyobject.CopyObjectMap;
import com.csc.fsg.life.biz.copyobject.XgCommAreaCopyObject;
import com.csc.fsg.life.biz.copyobject.XgErrorAreaCopyObject;
import com.csc.fsg.life.biz.dao.DuplicateMessageKeyException;
import com.csc.fsg.life.biz.dao.XgreposDAO;
import com.csc.fsg.life.biz.dao.XgreposDAOImpl;
import com.csc.fsg.life.biz.exception.BusinessMessage;
import com.csc.fsg.life.biz.meta.MetaDataHelper;
import com.csc.fsg.life.common.config.ApplicationEnvironmentBean;
import com.csc.fsg.life.common.config.EnvDbInfoBean;
import com.csc.fsg.life.common.config.PerformanceLogEffectiveConfig;
import com.csc.fsg.life.common.util.ApplicationContextUtils;
import com.csc.fsg.life.common.util.ResponseTimeMonitor;
import com.csc.fsg.life.context.UserContext;
import com.csc.fsg.life.performance.PerformanceLogActivity;
import com.csc.fsg.life.performance.PerformanceLogActivityStore;
import com.csc.fsg.life.performance.PerformanceLogSegment;

/* Modifications: ENH863, P0157, CCCV-E446, ENH988, P0158, ENH911, T0140, T0150, WMA-1176, */
/*                P0200, T0175, T0166, WMA-1638, T0127, ENH1107, T0199 */

/**
 *  ServiceManager is used for the invocation of events that are
 *  processed by a back-end system.  It handles all communication
 *  to/from the host system, as well as, any necessary error
 *  handling.
 */
public class ServiceManager
{
    /** logger for the ServiceManager class. */
    protected static final Log log = LogFactory.getLog("com.csc.fsg");

    /** List of connections - TCP/IP, CTG, etc. */
    private List<ServiceManagerConnection> connections = new Vector<ServiceManagerConnection>();

    /** System identifier. */
    private String systemId = null;

    /** map of copy objects keyed by copybook name. */
    private CopyObjectMap copyObjectMap = null;

    /** XGREPOS data access object. */
    private XgreposDAO xgreposDao = null;

    /** Should we delete the XGREPOS records? */
    private boolean deleteRecords = false;

    /** Maximum size of the event image. */
    protected int maxImageSize = 32000;

    /** Constant for an add row in XGREPOS. */
    public static final String OPERATION_ADD = "A";

    /** Constant for a response row in XGREPOS. */
    public static final String OPERATION_RESPONSE = "R";

    /** Maximum number of insert retries in response to a duplicate key in XGREPOS. */
    public static final int MAX_DUPLICATE_MESSAGE_KEY_RETRIES = 10;
   
    public XgReposInsertResponseTimeMonitor xgReposInsertResTimeMonitor;
    public ServiceManagerResponseTimeMonitor smResTimeMonitor;

    /**
     * Invokes a ServiceManager event using the following steps:
     *    <ul>
     *      <li>Generates a unique message key
     *      <li>Utilizes the unique key to write any input records
     *          to the XGREPOS table.
     *      <li>Use the configured ServiceManagerConnection to send a
     *          message to the back-end system to signify that record(s)
     *          are waiting in XGREPOS to be processed.
     *      <li>Handle any response records written to XGREPOS by the
     *          back-end.
     *      <li>Build a ServiceManagerResult for use by calling object.
     *    </ul>
     *
     * @param input contains all the input information needed to invoke
     *              the event
     * @return the results of the event invocation
     *
     * @throws ServiceManagerException
     * @throws BusinessServiceException
     */
    public ServiceManagerResult invoke(ServiceManagerInput input)
        throws ServiceManagerException, BusinessServiceException
    {
        // verify the input objects
        List<Object> inputObjects = input.getInputObjects();
        if (inputObjects == null || inputObjects.size() == 0) {
            log.error("No input objects specified");
            throw new ServiceManagerException("No input objects specified");
        }

		// get service parameters
		ServiceParam param = input.getServiceParam();

		int retryCount = 0;
		while (true) {
			// generate key
			String messageKey = param.buildMessageKey();

			// build the XGREPOS records based on the given inputs
			List<XgreposModel> inputRecords = buildInputRecords(messageKey, input);

			try {
				// write input records to XGREPOS and send the message to host system
				return processEvent(messageKey, input, inputRecords);
			}
			catch (DuplicateMessageKeyException e) {
				if (++retryCount > MAX_DUPLICATE_MESSAGE_KEY_RETRIES) {
					log.error(e.getCause());
					throw new ServiceManagerException("Error inserting record into XGREPOS due to duplicate key: "
													 + messageKey);
				}
				else {
					log.info("Duplicate message key value detected: "
							+ messageKey
							+ "; new message key will be generated for this event");
				}
			}
        }
    }

    /**
     * Invoke a Service Manager Batch Event using the following steps:
     * <ol>
     * <li>Obtain the message key from contents of the given 
     * <em>batchId</em>.</li>
     * <li>Utilize the unique key derived from message key to write 
     * all input records to the XGREPOS table.</li>
     * <li>Use the configured ServiceManagerConnection to send 
     * a message to the back-end system to signify that record(s)
     * are waiting in XGREPOS to be processed.</li>
     * <li>Handle communication errors and failure on the host system, 
     * but not application-level errors.</li>
     * </ol>
     *
	 * @param batchId  Key information used to uniquely identify the invoked Batch Event.
     * @param smi      Container for the data to be submitted along with the triggered
	 *        		   Batch Event, as well as any other relevant information.
     *
     * @throws ServiceManagerException
     * @throws BusinessServiceException
     */
    public void invokeBatchEvent(BatchId batchId, ServiceManagerInput smi)
        throws ServiceManagerException, BusinessServiceException
    {
        // Verify the input objects.
        List<Object> inputObjects = smi.getInputObjects();
        if (inputObjects == null || inputObjects.size() == 0) {
            log.error("No input objects specified");
            throw new ServiceManagerException("No input objects specified");
        }

        // Build the XGREPOS records based on the given input.
        List<XgreposModel> inputRecords = buildInputRecords(batchId.getMessageKey(), smi);

        // Write input records to XGREPOS and send the message to host system.
        processBatchEvent(batchId, smi, inputRecords);
    }

    /**
     * Write input records to XGREPOS using given message key and send
     * message to host system to process the event.
     *
     * @param messageKey        unique key for this event
     * @param input             service input
     * @param inputRecords      list of models to write to XGREPOS
     *
     * @throws ServiceManagerException
     * @throws BusinessServiceException
     */
    @SuppressWarnings("unchecked")
	public ServiceManagerResult processEvent(String messageKey, ServiceManagerInput input, List<XgreposModel> inputRecords)
        throws ServiceManagerException, BusinessServiceException
    {
        ServiceParam param = input.getServiceParam();
        UserContext userContext = param.getUserContext();

        log.info("Invoking event: " + input.getEventId() +
                " (Message Key: " + messageKey +
                ", Error Override: " + param.getErrorOverrideInd() +
                ", Realtime: " + param.getRealtimeInd() + ")");

        // loop through the input objects and perform insert to XGREPOS
        xgreposDao = new XgreposDAOImpl(userContext);
        int highestSequence = insertIntoXgrepos(messageKey, inputRecords);

        // build the input communication area
        CommArea commAreaInput = buildCommArea(messageKey, input, param, highestSequence);

        // create history item
        ServiceHistoryItem historyItem = new ServiceHistoryItem();
        historyItem.setRealtimeInd(param.getRealtimeInd());
        historyItem.setErrorOverrideInd(param.getErrorOverrideInd());
        historyItem.setMessageKey(messageKey);
        historyItem.setEventId(input.getEventId());
        historyItem.setEventName(input.getEventName());
        historyItem.setTrxCode(param.getTrxCode());
        if (inputRecords != null)
            historyItem.setNumberOfInputObjects(inputRecords.size());
        userContext.addHistoryItem(historyItem);

		// If applicable, create and activate a new performance log segment
		PerformanceLogActivity activity = getPerformanceLogActivity();
		PerformanceLogSegment smSegment = null;

		if (activity != null) {
			String eventId = null;
			if (commAreaInput instanceof XgCommArea)
				eventId = ((XgCommArea) commAreaInput).getEventId();
			else
				eventId = "";

			smSegment = activity.createServiceManagerSegment(eventId, messageKey);
			smSegment.activate();
		}

        // utilize the appropriate connection type to send message
        CommArea commArea = null;  
        StopWatch smResStopwatch = null;
    	ResponseTimeMonitor responseTimeMonitor = null;
        if (getSmResTimeMonitor() != null) {
        	smResStopwatch = getSmResTimeMonitor().getStopwatch();
        	responseTimeMonitor = getSmResTimeMonitor().getResponseTimeMonitor();    	
        	smResStopwatch.start();
        }
        
        try {        	
            commArea = getConnection().sendEvent(commAreaInput);
        } catch (ServiceManagerTimeoutException e) {
        	log.info("ServiceManager connection timeout...initiating async");
        	throw new ServiceManagerException("Queuing transactions for processing asynchronously");
        } finally {
        	if(smResStopwatch != null) {
        		smResStopwatch.stop();
        		responseTimeMonitor.recordResponseTime(smResStopwatch.getLastTaskTimeMillis());
        	}
			if (smSegment != null) {
				// Stop the timer in the performance log segment
				smSegment.passivate();

				// If activity segment sequence has been updated in COBOL,
				// create place holder segments to account for the consumed
				// sequence numbers
				int newSequenceNo = commArea.getResponseSequenceNo().intValue();
				for (int i = 0, n = activity.getNextSequenceNoAdjustment(newSequenceNo); i < n; i++)
					activity.createExternalSegment();
			}
        }

        // prepare the result object and evaluate status
        ServiceManagerResult result = new ServiceManagerResult();
        String status = commArea.getReturnstatus();
        historyItem.setStatus(status);
        try {
            // if status is OK, get the returned objects if available
            if (status.equals(CommArea.COMM_SUCCESS)) {
                log.info("Event processed successfully");

                // read XGREPOS for return objects
                List resultObjects = fetchResultRecords(input, messageKey);

                // build the return objects
                List returnObjects = buildReturnObjects(resultObjects, userContext);
                if (returnObjects != null)
                    historyItem.setNumberOfOutputObjects(returnObjects.size());
                result.setReturnObjects(returnObjects);

            } else if (status.equals(CommArea.COMM_SUCCESS_WITH_MESSAGES)) {
                log.info("Event processed successfully with returned messages");

                // read XGREPOS for return objects
                List resultObjects = fetchResultRecords(input, messageKey);

                // build the return objects
                List returnObjects = buildReturnObjects(resultObjects, userContext);
                if (returnObjects != null)
                    historyItem.setNumberOfOutputObjects(returnObjects.size());
                result.setReturnObjects(returnObjects);

                // build & set the return messages
                setReturnMessages(result, messageKey, input, userContext, historyItem);

            } else if (status.equals(CommArea.COMM_ERRORS)) {
                handleErrors(result, messageKey, input, userContext, historyItem, param.getTrxCode4());
            } else if (status.equals(CommArea.COMM_FAILURE)) {
                handleFailure(historyItem, commArea);
            } else {
                // Service Manager returned invalid status - typically a back-end abend
                log.error("Service Manager returned an invalid status of: " + status);
            	handleErrors(result, messageKey, input, userContext, historyItem, param.getTrxCode4());
            }

            // delete the XGREPOS records
            if (deleteRecords) {
                XgreposModel model = new XgreposModelImpl();
                model.setMessageKey(messageKey);
                model.setSystemId(systemId);
                model.setEventId(input.getEventId());
                xgreposDao.deleteEventFromXGREPOS(model);
            }
        }
        finally {
            // update responseStatus on XGREPOS response rows
            XgreposModel model = new XgreposModelImpl();
            model.setMessageKey(messageKey);
            model.setSystemId(systemId);
            model.setEventId(input.getEventId());
            model.setOperation(OPERATION_RESPONSE);
            xgreposDao.updateXGREPOSResponse(model);
        }

        return result;
    }

    /**
     * Trigger execution of a Batch Event.
     * <p>
     * This method differs from <code>public ServiceManagerResult 
     * processEvent(String messageKey, ServiceManagerInput input, List<XgreposModel> 
     * inputRecords)</code>, which is used for standard events, in following ways:
     * <ol>
     * <li>Application-level errors reported by the host system are not 
     * returned to the caller.</ol>
     * <li>The column RESPONSE_STATUS is not set to <em>R</em> in the 
     * corresponding XGREPOS rows upon completion of the event; 
     * the rows can only be marked as received through an explicit 
     * action from the user.</li>
     * <li>Even if <em>deleteRecords</em> should be set to <em>true</em>,
     * XGREPOS rows corresponding to a Batch Event will not be deleted 
     * when the event is completed; an explicit action from the user
     * is required for that.</li>
     * </ol>
     * 
	 * @param batchId
	 *        Key information used to uniquely identify the invoked Batch Event.
	 *        
     * @param input 
 	 *        Container for the data to be submitted along with the triggered
	 *        Batch Event, as well as any other relevant information.
	 *        
	 * @param inputRecords
	 *        List of XGREPOS model objects, filled with data reflecting 
	 *        Batch Event input.
     *
     * @throws ServiceManagerException
     * @throws BusinessServiceException
    */
    private void processBatchEvent(BatchId batchId, ServiceManagerInput smi, List<XgreposModel> inputRecords)
    	throws ServiceManagerException, BusinessServiceException
    {
        ServiceParam param = smi.getServiceParam();
        UserContext userContext = param.getUserContext();
        String messageKey = batchId.getMessageKey();

        log.info("Invoking Batch Event: " + smi.getEventId() + " (Message Key: " + messageKey + ")");

        // Loop through the input objects and perform insert to XGREPOS.
        xgreposDao = new XgreposDAOImpl(userContext, ExecutorType.BATCH);
        int highestSequence = insertIntoXgrepos(messageKey, inputRecords, true);

        // Build the input communication area.
        CommArea commAreaInput = buildCommArea(messageKey, smi, param, highestSequence);

        // Create history item
        ServiceHistoryItem historyItem = new ServiceHistoryItem();
        userContext.addHistoryItem(historyItem);
        
        historyItem.setRealtimeInd(param.getRealtimeInd());
        historyItem.setErrorOverrideInd(param.getErrorOverrideInd());
        historyItem.setMessageKey(messageKey);
        historyItem.setEventId(smi.getEventId());
        historyItem.setEventName(smi.getEventName());
        historyItem.setTrxCode(param.getTrxCode());
        if (inputRecords != null)
            historyItem.setNumberOfInputObjects(inputRecords.size());

		// If applicable, create and activate a new performance log segment
		PerformanceLogActivity activity = getPerformanceLogActivity();
		PerformanceLogSegment smSegment = null;

		if (activity != null) {
			String eventId = null;
			if (commAreaInput instanceof XgCommArea)
				eventId = ((XgCommArea) commAreaInput).getEventId();
			else
				eventId = "";

			smSegment = activity.createServiceManagerSegment(eventId, messageKey);
			smSegment.activate();
		}

        // Utilize the appropriate connection type to send message.
        CommArea commArea = null;  
        StopWatch smResStopwatch = null;
    	ResponseTimeMonitor responseTimeMonitor = null;
        if (getSmResTimeMonitor() != null) {
        	smResStopwatch = getSmResTimeMonitor().getStopwatch();
        	responseTimeMonitor = getSmResTimeMonitor().getResponseTimeMonitor();    	
        	smResStopwatch.start();
        }
        
        try {     
            commArea = getConnection().sendEvent(commAreaInput);
        } finally {
        	if(smResStopwatch != null) {
        		smResStopwatch.stop();
        		responseTimeMonitor.recordResponseTime(smResStopwatch.getLastTaskTimeMillis());
        	}
			if (smSegment != null) {
				// Stop the timer in the performance log segment
				smSegment.passivate();

				// If activity segment sequence has been updated in COBOL,
				// create place holder segments to account for the consumed
				// sequence numbers
				int newSequenceNo = commArea.getResponseSequenceNo().intValue();
				for (int i = 0, n = activity.getNextSequenceNoAdjustment(newSequenceNo); i < n; i++)
					activity.createExternalSegment();
			}
        }

        // Evaluate status.
        String status = commArea.getReturnstatus();
        historyItem.setStatus(status);

        if (status.equals(CommArea.COMM_SUCCESS)) {
        	log.info("Batch Event processed successfully");
        } else if (status.equals(CommArea.COMM_SUCCESS_WITH_MESSAGES)) {
        	log.info("Batch Event processed successfully with application-level messages");
        } else if (status.equals(CommArea.COMM_ERRORS)) {
        	log.info("Batch Event completed with one or more application-level errors");
        } else if (status.equals(CommArea.COMM_FAILURE)) {
        	handleFailure(historyItem, commArea);
        } else {
	        String errorMsg = "Service Manager returned an invalid status of: " + status;
	        log.error(errorMsg);
	        historyItem.addError("", errorMsg);
	        throw new ServiceManagerException(errorMsg);
        }
    }

    public int insertIntoXgrepos(String messageKey, List<XgreposModel> inputRecords)
		throws ServiceManagerException 
	{
		return insertIntoXgrepos(messageKey, inputRecords, false);
	}

    /**
     * Insert the list of XGREPOS table model objects into the database table. 
     * 
     * @param messageKey
     *        Key value used for identification of a Service Manager event.
     *        
     * @param inputRecords
     *        List of XgreposModel instances, each containing data to write 
     *        to a new XGREPOS row.
     *        
     * @param isBatchInsertMode
     *        If value of <em>true</em> is provided, JDBC batch insert will 
     *        be used to write new database table rows.  Otherwise, 
     *        one row will be written at a time.
     *        
     * @exception ServiceManagerException
     */
    private int insertIntoXgrepos(String messageKey, List<XgreposModel> inputRecords, boolean isBatchInsertMode)
		throws ServiceManagerException
	{	
    	StopWatch stopWatch = null;
    	ResponseTimeMonitor responseTimeMonitor = null;
    	if (getXgReposInsertResTimeMonitor() != null) {
    		stopWatch = getXgReposInsertResTimeMonitor().getStopwatch();
    		responseTimeMonitor = getXgReposInsertResTimeMonitor().getResponseTimeMonitor();    	
    		stopWatch.start();
    	}
    	
		int highestSequence = 0;
        try {
            Iterator<XgreposModel> inputItt = inputRecords.iterator();
            while (inputItt.hasNext()) {
                XgreposModel model = inputItt.next();

                // check for highest sequence number
                int seq = model.getSequenceNo().intValue();
                if (seq > highestSequence)
                    highestSequence = seq;

                if (!isBatchInsertMode)
                	xgreposDao.insertXGREPOS(model);
            }

            if (isBatchInsertMode)
            	xgreposDao.insertXGREPOS(inputRecords);

        } catch(UncategorizedSQLException e) {
            log.error(e);
            Throwable t = e.getCause();
            throw new ServiceManagerException("Error inserting record into XGREPOS due to: " + t.getMessage());
        } finally {
        	if (stopWatch != null) {
        		stopWatch.stop();
        		responseTimeMonitor.recordResponseTime(stopWatch.getLastTaskTimeMillis());
        	}
        }
		return highestSequence;
	}

	private void setReturnMessages(ServiceManagerResult smResult,String messageKey,
                                     ServiceManagerInput input,
                                     UserContext userContext,
                                     ServiceHistoryItem historyItem)
    	throws ServiceManagerException
    {
        // fetch the message records and build return messages in the result object
        List<BusinessMessage> messages = new ArrayList<BusinessMessage>();
        List<XgreposModel> errorModels = fetchErrorRecords(input, messageKey);
        Iterator<XgreposModel> errorModelsItt = errorModels.iterator();
        while (errorModelsItt.hasNext()) {
            XgreposModel errorModel = (XgreposModel) errorModelsItt.next();
            try {
                XgErrorArea errorArea = new XgErrorAreaCopyObject(userContext, errorModel.getEventImage());
                smResult.addStatusRecord(errorArea);

                log.info("Transaction " + errorArea.getTrxIdentification().trim() + " returned " +
                          errorArea.getNumberOfErrorsReturned() + " message(s)");

                // build the error message manager
                ErrorMessageManager errorMessageManager = userContext.getErrorMessageManager();
                if (errorMessageManager == null)
                    errorMessageManager = new BasicErrorMessageManager();
                errorMessageManager.initErrors(errorArea, userContext);

                // loop over errors ...
                Iterator<XgErrorAreaErrorArray> errorAreaItt = errorArea.getXgErrorAreaErrorArray().iterator();
                while (errorAreaItt.hasNext()) {
                    XgErrorAreaErrorArray error = (XgErrorAreaErrorArray)errorAreaItt.next();
                    String longMsg = errorMessageManager.buildErrorMessage(error.getErrorCode());
                    String msg = error.getErrorCode() + " - " + longMsg;
                    log.info(" Message: " + msg);

                    // add error to service history
                    historyItem.addError(error.getErrorCode(), longMsg);

                    // add a message to the exception for this error
                    messages.add(new BusinessMessage(error.getErrorCode(), longMsg, null));
                }

            } catch(CopyObjectException e) {
                log.error("Could not instantiate message area", e);
                throw new ServiceManagerException(e);
            }
        }
        
        smResult.setReturnMessages(messages);
    }

    /**
     * Handle a Service Manager status of "ERR" by getting error records from
     * XGREPOS and building an exception.
     */
    protected void handleErrors(ServiceManagerResult smResult, String messageKey, ServiceManagerInput input, UserContext userContext,
                                ServiceHistoryItem historyItem)
        throws ServiceManagerException, BusinessServiceException
    {
    	handleErrors(smResult, messageKey, input, userContext, historyItem, null);
    }

    /**
     * Handle a Service Manager status of "ERR" by getting error records from
     * XGREPOS and building an exception.
     */
	protected void handleErrors(ServiceManagerResult smResult, String messageKey, ServiceManagerInput input, UserContext userContext,
                                ServiceHistoryItem historyItem, String initialTrxCode)
        throws ServiceManagerException, BusinessServiceException
    {
        BusinessServiceException bsException = new BusinessServiceException();

        // fetch the error records and build exception
        List<XgreposModel> errorModels = fetchErrorRecords(input, messageKey);
        Iterator<XgreposModel> errorModelsItt = errorModels.iterator();
        while (errorModelsItt.hasNext()) {
            XgreposModel errorModel = (XgreposModel)errorModelsItt.next();
            try {
                XgErrorArea errorArea = new XgErrorAreaCopyObject(userContext, errorModel.getEventImage());
                smResult.addStatusRecord(errorArea);

                log.error("Transaction " + errorArea.getTrxIdentification().trim() + " returned " +
                          errorArea.getNumberOfErrorsReturned() + " error(s)");

                // build the error message manager
                ErrorMessageManager errorMessageManager = userContext.getErrorMessageManager();
                if (errorMessageManager == null)
                    errorMessageManager = new BasicErrorMessageManager();
                errorMessageManager.initErrors(errorArea, userContext);

                // build trx code information
                String errorTrxCode = null;
                boolean isFromInitialTrx = true;
                if (errorArea.getTrxIdentification().trim().length() > 0) {
                	errorTrxCode = errorArea.getTrxIdentification().trim();
                	if (initialTrxCode != null)
                		isFromInitialTrx = errorTrxCode.equals(initialTrxCode.trim());
                }

                // loop over errors ...
                Iterator<XgErrorAreaErrorArray> errorAreaItt = errorArea.getXgErrorAreaErrorArray().iterator();
                while (errorAreaItt.hasNext()) {
                    XgErrorAreaErrorArray error = (XgErrorAreaErrorArray)errorAreaItt.next();
                    String errorCode = error.getErrorCode();
                    String longErrorMsg = "";
                    if (errorCode != null && errorCode.equalsIgnoreCase("ABND")) {
                    	longErrorMsg = error.getErrorMessage();
                    	String miscDetail = error.getMiscDetail();
    					if (StringUtils.hasText(miscDetail))
    						longErrorMsg += " (" + miscDetail + ")";
                    } else
                    	longErrorMsg = errorMessageManager.buildErrorMessage(errorCode);

                    StringBuffer buf = new StringBuffer();
                    if (errorTrxCode != null) {
                    	buf.append(errorTrxCode);
                    	buf.append(":");
                    }

                    buf.append(errorCode);
                    buf.append(" - ");
                    buf.append(longErrorMsg);
                    String errorMsg = buf.toString();
                    log.error(" Error: " + errorMsg);

                    // add error to session history
                    if (errorTrxCode != null)
                    	historyItem.addError(errorTrxCode + ":" + errorCode, longErrorMsg);
                    else
                    	historyItem.addError(errorCode, longErrorMsg);

                    // map error to field by looking for field at the given displacement
                    int errorSeqNo = errorModel.getSequenceNo().intValue();
                    List<Object> inputObjects = input.getInputObjects();
                    CopyObject objectInError = (CopyObject)inputObjects.get(errorSeqNo-1);
                    String errorFieldId = MetaDataHelper.getAttributeForOffset(objectInError.getClass(), error.getErrorFieldDisplacement().intValue(), 0);
                    log.info("Field in error: " + errorFieldId);

                    // add a message to the exception for this error
                    BusinessMessage message = new BusinessMessage(errorTrxCode, isFromInitialTrx, errorCode, longErrorMsg, errorFieldId);
                    bsException.addMessage(message);
                }
            } catch(CopyObjectException e) {
                log.error("Could not instantiate error area", e);
                throw new ServiceManagerException(e);
            }
        }
        
        // if no messages, add message that no error record was found
        if (!bsException.hasMessages())
        	bsException.addMessage(new BusinessMessage("Service manager errored, but no error record found."));

        bsException.setServiceManagerResult(smResult);
        // throw the exception back to caller
        throw bsException;
    }

    /**
     * Handle a Service Manager status of "FAIL" by getting error from the
     * comm area and building exception.
     */
    protected void handleFailure(ServiceHistoryItem historyItem, CommArea commArea)
    	throws BusinessServiceException
    {
        // create error message with error code
        BusinessMessage msg =
            new BusinessMessage(commArea.getErrorcode(), commArea.getErrorcode(), null);
        BusinessServiceException bsException = new BusinessServiceException(msg);

        // Service Manager had errors not relating to the event
        log.error("Service Manager could not execute event due to errors");

        throw bsException;
    }
    
    /**
     * Build the input communication area that will be sent as a message to the
     * host system.
     *
     * @param messageKey        unique key for this event
     * @param input             service input
     * @param param             service parameters
     * @param highestSequence   highest sequence number of input records
     *
     * @throws ServiceManagerException - could not instantiate communication area
     */
    protected CommArea buildCommArea(String messageKey, ServiceManagerInput input, ServiceParam param, int highestSequence)
    	throws ServiceManagerException
    {
        UserContext userContext = param.getUserContext();

        // build the input communication area
        XgCommArea commAreaInput;
        char[] userPwd = null;

        try {
            commAreaInput = new XgCommAreaCopyObject();
            commAreaInput.init(userContext);

            // set environment information
            ApplicationEnvironmentBean env = userContext.getApplicationEnvironment();
            EnvDbInfoBean dataDb = userContext.getDataDb();
            if (dataDb != null) {
                commAreaInput.setApDatabase(env.getDataBaseId());
                commAreaInput.setApSchema(dataDb.getSchema());
            }

            EnvDbInfoBean rulesDb = userContext.getRuleDb();
            if (rulesDb != null) {
                commAreaInput.setBrDatabase(env.getDataBaseId());
                commAreaInput.setBrSchema(rulesDb.getSchema());
            }

            commAreaInput.setApSchema(userContext.getDataDb().getSchema());

            // logging options
            commAreaInput.setVcslogSwitch(userContext.getLogSwitch());
            commAreaInput.setVcslogDest(userContext.getLogDestination());
            commAreaInput.setVcslogFormat(userContext.getLogFormat());
            commAreaInput.setVcslogDetailSw(userContext.getLogDetail());
            if(userContext.getCompanyCode()!=null)
            	commAreaInput.setCompanyCode(userContext.getCompanyCode());
            
            // set MRO data
            if (param.getMroIndicator()) {
                commAreaInput.setMrocontrolindicator("Y");
                commAreaInput.setMrocontrolaorregion(param.getMroControlRegion());
                commAreaInput.setMrotsqueuename(param.getMroQueueName());
                commAreaInput.setMrocontroluserdata(param.getMroControlUserData());
            } else
                commAreaInput.setMrocontrolindicator("N");
            
            commAreaInput.setMessagekey(messageKey);
            commAreaInput.setErroroverrideind(param.getErrorOverrideInd());
            commAreaInput.setInputoperationcode(OPERATION_ADD);
            commAreaInput.setReturnoverriddenerrorsind(
                          (param.getReturnOverriddenErrors() ? "Y" : "N") );
            commAreaInput.setRtdcind(param.getRealtimeInd());
            commAreaInput.setFilecontrol(userContext.getFileCodes());
            commAreaInput.setSystemId(systemId);
            commAreaInput.setEventId(input.getEventId());
            commAreaInput.setEventMode(input.getEventMode());
            commAreaInput.setTrxCode(param.getTrxCode4());
            commAreaInput.setHighestinputrecordseq(Long.valueOf(highestSequence));
            commAreaInput.setUserId(userContext.getUserId20());
            userPwd = userContext.encrypt(userContext.getUserPwdE());
            commAreaInput.setUserPwd(obfuscate(userPwd));

            // set the EZ Socket delimiters
            commAreaInput.setDelim1(",");
            commAreaInput.setDelim2(",");
            commAreaInput.setDelim3(",");

            if (!userContext.isProductCodeDefault())
                commAreaInput.setProdCode(userContext.getProductCode());

            if (param.getRemoveRestrict())
                commAreaInput.setRestrictcoderemovalind("Y");
            if (param.getRemoveSuspend())
                commAreaInput.setSuspendcoderemovalind("Y");

            // if this is a revision, add data necessary for SM to revise the event
            if (param.isRevision()) {
                commAreaInput.setDcrcreviseind(param.getDcRcRevisedInd());
                commAreaInput.setDcrcrevisedkey(param.getDcRcRevisedKey());
            }
            
            // set the service origin indicator
            commAreaInput.setTaskInvocation(param.getInvocationOrigin());
            
            // set the commit strategy
            if (input.isBatchEventInput())
            	commAreaInput.setCommitStrategy("I");	// COMMIT-EACH-TRX-INDIVIDUALLY
            else
            	commAreaInput.setCommitStrategy("A");	// COMMIT-EVENT-ATOMICALLY

			// Pass current performance log activity and sequence to COBOL
			PerformanceLogActivity activity = getPerformanceLogActivity();
			if (activity != null) {
				commAreaInput.setActivityId(activity.getActivityId());
				commAreaInput.setSequenceNo(Long.valueOf(activity.getNextSequenceNo()));
			}
        } catch (BusinessObjectException e) {
            log.error("Could not instantiate input comm area", e);
            throw new ServiceManagerException("Could not instantiate input comm area");
        }
        finally {
        	Arrays.fill(userPwd, ' ');
        }
        
        return commAreaInput;
    }

    /**
     * Build the XGREPOS models based on the given input using the
     * following rules:
     * <p>
     * If the event image of a given input object is greater than
     * the maximum size, break it into multiple records and increment
     * the sub sequence number (zero based).
     *
     * For each record that is broken into multiple pieces, place a "W"
     * in the second position of the status code for all pieces but the
     * last.
     *
     * @param messageKey    unique message key
     * @param input         contains input parameters/objects
     * @return list of XGREPOS models to be written
     *
     * @throws ServiceManagerException - could not instantiate/populate XGREPOSModel
     */
    protected List<XgreposModel> buildInputRecords(String messageKey, ServiceManagerInput input)
        throws ServiceManagerException
    {
    	boolean isBatchEvent = input.isBatchEventInput();
        ServiceParam param = input.getServiceParam();
        UserContext userContext = param.getUserContext();

        List<XgreposModel> inputRecords = new ArrayList<XgreposModel>();
        try {
            int sequence = 0;
            Iterator<Object> inputItt = input.getInputObjects().iterator();
            while (inputItt.hasNext()) {
                CopyObject obj = (CopyObject)inputItt.next();
                byte[] eventImage = obj.toBytes();
                int imageLength = eventImage.length;
                sequence++;

                // check to see if we need to break up(wrap) the event image
                if (imageLength > maxImageSize) {
                    int byteIndex = 0;
                    int subsequence = 0;
                    String status = "";

                    // loop until there are no more bytes
                    while (imageLength > 0) {

                        // build new byte array
                        byte[] tempBytes;

                        // if we are wrapping, set second position of status to "W"
                        if (imageLength > maxImageSize) {
                            status = " W";
                            tempBytes = new byte[maxImageSize];
                        } else {
                            status = "";
                            tempBytes = new byte[imageLength];
                        }

                        // copy the appropriate bytes into new byte array
                        System.arraycopy(eventImage, byteIndex, tempBytes, 0, tempBytes.length);
                        byteIndex += tempBytes.length;

                        // create XGREPOS model
                        XgreposModel model = new XgreposModelImpl();
                        model.setMessageKey(messageKey);
                        model.setSystemId(systemId);
                        
                        if (isBatchEvent) {
                        	model.setBatchOrigin(input.getEventBatchOrigin());
                        	model.setEventId(input.getEventId(obj));
                        } else {
                        	model.setBatchOrigin("");
                        	model.setEventId(input.getEventId());
                        }
                        
                        model.setOperation(OPERATION_ADD);
                        model.setSequenceNo(Integer.valueOf(sequence));
						if (input.isTrxOrderEnforced())
							model.setReseqSw("Y");
						else
							model.setReseqSw("");

                        model.setCopybookName(obj.getCopybookName());
                        model.setEventImage(tempBytes);
                        model.setSubSeqNo(Integer.valueOf(subsequence++));
                        model.setMapId(Integer.valueOf(0));
                        model.setStatusCode(status);
                        model.setUserId(userContext.getUserId12().toUpperCase());
                        model.setTrxCode(param.getTrxCode4());
                        inputRecords.add(model);

                        // decrement image length
                        imageLength -= tempBytes.length;
                    }
                } else {
                    // no wrapping needed, just build one record
                    XgreposModel model = new XgreposModelImpl();
                    model.setMessageKey(messageKey);
                    model.setSystemId(systemId);
                    
                    if (isBatchEvent) {
                    	model.setBatchOrigin(input.getEventBatchOrigin());
                    	model.setEventId(input.getEventId(obj));
                    } else {
                    	model.setBatchOrigin("");
                        model.setEventId(input.getEventId());
                    }
                    
                    model.setOperation(OPERATION_ADD);
                    model.setSequenceNo(Integer.valueOf(sequence));
					if (input.isTrxOrderEnforced())
						model.setReseqSw("Y");
					else
						model.setReseqSw("");

                    model.setCopybookName(obj.getCopybookName());
                    model.setEventImage(eventImage);
                    model.setSubSeqNo(Integer.valueOf(0));
                    model.setMapId(Integer.valueOf(0));
                    model.setStatusCode("");
                    model.setUserId(userContext.getUserId12().toUpperCase());
                    model.setTrxCode(param.getTrxCode4());

                    inputRecords.add(model);
                }
            }
        } catch(CopyObjectException e) {
            log.error("Error populating XGREPOS model", e);
            throw new ServiceManagerException(e);
        }

        return inputRecords;
    }

    /**
     * Fetch the result records from the XGREPOS table.
     *
     * @param input         service manager input
     * @param messageKey    unique message key to retrieve error records for
     * @return list of result records
     */
	@SuppressWarnings("unchecked")
	protected List fetchResultRecords(ServiceManagerInput input, String messageKey) 
	{
		XgreposModel model = new XgreposModelImpl();
		model.setMessageKey(messageKey);
		model.setSystemId(systemId);
		model.setEventId(input.getEventId());
		model.setOperation(OPERATION_RESPONSE);
		return xgreposDao.selectResultsFromXGREPOS(model);
	}
	
    /**
     * Fetch the error records from the XGREPOS table.
     *
     * @param input         service manager input
     * @param messageKey    unique message key to retrieve error records for
     * @return list of error records
     */
    @SuppressWarnings("unchecked")
	protected List<XgreposModel> fetchErrorRecords(ServiceManagerInput input, String messageKey)
    {
        // read XGREPOS for error record
        XgreposModel model = new XgreposModelImpl();
        model.setMessageKey(messageKey);
        model.setSystemId(systemId);
        model.setEventId(input.getEventId());
        model.setOperation(OPERATION_RESPONSE);
        model.setSubSeqNo(Integer.valueOf(0));
        List<XgreposModel> returnObjects = xgreposDao.selectListFromXGREPOS(model);
        log.info("Fetched " + returnObjects.size() + " error record(s)");
        return returnObjects;
    }

    /**
     * Instantiates a <code>CopyObject</code> for the given copybook.
     *
     * @param copybookName  name of the copybook that determines
     *                      the CopyObject to instantiate
     * @param context       current user context
     * @param eventImage    byte array used to initialize copy object
     * @return  newly instantiated CopyObject
     *
     * @throws ServiceManagerException - error instantiating/initializing copy object
     */
    @SuppressWarnings("unchecked")
	public CopyObject getCopyObject(String copybookName, UserContext context, byte[] eventImage)
        throws ServiceManagerException
    {
        CopyObject copyObj = null;

        // verify copybook name
        if (copybookName == null || copybookName.trim().length() == 0)
            throw new ServiceManagerException("No copybook name returned");

        String className = getCopyObjectClass(copybookName + context.getCopybookVersion());
        if (className == null && context.getCopybookVersion().length() != 0)
        	className = getCopyObjectClass(copybookName);
        try {
            Class cls = Class.forName(className);
            copyObj = (CopyObject)cls.newInstance();
            if (eventImage.length > copyObj.getSize()) {
                byte[] realImage = new byte[copyObj.getSize()];
                System.arraycopy(eventImage, 0, realImage, 0, realImage.length);
                eventImage = realImage;
            }

            copyObj.init(context, eventImage);
        } catch (Exception e) {
            log.error("Error instantiating copybook: " + copybookName, e);
            throw new ServiceManagerException("Could not instantiate copy object for copybook "
                    + copybookName);
        }

        return copyObj;
    }

    public String getCopyObjectClass(String copybookName)
    {
    	return copyObjectMap.getCopyObjectClass(copybookName);
    }
    
    /**
     * Build the list of return objects.  Any wrapped rows must be
     * combined into a single event image.
     *
     * @param results       list of XGREPOSModels to use
     * @param userContext   current user context
     * @return list of copy objects
     *
     * @throws ServiceManagerException - could not instantiate a copy object
     */
    public List<Object> buildReturnObjects(List<XgreposModel> results, UserContext userContext)
        throws ServiceManagerException
    {
        List<Object> returnObjects = new ArrayList<Object>();
        byte temp[] = new byte[0];

        Iterator<XgreposModel> resultsItt = results.iterator();
        while (resultsItt.hasNext()) {
            XgreposModel nextResult = (XgreposModel)resultsItt.next();

            // check for any wrapped records that need to be merged
            String statusCode = nextResult.getStatusCode();
            String secondByte = statusCode.length() <= 1 ? "" : statusCode.substring(1, 2);
            boolean wrappedCopybook = secondByte.equalsIgnoreCase("W");

            byte[] thisArray = nextResult.getEventImage();

            if (temp.length == 0)
                temp = thisArray;
            else
                temp = joinByteArrays(temp, thisArray);

            if (!wrappedCopybook) {
                // use the copybook name to lookup the corresponding CopyObject to instantiate
                String copybookName = nextResult.getCopybookName();
                CopyObject copyObj = getCopyObject(copybookName, userContext, temp);

                // make sure we have a copy object
                if (copyObj == null) {
                    log.error("Copy object for copybook " + copybookName + " is null");
                    throw new ServiceManagerException("Could not instantiate copy object for copybook "
                            + copybookName);
                }

                // add newly instantiated CopyObject to result
                log.info("Adding return object for copybook " + copybookName);
                returnObjects.add(copyObj);

                // initialize byte array
                temp = new byte[0];
            }
        }

        return returnObjects;
    }

    /**
     * Create one byte array by combining the given arrays.
     *
     * @param first     first byte array
     * @param second    second byte array
     * @return  combined byte array
     */
    protected static byte[] joinByteArrays(byte[] first, byte[] second)
    {
        if (first == null) {
        	if (second != null)
        		return second;
        	else
        		return new byte[0];
        }
        
        if (second == null) {
        	if (first != null)
        		return first;
        }
        
        byte[] temp = new byte[first.length + second.length];
        System.arraycopy(first, 0, temp, 0, first.length);
        System.arraycopy(second, 0, temp, first.length, second.length);

        return temp;
    }

    /**
     * Utility method used for obfuscating a given char array.  It is a modified
     * version of the ROT13 encryption method.  It will rotate alphabetic characters
     * 13 positions in the alphabet.  Numeric characters are rotated 5 positions.
     *
     * @param bInput    byte array to obfuscate
     * @return  obfuscated byte array
     */
    private char[] obfuscate(char[] chInput)
    {
    	for (int i = 0; i < chInput.length; i++) {
            char ch = chInput[i];
            if (ch >= 'a' && ch <= 'm') 
                ch += 13;
            else if (ch >= 'n' && ch <= 'z') 
                ch -= 13;
            else if (ch >= 'A' && ch <= 'M') 
                ch += 13;
            else if (ch >= 'A' && ch <= 'Z') 
                ch -= 13;
            else if (ch >= '0' && ch <= '4')
            	ch += 5;
            else if (ch >= '5' && ch <= '9')
            	ch -= 5;
            
            chInput[i] = ch;
        }

        return chInput;
    }

	/**
	 * Returns current performance log activity. Note that a null value will be
	 * returned, thus turning off performance logging, unless all of the
	 * following are true:
	 * <ol>
	 * <li>Performance logging is configured and enabled at the applicable level
	 * </li>
	 * <li>Performance logging is enabled in the current session</li>
	 * <li>A performance log activity has already been created, for example by a
	 * service-level interceptor</li>
	 * </ol>
	 * 
	 * @return The current performance log activity instance
	 */
	private PerformanceLogActivity getPerformanceLogActivity()
	{
		if (!ApplicationContextUtils.containsBean("performanceLogActivityStore"))
			return null;

		PerformanceLogActivityStore activityStore 
			= (PerformanceLogActivityStore) ApplicationContextUtils.getBean("performanceLogActivityStore");
		if (activityStore.isEmpty())
			return null;

		PerformanceLogEffectiveConfig logConfigBean 
			= (PerformanceLogEffectiveConfig) ApplicationContextUtils.getBean("performanceLogEffectiveConfig");

		if (logConfigBean == null || !logConfigBean.isLoggingEnabledAtServiceLevel())
			return null;
		else
			return activityStore.getActivity();
	}

    /**
     * Returns the first connection in the list.  Then rotates the list items so next
     * call to this method may return a different connection from list.
     *
     * @return The Connection value.
     * @see #getConnection
     */
    public ServiceManagerConnection getConnection()
    {
        ServiceManagerConnection conn = connections.get(0);
        Collections.rotate(connections, -1);

        return conn;
    }

    /**
     * Sets the Connection.
     *
     * @param connection The new Connection value.
     * @see #getConnection
     */
    public void setConnection(ServiceManagerConnection connection)
    {
        connections.add(connection);
    }

    /**
     * Sets the list of connections.
     *
     * @param connections The new Connections value.
     */
    public void setConnections(List<ServiceManagerConnection> connections)
    {
        this.connections = connections;
    }

    /**
     * Sets the CopyObjectMap.
     *
     * @param copyObjectMap The new CopyObjectMap value.
     */
    public void setCopyObjectMap(CopyObjectMap copyObjectMap)
    {
        this.copyObjectMap = copyObjectMap;
    }

    /**
     * Sets the XgreposDao.
     *
     * @param xgreposDao The new XgreposDao value.
     */
    public void setXgreposDao(XgreposDAO xgreposDao)
    {
        this.xgreposDao = xgreposDao;
    }

    /**
     * Sets the SystemId.
     *
     * @param systemId The new SystemId value.
     */
    public void setSystemId(String systemId)
    {
        this.systemId = systemId;
    }

    /**
     * Should records in XGREPOS be deleted after event is successful.
     *
     * @return true if records are to be deleted, false if not.
     */
    public boolean isDeleteRecords()
    {
        return deleteRecords;
    }

    /**
     * Sets the DeleteRecords.
     *
     * @param deleteRecords The new DeleteRecords value.
     */
    public void setDeleteRecords(boolean deleteRecords)
    {
        this.deleteRecords = deleteRecords;
    }

    /**
     * Sets the MaxImageSize.
     *
     * @param maxImageSize The new MaxImageSize value.
     */
    public void setMaxImageSize(int maxImageSize)
    {
        this.maxImageSize = maxImageSize;
    }

    public String getSystemId()
    {
        return systemId;
    }

	public XgReposInsertResponseTimeMonitor getXgReposInsertResTimeMonitor()
	{
		return xgReposInsertResTimeMonitor;
	}

	public void setXgReposInsertResTimeMonitor(XgReposInsertResponseTimeMonitor xgReposInsertResponseTimeMonitor)
	{
		this.xgReposInsertResTimeMonitor = xgReposInsertResponseTimeMonitor;
	}
	
	/**
	 * Get the ServiceManagerResponseTimeMonitor.
	 */
	public ServiceManagerResponseTimeMonitor getSmResTimeMonitor() 
	{
		return smResTimeMonitor;
	}

	/**
	 * Set the ServiceManagerResponseTimeMonitor
	 */
	public void setSmResTimeMonitor(ServiceManagerResponseTimeMonitor smResTimeMonitor)
	{
		this.smResTimeMonitor = smResTimeMonitor;
	}
}