/*
 * THIS PROGRAM IS THE PROPERTY OF CSC FINANCIAL SERVICES GROUP. IT MAY NOT BE
 * COPIED IN WHOLE OR IN PART WITHOUT THE EXPRESS WRITTEN CONSENT OF CSC
 * FINANCIAL SERVICES GROUP.
 */

package com.csc.fsg.life.pw.io;

import java.sql.Connection;
import java.text.SimpleDateFormat;
import java.util.Vector;

import org.springframework.util.StringUtils;

import com.csc.fsg.life.pw.rules.SpecialHandling;
import com.csc.fsg.life.pw.util.Constants;
import com.csc.fsg.life.pw.controller.IResponse;
import com.csc.fsg.life.pw.environment.*;
import com.csc.fsg.life.pw.util.DBConnMgr;

/* Modifications: T0091, T0094 , HAVMENH, T0115, ENH961, WMABASEIXI-2722, WMA-459, ENH1063-05, WMA-1550 */
// ENH961 - use InfoException

/**
 * Class UtilMethods
 * 
 * @author CSC-FSG,E.Hartford
 * @version PW 2.0 , 09-24-2002
 */

public class UtilMethods {

	public static final int APPEND_SPACE = 0;

	public static final int APPEND_ZEROS = 1;

	public static final String SINGLE_SPACE = " ";

	public static final String SINGLE_ZERO = "0";

	public static final String POSITIVE_SIGN = "+";

	public static final String NEGATIVE_SIGN = "-";

	/**
	 * Method buildJoinSourceClause
	 * 
	 * @param schema
	 * @param dependsOnTable
	 * @return
	 */

	public static String buildJoinSourceClause(String schema,
			String dependsOnTable) {
		StringBuffer sb = new StringBuffer();
		sb.append(".");
		sb.append(dependsOnTable).append("\n");
		
		sb.append("JOIN ");
		sb.append(schema);
		sb.append(".");
		sb.append("T000X ");
		sb.append("on table_ptr_id = '");
		sb.append(dependsOnTable.substring(1, 4)); // extract tableId
		sb.append("' and  ");
		sb.append("table_ptr_subset = table_subset ").append("\n");
		
		return sb.toString();
	}

	public static void writeDescriptorsToStream(TableDescriptor td,
			int userAuthority, SourceTableFilter filter, IResponse out,
			boolean nextIndicator, boolean prevIndicator, String pagingMode,
			String firstKey, String lastKey, boolean locator) throws Exception {

		

			out.print(pagingMode);
			out.print(Constants.STAB);
			if (locator)
				out.print(Constants.LOCATE);
			else
				out.print(Constants.NO_LOCATE);
			out.print(Constants.STAB);
			out.print(firstKey);
			out.print(Constants.STAB);
			out.print(lastKey);
			out.print(Constants.STAB);

			if (nextIndicator
					&& !SpecialHandling.getInstance().isTableZeroOrXA(
							td.getDDLName()))
				out.print(Constants.PAGING_NEXT);
			else
				out.print(Constants.PAGING_NO_NEXT);
			out.print(Constants.STAB);
			if (prevIndicator)
				out.print(Constants.PAGING_PREV);
			else
				out.print(Constants.PAGING_NO_PREV);
			out.print(Constants.EOL);

			out.print(userAuthority + "");
			out.print(Constants.STAB);
			td.toStream(out);
			out.print(Constants.EOL);

			ColumnDescriptor descriptor = null;
			Connection conn = null;
			try{

				conn = DBConnMgr.getInstance().getConnection(filter.getEnvSchema(),DBConnMgr.BUSINESS_RULES);
				for (int i = 1; i <= td.getNumberOfColumns(); i++) {
					descriptor = td.getColumn(i);
					descriptor.toStream(out, filter, null,conn);
					out.print(Constants.EOL);
				}
			}finally{
				DBConnMgr.getInstance().releaseConnection(conn);
			}
		
	}

	/**
	 * Method writeRowDataToStream
	 * 
	 * @param td
	 * @param row
	 * @param out
	 * @param schemaName
	 */

	public static void writeRowDataToStream(TableDescriptor td, Row row,
			IResponse out, String schemaName) {

		out.print("0");
		out.print(Constants.STAB);
		ColumnDescriptor descriptor = null;

		for (int i = 1; i <= td.getNumberOfColumns(); i++) {
			descriptor = td.getColumn(i);
			out.print(WizUtils.pwTrim(descriptor.getValue(row).toString()));
			out.print(Constants.STAB);
		}

		out.print(buildWithDelim(row, schemaName, true));
		out.print(Constants.STAB);
		out.print(buildWithDelim(row, schemaName, false));
		out.print(Constants.EOL);

	}

	/**
	 * Method writeWipDataToStream
	 * 
	 * @param wipRow
	 * @param out
	 */

	public static void writeWipDataToStream(WIPRow wipRow, IResponse out) {

		String operation = wipRow.getOperation();

		if (operation.equalsIgnoreCase(Constants.INSERT_OPERATION)) {
			out.print(Constants.TABLE_ATTR_HAS_AUDIT_INFO
					| Constants.TABLE_ATTR_TARGET_INSERT);
		} else if (operation.equalsIgnoreCase(Constants.UPDATE_OPERATION)) {
			out.print(Constants.TABLE_ATTR_HAS_AUDIT_INFO
					| Constants.TABLE_ATTR_TARGET_UPDATE);
		} else {
			out.print(Constants.TABLE_ATTR_HAS_AUDIT_INFO
					| Constants.TABLE_ATTR_TARGET_DELETE);
		}

		out.print(Constants.STAB);
		// out.print(wipRow.getNewData());
		out.print(getDataWithDelims(wipRow)); // Tab is added at the end while
		// returning from the method
		// out.print(Constants.STAB);
		out.print(wipRow.getOldConcatKey());
		out.print(Constants.STAB);
		out.print(wipRow.getOldData());
		out.print(Constants.STAB);
		out.print(wipRow.getChangeUserId());
		out.print(Constants.STAB);
		out.print(wipRow.getTimestamp());
		out.print(Constants.STAB);
		out.print(wipRow.getProjectName());
		out.print(Constants.EOL);
	}

	/**
	 * This method forms the table descriptor portion of the stream for the RCM
	 * process. Moved from the obsolete ViewAssistent class 11/2001.
	 * 
	 * @param schemaName
	 * @param tableId
	 * @param authority
	 * @param productCode
	 * @return
	 * @throws WIPRowsException
	 */

	public static String getTableMetaData(String envId, String tableId,
			int authority, String productCode) throws WIPRowsException {

		
		TableDescriptor td = EnvironmentManager.getInstance().getEnvironment(envId)
							.getTableDescMgr().getTableDescriptor(tableId);

		if (td == null) {
			throw new WIPRowsException(
					" Table Descriptor not found for tableId " + tableId);
		}

		StringBuffer sb = new StringBuffer();

		sb.append("0").append("\t").append(td.getNumberOfColumns())
				.append("\t").append(td.getTableName()).append("\t");
		sb.append(tableId).append("\n").append(
				td.getMetaData(envId, authority, productCode));

		return sb.toString();
	}
	
	public static void appendTrailingSpaces(StringBuffer finalValue,
			String data, int length) throws Exception {
		appendTrailingSpaces(finalValue, data, length, null);
	}

	public static void appendTrailingSpaces(StringBuffer finalValue,
			String data, int length, String columnName) throws Exception {

		if (length == -1) {
			throw new Exception("Invalid length paramater");
		}

		int initialLength = data.length();

		finalValue.append(data);

		if (initialLength == length) {
			return;
		}
		if (initialLength > length) {
			String columnDisplay = "";
			if ( columnName != null && !columnName.isEmpty() ) {
				columnDisplay = "(" + columnName + ") ";
			}
			throw new Exception("Value (" + data
					+ ") does not fit in the column " + columnDisplay + "of length " + length);
		}

		for (int i = 0; i < (length - initialLength); i++) {
			finalValue.append(SINGLE_SPACE);
		}
	}

	/**
	 * Method appendLeadingZeros
	 * 
	 * @param finalValue
	 * @param data
	 * @param length
	 * @throws Exception
	 */

	public static void appendLeadingZeros(StringBuffer finalValue, String data,
			int length) throws Exception {
		appendLeadingZeros(finalValue, data, length, null);
	}
	
	public static void appendLeadingZeros(StringBuffer finalValue, String data,
			int length, String columnName) throws Exception {

		if (length == -1) {
			throw new Exception("Invalid length paramater");
		}

		int decimalIndex = data.indexOf(".");

		// Remove Decimal if exists
		if (decimalIndex != -1) {
			data = data.substring(0, decimalIndex)
					+ data.substring(decimalIndex + 1);
		}

		boolean containsNegSign = data.indexOf("-") != -1;
		boolean containsPosSign = data.indexOf("+") != -1;

		// Remove sign if exists
		if (containsNegSign || containsPosSign) {
			data = data.substring(1);
		}

		int initialLength = data.length();

		if (initialLength > length) {
			if (decimalIndex != -1) {
				// WMA-459
				if ( (initialLength == (length+1)) && (decimalIndex == 1) && data.startsWith("0") ) {
					data = data.substring(1); // remove leading zero where length == scale
				} else {
					data = data.substring(0, length);
				}
			} else {
				String columnDisplay = "";
				if ( columnName != null && !columnName.isEmpty() ) {
					columnDisplay = "(" + columnName + ") ";
				}
				throw new Exception("Value (" + data
						+ ") does not fit in the column " + columnDisplay + "of length " + length);
			}
		}

		// append sign
		if (containsNegSign) {
			finalValue.append(NEGATIVE_SIGN);
		} else {
			finalValue.append(POSITIVE_SIGN);
		}

		// append leading zeroes if the length is less
		if (initialLength < length) {
			for (int i = 0; i < (length - initialLength); i++) {
				finalValue.append(SINGLE_ZERO);
			}
		}
		// append data
		finalValue.append(data);
	}

	/**
	 * Method getAppendType
	 * 
	 * @param dataType
	 * @return
	 */

	public static int getAppendType(int dataType) {

		int type = -1;

		switch (dataType) {

			case java.sql.Types.VARCHAR:
			case java.sql.Types.CHAR:
			case java.sql.Types.DATE:
				type = APPEND_SPACE;
				break;
			case java.sql.Types.BIGINT:
			case java.sql.Types.INTEGER:
			case java.sql.Types.SMALLINT:
			case java.sql.Types.DOUBLE:
			case java.sql.Types.DECIMAL:
				type = APPEND_ZEROS;
				break;
			default:
				type = APPEND_SPACE;
				break;
		}
		return type;
	}

	/**
	 * Method getDataWithDelims
	 * 
	 * @param wipRow
	 * @return
	 */

	public static String getDataWithDelims(WIPRow wipRow) {
		return getDataWithDelims(wipRow, null, true);
	}

	/**
	 * Method getDataWithDelims
	 * 
	 * @param wipRow
	 * @param inputData
	 * @param doTrim
	 * @return
	 */

	public static String getDataWithDelims(WIPRow wipRow, String inputData,
			boolean doTrim) {

		StringBuffer toSend = new StringBuffer();
		int startValue = 0;
		int endValue = 0;
		int columnLength = 0;
		String columnValue = null;
		
		String envID = wipRow.getEnvironment();
		Environment environment = EnvironmentManager.getInstance().getEnvironment(envID);
		TableDescriptor _tableDescriptor = environment.getTableDescMgr()
				.getTableDescriptor(wipRow.getTableId());
		Vector<ColumnDescriptor> columndescriptors = _tableDescriptor.getColumnDescriptors();
		int columnCount = _tableDescriptor.getNumberOfColumns();

		if (inputData == null) {
			String data = wipRow.getNewData();
			if (wipRow instanceof PlanWIPRow || wipRow instanceof IndexWIPRow)
				columnValue = data; // key = value
			else
				columnValue = wipRow.getKeyAndDataConcatenated(true);
		} else {
			columnValue = inputData;
		}

		for (int count = 0; count < columnCount; count++) {
			ColumnDescriptor cd = columndescriptors
					.get(count);

			columnLength = cd.getColumnSize();
			endValue = startValue + columnLength;

			boolean isNumeric = false;

			// spr 2241
			String dataToSend = "";

			// spr 2241
			if (!(endValue > columnValue.length())) {
				isNumeric = isNumeric(cd.getDataType());
				if (isNumeric) {

					if ((columnValue.substring(startValue, endValue).indexOf(
							"+") != -1)
							|| (columnValue.substring(startValue, endValue)
									.indexOf("-") != -1)) {
						endValue = endValue + 1;
					}
				}
				dataToSend = columnValue.substring(startValue, endValue);
			} // end spr 2241

			if (isNumeric) {
				if (dataToSend.startsWith("+")) {
					dataToSend = dataToSend.substring(1);
				}
				if (dataToSend.indexOf(".") == -1) {
					dataToSend = placeDecimal(dataToSend, cd
							.getDecimalDigits());
				} else {
					dataToSend = new Integer(dataToSend).toString();
				}
			}

			if (doTrim) {
				toSend.append(WizUtils.pwTrim(dataToSend));
			} else {
				toSend.append(dataToSend);
			}

			toSend.append(Constants.STAB);
			startValue = endValue;
		}
		return toSend.toString();

	}

	// method needs to be removed later

	/**
	 * Method getAuditDataWithDelims
	 * 
	 * @param wipRow
	 * @param inputData
	 * @param doTrim
	 * @return
	 */

	public static String getAuditDataWithDelims(WIPRow wipRow,
	        String inputData, boolean doTrim) throws Exception {

		StringBuffer toSend = new StringBuffer();
		int startValue = 0;
		int endValue = 0;
		int columnLength = 0;
		String columnValue = null;

		Environment environment = EnvironmentManager.getInstance().getEnvironment(wipRow.getEnvironment());
		TableDescriptor _tableDescriptor = environment.getTableDescMgr()
		        .getTableDescriptor(wipRow.getTableId());
		if ( _tableDescriptor == null )
			throw new InfoException("No table descriptor exists for table ID " + wipRow.getTableId());
		Vector<ColumnDescriptor> columndescriptors = _tableDescriptor.getColumnDescriptors();
		int columnCount = _tableDescriptor.getNumberOfColumns();
		// SPR-PH1-1450-Using row.getOldConcatKey().trim() in place of
		// row.getOldData.
		// if (inputData == null) {
		// String key = wipRow.getNewConcatKey();
		// String data = wipRow.getNewData();
		// //columnValue = wipRow.getNewData();
		// columnValue = key+data;
		// } else {
		columnValue = inputData;
		// }

		for (int count = 0; count < columnCount; count++) {
			ColumnDescriptor cd = columndescriptors
			        .get(count);

			columnLength = cd.getColumnSize();
			endValue = startValue + columnLength;

			boolean isNumeric = false;

			// spr 2241
			String dataToSend = "";

			// spr 2241
			if (!(endValue > columnValue.length())) {
				isNumeric = isNumeric(cd.getDataType());
				if (isNumeric) {

					if ((columnValue.substring(startValue, endValue).indexOf(
					        "+") != -1)
					        || (columnValue.substring(startValue, endValue)
					                .indexOf("-") != -1)) {
						endValue = endValue + 1;
					}
				}
				dataToSend = columnValue.substring(startValue, endValue);
			} // end spr 2241

			if (isNumeric) {
				try {
				
				if (dataToSend.startsWith("+")) {
					dataToSend = dataToSend.substring(1);
				}
				if (dataToSend.indexOf(".") == -1) {
					dataToSend = placeDecimal(dataToSend, cd
							.getDecimalDigits());
				} else {
					dataToSend = new Integer(dataToSend).toString();
				}
				
				} catch ( RuntimeException ex ) {
					throw new Exception("Bad data for table " + _tableDescriptor.getTableName() + ", column " + cd.getDbColumnName() + "<" + dataToSend + ">.");
				}
			}

			if (doTrim) {
				toSend.append(WizUtils.pwTrim(dataToSend));
			} else {
				toSend.append(dataToSend);
			}

			toSend.append(Constants.STAB);
			startValue = endValue;
		}
		return toSend.toString();

	}

	public static boolean isNumeric(int dataType) {

		if (getAppendType(dataType) == APPEND_ZEROS) {
			return true;
		}
		return false;
	}

	/**
	 * Method placeDecimal
	 * 
	 * @param data
	 * @param decimal
	 * @return
	 */

	public static String placeDecimal(String data, int decimal) {
		boolean negativeFlag = false; // CCC SPR#V-D145
		int startDecimal=0;
		
		if (decimal == 0) {
			return "" + (new Double(data).intValue()); // data;
		}
		if (data.startsWith("-")) {
			data = data.substring(1);
			negativeFlag = true;
		}
		int decimalPlace = data.length() - decimal;
		// if (decimalPlace==0)
		// return "0."+(new Double(data).intValue());

		if (decimalPlace == 0 && negativeFlag)
			return "-" + "0." + data;

		if (decimalPlace == 0)
			return "0." + data;
		
		while(data.substring(startDecimal, decimalPlace).startsWith("0")){
			if(Integer.parseInt(data.substring(0, decimalPlace))!=0 ){
				startDecimal++;
			}
			else{
				startDecimal=decimalPlace-1;
				break;
			}			
		}

		String afterDecimalData = data.substring(startDecimal, decimalPlace) + "."
				+ data.substring(decimalPlace);
		// String afterDecimalData = ""
		// + (new Double(data.substring(0, decimalPlace)).intValue())
		// + "." + data.substring(decimalPlace);
		if (negativeFlag)
			afterDecimalData = "-" + afterDecimalData;
		return afterDecimalData;
	}


	public static String buildWithDelim(Row row, String schemaName,
			boolean keyData) {

		String delim = "|";
		String columnValue = null;

		StringBuffer buffer = new StringBuffer();

		Vector<ColumnDescriptor> columndescriptors = row.getTableDescriptor()
				.getColumnDescriptors();
		int size = columndescriptors.size();
		int dataColumn = 0;

		for (int count = 0; count < size; count++) {

			ColumnDescriptor cd = columndescriptors
					.get(count);
			columnValue = cd.getValue(row).toString();

			if (isNumeric(cd.getDataType())) {
				if (columnValue.indexOf("+") != -1) {
					columnValue = columnValue.substring(1);
				}

				// if (columnValue.indexOf(".") == -1) {
				// columnValue = new Integer(columnValue).toString();
				// }
			}

			if (keyData) {
				if (cd.isKey()) {
					buffer.append(columnValue);
					buffer.append(delim);
				}
			} else {
				if (!cd.isKey()) {
					dataColumn++;
					buffer.append(columnValue);
					buffer.append(delim);
				}
			}
		}

		if (buffer.length() != 0) {
			buffer.deleteCharAt(buffer.length() - delim.length());
			if (dataColumn == 1) { // Check if there's single column data
				buffer.append("^");
			}
		}

		return buffer.toString();
	}

	// WMABASEIXI-2722 - added getSqlDate (includes yyyy-MM-dd)
	public static java.sql.Date getSqlDate(String date) {

		if (date == null) {
			return null;
		}
		java.sql.Date sqlDate = null;

		try {
			SimpleDateFormat sdf = null;

			if (date.indexOf("/") != -1) {
				sdf = new SimpleDateFormat("MM/dd/yyyy");
			} else if (date.indexOf("-") == 2) {
				sdf = new SimpleDateFormat("MM-dd-yyyy");
			} else if (date.indexOf("-") == 4) {
				sdf = new SimpleDateFormat("yyyy-MM-dd");
			} else {
				sdf = new SimpleDateFormat("yyyyMMdd");
			}
			java.util.Date tempDate = sdf.parse(date);

			sqlDate = new java.sql.Date(tempDate.getTime());
		} catch (Exception ex) {
		}
		return sqlDate;
	}

	/**
	 * Accepts a ORDER BY clause, and removes from it the column with name
	 * matching the value of <code>columnToRemove</code>, if it is found.
	 * <p>
	 * If the matching column was the only sort column, then a blank string is
	 * returned.
	 */
	public static String removeSortColumn(String orderBy, String columnToRemove)
	{
		if (!StringUtils.hasText(orderBy) 
		 || !StringUtils.hasText(columnToRemove))
			return orderBy;

		// Remove the columnToRemove
		String[] sortColumns = orderBy.split(",");
		StringBuilder buf = new StringBuilder();

		for (int i = 0, n = sortColumns.length; i < n; i++) {
			String sortColumn = sortColumns[i];
			int idx = sortColumn.toUpperCase().indexOf(columnToRemove.toUpperCase());
			if (idx >= 0)
				sortColumn = sortColumn.substring(0, idx);

			sortColumn = sortColumn.replaceAll("(?i)^\\s*order\\s+by\\s*", "");
			if (StringUtils.hasText(sortColumn)) {
				buf.append(sortColumn.trim());
				buf.append(", ");
			}
		}

		// Remove the trailing ',' character
		int lastCommaIdx = buf.lastIndexOf(",");
		if (lastCommaIdx >= 0)
			buf.setCharAt(lastCommaIdx, ' ');

		String output = buf.toString();
		if (StringUtils.hasText(output))
			output = " ORDER BY " + output;

		return output;
	}
}
