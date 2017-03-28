/*
 * THIS PROGRAM IS THE PROPERTY OF CSC FINANCIAL SERVICES GROUP. IT MAY NOT BE
 * COPIED IN WHOLE OR IN PART WITHOUT THE EXPRESS WRITTEN CONSENT OF CSC
 * FINANCIAL SERVICES GROUP.
 */

package com.csc.fsg.life.pw.web.actions.tree;

import java.sql.Connection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.w3c.dom.*;

import com.csc.fsg.life.pw.common.util.Constants;
import com.csc.fsg.life.pw.web.avm.AVManager;
import com.csc.fsg.life.pw.web.environment.*;
import com.csc.fsg.life.pw.web.io.*;
import com.csc.fsg.life.pw.web.config.ProductManager;

/* Modifications: T0091, ENH961 ,T0115,T0120, T0129*/

/**
 * Class CommonTablesWriterThread
 * 
 * @author CSC-FSG,E.Hartford
 * @version PW 2.0 , 09-24-2002
 */

public class CommonTablesWriter {
	private String env = null;

	private String company = null;

	private boolean isWithChanges = true;

	private Node rootNode = null;

	private HashMap rowCounter = null;
	
//	private User user = null;
	
	private Map<String, Integer> wipCounts = null;
	
	public CommonTablesWriter(String env, String company, boolean withChanges,
	        Connection mfConn, Connection wipConn) throws Exception {
		this.company = company;
		this.env = env;
		this.isWithChanges = withChanges;
		rowCounter = new HashMap();

		Environment environment = EnvironmentManager.getInstance().getEnvironment(env);
		rootNode = ProductManager.getInstance()
			.getProduct(environment.getProductSystem()).getCommonTreeRoot();
		
		Set<String> allTableNames = new HashSet<String>();
		getAllTableNames(rootNode, allTableNames);
		environment.getAssistent().initRowCountCache(company, allTableNames, mfConn);

		TableRowCounter counter = new TableRowCounter(environment);
		wipCounts = counter.getWIPCountsByTable(company, wipConn);

		initRowCounts(mfConn, wipConn, rootNode, environment);
	}

	public String getStream() throws Exception {
		StringBuffer ctTree = new StringBuffer();
		writeTables(ctTree, rootNode, 3);
		return ctTree.toString();
	}

	private synchronized void writeTables(StringBuffer ctTree, Node node,
	        int level) throws Exception {

		NodeList list = node.getChildNodes();

		for (int p = 0; p < list.getLength(); p++) {
			Node cnode = list.item(p);
			if (!(cnode instanceof Element))
				continue;
			Element ele = (Element) cnode;

			String tableName = ele.getAttribute("ID");
			String type = ele.getAttribute("TYPE");

			int length = tableName.length();
			String tableId = tableName.substring((length - 4), (length - 1));

			int tableAuth = -1;
			if (type.equals("F")) {
				
				tableAuth = Constants.NODE_ATTR_INQUIRY;
//				if (user.isPermitted(env, company, PolicyConstants.UPDATE))
					tableAuth = Constants.NODE_ATTR_UPDATE;
						
				ctTree.append("999999\t").append(level).append("\t7\t").append(
				        tableAuth).append("\t" + tableName + "\n");
			} else if (type.equals("B")) {
				if (EnvironmentManager.getInstance().getEnvironment(env).getTableDescMgr().getDDLName(tableId) != null) {
					if (rowCounter.get(tableName) != null) {
						
						tableAuth = Constants.NODE_ATTR_INQUIRY;
//						if (user.isPermitted(env, company, tableId, PolicyConstants.UPDATE))
							tableAuth = Constants.NODE_ATTR_UPDATE;
						
						ctTree.append("999999\t").append(level)
						        .append("\t17\t").append(tableAuth)
						        .append("\t");
						ctTree.append(AVManager.getTableName(env, tableName))
						        .append("\t");
						ctTree.append(tableName).append("\t");
						ctTree.append(tableId).append("\n");
					}else{
						continue;
					}
				}
			} else {
				if (EnvironmentManager.getInstance().getEnvironment(env).getTableDescMgr().getDDLName(tableId) != null) {
					if (rowCounter.get(tableName) != null) {
						
						tableAuth = Constants.NODE_ATTR_INQUIRY;
//						if (user.isPermitted(env, company, tableId, PolicyConstants.UPDATE))
							tableAuth = Constants.NODE_ATTR_UPDATE;
						
						ctTree.append("999999\t").append(level)
						        .append("\t13\t").append(tableAuth)
						        .append("\t");
						ctTree.append(AVManager.getTableName(env, tableName))
						        .append("\t");
						ctTree.append(tableName).append("\t");
						ctTree.append(tableId).append("\n");
					}
				}
			}

			writeTables(ctTree, cnode, level + 1);
		}
	}

	private void initRowCounts(Connection mfconn, Connection wipConn,
	        Node node, Environment environment) throws Exception {

		NodeList list = node.getChildNodes();

		for (int p = 0; p < list.getLength(); p++) {

			Node cnode = list.item(p);
			if (!(cnode instanceof Element))
				continue;
			Element ele = (Element) cnode;

			String tableName = ele.getAttribute("ID");
			String type = ele.getAttribute("TYPE");
			if (!type.equals("F")) 
			{
				int rowCount = environment.getAssistent().countRowsForCompany(tableName, company, mfconn);
				
				if (isWithChanges)
				{
					Integer icount = wipCounts.get(tableName);
					if ( icount != null )
						rowCount += icount.intValue();
				}

				if (rowCount > 0) {
					rowCounter.put(tableName, rowCount + "");
				}
			}
			initRowCounts(mfconn, wipConn, cnode, environment);
		}
	}

	private void getAllTableNames(Node node, Set<String> tableNames) throws Exception {

		NodeList list = node.getChildNodes();

		for (int p = 0; p < list.getLength(); p++) {
			Node cnode = list.item(p);
			if (!(cnode instanceof Element))
				continue;
			Element ele = (Element) cnode;

			String tableName = ele.getAttribute("ID");
			String type = ele.getAttribute("TYPE");
			if (!type.equals("F")) {
				tableNames.add(tableName);
			}
			getAllTableNames(cnode, tableNames);
		}
	}
}
