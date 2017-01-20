package com.csc.fsg.life.biz.bo;

import java.io.Serializable;

/* Modifications: ENH988 */

/**
 * Container used for transport of data related to Batch Event status inquiry.
 */
public class BatchEventStatusModelImpl
	implements BatchEventStatusModel, Serializable
{
	private static final long serialVersionUID = -984583920127451247L;

	private String operation = null;
	private Integer count = null;

	/**
	 * @return Current value of the property <em>operation</em>.
	 */
	public String getOperation()
	{
		return operation;
	}

	/**
	 * @param operation
	 *        New value of the property <em>operation</em>.
	 */
	public void setOperation(String operation)
	{
		this.operation = operation;
	}

	/**
	 * @return Current value of the property <em>count</em>.
	 */
	public Integer getCount()
	{
		return count;
	}

	/**
	 * @param count
	 *        New value of the property <em>count</em>.
	 */
	public void setCount(Integer count)
	{
		this.count = count;
	}
}
