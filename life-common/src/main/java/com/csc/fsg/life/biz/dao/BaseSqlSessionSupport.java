package com.csc.fsg.life.biz.dao;

import org.apache.ibatis.session.ExecutorType;

import com.csc.fsg.life.common.config.MyBatisApplicationEnvironmentBean;
import com.csc.fsg.life.context.UserContext;

/* Modifications: T0199 */

/**
 * Extends the Spring class <code>SqlSessionDaoSupport</code> in order to
 * build it from the <code>UserContext</code>.
 */
abstract public class BaseSqlSessionSupport
	extends BaseSqlSessionDaoSupport
{
	/**
	 * Build this class from the <code>UserContext</code>.
	 * <p>
	 * This allows the DAO to dynamically connect to the appropriate data source
	 * based on the environment the user has chosen at runtime.
	 * 
	 * @param context
	 *        The user context.
	 */
	public BaseSqlSessionSupport(UserContext context)
	{
		MyBatisApplicationEnvironmentBean applicationEnvironment
			= (MyBatisApplicationEnvironmentBean) context.getApplicationEnvironment();

		setSqlSessionTemplate(applicationEnvironment.getSqlSessionTemplate(ExecutorType.SIMPLE));
	}

	/**
	 * Build this class from the <code>UserContext</code> and
	 * <code>ExecutorType</code>.
	 * 
	 * ExecutorType parameter is added to set either batch or simple SQL session
	 * object.
	 * 
	 * @param context
	 *        The user context.
	 * @param executorType
	 *        Type of Executor Simple or Batch
	 */
	public BaseSqlSessionSupport(UserContext context, ExecutorType executorType)
	{
		MyBatisApplicationEnvironmentBean applicationEnvironment
			= (MyBatisApplicationEnvironmentBean) context.getApplicationEnvironment();

		setSqlSessionTemplate(applicationEnvironment.getSqlSessionTemplate(executorType));
	}
}
