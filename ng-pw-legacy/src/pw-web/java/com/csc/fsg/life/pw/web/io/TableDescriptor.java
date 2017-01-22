
package com.csc.fsg.life.pw.web.io;

import java.io.*;
import java.sql.*;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.xml.stream.*;
import javax.xml.stream.events.*;

import org.apache.commons.logging.Log;

import com.csc.fsg.life.pw.common.util.IOTokenizer;
import com.csc.fsg.life.pw.common.util.Utils;
import com.csc.fsg.life.pw.common.util.Constants;
import com.csc.fsg.life.pw.common.transferobjects.PlanRowTO;
import com.csc.fsg.life.pw.common.transferobjects.PlanTO;
import com.csc.fsg.life.common.util.StringParserList;
import com.csc.fsg.life.exceptions.WrapperException;
import com.csc.fsg.life.pw.web.avm.AVKey;
import com.csc.fsg.life.pw.web.controller.IResponse;
import com.csc.fsg.life.pw.web.utils.*;
import com.csc.fsg.life.pw.common.rules.SpecialHandling;
import com.csc.fsg.life.pw.web.config.*;
import com.csc.fsg.life.pw.web.environment.*;
import com.csc.fsg.life.pw.web.log.PWServerLogManager;

/* Modifications ENH764, T0103, T0091, T0094, T0115, CCCV-E501, ENH992, V-E892, ENH1006, T0129, WMA-417, WMA-1182, ENH1063-05, ENH1209  */
// CCCV-E501 - removed commented references to productSystem

public abstract class TableDescriptor extends WizObject implements Serializable {

	private static Log _log = PWServerLogManager.getLog(TableDescriptor.class
	        .getPackage().getName());

	protected String _tableName;
	protected Vector<ColumnDescriptor> _columnDescriptors;
	protected String _tableId;
	protected Hashtable<String, ColumnDescriptor> _columnDescriptorsTable;
	protected Vector<ColumnDescriptor> _keyColumnDescriptors;
	protected Class _rowClass;
	protected Hashtable<Integer, ColumnDescriptor> columnDetails;
	protected boolean nextIndicator = false;
	protected boolean previousIndicator = false;
	//protected static HashMap<String, HashMap<String, Hashtable<String, String>>> tableConstraintsData = null;
	//public static ArrayList<String> tableVisitedArry = new ArrayList<String>();
	private boolean keyFieldsInOrder;
		
	public void initialize() {
		super.initialize();
		_columnDescriptors = new Vector<ColumnDescriptor>();
		_columnDescriptorsTable = new Hashtable<String, ColumnDescriptor>();
		initializeDescriptors();
	}
	
	public void initializeDescriptors() {
		initializeColumnDescriptors();
		initializeKeyColumnDescriptors();
	}
		
	public String prepareInsertStmt(String schemaName) {
		return null;
	}

	public String prepareUpdateStmt(String schemaName) {
		return null;
	}

	public String prepareDeleteStmt(String schemaName) {
		return null;
	}
	
	public void initializeColumnDescriptors() {
	}

	public void addColumnDescriptor(ColumnDescriptor cd) {
		_columnDescriptors.addElement(cd);
		_columnDescriptorsTable.put(cd.getDbColumnName().trim(), cd);
	}

	public void setTableName(String s) {
		_tableName = s;
	}

	public void setTableId(String s) {
		_tableId = s;
	}

	public void setRowClass(Class c) {
		_rowClass = c;
	}

	public String getTableName() {
		return _tableName;
	}

	public Vector<ColumnDescriptor> getColumnDescriptors() {
		return _columnDescriptors;
	}

	public Hashtable<String, ColumnDescriptor> getColumnDescriptorsTable() {
		return _columnDescriptorsTable;
	}

	public Vector<ColumnDescriptor> getKeyColumnDescriptors() {
		return _keyColumnDescriptors;
	}
    
	// WMA-1182
    public Vector<ColumnDescriptor> getDataColumnDescriptors() {
		Vector<ColumnDescriptor> datacds = new Vector<ColumnDescriptor>();
		for ( ColumnDescriptor cd : _columnDescriptors ) {
			if (cd.isKey())
				continue;
			datacds.add(cd);
		}
		return datacds;
    }

	public String getTableId() {
		return _tableId;
	}

	public String getDDLName() {
		return getTableName();
	}

	public Row getNewRowObject() {
		return (Row) newInstanceOf(getRowClass());
	}

	public int getNumberOfColumns() {
		return _columnDescriptors.size();
	}

	public Class getRowClass() {
		return _rowClass;
	}

	public void initializeKeyColumnDescriptors() {

		Vector<ColumnDescriptor> vector = new Vector<ColumnDescriptor>();
		Vector<ColumnDescriptor> v = _columnDescriptors;
		int size = v.size();
		int x=-1;
		boolean isInOrder = true;
		for (int i = 0; i < size; i++) {
			ColumnDescriptor cd = v.get(i);
			if (cd.isKey()) {
				if(x!=i-1)
					isInOrder = false;	
				x = i;
				vector.addElement(cd);
			}
		}
		setKeyFieldsInOrder(isInOrder);
		_keyColumnDescriptors = vector;
	}


	public Vector<Row> getTableRowsGivenCond(Environment env, String cond) throws Exception{

		Connection con = null;
		Statement stmt = null;
		ResultSet resultSet = null;
		Vector<Row> rows = null;
		
		String schema  = env.getSchema();
		try 
		{
			String sql = "SELECT * FROM "+schema+"."+_tableName+" "+cond;
			con = DBConnMgr.getInstance().getConnection(env.getId(),DBConnMgr.BUSINESS_RULES);
			stmt = con.createStatement();
			resultSet = stmt.executeQuery(sql);
			rows = hydrateFrom(resultSet,env.getId(),false,false);
		
		} finally {
			Utils.closeResultSet(resultSet);
			Utils.closeStatement(stmt);
			DBConnMgr.getInstance().releaseConnection(con);
		}

		return rows;
	}
		
	/*
	public Vector<Row> getTableRowsGivenCond(Connection con,Environment env, String cond) throws Exception{

		
		Statement stmt = null;
		ResultSet resultSet = null;
		Vector<Row> rows = null;
		
		String schema  = env.getSchema();
		try 
		{
			String sql = "SELECT * FROM "+schema+"."+_tableName+" "+cond;
			stmt = con.createStatement();
			resultSet = stmt.executeQuery(sql);
			rows = hydrateFrom(resultSet,env.getId(),false,false);
		} catch (Exception exception) {
			throw WrapperException.wrap(exception);
		} finally {
			Utils.closeResultSet(resultSet);
			Utils.closeStatement(stmt);
		}

		return rows;
	}
	*/
	
//	private Vector<Row> getPagedRows(Connection conn, Environment environment, TableFilter tableFilter)
//	{
//		
//		Vector<Row> rows = new Vector<Row>();
//		ResultSet resultSet = null;
//		PreparedStatement pstmt = null;
//		
//		String view = tableFilter.getView();
//		
//		boolean isEntireTableView = ( view!=null && view.equalsIgnoreCase(Constants.ENTIRE_TABLE)) ;
//		boolean isCommonTable = SpecialHandling.getInstance().isCommonTable(_tableName);
//		boolean isSubsetMode = !(isEntireTableView || isCommonTable);
//		boolean isNext = tableFilter.isNextPage();
//		boolean hasLocator = tableFilter.isLocatorView();
//		
//		try
//		{
//			String sqlString  = getPagingSQL(environment.getSchema(), isSubsetMode, isNext, hasLocator);
//			pstmt = conn.prepareStatement(sqlString);
//			
//			String pagingKey = tableFilter.getPagingKey();
//	
//			String key = "";
//	
//			if (!hasLocator){
//				if (pagingKey.indexOf("|") == -1)
//					key = getPagingKeyForWiprow(pagingKey, environment.getId(),  tableFilter);
//				else
//					key = getPagingKey(environment.getId(), pagingKey);
//			} else{
//				key = getPagingKey(environment.getId(), pagingKey);
//			}
//	
//			int param = 1;
//			String company = tableFilter.getCompanyCode();
//			pstmt.setString(param++, company);
//			
//			if (isSubsetMode){
//				if (hasTableSubset())
//					pstmt.setString(param++, tableFilter.getTableSubset());
//	
//				if (hasProductPrefix())
//					pstmt.setString(param++, tableFilter.getProductPrefix());
//			}
//	
//			pstmt.setString(param++, key);
//			resultSet = SqlPW.query(SqlPW.SQL_TBL_PAGE, pstmt);
//			rows = hydrateFrom(resultSet, environment.getId(),true, isNext);
//			
//		} catch (Exception exception) {
//			throw WrapperException.wrap(exception);
//		} finally {
//			Utils.closeResultSet(resultSet);
//			Utils.closeStatement(pstmt);
//	
//		}
//		return rows;
//	}
	
	public Vector<Row> getTableRows(Connection con,Environment env, TableFilter tableFilter) throws Exception{
		
		
		ResultSet resultSet = null;
		NamedParameterPreparedStatement pstmt = null;
		
		Vector<Row> rows = null;
		String schema = env.getSchema();
		boolean isPagingMode = tableFilter.isPagingMode();
				
		try {

			if (isPagingMode)
				//return getPagedRows(con,env,tableFilter);
  			    return getPagedRowsNew(con,env,tableFilter);
			else
			{
				
				String sql = "SELECT * FROM "+schema+"."+_tableName;
				
				boolean whereStr = false;
				
				StringBuffer where = new StringBuffer(" WHERE ");
				
				if (tableFilter.getCompanyCode()!=null){
					where.append(" COMPANY_CODE = '"+tableFilter.getCompanyCode()+"' ");
					whereStr = true;
				}
				
				// refactor plan key: handle empty prefix
				if (hasProductPrefix() && tableFilter.getProductPrefix()!=null && tableFilter.getProductPrefix().length() > 0){
					if (whereStr)
						where.append(" AND ");
					where.append(" PRODUCT_PREFIX = '"+tableFilter.getProductPrefix()+"' ");
					whereStr = true;
				}
				
				if (hasTableSubset() && tableFilter.getTableSubset()!=null){
					if (whereStr)
						where.append(" AND ");
					where.append(" TABLE_SUBSET = '"+tableFilter.getTableSubset()+"' ");
					whereStr = true;
				}
				
				Map<String, Comparable> keysMap = new HashMap<String, Comparable>();
				if ( tableFilter.getUserCondition() != null ){
					String hvTranslated = handleUserConditionHighValues(tableFilter.getUserCondition(), keysMap, env);
					if (whereStr)
						where.append(" AND ");
					where.append("(").append(hvTranslated).append(" ) ");
					whereStr = true;
				}
				
				if (whereStr)
					sql = sql+where;
				
				StringBuffer orderby = new StringBuffer(" ORDER BY ");
				boolean isFirstOne = true;
				for (int i=0;i<_columnDescriptors.size();i++){
					ColumnDescriptor cd = _columnDescriptors.get(i);
					if (cd.isKey()){
						if (!isFirstOne) 
							orderby.append(", ");
						orderby.append(cd.getDbColumnName());
						isFirstOne = false;
					}
				}
				
				_log.info("TableDescriptor.getTableRows: getting first page for " + getTableName());
				if ( _log.isDebugEnabled() )
					_log.debug("TableDescriptor.getTableRows sql: " + sql + orderby);
				_log.info("TableDescriptor.getTableRows sql: " + sql + orderby);
				pstmt = new NamedParameterPreparedStatement(con, sql + orderby);
				Set<Map.Entry<String, Comparable>> entries = keysMap.entrySet();
				for ( Map.Entry<String, Comparable> entry : entries )
					pstmt.setObject(entry.getKey(), entry.getValue());
				resultSet = pstmt.executeQuery();
				
				boolean isFirstPage = tableFilter.isFirstPage();
				
				if (isFirstPage) 
					rows = hydrateFrom(resultSet, env.getId(),true,true);
				else 
					rows = hydrateFrom(resultSet, env.getId(),false,false);
				_log.info("TableDescriptor.getTableRows: " + rows.size() + " rows fetched.");
			}	

		} catch (SQLException exception) {
			if ( tableFilter.getUserCondition() != null )
				throw new InfoException(Constants.USER_FILTER_ERROR + exception.getMessage());
			throw WrapperException.wrap(exception);
		} catch (Exception exception) {
			throw WrapperException.wrap(exception);
		} finally {
			Utils.closeResultSet(resultSet);
			if (pstmt != null)
				pstmt.close();
		}
		
		return rows;
	}
	
	public Vector<Row> getTableRows(Environment env, TableFilter tableFilter) throws Exception{

		Connection con = null;
		Vector<Row> rows = null;		
		try {

			con = DBConnMgr.getInstance().getConnection(env.getId(),DBConnMgr.BUSINESS_RULES);
			rows = getTableRows(con,env,tableFilter);
		

		} catch (Exception exception) {
			throw WrapperException.wrap(exception);
		} finally {
			
			DBConnMgr.getInstance().releaseConnection(con);
		}
		
		return rows;
	}
	
	// TT wma SPR 6579 - add countTableRows
	public int countTableRows(Connection con,Environment env, TableFilter tableFilter) throws Exception{
		
		int ret = 0;
		ResultSet resultSet = null;
		PreparedStatement pstmt = null;
		
		String schema = env.getSchema();
				
		try {

			String sql = "SELECT COUNT(*) FROM "+schema+"."+_tableName;
			
			boolean whereStr = false;
			
			StringBuffer where = new StringBuffer(" WHERE ");
			
			if (tableFilter.getCompanyCode()!=null){
				where.append(" COMPANY_CODE = '"+tableFilter.getCompanyCode()+"' ");
				whereStr = true;
			}
			
			// refactor plan key: handle empty prefix
			if (hasProductPrefix() && tableFilter.getProductPrefix()!=null && tableFilter.getProductPrefix().length() > 0){
				if (whereStr)
					where.append(" AND ");
				where.append(" PRODUCT_PREFIX = '"+tableFilter.getProductPrefix()+"' ");
				whereStr = true;
			}
			
			if (hasTableSubset() && tableFilter.getTableSubset()!=null){
				if (whereStr)
					where.append(" AND ");
				where.append(" TABLE_SUBSET = '"+tableFilter.getTableSubset()+"' ");
				whereStr = true;
			}
			
			if (whereStr)
				sql = sql+where;
			
			_log.info("TableDescriptor.countTableRows: counting rows for " + getTableName());
			if ( _log.isDebugEnabled() )
				_log.debug("TableDescriptor.countTableRows sql: " + sql);
			pstmt = con.prepareStatement(sql);
			resultSet = SqlPW.query(SqlPW.SQL_TBL_SUBSET, pstmt);
			if ( resultSet.next() )
				ret = resultSet.getInt(1);
			_log.info("TableDescriptor.countTableRows: " + ret + " rows.");
		} catch (Exception exception) {
			throw WrapperException.wrap(exception);
		} finally {
			Utils.closeResultSet(resultSet);
			Utils.closeStatement(pstmt);
		}
		
		return ret;
	}

	public int countTableRows(Environment env, TableFilter tableFilter) throws Exception{

		Connection con = null;
		try {
			con = DBConnMgr.getInstance().getConnection(env.getId(),DBConnMgr.BUSINESS_RULES);
			return countTableRows(con,env,tableFilter);		
		} catch (Exception exception) {
			throw WrapperException.wrap(exception);
		} finally {		
			DBConnMgr.getInstance().releaseConnection(con);
		}
	}

//	private String pad(String s, int len) {
//		String padding = "                                                                                                    ";
//		return (s + padding).substring(0, len);
//	}
//
//	private String padNumber(String s, int len, int dlen,boolean nex) {
//		int decimalIndex = s.indexOf('.');
//		if (decimalIndex == -1) {
//			// should not have a space in a decimal field
//			s = s.trim();
//			String s2 = "00000000000000" + s + ".";
//			int ix = s2.length() - (len + 1);
//			if (nex)
//				return s2.substring(ix);
//			else
//				return " " + s2.substring(ix);
//		} else {
//			int leftLen = len - dlen;
//			String left = s.substring(0, decimalIndex);
//			String right = s.substring(decimalIndex + 1);
//			String zeroes = "00000000000000";
//			int padLen = leftLen - left.length();
//			if (padLen > 0)
//				left = zeroes.substring(0, padLen) + left;
//			padLen = dlen - right.length();
//			if (padLen > 0)
//				left = right + zeroes.substring(0, padLen);
//			if (nex)
//				return  left + "." + right;
//			else
//				return " " + left + "." + right;
//		}
//	}

//	private String getPagingKey(String envId, String pagingKey) {
//		int end;
//		int tokenNo = -1;
//		Vector<ColumnDescriptor> columndescriptors = getKeyColumnDescriptors();
//		String token, key = "";
//		ColumnDescriptor cd;
//
//		for (int start = 0; start < pagingKey.length();) {
//			end = pagingKey.indexOf("|", start);
//
//			if (end == -1)
//				end = pagingKey.length();
//
//			tokenNo++;
//			token = pagingKey.substring(start, end);
//			cd = columndescriptors.get(tokenNo);
//
//			int dataType = cd.getDataType();
//			if (dataType == Types.DECIMAL)
//				token = padNumber(token, cd.getColumnSize(), cd
//				        .getDecimalDigits(),EnvironmentManager.getInstance().getEnvironment(envId).isNexEnv());
//
//			token = HighValueHandler.convertAsteriskToHV(token, envId);
//
//			if (dataType == Types.CHAR || dataType == Types.VARCHAR)
//				token = pad(token, cd.getColumnSize());
//
//			key += token;
//			start = end + 1;
//		}
//		return key;
//	}
//
//	private String getPagingKeyForWiprow(String str, String envId,
//	        TableFilter tableFilter) {
//
//		String signlessKey = "";
//		int signIndex;
//		int start = 0;
//
//		while ((signIndex = str.indexOf('+')) != -1) {
//			signlessKey += str.substring(start, signIndex);
//			str = str.substring(++signIndex);
//		}
//
//		if (str.length() > 0)
//			signlessKey += str;
//
//		String key = "";
//
//		String tokenString = "", realString = "", decimalString = "";
//	
//		Vector<ColumnDescriptor> columndescriptors = getKeyColumnDescriptors();
//
//		for (int i = 0; i < columndescriptors.size(); i++) {
//			ColumnDescriptor cd = columndescriptors.get(i);
//
//			int columnSize = cd.getColumnSize();
//
//			if (signlessKey.length() <= 0)
//				break;
//
//			tokenString += signlessKey.substring(start, columnSize);
//			tokenString = tokenString.trim();
//
//			tokenString = HighValueHandler.convertAsteriskToHV(tokenString,envId);
//
//			int dataType = cd.getDataType();
//
//			if (dataType == Types.DECIMAL) {
//				int decimalDigits = cd.getDecimalDigits();
//				if (decimalDigits > 0) {
//					while (tokenString.length() > 0
//					        && tokenString.charAt(0) == '0')
//						tokenString = tokenString.substring(1);
//
//					int lengthTkn = tokenString.length();
//					if (lengthTkn == 0) {
//						realString = "0";
//						decimalString = "0";
//						for (int d = 1; d < decimalDigits; d++)
//							decimalString += "0";
//					} else {
//						realString = tokenString.substring(0, lengthTkn
//						        - decimalDigits);
//						decimalString = tokenString.substring(lengthTkn
//						        - decimalDigits);
//					}
//					tokenString = realString + "." + decimalString;
//				}
//
//				tokenString = padNumber(tokenString, columnSize, cd
//				        .getDecimalDigits(),EnvironmentManager.getInstance().getEnvironment(envId).isNexEnv());
//			}
//
//			if (dataType == Types.CHAR || dataType == Types.VARCHAR)
//				tokenString = pad(tokenString, columnSize);
//
//			key = key + tokenString;
//			tokenString = "";
//			signlessKey = signlessKey.substring(columnSize);
//		}
//		return key;
//	}

	// WMA-1182 add isImport argument
	private void pagingHydrate(Vector<Row> rows,ResultSet resultSet, String envId, boolean isNext, boolean isImport)
	        throws Exception {

		Environment environment = EnvironmentManager.getInstance().getEnvironment(envId);
	    int pagingSize = (isImport) ? environment.getProps().getPacketSize()
	    		: environment.getProps().getPagingSize();
		int rowCount = 0;
		
		if (isNext){
			
			nextIndicator = false;
			while (resultSet.next() && !nextIndicator){
				if (rowCount++ < pagingSize) 
					addRow(resultSet, envId, rows);
				else 
					nextIndicator = true;
			}
			
		}else{
			
			previousIndicator = false;
			Vector<Row> prevRows = new Vector<Row>();
			while (resultSet.next() && !previousIndicator) {
				if (rowCount++ < pagingSize) 
					addRow(resultSet, envId, prevRows);
				else 
					previousIndicator = true;
			}
			
			for (int j = (prevRows.size()) - 1; j >= 0; j--) 
				rows.addElement(prevRows.elementAt(j));
		}
		
	}

	//  WMA-1182
	public Vector<Row> hydrateFrom(ResultSet resultSet, String envId, boolean paging,boolean isNext)
	        throws Exception {
		return hydrateFrom(resultSet, envId, paging, isNext, false);
	}

	public Vector<Row> hydrateFrom(ResultSet resultSet, String envId, boolean paging,boolean isNext,boolean isImport)
	        throws Exception {

		Vector<Row> rows = new Vector<Row>();
		
		if (paging)
			pagingHydrate(rows, resultSet, envId,isNext,isImport);	// WMA-1182
		else{
			while (resultSet.next()) 
				addRow(resultSet, envId, rows);
		}
		return rows;
	}

	private void addRow(ResultSet resultSet, String envId, Vector<Row> rows) throws Exception {
	
		int size = _columnDescriptors.size();
		
		Row row = getNewRowObject();
		rows.addElement(row);
		row.setTableDescriptor(this);
		row.setEnvId(envId);
		
		for (int i = 0; i < size; i++) {
			ColumnDescriptor cd = _columnDescriptors.get(i);
			cd.hydrateFrom(resultSet, row, envId);
		}
		
	}

	// ENH764
	public Row hydrateFrom(ResultSet resultSet, String envId) throws Exception {
	
		int size = _columnDescriptors.size();
		
		Row row = getNewRowObject();
		row.setTableDescriptor(this);
		row.setEnvId(envId);
		
		for (int i = 0; i < size; i++) {
			ColumnDescriptor cd = _columnDescriptors.get(i);
			cd.hydrateFrom(resultSet, row, envId);
		}
		return row;
	}

	public ColumnDescriptor getColumn(int position) {

		ColumnDescriptor columnDescriptor = null;
		
		if (columnDetails == null){
			Integer columnPosition = null;
			columnDetails = new Hashtable<Integer, ColumnDescriptor>();

			Vector<ColumnDescriptor> v = _columnDescriptors;
			int size = v.size();

			for (int i = 0; i < size; i++) {
				columnDescriptor = v.get(i);
				columnPosition = Integer.valueOf(columnDescriptor.getDbColumnPosition());
				columnDetails.put(columnPosition, columnDescriptor);
			}
		}
			
		columnDescriptor = columnDetails.get(Integer.valueOf(position));

		return columnDescriptor;
	}


	public String getMetaData(String schemaName, int authority,
	        String productCode) {

		StringBuffer sb = new StringBuffer();
		Vector<ColumnDescriptor> v = _columnDescriptors;
		int size = v.size();

		for (int i = 0; i < size; i++) {
			ColumnDescriptor cd = v.get(i);
			sb.append(cd.getColMetaData(schemaName, authority, productCode));
		}

		return sb.toString();

	}

	
	public boolean hasField(String fieldName) {

		Vector<ColumnDescriptor> cds = _columnDescriptors;

		for (int i = 0; i < cds.size(); i++) {
			ColumnDescriptor cd = cds.get(i);

			if (cd.getDbColumnName().trim().equals(fieldName.trim())) {
				return true;
			}
		}
		return false;
	}

	public boolean hasProductPrefix() {
		return hasField("PRODUCT_PREFIX");
	}

	public boolean hasTableSubset() {
		return hasField("TABLE_SUBSET");
	}


	public Object getValueOfColumn(String colName, Row row) {

		Vector<ColumnDescriptor> cds = _columnDescriptors;

		for (int i = 0; i < cds.size(); i++) {
			ColumnDescriptor cd = cds.get(i);

			if (cd.getDbColumnName().trim().equalsIgnoreCase(colName)) {
				return cd.getValue(row);
			}
		}

		return null;
	}

	public String getPagingSQL(String schemaName, boolean isSubsetMode,
	        boolean isNext, boolean locator) {
		return null;
	}

	public boolean getNextIndicator() {
		return nextIndicator;
	}

	public boolean getPrevIndicator() {
		return previousIndicator;
	}

	
	public void setValuesForInsertStmt(PreparedStatement stmt,
	        String envId, String row, String delim) throws SQLException,
	        Exception {

		StringParserList columns = new StringParserList(row, delim);

		columns.unstring();

		long startTime = System.currentTimeMillis();

		ColumnDescriptor cd = null;

		for (int i = 0; i < _columnDescriptors.size(); i++) {
			String value = (String) columns.get(i);
			cd = _columnDescriptors.get(i);
			int dataType = cd.getDataType();
		
			// TT wmA SPR 6429 - upper case the value for import
			if (dataType==1 && !SpecialHandling.getInstance().isMixedCase(getTableName(), cd.getDbColumnName()) ) 
				value = value.toUpperCase();
			
			if (cd.isKey() && dataType == 1)
				value = HighValueHandler.convertAsteriskToHV(value, envId);
			switch (dataType) {
				case 1: // CHAR
					stmt.setString(i + 1, value);
					break;
				case 3: // DOUBLE
				case 5: // SHORT
					stmt.setObject(i + 1, new Double(value));
					break;
				case 91: // DATE
					stmt.setObject(i + 1, value);
					break;
				default:
					break;
			}
		}
		SqlPW.lap(SqlPW.SQL_IMPORT_PREP_BATCH, startTime);

	}

	public void toStream(StringBuffer sb) {
		sb.append(getNumberOfColumns());
		sb.append(Constants.STAB);
		sb.append(getDDLName());
		sb.append(Constants.STAB);
		sb.append(getTableId());
	}

	public void toStream(IResponse out) {
		StringBuffer sb = new StringBuffer();
		toStream(sb);
		out.print(sb.toString());
	}

	public ColumnDescriptor getColumnDescriptor(String columnName) {
		Object obj = _columnDescriptorsTable.get(columnName.trim());
		if (obj != null)
			return (ColumnDescriptor) obj;

		return null; // Should throw exception ..for now checking for null at
		// calling place

	}

	/** Assumption: Unique column for a tableid and variation in data name */
	HashMap<String, ColumnDescriptor> pointerColumns = null;

	public ColumnDescriptor getPointerColumnFor(String tableId, String variation) {

		if (pointerColumns == null) {
			pointerColumns = new HashMap<String, ColumnDescriptor>();
			for (int i = 0; i < _columnDescriptors.size(); i++) {
				ColumnDescriptor cd = _columnDescriptors
				        .get(i);
				if (!cd.isKey()) {
					ArrayList<String> list = cd.getMetaData()
					        .getAllTableIdNVariations();
					for (int j = 0; j < list.size(); j++)
						pointerColumns.put(list.get(j), cd);
				}
			}
		}

		if (variation == null)
			variation = "";

		return pointerColumns.get(tableId.trim()
		        + variation.trim());
	}
	
	public boolean hasDecimalColumns(String schema){
		for (int count = 0; count < _columnDescriptors.size(); count++){
			if ( _columnDescriptors.get(count).getDataType()==3)
				return true;
		}
		return false;
	}
	
	private Vector<Row> getPagedRowsNew(Connection conn, Environment environment, TableFilter tableFilter)
	{
		
		Vector<Row> rows = null;
		ResultSet resultSet = null;
		NamedParameterPreparedStatement pstmt = null;
		
		String view = tableFilter.getView();
		
		boolean isEntireTableView = ( view!=null && view.equalsIgnoreCase(Constants.ENTIRE_TABLE)) ;
		boolean isCommonTable = SpecialHandling.getInstance().isCommonTable(_tableName);
		boolean isSubsetMode = !(isEntireTableView || isCommonTable);
		boolean isNext = tableFilter.isNextPage();
		boolean hasLocator = tableFilter.isLocatorView();
		boolean isImport = tableFilter.isImport();	// WMA-1182
		
		try
		{
			_log.info("TableDescriptor.getPagedRowsNew: getting page for " + getTableName()
					+ ". subset=" + Boolean.toString(isSubsetMode)
					+ " next=" + Boolean.toString(isNext)
					+ " locator=" + Boolean.toString(hasLocator)
					+ " import=" + Boolean.toString(isImport) );
			String sqlString  = getPagingSQL(environment.getSchema(), isSubsetMode, isNext, hasLocator || isImport);
			
			String pagingKey = tableFilter.getPagingKey();
			Map<String, Comparable> keysMap = getPagingKeyMap(environment.getId(), pagingKey);

			if ( tableFilter.getUserCondition() != null ) {
				String hvTranslated = handleUserConditionHighValues(tableFilter.getUserCondition(), keysMap, environment);
				String userCondition = " AND (" + hvTranslated + " ) " ;
				int pos = sqlString.indexOf("ORDER BY");
				if ( pos == -1 ) {
					sqlString += userCondition;
				} else {
					String where = sqlString.substring(0, pos);
					String orderBy = sqlString.substring(pos);
					sqlString = where + userCondition + orderBy;
				}
			}
			
			pstmt = new NamedParameterPreparedStatement(conn,sqlString);
			Set<Map.Entry<String, Comparable>> entries = keysMap.entrySet();
			for ( Map.Entry<String, Comparable> entry : entries )
				pstmt.setObject(entry.getKey(), entry.getValue());
			resultSet = pstmt.executeQuery();
			rows = hydrateFrom(resultSet, environment.getId(),true, isNext, isImport);
			_log.info("TableDescriptor.getPagedRowsNew: " + rows.size() + " rows fetched.");
			
			// TT wmA SPR 4329 - if locator, check for previous rows
			if (hasLocator) {
				_log.info("TableDescriptor.getPagedRowsNew: doing locator previous query.");
				String dbProduct = conn.getMetaData().getDatabaseProductName();
				// If MFE connection, avoid MFE driver bug by reading out result set
				if ( dbProduct.startsWith("XDB") ) {
					try {
						while ( resultSet.next() ) {
							String tmp = resultSet.getString(1);
						}
					} catch ( Exception e ) {
					}
				}
				Utils.closeResultSet(resultSet);
				pstmt.close();
				sqlString = getPagingSQL(environment.getSchema(), isSubsetMode,
						false, false);
				
				if (sqlString.indexOf("<")==-1){
					previousIndicator = false;
				}else{					
					if ( tableFilter.getUserCondition() != null ) {
						String hvTranslated = handleUserConditionHighValues(tableFilter.getUserCondition(), keysMap, environment);
						String userCondition = " AND (" + hvTranslated + " ) " ;
						int pos = sqlString.indexOf("ORDER BY");
						if ( pos == -1 ) {
							sqlString += userCondition;
						} else {
							String where = sqlString.substring(0, pos);
							String orderBy = sqlString.substring(pos);
							sqlString = where + userCondition + orderBy;
						}
					}
					pstmt = new NamedParameterPreparedStatement(conn, sqlString);
					for ( Map.Entry<String, Comparable> entry : entries )
						pstmt.setObject(entry.getKey(), entry.getValue());
					resultSet = pstmt.executeQuery();
					previousIndicator = false;
					if ( resultSet.next()) 
						previousIndicator = true;
					// If MFE connection, avoid MFE driver bug by reading out result set
					if ( dbProduct.startsWith("XDB") ) {
						try {
							while ( resultSet.next() ) {
								String tmp = resultSet.getString(1);
							}
						} catch ( Exception e ) {
						}
					}
				}
			}
		} catch (Exception exception) {
			throw WrapperException.wrap(exception);
		} finally {
			Utils.closeResultSet(resultSet);
			if (pstmt!=null)
				pstmt.close();
		}
		return rows;
	}
	
	private Map<String, Comparable> getPagingKeyMap(String envId, String pagingKey) {
		Map<String, Comparable> keyMap = new HashMap<String, Comparable>();
		Vector<ColumnDescriptor> columndescriptors = getKeyColumnDescriptors();
		
		if (pagingKey.indexOf("|") == -1)
		{
			String signlessKey = "";
			int signIndex=0, start = 0;

			while ((signIndex = pagingKey.indexOf('+')) != -1) {
				signlessKey += pagingKey.substring(start, signIndex);
				pagingKey = pagingKey.substring(++signIndex);
			}

			if (pagingKey.length() > 0)
				signlessKey += pagingKey;

			for (int i = 0; i < columndescriptors.size(); i++) 
			{
				ColumnDescriptor cd = columndescriptors.get(i);
				int dataType = cd.getDataType();
				String column = cd.getDbColumnName().toLowerCase();
				
				int columnSize = cd.getColumnSize();

				if (signlessKey.length() <= 0)
					break;

				String tokenString = "";
				tokenString += signlessKey.substring(start, columnSize);
				tokenString = tokenString.trim();

				tokenString = HighValueHandler.convertAsteriskToHV(tokenString,envId);

				if (dataType == Types.CHAR || dataType == Types.VARCHAR ||dataType == Types.DATE ){
					keyMap.put(column, tokenString);
				}  
				else if (dataType == Types.DECIMAL) 
				{
					int decimalDigits = cd.getDecimalDigits();
					
					if (decimalDigits > 0) {
						
						while (tokenString.length() > 0 && tokenString.charAt(0) == '0')
							tokenString = tokenString.substring(1);

						int lengthTkn = tokenString.length();
						String  realString = "", decimalString = "";
						
						if (lengthTkn == 0){
							
							realString = "0";
							decimalString = "0";
							for (int d = 1; d < decimalDigits; d++)
								decimalString += "0";
						} else 	{
							realString = tokenString.substring(0, lengthTkn - decimalDigits);
							decimalString = tokenString.substring(lengthTkn - decimalDigits);
						}
						tokenString = realString + "." + decimalString;
					}
					keyMap.put(column, new Double(tokenString));
				}
				signlessKey = signlessKey.substring(columnSize);
			}
		}
		else
		{
			String[] keys = pagingKey.split("\\|");
			for (int i=0;i<keys.length;i++){
				ColumnDescriptor cd = columndescriptors.get(i);
				String column = cd.getDbColumnName().toLowerCase();
				String key = HighValueHandler.convertAsteriskToHV(keys[i], envId);
				int dataType = cd.getDataType();
				switch (dataType) {
				case 1: // CHAR
				case 91://DATE	
					keyMap.put(column, key);
					break;
				case 3: // DOUBLE
				case 5: // SHORT
					keyMap.put(column, new Double(key));
					break;
				}
			}
		}
		return keyMap;
	}

	public String handleUserConditionHighValues(String input, Map<String, Comparable> preparedStmtMap,
			Environment environment) {
		StringBuffer output = new StringBuffer();
		String[] tokens = input.split("[\\s]");
		Pattern p = Pattern.compile("^'\\*+'");		// asterisk pattern
		for (int i=0; i < tokens.length; i++) {
			String token = tokens[i];
			if ( p.matcher(token).matches() ) {		// this token contains asterisks
				String columnName = tokens[i-2];
				ColumnDescriptor cd = getColumnDescriptor(columnName);
				if ( cd != null && cd.isKey() ) {
					int length = token.length() - 2;
					String key = "highValue" + length;
					String hvString = HighValueHandler.getHighValues(length, environment.getId());
					preparedStmtMap.put(key, hvString);
					token = ":" + key;
				}
			}
			output.append(" ").append(token);
		}
		
		return output.toString();
	}
	
	public boolean isKeyFieldsInOrder() {
		return keyFieldsInOrder;
	}

	public void setKeyFieldsInOrder(boolean keyFieldsInOrder) {
		this.keyFieldsInOrder = keyFieldsInOrder;
	}
	
	public String parseToMatchOrder(String key, String data){
		
		StringBuffer newConcatKeyData = new StringBuffer();
		Vector<ColumnDescriptor> v = _columnDescriptors;
		
		for (int i = 0; i < v.size(); i++) {
			
			ColumnDescriptor cd = v.get(i);
			int columnSize = cd.getColumnSize();
			String val = null;
			
			if (cd.isKey()) {
				if(key.startsWith("+"))
					columnSize++;
				val = key.substring(0,columnSize);
				key = key.substring(columnSize);
				
			}else{
				if(data.startsWith("+"))
					columnSize++;
				val = data.substring(0,columnSize);
				data = data.substring(columnSize);
			}
			
			newConcatKeyData.append(val);
		}
		return newConcatKeyData.toString();
	}


	public String generateKeyDataSpideRow(String line){
		IOTokenizer tokenizer = new IOTokenizer(line, Constants.STAB);
		StringBuffer key = new StringBuffer();
		StringBuffer data = new StringBuffer();
		Vector<ColumnDescriptor> v = _columnDescriptors;
		
		for (int i = 0; i < v.size(); i++) {
			ColumnDescriptor cd = v.get(i);
			if (cd.isKey()) {
				if(!key.toString().equals(""))
					key.append(Constants.SPIPE);
				key.append(tokenizer.nextToken());
			}else{
				if(!data.toString().equals(""))
					data.append(Constants.SPIPE);
				data.append(tokenizer.nextToken());
			}
		}
		return key.toString()+ Constants.STAB + data.toString();
	}

	// ENH992
	public static String ddlNameToId(String ddlName) {
		if ( ddlName == null || ddlName.length() < 5 )
			return null;
		return ddlName.substring(ddlName.length() - 4, ddlName.length() - 1);
	}
	
	// ENH1063-05
	public void processRow(XMLEventReader eventReader, String userId, String envId, String company, 
			String project, WIPRows wipRows) throws XMLStreamException, Exception{
		
		CommonWIPRow cWipRow = new CommonWIPRow();
		cWipRow.setEnvironment(envId);
		cWipRow.setDdlName(getDDLName());
		cWipRow.setTableId(getTableId());
		cWipRow.setCompanyCode(company);
		cWipRow.setProjectName(project);
		cWipRow.setChangeUserId(userId);
		cWipRow.setPromoteUserId(userId);
	 	cWipRow.setTimestamp((new Timestamp((new java.util.Date()).getTime())).toString());
	 	cWipRow.setPackageId(System.currentTimeMillis()+"");
	 	cWipRow.setErrorIndicator("");
	 	cWipRow.setPromotionLock("L");
		cWipRow.setDbSequence(wipRows.getDBSequenceNumber());
		cWipRow.setEbcdicKey("");
		cWipRow.setAttrMask("0");
		cWipRow.setWipActionAttrMask(Constants.TABLE_ATTR_INSERT);
				
		while (eventReader.hasNext()) 
		{
			XMLEvent event = eventReader.nextEvent();
			
			if (event.isStartElement()) 
			{
				StartElement startElement = event.asStartElement();
				
				if (startElement.getName().getLocalPart().equals("operation")) {
					event = eventReader.nextEvent();
					cWipRow.setOperation(event.asCharacters().getData());
					continue;
				}
				
				if (startElement.getName().getLocalPart().equals("fields")) {
					 readNewValues(eventReader,cWipRow);
					 continue;
				}
				
				if (cWipRow.getOperation().equals("UPDATE") && startElement.getName().getLocalPart().equals("old_key")) {
					readOldKey(eventReader,cWipRow);
					continue;
				}else{
					cWipRow.setOldConcatKey(cWipRow.getNewConcatKey());
					cWipRow.setOldData(cWipRow.getNewData());				
				}
			}
			
			if (event.isEndElement()){
				EndElement endElement = event.asEndElement();
				if (endElement.getName().getLocalPart().equals(getDDLName())) 
					break;
				
			}
		}
		wipRows.add(cWipRow);
	}
	// ENH1063-05
	public void processPlanRow(XMLEventReader eventReader, String userId, String envId, String company, 
			String project, WIPRows wipRows) throws XMLStreamException, Exception{
				
		PlanWIPRow planWIPRow = (PlanWIPRow) WIPRowFactory.createWIPRowOfType(Constants.PLAN_WIP);
		planWIPRow.setProjectName(project);
		planWIPRow.setCompanyCode(company);
		planWIPRow.setEnvironment(envId);
		planWIPRow.setChangeUserId("");
		planWIPRow.setWipActionAttrMask(Constants.TABLE_ATTR_INSERT);
		planWIPRow.setAttrMask("0");
		planWIPRow.setChangeUserId(userId);
		planWIPRow.setPromoteUserId(userId);
		planWIPRow.setTimestamp((new Timestamp((new java.util.Date()).getTime())).toString());
		planWIPRow.setPackageId(System.currentTimeMillis()+"");
		planWIPRow.setErrorIndicator("");
		planWIPRow.setPromotionLock("L");
		planWIPRow.setDbSequence(wipRows.getDBSequenceNumber());
		while (eventReader.hasNext()) 
		{
			XMLEvent event = eventReader.nextEvent();
			
			if (event.isStartElement()) 
			{
				StartElement startElement = event.asStartElement();
				if (startElement.getName().getLocalPart().equals("operation")) {
					event = eventReader.nextEvent();
					planWIPRow.setOperation(event.asCharacters().getData());
					continue;
				}
				if (startElement.getName().getLocalPart().equals("fields")) {
					extractNewValues(eventReader,planWIPRow);
				}
				
				if (planWIPRow.getOperation().equals("UPDATE") && startElement.getName().getLocalPart().equals("old_key")) {
					extractOldKey(eventReader,planWIPRow);
					continue;
				}else{
					planWIPRow.setOldConcatKey(planWIPRow.getNewConcatKey());
					planWIPRow.setOldData(planWIPRow.getNewData());				
				}
				
			}
			
			if (event.isEndElement()){
				EndElement endElement = event.asEndElement();
				if (endElement.getName().getLocalPart().equals("tableRef")) 
					break;
				
			}
		}
		wipRows.add(planWIPRow);
	}
	// ENH1063-05
	public void processLine(String line, String userId, String envId, String company, 
			String project, WIPRows wipRows) throws Exception{
		
		CommonWIPRow cWipRow = new CommonWIPRow();
		cWipRow.setEnvironment(envId);
		cWipRow.setDdlName(getDDLName());
		cWipRow.setTableId(getTableId());
		cWipRow.setCompanyCode(company);
		cWipRow.setProjectName(project);
		cWipRow.setChangeUserId(userId);
		cWipRow.setPromoteUserId(userId);
	 	cWipRow.setTimestamp((new Timestamp((new java.util.Date()).getTime())).toString());
	 	cWipRow.setPackageId(System.currentTimeMillis()+"");
	 	cWipRow.setErrorIndicator("");
	 	cWipRow.setPromotionLock("L");
		cWipRow.setDbSequence(wipRows.getDBSequenceNumber());
		cWipRow.setEbcdicKey("");
		cWipRow.setAttrMask("0");
		cWipRow.setWipActionAttrMask(Constants.TABLE_ATTR_INSERT);
		
		String delim = "|";	// configure?
		String[] tokens = (new IOTokenizer(line, delim)).getValues();
		
		String operation;
		if ( tokens[0].equals("I") )
			operation = Constants.INSERT_OPERATION;
		else if ( tokens[0].equals("U") )
			operation = Constants.UPDATE_OPERATION;
		else if ( tokens[0].equals("D") )
			operation = Constants.DELETE_OPERATION;
		else
			throw new Exception("Unknown operation [" + tokens[0] + "]" );
		cWipRow.setOperation(operation);
		ArrayList<String> originalData = new ArrayList<String>();
		if ( Constants.INSERT_OPERATION.equals(operation) ||  Constants.DELETE_OPERATION.equals(operation)  ) {
			int numColumns = getNumberOfColumns();
			StringBuffer sb = new StringBuffer();
			for (int i=1; i <= numColumns; i++) {
				sb.append("1");
			}
			cWipRow.setChangedColumns(sb.toString());
		}
		if ( Constants.UPDATE_OPERATION.equals(operation) ) {
			int numColumns = getNumberOfColumns();
			if ( tokens.length != (1+(2*numColumns)) )
				throw new Exception("Update operation expected " + (1+(2*numColumns)) + " tokens bot got " + tokens.length );
			StringBuffer sb = new StringBuffer();
			for (int i=1; i <= numColumns; i++) {
				if ( tokens[i].equals(tokens[i+numColumns]) )
					sb.append("0");
				else
				{
					sb.append("1");
					originalData.add(tokens[i+numColumns]);
				}
			}
			cWipRow.setChangedColumns(sb.toString());
			cWipRow.setOriginalData(originalData);
		}
		
		if(SpecialHandling.getInstance().getProductPrefix(getDDLName(), null) !=null)
			cWipRow.setProductPrefix(SpecialHandling.getInstance().getProductPrefix(getDDLName(), null));
		
		
		StringBuffer newKey = new StringBuffer();
		StringBuffer newData = new StringBuffer();
		List<ColumnDescriptor> cds = getColumnDescriptors();
		
		int tokenIndex = 1;
		for ( ColumnDescriptor cd : cds ) {
			if ( tokenIndex >= tokens.length )
				throw new Exception("Expected new value for column " + cd.getDbColumnName());
			String columnValue = tokens[tokenIndex].trim();	// trim?
			
			int originalColumnLength = cd.getColumnSize();
			int appendType = UtilMethods.getAppendType(cd.getDataType());

			StringBuffer returnValue = new StringBuffer();
			if (appendType == UtilMethods.APPEND_ZEROS) {
				// first append trailing zeroes to fill out decimal digits
				int numDecimals = cd.getDecimalDigits();
				if ( numDecimals > 0 ) {
					int decimalIndex = columnValue.indexOf(".");
					if ( decimalIndex != -1 ) {
						String[] numberTokens = columnValue.split("\\.");
						String afterDecimal = (numberTokens.length > 1) ? numberTokens[1] : "";
						for (int i=afterDecimal.length(); i < cd.getDecimalDigits(); i++) {
							columnValue += "0";
						}
					} else {
						// no decimal point in number.... fill all decimals with 0
						for (int i=0; i < cd.getDecimalDigits(); i++) {
							columnValue += "0";
						}
					}
				}
				UtilMethods.appendLeadingZeros(returnValue, columnValue,originalColumnLength,cd.getDbColumnName());
			} else {
				UtilMethods.appendTrailingSpaces(returnValue, columnValue, originalColumnLength, cd.getDbColumnName());
			}
			
			if (cd.isKey())
				newKey.append(returnValue);
			else	
				newData.append(returnValue);
			
			if (cd.getDbColumnName().equals("COMPANY_CODE"))
				cWipRow.setCompanyCode(columnValue);
			
			if (cd.getDbColumnName().equals("PRODUCT_PREFIX"))
				cWipRow.setProductPrefix(columnValue);
			
			if (cd.getDbColumnName().equals("TABLE_SUBSET"))
				cWipRow.setSubsetName(columnValue);
			
			tokenIndex++;
		}
		
		cWipRow.setNewConcatKey(newKey.toString());
		cWipRow.setNewData(newData.toString());
		
		if ( operation == Constants.UPDATE_OPERATION ) {
			StringBuffer oldKey = new StringBuffer();
			StringBuffer oldData = new StringBuffer();
			for ( ColumnDescriptor cd : cds ) {
				if ( tokenIndex >= tokens.length )
					throw new Exception("Expected old value for column " + cd.getDbColumnName());
				String columnValue = tokens[tokenIndex].trim();	// trim?
				
				int originalColumnLength = cd.getColumnSize();
				int appendType = UtilMethods.getAppendType(cd.getDataType());

				StringBuffer returnValue = new StringBuffer();
				if (appendType == UtilMethods.APPEND_ZEROS)
					UtilMethods.appendLeadingZeros(returnValue, columnValue,originalColumnLength,cd.getDbColumnName());
				else
					UtilMethods.appendTrailingSpaces(returnValue, columnValue, originalColumnLength,cd.getDbColumnName());
				
				if (cd.isKey())
					oldKey.append(returnValue);
				else	
					oldData.append(returnValue);
				
				tokenIndex++;
			}
			
			cWipRow.setOldConcatKey(oldKey.toString());
			cWipRow.setOldData(oldData.toString());
			
		} else {
			cWipRow.setOldConcatKey(newKey.toString());
			cWipRow.setOldData(newData.toString());
		}
		wipRows.add(cWipRow);
	}
	
	
	// ENH1209
	public void processDelimitedLine(String line, String userId, String envId, String company, 
			String project, WIPRows wipRows) throws Exception{
		
		CommonWIPRow cWipRow = new CommonWIPRow();
		cWipRow.setEnvironment(envId);
		cWipRow.setDdlName(getDDLName());
		cWipRow.setTableId(getTableId());
		cWipRow.setCompanyCode(company);
		cWipRow.setProjectName(project);
		cWipRow.setChangeUserId(userId);
		cWipRow.setPromoteUserId(userId);
	 	cWipRow.setTimestamp((new Timestamp((new java.util.Date()).getTime())).toString());
	 	cWipRow.setPackageId(System.currentTimeMillis()+"");
	 	cWipRow.setErrorIndicator("");
	 	cWipRow.setPromotionLock("L");
		cWipRow.setDbSequence(wipRows.getDBSequenceNumber());
		cWipRow.setEbcdicKey("");
		cWipRow.setAttrMask("0");
		cWipRow.setWipActionAttrMask(Constants.TABLE_ATTR_INSERT);
		
		String delim = "|";	// configure?
		String[] tokens = (new IOTokenizer(line, delim)).getValues();
		
		String operation;
		if ( tokens[0].equals("I") )
			operation = Constants.INSERT_OPERATION;
		else if ( tokens[0].equals("U") )
			operation = Constants.UPDATE_OPERATION;
		else if ( tokens[0].equals("D") )
			operation = Constants.DELETE_OPERATION;
		else
			throw new Exception("Unknown operation [" + tokens[0] + "]" );	
		cWipRow.setOperation(operation);
		
		List<String> currentValues = new ArrayList<String>();
		Map<Integer, String> updatedValues = new HashMap<Integer, String>();
		fillLineValues(tokens, operation, envId, currentValues, updatedValues);

		ArrayList<String> originalData = new ArrayList<String>();
		if ( Constants.INSERT_OPERATION.equals(operation) ||  Constants.DELETE_OPERATION.equals(operation)  ) {
			int numColumns = getNumberOfColumns();
			StringBuffer sb = new StringBuffer();
			for (int i=0; i < numColumns; i++) {
				sb.append("1");
			}
			cWipRow.setChangedColumns(sb.toString());
		}
		if ( Constants.UPDATE_OPERATION.equals(operation) ) {
			int numColumns = getNumberOfColumns();
			//if ( tokens.length != (1+(2*numColumns)) )
			//	throw new Exception("Update operation expected " + (1+(2*numColumns)) + " tokens but got " + tokens.length );
			StringBuffer sb = new StringBuffer();
			for (int i=0; i < numColumns; i++) {
				String newValue = updatedValues.get(Integer.valueOf(i));
				if ( newValue == null )
					sb.append("0");
				else
				{
					sb.append("1");
					originalData.add(currentValues.get(i));
				}
			}
			cWipRow.setChangedColumns(sb.toString());
			cWipRow.setOriginalData(originalData);
		}
		
		if(SpecialHandling.getInstance().getProductPrefix(getDDLName(), null) !=null)
			cWipRow.setProductPrefix(SpecialHandling.getInstance().getProductPrefix(getDDLName(), null));
		
		
		StringBuffer newKey = new StringBuffer();
		StringBuffer newData = new StringBuffer();
		List<ColumnDescriptor> cds = getColumnDescriptors();
		
		int columnIndex = 0;
		for ( ColumnDescriptor cd : cds ) {
			String columnValue = updatedValues.get(Integer.valueOf(columnIndex));
			if ( columnValue == null )
				columnValue = currentValues.get(columnIndex);
			columnValue = columnValue.trim();
			
			int originalColumnLength = cd.getColumnSize();
			int appendType = UtilMethods.getAppendType(cd.getDataType());

			StringBuffer returnValue = new StringBuffer();
			if (appendType == UtilMethods.APPEND_ZEROS) {
				// first append trailing zeroes to fill out decimal digits
				int numDecimals = cd.getDecimalDigits();
				if ( numDecimals > 0 ) {
					int decimalIndex = columnValue.indexOf(".");
					if ( decimalIndex != -1 ) {
						String[] numberTokens = columnValue.split("\\.");
						String afterDecimal = (numberTokens.length > 1) ? numberTokens[1] : "";
						for (int i=afterDecimal.length(); i < cd.getDecimalDigits(); i++) {
							columnValue += "0";
						}
					} else {
						// no decimal point in number.... fill all decimals with 0
						for (int i=0; i < cd.getDecimalDigits(); i++) {
							columnValue += "0";
						}
					}
				}
				UtilMethods.appendLeadingZeros(returnValue, columnValue,originalColumnLength,cd.getDbColumnName());
			} else {
				UtilMethods.appendTrailingSpaces(returnValue, columnValue, originalColumnLength, cd.getDbColumnName());
			}
			
			if (cd.isKey())
				newKey.append(returnValue);
			else	
				newData.append(returnValue);
			
			if (cd.getDbColumnName().equals("COMPANY_CODE"))
				cWipRow.setCompanyCode(columnValue);
			
			if (cd.getDbColumnName().equals("PRODUCT_PREFIX"))
				cWipRow.setProductPrefix(columnValue);
			
			if (cd.getDbColumnName().equals("TABLE_SUBSET"))
				cWipRow.setSubsetName(columnValue);
			
			columnIndex++;
		}
		
		cWipRow.setNewConcatKey(newKey.toString());
		cWipRow.setNewData(newData.toString());
		
		if ( operation == Constants.UPDATE_OPERATION ) {
			StringBuffer oldKey = new StringBuffer();
			StringBuffer oldData = new StringBuffer();
			columnIndex = 0;
			for ( ColumnDescriptor cd : cds ) {
				String columnValue = currentValues.get(columnIndex).trim();
				
				int originalColumnLength = cd.getColumnSize();
				int appendType = UtilMethods.getAppendType(cd.getDataType());

				StringBuffer returnValue = new StringBuffer();
				if (appendType == UtilMethods.APPEND_ZEROS)
					UtilMethods.appendLeadingZeros(returnValue, columnValue,originalColumnLength,cd.getDbColumnName());
				else
					UtilMethods.appendTrailingSpaces(returnValue, columnValue, originalColumnLength,cd.getDbColumnName());
				
				if (cd.isKey())
					oldKey.append(returnValue);
				else	
					oldData.append(returnValue);
				
				columnIndex++;
			}
			
			cWipRow.setOldConcatKey(oldKey.toString());
			cWipRow.setOldData(oldData.toString());
			
		} else {
			cWipRow.setOldConcatKey(newKey.toString());
			cWipRow.setOldData(newData.toString());
		}
		wipRows.add(cWipRow);
	}

	// ENH1209
	private void fillLineValues(String[] tokens, String operation, String envId,
			List<String> currentValues, Map<Integer, String> updatedValues)
			throws Exception {
		
		if ( Constants.INSERT_OPERATION.equals(operation) ) { 
			for (int i=1; i < tokens.length; i++) {
				currentValues.add(tokens[i]);
			}
		} else {			
			Environment environment = EnvironmentManager.getInstance().getEnvironment(envId);
			String delim = "|";
			String company = null;
			String subset = null;
			String productPrefix = null;
			int tokenNum = 1;
			StringBuffer sbKey = new StringBuffer();
			for (ColumnDescriptor cd : getColumnDescriptors()) {
				if ( cd.isKey() ) {
					String dbColumnName = cd.getDbColumnName();
					if ( dbColumnName.equals("COMPANY_CODE") )
						company = (String) tokens[tokenNum];
					else if ( dbColumnName.equals("TABLE_SUBSET") )
						subset = (String) tokens[tokenNum];
					else if ( dbColumnName.equals("PRODUCT_PREFIX") )
						productPrefix = (String) tokens[tokenNum];
					if ( sbKey.length() != 0 )
						sbKey.append(delim);
					sbKey.append(tokens[tokenNum]);
				}
				tokenNum++;
			}
			
			TableFilter tableFilter = new TableFilter();
			tableFilter.setCompanyCode(company);
			tableFilter.setTableSubset(subset);
			tableFilter.setTableName(getTableName());
			
			if (productPrefix != null) {
				if (!(productPrefix.equals("N") || productPrefix.equals("C")))
					tableFilter.setProductPrefix(productPrefix);
			}
			
			tableFilter.setPagingMode(true);
			tableFilter.setNextPage(true);
			tableFilter.setPagingModeStr("NEXT");
			tableFilter.setPagingKey(sbKey.toString());
			tableFilter.setIsImport(true);
	
			Vector<Row> sourceRows = getTableRows(environment, tableFilter);
			if ( sourceRows != null && !sourceRows.isEmpty() ) {
				String delimValues = sourceRows.get(0).getColumnsAsString(delim);
				String[] currentTokens = (new IOTokenizer(delimValues, delim)).getValues();
				for (int i=0; i < currentTokens.length; i++) {
					currentValues.add(currentTokens[i]);
				}
			} else {
				throw new InfoException("No existing " + getTableId() + " row with keys " + sbKey);
			}
			
			if ( Constants.UPDATE_OPERATION.equals(operation) ) { 
				String updateDelim = ";";
				for (int i=1; i < tokens.length; i++) {
					if ( tokens[i].contains(updateDelim) ) {
						String updateTokens[] = (new IOTokenizer(tokens[i], updateDelim)).getValues();
						int colNum = Integer.parseInt(updateTokens[0]) - 1;
						updatedValues.put(Integer.valueOf(colNum), updateTokens[1]);
					}
				}
			}
		}
	}
	
	// ENH1063-05
	private void readNewValues(XMLEventReader eventReader,CommonWIPRow cWipRow) throws XMLStreamException,Exception {
		
		StringBuffer newKey = new StringBuffer(cWipRow.getCompanyCode());
		StringBuffer newData = new StringBuffer();
		
		while (eventReader.hasNext()) 
		{
			XMLEvent event = eventReader.nextEvent();
			
			if (event.isStartElement()) 
			{
				StartElement startElement = event.asStartElement();
				
				ColumnDescriptor cd = getColumnDescriptor(startElement.getName().getLocalPart());
				event = eventReader.nextEvent();
				String columnValue = event.asCharacters().getData();
				
				int originalColumnLength = cd.getColumnSize();
				int apendType = UtilMethods.getAppendType(cd.getDataType());
	
				StringBuffer returnValue = new StringBuffer();
				if (apendType == UtilMethods.APPEND_ZEROS)
					UtilMethods.appendLeadingZeros(returnValue, columnValue,originalColumnLength, cd.getDbColumnName());
				else
					UtilMethods.appendTrailingSpaces(returnValue, columnValue, originalColumnLength, cd.getDbColumnName());
				
				if (cd.isKey())
					newKey.append(returnValue);
				else	
					newData.append(returnValue);
				
				if (cd.getDbColumnName().equals("PRODUCT_PREFIX"))
					cWipRow.setProductPrefix(columnValue);
				
				if (cd.getDbColumnName().equals("TABLE_SUBSET"))
					cWipRow.setSubsetName(columnValue);
					
				continue;
			}
			
			if (event.isEndElement()){
				EndElement endElement = event.asEndElement();
				if (endElement.getName().getLocalPart().equals("fields")) 
					break;
			}
		}
		
		cWipRow.setNewConcatKey(newKey.toString());
		cWipRow.setNewData(newData.toString());
	}
	// ENH1063-05
private void extractNewValues(XMLEventReader eventReader,PlanWIPRow pWipRow) throws XMLStreamException,Exception {
		
		StringBuffer newKey = new StringBuffer(pWipRow.getCompanyCode());
		StringBuffer newData = new StringBuffer();
		
		while (eventReader.hasNext()) 
		{
			XMLEvent event = eventReader.nextEvent();
			
			if (event.isStartElement()) 
			{
				StartElement startElement = event.asStartElement();
				
				ColumnDescriptor cd = getColumnDescriptor(startElement.getName().getLocalPart().toUpperCase());
				event = eventReader.nextEvent();
				String columnValue = "";
				if(event.isCharacters()){
					
					 columnValue = event.asCharacters().getData();
				}
				
				int originalColumnLength = cd.getColumnSize();
				int apendType = UtilMethods.getAppendType(cd.getDataType());
	
				StringBuffer returnValue = new StringBuffer();
				if (apendType == UtilMethods.APPEND_ZEROS)
					UtilMethods.appendLeadingZeros(returnValue, columnValue,originalColumnLength, cd.getDbColumnName());
				else
					UtilMethods.appendTrailingSpaces(returnValue, columnValue, originalColumnLength, cd.getDbColumnName());
				
				if (cd.isKey())
					newKey.append(returnValue);
				else	
					newData.append(returnValue);
				
				if (cd.getDbColumnName().equals("PRODUCT_PREFIX"))
					pWipRow.setProductPrefix(columnValue);
				
				if (cd.getDbColumnName().equals("TABLE_PTR_SUBSET"))
					pWipRow.setSubsetName(columnValue);
					
				continue;
			}
			
			if (event.isEndElement()){
				EndElement endElement = event.asEndElement();
				if (endElement.getName().getLocalPart().equals("fields")) 
					break;
			}
		}
		
		pWipRow.setNewConcatKey(newKey.toString());
		pWipRow.setNewData(newData.toString());
	}
//ENH1063-05
	private void readOldKey(XMLEventReader eventReader,CommonWIPRow cWipRow) throws XMLStreamException {
		
		StringBuffer oldKey = new StringBuffer();
		
		while (eventReader.hasNext()) 
		{
			XMLEvent event = eventReader.nextEvent();
			
			if (event.isStartElement()) {
				event = eventReader.nextEvent();
				oldKey.append( event.asCharacters().getData() ).append("\t");
				continue;
			}
			
			if (event.isEndElement()){
				EndElement endElement = event.asEndElement();
				if (endElement.getName().getLocalPart().equals("old_key")) 
					break;
			}
		}
		System.out.println("Old Key "+oldKey);
	}
	// end ENH1063-05
private void extractOldKey(XMLEventReader eventReader,PlanWIPRow pWipRow) throws XMLStreamException,Exception {
	
	StringBuffer oldKey = new StringBuffer(pWipRow.getCompanyCode());
	StringBuffer oldData = new StringBuffer();
	
	while (eventReader.hasNext()) 
	{
		XMLEvent event = eventReader.nextEvent();
		
		if (event.isStartElement()) 
		{
			StartElement startElement = event.asStartElement();
			
			ColumnDescriptor cd = getColumnDescriptor(startElement.getName().getLocalPart().toUpperCase());
			event = eventReader.nextEvent();
			String columnValue = "";
			if(event.isCharacters()){
				
				 columnValue = event.asCharacters().getData();
			}
			int originalColumnLength = cd.getColumnSize();
			int apendType = UtilMethods.getAppendType(cd.getDataType());

			StringBuffer returnValue = new StringBuffer();
			if (apendType == UtilMethods.APPEND_ZEROS)
				UtilMethods.appendLeadingZeros(returnValue, columnValue,originalColumnLength, cd.getDbColumnName());
			else
				UtilMethods.appendTrailingSpaces(returnValue, columnValue, originalColumnLength, cd.getDbColumnName());
			
			if (cd.isKey())
				oldKey.append(returnValue);
			else	
				oldData.append(returnValue);
			
			if (cd.getDbColumnName().equals("PRODUCT_PREFIX"))
				pWipRow.setProductPrefix(columnValue);
			
			if (cd.getDbColumnName().equals("TABLE_PTR_SUBSET"))
				pWipRow.setSubsetName(columnValue);
				
			continue;
		}
		
		if (event.isEndElement()){
			EndElement endElement = event.asEndElement();
			if (endElement.getName().getLocalPart().equals("old_key")) 
				break;
		}
	}
	
	pWipRow.setOldConcatKey(oldKey.toString());
	pWipRow.setOldData(oldData.toString());
}
	// end ENH1063-05
}
