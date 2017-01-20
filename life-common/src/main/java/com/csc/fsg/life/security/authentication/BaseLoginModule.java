package com.csc.fsg.life.security.authentication;

import java.util.Map;

import javax.naming.NamingException;
import javax.naming.directory.DirContext;
import javax.security.auth.login.LoginException;
import javax.security.auth.spi.LoginModule;

/* Modifications: T0120, T0173 */

/**
 * Application specific LoginModules implements the <code>BaseLoginModule</code> 
 * interface. It populates principals for a authenticated subject based on ldap compliant
 * directory server.
 * <p> Populate principals methods searches dircontext to get Groups that the user is memebr of
 *  
 * @see javax.security.auth.spi.LoginModule
 */
public interface BaseLoginModule extends LoginModule{
	
	/**
	 * This method is used to populate principals (LDAP Groups) for the given user
	 * @param ctx the Directory Context 
	 * @param sharedState - map of shared state between diff moduleas 
	 * @param options - map of options 
	 * @throws NamingException
	 */
	public void populatePrincipals(DirContext ctx, Map<String, ?> sharedState, Map<String, ?> options)
			throws NamingException, LoginException;
}
