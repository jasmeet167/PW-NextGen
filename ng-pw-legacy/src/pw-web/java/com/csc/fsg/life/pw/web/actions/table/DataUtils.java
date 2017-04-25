package com.csc.fsg.life.pw.web.actions.table;

import java.util.*;

import com.csc.fsg.life.pw.common.util.Constants;
import com.csc.fsg.life.pw.web.controller.BaseAction;
import com.csc.fsg.life.pw.common.util.IOTokenizer;

/* Modifications: T0103 */
// T0103 - IOTokenizer moved to common

public class DataUtils {

	/**
	 * Returns a vector of projects extracted from client request.
	 * 
	 * @param req
	 * @return A Vector of projects
	 */
	public static Vector getProjectsVector(HashMap req) {

		Vector projsVector = new Vector();

		String projects = BaseAction.getRequestParam(req, ("projects"));

		if (projects != null) {
			IOTokenizer iot = new IOTokenizer(projects, "\t");
			String proj = null;

			while (iot.hasMoreTokens()) {
				proj = iot.nextToken().trim();
				if (proj.length() > 0) {
					projsVector.add(proj);
				}
			}
			if (((String) projsVector.get(0)).equalsIgnoreCase("None")) {
				projsVector.removeAllElements();
				projsVector.trimToSize();
				return projsVector;
			}
		}

		String project = BaseAction.getRequestParam(req, ("project"));

		if (project != null) {
			if (!projsVector.contains(project.trim())) {
				projsVector.add(project.trim());
			}
		}
		projsVector.trimToSize();
		return projsVector;
	}

	/**
	 * Converts asterisks to Mainframe Highvalues.
	 * 
	 * @param value
	 * @return Returns Mainframe Highvalues
	 */
	// public static String toHighValues(String value) {
	//
	// int len = value.length();
	// StringBuffer sb = new StringBuffer(len);
	//
	// for (int i = 0; i < len; i++) {
	// sb.append(Constants.HIGHVALUES_1_BYTE);
	// }
	//
	// return sb.toString();
	// }
	/**
	 * Checks whether the request is for Table T000X or T000XA
	 * 
	 * @param req
	 * @return Returns true if table is T000X or T000XA else false
	 */
	public static boolean isTableZeroRXAReq(HashMap req) {
		String table = BaseAction.getRequestParam(req, "table");

		if (table == null)
			return false;

		String tableId = null;
		if (table.length() > 3)
			tableId = table.substring(table.length() - 4, table.length() - 1);
		else
			tableId = table;

		return tableId.equalsIgnoreCase(Constants.TABLE_ZERO_ID)
		        || tableId.equalsIgnoreCase(Constants.SUBSET_TABLE_ID);
	}
}
