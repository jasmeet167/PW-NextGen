package com.csc.fsg.life.pw.web.avm;

/* Modifications: T0106, T0157, ENH1053 */

public class DataNameData {

	private String stpTable = null;

	private String ddlName = null;

	private String productPrefix = null;

	private int displayOrder;

	private String dataType = null;

	private String helpURL = null;

	private int attribute = -1;

	private String tableId = null;

	private String tableZeroVariation = null;

	private String brccFilter = null;

	private int displayLength;

	private String controlColumnName = null;

	public DataNameData(String stpTable, String ddlName, String productPrefix,
			int displayOrder, String dataType, int attribute, String tableId,
			String tableZeroVariation, String brccFilter, String helpURL, int displayLength,
			String controlColumnName) 
	{
		this.stpTable = stpTable;
		this.ddlName = ddlName;
		this.productPrefix = productPrefix;
		this.dataType = dataType;
		this.attribute = attribute;
		this.displayOrder = displayOrder;
		this.displayLength = displayLength;

		/**
		 * Null fields are made as 'null' Strings while assigning to table
		 * descriptor (passing all datatypes as strings) and while reading back
		 * null strings should be read as Null
		 */

		if (tableId != null && !tableId.trim().equals("null"))
			this.tableId = tableId;

		if (tableZeroVariation != null
				&& !tableZeroVariation.trim().equals("null"))
			this.tableZeroVariation = tableZeroVariation;

		if (brccFilter != null && !brccFilter.trim().equals("null"))
			this.brccFilter = brccFilter;

		if (helpURL != null && !helpURL.trim().equals("null"))
			this.helpURL = helpURL;

		if (controlColumnName != null && !controlColumnName.trim().equals("null"))
			this.controlColumnName = controlColumnName;
	}

	public int getAttribute() {
		return attribute;
	}

	public String getBrccFilter() {
		return brccFilter;
	}

	public String getDataType() {
		return dataType;
	}

	public String getDdlName() {
		return ddlName;
	}

	public int getDisplayOrder() {
		return displayOrder;
	}

	public String getHelpURL() {
		return helpURL;
	}

	public String getProductPrefix() {
		return productPrefix;
	}

	public String getStpTable() {
		return stpTable;
	}

	public String getTableId() {
		return tableId;
	}

	public String getTableZeroVariation() {
		return tableZeroVariation;
	}

	public int getDisplayLength() {
		return displayLength;
	}

	public String getControlColumnName()
	{
		return controlColumnName;
	}

	public void print() {
		System.out.println("ddlName " + ddlName);
		System.out.println("productPrefix " + productPrefix);
		System.out.println("displayOrder " + displayOrder);
		System.out.println("dataType " + dataType);
		System.out.println("helpURL " + helpURL);
		System.out.println("attribute " + attribute);
		System.out.println("tableId " + tableId);
		System.out.println("tableZeroVariation " + tableZeroVariation);
		System.out.println("brccFilter " + brccFilter);
		System.out.println("displayLength " + displayLength);
		System.out.println("controlColumnName " + controlColumnName);
	}

    public void setDataType(String dataType) {
        this.dataType = dataType;
    }
}