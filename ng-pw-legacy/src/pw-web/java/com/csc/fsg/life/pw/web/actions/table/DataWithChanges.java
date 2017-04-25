package com.csc.fsg.life.pw.web.actions.table;

import java.sql.Connection;
import java.util.*;

import com.csc.fsg.life.pw.common.transferobjects.PlanTO;
import com.csc.fsg.life.pw.common.util.Constants;
import com.csc.fsg.life.pw.web.avm.AVKey;
import com.csc.fsg.life.pw.web.controller.BaseAction;
import com.csc.fsg.life.pw.web.environment.EnvironmentManager;
import com.csc.fsg.life.pw.web.io.*;
import com.csc.fsg.life.pw.web.utils.DBConnMgr;

/* Modifications: T0103, T0091 ,HAVMENH ,T0115, T0129 */

public class DataWithChanges implements ITableManager {

	public DataWithChanges() {
	}

	/**
	 * Returns the data stream for Merged view (Data With changes).
	 * 
	 * @param req
	 * @param user
	 *            Object
	 * @return Data Stream to be returned to client
	 */
	public final StringBuffer getData(HashMap req, int userAuthority)
	        throws Exception {

		StringBuffer stream = new StringBuffer();

		WIPTableFilter filter = EntireTableData.getFilter(req);
		String pp = filter.getProductPrefix();

		PlanTO plan = null;
		String planKey = (String) req.get("plan_key");
		if (planKey != null) {
			plan = new PlanTO(planKey, "|");
			plan.setEnvironment(filter.getEnvironment());
		}

		MergedDataAssistent ma = new MergedDataAssistent();
		Iterator<Object> displayRows = ma.getMergedRowsWithPaging(filter);

		if (pp != null) {
			filter.setProductPrefix(pp); // re assigning the saved pp.. pp is
			// made null in setFilterValues
			// method of Merged Data Assistent
			// (?)

			if (pp.equals("N") || pp.equals("C"))
				filter.setProductPrefix(null); // For Common tables the product
			// prefix should go as ALL(null)
		}

		EntireTableData.addPagingStream(stream, filter, ma);
		addDescStream(stream, filter, BaseAction.getRequestParam(req, "table"),
		        userAuthority, plan);
		EntireTableData.addDataStream(stream, filter, displayRows);

		return stream;
	}

	/**
	 * Adds Table and Column descriptors information to client data stream
	 * 
	 * @param stream
	 * @param filter
	 * @param table
	 * @param userAuthority
	 * @return
	 */
	private static void addDescStream(StringBuffer stream,
			WIPTableFilter filter, String table,
			int userAuthority, PlanTO plan) throws Exception{

		String tableId = filter.getTableIds().get(0);
		
		TableDescriptor td = EnvironmentManager.getInstance().
						getEnvironment(filter.getEnvironment()).getTableDescMgr().getTableDescriptor(tableId);

		stream.append(userAuthority).append(Constants.STAB);
		td.toStream(stream);
		stream.append(Constants.EOL);

		ColumnDescriptor descriptor = null;

		AVKey avKey = new AVKey(filter.getEnvironment(), filter
		        .getCompanyCode(), filter.getProductPrefix(), table, filter
		        .getSubsetName(), null, null);
		
		Connection conn = null;
		try{
			conn = DBConnMgr.getInstance().getConnection(filter.getEnvironment(),DBConnMgr.BUSINESS_RULES);

		for (int i = 1; i <= td.getNumberOfColumns(); i++) {
			descriptor = td.getColumn(i);
			avKey.setFieldName(descriptor.getDbColumnName());
			avKey.setKey(descriptor.isKey());
			descriptor.toStream(stream, avKey, plan,conn);
			stream.append(Constants.EOL);
		}

		}finally{
			DBConnMgr.getInstance().releaseConnection(conn);
		}

	}
}
