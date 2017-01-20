package com.csc.fsg.life.security.authentication;

import java.util.Arrays;
import java.util.List;

import org.springframework.security.authentication.AnonymousAuthenticationProvider;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;

/* Modifications: ENH1220 */

public class LifeAnonymousAuthenticationProvider
	extends AnonymousAuthenticationProvider
{
	static private final List<Integer> offHashCodes
		= Arrays.asList(Integer.valueOf("OFF".hashCode()), Integer.valueOf("Off".hashCode()), Integer.valueOf("off".hashCode()));

	public LifeAnonymousAuthenticationProvider(String key)
	{
		super(key);
	}

	/**
	 * Application value {@code OFF} has a special meaning in all applications
	 * based on the {@code life} architecture; it can be used to turn off
	 * security.<br>
	 * Therefore, if authentication key matching {@code OFF} is detected, the
	 * anonymous authentication will be considered successful.
	 */
	@Override
	public Authentication authenticate(Authentication authentication)
		throws AuthenticationException
	{
		if (!supports(authentication.getClass()))
			return null;

		if (offHashCodes.contains(Integer.valueOf(((AnonymousAuthenticationToken) authentication).getKeyHash())))
			return authentication;
		else
			return super.authenticate(authentication);
	}
}
