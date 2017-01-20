package com.csc.fsg.life.pw.util;

/*
 * $#(c) This software contains trade secrets and confidential information,
 * which are proprietary to Computer Sciences Corporation. The use,
 * reproduction, distribution, or disclosure of this software, in whole or in
 * part, without the express written permission of Computer Sciences Corporation
 * is prohibited. This software is also an unpublished work protected under the
 * copyright laws of the United States of America and other countries. If this
 * software becomes published, the following notice shall apply: Copyright (c)
 * 1994 - 2003 Computer Sciences Corporation, all rights reserved. This software
 * may be modified only in accordance with the terms of the applicable license
 * agreement. Any modifications may result in the voiding of applicable
 * warranties and support services.
 */

/* Modifications: T0106 */

public class SqlMonitor {
	protected static SqlMonitor inst = null;

	private long hits[] = null;

	private long totTime[] = null;

	private long minTime[] = null;

	private long maxTime[] = null;

	private long resetTime;

	private static final int MS_PER_SEC = 1000, MS_PER_MIN = MS_PER_SEC * 60,
	        MS_PER_HOUR = MS_PER_MIN * 60, MS_PER_DAY = MS_PER_HOUR * 24;

	/**
	 */
	protected SqlMonitor() {
	}

	/**
	 */
	public void reset() {
		synchronized (inst) {
			hits = new long[getSize()];
			totTime = new long[getSize()];
			minTime = new long[getSize()];
			maxTime = new long[getSize()];
			for (int i = 0; i < getSize(); i++) {
				hits[i] = 0;
				totTime[i] = 0;
				minTime[i] = 0;
				maxTime[i] = 0;
			}
		}

		resetTime = System.currentTimeMillis();
	}

	/**
	 * @return SqlMonitor
	 */
	public static synchronized SqlMonitor inst() {
		if (inst == null) {
			inst = new SqlMonitor();
			inst.reset();
		}
		return inst;
	}

	/**
	 * @return int
	 */
	public int getSize() {
		return Sql.SQL_MAX_ID + 1;
	}

	/**
	 * @param id
	 * @param time
	 */
	public void add(int id, long time) {
		synchronized (inst) {
			if (isValid(id)) {
				hits[id]++;
				totTime[id] += time;
				if (hits[id] == 1) {
					minTime[id] = time;
					maxTime[id] = time;
				} else {
					minTime[id] = Math.min(time, minTime[id]);
					maxTime[id] = Math.max(time, maxTime[id]);
				}
			}
		}
	}

	/**
	 * @param id
	 * @return long
	 */
	public long getHits(int id) {
		if (isValid(id))
			return hits[id];
		else
			return 0;
	}

	/**
	 * @param id
	 * @return long
	 */
	public long getTotTime(int id) {
		if (isValid(id))
			return totTime[id];
		else
			return 0;
	}

	/**
	 * @param id
	 * @return long
	 */
	public long getMinTime(int id) {
		if (isValid(id))
			return minTime[id];
		else
			return 0;
	}

	/**
	 * @param id
	 * @return long
	 */
	public long getMaxTime(int id) {
		if (isValid(id))
			return maxTime[id];
		else
			return 0;
	}

	/**
	 * @param id
	 * @return boolean
	 */
	private boolean isValid(int id) {
		if (id >= 0 && id < getSize())
			return true;
		else {
			System.out.println("invalid id: " + id);
			Thread.dumpStack();
			return false;
		}
	}

	/**
	 * @return String
	 */
	@Override
	public String toString() {
		StringBuffer sb = new StringBuffer(64);
		for (int i = 0; i < getSize(); i++) {
			if (getHits(i) <= 0)
				continue;
			sb.append("<p>");
			sb.append(i);
			sb.append(" ");
			sb.append(getHits(i));
			sb.append(" hits, ");
			sb.append(getTotTime(i));
			sb.append("ms\n");
		}

		return sb.toString();
	}

	/**
	 * @return String
	 */
	public String getTimeSinceReset() {
		long elapsed = System.currentTimeMillis() - resetTime;
		if (elapsed < MS_PER_MIN * 3)
			return elapsed / MS_PER_SEC + " seconds.";
		else if (elapsed < MS_PER_HOUR * 3)
			return elapsed / MS_PER_MIN + " minutes.";
		else if (elapsed < MS_PER_DAY * 3)
			return elapsed / MS_PER_HOUR + " hours.";
		else
			return elapsed / MS_PER_DAY + " days.";
	}

	/**
	 * @return String
	 */
	public String display() {
		StringBuffer sb = new StringBuffer(64);
		for (int i = 0; i < getSize(); i++) {
			if (getHits(i) <= 0)
				continue;
			sb.append("<tr><td align='right'>");
			sb.append(getLabel(i));
			sb.append("</td><td align='right'>");
			sb.append(getHits(i));
			sb.append("</td><td align='right'>");
			sb.append(getMinTime(i));
			sb.append("</td><td align='right'>");
			sb.append(getTotTime(i) / getHits(i)); // avg. time in msec
			sb.append("</td><td align='right'>");
			sb.append(getMaxTime(i));
			sb.append("</td><td align='right'>");
			sb.append(getTotTime(i) / 1000.0); // cum. time in sec
			sb.append("</td></tr>");
		}

		return sb.toString();
	}

	/**
	 * Override this to provide better labels
	 * 
	 * @param i
	 * @return
	 */
	protected String getLabel(int i) {
		return String.valueOf(i);
	}

	/**
	 * This method is used to facilitate unit testing of this class
	 */
//	public static void main(String[] s) {
//		inst().add(Sql.SQL_GET_OB8_LOB_CODES, 500);
//		inst().add(Sql.SQL_GET_OB8_LOB_CODES, 250);
//		inst().add(Sql.SQL_GET_OTHER, 1234);
//		inst().add(Sql.SQL_DEL_OTHER, 2000);
//
//		System.out.println(inst().toString());
//	}
}
