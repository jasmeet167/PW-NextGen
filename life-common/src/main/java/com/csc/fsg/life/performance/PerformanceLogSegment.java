package com.csc.fsg.life.performance;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

import com.csc.fsg.life.common.util.StopWatch;

/* Modifications: T0166 */

/**
 * Each instance of this segment represents a basic step along with its
 * attributes, such as captured timing information, in a performance log
 * activity.
 */
public class PerformanceLogSegment
	implements Serializable
{
	private static final long serialVersionUID = 8740410328588810111L;

	private String segmentType = null;
	private int sequenceNo = -1;
	private PerformanceLogSegmentOrigin origin = null;
	private String sqlQuery = null;
	private Timestamp timestamp = null;
	private StopWatch timer = null;

	public enum SegmentType { 
		SERVICE_INTERCEPTOR { @Override public String toString() { return "SI"; }},
		SERVICE_JDBC { @Override public String toString() { return "SJ"; }},
		SERVICE_MANAGER { @Override public String toString() { return "SM"; }},
		SERVICE_EXTERNAL { @Override public String toString() { return "SE"; }}
	};

	protected PerformanceLogSegment(SegmentType type, int sequenceNo, PerformanceLogSegmentOrigin origin)
	{
		this(type.toString(), sequenceNo, origin);
	}

	protected PerformanceLogSegment(SegmentType type, int sequenceNo, PerformanceLogSegmentOrigin origin, String sqlQuery)
	{
		this(type.toString(), sequenceNo, origin);
		this.sqlQuery = sqlQuery;
	}

	protected PerformanceLogSegment(String segmentType, int sequenceNo, PerformanceLogSegmentOrigin origin)
	{
		this.segmentType = segmentType;
		this.sequenceNo = sequenceNo;
		this.origin = origin;
		this.sqlQuery = "";
		this.timestamp = new Timestamp(new Date().getTime());
		this.timer = new StopWatch();
	}

	public String getSegmentType()
	{
		return segmentType;
	}

	public int getSequenceNo()
	{
		return sequenceNo;
	}

	/**
	 * Default implementation of the method <code>getViewId()</code>, to be
	 * overridden in a web-application aware subclass.
	 */
	public String getViewId()
	{
		return "";
	}

	public PerformanceLogSegmentOrigin getOrigin()
	{
		return origin;
	}

	public String getSqlQuery()
	{
		return sqlQuery;
	}
	
	public Timestamp getTimestamp()
	{
		return timestamp;
	}

	public long getElapsedTime()
	{
		return timer.getElapsedTimeMillis();
	}

	public void activate()
	{
		timer.start();
	}

	public void passivate()
	{
		timer.stop();
	}

	public boolean isExternal()
	{
		return SegmentType.SERVICE_EXTERNAL.toString().equals(segmentType); 
	}
}
