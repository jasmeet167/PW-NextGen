/*
 * THIS PROGRAM IS THE PROPERTY OF CSC FINANCIAL SERVICES GROUP. IT MAY NOT BE
 * COPIED IN WHOLE OR IN PART WITHOUT THE EXPRESS WRITTEN CONSENT OF CSC
 * FINANCIAL SERVICES GROUP.
 */

package com.csc.fsg.life.pw.io;

import java.util.*;

import com.csc.fsg.life.pw.PolicyConstants;
import com.csc.fsg.life.pw.util.Constants;
import com.csc.fsg.life.pw.avm.AVManager;
import com.csc.fsg.life.pw.environment.*;

/* Modifications: T0091, ENH961,T0120, ENH1043 */

public class EntireTableFilterContext extends WizObject {

	private String _company;
	private Hashtable<String, String> _companyCodesandNames;
	private String _table;
	private List<String> _tableList = new ArrayList<String>();;
	private Environment environment;
	
	public Hashtable<String, String> get_tableDDLNames() {
		return get_tableDDLNames(environment.getId(), _company);
	}

	public Hashtable<String, String> get_tableDDLNames(String envId, String companyCode) {
		return EntireTableFilterSpecTableManager.getInstance()
		        .getTableDDLNames(envId, companyCode);
	}

	public void initalize_companyCodes() throws Exception {
		_companyCodesandNames = environment.getAssistent()
		        .getCompanyCodesAndNames();
	}

	public String toStreamETC() throws Exception {

		if ((environment.getId() == null) | (_company == null) | (_table == null))
			throw new Exception("Environment/Company/Table Cannot be null");
		
		
		int tableAuth = Constants.NODE_ATTR_INQUIRY;
//		if (user.isPermitted(environment.getId(), null, PolicyConstants.UPDATE))
			tableAuth = Constants.NODE_ATTR_UPDATE;
		
		
		StringBuffer sb = new StringBuffer();
		
		sb.append("0");
		sb.append("\t");
		sb.append("0");
		sb.append("\t");
		sb.append(0);
		sb.append("\t");
		sb.append(tableAuth);
		sb.append("\t");
		sb.append(environment.getId());
		sb.append("\n");
		sb.append("0");
		sb.append("\t");
		sb.append(1);
		sb.append("\t");
		sb.append(3);
		sb.append("\t");
		
		tableAuth = Constants.NODE_ATTR_INQUIRY;
//		if (user.isPermitted(environment.getId(), _company, PolicyConstants.UPDATE))
			tableAuth = Constants.NODE_ATTR_UPDATE;
		
		sb.append(tableAuth);
		sb.append("\t");
		sb.append(get_companyCodesAndNames().get(_company));
		sb.append("\t");
		sb.append(_company);
		sb.append("\n");

		for(String tableDDL: _tableList){
			String tableId = tableDDL.substring(tableDDL.length() - 4, tableDDL.length() - 1);
			String label = AVManager.getTableName(environment.getId(), tableDDL);
			sb.append(prepareString(tableDDL, tableId, label));
		}
		return sb.toString();
	}

	public Hashtable<String, String> get_companyCodesAndNames() {
		return _companyCodesandNames;
	}

//	public EntireTableFilterContext(IRequest reqObj) throws Exception {
//		HashMap req = reqObj.getRequest();
//		String envId = getRequestParam(req, "environment");
//		environment = EnvironmentManager.getInstance().getEnvironment(envId);
//		_company = getRequestParam(req, "company");
//		_table = getRequestParam(req, "table");
//		if(_table!= null)
//			parseTableList(_table);
//		user = reqObj.getUser();
//		initalize_companyCodes();
//
//	}
	
	public EntireTableFilterContext(Environment environment, String company, String _table) throws Exception {
//		HashMap req = reqObj.getRequest();
//		String envId = getRequestParam(req, "environment");
//		environment = EnvironmentManager.getInstance().getEnvironment(envId);
		this.environment = environment;
		this._company = company;
		this._table = _table;
		if(_table!= null)
			parseTableList(_table);
//		user = reqObj.getUser();
		initalize_companyCodes();

	}

	private String prepareString(String tableDDL, String tableId,String label ){
		
		int tableAuth = Constants.NODE_ATTR_INQUIRY;
//		if (user.isPermitted(environment.getId(), _company,tableId, PolicyConstants.UPDATE)) {
			// ENH1043 - ETV update requires UPDATE and UPDATE_ETV
//			if (user.isPermitted(environment.getId(), _company,tableId, PolicyConstants.UPDATE_ETV))
				tableAuth = Constants.NODE_ATTR_UPDATE;
//		}
		
		StringBuffer sb = new StringBuffer();
		sb.append("0");
		sb.append("\t");
		sb.append(2);
		sb.append("\t");
		sb.append(12);
		sb.append("\t");
		sb.append(tableAuth);
		sb.append("\t");
		sb.append(tableDDL+"-"+label);
		sb.append("\t");
		sb.append(tableDDL);
		sb.append("\t");
		sb.append(tableId);
		sb.append("\n");
		return sb.toString();
	}
	
	private void parseTableList(String _table){
		StringTokenizer tkn = new StringTokenizer(_table, "\t");
		while (tkn.hasMoreTokens()) {
			String s = tkn.nextToken().trim();
			if (s.length() > 0) {
				if (!_tableList.contains(s)) {
					_tableList.add(s);
				}
			}
		}
	}

	

}
