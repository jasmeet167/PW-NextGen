package com.csc.fsg.life.security;

import com.csc.fsg.life.context.UserContext;

/* Modifications: T0175 */

/**
 * Default implementation of the recipient of login process notifications.
 */
public class EmptyLoginListener
	implements LoginListener
{
	private static final long serialVersionUID = 7150007762701413917L;

	/**
	 * @see LoginListener#onBeforeLogin()
	 */
	public void onBeforeLogin()
	{
		// no-op
	}

	/**
	 * @see LoginListener#onUserContextInit(UserContext)
	 */
	public void onUserContextInit(@SuppressWarnings("unused") UserContext context)
	{
		// no-op
	}
}
