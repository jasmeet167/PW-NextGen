package com.csc.fsg.life.biz.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.executor.BatchResult;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;

import com.csc.fsg.life.performance.PerformanceLogSegment;

/* Modifications: T0166, T0199 */

/**
 * This class provides delegate methods for access to the instance of
 * {@link org.apache.ibatis.session.SqlSession} wrapped by
 * {@link SqlSessionAbstractDelegate}, while injecting the logic implementing
 * performance logging of database access using JDBC.
 */
public class SqlSessionTemplateDelegate
	extends SqlSessionAbstractDelegate
{
	public SqlSessionTemplateDelegate(SqlSession sqlSession)
	{
		super(sqlSession);
	}

	public int insert(String statementName, Object parameterObject)
	{
		PerformanceLogSegment performanceSegment = createActivitySegment(statementName, parameterObject);

		try {
			return sqlSession.insert(statementName, parameterObject);
		}
		finally {
			if (performanceSegment != null)
				performanceSegment.passivate();
		}
	}

	public int insert(String statementName)
	{
		PerformanceLogSegment performanceSegment = createActivitySegment(statementName, null);

		try {
			return sqlSession.insert(statementName);
		}
		finally {
			if (performanceSegment != null)
				performanceSegment.passivate();
		}
	}

	public int update(String statementName, Object parameterObject)
	{
		PerformanceLogSegment performanceSegment = createActivitySegment(statementName, parameterObject);

		try {
			return sqlSession.update(statementName, parameterObject);
		}
		finally {
			if (performanceSegment != null)
				performanceSegment.passivate();
		}
	}

	public int update(String statementName)
	{
		PerformanceLogSegment performanceSegment = createActivitySegment(statementName, null);

		try {
			return sqlSession.update(statementName);
		}
		finally {
			if (performanceSegment != null)
				performanceSegment.passivate();
		}
	}

	public int delete(String statementName, Object parameterObject)
	{
		PerformanceLogSegment performanceSegment = createActivitySegment(statementName, parameterObject);

		try {
			return sqlSession.delete(statementName, parameterObject);
		}
		finally {
			if (performanceSegment != null)
				performanceSegment.passivate();
		}
	}

	public int delete(String statementName)
	{
		PerformanceLogSegment performanceSegment = createActivitySegment(statementName, null);

		try {
			return sqlSession.delete(statementName);
		}
		finally {
			if (performanceSegment != null)
				performanceSegment.passivate();
		}
	}

	public <T> T selectOne(String statementName, Object parameterObject)
	{
		PerformanceLogSegment performanceSegment = createActivitySegment(statementName, parameterObject);

		try {
			return sqlSession.selectOne(statementName, parameterObject);
		}
		finally {
			if (performanceSegment != null)
				performanceSegment.passivate();
		}
	}

	public <T> T selectOne(String statementName)
	{
		PerformanceLogSegment performanceSegment = createActivitySegment(statementName, null);

		try {
			return sqlSession.selectOne(statementName);
		}
		finally {
			if (performanceSegment != null)
				performanceSegment.passivate();
		}
	}

	public <E> List<E> selectList(String statementName, Object parameterObject)
	{
		PerformanceLogSegment performanceSegment = createActivitySegment(statementName, parameterObject);

		try {
			return sqlSession.selectList(statementName, parameterObject);
		}
		finally {
			if (performanceSegment != null)
				performanceSegment.passivate();
		}
	}

	public <E> List<E> selectList(String statementName)
	{
		PerformanceLogSegment performanceSegment = createActivitySegment(statementName, null);

		try {
			return sqlSession.selectList(statementName);
		}
		finally {
			if (performanceSegment != null)
				performanceSegment.passivate();
		}
	}

	public <E> List<E> selectList(String statementName, Object parameterObject, RowBounds rowBounds)
	{
		PerformanceLogSegment performanceSegment = createActivitySegment(statementName, parameterObject);

		try {
			return sqlSession.selectList(statementName, parameterObject, rowBounds);
		}
		finally {
			if (performanceSegment != null)
				performanceSegment.passivate();
		}
	}

	public void select(String statementName, ResultHandler<?> resultHandler)
	{
		PerformanceLogSegment performanceSegment = createActivitySegment(statementName, null);

		try {
			sqlSession.select(statementName, resultHandler);
		}
		finally {
			if (performanceSegment != null)
				performanceSegment.passivate();
		}
	}

	public void select(String statementName, Object parameterObject, ResultHandler<?> resultHandler)
	{
		PerformanceLogSegment performanceSegment = createActivitySegment(statementName, parameterObject);

		try {
			sqlSession.select(statementName, parameterObject, resultHandler);
		}
		finally {
			if (performanceSegment != null)
				performanceSegment.passivate();
		}
	}

	public void select(String statementName, Object parameterObject, RowBounds rowBounds, ResultHandler<?> resultHandler)
	{
		PerformanceLogSegment performanceSegment = createActivitySegment(statementName, parameterObject);

		try {
			sqlSession.select(statementName, parameterObject, rowBounds, resultHandler);
		}
		finally {
			if (performanceSegment != null)
				performanceSegment.passivate();
		}
	}

	public <K, V> Map<K, V> selectMap(String statementName, String mapKey)
	{
		PerformanceLogSegment performanceSegment = createActivitySegment(statementName, null);

		try {
			return sqlSession.selectMap(statementName, mapKey);
		}
		finally {
			if (performanceSegment != null)
				performanceSegment.passivate();
		}
	}

	public <K, V> Map<K, V> selectMap(String statementName, Object parameterObject, String mapKey)
	{
		PerformanceLogSegment performanceSegment = createActivitySegment(statementName, parameterObject);

		try {
			return sqlSession.selectMap(statementName, parameterObject, mapKey);
		}
		finally {
			if (performanceSegment != null)
				performanceSegment.passivate();
		}
	}

	public <K, V> Map<K, V> selectMap(String statementName, Object parameterObject, String mapKey, RowBounds rowBounds)
	{
		PerformanceLogSegment performanceSegment = createActivitySegment(statementName, parameterObject);

		try {
			return sqlSession.selectMap(statementName, parameterObject, mapKey, rowBounds);
		}
		finally {
			if (performanceSegment != null)
				performanceSegment.passivate();
		}
	}

	public void beginBatch()
	{
		if (!isBatchExecutor())
			log.warn("A batch operation is beginning, but the executor type is not ExecutorType.BATCH; the operation will not be executed in Batch JDBC mode");

		PerformanceLogSegment performanceSegment = createActivitySegment("Begin Batch", null, true);
		if (performanceSegment != null)
			performanceSegment.passivate();
	}

	public List<BatchResult> endBatch()
	{
		PerformanceLogSegment performanceSegment = createActivitySegment("End Batch", null, true);

		try {
			return sqlSession.flushStatements();
		}
		finally {
			if (performanceSegment != null)
				performanceSegment.passivate();

			if (!isBatchExecutor())
				log.warn("A batch operation has been completed, but the executor type was not ExecutorType.BATCH; the operation has not been executed in Batch JDBC mode");
		}
	}
}
