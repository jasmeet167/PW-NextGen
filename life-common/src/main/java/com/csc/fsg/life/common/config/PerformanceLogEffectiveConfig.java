package com.csc.fsg.life.common.config;

import java.io.Serializable;

import javax.inject.Inject;

/* Modifications: T0166 */

public class PerformanceLogEffectiveConfig
	implements Serializable
{
	private static final long serialVersionUID = 3546702357167324162L;

	private boolean isLoggingEnabledAtWebLevel = false;
	private boolean isLoggingEnabledAtServiceLevel = false;
	private long minimumElapsedTime = 0L;

	@Inject
	protected void establishEffectiveConfig(PerformanceLogConfigBean configBean)
	{
		minimumElapsedTime = configBean.getMinimumElapsedTime();
	}

	public boolean isLoggingEnabled()
	{
		return isLoggingEnabledAtWebLevel() 
			|| isLoggingEnabledAtServiceLevel();
	}

	public void setLoggingEnabledAtWebLevel(boolean isLoggingEnabledAtWebLevel)
	{
		this.isLoggingEnabledAtWebLevel = isLoggingEnabledAtWebLevel;
	}

	public boolean isLoggingEnabledAtWebLevel()
	{
		return isLoggingEnabledAtWebLevel;
	}

	public void setLoggingEnabledAtServiceLevel(boolean isLoggingEnabledAtServiceLevel)
	{
		this.isLoggingEnabledAtServiceLevel = isLoggingEnabledAtServiceLevel;
	}

	public boolean isLoggingEnabledAtServiceLevel()
	{
		return isLoggingEnabledAtServiceLevel;
	}

	public void setMinimumElapsedTime(long minimumElapsedTime)
	{
		this.minimumElapsedTime = minimumElapsedTime;
	}

	public long getMinimumElapsedTime()
	{
		return minimumElapsedTime;
	}
}
