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

import com.csc.fsg.life.security.authentication.BaseLoginModule;
import com.csc.fsg.life.security.authentication.GroupToRoleManager;
import com.csc.fsg.life.security.authentication.UserGroupPrincipal;
import com.csc.fsg.life.security.authentication.UserPrincipal;

/* Modifications: T0120, T0173, WMA-1718 */

/**
 * The
 * <code> BaseLdapLoginModule <code> class implements methods to create the search string for the 
 * Directory server and finds groups that a given user is member of by implementing populatePrincipal method
 * <p> The LDAP groups for a user can be mapped to application roles by updating groupToRolesMap.properties file <br>
 * in this package. if the groupToRolesMap.properties field is empty or null then LDAP groups will be <br>
 * returned as a userGroupPrincipal else The APPLication roles will be returned as userGroupPrincipal
 */
public class BaseLdapLoginModule 
	extends AbstractLdapLoginModule 
	implements BaseLoginModule 
{
	private static Log logger = LogFactory.getLog(BaseLdapLoginModule.class);

	/**
	 * @see AbstractLdapLoginModule#createSecurityPrincipal(String, Map)
	 */
	@Override
	public void createSecurityPrincipal(String user, Map<String, ?> options) 
	{
		if (logger.isInfoEnabled())
			logger.info("BaseLdapLoginModule:  createSecurityPrincipals()");
		
		// Check for a CN override, if not default to CN.
		String cn = "cn";
		String overrideCn = (String) options.get("USER_CN");
		if ((overrideCn != null) && (overrideCn.trim().length() > 0))
			cn = overrideCn;

		String user_base_dn = (String) options.get("USER_BASE_DN");
		securityPrincipal = cn + "=" + user + "," + user_base_dn;
		
		if (logger.isDebugEnabled())
			logger.debug("Security principal: using securityPrincipal " + securityPrincipal);
	}

	/**
	 * @see com.csc.fsg.life.security.authentication.BaseLoginModule#populatePrincipals(javax.naming.directory.DirContext,
	 *      java.util.Map, java.util.Map)
	 */
	public void populatePrincipals(DirContext ctx, Map<String, ?> sharedSate, Map<String, ?> options)
			throws NamingException,LoginException {
		
		if (logger.isInfoEnabled())
			logger.info("BaseLdapLoginModule:  populatePrincipals()");

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
		// create an array return attributes
		String returnAttrs[] = { "cn" };
		ctls.setReturningAttributes(returnAttrs);

		// create filter to find the groups that user is member of
		// attForSearchingGroupUserIsMemberOf represent what attribute to use to find groups for given user
		// can differ based on the LDap object class used for groups
		// for example for groupOfName Object class the attribute is "member"
		// for groupofUniqueName ObjectClass the attribute is "uniqueMember"
		String searchAtt =  (String)options.get("SEARCH_ATTRIBUTE_FOR_GROUP");
		
		String filter = "("+ searchAtt+"=" + securityPrincipal + ")";

		NamingEnumeration<SearchResult> answer = ctx.search((String) options.get("GROUP_BASE_DN"),
				filter, ctls);
		
		if (!answer.hasMoreElements())
			throw new LoginException("No groups defined for given user");

		// get each group the user is member of
		while (answer.hasMoreElements()) {
			SearchResult userGroup = answer.nextElement();
			Attributes attributes = userGroup.getAttributes();
			Attribute attribute = attributes.get("cn");
			for (int i = 0; i < attribute.size(); i++) {
				addGroupsToSubject(attribute.get(i),sharedSate, options);
			}
		}
		populateUserPrincipals(ctx, sharedSate, options);
		// close the context
		ctx.close();
	}

	/**
	 * Loads the properties files having LDAP group to application roles map<br>
	 * and creates the UserPrincipal with defined roles for given LDAP group if
	 * no entry for LDAP group is found in the properties file then return
	 * UserPrincipal with ldapGroup.
	 * 
	 * <p> The mapping is defined as Spring bean in project specific application Context file
	 * 
	 * @param ldapGroup
	 *        LDAP group name the given subject is member of as a result of
	 *        the DirContext.search() methods
	 * @param sharedState Not currently used, but anticipated for single sign-on
	 * @param options
	 */
	protected void addGroupsToSubject(Object ldapGroup, Map<String, ?> sharedState, Map<String, ?> options) {

		// map roles to the group if any defined in grouptoRolesMaping
		// properties file for a particular application

		GroupToRoleManager manager = GroupToRoleManager.getInstance();
		String app = (String)options.get("APPLICATION");
		Properties groupsToRoleMapProperties = manager.get(app);
		
		// get roles if defined in properties file
		String ldapGroupName = (String) ldapGroup;
		String roles = groupsToRoleMapProperties.getProperty(ldapGroupName);
		UserGroupPrincipal userGroupPrincipal = null;

		if (logger.isDebugEnabled()) {
			logger.debug(getDirectoryProtocol()
					   + " Group "
					   + ldapGroupName
					   + " mapped to Role(s): "
					   + roles);
		}

		if (roles != null) {
			String patternStr = ";";
			String role[] = roles.split(patternStr);
			for (int i = 0; i < role.length; i++) {
				userGroupPrincipal = new UserGroupPrincipal(role[i]);

				// check if subject contains this group principal if not then add
				// it to the subject
				if (!(subject.getPrincipals().contains(userGroupPrincipal)))
					subject.getPrincipals().add(userGroupPrincipal);
			}
		} else if (roles == null) {
			userGroupPrincipal = new UserGroupPrincipal(ldapGroupName);

			// check if subject contains this group principal if not then add it
			// to the subject
			if (!(subject.getPrincipals().contains(userGroupPrincipal)))
				subject.getPrincipals().add(userGroupPrincipal);
		}

	}

	/**
	 * @return String A text label used to identify the protocol used to access
	 *         the directory service. This method is intended to be overridden
	 *         in any subclass, with an applicable protocol identifier.
	 *         <p>
	 *         The returned value is used for logging.
	 */
	protected String getDirectoryProtocol()
	{
		return "LDAP";
	}

	/**
	 * This method searches dirContext to find common name and the list of userid's
	 * defined for given users in LDAP and creates user principal with commonName as name with list of<p>
	 * user ids
	 * @param ctx
	 * @param sharedSate
	 * @param options
	 * @throws NamingException
	 */
	public void populateUserPrincipals(DirContext ctx, Map<String, ?> sharedSate, Map<String, ?> options) 
		throws NamingException 
	{
		if (logger.isDebugEnabled())
			logger.debug("BaseLdapLoginModule:  populateUserPrincipals()");

		// Specify the ids of the attributes to return
		// find uids and common name to create user principal
		String[] attrIDs = { "uid", "cn" };

		Attributes answer = ctx.getAttributes(securityPrincipal, attrIDs);

		// get commonName
		Attribute userCommonName = (Attribute) answer.get("cn");

		// we will always have one value in common name
		String commonName = (String) userCommonName.get(0);

		// get all the uid's for these user
		Attribute userIds = (Attribute) answer.get("uid");
		ArrayList<String> userIDList = new ArrayList<String>();

		if (userIds != null) {
			for (int i = 0; i < userIds.size(); i++) {
				//add user id to the list
				userIDList.add((String)userIds.get(i));
				if (logger.isDebugEnabled())
					logger.debug(">>>" + userIds.get(i));
			}
		}

		// create user principal with the common name and user id list
		UserPrincipal userPrincipal = new UserPrincipal(commonName, userIDList);
		if (!(subject.getPrincipals().contains(userPrincipal)))
			subject.getPrincipals().add(userPrincipal);
	}
}
