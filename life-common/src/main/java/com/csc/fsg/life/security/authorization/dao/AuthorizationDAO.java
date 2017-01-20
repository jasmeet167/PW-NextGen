package com.csc.fsg.life.security.authorization.dao;

import java.util.List;

import com.csc.fsg.life.security.authorization.bo.AuthorityCacheModel;
import com.csc.fsg.life.security.authorization.bo.EnvironmentCompanyModel;

/* Modifications: ENH1220 */

/**
 * Data access interface for retrieval of data used to load Authority cache
 */
public interface AuthorizationDAO
{
	public List<EnvironmentCompanyModel> selectSecureEnvironmentCompanyList(String applicationId);

	public List<String> selectActiveRoleIdsForApplication(String applicationId);

	public List<AuthorityCacheModel> selectAuthorityList(String applicationId);
}
