package com.csc.fsg.life.biz.service;

import com.csc.fsg.life.biz.bo.CommArea;

/**
 * Generic interface for a service manager connection.
 */
public interface ServiceManagerConnection
{

	/**
	 * Called to send an event to the COBOL service manager.
	 * 
	 * @param commAreaInput the input to send to the Service Manager.
	 * @return The result from the service manager.
	 * @throws ServiceManagerException
	 *             If there is a problem sending or receiving the event.
	 */
    public CommArea sendEvent(CommArea commAreaInput)
        throws ServiceManagerException;
}
