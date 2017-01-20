package com.csc.fsg.life.biz.dao;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.SqlSession;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.util.StringUtils;

import com.csc.fsg.life.common.config.PerformanceLogEffectiveConfig;
import com.csc.fsg.life.common.util.ApplicationContextUtils;
import com.csc.fsg.life.performance.PerformanceLogActivity;
import com.csc.fsg.life.performance.PerformanceLogActivityStore;
import com.csc.fsg.life.performance.PerformanceLogSegment;

/* Modifications: T0166, T0199 */

/**
 * Base class extended by components used to wrap MyBatis components, and inject
 * logic implementing performance logging of database access using JDBC.
 */
abstract public class SqlSessionAbstractDelegate
{
	protected static final Log log = LogFactory.getLog("com.csc.fsg");

	protected SqlSession sqlSession = null;

	public SqlSessionAbstractDelegate(SqlSession sqlSession)
	{
		this.sqlSession = sqlSession;
	}

	/**
	 * A helper method to create and return an instance of performance log
	 * activity segment; it works exactly the same as
	 * {@link #createActivitySegment(String, Object, boolean)}, with value of
	 * the boolean parameter defaulted to <code>false</code>.
	 * 
	 * @param statementName
	 *        Name of the MyBatis Mapper, which is being executed
	 * @param parameterObject
	 *        Container with parameter values to be used for JDBC query
	 * @return A new instance of <code>PerformanceLogSegment</code> initialized
	 *         to reflect the provided input.
	 */
	protected PerformanceLogSegment createActivitySegment(String statementName, Object parameterObject)
	{
		return createActivitySegment(statementName, parameterObject, false);
	}

	/**
	 * Create and return an instance of performance log activity segment, to be
	 * used for logging of database access using JDBC.
	 * 
	 * @param statementName
	 *        Name of the MyBatis mapper, which is being executed
	 * @param parameterObject
	 *        Container with parameter values to be used for JDBC query
	 * @param isBatchExec
	 *        A flag indicating whether the executed statment is part of a batch
	 *        operation.
	 * @return A new instance of <code>PerformanceLogSegment</code> initialized
	 *         to reflect the provided input.
	 */
	protected PerformanceLogSegment createActivitySegment(String statementName, Object parameterObject, boolean isBatchExec)
	{
		PerformanceLogSegment segment = null;

		try {
			PerformanceLogActivity activity = getPerformanceLogActivity();
			if (activity != null) {
				String sqlQuery = "";
				if (!isBatchExec)
					sqlQuery = getSqlQuery(statementName, parameterObject);

				segment = activity.createJdbcSegment(statementName, sqlQuery, isBatch());
				segment.activate();
			}
		}
		catch (Exception e) {
			log.error("Unable to create Performance Log Activity Segment; " + e.getMessage());
		}

		return segment;
	}

	/**
	 * Create and return an instance of performance log activity, as needed and
	 * as applicable. The determination of whether or not to create a new
	 * instance is made as follows:
	 * <ul>
	 * <li>If the system is not configured for performance logging, or for
	 * performance logging at service level, or performance logging has not been
	 * enabled in the current session, <code>null</code> value is returned</li>
	 * <li>Otherwise, if an existing activity has been detected, for example one
	 * created at web application level, that activity will be returned</li>
	 * <li>Otherwise <code>null</code> value will be returned</li>
	 * </ul>
	 * 
	 * @return An applicable instance of performance log activity.
	 */
	protected PerformanceLogActivity getPerformanceLogActivity()
	{
		if (!ApplicationContextUtils.containsBean("performanceLogActivityStore"))
			return null;

		PerformanceLogActivityStore activityStore 
			= (PerformanceLogActivityStore) ApplicationContextUtils.getBean("performanceLogActivityStore");
		if (activityStore.isEmpty())
			return null;

		PerformanceLogEffectiveConfig logConfigBean 
			= (PerformanceLogEffectiveConfig) ApplicationContextUtils.getBean("performanceLogEffectiveConfig");

		if (logConfigBean == null || !logConfigBean.isLoggingEnabledAtServiceLevel())
			return null;
		else
			return activityStore.getActivity();
	}

	/**
	 * Constructs and returns and unformatted string representing SQL query
	 * obtained by applying properties of the provided
	 * <code>parameterObject</code> to the Mapper identified by
	 * <code>statementName</code>.
	 * 
	 * @param statementName
	 *        Name of a statement encoded in a Mapper
	 * @param parameterObject
	 *        The object used as a source of parameterized variables in Mapper
	 * @return SQL query reflecting the provided input
	 */
	protected String getSqlQuery(String statementName, Object parameterObject)
	{
		if (!StringUtils.hasText(statementName))
			return "";

		MappedStatement statement = sqlSession.getConfiguration().getMappedStatement(statementName);
		String sqlQuery = statement.getSqlSource().getBoundSql(parameterObject).getSql();
		sqlQuery = sqlQuery.replaceAll("\\s+", " ").trim();
		return sqlQuery;
	}

	public boolean isBatchExecutor()
	{
		if (sqlSession instanceof SqlSessionTemplate) {
			SqlSessionTemplate sessionTemplate = (SqlSessionTemplate) sqlSession;
			return sessionTemplate.getExecutorType() == ExecutorType.BATCH;
		}
		else {
			throw new UnsupportedOperationException("Operation not supported for this type of SqlSession");
		}
	}

	public boolean isSimpleExecutor()
	{
		if (sqlSession instanceof SqlSessionTemplate) {
			SqlSessionTemplate sessionTemplate = (SqlSessionTemplate) sqlSession;
			return sessionTemplate.getExecutorType() == ExecutorType.SIMPLE;
		}
		else {
			throw new UnsupportedOperationException("Operation not supported for this type of SqlSession");
		}
	}

	/**
	 * @return A flag indicating whether a the current JDBC access is occurring
	 *         using batch executor.
	 */
	protected boolean isBatch()
	{
		return isBatchExecutor();
	}
}
