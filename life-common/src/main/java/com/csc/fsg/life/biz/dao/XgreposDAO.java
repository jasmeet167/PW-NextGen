package com.csc.fsg.life.biz.dao;

import java.util.List;

import com.csc.fsg.life.biz.bo.BatchEventStatusModel;
import com.csc.fsg.life.biz.bo.XgreposModel;
import com.csc.fsg.life.biz.service.BatchId;

/* Modifications: ENH863, ENH988, T0199 */

/**
 * Interface used for data access to the XGREPOS table.
 */
public interface XgreposDAO
{
	/**
	 *	Insert a record into XGREPOS utilizing the data in the given model.
	 */
	public void insertXGREPOS(XgreposModel model);

    /**
     *  Batch insert of records into XGREPOS utilizing the given list of models.
     */
	public void insertXGREPOS(List<XgreposModel> list);
    
	/**
	 *	Update the XGREPOS record represented by the given model.
	 */
	public void updateXGREPOS(XgreposModel model);
	
	/**
	 *	Update the responseStatus on the XGREPOS records represented by the given model.
	 */
	public void updateXGREPOSResponse(XgreposModel model);
	
	/**
	 *	Delete the XGREPOS record represented by the given model.
	 */
	public void deleteXGREPOS(XgreposModel model);
	
    /**
     *  Delete the XGREPOS records related to a particular event.
     */
    public void deleteEventFromXGREPOS(XgreposModel model);
    
    /**
     *  Delete the XGREPOS records related to a particular user.
     */
    public void deleteUserFromXGREPOS(XgreposModel model);

    /**
     *  Count the XGREPOS records related to a particular user.
     */
    public Integer countUserFromXGREPOS(XgreposModel model);
    
	/**
	 *	Query XGREPOS for all records.
	 */
	public List<XgreposModel> selectAllFromXGREPOS();

	/**
	 *	Query XGREPOS for 1 or many records.  The query will be
	 *	dynamically created based upon the populated attributes in the
	 *	given model.
	 */
	public List<XgreposModel> selectListFromXGREPOS(XgreposModel model);
    
    /**
     * Query XGREPOS for any result records excluding the error record.
     */
	public List<XgreposModel> selectResultsFromXGREPOS(XgreposModel model);
    
    /**
     * Query XGREPOS for any "response" records that are pending.  This means
     * that the RESPONSE_STATUS has not yet been set to 'R', which signifies that
     * the response has been received.
     */
	public List<XgreposModel> selectPendingResponsesFromXGREPOS(XgreposModel model);
    
    /**
     * Query XGREPOS for "request" records that have not yet received a response.  
     * This means that there is not a corresponding "response" row in the table.
     */
	public List<XgreposModel> selectInProgressRecordsFromXGREPOS(XgreposModel model);

    /**
     * Query XGEREPOS for list of Batch Events initiated by the user identified 
     * by <em>userId</em>, and not yet marked as received.  The return List 
     * includes only enough information to to identify each Batch Event: the first 
     * row of the first transaction.
     * 
     * @param userId
     *        Id of the user, whose Batch Events are to be returned.
     *        
     * @return List of rows with key information for each applicable Batch Event.
     */
    public List<XgreposModel> selectBatchEventsForUser(String userId);

    /**
     * Query XGREPOS for total count of transactions participating in a Batch Event 
     * indicated by the property <em>messageKey</em> of the given <em>model</em>, 
     * and count of the transactions, which aready have associated responses from
     * the host system.
     * 
     * @param batchId
     *        Container for key information used to identify a Batch Event.
     *        
     * @return A list of two instances of BatchEventStatusModel, one containing 
     *         total count of transactions (OPERATION = "A"), and one with count
     *         of transactions, which have been responded to (OPERATION = "R").  
     */
    public List<BatchEventStatusModel> selectBatchEventStatus(BatchId batchId);
    
	/**
	 * Query XGREPOS for rows representing cancelled transactions in a Batch Event.  
	 * This inquiry will return only rows with SUB_SEQ_NO of 0.
	 * 
	 * @param batchId
	 *        Container for key information used to identify a Batch Event.
	 * 
	 * @return List of rows, corresponding to cancelled transactions.
	 */
	public List<XgreposModel> selectBatchEventCancelledItems(BatchId batchId);
    
	/**
	 * Identify all unprocessed XGREPOS rows corresponding to the Batch Event 
	 * indicated by <em>batchId</em>, and mark them as cancelled, by setting 
	 * the value of STATUS to <code>X</code>.
	 * 
	 * @param batchId
	 *        Key information used to uniquely identify the invoked Batch Event.
	 * 
	 * @return The number of XGREPOS rows actually cancelled.
	 */
    public int cancelRemainingBatchEvent(BatchId batchId);

	/**
	 * Change RESPONSE_STATUS to "R" in every XGREPOS row corresponding 
	 * to the Batch Event indicated by <em>batchId</em>, thus indicating 
	 * that the response has been received and processed.
	 * 
	 * @param batchId
	 *        Key information used to uniquely identify the invoked Batch Event.
	 */
    public void indicateBatchEventResponseReceived(BatchId batchId);
    
	/**
	 * Delete from XGREPOS all rows corresponding to the Batch Event 
	 * indicated by <em>batchId</em>.
	 * 
	 * @param batchId
	 *        Key information used to uniquely identify the invoked Batch Event.
	 */
    public void deleteBatchEventFromXGREPOS(BatchId batchId);
}
