/*
 * THIS PROGRAM IS THE PROPERTY OF CSC FINANCIAL SERVICES GROUP. IT MAY NOT BE
 * COPIED IN WHOLE OR IN PART WITHOUT THE EXPRESS WRITTEN CONSENT OF CSC
 * FINANCIAL SERVICES GROUP.
 */

package com.csc.fsg.life.pw.web.io;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Hashtable;
import java.util.Iterator;

import org.apache.commons.logging.Log;


import com.csc.fsg.life.pw.common.util.Utils;
import com.csc.fsg.life.pw.web.avm.AVManager;
import com.csc.fsg.life.pw.web.environment.EnvironmentManager;
import com.csc.fsg.life.pw.web.log.PWServerLogManager;

/* Modifications: T0091, T0129 */

/**
 * Class EntireTableFilterSpecTableManager
 * 
 * @author CSC-FSG,E.Hartford
 * @version PW 2.0 , 09-24-2002
 */

public class EntireTableFilterSpecTableManager extends
        com.csc.fsg.life.pw.web.io.WizObject {

	static Log _log = PWServerLogManager
	        .getLog(EntireTableFilterSpecTableManager.class.getName());

	private Hashtable<String, Boolean> _tableManager = new Hashtable<String, Boolean>(135);

	private static EntireTableFilterSpecTableManager _entTblFilter = null;

	// private Connection sourceCx = null;

	private EntireTableFilterSpecTableManager() {
	}

	/**
	 * Method getInstance
	 * 
	 * @return
	 */

	public static synchronized EntireTableFilterSpecTableManager getInstance() {

		if (_entTblFilter == null) {
			_entTblFilter = new EntireTableFilterSpecTableManager();
		}
		return _entTblFilter;
	}

	private boolean isTableDisplayable(String envId, String compCode,
	        String tableDDLName) {

		boolean isDisp = false;

		try {
			isDisp = _tableManager.get(envId.trim()
			        + compCode.trim() + tableDDLName.trim()).booleanValue();
		} catch (NullPointerException ne) {
			isDisp = false;
		}

		if (isDisp) {
			return true;
		} else {
			return putInTableManager(envId, compCode, tableDDLName);
		}
	}

	private void addToTableManager(String envId, String compCode,
	        String tableDDLName, boolean result) {

		_log.debug("Added Key\t" + envId.trim() + compCode.trim()
		        + tableDDLName.trim() + "\tValue\t" + Boolean.valueOf(result));
		_tableManager.put(envId.trim() + compCode.trim()
		        + tableDDLName.trim(), Boolean.valueOf(result));
	}

	private boolean putInTableManager(String envId, String compCode,
	        String tableDDLName) {

		boolean canInclude = false;
		Statement statement = null;
		ResultSet rs = null;

		canInclude = true; // rs.next();

		addToTableManager(envId, compCode, tableDDLName, canInclude);
		Utils.closeResultSet(rs);
		Utils.closeStatement(statement);
		return canInclude;
	}

	/**
	 * Method getTableDDLNames
	 * 
	 * @param envSchemaName
	 * @param companyCode
	 * @return
	 */

	public Hashtable<String, String> getTableDDLNames(String envId, String companyCode) {

		Hashtable<String, String> tableDDLNames = null;
		Iterator<TableDescriptor> iterator = null;

		
		

			tableDDLNames = new Hashtable<String, String>(150);
			Hashtable<String, TableDescriptor> tbD = EnvironmentManager.getInstance().getEnvironment(envId).getTableDescMgr()
			        .getTableDescriptors();

			iterator = tbD.values().iterator();
			String ddlName = null;
			String transName = null;

			while (iterator.hasNext()) {
				ddlName = (iterator.next()).getDDLName();
				if (isTableDisplayable(envId, companyCode, ddlName)) {
					transName = AVManager.getTableName(envId, ddlName);
					if (transName != null) {
						tableDDLNames.put(ddlName, transName);
					} else {
						tableDDLNames.put(ddlName, ddlName);
					}
				}
			}
		
	
		return tableDDLNames;
	}

}
