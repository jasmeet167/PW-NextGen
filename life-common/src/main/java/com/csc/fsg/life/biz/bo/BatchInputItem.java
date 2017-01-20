package com.csc.fsg.life.biz.bo;

import java.io.Serializable;

import com.csc.fsg.life.biz.copyobject.CopyObject;

/* Modifications: ENH988 */

/**
 * Container for data associated with each transaction participating in a Batch
 * Event.
 */
public class BatchInputItem
	implements Serializable
{
	private static final long serialVersionUID = 990847589012391385L;

	private String trxEventId = null;
	private CopyObject copyObject = null;
	private String trxType = null;

	/**
	 * Constructor.
	 */
	public BatchInputItem(String trxEventId, CopyObject copyObject)
	{
		this.trxEventId = trxEventId;
		this.copyObject = copyObject;
	}

	/**
	 * Constructor with trxType.
	 */
	public BatchInputItem(String trxEventId, CopyObject copyObject, String trxType)
	{
		this.trxEventId = trxEventId;
		this.copyObject = copyObject;
		this.trxType = trxType;
	}
	/**
	 * @return Current value of the property <em>trxEventId</em>.
	 */
	public String getTrxEventId()
	{
		return trxEventId;
	}

	/**
	 * @return Current value of the property <em>copyObject</em>.
	 */
	public CopyObject getCopyObject()
	{
		return copyObject;
	}

	/**
	 * @param trxType the trxType to set
	 */
	public void setTrxType(String trxType) {
		this.trxType = trxType;
	}

	/**
	 * @return the trxType
	 */
	public String getTrxType() {
		return trxType;
	}
}
