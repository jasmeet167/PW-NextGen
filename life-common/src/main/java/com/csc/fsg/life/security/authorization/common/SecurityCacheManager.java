package com.csc.fsg.life.security.authorization.common;

import javax.inject.Inject;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.security.acls.model.AclCache;
import org.springframework.stereotype.Component;

/* Modifications: ENH1220 */

@Component("securityCacheManager")
public class SecurityCacheManager
{
	private static final Log log = LogFactory.getLog("com.csc.fsg");

	@Inject
	private AuthorityCache authorityCache = null;

	@Inject
	private AclCache aclCache = null;

	public void refreshCachedSecurityData()
	{
		log.info("Initiating refresh of cached security data...");
		authorityCache.refresh();
		aclCache.clearCache();
		log.info("Refresh of cached security data has been completed");
	}
}
