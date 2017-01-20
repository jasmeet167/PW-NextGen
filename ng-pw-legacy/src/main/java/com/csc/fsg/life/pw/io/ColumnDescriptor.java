package com.csc.fsg.life.pw.io;

import java.lang.reflect.*;
import java.sql.*;
import java.util.*;

import org.apache.commons.logging.Log;
import org.springframework.util.StringUtils;

import com.csc.fsg.life.exceptions.WrapperException;
import com.csc.fsg.life.pw.rules.SpecialHandling;
import com.csc.fsg.life.pw.transferobjects.PlanTO;
import com.csc.fsg.life.pw.util.*;
import com.csc.fsg.life.pw.avm.*;
import com.csc.fsg.life.pw.controller.IResponse;
import com.csc.fsg.life.pw.environment.*;
import com.csc.fsg.life.pw.log.PWServerLogManager;
import com.csc.fsg.life.pw.util.HighValueHandler;

/* Modifications: ENH764, T0103, T0091 , HAVMENH, T0115, T0114, ENH961, T0129, ENH793,
 *                WMA-1190, WMA-1209, ENH1053 */

public class ColumnDescriptor {
	
	private TableDescriptor td;
	private Method getterMethod;
	private Method setterMethod;
	private String getterMethodName;
	private String setterMethodName;

	private FieldMetaData metaData = null;

	private static Log _log = PWServerLogManager.getLog(ColumnDescriptor.class
	        .getPackage().getName());

	public ColumnDescriptor(TableDescriptor td, String getter, String setter,
	        String tabbedData) {
		try {
			this.td = td;
			metaData = new FieldMetaData(tabbedData);
			getterMethodName = getter;
			setterMethodName = setter;
			initializeSetterMethod();
			initializeGetterMethod();
			metaData.setDdlName(td.getDDLName());

		} catch (Exception e) {
			throw WrapperException.wrap(e, e.getMessage());
		}
	}

	public Method getGetterMethod() {
		return getterMethod;
	}

	public Method getSetterMethod() {
		return setterMethod;
	}

	public Object getValue(ResultSet resultSet) {

		Object object = null;

		try {
			object = resultSet.getString(metaData.getDbColumnPosition());
			object = WizUtils.pwTrim(((String) object));
		} catch (SQLException sqlException) {
			throw WrapperException
			        .wrap(sqlException, sqlException.getMessage());
		}
		return object;
	}

	public Object getValue(Row row) {

		Object object = null;

		try {
			object = getterMethod.invoke(row, (Object[])null);
		} catch (IllegalAccessException illegalAccessException) {
			throw WrapperException.wrap(illegalAccessException,
			        illegalAccessException.getMessage());
		} catch (InvocationTargetException invocationTargetException) {
			throw WrapperException.wrap(invocationTargetException,
			        invocationTargetException.getMessage());
		}

		return object;
	}

	public void hydrateFrom(ResultSet resultSet, Row object, String envId)
	        throws Exception {

		Object obj = getValue(resultSet);

		if (isKey() && obj instanceof String)
			obj = HighValueHandler.convertHVToAsterisk((String) obj, envId);

		Object args[] = new Object[1];
		args[0] = obj;
		setterMethod.invoke(object, args);

	}

	public StringBuffer getColMetaData(String schemaName, int authority,
	        String productCode) {

		StringBuffer sbuff = new StringBuffer();
		sbuff.append(authority);
		sbuff.append("\t");
		String displayName = AVManager.getColumnName(schemaName, td
		        .getTableName(), metaData.getDbColumnName().trim());
		sbuff.append(displayName);
		sbuff.append("\t");
		sbuff.append(metaData.getDbColumnName());
		sbuff.append("\t");
		sbuff.append(metaData.getSQLDataType());
		sbuff.append("\t");
		sbuff.append(metaData.getColumnSize());
		sbuff.append("\t");
		sbuff.append(metaData.getMinDisplaySize());
		sbuff.append("\t");
		sbuff.append("0");
		sbuff.append("\n");
		return sbuff;
	}

	public int getAttrMask(String productPrefix) {

		int attrMask = 0;
		String saveProduct = productPrefix;
		if (productPrefix == null || productPrefix.equalsIgnoreCase("C")
		        || productPrefix.equalsIgnoreCase("*"))
			productPrefix = " ";

		int attr = metaData.getAttribute(productPrefix);

		switch (attr) {
			case 0: {
				attrMask += Constants.TABLE_ATTR_REQUIRED;
				break;
			}
			case 1: {
				attrMask += Constants.TABLE_ATTR_READ_ONLY;
				break;
			}
			case 2: {
				attrMask += Constants.TABLE_ATTR_HIDDEN;
				break;
			}
			case 4: {
				break;
			}
		}

		attrMask += Constants.TABLE_ATTR_HAS_HELP_URL;
		
		if (metaData.isKeyField())
			attrMask += Constants.TABLE_ATTR_KEY;

		// ENH764
		if (metaData.getTableId(productPrefix) != null)
			attrMask += Constants.TABLE_ATTR_DO_POINTER_MAINTENANCE;
			
		return attrMask;
	}

	protected void initializeGetterMethod() {

		Class parms[] = new Class[0];

		try {
			getterMethod = getRowClass().getMethod(getterMethodName, parms);
		} catch (NoSuchMethodException _ex) {
			_log.debug("Error Setting " + getterMethodName);
		}
	}

	protected void initializeSetterMethod() {

		Class parms[] = new Class[1];

		parms[0] = metaData.getSetterParmClass();
		try {
			setterMethod = getRowClass().getMethod(setterMethodName, parms);
		} catch (NoSuchMethodException _ex) {
			_log.debug("Error Setting " + setterMethodName);
		}
	}
	
	public Class getRowClass() {
		return td.getRowClass();
	}

	public FieldMetaData getMetaData() {
		return metaData;
	}

	/** with allowable values - PrintWriter */
	public void toStream(IResponse out, SourceTableFilter filter,
	        PlanTO plan,Connection conn) throws Exception{
		StringBuffer sb = new StringBuffer();
		toStream(sb, filter, plan,conn);
		out.print(sb.toString());
	}

	private void toStream(StringBuffer sb, SourceTableFilter filter,
	        PlanTO plan,Connection conn) throws Exception{
		String envSchema = filter.getEnvSchema();
		Vector<String> companies = filter.getCompanies();
		String company = null;
		if (companies != null && !companies.isEmpty())
			company = companies.elementAt(0);
		String product = filter.getProductPrefix();

		String table = filter.getTableName();
		String subset = filter.getSubsetName();

		AVKey avKey = new AVKey(envSchema, company, product, table, subset,
		        null, getDbColumnName());
		avKey.setKey(this.isKey());
		toStream(sb, avKey, plan,conn);
	}

	public void toStream(StringBuffer sb, AVKey avKey, PlanTO plan,Connection conn) throws Exception{
		StringBuffer colBuffer = new StringBuffer();

		String colId = metaData.getDbColumnName().trim();
		colBuffer.append(colId);

		String displayName = AVManager.getColumnName(avKey.getEnvironment(),
		        avKey.getTable(), colId);
		colBuffer.append(Constants.STAB);
		colBuffer.append(displayName);

		String product = avKey.getProduct();
		if (product != null)
			product = product.substring(0, 1);

		colBuffer.append(Constants.STAB);
		colBuffer.append(metaData.getDisplayOrder(product));
		
		colBuffer.append(Constants.STAB);
		if (hasDynamicValues(product))
			colBuffer.append(getControlColumnName(product));

		// ENH961 - send pointer table and variation
		colBuffer.append(Constants.STAB);
		String pointerTableId = metaData.getTableId(product);
		if ( pointerTableId == null ) {
			colBuffer.append(" " + Constants.STAB + " ");	// table name and variation
		} else {
			TableDescriptor pointerTd = EnvironmentManager.getInstance()
				.getEnvironment(avKey.getEnvironment()).getTableDescMgr().getTableDescriptor(pointerTableId);
			colBuffer.append(pointerTd.getTableName());
			colBuffer.append(Constants.STAB);
			String pointerTableVar = metaData.getTableZeroVariation(product);
			if ( pointerTableVar == null )
				colBuffer.append(" ");
			else
				colBuffer.append(pointerTableVar);
		}

		colBuffer.append(Constants.STAB);
		colBuffer.append(metaData.getSQLDataType());
		colBuffer.append(Constants.STAB);
		colBuffer.append(metaData.getColumnSize());

		String sqlDataType = metaData.getSQLDataType();

		if (sqlDataType.equalsIgnoreCase("DOUBLE")
		        || sqlDataType.equalsIgnoreCase("DECIMAL")) {
			colBuffer.append(Constants.STAB);
			colBuffer.append(metaData.getDecimalDigits());
		}

		String helpURL = getHelpURL(avKey.getEnvironment());

		if (helpURL != null && helpURL.trim().length() > 0) {
			colBuffer.append(Constants.STAB);
			colBuffer.append(helpURL);
		}

		int attrMask = getAttrMask(product);

		if (hasDynamicValues(product)) {
			List<String[]> dynamicAVValues = getDynamicAllowableValues(avKey, plan, conn);
			if (dynamicAVValues != null && !dynamicAVValues.isEmpty()) {
				String avSourceName = "DYNAMIC - " + colId;
				String dynamicAVString = getDynamicAVStream(dynamicAVValues, avKey.getEnvironment(), avSourceName);
				colBuffer.append(Constants.STAB);
				colBuffer.append(dynamicAVString);
			}
		}
		else {
			StringBuffer avSourceName = new StringBuffer();
			Hashtable<String, String> avValues = getAllowableValues(avKey, plan,conn,avSourceName);
			String avString = getAVStream(avValues, avKey.getEnvironment(), avSourceName.toString());
	
			if (avString.trim().length() > 0) {
				if (isAVList(avValues))
					attrMask += Constants.TABLE_ATTR_HAS_VALUES;
				else
					attrMask += Constants.TABLE_ATTR_IS_BOUNDED;
	
				colBuffer.append(Constants.STAB);
				colBuffer.append(avString);
			}
		}

		sb.append(attrMask + Constants.STAB + colBuffer);
	}

	protected String getDynamicAVStream(List<String[]> dynamicAVValues, String schema, String avSourceName)
	{
		StringBuilder buf = new StringBuilder();
		buf.append(avSourceName);
		buf.append(Constants.STAB);

		if (dynamicAVValues == null || dynamicAVValues.isEmpty()) {
			buf.append(0);
		}
		else {
			buf.append(dynamicAVValues.size());

			for (String[] values : dynamicAVValues) {
				if (values.length != 3)
					throw new IllegalArgumentException();

				// Control value
				buf.append(Constants.STAB);
				buf.append(values[0]);

				// Core value
				buf.append(Constants.STAB);
				buf.append(HighValueHandler.convertHVToAsterisk(values[1], schema));

				// Display value
				buf.append(Constants.STAB);
				buf.append(values[1]);
				if (values[2] != null 
				 && values[2].trim().length() > 0) {
					buf.append('-');
					buf.append(values[2]);
				}
			}
		}

		return buf.toString();
	}

	protected String getAVStream(Hashtable<String, String> avValues, String schema, String avSourceName) {
		if (avValues == null || avValues.isEmpty())
			return "";

		if (isAVList(avValues))
			return writeList(avValues, schema, avSourceName);
		else
			return writeBounds(avValues);
	}

	protected String writeList(Hashtable<String, String> av, String schema, String avSourceName) {
		// Just to sort..
		TreeMap<String, String> tmap = new TreeMap<String, String>(av);

		StringBuffer sb = new StringBuffer();

		Set<Map.Entry<String, String>> entries = tmap.entrySet();
		
		int occurences = entries.size();
		if (occurences <= 0)
			return sb.toString();

		sb.append(avSourceName).append(Constants.STAB);
		sb.append(occurences);

		for ( Map.Entry<String, String> entry : entries ) {
			sb.append(Constants.STAB);
			String coreValue = entry.getKey();
			String displayValue = entry.getValue();

			displayValue = Utils.safeTrim(displayValue);
			// table constraints values may not be converted..so convert now
			coreValue = HighValueHandler.convertHVToAsterisk(coreValue, schema);
			sb.append(coreValue);
			if (displayValue.trim().length() > 0)
				sb.append("-").append(displayValue);
			sb.append(Constants.STAB);
			sb.append(coreValue);
		}

		return sb.toString();
	}

	protected String writeBounds(Hashtable<String, String> av) {

		StringBuffer sb = new StringBuffer();

		String minValues = av.get(AVManager.MIN_VALUES);
		String maxValues = av.get(AVManager.MAX_VALUES);

		if (minValues.trim().length() < 1 || maxValues.trim().length() < 1)
			return "";

		IOTokenizer minTokenizer = new IOTokenizer(minValues, AVManager.PIPE);
		IOTokenizer maxTokenizer = new IOTokenizer(maxValues, AVManager.PIPE);

		sb.append(minTokenizer.countTokens());

		while (minTokenizer.hasMoreTokens()) {
			sb.append(Constants.STAB);

			String minValue = minTokenizer.nextToken();
			String maxValue = maxTokenizer.nextToken();

			String sQLDataType = metaData.getSQLDataType();
			if (sQLDataType.equalsIgnoreCase("INTEGER")
			        || sQLDataType.equalsIgnoreCase("BIGINT")) {
				minValue = minValue.substring(0, minValue.indexOf(".")).trim();
				maxValue = maxValue.substring(0, maxValue.indexOf(".")).trim();
			}

			sb.append(minValue);
			sb.append(Constants.STAB);
			sb.append(maxValue);
		}
		return sb.toString();
	}

	private List<String[]> getDynamicAllowableValues(AVKey avKey, PlanTO plan, Connection conn)
	{
		TCBean tcBean = TCBean.getInstance();
		String controlColumnName = getControlColumnName(plan.getProductPrefix());
		return tcBean.getDynamicAV(avKey, plan, conn, controlColumnName);
	}
	
	public Hashtable<String, String> getAllowableValues(AVKey avKey,PlanTO plan,Connection conn) throws Exception {
		return getAllowableValues(avKey, plan, conn, null);
	}
	
	public Hashtable<String, String> getAllowableValues(AVKey avKey,PlanTO plan,Connection conn,
			StringBuffer avSourceName) throws Exception {
		StringBuffer avSource = new StringBuffer();
		avSource.setLength(0);
		Hashtable<String, String> avValues = TCBean.getInstance().getAV(avKey,plan,conn,avSource);
		if (avValues == null) {
			avValues = AVManager.getAllowableValues(avKey);
			if ( avValues != null && avValues.size() > 0 )
				avSource.append("AVM - " + SpecialHandling.getInstance().convertFieldName(avKey.getFieldName()));
		}
		if ( avSourceName != null ) {
			avSourceName.setLength(0);
			avSourceName.append(avSource);
		}
		return avValues;
	}
	
	public String getDefault(AVKey avKey,PlanTO plan,Connection conn) throws Exception {
		StringBuffer avSource = new StringBuffer();
		Hashtable<String, String> avValues = TCBean.getInstance().getAV(avKey,plan,conn,avSource);
		if (avValues == null) {
			// WMA-1209 - add column size and scale
			return AVManager.getDefault(avKey, getColumnSize(), getDecimalDigits());
		}
		return null;
	}

	public static boolean isAVList(Hashtable<String, String> av) {
		return (av.get(AVManager.MAX_VALUES) == null);
	}

	/** Temporary methods.. */

	public int getColumnSize() {
		return metaData.getColumnSize();
	}

	public int getDataType() {
		return metaData.getDataType();
	}

	public String getDbColumnName() {
		return metaData.getDbColumnName();
	}

	public int getDbColumnPosition() {
		return metaData.getDbColumnPosition();
	}

	public int getDecimalDigits() {
		return metaData.getDecimalDigits();
	}

	public boolean isKey() {
		return metaData.isKeyField();
	}

	
	public void toStream(StringBuffer sb, String productPrefix, String envSchema) {
		StringBuffer colBuffer = new StringBuffer();

		String colId = metaData.getDbColumnName().trim();
		colBuffer.append(colId);

		String displayName = AVManager.getColumnName(envSchema, td
		        .getTableName(), colId);
		colBuffer.append(Constants.STAB);
		colBuffer.append(displayName);

		colBuffer.append(Constants.STAB);
		if (productPrefix == null || productPrefix.equalsIgnoreCase("*"))
			colBuffer.append(metaData.getDisplayOrder(null));
		else
			colBuffer.append(metaData.getDisplayOrder(productPrefix));
		
		colBuffer.append(Constants.STAB);
		if (hasDynamicValues(productPrefix))
			colBuffer.append(getControlColumnName(productPrefix));

		// ENH961 - send pointer table and variation
		String product = null;
		if ( (productPrefix != null) && !productPrefix.equalsIgnoreCase("*") )
			product = productPrefix;
		colBuffer.append(Constants.STAB);
		String pointerTableId = metaData.getTableId(product);
		if ( pointerTableId == null ) {
			colBuffer.append(" " + Constants.STAB + " ");	// table name and variation
		} else {
			TableDescriptor pointerTd = EnvironmentManager.getInstance()
				.getEnvironment(envSchema).getTableDescMgr().getTableDescriptor(pointerTableId);
			colBuffer.append(pointerTd.getTableName());
			colBuffer.append(Constants.STAB);
			String pointerTableVar = metaData.getTableZeroVariation(product);
			if ( pointerTableVar == null )
				colBuffer.append(" ");
			else
				colBuffer.append(pointerTableVar);
		}

		colBuffer.append(Constants.STAB);
		colBuffer.append(metaData.getSQLDataType());
		colBuffer.append(Constants.STAB);
		colBuffer.append(metaData.getColumnSize());

		String sqlDataType = metaData.getSQLDataType();

		if (sqlDataType.equalsIgnoreCase("DOUBLE")
		        || sqlDataType.equalsIgnoreCase("DECIMAL")) {
			colBuffer.append(Constants.STAB);
			colBuffer.append(metaData.getDecimalDigits());
		}

		colBuffer.append(Constants.STAB);
		colBuffer.append(getHelpURL(envSchema));
		
		int attrMask = getAttrMask(productPrefix);

		sb.append(attrMask + Constants.STAB + colBuffer);
	}
	
	public String getStringDataType() {

		int dataType = getDataType();
		String dType = "ALPHANUM";

		switch (dataType) {

			case Types.TINYINT:
			case Types.SMALLINT:
				dType = "SNUMERIC";
				break;
			case Types.INTEGER:
			case Types.BIGINT:
			case Types.FLOAT:
			case Types.REAL:
			case Types.DOUBLE:
			case Types.NUMERIC:
			case Types.DECIMAL:
				dType = "NUMERIC";
				break;

			case Types.CHAR:
			case Types.VARCHAR:
			case Types.LONGVARCHAR:
				dType = "ALPHANUM";
				break;

			case Types.BINARY:
			case Types.VARBINARY:
			case Types.LONGVARBINARY:
				dType = "BINARY";
				break;
		}
		return dType;
	}

	public void setValue(Row row, Object value) {
		Object args[] = new Object[1];
		args[0] = value;
		try {
			setterMethod.invoke(row, args);
		} catch (IllegalAccessException illegalAccessException) {
			illegalAccessException.printStackTrace();
		} catch (InvocationTargetException invocationTargetException) {
			invocationTargetException.printStackTrace();
		}
	}
	
	public String getHelpURL(String env){
		Environment environment = EnvironmentManager.getInstance().getEnvironment(env);
	    return "help/"+environment.getProductSystem()+"/businessrules/"
                + td.getTableName()
                + ".htm#"
                + metaData.getDbColumnName();
	}

	protected boolean hasDynamicValues(String productPrefix)
	{
		return StringUtils.hasText(getControlColumnName(productPrefix));
	}

	protected String getControlColumnName(String productPrefix)
	{
		return metaData.getControlColumnName(productPrefix);
	}
}
