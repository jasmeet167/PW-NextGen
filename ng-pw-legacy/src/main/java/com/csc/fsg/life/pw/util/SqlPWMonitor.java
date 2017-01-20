package com.csc.fsg.life.pw.util;

/**
 * @author rscranto
 */
public class SqlPWMonitor extends SqlMonitor {

	protected SqlPWMonitor() {
	}

	/**
	 * @return SqlMonitor
	 */
	public static synchronized SqlMonitor inst() {
		if (inst == null || !(inst instanceof SqlPWMonitor)) {
			inst = new SqlPWMonitor();
			inst.reset();
		}
		return inst;
	}

	/**
	 * Overridden from SqlMonitor so we can hook in labels
	 */
	protected String getLabel(int i) {
		return SqlPW.getLabel(i);
	}

	/**
	 * Overridden to show all decimal seconds instead of ms.
	 */
	public String display() {
		StringBuffer sb = new StringBuffer(64);
		for (int i = 0; i < getSize(); i++) {
			if (getHits(i) <= 0)
				continue;
			sb.append("<tr><td align='left'>");
			sb.append(getLabel(i));
			sb.append("</td><td align='right'>");
			sb.append(getHits(i));
			sb.append("</td><td align='right'>");
			sb.append(getMinTime(i) / 1000.0);
			sb.append("</td><td align='right'>");
			sb.append((getTotTime(i) / 1000.0) / getHits(i)); // avg. time in
			// s
			sb.append("</td><td align='right'>");
			sb.append(getMaxTime(i) / 1000.0);
			sb.append("</td><td align='right'>");
			sb.append(getTotTime(i) / 1000.0); // cum. time in sec
			sb.append("</td></tr>");
		}

		return sb.toString();
	}

}
