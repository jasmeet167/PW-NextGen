package com.csc.fsg.life.pw.web.actions.importexport;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;

import com.csc.fsg.life.pw.common.rules.SpecialHandling;
import com.csc.fsg.life.pw.common.util.IOTokenizer;
import com.csc.fsg.life.pw.web.avm.*;
import com.csc.fsg.life.pw.web.io.*;

/* Modifications: T0103, T0091, CCCV-E364, ENH961, CCCV-E571,T0115, ENH990, T0129, WMA-1209 */
// T0103 - IOTokenizer moved to common
// CCCV-E364 - close buffered readers so import file deletes
// ENH961 - set status in task, throw InfoException

public final class ImportHelper {

	public static final int TABLE25 = 0;

	public static final int TABLE26 = 1;

	public static final int TABLE60 = 2;

	public static final int BUSINESS_RULE_VIEW = 3;

	public static final int ENTIRE_TABLE = 4;
	
	public static final String NEW_LINE = "\n";

	private String schema = null;

	private String company = null;

	private String productPrefix = null;

	private String table = null;

	private String subset = null;

	private String delim = null;

	private int colCount = -1;

	private Vector<String> columnNames = null;

	private Vector<Vector<String>> errorVector = null;

	private String errorMessage = null;

	private int target = -1;

	private int wizardTarget = -1;
	
	private String userId = null;
	
	private int totalValidatedRows = 0;
	
	private boolean isCommonTable = false;

	public ImportHelper() {
	}

	public void setProductPrefix(String prefix) {
		productPrefix = prefix;
	}

	public void setImportCompany(String company) {
		this.company = company;
	}

	public void setImportTarget(int target) {
		this.target = target;
	}
	public void setWizardImportTarget(int target) {
		this.wizardTarget = target;
	}

	public void setTableName(String tableName) {
//		table = (tableName.trim()).toUpperCase();
//		isCommonTable = SpecialHandling.getInstance().isCommonTable(table);
//		String tableId = WIPFilterMaker.getTableId(table);
//		TableDescriptor _td = EnvironmentManager.getInstance()
//			.getEnvironment(schema).getTableDescMgr()
//				.getTableDescriptor(tableId);
//		columnNames = new Vector<String>();
//		colCount = _td.getNumberOfColumns();
//
//		Vector<ColumnDescriptor> descriptors = _td.getColumnDescriptors();
//		for (int i = 0; i < descriptors.size(); i++) {
//			ColumnDescriptor cDescriptor = descriptors
//					.elementAt(i);
//			String columnName = ((cDescriptor.getDbColumnName()).toUpperCase())
//					.trim();
//			columnNames.addElement(columnName);
//		}
	}


	public void setDelimiter(String delim) {
		this.delim = delim;
	}

	public void setSubsetName(String subsetName) {
		subset = subsetName;
	}

	private void setErrorVector(Vector<Vector<String>> errors) {
		errorVector = errors;
	}

	public void setImportSource(String env) {
		schema = env;
	}

	public Vector<Vector<String>> getErrorVector() {
		return errorVector;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	private void readImportedData(StringBuffer inputBuffer,File fileObject) throws Exception {
		int noOfColumns = -1;
		BufferedReader br = null;
		try {

			br = new BufferedReader(new FileReader(fileObject));

			switch (target) {
				case TABLE25:
					initTable25Buffer(br, inputBuffer);
					break;
				case TABLE60:
					initTable60Buffer(br, inputBuffer);
					break;
				case TABLE26:
					initTable26Buffer(br, inputBuffer);
					break;
				default:
					break;
			}
		} finally {
			if (br != null)
				br.close();
		}
	}

	public boolean verifyData(StringBuffer wizardBuffer,File fileObject
			) throws Exception {

		BufferedReader importedData = null;
		boolean ret = false;
		
		try {			
			long fileLength = fileObject.length();
			long bytesRead = 0;

			if (target == ENTIRE_TABLE || target == BUSINESS_RULE_VIEW) {
				importedData = new BufferedReader(new FileReader(fileObject));
			} else {
				readImportedData(wizardBuffer,fileObject);
				if (wizardBuffer.toString().trim().length() == 0)
					throw new InfoException("IMPORT FILE IS EMPTY");
				importedData = new BufferedReader(new StringReader(wizardBuffer.toString()));
			}
	
			String firstLine = importedData.readLine();
			if ( firstLine == null )
				throw new InfoException("Import file is empty");
			bytesRead += firstLine.length() + 1;
	
			if (firstLine.indexOf(delim) == -1) {
				throw new InfoException("File structure mismatch. Delimiter not found.");
			}
	
			IOTokenizer sp = new IOTokenizer(firstLine, delim);
			int tokens = sp.countTokens();
	
			if (tokens != colCount)
				throw new InfoException("Column number mismatch.  Expected "
						+ colCount + " columns but found " + tokens + " columns.");
	
			for (int i = 0; i < tokens; i++) {
				String token = sp.nextToken();
				String columnName = columnNames.elementAt(i);
				if (!(token.equalsIgnoreCase(columnName))) {
					throw new InfoException("COLUMN NAMES MISMATCH," + token
							+ " column doesn't exist in the table " + table);
				}
			}
	
			Vector<Vector<String>> errors = new Vector<Vector<String>>();
			Hashtable<String, Hashtable<String, Object>> avValues = getAVHashTableForAllColumns();
			int row = 0;
			String line = null;
	
			while ((line = importedData.readLine()) != null) {
				// CCCV-E571 - handle blank rows
				if ( line.trim().length() == 0 )
					continue;
				
				row++;
				sp = new IOTokenizer(line, delim);
				int tokenCount = sp.countTokens();
	
				if (tokenCount != colCount)
					throw new InfoException("Mismatch on number of columns on line " + row);
	
				for (int i = 0; i < tokenCount; i++) {
					String value = sp.nextToken();
	
					String columnName = columnNames.elementAt(i);
					Hashtable<String, Object> columnMetaData = avValues.get(columnName);
	
					if (!isProperCompany(columnName, value))
						writeToErrorLog(errors, columnName, value, company, row);
	
					/**WMABASEIXI-2577- Do not check subsets fro common tables */ 
					boolean checkSubsets = ( target != ENTIRE_TABLE  && wizardTarget!=ENTIRE_TABLE &&!isCommonTable );
					
					if (checkSubsets && !isProperSubset(columnName, value))
						writeToErrorLog(errors, columnName, value, subset, row);
	
					checkTokenValidity(columnName, value, columnMetaData, errors, row);
				}

				bytesRead += line.length() + 1;  // +2?
				int percentDone = 2 + (int)((float)bytesRead / (float)fileLength * 23.0);
//				if ( (row % 1000) == 0 && percentDone > task.getTaskBean().getPercentDone() )
//					task.setStatus(percentDone, "Validated " + row + " row(s)");
			}
			totalValidatedRows = row;
//			if ( 25 > task.getTaskBean().getPercentDone() )
//				task.setStatus(25, "Validated " + row + " row(s)");
	
			if (errors.size() != 0) {
				setErrorVector(errors);
			} else {	
				avValues = null;
				ret = true;
			}
		} catch (InfoException e) {
			errorMessage = e.getMessage();
		} catch (Exception e) {
			StringWriter sw = new StringWriter();
			e.printStackTrace(new PrintWriter(sw));
			errorMessage = sw.toString();
			sw.close();
			
		} finally {
			if ( importedData != null )
				importedData.close();
		}
		return ret;
	}

	private Hashtable<String, Hashtable<String, Object>> getAVHashTableForAllColumns() throws Exception {

		if (productPrefix == null) {
			SpecialHandling sh = SpecialHandling.getInstance();
			if (sh.isPlanRelatedCombinedTable(table)) {
				productPrefix = sh.getProductPrefix(table);
			}
		}

		AVKey avKey = new AVKey(schema, company, productPrefix, table, subset,
				null, null);

		Hashtable<String, Hashtable<String, Object>> columnValues = new Hashtable<String, Hashtable<String, Object>>();
//		String tableId = WIPFilterMaker.getTableId(table);
//	
//		TableDescriptor td = EnvironmentManager.getInstance()
//						.getEnvironment(schema).getTableDescMgr().getTableDescriptor(tableId);
//		Vector<ColumnDescriptor> colDesc = td.getColumnDescriptors();
//		Connection conn = null;
//		try{
//
//			conn = DBConnMgr.getInstance().getConnection(schema,DBConnMgr.BUSINESS_RULES);
//			for (int i = 0; i < colDesc.size(); i++) {
//				ColumnDescriptor cd = colDesc.get(i);
//				int attribute = cd.getMetaData().getAttribute(productPrefix);
//	
//				String isRequired = "N";
//				String isHidden = "N";
//	
//				if (attribute == 0)
//					isRequired = "Y";
//	
//				if (attribute == 2)
//					isHidden = "Y";
//	
//				Hashtable<String,String> range = new Hashtable<String,String> ();
//				Hashtable<String,String>  list = new Hashtable<String,String> ();
//	
//				avKey.setFieldName(cd.getDbColumnName());
//				avKey.setKey(cd.isKey());
//	
//				// WMA-1209 - ETV needs AVs to see if blank allowed in required field
//				//if (target != ENTIRE_TABLE) {
//					Hashtable<String, String> av = cd.getAllowableValues(avKey, new PlanTO(),conn);
//					if (ColumnDescriptor.isAVList(av))
//						list = av;
//					else
//						range = av;
//				//}
//	
//				Hashtable<String, Object> eachColInfo = new Hashtable<String, Object>();
//				eachColInfo.put("DATA_TYPE", cd.getMetaData().getSQLDataType());
//				eachColInfo.put("RANGE", range);
//				eachColInfo.put("LIST", list);
//				eachColInfo.put("HIDDEN", isHidden);
//				eachColInfo.put("REQUIRED", isRequired);
//				eachColInfo.put("LENGTH", cd.getMetaData().getColumnSize() + "");
//				eachColInfo
//						.put("DB_DATA_TYPE", cd.getMetaData().getDataType() + "");
//				eachColInfo.put("DECIMAL_POSITIONS", cd.getMetaData()
//						.getDecimalDigits()
//						+ "");
//				columnValues.put(cd.getMetaData().getDbColumnName(), eachColInfo);
//			}
//		}finally{
//			DBConnMgr.getInstance().releaseConnection(conn);
//		}

		return columnValues;
	}

	private String getStringOfAVs(Hashtable avTable, String columnName) {

		StringBuffer sb = new StringBuffer();
		String delim = "";
		Enumeration e = null;

		String dataType = (String) avTable.get("DATA_TYPE");

		if (hasRange(dataType, avTable)) {
			Hashtable range = (Hashtable) avTable.get("RANGE");

			e = range.elements();
			delim = ",";
			if (e.hasMoreElements()) {
				StringTokenizer stMin = new StringTokenizer(""
						+ e.nextElement(), "|");
				StringTokenizer stMax = new StringTokenizer(""
						+ e.nextElement(), "|");

				while (stMin.hasMoreTokens()) {
					sb.append(delim).append(stMin.nextToken())
						.append("-").append(stMax.nextToken());
				}
			}
		} else {
			Hashtable list = (Hashtable) avTable.get("LIST");

			e = list.keys();
			delim = ",";
			for (; e.hasMoreElements();) {
				sb.append(delim).append(e.nextElement());
			}
		}

		String avString = sb.toString();
		if (avString.length() > 1) {
			avString = avString.substring(1);
		}

		return avString;
	}

	private void checkTokenValidity(String columnName, String value,
			Hashtable<String, Object> colMetaData, Vector<Vector<String>> errors, int rowIndex) {

		String isRequired = (String) colMetaData.get("REQUIRED");
		String dbDataType = (String) colMetaData.get("DB_DATA_TYPE");
		boolean isChar = dbDataType.equals("1");

		int fieldLength = Integer.parseInt((String) colMetaData.get("LENGTH"));

		if (!isChar) {

			if ((value.indexOf(".") != -1) || (value.indexOf("-") != -1))
				fieldLength = fieldLength + 1;
		}

		boolean allowEmptyVal = containsEmptyValue((Hashtable) colMetaData
				.get("LIST"));

		String errorMessage = null;

		if (isRequired.equals("Y") && value.trim().length() == 0) {
			if (allowEmptyVal)
				return;
			else
				errorMessage = "Required Field, Cannot contain Blank space";
		}

		if (isIntegerField(dbDataType) && !isValidInteger(value))
			errorMessage = "Field requires a valid integer";

		else if (isDoubleField(dbDataType)) {
			if (!isValidDouble(value)) {
				errorMessage = "Field requires a valid double";
			} else {

				int length_db = Integer.parseInt((String) colMetaData
						.get("LENGTH"));
				int afterDecimal_db = Integer.parseInt((String) colMetaData
						.get("DECIMAL_POSITIONS"));
				int beforeDecimal_db = length_db - afterDecimal_db;

				int decimalIndex = value.indexOf(".");
				String beforeDecimalVal = "";
				String afterDecimalVal = "";

				if (decimalIndex != -1) {
					beforeDecimalVal = value.substring(0, decimalIndex);
					afterDecimalVal = value.substring(decimalIndex + 1);
				} else {
					beforeDecimalVal = value;
				}

				// remove sign
				if (value.startsWith("+") || value.startsWith("-"))
					beforeDecimalVal = beforeDecimalVal.substring(1);

				// remove leading zeros
				char[] chars = beforeDecimalVal.toCharArray();
				int index = 0;
				for (; index < beforeDecimalVal.length(); index++) {
					if (chars[index] != '0')
						break;
				}
				beforeDecimalVal = (index == 0) ? beforeDecimalVal
						: beforeDecimalVal.substring(index);

				beforeDecimalVal = beforeDecimalVal.trim();
				afterDecimalVal = afterDecimalVal.trim();

				if (beforeDecimalVal.length() > beforeDecimal_db
						|| afterDecimalVal.length() > afterDecimal_db) {
					errorMessage = "Over Size decimal:Max size for this Field "
							+ fieldLength + " ( " + beforeDecimal_db + "."
							+ afterDecimal_db + " )";
				}

			}
		}

		else if (isDateField(columnName, colMetaData) && !isValidDate(value))
			errorMessage = "Accepted Formats are 'YYYY-MM-DD', 'MM-DD-YYYY','MM/DD/YYYY','YYYYMMDD'";

		else if (value.length() > fieldLength) {
			String zeroTrimmedTkn = removeLeadingZeros(value);
			if (zeroTrimmedTkn.length() > fieldLength)
				errorMessage = "Over Size:Max size for this Field "
						+ fieldLength;
		}

		if (errorMessage != null) {
			writeToErrorLog(errors, columnName, value, errorMessage, rowIndex);
			return;
		}

		if (target != ENTIRE_TABLE && wizardTarget!=ENTIRE_TABLE)
			performAVCheck(colMetaData, columnName, value, errors, rowIndex);
	}

	private String removeLeadingZeros(String str) {
		if (str == null) {
			return null;
		}
		char[] chars = str.toCharArray();
		int index = 0;
		for (; index < str.length(); index++) {
			if (chars[index] != '0') {
				break;
			}
		}
		return (index == 0) ? str : str.substring(index);
	}

	private void performAVCheck(Hashtable avForThisColumn,
			String currentColName, String currentToken, Vector<Vector<String>> errors,
			int rowIndex) {

		String dataType = (String) avForThisColumn.get("DATA_TYPE");
		Hashtable allowableValues = null;

		if (hasList(dataType)) {
			allowableValues = (Hashtable) avForThisColumn.get("LIST");
		} else if (hasRange(dataType)) {
			allowableValues = (Hashtable) avForThisColumn.get("RANGE");
		}
		if (allowableValues == null || allowableValues.isEmpty())
			return;

		if (currentToken.length() == 0)
			currentToken = " ";

		if (hasList(dataType) && !allowableValues.containsKey(currentToken)) {
			String av = getStringOfAVs(avForThisColumn, currentColName);
			writeToErrorLog(errors, currentColName, currentToken, av, rowIndex);
		} else if (hasRange(dataType))
			checkRange(currentToken, allowableValues, errors, currentColName,
					rowIndex);
	}

	private void writeToErrorLog(Vector<Vector<String>> errors, String columnName,
			String value, String errorMessage, int rowIndex) {

		Vector<String> errData = new Vector<String>(0);
		errData.addElement("" + rowIndex);
		errData.addElement(columnName);
		errData.addElement(value);
		errData.addElement(errorMessage);
		errors.addElement(errData);
	}

	private void checkRange(String token, Hashtable hash, Vector<Vector<String>> errors,
			String columnName, int rowIndex) {

		if (token.trim().length() == 0) {
			writeToErrorLog(errors, columnName, token,
					"Error:NUMERIC FIELD CANNOT BE A BLANK SPACE", rowIndex);
			return;
		}

		String min = (String) hash.get(AVManager.MIN_VALUES);
		String max = (String) hash.get(AVManager.MAX_VALUES);

		if ((min == null) || (max == null)) {
			writeToErrorLog(errors, columnName, token,
					"Error:COULD NOT GET ALLOWABLE VALUES", rowIndex);
			return;
		}

		double tokenValue;
		try {
			tokenValue = Double.parseDouble(token);
		} catch (Exception ex) {
			writeToErrorLog(errors, columnName, token,
					"Error:THIS FIELD REQUIRES A NUMERIC VALUE", rowIndex);
			return;
		}

		double[][] actualRange = parseForMultipleRanges(min, max);
		double minValue, maxValue;
		boolean inRange = false;
		for (double[] element : actualRange) {
			minValue = element[0];
			maxValue = element[1];
			if ((tokenValue >= minValue) && (tokenValue <= maxValue))
				inRange = true;
		}

		if (!inRange)
			writeToErrorLog(errors, columnName, token,
					"Value not in allowed range", rowIndex);
	}

	private boolean hasRange(String columnName, Hashtable avForThisColumn) {

		String dataType = (String) avForThisColumn.get("DATA_TYPE");

		if (dataType.equalsIgnoreCase("BIGINT")
				|| dataType.equalsIgnoreCase("DOUBLE")
				|| dataType.equalsIgnoreCase("INTEGER")) {
			return true;
		}
		return false;

	}

	private boolean hasRange(String dataType) {

		if (dataType.equalsIgnoreCase("BIGINT")
				|| dataType.equalsIgnoreCase("DOUBLE")
				|| dataType.equalsIgnoreCase("INTEGER")) {
			return true;
		}
		return false;
	}

	private boolean hasList(String dataType) {

		if (dataType.equalsIgnoreCase("CHAR")) {
			return true;
		}
		return false;
	}

	private boolean isProperSubset(String columnName, String token) {
		if (!columnName.equalsIgnoreCase("TABLE_SUBSET"))
			return true;

		if (token == null)
			return false;

		if (token.trim().equalsIgnoreCase(subset.trim()))
			return true;
		return false;
	}

	private boolean isProperCompany(String columnName, String token) {
		if (!columnName.equalsIgnoreCase("COMPANY_CODE"))
			return true;

		if (token == null)
			return false;

		if (token.trim().equalsIgnoreCase(company.trim()))
			return true;

		return false;
	}

	private boolean isDateField(String columnName, Hashtable avForThisColumn) {

		String dataType = (String) avForThisColumn.get("DATA_TYPE");

		if (dataType.equalsIgnoreCase("DATE")) {
			return true;
		}
		return false;
	}

	private boolean isValidDate(String token) {

		if (token.equals("9999-99-99")) {
			return true;
		}
		try {
			parseDate(token);
		} catch (Exception ex) {
			return false;
		}
		return true;
	}

	private void initTable60Buffer(BufferedReader br, StringBuffer table60Buffer)
			throws Exception {

	    String columnHeader = br.readLine();
	    IOTokenizer st = new IOTokenizer(columnHeader, delim);
	
	    int tokens = st.countTokens();
	    ArrayList<String> requiredColsIndex = new ArrayList<String>();
	  	int prodIndex = -1;
		int lastCol = -1;
        for (int i = 0; i < tokens; i++) 
        {
        	String columnName = st.nextToken().trim();
        	if (columnName.equals("GUAR_INTEREST_RATE") || columnName.equals("MAX_POLICY_YEAR") || columnName.equals("ISSUE_YEAR")||
        	        columnName.equals("STAT_INTEREST_RATE") || columnName.equals("GAAP_INTEREST_RATE") || columnName.equals("TAX_INTEREST_RATE")){
        	    continue;
        	}else if (columnNames.contains(columnName)) {
        	    table60Buffer.append(columnName);
        	    table60Buffer.append(delim);
        		requiredColsIndex.add(i+"");
        	}else if (columnName.equalsIgnoreCase("PRODUCT_CODE")){
        	    table60Buffer.append("PRODUCT_PREFIX");
        	    table60Buffer.append(delim);
        		prodIndex = i;
        	    requiredColsIndex.add(i+"");
           	} else if (columnName.equalsIgnoreCase("NEW_GUAR_INT_RATE")) {
           	    table60Buffer.append("GUAR_INTEREST_RATE");
        		requiredColsIndex.add(i+"");
        		table60Buffer.append(delim);
           	}else if (columnName.equalsIgnoreCase("MAX_POLICY_DURATION")) {
           	    table60Buffer.append("MAX_POLICY_YEAR");
        		requiredColsIndex.add(i+"");
        		table60Buffer.append(delim);
       	   	}else if (columnName.equalsIgnoreCase("NEW_ISSUE_YEAR")) {
           	    table60Buffer.append("ISSUE_YEAR");
        		requiredColsIndex.add(i+"");
        		table60Buffer.append(delim);
       	   	}else if (columnName.equalsIgnoreCase("NEW_STAT_INT_RATE")) {
           	    table60Buffer.append("STAT_INTEREST_RATE");
        		requiredColsIndex.add(i+"");
        		table60Buffer.append(delim);
           	}else if (columnName.equalsIgnoreCase("NEW_GAAP_INT_RATE")) {
           	    table60Buffer.append("GAAP_INTEREST_RATE");
        		requiredColsIndex.add(i+"");
        		table60Buffer.append(delim);
       	   	}else if (columnName.equalsIgnoreCase("NEW_TAX_INT_RATE")) {
           	    table60Buffer.append("TAX_INTEREST_RATE");
        		requiredColsIndex.add(i+"");
        		lastCol = i;
		   	}
           	
        }
        table60Buffer.append(NEW_LINE);
        String dataLine = null;
		
		while ((dataLine = br.readLine()) != null){
		    st = new IOTokenizer(dataLine, delim);
		    tokens = st.countTokens();
		    for (int i = 0; i < tokens; i++) {
		        String data = st.nextToken().trim();
		         if (prodIndex==i){
		             table60Buffer.append(data.substring(0,1));
		             table60Buffer.append(delim);
		        }else if (requiredColsIndex.contains(i+"")){
		            table60Buffer.append(data);
		            if (lastCol!=i)
		                table60Buffer.append(delim); 
		        }
		    }
		    table60Buffer.append(NEW_LINE);
		}
		//System.out.println(table60Buffer.toString());
      
	}

//	private String convertOutputDateToDB(String outDate) {
//		return outDate.substring(0, 4) + "-" + outDate.substring(4, 6) + "-"
//				+ outDate.substring(6);
//	}
	
	private void initTable25Buffer(BufferedReader br, StringBuffer payoutRatesBuffer) throws Exception {
		String line = null;
		int effectiveDateIndex = -1;
		int renewalEffectiveDateIndex = -1;
		int interestRateIndex = -1;
		int renewalInterestRateIndex = -1;
	
		while ((line = br.readLine()) != null){
			if (line.trim().length()==0)
				continue;
		
			String[] tokens = line.split(delim);
			String firstToken = tokens[0];
			 
			if (firstToken.trim().equals("Plan Key") || firstToken.trim().startsWith("TW24X Info")
					|| firstToken.trim().startsWith("T024X Info") || firstToken.trim().startsWith("T025X Info"))
				continue;
			
			if (firstToken.trim().equals("COMPANY_CODE")){
				for (int i = 0; i < tokens.length; i++) {
					String columnName = tokens[i];
					if (columnName.equals("INT_RT_EFF_DT"))
						effectiveDateIndex = i;
					if (columnName.equals("RENEWAL_EFFECTIVE_DATE"))
						renewalEffectiveDateIndex = i;
					if (columnName.equals("INTEREST_RATE"))
						interestRateIndex = i;
					if (columnName.equals("RENEWAL_INTEREST_RATE"))
						renewalInterestRateIndex = i;
					
					if (!columnName.equals("RENEWAL_EFFECTIVE_DATE") && !columnName.equals("RENEWAL_INTEREST_RATE")){
						payoutRatesBuffer.append(columnName);
						if (i!=(tokens.length-3))
							payoutRatesBuffer.append(delim);
					}
				}
				payoutRatesBuffer.append("\n");
			}else{
				
				tokens[interestRateIndex] = tokens[renewalInterestRateIndex]; //Interest Rate
				tokens[effectiveDateIndex] = tokens[renewalEffectiveDateIndex]; //Effective Date
				
				for (int i = 0; i < tokens.length-2; i++) {
					payoutRatesBuffer.append(tokens[i]);
					if (i!=(tokens.length-3))
						payoutRatesBuffer.append(delim);
				}
				payoutRatesBuffer.append("\n");
			}
		}
		
		//System.out.println(payoutRatesBuffer);
		
	}

	private void initTable26Buffer(BufferedReader br, StringBuffer table26Buffer)
			throws Exception {

	    String columnHeader = br.readLine();
	    IOTokenizer st = new IOTokenizer(columnHeader, delim);
	
	    int tokens = st.countTokens();
	    ArrayList<String> requiredColsIndex = new ArrayList<String>();
	  	int prodIndex = -1;
		int lastCol = -1;
        for (int i = 0; i < tokens; i++) 
        {
        	String columnName = st.nextToken().trim();
        	if (columnName.equals("GUAR_INT_RT")){
        	    continue;
        	}else if (columnNames.contains(columnName)) {
        		table26Buffer.append(columnName);
        		table26Buffer.append(delim);
        		requiredColsIndex.add(i+"");
        	}else if (columnName.equalsIgnoreCase("PRODUCT_CODE")){
        	    table26Buffer.append("PRODUCT_PREFIX");
        		table26Buffer.append(delim);
        		prodIndex = i;
        	    requiredColsIndex.add(i+"");
          	} else if (columnName.equalsIgnoreCase("NEW_GUAR_INT_RT")) {
        		table26Buffer.append("GUAR_INT_RT");
        		requiredColsIndex.add(i+"");
        		lastCol = i;
		   	}
        }
		table26Buffer.append(NEW_LINE);
		
		String dataLine = null;
		
		while ((dataLine = br.readLine()) != null){
		    st = new IOTokenizer(dataLine, delim);
		    tokens = st.countTokens();
		    for (int i = 0; i < tokens; i++) {
		        String data = st.nextToken().trim();
		         if (prodIndex==i){
		            table26Buffer.append(data.substring(0,1));
		           	table26Buffer.append(delim);
		        }else if (requiredColsIndex.contains(i+"")){
		            table26Buffer.append(data);
		            if (lastCol!=i)
		                table26Buffer.append(delim); 
		        }
		    }
		  table26Buffer.append(NEW_LINE);
		}

	}

	private double[][] parseForMultipleRanges(String min, String max) {

		StringTokenizer minTokenizer = new StringTokenizer(min, "|");
		StringTokenizer maxTokenizer = new StringTokenizer(max, "|");
		double[][] tokens = new double[minTokenizer.countTokens()][2];
		int i = 0;

		while (minTokenizer.hasMoreTokens()) {
			tokens[i][0] = Double.parseDouble(minTokenizer.nextToken());
			tokens[i][1] = Double.parseDouble(maxTokenizer.nextToken());
			i++;
		}
		return tokens;
	}

	private boolean isIntegerField(String dbDataType) {

		int dataType = -1;

		try {
			dataType = Integer.parseInt(dbDataType);
		} catch (Exception ex) {
			dataType = java.sql.Types.VARCHAR;
		}

		if ((dataType == java.sql.Types.BIGINT)
				|| (dataType == java.sql.Types.INTEGER)
				|| (dataType == java.sql.Types.SMALLINT)) {
			return true;
		}

		return false;
	}

	private boolean isDoubleField(String dbDataType) {

		int dataType = -1;

		try {
			dataType = Integer.parseInt(dbDataType);
		} catch (Exception ex) {
			dataType = java.sql.Types.VARCHAR;
		}

		if ((dataType == java.sql.Types.DECIMAL)
				|| (dataType == java.sql.Types.DOUBLE)) {
			return true;
		}

		return false;
	}

	private boolean isValidInteger(String token) {

		boolean isValid = false;

		try {
			Integer.parseInt(token);
			isValid = true;
		} catch (Exception ex) {
			isValid = false;
		}
		return isValid;
	}

	private boolean isValidDouble(String token) {

		boolean isValid = false;

		try {
			Double.parseDouble(token);
			isValid = true;
		} catch (Exception ex) {
			isValid = false;
		}
		return isValid;
	}

	public static Date parseDate(String value) throws Exception {
		
		SimpleDateFormat _format1 = new SimpleDateFormat("MM-dd-yyyy");
		SimpleDateFormat _format2 = new SimpleDateFormat("MM/dd/yyyy");
		SimpleDateFormat _format3 = new SimpleDateFormat("yyyyMMdd");
		SimpleDateFormat _format4 = new SimpleDateFormat("yyyy-MM-dd");

		_format1.setLenient(false);
		_format2.setLenient(false);
		_format3.setLenient(false);
		_format4.setLenient(false);

		if (value.indexOf("/") != -1) {
			return _format2.parse(value);
		} else if (value.indexOf("-") != -1) {
			int hIndex = value.indexOf("-");
			if (hIndex > 3)
				return _format4.parse(value);
			return _format1.parse(value);
		} else {
			return _format3.parse(value);
		}
	}

	private boolean containsEmptyValue(Hashtable fieldHash) {
		if (fieldHash == null)
			return false;

		for (Enumeration e = fieldHash.keys(); e.hasMoreElements();) {
			String key = (String) e.nextElement();
			if ((key.trim()).length() == 0)
				return true;
		}

		return false;
	}

	// CCCV-E571 - pass file to RCMImportApply rather than reading into memory
//	public String directApply(File fileObject, PWTask task, int totalRows) throws Exception {
//		RCMImportApply rcmImport = new RCMImportApply(schema, fileObject);
//		rcmImport.setDelim(delim);
//		rcmImport.setTableId(WIPFilterMaker.getTableId(table));
//		rcmImport.setCompanyCode(company);
//		rcmImport.setProductPrefix(productPrefix);
//		rcmImport.setSubsetName(subset);
//		rcmImport.setUserId(userId);
//		return rcmImport.transfer(task, totalRows);
//	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public int getTotalValidatedRows() {
		return totalValidatedRows;
	}
}
