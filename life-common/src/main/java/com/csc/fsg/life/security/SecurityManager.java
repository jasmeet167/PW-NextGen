package com.csc.fsg.life.security;

import java.security.GeneralSecurityException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.inject.Inject;
import javax.security.auth.login.AppConfigurationEntry;
import javax.security.auth.login.Configuration;
import javax.security.auth.login.LoginContext;
import javax.security.auth.login.LoginException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.security.acls.domain.GrantedAuthoritySid;
import org.springframework.security.acls.domain.ObjectIdentityImpl;
import org.springframework.security.acls.model.Acl;
import org.springframework.security.acls.model.AclDataAccessException;
import org.springframework.security.acls.model.AclService;
import org.springframework.security.acls.model.Permission;
import org.springframework.security.acls.model.Sid;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.jaas.JaasAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.CredentialsContainer;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.util.StringUtils;

import com.csc.fsg.life.common.config.CommonApplicationConfigBean;
import com.csc.fsg.life.context.UserContext;
import com.csc.fsg.life.security.authorization.common.AuthorityCache;
import com.csc.fsg.life.security.authorization.common.AuthorityKey;
import com.csc.fsg.life.security.authorization.permissions.AccessPermission;
import com.csc.fsg.life.security.authorization.permissions.ImplicitPermissions;
import com.csc.fsg.life.security.authorization.permissions.PermissionsList;
import com.csc.fsg.life.security.authorization.resourceidentity.AclIdentity;
import com.csc.fsg.life.security.authorization.resourceidentity.MaskingAclIdentity;
import com.csc.fsg.life.security.authorization.resourceidentity.SpecialOpAclIdentity;

/* Modifications: ENHT0085,V-E323, T0120, WMA-1476, T0175, ENH1019, ENH1220 */

/**
 * Base Security manager for All Projects. Manages both authentication and
 * authorization requests.
 */
public class SecurityManager
{
	/** Standard Apache Commons Logger */
	protected static final Log log = LogFactory.getLog("com.csc.fsg");

	@Inject
	private ImplicitPermissions implicitPermissions = null;

	/** Has the bean been initialized yet */
	protected boolean isInitialized = false;

	/** Has LDAP been configured with a provider URL other than "off" */
	protected boolean isLdapConfigured = true;

	private String applicationId;
	protected AuthenticationProvider anonymousAuthenticationProvider;
	protected AuthenticationProvider secureAuthenticationProvider;
	protected AuthorityCache authorityCache;
	protected AclService aclService;

	public SecurityManager(String applicationId)
	{
		this.applicationId = applicationId;
	}

	public void setAnonymousAuthenticationProvider(AuthenticationProvider authenticationProvider)
	{
		this.anonymousAuthenticationProvider = authenticationProvider;
	}

	public void setSecureAuthenticationProvider(AuthenticationProvider authenticationProvider)
	{
		this.secureAuthenticationProvider = authenticationProvider;
	}

	public void setAuthorityCache(AuthorityCache authorityCache)
	{
		this.authorityCache = authorityCache;
	}

	public void setAclService(AclService aclService)
	{
		this.aclService = aclService;
	}

	/**
	 * Gets the application name from the JAAS configuration.
	 */
	public String getApplicationId()
	{
		return applicationId;
	}

	protected void init()
	{
		if (isInitialized)
			return;

		isInitialized = true;
		isLdapConfigured();
	}

	protected void isLdapConfigured()
	{
		Configuration jaasConfig = Configuration.getConfiguration();
		AppConfigurationEntry[] appConfigEntries = jaasConfig.getAppConfigurationEntry(applicationId);
		AppConfigurationEntry appConfigEntry = appConfigEntries[0];

		// check to see if LDAP is configured to be turned off
		String provider = (String) appConfigEntry.getOptions().get("PROVIDER_URL");
		isLdapConfigured = !"off".equalsIgnoreCase(provider);
	}

	/**
	 * Authenticates a user id/password against LDAP.
	 */
	public Authentication login(String userId, char[] userPwd, LoginListener loginListener)
		throws GeneralSecurityException
	{
		// Initialize if necessary.
		init();

		try {
			loginListener.onBeforeLogin();

			String uppercaseUserId = userId.toUpperCase();
			if (!isLdapConfigured) {
				Authentication authenticationToken = new AnonymousAuthenticationToken(applicationId, uppercaseUserId, AuthorityUtils.createAuthorityList("UNRESTRICTED_ACCESS"));
				return anonymousAuthenticationProvider.authenticate(authenticationToken);
			}
			else {
				Authentication authenticationToken = new UsernamePasswordAuthenticationToken(uppercaseUserId, userPwd);
				return secureAuthenticationProvider.authenticate(authenticationToken);
			}
		}
		catch (AuthenticationException e) {
			throw new GeneralSecurityException(e.getMessage(), e);
		}
	}

	public void logout(Authentication authenticationToken)
		throws LoginException
	{
		if (authenticationToken instanceof JaasAuthenticationToken) {
			JaasAuthenticationToken jaasAuthenticationToken = (JaasAuthenticationToken) authenticationToken;
			LoginContext loginContext = jaasAuthenticationToken.getLoginContext();
			if (loginContext != null)
				loginContext.logout();
		}

		if (authenticationToken instanceof CredentialsContainer) {
			CredentialsContainer container = (CredentialsContainer) authenticationToken;
			container.eraseCredentials();
		}

		authenticationToken.setAuthenticated(false);
	}

	public boolean hasAuthority(UserContext userContext, AclIdentity secureResource, List<Permission> permissions)
	{
		return hasAuthority(userContext, secureResource, permissions, false);
	}

	/**
	 * Checks the role in the user context to see if it has either inquiry or
	 * update authorization on the specified secure resource.
	 * 
	 * @param context
	 *        The user context.
	 * @param secureResource
	 *        Identification of the resource the user is about to access.
	 * @param permissions
	 *        The list of required permissions for access to the secure resource
	 * @isGrantedByDefault A flag indicating whether to grant authority if the
	 *                     ACL corresponding to {@code secureResource} is not
	 *                     found, or if it does not have an ACE matching the
	 *                     given {@code permissions}.
	 * @return true if the user has authorization, false if not.
	 */
	public boolean hasAuthority(UserContext userContext, AclIdentity secureResource, List<Permission> permissions, boolean isGrantedByDefault)
	{
		Collection<? extends GrantedAuthority> grantedAuthorities = userContext.getGrantedGroupAuthorities();
		return hasAuthority(grantedAuthorities, secureResource, permissions, isGrantedByDefault);
	}

	public boolean hasAuthority(Collection<? extends GrantedAuthority> grantedAuthorities, AclIdentity secureResource, List<Permission> permissions, boolean isGrantedByDefault)
	{
		init();

		// if we are in demo mode, do not check authorizations
		if (!isLdapConfigured)
			return true;

		boolean isAuthorityGranted;
		if (hasImplicitAuthority(grantedAuthorities, secureResource, permissions)) {
			isAuthorityGranted = true;
		}
		else if (authorityCache == null) {
			// This condition is true when the application is configured to use
			// only Implicit Security, with no Security database access
			isAuthorityGranted = false;
		}
		else {
			isAuthorityGranted = isGrantedByDefault;

			if (secureResource != null) {
				while (!authorityCache.isEnvCompanySecurityEstablished(secureResource)
					&& !secureResource.isFullyGeneric())
						secureResource = secureResource.getMoreGenericClone();
				if (authorityCache.isEnvCompanySecurityEstablished(secureResource))
					isAuthorityGranted = isGranted(grantedAuthorities, secureResource, permissions, isGrantedByDefault);
			}
		}

		if (isAuthorityGranted)
			logAuthorizationDecision("PERMIT ", isGrantedByDefault, secureResource, permissions);
		else
			logAuthorizationDecision("DENY   ", isGrantedByDefault, secureResource, permissions);

		return isAuthorityGranted;
	}

	private boolean isGranted(Collection<? extends GrantedAuthority> grantedAuthorities, AclIdentity secureResource, List<Permission> permissions, boolean isGrantedByDefault)
	{
		boolean isGranted = isGrantedByDefault;

		AuthorityKey key = secureResource.getAuthorityKey();
		AclIdentity secureResourceWithId = authorityCache.getAuthority(key);

		if (secureResourceWithId != null) {
			List<Sid> sids = new ArrayList<>();
			for (GrantedAuthority grantedAuthority : grantedAuthorities)
				sids.add(new GrantedAuthoritySid(grantedAuthority));

			try {
				Acl acl = aclService.readAclById(new ObjectIdentityImpl(secureResourceWithId), sids);
				isGranted = acl.isGranted(permissions, sids, false);
			}
			catch (AclDataAccessException e) {
				// If applicable ACL or ACE are not found, let the authorization retain the default value
			}
			catch (DataAccessException e) {
				isGranted = false;
				log.fatal("Unable to verify User's Authority due to an exception on retrieval of the ACL from database. "
						+ "Access to the requested resource will be denied. The original error message is: "
						+ e.getMessage(), e);
			}
		}

		return isGranted;
	}

	/**
	 * Special authorization check needed for search screen(s) to only show
	 * authorized companies. Default is to PERMIT all companies. We only check
	 * for explicit denials here.
	 */
	public boolean hasSearchAuthorityForCompany(UserContext userContext, String companyCode)
	{
		// If we are in demo mode, do not check authorizations
		if (!isLdapConfigured)
			return true;

		CommonApplicationConfigBean configBean = CommonApplicationConfigBean.getInstance();
		if (!configBean.isUseCompanyCodeAsAttrInSecurity())
			return true;

		init();
		String environment = userContext.getApplicationEnvironment().getName();
		SpecialOpAclIdentity aclIdentity = SpecialOpAclIdentity.getCompanySearchSpecialOpInstance(environment, companyCode);
		return hasAuthority(userContext, aclIdentity, PermissionsList.GENERAL, true);
	}

	public boolean isDataMaskingRequired(UserContext userContext, String maskClass)
	{
		if (StringUtils.hasText(maskClass)) {
			CommonApplicationConfigBean configBean = CommonApplicationConfigBean.getInstance();
			if (configBean.isMaskingEnabled()) {
				MaskingAclIdentity aclIdentity = MaskingAclIdentity.getInstance(userContext, maskClass);
				return !hasAuthority(userContext, aclIdentity, PermissionsList.GENERAL);
			}
		}

		return false;
	}

	/**
	 * Log the outcome of the process used to determine authorization to access
	 * functionality/resources identified by the provided
	 * {@code resourceIdentity} and {@code permissions}.
	 */
	protected void logAuthorizationDecision(String decision, boolean isGrantedByDefault, AclIdentity resourceIdentity, List<Permission> permissions)
	{
		if (log.isDebugEnabled()) {
			StringBuilder buf = new StringBuilder();
			boolean isFirst = true;
			for (Permission permission : permissions) {
				if (isFirst)
					isFirst = false;
				else
					buf.append('.');

				if (permission instanceof AccessPermission)
					buf.append(((AccessPermission) permission).getBusinessEventType());
				else
					buf.append(permission);
			}

			String defaultDecision = null;
			if (isGrantedByDefault)
				defaultDecision = "(default decision:PERMIT)";
			else
				defaultDecision = "(default decision:DENY)  ";

			log.debug(decision + " " 
					+ defaultDecision + " - "
					+ resourceIdentity.toString() + ", "
					+ buf.toString());
		}
	}

	protected boolean hasImplicitAuthority(Collection<? extends GrantedAuthority> authorities, AclIdentity secureResource, List<Permission> permissions)
	{
		return implicitPermissions.hasImplicitAuthority(authorities, permissions);
	}
}
