package com.csc.fsg.life.common.config;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import javax.inject.Inject;

import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;

/* Modifications: T0199 */

public class MyBatisApplicationEnvironmentBean
	extends ApplicationEnvironmentBean
{
	@Inject
	private MyBatisDynamicConfigurationBean myBatisDynamicConfiguration = null;

	private Map<ExecutorType, SqlSessionTemplate> sqlSessionMap = null;

	/**
	 * Get the SqlSessionTemplate object based on the type of executor passed as
	 * a parameter.
	 * 
	 * @param executorType
	 *        Type of Executor: Simple or Batch
	 * @return SqlSessionTemplate
	 */
	public SqlSessionTemplate getSqlSessionTemplate(ExecutorType executorType)
	{
		if (sqlSessionMap == null)
			buildSqlSessionMap();
	
		return sqlSessionMap.get(executorType);
	}

	/**
	 * Build the SqlSessionMap instance for the chosen environment.
	 */
	private void buildSqlSessionMap()
	{
		sqlSessionMap = new HashMap<ExecutorType, SqlSessionTemplate>();
		SqlSessionFactory sqlSessionFactory = createSqlSessionFactory();
	
		// Create simple(non-batch) SQL session object
		SqlSessionTemplate sqlSession = new SqlSessionTemplate(sqlSessionFactory);
		sqlSessionMap.put(ExecutorType.SIMPLE, sqlSession);
	
		// Create batch SQL session object
		SqlSessionTemplate batchSqlSession = new SqlSessionTemplate(sqlSessionFactory, ExecutorType.BATCH);
		sqlSessionMap.put(ExecutorType.BATCH, batchSqlSession);
	}

	/**
	 * Create the SqlSessionFactory for the chosen environment.
	 * 
	 * @return SqlSessionFactory
	 */
	private SqlSessionFactory createSqlSessionFactory()
	{
		EnvDbInfoBean dataInfo = getDefaultEnvDbInfo(EnvDbInfoBean.TYPE_DATA);
		EnvDbInfoBean ruleInfo = getDefaultEnvDbInfo(EnvDbInfoBean.TYPE_RULE);

		// Build SQL session factory for this environment
		try {
			SqlSessionFactoryBean factory = new SqlSessionFactoryBean();
			factory.setConfigLocation(myBatisDynamicConfiguration.getConfigLocation());
			factory.setDataSource(getJndiDataSource());

			// Register type aliases designated using the annotation @Alias.
			// These aliases may be used instead of, or in addition to aliases
			// defined in mapper configuration file.
			factory.setTypeAliases(myBatisDynamicConfiguration.getTypeAliases());

			// Register mapper locations. These locations may be used instead of,
			// or in addition to locations defined in mapper configuration file.
			factory.setMapperLocations(myBatisDynamicConfiguration.getMapperLocations());

			// Register type handlers packages. MyBatis will automatically search
			// all custom type handlers in the specified packages, using 
			// the annotations @MappedTypes and @MappedJdbcTypes.
			factory.setTypeHandlersPackage(myBatisDynamicConfiguration.getTypeHandlersPackage());

			Properties props = new Properties();
			props.setProperty("schema", dataInfo.getSchema());
			if (ruleInfo != null)
				props.setProperty("businessRulesSchema", ruleInfo.getSchema());

			factory.setConfigurationProperties(props);
			factory.afterPropertiesSet();
			return factory.getObject();
		}
		catch (Exception e) {
			log.error("Unable to create SqlSessionFactory instance for environment "
					 + getName()
					 + ".  Failed with error: "
					 + e.getMessage());
		}

		return null;
	}
}
