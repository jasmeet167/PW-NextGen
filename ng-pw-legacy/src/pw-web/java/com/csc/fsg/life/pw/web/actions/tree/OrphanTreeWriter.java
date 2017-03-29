
package com.csc.fsg.life.pw.web.actions.tree;

import java.sql.Connection;
import java.util.*;

import org.apache.commons.logging.Log;

import com.csc.fsg.life.pw.common.User;
import com.csc.fsg.life.pw.common.util.Constants;
import com.csc.fsg.life.pw.web.actions.clone.*;
import com.csc.fsg.life.pw.web.avm.AVManager;
import com.csc.fsg.life.pw.web.environment.Environment;
import com.csc.fsg.life.pw.web.environment.EnvironmentManager;
import com.csc.fsg.life.pw.web.io.*;
import com.csc.fsg.life.pw.web.log.PWServerLogManager;

/* Modifications: T0091, ENH961,T0120, T0129, WMA-1209 */

public class OrphanTreeWriter {
	private static Log _log = PWServerLogManager.getLog(OrphanTreeWriter.class
	        .getPackage().getName());
	
	private static final String TAB = "\t";
	private static final String NEW_LINE = "\n";
	
	private String env = null;
	private String company = null;
	private String prefix = null;
	private User user = null;
	private TableDescriptorManager tableDescMgr = null;
		
	public void writeOrphans(String env,String company,String prefix,
	        Connection conn, StringBuffer treeStream) throws Exception {
		
		this.env = env;
		this.company = company;
		this.prefix = prefix;
		this.user = user;
		Environment environment = EnvironmentManager.getInstance().getEnvironment(env);
		tableDescMgr = environment.getTableDescMgr();
		
		int tableAuth = Constants.NODE_ATTR_INQUIRY;
//		if (user.isPermitted(env, company,  PolicyConstants.UPDATE))
			tableAuth = Constants.NODE_ATTR_UPDATE;
		
		treeStream.append("999999" + TAB + "3" + TAB + "16" + TAB).append(
				tableAuth).append(TAB).append("Orphan Subsets").append(TAB);
					treeStream.append(prefix+"*").append(NEW_LINE);
				
		OrphanTreeCreator otc = new OrphanTreeCreator(environment,prefix,conn/*,task*/);
		Node node = otc.getOrphanTree(true);
		TreeMap<String, ArrayList<Node>> nodesHash = groupOrphans(node);
		node = null;
		
		Set<Map.Entry<String, ArrayList<Node>>> entries = nodesHash.entrySet();
		for ( Map.Entry<String, ArrayList<Node>> entry : entries ) {
			String key = entry.getKey();
			String[] tableIdNPrefix = key.split("\\|");
			String tableId = tableIdNPrefix[0];
			String productPrefix = tableIdNPrefix[1];
			String ddlName = tableDescMgr.getDDLName(tableId);
			if ( ddlName == null ) {
				_log.error("writeOrphans: no table descripter for table ID " + tableId);
			} else {
				String tableName = AVManager.getTableName(env, ddlName).trim();
				
				tableAuth = Constants.NODE_ATTR_INQUIRY;
//				if (user.isPermitted(env, company, tableId, PolicyConstants.UPDATE))
					tableAuth = Constants.NODE_ATTR_UPDATE;
				
				treeStream.append("999999" + TAB + "4" + TAB + "26" + TAB).append(
						tableAuth).append(TAB).append(tableName).append(TAB);
						treeStream.append(company).append(TAB);
						treeStream.append(productPrefix).append(TAB);
						treeStream.append(ddlName).append(TAB);
						treeStream.append(tableId).append(NEW_LINE);
				
				ArrayList<Node> nodesList = entry.getValue();
				for (int i=0;i<nodesList.size();i++)
					processNode(nodesList.get(i),treeStream,5,"0");
				
				nodesList.clear();
			}
		}
		
		nodesHash.clear();
		
	}

	private TreeMap<String, ArrayList<Node>> groupOrphans(Node node)	{
		Iterator iter = node.getChildren().iterator();
		TreeMap<String, ArrayList<Node>> nodesHash = new TreeMap<String, ArrayList<Node>>();
		while (iter.hasNext()){
			Node cnode = (Node) iter.next();
			ArrayList<Node> list = nodesHash.get(cnode.getNodeId().trim()+"|"+cnode.getProductPrefix().trim());
			if (list==null)
				list = new ArrayList<Node>();
				list.add(cnode);
			nodesHash.put(cnode.getNodeId().trim()+"|"+cnode.getProductPrefix().trim(),list);
		}
		return nodesHash;
	}

	private void processNode(Node node, StringBuffer treeStream,int level,String parentNodeId) 
			throws Exception{
		
		int tableAuth = Constants.NODE_ATTR_INQUIRY;
//		if (user.isPermitted(env, company, node.getNodeId(), PolicyConstants.UPDATE))
			tableAuth = Constants.NODE_ATTR_UPDATE;
		
		String ddlName = tableDescMgr.getDDLName(node.getNodeId());
		String tableName = AVManager.getTableName(env, ddlName).trim();
		
		String nodeId = "99999";
		
		treeStream.append(nodeId).append(TAB);
		treeStream.append(level).append(TAB);
		if (node.isOrphan())
			treeStream.append("18").append(TAB);
		else
			treeStream.append("19").append(TAB);
		treeStream.append(tableAuth).append(TAB);
		treeStream.append(tableName).append(TAB);
		treeStream.append(ddlName).append(TAB);
		treeStream.append(node.getNodeId()).append(TAB);
		treeStream.append(node.getVariation()).append(TAB);
		treeStream.append(node.getOldSubset()).append(TAB);
		treeStream.append(company).append(TAB);
		treeStream.append(node.getProductPrefix()).append(NEW_LINE);
		
		
		Iterator children = node.getChildren().iterator();
		while (children.hasNext())
			processNode((Node) children.next(),treeStream,level+1,nodeId);
	}
}
