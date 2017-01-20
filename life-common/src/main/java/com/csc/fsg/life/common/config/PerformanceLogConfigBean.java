package com.csc.fsg.life.common.config;

import javax.sql.DataSource;

/* Modifications: T0166 */

/**
 * This class encapsulates configuration information related to the performance
 * log facility.
 */
public class PerformanceLogConfigBean
{
	private int level = 0;
	private DataSource dataSource = null;
	private String performanceSchema = null;
	private long minimumElapsedTime = 0L;

	public boolean isLoggingEnabledAtWebLevel()
	{
		return (level & 1) > 0;
	}

	public boolean isLoggingEnabledAtServiceLevel()
	{
		return (level & 2) > 0;
	}

	public void setLevel(int level)
	{
		this.level = level;
	}

	public DataSource getDataSource()
	{
		return dataSource;
	}

	public void setDataSource(DataSource dataSource)
	{
		this.dataSource = dataSource;
	}

	public String getPerformanceSchema()
	{
		return performanceSchema;
	}

	public void setPerformanceSchema(String performanceSchema)
	{
		this.performanceSchema = performanceSchema;
	}

	public long getMinimumElapsedTime()
	{
		return minimumElapsedTime;
	}

	public void setMinimumElapsedTime(long minimumElapsedTime)
	{
		this.minimumElapsedTime = minimumElapsedTime;
	}
}
