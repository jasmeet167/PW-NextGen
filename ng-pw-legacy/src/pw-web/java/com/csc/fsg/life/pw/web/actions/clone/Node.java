package com.csc.fsg.life.pw.web.actions.clone;

import java.util.Iterator;
import java.util.Vector;

/* Modifications: T0091, WMABASEIXI-4754 */
// WMABASEIXI-4754 - add nodeStructId

public class Node {

	private String nodeId = " ";

	private String oldSubset = " ";

	private String newSubset = " ";
 
	private String variation = " ";

	private String nodeStructId = " ";

	private Vector<Node> children;
	
	private boolean isOrphan = false;
	
	private String productPrefix = null;

	public Node(String nodeId, String oldSubset, String variation) {
		this.nodeId = nodeId;
		this.oldSubset = oldSubset;
		this.variation = variation;
		children = new Vector<Node>();
	}
	
	public Node(String productPrefix, String nodeId, String oldSubset, String variation) {
		this.productPrefix = productPrefix;
		this.nodeId = nodeId;
		this.oldSubset = oldSubset;
		this.variation = variation;
		children = new Vector<Node>();
	}

	public Node add(String nodeId, String oldValue, String variation) {
		return add(new Node(nodeId, oldValue, variation));
	}
	
	public Node add(String productPrefix, String nodeId, String oldValue, String variation) {
		return add(new Node(productPrefix, nodeId, oldValue, variation));
	}

	public Node add(Node node) {
		children.add(node);
		return node;
	}

	public void toStream(Node node, StringBuffer sb) {
		sb.append(node.nodeId + "\t" + node.oldSubset + "\t" + node.newSubset
		        + "\t" + node.variation + "\n");
		Iterator<Node> iter = node.children.iterator();
		while (iter.hasNext())
			toStream(iter.next(), sb);
	}

//	private void toXMLStream(Node node, StringBuffer sb, String indent) {
//		sb.append(indent + "<NODE id=\"" + node.nodeId + "\" os=\""
//		        + node.oldSubset + "\" ns=\"" + node.newSubset + "\" var=\""
//		        + node.variation + "\" >\n");
//		Iterator<Node> iter = node.children.iterator();
//		while (iter.hasNext())
//			toXMLStream(iter.next(), sb, indent + " ");
//		sb.append(indent + "</NODE>\n");
//	}

	public Vector<Node> getChildren() {
		return children;
	}

	public String getNewSubset() {
		return newSubset;
	}

	public String getNodeId() {
		return nodeId;
	}

	public String getOldSubset() {
		return oldSubset;
	}

	public String getVariation() {
		return variation;
	}

	public void setChildren(Vector<Node> vector) {
		children = vector;
	}

	public void setNewSubset(String string) {
		newSubset = string;
	}

	public void setNodeId(String string) {
		nodeId = string;
	}

	public void setOldSubset(String string) {
		oldSubset = string;
	}

	public void setVariation(String string) {
		variation = string;
	}

	public void print() {
		print(this, "");
	}

	private void print(Node node, String indent) {
		System.out.println(" " + indent + node.nodeId + "-"
		        + node.oldSubset.trim() + "/" + node.newSubset + "-"
		        + node.variation);
		Iterator<Node> iter = node.children.iterator();
		while (iter.hasNext()) {
			print(iter.next(), indent + "|  ");
		}
	}

	public String toString() {
		return nodeId + " " + oldSubset + " " + variation + " " + newSubset;
	}

	public boolean isOrphan() {
		return isOrphan;
	}
	
	public void setOrphan(boolean isOrphan) {
		this.isOrphan = isOrphan;
	}
	
	public String str2Compare(){
		StringBuffer sb = new StringBuffer();
		sb.append(nodeId.trim());
		sb.append("-");
		sb.append(oldSubset.trim());
			if (variation.trim().length()>0){
				sb.append("-");
				sb.append(variation.trim());
			}
		return sb.toString();
	}

	public String getProductPrefix() {
		return productPrefix;
	}

	public void setProductPrefix(String productPrefix) {
		this.productPrefix = productPrefix;
	}

	public String getNodeStructId() {
		return nodeStructId;
	}
	
	public void setNodeStructId(String nodeStructId) {
		this.nodeStructId = nodeStructId;
	}
}
