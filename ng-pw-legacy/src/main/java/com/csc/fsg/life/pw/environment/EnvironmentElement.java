/*
 * THIS PROGRAM IS THE PROPERTY OF CSC FINANCIAL SERVICES GROUP. IT MAY NOT BE
 * COPIED IN WHOLE OR IN PART WITHOUT THE EXPRESS WRITTEN CONSENT OF CSC
 * FINANCIAL SERVICES GROUP.
 */

package com.csc.fsg.life.pw.environment;

import java.io.Serializable;
import java.util.*;

/**
 * Class EnvironmentElement
 * 
 * @author CSC-FSG,E.Hartford
 * @version PW 2.0 , 09-24-2002
 */
/** Modifications ,T0120, T0129 */
public class EnvironmentElement implements Serializable{

	private int environmentId = 0;

	private String environmentName = "";

	private String environmentSchema = "";

	private String dbName = "";

	private String dbAlias = "";

	private Set<String> companies = new HashSet<String>();
	
	public Set<String> getCompanies() {
		return companies;
	}

	public void setCompanies(Set<String> companies) {
		this.companies = companies;
	}


	/**
	 * Constructor EnvironmentElement
	 * 
	 * @param envId
	 * @param envName
	 * @param envSchema
	 */

	public EnvironmentElement(int envId, String envName, String envSchema) {

		environmentId = envId;
		environmentName = envName;
		environmentSchema = envSchema;
	}

	public EnvironmentElement(int envId, String envName, String envSchema,
	        String dbName, String dbAlias, String spSchema) {

		environmentId = envId;
		environmentName = envName;
		environmentSchema = envSchema;
		this.dbName = dbName;
		this.dbAlias = dbAlias;
	
	}

	public int getEnvironmentId() {
		return environmentId;
	}

	/**
	 * Method getEnvironmentName
	 * 
	 * @return
	 */

	public String getEnvironmentName() {
		return environmentName;
	}

	/**
	 * Method getEnvironmentSchema
	 * 
	 * @return
	 */

	public String getEnvironmentSchema() {
		return environmentSchema;
	}

	/**
	 * Method getHighestAuthority
	 * 
	 * @return
	 */

	// public int getHighestAuthority() {
	// return _highestAuthority;
	// }
	/**
	 * Method setHighestAuthority
	 * 
	 * @param value
	 */

//	public void setHighestAuthority(int value) {
//		_highestAuthority = value;
//	}

	// these are just duplicates. Avoid using these two methods

	/**
	 * Method getName
	 * 
	 * @return
	 */

	public String getName() {
		return environmentName;
	}

	/**
	 * Method getSchema
	 * 
	 * @return
	 */

	public String getSchema() {
		return environmentSchema;
	}

	/**
	 * Method toTabbedString
	 * 
	 * @return
	 */

	public String toTabbedString() {

		String tab = "\t";
		StringBuffer sb = new StringBuffer();

		sb.append(environmentId).append(tab);
		sb.append(environmentName).append(tab);
		sb.append(environmentSchema).append(tab);
		sb.append(dbName).append(tab);
		sb.append(dbAlias).append(tab);
		sb.append("");
		return sb.toString();
	}

	/**
	 * Method toString
	 * 
	 * @return
	 */

	public String toString() {
		return environmentName.trim();
	}

	/**
	 * Method equals
	 * 
	 * @param object
	 * @return
	 */

	public boolean equals(Object object) {

		if ((object instanceof EnvironmentElement)
		        && (this.getEnvironmentName().compareTo(
		                ((EnvironmentElement) object).getEnvironmentName()) == 0)) {
			return true;
		} else {
			return false;
		}
	}
	
	public int hashCode() {
		return getEnvironmentName().hashCode();
	}

	public String getdbName() {
		return dbName;
	}

	public void setdbName(String dbName) {
		this.dbName = dbName;
	}

	/**
	 * @return
	 */
	public String getDbAlias() {
		return dbAlias;
	}

	/**
	 * @param string
	 */
	public void setDbAlias(String string) {
		dbAlias = string;
	}



}
