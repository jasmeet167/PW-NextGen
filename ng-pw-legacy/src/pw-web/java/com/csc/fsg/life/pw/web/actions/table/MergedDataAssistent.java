/*
 * THIS PROGRAM IS THE PROPERTY OF CSC FINANCIAL SERVICES GROUP. IT MAY NOT BE
 * COPIED IN WHOLE OR IN PART WITHOUT THE EXPRESS WRITTEN CONSENT OF CSC
 * FINANCIAL SERVICES GROUP.
 */

package com.csc.fsg.life.pw.web.actions.table;

import java.sql.Connection;
import java.util.*;

import org.apache.commons.logging.Log;

import com.csc.fsg.life.pw.common.transferobjects.PlanCriteriaTO;
import com.csc.fsg.life.pw.common.util.Constants;
import com.csc.fsg.life.pw.web.environment.*;
import com.csc.fsg.life.pw.web.io.*;
import com.csc.fsg.life.pw.web.log.PWServerLogManager;

/* Modifications: T0103, T0091, ENH961, ENH1006, T0129, WMA-97 */
// ENH961 - improve exception handling

/**
 * This class merges source rows with corresponding rows from the WIP for common
 * tables and table subsets. It does not support the Plan Table (000).
 */

public class MergedDataAssistent {
	private static Log _log = PWServerLogManager.getLog(MergedDataAssistent.class
	        .getPackage().getName());

	private boolean nextIndicator = false;

	private boolean prevIndicator = false;

	private String firstEbcidicKey = null;

	private String lastEbcidicKey = null;

	public MergedDataAssistent() {
	}

	public ArrayList<WIPRow> getWIPRows(WIPTableFilter wipFilter, String orderBy)
	        throws Exception {
		return getWIPRows(null, wipFilter, orderBy);
	}

	public ArrayList<WIPRow> getWIPRows(Connection conn, WIPTableFilter wipFilter,
	        String orderBy) throws Exception {

		setProductPrefix(wipFilter);
		WIPRows wipRows = null;

		if (conn != null)
			wipRows = new WIPRows(conn, wipFilter, orderBy, false);
		else
			wipRows = new WIPRows(wipFilter, orderBy, false);

		if (wipRows.isNextInd())
			nextIndicator = wipRows.isNextInd();
		if (wipRows.isPrevInd())
			prevIndicator = wipRows.isPrevInd();
		
		// TT wmA SPR 4329 - if locator, check for previous rows
		if ( wipFilter.getLocator() != null ) {
			String pagingModeSave = wipFilter.getPagingMode();
			wipFilter.setPagingMode("previous");
			WIPRows previousRows = null;
			if (conn != null)
				previousRows = new WIPRows(conn, wipFilter, orderBy, false);
			else
				previousRows = new WIPRows(wipFilter, orderBy, false);
			if ( !previousRows.isEmpty() )
				prevIndicator = true;
			wipFilter.setPagingMode(pagingModeSave);
		}
		
		return wipRows;
	}

	public Vector<Row> getSourceRows(WIPTableFilter wipFilter) throws Exception {

		PlanCriteriaTO planCriteria = wipFilter.getData();
		String tableId = planCriteria.getTablePtrId();
		/*
		Vector tableIds = wipFilter.getTableIds();
		String tableId = (String) tableIds.elementAt(0);
		*/
		Environment environment = EnvironmentManager.getInstance().getEnvironment(wipFilter.getEnvironment());
		
		TableDescriptor td = environment.getTableDescMgr().getTableDescriptor(tableId);

		if (td == null)
			throw new Exception("Table Descriptor not found/loaded for table "
			        + tableId);

		String productPrefix = planCriteria.getProductPrefix();
		// Ignore Product Prefix of "C" (Common table)
		if ( (productPrefix != null) && (productPrefix.equals("N") || productPrefix.equals("C")) ) {
			planCriteria.setProductPrefix(null);
		}
		TableFilter sourceFilter = new TableFilter();
		sourceFilter.setCompanyCode(planCriteria.getCompanyCode());
		sourceFilter.setProductPrefix(planCriteria.getProductPrefix());
		/*
		sourceFilter.setCompanyCode(wipFilter.getCompanyCode());

		String productPrefix = wipFilter.getProductPrefix();
		if (productPrefix != null) {
			if (!(productPrefix.equals("N") || productPrefix.equals("C")))
				sourceFilter.setProductPrefix(productPrefix);
		}
		
		*/
		sourceFilter.setTableSubset(wipFilter.getSubsetName());

		sourceFilter.setView(wipFilter.getView());
		sourceFilter.setPagingMode(wipFilter.getPagingMode()!=null);
		sourceFilter.setPagingKey(wipFilter.getKeyForPaging());
		sourceFilter.setIsLocatorView(wipFilter.getLocator()!=null);
		sourceFilter.setFirstPage(wipFilter.isInitTableOpen());
		sourceFilter.setNextPage(wipFilter.getPagingMode() != null && wipFilter.getPagingMode().equalsIgnoreCase("next"));
		sourceFilter.setPreviousPage(wipFilter.getPagingMode() != null && !wipFilter.getPagingMode().equalsIgnoreCase("next"));
		
		sourceFilter.setUserCondition(wipFilter.getUserCondition());

		Vector<Row> sourceRows = environment.getTableDescMgr().getTableDescriptor(tableId).getTableRows(environment,
		        sourceFilter);
		nextIndicator = td.getNextIndicator();
		// SPR 6567 - preserve a true previous indicator
		if ( !prevIndicator )
			prevIndicator = td.getPrevIndicator();

		return sourceRows;

	}

	// ENH961 add versions of getSourceSQL and getWipSQL that return count SQL
	
	// SPR 6442
	public static String getSourceSQL(PlanCriteriaTO planCriteria) {
		return getSourceSQL(planCriteria, false);
	}
	
	public static String getSourceSQL(PlanCriteriaTO planCriteria, boolean getCount) {
		Environment environment = EnvironmentManager.getInstance().getEnvironment(planCriteria.getEnvironment());
		TableDescriptor tdesc = environment.getTableDescMgr().getTableDescriptor(planCriteria.getTablePtrId());
		
		String select = (getCount) ? "SELECT COUNT(*) FROM " : "SELECT * FROM ";
		StringBuffer sql = new StringBuffer(select).append(environment.getSchema()).append(".").append(tdesc.getTableName());
		sql.append(" WHERE COMPANY_CODE = '").append(planCriteria.getCompanyCode()).append("'");
		if (tdesc.hasProductPrefix() && planCriteria.getProductPrefix().trim().length()>0 && !planCriteria.getProductPrefix().equals("N"))
			sql.append(" AND PRODUCT_PREFIX = '").append(planCriteria.getProductPrefix()).append("'");
		if (tdesc.hasTableSubset() && planCriteria.getTablePtrSubset().trim().length()>0 )
			sql.append(" AND TABLE_SUBSET = '").append(planCriteria.getTablePtrSubset()).append("'");
		
		if ( !getCount ) {
			sql.append(" ORDER BY ");
			
			boolean isFirstOne = true;
			Vector<ColumnDescriptor> columnDescriptors = tdesc.getColumnDescriptors();
			
			for (int i=0;i<columnDescriptors.size();i++){
				ColumnDescriptor cd = columnDescriptors.get(i);
				if (cd.isKey()){
					if (!isFirstOne) 
						sql.append(", ");
					sql.append(cd.getDbColumnName());
					isFirstOne = false;
				}
			}
		}
		_log.debug("Sql to get Source rows: "+sql.toString());
		return sql.toString();
	}

	// SPR 6442
	public static String getWipSQL(PlanCriteriaTO planCriteria) {
		return getWipSQL(planCriteria, false);
	}
	
	public static String getWipSQL(PlanCriteriaTO planCriteria, boolean getCount) {
		Environment environment = EnvironmentManager.getInstance().getEnvironment(planCriteria.getEnvironment());
		TableDescriptor tdesc = environment.getTableDescMgr().getTableDescriptor(planCriteria.getTablePtrId());
		
		String select = (getCount) ? "SELECT COUNT(*) FROM " : "SELECT * FROM ";
		StringBuffer sql = new StringBuffer(select);
		sql.append(environment.getApplSchema()).append(".COMMONWIP ");
		sql.append(" WHERE ENVIRONMENT = '").append(planCriteria.getEnvironment()).append("'");
		sql.append(" AND COMPANY_CODE = '").append(planCriteria.getCompanyCode()).append("'");
		if (tdesc.hasProductPrefix() && planCriteria.getProductPrefix().trim().length()>0 && !planCriteria.getProductPrefix().equals("N"))
			sql.append(" AND PRODUCT_PREFIX = '").append(planCriteria.getProductPrefix()).append("'");
		sql.append(" AND TABLE_ID = '").append(tdesc.getTableId()).append("'");
		sql.append(" AND SUBSET_NAME = '").append(planCriteria.getTablePtrSubset().trim()).append("'");
		_log.debug("Sql to get WIP rows: "+sql.toString());
		return sql.toString();
	}
	
	private void setProductPrefix(WIPTableFilter wipFilter) {

		wipFilter.setProductSuffix(null);

		String tableId = wipFilter.getTableIds().elementAt(0);
		TableDescriptor td = EnvironmentManager.getInstance().getEnvironment(wipFilter.getEnvironment()).getTableDescMgr()
		        .getTableDescriptor(tableId);
		if (td.hasProductPrefix())
			return;

		String productPrefix = wipFilter.getProductPrefix();
		if (productPrefix != null && !productPrefix.equals("N")
		        && !productPrefix.equals("C"))
			wipFilter.setProductPrefix(null);
	}

	private TreeMap<Object, Object> finalMap = new TreeMap<Object, Object>();

	public Iterator<Object> getMergedRowsWithPaging(WIPTableFilter wipFilter)
	        throws Exception {

		Environment environment = EnvironmentManager.getInstance().getEnvironment(wipFilter.getEnvironment());
	    int pagingSize = environment.getProps().getPagingSize();
		
		wipFilter.setPagingSize(pagingSize);

		// to hold extra rows (i.e. update and insert with same old key)
		Map<String, Object> extraRows = new HashMap<String, Object>();
		prepareMergedRowsWithPaging(wipFilter, extraRows);

		if (!finalMap.isEmpty()) {
			firstEbcidicKey = getKey(wipFilter.getEnvironment(), finalMap
			        .get(finalMap.firstKey()), true);
			lastEbcidicKey = getKey(wipFilter.getEnvironment(), finalMap
			        .get(finalMap.lastKey()), true);
		}

		if ( extraRows.isEmpty() )
			return finalMap.values().iterator();	// normal case
		
		// add in extra rows (i.e. update and insert with same old key)
		Collection<Object> finalRows = new ArrayList<Object>();
		for (Object obj : finalMap.values()) {
			finalRows.add(obj);
			if ( obj instanceof CommonWIPRow  ) {
				CommonWIPRow wipRow = (CommonWIPRow) obj;
				String key = wipRow.getEbcdicKey(false);
				if ( extraRows.containsKey(key) )
					finalRows.add(extraRows.get(key));
			}
		}
		return finalRows.iterator();
	}

	private void prepareMergedRowsWithPaging(WIPTableFilter wipFilter, Map<String, Object> extraRows)
	        throws Exception {

		int pagingSize = wipFilter.getPagingSize();

		TreeMap<String, Object> sortedRows = new TreeMap<String, Object>();

		// Get <paging_size> source rows
		Vector<Row> sourceRows = getSourceRows(wipFilter);

		// Get <paging_size> WIP rows
		// Don't pass user condition to WIPRows.  Deal with user condition below
		String userCondition = wipFilter.getUserCondition();
		wipFilter.setUserCondition(null);
		ArrayList<WIPRow> wipRows = getWIPRows(wipFilter, "ORDER BY EBCDIC_KEY asc");
		wipFilter.setUserCondition(userCondition);

		if (sourceRows.isEmpty() && wipRows.isEmpty())
			return;

		// Add all wiprows to the treemap
		for (int i = 0; i < wipRows.size(); i++) {
			CommonWIPRow wipRow = (CommonWIPRow) wipRows.get(i);
			String key = wipRow.getEbcdicKey(false);
			Object previous = sortedRows.put(key, wipRow);
			if ( previous != null )
				extraRows.put(key, previous);
		}

		// Add the source rows next and do not add if a wip row with same key is
		// found
		// T0129 - only set "deletedSomeRows" if a wip delete actually replaced	a source row	
		boolean deletedSomeRows = false;
		String environment = wipFilter.getEnvironment();
		for (int j = 0; j < sourceRows.size(); j++) {
			Row sourceRow = sourceRows.elementAt(j);
			String sourceKey = sourceRow.getEBCDICKey(false);
			if (!sortedRows.containsKey(sourceKey))
				sortedRows.put(sourceKey, sourceRow);
			else {
				CommonWIPRow wipRow = (CommonWIPRow) sortedRows.get(sourceKey);
				verifyOldData(sourceRow, wipRow);
				if (wipRow.isDeleted()) {
					deletedSomeRows = true;
				}
			}
		}

		// TreeMap sorts all rows in ascending order

		// now remove all the wip deletes from the sorted rows
		for (int i = 0; i < wipRows.size(); i++) {
			CommonWIPRow wipRow = (CommonWIPRow) wipRows.get(i);
			if (wipRow.isDeleted()) {
				sortedRows.remove(wipRow.getEbcdicKey(false));
			}
		}

		// put all rows in final
		finalMap.putAll(sortedRows);
		
		if ( wipFilter.hasUserCondition() ) {
			if ( filterWipRows(wipFilter) )
				deletedSomeRows = true;
		}

		// if we deleted rows, we need to recurse to get more rows
		// in case some of the next batch of rows should go in this page.
		// prepare keys and get the rows again
		if (deletedSomeRows) {
			String ebcidicKey, concatKey = null;
			if (wipFilter.isInitTableOpen())
				wipFilter.setPagingMode("next");

			if (wipFilter.getPagingMode().equalsIgnoreCase("next")) {
				Object lastSourceRow;
				Object lastWipRow;
				if (!sourceRows.isEmpty() && !wipRows.isEmpty()) {
					lastSourceRow = sourceRows.elementAt(sourceRows.size() - 1);
					// SPR 6567: last wip row should track source
					//lastWipRow = wipRows.get(wipRows.size() - 1);
					lastWipRow = lastSourceRow;
				} else if (!sourceRows.isEmpty()) {
					lastSourceRow = sourceRows.elementAt(sourceRows.size() - 1);
					lastWipRow = lastSourceRow;
				} else {
					lastWipRow = wipRows.get(wipRows.size() - 1);
					lastSourceRow = lastWipRow;
				}

				ebcidicKey = getKey(environment, lastWipRow, true);
				concatKey = getKey(environment, lastSourceRow, false);
			} else {
				Object firstSourceRow;
				Object firstWipRow;
				if (!sourceRows.isEmpty() && !wipRows.isEmpty()) {
					firstSourceRow = sourceRows.elementAt(0);
					firstWipRow = wipRows.get(0);
				} else if (!sourceRows.isEmpty()) {
					firstSourceRow = sourceRows.elementAt(0);
					firstWipRow = firstSourceRow;
				} else {
					firstWipRow = wipRows.get(0);
					firstSourceRow = firstWipRow;
				}

				ebcidicKey = getKey(environment, firstWipRow, true);
				concatKey = getKey(environment, firstSourceRow, false);
			}

			wipFilter.setEbcdicKey(ebcidicKey);
			wipFilter.setKeyForPaging(concatKey);
			// we don't want to keep getting the locator row upon recursion
			// SPR 6567 - preserve original locator value
			String locatorSave = wipFilter.getLocator();
			wipFilter.setLocator(null);
			prepareMergedRowsWithPaging(wipFilter, extraRows);
			wipFilter.setLocator(locatorSave);

		} else if (finalMap.size() > pagingSize) { // if rows size > paging
			// size, trim it and send
			trimRows(wipFilter);			
		}
	}

	private void trimRows(WIPTableFilter wipFilter) {

		int pagingSize = wipFilter.getPagingSize();
		boolean isNext = wipFilter.isPagingNext();

		TreeMap<Object, Object> newMap = new TreeMap<Object, Object>();
		int count = 0;
		Iterator<Object> iter = finalMap.keySet().iterator();

		// send first <paging_size> rows from top to bottom
		if (wipFilter.isInitTableOpen() || isNext) {
			nextIndicator = true;
			while (iter.hasNext() && count < pagingSize) {
				Object key = iter.next();
				newMap.put(key, finalMap.get(key));
				count++;
			}
		} else { // send first <paging_size> rows from bottom to top

			prevIndicator = true;
			int rowsToDiscard = finalMap.size() - pagingSize;
			while (iter.hasNext()) {
				Object key = iter.next();
				if (count >= rowsToDiscard)
					newMap.put(key, finalMap.get(key));
				count++;
			}
		}

		finalMap.clear();
		finalMap.putAll(newMap);
		newMap.clear();
	}

	public Collection<Object> getAllMergedRows(WIPTableFilter wipFilter)
	        throws Exception {
		return getAllMergedRows(null, wipFilter);
	}

	public Collection<Object> getAllMergedRows(Connection conn, WIPTableFilter wipFilter)
	        throws Exception {

		TreeMap<String, Object> allRows = new TreeMap<String, Object>();
		// to hold extra rows (i.e. update and insert with same old key)
		Map<String, Object> extraRows = new HashMap<String, Object>();

		// NO PAGING-GET ALL
		wipFilter.setPagingMode(null);
		wipFilter.setKeyForPaging(null);
		wipFilter.setLocator(null);
		wipFilter.setInitTableOpen(false);
		wipFilter.setPagingSize(-1);

		Vector<Row> sourceRows = getSourceRows(wipFilter);
		if (sourceRows == null)
			throw new Exception("UNABLE TO GET THE SOURCE ROWS");

		ArrayList<WIPRow> wipRows = null;

		if (conn != null)
			wipRows = getWIPRows(conn, wipFilter, "ORDER BY EBCDIC_KEY asc");
		else
			wipRows = getWIPRows(wipFilter, "ORDER BY EBCDIC_KEY asc");

		if (sourceRows.isEmpty() && wipRows.isEmpty())
			return allRows.values();

		for (int i = 0; i < wipRows.size(); i++) {
			CommonWIPRow wipRow = (CommonWIPRow) wipRows.get(i);
			String key = wipRow.getEbcdicKey(false);
			Object previous = allRows.put(key, wipRow);
			if ( previous != null )
				extraRows.put(key, previous);
		}

		// Add the source rows next and do not add if a wip row with same key is
		// found
		/**
		 * TODO-? Not validating the invalid WIP data (WIP rows with no matching
		 * source rows)*
		 */
		for (int j = 0; j < sourceRows.size(); j++) {
			Row sourceRow = sourceRows.elementAt(j);
			String sourceKey = sourceRow.getEBCDICKey(false);
			if (!allRows.containsKey(sourceKey))
				allRows.put(sourceKey, sourceRow);
			else
				verifyOldData(sourceRow, (CommonWIPRow) allRows.get(sourceKey));
		}

		if ( extraRows.isEmpty() )
			return allRows.values();		// normal case
		
		// add in extra rows (i.e. update and insert with same old key)
		Collection<Object> finalRows = new ArrayList<Object>();
		for (Object obj : allRows.values()) {
			finalRows.add(obj);
			if ( obj instanceof CommonWIPRow  ) {
				CommonWIPRow wipRow = (CommonWIPRow) obj;
				String key = wipRow.getEbcdicKey(false);
				if ( extraRows.containsKey(key) )
					finalRows.add(extraRows.get(key));
			}
		}
		return finalRows;
	}

	// WMABASEIXI-2312 - workaround for the fact that Import->Replace has no access to source rows (to set old data)
	private void verifyOldData(Row sourceRow, CommonWIPRow wipRow) throws Exception {
		if ( wipRow.getOperation().equals("UPDATE") 
				&& wipRow.getOldConcatKey().equals(wipRow.getNewConcatKey()) 
				&& wipRow.getOldData().equals(wipRow.getNewData()) ) {
			wipRow.setOldData(sourceRow.buildData());
		}
	}
	
	/** Method to identify the invalid WIPRows while Merging the data * */

	public HashMap<Object, CommonWIPRow> getInvalidWIPRows(Vector sourceRows, ArrayList wipRows,
	        String env) throws Exception {
		HashMap<Object, CommonWIPRow> rows = new HashMap<Object, CommonWIPRow>();

		HashMap<String, CommonWIPRow> wipData = new HashMap<String, CommonWIPRow>();
		for (int i = 0; i < wipRows.size(); i++) {
			CommonWIPRow wipRow = (CommonWIPRow) wipRows.get(i);
			wipData.put(wipRow.getEbcdicKey(false), wipRow);
		}

		HashMap<String, Row> sourceData = new HashMap<String, Row>();
		for (int i = 0; i < sourceRows.size(); i++) {
			Row row = (Row) sourceRows.get(i);
			sourceData.put(row.getEBCDICKey(false), row);
		}

		Set<Map.Entry<String, CommonWIPRow>> entries = wipData.entrySet();
		for ( Map.Entry<String, CommonWIPRow> entry : entries ) {
			String wipKey = entry.getKey();
			CommonWIPRow wipRow = entry.getValue();
			String operation = wipRow.getOperation();

			if (operation.equalsIgnoreCase(Constants.INSERT_OPERATION)) {
				if (sourceData.containsKey(wipKey))
					rows.put(wipKey, wipRow);
			} else if (!sourceData.containsKey(wipKey)) { // UPDATE AND DELETE
				// OPERATION
				rows.put(wipKey, wipRow);
			}
		}
		return rows;
	}

	public ArrayList<Object> getMergedViewForSubset(String schema, String company,
	        String tableID, String productPrefix, String subset)
	        throws Exception {
		return getMergedViewForSubset(null, schema, company, tableID,
		        productPrefix, subset);

	}

	public ArrayList<Object> getMergedViewForSubset(Connection conn, String schema,
	        String company, String tableID, String productPrefix, String subset)
	        throws Exception {

		WIPTableFilter wtf = new WIPTableFilter();
		Vector<String> tableIds = new Vector<String>();
		tableIds.addElement(tableID);
		wtf.setTableIds(tableIds);
		wtf.setSubsetName(subset);
		wtf.setCompanyCode(company);
		wtf.setEnvironment(schema);
		wtf.setProductPrefix(productPrefix);

		ArrayList<Object> list = null;

		if (conn != null)
			list = new ArrayList<Object>(getAllMergedRows(conn, wtf));
		else
			list = new ArrayList<Object>(getAllMergedRows(wtf));

		return list;

	}

	public static String getKey(String env, Object row, boolean ebcidic)
	        throws Exception {

		if (row instanceof Row) {

			Row srow = (Row) row;
			if (ebcidic) {
				return srow.getEBCDICKey(true);
			} else {
				return UtilMethods.buildWithDelim(srow, env, true);
			}
		} else {

			CommonWIPRow crow = (CommonWIPRow) row;
			if (ebcidic) {
				return crow.getEbcdicKey(true);
			} else {
				return crow.getOldConcatKey();
			}
		}
	}

	public String getFirstEbcidicKey() {
		return firstEbcidicKey;
	}

	public String getLastEbcidicKey() {
		return lastEbcidicKey;
	}

	public boolean isNextIndicator() {
		return nextIndicator;
	}

	public boolean isPrevIndicator() {
		return prevIndicator;
	}

	private boolean filterWipRows(WIPTableFilter wipFilter) throws Exception {
		boolean ret = false;
		
		String tableId = wipFilter.getData().getTablePtrId();
		Environment environment = EnvironmentManager.getInstance().getEnvironment(wipFilter.getEnvironment());
		TableDescriptor td = environment.getTableDescMgr().getTableDescriptor(tableId);
		if (td == null) {
			throw new Exception("Table Descriptor not found for table " + tableId);
		}
		
		RowManager rowManager = new RowManager(environment.getId());
		SqlExpressionEvaluator ee = new SqlExpressionEvaluator(wipFilter.getUserCondition());
		
		Iterator<Object> iter = finalMap.keySet().iterator();
		while (iter.hasNext()) {
			Object key = iter.next();
			Object value = finalMap.get(key);
			if ( value instanceof WIPRow ) {
				WIPRow wipRow = (WIPRow) value;
				Row row = rowManager.getBlankRow(tableId);
				row.setColumns(wipRow.getNewConcatKey()+wipRow.getNewData(), Constants.STAB);
				if ( !ee.eval(td, row) ) {
					iter.remove();
					ret = true;
				}
			}
		}
		
		return ret;
	}
}
