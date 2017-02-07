package com.csc.fsg.life.sbe.jdbcconfig;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class DataSource {

	private String name;
	private String jndiname;
	private String databasename;
	private String servername;
	private String portnumber;
	private String userid;
	private String password;
	private List<ResourceProperty> resourceproperties = new ArrayList();

	public void addResourceproperty(ResourceProperty res) {
		resourceproperties.add(res);
	}

	public String getName() {
		return name;
	}

	public List<ResourceProperty> getResourceproperties() {
		return resourceproperties;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getJndiname() {
		return jndiname;
	}

	public void setJndiname(String jndiname) {
		this.jndiname = jndiname;
	}

	public String getDatabasename() {
		return databasename;
	}

	public void setDatabasename(String databasename) {
		this.databasename = databasename;
	}

	public String getServername() {
		return servername;
	}

	public void setServername(String servername) {
		this.servername = servername;
	}

	public String getPortnumber() {
		return portnumber;
	}

	public void setPortnumber(String portnumber) {
		this.portnumber = portnumber;
	}

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		StringBuffer sb = new StringBuffer("  ");
		sb.append("name: " + name + ", ");
		sb.append("jndiname: " + jndiname + ", ");
		sb.append("databasename: " + databasename + ", ");
		sb.append("servername: " + servername + ", ");
		sb.append("portnumber: " + portnumber + ", ");
		sb.append("userid: " + userid + ", ");
		sb.append("password: " + password + "\n");

		// print out datasource information
		sb.append("  resourcesproperties:" + "\n");
		for (Iterator<ResourceProperty> itt = resourceproperties.iterator(); itt.hasNext();) {
			sb.append("  " + itt.next());
		}
		return sb.toString();
	}
}
