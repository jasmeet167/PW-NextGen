package com.csc.fsg.life.security;

import java.io.Serializable;

import com.csc.fsg.life.context.UserContext;

/* Modifications: T0175 */

/**
 * Interface, which receives notification callbacks for login events.
 */
public interface LoginListener
	extends Serializable
{
	/**
	 * Perform any logic, which needs to be completed before the login is
	 * initiated.
	 */
	public void onBeforeLogin();

	/**
	 * Migrate user settings to new session.
	 */
	public void onUserContextInit(UserContext context);
}
