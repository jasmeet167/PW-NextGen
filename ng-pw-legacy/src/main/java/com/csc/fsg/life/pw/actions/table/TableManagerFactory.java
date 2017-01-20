/*
 * Created on Feb 24, 2005 To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package com.csc.fsg.life.pw.actions.table;

import com.csc.fsg.life.pw.util.Constants;

/**
 * @author smunigun
 */
public class TableManagerFactory {

	/**
	 * Returns corresponding TableManager based on filterType and viewType.
	 * 
	 * @param filterType
	 * @param viewType
	 * @return TableManager
	 * @throws Exception
	 */
	public static final ITableManager getTableManager(String filterType,
	        String viewType) throws Exception {

		if (filterType == null)
			throw new Exception("Filter Type Cannot be null");

		boolean isApplyRChangesOnly = filterType.equalsIgnoreCase("Promotion")
		        || filterType.equalsIgnoreCase("ApplyChanges");

		if (!isApplyRChangesOnly && viewType == null)
			throw new Exception("View Type Cannot be null");

		boolean isEntireTable = filterType
		        .equalsIgnoreCase(Constants.ENTIRE_TABLE);
		boolean isWithChanges = viewType.equalsIgnoreCase("with");
		boolean isWithoutChanges = !isWithChanges;

		if (isApplyRChangesOnly) {
//			return new ApplyRChangesOnly();
		} else if (isWithChanges) {
//			if (isEntireTable)
//				return new EntireTableData();
//			else
//				return new DataWithChanges();
		} else if (isWithoutChanges) {
			return new DataWithoutChanges();
		}

		return null;
	}
}
