package com.csc.fsg.life.security.authentication;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import javax.naming.Context;
import javax.naming.NamingEnumeration;
import javax.naming.NamingException;
import javax.naming.directory.Attribute;
import javax.naming.directory.Attributes;
import javax.naming.directory.DirContext;
import javax.naming.directory.InitialDirContext;
import javax.naming.directory.SearchControls;
import javax.naming.directory.SearchResult;
import javax.security.auth.login.AppConfigurationEntry;
import javax.security.auth.login.Configuration;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.csc.fsg.life.exceptions.WrapperException;

/* Modifications: V-E323 */

/**
   A LDAP utility class to search LDAP driectory.  This is a singleton.
 */
public class LDAPUtils implements JaasUtils {

	private static Log logger = LogFactory.getLog(LDAPUtils.class);

	private static final String SECURITY_AUTHENTICATION = "simple";

	private static LDAPUtils instance;
	

	
	public synchronized static LDAPUtils getInstance() {
		if (instance == null)
            instance = new LDAPUtils();
		return instance;		
	}

	/**
	 * 
	 * This method uses find all users(object for given base dn(distinguished
	 * name) using user id and password.
	 * <p>
	 * It creates UserPrincipal Object for each user in given directory tree
	 * with attributes <br>
	 * commonName,givenName (firstName) , sn (last name) and list of uids
	 * 
	 * @param applicationName
	 * @param userID
	 * @param password
	 * @throws NamingException
	 * @throws Exception
	 */
	public List<UserPrincipal> getAllUsers(String applicationName, String userID,
							String password) 
	{
		Configuration jaasConfig = Configuration.getConfiguration();        
		AppConfigurationEntry entries[] = jaasConfig.getAppConfigurationEntry(applicationName);

		for (int i = 0; i < entries.length; i++) {
			AppConfigurationEntry entry = entries[i];
			Map<String,?> options = entry.getOptions();

			// provider URl
			String providerurl = (String) options.get("PROVIDER_URL");

			if (providerurl == null) {
				if (logger.isFatalEnabled())
					logger
							.fatal("No provider url specified in the configuration file");
				throw new IllegalArgumentException(
						"No provider Url specified in the configuration file");

			}
			if (logger.isDebugEnabled())
				logger.debug("PROVIDER_URL : using " + providerurl);

			// base dn
			String user_base_dn = (String) options.get("USER_BASE_DN");

			if (user_base_dn == null) {
				if (logger.isFatalEnabled())
					logger
							.fatal("No user_base_dn specified in the configuration file");
				throw new IllegalArgumentException(
						"No user_base_dn specified in the configuration file");
			}

			String securityPrincipal = null;

			securityPrincipal = "cn=" + userID + ", " + user_base_dn;
			if (logger.isDebugEnabled())
				logger.debug("BaseLdapLoginModule:  createSecurityPrincipals()");
				logger.debug("Security principal : using securityPrincipal"
						+ securityPrincipal);

			// security authentication
			String security_authentication = (String) options.get("SECURITY_AUTHENTICATION");

			if (security_authentication == null) {
				if (logger.isWarnEnabled())
					logger.warn("No security_authentication specified in the configuration file using default value: simple ");
				security_authentication = SECURITY_AUTHENTICATION;
			}

			// create directory Context with
			// supplied userID (distinguished name for given user )and password
			Hashtable<String,Object> env = new Hashtable<String,Object>(11);
			env.put(Context.INITIAL_CONTEXT_FACTORY,
					"com.sun.jndi.ldap.LdapCtxFactory");
			env.put(Context.PROVIDER_URL, providerurl);
			env.put(Context.SECURITY_AUTHENTICATION, security_authentication);
			env.put(Context.SECURITY_PRINCIPAL, securityPrincipal);
			env.put(Context.SECURITY_CREDENTIALS, password);
			if (logger.isDebugEnabled())
			    logger.debug("Establishing Directory Context");
            try {
                DirContext ctx = new InitialDirContext(env);
                List<UserPrincipal> allUsers = getAllUsers(ctx, user_base_dn);
                ctx.close();
                return allUsers;
            } catch (Exception e) {
                throw WrapperException.wrap(e);
            }

		}

		return null;
	}

	private static List<UserPrincipal> getAllUsers(DirContext ctx, String user_base_dn)
			throws NamingException {

		ArrayList<UserPrincipal> userPrincipalList = new ArrayList<UserPrincipal>();

		if (logger.isDebugEnabled())
			logger.debug("BaseLdapLoginModule:  populatePrincipals()");

		SearchControls ctls = new SearchControls();
		ctls.setSearchScope(SearchControls.SUBTREE_SCOPE);
		// create an array return attributes
		String returnAttrs[] = { "cn", "uid", "sn", "givenName" };
		ctls.setReturningAttributes(returnAttrs);

		// create filter to find the groups that user is member of
		String filter = "(objectClass=inetOrgPerson)";

		NamingEnumeration<SearchResult> answer = ctx.search(user_base_dn, filter, ctls);

		// List of commonName always one commonName
		// List of firstName (LDAP attribute givenName ) always one
		// List of lastName (LDAP attribute sn ) always one
		// List of uid's (LDAP attribute sn )

		while (answer.hasMoreElements()) {
			SearchResult userGroup = answer.nextElement();
			Attributes attributes = userGroup.getAttributes();

			// CommonName always one
			Attribute commonName = attributes.get("cn");
			String commonNameStr = (String) commonName.get(0);

			// firstName - LDAP givenName Attribute - always one
			Attribute givenName = attributes.get("givenName");
			String firstName = null;
			if(givenName != null)
				firstName = (String) givenName.get(0);

			// lastName = LDAP attribute - second name
			Attribute secondName = attributes.get("sn");
			String lastName = null;
			if(secondName != null)
				lastName = (String) secondName.get(0);

			// user id multiple values
			// get all the uid's for these user
			Attribute userIds = (Attribute) attributes.get("uid");
			ArrayList<String> userIDList = new ArrayList<String>();

			for (int i = 0; i < userIds.size(); i++) {
				// add user id to the list
				userIDList.add((String)userIds.get(i));
			}

			// create user principal and add to the list
			UserPrincipal userPrincipal = new UserPrincipal(commonNameStr,
					userIDList, firstName, lastName);
			userPrincipalList.add(userPrincipal);
		}

		if (logger.isDebugEnabled()) {
			for (int i = 0; i < userPrincipalList.size(); i++) {
				UserPrincipal userPrincipal = userPrincipalList
						.get(i);
				logger.debug(userPrincipal.toString());
			}
		}

		return userPrincipalList;
	}
}
