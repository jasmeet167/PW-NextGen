package com.csc.fsg.life.pw.web.actions.clone;

import java.sql.*;
import java.util.*;

import com.csc.fsg.life.pw.common.util.Utils;
//import com.csc.fsg.life.pw.web.controller.PWTask;
import com.csc.fsg.life.pw.web.environment.Environment;
import com.csc.fsg.life.pw.web.environment.EnvironmentManager;
import com.csc.fsg.life.pw.web.io.StructureTableCache;
import com.csc.fsg.life.pw.web.io.TableDescriptor;

/* Modifications: T0091, ENH961, CCCV-E768, T0129, WMA-1209, WMA-1550 */
// ENH961 - set status in task.

public class OrphanTreeCreator {

	private Connection localConn = null;
	//private PWTask task = null;
	private Environment environment = null;
	private String treeProductPrefix = null;
//	
//	private Map<String, String> nonOrphanList = new HashMap<String, String>();
	
	public OrphanTreeCreator(Environment environment, String productPrefix, Connection conn/*, PWTask task*/){
		this.environment = environment;
		this.treeProductPrefix = productPrefix;
		localConn = conn;
		//this.task = task;
	}
	
//	private void prepareNonOrphanList() throws Exception{
//		HashMap nonOrphanList = new HashMap();
//		Node root = new Node("@non-orphans", "@non-orphans", "@non-orphans");
//		addPlanPointers(root);
//		addIndexPointers(root);
//		add2List(root);
//		root = null;
//	}
	
//	private void add2List(Node node) {
//		String str = node.str2Compare();
//		if (!nonOrphanList.containsKey(str))
//			nonOrphanList.put(str,str);
//		Iterator<Node> iter = node.getChildren().iterator();
//		while (iter.hasNext()) {
//			add2List(iter.next());
//		}
//	}
	
	public Node getOrphanTree(boolean markImpliedRows) throws Exception{
		//long stTime = System.currentTimeMillis();
		Node root = new Node("@orphans", "@orphans", "@orphans"); 
		addFirstLevelOrphans(root);
		addIndexPointers(root);
		setOrphanFlag(root);
		//System.out.println("Time taken to get Orphan Tree "+(System.currentTimeMillis()-stTime)+" ms");
//		if (markImpliedRows)
//			markImpliedOrphans(root);
		return root;
	}

//	private void markImpliedOrphans(Node root) throws Exception {
//		long stTime = System.currentTimeMillis();
//		prepareNonOrphanList();
//		//System.out.println("Time taken to get Non-Orphan Tree "+(System.currentTimeMillis()-stTime)+" ms");
//		stTime = System.currentTimeMillis();
//		setOrphanFlag(root);
//		//System.out.println("Time taken to get mark implied orphans "+(System.currentTimeMillis()-stTime)+" ms");
//		nonOrphanList.clear();
//	}

//	private void addPlanPointers(Node root) throws Exception, SQLException 
//	{
//		PreparedStatement ps = null;
//		ResultSet rs = null;
//		
//		try {
//			StringBuffer query = new StringBuffer();
//			query.append(" Select TABLE_PTR_ID, TABLE_PTR_SUBSET, TABLE_PTR_VAR from "); 
//			query.append(" SESSION.MERGEDX WHERE ");
//			//query.append(" APPL.T000X WHERE ENVIRONMENT = 'MFE_DEV2_93' AND ");
//			//query.append(" COMPANY_CODE = '"+company+"' and PRODUCT_PREFIX = '"+prefix+"'");
//				
//			ps = localConn.prepareStatement(query.toString());
//			rs = ps.executeQuery();
//			
//			while (rs.next()) 
//				root.add(rs.getString(1), rs.getString(2), rs.getString(3));
//			
//		} finally {
//				Utils.closeResultSet(rs);
//				Utils.closePreparedStatement(ps);
//		}
//	}
	
	private  void addIndexPointers( Node root) throws Exception {

		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			
			StringBuffer query = new StringBuffer();
			query.append(" Select PRODUCT_PREFIX, SECONDARY_TABLE_ID, SECNDRY_PTR_SUBSET , SECNDRY_TABLE_VAR from ");
			query.append(" SESSION.MERGEDXA WHERE ");
			//query.append(" APPL.T000XA WHERE ENVIRONMENT = 'MFE_DEV1_93' AND ");
			//query.append(" COMPANY_CODE = '"+company+"' and PRODUCT_PREFIX = '"+prefix+"' AND ");
			query.append("PRIMARY_TABLE_ID = ? AND PRIMARY_PTR_SUBSET = ?  ");
			query.append(" ORDER BY SECONDARY_TABLE_ID, SECNDRY_PTR_SUBSET, SECNDRY_TABLE_VAR ");
			
			ps = localConn.prepareStatement(query.toString());
			processPointers(ps, root);

		} finally {
			Utils.closeResultSet(rs);
			Utils.closePreparedStatement(ps);
	
		}
	}
	
	private void processPointers(PreparedStatement ps, Node node) throws Exception {
		Iterator<Node> children = node.getChildren().iterator();
		while (children.hasNext()) {
			Node childNode = children.next();
			addPointers(childNode, ps);
			processPointers(ps, childNode);
		}
	}
	
	
	private void setOrphanFlag(Node node){
		Iterator<Node> children = node.getChildren().iterator();
		while (children.hasNext()) {
			Node childNode = children.next();
			//if (!nonOrphanList.containsKey(childNode.str2Compare()))
				childNode.setOrphan(true);
			setOrphanFlag(childNode);
		}
	}
	
		
	private void addFirstLevelOrphans(Node root) throws SQLException, Exception {
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {

			StringBuffer query = new StringBuffer();
			
			query.append(" Select PRODUCT_PREFIX, SECONDARY_TABLE_ID, SECNDRY_PTR_SUBSET , SECNDRY_TABLE_VAR from "); 
			query.append(" SESSION.MERGEDXA WHERE PRIMARY_TABLE_ID ='' ");
			//query.append(" APPL.T000XA WHERE ENVIRONMENT = 'MFE_DEV1_93' AND");
			//query.append(" COMPANY_CODE = '"+company+"' and PRODUCT_PREFIX = '"+prefix+"' AND PRIMARY_TABLE_ID ='' ");
			//query.append("  AND SECONDARY_TABLE_ID = '097'  AND SECNDRY_PTR_SUBSET = '001' ");
			query.append(" ORDER BY SECONDARY_TABLE_ID, SECNDRY_PTR_SUBSET, SECNDRY_TABLE_VAR ");
			
			ps = localConn.prepareStatement(query.toString());
			rs = ps.executeQuery();
			
			StructureTableCache stcInstance = StructureTableCache.getInstance();
			while (rs.next()) {
				String productPrefix = rs.getString(1);
				String tableId = rs.getString(2);
				String subset = rs.getString(3);
				String var = rs.getString(4);
				if ( stcInstance.isInPlanStructure(treeProductPrefix, tableId) 
						&& (productPrefix.equals(treeProductPrefix)
								|| tableId.equals("H01") || productPrefix.equals("N")) ) {
					Node child = new Node(productPrefix, tableId, subset, var);
					root.add(child);
				}
				//TableDescriptor td = environment.getTableDescMgr().getTableDescriptor(child.getNodeId());
				//task.setStatus(0, "  Found Orphan Table " + td.getDDLName() +"~"+child.getOldSubset());
			}
			
		//	Utils.closeResultSet(rs);
		//	Utils.closePreparedStatement(ps);
	

		} finally {
			Utils.closeResultSet(rs);
			Utils.closePreparedStatement(ps);
		}
	}

	

	private void addPointers(Node node, PreparedStatement ps) throws Exception {
		ResultSet rs = null;
		try {
			ps.setString(1, node.getNodeId());
			ps.setString(2, node.getOldSubset());
			rs = ps.executeQuery();
			
			StructureTableCache stcInstance = StructureTableCache.getInstance();
			while (rs.next()) { 
				String productPrefix = rs.getString(1);
				String tableId = rs.getString(2);
				String subset = rs.getString(3);
				String var = rs.getString(4);
				if ( stcInstance.isInPlanStructure(treeProductPrefix, tableId) 
						&& (productPrefix.equals(treeProductPrefix)
								|| productPrefix.equals("H")) ) {
					Node child = new Node(productPrefix, tableId, subset, var);
					node.add(child);
				}
				//TableDescriptor td = environment.getTableDescMgr().getTableDescriptor(child.getNodeId());
				//task.setStatus(0, "  Found Orphan Child Table " + td.getDDLName() +"~"+child.getOldSubset());
			}
		
		} finally {
			Utils.closeResultSet(rs);
		}
	}
	
//
//	public static void main(String[] args) {
//		Connection conn = null;
//		try {
//
//			Class.forName(RowDescUtils.BR_DRIVER);
//			conn = DriverManager.getConnection("jdbc:db2:DATABASE", "username","password");
//			
////			OrphanTreeCreator creator = new OrphanTreeCreator("VCS", "A", conn);
////			long stTime = System.currentTimeMillis();
////			Node node = creator.getOrphanTree(false);
////			node.print();
////			System.out.println("Total Time "+(System.currentTimeMillis()-stTime)+" ms");
//			
//			long stTime = System.currentTimeMillis();
//			OrphanTreeWriter otw = new OrphanTreeWriter();
//			StringBuffer sb = new StringBuffer();
//			otw.writeOrphans("envname","VCS","A",conn,sb);
//			System.out.println(sb.toString());
//			System.out.println("Time taken to write Stream "+(System.currentTimeMillis()-stTime)+" ms");
//		} catch (Exception e) {
//			e.printStackTrace();
//		} finally {
//			try {
//				if (conn != null)
//					conn.close();
//			} catch (SQLException e1) {
//				e1.printStackTrace();
//			}
//		}
//	}

}
