package com.csc.fsg.life.pw.avm;

import java.text.NumberFormat;
import java.util.*;

import org.apache.commons.logging.Log;

import com.csc.fsg.life.avm.api.*;
import com.csc.fsg.life.avm.environments.AVMConfigBean;
import com.csc.fsg.life.pw.config.*;
import com.csc.fsg.life.pw.environment.*;
import com.csc.fsg.life.pw.log.PWServerLogManager;
import com.csc.fsg.life.pw.rules.SpecialHandling;

/* Modifications: T0091, HAVMENH, T0113, CCCV-E501, ENH793, T0150, WMA-1209 */

public class AVManager {

	private static Log _log = PWServerLogManager.getLog(
			AVManager.class.getPackage().getName());

	private static Hashtable<String, Hashtable> envXTables = new Hashtable<String, Hashtable>();

	public static final String LIST_TYPE = "L";
	public static final String RANGE_TYPE = "R";
	public static final String PIPE = "|";
	public static final String MIN_VALUES = "MIN_VALUES";
	public static final String MAX_VALUES = "MAX_VALUES";
	public static String application = "";
	public static String avURL = "";

	public static Hashtable<String, String> getAllowableValues(AVKey key) 
		throws Exception 
	{
		String env = key.getEnvironment();
		String field = key.getFieldName();
		Environment environment = EnvironmentManager.getInstance().getEnvironment(env);
		
		// Get company name from table T001X.
		if (field.equals("COMPANY_CODE")) 
			return environment.getAssistent() .getCompanyCodesAndNames();
		
		String company = key.getCompany();
		String product = key.getProduct();
		String table = key.getTable();
		String trxCode = key.getTrxCode();
		
		env = getAvmEnv(env);

		// CCCV-E501 - convertFieldName no longer takes productSystem arg
		field = SpecialHandling.getInstance().convertFieldName(field);

		if (product != null && product.trim().length() > 0)
			product = product.substring(0, 1) + "*";
			
		AccessKeys akeys = new AccessKeys(application, env, table, company,
		        product, trxCode, field, AccessKeys.ENVID_APPLID_PAGEID_ENABLED);

		AVMConfigBean avmBean = ProductManager.getInstance().getProduct(environment.getProductSystem()).getAvmBean();
		IFieldAllowableValues iav = AVMManager.getInstance(avmBean).getFieldAllowableValues(akeys,
		        AVMManager.SORT_CORE_VALUES_ASCENDING);
		if (iav == null) {
			_log.debug("AVM not found for  " + key.getFieldName());
			return new Hashtable<String, String>();
		}
		
		return getAllowableValues(iav, key.isKeyField());
	}

	// WMA-1209 - added size and scale
	public static String getDefault(AVKey key, int columnSize, int scale) 
		throws Exception 
	{
		String env = key.getEnvironment();
		String field = key.getFieldName();
		Environment environment = EnvironmentManager.getInstance().getEnvironment(env);
		
		// Get company name from table T001X.
		if (field.equals("COMPANY_CODE")) 
			return null;
		
		String company = key.getCompany();
		String product = key.getProduct();
		String table = key.getTable();
		String trxCode = key.getTrxCode();
		
		env = getAvmEnv(env);

		field = SpecialHandling.getInstance().convertFieldName(field);

		if (product != null 
		 && product.trim().length() > 0)
			product = product.substring(0, 1) + "*";
			
		AccessKeys akeys = new AccessKeys(application, env, table, company,
		        product, trxCode, field, AccessKeys.ENVID_APPLID_PAGEID_ENABLED);

		AVMConfigBean avmBean = ProductManager.getInstance().getProduct(environment.getProductSystem()).getAvmBean();
		IFieldAllowableValues iav = AVMManager.getInstance(avmBean).getFieldAllowableValues(akeys,
		        AVMManager.SORT_CORE_VALUES_ASCENDING);

		if (iav == null)
			return null;
		
		String defaultVal = iav.getFieldDefault();
		if (defaultVal == null 
		 || defaultVal.trim().isEmpty() 
		 || defaultVal.equals("*"))
			return null;
		
		if (iav.getFieldType().equalsIgnoreCase(RANGE_TYPE)) {
			int left = columnSize - scale;
			NumberFormat format = NumberFormat.getNumberInstance();
			format.setGroupingUsed(false);
			format.setMinimumFractionDigits(scale);
			format.setMaximumFractionDigits(scale);
			format.setMinimumIntegerDigits(left);
			format.setMaximumIntegerDigits(left);
			
			// WMA-1209 - handle AVM default values outside range
			Double rangeDefaultValue = Double.valueOf(defaultVal);
			RangeAllowableValues av = (RangeAllowableValues) iav.getAllowableValues();
			List<Double> minValues = ((RangeAllowableValues) av).getMinimumValues();
			List<Double> maxValues = ((RangeAllowableValues) av).getMaximumValues();
			
			if ( (minValues == null) || minValues.isEmpty() )
				return null;
			
			Boolean inRange = false;
			for (int i=0; i < minValues.size(); i++) {
				Double minValue = (Double) minValues.get(i);
				Double maxValue = (Double) maxValues.get(i);
				if ( rangeDefaultValue.doubleValue() >= minValue.doubleValue() &&
						rangeDefaultValue.doubleValue() <= maxValue.doubleValue() ) {
					inRange = true;
					break;
				}
			}
			if ( !inRange )
				rangeDefaultValue = Double.valueOf(minValues.get(0).doubleValue());
			
			defaultVal = format.format(rangeDefaultValue.doubleValue());
		}
		
		return defaultVal;
	}

	public static Hashtable getTablesInfo(String environment) 
		throws Exception 
	{
		String product = EnvironmentManager.getInstance().getEnvironment(environment).getProductSystem();
		AVMConfigBean avmBean = ProductManager.getInstance().getProduct(product).getAvmBean();
		environment = getAvmEnv(environment);
		Hashtable tableInfo = envXTables.get(environment);
		if (tableInfo == null) {
			tableInfo = AVMManager.getInstance(avmBean).getPageInfo(application, environment);
			envXTables.put(environment, tableInfo);
			_log.info("AVM Page info is loaded for " + environment);
		}
		return tableInfo;
	}

	public static String getTableName(String environment, String tableId) 
	{
		String tableName = null;
		environment = getAvmEnv(environment);
		Hashtable tablesInfo = envXTables.get(environment);

		try {
			if (tablesInfo == null)
				tablesInfo = getTablesInfo(environment);

			PageInfo pi = (PageInfo) tablesInfo.get(tableId);
			tableName = pi.getPageDesc();
		} catch (Exception e) {
			tableName = tableId;
		}
		return tableName;
	}

	public static String getColumnName(String environment, 
									   String tableId,
									   String columnId) 
	{
		// if recurring column, get its base name
		String nameBase = SpecialHandling.getInstance().getFieldNameBase(
		        columnId);
		String fieldName = (nameBase == null) ? columnId : nameBase;

		// get display name from AVM
		StringBuffer sb = new StringBuffer();
		try {
			environment = getAvmEnv(environment);
			Hashtable tablesInfo = envXTables.get(environment);
			if (tablesInfo == null)
				tablesInfo = getTablesInfo(environment);
			PageInfo pi = (PageInfo) tablesInfo.get(tableId);
			if (pi.getFieldInfo().containsKey(fieldName))
				sb.append(pi.getFieldInfo().get(fieldName));
			else {
				pi = (PageInfo) tablesInfo.get("ALL");
				if (pi.getFieldInfo().containsKey(fieldName))
					sb.append(pi.getFieldInfo().get(fieldName));
				else
					sb.append(fieldName);
			}
		} catch (Exception e) {
			sb.append(fieldName);
		}

		// if we used a base name, add number to display name
		if (nameBase != null) {
			int i = columnId.length() - 1;
			while (i >= 0) {
				char digit = columnId.charAt(i);
				if (!Character.isDigit(digit))
					break;
				i--;
			}
			if (i < (columnId.length() - 1)) {
				String snum = columnId.substring(i + 1);
				int num = Integer.parseInt(snum);
				sb.append("-").append(num);
			}
		}

		return sb.toString();
	}

	private static Hashtable<String, String> getAllowableValues(IFieldAllowableValues iav,
	        													boolean isKeyField) 
	    throws Exception 
	{
		AllowableValues av = iav.getAllowableValues();

		if (iav.getFieldType().equalsIgnoreCase(LIST_TYPE))
			return getList(av, isKeyField);

		return getRange(av);
	}

	private static Hashtable<String, String> getList(AllowableValues av, boolean isKeyField)
	    throws Exception 
	{
		Hashtable<String, String> allowValues = new Hashtable<String, String>();
		List coreValues = ((ListAllowableValues) av).getCoreValues();
		List displayValues = ((ListAllowableValues) av).getDisplayValues();

		for (int i = 0; i < coreValues.size(); i++) {
			String coreValue = (String) coreValues.get(i);

			/**
			 * AV is storing * for high values now..All key fields should be
			 * converted to HIGH VALES
			 */
			// if (isKeyField && coreValue.startsWith("*"))
			// coreValue =
			// Utils.buildString(coreValue.length(),Constants.HIGHVALUES_FROM_MAINFRAME.charAt(0));
			/**
			 * DAOUtils is trmming the values..We need Core Values with space
			 * not empty String
			 */
			if (coreValue.trim().length() == 0)
				coreValue = " ";
			String displayValue = (String) displayValues.get(i);
			allowValues.put(coreValue, displayValue);
		}

		return allowValues;
	}

	private static Hashtable<String, String> getRange(AllowableValues av) 
		throws Exception 
	{
		List minValues = ((RangeAllowableValues) av).getMinimumValues();
		List maxValues = ((RangeAllowableValues) av).getMaximumValues();

		NumberFormat format = NumberFormat.getNumberInstance();
		format.setGroupingUsed(false);
		format.setMinimumFractionDigits(3);
		format.setMaximumFractionDigits(12);
		format.setMaximumIntegerDigits(13);

		StringBuffer minValueString = new StringBuffer();
		StringBuffer maxValueString = new StringBuffer();

		for (int i = 0; i < minValues.size(); i++) {

			Double minValue = (Double) minValues.get(i);
			Double maxValue = (Double) maxValues.get(i);

			if (i != (minValues.size() - 1)) {
				minValueString.append(format.format(minValue.doubleValue())
				        + PIPE);
				maxValueString.append(format.format(maxValue.doubleValue())
				        + PIPE);
			} else {
				minValueString.append(format.format(minValue.doubleValue()));
				maxValueString.append(format.format(maxValue.doubleValue()));
			}
		}
		Hashtable<String, String> allowValues = new Hashtable<String, String>();
		allowValues.put(MIN_VALUES, minValueString.toString());
		allowValues.put(MAX_VALUES, maxValueString.toString());
		return allowValues;
	}

	private static String getAvmEnv(String env) {
		String ret = env;
		String override = EnvironmentManager.getInstance().getEnvironment(env).getProps().getAvmEnvironment();
		if (override != null) 
			ret = override;
		return ret;
	}

	public static String getAVURL(String product) {
		AVMConfigBean avmBean = ProductManager.getInstance().getProduct(product).getAvmBean();
		return avmBean.getAvmServerlink();
	}

	public static void setAVURL(String url) {
		// avURL = url;
	}
}
