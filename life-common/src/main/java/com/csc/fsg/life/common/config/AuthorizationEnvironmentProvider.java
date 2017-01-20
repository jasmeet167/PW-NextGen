package com.csc.fsg.life.common.config;

/* Modifications: ENH1220 */

public interface AuthorizationEnvironmentProvider
{
	/**
	 * @return Current value of the property {@code authorizationEnvironment}
	 */
	public String getAuthorizationEnvironment();
}
