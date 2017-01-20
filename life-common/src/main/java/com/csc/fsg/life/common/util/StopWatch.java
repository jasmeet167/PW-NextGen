package com.csc.fsg.life.common.util;

import java.io.Serializable;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/* Modifications: T0166 */

public class StopWatch
	implements Serializable
{
	private static final long serialVersionUID = 7386434311007926758L;
	private static final Log log = LogFactory.getLog("com.csc.fsg");

	private boolean isRunning;
	private long startTimeMillis;
	private long elapsedTimeMillis;

	public void start()
		throws IllegalStateException
	{
		if (isRunning) {
			log.warn("start() invoked on a StopWatch instance, which was already running");
		}
		else {
			startTimeMillis = System.currentTimeMillis();
			isRunning = true;
		}
	}

	public void stop()
	{
		if (isRunning) {
			elapsedTimeMillis = System.currentTimeMillis() - startTimeMillis;
			isRunning = false;
		}
		else {
			log.warn("stop() invoked on a StopWatch instance, which was not running");
		}
	}

	public long getElapsedTimeMillis()
	{
		return elapsedTimeMillis;
	}

	public double getElapsedTimeSeconds()
	{
		return elapsedTimeMillis / 1000.0;
	}
}
