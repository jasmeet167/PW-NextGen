/*
 * THIS PROGRAM IS THE PROPERTY OF CSC FINANCIAL SERVICES GROUP. IT MAY NOT BE
 * COPIED IN WHOLE OR IN PART WITHOUT THE EXPRESS WRITTEN CONSENT OF CSC
 * FINANCIAL SERVICES GROUP.
 */

package com.csc.fsg.life.pw.web.io;

import java.util.ArrayList;

/* Modifications: T0091 */

/**
 * Class QueryFilter
 * 
 * @author CSC-FSG,E.Hartford
 * @version PW 2.0 , 09-24-2002
 */

public class QueryFilter {

	private String envSchema = null;

	private String companyCode = null;

	private ArrayList<String> ddlNames = null;

	private ArrayList<String> productPrefixes = null;

	/**
	 * Constructor WIPTableFilter
	 */

	public QueryFilter() {
	}

	/**
	 * Method getEnvironment
	 * 
	 * @return
	 */

	public String getEnvSchema() {
		return envSchema;
	}

	/**
	 * Method getProductPrefixes
	 * 
	 * @return Vector productPrefixes
	 */

	public ArrayList<String> getProductPrefixes() {
		return productPrefixes;
	}

	/**
	 * Method getDdlNames
	 * 
	 * @return
	 */

	public ArrayList<String> getDdlNames() {
		return ddlNames;
	}

	/**
	 * Returns the companyCode.
	 * 
	 * @return String companyCode
	 */
	public String getCompanyCode() {
		return companyCode;
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
	 * Sets the companyCodes.
	 * 
	 * @param companyCodes
	 *            The companyCodes to set
	 */
	public void setCompanyCode(String companyCode) {
		this.companyCode = companyCode;
	}

	/**
	 * Method setProductPrefixes
	 * 
	 * @param productPrefixes
	 */

	public void setProductPrefixes(ArrayList<String> productPrefixes) {
		this.productPrefixes = productPrefixes;
	}

	/**
	 * Method setProductPrefix
	 * 
	 * @param productPrefix
	 */

	public void setProductPrefix(String productPrefix) throws Exception {
		if (productPrefixes != null) {
			throw new Exception("productPrefixes already set");
		}
		productPrefixes = new ArrayList<String>();
		productPrefixes.add(productPrefix);
	}

	/**
	 * Method setDdlNames
	 * 
	 * @param ddlNames
	 */

	public void setDdlNames(ArrayList<String> ddlNames) {
		this.ddlNames = ddlNames;
	}

	/**
	 * Method setDdlName
	 * 
	 * @param ddlName
	 */

	public void setDdlName(String ddlName) throws Exception {
		if (ddlNames != null) {
			throw new Exception("ddlNames already set");
		}
		ddlNames = new ArrayList<String>();
		ddlNames.add(ddlName);
	}

	/**
	 * Method toString
	 * 
	 * @return
	 */

	public String toString() {

		StringBuffer sb = new StringBuffer();

		sb.append("QueryFilter: ");

		sb.append("envSchema: ");
		if (envSchema != null) {
			sb.append(envSchema).append("\t");
		} else {
			sb.append("NULL").append("\t");
		}

		sb.append("companyCode: ");
		if (companyCode != null) {
			sb.append(companyCode).append("\t");
		} else {
			sb.append("NULL").append("\t");
		}

		sb.append("productPrefixes: ");
		if (productPrefixes != null) {
			sb.append(productPrefixes.toString()).append("\t");
		} else {
			sb.append("NULL").append("\t");
		}

		sb.append("ddlNames: ");
		if (ddlNames != null) {
			sb.append(ddlNames.toString()).append("\t");
		} else {
			sb.append("NULL").append("\t");
		}

		return sb.toString();
	}

}
