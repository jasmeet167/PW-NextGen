package com.csc.fsg.life.performance;

import com.csc.fsg.life.biz.exception.BusinessException;
import com.csc.fsg.life.biz.service.Service;
import com.csc.fsg.life.biz.service.ServiceParam;

/* Modifications: T0166 */

/**
 * This interface declares the service required by the performance log mechanism
 * to provide the mechanism to persist performance log data.
 * <p>
 * Metadata with security information is not specified in this interface,
 * because access to performance logging must not be restricted by security.
 */
public interface PerformanceLogWriterService
	extends Service
{
	/**
	 * Persist the data captured in the provided <code>activity</code>.
	 * 
	 * @param param
	 *        Encapsulated run-time information
	 * @param activity
	 *        Performance log activity to be persisted
	 */
	public void writePerformanceLogData(ServiceParam param, PerformanceLogActivity activity)
		throws BusinessException;
}
