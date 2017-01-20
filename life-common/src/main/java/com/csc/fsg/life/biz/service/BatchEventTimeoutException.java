package com.csc.fsg.life.biz.service;

/* Modifications: ENH988 */

public class BatchEventTimeoutException
	extends ServiceManagerException
{
	private static final long serialVersionUID = 8594038578237425485L;

	private BatchId batchId = null;

	/**
	 * Constructor.
	 */
	public BatchEventTimeoutException(Exception e, BatchId batchId)
	{
		super(e);
		this.batchId = batchId;
	}

	/**
	 * @return The instance of BatchId associated with this exception.
	 */
	public BatchId getBatchId()
	{
		return batchId;
	}
}
