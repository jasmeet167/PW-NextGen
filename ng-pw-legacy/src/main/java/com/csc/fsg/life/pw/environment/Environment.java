
package com.csc.fsg.life.pw.environment;

import java.sql.Connection;

import javax.sql.DataSource;

import com.csc.fsg.life.pw.avm.AVManager;
import com.csc.fsg.life.pw.io.TableDescriptorManager;
import com.csc.fsg.life.pw.util.DBConnMgr;

/* Modifications: T0103, T0113  */

public class Environment implements IEnvironment{
	
	private static final byte HIGHVALUE_BYTE_9F = (byte) 0x9f;
	private static final byte HIGHVALUE_BYTE_FF = (byte) 0xff;
	private byte HIGHVALUE_BYTE_RECEIVED = (byte) 0x9f;
	
	/** from application config bean */
	private String id = null;
	private String description = null;
	private String schema = null;
	private boolean nexEnv = false;
	private DataSource brDatasource = null;
	private String highValueEncoding = null;
	
	/** from env props */
	private EnvProperties props = null;
	
	/** from Product Manager bean  */
	private String productSystem = "WMA";
	
	/** runtime set */
	private boolean isExtAuditEnv = false;
	
	/** initialized on creation */
	private EnvironmentAssistent assistent = null;
	private TableDescriptorManager tableDescMgr = null;
		
		
	public DataSource getApplDatasource() {
		return getProps().getApplDatasource();
	}
		
	public EnvProperties getProps() {
		return props;
	}
	public void setProps(EnvProperties props) {
		this.props = props;
	}
	
	public DataSource getBrDatasource() {
		return brDatasource;
	}
	public void setBrDatasource(DataSource brDatasource) {
		this.brDatasource = brDatasource;
	}
	public String getHighValueEncoding() {
		return highValueEncoding;
	}
	public void setHighValueEncoding(String highValueEncoding) {
		this.highValueEncoding = highValueEncoding;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getSchema() {
		return schema;
	}
	public void setSchema(String schema) {
		this.schema = schema;
	}
		
	public TableDescriptorManager getTableDescMgr() {
		return tableDescMgr;
	}
	
	public void setTableDescMgr(TableDescriptorManager tableDescMgr) {
		this.tableDescMgr = tableDescMgr;
	}
	
	public String getApplSchema() {
		return getProps().getApplSchema();
	}
	
	public void initialize() throws Exception{
		
		Connection applConn = null;
		Connection brConn = null;
		try{
			
			brConn = DBConnMgr.getInstance().getConnection(id,DBConnMgr.BUSINESS_RULES);
				
			// refactor plan key: set td mgr before highvalue check
			setTableDescMgr(TableDescriptorManager.getInstance(getProductSystem()));
//			
//			HIGHVALUE_BYTE_RECEIVED = HighValueHandler.retrieveHighValue(brConn,getSchema(),getId());
//			
//			boolean reloadAppl = getProps().isInitReload();
//			boolean unlockWIP = getProps().isInitUnlockWIPRows();
//			
//			if (reloadAppl || unlockWIP)
//				applConn = DBConnMgr.getInstance().getConnection(id,DBConnMgr.APPL);
//			
//			if (reloadAppl)
//				EnvironmentLoader.reloadLocal(brConn,applConn,this,null);
//			
//			if (unlockWIP)
//				EnvironmentLoader.unLockRows(applConn,this);

			assistent = new EnvironmentAssistent(this);
			
			AVManager.getTablesInfo(id);
	
		} finally {
			DBConnMgr.getInstance().releaseConnection(brConn);
			DBConnMgr.getInstance().releaseConnection(applConn);
		}
	}
	
	public EnvironmentAssistent getAssistent() {
		return assistent;
	}
	
	public String getProductSystem() {
		return productSystem;
	}
	
	public void setProductSystem(String productSystem) {
		this.productSystem = productSystem;
	}
	public boolean isExtAuditEnv() {
		return isExtAuditEnv;
	}
	public void setExtAuditEnv(boolean isExtAuditEnv) {
		this.isExtAuditEnv = isExtAuditEnv;
	}
	public boolean isNexEnv() {
		return nexEnv;
	}
	public void setNexEnv(boolean nexEnv) {
		this.nexEnv = nexEnv;
	}
	
	public byte getHIGHVALUE_BYTE_RECEIVED() {
		return HIGHVALUE_BYTE_RECEIVED;
	}
	
	public void setHIGHVALUE_BYTE_RECEIVED(byte highvalue_byte_received) {
		HIGHVALUE_BYTE_RECEIVED = highvalue_byte_received;
	}
	
	public byte getHIGHVALUE_BYTE_TO_SEND() {
		//return HIGHVALUE_BYTE_TO_SEND;
		if (isNexEnv())
			return HIGHVALUE_BYTE_FF;
		else
			return HIGHVALUE_BYTE_9F;
	}
	
	public String getDescription() {
		if (description==null)
			return getId();
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	public EnvironmentElement getEnvironmentElement(){
		return new EnvironmentElement(0,getDescription(),getId(),null,null,null);
	}
	
}
