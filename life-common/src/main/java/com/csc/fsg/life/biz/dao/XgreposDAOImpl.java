package com.csc.fsg.life.biz.dao;

import java.sql.SQLException;
import java.util.Iterator;
import java.util.List;

import org.apache.ibatis.session.ExecutorType;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.DuplicateKeyException;

import com.csc.fsg.life.biz.bo.BatchEventStatusModel;
import com.csc.fsg.life.biz.bo.XgreposModel;
import com.csc.fsg.life.biz.service.BatchId;
import com.csc.fsg.life.context.UserContext;

/* Modifications: ENH863, ENH988, T0140, T0166, WMA-1638, T0199 */

/**
 * Class used for data access to the XGREPOS table.
 */
public class XgreposDAOImpl 
	extends BaseSqlSessionSupport 
	implements XgreposDAO
{
	/**
	 * Build an XgreposDAOImpl class from a UserContext.
	 * 
	 * @param context The User Context.
	 */
    public XgreposDAOImpl(UserContext context)
    {
        super(context);
    }
    
    /**
     * Build an XgreposDAOImpl class from a UserContext and ExecutorType.
     * 
     * @param context The User Context
     * @param executorType Type of Executor Simple or Batch
     */
    public XgreposDAOImpl(UserContext context, ExecutorType executorType)
    {
    	super(context, executorType);
    }
    
	/**
	 *	Insert a record into XGREPOS utilizing the data in the given model.
	 */
	public void insertXGREPOS(XgreposModel model)
	{
		try {
			getSqlSessionTemplateDelegate().insert("insertXGREPOS", model);
		}
		catch (DuplicateKeyException e) {
			// Duplicate key condition detected in DB2/UDB
			throw new DuplicateMessageKeyException(e.getMessage(), e);
		}
		catch (DataAccessException e) {
			if (isXdbDuplicateKeyException(e.getRootCause())) {
				// Duplicate key condition detected in MicroFocus XDB 
				throw new DuplicateMessageKeyException(e.getMessage(), e);
			}
			throw e;
		}
	}
	
    /**
     *  Batch insert of records into XGREPOS utilizing the given list of models.
     */
	public void insertXGREPOS(List<XgreposModel> list)
	{
		try {
			batchInsertXGREPOS(list);
		}
		catch (DataIntegrityViolationException e) {
			// Duplicate key condition detected in DB2/UDB
			throw new DuplicateMessageKeyException(e.getMessage(), e);
		}
		catch (DataAccessException e) {
			if (e.getRootCause() instanceof SQLException) {
				SQLException rootCause = (SQLException) e.getRootCause();
				SQLException chainedException = rootCause.getNextException();

				while (chainedException != null) {
					if (isXdbDuplicateKeyException(chainedException)) {
						// Duplicate key condition detected in MicroFocus XDB 
						throw new DuplicateMessageKeyException(e.getMessage(), e);
					}
					chainedException = chainedException.getNextException();
				}
			}
			throw e;
		}
	}

    private void batchInsertXGREPOS(List<XgreposModel> list)
    {
    	SqlSessionTemplateDelegate sqlSessionTemplateDelegate = getSqlSessionTemplateDelegate();
    	Iterator<XgreposModel> itr = list.iterator();
    	
    	try {
	    	sqlSessionTemplateDelegate.beginBatch();
			while (itr.hasNext()) {
	            XgreposModel model = itr.next();
	            sqlSessionTemplateDelegate.insert("insertXGREPOS", model);
	        }
		}
		finally {
			sqlSessionTemplateDelegate.endBatch();
		}
    }
    
	private boolean isXdbDuplicateKeyException(Throwable t)
	{
		if (t instanceof SQLException) {
			SQLException sqlException = (SQLException) t;

			if ("S1000".equals(sqlException.getSQLState())
			 && sqlException.getErrorCode() == -18
			 && sqlException.getMessage() != null
			 && sqlException.getMessage().indexOf("X18") >= 0) {
				// This condition indicates a unique key constraint
				// violation in Microfocus XDB database
				return true;
			}
		}

		return false;
	}

	/**
	 *	Update the XGREPOS record represented by the given model.
	 */
	public void updateXGREPOS(XgreposModel model)
	{
		getSqlSessionTemplateDelegate().update("updateXGREPOS", model);
	}
	
	/**
	 *	Update the responseStatus on the XGREPOS records represented by the given model.
	 */
	public void updateXGREPOSResponse(XgreposModel model)
	{
		getSqlSessionTemplateDelegate().update("updateXGREPOSResponse", model);
	}
	
	/**
	 *	Delete the XGREPOS record represented by the given model.
	 */
	public void deleteXGREPOS(XgreposModel model)
	{
		getSqlSessionTemplateDelegate().delete("deleteXGREPOS", model);
	}
    
    /**
     *  Delete the XGREPOS records related to a particular event.
     */
    public void deleteEventFromXGREPOS(XgreposModel model)
    {
        getSqlSessionTemplateDelegate().delete("deleteEventFromXGREPOS", model);
    }

    /**
     *  Delete the XGREPOS records related to a particular user.
     */
    public void deleteUserFromXGREPOS(XgreposModel model)
    {
        getSqlSessionTemplateDelegate().delete("deleteUserFromXGREPOS", model);
    }
	
    /**
     *  Count the XGREPOS records related to a particular user.
     */
    public Integer countUserFromXGREPOS(XgreposModel model)
    {
        return getSqlSessionTemplateDelegate().selectOne("countUserFromXGREPOS", model);
    }
	
	/**
	 *	Query XGREPOS for all records.
	 */
	public List<XgreposModel> selectAllFromXGREPOS()
	{
		return getSqlSessionTemplateDelegate().selectList("selectAllFromXGREPOS", null);
	}
	
	/**
	 *	Query XGREPOS for 1 or many records.  The query will be
	 *	dynamically created based upon the populated attributes in the
	 *	given model.
	 */
	public List<XgreposModel> selectListFromXGREPOS(XgreposModel model)
	{
		return getSqlSessionTemplateDelegate().selectList("selectListFromXGREPOS", model);
	}
    
    /**
     * Query XGREPOS for any result records excluding the error record.
     */
	public List<XgreposModel> selectResultsFromXGREPOS(XgreposModel model)
    {
        return getSqlSessionTemplateDelegate().selectList("selectResultsFromXGREPOS", model);
    }
    
    /**
     * Query XGREPOS for any "response" records that are pending.  This means
     * that the RESPONSE_STATUS has not yet been set to 'R', which signifies that
     * the response has been received.
     */
	public List<XgreposModel> selectPendingResponsesFromXGREPOS(XgreposModel model)
    {
        return getSqlSessionTemplateDelegate().selectList("selectPendingResponsesFromXGREPOS", model);
    }
    
    /**
     * Query XGREPOS for "request" records that have not yet received a response.  
     * This means that there is not a corresponding "response" row in the table.
     */
	public List<XgreposModel> selectInProgressRecordsFromXGREPOS(XgreposModel model)
    {
        return getSqlSessionTemplateDelegate().selectList("selectInProgressRecordsFromXGREPOS", model);
    }

	/**
	 * Query XGEREPOS for list of Batch Events initiated by the user identified
	 * by <em>userId</em>, and not yet marked as received. The return List
	 * includes only enough information to to identify each Batch Event: the
	 * first row of the first transaction.
	 * 
	 * @param userId
	 *        Id of the user, whose Batch Events are to be returned.
	 * 
	 * @return List of rows with key information for each applicable Batch
	 *         Event.
	 */
	public List<XgreposModel> selectBatchEventsForUser(String userId)
	{
		String userIdNormalized = userId.toUpperCase();
		List<XgreposModel> result = getSqlSessionTemplateDelegate().selectList("selectBatchEventsForUserFromXGREPOS", userIdNormalized);
		return result;
	}

	/**
	 * Query XGREPOS for total count of transactions participating in a Batch
	 * Event indicated by the property <em>messageKey</em> of the given
	 * <em>model</em>, and count of the transactions, which already have
	 * associated responses from the host system.
	 * 
	 * @param batchId
	 *        Container for key information used to identify a Batch Event.
	 * 
	 * @return A list of two instances of BatchEventStatusModel, one containing
	 *         total count of transactions (OPERATION = "A"), and one with count
	 *         of transactions, which have been responded to (OPERATION = "R").
	 */
	public List<BatchEventStatusModel> selectBatchEventStatus(BatchId batchId)
	{
		List<BatchEventStatusModel> result = getSqlSessionTemplateDelegate().selectList("selectBatchEventStatusFromXGREPOS", batchId);
		return result;
	}

	/**
	 * Query XGREPOS for rows representing cancelled transactions in a Batch Event.  
	 * This inquiry will return only rows with SUB_SEQ_NO of 0.
	 * 
	 * @param batchId
	 *        Container for key information used to identify a Batch Event.
	 * 
	 * @return List of rows, corresponding to cancelled transactions.
	 */
	public List<XgreposModel> selectBatchEventCancelledItems(BatchId batchId)
	{
		List<XgreposModel> result = getSqlSessionTemplateDelegate().selectList("selectBatchEventCancelledItemsFromXGREPOS", batchId);
		return result;
	}
	
	/**
	 * Identify all unprocessed XGREPOS rows corresponding to the Batch Event
	 * indicated by <em>batchId</em>, and mark them as canceled, by setting the
	 * value of STATUS to <code>X</code>.
	 * 
	 * @param batchId
	 *        Key information used to uniquely identify the invoked Batch Event.
	 * 
	 * @return The number of XGREPOS rows actually canceled.
	 */
	public int cancelRemainingBatchEvent(BatchId batchId)
	{
		int rowCount = getSqlSessionTemplateDelegate().update("cancelRemainingBatchEvent", batchId);
		return rowCount;
	}

	/**
	 * Change RESPONSE_STATUS to "R" in every XGREPOS row corresponding to the
	 * Batch Event indicated by <em>batchId</em>, thus indicating that the
	 * response has been received and processed.
	 * 
	 * @param batchId
	 *        Key information used to uniquely identify the invoked Batch Event.
	 */
	public void indicateBatchEventResponseReceived(BatchId batchId)
	{
		getSqlSessionTemplateDelegate().update("indicateBatchEventResponseReceived", batchId);
	}

	/**
	 * Delete from XGREPOS all rows corresponding to the Batch Event indicated
	 * by <em>batchId</em>.
	 * 
	 * @param batchId
	 *        Key information used to uniquely identify the invoked Batch Event.
	 */
	public void deleteBatchEventFromXGREPOS(BatchId batchId)
	{
		getSqlSessionTemplateDelegate().delete("deleteBatchEventFromXGREPOS", batchId);
	}
}
