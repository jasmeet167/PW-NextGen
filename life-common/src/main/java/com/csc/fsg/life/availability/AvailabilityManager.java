package com.csc.fsg.life.availability;

import java.io.Serializable;

import com.csc.fsg.life.biz.service.BusinessServiceException;
import com.csc.fsg.life.context.UserContext;

/* Modifications: T0153 */

/**
 * The interface implemented by a module used to verify availability of system
 * resources.
 */
public interface AvailabilityManager
	extends Serializable
{
	/**
	 * Check whether the database is available for update. If yes, then quietly
	 * return to caller. Otherwise throw an exception.
	 * 
	 * @param userContext
	 *        Encapsulated run-time information related to the current
	 *        application session.
	 * @exception BusinessServiceException
	 */
	public void checkDatabaseUpdateAvailability(UserContext userContext)
		throws BusinessServiceException;
}
