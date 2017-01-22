/*
 * THIS PROGRAM IS THE PROPERTY OF CSC FINANCIAL SERVICES GROUP. IT MAY NOT BE
 * COPIED IN WHOLE OR IN PART WITHOUT THE EXPRESS WRITTEN CONSENT OF CSC
 * FINANCIAL SERVICES GROUP.
 */

package com.csc.fsg.life.pw.common;

import java.io.Serializable;

import com.csc.fsg.life.pw.common.util.IOTokenizer;

/* Modifications: ENH962 */
// ENH962 - rewrite to remove environment, product prefix, and plan type
//		Also, provide fromStream and deserializer constructor
/**
 * The StructureNode class contains the information about nodes. Nodes are
 * identified using the unique node ids. The class has been serialised so that
 * it can be used on both the client and the server
 * 
 * @author Fiona D'Souza Date 9th May 2001
 */

public class StructureNode implements Serializable {

	private int node_id;

	private String variation = " ";

	private String level = "";

	private String table_id = "";

	private String required_ind = "";

	private String valid_drop = "";

	private String translate_text = "";

	// The Node object is constructed using the nodeId

	/**
	 * Constructor StructureNode
	 * 
	 * @param node_id
	 */

	public StructureNode(int node_id) {
		setNodeId(node_id);
	}

	public StructureNode(String stream) {
		fromStream(stream);
	}
	
	/*
	 * The getter methods
	 */


	/**
	 * Method getVariation
	 * 
	 * @return
	 */

	public String getVariation() {
		return this.variation;
	}

	/**
	 * Method getLevel
	 * 
	 * @return
	 */

	public String getLevel() {
		return this.level;
	}

	/**
	 * Method getTableId
	 * 
	 * @return
	 */

	public String getTableId() {
		return this.table_id;
	}

	/**
	 * Method getRequiredInd
	 * 
	 * @return
	 */

	public String getRequiredInd() {
		return this.required_ind;
	}

	/**
	 * Method getValidDrop
	 * 
	 * @return
	 */

	public String getValidDrop() {
		return this.valid_drop;
	}

	/**
	 * Method getTranslateText
	 * 
	 * @return
	 */

	public String getTranslateText() {
		return this.translate_text;
	}

	/*
	 * The setter methods
	 */

	/**
	 * Method setVariation
	 * 
	 * @param variation
	 */

	public void setNodeId(int nodeId) {
		this.node_id = nodeId;
	}

	/**
	 * Method setVariation
	 * 
	 * @param variation
	 */

	public void setVariation(String variation) {
		this.variation = variation;
	}

	/**
	 * Method setLevel
	 * 
	 * @param level
	 */

	public void setLevel(String level) {
		this.level = level;
	}

	/**
	 * Method setTableId
	 * 
	 * @param table_id
	 */

	public void setTableId(String table_id) {
		this.table_id = table_id;
	}

	/**
	 * Method setRequiredInd
	 * 
	 * @param required_ind
	 */

	public void setRequiredInd(String required_ind) {
		this.required_ind = required_ind;
	}

	/**
	 * Method setValidDrop
	 * 
	 * @param valid_drop
	 */

	public void setValidDrop(String valid_drop) {
		this.valid_drop = valid_drop;
	}

	/**
	 * Method setTranslateText
	 * 
	 * @param translate_text
	 */

	public void setTranslateText(String translate_text) {
		this.translate_text = translate_text;
	}

	/**
	 * Method getNodeId
	 * 
	 * @return
	 */

	public int getNodeId() {
		return this.node_id;
	}

	/**
	 * Insert the method's description here. Creation date: (05/14/2001 1:52:31
	 * PM)
	 * 
	 * @return java.lang.String
	 */

	// public String toString() {
	//
	// StringBuffer sbf = new StringBuffer();
	//
	// sbf.append(level + "\t");
	// sbf.append(node_id + "\t");
	// sbf.append(plan_type + "\t");
	// sbf.append(product_prefix + "\t");
	// sbf.append(required_ind + "\t");
	// sbf.append(table_id + "\t");
	// sbf.append(translate_text + "\t");
	// sbf.append(valid_drop + "\t");
	// sbf.append(variation + "\t");
	//
	// return sbf.toString();
	// }
	/**
	 * Method toStream
	 * 
	 * @return
	 */

	public String toStream() {

		StringBuffer sbf = new StringBuffer();
		sbf.append(node_id + "\t");
		sbf.append(variation + "\t");
		sbf.append(level + "\t");
		sbf.append(table_id + "\t");
		sbf.append(required_ind + "\t");
		sbf.append(valid_drop + "\t");
		sbf.append(translate_text + "\t");

		return sbf.toString();
	}
	
	public void fromStream(String stream) {
		IOTokenizer iot = new IOTokenizer(stream, "\t");
		setNodeId(Integer.parseInt(iot.nextToken()));
		setVariation(iot.nextToken());
		setLevel(iot.nextToken());
		setTableId(iot.nextToken());
		setRequiredInd(iot.nextToken());
		setValidDrop(iot.nextToken());
		setTranslateText(iot.nextToken());
	}
}
