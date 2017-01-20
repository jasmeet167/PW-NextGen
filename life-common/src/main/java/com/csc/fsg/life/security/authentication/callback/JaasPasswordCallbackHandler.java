package com.csc.fsg.life.security.authentication.callback;

import javax.security.auth.callback.Callback;
import javax.security.auth.callback.PasswordCallback;

import org.springframework.security.authentication.jaas.JaasAuthenticationCallbackHandler;
import org.springframework.security.core.Authentication;

/* Modifications: ENH1220 */

public class JaasPasswordCallbackHandler
	implements JaasAuthenticationCallbackHandler
{
	public void handle(Callback callback, Authentication auth)
	{
		if (callback instanceof PasswordCallback) {
			PasswordCallback pc = (PasswordCallback) callback;
			pc.setPassword((char[]) auth.getCredentials());
		}
	}
}
