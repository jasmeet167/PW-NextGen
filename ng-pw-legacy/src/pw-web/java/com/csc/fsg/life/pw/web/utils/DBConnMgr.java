package com.csc.fsg.life.pw.web.utils;


import java.sql.*;
import java.util.*;

import javax.sql.DataSource;

import org.apache.commons.logging.Log;

import com.csc.fsg.life.exceptions.WrapperException;
import com.csc.fsg.life.pw.common.log.PWCommonLogManager;
import com.csc.fsg.life.pw.common.util.Utils;
import com.csc.fsg.life.pw.web.environment.*;

/* Modifications: T0091, T0113, CCCV-E768 */

public final class DBConnMgr {

	public static final int BUSINESS_RULES = 1;
	public static final int APPL = 2;
		
	private static Log _log = PWCommonLogManager.getLog();
	
	private static DBConnMgr _instance;

	public static HashMap<String, ConnDetails> connMapObjects = new HashMap<String, ConnDetails>();

	private DBConnMgr() {
	}

	public synchronized static DBConnMgr getInstance() {
		if (_instance == null) {
			_instance = new DBConnMgr();
		}
		return _instance;
	}

	public Connection getConnection(String envId,int destination) throws SQLException {
		DataSource ds = getDataSource(envId,destination);
		if (ds == null)
			throw new IllegalArgumentException("No DataSource available for: "
			        + envId);

		Connection conn = getValidConnection(envId, destination, ds);
		//trackConnectionsDetails(conn.hashCode(),conn.getMetaData().getURL(), false);
		return conn;
	}

	public void releaseConnection(Connection conn) {
		try {
			if (conn != null && !conn.isClosed()) {
				//trackConnectionsDetails(conn.hashCode(),conn.getMetaData().getURL(), true);
				conn.setAutoCommit(true);
				conn.close();
			} else {
				//_log.debug("Connection is already closed");
			}

		} catch (Exception e) {
			_log.error("releaseConnection error", e);
		}
	}

	//private static HashMap dataSources = new HashMap();

	public DataSource getDataSource(String envId,int destination) {
		Environment env = EnvironmentManager.getInstance().getEnvironment(envId);
		switch (destination) {
			case BUSINESS_RULES:
				return env.getBrDatasource();
			case APPL:
				return env.getApplDatasource();
			default:
				return null;
		}
	}

	private Connection getValidConnection(String envId, int destination,DataSource ds)
	        throws SQLException {

		Connection conn = null;

		for (int tries = 0; tries < 2; tries++) {
			conn = ds.getConnection();

			if (!isConnectionStale(envId, destination,conn))
				break;

			if (tries == 1)
				throw new IllegalStateException(
				        "Unable to get valid connection for: " + envId);

//			System.out.println("INFO: Stale connection exception caught for: "
//			        + envId + ", retrying...");

			try {
				Thread.sleep(1000); // Wait before you try again
			} catch (InterruptedException e) {
				// Ignore; irrelevant
			}
		}

		conn.setAutoCommit(true);

		return conn;
	}

	private boolean isConnectionStale(String envId, int destination, Connection conn) {
		Statement stmt = null;
		ResultSet rs = null;
		try {
			Environment env = EnvironmentManager.getInstance().getEnvironment(envId);
			stmt = conn.createStatement();

			if (destination==APPL)
				rs = stmt.executeQuery("select ENVIRONMENT from " + env.getApplSchema()
				        + ".AUDIT where package_id = '1'");
			else
				rs = stmt.executeQuery("select company_code from " + env.getSchema()
				        + ".T001X where company_code = '1'");

			return false;
		} catch (SQLException e) {
			// Websphere specific code, but reluctant to introduce a code
			// dependency, hence this
			// hack.
			if (e.getClass().getName().toLowerCase().indexOf("stale") >= 0)
				return true;

			//e.printStackTrace();
			_log.info(Utils.getStackTrace(e));
			throw WrapperException.wrap(e);
		}finally{
			Utils.closeResultSet(rs);
			Utils.closeStatement(stmt);
		}
	}
	
	private void trackConnectionsDetails(int hashcode ,String url, boolean released) {
	//	if (!PWConfigBean.getInstance().isTrackConn())
		//	return;
		
		if (released)
		{
			ConnDetails connDetails = connMapObjects.get(hashcode+"");
			if (connDetails==null){
				_log.debug("Connection Details Object not found");
				return;
			}
			connDetails.setReleased(true);
		}else{
			Object hashCodeExists = connMapObjects.put(hashcode+"",new ConnDetails(url,hashcode));
			if (hashCodeExists!=null)
				_log.debug(hashCodeExists+" already exist");
		}
	}
	
	public void clearConnMap(){
		connMapObjects.clear();
	}
	
	public StringBuffer getConnDetails(){
		StringBuffer sb = new StringBuffer();
		Iterator<ConnDetails> iter = connMapObjects.values().iterator();
		while (iter.hasNext())
			sb.append(iter.next().getStatus());
		return sb;
	}
	
}
