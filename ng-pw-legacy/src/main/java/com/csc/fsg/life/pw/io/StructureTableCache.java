
package com.csc.fsg.life.pw.io;


import java.io.*;
import java.util.*;

import javax.xml.parsers.*;

import org.apache.commons.logging.Log;
import org.w3c.dom.*;
import com.csc.fsg.life.exceptions.WrapperException;
import com.csc.fsg.life.pw.log.PWServerLogManager;

/* Modifications: T0115, ENH962, WMABASEIXI-4754, WMA-1209 */
// ENH962 - rewrite to store data by product prefix and plan type

public class StructureTableCache {

	private static Log _log = PWServerLogManager.getLog(StructureTableCache.class
			.getPackage().getName());

	private static StructureTableCache instance = null;	
	private int nodeIdentity = 0;	
	private HashMap<String, Vector<StructureNode>> ppptXNodes = null;

	protected StructureTableCache() {
		super();
		initialize();
	}
	
	public static synchronized StructureTableCache getInstance() {
		if (instance == null) 
			instance = new StructureTableCache();
		return instance;
	}
	
	public HashMap<String, Vector<StructureNode>> getAllNodes() {
		return ppptXNodes;
	}

	public Vector<StructureNode> getStructureNodes(String productPrefix, String planType) {
		Vector<StructureNode> v = ppptXNodes.get(getKey(productPrefix, planType));
		if ( v == null ) {
			_log.error("StructureTableCache: no nodes for product prefix <" 
					+ productPrefix + "> and plan type <" + planType + ">");
		}
		return v;
	}

	private String getKey(String productPrefix, String planType) {
		return productPrefix + planType;
	}
	
	private void initialize(){
		
		try {
			ppptXNodes = new HashMap<String, Vector<StructureNode>>();			
			
			DocumentBuilderFactory docBuilderFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder docBuilder = docBuilderFactory.newDocumentBuilder();
			Document doc = null;
			InputStream is = null;
			
			String[] plans = {"Annuity_Base", "Annuity_Payout", "Annuity_PDF",
							  "UL_Base", "UL_PDF","UL_Rider",
							  "Trad_Base", "Trad_PDF","Trad_Rider",
							  "HCC_HCC", "NP_PDF"
							 };
			
			for (int i = 0; i < plans.length; i++) {
				Vector<StructureNode> v = new Vector<StructureNode>();
				is = StructureTableCache.class.getResourceAsStream("/treeStructure/"+plans[i]+".xml");
				doc = docBuilder.parse(is);
			
				Node rootNode = doc.getElementsByTagName(plans[i].toUpperCase()).item(0);
			
				String prefix = ((Element) rootNode).getAttribute("productPrefix");
				String planType = ((Element) rootNode).getAttribute("planType");
				
				cacheNodes(rootNode,0,1,v);
				ppptXNodes.put(getKey(prefix, planType), v);
			}
		} catch (Exception e) {
			throw WrapperException.wrap(e);
		}
	}
	
	
	private void cacheNodes(Node xnode, int parentId, int level,
			Vector<StructureNode> v) throws Exception {

		NodeList list = xnode.getChildNodes();

		for (int p = 0; p < list.getLength(); p++) {
			
			Node cnode = list.item(p);
			if (!(cnode instanceof Element))
				continue;

			Element ele = (Element) cnode;
			nodeIdentity++;
			
			StructureNode node = new StructureNode(nodeIdentity);
			node.setValidDrop(parentId + "");
			node.setLevel(level + "");
			node.setTableId(ele.getAttribute("tableId"));
			node.setRequiredInd(ele.getAttribute("req"));
			node.setVariation(ele.getAttribute("var"));
			node.setTranslateText(ele.getAttribute("varText"));

			v.add(node);
			int nextLevel = level + 1;
			cacheNodes(cnode, nodeIdentity, nextLevel, v);
		}
	}


	public StructureNode getNode(String productPrefix, String planType, String tableId, String variation,
	        String levelIndicator, String validDrop) {

		Vector<StructureNode> allNodes = getStructureNodes(productPrefix, planType);

		for ( StructureNode sn : allNodes ) {
			if ( tableId.trim().equals(sn.getTableId().trim())
			        && variation.trim().equals(sn.getVariation().trim())
			        && levelIndicator.trim().equals(sn.getLevel().trim())
			        && validDrop.trim().equals(sn.getValidDrop().trim()) ) {
				return sn;
			}
		}

		return null;
	}

	// WMABASEIXI-4754 - added getNode without level
	public StructureNode getNode(String productPrefix, String planType, String tableId, String variation,
	        String validDrop) {

		Vector<StructureNode> allNodes = getStructureNodes(productPrefix, planType);

		for ( StructureNode sn : allNodes ) {
			if ( tableId.trim().equals(sn.getTableId().trim())
			        && variation.trim().equals(sn.getVariation().trim())
			        && validDrop.trim().equals(sn.getValidDrop().trim()) ) {
				return sn;
			}
		}

		return null;
	}

	public String getNodeTranslation(String productPrefix,
	        String planType, String tableId, String variation,
	        String levelIndicator) {

		Vector<StructureNode> allNodes = getStructureNodes(productPrefix, planType);

		for ( StructureNode sn : allNodes ) {
			if ( tableId.trim().equalsIgnoreCase(sn.getTableId().trim())
			        && variation.trim().equalsIgnoreCase(sn.getVariation().trim())
			        && levelIndicator.trim().equalsIgnoreCase(sn.getLevel().trim()) ) {
				return sn.getTranslateText();
			}
		}
		return null;
	}
			
	public List<StructureNode> getRequiredNodes(String productPrefix, String planType) {

		List<StructureNode> nodes = new ArrayList<StructureNode>();
		
		Vector<StructureNode> allNodes = getStructureNodes(productPrefix, planType);
		
		for ( StructureNode node : allNodes ) {
			if ( node.getRequiredInd().trim().equalsIgnoreCase("Y") )
				nodes.add(node);
		}
		
		return nodes;
	}

	public boolean isInPlanStructure(String productPrefix, String table) {
		HashMap<String, Vector<StructureNode>> allNodes = getAllNodes();

		Set<Map.Entry<String, Vector<StructureNode>>> entries = allNodes.entrySet();
		for ( Map.Entry<String, Vector<StructureNode>> entry : entries ) {
			// for each product prefix / plan type pair
			String prefixAndPlanType = entry.getKey();
			String prefix = prefixAndPlanType.substring(0,1);
			String planType = prefixAndPlanType.substring(1,2);
			if ( !prefix.equals(productPrefix) )
				continue;
			if ( planType.equals("P") && !prefix.equals("N") )
				continue;
			
			Vector<StructureNode> nodes = entry.getValue();
			for ( StructureNode node : nodes ) {
				if ( node.getTableId().equals(table) )
					return true;
			}
		}
		return false;
	}
}
