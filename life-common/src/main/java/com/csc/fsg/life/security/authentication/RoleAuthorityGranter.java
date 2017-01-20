package com.csc.fsg.life.security.authentication;

import java.security.Principal;
import java.util.Collections;
import java.util.Set;

import org.springframework.security.authentication.jaas.AuthorityGranter;

/* Modifications: ENH1220 */

public class RoleAuthorityGranter
	implements AuthorityGranter
{
	public Set<String> grant(Principal principal)
	{
		return Collections.singleton(principal.getName().toUpperCase());
	}
}
