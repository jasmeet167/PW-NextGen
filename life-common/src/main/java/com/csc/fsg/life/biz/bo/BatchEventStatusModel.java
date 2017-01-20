package com.csc.fsg.life.biz.bo;

/* Modifications: ENH988 */

/**
 * Interface implemented by the container used for transport of data related to
 * Batch Event status inquiry.
 */
public interface BatchEventStatusModel
{
	/**
	 * @return Current value of the property <em>operation</em>.
	 */
	public String getOperation();

	/**
	 * @param operation
	 *        New value of the property <em>operation</em>.
	 */
	public void setOperation(String operation);

	/**
	 * @return Current value of the property <em>count</em>.
	 */
	public Integer getCount();

	/**
	 * @param count
	 *        New value of the property <em>count</em>.
	 */
	public void setCount(Integer count);
}
