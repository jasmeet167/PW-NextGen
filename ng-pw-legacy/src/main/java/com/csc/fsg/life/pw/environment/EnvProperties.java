
package com.csc.fsg.life.pw.environment;

import javax.sql.DataSource;

/* Modifications:  T0113*/

public class EnvProperties {
	
	/** Common properties for all Environments */
	
	private boolean initUnlockWIPRows = true;
	private boolean initReload = true;
	private int pagingSize = 1000;
	private int packetSize = 20000;
	private String applSchema = "APPL";
	private DataSource applDatasource = null;
	private String avmEnvironment = null;

	public boolean isInitReload() {
		return initReload;
	}
	public void setInitReload(boolean initReload) {
		this.initReload = initReload;
	}
	public boolean isInitUnlockWIPRows() {
		return initUnlockWIPRows;
	}
	public void setInitUnlockWIPRows(boolean initUnlockWIPRows) {
		this.initUnlockWIPRows = initUnlockWIPRows;
	}
	public int getPacketSize() {
		return packetSize;
	}
	public void setPacketSize(int packetSize) {
		this.packetSize = packetSize;
	}
	public int getPagingSize() {
		return pagingSize;
	}
	public void setPagingSize(int pagingSize) {
		this.pagingSize = pagingSize;
	}
	public String getApplSchema() {
		return applSchema;
	}
	public void setApplSchema(String applSchema) {
		this.applSchema = applSchema;
	}
	public DataSource getApplDatasource() {
		return applDatasource;
	}
	public void setApplDatasource(DataSource applDatasource) {
		this.applDatasource = applDatasource;
	}
	public String getAvmEnvironment() {
		return avmEnvironment;
	}
	public void setAvmEnvironment(String avmEnvironment) {
		this.avmEnvironment = avmEnvironment;
	}

}
