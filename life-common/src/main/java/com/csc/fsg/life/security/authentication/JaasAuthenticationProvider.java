package com.csc.fsg.life.security.authentication;

import java.security.Security;
import java.util.Map;

import javax.security.auth.callback.CallbackHandler;
import javax.security.auth.login.AppConfigurationEntry;
import javax.security.auth.login.Configuration;
import javax.security.auth.login.LoginContext;
import javax.security.auth.login.LoginException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.jaas.AbstractJaasAuthenticationProvider;
import org.springframework.security.authentication.jaas.event.JaasAuthenticationFailedEvent;
import org.springframework.security.core.AuthenticationException;

/* Modifications: ENH1220 */

public class JaasAuthenticationProvider
	extends AbstractJaasAuthenticationProvider
{
	protected static final Log log = LogFactory.getLog("com.csc.fsg");

	private String loginContextName = null;

	@Override
	public void setLoginContextName(String loginContextName)
	{
		super.setLoginContextName(loginContextName);
		this.loginContextName = loginContextName;
	}

	@Override
	public void afterPropertiesSet()
		throws Exception
	{
		super.afterPropertiesSet();

		AppConfigurationEntry[] configEntries = Configuration.getConfiguration().getAppConfigurationEntry(loginContextName);
		if (configEntries != null && configEntries.length > 0)
			for (Map.Entry<String, ?> entry : configEntries[0].getOptions().entrySet())
				Security.setProperty(entry.getKey(), (String) entry.getValue());
	}

	@Override
	protected LoginContext createLoginContext(CallbackHandler handler)
		throws LoginException
	{
		return new LoginContext(loginContextName, handler);
	}

	/**
	 * Publishes the {@link JaasAuthenticationFailedEvent}.
	 * 
	 * @param token
	 *        The authentication token being processed
	 * @param ase
	 *        The exception that caused the authentication failure
	 */
	@Override
	protected void publishFailureEvent(UsernamePasswordAuthenticationToken token, AuthenticationException ase)
	{
		// exists for passivity (the superclass does a null check before publishing)
		getApplicationEventPublisher().publishEvent(new JaasAuthenticationFailedEvent(token, ase));
	}
}
