package com.csc.fsg.life.context;

import com.csc.fsg.life.biz.bo.BusinessObjectException;

/**
 * An interface used for object that utilize the user context.
 */
public interface UserContextAware
{
	/**
	 * Initialize an object with the user context.
	 * 
	 * @param userContext	The user context.
	 * @throws BusinessObjectException	if an initialization problem occurs.
	 */
    public void init(UserContext userContext) throws BusinessObjectException;

	/**
	 * Initialize an object with the user context.
	 * 
	 * @param userContext	The user context.
	 * @param isForced	true if initialization should be forced.
	 * @throws BusinessObjectException	if an initialization problem occurs.
	 */
    public void init(UserContext userContext, boolean isForced) throws BusinessObjectException;

	/**
	 * Sets the user context.
	 * 
	 * @param userContext	The user context.
	 */
    public void setUserContext(UserContext userContext);
}
