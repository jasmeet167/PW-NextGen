package com.csc.fsg.life.pw.avm;

import java.util.*;

import com.csc.fsg.life.pw.rules.SpecialHandling;
import com.csc.fsg.life.pw.util.Constants;
import com.csc.fsg.life.pw.util.IOTokenizer;

/* Modifications: T0103, T0091, T0106, T0157, ENH1053 */

public class FieldMetaData {

	private static final String COMMA = ",";

	private static final String PIPE = "|";

	private static final String EMPTY_STRING = "";

	private Map<String, DataNameData> dataNameNProdPrefix = new TreeMap<String, DataNameData>();

	private boolean key = false;

	private String dbColumnName = null;

	private int dbColumnPosition = -1;

	private int decimalDigits = -1;

	private int columnSize = -1;

	private int dataType = -1;

	private String ddlName = null;

	public FieldMetaData(String dbColumnName, int dbColumnPosition,
			int dataType, int columnSize, int decimalDigits, boolean isKey,
			ArrayList<DataNameData> list) 
	{
		this.dbColumnName = dbColumnName;
		this.dbColumnPosition = dbColumnPosition;
		this.dataType = dataType;
		this.columnSize = columnSize;
		this.decimalDigits = decimalDigits;
		this.key = isKey;
		setDataNameNProdPrefix(list);
	}

	public FieldMetaData(String tabbedData) 
		throws Exception 
	{
		IOTokenizer tokenizer = new IOTokenizer(tabbedData, PIPE);
		setMetaData(tokenizer.nextToken());
		while (tokenizer.hasMoreTokens())
			add2DNMap(tokenizer.nextToken());
	}

	private void add2DNMap(String str) 
	{
		IOTokenizer tokenizer = new IOTokenizer(str, COMMA);
		String productPrefix = tokenizer.nextToken();
		String temp = tokenizer.nextToken();
		int displayOrder = 0;
		if (temp != null)
			displayOrder = Integer.parseInt(temp);

		String dataType = tokenizer.nextToken();

		temp = tokenizer.nextToken();
		int attribute = -1;
		if (temp != null)
			attribute = Integer.parseInt(temp);

		String tableId = tokenizer.nextToken();
		String tableZeroVariation = tokenizer.nextToken();
		String brccFilter = tokenizer.nextToken();
		String helpURL = tokenizer.nextToken();
		int displayLength = 0;
		String controlColumnName = tokenizer.nextToken();

		DataNameData fd = new DataNameData(EMPTY_STRING, dbColumnName,
				productPrefix, displayOrder, dataType, attribute, tableId,
				tableZeroVariation, brccFilter, helpURL, displayLength,
				controlColumnName);
		dataNameNProdPrefix.put(fd.getProductPrefix(), fd);
	}

	private void setMetaData(String str) 
		throws NumberFormatException 
	{
		IOTokenizer tokenizer = new IOTokenizer(str, COMMA);
		this.dbColumnName = tokenizer.nextToken();

		String temp = tokenizer.nextToken();

		if (temp != null)
			this.dataType = Integer.parseInt(temp);

		temp = tokenizer.nextToken();
		if (temp != null)
			this.dbColumnPosition = Integer.parseInt(temp);

		temp = tokenizer.nextToken();
		if (temp != null)
			this.columnSize = Integer.parseInt(temp);

		temp = tokenizer.nextToken();
		if (temp != null)
			this.decimalDigits = Integer.parseInt(temp);

		temp = tokenizer.nextToken();
		if (temp != null)
			this.key = Boolean.valueOf(temp).booleanValue();
	}

	public int getColumnSize() {
		return columnSize;
	}

	public int getDataType() {
		return dataType;
	}

	public String getDbColumnName() {
		return dbColumnName;
	}

	public int getDbColumnPosition() {
		return dbColumnPosition;
	}

	public int getDecimalDigits() {
		return decimalDigits;
	}

	public boolean isKeyField() {
		return key;
	}

	@Override
	public String toString() {
		StringBuffer str = new StringBuffer(dbColumnName + COMMA + dataType
				+ COMMA + dbColumnPosition + COMMA + columnSize + COMMA
				+ decimalDigits + COMMA + key);

		Set<String> set = dataNameNProdPrefix.keySet();
		Iterator<String> it = set.iterator();

		while (it.hasNext()) {
			str.append(PIPE);
			DataNameData data = dataNameNProdPrefix.get(it
					.next());
			str.append(data.getProductPrefix() + COMMA + data.getDisplayOrder()
					+ COMMA + data.getDataType() + COMMA + data.getAttribute()
					+ COMMA + data.getTableId() + COMMA
					+ data.getTableZeroVariation() + COMMA
					+ data.getBrccFilter() + COMMA + data.getHelpURL()
					+ COMMA + data.getControlColumnName());
		}

		return str.toString();
	}

	public void print() {
		System.out.println("dbColumnName " + dbColumnName);
		System.out.println("dataType " + dataType);
		System.out.println("dbColumnPosition " + dbColumnPosition);
		System.out.println("columnSize " + columnSize);
		System.out.println("decimalDigits " + decimalDigits);
		System.out.println("key " + key);

		Set<String> set = dataNameNProdPrefix.keySet();
		Iterator<String> it = set.iterator();

		while (it.hasNext()) {
			DataNameData data = dataNameNProdPrefix.get(it
					.next());
			data.print();
		}
	}

	private void setDataNameNProdPrefix(ArrayList<DataNameData> list) 
	{
		for (int i = 0; i < list.size(); i++) {
			DataNameData dt = list.get(i);
			String productPrefix = dt.getProductPrefix();
			if (productPrefix == null 
			 || productPrefix.trim().length() == 0)
				productPrefix = EMPTY_STRING;
			dataNameNProdPrefix.put(productPrefix, dt);
			
			if (dataType == 5
			 && dt.getDisplayLength() > 0)
				columnSize = dt.getDisplayLength();
		}
	}

	public Map<String, DataNameData> getDataNameNProdPrefix() {
		return dataNameNProdPrefix;
	}

	public int getAttribute(String productPrefix) {
		DataNameData dnd = getDataNameRecord(productPrefix);
		if (dnd != null)
			return dnd.getAttribute();
		return 4;// OPTIONAL
	}

	public String getBrccFilter(String productPrefix) {
		DataNameData dnd = getDataNameRecord(productPrefix);
		if (dnd != null)
			return dnd.getBrccFilter();
		// return EMPTY_STRING;
		return null;
	}

	public String getSQLDataType() {
//		Set set = dataNameNProdPrefix.keySet();
//		Iterator it = set.iterator();
//		DataNameData dnd = (DataNameData) dataNameNProdPrefix.get(it.next());
//		return dnd.getDataType();
		// V1 SPR 103 - get SQL data type from dataType not from DnD
		return SpecialHandling.getInstance().translateDataType(dataType,columnSize,decimalDigits);
	}

	public int getDisplayOrder(String productPrefix) {
		DataNameData dnd = getDataNameRecord(productPrefix);
		if (dnd != null)
			return dnd.getDisplayOrder();
		return dbColumnPosition;
	}

	private DataNameData getDataNameRecord(String productPrefix) 
	{
		DataNameData dnd = null;
		if (productPrefix != null
		 && productPrefix.trim().length() > 0
		 && (SpecialHandling.getInstance().getProductPrefix(ddlName) == null)) {
			dnd = dataNameNProdPrefix.get(productPrefix.substring(0, 1));
		} else
			dnd = dataNameNProdPrefix.get(EMPTY_STRING);

		return dnd;
	}

	public String[] getProductPrefix() {
		Set<String> set = dataNameNProdPrefix.keySet();
		String[] pp = new String[set.size()];
		Iterator<String> it = set.iterator();
		int i = 0;
		while (it.hasNext()) {
			pp[i] = it.next();
			i++;
		}
		return pp;
	}

	public String getTableId(String productPrefix) {
		DataNameData dnd = getDataNameRecord(productPrefix);
		if (dnd != null)
			return dnd.getTableId();
		// return EMPTY_STRING;
		return null;
	}

	public String getTableZeroVariation(String productPrefix) {
		DataNameData dnd = getDataNameRecord(productPrefix);
		if (dnd != null)
			return dnd.getTableZeroVariation();
		// return EMPTY_STRING;
		return null;
	}

	public String[] getTableIdNVariation(String productPrefix) 
	{
		String[] tabIdNVar = new String[2];
		String tabid = getTableId(productPrefix);
		String tabVar = getTableZeroVariation(productPrefix);

		if (tabid != null)
			tabIdNVar[0] = tabid;
		else
			tabIdNVar[0] = Constants.NOKEY;

		if (tabVar != null)
			tabIdNVar[1] = tabVar;
		else
			tabIdNVar[1] = " ";

		return tabIdNVar;
	}

	public boolean isTableSubsetColumn() {
		return getDbColumnName().equalsIgnoreCase("TABLE_SUBSET");
	}

	public Class<String> getSetterParmClass() {
		return java.lang.String.class;
	}

	public int getMinDisplaySize() {
		return columnSize + decimalDigits + 1;
	}

	/**
	 * @return Returns the ddlName.
	 */
	public String getDdlName() {
		return ddlName;
	}

	/**
	 * @param ddlName
	 *            The ddlName to set.
	 */
	public void setDdlName(String ddlName) {
		this.ddlName = ddlName;
	}

	public String getControlColumnName(String productPrefix)
	{
		DataNameData dnd = getDataNameRecord(productPrefix);
		if (dnd == null)
			return null;
		else
			return dnd.getControlColumnName();
	}

	public ArrayList<String> getAllTableIdNVariations() {
		ArrayList<String> list = new ArrayList<String>();
		String[] pp = getProductPrefix();
		String tableId = null;
		String var = null;
		for (String element : pp) {
			tableId = getTableId(element);
			var = getTableZeroVariation(element);
			if (tableId != null && tableId.trim().length() > 0) {
				if (var == null)
					var = "";
				list.add(tableId.trim() + var.trim());
			}
		}
		return list;
	}
}