package com.csc.fsg.life.pw.web.avm;

public class AVKey {
	private String environment;

	private String company;

	private String product;

	private String table;

	private String trxCode;

	private String fieldName;

	private boolean isKey = false;

	// for table constraints
	private String subset;

	public AVKey() {
	}

	public AVKey(String environment, String company, String product,
	        String table, String trxCode, String field) {
		this.environment = environment;
		this.company = company;
		if (product != null && product.equalsIgnoreCase("C")) {
			this.product = null;
		} else {
			this.product = product;
		}
		this.table = table;
		this.trxCode = trxCode;
		this.fieldName = field;
	}

	public AVKey(String environment, String company, String product,
	        String table, String subset, String trxCode, String field) {
		this.subset = subset;
		this.environment = environment;
		this.company = company;
		if (product != null && product.equalsIgnoreCase("C")) {
			product = null;
		} else {
			this.product = product;
		}
		this.table = table;
		this.trxCode = trxCode;
		this.fieldName = field;
	}

	public String getCompany() {
		return company;
	}

	public String getEnvironment() {
		return environment;
	}

	public String getFieldName() {
		return fieldName;
	}

	public String getProduct() {
		return product;
	}

	public String getTable() {
		return table;
	}

	public String getTrxCode() {
		return trxCode;
	}

	public void setCompany(String string) {
		company = string;
	}

	public void setEnvironment(String string) {
		environment = string;
	}

	public void setFieldName(String string) {
		fieldName = string;
	}

	public void setProduct(String string) {
		if (string != null && string.equalsIgnoreCase("C")) {
			this.product = null;
		} else {
			this.product = string;
		}

	}

	public void setTable(String string) {
		table = string;
	}

	public void setTrxCode(String string) {
		trxCode = string;
	}

	public String getSubset() {
		return subset;
	}

	public void setSubset(String string) {
		subset = string;
	}

	/**
	 * @return
	 */
	public boolean isKeyField() {
		return isKey;
	}

	/**
	 * @param b
	 */
	public void setKey(boolean b) {
		isKey = b;
	}

	public String toString(){
		return "Environment: "+environment+"\tCompany: "+company+"\tProduct: "+product+"\tSubset: "+subset+"Table: "+table+"Column: "+fieldName;
	}
}
