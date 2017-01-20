package com.csc.fsg.life.security.authentication.module;

import java.io.IOException;
import java.util.Collections;
import java.util.Hashtable;
import java.util.Map;

import javax.naming.Context;
import javax.naming.NamingException;
import javax.naming.directory.DirContext;
import javax.naming.directory.InitialDirContext;
import javax.security.auth.Subject;
import javax.security.auth.callback.Callback;
import javax.security.auth.callback.CallbackHandler;
import javax.security.auth.callback.NameCallback;
import javax.security.auth.callback.PasswordCallback;
import javax.security.auth.callback.UnsupportedCallbackException;
import javax.security.auth.login.LoginException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.csc.fsg.life.security.authentication.BaseLoginModule;

/* Modifications: T0120, T0173, T0175 */

/**
 * The <code>AbstractLdapLoginModule</code> class is base implementation for all <br>
 * LDAP compliant directory server this class should be inherited to <br> 
 * implement the createSecurityPrincipal().
 * 
 * <Note>
 * A directiory servers with different search criteria and string representation of Distinguished name
 * will result in a new implementation inherting AbstractLdapLoginModule.
 * </Note>
 * @see com.csc.fsg.life.security.authentication.module.ActiveDirectoryLoginModule 
 */
public abstract class AbstractLdapLoginModule 
	implements BaseLoginModule 
{
	/**
	 * A Subject represents a grouping of related information for a single entity, such as a person.
	 * Such information includes the Subject's identities as well as its security-related attributes.
	 */
	protected Subject subject;	

	private CallbackHandler callbackHandler;

	private Map<String, ?> options;

	private Map<String, ?> sharedState;
	
	/**
	 * LDAP Property SECURITY_PRINCIPAL is created using userid and User_base_dn property.
	 */
	protected String securityPrincipal;

	private boolean loginSuccess;
	
	private static Log logger = LogFactory.getLog(AbstractLdapLoginModule.class);

	private static final String SECURITY_AUTHENTICATION = "simple";

	/**
	 * @see javax.security.auth.spi.LoginModule#abort()
	 */
	public boolean abort() throws LoginException {
		if (loginSuccess == false) {
			if (logger.isDebugEnabled())
				logger.debug("Abort : LdapLoginModule FAIL");			
			return false;
		}
		if (logger.isDebugEnabled())
			logger.debug("Abort: LdapLoginModule SUCCESS");
		logout();
		return true;
	}

	/**
	 * @see javax.security.auth.spi.LoginModule#commit()
	 */
	public boolean commit() throws LoginException {
		if (loginSuccess == false) {
			if (logger.isDebugEnabled())
				logger.debug("Commit LdapLoginModule SUCCESS");
			return false;
		}

		// If Login succeeded
		if (logger.isDebugEnabled())
			logger.debug("Commit LdapLoginModule SUCCESS");
		return true;
	}

	/**
	 * @see javax.security.auth.spi.LoginModule#login()
	 */
	public boolean login() throws LoginException {
		try {
			if (callbackHandler == null) {
				// Should never happen
				if (logger.isFatalEnabled())
					logger.fatal("No callback defined");
				throw new LoginException("No callback defined");
			}

			Callback[] callbacks = new Callback[2];
			callbacks[0] = new NameCallback("UserName");
			callbacks[1] = new PasswordCallback("Password", false);

		
			if (logger.isDebugEnabled())
				logger.debug("LdapLoginModule Login");			
			callbackHandler.handle(callbacks);
			
			String user = ((NameCallback) callbacks[0]).getName();
			char[] password = ((PasswordCallback) callbacks[1]).getPassword();			

			// Provider URL
			String providerurl = (String) options.get("PROVIDER_URL");

			if (providerurl == null) {
				if (logger.isFatalEnabled())
					logger.fatal("No provider url specified in the configuration file");
				throw new LoginException("No provider Url specified in the configuration file");
						
			}
			if (logger.isDebugEnabled())
				logger.debug("PROVIDER_URL : using " + providerurl);

			// User dn
			String user_base_dn = (String) options.get("USER_BASE_DN");

			if (user_base_dn == null) {
				if (logger.isFatalEnabled())
					logger.fatal("No user_base_dn specified in the configuration file");
				throw new LoginException(
						"No user_base_dn specified in the configuration file");
			}

			createSecurityPrincipal(user, options);

			// Security authentication
			String security_authentication = (String) options
					.get("SECURITY_AUTHENTICATION");

			if (security_authentication == null) {
				if (logger.isWarnEnabled())
					logger.warn("No security_authentication specified in the configuration file using default value: simple ");
				security_authentication = SECURITY_AUTHENTICATION;
			}

			// Create directory Context with supplied userID 
			// (distiguinshed name for given user), and password
			Hashtable<String, Object> env = new Hashtable<String, Object>(11);			
			env.put(Context.INITIAL_CONTEXT_FACTORY,
					"com.sun.jndi.ldap.LdapCtxFactory");
			env.put(Context.PROVIDER_URL, providerurl);
			env.put(Context.SECURITY_AUTHENTICATION, security_authentication);
			env.put(Context.SECURITY_PRINCIPAL, securityPrincipal);
			env.put(Context.SECURITY_CREDENTIALS, password);
			logger.info("Establishing Directory Context");
			DirContext ctx = new InitialDirContext(env);			
			loginSuccess = true;						
			populatePrincipals(ctx, sharedState, options);				
		}
		catch (NamingException e) {
			String message = (e.getRootCause() == null) ? e.getMessage() : e.getRootCause().getMessage();
			logger.error("LDAP Naming Error: " + message);
			throw new LoginException("Invalid User Name and/or Password.");
		}
		catch (UnsupportedCallbackException e) {
			logger.error("LDAP UnsupportedCallback Error: " + e.getMessage());
			throw new LoginException("Invalid User Name and/or Password.");
		}
		catch (IOException e) {
			logger.error("LDAP IO Error: " + e.getMessage());
			throw new LoginException("Invalid User Name and/or Password.");
		}
		return true;
	}

	/**
	 * @see javax.security.auth.spi.LoginModule#logout()
	 */
	public boolean logout() throws LoginException {

		// Remove all principal from the subject
		subject.getPrincipals().clear();		
		loginSuccess = false;		
		if (logger.isDebugEnabled())
			logger.debug("Logout: LdapLoginModule SUCCESS");

		return true;
	}

	/**
	 * @see javax.security.auth.spi.LoginModule#initialize(javax.security.auth.Subject,
	 *                                                     javax.security.auth.callback.CallbackHandler,
	 *                                                     Map, Map)
	 */
	public void initialize(Subject subject, CallbackHandler callbackHandler,
							Map<String, ?> sharedState, Map<String, ?> options) {

		if (logger.isInfoEnabled())
			logger.info("intializing LdapLoginModule");

		this.subject = subject;
		this.callbackHandler = callbackHandler;
		this.options = Collections.unmodifiableMap(options);
		this.sharedState = sharedState;
		loginSuccess = false;
	}

	/**
	 * Implementation to create security principal for an LDAP directory server
	 * implementation.<br>
	 * For Active Directory or any other LDAP compliant server which has
	 * different string representation should implement this method to create
	 * security principal explained in the security doc base implementation
	 * (based on CSC proposed Directory Tree) is provided in class.
	 * 
	 * @see BaseLdapLoginModule
	 * 
	 * @param user
	 *        The userid used to autheticate user.
	 * @param options
	 *        Options specified in the login <code>Configuration</code> for this
	 *        particular <code>LoginModule</code>.
	 */
	public abstract void createSecurityPrincipal(String user, Map<String, ?> options);
}
