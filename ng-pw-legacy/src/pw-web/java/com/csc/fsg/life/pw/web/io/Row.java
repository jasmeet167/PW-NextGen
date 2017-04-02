package com.csc.fsg.life.pw.web.io;
/* Modifications: ENH761 */
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Vector;

import com.csc.fsg.life.common.util.CommonASCIIToEBCDICHelper;
import com.csc.fsg.life.exceptions.WrapperException;
import com.csc.fsg.life.pw.common.util.Utils;
import com.csc.fsg.life.pw.common.util.IOTokenizer;
import com.csc.fsg.life.pw.web.environment.*;
import com.csc.fsg.life.pw.web.actions.importexport.ImportHelper;
import com.csc.fsg.life.pw.web.utils.*;

/* Modifications: T0103, T0091, T0129, WMA-954, WMA-1182, WMA-1550, WMA-1773 */
// T0103 - IOTokenizer moved to common

public class Row extends WizObject {

	protected String envId = null;
	protected TableDescriptor _tableDescriptor;
	protected String _companyCode;
	protected String _key;

	protected String _oldConcatKey;
	protected String _oldData;

	private String _auditUserId;
	private String _auditDate;
	private String _auditProject;

	public String get_auditUserId() {
		return _auditUserId;
	}

	public String get_auditDate() {
		return _auditDate;
	}

	public String get_auditProject() {
		return _auditProject;
	}

	public void set_auditUserId(String auditUserId) {
		_auditUserId = auditUserId;
	}

	public void set_auditDate(String auditDate) {
		_auditDate = auditDate;
	}

	public void set_auditProject(String auditProject) {
		_auditProject = auditProject;
	}

	public String getCompanyCode() {
		return _companyCode;
	}

	public String getDDLName() {
		return _tableDescriptor.getDDLName();
	}

	public String getKey() {
		return _key;
	}

	public int getNumberOfColumns() {
		return _tableDescriptor.getNumberOfColumns();
	}

	public String getTableId() {
		return _tableDescriptor.getTableId();
	}

	public String getOldConcatKey() {
		return _oldConcatKey;
	}

	public String getOldData() {
		return _oldData;
	}

	public String getTableSubset() {
		return "BASECLASSNULLDATA";
	}

	public TableDescriptor getTableDescriptor() {
		return _tableDescriptor;
	}

	public String getProductPrefix() {
		return "BASECLASSNULLDATA";
	}

	public void setCompanyCode(String s) {
		_companyCode = s;
	}

	public void setKey(String s) {
		_key = s;
	}

	public void setTableDescriptor(TableDescriptor td) {
		_tableDescriptor = td;
	}

	public void setOldConcatKey(String s) {
		_oldConcatKey = s;
	}

	public void setOldData(String s) {
		_oldData = s;
	}
	
	public String getEnvId() {
		return envId;
	}
	
	public void setEnvId(String envId) {
		this.envId = envId;
	}

	
	private String buildConcatString(Vector<ColumnDescriptor> columnDescriptors) throws Exception {
		StringBuffer sb = new StringBuffer();

		for (int count = 0; count < columnDescriptors.size(); count++) {
			ColumnDescriptor cd = columnDescriptors.get(count);
			String columnValue = cd.getValue(this).toString();
			int originalColumnLength = cd.getColumnSize();
			int apendType = UtilMethods.getAppendType(cd.getDataType());

			StringBuffer returnValue = new StringBuffer();
			if (apendType == UtilMethods.APPEND_ZEROS)
				UtilMethods.appendLeadingZeros(returnValue, columnValue,
				        originalColumnLength);
			else
				UtilMethods.appendTrailingSpaces(returnValue, columnValue,
				        originalColumnLength);

			sb.append(returnValue.toString());
		}
		return sb.toString();
	}
	
	
	public String buildKey() throws Exception {
		Vector<ColumnDescriptor> columndescriptors = _tableDescriptor.getKeyColumnDescriptors();
		return buildConcatString(columndescriptors);
	}

	public String buildData() throws Exception {
		
		Vector<ColumnDescriptor> dataColumnDescriptors = new Vector<ColumnDescriptor>();
		for (int count = 0; count < _tableDescriptor.getColumnDescriptors().size(); count++) {
			ColumnDescriptor cd = _tableDescriptor.getColumnDescriptors().get(count);
			if (!cd.isKey())
				dataColumnDescriptors.add(cd);
		}
		return buildConcatString(dataColumnDescriptors);
	}

	public String getColumnsAsString() throws Exception {
		return buildConcatString(_tableDescriptor.getColumnDescriptors());
	}

	public void setColumns(String value) throws Exception {
		String delimit = "\t";
		if (value.indexOf('|') != -1)
			delimit = "|";
		setColumns(value, delimit);
	}
	
	public void setColumns(String value, String delim) throws Exception {
		
		int startValue = 0;
		int endValue = 0;
		String columnValue = null;

		Vector<ColumnDescriptor> columndescriptors = _tableDescriptor.getColumnDescriptors();
		int columnCount = _tableDescriptor.getNumberOfColumns();
		
		StringBuffer sb = new StringBuffer();

		for (int count = 0; count < columnCount; count++) {
			
			ColumnDescriptor cd = columndescriptors.get(count);
			int columnLength = cd.getColumnSize();
			endValue = startValue + columnLength;

			if (UtilMethods.isNumeric(cd.getDataType())) {
				if ((value.substring(startValue, endValue).indexOf(".") != -1)
				        || (value.substring(startValue, endValue).indexOf("-") != -1)
				        || (value.substring(startValue, endValue).indexOf("+") != -1)) {
					endValue = endValue + 1;
				}
			}

			columnValue = value.substring(startValue, endValue);

			if (delim.equals("|"))
				startValue = endValue + 1;
			else
				startValue = endValue;

			Object args[] = new Object[1];
			args[0] = columnValue;
			cd.getSetterMethod().invoke(this, args);

			int lengthTkn;
			String realString = "", decimalString = "";

			if (cd.isKey()) {
				columnValue = columnValue.trim();

				if (cd.getDataType() == Types.DECIMAL) {
					while (columnValue.length() != 0
					        && columnValue.charAt(0) == '0')
						columnValue = columnValue.substring(1);

					int decimalDigits;
					if ((columnValue.indexOf('.') == -1)) {
						if ((decimalDigits = cd.getDecimalDigits()) > 0) {
							lengthTkn = columnValue.length();
							realString = columnValue.substring(0, lengthTkn
							        - decimalDigits);
							decimalString = columnValue.substring(lengthTkn
							        - decimalDigits);
							columnValue = realString + "." + decimalString;
						} else
							columnValue = columnValue + ".";
					}

				}
				sb.append(columnValue);
			}
		}

		setKey(sb.toString());
	}

	
	public String getEBCDICKey(boolean dbVersion) throws Exception {

		Environment environment = EnvironmentManager.getInstance().getEnvironment(envId);
		if (environment.isNexEnv())
			return HighValueHandler.convertAsteriskToHV(buildKey(),envId);
		
		StringBuffer ebcKey = new StringBuffer();

		Vector<ColumnDescriptor> columndescriptors = _tableDescriptor.getKeyColumnDescriptors();
		StringBuffer adjustedColValue = new StringBuffer();
		for (int count = 0; count < columndescriptors.size(); count++) {

			ColumnDescriptor cd = columndescriptors.get(count);
			String columnValue = cd.getValue(this).toString();

			String strDataType = cd.getStringDataType();

			int colSize = cd.getColumnSize();
			int apendType = UtilMethods.getAppendType(cd
			        .getDataType());
			adjustedColValue.setLength(0);

			if (apendType == UtilMethods.APPEND_ZEROS)
				UtilMethods.appendLeadingZeros(adjustedColValue, columnValue,
				        colSize);
			else
				UtilMethods.appendTrailingSpaces(adjustedColValue, columnValue,
				        colSize);

			columnValue = HighValueHandler.convertAsteriskToHV(adjustedColValue.toString(), envId);

			if (strDataType.endsWith("NUMERIC"))
				colSize++;

			byte[] ebcdicDataArray = CommonASCIIToEBCDICHelper.convertData(
			        strDataType, colSize, 0, columnValue);

			String encoding = EnvironmentManager.getInstance().getEnvironment(envId).getHighValueEncoding();
			String ebcdicStr = null;
			try {
				ebcdicStr = new String(ebcdicDataArray, encoding);
			} catch (java.io.UnsupportedEncodingException us) {
				us.printStackTrace();
				ebcdicStr = new String(ebcdicDataArray);
			}

			ebcKey.append(ebcdicStr);
		}

		if (!dbVersion)
			return ebcKey.toString();
		else
			return generateDBEbcdic(ebcKey);
	}

	private String generateDBEbcdic(StringBuffer ebcKey)  {
		DBConnMgr dbconn = null;

		Connection connection = null;
		PreparedStatement pstmt = null;
		Statement stmt = null;
		ResultSet rs = null;

		String generatedKey = null;

		try {
			StringBuffer sql = new StringBuffer();
			sql.append(" DECLARE GLOBAL TEMPORARY TABLE EBCDIC_STORE ");
			sql.append(" ( UNIQUE_COL  CHAR(30) NOT NULL ,");
			sql.append(" EBCDIC_KEY  CHAR(200) FOR BIT DATA NOT NULL ) ");
			sql.append(" ON COMMIT PRESERVE ROWS");

			dbconn = DBConnMgr.getInstance();
			connection = dbconn.getConnection(envId,DBConnMgr.APPL);

			stmt = connection.createStatement();
			stmt.executeUpdate(sql.toString());

			// INSERT EBCDIC KEY
			String timeStamp = System.currentTimeMillis() + "";
			pstmt = connection
			        .prepareStatement("INSERT INTO SESSION.EBCDIC_STORE(UNIQUE_COL,EBCDIC_KEY) VALUES (?,?)");
			pstmt.setString(1, timeStamp);
			/**V-E231*/ 
			pstmt.setBytes(2, HighValueHandler.encode(ebcKey.toString(), EnvironmentManager.getInstance().getEnvironment(envId)));
			pstmt.executeUpdate();

			// READ THE EBCDIC KEY BACK FROM THE TEMPORARY TABLE
			rs = stmt
			        .executeQuery("SELECT EBCDIC_KEY FROM SESSION.EBCDIC_STORE WHERE UNIQUE_COL = '"
			                + timeStamp + "'");
			if (rs.next())
				generatedKey = rs.getString(1);

			// DROP THE TEMPORARY TABLE
			stmt.executeUpdate("DROP TABLE SESSION.EBCDIC_STORE");

		} catch (Exception e) {
			throw WrapperException.wrap(e);
		} finally {
			Utils.closeResultSet(rs);
			Utils.closeStatement(stmt);
			Utils.closePreparedStatement(pstmt);
			dbconn.releaseConnection(connection);
		}

		return generatedKey;
	}
	
	public String getValueOf(String columnName)throws Exception {
	    
	    if (columnName==null)
	        throw new Exception("column name is null");
	    
		String val = null;
		ColumnDescriptor cd = _tableDescriptor.getColumnDescriptor(columnName.toUpperCase());
		if (cd==null)
			throw new Exception(columnName+" not found");
		else
			val = (String)cd.getValue(this);
		return val;
	}
	

	
	
	public String getKeyFromRow(String value, String delim, String replaceDelim) {

		IOTokenizer strtok = new IOTokenizer(value, delim);
		
		int columnCount = 0;
		Vector<ColumnDescriptor> columndescriptors = _tableDescriptor.getColumnDescriptors();
		StringBuffer sb = new StringBuffer();
		
		while (strtok.hasMoreTokens()) {
			String columnValue = strtok.nextToken();
			ColumnDescriptor cd = columndescriptors.get(columnCount);
			columnCount++;

			if (columnCount > columndescriptors.size()) {
				break;
			} else {
				if (cd.isKey()) {
					sb.append(columnValue);
					sb.append(replaceDelim);
				}
			}
		}
		return sb.toString();
	}
	
	public String getColumnsAsString(String delim) throws Exception {
		StringBuffer sb = new StringBuffer();
		Vector<ColumnDescriptor> columndescriptors = _tableDescriptor.getColumnDescriptors();
		int size = columndescriptors.size();
		for (int count = 0; count < size; count++) {
			ColumnDescriptor cd = columndescriptors.get(count);
			String columnValue = cd.getValue(this).toString();
			sb.append(columnValue);
			if (count < columndescriptors.size() - 1)
				sb.append(delim);
		}
		return sb.toString();
	}
	
	public void setTableSubset(String subset) {
	}
	
    public void setColumnValues(String concatRow, String delim) throws Exception {
		
    	Vector<ColumnDescriptor> columndescriptors = _tableDescriptor.getColumnDescriptors();
		int columnCount = _tableDescriptor.getNumberOfColumns();
		
		StringBuffer sb = new StringBuffer();

		int startValue = 0;
		int endValue = 0;
		
		for (int count = 0; count < columnCount; count++) {
			
			ColumnDescriptor cd = columndescriptors.get(count);
			
			int columnLength = cd.getColumnSize();
			
			endValue = startValue + columnLength;

			String columnValue = null;
					
			if (UtilMethods.isNumeric(cd.getDataType())){
				
				String temp = concatRow.substring(startValue, endValue); 
				if ( temp.indexOf(".") != -1 || temp.indexOf("-") != -1 || temp.indexOf("+") != -1 ) 
					endValue = endValue + 1;
				
				columnValue = concatRow.substring(startValue, endValue);
				
				if ( columnValue.indexOf(".") != -1 || columnValue.indexOf("+") != -1 ) {
					columnValue = columnValue.substring(1);
				}
				
				if (cd.getDataType() == Types.DECIMAL) 
				{
					int decimalDigits = cd.getDecimalDigits();
				
					if ((columnValue.indexOf('.') == -1)){
						if (decimalDigits > 0)
						{
							int lengthTkn = columnValue.length();
							columnValue = columnValue.substring(0, lengthTkn - decimalDigits) + "." 
									 + columnValue.substring(lengthTkn - decimalDigits);
						} 
					}
				}
				
				while (columnValue.length() > 1  && columnValue.charAt(0) == '0'  && columnValue.charAt(1) != '.'){
					columnValue = columnValue.substring(1);
				}
				
			}else{
				columnValue = concatRow.substring(startValue, endValue);
			}

			//System.out.println(cd.getDbColumnName()+" "+columnValue);
			
			if (cd.isKey()) 
				sb.append(columnValue);
			
			Object args[] = new Object[1];
			args[0] = columnValue;
			cd.getSetterMethod().invoke(this, args);
			
			if (delim.trim().length()!=0)
				startValue = endValue + 1;
			else
				startValue = endValue;
			
		}

		setKey(sb.toString());
	}
	
	// WMA-1182
    public void setValuesFromDelimited(String delimitedRow, String delim) throws Exception {
		
    	Vector<ColumnDescriptor> columndescriptors = _tableDescriptor.getColumnDescriptors();
		int columnCount = _tableDescriptor.getNumberOfColumns();
		
		StringBuffer sb = new StringBuffer();

		IOTokenizer tokenizer = new IOTokenizer(delimitedRow, delim);
		
		for (int count = 0; count < columnCount; count++) {
			ColumnDescriptor cd = columndescriptors.get(count);			
			int columnLength = cd.getColumnSize();
			
			String columnValue = tokenizer.nextToken();
			if ( columnValue == null )
				throw new InfoException("Not enough columns in row (" + delimitedRow + ")");
			
			if (UtilMethods.isNumeric(cd.getDataType())){
				if ( /*columnValue.indexOf(".") != -1 ||*/ columnValue.indexOf("+") != -1 ) {
					columnValue = columnValue.substring(1);
				}
				
				while (columnValue.length() > 1  && columnValue.charAt(0) == '0'  && columnValue.charAt(1) != '.'){
					columnValue = columnValue.substring(1);
				}
			}
			
			// Accept dates in any supported format, and always convert them to yyyy-MM-dd
			if (cd.getDataType() == 91)
				columnValue = new SimpleDateFormat("yyyy-MM-dd").format(ImportHelper.parseDate(columnValue));

			if (cd.isKey()) 
				sb.append(columnValue);
			
			Object args[] = new Object[1];
			args[0] = columnValue;
			cd.getSetterMethod().invoke(this, args);
		}

		setKey(sb.toString());
	}
    
    public String getKeyColumnsPadded(String delim) throws Exception {
		Vector<ColumnDescriptor> columndescriptors = _tableDescriptor.getKeyColumnDescriptors();
		return getColumnsPadded(columndescriptors, delim);
    }
    
    public String getDataColumnsPadded(String delim) throws Exception {
		Vector<ColumnDescriptor> columndescriptors = _tableDescriptor.getDataColumnDescriptors();
		return getColumnsPadded(columndescriptors, delim);
    }
    
    public String getColumnsPadded(Vector<ColumnDescriptor> columndescriptors, String delim) throws Exception {
		StringBuffer sb = new StringBuffer();
		int size = columndescriptors.size();
		for (int count = 0; count < size; count++) {
			ColumnDescriptor cd = columndescriptors.get(count);
			String columnValue = cd.getValue(this).toString();
			sb.append(columnValue);

			if (count < columndescriptors.size() - 1)
				sb.append(delim);
		}
		return sb.toString();
    }
    
    public String getKeyColumnsAsString(String delim) throws Exception {
		StringBuffer sb = new StringBuffer();
		Vector<ColumnDescriptor> columndescriptors = _tableDescriptor.getKeyColumnDescriptors();
		int size = columndescriptors.size();
		for (int count = 0; count < size; count++) {
			ColumnDescriptor cd = columndescriptors.get(count);
			String columnValue = cd.getValue(this).toString();
			sb.append(columnValue);
			if (count < columndescriptors.size() - 1)
				sb.append(delim);
		}
		return sb.toString();
    }
    
    public String getDataColumnsAsString(String delim) throws Exception {
		StringBuffer sb = new StringBuffer();
		Vector<ColumnDescriptor> columndescriptors = _tableDescriptor.getColumnDescriptors();
		int size = columndescriptors.size();
		for (int count = 0; count < size; count++) {
			ColumnDescriptor cd = columndescriptors.get(count);
			if (cd.isKey())
				continue;
			String columnValue = cd.getValue(this).toString();
			sb.append(columnValue);
			if (count < columndescriptors.size() - 1)
				sb.append(delim);
		}
		return sb.toString();
    }
    
	public void setValueOf(String columnName,String value)throws Exception {
	    
	    if (columnName==null)
	        throw new Exception("column name is null");
	    
		String val = null;
		ColumnDescriptor cd = _tableDescriptor.getColumnDescriptor(columnName.toUpperCase());
		if (cd==null)
			throw new Exception(columnName+" not found");
		else
			cd.setValue(this,value);
	}
	

}
