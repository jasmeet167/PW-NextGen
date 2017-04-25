package com.csc.fsg.life.pw.web.actions.table;

import java.util.*;

import com.csc.fsg.life.pw.common.util.Constants;
import com.csc.fsg.life.pw.web.controller.BaseAction;
import com.csc.fsg.life.pw.web.environment.EnvironmentManager;
import com.csc.fsg.life.pw.web.io.*;
import com.csc.fsg.life.pw.web.utils.HighValueHandler;

/* Modifications: T0091, ENH1006 */

public class EntireTableData implements ITableManager {

	public EntireTableData() {
	}

	/**
	 * Builds the filter to get Source and WIP rows.
	 * 
	 * @param req
	 * @return WIPTableFilter
	 * @throws Exception
	 */
	protected static WIPTableFilter getFilter(HashMap req) throws Exception {

		WIPTableFilter wipFilter = new WIPTableFilter();

		String filterType = BaseAction.getRequestParam(req, "filter");

		String environment = BaseAction.getRequestParam(req, "environment");
		String company_code = BaseAction.getRequestParam(req, "company_code");

		String productPrefix = BaseAction
		        .getRequestParam(req, "product_prefix");

		String subset = BaseAction.getRequestParam(req, "subset");

		String table = BaseAction.getRequestParam(req, "table");

		String tableId = null;
		if (table.length() > 3)
			tableId = table.substring(table.length() - 4, table.length() - 1);
		else
			tableId = table;

		Vector<String> tableIds = new Vector<String>();
		tableIds.addElement(tableId);

		String pagingMode = BaseAction.getRequestParam(req, "PAGING_MODE");
		String pagingKey = BaseAction.getRequestParam(req, "PAGING_KEY");
		String locator = BaseAction.getRequestParam(req, "LOCATOR");
		String ebcdicKey = BaseAction.getRequestParam(req, "EBCDIC_KEY");
		
		//V-E991
		if (ebcdicKey!=null && EnvironmentManager.getInstance().getEnvironment(environment).isNexEnv())
			ebcdicKey = getEbcdicKeyFromPagingKey(pagingKey, tableId, environment);

		String userCondition = BaseAction.getRequestParam(req, "userCondition");
		
		String locatorColumns = null;
		if (locator != null && locator.equalsIgnoreCase("true")) {
			Row row = new RowManager(environment).getBlankRow(tableId);
			locatorColumns = pagingKey;
			pagingKey = row.getKeyFromRow(pagingKey, "|", "|");
		}

		wipFilter.setEnvironment(environment);
		wipFilter.setCompanyCode(company_code);
		wipFilter.setTableIds(tableIds);
		wipFilter.setSubsetName(subset);
		wipFilter.setView(filterType);

		if (pagingMode != null)
			wipFilter.setPagingMode(pagingMode);

		if (pagingKey != null)
			wipFilter.setKeyForPaging(pagingKey);
		else
			wipFilter.setInitTableOpen(true);

		if (ebcdicKey != null)
			wipFilter.setEbcdicKey(ebcdicKey);
		
		if ( userCondition != null && userCondition.trim().length() > 0 )
			wipFilter.setUserCondition(userCondition);

		wipFilter.setLocator(locatorColumns);

		Vector projectsVector = DataUtils.getProjectsVector(req);

		if (projectsVector.size() > 0)
			wipFilter.setProjectNames(projectsVector);

		if (productPrefix != null)
			wipFilter.setProductPrefix(productPrefix.toUpperCase());

		return wipFilter;
	}

	/**
	 * Returns the data stream for Entire table view.
	 * 
	 * @param req
	 * @param user
	 *            Object
	 * @return Data Stream to be returned to client
	 */
	public final StringBuffer getData(HashMap req, int userAuthority)
	        throws Exception {

		StringBuffer stream = new StringBuffer();

		if (DataUtils.isTableZeroRXAReq(req))
			return new TableXNXAData().getData(req, userAuthority);

		WIPTableFilter filter = getFilter(req);

		MergedDataAssistent ma = new MergedDataAssistent();
		Iterator<Object> displayRows = ma.getMergedRowsWithPaging(filter);

		addPagingStream(stream, filter, ma);
		addDescStream(stream, filter, userAuthority);
		addDataStream(stream, filter, displayRows);

		return stream;
	}

	/**
	 * Adds actual Business Rules data to client data stream.
	 * 
	 * @param stream
	 * @param filter
	 * @param displayRows
	 */
	protected static void addDataStream(StringBuffer stream,
	        WIPTableFilter filter, Iterator<Object> displayRows) {
		/** Data */
		String tableId = filter.getTableIds().get(0);
	
		TableDescriptor td = EnvironmentManager.getInstance()
						.getEnvironment(filter.getEnvironment()).getTableDescMgr().getTableDescriptor(tableId);

		Object obj = null;
		ColumnDescriptor descriptor = null;
		while (displayRows.hasNext()) {
			obj = displayRows.next();

			if (obj instanceof Row) {
				stream.append("0").append(Constants.STAB);
				for (int i = 1; i <= td.getNumberOfColumns(); i++) {
					descriptor = td.getColumn(i);
					stream.append(
					        WizUtils.pwTrim(descriptor.getValue((Row) obj)
					                .toString())).append(Constants.STAB);
				}

				stream.append(
				        UtilMethods.buildWithDelim((Row) obj, filter
				                .getEnvironment(), true))
				        .append(Constants.STAB);
				stream.append(
				        UtilMethods.buildWithDelim((Row) obj, filter
				                .getEnvironment(), false))
				        .append(Constants.EOL);

			} else {
				stream.append(((CommonWIPRow) obj).toString()).append(
				        Constants.EOL);
			}
		}
	}

	/**
	 * Adds Table and Column descriptors information to client data stream.
	 * 
	 * @param stream
	 * @param filter
	 * @param userAuthority
	 */
	protected static void addDescStream(StringBuffer stream,
	        WIPTableFilter filter, int userAuthority) {

		String tableId = filter.getTableIds().get(0);
		
		TableDescriptor td = EnvironmentManager.getInstance().getEnvironment(filter.getEnvironment())
					.getTableDescMgr().getTableDescriptor(tableId);

		stream.append(userAuthority).append(Constants.STAB);
		td.toStream(stream);
		stream.append(Constants.EOL);

		ColumnDescriptor descriptor = null;
		String productPrefix = "*";

		for (int i = 1; i <= td.getNumberOfColumns(); i++) {
			descriptor = td.getColumn(i);
			descriptor.toStream(stream, productPrefix, filter.getEnvironment());
			stream.append(Constants.EOL);
		}

	}

	/**
	 * Adds paging related inforamtion to client data stream.
	 * 
	 * @param stream
	 * @param filter
	 * @param ma
	 */
	protected static void addPagingStream(StringBuffer stream,
	        WIPTableFilter filter, MergedDataAssistent ma) {

		boolean nextInd = ma.isNextIndicator();
		boolean prevInd = ma.isPrevIndicator();

		stream.append(filter.getPagingMode()).append(Constants.STAB);

		if (filter.getLocator() != null)
			stream.append(Constants.LOCATE);
		else
			stream.append(Constants.NO_LOCATE);

		stream.append(Constants.STAB);

		stream.append(ma.getFirstEbcidicKey()).append(Constants.STAB);

		stream.append(ma.getLastEbcidicKey()).append(Constants.STAB);

		if (nextInd)
			stream.append(Constants.PAGING_NEXT);
		else
			stream.append(Constants.PAGING_NO_NEXT);

		stream.append(Constants.STAB);

		if (prevInd)
			stream.append(Constants.PAGING_PREV);
		else
			stream.append(Constants.PAGING_NO_PREV);

		stream.append(Constants.EOL);
	}
	
	//temp fix for paging key for NEX- will be removed later-V-E991
	private static String getEbcdicKeyFromPagingKey(String pagingKey,String tid,String envId) throws Exception{
		if (pagingKey.indexOf("|")==-1)
			return HighValueHandler.convertAsteriskToHV(pagingKey, envId);
		else{
			TableDescriptor td = EnvironmentManager.getInstance().getEnvironment(envId).getTableDescMgr().getTableDescriptor(tid);
			StringBuilder sb = new StringBuilder();
			Vector<ColumnDescriptor> columndescriptors = td.getKeyColumnDescriptors();
			String[] keys = pagingKey.split("\\|");
			if (keys.length!=columndescriptors.size())
				throw new Exception("Key String length not the same as column descriptions size");
			for (int i=0;i<keys.length;i++){
				ColumnDescriptor cd = columndescriptors.get(i);
				String columnValue = keys[i];
				int originalColumnLength = cd.getColumnSize();
				int apendType = UtilMethods.getAppendType(cd.getDataType());

				StringBuffer returnValue = new StringBuffer();
				if (apendType == UtilMethods.APPEND_ZEROS)
					UtilMethods.appendLeadingZeros(returnValue, columnValue,originalColumnLength);
				else
					UtilMethods.appendTrailingSpaces(returnValue, columnValue, originalColumnLength);

				sb.append(returnValue.toString());
			}
			return HighValueHandler.convertAsteriskToHV(sb.toString(),envId);

		}
	}

}
