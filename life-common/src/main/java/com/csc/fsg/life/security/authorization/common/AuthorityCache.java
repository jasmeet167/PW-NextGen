package com.csc.fsg.life.security.authorization.common;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Component;

import com.csc.fsg.life.security.authorization.bo.AuthorityCacheModel;
import com.csc.fsg.life.security.authorization.bo.EnvironmentCompanyModel;
import com.csc.fsg.life.security.authorization.dao.AuthorizationDAO;
import com.csc.fsg.life.security.authorization.resourceidentity.AclIdentity;
import com.csc.fsg.life.security.authorization.resourceidentity.AclIdentityFactory;

/* Modifications: ENH1220 */

@Component
public final class AuthorityCache
{
	private static final Log log = LogFactory.getLog("com.csc.fsg");

	@Value("${applicationId}")
	private String applicationId = null;
	@Inject
	private AuthorizationDAO dao = null;
	@Inject
	private AclIdentityFactory aclIdentityFactory = null;

	private Set<String> envCompanyCache = new HashSet<>();
	private Map<AuthorityKey, AclIdentity> authorityCache = new HashMap<>();

	@PostConstruct
	private void initializeCache()
	{
		synchronized (this) {
			log.info("Commencing load of Authority cache");
			try {
				List<EnvironmentCompanyModel> envCompanyModels = dao.selectSecureEnvironmentCompanyList(applicationId);
				for (EnvironmentCompanyModel model : envCompanyModels) {
					String envCompanyKey = buildEnvCompanyKey(model.getEnvironment(), model.getCompanyCode());
					envCompanyCache.add(envCompanyKey);
				}

				List<AuthorityCacheModel> authorityModels = dao.selectAuthorityList(applicationId);
				for (AuthorityCacheModel model : authorityModels) {
					AclIdentity authority = aclIdentityFactory.buildFromAuthorityCacheModel(model);
					authorityCache.put(authority.getAuthorityKey(), authority);
				}

				log.info("Successfully cached " + envCompanyCache.size() + " Environment Company definitions and "
				        + authorityCache.size() + " Authority elements");
			}
			catch (IllegalArgumentException | DataAccessException e) {
				log.error("Unable to load Authority cache", e);
				envCompanyCache.clear();
				authorityCache.clear();
			}
		}
	}

	private String buildEnvCompanyKey(String environment, String companyCode)
	{
		if (environment != null)
			environment = environment.trim();
		if (companyCode != null)
			companyCode = companyCode.trim();

		StringBuilder buf = new StringBuilder();
		buf.append(environment);
		buf.append('\t');
		buf.append(companyCode);
		return buf.toString();
	}

	public boolean isEnvCompanySecurityEstablished(AclIdentity secureResource)
	{
		synchronized (this) {
			String environment = secureResource.getEnvironment();
			String companyCode = secureResource.getCompanyCode();
			String envCompanyKey = buildEnvCompanyKey(environment, companyCode);
			return envCompanyCache.contains(envCompanyKey);
		}
	}

	public AclIdentity getAuthority(AuthorityKey key)
	{
		synchronized (this) {
			return authorityCache.get(key);
		}
	}

	public void refresh()
	{
		synchronized (this) {
			envCompanyCache.clear();
			authorityCache.clear();
			log.info("Authority cache has been cleared");

			initializeCache();
		}
	}
}
