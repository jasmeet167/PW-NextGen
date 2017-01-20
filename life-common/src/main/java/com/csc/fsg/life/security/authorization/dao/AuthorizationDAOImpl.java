package com.csc.fsg.life.security.authorization.dao;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.ibatis.session.SqlSession;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.stereotype.Component;

import com.csc.fsg.life.security.authorization.bo.AuthorityCacheModel;
import com.csc.fsg.life.security.authorization.bo.EnvironmentCompanyModel;

/* Modifications: ENH1220, T0199 */

/**
 * Data access class for retrieval of data used to load Authority cache
 */
@Component
public class AuthorizationDAOImpl
	extends SqlSessionDaoSupport
	implements AuthorizationDAO
{
	@Resource
	public void initializeFramework(SqlSessionTemplate securitySqlSessionTemplate)
	{
		setSqlSessionTemplate(securitySqlSessionTemplate);
	}

	public List<EnvironmentCompanyModel> selectSecureEnvironmentCompanyList(String applicationId)
	{
		if (applicationId == null) {
			return new ArrayList<>();
		}
		else {
			SqlSession session = getSqlSession();
			List<EnvironmentCompanyModel> selectedRows = session.selectList("selectSecureEnvironmentCompanyListForApplication", applicationId);
			return selectedRows;
		}
	}

	public List<String> selectActiveRoleIdsForApplication(String applicationId)
	{
		if (applicationId == null) {
			return new ArrayList<>();
		}
		else {
			SqlSession session = getSqlSession();
			List<String> selectedRows = session.selectList("selectActiveRoleIdsForApplication", applicationId);
			return selectedRows;
		}
	}

	public List<AuthorityCacheModel> selectAuthorityList(String applicationId)
	{
		if (applicationId == null) {
			return new ArrayList<>();
		}
		else {
			SqlSession session = getSqlSession();
			List<AuthorityCacheModel> selectedRows = session.selectList("selectAuthorityListForApplication", applicationId);
			return selectedRows;
		}
	}
}
