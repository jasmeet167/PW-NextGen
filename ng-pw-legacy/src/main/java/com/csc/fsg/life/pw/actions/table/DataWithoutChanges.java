package com.csc.fsg.life.pw.actions.table;

import java.util.HashMap;
import java.util.List;
import java.util.Vector;

import com.csc.fsg.life.pw.controller.BaseAction;
import com.csc.fsg.life.pw.environment.Environment;
import com.csc.fsg.life.pw.environment.EnvironmentManager;
import com.csc.fsg.life.pw.io.ColumnDescriptor;
import com.csc.fsg.life.pw.io.Row;
import com.csc.fsg.life.pw.io.RowManager;
import com.csc.fsg.life.pw.io.TableDescriptor;
import com.csc.fsg.life.pw.io.TableFilter;
import com.csc.fsg.life.pw.io.UtilMethods;
import com.csc.fsg.life.pw.io.WizUtils;
import com.csc.fsg.life.pw.transferobjects.PlanTO;
import com.csc.fsg.life.pw.util.Constants;

/* Modifications: T0103, T0091,HAVMENH, T0115, ENH1006 */

public class DataWithoutChanges implements ITableManager {

	public DataWithoutChanges() {
	}

	/**
	 * Builds the filter to get Source rows.
	 * 
	 * @param req
	 * @param envName
	 * @return TableFilter
	 * @throws Exception
	 */
	private static TableFilter getFilter(HashMap req, String envId)
	        throws Exception {

		String filter = BaseAction.getRequestParam(req, "filter");
		String company = BaseAction.getRequestParam(req, "company_code");
		String productPrefix = BaseAction.getRequestParam(req, "product_prefix");
		String subset = BaseAction.getRequestParam(req, "subset");
		String table = BaseAction.getRequestParam(req, "table");
		String pagingMode = BaseAction.getRequestParam(req, "PAGING_MODE");
		String pagingKey = BaseAction.getRequestParam(req, "PAGING_KEY");
		String locator = BaseAction.getRequestParam(req, "LOCATOR");
		String userCondition = BaseAction.getRequestParam(req, "userCondition");

		String tableId = table
		        .substring(table.length() - 4, table.length() - 1);
		
		TableFilter tableFilter = new TableFilter();
		tableFilter.setCompanyCode(company);
		tableFilter.setTableSubset(subset);
		tableFilter.setTableName(table);
		tableFilter.setView(filter);
		if ( userCondition != null && userCondition.trim().length() > 0 )
			tableFilter.setUserCondition(userCondition);

		if (productPrefix != null) {
			if (!(productPrefix.equals("N") || productPrefix.equals("C")))
				tableFilter.setProductPrefix(productPrefix);
		}


		String locatorColumns = null;

		if (locator != null && locator.equalsIgnoreCase("true")) {
			Row row = new RowManager(envId).getBlankRow(tableId);
			row.setColumns(pagingKey, "|");
			locatorColumns = pagingKey;
			pagingKey = row.getKeyFromRow(pagingKey, "|", "|");
		}
		
		tableFilter.setPagingMode(pagingMode!=null);
		tableFilter.setNextPage(pagingMode != null && pagingMode.equalsIgnoreCase("next"));
		tableFilter.setPagingModeStr(pagingMode);
		if (pagingKey != null)
			tableFilter.setPagingKey(pagingKey);
		else
			tableFilter.setFirstPage(true);

		tableFilter.setIsLocatorView(locatorColumns!=null);
		return tableFilter;
	}

	/**
	 * Returns the data stream for Business Rules view(With out changes).
	 * 
	 * @param req
	 * @param user
	 *            Object
	 * @return Data Stream to be returned to client
	 */
	public final List<Row> getData(HashMap req, int userAuthority)
	        throws Exception {

		StringBuffer stream = new StringBuffer();

		String envId = BaseAction.getRequestParam(req, "environment");
		Environment environment = EnvironmentManager.getInstance().getEnvironment(envId);
		
		TableFilter filter = getFilter(req, envId);

		TableDescriptor td = environment.getTableDescMgr().getTableDescriptor(filter.getTableId());
		if (td == null)
			throw new NoSuchTableException();

		Vector<Row> sourceRows = td.getTableRows(environment, filter);
		return sourceRows;
//
//		String pp = filter.getProductPrefix();
//		if (pp != null) {
//			if (pp.equals("N") || pp.equals("C"))
//				filter.setProductPrefix(null); // For Common tables the product
//			// prefix should go as ALL(null)
//		}
//
//		// WMA TT SPR# 489 - AVM Translation in "Rules" view
//		PlanTO plan = null;
//		String planKey = (String) req.get("plan_key");
//		if (planKey != null) {
//			plan = new PlanTO(planKey, "|");
//		}
//
//		String table = (String) req.get("table");
//		addPagingStream(stream, filter, td);
//		addDescStream(stream, td, filter, envId, plan, table);
//		addDataStream(stream, td, sourceRows, envId);
//
//		return stream;
	}

	/**
	 * Adds actual Business Rules data to client data stream.
	 * 
	 * @param stream
	 * @param tableDescriptor
	 * @param sourceRows
	 */
	private static void addDataStream(StringBuffer stream, TableDescriptor td,
	        Vector<Row> sourceRows, String schema) throws Exception {

		Vector<ColumnDescriptor> CDs = td.getColumnDescriptors();
		int cdSize = CDs.size();
		for (int r = 0; r < sourceRows.size(); r++) {
			Row row = sourceRows.get(r);

			for (int i = 0; i < cdSize; i++) {
				ColumnDescriptor cd = CDs.get(i);
				if (i == 0) {
					if ((row.get_auditUserId() == null)
					        || (row.get_auditDate() == null)
					        || (row.get_auditProject() == null)) {
						stream.append(0);
					} else {
						stream.append(Constants.TABLE_ATTR_HAS_AUDIT_INFO);
					}
				}

				stream.append(Constants.STAB);
				stream.append(WizUtils.pwTrim(cd.getValue(row).toString()));
			}

			stream.append(Constants.STAB);
			stream.append(UtilMethods.buildWithDelim(row, schema, true));

			stream.append(Constants.STAB);
			stream.append(UtilMethods.buildWithDelim(row, schema, false));

			if ((row.get_auditUserId() != null)
			        && (row.get_auditDate() != null)
			        && (row.get_auditProject() != null)) {
				stream.append(Constants.STAB);
				stream.append(row.get_auditUserId()).append(Constants.STAB);
				stream.append(row.get_auditDate()).append(Constants.STAB);
				stream.append(row.get_auditProject());
			}

			stream.append(Constants.EOL);
		}
	}

	/**
	 * Adds Table and Column descriptors information to client data stream
	 * 
	 * @param stream
	 * @param tableDescriptor
	 */
	// WMA TT SPR# 489 - AVM Translation in "Rules" view
	private static void addDescStream(StringBuffer stream, TableDescriptor td,
	        TableFilter filter, String envSchema, PlanTO plan, String table) throws Exception{

		stream.append(1).append(Constants.STAB);
		td.toStream(stream);
		stream.append(Constants.EOL);

		ColumnDescriptor descriptor = null;
		String productPrefix = "*";

		for (int i = 1; i <= td.getNumberOfColumns(); i++) {
			descriptor = td.getColumn(i);
			descriptor.toStream(stream, productPrefix, envSchema);
			stream.append(Constants.EOL);
		}
	}

	/**
	 * Adds paging related inforamtion to client data stream.
	 * 
	 * @param stream
	 * @param filter
	 * @param tableDescriptor
	 */
	private static void addPagingStream(StringBuffer stream,
	        TableFilter filter, TableDescriptor td) {

		boolean nextIndicator = td.getNextIndicator();
		boolean prevIndicator = td.getPrevIndicator();

		stream.append(filter.getPagingModeStr()).append(Constants.STAB);

		if (filter.isLocatorView() )
			stream.append(Constants.LOCATE).append(Constants.STAB);
		else
			stream.append(Constants.NO_LOCATE).append(Constants.STAB);

		stream.append("FIRST KEY").append(Constants.STAB);
		stream.append("LAST KEY").append(Constants.STAB);

		if (nextIndicator)
			stream.append(Constants.PAGING_NEXT).append(Constants.STAB);
		else
			stream.append(Constants.PAGING_NO_NEXT).append(Constants.STAB);

		if (prevIndicator)
			stream.append(Constants.PAGING_PREV);
		else
			stream.append(Constants.PAGING_NO_PREV);

		stream.append(Constants.EOL);
	}

}
