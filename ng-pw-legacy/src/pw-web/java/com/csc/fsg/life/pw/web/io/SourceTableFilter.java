/*
 * THIS PROGRAM IS THE PROPERTY OF CSC FINANCIAL SERVICES GROUP. IT MAY NOT BE
 * COPIED IN WHOLE OR IN PART WITHOUT THE EXPRESS WRITTEN CONSENT OF CSC
 * FINANCIAL SERVICES GROUP.
 */

package com.csc.fsg.life.pw.web.io;

import java.util.*;

/* Modifications: T0091 */

/**
 * Class SourceTableFilter
 * 
 * @author CSC-FSG,E.Hartford
 * @version PW 2.0 , 09-24-2002
 */

public class SourceTableFilter {

	private String envSchema;

	private String tableName;

	private String subsetName;

	private Vector<String> companies;

	private String productPrefix;

	private String view;

	/**
	 * Constructor SourceTableFilter
	 */

	public SourceTableFilter() { /* super(); */
	}

	/**
	 * Method getEnvSchema
	 * 
	 * @return
	 */

	public String getEnvSchema() {
		return envSchema;
	}

	/**
	 * Method getTableName
	 * 
	 * @return
	 */

	public String getTableName() {
		return tableName;
	}

	/**
	 * Method getSubsetName
	 * 
	 * @return
	 */

	public String getSubsetName() {
		return subsetName;
	}

	/**
	 * Method getCompanies
	 * 
	 * @return
	 */

	public Vector<String> getCompanies() {
		return companies;
	}

	/**
	 * Method getProductPrefix
	 * 
	 * @return
	 */

	public String getProductPrefix() {
		return productPrefix;
	}

	/**
	 * Method setEnvSchema
	 * 
	 * @param envSchema
	 */

	public void setEnvSchema(String envSchema) {
		this.envSchema = envSchema;
	}

	/**
	 * Method setTableName
	 * 
	 * @param tableName
	 */

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	/**
	 * Method setSubsetName
	 * 
	 * @param subsetName
	 */

	public void setSubsetName(String subsetName) {
		this.subsetName = subsetName;
	}

	/**
	 * Method setCompanies
	 * 
	 * @param companies
	 */

	public void setCompanies(Vector<String> companies) {
		this.companies = companies;
	}

	/**
	 * Method setProductPrefix
	 * 
	 * @param productPrefix
	 */

	public void setProductPrefix(String productPrefix) {
		this.productPrefix = productPrefix;
	}

	/**
	 * Method toString
	 * 
	 * @return
	 */

	public String toString() {

		StringBuffer sb = new StringBuffer();

		sb.append("ENVIRONEMT: ");
		if (envSchema != null) {
			sb.append(envSchema).append("\t");
		} else {
			sb.append("NULL").append("\t");
		}

		sb.append("TABLE NAME: ");
		if (tableName != null) {
			sb.append(tableName).append("\t");
		} else {
			sb.append("NULL").append("\t");
		}

		sb.append("SUBSET NAME: ");
		if (subsetName != null) {
			sb.append(subsetName).append("\t");
		} else {
			sb.append("NULL").append("\t");
		}

		sb.append("COMPANY CODES: ");
		if (companies != null) {
			sb.append(companies.toString()).append("\t");
		} else {
			sb.append("NULL").append("\t");
		}

		sb.append("PRODUCT PREFIX: ");
		if (productPrefix != null) {
			sb.append(productPrefix).append("\t");
		} else {
			sb.append("NULL").append("\t");
		}

		return sb.toString();
	}

	/**
	 * Returns the view.
	 * 
	 * @return String
	 */
	public String getView() {
		return view;
	}

	/**
	 * Sets the view.
	 * 
	 * @param view
	 *            The view to set
	 */
	public void setView(String view) {
		this.view = view;
	}

}
