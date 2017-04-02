package com.csc.fsg.life.pw.web.io;

import java.util.ArrayList;

/* Modifications: T0091 */

/**
 * @author ct07492
 */
public class IndexTableQueryFilter extends SouceQueryFilter {

	private ArrayList<String> childTableIds = null;

	private ArrayList<String> childTableSubsets = null;

	private ArrayList<String> childTableVars = null;

	private ArrayList<String> parentTableIds = null;

	private ArrayList<String> parentTableSubsets = null;

	/**
	 * Constructor for IndexTableQueryFilter.
	 */
	public IndexTableQueryFilter() {
		super();
	}

	/**
	 * Method getChildTableId
	 * 
	 * @return
	 */

	public ArrayList<String> getChildTableIds() {
		return childTableIds;
	}

	/**
	 * Method getChildTableSubset
	 * 
	 * @return
	 */

	public ArrayList<String> getChildTableSubsets() {
		return childTableSubsets;
	}

	/**
	 * Method getChildTableVar
	 * 
	 * @return
	 */

	public ArrayList<String> getChildTableVars() {
		return childTableVars;
	}

	/**
	 * Method getParentTableId
	 * 
	 * @return
	 */

	public ArrayList<String> getParentTableIds() {
		return parentTableIds;
	}

	/**
	 * Method getParentTableSubset
	 * 
	 * @return
	 */

	public ArrayList<String> getParentTableSubsets() {
		return parentTableSubsets;
	}

	/**
	 * Method setChildTableIds
	 * 
	 * @param ArrayList
	 *            childTableIds
	 */

	public void setChildTableIds(ArrayList<String> childTableIds) {
		this.childTableIds = childTableIds;
	}

	/**
	 * Method setChildTableId
	 * 
	 * @param String
	 *            childTableId
	 */

	public void setChildTableId(String childTableId) throws Exception {
		if (childTableIds != null) {
			throw new Exception("childTableIds already set");
		}
		childTableIds = new ArrayList<String>();
		childTableIds.add(childTableId);
	}

	/**
	 * Method setChildTableSubsets
	 * 
	 * @param ArrayList
	 *            childTableSubsets
	 */

	public void setChildTableSubsets(ArrayList<String> childTableSubsets) {
		this.childTableSubsets = childTableSubsets;
	}

	/**
	 * Method setChildTableSubset
	 * 
	 * @param childTableSubset
	 */

	public void setChildTableSubset(String childTableSubset) throws Exception {
		if (childTableSubsets != null) {
			throw new Exception("childTableSubsets already set");
		}
		childTableSubsets = new ArrayList<String>();
		childTableSubsets.add(childTableSubset);
	}

	/**
	 * Method setChildTableVars
	 * 
	 * @param ArrayList
	 *            childTableVars
	 */

	public void setChildTableVars(ArrayList<String> childTableVars) {
		this.childTableVars = childTableVars;
	}

	/**
	 * Method setChildTableVar
	 * 
	 * @param childTableVar
	 */

	public void setChildTableVar(String childTableVar) throws Exception {
		if (childTableVars != null) {
			throw new Exception("childTableVars already set");
		}
		childTableVars = new ArrayList<String>();
		childTableVars.add(childTableVar);
	}

	/**
	 * Method setParentTableIds
	 * 
	 * @param ArrayList
	 *            parentTableIds
	 */

	public void setParentTableIds(ArrayList<String> parentTableIds) throws Exception {
		this.parentTableIds = parentTableIds;
	}

	/**
	 * Method setParentTableId
	 * 
	 * @param newParentTableId
	 */

	public void setParentTableId(String parentTableId) throws Exception {
		if (parentTableIds != null) {
			throw new Exception("parentTableIds already set");
		}
		parentTableIds = new ArrayList<String>();
		parentTableIds.add(parentTableId);
	}

	/**
	 * Method setParentTableSubsets
	 * 
	 * @param ArrayList
	 *            parentTableSubsets
	 */

	public void setParentTableSubsets(ArrayList<String> parentTableSubsets)
	        throws Exception {
		this.parentTableSubsets = parentTableSubsets;
	}

	/**
	 * Method setParentTableSubset
	 * 
	 * @param newParentTableSubset
	 */

	public void setParentTableSubset(String parentTableSubset) throws Exception {
		if (parentTableSubsets != null) {
			throw new Exception("parentTableSubsets already set");
		}
		parentTableSubsets = new ArrayList<String>();
		parentTableSubsets.add(parentTableSubset);
	}

}
