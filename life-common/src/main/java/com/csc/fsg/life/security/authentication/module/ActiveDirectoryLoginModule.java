package com.csc.fsg.life.security.authentication.module;

import java.util.ArrayList;
import java.util.Map;
import java.util.Properties;

import javax.naming.NamingEnumeration;
import javax.naming.NamingException;
import javax.naming.directory.Attribute;
import javax.naming.directory.Attributes;
import javax.naming.directory.DirContext;
import javax.naming.directory.SearchControls;
import javax.naming.directory.SearchResult;
import javax.security.auth.login.LoginException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.util.StringUtils;

import com.csc.fsg.life.security.authentication.GroupToRoleManager;
import com.csc.fsg.life.security.authentication.UserPrincipal;

/* Modifications: T0173, WMA-1718 */

/**
 * The <code>ActiveDirectoryLoginModule</code> class implements methods to
 * create the search string for the specific to Windows Active Directory.<br>
 * The Login for active directory will always be using sAMAccountName with given
 * domain the domain is set up in jaas config file as ACTIVE_DIRECTORY_DOMAIN.
 */
public class ActiveDirectoryLoginModule
	extends BaseLdapLoginModule
{
	private static Log logger = LogFactory.getLog(ActiveDirectoryLoginModule.class);

	private String searchFilter = null;

	/**
	 * @see AbstractLdapLoginModule#createSecurityPrincipal(String, Map)
	 */
	@Override
	public void createSecurityPrincipal(String user, Map<String, ?> options)
	{
		if (logger.isInfoEnabled())
			logger.info("ActiveDirectoryLoginModule:  createSecurityPrincipals()");

		String activeDirectoryDomain = (String) options.get("ACTIVE_DIRECTORY_DOMAIN");

		securityPrincipal = user + "@" + activeDirectoryDomain;
		if (logger.isDebugEnabled())
			logger.debug("Security principal: using securityPrincipal " + securityPrincipal);

		String userBaseDn = "(sAMAccountName=" + user + ")";
		String searchAtt = "(" + (String) options.get("SEARCH_ATTRIBUTE_FOR_GROUP") + "=*)";
		searchFilter = "(&" + userBaseDn + searchAtt + ")";
	}

	/**
	 * @see com.csc.fsg.life.security.authentication.BaseLoginModule#populatePrincipals(DirContext, Map, Map)
	 */
	@Override
	public void populatePrincipals(DirContext ctx, Map<String, ?> sharedSate, Map<String, ?> options)
		throws NamingException, LoginException
	{
		if (logger.isInfoEnabled())
			logger.info("ActiveDirectoryLoginModule:  populatePrincipals()");

		if (logger.isDebugEnabled()) {
			String app = (String) options.get("APPLICATION");
			Properties groupsToRoleMapProperties = GroupToRoleManager.getInstance().get(app);
			logger.debug(getDirectoryProtocol()
					  + " Group to Role Map for application "
					  + app
					  + ": "
					  + groupsToRoleMapProperties.toString());
		}

		SearchControls ctls = new SearchControls();

		// Create an array return attributes
		// String returnAttrs[] = { "memberOf, sAMAccountName" };
		// ctls.setReturningAttributes(returnAttrs);

		NamingEnumeration<SearchResult> answer = ctx.search((String) options.get("USER_BASE_DN"), searchFilter, ctls);
		if (!answer.hasMoreElements())
			throw new LoginException("No groups defined for given user");

		// Get each group the user is member of
		while (answer.hasMoreElements()) {
			SearchResult userGroup = (SearchResult) answer.nextElement();
			Attributes attributes = userGroup.getAttributes();
			Attribute attribute = attributes.get("memberOf");

			for (int i = 0; i < attribute.size(); i++) {
				String ldapGroupStr[] = StringUtils.commaDelimitedListToStringArray((String) attribute.get(i));
				String groupDn = ldapGroupStr[0];
				String ldapGroupCn = groupDn.substring(groupDn.indexOf("=") + 1, groupDn.length());
				addGroupsToSubject(ldapGroupCn, sharedSate, options);
			}

			attribute = attributes.get("sAMAccountName");
			String userCommonName = "";
			for (int i = 0; i < attribute.size(); i++)
				userCommonName = (String) attribute.get(i);

			attribute = attributes.get("uid");
			ArrayList<String> userIDList = new ArrayList<String>();
			if (attribute != null) {
				for (int i = 0; i < attribute.size(); i++) {
					String userId = (String) attribute.get(i);
					userIDList.add(userId);
				}
			}

			// Create user principal with the common name and user id list
			UserPrincipal userPrincipal = new UserPrincipal(userCommonName, userIDList);
			if (!(subject.getPrincipals().contains(userPrincipal)))
				subject.getPrincipals().add(userPrincipal);
		}

		// Close the context
		ctx.close();
	}

	/**
	 * @return String A text label used to identify the protocol used to access
	 *         the directory service. This method is intended to be overridden
	 *         in any subclass, with an applicable protocol identifier.
	 *         <p>
	 *         The returned value is used for logging.
	 */
	@Override
	protected String getDirectoryProtocol()
	{
		return "Active Directory";
	}
}
